package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IQuotCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;

@Service
@Repository
@Transactional

public class QuotCompDtService implements IQuotCompDtService{
	
	@Autowired
	private IQuotCompDtRepository quotCompDtRepository;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IFindingRateMastService findingRateMastService;

	@Override
	public List<QuotCompDt> findAll() {
		return quotCompDtRepository.findAll();
	}

	@Override
	public void save(QuotCompDt quotCompDt) {
		quotCompDtRepository.save(quotCompDt);
		
	}

	@Override
	public void delete(int id) {
		QuotCompDt quotCompDt = quotCompDtRepository.findOne(id);
		quotCompDt.setDeactive(true);
		quotCompDt.setDeactiveDt(new java.util.Date());
		quotCompDtRepository.save(quotCompDt);
		
	}

	@Override
	public Long count() {
		return quotCompDtRepository.count();
	}

	@Override
	public QuotCompDt findOne(int id) {
		return quotCompDtRepository.findOne(id);
	}

	@Override
	public List<QuotCompDt> findByQuotMtAndDeactive(QuotMt quotMt,
			Boolean deactive) {
		return quotCompDtRepository.findByQuotMtAndDeactive(quotMt, deactive);
	}

	@Override
	public List<QuotCompDt> findByQuotDtAndDeactive(QuotDt quotDt,
			Boolean deactive) {
		return quotCompDtRepository.findByQuotDtAndDeactive(quotDt, deactive);
	}

	@Override
	public void setQuotCompDt(List<DesignComponent> designComponents,QuotMt quotMt, QuotDt quotDt, Principal principal) {
		
		if(designComponents != null){
			
			for(DesignComponent designComponent : designComponents){
			
			QuotCompDt quotCompDt = new QuotCompDt();
			
				quotCompDt.setQuotMt(quotMt);
				quotCompDt.setQuotDt(quotDt);
				quotCompDt.setComponent(designComponent.getComponent());
				quotCompDt.setCompQty(designComponent.getQuantity());
				quotCompDt.setCreatedBy(principal.getName());
				quotCompDt.setCreatedDate(new java.util.Date());
				quotCompDt.setPurity(quotDt.getPurity());
				quotCompDt.setColor(quotDt.getColor());
				quotCompDt.setMetalWt(designComponent.getCompWt());
								
				quotCompDtRepository.save(quotCompDt);
			
			}
			
			
		}
		
	}
	
	
	
	
	@Override
	public String transactionalSave(QuotCompDt quotCompDt, Integer id,
			Integer quotMtId, Integer quotDtId, Principal principal,Boolean netWtWithCompFlg) {
		
		String retVal = "-1";
		
		QuotMt quotMt = quotMtService.findOne(quotMtId);
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			quotCompDt.setQuotMt(quotMt);
			quotCompDt.setQuotDt(quotDt);
			quotCompDt.setPurityConv(quotDt.getPurityConv());
			quotCompDt.setCreatedBy(principal.getName());
			quotCompDt.setCreatedDate(new java.util.Date());
		}else{
			quotCompDt.setQuotMt(quotMt);
			quotCompDt.setQuotDt(quotDt);
			quotCompDt.setPurityConv(quotDt.getPurityConv());
			quotCompDt.setId(id);
			quotCompDt.setModiBy(principal.getName());
			quotCompDt.setModiDate(new java.util.Date());
			retVal = "-2";
		}
		
		
		
		if(quotCompDt.getComponent() != null && quotCompDt.getPurity() != null){
			
			FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(quotCompDt.getComponent(), quotCompDt.getQuotMt().getParty(),
					quotCompDt.getPurity(), false);
			
			if(findingRateMast != null){
				if(findingRateMast.getPerPcRate().equals(true)){
				//	quotCompDt.setCompRate(findingRateMast.getRate());
					quotCompDt.setPerPcRate(true);
					quotCompDt.setPerGramRate(false);
				}else{
					//quotCompDt.setCompRate(findingRateMast.getRate());
					quotCompDt.setPerPcRate(false);
					quotCompDt.setPerGramRate(true);
				}
			}
			
		}
		
		if(quotCompDt.getMetalWt() == null){
			quotCompDt.setMetalWt(0.0);
		}
		
		save(quotCompDt);
		
	
	quotDtService.updateGrossWt(quotDt,netWtWithCompFlg);	
	
		
		
		

		
		
		
		quotDtService.updateFob(quotDt,netWtWithCompFlg);
		
		
		return retVal;
	}
	
	
	
	@Override
	public void transactionalDelete(QuotCompDt quotCompDt,Boolean netWtWithCompFlg) {
		delete(quotCompDt.getId());
		quotDtService.updateGrossWt(quotCompDt.getQuotDt(),netWtWithCompFlg);
		quotDtService.updateFob(quotCompDt.getQuotDt(),netWtWithCompFlg);
	}
	
	
	

}

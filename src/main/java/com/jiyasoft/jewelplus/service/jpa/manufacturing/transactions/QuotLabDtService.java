package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IQuotLabDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;

@Service
@Repository
@Transactional
public class QuotLabDtService implements IQuotLabDtService {
	
	@Autowired
	private IQuotLabDtRepository quotLabDtRepository;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IQuotDtService quotDtService;

	@Override
	public List<QuotLabDt> findAll() {
		return quotLabDtRepository.findAll();
	}

	@Override
	public void save(QuotLabDt quotLabDt) {
		quotLabDtRepository.save(quotLabDt);
		
	}

	@Override
	public void delete(int id) {
		QuotLabDt quotLabDt = quotLabDtRepository.findOne(id);
		quotLabDt.setDeactive(true);
		quotLabDt.setDeactiveDt(new java.util.Date());
		quotLabDtRepository.save(quotLabDt);
		
	}

	@Override
	public Long count() {
		return quotLabDtRepository.count();
	}

	@Override
	public QuotLabDt findOne(int id) {
		return quotLabDtRepository.findOne(id);
	}

	@Override
	public List<QuotLabDt> findByQuotMtAndDeactive(QuotMt quotMt,
			Boolean deactive) {
		return quotLabDtRepository.findByQuotMtAndDeactive(quotMt, deactive);
	}

	@Override
	public List<QuotLabDt> findByQuotDtAndDeactive(QuotDt quotDt,
			Boolean deactive) {
		return quotLabDtRepository.findByQuotDtAndDeactive(quotDt, deactive);
	}

	@Override
	public List<QuotLabDt> findByQuotDtAndMetalAndDeactive(QuotDt quotDt,
			Metal metal, Boolean deactive) {
		return quotLabDtRepository.findByQuotDtAndMetalAndDeactive(quotDt, metal, deactive);
	}

	@Override
	public String transactionalSave(QuotLabDt quotLabDt, Integer id,
			Integer quotMtId, Integer quotDtId, Principal principal,Boolean netWtWithCompFlg) {
		
		String retVal = "-1";
		
		Integer chkValidation=0;
		
		if(quotLabDt.getPcsWise().equals(true)){
			chkValidation=1;
			
		}
		if(quotLabDt.getGramWise() == true ){
			chkValidation+=1;
		}
		
		if(quotLabDt.getPercentWise() == true ){
			chkValidation+=1;
		}
		
		if(quotLabDt.getPerCaratRate() == true){
			chkValidation+=1;

		}
		
		if(chkValidation !=1){
			return retVal = "-11";
		}
		
		
		QuotMt quotMt = quotMtService.findOne(quotMtId);
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			quotLabDt.setQuotMt(quotMt);
			quotLabDt.setQuotDt(quotDt);
			quotLabDt.setCreatedBy(principal.getName());
			quotLabDt.setCreatedDate(new java.util.Date());
		}else{
			quotLabDt.setQuotMt(quotMt);
			quotLabDt.setQuotDt(quotDt);
			quotLabDt.setId(id);
			quotLabDt.setModiBy(principal.getName());
			quotLabDt.setModiDate(new java.util.Date());
			retVal = "-2";
		}
		
		
		save(quotLabDt);
		
		//quotDtService.updateGrossWt(quotDt);
		quotDtService.updateFob(quotDt,netWtWithCompFlg);
		
		
		return retVal;
	}

	@Override
	public void transactionalDelete(QuotLabDt quotLabDt,Boolean netWtWithCompFlg) {
		delete(quotLabDt.getId());
		//quotDtService.updateGrossWt(quotLabDt.getQuotDt());
		quotDtService.updateFob(quotLabDt.getQuotDt(),netWtWithCompFlg);
	}

	/*@Override
	public void setQuotLabDt(QuotMt quotMt, QuotDt quotDt, Principal principal) {
		
		Category category = categoryService.findOne(quotDt.getDesign().getCategory().getId());
		
			
		List<LabourCharge> labourCharges = labourChargeService.findByCategoryAndDeactive(category,false);
		
		for(LabourCharge labourCharge:labourCharges){
			
			QuotLabDt quotLabDt=findby
			
			QuotLabDt quotLabDt = new QuotLabDt();
			
			quotLabDt.setQuotMt(quotMt);
			quotLabDt.setQuotDt(quotDt);
			quotLabDt.setLabourType(labourCharge.getLabourType());
			quotLabDt.setLabourRate(labourCharge.getRate());
			
			if(labourCharge.getPerPcsRate() == true){
				quotLabDt.setPcsWise(true);
			}else if(labourCharge.getPerGramRate() == true){
				quotLabDt.setGramWise(true);
			}else{
				quotLabDt.setPercentWise(true);
			}
			
			quotLabDt.setCreatedDate(new java.util.Date());
			quotLabDt.setCreatedBy(principal.getName());
			
			quotLabDtRepository.save(quotLabDt);
			
			
			
			
		}
		
		
	}*/
	

}

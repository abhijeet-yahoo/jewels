package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IQuotStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterFLService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotStnDtService;

@Service
@Repository
@Transactional
public class QuotStnDtService implements IQuotStnDtService {

	@Autowired
	private IQuotStnDtRepository quotStnDtRepository;
	
	@Autowired
	private IQuotQualityService quotQualityService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
		
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private IHandlingMasterFLService handlingMasterFLService;
	
	@Override
	public List<QuotStnDt> findAll() {
		return quotStnDtRepository.findAll();
	}

	@Override
	public void save(QuotStnDt quotStnDt) {
		 quotStnDtRepository.save(quotStnDt);
		
	}

	@Override
	public void delete(int id) {
		QuotStnDt quotStnDt = quotStnDtRepository.findOne(id);
		quotStnDt.setDeactive(true);
		quotStnDt.setDeactiveDt(new java.util.Date());
		quotStnDtRepository.save(quotStnDt);
		
	}

	@Override
	public Long count() {
		return quotStnDtRepository.count();
	}

	@Override
	public QuotStnDt findOne(int id) {
		return quotStnDtRepository.findOne(id);
	}

	@Override
	public List<QuotStnDt> findByQuotMtAndDeactive(QuotMt quotMt,
			Boolean deactive) {
		return quotStnDtRepository.findByQuotMtAndDeactive(quotMt, deactive);
	}

	@Override
	public List<QuotStnDt> findByQuotDtAndDeactive(QuotDt quotDt,
			Boolean deactive) {
		return quotStnDtRepository.findByQuotDtAndDeactive(quotDt, deactive);
	}

	
	@Override
	public void setQuotStnDt(List<DesignStone> designStones,QuotMt quotMt,QuotDt quotDt,Principal principal) {
		
		if(designStones != null){
			
					
			List<QuotQuality> quotQualitys = quotQualityService.findByQuotMtAndDeactive(quotMt, false);
			
			Boolean recordFound = false;
			
			for(DesignStone designStone:designStones){
				
				recordFound = false;
				
				QuotStnDt quotStnDt = new QuotStnDt();
				
				quotStnDt.setQuotMt(quotMt);
				quotStnDt.setQuotDt(quotDt);
				quotStnDt.setCarat(designStone.getCarat());
				quotStnDt.setShape(designStone.getShape());
				quotStnDt.setStoneType(designStone.getStoneType());
				
				for(QuotQuality quotQuality:quotQualitys){
					if((designStone.getShape().getId() == quotQuality.getShape().getId()) && (designStone.getStoneType().getId() == quotQuality.getStoneType().getId())){
						quotStnDt.setQuality(quotQuality.getQuality());
						recordFound = true;
						break;
					}
				}
				
				if(!recordFound){
					quotStnDt.setQuality(designStone.getQuality());
				}
				
				quotStnDt.setSize(designStone.getSize());
				quotStnDt.setSizeGroup(designStone.getSizeGroup());
				quotStnDt.setSieve(designStone.getSieve());
				quotStnDt.setStone(designStone.getStone());
				quotStnDt.setSetting(designStone.getSetting());
				quotStnDt.setSettingType(designStone.getSettingType());
				quotStnDt.setCreatedBy(principal.getName());
				quotStnDt.setCreatedDate(new java.util.Date());
				quotStnDt.setPartNm(designStone.getPartNm());
				quotStnDt.setDiaCateg(designStone.getDiaCateg());
				
				quotStnDtRepository.save(quotStnDt);
				
				
				
			}
			
		}
		
	}

	@Override
	public String transactionalSave(QuotStnDt quotStnDt, Integer id,
			Integer quotMtId, Integer quotDtId, String pSieve,
			String pSizeGroup, Principal principal,Boolean netWtWithCompFlg) {
		
		String retVal = "-1";
		
		QuotMt quotMt = quotMtService.findOne(quotMtId);
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		
		
		if (id == null || id.equals("") || (id != null && id == 0)){
			quotStnDt.setCreatedDate(new java.util.Date());
			quotStnDt.setCreatedBy(principal.getName());
			quotStnDt.setQuotMt(quotMt);
			quotStnDt.setQuotDt(quotDt);
			quotStnDt.setSieve(pSieve);
			quotStnDt.setSizeGroup(stoneChartService.findByShapeAndSizeAndDeactive(quotStnDt.getShape(), quotStnDt.getSize(),false).getSizeGroup());
			
			
			
		}else{
			quotStnDt.setModiBy(principal.getName());
			quotStnDt.setModiDate(new java.util.Date());
			quotStnDt.setQuotMt(quotMt);
			quotStnDt.setQuotDt(quotDt);
			quotStnDt.setSieve(pSieve);
			quotStnDt.setSizeGroup(stoneChartService.findByShapeAndSizeAndDeactive(quotStnDt.getShape(), quotStnDt.getSize(),false).getSizeGroup());
			
			retVal = "-2";
		}
		
		if (quotStnDt.getSubShape().getId() == null) {
			quotStnDt.setSubShape(null);
		}
		if (quotStnDt.getQuality().getId() == null) {
			quotStnDt.setQuality(null);
		}
		if (quotStnDt.getStoneType().getId() == null) {
			quotStnDt.setStoneType(null);
		} 
		if (quotStnDt.getSetting().getId() == null) {
			quotStnDt.setSetting(null);
		}
		if (quotStnDt.getSettingType().getId() == null) {
			quotStnDt.setSettingType(null);
		}
		
		
		if(quotStnDt.getStoneType() != null && quotStnDt.getShape() != null &&  quotStnDt.getQuality() != null ){
			
			List<StoneRateMast> stoneRateMast=stoneRateMastService.getStoneRate(quotStnDt.getStoneType().getId(),quotStnDt.getShape().getId(),quotStnDt.getQuality().getId(), 
					quotStnDt.getSizeGroup().getId(),quotStnDt.getQuotMt().getParty().getId(),quotStnDt.getSieve());
			
			if(stoneRateMast.size() > 0){
				if(stoneRateMast.get(0).getPerPcRate() > 0){
					//quotStnDt.setStnRate(stoneRateMast.get(0).getPerPcRate());
					quotStnDt.setPerPcsRateFlg(true);
					
				}else{
					//quotStnDt.setStnRate(stoneRateMast.get(0).getStoneRate());
					quotStnDt.setPerPcsRateFlg(false);
					
				}
			}
			
			
			
			
			
			
		
		}
		
		
		if(quotStnDt.getStnRate() >0){
			
			List<HandlingMasterFl> handlingList = handlingMasterFLService.getRates(quotStnDt.getQuotMt().getParty(),quotStnDt.getStnRate());
			
			if(handlingList.size() > 0){
				if(handlingList.get(0).getPercentage() > 0){
				//	quotStnDt.setHandlingRate(handlingList.get(0).getPercentage());
					quotStnDt.setHdlgPerCarat(false);
					quotStnDt.setHdlgPercentWise(true);
					
				}else{
					//quotStnDt.setHandlingRate(handlingList.get(0).getRate());
					quotStnDt.setHdlgPerCarat(true);
					quotStnDt.setHdlgPercentWise(false);
					
				}
			}
		
			
			
		}
		
		
		save(quotStnDt);
		
		quotDtService.updateGrossWt(quotDt,netWtWithCompFlg);
		
		
		quotDtService.updateFob(quotStnDt.getQuotDt(),netWtWithCompFlg);		
		
		quotDtService.updateQltyDesc(quotDt.getId());
		
		return retVal;
	}
	
	
	@Override
	public void transactionDelete(QuotStnDt quotStnDt,Boolean netWtWithCompFlg) {
		delete(quotStnDt.getId());
		quotDtService.updateGrossWt(quotStnDt.getQuotDt(),netWtWithCompFlg);
		quotDtService.updateFob(quotStnDt.getQuotDt(),netWtWithCompFlg);
		quotDtService.updateQltyDesc(quotStnDt.getQuotDt().getId());
	}

	
	

 }

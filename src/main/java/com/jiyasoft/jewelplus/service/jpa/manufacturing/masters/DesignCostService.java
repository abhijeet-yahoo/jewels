package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCost;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCostLabour;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
//import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignCostRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignCostLabourService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignCostService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
//import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;


@Service
@Repository
@Transactional
public class DesignCostService implements IDesignCostService{

	@Autowired
	private IDesignCostRepository designCostRepository;
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IDesignComponentService designComponentService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private IDesignCostLabourService designCostLabourService;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	/*@Autowired
	private ISettingChargeService settingChargeService;*/
	
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	
	@Override
	public void save(DesignCost designCost) {
		designCostRepository.save(designCost);
	}

	@Override
	public void delete(int id) {
		
		
	}

	@Override
	public Long count() {
		return designCostRepository.count();
	}

	@Override
	public DesignCost findOne(int id) {
		return designCostRepository.findOne(id);
	}
	
	@Override
	public DesignCost findByDesignAndDeactive(Design design,Boolean deactive) {
		return designCostRepository.findByDesignAndDeactive(design, false);
	}

	@Override
	public String applyRate(Design design, Party party,Double goldRate,Double addedPerc,Double tagPerc,Double dispPerc,Double handlingPerc,Purity purity,Principal principal) {
		
		DecimalFormat df = new DecimalFormat("#.##");
		DecimalFormat df1 = new DecimalFormat("#.###");
		
		
		
		
		
		
		Double totStnValue = 0.0;
		Double totSetValue = 0.0;
		Double totHandValue = 0.0;
		Double totCarat =0.0;
		
		//-----Design Stone---//------------//
		
		List<DesignStone> designStones = designStoneService.findByDesign(design);
		
		for(DesignStone designStone:designStones){
			
			/*SettingCharge settingCharge = settingChargeService.findByStoneTypeAndShapeAndPartyAndSettingAndSettingTypeAndDeactive(designStone.getStoneType(),designStone.getShape(), party, 
					designStone.getSetting(), designStone.getSettingType(),false);
			
			if(settingCharge != null){
				designStone.setSetRate(settingCharge.getRate());
			}*/
			
			
			
			
			designStone.setSetValue(Double.parseDouble(df.format(designStone.getSetRate() * designStone.getStone())));		
			designStone.setStoneValue(Double.parseDouble(df.format(designStone.getCarat() * designStone.getStnRate())));
			
			designStone.setHandlingRate(handlingPerc);
			designStone.setHandlingValue(Double.parseDouble(df.format(((designStone.getCarat() * designStone.getStnRate()) * designStone.getHandlingRate())/100  )));
			
			totStnValue  += designStone.getStoneValue();
			totSetValue  += designStone.getSetValue();
			totHandValue += designStone.getHandlingValue();
			totCarat +=designStone.getCarat();
			
			designStoneService.save(designStone);
			
		}
		
		
		//Design Cost //
		
		
		
		DesignCost  designCost = findByDesignAndDeactive(design, false);
		
		if(designCost == null){
			 designCost = new DesignCost();
			 designCost.setDesign(design);
		}
		
		String retVal = "-1";
		
		
		
		
		
		Double  tempCal = 0.0;
		Double  tempCal2 = 0.0;
		Double netWt =0.0;
		
		netWt=Double.parseDouble(df1.format(design.getGrms()- totCarat/5));
		
		
		tempCal =  goldRate/31.1035;
		tempCal2 = (tempCal/24) * (purity.getvPurity() != null ? purity.getvPurity() : 0.0);
		designCost.setPerGramRate(Double.parseDouble(df.format(tempCal2)));
		
		designCost.setMetalRate(goldRate);
		designCost.setMetalValue(Double.parseDouble(df.format(netWt * tempCal2)));
		
		designCost.setPurity(purity);
		designCost.setAddedPerc(addedPerc);
		designCost.setTagPerc(tagPerc);
		designCost.setDispPerc(dispPerc);
		designCost.setHandlingPerc(handlingPerc);
		
		designCostRepository.save(designCost);
		
		
		
		
		

		//-----Design Component-----//--------//
		
		List<DesignComponent> designComponents = designComponentService.findByDesign(design);

		Double totCompValue = 0.0;
		tempCal = 0.0;
		tempCal2 = 0.0;
		
		for(DesignComponent designComponent:designComponents){
			
			tempCal =  goldRate/31.1035;
			tempCal2 = (tempCal/24) * (purity.getvPurity() != null ? purity.getvPurity() : 0.0);
			
			if(designComponent.getCompRate() > 0 ){
			}else{
				designComponent.setCompRate(Double.parseDouble(df.format(tempCal2)));
			}
			
			designComponent.setCompValue(Double.parseDouble(df.format(designComponent.getCompWt() * tempCal2)));
			
			totCompValue += designComponent.getCompValue();
			
			designComponentService.save(designComponent);
			
		}
		
		
		//-----Design Labour-----//--------//
		
		
		Category category = categoryService.findOne(design.getCategory().getId());
		
		List<LabourCharge> labourCharges = labourChargeService.findByCategoryAndDeactive(category,false);
		
		
		List<DesignCostLabour> designCostLabours = designCostLabourService.findByDesignAndDeactive(design,false);
		
		if(designCostLabours.size() > 0){
			designCostLabourService.deleteAll(designCostLabours);
		}
		
		Double totLabourValue = 0.0;
		
		for(LabourCharge labourCharge:labourCharges){
			
			DesignCostLabour designCostLabour = new DesignCostLabour();
			
			designCostLabour.setLabourType(labourCharge.getLabourType());
			designCostLabour.setLabourRate(labourCharge.getRate());
			designCostLabour.setDesign(design);
			
			
			if(labourCharge.getPerPcsRate() == true){
				designCostLabour.setLabourValue(Double.parseDouble(df.format(designCostLabour.getLabourRate() * 1)));
			}else if(labourCharge.getPerGramRate() == true){
				designCostLabour.setLabourValue(Double.parseDouble(df.format(designCostLabour.getLabourRate() * design.getGrms())));
			}else if (labourCharge.getPercentage() == true) {
				designCostLabour.setLabourValue(Double.parseDouble(df.format((designCost.getMetalValue() * designCostLabour.getLabourRate())/100 )));
			}
			
			
			designCostLabour.setCreatedDate(new java.util.Date());
			designCostLabour.setCreatedBy(principal.getName());
			
			totLabourValue += designCostLabour.getLabourValue();
			
			designCostLabourService.save(designCostLabour);
			
			
		}
		
		
		//-quotDt--edit and save//
		
		designCost.setStnValue(Double.parseDouble(df.format(totStnValue)));
		designCost.setSetValue(Double.parseDouble(df.format(totSetValue)));
		designCost.setHandlingValue(Double.parseDouble(df.format(totHandValue)));
		designCost.setCompValue(Double.parseDouble(df.format(totCompValue)));
		designCost.setLabourValue(Double.parseDouble(df.format(totLabourValue)));
				
		Double totCompMetalWt = 0.0;
		for(DesignComponent designComponent:designComponents){
			totCompMetalWt += designComponent.getCompWt();
		}
				
		designCostRepository.save(designCost);
		
		return retVal;
	}

	
	@Override
	public String updateFob(Design design,Double addedPerc,Double dispPerc) {

		DesignCost  designCost = findByDesignAndDeactive(design, false);
		
		String retVal = "-1";
		
		Double tempVal = 0.0;
		Double vFob = 0.0;
		Double returedFinalPrice;
		String tempFinalPrice;
		String retFinalPrice;
		
			
		DecimalFormat df = new DecimalFormat("#.##");
		
		tempVal = designCost.getMetalValue()+designCost.getStnValue()+designCost.getCompValue()+designCost.getLabourValue()+designCost.getSetValue()+designCost.getHandlingValue();
		vFob = (addedPerc/100)*tempVal;
		vFob = vFob + tempVal;
		designCost.setFob(Double.parseDouble(df.format(vFob)));
		tempFinalPrice = df.format(dispPerc == 0 ? vFob : vFob * dispPerc);
		
		
		retFinalPrice = costingDtService.calculateFinalPrice(tempFinalPrice);
		returedFinalPrice = Double.parseDouble(retFinalPrice);
		designCost.setFinalPrice(Double.parseDouble(df.format(returedFinalPrice)));
		
		designCostRepository.save(designCost);
	
			
		return retVal;
	}

}

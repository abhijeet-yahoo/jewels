package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QTranDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostingDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class CostingDtService implements ICostingDtService {

	@Autowired
	private ICostingDtRepository costingDtRepository;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private ICostStnDtService costStnDtService;
	
	@Autowired
	private ICostCompDtService costCompDtService;
	
	@Autowired
	private ICostLabDtService costLabDtService;
	
	@Autowired
	private ICostMetalDtService costMetalDtService;
	
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ICostMetalRateService costMetalRateService;
	
	@Autowired
	private ISettingChargeService settingChargeService;
	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;
	
	@Autowired
	private IHandlingMasterService handlingMasterService;
	
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private IFindingRateMastService findingRateMastService;

	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IPartyService partyService;
	

	@Override
	public List<CostingDt> findAll() {
		return costingDtRepository.findAll();
	}

	@Override
	public void save(CostingDt costingDt) {
		costingDtRepository.save(costingDt);

	}

	@Override
	public void delete(int id) {
		CostingDt costingDt = costingDtRepository.findOne(id);
		costingDt.setDeactive(true);
		costingDt.setDeactiveDt(new java.util.Date());
		costingDtRepository.save(costingDt);

	}

	@Override
	public Long count() {
		return costingDtRepository.count();
	}

	@Override
	public CostingDt findOne(int id) {
		return costingDtRepository.findOne(id);
	}

	@Override
	public CostingDt findByUniqueId(Long uniqueId) {
		return costingDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<CostingDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		return costingDtRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public String applyRate(CostingDt costingDt, Principal principal) {
		
		String retVal="";
		
		if(costingDt.getrLock().equals(false)){

		applyMetal(costingDt);
		List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
					
		
		applyStoneRate(costStnDts);
		
		List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
		applyCompRate(costCompDts);
		
		applyLabRate(costingDt,principal);
		
		updateFob(costingDt);
		retVal="1";
		
		}
		
		return retVal;
		
	
	
		
	}

	@Override
	public String updateFob(CostingDt costingDt) {
	
		try {
		
		Double  tempCal = 0.0;
		Double  tempCal2 = 0.0;
		Double tempLoss=0.0;
		Double totMetalValue=0.0;
		Double totStnValue=0.0;
		Double totSetValue=0.0;
		Double totHdlgValue=0.0; 
		Double totCompValue=0.0;
		Double totLabourValue=0.0;
		Double totCarat=0.0;
		
		
		
		
		
		
		Double tempVal = 0.0;
		
		
		
		if(costingDt.getrLock() == true){	
		}else{
			
		
	/*-------------------------------------------------Metal Fob--------------------------------*/					
			
			List<CostMetalDt> costMetalDts = costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
			if(costMetalDts !=null){
				for(CostMetalDt costMetalDt: costMetalDts){
					if (costMetalDt.getPurity() != null && costMetalDt.getMetalRate()>0) {
						Purity purity = purityService.findOne(costMetalDt.getPurity().getId());
						String metalNm=purity.getMetal().getName();
						if(metalNm.equalsIgnoreCase("Gold")){
							tempCal =  costMetalDt.getMetalRate()/costMetalDt.getPurity().getMetal().getSpecificGravity();
							tempCal2 = (tempCal/(costMetalDt.getCostingMt().getIn999().equals(true)?24:23.88)) * (costMetalDt.getPurity().getvPurity() != null ? costMetalDt.getPurity().getvPurity() : 0.0);
							tempLoss=tempCal2*costMetalDt.getLossPerc()/100;
							costMetalDt.setPerGramRate( Math.round((tempCal2 + tempLoss)*100.0)/100.0);
							costMetalDt.setMetalValue(Math.round((costMetalDt.getMetalWeight()*costMetalDt.getPerGramRate())*100.0)/100.0);
						
						}else if (metalNm.equalsIgnoreCase("Silver")) {
							tempCal =  costMetalDt.getMetalRate()/costMetalDt.getPurity().getMetal().getSpecificGravity();
							if(costMetalDt.getCostingMt().getIn999().equals(true)){
								tempCal2 = tempCal;
							}else{
								tempCal2 = (tempCal*(costMetalDt.getPurity().getPurityConv() != null ? costMetalDt.getPurity().getPurityConv() : 0.0));	
							}
							
							tempLoss=tempCal2*costMetalDt.getLossPerc()/100;
							costMetalDt.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
							costMetalDt.setMetalValue(Math.round((costMetalDt.getMetalWeight()*costMetalDt.getPerGramRate())*100.0)/100.0);
							
							
								
						}else if (metalNm.equalsIgnoreCase("Alloy")) {
							tempCal =  costMetalDt.getMetalRate()/1000;
							tempLoss=tempCal*costMetalDt.getLossPerc()/100;
							
							costMetalDt.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
							costMetalDt.setMetalValue(Math.round((costMetalDt.getMetalWeight()*costMetalDt.getPerGramRate())*100.0)/100.0);
											
							
						}
						
						totMetalValue +=costMetalDt.getMetalValue();
					
					}
					
					
					
				}
				
			}
			
	/*---------------------------------------------------------------------------------------------------------*/			
			
	/*----------------------------------------------Stone Fob------------------------------------------------------------------*/			
			
			List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
			for(CostStnDt costStnDt : costStnDts){			
					
					if(costStnDt.getPerPcsRateFlg().equals(false)){
						costStnDt.setStoneValue(Math.round((costStnDt.getCarat() * costStnDt.getStnRate())*100.0)/100.0);
					}else{
						costStnDt.setStoneValue(Math.round((costStnDt.getStone() * costStnDt.getStnRate())*100.0)/100.0);
					}
									
					costStnDt.setSetValue(Math.round((costStnDt.getSetRate()*costStnDt.getStone())*100.0)/100.0);
					
					
					if(costStnDt.getHdlgPerCarat().equals(true)){
						costStnDt.setHandlingValue(Math.round((costStnDt.getCarat() * costStnDt.getHandlingRate())*100.0)/100.0);
					}else{
						costStnDt.setHandlingValue(Math.round(((costStnDt.getStoneValue() * costStnDt.getHandlingRate())/100)*100.0)/100.0);
					}
					
								
					costStnDtService.save(costStnDt);
					totCarat +=costStnDt.getCarat();
					totStnValue  	 += costStnDt.getStoneValue();
					totSetValue  	 += costStnDt.getSetValue();
					totHdlgValue 	 += costStnDt.getHandlingValue();
										
					
				}

	/*---------------------------------------------------------------------------------------------------------*/			
			
	/*----------------------------------------------Component Fob------------------------------------------------------------------*/		
			
			
			List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
			 
			for(CostCompDt costCompDt : costCompDts){
				if(costCompDt.getPerGramRate().equals(true)){
					costCompDt.setCompValue(Math.round((costCompDt.getMetalWt()* costCompDt.getCompRate())*100.0)/100.0);
				}else{
					costCompDt.setCompValue(Math.round((costCompDt.getCompPcs()* costCompDt.getCompRate())*100.0)/100.0);
				}
						
				costCompDtService.save(costCompDt);
				totCompValue += costCompDt.getCompValue();
				
				
			}
	/*---------------------------------------------------------------------------------------------------------*/				
			
	/*----------------------------------------------Labour Fob------------------------------------------------------------------*/		
			
			List<CostLabDt> costLabDts = costLabDtService.findByCostingDtAndDeactive(costingDt, false);
			
			
			for(CostLabDt costLabDt : costLabDts){
				
				
				Double vTotMetalWt=0.0;
				Double vTOtMetalVal=0.0;
				
				if (costMetalDts != null) {
					vTotMetalWt = 0.0;
					vTOtMetalVal=0.0;
					for (CostMetalDt costMetalDt : costMetalDts) {
						if (costLabDt.getMetal().equals(costLabDt.getCostingDt().getPurity().getMetal())) {
							vTotMetalWt += costMetalDt.getMetalWeight();
							vTOtMetalVal +=costMetalDt.getMetalValue();
						}

					}

				}
				
				
				if(costLabDt.getPcsWise() == true){
					costLabDt.setLabourValue(Math.round((costLabDt.getLabourRate() * costingDt.getPcs())*100.0)/100.0);
				
				}else if(costLabDt.getGramWise() == true){
					costLabDt.setLabourValue(Math.round((costLabDt.getLabourRate() * vTotMetalWt)*100.0)/100.0);
				}else if(costLabDt.getPercentWise() == true){
					costLabDt.setLabourValue(Math.round(((vTOtMetalVal * costLabDt.getLabourRate())/100 )*100.0)/100.0);
				}else if(costLabDt.getPerCaratRate() == true){
					costLabDt.setLabourValue(Math.round(((totCarat * costLabDt.getLabourRate())/100 )*100.0)/100.0);
				}
				
				
				
				
				
				costLabDtService.save(costLabDt);
				totLabourValue += costLabDt.getLabourValue();
				
			}
			
			
	/*---------------------------------------------------------------------------------------------------------*/					
			
			
			costingDt.setMetalValue(Math.round((totMetalValue)*100.0)/100.0);
			costingDt.setStoneValue(Math.round((totStnValue)*100.0)/100.0);
			costingDt.setSetValue(Math.round((totSetValue)*100.0)/100.0);
			costingDt.setHdlgValue(Math.round((totHdlgValue)*100.0)/100.0);
			costingDt.setCompValue(Math.round((totCompValue)*100.0)/100.0);
			costingDt.setLabValue(Math.round((totLabourValue)*100.0)/100.0);
			
			
			
									
			tempVal = costingDt.getMetalValue()+costingDt.getStoneValue()+costingDt.getCompValue()+costingDt.getLabValue()+costingDt.getSetValue()+costingDt.getHdlgValue();
			
			
		
			costingDt.setFob(Math.round((tempVal)*100.0)/100.0);
			
			costingDt.setFinalPrice(Math.round((costingDt.getFob()- costingDt.getDiscAmount())*100.0)/100.0);
			
			
		
			
			costingDtRepository.save(costingDt);
			
			
			
			
			
		} // ending main else
		
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		
			
			return "";
		
	
	}

	
	@Override
	public String calculateFinalPrice(String sFinalPrice) {
		
		Double stringToDouble = Double.parseDouble(sFinalPrice);
		Long roundOff  = Math.round(stringToDouble);
		Integer iFinalPrice = (int) (long) roundOff;
		String stringFinalPrice = roundOff+"";
		
		String retVal = "0"; 
		
		String temp1 = "";
		String temp2 = "";
		Integer tempInt1 = 0;
		Integer tempInt2 = 0;
		
		if(stringToDouble == 0 || stringToDouble == 0.0){
			return retVal="0";
		}
		
		// 1st if--------------->>>>>
		
		if(iFinalPrice > 300 && iFinalPrice < 700){
			/*temp1 = stringFinalPrice.substring(stringFinalPrice.length()-2);
			 tempInt1 = Integer.parseInt(temp1);
			if(tempInt1 > 18){
				
				if(tempInt1 == 19){
					temp1 = stringFinalPrice.substring(0,stringFinalPrice.length()-2);
					retVal = temp1.concat("28");
				}else{
					temp1 = stringFinalPrice.substring(0,stringFinalPrice.length()-1);
					retVal = temp1.concat("8");
				}
				
			}else{
				temp1 = stringFinalPrice.substring(0,1);
				tempInt1 = Integer.parseInt(temp1) - 1;
				retVal = tempInt1+"98";
			}*/
			
			
			String tempxVal[]=new String[stringFinalPrice.length()];
			
			int size = stringFinalPrice.length();
			
			
	        for (int i=0; i<size; i++)
	        {            
	        	tempxVal[i]= (stringFinalPrice.substring(i,i+1));
	        }
						
			
			
			String firstDigit = tempxVal[0];
			String middleDigit = tempxVal[1];
			String lastDigit =  tempxVal[2];
			
			Integer numFirtsDigit = Integer.parseInt(firstDigit);
			Integer numMiddleDigit = Integer.parseInt(middleDigit);
			Integer numLastDigit = Integer.parseInt(lastDigit);
			
			
			if(numLastDigit > 2){
				temp1 = stringFinalPrice.substring(0,stringFinalPrice.length()-1);
				retVal = temp1.concat("8");
			}else{
				if(numMiddleDigit == 0){
					numFirtsDigit = numFirtsDigit-1;
					retVal = numFirtsDigit+"98";
				}else{
					numMiddleDigit = numMiddleDigit-1;
					retVal = firstDigit.concat(numMiddleDigit.toString())+"8";
				}
			}
			
			
			
			
		} 
		
		//-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		// 2nd if--------------->>>>>
		
		if(iFinalPrice <= 300){
			
			
			
			if(stringFinalPrice.length() > 1){
				temp1 = stringFinalPrice.substring(stringFinalPrice.length()-1);
				tempInt1 = Integer.parseInt(temp1);
			}else{
				tempInt1 = Integer.parseInt(stringFinalPrice);
			}
			
			
			
			if(tempInt1 >= 4){
				if(stringFinalPrice.length() > 1){
					temp1 = stringFinalPrice.substring(0,stringFinalPrice.length()-1);
					retVal = temp1.concat("8");
				}else{
					retVal = tempInt1+"8";
				}
			}else{
				
				if(stringFinalPrice.length() > 1){
					temp1 = stringFinalPrice.substring(0,stringFinalPrice.length()-1);
					tempInt1 = Integer.parseInt(temp1) - 1;
					retVal = tempInt1+"8";
				}else{
					tempInt1 = tempInt1 - 1;
					retVal = tempInt1+"8";
				}
			}
			
		} 
		
		//-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		
		// 3rd if--------------->>>>>
		
		if(iFinalPrice >= 700 && iFinalPrice <= 1500){
			
			 if(stringFinalPrice.length() == 3){
				 temp1 = stringFinalPrice.substring(stringFinalPrice.length()-2);
				 tempInt1 = Integer.parseInt(temp1);
				 
				 if(tempInt1 > 28){
					 if(tempInt1 == 29){
						temp2 = stringFinalPrice.substring(0,stringFinalPrice.length()-2);
						retVal = temp2.concat("38");
					 	}else{
					 		temp2 = stringFinalPrice.substring(0,stringFinalPrice.length()-1);
					 		retVal = temp2.concat("8");
					 	}
				 }else{
					 	temp2 = stringFinalPrice.substring(0,1);
						tempInt2 = Integer.parseInt(temp2) - 1;
						retVal = tempInt2+"98";
				 }
				 
			 }
			
			 
			 if(stringFinalPrice.length() == 4){
				 temp1 = stringFinalPrice.substring(stringFinalPrice.length()-2);
				 tempInt1 = Integer.parseInt(temp1);
				 
				 if(tempInt1 > 28){
					 if(tempInt1 == 29){
						 temp2 = stringFinalPrice.substring(0,stringFinalPrice.length()-2);
						 retVal = temp2.concat("38");
					 }else{
						 temp2 = stringFinalPrice.substring(0,stringFinalPrice.length()-1);
					 	 retVal = temp2.concat("8");
					 }
				 }else{
					 	temp2 = stringFinalPrice.substring(0,2);
						tempInt2 = Integer.parseInt(temp2) - 1;
						retVal = tempInt2+"98";
				 }
				 
				 
			 }
			
			
			
			
		} 
		
		//-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		
		// 4th if--------------->>>>>
		
		if(iFinalPrice > 1500){
			
			if(stringFinalPrice.length() == 4){
				 temp1 = stringFinalPrice.substring(stringFinalPrice.length()-2);
				 tempInt1 = Integer.parseInt(temp1);
				 
				 if(tempInt1 > 38){
					 if(tempInt1 == 39){
						 temp2 = stringFinalPrice.substring(0,stringFinalPrice.length()-2);
						 retVal = temp2.concat("48");
					 }else{
						 temp2 = stringFinalPrice.substring(0,stringFinalPrice.length()-1);
					 	 retVal = temp2.concat("8");
					 }
				 }else{
					 temp2 = stringFinalPrice.substring(0,2);
					 tempInt2 = Integer.parseInt(temp2) - 1;
					 retVal = tempInt2+"98";
				 }
				
			}
			
			
		 
			if(stringFinalPrice.length() == 5){
				temp1 = stringFinalPrice.substring(stringFinalPrice.length()-2);
				tempInt1 = Integer.parseInt(temp1);
				
				if(tempInt1 > 38){
					if(tempInt1 == 39){
						 temp2 = stringFinalPrice.substring(0,stringFinalPrice.length()-2);
						 retVal = temp2.concat("48");
					}else{
						temp2 = stringFinalPrice.substring(0,stringFinalPrice.length()-1);
					 	retVal = temp2.concat("8");
					}
				}else{
					temp2 = stringFinalPrice.substring(0,3);
					tempInt2 = Integer.parseInt(temp2) - 1;
					retVal = tempInt2+"98";
				}
				
			}
			
			
			
		}
		
		//-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		
		System.out.println("----return-----"+retVal);		
		
		return retVal;
	}



	@Override
	public List<CostingDt> getCostingDtList(CostingMt costingMt) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<CostingDt> costingDtList = null;
		
		costingDtList = query.from(qCostingDt)
					.where(qCostingDt.costingMt.id.eq(costingMt.getId()).and(qCostingDt.deactive.eq(false)))
					.groupBy(qCostingDt.costingMt,qCostingDt.itemNo,qCostingDt.orderDt)
					.list(qCostingDt);
		
		return costingDtList;
	}

	@Override
	public List<CostingDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(
			String itemNo, OrderDt orderDt, CostingMt costingMt,Boolean deactive) {
		return costingDtRepository.findByItemNoAndOrderDtAndCostingMtAndDeactive(itemNo, orderDt, costingMt, deactive);
	}
	
	
	@Override
	public Page<CostingDt> findByItemNo(Integer limit, Integer offset, String sort,String order, String itemNo, Boolean onlyActive) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		BooleanExpression expression = qCostingDt.deactive.eq(false);

		if (onlyActive) {
			if (itemNo == null) {
				expression = qCostingDt.deactive.eq(false);
			} else {
				expression = qCostingDt.deactive.eq(false).and(qCostingDt.itemNo.like(itemNo + "%"));
			}
		} else {
			if (itemNo != null) {
				expression = qCostingDt.itemNo.like(itemNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "itemNo";
		}

		Page<CostingDt> costingDtList = (Page<CostingDt>) costingDtRepository.findAll(expression,new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC), sort));

		return costingDtList;
		
	}

	@Override
	public List<CostingDt> findByItemNoAndDeactive(String itemNo,
			Boolean deactive) {
		return costingDtRepository.findByItemNoAndDeactive(itemNo, deactive);
	}
	
	@Override
	public void lockUnlockDt(Integer CostMtId,Boolean status) {

		QCostingDt qCostingDt = QCostingDt.costingDt;
		
		new JPAUpdateClause(entityManager, qCostingDt).where(qCostingDt.costingMt.id.eq(CostMtId))
		.set(qCostingDt.rLock, status)
		.execute();
		
		
	}
	
	
	@Override
	public void resetStyle(Integer orderDtId,Integer styleId, String styleNo,String version) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		
		new JPAUpdateClause(entityManager, qCostingDt).where(qCostingDt.orderDt.id.eq(orderDtId))
		.set(qCostingDt.design.id,styleId)
		.set(qCostingDt.styleNo, styleNo)
		.set(qCostingDt.version, version)
		.execute();
		
	}
	
	
	@Override
	public void updateCostingDtDispPercent(Integer mtId, Double dispPerc) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		new JPAUpdateClause(entityManager, qCostingDt).where(qCostingDt.costingMt.id.eq(mtId).and(qCostingDt.rLock.eq(false)))
		.set(qCostingDt.dispPercentDt, dispPerc)
		.execute();
		
		
	}

	@Override
	public Page<CostingDt> searchAll(Integer limit, Integer offset,
			String sort, String order, String name, Integer mtId) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		BooleanExpression expression = qCostingDt.deactive.eq(false).and(qCostingDt.costingMt.id.eq(mtId));

		if(name !=null && name !=""){
			/*expression = qCostingDt.deactive.eq(false).and(qCostingDt.costingMt.id.eq(mtId).or(qCostingDt.design.mainStyleNo.like(name + "%"))
					.or(qCostingDt.party.name.like(name + "%")).or(qCostingDt.orderDt.orderMt.invNo.like(name + "%"))
					.or(qCostingDt.orderDt.orderMt.refNo.like(name + "%")).or(qCostingDt.bagMt.name.like(name + "%")));*/
			
			
			expression = qCostingDt.deactive.eq(false).and(qCostingDt.costingMt.id.eq(mtId)).and(qCostingDt.bagMt.name.like(name.trim()+"%")
					.or(qCostingDt.design.mainStyleNo.like(name+"%")).or(qCostingDt.party.name.like(name+"%")).or(qCostingDt.orderDt.orderMt.invNo.like(name+"%"))
					.or(qCostingDt.orderDt.orderMt.refNo.like(name+"%")));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if(sort.equalsIgnoreCase("style")){
			sort = "design";
		}

		Page<CostingDt> costDtList = (Page<CostingDt>) costingDtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return costDtList;
	}

	@Override
	public String applyMetal(CostingDt costingDt) {
	
		CostingMt costingMt =costingDt.getCostingMt();
		
		
		List<CostMetalDt> costMetalDts = costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
		if(costMetalDts !=null){
			
			for(CostMetalDt costMetalDt:costMetalDts){
				
				if(costMetalDt.getrLock().equals(false)){
					if (costMetalDt.getPurity() != null) {
						Purity purity = purityService.findOne(costMetalDt.getPurity().getId());
																	
						CostMetalRate costMetalRate = costMetalRateService.findByCostingMtAndDeactiveAndMetal(costingMt, false, purity.getMetal());
						
						
						
						if(costMetalRate!=null){
							
							if(costMetalDt.getMainMetal().equals(true)){
								costingDt.setLossPercDt(costMetalRate.getLossPerc());
								save(costingDt);
							}
							
							costMetalDt.setMetalRate(costMetalRate.getRate());
							costMetalDt.setLossPerc(costMetalRate.getLossPerc());	
							costMetalDtService.save(costMetalDt);
						}

					}

				}

			}

		}
		
		
		return "";
	}
	
	

	@Override
	public String applyStoneRate(List<CostStnDt> costStnDts) {
		// TODO Auto-generated method stub
	
		for(CostStnDt costStnDt: costStnDts){
			
			if(costStnDt.getrLock().equals(true)){
				continue;
			}
			
			
			
			
			CostMetalDt costMetalDt = costMetalDtService.findByCostingDtAndDeactiveAndPartNm(costStnDt.getCostingDt(), false, costStnDt.getPartNm());
			
			if(costMetalDt !=null){
				
				Double pointerWt =Math.round((costStnDt.getCarat()/costStnDt.getStone())*1000.0)/1000.0 ;
				
				if(costStnDt.getStoneType() != null && costStnDt.getShape() != null &&	costStnDt.getSetting() != null && costStnDt.getSettingType() != null){
					
					List<SettingCharge> settingChargeList = settingChargeService.getRates(costStnDt.getCostingMt().getParty(),pointerWt,
							false,costMetalDt.getPurity().getMetal(),costStnDt.getStoneType(),costStnDt.getShape(),costStnDt.getSetting(),costStnDt.getSettingType());
					
					SettingCharge settingCharge=null;
					
					if(settingChargeList.size()>0){
						settingCharge = settingChargeList.get(0);
					}
					
										
					if(settingCharge != null){
						
						if(settingCharge.getQualityWiseRate().equals(true)){
							
							List<SettingQualityRate>settingQualityRates=settingQualityRateService.findBySettingChargeAndDeactive(settingCharge, false);
							Boolean isAvailable=false;
							for(SettingQualityRate settingQualityRate:settingQualityRates){
								if(settingQualityRate.getQuality().equals(costStnDt.getQuality())){
									costStnDt.setSetRate(settingQualityRate.getQualityRate());
									isAvailable=true;
								}
							}
							
							if(isAvailable.equals(false)){
								costStnDt.setSetRate(settingCharge.getRate());
							}
							
							
							
							
						}else{
							costStnDt.setSetRate(settingCharge.getRate());
						}
						
						
						
					}
					
				}
			
			
			
			
			if(costStnDt.getStoneType() != null && costStnDt.getShape() != null){
				
				HandlingMaster handlingMaster = handlingMasterService.findByPartyAndMetalAndStoneTypeAndShapeAndDeactive(costStnDt.getCostingMt().getParty(),
						costMetalDt.getPurity().getMetal(),costStnDt.getStoneType(), costStnDt.getShape(), false);
				
				if(handlingMaster != null){
					if(handlingMaster.getPerCarate() > 0){
						costStnDt.setHandlingRate(handlingMaster.getPerCarate());
						costStnDt.setHdlgPerCarat(true);
						costStnDt.setHdlgPercentWise(false);
						
					
					}else{
						costStnDt.setHandlingRate(handlingMaster.getPercentage());
						costStnDt.setHdlgPerCarat(false);
						costStnDt.setHdlgPercentWise(true);
	
					}
				}
				
				
			
				
				
			}
			
				
			}
			
		
			
			
			
			
			
			if(costStnDt.getStoneType() != null && costStnDt.getShape() != null &&  costStnDt.getQuality() != null ){
			
				List<StoneRateMast> stoneRateMast=stoneRateMastService.getStoneRate(costStnDt.getStoneType().getId(),costStnDt.getShape().getId(),costStnDt.getQuality().getId(), 
						costStnDt.getSizeGroup().getId(),costStnDt.getCostingMt().getParty().getId(),costStnDt.getSieve());
				
				if(stoneRateMast.size() > 0){
					if(stoneRateMast.get(0).getPerPcRate() > 0){
						costStnDt.setStnRate(stoneRateMast.get(0).getPerPcRate());
						costStnDt.setPerPcsRateFlg(true);
						
					}else{
						costStnDt.setStnRate(stoneRateMast.get(0).getStoneRate());
						costStnDt.setPerPcsRateFlg(false);
						
					}
				}
			
			}
			
			costStnDtService.save(costStnDt);
			
		}
		
		
		
		return "";
	}
	
	@Override
	public String applyCompRate(List<CostCompDt> costCompDts) {

		for(CostCompDt costCompDt:costCompDts){
			
			if(costCompDt.getrLock().equals(true)){
				continue;
			}
			
			if(costCompDt.getComponent() != null && costCompDt.getPurity() != null){
			
				FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(costCompDt.getComponent(), costCompDt.getCostingMt().getParty(),
						costCompDt.getPurity(), false);
				
				if(findingRateMast != null){
					if(findingRateMast.getPerPcRate().equals(true)){
						costCompDt.setCompRate(findingRateMast.getRate());
						costCompDt.setPerPcRate(true);
						costCompDt.setPerGramRate(false);
					}else{
						costCompDt.setCompRate(findingRateMast.getRate());
						costCompDt.setPerPcRate(false);
						costCompDt.setPerGramRate(true);
					}
				}
				
			}
			
			costCompDtService.save(costCompDt);
			
		}
		
	
		return "";
	}

	@Override
	public String applyLabRate(CostingDt costingDt, Principal principal) {
		// TODO Auto-generated method stub
	
		QCostMetalDt qCostMetalDt = QCostMetalDt.costMetalDt;
		JPAQuery query=new JPAQuery(entityManager);
		
		List<Tuple> costMetalList=null;
		
		costMetalList = query.from(qCostMetalDt).
				where(qCostMetalDt.deactive.eq(false).and(qCostMetalDt.costingDt.id.eq(costingDt.getId())))
				.groupBy(qCostMetalDt.purity.metal).list(qCostMetalDt.purity.metal.name,qCostMetalDt.metalWeight.sum(),qCostMetalDt.metalPcs.sum());
		
		
		
		for(Tuple tuple : costMetalList){
		
			
					
			Metal metal =metalService.findByName(tuple.get(qCostMetalDt.purity.metal.name));
			
			List<LabourCharge> labourCharges =labourChargeService.getRates(costingDt.getCostingMt().getParty(), costingDt.getDesign().getCategory(),
					tuple.get(qCostMetalDt.metalWeight.sum()),false, metal);
			
			List<CostLabDt> costLabDts = costLabDtService.findByCostingDtAndMetalAndDeactive(costingDt, metal, false);
			
			Boolean isAvailable=false;
			for(LabourCharge labourCharge :labourCharges){
				isAvailable=false;
				for(CostLabDt costLabDt : costLabDts){
					if(costLabDt.getLabourType().equals(labourCharge.getLabourType())){
						
						isAvailable=true;
						if(costLabDt.getrLock().equals(false)){
							costLabDt.setLabourRate(labourCharge.getRate());	
						}
						
					}
										
				}
				if(!isAvailable){
					
					CostLabDt costLabDt = new CostLabDt();
					
					costLabDt.setCostingMt(costingDt.getCostingMt());
					costLabDt.setCostingDt(costingDt);
					costLabDt.setLabourType(labourCharge.getLabourType());
					costLabDt.setLabourRate(labourCharge.getRate());
					costLabDt.setMetal(metal);
					
					if(labourCharge.getPerPcsRate() == true){
						costLabDt.setPcsWise(true);
					}else if(labourCharge.getPerGramRate() == true){
						costLabDt.setGramWise(true);
					}else if(labourCharge.getPercentage() == true){
						costLabDt.setPercentWise(true);
					}
					else if(labourCharge.getPerCaratRate() == true){
						costLabDt.setPerCaratRate(true);
					}
				
					costLabDt.setCreatedDate(new java.util.Date());
					costLabDt.setCreatedBy(principal.getName());
					
					costLabDtService.save(costLabDt);
					
					
				}
				
				
			}
			
			
			
			
			
		}
		
			
	
		return "";
	}

	@Override
	public String costingDtSave(Integer costDtId, Double grossWt, Double other,
			Integer partyId, String itemNo, Double dispPercentDt,
			Double lossPercDt, Principal principal, String costingType) {
		// TODO Auto-generated method stub

		DecimalFormat df = new DecimalFormat("#.###");
		String retVal = "-1";

		try {
			Party costDtParty = partyService.findOne(partyId);
			CostingDt costingDt = findOne(costDtId);
			
			
			if(costingType != null && costingType.equalsIgnoreCase("merge")){
				
				Double vGrossWtDiff=costingDt.getGrossWt()-grossWt;
				
				
				
				
				costingDt.setGrossWt(grossWt);
				costingDt.setNetWt(Math.round((costingDt.getNetWt()-vGrossWtDiff)*1000.0)/1000.0);
				
				costingDt.setModiBy(principal.getName());
				costingDt.setModiDate(new java.util.Date());
				costingDt.setParty(costDtParty);
				save(costingDt);
				
				
				
				
				List<CostMetalDt>costMetalDts =costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
				for(CostMetalDt costMetalDt :costMetalDts){
					if(costMetalDt.getMainMetal().equals(true)){
						costMetalDt.setMetalWeight(Math.round((costMetalDt.getMetalWeight()-vGrossWtDiff)*1000.0)/1000.0);
					}
									
					costMetalDt.setLossPerc(lossPercDt);
					
					
					costMetalDtService.save(costMetalDt);
					
				}
				
		
				
			}else{
			
			BagMt bagMt = bagMtService.findOne(costingDt.getBagMt().getId());
			
			if(itemNo.equalsIgnoreCase("NO TAG")){
				
			}else{
			BagMt bagMtValidate = bagMtService.findByItemNoAndDeactive(itemNo, false);
			
					
			if(bagMtValidate != null){
				if(bagMtValidate.getId().equals(bagMt.getId())){
				}else{
					return retVal = "-2";
				}
			}
			}
			
			
			
		
			/*List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
			Double totStnCarat = 0.0;
			for(CostStnDt costStnDt:costStnDts){
				totStnCarat += costStnDt.getCarat(); 
			}
		
			Double temVal = totStnCarat/5;
			Double tempNetWt = grossWt-temVal;
			
			
			List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
			Double totCompMetalWt = 0.0;
			for(CostCompDt costCompDt:costCompDts){
				totCompMetalWt += costCompDt.getMetalWt();
			}
			
			Double actualNetWt = tempNetWt - totCompMetalWt;*/
			
			Double vGrossWtDiff=costingDt.getGrossWt()-grossWt;
			
			
			costingDt.setGrossWt(grossWt);
			costingDt.setNetWt(costingDt.getNetWt()-vGrossWtDiff);
			costingDt.setOther(other);
			costingDt.setLossPercDt(lossPercDt);
			costingDt.setModiBy(principal.getName());
			costingDt.setModiDate(new java.util.Date());
			costingDt.setParty(costDtParty);
			costingDt.setItemNo(itemNo);
			costingDt.setDispPercentDt(dispPercentDt);
			save(costingDt);
			
			
			bagMt.setItemNo(itemNo);
			bagMtService.save(bagMt);
			
			//---update itemNo in costStnDt and costCompDt and costLabDt
			
			costStnDtService.updateItemNo(costDtId, itemNo);
			costCompDtService.updateItemNo(costDtId, itemNo);
			costLabDtService.updateItemNo(costDtId, itemNo);
			
			List<CostMetalDt>costMetalDts =costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
			for(CostMetalDt costMetalDt :costMetalDts){
				if(costMetalDt.getMainMetal().equals(true)){
					costMetalDt.setMetalWeight(costMetalDt.getMetalWeight()-vGrossWtDiff);
				}
								
				costMetalDt.setLossPerc(lossPercDt);
				
				
				costMetalDtService.save(costMetalDt);
				
			}
			
			
			
			//applyRate(costingDt, principal);
			updateFob(costingDt);
		
			}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				return retVal;
			}

	@Override
	public Integer getMaxSetNo(Integer mtId) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = null;

		List<Integer> maxSetNo = query
			.from(qCostingDt)
			.where(qCostingDt.deactive.eq(false).and(qCostingDt.costingMt.id.eq(mtId)))
			.list(qCostingDt.setNo.max());
			

		for (Integer setNo : maxSetNo) {
			retVal = setNo;
		}
		
		if(retVal == null || retVal==0){
			retVal =1;
		}
		

		return retVal;
	}
	
	@Override
	public List<Tuple> getSetNoList(Integer costMtId) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Tuple> setNoList = null;
		
		setNoList = query.from(qCostingDt).where(qCostingDt.deactive.eq(false).and(qCostingDt.costingMt.id.eq(costMtId))).
				groupBy(qCostingDt.setNo).list(qCostingDt.costingMt.id,qCostingDt.id,qCostingDt.setNo,qCostingDt.rLock);
		return setNoList;
	}

	@Override
	public int lockDtInvoice(Integer costMtId, Integer setNo, Principal principal,Boolean vRLock) {
		
		Query query = entityManager.createNativeQuery("update costdt  set rlock ="+vRLock+" where setNo ="+setNo+" and mtid ="+costMtId+" and deactive=0");
		int retval = query.executeUpdate();
		
		query = entityManager.createNativeQuery("update costmetaldt a, costdt b set a.rlock = b.rlock where b.dtid = a.dtid and b.setNo ="+setNo+" and a.deactive =0 and b.deactive=0 and b.mtid="+costMtId);
		retval = query.executeUpdate();
		
		query = entityManager.createNativeQuery("update coststndt a, costdt b set a.rlock = b.rlock where b.dtid = a.dtid and b.setNo ="+setNo+" and a.deactive =0 and b.deactive=0 and b.mtid="+costMtId);
		retval = query.executeUpdate();
		
		query = entityManager.createNativeQuery("update costcompdt a, costdt b set a.rlock = b.rlock where b.dtid = a.dtid and b.setNo ="+setNo+" and a.deactive =0 and b.deactive=0 and b.mtid="+costMtId);
		retval = query.executeUpdate();
			
		 query = entityManager.createNativeQuery("update costdtitem  set rlock ="+vRLock+" where setNo ="+setNo+" and mtid ="+costMtId+" and deactive=0");
		 retval = query.executeUpdate();
		
		query = entityManager.createNativeQuery("update costmetaldtitem a, costdtitem b set a.rlock = b.rlock where b.dtitemid = a.dtitemid and b.setNo ="+setNo+" and a.deactive =0 and b.deactive=0 and b.mtid="+costMtId);
		retval = query.executeUpdate();
		
		query = entityManager.createNativeQuery("update coststndtitem a, costdtitem b set a.rlock = b.rlock where b.dtitemid = a.dtitemid and b.setNo ="+setNo+" and a.deactive =0 and b.deactive=0 and b.mtid="+costMtId);
		retval = query.executeUpdate();
		
		query = entityManager.createNativeQuery("update costcompdtitem a, costdtitem b set a.rlock = b.rlock where b.dtitemid = a.dtitemid and b.setNo ="+setNo+" and a.deactive =0 and b.deactive=0 and b.mtid="+costMtId);
		retval = query.executeUpdate();
		
		query = entityManager.createNativeQuery("update costlabdtitem a, costdtitem b set a.rlock = b.rlock where b.dtitemid = a.dtitemid and b.setNo ="+setNo+" and a.deactive =0 and b.deactive=0 and b.mtid="+costMtId);
		retval = query.executeUpdate();
		
		
		return retval;
	}

	@Override
	public List<CostingDt> findByOrderDtAndCostingMtAndDeactive(
			OrderDt orderDt, CostingMt costingMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return costingDtRepository.findByOrderDtAndCostingMtAndDeactive(orderDt, costingMt, deactive);
	}

	@Override
	public Integer getMaxSetNoByOrder(Integer mtId, Integer sordDtId) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = null;

		List<Integer> maxSetNo = query
			.from(qCostingDt)
			.where(qCostingDt.deactive.eq(false).and(qCostingDt.costingMt.id.eq(mtId)).and(qCostingDt.orderDt.id.eq(sordDtId)))
			.list(qCostingDt.setNo.max());
			

		for (Integer setNo : maxSetNo) {
			retVal = setNo;
		}
		
		if(retVal == null || retVal==0){
			retVal =1;
		}
		

		return retVal;
	}

	@Override
	public String deleteCostingDt(Integer dtId) {
		// TODO Auto-generated method stub
			String retVal = "1";
		
		CostingDt costingDt = findOne(dtId);
		
		if(costingDt.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
		
			
			
			List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
			for(CostStnDt costStnDt:costStnDts){
				costStnDtService.delete(costStnDt.getId());
			}
			
			List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
			for(CostCompDt costCompDt:costCompDts){
				costCompDtService.delete(costCompDt.getId());
			}
			
			List<CostLabDt> costLabDts = costLabDtService.findByCostingDtAndDeactive(costingDt, false);
			for(CostLabDt costLabDt:costLabDts){
				costLabDtService.delete(costLabDt.getId());
			}
			
			List<CostMetalDt> costMetalDts= costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
			for (CostMetalDt costMetalDt : costMetalDts) {
				costMetalDtService.delete(costMetalDt.getId());
			}
			
			BagMt bagMt = costingDt.getBagMt();
			bagMt.setCostingFlg(false);
			bagMt.setCostingMtId(null);
			bagMtService.save(bagMt);
			
			delete(dtId);
			
			 retVal = "-3";
			
		}
		
		return retVal;
	}

	@Override
	public String checkDiaTolerance(String bagNm) {
		// TODO Auto-generated method stub
		QTranDt qTranDt = QTranDt.tranDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		QDesignStone qDesignStn = QDesignStone.designStone;
		JPAQuery queryStn = new JPAQuery(entityManager);
		
		
		BagMt bagMt =bagMtService.findByName(bagNm);
		
		
		List<Tuple> trandtList =  query.from(qTranDt).
				where(qTranDt.currStk.eq(true).and(qTranDt.bagMt.id.eq(bagMt.getId())))
				.groupBy(qTranDt.shape)
				.list(qTranDt.shape,
						qTranDt.stone.sum(),qTranDt.carat.sum());
		
		
		List<Tuple> designStnList =  queryStn.from(qDesignStn).
				where(qDesignStn.deactive.eq(false).and(qDesignStn.design.id.eq(bagMt.getOrderDt().getDesign().getId())))
				.groupBy(qDesignStn.shape)
				.list(qDesignStn.shape,
						qDesignStn.stone.sum(),qDesignStn.carat.sum());
		
		
		
	
		
		String msg="";
		for (Tuple tupletrandt : trandtList) {
					
			Shape shape = tupletrandt.get(qTranDt.shape);
		
				
				for (Tuple tupleStn : designStnList) {
					
					if(shape.equals(tupleStn.get(qDesignStn.shape))){
						
						if(shape.getUpperTolerance()>0) {
							Double designCts=tupleStn.get(qDesignStn.carat.sum());
						
						Double upVal= (designCts*shape.getUpperTolerance())/100;
						upVal= upVal+designCts;
						
						if(tupletrandt.get(qTranDt.carat.sum())>upVal) {
							msg=msg+" "+shape.getName()+" Is Upper Tolerance ";
						}
					}
					
						if(shape.getLowerTolerance()>0) {
							Double designCts=tupleStn.get(qDesignStn.carat.sum());
							
							Double upVal= (designCts*shape.getLowerTolerance())/100;
							upVal= designCts-upVal;
							
							if(tupletrandt.get(qTranDt.carat.sum())<upVal) {
								msg=msg+" "+shape.getName()+" Is Lower Tolerance ";
							}
						}
					
					
					
				}
				
			}
	
			
			
		}

		
		return msg;
	}

	@Override
	public String costDtListing(Integer limit, Integer offset, String sort, String order, String search,
			String pInvNo,Boolean netWtWithComp) {
	
		int srno =offset;

		StringBuilder sb = new StringBuilder();
		Page<CostingDt> costDts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		
		CostingMt costingMt = costingMtService.findByInvNoAndDeactive(pInvNo.trim(), false);
		costDts= searchAll(limit, offset, sort, order, search, costingMt.getId());
		
		
		
		sb.append("{\"total\":").append(costDts.getTotalElements())
		.append(",\"rows\": [");
		
		
		
			for(CostingDt costingDt:costDts){
				
	
				
				Double vCostMetalWt =0.0;	
				List<CostMetalDt> costMetalDts =  costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
				for (CostMetalDt costMetalDt : costMetalDts) {
					vCostMetalWt += costMetalDt.getMetalWeight();
				}
				
				Double vCompMetalWt=0.0;
				List<CostCompDt> costCompDts =  costCompDtService.findByCostingDtAndDeactive(costingDt, false);
				for (CostCompDt costCompDt : costCompDts) {
					vCompMetalWt += costCompDt.getMetalWt();
				}
				
				Double vTotMetalWt=0.0;
				
				if(netWtWithComp.equals(true)){
					
					vTotMetalWt =  Math.round((vCostMetalWt+vCompMetalWt)*1000.0)/1000.0;
				}else {
					vTotMetalWt =  Math.round((vCostMetalWt)*1000.0)/1000.0;
				}
				
				
				
				srno +=1;
				
			sb.append("{\"id\":\"")
				.append(costingDt.getId())
				.append("\",\"srNo\":\"")
				.append(srno)
				.append("\",\"itemNo\":\"")
				.append((costingDt.getItemNo() != null ? costingDt.getItemNo() : ""))
				.append("\",\"party\":\"")
				.append((costingDt.getParty() != null ? costingDt.getParty().getPartyCode() : ""))
				.append("\",\"ordNo\":\"")
				.append((costingDt.getOrderDt().getOrderMt().getInvNo() != null ? costingDt.getOrderDt().getOrderMt().getInvNo() : ""))
				.append("\",\"ordRefNo\":\"")
				.append((costingDt.getOrderDt().getOrderMt().getRefNo() != null ? costingDt.getOrderDt().getOrderMt().getRefNo() : ""))
				.append("\",\"style\":\"")
				.append((costingDt.getDesign() != null ? costingDt.getDesign().getMainStyleNo() : ""))
				.append("\",\"bag\":\"")
				.append((costingDt.getBagMt() != null ? costingDt.getBagMt().getName() : ""))
				.append("\",\"purity\":\"")
				.append((costingDt.getPurity() != null ? costingDt.getPurity().getName() : ""))
				.append("\",\"lossPercDt\":\"")
				.append((costingDt.getLossPercDt() != null ? costingDt.getLossPercDt() : ""))
				.append("\",\"color\":\"")
				.append((costingDt.getColor() != null ? costingDt.getColor().getName() : ""))
				.append("\",\"pcs\":\"")
				.append((costingDt.getPcs() != null ? costingDt.getPcs() : ""))
				.append("\",\"grossWt\":\"")
				.append((costingDt.getGrossWt() != null ? costingDt.getGrossWt() : ""))
				.append("\",\"netWt\":\"")
				.append((costingDt.getNetWt() != null ? costingDt.getNetWt() : ""))
				.append("\",\"setNo\":\"")
				.append((costingDt.getSetNo() != null ? costingDt.getSetNo() : ""))
				.append("\",\"metalRate\":\"")
				.append((costingDt.getMetalRate() != null ? costingDt.getMetalRate() : ""))
				.append("\",\"metalValue\":\"")
				.append((costingDt.getMetalValue() != null ? costingDt.getMetalValue() : ""))
				.append("\",\"stoneValue\":\"")
				.append((costingDt.getStoneValue() != null ? costingDt.getStoneValue() : ""))
				.append("\",\"compValue\":\"")
				.append((costingDt.getCompValue() != null ? costingDt.getCompValue() : ""))
				.append("\",\"labourValue\":\"")
				.append((costingDt.getLabValue() != null ? costingDt.getLabValue() : ""))
				.append("\",\"setValue\":\"")
				.append((costingDt.getSetValue() != null ? costingDt.getSetValue() : ""))
				.append("\",\"handlingCost\":\"")
				.append((costingDt.getHdlgValue() != null ? costingDt.getHdlgValue() : ""))
				.append("\",\"fob\":\"")
				.append((costingDt.getFob() != null ? costingDt.getFob() : ""))
				.append("\",\"other\":\"")
				.append((costingDt.getOther() != null ? costingDt.getOther() : ""))
				.append("\",\"dispPercentDt\":\"")
				.append((costingDt.getDispPercentDt() != null ? costingDt.getDispPercentDt() : ""))
				.append("\",\"discAmount\":\"")
				.append((costingDt.getDiscAmount() != null ? costingDt.getDiscAmount() : ""))
				.append("\",\"finalPrice\":\"")
				.append((costingDt.getFinalPrice() != null ? costingDt.getFinalPrice() : ""))
				.append("\",\"image\":\"")
				.append(costingDt.getDesign().getDefaultImage() != null ? costingDt.getDesign().getDefaultImage() :"blank.png")
				.append("\",\"totMetalWt\":\"")
				.append((vTotMetalWt))
				.append("\",\"rLock\":\"")
				.append((costingDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCostDtLockUnLock(")
				.append(costingDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostingDt(")
				.append(costingDt.getId())
				.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostingDt(event,")
				.append(costingDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(costingDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@Override
	public String updateNetWt(CostingDt costingDt, Boolean netWtWithComp) {
		 Double totNetWt=0.0;	
		 List<CostMetalDt> costMetalDts=costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
		 if(costMetalDts.size()>0){
			 for(CostMetalDt costMetalDt :costMetalDts){
				 totNetWt  += costMetalDt.getMetalWeight();	 
			 }
			 
		 }
		 
				
			List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
			Double totStnCarat = 0.0;
			for(CostStnDt costStnDt:costStnDts){
				totStnCarat += costStnDt.getCarat();
			}
			
			Double temVal = totStnCarat/5;
			Double totGrossWt = totNetWt+temVal;
			
			List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
			Double totCompMetalWt = 0.0;
			for(CostCompDt costCompDt:costCompDts){
				totCompMetalWt += costCompDt.getMetalWt();
			}
			
			totGrossWt += totCompMetalWt;
			
			
			Double grossWtdiff = Math.round((costingDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
			
			CostMetalDt costMetalDt = costMetalDtService.findByCostingDtAndDeactiveAndMainMetal(costingDt, false, true);
			costMetalDt.setMetalWeight(Math.round((costMetalDt.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
			costMetalDtService.save(costMetalDt);	
			
			
			if(netWtWithComp.equals(true)){
				
				totNetWt +=  totCompMetalWt+grossWtdiff;
			}else {
				totNetWt += grossWtdiff;
			}
			
		
			costingDt.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
			
			save(costingDt);
			
		return null;
	}
	
	
	

}

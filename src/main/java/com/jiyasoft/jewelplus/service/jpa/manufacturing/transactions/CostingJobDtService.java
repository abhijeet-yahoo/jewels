package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.text.DecimalFormat;
import java.util.List;








import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingJobDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostingJobDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
//import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobDtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class CostingJobDtService implements ICostingJobDtService{

	@Autowired
	private ICostingJobDtRepository costingJobDtRepository;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private ICostJobStnDtService costJobStnDtService;
	
	@Autowired
	private ICostJobCompDtService costJobCompDtService;
	
	@Autowired
	private ICostJobLabDtService costJobLabDtService;
	
	/*@Autowired
	private ISettingChargeService settingChargeService;*/
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CostingJobDt> findAll() {
		return costingJobDtRepository.findAll();

	}

	@Override
	public void save(CostingJobDt costingJobDt) {
		costingJobDtRepository.save(costingJobDt);
		
	}

	@Override
	public void delete(int id) {
		CostingJobDt costingJobDt = costingJobDtRepository.findOne(id);
		costingJobDt.setDeactive(true);
		costingJobDt.setDeactiveDt(new java.util.Date());
		costingJobDtRepository.save(costingJobDt);		
	}

	@Override
	public Long count() {
		return costingJobDtRepository.count();

	}

	@Override
	public CostingJobDt findOne(int id) {
		return costingJobDtRepository.findOne(id);

	}

	@Override
	public CostingJobDt findByUniqueId(Long uniqueId) {
		return costingJobDtRepository.findByUniqueId(uniqueId);

	}

	@Override
	public List<CostingJobDt> findByCostingJobMtAndDeactive(
			CostingJobMt costingJobMt, Boolean deactive) {
		return costingJobDtRepository.findByCostingJobMtAndDeactive(costingJobMt, deactive);
	}

	@Override
	public String applyRate(CostingJobDt costingJobDt, Party party) {
        
		String retVal = "";
		
		Boolean status = false;
		
		Double vStoneValue = 0.0;
		Double vSetValue = 0.0;
		Double vHdlgValue = 0.0;
		Double vCompValue = 0.0;
		Double vLabValue = 0.0;
		Double vNetWt = 0.0;
		
		
		if(costingJobDt.getrLock() == true){	
		}else{
		
		status = true;	
			
		DecimalFormat df = new DecimalFormat("#.##");
		
		
		Double  tempCal = 0.0;
		Double  tempCal2 = 0.0;
		
		String metalNm = costingJobDt.getPurity().getMetal().getName();
				
		if(metalNm.equalsIgnoreCase("Gold")){
			
			tempCal =  costingJobDt.getCostingJobMt().getMetalRate()/31.1035;
			tempCal2 = (tempCal/24) * (costingJobDt.getPurity().getvPurity() != null ? costingJobDt.getPurity().getvPurity() : 0.0);
			costingJobDt.setMetalRate(Double.parseDouble(df.format(tempCal2)));
			
			costingJobDt.setMetalValue(Double.parseDouble(df.format(costingJobDt.getNetWt() * Double.parseDouble(df.format(tempCal2)))));
		}
		
		if(metalNm.equalsIgnoreCase("Silver")){
			tempCal =  costingJobDt.getCostingJobMt().getSilverRate()/31.1035;
			tempCal2 = (tempCal/(costingJobDt.getPurityConv() != null ? costingJobDt.getPurityConv() : 0.0));
			costingJobDt.setMetalRate(Double.parseDouble(df.format(tempCal2)));
			costingJobDt.setMetalValue(Double.parseDouble(df.format(costingJobDt.getNetWt() * Double.parseDouble(df.format(tempCal2)))));
		}
		
		
		
		
		costingJobDtRepository.save(costingJobDt);
		
		
		//--costJobStnDt//
		
		Double totStnValue = 0.0;
		Double totSetValue = 0.0;
		Double totHandValue = 0.0;
		
		List<CostJobStnDt> costJobStnDts = costJobStnDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
		
		for(CostJobStnDt costJobStnDt : costJobStnDts){
			
			if(costJobStnDt.getrLock() == true){
				continue;
			}
			
			costJobStnDt.setStoneValue(Double.parseDouble(df.format(costJobStnDt.getCarat() * costJobStnDt.getStnRate())));
			costJobStnDt.setSetValue(Double.parseDouble(df.format(costJobStnDt.getSetRate() * costJobStnDt.getStone())));
			costJobStnDt.setHandlingValue(Double.parseDouble(df.format(((costJobStnDt.getCarat() * costJobStnDt.getStnRate()) * costJobStnDt.getHandlingRate())/100  )));
			
			totStnValue += costJobStnDt.getStoneValue();
			totSetValue += costJobStnDt.getSetValue();
			totHandValue += costJobStnDt.getHandlingValue();
			
			costJobStnDtService.save(costJobStnDt);
			
		} // end //--costJobStnDt//
		
		
		//--//--costJobCompDt//
		
		List<CostJobCompDt> costJobCompDts = costJobCompDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
		
		Double totCompValue = 0.0;
		tempCal = 0.0;
		tempCal2 = 0.0;
		String vmetalNm = "";
		
		for(CostJobCompDt costJobCompDt : costJobCompDts){
			
			if(costJobCompDt.getrLock() == true){
				continue;
			}
			
			
			
			 vmetalNm = costJobCompDt.getPurity().getMetal().getName();
			
			
			if(vmetalNm.equalsIgnoreCase("Gold")){
				tempCal =  costingJobDt.getCostingJobMt().getMetalRate()/31.1035;
				tempCal2 = (tempCal/24) * (costJobCompDt.getPurity().getvPurity() != null ? costJobCompDt.getPurity().getvPurity() : 0.0);
				
				if(costJobCompDt.getCompRate() > 0 ){
					costJobCompDt.setCompValue(Double.parseDouble(df.format(costJobCompDt.getMetalWt() * costJobCompDt.getCompRate())));
				}else{
					costJobCompDt.setCompRate(Double.parseDouble(df.format(tempCal2)));
					costJobCompDt.setCompValue(Double.parseDouble(df.format(costJobCompDt.getMetalWt() * tempCal2)));
				}
				
				
			}
			
			if(vmetalNm.equalsIgnoreCase("Silver")){
				tempCal =  costingJobDt.getCostingJobMt().getSilverRate()/31.1035;
				tempCal2 = (tempCal/(costJobCompDt.getPurityConv() != null ? costJobCompDt.getPurityConv() : 0.0));
				
				if(costJobCompDt.getCompRate() > 0 ){
					costJobCompDt.setCompValue(Double.parseDouble(df.format(costJobCompDt.getMetalWt() * costJobCompDt.getCompRate())));
				}else{
					costJobCompDt.setCompRate(Double.parseDouble(df.format(tempCal2)));
					costJobCompDt.setCompValue(Double.parseDouble(df.format(costJobCompDt.getMetalWt() * tempCal2)));
				}
				
			}
			
			totCompValue += costJobCompDt.getCompValue();
			costJobCompDtService.save(costJobCompDt);
		
		} //end //--costJobCompDt//
		
		
		
		//--costJobLabDt//
		
		List<CostJobLabDt> costJobLabDts = costJobLabDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
		
		Double totLabourValue = 0.0;

		for(CostJobLabDt costJobLabDt : costJobLabDts){

			if(costJobLabDt.getrLock() == true){
				continue;
			}
				
			if(costJobLabDt.getPcsWise() == true){
				costJobLabDt.setLabourValue(Double.parseDouble(df.format(costJobLabDt.getLabourRate() * costingJobDt.getPcs())));
			}else if(costJobLabDt.getGramWise() == true){
				costJobLabDt.setLabourValue(Double.parseDouble(df.format(costJobLabDt.getLabourRate() * costingJobDt.getNetWt())));
			}else if(costJobLabDt.getPercentWise() == true){
				costJobLabDt.setLabourValue(Double.parseDouble(df.format((costingJobDt.getMetalValue() * costJobLabDt.getLabourRate())/100 )));
			}
			
			totLabourValue += costJobLabDt.getLabourValue();
			
			costJobLabDtService.save(costJobLabDt);

		}  //end //--costJobLabDt//


		
		
		//-costingJobDt--edit and save//
		
		costingJobDt.setStoneValue(Double.parseDouble(df.format(totStnValue)));
		costingJobDt.setSetValue(Double.parseDouble(df.format(totSetValue)));
		costingJobDt.setHdlgValue(Double.parseDouble(df.format(totHandValue)));
		costingJobDt.setCompValue(Double.parseDouble(df.format(totCompValue)));
		costingJobDt.setLabValue(Double.parseDouble(df.format(totLabourValue)));
		
		
		
		//List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
		Double totStnCarat = 0.0;
		for(CostJobStnDt costJobStnDt:costJobStnDts){
			totStnCarat += costJobStnDt.getCarat(); 
		}
		Double temVal = totStnCarat/5;
		Double tempNetWt = costingJobDt.getGrossWt()-temVal;
		
		//List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
		Double totCompMetalWt = 0.0;
		for(CostJobCompDt costJobCompDt:costJobCompDts){
			totCompMetalWt += costJobCompDt.getMetalWt();
		}
		
		Double actualNetWt = tempNetWt - totCompMetalWt;
		costingJobDt.setNetWt(Double.parseDouble(df.format(actualNetWt)));
	
		costingJobDtRepository.save(costingJobDt);
		
		
		 vStoneValue = Double.parseDouble(df.format(totStnValue));
		 vSetValue	 = Double.parseDouble(df.format(totSetValue));
		 vHdlgValue  = Double.parseDouble(df.format(totHandValue));
		 vCompValue  = Double.parseDouble(df.format(totCompValue));
		 vLabValue 	 = Double.parseDouble(df.format(totLabourValue));
		 vNetWt	 	 = Double.parseDouble(df.format(actualNetWt));
			
		
		} // ending main else
		
		
		if(status){
			retVal = vStoneValue+"_"+vSetValue+"_"+vHdlgValue+"_"+vCompValue+"_"+vLabValue+"_"+vNetWt;
		}else{
			retVal = "#";
		}
		
		return retVal;
	}

	@Override
	public String updateFob(CostingJobDt costingJobDt) {
		String retVal = "";
		
		Boolean status = false;
		Double vteFob = 0.0;
		Double vFinalPrice = 0.0;
		
		Double tempVal = 0.0;
		Double vFob = 0.0;
		Double returedFinalPrice;
		String tempFinalPrice;
		String retFinalPrice;
		
		if(costingJobDt.getrLock() == true){	
		}else{
			
			status = true;
			DecimalFormat df = new DecimalFormat("#.##");
			
			tempVal = costingJobDt.getMetalValue()+costingJobDt.getStoneValue()+costingJobDt.getCompValue()+costingJobDt.getLabValue()+costingJobDt.getSetValue()+costingJobDt.getHdlgValue();
			vFob = (costingJobDt.getCostingJobMt().getAddPercent()/100)*tempVal;
			vFob = vFob + tempVal;
			costingJobDt.setFob(Double.parseDouble(df.format(vFob)));
			tempFinalPrice = df.format(costingJobDt.getCostingJobMt().getDispPercent() == 0 ? vFob : vFob * costingJobDt.getCostingJobMt().getDispPercent());
			/*retFinalPrice = calculateFinalPrice(tempFinalPrice);*/
			returedFinalPrice = Double.parseDouble(tempFinalPrice);
			costingJobDt.setFinalPrice(Double.parseDouble(df.format(returedFinalPrice+costingJobDt.getOther())));
			
			costingJobDtRepository.save(costingJobDt);
			
			vteFob = costingJobDt.getFob();
			vFinalPrice = costingJobDt.getFinalPrice();
			
		} // ending main else
		
		
		if(status){
			retVal = vteFob+"_"+vFinalPrice;
		}else{
			retVal = "#";
		}
		
		return retVal;
	}

	@Override
	public String calculateFinalPrice(String finalPrice) {
		Double stringToDouble = Double.parseDouble(finalPrice);
		Long roundOff  = Math.round(stringToDouble);
		Integer iFinalPrice = (int) (long) roundOff;
		String stringFinalPrice = roundOff+"";
		
		String retVal = ""; 
		
		String temp1 = "";
		String temp2 = "";
		Integer tempInt1 = 0;
		Integer tempInt2 = 0;
		
		if(stringToDouble == 0 || stringToDouble == 0.0){
			return retVal="0";
		}
		
		// 1st if--------------->>>>>
		
		if(iFinalPrice > 300 && iFinalPrice < 700){
			temp1 = stringFinalPrice.substring(stringFinalPrice.length()-2);
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
	public List<CostingJobDt> findByItemNoAndOrderDtAndCostingJobMtAndDeactive(
			String itemNo, OrderDt orderDt, CostingJobMt costingJobMt,
			Boolean deactive) {
		return costingJobDtRepository.findByItemNoAndOrderDtAndCostingJobMtAndDeactive(itemNo, orderDt, costingJobMt, deactive);
	}

	@Override
	public List<CostingJobDt> getCostingJobDtList(CostingJobMt costingJobMt) {
		
		QCostingJobDt qCostingJobDt=QCostingJobDt.costingJobDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<CostingJobDt> costingJobDtList = null;
		
		costingJobDtList = query.from(qCostingJobDt)
					.where(qCostingJobDt.costingJobMt.id.eq(costingJobMt.getId()).and(qCostingJobDt.deactive.eq(false)))
					.groupBy(qCostingJobDt.costingJobMt,qCostingJobDt.itemNo,qCostingJobDt.orderDt)
					.list(qCostingJobDt);
		
		return costingJobDtList;
	}

	@Override
	public Page<CostingJobDt> findByItemNo(Integer limit, Integer offset,
			String sort, String order, String itemNo, Boolean onlyActive) {
		
		QCostingJobDt qCostingJobDt = QCostingJobDt.costingJobDt;
		BooleanExpression expression = qCostingJobDt.deactive.eq(false);

		if (onlyActive) {
			if (itemNo == null) {
				expression = qCostingJobDt.deactive.eq(false);
			} else {
				expression = qCostingJobDt.deactive.eq(false).and(qCostingJobDt.itemNo.like(itemNo + "%"));
			}
		} else {
			if (itemNo != null) {
				expression = qCostingJobDt.itemNo.like(itemNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "itemNo";
		}

		Page<CostingJobDt> costingJobDtList = (Page<CostingJobDt>) costingJobDtRepository.findAll(expression,new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC), sort));

		return costingJobDtList;
	}

	
	
	
	
	
}
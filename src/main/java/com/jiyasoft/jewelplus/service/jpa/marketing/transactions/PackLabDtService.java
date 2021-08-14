package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IPackLabDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackStnDtService;

@Service
@Repository
@Transactional
public class PackLabDtService implements IPackLabDtService {
	
	@Autowired
	private IPackLabDtRepository packLabDtRepository;
	
	@Autowired
	private IPackDtService packDtService;
	
	@Autowired
	private IPackMtService packMtService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IPackStnDtService packStnDtService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private IPackMetalDtService packMetalDtService;
	
	@Autowired
	private IStoneChartService stoneChartService;

	@Override
	public void save(PackLabDt packLabDt) {
		// TODO Auto-generated method stub
		packLabDtRepository.save(packLabDt);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		/*
		 * PackLabDt packLabDt = packLabDtRepository.findOne(id);
		 * packLabDt.setDeactive(true); packLabDt.setDeactiveDt(new Date());
		 */
		packLabDtRepository.delete(id);
		
		
	}

	@Override
	public PackLabDt findOne(int id) {
		// TODO Auto-generated method stub
		return packLabDtRepository.findOne(id);
	}

	@Override
	public List<PackLabDt> findByPackDt(PackDt packDt) {
		// TODO Auto-generated method stub
		return packLabDtRepository.findByPackDt(packDt);
	}

	@Override
	public List<PackLabDt> findByPackDtAndMetal(PackDt packDt, Metal metal) {
		// TODO Auto-generated method stub
		return packLabDtRepository.findByPackDtAndMetal(packDt, metal);
	}

	@Override
	public String packLabDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub

		PackDt packDt=packDtService.findOne(dtId);
		List<PackLabDt>packLabDts = findByPackDt(packDt);
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(packLabDts.size()).append(",\"rows\": [");
		  for(PackLabDt packLabDt :packLabDts){
				sb.append("{\"id\":\"")
				.append(packLabDt.getId())
				.append("\",\"metal\":\"")
				.append((packLabDt.getMetal() != null ? packLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((packLabDt.getLabourType() != null ? packLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((packLabDt.getLabourRate() != null ? packLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((packLabDt.getLabourValue() != null ? packLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(packLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(packLabDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(packLabDt.getPerGramRate())
				.append("\",\"percent\":\"")
				.append(packLabDt.getPercentage())
				.append("\",\"perCaratRate\":\"")
				.append(packLabDt.getPerCaratRate());
				if(disableFlg.equalsIgnoreCase("false")) {
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doLabDtLockUnLock(")
					.append(packLabDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editPackLabDt(")
					.append(packLabDt.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deletePackLabDt(event,")
					.append(packLabDt.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(packLabDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");	
				}else {
					 sb.append("\",\"actionLock\":\"")
						.append("")
						.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						 .append("\"},");
				}
				
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	}

	@Override
	public String packLabDtSave(PackLabDt packLabDt, Integer id, Integer packMtId, Integer packDtId,
			Principal principal) {
		
		String retVal ="-1";
		
		try {
			
			PackMt packMt = packMtService.findOne(packMtId);
			PackDt packDt = packDtService.findOne(packDtId);
			
			LabourType labourType = labourTypeService.findOne(packLabDt.getLabourType().getId());
			
			
			int i=0;
			if(packLabDt.getPerPcRate() == true){
				i +=1;
			}
			
			if(packLabDt.getPerGramRate() == true){
				i +=1;
			}
			
			if(packLabDt.getPercentage() == true){
				i +=1;
			}
			
			if(packLabDt.getPerCaratRate() == true){
				i +=1;
			}
			
			if(i >1){
				return retVal = "-11";
			}else if(i==0){
				return retVal = "-12";
			}
			
			
			
			if(labourType.getCode().equalsIgnoreCase("Grade")){
				return retVal = "-15";
			}
			
			Boolean labourCheckFlg =false;
//			List<PackLabDt> packLabDts = findByPackDt(packDt);
//			for (PackLabDt packLabDt2 : packLabDts) {
//				
//				if(packLabDt2.getLabourType().getId() == packLabDt.getLabourType().getId()) {
//					labourCheckFlg = true;
//					break;
//				}
//			}
			
			if(labourCheckFlg) {
				return "-13";
			}
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				packLabDt.setPackMt(packMt);
				packLabDt.setPackDt(packDt);
				packLabDt.setCreatedBy(principal.getName());
				packLabDt.setCreatedDate(new java.util.Date());

				
				
			}else{
				packLabDt.setId(id);
				packLabDt.setPackMt(packMt);
				packLabDt.setPackDt(packDt);
			
				packLabDt.setModiBy(principal.getName());
				packLabDt.setModiDate(new java.util.Date());
				retVal = "-2";
			}
			
			
			save(packLabDt);
			
			
				
		//costingDtItemService.applyItemLabRate(costingDtItem, principal);
		packDtService.updateFob(packDt);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

	@Override
	public String updatePackLabourRate(Principal principal, Integer packMtId, Double labourRate) {
		// TODO Auto-generated method stub
		PackMt packMt = packMtService.findOne(packMtId);
		
		List<PackDt> packDts = packDtService.findByPackMt(packMt);
		for (PackDt packDt2 : packDts) {
			List<PackLabDt> packLabDts = findByPackDt(packDt2); 
			for (PackLabDt packLabDt : packLabDts) {
				
				if(packLabDt.getLabourType().getName().equalsIgnoreCase("Finishing")) {
					packLabDt.setPerGramRate(true);
					packLabDt.setLabourRate(labourRate);
				//	packLabDt.setrLock(true);
					packLabDt.setPerPcRate(false);
					packLabDt.setPerCaratRate(false);
					packLabDt.setPercentage(false);
					packLabDt.setModiBy(principal.getName());
					packLabDt.setModiDate(new Date());
					save(packLabDt);
				}
				
			}
			
			packDtService.updateFob(packDt2);
		}
		
		
		
		return "1";
	}

	@Override
	public List<PackLabDt> findByPackMt(PackMt packMt) {
		// TODO Auto-generated method stub
		return packLabDtRepository.findByPackMt(packMt);
	}

	@Override
	public String applyGrading(PackDt packDt, Principal principal) {
		// TODO Auto-generated method stub

		
		String retVal = "-1";
		
		try {
		
					
					Double labValue = 0.0;
					
					LabourType labourType = labourTypeService.findByCodeAndDeactive("Grade", false);
					
					Double totalCarat = 0.0;
					List<PackStnDt> packStnDts = packStnDtService.findByPackDt(packDt);
					for (PackStnDt packStnDt : packStnDts) {
						if(packStnDt.getStoneType().getName().equalsIgnoreCase("Diamond")) {
							totalCarat +=   packStnDt.getCarat();	
						}
					}
					
					
					
					PackMetalDt packMetalDt = packMetalDtService.findByPackDtAndMainMetal(packDt, true);
					
					
					List<LabourCharge> labourCharges=labourChargeService.getLabourRates(packDt.getPackMt().getParty(), packDt.getDesign().getCategory(), totalCarat, false, packMetalDt.getPurity().getMetal(), labourType);
					
					if(labourCharges.size() > 0) {
					List<PackLabDt> packLabDts = findByPackDt(packDt);
					
					Boolean isAvailable=false;
					for(LabourCharge labourCharge :labourCharges){
						isAvailable=false;
						Double totalValue = 0.0;
						
						
						
							Double pointerCts=0.00;
							for (PackStnDt packStnDt : packStnDts) {
								if(packStnDt.getStoneType().getName().equalsIgnoreCase("Diamond")) {
								
								StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(packStnDt.getShape(), packStnDt.getSize(), false);
								if(stoneChart != null) {
									
									Double pointerRate =packDt.getPackMt().getPointerRate();
									if(pointerRate <= 0) {
										pointerRate = 750.0;
									}
									
									if(stoneChart.getPointerFlg()) {
										
										totalValue += Math.round((pointerRate * packStnDt.getCarat())*100.0)/100.0;	
										pointerCts +=packStnDt.getCarat();
									}else {
										
										
										 if(labourCharge.getPerCaratRate() == true){
											totalValue += Math.round((labourCharge.getRate() * packStnDt.getCarat())*100.0)/100.0;
										}
										
									}
									
								}
								}
								
							}
							
							if((Math.round(totalCarat*1000.0)/1000.0)>(Math.round(pointerCts*1000.0)/1000.0)) {
								if(labourCharge.getPerPcsRate() == true){
									totalValue += Math.round((labourCharge.getRate() * packDt.getPcs())*100.0)/100.0;
								
								}
							}
							
							
					
							
						
						
						
						
						for (PackLabDt packLabDt : packLabDts) {
							
							if(packLabDt.getLabourType().equals(labourCharge.getLabourType())){
								
								isAvailable=true;
								if(packLabDt.getrLock().equals(false)){
									
										
									packLabDt.setPerPcRate(false);
									packLabDt.setPerGramRate(false);
									packLabDt.setPercentage(false);
									packLabDt.setPerCaratRate(false);
									packLabDt.setLabourValue(totalValue);
									
									if((Math.round(totalCarat*1000.0)/1000.0)>(Math.round(pointerCts*1000.0)/1000.0)) {
										if(labourCharge.getPerPcsRate() == true){
											packLabDt.setPerPcRate(true);
											packLabDt.setLabourRate(Math.round((totalValue / packDt.getPcs())*100.0)/100.0);	
										}	
									}else {
										packLabDt.setPerCaratRate(true);
										packLabDt.setLabourRate(Math.round((totalValue / totalCarat)*100.0)/100.0);
									}
									
								 if(labourCharge.getPerCaratRate() == true){
										packLabDt.setPerCaratRate(true);
										packLabDt.setLabourRate(Math.round((totalValue / totalCarat)*100.0)/100.0);
										
									}
									
									
									save(packLabDt);
									
									labValue += totalValue;
									
								}
								
							}
						}
						
						
						if(!isAvailable){
							
							PackLabDt packLabDt = new PackLabDt();
							
							packLabDt.setPackMt(packDt.getPackMt());
							packLabDt.setPackDt(packDt);
							packLabDt.setLabourType(labourCharge.getLabourType());
							packLabDt.setMetal(packMetalDt.getPurity().getMetal());
							packLabDt.setLabourValue(totalValue);
							
							if((Math.round(totalCarat*1000.0)/1000.0)>(Math.round(pointerCts*1000.0)/1000.0)) {
								if(labourCharge.getPerPcsRate() == true){
									packLabDt.setPerPcRate(true);
									packLabDt.setLabourRate(Math.round((totalValue / packDt.getPcs())*100.0)/100.0);	
								}	
							}else {
								packLabDt.setPerCaratRate(true);
								packLabDt.setLabourRate(Math.round((totalValue / totalCarat)*100.0)/100.0);
							}
							
						 if(labourCharge.getPerCaratRate() == true){
								packLabDt.setPerCaratRate(true);
								packLabDt.setLabourRate(Math.round((totalValue / totalCarat)*100.0)/100.0);
								
							}
							
							
							packLabDt.setCreatedDate(new java.util.Date());
							packLabDt.setCreatedBy(principal.getName());
							
							save(packLabDt);
							
							labValue += totalValue;
						}
						
					}
					
					
					
			//		packDt.setLabValue(labValue);
			//		packDtService.save(packDt);
					
					}else {
						
					}
				//	updateFob(jobRecDt, false);
					
					
					
					retVal ="1";
				
					
					
					
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

}

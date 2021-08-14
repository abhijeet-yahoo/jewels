package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRmStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmStnDtService;

@Service
@Repository
@Transactional
public class ConsigRmStnDtService implements IConsigRmStnDtService{

	@Autowired
	private IConsigRmStnDtRepository consigRmStnDtRepository;
	
	@Autowired
	private IConsigMtService consigMtService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IQualityService  qualityService;
	
	@Autowired
	private IShapeService shapeService;
	

	@Autowired
	private IStoneTypeService stoneTypeService;
	

	@Override
	public List<ConsigRmStnDt> findByConsigMt(ConsigMt consigMt) {
		
		return consigRmStnDtRepository.findByConsigMt(consigMt);
	}


	@Override
	public ConsigRmStnDt findByUniqueId(Long uniqueId) {
		
		return consigRmStnDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public ConsigRmStnDt findOne(Integer id) {
		
		return consigRmStnDtRepository.findOne(id);
	}

	@Override
	public void save(ConsigRmStnDt consigRmStnDt) {
		
		consigRmStnDtRepository.save(consigRmStnDt);
	}

	@Override
	public void delete(int id) {
		consigRmStnDtRepository.delete(id);
		
	}

	@Override
	public String saveConsigRmStnDt(ConsigRmStnDt consigRmStnDt, Integer id, Integer mtId, Principal principal,
			String stockType, Boolean allowNegative) {
		
		String action = "added";
		String retVal = "-1";
		
		
		ConsigMt consigMt = consigMtService.findOne(mtId);
		
		
		
		StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(consigRmStnDt.getShape(), consigRmStnDt.getSize(),false);
		
			
			if(!allowNegative.equals(true)){
				
				Double stockBal = stoneTranService.getStockBalance(consigRmStnDt.getDepartment().getId(),
						consigRmStnDt.getStoneType().getId(), consigRmStnDt.getShape().getId(),
						consigRmStnDt.getQuality().getId(), stoneChart.getSizeGroup().getId(), stoneChart.getSieve(),
						stockType);

				if (stockBal < consigRmStnDt.getCarat()) {
					return "Error : Stock Not Available (Available In Stock : " + stockBal + ")";
				}
				 
				
			}
			
			StoneType stoneType =stoneTypeService.findOne(consigRmStnDt.getStoneType().getId());
			Shape shape =shapeService.findOne(consigRmStnDt.getShape().getId());
			Quality quality =qualityService.findOne(consigRmStnDt.getQuality().getId());
			
			List<StoneTran>stoneTrans =stoneTranService.getDiamondStockRate(consigRmStnDt.getDepartment().getId(),stoneType.getName(),
					shape.getName(), quality.getName(), consigRmStnDt.getSize(), stoneChart.getSieve());
			
			
			if(stoneTrans.size()>0) {
				
				
				consigRmStnDt.setCreatedBy(principal.getName());
				consigRmStnDt.setCreatedDt(new java.util.Date());
				consigRmStnDt.setUniqueId(new Date().getTime());
				
				
				
				
				
			
			
			consigRmStnDt.setBalCarat(consigRmStnDt.getCarat());
			consigRmStnDt.setBalStone(consigRmStnDt.getStone());
			
			if (consigRmStnDt.getSubShape().getId() == null) {
				consigRmStnDt.setSubShape(null);
			}
			
			consigRmStnDt.setConsigMt(consigMt);
			consigRmStnDt.setSieve(stoneChart.getSieve());
			consigRmStnDt.setSizeGroup(stoneChart.getSizeGroup());
			consigRmStnDt.setStnAmount(Math.round((consigRmStnDt.getCarat()*consigRmStnDt.getStnRate1())*100.0)/100.0);
			
			
			save(consigRmStnDt);
			
			
			Double adjCarat = consigRmStnDt.getCarat();
			Integer adjStone= consigRmStnDt.getStone();
			
		
			
			if(adjCarat>0) {
			
				
				
				
				Double valSum=0.0;
				Double caratSum=0.0;

				
				 for (StoneTran stoneTranBal : stoneTrans) {
					 
					 if(adjCarat>0) {
						 
						 if(stoneTranBal.getBalCarat()>= adjCarat) {
							 
							 StoneTran stoneTran = new StoneTran();
								stoneTran.setCreatedBy(consigRmStnDt.getCreatedBy());
								stoneTran.setCreatedDt(consigRmStnDt.getCreatedDt());
								stoneTran.setLotNo(stoneTranBal.getLotNo());

								
								stoneTran.setTranDate(consigMt.getInvDate()); 
								stoneTran.setDepartment(null);
								stoneTran.setDeptLocation(consigRmStnDt.getDepartment());
								stoneTran.setStoneType(stoneTranBal.getStoneType());
								stoneTran.setShape(stoneTranBal.getShape());
								stoneTran.setQuality(stoneTranBal.getQuality());
								stoneTran.setSize(stoneTranBal.getSize());
								stoneTran.setSieve(stoneTranBal.getSieve());
								stoneTran.setSizeGroup(stoneTranBal.getSizeGroup());
								stoneTran.setStone(adjStone);
								stoneTran.setCarat(adjCarat);		
								stoneTran.setBalStone(adjStone*-1);
								stoneTran.setBalCarat(adjCarat*-1); 
								stoneTran.setRate(stoneTranBal.getRate());
								stoneTran.setInOutFld("D"); 
								stoneTran.setBagMt(null);
								stoneTran.setBagSrNo(0);
								stoneTran.setSettingType(null);
								stoneTran.setSetting(null);
								stoneTran.setPacketNo(consigRmStnDt.getPacketNo());
								stoneTran.setRemark(consigRmStnDt.getRemark());
								stoneTran.setParty(consigMt.getParty());
								stoneTran.setTranType("ConsigRmStn");
								stoneTran.setRefTranId(consigRmStnDt.getId());
								stoneTran.setAvgRate(stoneTranBal.getRate());
								stoneTran.setTransferRate((double)Math.round((stoneTran.getAvgRate()+(stoneTran.getAvgRate()*stoneTranBal.getStoneType().getTransferRatePerc()/100))*100)/100);
								stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*stoneTranBal.getStoneType().getFactoryRatePerc()/100))*100)/100);
								
								
								stoneTranService.save(stoneTran);
							 
							 valSum += Math.round((adjCarat*stoneTranBal.getRate())*100.0)/100.0;
							  caratSum += Math.round(adjCarat*1000.0)/1000.0;
							
							break;
						 }else {
							 adjCarat =(double) Math.round((adjCarat-stoneTranBal.getBalCarat())*1000)/1000;
								adjStone = Math.round(adjStone-stoneTranBal.getBalStone());
								
								 StoneTran stoneTran = new StoneTran();
									stoneTran.setCreatedBy(consigRmStnDt.getCreatedBy());
									stoneTran.setCreatedDt(consigRmStnDt.getCreatedDt());
									stoneTran.setLotNo(stoneTranBal.getLotNo());

									
									stoneTran.setTranDate(consigMt.getInvDate()); 
									stoneTran.setDepartment(null);
									stoneTran.setDeptLocation(consigRmStnDt.getDepartment());
									stoneTran.setStoneType(stoneTranBal.getStoneType());
									stoneTran.setShape(stoneTranBal.getShape());
									stoneTran.setQuality(stoneTranBal.getQuality());
									stoneTran.setSize(stoneTranBal.getSize());
									stoneTran.setSieve(stoneTranBal.getSieve());
									stoneTran.setSizeGroup(stoneTranBal.getSizeGroup());
									stoneTran.setStone(stoneTranBal.getBalStone());
									stoneTran.setCarat(stoneTranBal.getBalCarat());		
									stoneTran.setBalStone(stoneTranBal.getBalStone()*-1);
									stoneTran.setBalCarat(stoneTranBal.getBalCarat()*-1); 
									stoneTran.setRate(stoneTranBal.getRate());
									stoneTran.setInOutFld("D"); 
									stoneTran.setBagMt(null);
									stoneTran.setBagSrNo(0);
									stoneTran.setSettingType(null);
									stoneTran.setSetting(null);
									stoneTran.setPacketNo(consigRmStnDt.getPacketNo());
									stoneTran.setRemark(consigRmStnDt.getRemark());
									stoneTran.setParty(consigMt.getParty());
									stoneTran.setTranType("ConsigRmStn");
									stoneTran.setRefTranId(consigRmStnDt.getId());
									stoneTran.setAvgRate(stoneTranBal.getRate());
									stoneTran.setTransferRate((double)Math.round((stoneTran.getAvgRate()+(stoneTran.getAvgRate()*stoneTranBal.getStoneType().getTransferRatePerc()/100))*100)/100);
									stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*stoneTranBal.getStoneType().getFactoryRatePerc()/100))*100)/100);
									
									
									stoneTranService.save(stoneTran);
								
								 valSum += Math.round((stoneTranBal.getBalCarat()*stoneTranBal.getRate())*100.0)/100.0;
								  caratSum += Math.round(stoneTranBal.getBalCarat()*1000.0)/1000.0;
						 }
						 
						 
						
						 
						 
					 }
					 
					 
				 }
				 
				 Double avgRate= Math.round((valSum/caratSum)*100.0)/100.0;
				 consigRmStnDt.setStnRate(avgRate);
				
				 save(consigRmStnDt);
				
				
			}
			retVal ="1";
				
			}else {
				retVal ="Stock Not Found, Line 124 - ConsigRmStnDtService ";
			}
			
		
			
		
		
	
	
		
		
		return retVal;
	}

	@Override
	public String consigRmStnDtDelete(Integer id, Principal principal) {
		String retVal = "-1";

		try {
	
			ConsigRmStnDt consigRmStnDt = findOne(id);
			
			if(consigRmStnDt.getAdjWt() > 0) {
				return "Can not delete, Loose Stone";
			}else {
			
			
			
			
			
			List<StoneTran>stoneTrans=stoneTranService.findByRefTranIdAndTranType(id, "ConsigRmStn");
			for(StoneTran stoneTran : stoneTrans) {
				stoneTranService.delete(stoneTran.getId());	
			}

			
			delete(id);
			retVal = "1";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return retVal;
	}

	@Override
	public String consigRmStnDtListing(Integer mtId) {

		
		StringBuilder sb = new StringBuilder();
		ConsigMt consigMt = consigMtService.findOne(mtId);
		
		List<ConsigRmStnDt> consigRmStnDts = findByConsigMt(consigMt);
		
		sb.append("{\"total\":").append(consigRmStnDts.size()).append(",\"rows\": [");
			
		if(consigRmStnDts.size() > 0){
				for (ConsigRmStnDt consigRmStnDt : consigRmStnDts) {
					sb.append("{\"id\":\"")
							.append(consigRmStnDt.getId())
							.append("\",\"stoneType\":\"")
							.append((consigRmStnDt.getStoneType() != null ? consigRmStnDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((consigRmStnDt.getShape() != null ? consigRmStnDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((consigRmStnDt.getSubShape() != null ? consigRmStnDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((consigRmStnDt.getQuality() != null ? consigRmStnDt.getQuality().getName() : ""))					
							.append("\",\"department\":\"")
							.append((consigRmStnDt.getDepartment() != null ? consigRmStnDt.getDepartment().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((consigRmStnDt.getSize() != null ? consigRmStnDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(consigRmStnDt.getSieve())	
							.append("\",\"sizeGroupStr\":\"")
							.append((consigRmStnDt.getSizeGroup() != null ? consigRmStnDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(consigRmStnDt.getStone())
							.append("\",\"balStone\":\"")
							.append(consigRmStnDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(consigRmStnDt.getCarat())
							.append("\",\"diffCarat\":\"")
							.append(consigRmStnDt.getDiffCarat())
							.append("\",\"balCarat\":\"")
							.append(consigRmStnDt.getBalCarat())
							.append("\",\"rate\":\"")
							.append(consigRmStnDt.getStnRate())
							.append("\",\"rate1\":\"")
							.append(consigRmStnDt.getStnRate1())
							.append("\",\"amount\":\"")
							.append(consigRmStnDt.getStnAmount())	
							.append("\",\"packetNo\":\"")
							.append(consigRmStnDt.getPacketNo() !=null ? consigRmStnDt.getPacketNo() : ""  )	
							.append("\",\"remark\":\"")
							.append((consigRmStnDt.getRemark() != null ? consigRmStnDt.getRemark() : ""));
									
							sb.append("\",\"action1\":\"");
						
							sb.append("<a href='javascript:editConsigRmStnDt(").append(consigRmStnDt.getId());
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
							sb.append("\",\"action2\":\"");
							sb.append("<a href='javascript:deleteConsigRmStnDt(event,")
							  .append(consigRmStnDt.getId()).append(", 0);' href='javascript:void(0);'");
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(consigRmStnDt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
							sb.append("\"},");
						
				}
				
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@Override
	public String rowStoneDtDetails(Integer mtId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		ConsigMt consigMt = consigMtService.findOne(mtId);
		
		List<ConsigRmStnDt> consigRmStnDts = findByConsigMt(consigMt);
		
		sb.append("{\"total\":").append(consigRmStnDts.size()).append(",\"rows\": [");
			
		if(consigRmStnDts.size() > 0){
				for (ConsigRmStnDt consigRmStnDt : consigRmStnDts) {
					
					sb.append("{\"id\":\"")
							.append(consigRmStnDt.getId())
							.append("\",\"stoneType\":\"")
							.append((consigRmStnDt.getStoneType() != null ? consigRmStnDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((consigRmStnDt.getShape() != null ? consigRmStnDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((consigRmStnDt.getSubShape() != null ? consigRmStnDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((consigRmStnDt.getQuality() != null ? consigRmStnDt.getQuality().getName() : ""))					
							.append("\",\"department\":\"")
							.append((consigRmStnDt.getDepartment() != null ? consigRmStnDt.getDepartment().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((consigRmStnDt.getSize() != null ? consigRmStnDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(consigRmStnDt.getSieve())	
							.append("\",\"sizeGroupStr\":\"")
							.append((consigRmStnDt.getSizeGroup() != null ? consigRmStnDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(consigRmStnDt.getStone())
							.append("\",\"balStone\":\"")
							.append(consigRmStnDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(consigRmStnDt.getCarat())
							.append("\",\"balCarat\":\"")
							.append(consigRmStnDt.getBalCarat())
							.append("\",\"issueStone\":\"")
							.append("0")
							.append("\",\"issueCarat\":\"")
							.append("0")
							.append("\",\"rate\":\"")
							.append(consigRmStnDt.getStnRate())
							.append("\",\"amount\":\"")
							.append(consigRmStnDt.getStnAmount());
							sb.append("\"},");
						
				}
				
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	
	
	
}

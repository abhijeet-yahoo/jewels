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
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRmStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmStnDtService;

@Service	
@Repository
@Transactional
public class SaleRmStnDtService implements ISaleRmStnDtService {

	@Autowired
	private ISaleRmStnDtRepository saleRmStnDtRepository;
	
	@Autowired
	private ISaleMtService	saleMtService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IQualityService qualityService;
	
	

	@Override
	public List<SaleRmStnDt> findBySaleMt(SaleMt saleMt) {
		
		return saleRmStnDtRepository.findBySaleMt(saleMt);
	}


	@Override
	public SaleRmStnDt findByUniqueId(Long uniqueId) {
		
		return saleRmStnDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public SaleRmStnDt findOne(Integer id) {
		
		return saleRmStnDtRepository.findOne(id);
	}

	@Override
	public void save(SaleRmStnDt saleRmStnDt) {
		
		saleRmStnDtRepository.save(saleRmStnDt);
	}

	@Override
	public void delete(int id) {
		 saleRmStnDtRepository.delete(id);
		
	}

	@Override
	public String saveSaleRmStnDt(SaleRmStnDt saleRmStnDt, Integer id, Integer mtId, Principal principal,
			String stockType, Boolean allowNegative) {
		
	//	String action = "added";
		String retVal = "-1";
		
		
		SaleMt saleMt = saleMtService.findOne(mtId);
		
		
		
		StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(saleRmStnDt.getShape(), saleRmStnDt.getSize(),false);
		
			
			if(!allowNegative.equals(true)){
				
				Double stockBal = stoneTranService.getStockBalance(saleRmStnDt.getDepartment().getId(),
						saleRmStnDt.getStoneType().getId(), saleRmStnDt.getShape().getId(),
						saleRmStnDt.getQuality().getId(), stoneChart.getSizeGroup().getId(), stoneChart.getSieve(),
						stockType);

				if (stockBal < saleRmStnDt.getCarat()) {
					return "Error : Stock Not Available (Available In Stock : " + stockBal + ")";
				}
				 
				
			}
			
			StoneType stoneType =stoneTypeService.findOne(saleRmStnDt.getStoneType().getId());
			Shape shape =shapeService.findOne(saleRmStnDt.getShape().getId());
			Quality quality =qualityService.findOne(saleRmStnDt.getQuality().getId());
			
			List<StoneTran>stoneTrans =stoneTranService.getDiamondStockRate(saleRmStnDt.getDepartment().getId(),stoneType.getName(),
					shape.getName(), quality.getName(), saleRmStnDt.getSize(), stoneChart.getSieve());
			
			if(stoneTrans.size()>0) {
				
				
				saleRmStnDt.setCreatedBy(principal.getName());
				saleRmStnDt.setCreatedDt(new java.util.Date());
				saleRmStnDt.setUniqueId(new Date().getTime());
				
				
				
				
				
			
			
				saleRmStnDt.setBalCarat(saleRmStnDt.getCarat());
				saleRmStnDt.setBalStone(saleRmStnDt.getStone());
			
			if (saleRmStnDt.getSubShape().getId() == null) {
				saleRmStnDt.setSubShape(null);
			}
			
			saleRmStnDt.setSaleMt(saleMt);
			saleRmStnDt.setSieve(stoneChart.getSieve());
			saleRmStnDt.setSizeGroup(stoneChart.getSizeGroup());
			saleRmStnDt.setStnAmount(Math.round((saleRmStnDt.getCarat()*saleRmStnDt.getStnRate1())*100.0)/100.0);
			saleRmStnDt.setRemark(saleRmStnDt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			
			
			save(saleRmStnDt);
			
			
			Double adjCarat = saleRmStnDt.getCarat();
			Integer adjStone= saleRmStnDt.getStone();
			
			
			
			if(adjCarat>0) {
			
			
				
				
				Double valSum=0.0;
				Double caratSum=0.0;

				
				 for (StoneTran stoneTranBal : stoneTrans) {
					 
					 if(adjCarat>0) {
						 
						 if(stoneTranBal.getBalCarat()>= adjCarat) {
							 
							 StoneTran stoneTran = new StoneTran();
								stoneTran.setCreatedBy(saleRmStnDt.getCreatedBy());
								stoneTran.setCreatedDt(saleRmStnDt.getCreatedDt());
								stoneTran.setLotNo(stoneTranBal.getLotNo());

								
								stoneTran.setTranDate(saleMt.getInvDate()); 
								stoneTran.setDepartment(null);
								stoneTran.setDeptLocation(saleRmStnDt.getDepartment());
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
								stoneTran.setPacketNo(saleRmStnDt.getPacketNo());
								stoneTran.setRemark(saleRmStnDt.getRemark());
								stoneTran.setParty(saleMt.getParty());
								stoneTran.setTranType("SaleRmStn");
								stoneTran.setRefTranId(saleRmStnDt.getId());
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
									stoneTran.setCreatedBy(saleRmStnDt.getCreatedBy());
									stoneTran.setCreatedDt(saleRmStnDt.getCreatedDt());
									stoneTran.setLotNo(stoneTranBal.getLotNo());

									
									stoneTran.setTranDate(saleMt.getInvDate()); 
									stoneTran.setDepartment(null);
									stoneTran.setDeptLocation(saleRmStnDt.getDepartment());
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
									stoneTran.setPacketNo(saleRmStnDt.getPacketNo());
									stoneTran.setRemark(saleRmStnDt.getRemark());
									stoneTran.setParty(saleMt.getParty());
									stoneTran.setTranType("SaleRmStn");
									stoneTran.setRefTranId(saleRmStnDt.getId());
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
				 saleRmStnDt.setStnRate(avgRate);
				
				 save(saleRmStnDt);
				
				
			}
				
			retVal ="1";	
				
			}else {
				
				retVal ="Stock Not Found, Line 124 - SaleRmStnDtService";
			}
		
			
		
		
	
	
		
		
		return retVal;
		
	
	}

	@Override
	public String saleRmStnDtDelete(Integer id, Principal principal) {

		String retVal = "-1";

		try {
			
			SaleRmStnDt saleRmStnDt = findOne(id);
			if(saleRmStnDt.getAdjWt() > 0) {
				return "Can not delete, Loose Stone";
			}else {
			
			List<StoneTran>stoneTrans=stoneTranService.findByRefTranIdAndTranType(id, "SaleRmStn");
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
	public String saleRmStnDtListing(Integer mtId,String disableFlg) {

		StringBuilder sb = new StringBuilder();
		SaleMt saleMt = saleMtService.findOne(mtId);
		
		List<SaleRmStnDt> saleRmStnDts = findBySaleMt(saleMt);
		
		sb.append("{\"total\":").append(saleRmStnDts.size()).append(",\"rows\": [");
			
		if(saleRmStnDts.size() > 0){
				for (SaleRmStnDt saleRmStnDt : saleRmStnDts) {
					sb.append("{\"id\":\"")
							.append(saleRmStnDt.getId())
							.append("\",\"stoneType\":\"")
							.append((saleRmStnDt.getStoneType() != null ? saleRmStnDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((saleRmStnDt.getShape() != null ? saleRmStnDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((saleRmStnDt.getSubShape() != null ? saleRmStnDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((saleRmStnDt.getQuality() != null ? saleRmStnDt.getQuality().getName() : ""))					
							.append("\",\"department\":\"")
							.append((saleRmStnDt.getDepartment() != null ? saleRmStnDt.getDepartment().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((saleRmStnDt.getSize() != null ? saleRmStnDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(saleRmStnDt.getSieve())	
							.append("\",\"sizeGroupStr\":\"")
							.append((saleRmStnDt.getSizeGroup() != null ? saleRmStnDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(saleRmStnDt.getStone())
							.append("\",\"balStone\":\"")
							.append(saleRmStnDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(saleRmStnDt.getCarat())
							.append("\",\"diffCarat\":\"")
							.append(saleRmStnDt.getDiffCarat())
							.append("\",\"balCarat\":\"")
							.append(saleRmStnDt.getBalCarat())
							.append("\",\"rate\":\"")
							.append(saleRmStnDt.getStnRate1())
							.append("\",\"amount\":\"")
							.append(saleRmStnDt.getStnAmount())	
							.append("\",\"packetNo\":\"")
							.append(saleRmStnDt.getPacketNo() !=null ? saleRmStnDt.getPacketNo() : ""  )	
							.append("\",\"remark\":\"")
							.append((saleRmStnDt.getRemark() != null ? saleRmStnDt.getRemark() : ""));
								
					if(disableFlg.equalsIgnoreCase("false")) {
							sb.append("\",\"action1\":\"");
						
							sb.append("<a href='javascript:editSaleRmStnDt(").append(saleRmStnDt.getId());
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
							sb.append("\",\"action2\":\"");
							sb.append("<a href='javascript:deleteSaleRmStnDt(event,")
							  .append(saleRmStnDt.getId()).append(", 0);' href='javascript:void(0);'");
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(saleRmStnDt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
							sb.append("\"},");
					}else {
						 sb.append("\",\"actionLock\":\"")
							.append("")
							.append("\",\"action1\":\"")
							.append("")
							.append("\",\"action2\":\"")
							 .append("\"},");
					}
						
				}
				
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}


	@Override
	public String saleRowStoneDtDetails(Integer mtId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		SaleMt saleMt = saleMtService.findOne(mtId);
		
		List<SaleRmStnDt> saleRmStnDts = findBySaleMt(saleMt);
		
		sb.append("{\"total\":").append(saleRmStnDts.size()).append(",\"rows\": [");
			
		if(saleRmStnDts.size() > 0){
				for (SaleRmStnDt saleRmStnDt : saleRmStnDts) {
					
					sb.append("{\"id\":\"")
							.append(saleRmStnDt.getId())
							.append("\",\"stoneType\":\"")
							.append((saleRmStnDt.getStoneType() != null ? saleRmStnDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((saleRmStnDt.getShape() != null ? saleRmStnDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((saleRmStnDt.getSubShape() != null ? saleRmStnDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((saleRmStnDt.getQuality() != null ? saleRmStnDt.getQuality().getName() : ""))					
							.append("\",\"department\":\"")
							.append((saleRmStnDt.getDepartment() != null ? saleRmStnDt.getDepartment().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((saleRmStnDt.getSize() != null ? saleRmStnDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(saleRmStnDt.getSieve())	
							.append("\",\"sizeGroupStr\":\"")
							.append((saleRmStnDt.getSizeGroup() != null ? saleRmStnDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(saleRmStnDt.getStone())
							.append("\",\"balStone\":\"")
							.append(saleRmStnDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(saleRmStnDt.getCarat())
							.append("\",\"balCarat\":\"")
							.append(saleRmStnDt.getBalCarat())
							.append("\",\"issueStone\":\"")
							.append("0")
							.append("\",\"issueCarat\":\"")
							.append("0")
							.append("\",\"rate\":\"")
							.append(saleRmStnDt.getStnRate())
							.append("\",\"amount\":\"")
							.append(saleRmStnDt.getStnAmount());
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

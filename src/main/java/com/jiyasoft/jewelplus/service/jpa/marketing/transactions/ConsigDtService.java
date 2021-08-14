package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;

@Service
@Repository
@Transactional
public class ConsigDtService implements IConsigDtService {

	@Autowired
	private IConsigDtRepository consigDtRepository;
	
	@Autowired
	private IConsigStnDtService consigStnDtService;
	
	@Autowired
	private IConsigMetalDtService consigMetalDtService;
	
	@Autowired
	private IConsigLabDtService consigLabDtService;
	
	@Autowired
	private IConsigCompDtService consigCompDtService;
	
	@Autowired
	private IStockTranService stockTranService;

	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IConsigMtService consigMtService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	
	@Autowired
	private IPackMtService packMtService;
	
	@Autowired
	private IPackDtService packDtService;
	
	@Autowired
	private IPackMetalDtService packMetalDtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	
	@Override
	public void save(ConsigDt consigDt) {
		// TODO Auto-generated method stub
		consigDtRepository.save(consigDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigDtRepository.delete(id);
	}

	@Override
	public ConsigDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigDtRepository.findOne(id);
	}

	@Override
	public String addConsigBarcodeItem(Integer mtId, String barcode, Principal principal,Integer partyId,Integer locationId,Integer employeeId) {
		
		ConsigMt consigMt = consigMtService.findOne(mtId);
		
		Department department = departmentService.findOne(locationId);
		Party party = partyService.findOne(partyId);
		
		Employee employee = null;
		if(employeeId != null) {
			 employee = employeeService.findOne(employeeId);
		}
		
		consigMt.setParty(party);
		consigMt.setLocation(department);
		consigMt.setEmployee(employee);
		consigMtService.save(consigMt);
		
		// TODO Auto-generated method stub
		StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactiveAndLocation(barcode, true, false,department);
		if(stockMt != null && stockMt.getCurrStk().equals(true)) {
			
			ConsigDt consigDt2 = findByConsigMtAndBarcode(consigMt,barcode);
			if(consigDt2 != null) {
				return "Duplicate Barcode";
			}
			
			ConsigDt consigDt =new ConsigDt();
			consigDt.setBarcode(stockMt.getBarcode());
			consigDt.setCreatedBy(principal.getName());
			consigDt.setCreatedDate(new Date());
			consigDt.setDesign(stockMt.getDesign());
			consigDt.setGrossWt(Math.round(stockMt.getGrossWt()*1000.0)/1000.0);
			consigDt.setNetWt(stockMt.getNetWt());
			consigDt.setConsigMt(consigMt);
			consigDt.setPcs(stockMt.getQty());
			consigDt.setMetalValue(stockMt.getMetalValue());
			consigDt.setFinalPrice((double) Math.round(stockMt.getFactoryCost()));
			consigDt.setLabValue(stockMt.getLabourValue());
//			consigDt.setHallMarking(stockMt.getHallMarking());
//			consigDt.setLazerMarking(stockMt.getLazerMarking());
//			consigDt.setEngraving(stockMt.getEngraving());
//			consigDt.setGrading(stockMt.getGrading());
			
			save(consigDt);
			
			
			List<StockMetalDt>stockMetalDts =stockMetalDtService.findByStockMtAndDeactive(stockMt, false);
			for(StockMetalDt stockMetalDt :stockMetalDts) {
				ConsigMetalDt consigMetalDt = new ConsigMetalDt();
				consigMetalDt.setColor(stockMetalDt.getColor());
				consigMetalDt.setCreateDate(new Date());
				consigMetalDt.setCreatedBy(principal.getName());
				consigMetalDt.setMainMetal(stockMetalDt.getMainMetal());
				consigMetalDt.setMetalPcs(stockMetalDt.getMetalPcs());
				consigMetalDt.setMetalWeight(stockMetalDt.getMetalWt());
				consigMetalDt.setConsigDt(consigDt);
				consigMetalDt.setConsigMt(consigMt);
				consigMetalDt.setPartNm(stockMetalDt.getPartNm());
				consigMetalDt.setPurity(stockMetalDt.getPurity());
				consigMetalDt.setPurityConv(stockMetalDt.getPurityConv());
				consigMetalDt.setMetalRate(Math.round((consigDt.getMetalValue() / consigDt.getNetWt())*1000.0)/1000.0);
				consigMetalDt.setMetalValue(Math.round((stockMetalDt.getMetalWt() * (Math.round((stockMt.getMetalValue() / stockMt.getNetWt())*1000.0)/1000.0))*1000.0)/1000.0);
			
				consigMetalDtService.save(consigMetalDt);
				
			}
			
			List<StockStnDt>stockStnDts=stockStnDtService.findByStockMtAndDeactive(stockMt, false);
			Double stoneVal =0.0;
			for(StockStnDt stockStnDt :stockStnDts) {
				ConsigStnDt consigStnDt =new ConsigStnDt();
				consigStnDt.setCarat(stockStnDt.getCarat());
				consigStnDt.setCreatedBy(principal.getName());
				consigStnDt.setCreatedDate(new Date());
				consigStnDt.setConsigDt(consigDt);
				consigStnDt.setConsigMt(consigMt);
				consigStnDt.setPartNm(stockStnDt.getPartNm());
				consigStnDt.setQuality(stockStnDt.getQuality());
				consigStnDt.setSetting(stockStnDt.getSetting());
				consigStnDt.setSettingType(stockStnDt.getSettingType());
				consigStnDt.setShape(stockStnDt.getShape());
				consigStnDt.setSieve(stockStnDt.getSieve());
				consigStnDt.setSize(stockStnDt.getSize());
				consigStnDt.setSizeGroup(stockStnDt.getSizeGroup());
				consigStnDt.setStone(stockStnDt.getStone());
				consigStnDt.setStoneType(stockStnDt.getStoneType());
				consigStnDt.setSubShape(stockStnDt.getSubShape());
				consigStnDt.setCenterStone(stockStnDt.getCenterStone());
				consigStnDt.setStoneValue(stockStnDt.getDiamValue());
				consigStnDtService.save(consigStnDt);
				
				stoneVal += stockStnDt.getDiamValue();
				
			}
			
			consigDt.setStoneValue(stoneVal);
			save(consigDt);
			
			List<StockCompDt>stockCompDts =stockCompDtService.findByStockMtAndDeactive(stockMt, false);
			for(StockCompDt stockCompDt :stockCompDts) {
				ConsigCompDt consigCompDt = new ConsigCompDt();
				consigCompDt.setColor(stockCompDt.getColor());
				consigCompDt.setComponent(stockCompDt.getComponent());
				consigCompDt.setCompQty(stockCompDt.getCompQty());
				consigCompDt.setCreatedBy(principal.getName());
				consigCompDt.setCreatedDate(new Date());
				consigCompDt.setConsigDt(consigDt);
				consigCompDt.setConsigMt(consigMt);
				consigCompDt.setPurity(stockCompDt.getPurity());
				consigCompDt.setPurityConv(stockCompDt.getPurityConv());
				consigCompDt.setCompWt(stockCompDt.getCompWt());
				consigCompDtService.save(consigCompDt);
				
				
				
			}
			stockMt.setCurrStk(false);
			stockMtService.save(stockMt);
			
			StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(barcode, true);
			StockTran stockTran2 = new StockTran();
			stockTran2.setBarcode(consigDt.getBarcode());
			stockTran2.setCreatedBy(principal.getName());
			stockTran2.setCreatedDate(new Date());
			stockTran2.setCurrStatus("CONSIGNMENTISS");
			stockTran2.setCurrStk(true);
			stockTran2.setLocation(stockTran.getLocation());
			stockTran2.setRefStkTranId(stockTran.getId());
			stockTran2.setRefTranId(consigDt.getId());
			stockTran2.setTranDate(consigMt.getInvDate());
			stockTran2.setTranType("CONSIGNMENTISS");
			stockTran2.setParty(consigMt.getParty());
			stockTran2.setEmployee(consigMt.getEmployee());
			stockTran2.setStockMt(stockMt);
			stockTranService.save(stockTran2);
			
			stockTran.setCurrStk(false);
			stockTran.setIssueDate(consigMt.getInvDate());
			stockTranService.save(stockTran);
	
			
			return "1";
			
		}else {
			
			return "Item Not Found In Stock";
		}
		
	}

	@Override
	public List<ConsigDt> findByConsigMt(ConsigMt consigMt) {
		// TODO Auto-generated method stub
		return consigDtRepository.findByConsigMt(consigMt);
	}

	@Override
	public String deleteConsigDt(Integer dtId) {
		// TODO Auto-generated method stub
		ConsigDt consigDt = findOne(dtId);
		if(consigDt.getAdjQty()>0) {
			
			return "Can not Delete,  Record present in consignment return or sales invoice";
			
		}else {
			
			List<ConsigStnDt> consigStnDts=consigStnDtService.findByConsigDt(consigDt);
			for(ConsigStnDt consigStnDt :consigStnDts) {
			consigStnDtService.delete(consigStnDt.getId());
				
			}
			
			List<ConsigMetalDt> consigMetalDts = consigMetalDtService.findByConsigDt(consigDt);
			for(ConsigMetalDt consigMetalDt :consigMetalDts) {
			
				consigMetalDtService.delete(consigMetalDt.getId());
				
			}
			
			List<ConsigCompDt> consigCompDts = consigCompDtService.findByConsigDt(consigDt);
			for(ConsigCompDt consigCompDt :consigCompDts) {
				consigCompDtService.delete(consigCompDt.getId());
				
			}
			
			
			List<ConsigLabDt> consigLabDts = consigLabDtService.findByConsigDt(consigDt);
			for(ConsigLabDt consigLabDt :consigLabDts) {
				consigLabDtService.delete(consigLabDt.getId());
				
			}
			
			//PackDt
			if(consigDt.getRefTranDtId() != null) {
			
			ConsigDt consigDt2 = findByRefTranDtIdAndTranType(consigDt.getRefTranDtId(), consigDt.getTranType());
			PackDt packDt = packDtService.findOne(consigDt2.getRefTranDtId());
			packDt.setAdjQty(packDt.getAdjQty() - consigDt2.getPcs());
			packDtService.save(packDt);
			}
			
			delete(dtId);
			
			StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("CONSIGNMENTISS", consigDt.getId(), true);
			if(stockTran !=null) {
				
				StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
				stockTran2.setCurrStk(true);
				stockTranService.save(stockTran2);
				
				stockTranService.delete(stockTran.getId());
				
			}
			
			
			if(consigDt.getRefTranDtId() == null && consigDt.getTranType() == null) {
	//		StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(consigDt.getBarcode(), false, false);
			
			 StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());	
			stockMt.setCurrStk(true);
			stockMtService.save(stockMt);
			}
			
		}
		
		
		
		return "1";
	}

	@Override
	public String applyRate(ConsigDt consigDt, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String applyMetal(ConsigDt consigDt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String applyStoneRate(List<ConsigStnDt> consigStnDts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsigStnDt applySettingRate(ConsigStnDt consigStnDt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsigStnDt applyConsigStoneRate(ConsigStnDt consigStnDt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsigStnDt applyHandlingRate(ConsigStnDt consigStnDt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String applyCompRate(List<ConsigCompDt> consigCompDts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsigCompDt applyConsigCompRate(ConsigCompDt consigCompDt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String applyLabRate(ConsigDt consigDt, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateFob(ConsigDt consigDt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String consigDtListing(Integer mtId,String disableFlg) {
		// TODO Auto-generated method stub
		ConsigMt consigMt = consigMtService.findOne(mtId);
		List<ConsigDt> consigDts = findByConsigMt(consigMt);
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigDts.size()).append(",\"rows\": [");
		 Integer srNo=0;
		 for(ConsigDt consigDt :consigDts){
			 
			 List<ConsigMetalDt> consigMetalDts = consigMetalDtService.findByConsigDt(consigDt);
			 String purityVal="";
			 for(ConsigMetalDt consigMetalDt : consigMetalDts) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+consigMetalDt.getPurity().getName()+"-"+consigMetalDt.getColor().getName();
				 }else {
					 purityVal=consigMetalDt.getPurity().getName()+"-"+consigMetalDt.getColor().getName();
				 }
				 
			 }
				
				sb.append("{\"srNo\":\"")
			     .append(++srNo)
			     .append("\",\"id\":\"")
				 .append(consigDt.getId())
				 .append("\",\"image\":\"")
				 .append(consigDt.getDesign().getDefaultImage() !=null?consigDt.getDesign().getDefaultImage():"")
			     .append("\",\"barcode\":\"")
				 .append(consigDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(consigDt.getDesign().getMainStyleNo())
				 .append("\",\"ktCol\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(consigDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(consigDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(consigDt.getNetWt())
				 .append("\",\"metalValue\":\"")
				 .append(consigDt.getMetalValue())
				 .append("\",\"stoneValue\":\"")
				 .append(consigDt.getStoneValue())
				 .append("\",\"compValue\":\"")
				 .append(consigDt.getCompValue())
				 .append("\",\"labourValue\":\"")
				 .append(consigDt.getLabValue())
				 .append("\",\"setValue\":\"")
				 .append(consigDt.getSetValue())
				 .append("\",\"handlingCost\":\"")
				 .append(consigDt.getHandlingValue())
				 .append("\",\"fob\":\"")
				 .append(consigDt.getFob())
				 .append("\",\"other\":\"")
				 .append(consigDt.getOther())
				 .append("\",\"discAmount\":\"")
				 .append(consigDt.getDiscAmount())
				 .append("\",\"finalPrice\":\"")
				 .append(consigDt.getFinalPrice())
				 .append("\",\"hallMark\":\"")
				 .append(consigDt.getHallMarking())
				 .append("\",\"grading\":\"")
				 .append(consigDt.getGrading())
				 .append("\",\"lazerMark\":\"")
				 .append(consigDt.getLazerMarking())
				 .append("\",\"engraving\":\"")
				 .append(consigDt.getEngraving())
				 .append("\",\"rLock\":\"")
				 .append((consigDt.getrLock())); //1 = lock & 0 = unlock
				 
				if(disableFlg.equalsIgnoreCase("false")) {
					sb.append("\",\"actionLock\":\"")
					 .append("<a href='javascript:doConsigDtLockUnLock(")
					 .append(consigDt.getId())
					 .append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
					 .append("\",\"action2\":\"")
					 .append("<a  href='javascript:deleteConsigDt(event,")
					 .append(consigDt.getId())
					 .append(");' class='btn btn-xs btn-danger triggerRemove")
					 .append(consigDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					 .append("\"},");
				}else {
					 sb.append("\",\"actionLock\":\"")
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
	public String consigDtPickupListing(Integer mtId) {
		// TODO Auto-generated method stub
		ConsigMt consigMt = consigMtService.findOne(mtId);
		List<ConsigDt> consigDts = findByConsigMt(consigMt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 
		 try {
			 
			 sb.append("{\"total\":").append(consigDts.size()).append(",\"rows\": [");
			 
			 for(ConsigDt consigDt :consigDts){
				 
				 if(Math.round(consigDt.getPcs() - consigDt.getAdjQty()) > 0) {
				 
					 List<ConsigMetalDt> consigMetalDts = consigMetalDtService.findByConsigDt(consigDt);
					 String purityVal="";
					 for(ConsigMetalDt consigMetalDt : consigMetalDts) {
						 if(purityVal.length()>0) {
							 purityVal=purityVal+","+consigMetalDt.getPurity().getName()+"-"+consigMetalDt.getColor().getName();
						 }else {
							 purityVal=consigMetalDt.getPurity().getName()+"-"+consigMetalDt.getColor().getName();
						 }
						 
					 }
				 
				 sb.append("{\"id\":\"")
				 .append(consigDt.getId())
				 .append("\",\"barcode\":\"")
				 .append(consigDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(consigDt.getDesign().getMainStyleNo())
				 .append("\",\"ktColor\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(consigDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(consigDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(consigDt.getNetWt())
				 .append("\"},");
			 
				 }
			 }
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	
	return str;
	}

	@Override
	public String packdtList(Integer mtId) {
		// TODO Auto-generated method stub
		PackMt packMt=packMtService.findOne(mtId);
		List<PackDt> packDts = packDtService.findByPackMt(packMt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 
		 try {
			 
			 sb.append("{\"total\":").append(packDts.size()).append(",\"rows\": [");
			 
			 for(PackDt packDt :packDts){
				 
				 if(Math.round(packDt.getPcs() - packDt.getAdjQty()) > 0) {
				 
				 List<PackMetalDt>packmetals=packMetalDtService.findByPackDt(packDt);
				 String purityVal="";
				 for(PackMetalDt packMetalDt:packmetals) {
					 if(purityVal.length()>0) {
						 purityVal=purityVal+","+packMetalDt.getPurity().getName()+"-"+packMetalDt.getColor().getName();
					 }else {
						 purityVal=packMetalDt.getPurity().getName()+"-"+packMetalDt.getColor().getName();
					 }
					 
				 }
				 
				 sb.append("{\"id\":\"")
				 .append(packDt.getId())
				 .append("\",\"barcode\":\"")
				 .append(packDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(packDt.getDesign().getMainStyleNo())
				 .append("\",\"ktColor\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(packDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(packDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(packDt.getNetWt())
				 .append("\"},");
			 
				 }
			 }
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	
	return str;
	}

	@Override
	public ConsigDt findByRefTranDtIdAndTranType(Integer refTranDtId, String tranType) {
		// TODO Auto-generated method stub
		return consigDtRepository.findByRefTranDtIdAndTranType(refTranDtId, tranType);
	}

	@Override
	public ConsigDt findByConsigMtAndBarcode(ConsigMt ConsigMt,String barcode) {
		// TODO Auto-generated method stub
		return consigDtRepository.findByConsigMtAndBarcode(ConsigMt,barcode);
	}

}

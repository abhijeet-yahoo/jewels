package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;


import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IRepairRetDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;

@Service
@Repository
@Transactional
public class RepairRetDtService implements IRepairRetDtService{
	
	@Autowired
	private IRepairRetDtRepository repairRetDtRepository;
	
	@Autowired
	private IRepairRetMtService repairRetMtService;
	
	@Autowired
	private IRepairRetMetalDtService repairRetMetalDtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	
	@Autowired
	private IRepairRetStnDtService repairRetStnDtService;
	
	@Autowired
	private IRepairRetCompDtService repairRetCompDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	

	@Override
	public void save(RepairRetDt repairRetDt) {
		// TODO Auto-generated method stub
		repairRetDtRepository.save(repairRetDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		repairRetDtRepository.delete(id);
	}

	@Override
	public RepairRetDt findOne(int id) {
		// TODO Auto-generated method stub
		return repairRetDtRepository.findOne(id);
	}

	@Override
	public List<RepairRetDt> findByRepairRetMt(RepairRetMt repairRetMt) {
		// TODO Auto-generated method stub
		return repairRetDtRepository.findByRepairRetMt(repairRetMt);
	}

	@Override
	public String deleteRepairRetDt(Integer dtId) {
		// TODO Auto-generated method stub
		
		RepairRetDt repairRetDt = findOne(dtId);
		
		StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(repairRetDt.getBarcode(), true, false);
		
		StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("REPAIRRETURN", dtId, false);
		
		if(stockMt != null) {
			
			return "Can not Delete";
		}else {
			
			List<RepairRetStnDt> repairRetStnDts = repairRetStnDtService.findByRepairRetDt(repairRetDt);
			for(RepairRetStnDt repairRetStnDt :repairRetStnDts) {
				repairRetStnDtService.delete(repairRetStnDt.getId());
				
			}
			
			List<RepairRetMetalDt> repairRetMetalDts=repairRetMetalDtService.findByRepairRetDt(repairRetDt);
			for(RepairRetMetalDt repairRetMetalDt :repairRetMetalDts) {
				repairRetMetalDtService.delete(repairRetMetalDt.getId());
				
			}
			
			List<RepairRetCompDt>repairRetCompDts=repairRetCompDtService.findByRepairRetDt(repairRetDt);
			for(RepairRetCompDt repairRetCompDt :repairRetCompDts) {
				repairRetCompDtService.delete(repairRetCompDt.getId());
				
			}
			
			
		
			delete(dtId);
			
			
			if(stockTran !=null) {
				
				StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
				stockTran2.setCurrStk(true);
				stockTranService.save(stockTran2);
				
				stockTranService.delete(stockTran.getId());
				
			}
			 
			StockMt stockMt2 = stockMtService.findOne(repairRetDt.getStockMt().getMtId());	
			stockMt2.setCurrStk(true);
			stockMtService.save(stockMt2);
			
		}
		
		
		
		return "1";
	}

	@Override
	public String repairRetDtListing(Integer mtId, String flag) {
		// TODO Auto-generated method stub
		
		RepairRetMt repairRetMt = repairRetMtService.findOne(mtId);
		List<RepairRetDt> repairRetDts = findByRepairRetMt(repairRetMt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(repairRetDts.size()).append(",\"rows\": [");
		 Integer srNo=0;
		 for(RepairRetDt repairRetDt : repairRetDts){
			 
			 List<RepairRetMetalDt> repairRetMetalDts =repairRetMetalDtService.findByRepairRetDt(repairRetDt);
			 String purityVal="";
			 for(RepairRetMetalDt repairRetMetalDt : repairRetMetalDts) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+repairRetMetalDt.getPurity().getName()+"-"+repairRetMetalDt.getColor().getName();
				 }else {
					 purityVal=repairRetMetalDt.getPurity().getName()+"-"+repairRetMetalDt.getColor().getName();
				 }
				 
			 }
				
				sb.append("{\"srNo\":\"")
			     .append(++srNo)
			     .append("\",\"id\":\"")
				 .append(repairRetDt.getId())
				 .append("\",\"image\":\"")
				 .append(repairRetDt.getDesign().getDefaultImage() !=null?repairRetDt.getDesign().getDefaultImage():"")
			     .append("\",\"barcode\":\"")
				 .append(repairRetDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(repairRetDt.getDesign().getMainStyleNo())
				 .append("\",\"ktCol\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(repairRetDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(repairRetDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(repairRetDt.getNetWt())
				 .append("\",\"metalValue\":\"")
				 .append(repairRetDt.getMetalValue())
				 .append("\",\"stoneValue\":\"")
				 .append(repairRetDt.getStoneValue())
				 .append("\",\"compValue\":\"")
				 .append(repairRetDt.getCompValue())
				 .append("\",\"labourValue\":\"")
				 .append(repairRetDt.getLabValue())
				 .append("\",\"setValue\":\"")
				 .append(repairRetDt.getSetValue())
				 .append("\",\"handlingCost\":\"")
				 .append(repairRetDt.getHandlingValue())
				 .append("\",\"fob\":\"")
				 .append(repairRetDt.getFob())
				 .append("\",\"other\":\"")
				 .append(repairRetDt.getOther())
				 .append("\",\"discAmount\":\"")
				 .append(repairRetDt.getDiscAmount())
				 .append("\",\"finalPrice\":\"")
				 .append(repairRetDt.getFinalPrice())
				 .append("\",\"rLock\":\"")
				 .append((repairRetDt.getrLock())); //1 = lock & 0 = unlock
				 
				 if(flag.equalsIgnoreCase("false")){
				 sb.append("\",\"actionLock\":\"")
				 .append("<a href='javascript:doPackDtLockUnLock(")
				 .append(repairRetDt.getId())
				 .append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				 .append("\",\"action2\":\"")
				 .append("<a  href='javascript:deleteRepairRetDt(event,")
				 .append(repairRetDt.getId())
				 .append(");' class='btn btn-xs btn-danger triggerRemove")
				 .append(repairRetDt.getId())
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
	public String repairRetTransferInFactory(Principal principal, String vMtId, Integer repairRetMtId) {
	
		RepairRetMt repairRetMt = repairRetMtService.findOne(repairRetMtId);
		String[] stkMtId = vMtId.split(",");
		for (int i = 0; i < stkMtId.length; i++) {
			
			StockMt stockMt = stockMtService.findOne(Integer.parseInt(stkMtId[i].toString()));
			
			RepairRetDt repairRetDt =new RepairRetDt();
			repairRetDt.setBarcode(stockMt.getBarcode());
			repairRetDt.setCreatedBy(principal.getName());
			repairRetDt.setCreatedDate(new Date());
			repairRetDt.setDesign(stockMt.getDesign());
			repairRetDt.setGrossWt(stockMt.getGrossWt());
			repairRetDt.setNetWt(stockMt.getNetWt());
			repairRetDt.setRepairRetMt(repairRetMt);
			repairRetDt.setPcs(stockMt.getQty());
			repairRetDt.setMetalValue(stockMt.getMetalValue());
			repairRetDt.setFinalPrice((double) Math.round(stockMt.getFactoryCost()));
			repairRetDt.setFob((double) Math.round(stockMt.getFactoryCost()));
			repairRetDt.setLabValue(stockMt.getLabourValue());
			repairRetDt.setStoneValue(stockMt.getStoneValue());
			repairRetDt.setStockMt(stockMt);
			save(repairRetDt);
			
			List<StockMetalDt>stockMetalDts =stockMetalDtService.findByStockMtAndDeactive(stockMt, false);
			for(StockMetalDt stockMetalDt :stockMetalDts) {
				RepairRetMetalDt repairRetMetalDt = new RepairRetMetalDt();
				repairRetMetalDt.setColor(stockMetalDt.getColor());
				repairRetMetalDt.setCreateDate(new Date());
				repairRetMetalDt.setCreatedBy(principal.getName());
				repairRetMetalDt.setMainMetal(stockMetalDt.getMainMetal());
				repairRetMetalDt.setMetalPcs(stockMetalDt.getMetalPcs());
				repairRetMetalDt.setMetalWeight(stockMetalDt.getMetalWt());
				repairRetMetalDt.setRepairRetDt(repairRetDt);
				repairRetMetalDt.setRepairRetMt(repairRetMt);
				repairRetMetalDt.setPartNm(stockMetalDt.getPartNm());
				repairRetMetalDt.setPurity(stockMetalDt.getPurity());
				repairRetMetalDt.setPurityConv(stockMetalDt.getPurityConv());
				
				repairRetMetalDtService.save(repairRetMetalDt);
				
			}
			
			List<StockStnDt>stockStnDts=stockStnDtService.findByStockMtAndDeactive(stockMt, false);
			Double stnVal =0.0;
			for(StockStnDt stockStnDt :stockStnDts) {
				RepairRetStnDt repairRetStnDt =new RepairRetStnDt();
				repairRetStnDt.setCarat(stockStnDt.getCarat());
				repairRetStnDt.setCreatedBy(principal.getName());
				repairRetStnDt.setCreatedDate(new Date());
				repairRetStnDt.setRepairRetDt(repairRetDt);
				repairRetStnDt.setRepairRetMt(repairRetMt);
				repairRetStnDt.setPartNm(stockStnDt.getPartNm());
				repairRetStnDt.setQuality(stockStnDt.getQuality());
				repairRetStnDt.setSetting(stockStnDt.getSetting());
				repairRetStnDt.setSettingType(stockStnDt.getSettingType());
				repairRetStnDt.setShape(stockStnDt.getShape());
				repairRetStnDt.setSieve(stockStnDt.getSieve());
				repairRetStnDt.setSize(stockStnDt.getSize());
				repairRetStnDt.setSizeGroup(stockStnDt.getSizeGroup());
				repairRetStnDt.setStone(stockStnDt.getStone());
				repairRetStnDt.setStoneType(stockStnDt.getStoneType());
				repairRetStnDt.setSubShape(stockStnDt.getSubShape());
				repairRetStnDt.setCenterStone(stockStnDt.getCenterStone());
				repairRetStnDt.setStoneValue(stockStnDt.getDiamValue());
				
				stnVal +=stockStnDt.getDiamValue();
				
				repairRetStnDtService.save(repairRetStnDt);
				
			}
			
			repairRetDt.setStoneValue(Math.round((stnVal)*100.0)/100.0);
			save(repairRetDt);
			
			
			List<StockCompDt>stockCompDts =stockCompDtService.findByStockMtAndDeactive(stockMt, false);
			for(StockCompDt stockCompDt :stockCompDts) {
				RepairRetCompDt repairRetCompDt = new RepairRetCompDt();
				repairRetCompDt.setColor(stockCompDt.getColor());
				repairRetCompDt.setComponent(stockCompDt.getComponent());
				repairRetCompDt.setCompQty(stockCompDt.getCompQty());
				repairRetCompDt.setCreatedBy(principal.getName());
				repairRetCompDt.setCreatedDate(new Date());
				repairRetCompDt.setRepairRetDt(repairRetDt);
				repairRetCompDt.setRepairRetMt(repairRetMt);
				repairRetCompDt.setPurity(stockCompDt.getPurity());
				repairRetCompDt.setPurityConv(stockCompDt.getPurityConv());
				repairRetCompDt.setCompWt(stockCompDt.getCompWt());
				
				repairRetCompDtService.save(repairRetCompDt);
				
				
				
			}
			stockMt.setCurrStk(false);
			stockMt.setModiBy(principal.getName());
			stockMt.setModiDt(new Date());
			stockMtService.save(stockMt);
			
			StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(stockMt.getBarcode(), true);
			StockTran stockTran2 = new StockTran();
			stockTran2.setBarcode(stockMt.getBarcode());
			stockTran2.setCreatedBy(principal.getName());
			stockTran2.setCreatedDate(new Date());
			stockTran2.setCurrStatus("REPAIRRETURN");
			stockTran2.setCurrStk(false);
			stockTran2.setLocation(stockTran.getLocation());
			stockTran2.setRefStkTranId(stockTran.getId());
			stockTran2.setRefTranId(repairRetDt.getId());
			stockTran2.setTranDate(repairRetMt.getInvDate());
			stockTran2.setParty(repairRetMt.getParty());
			stockTran2.setTranType("REPAIRRETURN");
			stockTran2.setStockMt(stockMt);
			stockTranService.save(stockTran2);
			
			stockTran.setCurrStk(false);
			stockTran.setIssueDate(repairRetMt.getInvDate());
			stockTranService.save(stockTran);
			
		
			
		}
		return "1";
	}

}


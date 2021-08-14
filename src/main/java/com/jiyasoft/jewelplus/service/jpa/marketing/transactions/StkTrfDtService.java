package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStkTrfDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;

@Service
@Repository
@Transactional
public class StkTrfDtService implements IStkTrfDtService{
	
	@Autowired
	private IStkTrfDtRepository stkTrfDtRepository;
	
	@Autowired
	private IStkTrfMtService stkTrfMtService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStkTrfCompDtService stkTrfCompDtService;
	
	@Autowired
	private IStkTrfStnDtService stkTrfStnDtService;
	
	@Autowired
	private IStkTrfMetalDtService stkTrfMetalDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	
	@Autowired
	private IStkTrfLabDtService stkTrfLabDtService;


	@Override
	public void save(StkTrfDt stkTrfDt) {
		// TODO Auto-generated method stub
		stkTrfDtRepository.save(stkTrfDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		 stkTrfDtRepository.delete(id);
		
	}

	@Override
	public StkTrfDt findOne(int id) {
		// TODO Auto-generated method stub
		return stkTrfDtRepository.findOne(id);
	}

	@Override
	public String addStkTrfBarcodeItem(Integer mtId, String barcode, Principal principal,String tranType) {
		// TODO Auto-generated method stub

		StkTrfMt stkTrfMt = stkTrfMtService.findOne(mtId);
		
		// TODO Auto-generated method stub
		StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(barcode, true, false);
		if(stockMt != null && stockMt.getCurrStk().equals(true)) {
			StkTrfDt stkTrfDt =new StkTrfDt();
			stkTrfDt.setBarcode(stockMt.getBarcode());
			stkTrfDt.setCreatedBy(principal.getName());
			stkTrfDt.setCreatedDate(new Date());
			stkTrfDt.setDesign(stockMt.getDesign());
			stkTrfDt.setGrossWt(Math.round(stockMt.getGrossWt()*1000.0)/1000.0);
			stkTrfDt.setNetWt(stockMt.getNetWt());
			stkTrfDt.setStkTrfMt(stkTrfMt);
			stkTrfDt.setPcs(stockMt.getQty());
			stkTrfDt.setPendApprovalFlg(true);
			stkTrfDt.setMetalValue(stockMt.getMetalValue());
			stkTrfDt.setFinalPrice(stockMt.getFactoryCost());
			stkTrfDt.setLabValue(stockMt.getLabourValue());
			stkTrfDt.setFob(stockMt.getFactoryCost());
			
			save(stkTrfDt);
			
			
			List<StockMetalDt>stockMetalDts =stockMetalDtService.findByStockMtAndDeactive(stockMt, false);
			for(StockMetalDt stockMetalDt :stockMetalDts) {
				StkTrfMetalDt stkTrfMetalDt = new StkTrfMetalDt();
				stkTrfMetalDt.setColor(stockMetalDt.getColor());
				stkTrfMetalDt.setCreateDate(new Date());
				stkTrfMetalDt.setCreatedBy(principal.getName());
				stkTrfMetalDt.setMainMetal(stockMetalDt.getMainMetal());
				stkTrfMetalDt.setMetalPcs(stockMetalDt.getMetalPcs());
				stkTrfMetalDt.setMetalWeight(stockMetalDt.getMetalWt());
				stkTrfMetalDt.setStkTrfDt(stkTrfDt);
				stkTrfMetalDt.setStkTrfMt(stkTrfMt);
				stkTrfMetalDt.setPartNm(stockMetalDt.getPartNm());
				stkTrfMetalDt.setPurity(stockMetalDt.getPurity());
				stkTrfMetalDt.setPurityConv(stockMetalDt.getPurityConv());
				stkTrfMetalDt.setMetalRate(Math.round((stkTrfDt.getMetalValue() / stkTrfDt.getNetWt())*1000.0)/1000.0);
				stkTrfMetalDt.setMetalValue(Math.round((stockMetalDt.getMetalWt() * (Math.round((stockMt.getMetalValue() / stockMt.getNetWt())*1000.0)/1000.0))*1000.0)/1000.0);
			
				
				stkTrfMetalDtService.save(stkTrfMetalDt);
				
			}
			
			List<StockStnDt>stockStnDts=stockStnDtService.findByStockMtAndDeactive(stockMt, false);
			Double stoneVal =0.0;
			for(StockStnDt stockStnDt :stockStnDts) {
				StkTrfStnDt stkTrfStnDt =new StkTrfStnDt();
				stkTrfStnDt.setCarat(stockStnDt.getCarat());
				stkTrfStnDt.setCreatedBy(principal.getName());
				stkTrfStnDt.setCreatedDate(new Date());
				stkTrfStnDt.setStkTrfDt(stkTrfDt);
				stkTrfStnDt.setStkTrfMt(stkTrfMt);
				stkTrfStnDt.setPartNm(stockStnDt.getPartNm());
				stkTrfStnDt.setQuality(stockStnDt.getQuality());
				stkTrfStnDt.setSetting(stockStnDt.getSetting());
				stkTrfStnDt.setSettingType(stockStnDt.getSettingType());
				stkTrfStnDt.setShape(stockStnDt.getShape());
				stkTrfStnDt.setSieve(stockStnDt.getSieve());
				stkTrfStnDt.setSize(stockStnDt.getSize());
				stkTrfStnDt.setSizeGroup(stockStnDt.getSizeGroup());
				stkTrfStnDt.setStone(stockStnDt.getStone());
				stkTrfStnDt.setStoneType(stockStnDt.getStoneType());
				stkTrfStnDt.setSubShape(stockStnDt.getSubShape());
				stkTrfStnDt.setCenterStone(stockStnDt.getCenterStone());
				stkTrfStnDt.setStoneValue(stockStnDt.getDiamValue());
				stkTrfStnDtService.save(stkTrfStnDt);
				
				stoneVal += stockStnDt.getDiamValue();
			}
			
			stkTrfDt.setStoneValue(stoneVal);
			save(stkTrfDt);
			
			List<StockCompDt>stockCompDts =stockCompDtService.findByStockMtAndDeactive(stockMt, false);
			for(StockCompDt stockCompDt :stockCompDts) {
				StkTrfCompDt stkTrfCompDt = new StkTrfCompDt();
				stkTrfCompDt.setColor(stockCompDt.getColor());
				stkTrfCompDt.setComponent(stockCompDt.getComponent());
				stkTrfCompDt.setCompQty(stockCompDt.getCompQty());
				stkTrfCompDt.setCreatedBy(principal.getName());
				stkTrfCompDt.setCreatedDate(new Date());
				stkTrfCompDt.setStkTrfMt(stkTrfMt);
				stkTrfCompDt.setStkTrfDt(stkTrfDt);
				stkTrfCompDt.setPurity(stockCompDt.getPurity());
				stkTrfCompDt.setPurityConv(stockCompDt.getPurityConv());
				stkTrfCompDt.setCompWt(stockCompDt.getCompWt());
				stkTrfCompDtService.save(stkTrfCompDt);
				
				
				
			}
			
			
			
			  
			
			 
			
			StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(barcode, true);
			StockTran stockTran2 = new StockTran();
			stockTran2.setBarcode(stkTrfDt.getBarcode());
			stockTran2.setCreatedBy(principal.getName());
			stockTran2.setCreatedDate(new Date());
			stockTran2.setCurrStatus("In Transit");
			stockTran2.setCurrStk(true);
			stockTran2.setLocation(stkTrfMt.getToLocation());
			stockTran2.setRefStkTranId(stockTran.getId());
			stockTran2.setRefTranId(stkTrfDt.getId());
			stockTran2.setTranDate(stkTrfMt.getInvDate());
			stockTran2.setTranType(tranType);
			stockTran2.setStockMt(stockMt);
			stockTranService.save(stockTran2);
			
			stockTran.setCurrStk(false);
			stockTran.setIssueDate(stkTrfMt.getInvDate());
			stockTranService.save(stockTran);
			
			//Stock Currstk flg false code
			stockMt.setCurrStk(false); 
			  stockMt.setModiBy(principal.getName());
			  stockMt.setModiDt(new Date());
			stockMt.setLocation(stkTrfMt.getToLocation()); 
			stockMtService.save(stockMt);
			
			return "1";
			
		}else {
			
			return "Item Not Found In Stock";
		}
		
	}

	@Override
	public List<StkTrfDt> findByStkTrfMt(StkTrfMt stkTrfMt) {
		// TODO Auto-generated method stub
		return stkTrfDtRepository.findByStkTrfMt(stkTrfMt);
	}

	@Override
	public String deleteStkTrfDt(Integer dtId,String tranType) {
		// TODO Auto-generated method stub
		StkTrfDt stkTrfDt = findOne(dtId);
		if(stkTrfDt.getPendApprovalFlg().equals(false)) {
			
			return "Can not Delete, Barcode Received & Approved";
			
		}else {
			
			List<StkTrfStnDt> stkTrfStnDts= stkTrfStnDtService.findByStkTrfDt(stkTrfDt);
			for(StkTrfStnDt stkTrfStnDt :stkTrfStnDts) {
				stkTrfStnDtService.delete(stkTrfStnDt.getId());
				
			}
			
			List<StkTrfMetalDt> stkTrfMetalDts = stkTrfMetalDtService.findByStkTrfDt(stkTrfDt);
			for(StkTrfMetalDt stkTrfMetalDt :stkTrfMetalDts) {
				stkTrfMetalDtService.delete(stkTrfMetalDt.getId());
				
			}
			
			List<StkTrfCompDt> stkTrfCompDts = stkTrfCompDtService.findByStkTrfDt(stkTrfDt);
			for(StkTrfCompDt stkTrfCompDt : stkTrfCompDts) {
				stkTrfCompDtService.delete(stkTrfCompDt.getId());
				
			}
			
			
			List<StkTrfLabDt> stkTrfLabDts = stkTrfLabDtService.findByStkTrfDt(stkTrfDt);
			for(StkTrfLabDt stkTrfLabDt :stkTrfLabDts) {
				stkTrfLabDtService.delete(stkTrfLabDt.getId());
				
			}
		
		
			
			StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk(tranType, stkTrfDt.getId(), true);
			if(stockTran !=null) {
				
				StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
				stockTran2.setCurrStk(true);
				stockTranService.save(stockTran2);
				
				stockTranService.delete(stockTran.getId());
				
//				StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(stkTrfDt.getBarcode(), false, false);
			    StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());	
				stockMt.setCurrStk(true);
				stockMt.setLocation(stockTran2.getLocation());
				stockMtService.save(stockMt);
				
			}
			
			
		
			
			StkTrfMt stkTrfMt = stkTrfMtService.findOne(stkTrfDt.getStkTrfMt().getId());
			
			if(stkTrfMt.getFinalPrice() != null) {
				stkTrfMt.setFinalPrice(Math.round(stkTrfMt.getFinalPrice() - stkTrfDt.getFob()/100.0)*100.0);
				
				stkTrfMtService.save(stkTrfMt);
			}
			
			delete(stkTrfDt.getId());
		}
		
		
		
		return "1";
	}

	@Override
	public String stkTrfDtListing(Integer mtId,String disableFlg) {
		// TODO Auto-generated method stub
		
		StkTrfMt stkTrfMt = stkTrfMtService.findOne(mtId);
		List<StkTrfDt> stkTrfDts = findByStkTrfMt(stkTrfMt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stkTrfDts.size()).append(",\"rows\": [");
		 Integer srNo=0;
		 for(StkTrfDt stkTrfDt :stkTrfDts){
			 
			 List<StkTrfMetalDt> stkTrfMetalDts = stkTrfMetalDtService.findByStkTrfDt(stkTrfDt);
			 String purityVal="";
			 for(StkTrfMetalDt stkTrfMetalDt : stkTrfMetalDts) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+stkTrfMetalDt.getPurity().getName()+"-"+stkTrfMetalDt.getColor().getName();
				 }else {
					 purityVal=stkTrfMetalDt.getPurity().getName()+"-"+stkTrfMetalDt.getColor().getName();
				 }
				 
			 }
				
				sb.append("{\"srNo\":\"")
			     .append(++srNo)
			     .append("\",\"id\":\"")
				 .append(stkTrfDt.getId())
				 .append("\",\"image\":\"")
				 .append(stkTrfDt.getDesign().getDefaultImage() !=null?stkTrfDt.getDesign().getDefaultImage():"")
			     .append("\",\"barcode\":\"")
				 .append(stkTrfDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(stkTrfDt.getDesign().getMainStyleNo())
				 .append("\",\"ktCol\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(stkTrfDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(stkTrfDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(stkTrfDt.getNetWt())
				 .append("\",\"metalValue\":\"")
				 .append(stkTrfDt.getMetalValue())
				 .append("\",\"stoneValue\":\"")
				 .append(stkTrfDt.getStoneValue())
				 .append("\",\"compValue\":\"")
				 .append(stkTrfDt.getCompValue())
				 .append("\",\"labourValue\":\"")
				 .append(stkTrfDt.getLabValue())
				 .append("\",\"setValue\":\"")
				 .append(stkTrfDt.getSetValue())
				 .append("\",\"handlingCost\":\"")
				 .append(stkTrfDt.getHandlingValue())
				 .append("\",\"fob\":\"")
				 .append(stkTrfDt.getFob())
				 .append("\",\"other\":\"")
				 .append(stkTrfDt.getOther())
				 .append("\",\"discAmount\":\"")
				 .append(stkTrfDt.getDiscAmount())
				 .append("\",\"finalPrice\":\"")
				 .append(stkTrfDt.getFinalPrice())
				 .append("\",\"rLock\":\"")
				 .append((stkTrfDt.getrLock())); //1 = lock & 0 = unlock
				 
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				 .append("<a href='javascript:doConsigDtLockUnLock(")
				 .append(stkTrfDt.getId())
				 .append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				 .append("\",\"action2\":\"")
				 .append("<a  href='javascript:deleteStkTrfDt(event,")
				 .append(stkTrfDt.getId())
				 .append(");' class='btn btn-xs btn-danger triggerRemove")
				 .append(stkTrfDt.getId())
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



}

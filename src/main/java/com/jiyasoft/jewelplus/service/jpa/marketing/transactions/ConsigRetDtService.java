package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;

@Service
@Repository
@Transactional
public class ConsigRetDtService implements IConsigRetDtService {
	
	@Autowired
	private IConsigRetDtRepository consigRetDtRepository;
	
	@Autowired
	private IConsigRetMetalDtService consigRetMetalDtService;
	
	@Autowired
	private IConsigRetStnDtService consigRetStnDtService;
	
	@Autowired
	private IConsigRetCompDtService consigRetCompDtService;
	
	@Autowired
	private IConsigRetLabDtService consigRetLabDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IConsigRetMtService consigRetMtService;
	
	
	@Autowired
	private IConsigDtService consigDtService;
	
	

	@Override
	public void save(ConsigRetDt consigRetDt) {
		// TODO Auto-generated method stub
		consigRetDtRepository.save(consigRetDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigRetDtRepository.delete(id);
	}

	@Override
	public ConsigRetDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigRetDtRepository.findOne(id);
	}

	@Override
	public List<ConsigRetDt> findByConsigRetMt(ConsigRetMt consigRetMt) {
		// TODO Auto-generated method stub
		return consigRetDtRepository.findByConsigRetMt(consigRetMt);
	}

	@Override
	public String deleteConsigRetDt(Integer dtId) {
		// TODO Auto-generated method stub
		
		ConsigRetDt consigRetDt = findOne(dtId);
		 StockMt stockMt =  stockMtService.findByBarcodeAndCurrStkAndDeactive(consigRetDt.getBarcode(),  true, false);
			if(stockMt == null) {
			return "Can not Delete, Record present in consignment issue or sales invoice or packing list";
			
		}else {
			
			List<ConsigRetStnDt> consigRetStnDts=consigRetStnDtService.findByConsigRetDt(consigRetDt);
			for(ConsigRetStnDt consigRetStnDt :consigRetStnDts) {
				consigRetStnDtService.delete(consigRetStnDt.getId());
			}
			
			List<ConsigRetMetalDt> consigRetMetalDts = consigRetMetalDtService.findByConsigRetDt(consigRetDt);
			for(ConsigRetMetalDt consigRetMetalDt :consigRetMetalDts) {
				consigRetMetalDtService.delete(consigRetMetalDt.getId());
			}
			
			List<ConsigRetCompDt> consigRetCompDts = consigRetCompDtService.findByConsigRetDt(consigRetDt);
			for(ConsigRetCompDt consigRetCompDt :consigRetCompDts) {
				consigRetCompDtService.delete(consigRetCompDt.getId());
			}
			
			
			List<ConsigRetLabDt> consigRetLabDts = consigRetLabDtService.findByConsigRetDt(consigRetDt);
			for(ConsigRetLabDt consigRetLabDt :consigRetLabDts) {
				consigRetLabDtService.delete(consigRetLabDt.getId());
			}
			
			//ConsigDt
			ConsigRetDt consigRetDt2 = findByRefConsigDtId(consigRetDt.getRefConsigDtId());
			ConsigDt consigDt = consigDtService.findOne(consigRetDt2.getRefConsigDtId());
			consigDt.setAdjQty(consigDt.getAdjQty() - consigRetDt2.getPcs());
			consigDtService.save(consigDt);
			
			
			delete(consigRetDt.getId());
			
	
			StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("CONSIGNMENTRET", consigRetDt.getId(), true);
			if(stockTran !=null) {
					
				StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
				stockTran2.setCurrStk(true);
				stockTranService.save(stockTran2);
				
				stockTranService.delete(stockTran.getId());
				
			}
			
			
			
			  stockMt.setCurrStk(false); 
			  stockMtService.save(stockMt);
			 
			
			
		}
		
		
		
		return "1";
	}



	@Override
	public String consigRetDtListing(Integer mtId,String disableFlg) {
		// TODO Auto-generated method stub
		
		ConsigRetMt consigRetMt = consigRetMtService.findOne(mtId);
		List<ConsigRetDt> consigRetDts = findByConsigRetMt(consigRetMt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigRetDts.size()).append(",\"rows\": [");
		 Integer srNo=0;
		 for(ConsigRetDt consigRetDt :consigRetDts){
			 
			 List<ConsigRetMetalDt> consigRetMetalDts = consigRetMetalDtService.findByConsigRetDt(consigRetDt);
			 String purityVal="";
			 for(ConsigRetMetalDt consigRetMetalDt : consigRetMetalDts) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+consigRetMetalDt.getPurity().getName()+"-"+consigRetMetalDt.getColor().getName();
				 }else {
					 purityVal=consigRetMetalDt.getPurity().getName()+"-"+consigRetMetalDt.getColor().getName();
				 }
				 
			 }
				
				sb.append("{\"srNo\":\"")
			     .append(++srNo)
			     .append("\",\"id\":\"")
				 .append(consigRetDt.getId())
				 .append("\",\"image\":\"")
				 .append(consigRetDt.getDesign().getDefaultImage() !=null?consigRetDt.getDesign().getDefaultImage():"")
			     .append("\",\"barcode\":\"")
				 .append(consigRetDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(consigRetDt.getDesign().getMainStyleNo())
				 .append("\",\"ktCol\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(consigRetDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(consigRetDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(consigRetDt.getNetWt())
				 .append("\",\"metalValue\":\"")
				 .append(consigRetDt.getMetalValue())
				 .append("\",\"stoneValue\":\"")
				 .append(consigRetDt.getStoneValue())
				 .append("\",\"compValue\":\"")
				 .append(consigRetDt.getCompValue())
				 .append("\",\"labourValue\":\"")
				 .append(consigRetDt.getLabValue())
				 .append("\",\"setValue\":\"")
				 .append(consigRetDt.getSetValue())
				 .append("\",\"handlingCost\":\"")
				 .append(consigRetDt.getHandlingValue())
				 .append("\",\"fob\":\"")
				 .append(consigRetDt.getFob())
				 .append("\",\"other\":\"")
				 .append(consigRetDt.getOther())
				 .append("\",\"discAmount\":\"")
				 .append(consigRetDt.getDiscAmount())
				 .append("\",\"finalPrice\":\"")
				 .append(consigRetDt.getFinalPrice())
				 .append("\",\"hallMark\":\"")
				 .append(consigRetDt.getHallMarking())
				 .append("\",\"grading\":\"")
				 .append(consigRetDt.getGrading())
				 .append("\",\"lazerMark\":\"")
				 .append(consigRetDt.getLazerMarking())
				 .append("\",\"engraving\":\"")
				 .append(consigRetDt.getEngraving())
				 .append("\",\"rLock\":\"")
				 .append((consigRetDt.getrLock())); //1 = lock & 0 = unlock
				 
				if(disableFlg.equalsIgnoreCase("false")) {
				
				sb.append("\",\"actionLock\":\"")
				 .append("<a href='javascript:doConsigDtLockUnLock(")
				 .append(consigRetDt.getId())
				 .append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				 .append("\",\"action2\":\"")
				 .append("<a  href='javascript:deleteConsigRetDt(event,")
				 .append(consigRetDt.getId())
				 .append(");' class='btn btn-xs btn-danger triggerRemove")
				 .append(consigRetDt.getId())
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
	public ConsigRetDt findByRefConsigDtId(Integer refConsigDtId) {
		// TODO Auto-generated method stub
		return consigRetDtRepository.findByRefConsigDtId(refConsigDtId);
	}

	@Override
	public ConsigRetDt findByConsigRetMtAndBarcode(ConsigRetMt consigRetMt, String barcode) {
		// TODO Auto-generated method stub
		return consigRetDtRepository.findByConsigRetMtAndBarcode(consigRetMt, barcode);
	}

}

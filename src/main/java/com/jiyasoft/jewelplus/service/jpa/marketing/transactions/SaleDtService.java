package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;

@Service
@Repository
@Transactional
public class SaleDtService implements ISaleDtService{
	
	@Autowired
	private ISaleDtRepository saleDtRepository;
	
	@Autowired
	private ISaleMtService saleMtService;
	
	@Autowired
	private ISaleMetalDtService saleMetalDtService;
	
	@Autowired
	private ISaleStnDtService saleStnDtService;
	
	@Autowired
	private ISaleCompDtService saleCompDtService;
	
	@Autowired
	private ISaleLabDtService saleLabDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IConsigDtService consigDtService;
	
	@Autowired
	private IPackDtService packDtService;

	@Override
	public List<SaleDt> findAll() {
		// TODO Auto-generated method stub
		return saleDtRepository.findAll();
	}

	@Override
	public void save(SaleDt saleDt) {
		// TODO Auto-generated method stub
		saleDtRepository.save(saleDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		saleDtRepository.delete(id);
	}

	@Override
	public SaleDt findOne(int id) {
		// TODO Auto-generated method stub
		return saleDtRepository.findOne(id);
	}

	@Override
	public String addBarcodeItem(Integer mtId, String barcode, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleDt> findBySaleMt(SaleMt saleMt) {
		// TODO Auto-generated method stub
		return saleDtRepository.findBySaleMt(saleMt);
	}

	@Override
	public String deleteSaleDt(Integer dtId) {
		// TODO Auto-generated method stub
		SaleDt saleDt = findOne(dtId);
		if(saleDt.getAdjQty()>0) {
			
			return "Can not Delete,Record present in sales return";
			
		}else {
			
			List<SaleStnDt> saleStnDts = saleStnDtService.findBySaleDt(saleDt);
			for(SaleStnDt saleStnDt :saleStnDts) {
				saleStnDtService.delete(saleStnDt.getId());
				
			}
			
			List<SaleMetalDt> saleMetalDts = saleMetalDtService.findBySaleDt(saleDt);
			for(SaleMetalDt saleMetalDt :saleMetalDts) {
				saleMetalDtService.delete(saleMetalDt.getId());
				
			}
			
			List<SaleCompDt> saleCompDts = saleCompDtService.findBySaleDt(saleDt);
			for(SaleCompDt saleCompDt : saleCompDts) {
				saleCompDtService.delete(saleCompDt.getId());
				
			}
			
			
			List<SaleLabDt> saleLabDts = saleLabDtService.findBySaleDt(saleDt);
			for(SaleLabDt saleLabDt :saleLabDts) {
				saleLabDtService.delete(saleLabDt.getId());
				
			}
			
			//ConsigDt
			if(saleDt.getTranType().equalsIgnoreCase("CONSIGNMENTISS")) {
			SaleDt saleDt2 = findByRefTranDtIdAndTranType(saleDt.getRefTranDtId(), saleDt.getTranType());
			ConsigDt consigDt = consigDtService.findOne(saleDt2.getRefTranDtId());
			consigDt.setAdjQty(consigDt.getAdjQty() - saleDt2.getPcs());
			consigDtService.save(consigDt);
			}
			
			
			//PackingList
			if(saleDt.getTranType().equalsIgnoreCase("PACKLIST")) {
			SaleDt saleDt2 = findByRefTranDtIdAndTranType(saleDt.getRefTranDtId(), saleDt.getTranType());
			PackDt packDt = packDtService.findOne(saleDt2.getRefTranDtId());
			packDt.setAdjQty(packDt.getAdjQty() - saleDt2.getPcs());
			packDtService.save(packDt);
			}
		
			delete(saleDt.getId());
			
			StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("SaleIss", saleDt.getId(), true);
			if(stockTran !=null) {
				
				StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
				stockTran2.setCurrStk(true);
				stockTranService.save(stockTran2);
				
				stockTranService.delete(stockTran.getId());
				
			}
			
			
			/*
			 * StockMt stockMt =
			 * stockMtService.findByBarcodeAndCurrStkAndDeactive(saleDt.getBarcode(), false,
			 * false);
			 * 
			 * stockMt.setCurrStk(true); stockMtService.save(stockMt);
			 */
			
			
		}
		
		
		
		return "1";
	}

	@Override
	public String saleDtListing(Integer mtId,String disableFlg) {
		// TODO Auto-generated method stub
	
		SaleMt saleMt = saleMtService.findOne(mtId);
		List<SaleDt> saleDts = findBySaleMt(saleMt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleDts.size()).append(",\"rows\": [");
		 Integer srNo=0;
		 for(SaleDt saleDt :saleDts){
			 
			 List<SaleMetalDt> saleMetalDts = saleMetalDtService.findBySaleDt(saleDt);
			 String purityVal="";
			 for(SaleMetalDt saleMetalDt : saleMetalDts) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+saleMetalDt.getPurity().getName()+"-"+saleMetalDt.getColor().getName();
				 }else {
					 purityVal=saleMetalDt.getPurity().getName()+"-"+saleMetalDt.getColor().getName();
				 }
				 
			 }
				
				sb.append("{\"srNo\":\"")
			     .append(++srNo)
			     .append("\",\"id\":\"")
				 .append(saleDt.getId())
				 .append("\",\"image\":\"")
				 .append(saleDt.getDesign().getDefaultImage() !=null?saleDt.getDesign().getDefaultImage():"")
			     .append("\",\"barcode\":\"")
				 .append(saleDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(saleDt.getDesign().getMainStyleNo())
				 .append("\",\"ktCol\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(saleDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(saleDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(saleDt.getNetWt())
				 .append("\",\"metalValue\":\"")
				 .append(saleDt.getMetalValue())
				 .append("\",\"stoneValue\":\"")
				 .append(saleDt.getStoneValue())
				 .append("\",\"compValue\":\"")
				 .append(saleDt.getCompValue())
				 .append("\",\"labourValue\":\"")
				 .append(saleDt.getLabValue())
				 .append("\",\"setValue\":\"")
				 .append(saleDt.getSetValue())
				 .append("\",\"handlingCost\":\"")
				 .append(saleDt.getHandlingValue())
				 .append("\",\"fob\":\"")
				 .append(saleDt.getFob())
				 .append("\",\"other\":\"")
				 .append(saleDt.getOther())
				 .append("\",\"discAmount\":\"")
				 .append(saleDt.getDiscAmount())
				 .append("\",\"finalPrice\":\"")
				 .append(saleDt.getFinalPrice())
				 .append("\",\"hallMark\":\"")
				 .append(saleDt.getHallMarking())
				 .append("\",\"grading\":\"")
				 .append(saleDt.getGrading())
				 .append("\",\"lazerMark\":\"")
				 .append(saleDt.getLazerMarking())
				 .append("\",\"engraving\":\"")
				 .append(saleDt.getEngraving())
				 .append("\",\"rLock\":\"")
				 .append((saleDt.getrLock())); //1 = lock & 0 = unlock
				 
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				 .append("<a href='javascript:doSaleDtLockUnLock(")
				 .append(saleDt.getId())
				 .append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				 .append("\",\"action2\":\"")
				 .append("<a  href='javascript:deleteSaleDt(event,")
				 .append(saleDt.getId())
				 .append(");' class='btn btn-xs btn-danger triggerRemove")
				 .append(saleDt.getId())
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
	public String saleDtPickupListing(Integer mtId) {
		// TODO Auto-generated method stub
		SaleMt saleMt = saleMtService.findOne(mtId);
		List<SaleDt> saleDts = findBySaleMt(saleMt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 
		 try {
			 
			 sb.append("{\"total\":").append(saleDts.size()).append(",\"rows\": [");
			 
			 for(SaleDt saleDt :saleDts){
				 
				 if(Math.round(saleDt.getPcs() - saleDt.getAdjQty()) > 0) {
				 
					 List<SaleMetalDt> saleMetalDts = saleMetalDtService.findBySaleDt(saleDt);
					 String purityVal="";
					 for(SaleMetalDt saleMetalDt : saleMetalDts) {
						 if(purityVal.length()>0) {
							 purityVal=purityVal+","+saleMetalDt.getPurity().getName()+"-"+saleMetalDt.getColor().getName();
						 }else {
							 purityVal=saleMetalDt.getPurity().getName()+"-"+saleMetalDt.getColor().getName();
						 }
						 
					 }
				 
				 sb.append("{\"id\":\"")
				 .append(saleDt.getId())
				 .append("\",\"barcode\":\"")
				 .append(saleDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(saleDt.getDesign().getMainStyleNo())
				 .append("\",\"ktColor\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(saleDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(saleDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(saleDt.getNetWt())
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
	public SaleDt findByRefTranDtIdAndTranType(Integer refTranDtId, String tranType) {
		// TODO Auto-generated method stub
		return saleDtRepository.findByRefTranDtIdAndTranType(refTranDtId, tranType);
	}

	@Override
	public SaleDt findBySaleMtAndBarcode(SaleMt saleMt, String barcode) {
		// TODO Auto-generated method stub
		return saleDtRepository.findBySaleMtAndBarcode(saleMt, barcode);
	}

}

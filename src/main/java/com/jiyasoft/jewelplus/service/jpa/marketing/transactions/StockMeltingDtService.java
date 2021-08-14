package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QStockMeltingDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockMeltingDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockMeltingDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockMeltingMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StockMeltingDtService implements IStockMeltingDtService {
	
	@Autowired
	private IStockMeltingDtRepository stockMeltingDtRepository;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockMeltingMtService stockMeltingMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IMeltingIssDtService meltingIssDtService;
	

	@Override
	public void save(StockMeltingDt stockMeltingDt) {
		// TODO Auto-generated method stub
		
		stockMeltingDtRepository.save(stockMeltingDt);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		stockMeltingDtRepository.delete(id);
		
	}

	@Override
	public StockMeltingDt findOne(int id) {
		// TODO Auto-generated method stub
		return stockMeltingDtRepository.findOne(id);
	}

	@Override
	public List<StockMeltingDt> findByStockMeltingMt(StockMeltingMt stockMeltingMt) {
		// TODO Auto-generated method stub
		return stockMeltingDtRepository.findByStockMeltingMtOrderByIdDesc(stockMeltingMt);
	}
	
	@Override
	public String stockMeltingTransfer(Principal principal, String barcode, Integer stkMeltMtId) {
		// TODO Auto-generated method stub
		
		StockMeltingMt stockMeltingMt = stockMeltingMtService.findOne(stkMeltMtId);
		
		StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactiveAndLocation(barcode, true, false, stockMeltingMt.getLocation());
		
		if(stockMt !=null) {
			
			
			StockMeltingDt stockMeltingDt = new StockMeltingDt();
			stockMeltingDt.setBagId(stockMt.getBagId());
			stockMeltingDt.setBarcode(stockMt.getBarcode());
			stockMeltingDt.setCreatedBy(principal.getName());
			stockMeltingDt.setCreatedDt(new Date());
			stockMeltingDt.setStockMeltingMt(stockMeltingMt);
			stockMeltingDt.setStockMt(stockMt);
			stockMeltingDt.setPendApprovalFlg(true);
			stockMeltingDt.setCurrStk(true);
			
			save(stockMeltingDt);
			
			StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(stockMt.getBarcode(), true);
			StockTran stockTran2 = new StockTran();
			stockTran2.setBarcode(stockMt.getBarcode());
			stockTran2.setCreatedBy(principal.getName());
			stockTran2.setCreatedDate(new Date());
			stockTran2.setCurrStatus("In Transit");
			stockTran2.setCurrStk(true);
			stockTran2.setLocation(stockTran.getLocation());
			stockTran2.setTranDate(stockMeltingMt.getInvDate());
			stockTran2.setTranType("Melting");
			stockTran2.setRefTranId(stockMeltingDt.getId());
			stockTran2.setRefStkTranId(stockTran.getId());
			stockTran2.setParty(stockTran.getParty());
			stockTran2.setEmployee(stockTran.getEmployee());
			stockTran2.setStockMt(stockMt);
			stockTranService.save(stockTran2);
		
			
			stockTran.setCurrStk(false);
			stockTranService.save(stockTran);
			
			
		stockMt.setCurrStk(false);
		stockMtService.save(stockMt);
			
		

		return "1";
			
		}else {
			return "Barcode Not Found In Location";
		}
		
		//StockMt stockMt = stockMtService.findOne(Integer.parseInt(mtId[i].toString()));
				
				
			
	}

	@Override
	public String stockMeltingDtListing(Integer limit, Integer offset, String sort, String order, String name,Integer mtId) {
		
		//StockMeltingMt stockMeltingMt = stockMeltingMtService.findOne(mtId);
		//List<StockMeltingDt> stockMeltingDts = findByStockMeltingMt(stockMeltingMt);
		
		Page<StockMeltingDt>stockMeltingDts =searchAll(limit, offset, sort, order, name, mtId);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stockMeltingDts.getTotalElements()).append(",\"rows\": [");
		 
		 for(StockMeltingDt stockMeltingDt:stockMeltingDts){
			 
			 
			 
				String orderNo = null;
				String refNo = null;
				String bagNo = null;
				Integer bagId = 0;
				if(stockMeltingDt.getBagId() != null) {
				BagMt bagMt = bagMtService.findOne(stockMeltingDt.getBagId());
			
					orderNo = bagMt.getOrderMt().getInvNo();
					refNo = bagMt.getOrderDt().getRefNo();
					bagNo = bagMt.getName();
					bagId = bagMt.getId();
				
				}
				
				List<StockMetalDt> stockMetalDts = stockMetalDtService.findByStockMtAndDeactive(stockMeltingDt.getStockMt(), false);
				 String purityVal="";
				 for(StockMetalDt stockMetalDt:stockMetalDts) {
					 if(purityVal.length()>0) {
						 purityVal=purityVal+","+stockMetalDt.getPurity().getName()+"-"+stockMetalDt.getColor().getName();
					 }else {
						 purityVal=stockMetalDt.getPurity().getName()+"-"+stockMetalDt.getColor().getName();
					 }
				 }
				
				Integer totstone = 0;
				Double totCarat = 0.0;
				List<StockStnDt> stockStnDts = stockStnDtService.findByStockMtAndDeactive(stockMeltingDt.getStockMt(), false);
				for (StockStnDt stockStnDt : stockStnDts) {
					totstone += stockStnDt.getStone();
					totCarat += stockStnDt.getCarat();
				}
				
				
				
				//StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("Melting", stockMeltingDt.getId(), false);
				
				StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStatus("Melting", stockMeltingDt.getId(),"In Transit");
				
				
				
				sb.append("{\"party\":\"")
			     .append(stockTran.getParty() != null ? stockTran.getParty().getName() : "")
			     .append("\",\"orderNo\":\"")
				 .append(orderNo != null ? orderNo : "")
				 .append("\",\"refNo\":\"")
				 .append(refNo != null ? refNo : "")
				 .append("\",\"bagNo\":\"")
				 .append(bagNo != null ? bagNo : "")
				 .append("\",\"bagId\":\"")
				 .append(bagId != null ? bagId : "")
				 .append("\",\"styleNo\":\"")
				 .append(stockMeltingDt.getStockMt().getDesign() != null ? stockMeltingDt.getStockMt().getDesign().getMainStyleNo() : "")
				 .append("\",\"qty\":\"")
				 .append(stockMeltingDt.getStockMt() != null ?stockMeltingDt.getStockMt().getQty() : "")
				 .append("\",\"grossWt\":\"")
				 .append(stockMeltingDt.getStockMt() != null ?stockMeltingDt.getStockMt().getGrossWt() : "")
				 .append("\",\"image\":\"")
				 .append(stockMeltingDt.getStockMt().getDesign() != null ? stockMeltingDt.getStockMt().getDesign().getDefaultImage() : "")
				 .append("\",\"barcode\":\"")
				 .append(stockMeltingDt.getBarcode()!= null ? stockMeltingDt.getBarcode() : "")
				 .append("\",\"mtId\":\"")
				 .append(stockMeltingDt.getStockMeltingMt().getId())
				 .append("\",\"totCarat\":\"")
				 .append(Math.round((totCarat)*1000.0)/1000.0)
				 .append("\",\"totStone\":\"")
				 .append(totstone)
				 .append("\",\"purityVal\":\"")
				 .append(purityVal)
				 .append("\",\"action2\":\"")
				.append("<a href='javascript:deleteStockMeltingDt(event,");					
				sb.append(stockMeltingDt.getId());
				sb.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(stockMeltingDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
			
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
		return str;
	}

	@Override
	public String stockMeltingPickupListing(Boolean approvalFlg) {
		// TODO Auto-generated method stub
		
		List<StockMeltingDt> stockMeltingDts = findByPendApprovalFlgAndCurrStk(approvalFlg,true);
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stockMeltingDts.size()).append(",\"rows\": [");
		 
		 for(StockMeltingDt stockMeltingDt:stockMeltingDts){
			 
				String orderNo = null;
				String refNo = null;
				String bagNo = null;
				Integer bagId = 0;
				
				if(stockMeltingDt.getBagId() != null) {
				BagMt bagMt = bagMtService.findOne(stockMeltingDt.getBagId());
				if(bagMt != null) {
					orderNo = bagMt.getOrderMt().getInvNo();
					refNo = bagMt.getOrderDt().getRefNo();
					bagNo = bagMt.getName();
					bagId = bagMt.getId();
				}
				}
				List<StockMetalDt> stockMetalDts = stockMetalDtService.findByStockMtAndDeactive(stockMeltingDt.getStockMt(), false);
				 String purityVal="";
				 for(StockMetalDt stockMetalDt:stockMetalDts) {
					 if(purityVal.length()>0) {
						 purityVal=purityVal+","+stockMetalDt.getPurity().getName()+"-"+stockMetalDt.getColor().getName();
					 }else {
						 purityVal=stockMetalDt.getPurity().getName()+"-"+stockMetalDt.getColor().getName();
					 }
					 
				 }
				
				Integer totstone = 0;
				Double totCarat = 0.0;
				List<StockStnDt> stockStnDts = stockStnDtService.findByStockMtAndDeactive(stockMeltingDt.getStockMt(), false);
				for (StockStnDt stockStnDt : stockStnDts) {
					totstone += stockStnDt.getStone();
					totCarat += stockStnDt.getCarat();
				}
				
				
				
				//StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("Melting", stockMeltingDt.getId(), false);
				
				StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStatus("Melting", stockMeltingDt.getId(),"In Transit");
				
				
				
				sb.append("{\"party\":\"")
			     .append(stockTran.getParty() != null ? stockTran.getParty().getName() : "")
			     .append("\",\"orderNo\":\"")
				 .append(orderNo != null ? orderNo : "")
				 .append("\",\"refNo\":\"")
				 .append(refNo != null ? refNo : "")
				 .append("\",\"bagNo\":\"")
				 .append(bagNo != null ? bagNo : "")
				 .append("\",\"bagId\":\"")
				 .append(bagId != null ? bagId : "")
				 .append("\",\"styleNo\":\"")
				 .append(stockMeltingDt.getStockMt().getDesign() != null ? stockMeltingDt.getStockMt().getDesign().getMainStyleNo() : "")
				 .append("\",\"qty\":\"")
				 .append(stockMeltingDt.getStockMt() != null ?stockMeltingDt.getStockMt().getQty() : "")
				 .append("\",\"grossWt\":\"")
				 .append(stockMeltingDt.getStockMt() != null ?stockMeltingDt.getStockMt().getGrossWt() : "")
				 .append("\",\"image\":\"")
				 .append(stockMeltingDt.getStockMt().getDesign() != null ? stockMeltingDt.getStockMt().getDesign().getDefaultImage() : "")
				 .append("\",\"barcode\":\"")
				 .append(stockMeltingDt.getBarcode()!= null ? stockMeltingDt.getBarcode() : "")
				 .append("\",\"mtId\":\"")
				 .append(stockMeltingDt.getStockMeltingMt().getId())
				 .append("\",\"dtId\":\"")
				 .append(stockMeltingDt.getId())
				 .append("\",\"totCarat\":\"")
				 .append(Math.round((totCarat)*1000.0)/1000.0)
				 .append("\",\"totStone\":\"")
				 .append(totstone)
				 .append("\",\"purityVal\":\"")
				 .append(purityVal)
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
		return str;
	}

	@Override
	public List<StockMeltingDt> findByPendApprovalFlgAndCurrStk(Boolean pendApprovalFlg, Boolean currStk) {
		// TODO Auto-generated method stub
		return stockMeltingDtRepository.findByPendApprovalFlgAndCurrStk(pendApprovalFlg, currStk);
	}

	@Override
	public String stockMeltingApproval(Principal principal, String vDtId) {
		// TODO Auto-generated method stub
		
		String[] mtId = vDtId.split(",");
		for (int i = 0; i < mtId.length; i++) {
			
			StockMeltingDt stockMeltingDt = findOne(Integer.parseInt(mtId[i].toString()));
			stockMeltingDt.setPendApprovalFlg(false);
			stockMeltingDt.setModiBy(principal.getName());
			stockMeltingDt.setModiDt(new Date());
			save(stockMeltingDt);
			
			
			StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(stockMeltingDt.getBarcode(), true);
			StockTran stockTran2 = new StockTran();
			stockTran2.setBarcode(stockTran.getBarcode());
			stockTran2.setCreatedBy(principal.getName());
			stockTran2.setCreatedDate(new Date());
			stockTran2.setCurrStatus("Melting");
			stockTran2.setCurrStk(true);
			stockTran2.setLocation(stockTran.getLocation());
			stockTran2.setTranDate(new Date());
			stockTran2.setTranType("Melting");
			stockTran2.setRefTranId(stockMeltingDt.getId());
			stockTran2.setRefStkTranId(stockTran.getId());
			stockTran2.setParty(stockTran.getParty());
			stockTran2.setEmployee(stockTran.getEmployee());
			stockTran2.setStockMt(stockMeltingDt.getStockMt());
			stockTranService.save(stockTran2);
		
			
			stockTran.setCurrStk(false);
			stockTranService.save(stockTran);
			
			
			
			
		
		}
		
		return "1";
	}

	@Override
	public String stockMeltingDtDelete(Integer dtId,Principal principal) {
		// TODO Auto-generated method stub
		String retVal = "-1";
		
		StockMeltingDt stockMeltingDt = findOne(dtId);
		
		MeltingIssDt meltingIssDt = meltingIssDtService.findByTranTypeAndRefTranMetalIdAndDeactive("MELTINGISS", dtId, false);
		if(meltingIssDt != null) {
			retVal =  "-2";
		}else {
			
			StockMt stockMt = stockMtService.findOne(stockMeltingDt.getStockMt().getMtId());
			stockMt.setCurrStk(true);
			stockMtService.save(stockMt);
			
						
			List<StockTran>stockTrans=stockTranService.findByTranTypeAndRefTranId("Melting", dtId);
			
			for(StockTran stockTran2 :stockTrans) {
				
				StockTran stockTranNew = stockTranService.findOne(stockTran2.getRefStkTranId());
				stockTranNew.setCurrStk(true);
				stockTranService.save(stockTranNew);
				
			
				
			}
			
			
			for(StockTran stockTran2 :stockTrans) {
				stockTranService.delete(stockTran2.getId());
			}
			
			
		
			
			delete(dtId);
			retVal = "1";
		}
		
		
		return retVal;
	}

	@Override
	public Page<StockMeltingDt> searchAll(Integer limit, Integer offset, String sort, String order, String name,
			Integer mtId) {
		
		QStockMeltingDt qStockMeltingDt = QStockMeltingDt.stockMeltingDt;
		BooleanExpression expression = qStockMeltingDt.stockMeltingMt.id.eq(mtId);
		
		if(name !=null && name !=""){
			expression =qStockMeltingDt.stockMeltingMt.id.eq(mtId).and(qStockMeltingDt.Barcode.like("%"+name+"%"));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<StockMeltingDt> stockMeltingDtList = (Page<StockMeltingDt>) stockMeltingDtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));

		
		
		return stockMeltingDtList;
		
		
		
	}


}

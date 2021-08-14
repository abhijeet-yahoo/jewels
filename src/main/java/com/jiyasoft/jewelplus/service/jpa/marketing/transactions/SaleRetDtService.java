package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;


import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockCompDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMetalDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockStnDtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetDtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockTranRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
@Service
@Repository
@Transactional
public class SaleRetDtService implements ISaleRetDtService{

	@Autowired
	private ISaleRetDtRepository saleRetDtRepository;
	
	@Autowired
	private ISaleRetStnDtService saleRetStnDtService;
	
	@Autowired
	private ISaleRetMetalDtService saleRetMetalDtService;
	
	@Autowired
	private ISaleRetCompDtService  saleRetCompDtService;
	
	@Autowired
	private ISaleRetLabDtService saleRetLabDtService;
	
	@Autowired
	private ISaleDtService saleDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private ISaleRetMtService saleRetMtService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IDesignComponentService designComponentService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;

	@Autowired
	private IStockMtRepository stockMtRepository;
	
	@Autowired
	private IStockMetalDtRepository stockMetalDtRepository;
	
	@Autowired
	private IStockStnDtRepository stockStnDtRepository;
	
	@Autowired
	private IStockCompDtRepository stockCompDtRepository;
	
	@Autowired
	private IStockTranRepository stockTranRepository;
	
	
	@Override
	public void save(SaleRetDt saleRetDt) {
		
		 saleRetDtRepository.save(saleRetDt);
	}

	@Override
	public void delete(int id) {
		saleRetDtRepository.delete(id);
		
	}

	@Override
	public SaleRetDt findOne(int id) {
		
		return saleRetDtRepository.findOne(id);
	}

	@Override
	public List<SaleRetDt> findBySaleRetMt(SaleRetMt saleRetMt) {
		
		return saleRetDtRepository.findBySaleRetMt(saleRetMt);
	}

	@Override
	public String deleteSaleRetDt(Integer dtId) {
		
		SaleRetDt saleRetDt = findOne(dtId);
		
		List<StockTran> stockTrans = stockTranService.findByBarcode(saleRetDt.getBarcode());
		
		if(stockTrans.size() > 1) {
			

			StockMt stockMt =  stockMtService.findByBarcodeAndCurrStkAndDeactive(saleRetDt.getBarcode(),  true, false);
			if(stockMt == null) {
				
				return "Can not Delete, Qty Adjusted";
				
			}else {
				List<SaleRetStnDt> saleRetStnDts=saleRetStnDtService.findBySaleRetDt(saleRetDt);
				for(SaleRetStnDt saleRetStnDt :saleRetStnDts) {
					saleRetStnDtService.delete(saleRetStnDt.getId());
				}
				
				List<SaleRetMetalDt> saleRetMetalDts = saleRetMetalDtService.findBySaleRetDt(saleRetDt);
				for(SaleRetMetalDt saleRetMetalDt :saleRetMetalDts) {
					saleRetMetalDtService.delete(saleRetMetalDt.getId());
				}
				
				List<SaleRetCompDt> saleRetCompDts = saleRetCompDtService.findBySaleRetDt(saleRetDt);
				for(SaleRetCompDt saleRetCompDt :saleRetCompDts) {
					saleRetCompDtService.delete(saleRetCompDt.getId());
				}
				
				
				List<SaleRetLabDt> saleRetLabDts = saleRetLabDtService.findBySaleRetDt(saleRetDt);
				for(SaleRetLabDt saleRetLabDt :saleRetLabDts) {
					saleRetLabDtService.delete(saleRetLabDt.getId());
				}
				
				//SaleDt
				SaleRetDt saleRetDt2 = findByRefSaleDtId(saleRetDt.getRefSaleDtId());
				SaleDt saleDt = saleDtService.findOne(saleRetDt2.getRefSaleDtId());
				saleDt.setAdjQty(saleDt.getAdjQty() - saleRetDt2.getPcs());
				saleDtService.save(saleDt);
				
				
			
				StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("SALERET", saleRetDt.getId(), true);
				if(stockTran !=null) {
					
					
					
					StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
					stockTran2.setCurrStk(true);
					stockTranService.save(stockTran2);
					
					stockTranService.delete(stockTran.getId());
					
				}
				
				
				 stockMt.setCurrStk(false); 
				  stockMtService.save(stockMt);
					
				}
				
				
				delete(saleRetDt.getId());
			
				
				
			
			
		}else {
			
				
				List<SaleRetStnDt> saleRetStnDts=saleRetStnDtService.findBySaleRetDt(saleRetDt);
				for(SaleRetStnDt saleRetStnDt :saleRetStnDts) {
					saleRetStnDtService.delete(saleRetStnDt.getId());
				}
				
				List<SaleRetMetalDt> saleRetMetalDts = saleRetMetalDtService.findBySaleRetDt(saleRetDt);
				for(SaleRetMetalDt saleRetMetalDt :saleRetMetalDts) {
					saleRetMetalDtService.delete(saleRetMetalDt.getId());
				}
				
				List<SaleRetCompDt> saleRetCompDts = saleRetCompDtService.findBySaleRetDt(saleRetDt);
				for(SaleRetCompDt saleRetCompDt :saleRetCompDts) {
					saleRetCompDtService.delete(saleRetCompDt.getId());
				}
				
				
				List<SaleRetLabDt> saleRetLabDts = saleRetLabDtService.findBySaleRetDt(saleRetDt);
				for(SaleRetLabDt saleRetLabDt :saleRetLabDts) {
					saleRetLabDtService.delete(saleRetLabDt.getId());
				}
				
				
				
				//Stock Mt
				
				StockMt stockMt2 = stockMtService.findByRefTranIdAndTranTypeAndDeactive(saleRetDt.getId(), "SALERET", false);
				if(stockMt2 != null) {
					
					List<StockCompDt> stockCompDts = stockCompDtService.findByStockMtAndDeactive(stockMt2, false);
					for (StockCompDt stockCompDt : stockCompDts) {
						stockCompDtRepository.delete(stockCompDt.getStkCompId());
					}
					
					List<StockStnDt> stockStnDts = stockStnDtService.findByStockMtAndDeactive(stockMt2, false);
					for (StockStnDt stockStnDt : stockStnDts) {
						stockStnDtRepository.delete(stockStnDt.getStkStnId());
					}
					
					List<StockMetalDt> stockMetalDts = stockMetalDtService.findByStockMtAndDeactive(stockMt2, false);
					for (StockMetalDt stockMetalDt : stockMetalDts) {
						stockMetalDtRepository.delete(stockMetalDt.getStkMetalId());
					}
					
					stockMtRepository.delete(stockMt2.getMtId()); 
					
					StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("SALERET", saleRetDt.getId(), true);
					if(stockTran !=null) {
						stockTranRepository.delete(stockTran.getId());
					}
					
				}
				
				
				delete(saleRetDt.getId());
			
				
		}
		
		
		
		
		
		return "1";
	}

	@Override
	public String saleRetDtListing(Integer mtId,String disableFlg) {
		
		SaleRetMt saleRetMt = saleRetMtService.findOne(mtId);
		List<SaleRetDt> saleRetDts = findBySaleRetMt(saleRetMt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleRetDts.size()).append(",\"rows\": [");
		 Integer srNo=0;
		 for(SaleRetDt saleRetDt :saleRetDts){
			 
			 List<SaleRetMetalDt> saleRetMetalDts = saleRetMetalDtService.findBySaleRetDt(saleRetDt);
			 String purityVal="";
			 for(SaleRetMetalDt saleRetMetalDt : saleRetMetalDts) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+saleRetMetalDt.getPurity().getName()+"-"+saleRetMetalDt.getColor().getName();
				 }else {
					 purityVal=saleRetMetalDt.getPurity().getName()+"-"+saleRetMetalDt.getColor().getName();
				 }
				 
			 }
				
				sb.append("{\"srNo\":\"")
			     .append(++srNo)
			     .append("\",\"id\":\"")
				 .append(saleRetDt.getId())
				 .append("\",\"image\":\"")
				 .append(saleRetDt.getDesign().getDefaultImage() !=null?saleRetDt.getDesign().getDefaultImage():"")
			     .append("\",\"barcode\":\"")
				 .append(saleRetDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(saleRetDt.getDesign().getMainStyleNo())
				 .append("\",\"ktCol\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(saleRetDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(saleRetDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(saleRetDt.getNetWt())
				 .append("\",\"metalValue\":\"")
				 .append(saleRetDt.getMetalValue())
				 .append("\",\"stoneValue\":\"")
				 .append(saleRetDt.getStoneValue())
				 .append("\",\"compValue\":\"")
				 .append(saleRetDt.getCompValue())
				 .append("\",\"labourValue\":\"")
				 .append(saleRetDt.getLabValue())
				 .append("\",\"setValue\":\"")
				 .append(saleRetDt.getSetValue())
				 .append("\",\"handlingCost\":\"")
				 .append(saleRetDt.getHandlingValue())
				 .append("\",\"fob\":\"")
				 .append(saleRetDt.getFob())
				 .append("\",\"other\":\"")
				 .append(saleRetDt.getOther())
				 .append("\",\"discAmount\":\"")
				 .append(saleRetDt.getDiscAmount())
				 .append("\",\"finalPrice\":\"")
				 .append(saleRetDt.getFinalPrice())
				 .append("\",\"hallMark\":\"")
				 .append(saleRetDt.getHallMarking())
				 .append("\",\"grading\":\"")
				 .append(saleRetDt.getGrading())
				 .append("\",\"lazerMark\":\"")
				 .append(saleRetDt.getLazerMarking())
				 .append("\",\"engraving\":\"")
				 .append(saleRetDt.getEngraving())
				 .append("\",\"rLock\":\"")
				 .append((saleRetDt.getrLock())); //1 = lock & 0 = unlock
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				 .append("<a href='javascript:doSaleDtLockUnLock(")
				 .append(saleRetDt.getId())
				 .append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				 .append("\",\"action2\":\"")
				 .append("<a  href='javascript:deleteSaleRetDt(event,")
				 .append(saleRetDt.getId())
				 .append(");' class='btn btn-xs btn-danger triggerRemove")
				 .append(saleRetDt.getId())
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
	public SaleRetDt findByRefSaleDtId(Integer refSaleDtId) {
	
		return saleRetDtRepository.findByRefSaleDtId(refSaleDtId);
	}

	@Override
	public SaleRetDt findBySaleRetMtAndBarcode(SaleRetMt saleRetMt, String barcode) {
		// TODO Auto-generated method stub
		return saleRetDtRepository.findBySaleRetMtAndBarcode(saleRetMt, barcode);
	}

	@Override
	public String transactionalSave(SaleRetDt saleRetDt, Integer id, Integer saleRetMtIdPk, String metalDtData,
			Principal principal) {
		// TODO Auto-generated method stub
		String action = "";
		
		SaleRetMt saleRetMt = saleRetMtService.findOne(saleRetMtIdPk);
			
		Design design = null;
		
		
		Purity purity = purityService.findOne(saleRetDt.getPurity().getId());
		design =designService.findByMainStyleNoAndDeactive(saleRetDt.getDesign().getMainStyleNo(), false);
		
		saleRetDt.setDesign(design);
		saleRetDt.setSaleRetMt(saleRetMt);
	
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			saleRetDt.setCreatedBy(principal.getName());
			saleRetDt.setCreatedDate(new java.util.Date());
			
			action = "added";
			
		} else {
			
			SaleRetDt saleRetDtEdit = findOne(id);
	
			saleRetDtEdit.setModiBy(principal.getName());
			saleRetDtEdit.setModiDate(new java.util.Date());
						
			save(saleRetDtEdit);
			
			
			action = "updated";
			
		}
		
		saleRetDt.setPurityConv(purity.getPurityConv());
		
		saleRetDt.setRemark(saleRetDt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
		
		save(saleRetDt);
				
		if(action.equals("added")){
			
			
			
			saleRetMetalDtService.addSaleRetMetal(metalDtData, saleRetMt, saleRetDt, principal);
			
			List<DesignStone> designStones = designStoneService.findByDesign(design); 
			saleRetStnDtService.setSaleRetStnDt(designStones, saleRetMt, saleRetDt, principal);
			
			List<DesignComponent> designComponents = designComponentService.findByDesign(design);
			saleRetCompDtService.setSaleRetCompDt(designComponents, saleRetMt, saleRetDt, principal);
			
			
			 Double totNetWt=0.0;	
			 String ktDesc="";
			 List<SaleRetMetalDt> saleRetMetalDts = saleRetMetalDtService.findBySaleRetDt(saleRetDt);
			 if(saleRetMetalDts.size()>0){
				 for(SaleRetMetalDt saleRetMetalDt :saleRetMetalDts){
					 totNetWt  += saleRetMetalDt.getMetalWeight();	 
				 }
				 
			 }
			 
					
				List<SaleRetStnDt> saleRetStnDts = saleRetStnDtService.findBySaleRetDt(saleRetDt);
				Double totStnCarat = 0.0;
				for(SaleRetStnDt saleRetStnDt:saleRetStnDts){
					totStnCarat += saleRetStnDt.getCarat();
				}
				
				Double temVal = totStnCarat/5;
				Double totGrossWt = totNetWt+temVal;
				
				List<SaleRetCompDt> saleRetCompDts = saleRetCompDtService.findBySaleRetDt(saleRetDt);
				Double totCompMetalWt = 0.0;
				for(SaleRetCompDt saleRetCompDt : saleRetCompDts){
					totCompMetalWt += saleRetCompDt.getCompWt();
				}
				
				totGrossWt += totCompMetalWt;
				
				Double grossWtdiff = Math.round((saleRetDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
				
				SaleRetMetalDt saleRetMetalDt = saleRetMetalDtService.findBySaleRetDtAndMainMetal(saleRetDt, true);
				
				saleRetMetalDt.setMetalWeight(Math.round((saleRetMetalDt.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
				saleRetMetalDtService.save(saleRetMetalDt);
				
				
				
				StockMt stockMt =  new StockMt();
				stockMt.setBarcode(saleRetDt.getBarcode());
				stockMt.setLocation(saleRetMt.getLocation());
				stockMt.setMemoParty(saleRetMt.getParty());
				stockMt.setDesign(design);
				stockMt.setCategory(saleRetDt.getDesign().getCategory());
				stockMt.setMetalValue(saleRetDt.getMetalValue());
				stockMt.setLabourValue(saleRetDt.getLabValue());
				stockMt.setFactoryCost(saleRetDt.getFinalPrice());
				stockMt.setNetWt((saleRetDt.getNetWt()));
				stockMt.setGrossWt((saleRetDt.getGrossWt()));
				stockMt.setCreatedDate(new Date());
				stockMt.setCreatedBy(principal.getName());
				stockMt.setCurrStk(true);
				stockMt.setQty(saleRetDt.getPcs());
				stockMt.setRefTranId(saleRetDt.getId());
				stockMt.setTranType("SALERET");
				stockMtService.save(stockMt);
				
				
				
				//Dump record in stock table
				List<SaleRetMetalDt> saleRetMetalDts2 = saleRetMetalDtService.findBySaleRetDt(saleRetDt);
				for (SaleRetMetalDt saleRetMetalDt2 : saleRetMetalDts2) {
					
					StockMetalDt stockMetalDt =  new StockMetalDt();
					stockMetalDt.setPartNm(saleRetMetalDt2.getPartNm());
					stockMetalDt.setMainMetal(true);
					stockMetalDt.setMetalPcs(saleRetMetalDt2.getMetalPcs());
					stockMetalDt.setMetalWt(saleRetMetalDt2.getMetalWeight());
					stockMetalDt.setPurity(saleRetMetalDt2.getPurity());
					stockMetalDt.setColor(saleRetMetalDt2.getColor());
					stockMetalDt.setCreatedDate(new Date());
					stockMetalDt.setCreatedBy(principal.getName());
					stockMetalDt.setStockMt(stockMt);
					stockMetalDt.setMetalRate(saleRetMetalDt2.getMetalRate());
					stockMetalDtService.save(stockMetalDt);
				}
				
				List<SaleRetStnDt> saleRetStnDts2 = saleRetStnDtService.findBySaleRetDt(saleRetDt);
				for (SaleRetStnDt saleRetStnDt2 : saleRetStnDts2) {
					
					StockStnDt stockStnDt = new StockStnDt();
					stockStnDt.setStoneType(saleRetStnDt2.getStoneType());
					stockStnDt.setShape(saleRetStnDt2.getShape());
					stockStnDt.setQuality(saleRetStnDt2.getQuality());
					stockStnDt.setSize(saleRetStnDt2.getSize());
					stockStnDt.setSieve(saleRetStnDt2.getSieve());
					stockStnDt.setSizeGroup(saleRetStnDt2.getSizeGroup());
					stockStnDt.setStone(saleRetStnDt2.getStone());
					stockStnDt.setCarat(saleRetStnDt2.getCarat());
					stockStnDt.setRate(saleRetStnDt2.getStoneRate());
					stockStnDt.setDiamValue(saleRetStnDt2.getStoneValue());
					stockStnDt.setPartNm(saleRetStnDt2.getPartNm());
					stockStnDt.setStockMt(stockMt);
					
					
					stockStnDt.setAvgRate(saleRetStnDt2.getStoneRate());
					stockStnDt.setTransferRate(saleRetStnDt2.getStoneRate());
					stockStnDt.setFactoryRate(saleRetStnDt2.getStoneRate());
					
					
					stockStnDt.setCreatedDate(new Date());
					stockStnDt.setCreatedBy(principal.getName());
					
					stockStnDtService.save(stockStnDt);
				}
				
				List<SaleRetCompDt> saleRetCompDts2 =  saleRetCompDtService.findBySaleRetDt(saleRetDt);
				for (SaleRetCompDt saleRetCompDt2 : saleRetCompDts2) {
					
					StockCompDt stockCompDt =  new StockCompDt();
					stockCompDt.setColor(saleRetCompDt2.getColor());
					stockCompDt.setComponent(saleRetCompDt2.getComponent());
					stockCompDt.setCompQty(saleRetCompDt2.getCompQty());
					stockCompDt.setCreatedBy(principal.getName());
					stockCompDt.setCreatedDate(new Date());
					stockCompDt.setPurity(saleRetCompDt2.getPurity());
					stockCompDt.setPurityConv(saleRetCompDt2.getPurityConv());
					stockCompDt.setCompWt(saleRetCompDt2.getCompWt());
					stockCompDt.setStockMt(stockMt);
					stockCompDtService.save(stockCompDt);
					
				}
				
				

				StockTran stockTran = new StockTran();
				stockTran.setBarcode(saleRetDt.getBarcode());
				stockTran.setCreatedBy(principal.getName());
				stockTran.setCreatedDate(new Date());
				stockTran.setCurrStatus(saleRetMt.getLocation().getName());
				stockTran.setLocation(saleRetMt.getLocation());
				stockTran.setTranDate(new Date());
				stockTran.setTranType("OPSTK");
				stockTran.setCurrStk(true);
				stockTran.setRefTranId(saleRetDt.getId());
				stockTran.setTranDate(saleRetMt.getInvDate());
				stockTran.setTranType("SALERET");
				stockTran.setParty(saleRetMt.getParty());
				stockTran.setStockMt(stockMt);
				stockTranService.save(stockTran);
				
			
		
		}else{
			
			saleRetMetalDtService.addSaleRetMetal(metalDtData, saleRetMt, saleRetDt, principal);
			
			
			 Double totNetWt=0.0;	
			 List<SaleRetMetalDt> saleRetMetalDts = saleRetMetalDtService.findBySaleRetDt(saleRetDt);
			 if(saleRetMetalDts.size()>0){
				 for(SaleRetMetalDt saleRetMetalDt :saleRetMetalDts){
					 totNetWt  += saleRetMetalDt.getMetalWeight();	 
				 }
				 
			 }
			 
					
				List<SaleRetStnDt> saleRetStnDts = saleRetStnDtService.findBySaleRetDt(saleRetDt);
				Double totStnCarat = 0.0;
				for(SaleRetStnDt saleRetStnDt:saleRetStnDts){
					totStnCarat += saleRetStnDt.getCarat();
				}
				
				Double temVal = totStnCarat/5;
				Double totGrossWt = totNetWt+temVal;
				
				List<SaleRetCompDt> saleRetCompDts = saleRetCompDtService.findBySaleRetDt(saleRetDt);
				Double totCompMetalWt = 0.0;
				for(SaleRetCompDt saleRetCompDt : saleRetCompDts){
					totCompMetalWt += saleRetCompDt.getCompWt();
				}
				
				totGrossWt += totCompMetalWt;
				
				Double grossWtdiff = Math.round((saleRetDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
				
				SaleRetMetalDt saleRetMetalDt = saleRetMetalDtService.findBySaleRetDtAndMainMetal(saleRetDt, true);
				
				saleRetMetalDt.setMetalWeight(Math.round((saleRetMetalDt.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
				saleRetMetalDtService.save(saleRetMetalDt);
				
		}
		
		
	
		
		
	return action;
	}

}

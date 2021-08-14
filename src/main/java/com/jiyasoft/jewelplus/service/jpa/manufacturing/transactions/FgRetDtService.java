package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IFgRetDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgRetMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;

@Service
@Repository
@Transactional
public class FgRetDtService implements IFgRetDtService {

	@Autowired
	private IFgRetDtRepository fgRetDtRepository;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IFgRetMtService fgRetMtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;

	@Override
	public void save(FgRetDt fgRetDt) {
		// TODO Auto-generated method stub
		fgRetDtRepository.save(fgRetDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		fgRetDtRepository.delete(id);
	}

	@Override
	public FgRetDt findOne(int id) {
		// TODO Auto-generated method stub
		return fgRetDtRepository.findOne(id);
	}

	@Override
	public List<FgRetDt> findByFgRetMt(FgRetMt fgRetMt) {
		// TODO Auto-generated method stub
		return fgRetDtRepository.findByFgRetMt(fgRetMt);
	}

	@Override
	public String fgRetAddBarcodeItem(Integer mtId, String barcode, Principal principal,Integer locationId) {
		
		//StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(barcode, true, false);
		
		Department location =departmentService.findOne(locationId);
		
		StockMt stockMt =stockMtService.findByBarcodeAndCurrStkAndDeactiveAndLocation(barcode, true, false, location);
		
		if(stockMt != null && stockMt.getCurrStk().equals(true)) {
			
			FgRetMt fgRetMt = fgRetMtService.findOne(mtId);
			
			BagMt bagMt = bagMtService.findOne(stockMt.getBagId());
			bagMt.setBagCloseFlg(false);
			bagMtService.save(bagMt);
			
			
			TranMt tranMt = tranMtService.findByBagMtAndPendApprovalFlgAndCurrStk(bagMt, false, true);
			
			if(tranMt != null) {
			
			TranMt tranMtNew = new TranMt();
			
			tranMtNew.setBagMt(tranMt.getBagMt());
			tranMtNew.setPcs(tranMt.getPcs());
			tranMtNew.setCurrStk(true);
			tranMtNew.setTrandate(new java.util.Date());
			tranMtNew.setCreatedBy(principal.getName());
			tranMtNew.setCreatedDate(new java.util.Date());
			tranMtNew.setRecWt(tranMt.getRecWt());
			tranMtNew.setDepartment(fgRetMt.getLocation());
			tranMtNew.setDeptTo(fgRetMt.getToLocation());
			tranMtNew.setPendApprovalFlg(true);
			tranMtNew.setRepFlg(true);
		//	tranMtNew.setDeptFrom(tranMt.getDepartment());
			tranMtNew.setOrderMt(tranMt.getOrderMt());
			tranMtNew.setOrderDt(tranMt.getOrderDt());
			tranMtNew.setRefMtId(tranMt.getId());
			tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
			tranMtService.save(tranMtNew);
			
		
			
			
			List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
			for (TranDt tranDt : tranDts) {
				
				// adding the new record
				TranDt tranDtNew = new TranDt();
				tranDtNew.setBagMt(tranDt.getBagMt());
				tranDtNew.setPcs(tranDt.getPcs());
				tranDtNew.setCurrStk(true);
				tranDtNew.setCreatedBy(principal.getName());
				tranDtNew.setCreatedDate(new java.util.Date());
				tranDtNew.setDepartment(fgRetMt.getLocation());
				tranDtNew.setDeptTo(fgRetMt.getToLocation());
				tranDtNew.setRefDtId(tranDt.getId());
				tranDtNew.setBagSrNo(tranDt.getBagSrNo());
				tranDtNew.setCarat(tranDt.getCarat());
				tranDtNew.setSieve(tranDt.getSieve());
				tranDtNew.setSize(tranDt.getSize());
				tranDtNew.setStone(tranDt.getStone());
				tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
				tranDtNew.setTranDate(new java.util.Date());
				tranDtNew.setQuality(tranDt.getQuality());
				tranDtNew.setSetting(tranDt.getSetting());
				if (tranDt.getSettingType() == null) {
					tranDtNew.setSettingType(null);
				} else {
					tranDtNew.setSettingType(tranDt.getSettingType());
				}
				tranDtNew.setShape(tranDt.getShape());
				tranDtNew.setSizeGroup(tranDt.getSizeGroup());
				tranDtNew.setTranMt(tranMtNew);
				tranDtNew.setStoneType(tranDt.getStoneType());
				tranDtNew.setPartNm(tranDt.getPartNm());
				tranDtNew.setRate(tranDt.getRate());
				tranDtNew.setFactoryRate(tranDt.getFactoryRate());
				tranDtNew.setAvgRate(tranDt.getAvgRate());
				tranDtNew.setTransferRate(tranDt.getTransferRate());
				
				tranDtService.save(tranDtNew);

				// editing the existing record
				tranDt.setCurrStk(false);
				tranDt.setDeptTo(fgRetMt.getToLocation());
				tranDt.setIssDate(new java.util.Date());
				tranDt.setModiBy(principal.getName());
				tranDt.setModiDate(new java.util.Date());
				tranDtService.save(tranDt);
			}
			
			
			List<TranMetal> tranMetals = tranMetalService.findByTranMtAndCurrStk(tranMt, true);
			for (TranMetal tranMetal : tranMetals) {
				
				TranMetal tranMetal2 = new TranMetal();
				tranMetal2.setBagMt(tranMetal.getBagMt());
				tranMetal2.setColor(tranMetal.getColor());
				tranMetal2.setMainMetal(tranMetal.getMainMetal());
				tranMetal2.setCreatedBy(principal.getName());
				tranMetal2.setCreatedDate(new Date());
				tranMetal2.setCurrStk(true);
				tranMetal2.setDepartment(fgRetMt.getLocation());
				tranMetal2.setDeptTo(fgRetMt.getToLocation());
				tranMetal2.setMetalPcs(tranMetal.getMetalPcs());
				tranMetal2.setPartNm(tranMetal.getPartNm());
				tranMetal2.setPurity(tranMetal.getPurity());
				tranMetal2.setPurityConv(tranMetal.getPurity().getPurityConv());
				tranMetal2.setRefTranMetalId(tranMetal.getId());
				tranMetal2.setMetalWeight(tranMetal.getMetalWeight());
				tranMetal2.setTime(new java.sql.Time(new java.util.Date().getTime()));
				tranMetal2.setTranDate(new Date());
				tranMetal2.setTranMt(tranMtNew);
				tranMetal2.setMetalRate(tranMetal.getMetalRate());
				tranMetalService.save(tranMetal2);
				
				// tranmetal False record
				tranMetal.setCurrStk(false);
				tranMetal.setDeptTo(fgRetMt.getDepartment());
				tranMetal.setIssDate(new java.util.Date());
				tranMetal.setModiBy(principal.getName());
				tranMetal.setModiDate(new java.util.Date());
				tranMetalService.save(tranMetal);
			}
			
			
			
			//Dt Save
			FgRetDt fgRetDt = new FgRetDt();
			fgRetDt.setBagId(tranMt.getBagMt().getId());
			fgRetDt.setBarcode(tranMt.getBagMt().getBarcode());
			fgRetDt.setCreatedBy(principal.getName());
			fgRetDt.setCreatedDt(new Date());
			fgRetDt.setFgRetMt(fgRetMt);
			fgRetDt.setPendApprovalFlg(true);
			fgRetDt.setTranMtId(tranMtNew.getId());
			fgRetDt.setStockMt(stockMt);
			
			save(fgRetDt);
			
			//Old Id
			tranMt.setCurrStk(false);
			tranMt.setDeptTo(fgRetMt.getToLocation());
			tranMt.setIssWt(tranMt.getRecWt());
			tranMt.setIssueDate(new java.util.Date());
			tranMt.setModiBy(principal.getName());
			tranMt.setModiDate(new java.util.Date());
			tranMtService.save(tranMt);
			
			
			StockTran stockTr = stockTranService.findByBarcodeAndCurrStk(barcode, true);
			stockTr.setCurrStk(false);
			stockTr.setModiBy(principal.getName());
			stockTr.setModiDt(new Date());
			stockTranService.save(stockTr);
			
			
			
			StockTran stockTran = new StockTran();
			stockTran.setBarcode(stockMt.getBarcode());
			stockTran.setCreatedBy(principal.getName());
			stockTran.setCreatedDate(new Date());
			stockTran.setCurrStatus(fgRetMt.getToLocation().getName());
			stockTran.setLocation(fgRetMt.getToLocation());
			stockTran.setRefTranId(fgRetDt.getId());
			stockTran.setTranDate(fgRetMt.getInvDate());
			stockTran.setTranType("ISSUETOFACTORY");
			stockTran.setCurrStk(false);
			stockTran.setRefStkTranId(stockTr.getId());
			stockTran.setStockMt(stockMt);
			stockTranService.save(stockTran);
			
			}else {
				//Dump value from stockmt
				
				 
				
				TranMt tranMtNew = new TranMt();
				
				tranMtNew.setBagMt(bagMt);
				tranMtNew.setPcs(stockMt.getQty());
				tranMtNew.setCurrStk(true);
				tranMtNew.setTrandate(new java.util.Date());
				tranMtNew.setCreatedBy(principal.getName());
				tranMtNew.setCreatedDate(new java.util.Date());
				tranMtNew.setRecWt(stockMt.getGrossWt());
				tranMtNew.setDepartment(fgRetMt.getLocation());
				tranMtNew.setDeptTo(fgRetMt.getToLocation());
				tranMtNew.setPendApprovalFlg(true);
				tranMtNew.setOrderMt(bagMt.getOrderMt());
				tranMtNew.setOrderDt(bagMt.getOrderDt());
				tranMtNew.setRefMtId(null);
				tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
				tranMtNew.setRepFlg(true);
				tranMtService.save(tranMtNew);
				
			
				
				
				List<StockStnDt> stockStnDts = stockStnDtService.findByStockMtAndDeactive(stockMt, false);
				for (StockStnDt stockStnDt : stockStnDts) {
					
					// adding the new record
					TranDt tranDtNew = new TranDt();
					tranDtNew.setBagMt(bagMt);
					tranDtNew.setPcs(stockMt.getQty());
					tranDtNew.setCurrStk(true);
					tranDtNew.setCreatedBy(principal.getName());
					tranDtNew.setCreatedDate(new java.util.Date());
					tranDtNew.setDepartment(fgRetMt.getLocation());
					tranDtNew.setDeptTo(fgRetMt.getToLocation());
					tranDtNew.setRefDtId(stockStnDt.getStkStnId());
					tranDtNew.setBagSrNo(bagMt.getBarcodeSrNo());
					tranDtNew.setCarat(stockStnDt.getCarat());
					tranDtNew.setSieve(stockStnDt.getSieve());
					tranDtNew.setSize(stockStnDt.getSize());
					tranDtNew.setStone(stockStnDt.getStone());
					tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
					tranDtNew.setTranDate(new java.util.Date());
					tranDtNew.setQuality(stockStnDt.getQuality());
					tranDtNew.setSetting(stockStnDt.getSetting());
					if (stockStnDt.getSettingType() == null) {
						tranDtNew.setSettingType(null);
					} else {
						tranDtNew.setSettingType(stockStnDt.getSettingType());
					}
					tranDtNew.setShape(stockStnDt.getShape());
					tranDtNew.setSizeGroup(stockStnDt.getSizeGroup());
					tranDtNew.setTranMt(tranMtNew);
					tranDtNew.setStoneType(stockStnDt.getStoneType());
					tranDtNew.setPartNm(stockStnDt.getPartNm());
					tranDtNew.setRate(stockStnDt.getRate());
					tranDtNew.setFactoryRate(stockStnDt.getFactoryRate());
					tranDtNew.setAvgRate(stockStnDt.getAvgRate());
					tranDtNew.setTransferRate(stockStnDt.getTransferRate());
					
					tranDtService.save(tranDtNew);

				}
				
				
				List<StockMetalDt> stockMetalDts = stockMetalDtService.findByStockMtAndDeactive(stockMt, false);
				for (StockMetalDt stockMetalDt : stockMetalDts) {
					
					TranMetal tranMetal2 = new TranMetal();
					tranMetal2.setBagMt(bagMt);
					tranMetal2.setColor(stockMetalDt.getColor());
					tranMetal2.setMainMetal(stockMetalDt.getMainMetal());
					tranMetal2.setCreatedBy(principal.getName());
					tranMetal2.setCreatedDate(new Date());
					tranMetal2.setCurrStk(true);
					tranMetal2.setDepartment(fgRetMt.getLocation());
					tranMetal2.setDeptTo(fgRetMt.getToLocation());
					tranMetal2.setMetalPcs(stockMetalDt.getMetalPcs());
					tranMetal2.setPartNm(stockMetalDt.getPartNm());
					tranMetal2.setPurity(stockMetalDt.getPurity());
					tranMetal2.setPurityConv(stockMetalDt.getPurity().getPurityConv());
					tranMetal2.setRefTranMetalId(stockMetalDt.getStkMetalId());
					tranMetal2.setMetalWeight(stockMetalDt.getMetalWt());
					tranMetal2.setTime(new java.sql.Time(new java.util.Date().getTime()));
					tranMetal2.setTranDate(new Date());
					tranMetal2.setTranMt(tranMtNew);
					tranMetal2.setMetalRate(stockMetalDt.getMetalRate());
					tranMetalService.save(tranMetal2);
					
				
				}
				
				
				
				//Dt Save
				FgRetDt fgRetDt = new FgRetDt();
				fgRetDt.setBagId(stockMt.getBagId());
				fgRetDt.setBarcode(stockMt.getBarcode());
				fgRetDt.setCreatedBy(principal.getName());
				fgRetDt.setCreatedDt(new Date());
				fgRetDt.setFgRetMt(fgRetMt);
				fgRetDt.setPendApprovalFlg(true);
				fgRetDt.setTranMtId(tranMtNew.getId());
				fgRetDt.setStockMt(stockMt);
				
				save(fgRetDt);
				
				StockTran stockTr = stockTranService.findByBarcodeAndCurrStk(barcode, true);
				stockTr.setCurrStk(false);
				stockTr.setModiBy(principal.getName());
				stockTr.setModiDt(new Date());
				stockTranService.save(stockTr);
				
				
				
				StockTran stockTran = new StockTran();
				stockTran.setBarcode(stockMt.getBarcode());
				stockTran.setCreatedBy(principal.getName());
				stockTran.setCreatedDate(new Date());
				stockTran.setCurrStatus(fgRetMt.getToLocation().getName());
				stockTran.setLocation(fgRetMt.getToLocation());
				stockTran.setRefTranId(fgRetDt.getId());
				stockTran.setTranDate(fgRetMt.getInvDate());
				stockTran.setTranType("ISSUETOFACTORY");
				stockTran.setCurrStk(false);
				stockTran.setRefStkTranId(stockTr.getId());
				stockTran.setStockMt(stockMt);
				stockTranService.save(stockTran);
				
			}
			
			
			
			stockMt.setCurrStk(false);
			stockMtService.save(stockMt);
			

			
			return "1";
			
		}else {
			return "Item Not Found In Stock";
		}
		
		
	}

	@Override
	public String fgRetListing(Integer mtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fgRetDtDelete(Integer dtId, Principal principal) {
		// TODO Auto-generated method stub
		String retVal = "1";
		
		try {
			FgRetDt fgRetDt = findOne(dtId);
			if(!
					fgRetDt.getPendApprovalFlg()) {
				return "Can not delete, Approval done";
			}else {
				
				BagMt bagMt = bagMtService.findOne(fgRetDt.getBagId());
				bagMt.setBagCloseFlg(true);
				bagMtService.save(bagMt);
				
				TranMt tranMt = tranMtService.findOne(fgRetDt.getTranMtId());
				
				List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
				for (TranDt tranDt : tranDts) {
					tranDtService.delete(tranDt.getId());
					
				}
				
				List<TranMetal> tranMetals = tranMetalService.findByTranMtAndCurrStk(tranMt, true);
				for (TranMetal tranMetal : tranMetals) {
					tranMetalService.delete(tranMetal.getId());
				}
				
				if(tranMt.getRefMtId() != null) {
				TranMt tranMt2 = tranMtService.findByRefMtIdAndCurrStk(tranMt.getRefMtId(), false);
				
				List<TranDt> tranDts2 = tranDtService.findByTranMtAndCurrStk(tranMt2, true);
				for (TranDt tranDt : tranDts2) {
					
					// editing the existing record
					tranDt.setCurrStk(true);
		//			tranDt.setDeptTo(fgRetMt.getToLocation());
					tranDt.setIssDate(new java.util.Date());
					tranDt.setModiBy(principal.getName());
					tranDt.setModiDate(new java.util.Date());
					tranDtService.save(tranDt);
				}
				
				List<TranMetal> tranMetals2 = tranMetalService.findByTranMtAndCurrStk(tranMt2, true);
				for (TranMetal tranMetal : tranMetals2) {
					// tranmetal False record
					tranMetal.setCurrStk(true);
			//		tranMetal.setDeptTo(fgRetMt.getDepartment());
					tranMetal.setIssDate(new java.util.Date());
					tranMetal.setModiBy(principal.getName());
					tranMetal.setModiDate(new java.util.Date());
					tranMetalService.save(tranMetal);
				}
				
				
				if(tranMt2 != null) {
					//Old Id
					tranMt2.setCurrStk(true);
			//		tranMt.setDeptTo(fgRetMt.getToLocation());
					tranMt2.setIssWt(tranMt.getRecWt());
					tranMt2.setIssueDate(new java.util.Date());
					tranMt2.setModiBy(principal.getName());
					tranMt2.setModiDate(new java.util.Date());
					tranMtService.save(tranMt2);
					
				}
			}
				
				tranMtService.delete(tranMt.getId());
				
				
				
				StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("ISSUETOFACTORY", fgRetDt.getId(), false);
				
				
				StockTran stockTr = stockTranService.findOne(stockTran.getRefStkTranId());
				stockTr.setCurrStk(true);
				stockTr.setModiBy(principal.getName());
				stockTr.setModiDt(new Date());
				stockTranService.save(stockTr);
				
				stockTranService.delete(stockTran.getId());
				
				StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());
				stockMt.setCurrStk(true);
				stockMtService.save(stockMt);
				
				
				delete(dtId);
				
				
				
				retVal ="1";
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return retVal;
	}

	@Override
	public FgRetDt findByTranMtId(Integer tranMtId) {
		// TODO Auto-generated method stub
		return fgRetDtRepository.findByTranMtId(tranMtId);
	}
	

	

}

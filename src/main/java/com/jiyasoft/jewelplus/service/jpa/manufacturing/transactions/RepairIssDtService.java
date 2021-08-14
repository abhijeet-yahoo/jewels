package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IRepairIssDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
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
public class RepairIssDtService implements IRepairIssDtService {

	@Autowired
	private IRepairIssDtRepository repairIssDtRepository;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IRepairIssMtService repairIssMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;	
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private EntityManager entityManager;

	
	
	@Override
	public List<RepairIssDt> findAll() {
		// TODO Auto-generated method stub
		return repairIssDtRepository.findAll();
	}

	@Override
	public Page<RepairIssDt> findAll(Integer limit, Integer offset, String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return repairIssDtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(RepairIssDt repairIssDt) {
		repairIssDtRepository.save(repairIssDt);
		
	}

	@Override
	public void delete(int id) {
		RepairIssDt repairIssDt = repairIssDtRepository.findOne(id);
		repairIssDt.setDeactive(true);
		repairIssDt.setDeactiveDt(new java.util.Date());
		repairIssDtRepository.save(repairIssDt);
		
	}

	@Override
	public RepairIssDt findOne(int id) {
		// TODO Auto-generated method stub
		return repairIssDtRepository.findOne(id);
	}

	@Override
	public List<RepairIssDt> findByRepairIssMt(RepairIssMt repairIssMt) {
		// TODO Auto-generated method stub
		return repairIssDtRepository.findByRepairIssMt(repairIssMt);
	}

	@Override
	public Page<RepairIssDt> findByRepairIssMt(RepairIssMt repairIssMt, Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return repairIssDtRepository.findByRepairIssMt(repairIssMt,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}

	@Override
	public String repairTransferInStock(Principal principal, String vMtId, Integer repairIssMtId) {

		String[] tranMtId = vMtId.split(",");
		for (int i = 0; i < tranMtId.length; i++) {
			
			TranMt tranMt =tranMtService.findOne(Integer.parseInt(tranMtId[i].toString()));
			if(tranMt.getCurrStk().equals(true) && tranMt.getBagMt().getBagCloseFlg().equals(false)) {
				
				RepairIssMt repairIssMt = repairIssMtService.findOne(repairIssMtId);
				
				RepairIssDt repairIssDt = new RepairIssDt();
				repairIssDt.setBagId(tranMt.getBagMt().getId());
				repairIssDt.setBarcode(tranMt.getBagMt().getBarcode());
				repairIssDt.setCreatedBy(principal.getName());
				repairIssDt.setCreatedDt(new Date());
				repairIssDt.setRepairIssMt(repairIssMt);
				repairIssDt.setPendApprovalFlg(true);
				repairIssDt.setTranMtId(tranMt.getId());
				
				save(repairIssDt);
				
				
				
				//Stockmt code start
				/*
				 * StockMt stockMt2 =
				 * stockMtService.findByBarcodeAndCurrStkAndDeactive(repairIssDt.getBarcode(),
				 * true, false); if(stockMt2 != null) { stockMt2.setCurrStk(false);
				 * stockMt2.setModiBy(principal.getName()); stockMt2.setModiDt(new Date());
				 * stockMtService.save(stockMt2); }
				 */
				
				
				StockMt stockMt = new StockMt();
				stockMt.setBarcode(repairIssDt.getBarcode());
				stockMt.setCreatedBy(principal.getName());
				stockMt.setCreatedDate(new Date());
				stockMt.setCurrStk(false);
				stockMt.setDesign(tranMt.getOrderDt().getDesign());
				stockMt.setGrossWt(tranMt.getRecWt());
				stockMt.setLocation(repairIssDt.getRepairIssMt().getDepartment());
				stockMt.setQty(tranMt.getPcs());
				stockMt.setRefTranId(repairIssDt.getId());
				stockMt.setTranDate(new Date());
				stockMt.setTranType("repairIss");
				stockMt.setBagId(tranMt.getBagMt().getId());
				stockMt.setFromLocation(tranMt.getDepartment());
				
				stockMtService.save(stockMt);
				
				List<TranDt>tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
				Double totCarat=0.0;
				Double totDiaVal=0.0;
				for(TranDt tranDt :tranDts) {
					StockStnDt stockStnDt =new StockStnDt();
					stockStnDt.setCarat(tranDt.getCarat());
					stockStnDt.setCreatedBy(principal.getName());
					stockStnDt.setCreatedDate(new Date());
					stockStnDt.setQuality(tranDt.getQuality());
					stockStnDt.setSetting(tranDt.getSetting());
					stockStnDt.setSettingType(tranDt.getSettingType());
					stockStnDt.setShape(tranDt.getShape());
					stockStnDt.setSize(tranDt.getSize());
					stockStnDt.setSizeGroup(tranDt.getSizeGroup());
					stockStnDt.setStockMt(stockMt);
					stockStnDt.setStone(tranDt.getStone());
					stockStnDt.setStoneType(tranDt.getStoneType());
					stockStnDt.setSubShape(tranDt.getSubShape());
					stockStnDt.setPartNm(tranDt.getPartNm());
					stockStnDt.setSieve(tranDt.getSieve());
					stockStnDt.setCenterStone(tranDt.getCenterStone());
					
					stockStnDt.setRate(tranDt.getRate());
					stockStnDt.setAvgRate(tranDt.getAvgRate());
					stockStnDt.setTransferRate(tranDt.getTransferRate());
					stockStnDt.setFactoryRate(tranDt.getFactoryRate());
					stockStnDt.setDiamValue(Math.round((stockStnDt.getCarat()*stockStnDt.getFactoryRate())*100.0)/100.0);
					
						
						
				//	}
					
					
					stockStnDtService.save(stockStnDt);
					totCarat  +=stockStnDt.getCarat();
					totDiaVal +=stockStnDt.getDiamValue();
					
				}
				
				List<TranMetal>tranMetals = tranMetalService.findByTranMtAndCurrStk(tranMt, true);
				Double totMetalVal=0.0;
				for(TranMetal tranMetal :tranMetals) {
					
					StockMetalDt stockMetalDt = new StockMetalDt();
					stockMetalDt.setColor(tranMetal.getColor());
					stockMetalDt.setCreatedBy(principal.getName());
					stockMetalDt.setCreatedDate(new Date());
					stockMetalDt.setPurity(tranMetal.getPurity());
					stockMetalDt.setPurityConv(tranMetal.getPurityConv());
					stockMetalDt.setStockMt(stockMt);
					stockMetalDt.setMainMetal(tranMetal.getMainMetal());
					stockMetalDt.setMetalPcs(tranMetal.getMetalPcs());
					stockMetalDt.setPartNm(tranMetal.getPartNm());
					stockMetalDt.setMetalWt(tranMetal.getMetalWeight());
					stockMetalDt.setMetalRate(tranMetal.getMetalRate());
					stockMetalDt.setMetalValue(Math.round((stockMetalDt.getMetalWt()*stockMetalDt.getMetalRate())*100.0)/100.0);
				
					
					totMetalVal +=stockMetalDt.getMetalValue();
					stockMetalDtService.save(stockMetalDt);

					
					
					
				}
				
			
				
				List<CompTran> compTrans = compTranService.getCompTranListForCosting(tranMt.getBagMt().getId());
				
				Double notMetalComp=0.0;
				Double notMetalVal=0.0;
				
				for(CompTran compTran : compTrans){
					
					if(compTran.getComponent().getChargable().equals(false)){
						continue;
					}
					
					
					List<CompTran> cTran = compTranService.findByBagMtAndPurityAndColorAndComponent(compTran.getBagMt(),compTran.getPurity(), compTran.getColor(), compTran.getComponent());
				
					
						Double balance = 0.0;
						Double compPcs = 0.0;
						Double balValue=0.0;
					
						for(CompTran comp:cTran){
							balance += comp.getBalance(); 
							compPcs += comp.getBalancePcs();
							balValue +=Math.round((comp.getBalance()*comp.getMetalRate())*100.0)/100.0;
						}
						
						
						if(balance >= 0){
							continue;
						}
					
						StockCompDt stockCompDt = new StockCompDt();
						stockCompDt.setColor(compTran.getColor());
						stockCompDt.setComponent(compTran.getComponent());
						
						
						
						if(balance < 0){
							balance = -balance;
							compPcs = -compPcs;
							balValue =  -balValue;
						}
						
						stockCompDt.setCompQty(compPcs);
						stockCompDt.setCompWt(Math.round(balance*1000.0)/1000.0);
						stockCompDt.setCreatedBy(principal.getName());
						stockCompDt.setCreatedDate(new Date());
						stockCompDt.setPurity(compTran.getPurity());
						stockCompDt.setPurityConv(compTran.getPurityConv());
						stockCompDt.setStockMt(stockMt);
						stockCompDt.setMetalValue(Math.round(balValue*100.0)/100.0);
						stockCompDt.setMetalRate(Math.round((balValue/balance)*100.0)/100.0);
						
						if(compTran.getComponent().getNotMetalFlg().equals(true)){
							notMetalComp +=stockCompDt.getCompWt();
							notMetalVal += Math.round((stockCompDt.getMetalValue())*100.0)/100.0;
						}else {
							totMetalVal += Math.round((stockCompDt.getMetalValue())*100.0)/100.0;

						}
						
					stockCompDtService.save(stockCompDt);	
					
				
					
				} 
				
				
				
				totCarat=totCarat/5;
				
				stockMt.setNetWt(Math.round((stockMt.getGrossWt()-totCarat-notMetalComp)*1000.0)/1000.0);
		//		stockMt.setLabourValue(Math.round((stockMt.getNetWt()*600)*100.0)/100.0);
				stockMt.setMetalValue(Math.round((totMetalVal)*100.0)/100.0);
				stockMt.setStoneValue(Math.round((totDiaVal)*100.0)/100.0);
		//		stockMt.setFactoryCost(Math.round((stockMt.getMetalValue()+stockMt.getStoneValue()+stockMt.getLabourValue())*100.0)/100.0);
				stockMt.setOtherVlaue(Math.round((notMetalVal)*100.0)/100.0);
				stockMt.setOtherWt(Math.round((notMetalComp)*1000.0)/1000.0);
				
				List<Object[]> objects =new ArrayList<Object[]>();
				
				@SuppressWarnings("unchecked")
				TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_stockLabValue(?) }");
				query.setParameter(1, tranMt.getBagMt().getId());
				objects = query.getResultList();

				if(objects.size() > 0) {
				for(Object[] list:objects){
					stockMt.setGrading(Double.parseDouble(list[1].toString()));
					stockMt.setHallMarking(Double.parseDouble(list[2].toString()));
					stockMt.setLazerMarking(Double.parseDouble(list[3].toString()));
					stockMt.setEngraving(Double.parseDouble(list[5].toString()));
					
					if(Double.parseDouble(list[4].toString()) > 0) {
						stockMt.setLabourValue(Double.parseDouble(list[4].toString()));	
					}else {
						stockMt.setLabourValue(Math.round((stockMt.getNetWt()* tranMt.getBagMt().getOrderDt().getDesign().getCategory().getLabourRate())*100.0)/100.0);
					}
					
				}
				}else {
					stockMt.setLabourValue(Math.round((stockMt.getNetWt()* tranMt.getBagMt().getOrderDt().getDesign().getCategory().getLabourRate())*100.0)/100.0);
				}
				
				stockMt.setFactoryCost(Math.round((stockMt.getMetalValue()+stockMt.getStoneValue()+stockMt.getLabourValue()+stockMt.getGrading()+stockMt.getHallMarking()+
						stockMt.getLazerMarking()+stockMt.getEngraving()+stockMt.getOtherVlaue())*100.0)/100.0);
				
				stockMtService.save(stockMt);				
				
				Integer refStkId=null;
				StockTran stockTr = stockTranService.findByBarcodeAndCurrStk(repairIssDt.getBarcode(), true);
				if(stockTr != null) {
					refStkId = stockTr.getId();
					
					stockTr.setCurrStk(false);
					stockTr.setModiBy(principal.getName());
					stockTr.setModiDt(new Date());
					stockTranService.save(stockTr);
						
				}
				
				
				StockTran stockTran = new StockTran();
				stockTran.setBarcode(repairIssDt.getBarcode());
				stockTran.setCreatedBy(principal.getName());
				stockTran.setCreatedDate(new Date());
				stockTran.setCurrStatus("In Transit");
				stockTran.setLocation(repairIssDt.getRepairIssMt().getDepartment());
				stockTran.setRefTranId(repairIssDt.getId());
				stockTran.setTranDate(repairIssDt.getRepairIssMt().getInvDate());
				stockTran.setTranType("REPAIRISS");
				stockTran.setStockMt(stockMt);
				stockTran.setCurrStk(true);
				stockTran.setRefStkTranId(refStkId);
				stockTran.setStockMt(stockMt);
				stockTranService.save(stockTran);
				
				
				
				//Bagclose code
				BagMt bagMt =bagMtService.findOne(tranMt.getBagMt().getId());
				bagMt.setBagCloseFlg(true);
				bagMtService.save(bagMt);
				
			}
			
			
			
		}
		return "1";
	}

	@Override
	public String repairApproval(Principal principal, String vRepairIssDtIds) {
		String[] repairIssDtId = vRepairIssDtIds.split(",");
		for (int i = 0; i < repairIssDtId.length; i++) {
			
			RepairIssDt repairIssDt = findOne(Integer.parseInt(repairIssDtId[i].toString()));
			if(repairIssDt.getPendApprovalFlg().equals(true)) {
				
				 StockTran stockTran2 =  stockTranService.findByTranTypeAndRefTranIdAndCurrStatus("REPAIRISS",repairIssDt.getId(),"In Transit");
					if(stockTran2 != null) {
						StockTran stockTran = new StockTran();
						stockTran.setBarcode(repairIssDt.getBarcode());
						stockTran.setCreatedBy(principal.getName());
						stockTran.setCreatedDate(new Date());
						stockTran.setCurrStatus(stockTran2.getLocation().getName());
						stockTran.setLocation(stockTran2.getLocation());
						stockTran.setRefTranId(repairIssDt.getId());
						stockTran.setTranDate(stockTran2.getTranDate());
						stockTran.setTranType("REPAIRISS");
						stockTran.setCurrStk(true);
						stockTran.setStockMt(stockTran2.getStockMt());
						stockTran.setParty(stockTran2.getParty());
						stockTranService.save(stockTran);
						
						
						stockTran2.setCurrStk(false);
						stockTran2.setIssueDate(stockTran2.getTranDate());
						stockTranService.save(stockTran2);
						
						StockMt stockMt = stockMtService.findOne(stockTran2.getStockMt().getMtId());
						stockMt.setCurrStk(true);
						stockMt.setTranDate(new Date());
						stockMt.setModiBy(principal.getName());
						stockMt.setModiDt(new Date());
						stockMtService.save(stockMt);
				
			
					}
			
				
			}
			repairIssDt.setPendApprovalFlg(false);
			save(repairIssDt);
			
			
		}
		return "1";
	}

}

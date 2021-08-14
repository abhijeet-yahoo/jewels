package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostingMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class CostingMtService implements ICostingMtService {
	
	@Autowired
	private ICostingMtRepository costingMtRepository;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private ICostStnDtService costStnDtService;
	
	@Autowired
	private ICostCompDtService costCompDtService;
	
	@Autowired
	private ICostMetalDtService costMetalDtService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranDtService tranDtService;

	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ICostLabDtService costLabDtService;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ICostMetalDtItemService costMetalDtItemService;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private ICostStnDtItemService costStnDtItemService;
	
	@Autowired
	private ICostCompDtItemService costCompDtItemService;
	
	@Autowired
	private ICostLabDtItemService costLabDtItemService;
	
	@Autowired
	private IVAddDtService vadddtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Override
	public List<CostingMt> findAll() {
		return costingMtRepository.findAll();
	}

	@Override
	public Page<CostingMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return costingMtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(CostingMt costingMt) {
		costingMtRepository.save(costingMt);
		
	}

	@Override
	public void delete(int id) {
		CostingMt costingMt = costingMtRepository.findOne(id);
		costingMt.setDeactive(true);
		costingMt.setDeactiveDt(new java.util.Date());
		costingMtRepository.save(costingMt);
	}

	@Override
	public Long count() {
		return costingMtRepository.count();
	}

	@Override
	public CostingMt findOne(int id) {
		return costingMtRepository.findOne(id);
	}

	

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QCostingMt qCostingMt = QCostingMt.costingMt;
		BooleanExpression expression = qCostingMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qCostingMt.deactive.eq(false);
			} else if (colValue != null) {
				expression = qCostingMt.deactive.eq(false).and(
						qCostingMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invNo"))
					&& colValue != null) {

				expression = qCostingMt.invNo.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return costingMtRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return costingMtRepository.count();
			}
		}

		return costingMtRepository.count(expression);
		
	}

	@Override
	public Page<CostingMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name) {
	
		QCostingMt qCostingMt = QCostingMt.costingMt;
		BooleanExpression expression = qCostingMt.deactive.eq(false);

		if(name !=null && name !=""){
			expression = qCostingMt.deactive.eq(false).and(qCostingMt.invNo.like("%"+name+"%"));
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "invDate";
		}
			
		
		
		Page<CostingMt> costingMtList = (Page<CostingMt>) costingMtRepository.findAll(expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));

		return costingMtList;
	}

	@Override
	public CostingMt findByUniqueId(Long uniqueId) {
		return costingMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public CostingMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return costingMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}
	
	
	@Override
	public Page<CostingMt> getInvNoAutoFill(Integer limit, Integer offset,
			String sort, String order, String invNo, Boolean onlyActive) {
		
		QCostingMt qCostingMt = QCostingMt.costingMt;
		BooleanExpression expression = qCostingMt.deactive.eq(false);

		if (onlyActive) {
			if (invNo == null) {
				expression = qCostingMt.deactive.eq(false);
			} else {
				expression = qCostingMt.deactive.eq(false).and(qCostingMt.invNo.like(invNo + "%"));
			}
		} else {
			if (invNo != null) {
				expression = qCostingMt.invNo.like(invNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<CostingMt> costMtList = (Page<CostingMt>) costingMtRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
		
		return costMtList;
		
		
	}
	
	
	
	@Override
	public Map<Integer, String> getCostingList() {
		Map<Integer,String> costMap = new LinkedHashMap<Integer, String>();
		Page<CostingMt> costMtList = findActiveCostingSortByInvDate();
		for(CostingMt cost:costMtList){
			costMap.put(cost.getId(), cost.getInvNo());
		}
		return costMap;
	}
	
	
	@Override
	public Page<CostingMt> findActiveCostingSortByInvDate() {
		QCostingMt qCostingMt = QCostingMt.costingMt;
		BooleanExpression expression = qCostingMt.deactive.eq(false).and(qCostingMt.expClose.eq(false));
		Page<CostingMt> costingList = costingMtRepository.findAll(expression, new PageRequest(0, 10000, Direction.DESC, "invDate"));
		return costingList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public String costingTransfer(String fromInvoice, String toInvoice,
			String dtids,Principal principal) {
		CostingMt costingMtOld=findByInvNoAndDeactive(fromInvoice, false);
		CostingMt costingMtNew=findByInvNoAndDeactive(toInvoice, false);
		String retval="";
		if(costingMtOld !=null && costingMtNew !=null){
			
			String dtid[]=dtids.split(",");
			for(int i=0;i<dtid.length;i++){
				CostingDt costingDt=costingDtService.findOne(Integer.parseInt(dtid[i]));
				costingDt.setCostingMt(costingMtNew);
				costingDt.setModiBy(principal.getName());
				costingDt.setModiDate(new java.util.Date());
				costingDt.setTrfInvMtId(costingMtOld.getId());
				costingDt.setCostTransfer(true);
				costingDtService.save(costingDt);
				
				List<CostStnDt>costStnDts=costStnDtService.findByCostingDtAndDeactive(costingDt, false);
				for(CostStnDt costStnDt: costStnDts){
					costStnDt.setCostingMt(costingMtNew);
					costStnDt.setModiBy(principal.getName());
					costStnDt.setModiDate(new java.util.Date());
					costStnDtService.save(costStnDt);
				}
				
				List<CostCompDt>costCompDts=costCompDtService.findByCostingDtAndDeactive(costingDt, false);
				for(CostCompDt costCompDt:costCompDts){
					costCompDt.setCostingMt(costingMtNew);
					costCompDt.setModiBy(principal.getName());
					costCompDt.setModiDate(new java.util.Date());
					costCompDtService.save(costCompDt);
				}
				
				List<CostLabDt>costLabDts=costLabDtService.findByCostingDtAndDeactive(costingDt, false);
				for(CostLabDt costLabDt:costLabDts){
					costLabDt.setCostingMt(costingMtNew);
					costLabDt.setModiBy(principal.getName());
					costLabDt.setModiDate(new java.util.Date());
					costLabDtService.save(costLabDt);
				}
				
				
				
				
				if(i==(dtid.length-1)){
					retval="1";
				}
				
			}
			
		}
		
		
		
		
		return retval;
	}
	
	
	@Override
	public List<Object[]>partyWiseAndDateWiseListing(Integer limit, Integer offset, String sort, String order, String search,String partyIds,
			String fromDate, String toDate) throws ParseException {
		
		if((fromDate != null && !fromDate.isEmpty()) && (toDate != null && !toDate.isEmpty())){
			SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
			Date ordFromDate = dfInput.parse(fromDate);
			String frmDates = dfOutput.format(ordFromDate);
			
			Date ordToDate = dfInput.parse(toDate);
			String toDates = dfOutput.format(ordToDate);
			Query tempQuery ;
			if(partyIds !=null && !partyIds.isEmpty()) {
			
				if(search !=null && !search.isEmpty())
					 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt  where mtid in (select mtid from costdt where  ordpartyid in ("+partyIds+")) and deactive = 0 and invno like '"+search+"%' and cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' order by mtid desc limit "+limit+" offset "+offset);
					else
						tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt  where mtid in (select mtid from costdt where ordpartyid in ("+partyIds+")) and deactive = 0 and cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' order by mtid desc limit "+limit+" offset "+offset);	
					
			}else {
				
				if(search !=null && !search.isEmpty())
					 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt  where  deactive = 0 and invno like '"+search+"%' and cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' order by mtid desc limit "+limit+" offset "+offset);
					else
						tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt  where  deactive = 0 and cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' order by mtid desc limit "+limit+" offset "+offset);	
					
				
			}
				
				
						
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			
			return list;
			
		}else{
			Query tempQuery ;
			if(search !=null && !search.isEmpty())
			 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt where  partyid in ("+partyIds+") and deactive = 0 and invno like'"+search+"%' order by mtid desc limit "+limit+" offset "+offset);
			
			else
			 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt where  partyid in ("+partyIds+") and deactive = 0 order by mtid desc limit "+limit+" offset "+offset);
	
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			return list;
		}
		
	}

	@Override
	public String addBagInCosting(String pOIds, Integer costingMtId,Principal principal,Integer setNo,Boolean netWtWithComp) {
		
		String retVal = "-1";
		
		
		String[] data1 = pOIds.split(",");
				
		
		for(int i=0; i<data1.length; i++){
			
			 TranMt tranMt = tranMtService.findOne(Integer.parseInt(data1[i]));
			CostingMt costingMt = findOne(costingMtId);
			
			//new entry in CostDt
			
			CostingDt costingDt = new CostingDt();
			costingDt.setCostingMt(costingMt);
			costingDt.setDesign(tranMt.getOrderDt().getDesign());
			costingDt.setStyleNo(tranMt.getOrderDt().getDesign().getStyleNo());
			costingDt.setVersion(tranMt.getOrderDt().getDesign().getVersion());
			costingDt.setBagMt(tranMt.getBagMt());
			costingDt.setBagNm(tranMt.getBagMt().getName());
			costingDt.setParty(tranMt.getOrderDt().getOrderMt().getParty());
			costingDt.setPurity(tranMt.getOrderDt().getPurity());
			costingDt.setPurityNm(tranMt.getOrderDt().getPurity().getName());
			costingDt.setColor(tranMt.getOrderDt().getColor());
			costingDt.setColorNm(tranMt.getOrderDt().getColor().getName());
			costingDt.setOrderDt(tranMt.getOrderDt());
			costingDt.setDepartment(tranMt.getDepartment());
			costingDt.setGrossWt(tranMt.getRecWt());
			costingDt.setPcs(tranMt.getPcs());
			costingDt.setCreatedBy(principal.getName());
			costingDt.setCreatedDate(new java.util.Date());
			costingDt.setItemNo(tranMt.getBagMt().getItemNo());
			costingDt.setSetNo(setNo);
			
			costingDtService.save(costingDt);
			
			//Dump Value In BagMt
			BagMt bagMt = bagMtService.findOne(costingDt.getBagMt().getId());
			bagMt.setCostingFlg(true);
			bagMt.setCostingMtId(costingDt.getCostingMt().getId());
			bagMt.setModiBy(principal.getName());
			bagMt.setModiDate(new Date());
			bagMtService.save(bagMt);
			
			
			// new entry in CostStnDt
			List<TranDt>tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
			
			Double totCarat=0.0;
		
			
			for(TranDt tranDt:tranDts){
			
				CostStnDt costStnDt = new CostStnDt();
				costStnDt.setStoneType(tranDt.getStoneType());
					costStnDt.setCostingDt(costingDt);
					costStnDt.setCostingMt(costingMt);
					costStnDt.setShape(tranDt.getShape());
					costStnDt.setShapeNm(tranDt.getShape().getName());
					costStnDt.setQuality(tranDt.getQuality());
				
					if(tranDt.getQuality() != null) {
						costStnDt.setQualityNm(tranDt.getQuality().getName());	
					}
					
					costStnDt.setSizeGroup(tranDt.getSizeGroup());
					if(tranDt.getSizeGroup() != null ) {
						costStnDt.setSizeGroupNm((tranDt.getSizeGroup() != null ? tranDt.getSizeGroup().getName() : ""));	
					}
					
					costStnDt.setSetting(tranDt.getSetting());
					costStnDt.setSettingType(tranDt.getSettingType());
					costStnDt.setOrderDt(tranDt.getTranMt().getOrderDt());
					costStnDt.setBagSrNo(tranDt.getBagSrNo());
					costStnDt.setSize(tranDt.getSize());
					costStnDt.setSieve(tranDt.getSieve());
					costStnDt.setStone(tranDt.getStone());
					costStnDt.setCarat(tranDt.getCarat());
					costStnDt.setPartNm(tranDt.getPartNm());
					costStnDt.setCreatedBy(principal.getName());
					costStnDt.setCreatedDate(new java.util.Date());
					costStnDt.setCenterStone(tranDt.getCenterStone() !=null? tranDt.getCenterStone() :false);
					
					
					costStnDtService.save(costStnDt);
					
					
				totCarat  +=costStnDt.getCarat();
				
				
			} //ending CostStnDt for loop
			
			
			
			//CostMetalDt

			List<TranMetal> tranMtLists = tranMetalService.findByBagMtAndDepartmentAndCurrStk(tranMt.getBagMt(),tranMt.getDepartment(), true);
			Double totMetalWt = 0.0;
			for(TranMetal tranMetal:tranMtLists ) {
				
				CostMetalDt costMetalDt = new CostMetalDt();
				
				costMetalDt.setCostingMt(costingMt);
				costMetalDt.setCostingDt(costingDt);
				costMetalDt.setBagMt(tranMetal.getBagMt());
				costMetalDt.setPartNm(tranMetal.getPartNm());
				costMetalDt.setPurity(tranMetal.getPurity());
				costMetalDt.setDepartment(tranMetal.getDepartment());
				costMetalDt.setColor(tranMetal.getColor());
				costMetalDt.setMetalWeight(tranMetal.getMetalWeight());
				costMetalDt.setMetalPcs(tranMetal.getMetalPcs());
				costMetalDt.setCreateDate(new Date());
				costMetalDt.setCreatedBy(principal.getName());
				costMetalDt.setMainMetal(tranMetal.getMainMetal());					
				
				costMetalDtService.save(costMetalDt);
				totMetalWt += tranMetal.getMetalWeight();
				
				
			}
			
			
			
			
			
			//new entry in CostCompDt
			
			
			//List<CompTran> compTrans = compTranService.findByBagMtAndDeactive(tranMt.getBagMt(), false);
			
			List<CompTran> compTrans = compTranService.getCompTranListForCosting(tranMt.getBagMt().getId());
			
			Double totComWt = 0.0;
			
			for(CompTran compTran : compTrans){
				
				if(compTran.getComponent().getChargable().equals(false)){
					continue;
				}
				
				
				List<CompTran> cTran = compTranService.findByBagMtAndPurityAndColorAndComponent(compTran.getBagMt(),compTran.getPurity(), compTran.getColor(), compTran.getComponent());
			
				
					Double balance = 0.0;
					Double compPcs = 0.0;
				
					for(CompTran comp:cTran){
						balance += comp.getBalance(); 
						compPcs += comp.getBalancePcs();
					}
					
					
					if(balance >= 0){
						continue;
					}
				
					CostCompDt costCompDt = new CostCompDt();
					costCompDt.setCostingDt(costingDt);
					costCompDt.setCostingMt(costingMt);
					costCompDt.setComponent(compTran.getComponent());
					costCompDt.setPurity(compTran.getPurity());
					costCompDt.setColor(compTran.getColor());
					costCompDt.setOrderDt(tranMt.getOrderDt());
					costCompDt.setBagMt(compTran.getBagMt());
					
					if(balance < 0){
						balance = -balance;
						compPcs = -compPcs;
					}
					
					//System.out.println(balance);
					
					costCompDt.setMetalWt(Math.round(balance*1000.0)/1000.0);
					costCompDt.setPurityConv(compTran.getPurityConv());
					costCompDt.setCreatedBy(principal.getName());
					costCompDt.setCreatedDate(new java.util.Date());
					costCompDt.setCompPcs(compPcs);
				
					costCompDtService.save(costCompDt);
					
					totComWt += balance;
				
			} //ending the CostCompDt for loop
			
			
			//setting netWt of costDt
			
			/*
			 * totCarat=totCarat/5;
			 * 
			 * costingDt.setNetWt(Math.round((costingDt.getGrossWt()-totCarat)*1000.0)/1000.
			 * 0);
			 * 
			 * Double vNetDiff=
			 * Math.round((costingDt.getNetWt()-(totMetalWt+totComWt))*1000.0)/1000.0;
			 * 
			 * 
			 * costingDtService.save(costingDt);
			 * 
			 * CostMetalDt costMetalDt
			 * =costMetalDtService.findByCostingDtAndDeactiveAndMainMetal(costingDt, false,
			 * true);
			 * costMetalDt.setMetalWeight(Math.round((costMetalDt.getMetalWeight()+vNetDiff)
			 * *1000.0)/1000.0); costMetalDtService.save(costMetalDt);
			 */
		
			costingDtService.updateNetWt(costingDt, netWtWithComp);
			
			
		} // ending the main for loop
		
		retVal="1";
		return retVal;
	}

	@Override
	public String deleteCostingMtInvoice(Integer id, Principal principal) {
		
		String retVal="";
		
		CostingMt costingMt = findOne(id);
		if(costingMt.getExpClose().equals(true)) {
			
			return "Warn : Can't Delete Export Close";
			
		}
		
		List<VAddDt>vAddDts = vadddtService.findByCostingMtAndDeactive(costingMt, false);
		if(vAddDts.size()>0){
			return  "Warn :Can Not Delete Value Addition Created For This Invoice";
		}else{
			
			
			List<CostLabDtItem>costLabDtItems = costLabDtItemService.findByCostingMtAndDeactive(costingMt, false);
			
			for(CostLabDtItem costLabDtItem:costLabDtItems){
				costLabDtItemService.delete(costLabDtItem.getId());
			}
			
			List<CostCompDtItem>costCompDtItems =costCompDtItemService.findByCostingMtAndDeactive(costingMt, false);
			for(CostCompDtItem costCompDtItem :costCompDtItems){
				costCompDtItemService.delete(costCompDtItem.getId());
			}
			
			
			List<CostStnDtItem>costStnDtItems =costStnDtItemService.findByCostingMtAndDeactive(costingMt, false);
			for(CostStnDtItem costStnDtItem : costStnDtItems){
				costStnDtItemService.delete(costStnDtItem.getId());
			}
			
			List<CostMetalDtItem>costMetalDtItems =costMetalDtItemService.findByCostingMtAndDeactive(costingMt, false);
			for(CostMetalDtItem costMetalDtItem :costMetalDtItems){
				costMetalDtItemService.delete(costMetalDtItem.getId());
			}
			
			
			List<CostingDtItem> costingDtItems=costingDtItemService.findByCostingMtAndDeactive(costingMt, false);
			for (CostingDtItem costingDtItem : costingDtItems) {
				costingDtItemService.delete(costingDtItem.getId());
			}
			
			List<CostLabDt> costLabDts = costLabDtService.findByCostingMtAndDeactive(costingMt, false);
			for(CostLabDt costLabDt:costLabDts){
				costLabDtService.delete(costLabDt.getId());;
			}
			
			
			List<CostCompDt> costCompDts = costCompDtService.findByCostingMtAndDeactive(costingMt, false);
			for(CostCompDt costCompDt:costCompDts){
				costCompDtService.delete(costCompDt.getId());
			}
			
			List<CostStnDt> costStnDts = costStnDtService.findByCostingMtAndDeactive(costingMt, false);
			for(CostStnDt costStnDt:costStnDts){
				costStnDtService.delete(costStnDt.getId());
			}
			
			List<CostMetalDt> costMetalDts= costMetalDtService.findByCostingMtAndDeactive(costingMt, false);
			for (CostMetalDt costMetalDt : costMetalDts) {
				costMetalDtService.delete(costMetalDt.getId());
			}
			
			
			List<CostingDt> costingDts = costingDtService.findByCostingMtAndDeactive(costingMt, false);
			for(CostingDt costingDt:costingDts){
				
				costingDtService.delete(costingDt.getId());
				
				BagMt bagMt = costingDt.getBagMt();
				bagMt.setCostingFlg(false);
				bagMt.setCostingMtId(null);
				bagMtService.save(bagMt);
			}
			
			
			delete(id);
		
		
	}
		return retVal;
	}

	@Override
	public String exportCloseTransfer(Integer costMtId, Principal principal) {

		synchronized (this) {
			
			String retVal="-1";
			
			CostingMt costingMt  = findOne(costMtId);
			Department deptExport = departmentService.findByName("Export");
						
			if(costingMt != null){
				
				List<CostingDt> costingDtList = costingDtService.findByCostingMtAndDeactive(costingMt, false);
				List<Integer>exportBagList = new ArrayList<Integer>();
				String vBagNm="";

				for(CostingDt costingDt:costingDtList){
					
				
					
				//	TranMt tranMtOld = tranMtService.getBagForExportClose(costingDt.getBagMt().getId());
					
					List<TranMt>tranMts = tranMtService.findByBagMtAndCurrStk(costingDt.getBagMt(), true);
					TranMt tranMt =tranMts.get(0); 
					if(tranMts.size()>1) {
						
						if(vBagNm !=""){
							vBagNm +="<br />"+tranMt.getDepartment().getName()+"-"+costingDt.getBagNm();
						}else{
							vBagNm = tranMt.getDepartment().getName()+"-"+costingDt.getBagNm();
						}
						
					}else {
						
							//TranMt tranMt =tranMtService.findByBagMtIdAndCurrStk(costingDt.getBagMt().getId(), true);
						
						
						if(!tranMt.getDepartment().getName().equalsIgnoreCase("Export") && !tranMt.getDepartment().getProcess().equalsIgnoreCase("FG")) {
							
							if(vBagNm !=""){
								vBagNm +="<br />"+tranMt.getDepartment().getName()+"-"+costingDt.getBagNm();
							}else{
								vBagNm = tranMt.getDepartment().getName()+"-"+costingDt.getBagNm();
							}
						}else {
							exportBagList.add(costingDt.getBagMt().getId());
						}
						
					}
						
						
						
									
						
					
				}
				
				if(vBagNm==""){

					List<TranMt> tranMts = tranMtService.getTranMtListForExportClose(exportBagList);
					
					for(TranMt tranMt :tranMts) {
						
						if(tranMt.getDepartment().getName().equalsIgnoreCase("Export")) {
							continue;
						}
						
						//adding the new record
						TranMt tranMtNew = new TranMt();
						tranMtNew.setBagMt(tranMt.getBagMt());
						tranMtNew.setPcs(tranMt.getPcs());
						tranMtNew.setCurrStk(true);
						tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
						tranMtNew.setTrandate(costingMt.getInvDate());
						tranMtNew.setCreatedBy(principal.getName());
						tranMtNew.setCreatedDate(new java.util.Date());
						tranMtNew.setDepartment(deptExport);
						tranMtNew.setDeptFrom(tranMt.getDepartment());
						tranMtNew.setOrderMt(tranMt.getOrderMt());
						tranMtNew.setOrderDt(tranMt.getOrderDt());
						tranMtNew.setRefMtId(tranMt.getId());
						tranMtNew.setRecWt(tranMt.getRecWt());
						tranMtNew.setRemark("Export Close");
					/*	tranMtNew.setPurityConv((tranMtt.getOrderDt().getPurity().getPurityConv() == null ? 0.0 : tranMtt.getOrderDt().getPurity().getPurityConv()));*/
						tranMtService.save(tranMtNew);
					
						//editing the existing record
						tranMt.setCurrStk(false);
						tranMt.setDeptTo(deptExport);
						tranMt.setIssueDate(costingMt.getInvDate());
						tranMt.setModiBy(principal.getName());
						tranMt.setModiDate(new java.util.Date());
						tranMt.setIssWt(tranMt.getRecWt());
						//tranMtt.setLoss(Double.parseDouble(df.format(vRecWt-vIssWt)));

						tranMtService.save(tranMt);
						
						
						
						//---------TranMetal ------//----------//
						List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMt, true);
						for(TranMetal tranMetal :tranMetals){
								TranMetal tranMetal2 = new TranMetal();
								tranMetal2.setBagMt(tranMetal.getBagMt());
								tranMetal2.setColor(tranMetal.getColor());
								tranMetal2.setMainMetal(tranMetal.getMainMetal());
								tranMetal2.setCreatedBy(principal.getName());
								tranMetal2.setCreatedDate(new Date());
								tranMetal2.setCurrStk(true);
								tranMetal2.setDepartment(deptExport);
								tranMetal2.setDeptFrom(tranMetal.getDepartment());
								tranMetal2.setMetalPcs(tranMetal.getMetalPcs());
								tranMetal2.setRefTranMetalId(tranMetal.getId());
								tranMetal2.setMetalWeight(tranMetal.getMetalWeight());
								tranMetal2.setPartNm(tranMetal.getPartNm());
								tranMetal2.setPurity(tranMetal.getPurity());
								tranMetal2.setPurityConv(tranMetal.getPurity().getPurityConv());
								tranMetal2.setTime(new java.sql.Time(new java.util.Date().getTime()));
								tranMetal2.setTranDate(costingMt.getInvDate());
								tranMetal2.setMetalRate(tranMetal.getMetalRate());
								tranMetal2.setTranMt(tranMtNew);
								
								tranMetalService.save(tranMetal2);
								
								
								// tranmetal False record
								tranMetal.setCurrStk(false);
								tranMetal.setDeptTo(deptExport);
								
								tranMetal.setIssDate(costingMt.getInvDate());
								tranMetal.setModiBy(principal.getName());
								tranMetal.setModiDate(new java.util.Date());
								tranMetalService.save(tranMetal);
							}
							
	/*----------------------------------------------------------------------------------*/
						
						//---------TranDt------//----------//
						
						List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
						
						for(TranDt tranDt:tranDts){
									
																						
									//adding the new record
									
									TranDt tranDtNew = new TranDt();
									tranDtNew.setBagMt(tranDt.getBagMt());
									tranDtNew.setPcs(tranDt.getPcs());
									tranDtNew.setCurrStk(true);
									tranDtNew.setCreatedBy(principal.getName());
									tranDtNew.setCreatedDate(new java.util.Date());
									tranDtNew.setDepartment(deptExport);
									tranDtNew.setDeptFrom(tranDt.getDepartment());
									tranDtNew.setRefDtId(tranDt.getId());
									
									tranDtNew.setBagSrNo(tranDt.getBagSrNo());
									tranDtNew.setCarat(tranDt.getCarat());
									tranDtNew.setSieve(tranDt.getSieve());
									tranDtNew.setSize(tranDt.getSize());
									tranDtNew.setStone(tranDt.getStone());
									tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
									tranDtNew.setTranDate(costingMt.getInvDate());
									tranDtNew.setQuality(tranDt.getQuality());
									tranDtNew.setSetting(tranDt.getSetting());
									tranDtNew.setStoneType(tranDt.getStoneType());
									
									if(tranDt.getSettingType() == null){
										tranDtNew.setSettingType(null);
									}else{
										tranDtNew.setSettingType(tranDt.getSettingType());
									}
									
									tranDtNew.setShape(tranDt.getShape());
									tranDtNew.setSizeGroup(tranDt.getSizeGroup());
									tranDtNew.setTranMt(tranMtNew);
									
									tranDtNew.setPartNm(tranDt.getPartNm());
									tranDtNew.setRate(tranDt.getRate());
									tranDtNew.setFactoryRate(tranDt.getFactoryRate());
									tranDtNew.setAvgRate(tranDt.getAvgRate());
									tranDtNew.setTransferRate(tranDt.getTransferRate());
									
									tranDtService.save(tranDtNew);
									
									//editing the existing record
									
									tranDt.setCurrStk(false);
									tranDt.setDeptTo(deptExport);
									tranDt.setIssDate(costingMt.getInvDate());
									tranDt.setModiBy(principal.getName());
									tranDt.setModiDate(new java.util.Date());
									
									tranDtService.save(tranDt);
									
								}
							
							
						
						BagMt bagMt = tranMt.getBagMt();
						bagMt.setBagCloseFlg(true);
						bagMtService.save(bagMt);
						
						
						
						
					}
					
					retVal="1";
					
				}else{
					
					return vBagNm;
					
				}
				

				
				if(retVal == "1"){
				
					costingMt.setExpClose(true);
					costingMt.setModiBy(principal.getName());
					costingMt.setModiDate(new java.util.Date());
					save(costingMt);
					
					
					costingDtService.lockUnlockDt(costMtId, true);
					costStnDtService.lockUnlockStnDt(costMtId, true);
					costCompDtService.lockUnlockCompDt(costMtId,true);
					costLabDtService.lockUnlockLabDt(costMtId, true);
					
				}
				
				
			
						}
			
			
			return retVal;
			
		}
		
		
		

		
	}

	@Override
	public List<Object[]> exportInvoiceAllPartyWiseAndDateWiseListing(String search, String partyIds, String fromDate,
			String toDate) throws ParseException {
			
		
		if((fromDate != null && !fromDate.isEmpty()) && (toDate != null && !toDate.isEmpty())){
			SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
			Date ordFromDate = dfInput.parse(fromDate);
			String frmDates = dfOutput.format(ordFromDate);
			
			Date ordToDate = dfInput.parse(toDate);
			String toDates = dfOutput.format(ordToDate);
			Query tempQuery ;
			if(partyIds !=null && !partyIds.isEmpty()) {
			
				if(search !=null && !search.isEmpty())
					 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt  where partyid in ("+partyIds+") and deactive = 0 and invno like '"+search+"%' and cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' order by mtid");
					else
						tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt  where partyid in ("+partyIds+") and deactive = 0 and cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' order by mtid");	
					
			}else {
				
				if(search !=null && !search.isEmpty())
					 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt  where  deactive = 0 and invno like '"+search+"%' and cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' order by mtid");
					else
						tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt  where  deactive = 0 and cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' order by mtid");	
					
				
			}
				
				
						
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			
			return list;
			
		}else{
			Query tempQuery ;
			if(search !=null && !search.isEmpty())
			 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt where  partyid in ("+partyIds+") and deactive = 0 and invno like'"+search+"%' order by mtid");
			
			else
			 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from costmt where  partyid in ("+partyIds+") and deactive = 0 order by mtid");
	
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			return list;
		}
	}

	@Override
	public String updateDiaWtAsPerOrder(Integer costMtId) {
		// TODO Auto-generated method stub
		
		Query query = entityManager.createNativeQuery("update coststndtitem a,sordstndt b,costdtitem c set a.carat = b.carat,a.tagwt = b.carat where c.sorddtid = b.dtid" + 
				" and a.deactive =0 and b.deactive =0 and a.partnm = b.partnm and a.stonetypeid = b.stonetypeid" + 
				" and a.shapeid=b.shapeid and a.qualityid = b.qualityid and a.size = b.size " + 
				" and a.settingid = b.settingid and a.settypeid =b.settypeid and a.rlock=0 and a.dtitemid = c.dtitemid and a.mtid ="+costMtId);
		
	     query.executeUpdate();
		return "1";
	}
	
}	




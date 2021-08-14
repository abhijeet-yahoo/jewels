package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QBagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IBagMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBarcodeDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBarcodeMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class BagMtService implements IBagMtService {

	@Autowired
	private IBagMtRepository bagMtRepository;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IReadyBagService readyBagService;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IOrderMtService orderMtService; ;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IBarcodeDtService barcodeDtService;
	
	@Autowired
	private IBarcodeMtService barcodeMtService;
	
	@Autowired
	private IOrderMetalService orderMetalService;

	@Override
	public List<BagMt> findAll() {
		return bagMtRepository.findAll();
	}

	@Override
	public Page<BagMt> findAll(Integer limit, Integer offset, String sort,
			String bagMt, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return bagMtRepository.findAll(new PageRequest(page, limit, (bagMt
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(BagMt bagMt) {
		bagMtRepository.save(bagMt);
	}

	@Override
	public void delete(int id) {
//		BagMt bagMt = bagMtRepository.findOne(id);
//		bagMt.setDeactive(true);
//		bagMt.setDeactiveDt(new java.util.Date());
//		bagMtRepository.save(bagMt);

		bagMtRepository.delete(id);
	}

	@Override
	public Long count() {
		return bagMtRepository.count();
	}

	@Override
	public BagMt findOne(int id) {
		return bagMtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getBagMtList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagMt findByName(String name) {
		return bagMtRepository.findByName(name);
	}

	@Override
	public List<BagMt> findByOrderMt(OrderMt orderMt) {
		return bagMtRepository.findByOrderMt(orderMt);
	}

	@Override
	public List<BagMt> findByOrderMtAndOrderDt(OrderMt orderMt, OrderDt orderDt) {
		return bagMtRepository.findByOrderMtAndOrderDt(orderMt, orderDt);
	}

	@Override
	public List<BagMt> findActivebags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QBagMt qBagMt = QBagMt.bagMt;
		BooleanExpression expression = qBagMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qBagMt.deactive.eq(false);
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("bagMtNo")) && colValue != null) {
				 expression = qBagMt.orderMt.invNo.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return bagMtRepository.count();
			} else {
				return bagMtRepository.count();
			}
		}

		return bagMtRepository.count(expression);
	}


	public Page<BagMt> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QBagMt qBagMt = QBagMt.bagMt;
		BooleanExpression expression = qBagMt.deactive.eq(false);

		if (onlyActive) {
			if (name == null) {
				expression = qBagMt.deactive.eq(false);
			} else {
				expression = qBagMt.deactive.eq(false).and(qBagMt.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qBagMt.name.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<BagMt> bagMtList = (Page<BagMt>) bagMtRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return bagMtList;

	}




	public Integer getMaxSrno() {
		QBagMt qBagMt = QBagMt.bagMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qBagMt)
			.where(qBagMt.deactive.eq(false).and(qBagMt.createdDate.year().eq(date.get(Calendar.YEAR)))).list(qBagMt.seqNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	public Double getGenQty(Integer orderMtId, Integer orderDtId) {
		QBagMt qBagMt = QBagMt.bagMt;

		JPAQuery query = new JPAQuery(entityManager);
		Double retVal = -1.0;

		List<Double> genQtyList = query
			.from(qBagMt)
			.where(qBagMt.deactive.eq(false).and(qBagMt.orderMt.id.eq(orderMtId).and(qBagMt.orderDt.id.eq(orderDtId))))
			.list(qBagMt.qty.sum());

		for (Double qty : genQtyList) {
			retVal = (qty == null ? 0 : qty);
		}

		return retVal;
	}

	@Override
	public String getMaxItemno() {
		
		QBagMt qBagMt = QBagMt.bagMt;
		JPAQuery query = new JPAQuery(entityManager);
		String retVal = "";

		List<String> maxItemno = query
			.from(qBagMt)
			.where(qBagMt.deactive.eq(false))
			.list(qBagMt.itemNo.max());

		for (String itemNo : maxItemno) {
			retVal = (itemNo == null ? "" : itemNo);
		}
		

		return retVal;
	}

	@Override
	public List<BagMt> findByItemNo(String itemNo) {
		return bagMtRepository.findByItemNo(itemNo);
	}

	
	
	@Override
	public List<BagMt> findBagsByOrderMt(String orderIds) {
		
		List<Integer> ordIdList = new ArrayList<Integer>();
		
		if(orderIds.length() > 0){
			String ids[] = orderIds.split(",");
			for(int i=0;i<ids.length;i++){
				ordIdList.add(Integer.parseInt(ids[i]));
			}
		}else{
			ordIdList.add(0);
		}
		
		
		for(Integer i:ordIdList){
			System.out.println(i);
		}
		
		

		QBagMt qBagMt = QBagMt.bagMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<BagMt> bagMts = null;
		
		bagMts = query.from(qBagMt)
				.where(qBagMt.deactive.eq(false).and(qBagMt.orderMt.id.in(ordIdList))).orderBy(qBagMt.name.asc()).list(qBagMt);
		
		return bagMts;
	}

	@Override
	public BagMt findByItemNoAndDeactive(String itemNo, Boolean deactive) {
		return bagMtRepository.findByItemNoAndDeactive(itemNo, deactive);
	}

	@Override
	public String bagSplit(String bagNo, Double bagQty, Double splitQty, Double weight,
			Principal principal,Double splitWeight,String barcodeuploadFilePath) {
		
		String retVal="";
		
	/*	if(splitWeight==null){
			splitWeight=0.0;
		}
		
		BagMt bagMt =findByName(bagNo);

		List<TranMt>tranMts=tranMtService.findByBagMtAndCurrStkAndDeactive(bagMt, true,false);
		if(tranMts.size()>1){
			return "error :Can't Split As Bag Found In Multiple Location ";
		}else{
			TranMt tranMt =tranMtService.findByBagMtAndCurrStk(bagMt, true);
			
			if(tranMt.getRecWt()>0){
				
				 if (splitWeight == 0){
					return "error : Can Not Split Bag With Zero Weight";
				}		
				else if(splitWeight >= weight){
						return "error : Can Not Split Bag With GreaterThan Or Same Weight.";
					}
				
				
				
			}
		}*/
		
		
	try {

		if(splitWeight==null){
			splitWeight=0.0;
		}
		
		if(splitQty == null){
			return "error : Split Qty Can Not Be Blank ! ";
		}else if(weight > 0){
				
				 if (splitWeight == 0){
					return "error : Can Not Split Bag With Zero Weight";
				}		
				else if(splitWeight >= weight){
						return "error : Can Not Split Bag With GreaterThan Or Same Weight.";
					}
				}
		
		if(splitQty >=bagQty){
			return "error : Can Not Split Bag With GreaterThan Or Same Qty.";
		}else{
		
			
			BagMt bagMt =findByName(bagNo);
			BagMt bagMtNew = null;
			
			List<TranMt>tranMts=tranMtService.findByBagMtAndCurrStk(bagMt,true);
			if(tranMts.size()>1){
				return "error :Can't Split As Bag Found In Multiple Location ";
			}
			
			for (TranMt tranMt : tranMts) {
			
				if(tranMt.getDepartment().getName().equalsIgnoreCase("Casting")) {
					return "error :Can Not Split Casting Department Bag"; 
				}else {
					
					

					Calendar date = new GregorianCalendar();
					String vYear = "" + date.get(Calendar.YEAR);
					vYear = vYear.substring(2);
					
					
					Integer maxSrno = null;
					synchronized(this) {
						maxSrno = getMaxSrno();
						maxSrno = (maxSrno == null ? 0 : maxSrno);

						bagMtNew = new BagMt();
						bagMtNew.setSeqNo(++maxSrno);
						
						int si = maxSrno.toString().length();
						String bagSr = null;
						switch (si) {
									case 1:
										bagSr = "000"+maxSrno;
										break;
										
									case 2:
										bagSr = "00"+maxSrno;
										break;
										
									case 3:
										bagSr = "0"+maxSrno;
										break;
										
									default:
										bagSr = maxSrno.toString();
										break;
									}
									if(bagMt.getOrderMt().getOrderType().getBagPrefix() !=null && !bagMt.getOrderMt().getOrderType().getBagPrefix().isEmpty()){
									
									bagMtNew.setName(bagMt.getOrderMt().getOrderType().getBagPrefix() + "-" + (bagSr) + "-" + vYear);
									}else{
										
										bagMtNew.setName(bagSr + "-" + vYear);
										
									}
									
									bagMtNew.setOrderMt(bagMt.getOrderMt());
									bagMtNew.setOrderDt(bagMt.getOrderDt());
									bagMtNew.setQty(splitQty);
									bagMtNew.setCreatedBy(principal.getName());
									bagMtNew.setCreatedDate(new java.util.Date());
									
									if(bagMt.getMainParentBagNm()==null ||bagMt.getMainParentBagNm()==""){
										bagMtNew.setMainParentBagNm(bagMt.getName());
										bagMtNew.setParentBagNm(bagMt.getName());
									}else{
										bagMtNew.setMainParentBagNm(bagMt.getMainParentBagNm());
										bagMtNew.setParentBagNm(bagMt.getName());
									}
									
									save(bagMtNew);
									
									
									Utils.barcodeGeneration(bagMtNew.getId(), bagMtNew.getName(), barcodeuploadFilePath);
									

									bagMt.setSplit(true);
									bagMt.setQty(bagMt.getQty()-splitQty);
							
									save(bagMt);
									
									
									
									
									
									Double vDiaWt=0.0;
									Double vCompWt=0.0;
									

									List<TranDt>tranDts =tranDtService.findByBagMtAndCurrStk(bagMt, true);
									for(TranDt tranDt :tranDts){
										vDiaWt +=Math.round(((tranDt.getCarat()/tranDt.getPcs())*splitQty)*1000.0)/1000.0;
									}
									
									vDiaWt=Math.round((vDiaWt/5)*1000.0)/1000.0;
									
									QCompTran qCompTran = QCompTran.compTran;
									List<Tuple> tupleList = compTranService.getCompTranTupleList(bagMt.getId());
									Double compWt1=0.0;
									for(Tuple tuple:tupleList){
										
										if(tuple.get(qCompTran.balance.sum())>=0){
											continue;
										}else{
									
											compWt1=-tuple.get(qCompTran.balance.sum());
											
											vCompWt += Math.round(((compWt1/tranMt.getPcs())*splitQty)*1000.0)/1000.0;
										}
									}
									
									TranMt tranMtNew = new TranMt();
									tranMtNew.setBagMt(bagMtNew);
									tranMtNew.setCreatedBy(principal.getName());
									tranMtNew.setCreatedDate(new Date());
									tranMtNew.setCurrStk(true);
									tranMtNew.setTrandate(new Date());
									tranMtNew.setDepartment(tranMt.getDepartment());
									tranMtNew.setDeptFrom(tranMt.getDeptFrom());
									tranMtNew.setDeptTo(tranMt.getDeptTo());
									tranMtNew.setOrderDt(tranMt.getOrderDt());
									tranMtNew.setOrderMt(tranMt.getOrderMt());
									tranMtNew.setPcs(splitQty);
									tranMtNew.setRecWt(splitWeight);
									tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
									tranMtNew.setRefMtId(tranMt.getId());
									tranMtService.save(tranMtNew);
									
									TranMt tranMt2 = new TranMt();
									tranMt2.setBagMt(tranMt.getBagMt());
									tranMt2.setCreatedBy(principal.getName());
									tranMt2.setCreatedDate(new Date());
									tranMt2.setCurrStk(true);
									tranMt2.setTrandate(new Date());
									tranMt2.setDepartment(tranMt.getDepartment());
									tranMt2.setDeptFrom(tranMt.getDeptFrom());
									tranMt2.setDeptTo(tranMt.getDeptTo());
									tranMt2.setOrderDt(tranMt.getOrderDt());
									tranMt2.setOrderMt(tranMt.getOrderMt());
									tranMt2.setPcs(Math.round((tranMt.getPcs()-splitQty)*100.0)/100.0);
									tranMt2.setRecWt(Math.round((tranMt.getRecWt()-splitWeight)*1000.0)/1000.0);
									tranMt2.setTime(new java.sql.Time(new java.util.Date().getTime()));
									tranMt2.setRefMtId(tranMt.getId());
								
									tranMtService.save(tranMt2);
									
									
									tranMt.setCurrStk(false);
									tranMt.setIssueDate(new Date());
									tranMt.setDeptTo(tranMt.getDepartment());
									tranMt.setIssWt(Math.round((tranMt.getRecWt()-splitWeight)*1000.0)/1000.0);
									tranMt.setModiBy(principal.getName());
									tranMt.setModiDate(new Date());
									tranMtService.save(tranMt);
									
									
									
									
									
									
									if(tranDts.size()>0){
										
										for(TranDt tranDt :tranDts){
											TranDt tranDtNew = new TranDt();
											tranDtNew.setBagMt(bagMtNew);
											tranDtNew.setBagSrNo(tranDt.getBagSrNo());
											tranDtNew.setCarat(Math.round(((tranDt.getCarat()/tranDt.getPcs())*splitQty)*1000.0)/1000.0);
											tranDtNew.setCenterStone(tranDt.getCenterStone());
											tranDtNew.setCreatedBy(principal.getName());
											tranDtNew.setCreatedDate(new Date());
											tranDtNew.setCurrStk(true);
											tranDtNew.setDepartment(tranDt.getDepartment());
											tranDtNew.setDeptFrom(tranDt.getDeptFrom());
											tranDtNew.setPartNm(tranDt.getPartNm());
											tranDtNew.setPcs(splitQty);
											tranDtNew.setQuality(tranDt.getQuality());
											tranDtNew.setRefDtId(tranDt.getId());
											tranDtNew.setSetting(tranDt.getSetting());
											tranDtNew.setSettingType(tranDt.getSettingType());
											tranDtNew.setShape(tranDt.getShape());
											tranDtNew.setSieve(tranDt.getSieve());
											tranDtNew.setSize(tranDt.getSize());
											tranDtNew.setSizeGroup(tranDt.getSizeGroup());
											tranDtNew.setStone((int)Math.round((tranDt.getStone()/tranDt.getPcs())*splitQty));
											tranDtNew.setStoneType(tranDt.getStoneType());
											tranDtNew.setSubShape(tranDt.getSubShape());
											tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
											tranDtNew.setTranDate(new Date());
											tranDtNew.setTranMt(tranMtNew);
											tranDtNew.setRate(tranDt.getRate());
											tranDtNew.setFactoryRate(tranDt.getFactoryRate());
											tranDtNew.setAvgRate(tranDt.getAvgRate());
											tranDtNew.setTransferRate(tranDt.getTransferRate());
											tranDtService.save(tranDtNew);
											
											
											
											TranDt tranDt2 = new TranDt();
											tranDt2.setBagMt(tranDt.getBagMt());
											tranDt2.setBagSrNo(tranDt.getBagSrNo());
											tranDt2.setCarat(Math.round(((tranDt.getCarat()/tranDt.getPcs())*(tranDt.getPcs()-splitQty))*1000.0)/1000.0);
											tranDt2.setCenterStone(tranDt.getCenterStone());
											tranDt2.setCreatedBy(principal.getName());
											tranDt2.setCreatedDate(new Date());
											tranDt2.setCurrStk(true);
											tranDt2.setDepartment(tranDt.getDepartment());
											tranDt2.setDeptFrom(tranDt.getDeptFrom());
											tranDt2.setPartNm(tranDt.getPartNm());
											tranDt2.setPcs(Math.round((tranDt.getPcs()-splitQty)*100.0)/100.0);
											tranDt2.setQuality(tranDt.getQuality());
											tranDt2.setRefDtId(tranDt.getId());
											tranDt2.setSetting(tranDt.getSetting());
											tranDt2.setSettingType(tranDt.getSettingType());
											tranDt2.setShape(tranDt.getShape());
											tranDt2.setSieve(tranDt.getSieve());
											tranDt2.setSize(tranDt.getSize());
											tranDt2.setSizeGroup(tranDt.getSizeGroup());
											tranDt2.setStone((int)Math.round((tranDt.getStone()/tranDt.getPcs())*(tranDt.getPcs()-splitQty)));
											tranDt2.setStoneType(tranDt.getStoneType());
											tranDt2.setSubShape(tranDt.getSubShape());
											tranDt2.setTime(new java.sql.Time(new java.util.Date().getTime()));
											tranDt2.setTranDate(new Date());
											tranDt2.setTranMt(tranMt2);
											tranDt2.setRate(tranDt.getRate());
											tranDt2.setFactoryRate(tranDt.getFactoryRate());
											tranDt2.setAvgRate(tranDt.getAvgRate());
											tranDt2.setTransferRate(tranDt.getTransferRate());
											tranDtService.save(tranDt2);
											
											
											
											
											//editing the existing record
																		
											tranDt.setCurrStk(false);
											tranDt.setDeptTo(tranDt.getDepartment());
											tranDt.setIssDate(new java.util.Date());
											tranDt.setModiBy(principal.getName());
											tranDt.setModiDate(new java.util.Date());
											
											tranDtService.save(tranDt);
											
										}
										
										
										
								
										
										
										
									}
									
									
									
									
									List<TranMetal>tranMetals=tranMetalService.findByBagMtAndCurrStk(bagMt, true);
									
									if(tranMetals.size()>0){
										
										Double vTranMetalWt=0.0;
										for(TranMetal tranMetal :tranMetals){
											TranMetal tranMetalNew=new TranMetal();
											tranMetalNew.setBagMt(bagMtNew);
											tranMetalNew.setColor(tranMetal.getColor());
											tranMetalNew.setCreatedBy(principal.getName());
											tranMetalNew.setCreatedDate(new Date());
											tranMetalNew.setCurrStk(true);
											tranMetalNew.setDepartment(tranMetal.getDepartment());
											tranMetalNew.setDeptFrom(tranMetal.getDeptFrom());
											tranMetalNew.setMainMetal(tranMetal.getMainMetal());
											tranMetalNew.setMetalPcs((int)Math.round(((tranMetal.getMetalPcs()/tranMt.getPcs())*splitQty)));
											tranMetalNew.setMetalWeight(Math.round(((tranMetal.getMetalWeight()/tranMt.getPcs())*splitQty)*1000.0)/1000.0);
											
									
											tranMetalNew.setPartNm(tranMetal.getPartNm());
											tranMetalNew.setPurity(tranMetal.getPurity());
											tranMetalNew.setPurityConv(tranMetal.getPurityConv());
											tranMetalNew.setRefTranMetalId(tranMetal.getId());
											tranMetalNew.setTime(new java.sql.Time(new Date().getTime()));
											tranMetalNew.setTranDate(new Date());
											tranMetalNew.setTranMt(tranMtNew);
											tranMetalNew.setMetalRate(tranMetal.getMetalRate());
											vTranMetalWt += Math.round(tranMetalNew.getMetalWeight()*1000.0)/1000.0;
											tranMetalService.save(tranMetalNew);

											TranMetal tranMetal2 = new TranMetal();
											tranMetal2.setBagMt(tranMetal.getBagMt());
											tranMetal2.setColor(tranMetal.getColor());
											tranMetal2.setCreatedBy(principal.getName());
											tranMetal2.setCreatedDate(new Date());
											tranMetal2.setCurrStk(true);
											tranMetal2.setDepartment(tranMetal.getDepartment());
											tranMetal2.setDeptFrom(tranMetal.getDeptFrom());
											tranMetal2.setMainMetal(tranMetal.getMainMetal());
											tranMetal2.setMetalPcs((int)Math.round(((tranMetal.getMetalPcs()/tranMt.getPcs())*(tranMt.getPcs()-splitQty))));
											tranMetal2.setMetalWeight(Math.round(((tranMetal.getMetalWeight()/tranMt.getPcs())*(tranMt.getPcs()-splitQty))*1000.0)/1000.0);
									
											tranMetal2.setPartNm(tranMetal.getPartNm());
											tranMetal2.setPurity(tranMetal.getPurity());
											tranMetal2.setPurityConv(tranMetal.getPurityConv());
											tranMetal2.setRefTranMetalId(tranMetal.getId());
											tranMetal2.setTime(new java.sql.Time(new Date().getTime()));
											tranMetal2.setTranDate(new Date());
											tranMetal2.setTranMt(tranMt2);
											tranMetal2.setMetalRate(tranMetal.getMetalRate());
											tranMetalService.save(tranMetal2);
											
											
											tranMetal.setCurrStk(false);
											tranMetal.setDeptTo(tranMetal.getDepartment());
											tranMetal.setIssDate(new java.util.Date());
											tranMetal.setModiBy(principal.getName());
											tranMetal.setModiDate(new java.util.Date());
											tranMetalService.save(tranMetal);
											
										}
										
										
										
										if(vTranMetalWt >0){
											
											Double vGrossWt =Math.round((vTranMetalWt+vDiaWt+vCompWt)*1000.0)/1000.0;
											
											if(!vGrossWt.equals(splitWeight)){
												
												Double diffWt = splitWeight-vGrossWt;
												
												if(diffWt !=0){
													
													TranMetal tranMetalNew = tranMetalService.findByBagMtIdAndMainMetalAndCurrStk(bagMtNew.getId(), true,true);
													
													tranMetalNew.setMetalWeight(Math.round((tranMetalNew.getMetalWeight()+diffWt)*1000.0)/1000.0);
													
													TranMetal tranMetalold = tranMetalService.findByBagMtIdAndMainMetalAndCurrStk(bagMt.getId(), true,true);
													tranMetalold.setMetalWeight(Math.round((tranMetalold.getMetalWeight()-diffWt)*1000.0)/1000.0);
													
													
												}
												
												
												
											}
											
											
											
											
											
											
											
										}
								
										
									}
								
									
							
									Double compWt=0.0;
									Double compPcs=0.0;
									for(Tuple tuple:tupleList){
										
								
								
										
										
										if(tuple.get(qCompTran.balance.sum())>=0){
											continue;
										}else{
											Purity purity =purityService.findByName(tuple.get(qCompTran.purity.name));
											Color color =colorService.findByName(tuple.get(qCompTran.color.name));
											Component component = componentService.findByName(tuple.get(qCompTran.component.name));
											
											compWt=-tuple.get(qCompTran.balance.sum());
											compPcs=-tuple.get(qCompTran.balancePcs.sum());
											
											Double compMtlRate=compTranService.getCompMetalRate(purity.getId(), color.getId(), tranMt.getDepartment().getId(), 
													component.getId(),Math.round(((compWt/tranMt.getPcs())*splitQty)*1000.0)/1000.0);
											
											
											CompTran compTranNew=new CompTran();
											compTranNew.setBagMt(bagMtNew);
											compTranNew.setBalance(-(Math.round(((compWt/tranMt.getPcs())*splitQty)*1000.0)/1000.0));
											compTranNew.setBalancePcs(-(Math.round(((compPcs/tranMt.getPcs())*splitQty)*100.0)/100.0));
											compTranNew.setColor(color);
											compTranNew.setComponent(component);
											compTranNew.setCreatedBy(principal.getName());
											compTranNew.setCreatedDt(new Date());
											compTranNew.setInOutFld("D");
											compTranNew.setMetalWt(Math.round(((compWt/tranMt.getPcs())*splitQty)*1000.0)/1000.0);
											compTranNew.setPcs(Math.round(((compPcs/tranMt.getPcs())*splitQty)*100.0)/100.0);
											compTranNew.setPurity(purity);
											compTranNew.setPurityConv(purity.getPurityConv());
											compTranNew.setTranMtId(tranMtNew.getId());
											compTranNew.setRemark("BagSplit");
											compTranNew.setTranType("BagSplit");
											compTranNew.setTrandate(new Date());
											compTranNew.setMetalRate(compMtlRate);
											compTranService.save(compTranNew);
											
											
											CompTran compTran2 = new CompTran();
											compTran2.setBagMt(bagMt);
											compTran2.setBalance(Math.round((compTranNew.getMetalWt())*1000.0)/1000.0);
											compTran2.setBalancePcs(Math.round((compTranNew.getPcs())*100.0)/100.0);
											compTran2.setColor(color);
											compTran2.setComponent(component);
											compTran2.setCreatedBy(principal.getName());
											compTran2.setCreatedDt(new Date());
											compTran2.setInOutFld("C");
											compTran2.setMetalWt(Math.round((compTranNew.getMetalWt())*1000.0)/1000.0);
											compTran2.setPcs(Math.round((compTranNew.getPcs())*100.0)/100.0);
											compTran2.setPurity(purity);
											compTran2.setPurityConv(purity.getPurityConv());
											compTran2.setTranMtId(tranMt.getId());
											compTran2.setRemark("BagSplit");
											compTran2.setTranType("BagSplit");
											compTran2.setTrandate(new Date());
											compTran2.setMetalRate(compMtlRate);
											compTranService.save(compTran2);
											
											
											
											
											
										}
										
										
									}
									
									
									List<ReadyBag>readyBags =readyBagService.findByBagMtAndCurrentStock(bagMt, true);
									for(ReadyBag readyBag :readyBags){
										
										ReadyBag readyBagNew = new ReadyBag();
										readyBagNew.setBagMt(bagMtNew);
										readyBagNew.setBagPcs(bagMtNew.getQty());
										readyBagNew.setBagSrNo(readyBag.getBagSrNo());
										readyBagNew.setCarat(Math.round(((readyBag.getCarat()/readyBag.getBagPcs())*splitQty)*1000.0)/1000.0);
										readyBagNew.setCenterStone(readyBag.getCenterStone());
										readyBagNew.setCreatedBy(principal.getName());
										readyBagNew.setCreatedDt(new Date());
										readyBagNew.setCurrentStock(true);
										readyBagNew.setDepartment(readyBag.getDepartment());
										readyBagNew.setPartNm(readyBag.getPartNm());
										readyBagNew.setQuality(readyBag.getQuality());
										readyBagNew.setSetting(readyBag.getSetting());
										readyBagNew.setSettingType(readyBag.getSettingType());
										readyBagNew.setShape(readyBag.getShape());
										readyBagNew.setSieve(readyBag.getSieve());
										readyBagNew.setSize(readyBag.getSize());
										readyBagNew.setSizeGroup(readyBag.getSizeGroup());
										readyBagNew.setStone((int)Math.round((readyBag.getStone()/readyBag.getBagPcs())*splitQty));
										readyBagNew.setStoneType(readyBag.getStoneType());
										readyBagNew.setSubShape(readyBag.getSubShape());
										readyBagNew.setTranMt(tranMtNew);
										readyBagNew.setLocation(readyBag.getLocation());
										readyBagNew.setRate(readyBag.getRate());
										readyBagNew.setFactoryRate(readyBag.getFactoryRate());
										readyBagNew.setAvgRate(readyBag.getAvgRate());
										readyBagNew.setTransferRate(readyBag.getTransferRate());
										readyBagService.save(readyBagNew);
										
										ReadyBag readyBag2 =new ReadyBag();
										readyBag2.setBagMt(readyBag.getBagMt());
										readyBag2.setBagPcs(Math.round((readyBag.getBagPcs()-splitQty)*100.0)/100.0);
										readyBag2.setBagSrNo(readyBag.getBagSrNo());
										readyBag2.setCarat(Math.round(((readyBag.getCarat()/readyBag.getBagPcs())*(readyBag.getBagPcs()-splitQty))*1000.0)/1000.0);
										readyBag2.setCenterStone(readyBag.getCenterStone());
										readyBag2.setCreatedBy(principal.getName());
										readyBag2.setCreatedDt(new Date());
										readyBag2.setCurrentStock(true);
										readyBag2.setDepartment(readyBag.getDepartment());
										readyBag2.setPartNm(readyBag.getPartNm());
										readyBag2.setQuality(readyBag.getQuality());
										readyBag2.setSetting(readyBag.getSetting());
										readyBag2.setSettingType(readyBag.getSettingType());
										readyBag2.setShape(readyBag.getShape());
										readyBag2.setSieve(readyBag.getSieve());
										readyBag2.setSize(readyBag.getSize());
										readyBag2.setSizeGroup(readyBag.getSizeGroup());
										readyBag2.setStone((int)Math.round((readyBag.getStone()/readyBag.getBagPcs())*(readyBag.getBagPcs()-splitQty)));
										readyBag2.setStoneType(readyBag.getStoneType());
										readyBag2.setSubShape(readyBag.getSubShape());
										readyBag2.setTranMt(tranMt2);
										readyBag2.setLocation(readyBag.getLocation());
										readyBag2.setRate(readyBag.getRate());
										readyBag2.setFactoryRate(readyBag.getFactoryRate());
										readyBag2.setAvgRate(readyBag.getAvgRate());
										readyBag2.setTransferRate(readyBag.getTransferRate());
										readyBagService.save(readyBag2);
										
										
										readyBag.setCurrentStock(false);
										readyBag.setIssDt(new java.util.Date());
										readyBag.setModiBy(principal.getName());
										readyBag.setModiDate(new java.util.Date());
										
										readyBagService.save(readyBag);
										
										
										
									}
									
					
					
				}
			}
			
		
						//	TranMt tranMt =tranMtService.findByBagMtCurrStk(bagMt, true);
						//	TranMt tranMt =tranMtService.findByBagMtCurrStkNew(bagMt, true);
							
							if(tranMt!=null){
								
								
								
								
							}
							
							
							
							
			}

			
				retVal=bagMtNew.getName();
				
				
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		retVal=e.getMessage();
	}
		
		
		
		return retVal;
		
		
	
		
		
	}

	@Override
	public List<BagMt> findByOrderDtAndDeactive(OrderDt orderDt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return bagMtRepository.findByOrderDtAndDeactive(orderDt, deactive);
	}

	@Override
	public String recastBag(String bagIds, Principal principal,String barcodeuploadFilePath) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		
		String[] data1 = bagIds.split(",");
		
		for (int i = 0; i < data1.length; i++) {
		//updating the record
			BagMt bagMtOld = findOne(Integer.parseInt(data1[i]));
			bagMtOld.setRecast(true);
			bagMtOld.setModiBy(principal.getName());
			bagMtOld.setModiDate(new java.util.Date());
			save(bagMtOld);

			
			//new record 
			
			BagMt bagMt = new BagMt();
			String bagName = bagMtOld.getName().trim();
			
			String bagChr=   bagName.substring(bagName.length() - 2);
			
			
			Integer check = bagChr.indexOf("R");

			if (check == -1) {
				bagMt.setName(bagName+ "R1");
			} else {
				
				
				
				String[] part = bagChr.split("(?<=\\D)(?=\\d)");
							
			
				Integer numValInt = Integer.parseInt(part[1]);
				++numValInt;
				
				
				   int sep = bagName.lastIndexOf("R");
			       String newBag= bagName.substring(0,sep + 1);
			       
			  
					
			String finalName = newBag+numValInt;
				bagMt.setName(finalName.trim());
			}
			bagMt.setQty(bagMtOld.getQty());
			bagMt.setOrderMt(bagMtOld.getOrderMt());
			bagMt.setOrderDt(bagMtOld.getOrderDt());
			Integer srNo =	getMaxSrno();
			bagMt.setSeqNo(++srNo);
			bagMt.setCreatedBy(principal.getName());
			bagMt.setCreatedDate(new java.util.Date());
		
			save(bagMt);
			
			Utils.barcodeGeneration(bagMt.getId(), bagMt.getName(), barcodeuploadFilePath);

			retVal = bagMt.getName();
		}
		
		
		return retVal;
	}

	@Override
	public Integer getMaxBarcodeSrno(String barCodeType) {
		QBagMt qBagMt = QBagMt.bagMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		
		
		List<Integer> maxSrno = query
			.from(qBagMt)
			.where(qBagMt.deactive.eq(false).and(qBagMt.barcodeType.equalsIgnoreCase(barCodeType))).list(qBagMt.barcodeSrNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 999 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public String generateBarcode(String bagNm,String barcodeuploadFilePath, Principal principal, Integer barcodeMtId) {
		// TODO Auto-generated method stub
		
		String retVal= "-1";
		
		BagMt bagMt = findByName(bagNm);
		
		
		Integer barcodeSrNo=getMaxBarcodeSrno("LDJ");
		barcodeSrNo++;
		String barcodeNm="LDJ-"+barcodeSrNo;
		bagMt.setBarcodeSrNo(barcodeSrNo);
		bagMt.setBarcode(barcodeNm);
		bagMt.setBarcodeType("LDJ");
		save(bagMt);
		Utils.barcodeGeneration(bagMt.getId(), barcodeNm, barcodeuploadFilePath);
		

		if(barcodeMtId != null) {
			
			TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
			
			BarcodeDt barcodeDt = new BarcodeDt();
			barcodeDt.setBagMt(bagMt);
			barcodeDt.setColor(bagMt.getOrderDt().getColor());
			barcodeDt.setPurity(bagMt.getOrderDt().getPurity());
			barcodeDt.setDesign(bagMt.getOrderDt().getDesign());
			barcodeDt.setBarcode(barcodeNm);
			barcodeDt.setCreatedDt(new Date());
			barcodeDt.setCreatedBy(principal.getName());
			barcodeDt.setRefTranMtId(tranMt.getId());
		
			barcodeDt.setBarcodeMt(barcodeMtService.findOne(barcodeMtId));
			
			
			barcodeDtService.save(barcodeDt);
			
			
			retVal = "1";
		}
		
		
		
		return retVal;
	}

	@Override
	public String bagMtListing(Integer limit, Integer offset, String sort, String order, String search, String pInvNo,
			Integer pOrdDt) {
		
		StringBuilder sb = new StringBuilder();
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo, false); 
				
		OrderDt orderDt = orderDtService.findOne(pOrdDt);

		List<BagMt> bagMts = null;
					
		
			if (orderMt != null) {
				bagMts = findByOrderMtAndOrderDt(orderMt, orderDt);
			} else {
				bagMts = findAll();				
			}

			sb.append("{\"total\":").append(bagMts.size()).append(",\"rows\": [");
			
			for (BagMt bagMt : bagMts) {
					sb.append("{\"id\":\"")
					.append(bagMt.getId())
					.append("\",\"name\":\"")
					.append(bagMt.getName())
					.append("\",\"qty\":\"")
					.append(bagMt.getQty())
					.append("\",\"srNo\":\"")
					.append(bagMt.getSrNo())
					.append("\",\"style\":\"")
					.append((bagMt.getOrderDt() == null ? "" : bagMt.getOrderDt().getDesign().getMainStyleNo()))
					.append("\",\"ordQty\":\"")
					.append((bagMt.getOrderDt() == null ? "" : bagMt.getOrderDt().getPcs()))
					.append("\",\"deactive\":\"")
					.append((bagMt.getDeactive() == null ? "Active" : (bagMt.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((bagMt.getDeactiveDt() == null ? "" : bagMt.getDeactiveDt()))
					.append("\",\"select\":\"")
					.append((bagMt.getDeactiveDt() == null ? "" : bagMt.getDeactiveDt()))
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/bagMt/edit/")
					.append(bagMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					if(!orderMt.getOrderClose()){
						sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:bagDtDelete(event, ").append(bagMt.getId()).append(", 1);' href='javascript:void(0);'")
						.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(bagMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						
					}
					sb.append("\"},");	
			}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		return str;

	}

	@Override
	public String setOpeningListing(Integer limit, Integer offset, String sort, String order, String search,
			String pInvNo,String opt) {
		
		StringBuilder sb = new StringBuilder();
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo, false);

		
		if (orderMt != null) {
			if (search == null) {
				search = "" + orderMt.getId();
			}
				
			List<BagMt> bagMts = findByOrderMt(orderMt);

			sb.append("{\"total\":").append(bagMts.size()).append(",\"rows\": [");
			for (BagMt bagMt : bagMts) {

				List<TranMt> tranMts = tranMtService.findByBagMt(bagMt);
				  				
				if (tranMts.size() == 0  ) {
					
					if(opt.equalsIgnoreCase("1")) {
					
					sb.append("{\"id\":\"")
							.append(bagMt.getId())
							.append("\",\"name\":\"")
							.append(bagMt.getName())
							.append("\",\"qty\":\"")
							.append(bagMt.getQty())
							.append("\",\"styleNo\":\"")
							.append(bagMt.getOrderDt().getDesign().getMainStyleNo())
							.append("\",\"deactive\":\"")
							.append((bagMt.getDeactive() == null ? "Active": (bagMt.getDeactive() ? "Deactive": "Active")))
							.append("\",\"deactiveDt\":\"")
							.append((bagMt.getDeactiveDt() == null ? "" : bagMt.getDeactiveDt()))
							.append("\",\"action1\":\"")
							.append("<a href='<spring:url value='/manufacturing/transactions/bagMt/edit/{")
							.append(bagMt.getId())
							.append("}.html'>' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
							.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='<spring:url value='/manufacturing/transactions/bagMt/delete/")
							.append(bagMt.getId())
							.append(".html' />' class='btn btn-xs btn-danger triggerRemove")
							.append(bagMt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
					}else {
						
						if(bagMt.getOrderDt().getBarcode() != null && !bagMt.getOrderDt().getBarcode().isEmpty() && !bagMt.getBagCloseFlg()) {
							
							List<OrderMetal>orderMetals=orderMetalService.findByOrderDtAndDeactive(bagMt.getOrderDt(), false);
							String purityVal="";
							for(OrderMetal orderMetal :orderMetals) {
								 if(purityVal.length()>0) {
									 purityVal=purityVal+","+orderMetal.getPurity().getName()+"-"+orderMetal.getColor().getName();
								 }else {
									 purityVal=orderMetal.getPurity().getName()+"-"+orderMetal.getColor().getName();
								 }
							}	
							
						sb.append("{\"id\":\"")
						.append(bagMt.getId())
						.append("\",\"name\":\"")
						.append(bagMt.getName())
						.append("\",\"barcode\":\"")
						.append(bagMt.getOrderDt().getBarcode())
						.append("\",\"qty\":\"")
						.append(bagMt.getQty())
						.append("\",\"ktColor\":\"")
						.append(purityVal)
						.append("\",\"styleNo\":\"")
						.append(bagMt.getOrderDt().getDesign().getMainStyleNo())
						.append("\",\"deactive\":\"")
						.append((bagMt.getDeactive() == null ? "Active": (bagMt.getDeactive() ? "Deactive": "Active")))
						.append("\",\"deactiveDt\":\"")
						.append((bagMt.getDeactiveDt() == null ? "" : bagMt.getDeactiveDt()))
						.append("\",\"action1\":\"")
						.append("<a href='<spring:url value='/manufacturing/transactions/bagMt/edit/{")
						.append(bagMt.getId())
						.append("}.html'>' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
						.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='<spring:url value='/manufacturing/transactions/bagMt/delete/")
						.append(bagMt.getId())
						.append(".html' />' class='btn btn-xs btn-danger triggerRemove")
						.append(bagMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
						}
						
					}

				}
				else{
					System.out.println("in else part--------------");
				}

			}// for loop ending

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
	

		return str;
	}

	@Override
	public String jobBagDetails(String bagName,String uploadFilePath) {
	
		BagMt bagMt = findByName(bagName);
		OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());
		
		StringBuilder sb = new StringBuilder();

		String img = orderDt.getDesign().getDefaultImage();
		String imgName = orderDt.getDesign().getImage1();
		if (img != null && img.equals("2")) {
			imgName = orderDt.getDesign().getImage2();
		} else if (img != null && img.equals("3")) {
			imgName = orderDt.getDesign().getImage3();
		}

		sb.append("{\"invNo\":\"")
			.append(orderDt.getOrderMt().getInvNo())
			.append("\",\"party\":\"")
			.append((orderDt.getOrderMt().getParty() == null ? "" : orderDt.getOrderMt().getParty().getPartyCode()))
			.append("\",\"styleNo\":\"")
			.append(orderDt.getDesign().getMainStyleNo())
			.append("\",\"bagPcs\":\"")
			.append(bagMt.getQty())
			.append("\",\"orderDtId\":\"")
			.append(orderDt.getId())
			.append("\",\"bagSrno\":\"")
			.append(orderDt.getSrNo())
			.append("\",\"image\":\"")
			.append(uploadFilePath + imgName)
			.append("\"},");
		
		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}

	@Override
	public String jobBagDiamondDetails(String bagName, String uploadFilePath) {
	
		BagMt bagMt = findByName(bagName);
		
		if(bagMt == null){
			
			return "-1";
		}
		
		Department department = departmentService.findByName("Cancel");
		
		TranMt tranMt =tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
		
		if(tranMt != null){
			
			return "-2";
		}
			
		OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());
		
		
		String img = orderDt.getDesign().getDefaultImage();
				
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("invNo", orderDt.getOrderMt().getInvNo());
		jsonObject.put("partyName", orderDt.getOrderMt().getParty().getPartyCode());
		jsonObject.put("styleNo", orderDt.getDesign().getMainStyleNo());
		jsonObject.put("bagPcs", bagMt.getQty());
		jsonObject.put("image", uploadFilePath + img);
		jsonObject.put("orderDtId", orderDt.getId());
		
		
		return jsonObject.toString();
	}

	@Override
	public String diaRateChngDetails(String bagName, String uploadFilePath) {
		BagMt bagMt = findByName(bagName);
		OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());
		TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);

		StringBuilder sb = new StringBuilder();

	

		String img = orderDt.getDesign().getDefaultImage();

		String imgName = orderDt.getDesign().getImage1();
		if (img != null && img.equals("2")) {
			imgName = orderDt.getDesign().getImage2();
		} else if (img != null && img.equals("3")) {
			imgName = orderDt.getDesign().getImage3();
		}

		sb.append("{\"invNo\":\"")
				.append(orderDt.getOrderMt().getInvNo())
				.append("\",\"party\":\"")
				.append((orderDt.getOrderMt().getParty() == null ? "" : orderDt.getOrderMt().getParty().getPartyCode()))
				.append("\",\"styleNo\":\"")
				.append(orderDt.getDesign().getMainStyleNo())
				.append("\",\"bagPcs\":\"")
				.append(bagMt.getQty())
				.append("\",\"orderDtId\":\"")
				.append(orderDt.getId())
				.append("\",\"department\":\"")
				.append((tranMt == null ? "" : tranMt.getDepartment().getName()))
				.append("\",\"bagSrno\":\"").append(orderDt.getSrNo())
				.append("\",\"image\":\"").append(uploadFilePath + imgName)
				.append("\"},");

		
		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]";

		return str;
	}

	@Override
	public String metalAdditionBagDetail(String bagName, String uploadFilePath) {
	String str = null;
		
		BagMt bagMt = findByName(bagName);
		
		
		if(bagMt==null){
			return "error : Not Valid Bag ";
		}else {
		
		TranMt tranMtt = tranMtService.findByBagMtCurrStk(bagMt, true);
		
	
		
		if(tranMtt != null){
	
			OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());

		

			String img = orderDt.getDesign().getDefaultImage();
			String imgName = orderDt.getDesign().getImage1();
			if (img != null && img.equals("2")) {
				imgName = orderDt.getDesign().getImage2();
			} else if (img != null && img.equals("3")) {
				imgName = orderDt.getDesign().getImage3();
			}
			
			
			
			  SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		    	 
	    	  String ordDate = sf.format(tranMtt.getOrderMt().getInvDate());

			str = "" + tranMtt.getOrderMt().getInvNo() + "#"
					+ ordDate + "#"
					+ tranMtt.getOrderMt().getParty().getName() + "#"
					+ tranMtt.getOrderMt().getOrderType().getName() + "#"
					+ tranMtt.getOrderDt().getDesign().getMainStyleNo() + "#"
					/*+ tranMtt.getOrderDt().getPurity().getName() + "#"
					+ tranMtt.getOrderDt().getColor().getName() + "#"*/
					+ tranMtt.getRecWt() + "#" 
					+ tranMtt.getPcs() + "#"
					+ tranMtt.getDepartment().getName() + "#"
					+ uploadFilePath + imgName;
			
			
			
		}else{
			return "error : Bag Not in Department ";
		}
		}

		return str;
	}

	@Override
	public String bagSearch(String bagName,String uploadFilePath) {
		BagMt bagMt = findByName(bagName.trim());
		OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());
		
		
		
		TranMt tranMt = tranMtService.findByBagMtCurrStkNew(bagMt, true);
		
		
		String str = null;
		if(!tranMt.getPendApprovalFlg()) {
			
		
		List<TranDt> tranDts = tranDtService.findByBagMtAndDepartmentAndCurrStk(bagMt, tranMt.getDepartment(), true);
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		
		Integer vStone=0;
		Double vCarat = 0.0;
		
		if(tranDts.size() > 0){
			
			for(TranDt tranDt:tranDts){
				
				vStone += tranDt.getStone();
				vCarat += tranDt.getCarat();
				
			}
			
		}
		

		StringBuilder sb = new StringBuilder();

	

		String img = orderDt.getDesign().getDefaultImage();
		String imgName = orderDt.getDesign().getImage1();
		if (img != null && img.equals("2")) {
			imgName = orderDt.getDesign().getImage2();
		} else if (img != null && img.equals("3")) {
			imgName = orderDt.getDesign().getImage3();
		}

		sb.append("{\"invNo\":\"")
			.append(orderDt.getOrderMt().getInvNo())
			.append("\",\"party\":\"")
			.append((orderDt.getOrderMt().getParty() == null ? "" : orderDt.getOrderMt().getParty().getPartyCode()))
			.append("\",\"styleNo\":\"")
			.append(orderDt.getDesign().getMainStyleNo())
			.append("\",\"bagPcs\":\"")
			.append(bagMt.getQty())
			.append("\",\"department\":\"")
			.append((tranMt == null ? "" : tranMt.getDepartment().getName()))
			.append("\",\"recWt\":\"")
			.append((tranMt == null ? "" : df.format(tranMt.getRecWt())))
			.append("\",\"stone\":\"")
			.append(vStone)
			.append("\",\"carat\":\"")
			.append(df.format(vCarat))
			.append("\",\"image\":\"")
			.append(uploadFilePath + imgName)
			
			.append("\"},");


		 str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";
		return str;
		}else {
			return "Approval Pending From "+tranMt.getDeptTo().getCode() +" Department";	
		}
	}

	@Override
	public String bagDetailsList(String orderNo, Principal principal) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(orderNo, false);
		
		List<BagMt> bagMts = findByOrderMt(orderMt);
		
		sb.append("{\"total\":").append(bagMts.size()).append(",\"rows\": [");
		
		for(BagMt bagMt:bagMts){
		
			sb.append("{\"id\":\"")
			.append(bagMt.getId())
			.append("\",\"bagNo\":\"")
			.append(bagMt.getName())
			.append("\",\"styleNo\":\"")
			.append(bagMt.getOrderDt().getDesign().getMainStyleNo())
			.append("\",\"kt\":\"")
			.append(bagMt.getOrderDt().getPurity() != null ? bagMt.getOrderDt().getPurity().getName() : "")
			.append("\",\"color\":\"")
			.append(bagMt.getOrderDt().getColor() != null ? bagMt.getOrderDt().getColor().getName() : "")
			.append("\",\"barcode\":\"")
			.append(bagMt.getOrderDt().getBarcode() != null ? bagMt.getOrderDt().getBarcode() : "")
			.append("\",\"qty\":\"")
			.append(bagMt.getQty())
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}

	@Override
	public BagMt findByBarcode(String barcode) {
		// TODO Auto-generated method stub
		return bagMtRepository.findByBarcode(barcode);
	}



}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ChangingList;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QDiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IDiamondBaggingRepository;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondBaggingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class DiamondBaggingService implements IDiamondBaggingService {

	@Autowired
	private IDiamondBaggingRepository diamondBaggingRepository;

	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IOrderStnDtService orderStnDtService;
	
	@Autowired
	private IReadyBagService readyBagService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IStoneInwardDtService stoneInwardDtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ISettingService settingService;

	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IShapeService shapeService;

	@Autowired
	private IQualityService qualityService;

	@Autowired
	private ISubShapeService subShapeService;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStoneChartService stoneChartService;
	

	
	

	@Override
	public List<DiamondBagging> findAll() {
		QDiamondBagging qDiamondBagging = QDiamondBagging.diamondBagging;
		BooleanExpression expression = qDiamondBagging.deactive.eq(false);
		return (List<DiamondBagging>) diamondBaggingRepository.findAll(expression);
	}

	@Override
	public Page<DiamondBagging> findAll(Integer limit, Integer offset,
			String sort, String diamondBagging, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return diamondBaggingRepository.findAll(new PageRequest(page, limit,
				(diamondBagging.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(DiamondBagging diamondBagging) {
		diamondBaggingRepository.save(diamondBagging);
	}

	@Override
	public void delete(int id) {
		DiamondBagging diamondBagging = diamondBaggingRepository.findOne(id);

		if (!diamondBagging.getTransfered()) {
			
			StoneInwardDt stoneInwardDt = diamondBagging.getStoneInwardDt();
			
			stoneInwardDt.setBalStone(stoneInwardDt.getBalStone()+ diamondBagging.getStone());
			stoneInwardDt.setBalCarat(stoneInwardDt.getBalCarat()+ diamondBagging.getCarat());

/*			List<StoneTran> stoneTranList = stoneTranService.findByRefTranIdAndTranType(diamondBagging.getId(),"ImpStnAdj");
			for (StoneTran stoneTran : stoneTranList) {
				stoneTran.setDeactive(true);
				stoneTran.setDeactiveDt(new java.util.Date());
			}
*/
			
			diamondBagging.setDeactive(true);
			diamondBagging.setDeactiveDt(new java.util.Date());

			diamondBaggingRepository.save(diamondBagging);
		}

		// diamondBaggingRepository.delete(id);
	}

	@Override
	public Long count() {
		return diamondBaggingRepository.count();
	}

	@Override
	public DiamondBagging findOne(int id) {
		return diamondBaggingRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getDiamondBaggingList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiamondBagging findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiamondBagging> findByBagMt(BagMt bagMt) {
		QDiamondBagging qDiamondBagging = QDiamondBagging.diamondBagging;
		BooleanExpression expression = qDiamondBagging.deactive.eq(false).and(
				qDiamondBagging.bagMt.id.eq(bagMt.getId()));

		return (List<DiamondBagging>) diamondBaggingRepository
				.findAll(expression);
	}

	
	@Override
	public List<DiamondBagging> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive) {
		return diamondBaggingRepository.findByOrderDtAndDeactive(orderDt, deactive);
	}

	@Override
	public List<DiamondBagging> findByOrderDtAndOrderStnDt(OrderDt orderDt,
			OrderStnDt orderStnDt) {
		return diamondBaggingRepository.findByOrderDtAndOrderStnDt(orderDt,
				orderStnDt);
	}

	@Override
	public List<DiamondBagging> findActivebags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QDiamondBagging qDiamondBagging = QDiamondBagging.diamondBagging;
		BooleanExpression expression = qDiamondBagging.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDiamondBagging.deactive.eq(false);
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("baggingNo"))
					&& colValue != null) {
				expression = qDiamondBagging.bagMt.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return diamondBaggingRepository.count();
			} else {
				return diamondBaggingRepository.count();
			}
		}

		return diamondBaggingRepository.count(expression);
	}

	@Override
	public Integer getMaxSrno(Integer bagId) {
		QDiamondBagging qDiamondBagging = QDiamondBagging.diamondBagging;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;

		List<Integer> maxSrno = query.from(qDiamondBagging)
				.where(qDiamondBagging.deactive.eq(false).and(qDiamondBagging.bagMt.id.eq(bagId)))
					.list(qDiamondBagging.bagSrNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}

		return retVal;
	}

	@Override
	public List<DiamondBagging> findByStoneTypeAndShapeAndQualityAndSize(
			StoneType stoneType, Shape shape, Quality quality, String size) {
		return diamondBaggingRepository
				.findByStoneTypeAndShapeAndQualityAndSize(stoneType, shape,
						quality, size);
	}

	public List<DiamondBagging> findByStoneTypeAndShapeAndQualityAndSizeGroup(
			StoneType stoneType, Shape shape, Quality quality,
			SizeGroup sizeGroup) {
		return diamondBaggingRepository
				.findByStoneTypeAndShapeAndQualityAndSizeGroup(stoneType,
						shape, quality, sizeGroup);
	}

	@Override
	public List<DiamondBagging> findByBagMtAndBagSrNo(BagMt bagMt,
			Integer bagSrNo) {
		QDiamondBagging qDiamondBagging = QDiamondBagging.diamondBagging;
		BooleanExpression expression = qDiamondBagging.deactive.eq(false).and(
				qDiamondBagging.bagMt.id.eq(bagMt.getId()).and(qDiamondBagging.bagSrNo.eq(bagSrNo)).and(qDiamondBagging.tranType.in("Bagging","BaggingReturn")));

		return (List<DiamondBagging>) diamondBaggingRepository
				.findAll(expression);

	}

	@Override
	public List<ChangingList> getListForDiamondChanging(Integer bagId) {
		
		String cond = " BagId in (" + bagId + ")  ";

		List<ChangingList> changeingList = null;

		@SuppressWarnings("unchecked")
		TypedQuery<ChangingList> query =  (TypedQuery<ChangingList>)entityManager.createNativeQuery("{ CALL jsp_diamondBagging(?) }",ChangingList.class);
		query.setParameter(1, cond);

		changeingList = query.getResultList();
		
		return changeingList;
		
	}
	
	

	@Override 
	public DiamondBagging findByUniqueId(Long uniqueId) {
		return diamondBaggingRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<DiamondBagging> findByBagMtAndBagSrNoAndStoneInwardDtAndSettingAndSettingTypeAndDeactive(
			BagMt bagMt, Integer bagSrNo, StoneInwardDt stoneInwardDt,
			Setting setting, SettingType settingType, Boolean deactive) {

		return diamondBaggingRepository
				.findByBagMtAndBagSrNoAndStoneInwardDtAndSettingAndSettingTypeAndDeactive(
						bagMt, bagSrNo, stoneInwardDt, setting, settingType,
						deactive);
	}

	@Override
	public List<DiamondBagging> findByBagMtAndTranTypeAndDeactiveAndBaggingReturn(
			BagMt bagMt, String tranType, Boolean deactive, Boolean baggingReturn) {
		return diamondBaggingRepository.findByBagMtAndTranTypeAndDeactiveAndBaggingReturn(bagMt, tranType, deactive, baggingReturn);
	}

	
	@Override
	public List<DiamondBagging> getListForCostingTransfer(Integer bagId) {

		String cond = "  BagId in (" + bagId + ")  ";
		
		List<DiamondBagging> diamondBaggingList = null;

		@SuppressWarnings("unchecked")
		TypedQuery<DiamondBagging> query =  (TypedQuery<DiamondBagging>)entityManager.createNativeQuery("{ CALL jsp_costDiamondList(?) }",DiamondBagging.class);
		query.setParameter(1, cond);

		diamondBaggingList = query.getResultList();
		
		return diamondBaggingList;
		
		
		
	}
	
	@Override
	public List<DiamondBagging> findByBagMtAndStoneInwardDtAndDeactive(
			BagMt bagMt, StoneInwardDt stoneInwardDt, Boolean deactive) {
		return diamondBaggingRepository.findByBagMtAndStoneInwardDtAndDeactive(bagMt, stoneInwardDt, deactive);
	}

	@Override
	public String diamondBaggingTransfer(String bagNm, String data,Principal principal,String stockType,Boolean allowNegative,Date tranDate,String companyName) {
		
		synchronized (this) {
			String retVal="-1";
			BagMt bagMt = bagMtService.findByName(bagNm.trim());
			
			JSONArray jsonBagDt = new JSONArray(data);
			
			String tempSrNo = "";
			for (int y = 0; y < jsonBagDt.length(); y++) {
			
				JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
				
		
				if (dataBagDt.isNull("trfStone") || dataBagDt.get("trfStone").toString().equals("") 
						|| dataBagDt.isNull("trfCarat") || dataBagDt.get("trfCarat").toString().equals("")
						|| dataBagDt.isNull("quality") || dataBagDt.get("quality").toString().equals("")
						|| dataBagDt.isNull("sizeGroup") || dataBagDt.get("sizeGroup").toString().equals("")
						|| dataBagDt.isNull("stoneType") || dataBagDt.get("stoneType").toString().equals("")
						|| dataBagDt.isNull("shape") || dataBagDt.get("shape").toString().equals("")
						|| dataBagDt.isNull("mm") || dataBagDt.get("mm").toString().equals("")
						|| dataBagDt.isNull("sieve") || dataBagDt.get("sieve").toString().equals("")
						|| dataBagDt.isNull("setting") || dataBagDt.get("setting").toString().equals("")
						|| dataBagDt.isNull("setType") || dataBagDt.get("setType").toString().equals("")) {
					if(tempSrNo == ""){
						tempSrNo =  dataBagDt.get("bagSrno").toString();
					}else{
						tempSrNo =  tempSrNo+","+dataBagDt.get("bagSrno").toString();
					}
				}else{
					if(dataBagDt.getDouble("trfCarat") <= 0 ){
						if(tempSrNo == ""){
							tempSrNo =  dataBagDt.get("bagSrno").toString();
						}else{
							tempSrNo =  tempSrNo+","+dataBagDt.get("bagSrno").toString();
						}
					}
				}
				
			}
			
			
			if(tempSrNo != ""){
				String tempVal = "";
				if(tempSrNo != ""){
					tempVal = "Warning: TrfStone and TrfCarat or Qulaity or SizeGroup or StoneType or Shape or Size or Sieve or Setting or SettingType  Of BagSrNo ["+tempSrNo+"] Cannot be null or"
							+ " less than zero(0) or Blank";
				}
				
				return tempVal;
			}
			
			 String locationName="Diamond";
				
				if(companyName.equalsIgnoreCase("nuance II")) {
					locationName="Bagging";
				}
			
			  Department location=  departmentService.findByName(locationName);
			if(!allowNegative.equals(true)){
				String stockSrNo = "";
				
				
				  
				 
					
				  
				  
				
				for (int y = 0; y < jsonBagDt.length(); y++) {
					JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
					
					
					
					Double stockBal = stoneTranService.getStockBalanceStr(location.getId(),dataBagDt.get("stoneType").toString(), dataBagDt.get("shape").toString(), 
							dataBagDt.get("quality").toString(), dataBagDt.get("sizeGroup").toString(), dataBagDt.get("sieve").toString(), stockType,bagMt.getOrderDt().getId());
					
					if(stockBal < Double.parseDouble(dataBagDt.get("trfCarat").toString())){
						if(stockSrNo == ""){
							stockSrNo = dataBagDt.get("bagSrno").toString();
						}else{
							stockSrNo =  stockSrNo+","+dataBagDt.get("bagSrno").toString();
						}
					}
					
				}
				
				
				if(stockSrNo != ""){
					return "Warning : Stock Not Found of BagSrNo ["+stockSrNo+"]";
				}
				
			}
			
			
			
			
			
			
		/*	List<TranDt> tranDts = tranDtService.findByBagMt(bagMt);
			if(tranDts.size() > 0){
				return "Warning : Cannot Transfer Record As Bagging Already Done";
			}*/
			
			
			
			for (int y = 0; y < jsonBagDt.length(); y++) {
				
				JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
				
				
				List<ReadyBag>readyBags =readyBagService.findByBagMtAndBagSrNoAndDeactive(bagMt,  Integer.parseInt(dataBagDt.get("bagSrno").toString()), false);
				if (readyBags.size() > 0) {
					
					continue;
					
				}
				
				

				ReadyBag readyBag = new ReadyBag();
				readyBag.setBagMt(bagMt);
				readyBag.setBagPcs(bagMt.getQty());
				
				Shape shapeTmp = shapeService.findByName(dataBagDt.getString("shape"));
				
				readyBag.setShape(shapeTmp);
				
				if (dataBagDt.get("subShape").toString().trim().length() > 0) {
					readyBag.setSubShape(subShapeService.findByShapeAndName(shapeTmp, dataBagDt.getString("subShape")));					
				}
				
				readyBag.setPartNm(lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", dataBagDt.get("partNm").toString().trim(), false));
				readyBag.setQuality(qualityService.findByShapeAndNameAndDeactive(shapeTmp,dataBagDt.get("quality").toString(),false));
				readyBag.setSize(dataBagDt.get("mm").toString());
				readyBag.setSieve(dataBagDt.get("sieve").toString());
				readyBag.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(shapeTmp, dataBagDt.get("sizeGroup").toString().trim(),false));
				readyBag.setCarat(Double.parseDouble(dataBagDt.get("trfCarat").toString()));
				
				// setting the stone
				Double stn = Double.parseDouble(dataBagDt.get("trfStone").toString());
				Integer stnInt = stn.intValue();
				readyBag.setStone(stnInt);

				readyBag.setSetting(settingService.findByName(dataBagDt.get("setting").toString()));
				readyBag.setSettingType(settingTypeService.findByName(dataBagDt.get("setType").toString()));
				readyBag.setBagSrNo((dataBagDt.get("bagSrno") == null || dataBagDt.get("bagSrno").toString().length() == 0) ? null : Integer.parseInt(dataBagDt.get("bagSrno").toString()));

				readyBag.setStoneType(stoneTypeService.findByName(dataBagDt.get("stoneType").toString()));
				readyBag.setCurrentStock(true);
				readyBag.setCreatedBy(principal.getName());
				readyBag.setCreatedDt(new java.util.Date());
				readyBag.setDeactive(false);
				readyBag.setCenterStone(Boolean.parseBoolean(dataBagDt.get("centerStone").toString()));
				readyBag.setTranDate(tranDate);
				readyBag.setDiaCateg(dataBagDt.getString("diaCateg"));
				readyBagService.save(readyBag);
				
				//---------------Entry In StoneTran --------------//
				
				StoneTran stoneTran = new StoneTran();
				stoneTran.setBalCarat(-(Double.parseDouble(dataBagDt.get("trfCarat").toString())));
				stoneTran.setBalStone(-(Integer.parseInt(dataBagDt.get("trfStone").toString())));
				stoneTran.setCarat(Double.parseDouble(dataBagDt.get("trfCarat").toString()));
				stoneTran.setDeptLocation(location);
				stoneTran.setSieve(dataBagDt.get("sieve").toString());
				stoneTran.setStone(stnInt);
				stoneTran.setSize(dataBagDt.get("mm").toString());
				stoneTran.setBagMt(bagMt);
				stoneTran.setInOutFld("D");
				stoneTran.setBagSrNo(Integer.parseInt(dataBagDt.get("bagSrno").toString()));
				stoneTran.setCreatedBy(principal.getName());
				stoneTran.setCreatedDt(new java.util.Date());
				stoneTran.setDeactive(false);
				stoneTran.setQuality(qualityService.findByShapeAndNameAndDeactive(shapeTmp,dataBagDt.get("quality").toString(),false)); 
				stoneTran.setStoneType(stoneTypeService.findByName(dataBagDt.get("stoneType").toString()));
				stoneTran.setShape(shapeTmp);
				stoneTran.setSubShape(subShapeService.findByShapeAndName(shapeTmp, dataBagDt.getString("subShape")));
				stoneTran.setSetting(settingService.findByName(dataBagDt.get("setting").toString()));
				stoneTran.setSettingType(settingTypeService.findByName(dataBagDt.get("setType").toString()));
				stoneTran.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(shapeTmp, dataBagDt.get("sizeGroup").toString().trim(),false));
				stoneTran.setTranType("Bagging");
				stoneTran.setCenterStone(Boolean.parseBoolean(dataBagDt.get("centerStone").toString()));
				stoneTran.setPartNm(lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", dataBagDt.get("partNm").toString().trim(), false));
				stoneTran.setTranDate(tranDate);
				stoneTran.setSordDtId(bagMt.getOrderDt().getId());
				stoneTran.setBagQty(bagMt.getQty());
				stoneTran.setDiaCateg(dataBagDt.getString("diaCateg"));
				
				if(companyName.equalsIgnoreCase("nuance II")) {
					
					
					
					List<Tuple>stkBal=null;
					QStoneTran qStoneTran = QStoneTran.stoneTran;
					JPAQuery query = new JPAQuery(entityManager);
					
					stkBal = query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(stoneTran.getStoneType().getName()))
							.and(qStoneTran.shape.name.eq(stoneTran.getShape().getName())).and(qStoneTran.quality.name.eq(stoneTran.getQuality().getName()))
							.and(qStoneTran.sieve.eq(stoneTran.getSieve()))
							.and(qStoneTran.sordMtId.eq(bagMt.getOrderDt().getOrderMt().getId()))
							.and(qStoneTran.deptLocation.id.eq(location.getId())))
							.groupBy(qStoneTran.sieve).list(qStoneTran.balCarat.sum(),qStoneTran.balCarat.multiply(qStoneTran.rate).sum());
					
					Double stnVal=0.0;
					Double balcarat=0.0;
					for(Tuple tuple : stkBal) {
						 stnVal= Math.round((tuple.get(qStoneTran.balCarat.multiply(qStoneTran.rate).sum()))*100.0)/100.0;
						 
						 balcarat= Math.round((tuple.get(qStoneTran.balCarat.sum()))*1000.0)/1000.0;
					}
					
					Double avgRate =Math.round((stnVal/balcarat)*100.0)/100.0;
					
					
					
					/*
					 * QStoneTran qStoneTran = QStoneTran.stoneTran; JPAQuery query = new
					 * JPAQuery(entityManager);
					 * 
					 * List<StoneTran> stoneTrans = null;
					 * 
					 * stoneTrans = query.from(qStoneTran)
					 * .where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(
					 * stoneTran.getStoneType().getName()))
					 * .and(qStoneTran.shape.name.eq(stoneTran.getShape().getName())).and(qStoneTran
					 * .quality.name.eq(stoneTran.getQuality().getName()))
					 * .and(qStoneTran.sieve.eq(stoneTran.getSieve()))
					 * .and(qStoneTran.sordMtId.eq(bagMt.getOrderDt().getOrderMt().getId())).and(
					 * qStoneTran.deptLocation.id.eq(location.getId())))
					 * .groupBy(qStoneTran.stoneType,qStoneTran.shape,qStoneTran.quality,qStoneTran.
					 * size) .list(qStoneTran);
					 * 
					 * StoneTran stoneTran2 = stoneTrans.get(0);
					 */
					
					stoneTran.setSordMtId(bagMt.getOrderDt().getOrderMt().getId());
					stoneTran.setRate(avgRate);
					stoneTran.setAvgRate(avgRate);
					stoneTran.setTransferRate((double)Math.round((avgRate+(avgRate*stoneTran.getStoneType().getTransferRatePerc()/100))*100)/100);
					stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*stoneTran.getStoneType().getFactoryRatePerc()/100))*100)/100);
					
					readyBag.setRate(stoneTran.getAvgRate());
					readyBag.setAvgRate(stoneTran.getAvgRate());
					readyBag.setFactoryRate(stoneTran.getFactoryRate());
					readyBag.setTransferRate(stoneTran.getTransferRate());
					readyBagService.save(readyBag);
					
				}
				
			
				stoneTranService.save(stoneTran);
				
				
			}
			return retVal;
		}
		
		
		
		
		
	}

	@Override
	public String saveNewBreakUp(String pOIds, String trfBalStone,
			String trfBalCarat, String cSetting, String cSettingType,
			String bagNm, String tempDeptNm, String pCenterStone,
			Principal principal) {
		String retVal="1";
		
		Date aDdate = null;
		java.sql.Time timeValue = new java.sql.Time(new java.util.Date().getTime());

		if(pOIds.length() == 0){
			retVal = "-1";
			
		}else{
			
			
			
			String vStnDtId[] = pOIds.split(",");
			String vTrfBalStone[] = trfBalStone.split(",");
			String vTrfBalCarat[] = trfBalCarat.split(",");
			String vSetting[] = cSetting.split(",");
			String vSettingType[] = cSettingType.split(",");
			String vCenterStone[] = pCenterStone.split(",");
			
			aDdate = new java.util.Date();
			DecimalFormat df = new DecimalFormat("#.###");
			
			
			for(int j=0; j < vStnDtId.length; j++){
				String setNm = vSetting[j];
				if(setNm.equals("-")){
					retVal = "-2";
					return retVal;
				}
				
				String setTypeNm = vSettingType[j];
				if(setTypeNm.equals("-")){
					retVal = "-3";
					return retVal;
				}
				
			}
			
			
			for (int j = 0; j < vStnDtId.length; j++) {

				StoneInwardDt stoneInwardPk = stoneInwardDtService
						.findOne(Integer.parseInt(vStnDtId[j]));

				Double stockCarat = stoneInwardPk.getBalCarat();
				Double currTrfCarat = Double.parseDouble(vTrfBalCarat[j]);

				if (stockCarat < currTrfCarat) {
					retVal = "-5";
					break;
				}

		

			} // ending the first for loop

			if (retVal == "-5") {
				return retVal;
			}
			
			
			
			
			
			
			
			Department department = departmentService.findByName(tempDeptNm.trim());
			
			BagMt bagMt = bagMtService.findByName(bagNm.trim());
			TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
			
			if(tranMt == null){
				retVal = "-4";
				return retVal;
			}
			
			
			
			for(int i=0; i < vStnDtId.length; i++){
				
				//StoneInwardDt stoneInwardDt = stoneInwardDtService.findOne(Integer.parseInt(vStnDtId[i]));
				
				
				Integer maxBagSrNo = getMaxSrno(bagMt.getId());
				
				
				Integer tempBagStone = 0;
				Double tempBagCarat = 0.000;
				
				
				
				tempBagStone = Integer.parseInt(vTrfBalStone[i]);
				tempBagCarat = Double.parseDouble( vTrfBalCarat[i]);
				
				df.format(tempBagCarat);
				
				String settingName = vSetting[i];
				Setting setting = settingService.findByName(settingName.trim());
				
				String settingTypeName = vSettingType[i];
				SettingType settingType = settingTypeService.findByName(settingTypeName.trim());
				
				
				if(tempBagCarat > 0 && tempBagStone > 0){
					
					//new entry in DiamondBagging
					
				
					
					//update in tranMt
					
						/*DiamondBagging diamondBaggingNew = new DiamondBagging();
						
						diamondBaggingNew.setBagSrNo(maxBagSrNo==null?1: maxBagSrNo+1);
						diamondBaggingNew.setBalCarat(tempBagCarat );
						diamondBaggingNew.setBalStone(tempBagStone );
						diamondBaggingNew.setCarat(tempBagCarat);
						diamondBaggingNew.setStone(tempBagStone);
						diamondBaggingNew.setTranType("ChangingIssue");
						diamondBaggingNew.setInOutFld("C");
						diamondBaggingNew.setBagMt(bagMt);
						diamondBaggingNew.setDepartment(department);
						diamondBaggingNew.setBagPcs(bagMt.getQty());
						diamondBaggingNew.setShape(stoneInwardDt.getShape());
						diamondBaggingNew.setQuality(stoneInwardDt.getQuality());
						diamondBaggingNew.setSize(stoneInwardDt.getSize());
						diamondBaggingNew.setSizeGroup(stoneInwardDt.getSizeGroup());
						diamondBaggingNew.setSieve(stoneInwardDt.getSieve());
						diamondBaggingNew.setSetting(setting);
						diamondBaggingNew.setSettingType(settingType);
						diamondBaggingNew.setStoneInwardDt(stoneInwardDt);
						diamondBaggingNew.setStnRate(stoneInwardDt.getRate());
						diamondBaggingNew.setOrderDt(bagMt.getOrderDt());
						diamondBaggingNew.setStoneType(stoneInwardDt.getStoneType());
						diamondBaggingNew.setSubShape(stoneInwardDt.getSubShape() == null ? null : stoneInwardDt.getSubShape());
						diamondBaggingNew.setUniqueId(aDdate.getTime()+i);
						diamondBaggingNew.setDeactive(false);
						diamondBaggingNew.setCreatedBy(principal.getName());
						diamondBaggingNew.setCreatedDt(new java.util.Date());
						diamondBaggingNew.setRefTranId(tranMt.getId());
						diamondBaggingNew.setCenterStone(vCenterStone[i].toString().equalsIgnoreCase("yes") ? true : false );*/
						
						//System.out.println(aDdate.getTime()+ "========="+ i);
						
						//save(diamondBaggingNew);
						
						
						//update in stoneInward
						
						/*StoneInwardDt stoneInwardDtupDate = stoneInwardDtService.findOne(Integer.parseInt(vStnDtId[i]));*/
						/*stoneInwardDt.setBalCarat(Double.parseDouble(df.format(stoneInwardDt.getBalCarat() - tempBagCarat)));
						stoneInwardDt.setBalStone(stoneInwardDt.getBalStone() - tempBagStone);
						stoneInwardDtService.save(stoneInwardDt);*/
						
						
						
						
						
						Double recWt = tranMt.getRecWt();
						if(recWt > 0){
							Double tempVal = tempBagCarat/5;
							Double tempValSet = recWt + tempVal;
							tranMt.setRecWt(Double.parseDouble(df.format(tempValSet)));
							
							tranMtService.save(tranMt);
							
							
							
							
							
							
						}
						
						//new entry in tranDt
						
						TranDt tranDtNew = new TranDt();
						tranDtNew.setBagMt(bagMt);
						tranDtNew.setPcs(bagMt.getQty());
						tranDtNew.setCurrStk(true);
						tranDtNew.setCreatedBy(principal.getName());
						tranDtNew.setCreatedDate(new java.util.Date());
						tranDtNew.setDepartment(tranMt.getDepartment());
						tranDtNew.setDeptFrom(tranMt.getDeptFrom());
						tranDtNew.setBagSrNo(maxBagSrNo==null?1: maxBagSrNo+1);
						tranDtNew.setCarat(tempBagCarat);
						tranDtNew.setStone(tempBagStone);
						//tranDtNew.setSieve(stoneInwardDt.getSieve());
						//tranDtNew.setSize(stoneInwardDt.getSize());
						tranDtNew.setTime(timeValue);
						tranDtNew.setTranDate(new java.util.Date());
						//tranDtNew.setQuality(stoneInwardDt.getQuality());
						tranDtNew.setSetting(setting);
						tranDtNew.setSettingType(settingType);
						//tranDtNew.setShape(stoneInwardDt.getShape());
						//tranDtNew.setSizeGroup(stoneInwardDt.getSizeGroup());
						tranDtNew.setTranMt(tranMt);
						//tranDtNew.setStoneType(stoneInwardDt.getStoneType());
						
						tranDtService.save(tranDtNew);
						
						
						
						//new entry in stoneTran
						
						StoneTran stoneTran = new StoneTran();
						stoneTran.setBagMt(bagMt);
						stoneTran.setBalStone(tempBagStone * -1);
						stoneTran.setBalCarat(tempBagCarat * -1);
						stoneTran.setStone(tempBagStone);
						stoneTran.setCarat(tempBagCarat);
						stoneTran.setInOutFld("D");
						stoneTran.setDeptLocation(departmentService.findByName("Diamond"));
						stoneTran.setParty(bagMt.getOrderMt().getParty());
						stoneTran.setTranType("ChangingIssue");
						/*stoneTran.setRefTranId(findByUniqueId(diamondBaggingNew.getUniqueId()).getId());
						stoneTran.setStnRecDtId(stoneInwardDt.getId());
						stoneTran.setBagSrNo(maxBagSrNo==null?1: maxBagSrNo+1);
						stoneTran.setDepartment(tranMt.getDepartment()); 
						stoneTran.setQuality(stoneInwardDt.getQuality());
						stoneTran.setRate(stoneInwardDt.getRate());
						stoneTran.setSetting(setting);
						stoneTran.setSettingType(settingType);
						stoneTran.setShape(stoneInwardDt.getShape());
						stoneTran.setSize(stoneInwardDt.getSize());
						stoneTran.setSizeGroup(stoneInwardDt.getSizeGroup());
						stoneTran.setSieve(stoneInwardDt.getSieve());
						stoneTran.setStoneType(stoneInwardDt.getStoneType());
						stoneTran.setSubShape(stoneInwardDt.getSubShape() == null ? null : stoneInwardDt.getSubShape());
						stoneTran.setDeactive(false);*/
						stoneTran.setCreatedBy(principal.getName());
						stoneTran.setCreatedDt(new java.util.Date());
								
						stoneTranService.save(stoneTran);	
						
					
					
					
					
					
					
					
				
					
					
					
				}  // ending the if inside for loop
				
				
				
				
			} // ending the for loop second
			
			
			
			
		} // ending first if-else
		
		// TODO Auto-generated method stub
		return retVal;
	}

	@Override
	public String diamondChangingReturn(DiamondBagging diamondBagging,
			String pOIds, String trfBagStone, String trfBagCarat, String bagNm,
			String cDeptNm,
			Principal principal) {
		
		String retVal = "1";

		Date aDate = null;

		
		Department department = null;
		Employee employee = null;
		
		if(pOIds.length() == 0){
			retVal = "-1";
		}else{
		
				String vDbId[] 			= pOIds.split(",");
				String vTrfBagStone[] 	= trfBagStone.split(",");
				String vTrfBagCarat[] 	= trfBagCarat.split(",");
				//String vChangingType[]	= typeNm.split(",");
				
				
				aDate = new java.util.Date();
				DecimalFormat df = new DecimalFormat("#.###");
				
				if(cDeptNm != null){
					 department = departmentService.findByName(cDeptNm);
				}
				

				for(int i=0; i < vDbId.length; i++){
		
					
					
					
					Integer xtempBagStone = 0;
					Double xtempBagCarat = 0.000;
					
					xtempBagStone = Integer.parseInt(vTrfBagStone[i]);
					xtempBagCarat = Double.parseDouble( vTrfBagCarat[i]);
				
					if(xtempBagCarat <= 0 || xtempBagStone <= 0){
						
						return retVal = "-4";
						
					}
					
					
					
					
					
					
					
				} // ending for loop
				
				
				Integer tempBagStone = 0;
				Double tempBagCarat = 0.0;

				 for(int i=0; i < vDbId.length; i++){
					
					//DiamondBagging diamondBaggingOld = findOne(Integer.parseInt(vDbId[i]));
					BagMt bagMt = bagMtService.findByName(bagNm.trim());
					TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
					
					 tempBagStone = 0;
					 tempBagCarat = 0.0;
					
					tempBagStone = Integer.parseInt(vTrfBagStone[i]);
					tempBagCarat = Double.parseDouble( vTrfBagCarat[i]);
					
					df.format(tempBagCarat);
					
					//department = departmentService.findOne(cDeptId);
					
					
					if(tempBagCarat > 0 && tempBagStone > 0){
					
							//update in tranMt
						
							if(tranMt != null){ 
								Double recWt = tranMt.getRecWt();
								if(recWt > 0){
									Double tempVal = tempBagCarat/5;
									Double tempValSet = recWt - tempVal;
									tranMt.setRecWt(Double.parseDouble(df.format(tempValSet)));
									
									tranMtService.save(tranMt);
								}
							}
							
							
							
							//update in tranDt
							
							TranDt tranDt = tranDtService.findOne(Integer.parseInt(vDbId[i]));
							
							tranDt.setStone( tranDt.getStone() - tempBagStone);	
							tranDt.setCarat(Double.parseDouble(df.format(tranDt.getCarat() - tempBagCarat)));
							
							if(tranDt.getStone() == 0 && (tranDt.getCarat() == 0 || tranDt.getCarat() == 0.0)){
								tranDt.setCurrStk(false);
							}
							
							tranDtService.save(tranDt);
							
							//new entry in stoneTran
							
							StoneTran stoneTran = new StoneTran();
							stoneTran.setBagMt(bagMt);
							stoneTran.setStone(tempBagStone);
							stoneTran.setCarat(tempBagCarat);
							stoneTran.setInOutFld("C");
							stoneTran.setDeptLocation(departmentService.findByName("Diamond"));
							//stoneTran.setParty(diamondBaggingOld.getBagMt().getOrderMt().getParty());
							
							/*if(vChangingType[i].equalsIgnoreCase("Normal")){
								stoneTran.setTranType("ChangingReturn");
								stoneTran.setBalStone(tempBagStone);
								stoneTran.setBalCarat(tempBagCarat);
							}else if(vChangingType[i].equalsIgnoreCase("Broken")){
								stoneTran.setTranType("Broken");
								stoneTran.setBalStone(tempBagStone*-1);
								stoneTran.setBalCarat(tempBagCarat*-1);
							}else{
								stoneTran.setTranType("Fallen");
								stoneTran.setBalStone(tempBagStone*-1);
								stoneTran.setBalCarat(tempBagCarat*-1);
							}*/
							
							//stoneTran.setRefTranId(findByUniqueId(diamondBaggingNew.getUniqueId()).getId());
							stoneTran.setBagSrNo(tranDt.getBagSrNo());
							stoneTran.setDepartment(tranDt.getDepartment()); 
							stoneTran.setQuality(tranDt.getQuality());
						//	stoneTran.setRate(tranDt.getStnRate());
							stoneTran.setSetting(tranDt.getSetting() == null ? null : tranDt.getSetting());
							stoneTran.setSettingType(tranDt.getSettingType() == null ? null : tranDt.getSettingType());
							stoneTran.setShape(tranDt.getShape());
							stoneTran.setSize(tranDt.getSize());
							stoneTran.setSizeGroup(tranDt.getSizeGroup());
							stoneTran.setSieve(tranDt.getSieve());
							stoneTran.setStoneType(tranDt.getStoneType());
							stoneTran.setSubShape(tranDt.getSubShape() == null ? null : tranDt.getSubShape());
							stoneTran.setDeactive(false);
							stoneTran.setCreatedBy(principal.getName());
							stoneTran.setCreatedDt(new java.util.Date());
							
						
									
							stoneTranService.save(stoneTran);
							
					} // ending if
			
					
					
				} // ending for second for-loop
				
						
		
		} // ending first if
		
		// TODO Auto-generated method stub
		return retVal;
	}
	
	
	public String diamondChangingAddBreakUp( String pOIds, String trfBalStone,String trfBalCarat,Integer dbId,String cSetting,String cSettingType,
			String tempDeptNm,Principal principal){
		
		String retVal = "1";
		Date aDdate = null;
		
		if(pOIds.length() == 0){
			retVal = "-1";
			
		}else{
		
				String vStnDtId[] 	  = pOIds.split(",");
				String vTrfBalStone[] = trfBalStone.split(",");
				String vTrfBalCarat[] = trfBalCarat.split(",");
				String vSetting[] 	  = cSetting.split(",");
				String vSettingType[] = cSettingType.split(",");
				
				aDdate = new java.util.Date();
				DecimalFormat df = new DecimalFormat("#.###");
				
				
				for(int j=0; j < vStnDtId.length; j++){
					String setNm = vSetting[j];
					if(setNm.equals("-")){
						retVal = "-2";
						return retVal;
					}
					
					String setTypeNm = vSettingType[j];
					if(setTypeNm.equals("-")){
						retVal = "-3";
						return retVal;
					}
					
				}
				
				
				
				for (int j = 0; j < vStnDtId.length; j++) {

					StoneInwardDt stoneInwardPk = stoneInwardDtService
							.findOne(Integer.parseInt(vStnDtId[j]));

					Double stockCarat = stoneInwardPk.getBalCarat();
					Double currTrfCarat = Double.parseDouble(vTrfBalCarat[j]);

					if (stockCarat < currTrfCarat) {
						retVal = "-4";
						break;
					}

			

				} // ending the first for loop

				if (retVal == "-4") {
					return retVal;
				}
				
				
				
				
				
				
				
				
				
				
				Department department = departmentService.findByName(tempDeptNm.trim());

				for(int i=0; i < vStnDtId.length; i++){
					
					StoneInwardDt stoneInwardDt = stoneInwardDtService.findOne(Integer.parseInt(vStnDtId[i]));
					DiamondBagging diamondBaggingOd = findOne(dbId); 
					TranMt tranMt = tranMtService.findByBagMtCurrStk(diamondBaggingOd.getBagMt(), true);
					
					Integer tempBagStone = 0;
					Double tempBagCarat = 0.000;
					
					
					tempBagStone = Integer.parseInt(vTrfBalStone[i]);
					tempBagCarat = Double.parseDouble( vTrfBalCarat[i]);
					
					df.format(tempBagCarat);
					
					String settingName = vSetting[i];
					Setting setting = settingService.findByName(settingName.trim());
					
					String settingTypeName = vSettingType[i];
					SettingType settingType = settingTypeService.findByName(settingTypeName.trim());
					
					
					
					if(tempBagCarat > 0 && tempBagStone > 0){
					
							//new entry for DiamondBagging
						
							
							DiamondBagging diamondBaggingNew = new DiamondBagging();
							
							diamondBaggingNew.setBagSrNo(diamondBaggingOd.getBagSrNo());
							diamondBaggingNew.setBalCarat(tempBagCarat );
							diamondBaggingNew.setBalStone(tempBagStone );
							diamondBaggingNew.setCarat(tempBagCarat);
							diamondBaggingNew.setStone(tempBagStone);
							diamondBaggingNew.setTranType("ChangingIssue");
							diamondBaggingNew.setInOutFld("C");
							diamondBaggingNew.setBagMt(diamondBaggingOd.getBagMt());
							diamondBaggingNew.setDepartment(department);
							diamondBaggingNew.setBagPcs(diamondBaggingOd.getBagPcs());
							diamondBaggingNew.setShape(stoneInwardDt.getShape());
							diamondBaggingNew.setQuality(stoneInwardDt.getQuality());
							diamondBaggingNew.setSize(stoneInwardDt.getSize());
							diamondBaggingNew.setSizeGroup(stoneInwardDt.getSizeGroup());
							diamondBaggingNew.setSieve(stoneInwardDt.getSieve());
							diamondBaggingNew.setSetting(setting);
							diamondBaggingNew.setSettingType(settingType);
							diamondBaggingNew.setStoneInwardDt(stoneInwardDt);
							diamondBaggingNew.setStnRate(stoneInwardDt.getRate());
							diamondBaggingNew.setOrderDt(diamondBaggingOd.getOrderDt());
							diamondBaggingNew.setOrderStnDt(diamondBaggingOd.getOrderStnDt());
							diamondBaggingNew.setStoneType(stoneInwardDt.getStoneType());
							diamondBaggingNew.setSubShape(stoneInwardDt.getSubShape() == null ? null : stoneInwardDt.getSubShape());
							diamondBaggingNew.setUniqueId(aDdate.getTime()+i);
							diamondBaggingNew.setDeactive(false);
							diamondBaggingNew.setCenterStone(diamondBaggingOd.getCenterStone() !=null ?  diamondBaggingOd.getCenterStone():false);
							diamondBaggingNew.setCreatedBy(principal.getName());
							diamondBaggingNew.setCreatedDt(new java.util.Date());
							if(tranMt != null){
								diamondBaggingNew.setRefTranId(tranMt.getId());
							}
							
							
							save(diamondBaggingNew);
							
							//update in stoneInward
							
							/*StoneInwardDt stoneInwardDtupDate = stoneInwardDtService.findOne(Integer.parseInt(vStnDtId[i]));*/
							stoneInwardDt.setBalCarat(Double.parseDouble(df.format(stoneInwardDt.getBalCarat() - tempBagCarat)));
							stoneInwardDt.setBalStone(stoneInwardDt.getBalStone() - tempBagStone);
							
							stoneInwardDtService.save(stoneInwardDt);
							
							
							//update in tranMt
							
							
							
							if(tranMt != null){
								Double recWt = tranMt.getRecWt();
								if(recWt > 0){
									Double tempVal = tempBagCarat/5;
									Double tempValSet = recWt + tempVal;
									tranMt.setRecWt(Double.parseDouble(df.format(tempValSet)));
									
									tranMtService.save(tranMt);
								}
							}
							
							
							
							//update in tranDt
							
							TranDt tranDt = tranDtService.findByBagMtAndBagSrNoAndCurrStk(diamondBaggingOd.getBagMt(), diamondBaggingOd.getBagSrNo(), true);
							tranDt.setStone( tranDt.getStone() + tempBagStone);
							tranDt.setCarat(Double.parseDouble(df.format(tranDt.getCarat() + tempBagCarat)));
							tranDtService.save(tranDt);
							
							//new entry in stoneTran
							
							StoneTran stoneTran = new StoneTran();
							stoneTran.setBagMt(diamondBaggingOd.getBagMt());
							stoneTran.setBalStone(tempBagStone * -1);
							stoneTran.setBalCarat(tempBagCarat * -1);
							stoneTran.setStone(tempBagStone);
							stoneTran.setCarat(tempBagCarat);
							stoneTran.setInOutFld("D");
							stoneTran.setDeptLocation(departmentService.findByName("Diamond"));
							stoneTran.setParty(diamondBaggingOd.getBagMt().getOrderMt().getParty());
							stoneTran.setTranType("ChangingIssue");
							stoneTran.setStnRecDtId(stoneInwardDt.getId());
							stoneTran.setRefTranId(findByUniqueId(diamondBaggingNew.getUniqueId()).getId());
							stoneTran.setBagSrNo(diamondBaggingOd.getBagSrNo());
							stoneTran.setDepartment(tranDt.getDepartment()); 
							stoneTran.setQuality(stoneInwardDt.getQuality());
							stoneTran.setRate(stoneInwardDt.getRate());
							stoneTran.setSetting(setting);
							stoneTran.setSettingType(settingType);
							stoneTran.setShape(stoneInwardDt.getShape());
							stoneTran.setSize(stoneInwardDt.getSize());
							stoneTran.setSizeGroup(stoneInwardDt.getSizeGroup());
							stoneTran.setSieve(stoneInwardDt.getSieve());
							stoneTran.setStoneType(stoneInwardDt.getStoneType());
							stoneTran.setSubShape(stoneInwardDt.getSubShape() == null ? null : stoneInwardDt.getSubShape());
							stoneTran.setDeactive(false);
							stoneTran.setCreatedBy(principal.getName());
							stoneTran.setCreatedDt(new java.util.Date());
									
							stoneTranService.save(stoneTran);
							
					} // ending if
			
					
					
				} // ending for loop
				
				
				
		}
		
		
		return retVal;
	}

	@Override
	public String saveAdjustment(String stnPk, String trfStone,
			String trfCarat, String bagNm, Integer ordStnDtId,
			Principal principal) {

		String retVal = "";

		if (stnPk.length() > 0) {

			String vStnPk[] = stnPk.split(",");
			/*
			 * String vBalStn[] = balStone.split(","); String vBalCart[] =
			 * balCarat.split(",");
			 */
			String vTrfStn[] = trfStone.split(",");
			String vTrfCart[] = trfCarat.split(",");

			DecimalFormat df = new DecimalFormat("#.###");

			for (int j = 0; j < vStnPk.length; j++) {

				StoneInwardDt stoneInwardPk = stoneInwardDtService
						.findOne(Integer.parseInt(vStnPk[j]));

				Double stockCarat = stoneInwardPk.getBalCarat();
				Double currTrfCarat = Double.parseDouble(vTrfCart[j]);

				if (stockCarat < currTrfCarat) {
					retVal = "-3";
					break;
				}

				if ((stoneInwardPk.getSieve() == null)
						|| (stoneInwardPk.getSizeGroup() == null)
						|| (stoneInwardPk.getSize() == null)
						|| (stoneInwardPk.getQuality() == null)) {

					retVal = "-4";
					break;
				}

			} // ending the first for loop

			if (retVal == "-3") {
				return retVal;
			}

			if (retVal == "-4") {
				return retVal;
			}

			BagMt bagMt = bagMtService.findByName(bagNm.trim());
			OrderStnDt orderStnDt = orderStnDtService.findOne(ordStnDtId);

			for (int i = 0; i < vStnPk.length; i++) {

				StoneInwardDt stoneInwardDt = stoneInwardDtService
						.findOne(Integer.parseInt(vStnPk[i]));

				/*
				 * Integer sBalStone = Integer.parseInt(vBalStn[i]) ; Double
				 * sBalCarat = Double.parseDouble(vBalCart[i]);
				 */

				Integer sTrfStone = Integer.parseInt(vTrfStn[i]);
				Double sTrfCarat = Double.parseDouble(vTrfCart[i]);

				if (sTrfStone <= 0 || sTrfCarat <= 0) {
					continue;
				}

				DiamondBagging diamondBagging = new DiamondBagging();
				diamondBagging.setBagSrNo(orderStnDt.getSrNo());
				diamondBagging.setCenterStone(orderStnDt.getCenterStone() != null ? orderStnDt.getCenterStone() : false);
				diamondBagging.setBagMt(bagMt);
				diamondBagging.setBagPcs(bagMt.getQty());
				diamondBagging.setShape(stoneInwardDt.getShape());
				diamondBagging.setQuality(stoneInwardDt.getQuality());
				diamondBagging.setSize(stoneInwardDt.getSize());
				diamondBagging.setSizeGroup(stoneInwardDt.getSizeGroup());
				diamondBagging.setSieve(stoneInwardDt.getSieve());
				diamondBagging.setStone(sTrfStone);
				diamondBagging.setCarat(Double.parseDouble(df.format(sTrfCarat)));
				diamondBagging.setSetting(orderStnDt.getSetting());
				diamondBagging.setSettingType(orderStnDt.getSettingType());
				diamondBagging.setStoneInwardDt(stoneInwardDt);
				diamondBagging.setStnRate(stoneInwardDt.getRate());
				diamondBagging.setBalCarat(Double.parseDouble(df.format(sTrfCarat)));
				diamondBagging.setBalStone(sTrfStone);
				diamondBagging.setOrderDt(bagMt.getOrderDt());
				diamondBagging.setInOutFld("C");
				diamondBagging.setTranType("Bagging");
				diamondBagging.setOrderStnDt(orderStnDt);
				diamondBagging.setStoneType(stoneInwardDt.getStoneType());
				diamondBagging.setSubShape(stoneInwardDt.getSubShape());

				diamondBagging.setCreatedDt(new java.util.Date());
				diamondBagging.setCreatedBy(principal.getName());
				diamondBagging.setDeactive(false);

				save(diamondBagging);

				// ----Edit Code Of StoneInwardDt ---->>
				stoneInwardDt.setBalStone(sTrfStone >= 0 ? (stoneInwardDt.getBalStone() - sTrfStone) : 0);
				stoneInwardDt.setBalCarat(Double.parseDouble(df.format(stoneInwardDt.getBalCarat() - Double.parseDouble(df.format(sTrfCarat)))));
				stoneInwardDtService.save(stoneInwardDt);

			} // ending the second for loop

			retVal = "success";

		} else {
			retVal = "-2";
		}

		return retVal;	
		
	}

	@Override
	public List<DiamondBagging> findByBagMtAndTranTypeAndTransferedAndDeactive(
			BagMt bagMt,String tranType ,Boolean transfered, Boolean deactive) {
		return diamondBaggingRepository.findByBagMtAndTranTypeAndTransferedAndDeactive(bagMt,tranType,transfered, deactive);
	}
	
	
	@Override
	public String diamondChangingAddNewBreakUp(Integer tranDtId,
			Integer reqStone, Double reqCarat, Principal principal,String stockType,Boolean allowNegative,Date tranDate) {
		
		String retVal = "success";
		
		TranDt tranDtObj = tranDtService.findOne(tranDtId);
		
		Double reqCarat1 =reqCarat;
		
		Integer reqStone1 =reqStone;
		
	
		
		TranMt tranMt = tranMtService.findOne(tranDtObj.getTranMt().getId());
		
		
		Department location=  tranMt.getDepartment();
		
		User user =userService.findByName(principal.getName());
		LocationRights locationRights = locationRightsService.findByUserAndDepartmentAndDeactive(user, location, false);
		
		
		
		 if(location.getStoneStk().equals(true) && locationRights !=null ) {
			 
			 if(!allowNegative.equals(true)){
					
					
					
					Double stockBalance = stoneTranService.getStockBalance(location.getId(),tranDtObj.getStoneType().getId(), tranDtObj.getShape().getId(), 
							tranDtObj.getQuality().getId(), tranDtObj.getSizeGroup().getId(), tranDtObj.getSieve(), stockType);
					
					
					if(reqCarat > stockBalance){
						return "Warning:Stock Not Availabe, ( "+stockBalance+" Is Available)";
					}
					
					}
					
			 
			 List<StoneTran>stoneTrans =stoneTranService.getDiamondStockRate(location.getId(),tranDtObj.getStoneType().getName(),
						tranDtObj.getShape().getName(), tranDtObj.getQuality().getName(), tranDtObj.getSize(), tranDtObj.getSieve());
			 
			 
			 	Double valSum=0.0;
				for(StoneTran stoneTran2 :stoneTrans) {
					if (reqCarat > 0) {
						if (stoneTran2.getBalCarat() >= reqCarat) {
							
							StoneTran stoneTran = new StoneTran();
							stoneTran.setBagMt(tranDtObj.getBagMt());
							stoneTran.setBalStone(reqStone * -1);
							stoneTran.setBalCarat(reqCarat * -1);
							stoneTran.setStone(reqStone);
							stoneTran.setCarat(reqCarat);
							stoneTran.setInOutFld("D");
							stoneTran.setDeptLocation(tranMt.getDepartment());
							stoneTran.setTranType("ChangingIssue");
							stoneTran.setRefTranId(tranDtObj.getId());
							stoneTran.setPartNm(tranDtObj.getPartNm());
							stoneTran.setTranMtId(tranDtObj.getTranMt().getId());
							stoneTran.setBagSrNo(tranDtObj.getBagSrNo());
							stoneTran.setDepartment(tranDtObj.getDepartment()); 
							stoneTran.setQuality(tranDtObj.getQuality());
							stoneTran.setCenterStone(tranDtObj.getCenterStone());
							stoneTran.setSetting(tranDtObj.getSetting());
							stoneTran.setSettingType(tranDtObj.getSettingType());
							stoneTran.setShape(tranDtObj.getShape());
							stoneTran.setSize(tranDtObj.getSize());
							stoneTran.setSizeGroup(tranDtObj.getSizeGroup());
							stoneTran.setSieve(tranDtObj.getSieve());
							stoneTran.setStoneType(tranDtObj.getStoneType());
							stoneTran.setSubShape(tranDtObj.getSubShape() == null ? null : tranDtObj.getSubShape());
							stoneTran.setDeactive(false);
							stoneTran.setCreatedBy(principal.getName());
							stoneTran.setCreatedDt(new java.util.Date());
							stoneTran.setTranDate(tranDate);
							stoneTran.setRate(stoneTran2.getRate());
							stoneTran.setAvgRate(stoneTran2.getRate());
							stoneTran.setTransferRate((double)Math.round((stoneTran.getAvgRate()+(stoneTran.getAvgRate()*tranDtObj.getStoneType().getTransferRatePerc()/100))*100)/100);
							stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*tranDtObj.getStoneType().getFactoryRatePerc()/100))*100)/100);
							stoneTran.setLotNo(stoneTran2.getLotNo());		
							stoneTranService.save(stoneTran);
							
							  valSum += Math.round((reqCarat*stoneTran2.getRate())*100.0)/100.0;
							  
							
							break;
							
						}else {
							reqCarat = Math.round((reqCarat - stoneTran2.getBalCarat()) * 1000.0) / 1000.0;
							reqStone = Math.round(reqStone - stoneTran2.getBalStone());
							
							
							StoneTran stoneTran = new StoneTran();
							stoneTran.setBagMt(tranDtObj.getBagMt());
							stoneTran.setBalStone(stoneTran2.getBalStone() * -1);
							stoneTran.setBalCarat(stoneTran2.getBalCarat() * -1);
							stoneTran.setStone(stoneTran2.getBalStone());
							stoneTran.setCarat(stoneTran2.getBalCarat());
							stoneTran.setInOutFld("D");
							stoneTran.setDeptLocation(tranMt.getDepartment());
							stoneTran.setTranType("ChangingIssue");
							stoneTran.setRefTranId(tranDtObj.getId());
							stoneTran.setPartNm(tranDtObj.getPartNm());
							stoneTran.setTranMtId(tranDtObj.getTranMt().getId());
							stoneTran.setBagSrNo(tranDtObj.getBagSrNo());
							stoneTran.setDepartment(tranDtObj.getDepartment()); 
							stoneTran.setQuality(tranDtObj.getQuality());
							stoneTran.setCenterStone(tranDtObj.getCenterStone());
							stoneTran.setSetting(tranDtObj.getSetting());
							stoneTran.setSettingType(tranDtObj.getSettingType());
							stoneTran.setShape(tranDtObj.getShape());
							stoneTran.setSize(tranDtObj.getSize());
							stoneTran.setSizeGroup(tranDtObj.getSizeGroup());
							stoneTran.setSieve(tranDtObj.getSieve());
							stoneTran.setStoneType(tranDtObj.getStoneType());
							stoneTran.setSubShape(tranDtObj.getSubShape() == null ? null : tranDtObj.getSubShape());
							stoneTran.setDeactive(false);
							stoneTran.setCreatedBy(principal.getName());
							stoneTran.setCreatedDt(new java.util.Date());
							stoneTran.setTranDate(tranDate);
							stoneTran.setRate(stoneTran2.getRate());
							stoneTran.setAvgRate(stoneTran2.getRate());
							stoneTran.setTransferRate((double)Math.round((stoneTran.getAvgRate()+(stoneTran.getAvgRate()*tranDtObj.getStoneType().getTransferRatePerc()/100))*100)/100);
							stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*tranDtObj.getStoneType().getFactoryRatePerc()/100))*100)/100);
							stoneTran.setLotNo(stoneTran2.getLotNo());		
							stoneTranService.save(stoneTran);
							
							  valSum += Math.round((stoneTran2.getBalCarat()*stoneTran2.getRate())*100.0)/100.0;
							
							
							
						}
						
						
					}
				}
					
					
					
					
					if(tranMt != null){
						Double recWt = tranMt.getRecWt();
						if(recWt > 0){
							Double tempVal = reqCarat1/5;
							Double tempValSet = recWt + tempVal;
							tranMt.setRecWt(Math.round(tempValSet*1000.0)/1000.0);
							
							tranMtService.save(tranMt);
						}
					}
					
				

					
					Double bagVal=Math.round((tranDtObj.getCarat()*tranDtObj.getRate())*100.0)/100.0;
					Double addedVal =Math.round((valSum)*100.0)/100.0;
					Double totVal=Math.round((bagVal+addedVal)*100.0)/100.0;
					Double totWt=Math.round((tranDtObj.getCarat()+reqCarat1)*1000.0)/1000.0;
					Double bagRate = Math.round((totVal/totWt)*100.0)/100.0;
					
					
					
					tranDtObj.setStone( tranDtObj.getStone() + reqStone1);
					tranDtObj.setCarat(Math.round((tranDtObj.getCarat() + reqCarat1)*1000.0)/1000.0);
					tranDtObj.setRate(bagRate);
					tranDtObj.setAvgRate(bagRate);
					tranDtObj.setTransferRate((double)Math.round((bagRate+(bagRate*tranDtObj.getStoneType().getTransferRatePerc()/100))*100)/100);
					tranDtObj.setFactoryRate((double)Math.round((tranDtObj.getTransferRate()+(tranDtObj.getTransferRate()*tranDtObj.getStoneType().getFactoryRatePerc()/100))*100)/100);
					tranDtService.save(tranDtObj);
					
					
				
					
					
					return retVal;
			 
		 }else {
			 return "Warning : Permission Denied";
		 }
		
		
		
		
		
	}
	
	
	
	
	@SuppressWarnings("unused")
	@Override
	public String diamondChangingNewBreakUpTrf(String bagNm, String data,
			Principal principal,Integer partId,String vSize,String stockType,Boolean allowNegative,Date tranDate) {
		
		String retVal="-1";
		BagMt bagMt = bagMtService.findByName(bagNm);
		Shape shapeTmp = null;
		JSONArray jsonBagDt = new JSONArray(data);
		
		TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
		
		LookUpMast partMast = lookUpMastService.findOne(partId);
		
		Department location=  tranMt.getDepartment();
		
		User user =userService.findByName(principal.getName());
		LocationRights locationRights = locationRightsService.findByUserAndDepartmentAndDeactive(user, location, false);
		
		
		
		 if(location.getStoneStk().equals(true) && locationRights !=null ) {
		
		
		
		if(!allowNegative.equals(true)){
			
			String stockSrNo = "";
					
			
			
			
			
			for (int y = 0; y < jsonBagDt.length(); y++) {
				
				
				JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
				
				  
				
				Double stockBal = stoneTranService.getStockBalanceStr(location.getId(),dataBagDt.get("stoneType").toString(), dataBagDt.get("shape").toString(), 
						dataBagDt.get("quality").toString(), dataBagDt.get("sizeGroup").toString(), dataBagDt.get("sieve").toString(), stockType,bagMt.getOrderDt().getId());
				
				if(stockBal < Double.parseDouble(dataBagDt.get("trfCarat").toString())){
					if(stockSrNo == ""){
						stockSrNo = dataBagDt.get("serialNo").toString();
					}else{
						stockSrNo =  stockSrNo+","+dataBagDt.get("serialNo").toString();
					}
				}
				
			}
			
			
			if(stockSrNo != ""){
				return "Warning : Stock Not Found of BagSrNo ["+stockSrNo+"]";
			}
			
			
			
			 }
		
		
	for (int y = 0; y < jsonBagDt.length(); y++) {
			
			Integer maxBagSrNo = stoneTranService.getMaxSrno(bagMt.getId());
			
			if(maxBagSrNo == null || maxBagSrNo <100){
				maxBagSrNo=100;
			}
				
			JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
			
			Double reqCarat=Double.parseDouble(dataBagDt.get("trfCarat").toString());
			Integer reqStone = Integer.parseInt(dataBagDt.get("trfStone").toString());
			
			Double stn = Double.parseDouble(dataBagDt.get("trfStone").toString());
			Integer stnInt = stn.intValue();
			shapeTmp = shapeService.findByName(dataBagDt.getString("shape"));
			
			Double recWt = tranMt.getRecWt();
			if(recWt > 0){
				Double tempVal = Double.parseDouble(dataBagDt.get("trfCarat").toString())/5;
				Double tempValSet = recWt + tempVal;
				tranMt.setRecWt(Math.round(tempValSet*1000.0)/1000.0);
				tranMtService.save(tranMt);
			}
			
			SizeGroup sizeGroup = sizeGroupService.findByShapeAndNameAndDeactive(shapeTmp, dataBagDt.get("sizeGroup").toString().trim(),false);
			
			String sizeNm="";
			String sieve ="";
		
			if(vSize != null &&  vSize != ""){
				
			List<StoneChart> stoneCharts = stoneChartService.findBySizeGroupAndSizeAndDeactive(sizeGroup, vSize, false);
			for (StoneChart stoneChart : stoneCharts) {
				sizeNm = stoneChart.getSize();
				sieve = stoneChart.getSieve();
			}
			}else {
				sizeNm = dataBagDt.get("size").toString();
				sieve = dataBagDt.get("sieve").toString();
			}
			
			
			StoneType stoneType= stoneTypeService.findByName(dataBagDt.get("stoneType").toString());
			Quality quality =qualityService.findByShapeAndNameAndDeactive(shapeTmp,dataBagDt.get("quality").toString(),false);
			
			
			
			
			
			
			
			
			
			
			TranDt tranDtNew = new TranDt();
			tranDtNew.setBagMt(bagMt);
			tranDtNew.setPcs(bagMt.getQty());
			tranDtNew.setCurrStk(true);
			tranDtNew.setCreatedBy(principal.getName());
			tranDtNew.setCreatedDate(new java.util.Date());
			tranDtNew.setDepartment(tranMt.getDepartment());
			tranDtNew.setDeptFrom(tranMt.getDeptFrom());
			tranDtNew.setBagSrNo(maxBagSrNo==null?1: maxBagSrNo+1);
			tranDtNew.setCarat(reqCarat);
			tranDtNew.setStone(reqStone);
			tranDtNew.setPartNm(partMast);
			tranDtNew.setSieve(sieve);
			tranDtNew.setSize(sizeNm);
			tranDtNew.setTranDate(new java.util.Date());
			tranDtNew.setQuality(quality);
			tranDtNew.setSetting(settingService.findByName(dataBagDt.get("setting").toString()));
			tranDtNew.setSettingType(settingTypeService.findByName(dataBagDt.get("settingType").toString()));
			tranDtNew.setShape(shapeTmp);
			tranDtNew.setSizeGroup(sizeGroup);
			tranDtNew.setTranMt(tranMt);
			tranDtNew.setStoneType(stoneType);
			if(dataBagDt.getString("subShape").toString() != ""){
				tranDtNew.setSubShape(subShapeService.findByShapeAndName(shapeTmp, dataBagDt.getString("subShape")));
			}
			
			tranDtNew.setTranDate(tranDate);
			
			//tranDtNew.setRate(avgRate);
			//tranDtNew.setAvgRate(avgRate);
			//tranDtNew.setTransferRate((double)Math.round((avgRate+(avgRate*stoneType.getTransferRatePerc()/100))*100)/100);
			//tranDtNew.setFactoryRate((double)Math.round((tranDtNew.getTransferRate()+(tranDtNew.getTransferRate()*stoneType.getFactoryRatePerc()/100))*100)/100);
			
			
			tranDtService.save(tranDtNew);
			
			
			
			
			

			List<StoneTran>stoneTrans =stoneTranService.getDiamondStockRate(location.getId(),stoneType.getName(),
					shapeTmp.getName(), quality.getName(), sizeNm,sieve);
			
			
			
			
			
			Double valSum=0.0;
			for(StoneTran stoneTran2 :stoneTrans) {
				if (reqCarat > 0) {
					if (stoneTran2.getBalCarat() >= reqCarat) {
						
						
						
						StoneTran stoneTran = new StoneTran();
						stoneTran.setBagMt(bagMt);
						stoneTran.setBalStone(reqStone * -1);
						stoneTran.setBalCarat(reqCarat * -1);
						stoneTran.setStone(reqStone);
						stoneTran.setCarat(reqCarat);
						stoneTran.setInOutFld("D");
						stoneTran.setPartNm(partMast);
						stoneTran.setTranMtId(tranMt.getId());
						stoneTran.setDeptLocation(tranMt.getDepartment());
						stoneTran.setParty(bagMt.getOrderMt().getParty());
						stoneTran.setTranType("ChangingIssue");
						stoneTran.setRefTranId(tranDtNew.getId());
						//stoneTran.setStnRecDtId(stoneInwardDt.getId());
						stoneTran.setBagSrNo(maxBagSrNo==null ? 1: maxBagSrNo+1);
						stoneTran.setDepartment(tranMt.getDepartment()); 
						stoneTran.setQuality(qualityService.findByShapeAndNameAndDeactive(shapeTmp,dataBagDt.get("quality").toString(),false));
						//stoneTran.setRate(stoneInwardDt.getRate());
						stoneTran.setSetting(settingService.findByName(dataBagDt.get("setting").toString()));
						stoneTran.setSettingType(settingTypeService.findByName(dataBagDt.get("settingType").toString()));
						stoneTran.setShape(shapeTmp);
						stoneTran.setSize(sizeNm);
						stoneTran.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(shapeTmp, dataBagDt.get("sizeGroup").toString().trim(),false));
						stoneTran.setSieve(sieve);
						stoneTran.setStoneType(stoneTypeService.findByName(dataBagDt.get("stoneType").toString()));
						if(dataBagDt.getString("subShape").toString() != ""){
							stoneTran.setSubShape(subShapeService.findByShapeAndName(shapeTmp, dataBagDt.getString("subShape")));
						}
						
						stoneTran.setCreatedBy(principal.getName());
						stoneTran.setCreatedDt(new java.util.Date());
						stoneTran.setTranDate(tranDate);
						
						stoneTran.setRate(stoneTran2.getRate());
						stoneTran.setAvgRate(stoneTran2.getRate());
						stoneTran.setTransferRate((double)Math.round((stoneTran2.getRate()+(stoneTran2.getRate()*stoneType.getTransferRatePerc()/100))*100)/100);
						stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*stoneType.getFactoryRatePerc()/100))*100)/100);
						stoneTran.setLotNo(stoneTran2.getLotNo());
								
						stoneTranService.save(stoneTran);	
						
						
						  valSum += Math.round((reqCarat*stoneTran2.getRate())*100.0)/100.0;
						  
						
						break;
						
					}else {
						reqCarat = Math.round((reqCarat - stoneTran2.getBalCarat()) * 1000.0) / 1000.0;
						reqStone = Math.round(reqStone - stoneTran2.getBalStone());
						
						
						StoneTran stoneTran = new StoneTran();
						stoneTran.setBagMt(bagMt);
						stoneTran.setBalStone(stoneTran2.getBalStone() * -1);
						stoneTran.setBalCarat(stoneTran2.getBalCarat() * -1);
						stoneTran.setStone(stoneTran2.getBalStone());
						stoneTran.setCarat(stoneTran2.getBalCarat());
						stoneTran.setInOutFld("D");
						stoneTran.setPartNm(partMast);
						stoneTran.setTranMtId(tranMt.getId());
						stoneTran.setDeptLocation(tranMt.getDepartment());
						stoneTran.setParty(bagMt.getOrderMt().getParty());
						stoneTran.setTranType("ChangingIssue");
						stoneTran.setRefTranId(tranDtNew.getId());
						//stoneTran.setStnRecDtId(stoneInwardDt.getId());
						stoneTran.setBagSrNo(maxBagSrNo==null ? 1: maxBagSrNo+1);
						stoneTran.setDepartment(tranMt.getDepartment()); 
						stoneTran.setQuality(qualityService.findByShapeAndNameAndDeactive(shapeTmp,dataBagDt.get("quality").toString(),false));
						//stoneTran.setRate(stoneInwardDt.getRate());
						stoneTran.setSetting(settingService.findByName(dataBagDt.get("setting").toString()));
						stoneTran.setSettingType(settingTypeService.findByName(dataBagDt.get("settingType").toString()));
						stoneTran.setShape(shapeTmp);
						stoneTran.setSize(sizeNm);
						stoneTran.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(shapeTmp, dataBagDt.get("sizeGroup").toString().trim(),false));
						stoneTran.setSieve(sieve);
						stoneTran.setStoneType(stoneTypeService.findByName(dataBagDt.get("stoneType").toString()));
						if(dataBagDt.getString("subShape").toString() != ""){
							stoneTran.setSubShape(subShapeService.findByShapeAndName(shapeTmp, dataBagDt.getString("subShape")));
						}
						
						stoneTran.setCreatedBy(principal.getName());
						stoneTran.setCreatedDt(new java.util.Date());
						stoneTran.setTranDate(tranDate);
						
						stoneTran.setRate(stoneTran2.getRate());
						stoneTran.setAvgRate(stoneTran2.getRate());
						stoneTran.setTransferRate((double)Math.round((stoneTran2.getRate()+(stoneTran2.getRate()*stoneType.getTransferRatePerc()/100))*100)/100);
						stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*stoneType.getFactoryRatePerc()/100))*100)/100);
						stoneTran.setLotNo(stoneTran2.getLotNo());
								
						stoneTranService.save(stoneTran);	
						
						  valSum += Math.round((stoneTran2.getBalCarat()*stoneTran2.getRate())*100.0)/100.0;
						
						
						
					}
					
					
				}
			}
				
			
			Double avgRate= Math.round((valSum/Double.parseDouble(dataBagDt.get("trfCarat").toString()))*100.0)/100.0;
			
			tranDtNew.setRate(avgRate);
			tranDtNew.setAvgRate(avgRate);
			tranDtNew.setTransferRate((double)Math.round((avgRate+(avgRate*stoneType.getTransferRatePerc()/100))*100)/100);
			tranDtNew.setFactoryRate((double)Math.round((tranDtNew.getTransferRate()+(tranDtNew.getTransferRate()*stoneType.getFactoryRatePerc()/100))*100)/100);
			
			tranDtService.save(tranDtNew);
			
				/*
				 * Double valSum=0.0; Double caratSum=0.0; for (StoneTran stoneTran :
				 * stoneTrans) {
				 * 
				 * valSum +=
				 * Math.round((stoneTran.getBalCarat()*stoneTran.getRate())*100.0)/100.0;
				 * caratSum += Math.round(stoneTran.getBalCarat()*1000.0)/1000.0;
				 * 
				 * 
				 * }
				 * 
				 * Double avgRate= Math.round((valSum/caratSum)*100.0)/100.0;
				 */
			
			
			
			
			
			
			
		
			
		
			
			
			
	}
		
		
		
		}else {
			 return "Warning : Permission Denied";
		 }
		
	
		
		
	
		
		
		
		return retVal;
	}
	
	
	@Override
	public String diamondChangingSingleReturnBreakUp(Integer tranDtId,
			Integer retStone, Double retCarat, String changingType,
			Integer employeeId, Principal principal,Date tranDate) {
		
		
		String retVal = "success";
		
		TranDt tranDt = tranDtService.findOne(tranDtId);
		BagMt bagMt = bagMtService.findByName(tranDt.getBagMt().getName());
		TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
		
		
		Department location=  tranMt.getDepartment();
		
		User user =userService.findByName(principal.getName());
		LocationRights locationRights = locationRightsService.findByUserAndDepartmentAndDeactive(user, location, false);
		
		
		
		 if(location.getStoneStk().equals(true) && locationRights !=null ) {
		
		
		if(tranMt != null){ 
			Double recWt = tranMt.getRecWt();
			if(recWt > 0){
				Double tempVal = retCarat/5;
				Double tempValSet = recWt - tempVal;
				tranMt.setRecWt(Math.round(tempValSet*1000.0)/1000.0);
				tranMtService.save(tranMt);
			}
		}
		
		
		
		//update in tranDt
		tranDt.setStone( tranDt.getStone() - retStone);	
		tranDt.setCarat(Math.round((tranDt.getCarat() - retCarat)*1000.0)/1000.0);
		
		if(tranDt.getStone() == 0 && (tranDt.getCarat() == 0 || tranDt.getCarat() == 0.0)){
			tranDt.setCurrStk(false);
		}
		
		tranDtService.save(tranDt);
		
		//new entry in stoneTran
		
		StoneTran stoneTran = new StoneTran();
		stoneTran.setBagMt(bagMt);
		stoneTran.setStone(retStone);
		stoneTran.setCarat(retCarat);
		stoneTran.setInOutFld("C");
		stoneTran.setSordDtId(bagMt.getOrderDt().getId());
		stoneTran.setDeptLocation(tranMt.getDepartment());
		//stoneTran.setParty(diamondBaggingOld.getBagMt().getOrderMt().getParty());
		
		if(changingType.equalsIgnoreCase("Normal")){
			stoneTran.setTranType("ChangingReturn");
			stoneTran.setBalStone(retStone);
			stoneTran.setBalCarat(retCarat);
		}else if(changingType.equalsIgnoreCase("Broken")){
			stoneTran.setTranType("Broken");
			stoneTran.setBalStone(retStone*-1);
			stoneTran.setBalCarat(retCarat*-1);
			stoneTran.setEmployee(employeeService.findOne(employeeId));
		}else{
			stoneTran.setTranType("Fallen");
			stoneTran.setBalStone(retStone*-1);
			stoneTran.setBalCarat(retCarat*-1);
			stoneTran.setEmployee(employeeService.findOne(employeeId));
		}
		
		stoneTran.setRefTranId(tranDt.getId());
		stoneTran.setTranMtId(tranDt.getTranMt().getId());
		stoneTran.setPartNm(tranDt.getPartNm());
		stoneTran.setBagSrNo(tranDt.getBagSrNo());
		stoneTran.setDepartment(tranDt.getDepartment()); 
		stoneTran.setQuality(tranDt.getQuality());
		//stoneTran.setRate(tranDt.getRate());
		stoneTran.setSetting(tranDt.getSetting());
		stoneTran.setSettingType(tranDt.getSettingType());
		stoneTran.setShape(tranDt.getShape());
		stoneTran.setSize(tranDt.getSize());
		stoneTran.setSizeGroup(tranDt.getSizeGroup());
		stoneTran.setSordDtId(bagMt.getOrderDt().getId());
		stoneTran.setSieve(tranDt.getSieve());
		stoneTran.setStoneType(tranDt.getStoneType());
		stoneTran.setSubShape(tranDt.getSubShape() == null ? null : tranDt.getSubShape());
		stoneTran.setCreatedBy(principal.getName());
		stoneTran.setCreatedDt(new java.util.Date());
		stoneTran.setTranDate(tranDate);
		stoneTran.setRate(tranDt.getRate());
		stoneTran.setAvgRate(tranDt.getAvgRate());
		stoneTran.setFactoryRate(tranDt.getFactoryRate());
		stoneTran.setTransferRate(tranDt.getTransferRate());
		stoneTran.setLotNo("CHNG-"+bagMt.getId()+"-"+tranDt.getBagSrNo());
	
		
		OrderStnDt orderStnDt = orderStnDtService.findByOrderDtAndSrNoAndDeactive(bagMt.getOrderDt(), tranDt.getBagSrNo(), false);
		if(orderStnDt == null) {
			stoneTran.setDiaCateg("");
		}else {
			stoneTran.setDiaCateg(orderStnDt.getDiaCateg());	
		}
		
				
		stoneTranService.save(stoneTran);
		
		
		
		return retVal;
		
		
		 }else {
			 return "Warning: Permission Denied";
		 }
	}

	@Override
	public String diamondReturnSelected(String tblData, Principal principal,Date tranDate) {
		// TODO Auto-generated method stub
		
		String retVal="";
		
		JSONArray jsonArray = new JSONArray(tblData);
		
		
		for(int i=0;i<jsonArray.length();i++){
			
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			
			retVal =diamondChangingSingleReturnBreakUp(Integer.parseInt(jsonObject.get("id").toString()), Integer.parseInt(jsonObject.get("stones").toString()),
					Double.parseDouble(jsonObject.get("carat").toString()), "Normal", 0, principal,tranDate);
			
			
		}
		
		
		return retVal;
	}
	
	
	
	
	@Override
	public String diamondBaggingTransferOnBag(String bagNm, String data,Principal principal,String stockType,Boolean allowNegative,Date tranDate,String companyName,Integer deptId) {
		
		synchronized (this) {
			String retVal="-1";
			BagMt bagMt = bagMtService.findByName(bagNm.trim());
			Department department =departmentService.findOne(deptId);
			
			TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
			
			if(tranMt==null) {
				return "Warning : Bag Not in "+department.getName()+" Department";
			}
			
			
			List<TranDt> tranDts =tranDtService.findByBagMt(bagMt);
			if(tranDts.size()>0) {
				return "Warning : Not Allow Recored present in TranDt";
			}
				
			JSONArray jsonBagDt = new JSONArray(data);
			
			String tempSrNo = "";
			for (int y = 0; y < jsonBagDt.length(); y++) {
			
				JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
				
		
				if (dataBagDt.isNull("trfStone") || dataBagDt.get("trfStone").toString().equals("") 
						|| dataBagDt.isNull("trfCarat") || dataBagDt.get("trfCarat").toString().equals("")
						|| dataBagDt.isNull("quality") || dataBagDt.get("quality").toString().equals("")
						|| dataBagDt.isNull("sizeGroup") || dataBagDt.get("sizeGroup").toString().equals("")
						|| dataBagDt.isNull("stoneType") || dataBagDt.get("stoneType").toString().equals("")
						|| dataBagDt.isNull("shape") || dataBagDt.get("shape").toString().equals("")
						|| dataBagDt.isNull("mm") || dataBagDt.get("mm").toString().equals("")
						|| dataBagDt.isNull("sieve") || dataBagDt.get("sieve").toString().equals("")
						|| dataBagDt.isNull("setting") || dataBagDt.get("setting").toString().equals("")
						|| dataBagDt.isNull("setType") || dataBagDt.get("setType").toString().equals("")) {
					if(tempSrNo == ""){
						tempSrNo =  dataBagDt.get("bagSrno").toString();
					}else{
						tempSrNo =  tempSrNo+","+dataBagDt.get("bagSrno").toString();
					}
				}else{
					if(dataBagDt.getDouble("trfCarat") <= 0 ){
						if(tempSrNo == ""){
							tempSrNo =  dataBagDt.get("bagSrno").toString();
						}else{
							tempSrNo =  tempSrNo+","+dataBagDt.get("bagSrno").toString();
						}
					}
				}
				
			}
			
			
			if(tempSrNo != ""){
				String tempVal = "";
				if(tempSrNo != ""){
					tempVal = "Warning: TrfStone and TrfCarat or Qulaity or SizeGroup or StoneType or Shape or Size or Sieve or Setting or SettingType  Of BagSrNo ["+tempSrNo+"] Cannot be null or"
							+ " less than zero(0) or Blank";
				}
				
				return tempVal;
			}
			
			 String locationName="Diamond";
				
			
			  if(companyName.equalsIgnoreCase("nuance II")) { 
				  locationName="Bagging";
				  
			  }
			 
			
			  Department location=  departmentService.findByName(locationName);
			if(!allowNegative.equals(true)){
				String stockSrNo = "";
				
				
				  
				 
					
				  
				  
				
				for (int y = 0; y < jsonBagDt.length(); y++) {
					JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
					
					
					
					Double stockBal = stoneTranService.getStockBalanceStr(location.getId(),dataBagDt.get("stoneType").toString(), dataBagDt.get("shape").toString(), 
							dataBagDt.get("quality").toString(), dataBagDt.get("sizeGroup").toString(), dataBagDt.get("sieve").toString(), stockType,bagMt.getOrderDt().getId());
					
					if(stockBal < Double.parseDouble(dataBagDt.get("trfCarat").toString())){
						if(stockSrNo == ""){
							stockSrNo = dataBagDt.get("bagSrno").toString();
						}else{
							stockSrNo =  stockSrNo+","+dataBagDt.get("bagSrno").toString();
						}
					}
					
				}
				
				
				if(stockSrNo != ""){
					return "Warning : Stock Not Found of BagSrNo ["+stockSrNo+"]";
				}
				
			}
			
			
			
			
			
			
		/*	List<TranDt> tranDts = tranDtService.findByBagMt(bagMt);
			if(tranDts.size() > 0){
				return "Warning : Cannot Transfer Record As Bagging Already Done";
			}*/
			
			
			Double totCarat=0.0;
			for (int y = 0; y < jsonBagDt.length(); y++) {
				
				JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
				
				Shape shapeTmp = shapeService.findByName(dataBagDt.getString("shape"));
				
				TranDt tranDtTmp = new TranDt();
				
				tranDtTmp.setBagMt(bagMt);
				tranDtTmp.setBagSrNo((dataBagDt.get("bagSrno") == null || dataBagDt.get("bagSrno").toString().length() == 0) ? null : Integer.parseInt(dataBagDt.get("bagSrno").toString()));
				tranDtTmp.setCarat(Double.parseDouble(dataBagDt.get("trfCarat").toString()));
				tranDtTmp.setCurrStk(true);
				tranDtTmp.setDepartment(department);
				tranDtTmp.setDeptFrom(department);
				tranDtTmp.setIssDate(tranDate);
				tranDtTmp.setPcs(tranMt.getPcs());
				tranDtTmp.setQuality(qualityService.findByShapeAndNameAndDeactive(shapeTmp,dataBagDt.get("quality").toString(),false));
				tranDtTmp.setSetting(settingService.findByName(dataBagDt.get("setting").toString()));
				tranDtTmp.setSettingType(settingTypeService.findByName(dataBagDt.get("setType").toString()));
				tranDtTmp.setShape(shapeTmp);
				tranDtTmp.setSieve(dataBagDt.get("sieve").toString());
				tranDtTmp.setSize(dataBagDt.get("mm").toString());
				tranDtTmp.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(shapeTmp, dataBagDt.get("sizeGroup").toString().trim(),false));
				
				Double stn = Double.parseDouble(dataBagDt.get("trfStone").toString());
				Integer stnInt = stn.intValue();
				tranDtTmp.setStone(stnInt);
				
				tranDtTmp.setTime(new java.sql.Time(new java.util.Date().getTime()));
				tranDtTmp.setTranDate(tranDate);
				tranDtTmp.setTranMt(tranMt);
				tranDtTmp.setCreatedBy(principal.getName());
				tranDtTmp.setCreatedDate(new java.util.Date());
				tranDtTmp.setStoneType(stoneTypeService.findByName(dataBagDt.get("stoneType").toString()));
				tranDtTmp.setCenterStone(Boolean.parseBoolean(dataBagDt.get("centerStone").toString()));
				tranDtTmp.setPartNm(lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", dataBagDt.get("partNm").toString().trim(), false));
						
				tranDtService.save(tranDtTmp);
				
				totCarat +=tranDtTmp.getCarat();
				
				//---------------Entry In StoneTran --------------//
				
				StoneTran stoneTran = new StoneTran();
				stoneTran.setBalCarat(-(Double.parseDouble(dataBagDt.get("trfCarat").toString())));
				stoneTran.setBalStone(-(Integer.parseInt(dataBagDt.get("trfStone").toString())));
				stoneTran.setCarat(Double.parseDouble(dataBagDt.get("trfCarat").toString()));
				stoneTran.setDeptLocation(location);
				stoneTran.setSieve(dataBagDt.get("sieve").toString());
				stoneTran.setStone(stnInt);
				stoneTran.setSize(dataBagDt.get("mm").toString());
				stoneTran.setBagMt(bagMt);
				stoneTran.setInOutFld("D");
				stoneTran.setBagSrNo(Integer.parseInt(dataBagDt.get("bagSrno").toString()));
				stoneTran.setCreatedBy(principal.getName());
				stoneTran.setCreatedDt(new java.util.Date());
				stoneTran.setDeactive(false);
				stoneTran.setQuality(qualityService.findByShapeAndNameAndDeactive(shapeTmp,dataBagDt.get("quality").toString(),false)); 
				stoneTran.setStoneType(stoneTypeService.findByName(dataBagDt.get("stoneType").toString()));
				stoneTran.setShape(shapeTmp);
				stoneTran.setSubShape(subShapeService.findByShapeAndName(shapeTmp, dataBagDt.getString("subShape")));
				stoneTran.setSetting(settingService.findByName(dataBagDt.get("setting").toString()));
				stoneTran.setSettingType(settingTypeService.findByName(dataBagDt.get("setType").toString()));
				stoneTran.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(shapeTmp, dataBagDt.get("sizeGroup").toString().trim(),false));
				stoneTran.setTranType("Bagging");
				stoneTran.setCenterStone(Boolean.parseBoolean(dataBagDt.get("centerStone").toString()));
				stoneTran.setPartNm(lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", dataBagDt.get("partNm").toString().trim(), false));
				stoneTran.setTranDate(tranDate);
				stoneTran.setSordDtId(bagMt.getOrderDt().getId());
				stoneTran.setBagQty(bagMt.getQty());
				stoneTran.setDiaCateg(dataBagDt.getString("diaCateg"));
				
				if(companyName.equalsIgnoreCase("nuance II")) {
					
					
					QStoneTran qStoneTran = QStoneTran.stoneTran;
					JPAQuery query = new JPAQuery(entityManager);
					
					List<StoneTran> stoneTrans = null;
					
					stoneTrans = query.from(qStoneTran)
							.where(qStoneTran.sordDtId.eq(bagMt.getOrderDt().getId()).and(qStoneTran.deactive.eq(false)).and(qStoneTran.stoneType.name.eq(stoneTran.getStoneType().getName()))
									.and(qStoneTran.shape.name.eq(stoneTran.getShape().getName())).and(qStoneTran.quality.name.eq(stoneTran.getQuality().getName()))
									.and(qStoneTran.sieve.eq(stoneTran.getSieve()))
									.and(qStoneTran.deptLocation.id.eq(location.getId())))
							.groupBy(qStoneTran.stoneType,qStoneTran.shape,qStoneTran.quality,qStoneTran.size)
							.list(qStoneTran);
					
					StoneTran stoneTran2 = stoneTrans.get(0);
					
					stoneTran.setSordMtId(bagMt.getOrderDt().getOrderMt().getId());
					stoneTran.setRate(stoneTran2.getAvgRate());
					stoneTran.setAvgRate(stoneTran2.getAvgRate());
					stoneTran.setFactoryRate(stoneTran2.getFactoryRate());
					stoneTran.setTransferRate(stoneTran2.getTransferRate());
					
					tranDtTmp.setRate(stoneTran.getRate());
					tranDtTmp.setFactoryRate(stoneTran.getFactoryRate());
					tranDtTmp.setAvgRate(stoneTran.getAvgRate());
					tranDtTmp.setTransferRate(stoneTran.getTransferRate());
					
					
					tranDtService.save(tranDtTmp);
					
					
				}
				
			
				stoneTranService.save(stoneTran);
				
				
			}
			
			if(tranMt.getRecWt()!=null && tranMt.getRecWt()>0) {
				tranMt.setRecWt(Math.round((tranMt.getRecWt()+(totCarat/5))*1000.0)/1000.0);
				tranMtService.save(tranMt);	
			}
			
			return retVal;
		}
		
		
		
		
		
	}
	

}

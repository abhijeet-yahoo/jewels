package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStoneTranRepository;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneConversionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.jpa.impl.JPAQuery;


@Service
@Repository
@Transactional
public class StoneTranService implements IStoneTranService {

	@Autowired
	private IQualityService  qualityService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
		
	@Autowired
	private IStoneTranRepository stoneTranRepository;

	@Autowired
	private IStoneConversionService stoneConversionService;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	
	
	@Override
	public List<StoneTran> findAll() {
		return stoneTranRepository.findAll();
	}

	@Override
	public void save(StoneTran stoneTran) {
		stoneTranRepository.save(stoneTran);

	}

	@Override
	public void delete(int id) {
		StoneTran stoneTran = stoneTranRepository.findOne(id);
		stoneTran.setDeactive(true);
		stoneTran.setDeactiveDt(new java.util.Date());
		stoneTranRepository.save(stoneTran);
	}

	@Override
	public List<StoneTran> findByRefTranIdAndTranType(Integer refTranId,
			String tranType) {
		return stoneTranRepository.findByRefTranIdAndTranType(refTranId, tranType);
	}

	@Override
	public StoneTran RefTranIdAndTranType(Integer refTranId, String tranType) {
		return stoneTranRepository.RefTranIdAndTranType(refTranId, tranType);
	}


	@Override
	public Double getStockBalance(Integer locationId,Integer stoneTypeId, Integer shapeId,
			Integer qualityId, Integer sizeGroupId,String sieve, String flag) {
		
		StoneType stoneType =stoneTypeService.findOne(stoneTypeId);
		Shape shape =shapeService.findOne(shapeId);
		Quality quality =qualityService.findOne(qualityId);
		SizeGroup sizeGroup =sizeGroupService.findOne(sizeGroupId);
		
		Double tempCaratSum = getStockBalanceStr(locationId, stoneType.getName(), shape.getName(), quality.getName(), sizeGroup.getName(), sieve, flag, null);
		
		/*
		 * QStoneTran qStoneTran = QStoneTran.stoneTran; JPAQuery query = new
		 * JPAQuery(entityManager);
		 */
		

		/*
		 * List<Double> val = null;
		 * 
		 * if(flag.equalsIgnoreCase("size")){ val =
		 * query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.
		 * stoneType.id.eq(stoneTypeId))
		 * .and(qStoneTran.shape.id.eq(shapeId)).and(qStoneTran.quality.id.eq(qualityId)
		 * )
		 * .and(qStoneTran.size.eq(size)).and(qStoneTran.deptLocation.id.eq(locationId))
		 * ) .groupBy(qStoneTran.size).list(qStoneTran.balCarat.sum()); }else{ val =
		 * query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.
		 * stoneType.id.eq(stoneTypeId))
		 * .and(qStoneTran.shape.id.eq(shapeId)).and(qStoneTran.quality.id.eq(qualityId)
		 * )
		 * .and(qStoneTran.sizeGroup.id.eq(sizeGroupId)).and(qStoneTran.deptLocation.id.
		 * eq(locationId)))
		 * .groupBy(qStoneTran.sizeGroup).list(qStoneTran.balCarat.sum()); }
		 * 
		 * 
		 * 
		 * for(Double vCarat:val){ if(vCarat != null){ tempCaratSum = vCarat; } }
		 * 
		 * tempCaratSum=Math.round(tempCaratSum*1000.0)/1000.0;
		 */
		
		return tempCaratSum;
	}
	
	
	@Override
	public Double getStockBalanceStr(Integer locationId,String stoneTypeNm, String shapeNm,
			String qualityNm, String sizeGroupNm, String sieveNm, String flag,Integer orderDtId) {
		
		QStoneTran qStoneTran = QStoneTran.stoneTran;
		//QStoneTran qStoneTran1 = QStoneTran.stoneTran;
		JPAQuery query = new JPAQuery(entityManager);
		JPAQuery query1 = new JPAQuery(entityManager);
		
				
		List<Double> val = null;
		Double tempCaratSum = 0.0;
		Department location=  departmentService.findOne(locationId);
		
	
		if(location.getName().equalsIgnoreCase("Bagging")) {
			
			if(flag.equalsIgnoreCase("orderitem")){
				val = query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(stoneTypeNm))
						.and(qStoneTran.shape.name.eq(shapeNm)).and(qStoneTran.quality.name.eq(qualityNm))
						.and(qStoneTran.sieve.eq(sieveNm)).and(qStoneTran.deptLocation.id.eq(locationId)).and(qStoneTran.sordDtId.eq(orderDtId)))
						.groupBy(qStoneTran.sieve).list(qStoneTran.balCarat.sum());
				
				for(Double vCarat:val){
					tempCaratSum = vCarat;
				}
				
				tempCaratSum=Math.round(tempCaratSum*1000.0)/1000.0;
				
				
				if(tempCaratSum<=0) {
					
					OrderDt orderDt = orderDtService.findOne(orderDtId);
					
					val = query1.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(stoneTypeNm))
							.and(qStoneTran.shape.name.eq(shapeNm)).and(qStoneTran.quality.name.eq(qualityNm))
							.and(qStoneTran.sieve.eq(sieveNm)).and(qStoneTran.deptLocation.id.eq(locationId)).and(qStoneTran.sordMtId.eq(orderDt.getOrderMt().getId())))
							.groupBy(qStoneTran.sieve).list(qStoneTran.balCarat.sum());
					
					for(Double vCarat:val){
						tempCaratSum = vCarat;
					}
					
					tempCaratSum=Math.round(tempCaratSum*1000.0)/1000.0;
					
				}
				
				
			}else if(flag.equalsIgnoreCase("size")) {
				val = query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(stoneTypeNm))
						.and(qStoneTran.shape.name.eq(shapeNm)).and(qStoneTran.quality.name.eq(qualityNm))
						.and(qStoneTran.sieve.eq(sieveNm)).and(qStoneTran.deptLocation.id.eq(locationId)))
						.groupBy(qStoneTran.sieve).list(qStoneTran.balCarat.sum());
				
				for(Double vCarat:val){
					tempCaratSum = vCarat;
				}
				
				tempCaratSum=Math.round(tempCaratSum*1000.0)/1000.0;
			}else{
				val = query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(stoneTypeNm))
						.and(qStoneTran.shape.name.eq(shapeNm)).and(qStoneTran.quality.name.eq(qualityNm))
						.and(qStoneTran.sizeGroup.name.eq(sizeGroupNm)).and(qStoneTran.deptLocation.id.eq(locationId)))
						.groupBy(qStoneTran.sizeGroup).list(qStoneTran.balCarat.sum());
				
				for(Double vCarat:val){
					tempCaratSum = vCarat;
				}
				
				tempCaratSum=Math.round(tempCaratSum*1000.0)/1000.0;
			}
			
		}else {
			if(flag.equalsIgnoreCase("size")){
				val = query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(stoneTypeNm))
						.and(qStoneTran.shape.name.eq(shapeNm)).and(qStoneTran.quality.name.eq(qualityNm))
						.and(qStoneTran.sieve.eq(sieveNm)).and(qStoneTran.deptLocation.id.eq(locationId)))
						.groupBy(qStoneTran.sieve).list(qStoneTran.balCarat.sum());
			}else if(flag.equalsIgnoreCase("orderitem")) {
				
				val = query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(stoneTypeNm))
						.and(qStoneTran.shape.name.eq(shapeNm)).and(qStoneTran.quality.name.eq(qualityNm))
						.and(qStoneTran.sieve.eq(sieveNm)).and(qStoneTran.deptLocation.id.eq(locationId)).and(qStoneTran.sordDtId.eq(orderDtId)))
						.groupBy(qStoneTran.sieve).list(qStoneTran.balCarat.sum());
				
			}else{
				val = query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.name.eq(stoneTypeNm))
						.and(qStoneTran.shape.name.eq(shapeNm)).and(qStoneTran.quality.name.eq(qualityNm))
						.and(qStoneTran.sizeGroup.name.eq(sizeGroupNm)).and(qStoneTran.deptLocation.id.eq(locationId)))
						.groupBy(qStoneTran.sizeGroup).list(qStoneTran.balCarat.sum());
			}
			
			
			for(Double vCarat:val){
				tempCaratSum = vCarat;
			}
			
			tempCaratSum=Math.round(tempCaratSum*1000.0)/1000.0;
			
		}
		
		
		
		
	
		
		
		
		return tempCaratSum;
		
		
	}
	
	
	@Override
	public List<StoneTran> findByBagMtAndBagSrNoAndTranType(BagMt bagMt,
			Integer bagSrNo, String tranType) {
		return stoneTranRepository.findByBagMtAndBagSrNoAndTranType(bagMt, bagSrNo, tranType);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getStoneTranList(Integer stonetypeId,
			Integer shapeId, Integer qualityId, Integer sizeGroupId,
			String size, String flag,String bagNm,Principal principal) {
		
		BagMt bagMt =bagMtService.findByName(bagNm);
		
		TranMt tranMt =tranMtService.findByBagMtCurrStk(bagMt, true);
		
		Department location=  tranMt.getDepartment();
		
		User user =userService.findByName(principal.getName());
		LocationRights locationRights = locationRightsService.findByUserAndDepartmentAndDeactive(user, location, false);
		
		List<Object[]> stoneTranList=null;
		
		 if(location.getStoneStk().equals(true) && locationRights !=null ) {
				TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_changingNewBreakup(?,?,?,?,?,?,?) }");
				query.setParameter(1,stonetypeId);
				query.setParameter(2,shapeId);
				query.setParameter(3,qualityId);
				query.setParameter(4,sizeGroupId);
				query.setParameter(5,size);
				query.setParameter(6,flag);
				query.setParameter(7,location.getId());
				
				
				 stoneTranList = query.getResultList();
				
				return stoneTranList;
		 }else {
			 
			 return stoneTranList;
		 }
		
		
		
	
	}
	
	
	@Override
	public Integer getMaxSrno(Integer bagId) {
		QStoneTran qStoneTran = QStoneTran.stoneTran;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;

		List<Integer> maxSrno = query.from(qStoneTran)
				.where(qStoneTran.deactive.eq(false).and(qStoneTran.bagMt.id.eq(bagId)))
					.list(qStoneTran.bagSrNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}

		return retVal;
	}

	@Override
	public StoneTran findByQualityAndSize(Quality quality, String size) {
		// TODO Auto-generated method stub
		return stoneTranRepository.findByQualityAndSize(quality, size);
	}

	@Override
	public String saveStnTran(String vSieveFrom, Integer vshapeIdFrom,
			Principal principal, Integer vQualityIdFrom,
			Integer vStoneTypeIdFrom, Double vtrfCarat,Integer vtrfStone, StoneTran stoneTran,String diamondStockType,Date tranDate,Integer locationId) {
		
		
		synchronized (this) {
			Department department = departmentService.findOne(locationId);
			Shape shape2 = shapeService.findOne(vshapeIdFrom);
		
			/*
			 * StoneChart stoneChart3 =
			 * stoneChartService.findByShapeAndSizeAndDeactive(shape2, vSizeIdFrom, false);
			 */
			
			StoneChart stoneChart3 =  stoneChartService.findByShapeAndSieveAndDeactive(shape2, vSieveFrom, false);
			
			Double availableStock = getStockBalance(department.getId(),vStoneTypeIdFrom, vshapeIdFrom, 
					vQualityIdFrom, stoneChart3.getSizeGroup().getId(),vSieveFrom, "size");
			

			
			
			
			if(availableStock < vtrfCarat){
				
				return "Error : Stock Not Available";
				
			}else{
				
				Quality quality = qualityService.findOne(vQualityIdFrom);
				Shape shape = shapeService.findOne(vshapeIdFrom);
				StoneType stoneType = stoneTypeService.findOne(vStoneTypeIdFrom);
				
				StoneChart stoneChart =  stoneChartService.findByShapeAndSieveAndDeactive(shape, vSieveFrom, false);
				StoneChart stoneChartTo =stoneChartService.findByShapeAndSieveAndDeactive(stoneTran.getShape(), stoneTran.getSieve(), false);
				
				List<StoneTran>stoneTrans =getDiamondStockRate(department.getId(),stoneType.getName(),
						shape.getName(), quality.getName(), stoneChart.getSize(), stoneChart.getSieve());
				
				if(stoneTrans.size()>0) {
					
					
					StoneConversion stoneConversion =new StoneConversion();
					
					Integer maxSrNo=stoneConversionService.getMaxInvSrno();
					maxSrNo = (maxSrNo == null ? 1 : maxSrNo + 1);
					stoneConversion.setSrNo(maxSrNo);
					
					
						int si = maxSrNo.toString().length();
					
					Calendar date = new GregorianCalendar();
					String vYear = "" + date.get(Calendar.YEAR);
					vYear = vYear.substring(2);
					
					String bagSr = null;
					
					switch (si) {
					case 1:
						bagSr = "000"+maxSrNo;
						break;
						
					case 2:
						bagSr = "00"+maxSrNo;
						break;
						
					case 3:
						bagSr = "0"+maxSrNo;
						break;
						
					default:
						bagSr = maxSrNo.toString();
						break;
					}
					stoneConversion.setInvNo("TRF-" + (bagSr) + "-" + vYear);
					stoneConversion.setCarat(vtrfCarat);
					stoneConversion.setCreatedBy(principal.getName());
					stoneConversion.setCreatedDt(new Date());
					stoneConversion.setInvDate(tranDate);
					stoneConversion.setIssQuality(stoneTran.getQuality());
					stoneConversion.setIssShape(stoneTran.getShape());
					stoneConversion.setIssSize(stoneChartTo.getSize());
					stoneConversion.setQuality(quality);
					stoneConversion.setShape(shape);
					stoneConversion.setSize(stoneChart.getSize());
					stoneConversion.setStone(vtrfStone);
					stoneConversion.setStoneType(stoneType);
					stoneConversion.setStoneRate(stoneTran.getRate());
					stoneConversionService.save(stoneConversion);
					
				
					
					
					/*
					 * List<StoneTran>stoneTrans
					 * =getDiamondStockForAllocation(department.getId(),stoneType.getName(),
					 * shape.getName(), quality.getName(), stoneChart.getSize(),
					 * stoneChart.getSieve());
					 */
					
					
					
					
					
					
					
				
					Double adjCarat = vtrfCarat;
					Integer adjStone= vtrfStone;
					
					for (StoneTran stoneTran2 : stoneTrans) {
					  
						  if(vtrfCarat>0) {
							  if(stoneTran2.getBalCarat()>= vtrfCarat) {
								  StoneTran stoneTran1 = new StoneTran();
									
									stoneTran1.setStoneType(stoneType);
									stoneTran1.setQuality(quality);
									stoneTran1.setSize(stoneChart.getSize());
									stoneTran1.setShape(shape);
									stoneTran1.setSieve(stoneChart.getSieve());
									stoneTran1.setSizeGroup(stoneChart.getSizeGroup());
									stoneTran1.setCreatedBy(principal.getName());
									stoneTran1.setCreatedDt(new java.util.Date());
									stoneTran1.setCarat(vtrfCarat);
									stoneTran1.setInOutFld("D");
									stoneTran1.setBalCarat(-vtrfCarat);
									stoneTran1.setBalStone(-vtrfStone);
									stoneTran1.setTranType("StnConv");
									stoneTran1.setRefTranId(stoneConversion.getId());
									stoneTran1.setTranDate(tranDate);
									stoneTran1.setStone(vtrfStone);
									stoneTran1.setDeptLocation(department);
									stoneTran1.setDepartment(department);
									stoneTran1.setLotNo(stoneTran2.getLotNo());
									stoneTran1.setRate(stoneTran.getRate());
									
									
									save(stoneTran1);
								  
								  break;
							  }else {
								  
								  
								  vtrfCarat =(double) Math.round((vtrfCarat-stoneTran2.getBalCarat())*1000)/1000;
								  vtrfStone = Math.round(vtrfStone-stoneTran2.getBalStone());
								  StoneTran stoneTran1 = new StoneTran();
									
									stoneTran1.setStoneType(stoneType);
									stoneTran1.setQuality(quality);
									stoneTran1.setSize(stoneChart.getSize());
									stoneTran1.setShape(shape);
									stoneTran1.setSieve(stoneChart.getSieve());
									stoneTran1.setSizeGroup(stoneChart.getSizeGroup());
									stoneTran1.setCreatedBy(principal.getName());
									stoneTran1.setCreatedDt(new java.util.Date());
									stoneTran1.setCarat(stoneTran2.getBalCarat());
									stoneTran1.setInOutFld("D");
									stoneTran1.setBalCarat(-stoneTran2.getBalCarat());
									stoneTran1.setBalStone(-stoneTran2.getBalStone());
									stoneTran1.setTranType("StnConv");
									stoneTran1.setRefTranId(stoneConversion.getId());
									stoneTran1.setTranDate(tranDate);
									stoneTran1.setStone(stoneTran2.getBalStone());
									stoneTran1.setDeptLocation(department);
									stoneTran1.setDepartment(department);
									stoneTran1.setLotNo(stoneTran2.getLotNo());
									stoneTran1.setRate(stoneTran.getRate());
									
									save(stoneTran1);
								  
							  }
						  }
						 
					}
					
					
					
					StoneTran stoneTran2 =  new StoneTran();
					
									
					StoneChart stoneChart2 =stoneChartService.findByShapeAndSieveAndDeactive(stoneTran.getShape(), stoneTran.getSieve(), false);
					
					stoneTran2.setStoneType(stoneType);
					stoneTran2.setShape(stoneTran.getShape());
					stoneTran2.setQuality(stoneTran.getQuality());
					stoneTran2.setSize(stoneChart2.getSize());
					stoneTran2.setSieve(stoneChart2.getSieve());
					stoneTran2.setSizeGroup(stoneChart2.getSizeGroup());
					stoneTran2.setCreatedBy(principal.getName());
					stoneTran2.setCreatedDt(new java.util.Date());
					stoneTran2.setCarat(adjCarat);
					stoneTran2.setBalCarat(adjCarat);
					stoneTran2.setTranType("StnConv");
					stoneTran2.setRefTranId(stoneConversion.getId());
					stoneTran2.setInOutFld("C");
					stoneTran2.setTranDate(new java.util.Date());
					stoneTran2.setStone(adjStone);
					stoneTran2.setBalStone(adjStone);
					stoneTran2.setDeptLocation(department);
					stoneTran2.setDepartment(department);
					stoneTran2.setLotNo(stoneConversion.getInvNo());
					stoneTran2.setRate(stoneTran.getRate());
					
					save(stoneTran2);
					
				}
				
				
				
				
				
			}
			
			
			return "1";
			
		}
				
		
	}

	
	@Override
	public Double getDiamondCaratStock(Integer locationId, Integer stoneTypeId, Integer shapeId, Integer qualityId, StoneChart stoneChart,String flag) {
		
		QStoneTran qStoneTran = QStoneTran.stoneTran;
		JPAQuery query = new JPAQuery(entityManager);
		Double retVal = 0.0;
		
		
		List<Double> balance=null;
		
		if(flag.equalsIgnoreCase("size")){
			balance = query
					.from(qStoneTran)
					.where(qStoneTran.deptLocation.id.eq(locationId).and(
							qStoneTran.stoneType.id.eq(stoneTypeId)
							.and(qStoneTran.shape.id.eq(shapeId)).and(qStoneTran.quality.id.eq(qualityId)).and(qStoneTran.size.eq(stoneChart.getSize()))
							.and(qStoneTran.deactive.eq(false)))).groupBy(qStoneTran.size).list(qStoneTran.balCarat.sum());
			
		}else{
					
			
			balance = query
					.from(qStoneTran)
					.where(qStoneTran.deptLocation.id.eq(locationId).and(
							qStoneTran.stoneType.id.eq(stoneTypeId)
							.and(qStoneTran.shape.id.eq(shapeId)).and(qStoneTran.quality.id.eq(qualityId)).and(qStoneTran.sizeGroup.eq(stoneChart.getSizeGroup()))
							.and(qStoneTran.deactive.eq(false)))).groupBy(qStoneTran.sizeGroup).list(qStoneTran.balCarat.sum());
			
		}
		
		
		
		
		for(Double bal:balance){
			retVal = bal;
		}
		
		retVal=Math.round(retVal*1000.0)/1000.0;
		return retVal;
		
		
		
	}

	@Override
	public List<StoneTran> findByTranMtIdAndDeactive(Integer tranMtId,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneTranRepository.findByTranMtIdAndDeactive(tranMtId, deactive);
	}

	@Override
	public List<StoneTran> findBySordDtIdAndDeactive(Integer sordDtId, Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneTranRepository.findBySordDtIdAndDeactive(sordDtId, deactive);
	}

	@Override
	public List<StoneTran> findByStnRecDtIdAndDeactive(Integer stnRecDtId, Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneTranRepository.findByStnRecDtIdAndDeactive(stnRecDtId, deactive);
	}

	@Override
	public List<StoneTran> findByTranTypeAndDeactiveAndSordMtIdAndShapeAndQualityAndSizeAndInOutFld(String tranType,
			Boolean deactive, Integer sordMtId, Shape shape, Quality quality, String size,String inOutFld) {
		// TODO Auto-generated method stub
		return stoneTranRepository.findByTranTypeAndDeactiveAndSordMtIdAndShapeAndQualityAndSizeAndInOutFld(tranType, deactive, sordMtId, shape, quality, size,inOutFld);
	}

	@Override
	public List<StoneTran> getDiamondStockForAllocation(Integer locationId, String stoneTypeNm, String shapeNm,
			String qualityNm, String sizeNm, String sieve) {
		
		StoneType stoneType =stoneTypeService.findByName(stoneTypeNm);
		Shape shape =shapeService.findByName(shapeNm);
		Quality quality =qualityService.findByShapeAndName(shape, qualityNm);
		
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<StoneTran> query =  (TypedQuery<StoneTran>)entityManager.createNativeQuery("{ call jsp_diamondStockwithsorddtidnull(?,?,?,?,?,?) }",StoneTran.class);
		query.setParameter(1,locationId);
		query.setParameter(2,stoneType.getId());
		query.setParameter(3,shape.getId());
		query.setParameter(4,quality.getId());
		query.setParameter(5,sizeNm);
		query.setParameter(6,sieve);
		
		
		
		List<StoneTran> stoneTrans = query.getResultList();
		return stoneTrans;
	}

	@Override
	public List<StoneTran> getDiamondStockRate(Integer locationId, String stoneTypeNm, String shapeNm, String qualityNm,
			String sizeNm, String sieve) {
		StoneType stoneType =stoneTypeService.findByName(stoneTypeNm);
		Shape shape =shapeService.findByName(shapeNm);
		Quality quality =qualityService.findByShapeAndName(shape, qualityNm);
		
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<StoneTran> query =  (TypedQuery<StoneTran>)entityManager.createNativeQuery("{ call jsp_diamondStockrate(?,?,?,?,?,?) }",StoneTran.class);
		query.setParameter(1,locationId);
		query.setParameter(2,stoneType.getId());
		query.setParameter(3,shape.getId());
		query.setParameter(4,quality.getId());
		query.setParameter(5,sizeNm);
		query.setParameter(6,sieve);
		
		
		
		List<StoneTran> stoneTrans = query.getResultList();
		return stoneTrans;
	}

	@Override
	public String getAvailableStock(Integer locationId, Integer stoneTypeId, Integer shapeId, Integer qualityId,
			String sieve) {
		// TODO Auto-generated method stub
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_diamondStockbalancewithvalue(?,?,?,?,?) }");
		query.setParameter(1, locationId);
		query.setParameter(2, stoneTypeId);
		query.setParameter(3, shapeId);
		query.setParameter(4, qualityId);
		query.setParameter(5, sieve);
		objects = query.getResultList();
		
		
		Double balcarat = 0.0;
		Double stnVal = 0.0;
		Double avgRate = 0.0;
		
		for(Object[] list:objects){
			
			
			
			balcarat += list[0]==null?0.0: Double.parseDouble(list[0].toString());
			stnVal += list[1]==null?0.0:  Double.parseDouble(list[1].toString());
			avgRate += list[2]==null?0.0: Double.parseDouble(list[2].toString());
		}

		
		JSONObject jsonObject =  new JSONObject();
		
		jsonObject.put("availableStkId", balcarat);
		jsonObject.put("stnVal", stnVal);
		jsonObject.put("avgRate", avgRate);

		return jsonObject.toString();

	}

	
}

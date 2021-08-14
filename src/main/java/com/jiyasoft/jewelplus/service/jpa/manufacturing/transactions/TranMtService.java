package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpStoneProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LossBook;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QTranMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICompTranRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IEmpStoneProductionRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ILossBookRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMetalTranRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStoneTranRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ITranMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpStoneProductionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILossBookService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class TranMtService implements ITranMtService {
	
	@Autowired
	private IMetalTranRepository metalTranRepository;
	
	@Autowired
	private IEmpStoneProductionService empStoneProductionService;
	
	@Autowired
	private IEmpStoneProductionRepository empStoneProductionRepository;

	@Autowired
	private ILossBookService lossBookService;
	
	@Autowired
	private ILossBookRepository lossBookRepository;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private ICompTranRepository compTranRepository;
	
	@Autowired
	private ITranMtRepository tranMtRepository;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private ICastingDtService castingDtService;
	
	@Autowired
	private IOrderMetalService orderMetalService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private IReadyBagService readyBagService;
	
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IStoneTranRepository stoneTranRepository;
	
	@Autowired
	private IFgRetDtService fgRetDtService;

	/*@Override
	public List<TranMt> findAll() {
		return tranMtRepository.findAll();
	}
*/
	// ------- two method for readyBag------>>>>>>>>>

	@Override
	public Page<TranMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		QTranMt qTranMt = QTranMt.tranMt;
		BooleanExpression expression = qTranMt.currStk.eq(true).and(
						qTranMt.department.name.toLowerCase().eq("diamond"));

		int page = ((offset == null || offset == 0) ? 0 : (offset / limit));

		if (limit == null) {
			limit = 1000;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return tranMtRepository.findAll(expression, new PageRequest(page,
				limit, Direction.DESC, sort));

	}

	

	@Override
	public void save(TranMt tranMt) {

		tranMtRepository.save(tranMt);

	}

	@Override
	public void delete(int id) {
		tranMtRepository.delete(id);
	}



	@Override
	public TranMt findOne(int id) {
		return tranMtRepository.findOne(id);
	}



	@Override
	public List<TranMt> findByBagMt(BagMt bagMt) {

		return tranMtRepository.findByBagMt(bagMt);
	}

	

	@Override
	public TranMt findByBagMtAndDepartmentAndCurrStk(BagMt bagMt,
			Department department, Boolean currStk) {

		return tranMtRepository.findByBagMtAndDepartmentAndCurrStk(bagMt,
				department, currStk);
	}

	@Override
	public TranMt findByBagMtCurrStk(BagMt bagMt, Boolean currStk) {
		
		QTranMt qTranMt = QTranMt.tranMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<TranMt> tranMtList = query.from(qTranMt)
				.where(qTranMt.bagMt.id.eq(bagMt.getId()).and(qTranMt.currStk.eq(true))
						.and(qTranMt.department.name.notEqualsIgnoreCase("Casting")).and(qTranMt.department.name.notEqualsIgnoreCase("READY FOR CASTING")))
				.list(qTranMt);
		
		if(tranMtList.size()>0){
			return tranMtList.get(0);		
		}else{
			
			return null;
		}
		
	
	}

	@Override
	public List<TranMt> findByDepartmentAndCurrStk(Department department,
			Boolean currStk) {
		return tranMtRepository.findByDepartmentAndCurrStk(department, currStk);
	}

	@Override
	public List<TranMt> findByDeptIdAndCostDt(Integer deptId) {
		
		String cond = "  DeptId in (" + deptId + ")  ";
		
		List<TranMt> tranMtList = null;

		@SuppressWarnings("unchecked")
		TypedQuery<TranMt> query =  (TypedQuery<TranMt>)entityManager.createNativeQuery("{ CALL jsp_addBagCosting(?) }",TranMt.class);
		query.setParameter(1, cond);

		tranMtList = query.getResultList();
		

		return tranMtList;

	
}



	
	@Override
	public List<Object[]> getBagsForCasting(Department department, Purity purity,Color color) {
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_castingBagDetail(?,?,?) }");
		query.setParameter(1,department.getId());
		query.setParameter(2,purity.getId());
		query.setParameter(3,color.getId());
		
			
		List<Object[]> dtList = query.getResultList();
		return dtList;

	}

	@Override
	public TranMt findByDepartmentAndDeptFromAndBagMtAndCurrStk(
			Department department, Department deptFrom, BagMt bagMt,
			Boolean currStk) {
		return tranMtRepository.findByDepartmentAndDeptFromAndBagMtAndCurrStk(department, deptFrom, bagMt, currStk);
	}
	
	@Override
	public TranMt findByIdAndCurrStk(Integer id, Boolean currStk) {
		return tranMtRepository.findByIdAndCurrStk(id, currStk);
	}
	
	@Override
	public Double getTotalTranQtys(Integer orderMtId) {
		QTranMt qTranMt = QTranMt.tranMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		Double retVal = 0.0;
		
		List<Double> tranMtList = query.from(qTranMt).
				where(qTranMt.currStk.eq(true).and(qTranMt.orderMt.id.eq(orderMtId)).and(qTranMt.department.name.equalsIgnoreCase("Cancel")
						.or(qTranMt.department.name.equalsIgnoreCase("Export")))).list(qTranMt.pcs.sum());
		
		
		for (Double qty : tranMtList) {
			if(qty != null){
				retVal = qty;
			}
		}
		
		return retVal;
	}

	@Override
	public TranMt findByBagMtIdAndCurrStk(Integer bagId, Boolean currStk) {
		// TODO Auto-generated method stub
		return tranMtRepository.findByBagMtIdAndCurrStk(bagId, currStk);
	}

	@Override
	public String setOpeningTransfer(String bagIds,Principal principal,TranMt tranMt) {
		
		String retVal="";

		String[] data1 = bagIds.split(",");
		String vBagNm="";
		for (int i = 0; i < data1.length; i++) {
			BagMt bagMt = bagMtService.findOne(Integer.parseInt(data1[i]));
			List<TranMt> tranMts = findByBagMt(bagMt);
			if (tranMts.size()>0) {
				if(vBagNm ==""){
					vBagNm =bagMt.getName();
				}else{
					vBagNm +=","+bagMt.getName();	
				}
				
			}
		}
		
		if(vBagNm !=""){
			return "error : Can Not Transfer Bag  , Bags In Transation ( "+vBagNm+")";
		}
		

		 

		Department department = departmentService.findByName("Opening");
		if(department==null){
			return "error : Opening Department Not Available , Please Create Department Opening";
					
		}

		for (int i = 0; i < data1.length; i++) {

			BagMt bagMt = bagMtService.findOne(Integer.parseInt(data1[i]));
			TranMt tranMttemp = new TranMt();
				tranMttemp.setBagMt(bagMt);
				tranMttemp.setPcs(bagMt.getQty());
				tranMttemp.setCurrStk(true);
				tranMttemp.setTrandate(new java.util.Date());
				tranMttemp.setCreatedBy(principal.getName());
				tranMttemp.setCreatedDate(new java.util.Date());
				tranMttemp.setDepartment(tranMt.getDepartment());
				tranMttemp.setDeptFrom(department);
				tranMttemp.setOrderMt(bagMt.getOrderMt());
				tranMttemp.setOrderDt(bagMt.getOrderDt());
				/*tranMttemp.setPurityConv(bagMt.getOrderDt().getPurity().getPurityConv());*/
				tranMttemp.setTime(new java.sql.Time(new java.util.Date().getTime()));
				save(tranMttemp);
				
				//Order Metal Transfer
				
				
				List<OrderMetal>orderMetals =orderMetalService.findByOrderDtAndDeactive(bagMt.getOrderDt(), false);
				
				
				for(OrderMetal orderMetal :orderMetals){
					
					TranMetal tranMetal = new TranMetal();
					tranMetal.setBagMt(bagMt);
					tranMetal.setColor(orderMetal.getColor());
					tranMetal.setMainMetal(orderMetal.getMainMetal());
					tranMetal.setCreatedBy(principal.getName());
					tranMetal.setCreatedDate(new Date());
					tranMetal.setCurrStk(true);
					tranMetal.setDepartment(tranMt.getDepartment());
					tranMetal.setDeptFrom(department);
					tranMetal.setMetalPcs((int)(orderMetal.getMetalPcs()*bagMt.getQty()));
					tranMetal.setPartNm(orderMetal.getPartNm());
					tranMetal.setPurity(orderMetal.getPurity());
					tranMetal.setPurityConv(orderMetal.getPurity().getPurityConv());
					tranMetal.setTime(new java.sql.Time(new java.util.Date().getTime()));
					tranMetal.setTranDate(new Date());
					tranMetal.setTranMt(tranMttemp);
					tranMetalService.save(tranMetal);
					
					
					
				}

			
		

		} // for loop
		retVal="1";

		return retVal;
	}

	@Override
	public List<TranMt> findByBagMtAndCurrStk(BagMt bagMt,
			Boolean currStk) {
		return tranMtRepository.findByBagMtAndCurrStk(bagMt, currStk);
	}

	@Override
	public String transfer(String vBagNo,Integer vDepartmentId,Integer vDepartmentToId,Principal principal,String remark,Date tranDate,Boolean trfApproval) {
		
		synchronized (this) {
			String retVal="-1";	
			
			try {
				
				
				BagMt bagMt = bagMtService.findByName(vBagNo);
				Department department = departmentService.findOne(vDepartmentId);
				Department departmentTo = departmentService.findOne(vDepartmentToId);
				
						
				TranMt tranMtt = findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
				List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMtt, true);
			
				if(tranMtt !=null){
					
					if(tranMtt.getHoldFlg().equals(true)) {
						return  "Error: Can not Transfer Bag Not received against "+ department.getName()+" issue voucher";
					}
					
					
					if(tranDate == null){
						tranDate =  new Date();
					}
					
					
					
					String empPcsStatus = "";
					String empStoneStatus = "";
					
						if(department.getPcsProd().equals(true)){
						
						List<LossBook>empPcsProductions =lossBookService.findByDepartmentAndBagMtAndDeactive(department, bagMt, false);
											
						if(empPcsProductions.size() > 0){
						
						}else{
							empPcsStatus = "-4";
						}
						
					}else if(department.getStoneProd().equals(true)){
						
						
						if(tranDts.size()>0) {
							
							
							List<EmpStoneProduction> empStoneProductions = empStoneProductionService.findByDepartmentAndBagMtAndDeactive(department, bagMt, false);
							
							if(empStoneProductions.size() > 0){
								/*empStoneStatus = "-2";
								for(EmpStoneProduction empStoneProduction:empStoneProductions){
									if(empStoneProduction.getTranMt().getId().equals(tranMtt.getId())){
										empStoneStatus = "";
									}
								}*/
							}else{
								empStoneStatus = "-4";
							}
							
						}
						
					
				
					}
					
						
						if(empPcsStatus !="" ) {
							return empPcsStatus;
						}else if(empStoneStatus !="") {
							return empStoneStatus;
						}else {
							
							

							
							if(department.getName().equalsIgnoreCase("Casting")){
								
								CastingDt castingDt = castingDtService.findByRefMtIdAndDeactive(tranMtt.getId(), false);
								if(castingDt != null){
									if(castingDt.getTransfer().equals(false)){
										return  "Error: Could not transfer as bag present in casting tree !";
									}
								}
								
								
						
							}
							

						if(department.getAllowZeroWt() == false){
									
									if(tranMtt.getRecWt()<=0){
										
										return "Error : Could not transfer Weight <=0";
										
									}
									
									
								}
						//trfApprovalFlg True For Nuance
						
						if(trfApproval.equals(true)) {
							
							tranMtt.setPendApprovalFlg(true);
							tranMtt.setDeptTo(departmentTo);
							tranMtt.setIssueDate(tranDate);
							tranMtt.setModiBy(principal.getName());
							tranMtt.setModiDate(new java.util.Date());
							tranMtt.setIssWt(tranMtt.getRecWt());
						
							save(tranMtt);
							
							List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMtt, true);
				
								
								for(TranMetal tranMetal :tranMetals){
						
							
							// tranmetal False record
							tranMetal.setDeptTo(departmentTo);
							
							tranMetal.setIssDate(tranDate);
							tranMetal.setModiBy(principal.getName());
							tranMetal.setModiDate(new java.util.Date());
							tranMetalService.save(tranMetal);
						
								}
							
							
							
							//---------TranDt------//----------//
							
									for(TranDt tranDt:tranDts){
										tranDt.setDeptTo(departmentTo);
										tranDt.setIssDate(tranDate);
										tranDt.setModiBy(principal.getName());
										tranDt.setModiDate(new java.util.Date());
										
										tranDtService.save(tranDt);
								
									
							}
							
							
						}else {		
									
											
									//adding the new record
									TranMt tranMtNew = new TranMt();
									tranMtNew.setBagMt(tranMtt.getBagMt());
									tranMtNew.setPcs(tranMtt.getPcs());
									tranMtNew.setCurrStk(true);
									tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
									tranMtNew.setTrandate(tranDate);
									tranMtNew.setCreatedBy(principal.getName());
									tranMtNew.setCreatedDate(new java.util.Date());
									tranMtNew.setDepartment(departmentTo);
									tranMtNew.setDeptFrom(tranMtt.getDepartment());
									tranMtNew.setOrderMt(tranMtt.getOrderMt());
									tranMtNew.setOrderDt(tranMtt.getOrderDt());
									tranMtNew.setRefMtId(tranMtt.getId());
									tranMtNew.setRecWt(tranMtt.getRecWt());
									tranMtNew.setRemark(remark);
								/*	tranMtNew.setPurityConv((tranMtt.getOrderDt().getPurity().getPurityConv() == null ? 0.0 : tranMtt.getOrderDt().getPurity().getPurityConv()));*/
									save(tranMtNew);
								
									//editing the existing record
									tranMtt.setCurrStk(false);
									tranMtt.setPendApprovalFlg(false);
									tranMtt.setDeptTo(departmentTo);
									tranMtt.setIssueDate(tranDate);
									tranMtt.setModiBy(principal.getName());
									tranMtt.setModiDate(new java.util.Date());
									tranMtt.setIssWt(tranMtt.getRecWt());
									//tranMtt.setLoss(Double.parseDouble(df.format(vRecWt-vIssWt)));

									save(tranMtt);

									
									//---------TranMetal ------//----------//
									List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMtt, true);
									if(tranMetals.size()>0){
										
										for(TranMetal tranMetal :tranMetals){
											TranMetal tranMetal2 = new TranMetal();
											tranMetal2.setBagMt(tranMetal.getBagMt());
											tranMetal2.setColor(tranMetal.getColor());
											tranMetal2.setMainMetal(tranMetal.getMainMetal());
											tranMetal2.setCreatedBy(principal.getName());
											tranMetal2.setCreatedDate(new Date());
											tranMetal2.setCurrStk(true);
											tranMetal2.setDepartment(departmentTo);
											tranMetal2.setDeptFrom(tranMetal.getDepartment());
											tranMetal2.setMetalPcs(tranMetal.getMetalPcs());
											tranMetal2.setRefTranMetalId(tranMetal.getId());
											tranMetal2.setMetalWeight(tranMetal.getMetalWeight());
											tranMetal2.setPartNm(tranMetal.getPartNm());
											tranMetal2.setPurity(tranMetal.getPurity());
											tranMetal2.setPurityConv(tranMetal.getPurity().getPurityConv());
											tranMetal2.setTime(new java.sql.Time(new java.util.Date().getTime()));
											tranMetal2.setTranDate(tranDate);
											tranMetal2.setTranMt(tranMtNew);
											tranMetal2.setMetalRate(tranMetal.getMetalRate());
											tranMetalService.save(tranMetal2);
											
											
											// tranmetal False record
											tranMetal.setCurrStk(false);
											tranMetal.setDeptTo(departmentTo);
											tranMetal.setIssDate(tranDate);
											tranMetal.setModiBy(principal.getName());
											tranMetal.setModiDate(new java.util.Date());
											tranMetalService.save(tranMetal);
										}
										
									}
									
									
									
									
									/*---------------------------------------------------------*/
									
									
						
									
								//---------TranDt------//----------//
								
								List<TranDt> tranDts1 = tranDtService.findByTranMtAndCurrStk(tranMtt, true);
								if(tranDts.size()>0 ){
										for(TranDt tranDt:tranDts1){
																								
											//adding the new record
											
											 TranDt tranDtNew = new TranDt();
											tranDtNew.setBagMt(tranDt.getBagMt());
											tranDtNew.setPcs(tranDt.getPcs());
											tranDtNew.setCurrStk(true);
											tranDtNew.setCreatedBy(principal.getName());
											tranDtNew.setCreatedDate(new java.util.Date());
											tranDtNew.setDepartment(departmentTo);
											tranDtNew.setDeptFrom(tranDt.getDepartment());
											tranDtNew.setRefDtId(tranDt.getId());
											tranDtNew.setBagSrNo(tranDt.getBagSrNo());
											tranDtNew.setCarat(tranDt.getCarat());
											tranDtNew.setSieve(tranDt.getSieve());
											tranDtNew.setSize(tranDt.getSize());
											tranDtNew.setStone(tranDt.getStone());
											tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
											tranDtNew.setTranDate(tranDate);
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
											tranDt.setDeptTo(departmentTo);
											tranDt.setIssDate(tranDate);
											tranDt.setModiBy(principal.getName());
											tranDt.setModiDate(new java.util.Date());
											
											tranDtService.save(tranDt);
											
										}
									
								}
								
						}
							
						}
						
					}
				
				
				retVal="1";
			
			} catch (Exception e) {
				// TODO: handle exception
				
				
							
				
			}
			
			return retVal;
		}
		
    
    	
	}

	@Override
	public TranMt findByRefMtIdAndCurrStk(Integer refMtId, Boolean currStk) {
		// TODO Auto-generated method stub
		return tranMtRepository.findByRefMtIdAndCurrStk(refMtId, currStk);
	}

	

	@Override
	public Page<TranMt> getBagsForCosting(Integer limit, Integer offset,
			String sort, String order, String name, Integer deptId,Boolean partyFlg,Integer partyId) {
		
		QTranMt qTranMt = QTranMt.tranMt;
		BooleanExpression expression = null;
		
		if(partyFlg.equals(true)) {
			
			if( name != null && !name.isEmpty()){
				
				
				expression = qTranMt.currStk.eq(true).and(qTranMt.department.id.eq(deptId)).and(qTranMt.bagMt.costingFlg.eq(false)).and(qTranMt.bagMt.name.like(name+"%")
								.or(qTranMt.orderMt.invNo.like(name+"%")).or(qTranMt.orderMt.party.name.equalsIgnoreCase(name))
								.or(qTranMt.orderMt.party.partyCode.equalsIgnoreCase(name)));
				
			}else{
			
				
				expression = qTranMt.currStk.eq(true).and(qTranMt.bagMt.costingFlg.eq(false)).and(qTranMt.department.id.eq(deptId));
			}
			
		}else {
			
			if( name != null && !name.isEmpty()){
				
			expression = qTranMt.currStk.eq(true).and(qTranMt.department.id.eq(deptId)).and(qTranMt.bagMt.costingFlg.eq(false))
					.and(qTranMt.orderMt.party.id.eq(partyId))
					.and(qTranMt.bagMt.name.like(name+"%")
								.or(qTranMt.orderMt.invNo.like(name+"%")).or(qTranMt.orderMt.party.name.equalsIgnoreCase(name))
								.or(qTranMt.orderMt.party.partyCode.equalsIgnoreCase(name)));
				
			}else{
			
				
				expression = qTranMt.currStk.eq(true).and(qTranMt.bagMt.costingFlg.eq(false)).and(qTranMt.orderMt.party.id.eq(partyId)).and(qTranMt.department.id.eq(deptId));
			}
			
		}
				
	

		int page = ((offset == null || offset == 0) ? 0 : (offset / limit));

		if (limit == null) {
			limit = 1000;
		}

		if (order == null) {
			order = "ASC";
		}
		if (sort == null) {
			sort = "id";
		}

		return tranMtRepository.findAll(expression, new PageRequest(page,
				limit, Direction.DESC, sort));
	}

	@Override
	public List<TranMt> getBagsForMultiComponentAddtion(Integer deptId) {
		
		QTranMt qTranMt = QTranMt.tranMt;
		BooleanExpression expression = qTranMt.currStk.eq(true).and(qTranMt.bagMt.costingFlg.eq(false)).and(qTranMt.department.id.eq(deptId));
		List<TranMt> tranmtList = (List<TranMt>) tranMtRepository.findAll(expression);
		return tranmtList;
	}

	@Override
	public Page<TranMt> rejectionRecastList(Integer limit, Integer offset, String sort, String order, String name,
			Integer deptId) {
		QTranMt qTranMt = QTranMt.tranMt;
		BooleanExpression expression = qTranMt.currStk.eq(true).and(qTranMt.department.id.eq(deptId)).and(qTranMt.bagMt.recast.eq(false));
		
		if(name != null){
			expression = qTranMt.currStk.eq(true).and(qTranMt.department.id.eq(deptId)).and(qTranMt.bagMt.recast.eq(false))
					.and(qTranMt.orderMt.invNo.like(name+"%").or(qTranMt.bagMt.name.like(name+"%")));
			
		}

		int page = ((offset == null || offset == 0) ? 0 : (offset / limit));

		if (limit == null) {
			limit = 1000;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		
		Page<TranMt> tranMtList =(Page<TranMt>) tranMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		
		return tranMtList;
	}

	@Override
	public String bagRollBack(Integer mtId) {
		
		TranMt tranMt =findOne(mtId);
		
		if(tranMt.getHoldFlg().equals(true)) {
			return  "Error: Can not Transfer Bag Not received against "+ tranMt.getDepartment().getName()+" issue voucher";
		}
		
		
		List<TranMt>tranMtRefList = findByRefMtId(tranMt.getRefMtId());
		
		if(tranMtRefList.size()>1) {
			return "Can Not Rollback Bag refrence Is Greater Than 1, Contact Admin";
			
		}
		
		TranMt tranMtRef = findOne(tranMt.getRefMtId());
		if(tranMtRef !=null) {
			
			if(tranMt.getDeptFrom().getName().equalsIgnoreCase("Casting")) {
				
				List<CastingDt>castingDts = castingDtService.findByTransferTranMtIdAndDeactive(tranMt.getId(), false);
				for(CastingDt castingDt :castingDts) {
					
					castingDt.setTransfer(false);
					castingDt.setMetalWt(0.00);
					castingDt.setTransferTranMtId(null);
					castingDtService.save(castingDt);
				}
				
				
			}
			
			
			CastingDt castingDt =castingDtService.findByRefMtIdAndDeactive(tranMt.getId(), false);
			
			if (castingDt != null) {
				
				castingDt.setDeactive(true);
				castingDt.setDeactiveDt(new java.util.Date());
				castingDtService.save(castingDt);
				
			}
			
			
			
			
			
			
			List<MetalTran>metalTrans = metalTranService.findByTranMtIdAndDeactive(mtId, false);
			for(MetalTran metalTran :metalTrans) {
				
				/*
				 * metalTran.setDeactive(true); metalTran.setDeactiveDt(new java.util.Date());
				 * metalTranService.save(metalTran);
				 */
				
				metalTranRepository.delete(metalTran);
			}
			
			
			List<CompTran>compTrans = compTranService.findByTranMtIdAndDeactive(mtId,false);
			for(CompTran compTran :compTrans) {
				/*
				 * compTran.setDeactive(true); compTran.setDeactiveDt(new java.util.Date());
				 * compTranService.save(compTran);
				 */
				compTranRepository.delete(compTran);
				
			}
			
			
			
			List<ReadyBag>readyBags = readyBagService.findByTranMtAndCurrentStockAndDeactive(tranMt, false, false);
			for(ReadyBag readyBag :readyBags) {
				readyBag.setTranMt(null);
				readyBag.setCurrentStock(true);
				readyBag.setIssDt(null);
				readyBagService.save(readyBag);
				
			}
			
			List<StoneTran>stoneTrans =stoneTranService.findByTranMtIdAndDeactive(mtId, false);
			for(StoneTran stoneTran :stoneTrans) {
				/*
				 * stoneTran.setDeactive(true); stoneTran.setDeactiveDt(new java.util.Date());
				 * stoneTranService.save(stoneTran);
				 */
				stoneTranRepository.delete(stoneTran);
			}
			
			
			List<LossBook>lossBooks = lossBookService.findByTranMtAndDeactive(tranMt, false);
			for(LossBook lossBook :lossBooks) {
				/*
				 * lossBook.setDeactive(true); lossBook.setDeactiveDt(new Date());
				 * lossBookService.save(lossBook);
				 */
				lossBookRepository.delete(lossBook);
			}
			
			List<EmpStoneProduction>empStoneProductions = empStoneProductionService.findByTranMtAndDeactive(tranMt, false);
			for(EmpStoneProduction empStoneProduction :empStoneProductions) {
				/*
				 * empStoneProduction.setDeactive(true); empStoneProduction.setDeactiveDt(new
				 * Date()); empStoneProductionService.save(empStoneProduction);
				 */
				
				empStoneProductionRepository.delete(empStoneProduction);
				
			}
			
			
			
			List<TranMetal>tranMetals = tranMetalService.findByTranMtAndCurrStk(tranMt, true);
			for(TranMetal tranMetal :tranMetals) {
				
				tranMetalService.delete(tranMetal.getId());
			}
			
			
			List<TranDt>tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
			for(TranDt tranDt : tranDts) {
				
				tranDtService.delete(tranDt.getId());
				
			}
			
			
				tranMtRef.setCurrStk(true);
				tranMtRef.setIssueDate(null);
				save(tranMtRef);
				
				List<TranMetal>tranMetalsRef = tranMetalService.findByTranMtAndCurrStk(tranMtRef, false);
				for(TranMetal tranMetal :tranMetalsRef) {
					tranMetal.setCurrStk(true);
					tranMetal.setIssDate(null);
					tranMetalService.save(tranMetal);
				}	
				
				
				
				List<TranDt>tranDtsRef = tranDtService.findByTranMtAndCurrStk(tranMtRef, false);
				for(TranDt tranDt : tranDtsRef) {
					tranDt.setCurrStk(true);
					tranDt.setIssDate(null);
					tranDtService.save(tranDt);
					
				}
				
				
			
				delete(tranMt.getId());
			
			
			
			
			
		}
		
		
		
		
		
		return "1";
	}

	@Override
	public List<TranMt> findByRefMtId(Integer refMtid) {
		// TODO Auto-generated method stub
		return tranMtRepository.findByRefMtId(refMtid);
	}



	@Override
	public TranMt getBagForExportClose(Integer bagId) {
		
		QTranMt qTranMt = QTranMt.tranMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		TranMt tranMt =query.from(qTranMt)
				.where(qTranMt.bagMt.id.eq(bagId).and(qTranMt.currStk.eq(true))
						.and(qTranMt.department.process.equalsIgnoreCase("FG")))
				.uniqueResult(qTranMt);
		
		
		

		return tranMt;
	}



	@Override
	public List<TranMt> getTranMtListForExportClose(List<Integer> bagList) {
		// TODO Auto-generated method stub
		
		QTranMt qTranMt = QTranMt.tranMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		
		List<TranMt>tranMtList =query.from(qTranMt)
				.where(qTranMt.bagMt.id.in(bagList).and(qTranMt.currStk.eq(true))
						.and(qTranMt.department.process.equalsIgnoreCase("FG"))).list(qTranMt);
		
		return tranMtList;
	}



	@Override
	public Page<TranMt> getBagsForJobIss(Integer limit, Integer offset, String sort, String order, String name,
			Integer deptId,Date date2) {
	
		QTranMt qTranMt = QTranMt.tranMt;
		BooleanExpression expression = null;
				
		if( name != null && !name.isEmpty()){
		
			
			expression = qTranMt.currStk.eq(true).and(qTranMt.department.id.eq(deptId)).and(qTranMt.bagMt.jobWorkFlg.eq(false)).and(qTranMt.bagMt.name.like(name+"%")
							.or(qTranMt.orderMt.invNo.like(name+"%")).or(qTranMt.orderMt.party.name.equalsIgnoreCase(name)));
			
		}else{
		
			
			expression = qTranMt.currStk.eq(true).and(qTranMt.bagMt.jobWorkFlg.eq(false)).and(qTranMt.department.id.eq(deptId));
		}

		int page = ((offset == null || offset == 0) ? 0 : (offset / limit));

		if (limit == null) {
			limit = 1000;
		}

		if (order == null) {
			order = "ASC";
		}
		if (sort == null) {
			sort = "id";
		}

		return tranMtRepository.findAll(expression, new PageRequest(page,
				limit, Direction.DESC, sort));
	}



	@Override
	public TranMt findByBagMtAndDeptToAndCurrStkAndPendApprovalFlg(BagMt bagMt, Department departmentTo,Boolean currStk, Boolean pendApprovalFlg) {
		// TODO Auto-generated method stub
		return tranMtRepository.findByBagMtAndDeptToAndCurrStkAndPendApprovalFlg(bagMt, departmentTo, currStk, pendApprovalFlg);
	}



	@Override
	public String pendingApprovaltransfer(String vBagNo,Integer vDepartmentToId,
			Principal principal, String remark, Date tranDate) {
		// TODO Auto-generated method stub
			
			
			synchronized (this) {
				String retVal="-1";	
				
				try {
					
					
					BagMt bagMt = bagMtService.findByName(vBagNo);

					Department departmentTo = departmentService.findOne(vDepartmentToId);
					
							
					TranMt tranMtt = findByBagMtAndDeptToAndCurrStkAndPendApprovalFlg(bagMt, departmentTo, true, true);
				
					if(tranMtt !=null){
						
						
						if(tranMtt.getRepFlg()){
							FgRetDt fgRetDt = fgRetDtService.findByTranMtId(tranMtt.getId());
							fgRetDt.setPendApprovalFlg(false);
							fgRetDt.setModiBy(principal.getName());
							fgRetDt.setModiDt(new Date());
							fgRetDtService.save(fgRetDt);
							
						}
						
						
						if(tranDate == null){
							tranDate =  new Date();
						}
										//adding the new record
										TranMt tranMtNew = new TranMt();
										tranMtNew.setBagMt(tranMtt.getBagMt());
										tranMtNew.setPcs(tranMtt.getPcs());
										tranMtNew.setCurrStk(true);
										tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
										tranMtNew.setTrandate(tranDate);
										tranMtNew.setCreatedBy(principal.getName());
										tranMtNew.setCreatedDate(new java.util.Date());
										tranMtNew.setDepartment(departmentTo);
										tranMtNew.setDeptFrom(tranMtt.getDepartment());
										tranMtNew.setOrderMt(tranMtt.getOrderMt());
										tranMtNew.setOrderDt(tranMtt.getOrderDt());
										tranMtNew.setRefMtId(tranMtt.getId());
										tranMtNew.setRecWt(tranMtt.getRecWt());
										tranMtNew.setRemark(remark);
									/*	tranMtNew.setPurityConv((tranMtt.getOrderDt().getPurity().getPurityConv() == null ? 0.0 : tranMtt.getOrderDt().getPurity().getPurityConv()));*/
										save(tranMtNew);
									
										//editing the existing record
										tranMtt.setCurrStk(false);
										tranMtt.setPendApprovalFlg(false);
										tranMtt.setDeptTo(departmentTo);
										tranMtt.setIssueDate(tranDate);
										tranMtt.setModiBy(principal.getName());
										tranMtt.setModiDate(new java.util.Date());
										tranMtt.setIssWt(tranMtt.getRecWt());
										
										//tranMtt.setLoss(Double.parseDouble(df.format(vRecWt-vIssWt)));

										save(tranMtt);

										
										//---------TranMetal ------//----------//
										List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMtt, true);
										if(tranMetals.size()>0){
											
											for(TranMetal tranMetal :tranMetals){
												TranMetal tranMetal2 = new TranMetal();
												tranMetal2.setBagMt(tranMetal.getBagMt());
												tranMetal2.setColor(tranMetal.getColor());
												tranMetal2.setMainMetal(tranMetal.getMainMetal());
												tranMetal2.setCreatedBy(principal.getName());
												tranMetal2.setCreatedDate(new Date());
												tranMetal2.setCurrStk(true);
												tranMetal2.setDepartment(departmentTo);
												tranMetal2.setDeptFrom(tranMetal.getDepartment());
												tranMetal2.setMetalPcs(tranMetal.getMetalPcs());
												tranMetal2.setRefTranMetalId(tranMetal.getId());
												tranMetal2.setMetalWeight(tranMetal.getMetalWeight());
												tranMetal2.setPartNm(tranMetal.getPartNm());
												tranMetal2.setPurity(tranMetal.getPurity());
												tranMetal2.setPurityConv(tranMetal.getPurity().getPurityConv());
												tranMetal2.setTime(new java.sql.Time(new java.util.Date().getTime()));
												tranMetal2.setTranDate(tranDate);
											
												tranMetal2.setTranMt(tranMtNew);
												tranMetal2.setMetalRate(tranMetal.getMetalRate());
												
												tranMetalService.save(tranMetal2);
												
												
												// tranmetal False record
												tranMetal.setCurrStk(false);
												tranMetal.setDeptTo(departmentTo);
												
												tranMetal.setIssDate(tranDate);
												tranMetal.setModiBy(principal.getName());
												tranMetal.setModiDate(new java.util.Date());
												tranMetalService.save(tranMetal);
											}
											
											
											
											
										}
										
										
										
										
										/*---------------------------------------------------------*/
										
										
							
										
									//---------TranDt------//----------//
									
									List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMtt, true);
									
									if(tranDts.size()>0 ){
										
										 			
											for(TranDt tranDt:tranDts){
												
																									
												//adding the new record
												
												 TranDt tranDtNew = new TranDt();
												tranDtNew.setBagMt(tranDt.getBagMt());
												tranDtNew.setPcs(tranDt.getPcs());
												tranDtNew.setCurrStk(true);
												tranDtNew.setCreatedBy(principal.getName());
												tranDtNew.setCreatedDate(new java.util.Date());
												tranDtNew.setDepartment(departmentTo);
												tranDtNew.setDeptFrom(tranDt.getDepartment());
												tranDtNew.setRefDtId(tranDt.getId());
												
												tranDtNew.setBagSrNo(tranDt.getBagSrNo());
												tranDtNew.setCarat(tranDt.getCarat());
												tranDtNew.setSieve(tranDt.getSieve());
												tranDtNew.setSize(tranDt.getSize());
												tranDtNew.setStone(tranDt.getStone());
												tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
												tranDtNew.setTranDate(tranDate);
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
												tranDt.setDeptTo(departmentTo);
												tranDt.setIssDate(tranDate);
												tranDt.setModiBy(principal.getName());
												tranDt.setModiDate(new java.util.Date());
												
												tranDtService.save(tranDt);
												
											}
										
										
									}
								
							
						
						
							
						
						
							
						}
					
					
					retVal="1";
					
			
				
				
				} catch (Exception e) {
					// TODO: handle exception
					
					
								
					
				}
				
				return retVal;
			}
			
	    
	    	
		}



	@Override
	public String notApprovalAndReturn(String vBagNo,Integer vDepartmentToId,
			Principal principal, String remark, Date tranDate,Boolean trfApproval) {
		// TODO Auto-generated method stub
		synchronized (this) {
			String retVal="-1";	
			
			try {
				
				
				BagMt bagMt = bagMtService.findByName(vBagNo);
				
				Department departmentTo = departmentService.findOne(vDepartmentToId);
				
						
				TranMt tranMtt = findByBagMtAndDeptToAndCurrStkAndPendApprovalFlg(bagMt, departmentTo, true, true);
			
			
				if(tranMtt !=null){
					
					
					if(tranDate == null){
						tranDate =  new Date();
					}
					
						
							
						
						if(trfApproval.equals(true)) {
							
							tranMtt.setPendApprovalFlg(false);
							tranMtt.setDeptTo(null);
							tranMtt.setIssueDate(null);
							tranMtt.setModiBy(principal.getName());
							tranMtt.setModiDate(null);
							tranMtt.setIssWt(0.0);
							tranMtt.setNoneAppRm(remark);
						
							save(tranMtt);
							
							List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMtt, true);
				
								
								for(TranMetal tranMetal :tranMetals){
						
							
							// tranmetal False record
							tranMetal.setDeptTo(null);
							
							tranMetal.setIssDate(tranDate);
							tranMetal.setModiBy(null);
							tranMetal.setModiDate(null);
							tranMetalService.save(tranMetal);
						
								}
							
							
							
							//---------TranDt------//----------//
							List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMtt, true);
									for(TranDt tranDt:tranDts){
										tranDt.setDeptTo(null);
										tranDt.setIssDate(null);
										tranDt.setModiBy(null);
										tranDt.setModiDate(null);
										
										tranDtService.save(tranDt);
								
									
							}
							
							
						}
							
						
						
					}
				
				
				retVal="1";
			
			} catch (Exception e) {
				// TODO: handle exception
				
				
							
				
			}
			
			return retVal;
		}
		
	}



	@Override
	public TranMt findByBagMtAndPendApprovalFlgAndCurrStk(BagMt bagMt, Boolean pendApprovalFlg, Boolean currStk) {
		// TODO Auto-generated method stub
		return tranMtRepository.findByBagMtAndPendApprovalFlgAndCurrStk(bagMt, pendApprovalFlg, currStk);
	}



	@Override
	public TranMt findByBagMtCurrStkNew(BagMt bagMt, Boolean currStk) {
		// TODO Auto-generated method stub
		QTranMt qTranMt = QTranMt.tranMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<TranMt> tranMtList = query.from(qTranMt)
				.where(qTranMt.bagMt.id.eq(bagMt.getId()).and(qTranMt.currStk.eq(true))
						.and(qTranMt.department.name.notEqualsIgnoreCase("Casting")))
				.list(qTranMt);
		
		if(tranMtList.size()>0){
			return tranMtList.get(0);		
		}else{
			
			return null;
		}
	}



	@Override
	public String transferForReadybag(String vBagNo, Integer vDepartmentId, Integer vDepartmentToId,
			Principal principal, String remark, Date tranDate, Boolean trfApproval) {
		
		
		
		synchronized (this) {
			String retVal="-1";	
			
			try {
				
				
				BagMt bagMt = bagMtService.findByName(vBagNo);
				Department department = departmentService.findOne(vDepartmentId);
				Department departmentTo = departmentService.findByName("DIAMOND");
				
						
				TranMt tranMtt = findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
				List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMtt, true);
			
				if(tranMtt !=null){
					
					if(tranMtt.getHoldFlg().equals(true)) {
						return  "Error: Can not Transfer Bag Not received against "+ department.getName()+" issue voucher";
					}
					
					
					if(tranDate == null){
						tranDate =  new Date();
					}
					
					
					
				
					

							
							if(department.getName().equalsIgnoreCase("Casting")){
								
								CastingDt castingDt = castingDtService.findByRefMtIdAndDeactive(tranMtt.getId(), false);
								if(castingDt != null){
									if(castingDt.getTransfer().equals(false)){
										return  "Error: Could not transfer as bag present in casting tree !";
									}
								}
								
								
						
							}
							

					//trfApprovalFlg True For Nuance
						
						if(trfApproval.equals(true)) {
							
							tranMtt.setPendApprovalFlg(true);
							tranMtt.setDeptTo(departmentTo);
							tranMtt.setIssueDate(tranDate);
							tranMtt.setModiBy(principal.getName());
							tranMtt.setModiDate(new java.util.Date());
							tranMtt.setIssWt(tranMtt.getRecWt());
						
							save(tranMtt);
							
							List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMtt, true);
				
								
								for(TranMetal tranMetal :tranMetals){
						
							
							// tranmetal False record
							tranMetal.setDeptTo(departmentTo);
							
							tranMetal.setIssDate(tranDate);
							tranMetal.setModiBy(principal.getName());
							tranMetal.setModiDate(new java.util.Date());
							tranMetalService.save(tranMetal);
						
								}
							
							
							
							//---------TranDt------//----------//
							
									for(TranDt tranDt:tranDts){
										tranDt.setDeptTo(departmentTo);
										tranDt.setIssDate(tranDate);
										tranDt.setModiBy(principal.getName());
										tranDt.setModiDate(new java.util.Date());
										
										tranDtService.save(tranDt);
								
									
							}
							
							
						}else {		
									
											
									//adding the new record
									TranMt tranMtNew = new TranMt();
									tranMtNew.setBagMt(tranMtt.getBagMt());
									tranMtNew.setPcs(tranMtt.getPcs());
									tranMtNew.setCurrStk(true);
									tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
									tranMtNew.setTrandate(tranDate);
									tranMtNew.setCreatedBy(principal.getName());
									tranMtNew.setCreatedDate(new java.util.Date());
									tranMtNew.setDepartment(departmentTo);
									tranMtNew.setDeptFrom(tranMtt.getDepartment());
									tranMtNew.setOrderMt(tranMtt.getOrderMt());
									tranMtNew.setOrderDt(tranMtt.getOrderDt());
									tranMtNew.setRefMtId(tranMtt.getId());
									tranMtNew.setRecWt(tranMtt.getRecWt());
									tranMtNew.setRemark(remark);
								/*	tranMtNew.setPurityConv((tranMtt.getOrderDt().getPurity().getPurityConv() == null ? 0.0 : tranMtt.getOrderDt().getPurity().getPurityConv()));*/
									save(tranMtNew);
								
									//editing the existing record
									tranMtt.setCurrStk(false);
									tranMtt.setPendApprovalFlg(false);
									tranMtt.setDeptTo(departmentTo);
									tranMtt.setIssueDate(tranDate);
									tranMtt.setModiBy(principal.getName());
									tranMtt.setModiDate(new java.util.Date());
									tranMtt.setIssWt(tranMtt.getRecWt());
									//tranMtt.setLoss(Double.parseDouble(df.format(vRecWt-vIssWt)));

									save(tranMtt);

									
									//---------TranMetal ------//----------//
									List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMtt, true);
									if(tranMetals.size()>0){
										
										for(TranMetal tranMetal :tranMetals){
											TranMetal tranMetal2 = new TranMetal();
											tranMetal2.setBagMt(tranMetal.getBagMt());
											tranMetal2.setColor(tranMetal.getColor());
											tranMetal2.setMainMetal(tranMetal.getMainMetal());
											tranMetal2.setCreatedBy(principal.getName());
											tranMetal2.setCreatedDate(new Date());
											tranMetal2.setCurrStk(true);
											tranMetal2.setDepartment(departmentTo);
											tranMetal2.setDeptFrom(tranMetal.getDepartment());
											tranMetal2.setMetalPcs(tranMetal.getMetalPcs());
											tranMetal2.setRefTranMetalId(tranMetal.getId());
											tranMetal2.setMetalWeight(tranMetal.getMetalWeight());
											tranMetal2.setPartNm(tranMetal.getPartNm());
											tranMetal2.setPurity(tranMetal.getPurity());
											tranMetal2.setPurityConv(tranMetal.getPurity().getPurityConv());
											tranMetal2.setTime(new java.sql.Time(new java.util.Date().getTime()));
											tranMetal2.setTranDate(tranDate);
											tranMetal2.setTranMt(tranMtNew);
											tranMetal2.setMetalRate(tranMetal.getMetalRate());
											tranMetalService.save(tranMetal2);
											
											
											// tranmetal False record
											tranMetal.setCurrStk(false);
											tranMetal.setDeptTo(departmentTo);
											tranMetal.setIssDate(tranDate);
											tranMetal.setModiBy(principal.getName());
											tranMetal.setModiDate(new java.util.Date());
											tranMetalService.save(tranMetal);
										}
										
									}
									
									
									
									
									/*---------------------------------------------------------*/
									
									
						
									
								//---------TranDt------//----------//
								
								List<TranDt> tranDts1 = tranDtService.findByTranMtAndCurrStk(tranMtt, true);
								if(tranDts.size()>0 ){
										for(TranDt tranDt:tranDts1){
																								
											//adding the new record
											
											 TranDt tranDtNew = new TranDt();
											tranDtNew.setBagMt(tranDt.getBagMt());
											tranDtNew.setPcs(tranDt.getPcs());
											tranDtNew.setCurrStk(true);
											tranDtNew.setCreatedBy(principal.getName());
											tranDtNew.setCreatedDate(new java.util.Date());
											tranDtNew.setDepartment(departmentTo);
											tranDtNew.setDeptFrom(tranDt.getDepartment());
											tranDtNew.setRefDtId(tranDt.getId());
											tranDtNew.setBagSrNo(tranDt.getBagSrNo());
											tranDtNew.setCarat(tranDt.getCarat());
											tranDtNew.setSieve(tranDt.getSieve());
											tranDtNew.setSize(tranDt.getSize());
											tranDtNew.setStone(tranDt.getStone());
											tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
											tranDtNew.setTranDate(tranDate);
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
											tranDt.setDeptTo(departmentTo);
											tranDt.setIssDate(tranDate);
											tranDt.setModiBy(principal.getName());
											tranDt.setModiDate(new java.util.Date());
											
											tranDtService.save(tranDt);
											
										}
									
								}
								
						}
							
						
						
					}
				
				
				retVal="1";
			
			} catch (Exception e) {
				// TODO: handle exception
				
				
							
				
			}
			
			return retVal;
		}
		
		
	}



	@Override
	public List<TranMt> getBagsNotCloseByDepartment(Integer deptId) {
		// TODO Auto-generated method stub
		QTranMt qTranMt = QTranMt.tranMt;
		JPAQuery query = new JPAQuery(entityManager);

		List<TranMt> tranMts =query.from(qTranMt).
				where(qTranMt.currStk.eq(true).and(qTranMt.department.id.eq(deptId))
						.and(qTranMt.bagMt.bagCloseFlg.eq(false))).orderBy(qTranMt.bagMt.name.asc()).list(qTranMt);

		return tranMts;
	}



	@Override
	public String displayTranDetails(String BagNo, Integer departmentId, TranMt tranMt,String uploadFilePath) {
		String str = null;
		if(departmentId != null){
			
			
			BagMt bagMt = bagMtService.findByName(BagNo);
			Department department = departmentService.findOne(departmentId);
			TranMt tranMtt =findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
				
			if(tranMtt != null){
				
				if(!tranMtt.getPendApprovalFlg()) {
					OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());

				

					String img = orderDt.getDesign().getDefaultImage();
					
					
					
			
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
					String delDate="";
					if(tranMtt.getOrderMt().getDispatchDate() !=null){
						delDate=formatter.format(tranMtt.getOrderMt().getDispatchDate()) ;
					}
					
					 
					
					String ordDate =formatter.format(tranMtt.getOrderMt().getInvDate()) ; 
					
					 str = "" 
							+ tranMtt.getOrderMt().getParty().getPartyCode() + "#"
							+ tranMtt.getOrderMt().getInvNo() + "#"
							+ ordDate + "#"
							+ tranMtt.getOrderMt().getOrderType().getName() + "#"
							+ delDate + "#"
							+ tranMtt.getOrderDt().getDesign().getMainStyleNo() + "#"
							+ tranMtt.getPcs() + "#" 
							+ tranMtt.getRecWt() + "#"
							+ uploadFilePath + img + "#";
				 


				}else {
					str = "-1";
				}
				
							}else{
					str = "-1";
				}
		}else{
			str = "-2";
		}
		
		
		return str;
	}



	@Override
	public String multiCompAdditionList(Integer deptId) {
		
		StringBuilder sb = new StringBuilder();
		List<TranMt> tranMts = getBagsForMultiComponentAddtion(deptId);
		
		sb.append("{\"total\":").append(tranMts.size()).append(",\"rows\": [");
		
		Integer rows = 1 ;
				
		for (TranMt tranMt : tranMts) {
			sb.append("{\"id\":\"")
					.append(tranMt.getId())
					.append("\",\"row\":\"")
					.append((rows++))
					.append("\",\"party\":\"")
					.append((tranMt.getOrderMt().getParty() != null ? tranMt.getOrderMt().getParty().getName() : ""))
					.append("\",\"orderNo\":\"")
					.append((tranMt.getOrderMt() != null ? tranMt.getOrderMt().getInvNo() : ""))
					.append("\",\"bagNo\":\"")
					.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getName() : ""))
					.append("\",\"pcs\":\"")
					.append((tranMt.getPcs() != null ? tranMt.getPcs() : ""))
					.append("\",\"style\":\"")
					.append((tranMt.getOrderDt() != null ? tranMt.getOrderDt().getDesign().getMainStyleNo() : ""))
					
					  .append("\",\"image\":\"")
					  //.append((tranMt.getOrderDt() != null ?(tranMt.getOrderDt().getDesign().getDefaultImage() != null ? tranMt.getOrderDt().getDesign().getDefaultImage() : "blank.png") : "blank.png"))
					  .append(tranMt.getOrderDt().getDesign().getDefaultImage() != null && !tranMt.getOrderDt().getDesign().getDefaultImage().isEmpty()? tranMt.getOrderDt().getDesign().getDefaultImage() : "blank.png")
					 .append("\",\"metalWt\":\"")
					.append((tranMt.getRecWt() != null ?Math.round((tranMt.getRecWt())*1000.0)/1000.0 : 0.0 ))
					.append("\",\"orderDtNo\":\"")
					.append((tranMt.getOrderDt() != null ? tranMt.getOrderDt().getId() : ""))
					.append("\",\"bagId\":\"")
					.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getId() : ""))
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
	

		return str;
	}



	@Override
	public List<TranMt> findByDepartmentAndCurrStkAndPendApprovalFlg(Department department, Boolean currStk,
			Boolean pendApprovalFlg) {
		// TODO Auto-generated method stub
		return tranMtRepository.findByDepartmentAndCurrStkAndPendApprovalFlg(department, currStk, pendApprovalFlg);
	}



	@Override
	public Page<TranMt> searchAllByDepartment(Integer limit, Integer offset, String sort, String order, String name,
			Integer deptId) {
		
		
		QTranMt qTranMt = QTranMt.tranMt;
		BooleanExpression expression = qTranMt.currStk.eq(true).and(
						qTranMt.department.id.eq(deptId).and(qTranMt.pendApprovalFlg.eq(false)).and(qTranMt.bagMt.bagCloseFlg.eq(false)));

		if(name != null && !name.isEmpty()){
			expression = qTranMt.currStk.eq(true).and(
					qTranMt.department.id.eq(deptId)).and(qTranMt.pendApprovalFlg.eq(false)).and(qTranMt.bagMt.bagCloseFlg.eq(false))
							.and(qTranMt.orderMt.party.partyCode.like(name+"%")
							.or(qTranMt.orderMt.invNo.like(name+"%")).or(qTranMt.orderMt.refNo.like(name+"%"))
							.or(qTranMt.bagMt.name.like(name+"%")).or(qTranMt.orderDt.design.mainStyleNo.like(name+"%")));
		}
		
		int page = ((offset == null || offset == 0) ? 0 : (offset / limit));

		if (limit == null) {
			limit = 1000;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return tranMtRepository.findAll(expression, new PageRequest(page,
				limit, Direction.DESC, sort));

	}



	@Override
	public String departmentstockList(Integer limit, Integer offset, String sort, String order, String name,
			Integer deptId) {
		
		Page<TranMt>tranmts=searchAllByDepartment(limit, offset, sort, order, name, deptId);
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(tranmts.getTotalElements()).append(",\"rows\": [");
		 
		 
		 for(TranMt tranMt:tranmts){
			 
				
				sb.append("{\"party\":\"")
			     .append(tranMt.getOrderMt().getParty().getPartyCode())
			     .append("\",\"orderNo\":\"")
				 .append(tranMt.getOrderMt().getInvNo())
				 .append("\",\"refNo\":\"")
				 .append(tranMt.getOrderMt().getRefNo() !=null?tranMt.getOrderMt().getRefNo():"")
				 .append("\",\"bagNo\":\"")
				 .append(tranMt.getBagMt().getName())
				 .append("\",\"styleNo\":\"")
				 .append(tranMt.getOrderDt().getDesign().getMainStyleNo())
				 .append("\",\"qty\":\"")
				 .append(tranMt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(tranMt.getRecWt())
				
				 .append("\"},");
			}
		 
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			System.out.println("str "+str);
		return str;
		
	}



	@Override
	public String costingTransferListing(Integer limit, Integer offset, String sort, String order, String name,
			Integer deptId, Boolean partyFlg, Integer partyId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		Page<TranMt> tranMts = getBagsForCosting(limit, offset, sort, order, name, deptId,partyFlg,partyId);
		
		sb.append("{\"total\":").append(tranMts.getTotalElements()).append(",\"rows\": [");
			
		for (TranMt tranMt : tranMts) {
			
			Double totalCarat = 0.0;
			Integer totalStone = 0;
			List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
			for (TranDt tranDt : tranDts) {
				totalCarat += tranDt.getCarat();
				totalStone += tranDt.getStone();
				
			}
			
			sb.append("{\"id\":\"")
					.append(tranMt.getId())
					.append("\",\"party\":\"")
					.append((tranMt.getOrderMt().getParty() != null ? tranMt.getOrderMt().getParty().getPartyCode() : ""))
					.append("\",\"orderNo\":\"")
					.append((tranMt.getOrderMt() != null ? tranMt.getOrderMt().getInvNo() : ""))
					.append("\",\"bagNo\":\"")
					.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getName() : ""))
					.append("\",\"pcs\":\"")
					.append((tranMt.getPcs() != null ? tranMt.getPcs() : ""))
					.append("\",\"style\":\"")
					.append((tranMt.getOrderDt() != null ? tranMt.getOrderDt().getDesign().getMainStyleNo() : ""))
					.append("\",\"image\":\"")
					.append((tranMt.getOrderDt() != null ? (tranMt.getOrderDt().getDesign().getDefaultImage() != null ? tranMt.getOrderDt().getDesign().getDefaultImage() : "blank.png") : "blank.png"))
					.append("\",\"metalWt\":\"")
					.append((tranMt.getRecWt() != null ?Math.round((tranMt.getRecWt())*1000.0)/1000.0 : 0.0 ))
					.append("\",\"itemNo\":\"")
					.append((tranMt.getBagMt().getItemNo() == null || tranMt.getBagMt().getItemNo().equals("") ? "" : tranMt.getBagMt().getItemNo()))
					.append("\",\"orderDtNo\":\"")
					.append((tranMt.getOrderDt() != null ? tranMt.getOrderDt().getId() : ""))
					.append("\",\"bagId\":\"")
					.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getId() : ""))
					.append("\",\"totalCarat\":\"")
					.append(Math.round(totalCarat*1000.0)/1000.0)
					.append("\",\"totalStone\":\"")
					.append((totalStone))
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		

		return str;
	}



	@Override
	public String rejectionTranMtList(Integer limit, Integer offset, String sort, String order, String search) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();

		Department department = departmentService.findByName("Rejection");
	
		
		Page<TranMt> tranMts = rejectionRecastList(limit, offset, sort, order, search, department.getId());
		
		sb.append("{\"total\":").append(tranMts.getTotalElements()).append(",\"rows\": [");

		for (TranMt tranMt : tranMts) {
					sb.append("{\"id\":\"")
							.append(tranMt.getId())
							.append("\",\"party\":\"")
							.append((tranMt.getOrderMt().getParty() != null ? tranMt.getOrderMt().getParty().getPartyCode() : ""))
							.append("\",\"orderNo\":\"")
							.append((tranMt.getOrderMt().getInvNo() != null ? tranMt.getOrderMt().getInvNo() : ""))
							.append("\",\"bagNo\":\"")
							.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getName() : ""))
							.append("\",\"bagId\":\"")
							.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getId() : ""))
							.append("\",\"purity\":\"")
							.append((tranMt.getOrderDt().getPurity() != null ? tranMt.getOrderDt().getPurity().getName() : ""))
							.append("\",\"color\":\"")
							.append((tranMt.getOrderDt().getColor() != null ? tranMt.getOrderDt().getColor().getName(): ""))
							.append("\",\"pcs\":\"")
							.append((tranMt.getPcs() != null ? tranMt.getPcs(): ""))
							.append("\",\"recWt\":\"")
							.append((tranMt.getRecWt() != null ? tranMt.getRecWt() : ""))
							.append("\"},");
				
			
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}



	@Override
	public String getBagsForJobIssMt(Integer limit, Integer offset, String sort, String order, String search,
			Integer deptId, String invDt) throws ParseException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		Date date2= new Date();
		
		if(invDt !=null && !invDt.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date2 = originalFormat.parse(invDt);
		}

		
		Page<TranMt> tranMts = getBagsForJobIss(limit, offset, sort, order, search, deptId, date2);
		
		sb.append("{\"total\":").append(tranMts.getTotalElements()).append(",\"rows\": [");
	
		
      
	
			
		for (TranMt tranMt : tranMts) {
			
			sb.append("{\"id\":\"")
					.append(tranMt.getId())
					.append("\",\"party\":\"")
					.append((tranMt.getOrderMt().getParty() != null ? tranMt.getOrderMt().getParty().getPartyCode() : ""))
					.append("\",\"orderNo\":\"")
					.append((tranMt.getOrderMt() != null ? tranMt.getOrderMt().getInvNo() : ""))
					.append("\",\"bagNo\":\"")
					.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getName() : ""))
					.append("\",\"pcs\":\"")
					.append((tranMt.getPcs() != null ? tranMt.getPcs() : ""))
					.append("\",\"style\":\"")
					.append((tranMt.getOrderDt() != null ? tranMt.getOrderDt().getDesign().getMainStyleNo() : ""))
					.append("\",\"image\":\"")
					.append((tranMt.getOrderDt() != null ? (tranMt.getOrderDt().getDesign().getDefaultImage() != null ? tranMt.getOrderDt().getDesign().getDefaultImage() : "blank.png") : "blank.png"))
					.append("\",\"metalWt\":\"")
					.append((tranMt.getRecWt() != null ?Math.round((tranMt.getRecWt())*1000.0)/1000.0 : 0.0 ))
					.append("\",\"itemNo\":\"")
					.append((tranMt.getBagMt().getItemNo() == null || tranMt.getBagMt().getItemNo().equals("") ? "" : tranMt.getBagMt().getItemNo()))
					.append("\",\"orderDtNo\":\"")
					.append((tranMt.getOrderDt() != null ? tranMt.getOrderDt().getId() : ""))
					.append("\",\"bagId\":\"")
					.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getId() : ""))
					.append("\"},");
			
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		

		return str;
	}




	
	

}

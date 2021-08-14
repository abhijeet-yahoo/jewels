package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IReadyBagRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
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
public class ReadyBagService implements IReadyBagService {

	@Autowired
	private IReadyBagRepository readyBagRepository;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IStoneTranService stoneTranService;


	@Override
	public List<ReadyBag> findAll() {
		return readyBagRepository.findAll();
	}

	@Override
	public Page<ReadyBag> findAll(Integer limit, Integer offset, String sort,
			String readyBag, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return readyBagRepository.findAll(new PageRequest(page, limit, (readyBag
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(ReadyBag readyBag) {
		readyBagRepository.save(readyBag);
	}

	@Override
	public void delete(int id) {
//		ReadyBag readyBag = readyBagRepository.findOne(id);
//		readyBag.setDeactive(true);
//		readyBag.setDeactiveDt(new java.util.Date());
//		readyBagRepository.save(readyBag);

		readyBagRepository.delete(id);
	}

	@Override
	public Long count() {
		return readyBagRepository.count();
	}

	@Override
	public ReadyBag findOne(int id) {
		return readyBagRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getReadyBagList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadyBag findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReadyBag> findActivebags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QReadyBag qReadyBag = QReadyBag.readyBag;
		BooleanExpression expression = qReadyBag.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qReadyBag.deactive.eq(false);
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("baggingNo")) && colValue != null) {
				 expression = qReadyBag.bagMt.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return readyBagRepository.count();
			} else {
				return readyBagRepository.count();
			}
		}

		return readyBagRepository.count(expression);
	}

	public List<ReadyBag> getBagsForIssuing(BagMt bagMt,Integer locationId) {
		QReadyBag qReadyBag = QReadyBag.readyBag;
		BooleanExpression expression = qReadyBag.deactive.eq(false).and(qReadyBag.bagMt.id.eq(bagMt.getId()).and(qReadyBag.currentStock.eq(true))
				.and(qReadyBag.pendApprovalFlg.eq(false)).and(qReadyBag.location.id.eq(locationId)));
		return (List<ReadyBag>) readyBagRepository.findAll(expression);

	}
	
	public Integer getMaxSrno() {
		QReadyBag qReadyBag = QReadyBag.readyBag;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;

		List<Integer> maxSrno = query
			.from(qReadyBag)
			.where(qReadyBag.deactive.eq(false))
			.list(qReadyBag.bagSrNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}

		return retVal;
	}

	
	/*@Override
	public ReadyBag findByBagMtAndBagSrNoAndRetStoneAndRetCarat(BagMt bagMt,
			Integer bagSrNo, Integer retStone, Double retCarat) {
		// TODO Auto-generated method stub
		return readyBagRepository.findByBagMtAndBagSrNoAndRetStoneAndRetCarat(bagMt, bagSrNo, retStone, retCarat);
	}*/
	
	@Override
	public List<ReadyBag> findByBagMtAndCurrentStock(BagMt bagMt,Boolean currStk) {
		return readyBagRepository.findByBagMtAndCurrentStock(bagMt, currStk);
	}

	@Override
	public String readyBagTransfer(String pBagNm, 
			String pIds, Integer pDeptId, Boolean pUpdGold, Principal principal,Date tranDate,Integer pLocationId) {
		// TODO Auto-generated method stub
		String retVal="-1";

		BagMt bagMt = bagMtService.findByName(pBagNm);
		
		Department departmentTo=  departmentService.findOne(pDeptId);
		
		
		Department department = departmentService.findOne(pLocationId);
		
		TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
						
		if(tranMt == null){
			return  "-2";
		}
		
		
		
		tranMt.setDeptTo(departmentService.findOne(pDeptId));
		tranMt.setIssueDate(tranDate);
		tranMt.setCurrStk(false);
		tranMt.setModiBy(principal.getName());
		tranMt.setModiDate(new java.util.Date());
		

		
		TranMt tranMtNew = new TranMt();
		tranMtNew.setBagMt(tranMt.getBagMt());
		tranMtNew.setDepartment(departmentTo);
		tranMtNew.setDeptFrom(departmentService.findByName("Diamond"));
		tranMtNew.setPcs(tranMt.getPcs());
	/*	tranMtNew.setPurityConv(tranMt.getPurityConv());*/
		tranMtNew.setRefMtId(tranMt.getId());
		tranMtNew.setOrderDt(tranMt.getOrderDt());
		tranMtNew.setRemark(tranMt.getRemark());
		tranMtNew.setOrderMt(tranMt.getOrderMt());
		tranMtNew.setCurrStk(true);
		tranMtNew.setCreatedBy(principal.getName());
		tranMtNew.setCreatedDate(new java.util.Date());
		tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
		tranMtNew.setTrandate(tranDate);


		tranMtService.save(tranMt);
		tranMtService.save(tranMtNew);
		
		
		
		
		//---------TranMetal ------//----------//
		List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMt, true);
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
		
		
		
		List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
		

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
                        
			
		
		
		
		
		Double carats = 0.0;
		String[] ids = pIds.split(",");
		for (int x=0; x<ids.length; x++) {

			ReadyBag readyBag = findOne(Integer.parseInt(ids[x]));
			carats += readyBag.getCarat();
			TranDt tranDtTmp = new TranDt();
			
			tranDtTmp.setBagMt(readyBag.getBagMt());
			tranDtTmp.setBagSrNo(readyBag.getBagSrNo());
			tranDtTmp.setCarat(readyBag.getCarat());
			tranDtTmp.setCurrStk(readyBag.getCurrentStock());
			tranDtTmp.setDepartment(departmentTo);
			tranDtTmp.setDeptFrom(department);
			tranDtTmp.setIssDate(tranDate);
			tranDtTmp.setPcs(Double.parseDouble(readyBag.getBagPcs().toString()));
			tranDtTmp.setQuality(readyBag.getQuality());
			tranDtTmp.setSetting(readyBag.getSetting());
			tranDtTmp.setSettingType(readyBag.getSettingType());
			tranDtTmp.setShape(readyBag.getShape());
			tranDtTmp.setSieve(readyBag.getSieve());
			tranDtTmp.setSize(readyBag.getSize());
			tranDtTmp.setSizeGroup(readyBag.getSizeGroup());
			tranDtTmp.setStone(readyBag.getStone());
			tranDtTmp.setTime(new java.sql.Time(new java.util.Date().getTime()));
			tranDtTmp.setTranDate(tranDate);
			tranDtTmp.setTranMt(tranMtNew);
			tranDtTmp.setCreatedBy(principal.getName());
			tranDtTmp.setCreatedDate(new java.util.Date());
			tranDtTmp.setStoneType(readyBag.getStoneType());
			tranDtTmp.setCenterStone(readyBag.getCenterStone());
			tranDtTmp.setPartNm(readyBag.getPartNm());
			tranDtTmp.setRate(readyBag.getRate());
			tranDtTmp.setFactoryRate(readyBag.getFactoryRate());
			tranDtTmp.setAvgRate(readyBag.getAvgRate());
			tranDtTmp.setTransferRate(readyBag.getTransferRate());
			
			
			tranDtService.save(tranDtTmp);
			
			readyBag.setIssDt(tranDate);
			readyBag.setCurrentStock(false);
			readyBag.setDepartment(departmentTo);
			readyBag.setTranMt(tranMtNew);
			save(readyBag);
			
		}
		
		
		
		

		Double recWt = tranMt.getRecWt();
		if (recWt > 0) {
			
			if (pUpdGold) {
			} else {
				recWt = (recWt + (carats / 5));
			}

			
			
			
			tranMt.setIssWt(Math.round(recWt*1000.0)/1000.0);
			tranMtNew.setRecWt(Math.round(recWt*1000.0)/1000.0);
			
			tranMtService.save(tranMt);
			tranMtService.save(tranMtNew);
		}
		
		retVal="1";
		
		
		return retVal;
	}

	@Override
	public String multiReadyBagTransfer(String pBagNm, Integer pDeptId,
			Boolean pUpdGold, Principal principal,Date tranDate,Integer locationId) {
		// TODO Auto-generated method stub
		
		
		String retVal="Contact Admin";

		BagMt bagMt = bagMtService.findByName(pBagNm);
		
		Department departmentTo=  departmentService.findOne(pDeptId);
		
		
		Department department = departmentService.findOne(locationId);
		
		TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
						
		if(tranMt == null){
			return  "Bag Not In Diamond Department";
		}
		
		
		
		tranMt.setDeptTo(departmentService.findOne(pDeptId));
		tranMt.setIssueDate(tranDate);
		tranMt.setCurrStk(false);
		tranMt.setModiBy(principal.getName());
		tranMt.setModiDate(new java.util.Date());
		

		
		TranMt tranMtNew = new TranMt();
		tranMtNew.setBagMt(tranMt.getBagMt());
		tranMtNew.setDepartment(departmentTo);
		tranMtNew.setDeptFrom(tranMt.getDepartment());
		tranMtNew.setPcs(tranMt.getPcs());
	/*	tranMtNew.setPurityConv(tranMt.getPurityConv());*/
		tranMtNew.setRefMtId(tranMt.getId());
		tranMtNew.setOrderDt(tranMt.getOrderDt());
		tranMtNew.setRemark(tranMt.getRemark());
		tranMtNew.setOrderMt(tranMt.getOrderMt());
		tranMtNew.setCurrStk(true);
		tranMtNew.setCreatedBy(principal.getName());
		tranMtNew.setCreatedDate(new java.util.Date());
		tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
		tranMtNew.setTrandate(tranDate);


		tranMtService.save(tranMt);
		tranMtService.save(tranMtNew);
		
		
		
		
		//---------TranMetal ------//----------//
		List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMt, true);
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
		
		
		
		List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
		

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
                        
			
		
		
		Double carats = 0.0;
		
		List <ReadyBag> readyBags = getBagsForIssuing(bagMt,department.getId());
		for(ReadyBag readyBag:readyBags) {

			
			carats += readyBag.getCarat();
			TranDt tranDtTmp = new TranDt();
			
			tranDtTmp.setBagMt(readyBag.getBagMt());
			tranDtTmp.setBagSrNo(readyBag.getBagSrNo());
			tranDtTmp.setCarat(readyBag.getCarat());
			tranDtTmp.setCurrStk(readyBag.getCurrentStock());
			tranDtTmp.setDepartment(departmentTo);
			tranDtTmp.setDeptFrom(department);
			tranDtTmp.setIssDate(tranDate);
			tranDtTmp.setPcs(Double.parseDouble(readyBag.getBagPcs().toString()));
			tranDtTmp.setQuality(readyBag.getQuality());
			tranDtTmp.setSetting(readyBag.getSetting());
			tranDtTmp.setSettingType(readyBag.getSettingType());
			tranDtTmp.setShape(readyBag.getShape());
			tranDtTmp.setSieve(readyBag.getSieve());
			tranDtTmp.setSize(readyBag.getSize());
			tranDtTmp.setSizeGroup(readyBag.getSizeGroup());
			tranDtTmp.setStone(readyBag.getStone());
			tranDtTmp.setTime(new java.sql.Time(new java.util.Date().getTime()));
			tranDtTmp.setTranDate(tranDate);
			tranDtTmp.setTranMt(tranMtNew);
			tranDtTmp.setCreatedBy(principal.getName());
			tranDtTmp.setCreatedDate(new java.util.Date());
			tranDtTmp.setStoneType(readyBag.getStoneType());
			tranDtTmp.setCenterStone(readyBag.getCenterStone());
			tranDtTmp.setPartNm(readyBag.getPartNm());
			tranDtTmp.setRate(readyBag.getRate());
			tranDtTmp.setFactoryRate(readyBag.getFactoryRate());
			tranDtTmp.setAvgRate(readyBag.getAvgRate());
			tranDtTmp.setTransferRate(readyBag.getTransferRate());
			
			
			tranDtService.save(tranDtTmp);
			
			readyBag.setIssDt(tranDate);
			readyBag.setCurrentStock(false);
			readyBag.setDepartment(departmentTo);
			readyBag.setTranMt(tranMtNew);
			save(readyBag);
			
		}
		
		
		
		

		Double recWt = tranMt.getRecWt();
		if (recWt > 0) {
			
			if (pUpdGold) {
			} else {
				recWt = (recWt + (carats / 5));
			}

			
			
			
			tranMt.setIssWt(Math.round(recWt*1000.0)/1000.0);
			tranMtNew.setRecWt(Math.round(recWt*1000.0)/1000.0);
			
			tranMtService.save(tranMt);
			tranMtService.save(tranMtNew);
		}
		
		retVal="1";
		
		
		return retVal;
	}

	@Override
	public String getReadyBagDetail(Integer deptid) {
		// TODO Auto-generated method stub
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		String departmentCond ="";
		
			departmentCond = " deptId in (" + deptid + ")  ";

		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_readyBaglist(?) }");
		query.setParameter(1, departmentCond);
		objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
//			 if(orderNo !=null && !orderNo.isEmpty()){
//					if(!list[4].toString().equalsIgnoreCase(orderNo)){
//						continue;
//					}
//				}
			 
			 
				
				sb.append("{\"deptid\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"grossWt\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"qty\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"party\":\"")
				 .append(list[3] != null ? list[3] : "")
				 .append("\",\"orderNo\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[7] != null ? list[7] : 0)
				 .append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
		
		return str;
	}

	@Override
	public List<ReadyBag> findByBagMtAndBagSrNoAndDeactive(BagMt bagMt,
			Integer bagSrNo, Boolean deactive) {
		
		
		return readyBagRepository.findByBagMtAndBagSrNoAndDeactive(bagMt, bagSrNo, deactive);
	}

	@Override
	public String readyBagDelete(Integer id,Principal principal,Date tranDate,String companyName) {
		
		String retVal ="Error : Contact Admin";
		
		ReadyBag readyBag = findOne(id);
		if(!readyBag.getCurrentStock()){
			retVal ="Error : Bagging Already Issued,Can Not Delete";
		}else{

			
			Department location=  departmentService.findByName("Bagging");
			
			
			
			
			
			StoneTran stoneTran = new StoneTran();
			
			stoneTran.setBalCarat(readyBag.getCarat());
			stoneTran.setBalStone(readyBag.getStone());
			stoneTran.setCarat(readyBag.getCarat());
			stoneTran.setDeptLocation(location);
			stoneTran.setSieve(readyBag.getSieve());
			stoneTran.setStone(readyBag.getStone());
			stoneTran.setSize(readyBag.getSize());
			stoneTran.setBagMt(readyBag.getBagMt());
			stoneTran.setInOutFld("C");
			stoneTran.setBagSrNo(readyBag.getBagSrNo());
			stoneTran.setCreatedBy(principal.getName());
			stoneTran.setCreatedDt(new java.util.Date());
			stoneTran.setQuality(readyBag.getQuality()); 
			stoneTran.setStoneType(readyBag.getStoneType());
			stoneTran.setShape(readyBag.getShape());
			stoneTran.setSubShape(readyBag.getSubShape());
			stoneTran.setSetting(readyBag.getSetting());
			stoneTran.setSettingType(readyBag.getSettingType());
			stoneTran.setSizeGroup(readyBag.getSizeGroup());
			stoneTran.setTranType("BaggingReturn");
			stoneTran.setCenterStone(readyBag.getCenterStone());
			stoneTran.setPartNm(readyBag.getPartNm());
			stoneTran.setTranDate(tranDate);
			stoneTran.setDiaCateg(readyBag.getDiaCateg());
			stoneTran.setSordDtId(readyBag.getBagMt().getOrderDt().getId());
			stoneTran.setBagQty(readyBag.getBagMt().getQty());

			stoneTran.setSordMtId(readyBag.getBagMt().getOrderDt().getOrderMt().getId());
			stoneTran.setRate(readyBag.getRate());
			stoneTran.setAvgRate(readyBag.getAvgRate());
			stoneTran.setFactoryRate(readyBag.getFactoryRate());
			stoneTran.setTransferRate(readyBag.getTransferRate());
			stoneTranService.save(stoneTran);
			delete(id);
			retVal="1";
		}
		
	
		
		return retVal;
	}

	@Override
	public List<ReadyBag> findByTranMtAndCurrentStockAndDeactive(TranMt tranMt, Boolean currStk, Boolean deactive) {
		// TODO Auto-generated method stub
		return readyBagRepository.findByTranMtAndCurrentStockAndDeactive(tranMt, currStk, deactive);
	}

	@Override
	public List<ReadyBag> findByBagMtAndLocationIsNull(BagMt bagMt) {
		// TODO Auto-generated method stub
		return readyBagRepository.findByBagMtAndLocationIsNull(bagMt);
	}

	@Override
	public List<ReadyBag> findByBagMt(BagMt bagMt) {
		// TODO Auto-generated method stub
		return readyBagRepository.findByBagMt(bagMt);
	}

	

	@Override
	public String getReadyBagReturnList(String pBagNm, Integer locationId) {
		// TODO Auto-generated method stub
		//TranMt tranMt = tranMtService.findOne(pBgIssId);
				
			BagMt bagMt = bagMtService.findByName(pBagNm.trim());
				
				StringBuilder sb = new StringBuilder();
				
				if (bagMt != null) {
					List<ReadyBag> readyBags = findByBagMtAndCurrentStockAndLocationIsNull(bagMt,true);
			
					sb.append("{\"total\":").append(readyBags.size()).append(",\"rows\": [");
					
					for (ReadyBag readyBag : readyBags) {
						sb.append("{\"id\":\"")
							.append(readyBag.getId())
							.append("\",\"stoneType\":\"")
							.append((readyBag.getStoneType() == null ? "" : readyBag.getStoneType().getName()))
							.append("\",\"partNm\":\"")
							.append((readyBag.getPartNm() == null ? "" : readyBag.getPartNm().getFieldValue()))
							.append("\",\"shape\":\"")
							.append((readyBag.getShape() == null ? "" : readyBag.getShape().getName()))
							.append("\",\"quality\":\"")
							.append((readyBag.getQuality() == null ? "" : readyBag.getQuality().getName()))
							.append("\",\"mm\":\"")
							.append((readyBag.getSize() == null ? "" : readyBag.getSize()))
							.append("\",\"sieve\":\"")
							.append((readyBag.getSieve() == null ? "" : readyBag.getSieve()))
							.append("\",\"stone\":\"")
							.append((readyBag.getStone() == null ? "" : readyBag.getStone()))
							.append("\",\"carat\":\"")
							.append((readyBag.getCarat() == null ? "" : readyBag.getCarat()))
							.append("\",\"setting\":\"")
							.append((readyBag.getSetting() == null ? "" : readyBag.getSetting().getName()))
							.append("\",\"setType\":\"")
							.append((readyBag.getSettingType() == null ? "" : readyBag.getSettingType().getName()))
							.append("\",\"centerStone\":\"")
							.append((readyBag.getCenterStone() != null ? (readyBag.getCenterStone() ? readyBag.getCenterStone() : false) : false))
							.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/transactions/bagIssue/edit/")
							.append(readyBag.getId())
							.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
							.append("\",\"action2\":\"")
							.append("<a onClick='javascript:readyBagDelete(").append(readyBag.getId()).append(");' href='javascript:void(0);'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(readyBag.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
					}
				}
				
				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";


				return str;
	}

	@Override
	public List<ReadyBag> findByBagMtAndCurrentStockAndLocationIsNull(BagMt bagMt, Boolean currStk) {
		// TODO Auto-generated method stub
		return readyBagRepository.findByBagMtAndCurrentStockAndLocationIsNull(bagMt, currStk);
	}

	@Override
	public List<ReadyBag> findByBagMtAndCurrentStockAndLocationAndPendApprovalFlg(BagMt bagMt, Boolean currStk,
			Department department, Boolean pendApprovalFlg) {
		// TODO Auto-generated method stub
		return readyBagRepository.findByBagMtAndCurrentStockAndLocationAndPendApprovalFlg(bagMt, currStk, department, pendApprovalFlg);
	}



	

}

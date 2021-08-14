package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QTranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ITranDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ITranMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;


@Service
@Repository
@Transactional
public class TranDtService implements ITranDtService {
	
	@Autowired
	private ITranDtRepository tranDtRepository;
	
	@Autowired
	private ITranMtRepository tranMtRepository;
	
	@Autowired
	private ICastingDtService castingDtService;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void save(TranDt tranDt) {
		tranDtRepository.save(tranDt);		
	}

	@Override
	public void delete(int id) {
		tranDtRepository.delete(id);
	}

	@Override
	public TranDt findOne(int id) {
		return tranDtRepository.findOne(id);
	}
	
	@Override
	public Long count() {
		return tranDtRepository.count();
	}

	@Override
	public List<TranDt> findByTranMtAndCurrStk(TranMt tranMt, Boolean currStk) {
		return tranDtRepository.findByTranMtAndCurrStk(tranMt, currStk);
	}


	@Override
	public List<TranDt> findByBagMtAndDepartmentAndCurrStk(BagMt bagMt,
			Department department, Boolean currStk) {
		return tranDtRepository.findByBagMtAndDepartmentAndCurrStk(bagMt, department, currStk);
	}

	@Override
	public TranDt findByBagMtAndBagSrNoAndCurrStk(BagMt bagMt,
			Integer bagSrNo, Boolean currStk) {
		return tranDtRepository.findByBagMtAndBagSrNoAndCurrStk(bagMt, bagSrNo, currStk);
	}

	@Override
	public List<TranDt> findByBagMtAndCurrStk(BagMt bagMt, Boolean currStk) {
		return tranDtRepository.findByBagMtAndCurrStk(bagMt, currStk);
	}

	@Override
	public List<TranDt> findByBagMtAndCurrStkByQuery(Integer bagMt,
			Boolean currStk) {
		
		QTranDt qTranDt = QTranDt.tranDt;
		
		JPAQuery query = new JPAQuery(entityManager);
		List<TranDt> tranDtList = null;
		
		 tranDtList = query.from(qTranDt)
				 .where(qTranDt.bagMt.id.eq(bagMt).and(qTranDt.currStk.eq(currStk))).list(qTranDt);
		return tranDtList;
	}

	@Override
	public List<TranDt> findByDepartmentAndCurrStk(Department department,
			Boolean currStk) {
		
		return tranDtRepository.findByDepartmentAndCurrStk(department, currStk);
	}

	@Override
	public List<TranDt> findByDepartmentAndDeptFromAndBagMtAndCurrStk(
			Department department, Department deptFrom, BagMt bagMt,
			Boolean currStk) {
		
		return tranDtRepository.findByDepartmentAndDeptFromAndBagMtAndCurrStk(department, deptFrom, bagMt, currStk);
	}

	

	
	
	@Override
	public List<Tuple> getStoneProductionTranDtList(Department department, BagMt bagMt,Boolean currStk) {
		
		QTranDt qTranDt = QTranDt.tranDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Tuple> results = query.from(qTranDt).where(qTranDt.department.eq(department).and(qTranDt.bagMt.eq(bagMt))
				.and(qTranDt.currStk.eq(true))).groupBy(qTranDt.stoneType,qTranDt.shape,qTranDt.sizeGroup,qTranDt.setting,
						qTranDt.settingType,qTranDt.quality)
				.list(qTranDt.id,qTranDt.stoneType.name,qTranDt.shape.name,qTranDt.quality.name,qTranDt.sizeGroup.name,
				qTranDt.stone.sum(),qTranDt.carat.sum(),qTranDt.setting.name,qTranDt.settingType.name,qTranDt.tranMt.id,qTranDt.setting.id,qTranDt.settingType.id);


		
		return results;
	}  
	
	
	@Override
	public List<TranDt> findByBagMt(BagMt bagMt) {
		return tranDtRepository.findByBagMt(bagMt);
	}
	
	@Override
	public void saveMultiObject(TranMt tranMtOld, List<TranDt> tranDtOld,
			TranMt tranMtNew, List<TranDt> tranDtNew,CastingDt castingDt) {
		
		tranMtRepository.save(tranMtOld);
		tranDtRepository.save(tranDtOld);
		
		tranMtRepository.delete(tranMtNew);
		tranDtRepository.delete(tranDtNew);
		
		castingDtService.save(castingDt);
		
	}

	@Override
	public List<TranDt> findByTranMtAndCurrStkAndPartNm(
			TranMt tranMt, Boolean currStk,
			LookUpMast partNm) {
		// TODO Auto-generated method stub
		return tranDtRepository.findByTranMtAndCurrStkAndPartNm(tranMt, currStk, partNm);
	}

	@Override
	public TranDt findByRefTranTypeAndRefTranDtIdAndCurrStk(String refTranType, Integer refTranDtId,Boolean currStk) {
		// TODO Auto-generated method stub
		return tranDtRepository.findByRefTranTypeAndRefTranDtIdAndCurrStk(refTranType, refTranDtId,currStk);
	}
	

}

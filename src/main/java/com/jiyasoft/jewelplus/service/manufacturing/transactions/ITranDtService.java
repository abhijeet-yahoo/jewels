package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.mysema.query.Tuple;

public interface ITranDtService {
	
	public void save(TranDt tranDt);
	
	public void delete(int id);
	
	public TranDt findOne(int id);
	
	public Long count();
	
	public List<TranDt> findByBagMt(BagMt bagMt);
	
	public List<TranDt> findByTranMtAndCurrStk(TranMt tranMt, Boolean currStk);
			
	public List<TranDt> findByBagMtAndDepartmentAndCurrStk(BagMt bagMt,Department department, Boolean currStk);
	
	public TranDt findByBagMtAndBagSrNoAndCurrStk(BagMt bagMt,Integer bagSrNo, Boolean currStk);
	
	public List<TranDt> findByBagMtAndCurrStk(BagMt bagMt, Boolean currStk);
	
	public List<TranDt> findByBagMtAndCurrStkByQuery(Integer bagMt, Boolean currStk);
	
	public List<TranDt> findByDepartmentAndCurrStk(Department department, Boolean currStk);
	
	public List<TranDt> findByDepartmentAndDeptFromAndBagMtAndCurrStk(Department department,Department deptFrom,BagMt bagMt,Boolean currStk);

	public List<Tuple> getStoneProductionTranDtList(Department department, BagMt bagMt,Boolean currStk);
	
	public void saveMultiObject(TranMt tranMtOld,List<TranDt> tranDtOld,TranMt tranMtNew,List<TranDt> tranDtNew,CastingDt castingDt);
	
	public List<TranDt> findByTranMtAndCurrStkAndPartNm(TranMt tranMt,Boolean currStk,LookUpMast partNm);
	
	public TranDt findByRefTranTypeAndRefTranDtIdAndCurrStk(String refTranType,Integer refTranDtId,Boolean currStk);
}

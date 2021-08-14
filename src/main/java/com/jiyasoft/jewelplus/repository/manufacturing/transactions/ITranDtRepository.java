package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface ITranDtRepository extends JpaRepository<TranDt, Integer>,
		QueryDslPredicateExecutor<TranDt> {
	
	List<TranDt> findByBagMt(BagMt bagMt);
	
	List<TranDt> findByTranMtAndCurrStk(TranMt tranMt, Boolean currStk);
		
	List<TranDt> findByBagMtAndDepartmentAndCurrStk(BagMt bagMt,Department department, Boolean currStk);
	
	List<TranDt> findByBagMtAndCurrStk(BagMt bagMt, Boolean currStk);
	
	TranDt findByBagMtAndBagSrNoAndCurrStk(BagMt bagMt,Integer bagSrNo, Boolean currStk);

	List<TranDt> findByDepartmentAndCurrStk(Department department, Boolean currStk);
	
	List<TranDt> findByDepartmentAndDeptFromAndBagMtAndCurrStk(Department department,Department deptFrom,BagMt bagMt,Boolean currStk);
	
	List<TranDt> findByTranMtAndCurrStkAndPartNm(TranMt tranMt,Boolean currStk,LookUpMast partNm);
	
	TranDt findByRefTranTypeAndRefTranDtIdAndCurrStk(String refTranType,Integer refTranDtId,Boolean currStk);
	
	
}

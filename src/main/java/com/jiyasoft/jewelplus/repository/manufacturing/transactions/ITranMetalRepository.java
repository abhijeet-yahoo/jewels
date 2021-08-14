package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface ITranMetalRepository extends JpaRepository<TranMetal, Integer>, QueryDslPredicateExecutor<TranMetal> {

	public List<TranMetal>findByBagMtAndCurrStk(BagMt bagMt,Boolean currStk);
	
	public TranMetal findByBagMtIdAndPartNmIdAndCurrStk(Integer bagId,Integer partId,Boolean currStk);
	
	public TranMetal findByBagMtIdAndMainMetalAndCurrStk(Integer bagId,Boolean mainMetal,Boolean currStk);
	
	
	public List<TranMetal> findByTranMtAndCurrStk(TranMt tranMt, Boolean currStk);
	
	
	public List<TranMetal> findByDepartmentAndCurrStkAndPurityAndColor(Department department,Boolean currStk,Purity purity,Color color);
	
	public List<TranMetal> findByBagMtAndDepartmentAndCurrStk(BagMt bagMt ,Department department,Boolean currStk);
	
	public List<TranMetal> findByBagMtIdAndPartNmId(Integer bagId,Integer partId);
	
	
	TranMetal findByRefTranMetalIdAndCurrStk(Integer refTranMetalId,Boolean currStk);
	
	
	
}

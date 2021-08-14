package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpStoneProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface IEmpStoneProductionRepository extends
		JpaRepository<EmpStoneProduction, Integer>,
		QueryDslPredicateExecutor<EmpStoneProduction> {
	
	
	List<EmpStoneProduction> findByDepartmentAndBagMtAndDeactive(Department department,BagMt bagMt,Boolean deactive);
	
	List<EmpStoneProduction>findByTranMtAndDeactive(TranMt tranMt,Boolean deactive);
}

package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPcsProduction;

public interface IEmpPcsProductionRepository extends
		JpaRepository<EmpPcsProduction, Integer>,
		QueryDslPredicateExecutor<EmpPcsProduction> {
	
	List<EmpPcsProduction> findByDepartmentAndBagMtAndDeactive(Department department,BagMt bagMt,Boolean deactive);

}

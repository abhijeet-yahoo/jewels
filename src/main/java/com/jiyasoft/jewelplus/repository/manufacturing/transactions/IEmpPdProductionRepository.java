package com.jiyasoft.jewelplus.repository.manufacturing.transactions;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPdProduction;

public interface IEmpPdProductionRepository extends JpaRepository<EmpPdProduction, Integer>,QueryDslPredicateExecutor<EmpPdProduction>{
	
	List<EmpPdProduction> findByDepartmentAndDesignAndDeactive(Department department,Design design,Boolean deactive);

}
 
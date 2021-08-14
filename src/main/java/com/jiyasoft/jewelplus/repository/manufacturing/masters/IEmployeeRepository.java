package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer>, QueryDslPredicateExecutor<Employee>{
	
	Employee findByName(String name);
	
	Page<Employee> findByDepartmentAndDeactive(Department department,Boolean deactive, Pageable pageable);
	
	Employee findByNameAndDepartmentAndDeactive(String name, Department department,Boolean deactive);
	
	
	List<Employee> findByLookUpMastAndDeactive(LookUpMast lookUpMast,Boolean deactive);


}

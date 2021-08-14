package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.UserDeptTrfRight;

public interface IUserDeptTrfRightRepository extends
		JpaRepository<UserDeptTrfRight, Integer>,
		QueryDslPredicateExecutor<UserDeptTrfRight> {

	//UserDeptTrfRight findByName();
	
	List<UserDeptTrfRight> findByUserAndDepartmentAndDeactive(User user , Department department, Boolean deactive);
	
	List <UserDeptTrfRight> findByUserAndDeactive(User user, Boolean deactive);
	
	Set<UserDeptTrfRight> findByUserAndDepartment(User user , Department department);

}

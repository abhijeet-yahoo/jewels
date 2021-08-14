package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;

public interface ILocationRightsRepository extends JpaRepository<LocationRights, Integer>, QueryDslPredicateExecutor<LocationRights> {

	LocationRights findByUserAndDepartmentAndDeactive(User user , Department department, Boolean deactive);
	
	List <LocationRights> findByUser(User user);
	
	Set<LocationRights> findByUserAndDepartment(User user , Department department);
	
	List<LocationRights> findByUserAndDeactive(User user, Boolean deactive);
	
	LocationRights findByUserAndDeactiveAndDefaultFlg(User user, Boolean deactive,Boolean defaultFlg); 

}

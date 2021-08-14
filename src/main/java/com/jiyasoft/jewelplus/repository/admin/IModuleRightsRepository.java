package com.jiyasoft.jewelplus.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.ModuleRights;
import com.jiyasoft.jewelplus.domain.admin.User;

public interface IModuleRightsRepository extends
		JpaRepository<ModuleRights, Integer>,
		QueryDslPredicateExecutor<ModuleRights> {
	
	ModuleRights findByUserAndModuleName(User user,String moduleName);

}

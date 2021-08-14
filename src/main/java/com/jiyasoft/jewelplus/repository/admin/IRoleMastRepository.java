package com.jiyasoft.jewelplus.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.RoleMast;

public interface IRoleMastRepository extends JpaRepository<RoleMast, Integer>, QueryDslPredicateExecutor<RoleMast>{
	
	RoleMast findByRoleNm(String roleNm);

}

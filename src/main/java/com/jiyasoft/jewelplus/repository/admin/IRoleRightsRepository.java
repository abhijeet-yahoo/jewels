package com.jiyasoft.jewelplus.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;

public interface IRoleRightsRepository extends JpaRepository<RoleRights, Integer>,QueryDslPredicateExecutor<RoleRights>{

    List<RoleRights> findByRoleMastAndDeactive(RoleMast roleMast,Boolean deactive);
	
	RoleRights findByRoleMastAndMenuMast(RoleMast roleMast,MenuMast menuMast);
}

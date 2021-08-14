package com.jiyasoft.jewelplus.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.RoleMast;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer>,QueryDslPredicateExecutor<Integer> {
	
	UserRole findByUserAndRoleMast(User user,RoleMast roleMast);
	
	UserRole findByUser(User user);

}

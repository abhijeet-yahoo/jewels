package com.jiyasoft.jewelplus.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserLoginSecurity;

public interface IUserLoginSecurityRepository extends
		JpaRepository<UserLoginSecurity, Integer>,
		QueryDslPredicateExecutor<UserLoginSecurity> {

	
	List<UserLoginSecurity> findByUserAndDeactive(User user,Boolean deactive);
	
	UserLoginSecurity findByUserAndQuestionAndDeactive(User user,String question,Boolean deactive);
	
}

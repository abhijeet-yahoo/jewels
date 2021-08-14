package com.jiyasoft.jewelplus.repository.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.User;

public interface IUserRepository extends JpaRepository<User, Integer>, QueryDslPredicateExecutor<User> {

	Page<User> findAll(Pageable pageable);

	User findByName(String userName);

	User findByIdAndName(Integer id, String userName);
	
	

}

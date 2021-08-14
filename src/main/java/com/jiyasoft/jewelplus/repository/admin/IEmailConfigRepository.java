package com.jiyasoft.jewelplus.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.admin.EmailConfig;


public interface IEmailConfigRepository extends JpaRepository<EmailConfig, Integer>, QueryDslPredicateExecutor<EmailConfig>{

	EmailConfig findByName(String userName);

	EmailConfig findByIdAndName(Integer id, String userName);
}

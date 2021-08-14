package com.jiyasoft.jewelplus.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.IPAddressAccess;

public interface IIPAddressAccessRepository extends
		JpaRepository<IPAddressAccess, Integer>,
		QueryDslPredicateExecutor<IPAddressAccess> {

}

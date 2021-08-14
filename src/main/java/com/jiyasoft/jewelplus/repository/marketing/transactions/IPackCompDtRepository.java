package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;

public interface IPackCompDtRepository extends JpaRepository<PackCompDt,Integer>,
QueryDslPredicateExecutor<PackCompDt>{

	List<PackCompDt>findByPackDt(PackDt packDt);
}

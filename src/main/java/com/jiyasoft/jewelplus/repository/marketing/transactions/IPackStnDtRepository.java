package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;

public interface IPackStnDtRepository extends JpaRepository<PackStnDt,Integer>,
QueryDslPredicateExecutor<PackStnDt>{

	List<PackStnDt>findByPackDt(PackDt packDt);
	List<PackStnDt>findByPackMt(PackMt packMt);
	
}

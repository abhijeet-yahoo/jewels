package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;

public interface IPackMetalDtRepository extends JpaRepository<PackMetalDt, Integer>,
QueryDslPredicateExecutor<PackMetalDt>{

	List<PackMetalDt>findByPackDt(PackDt packDt);
	
	PackMetalDt findByPackDtAndPartNm(PackDt packDt,LookUpMast lookUpMast);
	
	PackMetalDt findByPackDtAndMainMetal(PackDt packDt,Boolean mainMetal);
	
}

package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;

public interface IPackDtRepository extends JpaRepository<PackDt, Integer>,QueryDslPredicateExecutor<PackDt> {
	
	List<PackDt> findByPackMt(PackMt packMt);
	
	PackDt findByPackMtAndBarcode(PackMt packMt,String barcode);
	

}

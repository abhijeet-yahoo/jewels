package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.MarketTypeMast;

public interface IMarketTypeRepository extends JpaRepository<MarketTypeMast, Integer>,QueryDslPredicateExecutor<MarketTypeMast>{

	MarketTypeMast findByNameAndDeactive(String name, Boolean deavtice);

	MarketTypeMast findByCodeAndDeactive(String code, Boolean deactive);
	
	List<MarketTypeMast> findAllByOrderByNameAsc(); 
}

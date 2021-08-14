package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

public interface IHandlingMasterFLRepository extends JpaRepository<HandlingMasterFl, Integer>, QueryDslPredicateExecutor<HandlingMasterFl> {

	List<HandlingMasterFl> findByPartyAndFromDiaRateAndDeactive(Party party, Double fromDiaRate, Boolean deactive);	
	
	
}

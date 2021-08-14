package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStyleLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

public interface IClientStyleLabRepository extends
		JpaRepository<ClientStyleLabDt, Integer>,
		QueryDslPredicateExecutor<ClientStyleLabDt> {

	
	ClientStyleLabDt findByRate(Double rate);
	
	List<ClientStyleLabDt> findByDesignAndParty(Design design,Party party);
	List<ClientStyleLabDt> findByPartyAndDesignAndLabourType(Party party,Design design,LabourType labourType);
	
	List<ClientStyleLabDt> findByPartyAndDesignAndMetal(Party party,Design design,Metal metal);
}

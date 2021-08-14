package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientReference;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

public interface IClientRefRepository extends JpaRepository<ClientReference, Integer>,QueryDslPredicateExecutor<ClientReference> {
	
	ClientReference findByPartyAndDesignAndPurityAndDeactive(Party party,Design design,Purity purity,Boolean deactive);
	
	

}

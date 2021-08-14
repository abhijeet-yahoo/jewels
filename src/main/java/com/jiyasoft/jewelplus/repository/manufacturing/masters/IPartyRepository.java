package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

public interface IPartyRepository extends JpaRepository<Party, Integer>,
		QueryDslPredicateExecutor<Party> {

	Party findByName(String name);
	
	Party findByPartyCodeAndDeactive(String partyCode, Boolean deactive);
	
	Party findByDefaultFlag(Boolean defaultFlag);
	
	List<Party> findAByExportClientOrderByPartyCodeAsc(Boolean exportClient);
	
	List<Party> findByDeactiveOrderByPartyCodeAsc(Boolean deactive);
	
}

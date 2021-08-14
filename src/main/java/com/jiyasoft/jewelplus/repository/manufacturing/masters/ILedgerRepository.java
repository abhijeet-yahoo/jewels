package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;



public interface ILedgerRepository extends JpaRepository<Party, Integer>,QueryDslPredicateExecutor<Party> {
	
	Party findByNameAndDeactive(String name, Boolean deactive);
	
	Party findByPartyCodeAndDeactive(String code, Boolean deactive);
	
	Party findByMailingNameAndDeactive(String mailingNm,Boolean deactive);
	
	
	List<Party> findDistinctByCityStartingWith(String cityNm);
}

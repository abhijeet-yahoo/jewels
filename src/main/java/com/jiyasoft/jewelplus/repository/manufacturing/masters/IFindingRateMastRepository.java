package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;


public interface IFindingRateMastRepository extends JpaRepository<FindingRateMast, Integer>, QueryDslPredicateExecutor<FindingRateMast>{

	
	FindingRateMast findByComponentAndPartyAndPurityAndDeactive(Component component, Party party, Purity purity, Boolean deactive);
	
	FindingRateMast findByPartyAndComponentAndPurityAndPerGramRateAndPerPcRateAndDeactive(Party party, Component component, Purity purity, Double perGramRate, Double perPcRate, Boolean deactive);

	FindingRateMast findByPartyAndComponentAndPurityAndDeactive(Party party, Component component, Purity purity, Boolean deactive);
	
}

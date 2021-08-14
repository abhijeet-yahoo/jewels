package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientKtConvMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

public interface IClientKtConvRepository extends JpaRepository<ClientKtConvMast, Integer>, QueryDslPredicateExecutor<ClientKtConvMast>{
	
	ClientKtConvMast findById(Integer id);
	
	ClientKtConvMast findByPartyAndPurityAndDeactive(Party party,Purity purity, Boolean deactive);

	

}

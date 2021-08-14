package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

public interface IClientStampRepository extends JpaRepository<ClientStamp, Integer>, QueryDslPredicateExecutor<ClientStamp>{
	
List<ClientStamp> findByPartyAndFromCtsAndDeactive(Party party, Double fromCts, Boolean deactive);	

ClientStamp findByPartyAndPurityAndDeactive(Party party, Purity purity, Boolean deactive);

ClientStamp findByStampNmAndDeactive(String name, Boolean deactive);

}

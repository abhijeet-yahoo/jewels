package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Address;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;



public interface IAddressRepository extends JpaRepository<Address, Integer>, QueryDslPredicateExecutor<Address> {

	public List<Address> findByParty(Party party);

}

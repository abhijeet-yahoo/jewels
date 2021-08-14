package com.jiyasoft.jewelplus.repository.manufacturing.masters;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.CountryMaster;


public interface ICountryMasterRepository extends JpaRepository<CountryMaster, Integer>, QueryDslPredicateExecutor<CountryMaster> {

	List<CountryMaster> findByName(String name);

	CountryMaster findByNameAndDeactive(String name,Boolean deactive);
	
}

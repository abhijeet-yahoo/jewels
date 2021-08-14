package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;

public interface ISettingTypeRepository extends
		JpaRepository<SettingType, Integer>,
		QueryDslPredicateExecutor<SettingType> {
	
	SettingType findByName(String name);

}

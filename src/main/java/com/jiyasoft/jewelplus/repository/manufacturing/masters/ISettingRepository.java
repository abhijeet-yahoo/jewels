package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;

public interface ISettingRepository extends JpaRepository<Setting, Integer>,
		QueryDslPredicateExecutor<Setting> {
	Setting findByName(String name);
}

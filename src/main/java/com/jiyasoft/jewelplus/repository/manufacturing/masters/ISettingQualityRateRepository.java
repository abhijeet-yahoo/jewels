package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;

public interface ISettingQualityRateRepository extends
		JpaRepository<SettingQualityRate, Integer>,
		QueryDslPredicateExecutor<SettingQualityRate> {
	
	
	List<SettingQualityRate> findBySettingChargeAndDeactive(SettingCharge settingCharge,Boolean deactive);
	

}

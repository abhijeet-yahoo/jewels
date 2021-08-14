package com.jiyasoft.jewelplus.repository.manufacturing.masters;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;



public interface ISettingChargeRepository extends JpaRepository<SettingCharge, Integer>, QueryDslPredicateExecutor<SettingCharge>{
	
	SettingCharge findByRate(Double rate);

	Page<SettingCharge> findByShape(Shape shape, Pageable pageable);
	
	SettingCharge findByStoneTypeAndShapeAndPartyAndMetalAndSettingAndSettingTypeAndFromWeightAndToWeightAndDeactive(
			StoneType stoneType, Shape shape, Party party,Metal metal, Setting setting,
			SettingType settingType,Double fromWeight, Double toWeight, Boolean deactive);
	
	List<SettingCharge> findByShapeAndDeactive(Shape shape,Boolean deative);
	
	Page<SettingCharge> findByShapeAndDeactive(Shape shape,Boolean deactive, Pageable pageable);
	
	SettingCharge findByUniqueId(Long uniqueId);
	
}

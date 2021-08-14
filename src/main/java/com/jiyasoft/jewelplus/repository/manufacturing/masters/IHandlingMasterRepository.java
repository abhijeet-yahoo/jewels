package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;


public interface IHandlingMasterRepository extends JpaRepository<HandlingMaster, Integer>, QueryDslPredicateExecutor<HandlingMaster>{
	
	HandlingMaster findByPartyAndMetalAndStoneTypeAndShapeAndDeactive(Party party,Metal metal, StoneType stoneType, Shape shape, Boolean deactive);
}

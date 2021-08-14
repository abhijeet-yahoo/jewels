package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;

public interface IStoneRateMastRepository extends JpaRepository<StoneRateMast, Integer>,
QueryDslPredicateExecutor<StoneRateMast>{
	
	
	List<StoneRateMast> findByStoneTypeAndDeactive(StoneType stoneType,Boolean deactive);
	
	List<StoneRateMast> findByStoneTypeAndShapeAndDeactive(StoneType stoneType,Shape shape,Boolean deactive);
	
	StoneRateMast findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveAndPartyAndSieve(StoneType stoneType,Shape shape,Quality quality,SizeGroup sizeGroup,Boolean deactive,Party party,String sieve);
	
	List<StoneRateMast> findByDeactive(Boolean deactive);
	
	StoneRateMast findByStoneTypeAndShapeAndQualityAndPartyAndFromWeightAndToWeightAndDeactive(StoneType stoneType,Shape shape,Quality quality,Party party, Double fromWeight,Double toWeight, Boolean deactive);

}

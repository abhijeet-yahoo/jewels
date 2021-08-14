package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnAdj;

public interface IVAddStnAdjRepositoty extends JpaRepository<VAddStnAdj, Integer>, QueryDslPredicateExecutor<VAddStnAdj> {
	
	/*
	 * List<VAddStnAdj>
	 * findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndStnRateAndSizeGroup(
	 * CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,String
	 * size,Double stnRate,SizeGroup sizeGroup);
	 * 
	 * List<VAddStnAdj>
	 * findByCostingMtAndStoneTypeAndShapeAndQualityAndStnRateAndSizeGroup(CostingMt
	 * costingMt,StoneType stoneType,Shape shape,Quality quality,Double
	 * stnRate,SizeGroup sizeGroup);
	 * 
	 * List<VAddStnAdj>
	 * findByCostingMtAndStoneTypeAndShapeAndQualityAndStnRate(CostingMt
	 * costingMt,StoneType stoneType,Shape shape,Quality quality,Double stnRate);
	 */	
	List<VAddStnAdj> findByCostingMtAndStoneTypeAndShape(CostingMt costingMt,StoneType stoneType,Shape shape);
	
	VAddStnAdj findByStonePurchaseDt(StonePurchaseDt stonePurchaseDt);
	
	List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQualityAndSize(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,
			String size);

	List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroup(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,SizeGroup sizeGroup);
	
	List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQuality(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality);
	
	List<VAddStnAdj>findByCostingMt(CostingMt costingMt);
}

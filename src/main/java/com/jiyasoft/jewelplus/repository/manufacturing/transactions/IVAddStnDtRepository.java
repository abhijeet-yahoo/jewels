package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;

public interface IVAddStnDtRepository extends JpaRepository<VAddStnDt, Integer>,QueryDslPredicateExecutor<VAddStnDt> {
	
	List<VAddStnDt> findByVAddDtAndDeactive(VAddDt VAddDt, Boolean deactive);
	
	List<VAddStnDt> findByCostingMtAndDeactive(CostingMt costingMt, Boolean deactive);
	
	
	
	
	List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndDeactive(CostingMt costingMt,StoneType stoneType,Shape shape, Quality quality,Boolean deactive);
	
	List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,SizeGroup sizeGroup,Boolean deactive);
	
	List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndDeactive(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,String size,Boolean deactive);
	
	List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndDeactive(CostingMt costingMt,StoneType stoneType,Shape shape,Boolean deactive);
	
	List<VAddStnDt> findByCostingMtAndShapeAndQualityAndDeactive(CostingMt costingMt,Shape shape, Quality quality,Boolean deactive);
}

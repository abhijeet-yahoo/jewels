package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;

public interface IStoneInwardDtRepository extends
		JpaRepository<StoneInwardDt, Integer>,
		QueryDslPredicateExecutor<StoneInwardDt> {

	List<StoneInwardDt> findByStoneInwardMt(StoneInwardMt stoneInwardMt);

	Page<StoneInwardDt> findByStoneInwardMt(StoneInwardMt stoneInwardMt,Pageable pageable);

	StoneInwardDt findByUniqueId(Long uniqueId);

	List<StoneInwardDt> findByStoneInwardMtAndDeactiveOrderByIdDesc(StoneInwardMt stoneInwardMt, Boolean deactive);

	public List<StoneInwardDt> findByStoneTypeAndShapeAndDeactive(StoneType stoneType,Shape shape,Boolean Deactive);

	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndDeactive(StoneType stoneType, Shape shape, Quality quality, String size,Boolean deactive);

	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(StoneType stoneType, Shape shape, Quality quality, SizeGroup sizeGroup,Boolean deactive);
	
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndDeactiveOrderByIdAsc(StoneType stoneType, Shape shape, Quality quality,Boolean Deactive);
	
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndSizeGroupAndDeactive(StoneType stoneType, Shape shape, Quality quality, String size,SizeGroup sizeGroup,Boolean deactive);
	
	
	
	//---all new method for importButton
	
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndDeactiveOrderByRateDesc(StoneType stoneType, Shape shape, Quality quality, String size,Boolean deactive);

	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveOrderByRateDesc(StoneType stoneType, Shape shape, Quality quality, SizeGroup sizeGroup,Boolean deactive);
	
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndDeactiveOrderByRateDesc(StoneType stoneType, Shape shape, Quality quality,Boolean deactive);
	
	
	
	
	

}

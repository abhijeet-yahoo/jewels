package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.mysema.query.types.Predicate;


public interface IStoneRateMastService {

	
	public List<StoneRateMast> findAll();

	public Page<StoneRateMast> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(StoneRateMast stoneRateMast);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public StoneRateMast findOne(int id);
	
	public List<StoneRateMast> findByStoneTypeAndDeactive(StoneType stoneType,Boolean deactive);
	
	public List<StoneRateMast> findByStoneTypeAndShapeAndDeactive(StoneType stoneType,Shape shape,Boolean deactive);
	
	public StoneRateMast findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveAndPartyAndSieve(StoneType stoneType,Shape shape,Quality quality,SizeGroup sizeGroup,Boolean deactive,Party party,String sieve);
	
	public List<StoneRateMast> findByDeactive(Boolean deactive);
	
	public String updateStoneRateMast(Integer stoneTypeId);
	
	public List<StoneRateMast> getStoneRate(Integer stoneTypeId,Integer shapeId, Integer qualityId, Integer sizeGroupId,Integer partyId,String sieve);
	
	public List<StoneRateMast> getStoneRateForLaxmi(Integer stoneTypeId,Integer shapeId, Integer qualityId, Integer sizeGroupId,Integer partyId,String sieve);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Page<StoneRateMast> findBySearchAndDeactive(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public 	StoneRateMast findByStoneTypeAndShapeAndQualityAndPartyAndFromWeightAndToWeightAndDeactive(StoneType stoneType,Shape shape,Quality quality,Party party, Double fromWeight,Double toWeight, Boolean deactive);
	
	//public List<StoneRateMast> getBetweenStoneRate(StoneType stoneType,Shape shape,Quality quality,Party party, Double caratWt, Boolean deactive);
	
	public List<StoneRateMast> getBetweenStoneRate(StoneType stoneType,Shape shape,Quality quality,Party party, Double caratWt, Boolean deactive);
	
	
 

	//----------custom search method ---//
	
		public Page<StoneRateMast> customSearch( Integer limit, Integer offset,
					String sort, String order, String partyCode,String stoneTypeNm,
					String shapeNm,String qualityNm,String sizeGroupNm);
		
		public Long customSearchCount(String partyCode,String stoneTypeNm,
				String shapeNm,String qualityNm,String sizeGroupNm);
	
}

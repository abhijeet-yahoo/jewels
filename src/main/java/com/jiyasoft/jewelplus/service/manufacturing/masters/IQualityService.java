package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.mysema.query.types.Predicate;

public interface IQualityService {
	public List<Quality> findAll();

	public Page<Quality> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Quality quality);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public Quality findOne(int id);

	//public Quality findByName(String qualityName);
	
	public Quality findByShapeAndNameAndDeactive(Shape shape, String name,Boolean deactive);
	
	public Quality findByShapeAndName(Shape shape, String name);

	public Page<Quality> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String search);

	public Predicate countByShape(Integer id);
	
	public Map<Integer, String> getQualityList(Integer shapeId);

	public String getQualityListDropDown(Integer shapeId);

	public Map<Integer, String> getQualityList();

	public List<Quality> findActiveQualities(Integer shapeId);

	public Page<Quality> findActiveQualitiesSortByName(Integer shapeId);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Quality> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public List<Quality> findByDeactiveOrderByNameAsc(Boolean deactive);
	
	public List<Quality> findByShapeList(String shapeId);
	
	public String getQualityListDropDownForQuot(Integer shapeId);
	
	// new method for search and pagination
	public Page<Quality> findByShape(Shape shape, Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Long countByShape(Shape shape, String colName, String colValue, Boolean onlyActive);
	
	public List<Quality> findByIntQuality(String intQuality);
	
	public Page<Quality> searchAll(Integer limit, Integer offset,
			String sort, String order, String name,Integer shapeId, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	
	
}

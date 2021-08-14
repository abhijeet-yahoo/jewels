	package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.mysema.query.types.Predicate;

public interface IStoneChartService {

	public List<StoneChart> findAll();

	public Page<StoneChart> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(StoneChart stoneChart);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public StoneChart findOne(int id);

	public Page<StoneChart> findByShapeAndDeactive(Shape shape,Boolean deactive, Integer limit,
			Integer offset, String sort, String order, String search);

	public Predicate countByShape(Integer id);

	public Map<String, String> getStoneChartList(Integer shpId);

	public String getStoneChartListDropDown(Integer shpId);
	
	public String getStoneChartListDropDownForDc(Integer shpId);

	public List<StoneChart> findActiveStoneChart(Integer shpId);

	public Page<StoneChart> findActiveStoneChartSortBySize(Integer shpId);
	
	// Diamond Changing Diamond Stock type
	public Page<StoneChart> findActiveSize(Integer sizeGroupId);
	
	public Map<String, String> getStoneChartSizeList(Integer sizeGroupId);

	public String getStoneChartSizeListDropDown(Integer sizeGroupId);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<StoneChart> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	public StoneChart findByShapeAndSizeAndDeactive(Shape shape, String size,Boolean deactive);
	
	public List<StoneChart> findByShape(Shape shape);
	
	public List<StoneChart> findBySizeGroupAndDeactive(SizeGroup sizeGroup,Boolean deactive);
	
	public Page<StoneChart> searchAll(Integer limit, Integer offset, String sort,
			String order, String search,Integer shapeId, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);

	public List<StoneChart> findBySizeGroupAndSizeAndDeactive(SizeGroup sizeGroup,String size,Boolean deactive);
	

	public Page<StoneChart> findActiveSieve(Integer sizeGroupId);

	public Map<String, String> getStoneChartSieveList(Integer sizeGroupId);
	
	public StoneChart findByShapeAndSieveAndDeactive(Shape shape, String sieve,Boolean deactive);
	
	
	public Page<StoneChart> findActiveSieveListForConv(Integer shapeId);

	public Map<String, String> getStoneChartSieveListForConv(Integer shapeId);
	
	public String getStoneChartSieveListDropDown(Integer shpId);
	
	public StoneChart findByShapeAndSizeAndSieveAndDeactive(Shape shape, String size,String sieve,Boolean deactive);
	
	public StoneChart findByShapeAndSizeAndSieveAndSizeGroupAndDeactive(Shape shape, String size,String sieve,SizeGroup sizeGroup,Boolean deactive);
	
	
}

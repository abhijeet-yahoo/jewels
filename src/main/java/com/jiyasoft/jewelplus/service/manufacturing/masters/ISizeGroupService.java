package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.mysema.query.types.Predicate;

public interface ISizeGroupService {

	public List<SizeGroup> findAll();

	public Page<SizeGroup> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(SizeGroup sizeGroup);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public SizeGroup findOne(int id);

	public SizeGroup findByName(String sizeGroupName);
	
	public SizeGroup findByShapeAndNameAndDeactive(Shape shape, String sizeGroupName,Boolean deactive);

	public Page<SizeGroup> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String search);

	public Predicate countByShape(Integer id);

	public Map<Integer, String> getSizeGroupList(Integer shapeId);

	public List<SizeGroup> findActiveSizeGroup(Integer shapeId);
	
	public Page<SizeGroup> findActiveSizeGroupSortByName(Integer shapeId);
	
	public String getSizeGroupListDropDown(Integer shapeId);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<SizeGroup> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Page<SizeGroup> searchAll(Integer limit, Integer offset, String sort,
			String order, String search,Integer shapeId, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public SizeGroup findByNameAndDeactive(String name, Boolean deactive);

}

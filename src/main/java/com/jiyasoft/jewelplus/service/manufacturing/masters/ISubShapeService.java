	package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;
import com.mysema.query.types.Predicate;

public interface ISubShapeService {

	public List<SubShape> findAll();

	public Page<SubShape> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(SubShape subShape);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public SubShape findOne(int id);

	public SubShape findByName(String subShapeName);

	public Page<SubShape> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String search);

	public Predicate countByShape(Integer id);

	public Map<Integer, String> getSubShapeList(Integer conId);

	public String getSubShapeListDropDown(Integer conId);

	public List<SubShape> findActiveSubShape(Integer conId);

	public Page<SubShape> findActiveSubShapesSortByName(Integer conId);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<SubShape> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public SubShape findByShapeAndName(Shape shape, String subShapeName);

	public Page<SubShape> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
}

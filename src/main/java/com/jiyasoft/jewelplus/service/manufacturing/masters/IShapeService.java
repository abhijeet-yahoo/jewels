package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;

public interface IShapeService {

	public List<Shape> findAll();

	public Page<Shape> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Shape shape);

	public void delete(int id);

	public Long count();

	public Shape findOne(int id);

	public Shape findByName(String shapeName);

	public Map<Integer, String> getShapeList();

	public List<Shape> findActiveShapes();

	public Page<Shape> findActiveShapesSortByName();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Shape> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Page<Shape> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);

}

package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;

public interface ICategoryService {

	public List<Category> findAll();

	public Page<Category> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Category category);

	public void delete(int id);

	public Long count();
	
	public Category findOne(int id);

	public Category findByName(String categoryName);

	public Map<Integer, String> getCategoryList();

	public List<Category> findActiveCategories();

	public Page<Category> findActiveCategoriesSortByName();

	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Page<Category> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);

	public Page<Category> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Category findByCategCode(String code);
	

}

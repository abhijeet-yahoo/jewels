package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;
import com.mysema.query.types.Predicate;

public interface ISubCategoryService {

	public List<SubCategory> findAll();

	public Page<SubCategory> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(SubCategory subCategory);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public SubCategory findOne(int id);

	public SubCategory findByName(String subCategoryName);

	public Page<SubCategory> findByCategory(Category category, Integer limit,
			Integer offset, String sort, String order, String search);

	public Predicate countByCategory(Integer id);
	
	public Map<Integer, String> getSubCategoryList(Integer catId);

	public String getSubCategoryListDropDown(Integer catId);

	public List<SubCategory> findActiveSubCategories(Integer catId);

	public Page<SubCategory> findActiveSubCategoriesSortByName(Integer catId);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<SubCategory> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public String getSubCategoryReportListDropDown(String catIds);
	
	public List<SubCategory> findByDeactive(Boolean deactive);
	
	public Page<SubCategory> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
}

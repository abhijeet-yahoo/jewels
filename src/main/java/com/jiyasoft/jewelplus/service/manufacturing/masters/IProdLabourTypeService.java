package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LossBook;

public interface IProdLabourTypeService {
	public List<ProdLabourType> findAll();

	public Page<ProdLabourType> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(ProdLabourType prodLabourType);

	public void delete(int id);

	public Long count();

	public ProdLabourType findOne(int id);

	public ProdLabourType findByName(String name);

	public Page<ProdLabourType> findByCategory(Category category,
			Integer limit, Integer offset, String sort, String order,
			String search);

	public Page<ProdLabourType> findByCategoryAndDepartment(Category category,
			Department department, Integer limit, Integer offset, String sort,
			String order, String search);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<ProdLabourType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Map<Integer, String> getProdLabourTypeList();

	public List<ProdLabourType> findActiveProdLabourTypes();

	public Page<ProdLabourType> findActiveProdLabourTypesSortByName();
	
	public Map<Integer, String> getProdLabourTypeList(Integer deptId, Integer categId);
	
	public List<ProdLabourType> findActiveProdLabType(Integer deptId, Integer categId);
	
	public Page<ProdLabourType> findActiveProdLabourTypesSortByName(Integer deptId, Integer categId);
	
	public String getProdLabourTypeDropDown(Integer deptId, Integer categId);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public Page<ProdLabourType> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	

	public Page<ProdLabourType> customSearch(Integer limit, Integer offset,
			String sort, String order, String departmentName, String categoryNm,
			String prodLobourName);

	public Long customSearchCount(String departmentName, String categoryNm,
			String prodLobourName);
	
	public String getLossLabourTypeDtDropDown(String bagNo,Integer departmentId);
	
}
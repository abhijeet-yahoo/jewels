package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;

public interface IMetalService {

	public List<Metal> findAll();

	public Page<Metal> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Metal metal);

	public void delete(int id);

	public Long count();

	public Metal findOne(int id);

	public Metal findByName(String name);

	public Map<Integer, String> getMetalList();

	public List<Metal> findActiveMetals();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Metal> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Page<Metal> findActiveMetalsSortByName();
	
	public Page<Metal> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	
}

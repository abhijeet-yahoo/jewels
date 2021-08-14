package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;

public interface IStoneTypeService {

	public List<StoneType> findAll();

	public Page<StoneType> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(StoneType stoneType);

	public void delete(int id);

	public Long count();

	public StoneType findOne(int id);

	public StoneType findByName(String name);
	
	public Map<Integer, String> getStoneTypeList();

	public List<StoneType> findActiveStoneTypes();

	public Page<StoneType> findActiveStoneTypesSortByName();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<StoneType> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Page<StoneType> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
}

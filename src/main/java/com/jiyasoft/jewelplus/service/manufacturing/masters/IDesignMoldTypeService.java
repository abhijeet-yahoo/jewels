package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMoldType;

public interface IDesignMoldTypeService {

	public List<DesignMoldType> findAll();
	
	public Page<DesignMoldType> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(DesignMoldType designMoldType);
	
	public void delete(int id);
	
	public Long count();
	
	public DesignMoldType findOne(int id);
	
	public DesignMoldType findByName(String name);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Page<DesignMoldType> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Map<Integer, String> getDesignMoldTypeList();
	
	public Page<DesignMoldType> findActiveDesignMoldTypeSortByName();
	
}

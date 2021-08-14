package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignGroup;


public interface IDesignGroupService {
	
	
	public List<DesignGroup> findAll();

	public Page<DesignGroup> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(DesignGroup designGroup);

	public void delete(int id);

	public Long count();
	
	public DesignGroup findOne(int id);

	public DesignGroup findByName(String designGroupName);

	public Map<Integer, String> getDesignGroupList();

	public List<DesignGroup> findActiveDesignGroups();

	public Page<DesignGroup> findActiveDesignGroupsSortByName();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<DesignGroup> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);

	public Page<DesignGroup> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	
}

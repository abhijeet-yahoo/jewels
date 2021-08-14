package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;

public interface ILabourTypeService {
public List<LabourType> findAll();
	
	public Page<LabourType> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(LabourType labtype);
	
	public void delete(int id);
	
	public Long count();
	
	public LabourType findOne(int id);
	
	public LabourType findByName(String name);
	
	public Map<Integer, String> getLabourTypeList();

	public List<LabourType> findActiveLabourTypes();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<LabourType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Page<LabourType> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public LabourType findByCodeAndDeactive(String code,Boolean deactive);


	
}

package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.InwardType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;

public interface IInwardTypeService {

	public List<InwardType> findAll();

	public Page<InwardType> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(InwardType inwardType);

	public void delete(int id);

	public Long count();

	public InwardType findOne(int id);

	public InwardType findByName(String name);

	public Map<Integer, String> getInwardTypeList();

	public List<InwardType> findActiveInwardTypes();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<InwardType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Page<InwardType> findActiveInwardTypesSortByName() ;
	
	public Page<InwardType> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);

}

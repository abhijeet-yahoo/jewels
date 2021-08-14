package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;

public interface ISettingTypeService {

	public List<SettingType> findAll();

	public Page<SettingType> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(SettingType settingType);

	public void delete(int id);

	public Long count();

	public SettingType findOne(int id);

	public SettingType findByName(String name);

	public Map<Integer, String> getSettingTypeList();

	public List<SettingType> findActiveSettingType();

	public Page<SettingType> findActiveSettingTypeSortByName();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<SettingType> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);

}

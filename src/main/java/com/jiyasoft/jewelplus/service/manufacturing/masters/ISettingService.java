package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;

public interface ISettingService {
	public List<Setting> findAll();

	public Page<Setting> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Setting setting);

	public void delete(int id);

	public Long count();

	public Setting findOne(int id);

	public Setting findByName(String name);
	
	public Map<Integer, String> getSettingList(Integer settingId);

//	public String getSettingListDropDown(Integer settingId);

	public List<Setting> findActiveSettings(Integer settingId);

	public Page<Setting> findActiveSettingsSortByName(Integer settingId);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Setting> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Page<Setting> findActiveSettingsSortByName();

	public Map<Integer, String> getSettingList();


}

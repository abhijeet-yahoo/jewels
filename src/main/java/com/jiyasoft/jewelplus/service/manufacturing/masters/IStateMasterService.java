package com.jiyasoft.jewelplus.service.manufacturing.masters;


import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.StateMaster;



public interface IStateMasterService {
	
	public String getStateListDropDown(Integer countryId);
	
	public Map<Integer, String> getStateListByCountry(Integer countryId);

	public Page<StateMaster> findActiveStateByCountry(Integer countryId);
	
	public StateMaster findOne(int id);
	
	public StateMaster findByName(String name);
	
	public Map<Integer, String> getStateNameList();
	
	public Page<StateMaster> findActiveStateSortByName();
	
	

}

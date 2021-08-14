package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.CountryMaster;


public interface ICountryService {
	
	public CountryMaster findOne(int id);
	
	public List<CountryMaster> findByName(String name);
	
	public List<CountryMaster> findActiveCountry();
	
	public Map<Integer, String> getCountryList();
	
	public Page<CountryMaster> countryAutoFillList(Integer limit, Integer offset, String sort,String order, String search, Boolean onlyActive);
	
	public CountryMaster findByNameAndDeactive(String name,Boolean deactive);
	
	public Map<Integer, String> getCountryWiseCurrencyList();
}

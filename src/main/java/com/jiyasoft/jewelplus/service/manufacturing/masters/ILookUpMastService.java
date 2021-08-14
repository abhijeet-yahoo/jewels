package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;

public interface ILookUpMastService {

	public List<LookUpMast> findAll();
	
	public void save(LookUpMast lookUpMast);
	
	public void delete(int id);
	
	public Long count();
	
	public LookUpMast findOne(int id);
	
	public Page<LookUpMast> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public List<LookUpMast> findByName(String name);
	
	public LookUpMast findByNameAndFieldValueAndDeactive(String name,String fieldValue,Boolean deactive);
	
	public Map<String, String> getTypeList();
	
	public Page<LookUpMast> getTypeLists();
	
	
	//---category combo box -----//
	
	public Map<Integer,String> getActiveLookUpMastByType(String Type);
	public Page<LookUpMast> findLookUpMastByType(String Type);
	
	public LookUpMast findByFieldValueAndDeactive(String fieldValue,Boolean deactive);
	
	public LookUpMast findByNameAndCodeAndDeactive(String name,String code,Boolean deactive);

	
}

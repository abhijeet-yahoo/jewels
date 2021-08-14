package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookMast;

public interface ILookMastService {
	
	public Page<LookMast> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);
	
	public LookMast findOne(int id);
	
	public void save(LookMast lookmast);
	
	public void delete(int id);
	
	public LookMast findByName(String lookmastName);
	
	public Map<Integer, String> getLookList();
	
	public Page<LookMast> findActiveLookSortByName();

}

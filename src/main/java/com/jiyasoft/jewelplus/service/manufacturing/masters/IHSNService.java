package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HSNMast;

public interface IHSNService {

	public void save(HSNMast hSNMast);
	
	public void delete(int id);
	
	public HSNMast findOne(int id);
	
	public List<HSNMast> findAll();
	
	public Page<HSNMast> searchAll (Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);
	
	public String hSNListing (Integer limit, Integer offset,
			String sort, String order, String search, Principal principal, Boolean onlyActive);
	
	public String addHSNMaster(Model model,Principal principal) ;
	
	
	public HSNMast findByCodeAndDeactive(String code, Boolean deactive);
	
	public List<HSNMast> findActiveHsn();
	
	public Map<Integer, String> getHsnList();
	
	public String getapplyGst(Integer hsnId); 
	
}

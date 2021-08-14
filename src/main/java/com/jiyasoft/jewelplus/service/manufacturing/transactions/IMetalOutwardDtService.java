package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardMt;

public interface IMetalOutwardDtService {

	public List<MetalOutwardDt> findAll();

	public Page<MetalOutwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(MetalOutwardDt metalOutwardDt);

	public void delete(int id);

	public Long count();

	public MetalOutwardDt findOne(int id);

	public Map<Integer, String> getMetalOutwardDtList();

	public Page<MetalOutwardDt> findByMetalOutwardMt(
			MetalOutwardMt metalOutwardMt, Integer limit, Integer offset,
			String sort, String order, String search);
	
	public MetalOutwardDt findByUniqueId(Long uniqueId);
	
	public List<MetalOutwardDt> findByMetalOutwardMt(MetalOutwardMt metalOutwardMt);
	
	public List<MetalOutwardDt> findByMetalOutwardMtAndDeactive(MetalOutwardMt metalOutwardMt, Boolean deactive);
	
	public String metalOutwardSave(MetalOutwardDt metalOutwardDt,Integer id,String pInvNo,Double metalWt,Double prevMetalWt, Principal principal);
	
	public String metalOutDelete(Integer id, Principal principal);
	

}

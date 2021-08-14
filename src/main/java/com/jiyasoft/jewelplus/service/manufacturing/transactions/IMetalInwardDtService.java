package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;

public interface IMetalInwardDtService {
	public List<MetalInwardDt> findAll();

	public Page<MetalInwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(MetalInwardDt metalInwardDt);

	public void delete(int id);

	public Long count();

	public MetalInwardDt findOne(int id);

	public MetalInwardDt findByUniqueId(Long uniqueId);

	public List<MetalInwardDt> findByMetalInwardMt(MetalInwardMt metalInwardMt);

	public Map<Integer, String> getMetalInwardDtList();
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<MetalInwardDt> findByMetalInwardMt(MetalInwardMt metalInwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search);
	
	public 	MetalInwardDt findByMetalInwardMtAndMetal(MetalInwardMt metalInwardMt,Metal metal); 
	
	public List<MetalInwardDt> findByMetalInwardMtAndDeactive(MetalInwardMt metalInwardMt, Boolean deactive); 
	
	public List<MetalInwardDt> findByMetalAndDeactive(Metal metal,Boolean deactive);
	
	public String metalInwardSave(MetalInwardDt metalInwardDt,Integer id,String pInvNo,Integer purityyId,Double invWtt,Principal principal);
	
	public String metalInwardDtDelete(Integer id, Principal principal);
	
	public Page<MetalInwardDt> balanceInvoice(Integer limit, Integer offset,
			String sort, String order, String search,Metal metal);
	
	
}

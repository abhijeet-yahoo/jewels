package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardMt;

public interface ICompOutwardDtService {
	
	public List<CompOutwardDt> findAll();

	public Page<CompOutwardDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(CompOutwardDt compOutwardDt);

	public void delete(int id);

	public Long count();
	
	public CompOutwardDt findOne(int id);

	public Map<Integer, String> getCompOutwardDtList();
	
	public  List<CompOutwardDt> findByCompOutwardMt(CompOutwardMt compOutwardMt);
	
	public Page<CompOutwardDt> findByCompOutwardMt(CompOutwardMt compOutwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search);
	
	public CompOutwardDt findByUniqueId(Long uniqueId);
	
	public String compOutwardDtSave(CompOutwardDt compOutwardDt, Integer id,String pInvNo,Double metalWt,Double prevMetalWt,Principal principal);
	
	public  List<CompOutwardDt> findByCompOutwardMtAndDeactive(CompOutwardMt compOutwardMt, Boolean deactive);
	
	public String compOutwardDtDelete(Integer id, Principal principal);

}

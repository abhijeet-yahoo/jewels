package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;



public interface ICompInwardDtService {
	
	public List<CompInwardDt> findAll();

	public Page<CompInwardDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(CompInwardDt compInwardDt);

	public void delete(int id);

	public Long count();
	
	public CompInwardDt findOne(int id);

	public Map<Integer, String> getCompInwardDtList();
	
	public List<CompInwardDt> findByCompInwardMt(CompInwardMt compInwardMt);
	
	public Page<CompInwardDt> findByCompInwardMt(CompInwardMt compInwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search);
	
	public CompInwardDt findByUniqueId(Long uniqueId);
	
	public  List<CompInwardDt> findByComponentAndPurityAndColorAndDeactive(Component component,Purity purity,Color color,Boolean deactive);
	
	public List<CompInwardDt> findByCompInwardMtAndDeactive(CompInwardMt compInwardMt,Boolean deactive);
	
	public String compInwardDtSave( CompInwardDt compInwardDt,Integer id,String pInvNo,Integer vPurityId,Double invWtt,Principal principal);
	
	public String compInwDtDelete(Integer id, Principal principal);



}

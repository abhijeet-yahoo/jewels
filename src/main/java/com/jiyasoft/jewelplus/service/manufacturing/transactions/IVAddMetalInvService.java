package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;

public interface IVAddMetalInvService {
	
public List<VAddMetalInv> findAll();
	
	public Page<VAddMetalInv> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(VAddMetalInv vAddMetalInv);
	
	public void delete(int id);
	
	public Long count();
	
	public VAddMetalInv findOne(int id);
	
	public List<VAddMetalInv> findByCostingMt(CostingMt costingMt);
	
	public VAddMetalInv findByMetalAndAdjustedAndCostingMt(Metal metal,Boolean adjusted,CostingMt costingMt);
	
	public List<VAddMetalInv> getMetalInvStockList(Integer costMtId);
	
	public String deleteMetalAdjustment(Integer vaddMetalInvId,Principal principal);
	
	public String deleteAllMetalAdjustment(Integer mtId,Principal principal);


}

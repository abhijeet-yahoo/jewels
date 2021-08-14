package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;

public interface IVAddCompInvService {
	
	public List<VAddCompInv> findAll();
	
	public Page<VAddCompInv> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(VAddCompInv vAddCompInv);
	
	public void delete(int id);
	
	public Long count();
	
	public VAddCompInv findOne(int id);
	
	public List<VAddCompInv> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public VAddCompInv findByComponentAndPurityAndColorAndAdjustedAndDeactive(Component component,Purity purity,Color color,Boolean adjusted,Boolean deactive);
	
	public List<VAddCompInv> getCompStockList(Integer costId);

	public String deleteAllCompAdjustment(Integer mtId,Principal principal);
}

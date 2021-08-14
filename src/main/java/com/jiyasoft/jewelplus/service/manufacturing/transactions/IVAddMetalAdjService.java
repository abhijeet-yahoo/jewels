package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;

public interface IVAddMetalAdjService {

public List<VAddMetalAdj> findAll();
	
	public Page<VAddMetalAdj> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(VAddMetalAdj vAddMetalAdj);
	
	public void delete(int id);
	
	public Long count();
	
	public VAddMetalAdj findOne(int id);
	
	public List<VAddMetalAdj> findByVAddMetalInv(VAddMetalInv vAddMetalInv);
	
	public String saveMetalAdjustment(String tempMetalInwId,String adjustWt,Integer costMtIdPk,Integer vAddMetalInvPkId,Principal principal);
	
	
	public VAddMetalAdj findByCostingMtAndVAddMetalInv(CostingMt costingMt,VAddMetalInv vAddMetalInv);
	
	
	
	
}

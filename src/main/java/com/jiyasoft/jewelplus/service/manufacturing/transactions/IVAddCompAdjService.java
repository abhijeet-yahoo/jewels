package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;

public interface IVAddCompAdjService {

	
public List<VAddCompAdj> findAll();
	
	public Page<VAddCompAdj> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(VAddCompAdj vAddCompAdj);
	
	public void delete(int id);
	
	public Long count();
	
	public VAddCompAdj findOne(int id);
	
	public List<VAddCompAdj> findByVAddCompInvAndDeactive(VAddCompInv vAddCompInv,Boolean deactive);
	
	public String saveCompAdjustment(String tempCompPurcId,Double adjustWt,Integer costMtIdPk,Integer vAddCompInvPkId,Double totCompPcs,Principal principal); 
	
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface IVaddMtService {
public List<CostingMt> findAll();
	
	public Page<CostingMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(CostingMt costingMt);
	
	public void delete(int id);
	
	public Long count();
	
	public CostingMt findOne(int id);
	
	public CostingMt findByInvNo(String invNo);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<CostingMt> findByInvNo(Integer limit, Integer offset, String sort,
			String order, String invNo, Boolean onlyActive);
	
	public CostingMt findByUniqueId(Long uniqueId);
	
	public String printWord(CostingMt costingMt);
	
	public String updateLossPerc(int id,Double lossPerc);
	
	public String updateLossPercComp(int id,Double lossPerc);

}

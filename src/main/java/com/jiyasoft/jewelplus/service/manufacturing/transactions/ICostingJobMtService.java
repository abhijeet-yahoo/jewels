package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;

public interface ICostingJobMtService {
	
	public List<CostingJobMt> findAll();
	
	public Page<CostingJobMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(CostingJobMt costingJobMt);
	
	public void delete(int id);
	
	public Long count();
	
	public CostingJobMt findOne(int id);
	
	public CostingJobMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<CostingJobMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String invNo, Boolean onlyActive);
	
	public CostingJobMt findByUniqueId(Long uniqueId);
	 
	public Page<CostingJobMt> getInvNoAutoFill(Integer limit, Integer offset, String sort, String order, String invNo, Boolean onlyActive);
	
	public Map<Integer,String> getCostingList();
	
	public Page<CostingJobMt> findActiveCostingSortByInvNo();

}

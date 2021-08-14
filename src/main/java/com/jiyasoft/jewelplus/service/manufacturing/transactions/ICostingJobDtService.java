package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;


public interface ICostingJobDtService {

	

	public List<CostingJobDt> findAll();
	
	public void save(CostingJobDt costingJobDt);
	
	public void delete(int id);
	
	public Long count();
	
	public CostingJobDt findOne(int id);
	
	public CostingJobDt findByUniqueId(Long uniqueId);
	
	public List<CostingJobDt> findByCostingJobMtAndDeactive(CostingJobMt costingJobMt,Boolean deactive);
	
	public String applyRate(CostingJobDt costingJobDt, Party party); 
	
	public String updateFob(CostingJobDt costingJobDt); 
	
	public String calculateFinalPrice(String finalPrice);
	
	public List<CostingJobDt> findByItemNoAndOrderDtAndCostingJobMtAndDeactive(String itemNo,OrderDt orderDt,CostingJobMt costingJobMt,Boolean deactive);
	
	public List<CostingJobDt> getCostingJobDtList(CostingJobMt costingJobMt);
	
	public Page<CostingJobDt> findByItemNo(Integer limit, Integer offset, String sort, String order, String itemNo, Boolean onlyActive);
	
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;

public interface ICostJobCompDtService {

    public List<CostJobCompDt> findAll();
	
	public void save(CostJobCompDt costJobCompDt);
	
	public void delete(int id);
	
	public Long count();
	
	public CostJobCompDt findOne(int id);
	
	public List<CostJobCompDt> findByCostingJobMtAndDeactive(CostingJobMt costingJobMt,Boolean deactive);
	
	public List<CostJobCompDt> findByCostingJobDtAndDeactive(CostingJobDt costingJobDt,Boolean deactive);
	
	public List<CostJobCompDt> findByItemNoAndOrderDtAndCostingJobMtAndDeactive(String itemNo,OrderDt orderDt,CostingJobMt costingJobMt,Boolean deactive);
	
	public List<CostJobCompDt> getCostJobCompDtList(String itemNo,OrderDt orderDt,CostingJobMt costingJobMt);
	
}

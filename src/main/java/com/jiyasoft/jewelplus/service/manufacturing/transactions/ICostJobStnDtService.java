package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;


public interface ICostJobStnDtService {

	public List<CostJobStnDt> findAll();
	
	public void save(CostJobStnDt costJobStnDt);

	public void delete(int id);

	public Long count();

	public CostJobStnDt findOne(int id);
	
	public List<CostJobStnDt> findByCostingJobMtAndDeactive(CostingJobMt costingJobMt,Boolean deactive);
	
	public List<CostJobStnDt> findByCostingJobDtAndDeactive(CostingJobDt costingJobDt,Boolean deactive);
	
	public List<CostJobStnDt> findByItemNoAndOrderDtAndCostingJobMtAndDeactive(String itemNo,OrderDt orderDt,CostingJobMt costingJobMt,Boolean deactive);
}

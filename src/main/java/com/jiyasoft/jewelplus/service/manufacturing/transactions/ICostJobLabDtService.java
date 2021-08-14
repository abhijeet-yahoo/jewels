package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;

public interface ICostJobLabDtService {

	
    public List<CostJobLabDt> findAll();
	
	public void save(CostJobLabDt costJobLabDt);

	public void delete(int id);

	public Long count();

	public CostJobLabDt findOne(int id);
	
	public List<CostJobLabDt> findByCostingJobMtAndDeactive(CostingJobMt costingJobMt,Boolean deactive);
	
	public List<CostJobLabDt> findByCostingJobDtAndDeactive(CostingJobDt costingJobDt,Boolean deactive);
	
	public int labAsPerMaster(Integer costJobMtId);
}

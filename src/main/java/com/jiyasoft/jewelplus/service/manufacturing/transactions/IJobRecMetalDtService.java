package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecMetalDtService {
	
	public void save(JobRecMetalDt jobRecMetalDt);

	public void delete(int id);

	public JobRecMetalDt findOne(int id);
	
	public List<JobRecMetalDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	public List<JobRecMetalDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt,Boolean deactive);

	public JobRecMetalDt findByJobRecDtAndDeactiveAndMainMetal(JobRecDt jobRecDt,Boolean deactive,Boolean mainMetal);
	
}

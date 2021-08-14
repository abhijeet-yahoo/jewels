package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssMetalDtService {
	
	public void save(JobIssMetalDt jobIssMetalDt);

	public void delete(int id);

	public JobIssMetalDt findOne(int id);
	
	public List<JobIssMetalDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	public List<JobIssMetalDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt,Boolean deactive);

	public JobIssMetalDt findByJobIssDtAndDeactiveAndMainMetal(JobIssDt jobIssDt,Boolean deactive,Boolean mainMetal);
	
}

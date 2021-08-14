package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssLabDtService {

	public List<JobIssLabDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	public List<JobIssLabDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt,Boolean deactive);
	
	public List<JobIssLabDt>findByJobIssDtAndMetalAndDeactive(JobIssDt JobIssDt,Metal metal,Boolean deactive );

	public void save(JobIssLabDt jobIssLabDt);

	public void delete(int id);

	public JobIssLabDt findOne(int id);
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssCompDtService {
	
	public void save(JobIssCompDt jobIssCompDt);

	public void delete(int id);

	public JobIssCompDt findOne(int id);
	
	public List<JobIssCompDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	public List<JobIssCompDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt,Boolean deactive);

}

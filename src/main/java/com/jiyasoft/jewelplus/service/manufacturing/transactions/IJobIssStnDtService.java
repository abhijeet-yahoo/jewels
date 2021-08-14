package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;

public interface IJobIssStnDtService {
	
	public void save(JobIssStnDt jobIssStnDt);

	public void delete(int id);

	public JobIssStnDt findOne(int id);
	
	public List<JobIssStnDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	public List<JobIssStnDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt,Boolean deactive);
	

}

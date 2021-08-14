package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecCompDtService {
	
	public void save(JobRecCompDt jobRecCompDt);

	public void delete(int id);

	public JobRecCompDt findOne(int id);
	
	public List<JobRecCompDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	public List<JobRecCompDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt,Boolean deactive);
	
	public String jobRecCompDtListing(Integer dtId, String disableFlg);

}

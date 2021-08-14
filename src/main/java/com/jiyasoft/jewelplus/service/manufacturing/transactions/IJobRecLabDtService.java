package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecLabDtService {

	public List<JobRecLabDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	public List<JobRecLabDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt,Boolean deactive);
	
	public List<JobRecLabDt>findByJobRecDtAndMetalAndDeactive(JobRecDt JobRecDt,Metal metal,Boolean deactive );

	public void save(JobRecLabDt jobRecLabDt);

	public void delete(int id);

	public JobRecLabDt findOne(int id);
	
	public String jobRecLabDtListing(Integer dtId,String disableFlg);
	
	public String jobRecLabDtSave(JobRecLabDt jobRecLabDt, Integer id,Integer jobRecMtId, Integer jobRecDtId, Principal principal);
}

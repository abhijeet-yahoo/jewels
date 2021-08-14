package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnIssDt;

public interface IJobStnIssDtService {


	public List<JobStnIssDt> findByJobIssMt(JobIssMt jobIssMt);

	public List<JobStnIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive);
	
	public JobStnIssDt findByUniqueId(Long uniqueId);
	
	public JobStnIssDt findOne(Integer id);
	
	public void save(JobStnIssDt jobStnIssDt);
	
	public void delete(int id);
	
	public String saveJobStnIssDt(JobStnIssDt jobStnIssDt,Integer id,Integer mtId, Principal principal,String stockType,Boolean allowNegative);
	
	public String jobStnIssDtDelete(Integer id, Principal principal);


}

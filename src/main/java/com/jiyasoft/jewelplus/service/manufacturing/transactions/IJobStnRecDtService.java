package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnRecDt;

public interface IJobStnRecDtService {

	public List<JobStnRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive);
	
	public JobStnRecDt findByUniqueId(Long uniqueId);
	
	public void save(JobStnRecDt jobStnRecDt);
	
	public void delete(int id);
	
	public JobStnRecDt findOne(int id);
			
	public String jobStnRecDtSave(JobStnRecDt jobStnRecDt,Integer id,Integer mtId, Principal principal);
	
	public String jobStnRecDtDelete(Integer id, Principal principal);

}

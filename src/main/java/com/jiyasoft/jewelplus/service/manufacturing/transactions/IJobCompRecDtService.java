package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobCompRecDtService {
	
	public List<JobCompRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive);
	
	public JobCompRecDt findByUniqueId(Long uniqueId);
	
	public void save(JobCompRecDt jobCompRecDt);
	
	public void delete(int id);
	
	public JobCompRecDt findOne(int id);
			
	public String jobCompRecDtSave(JobCompRecDt jobCompRecDt,Integer id,Integer mtId, Principal principal);
	
	public String jobCompRecDtDelete(Integer id, Principal principal);



}

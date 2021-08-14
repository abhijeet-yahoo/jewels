package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlIssDt;
public interface IJobMtlIssDtService {

	 public List<JobMtlIssDt> findByJobIssMt(JobIssMt jobIssMt);

	 public List<JobMtlIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive);
	 
	 public JobMtlIssDt findByUniqueId(Long uniqueId);
	 
	public void save(JobMtlIssDt jobMtlIssDt);
	
	public void delete(int id);
	
	public JobMtlIssDt findOne(int id);
	
	public Map<Integer, String> getJobMtlIssDtList();
	
	public String jobMtlIssDtSave(JobMtlIssDt jobMtlIssDt,Integer id,Integer mtId, Principal principal);
	
	public String jobMtlIssDtDelete(Integer id, Principal principal);

}

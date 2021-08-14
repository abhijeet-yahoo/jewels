package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobCompIssDtService {

	public List<JobCompIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive);
	
	public JobCompIssDt findByUniqueId(Long uniqueId);
	
	public void save(JobCompIssDt jobCompIssDt);

	public void delete(int id);

	public Long count();
	
	public JobCompIssDt findOne(int id);

	public String saveJobCompIssDt(JobCompIssDt jobCompIssDt,Integer id,Integer mtId, Principal principal);
	
	public String jobCompIssDtDelete(Integer id, Principal principal);

}

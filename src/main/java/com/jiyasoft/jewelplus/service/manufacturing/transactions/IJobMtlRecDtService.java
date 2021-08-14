package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobMtlRecDtService {

	public List<JobMtlRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive);
	 
	 public JobMtlRecDt findByUniqueId(Long uniqueId);
	 
	 public void save(JobMtlRecDt jobMtlRecDt);
		
		public void delete(int id);
		
		public JobMtlRecDt findOne(int id);
				
		public String jobMtlRecDtSave(JobMtlRecDt jobMtlRecDt,Integer id,Integer mtId, Principal principal);
		
		public String jobMtlRecDtDelete(Integer id, Principal principal);

}

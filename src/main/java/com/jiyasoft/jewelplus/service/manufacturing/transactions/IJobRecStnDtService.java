package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;


import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecStnDt;

public interface IJobRecStnDtService {
	
	public void save(JobRecStnDt jobRecStnDt);

	public void delete(int id);

	public JobRecStnDt findOne(int id);
	
	public List<JobRecStnDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	public List<JobRecStnDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt,Boolean deactive);
	
	public String transactionalSave(JobRecStnDt jobRecStnDt,Integer id,Integer jobRecMtId,Integer jobrecDtId,String pSieve,String pSizeGroup,
			Principal principal,Boolean netWtWithCompFlg);
	
	
	public Integer getMaxSrno(JobRecMt jobRecMt, JobRecDt jobRecDt);
	
	public String deleteJobRecStnDt(Integer stnId,Principal principal);
	

}

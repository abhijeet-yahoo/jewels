package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.mysema.query.Tuple;

public interface IJobIssDtService {
	
public List<JobIssDt> findAll();
	
	public void save(JobIssDt jobIssDt);
	
	public void delete(int id);
	
	public JobIssDt findOne(int id);
	
	public JobIssDt findByUniqueId(Long uniqueId);
	
	public List<JobIssDt> findByJobIssMtAndDeactive(JobIssMt JobIssMt,Boolean deactive);
	
	
	
	public String calculateFinalPrice(String finalPrice);
	
	public List<JobIssDt> findByItemNoAndOrderDtAndJobIssMtAndDeactive(String itemNo,OrderDt orderDt,JobIssMt jobIssMt,Boolean deactive);
	
	public List<JobIssDt> findByOrderDtAndJobIssMtAndDeactive(OrderDt orderDt,JobIssMt jobIssMt,Boolean deactive);
	
	public List<JobIssDt> getJobIssDtList(JobIssMt jobIssMt);
	
	public Page<JobIssDt> findByItemNo(Integer limit, Integer offset, String sort, String order, String itemNo, Boolean onlyActive);
	
	public List<JobIssDt> findByItemNoAndDeactive(String itemNo,Boolean deactive);
	
	public void lockUnlockDt(Integer jobIssMtId,Boolean status);
		
	public Page<JobIssDt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId);
	

	public String jobIssDtSave( Integer jobIssDtId,Double grossWt,Double other,Integer partyId,String itemNo,Double dispPercentDt,Double lossPercDt,Principal principal);
	
	public Integer getMaxSetNo(Integer mtId);
	
	public List<Tuple> getSetNoList(Integer jobIssMtId);
	
	public int lockDtInvoice(Integer jobIssMtId, Integer setNo, Principal principal, Boolean vRLock);
	
	public Integer getMaxSetNoByOrder(Integer mtId,Integer sordDtId);
	
	
	public String deleteJobIssDt(Integer dtId);

	
	public String jobIssDtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal,String pInvNo,Boolean disableFlg) throws ParseException;
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecMtService {
	
	public Page<JobRecMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(JobRecMt jobRecMt);
	
	public void delete(int id);
	
	public JobRecMt findOne(int id);
	
	public JobRecMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public Page<JobRecMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name);
	
	public JobRecMt findByUniqueId(Long uniqueId);
	 
	public Page<JobRecMt> getInvNoAutoFill(Integer limit, Integer offset, String sort, String order, String invNo, Boolean onlyActive);
	
	public Map<Integer,String> getJobRecList();
	
	public Page<JobRecMt> findActiveJobRecSortByInvDate();
	
	public String jobRecTransfer(String fromInvoice,String toInvoice,String dtids,Principal principal);
	
	public List<Object[]>  partyWiseAndDateWiseListing(Integer limit, Integer offset, String sort, String order, String search,String partyIds,String fromDate,String toDate) throws ParseException;
	
	public String deleteJobRecMtInvoice(Integer jobRecMtId,Principal principal);
	
	public String addBagInJobRec(String pOIds,Integer jobRecMtId,Principal principal);
	
	public Integer getMaxInvSrno();
}

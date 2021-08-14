package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssMtService {

public List<JobIssMt> findAll();
	
	public Page<JobIssMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(JobIssMt jobIssMt);
	
	public void delete(int id);
	
	public JobIssMt findOne(int id);
	
	public JobIssMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public Page<JobIssMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name);
	
	public JobIssMt findByUniqueId(Long uniqueId);
	 
	public Page<JobIssMt> getInvNoAutoFill(Integer limit, Integer offset, String sort, String order, String invNo, Boolean onlyActive);
	
	public Map<Integer,String> getJobIssList();
	
	public Page<JobIssMt> findActiveJobIssSortByInvDate();
	
	public String jobIssTransfer(String fromInvoice,String toInvoice,String dtids,Principal principal);
	
	public List<Object[]>  partyWiseAndDateWiseListing(Integer limit, Integer offset, String sort, String order, String search,String partyIds,String fromDate,String toDate) throws ParseException;
	
	public String deleteJobIssMtInvoice(Integer jobIssMtId,Principal principal);
	
	public String addBagInJobIss(String pOIds,Integer jobIssMtId,Principal principal, String vProcessId);
	
	public Integer getMaxInvSrno();
	
	public String jobIssMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;
}

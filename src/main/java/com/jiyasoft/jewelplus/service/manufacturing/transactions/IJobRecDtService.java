package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.mysema.query.Tuple;

public interface IJobRecDtService {
	
	
	public void save(JobRecDt jobRecDt);
	
	public void delete(int id);
	
	public JobRecDt findOne(int id);
	
	public JobRecDt findByUniqueId(Long uniqueId);
	
	public List<JobRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	
	
	public String calculateFinalPrice(String finalPrice);
	
		public List<JobRecDt> findByOrderDtAndJobRecMtAndDeactive(OrderDt orderDt,JobRecMt jobRecMt,Boolean deactive);
	
	public List<JobRecDt> getJobRecDtList(JobRecMt jobRecMt);
	
	public Page<JobRecDt> findByItemNo(Integer limit, Integer offset, String sort, String order, String itemNo, Boolean onlyActive);
	
	public List<JobRecDt> findByItemNoAndDeactive(String itemNo,Boolean deactive);
	
	public void lockUnlockDt(Integer jobRecMtId,Boolean status);
		
	public Page<JobRecDt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId);
	

	public String jobRecDtSave( Integer jobRecDtId,Double grossWt,Double other,Integer partyId,String itemNo,Double dispPercentDt,Double lossPercDt,Principal principal);
	
	public Integer getMaxSetNo(Integer mtId);
	
	public List<Tuple> getSetNoList(Integer jobRecMtId);
	
	public int lockDtInvoice(Integer jobRecMtId, Integer setNo, Principal principal, Boolean vRLock);
	
	public Integer getMaxSetNoByOrder(Integer mtId,Integer sordDtId);
	
	public String addBagInJobRecDt(Integer mtId,String data,Principal principal);
	
	public String deleteJobRecDt(Integer dtId);
	
	public String updateDtGrossWtAndMetalDetails(Principal principal,Integer dtId, Double grossWt,Boolean netWtWithComp);
	
	public String updateGrossWt(JobRecDt jobRecDt,Boolean netWtWithComp);

	public String jobRecDtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal,String pInvNo,Boolean disableFlg) throws ParseException;

	public String applyLabRate(String dtIds,String labType,Principal principal);
	
	public String updateFob(JobRecDt jobRecDt,Boolean deactive);
	
	public String applyGradingRate(String dtIds,String labType,Principal principal);

}

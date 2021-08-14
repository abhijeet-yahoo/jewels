package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;
import com.mysema.query.Tuple;

public interface IVouIssDtService {

	public List<VouIssDt> findAll();
	
	public void save(VouIssDt vouIssDt);
	
	public void delete(int id);
	
	public VouIssDt findOne(int id);
	
	public List<VouIssDt> findByVouIssMtAndDeactive(VouIssMt VouIssMt,Boolean deactive);
	
	public String calculateFinalPrice(String finalPrice);
	
	public List<VouIssDt> findBySrNoAndVouIssMtAndDeactive(String srNo,VouIssMt vouIssMt,Boolean deactive);
	
	public List<VouIssDt> getVouIssDtList(VouIssMt vouIssMt);
	
	public Page<VouIssDt> findBySrNo(Integer limit, Integer offset, String sort, String order, String srNo, Boolean onlyActive);
	
	public List<VouIssDt> findBySrNoAndDeactive(String srNo,Boolean deactive);
	
	public void lockUnlockDt(Integer vouIssMtId,Boolean status);
		
	public Page<VouIssDt> searchAll(Integer limit, Integer offset, String sort, String order, String name,Integer mtId);
	
	public String vouIssDtSave( Integer vouIssDtId,Double grossWt,Double other,Integer partyId,String srNo,Double dispPercentDt,Double lossPercDt,Principal principal);
	
	public Integer getMaxSetNo(Integer mtId);
	
	public List<Tuple> getSetNoList(Integer vouIssMtId);
	
	public int lockDtInvoice(Integer vouIssMtId, Integer setNo, Principal principal, Boolean vRLock);
	
	public Integer getMaxSetNoByOrder(Integer mtId,Integer sordDtId);
	
	

}

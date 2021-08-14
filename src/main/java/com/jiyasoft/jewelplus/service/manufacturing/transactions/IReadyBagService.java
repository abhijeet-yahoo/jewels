package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface IReadyBagService {
	
	public List<ReadyBag> findAll();
	
	public Page<ReadyBag> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(ReadyBag readyBag);
	
	public void delete(int id);
	
	public Long count();
	
	public ReadyBag findOne(int id);
	
	public ReadyBag findByName(String name);
	
	public Map<Integer, String> getReadyBagList();

	public List<ReadyBag> findActivebags();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Integer getMaxSrno();

	public List<ReadyBag> getBagsForIssuing(BagMt bagMt,Integer locationId);
	
	/*public ReadyBag findByBagMtAndBagSrNoAndRetStoneAndRetCarat(BagMt bagMt,Integer bagSrNo,Integer retStone,Double retCarat);*/
	
	public List<ReadyBag> findByBagMtAndCurrentStock(BagMt bagMt,Boolean currStk);
	
	public String readyBagTransfer(String pBagNm, String pIds,Integer pDeptId,Boolean pUpdGold,Principal principal,Date tranDate,Integer pLocationId);
	
	public String multiReadyBagTransfer(String pBagNm, Integer pDeptId,Boolean pUpdGold,Principal principal,Date tranDate,Integer locationId);

	public String getReadyBagDetail(Integer deptid);
	
	public List<ReadyBag> findByBagMtAndBagSrNoAndDeactive(BagMt bagMt,Integer bagSrNo,Boolean deactive);
	
	public String readyBagDelete(Integer id,Principal principal,Date tranDate,String companyName);
	
	public List<ReadyBag> findByTranMtAndCurrentStockAndDeactive(TranMt tranMt,Boolean currStk,Boolean deactive);
	
	public List<ReadyBag> findByBagMtAndLocationIsNull(BagMt bagMt);
	
	public List<ReadyBag> findByBagMt(BagMt bagMt);
	
	public List<ReadyBag> findByBagMtAndCurrentStockAndLocationIsNull(BagMt bagMt,Boolean currStk);	

	public String getReadyBagReturnList(String pBagNm,Integer locationId);
	
	public List<ReadyBag> findByBagMtAndCurrentStockAndLocationAndPendApprovalFlg(BagMt bagMt,Boolean currStk,Department department, Boolean pendApprovalFlg);
}

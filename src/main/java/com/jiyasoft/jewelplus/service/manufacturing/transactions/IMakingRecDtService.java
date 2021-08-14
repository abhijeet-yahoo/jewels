package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingRecDt;


public interface IMakingRecDtService {

	
	public List<MakingRecDt> findAll();

	public Page<MakingRecDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(MakingRecDt makingRecDt);

	public void delete(int id);

	public Long count();

	public MakingRecDt findOne(int id);

	public MakingRecDt findByUniqueId(Long uniqueId);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public List<MakingRecDt> findByMakingMtAndDeactive(MakingMt makingMt, Boolean deactive); 
	
	public String saveMakingDt(String pInvNo,Integer id,MakingRecDt makingRecDt,Principal principal,Double changedIssueWt,Double changeRetMetWt);
	
	public Integer makingrecDelete(Integer id);
	
}

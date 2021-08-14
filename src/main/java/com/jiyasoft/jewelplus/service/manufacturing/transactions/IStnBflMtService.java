package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflMt;

public interface IStnBflMtService {

	
	public List<StnBflMt> findAll();

	public Page<StnBflMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(StnBflMt stnBflMt);

	public void delete(int id);

	public Long count();

	public StnBflMt findOne(int id);

	public StnBflMt  findByInvNoAndDeactive(String invNo,Boolean deactive);

	public Map<Integer, String> getStnBflMtList();

	public List<StnBflMt> findActiveStnBflMts();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<StnBflMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	public StnBflMt findByUniqueId(Long uniqueId);
	
	public Integer maxSrNo();
	
	
	
	public String saveData(String pOIds,String trfAdjStone,String trfAdjCarat,String pTypeFormat,String trfLossStone,String trfLossCarat,Integer pMtId,Principal principal);
	
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;

public interface IMakingMtService {

	public List<MakingMt> findAll();

	public Page<MakingMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(MakingMt makingMt);

	public void delete(int id);

	public Long count();

	public MakingMt findOne(int id);

	MakingMt findByInvNoAndDeactive(String invNo,Boolean deactive);

	public Map<Integer, String> getMakingMtList();

	public List<MakingMt> findActiveMakingMts();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<MakingMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	public MakingMt findByUniqueId(Long uniqueId);
	
	public Long maxBySrNo();
	
	
	public String saveMakingMt(MakingMt makingMt,Integer id,Principal principal,Integer prevKt,Integer prevColor,Double prevFreshMetalWt,String vTranDate) throws ParseException;
	
	public String makingMtDelete(Integer id);
	
	public String  makingMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;

	public Page<MakingMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	
}

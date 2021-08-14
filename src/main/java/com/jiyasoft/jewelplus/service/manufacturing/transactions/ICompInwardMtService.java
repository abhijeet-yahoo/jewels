package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;


public interface ICompInwardMtService {
	
	public List<CompInwardMt> findAll();

	public Page<CompInwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(CompInwardMt compInwardMt);

	public void delete(int id);

	public Long count();

	public CompInwardMt findOne(int id);

	public CompInwardMt  findByInvNoAndDeactive(String invNo,Boolean deactive);

	public Map<Integer, String> getCompInwardMtList();

	public List<CompInwardMt> findActiveCompInwardMts();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<CompInwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	public CompInwardMt findByUniqueId(Long uniqueId);
	
	
	public List<Object[]>  partyWiseAndDateWiseListing(String partyIds,String fromDate, String toDate) throws ParseException;
	
	public Page<CompInwardMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String compInwDelete(int id, Principal principal);
	
	
	public Integer getMaxInvSrno();

	
	
	
	
}

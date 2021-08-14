package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardMt;

public interface ICompOutwardMtService {
	
	public List<CompOutwardMt> findAll();

	public Page<CompOutwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(CompOutwardMt compOutwardMt);

	public void delete(int id);

	public Long count();

	public CompOutwardMt findOne(int id);

	public CompOutwardMt findByInvNo(String invNo);

	public Map<Integer, String> getCompOutwardMtList();

	public List<CompOutwardMt> findActiveCompOutwardMts();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<CompOutwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	public CompOutwardMt findByUniqueId(Long uniqueId);
	
	public CompOutwardMt findByInvNoAndDeactive(String invNo, Boolean deactive);
	
	public Page<CompOutwardMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String compOutDelete(int id);
	
	public List<Object[]>  partyWiseAndDateWiseListing(String partyIds,String fromDate, String toDate) throws ParseException;
	
	public Integer getMaxCompOutInvSrno();
}

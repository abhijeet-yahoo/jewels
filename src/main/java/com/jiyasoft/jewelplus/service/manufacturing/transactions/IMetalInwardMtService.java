package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;

public interface IMetalInwardMtService {

	public List<MetalInwardMt> findAll();

	public Page<MetalInwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(MetalInwardMt metalInwardMt);

	public void delete(int id);

	public Long count();

	public MetalInwardMt findOne(int id);

	public MetalInwardMt  findByInvNoAndDeactive(String invNo,Boolean deactive);

	public Map<Integer, String> getMetalInwardMtList();

	public List<MetalInwardMt> findActiveMetalInwardMts();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<MetalInwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public MetalInwardMt findByUniqueId(Long uniqueId);
	
	public List<Object[]>  partyWiseAndDateWiseListing(String partyIds,
			String fromDate, String toDate) throws ParseException; 
	
	public Page<MetalInwardMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String metalInwDelete(int id);
	
	public Integer getMaxInvSrno();
	
	
}

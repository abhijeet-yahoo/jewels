package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;

public interface IStoneOutwardMtService {
	

	public List<StoneOutwardMt> findAll();
	
	public Page<StoneOutwardMt> findAll(Integer limit, Integer offset,
			String sort,String order,String search);
	
	public void save(StoneOutwardMt stoneOutwardMt);
	
	public void delete(int id);
	
	public Long count();
	
	public StoneOutwardMt findOne(int id);
	
	public Map<Integer, String> getStoneOutwardMtList();
	
	public List<StoneOutwardMt> findActiveStoneOutwardMts();
	
	public Long count(String colName,String colValue,Boolean onlyActive);
	
	public Page<StoneOutwardMt> findByInvNo(Integer limit,Integer offset,
			String sort,String order,String name,Boolean onlyActive);
	

	public String deleteMt(Integer mtId,Principal principal);
	
	public StoneOutwardMt  findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public List<StoneOutwardMt>getInvoiceList(String inwardTypeIds); 
	
	public Page<StoneOutwardMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String stoneOutDelete(int id);
	
	public Integer getMaxStoneOutInvSrno();
	
	
	
}

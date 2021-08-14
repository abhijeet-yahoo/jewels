package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;

public interface IStoneInwardMtService {

	public List<StoneInwardMt> findAll();

	public Page<StoneInwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(StoneInwardMt stoneInwardMt);

	public void delete(int id);

	public Long count();

	public StoneInwardMt findOne(int id);

	public StoneInwardMt  findByInvNoAndDeactive(String invNo,Boolean deactive);

	public Map<Integer, String> getStoneInwardMtList();

	public List<StoneInwardMt> findActiveStoneInwardMts();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<StoneInwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	public StoneInwardMt findByUniqueId(Long uniqueId);
	
	public List<StoneInwardMt>getInvoiceList(String inwardTypeIds); 
	
	
	public String deleteMt(Integer mtId,Principal principal);
	
	public Page<StoneInwardMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String stoneInwDelete(int id);
	
	public Integer getMaxInvSrno();
	
	public String stoneInwLooseConvTransfer(Integer mtid, String convDtId,Principal principal);
}

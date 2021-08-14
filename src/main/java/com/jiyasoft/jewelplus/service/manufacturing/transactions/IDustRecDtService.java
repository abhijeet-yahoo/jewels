package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
 

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustRecDt;

public interface IDustRecDtService {
	
	public DustRecDt findOne(Integer id);
	
	public List<DustRecDt> findAll();
	
    public Page<DustRecDt> findAll(Integer limit, Integer offset, String sort,String order, String search);
	
	public void delete(Integer id);
	
	public void save(DustRecDt dustRecDt);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Long count();
	
	public List<DustRecDt> findByDustMtAndDeactive(DustMt dustMt,Boolean deactive);
	
	public DustRecDt findByUniqueId(Long uniqueId);
	

}

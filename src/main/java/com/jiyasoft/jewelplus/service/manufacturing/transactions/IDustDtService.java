package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;

public interface IDustDtService {
	
	public DustDt findOne(Integer id);
	
	public List<DustDt> findAll();
	
    public Page<DustDt> findAll(Integer limit, Integer offset, String sort,String order, String search);
	
	public void delete(Integer id);
	
	public void save(DustDt dustMt);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Long count();
	
	public List<DustDt> findByDustMtAndDeactive(DustMt dustMt,Boolean deactive);
	
}

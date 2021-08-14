package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;

public interface IDustMtService {
	
	public DustMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public DustMt findOne(Integer id);
	
	public List<DustMt> findAll();
	
    public Page<DustMt> findAll(Integer limit, Integer offset, String sort,String order, String search);
	
	public void delete(Integer id);
	
	public void save(DustMt dustMt);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<DustMt> findByInvNo(Integer limit, Integer offset,String sort, String order, String name, Boolean onlyActive);
	
	public Integer maxSrNo();
	
	public DustMt findByUniqueId(Long uniqueId);
	
	public DustMt findByFromPeriodAndToPeriodAndDeactive(Date fromPeriod,Date toPeriod,Boolean deactive);
	
}

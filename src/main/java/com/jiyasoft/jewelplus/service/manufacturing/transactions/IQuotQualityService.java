package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotQualityService {
	
	public List<QuotQuality> findAll();
	
	public Page<QuotQuality> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(QuotQuality quotQuality);
	
	public void delete(int id);
	
	public Long count();
	
	public QuotQuality findOne(int id);
	
	public List<QuotQuality> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
}

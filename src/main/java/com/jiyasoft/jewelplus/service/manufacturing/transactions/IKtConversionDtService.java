package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionMt;

public interface IKtConversionDtService {
	
	public List<KtConversionDt> findAll();
	
	public Page<KtConversionDt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(KtConversionDt ktConversionDt);
	
	public void delete(int id);
	
	public Long count();
	
	public KtConversionDt findOne(int id);
	
	public List<KtConversionDt> findByKtConversionMtAndDeactive(KtConversionMt ktConversionMt,Boolean deactive);
	
	public KtConversionDt findByUniqueId(Long uniqueId);

}

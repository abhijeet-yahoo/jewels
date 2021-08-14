package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionMt;;

public interface IKtConversionMtService {
	
	public List<KtConversionMt> findAll();
	
	public Page<KtConversionMt> findAll(Integer limit, Integer offset, String sort, String order, String search);

	public void save(KtConversionMt ktConversionMt);

	public void delete(int id);

	public Long count();

	public KtConversionMt findOne(int id);

	public KtConversionMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<KtConversionMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name, Boolean onlyActive);
	
	public KtConversionMt findByUniqueId(Long uniqueId);
	
	public Integer maxSrNo();

}

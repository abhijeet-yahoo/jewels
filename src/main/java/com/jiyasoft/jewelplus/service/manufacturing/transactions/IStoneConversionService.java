package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneConversion;


public interface IStoneConversionService {

	public Page<StoneConversion> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(StoneConversion stoneConversion);

	public void delete(int id);
	
	public Integer getMaxInvSrno();
}

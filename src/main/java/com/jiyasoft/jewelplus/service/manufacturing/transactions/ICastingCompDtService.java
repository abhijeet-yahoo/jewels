package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;

public interface ICastingCompDtService {
	
	public List<CastingCompDt> findAll();

	public Page<CastingCompDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(CastingCompDt castingCompDt);

	public void delete(int id);

	public Long count();

	public CastingCompDt findOne(int id);
	
	public List<CastingCompDt> findByCastingMtAndDeactive(CastingMt castingMt,Boolean deactive);

}

package com.jiyasoft.jewelplus.service.manufacturing.masters;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BagGenQty;


public interface IBagGenQtyService {
	
	public void save(BagGenQty bagGenQty);

	public void delete(int id);
	
	public BagGenQty findOne(int id);
	
	public Page<BagGenQty> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public String checkDuplicate(Double fromCtsWt, Double toCtsWt,Integer id);
	
	public  BagGenQty findByQty(Double qty);

}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardMt;

public interface IMetalOutwardMtService {
	public List<MetalOutwardMt> findAll();

	public Page<MetalOutwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(MetalOutwardMt metalOutwardMt);

	public void delete(int id);

	public Long count();

	public MetalOutwardMt findOne(int id);

	public MetalOutwardMt findByInvNo(String invNo);

	public Map<Integer, String> getMetalOutwardMtList();

	public List<MetalOutwardMt> findActiveMetalOutwardMts();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<MetalOutwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);


	public MetalOutwardMt findById(Integer id);
	
	public MetalOutwardMt findByUniqueId(Long uniqueId);
	
	public MetalOutwardMt findByInvNoAndDeactive(String invNo, Boolean deactive);
	
	public Page<MetalOutwardMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String metalOutDelete(int id);
	
	public Integer getMaxInvSrno();
}

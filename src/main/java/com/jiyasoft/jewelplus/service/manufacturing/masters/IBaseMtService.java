package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BaseMt;

public interface IBaseMtService {
	
	public List<BaseMt> findAll();
	
	public Page<BaseMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(BaseMt baseMt);
	
	public void delete(int id);
	
	public Long count();
	
	public BaseMt findOne(int id);
	
	public BaseMt findByBaseNo(Integer baseNo);
	
	public Map<Integer, String> getBaseMtList();

	public List<BaseMt> findActiveBaseMts();

}

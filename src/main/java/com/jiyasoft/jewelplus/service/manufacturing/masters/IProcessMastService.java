package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProcessMast;

public interface IProcessMastService {

	public List<ProcessMast> findAll();

	public Page<ProcessMast> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(ProcessMast processMast);
	
	public void delete(int id);

	public Long count();
	
	public ProcessMast findOne(int id);

	public List<ProcessMast> findActiveProcessMasts();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<ProcessMast> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	
	
	public Long countAll(String colValue);

	
	
	public Page<ProcessMast> searchAll(Integer limit, Integer offset, String sort,
			String order, String name);

	

}

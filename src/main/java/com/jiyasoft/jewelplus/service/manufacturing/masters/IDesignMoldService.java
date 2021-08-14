package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMold;

public interface IDesignMoldService {
	
	public List<DesignMold> findAll();
	
	public void save(DesignMold designMold);
	
	public void delete(int id);
	
	public Long count();
	
	public DesignMold findOne(int id);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Page<DesignMold> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public Page<DesignMold> findByStyleNo(Integer limit, Integer offset, String sort, String order,String styleNo, Boolean onlyActive);
	
	public Page<DesignMold> findByDesignMoldType(Integer limit, Integer offset, String sort, String order,String designMoldType, Boolean onlyActive);
	
	public Page<DesignMold> findByRackNo(Integer limit, Integer offset, String sort, String order,String rackNo, Boolean onlyActive);
	
	public Page<DesignMold> findByDrawer(Integer limit, Integer offset, String sort, String order,String drawerNo, Boolean onlyActive);
	
	

}

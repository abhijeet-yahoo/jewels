package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;

public interface IColorService
{
		
	public List<Color> findAll();
	
	public Page<Color> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(Color color);
	
	public void delete(int id);
	
	public Long count();
	
	public Color findOne(int id);
	
	public Color findByName(String name);
	
	public Map<Integer, String> getColorList();

	public List<Color> findActiveColors();
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Color> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Page<Color> findActiveColorsSortByName();
	
	//--------deduction color----//
	
	public Map<Integer, String> getColorForDeduction(List<String> colors);
	
	public List<Color> findDeductionColor(List<String> colors);
	
	public String getColorListDropDownForDeduction(List<String> colors);
	
	public Page<Color> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public Color findByDefColorAndDeactive(Boolean defColor,Boolean deactive);
	
}

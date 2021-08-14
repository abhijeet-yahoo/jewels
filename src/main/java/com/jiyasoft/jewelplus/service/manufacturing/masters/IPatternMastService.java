package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.PatternMast;

public interface IPatternMastService {
	
	public Page<PatternMast> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);
	
	public PatternMast findOne(int id);
	
	public void save(PatternMast patternMast);
	
	public void delete(int id);
	
	public PatternMast findByName(String name);
	
	public Map<Integer, String> getPatternList();
	
	public Page<PatternMast> findActivePatternSortByName();

}

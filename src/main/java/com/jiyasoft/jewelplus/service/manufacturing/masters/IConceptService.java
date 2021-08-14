package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;

public interface IConceptService {

	public List<Concept> findAll();

	public Page<Concept> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Concept concept);

	public void delete(int id);

	public Long count();
	
	public Concept findOne(int id);

	public Concept findByName(String conceptName);

	public Map<Integer, String> getConceptList();

	public List<Concept> findActiveConcepts();

	public Page<Concept> findActiveConceptsSortByName();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Concept> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public Page<Concept> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);


}

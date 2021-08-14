package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubConcept;
import com.mysema.query.types.Predicate;

public interface ISubConceptService {
	
	public List<SubConcept> findAll();

	public Page<SubConcept> findAll(Integer limit, Integer offset, String sort,
			String order, String search);
	
	public void save(SubConcept subConcept);
	
	public void delete(int id);
	
	public Long count();
	
	public Long count(Predicate predicate);
	
	public SubConcept findOne(int id);
	
	public SubConcept findByName(String subConceptName);
	public SubConcept findByConceptAndName(Concept concept,String subConceptName);
	
	public Page<SubConcept> findByConcept(Concept concept, Integer limit,
			Integer offset, String sort, String order, String search);
	
	public Predicate countByConcept(Integer id);
	
	public Map<Integer, String> getSubConceptList(Integer conId);

	public String getSubConceptListDropDown(Integer conId);

	public List<SubConcept> findActiveSubConcepts(Integer conId);

	public Page<SubConcept> findActiveSubConceptsSortByName(Integer conId);

	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Page<SubConcept> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public String getSubConceptReportListDropDown(String conIds);
	
	public List<SubConcept> findByDeactive(Boolean deactive);
	
	public Page<SubConcept> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);

	
}

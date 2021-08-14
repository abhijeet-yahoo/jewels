package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;
import com.mysema.query.types.Predicate;



public interface IPurityService {

	public List<Purity> findAll();

	public Page<Purity> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Purity purity);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public Purity findOne(int id);

	public Purity findByName(String purityName);

	public Page<Purity> findByMetal(Metal metal, Integer limit, Integer offset,
			String sort, String order, String search);

	public Predicate countByMetal(Integer id);
	
	public Map<Integer, String> getPurityList(Integer matId);
	
	public List<Purity> findActivePurity(Integer matId);
	
	public Map<String, Double> getPurityWeightList(Integer metalId);
	
	//////////////
	public Map<Integer, String> getPurityList();
	
	public List<Purity> findActivePurity();
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Purity> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Purity findByMetalAndPure(Metal metal, Boolean pure);

	public Purity findByMetalAndDefPurity(Metal metal, Boolean defPurity);
	
	public Purity getDefaultPurity();

	public String getPurityListDropDown(Integer conId);
	
	public Purity findByDefPurityAndDeactive(Boolean defPurity,Boolean deactive);
	
	//--------deduction purity----//
	
	public Map<Integer, String> getPurityForDeduction(List<String> puritys);
	
	public List<Purity> findDeductionPurity(List<String> puritys);
	
	public String getPurityListDropDownForDeduction(List<String> puritys);
	
	public Page<Purity> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
}

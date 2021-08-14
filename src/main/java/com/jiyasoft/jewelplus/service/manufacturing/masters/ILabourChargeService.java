package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.mysema.query.types.Predicate;

public interface ILabourChargeService {

	public List<LabourCharge> findAll();

	public Page<LabourCharge> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(LabourCharge labourCharge);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public LabourCharge findOne(int id);

	public LabourCharge findByRate(Double rate);

	public Page<LabourCharge> findByCategory(Category category, Integer limit,
			Integer offset, String sort, String order, String search);

	public Predicate countByCategory(Integer id);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<LabourCharge> findByRate(Integer limit, Integer offset,
			String sort, String order, String perPcsRate, Boolean onlyActive);

	public List<LabourCharge> findByCategoryAndLabourType(Category category,
			LabourType labourType);

	public List<LabourCharge> findByCategoryAndDeactive(Category category,
			Boolean deactive);

	public List<LabourCharge> findByDefLabour(Boolean defLabour);

	// public List<LabourCharge> findbyLabourTypeAndRLock(LabourType
	// labourType,Boolean rLock);

	public List<LabourCharge> findByCategoryAndDeactiveAndParty(
			Category category, Boolean deactive, Party party);

	// new method for party

	public List<LabourCharge> findByPartyAndCategoryAndLabourType(Party party,
			Category category, LabourType labourType);

	public Page<LabourCharge> findByCategory(Category category, Integer limit,
			Integer offset, String sort, String order, String name,
			Boolean onlyActive);

	public Long countByCategory(Category category, String colName,
			String colValue, Boolean onlyActive);

	

	public List<LabourCharge> getRates(Party party, Category category,
			Double metalWt, Boolean deactive, Metal metal);
	
	
	public List<LabourCharge> getPurityWiseRates(Party party, Category category,
			Double metalWt, Boolean deactive, Metal metal,Purity purity);
	

	public List<LabourCharge> getLabourRates(Party party, Category category,
			Double metalWt, Boolean deactive, Metal metal, LabourType labourType);
	
	

	public List<LabourCharge> getPurityLabourRates(Party party, Category category,
			Double metalWt, Boolean deactive, Metal metal, LabourType labourType,Purity purity);

	// ----------custom search method ---//

	public Page<LabourCharge> customSearch(Integer limit, Integer offset,
			String sort, String order, String partyCode, String categoryNm,
			String metalNm, String labourTypeNm);

	public Long customSearchCount(String partyCode, String categoryNm,
			String metalNm, String labourTypeNm);

	public List<LabourCharge> findByPartyAndCategoryAndMetalAndDeactive(
			Party party, Category category, Metal metal, Boolean deactive);

	public Page<LabourCharge> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public String CheckDuplicate(Integer id, Integer vPartyId, Integer vCategoryId, Integer metalId, Integer labourTypeId,Double fromWt, Double toWt,Integer purityId);

}

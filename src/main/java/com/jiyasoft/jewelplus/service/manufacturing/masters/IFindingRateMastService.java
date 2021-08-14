	package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;



public interface IFindingRateMastService {
	
	public List<FindingRateMast> findAll();

	public Page<FindingRateMast> findAll(Integer limit, Integer offset, String sort,String order, String search);

	public void save(FindingRateMast findingRateMast);

	public void delete(int id);

	public Long count();
	
	public FindingRateMast findOne(int id);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Page<FindingRateMast> findByPartyAndPurityAndComponentAndRate(Integer limit, Integer offset, String sort, String order, String name, Boolean onlyActive);
	
	public FindingRateMast findByComponentAndPartyAndPurityAndDeactive(Component component, Party party, Purity purity, Boolean deactive);
	
	public 	FindingRateMast findByPartyAndComponentAndPurityAndPerGramRateAndPerPcRateAndDeactive(Party party, Component component, Purity purity, Double perGramRate, Double perPcRate, Boolean deactive);

	public FindingRateMast findByPartyAndComponentAndPurityAndDeactive(Party party, Component component, Purity purity, Boolean deactive);
	
	public Page<FindingRateMast> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	
	
	public Page<FindingRateMast> customSearch(Integer limit, Integer offset,
			String sort, String order, String partyCode, String purityName,
			String findingName);

	public Long customSearchCount( String partyCode, String purityName,
			String findingName);

}

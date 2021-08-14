package com.jiyasoft.jewelplus.service.manufacturing.masters;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LedgerGroup;

public interface ILedgerGroupService {

    public List<LedgerGroup> findAll(Integer compId);
	
	public void save(LedgerGroup ledgerGroup);
	
	public void delete(int id);
	
	public Long count(Integer compId);
	
	public LedgerGroup findOne(int id);
	
	public LedgerGroup findByName(String name);
	
	public String getMainLedgerGroupListDropDown();
	
	public Map<String, String> getMainLedgerGroupList();

	
	public Page<LedgerGroup> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Integer compId, Boolean onlyActive);
	
	//---- Main Group combos -- //
	
	public List<String> getMainGroupList();
		
	public Page<LedgerGroup> findActiveMainGroupSortByName();
	
	//---- Ledger Group combos -- //
	
	public Map<Integer, String> getLedgerGroupList();
	
	public Page<LedgerGroup> findActiveLedgerGroupSortByName();
	
	public List<String> getLedgerGroupNmList();
	
	
	
}

package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BranchMaster;

public interface IBranchService {

	public void save (BranchMaster branchMaster);
	
	public void delete(int id);
	
	public List<BranchMaster> findAll(); 
	
	public Page<BranchMaster> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public BranchMaster findOne (int id);
	
	public Map<Integer, String> getBranchMasterList();
	
	public List<BranchMaster> findActiveBranchMasters();
	
	public Page<BranchMaster> findActiveBrancMasterSortByName();
	
	public BranchMaster findByCodeAndDeactive(String code, Boolean deactive);
	
	public BranchMaster findByNameAndDeactive(String branchName, Boolean deactive);
	
	
}

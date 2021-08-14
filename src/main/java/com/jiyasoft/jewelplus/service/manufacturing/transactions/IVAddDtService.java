package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;

public interface IVAddDtService {

	public void save(VAddDt vAddDt);
	
	public void delete(int id);
	
	public VAddDt findOne(int id);
	
	public Long count();
	
	public List<VAddDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public void deleteAll(CostingMt costingMt);
	
	public VAddDt findByUniqueId(Long uniqueId);
	
	public Double calulateVAddDtTotFob(Integer costMt);
	
	public void updateWastagePerc(Integer costMtId,Double wastagePerc);
	
	public String loadValuAddition(String invNo,Principal principal);
	
	public Page<VAddDt> searchAll(Integer limit, Integer offset, String sort, String order, String search,Integer mtId);
	
	public String applyRate(VAddDt vAddDt, Principal principal); 
	
	public String applyMetal(VAddDt vAddDt);
	
	public String applyWastage(VAddDt vAddDt);
	
	public String loadValuAdditionFl(String invNo,Principal principal);
	
	public String updateValueAddition(VAddDt vAddDt);
	
	public String updateQualityStoneRate(Integer costMtId,Integer shapeId,Integer qualityId,Double fromRate,Double toRate,Principal principal,Integer sizeGroupId);
	
	
	public String vaddDtListing(Integer limit, Integer offset, String sort, String order, String search,String pInvNo);
	

	
}

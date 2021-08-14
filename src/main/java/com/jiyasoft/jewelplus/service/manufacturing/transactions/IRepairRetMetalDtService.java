package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMetalDt;

public interface IRepairRetMetalDtService {

	public List<RepairRetMetalDt>findByRepairRetDt(RepairRetDt repairRetDt);
	
	public RepairRetMetalDt findByRepairRetDtAndPartNm(RepairRetDt repairRetDt,LookUpMast lookUpMast);
	
	public void save(RepairRetMetalDt repairRetMetalDt);

	public void delete(int id);
	
	public RepairRetMetalDt findOne(int id);	
	
	public String repairRetMetalDtListing(Integer dtId);
	
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetStnDt;

public interface IRepairRetStnDtService {

	public void save(RepairRetStnDt repairRetStnDt);

	public void delete(int id);
	
	public RepairRetStnDt findOne(int id);
	
	public List<RepairRetStnDt>findByRepairRetDt(RepairRetDt repairRetDt);
	
	public String repairRetStnDtListing(Integer dtId);
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;

public interface IRepairRetCompDtService {
	
	public List<RepairRetCompDt>findByRepairRetDt(RepairRetDt repairRetDt);
	
	public void save(RepairRetCompDt repairRetCompDt);

	public void delete(int id);
	
	public RepairRetCompDt findOne(int id);
	
	public String repairRetCompDtListing(Integer dtId, String disableFlg);
	

}

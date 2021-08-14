package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMt;

public interface IRepairRetDtService {

	public void save(RepairRetDt repairRetDt);

	public void delete(int id);
	
	public RepairRetDt findOne(int id);
	
	public List<RepairRetDt> findByRepairRetMt(RepairRetMt repairRetMt);
	
	public String deleteRepairRetDt(Integer dtId);
	
	public String repairRetDtListing(Integer mtId,String flag);
	
	public String repairRetTransferInFactory(Principal principal, String vMtId, Integer repairRetMtId);
}

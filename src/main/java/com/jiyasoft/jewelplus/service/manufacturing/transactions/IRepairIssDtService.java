package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssMt;

public interface IRepairIssDtService {

	public List<RepairIssDt> findAll();

	public Page<RepairIssDt> findAll(Integer limit, Integer offset, String sort, String order, String search);

	public void save(RepairIssDt repairIssDt);

	public void delete(int id);

	public RepairIssDt findOne(int id);

	public List<RepairIssDt> findByRepairIssMt(RepairIssMt repairIssMt);

	public Page<RepairIssDt> findByRepairIssMt(RepairIssMt repairIssMt, Integer limit, Integer offset, String sort, String order,
			String search);

	public String repairTransferInStock(Principal principal, String vMtId, Integer repairIssMtId);

	public String repairApproval(Principal principal, String vRepairIssDtIds);
}

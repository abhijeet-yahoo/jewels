package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingRecDt;

public interface IMeltingRecDtService {
	
	public List<MeltingRecDt> findAll();

	public void save(MeltingRecDt meltingRecDt);

	public void delete(int id);

	public MeltingRecDt findOne(int id);
	
	public Page<MeltingRecDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);
	
	public Long count();
	
	List<MeltingRecDt> findByMeltingMtAndDeactive(MeltingMt meltingMt,Boolean deactive);
	
	public MeltingRecDt findByUniqueId(Long uniqueId);
	
	public String meltingRecDtSave(MeltingRecDt meltingRecDt,Integer id,String recInvNo,Double recPNetWt,Double recPExcpPureWt,Double pRecFMetalWt,Double vLoss,
			Double pRecTotalExpcPureWts,Principal principal,Double pMeltingBal);

}

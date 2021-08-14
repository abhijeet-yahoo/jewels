package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetMt;

public interface IStnLooseRetDtService {
	
public List<StnLooseRetDt> findByStnLooseRetMt(StnLooseRetMt stnLooseRetMt);
	
	public void save(StnLooseRetDt stnLooseRetDt);

	public void delete(int id);
	
	public StnLooseRetDt findOne(int id);
	
	public Integer getMaxLotSrno();
	
	public String stnLooseRetDtListing(Integer mtId,Boolean canViewFlag,Principal principal);
	
	public String stnLooseRetDtDelete(Integer id, Principal principal);
	
	public String stnLooseRetDtSave(Integer mtId,String dtIds,Principal principal);

	public List<StnLooseRetDt> findByRefTranId(Integer refTranId);
}

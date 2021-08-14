package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;

public interface IStnLooseDtService {
	
	public List<StnLooseDt> findByStnLooseMt(StnLooseMt stnLooseMt);
	
	public void save(StnLooseDt stnLooseDt);

	public void delete(int id);
	
	public StnLooseDt findOne(int id);
	
	public Integer getMaxLotSrno();
	
	public String stnLooseDtListing(Integer mtId,Boolean canViewFlag,Principal principal);
	
	public String stnLooseDtDelete(Integer id, Principal principal);
	
	
	public String saveStnLooseDt(StnLooseDt stnLooseDt, Integer id, String pInvNo, Double vCarat, Integer vStone,
			String sizeGroupStr, Principal principal);
	
	public String getStnLooseBalanceStock(Integer dtId);

}

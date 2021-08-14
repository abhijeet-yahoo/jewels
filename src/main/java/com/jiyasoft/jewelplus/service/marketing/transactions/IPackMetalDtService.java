package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;

public interface IPackMetalDtService {

	public void save(PackMetalDt packMetalDt);

	public void delete(int id);
	
	public PackMetalDt findOne(int id);
	
	public List<PackMetalDt>findByPackDt(PackDt packDt);
	
	public PackMetalDt findByPackDtAndPartNm(PackDt packDt,LookUpMast lookUpMast);
	
	public String packMetalDtListing(Integer dtId);
	
	public String updateLossPerc(Principal principal, Integer packDtId, Double lossPerc);
	
	public PackMetalDt findByPackDtAndMainMetal(PackDt packDt,Boolean mainMetal);

}

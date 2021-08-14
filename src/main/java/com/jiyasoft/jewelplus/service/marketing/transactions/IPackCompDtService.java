package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;


public interface IPackCompDtService {
	
	public void save(PackCompDt packCompDt);

	public void delete(int id);
	
	public PackCompDt findOne(int id);
	
	public List<PackCompDt>findByPackDt(PackDt packDt);
	
	public String updateCompRate(Principal principal,Integer packCompId,Double compRate);
	
	public String packCompDtListing(Integer dtId, String disableFlg);
	
	public String packCompDtSave(PackCompDt packCompDt, Integer id,Integer packMtId, Integer packDtId,Integer componentId,Integer purityId,Integer colorId, Principal principal);
	
}

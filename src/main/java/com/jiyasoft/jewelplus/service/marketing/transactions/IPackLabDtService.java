package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;

public interface IPackLabDtService {
	
	public void save(PackLabDt packLabDt);

	public void delete(int id);
	
	public PackLabDt findOne(int id);
	
	public List<PackLabDt>findByPackDt(PackDt packDt);
	
	public List<PackLabDt> findByPackDtAndMetal(PackDt packDt,Metal metal);
	
	public String packLabDtListing(Integer dtId,String disableFlg);
	
	
	public String packLabDtSave(PackLabDt packLabDt, Integer id,Integer packMtId, Integer packDtId, Principal principal);
	
	public String updatePackLabourRate(Principal principal,Integer packMtId, Double labourRate);
	
	public List<PackLabDt>findByPackMt(PackMt packMt);
	
	public String applyGrading(PackDt packDt, Principal principal);

}

package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;

public interface IGrecMetalDtService {

	public void save(GrecMetalDt grecMetalDt);

	public void delete(int id);
		
	public GrecMetalDt findOne(int id);
	
	public GrecMetalDt findByGrecDtAndMainMetal(GrecDt grecDt,Boolean mainMetal);

	public GrecMetalDt findByGrecDtAndPartNm(GrecDt grecDt,LookUpMast lookUpMast);

	public List<GrecMetalDt> findByGrecMt(GrecMt grecMt);
	
	public List<GrecMetalDt> findByGrecDt(GrecDt grecDt);
	
	public void addGrecMetalDtFromDesign(List<DesignMetal> designMetals,GrecMt grecMt,GrecDt grecDt,Principal principal);
	
	public void addGrecMetalDt(String metalData,GrecMt grecMt,GrecDt grecDt,Principal  principal);
	
	public Boolean grecPartValidation(Integer grecDtId, Integer partId); /* ASk about it */
	
	public void setGrecMetalDt(List<DesignMetal> designMetals,GrecMt grecMt,GrecDt grecDt,Principal principal);
	
	public String grecMetalDtListing(Integer grecDtId);
}

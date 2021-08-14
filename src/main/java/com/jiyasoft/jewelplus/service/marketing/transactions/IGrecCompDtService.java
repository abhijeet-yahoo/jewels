package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;

public interface IGrecCompDtService {

	public void save(GrecCompDt grecCompDt);

	public void delete(int id);

	public GrecCompDt findOne(int id);

	public List<GrecCompDt> findByGrecMtAndGrecDt(GrecMt grecMt, GrecDt grecDt);

	public List<GrecCompDt> findByGrecMt(GrecMt grecMt);

	public List<GrecCompDt> findByGrecDt(GrecDt grecDt);
	
	public String grecCompDtListing(Integer grecDtId,  Boolean canViewFlag, Principal principal);
	
	public void setGrecCompDt(List<DesignComponent> designComponents, GrecMt grecMt, GrecDt grecDt,
			Principal principal);
}

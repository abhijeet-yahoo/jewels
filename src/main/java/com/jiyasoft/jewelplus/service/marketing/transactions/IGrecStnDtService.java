package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecStnDt;

public interface IGrecStnDtService {

	public void save(GrecStnDt grecStnDt);

	public void delete(int id);

	public GrecStnDt findOne(int id);

	public List<GrecStnDt> findByGrecMt(GrecMt grecMt);

	public Integer getMaxSrno(GrecMt grecMt, GrecDt grecDt);

	public void setGrecStnDt(List<DesignStone> designStones, GrecMt grecMt, GrecDt grecDt, Principal principal);

	public List<GrecStnDt> findByGrecDt(GrecDt grecDt);
	
	public String grecStnDtListing(Integer grecDtId, Boolean canViewFlag, Principal principal );
	
}

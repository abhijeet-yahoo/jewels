package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.WipStyle;

public interface IWipStyleService {

	public List<WipStyle> findByPartyAndDeactive(Party party, Boolean deactive);
	
	public WipStyle findByPartyAndDesignAndDeactive(Party party,Design design,  Boolean deactive);
	
	public void save(WipStyle wipstyle);

	public void delete(int id);

	public WipStyle findOne(int id);

	public String wipStyleListing(String partyCode,Principal principal);

}

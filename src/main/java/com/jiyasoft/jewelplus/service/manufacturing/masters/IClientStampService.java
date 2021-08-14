package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

public interface IClientStampService {
	

	public void save(ClientStamp clientStamp);

	public void delete(int id);

	public ClientStamp findOne(int id);


	public Page<ClientStamp> findByParty(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive,Party party);
	
	
	public String CheckDuplicate(Double fromCts, Double toCts, Integer id,Integer vPartyId);
	
	public List<ClientStamp> findByPartyAndFromCtsAndDeactive(Party party, Double fromCts, Boolean deactive);
	
	public String getStampName(Double carat,Integer vPartyId);
	
	public String CheckDuplicatePurityWise(Integer id,Integer vPartyId, Integer vPurityId);
	
	public ClientStamp findByPartyAndPurityAndDeactive(Party party, Purity purity, Boolean deactive);
	
	public ClientStamp findByStampNmAndDeactive(String name, Boolean deactive);

}

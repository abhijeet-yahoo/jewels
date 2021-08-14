package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;


public interface IHandlingMasterFLService {

	List<HandlingMasterFl> findByPartyAndFromDiaRateAndDeactive(Party party, Double fromDiaRate, Boolean deactive);

	public void save(HandlingMasterFl handlingMasterFl);

	public void delete(int id);

	public HandlingMasterFl findOne(int id);


	public Page<HandlingMasterFl> findByParty(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive,Party party);
	
	
	public String CheckDuplicate(Double fromDiaRate, Double toDiaRate, Integer id,Integer vPartyId);
	
	
	public List<HandlingMasterFl> getRates(Party party,Double stoneRate);
}

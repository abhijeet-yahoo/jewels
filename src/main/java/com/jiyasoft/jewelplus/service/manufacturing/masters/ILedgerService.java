package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;



public interface ILedgerService {
	
	public List<Party> findAll();
	
	public void save(Party party);
	
	public void delete(int id);
	
	public Party findOne(int id);
	
	public Party findByNameAndDeactive(String name, Boolean deactive);
	
	public Map<Integer, String> getPartyListForPurchase();
	
	public Page<Party> findActivePartySortByNameForPurchase();
	
	//for SaleMt
	
	 public Map<Integer, String> getPartyListForSale();
	
	public Page<Party> findActivePartySortByNameForSale();
	
	//---for universal Party(Party) List
	
	public Map<Integer, String> getPartyList(String partyGroupNms,String partyType);
	
	public Page<Party> findActivePartySortByName(String partyGroupNms,String partyType);
	
	
	public List<Object[]> getPartyListValueByQuery(String customQuery,String tranType,String partyGroup);
	
	public Page<Party> findActivePartySortByName();
	
    
  //new Search method
	
  	public Page<Party> searchAll(Integer limit, Integer offset, String sort,
  			String order, String search, Boolean onlyActive);
  	
  	public Party findByMailingNameAndDeactive(String mailingNm, Boolean deactive);
  	
  	public String partySave(Party party,Integer id,Principal principal,HttpSession httpSession);
  	
  	public Map<Integer, String> getTransporterList();
	
	public Page<Party> findActiveTransporter();
	
	
	public Page<Party> getPartyAuotFillByTranSetting(Integer limit, Integer offset, String sort,
			String order, String search,  String partyType,String partyGroup,Boolean onlyActive);
	
	public List<Party>autoCompletePartyList(String partyNm); 
	
	public Party findByPartyCodeAndDeactive(String code, Boolean deactive);
	
	public List<Party> findDistinctByCity(String cityNm);
	
	public Integer getMaxSrno(Integer partyGroupId);
	
}

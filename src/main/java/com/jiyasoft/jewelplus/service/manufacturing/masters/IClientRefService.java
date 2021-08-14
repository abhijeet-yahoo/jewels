package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientReference;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

public interface IClientRefService {
	
	
	public List<ClientReference> findAll();

	public Page<ClientReference> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(ClientReference clientReference);

	public String delete(int id);

	public Long count();
	
	public ClientReference findOne(int id);

	public List<ClientReference> findActiveClientReferences();

	public Long count(String colName, String colValue, Boolean onlyActive,Party party);

	public Page<ClientReference> findByParty(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive,Party party);
	
	public ClientReference findByPartyAndDesignAndPurityAndDeactive(Party party,Design design,Purity purity,Boolean deactive);
	
	
	

}

package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientKtConvMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

public interface IClientKtConvService {
	
	public void save(ClientKtConvMast clientKtConvMast);
	
	public void delete(int id);
	
	public List<ClientKtConvMast> findAll();
	
	public ClientKtConvMast findByPartyAndPurityAndDeactive(Party party, Purity purity, Boolean deactive);
	
	public Page<ClientKtConvMast> searchAll(Integer limit, Integer offset, String sort,
			String order, String name);

	public ClientKtConvMast findOne(int id);

}

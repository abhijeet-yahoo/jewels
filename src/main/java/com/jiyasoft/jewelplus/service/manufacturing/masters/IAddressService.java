package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Address;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;


public interface IAddressService {

	public List<Address> findAll();

	public Page<Address> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Address address);

	public void delete(int id);

	public Long count();
	
	public Address findOne(int id);

	public List<Address> findByParty(Party party);
}

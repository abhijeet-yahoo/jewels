package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Address;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IAddressRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IAddressService;


@Service
@Repository
@Transactional
public class AddressService implements IAddressService {

	@Autowired
	private IAddressRepository addressRepository;

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Page<Address> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return addressRepository.findAll(new PageRequest(page, limit,
				(order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), sort));
	}

	@Override
	public void save(Address address) {
		addressRepository.save(address);
	}

	@Override
	public void delete(int id) {
		Address address = addressRepository.findOne(id);
		address.setDeactive(true);
		address.setDeactiveDt(new java.util.Date());
		addressRepository.save(address);
	}

	@Override
	public Long count() {
		return addressRepository.count();
	}

	@Override
	public Address findOne(int id) {
		return addressRepository.findOne(id);
	}

	@Override
	public List<Address> findByParty(Party party) {
		List<Address> addressList = (List<Address>) addressRepository.findByParty(party);
		
		return addressList;
	}

}

package com.jiyasoft.jewelplus.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.IPAddressAccess;
import com.jiyasoft.jewelplus.repository.admin.IIPAddressAccessRepository;

@Service
@Repository
@Transactional
public class IPAddressAccessService {

	
	@Autowired
	private IIPAddressAccessRepository iPAddressAccessRepository;
	
	
	
	public IPAddressAccess findOne(Integer id){
		return iPAddressAccessRepository.findOne(id);
	}
	
	
	
	
}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Company;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ICompanyRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICompanyService;

@Service
@Repository
@Transactional
public class CompanyService implements ICompanyService {

	@Autowired
	private ICompanyRepository companyRepository;

	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}
	
}

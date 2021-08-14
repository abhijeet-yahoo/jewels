package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.CountryMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QCountryMaster;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ICountryMasterRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICountryService;
import com.mysema.query.types.expr.BooleanExpression;
@Service
@Repository
@Transactional
public class CountryService implements ICountryService {
	
	@Autowired
	private ICountryMasterRepository countryRepository;

	@Override
	public CountryMaster findOne(int id) {
		// TODO Auto-generated method stub
		return countryRepository.findOne(id);
	}

	@Override
	public List<CountryMaster> findByName(String name) {
		// TODO Auto-generated method stub
		return countryRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getCountryList() {
		
		Map<Integer, String> countryMap = new LinkedHashMap<Integer, String>();
		List<CountryMaster> countryList = (List<CountryMaster>) findActiveCountry();
		for (CountryMaster countryMaster : countryList) {
			countryMap.put(countryMaster.getId(), countryMaster.getName());
		}
		return countryMap;
	}

	
	@Override
	public Page<CountryMaster> countryAutoFillList(Integer limit, Integer offset, String sort,String order, String search,
			Boolean onlyActive) {

		QCountryMaster qCountryMaster = QCountryMaster.countryMaster;
		BooleanExpression expression= qCountryMaster.deactive.eq(false).and(qCountryMaster.name.like("%" + search + "%"));
	
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "name";
		}

		Page<CountryMaster> countryList = (Page<CountryMaster>) countryRepository
				.findAll(expression,new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC), sort));

		return countryList;
	}

	@Override
	public List<CountryMaster> findActiveCountry() {
		QCountryMaster qCountryMaster = QCountryMaster.countryMaster;
		BooleanExpression expression = qCountryMaster.deactive.eq(false);
		List<CountryMaster> categoryList = (List<CountryMaster>) countryRepository.findAll(expression);
		return categoryList;
	}

	@Override
	public CountryMaster findByNameAndDeactive(String name, Boolean deactive) {
		// TODO Auto-generated method stub
		return countryRepository.findByNameAndDeactive(name, deactive);
	}


	@Override
	public Map<Integer, String> getCountryWiseCurrencyList() {
		// TODO Auto-generated method stub
		Map<Integer, String> countryWiseCurrencyMap = new LinkedHashMap<Integer, String>();
		List<CountryMaster> countryList = (List<CountryMaster>) findActiveCountry();
		for (CountryMaster countryMaster : countryList) {
			
			if(countryMaster.getCurrencySymbol()!= null) {
				countryWiseCurrencyMap.put(countryMaster.getId(), countryMaster.getName()+"  "+countryMaster.getCurrencySymbol());}
			
		}
		return countryWiseCurrencyMap;
	}

	
	
}

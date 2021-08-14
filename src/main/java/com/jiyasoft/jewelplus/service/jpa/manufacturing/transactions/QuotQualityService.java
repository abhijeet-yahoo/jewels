package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IQuotQualityRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotQualityService;

@Service
@Transactional
@Repository
public class QuotQualityService implements IQuotQualityService{

	@Autowired
	private IQuotQualityRepository quotQualityRepository;
	
	
	@Override
	public List<QuotQuality> findAll() {
		return quotQualityRepository.findAll();
	}

	@Override
	public Page<QuotQuality> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		return null;
	}

	@Override
	public void save(QuotQuality quotQuality) {
		quotQualityRepository.save(quotQuality);
	}

	@Override
	public void delete(int id) {
		QuotQuality quotQuality = quotQualityRepository.findOne(id);
		quotQuality.setDeactive(true);
		quotQuality.setDeactiveDt(new java.util.Date());
		quotQualityRepository.save(quotQuality);
	}

	@Override
	public Long count() {
		return quotQualityRepository.count();
	}

	@Override
	public QuotQuality findOne(int id) {
		return quotQualityRepository.findOne(id);
	}

	@Override
	public List<QuotQuality> findByQuotMtAndDeactive(QuotMt quotMt,
			Boolean deactive) {
		return quotQualityRepository.findByQuotMtAndDeactive(quotMt, deactive);
	}	
	
}

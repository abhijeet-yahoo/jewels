package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IKtConversionDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IKtConversionDtService;

@Service
@Repository
@Transactional
public class KtConversionDtService implements IKtConversionDtService{
	
	@Autowired
	private IKtConversionDtRepository ktConversionDtRepository;

	@Override
	public List<KtConversionDt> findAll() {
		return ktConversionDtRepository.findAll();
	}

	@Override
	public Page<KtConversionDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(KtConversionDt ktConversionDt) {
		ktConversionDtRepository.save(ktConversionDt);
	}

	@Override
	public void delete(int id) {
		KtConversionDt ktConversionDt = ktConversionDtRepository.findOne(id);
		ktConversionDt.setDeactive(true);
		ktConversionDt.setDeactiveDt(new java.util.Date());
		ktConversionDtRepository.save(ktConversionDt);
	}

	@Override
	public Long count() {
		return ktConversionDtRepository.count();
	}

	@Override
	public KtConversionDt findOne(int id) {
		return ktConversionDtRepository.findOne(id);
	}

	@Override
	public List<KtConversionDt> findByKtConversionMtAndDeactive(
			KtConversionMt ktConversionMt, Boolean deactive) {
		return ktConversionDtRepository.findByKtConversionMtAndDeactive(ktConversionMt, deactive);
	}

	@Override
	public KtConversionDt findByUniqueId(Long uniqueId) {
		return ktConversionDtRepository.findByUniqueId(uniqueId);
	}

}

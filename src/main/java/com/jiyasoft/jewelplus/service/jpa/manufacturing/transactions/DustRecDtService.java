package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustRecDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IDustRecDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustRecDtService;

@Service
@Repository
@Transactional
public class DustRecDtService implements IDustRecDtService{

	@Autowired
	private IDustRecDtRepository dustRecDtRepository;

	@Override
	public DustRecDt findOne(Integer id) {
		return dustRecDtRepository.findOne(id);
	}

	@Override
	public List<DustRecDt> findAll() {
		return dustRecDtRepository.findAll();
	}

	@Override
	public Page<DustRecDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		DustRecDt dustRecDt = dustRecDtRepository.findOne(id);
		dustRecDt.setDeactive(true);
		dustRecDt.setDeactiveDt(new java.util.Date());
		dustRecDtRepository.save(dustRecDt);
		
	}

	@Override
	public void save(DustRecDt dustRecDt) {
		dustRecDtRepository.save(dustRecDt);
		
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		return dustRecDtRepository.count();
	}

	@Override
	public List<DustRecDt> findByDustMtAndDeactive(DustMt dustMt,
			Boolean deactive) {
		return dustRecDtRepository.findByDustMtAndDeactive(dustMt, false);
	}

	@Override
	public DustRecDt findByUniqueId(Long uniqueId) {
		return dustRecDtRepository.findByUniqueId(uniqueId);
	}
	
	
	
	
}

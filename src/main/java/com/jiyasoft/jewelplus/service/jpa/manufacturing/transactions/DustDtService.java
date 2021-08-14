package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IDustDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustDtService;

@Service
@Repository
@Transactional
public class DustDtService implements IDustDtService {
	
	@Autowired
	private IDustDtRepository dustDtRepository;

	@Override
	public DustDt findOne(Integer id) {
		return dustDtRepository.findOne(id);
	}

	@Override
	public List<DustDt> findAll() {
		return dustDtRepository.findAll();
	}

	@Override
	public Page<DustDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		DustDt dustDt = dustDtRepository.findOne(id);
		dustDt.setDeactive(true);
		dustDt.setDeactiveDt(new java.util.Date());
		dustDtRepository.save(dustDt);
		
	}

	@Override
	public void save(DustDt dustMt) {
		dustDtRepository.save(dustMt);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		return null;
	}
	
	
	@Override
	public Long count() {
		return dustDtRepository.count();
	}
	
	@Override
	public List<DustDt> findByDustMtAndDeactive(DustMt dustMt, Boolean deactive) {
		return dustDtRepository.findByDustMtAndDeactive(dustMt, deactive);
	}
	
	

}

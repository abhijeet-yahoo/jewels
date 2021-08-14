package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICastingCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingCompDtService;

@Service
@Repository
@Transactional
public class CastingCompDtService implements ICastingCompDtService {

	@Autowired
	private ICastingCompDtRepository castingCompDtRepository;

	@Override
	public List<CastingCompDt> findAll() {
		return castingCompDtRepository.findAll();
	}

	@Override
	public Page<CastingCompDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CastingCompDt castingCompDt) {
		castingCompDtRepository.save(castingCompDt);

	}

	@Override
	public void delete(int id) {
		
		CastingCompDt castingCompDt = castingCompDtRepository.findOne(id);
		castingCompDt.setDeactive(true);
		castingCompDt.setDeactiveDt(new java.util.Date());
		castingCompDtRepository.save(castingCompDt);

	}

	@Override
	public Long count() {
		return castingCompDtRepository.count();
	}

	@Override
	public CastingCompDt findOne(int id) {
		return castingCompDtRepository.findOne(id);
	}

	@Override
	public List<CastingCompDt> findByCastingMtAndDeactive(CastingMt castingMt, Boolean deactive) {
		return castingCompDtRepository.findByCastingMtAndDeactive(castingMt, deactive);
	}

}

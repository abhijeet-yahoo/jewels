package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddMetalDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVaddMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVaddMetalDtService;

@Service
@Repository
@Transactional
public class VaddMetalDtService implements IVaddMetalDtService{

	@Autowired
	private IVaddMetalDtRepository vaddMetalDtRepository;
	
	

	@Override
	public void save(VaddMetalDt vaddMetalDt) {
		// TODO Auto-generated method stub
		vaddMetalDtRepository.save(vaddMetalDt);
	}


	@Override
	public void delete(int id) {
		VaddMetalDt vaddMetalDt = vaddMetalDtRepository.findOne(id);
		vaddMetalDt.setDeactive(true);
		vaddMetalDt.setDeactiveDt(new java.util.Date());
		vaddMetalDtRepository.save(vaddMetalDt);
		
	}



	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return vaddMetalDtRepository.count();
	}



	@Override
	public VaddMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return vaddMetalDtRepository.findOne(id);
	}



	@Override
	public List<VaddMetalDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<VaddMetalDt> findByVaddDtAndDeactive(VAddDt vAddDt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return vaddMetalDtRepository.findByVAddDtAndDeactive(vAddDt, deactive);
	}



	@Override
	public List<VaddMetalDt> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public VaddMetalDt findByVAddDtAndDeactiveAndPartNm(VAddDt vAddDt,
			Boolean deactive, LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return vaddMetalDtRepository.findByVAddDtAndDeactiveAndPartNm(vAddDt, deactive, lookUpMast);
	}

}

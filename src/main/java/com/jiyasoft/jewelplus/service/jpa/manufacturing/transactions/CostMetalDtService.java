package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtService;

@Service
@Repository
@Transactional
public class CostMetalDtService implements ICostMetalDtService{

	@Autowired
	private ICostMetalDtRepository costMetalDtRepository;
	
	

	@Override
	public void save(CostMetalDt costMetalDt) {
		// TODO Auto-generated method stub
		costMetalDtRepository.save(costMetalDt);
	}




	@Override
	public void delete(int id) {
		
		CostMetalDt costMetalDt = costMetalDtRepository.findOne(id);
		costMetalDt.setDeactive(true);
		costMetalDt.setDeactiveDt(new java.util.Date());
		costMetalDtRepository.save(costMetalDt);
	}



	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return costMetalDtRepository.count();
	}



	@Override
	public CostMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return costMetalDtRepository.findOne(id);
	}



	@Override
	public List<CostMetalDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return costMetalDtRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}



	@Override
	public List<CostMetalDt> findByCostingDtAndDeactive(CostingDt costingDt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return costMetalDtRepository.findByCostingDtAndDeactive(costingDt, deactive);
	}



	@Override
	public List<CostMetalDt> findAll() {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public CostMetalDt findByCostingDtAndDeactiveAndMainMetal(
			CostingDt costingDt, Boolean deactive, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return costMetalDtRepository.findByCostingDtAndDeactiveAndMainMetal(costingDt, deactive, mainMetal);
	}







	@Override
	public CostMetalDt findByCostingDtAndDeactiveAndPartNm(CostingDt costingDt,
			Boolean deactive, LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return costMetalDtRepository.findByCostingDtAndDeactiveAndPartNm(costingDt, deactive, lookUpMast);
	}

}

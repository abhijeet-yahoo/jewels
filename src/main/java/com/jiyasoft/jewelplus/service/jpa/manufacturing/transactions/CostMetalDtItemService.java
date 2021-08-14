package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostMetalDtItemRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtItemService;

@Service
@Repository
@Transactional
public class CostMetalDtItemService implements ICostMetalDtItemService {
	
	@Autowired
	private ICostMetalDtItemRepository costMetalDtItemRepository;

	@Override
	public List<CostMetalDtItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CostMetalDtItem costMetalDtItem) {
		
		costMetalDtItemRepository.save(costMetalDtItem);
		
	}

	@Override
	public void delete(int id) {
		CostMetalDtItem costMetalDtItem = costMetalDtItemRepository.findOne(id);
		costMetalDtItem.setDeactive(true);
		costMetalDtItem.setDeactiveDt(new Date());
		costMetalDtItemRepository.save(costMetalDtItem);
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostMetalDtItem findOne(int id) {
		// TODO Auto-generated method stub
		return costMetalDtItemRepository.findOne(id);
	}

	@Override
	public List<CostMetalDtItem> findByCostingMtAndDeactive(
			CostingMt costingMt, Boolean deactive) {
		
		return costMetalDtItemRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public List<CostMetalDtItem> findByCostingDtItemAndDeactive(
			CostingDtItem costingDtItem, Boolean deactive) {
		
		return costMetalDtItemRepository.findByCostingDtItemAndDeactive(costingDtItem, deactive);
	}

	@Override
	public CostMetalDtItem findByCostingDtItemAndDeactiveAndMainMetal(
			CostingDtItem costingDtItem, Boolean deactive, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return costMetalDtItemRepository.findByCostingDtItemAndDeactiveAndMainMetal(costingDtItem, deactive, mainMetal);
	}

	@Override
	public CostMetalDtItem findByCostingDtItemAndDeactiveAndPartNm(
			CostingDtItem costingDtItem, Boolean deactive, LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return costMetalDtItemRepository.findByCostingDtItemAndDeactiveAndPartNm(costingDtItem, deactive, lookUpMast);
	}

}

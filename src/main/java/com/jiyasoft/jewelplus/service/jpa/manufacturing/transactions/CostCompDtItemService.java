package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostCompDtItemRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtItemService;

@Service
@Repository
@Transactional
public class CostCompDtItemService implements ICostCompDtItemService {
	
	@Autowired
	private ICostCompDtItemRepository compDtItemRepository;

	@Override
	public List<CostCompDtItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CostCompDtItem costCompDtItem) {
		// TODO Auto-generated method stub
		compDtItemRepository.save(costCompDtItem);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		CostCompDtItem costCompDtItem = compDtItemRepository.findOne(id);
		costCompDtItem.setDeactive(true);
		costCompDtItem.setDeactiveDt(new Date());
		compDtItemRepository.save(costCompDtItem);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostCompDtItem findOne(int id) {
		// TODO Auto-generated method stub
		return compDtItemRepository.findOne(id);
	}

	@Override
	public List<CostCompDtItem> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return compDtItemRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public List<CostCompDtItem> findByCostingDtItemAndDeactive(
			CostingDtItem costingDtItem, Boolean deactive) {
		// TODO Auto-generated method stub
		return compDtItemRepository.findByCostingDtItemAndDeactive(costingDtItem, deactive);
	}

	

	@Override
	public List<CostCompDtItem> getCostCompDtList(String itemNo,
			OrderDt orderDt, CostingMt costingMt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CostCompDtItem> findByItemNoAndCostingDtItemAndDeactive(
			String itemNo, CostingDtItem costingDtItem, Boolean deactive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lockUnlockCompDt(Integer CostMtId, Boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItemNo(Integer costDtId, String itemNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CostCompDtItem findByCostingDtItemAndComponentAndPurityAndColor(CostingDtItem costingDtItem, Component component, Purity purity,Color color) {
		// TODO Auto-generated method stub
		return compDtItemRepository.findByCostingDtItemAndComponentAndPurityAndColorAndDeactive(costingDtItem, component, purity, color,false);
	}
	

}

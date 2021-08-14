package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.mysema.query.jpa.impl.JPAUpdateClause;

@Service
@Repository
@Transactional
public class CostStnDtService implements ICostStnDtService{
	
	@Autowired
	private ICostStnDtRepository costStnDtRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CostStnDt> findAll() {
		return costStnDtRepository.findAll();
	}

	@Override
	public void save(CostStnDt costStnDt) {
		costStnDtRepository.save(costStnDt);
		
	}

	@Override
	public void delete(int id) {
		CostStnDt costStnDt = costStnDtRepository.findOne(id);
		costStnDt.setDeactive(true);
		costStnDt.setDeactiveDt(new java.util.Date());
		costStnDtRepository.save(costStnDt);
		
	}

	@Override
	public Long count() {
		return costStnDtRepository.count();
	}

	@Override
	public CostStnDt findOne(int id) {
		return costStnDtRepository.findOne(id);
	}
    
	@Override
	public List<CostStnDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		return costStnDtRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}
	
	@Override
	public List<CostStnDt> findByCostingDtAndDeactive(CostingDt costingDt,
			Boolean deactive) {
		return costStnDtRepository.findByCostingDtAndDeactive(costingDt, false);
	}

	@Override
	public List<CostStnDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(
			String itemNo, OrderDt orderDt, CostingMt costingMt,
			Boolean deactive) {
		return costStnDtRepository.findByItemNoAndOrderDtAndCostingMtAndDeactive(itemNo, orderDt, costingMt, deactive);
	}

	@Override
	public List<CostStnDt> findByItemNoAndCostingDtAndDeactive(String itemNo,
			CostingDt costingDt, Boolean deactive) {
		return costStnDtRepository.findByItemNoAndCostingDtAndDeactive(itemNo, costingDt, deactive);
	}

	
	@Override
	public void lockUnlockStnDt(Integer CostMtId, Boolean status) {
		QCostStnDt qCostStnDt = QCostStnDt.costStnDt;
		new JPAUpdateClause(entityManager, qCostStnDt).where(qCostStnDt.costingMt.id.eq(CostMtId))
		.set(qCostStnDt.rLock, status)
		.execute();
		
	}
	
	@Override
	public void updateItemNo(Integer costDtId, String itemNo) {
		QCostStnDt qCostStnDt = QCostStnDt.costStnDt;
		new JPAUpdateClause(entityManager, qCostStnDt).where(qCostStnDt.costingDt.id.eq(costDtId))
		.set(qCostStnDt.itemNo, itemNo)
		.execute();
	}


}

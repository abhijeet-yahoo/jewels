package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostCompDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;

@Service
@Repository
@Transactional
public class CostCompDtService implements ICostCompDtService {
	
	@Autowired
	private ICostCompDtRepository costCompDtRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CostCompDt> findAll() {
		return costCompDtRepository.findAll();
	}

	@Override
	public void save(CostCompDt costCompDt) {
		costCompDtRepository.save(costCompDt);
		
	}

	@Override
	public void delete(int id) {
		CostCompDt costCompDt = costCompDtRepository.findOne(id);
		costCompDt.setDeactive(true);
		costCompDt.setDeactiveDt(new java.util.Date());
		costCompDtRepository.save(costCompDt);
	}

	@Override
	public Long count() {
		return costCompDtRepository.count();
	}

	@Override
	public CostCompDt findOne(int id) {
		return costCompDtRepository.findOne(id);
	}

	@Override
	public List<CostCompDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		return costCompDtRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}
	
	
	@Override
	public List<CostCompDt> findByCostingDtAndDeactive(CostingDt costingDt,
			Boolean deactive) {
		return costCompDtRepository.findByCostingDtAndDeactive(costingDt, deactive);
	}
	

	@Override
	public List<CostCompDt> getCostCompDtList(String itemNo, OrderDt orderDt,
			CostingMt costingMt) {
		
		QCostCompDt qCostCompDt = QCostCompDt.costCompDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<CostCompDt> costCompDtList = null;
		
		costCompDtList = query.from(qCostCompDt)
						.where(qCostCompDt.itemNo.eq(itemNo).and(qCostCompDt.orderDt.eq(orderDt)).and(qCostCompDt.costingMt.eq(costingMt)).and(qCostCompDt.deactive.eq(false)))
						.groupBy(qCostCompDt.purity,qCostCompDt.color,qCostCompDt.component,qCostCompDt.compRate)
						.list(qCostCompDt);
		
		
		return costCompDtList;
	}

	@Override
	public List<CostCompDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(
			String itemNo, OrderDt orderDt, CostingMt costingMt,
			Boolean deactive) {
		
		return costCompDtRepository.findByItemNoAndOrderDtAndCostingMtAndDeactive(itemNo, orderDt, costingMt, deactive);
	}

	@Override
	public List<CostCompDt> findByItemNoAndCostingDtAndDeactive(String itemNo,
			CostingDt costingDt, Boolean deactive) {
		return costCompDtRepository.findByItemNoAndCostingDtAndDeactive(itemNo, costingDt, deactive);
	}
	
	@Override
	public void lockUnlockCompDt(Integer CostMtId, Boolean status) {
		QCostCompDt qCostCompDt = QCostCompDt.costCompDt;
		new JPAUpdateClause(entityManager, qCostCompDt).where(qCostCompDt.costingMt.id.eq(CostMtId))
		.set(qCostCompDt.rLock, status)
		.execute();
	}
	
	
	@Override
	public void updateItemNo(Integer costDtId, String itemNo) {
		QCostCompDt qCostCompDt = QCostCompDt.costCompDt;
		new JPAUpdateClause(entityManager, qCostCompDt).where(qCostCompDt.costingDt.id.eq(costDtId))
		.set(qCostCompDt.itemNo, itemNo)
		.execute();
	}

	
	/*@Override
	public List<CostCompDt> getCostCompDtListFromCompTran(Integer bagId) {
		
		List<CostCompDt> costCompDts = null;
		
		@SuppressWarnings("unchecked")
		TypedQuery<CostCompDt> query = (TypedQuery<CostCompDt>)entityManager.createNativeQuery("{ CALL jsp_addcostcomp(?) }", CostCompDt.class);
		query.setParameter(1, 224873);
		
		@SuppressWarnings("unchecked")
		TypedQuery<CostCompDt> query = (TypedQuery<CostCompDt>) entityManager.createNativeQuery("{ call jsp_addcostcomp(?) }",CostCompDt.class);
		query.setParameter(1, 224873);
		costCompDts = query.getResultList();
		
		
		
		//System.out.println(i);
		
		System.out.println("/////-----------------------------");
			// costCompDts = query.getResultList();
		System.out.println("/////--------end---------------------");
		
		return costCompDts;
	}*/
	

}

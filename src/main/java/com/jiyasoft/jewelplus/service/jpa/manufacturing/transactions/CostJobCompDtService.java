package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostJobCompDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostJobCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobCompDtService;
import com.mysema.query.jpa.impl.JPAQuery;


@Service
@Repository
@Transactional
public class CostJobCompDtService implements ICostJobCompDtService{

	
	@Autowired
	private ICostJobCompDtRepository costJobCompDtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public List<CostJobCompDt> findAll() {
		return costJobCompDtRepository.findAll();
	}

	@Override
	public void save(CostJobCompDt costJobCompDt) {
		costJobCompDtRepository.save(costJobCompDt);
		
	}

	@Override
	public void delete(int id) {
		CostJobCompDt costJobCompDt = costJobCompDtRepository.findOne(id);
		costJobCompDt.setDeactive(true);
		costJobCompDt.setDeactiveDt(new java.util.Date());
		costJobCompDtRepository.save(costJobCompDt);
	}

	@Override
	public Long count() {
		return costJobCompDtRepository.count();
	}

	@Override
	public CostJobCompDt findOne(int id) {
		return costJobCompDtRepository.findOne(id);
	}

	@Override
	public List<CostJobCompDt> findByCostingJobMtAndDeactive(
			CostingJobMt costingJobMt, Boolean deactive) {
		return costJobCompDtRepository.findByCostingJobMtAndDeactive(costingJobMt, deactive);
	}

	@Override
	public List<CostJobCompDt> findByCostingJobDtAndDeactive(
			CostingJobDt costingJobDt, Boolean deactive) {
		return costJobCompDtRepository.findByCostingJobDtAndDeactive(costingJobDt, deactive);
	}

	@Override
	public List<CostJobCompDt> findByItemNoAndOrderDtAndCostingJobMtAndDeactive(
			String itemNo, OrderDt orderDt, CostingJobMt costingJobMt,
			Boolean deactive) {
		return costJobCompDtRepository.findByItemNoAndOrderDtAndCostingJobMtAndDeactive(itemNo, orderDt, costingJobMt, deactive);
	}

	@Override
	public List<CostJobCompDt> getCostJobCompDtList(String itemNo,
			OrderDt orderDt, CostingJobMt costingJobMt) {
		QCostJobCompDt qCostJobCompDt = QCostJobCompDt.costJobCompDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<CostJobCompDt> costJobCompDtList = null;
		
		costJobCompDtList = query.from(qCostJobCompDt)
						.where(qCostJobCompDt.itemNo.eq(itemNo).and(qCostJobCompDt.orderDt.eq(orderDt)).and(qCostJobCompDt.costingJobMt.eq(costingJobMt)).and(qCostJobCompDt.deactive.eq(false)))
						.groupBy(qCostJobCompDt.purity,qCostJobCompDt.color,qCostJobCompDt.component,qCostJobCompDt.compRate)
						.list(qCostJobCompDt);
		
		
		return costJobCompDtList;
	}

}

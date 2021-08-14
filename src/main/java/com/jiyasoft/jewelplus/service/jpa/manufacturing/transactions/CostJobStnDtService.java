package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostJobStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobStnDtService;


@Service
@Repository
@Transactional
public class CostJobStnDtService implements ICostJobStnDtService {

	
	@Autowired
	private ICostJobStnDtRepository costJobStnDtRepository;
	
	
	@Override
	public List<CostJobStnDt> findAll() {
		return costJobStnDtRepository.findAll();
	}

	@Override
	public void save(CostJobStnDt costJobStnDt) {
		costJobStnDtRepository.save(costJobStnDt);
		
	}

	@Override
	public void delete(int id) {
		CostJobStnDt costJobStnDt = costJobStnDtRepository.findOne(id);
		costJobStnDt.setDeactive(true);
		costJobStnDt.setDeactiveDt(new java.util.Date());
		costJobStnDtRepository.save(costJobStnDt);
		
	}

	@Override
	public Long count() {
		return costJobStnDtRepository.count();
	}

	@Override
	public CostJobStnDt findOne(int id) {
		return costJobStnDtRepository.findOne(id);
	}

	@Override
	public List<CostJobStnDt> findByCostingJobMtAndDeactive(
			CostingJobMt costingJobMt, Boolean deactive) {
		return costJobStnDtRepository.findByCostingJobMtAndDeactive(costingJobMt, deactive);
	}

	@Override
	public List<CostJobStnDt> findByCostingJobDtAndDeactive(
			CostingJobDt costingJobDt, Boolean deactive) {
		return costJobStnDtRepository.findByCostingJobDtAndDeactive(costingJobDt, false);
	}

	@Override
	public List<CostJobStnDt> findByItemNoAndOrderDtAndCostingJobMtAndDeactive(
			String itemNo, OrderDt orderDt, CostingJobMt costingJobMt,
			Boolean deactive) {
		return costJobStnDtRepository.findByItemNoAndOrderDtAndCostingJobMtAndDeactive(itemNo, orderDt, costingJobMt, deactive);

	}

}

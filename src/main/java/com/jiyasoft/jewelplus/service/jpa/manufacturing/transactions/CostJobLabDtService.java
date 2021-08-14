package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostJobLabDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobLabDtService;


@Service
@Repository
@Transactional
public class CostJobLabDtService implements ICostJobLabDtService {
    
	
	@Autowired
	private ICostJobLabDtRepository costJobLabDtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<CostJobLabDt> findAll() {
		return costJobLabDtRepository.findAll();
	}

	@Override
	public void save(CostJobLabDt costJobLabDt) {
		costJobLabDtRepository.save(costJobLabDt);
		
	}

	@Override
	public void delete(int id) {
		CostJobLabDt costJobLabDt = costJobLabDtRepository.findOne(id);
		costJobLabDt.setDeactive(true);
		costJobLabDt.setDeactiveDt(new java.util.Date());
		costJobLabDtRepository.save(costJobLabDt);
	}

	@Override
	public Long count() {
		return costJobLabDtRepository.count();
	}

	@Override
	public CostJobLabDt findOne(int id) {
		return costJobLabDtRepository.findOne(id);
	}

	@Override
	public List<CostJobLabDt> findByCostingJobMtAndDeactive(
			CostingJobMt costingJobMt, Boolean deactive) {
		return costJobLabDtRepository.findByCostingJobMtAndDeactive(costingJobMt, deactive);
	}

	@Override
	public List<CostJobLabDt> findByCostingJobDtAndDeactive(
			CostingJobDt costingJobDt, Boolean deactive) {
		return costJobLabDtRepository.findByCostingJobDtAndDeactive(costingJobDt, deactive);
	}

	@Override
	public int labAsPerMaster(Integer costJobMtId) {
		Query query = entityManager.createNativeQuery("{ CALL jsp_updatecostingjoblabour(?) }");
		query.setParameter(1, costJobMtId);
		int retval = query.executeUpdate();

	return retval;
	}

}

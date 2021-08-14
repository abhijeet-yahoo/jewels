package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QVAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompDtService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class VAddCompDtService implements IVAddCompDtService {

	@Autowired
	private IVAddCompDtRepository vAddCompDtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	
	@Override
	public void save(VAddCompDt vAddCompDt) {
		vAddCompDtRepository.save(vAddCompDt);
		
	}

	@Override
	public void delete(int id) {
		VAddCompDt vAddCompDt = vAddCompDtRepository.findOne(id);
		vAddCompDt.setDeactive(true);
		vAddCompDt.setDeactiveDt(new java.util.Date());
		vAddCompDtRepository.save(vAddCompDt);
	}

	@Override
	public VAddCompDt findOne(int id) {
		return vAddCompDtRepository.findOne(id);
	}

	@Override
	public Long count() {
		return vAddCompDtRepository.count();
	}

	@Override
	public List<VAddCompDt> findByVAddDtAndDeactive(VAddDt vAddDt,
			Boolean deactive) {
		return vAddCompDtRepository.findByVAddDtAndDeactive(vAddDt, deactive);
	}

	
	@Override
	public List<VAddCompDt> getComponentStockListing(CostingMt costingMt,List<Component> component) {
		
		
		QVAddCompDt qvAddCompDt = QVAddCompDt.vAddCompDt;
		JPAQuery query = new JPAQuery(entityManager);
		List<VAddCompDt> vAddCompDt = null;
		
		vAddCompDt = query.from(qvAddCompDt)
					.where(qvAddCompDt.costingMt.eq(costingMt).and(qvAddCompDt.component.in(component)).and(qvAddCompDt.deactive.eq(false)))
					.groupBy(qvAddCompDt.component,qvAddCompDt.purity,qvAddCompDt.color)
					.list(qvAddCompDt);
	
		
		return vAddCompDt;
	}
	

	@Override
	public List<VAddCompDt> findByComponentAndPurityAndColor(
			Component component, Purity purity, Color color) {
		return vAddCompDtRepository.findByComponentAndPurityAndColor(component, purity, color);
	}

	@Override
	public List<VAddCompDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		return vAddCompDtRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public void saveAll(List<VAddDt> vaddDt) {
		//vAddCompDtRepository.save(vaddDt);
		
	}

	
	
	
}

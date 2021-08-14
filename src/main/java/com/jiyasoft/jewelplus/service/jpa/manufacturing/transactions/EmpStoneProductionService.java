package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpStoneProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IEmpStoneProductionRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpStoneProductionService;

@Service
@Repository
@Transactional
public class EmpStoneProductionService implements IEmpStoneProductionService {

	@Autowired
	private IEmpStoneProductionRepository empStoneProductionRepository;

	@Override
	public List<EmpStoneProduction> findAll() {
		return empStoneProductionRepository.findAll();
	}

	@Override
	public Page<EmpStoneProduction> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return empStoneProductionRepository.findAll(new PageRequest(page,
				limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(EmpStoneProduction employeeStoneProduction) {
		empStoneProductionRepository.save(employeeStoneProduction);

	}

	@Override
	public void delete(int id) {
		EmpStoneProduction empStoneProduction = empStoneProductionRepository.findOne(id);
		empStoneProduction.setDeactive(true);
		empStoneProduction.setDeactiveDt(new java.util.Date());
		empStoneProductionRepository.save(empStoneProduction);

	}

	@Override
	public Long count() {
		return empStoneProductionRepository.count();
	}

	@Override
	public EmpStoneProduction findOne(int id) {
		return empStoneProductionRepository.findOne(id);
	}

	@Override
	public List<EmpStoneProduction> findByDepartmentAndBagMtAndDeactive(
			Department department, BagMt bagMt, Boolean deactive) {
		return empStoneProductionRepository.findByDepartmentAndBagMtAndDeactive(department, bagMt, deactive);
	}

	@Override
	public List<EmpStoneProduction> findByTranMtAndDeactive(TranMt tranMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return empStoneProductionRepository.findByTranMtAndDeactive(tranMt, deactive);
	}
	
	
	

}

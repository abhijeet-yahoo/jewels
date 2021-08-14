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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPcsProduction;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IEmpPcsProductionRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpPcsProductionService;

@Service
@Repository
@Transactional
public class EmpPcsProductionService implements IEmpPcsProductionService {

	@Autowired
	private IEmpPcsProductionRepository empPcsProductionRepository;

	@Override
	public List<EmpPcsProduction> findAll() {
		return empPcsProductionRepository.findAll();
	}

	@Override
	public Page<EmpPcsProduction> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return empPcsProductionRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(EmpPcsProduction employeePcsProduction) {
		empPcsProductionRepository.save(employeePcsProduction);
	}

	@Override
	public void delete(int id) {
		EmpPcsProduction empPcsProduction = empPcsProductionRepository
				.findOne(id);
		empPcsProduction.setDeactive(true);
		empPcsProduction.setDeactiveDt(new java.util.Date());
		empPcsProductionRepository.save(empPcsProduction);

	}

	@Override
	public Long count() {
		return empPcsProductionRepository.count();
	}

	@Override
	public EmpPcsProduction findOne(int id) {
		return empPcsProductionRepository.findOne(id);
	}

	@Override
	public List<EmpPcsProduction> findByDepartmentAndBagMtAndDeactive(
			Department department, BagMt bagMt, Boolean deactive) {
		return empPcsProductionRepository.findByDepartmentAndBagMtAndDeactive(department, bagMt, deactive);
	}

	
	

}

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPdProduction;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IEmpPdProductionRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpPdProductionService;


@Service
@Repository
@Transactional
public class EmpPdProductionService implements IEmpPdProductionService {

	@Autowired
	private IEmpPdProductionRepository empPdProductionRepository;
	
	@Override
	public List<EmpPdProduction> findAll() {
		return empPdProductionRepository.findAll();
	}

	@Override
	public Page<EmpPdProduction> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return empPdProductionRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(EmpPdProduction employeePdProduction) {
		empPdProductionRepository.save(employeePdProduction);
		
	}

	@Override
	public void delete(int id) {
		EmpPdProduction empPdProduction = empPdProductionRepository
				.findOne(id);
		empPdProduction.setDeactive(true);
		empPdProduction.setDeactiveDt(new java.util.Date());
		empPdProductionRepository.save(empPdProduction);

	}

	@Override
	public Long count() {
		return empPdProductionRepository.count();
	}

	@Override
	public EmpPdProduction findOne(int id) {
		return empPdProductionRepository.findOne(id);
	}

	@Override
	public List<EmpPdProduction> findByDepartmentAndStyleNoAndDeactive(
			Department department, Design design, Boolean deactive) {
		return empPdProductionRepository.findByDepartmentAndDesignAndDeactive(department, design, deactive);
	}

}

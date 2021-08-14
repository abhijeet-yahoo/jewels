package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;
import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPdProduction;

public interface IEmpPdProductionService {

	public List<EmpPdProduction> findAll();

	public Page<EmpPdProduction> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(EmpPdProduction employeePdProduction);

	public void delete(int id);

	public Long count();

	public EmpPdProduction findOne(int id);
	
	public List<EmpPdProduction> findByDepartmentAndStyleNoAndDeactive(Department department,Design design,Boolean deactive);
}

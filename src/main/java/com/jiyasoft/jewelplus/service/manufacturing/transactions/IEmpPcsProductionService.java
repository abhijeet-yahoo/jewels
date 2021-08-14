package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPcsProduction;

public interface IEmpPcsProductionService {

	public List<EmpPcsProduction> findAll();

	public Page<EmpPcsProduction> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(EmpPcsProduction employeePcsProduction);

	public void delete(int id);

	public Long count();

	public EmpPcsProduction findOne(int id);
	
	public List<EmpPcsProduction> findByDepartmentAndBagMtAndDeactive(Department department,BagMt bagMt,Boolean deactive);

}

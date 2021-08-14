package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpStoneProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface IEmpStoneProductionService {
	
	public List<EmpStoneProduction> findAll();

	public Page<EmpStoneProduction> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(EmpStoneProduction employeeStoneProduction);

	public void delete(int id);

	public Long count();

	public EmpStoneProduction findOne(int id);
	
	public List<EmpStoneProduction> findByDepartmentAndBagMtAndDeactive(Department department,BagMt bagMt,Boolean deactive);
	
	
	public List<EmpStoneProduction>findByTranMtAndDeactive(TranMt tranMt,Boolean deactive);
	
}

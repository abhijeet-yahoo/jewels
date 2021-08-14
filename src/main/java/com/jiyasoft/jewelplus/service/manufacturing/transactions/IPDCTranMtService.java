package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;

public interface IPDCTranMtService {

    public List<PDCTranMt> findAll();
	
	public Page<PDCTranMt> findAll(Integer limit, Integer offset, String sort, String order, String search);

	public void save(PDCTranMt PDCTranMt);

	public void delete(int id);

	public Long count();

	public PDCTranMt findOne(int id);

	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public List<PDCTranMt> findBycurrentStk(Boolean status);
	
	public void save(PDCTranMt PDCTranMt,PDC pdc);
	
	public PDCTranMt findByDesignAndDepartmentAndCurrStk(Design design,Department department,Boolean currStk);
	
	public PDCTranMt findByDesignAndCurrStk(Design design,Boolean currStk);


}

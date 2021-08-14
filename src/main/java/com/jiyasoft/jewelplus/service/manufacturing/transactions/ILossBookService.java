package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LossBook;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface ILossBookService {
	
	public void save(LossBook lossBook);
	
	public void delete(int id);
	
	public String lossBookSave(LossBook lossBook,String empStoneData,Principal principal, Date tranDate);
	
	public List<LossBook> findByDepartmentAndBagMtAndDeactive(Department department,BagMt bagMt,Boolean deactive);
	
	public List<LossBook> findByTranMtAndDeactive(TranMt tranMt,Boolean deactive);
	
	public String getPartListForLossBook(Integer departmentId,String bagNo,String uploadFilePath);
	


}

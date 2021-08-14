package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;

public interface IVouIssCompDtService {

	public void save(VouIssCompDt vouIssCompDt);

	public void delete(int id);

	public VouIssCompDt findOne(int id);
	
	public List<VouIssCompDt> findByVouIssMtAndDeactive(VouIssMt vouIssMt,Boolean deactive);
	
	public List<VouIssCompDt> findByVouIssDtAndDeactive(VouIssDt vouIssDt,Boolean deactive);
	
	
}

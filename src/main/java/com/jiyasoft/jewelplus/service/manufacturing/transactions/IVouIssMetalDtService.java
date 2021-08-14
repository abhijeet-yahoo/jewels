package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;

public interface IVouIssMetalDtService {
	
	public void save(VouIssMetalDt vouIssMetalDt);
	
	public void delete(int id);
	
	public VouIssMetalDt findOne(int id);
	
	public List<VouIssMetalDt> findByVouIssMetalAndDeactive(VouIssMt vouIssMt, Boolean deactive );
	
	public List<VouIssMetalDt> findByVouIssDtAndDeactive(VouIssDt vouIssDt, Boolean deactive);
	
	public VouIssMetalDt findByVouIssDtAndDeactiveAndMainMetal(VouIssDt vouIssDt,Boolean deactive,Boolean mainMetal); 

	
}

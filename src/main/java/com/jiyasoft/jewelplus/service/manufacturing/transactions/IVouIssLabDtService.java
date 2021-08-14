package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;

public interface IVouIssLabDtService {


	public List<VouIssLabDt> findByVouIssMtAndDeactive(VouIssMt vouIssMt,Boolean deactive);
	
	public List<VouIssLabDt> findByVouIssDtAndDeactive(VouIssDt vouIssDt,Boolean deactive);
	
	public List<VouIssLabDt>findByVouIssDtAndMetalAndDeactive(VouIssDt VouIssDt,Metal metal,Boolean deactive );

	public void save(VouIssLabDt vouIssLabDt);

	public void delete(int id);

	public VouIssLabDt findOne(int id);
	
	
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssStnDt;

public interface IVouIssStnDtService {

	public void save(VouIssStnDt vouIssStnDt);
	
	public void delete(int id);
	
	public VouIssStnDt findOne(int id);
	
	public List<VouIssStnDt> findByVouIssMtAndDeactive(VouIssMt vouIssMt,Boolean deactive);
	
	public List<VouIssStnDt> findByVouIssDtAndDeactive(VouIssDt vouIssDt,Boolean deactive);
}

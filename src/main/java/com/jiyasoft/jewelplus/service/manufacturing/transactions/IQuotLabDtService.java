package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;


public interface IQuotLabDtService {
	
	public List<QuotLabDt> findAll();
	
	public void save(QuotLabDt quotLabDt);

	public void delete(int id);

	public Long count();

	public QuotLabDt findOne(int id);
	
	public List<QuotLabDt> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	public List<QuotLabDt> findByQuotDtAndDeactive(QuotDt quotDt,Boolean deactive);
	
	/*public void setQuotLabDt(QuotMt quotMt,QuotDt quotDt,Principal principal);*/
	
	public List<QuotLabDt>findByQuotDtAndMetalAndDeactive(QuotDt quotDt,Metal metal,Boolean deactive );
	
	public String transactionalSave(QuotLabDt quotLabDt,Integer id,Integer quotMtId,Integer quotDtId,Principal principal,Boolean netWtWithCompFlg);

	public void transactionalDelete(QuotLabDt quotLabDt,Boolean netWtWithCompFlg);

	
	
}

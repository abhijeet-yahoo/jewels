package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;


public interface IQuotCompDtService {
	
	public List<QuotCompDt> findAll();
	
	public void save(QuotCompDt quotCompDt);

	public void delete(int id);

	public Long count();

	public QuotCompDt findOne(int id);
	
	public List<QuotCompDt> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	public List<QuotCompDt> findByQuotDtAndDeactive(QuotDt quotDt,Boolean deactive);

	public void setQuotCompDt(List<DesignComponent> designComponents,QuotMt quotMt,QuotDt quotDt,Principal principal); 

	public String transactionalSave(QuotCompDt quotCompDt,Integer id,Integer quotMtId,Integer quotDtId,Principal principal,Boolean netWtWithCompFlg);

	public void transactionalDelete(QuotCompDt quotCompDt,Boolean netWtWithCompFlg);
	
}

package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;




import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;

public interface IQuotStnDtService {
	
	public List<QuotStnDt> findAll();
	
	public void save(QuotStnDt quotStnDt);

	public void delete(int id);

	public Long count();

	public QuotStnDt findOne(int id);
	
	public List<QuotStnDt> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	public List<QuotStnDt> findByQuotDtAndDeactive(QuotDt quotDt,Boolean deactive);
	
	public void setQuotStnDt(List<DesignStone> designStones,QuotMt quotMt,QuotDt quotDt,Principal principal);
	
	public String transactionalSave(QuotStnDt quotStnDt,Integer id,Integer quotMtId,Integer quotDtId,String pSieve,String pSizeGroup,Principal principal,Boolean netWtWithCompFlg); 
	
	public void transactionDelete(QuotStnDt quotStnDt,Boolean netWtWithCompFlg);
	
	
	


}

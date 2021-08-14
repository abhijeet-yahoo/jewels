package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotMetalService {

	public void save(QuotMetal quotMetal);

	public void delete(int id);
		
	public QuotMetal findOne(int id);

	public List<QuotMetal> findByQuotDtAndDeactive(QuotDt quotDt,Boolean deactive);
	
	public QuotMetal findByQuotDtAndDeactiveAndMainMetal(QuotDt quotDt,Boolean deactive,Boolean mainMetal);
	
	public QuotMetal findByQuotDtAndDeactiveAndPartNm(QuotDt quotDt,Boolean deactive,LookUpMast lookUpMast);
	
	public void addQuotMetalFromDesign(List<DesignMetal> designMetals,QuotMt quotMt,QuotDt quotDt,Principal principal);
	
	public void addQuotMetal(String metalData,QuotMt quotMt,QuotDt quotDt,Principal  principal);
	
	public List<QuotMetal> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	public String updateLossPerc(Principal principal, Integer id, Double lossPerc,Boolean netWtWithCompFlg);	
	
	
}

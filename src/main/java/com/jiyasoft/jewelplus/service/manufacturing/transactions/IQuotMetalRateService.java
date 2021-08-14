package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotMetalRateService {

 	QuotMetalRate findByQuotMtAndDeactiveAndMetal(QuotMt quotMt,Boolean deactive,Metal metal);

    List<QuotMetalRate> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
    
    QuotMetalRate findOne(int id);
    
    void save(QuotMetalRate quotMetalRate);
    
    public String quotMetalRateSave(String tabData, int quotMtId,
			Principal principal,Boolean netWtWithCompFlg);
}

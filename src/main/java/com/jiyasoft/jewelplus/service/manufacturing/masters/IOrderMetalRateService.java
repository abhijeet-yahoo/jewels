package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;


public interface IOrderMetalRateService {
	
	OrderMetalRate  findByOrderMtAndDeactiveAndMetal(OrderMt orderMt,Boolean deactive,Metal metal);

    List<OrderMetalRate> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);
    
    OrderMetalRate findOne(int id);
    
    void save(OrderMetalRate orderMetalRate);
	
    public String orderMetalRateSave(String tabData,int orderMtId,Principal principal,Boolean netWtWithCompFlg);

}

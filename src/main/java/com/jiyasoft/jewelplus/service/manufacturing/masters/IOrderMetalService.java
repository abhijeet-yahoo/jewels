package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;


public interface IOrderMetalService {
	
	public void save(OrderMetal orderMetal);

	public void delete(int id);
		
	public OrderMetal findOne(int id);

	public List<OrderMetal> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);
	
	public OrderMetal findByOrderDtAndDeactiveAndMainMetal(OrderDt orderDt,Boolean deactive,Boolean mainMetal);
	
	public OrderMetal findByOrderDtAndDeactiveAndPartNm(OrderDt orderDt,Boolean deactive,LookUpMast lookUpMast);
	
	public void addOrderMetalFromDesign(List<DesignMetal> designMetals,OrderMt orderMt,OrderDt orderDt,Principal principal);
	
	public void addOrderMetal(String metalData,OrderMt orderMt,OrderDt orderDt,Principal  principal);
	
	public List<OrderMetal> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);
	
	public Boolean orderPartValidation(Integer ordDtId,Integer partId);
	
	public void setOrderMetalDt(List<DesignMetal> designMetals,OrderMt orderMt,OrderDt orderDt,Principal principal);
	public String updateLossPerc(Principal principal, Integer id, Double lossPerc,Boolean netWtWithCompFlg);

}

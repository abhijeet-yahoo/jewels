package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;

public interface IOrderCompDtService {

	public List<OrderCompDt> findAll();

	public Page<OrderCompDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(OrderCompDt orderCompDt);

	public void delete(int id);

	public Long count();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public OrderCompDt findOne(int id);

	public Map<Integer, String> getOrderCompDtList();

	public List<OrderCompDt> findByOrderMtAndOrderDt(OrderMt orderMt, OrderDt orderDt);
	
	public List<OrderCompDt> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);

	
	public List<OrderCompDt> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);

	public List<OrderCompDt> findByDesign(OrderMt orderMt, OrderDt orderDt, Principal principal, Boolean lInsert);

	public Page<OrderCompDt> findByOrderMt(OrderMt orderMt, Integer limit, Integer offset, String sort,
			String order, String search);
	
	
	public void setOrderCompDt(List<DesignComponent> designComponents,OrderMt orderMt,OrderDt orderDt,Principal principal);
	
	public String transactionalSave(OrderCompDt orderCompDt,Integer id,Integer orderMtId,Integer orderDtId,Principal principal,Boolean netWtWithCompFlg);

	public void transactionalDelete(OrderCompDt orderCompDt,Boolean netWtWithCompFlg);

}

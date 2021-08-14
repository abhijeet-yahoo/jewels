package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;

public interface IOrderStnDtService {

	public List<OrderStnDt> findAll();

	public Page<OrderStnDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(OrderStnDt orderStnDt);

	public void delete(int id);

	public Long count();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public OrderStnDt findOne(int id);

	public List<OrderStnDt> findByOrderMtAndOrderDt(OrderMt orderMt, OrderDt orderDt);
	
	public List<OrderStnDt> findByDesign(OrderMt orderMt, OrderDt orderDt, Principal principal, Boolean lInsert);
	
	public Page<OrderStnDt> findByOrderMt(OrderMt orderMt, Integer limit, Integer offset, String sort,
			String order, String search);
	
	public List<OrderStnDt>findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);

	public Integer getMaxSrno(OrderMt orderMt, OrderDt orderDt);
	
	public void setOrderStnDt(List<DesignStone> designStones,OrderMt orderMt,OrderDt orderDt,Principal principal);
	
	public List<OrderStnDt> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);
	
	public List<OrderStnDt> findByOrderDtAndDeactiveOrderByShapeAscSubShapeAscQualityAscSizeAscCaratAsc(OrderDt orderDt,Boolean deactive);
	
	public String transactionalSave(OrderStnDt orderStnDt,Integer id,Integer orderMtId,Integer orderDtId,String pSieve,String pSizeGroup,Principal principal,Boolean netWtWithCompFlg); 
	
	public void transactionDelete(OrderStnDt orderStnDt,Boolean netWtWithCompFlg);
	
	public OrderStnDt findByOrderDtAndSrNoAndDeactive(OrderDt orderDt,Integer srNo,Boolean deactive);
	
	public String changePointerStoneType(Integer stoneTypeId, Integer mtId, Principal principal);

}

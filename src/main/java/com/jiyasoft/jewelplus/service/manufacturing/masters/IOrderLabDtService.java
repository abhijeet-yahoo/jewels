package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;

public interface IOrderLabDtService {
	
	
public List<OrderLabDt> findAll();
	
	public void save(OrderLabDt orderLabDt);

	public void delete(int id);

	public Long count();

	public OrderLabDt findOne(int id);
	
	public List<OrderLabDt> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);
	
	public List<OrderLabDt> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);
	
	public List<OrderLabDt>findByOrderDtAndMetalAndDeactive(OrderDt orderDt,Metal metal,Boolean deactive );
	
	public String transactionalSave(OrderLabDt orderLabDt,Integer id,Integer orderMtId,Integer orderDtId,Principal principal, Boolean netWtWithCompFlg);

	public void transactionalDelete(OrderLabDt orderLabDt, Boolean netWtWithCompFlg);

}

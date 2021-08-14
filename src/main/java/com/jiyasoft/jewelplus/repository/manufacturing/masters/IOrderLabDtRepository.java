package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;


public interface IOrderLabDtRepository extends JpaRepository<OrderLabDt, Integer>{
	
List<OrderLabDt> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);
	
	List<OrderLabDt> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);
	
	List<OrderLabDt>findByOrderDtAndMetalAndDeactive(OrderDt orderDt,Metal metal,Boolean deactive );

}

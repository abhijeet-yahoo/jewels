package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderQuality;

public interface IOrderQualityService {

	public List<OrderQuality> findAll();

	public Page<OrderQuality> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(OrderQuality orderQuality);

	public void delete(int id);

	public Long count();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public OrderQuality findOne(int id);

	public Map<Integer, String> getOrderQualityList();

	public List<OrderQuality> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);

	public Page<OrderQuality> findByOrderMt(OrderMt orderMt, Integer limit, Integer offset, String sort,
			String order, String search);

}

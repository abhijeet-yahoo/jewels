package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;

public interface IOrderTypeService {

	public List<OrderType> findAll();

	public Page<OrderType> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(OrderType orderType);

	public void delete(int id);

	public Long count();

	public OrderType findOne(int id);

	public OrderType findByName(String name);

	public Map<Integer, String> getOrderTypeList();

	public List<OrderType> findActiveOrderType();
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<OrderType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);

	public Page<OrderType> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	
	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public List<OrderType> findRepairAndPurchaseOrderType();
	
	public Map<Integer, String> findRepairAndPurchaseOrderTypeMap();

	
}

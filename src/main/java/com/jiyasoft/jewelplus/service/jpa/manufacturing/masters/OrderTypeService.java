package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderTypeService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class OrderTypeService implements IOrderTypeService {

	@Autowired
	private IOrderTypeRepository orderTypeRepository;
	

	@Override
	public List<OrderType> findAll() {
		return orderTypeRepository.findAll();
	}

	@Override
	public Page<OrderType> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		//int page = (offset == 0 ? 0 : (offset / limit));
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}
		
		if (sort == null) {
			sort = "id";
		}
		return orderTypeRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(OrderType orderType) {
		orderTypeRepository.save(orderType);

	}

	@Override
	public void delete(int id) {
		OrderType orderType = orderTypeRepository.findOne(id);
		orderType.setDeactive(true);
		orderType.setDeactiveDt(new java.util.Date());
		orderTypeRepository.save(orderType);

	}

	@Override
	public Long count() {
		return orderTypeRepository.count();
	}

	@Override
	public OrderType findOne(int id) {
		return orderTypeRepository.findOne(id);
	}

	@Override
	public OrderType findByName(String name) {
		return orderTypeRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getOrderTypeList() {

		Map<Integer, String> orderTypeMap = new HashMap<Integer, String>();
		List<OrderType> orderTypeList = findActiveOrderType();

		for (OrderType orderType : orderTypeList) {
			orderTypeMap.put(orderType.getId(), orderType.getName());
		}

		return orderTypeMap;
	}

	@Override
	public List<OrderType> findActiveOrderType() {
		QOrderType qOrderType = QOrderType.orderType;
		BooleanExpression expression = qOrderType.deactive.eq(false);

		List<OrderType> orderTypeList = (List<OrderType>) orderTypeRepository.findAll(expression);

		return orderTypeList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QOrderType qOrderType = QOrderType.orderType;
		BooleanExpression expression = qOrderType.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qOrderType.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qOrderType.deactive.eq(false).and(
						qOrderType.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qOrderType.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("name")
					&& colValue != null) {
				expression = qOrderType.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return orderTypeRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return orderTypeRepository.count();
			}
		}

		return orderTypeRepository.count(expression);
	}
	

	@Override
	public Page<OrderType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QOrderType qOrderType = QOrderType.orderType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qOrderType.deactive.eq(false);
			} else {
				expression = qOrderType.deactive.eq(false).and(
						qOrderType.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qOrderType.name.like(name + "%");
			}
		}

		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<OrderType> orderTypeList = (Page<OrderType>) orderTypeRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return orderTypeList;
	}

	@Override
	public Page<OrderType> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {


		QOrderType qOrderType = QOrderType.orderType;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qOrderType.deactive.eq(false);
			}else{
				expression = qOrderType.deactive.eq(false).and(qOrderType.name.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression = qOrderType.name.like("%" + search + "%");
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} 
		
		Page<OrderType> orderTypeMastList =(Page<OrderType>) orderTypeRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return orderTypeMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {


		QOrderType qOrderType = QOrderType.orderType;
		BooleanExpression expression = null;
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qOrderType.deactive.eq(false);			
				}else{
					expression = qOrderType.deactive.eq(false).and(qOrderType.name.like("%" + colValue + "%"));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qOrderType.name.like("%" + colValue + "%");
				}
			}
			
			return orderTypeRepository.count(expression);
	}

	@Override
	public List<OrderType> findRepairAndPurchaseOrderType() {
		// TODO Auto-generated method stub
		QOrderType qOrderType = QOrderType.orderType;
		BooleanExpression expression = qOrderType.deactive.eq(false).and(qOrderType.name.in("REPAIR ORDER","Purchase"));
		
		List<OrderType> orderTypeMastList =(List<OrderType>) orderTypeRepository.findAll(expression);
		
		return orderTypeMastList;
	}

	@Override
	public Map<Integer, String> findRepairAndPurchaseOrderTypeMap() {
		// TODO Auto-generated method stub
		Map<Integer, String> orderTypeMap = new HashMap<Integer, String>();
		List<OrderType> orderTypeList = findRepairAndPurchaseOrderType();

		for (OrderType orderType : orderTypeList) {
			orderTypeMap.put(orderType.getId(), orderType.getName());
		}

		return orderTypeMap;
	}


}

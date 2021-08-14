package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderCompDt;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignComponentRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class OrderCompDtService implements IOrderCompDtService {

	@Autowired
	private IOrderCompDtRepository orderCompDtRepository;

	@Autowired
	private IDesignComponentRepository designComponentRepository;
	
	@Autowired
	private IOrderMtService orderMtService;;

	@Autowired
	private IOrderDtService orderDtService;

	@Override
	public List<OrderCompDt> findAll() {
		return orderCompDtRepository.findAll();
	}

	@Override
	public Page<OrderCompDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return orderCompDtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(OrderCompDt orderCompDt) {
		orderCompDtRepository.save(orderCompDt);
	}

	@Override
	public void delete(int id) {
		OrderCompDt orderCompDt = orderCompDtRepository.findOne(id);
		deleteObj(orderCompDt);	}

	private void deleteObj(OrderCompDt orderCompDt) {
		orderCompDt.setDeactive(true);
		orderCompDt.setDeactiveDt(new java.util.Date());
		orderCompDtRepository.save(orderCompDt);
	}

	@Override
	public Long count() {
		return orderCompDtRepository.count();
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QOrderCompDt qOrderCompDt = QOrderCompDt.orderCompDt;
		BooleanExpression expression = qOrderCompDt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qOrderCompDt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("styleId") && colValue != null) {
				expression = qOrderCompDt.deactive.eq(false).and(
						qOrderCompDt.orderMt.id.eq(Integer.parseInt(colValue)));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("styleId"))
					&& colValue != null) {
				expression = qOrderCompDt.orderMt.id.eq(Integer
						.parseInt(colValue));
			} else if ((colName != null || colName.equalsIgnoreCase("styleId"))
					&& colValue != null) {
				expression = qOrderCompDt.orderMt.id.eq(Integer
						.parseInt(colValue));
			} /*
			 * else if (colName != null && colName.equalsIgnoreCase("style") &&
			 * colValue != null) { expression = qInventory.style.like(colValue +
			 * "%"); } else if (colName != null &&
			 * colName.equalsIgnoreCase("category") && colValue != null) {
			 * expression = qInventory.category.name.like(colValue + "%"); }
			 * else if (colName != null &&
			 * colName.equalsIgnoreCase("subCategory") && colValue != null) {
			 * expression = qInventory.subCategory.name.like(colValue + "%"); }
			 * else if (colName != null && colName.equalsIgnoreCase("metal") &&
			 * colValue != null) { expression =
			 * qInventory.metal.name.like(colValue + "%"); } else if (colName !=
			 * null && colName.equalsIgnoreCase("purity") && colValue != null) {
			 * expression = qInventory.purity.name.like(colValue + "%"); } else
			 * if (colName != null && colName.equalsIgnoreCase("pieces") &&
			 * colValue != null) { Integer tmpPieces = 0;
			 * 
			 * try { tmpPieces = Integer.parseInt(colValue); } catch
			 * (NumberFormatException ex) { tmpPieces = 0; }
			 * 
			 * expression = qInventory.pieces.eq(tmpPieces); } else if (colName
			 * != null && colValue == null) { return
			 * inventoryRepository.count(); } else if (colName != null &&
			 * colValue != null) { return 0L; } else { return
			 * inventoryRepository.count(); }
			 */
		}

		return orderCompDtRepository.count(expression);
	}

	@Override
	public OrderCompDt findOne(int id) {
		return orderCompDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getOrderCompDtList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderCompDt> findByOrderMtAndOrderDt(OrderMt orderMt, OrderDt orderDt) {
		QOrderCompDt qOrderCompDt = QOrderCompDt.orderCompDt;
		BooleanExpression expression = qOrderCompDt.deactive.eq(false)
				.and(qOrderCompDt.orderMt.id.eq(orderMt.getId()))
				.and(qOrderCompDt.orderDt.id.eq(orderDt.getId()));

		return (List<OrderCompDt>) orderCompDtRepository.findAll(expression);
//		return orderCompDtRepository.findByOrderMtAndOrderDt(orderMt, orderDt);
	}

	@Override
	public List<OrderCompDt> findByDesign(OrderMt orderMt, OrderDt orderDt, Principal principal, Boolean lInsert) {
		int cnt = 0;
		List<OrderCompDt> orderCompDtList = new ArrayList<OrderCompDt>();

		orderCompDtList = findByOrderMtAndOrderDt(orderMt, orderDt);

		if ((lInsert) && orderCompDtList.size() > 0) {
			for (OrderCompDt orderCompDt : orderCompDtList) {
				deleteObj(orderCompDt);
			}

			orderCompDtList.clear();
		}

		
		if ((lInsert) && orderCompDtList.size() == 0) {
			List<DesignComponent> designComponentList = designComponentRepository.findByDesign(orderDt.getDesign());

			for (DesignComponent designComponent : designComponentList) {
				OrderCompDt orderCompDt = new OrderCompDt();
				orderCompDt.setOrderMt(orderMt);
				orderCompDt.setOrderDt(orderDt);
				orderCompDt.setComponent(designComponent.getComponent());
				orderCompDt.setCompQty(designComponent.getQuantity());
				orderCompDt.setCreatedBy(principal.getName());
				orderCompDt.setCreatedDate(new java.util.Date());

				orderCompDtRepository.save(orderCompDt);
				orderCompDtList.add(orderCompDt);
			}
		}

		return orderCompDtList;
	}

	@Override
	public Page<OrderCompDt> findByOrderMt(OrderMt orderMt, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return orderCompDtRepository.findByOrderMt(orderMt, new PageRequest(
				page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void setOrderCompDt(List<DesignComponent> designComponents,
			OrderMt orderMt, OrderDt orderDt, Principal principal) {
		if(designComponents != null){
			
			for(DesignComponent designComponent : designComponents){
			
			OrderCompDt orderCompDt = new OrderCompDt();
			
			orderCompDt.setOrderMt(orderMt);
			orderCompDt.setOrderDt(orderDt);
			orderCompDt.setComponent(designComponent.getComponent());
			orderCompDt.setCompQty(designComponent.getQuantity());
			orderCompDt.setCreatedBy(principal.getName());
			orderCompDt.setCreatedDate(new java.util.Date());
			orderCompDt.setPurity(orderDt.getPurity());
			orderCompDt.setColor(orderDt.getColor());
			orderCompDt.setMetalWt(designComponent.getCompWt());
								
				orderCompDtRepository.save(orderCompDt);
			
			}
			
			
		}
		
	}

	@Override
	public List<OrderCompDt> findByOrderDtAndDeactive(OrderDt orderDt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return orderCompDtRepository.findByOrderDtAndDeactive(orderDt, deactive);
	}

	@Override
	public List<OrderCompDt> findByOrderMtAndDeactive(OrderMt orderMt,
			Boolean deactive) {
		return orderCompDtRepository.findByOrderMtAndDeactive(orderMt, false);
		
	}

	@Override
	public String transactionalSave(OrderCompDt orderCompDt, Integer id,
			Integer orderMtId, Integer orderDtId, Principal principal,Boolean netWtWithCompFlg ) {

		String retVal = "-1";

		
		OrderMt orderMt = orderMtService.findOne(orderMtId);
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			orderCompDt.setOrderMt(orderMt);
			orderCompDt.setOrderDt(orderDt);
			orderCompDt.setPurityConv(orderDt.getPurityConv());
			orderCompDt.setCreatedBy(principal.getName());
			orderCompDt.setCreatedDate(new java.util.Date());
		}else{
			orderCompDt.setOrderMt(orderMt);
			orderCompDt.setOrderDt(orderDt);
			orderCompDt.setPurityConv(orderDt.getPurityConv());
			orderCompDt.setId(id);
			orderCompDt.setModiBy(principal.getName());
			orderCompDt.setModiDate(new java.util.Date());
			retVal = "-2";
		}
		
		
		save(orderCompDt);
		
		orderDtService.updateGrossWt(orderDt,netWtWithCompFlg);
		orderDtService.updateFob(orderDt,netWtWithCompFlg);
		
				
		return retVal;
	}

	@Override
	public void transactionalDelete(OrderCompDt orderCompDt,Boolean netWtWithCompFlg) {
		delete(orderCompDt.getId());
		orderDtService.updateGrossWt(orderCompDt.getOrderDt(),netWtWithCompFlg);
		orderDtService.updateFob(orderCompDt.getOrderDt(),netWtWithCompFlg);
		
	}

}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class OrderStnDtService implements IOrderStnDtService {

	@Autowired
	private IOrderStnDtRepository orderStnDtRepository;

	@Autowired
	private IOrderQualityService orderQualityService;
 
	@Autowired
	private IDesignStoneService designStoneService;

	@Autowired
	private IOrderMtService orderMtService;;

	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStoneTypeService stoneTypeService;

	@Override
	public List<OrderStnDt> findAll() {
		return orderStnDtRepository.findAll();
	}

	@Override
	public Page<OrderStnDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		QOrderStnDt qOrderStnDt = QOrderStnDt.orderStnDt;
		BooleanExpression expression = qOrderStnDt.deactive.eq(false);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return orderStnDtRepository.findAll(expression, new PageRequest(page, limit, Direction.DESC, sort));
	}

	@Override
	public void save(OrderStnDt orderStnDt) {
		orderStnDtRepository.save(orderStnDt);
	}

	@Override
	public void delete(int id) {
		OrderStnDt orderStnDt = orderStnDtRepository.findOne(id);
		deleteObj(orderStnDt);
	}

	private void deleteObj(OrderStnDt orderStnDt) {
		orderStnDt.setDeactive(true);
		orderStnDt.setDeactiveDt(new java.util.Date());
		orderStnDtRepository.save(orderStnDt);		
	}

	@Override
	public Long count() {
		return orderStnDtRepository.count();
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QOrderStnDt qOrderStnDt  = QOrderStnDt.orderStnDt;
		BooleanExpression expression = qOrderStnDt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qOrderStnDt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("styleId") && colValue != null) {
				expression = qOrderStnDt.deactive.eq(false).and(
						qOrderStnDt.orderMt.id.eq(Integer.parseInt(colValue)));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("styleId")) && colValue != null) {
				expression = qOrderStnDt.orderMt.id.eq(Integer.parseInt(colValue));
			} 
		}

		return orderStnDtRepository.count(expression);
	}

	@Override
	public OrderStnDt findOne(int id) {
		return orderStnDtRepository.findOne(id);
	}

	

	@Override
	public List<OrderStnDt> findByOrderMtAndOrderDt(OrderMt orderMt, OrderDt orderDt) {
		QOrderStnDt qOrderStnDt = QOrderStnDt.orderStnDt;
		BooleanExpression expression = qOrderStnDt.deactive.eq(false)
				.and(qOrderStnDt.orderMt.id.eq(orderMt.getId()))
				.and(qOrderStnDt.orderDt.id.eq(orderDt.getId()));

		return (List<OrderStnDt>) orderStnDtRepository.findAll(expression);
	}

	@Override
	public List<OrderStnDt> findByDesign(OrderMt orderMt, OrderDt orderDt, Principal principal, Boolean lInsert) {
		int cnt = 0;
		List<OrderStnDt> orderStnDtList = new ArrayList<OrderStnDt>();

	//	orderStnDtList = findByOrderMtAndOrderDt(orderMt, orderDt);
		
		orderStnDtList = findByOrderDtAndDeactiveOrderByShapeAscSubShapeAscQualityAscSizeAscCaratAsc(orderDt,false);
		
		if ((lInsert) && orderStnDtList.size() > 0) {
			for (OrderStnDt orderStnDt : orderStnDtList) {
				deleteObj(orderStnDt);
			}

			orderStnDtList.clear();
		}

		if ((lInsert) && orderStnDtList.size() == 0) {
			List<DesignStone> designStoneList = designStoneService.findByDesign(orderDt.getDesign());
			List<OrderQuality> orderQualityList = orderQualityService.findByOrderMtAndDeactive(orderMt, false);

			boolean lfound = false;
			for (DesignStone designStone : designStoneList) {
				lfound = false;
				
				
				OrderStnDt orderStnDt = new OrderStnDt();
				orderStnDt.setOrderMt(orderMt);
				orderStnDt.setOrderDt(orderDt);
				orderStnDt.setCarat(designStone.getCarat());
				orderStnDt.setShape(designStone.getShape());
				orderStnDt.setSubShape(designStone.getSubShape());
				orderStnDt.setStoneType(designStone.getStoneType());
				orderStnDt.setCenterStone(designStone.getCenterStone());

				for (OrderQuality orderQuality : orderQualityList) {
					if ((orderQuality.getShape().getId() == designStone.getShape().getId()) && (orderQuality.getStoneType().getId() == designStone.getStoneType().getId())) {
						orderStnDt.setQuality(orderQuality.getQuality());
						lfound = true;
						break;
					}
				}

				if (!lfound) {
					orderStnDt.setQuality(designStone.getQuality());	
				}

				orderStnDt.setSize(designStone.getSize());
				orderStnDt.setSizeGroup(designStone.getSizeGroup());
				orderStnDt.setSieve(designStone.getSieve());
				orderStnDt.setStone(designStone.getStone());
				orderStnDt.setBreadth((designStone.getBreadth() != null ? designStone.getBreadth() : null));
				orderStnDt.setSetting(designStone.getSetting());
				orderStnDt.setSettingType(designStone.getSettingType()); 
				orderStnDt.setSrNo(++cnt);
				orderStnDt.setCreatedBy(principal.getName());
				orderStnDt.setCreatedDate(new java.util.Date());
				orderStnDt.setDiaCateg(designStone.getDiaCateg());

				orderStnDtRepository.save(orderStnDt);
				orderStnDtList.add(orderStnDt);
			}
			
			
		}

		return orderStnDtList;
	}
	
	
	
	
	
	
	
	
	@Override
	public void setOrderStnDt(List<DesignStone> designStones,OrderMt orderMt,OrderDt orderDt,Principal principal) {
		
		if(designStones != null){
			
					
			List<OrderQuality> orderQualitys = orderQualityService.findByOrderMtAndDeactive(orderMt, false);
				
			
			Boolean recordFound = false;
			int srNo=0;
			for(DesignStone designStone:designStones){
				srNo+=1;
				recordFound = false;
				
				OrderStnDt orderStnDt = new OrderStnDt();
				orderStnDt.setSrNo(srNo);
				orderStnDt.setOrderMt(orderMt);
				orderStnDt.setOrderDt(orderDt);
				orderStnDt.setCarat(designStone.getCarat());
				orderStnDt.setShape(designStone.getShape());
				orderStnDt.setStoneType(designStone.getStoneType());
				orderStnDt.setSubShape(designStone.getSubShape());
				
				for(OrderQuality orderQuality:orderQualitys){
					if((designStone.getShape().getId() == orderQuality.getShape().getId()) && (designStone.getStoneType().getId() == orderQuality.getStoneType().getId())){
						orderStnDt.setQuality(orderQuality.getQuality());
						recordFound = true;
						break;
					}
				}
				
				if(!recordFound){
					orderStnDt.setQuality(designStone.getQuality());
				}
				
				orderStnDt.setSize(designStone.getSize());
				orderStnDt.setSizeGroup(designStone.getSizeGroup());
				orderStnDt.setSieve(designStone.getSieve());
				orderStnDt.setStone(designStone.getStone());
				orderStnDt.setSetting(designStone.getSetting());
				orderStnDt.setSettingType(designStone.getSettingType());
				orderStnDt.setCreatedBy(principal.getName());
				orderStnDt.setCreatedDate(new java.util.Date());
				orderStnDt.setPartNm(designStone.getPartNm());
				orderStnDt.setDiaCateg(designStone.getDiaCateg());
				
				
				orderStnDtRepository.save(orderStnDt);
				
				
				
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public Page<OrderStnDt> findByOrderMt(OrderMt orderMt, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return orderStnDtRepository.findByOrderMt(orderMt, new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public Integer getMaxSrno(OrderMt orderMt, OrderDt orderDt) {
		QOrderStnDt qOrderStnDt = QOrderStnDt.orderStnDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = 0;

		List<Integer> maxSrno = query
			.from(qOrderStnDt)
			.where(qOrderStnDt.deactive.eq(false).and(qOrderStnDt.orderMt.id.eq(orderMt.getId())).and(qOrderStnDt.orderDt.id.eq(orderDt.getId())))
			.list(qOrderStnDt.srNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}
		
		if(retVal == null){
			retVal =0;
		}
		

		return retVal;
	}

	@Override
	public List<OrderStnDt> findByOrderMtAndDeactive(OrderMt orderMt,
			Boolean deactive) {
		
		return orderStnDtRepository.findByOrderMtAndDeactive(orderMt, deactive);
	}

	@Override
	public List<OrderStnDt> findByOrderDtAndDeactive(OrderDt orderDt,
			Boolean deactive) {
		return orderStnDtRepository.findByOrderDtAndDeactive(orderDt, deactive);
	}

	@Override
	public String transactionalSave(OrderStnDt orderStnDt, Integer id,
			Integer orderMtId, Integer orderDtId, String pSieve,
			String pSizeGroup, Principal principal,Boolean netWtWithCompFlg) {
	
		String retVal = "-1";

		OrderMt orderMt = orderMtService.findOne(orderMtId);
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			orderStnDt.setCreatedBy(principal.getName());
			orderStnDt.setCreatedDate(new java.util.Date());
			orderStnDt.setOrderMt(orderMt);
			orderStnDt.setOrderDt(orderDt);
			orderStnDt.setSieve(pSieve);
			orderStnDt.setSizeGroup(stoneChartService.findByShapeAndSizeAndDeactive(orderStnDt.getShape(), orderStnDt.getSize(),false).getSizeGroup());
			int srNo =getMaxSrno(orderMt, orderDt);
			orderStnDt.setSrNo(srNo+1);
			
			
		} else {
			orderStnDt.setId(id);
			orderStnDt.setModiBy(principal.getName());
			orderStnDt.setModiDate(new java.util.Date());
			orderStnDt.setOrderMt(orderMt);
			orderStnDt.setOrderDt(orderDt);
			orderStnDt.setSieve(pSieve);
			orderStnDt.setSizeGroup(stoneChartService.findByShapeAndSizeAndDeactive(orderStnDt.getShape(), orderStnDt.getSize(),false).getSizeGroup());
			retVal = "-2";
		}

		if (orderStnDt.getShape().getId() == null) {
			orderStnDt.setShape(null);
		} else {
			orderStnDt.setSizeGroup(stoneChartService.findByShapeAndSizeAndDeactive(orderStnDt.getShape(), orderStnDt.getSize(),false).getSizeGroup());
		}
		if (orderStnDt.getSubShape().getId() == null) {
			orderStnDt.setSubShape(null);
		}
		if (orderStnDt.getQuality().getId() == null) {
			orderStnDt.setQuality(null);
		}
		if (orderStnDt.getStoneType().getId() == null) {
			orderStnDt.setStoneType(null);
		} 
		if (orderStnDt.getSetting().getId() == null) {
			orderStnDt.setSetting(null);
		}
		if (orderStnDt.getSettingType().getId() == null) {
			orderStnDt.setSettingType(null);
		}
		
		if (orderStnDt.getDiaCateg() == null) {
			orderStnDt.setDiaCateg("");
		}
		
		if(orderStnDt.getHandlingRate() == null) {
			orderStnDt.setHandlingRate(0.0);
		}
		
		if(orderStnDt.getStnRate() == null) {
			orderStnDt.setStnRate(0.0);
		}
		
		if(orderStnDt.getSetRate() == null) {
			orderStnDt.setSetRate(0.0);
		}
		
	
		save(orderStnDt);
		
		orderDtService.updateGrossWt(orderDt,netWtWithCompFlg);
		
		orderDtService.updateFob(orderDt,netWtWithCompFlg);
		
		orderDtService.updateQltyDesc(orderDt.getId());
		
		return retVal;
	}

	
	@Override
	public void transactionDelete(OrderStnDt orderStnDt,Boolean netWtWithCompFlg) {
		delete(orderStnDt.getId());
		orderDtService.updateGrossWt(orderStnDt.getOrderDt(),netWtWithCompFlg);
		orderDtService.updateFob(orderStnDt.getOrderDt(),netWtWithCompFlg);
		orderDtService.updateQltyDesc(orderStnDt.getOrderDt().getId());
	}

	@Override
	public List<OrderStnDt> findByOrderDtAndDeactiveOrderByShapeAscSubShapeAscQualityAscSizeAscCaratAsc(OrderDt orderDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return orderStnDtRepository.findByOrderDtAndDeactiveOrderByShapeAscSubShapeAscQualityAscSizeAscCaratAsc(orderDt, deactive);
	}

	@Override
	public OrderStnDt findByOrderDtAndSrNoAndDeactive(OrderDt orderDt, Integer srNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return orderStnDtRepository.findByOrderDtAndSrNoAndDeactive(orderDt, srNo, deactive);
		
	}

	@Override
	public String changePointerStoneType(Integer stoneTypeId, Integer mtId,
			Principal principal) {
		
		String retVal = "-1";
		
		try {
			
			OrderMt orderMt = orderMtService.findOne(mtId);
			StoneType stoneType=stoneTypeService.findOne(stoneTypeId);
			
			List<OrderStnDt> orderStnDts = findByOrderMtAndDeactive(orderMt, false);
			for (OrderStnDt orderStnDt2 : orderStnDts) {
				
				StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(orderStnDt2.getShape(), orderStnDt2.getSize(), false);
				if(stoneChart != null) {
					if(stoneChart.getPointerFlg()) {
						
						orderStnDt2.setStoneType(stoneType);
						orderStnDt2.setModiBy(principal.getName());
						orderStnDt2.setModiDate(new Date());
						save(orderStnDt2);
					}
				}
			
			
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "1";
	}

}

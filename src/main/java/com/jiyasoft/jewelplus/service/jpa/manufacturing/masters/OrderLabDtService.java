package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderLabDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;


@Service
@Transactional
@Repository
public class OrderLabDtService implements IOrderLabDtService {
	
	@Autowired
	private IOrderLabDtRepository orderLabDtRepository;
	
	
	@Autowired
	private IOrderMtService orderMtService;;

	@Autowired
	private IOrderDtService orderDtService;

	@Override
	public List<OrderLabDt> findAll() {
		return orderLabDtRepository.findAll();
	}

	@Override
	public void save(OrderLabDt orderLabDt) {
		orderLabDtRepository.save(orderLabDt);
		
	}

	@Override
	public void delete(int id) {
		OrderLabDt orderLabDt = orderLabDtRepository.findOne(id);
		orderLabDt.setDeactive(true);
		orderLabDt.setDeactiveDt(new java.util.Date());
		orderLabDtRepository.save(orderLabDt);
		
	}

	@Override
	public Long count() {
		return orderLabDtRepository.count();
	}

	@Override
	public OrderLabDt findOne(int id) {
		return orderLabDtRepository.findOne(id);
	}

	@Override
	public List<OrderLabDt> findByOrderMtAndDeactive(OrderMt orderMt,
			Boolean deactive) {
		return orderLabDtRepository.findByOrderMtAndDeactive(orderMt, deactive);
	}

	@Override
	public List<OrderLabDt> findByOrderDtAndDeactive(OrderDt orderDt,
			Boolean deactive) {
		return orderLabDtRepository.findByOrderDtAndDeactive(orderDt, deactive);
	}

	@Override
	public List<OrderLabDt> findByOrderDtAndMetalAndDeactive(OrderDt orderDt,
			Metal metal, Boolean deactive) {
		return orderLabDtRepository.findByOrderDtAndMetalAndDeactive(orderDt, metal, deactive);
	}

	@Override
	public String transactionalSave(OrderLabDt orderLabDt, Integer id,
			Integer orderMtId, Integer orderDtId, Principal principal, Boolean netWtWithCompFlg) {
		
		
		String retVal = "-1";
		
		if(orderLabDt.getPcsWise() == true && orderLabDt.getGramWise() == true ){
			return retVal = "-11";
		}
		
		if(orderLabDt.getPcsWise() == true && orderLabDt.getPercentWise() == true ){
			return retVal = "-11";
		}
		
		if(orderLabDt.getGramWise() == true && orderLabDt.getPercentWise() == true){
			return retVal = "-11";
		}
		
		if(orderLabDt.getPcsWise() == false && orderLabDt.getGramWise() == false && orderLabDt.getPercentWise() == false){
			return retVal = "-12";
		}
		
		
		OrderMt orderMt = orderMtService.findOne(orderMtId);
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			orderLabDt.setOrderMt(orderMt);
			orderLabDt.setOrderDt(orderDt);
			orderLabDt.setCreatedBy(principal.getName());
			orderLabDt.setCreatedDate(new java.util.Date());
		}else{
			orderLabDt.setOrderMt(orderMt);
			orderLabDt.setOrderDt(orderDt);
			orderLabDt.setId(id);
			orderLabDt.setModiBy(principal.getName());
			orderLabDt.setModiDate(new java.util.Date());
			retVal = "-2";
		}
		
		
		save(orderLabDt);
		
		//orderDtService.updateGrossWt(orderDt);
		orderDtService.updateFob(orderDt,netWtWithCompFlg);
		
		return retVal;
	}

	@Override
	public void transactionalDelete(OrderLabDt orderLabDt, Boolean netWtWithCompFlg) {
		delete(orderLabDt.getId());
		//orderDtService.updateGrossWt(orderLabDt.getOrderDt());
		orderDtService.updateFob(orderLabDt.getOrderDt(),netWtWithCompFlg);
	}

}

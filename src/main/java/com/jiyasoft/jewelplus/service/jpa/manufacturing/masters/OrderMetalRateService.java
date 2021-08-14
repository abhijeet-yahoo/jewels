package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderMetalRateRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;

@Service
@Repository
@Transactional
public class OrderMetalRateService implements IOrderMetalRateService {
	
	@Autowired
	private IOrderMetalRateRepository   orderMetalRateRepository;
	
	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IMetalService metalService;

	@Override
	public OrderMetalRate findByOrderMtAndDeactiveAndMetal(OrderMt orderMt,
			Boolean deactive, Metal metal) {
		// TODO Auto-generated method stub
		return orderMetalRateRepository.findByOrderMtAndDeactiveAndMetal(orderMt, deactive, metal);
	}

	@Override
	public List<OrderMetalRate> findByOrderMtAndDeactive(OrderMt orderMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return orderMetalRateRepository.findByOrderMtAndDeactive(orderMt, deactive);
	}

	@Override
	public OrderMetalRate findOne(int id) {
		// TODO Auto-generated method stub
		return orderMetalRateRepository.findOne(id);
	}

	@Override
	public void save(OrderMetalRate orderMetalRate) {
		// TODO Auto-generated method stub
		orderMetalRateRepository.save(orderMetalRate);
	}

	@Override
	public String orderMetalRateSave(String tabData, int orderMtId, Principal principal,Boolean netWtWithCompFlg) {
		String retVal = "-1";
		
		try {
			JSONArray jsonArrays = new JSONArray(tabData);
			
			OrderMt orderMt = orderMtService.findOne(orderMtId);
			
			for(int y=0;y<jsonArrays.length();y++){
				
				JSONObject jsonObject = (JSONObject) jsonArrays.get(y);
				
				if(jsonObject.getInt("id") > 0){
					//edit Mode
					OrderMetalRate orderMetalRate =  findOne(jsonObject.getInt("id"));
					orderMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					orderMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					orderMetalRate.setModiBy(principal.getName());
					orderMetalRate.setModiDt(new java.util.Date());
					
					save(orderMetalRate);
					
					
				}else{
					//add Mode
					
					if(Double.parseDouble(jsonObject.get("rate").toString()) > 0 || Double.parseDouble(jsonObject.get("lossPerc").toString()) > 0){
					
						OrderMetalRate orderMetalRate =  new OrderMetalRate();
						orderMetalRate.setOrderMt(orderMt);
						orderMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
						orderMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
						orderMetalRate.setMetal(metalService.findByName(jsonObject.get("metal").toString()));
						orderMetalRate.setCreatedBy(principal.getName());
						orderMetalRate.setCreateDate(new java.util.Date());
					
						save(orderMetalRate);
					}
					
				}
				
			}
			
			
			List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(orderMt, false);
			for (OrderDt orderDt : orderDts) {
				orderDtService.applyMetal(orderDt);
				orderDtService.updateFob(orderDt, netWtWithCompFlg);
			}
			
			
		} catch (Exception e) {
			retVal = "-2";
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	

	
	
}

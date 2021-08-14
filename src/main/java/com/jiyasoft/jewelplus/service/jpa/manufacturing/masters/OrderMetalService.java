package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QTranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderMetalRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class OrderMetalService implements IOrderMetalService {
	
	@Autowired
	private IOrderMetalRepository orderMetalRepository;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public void save(OrderMetal orderMetal) {
		// TODO Auto-generated method stub
		orderMetalRepository.save(orderMetal);
	}

	@Override
	public void delete(int id) {
		OrderMetal orderMetal =orderMetalRepository.findOne(id);
		orderMetal.setDeactive(true);
		orderMetal.setDeactiveDt(new java.util.Date());
		orderMetalRepository.save(orderMetal);
		
	}

	

	@Override
	public OrderMetal findOne(int id) {
		return orderMetalRepository.findOne(id);
	}

	@Override
	public List<OrderMetal> findByOrderDtAndDeactive(OrderDt orderDt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return orderMetalRepository.findByOrderDtAndDeactive(orderDt, deactive);
	}

	@Override
	public OrderMetal findByOrderDtAndDeactiveAndMainMetal(OrderDt orderDt,
			Boolean deactive, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return orderMetalRepository.findByOrderDtAndDeactiveAndMainMetal(orderDt, deactive, mainMetal);
	}

	@Override
	public OrderMetal findByOrderDtAndDeactiveAndPartNm(OrderDt orderDt,
			Boolean deactive, LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return orderMetalRepository.findByOrderDtAndDeactiveAndPartNm(orderDt, deactive, lookUpMast);
	}

	@Override
	public void addOrderMetalFromDesign(List<DesignMetal> designMetals,
			OrderMt orderMt, OrderDt orderDt, Principal principal) {

		for(DesignMetal designMetal :designMetals){
			
			OrderMetal orderMetal = new OrderMetal();
			
			orderMetal.setColor(designMetal.getColor());
			orderMetal.setCreateDate(new Date());
			orderMetal.setCreatedBy(principal.getName());
			orderMetal.setMainMetal(designMetal.getMainMetal());
			orderMetal.setMetalPcs(designMetal.getMetalPcs());
			orderMetal.setMetalWeight(designMetal.getMetalWeight());
			orderMetal.setPartNm(designMetal.getPartNm());
			orderMetal.setPurity(designMetal.getPurity());
			orderMetal.setOrderDt(orderDt);
			orderMetal.setOrderMt(orderMt);
			//orderMetal.setWaxWt(designMetal.getWaxWt());
			
			
			save(orderMetal);
			
			
		}
			
			
		}
		
		@Override
		public void addOrderMetal(String metalDtData,OrderMt orderMt,OrderDt orderDt,Principal  principal) {
			
			
			
			if(metalDtData.length()>0){
				
				JSONArray jsonArrayMetal =new JSONArray(metalDtData);
				
				for(int i=0 ;i<jsonArrayMetal.length();i++){
					
					JSONObject metalObj =jsonArrayMetal.getJSONObject(i);
					if(metalObj.length()>0){
						if(metalObj.getInt("id") == 0){
							
							OrderMetal orderMetal = new OrderMetal();
							
							
							Color color=colorService.findByName(metalObj.get("color").toString());
							if(color !=null){
								orderMetal.setColor(color);
							}
											
							orderMetal.setCreateDate(new Date());
							orderMetal.setCreatedBy(principal.getName());
							orderMetal.setMainMetal(metalObj.getBoolean("mainMetal"));
							orderMetal.setMetalPcs(metalObj.getInt("qty"));
							orderMetal.setMetalWeight(metalObj.getDouble("metalWt"));
							orderMetal.setProcessLoss(metalObj.getDouble("processLoss"));
							orderMetal.setCastWeight(Math.round((orderMetal.getMetalWeight()+(orderMetal.getMetalWeight()*orderMetal.getProcessLoss()/100))*1000.0)/1000.0);
							LookUpMast part = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", metalObj.getString("partNm").toString(),false);
							if(part !=null){
								orderMetal.setPartNm(part);	
							}
							
							Purity purity=purityService.findByName(metalObj.get("purity").toString());
							if(purity !=null){
								orderMetal.setPurity(purity);
							}
			
							orderMetal.setOrderDt(orderDt);
							orderMetal.setOrderMt(orderMt);
							//orderMetal.setWaxWt(metalObj.getDouble("waxWt"));
							
							
							save(orderMetal);
						}else{
							
							OrderMetal orderMetal =findOne(metalObj.getInt("id"));
							if(orderMetal !=null){
								
								List<BagMt>bagMts = bagMtService.findByOrderDtAndDeactive(orderDt, false);
								
								
								
								
								
								Color color=colorService.findByName(metalObj.get("color").toString());
								if(color !=null){
									orderMetal.setColor(color);
								}
								
								Purity purity=purityService.findByName(metalObj.get("purity").toString());
								if(purity !=null){
									orderMetal.setPurity(purity);
								}
								
								
					
								
								
								orderMetal.setMainMetal(metalObj.getBoolean("mainMetal"));
								orderMetal.setMetalPcs(metalObj.getInt("qty"));
								orderMetal.setMetalWeight(metalObj.getDouble("metalWt"));
								orderMetal.setCastWeight(Math.round((orderMetal.getMetalWeight()+(orderMetal.getMetalWeight()*orderMetal.getProcessLoss()/100))*1000.0)/1000.0);
								
								LookUpMast part = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", metalObj.getString("partNm").toString(),false);
								if(part !=null){
									orderMetal.setPartNm(part);	
								}
								
								
									if(bagMts.size()>0){
									
									for(BagMt bagMt :bagMts){
										
										List<TranMetal>tranMetals=tranMetalService.findByBagMtIdAndPartNmId(bagMt.getId(), orderMetal.getPartNm().getId());
										
										for(TranMetal tranMetal :tranMetals){
											if(tranMetal.getMetalWeight()>0){
												
											}else{
												
												tranMetal.setColor(orderMetal.getColor());
												tranMetal.setPurity(orderMetal.getPurity());
												
												tranMetalService.save(tranMetal);
												
											}
											
											
											
											
											
										}
										
										
										
									}
									
									
								}
								
				
								orderMetal.setOrderDt(orderDt);
								orderMetal.setOrderMt(orderMt);
								//orderMetal.setWaxWt(metalObj.getDouble("waxWt"));
								orderMetal.setModiBy(principal.getName());
								orderMetal.setModiDt(new Date());

								save(orderMetal);
								
								
							}
							
							
							
							
							
						}
						
					
						
					}
					
				}
				
				
				
			}

		
		
		
		
	}
		
		@Override
		public Boolean orderPartValidation(Integer ordDtId, Integer partId) {
			
			//QTranMt qTranMt = QTranMt.tranMt;
			
			QTranMetal qTranMetal = QTranMetal.tranMetal;
			JPAQuery query = new JPAQuery(entityManager);
			
			
			QCastingDt qCastingDt =QCastingDt.castingDt;
			JPAQuery query1 = new JPAQuery(entityManager);
					
			List<TranMetal> tranMetalList= null;
					
			tranMetalList = query.from(qTranMetal).
								where(qTranMetal.tranMt.orderDt.id.eq(ordDtId).and(qTranMetal.partNm.id.eq(partId).and(qTranMetal.metalWeight.gt(0)))).list(qTranMetal);
			// TODO Auto-generated method stub
			
			Boolean flag=false;
			if(tranMetalList.size()>0)
			{		
				
				flag=true;
			}
			
			if(flag.equals(false)){
							
				List<CastingDt>castingDts =query1.from(qCastingDt).
						where(qCastingDt.deactive.eq(false).and(qCastingDt.orderDt.id.eq(ordDtId))).list(qCastingDt);
				
				for(CastingDt castingDt :castingDts){
					
					TranMetal tranMetal =tranMetalService.findOne(castingDt.getRefTranMetalId());
					
					if(tranMetal.getPartNm().getId().equals(partId)){
						
						flag=true;
						break;
						
					}
					
					
					
					
				}
				
				
								
				
			}
			
			
			return flag;
		}
	
	
	@Override
	public List<OrderMetal> findByOrderMtAndDeactive(OrderMt orderMt, Boolean deactive) {
		return orderMetalRepository.findByOrderMtAndDeactive(orderMt, deactive);
	}

	@Override
	public void setOrderMetalDt(List<DesignMetal> designMetals, OrderMt orderMt, OrderDt orderDt, Principal principal) {
		
		if(designMetals != null){

			for(DesignMetal designMetal : designMetals){
	
				OrderMetal orderMetal = new OrderMetal();
				orderMetal.setOrderMt(orderMt);
				orderMetal.setOrderDt(orderDt);
				orderMetal.setMainMetal(designMetal.getMainMetal());
				orderMetal.setMetalPcs(designMetal.getMetalPcs());
				orderMetal.setPartNm(designMetal.getPartNm());
				orderMetal.setPurity(orderDt.getPurity());
				orderMetal.setColor(orderDt.getColor());
				orderMetal.setLossPerc(designMetal.getLossPerc());
				orderMetal.setCreatedBy(principal.getName());
				orderMetal.setCreateDate(new java.util.Date());
			//	orderMetal.setMetalWeight(Math.round((designMetal.getWaxWt() * designMetal.getPurity().getPurityConv())*1000.0)/1000.0);
				orderMetal.setProcessLoss(designMetal.getLossPerc());
				
				
				
				  if(designMetal.getWaxWt()>0) {
				  
				  Double vMetalWt =  Math.round((designMetal.getWaxWt() * orderDt.getPurity().getWaxWtConv())*1000.0)/1000.0;
				  
				  vMetalWt = vMetalWt-((vMetalWt*designMetal.getLossPerc())/100);
				  
				  orderMetal.setMetalWeight(Math.round((vMetalWt)*1000.0)/1000.0);
				  
				//  orderMetal.setMetalWeight(vMetalWt);
				  
				  }
				 
				
				orderMetalRepository.save(orderMetal);
				
				
				
			}
			
		}
		
	}

	@Override
	public String updateLossPerc(Principal principal, Integer id, Double lossPerc,Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		
		OrderMetal orderMetal = findOne(id);
		orderMetal.setLossPerc(lossPerc);
		save(orderMetal);
		orderDtService.updateFob(orderMetal.getOrderDt(), netWtWithCompFlg);
		
		return null;
	}


}

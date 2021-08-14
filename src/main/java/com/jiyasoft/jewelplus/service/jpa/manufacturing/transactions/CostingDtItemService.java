package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientKtConvMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingDtItem;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostingDtItemRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientKtConvService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterFLService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Transactional
@Service
@Repository
public class CostingDtItemService implements ICostingDtItemService{
	
	@Autowired
	private ICostingDtItemRepository costingDtItemRepository;
	
	@Autowired
	private IClientWastageService clientWastageService;
	
	@Autowired
	private IClientKtConvService clientKtConvService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IOrderLabDtService orderLabDtService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private ICostMetalDtItemService costMetalDtItemService;
	
	@Autowired
	private ICostStnDtItemService costStnDtItemService;
	
	@Autowired
	private ICostCompDtItemService costCompDtItemService;
	
	@Autowired
	private ICostLabDtItemService costLabDtItemService;
	

	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ICostMetalRateService costMetalRateService;
	
	@Autowired
	private ISettingChargeService settingChargeService;
	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;
	
	@Autowired
	private IHandlingMasterFLService handlingMasterFLService;
	
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private IFindingRateMastService findingRateMastService;

	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private IOrderMetalRateService orderMetalRateService;
	
	
	
	
	
	
	@Override
	public void delete(int id) {
	CostingDtItem costingDtItem =  findOne(id);
	costingDtItem.setDeactive(true);
	costingDtItem.setDeactiveDt(new Date());
	costingDtItemRepository.save(costingDtItem);
		
	}

	@Override
	public void save(CostingDtItem costingDtItem) {
		// TODO Auto-generated method stub
		costingDtItemRepository.save(costingDtItem);
	}

	@Override
	public CostingDtItem findOne(int id) {
		// TODO Auto-generated method stub
		return costingDtItemRepository.findOne(id);
	}

	@Override
	public CostingDtItem findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return costingDtItemRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<CostingDtItem> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return costingDtItemRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public String applyItemRate(CostingDtItem costingDtItem, Principal principal,Boolean netWtWithCompFlg) {
		
	String retVal="";
	
		
		if(costingDtItem.getrLock().equals(false)){

		applyItemMetal(costingDtItem);
		
		
		List<CostStnDtItem> costStnDtItems = costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
					
		
		applyItemStoneRate(costStnDtItems);
		
		List<CostCompDtItem> costCompDtItems = costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
		applyItemCompRate(costCompDtItems);
		
		applyItemLabRate(costingDtItem, principal);
		
		updateItemFob(costingDtItem,netWtWithCompFlg);
		retVal="1";
		
		}
		
		return retVal;
	}

	@Override
	public Page<CostingDtItem> searchAll(Integer limit, Integer offset,
			String sort, String order, String name, Integer mtId) {


		QCostingDtItem qCostingDtItem = QCostingDtItem.costingDtItem;
		BooleanExpression expression = qCostingDtItem.deactive.eq(false).and(qCostingDtItem.costingMt.id.eq(mtId));

		if(name !=null && name !=""){
			
		/*	
			expression = qCostingDtItem.deactive.eq(false).and(qCostingDtItem.costingMt.id.eq(mtId))
					.and(qCostingDtItem.design.mainStyleNo.like("%"+name+"%").or(qCostingDtItem.party.name.like("%"+name+"%")).or(qCostingDtItem.orderDt.orderMt.invNo.like("%"+name+"%"))
					.or(qCostingDtItem.orderDt.orderMt.refNo.like("%"+name+"%")));
*/		
			expression = qCostingDtItem.deactive.eq(false).and(qCostingDtItem.costingMt.id.eq(mtId)).and(qCostingDtItem.design.mainStyleNo.like(name + "%")
							.or(qCostingDtItem.orderDt.orderMt.invNo.like(name+"%")).or(qCostingDtItem.itemNo.like(name+"%")));	
		
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if(sort.equalsIgnoreCase("style")){
			sort = "design";
		}

		Page<CostingDtItem> costDtItemList = (Page<CostingDtItem>) costingDtItemRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return costDtItemList;
	}

	@Override
	public String applyItemMetal(CostingDtItem costingDtItem) {
		
		CostingMt costingMt =costingMtService.findOne(costingDtItem.getCostingMt().getId());
		
		if(costingMt.getParty().getMetalWtType().equalsIgnoreCase("As Per Order")) {
			OrderDt orderDt =orderDtService.findOne(costingDtItem.getOrderDt().getId());
			Double vnetDiff =Math.round((costingDtItem.getNetWt()-(orderDt.getNetWt()*costingDtItem.getPcs()))*1000.0)/1000.0;
			costingDtItem.setGrossWt(Math.round((orderDt.getGrossWt()*costingDtItem.getPcs())*1000.0)/1000.0);
			costingDtItem.setNetWt(Math.round((orderDt.getNetWt()*costingDtItem.getPcs())*1000.0)/1000.0);
			
			save(costingDtItem);
			
			CostMetalDtItem costMetalDtItem =costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(costingDtItem, false, true);
			costMetalDtItem.setMetalWeight(Math.round((costMetalDtItem.getMetalWeight()-vnetDiff)*1000.0)/1000.0);
			costMetalDtItemService.save(costMetalDtItem);
			
			
			
		} /*
			 * else { costingDtItem.setGrossWt(costingDtItem.getActGrossWt());
			 * costingDtItem.setNetWt(costingDtItem.getActNetWt()); save(costingDtItem);
			 * 
			 * CostMetalDtItem costMetalDtItem
			 * =costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(
			 * costingDtItem, false, true);
			 * costMetalDtItem.setMetalWeight(costMetalDtItem.getActMetalWeight());
			 * costMetalDtItemService.save(costMetalDtItem);
			 * 
			 * }
			 */
		

		
		/*
		 * if(costingMt.getParty().getMetalWtAddPerc()>0) {
		 * 
		 * Double vWtAdded=
		 * Math.round(((costingDtItem.getActGrossWt()*costingMt.getParty().
		 * getMetalWtAddPerc())/100)*1000.0)/1000.0;
		 * costingDtItem.setGrossWt(Math.round((costingDtItem.getActGrossWt()+vWtAdded)*
		 * 1000.0)/1000.0);
		 * costingDtItem.setNetWt(Math.round((costingDtItem.getActNetWt()+vWtAdded)*1000
		 * .0)/1000.0); save(costingDtItem);
		 * 
		 * 
		 * CostMetalDtItem costMetalDtItem
		 * =costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(
		 * costingDtItem, false, true);
		 * costMetalDtItem.setMetalWeight(Math.round((costMetalDtItem.getActMetalWeight(
		 * )+vWtAdded)*1000.0)/1000.0); costMetalDtItemService.save(costMetalDtItem); }
		 */
		
		
		
		List<CostMetalDtItem> costMetalDtItems = costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
		if(costMetalDtItems !=null){
			
			for(CostMetalDtItem costMetalDtItem:costMetalDtItems){
				
				if(costMetalDtItem.getrLock().equals(false)){
					if (costMetalDtItem.getPurity() != null) {
						Purity purity = purityService.findOne(costMetalDtItem.getPurity().getId());
																	
						CostMetalRate costMetalRate = costMetalRateService.findByCostingMtAndDeactiveAndMetal(costingMt, false, purity.getMetal());
						
						OrderMetalRate orderMetalRate =orderMetalRateService.findByOrderMtAndDeactiveAndMetal(costingDtItem.getOrderDt().getOrderMt(), false, purity.getMetal());
						
						if(orderMetalRate !=null && orderMetalRate.getRate()>0){
							costMetalDtItem.setMetalRate(orderMetalRate.getRate());
							
							
							if(orderMetalRate.getLossPerc()>0) {
								costMetalDtItem.setLossPerc(orderMetalRate.getLossPerc());
							}else {
								ClientWastage clientWastage =clientWastageService.findByMetalAndPartyAndDeactive(purity.getMetal(), costingMt.getParty(), false);
								if(clientWastage!=null){
									costMetalDtItem.setLossPerc(clientWastage.getWastagePerc());	
								}
							}
							
							
							costMetalDtItemService.save(costMetalDtItem);
							
							if(costMetalDtItem.getMainMetal().equals(true)){
								costingDtItem.setLossPercDt(costMetalDtItem.getLossPerc());
								save(costingDtItem);
							}
							
							
							
						}else{
							if(costMetalRate!=null){
								
								costMetalDtItem.setMetalRate(costMetalRate.getRate());
								
								
								if(costMetalRate.getLossPerc()>0) {
									costMetalDtItem.setLossPerc(costMetalRate.getLossPerc());
								}else {
									ClientWastage clientWastage =clientWastageService.findByMetalAndPartyAndDeactive(purity.getMetal(), costingMt.getParty(), false);
									if(clientWastage!=null){
										costMetalDtItem.setLossPerc(clientWastage.getWastagePerc());	
									}
								}
								
								
								
								costMetalDtItemService.save(costMetalDtItem);
								
								if(costMetalDtItem.getMainMetal().equals(true)){
									costingDtItem.setLossPercDt(costMetalDtItem.getLossPerc());
									save(costingDtItem);
								}
								
								
							}
						}
						
						

					}

				}

			}

		}
		
		
		return "";
	}

	@Override
	public String applyItemStoneRate(List<CostStnDtItem> costStnDtItems) {

		String retVal="error";
		
		Party party =costStnDtItems.get(0).getCostingMt().getParty();

			for(CostStnDtItem costStnDtItem: costStnDtItems){
			
			if(costStnDtItem.getrLock().equals(true)){
				continue;
			}
			
			
			if(party.getSetRateType().equalsIgnoreCase("As Per Order")) {
				
				Query query = entityManager.createNativeQuery("update coststndtitem a,sordstndt b,costdtitem c set a.setrate = b.setrate where c.sorddtid = b.dtid" + 
						" and a.deactive =0 and b.deactive =0 and a.partnm = b.partnm and a.stonetypeid = b.stonetypeid" + 
						" and a.shapeid=b.shapeid and a.qualityid = b.qualityid and a.size = b.size " + 
						" and a.settingid = b.settingid and a.settypeid =b.settypeid and a.rlock=0 and a.dtitemid = c.dtitemid and  a.stndtid ="+costStnDtItem.getId());
				
			     query.executeUpdate();
				
			}else {
				costStnDtItem=applySettingRate(costStnDtItem);	
			}
			
			
			costStnDtItem=applyStoneRate(costStnDtItem);
			
			if(party.getDiaRateType().equalsIgnoreCase("As Per Order")) {   
				
				Query query = entityManager.createNativeQuery("update coststndtitem a,sordstndt b,costdtitem c set a.stnrate = b.stnrate where c.sorddtid = b.dtid" + 
						" and a.deactive =0 and b.deactive =0 and a.partnm = b.partnm and a.stonetypeid = b.stonetypeid" + 
						" and a.shapeid=b.shapeid and a.qualityid = b.qualityid and a.size = b.size " + 
						" and a.settingid = b.settingid and a.settypeid =b.settypeid and a.rlock=0 and a.dtitemid = c.dtitemid and  a.stndtid ="+costStnDtItem.getId());
				
			     query.executeUpdate();
			
				
			}else if(party.getDiaRateType().equalsIgnoreCase("As Per Inward")){
				
				OrderMt orderMt  =costStnDtItem.getCostingDtItem().getOrderDt().getOrderMt();
				
				Query query = entityManager.createNativeQuery("update coststndtitem a,stonetran b,costdtitem c set a.stnrate = b.rate where c.sorddtid = b.sorddtid" + 
						" and a.deactive =0 and b.deactive =0 and  a.stonetypeid = b.stonetypeid" + 
						" and a.shapeid=b.shapeid and a.qualityid = b.qualityid and a.size = b.size " + 
						" and a.rlock=0 and b.trantype='inward' "
						+ "and a.dtitemid = c.dtitemid and  a.stndtid ="+costStnDtItem.getId());
				
			     query.executeUpdate();
			     
			     
			     
			 	Query query1 = entityManager.createNativeQuery("update coststndtitem a,stonetran b set a.stnrate = b.transferrate where " + 
						" a.deactive =0 and b.deactive =0 and  a.stonetypeid = b.stonetypeid" + 
						" and a.shapeid=b.shapeid and a.qualityid = b.qualityid and a.size = b.size " + 
						" and a.rlock=0 and b.trantype='ordAlloc' and b.sordmtid = "+orderMt.getId()+ 
						" and  a.stndtid ="+costStnDtItem.getId());
				
			     query1.executeUpdate();
				
				
				
				
				
			}
			
			
			costStnDtItem=applyHandlingRate(costStnDtItem);
			if(party.getHdlgRateType().equalsIgnoreCase("As Per Order")) {  
				
				Query query = entityManager.createNativeQuery("update coststndtitem a,sordstndt b,costdtitem c set a.HandlingRate = b.HandlingRate where c.sorddtid = b.dtid" + 
						" and a.deactive =0 and b.deactive =0 and a.partnm = b.partnm and a.stonetypeid = b.stonetypeid" + 
						" and a.shapeid=b.shapeid and a.qualityid = b.qualityid and a.size = b.size " + 
						" and a.settingid = b.settingid and a.settypeid =b.settypeid and a.rlock=0 and a.dtitemid = c.dtitemid and  a.stndtid ="+costStnDtItem.getId());
				
			     query.executeUpdate();
			}
			
			
			costStnDtItemService.save(costStnDtItem);
			
		}
			
		retVal="1";
		
		
		return retVal;
		
	}

	@Override
	public String applyItemCompRate(List<CostCompDtItem> costCompDtItems) {
		
		String retVal="error";
		
		for(CostCompDtItem costCompDtItem:costCompDtItems){
			
			if(costCompDtItem.getrLock().equals(true)){
				continue;
			}

			costCompDtItem=applyCompRate(costCompDtItem);
						
			costCompDtItemService.save(costCompDtItem);
			
				
				
				

			
			
		}
		
			retVal="1";
		return retVal;
	}

	@Override
	public String applyItemLabRate(CostingDtItem costingDtItem,
			Principal principal) {
		
		CostingMt costingMt =costingMtService.findOne(costingDtItem.getCostingMt().getId());
		
		if(costingMt.getParty().getLabRateType().equalsIgnoreCase("As Per Order")) {
			
			List<OrderLabDt>orderLabDts=orderLabDtService.findByOrderDtAndDeactive(costingDtItem.getOrderDt(), false);
			
			List<CostLabDtItem> costLabDtItems = costLabDtItemService.findByCostingDtItemAndDeactive(costingDtItem,false);
			for(CostLabDtItem costLabDtItem :costLabDtItems) {
				costLabDtItemService.delete(costLabDtItem.getId());
			}
			    
			
			for(OrderLabDt orderLabDt :orderLabDts) {
				CostLabDtItem costLabDtItem = new CostLabDtItem();
				
				costLabDtItem.setCostingMt(costingDtItem.getCostingMt());
				costLabDtItem.setCostingDtItem(costingDtItem);
				costLabDtItem.setLabourType(orderLabDt.getLabourType());
				costLabDtItem.setLabourRate(orderLabDt.getLabourRate());
				costLabDtItem.setMetal(orderLabDt.getMetal());
				costLabDtItem.setPerPcRate(orderLabDt.getPcsWise());
				costLabDtItem.setPerGramRate(orderLabDt.getGramWise());
				costLabDtItem.setPercentage(orderLabDt.getPercentWise());
				costLabDtItem.setPerCaratRate(orderLabDt.getPerCaratRate());
				costLabDtItem.setCreatedDate(new java.util.Date());
				costLabDtItem.setCreatedBy(principal.getName());
				
				costLabDtItemService.save(costLabDtItem);
				
			}
			
			
			
			
			
			
			
			
		}else {
			QCostMetalDtItem qCostMetalDtItem = QCostMetalDtItem.costMetalDtItem;
			JPAQuery query=new JPAQuery(entityManager);
			
			
			
			List<Tuple> costMetalItemList=null;
			
			costMetalItemList = query.from(qCostMetalDtItem).
					where(qCostMetalDtItem.deactive.eq(false).and(qCostMetalDtItem.costingDtItem.id.eq(costingDtItem.getId())))
					.groupBy(qCostMetalDtItem.purity.metal).list(qCostMetalDtItem.purity,qCostMetalDtItem.purity.metal.name,qCostMetalDtItem.metalWeight.sum(),qCostMetalDtItem.metalPcs.sum());
			
			
			
			for(Tuple tuple : costMetalItemList){
			
				QCostCompDtItem qCostCompDtItem = QCostCompDtItem.costCompDtItem;
				JPAQuery compQuery=new JPAQuery(entityManager);
				
						
				Metal metal =metalService.findByName(tuple.get(qCostMetalDtItem.purity.metal.name));
				
				
				List<Tuple>costCompList = compQuery.from(qCostCompDtItem).where(qCostCompDtItem.deactive.eq(false).
						and(qCostCompDtItem.costingDtItem.id.eq(costingDtItem.getId())).and(qCostCompDtItem.purity.metal.eq(metal)))
						.groupBy(qCostCompDtItem.purity.metal).list(qCostCompDtItem.metalWt.sum(),qCostCompDtItem.compPcs.sum());
				
				
				Double vCompWt=0.0;
				for(Tuple tupleComp : costCompList){
						vCompWt= Math.round((tupleComp.get(qCostCompDtItem.metalWt.sum())/costingDtItem.getPcs())*1000.0)/1000.0;
				}
				
				
				Double vMetalWt= Math.round((tuple.get(qCostMetalDtItem.metalWeight.sum())/costingDtItem.getPcs())*1000.0)/1000.0;
				
				Double vNetWt = Math.round((vMetalWt+vCompWt)*1000.0)/1000.0;
				
				
				
				List<LabourCharge> labourCharges=null;
				
				
				 labourCharges =labourChargeService.getPurityWiseRates(costingDtItem.getCostingMt().getParty(), costingDtItem.getDesign().getCategory(),
						 vNetWt,false, metal,tuple.get(qCostMetalDtItem.purity));
				 
				 if(labourCharges.size()<=0){
					 
					 labourCharges =labourChargeService.getRates(costingDtItem.getCostingMt().getParty(), costingDtItem.getDesign().getCategory(),
							 vNetWt,false, metal);
					 
				 }
				
				
				
				
				
				
				List<CostLabDtItem> costLabDtItems = costLabDtItemService.findByCostingDtItemAndMetalAndDeactive(costingDtItem, metal, false);
				
				Boolean isAvailable=false;
				for(LabourCharge labourCharge :labourCharges){
					isAvailable=false;
					for(CostLabDtItem costLabDtItem : costLabDtItems){
						if(costLabDtItem.getLabourType().equals(labourCharge.getLabourType())){
							
							isAvailable=true;
							if(costLabDtItem.getrLock().equals(false)){
								costLabDtItem.setLabourRate(labourCharge.getRate());	
								
								costLabDtItem.setPerPcRate(false);
								costLabDtItem.setPerGramRate(false);
								costLabDtItem.setPercentage(false);
								costLabDtItem.setPerCaratRate(false);
								
								if(labourCharge.getPerPcsRate() == true){
									costLabDtItem.setPerPcRate(true);
								}else if(labourCharge.getPerGramRate() == true){
									costLabDtItem.setPerGramRate(true);
								}else if(labourCharge.getPercentage() == true){
									costLabDtItem.setPercentage(true);
								}
								else if(labourCharge.getPerCaratRate() == true){
									costLabDtItem.setPerCaratRate(true);
								}
								
							}
							
						}
											
					}
					if(!isAvailable){
						
						CostLabDtItem costLabDtItem = new CostLabDtItem();
						
						costLabDtItem.setCostingMt(costingDtItem.getCostingMt());
						costLabDtItem.setCostingDtItem(costingDtItem);
						costLabDtItem.setLabourType(labourCharge.getLabourType());
						costLabDtItem.setLabourRate(labourCharge.getRate());
						costLabDtItem.setMetal(metal);
						
						if(labourCharge.getPerPcsRate() == true){
							costLabDtItem.setPerPcRate(true);
						}else if(labourCharge.getPerGramRate() == true){
							costLabDtItem.setPerGramRate(true);
						}else if(labourCharge.getPercentage() == true){
							costLabDtItem.setPercentage(true);
						}
						else if(labourCharge.getPerCaratRate() == true){
							costLabDtItem.setPerCaratRate(true);
						}
					
						costLabDtItem.setCreatedDate(new java.util.Date());
						costLabDtItem.setCreatedBy(principal.getName());
						
						costLabDtItemService.save(costLabDtItem);
						
						
					}
					
					
				}
				
				
				
				
				
			}
		}
		
		
		
		
			
	
		return "1";
	}

	@Override
	public String updateItemFob(CostingDtItem costingDtItem,Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		try {
			
			Double  tempCal = 0.0;
			Double  tempCal2 = 0.0;
			Double tempLoss=0.0;
			Double totMetalValue=0.0;
			Double totStnValue=0.0;
			Double totSetValue=0.0;
			Double totHdlgValue=0.0; 
			Double totCompValue=0.0;
			Double totLabourValue=0.0;
			Double totCarat=0.0;
			
			
			
			
			
			
			Double tempVal = 0.0;
		//	Double vFob = 0.0;
			
			
			if(costingDtItem.getrLock() == true){	
			}else{
				
			
		/*-------------------------------------------------Metal Fob--------------------------------*/		
				
				
				List<CostMetalDtItem> costMetalDtItems = costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				if(costMetalDtItems !=null){
					for(CostMetalDtItem costMetalDtItem: costMetalDtItems){
						if (costMetalDtItem.getPurity() != null && costMetalDtItem.getMetalRate()>0) {
							
							
							if(costingDtItem.getCostingMt().getParty().getType().getFieldValue().equalsIgnoreCase("Local")) {
								ClientKtConvMast clientKtConvMast=clientKtConvService.findByPartyAndPurityAndDeactive(costingDtItem.getCostingMt().getParty(), costMetalDtItem.getPurity(), false);
								
								if(clientKtConvMast !=null) {
									tempCal =  costMetalDtItem.getMetalRate()*clientKtConvMast.getPurityConv();
									costMetalDtItem.setPerGramRate(Math.round((tempCal)*100.0)/100.0);
									costMetalDtItem.setMetalValue(Math.round((costMetalDtItem.getMetalWeight()*costMetalDtItem.getPerGramRate())*100.0)/100.0);
									
									
								    
								}else {
									tempCal =  costMetalDtItem.getMetalRate()*costMetalDtItem.getPurity().getPurityConv();
									costMetalDtItem.setPerGramRate(Math.round((tempCal)*100.0)/100.0);
									costMetalDtItem.setMetalValue(Math.round((costMetalDtItem.getMetalWeight()*costMetalDtItem.getPerGramRate())*100.0)/100.0);
								}
								
							}else {
								Purity purity = purityService.findOne(costMetalDtItem.getPurity().getId());
								String metalNm=purity.getMetal().getName();
								if(metalNm.equalsIgnoreCase("Gold")){
									tempCal =  costMetalDtItem.getMetalRate()/costMetalDtItem.getPurity().getMetal().getSpecificGravity();
									tempCal2 = (tempCal/(costMetalDtItem.getCostingMt().getIn999().equals(true)?24:23.88)) * (costMetalDtItem.getPurity().getvPurity() != null ? costMetalDtItem.getPurity().getvPurity() : 0.0);
									tempLoss=tempCal2*costMetalDtItem.getLossPerc()/100;
									costMetalDtItem.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
									costMetalDtItem.setMetalValue(Math.round((costMetalDtItem.getMetalWeight()*costMetalDtItem.getPerGramRate())*100.0)/100.0);
								
								}else if (metalNm.equalsIgnoreCase("Silver")) {
									tempCal =  costMetalDtItem.getMetalRate()/costMetalDtItem.getPurity().getMetal().getSpecificGravity();
									tempCal2 = (tempCal*(costMetalDtItem.getPurity().getPurityConv() != null ? costMetalDtItem.getPurity().getPurityConv() : 0.0));
									/*
									 * if(costMetalDtItem.getCostingMt().getIn999().equals(true)){ tempCal2 =
									 * tempCal; }else{ tempCal2 =
									 * (tempCal*(costMetalDtItem.getPurity().getPurityConv() != null ?
									 * costMetalDtItem.getPurity().getPurityConv() : 0.0)); }
									 */	
									tempLoss=tempCal2*costMetalDtItem.getLossPerc()/100;
									costMetalDtItem.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
									costMetalDtItem.setMetalValue( Math.round((costMetalDtItem.getMetalWeight()*costMetalDtItem.getPerGramRate())*100.0)/100.0);
									
									
										
								}else if (metalNm.equalsIgnoreCase("Alloy")) {
									tempCal =  costMetalDtItem.getMetalRate()/1000;
									tempLoss=tempCal*costMetalDtItem.getLossPerc()/100;
									
									costMetalDtItem.setPerGramRate( Math.round((tempCal2 + tempLoss)*100.0)/100.0);
									costMetalDtItem.setMetalValue( Math.round((costMetalDtItem.getMetalWeight()*costMetalDtItem.getPerGramRate())*100.0)/100.0);
													
									
								}
							}
							
							
							
							
							
							
							
							
							totMetalValue +=costMetalDtItem.getMetalValue();
						
						}
						
						costMetalDtItemService.save(costMetalDtItem);
						
					}
					
				}
				
		/*---------------------------------------------------------------------------------------------------------*/			
				
		/*----------------------------------------------Stone Fob------------------------------------------------------------------*/			
				
				List<CostStnDtItem> costStnDtItems = costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				for(CostStnDtItem costStnDtItem : costStnDtItems){			
						
						if(costStnDtItem.getPerPcsRateFlg().equals(false)){
							costStnDtItem.setStoneValue(Math.round((costStnDtItem.getCarat() * costStnDtItem.getStnRate())*100.0)/100.0);
						}else{
							costStnDtItem.setStoneValue(Math.round((costStnDtItem.getStone() * costStnDtItem.getStnRate())*100.0)/100.0);
						}
										
						costStnDtItem.setSetValue(Math.round((costStnDtItem.getSetRate()*costStnDtItem.getStone())*100.0)/100.0);
						
					/*
					 * if(costStnDtItem.getHdlgPerCarat().equals(false)) {
					 * costStnDtItem.setHandlingRate(Math.round(((costStnDtItem.getStnRate()
					 * * costStnDtItem.getHandlingRate())/100)*100)/100); }
					 */
						costStnDtItem.setHandlingValue(Math.round((costStnDtItem.getCarat() * costStnDtItem.getHandlingRate())*100.0)/100.0);
						
					/*
					 * if(costStnDtItem.getHdlgPerCarat().equals(true)){
					 * costStnDtItem.setHandlingValue(Math.round((costStnDtItem.getCarat() *
					 * costStnDtItem.getHandlingRate())*100)/100); }else{
					 * costStnDtItem.setHandlingValue(Math.round(((costStnDtItem.
					 * getStoneValue() * costStnDtItem.getHandlingRate())/100)*100)/100); }
					 */
									
						costStnDtItemService.save(costStnDtItem);
						totCarat +=costStnDtItem.getCarat();
						totStnValue  	 += costStnDtItem.getStoneValue();
						totSetValue  	 += costStnDtItem.getSetValue();
						totHdlgValue 	 += costStnDtItem.getHandlingValue();
											
						
					}

		/*---------------------------------------------------------------------------------------------------------*/			
				
		/*----------------------------------------------Component Fob------------------------------------------------------------------*/		
				
				
				List<CostCompDtItem> costCompDtItems = costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				 
				for(CostCompDtItem costCompDtItem : costCompDtItems){
					if(costCompDtItem.getPerGramRate().equals(true)){
						costCompDtItem.setCompValue(Math.round((costCompDtItem.getMetalWt()* costCompDtItem.getCompRate())*100.0)/100.0);
					}else{
						costCompDtItem.setCompValue(Math.round((costCompDtItem.getCompPcs()* costCompDtItem.getCompRate())*100.0)/100.0);
					}
							
					costCompDtItemService.save(costCompDtItem);
					totCompValue += costCompDtItem.getCompValue();
					
					if(netWtWithCompFlg.equals(true)) {	
					if (costCompDtItem.getPurity() != null) {
						Purity purity = purityService.findOne(costCompDtItem.getPurity().getId());
																	
						CostMetalRate costMetalRate = costMetalRateService.findByCostingMtAndDeactiveAndMetal(costCompDtItem.getCostingMt(), false, purity.getMetal());
						
						OrderMetalRate orderMetalRate =orderMetalRateService.findByOrderMtAndDeactiveAndMetal(costCompDtItem.getCostingDtItem().getOrderDt().getOrderMt(), false, purity.getMetal());
						
						if(orderMetalRate !=null && orderMetalRate.getRate()>0){
							costCompDtItem.setMetalRate(orderMetalRate.getRate());
							
							
							if(orderMetalRate.getLossPerc()>0) {
								costCompDtItem.setLossPerc(orderMetalRate.getLossPerc());
							}else {
								ClientWastage clientWastage =clientWastageService.findByMetalAndPartyAndDeactive(purity.getMetal(), costCompDtItem.getCostingMt().getParty(), false);
								if(clientWastage!=null){
									costCompDtItem.setLossPerc(clientWastage.getWastagePerc());	
								}
							}
							
							
						
							
							costCompDtItemService.save(costCompDtItem);
							
						}else{
							if(costMetalRate!=null){
								
								costCompDtItem.setMetalRate(costMetalRate.getRate());
								
								if(costMetalRate.getLossPerc()>0) {
									costCompDtItem.setLossPerc(costMetalRate.getLossPerc());
								}else {
									ClientWastage clientWastage =clientWastageService.findByMetalAndPartyAndDeactive(purity.getMetal(), costCompDtItem.getCostingMt().getParty(), false);
									if(clientWastage!=null){
										costCompDtItem.setLossPerc(clientWastage.getWastagePerc());	
									}
								}
								
								
								costCompDtItemService.save(costCompDtItem);

							}
						}
						
						

					

				}
					
					
					
					
					
					if (costCompDtItem.getPurity() != null && costCompDtItem.getMetalRate()>0) {
						
						
						if(costingDtItem.getCostingMt().getParty().getType().getFieldValue().equalsIgnoreCase("Local")) {
							ClientKtConvMast clientKtConvMast=clientKtConvService.findByPartyAndPurityAndDeactive(costingDtItem.getCostingMt().getParty(), costCompDtItem.getPurity(), false);
							
							if(clientKtConvMast !=null) {
								tempCal =  costCompDtItem.getMetalRate()*clientKtConvMast.getPurityConv();
								costCompDtItem.setPerGramMetalRate(Math.round((tempCal)*100.0)/100.0);
								costCompDtItem.setMetalValue(Math.round((costCompDtItem.getMetalWt()*costCompDtItem.getPerGramMetalRate())*100.0)/100.0);
								
								
							    
							}else {
								tempCal =  costCompDtItem.getMetalRate()*costCompDtItem.getPurity().getPurityConv();
								costCompDtItem.setPerGramMetalRate(Math.round((tempCal)*100.0)/100.0);
								costCompDtItem.setMetalValue(Math.round((costCompDtItem.getMetalWt()*costCompDtItem.getPerGramMetalRate())*100.0)/100.0);
							}
							
						}else {
							
							Purity purity = purityService.findOne(costCompDtItem.getPurity().getId());
							String metalNm=purity.getMetal().getName();
							if(metalNm.equalsIgnoreCase("Gold")){
								tempCal =  costCompDtItem.getMetalRate()/costCompDtItem.getPurity().getMetal().getSpecificGravity();
								tempCal2 = (tempCal/(costCompDtItem.getCostingMt().getIn999().equals(true)?24:23.88)) * (costCompDtItem.getPurity().getvPurity() != null ? costCompDtItem.getPurity().getvPurity() : 0.0);
								tempLoss=tempCal2*costCompDtItem.getLossPerc()/100;
								costCompDtItem.setPerGramMetalRate( Math.round((tempCal2 + tempLoss)*100.0)/100.0);
								costCompDtItem.setMetalValue( Math.round((costCompDtItem.getMetalWt()*costCompDtItem.getPerGramMetalRate())*100.0)/100.0);
							
							}else if (metalNm.equalsIgnoreCase("Silver")) {
								tempCal =  costCompDtItem.getMetalRate()/costCompDtItem.getPurity().getMetal().getSpecificGravity();
								if(costCompDtItem.getCostingMt().getIn999().equals(true)){
									tempCal2 = tempCal;
								}else{
									tempCal2 = (tempCal*(costCompDtItem.getPurity().getPurityConv() != null ? costCompDtItem.getPurity().getPurityConv() : 0.0));	
								}
								
								tempLoss=tempCal2*costCompDtItem.getLossPerc()/100;
								costCompDtItem.setPerGramMetalRate( Math.round((tempCal2 + tempLoss)*100.0)/100.0);
								costCompDtItem.setMetalValue( Math.round((costCompDtItem.getMetalWt()*costCompDtItem.getPerGramMetalRate())*100.0)/100.0);
								
								
									
							}else if (metalNm.equalsIgnoreCase("Alloy")) {
								tempCal =  costCompDtItem.getMetalRate()/1000;
								tempLoss=tempCal*costCompDtItem.getLossPerc()/100;
								
								costCompDtItem.setPerGramMetalRate( Math.round((tempCal2 + tempLoss)*100.0)/100.0);
								costCompDtItem.setMetalValue( Math.round((costCompDtItem.getMetalWt()*costCompDtItem.getPerGramMetalRate())*100.0)/100.0);
												
								
							}
						}
						
						
					
						
						totMetalValue +=costCompDtItem.getMetalValue();
						
						costCompDtItemService.save(costCompDtItem);
					
					}
					
					
				}
					
					
					
				}
		/*---------------------------------------------------------------------------------------------------------*/				
				
		/*----------------------------------------------Labour Fob------------------------------------------------------------------*/		
				
				List<CostLabDtItem> costLabDtItems = costLabDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				
				
				for(CostLabDtItem costLabDtItem : costLabDtItems){
					
					
					Double vTotMetalWt=0.0;
					Double vTOtMetalVal=0.0;
					
					if (costMetalDtItems != null) {
						vTotMetalWt = 0.0;
						vTOtMetalVal=0.0;
						for (CostMetalDtItem costMetalDtItem : costMetalDtItems) {
							if (costLabDtItem.getMetal().getId().equals(costMetalDtItem.getPurity().getMetal().getId())) {
								vTotMetalWt += costMetalDtItem.getMetalWeight();
								vTOtMetalVal +=costMetalDtItem.getMetalValue();
							}

						}

					}
					
					
					QCostCompDtItem qCostCompDtItem = QCostCompDtItem.costCompDtItem;
					JPAQuery compQuery=new JPAQuery(entityManager);
					
					
					List<Tuple>costCompList = compQuery.from(qCostCompDtItem).where(qCostCompDtItem.deactive.eq(false).
							and(qCostCompDtItem.costingDtItem.id.eq(costingDtItem.getId())).and(qCostCompDtItem.purity.metal.eq(costLabDtItem.getMetal())))
							.groupBy(qCostCompDtItem.purity.metal).list(qCostCompDtItem.metalWt.sum(),qCostCompDtItem.metalValue.sum());
					
					
					for(Tuple tupleComp : costCompList){
						
						vTotMetalWt +=  tupleComp.get(qCostCompDtItem.metalWt.sum());
						vTOtMetalVal += tupleComp.get(qCostCompDtItem.metalValue.sum());
							
					}
					
					
					vTotMetalWt =  Math.round(vTotMetalWt*1000.0)/1000.0;
					
					vTOtMetalVal =  Math.round(vTOtMetalVal*100.0)/100.0;
					
					
					
					
					
					
					
					if(costLabDtItem.getPerPcRate() == true){
						costLabDtItem.setLabourValue(Math.round((costLabDtItem.getLabourRate() * costingDtItem.getPcs())*100.0)/100.0);
					
					}else if(costLabDtItem.getPerGramRate() == true){
						costLabDtItem.setLabourValue(Math.round((costLabDtItem.getLabourRate() * vTotMetalWt)*100.0)/100.0);
					}else if(costLabDtItem.getPercentage() == true){
						costLabDtItem.setLabourValue(Math.round(((vTOtMetalVal * costLabDtItem.getLabourRate())/100 )*100.0)/100.0);
					}else if(costLabDtItem.getPerCaratRate() == true){
						costLabDtItem.setLabourValue(Math.round((totCarat * costLabDtItem.getLabourRate())*100.0)/100.0);
					}
					
					
					
					
					
					costLabDtItemService.save(costLabDtItem);
					totLabourValue += costLabDtItem.getLabourValue();
					
				}
				
				
		/*---------------------------------------------------------------------------------------------------------*/					
				
				
				costingDtItem.setMetalValue(Math.round((totMetalValue)*100.0)/100.0);
				costingDtItem.setStoneValue(Math.round((totStnValue)*100.0)/100.0);
				costingDtItem.setSetValue(Math.round((totSetValue)*100.0)/100.0);
				costingDtItem.setHdlgValue(Math.round((totHdlgValue)*100.0)/100.0);
				costingDtItem.setCompValue(Math.round((totCompValue)*100.0)/100.0);
				costingDtItem.setLabValue(Math.round((totLabourValue)*100.0)/100.0);
				
				
				
										
				tempVal = costingDtItem.getMetalValue()+costingDtItem.getStoneValue()+costingDtItem.getCompValue()+costingDtItem.getLabValue()+costingDtItem.getSetValue()+costingDtItem.getHdlgValue();
				
				costingDtItem.setFob(Math.round((tempVal)*100.0)/100.0);
				
				
				costingDtItem.setDiscAmount(Math.round((costingDtItem.getPerPcDiscAmount()*costingDtItem.getPcs())*100.0)/100.0);
				
				
				
				
				costingDtItem.setFinalPrice( Math.round((costingDtItem.getFob()- costingDtItem.getDiscAmount())*100.0)/100.0);
				
				costingDtItem.setPerPcFinalPrice(Math.round((costingDtItem.getFinalPrice()/(costingDtItem.getPcs()>=1?costingDtItem.getPcs():1))*100.0)/100.0);
				
			
				
				costingDtItemRepository.save(costingDtItem);
				
				
				
				
				
			} // ending main else
			
			
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			
				
				return "";
	}

	@Override
	public String costingDtItemSave(Integer costMtId, Principal principal) {
		
		CostingMt costingMt = costingMtService.findOne(costMtId);
		
		String retVal = "-1";
		
		try {
			
			
			
			@SuppressWarnings("unchecked")
			List<Object[]> results =  entityManager.createNativeQuery("select a.sorddtid,a.dtitemid from costdtitem a "
					+ "where a.deactive =0 and a.sorddtid not in(select sorddtid from costdt where mtid="+costMtId+" and deactive =0) and a.mtid="+costMtId+" and a.deactive=0  ").getResultList();
			
			
			for (Object[] obj: results) {
				
				CostingDtItem costingDtItem2 = findOne((Integer) (obj[1]));
				
				List<CostMetalDtItem> costMetalDtItems = costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem2, false);
				for (CostMetalDtItem costMetalDtItem : costMetalDtItems) {
					costMetalDtItem.setDeactive(true);
					costMetalDtItem.setDeactiveDt(new Date());
					costMetalDtItemService.save(costMetalDtItem);
				}
				
				List<CostStnDtItem> costStnDtItems = costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem2, false);
				for (CostStnDtItem costStnDtItem : costStnDtItems) {
					costStnDtItem.setDeactive(true);
					costStnDtItem.setDeactiveDt(new Date());
					costStnDtItemService.save(costStnDtItem);
				}
				
				List<CostCompDtItem> costCompDtItem = costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem2, false);
				for (CostCompDtItem costCompDtItem2 : costCompDtItem) {
					costCompDtItem2.setDeactive(true);
					costCompDtItem2.setDeactiveDt(new Date());
					costCompDtItemService.save(costCompDtItem2);
					
				}
				
				List<CostLabDtItem> costLabDtItems =  costLabDtItemService.findByCostingDtItemAndDeactive(costingDtItem2, false);
				for (CostLabDtItem costLabDtItem : costLabDtItems) {
					costLabDtItem.setDeactive(true);
					costLabDtItem.setDeactiveDt(new Date());
					costLabDtItemService.save(costLabDtItem);
				}
				
				costingDtItem2.setDeactive(true);
				costingDtItem2.setDeactiveDt(new Date());
				save(costingDtItem2);
				
				
			}
			
			
			QCostingDt qCostingDt = QCostingDt.costingDt;
			
			List<Tuple> costingDtItemList = getCostingOrderDtList(costMtId, false);
		
			 Double vNetWt   = 0.0;
			 Double vGrossWt = 0.0;
			 Double vPcs     = 0.0;
			 
			 
		
			 
			for (Tuple tuple : costingDtItemList) {
				
			
				vNetWt = tuple.get(qCostingDt.netWt.sum());  
				vGrossWt = tuple.get(qCostingDt.grossWt.sum());
				vPcs = tuple.get(qCostingDt.pcs.sum());
				
				CostingDtItem costingDtItem = findByOrderDtAndCostingMtAndDeactive(tuple.get(qCostingDt.orderDt), costingMt, false);
				
		//CostMetal Item
				QCostingDt qCostingDt1 = QCostingDt.costingDt;
				QCostMetalDt qCostMetalDt =  QCostMetalDt.costMetalDt;
				JPAQuery query =  new JPAQuery(entityManager);
				
		//CostStn Item
				QCostingDt qCostingDt2 = QCostingDt.costingDt;
				QCostStnDt qCostStnDt = QCostStnDt.costStnDt;
				JPAQuery queryStn =  new JPAQuery(entityManager);
				
				
		//CostStn Item
				QCostingDt qCostingDt3 = QCostingDt.costingDt;
				QCostCompDt qCostCompDt = QCostCompDt.costCompDt;
				JPAQuery queryComp =  new JPAQuery(entityManager);		
				
				
				
				
				
								
			if(costingDtItem == null){
						
						
						CostingDtItem costingDtItem2 = new CostingDtItem();
						costingDtItem2.setUniqueId(new Date().getTime());
						costingDtItem2.setColor(tuple.get(qCostingDt.color));
						costingDtItem2.setColorRhod(tuple.get(qCostingDt.colorRhod));
						costingDtItem2.setCostingMt(costingMt);
						costingDtItem2.setCreatedBy(principal.getName());
						costingDtItem2.setCreatedDate(new Date());
						costingDtItem2.setDesign(tuple.get(qCostingDt.design));
						costingDtItem2.setOrderDt(tuple.get(qCostingDt.orderDt));
						costingDtItem2.setPurity(tuple.get(qCostingDt.purity));
						costingDtItem2.setPurityConv(tuple.get(qCostingDt.purityConv));
						costingDtItem2.setNetWt(Math.round(vNetWt*1000.0)/1000.0);
						costingDtItem2.setActNetWt(Math.round(vNetWt*1000.0)/1000.0);
						costingDtItem2.setGrossWt(Math.round(vGrossWt*1000.0)/1000.0);
						costingDtItem2.setActGrossWt(Math.round(vGrossWt*1000.0)/1000.0);
						costingDtItem2.setPcs(Math.round(vPcs*100.0)/100.0);
						int vSetNo = costingDtService.getMaxSetNoByOrder(costMtId,costingDtItem2.getOrderDt().getId());
						costingDtItem2.setSetNo(vSetNo);
						save(costingDtItem2);
						
						
					//CostMetal Add	
					
						
						List<Tuple> costMetalList =  query.from(qCostMetalDt).innerJoin(qCostMetalDt.costingDt,qCostingDt1).
								where(qCostMetalDt.deactive.eq(false).and(qCostMetalDt.costingMt.id.eq(costingMt.getId())).and(qCostingDt1.orderDt.id.eq(costingDtItem2.getOrderDt().getId())))
								.groupBy(qCostingDt1.orderDt,qCostMetalDt.partNm).list(qCostMetalDt.color,qCostMetalDt.purity,qCostMetalDt.partNm,qCostMetalDt.metalWeight.sum(),
										qCostMetalDt.metalPcs.sum(),qCostMetalDt.mainMetal);
						
						
						for (Tuple tuple2 : costMetalList) {
							
							CostMetalDtItem costMetalDtItem =  new CostMetalDtItem();
							costMetalDtItem.setColor(tuple2.get(qCostMetalDt.color));
							costMetalDtItem.setCostingDtItem(costingDtItem2);
							costMetalDtItem.setCostingMt(costingMt);
							costMetalDtItem.setCreateDate(new Date());
							costMetalDtItem.setCreatedBy(principal.getName());
							costMetalDtItem.setMetalPcs(tuple2.get(qCostMetalDt.metalPcs.sum()));
							costMetalDtItem.setMetalWeight(Math.round(tuple2.get(qCostMetalDt.metalWeight.sum())*1000.0)/1000.0);
							costMetalDtItem.setActMetalWeight(Math.round(tuple2.get(qCostMetalDt.metalWeight.sum())*1000.0)/1000.0);
							costMetalDtItem.setPartNm(tuple2.get(qCostMetalDt.partNm));
							costMetalDtItem.setPurity(tuple2.get(qCostMetalDt.purity));
							costMetalDtItem.setMainMetal(tuple2.get(qCostMetalDt.mainMetal));
							costMetalDtItemService.save(costMetalDtItem);
						
						}
						
						
					// CostStnDt Add
						
						List<Tuple> costStnList =  queryStn.from(qCostStnDt).innerJoin(qCostStnDt.costingDt,qCostingDt2).
								where(qCostStnDt.deactive.eq(false).and(qCostStnDt.costingMt.id.eq(costingMt.getId())).and(qCostingDt2.orderDt.id.eq(costingDtItem2.getOrderDt().getId())))
								.groupBy(qCostingDt2.orderDt,qCostStnDt.partNm,qCostStnDt.stoneType,qCostStnDt.shape,qCostStnDt.quality,qCostStnDt.size,qCostStnDt.sieve,
										qCostStnDt.sizeGroup,qCostStnDt.setting,qCostStnDt.settingType)
								.list(qCostStnDt.partNm,qCostStnDt.stoneType,qCostStnDt.shape,qCostStnDt.quality,qCostStnDt.size,qCostStnDt.sieve,qCostStnDt.sizeGroup,
										qCostStnDt.stone.sum(),qCostStnDt.carat.sum(),qCostStnDt.setting,qCostStnDt.settingType);
						
						
						for (Tuple tupleStn : costStnList) {
							
							CostStnDtItem costStnDtItem = new CostStnDtItem();
							costStnDtItem.setCarat(Math.round(tupleStn.get(qCostStnDt.carat.sum())*1000.0)/1000.0);
							costStnDtItem.setActCarat(costStnDtItem.getCarat());
							costStnDtItem.setCostingDtItem(costingDtItem2);
							costStnDtItem.setCostingMt(costingMt);
							costStnDtItem.setCreatedBy(principal.getName());
							costStnDtItem.setCreatedDate(new Date());
							costStnDtItem.setPartNm(tupleStn.get(qCostStnDt.partNm));
							costStnDtItem.setQuality(tupleStn.get(qCostStnDt.quality));
							costStnDtItem.setSetting(tupleStn.get(qCostStnDt.setting));
							costStnDtItem.setSettingType(tupleStn.get(qCostStnDt.settingType));
							costStnDtItem.setStone(tupleStn.get(qCostStnDt.stone.sum()));
							costStnDtItem.setActStone(costStnDtItem.getStone());
							costStnDtItem.setShape(tupleStn.get(qCostStnDt.shape));
							costStnDtItem.setSieve(tupleStn.get(qCostStnDt.sieve));
							costStnDtItem.setSize(tupleStn.get(qCostStnDt.size));
							costStnDtItem.setSizeGroup(tupleStn.get(qCostStnDt.sizeGroup));
							costStnDtItem.setStoneType(tupleStn.get(qCostStnDt.stoneType));
							
							
							Integer perStonePc = (int) Math.round((tupleStn.get(qCostStnDt.stone.sum())/(costingDtItem2.getPcs()==0.50?1:costingDtItem2.getPcs())));
							costStnDtItem.setPerStonePcs(perStonePc);
							
							Double perStoneWt=   Math.round((tupleStn.get(qCostStnDt.carat.sum())/(costingDtItem2.getPcs()==0.50?1:costingDtItem2.getPcs()))*1000.0)/1000.0;
							costStnDtItem.setPerStoneWt(perStoneWt);
							costStnDtItem.setTagWt(perStoneWt);
							
							
							costStnDtItemService.save(costStnDtItem);
							
							
						}
				
						
						
						
				//CostComp  Add	 
						
						List<Tuple> costCompList =  queryComp.from(qCostCompDt).innerJoin(qCostCompDt.costingDt,qCostingDt3).
								where(qCostCompDt.deactive.eq(false).and(qCostCompDt.costingMt.id.eq(costingMt.getId())).and(qCostingDt3.orderDt.id.eq(costingDtItem2.getOrderDt().getId())))
								.groupBy(qCostingDt3.orderDt,qCostCompDt.component,qCostCompDt.purity,qCostCompDt.color).list(qCostCompDt.component,qCostCompDt.purity,
										qCostCompDt.color,qCostCompDt.metalWt.sum(),qCostCompDt.purityConv,qCostCompDt.compPcs.sum());
						
						
						for (Tuple tupleComp : costCompList) {
							
							CostCompDtItem costCompDtItem =  new CostCompDtItem();
							costCompDtItem.setColor(tupleComp.get(qCostCompDt.color));
							costCompDtItem.setComponent(tupleComp.get(qCostCompDt.component));
							costCompDtItem.setCostingDtItem(costingDtItem2);
							costCompDtItem.setCostingMt(costingMt);
							costCompDtItem.setCreatedBy(principal.getName());
							costCompDtItem.setCreatedDate(new Date());
							costCompDtItem.setMetalWt(Math.round(tupleComp.get(qCostCompDt.metalWt.sum())*1000.0)/1000.0);
							costCompDtItem.setCompPcs(Math.round(tupleComp.get(qCostCompDt.compPcs.sum())*100.0)/100.0);
							costCompDtItem.setPurity(tupleComp.get(qCostCompDt.purity));
							costCompDtItem.setPurityConv(tupleComp.get(qCostCompDt.purityConv));
							costCompDtItemService.save(costCompDtItem);
						}
						
						
						
						
					}else{
						
						
						List<CostingDt>costingDts = costingDtService.findByOrderDtAndCostingMtAndDeactive(costingDtItem.getOrderDt(), costingDtItem.getCostingMt(), false);
						vNetWt=0.0;
						vGrossWt=0.0;
						vPcs=0.0;
						for(CostingDt costingDt :costingDts){
							vNetWt +=costingDt.getNetWt();
							vGrossWt +=costingDt.getGrossWt();
							vPcs += costingDt.getPcs();
							
						}
						
						
						costingDtItem.setModiBy(principal.getName());
						costingDtItem.setModiDate(new Date());
						costingDtItem.setNetWt(Math.round(vNetWt*1000.0)/1000.0);
						costingDtItem.setActNetWt(Math.round(vNetWt*1000.0)/1000.0);
						costingDtItem.setGrossWt(Math.round(vGrossWt*1000.0)/1000.0);
						costingDtItem.setActGrossWt(Math.round(vGrossWt*1000.0)/1000.0);
						costingDtItem.setPcs(Math.round(vPcs*100.0)/100.0);
						int vSetNo = costingDtService.getMaxSetNoByOrder(costMtId,costingDtItem.getOrderDt().getId());
						costingDtItem.setSetNo(vSetNo);
						costingDtItem.setrLock(false);
						save(costingDtItem);
						
			//CostMetal Edit
						
						List<CostMetalDtItem>costMetalDtItems= costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
						
						List<Tuple> costMetalList =  query.from(qCostMetalDt).innerJoin(qCostMetalDt.costingDt,qCostingDt1).
								where(qCostMetalDt.deactive.eq(false).and(qCostMetalDt.costingMt.id.eq(costingMt.getId())).and(qCostingDt1.orderDt.id.eq(costingDtItem.getOrderDt().getId())))
								.groupBy(qCostingDt1.orderDt,qCostMetalDt.partNm).list(qCostMetalDt.color,qCostMetalDt.purity,qCostMetalDt.partNm,qCostMetalDt.metalWeight.sum(),
										qCostMetalDt.metalPcs.sum(),qCostMetalDt.mainMetal);
						
						for(CostMetalDtItem costMetalDtItem :costMetalDtItems){
							Boolean flg=false;
							for (Tuple tuple2 : costMetalList){
								if(tuple2.get(qCostMetalDt.color).equals(costMetalDtItem.getColor()) && tuple2.get(qCostMetalDt.purity).equals(costMetalDtItem.getPurity())
										&& tuple2.get(qCostMetalDt.partNm).equals(costMetalDtItem.getPartNm())){
									
									flg=true;
									break;
									
								}
								
								
							}
							
							if(flg.equals(false)){
								
								costMetalDtItem.setDeactive(true);
								costMetalDtItem.setDeactiveDt(new Date());
								costMetalDtItemService.save(costMetalDtItem);
							}
							
						}
						
						
						
					
						for (Tuple tuple2 : costMetalList) {
						CostMetalDtItem costMetalDtItemEdit =  costMetalDtItemService.findByCostingDtItemAndDeactiveAndPartNm(costingDtItem, false,tuple2.get(qCostMetalDt.partNm));
						
						if(costMetalDtItemEdit == null){
							
							CostMetalDtItem costMetalDtItem =  new CostMetalDtItem();
							costMetalDtItem.setColor(tuple2.get(qCostMetalDt.color));
							costMetalDtItem.setCostingDtItem(costingDtItem);
							costMetalDtItem.setCostingMt(costingMt);
							costMetalDtItem.setCreateDate(new Date());
							costMetalDtItem.setCreatedBy(principal.getName());
							costMetalDtItem.setMetalPcs(tuple2.get(qCostMetalDt.metalPcs.sum()));
							costMetalDtItem.setMetalWeight(Math.round(tuple2.get(qCostMetalDt.metalWeight.sum())*1000.0)/1000.0);
							costMetalDtItem.setActMetalWeight(Math.round(tuple2.get(qCostMetalDt.metalWeight.sum())*1000.0)/1000.0);
							costMetalDtItem.setPartNm(tuple2.get(qCostMetalDt.partNm));
							costMetalDtItem.setPurity(tuple2.get(qCostMetalDt.purity));
							costMetalDtItem.setMainMetal(tuple2.get(qCostMetalDt.mainMetal));
							costMetalDtItemService.save(costMetalDtItem);
							
						}else{
							costMetalDtItemEdit.setMetalPcs(tuple2.get(qCostMetalDt.metalPcs.sum()));
							costMetalDtItemEdit.setMetalWeight(Math.round(tuple2.get(qCostMetalDt.metalWeight.sum())*1000.0)/1000.0);
							costMetalDtItemEdit.setActMetalWeight(Math.round(tuple2.get(qCostMetalDt.metalWeight.sum())*1000.0)/1000.0);
							costMetalDtItemEdit.setMainMetal(tuple2.get(qCostMetalDt.mainMetal));
							costMetalDtItemEdit.setModiBy(principal.getName());
							costMetalDtItemEdit.setModiDt(new Date());
							costMetalDtItemEdit.setrLock(false);
							costMetalDtItemService.save(costMetalDtItemEdit);
								
						}
						
						}
						
						
						// CostStnDt Edit
						
						List<CostStnDtItem>costStnDtItems =costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
					
						List<Tuple> costStnList =  queryStn.from(qCostStnDt).innerJoin(qCostStnDt.costingDt,qCostingDt2).
								where(qCostStnDt.deactive.eq(false).and(qCostStnDt.costingMt.id.eq(costingMt.getId()))
										.and(qCostingDt2.orderDt.id.eq(costingDtItem.getOrderDt().getId())))
								.groupBy(qCostingDt2.orderDt,qCostStnDt.partNm,qCostStnDt.stoneType,qCostStnDt.shape,qCostStnDt.quality,qCostStnDt.size,qCostStnDt.sieve,
										qCostStnDt.sizeGroup,qCostStnDt.setting,qCostStnDt.settingType)
								.list(qCostStnDt.partNm,qCostStnDt.stoneType,qCostStnDt.shape,qCostStnDt,qCostStnDt.quality,qCostStnDt.size,qCostStnDt.sieve,qCostStnDt.sizeGroup,
										qCostStnDt.stone.sum(),qCostStnDt.carat.sum(),qCostStnDt.setting,qCostStnDt.settingType);
						
						for(CostStnDtItem costStnDtItem:costStnDtItems){
							Boolean flg=false;
							for (Tuple tupleStn : costStnList){
								if(tupleStn.get(qCostStnDt.partNm).equals(costStnDtItem.getPartNm())
										&& tupleStn.get(qCostStnDt.stoneType).equals(costStnDtItem.getStoneType())
										&& tupleStn.get(qCostStnDt.shape).equals(costStnDtItem.getShape())
										&& tupleStn.get(qCostStnDt.quality).equals(costStnDtItem.getQuality())
										&& tupleStn.get(qCostStnDt.size).equalsIgnoreCase(costStnDtItem.getSize())
										&& tupleStn.get(qCostStnDt.setting).equals(costStnDtItem.getSetting())
										&& tupleStn.get(qCostStnDt.settingType).equals(costStnDtItem.getSettingType())){
									
									flg=true;
									break;
									
								}
								
								
							}
							
							if(flg.equals(false)){
								
								costStnDtItem.setDeactive(true);
								costStnDtItem.setDeactiveDt(new Date());
								costStnDtItemService.save(costStnDtItem);
							}
							
						}
					
					
						
						
						for (Tuple tupleStn : costStnList) {
						
						CostStnDtItem costStnDtItem = costStnDtItemService.findByCostingDtItemAndPartNmAndQualityAndSettingAndSettingTypeAndShapeAndSieveAndSizeAndSizeGroupAndStoneType
								(costingDtItem, tupleStn.get(qCostStnDt.partNm), tupleStn.get(qCostStnDt.quality), tupleStn.get(qCostStnDt.setting), tupleStn.get(qCostStnDt.settingType), 
										tupleStn.get(qCostStnDt.shape), tupleStn.get(qCostStnDt.sieve), tupleStn.get(qCostStnDt.size), tupleStn.get(qCostStnDt.sizeGroup), tupleStn.get(qCostStnDt.stoneType));
						
						if(costStnDtItem == null){
							
							CostStnDtItem costStnDtItem2 = new CostStnDtItem();
							costStnDtItem2.setCarat(Math.round(tupleStn.get(qCostStnDt.carat.sum())*1000.0)/1000.0);
							costStnDtItem2.setActCarat(costStnDtItem2.getCarat());
							costStnDtItem2.setCostingDtItem(costingDtItem);
							costStnDtItem2.setCostingMt(costingMt);
							costStnDtItem2.setCreatedBy(principal.getName());
							costStnDtItem2.setCreatedDate(new Date());
							costStnDtItem2.setPartNm(tupleStn.get(qCostStnDt.partNm));
							costStnDtItem2.setQuality(tupleStn.get(qCostStnDt.quality));
							costStnDtItem2.setSetting(tupleStn.get(qCostStnDt.setting));
							costStnDtItem2.setSettingType(tupleStn.get(qCostStnDt.settingType));
							costStnDtItem2.setStone(Math.round(tupleStn.get(qCostStnDt.stone.sum())));
							costStnDtItem2.setActStone(costStnDtItem2.getStone());
							
							Integer perStonePc = (int) Math.round((tupleStn.get(qCostStnDt.stone.sum())/(costingDtItem.getPcs()==0.50?1:costingDtItem.getPcs())));
							costStnDtItem2.setPerStonePcs(perStonePc);
							
							Double perStoneWt=   Math.round((tupleStn.get(qCostStnDt.carat.sum())/(costingDtItem.getPcs()==0.50?1:costingDtItem.getPcs()))*1000.0)/1000.0;
							costStnDtItem2.setPerStoneWt(perStoneWt);
							costStnDtItem2.setTagWt(perStoneWt);
							
							costStnDtItem2.setShape(tupleStn.get(qCostStnDt.shape));
							costStnDtItem2.setSieve(tupleStn.get(qCostStnDt.sieve));
							costStnDtItem2.setSize(tupleStn.get(qCostStnDt.size));
							costStnDtItem2.setSizeGroup(tupleStn.get(qCostStnDt.sizeGroup));
							costStnDtItem2.setStoneType(tupleStn.get(qCostStnDt.stoneType));
							costStnDtItemService.save(costStnDtItem2);
							
						}else{
							costStnDtItem.setStone(Math.round(tupleStn.get(qCostStnDt.stone.sum())));
							costStnDtItem.setCarat(Math.round(tupleStn.get(qCostStnDt.carat.sum())*1000.0)/1000.0);
							costStnDtItem.setActStone(costStnDtItem.getStone());
							costStnDtItem.setActCarat(costStnDtItem.getCarat());
							
							Integer perStonePc = (int) Math.round((tupleStn.get(qCostStnDt.stone.sum())/(costingDtItem.getPcs()==0.50?1:costingDtItem.getPcs())));
							costStnDtItem.setPerStonePcs(perStonePc);
							
							Double perStoneWt=   Math.round((tupleStn.get(qCostStnDt.carat.sum())/(costingDtItem.getPcs()==0.50?1:costingDtItem.getPcs()))*1000.0)/1000.0;
							costStnDtItem.setPerStoneWt(perStoneWt);
							costStnDtItem.setTagWt(perStoneWt);
							
							
							costStnDtItem.setModiBy(principal.getName());
							costStnDtItem.setModiDate(new Date());
							costStnDtItem.setrLock(false);
							costStnDtItemService.save(costStnDtItem);
							
						}
						
						}
						
						
					// CostComp Edit
						
						
						
						List<CostCompDtItem>costCompDtItems=costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
						
						List<Tuple> costCompList =  queryComp.from(qCostCompDt).innerJoin(qCostCompDt.costingDt,qCostingDt3).
								where(qCostCompDt.deactive.eq(false).and(qCostCompDt.costingMt.id.eq(costingMt.getId())).and(qCostingDt3.orderDt.id.eq(costingDtItem.getOrderDt().getId())))
								.groupBy(qCostingDt2.orderDt,qCostCompDt.component,qCostCompDt.purity,qCostCompDt.color).list(qCostCompDt.component,qCostCompDt.purity,
										qCostCompDt.color,qCostCompDt.metalWt.sum(),qCostCompDt.purityConv,qCostCompDt.compPcs.sum());
						
						
						for(CostCompDtItem costCompDtItem:costCompDtItems){
							
							Boolean flg=false;
							for (Tuple tupleComp : costCompList){
								if(tupleComp.get(qCostCompDt.component).equals(costCompDtItem.getComponent())
										&& tupleComp.get(qCostCompDt.color).equals(costCompDtItem.getColor()) 
										&& tupleComp.get(qCostCompDt.purity).equals(costCompDtItem.getPurity())){
									
									flg=true;
									break;
									
								}
								
								
							}
							
							if(flg.equals(false)){
								
								costCompDtItem.setDeactive(true);
								costCompDtItem.setDeactiveDt(new Date());
								costCompDtItemService.save(costCompDtItem);
							}
							
							
							
							
						}
						
						
						
						
						
						
						for (Tuple tupleComp : costCompList) {
							
							CostCompDtItem costCompDtItem = costCompDtItemService.findByCostingDtItemAndComponentAndPurityAndColor(costingDtItem, tupleComp.get(qCostCompDt.component), 
									tupleComp.get(qCostCompDt.purity), tupleComp.get(qCostCompDt.color));
							
							
							if(costCompDtItem == null){
								CostCompDtItem costCompDtItem2 =  new CostCompDtItem();
								costCompDtItem2.setColor(tupleComp.get(qCostCompDt.color));
								costCompDtItem2.setComponent(tupleComp.get(qCostCompDt.component));
								costCompDtItem2.setCostingDtItem(costingDtItem);
								costCompDtItem2.setCostingMt(costingMt);
								costCompDtItem2.setCreatedBy(principal.getName());
								costCompDtItem2.setCreatedDate(new Date());
								costCompDtItem2.setMetalWt(Math.round(tupleComp.get(qCostCompDt.metalWt.sum())*1000.0)/1000.0);
								costCompDtItem2.setCompPcs(Math.round(tupleComp.get(qCostCompDt.compPcs.sum())*100.0)/100.0);
								costCompDtItem2.setPurity(tupleComp.get(qCostCompDt.purity));
								costCompDtItem2.setPurityConv(tupleComp.get(qCostCompDt.purityConv));
								costCompDtItemService.save(costCompDtItem2);
								
							}else{
							
							costCompDtItem.setMetalWt(Math.round(tupleComp.get(qCostCompDt.metalWt.sum())*1000.0)/1000.0);
							costCompDtItem.setCompPcs(Math.round(tupleComp.get(qCostCompDt.compPcs.sum())*100.0)/100.0);
							costCompDtItem.setModiBy(principal.getName());
							costCompDtItem.setModiDate(new Date());
							costCompDtItem.setrLock(false);
							costCompDtItemService.save(costCompDtItem);
							}
							
						}
						
						
						
					
						
					}
					
					
				
					
	
					
					
					
				
			}
			
			
			
		
			
			retVal ="1";
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		

		
		return retVal;
	}

	@Override
	public List<Tuple> getCostingOrderDtList(Integer invId, Boolean deactive) {
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Tuple> costingDtList =null;
		costingDtList = query.from(qCostingDt).where(qCostingDt.deactive.eq(false).and(qCostingDt.costingMt.id.eq(invId)).and(qCostingDt.rLock.eq(false))).
				groupBy(qCostingDt.orderDt.id).list(qCostingDt.orderDt, qCostingDt.costingMt,qCostingDt.color,qCostingDt.colorRhod,qCostingDt.costingMt,
						qCostingDt.design,qCostingDt.purity,qCostingDt.purityConv,qCostingDt.netWt.sum(),qCostingDt.grossWt.sum(),qCostingDt.pcs.sum());
		
				
		return costingDtList;
	}

	@Override
	public CostingDtItem findByOrderDtAndCostingMtAndDeactive(OrderDt orderDt,CostingMt costingMt,Boolean deactive) {
		return costingDtItemRepository.findByOrderDtAndCostingMtAndDeactive(orderDt, costingMt, deactive);
	}

	@Override
	public String costingDtItemSave(Integer costDtItemId,Double perPcDiscAmount, Principal principal,Boolean netWtWithCompFlg) {
		
		String retVal = "-1";

		try {
			
			
			CostingDtItem costingDtItem = findOne(costDtItemId);
			
			
		
		/*			
			Double vGrossWtDiff=costingDtItem.getGrossWt()-grossWt;
			
			
			costingDtItem.setGrossWt(grossWt);
			costingDtItem.setNetWt(Math.round((costingDtItem.getNetWt()-vGrossWtDiff)*1000)/1000);*/
			
			costingDtItem.setPerPcDiscAmount(perPcDiscAmount);
			
			save(costingDtItem);
			
			
			updateItemFob(costingDtItem,netWtWithCompFlg);
				
			} catch (Exception e) {
					// TODO: handle exception
				}
				
				return retVal;
	}

	@Override
	public String deleteCostDtItem(Integer costDtId, Principal principal) {
		
		String retVal = "-1";
		
		try {

			CostingDtItem costingDtItem = findOne(costDtId);
			
			if(costingDtItem.getCostingMt().getExpClose() == true){
				retVal = "-2";
			}else{
			
				delete(costDtId);
				
				List<CostStnDtItem> costStnDtItems = costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				for(CostStnDtItem costStnDtItem:costStnDtItems){
					costStnDtItemService.delete(costStnDtItem.getId());
				}
				
				List<CostCompDtItem> costCompDtItems = costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				for(CostCompDtItem costCompDtItem:costCompDtItems){
					costCompDtItemService.delete(costCompDtItem.getId());
				}
				
				List<CostLabDtItem> costLabDtItems = costLabDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				for(CostLabDtItem costLabDtItem:costLabDtItems){
					costLabDtItemService.delete(costLabDtItem.getId());
				}
				
				List<CostMetalDtItem> costMetalDtItems= costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				for (CostMetalDtItem costMetalDtItem : costMetalDtItems) {
					costMetalDtItemService.delete(costMetalDtItem.getId());
				}
				
				 retVal = "-3";
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "Error : Contact Support";
		}
		
		return retVal;
	}

	@Override
	public String updateNetWt(CostingDtItem costingDtItem,Boolean netWtWithComp) {

		

		 Double totNetWt=0.0;	
		 List<CostMetalDtItem> costMetalDtItems=costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
		 if(costMetalDtItems.size()>0){
			 for(CostMetalDtItem costMetalDtItem :costMetalDtItems){
				 totNetWt  += costMetalDtItem.getMetalWeight();	 
			 }
			 
		 }
		 
				
		 List<CostStnDtItem>costStnDtItems=costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
			Double totStnCarat = 0.0;
			for(CostStnDtItem costStnDtItem:costStnDtItems){
				totStnCarat += costStnDtItem.getCarat();
			}
			
			Double temVal = totStnCarat/5;
			Double totGrossWt = totNetWt+temVal;
			
			List<CostCompDtItem> costCompDtItems = costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
			Double totCompMetalWt = 0.0;
			for(CostCompDtItem costCompDtItem:costCompDtItems){
				totCompMetalWt += costCompDtItem.getMetalWt();
			}
			
			totGrossWt += totCompMetalWt;
			
			
			Double grossWtdiff = Math.round((costingDtItem.getGrossWt()-totGrossWt)*1000.0)/1000.0;
			
			CostMetalDtItem costMetalDtItem = costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(costingDtItem, false,true);
			costMetalDtItem.setMetalWeight(Math.round((costMetalDtItem.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
			costMetalDtItemService.save(costMetalDtItem);	
			
			
			if(netWtWithComp.equals(true)){
				
				totNetWt +=  totCompMetalWt+grossWtdiff;
			}else {
				totNetWt += grossWtdiff;
			}
			
		
			costingDtItem.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
			
			save(costingDtItem);
		
		
		return null;
	}
	
	
	@Override
	public int getUpdateBarcode(Integer costMtId, Principal principal) {
		
		Query query = entityManager.createNativeQuery("update costdtitem a,sorddt b set a.itemno = b.barcode where a.sorddtid = b.dtid and a.deactive =0 and b.deactive =0 and a.mtid ="+costMtId);
		
		int retval = query.executeUpdate();

		return retval;
		
	}

	@Override
	public CostStnDtItem applySettingRate(CostStnDtItem costStnDtItem) {
		
		CostMetalDtItem costMetalDtItem = costMetalDtItemService.findByCostingDtItemAndDeactiveAndPartNm(costStnDtItem.getCostingDtItem(), false, costStnDtItem.getPartNm());
		
		if(costMetalDtItem !=null){
			
			Double pointerWt =Math.round((costStnDtItem.getTagWt()/costStnDtItem.getPerStonePcs())*1000.0)/1000.0 ;
			
			if(costStnDtItem.getStoneType() != null && costStnDtItem.getShape() != null &&	costStnDtItem.getSetting() != null && costStnDtItem.getSettingType() != null){
				
				List<SettingCharge> settingChargeList = settingChargeService.getRates(costStnDtItem.getCostingMt().getParty(),pointerWt,
						false,costMetalDtItem.getPurity().getMetal(),costStnDtItem.getStoneType(),costStnDtItem.getShape(),costStnDtItem.getSetting(),costStnDtItem.getSettingType());
				
				SettingCharge settingCharge=null;
				
				if(settingChargeList.size()>0){
					settingCharge = settingChargeList.get(0);
				}
				
									
				if(settingCharge != null){
					
					if(settingCharge.getQualityWiseRate().equals(true)){
						
						List<SettingQualityRate>settingQualityRates=settingQualityRateService.findBySettingChargeAndDeactive(settingCharge, false);
						Boolean isAvailable=false;
						for(SettingQualityRate settingQualityRate:settingQualityRates){
							if(settingQualityRate.getQuality().equals(costStnDtItem.getQuality())){
								costStnDtItem.setSetRate(settingQualityRate.getQualityRate());
								isAvailable=true;
								
							}
						}
						
						if(isAvailable.equals(false)){
							costStnDtItem.setSetRate(settingCharge.getRate());
						}
						
						
						
						
					}else{
						costStnDtItem.setSetRate(settingCharge.getRate());
					}
					
					
					
				}
				
			}
			
			//costStnDtItemService.save(costStnDtItem);
			
		//	retVal=costStnDtItem.getSetRate();
		
		}
		return costStnDtItem;
	}

	@Override
	public CostStnDtItem applyHandlingRate(CostStnDtItem costStnDtItem) {
			
		if(costStnDtItem.getStnRate() >0){
			
			List<HandlingMasterFl> handlingList = handlingMasterFLService.getRates(costStnDtItem.getCostingMt().getParty(),costStnDtItem.getStnRate());
			
			if(handlingList.size() > 0){
				if(handlingList.get(0).getPercentage() > 0){
					
					
						costStnDtItem.setHandlingRate(Math.round(((costStnDtItem.getStnRate() * handlingList.get(0).getPercentage())/100)*100.0)/100.0);
					
					
					//costStnDtItem.setHandlingRate(handlingList.get(0).getPercentage());
					costStnDtItem.setHdlgPerCarat(false);
					costStnDtItem.setHdlgPercentWise(true);
					
				}else{
					costStnDtItem.setHandlingRate(handlingList.get(0).getRate());
					costStnDtItem.setHdlgPerCarat(true);
					costStnDtItem.setHdlgPercentWise(false);
					
				}
			}else{
				costStnDtItem.setHandlingRate(0.0);
				costStnDtItem.setHdlgPerCarat(false);
				costStnDtItem.setHdlgPercentWise(false);
			}
			
			
			

		//	costStnDtItemService.save(costStnDtItem);
			
			//retVal="success";
			
			
		}
		return costStnDtItem;
	}

	@Override
	public CostStnDtItem applyStoneRate(CostStnDtItem costStnDtItem) {
		
		
		if(costStnDtItem.getStoneType() != null && costStnDtItem.getShape() != null &&  costStnDtItem.getQuality() != null ){
			
			List<StoneRateMast> stoneRateMast=stoneRateMastService.getStoneRate(costStnDtItem.getStoneType().getId(),costStnDtItem.getShape().getId(),costStnDtItem.getQuality().getId(), 
					costStnDtItem.getSizeGroup().getId(),costStnDtItem.getCostingMt().getParty().getId(),costStnDtItem.getSieve());
			
			if(stoneRateMast.size() > 0){
				if(stoneRateMast.get(0).getPerPcRate() > 0){
					costStnDtItem.setStnRate(stoneRateMast.get(0).getPerPcRate());
					costStnDtItem.setPerPcsRateFlg(true);
					
				}else{
					costStnDtItem.setStnRate(stoneRateMast.get(0).getStoneRate());
					costStnDtItem.setPerPcsRateFlg(false);
					
				}
			}
			
			//costStnDtItemService.save(costStnDtItem);
			
			
			
			
		
		}
		return costStnDtItem;
	}

	@Override
	public CostCompDtItem applyCompRate(CostCompDtItem costCompDtItem) {
		
		if(costCompDtItem.getComponent() != null && costCompDtItem.getPurity() != null){
			
			FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(costCompDtItem.getComponent(), costCompDtItem.getCostingMt().getParty(),
					costCompDtItem.getPurity(), false);
			
			if(findingRateMast != null){
				if(findingRateMast.getPerPcRate().equals(true)){
					costCompDtItem.setCompRate(findingRateMast.getRate());
					costCompDtItem.setPerPcRate(true);
					costCompDtItem.setPerGramRate(false);
				}else{
					costCompDtItem.setCompRate(findingRateMast.getRate());
					costCompDtItem.setPerPcRate(false);
					costCompDtItem.setPerGramRate(true);
				}
			}
			
		}
		return costCompDtItem;
	}

	@Override
	public Page<CostingDtItem> costingItemListBySetNo(Integer limit,
			Integer offset, String sort, String order, String name,
			String mtIds, Integer setNo) {
		
			List<Integer> mtIdList = new ArrayList<Integer>();
		
		if(mtIds.length() > 0){
			String ids[] = mtIds.split(",");
			for(int i=0;i<ids.length;i++){
				mtIdList.add(Integer.parseInt(ids[i]));
			}
		}else{
			mtIdList.add(0);
		}
		
		
		
		QCostingDtItem qCostingDtItem = QCostingDtItem.costingDtItem;
		BooleanBuilder expression =new BooleanBuilder();
		expression.and(qCostingDtItem.deactive.eq(false).and(qCostingDtItem.costingMt.id.in(mtIdList)));
		
		if(setNo >0){
			expression.and(qCostingDtItem.setNo.eq(setNo));
		}
		
		
		

		if(name !=null && name !=""){
			expression.and(qCostingDtItem.design.mainStyleNo.like(name + "%")
							.or(qCostingDtItem.orderDt.orderMt.refNo.like(name+"%")).or(qCostingDtItem.itemNo.like(name+"%")));
		
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if(sort.equalsIgnoreCase("style")){
			sort = "design";
		}

		Page<CostingDtItem> costDtItemList = (Page<CostingDtItem>) costingDtItemRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return costDtItemList;
	}

	@Override
	public String costDtItemListing(Integer limit, Integer offset, String sort, String order, String search,
			String pInvNo) {
		int srno = offset;

		StringBuilder sb = new StringBuilder();
		Page<CostingDtItem> costDts = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
							  
		CostingMt costingMt = costingMtService.findByInvNoAndDeactive(pInvNo.trim(), false);
		costDts = searchAll(limit, offset, sort, order, search, costingMt.getId());

		sb.append("{\"total\":").append(costDts.getTotalElements()).append(",\"rows\": [");
		
		for (CostingDtItem costingDtItem : costDts) {

			srno += 1;
			
			
			Double addedWtPerc =Math.round((((costingDtItem.getNetWt()-costingDtItem.getActNetWt())/costingDtItem.getActNetWt())*100)*100.0)/100.0;
			
			Double addInNetWtPerc =Math.round((costingDtItem.getActNetWt()+((costingDtItem.getActNetWt()*costingMt.getParty().getMetalWtAddPerc())/100))*1000.0)/1000.0;
			

			sb.append("{\"id\":\"").append(costingDtItem.getId()).append("\",\"srNo\":\"").append(srno)
					.append("\",\"setNo\":\"")
					.append((costingDtItem.getSetNo() != null ? costingDtItem.getSetNo() : ""))
					.append("\",\"itemNo\":\"")
					.append((costingDtItem.getItemNo() != null ? costingDtItem.getItemNo() : ""))
					.append("\",\"addWtPerc\":\"")
					.append(addedWtPerc > 0 ? addedWtPerc : "")
					.append("\",\"addInNetWt\":\"")
					.append(addInNetWtPerc > 0 ? addInNetWtPerc : "")
					.append("\",\"prodSize\":\"")
					.append((costingDtItem.getOrderDt().getProductSize() != null ? costingDtItem.getOrderDt().getProductSize().getName() : ""))
					.append("\",\"party\":\"")
					.append((costingDtItem.getParty() != null ? costingDtItem.getParty().getPartyCode() : ""))
					.append("\",\"ordNo\":\"")
					.append((costingDtItem.getOrderDt().getOrderMt().getInvNo() != null
							? costingDtItem.getOrderDt().getOrderMt().getInvNo()
							: ""))
					.append("\",\"reqCts\":\"")
					.append((costingDtItem.getOrderDt().getReqCarat() != null ? costingDtItem.getOrderDt().getReqCarat()
							: ""))
					.append("\",\"ordRefNo\":\"")
					.append((costingDtItem.getOrderDt().getOrderMt().getRefNo() != null
							? costingDtItem.getOrderDt().getOrderMt().getRefNo()
							: ""))
					.append("\",\"style\":\"")
					.append((costingDtItem.getDesign() != null ? costingDtItem.getDesign().getMainStyleNo() : ""))
					.append("\",\"purity\":\"")
					.append((costingDtItem.getPurity() != null ? costingDtItem.getPurity().getName() : ""))
					.append("\",\"lossPercDt\":\"")
					.append((costingDtItem.getLossPercDt() != null ? costingDtItem.getLossPercDt() : ""))
					.append("\",\"color\":\"")
					.append((costingDtItem.getColor() != null ? costingDtItem.getColor().getName() : ""))
					.append("\",\"pcs\":\"").append((costingDtItem.getPcs() != null ? costingDtItem.getPcs() : ""))
					.append("\",\"grossWt\":\"")
					.append((costingDtItem.getGrossWt() != null ? costingDtItem.getGrossWt() : ""))
					.append("\",\"actGrossWt\":\"")
					.append((costingDtItem.getActGrossWt() != null ? costingDtItem.getActGrossWt() : ""))
					.append("\",\"netWt\":\"")
					.append((costingDtItem.getNetWt() != null ? costingDtItem.getNetWt() : ""))
					.append("\",\"actNetWt\":\"")
					.append((costingDtItem.getActNetWt() != null ? costingDtItem.getActNetWt() : ""))
					.append("\",\"metalRate\":\"")
					.append((costingDtItem.getMetalRate() != null ? costingDtItem.getMetalRate() : ""))
					.append("\",\"metalValue\":\"")
					.append((costingDtItem.getMetalValue() != null ? costingDtItem.getMetalValue() : ""))
					.append("\",\"stoneValue\":\"")
					.append((costingDtItem.getStoneValue() != null ? costingDtItem.getStoneValue() : ""))
					.append("\",\"compValue\":\"")
					.append((costingDtItem.getCompValue() != null ? costingDtItem.getCompValue() : ""))
					.append("\",\"labourValue\":\"")
					.append((costingDtItem.getLabValue() != null ? costingDtItem.getLabValue() : ""))
					.append("\",\"setValue\":\"")
					.append((costingDtItem.getSetValue() != null ? costingDtItem.getSetValue() : ""))
					.append("\",\"handlingCost\":\"")
					.append((costingDtItem.getHdlgValue() != null ? costingDtItem.getHdlgValue() : ""))
					.append("\",\"fob\":\"").append((costingDtItem.getFob() != null ? costingDtItem.getFob() : ""))
					.append("\",\"other\":\"")
					.append((costingDtItem.getOther() != null ? costingDtItem.getOther() : ""))
					.append("\",\"dispPercentDt\":\"")
					.append((costingDtItem.getDispPercentDt() != null ? costingDtItem.getDispPercentDt() : ""))
					.append("\",\"perPcDiscAmount\":\"")
					.append((costingDtItem.getPerPcDiscAmount() != null ? costingDtItem.getPerPcDiscAmount() : ""))
					.append("\",\"discAmount\":\"")
					.append((costingDtItem.getDiscAmount() != null ? costingDtItem.getDiscAmount() : ""))
					.append("\",\"finalPrice\":\"")
					.append((costingDtItem.getFinalPrice() != null ? costingDtItem.getFinalPrice() : ""))
					.append("\",\"perPcFinalPrice\":\"")
					.append((costingDtItem.getPerPcFinalPrice() != null ? costingDtItem.getPerPcFinalPrice() : ""))
					.append("\",\"image\":\"")
					.append(costingDtItem.getDesign().getDefaultImage() != null
							? costingDtItem.getDesign().getDefaultImage()
							: "blank.png")
					.append("\",\"rLock\":\"").append((costingDtItem.getrLock())) // 1 = lock & 0 = unlock
					.append("\",\"actionLock\":\"").append("<a href='javascript:doCostDtLockUnLockItem(")
					.append(costingDtItem.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
					.append("\",\"action1\":\"").append("<a href='javascript:editcostingDtItem(")
					.append(costingDtItem.getId())
					.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"").append("<a  href='javascript:deletecostingDtItem(event,")
					.append(costingDtItem.getId()).append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(costingDtItem.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>").append("\"},");

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	@Override
	public String costDtItemReportListing(Integer limit, Integer offset, String sort, String order, String search,
			String mtIds, Integer setNoId) {
		int srno = offset;

		StringBuilder sb = new StringBuilder();
		Page<CostingDtItem> costDts = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		costDts = costingItemListBySetNo(limit, offset, sort, order, search, mtIds, setNoId);

		sb.append("{\"total\":").append(costDts.getTotalElements()).append(",\"rows\": [");

		for (CostingDtItem costingDtItem : costDts) {

			srno += 1;

			sb.append("{\"id\":").append(costingDtItem.getId()).append(",\"srNo\":\"").append(srno)
					.append("\",\"invNo\":\"").append(costingDtItem.getCostingMt().getInvNo()).append("\",\"setNo\":\"")
					.append((costingDtItem.getSetNo() != null ? costingDtItem.getSetNo() : ""))
					.append("\",\"itemNo\":\"")
					.append((costingDtItem.getItemNo() != null ? costingDtItem.getItemNo() : ""))
					.append("\",\"party\":\"")
					.append((costingDtItem.getParty() != null ? costingDtItem.getParty().getPartyCode() : ""))
					.append("\",\"ordNo\":\"")
					.append((costingDtItem.getOrderDt().getOrderMt().getInvNo() != null
							? costingDtItem.getOrderDt().getOrderMt().getInvNo()
							: ""))
					.append("\",\"ordRefNo\":\"")
					.append((costingDtItem.getOrderDt().getOrderMt().getRefNo() != null
							? costingDtItem.getOrderDt().getOrderMt().getRefNo()
							: ""))
					.append("\",\"style\":\"")
					.append((costingDtItem.getDesign() != null ? costingDtItem.getDesign().getMainStyleNo() : ""))
					.append("\",\"purity\":\"")
					.append((costingDtItem.getPurity() != null ? costingDtItem.getPurity().getName() : ""))
					.append("\",\"lossPercDt\":\"")
					.append((costingDtItem.getLossPercDt() != null ? costingDtItem.getLossPercDt() : ""))
					.append("\",\"color\":\"")
					.append((costingDtItem.getColor() != null ? costingDtItem.getColor().getName() : ""))
					.append("\",\"pcs\":\"").append((costingDtItem.getPcs() != null ? costingDtItem.getPcs() : ""))
					.append("\",\"grossWt\":\"")
					.append((costingDtItem.getGrossWt() != null ? costingDtItem.getGrossWt() : ""))
					.append("\",\"netWt\":\"")
					.append((costingDtItem.getNetWt() != null ? costingDtItem.getNetWt() : ""))
					.append("\",\"metalRate\":\"")
					.append((costingDtItem.getMetalRate() != null ? costingDtItem.getMetalRate() : ""))
					.append("\",\"metalValue\":\"")
					.append((costingDtItem.getMetalValue() != null ? costingDtItem.getMetalValue() : ""))
					.append("\",\"stoneValue\":\"")
					.append((costingDtItem.getStoneValue() != null ? costingDtItem.getStoneValue() : ""))
					.append("\",\"compValue\":\"")
					.append((costingDtItem.getCompValue() != null ? costingDtItem.getCompValue() : ""))
					.append("\",\"labourValue\":\"")
					.append((costingDtItem.getLabValue() != null ? costingDtItem.getLabValue() : ""))
					.append("\",\"setValue\":\"")
					.append((costingDtItem.getSetValue() != null ? costingDtItem.getSetValue() : ""))
					.append("\",\"handlingCost\":\"")
					.append((costingDtItem.getHdlgValue() != null ? costingDtItem.getHdlgValue() : ""))
					.append("\",\"fob\":\"").append((costingDtItem.getFob() != null ? costingDtItem.getFob() : ""))
					.append("\",\"other\":\"")
					.append((costingDtItem.getOther() != null ? costingDtItem.getOther() : ""))
					.append("\",\"dispPercentDt\":\"")
					.append((costingDtItem.getDispPercentDt() != null ? costingDtItem.getDispPercentDt() : ""))
					.append("\",\"perPcDiscAmount\":\"")
					.append((costingDtItem.getPerPcDiscAmount() != null ? costingDtItem.getPerPcDiscAmount() : ""))
					.append("\",\"discAmount\":\"")
					.append((costingDtItem.getDiscAmount() != null ? costingDtItem.getDiscAmount() : ""))
					.append("\",\"finalPrice\":\"")
					.append((costingDtItem.getFinalPrice() != null ? costingDtItem.getFinalPrice() : ""))
					.append("\",\"perPcFinalPrice\":\"")
					.append((costingDtItem.getPerPcFinalPrice() != null ? costingDtItem.getPerPcFinalPrice() : ""))
					.append("\",\"image\":\"")
					.append(costingDtItem.getDesign().getDefaultImage() != null
							? costingDtItem.getDesign().getDefaultImage()
							: "blank.png")
					.append("\"},");

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	@Override
	public String updateDtNetWtAndMetalDetails(Principal principal, Integer dtId, Double netWt,Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		
		CostingDtItem costingDtItem = findOne(dtId);
		
		Double vnetDiff =Math.round((costingDtItem.getNetWt()-netWt)*1000.0)/1000.0;
		costingDtItem.setGrossWt(Math.round((costingDtItem.getGrossWt()-vnetDiff)*1000.0)/1000.0);
		costingDtItem.setNetWt(netWt);
		save(costingDtItem);
		
		CostMetalDtItem costMetalDtItem =costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(costingDtItem, false, true);
		costMetalDtItem.setMetalWeight(Math.round((costMetalDtItem.getMetalWeight()-vnetDiff)*1000.0)/1000.0);
		costMetalDtItemService.save(costMetalDtItem);
		
		updateItemFob(costingDtItem,netWtWithCompFlg);
		return "1";
	}

}

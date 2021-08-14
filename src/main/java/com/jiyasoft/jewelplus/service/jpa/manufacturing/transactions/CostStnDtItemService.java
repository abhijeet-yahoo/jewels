package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DiaWtTagRangeMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QQuotStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostStnDtItemRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDiaWtTagRangeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class CostStnDtItemService implements ICostStnDtItemService{
	
	@Autowired
	private ICostStnDtItemRepository costStnDtItemRepository;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private ICostMetalDtItemService costMetalDtItemService;
	
	@Autowired
	private ICostCompDtItemService costCompDtItemService;
	
	@Autowired
	private IDiaWtTagRangeService diaWtTagRangeService;
	
	
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CostStnDtItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CostStnDtItem costStnDtItem) {
		// TODO Auto-generated method stub
		costStnDtItemRepository.save(costStnDtItem);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		CostStnDtItem costStnDtItem =  costStnDtItemRepository.findOne(id);
		costStnDtItem.setDeactive(true);
		costStnDtItem.setDeactiveDt(new Date());
		costStnDtItemRepository.save(costStnDtItem);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostStnDtItem findOne(int id) {
		// TODO Auto-generated method stub
		return costStnDtItemRepository.findOne(id);
	}

	@Override
	public List<CostStnDtItem> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return costStnDtItemRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public List<CostStnDtItem> findByCostingDtItemAndDeactive(
			CostingDtItem costingDtItem, Boolean deactive) {
		// TODO Auto-generated method stub
		return costStnDtItemRepository.findByCostingDtItemAndDeactive(costingDtItem, deactive);
	}


	@Override
	public List<CostStnDtItem> findByItemNoAndCostingDtItemAndDeactive(
			String itemNo, CostingDtItem costingDtItem, Boolean deactive) {
		// TODO Auto-generated method stub
		return costStnDtItemRepository.findByItemNoAndCostingDtItemAndDeactive(itemNo, costingDtItem, deactive);
	}

	@Override
	public void lockUnlockStnDt(Integer CostMtId, Boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItemNo(Integer costDtId, String itemNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CostStnDtItem findByCostingDtItemAndPartNmAndQualityAndSettingAndSettingTypeAndShapeAndSieveAndSizeAndSizeGroupAndStoneType(
			CostingDtItem costingDtItem, LookUpMast lookUpMast,	Quality quality, Setting setting, SettingType settingType,Shape shape, String sieve, String size, SizeGroup sizeGroup,
			StoneType stoneType) {
		// TODO Auto-generated method stub
		return costStnDtItemRepository.findByCostingDtItemAndPartNmAndQualityAndSettingAndSettingTypeAndShapeAndSieveAndSizeAndSizeGroupAndStoneTypeAndDeactive(costingDtItem, lookUpMast, quality, setting, settingType, shape, sieve, size, sizeGroup, stoneType,false);
	}

	@Override
	public String updateManualTagWt(Integer costDtItemId, Double tagWt,Principal principal,Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		
		CostingDtItem costingDtItem =costingDtItemService.findOne(costDtItemId);
		
		List<CostStnDtItem>costStnDtItems=findByCostingDtItemAndDeactiveOrderByStoneDesc(costingDtItem, false);
		
		Double caratwt=0.0;
		for(CostStnDtItem costStnDtItem :costStnDtItems){
			
			caratwt +=costStnDtItem.getTagWt();
			
		}
		Double wtDiff=Math.round((tagWt-caratwt)*1000.0)/1000.0;
		
		Double wtMetalDiff=Math.round(((wtDiff*costingDtItem.getPcs())/5)*1000.0)/1000.0;
		
		for(CostStnDtItem costStnDtItem :costStnDtItems){
			
			costStnDtItem.setTagWt(Math.round((costStnDtItem.getTagWt()+wtDiff)*1000.0)/1000.0);
			costStnDtItem.setCarat(Math.round((costStnDtItem.getTagWt()*costingDtItem.getPcs())*1000.0)/1000.0);
			save(costStnDtItem);
			
			
			costingDtItem.setNetWt(Math.round((costingDtItem.getNetWt()-wtMetalDiff)*1000.0)/1000.0);
			costingDtItemService.save(costingDtItem);
			
			CostMetalDtItem costMetalDtItem =costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(costingDtItem, false, true);
			
			costMetalDtItem.setMetalWeight(Math.round((costMetalDtItem.getMetalWeight()-wtMetalDiff)*1000.0)/1000.0);
			costMetalDtItemService.save(costMetalDtItem);
			
			break;
			
		}
		
		
		costingDtItemService.applyItemLabRate(costingDtItem, principal);
		
		costingDtItemService.updateItemFob(costingDtItem,netWtWithCompFlg);
		
		retVal="1";
		
		
		return retVal;
	}
	
	@Override
	public List<CostStnDtItem> findByCostingDtItemAndDeactiveOrderByStoneDesc(
			CostingDtItem costingDtItem, Boolean deactive) {
		// TODO Auto-generated method stub
		return costStnDtItemRepository.findByCostingDtItemAndDeactiveOrderByStoneDesc(costingDtItem, deactive);
	}

	@Override
	public String updateTagWtReqCts(CostingMt costingMt,Principal principal,Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		
		List<CostingDtItem>costingDtItems=costingDtItemService.findByCostingMtAndDeactive(costingMt, false);
		
		
		for(CostingDtItem costingDtItem:costingDtItems){
			
			List<CostStnDtItem>costStnDtItems=findByCostingDtItemAndDeactiveOrderByStnRateAsc(costingDtItem, false);
			
			Double caratwt=0.0;
			for(CostStnDtItem costStnDtItem :costStnDtItems){
				
				caratwt +=costStnDtItem.getPerStoneWt();
				
			}
			
			Double wtDiff=Math.round(((costingDtItem.getOrderDt().getReqCarat() != null ? costingDtItem.getOrderDt().getReqCarat() : 0.0)-caratwt)*1000.0)/1000.0;
			
			Double wtMetalDiff=Math.round(((wtDiff*costingDtItem.getPcs())/5)*1000.0)/1000.0;
			
			
			
			
			for(CostStnDtItem costStnDtItem :costStnDtItems){
				
				costStnDtItem.setTagWt(Math.round((costStnDtItem.getPerStoneWt()+wtDiff)*1000.0)/1000.0);
				costStnDtItem.setCarat(Math.round((costStnDtItem.getTagWt()*costingDtItem.getPcs())*1000.0)/1000.0);
				save(costStnDtItem);
				
				
				costingDtItem.setNetWt(Math.round((costingDtItem.getNetWt()-wtMetalDiff)*1000.0)/1000.0);
				costingDtItemService.save(costingDtItem);
				
				CostMetalDtItem costMetalDtItem =costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(costingDtItem, false, true);
				
				costMetalDtItem.setMetalWeight(Math.round((costMetalDtItem.getMetalWeight()-wtMetalDiff)*1000.0)/1000.0);
				costMetalDtItemService.save(costMetalDtItem);
				
				break;
				
			}
			
			costingDtItemService.updateItemFob(costingDtItem,netWtWithCompFlg);
			
		}
		retVal="1";
		
		
		
		
		return retVal;
	}

	@Override
	public List<CostStnDtItem> findByCostingDtItemAndDeactiveOrderByStnRateAsc(
			CostingDtItem costingDtItem, Boolean deactive) {
		// TODO Auto-generated method stub
		return costStnDtItemRepository.findByCostingDtItemAndDeactiveOrderByStnRateAsc(costingDtItem, deactive);
	}

	@Override
	public String autoApplyTagWt(CostingMt costingMt, Principal principal,Boolean netWtWithCompFlg) {
		
		String retVal="-1";
		
		List<CostingDtItem>costingDtItems=costingDtItemService.findByCostingMtAndDeactive(costingMt, false);
		
		
		for(CostingDtItem costingDtItem:costingDtItems){
			
			List<CostStnDtItem>costStnDtItems=findByCostingDtItemAndDeactiveOrderByStnRateAsc(costingDtItem, false);
			
			Double caratwt=0.0;
			for(CostStnDtItem costStnDtItem :costStnDtItems){
				
				caratwt +=costStnDtItem.getPerStoneWt();
				
			}
			
			caratwt=Math.round(caratwt*1000.0)/1000.0;
			
			List<DiaWtTagRangeMaster>diaWtTagRangeMasters=diaWtTagRangeService.getDiaWt(caratwt);
			
			DiaWtTagRangeMaster diaWtTagRangeMaster= new DiaWtTagRangeMaster();
			if(diaWtTagRangeMasters.size()>0){
				
				diaWtTagRangeMaster =diaWtTagRangeMasters.get(0);
				
			}
			
			
			
			
			
			
			
			Double wtMetalDiff=Math.round(((diaWtTagRangeMaster.getAddedWt()*costingDtItem.getPcs())/5)*1000.0)/1000.0;
			
			for(CostStnDtItem costStnDtItem :costStnDtItems){
				
				costStnDtItem.setTagWt(Math.round((costStnDtItem.getPerStoneWt()+diaWtTagRangeMaster.getAddedWt())*1000.0)/1000.0);
				costStnDtItem.setCarat(Math.round((costStnDtItem.getTagWt()*costingDtItem.getPcs())*1000.0)/1000.0);
				save(costStnDtItem);
				
				
				costingDtItem.setNetWt(Math.round((costingDtItem.getNetWt()-wtMetalDiff)*1000.0)/1000.0);
				costingDtItemService.save(costingDtItem);
				
				CostMetalDtItem costMetalDtItem =costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(costingDtItem, false, true);
				
				costMetalDtItem.setMetalWeight(Math.round((costMetalDtItem.getMetalWeight()-wtMetalDiff)*1000.0)/1000.0);
				costMetalDtItemService.save(costMetalDtItem);
				
				break;
				
			}
			
			costingDtItemService.updateItemFob(costingDtItem,netWtWithCompFlg);
			
		}
		retVal="1";
		
		
		
		
		return retVal;
	}

	@Override
	public String apply005TagWt(CostingMt costingMt, Principal principal,Boolean netWtWithCompFlg) {
			
		String retVal="-1";
		
	
		
		
		List<CostingDtItem>costingDtItems=costingDtItemService.findByCostingMtAndDeactive(costingMt, false);
		
		
		for(CostingDtItem costingDtItem:costingDtItems){
			
			QQuotStnDt qQuotStnDt = QQuotStnDt.quotStnDt;
			JPAQuery queryStn =  new JPAQuery(entityManager);
			
						
			
			@SuppressWarnings("unchecked")
			TypedQuery<QuotDt> query2 = (TypedQuery<QuotDt>) entityManager
					.createNativeQuery("select a.* from quotdt a,quotmt b where  a.mtid = b.mtid and a.deactive =0 and "
							+ "b.deactive =0  and b.refno= '"+costingDtItem.getOrderDt().getOrderMt().getRefNo()
							+ "' and a.barcode='"+costingDtItem.getItemNo()+"'", QuotDt.class);
			
			
			  List<QuotDt> results3 = query2.getResultList();
			  
			  if(results3.size()>0) {
				  
				  QuotDt quotDt = results3.get(0);
				  
				  if(quotDt !=null){
					  
					  
					  
					  
					  List<Tuple> quotStnList =  queryStn.from(qQuotStnDt).
								where(qQuotStnDt.deactive.eq(false).and(qQuotStnDt.quotDt.id.eq(quotDt.getId())))
								.groupBy(qQuotStnDt.partNm,qQuotStnDt.stoneType,qQuotStnDt.shape,qQuotStnDt.quality,qQuotStnDt.size,qQuotStnDt.sieve,
										qQuotStnDt.sizeGroup,qQuotStnDt.setting,qQuotStnDt.settingType)
								.list(qQuotStnDt.partNm,qQuotStnDt.stoneType,qQuotStnDt.shape,qQuotStnDt.quality,qQuotStnDt.size,qQuotStnDt.sieve,qQuotStnDt.sizeGroup,
										qQuotStnDt.stone.sum(),qQuotStnDt.carat.sum(),qQuotStnDt.setting,
										qQuotStnDt.settingType,qQuotStnDt.stnRate,qQuotStnDt.setRate);
					  
					  
					//  List<QuotStnDt>quotStnDts =quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
					  
					  List<CostStnDtItem>costStnDtItems=findByCostingDtItemAndDeactiveOrderByStnRateAsc(costingDtItem, false);
					  
					  Double caratwt=0.0;
						for(CostStnDtItem costStnDtItem :costStnDtItems){
							
							for(Tuple tupleStn :quotStnList){
								
								if(tupleStn.get(qQuotStnDt.quality).equals(costStnDtItem.getQuality())
										&& tupleStn.get(qQuotStnDt.size).equals(costStnDtItem.getSize()) 
										&& tupleStn.get(qQuotStnDt.settingType).equals(costStnDtItem.getSettingType())
										&& tupleStn.get(qQuotStnDt.setting).equals(costStnDtItem.getSetting())){
									
									costStnDtItem.setTagWt(Math.round(tupleStn.get(qQuotStnDt.carat.sum())*1000.0)/1000.0);
								/*
								 * costStnDtItem.setPerStonePcs(tupleStn.get(qQuotStnDt.stone.sum()));
								 * costStnDtItem.setPerStoneWt(Math.round(tupleStn.get(qQuotStnDt.carat.sum())*
								 * 1000.0)/1000.0);
								 */
									costStnDtItem.setCarat(Math.round((costStnDtItem.getTagWt()*costingDtItem.getPcs())*1000.0)/1000.0);
									costStnDtItem.setStone((int)(tupleStn.get(qQuotStnDt.stone.sum())*costingDtItem.getPcs()));
									costStnDtItem.setStnRate(tupleStn.get(qQuotStnDt.stnRate));
									costStnDtItem.setSetRate(tupleStn.get(qQuotStnDt.setRate));
									
									
									
									
								}
								
								
							}
							
							caratwt +=costStnDtItem.getCarat();
							save(costStnDtItem);
						}
						
						
						
						
						costingDtItem.setNetWt(Math.round((costingDtItem.getGrossWt()-(caratwt/5))*1000.0)/1000.0);
						
						
						costingDtItemService.save(costingDtItem);
						
						
						List<CostMetalDtItem>costMetalDtItems=costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
						
						Double vMetalWt=0.0;
						for(CostMetalDtItem costMetalDtItem :costMetalDtItems){
							vMetalWt +=costMetalDtItem.getMetalWeight();
						}
						
						
						List<CostCompDtItem>costCompDtItems=costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
						for(CostCompDtItem costCompDtItem :costCompDtItems){
							vMetalWt +=costCompDtItem.getMetalWt();
						}
						
						
											
						Double vMetalWtDiff= Math.round((costingDtItem.getNetWt()-vMetalWt)*1000.0)/1000.0;
						
						CostMetalDtItem costMetalDtItem =costMetalDtItemService.findByCostingDtItemAndDeactiveAndMainMetal(costingDtItem, false, true);

						
						costMetalDtItem.setMetalWeight(Math.round((costMetalDtItem.getMetalWeight()+vMetalWtDiff)*1000.0)/1000.0);
						costMetalDtItemService.save(costMetalDtItem);
						
						
						costingDtItemService.updateItemFob(costingDtItem,netWtWithCompFlg);
					  
				  }
				  
				  
				  
			  }
			  
			
			
			
			
			
			
			
			
			
			
			
			
		
			
		
			
		}
		retVal="1";
		
		
		
		
		return retVal;
		
		
	}

}

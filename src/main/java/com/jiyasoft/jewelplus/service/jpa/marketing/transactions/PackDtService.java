package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientKtConvMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalRate;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QPackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QPackMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IPackDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientKtConvService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterFLService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalRateService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class PackDtService implements IPackDtService {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPackDtRepository packDtRepository;
	
	@Autowired
	private IPackMtService packMtService;
	
	@Autowired
	private IPackMetalDtService packMetalDtService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	
	@Autowired
	private IPackStnDtService packStnDtService;
	
	@Autowired
	private IPackCompDtService packCompDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IPackLabDtService packLabDtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IPackMetalRateService packMetalRateService;
	
	@Autowired
	private ISettingChargeService settingChargeService;
	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;
	
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private IHandlingMasterFLService handlingMasterFLService;

	
	@Autowired
	private IFindingRateMastService findingRateMastService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private IClientKtConvService clientKtConvService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	

	
	@Override
	public void save(PackDt packDt) {
		// TODO Auto-generated method stub
		packDtRepository.save(packDt);
	}
 
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		packDtRepository.delete(id);
		
	}

	@Override
	public PackDt findOne(int id) {
		// TODO Auto-generated method stub
		return packDtRepository.findOne(id);
	}

	@Override
	public String addBarcodeItem(Integer mtId,String barcode, Principal principal,Integer partyId,Integer locationId) {
		
	//	Party party = partyService.findOne(partyId);
		Department department = departmentService.findOne(locationId);
		
		PackMt packMt =packMtService.findOne(mtId);
		
	//	packMt.setParty(party);
	//	packMt.setLocation(department);
	//	packMtService.save(packMt);
		
		StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactiveAndLocation(barcode, true, false,department);
		if(stockMt != null && stockMt.getCurrStk().equals(true)) {
			
			
			PackDt packDt2 = findByPackMtAndBarcode(packMt,barcode);
			if(packDt2 != null) {
				return "Duplicate Barcode";
			}
			
			
			PackDt packDt =new PackDt();
			packDt.setBarcode(stockMt.getBarcode());
			packDt.setCreatedBy(principal.getName());
			packDt.setCreatedDate(new Date());
			packDt.setDesign(stockMt.getDesign());
			packDt.setGrossWt(stockMt.getGrossWt());
			packDt.setNetWt(stockMt.getNetWt());
			packDt.setPackMt(packMt);
			packDt.setPcs(stockMt.getQty());
//			packDt.setMetalValue(stockMt.getMetalValue());
//			packDt.setFinalPrice((double) Math.round(stockMt.getFactoryCost()));
//			packDt.setLabValue(stockMt.getLabourValue());
//			packDt.setHallMarking(stockMt.getHallMarking());
//			packDt.setLazerMarking(stockMt.getLazerMarking());
//			packDt.setEngraving(stockMt.getEngraving());
//			packDt.setGrading(stockMt.getGrading());
//			packDt.setStoneValue(stockMt.getStoneValue());
			save(packDt);
			
			List<StockMetalDt>stockMetalDts =stockMetalDtService.findByStockMtAndDeactive(stockMt, false);
			for(StockMetalDt stockMetalDt :stockMetalDts) {
				PackMetalDt packMetalDt = new PackMetalDt();
				packMetalDt.setColor(stockMetalDt.getColor());
				packMetalDt.setCreateDate(new Date());
				packMetalDt.setCreatedBy(principal.getName());
				packMetalDt.setMainMetal(stockMetalDt.getMainMetal());
				packMetalDt.setMetalPcs(stockMetalDt.getMetalPcs());
				packMetalDt.setMetalWeight(stockMetalDt.getMetalWt());
				packMetalDt.setPackDt(packDt);
				packMetalDt.setPackMt(packMt);
				packMetalDt.setPartNm(stockMetalDt.getPartNm());
				packMetalDt.setPurity(stockMetalDt.getPurity());
				packMetalDt.setPurityConv(stockMetalDt.getPurityConv());
				packMetalDtService.save(packMetalDt);
				
			}
			
			List<StockStnDt>stockStnDts=stockStnDtService.findByStockMtAndDeactive(stockMt, false);
			
			
			for(StockStnDt stockStnDt :stockStnDts) {
				PackStnDt packStnDt =new PackStnDt();
				packStnDt.setCarat(stockStnDt.getCarat());
				packStnDt.setCreatedBy(principal.getName());
				packStnDt.setCreatedDate(new Date());
				packStnDt.setPackDt(packDt);
				packStnDt.setPackMt(packMt);
				packStnDt.setPartNm(stockStnDt.getPartNm());
				packStnDt.setQuality(stockStnDt.getQuality());
				packStnDt.setSetting(stockStnDt.getSetting());
				packStnDt.setSettingType(stockStnDt.getSettingType());
				packStnDt.setShape(stockStnDt.getShape());
				packStnDt.setSieve(stockStnDt.getSieve());
				packStnDt.setSize(stockStnDt.getSize());
				packStnDt.setSizeGroup(stockStnDt.getSizeGroup());
				packStnDt.setStone(stockStnDt.getStone());
				packStnDt.setStoneType(stockStnDt.getStoneType());
				packStnDt.setSubShape(stockStnDt.getSubShape());
				packStnDt.setCenterStone(stockStnDt.getCenterStone());
		//		packStnDt.setStoneValue(stockStnDt.getDiamValue());
				
				
				
				packStnDtService.save(packStnDt);
				
			}
			
			
			
			List<StockCompDt>stockCompDts =stockCompDtService.findByStockMtAndDeactive(stockMt, false);
			for(StockCompDt stockCompDt :stockCompDts) {
				PackCompDt packCompDt = new PackCompDt();
				packCompDt.setColor(stockCompDt.getColor());
				packCompDt.setComponent(stockCompDt.getComponent());
				packCompDt.setCompQty(stockCompDt.getCompQty());
				packCompDt.setCreatedBy(principal.getName());
				packCompDt.setCreatedDate(new Date());
				packCompDt.setPackDt(packDt);
				packCompDt.setPackMt(packMt);
				packCompDt.setPurity(stockCompDt.getPurity());
				packCompDt.setPurityConv(stockCompDt.getPurityConv());
				packCompDt.setCompWt(stockCompDt.getCompWt());
				
				packCompDtService.save(packCompDt);
				
				
				
			}
			stockMt.setCurrStk(false);
			stockMtService.save(stockMt);
			
			StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(barcode, true);
			StockTran stockTran2 = new StockTran();
			stockTran2.setBarcode(packDt.getBarcode());
			stockTran2.setCreatedBy(principal.getName());
			stockTran2.setCreatedDate(new Date());
			stockTran2.setCurrStatus("PackingList");
			stockTran2.setCurrStk(true);
			stockTran2.setLocation(stockTran.getLocation());
			stockTran2.setRefStkTranId(stockTran.getId());
			stockTran2.setRefTranId(packDt.getId());
			stockTran2.setTranDate(packMt.getInvDate());
			stockTran2.setParty(packMt.getParty());
			stockTran2.setTranType("PACKLIST");
			stockTran2.setStockMt(stockMt);
			stockTranService.save(stockTran2);
			
			stockTran.setCurrStk(false);
			stockTran.setIssueDate(packMt.getInvDate());
			stockTranService.save(stockTran);
			
			return "1";
			
			
		}else {
			
			return "Item Not Found In Stock";
		}
		
	}

	@Override
	public List<PackDt> findByPackMt(PackMt packMt) {
		// TODO Auto-generated method stub
		return packDtRepository.findByPackMt(packMt);
	}

	@Override
	public String deletePackDt(Integer dtId) {
		
		PackDt packDt = findOne(dtId);
		if(packDt.getAdjQty()>0) {  
			
			return "Can not Delete, Record present in consignment issue or sales invoice";
			
		}else {
			
			List<PackStnDt>packStnDts=packStnDtService.findByPackDt(packDt);
			for(PackStnDt packStnDt :packStnDts) {
				packStnDtService.delete(packStnDt.getId());
				
			}
			
			List<PackMetalDt>packMetalDts=packMetalDtService.findByPackDt(packDt);
			for(PackMetalDt packMetalDt :packMetalDts) {
				packMetalDtService.delete(packMetalDt.getId());
				
			}
			
			List<PackCompDt>packCompDts=packCompDtService.findByPackDt(packDt);
			for(PackCompDt packCompDt :packCompDts) {
				packCompDtService.delete(packCompDt.getId());
				
			}
			
			
			List<PackLabDt>packLabDts=packLabDtService.findByPackDt(packDt);
			for(PackLabDt packLabDt :packLabDts) {
				
				packLabDtService.delete(packLabDt.getId());
				
			}
			
		
			delete(packDt.getId());
			
			StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("PackList", packDt.getId(), true);
			if(stockTran !=null) {
				
				StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
				stockTran2.setCurrStk(true);
				stockTranService.save(stockTran2);
				
				stockTranService.delete(stockTran.getId());
				
			}
			
			
		//	StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(packDt.getBarcode(), false, false);
			 
			StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());	
			stockMt.setCurrStk(true);
			stockMtService.save(stockMt);
			
			
			
			
		}
		
		
		
		return "1";
	}

	@Override
	public String applyRate(PackDt packDt, Principal principal) {
	
		String retVal="";
		
		if(packDt.getrLock().equals(false)){

		applyMetal(packDt);
		List<PackStnDt> packStnDts = packStnDtService.findByPackDt(packDt);
					
		
		applyStoneRate(packStnDts);
		
		List<PackCompDt> packCompDts = packCompDtService.findByPackDt(packDt);
		applyCompRate(packCompDts);
		
		applyLabRate(packDt, principal);
		
		packLabDtService.applyGrading(packDt, principal);
		
		updateFob(packDt);
		retVal="1";
		
		}
		
		return retVal;
	}

	@Override
	public String applyMetal(PackDt packDt) {
		
		PackMt packMt =packDt.getPackMt();
		
		
		List<PackMetalDt> packMetalDts = packMetalDtService.findByPackDt(packDt);
				
			for(PackMetalDt packMetalDt:packMetalDts){
				
				if(packMetalDt.getrLock().equals(false)){
					if (packMetalDt.getPurity() != null) {
						Purity purity = purityService.findOne(packMetalDt.getPurity().getId());
																	
						PackMetalRate packMetalRate = packMetalRateService.findByPackMtAndMetal(packMt, purity.getMetal());
						
							if(packMetalRate!=null){
								
								packMetalDt.setMetalRate(packMetalRate.getRate());
								packMetalDt.setLossPerc(packMetalRate.getLossPerc());
								packMetalDtService.save(packMetalDt);
								
							}
						
						
						

					}

				}

			}

		
		
		
		return "";
	}

	@Override
	public String applyStoneRate(List<PackStnDt> packStnDts) {
		String retVal="error";

		for(PackStnDt packStnDt: packStnDts){
		
		if(packStnDt.getrLock().equals(true)){
			continue;
		}
		
		
		
		packStnDt=applySettingRate(packStnDt);
		
		packStnDt=applyPackStoneRate(packStnDt);
		
		packStnDt=applyHandlingRate(packStnDt);
		
		packStnDtService.save(packStnDt);
		
	}
		
	retVal="1";
	
	
	return retVal;
	}

	@Override
	public String applyCompRate(List<PackCompDt> packCompDts) {
	
		String retVal="error";
		
		for(PackCompDt packCompDt:packCompDts){
			
			if(packCompDt.getrLock().equals(true)){
				continue;
			}

			packCompDt=applyPackCompRate(packCompDt);
						
			packCompDtService.save(packCompDt);
			
				
				
				

			
			
		}
		
			retVal="1";
		return retVal;
	}

	@Override
	public String applyLabRate(PackDt packDt, Principal principal) {
		
		QPackMetalDt qPackMetalDt = QPackMetalDt.packMetalDt;
		JPAQuery query=new JPAQuery(entityManager);
		
		
		
		List<Tuple> packMetalList=null;
		
		packMetalList = query.from(qPackMetalDt).
				where(qPackMetalDt.packDt.id.eq(packDt.getId()))
				.groupBy(qPackMetalDt.purity.metal).list(qPackMetalDt.purity,qPackMetalDt.purity.metal.name,qPackMetalDt.metalWeight.sum(),qPackMetalDt.metalPcs.sum());
		
		
		
		for(Tuple tuple : packMetalList){
		
			QPackCompDt qPackCompDt = QPackCompDt.packCompDt;
			JPAQuery compQuery=new JPAQuery(entityManager);
			
					
			Metal metal =metalService.findByName(tuple.get(qPackMetalDt.purity.metal.name));
			
			
			List<Tuple>packCompList = compQuery.from(qPackCompDt).where(
					qPackCompDt.packDt.id.eq(packDt.getId()).and(qPackCompDt.purity.metal.eq(metal)))
					.groupBy(qPackCompDt.purity.metal).list(qPackCompDt.compWt.sum(),qPackCompDt.compQty.sum());
			
			
			Double vCompWt=0.0;
			for(Tuple tupleComp : packCompList){
					vCompWt= Math.round((tupleComp.get(qPackCompDt.compWt.sum())/packDt.getPcs())*1000.0)/1000.0;
			}
			
			
			Double vMetalWt= Math.round((tuple.get(qPackMetalDt.metalWeight.sum())/packDt.getPcs())*1000.0)/1000.0;
			
			Double vNetWt = Math.round((vMetalWt+vCompWt)*1000.0)/1000.0;
			
			
			
			List<LabourCharge> labourCharges=null;
			
			
			 labourCharges =labourChargeService.getPurityWiseRates(packDt.getPackMt().getParty(), packDt.getDesign().getCategory(),
					 vNetWt,false, metal,tuple.get(qPackMetalDt.purity));
			 
			 if(labourCharges.size()<=0){
				 
				 labourCharges =labourChargeService.getRates(packDt.getPackMt().getParty(), packDt.getDesign().getCategory(),
						 vNetWt,false, metal);
				 
			 }
			
			
			
			
			
			
			List<PackLabDt> packLabDts = packLabDtService.findByPackDtAndMetal(packDt, metal);
			
			Boolean isAvailable=false;
			for(LabourCharge labourCharge :labourCharges){
				
				if(labourCharge.getLabourType().getCode().equalsIgnoreCase("Grade")) {
					continue;
				}
				
				isAvailable=false;
				for(PackLabDt packLabDt : packLabDts){
					if(packLabDt.getLabourType().equals(labourCharge.getLabourType())){
						
						isAvailable=true;
						if(packLabDt.getrLock().equals(false)){
							packLabDt.setLabourRate(labourCharge.getRate());	
							
							packLabDt.setPerPcRate(false);
							packLabDt.setPerGramRate(false);
							packLabDt.setPercentage(false);
							packLabDt.setPerCaratRate(false);
							
							if(labourCharge.getPerPcsRate() == true){
								packLabDt.setPerPcRate(true);
							}else if(labourCharge.getPerGramRate() == true){
								packLabDt.setPerGramRate(true);
							}else if(labourCharge.getPercentage() == true){
								packLabDt.setPercentage(true);
							}
							else if(labourCharge.getPerCaratRate() == true){
								packLabDt.setPerCaratRate(true);
							}
							
						}
						
					}
										
				}
				if(!isAvailable){
					
					PackLabDt packLabDt = new PackLabDt();
					
					packLabDt.setPackMt(packDt.getPackMt());
					packLabDt.setPackDt(packDt);
					packLabDt.setLabourType(labourCharge.getLabourType());
					packLabDt.setLabourRate(labourCharge.getRate());
					packLabDt.setMetal(metal);
					
					if(labourCharge.getPerPcsRate() == true){
						packLabDt.setPerPcRate(true);
					}else if(labourCharge.getPerGramRate() == true){
						packLabDt.setPerGramRate(true);
					}else if(labourCharge.getPercentage() == true){
						packLabDt.setPercentage(true);
					}
					else if(labourCharge.getPerCaratRate() == true){
						packLabDt.setPerCaratRate(true);
					}
				
					packLabDt.setCreatedDate(new java.util.Date());
					packLabDt.setCreatedBy(principal.getName());
					
					packLabDtService.save(packLabDt);
					
					
				}
				
				
			}
			
			
			
			
			
		}
		
			
	
		return "1";
	}

	@Override
	public String updateFob(PackDt packDt) {
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
					Double totHallMarkValue=0.0;
					Double totLazerValue=0.0;
					Double totGradingValue=0.0;
					Double totEngravingValue=0.0;
					Double totCarat=0.0;
					
					
					
					
					
					
					Double tempVal = 0.0;
				//	Double vFob = 0.0;
					
					
					if(packDt.getrLock() == true){	
					}else{
						
					
				/*-------------------------------------------------Metal Fob--------------------------------*/		
						
						
						List<PackMetalDt> packMetalDts = packMetalDtService.findByPackDt(packDt);
						if(packMetalDts !=null){
							for(PackMetalDt packMetalDt: packMetalDts){
								if (packMetalDt.getPurity() != null && packMetalDt.getMetalRate()>0) {
									if(packMetalDt.getPackMt().getParty().getType().getFieldValue().equalsIgnoreCase("Local")) {
									//Purity purity = purityService.findOne(packMetalDt.getPurity().getId());
									ClientKtConvMast clientKtConvMast=clientKtConvService.findByPartyAndPurityAndDeactive(packDt.getPackMt().getParty(), packMetalDt.getPurity(), false);
						
									if(clientKtConvMast !=null) {
										tempCal =  packMetalDt.getMetalRate()*clientKtConvMast.getPurityConv();
										packMetalDt.setPerGramRate(Math.round((tempCal)*100.0)/100.0);
										packMetalDt.setMetalValue(Math.round((packMetalDt.getMetalWeight()*packMetalDt.getPerGramRate())*100.0)/100.0);
										
										totMetalValue +=packMetalDt.getMetalValue();
									    packMetalDtService.save(packMetalDt);
									}else {
										
										tempCal =  packMetalDt.getMetalRate()*packMetalDt.getPurity().getPurityConv();
										packMetalDt.setPerGramRate(Math.round((tempCal)*100.0)/100.0);
										packMetalDt.setMetalValue(Math.round((packMetalDt.getMetalWeight()*packMetalDt.getPerGramRate())*100.0)/100.0);
										
										totMetalValue +=packMetalDt.getMetalValue();
									    packMetalDtService.save(packMetalDt);
									}
									
									}else {
										
										Purity purity = purityService.findOne(packMetalDt.getPurity().getId());
										String metalNm=purity.getMetal().getName();
										if(metalNm.equalsIgnoreCase("Gold")){
											tempCal =  packMetalDt.getMetalRate()/packMetalDt.getPurity().getMetal().getSpecificGravity();
											tempCal2 = (tempCal/(packMetalDt.getPackMt().getIn999().equals(true)?24:23.88)) * (packMetalDt.getPurity().getvPurity() != null ? packMetalDt.getPurity().getvPurity() : 0.0);
											tempLoss=tempCal2*packMetalDt.getLossPerc()/100;
											packMetalDt.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
											packMetalDt.setMetalValue(Math.round((packMetalDt.getMetalWeight()*packMetalDt.getPerGramRate())*100.0)/100.0);
											
											totMetalValue +=packMetalDt.getMetalValue();
										    packMetalDtService.save(packMetalDt);
										
										}else if (metalNm.equalsIgnoreCase("Silver") || metalNm.equalsIgnoreCase("PLATINUM")) {
											tempCal =  packMetalDt.getMetalRate()/packMetalDt.getPurity().getMetal().getSpecificGravity();
											tempCal2 = (tempCal*(packMetalDt.getPurity().getPurityConv() != null ? packMetalDt.getPurity().getPurityConv() : 0.0));
												
											
											tempLoss=tempCal2*packMetalDt.getLossPerc()/100;
											packMetalDt.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
											packMetalDt.setMetalValue(Math.round((packMetalDt.getMetalWeight()*packMetalDt.getPerGramRate())*100.0)/100.0);
											
											totMetalValue +=packMetalDt.getMetalValue();
										    packMetalDtService.save(packMetalDt);
											
											
												
										}else if (metalNm.equalsIgnoreCase("Alloy")) {
											tempCal =  packMetalDt.getMetalRate()/1000;
											tempLoss=tempCal*packMetalDt.getLossPerc()/100;
											
											packMetalDt.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
											packMetalDt.setMetalValue(Math.round((packMetalDt.getMetalWeight()*packMetalDt.getPerGramRate())*100.0)/100.0);
											
											totMetalValue +=packMetalDt.getMetalValue();
										    packMetalDtService.save(packMetalDt);
															
											
										}
									}
								
								}
								
								
								
							}
							
						}
						
				/*---------------------------------------------------------------------------------------------------------*/			
						
				/*----------------------------------------------Stone Fob------------------------------------------------------------------*/			
						
						List<PackStnDt> packStnDts = packStnDtService.findByPackDt(packDt);
						for(PackStnDt packStnDt : packStnDts){			
								
								if(packStnDt.getPerPcsRateFlg().equals(false)){
									packStnDt.setStoneValue(Math.round((packStnDt.getCarat() * packStnDt.getStoneRate())*100.0)/100.0);
								}else{
									packStnDt.setStoneValue(Math.round((packStnDt.getStone() * packStnDt.getStoneRate())*100.0)/100.0);
								}
												
								packStnDt.setSetValue(Math.round((packStnDt.getSetRate()*packStnDt.getStone())*100.0)/100.0);
								
								packStnDt.setHandlingValue(Math.round((packStnDt.getCarat() * packStnDt.getHandlingRate())*100.0)/100.0);
				
											
								packStnDtService.save(packStnDt);
								totCarat +=packStnDt.getCarat();
								totStnValue  	 += packStnDt.getStoneValue();
								totSetValue  	 += packStnDt.getSetValue();
								totHdlgValue 	 += packStnDt.getHandlingValue();
													
								
							}

				/*---------------------------------------------------------------------------------------------------------*/			
						
				/*----------------------------------------------Component Fob------------------------------------------------------------------*/		
						
						
						List<PackCompDt> packCompDts = packCompDtService.findByPackDt(packDt);
						 
						for(PackCompDt packCompDt : packCompDts){
							if(packCompDt.getPerGramRate().equals(true)){
								packCompDt.setCompValue(Math.round((packCompDt.getCompWt()* packCompDt.getCompRate())*100.0)/100.0);
							}else{
								packCompDt.setCompValue(Math.round((packCompDt.getCompQty()* packCompDt.getCompRate())*100.0)/100.0);
							}
									
							packCompDtService.save(packCompDt);
							totCompValue += packCompDt.getCompValue();
							
							
							if (packCompDt.getPurity() != null) {
								Purity purity = purityService.findOne(packCompDt.getPurity().getId());
																			
								PackMetalRate packMetalRate = packMetalRateService.findByPackMtAndMetal(packDt.getPackMt(),purity.getMetal());
								
								
									if(packMetalRate!=null){
										
										packCompDt.setMetalRate(packMetalRate.getRate());
										packCompDt.setLossPerc(packMetalRate.getLossPerc());
																			
										packCompDtService.save(packCompDt);

									}
								
						}
							
							
							
							if(packCompDt.getPackMt().getParty().getType().getFieldValue().equalsIgnoreCase("Local")) {
								
								if (packCompDt.getPurity() != null && packCompDt.getMetalRate()>0) {
									ClientKtConvMast clientKtConvMast=clientKtConvService.findByPartyAndPurityAndDeactive(packDt.getPackMt().getParty(), packCompDt.getPurity(), false);
									if(clientKtConvMast !=null) {
										tempCal =  packCompDt.getMetalRate()*clientKtConvMast.getPurityConv();
										packCompDt.setPerGramMetalRate( Math.round((tempCal)*100.0)/100.0);
										packCompDt.setMetalValue( Math.round((packCompDt.getCompWt()*packCompDt.getPerGramMetalRate())*100.0)/100.0);
									
										totMetalValue +=packCompDt.getMetalValue();
										packCompDtService.save(packCompDt);
									}else {
										
										tempCal =  packCompDt.getMetalRate()*packCompDt.getPurity().getPurityConv();
										packCompDt.setPerGramMetalRate(Math.round((tempCal)*100.0)/100.0);
										
										
										packCompDt.setMetalValue( Math.round((packCompDt.getCompWt()*packCompDt.getPerGramMetalRate())*100.0)/100.0);
									
										totMetalValue +=packCompDt.getMetalValue();
										packCompDtService.save(packCompDt);
									}
								
								}
								
								
							}else {
								
								
								if (packCompDt.getPurity() != null && packCompDt.getMetalRate()>0) {
									
									Purity purity = purityService.findOne(packCompDt.getPurity().getId());
									String metalNm=purity.getMetal().getName();
									if(metalNm.equalsIgnoreCase("Gold")){
										tempCal =  packCompDt.getMetalRate()/packCompDt.getPurity().getMetal().getSpecificGravity();
										tempCal2 = (tempCal/(packCompDt.getPackMt().getIn999().equals(true)?24:23.88)) * (packCompDt.getPurity().getvPurity() != null ? packCompDt.getPurity().getvPurity() : 0.0);
										tempLoss=tempCal2*packCompDt.getLossPerc()/100;
										packCompDt.setPerGramMetalRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
										packCompDt.setMetalValue(Math.round((packCompDt.getCompWt()*packCompDt.getPerGramMetalRate())*100.0)/100.0);
										
										totMetalValue +=packCompDt.getMetalValue();
										packCompDtService.save(packCompDt);
									
									}else if (metalNm.equalsIgnoreCase("Silver") || metalNm.equalsIgnoreCase("PLATINUM")) {
										tempCal =  packCompDt.getMetalRate()/packCompDt.getPurity().getMetal().getSpecificGravity();
										tempCal2 = (tempCal*(packCompDt.getPurity().getPurityConv() != null ? packCompDt.getPurity().getPurityConv() : 0.0));
											
										
										tempLoss=tempCal2*packCompDt.getLossPerc()/100;
										packCompDt.setPerGramMetalRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
										packCompDt.setMetalValue(Math.round((packCompDt.getCompWt()*packCompDt.getPerGramMetalRate())*100.0)/100.0);
										
										totMetalValue +=packCompDt.getMetalValue();
										packCompDtService.save(packCompDt);
										
										
											
									}else if (metalNm.equalsIgnoreCase("Alloy")) {
										tempCal =  packCompDt.getMetalRate()/1000;
										tempLoss=tempCal*packCompDt.getLossPerc()/100;
										
										packCompDt.setPerGramMetalRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
										packCompDt.setMetalValue(Math.round((packCompDt.getCompWt()*packCompDt.getPerGramMetalRate())*100.0)/100.0);
										
										totMetalValue +=packCompDt.getMetalValue();
										packCompDtService.save(packCompDt);
														
										
									}
									
									
									
									
									
									
									
								
								}
								
							}
							
							
							

							
							
							
							
							
							
							
							
							
						}
				/*---------------------------------------------------------------------------------------------------------*/				
						
				/*----------------------------------------------Labour Fob------------------------------------------------------------------*/		
						
						List<PackLabDt> packLabDts = packLabDtService.findByPackDt(packDt);
						Double vTotMetalWt=0.0;
						Double vTOtMetalVal=0.0;
						
						for(PackLabDt packLabDt : packLabDts){
						
							
								vTotMetalWt = 0.0;
								vTOtMetalVal=0.0;
								for (PackMetalDt packMetalDt : packMetalDts) {
									if (packLabDt.getMetal().getId().equals(packMetalDt.getPurity().getMetal().getId())) {
										vTotMetalWt += packMetalDt.getMetalWeight();
										vTOtMetalVal +=packMetalDt.getMetalValue();
									}

								}

							
							
							
							QPackCompDt qPackCompDt = QPackCompDt.packCompDt;
							JPAQuery compQuery=new JPAQuery(entityManager);
							
							
							List<Tuple>packCompList = compQuery.from(qPackCompDt).where(
									qPackCompDt.packDt.id.eq(packDt.getId()).and(qPackCompDt.purity.metal.eq(packLabDt.getMetal())))
									.groupBy(qPackCompDt.purity.metal).list(qPackCompDt.compWt.sum(),qPackCompDt.metalValue.sum());
							
							
							for(Tuple tupleComp : packCompList){
								
								vTotMetalWt +=  tupleComp.get(qPackCompDt.compWt.sum());
								vTOtMetalVal += tupleComp.get(qPackCompDt.metalValue.sum());
									
							}
							
							
							vTotMetalWt =  Math.round(vTotMetalWt*1000.0)/1000.0;
							
							vTOtMetalVal =  Math.round(vTOtMetalVal*100.0)/100.0;
							
							
							
							
							
							if(!packLabDt.getLabourType().getCode().equalsIgnoreCase("Grade")) {
								
							
							
							
							if(packLabDt.getPerPcRate() == true){
								packLabDt.setLabourValue(Math.round((packLabDt.getLabourRate() * packDt.getPcs())*100.0)/100.0);
							
							}else if(packLabDt .getPerGramRate() == true){
								packLabDt.setLabourValue(Math.round((packLabDt.getLabourRate() * vTotMetalWt)*100.0)/100.0);
							}else if(packLabDt.getPercentage() == true){
								packLabDt.setLabourValue(Math.round(((vTOtMetalVal * packLabDt.getLabourRate())/100 )*100.0)/100.0);
							}else if(packLabDt.getPerCaratRate() == true){
								packLabDt.setLabourValue(Math.round((totCarat * packLabDt.getLabourRate())*100.0)/100.0);
							}
							
							}
							
							packLabDtService.save(packLabDt);
							
							
							if(packLabDt.getLabourType().getCode().equalsIgnoreCase("Hallmark")) {
								totHallMarkValue += packLabDt.getLabourValue();
							}else if(packLabDt.getLabourType().getCode().equalsIgnoreCase("Grade")) {
								totGradingValue += packLabDt.getLabourValue();
							}else if(packLabDt.getLabourType().getCode().equalsIgnoreCase("Stamping")) {
								totLazerValue += packLabDt.getLabourValue();
							}else if(packLabDt.getLabourType().getCode().equalsIgnoreCase("Engraving")) {
								totEngravingValue += packLabDt.getLabourValue();
							}else {
								totLabourValue += packLabDt.getLabourValue();
							}
							
						}
						
						
				/*---------------------------------------------------------------------------------------------------------*/					
						
						
						packDt.setMetalValue(Math.round((totMetalValue)*100.0)/100.0);
						packDt.setStoneValue(Math.round((totStnValue)*100.0)/100.0);
						packDt.setSetValue(Math.round((totSetValue)*100.0)/100.0);
						packDt.setHandlingValue(Math.round((totHdlgValue)*100.0)/100.0);
						packDt.setCompValue(Math.round((totCompValue)*100.0)/100.0);
						
						packDt.setGrading(Math.round((totGradingValue)*100.0)/100.0);
						packDt.setHallMarking(Math.round((totHallMarkValue)*100.0)/100.0);
						packDt.setLazerMarking(Math.round((totLazerValue)*100.0)/100.0);
						packDt.setEngraving(Math.round((totEngravingValue)*100.0)/100.0);
						packDt.setLabValue(Math.round((totLabourValue)*100.0)/100.0);	
						
//						List<Object[]> objects =new ArrayList<Object[]>();
//						
//						@SuppressWarnings("unchecked")
//						TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_packLabValue(?) }");
//						query.setParameter(1, packDt.getId());
//						objects = query.getResultList();
//						
//						if(objects.size() > 0) {
//						for(Object[] list:objects){
//							packDt.setGrading(Double.parseDouble(list[1].toString()));
//							packDt.setHallMarking(Double.parseDouble(list[2].toString()));
//							packDt.setLazerMarking(Double.parseDouble(list[3].toString()));
//							packDt.setEngraving(Double.parseDouble(list[4].toString()));
//							
//
//								packDt.setLabValue(Double.parseDouble(list[5].toString()));	
//
//						}
//						
//						}else {
//							packDt.setGrading(0.0);
//							packDt.setHallMarking(0.0);
//							packDt.setLazerMarking(0.0);
//							packDt.setEngraving(0.0);
//							
//							packDt.setLabValue(0.0);
//						}
						
												
						tempVal = packDt.getMetalValue()+packDt.getStoneValue()+packDt.getCompValue()+packDt.getLabValue()+packDt.getSetValue()
						+packDt.getHandlingValue()+packDt.getGrading()+packDt.getHallMarking()+packDt.getLazerMarking()+packDt.getEngraving();
						
						packDt.setFob(Math.round((tempVal)*100.0)/100.0);
						
						
						//packDt.setDiscAmount(Math.round((packDt.getPerPcDiscAmount()*costingDtItem.getPcs())*100.0)/100.0);
						
						
						
						
						packDt.setFinalPrice((double) Math.round(packDt.getFob()- packDt.getDiscAmount()));
						
					//	packDt.setPerPcFinalPrice(Math.round((packDt.getFinalPrice()/(packDt.getPcs()>=1?packDt.getPcs():1))*100.0)/100.0);
						
					
						
						save(packDt);
						
						
						
						
						
					} // ending main else
					
					
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
						
					
						
						return "";
	}

	@Override
	public PackStnDt applySettingRate(PackStnDt packStnDt) {
PackMetalDt packMetalDt = packMetalDtService.findByPackDtAndPartNm(packStnDt.getPackDt(), packStnDt.getPartNm());
		
		if(packMetalDt !=null){
			
			Integer perStonePc = (int) Math.round((packStnDt.getStone())/(packStnDt.getPackDt().getPcs()==0.50?1:packStnDt.getPackDt().getPcs()));
			
			Double pointerWt =Math.round((packStnDt.getCarat()/perStonePc)*1000.0)/1000.0 ;
			
			if(packStnDt.getStoneType() != null && packStnDt.getShape() != null &&	packStnDt.getSetting() != null && packStnDt.getSettingType() != null){
				
				List<SettingCharge> settingChargeList = settingChargeService.getRates(packStnDt.getPackMt().getParty(),pointerWt,
						false,packMetalDt.getPurity().getMetal(),packStnDt.getStoneType(),packStnDt.getShape(),packStnDt.getSetting(),packStnDt.getSettingType());
				
				SettingCharge settingCharge=null;
				
				if(settingChargeList.size()>0){
					settingCharge = settingChargeList.get(0);
				}
				
									
				if(settingCharge != null){
					
					if(settingCharge.getQualityWiseRate().equals(true)){
						
						List<SettingQualityRate>settingQualityRates=settingQualityRateService.findBySettingChargeAndDeactive(settingCharge, false);
						Boolean isAvailable=false;
						for(SettingQualityRate settingQualityRate:settingQualityRates){
							if(settingQualityRate.getQuality().equals(packStnDt.getQuality())){
								packStnDt.setSetRate(settingQualityRate.getQualityRate());
								isAvailable=true;
								
							}
						}
						
						if(isAvailable.equals(false)){
							packStnDt.setSetRate(settingCharge.getRate());
						}
						
						
						
						
					}else{
						packStnDt.setSetRate(settingCharge.getRate());
					}
					
					
					
				}
				
			}
			
			//costStnDtItemService.save(costStnDtItem);
			
		//	retVal=costStnDtItem.getSetRate();
		
		}
		return packStnDt;
	}

	@Override
	public PackStnDt applyPackStoneRate(PackStnDt packStnDt) {
		
		if(packStnDt.getStoneType() != null && packStnDt.getShape() != null &&  packStnDt.getQuality() != null ){
			
			List<StoneRateMast> stoneRateMast=stoneRateMastService.getStoneRateForLaxmi(packStnDt.getStoneType().getId(),packStnDt.getShape().getId(),packStnDt.getQuality().getId(), 
					packStnDt.getSizeGroup().getId(),packStnDt.getPackMt().getParty().getId(),packStnDt.getSieve());
			
			if(stoneRateMast.size() > 0){
				if(stoneRateMast.get(0).getPerPcRate() > 0){
					packStnDt.setStoneRate(stoneRateMast.get(0).getPerPcRate());
					packStnDt.setPerPcsRateFlg(true);
					
				}else{
					packStnDt.setStoneRate(stoneRateMast.get(0).getStoneRate());
					packStnDt.setPerPcsRateFlg(false);
					
				}
			}
			
		
			
			
			
			
		
		}
		return packStnDt;
	}

	@Override
	public PackStnDt applyHandlingRate(PackStnDt packStnDt) {
		
	if(packStnDt.getStoneRate() >0){
			
			List<HandlingMasterFl> handlingList = handlingMasterFLService.getRates(packStnDt.getPackMt().getParty(),packStnDt.getStoneRate());
			
			if(handlingList.size() > 0){
				if(handlingList.get(0).getPercentage() > 0){
					
					
					packStnDt.setHandlingRate(Math.round(((packStnDt.getStoneRate() * handlingList.get(0).getPercentage())/100)*100.0)/100.0);
					
					
					//costStnDtItem.setHandlingRate(handlingList.get(0).getPercentage());
					packStnDt.setHdlgPerCarat(false);
					packStnDt.setHdlgPercentWise(true);
					
				}else{
					packStnDt.setHandlingRate(handlingList.get(0).getRate());
					packStnDt.setHdlgPerCarat(true);
					packStnDt.setHdlgPercentWise(false);
					
				}
			}else{
				packStnDt.setHandlingRate(0.0);
				packStnDt.setHdlgPerCarat(false);
				packStnDt.setHdlgPercentWise(false);
			}
			
			
			

		//	costStnDtItemService.save(costStnDtItem);
			
			//retVal="success";
			
			
		}
		return packStnDt;
	}

	@Override
	public PackCompDt applyPackCompRate(PackCompDt packCompDt) {
		if(packCompDt.getComponent() != null && packCompDt.getPurity() != null){
			
			FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(packCompDt.getComponent(), packCompDt.getPackMt().getParty(),
					packCompDt.getPurity(), false);
			
			if(findingRateMast != null){
				if(findingRateMast.getPerPcRate().equals(true)){
					packCompDt.setCompRate(findingRateMast.getRate());
					packCompDt.setPerPcRate(true);
					packCompDt.setPerGramRate(false);
				}else{
					packCompDt.setCompRate(findingRateMast.getRate());
					packCompDt.setPerPcRate(false);
					packCompDt.setPerGramRate(true);
				}
			}
			
		}
		return packCompDt;
	}

	@Override
	public String locationWiseStockTransferInPacking(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		String retVal="-1";
	
		JSONArray jsonPackDt = new JSONArray(data);
		
		try {
			for (int y = 0; y < jsonPackDt.length(); y++) {
				
				JSONObject dataPackDt = (JSONObject) jsonPackDt.get(y);
			
				StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(dataPackDt.get("barcode").toString(), true, false);
				if(stockMt != null && stockMt.getCurrStk().equals(true)) {
					PackMt packMt =packMtService.findOne(pMtId);
					PackDt packDt =new PackDt();
					packDt.setBarcode(stockMt.getBarcode());
					packDt.setCreatedBy(principal.getName());
					packDt.setCreatedDate(new Date());
					packDt.setDesign(stockMt.getDesign());
					packDt.setGrossWt(stockMt.getGrossWt());
					packDt.setNetWt(stockMt.getNetWt());
					packDt.setPackMt(packMt);
					packDt.setPcs(stockMt.getQty());
					save(packDt);
					
					List<StockMetalDt>stockMetalDts =stockMetalDtService.findByStockMtAndDeactive(stockMt, false);
					for(StockMetalDt stockMetalDt :stockMetalDts) {
						PackMetalDt packMetalDt = new PackMetalDt();
						packMetalDt.setColor(stockMetalDt.getColor());
						packMetalDt.setCreateDate(new Date());
						packMetalDt.setCreatedBy(principal.getName());
						packMetalDt.setMainMetal(stockMetalDt.getMainMetal());
						packMetalDt.setMetalPcs(stockMetalDt.getMetalPcs());
						packMetalDt.setMetalWeight(stockMetalDt.getMetalWt());
						packMetalDt.setPackDt(packDt);
						packMetalDt.setPackMt(packMt);
						packMetalDt.setPartNm(stockMetalDt.getPartNm());
						packMetalDt.setPurity(stockMetalDt.getPurity());
						packMetalDt.setPurityConv(stockMetalDt.getPurityConv());
						
						packMetalDtService.save(packMetalDt);
						
					}
					
					List<StockStnDt>stockStnDts=stockStnDtService.findByStockMtAndDeactive(stockMt, false);
					for(StockStnDt stockStnDt :stockStnDts) {
						PackStnDt packStnDt =new PackStnDt();
						packStnDt.setCarat(stockStnDt.getCarat());
						packStnDt.setCreatedBy(principal.getName());
						packStnDt.setCreatedDate(new Date());
						packStnDt.setPackDt(packDt);
						packStnDt.setPackMt(packMt);
						packStnDt.setPartNm(stockStnDt.getPartNm());
						packStnDt.setQuality(stockStnDt.getQuality());
						packStnDt.setSetting(stockStnDt.getSetting());
						packStnDt.setSettingType(stockStnDt.getSettingType());
						packStnDt.setShape(stockStnDt.getShape());
						packStnDt.setSieve(stockStnDt.getSieve());
						packStnDt.setSize(stockStnDt.getSize());
						packStnDt.setSizeGroup(stockStnDt.getSizeGroup());
						packStnDt.setStone(stockStnDt.getStone());
						packStnDt.setStoneType(stockStnDt.getStoneType());
						packStnDt.setSubShape(stockStnDt.getSubShape());
						packStnDt.setCenterStone(stockStnDt.getCenterStone());
						packStnDtService.save(packStnDt);
						
						
					}
					
					
					List<StockCompDt>stockCompDts =stockCompDtService.findByStockMtAndDeactive(stockMt, false);
					for(StockCompDt stockCompDt :stockCompDts) {
						PackCompDt packCompDt = new PackCompDt();
						packCompDt.setColor(stockCompDt.getColor());
						packCompDt.setComponent(stockCompDt.getComponent());
						packCompDt.setCompQty(stockCompDt.getCompQty());
						packCompDt.setCreatedBy(principal.getName());
						packCompDt.setCreatedDate(new Date());
						packCompDt.setPackDt(packDt);
						packCompDt.setPackMt(packMt);
						packCompDt.setPurity(stockCompDt.getPurity());
						packCompDt.setPurityConv(stockCompDt.getPurityConv());
						packCompDt.setCompWt(stockCompDt.getCompWt());
						packCompDtService.save(packCompDt);
						
						
						
					}
					stockMt.setCurrStk(false);
					stockMtService.save(stockMt);
					
					StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(dataPackDt.get("barcode").toString(), true);
					StockTran stockTran2 = new StockTran();
					stockTran2.setBarcode(packDt.getBarcode());
					stockTran2.setCreatedBy(principal.getName());
					stockTran2.setCreatedDate(new Date());
					stockTran2.setCurrStatus("PackingList");
					stockTran2.setCurrStk(true);
					stockTran2.setLocation(stockTran.getLocation());
					stockTran2.setRefStkTranId(stockTran.getId());
					stockTran2.setRefTranId(packDt.getId());
					stockTran2.setTranDate(packMt.getInvDate());
					stockTran2.setTranType("PackList");
					stockTran2.setStockMt(stockMt);
					stockTranService.save(stockTran2);
					
					stockTran.setCurrStk(false);
					stockTran.setIssueDate(packMt.getInvDate());
					stockTranService.save(stockTran);
					
					
				}else {
					
					return "Item Not Found In Stock";
				}
				
			}
			
			retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public String packDtListing(Integer mtId,String flag) {
		// TODO Auto-generated method stub
		PackMt packMt=packMtService.findOne(mtId);
		List<PackDt>packDts = findByPackMt(packMt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(packDts.size()).append(",\"rows\": [");
		 Integer srNo=0;
		 for(PackDt packDt :packDts){
			 
			 List<PackMetalDt>packmetals=packMetalDtService.findByPackDt(packDt);
			 String purityVal="";
			 for(PackMetalDt packMetalDt:packmetals) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+packMetalDt.getPurity().getName()+"-"+packMetalDt.getColor().getName();
				 }else {
					 purityVal=packMetalDt.getPurity().getName()+"-"+packMetalDt.getColor().getName();
				 }
				 
			 }
				
				sb.append("{\"srNo\":\"")
			     .append(++srNo)
			     .append("\",\"id\":\"")
				 .append(packDt.getId())
				 .append("\",\"image\":\"")
				 .append(packDt.getDesign().getDefaultImage() !=null?packDt.getDesign().getDefaultImage():"")
			     .append("\",\"barcode\":\"")
				 .append(packDt.getBarcode())
				 .append("\",\"style\":\"")
				 .append(packDt.getDesign().getMainStyleNo())
				 .append("\",\"ktCol\":\"")
				 .append(purityVal)
				 .append("\",\"pcs\":\"")
				 .append(packDt.getPcs())
				 .append("\",\"grossWt\":\"")
				 .append(packDt.getGrossWt())
				 .append("\",\"netWt\":\"")
				 .append(packDt.getNetWt())
				 .append("\",\"metalValue\":\"")
				 .append(packDt.getMetalValue())
				 .append("\",\"stoneValue\":\"")
				 .append(packDt.getStoneValue())
				 .append("\",\"compValue\":\"")
				 .append(packDt.getCompValue())
				 .append("\",\"labourValue\":\"")
				 .append(packDt.getLabValue())
				 .append("\",\"setValue\":\"")
				 .append(packDt.getSetValue())
				 .append("\",\"handlingCost\":\"")
				 .append(packDt.getHandlingValue())
				 .append("\",\"fob\":\"")
				 .append(packDt.getFob())
				 .append("\",\"other\":\"")
				 .append(packDt.getOther())
				 .append("\",\"discAmount\":\"")
				 .append(packDt.getDiscAmount())
				 .append("\",\"finalPrice\":\"")
				 .append(packDt.getFinalPrice())
				 .append("\",\"hallMark\":\"")
				 .append(packDt.getHallMarking())
				 .append("\",\"grading\":\"")
				 .append(packDt.getGrading())
				 .append("\",\"lazerMark\":\"")
				 .append(packDt.getLazerMarking())
				 .append("\",\"engraving\":\"")
				 .append(packDt.getEngraving())
				 .append("\",\"rLock\":\"")
				 .append((packDt.getrLock())); //1 = lock & 0 = unlock
				 
			/*	 if(flag.equalsIgnoreCase("false")){*/
				 sb.append("\",\"actionLock\":\"")
				 .append("<a href='javascript:doPackDtLockUnLock(")
				 .append(packDt.getId())
				 .append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				 .append("\",\"action2\":\"")
				 .append("<a  href='javascript:deletePackDt(event,")
				 .append(packDt.getId())
				 .append(");' class='btn btn-xs btn-danger triggerRemove")
				 .append(packDt.getId())
				 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				 .append("\"},");
			/*
			 * }else { sb.append("\",\"actionLock\":\"") .append("")
			 * .append("\",\"action2\":\"") .append("\"},"); }
			 */
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public String packDtData(Integer dtId) {
		// TODO Auto-generated method stub
		PackDt packDt = findOne(dtId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("grossWt", packDt.getGrossWt());
		jsonObject.put("netWt", packDt.getNetWt());
		jsonObject.put("metalValue", packDt.getMetalValue());
		jsonObject.put("stoneValue", packDt.getStoneValue());
		jsonObject.put("compValue", packDt.getCompValue());
		jsonObject.put("hallMark", packDt.getHallMarking());
		jsonObject.put("lazerMark", packDt.getLazerMarking());
		jsonObject.put("grading", packDt.getGrading());
		jsonObject.put("engraving", packDt.getEngraving());
		jsonObject.put("labourValue", packDt.getLabValue());
		jsonObject.put("setValue", packDt.getSetValue());
		jsonObject.put("handlingCost", packDt.getHandlingValue());
		jsonObject.put("fob", packDt.getFob());
		jsonObject.put("other", packDt.getOther());
		jsonObject.put("finalPrice", packDt.getFinalPrice());
		jsonObject.put("discAmount", packDt.getDiscAmount());
			
		
		return jsonObject.toString();
	}

	@Override
	public PackDt findByPackMtAndBarcode(PackMt packMt,String barcode) {
		// TODO Auto-generated method stub
		return packDtRepository.findByPackMtAndBarcode(packMt,barcode);
	}

	@Override
	public String updateFobAsPer999(PackMt packMt) {
		// TODO Auto-generated method stub
				
				
				List<PackDt> packDts = findByPackMt(packMt);
				
				for(PackDt packDt : packDts){
					updateFob(packDt);
				} 
		
		return "1";
	}
	
	

}

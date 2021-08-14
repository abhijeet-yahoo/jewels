package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientKtConvMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QQuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QQuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QQuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IQuotDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientKtConvService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientStampService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterFLService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotStnDtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class QuotDtService implements IQuotDtService {

	@Autowired
	private IQuotDtRepository quotDtRepository;
	
	@Autowired
	private IClientWastageService clientWastageService;
	
	@Autowired
	private IClientKtConvService clientKtConvService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IDesignService designService;
	
		
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IDesignComponentService designComponentService;
	
	@Autowired
	private IQuotMetalService quotMetalService;
	
	@Autowired
	private IQuotMetalRateService quotMetalRateService;
	
	@Autowired
	private IHandlingMasterFLService handlingMasterFLService;
	
	@Autowired
	private IFindingRateMastService findingRateMastService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private ISettingChargeService settingChargeService;
	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;
	

	@Autowired
	private IQuotStnDtService quotStnDtService;
	
	@Autowired
	private IQuotCompDtService quotCompDtService;
	
	@Autowired
	private IQuotLabDtService quotLabDtService;
	
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IOrderMetalService orderMetalService;
	
	@Autowired
	private IOrderStnDtService orderStnDtService;
	
	@Autowired
	private IOrderCompDtService orderCompDtService;
	
	@Autowired
	private IOrderLabDtService orderLabDtService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IShapeService shapeService;

	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IProductSizeService productSizeService;
	
	@Autowired
	private IClientStampService clientStampService;
	
	@Autowired
	private IDesignMetalService designMetalService;
	
	@Override
	public List<QuotDt> findAll() {
		return quotDtRepository.findAll();
	}

	@Override
	public void save(QuotDt quotDt) {
		quotDtRepository.save(quotDt);
		
	}

	@Override
	public void delete(int id) {
		QuotDt quotDt = quotDtRepository.findOne(id);
		quotDt.setDeactive(true);
		quotDt.setDeactiveDt(new java.util.Date());
		quotDtRepository.save(quotDt);

		
	}

	@Override
	public Long count() {
		return quotDtRepository.count();
	}

	@Override
	public QuotDt findOne(int id) {
		return quotDtRepository.findOne(id);
	}

	@Override
	public QuotDt findByUniqueId(Long uniqueId) {
		return quotDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<QuotDt> findByQuotMtAndDeactive(QuotMt quotMt, Boolean deactive) {
		return quotDtRepository.findByQuotMtAndDeactive(quotMt, deactive);
	}

	
		@Override
		public String applyRate(QuotDt quotDt,Principal principal,Boolean netWtWithCompFlg) {
											
			String retVal="";
			
			if(quotDt.getrLock().equals(false)){

			applyMetal(quotDt);
			List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
						
			
			applyStoneRate(quotStnDts);
			
			List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
			applyCompRate(quotCompDts);
			
			applyLabRate(quotDt,principal);
			
			updateFob(quotDt,netWtWithCompFlg);
			retVal="1";
			
			}
			return retVal;
			
			
		}
		
		
	
		
		@Override
		public String applyMetal(QuotDt quotDt) {
				
			QuotMt quotMt =quotDt.getQuotMt();
						
			
			List<QuotMetal> quotMetals = quotMetalService.findByQuotDtAndDeactive(quotDt, false);
			if(quotMetals !=null){
				
				for(QuotMetal quotMetal:quotMetals){
					
					if(quotMetal.getrLock().equals(false)){
						if (quotMetal.getPurity() != null) {
							Purity purity = purityService.findOne(quotMetal.getPurity().getId());
																		
							QuotMetalRate quotMetalRate=quotMetalRateService.findByQuotMtAndDeactiveAndMetal(quotMt, false,purity.getMetal());
							if(quotMetalRate!=null){
								quotMetal.setMetalRate(quotMetalRate.getRate());
								
								if(quotMetalRate.getLossPerc()>0) {
									quotMetal.setLossPerc(quotMetalRate.getLossPerc());	
								}else {
									ClientWastage clientWastage =clientWastageService.findByMetalAndPartyAndDeactive(purity.getMetal(), quotMt.getParty(), false);
									if(clientWastage!=null){
										quotMetal.setLossPerc(clientWastage.getWastagePerc());	
									}
								}
									
								quotMetalService.save(quotMetal);
								
								
								if(quotMetal.getMainMetal().equals(true)){
									quotDt.setLossPercDt(quotMetal.getLossPerc());
									save(quotDt);
								}
								
							}
							
							
				}
						
						
					}
					
					
				
					
					
				
		
				}
				
				
			}
			
			
			return "";
			
		}
		
		@Override
		public String applyStoneRate(List<QuotStnDt> quotStnDts) {
			
			String retVal="error";
								 
			for(QuotStnDt quotStnDt:quotStnDts){
				
				if(quotStnDt.getrLock().equals(true)){
					continue;
				}
				
				
				quotStnDt=applySettingRate(quotStnDt);
				
				quotStnDt=applyStoneRateDt(quotStnDt);
				
				quotStnDt=applyHandlingRate(quotStnDt);
				
				quotStnDtService.save(quotStnDt);
				
				retVal="1";
				
				
			}
			
			
			
			return retVal;
		}
		
		

		@Override
		public String applyCompRate(List<QuotCompDt>quotCompDts) {
			
			String retVal="error";
			
			for(QuotCompDt quotCompDt:quotCompDts){
				
				if(quotCompDt.getrLock().equals(true)){
					continue;
				}

				quotCompDt=applyCompRateDt(quotCompDt);
							
				quotCompDtService.save(quotCompDt);
				
			}
			
				retVal="1";
			return retVal;
			
		}
		
		
		
		@Override
		public String applyLabRate(QuotDt quotDt,Principal principal) {
			
			QQuotMetal qQuotMetal =QQuotMetal.quotMetal;
			JPAQuery query=new JPAQuery(entityManager);
			
			List<Tuple> quotMetalList=null;
			
			quotMetalList = query.from(qQuotMetal).
					where(qQuotMetal.deactive.eq(false).and(qQuotMetal.quotDt.id.eq(quotDt.getId())))
					.groupBy(qQuotMetal.purity.metal).list(qQuotMetal.purity,qQuotMetal.purity.metal.name,qQuotMetal.metalWeight.sum(),qQuotMetal.metalPcs.sum());
			
			
			
			for(Tuple tuple : quotMetalList){
				QQuotCompDt qQuotCompDt = QQuotCompDt.quotCompDt;
				JPAQuery compQuery=new JPAQuery(entityManager);
				
						
				Metal metal =metalService.findByName(tuple.get(qQuotMetal.purity.metal.name));
				
				List<Tuple>quotCompList = compQuery.from(qQuotCompDt).where(qQuotCompDt.deactive.eq(false).
						and(qQuotCompDt.quotDt.id.eq(quotDt.getId())).and(qQuotCompDt.purity.metal.eq(metal)))
						.groupBy(qQuotCompDt.purity.metal).list(qQuotCompDt.metalWt.sum(),qQuotCompDt.compQty.sum());
				
				
				Double vCompWt=0.0;
				for(Tuple tupleComp : quotCompList){
						vCompWt= Math.round((tupleComp.get(qQuotCompDt.metalWt.sum()))*1000.0)/1000.0;
				}
				
				
				Double vMetalWt=Math.round((tuple.get(qQuotMetal.metalWeight.sum()))*1000.0)/1000.0;
				
				Double vNetWt = Math.round((vMetalWt+vCompWt)*1000.0)/1000.0;
				
				
				
				List<LabourCharge> labourCharges=null;
				
				 				

				labourCharges =labourChargeService.getPurityWiseRates(quotDt.getQuotMt().getParty(), quotDt.getDesign().getCategory(),
						vNetWt,false, metal,tuple.get(qQuotMetal.purity));
					 
					 if(labourCharges.size()<=0){
						 
						 labourCharges =labourChargeService.getRates(quotDt.getQuotMt().getParty(), quotDt.getDesign().getCategory(),
								 vNetWt,false, metal);
						 
					 }
				
				
				
				
				
				List<QuotLabDt>quotLabDts =quotLabDtService.findByQuotDtAndMetalAndDeactive(quotDt, metal, false);
				
				Boolean isAvailable=false;
				for(LabourCharge labourCharge :labourCharges){
					isAvailable=false;
					for(QuotLabDt quotLabDt :quotLabDts){
						if(quotLabDt.getLabourType().equals(labourCharge.getLabourType())){
							
							isAvailable=true;
							if(quotLabDt.getrLock().equals(false)){
								quotLabDt.setLabourRate(labourCharge.getRate());	
								quotLabDt.setPcsWise(false);
								quotLabDt.setGramWise(false);
								quotLabDt.setPercentWise(false);
								quotLabDt.setPerCaratRate(false);
								
								if(labourCharge.getPerPcsRate() == true){
									quotLabDt.setPcsWise(true);
								}else if(labourCharge.getPerGramRate() == true){
									quotLabDt.setGramWise(true);
								}else if(labourCharge.getPercentage() == true){
									quotLabDt.setPercentWise(true);
								}else if(labourCharge.getPerCaratRate() == true){
									quotLabDt.setPerCaratRate(true);
								}
								
								
								quotLabDtService.save(quotLabDt);
								
							}
							
						}
											
					}
					if(!isAvailable){
						
						QuotLabDt quotLabDt = new QuotLabDt();
						
						quotLabDt.setQuotMt(quotDt.getQuotMt());
						quotLabDt.setQuotDt(quotDt);
						quotLabDt.setLabourType(labourCharge.getLabourType());
						quotLabDt.setLabourRate(labourCharge.getRate());
						quotLabDt.setMetal(metal);
						
						if(labourCharge.getPerPcsRate() == true){
							quotLabDt.setPcsWise(true);
						}else if(labourCharge.getPerGramRate() == true){
							quotLabDt.setGramWise(true);
						}else if(labourCharge.getPercentage() == true){
							quotLabDt.setPercentWise(true);
						}else if(labourCharge.getPerCaratRate() == true){
							quotLabDt.setPerCaratRate(true);
						}
						
						quotLabDt.setCreatedDate(new java.util.Date());
						quotLabDt.setCreatedBy(principal.getName());
						
						quotLabDtService.save(quotLabDt);
						
						
					}
					
					
				}
				
				
				
				
				
			}
			
				
		
			return "1";
		}
		
		
	

	@Override
	public String updateFob(QuotDt quotDt,Boolean netWtWithCompFlg) {
		
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
		
	
	if(quotDt.getrLock() == true){	
	}else{
		
	
/*-------------------------------------------------Metal Fob--------------------------------*/					
		
		List<QuotMetal> quotMetals = quotMetalService.findByQuotDtAndDeactive(quotDt, false);
		if(quotMetals !=null){
			for(QuotMetal quotMetal:quotMetals){
				if (quotMetal.getPurity() != null && quotMetal.getMetalRate()>0) {
					
					if(quotDt.getQuotMt().getParty().getType().getFieldValue().equalsIgnoreCase("Local")) {
						ClientKtConvMast clientKtConvMast=clientKtConvService.findByPartyAndPurityAndDeactive(quotDt.getQuotMt().getParty(), quotMetal.getPurity(), false);
						
						if(clientKtConvMast !=null) {
							tempCal =  quotMetal.getMetalRate()*clientKtConvMast.getPurityConv();
							quotMetal.setPerGramRate(Math.round((tempCal)*100.0)/100.0);
							quotMetal.setMetalValue(Math.round((quotMetal.getMetalWeight()*quotMetal.getPerGramRate())*100.0)/100.0);
							
							
						    
						}else {
							tempCal =  quotMetal.getMetalRate()*quotMetal.getPurity().getPurityConv();
							quotMetal.setPerGramRate(Math.round((tempCal)*100.0)/100.0);
							quotMetal.setMetalValue(Math.round((quotMetal.getMetalWeight()*quotMetal.getPerGramRate())*100.0)/100.0);
						}
						
					}else {
						Purity purity = purityService.findOne(quotMetal.getPurity().getId());
						String metalNm=purity.getMetal().getName();
						if(metalNm.equalsIgnoreCase("Gold")){
							tempCal =  quotMetal.getMetalRate()/quotMetal.getPurity().getMetal().getSpecificGravity();
							tempCal2 = (tempCal/(quotMetal.getQuotMt().getIn999().equals(true)?24:23.88)) * (quotMetal.getPurity().getvPurity() != null ? quotMetal.getPurity().getvPurity() : 0.0);
							tempLoss=tempCal2*quotMetal.getLossPerc()/100;
							quotMetal.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
							quotMetal.setMetalValue(Math.round((quotMetal.getMetalWeight()*quotMetal.getPerGramRate())*100.0)/100.0);
						
						}else if (metalNm.equalsIgnoreCase("Silver") || metalNm.equalsIgnoreCase("PLATINUM")) {
							tempCal =  quotMetal.getMetalRate()/quotMetal.getPurity().getMetal().getSpecificGravity();
							tempCal2 = (tempCal*(quotMetal.getPurity().getPurityConv() != null ? quotMetal.getPurity().getPurityConv() : 0.0));
								
							
							tempLoss=tempCal2*quotMetal.getLossPerc()/100;
							quotMetal.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
							quotMetal.setMetalValue(Math.round((quotMetal.getMetalWeight()*quotMetal.getPerGramRate())*100.0)/100.0);
							
							
								
						}else if (metalNm.equalsIgnoreCase("Alloy")) {
							tempCal =  quotMetal.getMetalRate()/1000;
							tempLoss=tempCal*quotMetal.getLossPerc()/100;
							
							quotMetal.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
							quotMetal.setMetalValue(Math.round((quotMetal.getMetalWeight()*quotMetal.getPerGramRate())*100.0)/100.0);
											
							
						}
					}
					
					
					
					
				
					
					totMetalValue +=quotMetal.getMetalValue();
				
				}
				
				
				
			}
			
		}
		
/*---------------------------------------------------------------------------------------------------------*/			
		
/*----------------------------------------------Stone Fob------------------------------------------------------------------*/			
		
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotStnDt quotStnDt : quotStnDts){			
				
				if(quotStnDt.getPerPcsRateFlg().equals(false)){
					quotStnDt.setStoneValue(Math.round((quotStnDt.getCarat() * quotStnDt.getStnRate())*100.0)/100.0);
				}else{
					quotStnDt.setStoneValue(Math.round((quotStnDt.getStone() * quotStnDt.getStnRate())*100.0)/100.0);
				}
								
				quotStnDt.setSetValue(Math.round((quotStnDt.getSetRate()*quotStnDt.getStone())*100.0)/100.0);
				
				
				
				quotStnDt.setHandlingValue(Math.round((quotStnDt.getCarat() * quotStnDt.getHandlingRate())*100.0)/100.0);
				
				
				
							
				quotStnDtService.save(quotStnDt);
				totCarat +=quotStnDt.getCarat();
				totStnValue  	 += quotStnDt.getStoneValue();
				totSetValue  	 += quotStnDt.getSetValue();
				totHdlgValue 	 += quotStnDt.getHandlingValue();
									
				
			}

/*---------------------------------------------------------------------------------------------------------*/			
		
/*----------------------------------------------Component Fob------------------------------------------------------------------*/		
		
		
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
		 
		for(QuotCompDt quotCompDt : quotCompDts){
			if(quotCompDt.getPerGramRate().equals(true)){
				quotCompDt.setCompValue(Math.round((quotCompDt.getMetalWt()* quotCompDt.getCompRate())*100.0)/100.0);
			}else{
				quotCompDt.setCompValue(Math.round((quotCompDt.getCompQty()* quotCompDt.getCompRate())*100.0)/100.0);
			}
			
			if(netWtWithCompFlg.equals(true)){
				QuotMetalRate quotMetalRate=quotMetalRateService.findByQuotMtAndDeactiveAndMetal(quotCompDt.getQuotMt(), false,quotCompDt.getPurity().getMetal());
				if(quotMetalRate!=null){
					Double metalRate=quotMetalRate.getRate();
					
					if(quotDt.getQuotMt().getParty().getType().getFieldValue().equalsIgnoreCase("Local")) {
						ClientKtConvMast clientKtConvMast=clientKtConvService.findByPartyAndPurityAndDeactive(quotDt.getQuotMt().getParty(), quotCompDt.getPurity(), false);
						
						if(clientKtConvMast !=null) {
							tempCal =  metalRate*clientKtConvMast.getPurityConv();
							quotCompDt.setMetalRate(Math.round((tempCal)*100.0)/100.0);
							quotCompDt.setMetalValue(Math.round((quotCompDt.getMetalWt()*quotCompDt.getMetalRate())*100.0)/100.0);
							
							
						    
						}else {
							tempCal =  metalRate*quotCompDt.getPurity().getPurityConv();
							quotCompDt.setMetalRate(Math.round((tempCal)*100.0)/100.0);
							quotCompDt.setMetalValue(Math.round((quotCompDt.getMetalWt()*quotCompDt.getMetalRate())*100.0)/100.0);
						}
						
					}else {
						QuotMetal quotMetal=quotMetalService.findByQuotDtAndDeactiveAndMainMetal(quotCompDt.getQuotDt(),false, true);
						
						
						
						
						String metalNm=quotCompDt.getPurity().getMetal().getName();
						if(metalNm.equalsIgnoreCase("Gold")){
							tempCal =  metalRate/quotCompDt.getPurity().getMetal().getSpecificGravity();
							tempCal2 = (tempCal/(quotCompDt.getQuotMt().getIn999().equals(true)?24:23.88)) * (quotCompDt.getPurity().getvPurity() != null ? quotCompDt.getPurity().getvPurity() : 0.0);
							tempLoss=tempCal2*quotMetal.getLossPerc()/100;
							quotCompDt.setMetalRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
							quotCompDt.setMetalValue(Math.round((quotCompDt.getMetalWt()*quotCompDt.getMetalRate())*100.0)/100.0);
						
						}else if (metalNm.equalsIgnoreCase("Silver") || metalNm.equalsIgnoreCase("PLATINUM")) {
							tempCal =  metalRate/quotCompDt.getPurity().getMetal().getSpecificGravity();
							if(quotMetal.getQuotMt().getIn999().equals(true)){
								tempCal2 = tempCal;
							}else{
								tempCal2 = (tempCal*(quotCompDt.getPurity().getPurityConv() != null ? quotCompDt.getPurity().getPurityConv() : 0.0));	
							}
							
							tempLoss=tempCal2*quotMetal.getLossPerc()/100;
							quotCompDt.setMetalRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
							quotCompDt.setMetalValue(Math.round((quotCompDt.getMetalWt()*quotCompDt.getMetalRate())*100.0)/100.0);
							
							
								
						}
						
					}
					
					
					
				
					
					
				
				}
				
				totMetalValue += quotCompDt.getMetalValue();
				
			}
			
					
			quotCompDtService.save(quotCompDt);
			totCompValue += quotCompDt.getCompValue();
			
			
		}
/*---------------------------------------------------------------------------------------------------------*/				
		
/*----------------------------------------------Labour Fob------------------------------------------------------------------*/		
		
		List<QuotLabDt> quotLabDts = quotLabDtService.findByQuotDtAndDeactive(quotDt, false);
		
		
		for(QuotLabDt quotLabDt : quotLabDts){
			
			
			Double vTotMetalWt=0.0;
			Double vTOtMetalVal=0.0;
			
			if (quotMetals != null) {
				vTotMetalWt = 0.0;
				vTOtMetalVal=0.0;
				for (QuotMetal quotMetal : quotMetals) {
					if (quotLabDt.getMetal().getId().equals(
							quotMetal.getPurity().getMetal().getId())) {
						vTotMetalWt += quotMetal.getMetalWeight();
						vTOtMetalVal +=quotMetal.getMetalValue();
					}

				}

			}
			
			
			if(quotLabDt.getPcsWise() == true){
				quotLabDt.setLabourValue(Math.round((quotLabDt.getLabourRate() * 1)*100.0)/100.0);
			
			}else if(quotLabDt.getGramWise() == true){
				quotLabDt.setLabourValue(Math.round((quotLabDt.getLabourRate() * vTotMetalWt)*100.0)/100.0);
			}else if(quotLabDt.getPercentWise() == true){
				quotLabDt.setLabourValue(Math.round(((vTOtMetalVal * quotLabDt.getLabourRate())/100 )*100.0)/100.0);
			}else if(quotLabDt.getPerCaratRate() == true){
				quotLabDt.setLabourValue(Math.round((totCarat * quotLabDt.getLabourRate())*100.0)/100.0);
			}
			
			
			
			
			
			quotLabDtService.save(quotLabDt);
			totLabourValue += quotLabDt.getLabourValue();
			
		}
		
		
/*---------------------------------------------------------------------------------------------------------*/					
		
		
		quotDt.setMetalValue(Math.round((totMetalValue)*100.0)/100.0);
		quotDt.setStoneValue(Math.round((totStnValue)*100.0)/100.0);
		quotDt.setSetValue(Math.round((totSetValue)*100.0)/100.0);
		quotDt.setHdlgValue(Math.round((totHdlgValue)*100.0)/100.0);
		quotDt.setCompValue(Math.round((totCompValue)*100.0)/100.0);
		quotDt.setLabValue(Math.round((totLabourValue)*100.0)/100.0);
		
		
		
								
		tempVal = quotDt.getMetalValue()+quotDt.getStoneValue()+quotDt.getCompValue()+quotDt.getLabValue()+quotDt.getSetValue()+quotDt.getHdlgValue();
		
		quotDt.setFob(Math.round((tempVal)*100.0)/100.0);
		
		quotDt.setPerPcFinalPrice(Math.round((quotDt.getFob()- quotDt.getDiscAmount())*100.0)/100.0);
		
		quotDt.setFinalPrice(Math.round((quotDt.getPerPcFinalPrice()* quotDt.getPcs())*100.0)/100.0);
		quotDtRepository.save(quotDt);
		
		
		
		
		
	} // ending main else
	
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	
		
		return "";
		
	}



	@Override
	public Page<QuotDt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name, Integer mtId) {
		QQuotDt qQuotDt = QQuotDt.quotDt;
		BooleanExpression expression = qQuotDt.deactive.eq(false).and(qQuotDt.quotMt.id.eq(mtId));

		if(name !=null && name !=""){
			expression = qQuotDt.deactive.eq(false).and(qQuotDt.quotMt.id.eq(mtId)).and(qQuotDt.design.mainStyleNo.like(name + "%"));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<QuotDt> quotDtList = (Page<QuotDt>) quotDtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return quotDtList;
		
		
	}







@Override
	public Long countAll(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId) {
		
				QQuotDt qQuotDt = QQuotDt.quotDt;
				BooleanExpression expression = qQuotDt.deactive.eq(false).and(qQuotDt.quotMt.id.eq(mtId));

				if(name !=null && name !=""){
					expression = qQuotDt.deactive.eq(false).and(qQuotDt.quotMt.id.eq(mtId)).and(qQuotDt.design.mainStyleNo.like(name + "%"));
				}

		
			
		 return quotDtRepository.count(expression);
	}


	@Override
	public String transactionalSave(QuotDt quotDt, Integer id,
			Integer quotMtIdPk, String metalDtData,Principal principal,Boolean netWtWithCompFlg) {
		
		String action = "";
		QuotMt quotMt = quotMtService.findOne(quotMtIdPk);
		Design design = null;
		
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			design =designService.findByMainStyleNoAndDeactive(quotDt.getDesign().getMainStyleNo(), false);
		
			quotDt.setDesign(design);
			quotDt.setQuotMt(quotMt);
			quotDt.setCreatedBy(principal.getName());
			quotDt.setCreatedDate(new java.util.Date());
			quotDt.setUniqueId(new java.util.Date().getTime());
		if(quotDt.getProductSize().getId() == null){
				quotDt.setProductSize(null);
			}
			
			action = "added";
			
		} else {
			
			QuotDt quotDtEdit = findOne(id);
			quotDtEdit.setPcs(quotDt.getPcs());
			quotDtEdit.setDiscPercent(quotDt.getDiscPercent());
			quotDtEdit.setDiscAmount(quotDt.getDiscAmount());
			quotDtEdit.setModiBy(principal.getName());
			quotDtEdit.setModiDate(new java.util.Date());
			quotDtEdit.setProductSize(quotDt.getProductSize().getId() != null ? quotDt.getProductSize() : null);
			quotDtEdit.setRefNo(quotDt.getRefNo());
			quotDtEdit.setStampInst(quotDt.getStampInst());
			quotDtEdit.setRemark(quotDt.getRemark());
			quotDtEdit.setPurity(quotDt.getPurity());
			quotDtEdit.setColor(quotDt.getColor());
			quotDtEdit.setGrossWt(quotDt.getGrossWt());
			quotDtEdit.setBarcode(quotDt.getBarcode());
						
			save(quotDtEdit);
			
			
			action = "updated";
			
		}
		

				
		if(action.equals("added")){
			
			
			
			save(quotDt);
			
			quotMetalService.addQuotMetal(metalDtData, quotMt, quotDt, principal);
			
			List<DesignStone> designStones = designStoneService.findByDesign(design); 
			quotStnDtService.setQuotStnDt(designStones, quotMt, quotDt,principal);
			
			List<DesignComponent> designComponents = designComponentService.findByDesign(design);
			quotCompDtService.setQuotCompDt(designComponents, quotMt, quotDt, principal);
			
			
			 Double totNetWt=0.0;	
			 
			
			 
			 List<QuotMetal> quotMetals=quotMetalService.findByQuotDtAndDeactive(quotDt, false);
			 if(quotMetals.size()>0){
				 for(QuotMetal quotMetal :quotMetals){
					 totNetWt  += quotMetal.getMetalWeight();
					
					 
					 
				 }
				 
			 }
			 
					
				List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
				Double totStnCarat = 0.0;
				
				
				for(QuotStnDt quotStnDt:quotStnDts){
					totStnCarat += quotStnDt.getCarat();
					
					 
				}
				
				Double temVal = totStnCarat/5;
				Double totGrossWt = totNetWt+temVal;
				
				List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
				Double totCompMetalWt = 0.0;
				for(QuotCompDt quotCompDt:quotCompDts){
					totCompMetalWt += quotCompDt.getMetalWt();
				}
				
				totGrossWt += totCompMetalWt;
				
				Double grossWtdiff =Math.round((quotDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
				
				QuotMetal quotMetal = quotMetalService.findByQuotDtAndDeactiveAndMainMetal(quotDt, false, true);
				
				quotMetal.setMetalWeight(Math.round((quotMetal.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
				quotMetalService.save(quotMetal);
				
				if(netWtWithCompFlg.equals(true)){
					totNetWt += totCompMetalWt+grossWtdiff;
				}else {
					totNetWt +=grossWtdiff;
				}
				

				quotDt.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
				
				
				save(quotDt);
			
				
				
				applyRate(quotDt,principal,netWtWithCompFlg);
				
			
			
			
			
			
	
			
		}else{
			
			quotMetalService.addQuotMetal(metalDtData, quotMt, quotDt, principal);
			
			
			QuotDt quotDtEdit = findOne(id);
			
			
			
			 Double totNetWt=0.0;	
			 
			 List<QuotMetal> quotMetals=quotMetalService.findByQuotDtAndDeactive(quotDtEdit, false);
			 if(quotMetals.size()>0){
				 for(QuotMetal quotMetal :quotMetals){
					 totNetWt  += quotMetal.getMetalWeight();	 
			
				 }
				 
			 }
			 
					
				List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDtEdit, false);
				Double totStnCarat = 0.0;
				
				for(QuotStnDt quotStnDt:quotStnDts){
					totStnCarat += quotStnDt.getCarat();
				
				}
				
				Double temVal = totStnCarat/5;
				Double totGrossWt = totNetWt+temVal;
				
				List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDtEdit, false);
				Double totCompMetalWt = 0.0;
				for(QuotCompDt quotCompDt:quotCompDts){
					totCompMetalWt += quotCompDt.getMetalWt();
				}
				
				totGrossWt += totCompMetalWt;
				
				Double grossWtdiff = Math.round((quotDtEdit.getGrossWt()-totGrossWt)*1000.0)/1000.0;
				
				QuotMetal quotMetal = quotMetalService.findByQuotDtAndDeactiveAndMainMetal(quotDtEdit, false, true);
				quotMetal.setMetalWeight(Math.round((quotMetal.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
				quotMetalService.save(quotMetal);		
				
				if(netWtWithCompFlg.equals(true)){
					totNetWt += totCompMetalWt+grossWtdiff;
				}else {
					totNetWt +=grossWtdiff;
				}
				
				
				quotDtEdit.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
				
				
				
				save(quotDtEdit);
			
				 applyMetal(quotDtEdit );
				
				applyLabRate(quotDtEdit,principal);				
				
				updateFob(quotDtEdit,netWtWithCompFlg);
				
			
		}
		
		updateKtDesc(quotDt.getId());
		updateQltyDesc(quotDt.getId());
		
			
			
		
		
		
		
		
		
		
		
		
		return action;
	}

	@Override
	public String updateGrossWt(QuotDt quotDt,Boolean netWtWithCompFlg) {
		
		 Double totNetWt=0.0;	
		 List<QuotMetal> quotMetals=quotMetalService.findByQuotDtAndDeactive(quotDt, false);
		 if(quotMetals.size()>0){
			 for(QuotMetal quotMetal :quotMetals){
				 totNetWt  += quotMetal.getMetalWeight();	 
			 }
			 
		 }
		 
				
			List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
			Double totStnCarat = 0.0;
			for(QuotStnDt quotStnDt:quotStnDts){
				totStnCarat += quotStnDt.getCarat();
			}
			
			Double temVal = totStnCarat/5;
			Double totGrossWt = totNetWt+temVal;
		
			
			List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
			Double totCompMetalWt = 0.0;
			for(QuotCompDt quotCompDt:quotCompDts){
				totCompMetalWt += quotCompDt.getMetalWt();
			}
			
			totGrossWt += totCompMetalWt;
			
			Double grossWtdiff =Math.round((quotDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
			
			QuotMetal quotMetal = quotMetalService.findByQuotDtAndDeactiveAndMainMetal(quotDt, false, true);
			quotMetal.setMetalWeight(Math.round((quotMetal.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
			quotMetalService.save(quotMetal);	
			
			if(netWtWithCompFlg.equals(true)){
				totNetWt += totCompMetalWt+grossWtdiff;
			}else {
				totNetWt += grossWtdiff;
			}
			
			
			quotDt.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
			
			save(quotDt);
			
		return null;
	}
	
	

	@Override
	public String applyDiamondRate(QuotDt quotDt) {
		
		
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		
		for(QuotStnDt quotStnDt:quotStnDts){
			if(quotStnDt.getStoneType() != null && quotStnDt.getShape() != null &&  quotStnDt.getQuality() != null ){
				
				List<StoneRateMast> stoneRateMast=stoneRateMastService.getStoneRate(quotStnDt.getStoneType().getId(),quotStnDt.getShape().getId(),quotStnDt.getQuality().getId(), 
						quotStnDt.getSizeGroup().getId(),quotStnDt.getQuotMt().getParty().getId(),quotStnDt.getSieve());
				
				if(stoneRateMast.size() > 0){
					if(stoneRateMast.get(0).getPerPcRate() > 0){
						quotStnDt.setStnRate(stoneRateMast.get(0).getPerPcRate());
						quotStnDt.setPerPcsRateFlg(true);
						
					}else{
						quotStnDt.setStnRate(stoneRateMast.get(0).getStoneRate());
						quotStnDt.setPerPcsRateFlg(false);
						
					}
				}
			
			}
			
			quotStnDtService.save(quotStnDt);
		}
		
		// TODO Auto-generated method stub
		return "1";
	}

	@Override
	public String quotToQuotPickup(String pOIds, String pTransferQty,
			Integer mtId, Principal principal, Integer partyId) {
		
		String retVal = "1";
		
		if(pOIds.length()<=0){
			return retVal="-1";
		}
		
		

		String quotDtId[] =pOIds.split(",");
		String trfQty[] = pTransferQty.split(",");
		for(int i=0;i<trfQty.length;i++){
			if((Double.parseDouble(trfQty[i]))<=0){
				return retVal="-2";
			}
		}
		
		
		
	
			QuotMt quotMt =quotMtService.findOne(mtId);
			
	    //OrderMt	orderMt = orderMtService.findOne(mtId);
	
		/*int incs = 0;*/
		for(int i1=0;i1<quotDtId.length;i1++){
	
		QuotDt quotDt = findOne(Integer.parseInt(quotDtId[i1]));
	

		QuotDt quotDt2  = new QuotDt();
		quotDt2.setQuotMt(quotMt);
		quotDt2.setDesign(quotDt.getDesign());
		quotDt2.setPurity(quotDt.getPurity());
		quotDt2.setColor(quotDt.getColor());
		quotDt2.setLossPercDt(quotDt.getLossPercDt());
		quotDt2.setPcs(Double.parseDouble(trfQty[i1]));
		quotDt2.setGrossWt(quotDt.getGrossWt());
		quotDt2.setNetWt(quotDt.getNetWt());
		quotDt2.setCreatedBy(principal.getName());
		quotDt2.setCreatedDate(new Date());
		quotDt2.setUniqueId(new java.util.Date().getTime());
		quotDt2.setDesignRemark(quotDt.getDesignRemark());
		quotDt2.setRemark(quotDt.getRemark());
		quotDt2.setStampInst(quotDt.getStampInst());
		quotDt2.setProductSize(quotDt.getProductSize());
		quotDt2.setPurityConv(quotDt.getPurityConv());
		quotDt2.setMetalValue(quotDt.getMetalValue());
		quotDt2.setStoneValue(quotDt.getStoneValue());
		quotDt2.setSetValue(quotDt.getSetValue());
		quotDt2.setCompValue(quotDt.getCompValue());
		quotDt2.setLabValue(quotDt.getLabValue());
		quotDt2.setHdlgValue(quotDt.getHdlgValue());
		quotDt2.setBarcode(quotDt.getBarcode());
	
		
		save(quotDt2);
		
		//OrderDt orderDt2 = findByUniqueId(orderDt.getUniqueId());
		
		
		List<QuotMetal> quotMetals=quotMetalService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotMetal quotMetal :quotMetals){
			QuotMetal quotMetal2 = new QuotMetal();
			quotMetal2.setColor(quotMetal.getColor());
			quotMetal2.setCreateDate(new Date());
			quotMetal2.setCreatedBy(principal.getName());
			quotMetal2.setLossPerc(quotMetal.getLossPerc());
			quotMetal2.setMainMetal(quotMetal.getMainMetal());
			quotMetal2.setMetalPcs(quotMetal.getMetalPcs());
			quotMetal2.setMetalRate(quotMetal.getMetalRate());
			quotMetal2.setMetalValue(quotMetal.getMetalValue());
			quotMetal2.setMetalWeight(quotMetal.getMetalWeight());
			quotMetal2.setQuotDt(quotDt2);
			quotMetal2.setQuotMt(quotMt);
			quotMetal2.setPartNm(quotMetal.getPartNm());
			quotMetal2.setPerGramRate(quotMetal.getPerGramRate());
			quotMetal2.setPurity(quotMetal.getPurity());
			
			quotMetalService.save(quotMetal2);
			
		}
		
		
		
		// for OrderStnDt
		
	
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotStnDt quotStnDt : quotStnDts){
			QuotStnDt quotStnDt2 = new QuotStnDt();
		quotStnDt2.setQuotDt(quotDt2);
				quotStnDt2.setQuotMt(quotMt);
				quotStnDt2.setCreatedBy(principal.getName());
				quotStnDt2.setCreatedDate(new java.util.Date());
				quotStnDt2.setStoneType(quotStnDt.getStoneType());
				quotStnDt2.setShape(quotStnDt.getShape());
				quotStnDt2.setSubShape(quotStnDt.getSubShape());
				quotStnDt2.setQuality(quotStnDt.getQuality());
				quotStnDt2.setSize(quotStnDt.getSize());
				quotStnDt2.setSieve(quotStnDt.getSieve());
				quotStnDt2.setSizeGroup(quotStnDt.getSizeGroup());
				quotStnDt2.setStone(quotStnDt.getStone());
				quotStnDt2.setCarat(quotStnDt.getCarat());
				quotStnDt2.setSetting(quotStnDt.getSetting());
				quotStnDt2.setSettingType(quotStnDt.getSettingType());
				quotStnDt2.setStnRate(quotStnDt.getStnRate());
				quotStnDt2.setStoneValue(quotStnDt.getStoneValue());
				quotStnDt2.setSetRate(quotStnDt.getSetRate());
				quotStnDt2.setSetValue(quotStnDt.getSetValue());
				quotStnDt2.setHandlingRate(quotStnDt.getHandlingRate());
				quotStnDt2.setHandlingValue(quotStnDt.getHandlingValue());
				quotStnDt2.setHdlgPercentWise(quotStnDt.getHdlgPercentWise());
				quotStnDt2.setHdlgPerCarat(quotStnDt.getHdlgPerCarat());
				quotStnDt2.setPerPcsRateFlg(quotStnDt.getPerPcsRateFlg());
				quotStnDt2.setPartNm(quotStnDt.getPartNm());
							
				quotStnDtService.save(quotStnDt2);
			}
		
		
	
		// for OrderCompDt
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
		
		for(QuotCompDt quotCompDt : quotCompDts){
			
			QuotCompDt quotCompDt2 = new QuotCompDt();
			
			quotCompDt2.setQuotDt(quotDt2);
			quotCompDt2.setQuotMt(quotMt);
			quotCompDt2.setPurity(quotCompDt.getPurity());
			quotCompDt2.setColor(quotCompDt.getColor());
			quotCompDt2.setCreatedBy(principal.getName());
			quotCompDt2.setCreatedDate(new java.util.Date());
			quotCompDt2.setComponent(quotCompDt.getComponent());
			quotCompDt2.setCompQty(quotCompDt.getCompQty());
			quotCompDt2.setPurityConv(quotCompDt.getPurityConv());
			quotCompDt2.setMetalWt(quotCompDt.getMetalWt());
			quotCompDt2.setCompRate(quotCompDt.getCompRate());
			quotCompDt2.setCompValue(quotCompDt.getCompValue());
			quotCompDt2.setPerPcRate(quotCompDt.getPerPcRate());
			quotCompDt2.setPerGramRate(quotCompDt.getPerGramRate());
			
			
			quotCompDtService.save(quotCompDt2);
		}
		
		
		//for Order Labour
		List<QuotLabDt> quotLabDts =quotLabDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotLabDt quotLabDt :quotLabDts){
			
			QuotLabDt quotLabDt2 =new QuotLabDt();
			
			quotLabDt2.setCreatedBy(principal.getName());
			quotLabDt2.setCreatedDate(new Date());
			quotLabDt2.setGramWise(quotLabDt.getGramWise());
			quotLabDt2.setLabourRate(quotLabDt.getLabourRate());
			quotLabDt2.setLabourType(quotLabDt.getLabourType());
			quotLabDt2.setLabourValue(quotLabDt.getLabourValue());
			quotLabDt2.setMetal(quotLabDt.getMetal());
			quotLabDt2.setQuotDt(quotDt2);
			quotLabDt2.setQuotMt(quotMt);
			quotLabDt2.setPcsWise(quotLabDt.getPcsWise());
			quotLabDt2.setPerCaratRate(quotLabDt.getPerCaratRate());
			quotLabDt2.setPercentWise(quotLabDt.getPercentWise());
			
			
			quotLabDtService.save(quotLabDt2);
			
			
		}
	
		
		updateKtDesc(quotDt2.getId());
		updateQltyDesc(quotDt2.getId());
		
		/*quotDt.setAdjustedQty(quotDt.getAdjustedQty()+Double.parseDouble(trfQty[i]));
		quotDtService.save(quotDt);
*/
		}
		retVal=quotMt.getId()+"";
		
		
	
		return retVal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<QuotDt> quotDtPickUpList(Integer limit, Integer offset,
			String sort, String order, String name, Integer mtId,String forOrderNm,String pickUpType) {
		
		
		
		if(forOrderNm !=null && !forOrderNm.isEmpty()){
			
			List<Integer> styleIds = new ArrayList<Integer>();
			String vStyleIds="";
			if(name !=null && !name.isEmpty()){
				
			//Page<Design>designs2 = designService.findByMainStyleNo(limit, offset, sort, order, name, true);
			List<Design>designs =designService.findByMainStyleNoContaining(name +"%");
			
			
			
			for(Design design :designs ){
				styleIds.add(design.getId());
				
			}
			
			
			 vStyleIds = styleIds.toString()
				    				    .replace("[", "") 
				    .replace("]", "").trim();  
				
			}
			
			OrderMt orderMt =orderMtService.findByInvNoAndDeactive(forOrderNm, false);
			
		
			
		int page = (offset == 0 ? 0 : (offset / limit));

				if (sort == null) {
					sort = "id";
				}
			
				TypedQuery<QuotDt> query2=null;	
		if(styleIds.size()>0){
			 query2 = (TypedQuery<QuotDt>) entityManager
					.createNativeQuery("SELECT * FROM quotdt"
							+ " WHERE deactive =0 and mtid ="+mtId+" and "
									+ "  (styleid, purityid,colorid) IN ( SELECT styleid, purityid,colorid FROM sorddt where deactive =0 "
									+ " and mtid ="+orderMt.getId()+ " and styleid in ("+vStyleIds+") )", QuotDt.class);
			
		}else{
			 query2 = (TypedQuery<QuotDt>) entityManager
					.createNativeQuery("SELECT * FROM quotdt"
							+ " WHERE deactive =0 and mtid ="+mtId+" and "
									+ "  (styleid, purityid,colorid) IN ( SELECT styleid, purityid,colorid FROM sorddt where deactive =0 "
									+ " and mtid ="+orderMt.getId()+ " )", QuotDt.class);
		}
				
		  

		  List<QuotDt> results = query2.getResultList();
					  
		 Long total = (long) results.size();
					  
		 Pageable pageable = new PageRequest(page, limit,
								(order.equalsIgnoreCase("asc") ? Direction.ASC
										: Direction.DESC), sort);
					  
		 TypedQuery<QuotDt> query3=null;
		 if(styleIds.size()>0){
			 
			   query3 = (TypedQuery<QuotDt>) entityManager.createNativeQuery("SELECT * FROM quotdt "
						+ " WHERE deactive =0 and mtid ="+mtId+" and "
								+ "  (styleid, purityid,colorid) IN ( SELECT styleid, purityid,colorid FROM sorddt where deactive =0 "
								+ " and mtid ="+orderMt.getId()+ " and styleid in ("+vStyleIds+") ) limit "+limit+" offset "+offset, QuotDt.class);
			 
		 }else{
			 
			  query3 = (TypedQuery<QuotDt>) entityManager.createNativeQuery("SELECT * FROM quotdt "
						+ " WHERE deactive =0 and mtid ="+mtId+" and "
								+ "  (styleid, purityid,colorid) IN ( SELECT styleid, purityid,colorid FROM sorddt where deactive =0 "
								+ " and mtid ="+orderMt.getId()+ " ) limit "+limit+" offset "+offset, QuotDt.class);
			 
		 }
				
				
					
					  
					  
					  List<QuotDt> results3 = query3.getResultList();
					  
					  
						List<QuotDt> content = total > pageable.getOffset() ? results3 : Collections.<QuotDt> emptyList();

						
						
						
						Page<QuotDt> quotDtList =new PageImpl<QuotDt>(content, pageable, total);
					  
					  
						
						return quotDtList;
					  
			
			
			
			
		}else{
			QQuotDt qQuotDt = QQuotDt.quotDt;
			
			BooleanExpression expression =null;
			if (pickUpType.equalsIgnoreCase("orderFromQuot")) {
				 expression = qQuotDt.deactive.eq(false).and(qQuotDt.quotMt.id.eq(mtId)).and(qQuotDt.pcs.subtract(qQuotDt.adjustedQty).gt(0));	
					if(name !=null && name !=""){
						expression = qQuotDt.deactive.eq(false).and(qQuotDt.quotMt.id.eq(mtId)).and(qQuotDt.design.mainStyleNo.like(name + "%")).and(qQuotDt.pcs.subtract(qQuotDt.adjustedQty).gt(0));
					}
				
			}else {
				 expression = qQuotDt.deactive.eq(false).and(qQuotDt.quotMt.id.eq(mtId));	
					if(name !=null && name !=""){
						expression = qQuotDt.deactive.eq(false).and(qQuotDt.quotMt.id.eq(mtId)).and(qQuotDt.design.mainStyleNo.like(name + "%"));
					}

			}
			
			

		
			int page = (offset == 0 ? 0 : (offset / limit));

			if (sort == null) {
				sort = "id";
			}

			Page<QuotDt> quotDtList = (Page<QuotDt>) quotDtRepository.findAll(
					expression,
					new PageRequest(page, limit,
							(order.equalsIgnoreCase("asc") ? Direction.ASC
									: Direction.DESC), sort));

			
			
			return quotDtList;
		}
		
		
		
		
		
		
	}
	
	
	
	@Override
	public QuotStnDt applySettingRate(QuotStnDt quotStnDt) {
		
		QuotMetal quotMetal = quotMetalService.findByQuotDtAndDeactiveAndPartNm(quotStnDt.getQuotDt(), false, quotStnDt.getPartNm());
		
		if(quotMetal !=null){
			
			Double pointerWt =Math.round((quotStnDt.getCarat()/quotStnDt.getStone())*1000.0)/1000.0 ;
			
			if(quotStnDt.getStoneType() != null && quotStnDt.getShape() != null &&
				quotStnDt.getSetting() != null && quotStnDt.getSettingType() != null){
				
				

				List<SettingCharge> settingChargeList = settingChargeService.getRates(quotStnDt.getQuotMt().getParty(),pointerWt,
						false,quotMetal.getPurity().getMetal(),quotStnDt.getStoneType(),quotStnDt.getShape(),quotStnDt.getSetting(),quotStnDt.getSettingType());
				
				SettingCharge settingCharge=null;
				
				if(settingChargeList.size()>0){
					settingCharge = settingChargeList.get(0);
				}
				
									
				if(settingCharge != null){
					
					if(settingCharge.getQualityWiseRate().equals(true)){
						
						List<SettingQualityRate>settingQualityRates=settingQualityRateService.findBySettingChargeAndDeactive(settingCharge, false);
						Boolean isAvailable=false;
						for(SettingQualityRate settingQualityRate:settingQualityRates){
							if(settingQualityRate.getQuality().equals(quotStnDt.getQuality())){
								quotStnDt.setSetRate(settingQualityRate.getQualityRate());
								isAvailable=true;
							}
						}
						
						if(isAvailable.equals(false)){
							quotStnDt.setSetRate(settingCharge.getRate());
						}
						
						
						
						
					}else{
						quotStnDt.setSetRate(settingCharge.getRate());
					}
					
					
					
					
					
					
					
				}
				
			}
		
		}
		return quotStnDt;
	}

	@Override
	public QuotStnDt applyHandlingRate(QuotStnDt quotStnDt) {

		if(quotStnDt.getStnRate() >0){
			
			List<HandlingMasterFl> handlingList = handlingMasterFLService.getRates(quotStnDt.getQuotMt().getParty(),quotStnDt.getStnRate());
			
			if(handlingList.size() > 0){
				if(handlingList.get(0).getPercentage() > 0){
					
					
						quotStnDt.setHandlingRate(Math.round(((quotStnDt.getStnRate() * handlingList.get(0).getPercentage())/100)*100.0)/100.0);
					
					
					//quotStnDt.setHandlingRate(handlingList.get(0).getPercentage());
					quotStnDt.setHdlgPerCarat(false);
					quotStnDt.setHdlgPercentWise(true);
					
				}else{
					quotStnDt.setHandlingRate(handlingList.get(0).getRate());
					quotStnDt.setHdlgPerCarat(true);
					quotStnDt.setHdlgPercentWise(false);
					
				}
			}
			
			
		}
		return quotStnDt;
		
	}

	@Override
	public QuotStnDt applyStoneRateDt(QuotStnDt quotStnDt) {
		
		if(quotStnDt.getStoneType() != null && quotStnDt.getShape() != null &&  quotStnDt.getQuality() != null ){
			
			List<StoneRateMast> stoneRateMast=stoneRateMastService.getStoneRate(quotStnDt.getStoneType().getId(),quotStnDt.getShape().getId(),quotStnDt.getQuality().getId(), 
					quotStnDt.getSizeGroup().getId(),quotStnDt.getQuotMt().getParty().getId(),quotStnDt.getSieve());
			
			if(stoneRateMast.size() > 0){
				if(stoneRateMast.get(0).getPerPcRate() > 0){
					quotStnDt.setStnRate(stoneRateMast.get(0).getPerPcRate());
					quotStnDt.setPerPcsRateFlg(true);
					
				}else{
					quotStnDt.setStnRate(stoneRateMast.get(0).getStoneRate());
					quotStnDt.setPerPcsRateFlg(false);
					
				}
			}
			
			//costStnDtItemService.save(costStnDtItem);
			
		}
		return quotStnDt;
	}

	@Override
	public QuotCompDt applyCompRateDt(QuotCompDt quotCompDt) {
		
		
		if(quotCompDt.getComponent() != null && quotCompDt.getPurity() != null){
			
			FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(quotCompDt.getComponent(), quotCompDt.getQuotMt().getParty(),
					quotCompDt.getPurity(), false);
			
			if(findingRateMast != null){
				if(findingRateMast.getPerPcRate().equals(true)){
					quotCompDt.setCompRate(findingRateMast.getRate());
					quotCompDt.setPerPcRate(true);
					quotCompDt.setPerGramRate(false);
				}else{
					quotCompDt.setCompRate(findingRateMast.getRate());
					quotCompDt.setPerPcRate(false);
					quotCompDt.setPerGramRate(true);
				}
			}
			
		}
		return quotCompDt;
		

	}

	@Override
	public String orderToQuotPickup(String pOIds,Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		String retVal = "-1";
		
		if(pOIds.length()<=0){
			return retVal="-1";
		}
		
		QuotMt quotMt = quotMtService.findOne(mtId);

		String orderDtId[] =pOIds.split(",");
		
		for(int i1=0;i1<orderDtId.length;i1++){
			
			OrderDt orderDt = orderDtService.findOne(Integer.parseInt(orderDtId[i1]));
		

			QuotDt quotDt2  = new QuotDt();
			quotDt2.setQuotMt(quotMt);
			quotDt2.setDesign(orderDt.getDesign());
			quotDt2.setPurity(orderDt.getPurity());
			quotDt2.setColor(orderDt.getColor());
	//		quotDt2.setLossPercDt(orderDt.getLossPercDt());
			quotDt2.setPcs(orderDt.getPcs());
			quotDt2.setGrossWt(orderDt.getGrossWt());
			quotDt2.setNetWt(orderDt.getNetWt());
			quotDt2.setCreatedBy(principal.getName());
			quotDt2.setCreatedDate(new Date());
			quotDt2.setUniqueId(new java.util.Date().getTime());
			quotDt2.setDesignRemark(orderDt.getDesignRemark());
			quotDt2.setRemark(orderDt.getRemark());
			quotDt2.setStampInst(orderDt.getStampInst());
			quotDt2.setProductSize(orderDt.getProductSize());
			quotDt2.setPurityConv(orderDt.getPurityConv());
			quotDt2.setMetalValue(orderDt.getMetalValue());
			quotDt2.setStoneValue(orderDt.getStoneValue());
			quotDt2.setSetValue(orderDt.getSetValue());
			quotDt2.setCompValue(orderDt.getCompValue());
			quotDt2.setLabValue(orderDt.getLabValue());
			quotDt2.setHdlgValue(orderDt.getHdlgValue());
			quotDt2.setBarcode(orderDt.getBarcode());
		
			
			save(quotDt2);
			
			//OrderDt orderDt2 = findByUniqueId(orderDt.getUniqueId());
			
			
			List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderDt, false);
			for(OrderMetal orderMetal :orderMetals){
				QuotMetal quotMetal2 = new QuotMetal();
				quotMetal2.setColor(orderMetal.getColor());
				quotMetal2.setCreateDate(new Date());
				quotMetal2.setCreatedBy(principal.getName());
				quotMetal2.setLossPerc(orderMetal.getLossPerc());
				quotMetal2.setMainMetal(orderMetal.getMainMetal());
				quotMetal2.setMetalPcs(orderMetal.getMetalPcs());
				quotMetal2.setMetalRate(orderMetal.getMetalRate());
				quotMetal2.setMetalValue(orderMetal.getMetalValue());
				quotMetal2.setMetalWeight(orderMetal.getMetalWeight());
				quotMetal2.setQuotDt(quotDt2);
				quotMetal2.setQuotMt(quotMt);
				quotMetal2.setPartNm(orderMetal.getPartNm());
				quotMetal2.setPerGramRate(orderMetal.getPerGramRate());
				quotMetal2.setPurity(orderMetal.getPurity());
				
				quotMetalService.save(quotMetal2);
				
			}
			
			
			
			// for OrderStnDt
			
		
			List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
			for(OrderStnDt orderStnDt : orderStnDts){
				QuotStnDt quotStnDt2 = new QuotStnDt();
					quotStnDt2.setQuotDt(quotDt2);
					quotStnDt2.setQuotMt(quotMt);
					quotStnDt2.setCreatedBy(principal.getName());
					quotStnDt2.setCreatedDate(new java.util.Date());
					quotStnDt2.setStoneType(orderStnDt.getStoneType());
					quotStnDt2.setShape(orderStnDt.getShape());
					quotStnDt2.setSubShape(orderStnDt.getSubShape());
					quotStnDt2.setQuality(orderStnDt.getQuality());
					quotStnDt2.setSize(orderStnDt.getSize());
					quotStnDt2.setSieve(orderStnDt.getSieve());
					quotStnDt2.setSizeGroup(orderStnDt.getSizeGroup());
					quotStnDt2.setStone(orderStnDt.getStone());
					quotStnDt2.setCarat(orderStnDt.getCarat());
					quotStnDt2.setSetting(orderStnDt.getSetting());
					quotStnDt2.setSettingType(orderStnDt.getSettingType());
					quotStnDt2.setStnRate(orderStnDt.getStnRate());
					quotStnDt2.setStoneValue(orderStnDt.getStoneValue());
					quotStnDt2.setSetRate(orderStnDt.getSetRate());
					quotStnDt2.setSetValue(orderStnDt.getSetValue());
					quotStnDt2.setHandlingRate(orderStnDt.getHandlingRate());
					quotStnDt2.setHandlingValue(orderStnDt.getHandlingValue());
					quotStnDt2.setHdlgPercentWise(orderStnDt.getHdlgPercentWise());
					quotStnDt2.setHdlgPerCarat(orderStnDt.getHdlgPerCarat());
					quotStnDt2.setPerPcsRateFlg(orderStnDt.getPerPcsRateFlg());
					quotStnDt2.setPartNm(orderStnDt.getPartNm());
								
					quotStnDtService.save(quotStnDt2);
				}
			
			
		
			// for OrderCompDt
			List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDt, false);
			
			for(OrderCompDt orderCompDt : orderCompDts){
				
				QuotCompDt quotCompDt2 = new QuotCompDt();
				
				quotCompDt2.setQuotDt(quotDt2);
				quotCompDt2.setQuotMt(quotMt);
				quotCompDt2.setPurity(orderCompDt.getPurity());
				quotCompDt2.setColor(orderCompDt.getColor());
				quotCompDt2.setCreatedBy(principal.getName());
				quotCompDt2.setCreatedDate(new java.util.Date());
				quotCompDt2.setComponent(orderCompDt.getComponent());
				quotCompDt2.setCompQty(orderCompDt.getCompQty());
				quotCompDt2.setPurityConv(orderCompDt.getPurityConv());
				quotCompDt2.setMetalWt(orderCompDt.getMetalWt());
				quotCompDt2.setCompRate(orderCompDt.getCompRate());
				quotCompDt2.setCompValue(orderCompDt.getCompValue());
				quotCompDt2.setPerPcRate(orderCompDt.getPerPcRate());
				quotCompDt2.setPerGramRate(orderCompDt.getPerGramRate());
				
				
				quotCompDtService.save(quotCompDt2);
			}
			
			
			//for Order Labour
			List<OrderLabDt> orderLabDts =orderLabDtService.findByOrderDtAndDeactive(orderDt, false);
			for(OrderLabDt orderLabDt :orderLabDts){
				
				QuotLabDt quotLabDt2 =new QuotLabDt();
				
				quotLabDt2.setCreatedBy(principal.getName());
				quotLabDt2.setCreatedDate(new Date());
				quotLabDt2.setGramWise(orderLabDt.getGramWise());
				quotLabDt2.setLabourRate(orderLabDt.getLabourRate());
				quotLabDt2.setLabourType(orderLabDt.getLabourType());
				quotLabDt2.setLabourValue(orderLabDt.getLabourValue());
				quotLabDt2.setMetal(orderLabDt.getMetal());
				quotLabDt2.setQuotDt(quotDt2);
				quotLabDt2.setQuotMt(quotMt);
				quotLabDt2.setPcsWise(orderLabDt.getPcsWise());
				quotLabDt2.setPerCaratRate(orderLabDt.getPerCaratRate());
				quotLabDt2.setPercentWise(orderLabDt.getPercentWise());
				
				
				quotLabDtService.save(quotLabDt2);
				
				
			}
		
			updateKtDesc(quotDt2.getId());
			updateQltyDesc(quotDt2.getId());
			
		retVal = "1";
			}

		
		
		return retVal;
	}

	@Override
	public String updateKtDesc(Integer quotDtId) {
		// TODO Auto-generated method stub
	
		QuotDt quotDt =findOne(quotDtId);
		 List<QuotMetal> quotMetals=quotMetalService.findByQuotDtAndDeactive(quotDt, false);
		 TreeSet<String> ktDesc = new TreeSet<String>();
		 
			 for(QuotMetal quotMetal :quotMetals){
				 ktDesc.add(quotMetal.getPurity().getId().toString());
			 }
			 
		 quotDt.setKtDesc(ktDesc.toString());
		 save(quotDt);
		 
				
		
			
		
		return "1";
	}

	@Override
	public String updateQltyDesc(Integer quotDtId) {
		QuotDt quotDt =findOne(quotDtId);
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		TreeSet<String> qltyDesc = new TreeSet<String>();
		for(QuotStnDt quotStnDt:quotStnDts){
			
			if(quotStnDt.getQuality()!=null) {
				qltyDesc.add(quotStnDt.getQuality().getId().toString());	
			}
			
		}
		
		quotDt.setQltyDesc(qltyDesc.toString());
		 save(quotDt);
		return "1";
	}

	@Override
	public QuotDt findByQuotMtAndDesignAndKtDescAndQltyDescAndDeactive(QuotMt quotMt, Design design, String ktDesc,
			String qltyDesc, Boolean deactive) {
		// TODO Auto-generated method stub
		return quotDtRepository.findByQuotMtAndDesignAndKtDescAndQltyDescAndDeactive(quotMt, design, ktDesc, qltyDesc,deactive);
	}

	@Override
	public String quotDtExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile,
			Principal principal, Integer mtId,Boolean netWtWithCompFlg) throws ParseException {
		// TODO Auto-generated method stub
		synchronized (this) {
			
			QuotMt quotMt = quotMtService.findOne(mtId);
			
			String retVal ="";
			
			
			try {
				if(!tempExcelFile.isEmpty()){
				
				List<OrderExcel> orderExcelList = new ArrayList<OrderExcel>();
			
				Boolean remarkFlg = false;	
				String remark = "";	
				
				    if (tempExcelFile.endsWith("xlsx")) {
				    	int i=1;
			
				    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				        XSSFSheet worksheet = workbook.getSheetAt(0);
				        
				    
				        while (i <= worksheet.getLastRowNum()) {
				        	 remark = "";	
							OrderExcel orderExcel = new OrderExcel();
							XSSFRow row = worksheet.getRow(i++);
							
							if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
								continue;
							}
							 
						
						
							
							//note : temporary list
							//remark is set in createdBy for temporary list
							//status is set in updatedBy for temporary list
							
						
						
													
							Design design = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString(),false);
							if(design == null){
								if(remark == ""){
									remark = "No such style exists ";
								}else {
									remark += ",No such style exists ";
								}
							}
							
							Purity purity =  purityService.findByName(row.getCell(1).toString());
							if(purity == null){
								if(remark == ""){
									remark = "No such purity exists ";
								}else {
									remark += ",No such purity exists ";
								}
								
								
							}
							
							Color color = colorService.findByName(row.getCell(2).toString());
							if(color == null){
								if(remark == ""){
									remark = "No such color exists ";
								}else {
									remark += ",No such color exists ";
								}
								
							}
							
					
							
							if((row.getCell(0) == null || row.getCell(0).toString().isEmpty() ) && (row.getCell(1)==null || row.getCell(1).toString().isEmpty()) 
									&& (row.getCell(2)==null || row.getCell(2).toString().isEmpty()) && (row.getCell(3)==null || row.getCell(3).toString().isEmpty()) 
									 &&  (row.getCell(13)==null || row.getCell(13).toString().isEmpty()) 
									&& (row.getCell(13)==null || row.getCell(13).toString().isEmpty())) {
								
								remark = "Mandatory fields can not be empty";
							}
							
							if (remark != "") {
								
								orderExcel.setStyleNo(row.getCell(0).toString() !=null ? row.getCell(0).toString() :"");
								orderExcel.setPurity(row.getCell(1).toString() !=null ? row.getCell(1).toString() :"");
								orderExcel.setColor(row.getCell(2).toString() !=null ? row.getCell(2).toString() :"");
								orderExcel.setQty(row.getCell(3).toString() !=null ? Double.parseDouble(row.getCell(3).toString()):0.0);
								orderExcel.setNetWt(row.getCell(4).toString() !=null ?Math.round(Double.parseDouble(row.getCell(4).toString())*1000.0)/1000.0:0.0);
								orderExcel.setProdSize(row.getCell(5).toString().equalsIgnoreCase("-") ? "" :row.getCell(5).toString());
								
								
								orderExcel.setDtRefNo(row.getCell(6).toString() !=null ? row.getCell(6).toString() :"");		
								orderExcel.setStamp(row.getCell(7).toString().equalsIgnoreCase("-") ? "" :row.getCell(7).toString());
								orderExcel.setItemRemark(row.getCell(8).toString().equalsIgnoreCase("-") ?"" : row.getCell(8).toString());
								
							//All Code change
								
								orderExcel.setOrdRefNo(row.getCell(9).toString().equalsIgnoreCase("-") ? "" :row.getCell(9).toString());
								orderExcel.setItem(row.getCell(10).toString().equalsIgnoreCase("-")  ? "" :row.getCell(10).toString());
								orderExcel.setShape(row.getCell(11).toString().equalsIgnoreCase("-") ? "" :row.getCell(11).toString());
								orderExcel.setQuality(row.getCell(12).toString().equalsIgnoreCase("-") ? "" :row.getCell(12).toString());
								orderExcel.setCarat(row.getCell(13).toString().equalsIgnoreCase("-") ? 0.0:Double.parseDouble(row.getCell(13).toString()));
								orderExcel.setRate(row.getCell(14).toString().equalsIgnoreCase("-")  ? 0.0 : Double.parseDouble(row.getCell(14).toString()));
								orderExcel.setAmount(row.getCell(15).toString().equalsIgnoreCase("-") ? 0.0 : Double.parseDouble(row.getCell(15).toString()));
								orderExcel.setStatusRemark(remark);
								orderExcelList.add(orderExcel);
								remarkFlg = true;
							}
													
						}
						
						workbook.close();
						
				    } else if (tempExcelFile.endsWith("xls")) {
				    	int i = 1;
						HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
						HSSFSheet worksheet = workbook.getSheetAt(0);
						
										
						
					
						while (i <= worksheet.getLastRowNum()) {
							
							 remark = "";	
							OrderExcel orderExcel = new OrderExcel();
							HSSFRow row = worksheet.getRow(i++);
				
							if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
								continue;
							}
							
							
									
							//note : temporary list
							//remark is set in createdBy for temporary list
							//status is set in updatedBy for temporary list
							
			
						
							Design design = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString(),false);
							if(design == null){
								if(remark == ""){
									remark = "No such style exists ";
								}else {
									remark += ",No such style exists ";
								}
							}
							
							Purity purity =  purityService.findByName(row.getCell(1).toString());
							if(purity == null){
								if(remark == ""){
									remark = "No such purity exists ";
								}else {
									remark += ",No such purity exists ";
								}
								
								
							}
							
							Color color = colorService.findByName(row.getCell(2).toString());
							if(color == null){
								if(remark == ""){
									remark = "No such color exists ";
								}else {
									remark += ",No such color exists ";
								}
								
							}
							
							
							if((row.getCell(0) == null || row.getCell(0).toString().isEmpty() ) && (row.getCell(1)==null || row.getCell(1).toString().isEmpty()) 
									&& (row.getCell(2)==null || row.getCell(2).toString().isEmpty()) && (row.getCell(3)==null || row.getCell(3).toString().isEmpty()) 
									 &&  (row.getCell(13)==null || row.getCell(13).toString().isEmpty()) 
									&& (row.getCell(13)==null || row.getCell(13).toString().isEmpty())) {
								
								remark = "Mandatory fields can not be empty";
							}
							
							if (remark != "") {
								
								orderExcel.setStyleNo(row.getCell(0).toString() !=null ? row.getCell(0).toString() :"");
								orderExcel.setPurity(row.getCell(1).toString() !=null ? row.getCell(1).toString() :"");
								orderExcel.setColor(row.getCell(2).toString() !=null ? row.getCell(2).toString() :"");
								orderExcel.setQty(row.getCell(3).toString() !=null ? Double.parseDouble(row.getCell(3).toString()):0.0);
								orderExcel.setNetWt(row.getCell(4).toString() !=null ?Math.round(Double.parseDouble(row.getCell(4).toString())*1000.0)/1000.0:0.0);
								orderExcel.setProdSize(row.getCell(5).toString().equalsIgnoreCase("-") ? "" :row.getCell(5).toString());
								
								
								orderExcel.setDtRefNo(row.getCell(6).toString() !=null ? row.getCell(6).toString() :"");		
								orderExcel.setStamp(row.getCell(7).toString().equalsIgnoreCase("-") ? "" :row.getCell(7).toString());
								orderExcel.setItemRemark(row.getCell(8).toString().equalsIgnoreCase("-") ?"" : row.getCell(8).toString());
								
							//All Code change
								
								orderExcel.setOrdRefNo(row.getCell(9).toString().equalsIgnoreCase("-") ? "" :row.getCell(9).toString());
								orderExcel.setItem(row.getCell(10).toString().equalsIgnoreCase("-")  ? "" :row.getCell(10).toString());
								orderExcel.setShape(row.getCell(11).toString().equalsIgnoreCase("-") ? "" :row.getCell(11).toString());
								orderExcel.setQuality(row.getCell(12).toString().equalsIgnoreCase("-") ? "" :row.getCell(12).toString());
								orderExcel.setCarat(row.getCell(13).toString().equalsIgnoreCase("-") ? 0.0:Double.parseDouble(row.getCell(13).toString()));
								orderExcel.setRate(row.getCell(14).toString().equalsIgnoreCase("-")  ? 0.0 : Double.parseDouble(row.getCell(14).toString()));
								orderExcel.setAmount(row.getCell(15).toString().equalsIgnoreCase("-") ? 0.0 : Double.parseDouble(row.getCell(15).toString()));
								orderExcel.setStatusRemark(remark);
								orderExcelList.add(orderExcel);
								remarkFlg = true;
							}
							
							
						}

						workbook.close();
						
				    } else {
				        throw new IllegalArgumentException("The specified file is not Excel file");
				    }
				    
				    
				    // Create Order
				    
				    if (remarkFlg) {
				    	 session.setAttribute("qoutDtExcelSessionList", orderExcelList);  
				    	 retVal="yes";
				    	
				    }else {
				    	
				    	
				    	   if (tempExcelFile.endsWith("xlsx")) {
						    	int i=1;
					
						    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
						        XSSFSheet worksheet = workbook.getSheetAt(0);
						     
						      
						    	Integer dtId = 0;
						        
						        while (i <= worksheet.getLastRowNum()) {
									
									XSSFRow row = worksheet.getRow(i++);
									
									if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
										continue;
									}
									 
								
									//note : temporary list
									//remark is set in createdBy for temporary list
									//status is set in updatedBy for temporary list
																						
									Design design = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString(),false);
									
									Purity purity =  purityService.findByName(row.getCell(1).toString());
									
									Color color = colorService.findByName(row.getCell(2).toString());
									
									Shape shape = null;
									if(row.getCell(11).toString().equalsIgnoreCase("-")) {
										shape = null;
									}else {
									 shape = shapeService.findByName(row.getCell(11).toString());	
									}
									
									Quality quality = null;
									if(row.getCell(12).toString().equalsIgnoreCase("-")) {
										quality = null;
									}else {
										if(shape != null) {
											quality  = qualityService.findByShapeAndName(shape,row.getCell(12).toString());	
										}
											
									}
									
									
									ProductSize productSize =  null;
									if(row.getCell(5).toString().equalsIgnoreCase("-")) {
										productSize= null;
									}else {
										 productSize = productSizeService.findByName(row.getCell(5).toString());
										
									}
									
									
									
									ClientStamp clientStamp =  null;
									String clientStampNm = null;
									if(row.getCell(7).toString().equalsIgnoreCase("-")) {
										if(quotMt.getParty() != null ) {
											clientStamp = clientStampService.findByPartyAndPurityAndDeactive(quotMt.getParty(), purity, false);	
											
											 if(clientStamp != null) {
												  clientStampNm = clientStamp.getStampNm(); 
											 }
										}
										 
										
										
									}else {
										clientStampNm = row.getCell(7).toString();
									}
										
											
											
											QuotDt quotDt =  new QuotDt();
											quotDt.setQuotMt(quotMt);
											quotDt.setPurity(purity);
											quotDt.setColor(color);
											quotDt.setDesign(design);
											quotDt.setPcs(Double.parseDouble(row.getCell(3).toString()));
											quotDt.setStampInst(clientStampNm);
										//	quotDt.setItem(row.getCell(17).toString().equalsIgnoreCase("-") ? "" :row.getCell(17).toString());
										//	quotDt.setOrdRef(row.getCell(16).toString().equalsIgnoreCase("-")  ? "" :row.getCell(16).toString());
											quotDt.setRemark(row.getCell(8).toString().equalsIgnoreCase("-")  ? "" :row.getCell(8).toString());
											quotDt.setRefNo(row.getCell(6).toString().equalsIgnoreCase("-")  ? "" :row.getCell(6).toString());
											quotDt.setDesignRemark(design.getRemarks());
											
											if(row.getCell(4).toString().equalsIgnoreCase("0") || row.getCell(4).toString().equalsIgnoreCase("0.0")) {
												quotDt.setNetWt(Math.round((design.getWaxWt() * purity.getWaxWtConv())*1000.0)/1000.0);
											}else {
												quotDt.setNetWt(Math.round(Double.parseDouble(row.getCell(4).toString())*1000.0)/1000.0);	
											}
											
											
											quotDt.setCreatedDate(new Date());
											quotDt.setCreatedBy(principal.getName());
										//	quotDt.setSrNo((int)(Double.parseDouble(row.getCell(6).toString())));
											quotDt.setProductSize(productSize);
											
											
											save(quotDt);
											
									//		srNo = orderDt.getSrNo();
											dtId = quotDt.getId();
											
											List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
											quotMetalService.addQuotMetalFromDesign(designMetals, quotMt, quotDt, principal);
											
											List<DesignStone> designStones = designStoneService.findByDesign(design); 
											quotStnDtService.setQuotStnDt(designStones, quotMt, quotDt,principal);
											
											List<DesignComponent> designComponents = designComponentService.findByDesign(design);
											quotCompDtService.setQuotCompDt(designComponents, quotMt, quotDt, principal);
											
											Double totalReqCarat =0.0;
											for(DesignStone designStone : designStones) {
												totalReqCarat += designStone.getCarat();
											}
											
											
									//		quotDt.setReqCarat(Math.round((totalReqCarat)*1000.0)/1000.0);
									//		save(quotDt);
										
										
										
										
									/*
									 * List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt,
									 * false);
									 * 
									 * Double totqltyCarat = 0.0;
									 * 
									 * if(shape !=null) {
									 * 
									 * for(QuotStnDt quotStnDt:quotStnDts){
									 * 
									 * if(quotStnDt.getShape().equals(shape)){
									 * 
									 * if(quotStnDt.getShape().getName().equalsIgnoreCase("ROUND")) {
									 * 
									 * 
									 * 
									 * if(row.getCell(12).toString().contains("S/C") &&
									 * Double.parseDouble(quotStnDt.getSize())<0.90){
									 * 
									 * //orderStnDt.setCarat(Double.parseDouble(row.getCell(20).toString()));
									 * quotStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
									 * quotStnDt.setQuality(quality);
									 * 
									 * totqltyCarat +=quotStnDt.getCarat();
									 * 
									 * }else if(row.getCell(12).toString().contains("D/C") &&
									 * Double.parseDouble(quotStnDt.getSize())>=0.90) {
									 * 
									 * quotStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
									 * quotStnDt.setQuality(quality); totqltyCarat +=quotStnDt.getCarat();
									 * 
									 * } else if(!row.getCell(12).toString().contains("D/C") &&
									 * !row.getCell(12).toString().contains("S/C")){
									 * quotStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
									 * quotStnDt.setQuality(quality); totqltyCarat +=quotStnDt.getCarat(); }
									 * 
									 * 
									 * 
									 * 
									 * 
									 * }else {
									 * 
									 * 
									 * quotStnDt.setStnRate(Double.parseDouble(row.getCell(7).toString()));
									 * quotStnDt.setQuality(quality); totqltyCarat +=quotStnDt.getCarat();
									 * 
									 * 
									 * }
									 * 
									 * }
									 * 
									 * }
									 * 
									 * }
									 */
							

										/*
										 * if(quality != null) {
										 * 
										 * @SuppressWarnings("unchecked") TypedQuery<QuotStnDt> query =
										 * (TypedQuery<QuotStnDt>) entityManager
										 * .createNativeQuery("select * from quotstndt " + " where dtid =" + dtId +
										 * " and qualityid = "+quality.getId()+" ORDER BY carat DESC LIMIT 1 " ,
										 * QuotStnDt.class);
										 * 
										 * List<QuotStnDt> quotStnDts2 = query.getResultList();
										 * 
										 * Double perPcsDiawt = Double.parseDouble(row.getCell(13).toString()) /
										 * Double.parseDouble(row.getCell(3).toString());
										 * 
										 * Double vCaratDiff =Math.round((perPcsDiawt-totqltyCarat)*1000.0)/1000.0;
										 * 
										 * 
										 * for(QuotStnDt quotStnDt : quotStnDts2) {
										 * quotStnDt.setCarat(Math.round((quotStnDt.getCarat()+vCaratDiff)*1000.0)/1000.
										 * 0); quotStnDtService.save(quotStnDt);
										 * 
										 * }
										 * 
										 * }
										 */	  
											  List<QuotStnDt> quotStnDts3 = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
												Double totStnCarat = 0.0;
											  for(QuotStnDt quotStnDt : quotStnDts3) {
												  totStnCarat +=quotStnDt.getCarat();
											  }
											  
										
											  quotDt.setGrossWt(Math.round((quotDt.getNetWt()+(totStnCarat/5))*100.0)/100.0);
											  save(quotDt);
										
									//	orderDtService.updateKtDesc(orderDt.getId());
									//	orderDtService.updateQltyDesc(orderDt.getId());		
								
								
								}

								
								
								workbook.close();
								
						    } else if (tempExcelFile.endsWith("xls")) {
						    	int i = 1;
								HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
								HSSFSheet worksheet = workbook.getSheetAt(0);
								
												
								Integer dtId = 0;
							    	
							        
									while (i <= worksheet.getLastRowNum()) {
										HSSFRow row = worksheet.getRow(i++);
							
										if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
											continue;
										}
										 
									
										//note : temporary list
										//remark is set in createdBy for temporary list
										//status is set in updatedBy for temporary list
																							
										Design design = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString(),false);
										
										Purity purity =  purityService.findByName(row.getCell(1).toString());
										
										Color color = colorService.findByName(row.getCell(2).toString());
										
										Shape shape = null;
										if(row.getCell(11).toString().equalsIgnoreCase("-")) {
											shape = null;
										}else {
										 shape = shapeService.findByName(row.getCell(11).toString());	
										}
										
										Quality quality = null;
										if(row.getCell(12).toString().equalsIgnoreCase("-")) {
											quality = null;
										}else {
											if(shape != null) {
												quality  = qualityService.findByShapeAndName(shape,row.getCell(12).toString());	
											}
												
										}
										
										
										ProductSize productSize =  null;
										if(row.getCell(5).toString().equalsIgnoreCase("-")) {
											productSize= null;
										}else {
											 productSize = productSizeService.findByName(row.getCell(5).toString());
											
										}
										
										
										
										ClientStamp clientStamp =  null;
										String clientStampNm = null;
										if(row.getCell(7).toString().equalsIgnoreCase("-")) {
											if(quotMt.getParty() != null ) {
												clientStamp = clientStampService.findByPartyAndPurityAndDeactive(quotMt.getParty(), purity, false);	
												
												 if(clientStamp != null) {
													  clientStampNm = clientStamp.getStampNm(); 
												 }
											}
											 
											
											
										}else {
											clientStampNm = row.getCell(7).toString();
										}
											
												
												
												QuotDt quotDt =  new QuotDt();
												quotDt.setQuotMt(quotMt);
												quotDt.setPurity(purity);
												quotDt.setColor(color);
												quotDt.setDesign(design);
												quotDt.setPcs(Double.parseDouble(row.getCell(3).toString()));
												quotDt.setStampInst(clientStampNm);
											//	quotDt.setItem(row.getCell(17).toString().equalsIgnoreCase("-") ? "" :row.getCell(17).toString());
											//	quotDt.setOrdRef(row.getCell(16).toString().equalsIgnoreCase("-")  ? "" :row.getCell(16).toString());
												quotDt.setRemark(row.getCell(8).toString().equalsIgnoreCase("-")  ? "" :row.getCell(8).toString());
												quotDt.setRefNo(row.getCell(6).toString().equalsIgnoreCase("-")  ? "" :row.getCell(6).toString());
												quotDt.setDesignRemark(design.getRemarks());
												
												if(row.getCell(4).toString().equalsIgnoreCase("0") || row.getCell(4).toString().equalsIgnoreCase("0.0")) {
													quotDt.setNetWt(Math.round((design.getWaxWt() * purity.getWaxWtConv())*1000.0)/1000.0);
												}else {
													quotDt.setNetWt(Math.round(Double.parseDouble(row.getCell(4).toString())*1000.0)/1000.0);	
												}
												
												
												quotDt.setCreatedDate(new Date());
												quotDt.setCreatedBy(principal.getName());
											//	quotDt.setSrNo((int)(Double.parseDouble(row.getCell(6).toString())));
												quotDt.setProductSize(productSize);
												
												
												save(quotDt);
												
										//		srNo = orderDt.getSrNo();
												dtId = quotDt.getId();
												
												List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
												quotMetalService.addQuotMetalFromDesign(designMetals, quotMt, quotDt, principal);
												
												List<DesignStone> designStones = designStoneService.findByDesign(design); 
												quotStnDtService.setQuotStnDt(designStones, quotMt, quotDt,principal);
												
												List<DesignComponent> designComponents = designComponentService.findByDesign(design);
												quotCompDtService.setQuotCompDt(designComponents, quotMt, quotDt, principal);
												
												Double totalReqCarat =0.0;
												for(DesignStone designStone : designStones) {
													totalReqCarat += designStone.getCarat();
												}
												
												
										//		quotDt.setReqCarat(Math.round((totalReqCarat)*1000.0)/1000.0);
										//		save(quotDt);
											
											
											
											
										/*
										 * List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt,
										 * false);
										 * 
										 * Double totqltyCarat = 0.0;
										 * 
										 * if(shape !=null) {
										 * 
										 * for(QuotStnDt quotStnDt:quotStnDts){
										 * 
										 * if(quotStnDt.getShape().equals(shape)){
										 * 
										 * if(quotStnDt.getShape().getName().equalsIgnoreCase("ROUND")) {
										 * 
										 * 
										 * 
										 * if(row.getCell(12).toString().contains("S/C") &&
										 * Double.parseDouble(quotStnDt.getSize())<0.90){
										 * 
										 * //orderStnDt.setCarat(Double.parseDouble(row.getCell(20).toString()));
										 * quotStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * quotStnDt.setQuality(quality);
										 * 
										 * totqltyCarat +=quotStnDt.getCarat();
										 * 
										 * }else if(row.getCell(12).toString().contains("D/C") &&
										 * Double.parseDouble(quotStnDt.getSize())>=0.90) {
										 * 
										 * quotStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * quotStnDt.setQuality(quality); totqltyCarat +=quotStnDt.getCarat(); } else
										 * if(!row.getCell(12).toString().contains("D/C") &&
										 * !row.getCell(12).toString().contains("S/C")){
										 * quotStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * quotStnDt.setQuality(quality); totqltyCarat +=quotStnDt.getCarat(); }
										 * 
										 * 
										 * }else { quotStnDt.setStnRate(Double.parseDouble(row.getCell(7).toString()));
										 * quotStnDt.setQuality(quality); totqltyCarat +=quotStnDt.getCarat(); }
										 * 
										 * }
										 * 
										 * } }
										 */
											
								
										/*
										 * if(quality != null) {
										 * 
										 * @SuppressWarnings("unchecked") TypedQuery<QuotStnDt> query =
										 * (TypedQuery<QuotStnDt>) entityManager
										 * .createNativeQuery("select * from quotstndt " + " where dtid =" + dtId +
										 * " and qualityid = "+quality.getId()+" ORDER BY carat DESC LIMIT 1 " ,
										 * QuotStnDt.class);
										 * 
										 * List<QuotStnDt> quotStnDts2 = query.getResultList();
										 * 
										 * Double perPcsDiawt = Double.parseDouble(row.getCell(13).toString()) /
										 * Double.parseDouble(row.getCell(3).toString());
										 * 
										 * Double vCaratDiff =Math.round((perPcsDiawt-totqltyCarat)*1000.0)/1000.0;
										 * 
										 * 
										 * for(QuotStnDt quotStnDt : quotStnDts2) {
										 * quotStnDt.setCarat(Math.round((quotStnDt.getCarat()+vCaratDiff)*1000.0)/1000.
										 * 0); quotStnDtService.save(quotStnDt);
										 * 
										 * }
										 * 
										 * }
										 */  
												  List<QuotStnDt> quotStnDts3 = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
													Double totStnCarat = 0.0;
												  for(QuotStnDt quotStnDt : quotStnDts3) {
													  totStnCarat +=quotStnDt.getCarat();
												  }
												  
											
												  quotDt.setGrossWt(Math.round((quotDt.getNetWt()+(totStnCarat/5))*100.0)/100.0);
												  save(quotDt);
											
										//	orderDtService.updateKtDesc(orderDt.getId());
										//	orderDtService.updateQltyDesc(orderDt.getId());		
									
									
									}

								workbook.close();
								
						    }
				    	
				    	retVal="1";
				    	   
				    }
				    
				    
				    
				    
				    
				   
					}
				}catch (IOException e) {
					
					e.printStackTrace();
				}
			
			
			
			return retVal;
			
			
		}
	}

	
}

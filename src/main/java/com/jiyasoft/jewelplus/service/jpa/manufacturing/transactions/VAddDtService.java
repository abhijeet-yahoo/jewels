package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QVAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QVAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QVAddStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QVaddMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddMetalDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalInvService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVaddMetalDtService;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class VAddDtService implements IVAddDtService {

	
	@Autowired
	private IVAddDtRepository vAddDtRepository;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private ICostingMtService costingtMtService;
	
	@Autowired
	private ICostStnDtService costStnDtService;
	
	@Autowired
	private ICostCompDtService costCompDtService;
	
	@Autowired
	private ICostMetalDtService costMetalDtService;
	
	@Autowired
	private IVAddStnDtService vAddStnDtService  ;
	
	@Autowired
	private IVAddCompDtService vAddCompDtService;
	
	@Autowired
	private IVaddMetalDtService vaddMetalDtService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IVAddMetalRateService addMetalRateService;
	
	
	@Autowired
	private ICostStnDtItemService costStnDtItemService;
	
	@Autowired
	private ICostCompDtItemService costCompDtItemService;
	
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private ICostMetalDtItemService costMetalDtItemService;
	
		
	@Autowired
	private IVAddMetalAdjService vaddMetalAdjService;
	

	@Autowired
	private IVAddMetalInvService vaddMetalInvService;
	
	
	@Override
	public void save(VAddDt vAddDt) {
		vAddDtRepository.save(vAddDt);
	}

	@Override
	public void delete(int id) {
		VAddDt vAddDt = vAddDtRepository.findOne(id);
		vAddDt.setDeactive(true);
		vAddDt.setDeactiveDt(new java.util.Date());
		vAddDtRepository.save(vAddDt);
		
	}

	@Override
	public VAddDt findOne(int id) {
		return vAddDtRepository.findOne(id);
	}

	@Override
	public List<VAddDt> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		return vAddDtRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public Long count() {
		return vAddDtRepository.count();
	}

	
	@Override
	public void deleteAll(CostingMt costingMt) {
		
		QVaddMetalDt qVaddMetalDt = QVaddMetalDt.vaddMetalDt;
		new JPADeleteClause(entityManager, qVaddMetalDt).where(qVaddMetalDt.costingMt.id.eq(costingMt.getId())).execute();
		
		QVAddStnDt qVAddStnDt = QVAddStnDt.vAddStnDt;
		new JPADeleteClause(entityManager, qVAddStnDt).where(qVAddStnDt.costingMt.id.eq(costingMt.getId())).execute();
		
		QVAddCompDt qVAddCompDt = QVAddCompDt.vAddCompDt;
		new JPADeleteClause(entityManager, qVAddCompDt).where(qVAddCompDt.costingMt.id.eq(costingMt.getId())).execute();
		
		QVAddDt qVAddDt = QVAddDt.vAddDt;
		new JPADeleteClause(entityManager, qVAddDt).where(qVAddDt.costingMt.id.eq(costingMt.getId())).execute();
		
	}

	@Override
	public VAddDt findByUniqueId(Long uniqueId) {
		return vAddDtRepository.findByUniqueId(uniqueId);
	}
	
	@Override
	public Double calulateVAddDtTotFob(Integer costMt) {
		
		QVAddDt qVaddDt = QVAddDt.vAddDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Double> totFob = null;
		
		totFob = query.from(qVaddDt).
				where(qVaddDt.costingMt.id.eq(costMt).and(qVaddDt.deactive.eq(false))).list(qVaddDt.fob.sum());
		
		Double retVal = 0.0;
		
		for(Double tFob : totFob){
			retVal = tFob;
		}
		
		
		if(retVal == null){
			retVal = 0.0;
		}
		
		
		return retVal;
	}
	
	
	
	@Override
	public void updateWastagePerc(Integer costMtId,Double pLossPerc) {
		QVAddDt qVAddDt = QVAddDt.vAddDt;
		JPAUpdateClause clause = new JPAUpdateClause(entityManager, qVAddDt);
		clause.set(qVAddDt.lossPercent, pLossPerc).where(qVAddDt.costingMt.id.eq(costMtId)).execute();
	}

	@Override
	public String loadValuAddition(String invNo, Principal principal) {
		
		String retVal = "-1";
		
		CostingMt costingMtNew = costingtMtService.findByInvNoAndDeactive(invNo, false);
		
		List<VAddDt> vAddDts = findByCostingMtAndDeactive(costingMtNew, false);
		
		if(vAddDts.size() > 0){
			deleteAll(costingMtNew);
		}
		
		
		List<CostingDt> costingDts = costingDtService.findByCostingMtAndDeactive(costingMtNew, false);
		
		for(CostingDt costingDt:costingDts){
			
				
			//entry for vAddDt
			
			VAddDt vAddDt = new VAddDt();
			
			vAddDt.setCostingMt(costingMtNew);
			vAddDt.setDesign(costingDt.getDesign());
			vAddDt.setPurity(costingDt.getPurity());
			vAddDt.setColor(costingDt.getColor());
			vAddDt.setOrderDt(costingDt.getOrderDt());
			vAddDt.setParty(costingDt.getParty() != null ? costingDt.getParty() : null );
			vAddDt.setGrossWt(costingDt.getGrossWt());
			vAddDt.setNetWt(costingDt.getNetWt());
			vAddDt.setFob(costingDt.getFob());
			vAddDt.setFinalPrice(costingDt.getFinalPrice());
			vAddDt.setPcs(costingDt.getPcs());
			vAddDt.setHdlgValue(costingDt.getHdlgValue());
			vAddDt.setSetValue(costingDt.getSetValue());
			
			//vAddDt.setWastagePercent(costingMtNew.getvAddWastage());
			//vAddDt.setFobPerPiece(Double.parseDouble(df2.format(vFob/vPcs)));
			vAddDt.setStoneValue(costingDt.getStoneValue());
			vAddDt.setMetalValue(0.0);
			vAddDt.setLossValue(costingDt.getLossValue());
			vAddDt.setrLock(false);
			vAddDt.setMetalRate(0.0);
			vAddDt.setPurityConv(costingDt.getPurityConv());
			vAddDt.setLossWt(costingDt.getLossWt());
			vAddDt.setOther(costingDt.getOther());
			vAddDt.setClientPurityConv(costingDt.getClientPurityConv());
			vAddDt.setItemNo(costingDt.getItemNo());
			vAddDt.setCreatedBy(principal.getName());
			vAddDt.setCreatedDate(new java.util.Date());
			vAddDt.setDeactive(costingDt.getDeactive());
			vAddDt.setCompValue(costingDt.getCompValue());
						
			save(vAddDt);
			
			
			
			
			List<CostMetalDt>costMetalDts =costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
			for(CostMetalDt costMetalDt : costMetalDts){
				VaddMetalDt vaddMetalDt = new VaddMetalDt();
				vaddMetalDt.setBagMt(costMetalDt.getBagMt());
				vaddMetalDt.setColor(costMetalDt.getColor());
				vaddMetalDt.setCostingMt(costingMtNew);
				vaddMetalDt.setCreateDate(new Date());
				vaddMetalDt.setCreatedBy(principal.getName());
				vaddMetalDt.setDepartment(costMetalDt.getDepartment());
				vaddMetalDt.setLossPerc(costMetalDt.getLossPerc());
				vaddMetalDt.setMainMetal(costMetalDt.getMainMetal());
				vaddMetalDt.setMetalPcs(costMetalDt.getMetalPcs());
				vaddMetalDt.setMetalWeight(costMetalDt.getMetalWeight());
				vaddMetalDt.setPartNm(costMetalDt.getPartNm());
				vaddMetalDt.setPurity(costMetalDt.getPurity());
				vaddMetalDt.setvAddDt(vAddDt);
				
				
				vaddMetalDtService.save(vaddMetalDt);
				
				
				
			}
			
			
				
				//entry for vAddStnDt
				
				/*List<CostStnDt> costStnDts = costStnDtService.findByItemNoAndOrderDtAndCostingMtAndDeactive(costingDt.getItemNo(), costingDt.getOrderDt(), costingDt.getCostingMt(),false);*/
			
			
			List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
				
				
				for(CostStnDt costStnDt:costStnDts){
					
					VAddStnDt vAddStnDt = new VAddStnDt();
					
					vAddStnDt.setCostingMt(costStnDt.getCostingMt());
					vAddStnDt.setvAddDt(vAddDt);
					vAddStnDt.setShape(costStnDt.getShape());
					vAddStnDt.setStoneType(costStnDt.getStoneType());
					vAddStnDt.setQuality(costStnDt.getQuality());
					vAddStnDt.setSizeGroup(costStnDt.getSizeGroup());
					vAddStnDt.setSetting(costStnDt.getSetting());
					vAddStnDt.setSettingType(costStnDt.getSettingType());
					vAddStnDt.setOrderDt(costStnDt.getOrderDt());
					vAddStnDt.setStoneInwardDt(costStnDt.getStoneInwardDt());
					vAddStnDt.setSeqNo(costStnDt.getSeqNo());
					vAddStnDt.setBagSrNo(costStnDt.getBagSrNo());
					vAddStnDt.setSize(costStnDt.getSize());
					vAddStnDt.setSieve(costStnDt.getSieve());
					vAddStnDt.setStone(costStnDt.getStone());
					vAddStnDt.setCarat(costStnDt.getCarat());
					vAddStnDt.setTagWt(costStnDt.getTagWt());
					vAddStnDt.setStnRate(costStnDt.getStnRate());
					vAddStnDt.setStoneValue(costStnDt.getStoneValue());
					vAddStnDt.setSetRate(costStnDt.getSetRate());
					vAddStnDt.setSetValue(costStnDt.getSetValue());
					vAddStnDt.setHandlingRate(costStnDt.getHandlingRate());
					vAddStnDt.setHandlingValue(costStnDt.getHandlingValue());
					vAddStnDt.setManualCaratRate(costStnDt.getManualCaratRate());
					vAddStnDt.setManualSetRate(costStnDt.getManualSetRate());
					vAddStnDt.setPerStoneWt(costStnDt.getPerStoneWt());
					vAddStnDt.setPerPcsRateFlg(costStnDt.getPerPcsRateFlg());
					vAddStnDt.setCaratRate(costStnDt.getCaratRate());
					vAddStnDt.setCenterStone(costStnDt.getCenterStone());
					vAddStnDt.setrLock(false);
					vAddStnDt.setItemNo(costStnDt.getItemNo());
					vAddStnDt.setCreatedBy(principal.getName());
					vAddStnDt.setCreatedDate(new java.util.Date());
					
								
					
					if(costStnDt.getStoneInwardDt() != null){
						if(costStnDt.getStoneInwardDt().getStoneInwardMt().getInwardType().getName().equalsIgnoreCase("Loan")){
							vAddStnDt.setLoanCarat(costStnDt.getCarat());
							vAddStnDt.setLoanValue(costStnDt.getStoneValue());
						}
					}
					
					vAddStnDtService.save(vAddStnDt);
					
				}
					
				//-------------------------------//---------------------------//
				
				
				// entry for VAddCompDt
				
				//List<CostCompDt> costCompDts2 = costCompDtService.getCostCompDtList(costingDt.getItemNo(), costingDt.getOrderDt(), costingDt.getCostingMt());
				
				List<CostCompDt>costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
				
				for(CostCompDt costCompDtNew:costCompDts){
					
					VAddCompDt vAddCompDt = new VAddCompDt();
					
					//Double vCompWt = 0.0;
					//Double vCompValue = 0.0;
					
					/*for(CostCompDt costCompDtt:costCompDts){
						
						vCompWt 	+= costCompDtt.getMetalWt();
						vCompValue	+= costCompDtt.getCompValue();
					}*/
			
					vAddCompDt.setvAddDt(vAddDt);
					vAddCompDt.setCostingMt(costCompDtNew.getCostingMt());
					vAddCompDt.setComponent(costCompDtNew.getComponent());
					vAddCompDt.setPurity(costCompDtNew.getPurity());
					vAddCompDt.setColor(costCompDtNew.getColor());
					vAddCompDt.setOrderDt(costCompDtNew.getOrderDt());
					vAddCompDt.setPurityConv(costCompDtNew.getPurityConv());
					vAddCompDt.setClientPurityConv(costCompDtNew.getClientPurityConv());
					vAddCompDt.setCompRate(costCompDtNew.getCompRate());
					vAddCompDt.setItemNo(costCompDtNew.getItemNo());
					vAddCompDt.setrLock(false);
					vAddCompDt.setCompPcs(costCompDtNew.getCompPcs());
					vAddCompDt.setMetalWt(costCompDtNew.getMetalWt());
					vAddCompDt.setCompValue(costCompDtNew.getCompValue());
					vAddCompDt.setCreatedBy(principal.getName());
					vAddCompDt.setCreatedDate(new java.util.Date());
					
					vAddCompDtService.save(vAddCompDt);
					
				}
				
				
				//-------------------------------//---------------------------//	
				
				
			
		}  //main for loop ends here
		
		retVal="1";
		
		return retVal;
	}

	@Override
	public Page<VAddDt> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Integer mtId) {
		// TODO Auto-generated method stub
		QVAddDt qvAddDt = QVAddDt.vAddDt;
		BooleanExpression expression = qvAddDt.deactive.eq(false).and(qvAddDt.costingMt.id.eq(mtId));
	
		if(search !=null && search !=""){
					
			expression = qvAddDt.deactive.eq(false).and(qvAddDt.costingMt.id.eq(mtId)).and(qvAddDt.design.mainStyleNo.like(search+"%").or(qvAddDt.orderDt.orderMt.refNo.like(search+"%")));
			
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if(sort.equalsIgnoreCase("style")){
			sort = "design";
		}

		Page<VAddDt> vAddDtList = (Page<VAddDt>) vAddDtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return vAddDtList;
	}

	@Override
	public String applyRate(VAddDt vAddDt, Principal principal) {

		String retVal="-1";
		
		applyMetal(vAddDt);
		
		applyWastage(vAddDt);
		updateValueAddition(vAddDt);
	
		retVal="1";
		return retVal;
	}

	@Override
	public String applyMetal(VAddDt vAddDt) {
	
		CostingMt costingMt =vAddDt.getCostingMt();
		
		
		List<VaddMetalDt> vaddMetalDts = vaddMetalDtService.findByVaddDtAndDeactive(vAddDt, false);
				
			for(VaddMetalDt vaddMetalDt:vaddMetalDts){
				
				if(vaddMetalDt.getrLock().equals(false)){
					if (vaddMetalDt.getPurity() != null) {
						Purity purity = purityService.findOne(vaddMetalDt.getPurity().getId());
						
						VAddMetalInv vAddMetalInv = vaddMetalInvService.findByMetalAndAdjustedAndCostingMt(purity.getMetal(), true, costingMt);
						if(vAddMetalInv !=null){
							
							VAddMetalAdj vAddMetalAdj = vaddMetalAdjService.findByCostingMtAndVAddMetalInv(costingMt, vAddMetalInv);
							if(vAddMetalAdj!=null){
								
								vaddMetalDt.setMetalRate(vAddMetalAdj.getMetalPurchaseDt().getRate());
								vaddMetalDtService.save(vaddMetalDt);
								
								
								
							}
						}else{
							
							
							VAddMetalRate addMetalRate = addMetalRateService.findByCostingMtAndDeactiveAndMetal(costingMt, false, purity.getMetal());
							
							
							
							if(addMetalRate!=null){
												
								vaddMetalDt.setMetalRate(addMetalRate.getRate());
								vaddMetalDtService.save(vaddMetalDt);
							}
							
						}
						
						
						
																	
					

					}

				}
				
				
		

			}
			
			
			List<VAddCompDt> vAddCompDts = vAddCompDtService.findByVAddDtAndDeactive(vAddDt, false);
			
			for(VAddCompDt vAddCompDt :vAddCompDts){
				
				if(vAddCompDt.getrLock().equals(false)){
					if (vAddCompDt.getPurity() != null) {
						Purity purity = purityService.findOne(vAddCompDt.getPurity().getId());
						
						VAddMetalInv vAddMetalInv = vaddMetalInvService.findByMetalAndAdjustedAndCostingMt(purity.getMetal(), true, costingMt);
						
							if(vAddMetalInv !=null){
							
							VAddMetalAdj vAddMetalAdj = vaddMetalAdjService.findByCostingMtAndVAddMetalInv(costingMt, vAddMetalInv);
							if(vAddMetalAdj!=null){
								
								vAddCompDt.setMetalRate(vAddMetalAdj.getMetalPurchaseDt().getRate());
								vAddCompDtService.save(vAddCompDt);
							}
						}else{
							
							VAddMetalRate addMetalRate = addMetalRateService.findByCostingMtAndDeactiveAndMetal(costingMt, false, purity.getMetal());
							
							
							
							if(addMetalRate!=null){
								
								vAddCompDt.setMetalRate(addMetalRate.getRate());
								vAddCompDtService.save(vAddCompDt);
								
							
								
								
							}
							
						}
						
																	
			

					}

				}
				
			
				
			}
			
			
			
			
		

		
		
		
		
		
		return "";
	}
	
	
	
	
	



	@Override
	public String loadValuAdditionFl(String invNo, Principal principal) {

		String retVal = "-1";
		
		CostingMt costingMtNew = costingtMtService.findByInvNoAndDeactive(invNo, false);
		
		deleteAll(costingMtNew);
		
		
		
		try {
		
			List<CostingDtItem> costingDtItems = costingDtItemService.findByCostingMtAndDeactive(costingMtNew, false);
			
			for(CostingDtItem costingDtItem:costingDtItems){
							
				VAddDt vAddDt = new VAddDt();
				
				vAddDt.setCostingMt(costingMtNew);
				vAddDt.setDesign(costingDtItem.getDesign());
				vAddDt.setPurity(costingDtItem.getPurity());
				vAddDt.setColor(costingDtItem.getColor());
				vAddDt.setOrderDt(costingDtItem.getOrderDt());
				vAddDt.setParty(costingDtItem.getParty() != null ? costingDtItem.getParty() : null );
				vAddDt.setGrossWt(costingDtItem.getGrossWt());
				vAddDt.setNetWt(costingDtItem.getNetWt());
				vAddDt.setFob(costingDtItem.getFob());
				vAddDt.setFinalPrice(costingDtItem.getFinalPrice());
				vAddDt.setFinalPricePerPcs(costingDtItem.getPerPcFinalPrice());
				vAddDt.setPcs(costingDtItem.getPcs());
				vAddDt.setHdlgValue(costingDtItem.getHdlgValue());
				vAddDt.setSetValue(costingDtItem.getSetValue());
				vAddDt.setStoneValue(costingDtItem.getStoneValue());
				vAddDt.setPurityConv(costingDtItem.getPurityConv());
				vAddDt.setClientPurityConv(costingDtItem.getClientPurityConv());
				vAddDt.setItemNo(costingDtItem.getItemNo());
				vAddDt.setCreatedBy(principal.getName());
				vAddDt.setCreatedDate(new java.util.Date());
				vAddDt.setCompValue(costingDtItem.getCompValue());
				save(vAddDt);
				
				
				
				
				List<CostMetalDtItem>costMetalDtItems =costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
				for(CostMetalDtItem costMetalDtItem : costMetalDtItems){
					VaddMetalDt vaddMetalDt = new VaddMetalDt();

					vaddMetalDt.setColor(costMetalDtItem.getColor());
					vaddMetalDt.setCostingMt(costingMtNew);
					vaddMetalDt.setCreateDate(new Date());
					vaddMetalDt.setCreatedBy(principal.getName());
					vaddMetalDt.setMainMetal(costMetalDtItem.getMainMetal());
					vaddMetalDt.setMetalPcs(costMetalDtItem.getMetalPcs());
					vaddMetalDt.setMetalWeight(costMetalDtItem.getMetalWeight());
					vaddMetalDt.setPartNm(costMetalDtItem.getPartNm());
					vaddMetalDt.setPurity(costMetalDtItem.getPurity());
					vaddMetalDt.setvAddDt(vAddDt);
					
					
					vaddMetalDtService.save(vaddMetalDt);
					
					
					
				}
				
				
					
					//entry for vAddStnDt
					
			//		List<CostStnDt> costStnDts = costStnDtService.findByItemNoAndOrderDtAndCostingMtAndDeactive(costingDt.getItemNo(), costingDt.getOrderDt(), costingDt.getCostingMt(),false);
				
				
				List<CostStnDtItem> costStnDtItems = costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
					
					
					for(CostStnDtItem costStnDtItem:costStnDtItems){
						
						VAddStnDt vAddStnDt = new VAddStnDt();
						
						vAddStnDt.setCostingMt(costStnDtItem.getCostingMt());
						vAddStnDt.setvAddDt(vAddDt);
						vAddStnDt.setShape(costStnDtItem.getShape());
						vAddStnDt.setStoneType(costStnDtItem.getStoneType());
						vAddStnDt.setQuality(costStnDtItem.getQuality());
						vAddStnDt.setSizeGroup(costStnDtItem.getSizeGroup());
						vAddStnDt.setSetting(costStnDtItem.getSetting());
						vAddStnDt.setSettingType(costStnDtItem.getSettingType());
						vAddStnDt.setStoneInwardDt(costStnDtItem.getStoneInwardDt());
						vAddStnDt.setSeqNo(costStnDtItem.getSeqNo());
						vAddStnDt.setBagSrNo(costStnDtItem.getBagSrNo());
						vAddStnDt.setSize(costStnDtItem.getSize());
						vAddStnDt.setSieve(costStnDtItem.getSieve());
						vAddStnDt.setStone(costStnDtItem.getStone());
						vAddStnDt.setCarat(costStnDtItem.getCarat());
						vAddStnDt.setTagWt(costStnDtItem.getTagWt());
						vAddStnDt.setStnRate(costStnDtItem.getStnRate());
						vAddStnDt.setStoneValue(costStnDtItem.getStoneValue());
						vAddStnDt.setSetRate(costStnDtItem.getSetRate());
						vAddStnDt.setSetValue(costStnDtItem.getSetValue());
						vAddStnDt.setHandlingRate(costStnDtItem.getHandlingRate());
						vAddStnDt.setHandlingValue(costStnDtItem.getHandlingValue());
						vAddStnDt.setManualCaratRate(costStnDtItem.getManualCaratRate());
						vAddStnDt.setManualSetRate(costStnDtItem.getManualSetRate());
						vAddStnDt.setPerStoneWt(costStnDtItem.getPerStoneWt());
						vAddStnDt.setCaratRate(costStnDtItem.getCaratRate());
						vAddStnDt.setCenterStone(costStnDtItem.getCenterStone());
						vAddStnDt.setPerPcsRateFlg(costStnDtItem.getPerPcsRateFlg());
						vAddStnDt.setItemNo(costStnDtItem.getItemNo());
						vAddStnDt.setCreatedBy(principal.getName());
						vAddStnDt.setCreatedDate(new java.util.Date());
						vAddStnDt.setPartNm(costStnDtItem.getPartNm());
									
						
						if(costStnDtItem.getStoneInwardDt() != null){
							if(costStnDtItem.getStoneInwardDt().getStoneInwardMt().getInwardType().getName().equalsIgnoreCase("Loan")){
								vAddStnDt.setLoanCarat(costStnDtItem.getCarat());
								vAddStnDt.setLoanValue(costStnDtItem.getStoneValue());
							}
						}
						
						vAddStnDtService.save(vAddStnDt);
						
					}
						
					//-------------------------------//---------------------------//
					
					
					// entry for VAddCompDt
					
					//List<CostCompDt> costCompDts2 = costCompDtService.getCostCompDtList(costingDt.getItemNo(), costingDt.getOrderDt(), costingDt.getCostingMt());
					
					List<CostCompDtItem>costCompDtItems = costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
					
					for(CostCompDtItem costCompDtNew:costCompDtItems){
						
						VAddCompDt vAddCompDt = new VAddCompDt();
										
						vAddCompDt.setvAddDt(vAddDt);
						vAddCompDt.setCostingMt(costCompDtNew.getCostingMt());
						vAddCompDt.setComponent(costCompDtNew.getComponent());
						vAddCompDt.setPurity(costCompDtNew.getPurity());
						vAddCompDt.setColor(costCompDtNew.getColor());
						vAddCompDt.setOrderDt(costCompDtNew.getOrderDt());
						vAddCompDt.setPurityConv(costCompDtNew.getPurityConv());
						vAddCompDt.setClientPurityConv(costCompDtNew.getClientPurityConv());
						vAddCompDt.setCompRate(costCompDtNew.getCompRate());
						vAddCompDt.setItemNo(costCompDtNew.getItemNo());
						vAddCompDt.setCompPcs(costCompDtNew.getCompPcs());
						vAddCompDt.setMetalWt(costCompDtNew.getMetalWt());
						vAddCompDt.setCompValue(costCompDtNew.getCompValue());
						vAddCompDt.setCreatedBy(principal.getName());
						vAddCompDt.setCreatedDate(new java.util.Date());
						
						vAddCompDtService.save(vAddCompDt);
						
					}
					
					
					//-------------------------------//---------------------------//	
					
					
				
			} 
			
			
			
			
		} catch (Exception e) {
			return e.toString();
		}
		
		retVal="1";
		
		return retVal;
	}

	@Override
	public String updateValueAddition(VAddDt vAddDt) {
		
		Double  tempCal = 0.0;
		Double  tempCal2 = 0.0;
		
		Double totMetalValue=0.0;
		Double totLossWt=0.0;
		Double totLossValue=0.0;
		
		
		
		List<VaddMetalDt> vaddMetalDts = vaddMetalDtService.findByVaddDtAndDeactive(vAddDt, false);
				
			for(VaddMetalDt vaddMetalDt:vaddMetalDts){
				
				
				/*-------------------------------------------------Metal Fob--------------------------------*/		
				
				
				if (vaddMetalDt.getPurity() != null) {
					Purity purity = purityService.findOne(vaddMetalDt.getPurity().getId());
					String metalNm=purity.getMetal().getName();
					if(metalNm.equalsIgnoreCase("Gold")){
						tempCal =  vaddMetalDt.getMetalRate()/vaddMetalDt.getPurity().getMetal().getTzRate();
						tempCal2 = (tempCal/(vaddMetalDt.getCostingMt().getVaddIn999().equals(true)?24:23.88)) * (vaddMetalDt.getPurity().getvPurity() != null ? vaddMetalDt.getPurity().getvPurity() : 0.0);
						vaddMetalDt.setPerGramRate(Math.round((tempCal2)*1000.0)/1000.0);
						vaddMetalDt.setMetalValue(Math.round((vaddMetalDt.getMetalWeight()*vaddMetalDt.getPerGramRate())*100.0)/100.0);
						
						vaddMetalDt.setLossWt(Math.round((((vaddMetalDt.getMetalWeight()*vaddMetalDt.getLossPerc())/100))*1000.0)/1000.0);
						vaddMetalDt.setLossValue(Math.round((vaddMetalDt.getLossWt()*vaddMetalDt.getPerGramRate())*100.0)/100.0);
						
					
					}else if (metalNm.equalsIgnoreCase("Silver")) {
						tempCal =  vaddMetalDt.getMetalRate()/vaddMetalDt.getPurity().getMetal().getTzRate();
						tempCal2 = (tempCal*(vaddMetalDt.getPurity().getPurityConv() != null ? vaddMetalDt.getPurity().getPurityConv() : 0.0));
									
						
						vaddMetalDt.setPerGramRate(Math.round(((tempCal2/(vaddMetalDt.getCostingMt().getVaddIn999().equals(true)?0.999:0.995)))*1000.0)/1000.0);
						vaddMetalDt.setMetalValue(Math.round((vaddMetalDt.getMetalWeight()*vaddMetalDt.getPerGramRate())*100.0)/100.0);
						
						vaddMetalDt.setLossWt(Math.round(((vaddMetalDt.getMetalWeight()*vaddMetalDt.getLossPerc())/100)*1000.0)/1000.0);
						vaddMetalDt.setLossValue(Math.round((vaddMetalDt.getLossWt()*vaddMetalDt.getPerGramRate())*100.0)/100.0);
						
						
							
					}else if (metalNm.equalsIgnoreCase("Alloy")) {
						tempCal =  vaddMetalDt.getMetalRate()/1000;
										
						vaddMetalDt.setPerGramRate(Math.round((tempCal2 )*1000.0)/1000.0);
						vaddMetalDt.setMetalValue(Math.round((vaddMetalDt.getMetalWeight()*vaddMetalDt.getPerGramRate())*100.0)/100.0);
						vaddMetalDt.setLossWt(Math.round(((vaddMetalDt.getMetalWeight()*vaddMetalDt.getLossPerc())/100)*1000.0)/1000.0);
						vaddMetalDt.setLossValue(Math.round((vaddMetalDt.getLossWt()*vaddMetalDt.getPerGramRate())*100.0)/100.0);
										
						
					}
					
				
					
					
				
				}
				
				totMetalValue +=vaddMetalDt.getMetalValue();
				totLossWt += vaddMetalDt.getLossWt();
				totLossValue +=vaddMetalDt.getLossValue();

			}
			

			
			
			List<VAddCompDt> vAddCompDts = vAddCompDtService.findByVAddDtAndDeactive(vAddDt, false);
			
			for(VAddCompDt vAddCompDt :vAddCompDts){

				if (vAddCompDt.getPurity() != null) {
						Purity purity = purityService.findOne(vAddCompDt.getPurity().getId());
							if(purity.getMetal().getName().equalsIgnoreCase("Gold")){
								tempCal =  vAddCompDt.getMetalRate()/vAddCompDt.getPurity().getMetal().getTzRate();
								tempCal2 = (tempCal/(vAddCompDt.getCostingMt().getVaddIn999().equals(true)?24:23.88)) * (vAddCompDt.getPurity().getvPurity() != null ? vAddCompDt.getPurity().getvPurity() : 0.0);
								vAddCompDt.setPerGramMetalRate(Math.round((tempCal2)*1000.0)/1000.0);
								vAddCompDt.setMetalValue(Math.round((vAddCompDt.getMetalWt()*vAddCompDt.getPerGramMetalRate())*100.0)/100.0);
								
								vAddCompDt.setLossWt(Math.round(((vAddCompDt.getMetalWt()*vAddCompDt.getLossPerc())/100)*1000.0)/1000.0);
								vAddCompDt.setLossValue(Math.round((vAddCompDt.getLossWt()*vAddCompDt.getPerGramMetalRate())*100.0)/100.0);
								
							
							}else if (purity.getMetal().getName().equalsIgnoreCase("Silver")) {
								tempCal =  vAddCompDt.getMetalRate()/vAddCompDt.getPurity().getMetal().getTzRate();
								tempCal2 = (tempCal*(vAddCompDt.getPurity().getPurityConv() != null ? vAddCompDt.getPurity().getPurityConv() : 0.0));
								vAddCompDt.setPerGramMetalRate(Math.round(((tempCal2/(vAddCompDt.getCostingMt().getVaddIn999().equals(true)?0.999:0.995)))*1000.0)/1000.0);
								vAddCompDt.setMetalValue(Math.round((vAddCompDt.getMetalWt()*vAddCompDt.getPerGramMetalRate())*100.0)/100.0);
								vAddCompDt.setLossWt(Math.round(((vAddCompDt.getMetalWt()*vAddCompDt.getLossPerc())/100)*1000.0)/1000.0);
								vAddCompDt.setLossValue(Math.round((vAddCompDt.getLossWt()*vAddCompDt.getPerGramMetalRate())*100.0)/100.0);

								
					/*
					 * if(vAddCompDt.getCostingMt().getVaddIn999().equals(true)){ tempCal2 =
					 * tempCal; }else{ tempCal2 = (tempCal*(vAddCompDt.getPurity().getPurityConv()
					 * != null ? vAddCompDt.getPurity().getPurityConv() : 0.0)); }
					 * 
					 * 
					 * vAddCompDt.setPerGramMetalRate(Math.round((tempCal2)*1000.0)/1000.0);
					 * vAddCompDt.setMetalValue(Math.round((vAddCompDt.getMetalWt()*vAddCompDt.
					 * getPerGramMetalRate())*100.0)/100.0);
					 */
																
								
									
							}else if (purity.getMetal().getName().equalsIgnoreCase("Alloy")) {
								tempCal =  vAddCompDt.getMetalRate()/1000;
												
								vAddCompDt.setPerGramMetalRate(Math.round((tempCal2 )*1000.0)/1000.0);
								vAddCompDt.setMetalValue(Math.round((vAddCompDt.getMetalWt()*vAddCompDt.getPerGramMetalRate())*100.0)/100.0);
								vAddCompDt.setLossWt(Math.round(((vAddCompDt.getMetalWt()*vAddCompDt.getLossPerc())/100)*1000.0)/1000.0);
								vAddCompDt.setLossValue(Math.round((vAddCompDt.getLossWt()*vAddCompDt.getPerGramMetalRate())*100.0)/100.0);
												
								
							}
							
							
						

					}

				
				
				totMetalValue +=vAddCompDt.getMetalValue();
				totLossWt += vAddCompDt.getLossWt();
				totLossValue +=vAddCompDt.getLossValue();
				
			}
			
			
			
			
			vAddDt.setMetalValue( Math.round((totMetalValue)*100.0)/100.0);
			vAddDt.setLossWt(Math.round((totLossWt)*1000.0)/1000.0);
			vAddDt.setLossValue(Math.round((totLossValue)*100.0)/100.0);
			save(vAddDt);
			
		

		
		
		
		
		
		return "";
		
	}

	@Override
	public String applyWastage(VAddDt vAddDt) {
	
		CostingMt costingMt =vAddDt.getCostingMt();
		
		
		List<VaddMetalDt> vaddMetalDts = vaddMetalDtService.findByVaddDtAndDeactive(vAddDt, false);
				
			for(VaddMetalDt vaddMetalDt:vaddMetalDts){
				
				if(vaddMetalDt.getrLock().equals(false)){
					if (vaddMetalDt.getPurity() != null) {
						Purity purity = purityService.findOne(vaddMetalDt.getPurity().getId());
																	
						VAddMetalRate addMetalRate = addMetalRateService.findByCostingMtAndDeactiveAndMetal(costingMt, false, purity.getMetal());
						
						
						
						if(addMetalRate!=null){
							
							if(vaddMetalDt.getMainMetal().equals(true)){
								vAddDt.setLossPercent(addMetalRate.getLossPerc());
								save(vAddDt);
							}
							
							vaddMetalDt.setLossPerc(addMetalRate.getLossPerc());	
							vaddMetalDtService.save(vaddMetalDt);
						}

					}

				}
				
				
			

			}
			
			
			List<VAddCompDt> vAddCompDts = vAddCompDtService.findByVAddDtAndDeactive(vAddDt, false);
			
			for(VAddCompDt vAddCompDt :vAddCompDts){
				
				if(vAddCompDt.getrLock().equals(false)){
					if (vAddCompDt.getPurity() != null) {
						Purity purity = purityService.findOne(vAddCompDt.getPurity().getId());
						
																	
						VAddMetalRate addMetalRate = addMetalRateService.findByCostingMtAndDeactiveAndMetal(costingMt, false, purity.getMetal());
						
						
						
						if(addMetalRate!=null){
							
							vAddCompDt.setLossPerc(addMetalRate.getLossPerc());
							
													
							
						}

					}

				}
				
								
			}
			
			
			
			
		
		

		
		
		
		
		
		return "";
	}

	@Override
	public String updateQualityStoneRate(Integer costMtId, Integer shapeId, Integer qualityId,
			Double fromRate, Double toRate, Principal principal,Integer sizeGroupId) {
		// TODO Auto-generated method stub
		
		CostingMt costingMt =costingtMtService.findOne(costMtId);
		Quality quality =qualityService.findOne(qualityId);
		Shape shape =shapeService.findOne(shapeId); 

		List<VAddStnDt>vAddStnDts =vAddStnDtService.findByCostingMtAndShapeAndQualityAndDeactive(costingMt, shape,quality, false);
		
		for(VAddStnDt vAddStnDt:vAddStnDts){
			if(sizeGroupId != null){
				
				if(vAddStnDt.getStnRate().equals(fromRate) && vAddStnDt.getSizeGroup().getId().equals(sizeGroupId) ){
					vAddStnDt.setStnRate(toRate);
					
					if(vAddStnDt.getPerPcsRateFlg().equals(false)){
						vAddStnDt.setStoneValue(Math.round((vAddStnDt.getCarat() * vAddStnDt.getStnRate())*100.0)/100.0);
					}else{
						vAddStnDt.setStoneValue(Math.round((vAddStnDt.getStone() * vAddStnDt.getStnRate())*100.0)/100.0);
					}
					
					vAddStnDtService.save(vAddStnDt);
					
					List<VAddStnDt>vAddStnDts2 =vAddStnDtService.findByVAddDtAndDeactive(vAddStnDt.getvAddDt(), false);
					Double totStoneVal=0.0;
					for(VAddStnDt vAddStnDt2 :vAddStnDts2){
						totStoneVal +=vAddStnDt2.getStoneValue();
						
						
					}
					
					VAddDt vAddDt = vAddStnDt.getvAddDt();
					vAddDt.setStoneValue(Math.round(totStoneVal*1000.0)/1000.0);
					save(vAddDt);
					
					
					}
				
			}else{
				if(vAddStnDt.getStnRate().equals(fromRate)){
					vAddStnDt.setStnRate(toRate);
					
					if(vAddStnDt.getPerPcsRateFlg().equals(false)){
						vAddStnDt.setStoneValue(Math.round((vAddStnDt.getCarat() * vAddStnDt.getStnRate())*100.0)/100.0);
					}else{
						vAddStnDt.setStoneValue(Math.round((vAddStnDt.getStone() * vAddStnDt.getStnRate())*100.0)/100.0);
					}
					
					vAddStnDtService.save(vAddStnDt);
					
					List<VAddStnDt>vAddStnDts2 =vAddStnDtService.findByVAddDtAndDeactive(vAddStnDt.getvAddDt(), false);
					Double totStoneVal=0.0;
					for(VAddStnDt vAddStnDt2 :vAddStnDts2){
						totStoneVal +=vAddStnDt2.getStoneValue();
						
						
					}
					
					VAddDt vAddDt = vAddStnDt.getvAddDt();
					vAddDt.setStoneValue(Math.round(totStoneVal*1000.0)/1000.0);
					save(vAddDt);
					
					
					}
			}
			

			
		}
		
		
		
		
		
		return null;
	}

	@Override
	public String vaddDtListing(Integer limit, Integer offset, String sort, String order, String search,
			String pInvNo) {
		
		StringBuilder sb = new StringBuilder();
		
		
		Page<VAddDt> vAddDts= null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(pInvNo.trim(), false);
		vAddDts=searchAll(limit, offset, sort, order, search, costingMt.getId());
		
		sb.append("{\"total\":").append(vAddDts.getTotalElements()).append(",\"rows\": [");
		
	
			for(VAddDt vAddDt:vAddDts){
				
			sb.append("{\"id\":\"")
				.append(vAddDt.getId())
				.append("\",\"itemNo\":\"")
				.append((vAddDt.getItemNo() != null ? vAddDt.getItemNo() : ""))
				.append("\",\"party\":\"")
				.append((vAddDt.getParty() != null ?vAddDt.getParty().getPartyCode() : ""))
				.append("\",\"ordNo\":\"")
				.append((vAddDt.getOrderDt().getOrderMt().getInvNo() != null ? vAddDt.getOrderDt().getOrderMt().getInvNo() : ""))
				.append("\",\"ordRefNo\":\"")
				.append((vAddDt.getOrderDt().getOrderMt().getRefNo() != null ? vAddDt.getOrderDt().getOrderMt().getRefNo() : ""))
				.append("\",\"style\":\"")
				.append((vAddDt.getDesign() != null ? vAddDt.getDesign().getMainStyleNo() : ""))
				.append("\",\"purity\":\"")
				.append((vAddDt.getPurity() != null ? vAddDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((vAddDt.getColor() != null ? vAddDt.getColor().getName() : ""))
				.append("\",\"pcs\":\"")
				.append((vAddDt.getPcs() != null ? vAddDt.getPcs() : ""))
				.append("\",\"grossWt\":\"")
				.append((vAddDt.getGrossWt() != null ? vAddDt.getGrossWt() : ""))
				.append("\",\"netWt\":\"")
				.append((vAddDt.getNetWt() != null ? vAddDt.getNetWt() : ""))
				.append("\",\"metalValue\":\"")
				.append((vAddDt.getMetalValue() != null ? vAddDt.getMetalValue() : ""))
				.append("\",\"lossPerc\":\"")
				.append((vAddDt.getLossPercent() != null ? vAddDt.getLossPercent() : ""))
				.append("\",\"lossWt\":\"")
				.append((vAddDt.getLossWt() != null ? vAddDt.getLossWt() : ""))
				.append("\",\"lossValue\":\"")
				.append((vAddDt.getLossValue() != null ? vAddDt.getLossValue() : ""))
				.append("\",\"totMetalValue\":\"")
				.append(Math.round((vAddDt.getMetalValue()+vAddDt.getLossValue())*100.0)/100.0)
				.append("\",\"stoneValue\":\"")
				.append((vAddDt.getStoneValue() != null ? vAddDt.getStoneValue() : ""))
				.append("\",\"compValue\":\"")
				.append((vAddDt.getCompValue() != null ? vAddDt.getCompValue() : ""))
				.append("\",\"setValue\":\"")
				.append((vAddDt.getSetValue() != null ? vAddDt.getSetValue() : ""))
				.append("\",\"handlingCost\":\"")
				.append((vAddDt.getHdlgValue() != null ? vAddDt.getHdlgValue() : ""))
				.append("\",\"fob\":\"")
				.append((vAddDt.getFob() != null ? vAddDt.getFob() : ""))
				.append("\",\"other\":\"")
				.append((vAddDt.getOther() != null ? vAddDt.getOther() : ""))
				.append("\",\"finalPrice\":\"")
				.append((vAddDt.getFinalPrice() != null ? vAddDt.getFinalPrice() : ""))
				.append("\",\"perPcsPrice\":\"")
				.append((vAddDt.getFinalPricePerPcs() != null ? vAddDt.getFinalPricePerPcs() : ""))
				.append("\",\"vAddTotal\":\"")
				.append((vAddDt.getvAddTotal() != null ? vAddDt.getvAddTotal() : ""))
				.append("\",\"addedStoneValue\":\"")
				.append((vAddDt.getAddedStoneValue() != null ? vAddDt.getAddedStoneValue() : ""))
				.append("\",\"addedMetalValue\":\"")
				.append((vAddDt.getAddedMetalValue() != null ? vAddDt.getAddedMetalValue() : ""))
				.append("\",\"rLock\":\"")
				.append((vAddDt.getrLock() == null ? "": (vAddDt.getrLock() ? "Lock": "Unlock"))) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCostDtLockUnLock(")
				.append(vAddDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\"},");
			
				
			}
		
	
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}





	
	
	
	
	

}

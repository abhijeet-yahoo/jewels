package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMeltingIssDtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockMeltingDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;

@Service
@Repository
@Transactional
public class MeltingIssDtService implements IMeltingIssDtService {

	@Autowired
	private IMeltingIssDtRepository meltingIssDtRepositoty;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private IStockTranService stockTranService;
	

	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IMeltingMtService meltingMtService;
	
	@Autowired
	private ITranMetalService tranMetalService; 
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
		
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockMeltingDtService stockMeltingDtService; 
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	

	@Override
	public List<MeltingIssDt> findAll() {
		return meltingIssDtRepositoty.findAll();
	}

	@Override
	public void save(MeltingIssDt meltingIssDt) {
		meltingIssDtRepositoty.save(meltingIssDt);

	}

	@Override
	public void delete(int id) {
		MeltingIssDt meltingIssDt = meltingIssDtRepositoty.findOne(id);
		meltingIssDt.setDeactive(true);
		meltingIssDt.setDeactiveDt(new java.util.Date());
		meltingIssDtRepositoty.save(meltingIssDt);

	}

	@Override
	public MeltingIssDt findOne(int id) {
		return meltingIssDtRepositoty.findOne(id);
	}

	@Override
	public Page<MeltingIssDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		return meltingIssDtRepositoty
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Long count() {
		return meltingIssDtRepositoty.count();
	}



	@Override
	public MeltingIssDt findByUniqueId(Long uniqueId) {
		return meltingIssDtRepositoty.findByUniqueId(uniqueId);
	}

	@Override
	public List<MeltingIssDt> findByMeltingMtAndDeactive(MeltingMt meltingMt,
			Boolean deactive) {
		return meltingIssDtRepositoty.findByMeltingMtAndDeactive(meltingMt, deactive);
	}

	@Override
	public String meltingPickupsave(Integer mtId, JSONArray tblData,
			Principal principal) {
		
		String 	retVal="-1";
		
		
		MeltingMt meltingMt =meltingMtService.findOne(mtId);
		
		for (int i = 0; i < tblData.length(); i++) {
			
			JSONObject jsonObject =   (JSONObject) tblData.get(i);
			
			TranMt tranMt =tranMtService.findOne(Integer.parseInt(jsonObject.get("id").toString()));
			
			List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMt, true);
			
			for(TranMetal tranMetal:tranMetals){
				
				MeltingIssDt meltingIssDt = new MeltingIssDt();
				meltingIssDt.setBagMt(tranMetal.getBagMt());
				
				List<TranDt>tranDts =tranDtService.findByTranMtAndCurrStkAndPartNm(tranMt, true, tranMetal.getPartNm());
				Double vCarat =0.0;
				Integer vStone=0;
				
				for(TranDt tranDt :tranDts){
					vCarat +=tranDt.getCarat();
					vStone +=tranDt.getStone();
				}
				
				
				
				meltingIssDt.setCarat(Math.round(vCarat*1000.0)/1000.0);
				meltingIssDt.setColor(tranMetal.getColor());
				meltingIssDt.setCreatedBy(principal.getName());
				meltingIssDt.setCreatedDt(new Date());
				
				meltingIssDt.setExcpPureWt(Math.round((tranMetal.getMetalWeight()*tranMetal.getPurityConv())*1000.0)/1000.0);
				meltingIssDt.setMeltingMt(meltingMt);
				meltingIssDt.setNetWt(tranMetal.getMetalWeight());
				meltingIssDt.setDtRemark(tranMetal.getPartNm().getFieldValue());
				
				Double grossWt=0.0;
				if(vCarat>0){
					grossWt =Math.round((tranMetal.getMetalWeight()+(vCarat/5))*1000.0)/1000.0;
				}else{
					grossWt = tranMetal.getMetalWeight();
				}
				meltingIssDt.setIssFreshMetalWt(grossWt);
				meltingIssDt.setPurity(tranMetal.getPurity());
				meltingIssDt.setPurityConv(tranMetal.getPurityConv());
				meltingIssDt.setRefMtId(tranMt.getId());
				meltingIssDt.setRefTranMetalId(tranMetal.getId());
				meltingIssDt.setStone(vStone);
				meltingIssDt.setMetalRate(tranMetal.getMetalRate());
				
				
				save(meltingIssDt);
				
				//tranMetal.setCurrStk(false);
				//tranMetalService.save(tranMetal);
				
				
				/*
				 * for(TranDt tranDt :tranDts){ tranDt.setCurrStk(false);
				 * tranDtService.save(tranDt); }
				 */
				
				
			}
			
			
			List<CompTran> compTrans = compTranService.getCompTranListForCosting(tranMt.getBagMt().getId());
			
			for(CompTran compTran : compTrans){
				
				if(compTran.getComponent().getChargable().equals(false)){
					continue;
				}
				
				
				List<CompTran> cTran = compTranService.findByBagMtAndPurityAndColorAndComponent(compTran.getBagMt(),compTran.getPurity(), compTran.getColor(), compTran.getComponent());
			
				
					Double balance = 0.0;
					Double compPcs = 0.0;
				
					for(CompTran comp:cTran){
						balance += comp.getBalance(); 
						compPcs += comp.getBalancePcs();
					}
					
					
					if(balance >= 0){
						continue;
					}
					
					if(balance < 0){
						balance = -balance;
						compPcs = -compPcs;
					}
					
					
					MeltingIssDt meltingIssDt = new MeltingIssDt();
					meltingIssDt.setBagMt(compTran.getBagMt());
					meltingIssDt.setColor(compTran.getColor());
					meltingIssDt.setCreatedBy(principal.getName());
					meltingIssDt.setCreatedDt(new Date());
					meltingIssDt.setExcpPureWt(Math.round((balance*compTran.getPurityConv())*1000.0)/1000.0);
					meltingIssDt.setMeltingMt(meltingMt);
					meltingIssDt.setNetWt(balance);
					meltingIssDt.setIssFreshMetalWt(balance);
					meltingIssDt.setPurity(compTran.getPurity());
					meltingIssDt.setPurityConv(compTran.getPurityConv());
					meltingIssDt.setRefMtId(tranMt.getId());
					meltingIssDt.setDtRemark(compTran.getComponent().getName());
					meltingIssDt.setMetalRate(compTran.getMetalRate());
					
					save(meltingIssDt);
				
			}
			
			
		//	tranMt.setCurrStk(false);
			//tranMtService.save(tranMt);
			BagMt bagMt = tranMt.getBagMt();
			bagMt.setBagCloseFlg(true);
			bagMtService.save(bagMt);
			
			
			
		}
		
		retVal="1";
		// TODO Auto-generated method stub
		return retVal;
	}

	@Override
	public String meltingIssDtDelete(Integer id) {
		
		MeltingIssDt meltingIssDt = findOne(id);
		
		if(meltingIssDt.getTranType() != null) {
			
			List<MeltingIssDt> meltingIssDts = findByMeltingMtAndBarcodeAndDeactive(meltingIssDt.getMeltingMt(), meltingIssDt.getBarcode(), false);
			for (MeltingIssDt meltingIssDt2 : meltingIssDts) {
				
				StockMeltingDt stockMeltingDt = stockMeltingDtService.findOne(meltingIssDt2.getRefTranMetalId());
				 stockMeltingDt.setCurrStk(true);
			//	 stockMeltingDt.setModiBy(principal.getName());
				 stockMeltingDt.setModiDt(new Date());
				 stockMeltingDtService.save(stockMeltingDt);
				 
				 
				 //StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(stockMeltingDt.getBarcode(), false);
				 StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStatus("Melting", stockMeltingDt.getId(),"Melting");
				 
				 stockTran.setCurrStk(true);
				 stockTranService.save(stockTran);
				 
				 StockMt stockMt = stockTran.getStockMt();
				 
				 stockMt.setCurrStk(true);
				 stockMtService.save(stockMt);
				 
				
				delete(meltingIssDt2.getId());
			}
			
		}else {
		
		if(meltingIssDt.getBagMt() !=null){
			
		//	TranMt tranMt = tranMtService.findOne(meltingIssDt.getRefMtId());
			List<MeltingIssDt> meltingIssDts =findByMeltingMtAndDeactiveAndBagMt(meltingIssDt.getMeltingMt(), false, meltingIssDt.getBagMt());
			
			for(MeltingIssDt meltingIssDt2 :meltingIssDts){
				
				
				/*
				 * if(meltingIssDt2.getRefTranMetalId() !=null) { TranMetal tranMetal
				 * =tranMetalService.findOne(meltingIssDt2.getRefTranMetalId());
				 * tranMetal.setCurrStk(true); tranMetalService.save(tranMetal);
				 * 
				 * 
				 * List<TranDt>tranDts =tranDtService.findByTranMtAndCurrStkAndPartNm(tranMt,
				 * false, tranMetal.getPartNm());
				 * 
				 * for(TranDt tranDt :tranDts){ tranDt.setCurrStk(true);
				 * tranDtService.save(tranDt); } }
				 */
				
				delete(meltingIssDt2.getId());
				
			}
			
			BagMt bagMt =meltingIssDt.getBagMt();
			bagMt.setBagCloseFlg(false);
			bagMtService.save(bagMt);
			
			/*
			 * tranMt.setCurrStk(true); tranMtService.save(tranMt);
			 */
			
			
		}else{
			
			List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(meltingIssDt.getId(), "MeltingIssue",false);
			
			for(MetalTran metalTran : metalTrans){
				metalTranService.delete(metalTran.getId());
			}
			
			delete(id);
		}
		}
			
		return "redirect:/manufacturing/transactions/meltingMt/edit/"+meltingIssDt.getMeltingMt().getId()+".html";
	}
	

	@Override
	public List<MeltingIssDt> findByMeltingMtAndDeactiveAndBagMt(
			MeltingMt meltingMt, Boolean deactive, BagMt bagMt) {
		// TODO Auto-generated method stub
		return meltingIssDtRepositoty.findByMeltingMtAndDeactiveAndBagMt(meltingMt, deactive, bagMt);
	}

	@Override
	public String meltingIssDtSave(MeltingIssDt meltingIssDt, Integer id,
			String pInvNo, Double prevNetWt,Double pFMetalWt, Double prevKt, Double prevColor,
			Double currNetWt, Double pExcpPureWt, Principal principal) {
		 
		String action = "added";
		String retVal="-1";
		MetalTran metalTran = null;
		
		try {
			
		//	Integer locationId = departmentService.findByName("Central").getId();             Old Code
			
			Integer locationId = meltingIssDt.getDeptLocation().getId();
			
			Double freshStockBalance;
	
			if(pFMetalWt > 0){
				freshStockBalance = metalTranService.getStockBalance(meltingIssDt.getPurity().getId(), meltingIssDt.getColor().getId(), locationId);
				if(freshStockBalance == null){
					return retVal = "-1";
				}
			}else{
				freshStockBalance = 0.0;
			}

	
			
			
			
			Purity tempPurityObj = purityService.findOne(meltingIssDt.getPurity().getId());
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				//fresh
				if(pFMetalWt > 0){
					if(freshStockBalance < currNetWt){
						return retVal = "-1";
					}
				}

				
				meltingIssDt.setCreatedBy(principal.getName());
				meltingIssDt.setCreatedDt(new java.util.Date());
				meltingIssDt.setMeltingMt(meltingMtService.findByInvNoAndDeactive(pInvNo, false));
				meltingIssDt.setUniqueId(new Date().getTime());
				meltingIssDt.setNetWt(currNetWt);
				meltingIssDt.setExcpPureWt(Math.round((pExcpPureWt)*1000.0)/1000.0);
				meltingIssDt.setIssFreshMetalWt(pFMetalWt);
				
				
				if(tempPurityObj.getPurityConv() != null){
					meltingIssDt.setPurityConv(tempPurityObj.getPurityConv());
				}else{
					meltingIssDt.setPurityConv(0.0);
				}
				
				
				metalTran = new MetalTran();

				retVal = "1";

			} else {
				
				//fresh
				if(meltingIssDt.getPurity().getId().equals(prevKt) && meltingIssDt.getColor().getId().equals(prevColor)){
				
						if(pFMetalWt > 0){
							Double freshDifference = currNetWt - prevNetWt;
							if(freshStockBalance < freshDifference){
								return retVal = "-1";
							}
						}
						
				
				}else{
					
					if(pFMetalWt > 0){
							if(freshStockBalance < currNetWt){
							return retVal = "-1";
						}
					}
					
				
					
					
				}
					
				
				meltingIssDt.setId(id);
				meltingIssDt.setModiBy(principal.getName());
				meltingIssDt.setModiDt(new java.util.Date());
				meltingIssDt.setMeltingMt(meltingMtService.findByInvNoAndDeactive(pInvNo, false));
				meltingIssDt.setNetWt(currNetWt);
				meltingIssDt.setExcpPureWt(Math.round((pExcpPureWt)*1000.0)/1000.0);
				meltingIssDt.setIssFreshMetalWt(pFMetalWt);
				
				action = "updated";
				
				if(tempPurityObj.getPurityConv() != null){
					meltingIssDt.setPurityConv(tempPurityObj.getPurityConv());
				}else{
					meltingIssDt.setPurityConv(0.0);
				}

			
				metalTran = metalTranService.findByRefTranIdAndTranTypeAndInOutFld(id, "MeltingIssue", "D");
				retVal = "2";
			}
			
			//RAte Apply
			Double mtlRate=metalTranService.getMetalRate(meltingIssDt.getPurity().getId(), meltingIssDt.getColor().getId(),  
					locationId, currNetWt);
			
			mtlRate = mtlRate != null ? mtlRate :0.0;
			
			/*
			 * Double totAmount = (double) Math.round((mtlRate * currNetWt)*100.0)/100.0;
			 * Double totPureAmount = (double) Math.round((totAmount *
			 * meltingIssDt.getPurityConv())*100.0)/100.0;
			 * 
			 * Double finalRate = (double) Math.round((totPureAmount *
			 * pExcpPureWt)*100.0)/100.0;
			 */

			meltingIssDt.setMetalRate(mtlRate);
			save(meltingIssDt);
			
			MeltingIssDt meltingIss = findByUniqueId(meltingIssDt.getUniqueId());
			
			if(meltingIss.getIssFreshMetalWt() > 0){
				
				
				
//				metalTran.setDepartment(departmentService.findByName("Melting"));
//				metalTran.setDeptLocation(departmentService.findByName("Central"));
				
				metalTran.setDepartment(departmentService.findByName("Melting"));
				metalTran.setDeptLocation(meltingIssDt.getDeptLocation());
				metalTran.setRefTranId(meltingIss.getId());
				metalTran.setPurity(meltingIss.getPurity());
				metalTran.setColor(meltingIss.getColor());
				metalTran.setMetalWt(meltingIss.getNetWt());
				metalTran.setBalance(meltingIss.getNetWt() * -1);
				metalTran.setInOutFld("D");
				if(meltingIss.getPurity().getPurityConv() != null){
					metalTran.setPurityConv(tempPurityObj.getPurityConv());
				}else{
					metalTran.setPurityConv(0.0);
				}
				metalTran.setTranType("MeltingIssue");
				metalTran.setCreatedBy(meltingIss.getCreatedBy());
				metalTran.setCreatedDt(meltingIss.getCreatedDt());
				metalTran.setDeactive(false);
				metalTran.setTranDate(new Date());
				metalTran.setMetalRate(mtlRate);
		
				metalTranService.save(metalTran);
			}
			retVal=retVal+","+action;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return retVal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> meltingStnRecList(Integer mtid, Integer deptid) {
		
		
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_meltingStnRec(?,?) }");
		query.setParameter(1,mtid);
		query.setParameter(2,deptid);
		
		
		List<Object[]> meltingList = query.getResultList();
		
				
		return meltingList;
	}

	@Override
	public String meltingStnTransfer(Integer mtid, String jsonData,Principal principal,Integer locationId) {
		
		String retVal="-1";
		
		
		
		JSONArray jsonBagDt = new JSONArray(jsonData);
		for (int y = 0; y < jsonBagDt.length(); y++) {
			JSONObject dataBagDt = (JSONObject) jsonBagDt.get(y);
			
			Integer vStone = dataBagDt.getInt("trfStone");
			Double vCarat = dataBagDt.getDouble("trfCarat");
			
			if(vStone >0 || vCarat >0){
				
				
			
			StoneType stoneType =  stoneTypeService.findByName(dataBagDt.get("stoneType").toString());
			Shape shape = shapeService.findByName(dataBagDt.get("shape").toString());
			Quality quality =  qualityService.findByShapeAndName(shape,dataBagDt.get("quality").toString());
			
			Department department = departmentService.findOne(locationId);
			Department departmentFrom = departmentService.findByName("Melting");
			
		//	Department department = departmentService.findByName("Diamond");
			SizeGroup sizeGroup = sizeGroupService.findByName(dataBagDt.get("sizeGroup").toString());
			
			StoneChart chart =stoneChartService.findByShapeAndSieveAndDeactive(shape, dataBagDt.get("sieve").toString(), false);
			
			StoneTran stoneTran =  new StoneTran();
			stoneTran.setTranDate(new java.util.Date()); 
			stoneTran.setDepartment(departmentFrom);
			stoneTran.setDeptLocation(department);
			stoneTran.setStoneType(stoneType);
			stoneTran.setShape(shape);
			stoneTran.setQuality(quality);
			stoneTran.setSize(chart.getSize());
			stoneTran.setSieve(dataBagDt.get("sieve").toString());
			stoneTran.setSizeGroup(sizeGroup);
			stoneTran.setStone(vStone);
			stoneTran.setCarat(vCarat);		
			stoneTran.setBalStone(vStone);
			stoneTran.setBalCarat(vCarat); 
			stoneTran.setCreatedBy(principal.getName());
			stoneTran.setCreatedDt(new  Date());
			stoneTran.setInOutFld("C");
			stoneTran.setBagMt(null);;
			stoneTran.setBagSrNo(0);
			stoneTran.setSettingType(null);
			stoneTran.setSetting(null);
			stoneTran.setTranType("MELTREC");
			stoneTran.setRefTranId(mtid);
			stoneTran.setAvgRate(dataBagDt.getDouble("avgRate"));
			stoneTran.setFactoryRate(dataBagDt.getDouble("factoryRate"));
			stoneTran.setRate(dataBagDt.getDouble("rate"));
			stoneTran.setTransferRate(dataBagDt.getDouble("trfRate"));
			
			stoneTranService.save(stoneTran);
			
			}
			
			
		}
		
		
		retVal ="1";
		
		return retVal;
	}

	@Override
	public String stockIssueToMeltingTransfer(Integer mtId, String stockDtId, Principal principal) {
		// TODO Auto-generated method stub
		String 	retVal="-1";
		
		
		MeltingMt meltingMt =meltingMtService.findOne(mtId);
		
		String[] stkMeltdtId = stockDtId.split(",");
		for (int i = 0; i < stkMeltdtId.length; i++) {
		
			StockMeltingDt stockMeltingDt = stockMeltingDtService.findOne(Integer.parseInt(stkMeltdtId[i]));
		
			StockMt stockMt = stockMtService.findOne(stockMeltingDt.getStockMt().getMtId());
			
			// BagMt bagMt = bagMtService.findOne(stockMeltingDt.getBagId());
			List<StockMetalDt> stockMetalDts = stockMetalDtService.findByStockMtAndDeactive(stockMeltingDt.getStockMt(), false);
			
			 for(StockMetalDt stockMetalDt:stockMetalDts) {
				 MeltingIssDt meltingIssDt = new MeltingIssDt(); 
				
				
				 //meltingIssDt.setBagMt(bagMt);
				 
				 
				 	Double vCarat =0.0;
					Integer vStone=0;
					List<StockStnDt> stockStnDts = stockStnDtService.findByStockMtAndDeactive(stockMeltingDt.getStockMt(), false);
					for (StockStnDt stockStnDt : stockStnDts) {
						vStone += stockStnDt.getStone();
						vCarat += stockStnDt.getCarat();
					}
					
					
					meltingIssDt.setCarat(Math.round(vCarat*1000.0)/1000.0);
					meltingIssDt.setColor(stockMetalDt.getColor());
					meltingIssDt.setCreatedBy(principal.getName());
					meltingIssDt.setCreatedDt(new Date());
					
					meltingIssDt.setExcpPureWt(Math.round((stockMetalDt.getMetalWt() * stockMetalDt.getPurityConv())*1000.0)/1000.0);
					meltingIssDt.setMeltingMt(meltingMt);
					meltingIssDt.setNetWt(stockMetalDt.getMetalWt());
					meltingIssDt.setDtRemark(stockMetalDt.getPartNm().getFieldValue());
					
					Double grossWt=0.0;
					if(vCarat>0){
						grossWt =Math.round((stockMetalDt.getMetalWt()+(vCarat/5))*1000.0)/1000.0;
					}else{
						grossWt = stockMetalDt.getMetalWt();
					}
					meltingIssDt.setIssFreshMetalWt(grossWt);
					meltingIssDt.setPurity(stockMetalDt.getPurity());
					meltingIssDt.setPurityConv(stockMetalDt.getPurityConv());
					meltingIssDt.setRefMtId(stockMeltingDt.getStockMeltingMt().getId());
					meltingIssDt.setStone(vStone);
					meltingIssDt.setMetalRate(stockMetalDt.getMetalRate());
					meltingIssDt.setTranType("MELTINGISS");
					meltingIssDt.setBarcode(stockMt.getBarcode());
					save(meltingIssDt);
				 
			 }
			 
				  
				  List<StockCompDt> stockCompDts = stockCompDtService.findByStockMtAndDeactive(stockMt, false);
				  for (StockCompDt stockCompDt : stockCompDts) {
					  
					  MeltingIssDt meltingIssDt = new MeltingIssDt();
					//  meltingIssDt.setBagMt(bagMt);
					  meltingIssDt.setColor(stockCompDt.getColor());
					  meltingIssDt.setCreatedBy(principal.getName()); 
					  meltingIssDt.setCreatedDt(new  Date());
					  meltingIssDt.setExcpPureWt(Math.round((stockCompDt.getCompWt() * stockCompDt.getPurityConv())*1000.0)/1000.0); 
					  meltingIssDt.setMeltingMt(meltingMt);
					  meltingIssDt.setNetWt(stockCompDt.getCompWt()); 
					  meltingIssDt.setIssFreshMetalWt(stockCompDt.getCompWt());
					  meltingIssDt.setPurity(stockCompDt.getPurity());
					  meltingIssDt.setPurityConv(stockCompDt.getPurityConv());
					  meltingIssDt.setRefMtId(stockMeltingDt.getStockMeltingMt().getId());
					  meltingIssDt.setDtRemark(stockCompDt.getComponent().getName());
				      meltingIssDt.setMetalRate(stockCompDt.getMetalRate());
					  meltingIssDt.setTranType("MELTINGISS");
					  meltingIssDt.setBarcode(stockMt.getBarcode());
					  save(meltingIssDt);
				}
				  
					 
					 stockMeltingDt.setCurrStk(false);
					 stockMeltingDt.setModiBy(principal.getName());
					 stockMeltingDt.setModiDt(new Date());
					 stockMeltingDtService.save(stockMeltingDt);
					 
					 
					 StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(stockMeltingDt.getBarcode(), true);
					 stockTran.setCurrStk(false);
					 stockTranService.save(stockTran);
					 
					 
					// stockMt.setCurrStk(false);
					 //stockMtService.save(stockMt);
					 
					 
				 
		}
		
		
		retVal="1";
		// TODO Auto-generated method stub
		return retVal;
	}

	@Override
	public String meltingIssDtListing(Principal principal, String pInvNo, Boolean canViewFlag) throws ParseException {
	
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("meltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		String disable = "";
		if(canViewFlag){
			disable = "disabled = 'disabled'";
		}else{
			System.err.println("in else view");
		}

		MeltingMt meltingMt = meltingMtService.findByInvNoAndDeactive(pInvNo, false);

		List<MeltingIssDt> meltingIssDts = findByMeltingMtAndDeactive(meltingMt, false);
		
		sb.append("{\"total\":").append(meltingIssDts.size()).append(",\"rows\": [");

		for (MeltingIssDt meltingIssDt : meltingIssDts) {
			
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(meltingIssDt.getCreatedDt()));

			sb.append("{\"id\":\"")
					.append(meltingIssDt.getId())
					.append("\",\"purity\":\"")
					.append((meltingIssDt.getPurity() != null ? meltingIssDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((meltingIssDt.getColor() != null ? meltingIssDt.getColor().getName() : ""))
					.append("\",\"freshMetalWt\":\"")
					.append((meltingIssDt.getIssFreshMetalWt() != null ? meltingIssDt.getIssFreshMetalWt() : ""))
					/*.append("\",\"usedMetalWt\":\"")
					.append((meltingIssDt.getIssUsedMetalWt() != null ? meltingIssDt.getIssUsedMetalWt() : "" ))*/
					.append("\",\"stones\":\"")
					.append(meltingIssDt.getStone())
					.append("\",\"location\":\"")
					.append((meltingIssDt.getDeptLocation() != null ? meltingIssDt.getDeptLocation().getName() : ""))
					.append("\",\"barcode\":\"")
					.append(meltingIssDt.getBarcode() !=null ?meltingIssDt.getBarcode():"")
					.append("\",\"carat\":\"")
					.append(meltingIssDt.getCarat())
					.append("\",\"bagNm\":\"")
					.append(meltingIssDt.getBagMt() !=null?meltingIssDt.getBagMt().getName():"")
					.append("\",\"remark\":\"")
					.append(meltingIssDt.getDtRemark() !=null?meltingIssDt.getDtRemark():"")
					.append("\",\"styleNo\":\"")
					.append(meltingIssDt.getBagMt() !=null?meltingIssDt.getBagMt().getOrderDt().getDesign().getMainStyleNo():"")
					.append("\",\"netWt\":\"")
					.append(meltingIssDt.getNetWt())
					.append("\",\"expcPureWt\":\"")
					.append(meltingIssDt.getExcpPureWt())
					.append("\",\"deactive\":\"")
					.append(meltingIssDt.getDeactive() ? "Yes" : "No");
			
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			if(!canViewFlag){
					sb.append("\",\"action1\":\"");
				
						if(principal.getName().equalsIgnoreCase("Administrator")){
							sb.append("<a href='javascript:editMeltingIssDt(").append(meltingIssDt.getId());
							sb.append(","+(meltingIssDt.getBagMt() !=null ? meltingIssDt.getBagMt().getId():0)+");'class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
						}else{
							if(currdate.equals(invDate)){
								sb.append("<a href='javascript:editMeltingIssDt(").append(meltingIssDt.getId());
								sb.append(","+(meltingIssDt.getBagMt() !=null ? meltingIssDt.getBagMt().getId():0)+");'class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							}else{
								sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
								sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							}
						}
						
					
			
					if(meltingIssDt.getBagMt() !=null && meltingIssDt.getRefTranMetalId() ==null) {
						sb.append("\",\"action2\":\"")
						.append("");
						
					}else {
							sb.append("\",\"action2\":\"");
					
						
						if(principal.getName().equalsIgnoreCase("Administrator")){
							sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingIssDt/delete/")
							.append(meltingIssDt.getId());
							
						}else{
							if(currdate.equals(invDate)){
								sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingIssDt/delete/")
								.append(meltingIssDt.getId());
							}else{
								sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
							}
						}
					
					sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(meltingIssDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");	
					}
					
					
			}	else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("");
			}
			}else{
				
				if(!canViewFlag){
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						if(principal.getName().equalsIgnoreCase("Administrator")){
							sb.append("<a href='javascript:editMeltingIssDt(").append(meltingIssDt.getId());
							sb.append(","+(meltingIssDt.getBagMt() !=null ? meltingIssDt.getBagMt().getId():0)+");'class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
						}else{
							if(currdate.equals(invDate)){
								sb.append("<a href='javascript:editMeltingIssDt(").append(meltingIssDt.getId());
								sb.append(","+(meltingIssDt.getBagMt() !=null ? meltingIssDt.getBagMt().getId():0)+");'class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							}else{
								sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
								sb.append(");' class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							}
						}
						
						
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					}
					
					
					
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						
						if(principal.getName().equalsIgnoreCase("Administrator")){
							sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingIssDt/delete/")
							.append(meltingIssDt.getId());
							
						}else{
							if(currdate.equals(invDate)){
								sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingIssDt/delete/")
								.append(meltingIssDt.getId());
							}else{
								sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
							}
						}
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(meltingIssDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			}	else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("");
			}
				
				
			}
					sb.append("\"},");
					
					
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@Override
	public MeltingIssDt findByTranTypeAndRefTranMetalIdAndDeactive(String tranType, Integer refTranMetalId,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return meltingIssDtRepositoty.findByTranTypeAndRefTranMetalIdAndDeactive(tranType, refTranMetalId, deactive);
	}

	@Override
	public List<MeltingIssDt> findByMeltingMtAndBarcodeAndDeactive(MeltingMt meltingMt,String barcode, Boolean deactive) {
		// TODO Auto-generated method stub
		return meltingIssDtRepositoty.findByMeltingMtAndBarcodeAndDeactive(meltingMt,barcode, deactive);
	}

}

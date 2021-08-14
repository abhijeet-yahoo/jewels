package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;


import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICastingDtRepository;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class CastingDtService implements ICastingDtService {

	@Autowired
	private ICastingDtRepository castingDtRepository;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ICastingMtService castingMtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IDepartmentService departmentService;


	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ILocationRightsService locationRightsService;

	@Autowired
	private UserService userService;
	
	@Override
	public List<CastingDt> findAll() {
		return castingDtRepository.findAll();
	}

	@Override
	public Page<CastingDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		return castingDtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(CastingDt castingDt) {
		castingDtRepository.save(castingDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
		castingDtRepository.delete(id);

	}

	@Override
	public Long count() {
		return castingDtRepository.count();
	}

	@Override
	public CastingDt findOne(int id) {
		return castingDtRepository.findOne(id);
	}

	@Override
	public List<CastingDt> findByCastingMtAndDeactive(CastingMt castingMt,Boolean deactive) {
		
		return castingDtRepository.findByCastingMtAndDeactive(castingMt, deactive);
	}

	@Override
	public List<CastingDt> findByCastingMtAndTransferAndDeactive(CastingMt castingMt,
			Boolean transfer,Boolean deactive) {
		return castingDtRepository.findByCastingMtAndTransferAndDeactive(castingMt, transfer,deactive);
	}
	
	
	@Override public List<CastingDt> findByBagMtAndDeactive(BagMt bagMt, Boolean deactive) {
		
		  return castingDtRepository.findByBagMtAndDeactive(bagMt,deactive); 
	
	}
	 
	@Override
	public String transferToCasting(JSONArray jsonTrfTblArray,Integer castMtId,Principal principal) {
		// TODO Auto-generated method stub
		
		
		Department castingDept = departmentService.findByName("Casting");
		
		Department readyFotCastingDept = departmentService.findByName("READY FOR CASTING");
		CastingMt castingMt = castingMtService.findOne(castMtId);

		for (int i = 0; i < jsonTrfTblArray.length(); i++) {
						
			
			JSONObject jsonObject =   (JSONObject) jsonTrfTblArray.get(i);
			
			TranMetal tranMetal =tranMetalService.findOne(Integer.parseInt(jsonObject.get("id").toString()));

				
			if(tranMetal.getCurrStk().equals(false)){
				continue;
			}
			
			
			List<TranMetal> tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMetal.getTranMt(), true);
			
			
			
			TranMt tranMt =tranMtService.findOne(tranMetal.getTranMt().getId());
			
			List<TranDt> tranDts =tranDtService.findByTranMtAndCurrStkAndPartNm(tranMt, true,tranMetal.getPartNm());
			
			if(tranMetals.size()==1){
				tranMt.setCurrStk(false);
				tranMt.setDeptTo(castingDept);
				tranMt.setIssueDate(new java.util.Date());
				tranMt.setModiBy(principal.getName());
				tranMt.setModiDate(new java.util.Date());
				tranMtService.save(tranMt);

			}
			
			TranMt mt = tranMtService.findByBagMtAndDepartmentAndCurrStk(tranMt.getBagMt(), castingDept, true);
			TranMt tranMtNew = new TranMt();

			if(mt ==null){
				
				tranMtNew.setBagMt(tranMt.getBagMt());
				tranMtNew.setPcs(tranMt.getPcs());
				tranMtNew.setCurrStk(true);
				tranMtNew.setTrandate(new java.util.Date());
				tranMtNew.setCreatedBy(principal.getName());
				tranMtNew.setCreatedDate(new java.util.Date());
				tranMtNew.setRecWt(tranMt.getRecWt());
				tranMtNew.setDepartment(castingDept);
				tranMtNew.setDeptFrom(tranMt.getDepartment());
				tranMtNew.setOrderMt(tranMt.getOrderMt());
				tranMtNew.setOrderDt(tranMt.getOrderDt());
				tranMtNew.setRefMtId(tranMt.getId());
				tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
				tranMtService.save(tranMtNew);
				
				
			}else{
				
				tranMtNew = mt;
			}
		
			
			
			
			
			
			TranMetal tranMetal2 = new TranMetal();
			tranMetal2.setBagMt(tranMetal.getBagMt());
			tranMetal2.setColor(tranMetal.getColor());
			tranMetal2.setMainMetal(tranMetal.getMainMetal());
			tranMetal2.setCreatedBy(principal.getName());
			tranMetal2.setCreatedDate(new Date());
			tranMetal2.setCurrStk(true);
			tranMetal2.setDepartment(castingDept);
			tranMetal2.setDeptFrom(readyFotCastingDept);
			tranMetal2.setMetalPcs(tranMetal.getMetalPcs());
			tranMetal2.setPartNm(tranMetal.getPartNm());
			tranMetal2.setPurity(tranMetal.getPurity());
			tranMetal2.setPurityConv(tranMetal.getPurity().getPurityConv());
			tranMetal2.setRefTranMetalId(tranMetal.getId());
			tranMetal2.setTime(new java.sql.Time(new java.util.Date().getTime()));
			tranMetal2.setTranDate(new Date());
			tranMetal2.setTranMt(tranMtNew);
			tranMetalService.save(tranMetal2);
			
			// tranmetal False record
			tranMetal.setCurrStk(false);
			tranMetal.setDeptTo(castingDept);
			tranMetal.setIssDate(new java.util.Date());
			tranMetal.setModiBy(principal.getName());
			tranMetal.setModiDate(new java.util.Date());
			tranMetalService.save(tranMetal);
			
			
			// adding the new record in castdt
			
			CastingDt castDt = new CastingDt();
			castDt.setCreatedBy(principal.getName());
			castDt.setCreatedDate(new java.util.Date());
			castDt.setQty(tranMtNew.getPcs());
			castDt.setRefMtId(tranMtNew.getId());
			castDt.setBagMt(tranMtNew.getBagMt());
			castDt.setCastingMt(castingMt);
			castDt.setMetalRate(castingMt.getMetalRate());
			castDt.setDepartment(castingDept);
			castDt.setOrderMt(tranMtNew.getOrderMt());
			castDt.setOrderDt(tranMtNew.getOrderDt());
			castDt.setRefTranMetalId(tranMetal2.getId());
			save(castDt);

			
			
			
			
			for(TranDt tranDt :tranDts){
				
				
				// adding the new record
				TranDt tranDtNew = new TranDt();
				tranDtNew.setBagMt(tranDt.getBagMt());
				tranDtNew.setPcs(tranDt.getPcs());
				tranDtNew.setCurrStk(true);
				tranDtNew.setCreatedBy(principal.getName());
				tranDtNew.setCreatedDate(new java.util.Date());
				tranDtNew.setDepartment(castingDept);
				tranDtNew.setDeptFrom(tranDt.getDepartment());
				tranDtNew.setRefDtId(tranDt.getId());
				tranDtNew.setBagSrNo(tranDt.getBagSrNo());
				tranDtNew.setCarat(tranDt.getCarat());
				tranDtNew.setSieve(tranDt.getSieve());
				tranDtNew.setSize(tranDt.getSize());
				tranDtNew.setStone(tranDt.getStone());
				tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
				tranDtNew.setTranDate(new java.util.Date());
				tranDtNew.setQuality(tranDt.getQuality());
				tranDtNew.setSetting(tranDt.getSetting());
				if (tranDt.getSettingType() == null) {
					tranDtNew.setSettingType(null);
				} else {
					tranDtNew.setSettingType(tranDt.getSettingType());
				}
				tranDtNew.setShape(tranDt.getShape());
				tranDtNew.setSizeGroup(tranDt.getSizeGroup());
				tranDtNew.setTranMt(tranMtNew);
				tranDtNew.setStoneType(tranDt.getStoneType());
				tranDtNew.setPartNm(tranDt.getPartNm());
				tranDtNew.setRate(tranDt.getRate());
				tranDtNew.setFactoryRate(tranDt.getFactoryRate());
				tranDtNew.setAvgRate(tranDt.getAvgRate());
				tranDtNew.setTransferRate(tranDt.getTransferRate());
				
				tranDtService.save(tranDtNew);

				// editing the existing record
				tranDt.setCurrStk(false);
				tranDt.setDeptTo(castingDept);
				tranDt.setIssDate(new java.util.Date());
				tranDt.setModiBy(principal.getName());
				tranDt.setModiDate(new java.util.Date());
				tranDtService.save(tranDt);

				
				
			}
			


		}
		
		return "1";
	}

	@Override
	public String castingToDepartmentTransfer(String pOIds,CastingDt castingDt,String metalWtt,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		LocationRights locationRights = locationRightsService.findByUserAndDeactiveAndDefaultFlg(user, false, true);
		
		
		String[] data1 = pOIds.split(",");
		
		String[] metalData = metalWtt.split(",");
		
		
		for (int i = 0; i < metalData.length; i++) {
			if(Double.parseDouble(metalData[i].toString())<=0){
				
				return "Error : Wt Can Not Be Zero ";
				
			}
		}
		
		
		
		
		
		for (int i = 0; i < data1.length; i++) {

			CastingDt castDt = findOne(Integer.parseInt(data1[i]));
			Boolean transferFlag = castDt.getTransfer();
			
			/*if(department.getAllowZeroWt().equals(false)){
				Double metalWtCheck = Double.parseDouble(metalData[i]);
				if(metalWtCheck <= 0){
					continue;
				}
			}*/
			
			//Double mtlRate=metalTranService.getMetalRate(castDt.getCastingMt().getPurity().getId(), castDt.getCastingMt().getColor().getId(), castDt.getCastingMt().getDepartment().getId(), castDt.getMetalWt());
			
			TranMetal tranMetal =tranMetalService.findOne(castDt.getRefTranMetalId());
						
			Double vmetalWt =Double.parseDouble(metalData[i]);
			
			if(tranMetal.getCurrStk().equals(false)){
				continue;
			}
			
				

			if (transferFlag != true) {
				
				TranMt tranMt =tranMtService.findOne(tranMetal.getTranMt().getId());
				List<TranDt> tranDts =tranDtService.findByTranMtAndCurrStkAndPartNm(tranMt, true,tranMetal.getPartNm());
				
				TranMt tranMt2 =tranMtService.findByBagMtAndDepartmentAndCurrStk(castDt.getBagMt(), castingDt.getDepartment(), true);
				
				
				
				
					TranMt tranMtNew=null;
				
				if(tranMt2 ==null){
					// adding new record
					tranMtNew = new TranMt();
					tranMtNew.setBagMt(tranMt.getBagMt());
					tranMtNew.setPcs(tranMt.getPcs());
					tranMtNew.setCurrStk(true);
					tranMtNew.setTrandate(new java.util.Date());
					tranMtNew.setCreatedBy(principal.getName());
					tranMtNew.setCreatedDate(new java.util.Date());
					tranMtNew.setDepartment(castingDt.getDepartment());
					tranMtNew.setDeptFrom(tranMt.getDepartment());
					tranMtNew.setOrderMt(tranMt.getOrderMt());
					tranMtNew.setOrderDt(tranMt.getOrderDt());
					tranMtNew.setRefMtId(tranMt.getId());
					tranMtNew.setRecWt( Math.round((vmetalWt)*1000.0)/1000.0);
					tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
					tranMtService.save(tranMtNew);
					
					
					
				}else{
					
					tranMt2.setRecWt(Math.round((tranMt2.getRecWt()+vmetalWt)*1000.0)/1000.0);
					
					
					tranMtService.save(tranMt2);
					
					
					
				}
				
				List<TranMetal> tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMetal.getTranMt(), true);
				
				if(tranMetals.size()==1){
				tranMt.setCurrStk(false);
				tranMt.setDeptTo(castingDt.getDepartment());
				tranMt.setIssueDate(new java.util.Date());
				tranMt.setModiBy(principal.getName());
				tranMt.setModiDate(new java.util.Date());
				tranMtService.save(tranMt);
				}
				
				
				
				
				
				//tranmetal Record
				
				TranMetal tranMetal2 = new TranMetal();
				tranMetal2.setBagMt(tranMetal.getBagMt());
				tranMetal2.setColor(tranMetal.getColor());
				tranMetal2.setMainMetal(tranMetal.getMainMetal());
				tranMetal2.setCreatedBy(principal.getName());
				tranMetal2.setCreatedDate(new Date());
				tranMetal2.setCurrStk(true);
				tranMetal2.setDepartment(castingDt.getDepartment());
				tranMetal2.setDeptFrom(tranMetal.getDepartment());
				tranMetal2.setMetalPcs(tranMetal.getMetalPcs());
				tranMetal2.setRefTranMetalId(tranMetal.getId());
				tranMetal2.setMetalWeight(Math.round((vmetalWt)*1000.0)/1000.0);
				tranMetal2.setPartNm(tranMetal.getPartNm());
				tranMetal2.setPurity(tranMetal.getPurity());
				tranMetal2.setPurityConv(tranMetal.getPurityConv());
				tranMetal2.setTime(new java.sql.Time(new java.util.Date().getTime()));
				tranMetal2.setTranDate(new Date());
				tranMetal2.setMetalRate(castDt.getCastingMt().getMetalRate());
				if(tranMt2 ==null){
					tranMetal2.setTranMt(tranMtNew);
				}else{
					tranMetal2.setTranMt(tranMt2);

				}
				
				tranMetalService.save(tranMetal2);
				
				/*tranMetal2.setTranMt(tranMtNew);
				tranMetalService.save(tranMetal2);*/
				
				// tranmetal False record
				tranMetal.setCurrStk(false);
				tranMetal.setDeptTo(castingDt.getDepartment());
				tranMetal.setIssDate(new java.util.Date());
				tranMetal.setModiBy(principal.getName());
				tranMetal.setModiDate(new java.util.Date());
				tranMetalService.save(tranMetal);
				
					
				
				
				
				
				
				
				
				castDt.setMetalWt(Math.round((vmetalWt)*1000.0)/1000.0);
				castDt.setTransfer(true);
				castDt.setTransferDate(new java.util.Date());
				castDt.setDeptTo(castingDt.getDepartment());
				castDt.setModiBy(principal.getName());
				castDt.setModiDate(new java.util.Date());
				
				if(tranMt2 ==null){
					castDt.setTransferTranMtId(tranMtNew.getId());
				}else{
					castDt.setTransferTranMtId(tranMt2.getId());

				}

				save(castDt);
				
				
				

			
				
				
				//-----new record for metal tran
				
				MetalTran metalTran = new MetalTran();
				
				metalTran.setColor(castDt.getCastingMt().getColor());
				metalTran.setPurity(castDt.getCastingMt().getPurity());
				metalTran.setInOutFld("D");
				metalTran.setBagMt(castDt.getBagMt());
				metalTran.setBalance(castDt.getMetalWt() * -1);
				metalTran.setMetalWt(castDt.getMetalWt());
				metalTran.setDeptLocation(castDt.getCastingMt().getDepartment());
				metalTran.setPurityConv(castDt.getCastingMt().getPurity().getPurityConv());
				metalTran.setRefTranId(castDt.getCastingMt().getId());
				metalTran.setTranType("Casting");
				metalTran.setRemark("");
				metalTran.setPartNm(tranMetal.getPartNm());
				metalTran.setDepartment(null);
				metalTran.setPcsWt(true);
				metalTran.setCreatedBy(principal.getName());
				metalTran.setCreatedDt(new java.util.Date());
				metalTran.setTranDate(new Date());
				metalTran.setMetalRate(castDt.getCastingMt().getMetalRate());
				
				if(tranMt2 ==null){
					metalTran.setTranMtId(tranMtNew.getId());
				}else{
					metalTran.setTranMtId(tranMt2.getId());

				}
				metalTranService.save(metalTran);
				
				
				
		
				if (tranDts != null) {
					Double vCarat = 0.0;

					for (TranDt tranDt : tranDts) {

						vCarat +=tranDt.getCarat();
						// adding the new record
						TranDt tranDtNew = new TranDt();
						tranDtNew.setBagMt(tranDt.getBagMt());
						tranDtNew.setPcs(tranDt.getPcs());
						tranDtNew.setCurrStk(true);
						tranDtNew.setCreatedBy(principal.getName());
						tranDtNew.setCreatedDate(new java.util.Date());
						tranDtNew.setDepartment(castingDt.getDepartment());
						tranDtNew.setDeptFrom(tranDt.getDepartment());
						tranDtNew.setRefDtId(tranDt.getId());
						tranDtNew.setBagSrNo(tranDt.getBagSrNo());
						tranDtNew.setCarat(tranDt.getCarat());
						tranDtNew.setSieve(tranDt.getSieve());
						tranDtNew.setSize(tranDt.getSize());
						tranDtNew.setStone(tranDt.getStone());
						tranDtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
						tranDtNew.setTranDate(new java.util.Date());
						tranDtNew.setQuality(tranDt.getQuality());
						tranDtNew.setSetting(tranDt.getSetting());
						tranDtNew.setPartNm(tranDt.getPartNm());
						if (tranDt.getSettingType() == null) {
							tranDtNew.setSettingType(null);
						} else {
							tranDtNew.setSettingType(tranDt.getSettingType());
						}
						tranDtNew.setShape(tranDt.getShape());
						tranDtNew.setSizeGroup(tranDt.getSizeGroup());
						tranDtNew.setStoneType(tranDt.getStoneType());
						
						if(tranMt2 ==null){
							
							tranDtNew.setTranMt(tranMtNew);

							
						}else{
							tranDtNew.setTranMt(tranMt2);
							
						}
						tranDtNew.setRate(tranDt.getRate());
						tranDtNew.setFactoryRate(tranDt.getFactoryRate());
						tranDtNew.setAvgRate(tranDt.getAvgRate());
						tranDtNew.setTransferRate(tranDt.getTransferRate());
						

						tranDtService.save(tranDtNew);

						// editing the existing record
						tranDt.setCurrStk(false);
						tranDt.setDeptTo(castingDt.getDepartment());
						tranDt.setIssDate(new java.util.Date());
						tranDt.setModiBy(principal.getName());
						tranDt.setModiDate(new java.util.Date());
						tranDtService.save(tranDt);

					}
					
					
					tranMetal2.setMetalWeight(Math.round((tranMetal2.getMetalWeight()-(vCarat/5))*1000.0)/1000.0 );
					tranMetalService.save(tranMetal2);
				}

			}// ending first if() in for loop
			

		}// ending main for loop

		// TODO Auto-generated method stub
		return "1";
	}

	@Override
	public String CastingDelete(Integer id) {
		
	String retVal="-1";
		
		CastingDt castingDt = findOne(id);
		
		if(castingDt.getTransfer().equals(false)){
			
			
			TranMt tranMtNew = tranMtService.findByIdAndCurrStk(castingDt.getRefMtId(),true);
			TranMt tranMtOld = tranMtService.findOne(tranMtNew.getRefMtId());
			
			
			TranMetal tranMetal =tranMetalService.findOne(castingDt.getRefTranMetalId());
			TranMetal tranMetalOld = tranMetalService.findOne(tranMetal.getRefTranMetalId());
			
			List<TranDt>tranDts = tranDtService.findByTranMtAndCurrStkAndPartNm(tranMtNew, true, tranMetal.getPartNm());
			
			List<TranDt>tranDtOlds = tranDtService.findByTranMtAndCurrStkAndPartNm(tranMtOld, false, tranMetal.getPartNm());

			List<TranMetal> tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMtNew, true);
			
			
			for(TranDt tranDt :tranDts){
				
				tranDtService.delete(tranDt.getId());
				
				
			}
			
			
			for(TranDt tranDt : tranDtOlds){
				tranDt.setCurrStk(true);
				tranDtService.save(tranDt);
				
			}
			
			
			tranMetalOld.setCurrStk(true);
			tranMetalService.save(tranMetalOld);
			
			tranMetalService.delete(tranMetal.getId());
			
			if(tranMetals.size()==1){
				tranMtService.delete(tranMtNew.getId());
			}
			
			
			tranMtOld.setCurrStk(true);
			tranMtService.save(tranMtOld);
			
			
			delete(castingDt.getId());
			
			retVal="1";
			
		}else{
			
			TranMt tranMtNew1 = tranMtService.findByRefMtIdAndCurrStk(castingDt.getRefMtId(),true);
			if(tranMtNew1 !=null){
				
				TranMt tranMtNew = tranMtService.findByIdAndCurrStk(castingDt.getRefMtId(),false);
				TranMt tranMtOld = tranMtService.findOne(tranMtNew.getRefMtId());
				
				
				
				TranMetal tranMetal =tranMetalService.findOne(castingDt.getRefTranMetalId());
				TranMetal tranMetal1 =tranMetalService.findByRefTranMetalIdAndCurrStk(tranMetal.getId(), true);
				TranMetal tranMetalOld = tranMetalService.findOne(tranMetal.getRefTranMetalId());
				
				
				List<TranDt>tranDts1 = tranDtService.findByTranMtAndCurrStkAndPartNm(tranMtNew1, true, tranMetal.getPartNm());
				List<TranDt>tranDts = tranDtService.findByTranMtAndCurrStkAndPartNm(tranMtNew, false, tranMetal.getPartNm());
				List<TranDt>tranDtOlds = tranDtService.findByTranMtAndCurrStkAndPartNm(tranMtOld, false, tranMetal.getPartNm());

				List<TranMetal> tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMtNew1, true);
				
				
				
				
				for(TranDt tranDt :tranDts){
					
					tranDtService.delete(tranDt.getId());
					
					
				}
				
				
				for(TranDt tranDt :tranDts1){
					
					tranDtService.delete(tranDt.getId());
					
					
				}
				
				for(TranDt tranDt : tranDtOlds){
					tranDt.setCurrStk(true);
					tranDtService.save(tranDt);
					
				}
				
				
				
				
				tranMetalOld.setCurrStk(true);
				tranMetalService.save(tranMetalOld);
				
				tranMetalService.delete(tranMetal.getId());
				tranMetalService.delete(tranMetal1.getId());
				
				
				
				if(tranMetals.size()==1){
					tranMtService.delete(tranMtNew.getId());
					tranMtService.delete(tranMtNew1.getId());
				}
				
				
				tranMtOld.setCurrStk(true);
				tranMtService.save(tranMtOld);
				
				
				
				MetalTran metalTran =metalTranService.findByTranTypeAndBagMtAndPcsWtAndDeactiveAndPartNm("Casting", castingDt.getBagMt(), true,false, tranMetal.getPartNm());
				
				metalTranService.delete(metalTran.getId());
				
				
				
				List<StoneTran>stoneTrans =stoneTranService.findByTranMtIdAndDeactive(tranMtNew1.getId(),false);
				
				for(StoneTran stoneTran:stoneTrans){
					
					stoneTranService.delete(stoneTran.getId());
					
				}
				
				List<MetalTran>metalTrans =metalTranService.findByTranMtIdAndDeactive(tranMtNew1.getId(), false);
				
				for(MetalTran metalTran2 :metalTrans){
					
					metalTranService.delete(metalTran2.getId());
					
				}
				
				
				List<CompTran>compTrans =compTranService.findByTranMtIdAndDeactive(tranMtNew1.getId(),false);
				
				for(CompTran compTran:compTrans){
					
					compTranService.delete(compTran.getId());
					
				}
				
				
				
				
				delete(castingDt.getId());
				
				
				retVal="1";
				
				
			}
			
			
			
			
		}
		
		
	
			
		

		return retVal;
		
	}

	@Override
	public CastingDt findByRefMtIdAndDeactive(Integer refMtId,Boolean deactive) {
		// TODO Auto-generated method stub
		return castingDtRepository.findByRefMtIdAndDeactive(refMtId, false);
	}

	@Override
	public List<CastingDt> getCastingDtListBagWise(Integer castMtId) {
		
		QCastingDt qCastingDt = QCastingDt.castingDt;
		JPAQuery query = new JPAQuery(entityManager);

		List<CastingDt> castingDts =query.from(qCastingDt).
				where(qCastingDt.deactive.eq(false).and(qCastingDt.castingMt.id.eq(castMtId))).orderBy(qCastingDt.bagMt.name.asc()).list(qCastingDt);

		return castingDts;

	}

	@Override
	public List<CastingDt> findByTransferTranMtIdAndDeactive(Integer transferTranMtId, Boolean deactive) {
		// TODO Auto-generated method stub
		return castingDtRepository.findByTransferTranMtIdAndDeactive(transferTranMtId, deactive);
	}

	@Override
	public String addCastingBagListing(Integer castMtId) {
		
		StringBuilder sb = new StringBuilder();

		
		
		CastingMt castingMt = castingMtService.findOne(castMtId);
		Department department = departmentService.findByName("READY FOR CASTING");
				
		List<TranMetal>tranMetals =tranMetalService.findByDepartmentAndCurrStkAndPurityAndColor(department, true, castingMt.getPurity(),castingMt.getColor());
		
		sb.append("{\"total\":").append(tranMetals.size()).append(",\"rows\": [");
		
		for (TranMetal tranMetal : tranMetals) { 
			
			if(!tranMetal.getBagMt().getOrderMt().getOrderClose()) {
				sb.append("{\"bagId\":\"")
				.append(tranMetal.getBagMt().getId())
				.append("\",\"id\":\"")
				.append(tranMetal.getId())
				.append("\",\"party\":\"")
				.append(tranMetal.getBagMt().getOrderMt().getParty().getPartyCode())
				.append("\",\"orderNo\":\"")
				.append(tranMetal.getBagMt().getOrderMt().getInvNo())
				.append("\",\"bagNo\":\"")
				.append(tranMetal.getBagMt().getName())
				.append("\",\"style\":\"")
				.append(tranMetal.getBagMt().getOrderDt().getDesign().getMainStyleNo())
				.append("\",\"partName\":\"")
				.append(tranMetal.getPartNm().getFieldValue())
				.append("\",\"kt\":\"")
				.append(tranMetal.getPurity().getName())
				.append("\",\"color\":\"")
				.append(tranMetal.getColor().getName())
				.append("\",\"pcs\":\"")
				.append(tranMetal.getBagMt().getQty())
				.append("\",\"partPcs\":\"")
				.append(tranMetal.getMetalPcs())
				.append("\"},");
	}
			}
			
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		
		
		return str;
		
	}
	
	
	@Override
	public String castingDtListing(String treeNo) {
		CastingMt castingMtt = castingMtService.findOne(Integer.parseInt(treeNo));
		StringBuilder sb = new StringBuilder();
		
		List<CastingDt> castingDts =  getCastingDtListBagWise(castingMtt.getId());
		
		sb.append("{\"total\":").append(castingDts.size()).append(",\"rows\": [");

		for (CastingDt castingDt : castingDts) {
			
			TranMetal tranMetal =tranMetalService.findOne(castingDt.getRefTranMetalId());

			sb.append("{\"id\":\"")
					.append(castingDt.getId())
					.append("\",\"bagNo\":\"")
					.append((castingDt.getBagMt() == null ? "" : castingDt.getBagMt().getName()))
					.append("\",\"styleNo\":\"")
					.append((castingDt.getOrderDt() == null ? "" : castingDt.getOrderDt().getDesign().getMainStyleNo()))
					.append("\",\"qty\":\"")
					.append(castingDt.getQty())
					.append("\",\"metalWt\":\"")
					.append(castingDt.getMetalWt())
					.append("\",\"partName\":\"")
					.append(tranMetal.getPartNm().getFieldValue())
					.append("\",\"partPcs\":\"")
					.append(tranMetal.getMetalPcs())
					.append("\",\"transfer\":\"")
					.append(castingDt.getTransfer() == null ? "" : castingDt.getTransfer() ? "Yes" : "No")
					.append("\",\"action1\":\"")
					.append("<a onClick='javascript:deleteCastingDt(event,")
					.append(castingDt.getId())
					.append(","+castingDt.getTransfer())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(castingDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

}
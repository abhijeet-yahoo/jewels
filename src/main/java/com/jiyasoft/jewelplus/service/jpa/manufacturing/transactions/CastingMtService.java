package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCastingMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICastingMtRepository;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class CastingMtService implements ICastingMtService {

	@Autowired
	private ICastingMtRepository castingMtRepository;
	
	@Autowired
	private ILocationRightsService locationRightsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ICastingDtService castingDtService;
	
	@Autowired
	private ICastingCompDtService castingCompDtService;




	@Override
	public List<CastingMt> findAll() {
		return castingMtRepository.findAll();
	}

	@Override
	public Page<CastingMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		return castingMtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));

	}
	
	
	@Override
	public Page<CastingMt> findByTreeNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QCastingMt qCastingMt = QCastingMt.castingMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qCastingMt.deactive.eq(false);
			} else {
				expression = qCastingMt.deactive.eq(false).and(
						qCastingMt.treeNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qCastingMt.treeNo.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}
		
		
		order = "desc";
		
		Page<CastingMt> castingMtList = (Page<CastingMt>) castingMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return castingMtList;
		
	}
	
	
	
	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QCastingMt qCastingMt = QCastingMt.castingMt;
		BooleanExpression expression = qCastingMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qCastingMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("treeNo") && colValue != null) {
				expression = qCastingMt.deactive.eq(false).and(
						qCastingMt.treeNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("treeNo"))
					&& colValue != null) {
				expression = qCastingMt.treeNo.like(colValue + "%");
			}
		}

		return castingMtRepository.count(expression);
	}

	
	
	
	@Override
	public void save(CastingMt castingMt) {
		castingMtRepository.save(castingMt);

	}

	@Override
	public void delete(int id) {
		CastingMt castingMt = castingMtRepository.findOne(id);
		castingMt.setDeactive(true);
		castingMt.setDeactiveDt(new java.util.Date());
		castingMtRepository.save(castingMt);

	}

	@Override
	public Long count() {
		return castingMtRepository.count();
	}

	@Override
	public CastingMt findOne(int id) {
		return castingMtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getCastingMtList(Date date) {

		Map<Integer, String> castingMtMap = new HashMap<Integer, String>();
		List<CastingMt> castingMtList = findActivecastingMt(date);

		for (CastingMt castingMt : castingMtList) {
			
			castingMtMap.put(castingMt.getId(), castingMt.getTreeNo());
		}

		return castingMtMap;
	}

	@Override
	public List<CastingMt> findActivecastingMt(Date date) {

		QCastingMt qCastingMt = QCastingMt.castingMt;
		BooleanExpression expression = null;

		if (date == null) {
			expression = qCastingMt.deactive.eq(false);
		} else {
			expression = qCastingMt.cDate.eq(date).and(
					qCastingMt.deactive.eq(false));
		}

		List<CastingMt> castingMtList = (List<CastingMt>) castingMtRepository
				.findAll(expression);

		

		return castingMtList;

	}

	@Override
	public CastingMt findByCDateAndTreeNo(Date cDate, String treeNo) {

		return castingMtRepository.findByCDateAndTreeNo(cDate, treeNo);
	}

	@Override
	public CastingMt findByUniqueId(Long uniqueId) {

		return castingMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<CastingMt> findByCDate(Date cDAte) {
		
		List<CastingMt> castingMtList = (List<CastingMt>) castingMtRepository
				.findByCDate(cDAte);

		
		return castingMtList;
	}

	@Override
	public String castingMtSave(CastingMt castingMt, Integer id, Double freshIssWt, Double prevIssWt, Double pcsWt,
			Double prevPcsWt, Integer prevKt, Integer prevColor, Double vReqWt, Principal principal,RedirectAttributes redirectAttributes) {
		
		String action = "added";
		String retVal = "/jewels/manufacturing/transactions/castingMt/add.html";
		Date aDate = null;
		
		User user = userService.findByName(principal.getName());


		MetalTran metalTran = null;
		
		
		
		Double tempBal 		= 0.0;
	
		Integer locationId = 0;
	//	Integer locationId = departmentService.findByName("Central").getId();
		
		LocationRights locationRights = locationRightsService.findByUserAndDeactiveAndDefaultFlg(user, false, true);
		if(locationRights != null) {
			locationId = locationRights.getDepartment().getId();
			castingMt.setDepartment(locationRights.getDepartment());
		}else {
			locationId = departmentService.findByName("Central").getId();
			castingMt.setDepartment(departmentService.findByName("Central"));
		}
		
		
		if(freshIssWt > 0){
			tempBal = metalTranService.getStockBalance(castingMt.getPurity().getId(), castingMt.getColor().getId(), locationId);
			if(tempBal == null){
				return retVal = "-1";
			}
		}else{
			tempBal = 0.0;
		}
		
				
		Purity purConvObj = purityService.findOne(castingMt.getPurity().getId());
		

		if (id == null || id.equals("") || (id != null && id == 0)) {
			aDate = new java.util.Date();
			castingMt.setCreatedBy(principal.getName());
			castingMt.setCreatedDate(new java.util.Date());
			castingMt.setDeactive(false);
			castingMt.setUniqueId(aDate.getTime());
			castingMt.setReqWt(vReqWt);
			
			
			
			if(purConvObj.getPurityConv() != null){
				castingMt.setPurityConv(purConvObj.getPurityConv());
			}else{
				castingMt.setPurityConv(0.0);
			}
			

			if (tempBal < freshIssWt) {
				return retVal = "-1";
			}
			
		/*	if (Double.parseDouble(df.format(tempUsedBal)) < usedIssWt) {
				return retVal = "-2";
			}*/
			

			metalTran = new MetalTran();
			retVal = "111";

		} else {
			
			CastingMt castingMtOld = findOne(id);
			castingMt.setModiBy(principal.getName());
			castingMt.setModiDate(new java.util.Date());
			castingMt.setReqWt(vReqWt);
			castingMt.setMetalRate(castingMtOld.getMetalRate());	
			
		
			
			if(purConvObj.getPurityConv() != null){
				castingMt.setPurityConv(purConvObj.getPurityConv());
			}else{
				castingMt.setPurityConv(0.0);
			}

			

			if(castingMt.getPurity().getId().equals(prevKt) && castingMt.getColor().getId().equals(prevColor)){
				Double difference = freshIssWt - prevIssWt;
				if (Math.round(tempBal*1000.0/1000.0) < difference) {
					return retVal = "-1";
				}
	
			
			}else{
				
				
				if (Math.round(tempBal*1000.0/1000.0) < freshIssWt) {
					return retVal = "-1";
				}
	
				
			}
				
				
			
			
			
			if(prevPcsWt > 0 && pcsWt<prevPcsWt){
				
				Double tempPcsWtBal=0.0;
				
				List<CastingDt>castingDts =castingDtService.findByCastingMtAndTransferAndDeactive(castingMt, true, false);
				for(CastingDt castingDt :castingDts) {
					tempPcsWtBal +=castingDt.getMetalWt();
				}
				
				
				List<CastingCompDt>castingCompDts =castingCompDtService.findByCastingMtAndDeactive(castingMt, false);
				
				for(CastingCompDt castingCompDt :castingCompDts) {
					tempPcsWtBal +=castingCompDt.getMetalWt();
					
				}
						
				// tempPcsWtBal = metalTranService.getPcsWtStockBalance(castingMt.getPurity().getId(), castingMt.getColor().getId(),locationId);
			
				/*
				 * if (tempPcsWtBal == null || tempPcsWtBal < 0) { tempPcsWtBal = 0.0; }
				 */
	
				
				if(pcsWt <tempPcsWtBal ) {
					return retVal = "-3";
				}
				
				
				
				/*
				 * if(castingMt.getPurity().getId().equals(prevKt) &&
				 * castingMt.getColor().getId().equals(prevColor)){ Double pcsWtDifference =
				 * prevPcsWt- pcsWt ; if (Double.parseDouble(df.format(tempPcsWtBal)) <
				 * pcsWtDifference) { return retVal = "-3"; } }else{
				 * 
				 * if (Double.parseDouble(df.format(tempPcsWtBal)) < pcsWt) { return retVal =
				 * "-3"; }
				 * 
				 * }
				 */
			
			}
			

			action = "updated";
			retVal = "/jewels/manufacturing/transactions/castingMt.html";
		}

	
		
	
		
		save(castingMt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

	CastingMt casting = findByUniqueId(castingMt.getUniqueId());
		
		
		
		Double mtlRate =0.0;
		
		if(casting.getFreshIssWt()>0) {
			
			
			if(action.equalsIgnoreCase("updated")) {
				if(casting.getMetalRate() >0) {
					mtlRate = casting.getMetalRate();
				}else {
					
					if(locationRights != null) {
						mtlRate=metalTranService.getMetalRate(castingMt.getPurity().getId(), castingMt.getColor().getId(), 
								locationRights.getDepartment().getId(), casting.getFreshIssWt());
						
						}else {
							
							mtlRate=metalTranService.getMetalRate(castingMt.getPurity().getId(), castingMt.getColor().getId(),  
									departmentService.findByName("Central").getId(), casting.getFreshIssWt());
						}
				}
			
			}else {
				
				if(locationRights != null) {
					mtlRate=metalTranService.getMetalRate(castingMt.getPurity().getId(), castingMt.getColor().getId(), 
							locationRights.getDepartment().getId(), casting.getFreshIssWt());
					
					}else {
						
						mtlRate=metalTranService.getMetalRate(castingMt.getPurity().getId(), castingMt.getColor().getId(),  
								departmentService.findByName("Central").getId(), casting.getFreshIssWt());
					}
			}
			
		}
		
	
		
		
		
		castingMt.setMetalRate(mtlRate != null ? mtlRate :0.0);
		
		save(castingMt);

		//for freshIssue Wt
		List<MetalTran> freshIssObj = metalTranService.findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndSrNoAndDeactive(casting.getId(), "Casting", false, false, 1, false);
		if(freshIssObj.size() > 0){
			//edit code here
			if(casting.getFreshIssWt() == 0){
				for(MetalTran metFreshDel : freshIssObj){
					metalTranService.delete(metFreshDel.getId());
				}
			}else{
				for(MetalTran metFreshEdit:freshIssObj){
					Double bal = metFreshEdit.getBalance();
					if(bal < 0 ){
						//debit entry
					if(locationRights != null) {
						metFreshEdit.setDeptLocation(locationRights.getDepartment());
					}else {
						metFreshEdit.setDeptLocation(departmentService.findByName("Central"));
					}
						
						metFreshEdit.setDepartment(departmentService.findByName("Casting"));
						metFreshEdit.setInOutFld("D");
						metFreshEdit.setBalance(casting.getFreshIssWt() * -1);
					}else{
						//credit entry
						metFreshEdit.setDeptLocation(departmentService.findByName("Casting"));
						if(locationRights != null) {
							metFreshEdit.setDepartment(locationRights.getDepartment());
						}else {
							metFreshEdit.setDepartment(departmentService.findByName("Central"));
						}
								
						metFreshEdit.setInOutFld("C");
						metFreshEdit.setBalance(casting.getFreshIssWt());
					}
					metFreshEdit.setPcsWt(false);
					metFreshEdit.setStubWt(false);
					metFreshEdit.setPurity(casting.getPurity());
					metFreshEdit.setColor(casting.getColor());
					metFreshEdit.setMetalWt(casting.getFreshIssWt());
					metFreshEdit.setPurityConv(casting.getPurityConv());
					metFreshEdit.setTranType("Casting");
					metFreshEdit.setRefTranId(casting.getId());
					metFreshEdit.setModiDt(new java.util.Date());
					metFreshEdit.setModiBy(principal.getName());
					metFreshEdit.setDeactive(false);
					metFreshEdit.setTranDate(casting.getcDate());
					metFreshEdit.setSrNo(1);
					metFreshEdit.setMetalRate(mtlRate != null ? mtlRate :0.0);
					metalTranService.save(metFreshEdit);	
					
				}
			}
		}else{
			//add code here
			if(casting.getFreshIssWt() > 0){
				for(int i=1; i<3; i++){
					 metalTran = new MetalTran();
					if(i == 1){
						//debit entry
						if(locationRights != null) {
							metalTran.setDeptLocation(locationRights.getDepartment());
						}else {
							metalTran.setDeptLocation(departmentService.findByName("Central"));
						}
					//	metalTran.setDeptLocation(departmentService.findByName("Central"));
						metalTran.setDepartment(departmentService.findByName("Casting"));
						metalTran.setInOutFld("D");
						metalTran.setBalance(casting.getFreshIssWt() * -1);
					}if(i == 2){
						//credit entry
						metalTran.setDeptLocation(departmentService.findByName("Casting"));
					//	metalTran.setDepartment(departmentService.findByName("Central"));	
						
						if(locationRights != null) {
							metalTran.setDepartment(locationRights.getDepartment());
						}else {
							metalTran.setDepartment(departmentService.findByName("Central"));
						}
						metalTran.setInOutFld("C");
						metalTran.setBalance(casting.getFreshIssWt());
					}
						metalTran.setPcsWt(false);
						metalTran.setStubWt(false);
						metalTran.setPurity(casting.getPurity());
						metalTran.setColor(casting.getColor());
						metalTran.setMetalWt(casting.getFreshIssWt());
						metalTran.setPurityConv(casting.getPurityConv());
						metalTran.setTranType("Casting");
						metalTran.setRefTranId(casting.getId());
						metalTran.setCreatedBy(principal.getName());
						metalTran.setCreatedDt(new java.util.Date());
						metalTran.setDeactive(false);
						metalTran.setTranDate(casting.getcDate());
						metalTran.setSrNo(1);
						metalTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
						metalTranService.save(metalTran);		
				}
			}//ending elsee-if
		}//ending main 1st - else
		
		
	
		
		//-------->>>>>>for Pcswt
		
		MetalTran pcsWtObj = metalTranService.findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndDeactiveAndBagMt(casting.getId(), "Casting", true, false, false,null);
		
		
		if(pcsWtObj != null){
			
			
			//edit code here
			if(casting.getPcsWt() == 0){
				metalTranService.delete(pcsWtObj.getId());
				
			}else{
				
						//credit entry
				if(locationRights != null) {
					pcsWtObj.setDeptLocation(locationRights.getDepartment());
				}else {
					pcsWtObj.setDeptLocation(departmentService.findByName("Central"));
				}
			//	pcsWtObj.setDeptLocation(departmentService.findByName("Central"));
				pcsWtObj.setDepartment(departmentService.findByName("Casting"));		
				pcsWtObj.setInOutFld("C");
				pcsWtObj.setBalance(casting.getPcsWt());
				pcsWtObj.setPcsWt(true);
				pcsWtObj.setStubWt(false);
				pcsWtObj.setPurity(casting.getPurity());
				pcsWtObj.setColor(casting.getColor());
				pcsWtObj.setMetalWt(casting.getPcsWt());
				pcsWtObj.setPurityConv(casting.getPurityConv());
				pcsWtObj.setTranType("Casting");
				pcsWtObj.setRefTranId(casting.getId());
				pcsWtObj.setModiBy(principal.getName());
				pcsWtObj.setModiDt(new java.util.Date());
				pcsWtObj.setDeactive(false);
				pcsWtObj.setTranDate(casting.getcDate());
				pcsWtObj.setMetalRate(mtlRate != null ? mtlRate :0.0);
				metalTranService.save(pcsWtObj);	
					
				
			}
		}else{
			//add code here
			if(casting.getPcsWt() > 0){
					 	metalTran = new MetalTran();
						//credit entry
						if(locationRights != null) {
							metalTran.setDeptLocation(locationRights.getDepartment());
						}else {
							metalTran.setDeptLocation(departmentService.findByName("Central"));
						}
					//	metalTran.setDeptLocation(departmentService.findByName("Central"));
						metalTran.setDepartment(departmentService.findByName("Casting"));		
						metalTran.setInOutFld("C");
						metalTran.setBalance(casting.getPcsWt());
						metalTran.setPcsWt(true);
						metalTran.setStubWt(false);
						metalTran.setPurity(casting.getPurity());
						metalTran.setColor(casting.getColor());
						metalTran.setMetalWt(casting.getPcsWt());
						metalTran.setPurityConv(casting.getPurityConv());
						metalTran.setTranType("Casting");
						metalTran.setRefTranId(casting.getId());
						metalTran.setCreatedBy(principal.getName());
						metalTran.setCreatedDt(new java.util.Date());
						metalTran.setDeactive(false);
						metalTran.setTranDate(casting.getcDate());
						metalTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
						metalTranService.save(metalTran);		
				
			}//ending elsee-if
		}//ending main 1st - else
		
		
		//----------->>>>>>>>>>for StubWt
		
		MetalTran stubWtObj = metalTranService.findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndDeactiveAndBagMt(casting.getId(), "Casting", false, true, false,null);
		if(stubWtObj != null){
			
			
			//edit code here
			if(casting.getStubWt() == 0){
				
				metalTranService.delete(stubWtObj.getId());
				
			}else{
				
					
						//credit entry
				if(locationRights != null) {
					stubWtObj.setDeptLocation(locationRights.getDepartment());
				}else {
					stubWtObj.setDeptLocation(departmentService.findByName("Central"));
				}
			//	stubWtObj.setDeptLocation(departmentService.findByName("Central"));
				stubWtObj.setDepartment(departmentService.findByName("Casting"));		
				stubWtObj.setInOutFld("C");
				stubWtObj.setBalance(casting.getStubWt());	
				stubWtObj.setPcsWt(false);
				stubWtObj.setStubWt(true);
				stubWtObj.setPurity(casting.getPurity());
				stubWtObj.setColor(casting.getColor());
				stubWtObj.setMetalWt(casting.getStubWt());
				stubWtObj.setPurityConv(casting.getPurityConv());
				stubWtObj.setTranType("Casting");
				stubWtObj.setRefTranId(casting.getId());
				stubWtObj.setModiBy(principal.getName());
				stubWtObj.setModiDt(new java.util.Date());
				stubWtObj.setDeactive(false);
				stubWtObj.setTranDate(casting.getcDate());
				stubWtObj.setMetalRate(mtlRate != null ? mtlRate :0.0);
				metalTranService.save(stubWtObj);	
					
				
			}
		}else{
			//add code here
			if(casting.getStubWt() > 0){
				
					 metalTran = new MetalTran();
						//credit entry
					 if(locationRights != null) {
						 metalTran.setDeptLocation(locationRights.getDepartment());
						}else {
							metalTran.setDeptLocation(departmentService.findByName("Central"));
						}
				//		metalTran.setDeptLocation(departmentService.findByName("Central"));
						metalTran.setDepartment(departmentService.findByName("Casting"));		
						metalTran.setInOutFld("C");
						metalTran.setBalance(casting.getStubWt());
					
						metalTran.setPcsWt(false);
						metalTran.setStubWt(true);
						metalTran.setPurity(casting.getPurity());
						metalTran.setColor(casting.getColor());
						metalTran.setMetalWt(casting.getStubWt());
						metalTran.setPurityConv(casting.getPurityConv());
						metalTran.setTranType("Casting");
						metalTran.setRefTranId(casting.getId());
						metalTran.setCreatedBy(principal.getName());
						metalTran.setCreatedDt(new java.util.Date());
						metalTran.setDeactive(false);
						metalTran.setTranDate(casting.getcDate());
						metalTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
						metalTranService.save(metalTran);		
				
			}//ending elsee-if
			
			
		}//ending main 1st - else
		
		if(retVal == "111")
		{
			retVal = "/jewels/manufacturing/transactions/castingMt/edit/"+casting.getId()+".html?success=true";
			return retVal;
		}else{
			return retVal;
		}
	}

	



}

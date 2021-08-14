package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICompTranRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class CompTranService implements ICompTranService {

	@Autowired
	ICompTranRepository compTranRepository;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPurityService purityService;

	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IComponentService componentService;
	
	@Override
	public List<CompTran> findAll() {
		return compTranRepository.findAll();
	}

	@Override
	public void save(CompTran compTran) {
		compTranRepository.save(compTran);
	}

	@Override
	public void delete(int id) {
		CompTran compTran = compTranRepository.findOne(id);
		compTran.setDeactive(true);
		compTran.setDeactiveDt(new java.util.Date());
		compTranRepository.save(compTran);

	}

	@Override
	public List<CompTran> findByRefTranIdAndTranType(Integer refTranId,
			String tranType) {
		return compTranRepository.findByRefTranIdAndTranType(refTranId,
				tranType);
	}

	@Override
	public CompTran RefTranIdAndTranType(Integer refTranId, String tranType) {
		return compTranRepository.RefTranIdAndTranType(refTranId, tranType);
	}



	@Override
	public Long count() {
		
		return compTranRepository.count();
	}
	
	@Override
	public Double getStockBalance(Integer purityId, Integer colorId,
			Integer locationId, Integer componentId) {
		
		QCompTran qCompTran = QCompTran.compTran;
		JPAQuery query = new JPAQuery(entityManager);
		Double retVal = -1.0;

		List<Double> balance = query
				.from(qCompTran)
				.where(qCompTran.purity.id.eq(purityId)
								.and(qCompTran.color.id.eq(colorId)
								.and(qCompTran.deptLocation.id.eq(locationId))
								.and(qCompTran.component.id.eq(componentId))
								.and(qCompTran.deactive.eq(false))))
				.list(qCompTran.balance.sum());

		
				
		for (Double bal : balance) {
			if(bal != null){
				retVal = bal;
			}
		}
		
		
		retVal=Math.round(retVal*1000.0)/1000.0;

		return retVal;
	}

	@Override
	public List<CompTran> findByBagMtAndTranTypeAndInOutFld(BagMt bagMt,
			String tranType, String inOutFld) {
		return compTranRepository.findByBagMtAndTranTypeAndInOutFld(bagMt, tranType, inOutFld);
	}

	@Override
	public List<CompTran> findByBagMtAndDeactive(BagMt bagMt, Boolean deactive) {
		return compTranRepository.findByBagMtAndDeactive(bagMt, deactive);
	}

	@Override
	public List<CompTran> findByBagMtAndPurityAndColorAndComponent(BagMt bagMt,
			Purity purity, Color color, Component component) {
		return compTranRepository.findByBagMtAndPurityAndColorAndComponent(bagMt, purity, color, component);
	}

	
	@Override
	public List<CompTran> getCompTranListForCosting(Integer bagId) {
		
		QCompTran qCompTran = QCompTran.compTran;
		
		JPAQuery query = new JPAQuery(entityManager);
		
		List<CompTran> compTrans = null;
		
		compTrans = query.from(qCompTran).
					where(qCompTran.deactive.eq(false).and(qCompTran.bagMt.id.eq(bagId))).
					groupBy(qCompTran.component,qCompTran.purity,qCompTran.color).
					list(qCompTran);
		
		return compTrans;
	}
	
	
	@Override
	public List<Tuple> getCompTranTupleList(Integer bagId) {
		
		QCompTran qCompTran = QCompTran.compTran;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Tuple> tupleList = null;
		
		tupleList = query.from(qCompTran).
				where(qCompTran.deactive.eq(false).and(qCompTran.bagMt.id.eq(bagId)).and(qCompTran.component.chargable.eq(true))).
				groupBy(qCompTran.component,qCompTran.purity,qCompTran.color).
				list(qCompTran.component.name,qCompTran.purity.name,qCompTran.color.name,qCompTran.balance.sum(),qCompTran.balancePcs.sum(),qCompTran.id);
		
		return tupleList;
	}

	@Override
	public String compAdditionSave(CompTran compTran,Integer id,String vBagNo,Double vQty,String vPresentDept,String findingFlg,Principal principal,Date vTranDate) {

		String retVal="1";
		
		if(vQty == null){
			vQty=0.0;
		}
		
		try {
			
			
			Department presentDept = departmentService.findByName(vPresentDept);
						
			Double balance = getStockBalance(compTran.getPurity().getId(), compTran.getColor().getId(),compTran.getDepartment().getId(),compTran.getComponent().getId());
			if (balance != null){
				if(balance < compTran.getMetalWt()){
					return  "Stock Not Available ,( Available Stock == "+balance+")";
				}
			}else{
				
				return  "Stock Not Available ,( Available Stock == 0)";
				
			}
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				BagMt bagMt = bagMtService.findByName(vBagNo);
				TranMt tranMtNew = tranMtService.findByBagMtCurrStk(bagMt, true);
				tranMtNew.setRecWt(Math.round((tranMtNew.getRecWt()+compTran.getMetalWt())*1000.0)/1000.0);
				
				tranMtService.save(tranMtNew);
				Double compMtlRate=getCompMetalRate(compTran.getPurity().getId(), compTran.getColor().getId(), compTran.getDepartment().getId(), 
						compTran.getComponent().getId(),compTran.getMetalWt());
				
				if(findingFlg.equalsIgnoreCase("false")){
					TranMetal tranMetal =tranMetalService.findByBagMtIdAndPartNmIdAndCurrStk(bagMt.getId(), compTran.getPartNm().getId(), true);
					
					Double bagVal=Math.round((tranMetal.getMetalWeight()*tranMetal.getMetalRate())*100.0)/100.0;
					Double addedVal =Math.round((compTran.getMetalWt()*compMtlRate)*100.0)/100.0;
					Double totVal=Math.round((bagVal+addedVal)*100.0)/100.0;
					Double totWt=Math.round((tranMetal.getMetalWeight()+compTran.getMetalWt())*1000.0)/1000.0;
					
					Double bagRate = Math.round((totVal/totWt)*100.0)/100.0;
					
					
					
					tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight() + compTran.getMetalWt())*1000.0)/1000.0);
					tranMetal.setMetalRate(bagRate);
					tranMetalService.save(tranMetal);
					
				}
				
				
				Double vMetalWt =Math.round((compTran.getMetalWt())*1000.0)/1000.0;
				
				CompTran compTranNew = new CompTran();
				
				compTranNew.setColor(compTran.getColor());
				compTranNew.setPurity(compTran.getPurity());
				compTranNew.setInOutFld("D");
				compTranNew.setBagMt(tranMtNew.getBagMt());;
				compTranNew.setBalance(-vMetalWt);
				compTranNew.setMetalWt(vMetalWt);
				compTranNew.setDeptLocation(compTran.getDepartment());
				compTranNew.setPurityConv(compTran.getPurity().getPurityConv());
				compTranNew.setPcs(vQty);
				compTranNew.setBalancePcs(-vQty);
				compTranNew.setRefTranId(tranMtNew.getId());
				compTranNew.setTranType("BagTran");
				compTranNew.setDepartment(presentDept);
				compTranNew.setComponent(compTran.getComponent());
				compTranNew.setCreatedBy(principal.getName());
				compTranNew.setCreatedDt(new java.util.Date());
				compTranNew.setTrandate(vTranDate);
				compTranNew.setMetalRate(compMtlRate);
				
				
				if(findingFlg.equalsIgnoreCase("false")){
					compTranNew.setPartNm(compTran.getPartNm());
				}
				compTranNew.setTranMtId(tranMtNew.getId());
				
				
				
				save(compTranNew);
				
				retVal = "1";
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return e.toString();
			
		}
		
		
		
		return retVal;

	}

	@Override
	public String compDeductionSave(CompTran compTran, Integer id,
			String vBagNo, Double vQty, String vPresentDept, String findingFlg,
			Principal principal,Date vTranDate) {
		
		String retVal="1";
		
		if(vQty == null){
			vQty=0.0;
		}
	
		try {
			
			Department presentDept = departmentService.findByName(vPresentDept);
			
		
			
			
			if(compTran.getMetalWt() <= 0){
				return ": Metal Wt Can Not Zero ";
			}
			
			
			BagMt bagMt = bagMtService.findByName(vBagNo);
			TranMt tranMtNew = tranMtService.findByBagMtCurrStk(bagMt, true);
			Double mtlRate=0.0;
			if(findingFlg.equalsIgnoreCase("false")){
				TranMetal tranMetal =tranMetalService.findByBagMtIdAndPartNmIdAndCurrStk(bagMt.getId(), compTran.getPartNm().getId(), true);
				
				
				if(tranMetal.getMetalWeight() >= compTran.getMetalWt()){
					tranMtNew.setRecWt(Math.round((tranMtNew.getRecWt() - compTran.getMetalWt())*1000.0)/1000.0);
					tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight() - compTran.getMetalWt())*1000.0)/1000.0);
					tranMetalService.save(tranMetal);
					tranMtService.save(tranMtNew);
					
					mtlRate =tranMetal.getMetalRate();
					
				}else{
					return ": Metal Wt Can Not Greater Than Part Wt ";
				}
				
			}else{
				
				tranMtNew.setRecWt(Math.round((tranMtNew.getRecWt() - compTran.getMetalWt())*1000.0)/1000.0);
				tranMtService.save(tranMtNew);

			}
			
			Double vMetalWt =Math.round((compTran.getMetalWt())*1000.0)/1000.0;
			
			CompTran compTranNew = new CompTran();
			
			compTranNew.setColor(compTran.getColor());
			compTranNew.setPurity(compTran.getPurity());
			compTranNew.setInOutFld("C");
			compTranNew.setBagMt(tranMtNew.getBagMt());;
			compTranNew.setBalance(vMetalWt);
			compTranNew.setMetalWt(vMetalWt);
			compTranNew.setDeptLocation(compTran.getDepartment());
			compTranNew.setPurityConv(compTran.getPurity().getPurityConv());
			compTranNew.setPcs(vQty);
			compTranNew.setBalancePcs(vQty);
			compTranNew.setRefTranId(tranMtNew.getId());
			compTranNew.setTranType("BagTran");
			compTranNew.setDepartment(presentDept);
			compTranNew.setComponent(compTran.getComponent());
			compTranNew.setCreatedBy(principal.getName());
			compTranNew.setCreatedDt(new java.util.Date());
			compTranNew.setTrandate(vTranDate);
			compTranNew.setMetalRate(mtlRate != null ? mtlRate :0.0);
			
			if(findingFlg.equalsIgnoreCase("false")){
				compTranNew.setPartNm(compTran.getPartNm());
			}
			compTranNew.setTranMtId(tranMtNew.getId());
			
			save(compTranNew);
			
			retVal = "1";
			
			
			
		} catch (Exception e) {
			return e.getMessage();
		}

		

		return retVal;

	}

	@Override
	public String multiCompAdditionSave(CompTran compTran, String vvBagNo,Double vTotQty,
			Integer vPresentDeptId, String findingFlg, Principal principal, Date tranDate) {

		String retVal="1";
		
		
		try {
			
			
			
			Department presentDept = departmentService.findOne(vPresentDeptId);
						
			Double balance = getStockBalance(compTran.getPurity().getId(), compTran.getColor().getId(),compTran.getDepartment().getId(),compTran.getComponent().getId());
			if (balance != null){
				if(balance < compTran.getMetalWt()){
					return  "Stock Not Available ,( Available Stock == "+balance+")";
				}
			}else{
				return  "Stock Not Available ,( Available Stock == 0)";
			}
			
			String[] data1 = vvBagNo.split(",");
			
			Double vMetalWt =Math.round((compTran.getMetalWt()/vTotQty)*1000.0)/1000.0;
			Double vqty =Math.round((compTran.getPcs()/vTotQty)*100.0)/100.0; 
				
			for (int i = 0; i < data1.length; i++) {
				Double vmtlWt=0.0;
				Double vQty =0.0;
				BagMt bagMt = bagMtService.findByName(data1[i]);
							
				
				TranMt tranMtNew = tranMtService.findByBagMtCurrStk(bagMt, true);
				vmtlWt =Math.round((vMetalWt*tranMtNew.getPcs())*1000.0)/1000.0;
				vQty = vqty*tranMtNew.getPcs();
				
				tranMtNew.setRecWt(Math.round((tranMtNew.getRecWt()+vmtlWt)*1000.0)/1000.0);
				
				tranMtService.save(tranMtNew);
				
				LookUpMast part = null;
				
				
				if(findingFlg.equalsIgnoreCase("false")){
					
					TranMetal tranMetal =tranMetalService.findByBagMtIdAndMainMetalAndCurrStk(bagMt.getId(), true, true);
					part =tranMetal.getPartNm();
					/*TranMetal tranMetal =tranMetalService.findByBagMtIdAndPartNmIdAndCurrStkAndDeactive(bagMt.getId(), part.getId(), true, false);*/
					
					tranMetal.setMetalWeight( Math.round((tranMetal.getMetalWeight() + vmtlWt)*1000.0)/1000.0);
					tranMetalService.save(tranMetal);
					
				}
				
				Purity purity =purityService.findOne(compTran.getPurity().getId());
				
				
				CompTran compTranNew = new CompTran();
				
				compTranNew.setColor(compTran.getColor());
				compTranNew.setPurity(compTran.getPurity());
				compTranNew.setInOutFld("D");
				compTranNew.setBagMt(tranMtNew.getBagMt());;
				compTranNew.setBalance(-vmtlWt);
				compTranNew.setMetalWt(vmtlWt);
				compTranNew.setDeptLocation(compTran.getDepartment());
				compTranNew.setPurityConv(purity.getPurityConv());
				compTranNew.setPcs(vQty);
				compTranNew.setBalancePcs(-vQty);
				compTranNew.setRefTranId(tranMtNew.getId());
				compTranNew.setTranType("BagTran");
				compTranNew.setDepartment(presentDept);
				compTranNew.setComponent(compTran.getComponent());
				compTranNew.setCreatedBy(principal.getName());
				compTranNew.setCreatedDt(new java.util.Date());
				compTranNew.setTrandate(tranDate);
				
				if(findingFlg.equalsIgnoreCase("false")){
					compTranNew.setPartNm(part);
				}
				compTranNew.setTranMtId(tranMtNew.getId());
				
				
				
				save(compTranNew);
				
				
				
				
				
			}
			
			
			
	
				
				
				
				retVal = "1";
			
		
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
			
		}
		
		
		
		return retVal;
	}

	@Override
	public String multiCompDeductionSave(CompTran compTran, String vBagNo,Double vTotQty,
			String vPresentDept, String findingFlg, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompTran findOne(int id) {
		// TODO Auto-generated method stub
		return compTranRepository.findOne(id);
	}

	@Override
	public List<CompTran> findByTranMtIdAndDeactive(Integer tranMtId,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return compTranRepository.findByTranMtIdAndDeactive(tranMtId, deactive);
	}

	@Override
	public String compDeductionSave(String vBagNo, String vPresentDept, String deductionData, Principal principal,Date vTranDate,Integer departmentId) {
	
		String retVal="1";
		try {
			
	
			Department presentDept = departmentService.findByName(vPresentDept);
			BagMt bagMt = bagMtService.findByName(vBagNo);
			TranMt tranMtNew = tranMtService.findByBagMtCurrStk(bagMt, true);
			
			JSONArray jsonDeductionDt = new JSONArray(deductionData);
			
			for (int y = 0; y < jsonDeductionDt.length(); y++) {
			
				JSONObject dataDeductionDt = (JSONObject) jsonDeductionDt.get(y);
				
				if(!dataDeductionDt.get("retMetalwt").toString().isEmpty() && dataDeductionDt.get("retMetalwt").toString() != null &&
						Double.parseDouble(dataDeductionDt.get("retMetalwt").toString()) > 0) {
				
				if(Double.parseDouble(dataDeductionDt.get("retMetalwt").toString()) > Double.parseDouble(dataDeductionDt.get("metalWt").toString())){
					return "Return metal wt can not be greater metal wt ";
				}
				LookUpMast lookUpMast = lookUpMastService.findByFieldValueAndDeactive(dataDeductionDt.get("partNm").toString(), false);
			
			if(lookUpMast != null){
				
				TranMetal tranMetal =tranMetalService.findByBagMtIdAndPartNmIdAndCurrStk(bagMt.getId(), lookUpMast.getId(), true);
				
				
				if(tranMetal.getMetalWeight() >= Double.parseDouble(dataDeductionDt.get("retMetalwt").toString())){
					tranMtNew.setRecWt(Math.round((tranMtNew.getRecWt() - Double.parseDouble(dataDeductionDt.get("retMetalwt").toString()))*1000.0)/1000.0);
					tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight() -  Double.parseDouble(dataDeductionDt.get("retMetalwt").toString()))*1000.0)/1000.0);
					tranMetalService.save(tranMetal);
					tranMtService.save(tranMtNew);
					
				}else{
					return "Error : Metal Wt Can Not Greater Than Part Wt ";
				}
				
			}else{
				
				tranMtNew.setRecWt(Math.round((tranMtNew.getRecWt() - Double.parseDouble(dataDeductionDt.get("retMetalwt").toString()))*1000.0)/1000.0);
				tranMtService.save(tranMtNew);

			}
	
			Double vMetalWt =Math.round((Double.parseDouble(dataDeductionDt.get("retMetalwt").toString()))*1000.0)/1000.0;
			
			Purity purity =  purityService.findByName(dataDeductionDt.get("purity").toString());
			
			Double returnQty=0.0;
			if(!dataDeductionDt.get("retQty").toString().isEmpty() && dataDeductionDt.get("retQty").toString() != null){
				returnQty= Double.parseDouble(dataDeductionDt.get("retQty").toString());
			}
			
			CompTran compTranNew = new CompTran();
			
			compTranNew.setColor(colorService.findByName(dataDeductionDt.get("color").toString()));
			compTranNew.setPurity(purity);
			compTranNew.setInOutFld("C");
			compTranNew.setBagMt(bagMt);;
			compTranNew.setBalance(vMetalWt);
			compTranNew.setMetalWt(vMetalWt);
			compTranNew.setDeptLocation(departmentService.findOne(departmentId));
			compTranNew.setPurityConv(purity.getPurityConv());
			compTranNew.setPcs(returnQty);
			compTranNew.setBalancePcs(returnQty);
			compTranNew.setRefTranId(tranMtNew.getId());
			compTranNew.setTranType("BagTran");
			compTranNew.setDepartment(presentDept);
			compTranNew.setComponent(componentService.findByName(dataDeductionDt.get("component").toString()));
			compTranNew.setCreatedBy(principal.getName());
			compTranNew.setCreatedDt(new java.util.Date());
			compTranNew.setTrandate(vTranDate);
			
			if(lookUpMast != null){
				compTranNew.setPartNm(lookUpMast);
			}
			compTranNew.setTranMtId(tranMtNew.getId());
			
			save(compTranNew);
			
			retVal = "1";
			
			}
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return retVal;
	
	}

	@Override
	public Double getCompMetalRate(Integer purityId, Integer colorId, Integer locationId, Integer componentId,
			Double adjWt) {
		@SuppressWarnings("unchecked")
		TypedQuery<Object> query =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_avgcompmetalrate(?,?,?,?,?) }");
		query.setParameter(1,purityId);
		query.setParameter(2,colorId);
		query.setParameter(3,locationId);
		query.setParameter(4,componentId);
		query.setParameter(5,adjWt);
		
		List<Object> mtlRateList = query.getResultList();
		
		Double mtlRate = (Double) mtlRateList.get(0);
		return mtlRate;
	}


}

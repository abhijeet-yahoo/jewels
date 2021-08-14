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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QMetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMetalTranRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class MetalTranService implements IMetalTranService {

	@Autowired
	IMetalTranRepository metalTranRepository;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private IColorService colorService;
	

	@Override
	public List<MetalTran> findAll() {

		return metalTranRepository.findAll();
	}

	@Override
	public void save(MetalTran metalTran) {
		metalTranRepository.save(metalTran);

	}

	@Override
	public void delete(int id) {
		MetalTran metalTran = metalTranRepository.findOne(id);
		metalTran.setDeactive(true);
		metalTran.setDeactiveDt(new java.util.Date());
		metalTranRepository.save(metalTran);

	}

	@Override
	public Long count() {
		return metalTranRepository.count();
	}
	
	@Override
	public Double getStockBalance(Integer purityId, Integer colorId,
			Integer locationId) {
	
		QMetalTran qMetalTran = QMetalTran.metalTran;
		JPAQuery query = new JPAQuery(entityManager);
				
		List<Double> balance = query
				.from(qMetalTran)
				.where(qMetalTran.purity.id.eq(purityId).and(
						qMetalTran.color.id.eq(colorId)
						.and(qMetalTran.deptLocation.id.eq(locationId))
						.and(qMetalTran.deactive.eq(false))).and(qMetalTran.pcsWt.eq(false)))
		.list(qMetalTran.balance.sum());
		
		
		
		
		Double tempSum = 0.0;
		for(Double vBalMtal:balance){
			
			if(vBalMtal != null){
				tempSum = vBalMtal;
			}
			
		}
		
		
		tempSum=Math.round(tempSum*1000.0)/1000.0;
		


		return tempSum;
	
	}
	
	
	
	
	@Override
	public Double getPcsWtStockBalance(Integer purityId, Integer colorId,
			Integer locationId) {
		
		QMetalTran qMetalTran = QMetalTran.metalTran;
		JPAQuery query = new JPAQuery(entityManager);
		Double retVal = -1.0;
		
		List<Double> balance = query
				.from(qMetalTran)
				.where(qMetalTran.purity.id.eq(purityId).and(
						qMetalTran.color.id.eq(colorId)
						.and(qMetalTran.deptLocation.id.eq(locationId))
						.and(qMetalTran.deactive.eq(false)).and(qMetalTran.pcsWt.eq(true))))
		.list(qMetalTran.balance.sum());
		
				
		for (Double bal : balance) {
			if(bal != null){
				retVal = bal;
			}
		}
		
		
		retVal= Math.round(retVal*1000.0)/1000.0;

		return retVal;
	}
	
	
	
	
	
	

	@Override
	public MetalTran findOne(int id) {
		return metalTranRepository.findOne(id);
	}

	

	@Override
	public MetalTran RefTranIdAndTranType(Integer refTranId, String tranType) {
		return metalTranRepository.RefTranIdAndTranType(refTranId, tranType);
	}

	@Override
	public MetalTran findByRefTranIdAndTranTypeAndSrNo(Integer refTranId,
			String tranType, Integer srNo) {
		return metalTranRepository.findByRefTranIdAndTranTypeAndSrNo(refTranId, tranType, srNo);
	}
	
	@Override
	public MetalTran findByRefTranIdAndTranTypeAndInOutFld(Integer refTranId,
			String tranType, String inOutFld) {
		return metalTranRepository.findByRefTranIdAndTranTypeAndInOutFld(refTranId, tranType, inOutFld);
	}

	@Override
	public List<MetalTran> findByBagMtAndTranTypeAndInOutFld(BagMt bagMt,
			String tranType, String inOutFld) {
		return metalTranRepository.findByBagMtAndTranTypeAndInOutFld(bagMt, tranType, inOutFld);
	}


	@Override
	public List<MetalTran> findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndSrNoAndDeactive(
			Integer refTranId, String tranType, Boolean pcsWt, Boolean stubWt,
			Integer srNo, Boolean deactive) {
		return metalTranRepository.findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndSrNoAndDeactive(refTranId, tranType, pcsWt, stubWt, srNo, deactive);
	}

	@Override
	public List<MetalTran> findByRefTranIdAndTranTypeAndDeactive(
			Integer refTranId, String tranType, Boolean deactive) {
		return metalTranRepository.findByRefTranIdAndTranTypeAndDeactive(refTranId, tranType, deactive);
	}

	@Override
	public MetalTran findByRefTranIdAndTranTypeAndBagMtAndPcsWtAndDeactive(
			Integer refTranId, String tranType, BagMt bagMt, Boolean pcsWt,
			Boolean deactive) {
		return metalTranRepository.findByRefTranIdAndTranTypeAndBagMtAndPcsWtAndDeactive(refTranId, tranType, bagMt, pcsWt, deactive);
	}

	@Override
	public MetalTran findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndDeactiveAndBagMt(
			Integer refTranId, String tranType, Boolean pcsWt, Boolean stubWt,
			Boolean deactive, BagMt bagMt) {
		
		return metalTranRepository.findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndDeactiveAndBagMt(refTranId, tranType, pcsWt, stubWt, deactive, bagMt);
	}

	@Override
	public List<MetalTran> findByStyleIdAndTranTypeAndInOutFld(Integer styleId,
			String tranType, String inOutFld) {
		// TODO Auto-generated method stub
		return metalTranRepository.findByStyleIdAndTranTypeAndInOutFld(styleId, tranType, inOutFld);
	}

	@Override
	public String metalAdditionSave(String vBagNo,MetalTran metalTran,String vPresentDept,Principal principal,Date vTranDate) {

		String retVal="-1";
		
		Department deptLocation = departmentService.findOne(metalTran.getDepartment().getId());
		
		Purity purity = purityService.findByName(metalTran.getPurity().getName());
		
		Color color = colorService.findByName(metalTran.getColor().getName());
		
		Department presentDept = departmentService.findByName(vPresentDept);
		
		Double balance = getStockBalance(purity.getId(), color.getId(),deptLocation.getId());
		
		if(metalTran.getMetalWt() > 0.0){
			if (balance != null){
				if(balance < metalTran.getMetalWt()){
					return retVal = "-1";
				}
			}else{
				return retVal = "-1";
			}
		}
			
			BagMt bagMt = bagMtService.findByName(vBagNo);
			TranMt tranMtNew = tranMtService.findByBagMtCurrStk(bagMt, true);
			Double rec = tranMtNew.getRecWt();
			
			tranMtNew.setRecWt(Math.round((rec + metalTran.getMetalWt())*1000.0)/1000.0);
					
			
			tranMtService.save(tranMtNew);
			
			TranMetal tranMetal =tranMetalService.findByBagMtIdAndPartNmIdAndCurrStk(bagMt.getId(), metalTran.getPartNm().getId(), true);
		
			
		/* Double mtlRate = (Double) mtlRateList.get(0); */
			
			Double mtlRate=getMetalRate(purity.getId(), color.getId(), deptLocation.getId(), metalTran.getMetalWt());
			mtlRate=(mtlRate != null ? mtlRate :0.0);
			
			Double bagVal=Math.round((tranMetal.getMetalWeight()*tranMetal.getMetalRate())*100.0)/100.0;
			Double addedVal =Math.round((metalTran.getMetalWt()* mtlRate)*100.0)/100.0;
			Double totVal=Math.round((bagVal+addedVal)*100.0)/100.0;
			Double totWt=Math.round((tranMetal.getMetalWeight()+metalTran.getMetalWt())*1000.0)/1000.0;
			
			Double bagRate = Math.round((totVal/totWt)*100.0)/100.0;
			
			
			tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight() + metalTran.getMetalWt())*1000.0)/1000.0);
			tranMetal.setMetalRate(bagRate);
			tranMetalService.save(tranMetal);
			
			
			
			MetalTran metalTran2 = new MetalTran();
			
			metalTran2.setTranDate(vTranDate);
			metalTran2.setColor(color);
			metalTran2.setPurity(purity);
			metalTran2.setInOutFld("D");
			metalTran2.setPartNm(metalTran.getPartNm());
			metalTran2.setBalance(Math.round((-metalTran.getMetalWt())*1000.0)/1000.0);
			metalTran2.setMetalWt(Math.round(metalTran.getMetalWt()*1000.0)/1000.0);
			metalTran2.setDeptLocation(deptLocation);
			metalTran2.setPurityConv(purity.getPurityConv());
			metalTran2.setRefTranId(tranMtNew.getId());
			metalTran2.setTranMtId(tranMtNew.getId());
			metalTran2.setTranType("BagTran");
			metalTran2.setDepartment(presentDept);
			metalTran2.setCreatedBy(principal.getName());
			metalTran2.setCreatedDt(new java.util.Date());
			metalTran2.setBagMt(tranMtNew.getBagMt());
			metalTran2.setMetalRate(mtlRate);
						
			save(metalTran2);
			
			retVal = "1";
		
	
	
		return retVal;
	}

	@Override
	public String metalDeductionSave(String vBagNo, MetalTran metalTran,
			String vPresentDept, Principal principal,Date vTranDate) {
		
		String retVal="Contact Admin :";
		
		Department deptLocation = departmentService.findOne(metalTran.getDepartment().getId());
		
		Purity purity = purityService.findByName(metalTran.getPurity().getName());
		
		Color color = colorService.findByName(metalTran.getColor().getName());
		
		Department presentDept = departmentService.findByName(vPresentDept);
		
		
		if(metalTran.getMetalWt() <= 0){
			return ": Metal Wt Can Not Zero ";
		}
		

		
			
			BagMt bagMt = bagMtService.findByName(vBagNo);
			TranMt tranMtNew = tranMtService.findByBagMtCurrStk(bagMt, true);
			
			
			TranMetal tranMetal =tranMetalService.findByBagMtIdAndPartNmIdAndCurrStk(bagMt.getId(), metalTran.getPartNm().getId(), true);
			
			
				if(tranMetal.getMetalWeight() >= metalTran.getMetalWt()){
					tranMtNew.setRecWt(Math.round((tranMtNew.getRecWt() - metalTran.getMetalWt())*1000.0)/1000.0);
					tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight() - metalTran.getMetalWt())*1000.0)/1000.0);
					tranMetalService.save(tranMetal);
					tranMtService.save(tranMtNew);
					
				}else{
					return ": Metal Wt Can Not Greater Than Part Wt ";
				}
			
			
			
			
			MetalTran metalTran2 = new MetalTran();
			
			metalTran2.setTranDate(vTranDate);
			metalTran2.setColor(color);
			metalTran2.setPurity(purity);
			metalTran2.setInOutFld("C");
			metalTran2.setPartNm(metalTran.getPartNm());
			
			metalTran2.setBalance(Math.round((metalTran.getMetalWt())*1000.0)/1000.0);
			metalTran2.setMetalWt(Math.round(metalTran.getMetalWt()*1000.0)/1000.0);
			
			
			
			metalTran2.setDeptLocation(deptLocation);
			metalTran2.setPurityConv(purity.getPurityConv());
			metalTran2.setRefTranId(tranMtNew.getId());
			metalTran2.setTranMtId(tranMtNew.getId());
			metalTran2.setTranType("BagTran");
			metalTran2.setDepartment(presentDept);
			metalTran2.setCreatedBy(principal.getName());
			metalTran2.setCreatedDt(new java.util.Date());
			metalTran2.setBagMt(tranMtNew.getBagMt());
			metalTran2.setMetalRate(tranMetal.getMetalRate());
			
			save(metalTran2);
			
			retVal = "1";
		
	
		return retVal;
	}

	@Override
	public MetalTran findByTranTypeAndBagMtAndPcsWtAndDeactiveAndPartNm(
			String tranType, BagMt bagMt, Boolean pcsWt, Boolean deactive,
			LookUpMast partNm) {
		// TODO Auto-generated method stub
		return metalTranRepository.findByTranTypeAndBagMtAndPcsWtAndDeactiveAndPartNm(tranType, bagMt, pcsWt, deactive, partNm);
	}

	@Override
	public List<MetalTran> findByTranMtIdAndDeactive(Integer tranMtId,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return metalTranRepository.findByTranMtIdAndDeactive(tranMtId, deactive);
	}

	@Override
	public Double getMetalRate(Integer purityId, Integer colorId, Integer locationId, Double adjWt) {
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object> query =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_avgmetalrateAvg(?,?,?,?) }");
		query.setParameter(1,purityId);
		query.setParameter(2,colorId);
		query.setParameter(3,locationId);
		query.setParameter(4,adjWt);
		
		List<Object> mtlRateList = query.getResultList();
		
		Double mtlRate = (Double) mtlRateList.get(0);
		return mtlRate;
	}

	



}

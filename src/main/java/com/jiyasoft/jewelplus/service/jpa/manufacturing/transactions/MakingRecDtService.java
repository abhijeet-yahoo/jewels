package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingRecDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMakingRecDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMakingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMakingRecDtService;

@Service
@Repository
@Transactional
public class MakingRecDtService implements IMakingRecDtService{

	@Autowired
	IMakingRecDtRepository makingRecDtRepository;
	
	@Autowired
	private IMakingMtService makingMtService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Override
	public List<MakingRecDt> findAll() {
		return makingRecDtRepository.findAll();
	}

	@Override
	public Page<MakingRecDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		return null;
	}

	@Override
	public void save(MakingRecDt makingRecDt) {
		makingRecDtRepository.save(makingRecDt);
		
	}

	@Override
	public void delete(int id) {
		MakingRecDt makingRecDt = makingRecDtRepository.findOne(id);
		makingRecDt.setDeactive(true);
		makingRecDt.setDeactiveDt(new java.util.Date());
		makingRecDtRepository.save(makingRecDt);
		
	}

	@Override
	public Long count() {
		return makingRecDtRepository.count();
	}

	@Override
	public MakingRecDt findOne(int id) {
		return makingRecDtRepository.findOne(id);
	}

	@Override
	public MakingRecDt findByUniqueId(Long uniqueId) {
		return makingRecDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		return null;
	}

	@Override
	public List<MakingRecDt> findByMakingMtAndDeactive(MakingMt makingMt,
			Boolean deactive) {
		return makingRecDtRepository.findByMakingMtAndDeactive(makingMt, deactive);
	}

	@Override
	public String saveMakingDt(String pInvNo,Integer id,MakingRecDt makingRecDt,Principal principal,Double changedIssueWt,Double changeRetMetWt) {
		
		String retVal = "-1";
		
		CompTran compTran = null;
		
		MakingMt makingMt = makingMtService.findByInvNoAndDeactive(pInvNo, false);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			makingRecDt.setCreatedBy(principal.getName());
			makingRecDt.setCreatedDt(new java.util.Date());
			makingRecDt.setMakingMt(makingMt);
			makingRecDt.setUniqueId(new java.util.Date().getTime());
			makingRecDt.setPurity(makingMt.getPurity());
			makingRecDt.setColor(makingMt.getColor());
			
			compTran = new CompTran();
			
			retVal = "1"; 
			
		}else{
			makingRecDt.setId(id);
			makingRecDt.setModiBy(principal.getName());
			makingRecDt.setModiDt(new java.util.Date());
			makingRecDt.setMakingMt(makingMt);
			makingRecDt.setPurity(makingMt.getPurity());
			makingRecDt.setColor(makingMt.getColor());
			
			compTran = compTranService.RefTranIdAndTranType(id, "Making");
		}
		
		makingRecDt.setMetalRate(makingMt.getMetalRate());
		save(makingRecDt);
		
		MakingRecDt makingRecDtNew = findByUniqueId(makingRecDt.getUniqueId());
			
		compTran.setColor(makingRecDtNew.getColor());
		compTran.setPurity(makingRecDtNew.getPurity());
		compTran.setInOutFld("C");
		compTran.setBagMt(null);
		compTran.setBalance(makingRecDtNew.getRecWt());
		compTran.setMetalWt(makingRecDtNew.getRecWt());
		compTran.setDeptLocation(departmentService.findByName("Component"));
		compTran.setPurityConv(makingRecDtNew.getPurity().getPurityConv());
		compTran.setRefTranId(makingRecDtNew.getId());
		compTran.setTranType("Making");
		compTran.setRemark("");
		compTran.setDepartment(null);
		compTran.setComponent(makingRecDtNew.getComponent());
		compTran.setCreatedBy(makingRecDtNew.getCreatedBy());
		compTran.setCreatedDt(makingRecDtNew.getCreatedDt());
		compTran.setDeactive(false);
		compTran.setPcs(makingRecDtNew.getQty());
		compTran.setBalancePcs(makingRecDtNew.getQty());
		compTran.setTrandate(makingMt.getInvDate());
		compTran.setMetalRate(makingMt.getMetalRate());
		compTranService.save(compTran);
		
		
		
		
		

		List<MakingRecDt> makingRecDts=findByMakingMtAndDeactive(makingMt, false);
		Double vMetalWt=0.0;
		for(MakingRecDt makingRecDt2 :makingRecDts){
			vMetalWt +=makingRecDt2.getRecWt();
		}
		
		Double loss =Math.round((changedIssueWt-changeRetMetWt-vMetalWt)*1000.0)/1000.0;
			
		makingMt.setLoss(loss);
		makingMt.setReturnMetal(changeRetMetWt);
		makingMt.setFreshIssWt(changedIssueWt);
		makingMtService.save(makingMt);
		
		
		return retVal;
		
	}

	@Override
	public Integer makingrecDelete(Integer id) {
		
		Integer retVal=null;

		delete(id);
		
		MakingRecDt makingRecDt = findOne(id);
		
		List<CompTran> compTrans = compTranService.findByRefTranIdAndTranType(id, "Making");
		for(CompTran compTran : compTrans){
			compTranService.delete(compTran.getId());
		}
		
		Integer makingMtId = makingRecDt.getMakingMt().getId();
		retVal=makingMtId;
		
		
		return retVal;
	}

}

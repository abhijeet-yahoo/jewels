package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddCompInvRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompInvService;

@Service
@Repository
@Transactional
public class VAddCompInvService implements IVAddCompInvService {
	
	
	@Autowired
	private IVAddCompInvRepository vAddCompInvRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private IVAddCompAdjService vAddCompAdjService;
	
	@Autowired
	private IComponentPurchaseDtService componentPurchaseDtService;
	
	@Override
	public List<VAddCompInv> findAll() {
		return vAddCompInvRepository.findAll();
	}

	@Override
	public Page<VAddCompInv> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		
		return null;
	}

	@Override
	public void save(VAddCompInv vAddCompInv) {
		vAddCompInvRepository.save(vAddCompInv);
		
	}

	@Override
	public void delete(int id) {
		VAddCompInv vAddCompInv = vAddCompInvRepository.findOne(id);
		vAddCompInv.setDeactive(true);
		vAddCompInv.setDeactiveDt(new java.util.Date());
		vAddCompInvRepository.save(vAddCompInv);
	}

	@Override
	public Long count() {
		return vAddCompInvRepository.count();
	}

	@Override
	public VAddCompInv findOne(int id) {
		return vAddCompInvRepository.findOne(id);
	}

	@Override
	public List<VAddCompInv> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		return vAddCompInvRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public VAddCompInv findByComponentAndPurityAndColorAndAdjustedAndDeactive(
			Component component, Purity purity, Color color, Boolean adjusted,
			Boolean deactive) {
		
		return vAddCompInvRepository.findByComponentAndPurityAndColorAndAdjustedAndDeactive(component, purity, color, adjusted, deactive);
	}


	@Override
	public List<VAddCompInv> getCompStockList(Integer costId) {
		
		
		List<VAddCompInv> vAddCompInv = null;
		
		@SuppressWarnings("unchecked")
		TypedQuery<VAddCompInv> query = (TypedQuery<VAddCompInv>) entityManager.createNativeQuery("{ CALL jsp_vaddcomp(?) }",VAddCompInv.class);
		query.setParameter(1, costId);
		vAddCompInv = query.getResultList();
		
		
		System.out.println("printing the size====="+vAddCompInv.size());
	
		return vAddCompInv;
	
	}

	@Override
	public String deleteAllCompAdjustment(Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
String retVal="-1";
		
		List<VAddCompInv> vAddCompInvs = findByCostingMtAndDeactive(costingMtService.findOne(mtId),false);
		for (VAddCompInv vAddCompInv : vAddCompInvs) {
			
			
			List<VAddCompAdj> vAddCompAdjs = vAddCompAdjService.findByVAddCompInvAndDeactive(vAddCompInv, false);
			for(VAddCompAdj vAddCompAdj : vAddCompAdjs){
				
				ComponentPurchaseDt componentPurchaseDt = componentPurchaseDtService.findOne(vAddCompAdj.getComponentPurchaseDt().getId());
				
				Double tempBalance = (Math.round((componentPurchaseDt.getBalance() + vAddCompAdj.getAdjustmentWt())*1000.0)/1000.0);
				
				componentPurchaseDt.setBalance(tempBalance);
				componentPurchaseDt.setModiBy(principal.getName());
				componentPurchaseDt.setModiDt(new java.util.Date());
				componentPurchaseDtService.save(componentPurchaseDt);
				
				vAddCompAdjService.delete(vAddCompAdj.getId());
				
			}
			
			vAddCompInv.setAdjusted(false);
			save(vAddCompInv);
			
			retVal="1";
		}
		
		
		
		return retVal;
	}
	
}

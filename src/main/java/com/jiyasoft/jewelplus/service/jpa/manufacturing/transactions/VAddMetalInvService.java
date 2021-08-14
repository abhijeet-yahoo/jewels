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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddMetalInvRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalInvService;

@Service
@Repository
@Transactional
public class VAddMetalInvService implements IVAddMetalInvService {
	
	@Autowired
	private IVAddMetalInvRepository vAddMetalInvRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IMetalPurchaseDtService metalPurchaseDtService;
	
	@Autowired
	private IVAddMetalAdjService vAddMetalAdjService;
	
	@Autowired
	private ICostingMtService costingMtService;

	@Override
	public List<VAddMetalInv> findAll() {
		return vAddMetalInvRepository.findAll();
	}

	@Override
	public Page<VAddMetalInv> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		return null;
	}

	@Override
	public void save(VAddMetalInv vAddMetalInv) {
		vAddMetalInvRepository.save(vAddMetalInv);
	}

	@Override
	public void delete(int id) {
		vAddMetalInvRepository.delete(id);
		
	}

	@Override
	public Long count() {
		return vAddMetalInvRepository.count();
	}

	@Override
	public VAddMetalInv findOne(int id) {
		// TODO Auto-generated method stub
		return vAddMetalInvRepository.findOne(id);
	}

	@Override
	public List<VAddMetalInv> findByCostingMt(CostingMt costingMt) {
		return vAddMetalInvRepository.findByCostingMt(costingMt);
	}

	@Override
	public VAddMetalInv findByMetalAndAdjustedAndCostingMt(
			Metal metal, Boolean adjusted, CostingMt costingMt) {
		return vAddMetalInvRepository.findByMetalAndAdjustedAndCostingMt(metal, adjusted,costingMt);
	}

	@Override
	public List<VAddMetalInv> getMetalInvStockList(Integer costMtId) {
		
		List<VAddMetalInv> vAddDts = null;
		
		@SuppressWarnings("unchecked")
		TypedQuery<VAddMetalInv> query = (TypedQuery<VAddMetalInv>) entityManager.createNativeQuery("{ CALL jsp_vaddmetal(?) }",VAddMetalInv.class);
		query.setParameter(1, costMtId);
		vAddDts = query.getResultList();
		
					
		return vAddDts;
	}

	@Override
	public String deleteMetalAdjustment(Integer vaddMetalInvId,
			Principal principal) {
		
		VAddMetalInv vAddMetalInv = findOne(vaddMetalInvId);
		
		List<VAddMetalAdj> vAddMetalAdjs = vAddMetalAdjService.findByVAddMetalInv(vAddMetalInv);
		for(VAddMetalAdj vAddMetalAdj:vAddMetalAdjs){
			
			MetalPurchaseDt metalPurchaseDt = metalPurchaseDtService.findOne(vAddMetalAdj.getMetalPurchaseDt().getId());
			
			Double tempBalance = (Math.round((metalPurchaseDt.getBalance() + vAddMetalAdj.getAdjustmentWt())*1000.0)/1000.0);
			
			metalPurchaseDt.setBalance(tempBalance);
			metalPurchaseDt.setModiBy(principal.getName());
			metalPurchaseDt.setModiDt(new java.util.Date());
			metalPurchaseDtService.save(metalPurchaseDt);
			
			vAddMetalAdjService.delete(vAddMetalAdj.getId());
			
		}
		
		delete(vaddMetalInvId);
		return "-1";
	}

	@Override
	public String deleteAllMetalAdjustment(Integer mtId, Principal principal) {
		
		String retVal="-1";
		
		List<VAddMetalInv> vAddMetalInvs = findByCostingMt(costingMtService.findOne(mtId));
		for (VAddMetalInv vAddMetalInv : vAddMetalInvs) {
			
			
			List<VAddMetalAdj> vAddMetalAdjs = vAddMetalAdjService.findByVAddMetalInv(vAddMetalInv);
			for(VAddMetalAdj vAddMetalAdj:vAddMetalAdjs){
				
				MetalPurchaseDt metalPurchaseDt = metalPurchaseDtService.findOne(vAddMetalAdj.getMetalPurchaseDt().getId());
				
				Double tempBalance = (Math.round((metalPurchaseDt.getBalance() + vAddMetalAdj.getAdjustmentWt())*1000.0)/1000.0);
				
				metalPurchaseDt.setBalance(tempBalance);
				metalPurchaseDt.setModiBy(principal.getName());
				metalPurchaseDt.setModiDt(new java.util.Date());
				metalPurchaseDtService.save(metalPurchaseDt);
				
				vAddMetalAdjService.delete(vAddMetalAdj.getId());
				
			}
			
			vAddMetalInv.setAdjusted(false);
			save(vAddMetalInv);
			
		//	delete(vAddMetalInv.getId());
			retVal="1";
		}
		
		
		
		return retVal;
	}

	
	
	
	

}

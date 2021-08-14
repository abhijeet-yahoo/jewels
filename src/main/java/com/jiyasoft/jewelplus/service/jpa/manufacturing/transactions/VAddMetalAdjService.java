package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddMetalAdjRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalInvService;

@Service
@Repository
@Transactional
public class VAddMetalAdjService implements IVAddMetalAdjService {

	
	@Autowired
	private IVAddMetalAdjRepository vAddMetalAdjRepository;
	
	@Autowired
	private IVAddDtService vadddtService;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private IMetalPurchaseDtService metalPurchaseDtService;
	
	
	@Autowired
	private IVAddMetalInvService vAddMetalInvService;
	
	@Override
	public List<VAddMetalAdj> findAll() {
		return vAddMetalAdjRepository.findAll();
	}

	@Override
	public Page<VAddMetalAdj> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		return null;
	}

	@Override
	public void save(VAddMetalAdj vAddMetalAdj) {
		vAddMetalAdjRepository.save(vAddMetalAdj);
	}

	@Override
	public void delete(int id) {
		
		vAddMetalAdjRepository.delete(id);
		
	}

	@Override
	public Long count() {
		return vAddMetalAdjRepository.count();
	}

	@Override
	public VAddMetalAdj findOne(int id) {
		return vAddMetalAdjRepository.findOne(id);
	}

	@Override
	public List<VAddMetalAdj> findByVAddMetalInv(
			VAddMetalInv vAddMetalInv) {
		return vAddMetalAdjRepository.findByVAddMetalInv(vAddMetalInv);
	}

	@Override
	public String saveMetalAdjustment(String tempMetalInwId, String adjustWt,
			Integer costMtIdPk, Integer vAddMetalInvPkId, Principal principal) {
		
		String retVal = "-1";
		
		if(tempMetalInwId.length() > 0){
			
			String metalInwardId[] 	= tempMetalInwId.split(",");
			String vAdjustWt[]	  	= adjustWt.split(",");
			
			
			CostingMt costingMt = costingMtService.findOne(costMtIdPk);
			
			VAddMetalInv vAddMetalInv = vAddMetalInvService.findOne(vAddMetalInvPkId);
			
			for(int i = 0;i<metalInwardId.length;i++){
				
			MetalPurchaseDt metalPurchaseDt = metalPurchaseDtService.findOne(Integer.parseInt(metalInwardId[i]));
			
			if(metalPurchaseDt.getBalance() >= Double.parseDouble(vAdjustWt[i].toString())){
			
			VAddMetalAdj vAddMetalAdjNew = new VAddMetalAdj();
			
			vAddMetalAdjNew.setCostingMt(costingMt);
			vAddMetalAdjNew.setvAddMetalInv(vAddMetalInv);
			vAddMetalAdjNew.setMetalPurchaseDt(metalPurchaseDt);
			vAddMetalAdjNew.setMetalPurchaseMt(metalPurchaseDt.getMetalPurchaseMt());
			vAddMetalAdjNew.setMetalInvNo(metalPurchaseDt.getMetalPurchaseMt().getInvNo());
			vAddMetalAdjNew.setMetalInvDate(metalPurchaseDt.getMetalPurchaseMt().getInvDate());
			vAddMetalAdjNew.setAdjustmentWt(Double.parseDouble(vAdjustWt[i]));
			vAddMetalAdjNew.setCreatedBy(principal.getName());
			vAddMetalAdjNew.setCreatedDt(new java.util.Date());
			save(vAddMetalAdjNew);
				
			
			//--adjustmet true of VAddMetalInv--//----//
			
			vAddMetalInv.setAdjusted(true);
			vAddMetalInvService.save(vAddMetalInv);
			
			
			//-----update InwardDt metal-------//
			
			metalPurchaseDt.setBalance( Math.round((metalPurchaseDt.getBalance() - Double.parseDouble(vAdjustWt[i]))*1000.0)/1000.0);
			metalPurchaseDtService.save(metalPurchaseDt);
			
			
			costingMt.setVaddIn999(vAddMetalAdjNew.getMetalPurchaseDt().getIn999());
			costingMtService.save(costingMt);
			
			
			List<VAddDt>vAddDts = vadddtService.findByCostingMtAndDeactive(costingMt, false);
			
			for(VAddDt vAddDt :vAddDts){
				vadddtService.applyMetal(vAddDt);
				vadddtService.updateValueAddition(vAddDt);
			}
			
			}else{
				retVal = "-3";
			}

		}
					
			
		}else{
			retVal = "-2";
		}
		
		return retVal;
	}

	@Override
	public VAddMetalAdj findByCostingMtAndVAddMetalInv(CostingMt costingMt,
			VAddMetalInv vAddMetalInv) {
		// TODO Auto-generated method stub
		return vAddMetalAdjRepository.findByCostingMtAndVAddMetalInv(costingMt, vAddMetalInv);
	}

	
	
	

}

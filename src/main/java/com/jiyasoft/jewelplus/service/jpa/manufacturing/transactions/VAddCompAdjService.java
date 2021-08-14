package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddCompAdjRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompInvService;

@Service
@Repository
@Transactional
public class VAddCompAdjService implements IVAddCompAdjService {

	
	@Autowired
	private IVAddCompAdjRepository vAddCompAdjRepository;
	
	@Autowired
	private IVAddCompInvService vAddCompInvService;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private IComponentPurchaseDtService componentPurchaseDtService;
	
	@Override
	public List<VAddCompAdj> findAll() {
		return vAddCompAdjRepository.findAll();
	}

	@Override
	public Page<VAddCompAdj> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		return null;
	}

	@Override
	public void save(VAddCompAdj vAddCompAdj) {
		vAddCompAdjRepository.save(vAddCompAdj);
	}

	@Override
	public void delete(int id) {
	//	VAddCompAdj vAddCompAdj = vAddCompAdjRepository.findOne(id);
	//	vAddCompAdj.setDeactive(true);
	//	vAddCompAdj.setDeactiveDt(new java.util.Date());
	//	vAddCompAdjRepository.save(vAddCompAdj);
		
		vAddCompAdjRepository.delete(id);
	}

	@Override
	public Long count() {
		return vAddCompAdjRepository.count();
	}

	@Override
	public VAddCompAdj findOne(int id) {
		return vAddCompAdjRepository.findOne(id);
	}

	@Override
	public List<VAddCompAdj> findByVAddCompInvAndDeactive(
			VAddCompInv vAddCompInv, Boolean deactive) {
		return vAddCompAdjRepository.findByVAddCompInvAndDeactive(vAddCompInv, deactive);
	}

	@Override
	public String saveCompAdjustment(String tempCompPurcId, Double adjustWt, Integer costMtIdPk,
			Integer vAddCompInvPkId,Double totCompPcs,Principal principal) {
	
		String retVal = "-1";
		
		
			DecimalFormat df = new DecimalFormat("#.###");
		
		
			if(tempCompPurcId.length() > 0){
				
				String compPurcId[] = tempCompPurcId.split(",");
				
				
				CostingMt costingMt = costingMtService.findOne(costMtIdPk);
				VAddCompInv vAddCompInv = vAddCompInvService.findOne(vAddCompInvPkId);
				
				if(vAddCompInv.getAdjusted().equals(true)){
					return "-2";
				}
				
				for(int i = 0;i<compPurcId.length;i++){
					
				ComponentPurchaseDt componentPurchaseDt = componentPurchaseDtService.findOne(Integer.parseInt(compPurcId[i]));
				
				if(componentPurchaseDt.getBalance() >= adjustWt){
				
				VAddCompAdj vAddCompAdjNew = new VAddCompAdj();
				
				vAddCompAdjNew.setCostingMt(costingMt);
				vAddCompAdjNew.setvAddCompInv(vAddCompInv);
				vAddCompAdjNew.setComponentPurchaseDt(componentPurchaseDt);
				vAddCompAdjNew.setCompInvNo(componentPurchaseDt.getComponentPurchaseMt().getInvNo());
				vAddCompAdjNew.setCompInvDate(componentPurchaseDt.getComponentPurchaseMt().getInvDate());
				vAddCompAdjNew.setAdjustmentWt(adjustWt);
				vAddCompAdjNew.setAdjustmentPcs(totCompPcs);
				vAddCompAdjNew.setCreatedBy(principal.getName());
				vAddCompAdjNew.setCreatedDt(new java.util.Date());
				vAddCompAdjNew.setDeactive(false);
				
				save(vAddCompAdjNew);
				
				
				//--adjustmet true of VAddCompInv--//----//
				
				vAddCompInv.setAdjusted(true);
				vAddCompInvService.save(vAddCompInv);
				
				componentPurchaseDt.setBalance(Double.parseDouble(df.format(componentPurchaseDt.getBalance()-adjustWt)));
				componentPurchaseDtService.save(componentPurchaseDt);
				
				retVal = "1";
				
				break;
					
				}
			}
						
				
			}else{
				retVal = "-3";
			}
			
		

		return retVal;
	}

}

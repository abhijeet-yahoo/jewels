package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IComponentPurchaseDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseMtService;

@Service
@Transactional
@Repository
public class ComponentPurchaseDtService implements IComponentPurchaseDtService{
	
	@Autowired
	private IComponentPurchaseDtRepository componentPurchaseDtRepository;
	
	@Autowired
	private IComponentPurchaseMtService componentPurchaseMtService;

	@Override
	public List<ComponentPurchaseDt> findByComponentPurchaseMtAndDeactive(ComponentPurchaseMt componentPurchaseMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return componentPurchaseDtRepository.findByComponentPurchaseMtAndDeactive(componentPurchaseMt, deactive);
	}

	@Override
	public ComponentPurchaseDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return componentPurchaseDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(ComponentPurchaseDt ComponentPurchaseDt) {
		// TODO Auto-generated method stub
		componentPurchaseDtRepository.save(ComponentPurchaseDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		ComponentPurchaseDt componentPurchaseDt = componentPurchaseDtRepository.findOne(id);
		componentPurchaseDt.setDeactive(true);
		componentPurchaseDt.setDeactiveDt(new Date());
		componentPurchaseDtRepository.save(componentPurchaseDt);
	}

	@Override
	public ComponentPurchaseDt findOne(int id) {
		// TODO Auto-generated method stub
		return componentPurchaseDtRepository.findOne(id);
	}

	@Override
	public String componentPurchaseDtSave(ComponentPurchaseDt componentPurchaseDt, Integer id, Integer mtId,
			Principal principal) {
		// TODO Auto-generated method stub

		String retVal="-1";
		
		ComponentPurchaseMt componentPurchaseMt = componentPurchaseMtService.findOne(mtId);
		try {
			

			if (id == null || id.equals("") || (id != null && id == 0)) {
				componentPurchaseDt.setCreatedBy(principal.getName());
				componentPurchaseDt.setCreatedDt(new java.util.Date());
				componentPurchaseDt.setUniqueId(new Date().getTime());
				
				retVal = "1";
			} else {
				
				componentPurchaseDt.setModiBy(principal.getName());
				componentPurchaseDt.setModiDt(new java.util.Date());
				retVal = "2";
			}

			componentPurchaseDt.setPurityConv(componentPurchaseDt.getPurity().getPurityConv());
			componentPurchaseDt.setBalance(componentPurchaseDt.getMetalWt());	
			componentPurchaseDt.setComponentPurchaseMt(componentPurchaseMt);

			save(componentPurchaseDt);
		
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String componentPurchaseDtDelete(Integer id, Principal principal) {

		String retVal ="-1";
		
		try {
		
			ComponentPurchaseDt componentPurchaseDt = findOne(id);
			  Double prevBalance = Math.round((componentPurchaseDt.getMetalWt()*componentPurchaseDt.getPurity().getPurityConv())*1000.0)/1000.0;
			  
			  if(componentPurchaseDt.getBalance()<prevBalance){
				  return "-2"; 
			  }
			  
			delete(id);
		
			retVal ="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public List<ComponentPurchaseDt> findByMetalAndComponentAndPurityAndColorAndDeactive(Metal metal,
			Component component, Purity purity, Color color, Boolean deactive) {
		// TODO Auto-generated method stub
		return componentPurchaseDtRepository.findByMetalAndComponentAndPurityAndColorAndDeactive(metal, component, purity, color, deactive);
	}

	}



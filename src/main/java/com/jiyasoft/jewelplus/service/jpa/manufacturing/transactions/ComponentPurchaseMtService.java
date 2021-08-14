package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QComponentPurchaseMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IComponentPurchaseMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseMtService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
@Repository
public class ComponentPurchaseMtService implements IComponentPurchaseMtService {
	
	@Autowired
	private IComponentPurchaseMtRepository componentPurchaseMtRepository;
	
	@Autowired
	private IComponentPurchaseDtService componentPurchaseDtService;

	@Override
	public ComponentPurchaseMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return componentPurchaseMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public ComponentPurchaseMt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return componentPurchaseMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(ComponentPurchaseMt componentPurchaseMt) {
		// TODO Auto-generated method stub
		componentPurchaseMtRepository.save(componentPurchaseMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
		ComponentPurchaseMt componentPurchaseMt = componentPurchaseMtRepository.findOne(id);
		componentPurchaseMt.setDeactive(true);
		componentPurchaseMt.setDeactiveDt(new Date());
		componentPurchaseMtRepository.save(componentPurchaseMt);
	}

	@Override
	public ComponentPurchaseMt findOne(int id) {
		// TODO Auto-generated method stub
		return componentPurchaseMtRepository.findOne(id);
	}

	@Override
	public Page<ComponentPurchaseMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		// TODO Auto-generated method stub

		QComponentPurchaseMt qComponentPurchaseMt = QComponentPurchaseMt.componentPurchaseMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qComponentPurchaseMt.deactive.eq(false);
			}else{
				expression = qComponentPurchaseMt.deactive.eq(false).and(qComponentPurchaseMt.invNo.like("%" + search + "%").or(qComponentPurchaseMt.party.name.like("%" + search + "%"))
						.or(qComponentPurchaseMt.inwardType.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qComponentPurchaseMt.invNo.like("%" + search + "%").or(qComponentPurchaseMt.party.name.like("%" + search + "%")).or(qComponentPurchaseMt.inwardType.name.like("%" + search + "%"));
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("invNo")) {
			sort = "invNo";
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
	
		Page<ComponentPurchaseMt> componentPurchaseList =(Page<ComponentPurchaseMt>) componentPurchaseMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		
		return componentPurchaseList;

	}

	@Override
	public String componentPurchaseMtDelete(int id) {
		
		String retVal ="-1";
		
		try {
		

			ComponentPurchaseMt componentPurchaseMt = findOne(id);
			
			List<ComponentPurchaseDt> componentPurchaseDts = componentPurchaseDtService.findByComponentPurchaseMtAndDeactive(componentPurchaseMt, false);
			for (ComponentPurchaseDt componentPurchaseDt : componentPurchaseDts) {
				
				Double prevBalance = Math.round((componentPurchaseDt.getMetalWt()*componentPurchaseDt.getPurity().getPurityConv())*1000.0)/1000.0;
				  
				  if(componentPurchaseDt.getBalance() < prevBalance){
					  return "-2"; 
				  }
				componentPurchaseDtService.delete(componentPurchaseDt.getId());
			}
			delete(id);
			
			retVal="1";
		} catch (Exception e) {
			// TODO: handle exception
		}return retVal;
	}

}

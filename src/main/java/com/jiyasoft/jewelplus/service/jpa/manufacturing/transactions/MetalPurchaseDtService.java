package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QMetalPurchaseDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMetalPurchaseDtReporsity;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseMtService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MetalPurchaseDtService  implements IMetalPurchaseDtService{
	
	@Autowired
	private IMetalPurchaseDtReporsity metalPurchaseDtReporsity;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IMetalPurchaseMtService metalPurchaseMtService;

	@Override
	public void save(MetalPurchaseDt metalPurchaseDt) {
		// TODO Auto-generated method stub
		metalPurchaseDtReporsity.save(metalPurchaseDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
		MetalPurchaseDt metalPurchaseDt =  metalPurchaseDtReporsity.findOne(id);
		metalPurchaseDt.setDeactive(true);
		metalPurchaseDt.setDeactiveDt(new Date());
		metalPurchaseDtReporsity.save(metalPurchaseDt);
		
	}

	@Override
	public MetalPurchaseDt findOne(int id) {
		// TODO Auto-generated method stub
		return metalPurchaseDtReporsity.findOne(id);
	}


	@Override
	public List<MetalPurchaseDt> findByMetalPurchaseMtAndDeactive(MetalPurchaseMt metalPurchaseMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return metalPurchaseDtReporsity.findByMetalPurchaseMtAndDeactive(metalPurchaseMt, deactive);
	}

	@Override
	public String metalPurchaseSave(MetalPurchaseDt metalPurchaseDt, Integer id, String pInvNo,Principal principal) {
	
		String retVal="-1";
				
		try {
			
			Purity purity =  purityService.findOne(metalPurchaseDt.getPurity().getId());
			

			if (id == null || id.equals("") || (id != null && id == 0)) {
				metalPurchaseDt.setCreatedBy(principal.getName());
				metalPurchaseDt.setCreatedDt(new java.util.Date());
				metalPurchaseDt.setPurityConv(purity.getPurityConv());
				metalPurchaseDt.setBalance(Math.round((metalPurchaseDt.getInvWt()*purity.getPurityConv())*1000.0)/1000.0);
				retVal = "1"; 
			

			} else {
				
				metalPurchaseDt.setModiBy(principal.getName());
				metalPurchaseDt.setModiDt(new java.util.Date());
				metalPurchaseDt.setPurityConv(metalPurchaseDt.getPurity().getPurityConv());
				
				//metalPurchaseDt.setBalance(Math.round((invWtt*purityService.findOne(purityyId).getPurityConv())*1000.0)/1000.0);
			
				retVal = "-1"; 

			}

			metalPurchaseDt.setMetalPurchaseMt(metalPurchaseMtService.findByInvNoAndDeactive(pInvNo, false));
		
			save(metalPurchaseDt);
			
			
			
						
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String metalPurchaseDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetalPurchaseDt findByMetalPurchaseMtAndMetal(MetalPurchaseMt metalPurchaseMt, Metal metal) {
		// TODO Auto-generated method stub
		return metalPurchaseDtReporsity.findByMetalPurchaseMtAndMetal(metalPurchaseMt, metal);
	}

	@Override
	public List<MetalPurchaseDt> findByMetalAndDeactive(Metal metal, Boolean deactive) {
		// TODO Auto-generated method stub
		return metalPurchaseDtReporsity.findByMetalAndDeactive(metal, deactive);
	}

	@Override
	public Page<MetalPurchaseDt> balanceInvoice(Integer limit, Integer offset, String sort, String order, String search,
			Metal metal,Boolean in999) {
		
		QMetalPurchaseDt qMetalPurchaseDt = QMetalPurchaseDt.metalPurchaseDt;
		BooleanExpression expression=null;
		if (search == null) {
				expression = qMetalPurchaseDt.deactive.eq(false).and(qMetalPurchaseDt.metal.eq(metal)).and(qMetalPurchaseDt.balance.gt(0)).and(qMetalPurchaseDt.in999.eq(in999));
			}else{
				expression = qMetalPurchaseDt.deactive.eq(false).and(qMetalPurchaseDt.metal.eq(metal)).and(qMetalPurchaseDt.balance.gt(0))
						.and(qMetalPurchaseDt.metalPurchaseMt.invNo.like("%" +search+"%")).and(qMetalPurchaseDt.in999.eq(in999));
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
		} 
	
		Page<MetalPurchaseDt> metalPurchaseDtList =(Page<MetalPurchaseDt>) metalPurchaseDtReporsity.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		

	
		return metalPurchaseDtList;
	}

}

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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QMetalPurchaseMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMetalPurchaseMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseMtService;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class MetalPurchaseMtService implements IMetalPurchaseMtService {
	
	@Autowired
	private IMetalPurchaseMtRepository metalPurchaseMtRepository;
	
	@Autowired
	private IMetalPurchaseDtService metalPurchaseDtService;

	@Override
	public void save(MetalPurchaseMt metalPurchaseMt) {
		// TODO Auto-generated method stub
		metalPurchaseMtRepository.save(metalPurchaseMt);
	}

	@Override
	public void delete(int id) {
		MetalPurchaseMt metalPurchaseMt =  metalPurchaseMtRepository.findOne(id);
		metalPurchaseMt.setDeactive(true);
		metalPurchaseMt.setDeactiveDt(new Date());
		metalPurchaseMtRepository.save(metalPurchaseMt);
	}

	@Override
	public MetalPurchaseMt findOne(int id) {
		// TODO Auto-generated method stub
		return metalPurchaseMtRepository.findOne(id);
	}

	@Override
	public MetalPurchaseMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return metalPurchaseMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public MetalPurchaseMt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return metalPurchaseMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Page<MetalPurchaseMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		
		QMetalPurchaseMt qMetalPurchaseMt = QMetalPurchaseMt.metalPurchaseMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qMetalPurchaseMt.deactive.eq(false);
			}else{
				expression = qMetalPurchaseMt.deactive.eq(false).and(qMetalPurchaseMt.invNo.like("%" + search + "%").or(qMetalPurchaseMt.party.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qMetalPurchaseMt.invNo.like("%" + search + "%").or(qMetalPurchaseMt.party.name.like("%" + search + "%"));
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
	
		Page<MetalPurchaseMt> metalPurchaseList =(Page<MetalPurchaseMt>) metalPurchaseMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return metalPurchaseList;
	}

	@Override
	public String metalPurcDelete(int id) {

		String retVal ="-1";
		
		try {
			
			delete(id);

			MetalPurchaseMt metalPurchaseMt = findOne(id);
			List<MetalPurchaseDt> metalPurchaseDts = metalPurchaseDtService.findByMetalPurchaseMtAndDeactive(metalPurchaseMt, false);

			for (MetalPurchaseDt metalPurchaseDt : metalPurchaseDts) {
		
				metalPurchaseDtService.delete(metalPurchaseDt.getId());
			}
			
			retVal ="1";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		return retVal;
	}

}

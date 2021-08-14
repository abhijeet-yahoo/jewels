package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BagGenQty;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QBagGenQty;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IBagGenQtyRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IBagGenQtyService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
@Repository
public class BagGenQtyService implements IBagGenQtyService{
	
	@Autowired
	private IBagGenQtyRepository bagGenQtyRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void save(BagGenQty bagGenQty) {
		// TODO Auto-generated method stub
		bagGenQtyRepository.save(bagGenQty);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		BagGenQty bagGenQty = bagGenQtyRepository.findOne(id);
		bagGenQty.setDeactive(true);
		bagGenQty.setDeactiveDt(new java.util.Date());
		bagGenQtyRepository.save(bagGenQty);
	}

	@Override
	public BagGenQty findOne(int id) {
		// TODO Auto-generated method stub
		return bagGenQtyRepository.findOne(id);
	}

	@Override
	public Page<BagGenQty> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		// TODO Auto-generated method stub
		
		QBagGenQty qBagGenQty = QBagGenQty.bagGenQty;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qBagGenQty.deactive.eq(false);
			}else{
				expression = qBagGenQty.deactive.eq(false).and(qBagGenQty.fromCtsWt.like("%" + search + "%").or(qBagGenQty.toCtsWt.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qBagGenQty.fromCtsWt.like("%" + search + "%").or(qBagGenQty.toCtsWt.like("%" + search + "%"));
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
		} else if (sort.equalsIgnoreCase("fromCtsWt")) {
			sort = "fromCtsWt";
		} else if (sort.equalsIgnoreCase("toCtsWt")) {
			sort = "toCtsWt";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<BagGenQty> bagGenQtyList =(Page<BagGenQty>) bagGenQtyRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		
		return bagGenQtyList;

		
	}

	@Override
	public String checkDuplicate(Double fromCtsWt, Double toCtsWt, Integer id) {
		// TODO Auto-generated method stub
		String retVal="false";
		@SuppressWarnings("unchecked")
		TypedQuery<BagGenQty> query =  (TypedQuery<BagGenQty>)entityManager.createNativeQuery("select * from baggenqty"
						+ " where '"+fromCtsWt+"'  between fromCtsWt and toCtsWt and deactive =0 ",BagGenQty.class);
		
		
		
		List<BagGenQty> bagGenQtys = query.getResultList();
		if(bagGenQtys.size()>0){
			if(id == null || id.equals("") || (id != null && id == 0)){
			
				retVal="true";
				
			}else{
			
				if(bagGenQtys.get(0).getId().equals(id)){
					retVal="false";
				}
				
			}
		}else{
			
			@SuppressWarnings("unchecked")
			TypedQuery<BagGenQty> query1 =  (TypedQuery<BagGenQty>)entityManager.createNativeQuery("select * from baggenqty"
					+ " where '"+toCtsWt+"' between fromCtsWt and toCtsWt and deactive =0 ",BagGenQty.class);
	
			List<BagGenQty> bagGenQtys2 = query1.getResultList();
			if(bagGenQtys2.size()>0){
				if(id == null || id.equals("") || (id != null && id == 0)){
					
					retVal="true";
					
				}else{
				
					if(bagGenQtys2.get(0).getId().equals(id)){
						retVal="false";
					}
					
				}	
				
			}

		}
		
		
		return retVal;
	}

	@Override
	public BagGenQty findByQty(Double qty) {
		// TODO Auto-generated method stub
		return findByQty(qty);
	}

}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ILookMastRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookMastService;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class LookMastService implements ILookMastService {
	
	@Autowired
	private ILookMastRepository lookMastRepository;

	@Override
	public Page<LookMast> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {
		
		QLookMast qLookMast = QLookMast.lookMast;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qLookMast.deactive.eq(false);
			}else{
				expression = qLookMast.deactive.eq(false).and(qLookMast.name.like("%" + search + "%").or(qLookMast.code.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qLookMast.name.like("%" + search + "%").or(qLookMast.code.like("%" + search + "%"));
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
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("code")) {
			sort = "code";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
	
		Page<LookMast> lookMastList =(Page<LookMast>) lookMastRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		

	
		return lookMastList;
		
	}

	@Override
	public LookMast findOne(int id) {
		// TODO Auto-generated method stub
		return lookMastRepository.findOne(id);
	}

	@Override
	public void save(LookMast lookmast) {
		lookMastRepository.save(lookmast);		
	}

	@Override
	public void delete(int id) {
		LookMast lookmast = lookMastRepository.findOne(id);
		lookmast.setDeactive(true);
		lookmast.setDeactiveDt(new java.util.Date());
		lookMastRepository.save(lookmast);
	}

	@Override
	public LookMast findByName(String lookmastName) {
		// TODO Auto-generated method stub
		return lookMastRepository.findByName(lookmastName);
	}

	@Override
	public Map<Integer, String> getLookList() {
		Map<Integer, String> lookMap = new LinkedHashMap<Integer, String>();
		Page<LookMast> lookList =findActiveLookSortByName();
		
		for(LookMast lookMast: lookList){
			lookMap.put(lookMast.getId(), lookMast.getName());
		}
		return lookMap;
	}

	@Override
	public Page<LookMast> findActiveLookSortByName() {

		QLookMast qLookMast = QLookMast.lookMast;
		BooleanExpression expression=qLookMast.deactive.eq(false);
		Page<LookMast> lookmastList=lookMastRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return lookmastList;
	}
		
	

}

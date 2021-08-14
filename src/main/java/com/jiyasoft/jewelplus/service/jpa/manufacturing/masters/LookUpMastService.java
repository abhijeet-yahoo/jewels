package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLookUpMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ILookupMastRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class LookUpMastService implements ILookUpMastService {
	
	@Autowired
	private ILookupMastRepository lookupMastRepository;

	@Override
	public List<LookUpMast> findAll() {
		// TODO Auto-generated method stub
		return lookupMastRepository.findAll();
	}

	@Override
	public void save(LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		lookupMastRepository.save(lookUpMast);
	}

	@Override
	public void delete(int id) {
		LookUpMast lookUpMast = lookupMastRepository.getOne(id);
		lookUpMast.setDeactive(true);
		lookUpMast.setDeactiveDt(new java.util.Date());
		lookupMastRepository.save(lookUpMast);
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return lookupMastRepository.count();
	}

	@Override
	public LookUpMast findOne(int id) {
		// TODO Auto-generated method stub
		return lookupMastRepository.findOne(id);
	}

	@Override
	public Page<LookUpMast> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		QLookUpMast qLookupMast = QLookUpMast.lookUpMast;
		BooleanExpression expression =null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qLookupMast.deactive.eq(false);
			}else{
				expression = qLookupMast.deactive.eq(false).and(qLookupMast.name.like("%" + search + "%").or(qLookupMast.fieldValue.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qLookupMast.name.like("%" + search + "%").or(qLookupMast.fieldValue.like("%" + search + "%"));
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
		} else if (sort.equalsIgnoreCase("fieldValue")) {
			sort = "fieldValue";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<LookUpMast> lookupMastList =(Page<LookUpMast>) lookupMastRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return lookupMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		QLookUpMast qLookupMast= QLookUpMast.lookUpMast;
		BooleanExpression expression = qLookupMast.deactive.eq(false);
		
		if(onlyActive){
		  if(colName == null && colValue==null ){
				expression = qLookupMast.deactive.eq(false);			
			}else{
				expression = qLookupMast.deactive.eq(false).and(qLookupMast.name.like("%" + colValue + "%").or(qLookupMast.fieldValue.like("%" + colValue + "%")));
			}
		}else{
			if (colValue == null) {
				expression = null;
			} else {
				expression = qLookupMast.name.like("%" + colValue + "%").or(qLookupMast.fieldValue.like("%" + colValue + "%"));
			}
		}
		
		return lookupMastRepository.count(expression);
	}

	@Override
	public List<LookUpMast> findByName(String name) {
		// TODO Auto-generated method stub
		return lookupMastRepository.findByName(name);
	}

	@Override
	public LookUpMast findByNameAndFieldValueAndDeactive(String name, String fieldValue,Boolean deactive) {
		// TODO Auto-generated method stub
		return lookupMastRepository.findByNameAndFieldValueAndDeactive(name, fieldValue,deactive);
	}

	@Override
	public Map<String, String> getTypeList() {
		Set<String> lookUpSet = new HashSet<String>();
		Map<String, String> lookupMap = new LinkedHashMap<String, String>();
		
		Page<LookUpMast> lookupPage = getTypeLists();
		for(LookUpMast lookUp:lookupPage){
			lookUpSet.add(lookUp.getName());
		}
		
		for(String stringSet:lookUpSet){
			lookupMap.put(stringSet, stringSet);
		}
		
		return lookupMap;
	}

	@Override
	public Page<LookUpMast> getTypeLists() {
		QLookUpMast qLookupMast= QLookUpMast.lookUpMast;
		BooleanExpression expression = qLookupMast.deactive.eq(false);
		Page<LookUpMast> lookupList = lookupMastRepository.findAll(expression,new PageRequest(0, 1000,Direction.ASC,"fieldValue"));
		return lookupList;
	}

	@Override
	public Map<Integer, String> getActiveLookUpMastByType(String Type) {
		Map<Integer, String> lookupMap = new LinkedHashMap<Integer,String>();
		Page<LookUpMast> lookuplist = findLookUpMastByType(Type);
		for(LookUpMast lookup:lookuplist){
			lookupMap.put(lookup.getId(),lookup.getFieldValue());
		}
		return lookupMap;
	}

	@Override
	public Page<LookUpMast> findLookUpMastByType(String Type) {
		QLookUpMast qLookupMast= QLookUpMast.lookUpMast;
		BooleanExpression expression = qLookupMast.deactive.eq(false).and(qLookupMast.name.equalsIgnoreCase(Type));
		Page<LookUpMast> lookupList = lookupMastRepository.findAll(expression,new PageRequest(0, 1000,Direction.ASC,"fieldValue"));
		return lookupList;
	}

	@Override
	public LookUpMast findByFieldValueAndDeactive(String fieldValue, Boolean deactive) {
		// TODO Auto-generated method stub
		return lookupMastRepository.findByFieldValueAndDeactive(fieldValue, deactive);
	}

	@Override
	public LookUpMast findByNameAndCodeAndDeactive(String name, String code, Boolean deactive) {
		// TODO Auto-generated method stub
		return lookupMastRepository.findByNameAndCodeAndDeactive(name, code, deactive);
	}

	
	

}

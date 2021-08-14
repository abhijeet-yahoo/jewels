package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LedgerGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLedgerGroup;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ILedgerGroupRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILedgerGroupService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class LedgerGroupService implements ILedgerGroupService{

	@Autowired
	private ILedgerGroupRepository ledgerGroupRepository;

	@Override
	public List<LedgerGroup> findAll(Integer compId) {
		QLedgerGroup qLedgerGroup = QLedgerGroup.ledgerGroup;
		BooleanExpression expression = qLedgerGroup.compId.eq(compId);
		return (List<LedgerGroup>) ledgerGroupRepository.findAll(expression);
	}



	@Override
	public void save(LedgerGroup ledgerGroup) {
		ledgerGroupRepository.save(ledgerGroup);
		
	}

	@Override
	public void delete(int id) {
	/*	LedgerGroup ledgerGroup =ledgerGroupRepository.findOne(id);
		ledgerGroup.setDeactive(true);
		ledgerGroup.setDeactiveDt(new java.util.Date());*/
		ledgerGroupRepository.delete(id);
	}

	@Override
	public Long count(Integer compId) {
		QLedgerGroup qLedgerGroup = QLedgerGroup.ledgerGroup;
		BooleanExpression expression = qLedgerGroup.compId.eq(compId);
		return ledgerGroupRepository.count(expression);
	}

	@Override
	public LedgerGroup findOne(int id) {
		return ledgerGroupRepository.findOne(id);
	}

	@Override
	public LedgerGroup findByName(String name) {
		return ledgerGroupRepository.findByName(name);
	}

	
	@Override
	public List<String> getMainGroupList() {
		List<String> ledgerGroupList = new ArrayList<String>();
		Page<LedgerGroup> ledgerGroupPageList = findActiveMainGroupSortByName();

		for (LedgerGroup ledgerGroup : ledgerGroupPageList) {
			ledgerGroupList.add(ledgerGroup.getName());
		}

		return ledgerGroupList;
	}
	
	
	@Override
	public Map<String, String> getMainLedgerGroupList() {
		Map<String, String> ledgerGroupMap = new LinkedHashMap<String, String>();
		Page<LedgerGroup> ledgerGroupPageList = findActiveMainGroupSortByName();

		for (LedgerGroup ledgerGroup : ledgerGroupPageList) {
			ledgerGroupMap.put(ledgerGroup.getName(), ledgerGroup.getName());
		}

		return ledgerGroupMap;
	}
	
	Ordering<Map.Entry<String, String>> byMapValues = new Ordering<Map.Entry<String, String>>() {
		@Override
		public int compare(Map.Entry<String, String> left, Map.Entry<String, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};
	
	
	@Override
	public String getMainLedgerGroupListDropDown() {

		StringBuilder sb = new StringBuilder();
		Map<String, String> ledgerGroupMap = getMainLedgerGroupList();

		List<Map.Entry<String, String>> ledgerGroupMapGv = Lists.newArrayList(ledgerGroupMap.entrySet());
	    Collections.sort(ledgerGroupMapGv, byMapValues);

		sb.append("<select id=\"mainGroup\" name=\"mainGroup\" class=\"form-control select2\">");
		sb.append("<option value=\"\">--Select Main Group</option>");

		Iterator<Entry<String, String>> iterator = ledgerGroupMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();

	}

	
	@Override
	public Page<LedgerGroup> findActiveMainGroupSortByName() {
		QLedgerGroup qLedgerGroup =QLedgerGroup.ledgerGroup;
		BooleanExpression expression = qLedgerGroup.deactive.eq(false)
				.and(qLedgerGroup.isGroup.eq(true));
		
		Page<LedgerGroup> ledgerGroupList = ledgerGroupRepository.findAll(expression,
				new PageRequest(0, 10000, Direction.ASC, "mainGroup"));
		return ledgerGroupList;
	}
	

	@Override
	public Map<Integer, String> getLedgerGroupList() {
		Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
		Page<LedgerGroup> ledgerGroupLists = findActiveLedgerGroupSortByName();
		for(LedgerGroup ledgerGroup:ledgerGroupLists){
			mapList.put(ledgerGroup.getId(), ledgerGroup.getName());
		}
		return mapList;
	}
	

	@Override
	public Page<LedgerGroup> findActiveLedgerGroupSortByName() {
		QLedgerGroup qLedgerGroup =QLedgerGroup.ledgerGroup;
		BooleanExpression expression = qLedgerGroup.deactive.eq(false);
		Page<LedgerGroup> ledgerGroupList = ledgerGroupRepository.findAll(expression,new PageRequest(0, 10000, Direction.ASC, "name"));
		return ledgerGroupList;
	}



	@Override
	public Page<LedgerGroup> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, 
			Boolean onlyActive) {
		
		QLedgerGroup qLedgerGroup =QLedgerGroup.ledgerGroup;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qLedgerGroup.deactive.eq(false);
			} else {
				expression = qLedgerGroup.deactive.eq(false).and(qLedgerGroup.name.like("%" + search + "%")
						.or(qLedgerGroup.mainGroup.like("%" + search + "%")).or(qLedgerGroup.ledgerGroupCode.like("%" + search + "%")));
			}
		} else {
			if (search != null) {
				expression = qLedgerGroup.name.like("%" + search + "%")
						.or(qLedgerGroup.mainGroup.like("%" + search + "%")).or(qLedgerGroup.ledgerGroupCode.like("%" + search + "%"));
			}
		}
		
		if(limit == null){
			limit = 1000;
		}
		
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		}else if (sort.equalsIgnoreCase("mainGroup")) {
			sort = "mainGroup";
		} else if (sort.equalsIgnoreCase("ledgerGroupCode")) {
			sort = "ledgerGroupCode";
		}else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}

		Page<LedgerGroup> ledgerGroupList = (Page<LedgerGroup>) ledgerGroupRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return ledgerGroupList;
	}



	@Override
	public Long countAll(String colName, String colValue, Integer compId, Boolean onlyActive) {
		
		QLedgerGroup qLedgerGroup =QLedgerGroup.ledgerGroup;
		BooleanExpression expression = qLedgerGroup.compId.eq(compId);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qLedgerGroup.deactive.eq(false).and(qLedgerGroup.compId.eq(compId));
			} else {
				expression = qLedgerGroup.deactive.eq(false).and(qLedgerGroup.compId.eq(compId)).and(qLedgerGroup.name.like("%" + colValue + "%")
						.or(qLedgerGroup.mainGroup.like("%" + colValue + "%")).or(qLedgerGroup.ledgerGroupCode.like("%" + colValue + "%")));
			}
		} else {
			if (colValue == null) {
				expression = qLedgerGroup.compId.eq(compId);
			} else {
				expression = qLedgerGroup.compId.eq(compId).and(qLedgerGroup.name.like("%" + colValue + "%")
						.or(qLedgerGroup.mainGroup.like("%" + colValue + "%")).or(qLedgerGroup.ledgerGroupCode.like("%" + colValue + "%")));
			}
		}

		return ledgerGroupRepository.count(expression);
	}

	
	
	@Override
	public List<String> getLedgerGroupNmList() {

		List<String> ledgerGroupList = new ArrayList<String>();
		Page<LedgerGroup> ledgerGroupPageList = findActiveLedgerGroupSortByName();

		for (LedgerGroup ledgerGroup : ledgerGroupPageList) {
			ledgerGroupList.add(ledgerGroup.getName());
		}

		return ledgerGroupList;
	}



	

}

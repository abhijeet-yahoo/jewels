package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QStateMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StateMaster;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IStateMasterRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStateMasterService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StateMasterService implements IStateMasterService {

	@Autowired
	private IStateMasterRepository stateMasterRepository;
	
	@Override
	public String getStateListDropDown(Integer countryId) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> stateMap = getStateListByCountry(countryId);

		List<Map.Entry<Integer, String>> stateMapGv = Lists.newArrayList(stateMap.entrySet());
		Collections.sort(stateMapGv, byMapValuesStr);

		sb.append("<select id=\"stateMast.id\" name=\"stateMast.id\" class=\"form-control select2\">");
		sb.append("<option value=\"\">- Select State -</option>");

		java.util.Iterator<Entry<Integer, String>> iterator = stateMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@Override
	public Map<Integer, String> getStateListByCountry(Integer countryId) {
		
		Map<Integer, String> stateMap = new LinkedHashMap<Integer, String>();
		Page<StateMaster> stateList = findActiveStateByCountry(countryId);

		for (StateMaster stateMaster : stateList) {
			stateMap.put(stateMaster.getId(), stateMaster.getName());
		}

		return stateMap;
	}

	@Override
	public Page<StateMaster> findActiveStateByCountry(Integer countryId) {
		QStateMaster qStateMaster = QStateMaster.stateMaster;
		BooleanExpression expression = null;

		expression = qStateMaster.country.id.eq(countryId).and(
					qStateMaster.deactive.eq(false));
		

		Page<StateMaster> stateMasterList = stateMasterRepository.findAll(expression, new PageRequest(0, 10000, org.springframework.data.domain.Sort.Direction.ASC, "name"));

		return stateMasterList;
	}

	@Override
	public StateMaster findOne(int id) {
		// TODO Auto-generated method stub
		return stateMasterRepository.findOne(id);
	}
	
	Ordering<Map.Entry<Integer, String>> byMapValuesStr = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@Override
	public StateMaster findByName(String name) {
		// TODO Auto-generated method stub
		return stateMasterRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getStateNameList() {
		Map<Integer, String> stateMasterMap = new LinkedHashMap<Integer, String>();
		Page<StateMaster> stateMasterList = findActiveStateSortByName();

		for (StateMaster stateMaster : stateMasterList) {
			stateMasterMap.put(stateMaster.getId(), stateMaster.getName());
		}

		return stateMasterMap;
	}

	@Override
	public Page<StateMaster> findActiveStateSortByName() {
		QStateMaster qStateMaster = QStateMaster.stateMaster;
		BooleanExpression expression = qStateMaster.deactive.eq(false);

		Page<StateMaster> stateMasterList = stateMasterRepository.findAll(expression, new PageRequest(0, 10000, org.springframework.data.domain.Sort.Direction.ASC, "name"));

		return stateMasterList;
	}


}

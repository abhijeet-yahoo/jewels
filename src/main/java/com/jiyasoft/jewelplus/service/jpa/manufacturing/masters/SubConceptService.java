package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubConcept;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubConcept;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISubConceptRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubConceptService;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.Predicate;

@Service
@Repository
@Transactional
public class SubConceptService implements ISubConceptService {

	@Autowired
	private ISubConceptRepository subConceptRepository;

	@Override
	public List<SubConcept> findAll() {
		return subConceptRepository.findAll();
	}

	@Override
	public Page<SubConcept> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		// int page = (offset == 0 ? 0 : (offset / limit));
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		}

		return subConceptRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(SubConcept subConcept) {
		subConceptRepository.save(subConcept);
	}

	@Override
	public void delete(int id) {
		SubConcept subConcept = subConceptRepository.findOne(id);
		subConcept.setDeactive(true);
		subConcept.setDeactiveDt(new java.util.Date());
		subConceptRepository.save(subConcept);
	}

	@Override
	public Long count() {
		return subConceptRepository.count();
	}

	@Override
	public SubConcept findOne(int id) {
		return subConceptRepository.findOne(id);
	}

	@Override
	public SubConcept findByName(String subConceptName) {
		return subConceptRepository.findByName(subConceptName);
	}

	@Override
	public Page<SubConcept> findByConcept(Concept concept, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return subConceptRepository.findByConcept(concept, new PageRequest(
				page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Long count(Predicate predicate) {
		return subConceptRepository.count(predicate);
	}

	@Override
	public Predicate countByConcept(Integer id) {
		QSubConcept qSubConcept = QSubConcept.subConcept;

		// Deactive was not considered, since all the records have to be
		// displayed
		BooleanExpression expression = qSubConcept.concept.id.eq(id);

		return expression;
	}

	@Override
	public Map<Integer, String> getSubConceptList(Integer conId) {
		
		Map<Integer, String> subConceptMap = new HashMap<Integer, String>();
		Page<SubConcept> subConceptList = findActiveSubConceptsSortByName(conId);

		for (SubConcept subConcept : subConceptList) {
			subConceptMap.put(subConcept.getId(), subConcept.getName());
		}

		return subConceptMap;
	}

	@Override
	public String getSubConceptListDropDown(Integer conId) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> subConceptMap = getSubConceptList(conId);

		List<Map.Entry<Integer, String>> subConceptMapGv = Lists.newArrayList(subConceptMap.entrySet());
	    Collections.sort(subConceptMapGv, byMapValues);

		sb.append("<select id=\"subConcept.id\" name=\"subConcept.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Sub Concept -</option>");

		Iterator<Entry<Integer, String>> iterator = subConceptMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();

	}

	@Override
	public List<SubConcept> findActiveSubConcepts(Integer conId) {
		QSubConcept qsubConcept = QSubConcept.subConcept;
		BooleanExpression expression = null;

		if (conId == 0) {
			expression = qsubConcept.deactive.eq(false);
		} else {
			expression = qsubConcept.concept.id.eq(conId).and(
					qsubConcept.deactive.eq(false));
		}

		List<SubConcept> subConceptList = (List<SubConcept>) subConceptRepository.findAll(expression);

		return subConceptList;
	}

	@Override
	public Page<SubConcept> findActiveSubConceptsSortByName(Integer conId) {
		QSubConcept qsubConcept = QSubConcept.subConcept;
		BooleanExpression expression = null;

		if (conId == 0) {
			expression = qsubConcept.deactive.eq(false);
		} else {
			expression = qsubConcept.concept.id.eq(conId).and(qsubConcept.deactive.eq(false));
		}

		Page<SubConcept> subConceptList = subConceptRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return subConceptList;		
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QSubConcept qSubConcept = QSubConcept.subConcept;
		BooleanExpression expression = qSubConcept.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSubConcept.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qSubConcept.deactive.eq(false).and(
						qSubConcept.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qSubConcept.name.like(colValue + "%");
			}
		}

		return subConceptRepository.count(expression);
	}

	@Override
	public Page<SubConcept> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QSubConcept qSubConcept = QSubConcept.subConcept;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qSubConcept.deactive.eq(false);
			} else {
				expression = qSubConcept.deactive.eq(false).and(
						qSubConcept.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qSubConcept.name.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<SubConcept> subConceptList = (Page<SubConcept>) subConceptRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return subConceptList;
	}

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};
	
	

	@Override
	public String getSubConceptReportListDropDown(String conIds) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> subConceptMap = new HashMap<Integer, String>();
		Map<Integer, String> tmpMap = null;
		
		sb.append("{\"total\":").append(0).append(",\"rows\": [");
		
		if(conIds.length() > 0 || conIds != ""){
			String[] conIdAry = conIds.split(",");
			for (int i = 0; i < conIdAry.length; i++) {
				
				tmpMap = getSubConceptList(Integer.parseInt(conIdAry[i]));
				subConceptMap.putAll(tmpMap);
			}
	
			List<Map.Entry<Integer, String>> subConceptMapGv = Lists.newArrayList(subConceptMap.entrySet());
		    Collections.sort(subConceptMapGv, byMapValues);
	
			// new addition
		    
		    
			Iterator<Entry<Integer, String>> iterator = subConceptMapGv.iterator();
			while (iterator.hasNext()) {
				Entry<Integer, String> et = iterator.next();
	
				sb.append("{\"id\":\"").append(et.getKey())
					.append("\",\"name\":\"")
					.append(et.getValue()).append("\"},");
			}
		}
		// new addition
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

	    return str;
		
	}

	@Override
	public List<SubConcept> findByDeactive(Boolean deactive) {
		return subConceptRepository.findByDeactive(deactive);
	}

	@Override
	public SubConcept findByConceptAndName(Concept concept,
			String subConceptName) {
		// TODO Auto-generated method stub
		return subConceptRepository.findByConceptAndName(concept, subConceptName);
	}

	@Override
	public Page<SubConcept> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QSubConcept qSubConcept = QSubConcept.subConcept;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qSubConcept.deactive.eq(false);
			} else {
				expression = qSubConcept.deactive.eq(false).and(qSubConcept.name.like("%" + search + "%").or(qSubConcept.concept.name.like("%" + search+ "%")));
			}
		} else {
			if (search != null) {
				expression = qSubConcept.concept.name.like("%" + search + "%").or(qSubConcept.name.like("%" + search+ "%"));
			}
		}

		if (limit == null) {
			limit = 1000;
		}
		if (offset == null) {
			offset = 0;
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("concept")) {
			sort = "concept";
		} 
		Page<SubConcept> subConceptMastList = (Page<SubConcept>) subConceptRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		return subConceptMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		
		QSubConcept qSubConcept = QSubConcept.subConcept;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSubConcept.deactive.eq(false);
			} else {
				expression = qSubConcept.deactive.eq(false).and(qSubConcept.concept.name.like("%" + colValue + "%").or(qSubConcept.name.like("%" + colValue + "%")));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qSubConcept.concept.name.like("%" + colValue + "%").or(
						qSubConcept.name.like("%" + colValue + "%"));
			}
		}

		return subConceptRepository.count(expression);
	}
	
	

}

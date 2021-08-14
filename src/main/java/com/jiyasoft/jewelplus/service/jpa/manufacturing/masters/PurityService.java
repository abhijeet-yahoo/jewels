package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QPurity;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IPurityRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class PurityService implements IPurityService {

	@Autowired
	private IPurityRepository purityRepository;

	@Override
	public List<Purity> findAll() {
		return purityRepository.findAll();
	}

	@Override
	public Page<Purity> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return purityRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Purity purity) {
		purityRepository.save(purity);
	}

	@Override
	public void delete(int id) {
		Purity purity = purityRepository.findOne(id);
		purity.setDeactive(true);
		purity.setDeactiveDt(new java.util.Date());
		purityRepository.save(purity);
	}

	@Override
	public Long count() {
		return purityRepository.count();
	}

	@Override
	public Purity findOne(int id) {
		return purityRepository.findOne(id);
	}

	@Override
	public Purity findByName(String purityName) {
		return purityRepository.findByName(purityName);
	}

	@Override
	public Page<Purity> findByMetal(Metal metal, Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return purityRepository.findByMetal(metal,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}

	@Override
	public Long count(Predicate predicate) {
		return purityRepository.count(predicate);
	}

	@Override
	public Predicate countByMetal(Integer id) {
		QPurity qPurity = QPurity.purity;

		// Deactive was not considered, since all the records have to be
		// displayed
		BooleanExpression expression = qPurity.metal.id.eq(id);

		return expression;
	}

	@Override
	public Map<Integer, String> getPurityList(Integer metalId) {

		Map<Integer, String> purityMap = new HashMap<Integer, String>();
		List<Purity> purityList = findActivePurity(metalId);

		for (Purity purity : purityList) {
			purityMap.put(purity.getId(), purity.getName());
		}

		return purityMap;
	}

	@Override
	public List<Purity> findActivePurity(Integer metalId) {
		QPurity qpurity = QPurity.purity;
		BooleanExpression expression = null;

		if (metalId == 0) {
			expression = qpurity.deactive.eq(false);
		} else {
			expression = qpurity.metal.id.eq(metalId).and(
					qpurity.deactive.eq(false));
		}

		List<Purity> purityList = (List<Purity>) purityRepository
				.findAll(expression);

		return purityList;
	}

	// /////////

	@Override
	public Map<Integer, String> getPurityList() {

		Map<Integer, String> purityMap = new HashMap<Integer, String>();
		List<Purity> purityList = findActivePurity();

		for (Purity purity : purityList) {
			purityMap.put(purity.getId(), purity.getName());
		}

		return purityMap;
	}

	@Override
	public List<Purity> findActivePurity() {

		QPurity qPurity = QPurity.purity;
		BooleanExpression expression = qPurity.deactive.eq(false);

		List<Purity> purityList = (List<Purity>) purityRepository.findAll(expression);

		return purityList;
	}

	@Override
	public Map<String, Double> getPurityWeightList(Integer metalId) {
		Map<String, Double> purityMap = new TreeMap<String, Double>();
		List<Purity> purityList = findActivePurity(metalId);

		for (Purity purity : purityList) {
			purityMap.put(purity.getName(), purity.getWaxWtConv());
		}

		return purityMap;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QPurity qPurity = QPurity.purity;
		BooleanExpression expression = qPurity.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qPurity.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qPurity.deactive.eq(false).and(
						qPurity.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qPurity.name.like(colValue + "%");
			}
		}

		return purityRepository.count(expression);
	}

	@Override
	public Page<Purity> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive) {

		QPurity qPurity = QPurity.purity;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qPurity.deactive.eq(false);
			} else {
				expression = qPurity.deactive.eq(false).and(
						qPurity.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qPurity.name.like(name + "%");
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

		Page<Purity> purityList = (Page<Purity>) purityRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return purityList;
	}

	@Override
	public Purity findByMetalAndPure(Metal metal, Boolean pure) {
		return purityRepository.findByMetalAndPure(metal, pure);
	}

	@Override
	public Purity findByMetalAndDefPurity(Metal metal, Boolean defPurity) {
		return purityRepository.findByMetalAndDefPurity(metal, defPurity);
	}

	@Override
	public Purity getDefaultPurity() {
		QPurity qPurity = QPurity.purity;
		BooleanExpression expression = qPurity.deactive.eq(false).and(
				qPurity.defPurity.eq(true));

		return purityRepository.findOne(expression);
	}

	@Override
	public String getPurityListDropDown(Integer conId) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> purityMap = getPurityList(conId);

		List<Map.Entry<Integer, String>> purityMapGv = Lists.newArrayList(purityMap.entrySet());
	    Collections.sort(purityMapGv, byMapValues);

		sb.append("<select id=\"purity.id\" name=\"purity.id\" class=\"form-control\">");
		sb.append("<option value=\"\">Select Purity</option>");

		Iterator<Entry<Integer, String>> iterator = purityMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
		
	}
	
	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@Override
	public Purity findByDefPurityAndDeactive(Boolean defPurity, Boolean deactive) {
		return purityRepository.findByDefPurityAndDeactive(defPurity, deactive);
	}
	
	

	@Override
	public Map<Integer, String> getPurityForDeduction(List<String> puritys) {
		HashMap<Integer, String> purityList = new LinkedHashMap<Integer, String>();
		List<Purity> purityTempList = findDeductionPurity(puritys);
		
		for(Purity purity:purityTempList){
			purityList.put(purity.getId(), purity.getName());
		}
		
		return purityList;
	}

	@Override
	public List<Purity> findDeductionPurity(List<String> puritys) {
		
		QPurity qPurity = QPurity.purity;
		BooleanExpression expression = qPurity.name.in(puritys);
		List<Purity> purityList = (List<Purity>) purityRepository.findAll(expression);
		return purityList;
		
	}
	
	@Override
	public String getPurityListDropDownForDeduction(List<String> puritys) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> purityMap = getPurityForDeduction(puritys);

		List<Map.Entry<Integer, String>> purityMapGv = Lists.newArrayList(purityMap.entrySet());
	    Collections.sort(purityMapGv, byMapValues);

		sb.append("<select id=\"purity.id\" name=\"purity.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Purity -</option>");

		Iterator<Entry<Integer, String>> iterator = purityMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
		
		
	}

	@Override
	public Page<Purity> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {
		
		QPurity qPurity = QPurity.purity;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qPurity.deactive.eq(false);
			} else {
				expression = qPurity.deactive.eq(false).and(qPurity.metal.name.like("%" + search + "%").or(qPurity.name.like("%" + search+ "%")));
			}
		} else {
			if (search != null) {
				expression = qPurity.metal.name.like("%" + search + "%").or(qPurity.name.like("%" + search+ "%"));
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
		} else if (sort.equalsIgnoreCase("metal")) {
			sort = "metal";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		}
		Page<Purity> purityyMastList = (Page<Purity>) purityRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		return purityyMastList;
		
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QPurity qPurity = QPurity.purity;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qPurity.deactive.eq(false);
			} else {
				expression = qPurity.deactive.eq(false).and(qPurity.metal.name.like("%" + colValue + "%").or(qPurity.name.like("%" + colValue + "%")));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qPurity.metal.name.like("%" + colValue + "%").or(
						qPurity.name.like("%" + colValue + "%"));
			}
		}

		return purityRepository.count(expression);
		
	}
	
	

}

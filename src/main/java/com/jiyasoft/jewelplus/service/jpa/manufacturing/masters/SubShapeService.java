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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubShape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISubShapeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class SubShapeService implements ISubShapeService {

	@Autowired
	private ISubShapeRepository subShapeRepository;

	@Override
	public List<SubShape> findAll() {

		return subShapeRepository.findAll();
	}

	@Override
	public Page<SubShape> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return subShapeRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));

	}

	@Override
	public void save(SubShape subShape) {
		subShapeRepository.save(subShape);

	}

	@Override
	public void delete(int id) {
		SubShape subShape = subShapeRepository.findOne(id);
		subShape.setDeactive(true);
		subShape.setDeactiveDt(new java.util.Date());
		subShapeRepository.save(subShape);

	}

	@Override
	public Long count() {
		return subShapeRepository.count();
	}

	@Override
	public Long count(Predicate predicate) {
		return subShapeRepository.count(predicate);
	}

	@Override
	public SubShape findOne(int id) {
		return subShapeRepository.findOne(id);
	}

	@Override
	public SubShape findByName(String subShapeName) {

		return subShapeRepository.findByName(subShapeName);
	}

	@Override
	public Page<SubShape> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return subShapeRepository.findByShape(shape, new PageRequest(page,
				limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

	}

	@Override
	public Predicate countByShape(Integer id) {
		QSubShape qSubShape = QSubShape.subShape;

		// Deactive was not considered, since all the records have to be
		// displayed
		BooleanExpression expression = qSubShape.shape.id.eq(id);

		return expression;
	}

	@Override
	public Map<Integer, String> getSubShapeList(Integer conId) {
		Map<Integer, String> subShapeMap = new HashMap<Integer, String>();
		Page<SubShape> subShapesList = findActiveSubShapesSortByName(conId);

		for (SubShape subShape : subShapesList) {
			subShapeMap.put(subShape.getId(), subShape.getName());
		}

		return subShapeMap;

	}

	@Override
	public String getSubShapeListDropDown(Integer conId) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> qualityMap = getSubShapeList(conId);

		List<Map.Entry<Integer, String>> qualityMapGv = Lists.newArrayList(qualityMap.entrySet());
	    Collections.sort(qualityMapGv, byMapValues);

		sb.append("<select id=\"subShape.id\" name=\"subShape.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Sub Shape -</option>");

		Iterator<Entry<Integer, String>> iterator = qualityMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();

	}

	@Override
	public List<SubShape> findActiveSubShape(Integer conId) {

		QSubShape qSubShape = QSubShape.subShape;
		BooleanExpression expression = null;

		if (conId == 0) {
			expression = qSubShape.deactive.eq(false);
		} else {
			expression = qSubShape.shape.id.eq(conId).and(
					qSubShape.deactive.eq(false));
		}

		List<SubShape> subShapeList = (List<SubShape>) subShapeRepository
				.findAll(expression);

		return subShapeList;

	}

	@Override
	public Page<SubShape> findActiveSubShapesSortByName(Integer conId) {
	
		QSubShape qSubShape = QSubShape.subShape;
		BooleanExpression expression = qSubShape.deactive.eq(false);

		if (conId == 0) {
			expression = qSubShape.deactive.eq(false);
		} else {
			expression = qSubShape.shape.id.eq(conId).and(
					qSubShape.deactive.eq(false));
		}

		Page<SubShape> subShapeList = subShapeRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return subShapeList;
	}

	
	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QSubShape qSubShape = QSubShape.subShape;
		BooleanExpression expression = qSubShape.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSubShape.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qSubShape.deactive.eq(false).and(
						qSubShape.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qSubShape.name.like(colValue + "%");
			}
		}

		return subShapeRepository.count(expression);
	}

	@Override
	public Page<SubShape> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QSubShape qSubShape = QSubShape.subShape;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qSubShape.deactive.eq(false);
			} else {
				expression = qSubShape.deactive.eq(false).and(
						qSubShape.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qSubShape.name.like(name + "%");
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

		Page<SubShape> subShapeList = (Page<SubShape>) subShapeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return subShapeList;
	}

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};
	
	@Override
	public SubShape findByShapeAndName(Shape shape, String subShapeName) {
		return subShapeRepository.findByShapeAndName(shape, subShapeName);
	}
	
	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QSubShape qSubShape = QSubShape.subShape;
		BooleanExpression expression = qSubShape.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSubShape.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qSubShape.deactive.eq(false).and(qSubShape.name.like(colValue + "%").or(qSubShape.shape.name.like(colValue + "%")));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qSubShape.name.like(colValue + "%").or(qSubShape.shape.name.like(colValue + "%"));
			}
		}

		return subShapeRepository.count(expression);
	}
	
	public Page<SubShape> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive){
		QSubShape qSubShape = QSubShape.subShape;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (search == null) {
				expression = qSubShape.deactive.eq(false);
			}else{
				expression = qSubShape.deactive.eq(false).and(qSubShape.name.like("%" + search + "%").or(qSubShape.shape.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qSubShape.name.like("%" + search + "%").or(qSubShape.shape.name.like("%" + search + "%"));
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
		} else if (sort.equalsIgnoreCase("shape")) {
			sort = "shape";
		} 
		
		Page<SubShape> subShapeMastList =(Page<SubShape>) subShapeRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return subShapeMastList;
	}

}

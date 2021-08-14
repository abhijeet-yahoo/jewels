package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QSizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISizeGroupRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class SizeGroupService implements ISizeGroupService{
	
	@Autowired
	private ISizeGroupRepository sizeGroupRepository;

	@Override
	public List<SizeGroup> findAll() {

		return sizeGroupRepository.findAll();
	}

	@Override
	public Page<SizeGroup> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		//int page = (offset == 0 ? 0 : (offset / limit));
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		}

		return sizeGroupRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));

	}

	@Override
	public void save(SizeGroup sizeGroup) {
		sizeGroupRepository.save(sizeGroup);

	}

	@Override
	public void delete(int id) {
		SizeGroup sizeGroup = sizeGroupRepository.findOne(id);
		sizeGroup.setDeactive(true);
		sizeGroup.setDeactiveDt(new java.util.Date());
		sizeGroupRepository.save(sizeGroup);

	}

	@Override
	public Long count() {
		return sizeGroupRepository.count();
	}

	@Override
	public Long count(Predicate predicate) {
		return sizeGroupRepository.count(predicate);
	}

	@Override
	public SizeGroup findOne(int id) {
		return sizeGroupRepository.findOne(id);
	}

	@Override
	public SizeGroup findByName(String sizeGroupName) {
		if (sizeGroupName.trim().length() > 0 ) {
			return sizeGroupRepository.findByName(sizeGroupName);
		} else {
			return null;
		}
	}
	
	
	@Override
	public SizeGroup findByShapeAndNameAndDeactive(Shape shape,
			String sizeGroupName, Boolean deactive) {
		return sizeGroupRepository.findByShapeAndNameAndDeactive(shape, sizeGroupName, deactive);
	}
	
	@Override
	public Page<SizeGroup> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return sizeGroupRepository.findByShape(shape, new PageRequest(page,
				limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

	}

	@Override
	public Predicate countByShape(Integer id) {
		QSizeGroup qSizeGroup = QSizeGroup.sizeGroup;

		// Deactive was not considered, since all the records have to be
		// displayed
		BooleanExpression expression = qSizeGroup.shape.id.eq(id);

		return expression;
	}

	@Override
	public Map<Integer, String> getSizeGroupList(Integer shapeId) {
		Map<Integer, String> sizeGroupMap = new LinkedHashMap<Integer, String>();
		Page<SizeGroup> sizeGroupsList = findActiveSizeGroupSortByName(shapeId);

		for (SizeGroup sizeGroup : sizeGroupsList) {
			sizeGroupMap.put(sizeGroup.getId(), sizeGroup.getName());
		}

		return sizeGroupMap;

	}

	@Override
	public List<SizeGroup> findActiveSizeGroup(Integer shapeId) {

		QSizeGroup qSizeGroup = QSizeGroup.sizeGroup;
		BooleanExpression expression = null;

		if (shapeId == 0) {
			expression = qSizeGroup.deactive.eq(false);
		} else {
			expression = qSizeGroup.shape.id.eq(shapeId).and(
					qSizeGroup.deactive.eq(false));
		}

		List<SizeGroup> sizeGroupList = (List<SizeGroup>) sizeGroupRepository
				.findAll(expression);

		return sizeGroupList;

	}
	

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QSizeGroup qSizeGroup = QSizeGroup.sizeGroup;
		BooleanExpression expression = qSizeGroup.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSizeGroup.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qSizeGroup.deactive.eq(false).and(
						qSizeGroup.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qSizeGroup.name.like(colValue + "%");
			}
		}

		return sizeGroupRepository.count(expression);
	}

	@Override
	public Page<SizeGroup> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QSizeGroup qSizeGroup = QSizeGroup.sizeGroup;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qSizeGroup.deactive.eq(false);
			} else {
				expression = qSizeGroup.deactive.eq(false).and(
						qSizeGroup.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qSizeGroup.name.like(name + "%");
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

		Page<SizeGroup> sizeGroupList = (Page<SizeGroup>) sizeGroupRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return sizeGroupList;
	}

	@Override
	public Page<SizeGroup> findActiveSizeGroupSortByName(Integer shapeId) {
		
		QSizeGroup qSizeGroup = QSizeGroup.sizeGroup;
		BooleanExpression expression = null;
		
		
		if (shapeId == 0) {
			expression = qSizeGroup.deactive.eq(false);
		} else {
			expression = qSizeGroup.shape.id.eq(shapeId).and(
					qSizeGroup.deactive.eq(false));
		}
		
		Page<SizeGroup> sizeGroupList = sizeGroupRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		
		return sizeGroupList;
	}

	@Override
	public String getSizeGroupListDropDown(Integer shapeId) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> sizeGroupMap = getSizeGroupList(shapeId);

		List<Map.Entry<Integer, String>> sizeGroupMapGv = Lists.newArrayList(sizeGroupMap.entrySet());
	    Collections.sort(sizeGroupMapGv, byMapValues);

		sb.append("<select id=\"sizeGroup.id\" name=\"sizeGroup.id\" class=\"form-control\" onChange=\"javascript:popGroupwiseSize();\">");
		sb.append("<option value=\"\">- Select SizeGroup -</option>");

		Iterator<Entry<Integer, String>> iterator = sizeGroupMapGv.iterator();
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
	public Page<SizeGroup> searchAll(Integer limit, Integer offset,
			String sort, String order, String search,Integer shapeId, Boolean onlyActive) {
		
		QSizeGroup qSizeGroup = QSizeGroup.sizeGroup;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null && shapeId == null) {
				expression = qSizeGroup.deactive.eq(false);
				
			}else if(search == null && shapeId != null){
				expression = qSizeGroup.deactive.eq(false).and(qSizeGroup.shape.id.eq(shapeId));
				
			}else if (search != null && shapeId == null) {
				expression = qSizeGroup.deactive.eq(false).and(qSizeGroup.shape.name.like("%" +search + "%").or(qSizeGroup.name.like("%" +search + "%")));
			}else {
				expression = qSizeGroup.deactive.eq(false).and(qSizeGroup.shape.name.like("%" +search + "%").or(qSizeGroup.name.like("%" +search + "%"))).and(qSizeGroup.shape.id.eq(shapeId));
			}
		}else{   System.err.println("shapeId in side Else == "+shapeId);
			
		if (search == null && shapeId == null) {
			expression = qSizeGroup.deactive.eq(false);
			
		}else if(search == null && shapeId != null){
			
			expression = qSizeGroup.deactive.eq(false).and(qSizeGroup.shape.id.eq(shapeId));
			
		}else if (search != null && shapeId == null) {
			expression = qSizeGroup.deactive.eq(false).and(qSizeGroup.shape.name.like("%" +search + "%").or(qSizeGroup.name.like("%" +search + "%")));
		} else if(search != null && shapeId != null) {
			expression = qSizeGroup.deactive.eq(false).and(qSizeGroup.shape.name.like("%" +search + "%").or(qSizeGroup.name.like("%" +search + "%"))).and(qSizeGroup.shape.id.eq(shapeId));
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
		} else if (sort.equalsIgnoreCase("shape")) {
			sort = "shape";
		} 
		
		Page<SizeGroup> sizeGroupMastList =(Page<SizeGroup>) sizeGroupRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		
		return sizeGroupMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QSizeGroup qSizeGroup = QSizeGroup.sizeGroup;
		BooleanExpression expression = null;
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qSizeGroup.deactive.eq(false);			
				}else{
					expression = qSizeGroup.deactive.eq(false).and(qSizeGroup.shape.name.like(colValue + "%"));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qSizeGroup.shape.name.like(colValue + "%");
				}
			}
			
			return sizeGroupRepository.count(expression);
	}

	@Override
	public SizeGroup findByNameAndDeactive(String name, Boolean deactive) {
		// TODO Auto-generated method stub
		return sizeGroupRepository.findByNameAndDeactive(name, deactive);
	}
	


}

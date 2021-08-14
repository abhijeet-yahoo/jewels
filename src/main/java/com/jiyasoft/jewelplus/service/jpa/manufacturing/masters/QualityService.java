package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IQualityRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class QualityService implements IQualityService {
	@Autowired
	private IQualityRepository qualityRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Quality> findAll() {
		return qualityRepository.findAll();

	}

	@Override
	public Page<Quality> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		limit = (limit == null ? 1000 : limit);
		offset = (offset == null ? 0 : offset);
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return qualityRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Quality quality) {
		qualityRepository.save(quality);
	}

	@Override
	public void delete(int id) {
		Quality quality = qualityRepository.findOne(id);
		quality.setDeactive(true);
		quality.setDeactiveDt(new java.util.Date());
		qualityRepository.save(quality);
	}

	@Override
	public Long count() {
		return qualityRepository.count();
	}

	@Override
	public Quality findOne(int id) {
		return qualityRepository.findOne(id);
	}

	/*@Override
	public Quality findByName(String qualityName) {
		return qualityRepository.findByName(qualityName);
	}*/
	
	@Override
	public Quality findByShapeAndNameAndDeactive(Shape shape, String name,Boolean deactive){
		return qualityRepository.findByShapeAndNameAndDeactive(shape, name, deactive);
	}

	@Override
	public Page<Quality> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String search) {

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

		return qualityRepository.findByShape(shape, new PageRequest(page,
				limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Long count(Predicate predicate) {
		return qualityRepository.count(predicate);
	}

	@Override
	public Predicate countByShape(Integer id) {
		QQuality qQuality = QQuality.quality;
		BooleanExpression expression = qQuality.shape.id.eq(id);

		return expression;
	}

	@Override
	public Map<Integer, String> getQualityList(Integer shapeId) {
		Map<Integer, String> qualityMap = new LinkedHashMap<Integer, String>();
		Page<Quality> qualityList = findActiveQualitiesSortByName(shapeId);

		for (Quality quality : qualityList) {
			qualityMap.put(quality.getId(), quality.getName());
		}

		return qualityMap;
	}

	@Override
	public String getQualityListDropDown(Integer shapeId) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> qualityMap = getQualityList(shapeId);

		List<Map.Entry<Integer, String>> qualityMapGv = Lists.newArrayList(qualityMap.entrySet());
	    Collections.sort(qualityMapGv, byMapValues);

		sb.append("<select id=\"quality.id\" name=\"quality.id\" class=\"form-control\" >");
		sb.append("<option value=\"\">- Select Quality -</option>");

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
	public List<Quality> findActiveQualities(Integer shapeId) {
		QQuality qquality = QQuality.quality;
		BooleanExpression expression = null;

		if (shapeId == 0) {
			expression = qquality.deactive.eq(false);
		} else {
			expression = qquality.shape.id.eq(shapeId).and(
					qquality.deactive.eq(false));
		}

		List<Quality> qualityList = (List<Quality>) qualityRepository
				.findAll(expression);

		return qualityList;
	}

	@Override
	public Page<Quality> findActiveQualitiesSortByName(Integer shapeId) {
		QQuality qquality = QQuality.quality;
		BooleanExpression expression = null;

		if (shapeId == 0) {
			expression = qquality.deactive.eq(false);
		} else {
			expression = qquality.shape.id.eq(shapeId).and(
					qquality.deactive.eq(false));
		}

		Page<Quality> qualityList = qualityRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return qualityList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QQuality qQuality = QQuality.quality;
		BooleanExpression expression = qQuality.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qQuality.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qQuality.deactive.eq(false).and(
						qQuality.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qQuality.name.like(colValue + "%");
			}
		}

		return qualityRepository.count(expression);
	}

	@Override
	public Page<Quality> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive) {

		QQuality qQuality = QQuality.quality;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qQuality.deactive.eq(false);
			} else {
				expression = qQuality.deactive.eq(false).and(
						qQuality.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qQuality.name.like(name + "%").or(qQuality.shape.name.like(name + "%")).or(qQuality.code.like(name + "%")).or(qQuality.intQuality.like(name + "%")).or(qQuality.description.like(name + "%"));
			}
		}

		//int page = (offset == 0 ? 0 : (offset / limit));
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<Quality> qualityList = (Page<Quality>) qualityRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return qualityList;
	}

	@Override
	public Map<Integer, String> getQualityList() {

		Map<Integer, String> qualityMap = new LinkedHashMap<Integer, String>();
		List<Quality> qualityList = qualityRepository.findAll();

		for (Quality quality : qualityList) {
			qualityMap.put(quality.getId(), quality.getName());
		}

		return qualityMap;
	}
	
	
	@Override
	public List<Quality> findByDeactiveOrderByNameAsc(Boolean deactive) {
		return qualityRepository.findByDeactiveOrderByNameAsc(deactive);
	}
	

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@Override
	public List<Quality> findByShapeList(String shapeId) {
		List<Integer> shapeIdList = new ArrayList<Integer>();
		
		if(shapeId.length() > 0){
			String ids[] = shapeId.split(",");
			for(int i=0;i<ids.length;i++){
				shapeIdList.add(Integer.parseInt(ids[i]));
			}
		}else{
			shapeIdList.add(0);
		}
		
		
		for(Integer i:shapeIdList){
			System.out.println(i);
		}
		
		

		QQuality qQuality = QQuality.quality;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Quality> qualitys = null;
		
		qualitys = query.from(qQuality)
				.where(qQuality.deactive.eq(false).and(qQuality.shape.id.in(shapeIdList))).orderBy(qQuality.name.asc()).list(qQuality);
		
		return qualitys;

	}

	
	
	@Override
	public String getQualityListDropDownForQuot(Integer shapeId) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> qualityMap = getQualityList(shapeId);

		List<Map.Entry<Integer, String>> qualityMapGv = Lists.newArrayList(qualityMap.entrySet());
	    Collections.sort(qualityMapGv, byMapValues);

		sb.append("<select id=\"quality.id\" name=\"quality.id\" class=\"form-control\" onChange=\"javascript:popStoneRate();\">");
		sb.append("<option value=\"\">- Select Quality -</option>");

		Iterator<Entry<Integer, String>> iterator = qualityMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
		
		
	}
	
	//new method for quality searchlist(shape vise)

	@Override
	public Page<Quality> findByShape(Shape shape,Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		QQuality qQuality = QQuality.quality;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qQuality.deactive.eq(false);
			} else {
				expression = qQuality.deactive.eq(false).and(
						qQuality.name.like(name + "%"));
			}
		} else {
			if (name != null) {
			
				expression =qQuality.shape.id.eq(shape.getId()).and(qQuality.name.like(name + "%").or(qQuality.shape.name.like(name + "%")).or(qQuality.code.like(name + "%")).or(qQuality.intQuality.like(name + "%")).or(qQuality.description.like(name + "%")));
			}
		}
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<Quality> qualityList = (Page<Quality>) qualityRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return qualityList; 
	}

	@Override
	public Long countByShape(Shape shape, String colName, String colValue, Boolean onlyActive) {
		QQuality qQuality = QQuality.quality;
		BooleanExpression expression = qQuality.deactive.eq(false);
		
		if (onlyActive) {
			if (colValue == null) {
				expression = qQuality.deactive.eq(false);
			} else {
				expression = qQuality.deactive.eq(false).and(
						qQuality.name.like(colValue + "%"));
			}
		} else {
			if (colValue != null) {
				expression =qQuality.shape.id.eq(shape.getId()).and(qQuality.name.like(colValue + "%").or(qQuality.shape.name.like(colValue + "%"))
						.or(qQuality.code.like(colValue + "%")).or(qQuality.intQuality.like(colValue + "%")).or(qQuality.description.like(colValue + "%")));
			}else {
				expression =qQuality.shape.id.eq(shape.getId());
			}
		}
		
		return qualityRepository.count(expression);
	}

	@Override
	public List<Quality> findByIntQuality(String intQuality) {
		
		return qualityRepository.findByIntQuality(intQuality);
	}

	@Override
	public Quality findByShapeAndName(Shape shape, String name) {
		// TODO Auto-generated method stub
		return qualityRepository.findByShapeAndName(shape, name);
	}

	@Override
	public Page<Quality> searchAll(Integer limit, Integer offset, String sort,
			String order,String search, Integer shapeId, Boolean onlyActive) {
		
		QQuality qQuality = QQuality.quality;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null && shapeId == null) {
				expression = qQuality.deactive.eq(false);
			}else if (search == null && shapeId != null) {
			
				expression = qQuality.deactive.eq(false).and(qQuality.shape.id.eq(shapeId));
			}else if (search != null && shapeId == null) {
				expression = qQuality.deactive.eq(false).and(qQuality.name.like("%" + search + "%").or(qQuality.shape.name.like("%" + search + "%")));
			}else{
				expression = qQuality.deactive.eq(false).and(qQuality.name.like("%" + search + "%").or(qQuality.shape.name.like("%" + search + "%"))).and(qQuality.shape.id.eq(shapeId));
			
			}
		}else{
			if (search == null && shapeId == null) {
				expression = qQuality.deactive.eq(false);
			}
			else if (search == null && shapeId != null) {
			
				expression = qQuality.deactive.eq(false).and(qQuality.shape.id.eq(shapeId));
			}else if (search != null && shapeId == null) {
				expression = qQuality.deactive.eq(false).and(qQuality.name.like("%" + search + "%")).or(qQuality.shape.name.like("%" + search + "%"));
			}else if(search != null && shapeId != null){
				expression = qQuality.deactive.eq(false).and(qQuality.name.like("%" + search + "%")).or(qQuality.shape.name.like("%" + search + "%")).and(qQuality.shape.id.eq(shapeId));
			
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
		
		Page<Quality> qualityMastList =(Page<Quality>) qualityRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.err.println("expression "+expression);
		return qualityMastList;
		
		
	}


	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		
		QQuality qQuality = QQuality.quality;
		BooleanExpression expression = null;
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qQuality.deactive.eq(false);			
				}else{
					expression = qQuality.deactive.eq(false).and(qQuality.name.like("%" + colValue + "%").or(qQuality.shape.name.like(colValue + "%")));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qQuality.name.like("%" + colValue + "%").or(qQuality.shape.name.like(colValue + "%"));
				}
			}
			
			return qualityRepository.count(expression);
	}
	}



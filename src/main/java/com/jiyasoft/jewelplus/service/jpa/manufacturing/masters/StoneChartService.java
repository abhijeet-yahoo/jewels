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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IStoneChartRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StoneChartService implements IStoneChartService {

	@Autowired
	private IStoneChartRepository stoneChartRepository;

	@Override
	public List<StoneChart> findAll() {

		return stoneChartRepository.findAll();
	}

	@Override
	public Page<StoneChart> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		QStoneChart qStoneChart = QStoneChart.stoneChart;
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "shape.name,size";
		}
		
		
		BooleanExpression expression = qStoneChart.deactive.eq(false);

		return stoneChartRepository.findAll(expression,new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC : Direction.ASC),sort));

	}

	@Override
	public void save(StoneChart stoneChart) {
		stoneChartRepository.save(stoneChart);

	}

	@Override
	public void delete(int id) {
		StoneChart stoneChart = stoneChartRepository.findOne(id);
		stoneChart.setDeactive(true);
		stoneChart.setDeactiveDt(new java.util.Date());
		stoneChartRepository.save(stoneChart);

	}

	@Override
	public Long count() {
		return stoneChartRepository.count();
	}

	@Override
	public Long count(Predicate predicate) {
		return stoneChartRepository.count(predicate);
	}

	@Override
	public StoneChart findOne(int id) {
		return stoneChartRepository.findOne(id);
	}


	@Override
	public Page<StoneChart> findByShapeAndDeactive(Shape shape,Boolean deactive, Integer limit,
			Integer offset, String sort, String order, String search) {
		
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "size";
		}
	

		return stoneChartRepository.findByShapeAndDeactive(shape,deactive,new PageRequest(page,limit, (order.equalsIgnoreCase("asc") ? Direction.DESC : Direction.ASC), sort));

	}

	@Override
	public Predicate countByShape(Integer id) {
		QStoneChart qStoneChart = QStoneChart.stoneChart;

		// Deactive was not considered, since all the records have to be
		// displayed
		BooleanExpression expression = qStoneChart.shape.id.eq(id);

		return expression;
	}

	@Override
	public Map<String, String> getStoneChartList(Integer shpId) {
		Map<String, String> stoneChartMap = new LinkedHashMap<String, String>();
		Page<StoneChart> stoneChartsList = findActiveStoneChartSortBySize(shpId);

		for (StoneChart stoneChart : stoneChartsList) {
			String str = stoneChart.getSize();
//			stoneChartMap.put(str.substring(0, str.indexOf(".")+3), str);
			stoneChartMap.put(str, str);
		}

		return stoneChartMap;
	}

	@Override
	public String getStoneChartListDropDown(Integer shpId) {
		StringBuilder sb = new StringBuilder();
		Map<String, String> chartMap = getStoneChartList(shpId);

		List<Map.Entry<String, String>> chartMapGv = Lists.newArrayList(chartMap.entrySet());
		Collections.sort(chartMapGv, byMapValuesStr);

		sb.append("<select id=\"size\" name=\"size\" class=\"form-control\" onChange=\"javascript:getSizeMMDetails();\">");
		sb.append("<option value=\"\">- Select Size -</option>");

		Iterator<Entry<String, String>> iterator = chartMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	
	@Override
	public String getStoneChartListDropDownForDc(Integer shpId) {
		
		StringBuilder sb = new StringBuilder();
		Map<String, String> chartMap = getStoneChartList(shpId);

		List<Map.Entry<String, String>> chartMapGv = Lists.newArrayList(chartMap.entrySet());
		Collections.sort(chartMapGv, byMapValuesStr);

		sb.append("<select id=\"size\" name=\"size\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Size -</option>");

		Iterator<Entry<String, String>> iterator = chartMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	

	@Override
	public List<StoneChart> findActiveStoneChart(Integer shpId) {

		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = null;

		if (shpId == 0) {
			expression = qStoneChart.deactive.eq(false);
		} else {
			expression = qStoneChart.shape.id.eq(shpId).and(
					qStoneChart.deactive.eq(false));
		}
		List<StoneChart> stoneChartList = (List<StoneChart>) stoneChartRepository.findAll(expression);

		return stoneChartList;
	}

	@Override
	public Page<StoneChart> findActiveStoneChartSortBySize(Integer shpId) {

		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = null;

		if (shpId == 0) {
			expression = qStoneChart.deactive.eq(false);
		} else {
			expression = qStoneChart.shape.id.eq(shpId).and(
					qStoneChart.deactive.eq(false));
		}

		Page<StoneChart> stoneChartList = stoneChartRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "size"));

		return stoneChartList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = qStoneChart.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qStoneChart.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qStoneChart.deactive.eq(false).and(
						qStoneChart.size.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qStoneChart.size.like(colValue + "%");
			}
		}

		return stoneChartRepository.count(expression);
	}

	@Override
	public Page<StoneChart> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qStoneChart.deactive.eq(false);
			} else {
				expression = qStoneChart.deactive.eq(false).and(
						qStoneChart.size.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qStoneChart.size.like(name + "%");
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

		Page<StoneChart> stoneChartList = (Page<StoneChart>) stoneChartRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return stoneChartList;
	}

	
	
	
	
	
	Ordering<Map.Entry<String, String>> byMapValuesStr = new Ordering<Map.Entry<String, String>>() {
		@Override
		public int compare(Map.Entry<String, String> left, Map.Entry<String, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};
	

	@Override
	public List<StoneChart> findByShape(Shape shape) {
		return stoneChartRepository.findByShape(shape);
	}

	@Override
	public StoneChart findByShapeAndSizeAndDeactive(Shape shape, String size,
			Boolean deactive) {
		return stoneChartRepository.findByShapeAndSizeAndDeactive(shape, size, deactive);
	}

	@Override
	public Page<StoneChart> searchAll(Integer limit, Integer offset,
			String sort, String order, String search,Integer shapeId, Boolean onlyActive) {
	
		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null && shapeId==null) {
				expression = qStoneChart.deactive.eq(false);
				
			}else if(search == null && shapeId !=null){
				expression = qStoneChart.deactive.eq(false).and(qStoneChart.shape.id.eq(shapeId));
			


			}else if(search != null && shapeId ==null) {
				expression = qStoneChart.deactive.eq(false).and(qStoneChart.shape.name.like("%" + search + "%")
						.or(qStoneChart.sizeGroup.name.like( search + "%")).or(qStoneChart.sieve.like("%" + search + "%")).or(qStoneChart.size.like("%" + search + "%")));

			}else {
		expression = qStoneChart.deactive.eq(false).and(qStoneChart.shape.name.like( search + "%")
				.or(qStoneChart.sizeGroup.name.like( search + "%")).or(qStoneChart.sieve.like("%" + search + "%")).or(qStoneChart.size.like("%" + search + "%")).and(qStoneChart.shape.id.eq(shapeId)));
			}
		}else{
			if (search == null && shapeId==null) {
				expression = qStoneChart.deactive.eq(false);
				
			}else if(search == null && shapeId !=null){
				expression = qStoneChart.deactive.eq(false).and(qStoneChart.shape.id.eq(shapeId));
			
			}else if(search != null && shapeId ==null) {
				expression = qStoneChart.deactive.eq(false).and(qStoneChart.shape.name.like("%" + search + "%")
						.or(qStoneChart.sizeGroup.name.like( search + "%")).or(qStoneChart.sieve.like("%" + search + "%")).or(qStoneChart.size.like("%" + search + "%")));

			}else if(search != null && shapeId ==null) {
		expression = qStoneChart.deactive.eq(false).and(qStoneChart.shape.name.like( search + "%")
				.or(qStoneChart.sizeGroup.name.like( search + "%")).or(qStoneChart.sieve.like("%" + search + "%")).or(qStoneChart.size.like("%" + search + "%")).and(qStoneChart.shape.id.eq(shapeId)));

				
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
		} else if (sort.equalsIgnoreCase("sizegroup")) {
			sort = "sizeGroup";
		}
		
		Page<StoneChart> stoneChartMastList =(Page<StoneChart>) stoneChartRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		return stoneChartMastList;

	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = null;

		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qStoneChart.deactive.eq(false);			
				}else{
					expression = qStoneChart.deactive.eq(false).and(qStoneChart.shape.name.like( colValue + "%").or(qStoneChart.sizeGroup.name.like("%" + colValue + "%")));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qStoneChart.shape.name.like( colValue + "%").or(qStoneChart.sizeGroup.name.like("%" + colValue + "%"));
				}
			}
			
			return stoneChartRepository.count(expression);
	}

	@Override
	public List<StoneChart> findBySizeGroupAndDeactive(SizeGroup sizeGroup,Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneChartRepository.findBySizeGroupAndDeactive(sizeGroup, deactive);
	}

	@Override
	public Page<StoneChart> findActiveSize(Integer sizeGroupId) {
	
		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = null;

		if (sizeGroupId == 0) {
			expression = qStoneChart.deactive.eq(false);
		} else {
			expression = qStoneChart.sizeGroup.id.eq(sizeGroupId).and(
					qStoneChart.deactive.eq(false));
		}
		Page<StoneChart> stoneChartList = stoneChartRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "size"));

		return stoneChartList;

	}

	@Override
	public Map<String, String> getStoneChartSizeList(Integer sizeGroupId) {
		
		Map<String, String> stoneChartMap = new LinkedHashMap<String, String>();
		Page<StoneChart> stoneChartsList = findActiveSize(sizeGroupId);

		for (StoneChart stoneChart : stoneChartsList) {
			String str = stoneChart.getSize();
			stoneChartMap.put(str, str);
		}

		return stoneChartMap;
	}

	@Override
	public String getStoneChartSizeListDropDown(Integer sizeGroupId) {
	
		StringBuilder sb = new StringBuilder();
		Map<String, String> chartMap = getStoneChartSizeList(sizeGroupId);

		List<Map.Entry<String, String>> chartMapGv = Lists.newArrayList(chartMap.entrySet());
		Collections.sort(chartMapGv, byMapValuesStr);

		sb.append("<select id=\"sizeNm\" name=\"sizeNm\" class=\"form-control\" >");
		sb.append("<option value=\"\">- Select Size -</option>");

		Iterator<Entry<String, String>> iterator = chartMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@Override
	public List<StoneChart> findBySizeGroupAndSizeAndDeactive(SizeGroup sizeGroup, String size, Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneChartRepository.findBySizeGroupAndSizeAndDeactive(sizeGroup, size, deactive);
	}
	
	@Override
	public Page<StoneChart> findActiveSieve(Integer sizeGroupId) {
	
		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = qStoneChart.sizeGroup.id.eq(sizeGroupId).and(
				qStoneChart.deactive.eq(false));;

		
		Page<StoneChart> stoneChartList = stoneChartRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "sieve"));

		return stoneChartList;

	}
	
	
	@Override
	public Map<String, String> getStoneChartSieveList(Integer sizeGroupId) {
		Map<String, String> sieveMap = new LinkedHashMap<String, String>();
		Page<StoneChart> stoneChartsList = findActiveSieve(sizeGroupId);

		for (StoneChart stoneChart : stoneChartsList) {
			String str = stoneChart.getSieve();
			sieveMap.put(str, str);
		}

		return sieveMap;
	}

	@Override
	public StoneChart findByShapeAndSieveAndDeactive(Shape shape, String sieve, Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneChartRepository.findByShapeAndSieveAndDeactive(shape, sieve, deactive);
	}

	@Override
	public String getStoneChartSieveListDropDown(Integer shpId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		Map<String, String> chartMap = getStoneChartSieveListForConv(shpId);

		List<Map.Entry<String, String>> chartMapGv = Lists.newArrayList(chartMap.entrySet());
		Collections.sort(chartMapGv, byMapValuesStr);

		sb.append("<select id=\"sieve\" name=\"sieve\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Sieve -</option>");

		Iterator<Entry<String, String>> iterator = chartMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@Override
	public Page<StoneChart> findActiveSieveListForConv(Integer shapeId) {
		// TODO Auto-generated method stub
		QStoneChart qStoneChart = QStoneChart.stoneChart;
		BooleanExpression expression = null;

		if (shapeId == 0) {
			expression = qStoneChart.deactive.eq(false);
		} else {
			expression = qStoneChart.shape.id.eq(shapeId).and(
					qStoneChart.deactive.eq(false));
		}

		Page<StoneChart> stoneChartList = stoneChartRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "sieve"));

		return stoneChartList;
	}

	@Override
	public Map<String, String> getStoneChartSieveListForConv(Integer shapeId) {
		// TODO Auto-generated method stub
		Map<String, String> stoneChartMap = new LinkedHashMap<String, String>();
		Page<StoneChart> stoneChartsList = findActiveStoneChartSortBySize(shapeId);

		for (StoneChart stoneChart : stoneChartsList) {
			String str = stoneChart.getSieve();
			stoneChartMap.put(str, str);
		}

		return stoneChartMap;
	}

	@Override
	public StoneChart findByShapeAndSizeAndSieveAndDeactive(Shape shape, String size, String sieve, Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneChartRepository.findByShapeAndSizeAndSieveAndDeactive(shape, size, sieve, deactive);
	}

	@Override
	public StoneChart findByShapeAndSizeAndSieveAndSizeGroupAndDeactive(Shape shape, String size, String sieve,
			SizeGroup sizeGroup, Boolean deactive) {
		// TODO Auto-generated method stub
		return stoneChartRepository.findByShapeAndSizeAndSieveAndSizeGroupAndDeactive(shape, size, sieve, sizeGroup, deactive);
	}
	
	
	
	}


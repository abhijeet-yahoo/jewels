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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QColor;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IColorRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ColorService implements IColorService {

	@Autowired
	private IColorRepository colorRepository;

	@Override
	public List<Color> findAll() {
		QColor qColor = QColor.color;
		BooleanExpression expression = qColor.deactive.eq(false); 
		return (List<Color>) colorRepository.findAll(expression);
	}

	@Override
	public Page<Color> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return colorRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Color color) {
		colorRepository.save(color);
	}

	@Override
	public void delete(int id) {
		Color color = colorRepository.findOne(id);
		color.setDeactive(true);
		color.setDeactiveDt(new java.util.Date());
		colorRepository.save(color);
	}

	@Override
	public Long count() {
		return colorRepository.count();
	}

	@Override
	public Color findOne(int id) {

		return colorRepository.findOne(id);
	}

	@Override
	public Color findByName(String name) {

		return colorRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getColorList() {
		Map<Integer, String> colorMap = new LinkedHashMap<Integer, String>();
		Page<Color> colorList = findActiveColorsSortByName();

		for (Color color : colorList) {
			colorMap.put(color.getId(), color.getName());
		}

		return colorMap;
	}

	@Override
	public List<Color> findActiveColors() {
		QColor qColor = QColor.color;
		BooleanExpression expression = qColor.deactive.eq(false);

		List<Color> colorList = (List<Color>) colorRepository
				.findAll(expression);

		return colorList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QColor qColor = QColor.color;
		BooleanExpression expression = qColor.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qColor.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qColor.deactive.eq(false).and(
						qColor.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {

				expression = qColor.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return colorRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return colorRepository.count();
			}
		}

		return colorRepository.count(expression);
	}

	@Override
	public Page<Color> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive) {

		QColor qColor = QColor.color;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qColor.deactive.eq(false);
			} else {
				expression = qColor.deactive.eq(false).and(
						qColor.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qColor.name.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<Color> colorList = (Page<Color>) colorRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return colorList;
	}

	@Override
	public Page<Color> findActiveColorsSortByName() {
		QColor qColor = QColor.color;
		BooleanExpression expression = qColor.deactive.eq(false);
		Page<Color> colorList = colorRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return colorList;
	}

	@Override
	public Map<Integer, String> getColorForDeduction(List<String> colors) {
		Map<Integer, String> colorMap = new LinkedHashMap<Integer, String>();
		List<Color> colorList = findDeductionColor(colors);
		for(Color color : colorList){
			colorMap.put(color.getId(), color.getName());
		}
		return colorMap;
	}

	@Override
	public List<Color> findDeductionColor(List<String> colors) {
		QColor qColor = QColor.color;
		BooleanExpression expression = qColor.deactive.eq(false).and(qColor.name.in(colors));
		List<Color> colorList = (List<Color>) colorRepository.findAll(expression);
		return colorList;
	}

	@Override
	public String getColorListDropDownForDeduction(List<String> colors) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> colorMap = getColorForDeduction(colors);

		List<Map.Entry<Integer, String>> colorMapGv = Lists.newArrayList(colorMap.entrySet());
	    Collections.sort(colorMapGv, byMapValues);

		sb.append("<select id=\"color.id\" name=\"color.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Color -</option>");

		Iterator<Entry<Integer, String>> iterator = colorMapGv.iterator();
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
	public Page<Color> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {

		QColor qColor = QColor.color;
		BooleanExpression expression = null;
		
		
		if (onlyActive) {
			if (search == null) {
				expression = qColor.deactive.eq(false);
			} else {
				expression = qColor.deactive.eq(false).and(qColor.name.like("%" + search + "%"));
			}
		} else {
			if (search != null) {
				expression = qColor.name.like("%" + search + "%");
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
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} 
		Page<Color> colorMastList = (Page<Color>) colorRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		return colorMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		
		QColor qColor = QColor.color;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qColor.deactive.eq(false);
			} else {
				expression = qColor.deactive.eq(false).and(qColor.name.like("%" + colValue + "%"));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qColor.name.like("%" + colValue + "%");
			}
		}

		return colorRepository.count(expression);

	}
	
	@Override
	public Color findByDefColorAndDeactive(Boolean defColor, Boolean deactive) {
		// TODO Auto-generated method stub
		return colorRepository.findByDefColorAndDeactive(defColor, deactive);
	}

}

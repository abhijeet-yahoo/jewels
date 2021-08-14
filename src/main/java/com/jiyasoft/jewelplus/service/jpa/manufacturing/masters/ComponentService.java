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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QComponent;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IComponentRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ComponentService implements IComponentService {

	@Autowired
	private IComponentRepository componentRepository;

	@Override
	public List<Component> findAll() {
		return componentRepository.findAll();
	}

	@Override
	public Page<Component> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		}
		
		return componentRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
			sort));
	}

	@Override
	public void save(Component component) {
		componentRepository.save(component);
	}

	@Override
	public void delete(int id) {
		Component component = componentRepository.findOne(id);
		component.setDeactive(true);
		component.setDeactiveDt(new java.util.Date());
		componentRepository.save(component);
	}

	@Override
	public Long count() {
		return componentRepository.count();
	}

	@Override
	public Component findOne(int id) {
		return componentRepository.findOne(id);
	}

	@Override
	public Component findByName(String name) {
		return componentRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getComponentList() {

		Map<Integer, String> componentMap = new LinkedHashMap<Integer, String>();
		Page<Component> componentList = findActiveComponentsSortByName();
		
		for (Component component : componentList) {
			componentMap.put(component.getId(), component.getName());
		}
		
		return componentMap;
	}

	@Override
	public List<Component> findActiveComponents() {

		QComponent qComponent = QComponent.component;
		BooleanExpression expression = qComponent.deactive.eq(false);

		List<Component> componentList = (List<Component>) componentRepository
				.findAll(expression);

		return componentList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
	
		QComponent qComponent = QComponent.component;
		BooleanExpression expression = qComponent.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qComponent.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qComponent.deactive.eq(false).and(
						qComponent.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qComponent.name.like(colValue + "%");
			}
		}

		return componentRepository.count(expression);
	}

	@Override
	public Page<Component> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {


		QComponent qComponent = QComponent.component;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qComponent.deactive.eq(false);
			} else {
				expression = qComponent.deactive.eq(false).and(
						qComponent.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qComponent.name.like(name + "%");
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

		Page<Component> componentList = (Page<Component>) componentRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return componentList;
	}

	@Override
	public Page<Component> findActiveComponentsSortByName() {
		QComponent qComponent = QComponent.component;
		BooleanExpression expression = qComponent.deactive.eq(false);
		Page<Component> componentList = componentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return componentList;
	}

	@Override
	public List<Component> findByChargableAndDeactive(Boolean chargable,Boolean deactive) {
		return componentRepository.findByChargableAndDeactive(chargable,deactive);
	}

	@Override
	public Map<Integer, String> getComponentForDeduction(List<String> components) {
		Map<Integer, String> componentMap = new LinkedHashMap<Integer, String>();
		List<Component> componentList = findDeductionComponent(components);
		for(Component component : componentList){
			componentMap.put(component.getId(), component.getName());
		}
		
		return componentMap;
	}

	@Override
	public List<Component> findDeductionComponent(List<String> components) {
		QComponent qComponent = QComponent.component;
		BooleanExpression expression = qComponent.deactive.eq(false).and(qComponent.name.in(components));
		List<Component> componentList =  (List<Component>) componentRepository.findAll(expression);
		return componentList;
	}

	@Override
	public String getComponentListDropDownForDeduction(List<String> components) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> componentMap = getComponentForDeduction(components);

		List<Map.Entry<Integer, String>> componentMapGv = Lists.newArrayList(componentMap.entrySet());
	    Collections.sort(componentMapGv, byMapValues);

		sb.append("<select id=\"component.id\" name=\"component.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Component -</option>");

		Iterator<Entry<Integer, String>> iterator = componentMapGv.iterator();
		
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"")
			.append(et.getKey())
			.append("\">")
			.append(et.getValue())
			.append("</option>");
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
	public Page<Component> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {

		QComponent qComponent = QComponent.component;
		BooleanExpression expression = null;
		if (onlyActive) {
			if (search == null) {
				expression = qComponent.deactive.eq(false);
			} else {
				expression = qComponent.deactive.eq(false).and(qComponent.name.like("%" + search + "%"));
			}
		} else {
			if (search != null) {
				expression = qComponent.name.like("%" + search + "%");
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
		Page<Component> componentMastList = (Page<Component>) componentRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		return componentMastList;
	
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
	
		QComponent qComponent = QComponent.component;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qComponent.deactive.eq(false);
			} else {
				expression = qComponent.deactive.eq(false).and(qComponent.name.like("%" + colValue + "%"));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qComponent.name.like("%" + colValue + "%");
			}
		}

		return componentRepository.count(expression);
	}

	@Override
	public Map<Integer, String> getChargebaleOrNonChargableComponentList(
			Boolean chargable) {
		Map<Integer, String> componentMap = new LinkedHashMap<Integer, String>();
		List<Component> componentList = findByChargableAndDeactive(chargable,false);
		for(Component component : componentList){
			componentMap.put(component.getId(), component.getName());
		}
		
		return componentMap;
	}

	@Override
	public String getChargebaleOrNonChargablComponentDropDown(Boolean chargable) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> componentMap = getChargebaleOrNonChargableComponentList(chargable);

		List<Map.Entry<Integer, String>> componentMapGv = Lists.newArrayList(componentMap.entrySet());
	    Collections.sort(componentMapGv, byMapValues);

		sb.append("<select id=\"component.id\" name=\"component.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Component -</option>");

		Iterator<Entry<Integer, String>> iterator = componentMapGv.iterator();
		
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"")
			.append(et.getKey())
			.append("\">")
			.append(et.getValue())
			.append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	
	

}

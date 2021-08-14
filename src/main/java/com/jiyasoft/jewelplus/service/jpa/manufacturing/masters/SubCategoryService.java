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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QSubCategory;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISubCategoryRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubCategoryService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class SubCategoryService implements ISubCategoryService {

	@Autowired
	private ISubCategoryRepository subCategoryRepository;

	@Override
	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll();
	}

	@Override
	public Page<SubCategory> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

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

		return subCategoryRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(SubCategory subCategory) {
		subCategoryRepository.save(subCategory);
	}

	@Override
	public void delete(int id) {
		SubCategory subCategory = subCategoryRepository.findOne(id);
		subCategory.setDeactive(true);
		subCategory.setDeactiveDt(new java.util.Date());
		subCategoryRepository.save(subCategory);
	}

	@Override
	public Long count() {
		return subCategoryRepository.count();
	}

	@Override
	public SubCategory findOne(int id) {
		return subCategoryRepository.findOne(id);
	}

	@Override
	public SubCategory findByName(String subCategoryName) {
		return subCategoryRepository.findByName(subCategoryName);
	}

	@Override
	public Page<SubCategory> findByCategory(Category category, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return subCategoryRepository.findByCategory(category, new PageRequest(
				page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Long count(Predicate predicate) {
		return subCategoryRepository.count(predicate);
	}

	@Override
	public Predicate countByCategory(Integer id) {
		QSubCategory qSubCategory = QSubCategory.subCategory;

		// Deactive was not considered, since all the records have to be
		// displayed
		BooleanExpression expression = qSubCategory.category.id.eq(id);

		return expression;
	}

	@Override
	public Map<Integer, String> getSubCategoryList(Integer catId) {
		Map<Integer, String> subCategoryMap = new HashMap<Integer, String>();
		Page<SubCategory> subCategoryList = findActiveSubCategoriesSortByName(catId);

		for (SubCategory subCategory : subCategoryList) {
			subCategoryMap.put(subCategory.getId(), subCategory.getName());
		}

		return subCategoryMap;
	}

	@Override
	public String getSubCategoryListDropDown(Integer catId) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> subCategoryMap = getSubCategoryList(catId);

		List<Map.Entry<Integer, String>> subCategoryMapGv = Lists.newArrayList(subCategoryMap.entrySet());
	    Collections.sort(subCategoryMapGv, byMapValues);

		sb.append("<select id=\"subCategory.id\" name=\"subCategory.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Sub Category -</option>");

		Iterator<Entry<Integer, String>> iterator = subCategoryMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();

	}

	@Override
	public List<SubCategory> findActiveSubCategories(Integer catId) {
		QSubCategory qsubCategory = QSubCategory.subCategory;
		BooleanExpression expression = null;

		if (catId == 0) {
			expression = qsubCategory.deactive.eq(false);
		} else {
			expression = qsubCategory.category.id.eq(catId).and(
					qsubCategory.deactive.eq(false));
		}

		List<SubCategory> subCategoryList = (List<SubCategory>) subCategoryRepository.findAll(expression);

		return subCategoryList;
	}

	@Override
	public Page<SubCategory> findActiveSubCategoriesSortByName(Integer catId) {

		QSubCategory qsubCategory = QSubCategory.subCategory;
		BooleanExpression expression = null;

		if (catId == 0) {
			expression = qsubCategory.deactive.eq(false);
		} else {
			expression = qsubCategory.category.id.eq(catId).and(qsubCategory.deactive.eq(false));
		}

		Page<SubCategory> subCategoryList = subCategoryRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return subCategoryList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QSubCategory qSubCategory = QSubCategory.subCategory;
		BooleanExpression expression = qSubCategory.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSubCategory.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qSubCategory.deactive.eq(false).and(
						qSubCategory.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qSubCategory.name.like(colValue + "%");
			}
		}

		return subCategoryRepository.count(expression);
	}

	@Override
	public Page<SubCategory> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QSubCategory qSubCategory = QSubCategory.subCategory;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qSubCategory.deactive.eq(false);
			} else {
				expression = qSubCategory.deactive.eq(false).and(
						qSubCategory.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qSubCategory.name.like(name + "%");
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

		Page<SubCategory> subCategoryList = (Page<SubCategory>) subCategoryRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return subCategoryList;
	}
	
	@Override
	public String getSubCategoryReportListDropDown(String catIds) {
		
	
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> subCategoryMap = new HashMap<Integer, String>();
		Map<Integer, String> tmpMap = null;
		
		sb.append("{\"total\":").append(0).append(",\"rows\": [");
		
		if(catIds.length() > 0 || catIds != ""){
			String[] catIdAry = catIds.split(",");
			for (int i = 0; i < catIdAry.length; i++) {
				
				tmpMap = getSubCategoryList(Integer.parseInt(catIdAry[i]));
				subCategoryMap.putAll(tmpMap);
			}
	
			List<Map.Entry<Integer, String>> subCategoryMapGv = Lists.newArrayList(subCategoryMap.entrySet());
		    Collections.sort(subCategoryMapGv, byMapValues);
	
			// new addition
		    
		    
			Iterator<Entry<Integer, String>> iterator = subCategoryMapGv.iterator();
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
	
	
	
	

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@Override
	public List<SubCategory> findByDeactive(Boolean deactive) {
		return subCategoryRepository.findByDeactive(deactive);
	}

	@Override
	public Page<SubCategory> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QSubCategory qSubCategory = QSubCategory.subCategory;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qSubCategory.deactive.eq(false);
			} else {
				expression = qSubCategory.deactive.eq(false).and(qSubCategory.category.name.like("%" + search + "%").or(qSubCategory.name.like("%" + search+ "%").or(qSubCategory.sCategCode.like("%" + search+ "%"))));
			}
		} else {
			if (search != null) {
				expression = qSubCategory.category.name.like("%" + search + "%").or(qSubCategory.name.like("%" + search+ "%").or(qSubCategory.sCategCode.like("%" + search+ "%")));
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
		} else if (sort.equalsIgnoreCase("category")) {
			sort = "category";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		}
		else if (sort.equalsIgnoreCase("sCategCode")) {
			sort = "sCategCode";
		}
	/*	Page<SubCategory> subCategoryMastList = (Page<SubCategory>) subCategoryRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);*/
		return subCategoryRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
	
		QSubCategory qSubCategory = QSubCategory.subCategory;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSubCategory.deactive.eq(false);
			} else {
				expression = qSubCategory.deactive.eq(false).and(qSubCategory.category.name.like("%" + colValue + "%").or(qSubCategory.name.like("%" + colValue + "%").or(qSubCategory.sCategCode.like("%" + colValue + "%"))));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qSubCategory.category.name.like("%" + colValue + "%").or(
						qSubCategory.name.like("%" + colValue + "%"));
			}
		}

		return subCategoryRepository.count(expression);

	}

}

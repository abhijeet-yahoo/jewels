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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QProdLabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LossBook;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IProdLabourTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProdLabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ProdLabourTypeService implements IProdLabourTypeService {

	@Autowired
	private IProdLabourTypeRepository prodLabourTypeRepository;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ITranMtService tranMtService;

	@Override
	public List<ProdLabourType> findAll() {
		// TODO Auto-generated method stub
		return prodLabourTypeRepository.findAll();
	}

	@Override
	public Page<ProdLabourType> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return prodLabourTypeRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

	}

	@Override
	public void save(ProdLabourType prodLabourType) {
		prodLabourTypeRepository.save(prodLabourType);

	}

	@Override
	public void delete(int id) {
		ProdLabourType prodLabourType = prodLabourTypeRepository.findOne(id);
		prodLabourType.setDeactive(true);
		prodLabourType.setDeactiveDt(new java.util.Date());
		prodLabourTypeRepository.save(prodLabourType);

	}

	@Override
	public Long count() {

		return prodLabourTypeRepository.count();
	}

	@Override
	public ProdLabourType findOne(int id) {

		return prodLabourTypeRepository.findOne(id);
	}

	@Override
	public ProdLabourType findByName(String name) {
		return prodLabourTypeRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getProdLabourTypeList() {
		Map<Integer, String> prodLabourTypeMap = new LinkedHashMap<Integer, String>();
		Page<ProdLabourType> prodLabourTypeList = findActiveProdLabourTypesSortByName();

		for (ProdLabourType prodLabourType : prodLabourTypeList) {
			prodLabourTypeMap.put(prodLabourType.getId(),
					prodLabourType.getName());
		}

		return prodLabourTypeMap;
	}

	@Override
	public List<ProdLabourType> findActiveProdLabourTypes() {
		QProdLabourType qProdLabourType = QProdLabourType.prodLabourType;
		BooleanExpression expression = qProdLabourType.deactive.eq(false);

		List<ProdLabourType> prodLabourTypeList = (List<ProdLabourType>) prodLabourTypeRepository
				.findAll(expression);

		return prodLabourTypeList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QProdLabourType qProdLabourType = QProdLabourType.prodLabourType;
		BooleanExpression expression = qProdLabourType.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qProdLabourType.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qProdLabourType.deactive.eq(false).and(
						qProdLabourType.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qProdLabourType.name.like(colValue + "%");
			}
		}

		return prodLabourTypeRepository.count(expression);
	}

	@Override
	public Page<ProdLabourType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QProdLabourType qQProdLabourType = QProdLabourType.prodLabourType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qQProdLabourType.deactive.eq(false);
			} else {
				expression = qQProdLabourType.deactive.eq(false).and(
						qQProdLabourType.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qQProdLabourType.name.like(name + "%");
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

		Page<ProdLabourType> prodLabourTypeList = (Page<ProdLabourType>) prodLabourTypeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return prodLabourTypeList;
	}

	@Override
	public Page<ProdLabourType> findActiveProdLabourTypesSortByName() {
		return null;
	}

	@Override
	public Page<ProdLabourType> findByCategory(Category category,
			Integer limit, Integer offset, String sort, String order,
			String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return prodLabourTypeRepository.findByCategory(category,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}

	@Override
	public Page<ProdLabourType> findByCategoryAndDepartment(Category category,
			Department department, Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return prodLabourTypeRepository.findByCategoryAndDepartment(category,
				department,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	}

	@Override
	public List<ProdLabourType> findActiveProdLabType(Integer deptId,
			Integer categId) {

		QProdLabourType qProdLabourType = QProdLabourType.prodLabourType;
		BooleanExpression expression = null;

		if (deptId == 0 && categId == 0) {
			expression = qProdLabourType.deactive.eq(false);
		} else {
			expression = qProdLabourType.department.id.eq(deptId).and(
					qProdLabourType.category.id.eq(categId).and(
							qProdLabourType.deactive.eq(false)));

		}

		List<ProdLabourType> prodLabourTypeList = (List<ProdLabourType>) prodLabourTypeRepository
				.findAll(expression);

		return prodLabourTypeList;
	}

	@Override
	public Map<Integer, String> getProdLabourTypeList(Integer deptId,
			Integer categId) {

		Map<Integer, String> prodLabourTypeMap = new LinkedHashMap<Integer, String>();
		Page<ProdLabourType> prodLabourTypeList = findActiveProdLabourTypesSortByName(
				deptId, categId);

		for (ProdLabourType prodLabourType : prodLabourTypeList) {
			prodLabourTypeMap.put(prodLabourType.getId(),
					prodLabourType.getName());
		}

		return prodLabourTypeMap;
	}

	@Override
	public String getProdLabourTypeDropDown(Integer deptId, Integer categId) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> prodLabTypeMap = getProdLabourTypeList(deptId,
				categId);

		List<Map.Entry<Integer, String>> prodLabTypeMapGv = Lists
				.newArrayList(prodLabTypeMap.entrySet());
		Collections.sort(prodLabTypeMapGv, byMapValues);

		sb.append("<select id=\"prodLabType.id\" name=\"prodLabType.id\" class=\"form-control\" onChange=\"javascript:getRate(this.value);\">");
		sb.append("<option value=\"\">- Select ProdLabourType -</option>");

		Iterator<Entry<Integer, String>> iterator = prodLabTypeMapGv.iterator();

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
		public int compare(Map.Entry<Integer, String> left,
				Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@Override
	public Page<ProdLabourType> findActiveProdLabourTypesSortByName(
			Integer deptId, Integer categId) {

		QProdLabourType qProdLabourType = QProdLabourType.prodLabourType;
		BooleanExpression expression = qProdLabourType.deactive.eq(false);

		if (deptId == 0 || categId == 0) {
			expression = qProdLabourType.deactive.eq(false);
		} else {
			expression = qProdLabourType.department.id.eq(deptId).and(
					qProdLabourType.category.id.eq(categId).and(
							qProdLabourType.deactive.eq(false)));
		}

		Page<ProdLabourType> prodLabourTypeList = prodLabourTypeRepository
				.findAll(expression, new PageRequest(0, 10000, Direction.ASC,
						"name"));

		return prodLabourTypeList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QProdLabourType qQProdLabourType = QProdLabourType.prodLabourType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qQProdLabourType.deactive.eq(false);
			} else {
				expression = qQProdLabourType.deactive.eq(false).and(qQProdLabourType.category.name.like("%" + colValue + "%").or(qQProdLabourType.name.like("%" + colValue+ "%").or(qQProdLabourType.department.name.like("%" + colValue+ "%"))));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qQProdLabourType.category.name.like("%" + colValue + "%").or(qQProdLabourType.name.like("%" + colValue + "%").or(qQProdLabourType.department.name.like("%" + colValue+ "%")));
			}
		}

		return prodLabourTypeRepository.count(expression);
	}

	@Override
	public Page<ProdLabourType> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		QProdLabourType qQProdLabourType = QProdLabourType.prodLabourType;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qQProdLabourType.deactive.eq(false);
			}else{
				expression = qQProdLabourType.deactive.eq(false).and(qQProdLabourType.category.name.like("%" + search + "%").or(qQProdLabourType.name.like("%" + search + "%").or(qQProdLabourType.department.name.like("%" + search + "%"))));
			}
		}else{
			if (search != null) {
				expression = qQProdLabourType.category.name.like("%" + search + "%").or(qQProdLabourType.name.like("%" + search + "%").or(qQProdLabourType.department.name.like("%" + search + "%")));
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
		} else if (sort.equalsIgnoreCase("categCode")) {
			sort = "categCode";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<ProdLabourType> proLabourMastList =(Page<ProdLabourType>) prodLabourTypeRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return proLabourMastList;
	}

	@Override
	public Page<ProdLabourType> customSearch(Integer limit, Integer offset,
			String sort, String order, String departmentName,
			String categoryNm, String prodLobourName) {
		
		QProdLabourType qQProdLabourType = QProdLabourType.prodLabourType;
		BooleanBuilder where = new BooleanBuilder();
		
		
		if (departmentName != null) {
			where.and(qQProdLabourType.department.name.like("%" + departmentName + "%"));
		}

		if (categoryNm != null) {
			where.and(qQProdLabourType.category.name.like("%" + categoryNm + "%"));
		}

		if (prodLobourName != null) {
			where.and(qQProdLabourType.name.like("%" + prodLobourName + "%"));
		}
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		}

		Page<ProdLabourType> proLabourTypeList = (Page<ProdLabourType>) prodLabourTypeRepository.findAll(where,new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return proLabourTypeList;
	}

	@Override
	public Long customSearchCount(String departmentName, String categoryNm,
			String prodLobourName) {
		QProdLabourType qQProdLabourType = QProdLabourType.prodLabourType;
		BooleanBuilder where = new BooleanBuilder();


		if (departmentName != null) {
			where.and(qQProdLabourType.department.name.like("%" + departmentName + "%"));
		}

		if (categoryNm != null) {
			where.and(qQProdLabourType.category.name.like("%" + categoryNm + "%"));
		}

		if (prodLobourName != null) {
			where.and(qQProdLabourType.name.like("%" + prodLobourName + "%"));
		}


		return prodLabourTypeRepository.count(where);
	}

	@Override
	public String getLossLabourTypeDtDropDown(String bagNo,Integer departmentId) {
		StringBuilder sb = new StringBuilder();
		
		BagMt bagMt =bagMtService.findByName(bagNo);
		TranMt tranMt=tranMtService.findByBagMtCurrStk(bagMt, true);
		
		
		Map<Integer, String> prodLabourMap= getProdLabourTypeList(departmentId,tranMt.getOrderDt().getDesign().getCategory().getId());
	
		sb.append("<select id=\"prodLabourType.id\" name=\"prodLabourType.id\" class=\"form-control\" >");
		sb.append("<option value=\"\">- Select LabourType -</option>");
		
		for (Object key : prodLabourMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(prodLabourMap.get(key)).append("</option>");
			
		}
		sb.append("</select>");
	
		return sb.toString();
	}

	
	
	
}

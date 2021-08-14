package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Collections;
import java.util.HashMap;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDepartmentRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository departmentRepository;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;

	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Page<Department> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		return departmentRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Department department) {
		departmentRepository.save(department);

	}

	@Override
	public void delete(int id) {
		Department department = departmentRepository.findOne(id);
		department.setDeactive(true);
		department.setDeactiveDt(new java.util.Date());
		departmentRepository.save(department);

	}

	@Override
	public Long count() {
		return departmentRepository.count();
	}

	@Override
	public Department findOne(int id) {
		return departmentRepository.findOne(id);
	}

	@Override
	public Department findByName(String name) {

		return departmentRepository.findByName(name);
	}
	
	@Override
	public Map<Integer, String> getDepartmentList() {
		Map<Integer, String> departmentMap = new HashMap<Integer, String>();
		List<Department> departmentList = findActiveDepartments();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}

		return departmentMap;
	}

	@Override
	public List<Department> findActiveDepartments() {
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false);

		List<Department> departmentList = (List<Department>) departmentRepository
				.findAll(expression);

		return departmentList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDepartment.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qDepartment.deactive.eq(false).and(
						qDepartment.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qDepartment.name.like(colValue + "%");
			}
		}

		return departmentRepository.count(expression);
	}

	@Override
	public Page<Department> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qDepartment.deactive.eq(false);
			} else {
				expression = qDepartment.deactive.eq(false).and(
						qDepartment.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qDepartment.name.like(name + "%");
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
			sort = "name";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<Department> departmentList = (Page<Department>) departmentRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return departmentList;
	}

	@Override
	public Page<Department> findActiveDepartmentForMetalSortByName() {
		
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.looseMetalStk.eq(true));
		
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		
		return departmentList;
	}

	@Override
	public Map<Integer, String> getDepartmentListForMetal() {
		
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveDepartmentForMetalSortByName();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}

		return departmentMap;
	}

	@Override
	public Page<Department> findActiveDepartmentForCompSortByName() {
		
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.componentStk.eq(true));
		
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		
		return departmentList;
	}

	@Override
	public Map<Integer, String> getDepartmentListForComp() {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveDepartmentForCompSortByName();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}

		return departmentMap;
	}

	@Override
	public Page<Department> findActiveToDepartmentForMetalSortByName(Integer dept) {
		
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.looseMetalStk.eq(true).and(qDepartment.id.ne(dept)));
		
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		
		return departmentList;
	}

	@Override
	public Map<Integer, String> getToDepartmentListForMetal(Integer dept) {
		
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveToDepartmentForMetalSortByName(dept);

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}

		return departmentMap;
	}

	@Override
	public String getToDepartmentListDropDownForMetal(Integer dept) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> departmentMapp = getToDepartmentListForMetal(dept);

		List<Map.Entry<Integer, String>> departmentMapGv = Lists.newArrayList(departmentMapp.entrySet());
	    Collections.sort(departmentMapGv, byMapValues);

		sb.append("<select id=\"department.id\" name=\"department.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select To Location -</option>");

		Iterator<Entry<Integer, String>> iterator = departmentMapGv.iterator();
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
	public Page<Department> findActiveDepartmentForCompSortByNameNotCentral() {
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.componentStk.eq(true).and(qDepartment.name.ne("Central")));
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return departmentList;
	}

	@Override
	public Map<Integer, String> getDepartmentListForCompNotCentral() {
		
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveDepartmentForCompSortByNameNotCentral();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}

		return departmentMap;
	}
	
	///////////////////////////////////////
	
	@Override
	public Page<Department> findActiveToDepartmentForCompSortByName(Integer dept) {
		
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.componentStk.eq(true).and(qDepartment.id.ne(dept)));
		
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		
		return departmentList;
	}

	@Override
	public Map<Integer, String> getToDepartmentListForComp(Integer dept) {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveToDepartmentForCompSortByName(dept);

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}
	

	@Override
	public String getToDepartmentListDropDownForComp(Integer dept) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> departmentMapp = getToDepartmentListForComp(dept);

		List<Map.Entry<Integer, String>> departmentMapGv = Lists.newArrayList(departmentMapp.entrySet());
	    Collections.sort(departmentMapGv, byMapValues);

		sb.append("<select id=\"department.id\" name=\"department.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select To Location -</option>");

		Iterator<Entry<Integer, String>> iterator = departmentMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	

	@Override
	public List<Department> findByLooseMetalStkOrComponentStkOrStoneStkOrderByNameAsc(
			Boolean looseMetalStk, Boolean componentStk, Boolean stoneStk) {
		return departmentRepository.findByLooseMetalStkOrComponentStkOrStoneStkOrderByNameAsc(looseMetalStk, componentStk, stoneStk);
	}

	@Override
	public Page<Department> findActiveDepartmentForEmpStoneProductionSortByName() {
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.stoneProd.eq(true));
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return departmentList;
	}
	
	
	@Override
	public Map<Integer, String> getDepartmentListForEmpStoneProduction() {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveDepartmentForEmpStoneProductionSortByName();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	@Override
	public Page<Department> findActiveDepartmentForEmpPcsProductionSortByName() {
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.pcsProd.eq(true));
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return departmentList;
	}

	@Override
	public Map<Integer, String> getDepartmentListForEmpPcsProduction() {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveDepartmentForEmpPcsProductionSortByName();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	@Override
	public Page<Department> findActiveDepartmentForPdcSortByName() {
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.process.equalsIgnoreCase("pd"));
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return departmentList;
	}

	@Override
	public Map<Integer, String> getDepartmentListForPdc() {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveDepartmentForPdcSortByName();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	@Override
	public Page<Department> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qDepartment.deactive.eq(false);
			} else {
				expression = qDepartment.deactive.eq(false).and(qDepartment.name.like("%" + search + "%").or(qDepartment.code.like("%" + search + "%")));
			}
		} else {
			if (search != null) {
				expression = qDepartment.name.like("%" + search + "%").or(qDepartment.code.like("%" + search + "%"));
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
		}else if (sort.equalsIgnoreCase("code")) {
			sort = "code"; 
		}
		Page<Department> departmentMastList = (Page<Department>) departmentRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return departmentMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDepartment.deactive.eq(false);
			} else {
				expression = qDepartment.deactive.eq(false).and(qDepartment.name.like("%" + colValue + "%").or(qDepartment.code.like("%" + colValue + "%")));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qDepartment.name.like("%" + colValue + "%").or(qDepartment.code.like("%" + colValue + "%"));
			}
		}

		return departmentRepository.count(expression);
	}
	
	
	
	
	@Override
	public Page<Department> findActiveToDepartmentForStoneTranSortByName(Integer dept) {
		
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.stoneStk.eq(true).and(qDepartment.id.ne(dept)));
		
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		
		return departmentList;
	}

	@Override
	public Map<Integer, String> getToDepartmentListForStoneTran(Integer dept) {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveToDepartmentForStoneTranSortByName(dept);

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	@Override
	public String getToDepartmentListDropDownForStoneTran(Integer dept) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> departmentMapp = getToDepartmentListForStoneTran(dept);

		List<Map.Entry<Integer, String>> departmentMapGv = Lists.newArrayList(departmentMapp.entrySet());
	    Collections.sort(departmentMapGv, byMapValues);

		sb.append("<select id=\"department.id\" name=\"department.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select To Location -</option>");

		Iterator<Entry<Integer, String>> iterator = departmentMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	
	@Override
	public Map<Integer, String> getDepartmentListForStoneTran() {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveDepartmentForStoneTranSortByName();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	
	

	@Override
	public Page<Department> findActiveDepartmentForStoneTranSortByName() {
		QDepartment qDepartment =QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.stoneStk.eq(true));
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return departmentList;		
		
	}

	@Override
	public Department findByCodeAndDeactive(String code, Boolean deactive) {
		// TODO Auto-generated method stub
		return departmentRepository.findByCodeAndDeactive(code, deactive);
	}

	@Override
	public String getLocationListDropDown() {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> locationMap = getDepartmentListForCompNotCentral();

		List<Map.Entry<Integer, String>> purityMapGv = Lists.newArrayList(locationMap.entrySet());
		Collections.sort(purityMapGv, byMapValues);

		sb.append("<select id=\"deptLocationId\" name=\"deptLocationId\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Location -</option>");

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
	public Map<Integer, String> LooseMetalStkOrComponentStkOrStoneSt(Boolean looseMetalStk,Boolean componentStk,Boolean stoneStk) {
		// TODO Auto-generated method stub
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		List<Department> departmentList = findByLooseMetalStkOrComponentStkOrStoneStkOrderByNameAsc(looseMetalStk, componentStk, stoneStk);

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	@Override
	public List<Department> findByVouTypeFlgAndDeactiveOrderByNameAsc(Boolean vouTypeFlg, Boolean deactive) {
		// TODO Auto-generated method stub
		return departmentRepository.findByVouTypeFlgAndDeactiveOrderByNameAsc(vouTypeFlg, deactive);
	}

	@Override
	public Map<Integer, String> getVouTypeDepartmentList() {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		List<Department> departmentList = findByVouTypeFlgAndDeactiveOrderByNameAsc(true, false);

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	@Override
	public Page<Department> findActiveReparingDepartment() {
		// TODO Auto-generated method stub
		QDepartment qDepartment = QDepartment.department;
		BooleanExpression expression = qDepartment.deactive.eq(false).and(qDepartment.name.in("REPARING-HU","Repair"));
		
		Page<Department> departmentList = departmentRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		
		return departmentList;
	}

	@Override
	public Map<Integer, String> getReparingDepartmentList() {
		// TODO Auto-generated method stub
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		Page<Department> departmentList = findActiveReparingDepartment();

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	@Override
	public List<Department> findByBarcodeFlag(Boolean barcodeFlg) {
		return departmentRepository.findByBarcodeFlg(barcodeFlg);
	}

	@Override
	public Map<Integer, String> getDepartmentListforBarcode() {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		
		List<Department> departmentList = findByBarcodeFlag(true);

		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	
	@Override
	public String getToDepartmentListDropDownForAll(Integer user) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> locationMap = userDeptTrfRightService.getDepartmentListFromUser(user);

		List<Map.Entry<Integer, String>> purityMapGv = Lists.newArrayList(locationMap.entrySet());
		Collections.sort(purityMapGv, byMapValues);

		sb.append("<select id=\"fromDeptId\" name=\"fromDeptId\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Department -</option>");

		Iterator<Entry<Integer, String>> iterator = purityMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
					.append(et.getValue()).append("</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}

}

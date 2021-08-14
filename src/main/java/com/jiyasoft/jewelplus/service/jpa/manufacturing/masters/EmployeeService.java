package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QEmployee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IEmployeeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.mysema.query.jpa.impl.JPAQuery;
//import com.jiyasoft.jewelplus.service.jpa.manufacturing.masters.DepartmentService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Page<Employee> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		QEmployee qEmployee = QEmployee.employee;
		BooleanExpression expression = qEmployee.deactive.eq(false);
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return employeeRepository.findAll(expression,new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),sort));
		
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void delete(int id) {
		Employee employee = employeeRepository.findOne(id);
		employee.setDeactive(true);
		employee.setDeactiveDt(new java.util.Date());
		employeeRepository.save(employee);
	}

	@Override
	public Long count() {
		return employeeRepository.count();
	}

	@Override
	public Employee findOne(int id) {
		return employeeRepository.findOne(id);
	}

	@Override
	public Employee findByName(String employeeName) {
		return employeeRepository.findByName(employeeName);
	}

	@Override
	public Page<Employee> findByDepartment(Department department,
			Integer limit, Integer offset, String sort, String order,
			String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return employeeRepository.findByDepartmentAndDeactive(department,false, new PageRequest(
				page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Long count(Predicate predicate) {
		return employeeRepository.count(predicate);
	}

	@Override
	public Predicate countByDepartment(Integer id) {
		QEmployee qEmployee = QEmployee.employee;

		// Deactive was not considered, since all the records have to be
		// displayed
		BooleanExpression expression = qEmployee.department.id.eq(id);

		return expression;
	}

	

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QEmployee qEmployee = QEmployee.employee;
		BooleanExpression expression = qEmployee.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qEmployee.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qEmployee.deactive.eq(false).and(
						qEmployee.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qEmployee.name.like(colValue + "%");
			}
		}

		return employeeRepository.count(expression);
	}

	@Override
	public Page<Employee> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QEmployee qEmployee = QEmployee.employee;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qEmployee.deactive.eq(false);
			} else {
				expression = qEmployee.deactive.eq(false).and(
						qEmployee.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qEmployee.name.like(name + "%");
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

		Page<Employee> employeeList = (Page<Employee>) employeeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return employeeList;
	}



	@Override
	public Map<Integer, String> getEmployeeList(String depName) {
		Map<Integer, String> employeeeMap = new HashMap<Integer, String>();
		return employeeeMap;
	}
	
	@Override
	public Map<Integer, String> getEmployeeList(Integer depId) {

		Map<Integer, String> employeeMap = new LinkedHashMap<Integer, String>();
		Page<Employee> employeeList = findActiveEmployeesSortByName(depId);

		for (Employee employee : employeeList) {
			employeeMap.put(employee.getId(), employee.getName());
		}

		return employeeMap;
	}

	@Override
	public List<Employee> findActiveEmployees(Integer depId) {
		QEmployee qemployee = QEmployee.employee;
		BooleanExpression expression = null;

			expression = qemployee.department.id.eq(depId).and(
					qemployee.deactive.eq(false));


		List<Employee> employeeList = (List<Employee>) employeeRepository
				.findAll(expression);

		return employeeList;
	}
	
	@Override
	public Page<Employee> findActiveEmployeesSortByName(Integer deptId) {

		QEmployee qemployee = QEmployee.employee;
		BooleanExpression expression = qemployee.deactive.eq(false);

		if (deptId == 0) {
			expression = qemployee.deactive.eq(false);
		} else {
			expression = qemployee.department.id.eq(deptId).and(
						 qemployee.deactive.eq(false));
		}

		Page<Employee> employeeList = employeeRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return employeeList;
	}

	@Override
	public String getEmployeeListDropDown(Integer deptId) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> employeeMap = getEmployeeList(deptId);

		List<Map.Entry<Integer, String>> purityMapGv = Lists.newArrayList(employeeMap.entrySet());
		Collections.sort(purityMapGv, byMapValues);

		sb.append("<select id=\"employee.id\" name=\"employee.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Employee -</option>");

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
		public int compare(Map.Entry<Integer, String> left,
				Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	
	
	
	
	@Override
	public String getEmployeeListDropDownForChanging(Integer deptId) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> employeeMap = getEmployeeList(deptId);

		List<Map.Entry<Integer, String>> purityMapGv = Lists.newArrayList(employeeMap.entrySet());
		Collections.sort(purityMapGv, byMapValues);

		sb.append("<select id=\"employee.id\" name=\"employee.id\" class=\"form-control\" >");
		sb.append("<option value=\"\">- Select Employee -</option>");

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
	public Employee findByNameAndDepartmentAndDeactive(String name,
			Department department, Boolean deactive) {
		return employeeRepository.findByNameAndDepartmentAndDeactive(name, department, deactive);
	}
	
	
	@Override
	public List<Employee> findEmployeeByDepartment(String deptIds) {
		
		List<Integer> deptIdList = new ArrayList<Integer>();
		
		if(deptIds.length() > 0){
			String ids[] = deptIds.split(",");
			for(int i=0;i<ids.length;i++){
				deptIdList.add(Integer.parseInt(ids[i]));
			}
		}else{
			deptIdList.add(0);
		}
		
		
		/*for(Integer i:deptIdList){
			System.out.println(i);
		}*/
		
		QEmployee qEmployee = QEmployee.employee;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Employee> employeeList = null;
		
		employeeList = query.from(qEmployee)
				.where(qEmployee.deactive.eq(false).and(qEmployee.department.id.in(deptIdList))).orderBy(qEmployee.name.asc()).list(qEmployee);
		
		return employeeList;
	}

	@Override
	public Page<Employee> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {
		
		QEmployee qemployee = QEmployee.employee;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qemployee.deactive.eq(false);
			} else {
				expression = qemployee.deactive.eq(false).and(qemployee.name.like("%" + search + "%").or(qemployee.code.like("%" + search + "%").or(qemployee.department.name.like("%" + search + "%"))));
			}
		} else {
			if (search != null) {
				expression = qemployee.name.like("%" + search + "%").or(qemployee.code.like("%" + search + "%").or(qemployee.department.name.like("%" + search + "%")));
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
		}else if (sort.equalsIgnoreCase("department")) {
			sort = "department"; 
		}
		
		Page<Employee> employeeMastList = (Page<Employee>) employeeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		
		return employeeMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		
		QEmployee qemployee = QEmployee.employee;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qemployee.deactive.eq(false);
			} else {
				expression = qemployee.deactive.eq(false).and(qemployee.name.like("%" + colValue + "%").or(qemployee.code.like("%" + colValue + "%").or(qemployee.department.name.like("%" + colValue + "%"))));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qemployee.name.like("%" + colValue + "%").or(qemployee.code.like("%" + colValue + "%").or(qemployee.department.name.like("%" + colValue + "%")));
			}
		}

		return employeeRepository.count(expression);
	}

	@Override
	public List<Employee>  findByLookUpMastAndDeactive(LookUpMast lookUpMast,Boolean deactive) {
		// TODO Auto-generated method stub
		
		return employeeRepository.findByLookUpMastAndDeactive(lookUpMast,deactive);
	}

	@Override
	public Map<Integer, String> getDesignationList(String designation) {
		// TODO Auto-generated method stub
		
		LookUpMast lookUpMast = lookUpMastService.findByFieldValueAndDeactive(designation, false);
		
		Map<Integer, String> employeeMap = new LinkedHashMap<Integer, String>();
		List<Employee> employeeList = findByLookUpMastAndDeactive(lookUpMast,false);

		for (Employee employee : employeeList) {
			employeeMap.put(employee.getId(), employee.getName());
		}

		return employeeMap;
	}

	@Override
	public String getSalesmanListDropDown() {
	
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> employeeMap = getDesignationList("Salesman");

		List<Map.Entry<Integer, String>> purityMapGv = Lists.newArrayList(employeeMap.entrySet());
		Collections.sort(purityMapGv, byMapValues);

		sb.append("<select id=\"employee.id\" name=\"employee.id\" class=\"form-control\" >");
		sb.append("<option value=\"\">- Select Salesman -</option>");

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

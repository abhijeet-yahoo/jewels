package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.mysema.query.types.Predicate;

public interface IEmployeeService {
	
	public List<Employee> findAll();

	public Page<Employee> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Employee employee);

	public void delete(int id);

	public Long count();

	public Long count(Predicate predicate);

	public Employee findOne(int id);

	public Employee findByName(String employeeName);

	public Page<Employee> findByDepartment(Department department, Integer limit,
			Integer offset, String sort, String order, String search);

	public Predicate countByDepartment(Integer id);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Employee> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive);
	
	public Map<Integer, String> getEmployeeList(String depName);
	
	public Map<Integer, String> getEmployeeList(Integer depId);

	public List<Employee> findActiveEmployees(Integer depId);
	
	public String getEmployeeListDropDown(Integer deptId);
	
	public String getEmployeeListDropDownForChanging(Integer deptId);
	
	public Page<Employee> findActiveEmployeesSortByName(Integer deptId);
	
	public Employee findByNameAndDepartmentAndDeactive(String name, Department department,Boolean deactive);
	
	public List<Employee> findEmployeeByDepartment(String deptIds);
	
	//public Department findByDepartmentName(String departmentName);
	
	public Page<Employee> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public List<Employee>  findByLookUpMastAndDeactive(LookUpMast lookUpMast,Boolean deactive);
	
	public Map<Integer, String> getDesignationList(String designation);
	
	public String getSalesmanListDropDown();

}

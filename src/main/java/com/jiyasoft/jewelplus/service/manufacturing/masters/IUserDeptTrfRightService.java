package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.UserDeptTrfRight;

public interface IUserDeptTrfRightService {

	public List<UserDeptTrfRight> findAll();

	public Page<UserDeptTrfRight> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(UserDeptTrfRight userDeptTrfRight);

	public void delete(int id);

	public Long count();

	public UserDeptTrfRight findOne(int id);

	public List<UserDeptTrfRight> findByUserAndDepartmentAndDeactive(User user , Department department, Boolean deactive);
	
	public 	List <UserDeptTrfRight> findByUserAndDeactive(User user, Boolean deactive);
	
	public Set<UserDeptTrfRight> findByUserAndDepartment(User user , Department department);
	
	////////////////////////new methods(5)///////////////
	
	public List<Department> findActiveUserDeptSortByName(Integer user);
	
	public Map<Integer,String> getDepartmentListFromUser(Integer user);
	
	public List<Department> findActiveUserToDeptSortByName(Integer user,Integer deptId);
	
	public Map<Integer,String> getToDepartmentListFromUser(Integer user,Integer deptId);
	
	public String getToDepartmentListDropDown(Integer user,Integer deptId,String dropDownId);
	
	public Map<Integer,String> getToDepartmentListFromUserForRepairIss(Integer user,Integer deptId);
	

	
	
	
////////////////////////new methods(5) for PDC///////////////
	
public List<Department> findActiveUserPdcDeptSortByName(Integer user);

public Map<Integer,String> getDepartmentListFromUserPdc(Integer user);

public List<Department> findActiveUserPdcToDeptSortByName(Integer user,Integer deptId);

public Map<Integer,String> getToDepartmentListFromUserPdc(Integer user,Integer deptId);

public String getToDepartmentListDropDownPdc(Integer user,Integer deptId);


public String getToLocationRightsListDropDown(Integer user,String dropDownId);
	
}

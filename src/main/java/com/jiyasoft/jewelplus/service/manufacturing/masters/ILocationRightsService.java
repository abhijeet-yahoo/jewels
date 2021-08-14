package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;

public interface ILocationRightsService {

	public void save(LocationRights locationRights);

	public void delete(int id);

	public LocationRights findOne(int id);

	public LocationRights findByUserAndDepartmentAndDeactive(User user , Department department, Boolean deactive);
	
	public 	List <LocationRights> findByUser(User user);
	
	public Set<LocationRights> findByUserAndDepartment(User user , Department department);
	
	
	// location Rights method
	
	public List<Department> findActiveLocationRightsSortByName(Integer user);
	
	public Map<Integer,String> getToLocationListFromUser(Integer user, String mOpt);
	
	public List<LocationRights> findByUserAndDeactive(User user, Boolean deactive);
	
	public LocationRights findByUserAndDeactiveAndDefaultFlg(User user, Boolean deactive,Boolean defaultFlg);
	
	
	
}

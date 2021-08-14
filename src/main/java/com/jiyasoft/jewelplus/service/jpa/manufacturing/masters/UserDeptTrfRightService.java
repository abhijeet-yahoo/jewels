package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QUserDeptTrfRight;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.UserDeptTrfRight;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IUserDeptTrfRightRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class UserDeptTrfRightService implements IUserDeptTrfRightService {

	@Autowired
	private IUserDeptTrfRightRepository userDeptTrfRightRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<UserDeptTrfRight> findAll() {
		return userDeptTrfRightRepository.findAll();
	}

	@Override
	public Page<UserDeptTrfRight> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return userDeptTrfRightRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(UserDeptTrfRight userDeptTrfRight) {
		userDeptTrfRightRepository.save(userDeptTrfRight);

	}

	@Override
	public void delete(int id) {

		UserDeptTrfRight userDeptTrfRight = userDeptTrfRightRepository.findOne(id);
		userDeptTrfRight.setDeactive(true);
		userDeptTrfRight.setDeactiveDt(new java.util.Date());
		userDeptTrfRightRepository.save(userDeptTrfRight);

	}

	@Override
	public Long count() {
		return userDeptTrfRightRepository.count();
	}

	@Override
	public UserDeptTrfRight findOne(int id) {
		return userDeptTrfRightRepository.findOne(id);
	}

	@Override
	public List<UserDeptTrfRight> findByUserAndDepartmentAndDeactive(User user,
			Department department, Boolean deactive) {
		return userDeptTrfRightRepository.findByUserAndDepartmentAndDeactive(user, department, deactive);
	}

	@Override
	public List<UserDeptTrfRight> findByUserAndDeactive(User user,
			Boolean deactive) {
		return userDeptTrfRightRepository.findByUserAndDeactive(user, deactive);
	}

	@Override
	public Set<UserDeptTrfRight> findByUserAndDepartment(User user,
			Department department) {
		
		return userDeptTrfRightRepository.findByUserAndDepartment(user, department);
	}
	
	////////////////////////new methods(4)////////////////////

	@Override
	public List<Department> findActiveUserDeptSortByName(Integer user) {
		
		QUserDeptTrfRight qUserDeptTrfRight = QUserDeptTrfRight.userDeptTrfRight;
		QDepartment qDepartment = QDepartment.department;
		
		JPAQuery query = new JPAQuery(entityManager);
		List<Department> departments = null;
		if(user == null){
			departments = query.from(qUserDeptTrfRight).innerJoin(qUserDeptTrfRight.department,qDepartment).where(qUserDeptTrfRight.deactive.eq(false).and(qDepartment.deactive.eq(false))).list(qUserDeptTrfRight.department);
		}else{
			departments = query.from(qUserDeptTrfRight).innerJoin(qUserDeptTrfRight.department,qDepartment).where(qUserDeptTrfRight.deactive.eq(false).and(qUserDeptTrfRight.user.id.eq(user)).and(qDepartment.deactive.eq(false))).distinct().list(qUserDeptTrfRight.department);	
		}
		return departments;
	}

	@Override
	public Map<Integer, String> getDepartmentListFromUser(Integer user) {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		List<Department> departmentList = findActiveUserDeptSortByName(user);
		
		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());
		}
		return departmentMap;
	}

	@Override
	public List<Department> findActiveUserToDeptSortByName(Integer user,Integer deptId) {
		QUserDeptTrfRight qUserDeptTrfRight = QUserDeptTrfRight.userDeptTrfRight;
		QDepartment qDepartment =QDepartment.department;
		
		JPAQuery query = new JPAQuery(entityManager);
		List<Department> toDepartments = null;
		
		if(user == null){
			toDepartments = query.from(qUserDeptTrfRight).innerJoin(qUserDeptTrfRight.toDeptId,qDepartment).where(qUserDeptTrfRight.deactive.eq(false)
					.and(qDepartment.deactive.eq(false)).and(qUserDeptTrfRight.deactive.eq(false))).list(qUserDeptTrfRight.toDeptId);
		}else{
			toDepartments = query.from(qUserDeptTrfRight).innerJoin(qUserDeptTrfRight.toDeptId,qDepartment).where(qUserDeptTrfRight.user.id.eq(user).
								and(qUserDeptTrfRight.department.id.eq(deptId)).and(qDepartment.deactive.eq(false)).and(qUserDeptTrfRight.deactive.eq(false))).distinct().
								list(qUserDeptTrfRight.toDeptId);
		 
		}
		return toDepartments;
	}

	@Override
	public Map<Integer, String> getToDepartmentListFromUser(Integer user,Integer deptId) {
		Map<Integer, String> toDepartmentMap = new LinkedHashMap<Integer, String>();
		List<Department> toDepartmentList = findActiveUserToDeptSortByName(user,deptId);

		for (Department toDepartment : toDepartmentList) {
			toDepartmentMap.put(toDepartment.getId(), toDepartment.getName());
		}
		return toDepartmentMap;
	}
	
	@Override
	public String getToDepartmentListDropDown(Integer user, Integer deptId,String dropDownId) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> departmentToMap = getToDepartmentListFromUser(user, deptId);
		
		List<Map.Entry<Integer, String>> departmentToMapGv = Lists.newArrayList(departmentToMap.entrySet());
		Collections.sort(departmentToMapGv, byMapValues);

	/*	sb.append("<select id=\"deptTo.id\" name=\"deptTo.id\" class=\"form-control\">");*/
		sb.append("<select id=\""+dropDownId+"\" name=\""+dropDownId+"\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Department -</option>");
		
		Iterator<Entry<Integer, String>> iterator = departmentToMapGv.iterator();
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

	
////////////////////////new methods(5) for PDC////////////////////

	
	@Override
	public List<Department> findActiveUserPdcDeptSortByName(Integer user) {
		QUserDeptTrfRight qUserDeptTrfRight = QUserDeptTrfRight.userDeptTrfRight;
		QDepartment qDepartment =QDepartment.department;
		JPAQuery query = new JPAQuery(entityManager);
		List<Department> departments = null;
		if(user == null){
			departments = query.from(qUserDeptTrfRight).innerJoin(qUserDeptTrfRight.department,qDepartment).where(qUserDeptTrfRight.deactive.eq(false).and(qDepartment.deactive.eq(false))).list(qUserDeptTrfRight.department);
		}else{
			departments = query.from(qUserDeptTrfRight).innerJoin(qUserDeptTrfRight.department,qDepartment).where(qUserDeptTrfRight.user.id.eq(user)
					.and(qUserDeptTrfRight.department.process.equalsIgnoreCase("pd")).and(qDepartment.deactive.eq(false))).distinct().list(qUserDeptTrfRight.department);	
		}
		return departments;
	}

	@Override
	public Map<Integer, String> getDepartmentListFromUserPdc(Integer user) {
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		List<Department> departmentList = findActiveUserPdcDeptSortByName(user);
		
		for (Department department : departmentList) {
			departmentMap.put(department.getId(), department.getName());

		}
		return departmentMap;
	}

	@Override
	public List<Department> findActiveUserPdcToDeptSortByName(Integer user,
			Integer deptId) {
		QUserDeptTrfRight qUserDeptTrfRight = QUserDeptTrfRight.userDeptTrfRight;
		QDepartment qDepartment =QDepartment.department;
		JPAQuery query = new JPAQuery(entityManager);
		List<Department> toDepartments = null;
		
		System.out.println("printing the user"+user);
		
		
		if(user == null){
			toDepartments = query.from(qUserDeptTrfRight).innerJoin(qUserDeptTrfRight.toDeptId,qDepartment).where(qUserDeptTrfRight.deactive.eq(false).and(qDepartment.deactive.eq(false))).list(qUserDeptTrfRight.toDeptId);
		}else{
			toDepartments = query.from(qUserDeptTrfRight).innerJoin(qUserDeptTrfRight.toDeptId,qDepartment).where(qUserDeptTrfRight.user.id.eq(user).
								and(qUserDeptTrfRight.department.id.eq(deptId)).and(qUserDeptTrfRight.toDeptId.process.equalsIgnoreCase("pd")).and(qDepartment.deactive.eq(false))).distinct().
								list(qUserDeptTrfRight.toDeptId);
		
		}
		return toDepartments;
	}

	@Override
	public Map<Integer, String> getToDepartmentListFromUserPdc(Integer user,
			Integer deptId) {
		Map<Integer, String> toDepartmentMap = new LinkedHashMap<Integer, String>();
		List<Department> toDepartmentList = findActiveUserPdcToDeptSortByName(user,deptId);

		for (Department toDepartment : toDepartmentList) {
			toDepartmentMap.put(toDepartment.getId(), toDepartment.getName());
		}
		return toDepartmentMap;
	}

	@Override
	public String getToDepartmentListDropDownPdc(Integer user, Integer deptId) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> departmentToMap = getToDepartmentListFromUserPdc(user, deptId);
		
		List<Map.Entry<Integer, String>> departmentToMapGv = Lists.newArrayList(departmentToMap.entrySet());
		Collections.sort(departmentToMapGv, byMapValues);

		sb.append("<select id=\"deptTo.id\" name=\"deptTo.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Department -</option>");
		
		Iterator<Entry<Integer, String>> iterator = departmentToMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
					.append(et.getValue()).append("</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}

	@Override
	public String getToLocationRightsListDropDown(Integer user, String dropDownId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> departmentToMap = getDepartmentListFromUser(user);
		
		List<Map.Entry<Integer, String>> departmentToMapGv = Lists.newArrayList(departmentToMap.entrySet());
		Collections.sort(departmentToMapGv, byMapValues);

		sb.append("<select id=\""+dropDownId+"\" name=\""+dropDownId+"\" class=\"form-control\"  onChange=\"javascript:popReadyBagDetails(this.value);popDeptTo();\">");
		sb.append("<option value=\"\">- Select Location -</option>");
		
		Iterator<Entry<Integer, String>> iterator = departmentToMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
					.append(et.getValue()).append("</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}

	@Override
	public Map<Integer, String> getToDepartmentListFromUserForRepairIss(Integer user, Integer deptId) {
		// TODO Auto-generated method stub
			Map<Integer, String> toDepartmentMap = new LinkedHashMap<Integer, String>();
			List<Department> toDepartmentList = findActiveUserToDeptSortByName(user,deptId);

			for (Department toDepartment : toDepartmentList) {
				if(toDepartment.getName().equalsIgnoreCase("Repair") || toDepartment.getName().equalsIgnoreCase("REPARING-HU")) {
					continue;
				}
				
				toDepartmentMap.put(toDepartment.getId(), toDepartment.getName());
			}
			return toDepartmentMap;
		}
	}

	



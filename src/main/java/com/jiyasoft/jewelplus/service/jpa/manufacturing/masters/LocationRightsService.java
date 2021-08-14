package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDepartment;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLocationRights;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ILocationRightsRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class LocationRightsService implements ILocationRightsService {
	
	@Autowired
	private ILocationRightsRepository locationRightsRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void save(LocationRights locationRights) {
		// TODO Auto-generated method stub
		locationRightsRepository.save(locationRights);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		LocationRights locationRights = locationRightsRepository.findOne(id);
		locationRights.setDeactive(true);
		locationRights.setDeactiveDt(new Date());
		locationRightsRepository.save(locationRights);
	}

	@Override
	public LocationRights findOne(int id) {
		// TODO Auto-generated method stub
		return locationRightsRepository.findOne(id);
	}

	@Override
	public LocationRights findByUserAndDepartmentAndDeactive(User user, Department department, Boolean deactive) {
		// TODO Auto-generated method stub
		return locationRightsRepository.findByUserAndDepartmentAndDeactive(user, department, deactive);
	}

	@Override
	public List<LocationRights> findByUser(User user) {
		// TODO Auto-generated method stub
		return locationRightsRepository.findByUser(user);
	}

	@Override
	public Set<LocationRights> findByUserAndDepartment(User user, Department department) {
		// TODO Auto-generated method stub
		return locationRightsRepository.findByUserAndDepartment(user, department);
	}


	@Override
	public List<Department> findActiveLocationRightsSortByName(Integer user) {
	
		QLocationRights qLocationRights = QLocationRights.locationRights;
		QDepartment qDepartment =QDepartment.department;
		
		JPAQuery query = new JPAQuery(entityManager);
		List<Department> toDepartments = null;
		
		if(user == null){
			toDepartments = query.from(qLocationRights).innerJoin(qLocationRights.department,qDepartment).where(qLocationRights.deactive.eq(false).and(qDepartment.deactive.eq(false))).list(qLocationRights.department);
		}else{
			toDepartments = query.from(qLocationRights).innerJoin(qLocationRights.department,qDepartment).where(qLocationRights.user.id.eq(user).
								and(qDepartment.deactive.eq(false))).distinct().
								list(qLocationRights.department);
		
		}
		return toDepartments;
	}

	@Override
	public Map<Integer, String> getToLocationListFromUser(Integer user,String mOpt) {
		// TODO Auto-generated method stub
		Map<Integer, String> toDepartmentMap = new LinkedHashMap<Integer, String>();
		List<Department> toDepartmentList = findActiveLocationRightsSortByName(user);

		if(mOpt.equalsIgnoreCase("All")) {
			for (Department toDepartment : toDepartmentList) {
				toDepartmentMap.put(toDepartment.getId(), toDepartment.getName());
			}	
		}else if(mOpt.equalsIgnoreCase("metal")) {
			for (Department toDepartment : toDepartmentList) {
				if(toDepartment.getLooseMetalStk().equals(true)) {
					toDepartmentMap.put(toDepartment.getId(), toDepartment.getName());	
				}
				
			}	
		}else if(mOpt.equalsIgnoreCase("component")) {
			for (Department toDepartment : toDepartmentList) {
				if(toDepartment.getComponentStk().equals(true)) {
					toDepartmentMap.put(toDepartment.getId(), toDepartment.getName());	
				}
				
			}	
		}else if(mOpt.equalsIgnoreCase("stone")) {
			for (Department toDepartment : toDepartmentList) {
				if(toDepartment.getStoneStk().equals(true)) {
					toDepartmentMap.put(toDepartment.getId(), toDepartment.getName());	
				}
				
			}	
		}
		
		return toDepartmentMap;
	}

	@Override
	public List<LocationRights> findByUserAndDeactive(User user, Boolean deactive) {
		// TODO Auto-generated method stub
		return locationRightsRepository.findByUserAndDeactive(user, deactive);
	}

	@Override
	public LocationRights findByUserAndDeactiveAndDefaultFlg(User user, Boolean deactive, Boolean defaultFlg) {
		// TODO Auto-generated method stub
		return locationRightsRepository.findByUserAndDeactiveAndDefaultFlg(user,  deactive, defaultFlg);
	}

	
	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left,
				Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

}

package com.jiyasoft.jewelplus.service.admin;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.QRoleRights;
import com.jiyasoft.jewelplus.domain.admin.RoleMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.repository.admin.IRoleRightsRepository;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class RoleRightsService{
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private IRoleRightsRepository roleRightsRepository;
	

	
	public List<RoleRights> findByRoleMastAndDeactive(RoleMast roleMast,Boolean deactive) {
		return roleRightsRepository.findByRoleMastAndDeactive(roleMast,deactive);
	}

	
	public RoleRights findByRoleMastAndMenuMast(RoleMast roleMast,
			MenuMast menuMast) {
		return roleRightsRepository.findByRoleMastAndMenuMast(roleMast, menuMast);
	}

	
	public List<RoleRights> findAll() {
		return roleRightsRepository.findAll();
	}

	
	public void save(RoleRights roleRights) {
		roleRightsRepository.save(roleRights);
	}

	
	public void delete(int id) {
		roleRightsRepository.delete(id);
	}

	
	public Long count() {
		return roleRightsRepository.count();
	}

	
	public RoleRights findOne(int id) {
		return roleRightsRepository.findOne(id);
	}

	
	public Map<Integer, String> getModalList(Integer roleMastId) {
		Map<Integer, String> modalMap = new LinkedHashMap<Integer, String>();
		List<RoleRights> roleRights = findActiveModalList(roleMastId);
		for(RoleRights roleRight:roleRights){
			modalMap.put(roleRight.getId(), roleRight.getMenuMast().getMenuHeading());
		}
		return modalMap;
	}

	
	public List<RoleRights> findActiveModalList(Integer roleMastId) {		
		QRoleRights qRoleRights = QRoleRights.roleRights;
		JPAQuery query = new JPAQuery(entityManager);
		List<RoleRights> roleRightsList = null;
		roleRightsList = query.from(qRoleRights).where(qRoleRights.roleMast.id.eq(roleMastId)).list(qRoleRights);
		return roleRightsList;
		
	}

	
	
	public String getModalListDropDown(Integer roleMastId) {
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> modalMap = getModalList(roleMastId);
		
		List<Map.Entry<Integer, String>> subCategoryMapGv = Lists.newArrayList(modalMap.entrySet());
	    Collections.sort(subCategoryMapGv, byMapValues);

		sb.append("<select id=\"roleRights.id\" name=\"roleRights.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Modal -</option>");

		Iterator<Entry<Integer, String>> iterator = subCategoryMapGv.iterator();
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
	
	
}

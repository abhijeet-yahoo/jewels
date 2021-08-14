package com.jiyasoft.jewelplus.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.RoleMast;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.repository.admin.IUserRoleRepository;


@Service
@Repository
@Transactional
public class UserRoleService {

	
	@Autowired
	private IUserRoleRepository userRoleRepository;
	
	
	public UserRole findByUserAndRoleMast(User user,RoleMast roleMast){
		return userRoleRepository.findByUserAndRoleMast(user, roleMast);
	}
	
	
	public void save(UserRole userRole){
		userRoleRepository.save(userRole);
	}
	
	public UserRole findByUser(User user){
		return userRoleRepository.findByUser(user);
	}
	
	
}

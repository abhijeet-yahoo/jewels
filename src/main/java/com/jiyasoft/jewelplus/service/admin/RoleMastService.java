package com.jiyasoft.jewelplus.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.RoleMast;
import com.jiyasoft.jewelplus.repository.admin.IRoleMastRepository;


@Service
@Repository
@Transactional
public class RoleMastService {
	
	@Autowired
	private IRoleMastRepository roleMastRepository;
	
	
	public RoleMast findOne(Integer id){
		return roleMastRepository.findOne(id);
	}
	
	
	public void save(RoleMast roleMast){
		roleMastRepository.save(roleMast);
	}
	
	public RoleMast findByRoleNm(String roleNm){
		return roleMastRepository.findByRoleNm(roleNm);
	}
	
	public List<RoleMast> findAll(){
		return roleMastRepository.findAll();
	}
 	
	public Long count(){
		return roleMastRepository.count();
	}
	
	
	public Map<Integer, String> getRoleMastList() {
		Map<Integer, String> roleMastMap = new HashMap<Integer, String>();
		List<RoleMast> roleMastList = roleMastRepository.findAll();

		for (RoleMast roleMast : roleMastList) {
			roleMastMap.put(roleMast.getId(), roleMast.getRoleNm());
		}

		return roleMastMap;
	}

	

}

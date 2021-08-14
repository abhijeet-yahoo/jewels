package com.jiyasoft.jewelplus.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.ModuleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.repository.admin.IModuleRightsRepository;

@Service
@Repository
@Transactional
public class ModuleRightsService {
	
	@Autowired
	private IModuleRightsRepository moduleRightsRepository;
	
	public ModuleRights findByUserAndModuleName(User user,String moduleName){
		return moduleRightsRepository.findByUserAndModuleName(user, moduleName);	
	}
	

}

package com.jiyasoft.jewelplus.service.admin;

import java.util.ArrayList;
import java.util.List;

//import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.Role;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubConcept;
import com.jiyasoft.jewelplus.repository.admin.IRoleRepository;
import com.jiyasoft.jewelplus.repository.admin.IUserRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ICategoryRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IConceptRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISubCategoryRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISubConceptRepository;

@Transactional
@Service
public class DbService {

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private ISubCategoryRepository subCategoryRepository;

	@Autowired
	private IConceptRepository conceptRepository;

	@Autowired
	private ISubConceptRepository subConceptRepository;

	//@PostConstruct
	public void init() {
		Role roleUser = null;
		if (roleRepository.findByName("ROLE_USER") == null) {
			roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);
		}

//		Role roleAdmin = null;
//		if (roleRepository.findByName("ROLE_ADMIN") == null) {
//			roleAdmin = new Role();
//			roleAdmin.setName("ROLE_ADMIN");
//			roleRepository.save(roleAdmin);
//		}

		User userAdmin = new User();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		for (int x = 1; x < 16; x++) {
			if (userRepository.findOne(x) == null) {
				userAdmin = new User();
				userAdmin.setId(x);
				userAdmin.setName("admin" + x);
				userAdmin.setPassword(encoder.encode("admin" + x));
				userAdmin.setEmail("admin@admin.com");
				userAdmin.setDeactive(false);
				//userAdmin.setCanRead(true);
				//userAdmin.setCanInsert(true);
				//userAdmin.setCanUpdate(true);
				//userAdmin.setCanDelete(true);

				List<Role> roles = new ArrayList<Role>();
				roles.add(roleRepository.findByName("ROLE_USER"));
//				roles.add(roleAdmin);
				userAdmin.setRoles(roles);

				userRepository.save(userAdmin);
			}
		}

		for (int x = 1; x < 6; x++) {
			Category category = new Category();
			category.setName("category" + x);
			category.setDeactive(false);
			categoryRepository.save(category);

			for (int y = 1; y < 3; y++) {
				SubCategory subCategory = new SubCategory();
				subCategory.setCategory(category);
				subCategory.setName("sub category" + x + "." + y);
				subCategory.setDeactive(false);
				subCategoryRepository.save(subCategory);
			}

			Concept concept = new Concept();
			concept.setName("concept" + x);
			concept.setDeactive(false);
			conceptRepository.save(concept);

			for (int y = 1; y < 3; y++) {
				SubConcept subConcept = new SubConcept();
				subConcept.setConcept(concept);
				subConcept.setName("Sub Concept" + x + "." + y);
				subConcept.setDeactive(false);
				subConceptRepository.save(subConcept);
			}
		}
	}
}

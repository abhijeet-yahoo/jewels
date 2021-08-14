package com.jiyasoft.jewelplus.service.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.QUser;
import com.jiyasoft.jewelplus.domain.admin.Role;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.repository.admin.IRoleRepository;
import com.jiyasoft.jewelplus.repository.admin.IUserRepository;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Page<User> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return userRepository.findAll(new PageRequest(page, limit,
				(order.equalsIgnoreCase("asc") ? Direction.DESC : Direction.ASC), sort));
	}

	public void save(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		
		/*if(user.getName().equalsIgnoreCase("admin")){
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
		}*/
		user.setRoles(roles);

		userRepository.save(user);
	}

	public void delete(int id) {
		User user = userRepository.findOne(id);
		user.setDeactive(true);
		user.setDeactiveDt(new java.util.Date());
		userRepository.save(user);
	}

	public Long count() {
		return userRepository.count();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public User findByName(String userName) {
		return userRepository.findByName(userName);	
	}

	public User findByIdAndName(Integer id, String userName) {
		return userRepository.findByIdAndName(id, userName);	
	}

	public Long count(String colName, String colValue, Boolean onlyActive) {
		QUser qUser = QUser.user;
		BooleanExpression expression = qUser.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qUser.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qUser.deactive.eq(false).and(
						qUser.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qUser.name.like(colValue + "%");
			}
		}

		return userRepository.count(expression);

	}

	public Page<User> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QUser qUser = QUser.user;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qUser.deactive.eq(false);
			} else {
				expression = qUser.deactive.eq(false).and(
						qUser.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qUser.name.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<User> userList = (Page<User>) userRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return userList;
	}
	
	//new method
	public Map<Integer, String> getUserList() {
		Map<Integer, String> userMap = new HashMap<Integer, String>();
		List<User> userList = findActiveUsers();

		for (User user : userList) {
			userMap.put(user.getId(), user.getName());
		}

		return userMap;
	}

	//new method
	public List<User> findActiveUsers() {
		QUser qUser = QUser.user;
		BooleanExpression expression = qUser.deactive.eq(false);
		List<User> userList = (List<User>) userRepository.findAll(expression);
		return userList;
	}
	
	
	
	public Page<User> searchAll(Integer limit, Integer offset, String sort,
			String order,String search, Boolean onlyActive) {
		
		QUser qUser = QUser.user;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qUser.deactive.eq(false);
			}else{
				expression = qUser.deactive.eq(false).and(qUser.name.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression = qUser.name.like("%" + search + "%");
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		}
		
		Page<User> userList =(Page<User>) userRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		
		return userList;
		
		
	}
	
	
	

}

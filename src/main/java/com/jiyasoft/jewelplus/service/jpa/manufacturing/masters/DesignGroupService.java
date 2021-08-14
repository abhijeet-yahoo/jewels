package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesignGroup;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignGroupRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignGroupService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class DesignGroupService implements IDesignGroupService{
	
	@Autowired
	IDesignGroupRepository designGroupRepository;

	@Override
	public List<DesignGroup> findAll() {
		
		return designGroupRepository.findAll();
	}

	@Override
	public Page<DesignGroup> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(DesignGroup designGroup) {
		designGroupRepository.save(designGroup);
		
	}

	@Override
	public void delete(int id) {
		DesignGroup designGroup = designGroupRepository.findOne(id);
		designGroup.setDeactive(true);
		designGroup.setDeactiveDt(new java.util.Date());
		designGroupRepository.save(designGroup);
	}

	@Override
	public Long count() {
		return designGroupRepository.count();
	}

	@Override
	public DesignGroup findOne(int id) {
		return designGroupRepository.findOne(id);
	}

	@Override
	public DesignGroup findByName(String designGroupName) {
		return designGroupRepository.findByName(designGroupName);
	}

	@Override
	public Map<Integer, String> getDesignGroupList() {
		Map<Integer, String> designGroupMap = new LinkedHashMap<Integer, String>();
		Page<DesignGroup> designGroupList = findActiveDesignGroupsSortByName();

		for (DesignGroup designGroup : designGroupList) {
			designGroupMap.put(designGroup.getId(), designGroup.getName());
		}

		return designGroupMap;
	}

	@Override
	public List<DesignGroup> findActiveDesignGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DesignGroup> findActiveDesignGroupsSortByName() {
		QDesignGroup qDesignGroup = QDesignGroup.designGroup;
		BooleanExpression expression = qDesignGroup.deactive.eq(false);
		Page<DesignGroup> designGroupList = designGroupRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return designGroupList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QDesignGroup qDesignGroup = QDesignGroup.designGroup;
		BooleanExpression expression = qDesignGroup.deactive.eq(false);
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDesignGroup.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qDesignGroup.deactive.eq(false).and(
						qDesignGroup.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qDesignGroup.name.like(colValue + "%");
			}
		}

		return designGroupRepository.count(expression);

	}

	@Override
	public Page<DesignGroup> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QDesignGroup qDesignGroup = QDesignGroup.designGroup;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qDesignGroup.deactive.eq(false);
			} else {
				expression = qDesignGroup.deactive.eq(false).and(
						qDesignGroup.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qDesignGroup.name.like(name + "%");
			}
		}

		// new addition
		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}
		
		// new addition

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<DesignGroup> designGroupList = (Page<DesignGroup>) designGroupRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return designGroupList;

	}

	@Override
	public Page<DesignGroup> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QDesignGroup qDesignGroup = QDesignGroup.designGroup;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			
			System.out.println("onlyActive" +onlyActive);
			
			if (search == null) {
				expression = qDesignGroup.deactive.eq(false);
			} else {
				expression = qDesignGroup.deactive.eq(false).and(qDesignGroup.name.like("%" + search + "%"));
			}
		} else {
			if (search != null) {
				expression = qDesignGroup.name.like("%" + search + "%");
			}
		}

		System.out.println("Expression "+expression);
		
		if (limit == null) {
			limit = 1000;
		}
		if (offset == null) {
			offset = 0;
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		}
		Page<DesignGroup> designGroupMastList = (Page<DesignGroup>) designGroupRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

	//	System.out.println("expression " + designGroupMastList.getSize());
		
		return designGroupMastList;
		
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QDesignGroup qDesignGroup = QDesignGroup.designGroup;
		BooleanExpression expression = null;
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDesignGroup.deactive.eq(false);
			} else {
				expression = qDesignGroup.deactive.eq(false).and(qDesignGroup.name.like("%" + colValue + "%"));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qDesignGroup.name.like("%" + colValue + "%");
			}
		}

		return designGroupRepository.count(expression);
	}
	}
	

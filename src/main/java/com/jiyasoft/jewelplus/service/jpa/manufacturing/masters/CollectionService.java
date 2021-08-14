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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QCollectionMaster;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ICollectionRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICollectionService;
import com.mysema.query.types.expr.BooleanExpression;




@Service
@Repository
@Transactional
public class CollectionService implements ICollectionService{
	
	@Autowired
	private ICollectionRepository collectionRepository;

	
	@Override
	public List<CollectionMaster> findAll() {

		return collectionRepository.findAll();
	}

	@Override
	public Page<CollectionMaster> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		limit = (limit == null ? 10 : limit);
		offset = (offset == null ? 0 : offset);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return collectionRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(CollectionMaster collectionMaster) {

		collectionRepository.save(collectionMaster);
	}

	@Override
	public void delete(int id) {
		CollectionMaster collectionMaster = collectionRepository.findOne(id);
		
		collectionMaster.setDeactive(true);
		collectionMaster.setDeactiveDt(new java.util.Date());
		collectionRepository.save(collectionMaster);
		
	}

	@Override
	public Long count() {
		
		return collectionRepository.count();
	}

	@Override
	public CollectionMaster findOne(int id) {
		
		return collectionRepository.findOne(id);
	}

	@Override
	public CollectionMaster findByName(String collectionName) {
		
		return collectionRepository.findByName(collectionName);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QCollectionMaster qCollectionMaster = QCollectionMaster.collectionMaster;
		BooleanExpression expression = qCollectionMaster.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qCollectionMaster.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qCollectionMaster.deactive.eq(false).and(qCollectionMaster.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qCollectionMaster.name.like(colValue + "%");
			}
		}

		return collectionRepository.count(expression);
		
	}

	@Override
	public Page<CollectionMaster> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QCollectionMaster qCollectionMaster = QCollectionMaster.collectionMaster;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qCollectionMaster.deactive.eq(false);
			} else {
				expression = qCollectionMaster.deactive.eq(false).and(
						qCollectionMaster.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qCollectionMaster.name.like(name + "%");
			}
		}
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<CollectionMaster> collectionMasterList = (Page<CollectionMaster>) collectionRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return collectionMasterList;
		
	}

	
	@Override
	public CollectionMaster findByCollectionCode(String collectionCode) {
		
		return collectionRepository.findByCollectionCode(collectionCode);
	}

	@Override
	public Map<Integer, String> getCollectionList() {
	
		Map<Integer, String> collectionMap = new LinkedHashMap<Integer, String>();
		Page<CollectionMaster> collectionMasterList = findActiveCollectionsSortByName();

		for (CollectionMaster collectionMaster : collectionMasterList) {
			collectionMap.put(collectionMaster.getId(), collectionMaster.getName());
		}

		return collectionMap;
		
	}

	@Override
	public Page<CollectionMaster> findActiveCollectionsSortByName() {
	
		QCollectionMaster qCollectionMaster = QCollectionMaster.collectionMaster;
		BooleanExpression expression = qCollectionMaster.deactive.eq(false);

		Page<CollectionMaster> collectionMasterList = collectionRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return collectionMasterList;
		
		
	}
	
	
}

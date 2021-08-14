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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMoldType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesignMoldType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignMoldTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMoldTypeService;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class DesignMoldTypeService implements IDesignMoldTypeService {
	
	@Autowired
	private IDesignMoldTypeRepository designMoldTypeRepository;

	@Override
	public List<DesignMoldType> findAll() {
		return designMoldTypeRepository.findAll();
	}

	@Override
	public Page<DesignMoldType> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return designMoldTypeRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(DesignMoldType designMoldType) {
		designMoldTypeRepository.save(designMoldType);
	}

	@Override
	public void delete(int id) {
		DesignMoldType designMoldType = designMoldTypeRepository.findOne(id);
		designMoldType.setDeactive(true);
		designMoldType.setDeactiveDt(new java.util.Date());
		designMoldTypeRepository.save(designMoldType);
	}

	@Override
	public Long count() {
		return designMoldTypeRepository.count();
	}

	@Override
	public DesignMoldType findOne(int id) {
		return designMoldTypeRepository.findOne(id);
	}

	@Override
	public DesignMoldType findByName(String name) {
		return designMoldTypeRepository.findByName(name);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QDesignMoldType qDesignMoldType = QDesignMoldType.designMoldType;
		BooleanExpression expression = qDesignMoldType.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDesignMoldType.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qDesignMoldType.deactive.eq(false).and(
						qDesignMoldType.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {

				expression = qDesignMoldType.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return designMoldTypeRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return designMoldTypeRepository.count();
			}
		}

		return designMoldTypeRepository.count(expression);
	}

	@Override
	public Page<DesignMoldType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QDesignMoldType qDesignMoldType = QDesignMoldType.designMoldType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qDesignMoldType.deactive.eq(false);
			} else {
				expression = qDesignMoldType.deactive.eq(false).and(
						qDesignMoldType.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qDesignMoldType.name.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<DesignMoldType> designMoldTypeList = (Page<DesignMoldType>) designMoldTypeRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return designMoldTypeList;
	}

	@Override
	public Map<Integer, String> getDesignMoldTypeList() {
		Map<Integer, String> designMoldTypeMap = new LinkedHashMap<Integer, String>();
		Page<DesignMoldType> designMoldTypeList = findActiveDesignMoldTypeSortByName();

		for (DesignMoldType designMoldType : designMoldTypeList) {
			designMoldTypeMap.put(designMoldType.getId(), designMoldType.getName());
		}

		return designMoldTypeMap;
	}
	
	

	@Override
	public Page<DesignMoldType> findActiveDesignMoldTypeSortByName() {
		QDesignMoldType qDesignMoldType = QDesignMoldType.designMoldType;
		BooleanExpression expression = qDesignMoldType.deactive.eq(false);
		Page<DesignMoldType> designMoldList = designMoldTypeRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return designMoldList;
	}
	
	

}

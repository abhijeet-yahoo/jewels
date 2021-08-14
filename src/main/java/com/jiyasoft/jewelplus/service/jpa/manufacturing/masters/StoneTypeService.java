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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IStoneTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StoneTypeService implements IStoneTypeService {

	@Autowired
	private IStoneTypeRepository stoneTypeRepository;

	@Override
	public List<StoneType> findAll() {
		return stoneTypeRepository.findAll();
	}

	@Override
	public Page<StoneType> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		return stoneTypeRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(StoneType stoneType) {
		stoneTypeRepository.save(stoneType);
	}

	@Override
	public void delete(int id) {
		StoneType stoneType = stoneTypeRepository.findOne(id);
		stoneType.setDeactive(true);
		stoneType.setDeactiveDt(new java.util.Date());
		stoneTypeRepository.save(stoneType);

	}

	@Override
	public Long count() {
		return stoneTypeRepository.count();
	}

	@Override
	public StoneType findOne(int id) {
		return stoneTypeRepository.findOne(id);
	}

	@Override
	public StoneType findByName(String name) {
		return stoneTypeRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getStoneTypeList() {
	
		Map<Integer, String> stoneTypeMap = new LinkedHashMap<Integer, String>();
		Page<StoneType> stoneTypeList = findActiveStoneTypesSortByName();

		for (StoneType stoneType : stoneTypeList) {
			stoneTypeMap.put(stoneType.getId(), stoneType.getName());
		}

		return stoneTypeMap;
	}

	@Override
	public List<StoneType> findActiveStoneTypes() {
		
		QStoneType qStoneType = QStoneType.stoneType;
		BooleanExpression expression = qStoneType.deactive.eq(false);

		List<StoneType> stoneTypeList = (List<StoneType>) stoneTypeRepository.findAll(expression);

		return stoneTypeList;
	}

	@Override
	public Page<StoneType> findActiveStoneTypesSortByName() {

		QStoneType qStoneType = QStoneType.stoneType;
		BooleanExpression expression = qStoneType.deactive.eq(false);

		Page<StoneType> stoneTypeList = stoneTypeRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return stoneTypeList;

	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QStoneType qStoneType = QStoneType.stoneType;
		BooleanExpression expression = qStoneType.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qStoneType.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qStoneType.deactive.eq(false).and(
						qStoneType.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qStoneType.name.like(colValue + "%");
			}
		}

		return stoneTypeRepository.count(expression);
	}

	@Override
	public Page<StoneType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QStoneType qStoneType = QStoneType.stoneType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qStoneType.deactive.eq(false);
			} else {
				expression = qStoneType.deactive.eq(false).and(
						qStoneType.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qStoneType.name.like(name + "%");
			}
		}

		//int page = (offset == 0 ? 0 : (offset / limit));
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
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

		Page<StoneType> stoneTypeList = (Page<StoneType>) stoneTypeRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return stoneTypeList;
	}

	@Override
	public Page<StoneType> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {

		QStoneType qStoneType = QStoneType.stoneType;
		BooleanExpression expression = null;

		
		
		if (onlyActive) {
			if (search == null) {
				expression = qStoneType.deactive.eq(false);
			}else{
				expression = qStoneType.deactive.eq(false).and(qStoneType.name.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression = qStoneType.name.like("%" + search + "%");
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
		
		Page<StoneType> stoneTypeMastList =(Page<StoneType>) stoneTypeRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return stoneTypeMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QStoneType qStoneType = QStoneType.stoneType;
		BooleanExpression expression = null;
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qStoneType.deactive.eq(false);			
				}else{
					expression = qStoneType.deactive.eq(false).and(qStoneType.name.like("%" + colValue + "%"));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qStoneType.name.like("%" + colValue + "%");
				}
			}
			
			return stoneTypeRepository.count(expression);
	}

}

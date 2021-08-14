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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.InwardType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QInwardType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IInwardTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class InwardTypeService implements IInwardTypeService {
	@Autowired
	private IInwardTypeRepository inwardTypeRepository;

	@Override
	public List<InwardType> findAll() {
		return inwardTypeRepository.findAll();
	}

	@Override
	public Page<InwardType> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		return inwardTypeRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(InwardType inwardType) {
		inwardTypeRepository.save(inwardType);

	}

	@Override
	public void delete(int id) {
		InwardType inwardType = inwardTypeRepository.findOne(id);
		inwardType.setDeactive(true);
		inwardType.setDeactiveDt(new java.util.Date());
		inwardTypeRepository.save(inwardType);

	}

	@Override
	public Long count() {
		return inwardTypeRepository.count();
	}

	@Override
	public InwardType findOne(int id) {
		return inwardTypeRepository.findOne(id);
	}

	@Override
	public InwardType findByName(String name) {
		return inwardTypeRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getInwardTypeList() {
		Map<Integer, String> inwardTypeMap = new LinkedHashMap<Integer, String>();

		Page<InwardType> inwardTypeList = findActiveInwardTypesSortByName();

		for (InwardType inwardType : inwardTypeList) {
			inwardTypeMap.put(inwardType.getId(), inwardType.getName());
		}

		return inwardTypeMap;
	}

	@Override
	public List<InwardType> findActiveInwardTypes() {

		QInwardType qInwardType = QInwardType.inwardType;
		BooleanExpression expression = qInwardType.deactive.eq(false);

		List<InwardType> inwardTypeList = (List<InwardType>) inwardTypeRepository
				.findAll(expression);

		return inwardTypeList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QInwardType qInwardType = QInwardType.inwardType;
		BooleanExpression expression = qInwardType.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qInwardType.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qInwardType.deactive.eq(false).and(
						qInwardType.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qInwardType.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("name")
					&& colValue != null) {
				expression = qInwardType.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return inwardTypeRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return inwardTypeRepository.count();
			}
		}

		return inwardTypeRepository.count(expression);
	}

	@Override
	public Page<InwardType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QInwardType qInwardType = QInwardType.inwardType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qInwardType.deactive.eq(false);
			} else {
				expression = qInwardType.deactive.eq(false).and(
						qInwardType.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qInwardType.name.like(name + "%");
			}
		}

	/*	int page = (offset == 0 ? 0 : (offset / limit));*/
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

		Page<InwardType> inwardTypeList = (Page<InwardType>) inwardTypeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return inwardTypeList;
	}

	@Override
	public Page<InwardType> findActiveInwardTypesSortByName() {

		System.out.println("in active new method()\n\n\n");

		QInwardType qInwardType = QInwardType.inwardType;
		BooleanExpression expression = qInwardType.deactive.eq(false);

		Page<InwardType> inwardTypeList = inwardTypeRepository.findAll(
				expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return inwardTypeList;
	}

	@Override
	public Page<InwardType> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {
		
		QInwardType qInwardType = QInwardType.inwardType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (search == null) {
				expression = qInwardType.deactive.eq(false);
			}else{
				expression = qInwardType.deactive.eq(false).and(qInwardType.name.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression = qInwardType.name.like("%" + search + "%");
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
		
		Page<InwardType> inwardMastList =(Page<InwardType>) inwardTypeRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return inwardMastList;

	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		
		QInwardType qInwardType = QInwardType.inwardType;
		BooleanExpression expression = null;
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qInwardType.deactive.eq(false);			
				}else{
					expression = qInwardType.deactive.eq(false).and(qInwardType.name.like("%" + colValue + "%"));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qInwardType.name.like("%" + colValue + "%");
				}
			}
			
			return inwardTypeRepository.count(expression);
	}

}

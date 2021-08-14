package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ILabourTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class LabourTypeService implements ILabourTypeService {
	
	@Autowired
	private ILabourTypeRepository labourTypeRepository;
	


	@Override
	public List<LabourType> findAll() {
		return labourTypeRepository.findAll();
	}

	@Override
	public Page<LabourType> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) 
		{
			sort = "id";
		}
			return labourTypeRepository.findAll(new PageRequest(page, limit,
				(order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), sort));
	}

	@Override
	public void save(LabourType labtype) {
		labourTypeRepository.save(labtype);
		
	}

	@Override
	public void delete(int id) {
		LabourType labtype = labourTypeRepository.findOne(id);
		labtype.setDeactive(true);
		labtype.setDeactiveDt(new java.util.Date());
		labourTypeRepository.save(labtype);
	}

	@Override
	public Long count() {
		return labourTypeRepository.count();
	}

	@Override
	public LabourType findOne(int id) {
		return labourTypeRepository.findOne(id);
	}

	@Override
	public LabourType findByName(String name) {
		return labourTypeRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getLabourTypeList() {
		
		Map<Integer, String> labourTypeMap = new HashMap<Integer, String>();
		List<LabourType> labourTypeList = findActiveLabourTypes();

		for (LabourType labourType : labourTypeList) {
			labourTypeMap.put(labourType.getId(), labourType.getName());
		}

		return labourTypeMap;
	}

	@Override
	public List<LabourType> findActiveLabourTypes() {

		QLabourType qLabourType=QLabourType.labourType;
		
		BooleanExpression expression = qLabourType.deactive.eq(false);

		List<LabourType> labourTypeList = (List<LabourType>) labourTypeRepository
				.findAll(expression);

		return labourTypeList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QLabourType qLabourType = QLabourType.labourType;
		BooleanExpression expression = qLabourType.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qLabourType.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qLabourType.deactive.eq(false).and(
						qLabourType.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qLabourType.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("name")
					&& colValue != null) {
				expression = qLabourType.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return labourTypeRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return labourTypeRepository.count();
			}
		}

		return labourTypeRepository.count(expression);
	}

	@Override
	public Page<LabourType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QLabourType qLabourType = QLabourType.labourType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qLabourType.deactive.eq(false);
			} else {
				expression = qLabourType.deactive.eq(false).and(
						qLabourType.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qLabourType.name.like(name + "%");
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

		Page<LabourType> labourTypeList = (Page<LabourType>) labourTypeRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return labourTypeList;
	}

	@Override
	public Page<LabourType> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {

		QLabourType qLabourType = QLabourType.labourType;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qLabourType.deactive.eq(false);
			}else{
				expression = qLabourType.deactive.eq(false).and(qLabourType.name.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression = qLabourType.name.like("%" + search + "%");
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
		
		Page<LabourType> labourTypeMastList =(Page<LabourType>) labourTypeRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return labourTypeMastList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		QLabourType qLabourType = QLabourType.labourType;
		BooleanExpression expression = null;
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qLabourType.deactive.eq(false);			
				}else{
					expression = qLabourType.deactive.eq(false).and(qLabourType.name.like("%" + colValue + "%"));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qLabourType.name.like("%" + colValue + "%");
				}
			}
			
			return labourTypeRepository.count(expression);
	}

	@Override
	public LabourType findByCodeAndDeactive(String code, Boolean deactive) {
		// TODO Auto-generated method stub
		return labourTypeRepository.findByCodeAndDeactive(code, deactive);
	}
	
		

}

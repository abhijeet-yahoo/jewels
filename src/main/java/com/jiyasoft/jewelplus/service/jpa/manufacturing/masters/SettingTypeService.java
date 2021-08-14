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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISettingTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class SettingTypeService implements ISettingTypeService {
	
	@Autowired
	private ISettingTypeRepository settingTypeRepository;
	
	@Override
	public List<SettingType> findAll(){
		QSettingType qSettingType = QSettingType.settingType;
		BooleanExpression expression = qSettingType.deactive.eq(false);
		return (List<SettingType>) settingTypeRepository.findAll(expression);
	}

	@Override
	public Page<SettingType> findAll(Integer limit, Integer offset, String sort,
			String order, String search){
		
		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null){
			sort = "id";
		}
		
		return settingTypeRepository.findAll(new PageRequest(page, limit,(order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),sort));
	}

	@Override
	public void save(SettingType settingType)
	{
		settingTypeRepository.save(settingType);
		
	}

	@Override
	public void delete(int id) 
	{
		SettingType settingType = settingTypeRepository.findOne(id);
		settingType.setDeactive(true);
		settingType.setDeactiveDt(new java.util.Date());
		settingTypeRepository.save(settingType);
		
	}

	@Override
	public Long count() 
	{
		return settingTypeRepository.count();
	}

	@Override
	public SettingType findOne(int id) 
	{
		return settingTypeRepository.findOne(id);
	}

	@Override
	public SettingType findByName(String name) 
	{
		return settingTypeRepository.findByName(name);
	}
	
	@Override
	public Map<Integer, String> getSettingTypeList() {
		
		Map<Integer, String> settingTypeMap = new LinkedHashMap<Integer, String>();
		Page<SettingType> settingTypeList = findActiveSettingTypeSortByName();

		for (SettingType settingType : settingTypeList) {
			settingTypeMap.put(settingType.getId(), settingType.getName());
		}

		return settingTypeMap;
	}

	@Override
	public List<SettingType> findActiveSettingType() {
		QSettingType qSettingType = QSettingType.settingType;
		BooleanExpression expression = qSettingType.deactive.eq(false);

		List<SettingType> settingTypeList = (List<SettingType>) settingTypeRepository.findAll(expression);

		return settingTypeList;
	}

	@Override
	public Page<SettingType> findActiveSettingTypeSortByName() {
		QSettingType qSettingType = QSettingType.settingType;
		BooleanExpression expression = qSettingType.deactive.eq(false);

		Page<SettingType> settingTypeList = settingTypeRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return settingTypeList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {


		QSettingType qSettingType = QSettingType.settingType;
		BooleanExpression expression = qSettingType.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSettingType.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qSettingType.deactive.eq(false).and(
						qSettingType.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qSettingType.name.like(colValue + "%");
			}
		}

		return settingTypeRepository.count(expression);
	}

	@Override
	public Page<SettingType> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QSettingType qSettingType = QSettingType.settingType;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qSettingType.deactive.eq(false);
			} else {
				expression = qSettingType.deactive.eq(false).and(
						qSettingType.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qSettingType.name.like(name + "%");
			}
		}

		//int page = (offset == 0 ? 0 : (offset / limit));
		
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

		Page<SettingType> settingTypeList = (Page<SettingType>) settingTypeRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return settingTypeList;
	}
	


}

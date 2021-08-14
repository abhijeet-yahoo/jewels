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

import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QSetting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISettingRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class SettingService implements ISettingService {

	@Autowired
	private ISettingRepository settingRepository;

	@Override
	public List<Setting> findAll() {
		QSetting qSetting = QSetting.setting;
		BooleanExpression expression = qSetting.deactive.eq(false);
		return (List<Setting>) settingRepository.findAll(expression);
	}

	@Override
	public Page<Setting> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));
		if (sort == null) {
			sort = "id";
		}
		return settingRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Setting setting) {
		settingRepository.save(setting);
	}

	@Override
	public void delete(int id) {
		Setting setting = settingRepository.findOne(id);
		setting.setDeactive(true);
		setting.setDeactiveDt(new java.util.Date());
		settingRepository.save(setting);

	}

	@Override
	public Long count() {
		return settingRepository.count();
	}

	@Override
	public Setting findOne(int id) {
		return settingRepository.findOne(id);
	}

	@Override
	public Setting findByName(String name) {
		return settingRepository.findByName(name);
	}
	
	@Override
	public Map<Integer, String> getSettingList(Integer settingId) {
		Map<Integer, String> settingMap = new LinkedHashMap<Integer, String>();
		Page<Setting> settingList = findActiveSettingsSortByName(0);

		for (Setting setting : settingList) {
			settingMap.put(setting.getId(), setting.getName());
		}

		return settingMap;
	}

	@Override
	public List<Setting> findActiveSettings(Integer settingId) {
		QSetting qSetting = QSetting.setting;
		BooleanExpression expression = null;

		if (settingId == 0) {
			expression = qSetting.deactive.eq(false);
		} else {
			expression = qSetting.id.eq(settingId).and(qSetting.deactive.eq(false));
		}

		List<Setting> settingList = (List<Setting>) settingRepository.findAll(expression);

		return settingList;
	}

	@Override
	public Page<Setting> findActiveSettingsSortByName(Integer settingId) {
		QSetting qSetting = QSetting.setting;
		BooleanExpression expression = null;

		if (settingId == 0) {
			expression = qSetting.deactive.eq(false);
		} else {
			expression = qSetting.id.eq(settingId).and(qSetting.deactive.eq(false));
		}

		Page<Setting> settingList = settingRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return settingList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QSetting qSetting = QSetting.setting;
		BooleanExpression expression = qSetting.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSetting.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qSetting.deactive.eq(false).and(
						qSetting.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qSetting.name.like(colValue + "%");
			}
		}

		return settingRepository.count(expression);
	}

	@Override
	public Page<Setting> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive) {
		
		QSetting qSetting = QSetting.setting;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qSetting.deactive.eq(false);
			} else {
				expression = qSetting.deactive.eq(false).and(
						qSetting.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qSetting.name.like(name + "%");
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

		Page<Setting> settingList = (Page<Setting>) settingRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return settingList;
	}

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@Override
	public Page<Setting> findActiveSettingsSortByName() {
		
		QSetting qSetting = QSetting.setting;
		BooleanExpression expression = null;
		expression = qSetting.deactive.eq(false);

		Page<Setting> settingList = settingRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return settingList;
	}

	@Override
	public Map<Integer, String> getSettingList() {
		Map<Integer, String> settingMap = new LinkedHashMap<Integer, String>();
		Page<Setting> settingList = findActiveSettingsSortByName();

		for (Setting setting : settingList) {
			settingMap.put(setting.getId(), setting.getName());
		}

		return settingMap;
	}

}

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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QMetal;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IMetalRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MetalService implements IMetalService {

	@Autowired
	private IMetalRepository metalRepository;

	@Override
	public List<Metal> findAll() {
		return metalRepository.findAll();
	}

	@Override
	public Page<Metal> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		limit = (limit == null ? 10 : limit);
		offset = (offset == null ? 0 : offset);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return metalRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Metal metal) {
		metalRepository.save(metal);
	}

	@Override
	public void delete(int id) {
		Metal metal = metalRepository.findOne(id);
		metal.setDeactive(true);
		metal.setDeactiveDt(new java.util.Date());
		metalRepository.save(metal);

	}

	@Override
	public Long count() {
		return metalRepository.count();
	}

	@Override
	public Metal findOne(int id) {
		return metalRepository.findOne(id);
	}

	@Override
	public Metal findByName(String name) {
		return metalRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getMetalList() {
		Map<Integer, String> metalMap = new LinkedHashMap<Integer, String>();
		Page<Metal> metalList = findActiveMetalsSortByName();

		for (Metal metal : metalList) {
			metalMap.put(metal.getId(), metal.getName());
		}

		return metalMap;

	}

	@Override
	public List<Metal> findActiveMetals() {
		QMetal qMetal = QMetal.metal;
		BooleanExpression expression = qMetal.deactive.eq(false);

		List<Metal> metalList = (List<Metal>) metalRepository
				.findAll(expression);

		return metalList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QMetal qMetal = QMetal.metal;
		BooleanExpression expression = qMetal.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qMetal.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qMetal.deactive.eq(false).and(
						qMetal.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qMetal.name.like(colValue + "%");
			}
		}

		return metalRepository.count(expression);
	}

	@Override
	public Page<Metal> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive) {

		QMetal qMetal = QMetal.metal;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qMetal.deactive.eq(false);
			} else {
				expression = qMetal.deactive.eq(false).and(
						qMetal.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qMetal.name.like(name + "%");
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

		Page<Metal> metalList = (Page<Metal>) metalRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return metalList;
	}

	@Override
	public Page<Metal> findActiveMetalsSortByName() {

		QMetal qMetal = QMetal.metal;
		BooleanExpression expression = qMetal.deactive.eq(false);

		Page<Metal> metalList = metalRepository.findAll(expression,
				new PageRequest(0, 10000, Direction.ASC, "name"));

		return metalList;
	}
	
	@Override
	public Page<Metal> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QMetal qMetal = QMetal.metal;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qMetal.deactive.eq(false);
			} else {
				expression = qMetal.deactive.eq(false).and(qMetal.name.like("%" + search + "%"));
			}
		} else {
			if (search != null) {
				expression = qMetal.name.like("%" + search + "%");
			}
		}

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
		
		Page<Metal> metalMastList = (Page<Metal>) metalRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		return metalMastList;
	}
	
	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
	
		QMetal qMetal = QMetal.metal;
		BooleanExpression expression = null;
		
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qMetal.deactive.eq(false);
			} else {
				expression = qMetal.deactive.eq(false).and(qMetal.name.like("%" + colValue + "%"));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qMetal.name.like("%" + colValue + "%");
			}
		}

		return metalRepository.count(expression);

	}

}

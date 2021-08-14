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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QShape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IShapeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ShapeService implements IShapeService {

	@Autowired
	private IShapeRepository shapeRepository;

	@Override
	public List<Shape> findAll() {
		return shapeRepository.findAll();
	}

	@Override
	public Page<Shape> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return shapeRepository.findAll(new PageRequest(page, limit,
				(order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), sort));
	}

	@Override
	public void save(Shape shape) {
		shapeRepository.save(shape);
	}

	@Override
	public void delete(int id) {
		Shape shape = shapeRepository.findOne(id);
		shape.setDeactive(true);
		shape.setDeactiveDt(new java.util.Date());
		shapeRepository.save(shape);
	}

	@Override
	public Long count() {
		return shapeRepository.count();
	}

	@Override
	public Shape findOne(int id) {
		return shapeRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getShapeList() {
		Map<Integer, String> shapeMap = new LinkedHashMap<Integer, String>();
		Page<Shape> shapeList = findActiveShapesSortByName();

		for (Shape shape : shapeList) {
			shapeMap.put(shape.getId(), shape.getName());
		}

		return shapeMap;
	}
	
	
	@Override
	public Shape findByName(String name) {

		return shapeRepository.findByName(name);
	}

	@Override
	public List<Shape> findActiveShapes() {
	
		QShape qShape = QShape.shape;
		BooleanExpression expression = qShape.deactive.eq(false);

		List<Shape> shapeList = (List<Shape>) shapeRepository
				.findAll(expression);

		return shapeList;
	}

	@Override
	public Page<Shape> findActiveShapesSortByName() {
	
		QShape qShape = QShape.shape;
		BooleanExpression expression = qShape.deactive.eq(false);

		Page<Shape> shapeList = shapeRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return shapeList;
	}


	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QShape qShape = QShape.shape;
		BooleanExpression expression = qShape.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qShape.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qShape.deactive.eq(false).and(
						qShape.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qShape.name.like(colValue + "%");
			}
		}

		return shapeRepository.count(expression);
	}

	@Override
	public Page<Shape> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive) {
			
		QShape qShape = QShape.shape;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qShape.deactive.eq(false);
			} else {
				expression = qShape.deactive.eq(false).and(
						qShape.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qShape.name.like(name + "%");
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

		Page<Shape> shapeList = (Page<Shape>) shapeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return shapeList;
	}

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@Override
	public Page<Shape> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {
		
		QShape qShape = QShape.shape;
		BooleanExpression expression = null;

		
		
		if (onlyActive) {
			if (search == null) {
				expression = qShape.deactive.eq(false);
			}else{
				expression = qShape.deactive.eq(false).and(qShape.name.like("%" + search + "%").or(qShape.code.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qShape.name.like("%" + search + "%").or(qShape.code.like("%" + search + "%"));
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
		
		Page<Shape> shapeList = (Page<Shape>) shapeRepository.findAll(expression,new PageRequest
				(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return shapeList;
	}


}

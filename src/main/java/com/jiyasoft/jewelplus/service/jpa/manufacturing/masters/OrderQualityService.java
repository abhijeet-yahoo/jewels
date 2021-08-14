package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderQuality;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignStoneRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderQualityRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderQualityService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class OrderQualityService implements IOrderQualityService {

	@Autowired
	private IOrderQualityRepository orderQualityRepository;

	@Autowired
	private IDesignStoneRepository designStoneRepository;
	
	@Override
	public List<OrderQuality> findAll() {
		return orderQualityRepository.findAll();
	}

	@Override
	public Page<OrderQuality> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		QOrderQuality qOrderQuality  = QOrderQuality.orderQuality;
		BooleanExpression expression = qOrderQuality.deactive.eq(false);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return orderQualityRepository.findAll(expression, new PageRequest(page, limit, Direction.DESC, sort));
	}

	@Override
	public void save(OrderQuality orderQuality) {
		orderQualityRepository.save(orderQuality);
	}

	@Override
	public void delete(int id) {
		OrderQuality orderQuality = orderQualityRepository.findOne(id);
		orderQuality.setDeactive(true);
		orderQuality.setDeactiveDt(new java.util.Date());
		orderQualityRepository.save(orderQuality);
	}

	@Override
	public Long count() {
		return orderQualityRepository.count();
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QOrderQuality qOrderQuality  = QOrderQuality.orderQuality;
		BooleanExpression expression = qOrderQuality.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qOrderQuality.deactive.eq(false);
			} else if (colName == null && colValue != null) {
				expression = qOrderQuality.deactive.eq(false).and(
						qOrderQuality.orderMt.id.eq(Integer.parseInt(colValue)));
			} else if (colName.equalsIgnoreCase("styleId") && colValue != null) {
				expression = qOrderQuality.deactive.eq(false).and(
						qOrderQuality.orderMt.id.eq(Integer.parseInt(colValue)));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("styleId")) && colValue != null) {
				expression = qOrderQuality.orderMt.id.eq(Integer.parseInt(colValue));
			} else if ((colName != null || colName.equalsIgnoreCase("styleId")) && colValue != null) {
				expression = qOrderQuality.orderMt.id.eq(Integer.parseInt(colValue));
			} /* else if (colName != null && colName.equalsIgnoreCase("style") && colValue != null) {
				expression = qInventory.style.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("category") && colValue != null) {
				expression = qInventory.category.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("subCategory")
					&& colValue != null) {
				expression = qInventory.subCategory.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("metal") && colValue != null) {
				expression = qInventory.metal.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("purity") && colValue != null) {
				expression = qInventory.purity.name.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("pieces") && colValue != null) {
				Integer tmpPieces = 0;

				try {
					tmpPieces = Integer.parseInt(colValue);
				} catch (NumberFormatException ex) {
					tmpPieces = 0;
				}

				expression = qInventory.pieces.eq(tmpPieces);
			} else if (colName != null && colValue == null) {
				return inventoryRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return inventoryRepository.count();
			}
*/		}

		return orderQualityRepository.count(expression);
	}

	@Override
	public OrderQuality findOne(int id) {
		return orderQualityRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getOrderQualityList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderQuality> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive) {
		
		return orderQualityRepository.findByOrderMtAndDeactive(orderMt, deactive);

	}

	@Override
	public Page<OrderQuality> findByOrderMt(OrderMt orderMt, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return orderQualityRepository.findByOrderMt(orderMt, new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

}

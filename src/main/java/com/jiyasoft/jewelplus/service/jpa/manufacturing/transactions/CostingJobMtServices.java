package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

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




import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingJobMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostingJobMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobMtService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class CostingJobMtServices implements ICostingJobMtService{
	
	
	@Autowired
	private ICostingJobMtRepository costingJobMtRepository;

	@Override
	public List<CostingJobMt> findAll() {
		return costingJobMtRepository.findAll();
	}

	@Override
	public Page<CostingJobMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return costingJobMtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(CostingJobMt costingJobMt) {
		costingJobMtRepository.save(costingJobMt);
		
	}

	@Override
	public void delete(int id) {
		CostingJobMt costingJobMt = costingJobMtRepository.findOne(id);
		costingJobMt.setDeactive(true);
		costingJobMt.setDeactiveDt(new java.util.Date());
		costingJobMtRepository.save(costingJobMt);
	}

	@Override
	public Long count() {
		return costingJobMtRepository.count();
	}

	@Override
	public CostingJobMt findOne(int id) {
		return costingJobMtRepository.findOne(id);
	}

	

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QCostingJobMt qCostingJobMt = QCostingJobMt.costingJobMt;
		BooleanExpression expression = qCostingJobMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qCostingJobMt.deactive.eq(false);
			} else if (colValue != null) {
				expression = qCostingJobMt.deactive.eq(false).and(
						qCostingJobMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invNo"))
					&& colValue != null) {

				expression = qCostingJobMt.invNo.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return costingJobMtRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return costingJobMtRepository.count();
			}
		}

		return costingJobMtRepository.count(expression);
		
	}

	@Override
	public Page<CostingJobMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String invNo, Boolean onlyActive) {
	
		QCostingJobMt qCostingJobMt = QCostingJobMt.costingJobMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (invNo == null) {
				expression = qCostingJobMt.deactive.eq(false);
			} else {
				expression = qCostingJobMt.deactive.eq(false).and(
						qCostingJobMt.invNo.like(invNo + "%"));
			}
		} else {
			if (invNo != null) {
				expression = qCostingJobMt.invNo.like(invNo + "%");
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

		Page<CostingJobMt> costingJobMtList = (Page<CostingJobMt>) costingJobMtRepository.findAll(expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return costingJobMtList;
	}

	@Override
	public CostingJobMt findByUniqueId(Long uniqueId) {
		return costingJobMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public CostingJobMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return costingJobMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}
	
	
	@Override
	public Page<CostingJobMt> getInvNoAutoFill(Integer limit, Integer offset,
			String sort, String order, String invNo, Boolean onlyActive) {
		
		QCostingJobMt qCostingJobMt = QCostingJobMt.costingJobMt;
		BooleanExpression expression = qCostingJobMt.deactive.eq(false);

		if (onlyActive) {
			if (invNo == null) {
				expression = qCostingJobMt.deactive.eq(false);
			} else {
				expression = qCostingJobMt.deactive.eq(false).and(qCostingJobMt.invNo.like(invNo + "%"));
			}
		} else {
			if (invNo != null) {
				expression = qCostingJobMt.invNo.like(invNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<CostingJobMt> costMtList = (Page<CostingJobMt>) costingJobMtRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
		
		return costMtList;
		
		
	}
	
	
	
	@Override
	public Map<Integer, String> getCostingList() {
		Map<Integer,String> costMap = new LinkedHashMap<Integer, String>();
		Page<CostingJobMt> costMtList = findActiveCostingSortByInvNo();
		for(CostingJobMt cost:costMtList){
			costMap.put(cost.getId(), cost.getInvNo());
		}
		return costMap;
	}
	
	
	@Override
	public Page<CostingJobMt> findActiveCostingSortByInvNo() {
		QCostingJobMt qCostingJobMt = QCostingJobMt.costingJobMt;
		BooleanExpression expression = qCostingJobMt.deactive.eq(false).and(qCostingJobMt.expClose.eq(false));
		Page<CostingJobMt> costingList = costingJobMtRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "invNo"));
		
		return costingList;
	}
	
	

	
	
}

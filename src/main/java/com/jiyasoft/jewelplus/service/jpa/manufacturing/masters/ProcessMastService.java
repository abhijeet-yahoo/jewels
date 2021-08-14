package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProcessMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QProcessMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IProcessMastRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProcessMastService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ProcessMastService implements IProcessMastService {
	
	@Autowired
	private IProcessMastRepository processMastRepository;

	@Override
	public List<ProcessMast> findAll() {
		// TODO Auto-generated method stub
		return processMastRepository.findAll();
	}

	@Override
	public Page<ProcessMast> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		limit = (limit == null ? 10 : limit);
		offset = (offset == null ? 0 : offset);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return processMastRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(ProcessMast processMast) {
		processMastRepository.save(processMast);
		
	}

	@Override
	public void delete(int id) {
		ProcessMast processMast = processMastRepository.findOne(id);
		processMast.setDeactive(true);
		processMast.setDeactiveDt(new java.util.Date());
		processMastRepository.save(processMast);
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return processMastRepository.count();
	}

	@Override
	public ProcessMast findOne(int id) {
		// TODO Auto-generated method stub
		return processMastRepository.findOne(id);
	}

	@Override
	public List<ProcessMast> findActiveProcessMasts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QProcessMast qProcessMast = QProcessMast.processMast;
		BooleanExpression expression = qProcessMast.deactive.eq(false);

		if (onlyActive) {
			if ( colValue == null) {
				expression = qProcessMast.deactive.eq(false);
			} else if (colValue != null) {
				expression = qProcessMast.deactive.eq(false).and(
						qProcessMast.processLookUp.fieldValue.like(colValue + "%"));
			}
		} else {
			if ( colValue != null) {
				expression = qProcessMast.processLookUp.fieldValue.like(colValue + "%");
			}
		}

		return processMastRepository.count(expression);
	}

	@Override
	public Page<ProcessMast> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		QProcessMast qProcessMast = QProcessMast.processMast;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qProcessMast.deactive.eq(false);
			} else {
				expression = qProcessMast.deactive.eq(false).and(
						qProcessMast.processLookUp.fieldValue.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qProcessMast.processLookUp.fieldValue.like(name + "%");
			}
		}

		// new addition
		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}
		
		// new addition

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<ProcessMast> processMastList = (Page<ProcessMast>) processMastRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return processMastList;
	}

	@Override
	public Long countAll(String colValue) {
		QProcessMast qProcessMast = QProcessMast.processMast;
		BooleanExpression expression = qProcessMast.deactive.eq(false);
		
			if(colValue!=null && colValue !="" ){
					
				expression = qProcessMast.deactive.eq(false).and(qProcessMast.processLookUp.fieldValue.like(colValue + "%"));
			}
		
			
		 return processMastRepository.count(expression);
	}

	@Override
	public Page<ProcessMast> searchAll(Integer limit, Integer offset,
			String sort, String order, String name) {
		QProcessMast qProcessMast = QProcessMast.processMast;
		BooleanExpression expression = qProcessMast.deactive.eq(false);

		if(name !=null && name !=""){
		expression =qProcessMast.deactive.eq(false).and(qProcessMast.processLookUp.fieldValue.like(name + "%"));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<ProcessMast> processMastList = (Page<ProcessMast>) processMastRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
		
		
		return processMastList;
	}
	
	

}

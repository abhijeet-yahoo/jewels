package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QDustMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IDustMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class DustMtService implements IDustMtService {
	
	@Autowired
	private IDustMtRepository dustMtRepository;
	
	@Autowired
	private EntityManager entityManager;

	
	@Override
	public DustMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return dustMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public DustMt findOne(Integer id) {
		return dustMtRepository.findOne(id);
	}

	@Override
	public List<DustMt> findAll() {
		return dustMtRepository.findAll();
	}
	
	@Override
	public Page<DustMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		QDustMt qDustMt = QDustMt.dustMt;
		BooleanExpression expression = qDustMt.deactive.eq(false);
		
		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10000;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return dustMtRepository.findAll(expression, new PageRequest(page, limit, Direction.DESC, sort));
	}

	@Override
	public void delete(Integer id) {
		DustMt dustMt = dustMtRepository.findOne(id);
		dustMt.setDeactive(true);
		dustMt.setDeactiveDt(new java.util.Date());
		dustMtRepository.save(dustMt);
		
	}
	
	@Override
	public void save(DustMt dustMt) {
		dustMtRepository.save(dustMt);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QDustMt qDustMt = QDustMt.dustMt;
		BooleanExpression expression = qDustMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDustMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qDustMt.deactive.eq(false).and(
						qDustMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qDustMt.invNo.like(colValue + "%");
			}
		}

		return dustMtRepository.count(expression);
		
	}

	@Override
	public Page<DustMt> findByInvNo(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive) {
		
		QDustMt qDustMt = QDustMt.dustMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qDustMt.deactive.eq(false);
			} else {
				expression = qDustMt.deactive.eq(false).and(
						qDustMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qDustMt.invNo.like(name + "%");
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

		Page<DustMt> dustMtList = (Page<DustMt>) dustMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));

		return dustMtList;
		
	}

	
	@Override
	public Integer maxSrNo() {

		QDustMt qDustMt = QDustMt.dustMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
				.from(qDustMt)
				.where(qDustMt.deactive.eq(false).and(qDustMt.createdDate.year().eq(date.get(Calendar.YEAR)))).list(qDustMt.srNo.max());

		for (Integer srNo : maxSrno) {
			retVal = (srNo == null ? 0 : srNo);
		}

		return retVal;
	}

	@Override
	public DustMt findByUniqueId(Long uniqueId) {
		return dustMtRepository.findByUniqueId(uniqueId);
	}
	
	
	@Override
	public DustMt findByFromPeriodAndToPeriodAndDeactive(Date fromPeriod,
			Date toPeriod, Boolean deactive) {
		return dustMtRepository.findByFromPeriodAndToPeriodAndDeactive(fromPeriod, toPeriod, deactive);
	}
	
	
	
	
}

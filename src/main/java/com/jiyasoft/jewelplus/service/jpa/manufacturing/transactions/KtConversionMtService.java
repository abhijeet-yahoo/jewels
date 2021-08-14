package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Calendar;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QKtConversionMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IKtConversionMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IKtConversionMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class KtConversionMtService implements IKtConversionMtService{
	
	
	@Autowired
	private IKtConversionMtRepository ktConversionMtRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<KtConversionMt> findAll() {
		return ktConversionMtRepository.findAll();
	}

	@Override
	public Page<KtConversionMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
 
			int page = (offset == 0 ? 0 : (offset / limit));

			if (sort == null) {
				sort = "id";
			}

			return ktConversionMtRepository
					.findAll(new PageRequest(page, limit, (order
							.equalsIgnoreCase("asc") ? Direction.ASC
							: Direction.DESC), sort));
	}

	@Override
	public void save(KtConversionMt ktConversionMt) {
		ktConversionMtRepository.save(ktConversionMt);
		
	}

	@Override
	public void delete(int id) {
		KtConversionMt ktConversionMt  = ktConversionMtRepository.findOne(id);
		ktConversionMt.setDeactive(true);
		ktConversionMt.setDeactiveDt(new java.util.Date());
		ktConversionMtRepository.save(ktConversionMt);
		
	}

	@Override
	public Long count() {
		return ktConversionMtRepository.count();
	}

	@Override
	public KtConversionMt findOne(int id) {
		return ktConversionMtRepository.findOne(id);
	}

	@Override
	public KtConversionMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return ktConversionMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QKtConversionMt qKtConversionMt = QKtConversionMt.ktConversionMt;
		BooleanExpression expression = qKtConversionMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qKtConversionMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qKtConversionMt.deactive.eq(false).and(
						qKtConversionMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qKtConversionMt.invNo.like(colValue + "%");
			}
		}

		return ktConversionMtRepository.count(expression);
	}

	@Override
	public Page<KtConversionMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QKtConversionMt qKtConversionMt = QKtConversionMt.ktConversionMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qKtConversionMt.deactive.eq(false);
			} else {
				expression = qKtConversionMt.deactive.eq(false).and(
						qKtConversionMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qKtConversionMt.invNo.like(name + "%");
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

		Page<KtConversionMt> ktConversionMtList = (Page<KtConversionMt>) ktConversionMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));

		return ktConversionMtList;
	}

	@Override
	public KtConversionMt findByUniqueId(Long uniqueId) {
		return ktConversionMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Integer maxSrNo() {
		QKtConversionMt qKtConversionMt = QKtConversionMt.ktConversionMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
				.from(qKtConversionMt)
				.where(qKtConversionMt.deactive.eq(false).and(qKtConversionMt.createdDt.year().eq(date.get(Calendar.YEAR)))).list(qKtConversionMt.srNo.max());

		for (Integer srNo : maxSrno) {
			retVal = (srNo == null ? 0 : srNo);
		}

		return retVal;
		
		
	//	return null;
		
	}

	

}

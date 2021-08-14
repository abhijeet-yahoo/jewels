package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QPDC;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IPDCRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class PDCService implements IPDCService {

	@Autowired
	private IPDCRepository pdcRepository;

	@Override
	public List<PDC> findAll() {
		return pdcRepository.findAll();
	}

	@Override
	public void save(PDC pdc) {
		pdcRepository.save(pdc);
	}

	@Override
	public PDC findOne(Integer id) {
		return pdcRepository.findOne(id);
	}

	@Override
	public Page<PDC> findByStyleNo(Integer limit, Integer offset, String sort,
			String order, String styleNo, Boolean onlyActive) {
		QPDC qPdc = QPDC.pDC;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (styleNo == null) {
				expression = qPdc.deactive.eq(false);
			} else {
				expression = qPdc.deactive.eq(false).and(
						qPdc.design.styleNo.like(styleNo.trim() + "%"));
			}
		} else {
			if (styleNo != null) {
				expression = qPdc.design.styleNo.like(styleNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<PDC> pdcList = (Page<PDC>) pdcRepository.findAll(expression,
				new PageRequest(page, limit, Direction.DESC, sort));
		return pdcList;
	}

	@Override
	public Page<PDC> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		QPDC qPdc = QPDC.pDC;
		BooleanExpression expression = qPdc.deactive.eq(false);

		int page = ((offset == null || offset == 0) ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10;
		}

		if (order == null) {
			order = "asc";
		}

		if (sort == null) {
			sort = "id";
		}

		return pdcRepository.findAll(expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QPDC qPdc = QPDC.pDC;
		BooleanExpression expression = qPdc.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qPdc.deactive.eq(false);

			} else{
				expression = qPdc.deactive.eq(false).and(
						qPdc.design.styleNo.like(colValue + "%"));
			}
		} else {
			if (colName != null && colName.equalsIgnoreCase("styleNo")
					&& colValue != null) {
				expression = qPdc.design.styleNo.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return pdcRepository.count(expression);
			} else {
				return pdcRepository.count(expression);
			}
		}

		return pdcRepository.count(expression);
	}

	@Override
	public void delete(Integer id) {
		PDC pdc = pdcRepository.findOne(id);
		pdc.setDeactive(true);
		pdc.setDeactiveDt(new java.util.Date());
		pdcRepository.save(pdc);
	}

	@Override
	public PDC findByDesignAndDeactive(Design design,Boolean deactive) {
		return pdcRepository.findByDesignAndDeactive(design, deactive);
	}

	@Override
	public List<PDC> findByCurrentStkAndDeactive(Boolean currStk,
			Boolean deactive) {
		return pdcRepository.findByCurrentStkAndDeactive(currStk, deactive);
	}

}

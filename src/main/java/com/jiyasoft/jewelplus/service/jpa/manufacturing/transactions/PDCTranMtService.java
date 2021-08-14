package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QPDCTranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IPDCRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IPDCTranMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCTranMtService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class PDCTranMtService implements IPDCTranMtService{

	
	@Autowired
	private IPDCTranMtRepository PDCTranMtRepository;
	
	@Autowired
	private IPDCRepository PDCRepository;

	@Override
	public List<PDCTranMt> findAll() {
		return PDCTranMtRepository.findAll();
	}

	@Override
	public Page<PDCTranMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
	QPDCTranMt qPDCTranMt=QPDCTranMt.pDCTranMt;
		BooleanExpression expression = qPDCTranMt.deactive.eq(false).and(
				qPDCTranMt.currStk.eq(true).and(
						qPDCTranMt.department.name.toLowerCase().eq("diamond")));

		int page = ((offset == null || offset == 0) ? 0 : (offset / limit));

		if (limit == null) {
			limit = 1000;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return PDCTranMtRepository.findAll(expression, new PageRequest(page,
				limit, Direction.DESC, sort));
	

	}

	@Override
	public void save(PDCTranMt PDCTranMt) {
		PDCTranMtRepository.save(PDCTranMt);
	}

	@Override
	public void delete(int id) {
		PDCTranMt PDCTranMt=PDCTranMtRepository.findOne(id);
		PDCTranMtRepository.delete(PDCTranMt);
	}

	@Override
	public Long count() {
		return PDCTranMtRepository.count();
	}

	@Override
	public PDCTranMt findOne(int id) {
		return PDCTranMtRepository.findOne(id);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QPDCTranMt qPDCTranMt=QPDCTranMt.pDCTranMt;
		BooleanExpression expression = qPDCTranMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qPDCTranMt.deactive.eq(false);
			}
		} else {
			System.out.println("in complete method");

		}

		return PDCTranMtRepository.count(expression);
	}

	@Override
	public List<PDCTranMt> findBycurrentStk(Boolean currStk) {

		
		return PDCTranMtRepository.findByCurrStk(currStk);
	}
	
	@Override
	public void save(PDCTranMt PDCTranMt, PDC pdc) {
		PDCTranMtRepository.save(PDCTranMt);
		PDCRepository.save(pdc);
	}

	@Override
	public PDCTranMt findByDesignAndDepartmentAndCurrStk(Design design,
			Department department, Boolean currStk) {
		
		return PDCTranMtRepository.findByDesignAndDepartmentAndCurrStk(design, department, currStk);
	}

	@Override
	public PDCTranMt findByDesignAndCurrStk(Design design, Boolean currStk) {
		return PDCTranMtRepository.findByDesignAndCurrStk(design, currStk);
	}

	

}

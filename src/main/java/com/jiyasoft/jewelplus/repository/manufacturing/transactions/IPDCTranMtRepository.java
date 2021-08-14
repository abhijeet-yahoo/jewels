package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;

public interface IPDCTranMtRepository extends
		JpaRepository<PDCTranMt, Integer>, QueryDslPredicateExecutor<PDCTranMt> {

	List<PDCTranMt> findByCurrStk(Boolean currStk);

	PDCTranMt findByDesignAndDepartmentAndCurrStk(Design design,
			Department department, Boolean currStk);

	PDCTranMt findByDesignAndCurrStk(Design design, Boolean currStk);

}

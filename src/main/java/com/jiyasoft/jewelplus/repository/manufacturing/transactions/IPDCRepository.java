package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;

public interface IPDCRepository extends JpaRepository<PDC, Integer>,
		QueryDslPredicateExecutor<PDC> {

	List<PDC> findByCurrentStkAndDeactive(Boolean currStk,Boolean deactive);

	PDC findByDesignAndDeactive(Design design,Boolean deactive);
}

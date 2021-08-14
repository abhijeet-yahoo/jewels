package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LossBook;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface ILossBookRepository extends JpaRepository<LossBook, Integer>,
QueryDslPredicateExecutor<LossBook>{

	
	List<LossBook> findByDepartmentAndBagMtAndDeactive(Department department,BagMt bagMt,Boolean deactive);
	
	List<LossBook> findByTranMtAndDeactive(TranMt tranMt,Boolean deactive);
	
}

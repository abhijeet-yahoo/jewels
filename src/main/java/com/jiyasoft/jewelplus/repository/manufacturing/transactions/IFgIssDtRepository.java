package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssMt;


public interface IFgIssDtRepository extends
		JpaRepository<FgIssDt, Integer>,
		QueryDslPredicateExecutor<FgIssDt> {

	List<FgIssDt> findByFgIssMt(FgIssMt fgIssMt);

	Page<FgIssDt> findByFgIssMt(FgIssMt fgIssMt,Pageable pageable);



	List<FgIssDt> findByFgIssMtAndDeactiveOrderByIdDesc(FgIssMt fgIssMt, Boolean deactive);

		
	
	
	
	

}

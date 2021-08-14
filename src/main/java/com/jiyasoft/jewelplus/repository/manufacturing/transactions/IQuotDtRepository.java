package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotDtRepository extends JpaRepository<QuotDt, Integer>,
QueryDslPredicateExecutor<QuotDt>{
	
	QuotDt findByUniqueId(Long uniqueId);
	
	List<QuotDt> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);

	QuotDt findByQuotMtAndDesignAndKtDescAndQltyDescAndDeactive(QuotMt quotMt,Design design,String ktDesc,String qltyDesc, Boolean deactive);

}

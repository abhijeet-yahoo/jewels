package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssStnDt;

public interface IVouIssStnDtRepository extends JpaRepository<VouIssStnDt, Integer>, QueryDslPredicateExecutor<VouIssStnDt>{

	List<VouIssStnDt> findByVouIssMtAndDeactive(VouIssMt vouIssMt,Boolean deactive);

	List<VouIssStnDt> findByVouIssDtAndDeactive (VouIssDt vouIssDt, Boolean deactive);
}

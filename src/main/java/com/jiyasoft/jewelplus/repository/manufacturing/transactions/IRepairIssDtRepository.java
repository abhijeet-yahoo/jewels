package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssMt;

public interface IRepairIssDtRepository extends JpaRepository<RepairIssDt, Integer>,QueryDslPredicateExecutor<RepairIssDt> {

	List<RepairIssDt> findByRepairIssMt(RepairIssMt repairIssMt);

	Page<RepairIssDt> findByRepairIssMt(RepairIssMt repairIssMt, Pageable pageable);

	List<RepairIssDt> findByRepairIssMtAndDeactiveOrderByIdDesc(RepairIssMt repairIssMt, Boolean deactive);
}

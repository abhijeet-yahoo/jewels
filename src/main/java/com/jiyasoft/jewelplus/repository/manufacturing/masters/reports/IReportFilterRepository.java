package com.jiyasoft.jewelplus.repository.manufacturing.masters.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;

public interface IReportFilterRepository extends JpaRepository<ReportFilter, Integer>, QueryDslPredicateExecutor<ReportFilter> {

	ReportFilter findByName(String name);

}
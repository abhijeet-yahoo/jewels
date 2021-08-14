package com.jiyasoft.jewelplus.repository.manufacturing.masters.reports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFormat;

public interface IReportFormatRepository extends JpaRepository<ReportFormat, Integer>, QueryDslPredicateExecutor<ReportFormat> {

	List<ReportFormat> findByFilterForm(String reportNm);
	
	
}

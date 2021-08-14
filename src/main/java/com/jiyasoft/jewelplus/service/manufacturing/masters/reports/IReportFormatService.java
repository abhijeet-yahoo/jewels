package com.jiyasoft.jewelplus.service.manufacturing.masters.reports;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFormat;

public interface IReportFormatService {

	public ReportFormat findByReportHeading(String reportHeading);
	
}

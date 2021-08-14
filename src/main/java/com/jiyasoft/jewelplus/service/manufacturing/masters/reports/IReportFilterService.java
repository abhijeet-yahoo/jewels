package com.jiyasoft.jewelplus.service.manufacturing.masters.reports;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFormat;

public interface IReportFilterService {

	public List<ReportFilter> findAll();

	public Page<ReportFilter> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(ReportFilter reportFilter);

	public Long count();
	
	public ReportFilter findOne(int id);

	public ReportFilter findByName(String reportFilterName);
	
	public List<String> popLevelOneList(String dbGroupVal);
	
	public List<String> popLevelTwoList(String dbGroupVal);
	
	public List<String> popLevelThreeList(String dbGroupVal);
	
	public String onChangeLevelOne(String group);
	
	public String onChangeLevelTwo(String group);
	
	public String onChangeLevelThree(String group);

	public String getWipSummary(String partyCond,String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String fromDispatchDate,String toDispatchDate,String departmentCond,String pWipFormat,Principal principal,String customerTypeCond,String regionCond,String divisionCond);
	
	public String getWipDetail(Integer sordMtid);
	
	public List<ReportFormat> findByFilterForm(String reportNm);
	
	public Map<String, String> getFilterFormList(String reportNm);
	
	public String getWipStatus(String partyCond,String orderTypeCond,String orderCond,String departmentCond,String purityCond,String colorCond,
			Integer delayCond,String styleCond,String fromOrderDate,String toOrderDate, String fProdToDate,String fProdFromDate,String priorityCond) throws ParseException;
	
	
	public String getOrderStatus(String partyCond,String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String fromDispatchDate,String toDispatchDate,String departmentCond) throws ParseException;
	
	public String getWipCasting(String partyCond,String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String fromDispatchDate,String toDispatchDate,String departmentCond,String pWipFormat);
}

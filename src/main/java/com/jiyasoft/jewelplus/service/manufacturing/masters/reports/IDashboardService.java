package com.jiyasoft.jewelplus.service.manufacturing.masters.reports;

import java.text.ParseException;

public interface IDashboardService {
	
	
	public String getSalesOrderData(String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String divisionCond,String regionCond,
			String customerTypeCond,String groupByCond) throws ParseException;
	
	public String salesOrderList(String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String divisionCond,String regionCond,
			String customerTypeCond,String groupByCond,String pClientIds) throws ParseException;
	
	public String getSaleInvoiceData(String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String divisionCond,String regionCond,
			String customerTypeCond,String groupByCond) throws ParseException;

	public String salesInvoiceList(String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String regionCond,
			String customerTypeCond,String groupByCond,String pClientIds) throws ParseException;
	
	
	public String diamondStockList(String toOrderDate,String pTranStatus) throws ParseException;
	
	public String wipStockList(String toOrderDate) throws ParseException;
	
	public String finishGoodsStockList(String toOrderDate) throws ParseException;
	
	public String getSalesQuotationData();
	
	public String getFgIssueData(String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String regionCond,
			String customerTypeCond,String groupByCond,String pClientIds);
	
	
	public String getFgIssueListing(String orderTypeCond,String orderCond,String fromOrderDate,String toOrderDate,String regionCond,
			String customerTypeCond,String groupByCond,String pClientIds) throws ParseException;
	
}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters.reports;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IDashboardService;

@Service
@Repository
@Transactional
public class DashboardService  implements IDashboardService{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public String getSalesOrderData(String orderTypeCond, String orderCond, String fromOrderDate, String toOrderDate,
			String divisionCond, String regionCond, String customerTypeCond, String groupByCond) throws ParseException {
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		Date date = new Date();
		Date previousDate = new Date(date.getTime() - (1000 * 60 * 60 * 24));
		String backDate = dfOutput.format(previousDate);
		
		if(orderTypeCond == null) {
			orderTypeCond =" "; 
		}
		
		if(orderCond == null) {
			orderCond =" "; 
		}
		
		if(fromOrderDate == null) {
			fromOrderDate =  backDate;
		}
		
		if(toOrderDate == null) {
			toOrderDate = backDate;
		}
		
		if(divisionCond == null) {
			divisionCond =" "; 
		}
		
		if(regionCond == null) {
			regionCond =" "; 
		}
		
		if(customerTypeCond == null) {
			customerTypeCond =" "; 
		}
		
		if(groupByCond == null) {
			groupByCond =" 'invDate' "; 
		}
		
		
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_Orders(?,?,?,?,?,?,?,?) }");
		query.setParameter(1, orderCond);
		query.setParameter(2, divisionCond);
		query.setParameter(3, regionCond);
		query.setParameter(4, customerTypeCond);
		query.setParameter(5, orderTypeCond);
		query.setParameter(6, fromOrderDate);
		query.setParameter(7, toOrderDate);
		query.setParameter(8, groupByCond);
		
		
		
		List<Object[]> objects = query.getResultList();
		
		Double pcs = 0.0;
		Double grossWt = 0.0;
		Double orderValue = 0.0;
		
		for(Object[] list:objects){
			
			pcs +=list[2] == null ? 0.0 : Double.parseDouble(list[2].toString());
			grossWt += list[3] == null ? 0.0 : Double.parseDouble(list[3].toString());
			orderValue += list[4] == null ? 0.0 : Double.parseDouble(list[4].toString());
			
		
	}
		
		
		JSONObject jsonObject =  new JSONObject();
		jsonObject.put("availableStkId", myFormatter.format(pcs));
		jsonObject.put("grossWt", myFormatter.format(grossWt));
		jsonObject.put("orderValue", myFormatter.format(orderValue));

		return jsonObject.toString();
	
	
}

	@Override
	public String salesOrderList(String orderTypeCond, String orderCond, String fromOrderDate, String toOrderDate,
			String divisionCond, String regionCond, String customerTypeCond, String groupByCond,String clientCond) throws ParseException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		String fOrdToDate="";
		String fOrdFromDate="";
		

		Date ordFromDate = dfInput.parse(fromOrderDate);
		fOrdFromDate = dfOutput.format(ordFromDate);

		Date ordToDate = dfInput.parse(toOrderDate);
		fOrdToDate = dfOutput.format(ordToDate);
		
		if (clientCond.length() > 0) {
			clientCond = "  partyId in (" + clientCond + ")  ";
		}
		
		if (orderTypeCond.length() > 0) {
			orderTypeCond = "  OrdTypeId in (" + orderTypeCond + ")  ";
		}

		if (orderCond.length() > 0) {
			orderCond = "  partyId in (" + clientCond + ") ";
		}
		
		if (divisionCond.length() > 0) {
			divisionCond = "  OrdDivisionId in (" + divisionCond + ")  ";
		}
		
		if (regionCond.length() > 0) {
				regionCond = "  PartyRegionId in (" + regionCond + ")  ";
		}
		
		if (customerTypeCond.length() > 0) {
			customerTypeCond = "  CustomerTypeId in (" + customerTypeCond + ")  ";
		}
		
		if(groupByCond.isEmpty()) {
			groupByCond ="invDate"; 
		}
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_Orders(?,?,?,?,?,?,?,?) }");
		query.setParameter(1, orderCond);
		query.setParameter(2, divisionCond);
		query.setParameter(3, regionCond);
		query.setParameter(4, customerTypeCond);
		query.setParameter(5, orderTypeCond);
		query.setParameter(6, fOrdFromDate);
		query.setParameter(7, fOrdToDate);
		query.setParameter(8, groupByCond);
		
		
		
		List<Object[]> objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		 for(Object[] list:objects){
			 
			 Double pcs = (double) Math.round(Double.parseDouble(list[2].toString()));
			 Double grossWt = (double) Math.round((Double.parseDouble(list[3].toString())));
			 Double ordVal = (double) Math.round((Double.parseDouble(list[4].toString())));
				
				sb.append("{\"invNo\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"invDate\":\"")
			     .append(list[1] != null ? list[1] : "")
			     .append("\",\"ordQty\":\"")
			     .append(list[2] != null ? myFormatter.format(pcs) : "")
			     .append("\",\"grossWt\":\"")
			     .append(list[3] != null ? myFormatter.format(grossWt) : "")
			     .append("\",\"ordValue\":\"")
			     .append(list[4] != null ? myFormatter.format(ordVal) : "")
			     .append("\",\"groupBy\":\"")
				 .append(list[11] != null ? list[11] : "")
			     .append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
		
		return str;
		
		
	}


	@Override
	public String getSaleInvoiceData(String orderTypeCond, String orderCond, String fromOrderDate, String toOrderDate,
			String divisionCond, String regionCond, String customerTypeCond, String groupByCond) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		Date date = new Date();
		
		Date previousDate = new Date(date.getTime() - (1000 * 60 * 60 * 24));
		
		String backDate = dfOutput.format(previousDate);
		
		
		
		if(orderTypeCond == null) {
			orderTypeCond =" "; 
		}
		
		if(orderCond == null) {
			orderCond =" "; 
		}
		
		if(fromOrderDate == null) {
			fromOrderDate =  backDate;
		}
		
		if(toOrderDate == null) {
			toOrderDate = backDate;
		}
		
		if(divisionCond == null) {
			divisionCond =" "; 
		}
		
		if(regionCond == null) {
			regionCond =" "; 
		}
		
		if(customerTypeCond == null) {
			customerTypeCond =" "; 
		}
		
		if(groupByCond == null) {
			groupByCond =" 'invDate' "; 
		}
		
		
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_Sales(?,?,?,?,?,?,?) }");
		query.setParameter(1, orderCond);
		query.setParameter(2, regionCond);
		query.setParameter(3, customerTypeCond);
		query.setParameter(4, orderTypeCond);
		query.setParameter(5, fromOrderDate);
		query.setParameter(6, toOrderDate);
		query.setParameter(7, groupByCond);
		
		
		
		List<Object[]> objects = query.getResultList();
		
		Double pcs = 0.0;
		Double grossWt = 0.0;
		Double orderValue = 0.0;
		
		for(Object[] list:objects){
			
			pcs += list[3] == null ? 0.0 :Double.parseDouble(list[3].toString());
			grossWt += list[4] == null ? 0.0 : Double.parseDouble(list[4].toString());
			orderValue += list[5] == null ? 0.0 : Double.parseDouble(list[5].toString());
			
		
	}
		
		
		JSONObject jsonObject =  new JSONObject();
		jsonObject.put("pcs", myFormatter.format(pcs));
		jsonObject.put("grossWt", myFormatter.format(grossWt));
		jsonObject.put("orderValue", myFormatter.format(orderValue));

		return jsonObject.toString();
	
	}

	@Override
	public String salesInvoiceList(String orderTypeCond, String orderCond, String fromOrderDate, String toOrderDate,
			String regionCond, String customerTypeCond, String groupByCond, String pClientIds) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		String fOrdToDate="";
		String fOrdFromDate="";
		
		

		Date ordFromDate = dfInput.parse(fromOrderDate);
		fOrdFromDate = dfOutput.format(ordFromDate);

		Date ordToDate = dfInput.parse(toOrderDate);
		fOrdToDate = dfOutput.format(ordToDate);
		
	
		if (orderTypeCond.length() > 0) {
			orderTypeCond = "  OrdTypeId in (" + orderTypeCond + ")  ";
		}

		if (orderCond.length() > 0) {
			orderCond = "  partyId in (" + pClientIds + ")  ";
		}
		
	
		if (regionCond.length() > 0) {
				regionCond = "  PartyRegionId in (" + regionCond + ")  ";
		}
		
		if (customerTypeCond.length() > 0) {
			customerTypeCond = "  CustomerTypeId in (" + customerTypeCond + ")  ";
		}
		
		if(groupByCond.isEmpty()) {
			groupByCond ="invDate"; 
		}
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_Sales(?,?,?,?,?,?,?) }");
		query.setParameter(1, orderCond);
		query.setParameter(2, regionCond);
		query.setParameter(3, customerTypeCond);
		query.setParameter(4, orderTypeCond);
		query.setParameter(5, fOrdFromDate);
		query.setParameter(6, fOrdToDate);
		query.setParameter(7, groupByCond);
		
		
		
		List<Object[]> objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		 for(Object[] list:objects){
			 
			 Double pcs = list[3] == null ? 0.0 : (double) Math.round(Double.parseDouble(list[3].toString()));
			 Double grossWt =list[4] == null ? 0.0 : (double) Math.round((Double.parseDouble(list[4].toString())));
			 Double ordVal =list[5] == null ? 0.0 : (double) Math.round((Double.parseDouble(list[5].toString())));
				
				sb.append("{\"invNo\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"invDate\":\"")
			     .append(list[1] != null ? list[1] : "")
			     .append("\",\"ordQty\":\"")
			     .append(list[2] != null ? myFormatter.format(pcs) : "")
			     .append("\",\"grossWt\":\"")
			     .append(list[3] != null ? myFormatter.format(grossWt) : "")
			     .append("\",\"ordValue\":\"")
			     .append(list[4] != null ? myFormatter.format(ordVal) : "")
			     .append("\",\"groupBy\":\"")
				 .append(list[11] != null ? list[11] : "")
			     .append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
		
		return str;
	}

	@Override
	public String diamondStockList(String toOrderDate, String pTranStatus) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		
		Date date = new Date();
		
		Date previousDate = new Date(date.getTime() - (1000 * 60 * 60 * 24));
		
		String backDate = dfOutput.format(previousDate);
		
		if(toOrderDate == null) {
			toOrderDate = backDate;
		}else {
			Date ordToDate = dfInput.parse(toOrderDate);
			toOrderDate = dfOutput.format(ordToDate);
		}
			
		if(pTranStatus == null || pTranStatus.isEmpty()) {
			pTranStatus =" "; 
		}
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_DiamondStk(?,?) }");
		query.setParameter(1, toOrderDate);
		query.setParameter(2, pTranStatus);
		
		List<Object[]> objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		
		for(Object[] list:objects){
			
			 Double carat = list[3] == null ? 0.0 :  Math.round((Double.parseDouble(list[3].toString()))*1000.0)/1000.0;
			 Double diaValue =list[4] == null ? 0.0 : Math.round((Double.parseDouble(list[4].toString()))*100.0)/100.0;
				
				sb.append("{\"deptNm\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"carat\":\"")
			     .append(myFormatter.format(carat))
			     .append("\",\"diaCost\":\"")
			     .append(myFormatter.format(diaValue))
			     .append("\",\"tranStatus\":\"")
			     .append(list[5] != null ? list[5] : "")
			     .append("\"},");
			
		
	}
		
		str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)	: str);
		str += "]}";
		
	
	return str;
	
	
	}

	@Override
	public String getSalesQuotationData() {
		// TODO Auto-generated method stub
		
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_Quatation() }");
		
		List<Object[]> objects = query.getResultList();
		
		Double pcs = 0.0;
		Double grossWt = 0.0;
		Double quotValue = 0.0;
		
		for(Object[] list:objects){
			
			pcs +=list[1] == null ? 0.0 : Double.parseDouble(list[1].toString());
			grossWt += list[0] == null ? 0.0 : Double.parseDouble(list[0].toString());
			quotValue += list[2] == null ? 0.0 : Double.parseDouble(list[2].toString());
			
		}
		
		
		JSONObject jsonObject =  new JSONObject();
		jsonObject.put("pcs", myFormatter.format(pcs));
		jsonObject.put("grossWt", myFormatter.format(grossWt));
		jsonObject.put("quotValue", myFormatter.format(quotValue));

		return jsonObject.toString();
	}

	@Override
	public String wipStockList(String toOrderDate) throws ParseException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		Date date = new Date();
		
		Date previousDate = new Date(date.getTime() - (1000 * 60 * 60 * 24));
		
		String backDate = dfOutput.format(previousDate);
		
		if(toOrderDate == null) {
			toOrderDate = backDate;
		}else {
			Date ordToDate = dfInput.parse(toOrderDate);
			toOrderDate = dfOutput.format(ordToDate);
		}
	
		
	
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_FactoryStock(?,?,?,?) }");
		query.setParameter(1, toOrderDate);
		query.setParameter(2, "");
		query.setParameter(3, "");
		query.setParameter(4, 1);
		
		List<Object[]> objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		
		for(Object[] list:objects){
			
			Double cost = list[8] == null ? 0.0 :  Double.parseDouble(list[8].toString());
			Double pcs = list[2] == null ? 0.0 :  Double.parseDouble(list[2].toString());
			Double grosswt = list[3] == null ? 0.0 :  Double.parseDouble(list[3].toString());
			Double diaWt = list[4] == null ? 0.0 :  Double.parseDouble(list[4].toString());
			Double colWt = list[5] == null ? 0.0 :  Double.parseDouble(list[5].toString());
			Double silWt = list[6] == null ? 0.0 :  Double.parseDouble(list[6].toString());
			Double pureWt = list[7] == null ? 0.0 :  Double.parseDouble(list[7].toString());
			
			sb.append("{\"status\":\"")
		     .append(list[1] != null ? list[1] : "")
		     .append("\",\"pcs\":\"")
		     .append(myFormatter.format(pcs))
		     .append("\",\"grossWt\":\"")
		     .append(myFormatter.format(grosswt))
		     .append("\",\"diaWt\":\"")
		     .append(myFormatter.format(diaWt))
		     .append("\",\"colWt\":\"")
		     .append(myFormatter.format(colWt))
		     .append("\",\"silWt\":\"")
		     .append(myFormatter.format(silWt))
		     .append("\",\"pureWt\":\"")
		     .append(myFormatter.format(pureWt))
		     .append("\",\"factoryCostt\":\"")
		     .append(myFormatter.format(cost))
		     .append("\"},");
		
		}
		
		str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)	: str);
		str += "]}";
		
	
	return str;
	}

	@Override
	public String finishGoodsStockList(String toOrderDate) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		Date date = new Date();
		Date previousDate = new Date(date.getTime() - (1000 * 60 * 60 * 24));
		String backDate = dfOutput.format(previousDate);
		
		if(toOrderDate == null) {
			toOrderDate = backDate;
		}else {
			Date ordToDate = dfInput.parse(toOrderDate);
			toOrderDate = dfOutput.format(ordToDate);
		}
	
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_FactoryStock(?,?,?,?) }");
		query.setParameter(1, toOrderDate);
		query.setParameter(2, "");
		query.setParameter(3, "");
		query.setParameter(4, 0);
		
		List<Object[]> objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		
		for(Object[] list:objects){
				
			Double cost = list[8] == null ? 0.0 :  Double.parseDouble(list[8].toString());
			Double pcs = list[2] == null ? 0.0 :  Double.parseDouble(list[2].toString());
			Double grosswt = list[3] == null ? 0.0 :  Double.parseDouble(list[3].toString());
			Double diaWt = list[4] == null ? 0.0 :  Double.parseDouble(list[4].toString());
			Double colWt = list[5] == null ? 0.0 :  Double.parseDouble(list[5].toString());
			Double silWt = list[6] == null ? 0.0 :  Double.parseDouble(list[6].toString());
			Double pureWt = list[7] == null ? 0.0 :  Double.parseDouble(list[7].toString());
			
			sb.append("{\"status\":\"")
		     .append(list[1] != null ? list[1] : "")
		     .append("\",\"pcs\":\"")
		     .append(myFormatter.format(pcs))
		     .append("\",\"grossWt\":\"")
		     .append(myFormatter.format(grosswt))
		     .append("\",\"diaWt\":\"")
		     .append(myFormatter.format(diaWt))
		     .append("\",\"colWt\":\"")
		     .append(myFormatter.format(colWt))
		     .append("\",\"silWt\":\"")
		     .append(myFormatter.format(silWt))
		     .append("\",\"pureWt\":\"")
		     .append(myFormatter.format(pureWt))
		     .append("\",\"factoryCostt\":\"")
		     .append(myFormatter.format(cost))
		     .append("\"},");
		
		}
		
		str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)	: str);
		str += "]}";
		
	
	return str;
	}



	@Override
	public String getFgIssueData(String orderTypeCond, String orderCond, String fromOrderDate, String toOrderDate,
			String regionCond, String customerTypeCond, String groupByCond, String pClientIds) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		Date date = new Date();
		
		Date previousDate = new Date(date.getTime() - (1000 * 60 * 60 * 24));
		
		String backDate = dfOutput.format(previousDate);
		
		if(orderTypeCond == null) {
			orderTypeCond =" "; 
		}
		
		if(orderCond == null) {
			orderCond =" "; 
		}
		
		if(fromOrderDate == null) {
			fromOrderDate =  backDate;
		}
		
		if(toOrderDate == null) {
			toOrderDate = backDate;
		}
		
		
		if(regionCond == null) {
			regionCond =" "; 
		}
		
		if(customerTypeCond == null) {
			customerTypeCond =" "; 
		}
		
		if(groupByCond == null) {
			groupByCond =" 'invDate' "; 
		}
		
		
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_fgissue(?,?,?,?,?,?,?) }");
		query.setParameter(1, orderCond);
		query.setParameter(2, regionCond);
		query.setParameter(3, customerTypeCond);
		query.setParameter(4, orderTypeCond);
		query.setParameter(5, fromOrderDate);
		query.setParameter(6, toOrderDate);
		query.setParameter(7, groupByCond);
		
		
		
		List<Object[]> objects = query.getResultList();
		
		Double pcs = 0.0;
		Double grossWt = 0.0;
		Double fgValue = 0.0;
		
		for(Object[] list:objects){
			
			pcs += list[4] == null ? 0.0 :Double.parseDouble(list[4].toString());
			grossWt += list[3] == null ? 0.0 : Double.parseDouble(list[3].toString());
			fgValue += list[13] == null ? 0.0 : Double.parseDouble(list[13].toString());
			
		
	}
		
		
		JSONObject jsonObject =  new JSONObject();
		jsonObject.put("pcs", myFormatter.format(pcs));
		jsonObject.put("grossWt", myFormatter.format(grossWt));
		jsonObject.put("fgValue", myFormatter.format(fgValue));

		return jsonObject.toString();
	}

	@Override
	public String getFgIssueListing(String orderTypeCond, String orderCond, String fromOrderDate, String toOrderDate,
			String regionCond, String customerTypeCond, String groupByCond, String pClientIds) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat myFormatter = new DecimalFormat("############");
		
		String fOrdToDate="";
		String fOrdFromDate="";
		
		

		Date ordFromDate = dfInput.parse(fromOrderDate);
		fOrdFromDate = dfOutput.format(ordFromDate);

		Date ordToDate = dfInput.parse(toOrderDate);
		fOrdToDate = dfOutput.format(ordToDate);
		
		if (pClientIds.length() > 0) {
			pClientIds = "  partyId in (" + pClientIds + ")  ";
		}
	
		if (orderTypeCond.length() > 0) {
			orderTypeCond = "  OrdTypeId in (" + orderTypeCond + ")  ";
		}

		if (orderCond.length() > 0) {
			orderCond = "  partyId in (" + pClientIds + ")  ";
		}
		
	
		if (regionCond.length() > 0) {
				regionCond = "  PartyRegionId in (" + regionCond + ")  ";
		}
		
		if (customerTypeCond.length() > 0) {
			customerTypeCond = "  CustomerTypeId in (" + customerTypeCond + ")  ";
		}
		
		if(groupByCond.isEmpty()) {
			groupByCond ="invDate"; 
		}
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_dashboard_fgissue(?,?,?,?,?,?,?) }");
		query.setParameter(1, orderCond);
		query.setParameter(2, regionCond);
		query.setParameter(3, customerTypeCond);
		query.setParameter(4, orderTypeCond);
		query.setParameter(5, fOrdFromDate);
		query.setParameter(6, fOrdToDate);
		query.setParameter(7, groupByCond);
		
		
		
		List<Object[]> objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		 for(Object[] list:objects){
			 
			 Double	pcs = list[4] == null ? 0.0 :Double.parseDouble(list[4].toString());
			 Double	grossWt = list[3] == null ? 0.0 : Double.parseDouble(list[3].toString());
			 Double	fgValue = list[13] == null ? 0.0 : Double.parseDouble(list[13].toString());
				
				sb.append("{\"deptNm\":\"")
			     .append(list[2] != null ? list[2] : "")
			     .append("\",\"pcs\":\"")
			     .append(myFormatter.format(pcs))
			     .append("\",\"grossWt\":\"")
			     .append(myFormatter.format(grossWt))
			     .append("\",\"fgValue\":\"")
			     .append(myFormatter.format(fgValue))
			     .append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
		
		return str;
	}

	
}
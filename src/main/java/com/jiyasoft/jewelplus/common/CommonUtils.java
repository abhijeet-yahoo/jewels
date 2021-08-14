package com.jiyasoft.jewelplus.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class CommonUtils {
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	public static List<String> getOrderPriorityList(){
		List<String> getPriority = new ArrayList<String>();
		getPriority.add("01");
		getPriority.add("02");
		getPriority.add("03");
		getPriority.add("04");
		getPriority.add("05");
		getPriority.add("06");
		getPriority.add("07");
		getPriority.add("08");
		getPriority.add("09");
		getPriority.add("10");
		getPriority.add("11");
		getPriority.add("12");
		getPriority.add("13");
		getPriority.add("14");
		getPriority.add("15");
		getPriority.add("16");
		getPriority.add("17");
		getPriority.add("18");
		getPriority.add("19");
		getPriority.add("20");
		
		return getPriority;
		
	}

	
	public static List<String> getPartyType(){
		List<String> partyTypeList = new ArrayList<String>();
		partyTypeList.add("Local");
		partyTypeList.add("Out Of State");
		partyTypeList.add("Out Of Country");
		return partyTypeList;
	}
	

	public static List<String> getOrderHallMarkList()
	{
		List <String> getHallMark = new ArrayList<String>();
		getHallMark.add("No");
		getHallMark.add("Yes");
		return getHallMark;
	}
	
	
	public static List<String> getPartyLabRatePolicy(){
		List<String> partyRateType = new ArrayList<String>();
		partyRateType.add("As Per Order");
		partyRateType.add("As Per Rate Master");
		
		return partyRateType;
	}
	
	
	public static List<String> getPartyDiaWtPolicy(){
		List<String> partyRateType = new ArrayList<String>();
		partyRateType.add("As Per Order");
		partyRateType.add("Actual");
		return partyRateType;
	}
	
	
	public static List<String> getPartyDiaRatePolicy(){
		List<String> partyRateType = new ArrayList<String>();
		partyRateType.add("As Per Order");
		partyRateType.add("As Per Rate Master");
		partyRateType.add("As Per Inward");
		return partyRateType;
	}
	
	public static List<String> getDisplayGroupBy(){
		List<String> groupByList = new ArrayList<String>();
		groupByList.add("Party");
		groupByList.add("InvNo");
		groupByList.add("InvDate");
		groupByList.add("Month");
		groupByList.add("FinYear");
		groupByList.add("Division");
		groupByList.add("Region");
		groupByList.add("CustomerType");
		return groupByList;
	}
	
	

	public static Map<String, String> getDisplayGroupByList() {
		Map<String, String> groupByMap = new LinkedHashMap<String, String>();
		List<String> groupByMaps = getDisplayGroupBy();
		
		for (String groupBy : groupByMaps) {
			groupByMap.put(groupBy, groupBy);
		}
		return groupByMap;
	}
	
	
	public static List<String> getTransactionStatus(){
		List<String> tranStatus = new ArrayList<String>();
		tranStatus.add("Physical");
		tranStatus.add("Job Work");
		tranStatus.add("Memo");
		tranStatus.add("In Transit");
		return tranStatus;
	}
	
	
	public static Map<String, String> getTransactionStatusList() {
		Map<String, String> tranStatusMap = new LinkedHashMap<String, String>();
		List<String> tranStatusMaps = getTransactionStatus();		
		for (String tranStatus : tranStatusMaps) {
			tranStatusMap.put(tranStatus, tranStatus);
		}
		return tranStatusMap;
	}
	
	
}

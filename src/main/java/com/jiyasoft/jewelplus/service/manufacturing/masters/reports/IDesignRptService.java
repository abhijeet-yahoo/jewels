package com.jiyasoft.jewelplus.service.manufacturing.masters.reports;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.DesignRpt;

public interface IDesignRptService {

	public Long count();

	public Page<DesignRpt> getDesignFilterListing(String designGrp, String cat,String subCat, String con, String coll, String subCon, String stnType,
			String shp, String sizGrp, String setType, Double frGld,Double toGld, Double frCarat, Double toKarat, String frBetDate,
			String toBetDate, Integer limit, Integer offset,Integer cancelCond, Integer bbcCond,Integer mustHaveCond, Integer allCond,String mCond,
			Integer msiCond,Double fromPrice,Double toPrice,String looks,String patterns,String clients);

	public Long getDesignFilterListingCount(String designGrp, String cat,String subCat, String con, String coll, String subCon, String stnType,
			String shp, String sizGrp, String setType, Double frGld,Double toGld, Double frCarat, Double toKarat, String frBetDate,
			String toBetDate, Integer cancelCond, Integer bbcCond,Integer mustHaveCond, Integer allCond,String mCond,Integer msiCond,Double fromPrice,
			Double toPrice,String looks,String patterns,String clients );

	public List<Design> getAll();
	
	/*public List<Object[]>exportList(String subCat, Double expQty,String frBetDate,String toBetDate);*/
	
	public List<Object[]>exportLists(String subCat, String cat,String concept,String party, Double expQty,String frBetDate,String toBetDate);
	
}

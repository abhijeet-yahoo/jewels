package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;

public interface IDesignService {

	public List<Design> findAll();

	public Page<Design> findAll(Integer limit, Integer offset, String sort, String order, String search);

	public void save(Design design);

	public Boolean delete(int id);

	public Long count();
	
	public Design findOne(int id);

	public Design findByStyleNo(String styleNo);

	public Design findByStyleNoAndVersion(String styleNo, String version);

	public Map<Integer, String> getDesignList();

	public List<Design> findActiveDesigns();

	public Map<Integer, String> getOrderDesigns(String pStyleNo);

	public Page<Design> findByStyleNo(Integer limit, Integer offset, String sort, String order,
			String styleNo, Boolean onlyActive);

	public Page<Design> findByOldStyleNo(Integer limit, Integer offset, String sort, String order,
			String oldStyleNo, Boolean onlyActive);

	public Page<Design> findByDesignNo(Integer limit, Integer offset, String sort, String order,
			String designNo, Boolean onlyActive);

	public Page<Design> findByCategory(Integer limit, Integer offset, String sort, String order,
			String category, Boolean onlyActive);

	public Page<Design> findBySubCategory(Integer limit, Integer offset, String sort, String order,
			String subCategory, Boolean onlyActive);

	public Page<Design> findByConcept(Integer limit, Integer offset, String sort, String order,
			String concept, Boolean onlyActive);

	public Page<Design> findByVersion(Integer limit, Integer offset, String sort, String order,
			String version, Boolean onlyActive);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Design findByUniqueId(Long uniqueId);

	public Design findByDesignNoAndDeactive(String designNo,Boolean deactive);
	
	public Design findByDesignNoAndVersionAndDeactive(String designNo,String version,Boolean deactive);
	
	
	public String updateCarat(Design design);
	
	public Page<Design> findByMainStyleNo(Integer limit, Integer offset, String sort, String order,
			String mainStyleNo, Boolean onlyActive);
	
	public Design findByMainStyleNoAndDeactive(String mainStyleNo,Boolean deactive);
	
	/*public List<Design> getDesignFilterListing(String cat,String subCat,String con,String subCon,String stnType,String shp,String sizGrp,String setType,
			Double frGld,Double toGld,Double frCarat,Double toKarat,String frBetDate,String toBetDate);
			//this method is not used for report--(DesignRptService method is used)
			*/
	public String designCopy(Integer styleId, String styleNo, String designNo,String versionNo, Principal principal);
	
	public Boolean checkDesignDuplicate(String styleNo,String version,Integer id);
	
	public Boolean checkDuplicateDesignNo(String designNo,String version,Integer id);
	 
	// Custom Search
	
	public Page<Design> customSearch(Integer limit, Integer offset,
			String sort, String order, String styleNo, String versionNo,
			String designNo, String reffNo,String categoryNm, String subCategoryNm, String conceptNm,String designDate) throws ParseException;
	
	
	public List<Design>findByMainStyleNoContaining(String name);
	
	public String designExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile, Principal principal) throws ParseException;
	
	
	public String designAutofill(String mainStyleNo,Integer partyId);
	
	public String designAutofillForClientRef(String mainStyleNo,Integer partyId);
	
	
	public String desingNoAutoGeneration(Design design,Principal principal);
	
	
	

}

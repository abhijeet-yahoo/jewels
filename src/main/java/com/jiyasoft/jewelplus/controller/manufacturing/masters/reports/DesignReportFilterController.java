package com.jiyasoft.jewelplus.controller.manufacturing.masters.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.DesignRpt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IDesignRptService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.mysema.query.Tuple;


@RequestMapping("/manufacturing/masters/reports")
@Controller
public class DesignReportFilterController {
	
	@Autowired
	private IReportFilterService reportFilterService;
	

	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IDesignRptService designRptService;
	
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;
	
	@Autowired
	private IPurityService purityService;

	
	@ModelAttribute("reportFilter")
	public ReportFilter constructReportFilter() {
		return new ReportFilter();
	}
	
	
	@ModelAttribute("design")
	public Design constructDesign() {
		return new Design();
	}
	
	
	@RequestMapping("/design/levelOne/combo")
	@ResponseBody
	public String levelOne(
			@RequestParam(value = "group") String group){
		String tempList = reportFilterService.onChangeLevelOne(group);
		return tempList;
	}
	
	@RequestMapping("/design/levelTwo/combo")
	@ResponseBody
	public String levelTwo(
			@RequestParam(value = "group") String group){
		String tempList = reportFilterService.onChangeLevelTwo(group);
		return tempList;
	}
	
	@RequestMapping("/design/levelThree/combo")
	@ResponseBody
	public String levelThree(
			@RequestParam(value = "group") String group){
		String tempList = reportFilterService.onChangeLevelThree(group);
		return tempList;
	}
	
	
	public List<String> getReportFormat(){
		
		List<String> list = new ArrayList<String>();
			list.add("Design Summary");
			list.add("Design BioData");
			list.add("4 * 4");
			list.add("3 * 3");
			list.add("3 * 3 With Sale Price");
			list.add("3 * 3 With MSI Sale Price");
			list.add("3 * 3 With Barcode");
			list.add("2 * 2");
			list.add("Design Pre Costing");
			list.add("Design Cost Catalog 1");
			list.add("Design Cost Catalog 2");
			list.add("Design Details");
			list.add("Design Details List");
			
		
		return list;
		
	}
	
	
	@RequestMapping("/generate/designReport/{reportName}")
	public String reportOne(@PathVariable String reportName, Model model,
		Principal principal) {
		
		ReportFilter reportFilter = reportFilterService.findByName(reportName);	
		
		String dbGroupValue = (reportFilter.getGroupList() != null ? reportFilter.getGroupList() : null);
		
		model.addAttribute("xml", reportFilter.getXml());
		model.addAttribute("comboLevelOne",  reportFilterService.popLevelOneList(dbGroupValue));
		model.addAttribute("comboLevelTwo",  reportFilterService.popLevelTwoList(dbGroupValue));
		model.addAttribute("comboLevelThree",reportFilterService.popLevelThreeList(dbGroupValue));
		model.addAttribute("reportFormat", getReportFormat());
		model.addAttribute("purityMap", purityService.getPurityList());

		String tilesTag = "";
		String jspPage = reportFilter.getFilterForm();

		if (jspPage.equalsIgnoreCase("designCatlog")) {
			tilesTag = "designCatlog";
		}
		
		if (jspPage.equalsIgnoreCase("designBioData")) {
			tilesTag = "designBioData";
		}
		
		if (jspPage.equalsIgnoreCase("bestSeller")) {
			tilesTag = "bestSeller";
		}
		
		if (jspPage.equalsIgnoreCase("designExcel")) {
			tilesTag = "designExcel";
		}


		return tilesTag;
		
		
	}
	
	
	
	
	
	
	
	private Long count = 0l;

	@RequestMapping("/updateCount")
	@ResponseBody
	public String updateCount(){
		count = 0l;
		return "-1";
	}

	
	@RequestMapping("/designFilter/listing")
	@ResponseBody
	public String designFilterListing(
			@RequestParam(value = "pDesignGroupIds", required = false) String pDesignGroupIds,
			@RequestParam(value = "pCategoryIds", required = false) String pCategoryIds,
			@RequestParam(value = "pSubCategoryIds", required = false) String pSubCategoryIds,
			@RequestParam(value = "pConceptIds", required = false) String pConceptIds,
			@RequestParam(value = "pCollectionIds", required = false) String pCollectionIds,
			@RequestParam(value = "pSubConceptIds", required = false) String pSubConceptIds,
			@RequestParam(value = "pStoneTypeIds", required = false) String pStoneTypeIds,
			@RequestParam(value = "pShapeIds", required = false) String pShapeIds,
			@RequestParam(value = "pSizeGroupIds", required = false) String pSizeGroupIds,
			@RequestParam(value = "pSettingTypeIds", required = false) String pSettingTypeIds,
			@RequestParam(value = "pFromGoldWt", required = false) Double pFromGoldWt,
			@RequestParam(value = "pToGoldWt", required = false) Double pToGoldWt,
			@RequestParam(value = "pFromCarat", required = false) Double pFromCarat,
			@RequestParam(value = "pToCarat", required = false) Double pToCarat,
			@RequestParam(value = "pFromPrice", required = false) Double pFromPrice,
			@RequestParam(value = "pToPrice", required = false) Double pToPrice,
			@RequestParam(value = "pFromBetDate", required = false) String pFromBetDate,
			@RequestParam(value = "pToBetDate", required = false) String pToBetDate,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "cancelStatus", required = false) Integer cancelStatus,
			@RequestParam(value = "bbcStatus", required = false) Integer bbcStatus,
			@RequestParam(value = "mustHaveStatus", required = false) Integer mustHaveStatus,
			@RequestParam(value = "allStatus", required = false) Integer allStatus,
			@RequestParam(value = "mCondStr", required = false) String mCondStr,
			@RequestParam(value = "msiStatus", required = false) Integer msiStatus,
			@RequestParam(value = "pLookIds", required = false) String pLookIds,
			@RequestParam(value = "pPatternIds", required = false) String pPatternIds,
			@RequestParam(value = "pClientIds", required = false) String pClientIds)
			
				{
		
		String str="";
		synchronized (this) {
			
			StringBuilder sb = new StringBuilder();

			Page<DesignRpt> designRpts = designRptService.getDesignFilterListing(pDesignGroupIds,pCategoryIds, pSubCategoryIds, pConceptIds, pCollectionIds, pSubConceptIds, 
							pStoneTypeIds, pShapeIds, pSizeGroupIds, pSettingTypeIds, pFromGoldWt, pToGoldWt, pFromCarat, pToCarat, pFromBetDate, pToBetDate,
							limit,offset,cancelStatus,bbcStatus,mustHaveStatus,allStatus,mCondStr,msiStatus,pFromPrice,pToPrice,pLookIds,pPatternIds,pClientIds);
			
			if(count == 0l){
				count = designRptService.getDesignFilterListingCount(pDesignGroupIds,pCategoryIds, pSubCategoryIds, pConceptIds, pCollectionIds, pSubConceptIds, 
							pStoneTypeIds, pShapeIds, pSizeGroupIds, pSettingTypeIds, pFromGoldWt, pToGoldWt, pFromCarat, pToCarat,
							pFromBetDate, pToBetDate,cancelStatus,bbcStatus,mustHaveStatus,allStatus,mCondStr,msiStatus,pFromPrice,pToPrice,pLookIds,pPatternIds,pClientIds);
				 
			}
			
		
			sb.append("{\"total\":").append(count).append(",\"rows\": [");
			
			
			for (DesignRpt designRpt : designRpts) {
				
				Design design =designService.findOne(designRpt.getId());
				
				sb.append("{\"id\":\"")
					.append(designRpt.getId())
					.append("\",\"styleNo\":\"")
					.append(design.getMainStyleNo())
					.append("\",\"category\":\"")
					.append(designRpt.getCategNm())
					.append("\",\"subCategory\":\"")
					.append(designRpt.getScategNm())
					.append("\",\"group\":\"")
					.append(designRpt.getDesignGroupNm() != null ? designRpt.getDesignGroupNm() : "")
					.append("\",\"grossWt\":\"")
					.append(designRpt.getGrossWt() != null ? designRpt.getGrossWt() :0.0)
					.append("\",\"stone\":\"")
					.append(designRpt.getStone() != null ? designRpt.getStone() : "")
					.append("\",\"carat\":\"")
					.append(designRpt.getCarat() != null ? designRpt.getCarat() : "")
					.append("\",\"defaultImage\":\"")
					.append(designRpt.getDefaultImage())
					.append("\",\"styleId\":\"")
					.append(designRpt.getStyleId())
					.append("\"},");
				
			}

			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";

		}
		
		
		
		
		return str;
	}
	
	//----------Best Seller Listing-------------//
	@RequestMapping("/designFilter/bestSellerListing")
	@ResponseBody
	public String bestSellerListing(
			@RequestParam(value = "pSubCategoryIds", required = false) String pSubCategoryIds,
			@RequestParam(value = "pCategoryIds", required = false) String pCategoryIds,
			@RequestParam(value = "pConceptIds", required = false) String pConceptIds,
			@RequestParam(value = "pPartyIds", required = false) String pPartyIds,
			@RequestParam(value="pExportQty")Double pExportQty,
			@RequestParam(value = "pFromBetDate", required = false) String pFromBetDate,
			@RequestParam(value = "pToBetDate", required = false) String pToBetDate){
		
		
				
				
		
		
		List<Object[]>bestSellerList= designRptService.exportLists(pSubCategoryIds, pCategoryIds, pConceptIds, pPartyIds, pExportQty, pFromBetDate, pToBetDate);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(1).append(",\"rows\": [");
		
		for(Object[] obj : bestSellerList){
			
			sb.append("{\"party\":\"")
			.append(obj[4].toString())
			.append("\",\"styleNo\":\"")
			.append(obj[1].toString())
			.append("\",\"exportQty\":\"")
			.append(obj[2].toString())
			.append("\",\"styleid\":\"")
			.append(obj[3].toString())
			.append("\",\"defaultImage\":\"")
			.append(obj[6].toString())
			.append("\"},");

					}
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

			
		return str;
	}
	//===========---------================================//
	
	
	
	//---------search table----//------//
	
	@RequestMapping("/designFilterSearch/listing")
	@ResponseBody
	public String designFilterListing(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "styleNm", required = false) String styleNm) {
		

		StringBuilder sb = new StringBuilder();
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		Page<Design> designs = designService.findByStyleNo(limit, offset, sort, order, styleNm, true);
		Long rowCount = designService.count(sort, styleNm, true);
		
		QDesignStone qDesignStone = QDesignStone.designStone;
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
		
		for (Design design : designs) {
			
		List<Tuple> designStoneList = designStoneService.getDesignStoneSumList(design.getId());
		
		Integer  stone = 0;
		Double  carat = 0.0;
		
		for(Tuple tuple : designStoneList){
			stone = tuple.get(qDesignStone.stone.sum()) != null ? tuple.get(qDesignStone.stone.sum()) : 0;
			carat = tuple.get(qDesignStone.carat.sum()) != null ? tuple.get(qDesignStone.carat.sum()) : 0.0;
		}
		
			
		sb.append("{\"id\":\"")
			.append(design.getId())
			.append("\",\"styleNo\":\"")
			.append(design.getMainStyleNo())
			.append("\",\"category\":\"")
			.append(design.getCategory().getName())
			.append("\",\"subCategory\":\"")
			.append(design.getSubCategory().getName())
			.append("\",\"group\":\"")
			.append(design.getDesignGroup() != null ? design.getDesignGroup().getName() : "")
			.append("\",\"grossWt\":\"")
			.append(design.getGrms())
			.append("\",\"stone\":\"")
			.append(stone)
			.append("\",\"carat\":\"")
			.append(df.format(carat))
			.append("\",\"defaultImage\":\"")
			.append(design.getDefaultImage())
			.append("\",\"styleId\":\"")
			.append(design.getId())
			.append("\"},");
			
			
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		System.out.println(str);
		
		return str;
		
		
	}
	
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/designFilter/finalTablelisting", method = RequestMethod.POST)
	public synchronized String designFilterFinalListing(@RequestParam(value = "selData" ,required = false) String selData,
			@RequestParam(value = "finalData" ,required = false) String finalData) {
			
			List<DesignRpt> designRptList = new ArrayList<DesignRpt>();
		
			JSONArray jsonRow = new JSONArray(selData);
			JSONArray jsonRow2 = new JSONArray(finalData);
			//Boolean status = true;
			
			
			for(int i=0;i<jsonRow.length();i++){
				
				JSONObject dataRow = (JSONObject) jsonRow.get(i);
				
				DesignRpt designRpt = new DesignRpt();
				
				designRpt.setStyleNo(dataRow.get("styleNo").toString());
				designRpt.setDesignGroupNm(dataRow.get("group").toString());
				designRpt.setCategNm(dataRow.get("category").toString());
				designRpt.setScategNm(dataRow.get("subCategory").toString());
				designRpt.setGrossWt(dataRow.get("grossWt").toString() != null  ?  Double.parseDouble(dataRow.get("grossWt").toString()) :0.0 );
				designRpt.setStone(dataRow.get("stone").toString().equals("") ?0:Integer.parseInt(dataRow.get("stone").toString()));
				designRpt.setCarat(dataRow.get("carat").toString().equals("")?0.00: Double.parseDouble(dataRow.get("carat").toString()));
				designRpt.setStyleId(Integer.parseInt(dataRow.get("styleId").toString()));
				
				designRptList.add(designRpt);
					
			}
			
			
			
			for(int i=0;i<jsonRow2.length();i++){
				
				String stat="true";				
				JSONObject dataRow2 = (JSONObject) jsonRow2.get(i);
				
				for (DesignRpt designRpt : designRptList){
					if(designRpt.getStyleId().equals(Integer.parseInt(dataRow2.get("styleId").toString()))){
						stat="false";
					}
				}
					
				if(stat.equalsIgnoreCase("true")){
					DesignRpt designRpt = new DesignRpt();
					
					designRpt.setStyleNo(dataRow2.get("styleNo").toString());
					designRpt.setDesignGroupNm(dataRow2.get("group").toString());
					designRpt.setCategNm(dataRow2.get("category").toString());
					designRpt.setScategNm(dataRow2.get("subCategory").toString());
					designRpt.setGrossWt(Double.parseDouble(dataRow2.get("grossWt").toString()));
					designRpt.setStone(dataRow2.get("stone").toString().equals("") ?0:Integer.parseInt(dataRow2.get("stone").toString()));
					designRpt.setCarat(dataRow2.get("carat").toString().equals("")?0.00: Double.parseDouble(dataRow2.get("carat").toString()));
					designRpt.setStyleId(Integer.parseInt(dataRow2.get("styleId").toString()));
					
					designRptList.add(designRpt);

					
				}
				
				
			}
			
			
		
		
		
		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(10).append(",\"rows\": [");
		
		for (DesignRpt designRpt : designRptList) {
			
				sb.append("{\"styleNo\":\"")
					.append(designRpt.getStyleNo())
					.append("\",\"category\":\"")
					.append(designRpt.getCategNm())
					.append("\",\"subCategory\":\"")
					.append(designRpt.getScategNm())
					.append("\",\"group\":\"")
					.append(designRpt.getDesignGroupNm() != null ? designRpt.getDesignGroupNm() : "")
					.append("\",\"grossWt\":\"")
					.append(designRpt.getGrossWt())
					.append("\",\"stone\":\"")
					.append(designRpt.getStone() != null ? designRpt.getStone() : "")
					.append("\",\"carat\":\"")
					.append(designRpt.getCarat() != null ? designRpt.getCarat() : "")
					.append("\",\"styleId\":\"")
					.append(designRpt.getStyleId())
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteDesignRpt(event,")
					.append(designRpt.getStyleId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(designRpt.getStyleId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
				
			}
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		designRptList.removeAll(designRptList);
		
		return str;
	}
	

	
	
	
	//----------//-------displayReports - code--------//
	
	@ResponseBody
	@RequestMapping(value = "/displayReports" , method=RequestMethod.POST)
	public String dispReport(@ModelAttribute("design") Design design,
			BindingResult result,
			Model model,
			Principal principal,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "xml", required = false) String xml,
			@RequestParam(value = "pStyleIds", required = false) String pStyleIds,
			@RequestParam(value = "pGroup1", required = false) String pGroup1,
			@RequestParam(value = "pGroup2", required = false) String pGroup2,
			@RequestParam(value = "pGroup3", required = false) String pGroup3,
			@RequestParam(value = "pRepFormat", required = false) String pRepFormat,
			@RequestParam(value = "purityIds", required = false) String purityIds,
			@RequestParam(value = "pmOpt", required = false) Integer pmOpt) throws SQLException{
		
		synchronized (this) {
		
		String retVal = "1";
		
		if(pStyleIds.length() < 0){
			return retVal ="-1";
		}
		
		
		String fileName = "";
		String subRptPath = "";
		String imgPath = "";
		String purityCond = "";
		
		Connection conn = null;
		try {
			
			if(pRepFormat.length() > 0){
						
				if(pRepFormat.equalsIgnoreCase("4 * 4")){
					xml = "DesignCatalog44";
				}else if(pRepFormat.equalsIgnoreCase("3 * 3")){
					xml = "DesignCatalog33";
				}else if(pRepFormat.equalsIgnoreCase("3 * 3 With Sale Price")){
					xml = "DesignCatalogSalePrice";
				}else if(pRepFormat.equalsIgnoreCase("3 * 3 With MSI Sale Price")){
					xml = "DesignCatalogMsiSalePrice";
				}else if(pRepFormat.equalsIgnoreCase("3 * 3 With Barcode")){
					xml = "DesignBarcodeCatalog33";
				}else if(pRepFormat.equalsIgnoreCase("Design Pre Costing")){
					xml = "DesignCostSheet";
				}else if(pRepFormat.equalsIgnoreCase("Design Cost Catalog 1")){
					xml = "designcostcatalog";
				}else if(pRepFormat.equalsIgnoreCase("Design Cost Catalog 2")){
					xml = "designcostcatalog2";
				}else if(pRepFormat.equalsIgnoreCase("Design Details")){
					xml = "designDetail";
				}else if(pRepFormat.equalsIgnoreCase("Design BioData")){
					xml = "designBioData";
				}else if(pRepFormat.equalsIgnoreCase("Design Details List")){
					xml = "designDetailList";
				}else{
					xml = "DesignCatalog22";
				}
			}
			
			
			conn = Utils.getConnection();
			String styleCond = " ' StyleId in (" + pStyleIds + ") ' ";
			
			if(purityIds.length() > 0){
				purityCond = " ' purityId  in ("+purityIds+") ' ";
			}else{
				purityCond = " ' ' ";
			}
			
			fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
			subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
			imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;

			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
			
			parametersMap.put("styleCond", styleCond);
			parametersMap.put("imagepath", imgPath);
			parametersMap.put("subrptpath", subRptPath);
			parametersMap.put("p_group1", pGroup1);
			parametersMap.put("p_group2", pGroup2);
			parametersMap.put("p_group3", pGroup3);
			parametersMap.put("purityCond", purityCond);
			
			
			JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
			
			if(pmOpt == 0) {
				String designCatalogFileName = System.currentTimeMillis()+"";
				File file = new File(uploadDirecotryPath + reportoutputdirectorypath + designCatalogFileName+".pdf");
				file.createNewFile();
				JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + designCatalogFileName+".pdf");
				
				String exportFileName = System.currentTimeMillis()+""+principal.getName();
				Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +designCatalogFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportFileName+".pdf");
				
				file.delete();
				
				retVal = exportFileName;
					
			}else if(pmOpt == 1) {
				
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				String fileNm=xml+"-"+System.currentTimeMillis()+".xlsx";
				
				//String fileNm="purc.xlsx";
				File outputFile = new File(fileNm);
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
				//Set configuration as you like it!!
				configuration.setDetectCellType(true);
				configuration.setWhitePageBackground(false);
				configuration.setCollapseRowSpan(false);
				configuration.setAutoFitPageHeight(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
				
			
				retVal = fileNm;
			
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			retVal = "-2";
		} finally {
			conn.close();
		}

		return retVal;
		
		}
	}
	
	
	
	
	
	
	
	
	// code for all condition  reports----//
	
	
	@RequestMapping(value = "/popReport/all",method=RequestMethod.POST)
	@ResponseBody
	public String popAllReportCond(
			@RequestParam(value = "pDesignGroupIds", required = false) String pDesignGroupIds,
			@RequestParam(value = "pCategoryIds", required = false) String pCategoryIds,
			@RequestParam(value = "pSubCategoryIds", required = false) String pSubCategoryIds,
			@RequestParam(value = "pConceptIds", required = false) String pConceptIds,
			@RequestParam(value = "pSubConceptIds", required = false) String pSubConceptIds,
			@RequestParam(value = "pStoneTypeIds", required = false) String pStoneTypeIds,
			@RequestParam(value = "pShapeIds", required = false) String pShapeIds,
			@RequestParam(value = "pSizeGroupIds", required = false) String pSizeGroupIds,
			@RequestParam(value = "pSettingTypeIds", required = false) String pSettingTypeIds,
			@RequestParam(value = "pFromGoldWt", required = false) Double pFromGoldWt,
			@RequestParam(value = "pToGoldWt", required = false) Double pToGoldWt,
			@RequestParam(value = "pFromCarat", required = false) Double pFromCarat,
			@RequestParam(value = "pToCarat", required = false) Double pToCarat,
			@RequestParam(value = "pFromBetDate", required = false) String pFromBetDate,
			@RequestParam(value = "pToBetDate", required = false) String pToBetDate,
			@RequestParam(value = "cancelStatus", required = false) Integer cancelStatus,
			@RequestParam(value = "bbcStatus", required = false) Integer bbcStatus,
			@RequestParam(value = "mustHaveStatus", required = false) Integer mustHaveStatus,
			@RequestParam(value = "allStatus", required = false) Integer allStatus,
			@RequestParam(value = "mCondStr", required = false) String mCond,
			@RequestParam(value = "pLookIds", required = false) String pLookIds,
			@RequestParam(value = "pPatternIds", required = false) String pPatternIds,
			@RequestParam(value = "pClientIds", required = false) String pClientIds,
			
			@RequestParam(value = "pGroup1", required = false) String pGroup1,
			@RequestParam(value = "pGroup2", required = false) String pGroup2,
			@RequestParam(value = "pGroup3", required = false) String pGroup3,
			@RequestParam(value = "pRepFormat", required = false) String pRepFormat,
			@RequestParam(value = "xml", required = false) String xml,
			@RequestParam(value = "msiStatus", required = false) Integer msiStatus,
			@RequestParam(value = "purityIds", required = false) String purityIds,
			@RequestParam(value = "pAllOpt", required = false) Integer pAllOpt,			
			Principal principal) throws ParseException, SQLException{
		
		synchronized (this) {
			
		
		
		String retVal = "1";
		
		String designGroupCond 	= "";
		String categCond 		= "";
		String subCatCond 		= "";
		String conceptCond 		= "";
		String subConceptCond 	= "";
		String stnTypeCond 		= "";
		String shapeCond 		= "";
		String sizeGroupCond 	= "";
		String setTypeCond 		= "";
		String fromWt			= "";
		String toWt				= "";
		String fromCarat		= "";
		String toCarat			= "";
		String startDate 		= "";
		String endDate			= "";
		Integer cancelCond;
		Integer bbcCond	;
		Integer musthaveCond;
		Integer allCond	;
		Integer msiCond;
		String lookCond			="";
		String patternCond		="";
		String clientCond		="";

		
		String fileName = "";
		String subRptPath = "";
		String imgPath = "";
		
		String purityCond = "";
		
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		Connection conn = null;
		
		if(pRepFormat.length() > 0){
			
			
			
			
			if(pRepFormat.equalsIgnoreCase("4 * 4")){
				xml = "DesignCatalog44A";
			}else if(pRepFormat.equalsIgnoreCase("3 * 3")){
				xml = "DesignCatalog33A";
			}
//			else if(pRepFormat.equalsIgnoreCase("3 * 3 With Barcode")){
//				xml = "DesignBarcodeCatalog33A";
//			}
			else if(pRepFormat.equalsIgnoreCase("Design Summary")){
				xml = "designSummaryListAllCond";
			}else if(pRepFormat.equalsIgnoreCase("2 * 2")){
				xml = "DesignCatalog22A";
			}else {
				return "-3";
			}
			
			
		}
		
		try {
			
	
			
			
			

			if(pDesignGroupIds.length() > 0){
				designGroupCond = " ' DesignGroupId  in ("+pDesignGroupIds+") ' ";
			}else{
				designGroupCond = " ' ' ";
			}
			
			if(pCategoryIds.length() > 0){
				categCond = " 'CategId in ("+pCategoryIds+") ' ";
			}else{
				categCond = " ' ' ";
			}
			
			
			if(pClientIds.length() > 0){
				clientCond = " 'PartyId in ("+pClientIds+") ' ";
			}else{
				clientCond = " ' ' ";
			}
			
			if(pSubCategoryIds.length() > 0){
				subCatCond = " ' SCategId in ("+pSubCategoryIds+") ' ";
			}else{
				subCatCond = " ' ' ";
			}
			
			if(pConceptIds.length() > 0){
				conceptCond = " ' ConceptId in ("+pConceptIds+") ' ";
			}else{
				conceptCond = " ' ' ";
			}
			
			if(pSubConceptIds.length() > 0){
				subConceptCond = " ' SubConceptId in ("+pSubConceptIds+") ' ";
			}else{
				subConceptCond = " ' ' ";
			}
			
			if(pStoneTypeIds.length() > 0){
				stnTypeCond = " ' StoneTypeId in ("+pStoneTypeIds+") ' ";
			}else{
				stnTypeCond = " ' ' ";
			}
			
			if(pShapeIds.length() > 0){
				shapeCond = " ' ShapeId in ("+pShapeIds+") ' ";
			}else{
				shapeCond = " ' ' ";
			}
			
			if(pSizeGroupIds.length() > 0){
				sizeGroupCond = " ' SizeGroupId in ("+pSizeGroupIds+") ' ";
			}else{
				sizeGroupCond = " ' ' ";
			}
			
			if(pSettingTypeIds.length() > 0){
				setTypeCond = " ' SettypeId in ("+pSettingTypeIds+") ' ";
			}else{
				setTypeCond = " ' ' ";
			}
			
			if(pFromGoldWt == null || pFromGoldWt.equals("")){
				fromWt = " ' ' ";
			}else{
				fromWt =  " ' "+pFromGoldWt+" '";
			}
			
			if(pToGoldWt == null || pToGoldWt.equals("")){
				toWt = " ' ' ";
			}else{
				toWt = " ' "+pToGoldWt+" ' ";
			}
			
			if(pFromCarat == null || pFromCarat.equals("")){
				fromCarat = " ' ' ";
			}else{
				fromCarat = " ' "+pFromCarat+" ' ";
			}
			
			if(pToCarat == null || pToCarat.equals("")){
				toCarat = " ' ' ";
			}else{
				toCarat = " ' "+pToCarat+" ' ";
			}
			
			if (pFromBetDate.length() > 0) {
				Date betFromDate = dfInput.parse(pFromBetDate);
				String fBetFromDate = dfOutput.format(betFromDate);
				startDate = " ' "+fBetFromDate+" ' ";
			}else{
				startDate = " ' ' ";
			}
			
			if (pToBetDate.length() > 0) {
				Date betToDate = dfInput.parse(pToBetDate);
				String fBetToDate = dfOutput.format(betToDate);
				endDate   = " ' "+fBetToDate+" ' ";
			}else{
				endDate = " ' ' ";
			}
			
			if(pLookIds.length() > 0){
				lookCond = " ' LookId in ("+pLookIds+") ' ";
			}else{
				lookCond = " ' ' ";
			}
			
			if(pPatternIds.length() > 0){
				patternCond = " ' LookId in ("+pPatternIds+") ' ";
			}else{
				patternCond = " ' ' ";
			}
			
			cancelCond = cancelStatus;
			bbcCond = bbcStatus;
			musthaveCond = mustHaveStatus;
			msiCond=msiStatus;
			allCond = allStatus;
			
			
			
			if(mCond.equalsIgnoreCase("0")){
				mCond = " ' ' ";
			}else{
				//mCond = "'%"+mCond+"%'";
				mCond = " ' ' ";
			}
			
			if(purityIds.length() > 0){
				purityCond = " ' purityId  in ("+purityIds+") ' ";
			}else{
				purityCond = " ' ' ";
			}
			
			
			
			
			
			
			
		
			
			conn = Utils.getConnection();
			
			
			fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
			subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
			imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;

			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
			
			
			
			parametersMap.put("categCond", categCond);
			parametersMap.put("subCatCond", subCatCond);
			parametersMap.put("conceptCond", conceptCond);
			parametersMap.put("subConceptCond", subConceptCond);
			parametersMap.put("stnTypeCond", stnTypeCond);
			parametersMap.put("shapeCond", shapeCond);
			parametersMap.put("sizeGroupCond", sizeGroupCond);
			parametersMap.put("setTypeCond", setTypeCond);
			parametersMap.put("fromWt", fromWt);
			parametersMap.put("toWt", toWt);
			parametersMap.put("fromCarat", fromCarat);
			parametersMap.put("toCarat", toCarat);
			parametersMap.put("startDate", startDate);
			parametersMap.put("endDate", endDate);
			parametersMap.put("designGroupCond", designGroupCond);
			parametersMap.put("cancelCond", cancelCond);
			parametersMap.put("bbcCond", bbcCond);
			parametersMap.put("musthaveCond", musthaveCond);
			parametersMap.put("msiCond", msiCond);
			parametersMap.put("allCond", allCond);
			parametersMap.put("mCond", mCond);
			parametersMap.put("lookCond", lookCond);
			parametersMap.put("patternCond", patternCond);
			parametersMap.put("clientCond", clientCond);
			parametersMap.put("imagepath", imgPath);
			parametersMap.put("subrptpath", subRptPath);
			parametersMap.put("p_group1", pGroup1);
			parametersMap.put("p_group2", pGroup2);
			parametersMap.put("p_group3", pGroup3);
			parametersMap.put("purityCond", purityCond);
			
			
			
			JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
			
			if(pAllOpt == 1) {
			
				String designCatalogFileName = System.currentTimeMillis()+"";
				File file = new File(uploadDirecotryPath + reportoutputdirectorypath + designCatalogFileName+".pdf");
				file.createNewFile();
				JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + designCatalogFileName+".pdf");
				
				String exportFileName = System.currentTimeMillis()+""+principal.getName();
				Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +designCatalogFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportFileName+".pdf");
				
				file.delete();
				
				retVal = exportFileName;
				
			}else if(pAllOpt == 2) {
				
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				String fileNm=xml+"-"+System.currentTimeMillis()+".xlsx";
				
				//String fileNm="purc.xlsx";
				File outputFile = new File(fileNm);
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
				//Set configuration as you like it!!
				configuration.setDetectCellType(true);
				configuration.setWhitePageBackground(false);
				configuration.setCollapseRowSpan(false);
				configuration.setAutoFitPageHeight(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
				
			
				retVal = fileNm;
			
			}
			
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e);
			retVal = "-2";
		} finally {
			conn.close();
		}

		return retVal;
		
		}
	}
	
	
	
	
	
	@RequestMapping(value="/bestSellerReport",method=RequestMethod.POST)
	@ResponseBody
	public String getReport(@RequestParam(value="tempStyleId") String styleid,
			@RequestParam(value="startdate") String pFromBetDate,
			@RequestParam(value="enddate") String pToBetDate,
			@RequestParam(value="expqty") Double expqty,
			@RequestParam(value="xml") String xml,
			@RequestParam(value="scategcond") String scategcond,
			@RequestParam(value="categcond") String categcond,
			@RequestParam(value="partycond") String partycond,
			@RequestParam(value="conceptcond") String conceptcond,
			Principal principal) throws ParseException,SQLException{
		
		synchronized (this) {		
		String retVal = "1";

		String startDate="";
		String endDate="";
		
		String subCatCond="";
		String catCond="";
		String conceptCond="";
		String partyCond="";
		String styleCond="";
		
		String fileName = "";
		String subRptPath = "";
		String imgPath = "";
		
		Connection conn = null;
		try {
		
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		
		
		if (pFromBetDate.length() > 0) {
			Date betFromDate = dfInput.parse(pFromBetDate);
			String fBetFromDate = dfOutput.format(betFromDate);
			startDate = " ' "+fBetFromDate+" ' ";
		}else{
			startDate = " ' ' ";
		}
		
		if (pToBetDate.length() > 0) {
			Date betToDate = dfInput.parse(pToBetDate);
			String fBetToDate = dfOutput.format(betToDate);
			endDate   = " ' "+fBetToDate+" ' ";
		}else{
			endDate = " ' ' ";
		}
		
		
		if(scategcond.length() > 0){
			subCatCond = " ' SCategId in ("+scategcond+") ' ";
		}else{
			subCatCond = " ' ' ";
		}
		
		if(categcond.length() > 0){
			catCond = " ' CategId in ("+categcond+") ' ";
		}else{
			catCond = " ' ' ";
		}
		
		if(conceptcond.length() > 0){
			conceptCond = " ' ConceptId in ("+conceptcond+") ' ";
		}else{
			conceptCond = " ' ' ";
		}
		
		if(partycond.length() > 0){
			partyCond = " ' ordPartyId in ("+partycond+") ' ";
		}else{
			partyCond = " ' ' ";
		}
		
		
		if(styleid.length() > 0){
			styleCond = "'styleid in ("+styleid+")'";
		}else{
			styleCond = "''";
		}
		
		
		conn = Utils.getConnection();
		fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
		System.err.println("file "+fileName);
		subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
		imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
		
		
		InputStream input = new FileInputStream(new File(fileName));
		java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
		
		System.out.println(styleCond+"---"+subCatCond+"---"+startDate+"---"+expqty+"---"+endDate+"---"+imgPath);
		
		parametersMap.put("styleCond", styleCond);
		parametersMap.put("subCatCond", subCatCond);
		parametersMap.put("catCond", catCond);
		parametersMap.put("conceptCond", conceptCond);
		parametersMap.put("partyCond", partyCond);
		parametersMap.put("startdate", startDate);
		parametersMap.put("exportQty", expqty);
		parametersMap.put("enddate", endDate);
		parametersMap.put("imagepath", imgPath);
		parametersMap.put("subrptpath", subRptPath);
		parametersMap.put("fromPeriod", pFromBetDate);
		parametersMap.put("toPeriod", pToBetDate);
		
		
		
		JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
		
		String bestSellerFileName = System.currentTimeMillis()+"";
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath + bestSellerFileName+".pdf");
		file.createNewFile();
		JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + bestSellerFileName+".pdf");
		
		String exportFileName = System.currentTimeMillis()+""+principal.getName();
		Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +bestSellerFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportFileName+".pdf");
		
		file.delete();
		
		retVal = exportFileName;

		
		
		
		
		
		}catch (Exception e) {
			System.out.println(e);
			retVal = "-2";
		} finally {
			conn.close();
		}

		return retVal;
		
	}
	
	}
	
	
	
	
	
	@RequestMapping("/designFilter/designExcelListing")
	@ResponseBody
	public String designExcelListing(
			@RequestParam(value = "pStyleIds", required = false) String pStyleIds,
			@RequestParam(value = "rptOpt", required = false) String rptOpt){
		
		
		try{
			
			String styleCond 	= "";
			
			
			if(pStyleIds.length() > 0){
				styleCond = "StyleId  in ("+pStyleIds+")";
			}else{
				styleCond = "";
			}
			
		
			
			if(rptOpt.equalsIgnoreCase("1")){
				
				@SuppressWarnings("unchecked")
				TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_rep_designExcelStylelistNew(?) }");
				query.setParameter(1,styleCond);
				
				
				List<Object[]> dtList = query.getResultList();
				
				StringBuilder sb = new StringBuilder();

				sb.append("{\"total\":").append(dtList.size()).append(",\"rows\": [");
				
				for (Object[] result : dtList) {
					
					sb.append("{\"productId\":\"")
					.append(result[0]!=null? result[0]:"")
					.append("\",\"productCode\":\"")
					.append(result[1]!=null? result[1]:"")
					.append("\",\"productName\":\"")
					.append(result[2]!=null? result[2]:"")
					.append("\",\"categoryName\":\"")
					.append(result[3]!=null? result[3]:"")
					.append("\",\"subCategoryName\":\"")
					.append(result[4]!=null? result[4]:"")
					.append("\",\"programName\":\"")
					.append(result[5]!=null? result[5]:"")
					.append("\",\"noofPcsinpack\":\"")
					.append(result[6]!=null? result[6]:"")
					.append("\",\"theam\":\"")
					.append(result[7]!=null? result[7]:"")
					.append("\",\"collectionName\":\"")
					.append(result[8]!=null? result[8]:"")
					.append("\",\"styleno\":\"")
					.append(result[9]!=null? result[9]:"")
					.append("\",\"styleName\":\"")
					.append(result[10]!=null? result[10]:"")
					.append("\",\"mRP\":\"")
					.append(result[11]!=null? result[11]:"")
					.append("\",\"discountedprice\":\"")
					.append("")
					.append("\",\"lowbidprice\":\"")
					.append("")
					.append("\",\"shopFor\":\"")
					.append(result[12]!=null? result[12]:"")
					.append("\",\"metalName\":\"")
					.append(result[13]!=null? result[13]:"")
					.append("\",\"metalPurityName\":\"")
					.append(result[14]!=null? result[14]:"")
					.append("\",\"metalColorName\":\"")
					.append(result[15]!=null? result[15]:"")
					.append("\",\"weight\":\"")
					.append(result[16]!=null? result[16]:"")
					.append("\",\"engraving\":\"")
					.append(result[17]!=null? result[17]:"")
					.append("\",\"productType\":\"")
					.append(result[18]!=null? result[18]:"")
					.append("\",\"instock\":\"")
					.append(result[19]!=null? result[19]:"")
					.append("\",\"description\":\"")
					.append(result[21]!=null? result[21]:"")
					.append("\",\"size\":\"")
					.append(result[20]!=null? result[20]:"")
					.append("\"},");
					
					
				    
				}

				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				
			
				
				return str;
				
			}else if(rptOpt.equalsIgnoreCase("2")){
				
				Query query = entityManager.createNativeQuery("select styleid,Stonetype,shapenm,subshapenm,qualitynm,Stone,Carat,Size,sizegroupnm "
						+ "from designstone_vw where "+ styleCond+" order by styleid");
				@SuppressWarnings("unchecked")
				List<Object[]> dtList = query.getResultList();
				
				
				StringBuilder sb = new StringBuilder();

				sb.append("{\"total\":").append(dtList.size()).append(",\"rows\": [");
				
				for (Object[] result : dtList) {
					
					sb.append("{\"productCode\":\"")
					.append(result[0]!=null? result[0]:"")
					.append("\",\"stoneTypeName\":\"")
					.append(result[1]!=null? result[1]:"")
					.append("\",\"stoneShapeName\":\"")
					.append(result[2]!=null? result[2]:"")
					.append("\",\"subStoneShapeName\":\"")
					.append(result[3]!=null? result[3]:"")
					.append("\",\"stoneQualityName\":\"")
					.append(result[4]!=null? result[4]:"")
					.append("\",\"stonePcs\":\"")
					.append(result[5]!=null? result[5]:"")
					.append("\",\"stoneCaret\":\"")
					.append(result[6]!=null? result[6]:"")
					.append("\",\"stoneSize\":\"")
					.append(result[7]!=null? result[7]:"")
					.append("\",\"stoneSizeGroup\":\"")
					.append(result[8]!=null? result[8]:"")
					.append("\"},");
					
					
				    
				}

				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				
				return str;
				
			}
			
			
			
	
	}catch(Exception e){
		System.out.println(e);
	}
		
		
		
		return null;
		
	}
	
	
	
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/designFilter/commonExcelUpload", method = RequestMethod.POST)
	@ResponseBody
	public String excelUpload(Model model,HttpSession httpSession,
			@RequestParam("file") MultipartFile excelfile,
			@RequestParam("tempExcelFile") String tempExcelFile){

		
		String retVal ="-1";
		String designNo ="";
		String remark = "";
		try {
				if(!tempExcelFile.isEmpty()){
			
			
				List<Design> designList = new ArrayList<Design>();
				List<DesignExcel> designExcels = new ArrayList<DesignExcel>();
			    if (tempExcelFile.endsWith("xlsx")) {
			    	int i=1;
			    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			        XSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						DesignExcel design = new DesignExcel();
						XSSFRow row = worksheet.getRow(i++);
						
						if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
							continue;
						}
						
						Design design1 = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString().trim(), false);
						
							if(design1 == null){
								if(designNo !=""){
									designNo +="<br/>"+row.getCell(0).toString().trim();
								}else{
									designNo = row.getCell(0).toString().trim();
								}
								remark = "No such design exists";
							
							}
						
					}
					
					
					
					workbook.close();
					
					
			    } else if (tempExcelFile.endsWith("xls")) {
			    	int i = 1;
					HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
					HSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						DesignExcel design = new DesignExcel();
						HSSFRow row = worksheet.getRow(i++);
						if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
							continue;
						}
					
						for(int j=0;j<row.getLastCellNum();j++){
							System.out.println(j);
							Cell cell = row.getCell(j);
						    cell.setCellType (Cell.CELL_TYPE_STRING);
						    j++;
						}
					
						
						
						Design design1 = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString().trim(), false);

						if(design1 == null){
							if(designNo !=""){
								designNo +="<br/>"+row.getCell(0).toString().trim();
							}else{
								designNo = row.getCell(0).toString().trim();
							}
							remark = "No such design exists";
						
						}
						
				
						
						
					}
					
					workbook.close();
									    
			    } else {
			        throw new IllegalArgumentException("The specified file is not Excel file");
			    }
			    
			    
			    if (remark != "") {
			    	  httpSession.setAttribute("designNoList", designNo);
			    	 retVal="-3";
			    	
			    }else {
			    	
			    	 if (tempExcelFile.endsWith("xlsx")) {
					    	int i=1;
					    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
					        XSSFSheet worksheet = workbook.getSheetAt(0);
							while (i <= worksheet.getLastRowNum()) {
								Design design = new Design();
								XSSFRow row = worksheet.getRow(i++);
								
								if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
									continue;
								}
							
								
								Design design1 = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString().trim(), false);
									
									design.setId(design1.getId());
									design.setMainStyleNo(row.getCell(0).toString());
									design.setGrossWt(design1.getGrossWt());
									if(design1.getCategory() != null){
										design.setCategory(design1.getCategory());
									}
									
									if(design1.getSubCategory() !=null) {
										design.setSubCategory(design1.getSubCategory());
									}
								
								
								designList.add(design);

								
							}
							
							
							
							workbook.close();
							
							
					    } else if (tempExcelFile.endsWith("xls")) {
					    	int i = 1;
							HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
							HSSFSheet worksheet = workbook.getSheetAt(0);
							while (i <= worksheet.getLastRowNum()) {
								Design design = new Design();
								HSSFRow row = worksheet.getRow(i++);
								if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
									continue;
								}
							
							
								
								Design design1 = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString().trim(), false);
								
								design.setId(design1.getId());
								design.setMainStyleNo(row.getCell(0).toString());
								design.setGrossWt(design1.getGrossWt());
								if(design1.getCategory() != null){
									design.setCategory(design1.getCategory());
								}
								
								if(design1.getSubCategory() !=null) {
									design.setSubCategory(design1.getSubCategory());
								}
							
							
							designList.add(design);
								
							}
							
							workbook.close();
											    
					    }
			    	 retVal="-1";
			    	
			    	 httpSession.setAttribute("designSessionList", designList);
			    }
			    
			    			   
			    
			}
		}catch (IOException e) {
			retVal ="-2";	
			e.printStackTrace();
			}
		
		
		
		return retVal;
	}
	
	

	@RequestMapping("/designFilter/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<Design> designs = (List<Design>) httpSession.getAttribute("designSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(designs.size()).append(",\"rows\": [");
		
		for(Design design : designs){
			Integer vStone =0;
			Double vCarat =0.0;
			String vGroup="";
			List<DesignStone> designStones = designStoneService.findByDesign(design);
			for (DesignStone designStone : designStones) {
				vStone += designStone.getStone();
				vCarat += designStone.getCarat();
				vGroup = designStone.getSizeGroup() != null ? designStone.getSizeGroup().getName() :"";
			}
			
			sb.append("{\"styleId\":\"")
				.append(design.getId())
				.append("\",\"styleNo\":\"")
				.append(design.getMainStyleNo() !=null ? design.getMainStyleNo() :"")
				.append("\",\"category\":\"")
				.append(design.getCategory() != null ? design.getCategory().getName():"")
				.append("\",\"subCategory\":\"")
				.append(design.getSubCategory() !=null ? design.getSubCategory().getName() :"")
				.append("\",\"group\":\"")
				.append(vGroup)
				.append("\",\"grossWt\":\"")
				.append(design.getGrossWt())
				.append("\",\"stone\":\"")
				.append(vStone)
				.append("\",\"carat\":\"")
				.append(Math.round(vCarat*1000.0)/1000.0)
				.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}


	@RequestMapping("/designFilter/styleNotInDesign")
	@ResponseBody
	public String designNoist(HttpSession httpSession){
		
		String designNo = (String) httpSession.getAttribute("designNoList");
		return designNo;
		
	}
	
	

}

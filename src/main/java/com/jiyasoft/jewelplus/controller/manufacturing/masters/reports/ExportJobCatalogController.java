package com.jiyasoft.jewelplus.controller.manufacturing.masters.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;

@RequestMapping("/manufacturing/masters/reports")
@Controller
public class ExportJobCatalogController {

	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private IReportFilterService reportFilterService;
	
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
	
	@RequestMapping("/exportJobCatalog")
	public String exportJobCatalog(){
		return "exportJobCatalog";
	}
	
	
	List<String> itemNoLists = new ArrayList<String>();
	
	
	@RequestMapping(value = "/exportJobCatalog/listing")
	@ResponseBody
	public String menuListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search){
		
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"total\":").append(10).append(",\"rows\": [");
		
		for(String itemNo:itemNoLists){	
			sb.append("{\"itemNo\":\"")
				.append(itemNo)
				.append("\"},");
		}

			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
			
			
		return str;
	}
	
	
	@ResponseBody
	@RequestMapping("/add/itemNo")
	public String add(
			@RequestParam(value = "itemNo") String itemNo){
		
		String retVal = "-1";
		
		if(!itemNoLists.contains(itemNo.toUpperCase())){
			itemNoLists.add(itemNo.toUpperCase());
		}else{
			retVal = "-2";
		}
		
		return retVal;
	}
	
	
	@ResponseBody()
	@RequestMapping("/deleteAll/itemNo")
	public String deleteAllItemNoList(){
		
		Boolean status = itemNoLists.removeAll(itemNoLists);
		return status+"";
	}
	
	@ResponseBody
	@RequestMapping("/delete/itemNo")
	public String delete(@RequestParam("itemNos") String itemNos){
		
		String retVal = "-1";
		
		String vItemNo[] = itemNos.split(",");
		
		for(int i=0; i < vItemNo.length ; i++){
			String tempItemNo = vItemNo[i];
			itemNoLists.remove(tempItemNo);
		}
		
		
		return retVal;
	}
	
	
	
	@RequestMapping("/autofill/itemNo")
	@ResponseBody
	public String itemNoList(@RequestParam(value = "term", required = false) String itemNo) {
		
		Page<CostingDt> costDtList = costingDtService.findByItemNo(15, 0, "itemNo", "ASC", itemNo.toUpperCase(), true);
		
		StringBuilder sb = new StringBuilder();

		for (CostingDt costingDt : costDtList) {
			sb.append("\"")
				.append(costingDt.getItemNo())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	
	
	@RequestMapping("/view/rept")
	@ResponseBody
	public String viewReport(@RequestParam("itemNo") String itemNos,
			@RequestParam("btnId") String btnId) throws SQLException {
		
		System.out.println("itemNo-------->>>>>>>>>>>"+itemNos);
		
		ReportFilter reportFilter = reportFilterService.findByName(btnId);
		String rptName = reportFilter.getXml();
		
		System.out.println("--------------reportname== "+rptName);
		
		//------------report code----------//
		
		String retVal = "-1";
		String fileName = "";
		String subRptPath = "";
		String imgPath = "";
		
		Connection conn = null;
		
		try{
			
			conn = Utils.getConnection();
			
			String itemCond = " ' ItemNo in (" + itemNos + ") ' ";
			
			System.out.println("-----------itemCond-------->>>>>>>>>>"+itemCond);
			
			fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + rptName + ".jasper";
			subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
			imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
			
			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();

			parametersMap.put("itemCond", itemCond);
			parametersMap.put("imagepath", imgPath);
			parametersMap.put("subrptpath", subRptPath);
			
			JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
			
			if(rptName.equalsIgnoreCase("ExportJobCostingTag")){
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				File outputFile = new File("exportJobCostingTag.xlsx");
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
				retVal = "-6";
				
			}else{
	
				synchronized(this) {
					String commonFileName = System.currentTimeMillis()+"";
					File file = new File(uploadDirecotryPath + reportoutputdirectorypath + commonFileName+".pdf");
					file.createNewFile();
					JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + commonFileName+".pdf");
					retVal = "-1$"+commonFileName;
					JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + "tt.pdf");
				}
			}
			
		}catch(Exception e){
			System.out.println(e);
			retVal = "-2";
		}finally {
			conn.close();
		}
		
		
		
		return retVal;
	}
	
	
	
	
}

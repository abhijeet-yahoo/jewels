package com.jiyasoft.jewelplus.controller.manufacturing.transactions;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostingReportController {
	
	@Autowired
	private ICostingMtService costingMtService;
	
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
	
	
	@ModelAttribute("reportFilter")
	public ReportFilter constructReportFilter() {
		return new ReportFilter();
	}
	
	@ModelAttribute("costingMt")
	public CostingMt construct() {
		return new CostingMt();
	}
	
	
	
	
	@RequestMapping(value = "/costing/reports", method = RequestMethod.POST)
	@ResponseBody
	public String costingReport(@ModelAttribute("costingMt") CostingMt costingMt,
			@RequestParam(value = "invCost") String invCost,
			@RequestParam(value = "btnName") String btnName) throws SQLException{
		
		
		System.out.println("??????????????????????????????");
		System.out.println("-------invCost-------------"+invCost);
		System.out.println("-------btnName-------------"+btnName);
		System.out.println("-------tagColor-------------"+costingMt.getTagColor());
		
		CostingMt costingMtOld = costingMtService.findByInvNoAndDeactive(invCost, false);
		//costingMtService.save(costingMtOld);
		
		
		
		String retVal = "-1";
		String fileName = "";
		String subRptPath = "";
		String imgPath = "";
		String mtId = "";
		String xml="";
		
		Connection conn = null;
		try {
			
			
			mtId = " ' MtId in (" + costingMtOld.getId() + ") ";
			
			//if()
			
			//ReportFilter reportFilter = reportFilterService.findByName(reportFilterName);
			
			
			
			System.out.println("mtid-------->>>>>>>>>>>>>"+mtId);
			
			
			conn = Utils.getConnection();
			
			fileName = uploadDirecotryPath+reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml+ ".jrxml";
			
			subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
			
			imgPath = uploadDirecotryPath + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;

			
			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();

			parametersMap.put("mtId", mtId);
			parametersMap.put("imagepath", imgPath);
			parametersMap.put("subrptpath", subRptPath);
			
			
			JasperReport jasperreport = JasperCompileManager.compileReport(input);
			JasperPrint jp = JasperFillManager.fillReport(jasperreport,parametersMap, conn);
			JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + "tt.pdf");

		} catch (Exception e) {
			System.out.println(e);
			retVal = "-2";
		} finally {
			conn.close();
		}
		
		
		
		return retVal;
		
	}
	
	
	
	
	
	@RequestMapping(value = "/download/costingReports", method = RequestMethod.POST)
	public String downloadReport(HttpServletRequest request,
			HttpServletResponse response) {
		
		try{
		 
 		PrintWriter out = response.getWriter();  
 		String filename = "costingtt.pdf";   
 		
 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
 		
 		response.setContentType("APPLICATION/OCTET-STREAM");   
 		response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   

 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
 		
 		int i;   
 		while ((i=fileInputStream.read()) != -1) {  
 		out.write(i);   
 		}   
 		
 		fileInputStream.close();   
 		out.close();   
 		
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;

	}
	

}

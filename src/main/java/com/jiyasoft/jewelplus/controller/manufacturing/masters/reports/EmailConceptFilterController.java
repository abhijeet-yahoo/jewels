package com.jiyasoft.jewelplus.controller.manufacturing.masters.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmailConceptService;

@RequestMapping("/manufacturing/masters/reports")
@Controller
public class EmailConceptFilterController {
	
	@Autowired
	private IEmailConceptService emialConceptService;
	
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
	
	
	
	
	
	@RequestMapping("/generateReport/emailConceptReport")
	public String emailConceptrpt(){
		
		return "emailConceptRpt";
	}
	
	@RequestMapping(value="/generateEmailConceptReport", method = RequestMethod.POST)
	@ResponseBody
	public String generateReport(
			@RequestParam(value="pStartDate")String pStartDate,
			@RequestParam(value="pEndDate")String pEndDate,
			@RequestParam(value="pRptFormat")String pRptFormat,
			@RequestParam(value="pChoice")String pChoice,Principal principal) throws SQLException{
		
		String retVal="-1";
		
		//ReportFilter reportFilter= reportFilterService.findByName("emailConceptReport");
		String xml="";
		
		System.out.println("vvvv  "+pRptFormat);
		if(pRptFormat.equalsIgnoreCase("EmailConceptReport")){
			 xml="emailconcept";
		}else{
			 xml="emailconceptsummary";
		}
			
		System.out.println("XML  "+xml);
		Connection conn = null;

		try{
		
		
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");

		conn = Utils.getConnection();
		String fromDate="";
		String toDate="";
		if (pStartDate.length() > 0) {

			Date vFromDate = dfInput.parse(pStartDate);
			 fromDate = dfOutput.format(vFromDate);

			Date vToDate = dfInput.parse(pEndDate);
			toDate = dfOutput.format(vToDate);
		}
		
		
		String	fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") +xml+".jasper";
		String	subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
		String	imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
		String 	emailImgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/emailConcept/" ;
	
		InputStream input = new FileInputStream(new File(fileName));
		java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
		
		parametersMap.put("choiceCond","'"+pChoice+"'");
		parametersMap.put("startdate","'"+fromDate+"'");
		parametersMap.put("enddate","'"+toDate+"'");
		parametersMap.put("fromPeriod", pStartDate);
		parametersMap.put("toPeriod", pEndDate);
		parametersMap.put("imagepath", imgPath);
		parametersMap.put("emailimagepath", emailImgPath);
		parametersMap.put("subrptpath", subRptPath);
	
		
		JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
		
		
		String commonFileName = System.currentTimeMillis()+"";
		commonFileName = commonFileName+xml;
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath + commonFileName+".pdf");
		file.createNewFile();
		JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + commonFileName+".pdf");
		
		String exportCommonFileName = System.currentTimeMillis()+""+principal.getName()+xml;
		Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +commonFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportCommonFileName+".pdf");
		
		file.delete();
		
		retVal = exportCommonFileName;
		
		
		}catch (Exception e) {
			//File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
			//file.delete();
			System.out.println(e);
			retVal = "-5";
		} finally {
			conn.close();
		}

		return retVal;
	
		
		
		
	}
	
	

}

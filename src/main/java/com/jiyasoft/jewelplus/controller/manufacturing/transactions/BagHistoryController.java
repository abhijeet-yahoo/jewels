package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;





import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class BagHistoryController {
	
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;


	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;

	
	@RequestMapping("/bagHistory")
	public String bagHistory(){
		return "bagHistory";
	}
	
	
	
	@RequestMapping("/bagHistory/listing")
	@ResponseBody
	public String bagHistoryListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "bagId",required = false) Integer bagId, Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		
		System.out.println("in bag history controller");
		
		BagMt bagMt = bagMtService.findOne(bagId);
		List<TranMt> tranMts = tranMtService.findByBagMt(bagMt);

		sb.append("{\"total\":").append(bagMtService.count()).append(",\"rows\": [");

		for (TranMt tranMt : tranMts) {
			
			sb.append("{\"id\":\"")
					.append(tranMt.getId())
					.append("\",\"orderNo\":\"")
					.append(tranMt.getBagMt().getOrderMt().getInvNo())
					.append("\",\"bagNo\":\"")
					.append(tranMt.getBagMt().getName())
					.append("\",\"date\":\"")
					.append(tranMt.getDateStr())
					.append("\",\"department\":\"")
					.append((tranMt.getDepartment() != null ? tranMt.getDepartment().getName() : ""))
					.append("\",\"pcs\":\"")
					.append(tranMt.getPcs())
					.append("\",\"recievewt\":\"")
					.append(tranMt.getRecWt())
					.append("\",\"issuewt\":\"")
					.append(tranMt.getIssWt())
					.append("\",\"loss\":\"")
					.append(tranMt.getLoss())
					.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
		
		
	}
	
	@RequestMapping("/bagHistory/report")
	@ResponseBody
	public String bagHistory(@RequestParam(value = "bagNo", required = false) String bagNo, Principal principal,
			@RequestParam(value = "xml", required = false) String xml
			) throws SQLException {
		
		BagMt bagMt = bagMtService.findByName(bagNo);
	
		String retVal = "-1";
		String fileName = "";
		String subRptPath = "";
		String imgPath = "";
		
		Connection conn = null;
		
		
try {
	
	conn = Utils.getConnection();
	
	Integer bagCond = bagMt.getId();

	fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
	subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
	imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
	
	
	InputStream input = new FileInputStream(new File(fileName));
	java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
	
	parametersMap.put("bagCond", bagCond);
	parametersMap.put("imagepath", imgPath);
	parametersMap.put("subrptpath", subRptPath);
	
	JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
	
	//------------//--common--PDF--------//
	
	String commonFileName = System.currentTimeMillis()+"";
	commonFileName = commonFileName+xml;
	File file = new File(uploadDirecotryPath + reportoutputdirectorypath + commonFileName+".pdf");
	file.createNewFile();
	JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + commonFileName+".pdf");
	
	String exportCommonFileName = System.currentTimeMillis()+""+principal.getName()+xml;
	Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +commonFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportCommonFileName+".pdf");
	
	file.delete();
	
	retVal = exportCommonFileName;

	
} catch (Exception e) {
	// TODO: handle exception
}
finally {
	conn.close();
}	
	return retVal;

	}
	
	
	
	
	

}

package com.jiyasoft.jewelplus.controller.marketing.transactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.marketing.transactions.BarcodeExcel;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RequestMapping("/marketing/transactions")
@Controller
public class BarcodeReportController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	

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
	
	@RequestMapping("/barcodeReport")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("barcodeReport");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "barcodeReport";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "barcodeReport";
		}
	}
	
	

	@RequestMapping("/barcodeReport/uploadExcel")
	public String barcodeReportExcel(Model model) {
		model.addAttribute("tableDisp", "no");
		return "uploadExcelBarcode";
	}
	
	
	
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/barcodeFilter/excelUpload", method = RequestMethod.POST)
	@ResponseBody
	public String excelUpload(Model model,HttpSession httpSession,
			@RequestParam("file") MultipartFile excelfile,
			@RequestParam("tempExcelFile") String tempExcelFile){

		
		String retVal ="-1";
		
		try {
				if(!tempExcelFile.isEmpty()){
			
					List<BarcodeExcel> barcodeRptList = new ArrayList<BarcodeExcel>();
			    if (tempExcelFile.endsWith("xlsx")) {
			    	int i=1;
			    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			        XSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						
						BarcodeExcel barcodeRpt = new BarcodeExcel();
						
						XSSFRow row = worksheet.getRow(i++);
						
						if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
							continue;
						}
						
							barcodeRpt.setBarcode(row.getCell(0).toString().trim());
						//	barcodeRpt.setBagId((Integer) (bagMt != null ? bagMt.getId() :""));
								
						
						
						barcodeRptList.add(barcodeRpt);
					}
								
					
					
					
					workbook.close();
					
					
			    } else if (tempExcelFile.endsWith("xls")) {
			    	int i = 1;
					HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
					HSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						BarcodeExcel barcodeRpt = new BarcodeExcel();
						HSSFRow row = worksheet.getRow(i++);
						if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
							continue;
						}
					
						barcodeRpt.setBarcode(row.getCell(0).toString().trim());
						//	barcodeRpt.setBagId((Integer) (bagMt != null ? bagMt.getId() :""));
								
						
						
						barcodeRptList.add(barcodeRpt);
								
					}
					
					workbook.close();
									    
			    } else {
			        throw new IllegalArgumentException("The specified file is not Excel file");
			    }
			    
		
			
			    	
			    	 httpSession.setAttribute("barcodeSessionList", barcodeRptList);
			    
			    
			    			   
			    
			}
		}catch (IOException e) {
			retVal ="-2";	
			e.printStackTrace();
			}
		
		
		
		return retVal;
	}	
	
	
	
	
	@RequestMapping("/barcodeFilter/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		
		@SuppressWarnings("unchecked")
		List<BarcodeExcel> barcodeExcels = (List<BarcodeExcel>) httpSession.getAttribute("barcodeSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(barcodeExcels.size()).append(",\"rows\": [");
		

		
		for (BarcodeExcel barcodeExcel : barcodeExcels) {
			
			sb.append("{\"bagId\":\"")
			.append(barcodeExcel.getBagId() !=null? barcodeExcel.getBagId():"")
			.append("\",\"barcode\":\"")
			.append(barcodeExcel.getBarcode() !=null? barcodeExcel.getBarcode():"")
			
			.append("\"},");
			
			
		    
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
	
		
		return str;
	}
	
	
	@RequestMapping("/generateBarCodeReport")
	@ResponseBody
	public String generateBarCodeReport(@RequestParam(value = "pBagIds", required = false) String pBagIds,
			@RequestParam(value = "opt", required = false) String opt,
			Principal principal
			) throws SQLException {
		
				
		synchronized (this) {	
			String retVal = "-1";
			String fileName = "";
			String subRptPath = "";
			String imgPath = "";
			String barCodePath="";
			String bagCond = "";
			Connection conn = null;
			
			
			String xml = "";
			
			if(opt.equalsIgnoreCase("3")) {
				xml = "barcodeSticker2";
			}else {
				xml = "barcodeStoneDetailsReport";
			}
			
			
			
		try {
			
			
			conn = Utils.getConnection();
		
			if(pBagIds.length()>0){
				String vBarcode[] =pBagIds.split(",");
				for(int i=0;i<vBarcode.length;i++){
					if(bagCond.length()>0){
						bagCond=bagCond+",''"+vBarcode[i]+"''";
					}else{
						bagCond="''"+vBarcode[i]+"''";
					}
					
				}
				bagCond="'Barcode in("+bagCond+")'";
			}
			
			fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
			subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
			imgPath =     uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
			barCodePath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/StockBarcode/" ;
		
			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
			
			
			parametersMap.put("bagCond", bagCond);
			
			parametersMap.put("imagepath", imgPath);
			parametersMap.put("subrptpath", subRptPath);
			parametersMap.put("barCodePath", barCodePath);
			
			
			JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
				
			if(opt.equalsIgnoreCase("2")) {
				
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
			
			}else {
				
				String newFileName = System.currentTimeMillis()+"";
				File file = new File(uploadDirecotryPath + reportoutputdirectorypath + newFileName+".pdf");
				file.createNewFile();
				JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + newFileName+".pdf");
				
				String exportFileName = System.currentTimeMillis()+""+principal.getName();
				Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +newFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportFileName+".pdf");
				
				file.delete();
				
				retVal = exportFileName;
				
				
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

}

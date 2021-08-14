package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.jiyasoft.jewelplus.common.CommonUtils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CountryMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Ledger;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LedgerGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICountryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILedgerGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILedgerService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStateMasterService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;


@Controller
@RequestMapping("/manufacturing/masters")
public class LedgerController {

	
	@Autowired
	private ILedgerService ledgerService;
	
	@Autowired
	private ILedgerGroupService ledgerGroupService;
	
	
	
	@Autowired
	private ILookUpMastService lookUpService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private IStateMasterService stateMasterService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	private String uploadFilePath = "";

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;
	

	
	@ModelAttribute("ledger")
	public Party Constructor(){
		return new Party();
	}
	
	@RequestMapping("/ledger")
	public String ledger(Model model,HttpSession httpSession,Principal principal){
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("ledger");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "ledger";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
				
		return "ledger";
	}
	
	
	
	@RequestMapping("/ledger/listing")
	@ResponseBody
	public String ledgerListing(Model model,
			@RequestParam(value="limit",required =false) Integer limit,
			@RequestParam(value="offset",required = false) Integer offset,
			@RequestParam(value="sort",required = false) String sort,
			@RequestParam(value="order",required = false) String order,
			@RequestParam(value="search",required=false) String search,
			Principal principal,HttpSession httpSession){
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("ledger");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
	
		StringBuilder sb = new StringBuilder();
		Page<Party> ledgers = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		
		ledgers = ledgerService.searchAll(limit, offset, sort, order, search,  false);
	
		
		sb.append("{\"total\":").append(ledgers.getTotalElements()).append(",\"rows\": [");

		for (Party ledger : ledgers) {
		
				sb.append("{\"id\":\"")
					.append(ledger.getId())
					.append("\",\"name\":\"")
					.append(ledger.getName() !=null ? ledger.getName() : "")
					.append("\",\"code\":\"")
					.append(ledger.getPartyCode() !=null ? ledger.getPartyCode() : "")
					.append("\",\"transportArea\":\"")
					.append(ledger.getArea() !=null ? ledger.getArea() :"")
					.append("\",\"city\":\"")
					.append(ledger.getCity() !=null ? ledger.getCity() : "")
					.append("\",\"zip\":\"")
					.append(ledger.getZip() !=null ? ledger.getZip() : "" )
					.append("\",\"gstn\":\"")
					.append(ledger.getGst() !=null ? ledger.getGst() : "")
					.append("\",\"ledgerGroup\":\"")
					.append((ledger.getLedgerGroup() != null ? ledger.getLedgerGroup().getName() : ""))
					.append("\",\"nonEditable\":\"")
					.append(ledger.getNonEditable())
					.append("\",\"deactive\":\"")
					.append(ledger.getDeactive() != null ? (ledger.getDeactive() ? "Deactive":"Active") : "")
					.append("\",\"defaultFlag\":\"")
					.append((ledger.getDefaultFlag() == null ? "" : (ledger.getDefaultFlag() ? "Yes" : "No")))
					.append("\",\"exportClient\":\"")
					.append((ledger.getExportClient() == null ? "" : (ledger.getExportClient() ? "Yes" : "No")));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					sb.append("\",\"action1\":\"");
						sb.append("<a href='/jewels/manufacturing/masters/ledger/edit/")
							.append(ledger.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/ledger/delete/")
								.append(ledger.getId()).append(".html'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(ledger.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
		
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/ledger/edit/")
							.append(ledger.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/ledger/delete/")
								.append(ledger.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(ledger.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
		
				}
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;

		
	}
	
	
	@RequestMapping("/ledger/add")
	public String add(Model model,HttpSession httpSession,Principal principal){
	
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("ledger");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("ledgerGroupMap", ledgerGroupService.getLedgerGroupList());
		model.addAttribute("ledgerTypeMap", lookUpService.getActiveLookUpMastByType("Ledger Type"));
		model.addAttribute("transporterList", ledgerService.getTransporterList());
		model.addAttribute("countryMap", countryService.getCountryList());
		model.addAttribute("partyTypeList", CommonUtils.getPartyType());
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "ledger/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
				
		
		
	
		return "ledger/add";
	}
	
	
	
	
	@RequestMapping(value = "/ledger/add",method=RequestMethod.POST)
	public String addEdit(@Valid @ModelAttribute("ledger") Party ledger,
			@RequestParam(value = "id") Integer id,
			Principal principal,
			RedirectAttributes redirectAttributes,HttpSession httpSession,
			BindingResult result){
		
		String retVal="";			
	
		if(result.hasErrors()){
			return "ledger/add";
		}
		
		retVal = ledgerService.partySave(ledger, id, principal, httpSession);
		
		
		return retVal;
	}
	
	
	@RequestMapping("/ledger/edit/{id}")
	public String edit(@PathVariable int id, Model model,HttpSession httpSession,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("ledger");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		

		Party ledger = ledgerService.findOne(id);
		model.addAttribute("ledger", ledger);
		model.addAttribute("ledgerGroupMap", ledgerGroupService.getLedgerGroupList());
		model.addAttribute("ledgerTypeMap", lookUpService.getActiveLookUpMastByType("Ledger Type"));
		model.addAttribute("transporterList", ledgerService.getTransporterList());
		model.addAttribute("mastType", "ledger");
		model.addAttribute("mastformData", "ledgerForm");
	//	model.addAttribute("partyTypeList", CommonUtils.getPartyType());
		model.addAttribute("countryMap", countryService.getCountryList());	
		if(ledger.getCountry() != null){
		model.addAttribute("stateMap", stateMasterService.getStateListByCountry(ledger.getCountry().getId()));
		
		}
		model.addAttribute("partyTypeList", CommonUtils.getPartyType());
		model.addAttribute("mode", "edit");
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "ledger/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
			
		return "ledger/add";
	}

	
	@RequestMapping("/ledger/delete/{id}")
	public String delete(@PathVariable int id){
			
		ledgerService.delete(id);	
		return "redirect:/manufacturing/masters/ledger.html";
	}
	
	
	@RequestMapping("/ledgerAvailable")
	@ResponseBody
	public String ledgerAvailable(@RequestParam String name,
			@RequestParam Integer id,HttpSession httpSession) {
		
		Boolean ledgerAvailable = true;
		

		if (id == null) {
			ledgerAvailable = (ledgerService.findByNameAndDeactive(name, false) == null);
		} else {
			Party  ledger = ledgerService.findOne(id);
			if (!(name.equalsIgnoreCase(ledger.getName()))) {
				ledgerAvailable = (ledgerService.findByNameAndDeactive(name, false) == null);
			}
		}

		return ledgerAvailable.toString();
	}
	
	@RequestMapping("/ledgerCodeAvailable")
	@ResponseBody
	public String ledgerCodeAvailable(@RequestParam String code,
			@RequestParam Integer id,HttpSession httpSession) {
		
		Boolean ledgerAvailable = true;
		

		if (id == null) {
			ledgerAvailable = (ledgerService.findByNameAndDeactive(code, false) == null);
		} else {
			Party ledger = ledgerService.findOne(id);
			if (!(code.equalsIgnoreCase(ledger.getPartyCode()))) {
				ledgerAvailable = (ledgerService.findByNameAndDeactive(code, false) == null);
			}
		}

		return ledgerAvailable.toString();
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/ledger/tranSetting/listing")
	@ResponseBody
	public String ledgerTranSettingList(){
		
		StringBuilder sb = new StringBuilder();
		
		String name = Ledger.class.getName();
		try {
			Class cls = Class.forName(name);
			sb.append("{\"total\":").append(10000).append(",\"rows\": [");	
			
			Field []  field= cls.getDeclaredFields();
			
			for(Field field2 : field){
				sb.append("{\"name\":\"")
				.append(field2.getName().substring(0, 1).toUpperCase()+field2.getName().substring(1))
				.append("\"},");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
		
	
	
	// upload excel code start here....
	
	@RequestMapping("/ledger/downloadExcel/format")
	@ResponseBody
	public String excelFormatLedgerDownload(
			@RequestParam(value = "headingVal") String headingVal,Principal principal){
		
		String retVal = "-1";
		String fileName = principal.getName()+new java.util.Date().getTime()+".xls";
		String filePath = uploadDirecotryPath + File.separator +"excelfilecontent" + File.separator;
		String tempHeadVal[] = headingVal.split(",");
		
		 try {
	            String filename = filePath+fileName;
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            HSSFSheet sheet = workbook.createSheet("FirstSheet");  

	            HSSFRow rowhead = sheet.createRow((short)0);
	            for(int i=0;i<tempHeadVal.length;i++){
	            	 rowhead.createCell(i).setCellValue(tempHeadVal[i].toString());
	            }
	            
	            FileOutputStream fileOut = new FileOutputStream(filename);
	            workbook.write(fileOut);
	            fileOut.close();
	            workbook.close();
	            retVal = fileName;
	        } catch ( Exception ex ) {
	            System.out.println(ex);
	            retVal = "-2";
	        }
		
		return retVal;
	}
	
	
	//---------download excel format ---//
	
	@RequestMapping("/ledger/download/excelformat")
	public String excelBlankLedgerFormat(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "fileName") String fileName) {
		
		try{
			
	 		PrintWriter out = response.getWriter();  
	 		String filename = fileName;
	 		String filepath = uploadDirecotryPath +  File.separator +"excelfilecontent" + File.separator;
	 		response.setContentType("application/vnd.ms-excel"); 
	 		  
	 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");  
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
		
		
		File file = new File(uploadDirecotryPath +  File.separator +"excelfilecontent" + File.separator + fileName);
		file.delete();
		
		return null;

	}
	
	

	@RequestMapping(value = "/ledger/commonExcelUpload", method = RequestMethod.POST)
	@ResponseBody
	public String excelUploadLedger(Model model,
			HttpSession httpSession,
			@RequestParam("file") MultipartFile excelfile,
			@RequestParam("tempExcelFile") String tempExcelFile){
		
		try {
			
			
			List<Party> ledgerList = new ArrayList<Party>();
			    if (tempExcelFile.endsWith("xlsx")) {
			    	int i=1;
			    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			        XSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						Party ledger = new Party();
						XSSFRow row = worksheet.getRow(i++);
					
						for(int j=0;j<row.getLastCellNum();j++){
							Cell cell = row.getCell(j);
							if(cell !=null){
						    cell.setCellType (Cell.CELL_TYPE_STRING);
							}
						    j++;
						}
					
						
						String remark = "successfull";
						String status = "verified";
						
						if(row.getCell(0) == null){
							remark = "Name Compulsory";
							status = "verification failed";
						}
						
						if(row.getCell(1) == null){
							remark = "Mailing Name Compulsory";
							status = "verification failed";
						}
                        
						
						if(row.getCell(2) == null){
							remark = "Ledger Group Compulsory";
							status = "verification failed";
						}else{
							List<String> ledgerGroupList = ledgerGroupService.getLedgerGroupNmList();
							for(String ledgerGrp:ledgerGroupList){
								
								if(ledgerGrp.toString().trim().equalsIgnoreCase(row.getCell(2).toString().trim())){
									remark = "successfull";
									status = "verified";
									break;
								}else {
									remark = "No such Ledger Group exists";
									status = "verification failed";
								}
							
							}
						}
						
						
						if(row.getCell(28) == null){
							remark = "Ledger Code Compulsory";
							status = "verification failed";
						}
						
						if(row.getCell(2) !=null){
							LedgerGroup ledgerGroup = ledgerGroupService.findByName(row.getCell(2).toString());
						
						
						ledger.setName(row.getCell(0) !=null?row.getCell(0).toString():"");
						ledger.setMailingName(row.getCell(1) !=null?row.getCell(1).toString():"");
						ledger.setLedgerGroup(ledgerGroup);
						//ledger.setType(row.getCell(3)!=null?row.getCell(3).toString():"");
						ledger.setContactPerson(row.getCell(4)!=null?row.getCell(4).toString():"");
						ledger.setAddress(row.getCell(5)!=null?row.getCell(5).toString():"");
						ledger.setArea(row.getCell(6)!=null?row.getCell(6).toString():"");
						ledger.setZone(row.getCell(7)!=null?row.getCell(7).toString():"");
						ledger.setCity(row.getCell(8)!=null?row.getCell(8).toString():"");
						ledger.setZip(row.getCell(9)!=null?row.getCell(9).toString():"");
					//	ledger.setState(row.getCell(10)!=null?row.getCell(10).toString():"");
					//	ledger.setCountry(row.getCell(11)!=null?row.getCell(11).toString():"");
						ledger.setOfficePhone(row.getCell(12)!=null?row.getCell(12).toString():"");
						ledger.setFax(row.getCell(13)!=null?row.getCell(13).toString():"");
						ledger.setWebSite(row.getCell(14)!=null?row.getCell(14).toString():"");
						ledger.setMobile(row.getCell(15)!=null?row.getCell(15).toString():"");
						ledger.setEmailAddress(row.getCell(16)!=null?row.getCell(16).toString():"");
						ledger.setSalesMan(row.getCell(17)!=null?row.getCell(17).toString():"");
					//	ledger.setBranch(row.getCell(18)!=null?row.getCell(18).toString():"");
					//	ledger.setAccountNo(row.getCell(19)!=null?row.getCell(19).toString():"");
					//	ledger.setAccountHolderName(row.getCell(20)!=null?row.getCell(20).toString():"");
					//	ledger.setBsrCode(row.getCell(21)!=null?row.getCell(21).toString():"");
					//	ledger.setIfsCode(row.getCell(22)!=null?row.getCell(22).toString():"");
						ledger.setCreditDays(row.getCell(23)!=null?row.getCell(23).toString():"");
                        ledger.setCreditLimit(row.getCell(24)!=null?Double.parseDouble(row.getCell(24).toString()):0);
						ledger.setGst(row.getCell(25)!=null?row.getCell(25).toString():"");
						ledger.setPanNo(row.getCell(26)!=null?row.getCell(26).toString():"");
						ledger.setDeactive(Boolean.parseBoolean(row.getCell(27) != null ?  row.getCell(27).toString() : "FALSE" ));
						ledger.setPartyCode(row.getCell(28)!=null?row.getCell(28).toString():"");
						
						if(row.getCell(0) !=null){
						Party ledgerNmCheck = ledgerService.findByNameAndDeactive(row.getCell(0).toString(), false);
						if(ledgerNmCheck != null){
							remark = "Duplicate Record";
							status = "verification failed";
						}
						}
						
						if(row.getCell(1) !=null){
                     
						Party ledgerMailNmCheck = ledgerService.findByMailingNameAndDeactive(row.getCell(1).toString(), false);
						if(ledgerMailNmCheck != null){
							remark = "Duplicate Record";
							status = "verification failed";
						}
						
						}
						
						if(row.getCell(28) !=null){
		                     
							Party ledgerCodeCheck = ledgerService.findByPartyCodeAndDeactive(row.getCell(28).toString(), false);
							if(ledgerCodeCheck != null){
								remark = "Duplicate Record";
								status = "verification failed";
							}
							
							}
						
						ledger.setModiBy(status);
						ledger.setCreatedBy(remark);
						ledgerList.add(ledger);
					}
					
					}
					workbook.close();
					
			    } else if (tempExcelFile.endsWith("xls")) {
			    	int i = 1;
					HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
					HSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						Party ledger = new Party();
						HSSFRow row = worksheet.getRow(i++);
					
						for(int j=0;j<row.getLastCellNum();j++){
							
							Cell cell = row.getCell(j);
							if(cell !=null){
						    cell.setCellType (Cell.CELL_TYPE_STRING);
							}
						    j++;
						}
						
						String remark = "successfull";
						String status = "verified";
						
						if(row.getCell(0) == null){
							remark = "Name Compulsory";
							status = "verification failed";
						}
						
						if(row.getCell(1) == null){
							remark = "Mailing Name Compulsory";
							status = "verification failed";
						}
                        						
						
						if(row.getCell(2) == null){
							remark = "Ledger Group Compulsory";
							status = "verification failed";
						}else{
							List<String> ledgerGroupList = ledgerGroupService.getLedgerGroupNmList();
							for(String ledgerGrp:ledgerGroupList){
								
								if(ledgerGrp.toString().trim().equalsIgnoreCase(row.getCell(2).toString().trim())){
									remark = "successfull";
									status = "verified";
									break;
								}else {
									remark = "No such Ledger Group exists";
									status = "verification failed";
								}
							
							}
						}
						
						if(row.getCell(28) == null){
							remark = "Ledger Code Compulsory";
							status = "verification failed";
						}
						
		
						if(row.getCell(2) !=null){
							LedgerGroup ledgerGroup = ledgerGroupService.findByName(row.getCell(2).toString());
						
						
						ledger.setName(row.getCell(0) !=null?row.getCell(0).toString():"");
						ledger.setMailingName(row.getCell(1) !=null?row.getCell(1).toString():"");
						ledger.setLedgerGroup(ledgerGroup);
						//ledger.setType(row.getCell(3)!=null?row.getCell(3).toString():"");
						ledger.setContactPerson(row.getCell(4)!=null?row.getCell(4).toString():"");
						ledger.setAddress(row.getCell(5)!=null?row.getCell(5).toString():"");
						ledger.setArea(row.getCell(6)!=null?row.getCell(6).toString():"");
						ledger.setZone(row.getCell(7)!=null?row.getCell(7).toString():"");
						ledger.setCity(row.getCell(8)!=null?row.getCell(8).toString():"");
						ledger.setZip(row.getCell(9)!=null?row.getCell(9).toString():"");
					//	ledger.setState(row.getCell(10)!=null?row.getCell(10).toString():"");
		//				ledger.setCountry(row.getCell(11)!=null?row.getCell(11).toString():"");
						ledger.setOfficePhone(row.getCell(12)!=null?row.getCell(12).toString():"");
						ledger.setFax(row.getCell(13)!=null?row.getCell(13).toString():"");
						ledger.setWebSite(row.getCell(14)!=null?row.getCell(14).toString():"");
						ledger.setMobile(row.getCell(15)!=null?row.getCell(15).toString():"");
						ledger.setEmailAddress(row.getCell(16)!=null?row.getCell(16).toString():"");
						ledger.setSalesMan(row.getCell(17)!=null?row.getCell(17).toString():"");
					//	ledger.setBranch(row.getCell(18)!=null?row.getCell(18).toString():"");
					//	ledger.setAccountNo(row.getCell(19)!=null?row.getCell(19).toString():"");
					//	ledger.setAccountHolderName(row.getCell(20)!=null?row.getCell(20).toString():"");
					//	ledger.setBsrCode(row.getCell(21)!=null?row.getCell(21).toString():"");
					//	ledger.setIfsCode(row.getCell(22)!=null?row.getCell(22).toString():"");
						//String crDays =String.valueOf(row.getCell(23).toString());
						ledger.setCreditDays(row.getCell(23)!=null?row.getCell(23).toString():"");
                        ledger.setCreditLimit(row.getCell(24)!=null?Double.parseDouble(row.getCell(24).toString()):0);
						ledger.setGst(row.getCell(25)!=null?row.getCell(25).toString():"");
						ledger.setPanNo(row.getCell(26)!=null?row.getCell(26).toString():"");
						ledger.setDeactive(Boolean.parseBoolean(row.getCell(27) != null ?  row.getCell(27).toString() : "FALSE" ));
						ledger.setPartyCode(row.getCell(28)!=null?row.getCell(28).toString():"");
						
						if(row.getCell(0) !=null){
						Party ledgerNmCheck = ledgerService.findByNameAndDeactive(row.getCell(0).toString(),false);
						if(ledgerNmCheck != null){
							remark = "Duplicate Record";
							status = "verification failed";
						}
						}
						
						if(row.getCell(1) !=null){
                     
						Party ledgerMailNmCheck = ledgerService.findByMailingNameAndDeactive(row.getCell(1).toString(), false);
						if(ledgerMailNmCheck != null){
							remark = "Duplicate Record";
							status = "verification failed";
						}
						
						}
						
						if(row.getCell(28) !=null){
		                     
							Party ledgerCodeCheck = ledgerService.findByPartyCodeAndDeactive(row.getCell(28).toString(), false);
							if(ledgerCodeCheck != null){
								remark = "Duplicate Record";
								status = "verification failed";
							}
							
							}
						
						ledger.setModiBy(status);
						ledger.setCreatedBy(remark);
						ledgerList.add(ledger);
					}
					
					}
					workbook.close();
					
			    } else {
			        throw new IllegalArgumentException("The specified file is not Excel file");
			    }
			    
			    
			    httpSession.setAttribute("ledgerSessionList", ledgerList);
			  
			    
			}catch (IOException e) {
				e.printStackTrace();
			}
		
		return "2";
	}
	
	
	
	

	@RequestMapping("/ledger/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<Party> ledgers = (List<Party>) httpSession.getAttribute("ledgerSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(1).append(",\"rows\": [");
		
		for(Party ledger:ledgers){
			
			sb.append("{\"id\":\"")
				.append(ledger.getId())
				.append("\",\"name\":\"")
				.append(ledger.getName() != null ? ledger.getName() : "")
				.append("\",\"mailingNm\":\"")
				.append(ledger.getMailingName() != null ? ledger.getMailingName() : "")
				.append("\",\"code\":\"")
				.append(ledger.getPartyCode() != null ? ledger.getPartyCode() : "")
				.append("\",\"ledgerGroupNm\":\"")
				.append(ledger.getLedgerGroup() != null ? ledger.getLedgerGroup().getName() : "")
				.append("\",\"type\":\"")
				.append(ledger.getType() != null ? ledger.getType() : "")
				.append("\",\"person\":\"")
				.append(ledger.getContactPerson() != null ? ledger.getContactPerson() : "")
				.append("\",\"address\":\"")
				.append(ledger.getAddress() != null ? ledger.getAddress() : "")
				.append("\",\"area\":\"")
				.append(ledger.getArea() != null ? ledger.getArea() : "")
				.append("\",\"zone\":\"")
				.append(ledger.getZone() != null ? ledger.getZone() : "")
				.append("\",\"city\":\"")
				.append(ledger.getCity() != null ? ledger.getCity() : "")
				.append("\",\"zip\":\"")
				.append(ledger.getZip() != null ? ledger.getZip() : "")
				.append("\",\"state\":\"")
				.append("\",\"phone\":\"")
				.append(ledger.getOfficePhone() != null ? ledger.getOfficePhone() : "")
				.append("\",\"fax\":\"")
				.append(ledger.getFax() != null ? ledger.getFax() : "")
				.append("\",\"website\":\"")
				.append(ledger.getWebSite() != null ? ledger.getWebSite() : "")
				.append("\",\"mobile\":\"")
				.append(ledger.getMobile() != null ? ledger.getMobile() : "")
				.append("\",\"email\":\"")
				.append(ledger.getEmailAddress() != null ? ledger.getEmailAddress() : "")
				.append("\",\"salesMan\":\"")
				.append("\",\"creditDays\":\"")
				.append(ledger.getCreditDays() != null ? ledger.getCreditDays() : "")
				.append("\",\"creditLimit\":\"")
				.append(ledger.getCreditLimit() != null ? ledger.getCreditLimit() : "")
				.append("\",\"gstn\":\"")
				.append(ledger.getGst() != null ? ledger.getGst() : "")
				.append("\",\"pan\":\"")
				.append(ledger.getPanNo() != null ? ledger.getPanNo() : "")
				.append("\",\"deactive\":\"")
				.append((ledger.getDeactive() == null ? "true" : ledger.getDeactive()))
				.append("\",\"status\":\"")
			    .append(ledger.getModiBy() != null ? ledger.getModiBy() : "not perfect")
			    .append("\",\"remark\":\"")
			    .append((ledger.getCreatedBy() != null ? ledger.getCreatedBy() : "unknown error"))
				.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	
	@RequestMapping(value = "/ledger/excelSave" , method = RequestMethod.POST)
	@ResponseBody
	public String saveExcelData(
			@ModelAttribute("ledger") Party ledger,
			@RequestParam(value ="tableJsonData") String tableJsonData,
			Principal principal,HttpSession httpSession){
		
		String retVal = "-1";
		
		JSONArray jsonDt = new JSONArray(tableJsonData);
		
		for(int i=0;i<jsonDt.length();i++){
			JSONObject jsonDt1 = (JSONObject) jsonDt.get(i);
			if(jsonDt1.getString("status").toString().equalsIgnoreCase("verification failed")){
				return retVal;
			}
		}
		
		
		for(int i=0;i<jsonDt.length();i++){
			JSONObject jsonDtNew = (JSONObject) jsonDt.get(i);
			Party ledgerNew = new Party(); 
			
			LedgerGroup ledgerGroup = ledgerGroupService.findByName(jsonDtNew.getString("ledgerGroupNm"));
			
			ledgerNew.setPartyCode(jsonDtNew.getString("code"));
			ledgerNew.setName(jsonDtNew.getString("name"));
			ledgerNew.setMailingName(jsonDtNew.getString("mailingNm"));
			ledgerNew.setLedgerGroup(ledgerGroup);
			ledgerNew.setContactPerson(jsonDtNew.getString("person"));
			ledgerNew.setAddress(jsonDtNew.getString("address"));
			ledgerNew.setArea(jsonDtNew.getString("area"));
			ledgerNew.setZone(jsonDtNew.getString("zone"));
			ledgerNew.setCity(jsonDtNew.getString("city"));
			ledgerNew.setZip(jsonDtNew.getString("zip"));
			ledgerNew.setOfficePhone(jsonDtNew.getString("phone"));
			ledgerNew.setFax(jsonDtNew.getString("fax"));
			ledgerNew.setWebSite(jsonDtNew.getString("website"));
			ledgerNew.setMobile(jsonDtNew.getString("mobile"));
			ledgerNew.setEmailAddress(jsonDtNew.getString("email"));
			ledgerNew.setSalesMan(jsonDtNew.getString("salesMan"));
			ledgerNew.setCreditDays(jsonDtNew.getString("creditDays"));
			ledgerNew.setCreditLimit(Double.parseDouble(jsonDtNew.getString("creditLimit")));
			ledgerNew.setGst(jsonDtNew.getString("gstn"));
			ledgerNew.setPanNo(jsonDtNew.getString("pan"));
			ledgerNew.setDeactive(Boolean.parseBoolean(jsonDtNew.getString("deactive")));
			ledgerNew.setCreateDt(new  java.util.Date());
			ledgerNew.setCreatedBy(principal.getName());
			ledgerService.save(ledgerNew);
			retVal = "-2";
		}
		
		
		return retVal;
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/ledger/partyType")
	public String ledPartyType(
			@RequestParam(value = "partyNm") String partyNm,
			HttpSession httpSession){
		
				
		String retVal = "-1";
		
		Party ledger = ledgerService.findByNameAndDeactive(partyNm, false);
		
		if(ledger != null){
			retVal = ledger.getPartyType();
		}
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/ledger/autoFillValidation")
	public String autoFillValidation(@RequestParam(value="name") String name,
			@RequestParam(value="voucherCode") String voucherCode,HttpSession httpSession){
		Boolean retVal = true;
		
		Party ledger = ledgerService.findByNameAndDeactive(name, false);
		
		if(ledger == null){
			retVal = false;
		}
		
		return retVal.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("/popUp/ledger/listing")
	public String popUpLedgerListing(Model model,Principal principal,HttpSession httpSession,
			@RequestParam(value="ledgerGroupCond") String ledgerGroupCond) {
		
		List<Integer> vLedgerGroup = new ArrayList<Integer>();
		if(ledgerGroupCond.length()>0){
			String temp[]=ledgerGroupCond.split(",");
			for(int i=0;i<temp.length;i++){
				vLedgerGroup.add(Integer.parseInt(temp[i]));
			}
		}
		
		

		StringBuilder sb = new StringBuilder();
		
		QParty qLedger = QParty.party;
		JPAQuery query = new JPAQuery(entityManager);
		List<Tuple> ledgerDetails = null;
		if (ledgerGroupCond !=""  ) {
					
			ledgerDetails = query.from(qLedger)
					.where(qLedger.deactive.eq(false).and(qLedger.ledgerGroup.id.in(vLedgerGroup)))
					.orderBy(qLedger.name.asc()).list(qLedger.id, qLedger.name);
		}else{
			

			ledgerDetails = query.from(qLedger)
					.where(qLedger.deactive.eq(false))
					.orderBy(qLedger.name.asc()).list(qLedger.id, qLedger.name);
		}
		
		sb.append("{\"total\":").append(ledgerDetails.size()).append(",\"rows\": [");
		
		for (Tuple row:ledgerDetails) {
			sb.append("{\"id\":\"")
				.append(row.get(qLedger.id))
				.append("\",\"name\":\"")
				.append(row.get(qLedger.name))
				.append("\"},");
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	@ResponseBody
	@RequestMapping("/ledger/masterAuotFill")
	public String masterAutoFill(Model model,Principal principal,HttpSession httpSession,
			@RequestParam(value="term") String partyNm) {
		
		
		
		Page<Party> ledgers = ledgerService.searchAll(100, 0, "name", "ASC", partyNm, true);
		
		StringBuilder sb = new StringBuilder();

		for (Party ledger : ledgers) {
			sb.append("\"")
				.append(ledger.getName())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
		
		
	}
	
	@RequestMapping("/ledger/listing/addNewBlankRow")
	@ResponseBody
	public String partyBankNewBlankRow(Model model, HttpSession httpSession){
		
	
		StringBuilder sb = new StringBuilder();
		
		List<String> banks = new ArrayList<String>();
		banks.add("0");
		

		sb.append("{\"total\":").append(banks.size()).append(",\"data\": [");
		
		for (String partyBank : banks) {
			
			sb.append("{\"partyBankId\":\"")
				.append(partyBank)
				.append("\",\"srNo\":\"")
				.append("1")
				.append("\",\"bank\":\"")
				.append("")
				.append("\",\"branch\":\"")
				.append("")
				.append("\",\"accountNo\":\"")
				.append("")
				.append("\",\"accountHolderName\":\"")
				.append("")
				.append("\",\"bsrCode\":\"")
				.append("")
				.append("\",\"ifsCode\":\"")
				.append("")
				.append("\"},");
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		
	
		
	
		
		StringBuilder dataStructure = new StringBuilder();
		
		dataStructure.append(str);
		dataStructure.append("],\"datatype\":[");
		
		dataStructure.append("{\"key\":\"")
		
			.append("srNo")
			.append("\",\"type\":\"")
			.append("number\"")			
			.append("},{")			

			.append("\"key\":\"")
			.append("bank")
			.append("\",\"type\":\"")
			.append("text")	
			.append("\",\"onChangeFun\":\"")
			.append("upperCase(this,\"")
			.append("},{")
			
			.append("\"key\":\"")
			.append("branch")
			.append("\",\"type\":\"")
			.append("text")	
			.append("\",\"onChangeFun\":\"")
			.append("upperCase(this,\"")
			.append("},{")
			
			
			.append("\"key\":\"")
			.append("accountNo")
			.append("\",\"type\":\"")
			.append("text")	
			.append("\",\"onChangeFun\":\"")
			.append("upperCase(this,\"")
			.append("},{")
			
			.append("\"key\":\"")
			.append("accountHolderName")
			.append("\",\"type\":\"")
			.append("text")	
			.append("\",\"onChangeFun\":\"")
			.append("upperCase(this,\"")
			.append("},{")
			
			.append("\"key\":\"")
			.append("bsrCode")
			.append("\",\"type\":\"")
			.append("text")	
			.append("\",\"onChangeFun\":\"")
			.append("upperCase(this,\"")
			.append("},{")
			
			.append("\"key\":\"")
			.append("ifsCode")
			.append("\",\"type\":\"")
			.append("text")	
			.append("\",\"onChangeFun\":\"")
			.append("upperCase(this,\"")	
			.append("}");
			
		/*.append("},{")
			.append("\"key\":\"")
			.append("runningNo")
			.append("\",\"type\":\"")
			.append("text\"")*/
			
		
		dataStructure.append("],");
		String strDataStruct = dataStructure.toString();
		
		strDataStruct += "\"target\":\"save\"}";
		
		return strDataStruct;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/country/multiselect")
	public String countryAutoFill(Model model,Principal principal,HttpSession httpSession,
			@RequestParam(value="name") String name) {
		
		
		Page<CountryMaster> countrys = countryService.countryAutoFillList(25, 0, "name", "ASC", name, true);
		
		StringBuilder sb = new StringBuilder();

		for (CountryMaster countryMaster : countrys) {
			sb.append("{\"id\":\"")
			.append(countryMaster.getId())
			.append("\",\"name\":\"")
			.append(countryMaster.getName())
			.append("\"},");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	@ResponseBody
	 @RequestMapping("/legder/multiselectCity")
	 public String multiSelectCityForFG(
			 @RequestParam(value = "cityNm") String cityNm,HttpSession httpSession){
		
		 StringBuilder sb = new StringBuilder();
		// List<Ledger> ledgers = ledgerService.findDistinctByCity(cityNm); 
		 
		 List<Ledger> ledgers=null;
			
			@SuppressWarnings("unchecked")
			TypedQuery<Ledger> query =  (TypedQuery<Ledger>)entityManager.createNativeQuery("select * from ledgerMast"
							+ " where city like '"+cityNm+"%' and deactive =0 group by city ",Ledger.class);
			
			ledgers = query.getResultList();
		 
		 
	 sb.append("{\"list\": [");
				
				for (Ledger ledger:ledgers) {
					sb.append("{\"Id\":\"")
						.append(ledger.getId())
						.append("\",\"city\":\"")
						.append(ledger.getCity())
						.append("\"},");
				}
				
				
				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				return str;
			 
		 }
	
	
	@ResponseBody
	 @RequestMapping("/legder/multiselectDestLandmark")
	 public String multiSelectDestLandmark(
			 @RequestParam(value = "landmark") String landmark,
			 @RequestParam(value = "city") String city,
			 HttpSession httpSession){
		
		 StringBuilder sb = new StringBuilder();
		// List<Ledger> ledgers = ledgerService.findDistinctByCity(cityNm); 
		 
		 List<Ledger> ledgers=null;
			
			@SuppressWarnings("unchecked")
			TypedQuery<Ledger> query =  (TypedQuery<Ledger>)entityManager.createNativeQuery("select * from ledgerMast"
							+ " where trandest like '"+landmark+"%' and city ='"+city+"' and deactive =0 ",Ledger.class);
			
			ledgers = query.getResultList();
		 
		 
	 sb.append("{\"list\": [");
				
				for (Ledger ledger:ledgers) {
					sb.append("{\"Id\":\"")
						.append(ledger.getId())
						.append("\",\"landmark\":\"")
						.append(ledger.getTranDest())
						.append("\"},");
				}
				
				
				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				return str;
			 
		 }
	
	
	
}

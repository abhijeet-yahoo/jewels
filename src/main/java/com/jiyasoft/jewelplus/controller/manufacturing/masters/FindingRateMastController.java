package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;


@Controller
@RequestMapping("/manufacturing/masters")
public class FindingRateMastController {
	
	@Autowired
	private IFindingRateMastService findingRateMastService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IPurityService purityService; 
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleRightsService roleRightService;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;
	
	@ModelAttribute("findingRateMast")
	public FindingRateMast construct() {
		return new FindingRateMast();
	}

	@RequestMapping("/findingRateMast")
	public String users(Model model, Principal principal) {
	
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("findingRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "findingRateMast";
			
		}else
			
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "findingRateMast";
	}
	
	
	@RequestMapping("/findingRateMast/listing")
	@ResponseBody
	public String findingRateMastListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

	    StringBuilder sb = new StringBuilder();
		Page<FindingRateMast> findingRateMasts = null;
		
		//list code 3 line--//
		
		/*String menuNm = "";
		if(flag.equalsIgnoreCase("exportClient")){
			menuNm = "findingRateMast";
		}else{
			menuNm = "findingRateVendorMast";
		}*/
		
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("findingRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
	//	Long rowCount = null;
		
		//System.err.println("flag == "+flag);
		

		findingRateMasts = findingRateMastService.searchAll(limit, offset, sort, order, search, true);
	//	rowCount = findingRateMastService.countAll(sort, search, true);


		sb.append("{\"total\":").append(findingRateMasts.getTotalElements()).append(",\"rows\": [");
		
		for (FindingRateMast findingRateMast : findingRateMasts) {
			
			sb.append("{\"id\":\"")
					.append(findingRateMast.getId())
					.append("\",\"party\":\"")
					.append(findingRateMast.getParty().getPartyCode())
					.append("\",\"purity\":\"")
					.append(findingRateMast.getPurity().getName())
					.append("\",\"component\":\"")
					.append(findingRateMast.getComponent().getName())
					.append("\",\"perGramRate\":\"")
					.append(findingRateMast.getPerGramRate())
					.append("\",\"perPcRate\":\"")
					.append(findingRateMast.getPerPcRate())
					.append("\",\"rate\":\"")
					.append(findingRateMast.getRate())
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/masters/findingRateMast/edit/")
					.append(findingRateMast.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/findingRateMast/delete/")
					.append(findingRateMast.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(findingRateMast.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"")
						.append("<a href='/jewels/manufacturing/masters/findingRateMast/edit/")
						.append(findingRateMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/findingRateMast/delete/")
							.append(findingRateMast.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(findingRateMast.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
		
				
			}else{
				
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/findingRateMast/edit/")
						.append(findingRateMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/findingRateMast/delete/")
							.append(findingRateMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(findingRateMast.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
	
			}
			
			
						
		
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		System.out.println("str = "+str);
		
		return str;

	}
	
	
	
	
	
	@RequestMapping("/findingRate/customSearch/listing")
	@ResponseBody
	public String findingRateCustomListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "partyCode") String partyCode,
			@RequestParam(value = "purityName") String purityName,
			@RequestParam(value = "findingName") String findingName,
			Principal principal) {
		
		
		
		StringBuilder sb = new StringBuilder();
		Page<FindingRateMast> findingRateMasts = null;
		Long rowCount = null;

		//list code 3 line--//
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("findingRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		rowCount = findingRateMastService.customSearchCount(partyCode, purityName, findingName);
		findingRateMasts = findingRateMastService.customSearch(limit, offset, sort, order, partyCode, purityName, findingName);
		
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
		
		for (FindingRateMast findingRateMast : findingRateMasts) {
			
			sb.append("{\"id\":\"")
					.append(findingRateMast.getId())
					.append("\",\"party\":\"")
					.append(findingRateMast.getParty().getPartyCode())
					.append("\",\"purity\":\"")
					.append(findingRateMast.getPurity().getName())
					.append("\",\"component\":\"")
					.append(findingRateMast.getComponent().getName())
					.append("\",\"perGramRate\":\"")
					.append(findingRateMast.getPerGramRate())
					.append("\",\"perPcRate\":\"")
					.append(findingRateMast.getPerPcRate())
					.append("\",\"rate\":\"")
					.append(findingRateMast.getRate())
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/masters/findingRateMast/edit/")
					.append(findingRateMast.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/findingRateMast/delete/")
					.append(findingRateMast.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(findingRateMast.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"")
						.append("<a href='/jewels/manufacturing/masters/findingRateMast/edit/")
						.append(findingRateMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/findingRateMast/delete/")
							.append(findingRateMast.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(findingRateMast.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
		
				
			}else{
				
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/findingRateMast/edit/")
						.append(findingRateMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/findingRateMast/delete/")
							.append(findingRateMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(findingRateMast.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
	
			}
			
			
						
		
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		System.out.println("str = "+str);
		
		return str;

	}
	

	
	public Model populateModel(Model model) {
		model.addAttribute("partyMap",partyService.getPartyList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("componentMap", componentService.getComponentList());
		return model;
	}
	
	
	@RequestMapping("/findingRateMast/add")
	public String add(Model model, Principal principal) {
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("findingRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "findingRateMast/add";

		}else	
		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}
				return "findingRateMast/add";
	}

	
	public Model popRoleRightModel(Model model,Principal principal){
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("findingRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("canAdd", roleRights.getCanAdd());
		model.addAttribute("canDelete", roleRights.getCanDelete());
		model.addAttribute("canEdit", roleRights.getCanEdit());
		
		return model;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/findingRateMast/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("findingRateMast") FindingRateMast findingRateMast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/findingRateMast/add.html?success=true";

		if (result.hasErrors()) {
			return "findingRateMast/add";
		}
		
		DecimalFormat df = new DecimalFormat("#.##");
		FindingRateMast  findingRateMastValidate = null;
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			findingRateMastValidate = findingRateMastService.findByPartyAndComponentAndPurityAndDeactive(findingRateMast.getParty(), 
					findingRateMast.getComponent(), findingRateMast.getPurity(), false);
			
			if(findingRateMastValidate != null){
				return retVal = "-1";
			}
			
			if(findingRateMast.getPerGramRate() == true && findingRateMast.getPerPcRate() == true){
				return retVal = "-2";
			}else if (findingRateMast.getPerGramRate() == false && findingRateMast.getPerPcRate() == false) {
				return retVal = "-2";
			}
			
			findingRateMast.setCreatedBy(principal.getName());
			findingRateMast.setCreatedDate(new java.util.Date());
		    findingRateMast.setRate(Double.parseDouble(df.format(findingRateMast.getRate())));
		} else {
			findingRateMastValidate = findingRateMastService.findByPartyAndComponentAndPurityAndDeactive(findingRateMast.getParty(), 
					findingRateMast.getComponent(), findingRateMast.getPurity(), false);
			
          if(findingRateMastValidate != null){
				if(findingRateMastValidate.getId().equals(findingRateMast.getId())){
				}else{
					return retVal = "-1"; 
				}
			}
          
        if(findingRateMast.getPerGramRate() == true && findingRateMast.getPerPcRate() == true){
				return retVal = "-2";
			}else if (findingRateMast.getPerGramRate() == false && findingRateMast.getPerPcRate() == false) {
				return retVal = "-2";
			}
			
           findingRateMast.setId(id);
           findingRateMast.setModiBy(principal.getName());
           findingRateMast.setModiDate(new java.util.Date());
           findingRateMast.setRate(Double.parseDouble(df.format(findingRateMast.getRate())));
			action = "updated";
			retVal = "/jewels/manufacturing/masters/findingRateMast.html?success=true";
		}

		findingRateMastService.save(findingRateMast);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/findingRateMast/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		FindingRateMast findingRateMast = findingRateMastService.findOne(id);
		model.addAttribute("findingRateMast", findingRateMast);
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("findingRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "findingRateMast/add";

		}else
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model = populateModel(model);
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}

		return "findingRateMast/add";
		
		
	//	model = popRoleRightModel(model, principal);
	
		
	}
	
	
	@RequestMapping("/findingRateMast/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		
		
		FindingRateMast findingRateMast = findingRateMastService.findOne(id);
		model.addAttribute("findingRateMast", findingRateMast);
		model = populateModel(model);

		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("findingRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("canView",roleRights.getCanView());
		model.addAttribute("editRight",roleRights.getCanEdit());
		
		return "findingRateMast/add";
	}

	@RequestMapping("/findingRateMast/delete/{id}")
	public String delete(@PathVariable int id) {
		findingRateMastService.delete(id);
		return "redirect:/manufacturing/masters/findingRateMast.html";
	}
	
	
	@ResponseBody
	@RequestMapping("popRate/findingRate")
	public String findingRate(@RequestParam(value = "pCompId") Integer pCompId,
			@RequestParam(value = "purityId") Integer purityId){
		String retVal = "";
		Component component = componentService.findOne(pCompId);
		Party party = partyService.findByDefaultFlag(true);
		Purity purity = purityService.findOne(purityId);
		
		FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(component, party, purity, false);
		
	/*	if(findingRateMast.getPerGramRate() > 0){
			retVal = findingRateMast.getPerGramRate().toString();
		}else{
			retVal = findingRateMast.getPerPcRate().toString();
		}
		*/
		return retVal;
	}

	
	@RequestMapping("/findingRate/uploadExcel")
	public String excelFilePage(Model model){
		
		model.addAttribute("tableDisp", "no");
		return "uploadExcelFindingRate";
	}
	
	
	
	@RequestMapping(value = "/findingRate/commonExcelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model,
			@RequestParam("excelfile") MultipartFile excelfile,HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile,RedirectAttributes redirectAttributes){

		
		String retVal ="";
		try {
				if(!tempExcelFile.isEmpty()){
			
			
			List<FindingRateMast> findingRateMastList = new ArrayList<FindingRateMast>();
			    if (tempExcelFile.endsWith("xlsx")) {
			    	int i=1;
			    	//int j=0;
			    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			        XSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						FindingRateMast findingRateMast = new FindingRateMast();
						XSSFRow row = worksheet.getRow(i++);
						
						if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
							continue;
						}
					
						for(int j=0;j<row.getLastCellNum();j++){
							Cell cell = row.getCell(j);
						    cell.setCellType (Cell.CELL_TYPE_STRING);
						    j++;
						}
					
						//note : temporary list
						//remark is set in createdBy for temporary list
						//status is set in updatedBy for temporary list
						
						String remark = "successfull";
						String status = "verified";
						
						Party party = partyService.findByPartyCodeAndDeactive(row.getCell(0).toString(), false);
						if(party == null){
							remark = "No such client exists";
							status = "verification failed";
						}/*else{
							if(!(party.getExportClient().equals(true))){
								party = null;
								remark = "No such client exists";
								status = "verification failed";
							}
						}*/
						
						
						Component component = componentService.findByName(row.getCell(1).toString());
						if(component == null){
							remark = "No such component exists";
							status = "verification failed";
						}
						
						
						Purity purity = purityService.findByName(row.getCell(2).toString());
						if(purity == null){
							remark = "No such purity exists";
							status = "verification failed";
						}
						
						findingRateMast.setParty(party);
						findingRateMast.setComponent(component);
						findingRateMast.setPurity(purity);
						findingRateMast.setRate(Double.parseDouble(row.getCell(3).toString()));
						findingRateMast.setPerPcRate(Boolean.parseBoolean(row.getCell(4) != null ?  row.getCell(4).toString() : "FALSE" ));
						findingRateMast.setPerGramRate(Boolean.parseBoolean(row.getCell(5) != null ?  row.getCell(5).toString() : "FALSE" ));
					//	findingRateMast.setDeactive(Boolean.parseBoolean(row.getCell(6) != null ?  row.getCell(6).toString() : "FALSE" ));
						
						
						if(party != null && component != null && purity != null){
							FindingRateMast findingRateMastCheck = findingRateMastService.findByPartyAndComponentAndPurityAndDeactive(findingRateMast.getParty(), 
									findingRateMast.getComponent(), findingRateMast.getPurity(), false);
							
							if(findingRateMastCheck != null){
								remark = "Duplicate Record";
								status = "verification failed";
							}
						}
						
						
						if(findingRateMast.getPerGramRate() == true && findingRateMast.getPerPcRate() == true){
							remark = "only one check box must be selected";
							status = "verification failed";
						}else if (findingRateMast.getPerGramRate() == false && findingRateMast.getPerPcRate() == false) {
							remark = "only one check box must be selected";
							status = "verification failed";
						}
						
						
						findingRateMast.setModiBy(status);
						findingRateMast.setCreatedBy(remark);
						findingRateMastList.add(findingRateMast);
					}
					
					workbook.close();
					
			    } else if (tempExcelFile.endsWith("xls")) {
			    	int i = 1;
					HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
					HSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						FindingRateMast findingRateMast = new FindingRateMast();
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
					
						//note : temporary list
						//remark is set in createdBy for temporary list
						//status is set in updatedBy for temporary list
						
						String remark = "successfull";
						String status = "verified";
						
						Party party = partyService.findByPartyCodeAndDeactive(row.getCell(0).toString(), false);
						if(party == null){
							remark = "No such client exists";
							status = "verification failed";
						}/*else{
							if(!(party.getExportClient().equals(true))){
								party = null;
								remark = "No such client exists";
								status = "verification failed";
							}
						}*/
						
						
						Component component = componentService.findByName(row.getCell(1).toString());
						if(component == null){
							remark = "No such component exists";
							status = "verification failed";
						}
						
						
						Purity purity = purityService.findByName(row.getCell(2).toString());
						if(purity == null){
							remark = "No such purity exists";
							status = "verification failed";
						}
						
						findingRateMast.setParty(party);
						findingRateMast.setComponent(component);
						findingRateMast.setPurity(purity);
						findingRateMast.setRate(Double.parseDouble(row.getCell(3).toString()));
						findingRateMast.setPerPcRate(Boolean.parseBoolean(row.getCell(4) != null ?  row.getCell(4).toString() : "FALSE" ));
						findingRateMast.setPerGramRate(Boolean.parseBoolean(row.getCell(5) != null ?  row.getCell(5).toString() : "FALSE" ));
						findingRateMast.setDeactive(Boolean.parseBoolean(row.getCell(6) != null ?  row.getCell(6).toString() : "FALSE" ));
						
						
						if(party != null && component != null && purity != null){
							FindingRateMast findingRateMastCheck = findingRateMastService.findByPartyAndComponentAndPurityAndDeactive(findingRateMast.getParty(), 
									findingRateMast.getComponent(), findingRateMast.getPurity(), false);
							
							if(findingRateMastCheck != null){
								remark = "Duplicate Record";
								status = "verification failed";
							}
						}
						
						
						if(findingRateMast.getPerGramRate() == true && findingRateMast.getPerPcRate() == true){
							remark = "only one check box must be selected";
							status = "verification failed";
						}else if (findingRateMast.getPerGramRate() == false && findingRateMast.getPerPcRate() == false) {
							remark = "only one check box must be selected";
							status = "verification failed";
						}
						
						
						findingRateMast.setModiBy(status);
						findingRateMast.setCreatedBy(remark);
						findingRateMastList.add(findingRateMast);
					}
					workbook.close();
					
			    } else {
			        throw new IllegalArgumentException("The specified file is not Excel file");
			    }
			    
			    
			    session.setAttribute("findingRateMastSessionList", findingRateMastList);
			    redirectAttributes.addFlashAttribute("success", true);
				redirectAttributes.addFlashAttribute("action", "uploaded");
				
				retVal="yes";
			    
			}
		}catch (IOException e) {
				redirectAttributes.addFlashAttribute("success", false);
				e.printStackTrace();
			}
		
		
		
		model.addAttribute("tableDisp", "yes");
		model.addAttribute("retVal", retVal);
		return "findingRateFileUploaded";
	}
	
	
	
	

	@RequestMapping("/findingRate/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<FindingRateMast> findingRateMasts = (List<FindingRateMast>) httpSession.getAttribute("findingRateMastSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(1).append(",\"rows\": [");
		
		for(FindingRateMast findingRateMast:findingRateMasts){
			
			sb.append("{\"id\":\"")
				.append(findingRateMast.getId())
				.append("\",\"party\":\"")
				.append(findingRateMast.getParty() != null ? findingRateMast.getParty().getPartyCode() : "")
				.append("\",\"purity\":\"")
				.append(findingRateMast.getPurity() != null ? findingRateMast.getPurity().getName() : "")
				.append("\",\"component\":\"")
				.append(findingRateMast.getComponent() != null ? findingRateMast.getComponent().getName() : "")
				.append("\",\"perGramRate\":\"")
				.append(findingRateMast.getPerGramRate())
				.append("\",\"perPcRate\":\"")
				.append(findingRateMast.getPerPcRate())
				.append("\",\"rate\":\"")
				.append(findingRateMast.getRate())
				.append("\",\"deactive\":\"")
				.append((findingRateMast.getDeactive() == null ? "true" : findingRateMast.getDeactive()))
			//note : temporary list
			//remark is set in createdBy for temporary list
			//status is set in updatedBy for temporary list
			.append("\",\"status\":\"")
			.append(findingRateMast.getModiBy() != null ? findingRateMast.getModiBy() : "not perfect")
			.append("\",\"remark\":\"")
			.append((findingRateMast.getCreatedBy() != null ? findingRateMast.getCreatedBy() : "unknown error"))
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	
	@RequestMapping(value = "/findingRate/excelSave" , method = RequestMethod.POST)
	@ResponseBody
	public String saveExcelData(
			@ModelAttribute("findingRateMast") FindingRateMast findingRateMast,
			@RequestParam(value ="tableJsonData") String tableJsonData,
			Principal principal){
		
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
			FindingRateMast findingRateMastNew = new FindingRateMast();
			findingRateMastNew.setParty(partyService.findByPartyCodeAndDeactive(jsonDtNew.getString("party"), false));
			findingRateMastNew.setComponent(componentService.findByName(jsonDtNew.getString("component")));
			findingRateMastNew.setPurity(purityService.findByName(jsonDtNew.getString("purity")));
			findingRateMastNew.setRate(Double.parseDouble(jsonDtNew.getString("rate")));
			findingRateMastNew.setPerPcRate(Boolean.parseBoolean(jsonDtNew.getString("perPcRate")));
			findingRateMastNew.setPerGramRate(Boolean.parseBoolean(jsonDtNew.getString("perGramRate")));
			findingRateMastNew.setDeactive(Boolean.parseBoolean(jsonDtNew.getString("deactive")));
			findingRateMastNew.setCreatedDate(new  java.util.Date());
			findingRateMastNew.setCreatedBy(principal.getName());
			findingRateMastService.save(findingRateMastNew);
			retVal = "-2";
		}
		
		
		return retVal;
		
	}
	
	
	
	
	//--------finding rate excel file download----//
	
	@RequestMapping("/findingRate/downloadExcel/format")
	@ResponseBody
	public String excelFormatDownload(
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
	
	
	
	
	
	

}

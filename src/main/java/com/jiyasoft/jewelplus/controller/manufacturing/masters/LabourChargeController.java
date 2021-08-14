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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;



@RequestMapping("/manufacturing/masters")
@Controller
public class LabourChargeController {
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IPurityService purityService;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;
	
	@ModelAttribute("labourCharge")
	public LabourCharge construct() {
		return new LabourCharge();
	}

	@RequestMapping("/labourCharge")
	public String labourCharge(Model model, Principal principal) {
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("labourCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "labourCharge";
			
		}else
			
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "labourCharge";
		
	//	model = popRoleRightModel(model, principal);
	//	return "labourCharge";
	}
	
	
	@RequestMapping("/labourCharge/listing")
	@ResponseBody
	public String labourChargeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,Principal principal) {
		
		StringBuilder sb = new StringBuilder();
		Page<LabourCharge> labourCharges = null;
		

		//list code 3 line--//
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("labourCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	//	Long rowCount = null;

		labourCharges = labourChargeService.searchAll(limit, offset, sort, order, search, true);
	//	rowCount = labourChargeService.countAll(sort, search, false);
		
		
		sb.append("{\"total\":").append(labourCharges.getTotalElements()).append(",\"rows\": [");

		for (LabourCharge labourCharge : labourCharges) {
				sb.append("{\"id\":\"")
					.append(labourCharge.getId())
					.append("\",\"category\":\"")
					.append((labourCharge.getCategory() != null ? labourCharge.getCategory().getName() : ""))
					.append("\",\"metal\":\"")
					.append((labourCharge.getMetal() != null ? labourCharge.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((labourCharge.getPurity() != null ? labourCharge.getPurity().getName() : ""))
					.append("\",\"party\":\"")
					.append((labourCharge.getParty() != null ? labourCharge.getParty().getPartyCode() : ""))
					.append("\",\"labourType\":\"")
					.append((labourCharge.getLabourType() != null ? labourCharge.getLabourType().getName() : ""))
					.append("\",\"rate\":\"")
					.append((labourCharge.getRate() != null ? labourCharge.getRate() : ""))
					.append("\",\"fromWeight\":\"")
					.append(labourCharge.getFromWeight())
					.append("\",\"toWeight\":\"")
					.append(labourCharge.getToWeight())
					.append("\",\"pcsRate\":\"")
					.append(labourCharge.getPerPcsRate())
					.append("\",\"gramRate\":\"")
					.append(labourCharge.getPerGramRate())
					.append("\",\"percent\":\"")
					.append(labourCharge.getPercentage())
					.append("\",\"perCarat\":\"")
					.append(labourCharge.getPerCaratRate())
					.append("\",\"defLabour\":\"")
					.append(labourCharge.getDefLabour())
					.append("\",\"updatedDt\":\"")
					.append((labourCharge.getModiDate() == null ? "" : labourCharge.getModiDate()))
					.append("\",\"deactive\":\"")
					.append((labourCharge.getDeactive() == null ? "": (labourCharge.getDeactive() ? "Deactive": "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((labourCharge.getDeactiveDt() == null ? "": labourCharge.getDeactiveDt()));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
						  sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/labourCharge/edit/")
							.append(labourCharge.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
						  sb.append("\",\"action2\":\"")
						    .append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/labourCharge/delete/")
							.append(labourCharge.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(labourCharge.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				
	
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/labourCharge/edit/")
							.append(labourCharge.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/labourCharge/delete/")
								.append(labourCharge.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(labourCharge.getId())
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
	
	
	
	
	
	@RequestMapping("/labourCharge/customSearch/listing")
	@ResponseBody
	public String labourChargeCustomListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "partyName") String partyName,
			@RequestParam(value = "categoryNm") String categoryNm,
			@RequestParam(value = "metalNm") String metalNm,
			@RequestParam(value = "labourTypeNm") String labourTypeNm,
			Principal principal) {
		
		
		
		StringBuilder sb = new StringBuilder();
		Page<LabourCharge> labourCharges = null;
	//	Long rowCount = null;
		

		//list code 3 line--//
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("labourCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
//		rowCount = labourChargeService.customSearchCount(partyName, categoryNm, metalNm, labourTypeNm,purityNm);
		labourCharges = labourChargeService.customSearch(limit, offset, sort, order, partyName, categoryNm, metalNm, labourTypeNm);
		
		
		sb.append("{\"total\":").append(labourCharges.getTotalElements()).append(",\"rows\": [");

		for (LabourCharge labourCharge : labourCharges) {
				sb.append("{\"id\":\"")
					.append(labourCharge.getId())
					.append("\",\"category\":\"")
					.append((labourCharge.getCategory() != null ? labourCharge.getCategory().getName() : ""))
					.append("\",\"metal\":\"")
					.append((labourCharge.getMetal() != null ? labourCharge.getMetal().getName() : ""))
					.append("\",\"party\":\"")
					.append((labourCharge.getParty() != null ? labourCharge.getParty().getPartyCode() : ""))
					.append("\",\"purity\":\"")
					.append((labourCharge.getPurity() != null ? labourCharge.getPurity().getName() : ""))
					.append("\",\"labourType\":\"")
					.append((labourCharge.getLabourType() != null ? labourCharge.getLabourType().getName() : ""))
					.append("\",\"rate\":\"")
					.append((labourCharge.getRate() != null ? labourCharge.getRate() : ""))
					.append("\",\"fromWeight\":\"")
					.append(labourCharge.getFromWeight())
					.append("\",\"toWeight\":\"")
					.append(labourCharge.getToWeight())
					.append("\",\"pcsRate\":\"")
					.append(labourCharge.getPerPcsRate())
					.append("\",\"gramRate\":\"")
					.append(labourCharge.getPerGramRate())
					.append("\",\"percent\":\"")
					.append(labourCharge.getPercentage())
					.append("\",\"perCarat\":\"")
					.append(labourCharge.getPerCaratRate())
					.append("\",\"defLabour\":\"")
					.append(labourCharge.getDefLabour())
					.append("\",\"updatedDt\":\"")
					.append((labourCharge.getModiDate() == null ? "" : labourCharge.getModiDate()))
					.append("\",\"deactive\":\"")
					.append((labourCharge.getDeactive() == null ? "": (labourCharge.getDeactive() ? "Deactive": "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((labourCharge.getDeactiveDt() == null ? "": labourCharge.getDeactiveDt()));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
						  sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/labourCharge/edit/")
							.append(labourCharge.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
						  sb.append("\",\"action2\":\"")
						    .append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/labourCharge/delete/")
							.append(labourCharge.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(labourCharge.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				
	
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/labourCharge/edit/")
							.append(labourCharge.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/labourCharge/delete/")
								.append(labourCharge.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(labourCharge.getId())
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
	
	
	
	
	@RequestMapping("/labourCharge/add")
	public String add(Model model, Principal principal) {
	//model = popRoleRightModel(model, principal);
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("labourCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "labourCharge/add";

		}else	
		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}
				return "labourCharge/add";
		
		
	}
	
	
	private Model populateModel(Model model) {
		model.addAttribute("categoryMap", categoryService.getCategoryList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("labTypeMap", labourTypeService.getLabourTypeList());
		model.addAttribute("partyMap",partyService.getPartyList());
		model.addAttribute("purityMap", purityService.getPurityList());
		return model;
	}
	
	
	public Model popRoleRightModel(Model model,Principal principal){
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("labourCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("canAdd", roleRights.getCanAdd());
		model.addAttribute("canDelete", roleRights.getCanDelete());
		model.addAttribute("canEdit", roleRights.getCanEdit());
		return model;
	}
	
	
	@RequestMapping(value = "/labourCharge/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditCategory(
			@Valid @ModelAttribute("labourCharge") LabourCharge labourCharge,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/labourCharge/add.html?success="+true+"&action="+action;

		if (result.hasErrors()) {
			return "labourCharge/add";
		}
		
		int i=0;
		if(labourCharge.getPerPcsRate() == true){
			i +=1;
		}
		
		if(labourCharge.getPerGramRate() == true){
			i +=1;
		}
		
		if(labourCharge.getPercentage() == true){
			i +=1;
		}
		
		if(labourCharge.getPerCaratRate() == true){
			i +=1;
		}
		
		if(i >1){
			return retVal = "-2";
		}else if(i==0){
			return retVal = "-3";
		}
		
		
		/*if(labourCharge.getPerPcsRate() == true && labourCharge.getPerGramRate() == true){
			return retVal = "-2";
		}
		
		if(labourCharge.getPerPcsRate() == true && labourCharge.getPercentage() == true){
			return retVal = "-2";
		}
		
		if(labourCharge.getPerGramRate() == true && labourCharge.getPercentage() == true){
			return retVal = "-2";
		}
		
		
		if(labourCharge.getPerPcsRate() == false && labourCharge.getPerGramRate() == false && labourCharge.getPercentage() == false
				&& labourCharge.getPerCaratRate() == false){
			return retVal = "-3";
		}*/
		
		String labourChargeCheck= "";
		DecimalFormat df = new DecimalFormat("#.##");
		
		labourChargeCheck =  labourChargeService.CheckDuplicate(id, labourCharge.getParty().getId(), labourCharge.getCategory().getId(), labourCharge.getMetal().getId(),
				labourCharge.getLabourType().getId(), labourCharge.getFromWeight(), labourCharge.getToWeight(),labourCharge.getPurity().getId());
		
		if(labourChargeCheck.equalsIgnoreCase("true")){
			return retVal = "-4";
		}
		
		if(labourCharge.getToWeight() < labourCharge.getFromWeight()){
			return retVal = "-5";
		}
		
		if((labourCharge.getLabourType() == null) || labourCharge.getLabourType().getId() == null ){
			labourCharge.setLabourType(null);
		}
		
		if((labourCharge.getParty() == null) || labourCharge.getParty().getId() == null ){
			labourCharge.setParty(null);
		}
		
		if(labourCharge.getPurity().getId() == null ){
			labourCharge.setPurity(null);
		}
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			labourCharge.setCreatedBy(principal.getName());
			labourCharge.setCreatedDate(new java.util.Date());

		} else {
	
			labourCharge.setModiBy(principal.getName());
			labourCharge.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "/jewels/manufacturing/masters/labourCharge.html?id="+ labourCharge.getCategory().getId()+"&success="+true+"&action="+action;

		}
		
		
		
		labourChargeService.save(labourCharge);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}
	
	
	@RequestMapping("/labourCharge/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		LabourCharge labourCharge = labourChargeService.findOne(id);
		model.addAttribute("labourCharge", labourCharge);
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("labourCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "labourCharge/add";

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

		return "labourCharge/add";
		
	//	model = popRoleRightModel(model, principal);
	//	model = populateModel(model);
		
		
	}
	
	@RequestMapping("/labourCharge/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		
		LabourCharge labourCharge = labourChargeService.findOne(id);
		model.addAttribute("labourCharge", labourCharge);
		model = populateModel(model);
		
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("labourCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("canView",roleRights.getCanView());
		model.addAttribute("editRight",roleRights.getCanEdit());
		
		return "labourCharge/add";
	}
	

	@RequestMapping("/labourCharge/delete/{id}")
	public String delete(@PathVariable int id) {
		labourChargeService.delete(id);
		return "redirect:/manufacturing/masters/labourCharge.html";
	}
	
	
	@RequestMapping("/labourCharge/uploadExcel")
	public String excelFilePage(Model model){
		model.addAttribute("tableDisp", "no");
		return "uploadExcelLabour";
	}
	
	
	@RequestMapping(value = "/labourCharge/commonExcelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model,
			@RequestParam("excelfile") MultipartFile excelfile,HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile,RedirectAttributes redirectAttributes){

		String retVal ="";
		String labourChargeCheck= "";
		try {
			if(!tempExcelFile.isEmpty()){
			
			List<LabourCharge> labourChargeList = new ArrayList<LabourCharge>();
			
			    if (tempExcelFile.endsWith("xlsx")) {
			    	int i=1;
			    	//int j=0;
			    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			        XSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						LabourCharge labourCharge = new LabourCharge();
						XSSFRow row = worksheet.getRow(i++);
						if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
							continue;
						}
					
						/*
						 * for(int j=0;j<row.getLastCellNum();j++){ Cell cell = row.getCell(j);
						 * cell.setCellType (Cell.CELL_TYPE_STRING); j++; }
						 */
					
						//note : temporary list
						//remark is set in createdBy for temporary list
						//status is set in updatedBy for temporary list
						
						String remark = "successfull";
						String status = "verified";
						
						Party party = partyService.findByPartyCodeAndDeactive(row.getCell(0).toString(), false);
						if(party == null){
							remark = "No such client exists "+row.getCell(0).toString();
							status = "verification failed";
						}else{
							if(!(party.getExportClient().equals(true))){
								party = null;
								remark = "No such client exists "+row.getCell(0).toString();
								status = "verification failed";
							}
						}
						
						Category category = categoryService.findByName(row.getCell(1).toString());
						if(category == null){
							remark = "No such category exists "+row.getCell(1).toString();
							status = "verification failed";
						}
						
						
						LabourType labourType = labourTypeService.findByName(row.getCell(2).toString());
						if(labourType == null){
							remark = "No such labourType exists "+row.getCell(2).toString();
							status = "verification failed";
						}
						
						Metal metal = metalService.findByName(row.getCell(10).toString());
						if(metal == null){
							remark = "No such metal exists "+row.getCell(10).toString();
							status = "verification failed";
						}
						
						
						Purity purity = null;
						if(row.getCell(12) !=null){
							 purity =  purityService.findByName(row.getCell(12).toString());
							if(purity == null){
								remark = "No such purity exists "+row.getCell(12).toString();
								status = "verification failed";
							}	
							labourCharge.setPurity(purity);
						}else{
							
					//		Purity purity =new Purity();
							labourCharge.setPurity(purity);
						}
						
						
						
						labourCharge.setParty(party);
						labourCharge.setCategory(category);
						labourCharge.setLabourType(labourType);
						labourCharge.setFromWeight(Double.parseDouble(row.getCell(3).toString()));
						labourCharge.setToWeight(Double.parseDouble(row.getCell(4).toString()));
						labourCharge.setRate(Double.parseDouble(row.getCell(5).toString()));
						labourCharge.setPerPcsRate(Boolean.parseBoolean(row.getCell(6) != null ?  row.getCell(6).toString() : "FALSE" ));
						labourCharge.setPerGramRate(Boolean.parseBoolean(row.getCell(7) != null ?  row.getCell(7).toString() : "FALSE" ));
						labourCharge.setPerCaratRate(Boolean.parseBoolean(row.getCell(11) != null ?  row.getCell(11).toString() : "FALSE" ));
						labourCharge.setPercentage(Boolean.parseBoolean(row.getCell(8) != null ?  row.getCell(8).toString() : "FALSE" ));
						labourCharge.setDefLabour(Boolean.parseBoolean(row.getCell(9) != null ?  row.getCell(9).toString() : "FALSE" ));
						labourCharge.setMetal(metal);
						labourCharge.setPurity(purity);
						
						
						
						
						if(party != null && category != null && labourType != null && metal != null){
							/*	LabourCharge labourChargeCheck = labourChargeService.findByPartyAndCategoryAndLabourTypeAndMetalAndPurityAndFromWeightAndToWeightAndDeactive(labourCharge.getParty(), labourCharge.getCategory(),
									labourCharge.getLabourType(), labourCharge.getMetal(),labourCharge.getPurity(),labourCharge.getFromWeight(), labourCharge.getToWeight(), false);
						*/
						
						labourChargeCheck =  labourChargeService.CheckDuplicate(labourCharge.getId(), labourCharge.getParty().getId(), labourCharge.getCategory().getId(), labourCharge.getMetal().getId(),
								labourCharge.getLabourType().getId(), labourCharge.getFromWeight(), labourCharge.getToWeight(),labourCharge.getPurity().getId());
						
						if(labourChargeCheck.equalsIgnoreCase("true")){
								remark = "Duplicate Record";
								status = "verification failed";
							}
						}
						
						
						Boolean flg=false;
						
						if(labourCharge.getPerPcsRate() == true){
							flg=true;
						}
						
						
						if(labourCharge.getPerCaratRate() == true){
							if(flg == true){
								remark = "only one check box must be selected";
								status = "verification failed";
							}else{
								flg=true;
							}
						}
						
						

						if(labourCharge.getPercentage() == true){
							if(flg == true){
								remark = "only one check box must be selected";
								status = "verification failed";
							}else{
								flg=true;
							}
						}
						

						if(labourCharge.getPerGramRate() == true){
							if(flg == true){
								remark = "only one check box must be selected";
								status = "verification failed";
							}else{
								flg=true;
							}
						}
						
//						if(labourCharge.getDefLabour() == true){
//							if(flg == true){
//								remark = "only one check box must be selected";
//								status = "verification failed";
//							}else{
//								flg=true;
//							}
//						}
						
						if(flg == false){
							remark = "one check box must be selected";
							status = "verification failed";
						}
						
						
						/*if(labourCharge.getPerPcsRate() == true && labourCharge.getPerGramRate() == true){
							remark = "only one check box must be selected";
							status = "verification failed";
							
						}
						
						if(labourCharge.getPerPcsRate() == true && labourCharge.getPercentage() == true){
							remark = "only one check box must be selected";
							status = "verification failed";
						}
						
						if(labourCharge.getPerGramRate() == true && labourCharge.getPercentage() == true){
							remark = "only one check box must be selected";
							status = "verification failed";
						}
						
						
						if(labourCharge.getPerPcsRate() == false && labourCharge.getPerGramRate() == false && labourCharge.getPercentage() == false){
							remark = "one check box must be selected";
							status = "verification failed";
						}*/
						
						
						labourCharge.setModiBy(status);
						labourCharge.setCreatedBy(remark);
						labourChargeList.add(labourCharge);
					}
					
					workbook.close();
					
			    } else if (tempExcelFile.endsWith("xls")) {
			    	int i = 1;
					HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
					HSSFSheet worksheet = workbook.getSheetAt(0);
					
									
					
				
					while (i <= worksheet.getLastRowNum()) {
						LabourCharge labourCharge = new LabourCharge();
						HSSFRow row = worksheet.getRow(i++);
			
						if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
							continue;
						}
						
						/*
						 * for(int j=0;j<row.getLastCellNum();j++){ Cell cell = row.getCell(j);
						 * cell.setCellType (Cell.CELL_TYPE_STRING); j++; }
						 */
					
			
						
						String remark = "successfull";
						String status = "verified";
						
						Party party = partyService.findByPartyCodeAndDeactive(row.getCell(0).toString(), false);
						if(party == null){
							remark = "No such client exists "+row.getCell(0).toString();
							status = "verification failed";
						}
						
						
						
						Category category = categoryService.findByName(row.getCell(1).toString());
						if(category == null){
							remark = "No such category exists "+row.getCell(1).toString();
							status = "verification failed";
						}
						
						
						LabourType labourType = labourTypeService.findByName(row.getCell(2).toString());
						if(labourType == null){
							remark = "No such labourType exists "+row.getCell(2).toString();
							status = "verification failed";
						}
						
						Metal metal = metalService.findByName(row.getCell(10).toString());
						if(metal == null){
							remark = "No such metal exists "+row.getCell(10).toString();
							status = "verification failed";
						}
						
						
												
						Purity purity = null;
						if(row.getCell(12) !=null){
							 purity =  purityService.findByName(row.getCell(12).toString());
							if(purity == null){
								remark = "No such purity exists "+row.getCell(12).toString();
								status = "verification failed";
							}	
							labourCharge.setPurity(purity);
						}else{
							labourCharge.setPurity(purity);
						}
						
						
						
						labourCharge.setParty(party);
						labourCharge.setCategory(category);
						labourCharge.setLabourType(labourType);
						labourCharge.setFromWeight(Double.parseDouble(row.getCell(3).toString()));
						labourCharge.setToWeight(Double.parseDouble(row.getCell(4).toString()));
						labourCharge.setRate(Double.parseDouble(row.getCell(5).toString()));
						labourCharge.setPerPcsRate(Boolean.parseBoolean(row.getCell(6) != null ?  row.getCell(6).toString() : "FALSE" ));
						labourCharge.setPerGramRate(Boolean.parseBoolean(row.getCell(7) != null ?  row.getCell(7).toString() : "FALSE" ));
						labourCharge.setPercentage(Boolean.parseBoolean(row.getCell(8) != null ?  row.getCell(8).toString() : "FALSE" ));
						labourCharge.setDefLabour(Boolean.parseBoolean(row.getCell(9) != null ?  row.getCell(9).toString() : "FALSE" ));
						labourCharge.setMetal(metal);
						labourCharge.setPerCaratRate(Boolean.parseBoolean(row.getCell(11) != null ?  row.getCell(11).toString() : "FALSE" ));
						labourCharge.setPurity(purity);
						
						if(party != null && category != null && labourType != null && metal != null){
							/*LabourCharge labourChargeCheck = labourChargeService.findByPartyAndCategoryAndLabourTypeAndMetalAndPurityAndFromWeightAndToWeightAndDeactive(labourCharge.getParty(), labourCharge.getCategory(),
									labourCharge.getLabourType(), labourCharge.getMetal(),labourCharge.getPurity(),labourCharge.getFromWeight(), labourCharge.getToWeight(), false);*/
							
							labourChargeCheck =  labourChargeService.CheckDuplicate(labourCharge.getId(), labourCharge.getParty().getId(), labourCharge.getCategory().getId(), labourCharge.getMetal().getId(),
									labourCharge.getLabourType().getId(), labourCharge.getFromWeight(), labourCharge.getToWeight(),labourCharge.getPurity().getId());
							
							
							if(labourChargeCheck.equalsIgnoreCase("true")){
								remark = "Duplicate Record";
								status = "verification failed";
							}
						}
						
						
						
						
						Boolean flg=false;
						
						if(labourCharge.getPerPcsRate() == true){
							flg=true;
						}
						
						
						if(labourCharge.getPerCaratRate() == true){
							if(flg == true){
								remark = "only one check box must be selected";
								status = "verification failed";
							}else{
								flg=true;
							}
						}
						
						

						if(labourCharge.getPercentage() == true){
							if(flg == true){
								remark = "only one check box must be selected";
								status = "verification failed";
							}else{
								flg=true;
							}
						}
						

						if(labourCharge.getPerGramRate() == true){
							if(flg == true){
								remark = "only one check box must be selected";
								status = "verification failed";
							}else{
								flg=true;
							}
						}
						
//						if(labourCharge.getDefLabour() == true){
//							if(flg == true){
//								remark = "only one check box must be selected";
//								status = "verification failed";
//							}else{
//								flg=true;
//							}
//						}
						
						
						
						
						if(flg == false){
							remark = "one check box must be selected";
							status = "verification failed";
						}
						
						
						/*if(labourCharge.getPerPcsRate() == true && labourCharge.getPerGramRate() == true){
							remark = "only one check box must be selected";
							status = "verification failed";
							
						}
						
						if(labourCharge.getPerPcsRate() == true && labourCharge.getPercentage() == true){
							remark = "only one check box must be selected";
							status = "verification failed";
						}
						
						if(labourCharge.getPerGramRate() == true && labourCharge.getPercentage() == true){
							remark = "only one check box must be selected";
							status = "verification failed";
						}
						
						
						if(labourCharge.getPerPcsRate() == false && labourCharge.getPerGramRate() == false && labourCharge.getPercentage() == false){
							remark = "one check box must be selected";
							status = "verification failed";
						}*/
						
						
						labourCharge.setModiBy(status);
						labourCharge.setCreatedBy(remark);
						
						labourChargeList.add(labourCharge);
					}
					workbook.close();
					
			    } else {
			        throw new IllegalArgumentException("The specified file is not Excel file");
			    }
			    
			    
			    session.setAttribute("labourChargeSessionList", labourChargeList);
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
		
		return "labourFileUploaded";
	}
	

	
	
	@RequestMapping("/labourCharge/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<LabourCharge> labourCharges = (List<LabourCharge>) httpSession.getAttribute("labourChargeSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(1).append(",\"rows\": [");
		
		for(LabourCharge labourCharge:labourCharges){
			
			sb.append("{\"id\":\"")
			.append(labourCharge.getId())
			.append("\",\"category\":\"")
			.append((labourCharge.getCategory() != null ? labourCharge.getCategory().getName() : ""))
			.append("\",\"metal\":\"")
			.append((labourCharge.getMetal() != null ? labourCharge.getMetal().getName() : ""))
			.append("\",\"purity\":\"")
			.append((labourCharge.getPurity() != null ? labourCharge.getPurity().getName() : ""))
			.append("\",\"party\":\"")
			.append((labourCharge.getParty() != null ? labourCharge.getParty().getPartyCode() : ""))
			.append("\",\"labourType\":\"")
			.append((labourCharge.getLabourType() != null ? labourCharge.getLabourType().getName() : ""))
			.append("\",\"rate\":\"")
			.append((labourCharge.getRate() != null ? labourCharge.getRate() : ""))
			.append("\",\"fromWeight\":\"")
			.append(labourCharge.getFromWeight())
			.append("\",\"toWeight\":\"")
			.append(labourCharge.getToWeight())
			.append("\",\"pcsRate\":\"")
			.append((labourCharge.getPerPcsRate() == null ? "true" : labourCharge.getPerPcsRate()))
			.append("\",\"gramRate\":\"")
			.append((labourCharge.getPerGramRate() == null ? "true" : labourCharge.getPerGramRate()))
			.append("\",\"caratRate\":\"")
			.append((labourCharge.getPerCaratRate() == null ? "true" : labourCharge.getPerCaratRate()))
			.append("\",\"percent\":\"")
			.append((labourCharge.getPercentage() == null ? "true" : labourCharge.getPercentage()))
			.append("\",\"defLabour\":\"")
			.append((labourCharge.getDefLabour() == null ? "true" : labourCharge.getDefLabour()))
			.append("\",\"deactive\":\"")
			.append((labourCharge.getDeactive() == null ? "true" : labourCharge.getDeactive()))
			//note : temporary list
			//remark is set in createdBy for temporary list
			//status is set in updatedBy for temporary list
			.append("\",\"status\":\"")
			.append(labourCharge.getModiBy() != null ? labourCharge.getModiBy() : "not perfect")
			.append("\",\"remark\":\"")
			.append((labourCharge.getCreatedBy() != null ? labourCharge.getCreatedBy() : "unknown error"))
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	
	
	@RequestMapping(value = "/labourCharge/excelSave" , method = RequestMethod.POST)
	@ResponseBody
	public String saveExcelData(
			@ModelAttribute("labourCharge") LabourCharge labourCharge,
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
			LabourCharge labourChargeNew = new LabourCharge();
			labourChargeNew.setParty(partyService.findByPartyCodeAndDeactive(jsonDtNew.getString("party"), false));
			labourChargeNew.setCategory(categoryService.findByName(jsonDtNew.getString("category")));
			labourChargeNew.setMetal(metalService.findByName(jsonDtNew.getString("metal")));
			labourChargeNew.setPurity(purityService.findByName(jsonDtNew.getString("purity")));
			labourChargeNew.setLabourType(labourTypeService.findByName(jsonDtNew.getString("labourType")));
			labourChargeNew.setFromWeight(Double.parseDouble(jsonDtNew.getString("fromWeight")));
			labourChargeNew.setToWeight(Double.parseDouble(jsonDtNew.getString("toWeight")));
			labourChargeNew.setRate(Double.parseDouble(jsonDtNew.getString("rate")));
			labourChargeNew.setPerPcsRate(Boolean.parseBoolean(jsonDtNew.getString("pcsRate")));
			labourChargeNew.setPerGramRate(Boolean.parseBoolean(jsonDtNew.getString("gramRate")));
			labourChargeNew.setPerCaratRate(Boolean.parseBoolean(jsonDtNew.getString("caratRate")));
			labourChargeNew.setPercentage(Boolean.parseBoolean(jsonDtNew.getString("percent")));
			labourChargeNew.setDefLabour(Boolean.parseBoolean(jsonDtNew.getString("defLabour")));
			labourChargeNew.setDeactive(Boolean.parseBoolean(jsonDtNew.getString("deactive")));
			labourChargeNew.setCreatedDate(new  java.util.Date());
			labourChargeNew.setCreatedBy(principal.getName());
			labourChargeService.save(labourChargeNew);
			retVal = "-2";
		}
		
		
		return retVal;
		
	}
	
	
	
	
	
	//--------labour charge excel file download----//
	
	@RequestMapping("/labourCharge/downloadExcel/format")
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








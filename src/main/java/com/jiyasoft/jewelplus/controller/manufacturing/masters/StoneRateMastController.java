package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;


@RequestMapping("/manufacturing/masters")
@Controller
public class StoneRateMastController {
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStoneChartService stoneChartService; 
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	

	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;
	
	
	@ModelAttribute("stoneRateMast")
	public StoneRateMast construct(){
		return new StoneRateMast();
	}
	
	@RequestMapping("/stoneRateMast")
	public String settingCharge(Model model, Principal principal) {
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "stoneRateMast";
			
		}else
			
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "stoneRateMast";
	}
	
	@RequestMapping("/stoneRateMast/add")
	public String add(Model model, Principal principal) {
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "stoneRateMast/add";

		}else	
		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}
				return "stoneRateMast/add";
		
	//	model = popRoleRightModel(model, principal);
		
		
		
	}
	
	public Model populateModel(Model model){
		model.addAttribute("partyMap",partyService.getPartyList());
		model.addAttribute("stoneTypeMap",stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap",shapeService.getShapeList());
		return model;
	}
	
	public Model popRoleRightModel(Model model,Principal principal){
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("canAdd", roleRights.getCanAdd());
		model.addAttribute("canDelete", roleRights.getCanDelete());
		model.addAttribute("canEdit", roleRights.getCanEdit());
		
		return model;
	}
	
	
	
	@RequestMapping("/stoneRateMast/customSearch/listing")
	@ResponseBody
	public String stoneRateCustomSearchList(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "partyName") String partyName,
			@RequestParam(value = "stoneTypeNm") String stoneTypeNm,
			@RequestParam(value = "shapeNm") String shapeNm,
			@RequestParam(value = "qualityNm") String qualityNm,
			@RequestParam(value = "sizeGroupNm") String sizeGroupNm,
			Principal principal){
		
		
		
		StringBuilder sb = new StringBuilder();
		//list code 3 line--//
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);

		MenuMast menuMast = menuMastService.findByMenuNm("stoneRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		Page<StoneRateMast> stoneRateMasts= null;
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	//	Long rowCount = null;
		
	//	rowCount = stoneRateMastService.customSearchCount(partyName,stoneTypeNm, shapeNm, qualityNm, sizeGroupNm);
		stoneRateMasts = stoneRateMastService.customSearch(limit, offset, sort, order,
				partyName,stoneTypeNm, shapeNm, qualityNm, sizeGroupNm);

		
		sb.append("{\"total\":").append(stoneRateMasts.getTotalElements()).append(",\"rows\": [");
        
		for (StoneRateMast stoneRateMast : stoneRateMasts) {
			
			sb.append("{\"id\":\"")
					.append(stoneRateMast.getId())
					.append("\",\"party\":\"")
					.append((stoneRateMast.getParty() != null ? stoneRateMast.getParty().getPartyCode() : ""))
					.append("\",\"stoneType\":\"")
					.append((stoneRateMast.getStoneType() != null ? stoneRateMast.getStoneType().getName() : ""))
					.append("\",\"shape\":\"")
					.append((stoneRateMast.getShape() != null ? stoneRateMast.getShape().getName() : ""))
					.append("\",\"quality\":\"")
					.append((stoneRateMast.getQuality() != null ? stoneRateMast.getQuality().getName() : ""))
					.append("\",\"sizeGroup\":\"")
					.append((stoneRateMast.getSizeGroup() != null ? stoneRateMast.getSizeGroup().getName() : ""))
					.append("\",\"stoneRate\":\"")
					.append(stoneRateMast.getStoneRate())
					.append("\",\"perPcRate\":\"")
					.append(stoneRateMast.getPerPcRate())
					.append("\",\"sieve\":\"")
					.append((stoneRateMast.getSieve()  != null ? stoneRateMast.getSieve() : ""));
					/*.append("\",\"fromWeight\":\"")
					.append(df.format(stoneRateMast.getFromWeight()))
					.append("\",\"toWeight\":\"")
					.append(df.format(stoneRateMast.getToWeight()))*/;
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"")
						.append("<a href='/jewels/manufacturing/masters/stoneRateMast/edit/")
						.append(stoneRateMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/stoneRateMast/delete/")
						.append(stoneRateMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(stoneRateMast.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
			
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/stoneRateMast/edit/")
						.append(stoneRateMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/stoneRateMast/delete/")
							.append(stoneRateMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(stoneRateMast.getId())
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
	
	
	
	@RequestMapping("/stoneRateMast/listing")
	@ResponseBody
	public String stoneRateListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

	    
		StringBuilder sb = new StringBuilder();
		Page<StoneRateMast> stoneRateMasts = null;
		
		
		//list code 3 line--//
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);

		MenuMast menuMast = menuMastService.findByMenuNm("stoneRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
						
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		Long rowCount = null;
		
		if (search == null && sort == null) {
			rowCount = stoneRateMastService.count(sort, search, true);
			stoneRateMasts = stoneRateMastService.findBySearchAndDeactive(limit, offset, sort, order, search, true);
		}else{
			
			System.out.println("in listing  else controller");
			rowCount = stoneRateMastService.count(sort, search, true);
			stoneRateMasts = stoneRateMastService.findBySearchAndDeactive(limit, offset, sort, order, search, true);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
             
		for (StoneRateMast stoneRateMast : stoneRateMasts) {
			
			sb.append("{\"id\":\"")
					.append(stoneRateMast.getId())
					.append("\",\"party\":\"")
					.append((stoneRateMast.getParty() != null ? stoneRateMast.getParty().getPartyCode() : ""))
					.append("\",\"stoneType\":\"")
					.append((stoneRateMast.getStoneType() != null ? stoneRateMast.getStoneType().getName() : ""))
					.append("\",\"shape\":\"")
					.append((stoneRateMast.getShape() != null ? stoneRateMast.getShape().getName() : ""))
					.append("\",\"quality\":\"")
					.append((stoneRateMast.getQuality() != null ? stoneRateMast.getQuality().getName() : ""))
					.append("\",\"sizeGroup\":\"")
					.append((stoneRateMast.getSizeGroup() != null ? stoneRateMast.getSizeGroup().getName() : ""))
					.append("\",\"stoneRate\":\"")
					.append(stoneRateMast.getStoneRate())
					.append("\",\"perPcRate\":\"")
					.append(stoneRateMast.getPerPcRate())
					.append("\",\"sieve\":\"")
					.append((stoneRateMast.getSieve()  != null ? stoneRateMast.getSieve() : ""));
					/*.append("\",\"fromWeight\":\"")
					.append(df.format(stoneRateMast.getFromWeight()))
					.append("\",\"toWeight\":\"")
					.append(df.format(stoneRateMast.getToWeight()));*/
			
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"")
						.append("<a href='/jewels/manufacturing/masters/stoneRateMast/edit/")
						.append(stoneRateMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/stoneRateMast/delete/")
						.append(stoneRateMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(stoneRateMast.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
			
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/stoneRateMast/edit/")
							.append(stoneRateMast.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/stoneRateMast/delete/")
								.append(stoneRateMast.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(stoneRateMast.getId())
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

	@ResponseBody
	@RequestMapping(value="stoneRateMast/add" , method = RequestMethod.POST)
	public String addEdit(@Valid @ModelAttribute("stoneRateMast") StoneRateMast stoneRateMast,
			@RequestParam(value = "id", required=false) Integer id,BindingResult result,
			Principal principal,RedirectAttributes redirectAttributes){
		
		
		String action = "added";
		String retVal = "-1";
		
		if (result.hasErrors()) {
			return "stoneRateMast/add";
		}
		
		StoneRateMast stoneRateMastCheck = null;
		DecimalFormat df1 = new DecimalFormat("#.##");
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			/*stoneRateMastCheck = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndPartyAndFromWeightAndToWeightAndDeactive(stoneRateMast.getStoneType(),
					stoneRateMast.getShape(), stoneRateMast.getQuality(), stoneRateMast.getParty(), stoneRateMast.getFromWeight(), stoneRateMast.getToWeight(), false);*/
			
			 stoneRateMastCheck = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveAndPartyAndSieve(stoneRateMast.getStoneType(),
					stoneRateMast.getShape(), stoneRateMast.getQuality(), stoneRateMast.getSizeGroup(),false,stoneRateMast.getParty(),stoneRateMast.getSieve());
			
			if(stoneRateMastCheck != null){
				return retVal = "-2";
			}
			
			if(stoneRateMast.getStoneRate() > 0 && stoneRateMast.getPerPcRate() > 0){
				return retVal = "-3";
			}else if (stoneRateMast.getStoneRate() == 0 && stoneRateMast.getPerPcRate() == 0) {
				return retVal = "-3";
			}
			
			if(stoneRateMast.getToWeight() < stoneRateMast.getFromWeight()){
				return retVal = "-4";
			}
			
			stoneRateMast.setCreatedBy(principal.getName());
			stoneRateMast.setCreatedDate(new java.util.Date());
			stoneRateMast.setDeactive(false);
			/*stoneRateMast.setFromWeight(Double.parseDouble(df.format(stoneRateMast.getFromWeight())));
			stoneRateMast.setToWeight(Double.parseDouble(df.format(stoneRateMast.getToWeight())));*/
			stoneRateMast.setStoneRate(Double.parseDouble(df1.format(stoneRateMast.getStoneRate())));
			stoneRateMast.setPerPcRate(Double.parseDouble(df1.format(stoneRateMast.getPerPcRate())));
			retVal = "/jewels/manufacturing/masters/stoneRateMast/add.html?success=true";
		}else{ 
			
			/*stoneRateMastCheck = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndPartyAndFromWeightAndToWeightAndDeactive(stoneRateMast.getStoneType(),
					stoneRateMast.getShape(), stoneRateMast.getQuality(), stoneRateMast.getParty(), stoneRateMast.getFromWeight(), stoneRateMast.getToWeight(), false);*/
			
						
			stoneRateMastCheck = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveAndPartyAndSieve(stoneRateMast.getStoneType(),
					stoneRateMast.getShape(), stoneRateMast.getQuality(), stoneRateMast.getSizeGroup(),false,stoneRateMast.getParty(),stoneRateMast.getSieve());
			
			if(stoneRateMastCheck != null){
				if(stoneRateMastCheck.getId().equals(id)){
					
				}else{
					return retVal = "-2";
				}
			}
			
			if(stoneRateMast.getStoneRate() > 0 && stoneRateMast.getPerPcRate() > 0){
				return retVal = "-3";
			}else if (stoneRateMast.getStoneRate() == 0 && stoneRateMast.getPerPcRate() == 0) {
				return retVal = "-3";
			}
            if(stoneRateMast.getToWeight() < stoneRateMast.getFromWeight()){
            	return retVal = "-4";
			}
			stoneRateMast.setId(id);
			stoneRateMast.setModiBy(principal.getName());
			stoneRateMast.setModiDate(new java.util.Date());
			stoneRateMast.setDeactive(false);
			/*stoneRateMast.setFromWeight(Double.parseDouble(df.format(stoneRateMast.getFromWeight())));
			stoneRateMast.setToWeight(Double.parseDouble(df.format(stoneRateMast.getToWeight())));*/
			stoneRateMast.setStoneRate(Double.parseDouble(df1.format(stoneRateMast.getStoneRate())));
			stoneRateMast.setPerPcRate(Double.parseDouble(df1.format(stoneRateMast.getPerPcRate())));
			action = "updated";
			retVal = "/jewels/manufacturing/masters/stoneRateMast.html?success=true";
		}
		
		stoneRateMastService.save(stoneRateMast);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		return retVal;
	}
	
	
	@RequestMapping("/stoneRateMast/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		StoneRateMast stoneRateMast = stoneRateMastService.findOne(id);
		model.addAttribute("stoneRateMast", stoneRateMast);
	//	model = populateModel(model);
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			if (stoneRateMast != null) {
				if(stoneRateMast.getShape() != null){
					Shape shape = stoneRateMast.getShape();
					model.addAttribute("qualityMap",qualityService.getQualityList(shape.getId()));
					model.addAttribute("sizeGroupMap",sizeGroupService.getSizeGroupList(shape.getId()));
					
					  if(stoneRateMast.getSizeGroup() !=null) {
						 
					  model.addAttribute("sieveMap",stoneChartService.getStoneChartList(stoneRateMast.getSizeGroup().getId())); } 
				}
			}
			
			
			
			return "stoneRateMast/add";

		}else	
		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
				
				if (stoneRateMast != null) {
					if(stoneRateMast.getShape() != null){
						Shape shape = stoneRateMast.getShape();
						model.addAttribute("qualityMap",qualityService.getQualityList(shape.getId()));
						//model.addAttribute("chartMap",stoneChartService.getStoneChartList(stoneRateMast.getShape().getId()));
						//model.addAttribute("sieveMap",stoneChartService.getStoneChartSieveList(shape.getId()));
						model.addAttribute("sizeGroupMap",sizeGroupService.getSizeGroupList(shape.getId()));
						
						  if(stoneRateMast.getSizeGroup() !=null) {
							  
						  model.addAttribute("sieveMap",stoneChartService.getStoneChartList(stoneRateMast.getSizeGroup().getId())); } 
					}
				}
			}
				return "stoneRateMast/add";
		
		
	}
	
	
	@RequestMapping("/stoneRateMast/view/{id}")
	public String view(@PathVariable int id, Model model,Principal principal) {
		
		DecimalFormat df = new DecimalFormat("#.####");
		StoneRateMast stoneRateMast = stoneRateMastService.findOne(id);

		model.addAttribute("stoneRateMast", stoneRateMast);
		model.addAttribute("fromWeightValue", df.format(stoneRateMast.getFromWeight()));
		model.addAttribute("toWeightValue", df.format(stoneRateMast.getToWeight()));
		model = populateModel(model);
		
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);

		MenuMast menuMast = menuMastService.findByMenuNm("stoneRateMast");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("canView",roleRights.getCanView());
		model.addAttribute("editRight",roleRights.getCanEdit());
		
		if (stoneRateMast != null) {
			if(stoneRateMast.getShape() != null){
				Shape shape = stoneRateMast.getShape();
				model.addAttribute("qualityMap",qualityService.getQualityList(shape.getId()));
				model.addAttribute("chartMap",stoneChartService.getStoneChartList(stoneRateMast.getShape().getId()));
				//model.addAttribute("sieveMap",stoneChartService.getStoneChartSieveList(shape.getId()));
				model.addAttribute("sizeGroupMap",sizeGroupService.getSizeGroupList(shape.getId()));
				
				  if(stoneRateMast.getSizeGroup() !=null) {
					  SizeGroup sizeGroup =stoneRateMast.getSizeGroup();
				  model.addAttribute("sieveMap",stoneChartService.getStoneChartList(sizeGroup.getId())); } 
			}
		}
		
		

		return "stoneRateMast/add";
	}
	
	
	@RequestMapping("/stoneRateMast/delete/{id}")
	public String delete(@PathVariable int id) {
		stoneRateMastService.delete(id);
		return "redirect:/manufacturing/masters/stoneRateMast.html";
	}
	
	
	@RequestMapping("/stoneRateMast/uploadExcel")
	public String excelFilePage(Model model){
		model.addAttribute("tableDisp", "no");
		return "uploadExcelstoneRateMast";
	}
	
	
	@RequestMapping(value = "/stoneRateMast/commonExcelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model,
			@RequestParam("excelfile") MultipartFile excelfile,HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile,RedirectAttributes redirectAttributes){

		String retVal ="";
		try {
			if(!tempExcelFile.isEmpty()){
			
			List<StoneRateMast> stoneRateMastList = new ArrayList<StoneRateMast>();
			
			    if (tempExcelFile.endsWith("xlsx")) {
			    	int i=1;
			    	//int j=0;
			    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			        XSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						//LabourCharge labourCharge = new LabourCharge();
						StoneRateMast stoneRateMast = new StoneRateMast();
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
							remark = "No such client exists";
							status = "verification failed";
						}
						
						
						StoneType stoneType = stoneTypeService.findByName(row.getCell(1).toString());
						if(stoneType == null){
							remark = "No such stoneType exists";
							status = "verification failed";
						}
						
						Shape shape = shapeService.findByName(row.getCell(2).toString());
						if(shape == null){
							remark = "No such shape exists";
							status = "verification failed";
						}
						
						Quality quality =qualityService.findByShapeAndNameAndDeactive(shape, row.getCell(3).toString(), false);
						if(quality == null){
							remark = "No such quality exists";
							status = "verification failed";
						}
						
						SizeGroup sizeGroup =sizeGroupService.findByShapeAndNameAndDeactive(shape, row.getCell(4).toString(), false);
						if(sizeGroup == null){
							remark = "No such quality exists";
							status = "verification failed";
						}	
						/* StoneChart stoneChart= stoneChartService.findByQualityAndSizeAndDeactive(quality, row.getCell(4).toString(), false);
						 if(stoneChart == null){
							
								remark = "No such size exists";
								status = "verification failed";
							}*/
						
						stoneRateMast.setSizeGroup(sizeGroup);		
						stoneRateMast.setParty(party);
						stoneRateMast.setStoneType(stoneType);
						stoneRateMast.setShape(shape);
						stoneRateMast.setQuality(quality);
						stoneRateMast.setStoneRate(Double.parseDouble(row.getCell(5).toString()));
						stoneRateMast.setPerPcRate(Double.parseDouble(row.getCell(6).toString()));
						stoneRateMast.setSieve(row.getCell(7).toString());
				//		stoneRateMast.setDeactive(Boolean.parseBoolean(row.getCell(7).toString()));
						
						if(party != null && stoneType != null && shape != null && quality != null && stoneRateMast.getStoneRate() != null && stoneRateMast.getPerPcRate() != null){
							/*StoneRateMast stoneRateMastCheck = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndPartyAndFromWeightAndToWeightAndDeactive(stoneRateMast.getStoneType(),
									stoneRateMast.getShape(), stoneRateMast.getQuality(), stoneRateMast.getParty(), stoneRateMast.getFromWeight(), stoneRateMast.getToWeight(), false);*/
							StoneRateMast stoneRateMastCheck = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveAndPartyAndSieve(stoneRateMast.getStoneType(),
									stoneRateMast.getShape(), stoneRateMast.getQuality(), stoneRateMast.getSizeGroup(),false,stoneRateMast.getParty(),stoneRateMast.getSieve());
							
							if(stoneRateMastCheck != null){
								remark = "Duplicate Record";
								status = "verification failed";
							}
						}
						
						
						if(stoneRateMast.getStoneRate() > 0 && stoneRateMast.getPerPcRate() > 0){
							remark = "At a time one entry either PerCaratRate or PerPcsRate";
							status = "verification failed";
						}else if (stoneRateMast.getStoneRate() == 0 && stoneRateMast.getPerPcRate() == 0) {
							remark = "At a time one entry either PerCaratRate or PerPcsRate";
							status = "verification failed";
						}
						
						stoneRateMast.setModiBy(status);
						stoneRateMast.setCreatedBy(remark);
						stoneRateMastList.add(stoneRateMast);
					}
					
					workbook.close();
					
			    } else if (tempExcelFile.endsWith("xls")) {
			    	int i = 1;
					HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
					HSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						StoneRateMast stoneRateMast = new StoneRateMast();
						HSSFRow row = worksheet.getRow(i++);
				
						if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
							continue;
						}
						
						/*
						 * for(int j=0;j<row.getLastCellNum();j++){ Cell cell = row.getCell(j);
						 * cell.setCellType (Cell.CELL_TYPE_STRING); j++; }
						 */
					
						//note : temporary listR
						//remark is set in createdBy for temporary list
						//status is set in updatedBy for temporary list
						
						String remark = "successfull";
						String status = "verified";
						
						
						Party party = partyService.findByPartyCodeAndDeactive(row.getCell(0).toString(), false);
						
						
						if(party == null){
							remark = "No such client exists";
							status = "verification failed";
						}
						
						
						
						
						StoneType stoneType = stoneTypeService.findByName(row.getCell(1).toString());
						if(stoneType == null){
							remark = "No such stoneType exists";
							status = "verification failed";
						}
						
						Shape shape = shapeService.findByName(row.getCell(2).toString());
						if(shape == null){
							remark = "No such shape exists";
							status = "verification failed";
						}
						
						/*Quality quality = qualityService.findByName(row.getCell(3).toString());*/
						
						Quality quality =qualityService.findByShapeAndNameAndDeactive(shape, row.getCell(3).toString(), false);
						if(quality == null){
							remark = "No such quality exists";
							status = "verification failed";
						}
						
						SizeGroup sizeGroup =sizeGroupService.findByShapeAndNameAndDeactive(shape, row.getCell(4).toString(), false);
						if(sizeGroup == null){
							remark = "No such quality exists";
							status = "verification failed";
						}	
						
						/* StoneChart stoneChart= stoneChartService.findByQualityAndSizeAndDeactive(quality, row.getCell(4).toString(), false);
						 if(stoneChart == null){
								remark = "No such size exists";
								status = "verification failed";
						  }		*/		
						
						stoneRateMast.setParty(party);
						stoneRateMast.setStoneType(stoneType);
						stoneRateMast.setSizeGroup(sizeGroup);
						stoneRateMast.setShape(shape);
						stoneRateMast.setQuality(quality);
						
						stoneRateMast.setStoneRate(Double.parseDouble(row.getCell(5).toString()));
						stoneRateMast.setPerPcRate((double) (row.getCell(6).toString() != null ? Double.parseDouble(row.getCell(6).toString())  : 0) );
						stoneRateMast.setSieve(row.getCell(7).toString() != null ? row.getCell(7).toString() :"");
						
						//	stoneRateMast.setDeactive(Boolean.parseBoolean(row.getCell(7).toString()));
						
						if(party != null && stoneType != null && shape != null && quality != null && stoneRateMast.getStoneRate() != null && stoneRateMast.getPerPcRate() != null){
							/*StoneRateMast stoneRateMastCheck = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndPartyAndFromWeightAndToWeightAndDeactive(stoneRateMast.getStoneType(),
									stoneRateMast.getShape(), stoneRateMast.getQuality(), stoneRateMast.getParty(), stoneRateMast.getFromWeight(), stoneRateMast.getToWeight(), false);*/
							
							StoneRateMast stoneRateMastCheck = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveAndPartyAndSieve(stoneRateMast.getStoneType(),
									stoneRateMast.getShape(), stoneRateMast.getQuality(), stoneRateMast.getSizeGroup(),false,stoneRateMast.getParty(),stoneRateMast.getSieve());
							
							if(stoneRateMastCheck != null){
								remark = "Duplicate Record";
								status = "verification failed";
							}
						}
						
						
						if(stoneRateMast.getStoneRate() > 0 && stoneRateMast.getPerPcRate() > 0){
							remark = "At a time one entry either PerCaratRate or PerPcsRate";
							status = "verification failed";
						}else if (stoneRateMast.getStoneRate() == 0 && stoneRateMast.getPerPcRate() == 0) {
							remark = "At a time one entry either PerCaratRate or PerPcsRate";
							status = "verification failed";
						}
						
						stoneRateMast.setModiBy(status);
						stoneRateMast.setCreatedBy(remark);
						stoneRateMastList.add(stoneRateMast);
					}
					workbook.close();
					
			    } else {
			        throw new IllegalArgumentException("The specified file is not Excel file");
			    }
			    
			    
			    session.setAttribute("stoneRateSessionList", stoneRateMastList);
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
		//model.addAttribute("flag", flag);
		
		return "stoneRateMastFileUploaded"; 
	}
	
	
	@RequestMapping("/stoneRateMast/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		DecimalFormat df = new DecimalFormat("#.####");
		
		@SuppressWarnings("unchecked")
		List<StoneRateMast> stoneRateMasts = (List<StoneRateMast>) httpSession.getAttribute("stoneRateSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(1).append(",\"rows\": [");
		
		for(StoneRateMast stoneRateMast:stoneRateMasts){
			sb.append("{\"id\":\"")
			.append(stoneRateMast.getId())
			.append("\",\"party\":\"")
			.append((stoneRateMast.getParty() != null ? stoneRateMast.getParty().getPartyCode() : ""))
			.append("\",\"stoneType\":\"")
			.append((stoneRateMast.getStoneType() != null ? stoneRateMast.getStoneType().getName() : ""))
			.append("\",\"shape\":\"")
			.append((stoneRateMast.getShape() != null ? stoneRateMast.getShape().getName() : ""))
			.append("\",\"quality\":\"")
			.append((stoneRateMast.getQuality() != null ? stoneRateMast.getQuality().getName() : ""))
			.append("\",\"size\":\"")
			.append((stoneRateMast.getSizeGroup() != null ? stoneRateMast.getSizeGroup().getName() : ""))
			.append("\",\"stoneRate\":\"")
			.append(stoneRateMast.getStoneRate())
			.append("\",\"sieve\":\"")
			.append(stoneRateMast.getSieve())
			.append("\",\"perPcRate\":\"")
			.append(stoneRateMast.getPerPcRate())
			.append("\",\"fromWeight\":\"")
			.append(df.format(stoneRateMast.getFromWeight()))
			.append("\",\"toWeight\":\"")
			.append(df.format(stoneRateMast.getToWeight()))
			.append("\",\"deactive\":\"")
			.append((stoneRateMast.getDeactive() == null ? "true" : stoneRateMast.getDeactive()))
			//note : temporary list
			//remark is set in createdBy for temporary list
			//status is set in updatedBy for temporary list
			.append("\",\"status\":\"")
			.append(stoneRateMast.getModiBy() != null ? stoneRateMast.getModiBy() : "not perfect")
			.append("\",\"remark\":\"")
			.append((stoneRateMast.getCreatedBy() != null ? stoneRateMast.getCreatedBy() : "unknown error"))
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	
	@RequestMapping(value = "/stoneRateMast/excelSave" , method = RequestMethod.POST)
	@ResponseBody
	public String saveExcelData(
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
			
			StoneRateMast stoneRateMastNew = new StoneRateMast();
			
			
			stoneRateMastNew.setParty(partyService.findByPartyCodeAndDeactive(jsonDtNew.getString("party"), false));
			stoneRateMastNew.setStoneType(stoneTypeService.findByName(jsonDtNew.getString("stoneType")));
			stoneRateMastNew.setShape(shapeService.findByName(jsonDtNew.getString("shape")));
			stoneRateMastNew.setQuality(qualityService.findByShapeAndName(stoneRateMastNew.getShape(),jsonDtNew.getString("quality")));
			stoneRateMastNew.setSizeGroup(sizeGroupService.findByName(jsonDtNew.getString("size")));
			/*stoneRateMast.setFromWeight(Double.parseDouble(jsonDtNew.getString("fromWeight")));
			stoneRateMast.setToWeight(Double.parseDouble(jsonDtNew.getString("toWeight")));*/
			stoneRateMastNew.setSieve(jsonDtNew.getString("sieve"));
			stoneRateMastNew.setStoneRate(Double.parseDouble(jsonDtNew.getString("stoneRate")));
			stoneRateMastNew.setPerPcRate(Double.parseDouble(jsonDtNew.getString("perPcRate")));
			stoneRateMastNew.setDeactive(false);
			stoneRateMastNew.setCreatedDate(new  java.util.Date());
			stoneRateMastNew.setCreatedBy(principal.getName());
		
			
			stoneRateMastService.save(stoneRateMastNew);
			retVal = "-2";
			
			
		}
		
		
		return retVal;
		
	}
	
	
	
	/*@ModelAttribute("stoneRateMast") StoneRateMast stoneRateMast,*/
	
	//--------stone rate charge excel file download----//
	
		@RequestMapping("/stoneRate/downloadExcel/format")
		@ResponseBody
		public String excelFormatDownload(
				@RequestParam(value = "headingVal") String headingVal,Principal principal){
			
			
			
			String retVal = "-1";
			String fileName = principal.getName()+new java.util.Date().getTime()+".xls";
			String filePath = uploadDirecotryPath + File.separator +"excelfilecontent" + File.separator;
			String tempHeadVal[] = headingVal.split(",");
			
			System.out.println("------length----->>>>>>>>>>>>"+tempHeadVal.length);
			
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
		
	
		@RequestMapping("/stoneRateMast/sizeGroup/list")
		@ResponseBody
		public String sizeGroupList(@RequestParam(value = "shpId") Integer shpId) {
			StringBuilder sb = new StringBuilder();
			Map<Integer, String> sizeGroupMap = sizeGroupService
					.getSizeGroupList(shpId);

			sb.append("<select id=\"sizeGroup.id\" name=\"sizeGroup.id\" class=\"form-control\" onChange=\"javascript:popSieve(this.value)\">");
			sb.append("<option value=\"\">- Select Size Group -</option>");
			for (Object key : sizeGroupMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(sizeGroupMap.get(key)).append("</option>");
			}
			sb.append("</select>");

			return sb.toString();
		}
	

			@RequestMapping("/stoneRateMast/sieve/list")
			@ResponseBody
			public String sieveList(@RequestParam(value = "sizeGrpId", required =false) Integer sizeGrpId) 
			{
				StringBuilder sb = new StringBuilder();
				Map<String, String> sieveMap = stoneChartService.getStoneChartSieveList(sizeGrpId);
						

				sb.append("<select id=\"sieve\" name=\"sieve\" class=\"form-control\">");
				System.err.println(""+sb);
				
				sb.append("<option value=\"\">- Select Sieve -</option>");
				
				
				for (Object key : sieveMap.keySet()) {
					sb.append("<option value=\"").append(key.toString()).append("\">")
							.append(sieveMap.get(key)).append("</option>");
				}
				sb.append("</select>");

				System.err.println(""+sb.toString());
				return sb.toString();
			}
	
}

package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;


@RequestMapping("/manufacturing/masters")
@Controller
public class SettingChargeController {

	@Autowired
	private ISettingChargeService settingChargeService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;
	
	
	@ModelAttribute("settingCharge")
	public SettingCharge construct() {
		return new SettingCharge();
	}
	
	@ModelAttribute("settingQualityRate")
	public SettingQualityRate constructSetQR() {
		return new SettingQualityRate();
	}

	@RequestMapping("/settingCharge")
	public String settingCharge(Model model, Principal principal) {
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("settingCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "settingCharge";
			
		}else
			
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "settingCharge";
	
		//	model = popRoleRightModel(model, principal);
		
	}
	
	
	@RequestMapping("/settingCharge/listing")
	@ResponseBody
	public String settingChargeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,
			Principal principal) {
			
		
		StringBuilder sb = new StringBuilder();
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("settingCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	
	
		Page<SettingCharge> settingCharges = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	//	Long rowCount = null;
		
		//rowCount = settingChargeService.countAll(sort, search, true);
		settingCharges = settingChargeService.searchAll(limit, offset, sort, order,search, true);
		
		/*if (id == null) {
			rowCount = settingChargeService.count(sort, search, true);
			settingCharges = settingChargeService.findAll(limit, offset, sort, order,search);
			
		} else {
			rowCount = settingChargeService.countByShape(shapeService.findOne(id), sort, search,true);
			settingCharges = settingChargeService.findByShape(shapeService.findOne(id), limit, offset, sort, order, search, true);
			
		}*/

		
		sb.append("{\"total\":").append(settingCharges.getTotalElements()).append(",\"rows\": [");

		for (SettingCharge settingCharge : settingCharges) {
			sb.append("{\"id\":\"")
					.append(settingCharge.getId())
					.append("\",\"stoneType\":\"")
					.append((settingCharge.getStoneType() != null ? settingCharge.getStoneType().getName() : ""))
					.append("\",\"shape\":\"")
					.append((settingCharge.getShape() != null ? settingCharge.getShape().getName() : ""))
					.append("\",\"party\":\"")
					.append((settingCharge.getParty() != null ? settingCharge.getParty().getPartyCode() : ""))
					.append("\",\"metal\":\"")
					.append(settingCharge.getMetal() !=null ? settingCharge.getMetal().getName() :"")
					.append("\",\"setting\":\"")
					.append((settingCharge.getSetting() != null ? settingCharge.getSetting().getName() : ""))
					.append("\",\"settingType\":\"")
					.append((settingCharge.getSettingType() != null ? settingCharge.getSettingType().getName() : ""))
					.append("\",\"fromWeight\":\"")
					.append(settingCharge.getFromWeight())
					.append("\",\"toWeight\":\"")
					.append(settingCharge.getToWeight())
					.append("\",\"rate\":\"")
					.append((settingCharge.getRate() == null ? "" : settingCharge.getRate()))
					.append("\",\"updatedBy\":\"")
					.append((settingCharge.getModiBy() == null ? "" : settingCharge.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((settingCharge.getModiDate() == null ? "" : settingCharge.getModiDate()))
					.append("\",\"deactive\":\"")
					.append((settingCharge.getDeactive() == null ? "": (settingCharge.getDeactive() ? "Deactive": "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((settingCharge.getDeactiveDt() == null ? "": settingCharge.getDeactiveDt()));
			
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
						
						sb.append("\",\"action1\":\"")
								.append("<a href='/jewels/manufacturing/masters/settingCharge/edit/")
								.append(settingCharge.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"")
									.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/settingCharge/delete/")
									.append(settingCharge.getId()).append(".html'")
									.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(settingCharge.getId())
									.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
									.append("\"},");
					}else{
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='/jewels/manufacturing/masters/settingCharge/edit/")
								.append(settingCharge.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/settingCharge/delete/")
									.append(settingCharge.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(settingCharge.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
					}
					
					

						/*sb.append("\",\"viewMode\":\"");
						if (roleRights.getCanView()){
							sb.append("<a href='/jewels/manufacturing/masters/settingCharge/view/")
							.append(settingCharge.getId()).append("/"+flag);
						}else{
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(".html' class='btn btn-xs btn-info' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>");	
			
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append(
									"<a href='/jewels/manufacturing/masters/settingCharge/edit/")
									.append(settingCharge.getId()).append("/"+flag).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/settingCharge/delete/")
									.append(settingCharge.getId()).append("/"+flag).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(settingCharge.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");*/

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	
	@RequestMapping("/settingCharge/customSearch/listing")
	@ResponseBody
	public String labourChargeCustomListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "partyName") String partyName,
			@RequestParam(value = "metalNm") String metalNm,
			@RequestParam(value = "stoneTypeNm") String stoneTypeNm,
			@RequestParam(value = "shapeNm") String shapeNm,
			@RequestParam(value = "settingNm") String settingNm,
			@RequestParam(value = "settingTypeNm") String settingTypeNm,
			Principal principal) {
		
		
			
				
			
			StringBuilder sb = new StringBuilder();
			//list code 3 line--//
			User loginUser = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(loginUser);
			MenuMast menuMast = menuMastService.findByMenuNm("settingCharge");
			RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
			Page<SettingCharge> settingCharges = null;
			Long rowCount = null;
			
			
			rowCount = settingChargeService.customSearchCount(partyName, metalNm, stoneTypeNm, shapeNm, settingNm, settingTypeNm);
			settingCharges = settingChargeService.customSearch(limit, offset, sort, order,
					partyName, metalNm, stoneTypeNm, shapeNm, settingNm, settingTypeNm);
			
			
			sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

			for (SettingCharge settingCharge : settingCharges) {
				sb.append("{\"id\":\"")
						.append(settingCharge.getId())
						.append("\",\"stoneType\":\"")
						.append((settingCharge.getStoneType() != null ? settingCharge.getStoneType().getName() : ""))
						.append("\",\"shape\":\"")
						.append((settingCharge.getShape() != null ? settingCharge.getShape().getName() : ""))
						.append("\",\"party\":\"")
						.append((settingCharge.getParty() != null ? settingCharge.getParty().getPartyCode() : ""))
						.append("\",\"metal\":\"")
						.append(settingCharge.getMetal() !=null ? settingCharge.getMetal().getName() :"")
						.append("\",\"setting\":\"")
						.append((settingCharge.getSetting() != null ? settingCharge.getSetting().getName() : ""))
						.append("\",\"settingType\":\"")
						.append((settingCharge.getSettingType() != null ? settingCharge.getSettingType().getName() : ""))
						.append("\",\"fromWeight\":\"")
						.append(settingCharge.getFromWeight())
						.append("\",\"toWeight\":\"")
						.append(settingCharge.getToWeight())
						.append("\",\"rate\":\"")
						.append((settingCharge.getRate() == null ? "" : settingCharge.getRate()))
						.append("\",\"updatedBy\":\"")
						.append((settingCharge.getModiBy() == null ? "" : settingCharge.getModiBy()))
						.append("\",\"updatedDt\":\"")
						.append((settingCharge.getModiDate() == null ? "" : settingCharge.getModiDate()))
						.append("\",\"deactive\":\"")
						.append((settingCharge.getDeactive() == null ? "": (settingCharge.getDeactive() ? "Deactive": "Active")))
						.append("\",\"deactiveDt\":\"")
						.append((settingCharge.getDeactiveDt() == null ? "": settingCharge.getDeactiveDt()));


					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
						
						sb.append("\",\"action1\":\"")
								.append("<a href='/jewels/manufacturing/masters/settingCharge/edit/")
								.append(settingCharge.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"")
									.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/settingCharge/delete/")
									.append(settingCharge.getId()).append(".html'")
									.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(settingCharge.getId())
									.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
									.append("\"},");
					}else{
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='/jewels/manufacturing/masters/settingCharge/edit/")
								.append(settingCharge.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/settingCharge/delete/")
									.append(settingCharge.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(settingCharge.getId())
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
	
	
	
	
	@RequestMapping("/settingCharge/add")
	public String add(Model model, Principal principal) {
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("settingCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "settingCharge/add";

		}else	
		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}
				return "settingCharge/add";
		
	//	model = popRoleRightModel(model, principal);
		
		
	}
	
	public Model popRoleRightModel(Model model,Principal principal){
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);

		MenuMast menuMast = menuMastService.findByMenuNm("settingCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("canAdd", roleRights.getCanAdd());
		model.addAttribute("canDelete", roleRights.getCanDelete());
		model.addAttribute("canEdit", roleRights.getCanEdit());
		
		return model;
	}
	
	private Model populateModel(Model model) {
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("partyMap",partyService.getPartyList());
		model.addAttribute("settingMap", settingService.getSettingList());
		model.addAttribute("settingTypeMap", settingTypeService.getSettingTypeList());
		return model;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/settingCharge/add", method = RequestMethod.POST)
	public String addEditCategory(
			@Valid @ModelAttribute("settingCharge") SettingCharge settingCharge,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "jData") String data,
			@RequestParam(value = "setQrPkDeleteIds") String setQrPkDeleteIds,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/settingCharge/add.html?success=true";

		if (result.hasErrors()) {
			return "settingCharge/add";
		}
		
		
		String settingChargeCheck = "";
		
		settingChargeCheck= settingChargeService.CheckDuplicate(id, settingCharge.getStoneType().getId(),settingCharge.getShape().getId(), settingCharge.getParty().getId(), settingCharge.getMetal().getId(),
				settingCharge.getSetting().getId(), settingCharge.getSettingType().getId(), settingCharge.getFromWeight(), settingCharge.getToWeight());
		
		if(settingChargeCheck.equalsIgnoreCase("true")){
			return retVal = "-1";
		}
		
		if(settingCharge.getToWeight() < settingCharge.getFromWeight()){
			return retVal = "-5";
		}
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			settingCharge.setCreatedBy(principal.getName());
			settingCharge.setCreatedDate(new java.util.Date());
			
			
		} else {
			
	
			settingCharge.setModiBy(principal.getName());
			settingCharge.setModiDate(new java.util.Date());
			
			if(settingCharge.getDeactive().equals(true)){
				settingCharge.setDeactiveDt(new Date());
			}

			

			action = "updated";
			retVal = "/jewels/manufacturing/masters/settingCharge.html?id="+ settingCharge.getShape().getId();

		}
		
		settingChargeService.save(settingCharge);
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		
		
			
		
			Shape shape = settingCharge.getShape();
			
			JSONArray jsonDt = new JSONArray(data);
			for (int y = 0; y < jsonDt.length(); y++) {
				JSONObject dataBagDt = (JSONObject) jsonDt.get(y);
				
				Integer setQRId = dataBagDt.getInt("id");
				
				if(setQRId == 0){
					//add
					SettingQualityRate settingQualityRate = new SettingQualityRate();
					settingQualityRate.setQuality(qualityService.findByShapeAndNameAndDeactive(shape, dataBagDt.getString("quality").toString().trim(),false));
					settingQualityRate.setQualityRate(Double.parseDouble(dataBagDt.getString("qualityRate").toString()));
					settingQualityRate.setSettingCharge(settingCharge);
					settingQualityRate.setCreatedBy(principal.getName());
					settingQualityRate.setCreatedDate(new java.util.Date());
					settingQualityRateService.save(settingQualityRate);
					
				}else{
					//update
					SettingQualityRate settingQualityRate = settingQualityRateService.findOne(dataBagDt.getInt("id"));
					settingQualityRate.setQuality(qualityService.findByShapeAndNameAndDeactive(shape, dataBagDt.getString("quality").toString().trim(),false));
					settingQualityRate.setQualityRate(Double.parseDouble(dataBagDt.getString("qualityRate").toString()));
					settingQualityRate.setModiBy(principal.getName());
					settingQualityRate.setModiDt(new java.util.Date());
					settingQualityRateService.save(settingQualityRate);
					
				}
				
				
			}
			
			
			if(setQrPkDeleteIds.length() > 0){
				
				String tempIds[] = setQrPkDeleteIds.split(",");
				for(int i=0;i<tempIds.length;i++){
					SettingQualityRate settingQualityRate = settingQualityRateService.findOne(Integer.parseInt(tempIds[i]));
					if(settingQualityRate != null){
						settingQualityRateService.delete(settingQualityRate.getId());
					}
				}
				
			}
		
		
		return retVal;
	}
	
	
	@RequestMapping("/settingCharge/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		SettingCharge settingCharge = settingChargeService.findOne(id);
		model.addAttribute("settingCharge", settingCharge);
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("settingCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "settingCharge/add";

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

		return "settingCharge/add";
		
	//	model = popRoleRightModel(model, principal);

		
		
		
	}
	
	
	@RequestMapping("/settingCharge/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		SettingCharge settingCharge = settingChargeService.findOne(id);
		model.addAttribute("settingCharge", settingCharge);
		model = populateModel(model);
		
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);

		MenuMast menuMast = menuMastService.findByMenuNm("settingCharge");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("canView",roleRights.getCanView());
		model.addAttribute("editRight",roleRights.getCanEdit());
		
		return "settingCharge/add";
	}
	

	@RequestMapping("/settingCharge/delete/{id}")
	public String delete(@PathVariable int id) {
		settingChargeService.delete(id);
		return "redirect:/manufacturing/masters/settingCharge.html";
	}
	
	
	@RequestMapping("/settingCharge/uploadExcel")
	public String excelFilePage(Model model){
		model.addAttribute("tableDisp", "no");
		return "uploadExcelSetting";
	}
	
	
	
	@RequestMapping(value = "/settingCharge/commonExcelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model,
			@RequestParam("excelfile") MultipartFile excelfile,HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile,RedirectAttributes redirectAttributes){
		
		String settingChargeCheck = "";
		String retVal ="";
		
		try {
			if(!tempExcelFile.isEmpty()){
			
			List<SettingCharge> settingChargeList = new ArrayList<SettingCharge>();
			
			    if (tempExcelFile.endsWith("xlsx")) {
			    	int i=1;
			    	//int j=0;
			    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			        XSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						SettingCharge settingCharge = new SettingCharge();
						
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
						
						Party party =partyService.findByPartyCodeAndDeactive(row.getCell(0).toString(), false);
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
						
						Metal metal =metalService.findByName(row.getCell(6).toString());
						if(metal == null){
							remark = "No such Metal exists";
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
						
						Setting setting = settingService.findByName(row.getCell(3).toString());
						if(setting == null){
							remark = "No such setting exists";
							status = "verification failed";
						}
						
						SettingType settingType = settingTypeService.findByName(row.getCell(4).toString());
						if(settingType == null){
							remark = "No such settingType exists";
							status = "verification failed";
						}
						
						if(Double.parseDouble(row.getCell(7).toString()) <=0 ){
							remark = "From weight must be greater than 0";
							status = "verification failed";
						}
						
						if(Double.parseDouble(row.getCell(8).toString()) <=0 ){
							remark = "To weight must be greater than 0";
							status = "verification failed";
						}
						
						settingCharge.setMetal(metal);
						settingCharge.setParty(party);
						settingCharge.setStoneType(stoneType);
						settingCharge.setShape(shape);
						settingCharge.setSetting(setting);
						settingCharge.setSettingType(settingType);
						settingCharge.setRate(Double.parseDouble(row.getCell(5).toString()));
						settingCharge.setFromWeight(Double.parseDouble(row.getCell(7).toString()));
						settingCharge.setToWeight(Double.parseDouble(row.getCell(8).toString()));
						settingCharge.setDeactive(false);
						
						
						if(party != null && stoneType != null && shape != null && setting != null && settingType != null){
							
							/*SettingCharge settingChargeCheck = settingChargeService.findByStoneTypeAndShapeAndPartyAndSettingAndSettingTypeAndDeactive(settingCharge.getStoneType(),settingCharge.getShape(),
									settingCharge.getParty(), settingCharge.getSetting(), settingCharge.getSettingType(),false);*/
							
							settingChargeCheck= settingChargeService.CheckDuplicate(settingCharge.getId(), settingCharge.getStoneType().getId(),settingCharge.getShape().getId(), settingCharge.getParty().getId(), settingCharge.getMetal().getId(),
									settingCharge.getSetting().getId(), settingCharge.getSettingType().getId(), settingCharge.getFromWeight(), settingCharge.getToWeight());
							
							if(settingChargeCheck.equalsIgnoreCase("true")){
								remark = "Duplicate Record";
								status = "verification failed";
							}
						}
						
						
						settingCharge.setModiBy(status);
						settingCharge.setCreatedBy(remark);
						settingChargeList.add(settingCharge);
					}
					
					workbook.close();
					
			    } else if (tempExcelFile.endsWith("xls")) {
			    	int i = 1;
					HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
					HSSFSheet worksheet = workbook.getSheetAt(0);
					while (i <= worksheet.getLastRowNum()) {
						SettingCharge settingCharge = new SettingCharge();
						
						HSSFRow row = worksheet.getRow(i++);
					
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
						
						Metal metal =metalService.findByName(row.getCell(6).toString());
						if(metal == null){
							remark = "No such Metal exists";
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
						
						Setting setting = settingService.findByName(row.getCell(3).toString());
						if(setting == null){
							remark = "No such setting exists";
							status = "verification failed";
						}
						
						SettingType settingType = settingTypeService.findByName(row.getCell(4).toString());
						if(settingType == null){
							remark = "No such settingType exists";
							status = "verification failed";
						}
						if(Double.parseDouble(row.getCell(7).toString()) <=0 ){
							remark = "From weight must be greater than 0";
							status = "verification failed";
						}
						
						if(Double.parseDouble(row.getCell(8).toString()) <=0 ){
							remark = "To weight must be greater than 0";
							status = "verification failed";
						}
						
						settingCharge.setMetal(metal);
						settingCharge.setParty(party);
						settingCharge.setStoneType(stoneType);
						settingCharge.setShape(shape);
						settingCharge.setSetting(setting);
						settingCharge.setSettingType(settingType);
						settingCharge.setFromWeight(Double.parseDouble(row.getCell(7).toString()));
						settingCharge.setToWeight(Double.parseDouble(row.getCell(8).toString()));
						settingCharge.setRate(Double.parseDouble(row.getCell(5).toString()));
						settingCharge.setDeactive(false);
						
						
						if(party != null && stoneType != null && shape != null && setting != null && settingType != null){
							
							settingChargeCheck= settingChargeService.CheckDuplicate(settingCharge.getId(), settingCharge.getStoneType().getId(),settingCharge.getShape().getId(), settingCharge.getParty().getId(), settingCharge.getMetal().getId(),
									settingCharge.getSetting().getId(), settingCharge.getSettingType().getId(), settingCharge.getFromWeight(), settingCharge.getToWeight());
							
							if(settingChargeCheck.equalsIgnoreCase("true")){
								remark = "Duplicate Record";
								status = "verification failed";
							}
						}
						
						
						settingCharge.setModiBy(status);
						settingCharge.setCreatedBy(remark);
						settingChargeList.add(settingCharge);
					}
					workbook.close();
					
			    } else {
			        throw new IllegalArgumentException("The specified file is not Excel file");
			    }
			    
			    
			    session.setAttribute("settingChargeSessionList", settingChargeList);
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
		
		return "settingChargeFileUploaded";
	}
	
	
	
	@RequestMapping("/settingCharge/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<SettingCharge> settingCharges = (List<SettingCharge>) httpSession.getAttribute("settingChargeSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(1).append(",\"rows\": [");
		
		for(SettingCharge settingCharge:settingCharges){
			
			sb.append("{\"id\":\"")
			.append(settingCharge.getId())
			.append("\",\"stoneType\":\"")
			.append((settingCharge.getStoneType() != null ? settingCharge.getStoneType().getName() : ""))
			.append("\",\"shape\":\"")
			.append((settingCharge.getShape() != null ? settingCharge.getShape().getName() : ""))
			.append("\",\"party\":\"")
			.append((settingCharge.getParty() != null ? settingCharge.getParty().getPartyCode() : ""))
			.append("\",\"metal\":\"")
			.append((settingCharge.getMetal() != null ? settingCharge.getMetal().getName() : ""))
			.append("\",\"setting\":\"")
			.append((settingCharge.getSetting() != null ? settingCharge.getSetting().getName() : ""))
			.append("\",\"settingType\":\"")
			.append((settingCharge.getSettingType() != null ? settingCharge.getSettingType().getName() : ""))
			.append("\",\"fromWeight\":\"")
			.append(settingCharge.getFromWeight())
			.append("\",\"toWeight\":\"")
			.append(settingCharge.getToWeight())
			.append("\",\"rate\":\"")
			.append((settingCharge.getRate() == null ? "" : settingCharge.getRate()))
			//note : temporary list
			//remark is set in createdBy for temporary list
			//status is set in updatedBy for temporary list
			.append("\",\"status\":\"")
			.append(settingCharge.getModiBy() != null ? settingCharge.getModiBy() : "not perfect")
			.append("\",\"remark\":\"")
			.append((settingCharge.getCreatedBy() != null ? settingCharge.getCreatedBy() : "unknown error"))
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	
	@RequestMapping(value = "/settingCharge/excelSave" , method = RequestMethod.POST)
	@ResponseBody
	public String saveExcelData(
			@ModelAttribute("settingCharge") SettingCharge settingCharge,
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
			SettingCharge settingChargeNew = new SettingCharge();
			settingChargeNew.setMetal(metalService.findByName(jsonDtNew.getString("metal")));
			settingChargeNew.setParty(partyService.findByPartyCodeAndDeactive(jsonDtNew.getString("party"), false));
			settingChargeNew.setStoneType(stoneTypeService.findByName(jsonDtNew.getString("stoneType")));
			settingChargeNew.setShape(shapeService.findByName(jsonDtNew.getString("shape")));
			settingChargeNew.setSetting(settingService.findByName(jsonDtNew.getString("setting")));
			settingChargeNew.setSettingType(settingTypeService.findByName(jsonDtNew.getString("settingType")));
			settingChargeNew.setFromWeight(Double.parseDouble(jsonDtNew.getString("fromWeight")));
			settingChargeNew.setToWeight(Double.parseDouble(jsonDtNew.getString("toWeight")));
			settingChargeNew.setRate(Double.parseDouble(jsonDtNew.getString("rate")));
			settingChargeNew.setDeactive(false);
			settingChargeNew.setCreatedBy(principal.getName());
			settingChargeNew.setCreatedDate(new java.util.Date());
			
			settingChargeService.save(settingChargeNew);
			
			retVal = "-2";
		}
		
		
		return retVal;
		
	}
	
	
	//--------setting charge excel file download----//
	
	@RequestMapping("/settingCharge/downloadExcel/format")
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
	            
	            retVal = "-2";
	        }
		
		return retVal;
	}
	
	
	
	
	
	
	
	
	
}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILooseStkConversionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class StnLooseMtController {

	@Autowired
	private IStnLooseMtService stnLooseMtService;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private IPartyService partyService;

	@Autowired
	private IInwardTypeService inwardTypeService;

	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IStnLooseDtService stnLooseDtService;
	
	
	@Autowired
	private ISubShapeService subShapeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private ILooseStkConversionService looseStkConversionService;
	
	
	@ModelAttribute("stnLooseMt")
	public StnLooseMt constructMt() {
		return new StnLooseMt();
	}

	@ModelAttribute("stnLooseDt")
	public StnLooseDt constructDt() {
		return new StnLooseDt();
	}
	
	@ModelAttribute("looseStkConversion")
	public LooseStkConversion constructDtt() {
		return new LooseStkConversion();
	}
	
	
	
	@RequestMapping("/stnLooseMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "stnLooseMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "stnLooseMt";
		}
	}
	
	
	
	@RequestMapping("/stnLooseConversion")
	public String stnLooseConversion(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseConversion");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
		
			
			return "stnLooseConversion";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "stnLooseConversion";
		}
	}
	
	
	
	@RequestMapping("/stnLooseMt/listing")
	@ResponseBody
	public String stnLooseMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<StnLooseMt> stnLooseMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		stnLooseMts = stnLooseMtService.searchAll(limit, offset, sort, order, search);

		sb.append("{\"total\":").append(stnLooseMts.getTotalElements()).append(",\"rows\": [");

		for (StnLooseMt stnLooseMt : stnLooseMts) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(stnLooseMt.getInvDate()));

					sb.append("{\"id\":\"")
					.append(stnLooseMt.getId())
					.append("\",\"invNo\":\"")
					.append(stnLooseMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(stnLooseMt.getInvDateStr())
					.append("\",\"beNo\":\"")
					.append((stnLooseMt.getBeNo() != null ? stnLooseMt.getBeNo() : ""))
					.append("\",\"beDate\":\"")
					.append((stnLooseMt.getBeDateStr() != null ? stnLooseMt.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((stnLooseMt.getParty() != null ? stnLooseMt.getParty().getPartyCode() : ""))
					.append("\",\"supplier\":\"")
					.append((stnLooseMt.getSupplier() != null ? stnLooseMt.getSupplier().getPartyCode() : ""))
					.append("\",\"inwardType\":\"")
					.append((stnLooseMt.getInwardType() != null ? stnLooseMt.getInwardType().getName() : ""))
					.append("\",\"remark\":\"")
					.append((stnLooseMt.getRemark() != null ? stnLooseMt.getRemark() : ""));
				
					sb.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/stnLooseMt/edit/")
								.append(stnLooseMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteStnLooseMt(event,");					
								sb.append(stnLooseMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(stnLooseMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						
		
				sb.append("\",\"action3\":\"");

				sb.append("<a href='/jewels/manufacturing/transactions/stnLooseMt/view/")
						.append(stnLooseMt.getId()).append(".html'");
				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")

						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/stnLooseMt/edit/")
					.append(stnLooseMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteStnLooseMt(event,")	
						.append(stnLooseMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(stnLooseMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");

				sb.append("\",\"action3\":\"");
				if (roleRights.getCanView()) {
					sb.append("<a href='/jewels/manufacturing/transactions/stnLooseMt/view/")
							.append(stnLooseMt.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")

						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		
		return str;

	}
	
	
	@RequestMapping("/stnLooseMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = populateModel(model, principal);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			return "stnLooseMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		
		model.addAttribute("canEditTranddate", "false");
		return "stnLooseMt/add";
		}
	}
	
	
	private Model populateModel(Model model, Principal principal) {
		//Client
		model.addAttribute("clientMap", partyService.getExportClientPartyList());
		model.addAttribute("inwardTypeMap",inwardTypeService.getInwardTypeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		
		//Supplier
		model.addAttribute("partyMap", partyService.getExportClientPartyListForSupplier());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);

		return model;
	}
	
	
	@RequestMapping(value = "/stnLooseMt/add", method = RequestMethod.POST)
	public String addEditstnLooseMt(
			@Valid @ModelAttribute("stnLooseMt") StnLooseMt stnLooseMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		synchronized (this) {
		return stnLooseMtService.saveStnLooseMt(stnLooseMt, id, redirectAttributes, principal, result,vTranDate);
			
		}
		}
	
	
	@RequestMapping("/stnLooseMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		StnLooseMt stnLooseMt = stnLooseMtService.findOne(id);
		model.addAttribute("stnLooseMt", stnLooseMt);
		model = populateModel(model,principal);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			model.addAttribute("mtid", id);
			
			
			return "stnLooseMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		model.addAttribute("mtid", id);
		model.addAttribute("canEditTranddate", "false");
		

		return "stnLooseMt/add";
		}
	}
	
	@RequestMapping("/stnLooseMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		StnLooseMt stnLooseMt = stnLooseMtService.findOne(id);
		model.addAttribute("stnLooseMt", stnLooseMt);
		model = populateModel(model, principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			return "stnLooseMt/add";
			
		}else{

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canView", roleRights.getCanDelete());
		}
		
		model.addAttribute("canEditTranddate", "false");
		return "stnLooseMt/add";
		}
	}
	
	@RequestMapping("/stnLooseDt/addeditRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal) {
	
		
		
		if(dtId>0) {
			StnLooseDt stnLooseDt = stnLooseDtService.findOne(dtId);
			if(stnLooseDt.getSordDtId()!= null){
				return "-2";
			}else {
				if(stnLooseDt.getCarat().equals(stnLooseDt.getBalCarat())) {
					
					User user = userService.findByName(principal.getName());
					UserRole userRole = userRoleService.findByUser(user);
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
					
				
						return  "1";
						
				}else{
				
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					StnLooseMt stnLooseMt = stnLooseMtService.findOne(mtId);
						Date cDate = stnLooseMt.getInvDate();
						String dbDate = dateFormat.format(cDate);
					
					Date date = new Date();
					String curDate = dateFormat.format(date);

						if(dbDate.equals(curDate)){
						
							return  "1";
						
										
					}else {
						return  "-1";
					}
						
				}
					
				}else {
					
					return "-3";
					
				}
				
			}
			
		}else {
			
			
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
		
				return  "1";
				
		}else{
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			StnLooseMt stnLooseMt = stnLooseMtService.findOne(mtId);
				Date cDate = stnLooseMt.getInvDate();
				String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);

				if(dbDate.equals(curDate)){
				
					return  "1";
				
								
			}else {
				return  "-1";
			}
			}
			
		}
		
	}
	
	
	@RequestMapping(value="/stnLooset/addedit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		StnLooseDt stnLooseDt = new StnLooseDt();
		if(id>0) {
			stnLooseDt = stnLooseDtService.findOne(id);
		}
		model.addAttribute("stnLooseDt", stnLooseDt);
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
			
		if (stnLooseDt != null) {
			
			Shape shape = stnLooseDt.getShape();
			model.addAttribute("subShapeMap", (stnLooseDt.getSubShape() == null ? null : subShapeService.getSubShapeList(shape.getId())));
			model.addAttribute("qualityMap", (stnLooseDt.getQuality() == null ? null : qualityService.getQualityList(shape.getId())));	
			model.addAttribute("stoneChartMap", (stnLooseDt.getSize() == null ? null : stoneChartService.getStoneChartList(shape.getId())));
			model.addAttribute("sizeGroupMap", (stnLooseDt.getSizeGroup() == null ? null : sizeGroupService.getSizeGroupList(shape.getId())));
			model.addAttribute("sizeGroupName", (stnLooseDt.getSizeGroup() == null ? null : stnLooseDt.getSizeGroup().getName()));
	}
	
		return "stnLooseDt/add";
	}
	
	
	

	@RequestMapping("/stnLooseDt/add")
	public String add(Model model) {
		return "stnLooseDt/add";
	}

	@RequestMapping(value = "/stnLooseDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("stnLooseDt") StnLooseDt stnLooseDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "vCarat") Double vCarat,
			@RequestParam(value = "vStone") Integer vStone,
			@RequestParam(value = "sizeGroupStr") String sizeGroupStr,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "stnLooseDt/add";
		}
	
		retVal=stnLooseDtService.saveStnLooseDt(stnLooseDt, id, pInvNo, vCarat, vStone, sizeGroupStr, principal);
		if(retVal.contains(",")){
		String xy[]=retVal.split(",");
		
		redirectAttributes.addAttribute("success",true);
		redirectAttributes.addAttribute("action",xy[1]);
		return xy[0];
		}else{
			return retVal;
		}
		
	}
	
	
	@RequestMapping("/stnLooseDt/listing")
	@ResponseBody
	public String stnLooseDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,Principal principal){
		
		return stnLooseDtService.stnLooseDtListing(mtId, canViewFlag, principal);
	
	}	

	@RequestMapping("/stnLooseMt/delete/{id}")
	@ResponseBody
	public String deleteMt(@PathVariable int id,Principal principal) {
		String retVal = "";
		
		retVal= stnLooseMtService.deleteMt(id, principal);
		
		if(retVal.equalsIgnoreCase("-2") || retVal.equalsIgnoreCase("-3")){
			return retVal;
		}
		
		return "redirect:/manufacturing/transactions/stnLooseMt.html";						 

	}
	
	@RequestMapping("/stnLooseDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		
		String retVal = stnLooseDtService.stnLooseDtDelete(id, principal);
			
		return retVal;
	}
	
	
	@RequestMapping("/stnLoosePickup/listing")
	@ResponseBody
	public String stnLoosePickupList(Principal principal) throws ParseException{
		
		return stnLooseMtService.stnLoosePickupList(principal);
	}
	
	
	@RequestMapping(value = "/looseStkConversion/add", method = RequestMethod.POST)
	@ResponseBody
	public String looseStkConversionAdd(@Valid @ModelAttribute("looseStkConversion") LooseStkConversion looseStkConversion,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vCarat") Double vCarat,
			@RequestParam(value = "vStone") Integer vStone,
			@RequestParam(value = "sizeGroupStr") String sizeGroupStr,
			@RequestParam(value = "vDtId") Integer vDtId,
			@RequestParam(value = "vMtId") Integer vMtId,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "looseStkConversion/add";
		}
	
		retVal=looseStkConversionService.saveStnLooseConversionDt(looseStkConversion, id, vCarat, vStone, sizeGroupStr, principal, vMtId, vDtId);
		if(retVal.contains(",")){
		String xy[]=retVal.split(",");
		
		redirectAttributes.addAttribute("success",true);
		redirectAttributes.addAttribute("action",xy[1]);
		return xy[0];
		}else{
			return retVal;
		}
		
	}
	
	@RequestMapping("/looseStkConversion/listing")
	@ResponseBody
	public String looseStkConversionList(@RequestParam(value = "dtId", required = false) Integer dtId,
			Principal principal){
		
		return looseStkConversionService.stnLooseConversionDtListing(dtId, principal);
	
	}
	
	
	@ResponseBody
	@RequestMapping("/stnLooseDt/getLooseBalanceStock")
	public String getLooseBalanceStock(
			@RequestParam(value="dtId")Integer dtId,Principal principal){
		
	
		return stnLooseDtService.getStnLooseBalanceStock(dtId);
	}
	
	
	@RequestMapping("/looseStkConversion/delete/{id}")
	@ResponseBody
	public String deleteLooseStn(@PathVariable int id,Principal principal) {
		
		String retVal = looseStkConversionService.stnLooseConversionDtDelete(id, principal);
			
		return retVal;
	}
	
	@RequestMapping("/stnLooseConversionPickup/listing")
	@ResponseBody
	public String stnLooseConversionPickup(@RequestParam(value = "partyId", required = false) Integer partyId,Principal principal) throws ParseException{
		
		return stnLooseMtService.looseConversionPickupList(partyId, principal);
	}
	
	
	@RequestMapping("/looseStnConv/deleteConvDt")
	@ResponseBody
	public String looseStnConvDelete(@RequestParam(value = "convDtId", required = false) String convDtId,
			Principal principal) throws ParseException {

			String retVal = "";
			
			
					try {
				synchronized (this) {
					
					String[] looseConvDtl = convDtId.split(",");
					for (int i = 0; i < looseConvDtl.length; i++) {
						
						 retVal = looseStkConversionService.stnLooseConversionDtDelete(Integer.parseInt(looseConvDtl[i]), principal);
				
						 if(retVal.equalsIgnoreCase("-2")) {
							 break;
						 }
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	
	@RequestMapping("/stnLooseMt/partyWiseListing")
	@ResponseBody
	public String stnLoosepartyWiseListing(@RequestParam(value = "partyId", required = false) Integer partyId,Principal principal) throws ParseException{
		
		return stnLooseMtService.stnLoosePickupPartyWiseList(partyId, principal);
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}

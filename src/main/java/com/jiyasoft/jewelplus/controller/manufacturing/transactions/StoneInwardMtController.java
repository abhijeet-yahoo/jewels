package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
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
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class StoneInwardMtController {

	@Autowired
	private IStoneInwardMtService stoneInwardMtService;


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
	private ISubShapeService subShapeService;

	@Autowired
	private IQualityService qualityService;

	@Autowired
	private IStoneChartService stoneChartService;

	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IReportFilterService reportFilterService;
	
	@Value("${companyName}")
	private String companyName;

	@ModelAttribute("stoneInwardMt")
	public StoneInwardMt constructMt() {
		return new StoneInwardMt();
	}

	@ModelAttribute("stoneInwardDt")
	public StoneInwardDt constructDt() {
		return new StoneInwardDt();
	}
	
	@RequestMapping("/stnInwAgainstOrder")
	public String addScreen(){
		return "stnInwAgainstOrder";
	}
	
	@RequestMapping("/stnOutwAgainstOrder")
	public String addScreenOut(){
		return "stnOutwAgainstOrder";
	}
	
	@RequestMapping("/looseStnConversionPickup")
	public String looseStnConversionPickup(){
		return "looseStnConversionPickup";
	}
	
	
	
	

	@RequestMapping("/stoneInwardMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "stoneInwardMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "stoneInwardMt";
		}
	}

	@RequestMapping("/stoneInwardMt/listing")
	@ResponseBody
	public String stoneInwardMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<StoneInwardMt> stoneInwardMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		stoneInwardMts = stoneInwardMtService.searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(stoneInwardMts.getTotalElements()).append(",\"rows\": [");

		for (StoneInwardMt stoneInwardMt : stoneInwardMts) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(stoneInwardMt.getInvDate()));

			sb.append("{\"id\":\"").append(stoneInwardMt.getId()).append("\",\"invNo\":\"")
					.append(stoneInwardMt.getInvNo()).append("\",\"invDate\":\"").append(stoneInwardMt.getInvDateStr())
					.append("\",\"beNo\":\"").append((stoneInwardMt.getBeNo() != null ? stoneInwardMt.getBeNo() : ""))
					.append("\",\"beDate\":\"")
					.append((stoneInwardMt.getBeDateStr() != null ? stoneInwardMt.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((stoneInwardMt.getParty() != null ? stoneInwardMt.getParty().getPartyCode() : ""))
					.append("\",\"supplier\":\"")
					.append((stoneInwardMt.getSupplier() != null ? stoneInwardMt.getSupplier().getPartyCode() : ""))
					.append("\",\"inwardType\":\"")
					.append((stoneInwardMt.getInwardType() != null ? stoneInwardMt.getInwardType().getName() : ""))
					.append("\",\"remark\":\"")
					.append((stoneInwardMt.getRemark() != null ? stoneInwardMt.getRemark() : ""));
				
					sb.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/stoneInwardMt/edit/")
								.append(stoneInwardMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteStoneMt(event,");					
								sb.append(stoneInwardMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(stoneInwardMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						
		
				sb.append("\",\"action3\":\"");

				sb.append("<a href='/jewels/manufacturing/transactions/stoneInwardMt/view/")
						.append(stoneInwardMt.getId()).append(".html'");
				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")

						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

//					if (currdate.equals(invDate)) {
//						sb.append("<a href='/jewels/manufacturing/transactions/stoneInwardMt/edit/")
//								.append(stoneInwardMt.getId()).append(".html'");
//
//					} else {
//						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
//					}
					sb.append("<a href='/jewels/manufacturing/transactions/stoneInwardMt/edit/")
					.append(stoneInwardMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteStoneMt(event,")	
						.append(stoneInwardMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(stoneInwardMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");

				sb.append("\",\"action3\":\"");
				if (roleRights.getCanView()) {
					sb.append("<a href='/jewels/manufacturing/transactions/stoneInwardMt/view/")
							.append(stoneInwardMt.getId()).append(".html'");
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

	@RequestMapping("/stoneInwardMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneInwardMt");
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
			model.addAttribute("company",companyName);
			return "stoneInwardMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			model.addAttribute("company",companyName);
		}
		
		model.addAttribute("canEditTranddate", "false");
		return "stoneInwardMt/add";
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
		
		ReportFilter reportFilter = reportFilterService.findByName("stoneInwardInvoice");
		model.addAttribute("xml",reportFilter.getXml());
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);

		return model;
	}

	@RequestMapping(value = "/stoneInwardMt/add", method = RequestMethod.POST)
	public String addEditStoneInwardMt(
			@Valid @ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vTranDate", required = false) String vTranDate,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		
		synchronized (this) {
			String action = "added";
			String retVal = "redirect:/manufacturing/transactions/stoneInwardMt/add.html";
			Date aDate = null;
			
			if(stoneInwardMt.getParty().getId() == null) {
				stoneInwardMt.setParty(null);
			}
			
			if(stoneInwardMt.getSupplier().getId() == null) {
				stoneInwardMt.setSupplier(null);
			}
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date dates = originalFormat.parse(vTranDate);
			
			stoneInwardMt.setInvDate(dates);
			
			}
			if (result.hasErrors()) {
				return "stoneInwardMt/add";
			}

			if (id == null || id.equals("") || (id != null && id == 0)) {
				aDate = new java.util.Date();
				stoneInwardMt.setCreatedBy(principal.getName());
				stoneInwardMt.setCreatedDt(new java.util.Date());
				stoneInwardMt.setUniqueId(aDate.getTime());
				
				
				
				Integer maxSrNo=stoneInwardMtService.getMaxInvSrno();
				maxSrNo = (maxSrNo == null ? 1 : maxSrNo + 1);
				stoneInwardMt.setSrNo(maxSrNo);
				
				
					int si = maxSrNo.toString().length();
				
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				String bagSr = null;
				
				switch (si) {
				case 1:
					bagSr = "000"+maxSrNo;
					break;
					
				case 2:
					bagSr = "00"+maxSrNo;
					break;
					
				case 3:
					bagSr = "0"+maxSrNo;
					break;
					
				default:
					bagSr = maxSrNo.toString();
					break;
				}
				
				if(!companyName.equalsIgnoreCase("nuance")) {
					stoneInwardMt.setInvNo("IN-" + (bagSr) + "-" + vYear);	
				}
				

			} else {
				
				StoneInwardMt stoneInwardMt2= stoneInwardMtService.findOne(id);
				stoneInwardMt.setSrNo(stoneInwardMt2.getSrNo());
				stoneInwardMt.setModiBy(principal.getName());
				stoneInwardMt.setModiDt(new java.util.Date());
				if(!companyName.equalsIgnoreCase("nuance")) {
					stoneInwardMt.setInvNo(stoneInwardMt2.getInvNo());
				}


				action = "updated";
				retVal = "redirect:/manufacturing/transactions/stoneInwardMt.html";
			}
			
			stoneInwardMt.getRemark().trim();
			stoneInwardMt.setRemark(stoneInwardMt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			stoneInwardMtService.save(stoneInwardMt);
			
			if (action.equals("added")) {
				StoneInwardMt stnInward = stoneInwardMtService.findByUniqueId(stoneInwardMt.getUniqueId());
				Integer stoneId =stnInward.getId();
				 retVal = "redirect:/manufacturing/transactions/stoneInwardMt/edit/"+stoneId+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);

			return retVal;
		}
		
	}

	@RequestMapping("/stoneInwardMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		StoneInwardMt stoneInwardMt = stoneInwardMtService.findOne(id);
		model.addAttribute("stoneInwardMt", stoneInwardMt);
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
			model.addAttribute("company",companyName);
			
			return "stoneInwardMt/add";
			
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
		model.addAttribute("company",companyName);

		return "stoneInwardMt/add";
		}
	}
	
	@RequestMapping("/stoneInwardMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		StoneInwardMt stoneInwardMt = stoneInwardMtService.findOne(id);
		model.addAttribute("stoneInwardMt", stoneInwardMt);
		model = populateModel(model, principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			return "stoneInwardMt/add";
			
		}else{

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canView", roleRights.getCanDelete());
		}
		
		model.addAttribute("canEditTranddate", "false");
		return "stoneInwardMt/add";
		}
	}

	@RequestMapping("/stoneInwardMt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		String retVal = "";
		
		retVal= stoneInwardMtService.deleteMt(id,principal);
		
		if(retVal.equalsIgnoreCase("-1")){
			return retVal;
		}
		
		return "redirect:/manufacturing/transactions/stoneInwardMt.html";						 
	//	return retVal;
	}

	@RequestMapping("/sInward/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {
			invoiceAvailable = (stoneInwardMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			StoneInwardMt stoneInward = stoneInwardMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(stoneInward.getInvNo()))) {
				invoiceAvailable = (stoneInwardMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}

	@RequestMapping("/subShape/list")
	@ResponseBody
	public String productSubShapeList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> subShapeMap = subShapeService
				.getSubShapeList(shapeId);

		sb.append("<select id=\"subShape.id\" name=\"subShape.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select SubShape -</option>");
		for (Object key : subShapeMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(subShapeMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@RequestMapping("/quality/list")
	@ResponseBody
	public String productQualityList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> qualityMap = qualityService
				.getQualityList(shapeId);

		sb.append("<select id=\"quality.id\" name=\"quality.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Quality -</option>");
		for (Object key : qualityMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(qualityMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@RequestMapping("/stoneChart/list")
	@ResponseBody
	public String productStoneChartList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {

		StringBuilder sb = new StringBuilder();
		Map<String, String> stoneChartMap = stoneChartService
				.getStoneChartList(shapeId);

		// sb.append("<select id=\"size.id\" name=\"size.id\" class=\"form-control\">");

		sb.append("<select id=\"size\" name=\"size\" class=\"form-control\" onChange=\"javascript:getSizeMMDetails();\">");

		sb.append("<option value=\"\">- Select StoneChart -</option>");
		for (Object key : stoneChartMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(stoneChartMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@RequestMapping("/sizeMM/details")
	@ResponseBody
	public String sizeMMDetails(
			@RequestParam(value = "shapeId") Integer shapeId,
			@RequestParam(value = "size") String size,
			@ModelAttribute("stoneInwardDt") StoneInwardDt stoneInwardDt) {

		StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(shapeService.findOne(shapeId), size,false);

		String str = stoneChart.getSieve() + "_"+ stoneChart.getSizeGroup().getName();

		return str;
	}

	@RequestMapping("/sizeGroup/list")
	@ResponseBody
	public String productSizeGroupList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> sizeGroupMap = sizeGroupService
				.getSizeGroupList(shapeId);

		sb.append("<select id=\"sizeGroup.id\" name=\"sizeGroup.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select SizeGroup -</option>");
		for (Object key : sizeGroupMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(sizeGroupMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	/*Stone Invoice Report Listing */

	
	
	
	@RequestMapping("/stoneInwardMt/report/listing")
	@ResponseBody
	public String stoneInvoiceListing(Model model,
			@RequestParam(value="inwardTypeId",required=false)String inwardTypeIds){
		
		
		StringBuilder sb = new StringBuilder();
		
		List<StoneInwardMt>stoneInwardMts = stoneInwardMtService.getInvoiceList(inwardTypeIds);
		
		sb.append("{\"total\":").append(stoneInwardMts.size()).append(",\"rows\": [");

		for (StoneInwardMt stoneInwardObj : stoneInwardMts) {
			sb.append("{\"id\":\"")
				.append(stoneInwardObj.getId())
				.append("\",\"invNo\":\"")
				.append(stoneInwardObj.getInvNo())
				.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		
		return str;
	}
	
	
	@RequestMapping("/looseStnConv/transfer")
	@ResponseBody
	public String transferAdjustment(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "convDtId", required = false) String convDtId,
			Principal principal) throws ParseException {

			String retVal = "";
			
			
					try {
				synchronized (this) {
					
					retVal = stoneInwardMtService.stoneInwLooseConvTransfer(mtId, convDtId, principal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	
}

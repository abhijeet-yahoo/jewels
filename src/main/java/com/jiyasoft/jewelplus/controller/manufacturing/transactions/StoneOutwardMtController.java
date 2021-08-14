package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class StoneOutwardMtController {

	@Autowired
	private IStoneOutwardMtService stoneOutwardMtService;

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
	private IReportFilterService reportFilterService;

	@ModelAttribute("stoneOutwardMt")
	public StoneOutwardMt constructMt() {
		return new StoneOutwardMt();
	}

	@ModelAttribute("stoneOutwardDt")
	public StoneOutwardDt constructDt() {
		return new StoneOutwardDt();
	}


	@RequestMapping("/stoneOutwardMt")
	public String users(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "stoneOutwardMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
			}

			return "stoneOutwardMt";
		}
	}

	@RequestMapping("/stoneOutwardMt/listing")
	@ResponseBody
	public String stoneOutwardMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<StoneOutwardMt> stoneOutwardMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		stoneOutwardMts = stoneOutwardMtService.searchAll(limit, offset, sort, order, search, true);
		sb.append("{\"total\":").append(stoneOutwardMts.getTotalElements()).append(",\"rows\": [");

		for (StoneOutwardMt stoneOutwardMt : stoneOutwardMts) {

			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(stoneOutwardMt.getInvDate()));

			sb.append("{\"id\":\"").append(stoneOutwardMt.getId()).append("\",\"invNo\":\"")
					.append(stoneOutwardMt.getInvNo()).append("\",\"invDate\":\"")
					.append(stoneOutwardMt.getInvDateStr()).append("\",\"beNo\":\"")
					.append((stoneOutwardMt.getBeNo() != null ? stoneOutwardMt.getBeNo() : ""))
					.append("\",\"beDate\":\"")
					.append((stoneOutwardMt.getBeDateStr() != null ? stoneOutwardMt.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((stoneOutwardMt.getParty() != null ? stoneOutwardMt.getParty().getPartyCode() : ""))
					/*
					 * .append("\",\"supplier\":\"") .append((stoneOutwardMt.getSupplier() != null ?
					 * stoneOutwardMt.getSupplier().getName() : ""))
					 */
					.append("\",\"inwardType\":\"")
					.append((stoneOutwardMt.getInwardType() != null ? stoneOutwardMt.getInwardType().getName() : ""))
					.append("\",\"remark\":\"")
					.append((stoneOutwardMt.getRemark() != null ? stoneOutwardMt.getRemark() : ""));
			sb.append("\",\"action1\":\"");

			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

				sb.append("<a href='/jewels/manufacturing/transactions/stoneOutwardMt/edit/")
						.append(stoneOutwardMt.getId()).append(".html'");

				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

//				sb.append("\",\"action2\":\"");
//				sb.append(
//						"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/stoneOutwardMt/delete/")
//						.append(stoneOutwardMt.getId());
//
//				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(stoneOutwardMt.getId())
//						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				
				sb.append("\",\"action2\":\"");
				
				sb.append("<a href='javascript:deleteStoneMt(event,");					
						sb.append(stoneOutwardMt.getId());

				sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(stoneOutwardMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				

				sb.append("\",\"action3\":\"");

				sb.append("<a href='/jewels/manufacturing/transactions/stoneOutwardMt/view/")
						.append(stoneOutwardMt.getId()).append(".html'");

				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>");
			} else {
				if (roleRights.getCanEdit()) {
//					if (currdate.equals(invDate)) {
//						sb.append("<a href='/jewels/manufacturing/transactions/stoneOutwardMt/edit/")
//								.append(stoneOutwardMt.getId()).append(".html'");
//
//					} else {
//						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
//					}
					sb.append("<a href='/jewels/manufacturing/transactions/stoneOutwardMt/edit/")
					.append(stoneOutwardMt.getId()).append(".html'");

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}

				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

//				sb.append("\",\"action2\":\"");
//				if (roleRights.getCanDelete()) {
//
//					if (currdate.equals(invDate)) {
//						sb.append("<a href='/jewels/manufacturing/transactions/stoneOutwardMt/delete/")
//								.append(stoneOutwardMt.getId()).append(".html'");
//
//					} else {
//						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
//					}
//
//				} else {
//					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
//				}
//				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(stoneOutwardMt.getId())
//						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				
				
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteStoneMt(event,")	
						.append(stoneOutwardMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(stoneOutwardMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");

				sb.append("\",\"action3\":\"");
				if (roleRights.getCanView()) {
					sb.append("<a href='/jewels/manufacturing/transactions/stoneOutwardMt/view/")
							.append(stoneOutwardMt.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>");

			}

			sb.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;

	}

	@RequestMapping("/stoneOutwardMt/add")
	public String add(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			model = populateModel(model);
			return "stoneOutwardMt/add";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
			}

			model.addAttribute("canEditTranddate", "false");
			model = populateModel(model);

			return "stoneOutwardMt/add";
		}
	}

	private Model populateModel(Model model) {
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("inwardTypeMap",inwardTypeService.getInwardTypeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		
		ReportFilter reportFilter = reportFilterService.findByName("stoneOutwardInvoice");
		model.addAttribute("xml",reportFilter.getXml());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);

		return model;
	}

	@RequestMapping(value = "/stoneOutwardMt/add", method = RequestMethod.POST)
	public String addEditStoneOutwardMt(
			@Valid @ModelAttribute("stoneOutwardMt") StoneOutwardMt stoneOutwardMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/stoneOutwardMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			return "stoneOutwardMt/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			Integer maxSrno = null;
			maxSrno = stoneOutwardMtService.getMaxStoneOutInvSrno();
			maxSrno = (maxSrno == null ? 0 : maxSrno);
			
			stoneOutwardMt.setSrNo(++maxSrno);
			int si = maxSrno.toString().length();
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			Integer presentYear = Integer.parseInt(vYear);
			Integer nextYear = presentYear + 1;
			
			String bagSr = null;
			
			switch (si) {
			case 1:
				bagSr = "000"+maxSrno;
				break;
				
			case 2:
				bagSr = "00"+maxSrno;
				break;
				
			case 3:
				bagSr = "0"+maxSrno;
				break;
				
			default:
				bagSr = maxSrno.toString();
				break;
			}
			
			
			stoneOutwardMt.setInvNo("STNOUT/" + (bagSr) + "/" + presentYear+"-"+nextYear);
			
			
			aDate = new java.util.Date();
			stoneOutwardMt.setCreatedBy(principal.getName());
			stoneOutwardMt.setCreatedDt(new java.util.Date());
			stoneOutwardMt.setUniqueId(aDate.getTime());

		} else {
			stoneOutwardMt.setModiBy(principal.getName());
			stoneOutwardMt.setModiDt(new java.util.Date());

			if (stoneOutwardMt.getDeactive() != stoneOutwardMtService.findOne(id)
					.getDeactive()) {
				stoneOutwardMt.setDeactiveDt(new java.util.Date());
			} else {
				stoneOutwardMt.setDeactiveDt(stoneOutwardMtService.findOne(id)
						.getDeactiveDt());
			}

			if (stoneOutwardMtService.findOne(id).getDeactive() == false) {
				stoneOutwardMt.setDeactive(false);

			} else {
				stoneOutwardMt.setDeactive(true);
			}

			stoneOutwardMt.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/stoneOutwardMt.html";
		}
		
		if(stoneOutwardMt.getInvDate() ==  null){
			stoneOutwardMt.setInvDate(new Date());
		}
		
		stoneOutwardMt.getRemark().trim();
		stoneOutwardMt.setRemark(stoneOutwardMt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());

		stoneOutwardMtService.save(stoneOutwardMt);
		
		if (action.equals("added")) {
			/*StoneOutwardMt stnInward = stoneOutwardMtService.findByUniqueId(stoneOutwardMt.getUniqueId());
			Integer stoneId =stnInward.getId();*/
			 retVal = "redirect:/manufacturing/transactions/stoneOutwardMt/edit/"+stoneOutwardMt.getId()+".html";
		}
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

		@RequestMapping("/stoneOutwardMt/edit/{id}")
		public String edit(@PathVariable int id, Model model, Principal principal) {

			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("stoneOutwardMt");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

			StoneOutwardMt stoneOutwardMt = stoneOutwardMtService.findOne(id);
			model.addAttribute("stoneOutwardMt", stoneOutwardMt);
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

				model.addAttribute("canAdd", true);
				model.addAttribute("canEdit", true);
				model.addAttribute("canDelete", true);
				model.addAttribute("canView", true);
				model.addAttribute("canEditTranddate", "false");
				model.addAttribute("mtid", id);
				model = populateModel(model);
				return "stoneOutwardMt/add";
			} else {
				if (roleRights == null) {
					return "accesDenied";
				} else {
					model.addAttribute("canAdd", roleRights.getCanAdd());
					model.addAttribute("canEdit", roleRights.getCanEdit());
					model.addAttribute("canDelete", roleRights.getCanDelete());
					model.addAttribute("canView", roleRights.getCanView());
				}
				model.addAttribute("canEditTranddate", "false");
				model = populateModel(model);
				model.addAttribute("mtid", id);
				return "stoneOutwardMt/add";
			}
		}
	

		@RequestMapping("/stoneOutwardMt/view/{id}")
		public String view(@PathVariable int id, Model model, Principal principal) {

			StoneOutwardMt stoneOutwardMt = stoneOutwardMtService.findOne(id);
			model.addAttribute("stoneOutwardMt", stoneOutwardMt);
			model = populateModel(model);

			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("stoneOutwardMt");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

				model.addAttribute("canAdd", true);
				model.addAttribute("canEdit", true);
				model.addAttribute("canDelete", true);
				model.addAttribute("canView", true);
				model.addAttribute("canEditTranddate", "false");
				return "stoneOutwardMt/add";
			} else {
				if (roleRights == null) {
					return "accesDenied";
				} else {
					model.addAttribute("canAdd", roleRights.getCanAdd());
					model.addAttribute("canEdit", roleRights.getCanEdit());
					model.addAttribute("canDelete", roleRights.getCanDelete());
					model.addAttribute("canView", roleRights.getCanView());
				}
				model.addAttribute("canEditTranddate", "false");
				return "stoneOutwardMt/add";
			}
		}

	@RequestMapping("/stoneOutwardMt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		String retVal = "";
		
		retVal= stoneOutwardMtService.deleteMt(id,principal);
	
		if(retVal.equalsIgnoreCase("-1")){
			return retVal;
		}
				
		return "redirect:/manufacturing/transactions/stoneOutwardMt.html";	
	}

	@RequestMapping("/sOutward/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {
			invoiceAvailable = (stoneOutwardMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			StoneOutwardMt stoneOutward = stoneOutwardMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(stoneOutward.getInvNo()))) {
				invoiceAvailable = (stoneOutwardMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}

	

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	/*Stone Invoice Report Listing */

	
	
	
	@RequestMapping("/stoneOutwardMt/report/listing")
	@ResponseBody
	public String stoneInvoiceListing(Model model,
			@RequestParam(value="inwardTypeId",required=false)String inwardTypeIds){
		
		
		StringBuilder sb = new StringBuilder();
		
		List<StoneOutwardMt>stoneOutwardMts = stoneOutwardMtService.getInvoiceList(inwardTypeIds);
		
		Integer rowCount=1000 ;
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (StoneOutwardMt stoneOutwardObj : stoneOutwardMts) {
			sb.append("{\"id\":\"")
				.append(stoneOutwardObj.getId())
				.append("\",\"invNo\":\"")
				.append(stoneOutwardObj.getInvNo())
				.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		
		return str;
	}
	
	
}

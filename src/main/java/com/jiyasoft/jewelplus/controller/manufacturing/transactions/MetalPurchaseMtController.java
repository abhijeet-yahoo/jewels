package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalPurchaseMtController {
	
	final private static Logger logger = LoggerFactory.getLogger(MetalPurchaseMtController.class);
	
	@Autowired
	private IMetalPurchaseMtService metalPurchaseMtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IPartyService partyService;

	@Autowired
	private IInwardTypeService inwardTypeService;

	@Autowired
	private IMetalService metalService;

	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMetalPurchaseDtService metalPurchaseDtService;
	
	@Autowired
	private IPurityService purityService;
	
	

	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	
	
	
	@ModelAttribute("metalPurchaseMt")
	public MetalPurchaseMt constructMt() {
		return new MetalPurchaseMt();
	}
	
	@ModelAttribute("metalPurchaseDt")
	public MetalPurchaseDt constructDt(){
		return new MetalPurchaseDt();
	}
	
	
	@RequestMapping("/metalPurchaseMt")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		return "metalPurchaseMt";
	}
	
	
	@RequestMapping("/metalPurchaseDt")
	public String users(Model model) {
			return "metalPurchaseDt";
	}

	
	@RequestMapping("/metalPurchaseMt/listing")
	@ResponseBody
	public String metalPurchaseMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<MetalPurchaseMt> metalPurchaseMts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		metalPurchaseMts = metalPurchaseMtService.searchAll(limit, offset, sort, order, search, true);


		sb.append("{\"total\":").append(metalPurchaseMts.getTotalElements()).append(",\"rows\": [");

		for (MetalPurchaseMt metalPurchaseMt : metalPurchaseMts) {
		
					sb.append("{\"id\":\"")
					.append(metalPurchaseMt.getId())
					.append("\",\"invNo\":\"")
					.append(metalPurchaseMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(metalPurchaseMt.getInvDateStr())
					.append("\",\"dueDate\":\"")
					.append(metalPurchaseMt.getDueDateStr())
					.append("\",\"beNo\":\"")
					.append((metalPurchaseMt.getBeNo() != null ? metalPurchaseMt.getBeNo() : ""))
					.append("\",\"beDate\":\"")
					.append((metalPurchaseMt.getBeDateStr() != null ? metalPurchaseMt.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((metalPurchaseMt.getParty() != null ? metalPurchaseMt.getParty().getName() : ""))
					.append("\",\"inwardType\":\"")
					.append((metalPurchaseMt.getInwardType() != null ? metalPurchaseMt.getInwardType().getName() : ""))
					.append("\",\"remark\":\"")
					.append((metalPurchaseMt.getRemark() != null ? metalPurchaseMt.getRemark() : ""));

					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/transactions/metalPurchaseMt/edit/")
							.append(metalPurchaseMt.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/metalPurchaseMt/delete/")
								.append(metalPurchaseMt.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(metalPurchaseMt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
							
					
					sb.append("\",\"action3\":\"");
					if (roleRights.getCanView()) {
						sb.append("<a href='/jewels/manufacturing/transactions/metalPurchaseMt/view/")
								.append(metalPurchaseMt.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(
							" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
							.append("\"},");
			
		}		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		
		return str;

	}
	
	
	@RequestMapping("/metalPurchaseMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = populateModel(model,principal);
		
	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}

		

		return "metalPurchaseMt/add";
	
		
	}
	
	
	private Model populateModel(Model model, Principal principal) {
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("inwardTypeMap",inwardTypeService.getInwardTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		
		
			return model;
	}
	
	
	@RequestMapping(value = "/metalPurchaseMt/add", method = RequestMethod.POST)
	public String addEditMetalPurchaseMt(
			@Valid @ModelAttribute("metalPurchaseMt") MetalPurchaseMt metalPurchaseMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/metalPurchaseMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			return "metalPurchaseMt/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			aDate = new java.util.Date();
			metalPurchaseMt.setCreatedBy(principal.getName());
			metalPurchaseMt.setCreatedDt(new java.util.Date());
			metalPurchaseMt.setUniqueId(aDate.getTime());
			
		  
		} else {
			metalPurchaseMt.setModiBy(principal.getName());
			metalPurchaseMt.setModiDt(new java.util.Date());
		metalPurchaseMt.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/metalPurchaseMt.html";
		}
		

		metalPurchaseMtService.save(metalPurchaseMt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		if (action.equals("added")) {
			MetalPurchaseMt metalPurchaseMt2 = metalPurchaseMtService.findByUniqueId(metalPurchaseMt.getUniqueId());
		
			retVal  = "redirect:/manufacturing/transactions/metalPurchaseMt/edit/"+metalPurchaseMt2.getId()+".html";
		}

		return retVal;
	}
	
	
	@RequestMapping("/metalPurchaseMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		MetalPurchaseMt metalPurchaseMt = metalPurchaseMtService.findOne(id);
		model.addAttribute("metalPurchaseMt", metalPurchaseMt);
		
		model = populateModel(model,principal);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}

		return "metalPurchaseMt/add";
		
	}
	
	
	@RequestMapping("/metalPurchaseMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		MetalPurchaseMt metalPurchaseMt = metalPurchaseMtService.findOne(id);
		model.addAttribute("metalPurchaseMt", metalPurchaseMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}

		return "metalPurchaseMt/add";
		
	}
	
	
	@RequestMapping("/metalPurchaseMt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		metalPurchaseMtService.metalPurcDelete(id);

		return	 "redirect:/manufacturing/transactions/metalPurchaseMt.html";
		 
	}
	
	
	@RequestMapping("/metalPurchaseDt/listing")
	@ResponseBody
	public String metalPurchaseDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,
			Principal principal) {

		logger.info("MetalPurchaseDt Listing");

		StringBuilder sb = new StringBuilder();
	
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	

		MetalPurchaseMt metalPurchaseMt = metalPurchaseMtService.findByInvNoAndDeactive(pInvNo, false);
		
		List<MetalPurchaseDt> metalPurchaseDts = metalPurchaseDtService.findByMetalPurchaseMtAndDeactive(metalPurchaseMt, false);
		
		sb.append("{\"total\":").append(metalPurchaseDts.size()).append(",\"rows\": [");
		
		if(metalPurchaseDts.size() > 0){
			
			  for (MetalPurchaseDt metalPurchaseDt : metalPurchaseDts) {
			  sb.append("{\"id\":\"") 
			  .append(metalPurchaseDt.getId())
			  .append("\",\"metal\":\"") 
			  .append((metalPurchaseDt.getMetal() != null ?  metalPurchaseDt.getMetal().getName() : "")) 
			  .append("\",\"purity\":\"")
			  .append((metalPurchaseDt.getPurity() != null ?  metalPurchaseDt.getPurity().getName() : "")) 
			  .append("\",\"color\":\"")
			  .append((metalPurchaseDt.getColor() != null ?  metalPurchaseDt.getColor().getName() : "")) 
			  .append("\",\"invWt\":\"")
			  .append(metalPurchaseDt.getInvWt()) 
			  .append("\",\"rate\":\"")
			  .append(metalPurchaseDt.getRate()) 
			  .append("\",\"amount\":\"")
			  .append(metalPurchaseDt.getAmount()) 
			  .append("\",\"in999\":\"")
			  .append(metalPurchaseDt.getIn999()) 
			  .append("\",\"remark\":\"")
			  .append((metalPurchaseDt.getRemark() != null ? metalPurchaseDt.getRemark() : ""));
			  
			  if(!canViewFlag){
			  sb.append("\",\"action1\":\""); 
			  if (roleRights.getCanEdit()) {
			  sb.append("<a href='javascript:editMetalPurcDt(").append(metalPurchaseDt.getId()); } 
			  else { 
				  sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'"  ); 
				  } 
			  sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>" );
			  
			  sb.append("\",\"action2\":\""); 
			  if (roleRights.getCanDelete()) {
			  sb.append("<a  href='javascript:deleteMetalPurcDt(event,")
			  .append(metalPurchaseDt.getId()).append(", 0);' href='javascript:void(0);'"); }
			  else { 
				  sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'"
			  ); } sb.append(" class='btn btn-xs btn-danger triggerRemove")
			  .append(metalPurchaseDt.getId())
			  .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			 			  
			  }else {
				  
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("");
			  }
			  
			  sb.append("\"},");
			  
			  }
			 
		}else{
			System.out.println("No record found");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	@RequestMapping("/metalPurchaseDt/add")
	public String add(Model model) {
		return "metalPurchaseDt/add";
	}
	
	@RequestMapping(value = "/metalPurchaseDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("metalPurchaseDt") MetalPurchaseDt metalPurchaseDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			RedirectAttributes redirectAttributes, Principal principal) {

	
		String retVal = "1";
		

		if (result.hasErrors()) {
			return "metalPurchaseDt/add";
		}
		
		retVal=metalPurchaseDtService.metalPurchaseSave(metalPurchaseDt, id, pInvNo,principal);


		
		return retVal;
			
		}
	
	@ResponseBody
	@RequestMapping(value = "/metalPurchaseDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		MetalPurchaseDt metalPurchaseDt =metalPurchaseDtService.findOne(id);
		
		  Double prevBalance = Math.round((metalPurchaseDt.getInvWt()*metalPurchaseDt.getPurity().getPurityConv())*1000.0)/1000.0;
		  
		  if(metalPurchaseDt.getBalance()<prevBalance){
			  return "-1"; 
		  
		  }
		  
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("purity", metalPurchaseDt.getPurity().getId());
		jsonObject.put("metal\\.id", metalPurchaseDt.getMetal().getId());
		jsonObject.put("color\\.id", metalPurchaseDt.getColor().getId());
		jsonObject.put("invWt", metalPurchaseDt.getInvWt());
		jsonObject.put("rate", metalPurchaseDt.getRate());
		jsonObject.put("amount", metalPurchaseDt.getAmount());
		jsonObject.put("in999", metalPurchaseDt.getIn999());
		jsonObject.put("remark", metalPurchaseDt.getRemark() !=null ? metalPurchaseDt.getRemark():"");
		jsonObject.put("balance", metalPurchaseDt.getBalance());
		jsonObject.put("metalPurchaseDtEntryId", metalPurchaseDt.getId());

		return jsonObject.toString();
	}

	
	@RequestMapping("/metalPurchaseDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
	
		String retVal="1";
		MetalPurchaseDt metalPurchaseDt =metalPurchaseDtService.findOne(id);
		Double prevBalance = Math.round((metalPurchaseDt.getInvWt()*metalPurchaseDt.getPurity().getPurityConv())*1000.0)/1000.0;
		  
		  if(metalPurchaseDt.getBalance()<prevBalance){
			  return "-1"; 
		  
		  }
		
		metalPurchaseDtService.delete(id);
	
		 return retVal;	
	
		
	}

	@RequestMapping("/metalPurchaseMt/purity/list")
	@ResponseBody
	public String productSizeList(
			@RequestParam(value = "metalId") Integer metalId,
			 @ModelAttribute("metalPurchaseMt") MetalPurchaseMt metalPurchaseMt) {

		return purityService.getPurityListDropDown(metalId);
	}
	
	@RequestMapping("/purcInvoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {
			invoiceAvailable = (metalPurchaseMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			MetalPurchaseMt metalPurchaseMt = metalPurchaseMtService.findOne(id);
			if (! (invNo.equalsIgnoreCase(metalPurchaseMt.getInvNo())) ) {
				invoiceAvailable = (metalPurchaseMtService.findByInvNoAndDeactive(invNo, false) == null);
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
}

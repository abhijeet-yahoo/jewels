package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalOutwardDtController {

	final private static Logger logger = LoggerFactory
			.getLogger(MetalOutwardDtController.class);

	@Autowired
	private IMetalOutwardMtService metalOutwardMtService;;

	@Autowired
	private IMetalOutwardDtService metalOutwardDtService;;

	@Autowired
	private IMetalService metalService;

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IMetalTranService metalTranService;

	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;

	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@RequestMapping("/metalOutwardDt")
	public String users(Model model) {
		//model.addAttribute("metalOutwardDt", metalOutwardDtService.findAll());

		return "metalOutwardDt";
	}

	@RequestMapping("/metalOutwardDt/listing")
	@ResponseBody
	public String metalOutwardDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,Principal principal) {

		logger.info("MetalOutwardDt Listing");

		StringBuilder sb = new StringBuilder();
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		/*sb.append("{\"total\":").append(metalOutwardDtService.count()).append(",\"rows\": [");*/
		
		String disable = "";
		if(canViewFlag){
			disable = "disabled = 'disabled'";
		}else{
			System.err.println("in else view");
		}
		
		//Page<MetalOutwardDt> metalOutwardDts = metalOutwardDtService.findByMetalOutwardMt(metalOutwardMtService.findByInvNo(pInvNo), limit,	offset, sort, order, search);

		List<MetalOutwardDt> metalOutwardDts=metalOutwardDtService.findByMetalOutwardMtAndDeactive(metalOutwardMtService.findByInvNo(pInvNo), false);
		sb.append("{\"total\":").append(metalOutwardDts.size()).append(",\"rows\": [");
		
		for (MetalOutwardDt metalOutwardDt : metalOutwardDts) {
			sb.append("{\"id\":\"")
					.append(metalOutwardDt.getId())
					.append("\",\"department\":\"")
					.append((metalOutwardDt.getDepartment() != null ? metalOutwardDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((metalOutwardDt.getMetal() != null ? metalOutwardDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((metalOutwardDt.getPurity() != null ? metalOutwardDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((metalOutwardDt.getColor() != null ? metalOutwardDt.getColor().getName() : ""))
					.append("\",\"invWt\":\"")
					.append(metalOutwardDt.getInvWt())
					.append("\",\"metalWt\":\"")
					.append(metalOutwardDt.getMetalWt())
					.append("\",\"rate1\":\"")
					.append(metalOutwardDt.getRate1())
					.append("\",\"amount\":\"")
					.append(metalOutwardDt.getAmount())
					.append("\",\"deactive\":\"")
					.append(metalOutwardDt.getDeactive() ? "Yes" : "No");
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				
			if(!canViewFlag){
					sb.append("\",\"action1\":\"");
					
						sb.append("<a href='javascript:editMetalOutwardDt(").append(metalOutwardDt.getId());
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");

						sb.append("<a  href='javascript:deleteMetalOutwardDt(event,")
							.append(metalOutwardDt.getId()).append(", 0);' href='javascript:void(0);'");
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(metalOutwardDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			}else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("");
			}
			}else
			{
				if(!canViewFlag){
					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='javascript:editMetalOutwardDt(").append(metalOutwardDt.getId());
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append("<a  href='javascript:deleteMetalOutwardDt(event,")
							.append(metalOutwardDt.getId()).append(", 0);' href='javascript:void(0);'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(metalOutwardDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			}else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("");
			}
			}	
					
					sb.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/metalOutwardDt/add")
	public String add(Model model) {
		return "metalOutwardDt/add";
	}

	@RequestMapping(value = "/metalOutwardDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("metalOutwardDt") MetalOutwardDt metalOutwardDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "metalWt") Double metalWt,
			@RequestParam(value = "prevMetalWt") Double prevMetalWt,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";
		
		if (result.hasErrors()) {
			return "metalOutwardDt/add";
		}

		retVal=metalOutwardDtService.metalOutwardSave(metalOutwardDt, id, pInvNo, metalWt, prevMetalWt, principal);
	
		if(retVal.contains(",")){
			String xy[]=retVal.split(",");
			
			redirectAttributes.addAttribute("success",true);
			redirectAttributes.addAttribute("action",xy[1]);
			return xy[0];
			}else{
				return retVal;
			}
			
		}
	
	@RequestMapping("/metalOutwardDt/editRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal) {
	
		String retVal = "-1";
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			MetalOutwardMt metalOutwardMt = metalOutwardMtService.findOne(dtId);
			Date cDate = metalOutwardMt.getInvDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);
			if(dbDate.equals(curDate)){
				retVal = "1";
			}else{
				retVal = "-1";
				
			}
			
			
		}

		return retVal;
	}

	@RequestMapping(value = "/metalOutwardDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		MetalOutwardDt metalOutwardDt = metalOutwardDtService.findOne(id);
		model.addAttribute("metalOutwardDt", metalOutwardDt);
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("departmentMap",departmentService.getDepartmentListForMetal());

		if (metalOutwardDt != null) {
			Metal metal = metalOutwardDt.getMetal();
			model.addAttribute(
					"purityMap",
					(metalOutwardDt.getPurity() == null ? null : purityService
							.getPurityList(metal.getId())));
		}

		return "metalOutwardDt/add";
	}

	@RequestMapping("/metalOutwardDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = metalOutwardDtService.metalOutDelete(id, principal);
	
			MetalOutwardDt metalOutwardDt = metalOutwardDtService.findOne(id);
			
		if(retVal != "-1"){
			return "/jewels/manufacturing/transactions/metalOutwardMt/edit/"+metalOutwardDt.getMetalOutwardMt().getId()+ ".html";
		}else{
			retVal ="-1";
		}
		
		return retVal;
	}

	@RequestMapping("/metalOutwardDt/stock")
	@ResponseBody
	public String stockCheck(
			@RequestParam(value = "metalWt", required = false) Double metalWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("castingMt") CastingMt castingMt) {

		StringBuilder sb = new StringBuilder();

		System.out.println("metalWt=" + metalWt);
		System.out.println("purityId=" + purityId);
		System.out.println("colorId=" + colorId);

		Integer locationId = departmentService.findByName("Central").getId();
		Double balance = metalTranService.getStockBalance(purityId, colorId,
				locationId);
		System.out.println("balance =" + balance);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		// sb.append(balance);
		
		System.out.println("in controller");

		return sb.toString();
	}
	
	
	@RequestMapping("/metalOutwardDt/addRights")
	@ResponseBody
	public String addRights(
			@RequestParam(value = "pInvNo", required = false) String pInvNo,Principal principal) {
	
		String retVal = "-1";
		
	if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
			
	}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			MetalOutwardMt metalOutwardMt = metalOutwardMtService.findByInvNoAndDeactive(pInvNo, false);
			Date cDate = metalOutwardMt.getInvDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);

			if(dbDate.equals(curDate)){
				
					retVal = "1";
				
							
			}
			
			
		}
	
		
		
		return retVal;
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}

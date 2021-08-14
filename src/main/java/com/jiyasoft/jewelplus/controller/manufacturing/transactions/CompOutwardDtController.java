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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CompOutwardDtController {

	final private static Logger logger = LoggerFactory
			.getLogger(CompOutwardDtController.class);

	@Autowired
	private ICompOutwardMtService compOutwardMtService;

	@Autowired
	private ICompOutwardDtService compOutwardDtService;

	@Autowired
	private IMetalService metalService;

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IComponentService componentService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private ICompTranService compTranService;
	
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

	@RequestMapping("/compOutwardDt")
	public String users(Model model) {
		return "compOutwardDt";
	}

	@RequestMapping("/compOutwardDt/listing")
	@ResponseBody
	public String compOutwardDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,Principal principal) {

		logger.info("CompOutwardDt Listing");

		StringBuilder sb = new StringBuilder();
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		//sb.append("{\"total\":").append(compOutwardDtService.count()).append(",\"rows\": [");

		String disable = "";
		if(canViewFlag){
			disable = "disabled = 'disabled'";
		}else{
			System.err.println("in else view");
		}
		
		//Page<CompOutwardDt> compOutwardDts = compOutwardDtService.findByCompOutwardMt(compOutwardMtService.findByInvNo(pInvNo),limit, offset, sort, order, search);

		List<CompOutwardDt> compOutwardDts=compOutwardDtService.findByCompOutwardMtAndDeactive(compOutwardMtService.findByInvNo(pInvNo), false);
		
		sb.append("{\"total\":").append(compOutwardDts.size()).append(",\"rows\": [");
		
		for (CompOutwardDt compOutwardDt : compOutwardDts) {
			sb.append("{\"id\":\"")
					.append(compOutwardDt.getId())
					.append("\",\"metal\":\"")
					.append((compOutwardDt.getMetal() != null ? compOutwardDt.getMetal().getName() : ""))
					.append("\",\"component\":\"")
					.append((compOutwardDt.getComponent() != null ? compOutwardDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((compOutwardDt.getPurity() != null ? compOutwardDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((compOutwardDt.getColor() != null ? compOutwardDt.getColor().getName() : ""))
					.append("\",\"department\":\"")
					.append((compOutwardDt.getDepartment() != null ? compOutwardDt.getDepartment().getName() : ""))
					.append("\",\"invWt\":\"")
					.append(compOutwardDt.getInvWt())
					.append("\",\"metalWt\":\"")
					.append(compOutwardDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(compOutwardDt.getRate())
					.append("\",\"amount\":\"")
					.append(compOutwardDt.getAmount())
					.append("\",\"qty\":\"")
					.append(compOutwardDt.getQty())
					.append("\",\"deactive\":\"")
					.append(compOutwardDt.getDeactive() ? "Yes" : "No");
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
				if(!canViewFlag){
					sb.append("\",\"action1\":\"");
			
						sb.append("<a href='javascript:editCompODt(").append(compOutwardDt.getId());
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
			
						sb.append("<a  href='javascript:deleteCompOutwardDt(event,")
						.append(compOutwardDt.getId()).append(", 0);' href='javascript:void(0);'");
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(compOutwardDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("");
				}
			}
				else
				{
					if(!canViewFlag){
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='javascript:editCompODt(").append(compOutwardDt.getId());
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append("<a  href='javascript:deleteCompOutwardDt(event,")
							.append(compOutwardDt.getId()).append(", 0);' href='javascript:void(0);'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
						 .append(compOutwardDt.getId())
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

	@RequestMapping("/compOutwardDt/add")
	public String add(Model model) {
		
		
		return "compOutwardDt/add";
	}

	@RequestMapping(value = "/compOutwardDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("compOutwardDt") CompOutwardDt compOutwardDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "metalWt") Double metalWt,
			@RequestParam(value = "prevMetalWt") Double prevMetalWt,
			RedirectAttributes redirectAttributes, Principal principal) {

		String retVal = "-1";
		
		if (result.hasErrors()) {
			return "compOutwardDt/add";
		}
		
		retVal=compOutwardDtService.compOutwardDtSave(compOutwardDt, id, pInvNo, metalWt, prevMetalWt, principal);
	
		if(retVal.contains(",")){
		String xy[]=retVal.split(",");
		
		redirectAttributes.addAttribute("success",true);
		redirectAttributes.addAttribute("action",xy[1]);
		return xy[0];
		}else{
			return retVal;
		}
		
	}

	@RequestMapping(value = "/compOutwardDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		CompOutwardDt compOutwardDt =new CompOutwardDt();
		if(id !=0) {
			compOutwardDt = compOutwardDtService.findOne(id);
			Metal metal = compOutwardDt.getMetal();
			model.addAttribute("purityMap",(compOutwardDt.getPurity() == null ? null : purityService.getPurityList(metal.getId())));
	
		}
	
		
		model.addAttribute("compOutwardDt", compOutwardDt);
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("departmentMap",departmentService.getDepartmentListForCompNotCentral());
		


		return "compOutwardDt/add";
	}
	
	@RequestMapping("/compOutwardDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		
		
		String retVal = "-1";
		retVal =compOutwardDtService.compOutwardDtDelete(id, principal);
		
		CompOutwardDt compOutwardDt =  compOutwardDtService.findOne(id);
		if(retVal != "-1"){
			return "/jewels/manufacturing/transactions/compOutwardMt/edit/"+compOutwardDt.getCompOutwardMt().getId()+ ".html";	
		}
		
	
		return retVal;
	}
	

	/*@RequestMapping("/compOutwardDt/delete/{id}")
	public String delete(@PathVariable int id) {
		

		Integer compTranId = null;
		List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(
				id, "Outward");
		for (CompTran comTran : compTran) {
			compTranId = comTran.getId();
			compTranService.delete(compTranId);
		}

		CompOutwardDt compOutwardDt = compOutwardDtService.findOne(id);
		Integer compOutwardMtId = compOutwardDt.getCompOutwardMt().getId();

		return "redirect:/manufacturing/transactions/compOutwardMt/edit/"
				+ compOutwardMtId + ".html";
	}*/
	
	@RequestMapping("/compOutwardDt/editRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal) {
	
		String retVal = "-1";
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			CompOutwardMt compOutwardMt = compOutwardMtService.findOne(dtId);
			Date cDate = compOutwardMt.getInvDate();
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

	@RequestMapping("/compOutwardDt/stock")
	@ResponseBody
	public String stockCheck(
			@RequestParam(value = "metalWt", required = false) Double metalWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@RequestParam(value = "componentId", required = false) Integer componentId,
			@ModelAttribute("castingMt") CastingMt castingMt) {

		StringBuilder sb = new StringBuilder();

		Integer locationId = departmentService.findByName("Central").getId();
		Double balance = compTranService.getStockBalance(purityId, colorId,
				locationId, componentId);

		System.out.println("balance =" + balance);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		// sb.append(balance);

		return sb.toString();
	}
	
	
	@RequestMapping("/compOutwardDt/addRights")
	@ResponseBody
	public String addRights(
			@RequestParam(value = "pInvNo", required = false) String pInvNo,Principal principal) {
	
		String retVal = "-1";
		
	if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
			
	}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			CompOutwardMt compOutwardMt = compOutwardMtService.findByInvNoAndDeactive(pInvNo, false);
			Date cDate = compOutwardMt.getInvDate();
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

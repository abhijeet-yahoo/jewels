package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CompInwardDtController {

	final private static Logger logger = LoggerFactory.getLogger(CompInwardDtController.class);

	@Autowired
	private ICompInwardMtService compInwardMtService;;

	@Autowired
	private ICompInwardDtService compInwardDtService;

	@Autowired
	private IMetalService metalService;

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IDepartmentService departmentService;

	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@RequestMapping("/compInwardDt")
	public String users(Model model) {
		return "compInwardDt";
	}

	@RequestMapping("/compInwardDt/listing")
	@ResponseBody
	public String compInwardDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,Principal principal) {

		logger.info("CompInwardDt Listing");

		StringBuilder sb = new StringBuilder();
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		sb.append("{\"total\":").append(compInwardDtService.count())
				.append(",\"rows\": [");
		String disable = "";
		if(canViewFlag){
			disable = "disabled = 'disabled'";
		}else{
			System.err.println("in else view");
		}
		
		CompInwardMt compInwardMt = compInwardMtService.findByInvNoAndDeactive(pInvNo, false);
		List<CompInwardDt> compInwardDts = compInwardDtService.findByCompInwardMtAndDeactive(compInwardMt, false);

		for (CompInwardDt compInwardDt : compInwardDts) {
			sb.append("{\"id\":\"")
					.append(compInwardDt.getId())
					.append("\",\"metal\":\"")
					.append((compInwardDt.getMetal() != null ? compInwardDt.getMetal().getName() : ""))
					.append("\",\"component\":\"")
					.append((compInwardDt.getComponent() != null ? compInwardDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((compInwardDt.getPurity() != null ? compInwardDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((compInwardDt.getColor() != null ? compInwardDt.getColor().getName() : ""))
					.append("\",\"department\":\"")
					.append((compInwardDt.getDepartment() != null ? compInwardDt.getDepartment().getName() : ""))
					.append("\",\"invWt\":\"")
					.append(compInwardDt.getInvWt())
					.append("\",\"metalWt\":\"")
					.append(compInwardDt.getMetalWt())
					.append("\",\"qty\":\"")
					.append(compInwardDt.getQty())
					.append("\",\"rate\":\"")
					.append(compInwardDt.getRate())
					.append("\",\"amount\":\"")
					.append(compInwardDt.getAmount())
					.append("\",\"deactive\":\"")
					.append(compInwardDt.getDeactive() ? "Yes" : "No");
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			
			if(!canViewFlag){
				sb.append("\",\"action1\":\"");
				
					sb.append("<a href='javascript:editCompIDt(").append(compInwardDt.getId());
				
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				sb.append("\",\"action2\":\"");
				
					sb.append("<a  href='javascript:deleteCompInwardDt(event,")
						.append(compInwardDt.getId()).append(", 0);' href='javascript:void(0);'");
				
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(compInwardDt.getId())
				 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			}else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("");
			}
			}else {
				if(!canViewFlag){
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='javascript:editCompIDt(").append(compInwardDt.getId());
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append("<a  href='javascript:deleteCompInwardDt(event,")
							.append(compInwardDt.getId()).append(", 0);' href='javascript:void(0);'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(compInwardDt.getId())
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
	

	@RequestMapping("/compInwardDt/add")
	public String add(Model model) {
		return "compInwardDt/add";
	}

	@RequestMapping(value = "/compInwardDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("compInwardDt") CompInwardDt compInwardDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "vPurityId") Integer vPurityId,
			@RequestParam(value = "invWtt") Double invWtt,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";
		
		if (result.hasErrors()) {
			return "compInwardDt/add";
		}
		
		retVal=compInwardDtService.compInwardDtSave(compInwardDt, id, pInvNo, vPurityId, invWtt, principal);
		if(retVal.contains(",")){
		String xy[]=retVal.split(",");
		
		redirectAttributes.addAttribute("success",true);
		redirectAttributes.addAttribute("action",xy[1]);
		return xy[0];
		}else{
			return retVal;
		}
		

		
	}
	

	@RequestMapping(value = "/compInwardDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
	CompInwardDt compInwardDt = new CompInwardDt();
	if(id !=0) {
		 compInwardDt = compInwardDtService.findOne(id);
		 Metal metal = compInwardDt.getMetal();
			model.addAttribute("purityMap",(compInwardDt.getPurity() == null ? null : purityService.getPurityList(metal.getId())));
	}
		model.addAttribute("compInwardDt", compInwardDt);
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("departmentMap",departmentService.getDepartmentListForCompNotCentral());

		return "compInwardDt/add";
	}
	
	@RequestMapping("/compInwardDt/editRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal) {
	
		String retVal = "-1";
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			CompInwardMt compInwardMt = compInwardMtService.findOne(dtId);
			Date cDate = compInwardMt.getInvDate();
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

	@RequestMapping("/compInwardDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		
		
		String retVal = "-1";
		
		retVal =compInwardDtService.compInwDtDelete(id, principal);
		
		CompInwardDt compInwardDt = compInwardDtService.findOne(id);
		if(retVal != "-1"){
			return "/jewels/manufacturing/transactions/compInwardMt/edit/"+compInwardDt.getCompInwardMt().getId()+ ".html";	
		}
		
		return retVal;
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	@RequestMapping("/compInwardDt/addRights")
	@ResponseBody
	public String addRights(
			@RequestParam(value = "pInvNo", required = false) String pInvNo,Principal principal) {
	
		String retVal = "-1";
		
	if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
			
	}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			CompInwardMt compInwardMt = compInwardMtService.findByInvNoAndDeactive(pInvNo, false);
			Date cDate = compInwardMt.getInvDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);

			if(dbDate.equals(curDate)){
				
					retVal = "1";
				
							
			}
			
			
		}
	
		
		
		return retVal;
	}





	//-----------listing of costing (costCompDt)-----//----------//

	

	@RequestMapping("/compFinding/listing")
	@ResponseBody
	public String compFindingListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search){
	
		
		DecimalFormat df = new DecimalFormat("#.###");
	
		StringBuilder sb = new StringBuilder();
	
		sb.append("{\"total\":").append(compInwardDtService.count()).append(",\"rows\": [");
	
		
		List<CompInwardDt> compInwardDts = compInwardDtService.findAll();
	
		for (CompInwardDt compInwardDt : compInwardDts) {
			
			if(compInwardDt.getBalance() <= 0){
				continue;
			}
			
			sb.append("{\"id\":\"")
					.append(compInwardDt.getId())
					.append("\",\"invNo\":\"")
					.append((compInwardDt.getCompInwardMt() != null ? compInwardDt.getCompInwardMt().getInvNo() : ""))
					.append("\",\"invDate\":\"")
					.append((compInwardDt.getCompInwardMt() != null ? compInwardDt.getCompInwardMt().getInvDateStr() : ""))
					.append("\",\"compName\":\"")
					.append((compInwardDt.getComponent() != null ? compInwardDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((compInwardDt.getPurity() != null ? compInwardDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((compInwardDt.getColor() != null ? compInwardDt.getColor().getName() : ""))
					.append("\",\"balance\":\"")
					.append(df.format(compInwardDt.getBalance()))
					.append("\",\"rate\":\"")
					.append(compInwardDt.getRate())
					.append("\"},");
		}
	
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
	
		return str;
		
	}



}




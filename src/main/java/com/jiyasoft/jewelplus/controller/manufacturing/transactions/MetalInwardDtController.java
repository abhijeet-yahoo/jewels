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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalInwardMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalInwardDtController {

	final private static Logger logger = LoggerFactory.getLogger(MetalInwardDtController.class);

	@Autowired
	private IMetalInwardMtService metalInwardMtService;;

	@Autowired
	private IMetalInwardDtService metalInwardDtService;;

	@Autowired
	private IMetalService metalService;

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;

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

	/*
	 * @ModelAttribute("metalInwardDt") public MetalInwardDt construct() {
	 * return new MetalInwardDt(); }
	 */
	@RequestMapping("/metalInwardDt")
	public String users(Model model) {
		//model.addAttribute("metalInwardDt", metalInwardDtService.findAll());

		return "metalInwardDt";
	}

	@RequestMapping("/metalInwardDt/listing")
	@ResponseBody
	public String metalInwardDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,Principal principal) {

		logger.info("MetalInwardDt Listing");

		StringBuilder sb = new StringBuilder();
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("MetalInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	
	/*	
		sb.append("{\"total\":").append(metalInwardDtService.count())
				.append(",\"rows\": [");
		*/
			String disable = "";
			if(canViewFlag){
				disable = "disabled = 'disabled'";
			}else{
				System.err.println("in else view");
			}
				

		MetalInwardMt metalInwardMt = metalInwardMtService.findByInvNoAndDeactive(pInvNo, false);
		
		List<MetalInwardDt> metalInwardDts = metalInwardDtService.findByMetalInwardMtAndDeactive(metalInwardMt, false);
		
		sb.append("{\"total\":").append(metalInwardDts.size()).append(",\"rows\": [");
		
		if(metalInwardDts.size() > 0){
		
				for (MetalInwardDt metalInwardDt : metalInwardDts) {
					sb.append("{\"id\":\"")
							.append(metalInwardDt.getId())
							.append("\",\"metal\":\"")
							.append((metalInwardDt.getMetal() != null ? metalInwardDt.getMetal().getName() : ""))
							.append("\",\"purity\":\"")
							.append((metalInwardDt.getPurity() != null ? metalInwardDt.getPurity().getName() : ""))
							.append("\",\"color\":\"")
							.append((metalInwardDt.getColor() != null ? metalInwardDt.getColor().getName() : ""))
							.append("\",\"invWt\":\"")
							.append(metalInwardDt.getInvWt())
							.append("\",\"metalWt\":\"")
							.append(metalInwardDt.getMetalWt())
							.append("\",\"rate\":\"")
							.append(metalInwardDt.getRate())
							.append("\",\"amount\":\"")
							.append(metalInwardDt.getAmount())
							.append("\",\"in999\":\"")
							.append(metalInwardDt.getIn999())
							.append("\",\"remark\":\"")
							.append((metalInwardDt.getRemark() != null ? metalInwardDt.getRemark() : ""));
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					 if(!canViewFlag){

						sb.append("\",\"action1\":\"");
								
									sb.append("<a href='javascript:editMetalInwDt(").append(metalInwardDt.getId());
								
								sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
								
								sb.append("\",\"action2\":\"");
								
									sb.append("<a  href='javascript:deleteMetalInwardDt(event,")
										.append(metalInwardDt.getId()).append(", 0);' href='javascript:void(0);'");
								
								sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(metalInwardDt.getId())
								 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						.append("");
					}
					
							sb.append("\"},");
				}
				else
				{
					 if(!canViewFlag){

							sb.append("\",\"action1\":\"");
									if (roleRights.getCanEdit()) {
										sb.append("<a href='javascript:editMetalInwDt(").append(metalInwardDt.getId());
									} else {
										sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
									}
									sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
									
									sb.append("\",\"action2\":\"");
									if (roleRights.getCanDelete()) {
										sb.append("<a  href='javascript:deleteMetalInwardDt(event,")
											.append(metalInwardDt.getId()).append(", 0);' href='javascript:void(0);'");
									} else {
										sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
									}
									sb.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(metalInwardDt.getId())
									 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
									
						}else{
							sb.append("\",\"action1\":\"")
							.append("")
							.append("\",\"action2\":\"")
							.append("");
						}
						
								sb.append("\"},");
					}

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


	@RequestMapping("/metalInwardDt/add")
	public String add(Model model) {
		return "metalInwardDt/add";
	}

	@RequestMapping(value = "/metalInwardDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("metalInwardDt") MetalInwardDt metalInwardDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "purityyId") Integer purityyId,
			@RequestParam(value = "invWtt") Double invWtt,
			RedirectAttributes redirectAttributes, Principal principal) {

	
		String retVal = "-1";
		

		if (result.hasErrors()) {
			return "metalInwardDt/add";
		}
		
		retVal=metalInwardDtService.metalInwardSave(metalInwardDt, id, pInvNo, purityyId, invWtt, principal);

		if(retVal.contains(",")){
			String xy[]=retVal.split(",");
			
			redirectAttributes.addAttribute("success",true);
			redirectAttributes.addAttribute("action",xy[1]);
			return xy[0];
			}else{
				return retVal;
			}
			
		}
	
	@RequestMapping("/metalInwardDt/editRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal) {
	
		String retVal = "-1";
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			MetalInwardMt metalInwardMt = metalInwardMtService.findOne(dtId);
			Date cDate = metalInwardMt.getInvDate();
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

	@RequestMapping(value = "/metalInwardDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		MetalInwardDt metalInwardDt = metalInwardDtService.findOne(id);
		
		Double prevBalance = Math.round((metalInwardDt.getInvWt()*metalInwardDt.getPurity().getPurityConv())*1000.0)/1000.0;
		
		if(metalInwardDt.getBalance()<prevBalance){
			model.addAttribute("balFlg",false);
			
		}else{
			model.addAttribute("balFlg", true);
		}
		
		
		model.addAttribute("metalInwardDt", metalInwardDt);
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());

		if (metalInwardDt != null) {
			Metal metal = metalInwardDt.getMetal();
			model.addAttribute("purityMap",(metalInwardDt.getPurity() == null ? null : purityService.getPurityList(metal.getId())));
		}

		return "metalInwardDt/add";
	}

	@RequestMapping("/metalInwardDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
		
		String retVal="-1";
		
		retVal =metalInwardDtService.metalInwardDtDelete(id, principal);
		MetalInwardDt metalInwardDt = metalInwardDtService.findOne(id);
		
		if(retVal != "-1"){
			 return "/jewels/manufacturing/transactions/metalInwardMt/edit/"+metalInwardDt.getMetalInwardMt().getId()+ ".html";	
		}else{
			retVal ="-1";
		}

		return retVal;
	
		
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	@RequestMapping("/metalInwardDt/addRights")
	@ResponseBody
	public String addRights(
			@RequestParam(value = "pInvNo", required = false) String pInvNo,Principal principal) {
	
		String retVal = "-1";
		
	if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
			
	}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			MetalInwardMt metalInwardMt = metalInwardMtService.findByInvNoAndDeactive(pInvNo, false);
			Date cDate = metalInwardMt.getInvDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);

			if(dbDate.equals(curDate)){
				
					retVal = "1";
				
							
			}
			
			
		}
	
		
		
		return retVal;
	}
	
	

}

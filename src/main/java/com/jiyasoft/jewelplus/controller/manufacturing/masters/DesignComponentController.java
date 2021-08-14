package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;

@RequestMapping("/manufacturing/masters")
@Controller
public class DesignComponentController {

	final private static Logger logger = LoggerFactory.getLogger(DesignComponentController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private IDesignService designService;;

	@Autowired
	private IDesignComponentService designComponentService;;

	@Autowired
	private IComponentService componentService;
	
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


	@ModelAttribute("designComponent")
	public DesignComponent construct() {
		return new DesignComponent();
	}

	@RequestMapping("/designComponent")
	public String users(Model model) {
		//model.addAttribute("designComponent", designComponentService.findAll());

		return "designComponent";
	}

	@RequestMapping("/designComponent/listing")
	@ResponseBody
	public String designComponentListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "styleNo", required = false) String styleNo,
			@RequestParam(value = "version", required = false) String version,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {

		logger.info("DesignComponent Listing");

		StringBuilder sb = new StringBuilder();
		Design design = designService.findByStyleNoAndVersion(styleNo, version);

		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		String disable = "";
		if(canViewFlag){
			disable = "disabled = 'disabled'";
		}else{
			System.err.println("in else view");
		}
		
		
		Long rowCount = null;
		if (design != null) {
			if (search == null) {
				search = "" + design.getId();
			}
			rowCount = designComponentService.count(sort, search, true);
	
			sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
			List<DesignComponent> designComponents = designComponentService.findByDesign(design);

			for (DesignComponent designComponent : designComponents) {
				sb.append("{\"id\":\"")
				.append(designComponent.getId())
				.append("\",\"component\":\"")
				.append((designComponent.getComponent() != null ? designComponent.getComponent().getName() : ""))
				.append("\",\"purity\":\"")
				.append((designComponent.getPurity() != null ? designComponent.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((designComponent.getColor() != null ? designComponent.getColor().getName() : ""))
				.append("\",\"quantity\":\"")
				.append((designComponent.getQuantity() != null ? designComponent.getQuantity() : ""))
				.append("\",\"compWt\":\"")
				.append((designComponent.getCompWt() != null ? designComponent.getCompWt() : ""))
				.append("\",\"deactive\":\"")
				.append((designComponent.getDeactive() == null ? "Active" : (designComponent.getDeactive() ? "Deactive" : "Active")))
				.append("\",\"deactiveDt\":\"")
				.append((designComponent.getDeactiveDt() == null ? "" : designComponent.getDeactiveDt()));
				/*.append("\",\"action1\":\"")
				.append("<a href='javascript:editDComponent(")
				.append(designComponent.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a onClick='javascript:designDtDelete(event, ").append(designComponent.getId()).append(", 2);' href='javascript:void(0);'")
				.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(designComponent.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");*/
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					if(!canViewFlag){	
						sb.append("\",\"action1\":\"");
					
							sb.append("<a href='javascript:editDComponent(")
								.append(designComponent.getId());
						
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						
						sb.append("\",\"action2\":\"");
						
							sb.append("<a onClick='javascript:designDtDelete(event, ").append(designComponent.getId()).append(", 2);' href='javascript:void(0);'");
						
						sb.append("class='btn btn-xs btn-danger triggerRemove")
						 .append(designComponent.getId())
						 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						
						
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"");
						
					}
					sb.append("\"},");
					
				}else{
					
					if(!canViewFlag){	
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='javascript:editDComponent(")
								.append(designComponent.getId());
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append("<a onClick='javascript:designDtDelete(event, ").append(designComponent.getId()).append(", 2);' href='javascript:void(0);'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append("class='btn btn-xs btn-danger triggerRemove")
						 .append(designComponent.getId())
						 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");						
						
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"");
						
					}
					sb.append("\"},");
				}
				
				
				
				
				
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	@RequestMapping("/designComponent/add")
	public String add(Model model) {
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());

		return "designComponent/add";
	}

	@RequestMapping(value = "/designComponent/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("designComponent") DesignComponent designComponent,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "dcStyleNo", required = false) String dcStyleNo,
			@RequestParam(value = "dcVersion", required = false) String dcVersion,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		
		
		
		if (result.hasErrors()) {
			return "designComponent/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			designComponent.setCreatedBy(principal.getName());
			designComponent.setCreateDate(new java.util.Date());
			designComponent.setDesign(designService.findByStyleNoAndVersion(dcStyleNo, dcVersion));

			retVal = "1";
		} else {
			designComponent.setModiBy(principal.getName());
			designComponent.setModiDt(new java.util.Date());
			designComponent.setDesign(designService.findByStyleNoAndVersion(dcStyleNo, dcVersion));

			action = "updated";
			retVal = "2";
		}
		
		
		if (designComponent.getPurity().getId() == null) {
			designComponent.setPurity(null);
		}

		if (designComponent.getColor().getId() == null) {
			designComponent.setColor(null);
		}
		
		if(designComponent.getCompWt() == null) {
			designComponent.setCompWt(0.0);
		}
		designComponentService.save(designComponent);
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		// return "redirect:/manufacturing/masters/designComponent/add.html";
		return retVal;
	}

	@RequestMapping(value="/designComponent/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		DesignComponent designComponent = null;

		if (id != 0) {
			designComponent = designComponentService.findOne(id);

			model.addAttribute("designComponent", designComponent);
			model.addAttribute("componentMap", componentService.getComponentList());
			model.addAttribute("purityMap", purityService.getPurityList());
			model.addAttribute("colorMap", colorService.getColorList());
		}

		User loginUser = userService.findByName(principal.getName());
		//model.addAttribute("canUpdate", loginUser.getCanUpdate());
		//model.addAttribute("canDelete", loginUser.getCanDelete());

		return "designComponent/add";
	}

	@RequestMapping("/designComponent/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		designComponentService.delete(id);

		return "1";
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}

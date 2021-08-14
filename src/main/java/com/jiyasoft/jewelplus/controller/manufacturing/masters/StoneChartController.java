package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;

@RequestMapping("/manufacturing/masters")
@Controller
public class StoneChartController {

	@Autowired
	private IShapeService shapeService;

	@Autowired
	private IStoneChartService stoneChartService;

	@Autowired
	private ISizeGroupService sizeGroupService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("stoneChart")
	public StoneChart construct() {
		return new StoneChart();
	}

	@RequestMapping("/stoneChart")
	public String users(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneChart");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "stoneChart";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		
		return "stoneChart";

	}

	@RequestMapping("/stoneChart/listing")
	@ResponseBody
	public String stoneChartListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "shapeId", required = false) Integer shapeId,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<StoneChart> stoneCharts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneChart");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}


		stoneCharts = stoneChartService.searchAll(limit, offset, sort, order, search,shapeId, true);


		sb.append("{\"total\":").append(stoneCharts.getTotalElements()).append(",\"rows\": [");

		for (StoneChart stoneChart : stoneCharts) {
			sb.append("{\"id\":\"")
					.append(stoneChart.getId())
					.append("\",\"shape\":\"")
					.append((stoneChart.getShape() != null ? stoneChart.getShape().getName() : ""))
					.append("\",\"size\":\"")
					.append(stoneChart.getSize())
					.append("\",\"sieve\":\"")
					.append(stoneChart.getSieve())
					.append("\",\"perstonewt\":\"")
					.append(stoneChart.getPerStoneWt())
					.append("\",\"sizeGroup\":\"")
					.append((stoneChart.getSizeGroup() != null ? stoneChart.getSizeGroup().getName() : ""))
					.append("\",\"updatedBy\":\"")
					.append((stoneChart.getModiBy() == null ? "" : stoneChart.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((stoneChart.getModiDt() == null ? "" : stoneChart.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((stoneChart.getDeactive() == null ? ""
							: (stoneChart.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((stoneChart.getDeactiveDt() == null ? ""
							: stoneChart.getDeactiveDt()));
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			

					sb.append("\",\"action1\":\"")
								.append("<a href='/jewels/manufacturing/masters/stoneChart/edit/")
								.append(stoneChart.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/stoneChart/delete/")
							.append(stoneChart.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(stoneChart.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
			}else{
				
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append(
							"<a href='/jewels/manufacturing/masters/stoneChart/edit/")
							.append(stoneChart.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/stoneChart/delete/")
							.append(stoneChart.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(stoneChart.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
				
			}	


		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/stoneChart/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneChart");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			return "stoneChart/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		model = populateModel(model);

		return "stoneChart/add";
	}

	public Model populateModel(Model model) {
		model.addAttribute("shapeList", shapeService.getShapeList());
		return model;
	}

	
	@ResponseBody
	@RequestMapping(value = "/stoneChart/add", method = RequestMethod.POST)
	public String addEditStoneChart(
			@Valid @ModelAttribute("stoneChart") StoneChart stoneChart,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/stoneChart/add.html";

		if (result.hasErrors()) {
			return "stoneChart/add";
		}

		
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			
			StoneChart stnChart = stoneChartService.findByShapeAndSizeAndDeactive(stoneChart.getShape(), stoneChart.getSize(), false);
			
			if(stnChart != null){
				return retVal = "-1";
			}
			
			StoneChart stnChart2 = stoneChartService.findByShapeAndSieveAndDeactive(stoneChart.getShape(), stoneChart.getSieve(), false);
			
			if(stnChart2 != null){
				return retVal = "-2";
			}
			
			
			stoneChart.setCreatedBy(principal.getName());
			stoneChart.setCreateDate(new java.util.Date());
			stoneChart.setDeactive(false);
		} else {
			
			
			StoneChart stnChart2 = stoneChartService.findByShapeAndSizeAndDeactive(stoneChart.getShape(), stoneChart.getSize(), false);
			
			if(stnChart2 != null){
				if(id.equals(stnChart2.getId())){
					
				}else{
					return retVal = "-1";
				}
			}
			
			StoneChart stnChart3 = stoneChartService.findByShapeAndSieveAndDeactive(stoneChart.getShape(), stoneChart.getSieve(), false);
			
			if(stnChart3 != null){
				if(id.equals(stnChart3.getId())){
					
				}else{
					return retVal = "-2";
				}
			}
			
			stoneChart.setModiBy(principal.getName());
			stoneChart.setModiDt(new java.util.Date());

			if (stoneChart.getDeactive() != stoneChartService.findOne(id).getDeactive()) {
				stoneChart.setDeactiveDt(new java.util.Date());
			} else {
				stoneChart.setDeactiveDt(stoneChartService.findOne(id).getDeactiveDt());
			}
			stoneChart.setId(id);

			action = "updated";
			retVal = "/jewels/manufacturing/masters/stoneChart.html?id="+ stoneChart.getShape().getId();
		}
		
		stoneChartService.save(stoneChart);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/stoneChart/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		StoneChart stoneChart = stoneChartService.findOne(id);
		model.addAttribute("stoneChart", stoneChart);
		model.addAttribute("shapeList", shapeService.getShapeList());
		
		if (stoneChart != null) {
			Shape shape = stoneChart.getShape();
			model.addAttribute("sizeGroupMap",
					(stoneChart.getSizeGroup() == null ? null
							: sizeGroupService.getSizeGroupList(shape.getId())));
		}

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneChart");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			return "stoneChart/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

	

		model = populateModel(model);

		return "stoneChart/add";
	}

	@RequestMapping("/stoneChart/delete/{id}")
	public String delete(@PathVariable int id) {
		stoneChartService.delete(id);

		return "redirect:/manufacturing/masters/stoneChart.html";
	}


	@RequestMapping("/sizeGrooup/list")
	@ResponseBody
	public String sizeGroupList(@RequestParam(value = "shpId") Integer shpId,
			@ModelAttribute("stoneChart") StoneChart stoneChart) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> sizeGroupMap = sizeGroupService
				.getSizeGroupList(shpId);

		sb.append("<select id=\"sizeGroup.id\" name=\"sizeGroup.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Size Group -</option>");
		for (Object key : sizeGroupMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(sizeGroupMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

}

package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;


@RequestMapping("/manufacturing/masters")
@Controller
public class LocatioRightsController {

	@Autowired
	private ILocationRightsService locationRightsService;

	@Autowired
	private UserService userService;

	@Autowired
	private IDepartmentService departmentService;

	@ModelAttribute("locationRights")
	public LocationRights construct() {
		return new LocationRights();
	}

	@RequestMapping("/locationRights")
	public String userDeptTrf(Model model) {
		
		model = populateModel(model);
		return "locationRights";
	}
	
	
	@RequestMapping("/locationRights/listing")
	@ResponseBody
	public String locationRightsListing(
			Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "userId", required = false) Integer userId,
			Principal principal) {
		
		User user = userService.findOne(userId);
	
		
		StringBuilder sb = new StringBuilder();

		
		
		List<LocationRights> locationRights = locationRightsService.findByUserAndDeactive(user, false);

		sb.append("{\"total\":").append(locationRights.size()).append(",\"rows\": [");
		
		for (LocationRights locationRight : locationRights) {

			sb.append("{\"id\":\"")
					.append(locationRight.getId())
					.append("\",\"userName\":\"")
					.append((locationRight.getUser() == null ? "" : locationRight.getUser().getName()))
					.append("\",\"toDept\":\"")
					.append((locationRight.getDepartment() == null ? "" : locationRight.getDepartment().getName()))
					.append("\",\"deactive\":\"")
					.append(locationRight.getDeactive() ? "deactive":"active")
					.append("\",\"defaultFlg\":\"")
					.append(locationRight.getDefaultFlg() ? "Selected":"")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteUserLocationRights(")
					.append(locationRight.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove' ><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
					
					
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		

	}	

	@RequestMapping("/locationRights/add")
	public String addUsert(Model model) {
		return "locationRights/add";
	}

	public Model populateModel(Model model) {

		model.addAttribute("departmentMap",departmentService.LooseMetalStkOrComponentStkOrStoneSt(true,true,true));
		model.addAttribute("userMap",userService.getUserList());
		return model;

	}
	
	
	@RequestMapping(value = "/locationRights/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("locationRights") LocationRights locationRights,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vUserId") Integer vUserId,
			@RequestParam(value = "vToDeptId") Integer vToDeptId,
			@RequestParam(value = "vDefaultFlg") Boolean vDefaultFlg,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String action = "added";
		String retVal = "1";

		if (result.hasErrors()) {
			return "locationRights/add";
		}
		
		User user = userService.findOne(vUserId);
		Department department = departmentService.findOne(vToDeptId);

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			
			locationRights.setUser(user);
			locationRights.setDepartment(department);
			locationRights.setDefaultFlg(vDefaultFlg);
			
			locationRights.setCreatedBy(principal.getName());
			locationRights.setCreatedDt(new java.util.Date());
			
			if(vDefaultFlg) {
			LocationRights locationRights2 = locationRightsService.findByUserAndDeactiveAndDefaultFlg(user, false, true);
			if(locationRights2 != null) {
				return "-2";
			}
			}
			
			LocationRights locationRight = locationRightsService.findByUserAndDepartmentAndDeactive(user, department, false);
			
			
					
				if(locationRight != null)
				{
					return "-1";
				}
				
						
			
			
		} else {
			
					
		//	locationRights.setId(id);
			locationRights.setModiBy(principal.getName());
			locationRights.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "1";
		}
		
		locationRightsService.save(locationRights);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);	
		
		return retVal;
		
	}
	
	@RequestMapping("/locationRights/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		locationRightsService.delete(id);

		return "1";
	}

}

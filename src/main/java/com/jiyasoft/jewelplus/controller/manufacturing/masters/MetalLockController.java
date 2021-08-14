package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.MetalLock;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalLockService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;


@RequestMapping("/manufacturing/masters")
@Controller
public class MetalLockController {
	
	@Autowired
	private IMetalLockService metalLockService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired 
	private UserRoleService userRoleService;
	
	@Autowired
	private IMetalService metalService;
	
	
	@ModelAttribute("metalLock")
	public MetalLock construct() {
		return new MetalLock();		
	}
	

	@RequestMapping("/metalLock")
	public String users(Model model, Principal principal) {
		
				User user = userService.findByName(principal.getName());
				UserRole userRole = userRoleService.findByUser(user);
				MenuMast menuMast = menuMastService.findByMenuNm("metalLock");
				RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
					model.addAttribute("canAdd", true);
			
					return "metalLock";
				}else
				
				
				if(roleRights == null){
					return "accesDenied";
				}else{
					model.addAttribute("canAdd", roleRights.getCanAdd());
				}
				
				return "metalLock";
			}
	
	@RequestMapping("/metalLock/listing")
	@ResponseBody
	public String metalLockListing(Model model,


			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<MetalLock> metalLocks= null;

		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd-MM-yyyy");
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		  metalLocks = metalLockService.searchAll(limit, offset, sort, order, search,true);
		
		sb.append("{\"total\":").append(metalLocks.getTotalElements()).append(",\"rows\": [");

		for (MetalLock metalLock : metalLocks) {
	
			String invDate = simpleDateFormat.format(metalLock.getMetalLockDt());
			
			sb.append("{\"id\":\"")
					.append(metalLock.getId())
					.append("\",\"date\":\"")
					.append(invDate)
					.append("\",\"gold\":\"")
					.append(metalLock.getMetal() != null ? metalLock.getMetal().getName() : "")
					.append("\",\"goldRate\":\"")
					.append(metalLock.getMetalLockRate() !=null ? metalLock.getMetalLockRate():"")
					.append("\",\"alloy\":\"")
					.append(metalLock.getMetalLockRate()!=null?metalLock.getMetalLockRate():"")
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/masters/metalLock/edit/")
					.append(metalLock.getId())
					.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/metalLock/delete/")
					.append(metalLock.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(metalLock.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
				
			
		}		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println(str);
		
		return str;
	}
	
	
	@RequestMapping("/metalLock/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalLock");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "metalLock/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "metalLock/add";
	}
	
	
	@RequestMapping("/metalLockRate/rateMaster/listing")
	@ResponseBody
	public String metalLockRateListing() {
		
		List<Metal> metalList = metalService.findActiveMetals();
		
		List<MetalLock> finalMetalLockRateList = new ArrayList<MetalLock>();
		
			
			for(Metal metal:metalList){
				
				MetalLock metalLockRate = new MetalLock();
				metalLockRate.setId(0);
				metalLockRate.setMetal(metal);
				metalLockRate.setMetalLockRate(0.0);
				finalMetalLockRateList.add(metalLockRate);
				
			}
			
		
		
			StringBuilder sb = new StringBuilder();
			
			sb.append("{\"total\":").append(finalMetalLockRateList.size()).append(",\"rows\": [");
		
			for(MetalLock metalLockRate:finalMetalLockRateList){
				sb.append("{\"id\":\"")
					.append(metalLockRate.getId())
					.append("\",\"metal\":\"")
					.append(metalLockRate.getMetal() != null ? metalLockRate.getMetal().getName() : "")
					.append("\",\"rate\":\"")
					.append(metalLockRate.getMetalLockRate())
					
				.append("\"},");
			}
		
		
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
			
		
			
			return str;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/metalLockRate/addTabData")
	public String orderMetalRateSave(
			@RequestParam(value = "tabData") String tabData,
			@RequestParam(value = "mtlLockDt") String mtlLockDt,Principal principal) throws ParseException{
		
		String retVal = "-1";
		Date date = new Date();
		if(mtlLockDt !=null && !mtlLockDt.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(mtlLockDt);
				
		}
		
		
		try {
			JSONArray jsonArrays = new JSONArray(tabData);
			
			for(int y=0;y<jsonArrays.length();y++){
				
				JSONObject jsonObject = (JSONObject) jsonArrays.get(y);
				
				if(jsonObject.getInt("id") > 0){
					
					//edit Mode
					MetalLock metalLock =  metalLockService.findOne(jsonObject.getInt("id"));
					metalLock.setMetalLockRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					metalLock.setModiBy(principal.getName());
					metalLock.setModiDate(new java.util.Date());
					metalLock.setMetalLockDt(date);
					
					metalLockService.save(metalLock);
					
					
				}else{
					//add Mode
					
					if(Double.parseDouble(jsonObject.get("rate").toString()) > 0){
					
					
						
						MetalLock metalLock =  new MetalLock();
						metalLock.setMetalLockRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
						metalLock.setMetal(metalService.findByName(jsonObject.get("metal").toString()));
						metalLock.setCreatedBy(principal.getName());
						metalLock.setCreatedDate(new Date());
						metalLock.setMetalLockDt(date);
					
						metalLockService.save(metalLock);
					}
					
				}
				
			}
			
			
			
		} catch (Exception e) {
			retVal = "-2";
			e.printStackTrace();
		}
		
	
		
		
		
		return retVal;
	}
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
}//Class End

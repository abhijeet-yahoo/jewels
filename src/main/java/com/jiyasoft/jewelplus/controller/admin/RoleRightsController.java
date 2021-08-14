package com.jiyasoft.jewelplus.controller.admin;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserService;

@Controller
@RequestMapping("/admin")
public class RoleRightsController {
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleMastService roleMastService;
	
	@Autowired
	private UserService userService;
	
	/*@Autowired
	private CommonUtilService commonUtilService;*/
	
	@ModelAttribute("roleRights")
	public RoleRights construct(){
		return new RoleRights();
	}
	
	@RequestMapping("/roleRights")
	public String roleRight(Model model,HttpSession httpSession,Principal principal){
		
		/*if(principal.getName().equalsIgnoreCase("admin1") || principal.getName().equalsIgnoreCase("super")){
			Company compSessionObj = (Company) httpSession.getAttribute("companyObj");
			if(compSessionObj == null){
				return CommonUtils.genericError("404", "Company Object Not Found, Contact Support", model);
			}*/
			model = populateModel(model);
		/*}else{
			return CommonUtils.genericError("404", "Access Denied", model);
		}*/
		
	    return "roleRights";
	}
	
	
	@RequestMapping("/roleRights/listing")
	@ResponseBody
	public String roleRightsListing(Model model, @RequestParam(value = "roleMastId") Integer roleMastId,HttpSession httpSession){
		StringBuilder sb = new StringBuilder();
		//Company compSessionObj = (Company) httpSession.getAttribute("companyObj");
		RoleMast roleMast = roleMastService.findOne(roleMastId);
		String rightVal="";
		List<RoleRights> roleRights = roleRightsService.findByRoleMastAndDeactive(roleMast,false);
		sb.append("{\"rightsDetail\": [");
		for(RoleRights rights:roleRights){
			
			rightVal = "";
			
			 sb.append("{\"id\":\"")
				.append(rights.getId())
				.append("\",\"menuHeading\":\"")
				.append(rights.getMenuMast().getMenuHeading() != null ? rights.getMenuMast().getMenuHeading() : "")
				.append("\",\"menuMastId\":\"")
				.append(rights.getMenuMast().getId());
				/*.append("\",\"canAdd\":\"")
				.append(rights.getCanAdd())
				.append("\",\"canEdit\":\"")
				.append(rights.getCanEdit())
				.append("\",\"canDelete\":\"")
				.append(rights.getCanDelete())
				.append("\",\"canCopy\":\"")
				.append(rights.getCanCopy())
				.append("\",\"canView\":\"")
				.append(rights.getCanView())
				.append("\",\"canPreview\":\"")
				.append(rights.getCanPreview())*/
			 	if(rights.getCanAdd().equals(true)){
			 		rightVal="add";
			 	}
			 	if(rights.getCanEdit().equals(true)){
			 		if(rightVal.length() > 0){
			 			rightVal= rightVal+",edit";
			 		}else{
			 			rightVal="edit";
			 		}
			 		
			 	}
			 	if(rights.getCanDelete().equals(true)){
			 		if(rightVal.length() > 0){
			 			rightVal = rightVal+",delete";
			 		}else{
			 			rightVal="delete";
			 		}
			 	}
			 	if(rights.getCanCopy().equals(true)){
			 		if(rightVal.length() > 0){
			 			rightVal = rightVal+",copy";
			 		}else{
			 			rightVal="copy";
			 		}
			 	}
			 	if(rights.getCanView().equals(true)){
			 		if(rightVal.length() > 0){
			 			rightVal = rightVal+",view";
			 		}else{
			 			rightVal="view";
			 		}
			 	}
			 	if(rights.getCanPreview().equals(true)){
			 		if(rightVal.length() > 0){
			 			rightVal= rightVal+",preview";
			 		}else{
			 			rightVal="preview";
			 		}
			 	}
			 	
			 	sb.append("\",\"rightVal\":\"")
				.append(rightVal)
				.append("\"},");
	
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		System.err.println("str : = "+str);
		
		return str;
	}
	
	
	@RequestMapping(value="/roleRights/save" ,method=RequestMethod.POST)
	@ResponseBody
	public String saveRights(@RequestParam(value="roleMastId") Integer roleMastId,
			@RequestParam(value="roleRightObj") String roleRightObj,Principal principal,HttpSession httpSession){
		
		//Company compSessionObj = (Company) httpSession.getAttribute("companyObj");
		RoleMast roleMast = roleMastService.findOne(roleMastId);
		String rightVal="-1";
		List<RoleRights> roleRights = roleRightsService.findByRoleMastAndDeactive(roleMast,false);
				
		for(RoleRights rights :roleRights){
			String delVal="true";
			Integer vCount=0;
			
			JSONObject jObject = new JSONObject(roleRightObj.trim());
			Iterator<?> delkeys = jObject.keys();
			while( delkeys.hasNext() ) {
				vCount=vCount+1;
				String key = (String)delkeys.next();
				
				if(rights.getMenuMast().getMenuHeading().equalsIgnoreCase(key)){
					delVal="false";									
				}
			}
			
		
			if(delVal.equalsIgnoreCase("true")){
				roleRightsService.delete(rights.getId());
				 rightVal="1";
			}
						
		}
		
		List<RoleRights> roleRightsNew = roleRightsService.findByRoleMastAndDeactive(roleMast,false);
	
		Integer id=0;
		JSONObject jObject = new JSONObject(roleRightObj.trim());
		Iterator<?> keys = jObject.keys();
		while( keys.hasNext() ) {
			id=0;
		    String key = (String)keys.next();
		    String rightData[]=jObject.get(key).toString().split(",");
		      
		    
		    for(RoleRights rights :roleRightsNew){
		    	if(rights.getMenuMast().getMenuHeading().equalsIgnoreCase(key)){
		    		id=rights.getId();
		    	}
		    }
		    
		    
		   
		    
		    
		    if(id==0){
		    	MenuMast menuMast =menuMastService.findByMenuHeading(key);
		    	
		    	RoleRights roleRights2 = new RoleRights();
		    	//roleRights2.setCompId(compSessionObj.getCompId());
		    	roleRights2.setCreateDate(new java.util.Date());
		    	roleRights2.setCreatedBy(principal.getName());
		    	roleRights2.setMenuMast(menuMast);
		    	roleRights2.setRoleMast(roleMast);
		       
		    	for(int i=0;i<rightData.length;i++){
		    		if(rightData[i].equalsIgnoreCase("add")){
		    			roleRights2.setCanAdd(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("copy")){
		    			roleRights2.setCanCopy(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("delete")){
		    			roleRights2.setCanDelete(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("edit")){
		    			roleRights2.setCanEdit(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("preview")){
		    			roleRights2.setCanPreview(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("view")){
		    			roleRights2.setCanView(true);
		    		}
		    	}
		        roleRightsService.save(roleRights2);
		        rightVal="1";
		    	
		    }else{
		    	RoleRights roleRights2=roleRightsService.findOne(id);
		    	roleRights2.setModiBy(principal.getName());
		    	roleRights2.setModiDt(new java.util.Date());
		    	roleRights2.setCanAdd(false);
		    	roleRights2.setCanCopy(false);
		    	roleRights2.setCanDelete(false);
		    	roleRights2.setCanEdit(false);
		    	roleRights2.setCanPreview(false);
		    	roleRights2.setCanView(false);
		    	for(int i=0;i<rightData.length;i++){
		    		if(rightData[i].equalsIgnoreCase("add")){
		    			roleRights2.setCanAdd(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("copy")){
		    			roleRights2.setCanCopy(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("delete")){
		    			roleRights2.setCanDelete(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("edit")){
		    			roleRights2.setCanEdit(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("preview")){
		    			roleRights2.setCanPreview(true);
		    		}
		    		if(rightData[i].equalsIgnoreCase("view")){
		    			roleRights2.setCanView(true);
		    		}
		    	}
		    	
		    	
		    roleRightsService.save(roleRights2);
		    rightVal="1";
		    	
		    }
		    
		}

		
		return rightVal;
	}

/*	@RequestMapping(value="/roleRights/add" , method = RequestMethod.POST)
	@ResponseBody
	public String addRoleRights(@Valid @ModelAttribute("roleRights") RoleRights roleRights,
			BindingResult result,
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "pMenuMast") String pMenuMastIds,
			RedirectAttributes redirectAttributes, Principal principal){
		
		String retVal = "-1";
		
		if(result.hasErrors()){
			return "roleRights";
		}
		
		
		if(roleRights.getRoleMast().getId() == null){
			return "-3";
		}
	
		
		if(pMenuMastIds.length() <= 0){
			return "-2";
		}
		
		String menuMastId[] = pMenuMastIds.split(",");
		
		for(int i = 0; i<menuMastId.length;i++){
			
			MenuMast menuMast = menuMastService.findOne(Integer.parseInt(menuMastId[i]));
			
			RoleRights roleRightsValidate = roleRightsService.findByRoleMastAndMenuMast(roleRights.getRoleMast(), menuMast);	
			
			if(roleRightsValidate == null){
				
				RoleRights roleRightsNew = new RoleRights();
				
				roleRightsNew.setRoleMast(roleRights.getRoleMast());
				roleRightsNew.setMenuMast(menuMast);
				roleRightsNew.setDeactive(false);
				roleRightsNew.setCreatedBy(principal.getName());
				roleRightsNew.setCreateDate(new java.util.Date());
				
				roleRightsService.save(roleRightsNew);
			}
		}
		
		return retVal;
		
	}*/
	
	
	public Model populateModel(Model model){
		model.addAttribute("roleMastMap", roleMastService.getRoleMastList());
		return model;
	}
	
	
	@RequestMapping("/roleRights/delete/{id}")
	public String delete(@PathVariable int id){
		 roleRightsService.delete(id);
		return "-1";
	}
	
	
	
	//----------------new Methods-----------//
	
	
	
	@RequestMapping("/roleRights/list")
	@ResponseBody
	public String tests(){
		
		
		StringBuilder sb = new StringBuilder();
		
		
		List<MenuMast> menuMasts = menuMastService.findByParentIdAndDeactiveOrderBySeqNoAsc(0,false);
		
		sb.append("[");
		for(MenuMast menuMast : menuMasts){
			sb.append("{\"text\":\""+menuMast.getMenuHeading()+"\",\"id\":\""+menuMast.getId()+"\",\"nodes\": [");
			
				List<MenuMast> childLists = menuMastService.findByParentIdAndDeactiveOrderBySeqNoAsc(menuMast.getId(),false);
				for(MenuMast childList : childLists){
					if(childList.getHasChild()){
						sb.append("{\"text\": \""+childList.getMenuHeading()+"\",\"id\":\""+childList.getId()+"\",\"nodes\": [");
						String str=childElements(childList.getId());
						sb.append(str.substring(0,str.length()-1));
						sb.append("	]},");
					}else{
						sb.append("{\"text\": \""+childList.getMenuHeading()+"\",\"id\":\""+childList.getId()+"\"},");
					}
				}
				
				sb.deleteCharAt(sb.length() - 1);
			sb.append("]},");
			
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
	
		
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	
	
		
	
	public String childElements(Integer id){
		
		StringBuilder sb = new StringBuilder();
		List<MenuMast> menuMasts = menuMastService.findByParentIdAndDeactiveOrderBySeqNoAsc(id,false);
		
			for(MenuMast menuMast: menuMasts){
				if(menuMast.getHasChild()){
					sb.append("{\"text\": \""+menuMast.getMenuHeading()+"\",\"id\":\""+menuMast.getId()+"\",\"nodes\":[");
					String str=childElements(menuMast.getId());
					sb.append(str.substring(0,str.length()-1));
					sb.append("]},");
					
				}else{
					sb.append("{\"text\": \""+menuMast.getMenuHeading()+"\",\"id\":\""+menuMast.getId()+"\"},");
					
				}
			}
			
			
		
		return sb.toString();
	}
	
	@RequestMapping("/menuHeading")
	@ResponseBody
	public String tests(HttpSession httpSession,Principal principal){
		
	User user =userService.findByName(principal.getName());
	RoleMast roleMast = roleMastService.findOne(user.getRoleMast().getId());
		StringBuilder sb = new StringBuilder();
		
		
		List<MenuMast> menuMasts = menuMastService.findByParentIdAndDeactiveOrderBySeqNoAsc(0,false);
		
		for(MenuMast menuMast : menuMasts){
			
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(roleMast, menuMast);
			if(roleRights==null){
				continue;
			}
				
			
			sb.append("<li class=\"current\"> <a href=\"javascript:void(0)\">"+menuMast.getMenuHeading()+" </a> <ul>");
			
				List<MenuMast> childLists = menuMastService.findByParentIdAndDeactiveOrderBySeqNoAsc(menuMast.getId(),false);
				for(MenuMast childList : childLists){
					
					RoleRights roleRights1 = roleRightsService.findByRoleMastAndMenuMast(roleMast, childList);
					if(roleRights1==null){
						continue;
					}
						
					if(childList.getHasChild()){
						sb.append("<li class=\"current\"> <a href=\"javascript:void(0)\">"+childList.getMenuHeading()+" </a> <ul>");
						sb.append(childElements(childList.getId(),httpSession,principal));
						sb.append("</ul> </li>");
					}else{
						
				
						sb.append("<li class=\"current\"> <a href=\"/jewels/admin/validation/"+childList.getMenuNm()+".html\">"+childList.getMenuHeading()+" </a></li>");
				
						
					}
				}
			
			sb.append("</ul> </li>");
			
		}
		sb.append("<li><a href=\"/jewels/logout\">Logout</a></li>");
	
		
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	
public String childElements(Integer id,HttpSession httpSession,Principal principal){
		
		User user =userService.findByName(principal.getName());
		RoleMast roleMast = roleMastService.findOne(user.getRoleMast().getId());
		StringBuilder sb = new StringBuilder();
		List<MenuMast> menuMasts = menuMastService.findByParentIdAndDeactiveOrderBySeqNoAsc(id,false);
		
			for(MenuMast menuMast: menuMasts){
				
				RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(roleMast, menuMast);
				if(roleRights==null){
					continue;
				}
				
				
				if(menuMast.getHasChild()){
					sb.append("<li class=\"current\"> <a href=\"javascript:void(0)\">"+menuMast.getMenuHeading()+" </a> <ul>");
						sb.append(childElements(menuMast.getId(),httpSession,principal));
					sb.append("</ul> </li>");
				}else{
					
		
						sb.append("<li class=\"current\"> <a href=\"/jewels/admin/validation/"+menuMast.getMenuNm()+".html\">"+menuMast.getMenuHeading()+" </a></li>");
					
					
					
					
				}
			}
		
		return sb.toString();
	}
	
	
	
	
}

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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;


@Controller
@RequestMapping("/manufacturing/masters")
public class DesignMetalController {
	
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IDesignMetalService designMetalService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	
	@ModelAttribute("designMetal")
	public DesignMetal construct() {
		return new DesignMetal();
	}

	@RequestMapping("/designMetal")
	public String users(Model model) {
		return "designMetal";
	}
	
	
	

	@RequestMapping("/designMetal/listing")
	@ResponseBody
	public String designMetalListing(
			@RequestParam(value = "designId", required = false) Integer designId,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,
			Principal principal) {

			StringBuilder sb = new StringBuilder();
			Design design = designService.findOne(designId);
			
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("design");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
			List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
			
			sb.append("{\"total\":").append(designMetals.size()).append(",\"rows\": [");
			
			
			String disable = "";
			if(canViewFlag){
				disable = "disabled = 'disabled'";
			}else{
				System.err.println("in else view");
			}
			
			
			

			for (DesignMetal designMetal : designMetals) {

				sb.append("{\"id\":\"")
					.append(designMetal.getId())
					.append("\",\"purity\":\"")
					.append((designMetal.getPurity() != null ? designMetal.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((designMetal.getColor() != null ? designMetal.getColor().getName() : ""))
					.append("\",\"partNm\":\"")
					.append((designMetal.getPartNm() != null ? designMetal.getPartNm().getFieldValue() : ""))
					.append("\",\"perPcWaxWt\":\"")
					.append((designMetal.getPerPcWaxWt() != null ? designMetal.getPerPcWaxWt() : ""))
					.append("\",\"waxWt\":\"")
					.append((designMetal.getWaxWt() != null ? designMetal.getWaxWt() : ""))
					.append("\",\"perPcSilverWt\":\"")
					.append((designMetal.getPerPcSilverWt() != null ? designMetal.getPerPcSilverWt() : ""))
					.append("\",\"silverWt\":\"")
					.append((designMetal.getSilverWt() != null ? designMetal.getSilverWt() : ""))
					.append("\",\"rptWt\":\"")
					.append((designMetal.getRptWt() != null ? designMetal.getRptWt() : ""))
					.append("\",\"qty\":\"")
					.append((designMetal.getMetalPcs() != null ? designMetal.getMetalPcs() : ""))
					.append("\",\"perPcMetalWt\":\"")
					.append((designMetal.getPerPcMetalWeight() != null ? designMetal.getPerPcMetalWeight() : ""))
					.append("\",\"metalWt\":\"")
					.append((designMetal.getMetalWeight() != null ? designMetal.getMetalWeight() : ""))
					.append("\",\"metalRate\":\"")
					.append((designMetal.getMetalRate() != null ? designMetal.getMetalRate() : ""))
					.append("\",\"perGramRate\":\"")
					.append((designMetal.getPerGramRate() != null ? designMetal.getPerGramRate() : ""))
					.append("\",\"lossPerc\":\"")
					.append((designMetal.getLossPerc() != null ? designMetal.getLossPerc() : ""))
					.append("\",\"metalValue\":\"")
					.append((designMetal.getMetalValue() != null ? designMetal.getMetalValue() : ""))
					.append("\",\"mainMetal\":\"")
					.append((designMetal.getMainMetal() != null ? (designMetal.getMainMetal() ? designMetal.getMainMetal() : false) : false));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					if(!canViewFlag){
						
						sb.append("\",\"action1\":\"");
						
							sb.append("<a href='javascript:editMetal(").append(designMetal.getId());
						
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						
						sb.append("\",\"action2\":\"");
						
							sb.append("<a onClick='javascript:deleteDesignMetal(event, ")
								.append(designMetal.getId()).append(", 0);' href='javascript:void(0);'");
						
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
						 .append(designMetal.getId())
						 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						 

					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						.append("");
					}
				}else{
					if(!canViewFlag){
						
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='javascript:editMetal(").append(designMetal.getId());
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append("<a onClick='javascript:deleteDesignMetal(event, ")
								.append(designMetal.getId()).append(", 0);' href='javascript:void(0);'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
						 .append(designMetal.getId())
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
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}
	
	
	private Model populateModel(Model model) {
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("lookUpMastMap", lookUpMastService.getActiveLookUpMastByType("Design Part"));
		return model;
	}
	
	
	@RequestMapping("/designMetal/add")
	public String add(Model model) {
		model = populateModel(model);
		return "designMetal/add";
	}
	
	
	
	@RequestMapping(value = "/designMetal/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("designMetal") DesignMetal designMetal,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "desPkId", required = false) Integer designId,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		synchronized (this) {
			
			if (result.hasErrors()) {
				return "designMetal/add";
			}

			
			Design design = designService.findOne(designId);
			
			
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				
				DesignMetal designMetalDublicate = designMetalService.findByDesignAndPartNmAndDeactive(design, designMetal.getPartNm(), false);
				
				if(designMetalDublicate != null){
					return retVal = "-4";
				}
				
				
				DesignMetal designMetalCheck = designMetalService.findByDesignAndDeactiveAndMainMetal(design, false, true);
				if(designMetalCheck != null){
					if(designMetal.getMainMetal()){
						return retVal = "-3";
					}
				}else{
					designMetal.setMainMetal(true);
				}
				
				designMetal.setCreatedBy(principal.getName());
				designMetal.setCreateDate(new java.util.Date());
				designMetal.setDesign(design);
				
				
				
				retVal = "1";
				
			} else {
				
				DesignMetal designMetalDublicate = designMetalService.findByDesignAndPartNmAndDeactive(design, designMetal.getPartNm(), false);
				
				if(designMetalDublicate != null && (!designMetalDublicate.getId().equals(designMetal.getId()))){
					return retVal = "-4";
				}
				
				
				DesignMetal designMetalCheck = designMetalService.findByDesignAndDeactiveAndMainMetal(design, false, true);
				
				if(designMetalCheck != null && (!designMetalCheck.getId().equals(designMetal.getId()))){
					if(designMetal.getMainMetal()){
						return retVal = "-3";
					}
				}else{
					designMetal.setMainMetal(true);
				}
				
				designMetal.setId(id);
				designMetal.setModiBy(principal.getName());
				designMetal.setModiDt(new java.util.Date());
				designMetal.setDesign(design);
				action = "updated";
				
				
				
				
				
				retVal = "-2";
			}
			
			designMetal.setWaxWt(designMetal.getPerPcWaxWt()>0?Math.round((designMetal.getPerPcWaxWt()*designMetal.getMetalPcs())*1000.0)/1000.0:0.0 );
			designMetal.setSilverWt(designMetal.getPerPcSilverWt()>0?Math.round((designMetal.getPerPcSilverWt()*designMetal.getMetalPcs())*1000.0)/1000.0:0.0 );
			designMetal.setMetalWeight(designMetal.getPerPcMetalWeight()>0?Math.round((designMetal.getPerPcMetalWeight()*designMetal.getMetalPcs())*1000.0)/1000.0:0.0 );
			
				
			

			designMetalService.save(designMetal);
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
			
		}
		

		return retVal;
	}
	
	

	@RequestMapping(value="/designMetal/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		DesignMetal designMetal = null;

		model = populateModel(model);
		if (id != 0) {
			designMetal = designMetalService.findOne(id);
			model.addAttribute("designMetal", designMetal);		
		}

		return "designMetal/add";
	}

	
	
	@RequestMapping("/designMetal/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		designMetalService.delete(id);
		return "1";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/designMetal/getPartNm")
	public String getPartNm(@RequestParam(value = "designId") Integer designId){
		
		Design design = designService.findOne(designId);
		DesignMetal designMetal = designMetalService.findByDesignAndDeactiveAndMainMetal(design, false, true);
		
		String retVal = "";
		
		if(designMetal != null){
			retVal = designMetal.getPartNm().getId().toString();
		}
		
		return retVal;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/designMetal/updateDesign")
	public String updateDesign(@RequestParam(value = "designId") Integer designId,
			@RequestParam(value="waxWt",required= false)Double waxWt,
			@RequestParam(value="silverWt",required= false)Double silverWt,
			@RequestParam(value="grms",required= false)Double grms,
			@RequestParam(value="finishWt",required= false)Double finishWt){
		
		if(designId>0){
			Design design = designService.findOne(designId);
			
			design.setWaxWt(waxWt);
			design.setSilverWt(silverWt);
			design.setGrms(grms);
			design.setFinishWt(finishWt);
			
			designService.save(design);
			
		}
		
		
		
		
		return "1";
		
	}
	
	
	
	@RequestMapping("/designMetal/defPurity")
	@ResponseBody
	public String defPurity() {

		Purity purity =purityService.getDefaultPurity();
		return purity.getId().toString();
	}
	
	@RequestMapping("/designMetal/defColor")
	@ResponseBody
	public String defColor(){
		
		Color color = colorService.findByDefColorAndDeactive(true,false);
		return color.getId().toString();
	}
	
	
	
	
	
	
	
	
	
	
	
}

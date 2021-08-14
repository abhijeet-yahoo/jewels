package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.DecimalFormat;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStyleLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientStyleLabService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ClientStyleLabController {
	
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IClientStyleLabService clientStyleLabService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IDesignService designService;
	
	
	
	@ModelAttribute("clientStyleLab")
	public ClientStyleLabDt construct() {
		return new ClientStyleLabDt();
	}
	
	
	
	@RequestMapping("/clientStyleLab")
	public String clientStyleLab(Model model, Principal principal) {
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("clientStyleLab");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "clientStyleLab";
			
		}else
			
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "clientStyleLab";
		
	
	}
	
	
	@RequestMapping("/clientStyleLab/listing")
	@ResponseBody
	public String clientStyleLabListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,Principal principal) {
		
		StringBuilder sb = new StringBuilder();
		Page<ClientStyleLabDt> labourCharges = null;
		

		//list code 3 line--//
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("clientStyleLab");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	//	Long rowCount = null;

		labourCharges = clientStyleLabService.searchAll(limit, offset, sort, order, search);
	//	rowCount = labourChargeService.countAll(sort, search, false);
		
		
		sb.append("{\"total\":").append(labourCharges.getTotalElements()).append(",\"rows\": [");

		for (ClientStyleLabDt labourCharge : labourCharges) {
				sb.append("{\"id\":\"")
					.append(labourCharge.getId())
					.append("\",\"styleNo\":\"")
					.append((labourCharge.getDesign() != null ? labourCharge.getDesign().getMainStyleNo() : ""))
					.append("\",\"metal\":\"")
					.append((labourCharge.getMetal() != null ? labourCharge.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((labourCharge.getPurity() != null ? labourCharge.getPurity().getName() : ""))
					.append("\",\"party\":\"")
					.append((labourCharge.getParty() != null ? labourCharge.getParty().getPartyCode() : ""))
					.append("\",\"labourType\":\"")
					.append((labourCharge.getLabourType() != null ? labourCharge.getLabourType().getName() : ""))
					.append("\",\"rate\":\"")
					.append((labourCharge.getRate() != null ? labourCharge.getRate() : ""))
					.append("\",\"fromWeight\":\"")
					.append(labourCharge.getFromWeight())
					.append("\",\"toWeight\":\"")
					.append(labourCharge.getToWeight())
					.append("\",\"pcsRate\":\"")
					.append(labourCharge.getPerPcsRate())
					.append("\",\"gramRate\":\"")
					.append(labourCharge.getPerGramRate())
					.append("\",\"percent\":\"")
					.append(labourCharge.getPercentage())
					.append("\",\"perCarat\":\"")
					.append(labourCharge.getPerCaratRate())
					.append("\",\"updatedDt\":\"")
					.append((labourCharge.getModiDate() == null ? "" : labourCharge.getModiDate()));
					
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
						  sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/clientStyleLab/edit/")
							.append(labourCharge.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
						  sb.append("\",\"action2\":\"")
						    .append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/clientStyleLab/delete/")
							.append(labourCharge.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(labourCharge.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
				
	
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/clientStyleLab/edit/")
							.append(labourCharge.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/clientStyleLab/delete/")
								.append(labourCharge.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(labourCharge.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				
	
				}
				
								
		

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	@RequestMapping("/clientStyleLab/add")
	public String add(Model model, Principal principal) {
	//model = popRoleRightModel(model, principal);
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("clientStyleLab");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "clientStyleLab/add";

		}else	
		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}
				return "clientStyleLab/add";
		
		
	}
	
	
	private Model populateModel(Model model) {
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("labTypeMap", labourTypeService.getLabourTypeList());
		model.addAttribute("partyMap",partyService.getPartyList());
		model.addAttribute("purityMap", purityService.getPurityList());
		return model;
	}
	
	
	@RequestMapping(value = "/clientStyleLab/add", method = RequestMethod.POST)
	@ResponseBody
	public String addLabour(
			@Valid @ModelAttribute("clientStyleLab") ClientStyleLabDt clientStyleLabDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/clientStyleLab/add.html?success="+true+"&action="+action;

		if (result.hasErrors()) {
			return "clientStyleLab/add";
		}
		
		int i=0;
		if(clientStyleLabDt.getPerPcsRate() == true){
			i +=1;
		}
		
		if(clientStyleLabDt.getPerGramRate() == true){
			i +=1;
		}
		
		if(clientStyleLabDt.getPercentage() == true){
			i +=1;
		}
		
		if(clientStyleLabDt.getPerCaratRate() == true){
			i +=1;
		}
		
		if(i >1){
			return retVal = "-2";
		}else if(i==0){
			return retVal = "-3";
		}
		
		
		Design design =designService.findByMainStyleNoAndDeactive(clientStyleLabDt.getDesign().getMainStyleNo(),false);
		
		clientStyleLabDt.setDesign(design);

		
		String labourChargeCheck= "";
		DecimalFormat df = new DecimalFormat("#.##");
		
		labourChargeCheck =  clientStyleLabService.CheckDuplicate(id, clientStyleLabDt.getParty().getId(), clientStyleLabDt.getDesign().getId(), clientStyleLabDt.getMetal().getId(),
				clientStyleLabDt.getLabourType().getId(), clientStyleLabDt.getFromWeight(), clientStyleLabDt.getToWeight(),clientStyleLabDt.getPurity().getId());
		
		if(labourChargeCheck.equalsIgnoreCase("true")){
			return retVal = "-4";
		}
		
		if(clientStyleLabDt.getToWeight() < clientStyleLabDt.getFromWeight()){
			return retVal = "-5";
		}
		
		if((clientStyleLabDt.getLabourType() == null) || clientStyleLabDt.getLabourType().getId() == null ){
			clientStyleLabDt.setLabourType(null);
		}
		
		if((clientStyleLabDt.getParty() == null) || clientStyleLabDt.getParty().getId() == null ){
			clientStyleLabDt.setParty(null);
		}
		
		if(clientStyleLabDt.getPurity().getId() == null ){
			clientStyleLabDt.setPurity(null);
		}
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			
			
			clientStyleLabDt.setCreatedBy(principal.getName());
			clientStyleLabDt.setCreatedDate(new java.util.Date());
			clientStyleLabDt.setFromWeight(Double.parseDouble(df.format(clientStyleLabDt.getFromWeight())));
			clientStyleLabDt.setToWeight(Double.parseDouble(df.format(clientStyleLabDt.getToWeight())));
			
		} else {
			
			
			
	
			clientStyleLabDt.setModiBy(principal.getName());
			clientStyleLabDt.setModiDate(new java.util.Date());
			clientStyleLabDt.setFromWeight(Double.parseDouble(df.format(clientStyleLabDt.getFromWeight())));
			clientStyleLabDt.setToWeight(Double.parseDouble(df.format(clientStyleLabDt.getToWeight())));

			action = "updated";
			retVal = "/jewels/manufacturing/masters/clientStyleLab.html?id="+ clientStyleLabDt.getDesign().getId()+"&success="+true+"&action="+action;

		}
		
		
		
		clientStyleLabService.save(clientStyleLabDt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}
	
	
	
	@RequestMapping("/clientStyleLab/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		ClientStyleLabDt clientStyleLabDt = clientStyleLabService.findOne(id);
		model.addAttribute("clientStyleLab", clientStyleLabDt);
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("clientStyleLab");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "clientStyleLab/add";

		}else
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model = populateModel(model);
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}

		return "clientStyleLab/add";
		
	
		
		
	}
	
	
	@RequestMapping("/clientStyleLab/delete/{id}")
	public String delete(@PathVariable int id) {
		clientStyleLabService.delete(id);
		return "redirect:/manufacturing/masters/clientStyleLab.html";
	}
	
	
	

}

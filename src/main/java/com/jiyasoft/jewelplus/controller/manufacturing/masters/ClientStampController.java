package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientStampService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ClientStampController {

	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IClientStampService clientStampService;
	
	@Autowired
	private IPurityService purityService;
	
	@Value("${orderstampinst}")
	private String orderstampinst;
	
	
	
	@ModelAttribute("clientStamp")
	public ClientStamp construct(){
		return new ClientStamp();
	}
	
	
	@RequestMapping("/clientStamp")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientStamp");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("purityMap", purityService.getPurityList());
	
			return "clientStamp";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("purityMap", purityService.getPurityList());
		}
		
		
		return "clientStamp";
	}
	
	@RequestMapping("/clientStamp/listing")
	@ResponseBody
	public String clientStampListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "partyNm", required = true) String partyNm,
			Principal principal) {
		
		
		System.out.println("in controller");
		
		Party party = partyService.findByPartyCodeAndDeactive(partyNm, false);
		if(party ==null){
			String retVal="{\"total\":0,\"rows\": []}";
			return retVal;
		}
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientStamp");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		

		StringBuilder sb = new StringBuilder();
		Page<ClientStamp> clientStamps = null;
			
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		clientStamps = clientStampService.findByParty(limit, offset, sort, order, search,true,party);
		
		sb.append("{\"total\":").append(clientStamps.getTotalElements()).append(",\"rows\": [");
		
		for (ClientStamp clientStamp : clientStamps) {
				sb.append("{\"id\":\"")
						.append(clientStamp.getId())
						.append("\",\"party\":\"")
						.append(clientStamp.getParty() != null ? clientStamp.getParty().getPartyCode() : "")
						.append("\",\"purity\":\"")
						.append(clientStamp.getPurity() != null ? clientStamp.getPurity().getName() : "")
						.append("\",\"fromCts\":\"")
						.append(clientStamp.getFromCts() != null ? clientStamp.getFromCts() : "")
						.append("\",\"toCts\":\"")
						.append(clientStamp.getToCts()!=null ?clientStamp.getToCts():"")
						.append("\",\"stampNm\":\"")
						.append(clientStamp.getStampNm()!=null ?clientStamp.getStampNm():"");
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
					sb.append("\",\"action1\":\"")
							.append("<a onClick='javascript:popClientStampEdit(event,"+clientStamp.getId()+ " )'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
							sb.append("\",\"action2\":\"")
								.append("<a onClick='javascript:confirmClientStampDelete(event,"+clientStamp.getId()+ " )'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(clientStamp.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");

				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a onClick='javascript:popClientStampEdit(event,"+clientStamp.getId()+ " )'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
						sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append("<a onClick='javascript:confirmClientStampDelete(event,"+clientStamp.getId()+ " )'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					  sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(clientStamp.getId())
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
	
	@ResponseBody
	@RequestMapping(value = "/clientStamp/add", method = RequestMethod.POST)
	public String addEditClientRef(@Valid @ModelAttribute("clientStamp") ClientStamp clientStamp,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value="vPartyNm")String vPartyNm,
			HttpSession httpSession,Principal principal) {
		
		String retVal = "error";
		synchronized (this) {
			try {
				
				if(clientStamp.getPurity().getId() == null) {
					clientStamp.setPurity(null);					
				}
				
				if (result.hasErrors()) {
					return "Error Contact Admin";
				}
				Party party=partyService.findByPartyCodeAndDeactive(vPartyNm,false);
				if(party ==null){
					return "Party Not Valid";
					
				}
				
				clientStamp.setParty(party);
				
				
			if(orderstampinst.equalsIgnoreCase("purity")) {
			
				retVal = clientStampService.CheckDuplicatePurityWise(id, clientStamp.getParty().getId(), clientStamp.getPurity().getId());
				if(retVal == "true"){
					return retVal = "Duplicate Entry";
				}
			}else {	
			retVal =  clientStampService.CheckDuplicate(clientStamp.getFromCts(), clientStamp.getToCts(), id,clientStamp.getParty().getId());		
			
			if(retVal == "true"){
				return retVal = "Duplicate Entry";
			}
			}
					
				if (id == null || id.equals("") || (id != null && id == 0)) {
					
					
					
					clientStamp.setCreatedBy(principal.getName());
					clientStamp.setCreatedDt(new java.util.Date());
					retVal = "1";
				}else{
					
					
			
									
					clientStamp.setId(id);
					clientStamp.setModiBy(principal.getName());
					clientStamp.setModiDt(new java.util.Date());
					retVal = "1";
				 }

				clientStampService.save(clientStamp);
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Erorr : Record Not Saved("+e+") Contact Support";
			}
			
		}
		
		return retVal;

	}
	
	
	@ResponseBody
	@RequestMapping("/clientStamp/edit/{id}")
	public String  edit(@PathVariable int id, Model model,HttpSession httpSession,Principal principal) {
			
		ClientStamp clientStamp =clientStampService.findOne(id);
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", clientStamp.getId());
		jsonObject.put("fromCts", clientStamp.getFromCts() !=null?clientStamp.getFromCts():0.0);
		jsonObject.put("toCts", clientStamp.getToCts()!=null?clientStamp.getToCts():0.0);
		jsonObject.put("stampNm", clientStamp.getStampNm()!=null?clientStamp.getStampNm():"");
		jsonObject.put("party", clientStamp.getParty()!=null?clientStamp.getParty().getName():"");
		jsonObject.put("purity\\.id", clientStamp.getPurity() != null ? clientStamp.getPurity().getId() : null);
		
		return jsonObject.toString();
		
	}
	
	@ResponseBody
	@RequestMapping("/clientStamp/delete/{id}")
	public String delete(@PathVariable int id) {
		
		clientStampService.delete(id);

		return "redirect:/manufacturing/masters/clientStamp.html";
	}
	
	
}

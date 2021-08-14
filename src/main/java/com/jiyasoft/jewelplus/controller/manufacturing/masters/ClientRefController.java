package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
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







import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientReference;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientRefService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;


@RequestMapping("/manufacturing/masters")
@Controller
public class ClientRefController {
	@Autowired
	private IClientRefService clientRefService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@ModelAttribute("clientRef")
	public ClientReference construct() {
		return new ClientReference();
	}
	
	
	@RequestMapping("/clientRef")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientRef");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("purityMap", purityService.getPurityList());
	
			return "clientRef";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		model.addAttribute("purityMap", purityService.getPurityList());
		
		return "clientRef";
	}
	
	
	@RequestMapping("/clientRef/listing")
	@ResponseBody
	public String clientRefListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "partyNm", required = true) String partyNm,
			Principal principal) {
		
		
		Party party=partyService.findByPartyCodeAndDeactive(partyNm, false);
		if(party ==null){
			String retVal="{\"total\":0,\"rows\": []}";
			return retVal;
		}
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientRef");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		StringBuilder sb = new StringBuilder();
		Page<ClientReference> clientRefs = null;
			
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
	//	Long rowCount = null;
		
		clientRefs = clientRefService.findByParty(limit, offset, sort, order, search,true,party);
	//	rowCount = clientRefService.count(sort, search,true,party);
		
		sb.append("{\"total\":").append(clientRefs.getTotalElements()).append(",\"rows\": [");
		
		for (ClientReference clientReference : clientRefs) {
				sb.append("{\"id\":\"")
						.append(clientReference.getId())
						.append("\",\"styleNo\":\"")
						.append(clientReference.getDesign() !=null ? clientReference.getDesign().getMainStyleNo():"")
						.append("\",\"purityNm\":\"")
						.append(clientReference.getPurity() !=null ? clientReference.getPurity().getName() :"")
						.append("\",\"y\":\"")
						.append(clientReference.getY()!=null ?clientReference.getY():"")
						.append("\",\"w\":\"")
						.append(clientReference.getW()!=null ?clientReference.getW():"")
						.append("\",\"p\":\"")
						.append(clientReference.getP()!=null ?clientReference.getP():"")
						.append("\",\"wy\":\"")
						.append(clientReference.getWy()!=null ?clientReference.getWy():"")
						.append("\",\"wp\":\"")
						.append(clientReference.getWp()!=null ?clientReference.getWp():"")
						.append("\",\"yw\":\"")
						.append(clientReference.getYw()!=null ?clientReference.getYw():"")
						.append("\",\"yp\":\"")
						.append(clientReference.getYp()!=null ?clientReference.getYp():"")
						.append("\",\"py\":\"")
						.append(clientReference.getPy()!=null ?clientReference.getPy():"")
						.append("\",\"pw\":\"")
						.append(clientReference.getPw()!=null ?clientReference.getPw():"")
						.append("\",\"tt\":\"")
						.append(clientReference.getTt()!=null ?clientReference.getTt():"")
						.append("\",\"finishWt\":\"")
						.append(clientReference.getFinishWt()!=null ?clientReference.getFinishWt():"")
						.append("\",\"caratWt\":\"")
						.append(clientReference.getCaratWt()!=null ?clientReference.getCaratWt():"");
					
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					sb.append("\",\"action1\":\"")
						.append("<a onClick='javascript:popClientRefEdit(event,"+clientReference.getId()+ " )'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
						sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:confirmClientRefDelete(event,"+clientReference.getId()+ " )'")
     				    .append(" class='btn btn-xs btn-danger triggerRemove")
						.append(clientReference.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");

				}else{
					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a onClick='javascript:popClientRefEdit(event,"+clientReference.getId()+ " )'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
						sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append("<a onClick='javascript:confirmClientRefDelete(event,"+clientReference.getId()+ " )'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					  sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(clientReference.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");

				}
						
			

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		
		//System.out.println("strr  "+str);
		
		return str;
		
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/clientRef/add", method = RequestMethod.POST)
	public String addEditClientRef(@Valid @ModelAttribute("clientRef") ClientReference clientReference,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value="vPartyNm")String vPartyNm,
			HttpSession httpSession,Principal principal) {
		
		String retVal = "error";
		synchronized (this) {
			try {
				
				
				
				if (result.hasErrors()) {
					return "Error Contact Admin";
				}
				Party party=partyService.findByPartyCodeAndDeactive(vPartyNm, false);
				if(party ==null){
					return "Party Not Valid";
					
				}
				
				clientReference.setParty(party);
				
				Design design=designService.findByMainStyleNoAndDeactive(clientReference.getDesign().getMainStyleNo(), false);
				if(design == null){
					return "Style Not Valid";
				}
				
				clientReference.setDesign(design);
				
				
				ClientReference clientReference2=clientRefService.findByPartyAndDesignAndPurityAndDeactive(party,design, clientReference.getPurity(), false);
					
				if (id == null || id.equals("") || (id != null && id == 0)) {
					
					if(clientReference2 != null){
						return retVal = "Duplicate Entry";
					}
					
					clientReference.setCreatedBy(principal.getName());
					clientReference.setCreatedDt(new java.util.Date());
					retVal = "1";
				}else{
					
					if(clientReference2 != null){
						if(!(clientReference2.getId().equals(clientReference.getId()))){
							return retVal = "Duplicate Entry";
						}
					}
					
				//	clientReference.setId(id);
					clientReference.setModiBy(principal.getName());
					clientReference.setModiDt(new java.util.Date());
					retVal = "1";
				 }
				
				
				if(clientReference.getY() != "") {
					clientReference.setY(clientReference.getY().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
				if(clientReference.getW() != "") {
					clientReference.setW(clientReference.getW().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
				if(clientReference.getP() != "") {
					clientReference.setP(clientReference.getP().replaceAll("[\\n\\t\\r ]", " ").trim());			
				}
				
				if(clientReference.getWy() != "") {
					clientReference.setWy(clientReference.getWy().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
				if(clientReference.getWp() != "") {
					clientReference.setWp(clientReference.getWp().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
				if(clientReference.getYw() != "") {
					clientReference.setYw(clientReference.getYw().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
				if(clientReference.getYp() != "") {
					clientReference.setYp(clientReference.getYp().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
				if(clientReference.getPy() != "") {
					clientReference.setPy(clientReference.getPy().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
				if(clientReference.getPw() != "") {
					clientReference.setPw(clientReference.getPw().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
				if(clientReference.getTt() != "") {
					clientReference.setTt(clientReference.getTt().replaceAll("[\\n\\t\\r ]", " ").trim());
				}
				
			
				clientRefService.save(clientReference);
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Erorr : Record Not Saved("+e+") Contact Support";
			}
			
		}
				
		
		
		
		
		return retVal;

	}
	
	
	
	@ResponseBody
	@RequestMapping("/clientRef/edit/{id}")
	public String  edit(@PathVariable int id, Model model,HttpSession httpSession,Principal principal) {
			
		ClientReference clientReference =clientRefService.findOne(id);
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", clientReference.getId());
		jsonObject.put("design", clientReference.getDesign().getMainStyleNo());
		jsonObject.put("purity", clientReference.getPurity().getId());
		jsonObject.put("finishWt", clientReference.getFinishWt() !=null?clientReference.getFinishWt():0.0);
		jsonObject.put("caratWt", clientReference.getCaratWt()!=null?clientReference.getCaratWt():0.0);
		jsonObject.put("y", clientReference.getY()!=null?clientReference.getY():"");
		jsonObject.put("w", clientReference.getW()!=null?clientReference.getW():"");
		jsonObject.put("p", clientReference.getP()!=null?clientReference.getP():"");
		jsonObject.put("wy", clientReference.getWy()!=null?clientReference.getWy():"");
		jsonObject.put("wp", clientReference.getWp()!=null?clientReference.getWp():"");
		jsonObject.put("yw", clientReference.getYw()!=null?clientReference.getYw():"");
		jsonObject.put("yp", clientReference.getYp()!=null?clientReference.getYp():"");
		jsonObject.put("py", clientReference.getPy()!=null?clientReference.getPy():"");
		jsonObject.put("pw", clientReference.getPw()!=null?clientReference.getPw():"");
		jsonObject.put("tt", clientReference.getTt()!=null?clientReference.getTt():"");
		
		return jsonObject.toString();
		
	}
	
	@ResponseBody
	@RequestMapping("/clientRef/delete/{id}")
	public String delete(@PathVariable int id) {
		String retVal="";
		retVal=clientRefService.delete(id);

		return retVal;
	}
	

}

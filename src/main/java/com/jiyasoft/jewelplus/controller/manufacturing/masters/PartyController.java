package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.CommonUtils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Address;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.PartyExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IAddressService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICountryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILedgerGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStateMasterService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;


@RequestMapping("/manufacturing/masters")
@Controller
public class PartyController {
	
	@Autowired
	private IPartyService partyService;;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ILedgerGroupService ledgerGroupService;
	
	@Autowired
	private ILookUpMastService lookUpService;
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private IStateMasterService stateMasterService;
	
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;
	
	@Autowired
	private IEmployeeService employeeService;
	

	@ModelAttribute("party")
	public Party constructParty() {
		return new Party();
	}
	
	
	@RequestMapping("/party/uploadExcel")
	public String excelFilePage(Model model) {
		model.addAttribute("tableDisp", "no");
		return "partyExcelFileUploaded";
	}

	@RequestMapping("/party")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("party");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "party";
		}else
	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "party";
	}
	
	
	
	

	@RequestMapping("/party/listing")
	@ResponseBody
	public String partyListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = false) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Party> partys = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("party");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		partys = partyService.searchAll(limit, offset, sort, order, search, false);


		sb.append("{\"total\":").append(partys.getTotalElements()).append(",\"rows\": [");
		
		for (Party party : partys) {
					sb.append("{\"id\":\"")
							.append(party.getId())
							.append("\",\"name\":\"")
							.append(party.getName())
							.append("\",\"code\":\"")
							.append((party.getPartyCode() == null ? "" : party.getPartyCode()))
							.append("\",\"email\":\"")
							.append(party.getEmail()!=null? party.getEmail():"")
							.append("\",\"phone\":\"")
							.append(party.getPhone() == null ? "" : party.getPhone())
							.append("\",\"deactive\":\"")
							.append((party.getDeactive() == null ? "" : (party.getDeactive() ? "Deactive" : "Active")))
							.append("\",\"defaultFlag\":\"")
							.append((party.getDefaultFlag() == null ? "" : (party.getDefaultFlag() ? "Yes" : "No")))
							.append("\",\"exportClient\":\"")
							.append((party.getExportClient() == null ? "" : (party.getExportClient() ? "Yes" : "No")));
					
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
						
						sb.append("\",\"action1\":\"")
								.append("<a href='/jewels/manufacturing/masters/party/edit/")
								.append(party.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"")
							  	.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/party/delete/")
								.append(party.getId()).append(".html'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(party.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
					}else{
						
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='/jewels/manufacturing/masters/party/edit/")
									.append(party.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/party/delete/")
									.append(party.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(party.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
					}
		
						
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println("str="+str);

		return str;
	}
	
	
	@RequestMapping("/client/listing")
	@ResponseBody
	public String clientListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
	

	
		List<Party>partys = partyService.findAllExportClient();


		sb.append("{\"total\":").append(partys.size()).append(",\"rows\": [");
		
		for (Party party : partys) {
					sb.append("{\"id\":\"")
							.append(party.getId())
							.append("\",\"name\":\"")
							.append(party.getName())
							.append("\",\"code\":\"")
							.append((party.getPartyCode() == null ? "" : party.getPartyCode()))
							.append("\",\"email\":\"")
							.append(party.getEmail()!=null? party.getEmail():"")
							.append("\",\"phone\":\"")
							.append(party.getPhone() == null ? "" : party.getPhone())
							.append("\",\"deactive\":\"")
							.append((party.getDeactive() == null ? "" : (party.getDeactive() ? "Deactive" : "Active")))
							.append("\",\"defaultFlag\":\"")
							.append((party.getDefaultFlag() == null ? "" : (party.getDefaultFlag() ? "Yes" : "No")))
							.append("\",\"exportClient\":\"")
							.append((party.getExportClient() == null ? "" : (party.getExportClient() ? "Yes" : "No")))
							.append("\"},");
					
							
						
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println("str="+str);

		return str;
	}
	

	@RequestMapping("/party/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("party");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("ledgerGroupMap", ledgerGroupService.getLedgerGroupList());
		model.addAttribute("ledgerTypeMap", lookUpService.getActiveLookUpMastByType("Ledger Type"));
		model.addAttribute("transporterList", partyService.getTransporterList());
		model.addAttribute("countryMap", countryService.getCountryList());
		model.addAttribute("partyTypeList", CommonUtils.getPartyType());
		model.addAttribute("partyLabRateType", CommonUtils.getPartyLabRatePolicy());
		model.addAttribute("partyDiaWtType", CommonUtils.getPartyDiaWtPolicy());
		model.addAttribute("partyDiaRateType", CommonUtils.getPartyDiaRatePolicy());
		model.addAttribute("parentPartyMap", partyService.getExportClientPartyListForSupplier());
		model.addAttribute("partyRegionMap", lookUpService.getActiveLookUpMastByType("Party Region"));
		model.addAttribute("customerTypeMap", lookUpService.getActiveLookUpMastByType("Customer Type"));
		model.addAttribute("salesmanMap", employeeService.getDesignationList("Salesman"));
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "party/add";

		}else	
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "party/add";
	}

	@RequestMapping(value = "/party/add", method = RequestMethod.POST)
	public String addEditParty(
			@Valid @ModelAttribute("party") Party party,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/party/add.html";

		if (result.hasErrors()) {
			return "party/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			party.setCreatedBy(principal.getName());
			party.setCreateDt(new java.util.Date());
			party.setDeactive(false);
			party.setName(party.getName().trim());
		} else {
			party.setId(id);
			party.setModiBy(principal.getName());
			party.setModiDt(new java.util.Date());
			party.setName(party.getName().trim());
			
			action = "updated";
			retVal = "redirect:/manufacturing/masters/party.html";
		}
		
		party.setName(party.getName().replaceAll("[\\n\\t\\r ]", " ").trim());
		party.setPartyCode(party.getPartyCode().replaceAll("[\\n\\t\\r ]", " ").trim());
		
		if(party.getType().getId() == null) {
			party.setType(null);
		}
		
		if(party.getCountry().getId() == null) {
			party.setCountry(null);
		}
		
		if(party.getStateMast().getId() == null) {
			party.setStateMast(null);
		}
		
		if(party.getPartyRegion().getId() == null) {
			party.setPartyRegion(null);
		}
		
		if(party.getCustomerType().getId() == null) {
			party.setCustomerType(null);
		}
		
		if(party.getEmployee().getId() == null) {
			party.setEmployee(null);
		}
		
		partyService.save(party);

		// Insert / Update addresses of the party
		List<Address> addressList = party.getAddressList();
		for (Address address : addressList) {
			System.out.println("\n\n\n\naddress " + address.getAddress1() + " "
					+ address.getAddress2());

			if (action.equalsIgnoreCase("added")) {
				address.setCreateDt(party.getCreateDt());
				address.setCreatedBy(party.getCreatedBy());

				// Billing address is compulsory - Shipping address is optional
				if ((address.getAddress1() != null)
						&& (address.getAddress1().trim().length() > 0)) {
					address.setParty(party);
					addressService.save(address);
				}
			} else {
				if (address.getId() == null) {
					address.setCreateDt(party.getModiDt());
					address.setCreatedBy(party.getModiBy());
				} else {
					address.setModiDt(party.getModiDt());
					address.setModiBy(party.getModiBy());
				}

				// Billing address is compulsory - Shipping address is optional
				address.setParty(party);
				addressService.save(address);
			}
		}

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/party/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		Party party = partyService.findOne(id);
		party.setAddressList(addressService.findByParty(party));

		model.addAttribute("party", party);
		model.addAttribute("ledgerGroupMap", ledgerGroupService.getLedgerGroupList());
		model.addAttribute("ledgerTypeMap", lookUpService.getActiveLookUpMastByType("Ledger Type"));
		model.addAttribute("transporterList", partyService.getTransporterList());
		model.addAttribute("countryMap", countryService.getCountryList());
		model.addAttribute("partyTypeList", CommonUtils.getPartyType());
		model.addAttribute("partyLabRateType", CommonUtils.getPartyLabRatePolicy());
		model.addAttribute("partyDiaWtType", CommonUtils.getPartyDiaWtPolicy());
		model.addAttribute("partyDiaRateType", CommonUtils.getPartyDiaRatePolicy());
		model.addAttribute("parentPartyMap", partyService.getExportClientPartyListForSupplier());
		model.addAttribute("partyRegionMap", lookUpService.getActiveLookUpMastByType("Party Region"));
		model.addAttribute("customerTypeMap", lookUpService.getActiveLookUpMastByType("Customer Type"));
		model.addAttribute("salesmanMap", employeeService.getDesignationList("Salesman"));
		
		if(party.getCountry() != null){
		model.addAttribute("stateMap", stateMasterService.getStateListByCountry(party.getCountry().getId()));
		
		}
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("party");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "party/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "party/add";
	}

	@RequestMapping("/party/delete/{id}")
	public String delete(@PathVariable int id) {
		partyService.delete(id);

		return "redirect:/manufacturing/masters/party.html";
	}

	@RequestMapping("/partyAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean partyAvailable = true;

		name = name.trim();

		if (id == null) {
			partyAvailable = (partyService.findByName(name) == null);
		} else {
			Party party = partyService.findOne(id);
			if (!(name.equalsIgnoreCase(party.getName()))) {
				partyAvailable = (partyService.findByName(name) == null);
			}
		}

		return partyAvailable.toString();
	}
	
	@RequestMapping("/partyCodeAvailable")
	@ResponseBody
	public String partyCodeAvailable(@RequestParam String partyCode,
			@RequestParam Integer id) {
		Boolean partyAvailable = true;

		partyCode = partyCode.trim();

		if (id == null) {
			partyAvailable = (partyService.findByPartyCodeAndDeactive(partyCode, false) == null);
		} else {
			Party party = partyService.findOne(id);
			if (!(partyCode.equalsIgnoreCase(party.getPartyCode()))) {
				partyAvailable = (partyService.findByPartyCodeAndDeactive(partyCode, false) == null);
			}
		}

		return partyAvailable.toString();
	}
	
	
	@RequestMapping("/defaultPartyAvailable")
	@ResponseBody
	public String defaultParty(
			@RequestParam String defaultFlag,
			@RequestParam Integer id) {
	
		Boolean partyAvailable = true;
		Party party = partyService.findByDefaultFlag(true);
		
		if(party != null){
			partyAvailable = false;
	
		}

		return partyAvailable.toString();
	}
	
	

	@ResponseBody
	@RequestMapping("/party/getClientName")
	public String getClientName(
			@RequestParam(value = "partyId") Integer partyId) {
		

	
		Party party = partyService.findOne(partyId);
		
	String partyNm =party.getName();

		return partyNm;
	}
	
	
	
	
	//---------Party report listing---------//

	
		@RequestMapping("/party/report/listing")
		@ResponseBody
		public String partyReportListing(Model model) {

			StringBuilder sb = new StringBuilder();

			QParty qParty = QParty.party;
			JPAQuery query = new JPAQuery(entityManager);
			
			List<Tuple> partyDetails = null;
			
			partyDetails = query.from(qParty).
						where(qParty.deactive.eq(false)).orderBy(qParty.partyCode.asc()).list(qParty.id,qParty.partyCode);
			
			sb.append("{\"total\":").append(partyDetails.size()).append(",\"rows\": [");
			
			for (Tuple row:partyDetails) {
				sb.append("{\"id\":\"")
					.append(row.get(qParty.id))
					.append("\",\"name\":\"")
					.append(row.get(qParty.partyCode))
					.append("\"},");
			}
			
			
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
			return str;
		}
		
	
	
	
		@RequestMapping("/party/autoFillList")
		@ResponseBody
		public String partyAutoFillList(@RequestParam(value = "term", required = false) String partyNm) {
					
			
			Integer limit = 15;
			
			if(partyNm.length() >= 5){
				limit = 100;
			}
			
			
			
			Page<Party> partyList = partyService.findByName(limit, 0, "name", "ASC", partyNm.toUpperCase(), true);
			StringBuilder sb = new StringBuilder();

			for (Party party : partyList) {
				sb.append("\"")
					.append(party.getPartyCode())
					.append("\", ");
			}
		
			
			

			String str = "[" + sb.toString().trim();
			str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]";

			return str;
		}
	
	
		@RequestMapping("/state/list")
		@ResponseBody
		public String stateList(
				@RequestParam(value = "countryId") Integer countryId,HttpSession httpSession) {
			return stateMasterService.getStateListDropDown(countryId);
		}
		
	
		@RequestMapping(value = "/party/excelUpload", method = RequestMethod.POST)
		public String excelUpload(Model model, @RequestParam("excelfile") MultipartFile excelfile, HttpSession session,
				@RequestParam("tempFileName") String tempExcelFile, RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

			String retVal = "yes";
			
			synchronized (this) {
				
				retVal = partyService.partyExcelUpload(excelfile, session, tempExcelFile,principal);
			}
			
			model.addAttribute("tableDisp", "yes");
			model.addAttribute("retVal", retVal);
			
			return "partyExcelFileUploaded";
		}
		
		
		@RequestMapping("/partyExcel/tableListing")
		@ResponseBody
		public String displaySessionTableListing(HttpSession httpSession){
			
			@SuppressWarnings("unchecked")
			List<PartyExcel> partyExcels = (List<PartyExcel>) httpSession.getAttribute("partyExcelSessionList");
			
			StringBuilder sb = new StringBuilder();
			sb.append("{\"total\":").append(partyExcels.size()).append(",\"rows\": [");
			
			for(PartyExcel partyExcel : partyExcels){
				
				sb.append("{\"partyNm\":\"")
				.append((partyExcel.getPartyname() != null ? partyExcel.getPartyname() : ""))
				.append("\",\"partyCode\":\"")
				.append((partyExcel.getPartyCode() != null ? partyExcel.getPartyCode() : ""))
				.append("\",\"ledgerGroup\":\"")
				.append((partyExcel.getLedgerGroup() != null ? partyExcel.getLedgerGroup() : ""))
				.append("\",\"state\":\"")
				.append((partyExcel.getStateNm() != null ? partyExcel.getStateNm() : ""))
				.append("\",\"country\":\"")
				.append((partyExcel.getCountry() != null ? partyExcel.getCountry() : ""))
				.append("\",\"statusRemark\":\"")
				.append((partyExcel.getStatusRemark() != null ? partyExcel.getStatusRemark() : ""))
				.append("\",\"mailingName\":\"")
				.append((partyExcel.getMailingName() != null ? partyExcel.getMailingName() : ""))
				
				.append("\"},");
				
			}
			
			
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
			return str;
		}
		

}

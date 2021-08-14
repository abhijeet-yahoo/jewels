package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingRecDt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingRecDtService;



@RequestMapping("/manufacturing/transactions")
@Controller
public class MeltingMtController {
	
	
	@Autowired
	private IMeltingMtService meltingMtService;
	
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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IMeltingRecDtService meltingRecDtService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	
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
	
	
	@ModelAttribute("meltingMt")
	public MeltingMt construct() {
		return new MeltingMt();
	}
	
	@ModelAttribute("meltingIssDt")
	public MeltingIssDt constructIssDt() {
		return new MeltingIssDt();
	}
	
	@ModelAttribute("meltingRecDt")
	public MeltingRecDt constructRecDt() {
		return new MeltingRecDt();
	}

	@RequestMapping("/meltingMt")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("meltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);

			return "meltingMt";
		} else {

			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}

			return "meltingMt";
		}
	}
	
	@RequestMapping("/meltStnRec")
	public String meltStnRec(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("meltStnRec");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);

			return "meltStnRec";
		} else {

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			
		}
		
		return "meltStnRec";
		}
	}

		
	@RequestMapping("/meltingMt/listing")
	@ResponseBody
	public String meltingMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) throws ParseException {
		
		System.out.println("opt   "+opt);

		StringBuilder sb = new StringBuilder();
		Page<MeltingMt> meltingMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = null;
		if(opt.equalsIgnoreCase("0")) {
			menuMast = menuMastService.findByMenuNm("meltingMt");	
		}else {
			menuMast = menuMastService.findByMenuNm("meltStnRec");
		}
		
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

	
		
		System.out.println("order   "+ order);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}


		
		meltingMts = meltingMtService.searchAll(limit, offset, sort, order, search, true);
		

		sb.append("{\"total\":").append(meltingMts.getTotalElements()).append(",\"rows\": [");

		for (MeltingMt meltingMt : meltingMts) {	
			
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(meltingMt.getInvDate()));
			
					
			sb.append("{\"id\":\"")
					.append(meltingMt.getId())
					.append("\",\"invNo\":\"")
					.append(meltingMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(meltingMt.getInvDateStr())
					.append("\",\"loss\":\"")
					.append((meltingMt.getLoss() != null ? meltingMt.getLoss() : ""));
			
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
					if (opt.equals("0")) {
						
						sb.append("\",\"action1\":\"");
					
							sb.append("<a href='/jewels/manufacturing/transactions/meltingMt/edit/")
							.append(meltingMt.getId()).append(".html'");
							
							sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
					
					}else if (opt.equals("1")) {
						sb.append("\",\"action1\":\"");
							
								sb.append("<a href='/jewels/manufacturing/transactions/meltStnRec/edit/")
								.append(meltingMt.getId()).append(".html'");
						
						sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
					}
					
							sb.append("\",\"action2\":\"");
							
								if(principal.getName().equalsIgnoreCase("Administrator")){
								
								sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingMt/delete/")
								.append(meltingMt.getId());
								}else{
									if(currdate.equals(invDate)){
										sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingMt/delete/")
										  .append(meltingMt.getId()).append(".html'");
									}else{
										sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
									}
								}
								
							sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
									.append(meltingMt.getId())
									.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				
							sb.append("\",\"action3\":\"");
							
								sb.append("<a href='/jewels/manufacturing/transactions/meltingMt/view/")
									.append(meltingMt.getId()).append(".html'");
						
								sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")	
								.append("\"},");
						
			}else{
				
				if (opt.equals("0")) {
					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/transactions/meltingMt/edit/")
						.append(meltingMt.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
				
				}else if (opt.equals("1")) {
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						
							sb.append("<a href='/jewels/manufacturing/transactions/meltStnRec/edit/")
							.append(meltingMt.getId()).append(".html'");
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
				}
				
						sb.append("\",\"action2\":\"");
						if(roleRights.getCanDelete()){
							if(principal.getName().equalsIgnoreCase("Administrator")){
							
							sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingMt/delete/")
							.append(meltingMt.getId());
							}else{
								if(currdate.equals(invDate)){
									sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingMt/delete/")
									  .append(meltingMt.getId()).append(".html'");
								}else{
									sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
								}
							}
							
						}else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
								.append(meltingMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
						sb.append("\",\"action3\":\"");
						if (roleRights.getCanView()) {
							sb.append("<a href='/jewels/manufacturing/transactions/meltingMt/view/")
								.append(meltingMt.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")	
						.append("\"},");
				
			}
							
				
					
							
				}
				
				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				return str;
				
				}
					
		@RequestMapping("/meltStnRec/add")
		public String addMeltStnRec(Model model,Principal principal) {
			
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("meltStnRec");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
			model = populateModel(model,principal);
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				model.addAttribute("canAdd", true);
				model.addAttribute("canEdit", true);
				model.addAttribute("canDelete", true);
				model.addAttribute("canView", true);
				
				return "meltStnRec/add";

			}else {
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
			}
			
			return "meltStnRec/add";
			}
	
		}
	
	
	@RequestMapping("/meltingMt/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("meltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "meltingMt/add";

		}else {
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		model = populateModel(model,principal);
		return "meltingMt/add";
		}

	}
	
	
	private Model populateModel(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		
		model.addAttribute("locationMetalMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		return model;
	}
	
	@RequestMapping(value = "/meltingMt/add", method = RequestMethod.POST)
	public String addEditMeltingMt(@Valid @ModelAttribute("meltingMt") MeltingMt meltingMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "metLoss") Double metLoss,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/meltingMt/add.html";
		java.util.Date vDate = null;

		if (result.hasErrors()) {
			return "meltingMt/add";
		}
		

		if (id == null || id.equals("") || (id != null && id == 0)) {
			vDate = new java.util.Date();
			meltingMt.setCreatedBy(principal.getName());			
			meltingMt.setCreatedDt(vDate);
			meltingMt.setUniqueId(vDate.getTime());
			meltingMt.setLoss(metLoss);
			
		} else {	
			meltingMt.setId(id);
			meltingMt.setModiBy(principal.getName());
			meltingMt.setModiDt(new java.util.Date());
			meltingMt.setLoss(metLoss);
			action = "updated";
			retVal = "redirect:/manufacturing/transactions/meltingMt.html";
		}
		
		
		if (action.equals("added")) {
			Integer maxSrno = meltingMtService.maxSrNo();
			maxSrno = (maxSrno == null ? 1 : maxSrno + 1);
			meltingMt.setSrNo(maxSrno);
			
		}
		
		meltingMtService.save(meltingMt);
		
		if (action.equals("added")) {
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			MeltingMt vMeltingMt = meltingMtService.findByUniqueId(vDate.getTime());
			vMeltingMt.setInvNo("MELT"+"-"+vMeltingMt.getSrNo()+"-"+vYear);
			
			retVal = "redirect:/manufacturing/transactions/meltingMt/edit/"+vMeltingMt.getId()+".html";
			meltingMtService.save(vMeltingMt);
		
		}

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/meltStnRec/edit/{id}")
	public String editMeltStnRec(@PathVariable int id, Model model, Principal principal) {
		MeltingMt meltingMt = meltingMtService.findOne(id);
		model.addAttribute("meltingMt", meltingMt);
		
		Integer totalStone=0;
		Double totalCarat=0.0;
		List<MeltingRecDt> meltingRecDts = meltingRecDtService.findByMeltingMtAndDeactive(meltingMt, false);
		for (MeltingRecDt meltingRecDt : meltingRecDts) {
			totalStone += meltingRecDt.getRecStone();
			totalCarat += meltingRecDt.getRecCarat();
		}
		
		
	
		
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("meltStnRec");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("totalStone", totalStone);
			model.addAttribute("totalCarat", totalCarat);
			
			return "meltStnRec/add";

		}else {
		
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				model.addAttribute("totalStone", totalStone);
				model.addAttribute("totalCarat", totalCarat);
				
			}
			model = populateModel(model,principal);
			return "meltStnRec/add";
			}
	}
	
	@RequestMapping("/meltingMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		MeltingMt meltingMt = meltingMtService.findOne(id);
		model.addAttribute("meltingMt", meltingMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("meltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "meltingMt/add";

		}else {
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		model = populateModel(model,principal);
		return "meltingMt/add";
		}
		
	}

	@RequestMapping("/meltingMt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		String retVal =meltingMtService.meltingMtDelete(id);		
		
		return "redirect:/manufacturing/transactions/meltingMt.html";
	}
	
	
	@ResponseBody
	@RequestMapping("/editLoss/melting")
	public String editLoss(@RequestParam(value = "vLoss") Double vLoss,
			@RequestParam(value = "pInvNo") String pInvNo){
		
		MeltingMt meltingMt = meltingMtService.findByInvNoAndDeactive(pInvNo.trim(), false);
		if(meltingMt != null){
			meltingMt.setLoss(vLoss);
			meltingMtService.save(meltingMt);
		}
		
		
		return "1";
	}
	
	
	@RequestMapping("/meltingMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		MeltingMt meltingMt = meltingMtService.findOne(id);
		model.addAttribute("meltingMt", meltingMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("meltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "meltingMt/add";
		}
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		

		return "meltingMt/add";
	}
	
	
	
	@RequestMapping("/meltingMt/meltingStnRecReport")
	@ResponseBody
	public String marketingReport(@RequestParam(value = "mtId", required = false) Integer mtId,	Principal principal) throws SQLException {
		
		return meltingMtService.meltingStnRecReport(mtId, uploadDirecotryPath, uploadParentDirecotryName, uploadDirecotryName, tmpUploadImage, reportXmlDirectoryPath, reportoutputdirectorypath, principal);

	
	}
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	

}

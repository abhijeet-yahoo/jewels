package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingRecDt;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMakingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MakingMtController {

	@Autowired
	private IMakingMtService makingMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IComponentService componentService;
	
		
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	
	
	@ModelAttribute("makingMt")
	public MakingMt constructMt() {
		return new MakingMt();
	}
	
	@ModelAttribute("makingRecDt")
	public MakingRecDt constructMakingRecDt() {
		return new MakingRecDt();
	}
	
	
	
	@RequestMapping("/makingMt")
	public String makingMt(Model model) {
		//model.addAttribute("meltingMt", meltingMtService.findAll());
		return "makingMt";
	}
	
	
	@RequestMapping("/makingMt/listing")
	@ResponseBody
	public String makingMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) throws ParseException {

		
		return makingMtService.makingMtListing(limit, offset, sort, order, search, principal);

	}
	
	
	@RequestMapping("/makingMt/add")
	public String add(Model model,Principal principal) {
		model = populateModel(model, principal);
		return "makingMt/add";
	}
	
	
	private Model populateModel(Model model,Principal principal) {
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("componentMap", componentService.getComponentList());
		
		//User user = userService.findByName(principal.getName());
		/*
		 * UserRole userRole = userRoleService.findByUser(user);
		 * 
		 * if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true");
		 * 
		 * }else { model.addAttribute("canEditTranddate", "false"); }
		 */
		
		model.addAttribute("canEditTranddate", "false");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		return model;
	}
	
	
	
	
	@RequestMapping(value = "/makingMt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMakingMt(
			@Valid @ModelAttribute("makingMt") MakingMt makingMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			 @RequestParam(value = "prevFreshMetalWt") Double prevFreshMetalWt,
			 @RequestParam(value = "prevKt") Integer prevKt,
			 @RequestParam(value = "prevColor") Integer prevColor,
			 @RequestParam(value = "vLoss") Double vLoss,
			 @RequestParam(value = "vTranDate", required = false) String vTranDate,	
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		String action = "added";

		if(id == null || id.equals("") || (id != null && id == 0)){
			action = "updated";
		}
		String retVal = "/jewels/manufacturing/transactions/makingMt/add.html";
		
		if (result.hasErrors()) {
			return "makingMt/add";
		}
		
		
		retVal=makingMtService.saveMakingMt(makingMt, id, principal, prevKt, prevColor, prevFreshMetalWt,vTranDate);
				
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
	

		return retVal;
	}
	
	
	@RequestMapping("/makingMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		MakingMt makingMt = makingMtService.findOne(id);
		model.addAttribute("makingMt", makingMt);
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model = populateModel(model, principal);
		
		return "makingMt/add";
	}
	
	
	@RequestMapping("/makingMt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		
		String retVal=makingMtService.makingMtDelete(id);
		
		return "redirect:/manufacturing/transactions/makingMt.html";
	}
	
	
	
	
	@RequestMapping("/makingMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {
			invoiceAvailable = (makingMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			MakingMt makingMt = makingMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(makingMt.getInvNo()))) {
				invoiceAvailable = (makingMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}
	
	
	
	@RequestMapping("/making/stock/freshMetalWt")
	@ResponseBody
	public String metalStockCheck(
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("makingMt") MakingMt making) {

		StringBuilder sb = new StringBuilder();

		Integer locationId = departmentService.findByName("Central").getId();
		Double balance = metalTranService.getStockBalance(purityId, colorId,locationId);
		
		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();

	}


	
	
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	

}

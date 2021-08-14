package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IKtConversionDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IKtConversionMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.mysema.query.types.expr.MathExpressions;

@Controller
@RequestMapping("/manufacturing/transactions")
public class KtConversionMtController {
	
	
	@Autowired
	private IKtConversionMtService ktConversionMtService;
	
	@Autowired
	private IKtConversionDtService ktConversionDtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IColorService colorService;

	@Autowired
	private IMetalService metalService;

	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ILocationRightsService locationRightsService;

	
	
	@ModelAttribute("ktConversionMt")
	public KtConversionMt construct(){
		return new KtConversionMt();
	}
	
	@ModelAttribute("ktConversionDt")
	public KtConversionDt constructDt(){
		return new KtConversionDt();
	}
	
	
	@RequestMapping("/ktConversionMt")
	public String ktConversionMt(){
		return "ktConversionMt";
	}
	
	
	
	@RequestMapping("/ktConversionMt/listing")
	@ResponseBody
	public String ktConversionMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<KtConversionMt> ktConversionMts = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = ktConversionMtService.count(sort, search, false);
			ktConversionMts = ktConversionMtService.findByInvNo(limit, offset,sort, order, search, true);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = ktConversionMtService.count(sort, search, false);
			ktConversionMts = ktConversionMtService.findByInvNo(limit, offset,sort, order, search, true);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			ktConversionMts = new PageImpl<KtConversionMt>(new ArrayList<KtConversionMt>());
		} else {
			rowCount = ktConversionMtService.count(sort, search, false);
			ktConversionMts = ktConversionMtService.findByInvNo(limit, offset,sort, order, search, true);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (KtConversionMt ktConversionMt : ktConversionMts) {
			sb.append("{\"id\":\"")
					.append(ktConversionMt.getId())
					.append("\",\"date\":\"")
					.append(ktConversionMt.getInvDateStr())
					.append("\",\"invNo\":\"")
					.append(ktConversionMt.getInvNo())
					.append("\",\"kt\":\"")
					.append(ktConversionMt.getReqPurity().getName())
					.append("\",\"color\":\"")
					.append(ktConversionMt.getReqColor().getName())
					.append("\",\"reqMetal\":\"")
					.append(ktConversionMt.getReqMetalWt())
					.append("\",\"totalIssueWt\":\"")
					.append(ktConversionMt.getTotIssWt())
					.append("\",\"recMetal\":\"")
					.append(ktConversionMt.getRecWt())
					.append("\",\"loss\":\"")
					.append(ktConversionMt.getLoss())
					.append("\",\"scrapWt\":\"")
					.append(ktConversionMt.getScrapWt())
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/ktConversionMt/edit/")
					.append(ktConversionMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteKtConversionMt(event,")
					.append(ktConversionMt.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(ktConversionMt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}

	@RequestMapping("/ktConversionMt/add")
	public String add(Model model, Principal principal) {
		model = populateModel(model, principal);
		return "ktConversionMt/add";

	}

	private Model populateModel(Model model, Principal principal) {
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("purityMapp", purityService.getPurityList());
		
		User user = userService.findByName(principal.getName());
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
	
	
	@RequestMapping(value="/ktConversionMt/add", method=RequestMethod.POST)
	public String addEdit(@Valid @ModelAttribute("ktConversionMt") KtConversionMt ktConversionMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal){
		
		User user = userService.findByName(principal.getName());
		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/ktConversionMt/add.html";
		java.util.Date vDate = null;
		
		if (result.hasErrors()) {
			return "ktConversionMt/add";
		}
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			vDate = new java.util.Date();
			ktConversionMt.setCreatedBy(principal.getName());			
			ktConversionMt.setCreatedDt(vDate);
			ktConversionMt.setUniqueId(vDate.getTime());
			Integer maxSrno = ktConversionMtService.maxSrNo();
			maxSrno = (maxSrno == null ? 1 : maxSrno + 1);
			ktConversionMt.setSrNo(maxSrno);
		}else{
			ktConversionMt.setModiBy(principal.getName());
			ktConversionMt.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/transactions/ktConversionMt/edit/"+id+".html";
		}
		
		if(ktConversionMt.getcDate() == null){
			ktConversionMt.setcDate(new Date());
		}
		
		
		if(ktConversionMt.getReqPurity() != null){
			Purity purity = purityService.findOne(ktConversionMt.getReqPurity().getId());
			ktConversionMt.setReqPurityConv(purity.getPurityConv() != null ? purity.getPurityConv() : 0.0);
		}
		
		ktConversionMtService.save(ktConversionMt);
		LocationRights locationRights = locationRightsService.findByUserAndDeactiveAndDefaultFlg(user, false, true);
		
		
		
		if (action.equals("added")) {
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			KtConversionMt ktConvMt = ktConversionMtService.findByUniqueId(ktConversionMt.getUniqueId());
			ktConvMt.setInvNo("CONV"+"-"+ktConvMt.getSrNo()+"-"+vYear);
			
			retVal = "redirect:/manufacturing/transactions/ktConversionMt/edit/"+ktConvMt.getId()+".html";
			
			
			ktConversionMtService.save(ktConvMt);
		
		}

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		
		//---------------Entry in MetalTran---//
		
		
			
			KtConversionMt ktConvMt = ktConversionMtService.findByUniqueId(ktConversionMt.getUniqueId()); 
			
			MetalTran metalTran = metalTranService.findByRefTranIdAndTranTypeAndInOutFld(ktConvMt.getId(),"AlloyingReceive", "C");
			
			Double mtlRate =0.0;
			
			//Update Metal Rate
			if(ktConvMt.getRecWt() > 0) {
				Double dtValue = 0.0;
				List<KtConversionDt> ktConversionDts = ktConversionDtService.findByKtConversionMtAndDeactive(ktConvMt, false);
				for (KtConversionDt ktConversionDt : ktConversionDts) {
					if(ktConvMt.getMetal().getId() == ktConversionDt.getIssPurity().getMetal().getId()) {
						dtValue += Math.round((ktConversionDt.getIssFreshMetalWt() * ktConversionDt.getMetalRate())*100.0)/100.0;	
					}
					
				}
				
				mtlRate = Math.round((dtValue / ktConvMt.getRecWt())*100.0)/100.0;
				
				
				if(metalTran != null){
					
					
					
					metalTran.setTranDate(ktConversionMt.getcDate());
					
					
					if(locationRights != null) {
						metalTran.setDeptLocation(locationRights.getDepartment());
						
					}else {
						metalTran.setDeptLocation(departmentService.findByName("Central"));	
						
					}
					
					metalTran.setDepartment(departmentService.findByName("Alloying"));
					metalTran.setInOutFld("C");
					metalTran.setPurity(ktConversionMt.getReqPurity());
					metalTran.setColor(ktConversionMt.getReqColor());
					metalTran.setMetalWt(ktConversionMt.getRecWt());
					metalTran.setBalance(ktConversionMt.getRecWt());
					if(ktConversionMt.getReqPurity() != null){
						Purity purity = purityService.findOne(ktConversionMt.getReqPurity().getId());
						metalTran.setPurityConv(purity.getPurityConv() != null ? purity.getPurityConv() : 0.0);
					}else{
						metalTran.setPurityConv(0.0);
					}
					metalTran.setTranType("AlloyingReceive");
					metalTran.setRefTranId(ktConversionMt.getId());
					metalTran.setModiBy(principal.getName());
					metalTran.setModiDt(new java.util.Date());
					metalTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
					metalTranService.save(metalTran);
					
				}else{
					
					MetalTran metalTranNew = new MetalTran();
					metalTranNew.setTranDate(ktConversionMt.getcDate());
					
					
					if(locationRights != null) {
						metalTranNew.setDeptLocation(locationRights.getDepartment());
					
					}else {
						metalTranNew.setDeptLocation(departmentService.findByName("Central"));	
					}
					metalTranNew.setDepartment(departmentService.findByName("Alloying"));
					metalTranNew.setInOutFld("C");
					metalTranNew.setPurity(ktConversionMt.getReqPurity());
					metalTranNew.setColor(ktConversionMt.getReqColor());
					metalTranNew.setMetalWt(ktConversionMt.getRecWt());
					metalTranNew.setBalance(ktConversionMt.getRecWt());
					if(ktConversionMt.getReqPurity() != null){
						Purity purity = purityService.findOne(ktConversionMt.getReqPurity().getId());
						metalTranNew.setPurityConv(purity.getPurityConv() != null ? purity.getPurityConv() : 0.0);
					}else{
						metalTranNew.setPurityConv(0.0);
					}
					metalTranNew.setTranType("AlloyingReceive");
					metalTranNew.setRefTranId(ktConversionMt.getId());
					metalTranNew.setCreatedBy(principal.getName());
					metalTranNew.setCreatedDt(new java.util.Date());
					metalTranNew.setMetalRate(mtlRate != null ? mtlRate :0.0);
					metalTranService.save(metalTranNew);
					
				}
			}
			
			
			
			
			
		
		
		
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/ktConversionMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		KtConversionMt ktConversionMt = ktConversionMtService.findOne(id);
		model.addAttribute("ktConversionMt", ktConversionMt);
		model = populateModel(model, principal);

		if (ktConversionMt != null) {
			Metal metal = ktConversionMt.getMetal();
			model.addAttribute("purityMap", purityService.getPurityList(metal.getId()));
		}

		return "ktConversionMt/add";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/ktConversionMt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal) {
		
		KtConversionMt ktConversionMt = ktConversionMtService.findOne(id);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		Date tempDate = ktConversionMt.getCreatedDt();
		String cDate = dateFormat.format(tempDate);
	    Boolean flag = false;
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			flag = true;
		}else{
			
			if(cDate.equals(curDate)){
				flag = true;
			}
			
		}
		
		
		if(flag){
		
			ktConversionMtService.delete(id);
//----------------------------------IssueDelete-------------------------------------------------------//
			
			List<KtConversionDt> ktConversionDts = ktConversionDtService.findByKtConversionMtAndDeactive(ktConversionMt, false);
			for(KtConversionDt ktConversionDt:ktConversionDts){
				ktConversionDtService.delete(ktConversionDt.getId());
				List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(ktConversionDt.getId(), "AlloyingIssue", false);
				for(MetalTran metalTran : metalTrans){
					metalTranService.delete(metalTran.getId());
				}
			}
			
//-----------------------------------IssueReceive-------------------------------------------------------------//
    	MetalTran metalTran = metalTranService.findByRefTranIdAndTranTypeAndInOutFld(id,"AlloyingReceive", "C");
			
			if(metalTran != null){
				metalTranService.delete(metalTran.getId());
			}
			
			
		}
		
		return flag.toString();
	}

	
	
	
	@RequestMapping("/ktConvMt/purityKt/reqPurity/list")
	@ResponseBody
	public String reqPurityList(
			@RequestParam(value = "metalId") Integer metalId) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> purityMap = purityService.getPurityList(metalId);

		sb.append("<select id=\"reqPurity.id\" name=\"reqPurity.id\" class=\"form-control\" onChange=\"javascript:getReqConversion(this.value);\">");
		sb.append("<option value=\"\">- Select Purity -</option>");
		for (Object key : purityMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(purityMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	
	@RequestMapping("/ktConvMt/pureId/reqConv/list")
	@ResponseBody
	public String purityIdFetch(
			@RequestParam(value = "reqPurityId") Integer reqPurityId) {

		StringBuilder sb = new StringBuilder();
		Purity purity = purityService.findOne(reqPurityId);

		double purityConv = purity.getPurityConv();
		sb.append(purityConv);

		return sb.toString();
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/ktconvMtValid")
	public String backDatedValidation(@RequestParam("MtId") Integer mtId,Principal principal){
		
		KtConversionMt ktConversionMt = ktConversionMtService.findOne(mtId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		Date tempDate = ktConversionMt.getCreatedDt();
		String cDate = dateFormat.format(tempDate);
		
		Boolean flag = false;
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			flag = true;
		}else{
			if(cDate.equals(curDate)){
				flag = true;
			}
		}
		
		return flag.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	

	

}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobMtService;

@Controller
@RequestMapping("/manufacturing/transactions")
public class CostingJobMtController {
	
	
	
	@Autowired
	private ICostingJobMtService costingJobMtService;
	
	@Autowired
	private ICostingJobDtService costingJobDtService;
	
	@Autowired
	private ICostJobStnDtService costJobStnDtService;
	
	@Autowired
	private ICostJobCompDtService costJobCompDtService;
	
	@Autowired
	private ICostJobLabDtService costJobLabDtService;
	
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	
	
	@ModelAttribute("costingJobMt")
	public CostingJobMt construct(){
		return new CostingJobMt();
	}
	
	@ModelAttribute("costingJobDt")
	public CostingJobDt constructJobDt(){
		return new CostingJobDt();
	}
	
	@ModelAttribute("costJobStnDt")
	public CostJobStnDt constructJobStnDt(){
		return new CostJobStnDt();
	}
	
	@ModelAttribute("costJobCompDt")
	public CostJobCompDt constructJobCompDt(){
		return new CostJobCompDt();
	}
	
	@ModelAttribute("costJobLabDt")
	public CostJobLabDt constructJobLabDt(){
		return new CostJobLabDt();
	}
	
	
	
	
	@RequestMapping("/costingJobMt")
	public String costingJobMt(Model model){
		return "costingJobMt";
	}
	
	
	@RequestMapping("/costJobCompFinding")
	public String compFinding(Model model) {
		return "costJobCompFinding";
	}

	
	
	
	@RequestMapping("/costingJobMt/listing")
	@ResponseBody
	public String costingJobMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<CostingJobMt> costingJobMts = null;

		// User loginUser = userService.findByName(principal.getName());
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		/*
		 * Long rowCount = null; if (search == null && sort == null) { rowCount =
		 * costingJobMtService.count(sort, search, true); costingJobMts =
		 * costingJobMtService.findByInvNo(limit, offset, sort, order,search, true); }
		 * else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
		 * rowCount = costingJobMtService.count(sort, search, true); costingJobMts =
		 * costingJobMtService.findByInvNo(limit, offset, sort, order,search, true); }
		 * else if (search != null && sort != null) { rowCount = 0L; costingJobMts = new
		 * PageImpl<CostingJobMt>(new ArrayList<CostingJobMt>()); } else { rowCount =
		 * costingJobMtService.count(sort, search, true); costingJobMts =
		 * costingJobMtService.findByInvNo(limit, offset, sort, order,search, true); }
		 * 
		 * 
		 */
		
		costingJobMts = costingJobMtService.findByInvNo(limit, offset, sort, order,search, true);
		
		sb.append("{\"total\":").append(costingJobMts.getTotalElements()).append(",\"rows\": [");

		for (CostingJobMt costingJobMt : costingJobMts) {
			
			sb.append("{\"id\":\"")
					.append(costingJobMt.getId())
					.append("\",\"party\":\"")
					.append((costingJobMt.getParty() != null ? costingJobMt.getParty().getName() : ""))
					.append("\",\"invNo\":\"")
					.append(costingJobMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(costingJobMt.getInvDateStr())
					.append("\",\"metalRate\":\"")
					.append((costingJobMt.getMetalRate() != null ? costingJobMt.getMetalRate() : ""))
					.append("\",\"expClose\":\"")
					.append((costingJobMt.getExpClose() != null ? (costingJobMt.getExpClose() ? "Closed" : "Open") : ""))
					.append("\",\"silverRate\":\"")
					.append((costingJobMt.getSilverRate() != null ? costingJobMt.getSilverRate() : ""))
					.append("\",\"deactive\":\"")
					.append((costingJobMt.getDeactive() == null ? "": (costingJobMt.getDeactive() ? "Deactive": "Active")))
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/costingJobMt/edit/")
					.append(costingJobMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/costingJobMt/delete/")
					.append(costingJobMt.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(costingJobMt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}

	
	
	@RequestMapping("/costingJobMt/add")
	public String addCostJobMt(Model model){
		model = populateModel(model);
		return "costingJobMt/add";
	}
	
	
	
	public Model populateModel(Model model){
		model.addAttribute("partyMap", partyService.getExportClientPartyList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		model.addAttribute("tagColorMap", getTagColor());	
		return model;
	}
	
	
	
	public List<String> getTagColor(){
		List<String> list = new ArrayList<String>();
		list.add("White");
		list.add("Green");
		list.add("Pink");
		return list;
	}
	
	
	@RequestMapping(value = "/costingJobMt/add", method = RequestMethod.POST)
	public String addEdit(
			@Valid @ModelAttribute("costingJobMt") CostingJobMt costingJobMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal){
		
		
		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/costingJobMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			return "costingJobMt/add";
		}
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			aDate = new java.util.Date();
			costingJobMt.setCreatedBy(principal.getName());
			costingJobMt.setCreatedDt(new java.util.Date());
			costingJobMt.setUniqueId(aDate.getTime());
			costingJobMt.setPartyNm(costingJobMt.getParty().getName());
		}else{
			costingJobMt.setModiBy(principal.getName());
			costingJobMt.setModiDt(new java.util.Date());
			costingJobMt.setPartyNm(costingJobMt.getParty().getName());
			action = "updated";
			retVal = "redirect:/manufacturing/transactions/costingJobMt/edit/"+id+".html";
		}
		
		
		costingJobMtService.save(costingJobMt);
		
		if (action.equals("added")) {
			CostingJobMt costingJobMtNew = costingJobMtService.findByUniqueId(costingJobMt.getUniqueId());
			Integer costingJobMtId = costingJobMtNew.getId();
			retVal  = "redirect:/manufacturing/transactions/costingJobMt/edit/"+costingJobMtId+".html";
		}
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		return retVal;
		
	}
	
	
	@RequestMapping("/costingJobMt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		CostingJobMt costingJobMt = costingJobMtService.findOne(id);
		model.addAttribute("costingJobMt", costingJobMt);
		model = populateModel(model);

		return "costingJobMt/add";
	}
	
	
	
	
	
	@RequestMapping("/costingJobMt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		costingJobMtService.delete(id);
		
		CostingJobMt costingJobMt = costingJobMtService.findOne(id);
		
		List<CostingJobDt> costingJobDts = costingJobDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
		for(CostingJobDt costingJobDt:costingJobDts){
			costingJobDtService.delete(costingJobDt.getId());
		}
		
		List<CostJobStnDt> costJobStnDts = costJobStnDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
		for(CostJobStnDt costJobStnDt:costJobStnDts){
			costJobStnDtService.delete(costJobStnDt.getId());
		}
		
		List<CostJobCompDt> costJobCompDts = costJobCompDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
		for(CostJobCompDt costJobCompDt:costJobCompDts){
			costJobCompDtService.delete(costJobCompDt.getId());
		}
		
		List<CostJobLabDt> costJobLabDts = costJobLabDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
		for(CostJobLabDt costJobLabDt:costJobLabDts){
			costJobLabDtService.delete(costJobLabDt.getId());;
		}

		return "redirect:/manufacturing/transactions/costingJobMt.html";
	}
	
	
	@RequestMapping("/costJobMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (costingJobMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			CostingJobMt costingJobMt = costingJobMtService.findOne(id);
			if (! (invNo.equalsIgnoreCase(costingJobMt.getInvNo())) ) {
				invoiceAvailable = (costingJobMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}

	
	
	
	
	@RequestMapping(value = "/applyRate/costJob/transfer", method = RequestMethod.POST)
	@ResponseBody
	public String applyRate(
			@RequestParam(value="costJobMtIdPkAR") Integer mtId,
			@RequestParam(value="costGldRate") Double costGldRate,
			@RequestParam(value="costSlvRate") Double costSlvRate,
			@RequestParam(value="costAddPerc") Double costAddPerc,
			@RequestParam(value="costTagPerc") Double costTagPerc,
			@RequestParam(value="costDisPerc") Double costDisPerc){
		
		String retval="1";

		CostingJobMt costingJobMt = costingJobMtService.findOne(mtId);
		costingJobMt.setMetalRate(costGldRate);
		costingJobMt.setSilverRate(costSlvRate);
		costingJobMt.setAddPercent(costAddPerc);
		costingJobMt.setTagPercent(costTagPerc);
		costingJobMt.setDispPercent(costDisPerc);
		costingJobMtService.save(costingJobMt);
		
		
		Party party = partyService.findByDefaultFlag(true);

		List<CostingJobDt> costingJobDts = costingJobDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
		
		for(CostingJobDt costingJobDt:costingJobDts){
			costingJobDtService.applyRate(costingJobDt,party);
			costingJobDtService.updateFob(costingJobDt);
			} // ending main for loop
		
			return retval;
	}
	
	
	
		//---------CostingJobMt report listing---------//

	
			/*@RequestMapping("/costingJobMt/report/listing")
			@ResponseBody
			public String costingJobMtReportListing(Model model) {
			StringBuilder sb = new StringBuilder();

			QCostingJobMt qCostingJobMt = QCostingJobMt.costingJobMt;
			JPAQuery query = new JPAQuery(entityManager);
					
			List<Tuple> costingJobMtDetails = null;
					
			costingJobMtDetails = query.from(qCostingJobMt).
								where(qCostingJobMt.deactive.eq(false)).orderBy(qCostingJobMt.invNo.asc()).list(qCostingJobMt.id,qCostingJobMt.invNo);
			
			sb.append("{\"total\":").append(costingJobMtService.count()).append(",\"rows\": [");
					
			for (Tuple row:costingJobMtDetails) {
				sb.append("{\"id\":\"")
					.append(row.get(qCostingJobMt.id))
					.append("\",\"invNo\":\"")
					.append(row.get(qCostingJobMt.invNo))
					.append("\"},");
				}
					
					
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
							
			return str;
		}*/
	

	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	

}

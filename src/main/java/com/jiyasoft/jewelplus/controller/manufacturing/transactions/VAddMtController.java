package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddExportExcel;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVaddMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class VAddMtController {
	
	@Autowired
	private IVaddMtService vaddMtService; 
	
	@Autowired
	private IVAddDtService vaddDtService;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Value("${vaddstnadjtype}")
	private String vaddstnadjtype;

		
	@ModelAttribute("costingMt")
	public CostingMt construct() {
		return new CostingMt();
	}
	
	@ModelAttribute("vAddDt")
	public VAddDt constructDt() {
		return new VAddDt();
	}
	
	@ModelAttribute("vAddStnDt")
	public VAddStnDt constructStnDt() {
		return new VAddStnDt();
	}
	
	
	@RequestMapping("/vaddMt")
	public String valueAddition(Model model) {
		return "valueAddition";
	}
	
	@ModelAttribute("vAddCompInv")
	public VAddCompInv constructCompInv() {
		return new VAddCompInv();
	}
	
	@RequestMapping("/vaddMt/listing")
	@ResponseBody
	public String vaddMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal){
		
		StringBuilder sb = new StringBuilder();
		
		Page<CostingMt> costingMts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = vaddMtService.count(sort, search, true);
			costingMts = vaddMtService.findByInvNo(limit, offset, sort, order,search, true);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = vaddMtService.count(sort, search, true);
			costingMts = vaddMtService.findByInvNo(limit, offset, sort, order,search, true);
		} 
		else if (search != null && sort != null) {
			rowCount = 0L;
			costingMts = new PageImpl<CostingMt>(new ArrayList<CostingMt>());
		} 
		else{
			rowCount = vaddMtService.count(sort, search, true);
			costingMts = vaddMtService.findByInvNo(limit, offset, sort, order,search, true);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (CostingMt costingMt : costingMts) {
			
			sb.append("{\"id\":\"")
					.append(costingMt.getId())
					.append("\",\"party\":\"")
					.append((costingMt.getParty() != null ? costingMt.getParty().getPartyCode() : ""))
					.append("\",\"invNo\":\"")
					.append(costingMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(costingMt.getInvDateStr())
					.append("\",\"metalRate\":\"")
					.append((costingMt.getMetalRate() != null ? costingMt.getMetalRate() : ""))
					.append("\",\"silverRate\":\"")
					.append((costingMt.getSilverRate() != null ? costingMt.getMetalRate() : ""))
					.append("\",\"deactive\":\"")
					.append((costingMt.getDeactive() == null ? "": (costingMt.getDeactive() ? "Deactive": "Active")))
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/vaddMt/edit/")
					.append(costingMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/vaddMt/delete/")
					.append(costingMt.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(costingMt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	private Model populateModel(Model model) {
		model.addAttribute("partyMap", partyService.getExportClientPartyList());
		model.addAttribute("partyMapBuyer", partyService.getExportClientPartyList());
		model.addAttribute("bankMap", getBankList());
		model.addAttribute("payTermMap", getPayTermList());
		model.addAttribute("descriptionMap", getDescriptionList());
		model.addAttribute("vaddstnadjtype", vaddstnadjtype.trim());
		
		return model;
	}
	
	
	@RequestMapping(value = "/vaddMt/add",method = RequestMethod.POST )
	public String addedit(@Valid @ModelAttribute("costingMt") CostingMt costingMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal){
		
		String action = "updated";
		String retVal = "redirect:/manufacturing/transactions/vaddMt/edit/"+id+".html";
		
		if(id != null ){
			
			CostingMt costingMtOld = costingMtService.findOne(id);

			costingMt.setId(id);
			costingMt.setInvNo(costingMtOld.getInvNo());
			costingMt.setInvDate(costingMtOld.getInvDate());
			costingMt.setParty(costingMtOld.getParty());
			costingMt.setTagColor(costingMtOld.getTagColor());
			costingMt.setModiBy(principal.getName());
			costingMt.setModiDate(new java.util.Date());
			costingMt.setMetalRate(costingMtOld.getMetalRate());
			costingMt.setSilverRate(costingMtOld.getSilverRate());
			costingMt.setAddPercent(costingMtOld.getAddPercent());
			costingMt.setTagPercent(costingMtOld.getTagPercent());
			costingMt.setDispPercent(costingMtOld.getDispPercent());
			costingMt.setvAddGoldRate(costingMtOld.getvAddGoldRate());
			costingMt.setvAddSilverRate(costingMtOld.getvAddSilverRate());
			costingMt.setvAddWastage(costingMtOld.getvAddWastage());
			costingMt.setvAdded(costingMtOld.getvAdded());
			
			if(costingMt.getBuyer().getId() == null){
				costingMt.setBuyer(null);
			}
			
			
			String word = vaddMtService.printWord(costingMt);
			costingMt.setInWords(word);
			
			vaddMtService.save(costingMt);
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
			
			
		}
		
		
		return retVal;
	}
	
	
	@RequestMapping("/vaddMt/edit/{id}")
	public String edit(@PathVariable int id,Model model) {
		CostingMt costingMt = vaddMtService.findOne(id);
		model.addAttribute("costingMt",costingMt);
		model.addAttribute("vItc", costingMt.getItcCode() != null ? costingMt.getItcCode() : "711319.30");
		model.addAttribute("cog", costingMt.getCountryOfGoods() != null  ? costingMt.getCountryOfGoods() : "India");
		model = populateModel(model);
		
		return "vaddMt/add";
		
	}
	
	
	
	@RequestMapping("/vaddMt/delete/{id}")
	public String vAddDtDelete(@PathVariable int id){
		
		CostingMt costingMt = costingMtService.findOne(id);
		
		vaddDtService.deleteAll(costingMt);
			
		
		return "redirect:/manufacturing/transactions/vaddMt.html";
		
	}
	
	
	public List<String> getBankList(){
		List<String> bankList = new ArrayList<String>();
		bankList.add("Union Bank Of India");
		return bankList;
	}
	
	
	public List<String> getPayTermList(){
		List<String> payTermList = new ArrayList<String>();
		payTermList.add("60 Days");
		payTermList.add("90 Days");
		payTermList.add("120 Days");
		payTermList.add("150 Days");
		payTermList.add("180 Days");
		payTermList.add("COD");
		return payTermList;
	}
	
	
	public List<String> getDescriptionList(){
		List<String> descriptionList = new ArrayList<String>();
		descriptionList.add("COMBINATION JWELLERY OF GOLD AND COPPER");
		descriptionList.add("COMBINATION JWELLERY OF GOLD AND SILVER");
		descriptionList.add("COMBINATION JWELLERY OF GOLD AND STEEL");
		descriptionList.add("PLAIN GOLD JEWELLERY");
		descriptionList.add("PLAIN SILVER JEWELLERY");
		descriptionList.add("STUDDED DIAMOND GOLD JEWELLERY");
		descriptionList.add("STUDDED SILVER JEWELLERY");
		descriptionList.add("STUDDED & PLAIN GOLD JEWELLERY");	
		descriptionList.add("STUDDED & PLAIN GOLD JEWELLERY WITH DIAMOND/P.S/S.P.S");
		descriptionList.add("STUDDED GOLD JEWELLERY WITH DIAMOND/P.S/S.P.S");
		descriptionList.add("BRONZE ALLOY JEWELLERY STUDDED WITH CZ");
		descriptionList.add("BRONZE ALLOY JEWELLERY STUDDED & PLAIN WITH CZ");
		descriptionList.add("PLAIN BRONZE ALLOY JEWELLERY");
		descriptionList.add("925 SILVER JEWELLERY STUDDED WITH CZ");
		descriptionList.add("925 SILVER JEWELLERY STUDDED & PLAIN WITH CZ");
		descriptionList.add("925 PLAIN SILVER JEWELLERY");
		
		return descriptionList;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/vaddMt/update", method = RequestMethod.POST )
	public String updateCostingMt( @ModelAttribute("costingMt") CostingMt costingMt,
			@RequestParam(value = "id") Integer id, Principal principal){
		
		String retVal = "-1";
		
		System.out.println("printing the id==="+id);
		
		if(id != null ){
			
			CostingMt costingMtOld = costingMtService.findOne(id);

			costingMtOld.setvAddGoldRate(costingMt.getvAddGoldRate());
			costingMtOld.setvAddSilverRate(costingMt.getvAddSilverRate());
			costingMtOld.setvAddAlloyRate(costingMt.getvAddAlloyRate());
			costingMtOld.setvAddWastage(costingMt.getvAddWastage());
			costingMtOld.setvAdded(costingMt.getvAdded());
			costingMtOld.setModiBy(principal.getName());
			costingMtOld.setModiDate(new java.util.Date());
			
			String words = vaddMtService.printWord(costingMtOld);
			costingMtOld.setInWords(words);
			
			vaddMtService.save(costingMtOld);
			
		}
		
		
		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/vaddMt/updateLossPerc")
	public String updateLossPerc(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "lossPerc", required = false) Double lossPerc) {
		
		vaddMtService.updateLossPerc(id, lossPerc);		
		
		
		return "1";
		
	}
	
	
	@ResponseBody
	@RequestMapping("/vaddMt/updateLossPercComp")
	public String updateLossPerccomp(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "lossPerc", required = false) Double lossPerc) {
		
		vaddMtService.updateLossPercComp(id, lossPerc);		
		
		
		return "1";
		
	}
	
	
	
	@RequestMapping("/vadd/reportExcel")
	public ModelAndView getExcel(@RequestParam(value="mtId") Integer mtId){
	    		
		String vCond="mtid in ("+mtId+")";
	            
		
		@SuppressWarnings("unchecked")
		TypedQuery<VaddExportExcel> query =  (TypedQuery<VaddExportExcel>)entityManager.createNativeQuery("{ CALL jsp_rep_valueadditionexcel(?) }",VaddExportExcel.class);
		query.setParameter(1,vCond);
		
		
		List<VaddExportExcel> vaddList = query.getResultList();

		return new ModelAndView(new VaddExcelReportView(), "vaddList", vaddList);
	              
	              
	       }
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	


}



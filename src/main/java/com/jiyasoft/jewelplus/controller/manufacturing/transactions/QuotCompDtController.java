package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class QuotCompDtController {
	
	@Autowired
	private IQuotCompDtService quotCompDtService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private ICompInwardDtService compInwardDtService;
	
	@Autowired
	private IFindingRateMastService findingRateMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	
	@ModelAttribute("quotCompDt")
	public QuotCompDt constructCompDt() {
		return new QuotCompDt();
	}
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	
	@RequestMapping("/quotCompDt/listing")
	@ResponseBody
	public String costCompListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "quotDtId", required = false) Integer quotDtId,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {

		StringBuilder sb = new StringBuilder();

		QuotDt quotDt = quotDtService.findOne(quotDtId);
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
		
		sb.append("{\"total\":").append(quotCompDts.size()).append(",\"rows\": [");
		
		if(quotCompDts.size() > 0){
			for(QuotCompDt quotCompDt:quotCompDts){
				
			sb.append("{\"id\":\"")
				.append(quotCompDt.getId())
				.append("\",\"compName\":\"")
				.append((quotCompDt.getComponent() != null ? quotCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((quotCompDt.getPurity() != null ? quotCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((quotCompDt.getColor() != null ? quotCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((quotCompDt.getMetalWt() != null ? quotCompDt.getMetalWt() : ""))
				.append("\",\"rate\":\"")
				.append((quotCompDt.getCompRate() != null ? quotCompDt.getCompRate() : ""))
				.append("\",\"compQty\":\"")
				.append((quotCompDt.getCompQty() != null ? quotCompDt.getCompQty() : ""))
				.append("\",\"value\":\"")
				.append((quotCompDt.getCompValue() != null ? quotCompDt.getCompValue() : ""))
				.append("\",\"perGramRate\":\"")
				.append(quotCompDt.getPerGramRate())
				.append("\",\"rLock\":\"")
				.append(quotCompDt.getrLock()); //1 = lock & 0 = unlock
			
				if(!canViewFlag){
			sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCompDtLockUnLock(")
				.append(quotCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editQuotCompDt(")
				.append(quotCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a href='javascript:deleteQuotCompDt(event,")
				.append(quotCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(quotCompDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("")
					.append("\",\"actionLock\":\"")
					.append("");
				}
					sb.append("\"},");
				
			}
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	@RequestMapping("/quotCompDt/add")
	public String add(Model model,Principal principal) {
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		return "quotCompDt/add";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/quotCompDt/add" , method = RequestMethod.POST)
	public String addEdit(@Valid @ModelAttribute("quotCompDt") QuotCompDt quotCompDt,
			BindingResult result,
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "forCompQuotMtId") Integer quotMtId,
			@RequestParam(value = "forCompQuotDtId") Integer quotDtId,
			RedirectAttributes redirectAttributes, Principal principal){
		
		
		String retVal = "";

		if (result.hasErrors()) {
			return "xyz";
		}
		
		try {
			retVal = quotCompDtService.transactionalSave(quotCompDt, id, quotMtId, quotDtId, principal,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		
		return retVal;
		
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/quotCompDt/lockUnlock")
	public String lockUnlockCostCompDt(
			@RequestParam(value="compDtId")Integer compDtId){
		
		String retVal = "-1";
		QuotCompDt quotCompDt = quotCompDtService.findOne(compDtId);
		
			if(quotCompDt.getrLock() == true){
				quotCompDt.setrLock(false);
			}else{
				quotCompDt.setrLock(true);
			}
			
		quotCompDtService.save(quotCompDt);
		
		return retVal;
	}
	
	
	@RequestMapping("/quotCompDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "compId")Integer compId){
		
		String retVal = "";
		
		QuotCompDt quotCompDt = quotCompDtService.findOne(compId);
		if(quotCompDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	@RequestMapping("/quotCompDt/edit/{id}")
	public String editQuotCompDt(@PathVariable int id,Model model){
		QuotCompDt quotCompDt = quotCompDtService.findOne(id);
		model.addAttribute("quotCompDt",quotCompDt);
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		
		return "quotCompDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/quotCompDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal){
		
		String retVal = "-1";
		
		QuotCompDt quotCompDt = quotCompDtService.findOne(id);
		
		try {
			quotCompDtService.transactionalDelete(quotCompDt,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "-2";
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/quotCompDt/LockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		QuotMt quotMt = quotMtService.findOne(mtId);
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotMtAndDeactive(quotMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(QuotCompDt quotCompDt:quotCompDts){
			quotCompDt.setrLock(setValue);
			quotCompDtService.save(quotCompDt);
		}
		
		return retVal;
	}
	

	
		//--------comp rate-------//
		@ResponseBody
		@RequestMapping("/quotCompDt/rateFromMaster")
		public String compRateMast(
				@RequestParam(value="componentId") Integer componentId,
				@RequestParam(value="purityId") Integer purityId,
				@RequestParam(value="mPartyId") Integer partyId){
			
			
			Component component = componentService.findOne(componentId);
			Purity 	  purity	= purityService.findOne(purityId);
			Party	  party		= partyService.findOne(partyId);
			
			
			JSONObject jsonObject = new JSONObject();
			
			FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(component, party,purity,false);
			
			if(findingRateMast != null){
				if(findingRateMast.getPerPcRate().equals(true)){
					jsonObject.put("rate", findingRateMast.getRate());
					jsonObject.put("perPcRate", true);
					jsonObject.put("perGramRate", false);
				}else{
					jsonObject.put("rate", findingRateMast.getRate());
					jsonObject.put("perPcRate", false);
					jsonObject.put("perGramRate", true);
					
				}
			}
			
			return jsonObject.toString();
		}
	
	
	
	
	
}

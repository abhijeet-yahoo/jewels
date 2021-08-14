package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingRecDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMakingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMakingRecDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MakingRecDtController {
	
	@Autowired
	private IMakingRecDtService makingRecDtService;
	
	@Autowired
	private IMakingMtService makingMtService;
	
	
	@Autowired
	private IComponentService componentService;;
	
	
	
	@RequestMapping("/makingRecDt/listing")
	@ResponseBody
	public String makingRecDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

		

		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(makingRecDtService.count())
				.append(",\"rows\": [");

		
		MakingMt makingMt = makingMtService.findByInvNoAndDeactive(pInvNo, false);
		
		List<MakingRecDt> makingRecDts = makingRecDtService.findByMakingMtAndDeactive(makingMt, false);
		
		if(makingRecDts.size() > 0){
		
				for (MakingRecDt makingRecDt : makingRecDts) {
					sb.append("{\"id\":\"")
							.append(makingRecDt.getId())
							.append("\",\"component\":\"")
							.append((makingRecDt.getComponent() != null ? makingRecDt.getComponent().getName() : ""))
							.append("\",\"purity\":\"")
							.append((makingRecDt.getPurity() != null ? makingRecDt.getPurity().getName() : ""))
							.append("\",\"color\":\"")
							.append((makingRecDt.getColor() != null ? makingRecDt.getColor().getName() : ""))
							.append("\",\"quantity\":\"")
							.append(makingRecDt.getQty())
							.append("\",\"recWt\":\"")
							.append(makingRecDt.getRecWt())
							.append("\",\"action1\":\"")
							.append("<a href='javascript:editMakingRecDt(")
							.append(makingRecDt.getId())
							.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
							.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/makingRecDt/delete/")
							.append(makingRecDt.getId())
							.append(".html' class='btn btn-xs btn-danger triggerRemove")
							.append(makingRecDt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
				}
		}else{
			System.out.println("No record found");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/makingRecDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMakingRecDt(
			@Valid @ModelAttribute("makingRecDt") MakingRecDt makingRecDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "changedIssueWt") Double changedIssueWt,
			@RequestParam(value = "changeRetMetWt") Double changeRetMetWt,
			RedirectAttributes redirectAttributes, Principal principal) {

		String retVal = "-1";
		

		if (result.hasErrors()) {
			return "makingRecDt/add";
		}
		
		
		retVal=makingRecDtService.saveMakingDt(pInvNo, id, makingRecDt, principal, changedIssueWt, changeRetMetWt);
		
		return retVal;
		
		
	}
	
	
	@RequestMapping(value = "/makingRecDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		MakingRecDt makingRecDt = makingRecDtService.findOne(id);
		model.addAttribute("makingRecDt", makingRecDt);
		model.addAttribute("componentMap", componentService.getComponentList());

		return "makingRecDt/add";
		
	}
	
	
	@RequestMapping("/makingRecDt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		
		Integer makingMtId =makingRecDtService.makingrecDelete(id);
		
		return "redirect:/manufacturing/transactions/makingMt/edit/"+ makingMtId + ".html";
		
	}
	
	
	
	
	
	
	
}

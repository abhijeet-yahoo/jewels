package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobCompIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobCompIssDtController {

	@Autowired
	private IJobIssMtService jobIssMtService;
	
	@Autowired
	private IJobCompIssDtService jobCompIssDtService;
	
	@RequestMapping("/jobCompIssDt/listing")
	@ResponseBody
	public String jobCompIssDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		JobIssMt jobIssMt = jobIssMtService.findOne(mtId);

		List<JobCompIssDt> jobCompIssDts= jobCompIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
		
		sb.append("{\"total\":").append(jobCompIssDts.size()).append(",\"rows\": [");
		
		for (JobCompIssDt jobCompIssDt : jobCompIssDts) {
			sb.append("{\"id\":\"")
					.append(jobCompIssDt.getId())
					.append("\",\"metal\":\"")
					.append((jobCompIssDt.getMetal() != null ? jobCompIssDt.getMetal().getName() : ""))
					.append("\",\"component\":\"")
					.append((jobCompIssDt.getComponent() != null ? jobCompIssDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((jobCompIssDt.getPurity() != null ? jobCompIssDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((jobCompIssDt.getColor() != null ? jobCompIssDt.getColor().getName() : ""))
					.append("\",\"department\":\"")
					.append((jobCompIssDt.getDepartment() != null ? jobCompIssDt.getDepartment().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(jobCompIssDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(jobCompIssDt.getRate())
					.append("\",\"amount\":\"")
					.append(jobCompIssDt.getAmount())
					.append("\",\"qty\":\"")
					.append(jobCompIssDt.getQty())
					.append("\",\"deactive\":\"")
					.append(jobCompIssDt.getDeactive() ? "Yes" : "No");
								
			if(!disableFlg) {
					sb.append("\",\"action1\":\"")
					
					.append("<a href='javascript:editJobCompIssDt(").append(jobCompIssDt.getId())
					.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteJobCompIssDt(event,")
					  .append(jobCompIssDt.getId()).append(", 0);' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(jobCompIssDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
		
					sb.append("\"},");
			}else {
				sb.append("\",\"actionLock\":\"")
				.append("")
				.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				 .append("\"},");
			}

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	@RequestMapping(value = "/jobCompIssDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("jobCompIssDt") JobCompIssDt jobCompIssDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vJobCompIssMtId", required = true) Integer vJobCompIssMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";
		
			retVal= jobCompIssDtService.saveJobCompIssDt(jobCompIssDt, id, vJobCompIssMtId, principal); 
	
		return retVal;			
		}


	@ResponseBody
	@RequestMapping(value = "/jobCompIssDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		JobCompIssDt jobCompIssDt = jobCompIssDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalJobCompIssDtId", jobCompIssDt.getId());
		jsonObject.put("department\\.id", jobCompIssDt.getDepartment().getId());
		jsonObject.put("metal\\.id", jobCompIssDt.getMetal().getId());
		jsonObject.put("component\\.id", jobCompIssDt.getComponent().getId());
		jsonObject.put("purity\\.id", jobCompIssDt.getPurity().getId());
		jsonObject.put("color\\.id", jobCompIssDt.getColor().getId());
		jsonObject.put("qty", jobCompIssDt.getQty());
		jsonObject.put("metalWt", jobCompIssDt.getMetalWt());
		jsonObject.put("rate", jobCompIssDt.getRate());
		jsonObject.put("amount", jobCompIssDt.getAmount());
		jsonObject.put("vJobCompIssMtId", jobCompIssDt.getJobIssMt().getId());
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/jobCompIssDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = jobCompIssDtService.jobCompIssDtDelete(id, principal);
		return retVal;
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	

	
}

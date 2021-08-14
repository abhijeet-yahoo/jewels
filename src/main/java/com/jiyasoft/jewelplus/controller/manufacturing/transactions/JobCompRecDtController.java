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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobCompRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobCompRecDtController {
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private IJobCompRecDtService jobCompRecDtService;
	
	@RequestMapping("/jobCompRecDt/listing")
	@ResponseBody
	public String jobCompRecDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			Principal principal,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg) {

		StringBuilder sb = new StringBuilder();
		
		JobRecMt jobRecMt = jobRecMtService.findOne(mtId);

		List<JobCompRecDt> jobCompRecDts= jobCompRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
		
		sb.append("{\"total\":").append(jobCompRecDts.size()).append(",\"rows\": [");
		
		for (JobCompRecDt jobCompRecDt : jobCompRecDts) {
			sb.append("{\"id\":\"")
					.append(jobCompRecDt.getId())
					.append("\",\"metal\":\"")
					.append((jobCompRecDt.getMetal() != null ? jobCompRecDt.getMetal().getName() : ""))
					.append("\",\"component\":\"")
					.append((jobCompRecDt.getComponent() != null ? jobCompRecDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((jobCompRecDt.getPurity() != null ? jobCompRecDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((jobCompRecDt.getColor() != null ? jobCompRecDt.getColor().getName() : ""))
					.append("\",\"department\":\"")
					.append((jobCompRecDt.getDepartment() != null ? jobCompRecDt.getDepartment().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(jobCompRecDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(jobCompRecDt.getRate())
					.append("\",\"amount\":\"")
					.append(jobCompRecDt.getAmount())
					.append("\",\"qty\":\"")
					.append(jobCompRecDt.getQty())
					.append("\",\"deactive\":\"")
					.append(jobCompRecDt.getDeactive() ? "Yes" : "No");
						
			if(!disableFlg) {
					sb.append("\",\"action1\":\"")
					
					.append("<a href='javascript:editJobCompRecDt(").append(jobCompRecDt.getId())
					.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteJobCompRecDt(event,")
					  .append(jobCompRecDt.getId()).append(", 0);' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(jobCompRecDt.getId())
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
	
	@RequestMapping(value = "/jobCompRecDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("jobCompRecDt") JobCompRecDt jobCompRecDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vJobCompRecMtId", required = true) Integer vJobCompRecMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";
		
			retVal= jobCompRecDtService.jobCompRecDtSave(jobCompRecDt, id, vJobCompRecMtId, principal); 
	
		return retVal;			
		}


	@ResponseBody
	@RequestMapping(value = "/jobCompRecDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		JobCompRecDt jobCompRecDt = jobCompRecDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalJobCompRecDtId", jobCompRecDt.getId());
		jsonObject.put("department\\.id", jobCompRecDt.getDepartment().getId());
		jsonObject.put("metal\\.id", jobCompRecDt.getMetal().getId());
		jsonObject.put("component\\.id", jobCompRecDt.getComponent().getId());
		jsonObject.put("purity\\.id", jobCompRecDt.getPurity().getId());
		jsonObject.put("color\\.id", jobCompRecDt.getColor().getId());
		jsonObject.put("qty", jobCompRecDt.getQty());
		jsonObject.put("metalWt", jobCompRecDt.getMetalWt());
		jsonObject.put("rate", jobCompRecDt.getRate());
		jsonObject.put("amount", jobCompRecDt.getAmount());
		jsonObject.put("vJobCompRecMtId", jobCompRecDt.getJobRecMt().getId());
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/jobCompRecDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = jobCompRecDtService.jobCompRecDtDelete(id, principal);
		return retVal;
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	


}

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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlRecDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobMtlRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobMtlRecDtController {

	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private IJobMtlRecDtService jobMtlRecDtService;

	
	@RequestMapping("/jobMtlRecDt/listing")
	@ResponseBody
	public String jobMtlIssDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			Principal principal,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg) {

		StringBuilder sb = new StringBuilder();
		
		
		List<JobMtlRecDt> jobMtlRecDts=jobMtlRecDtService.findByJobRecMtAndDeactive(jobRecMtService.findOne(mtId), false);
		sb.append("{\"total\":").append(jobMtlRecDts.size()).append(",\"rows\": [");
		
		for (JobMtlRecDt jobMtlRecDt : jobMtlRecDts) {
			sb.append("{\"id\":\"")
					.append(jobMtlRecDt.getId())
					.append("\",\"department\":\"")
					.append((jobMtlRecDt.getDepartment() != null ? jobMtlRecDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((jobMtlRecDt.getMetal() != null ? jobMtlRecDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((jobMtlRecDt.getPurity() != null ? jobMtlRecDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((jobMtlRecDt.getColor() != null ? jobMtlRecDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(jobMtlRecDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(jobMtlRecDt.getRate())
					.append("\",\"amount\":\"")
					.append(jobMtlRecDt.getAmount())
					.append("\",\"deactive\":\"")
					.append(jobMtlRecDt.getDeactive() ? "Yes" : "No")
					.append("\",\"in999\":\"")
					.append(jobMtlRecDt.getIn999())
					.append("\",\"remark\":\"")
					.append((jobMtlRecDt.getRemark() != null ? jobMtlRecDt.getRemark() : ""));
					
			if(!disableFlg) {
			sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editJobMtlRecDt(")
					.append(jobMtlRecDt.getId());
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					sb.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteJobMtlRecDt(event,")
							.append(jobMtlRecDt.getId()).append(", 0);' href='javascript:void(0);'");
					
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(jobMtlRecDt.getId())
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
	
	@RequestMapping(value = "/jobMtlRecDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("jobMtlRecDt") JobMtlRecDt jobMtlRecDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vJobRecMtId", required = true) Integer vJobRecMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";
		
			retVal= jobMtlRecDtService.jobMtlRecDtSave(jobMtlRecDt, id, vJobRecMtId,  principal);
	
		return retVal;			
		}

	
	@ResponseBody
	@RequestMapping(value = "/jobMtlRecDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		JobMtlRecDt jobMtlRecDt = jobMtlRecDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalJobMtlRecDtId", jobMtlRecDt.getId());
		jsonObject.put("department\\.id", jobMtlRecDt.getDepartment().getId());
		jsonObject.put("metal\\.id", jobMtlRecDt.getMetal().getId());
		jsonObject.put("purity\\.id", jobMtlRecDt.getPurity().getId());
		jsonObject.put("color\\.id", jobMtlRecDt.getColor().getId());
		jsonObject.put("metalWt", jobMtlRecDt.getMetalWt());
		jsonObject.put("rate", jobMtlRecDt.getRate());
		jsonObject.put("amount", jobMtlRecDt.getAmount());
		jsonObject.put("in9991", jobMtlRecDt.getIn999());
		jsonObject.put("remark", jobMtlRecDt.getRemark());
		jsonObject.put("vJobRecMtId", jobMtlRecDt.getJobRecMt().getId());
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/jobMtlRecDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = jobMtlRecDtService.jobMtlRecDtDelete(id, principal);
	
			
		return retVal;
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}

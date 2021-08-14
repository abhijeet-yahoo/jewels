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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlIssDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobMtlIssDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobMtlssDtController {
	
	@Autowired
	private IJobIssMtService jobIssMtService;
	
	@Autowired
	private IJobMtlIssDtService jobMtlIssDtService;

	
	@RequestMapping("/jobMtlIssDt/listing")
	@ResponseBody
	public String jobMtlIssDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		
		List<JobMtlIssDt> jobMtlIssDts=jobMtlIssDtService.findByJobIssMtAndDeactive(jobIssMtService.findOne(mtId), false);
		sb.append("{\"total\":").append(jobMtlIssDts.size()).append(",\"rows\": [");
		
		for (JobMtlIssDt jobMtlIssDt : jobMtlIssDts) {
			sb.append("{\"id\":\"")
					.append(jobMtlIssDt.getId())
					.append("\",\"department\":\"")
					.append((jobMtlIssDt.getDepartment() != null ? jobMtlIssDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((jobMtlIssDt.getMetal() != null ? jobMtlIssDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((jobMtlIssDt.getPurity() != null ? jobMtlIssDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((jobMtlIssDt.getColor() != null ? jobMtlIssDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(jobMtlIssDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(jobMtlIssDt.getRate())
					.append("\",\"amount\":\"")
					.append(jobMtlIssDt.getAmount())
					.append("\",\"deactive\":\"")
					.append(jobMtlIssDt.getDeactive() ? "Yes" : "No");
				
			if(!disableFlg) {
				sb.append("\",\"action1\":\"")
				.append("<a href='javascript:editJobMtlIssDt(")
				.append(jobMtlIssDt.getId());
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				sb.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteJobMtlIssDt(event,")
						.append(jobMtlIssDt.getId()).append(", 0);' href='javascript:void(0);'");
				
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
				 .append(jobMtlIssDt.getId())
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
	
	@RequestMapping(value = "/jobMtlIssDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("jobMtlIssDt") JobMtlIssDt jobMtlIssDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vJobIssMtPkId", required = true) Integer vJobIssMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";
		
			retVal= jobMtlIssDtService.jobMtlIssDtSave(jobMtlIssDt, id, vJobIssMtId,  principal);
	
		return retVal;			
		}

	
	@ResponseBody
	@RequestMapping(value = "/jobMtlIssDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		JobMtlIssDt jobMtlIssDt = jobMtlIssDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalJobMtlIssDtId", jobMtlIssDt.getId());
		jsonObject.put("department\\.id", jobMtlIssDt.getDepartment().getId());
		jsonObject.put("metal\\.id", jobMtlIssDt.getMetal().getId());
		jsonObject.put("purity\\.id", jobMtlIssDt.getPurity().getId());
		jsonObject.put("color\\.id", jobMtlIssDt.getColor().getId());
		jsonObject.put("metalWt", jobMtlIssDt.getMetalWt());
		jsonObject.put("rate", jobMtlIssDt.getRate());
		jsonObject.put("amount", jobMtlIssDt.getAmount());
		jsonObject.put("vJobIssMtId", jobMtlIssDt.getJobIssMt().getId());
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/jobMtlIssDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = jobMtlIssDtService.jobMtlIssDtDelete(id, principal);
	
			
		return retVal;
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}

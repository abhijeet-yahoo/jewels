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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnRecDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobStnRecDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobStnRecDtController {

	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private IJobStnRecDtService jobStnRecDtService;
	
	
	@RequestMapping("/jobStnRecDt/listing")
	@ResponseBody
	public String jobRecIssDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
		Principal principal,
		@RequestParam(value = "disableFlg", required = false) Boolean disableFlg)
			 {
		
	
		StringBuilder sb = new StringBuilder();
		JobRecMt jobRecMt = jobRecMtService.findOne(mtId);
		
		List<JobStnRecDt> jobStnRecDts = jobStnRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
		
		sb.append("{\"total\":").append(jobStnRecDts.size()).append(",\"rows\": [");
			
		if(jobStnRecDts.size() > 0){
				for (JobStnRecDt jobStnRecDt : jobStnRecDts) {
					sb.append("{\"id\":\"")
							.append(jobStnRecDt.getId())
							.append("\",\"stoneType\":\"")
							.append((jobStnRecDt.getStoneType() != null ? jobStnRecDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((jobStnRecDt.getShape() != null ? jobStnRecDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((jobStnRecDt.getSubShape() != null ? jobStnRecDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((jobStnRecDt.getQuality() != null ? jobStnRecDt.getQuality().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((jobStnRecDt.getSize() != null ? jobStnRecDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(jobStnRecDt.getSieve())	
							.append("\",\"department\":\"")
							.append((jobStnRecDt.getDepartment() != null ? jobStnRecDt.getDepartment().getName() : ""))					
							.append("\",\"sizeGroupStr\":\"")
							.append((jobStnRecDt.getSizeGroup() != null ? jobStnRecDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(jobStnRecDt.getStone())
							.append("\",\"balStone\":\"")
							.append(jobStnRecDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(jobStnRecDt.getCarat())
							.append("\",\"diffCarat\":\"")
							.append(jobStnRecDt.getDiffCarat())
							.append("\",\"balCarat\":\"")
							.append(jobStnRecDt.getBalCarat())
							.append("\",\"rate\":\"")
							.append(jobStnRecDt.getRate())
							.append("\",\"amount\":\"")
							.append(jobStnRecDt.getAmount())	
							.append("\",\"packetNo\":\"")
							.append(jobStnRecDt.getPacketNo() !=null ? jobStnRecDt.getPacketNo() : ""  )	
							.append("\",\"remark\":\"")
							.append((jobStnRecDt.getRemark() != null ? jobStnRecDt.getRemark() : ""));
						
						if(!disableFlg){
							sb.append("\",\"action1\":\"");
							sb.append("<a href='javascript:editJobStnRecDt(")
							.append(jobStnRecDt.getId());
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
							sb.append("\",\"action2\":\"");
							sb.append("<a href='javascript:deleteJobStnRecDt(event,")
							  .append(jobStnRecDt.getId()).append(", 0);' href='javascript:void(0);'");
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(jobStnRecDt.getId())
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
				
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	@RequestMapping(value = "/jobStnRecDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("jobStnRecDt") JobStnRecDt jobStnRecDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vJobStnRecMtId", required = true) Integer vJobStnRecMtId,
			RedirectAttributes redirectAttributes, Principal principal) {
	
		String retVal = "-1";
		
			retVal= jobStnRecDtService.jobStnRecDtSave(jobStnRecDt, id, vJobStnRecMtId, principal); 
					
	
		return retVal;			
		}
	
	
	@ResponseBody
	@RequestMapping(value = "/jobStnRecDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		JobStnRecDt jobStnRecDt = jobStnRecDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalJobStnRecDtId", jobStnRecDt.getId());
		jsonObject.put("department\\.id", jobStnRecDt.getDepartment().getId());
		jsonObject.put("stoneType\\.id", jobStnRecDt.getStoneType().getId());
		jsonObject.put("shape\\.id", jobStnRecDt.getShape().getId());
		jsonObject.put("subShape\\.id", jobStnRecDt.getSubShape() != null ? jobStnRecDt.getSubShape().getId() : "");
		jsonObject.put("quality", jobStnRecDt.getQuality().getId());
		jsonObject.put("size", jobStnRecDt.getSize());
		jsonObject.put("vSieve", jobStnRecDt.getSieve() != null ? jobStnRecDt.getSieve() :"");
		jsonObject.put("vSizeGroupStr", jobStnRecDt.getSizeGroup() !=null ?  jobStnRecDt.getSizeGroup().getName() : "");
		jsonObject.put("stone", jobStnRecDt.getStone());
		jsonObject.put("carat", jobStnRecDt.getCarat());
		jsonObject.put("diffCarat", jobStnRecDt.getDiffCarat());
		jsonObject.put("rate", jobStnRecDt.getRate());
		jsonObject.put("amount", jobStnRecDt.getAmount());
		jsonObject.put("packetNo", jobStnRecDt.getPacketNo());
		jsonObject.put("remark", jobStnRecDt.getRemark());
		jsonObject.put("vJobStnRecMtId", jobStnRecDt.getJobRecMt().getId());
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/jobStnRecDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = jobStnRecDtService.jobStnRecDtDelete(id, principal);
		return retVal;
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	
}

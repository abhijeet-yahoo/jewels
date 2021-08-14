package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnIssDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobStnIssDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobStnIssDtController {

	@Autowired
	private IJobIssMtService jobIssMtService;
	
	@Autowired
	private IJobStnIssDtService jobStnIssDtService;
	
	@Value("${jobworkDiaStk}")
	private String diamondStockType;
	
	@Value("${allowNegativeDiamond}")
	private Boolean allowNegativeDiamond;

	
	@RequestMapping("/jobStnIssDt/listing")
	@ResponseBody
	public String jobMtlIssDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg,
		Principal principal)
			 {
		
	
		StringBuilder sb = new StringBuilder();
		JobIssMt jobIssMt = jobIssMtService.findOne(mtId);
		
		List<JobStnIssDt> jobStnIssDts = jobStnIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
		
		sb.append("{\"total\":").append(jobStnIssDts.size()).append(",\"rows\": [");
			
		if(jobStnIssDts.size() > 0){
				for (JobStnIssDt jobStnIssDt : jobStnIssDts) {
					sb.append("{\"id\":\"")
							.append(jobStnIssDt.getId())
							.append("\",\"stoneType\":\"")
							.append((jobStnIssDt.getStoneType() != null ? jobStnIssDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((jobStnIssDt.getShape() != null ? jobStnIssDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((jobStnIssDt.getSubShape() != null ? jobStnIssDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((jobStnIssDt.getQuality() != null ? jobStnIssDt.getQuality().getName() : ""))					
							.append("\",\"department\":\"")
							.append((jobStnIssDt.getDepartment() != null ? jobStnIssDt.getDepartment().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((jobStnIssDt.getSize() != null ? jobStnIssDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(jobStnIssDt.getSieve())	
							.append("\",\"sizeGroupStr\":\"")
							.append((jobStnIssDt.getSizeGroup() != null ? jobStnIssDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(jobStnIssDt.getStone())
							.append("\",\"balStone\":\"")
							.append(jobStnIssDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(jobStnIssDt.getCarat())
							.append("\",\"diffCarat\":\"")
							.append(jobStnIssDt.getDiffCarat())
							.append("\",\"balCarat\":\"")
							.append(jobStnIssDt.getBalCarat())
							.append("\",\"rate\":\"")
							.append(jobStnIssDt.getStnRate())
							.append("\",\"amount\":\"")
							.append(jobStnIssDt.getStnAmount())	
							.append("\",\"packetNo\":\"")
							.append(jobStnIssDt.getPacketNo() !=null ? jobStnIssDt.getPacketNo() : ""  )	
							.append("\",\"remark\":\"")
							.append((jobStnIssDt.getRemark() != null ? jobStnIssDt.getRemark() : ""));
						
					if(!disableFlg) {
						sb.append("\",\"action1\":\"");
						sb.append("<a href='javascript:editJobStnIssDt(").append(jobStnIssDt.getId());
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
						sb.append("\",\"action2\":\"");
						sb.append("<a href='javascript:deleteJobStnIssDt(event,")
						  .append(jobStnIssDt.getId()).append(", 0);' href='javascript:void(0);'");
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(jobStnIssDt.getId())
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
	
	@RequestMapping(value = "/jobStnIssDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("jobStnIssDt") JobStnIssDt jobStnIssDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vJobStnIssMtId", required = true) Integer vJobStnIssMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";
		
			retVal= jobStnIssDtService.saveJobStnIssDt(jobStnIssDt, id, vJobStnIssMtId, principal, diamondStockType, allowNegativeDiamond); 
					
	
		return retVal;			
		}
	
	
	@ResponseBody
	@RequestMapping(value = "/jobStnIssDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		JobStnIssDt jobStnIssDt = jobStnIssDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalJobStnIssDtId", jobStnIssDt.getId());
		jsonObject.put("department\\.id", jobStnIssDt.getDepartment().getId());
		jsonObject.put("stoneType\\.id", jobStnIssDt.getStoneType().getId());
		jsonObject.put("shape\\.id", jobStnIssDt.getShape().getId());
		jsonObject.put("subShape\\.id", jobStnIssDt.getSubShape() != null ? jobStnIssDt.getSubShape().getId() : "");
		jsonObject.put("quality", jobStnIssDt.getQuality().getId());
		jsonObject.put("size", jobStnIssDt.getSize());
		jsonObject.put("vSieve", jobStnIssDt.getSieve() != null ? jobStnIssDt.getSieve() :"");
		jsonObject.put("vSizeGroupStr", jobStnIssDt.getSizeGroup() !=null ?  jobStnIssDt.getSizeGroup().getName() : "");
		jsonObject.put("stone", jobStnIssDt.getStone());
		jsonObject.put("carat", jobStnIssDt.getCarat());
		jsonObject.put("diffCarat", jobStnIssDt.getDiffCarat());
		jsonObject.put("stnRate", jobStnIssDt.getStnRate());
		jsonObject.put("stnAmount", jobStnIssDt.getStnAmount());
		jsonObject.put("packetNo", jobStnIssDt.getPacketNo());
		jsonObject.put("remark", jobStnIssDt.getRemark());
		jsonObject.put("vJobStnIssMtId", jobStnIssDt.getJobIssMt().getId());
		
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/jobStnIssDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = jobStnIssDtService.jobStnIssDtDelete(id, principal);
		return retVal;
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}

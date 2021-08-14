package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssLabDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssLabDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobIssLabDtController {

	@Autowired
	private IJobIssDtService jobIssDtService;
	
	@Autowired
	private IJobIssLabDtService jobIssLabDtService;

	
	
	@RequestMapping("/jobIssLabDt/listing")
	@ResponseBody
	public String jobIssLabDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "jobIssDtId", required = false) Integer jobIssDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		
		JobIssDt jobIssDt = jobIssDtService.findOne(jobIssDtId);
		List<JobIssLabDt> jobIssLabDts = jobIssLabDtService.findByJobIssDtAndDeactive(jobIssDt, false);
		
		sb.append("{\"total\":").append(jobIssLabDts.size()).append(",\"rows\": [");
		
		
		if(jobIssLabDts.size() > 0){
			for(JobIssLabDt jobIssLabDt : jobIssLabDts){
				
			sb.append("{\"id\":\"")
				.append(jobIssLabDt.getId())
				.append("\",\"metal\":\"")
				.append((jobIssLabDt.getMetal() != null ? jobIssLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((jobIssLabDt.getLabourType() != null ? jobIssLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((jobIssLabDt.getLabourRate() != null ? jobIssLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((jobIssLabDt.getLabourValue() != null ? jobIssLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(jobIssLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(jobIssLabDt.getPcsWise())
				.append("\",\"perGram\":\"")
				.append(jobIssLabDt.getGramWise())
				.append("\",\"percent\":\"")
				.append(jobIssLabDt.getPercentWise())
				.append("\",\"perCaratRate\":\"")
				.append(jobIssLabDt.getPerCaratRate())
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLock(")
				.append(jobIssLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostLabDt(")
				.append(jobIssLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostLabDt(event,")
				.append(jobIssLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(jobIssLabDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
				
			}
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		

		return str;
		
	}
}

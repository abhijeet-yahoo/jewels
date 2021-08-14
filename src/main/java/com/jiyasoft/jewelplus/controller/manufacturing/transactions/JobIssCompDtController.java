package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMetalDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMetalDtService;

@RequestMapping("/jobIssStnDt/listing")
@ResponseBody
public class JobIssCompDtController {

	@Autowired
	private IJobIssCompDtService jobIssCompDtService;
	
	@Autowired
	private IJobIssDtService jobIssDtService;
	
	@Autowired
	private IJobIssMetalDtService jobIssMetalDtService;
	
	
	
	
	@RequestMapping("/jobIssCompDt/listing")
	@ResponseBody
	public String jobIssCompListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "jobIssDtId", required = false) Integer jobIssDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		
		
		JobIssDt jobIssDt = jobIssDtService.findOne(jobIssDtId);
		
			
		JobIssMetalDt jobIssMetalDt =  jobIssMetalDtService.findByJobIssDtAndDeactiveAndMainMetal(jobIssDt, false, true);
		String vMetalKtNm = jobIssMetalDt.getPurity().getName(); 
		
		List<JobIssCompDt> jobIssCompDts = jobIssCompDtService.findByJobIssDtAndDeactive(jobIssDt, false);
		
		sb.append("{\"total\":").append(jobIssCompDts.size()).append(",\"rows\": [");
		
		if(jobIssCompDts.size() > 0){
			for(JobIssCompDt jobIssCompDt : jobIssCompDts){
				
			sb.append("{\"id\":\"")
				.append(jobIssCompDt.getId())
				.append("\",\"compName\":\"")
				.append((jobIssCompDt.getComponent() != null ? jobIssCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((jobIssCompDt.getPurity() != null ? jobIssCompDt.getPurity().getName() : ""))
				.append("\",\"vMetalKtNm\":\"")
				.append((vMetalKtNm))
				.append("\",\"color\":\"")
				.append((jobIssCompDt.getColor() != null ? jobIssCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((jobIssCompDt.getMetalWt() != null ? jobIssCompDt.getMetalWt() : ""))
				.append("\",\"rate\":\"")
				.append((jobIssCompDt.getCompRate() != null ? jobIssCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((jobIssCompDt.getCompValue() != null ? jobIssCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((jobIssCompDt.getCompPcs() != null ? jobIssCompDt.getCompPcs() : ""))
				.append("\",\"rLock\":\"")
				.append((jobIssCompDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCompDtLockUnLock(")
				.append(jobIssCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editJobIssCompDt(")
				.append(jobIssCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteJobIssCompDt(event,")
				.append(jobIssCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(jobIssCompDt.getId())
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

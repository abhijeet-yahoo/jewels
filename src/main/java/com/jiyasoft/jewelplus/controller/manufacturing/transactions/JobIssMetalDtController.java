package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMetalDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMetalDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobIssMetalDtController {

	@Autowired
	private IJobIssMetalDtService jobIssMetalDtService;
	
	@Autowired
	private IJobIssDtService jobIssDtService;
	
	@RequestMapping("/jobIssMetalDt/listing")
	@ResponseBody
	public String jobIssMetalListing(Model model,
			
			@RequestParam(value = "jobIssDtId", required = false) Integer jobIssDtId,Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		JobIssDt jobIssDt = jobIssDtService.findOne(jobIssDtId);
		List<JobIssMetalDt> jobissIssMetalDts = jobIssMetalDtService.findByJobIssDtAndDeactive(jobIssDt, false);
		
		sb.append("{\"total\":").append(jobissIssMetalDts.size()).append(",\"rows\": [");
		
		for(JobIssMetalDt jobIssMetalDt : jobissIssMetalDts){
		
			sb.append("{\"id\":\"")
				.append(jobIssMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((jobIssMetalDt.getPurity() != null ? jobIssMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((jobIssMetalDt.getColor() != null ? jobIssMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((jobIssMetalDt.getPartNm() != null ? jobIssMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((jobIssMetalDt.getMetalPcs() != null ? jobIssMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((jobIssMetalDt.getMetalWeight() != null ? jobIssMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((jobIssMetalDt.getMetalRate() != null ? jobIssMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((jobIssMetalDt.getPerGramRate() != null ? jobIssMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((jobIssMetalDt.getLossPerc() != null ? jobIssMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((jobIssMetalDt.getMetalValue() != null ? jobIssMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(jobIssMetalDt.getMainMetal());
				
		
				 sb.append("\"},");
			
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
}

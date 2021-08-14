package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMetalDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMetalDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobRecMetalDtController {
	
	@Autowired
	private IJobRecMetalDtService jobRecMetalDtService;
	
	@Autowired
	private IJobRecDtService jobRecDtService;
	
	
	@RequestMapping("/jobRecMetalDt/listing")
	@ResponseBody
	public String jobRecMetalListing(Model model,
			
			@RequestParam(value = "jobRecDtId", required = false) Integer jobRecDtId,Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		JobRecDt jobRecDt = jobRecDtService.findOne(jobRecDtId);
		List<JobRecMetalDt> jobRecMetalDts = jobRecMetalDtService.findByJobRecDtAndDeactive(jobRecDt, false);
		
		sb.append("{\"total\":").append(jobRecMetalDts.size()).append(",\"rows\": [");
		
		for(JobRecMetalDt jobRecMetalDt : jobRecMetalDts){
		
			sb.append("{\"id\":\"")
				.append(jobRecMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((jobRecMetalDt.getPurity() != null ? jobRecMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((jobRecMetalDt.getColor() != null ? jobRecMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((jobRecMetalDt.getPartNm() != null ? jobRecMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((jobRecMetalDt.getMetalPcs() != null ? jobRecMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((jobRecMetalDt.getMetalWeight() != null ? jobRecMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((jobRecMetalDt.getMetalRate() != null ? jobRecMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((jobRecMetalDt.getPerGramRate() != null ? jobRecMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((jobRecMetalDt.getLossPerc() != null ? jobRecMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((jobRecMetalDt.getMetalValue() != null ? jobRecMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(jobRecMetalDt.getMainMetal());
				
		
				 sb.append("\"},");
			
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	

}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobIssDtController {
	
	@Autowired
	private IJobIssDtService jobIssDtService;
	
	@Autowired
	private IJobIssMtService jobIssMtService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	
	@RequestMapping("/jobIssDt/listing")
	@ResponseBody
	public String jobIssDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,Principal principal,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg) throws ParseException {
		
		return jobIssDtService.jobIssDtListing(limit, offset, sort, order, search, principal, pInvNo,disableFlg);
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/jobIssDt/saveEdit")
	public String addEditJobIssDt(
			@RequestParam(value="dtId", required =false) Integer dtId,
			@RequestParam(value="mtId", required =false) Integer mtId,
			@RequestParam(value="vProcessDtId", required =false) String vProcessDtId,
			Principal principal){

		String retVal="-1";
		
		try {
			
			JobIssDt jobIssDt = jobIssDtService.findOne(dtId);
			jobIssDt.setJobIssMt(jobIssMtService.findOne(mtId));
			jobIssDt.setProcess(vProcessDtId);
			jobIssDt.setModiBy(principal.getName());
			jobIssDt.setModiDate(new Date());
			jobIssDtService.save(jobIssDt);
			
			retVal ="1";
		} catch (Exception e) {
			retVal="-1";
		}
		
		return retVal;
	}
	
		
	@ResponseBody
	@RequestMapping(value = "/jobIssDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		JobIssDt jobIssDt = jobIssDtService.findOne(id);
		String processNm="";
		if(jobIssDt.getProcess() != null){
		String[] data1 = jobIssDt.getProcess().split(",");
		
		for(int i=0; i<data1.length; i++){
			LabourType labourType = labourTypeService.findOne(Integer.parseInt(data1[i]));
			if(processNm == ""){
				processNm += labourType.getName();
				
			}else {
				processNm += ","+labourType.getName();
			
			}
		}
			
	}	
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalJobIssDtId", jobIssDt.getId());
		jsonObject.put("processDtTextBox", processNm);
		jsonObject.put("vJobIssMtId", jobIssDt.getJobIssMt().getId());
	//	jsonObject.put("vProcessDtId", processId);
		
		
		return jsonObject.toString();
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/jobIssDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = jobIssDtService.deleteJobIssDt(id);
		
		return retVal;
		
	}
		

}

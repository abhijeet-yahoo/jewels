package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobIssBagTransferController {
	
	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IJobIssMtService jobIssMtService;
	
	@ModelAttribute("jobIssMt")
	public JobIssMt construct() {
		return new JobIssMt();
	}

	@ModelAttribute("tranMt")
	public TranMt constructTranMt() {
		return new TranMt();
	}

	@RequestMapping("/jobIssBagTransfer")
	public String jobIssBagTransfer(Model model) {
		return "jobIssBagTransfer";
	}
	
	@RequestMapping("/jobIssBagTransfer/listing")
	@ResponseBody
	public String costingMtTransferListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			@RequestParam(value = "invDt", required = false) String invDt) throws ParseException {

		return tranMtService.getBagsForJobIssMt(limit, offset, sort, order, search, deptId, invDt);
	}
	
	@RequestMapping(value = "/transfer/jobIssDt", method = RequestMethod.POST)
	@ResponseBody
	public String addEditCastingDt(
			@Valid @ModelAttribute("tranMt") TranMt tranMt,
			BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "pODIds") String pOIds, 
			@RequestParam(value = "jobIssMtId") Integer jobIssMtId,
			@RequestParam(value = "vProcessId", required = false) String vProcessId,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "jobIssBagTransfer";
		}

		
		if(pOIds.length() == 0){
			retVal = "-1";
		}else{
		
			retVal = jobIssMtService.addBagInJobIss(pOIds, jobIssMtId,principal,vProcessId);	
				
		} // ending the main if
		

		return retVal;

	}
	
	
}

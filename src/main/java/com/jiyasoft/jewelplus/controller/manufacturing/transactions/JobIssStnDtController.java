package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssStnDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobIssStnDtController {

	@Autowired
	private IJobIssStnDtService jobIssStnDtService;
	
	@Autowired
	private IJobIssDtService jobIssDtService;
	

	
/*	@Autowired
	private ISettingChargeService settingChargeService;*/
	

	@RequestMapping("/jobIssStnDt/listing")
	@ResponseBody
	public String jobIssStnListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "jobIssDtId", required = false) Integer jobIssDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		
		
		JobIssDt jobIssDt = jobIssDtService.findOne(jobIssDtId);
		List<JobIssStnDt> jobIssStnDts = jobIssStnDtService.findByJobIssDtAndDeactive(jobIssDt, false);
		
		sb.append("{\"total\":").append(jobIssStnDts.size()).append(",\"rows\": [");
		
		if(jobIssStnDts.size() > 0){
			for(JobIssStnDt jobIssStnDt:jobIssStnDts){
				
			sb.append("{\"id\":\"")
				.append(jobIssStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((jobIssStnDt.getStoneType() != null ? jobIssStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((jobIssStnDt.getPartNm() != null ? jobIssStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((jobIssStnDt.getShape() != null ? jobIssStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((jobIssStnDt.getQuality() != null ? jobIssStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((jobIssStnDt.getSize() != null ? jobIssStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((jobIssStnDt.getSieve() != null ? jobIssStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((jobIssStnDt.getSizeGroup() != null ? jobIssStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((jobIssStnDt.getStone() != null ? jobIssStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((jobIssStnDt.getCarat() != null ? jobIssStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((jobIssStnDt.getStnRate() != null ? jobIssStnDt.getStnRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((jobIssStnDt.getStoneValue() != null ? jobIssStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((jobIssStnDt.getHandlingRate() != null ? jobIssStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((jobIssStnDt.getHandlingValue() != null ? jobIssStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((jobIssStnDt.getSetting() != null ? jobIssStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((jobIssStnDt.getSettingType() != null ? jobIssStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((jobIssStnDt.getSetRate() != null ? jobIssStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((jobIssStnDt.getSetValue() != null ? jobIssStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(jobIssStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((jobIssStnDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(jobIssStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editjobIssStnDt(")
				.append(jobIssStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteJobIssStnDt(event,")
				.append(jobIssStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(jobIssStnDt.getId())
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

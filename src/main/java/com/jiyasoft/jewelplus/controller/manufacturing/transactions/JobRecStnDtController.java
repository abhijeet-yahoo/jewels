package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecStnDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecStnDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobRecStnDtController {

	@Autowired
	private IJobRecStnDtService jobRecStnDtService;
	
	@Autowired
	private IJobRecDtService jobRecDtService;
	
	@Autowired
	private IJobRecMetalDtService jobRecMetalDtService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	
	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;

	@Autowired
	private ISubShapeService subShapeService;

	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private ISettingService settingService;

	@Autowired
	private ISettingTypeService settingTypeService;
	
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;

	
	@ModelAttribute("jobRecStnDt")
	public JobRecStnDt construct() {
		return new JobRecStnDt();
	}
		

	@RequestMapping("/jobRecStnDt/listing")
	@ResponseBody
	public String jobRecStnListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "jobRecDtId", required = false) Integer jobRecDtId,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg) {

		StringBuilder sb = new StringBuilder();
		
		
		
		
		
		JobRecDt jobRecDt = jobRecDtService.findOne(jobRecDtId);
		List<JobRecStnDt> jobRecStnDts = jobRecStnDtService.findByJobRecDtAndDeactive(jobRecDt, false);
		
		sb.append("{\"total\":").append(jobRecStnDts.size()).append(",\"rows\": [");
		
		if(jobRecStnDts.size() > 0){
			for(JobRecStnDt jobRecStnDt:jobRecStnDts){
				
			sb.append("{\"id\":\"")
				.append(jobRecStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((jobRecStnDt.getStoneType() != null ? jobRecStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((jobRecStnDt.getPartNm() != null ? jobRecStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((jobRecStnDt.getShape() != null ? jobRecStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((jobRecStnDt.getQuality() != null ? jobRecStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((jobRecStnDt.getSize() != null ? jobRecStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((jobRecStnDt.getSieve() != null ? jobRecStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((jobRecStnDt.getSizeGroup() != null ? jobRecStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((jobRecStnDt.getStone() != null ? jobRecStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((jobRecStnDt.getCarat() != null ? jobRecStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((jobRecStnDt.getStnRate() != null ? jobRecStnDt.getStnRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((jobRecStnDt.getStoneValue() != null ? jobRecStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((jobRecStnDt.getHandlingRate() != null ? jobRecStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((jobRecStnDt.getHandlingValue() != null ? jobRecStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((jobRecStnDt.getSetting() != null ? jobRecStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((jobRecStnDt.getSettingType() != null ? jobRecStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((jobRecStnDt.getSetRate() != null ? jobRecStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((jobRecStnDt.getSetValue() != null ? jobRecStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(jobRecStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((jobRecStnDt.getrLock())); //1 = lock & 0 = unlock
				
			if(!disableFlg) {
			sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(jobRecStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editjobRecStnDt(")
				.append(jobRecStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteJobRecStnDt(event,")
				.append(jobRecStnDt.getId()+","+(jobRecStnDt.getManualFlg()!=null ?jobRecStnDt.getManualFlg():false))
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(jobRecStnDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
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
	
	
	@RequestMapping("/jobRecStnDt/add")
	public String add(Model model,@RequestParam(value = "jobDtId") Integer jobDtId) {
		
		JobRecDt jobRecDt = jobRecDtService.findOne(jobDtId);
		
		if(jobRecDt != null){
			List<JobRecMetalDt> jobRecMetalDts = jobRecMetalDtService.findByJobRecDtAndDeactive(jobRecDt, false);
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(JobRecMetalDt jobRecMetalDt:jobRecMetalDts){
				map.put(jobRecMetalDt.getPartNm().getId(), jobRecMetalDt.getPartNm().getFieldValue());
			}
			model.addAttribute("lookUpMastStoneMap", map);
			
		}
		
		model.addAttribute("partMap", lookUpMastService.getActiveLookUpMastByType("Design Part"));
		
		model = populateModel(model);

		return "jobRecStnDt/add";
	}
	
	
	private Model populateModel(Model model) {
		
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("settingMap", settingService.getSettingList(0));
		model.addAttribute("settingTypeMap", settingTypeService.getSettingTypeList());

		
		return model;
	}
	
	
	@RequestMapping(value = "/jobRecStnDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditStnDt(@Valid 
			@ModelAttribute("jobRecStnDt") JobRecStnDt jobRecStnDt,
			BindingResult result,
			@RequestParam("id") Integer id,
			@RequestParam("pJobRecMtId") Integer jobRecMtId,
			@RequestParam("pJobRecDtId") Integer jobRecDtId,
			@RequestParam("pSieve") String pSieve,
			@RequestParam("pSizeGroup") String pSizeGroup,
			Principal principal) {

		
		String retVal = "";

		if (result.hasErrors()) {
			return "jobRecStnDt/add";
		}
		
		try {
			retVal = jobRecStnDtService.transactionalSave(jobRecStnDt, id, jobRecMtId, jobRecDtId, pSieve, pSizeGroup, principal,netWtWithCompFlg);
		} catch (Exception e) {
			retVal = "error";
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/jobRecStnDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal){
		
		JobRecStnDt jobRecStnDt = jobRecStnDtService.findOne(id);
		String retVal = jobRecStnDtService.deleteJobRecStnDt(id, principal);
		
		jobRecDtService.updateGrossWt(jobRecStnDt.getJobRecDt(), netWtWithCompFlg);
		
		return retVal;
		
	}
	
}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.json.JSONObject;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QJobRecMetalDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/transactions")
@Controller
public class JobRecDtController {

	@Autowired
	private IJobRecDtService jobRecDtService;
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IJobRecLabDtService jobRecLabDtService;
	
	@Autowired
	private IJobRecCompDtService jobRecCompDtService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@RequestMapping("/jobRecDt/listing")
	@ResponseBody
	public String jobRecDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,Principal principal,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg) throws ParseException {
		
		return jobRecDtService.jobRecDtListing(limit, offset, sort, order, search, principal, pInvNo,disableFlg);
		
	}
	
	@RequestMapping(value = "/jobIssDt/saveJobRecPickup", method = RequestMethod.POST)
	@ResponseBody
	public String addEditCastingDt(
			
			@RequestParam(value = "data") String data, 
			@RequestParam(value = "pMtid") Integer pMtid,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		
		String retVal = "-1";
		
		if(data.length() == 0){
			retVal = "-1";
		}else{
		
			retVal = jobRecDtService.addBagInJobRecDt(pMtid, data, principal);
				
		} // ending the main if
		

		return retVal;

	}
	
	@ResponseBody
	@RequestMapping("/jobRecDt/saveEdit")
	public String addEditJobRecDt(
			@RequestParam(value="dtId", required =false) Integer dtId,
			@RequestParam(value="mtId", required =false) Integer mtId,
			@RequestParam(value="vProcessRecDtId", required =false) String vProcessDtId,
			Principal principal){

		String retVal="-1";
		
		try {
			
			JobRecDt jobRecDt = jobRecDtService.findOne(dtId);
			jobRecDt.setJobRecMt(jobRecMtService.findOne(mtId));
			jobRecDt.setProcess(vProcessDtId);
			jobRecDt.setModiBy(principal.getName());
			jobRecDt.setModiDate(new Date());
			jobRecDtService.save(jobRecDt);
			
			retVal ="1";
		} catch (Exception e) {
			retVal="-1";
		}
		
		return retVal;
	}
	
		
	@ResponseBody
	@RequestMapping(value = "/jobRecDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		JobRecDt jobRecDt = jobRecDtService.findOne(id);
		String processNm="";
		if(jobRecDt.getProcess() != null){
		String[] data1 = jobRecDt.getProcess().split(",");
		
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
		jsonObject.put("modalJobRecDtId", jobRecDt.getId());
		jsonObject.put("processRecDtTextBox", processNm);
		jsonObject.put("vJobRecMtId", jobRecDt.getJobRecMt().getId());
		
		return jsonObject.toString();
	}
		

	
	@ResponseBody
	@RequestMapping("/jobRecDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = jobRecDtService.deleteJobRecDt(id);
		
		
		
		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/jobRecBagDt/updateGrossWt")
	public String updateGrossWt(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "grossWt", required = false) Double grossWt,Principal principal) {
		
		String retVal="-1";
		retVal=jobRecDtService.updateDtGrossWtAndMetalDetails(principal, id, grossWt,netWtWithCompFlg);
		
		
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/jobRecDt/getData/{dtId}")
	public String getData(@PathVariable int dtId) {

		JobRecDt jobRecDt = jobRecDtService.findOne(dtId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("grossWt", jobRecDt.getGrossWt());
		jsonObject.put("netWt", jobRecDt.getNetWt());
		jsonObject.put("metalValue", jobRecDt.getMetalValue());
		jsonObject.put("stoneValue", jobRecDt.getStoneValue());
		jsonObject.put("compValue", jobRecDt.getCompValue());
		jsonObject.put("labourValue", jobRecDt.getLabValue());
		jsonObject.put("setValue", jobRecDt.getSetValue());
		jsonObject.put("handlingCost", jobRecDt.getHdlgValue());
		jsonObject.put("fob", jobRecDt.getFob());
		jsonObject.put("other", jobRecDt.getOther());
		jsonObject.put("finalPrice", jobRecDt.getFinalPrice());
		jsonObject.put("discAmount", jobRecDt.getDiscAmount());
		

		return jsonObject.toString();

	}
	
	@RequestMapping("/jobRecDt/applyHallmarkingRate")
	@ResponseBody
	public String applyHallmarkingRate(@RequestParam(value = "dtIds", required = true) String dtIds,
			@RequestParam(value = "labType", required = true) String labType,
			Principal principal)  {
		
	 
		return jobRecDtService.applyLabRate(dtIds, labType, principal);
	}
	
	
	@RequestMapping("/jobRecLabDt/listing")
	@ResponseBody
	public String jobRecLabDtList(@RequestParam(value = "jobRecDtId", required = false) Integer jobRecDtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return jobRecLabDtService.jobRecLabDtListing(jobRecDtId, disableFlg);
	
	}
	
	
	@RequestMapping("/jobRecCompDt/listing")
	@ResponseBody
	public String jobRecCompDtList(@RequestParam(value = "jobRecDtId", required = false) Integer jobRecDtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return jobRecCompDtService.jobRecCompDtListing(jobRecDtId, disableFlg);
	
	}
	
	
	@RequestMapping("/jobRecLabDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "labDtId")Integer labDtId){
		
		String retVal = "";
		
		JobRecLabDt jobRecLabDt = jobRecLabDtService.findOne(labDtId);
	
		if(jobRecLabDt.getrLock() == true){
			retVal = "-1";
		}
		return retVal;
	}
	
	
	@RequestMapping("/jobRecLabDt/edit/{id}")
	public String editJobRecLabDt(@PathVariable int id,Model model){
		
		if(id>0){
			JobRecLabDt jobRecLabDt = jobRecLabDtService.findOne(id);
			model.addAttribute("jobRecLabDt",jobRecLabDt);
			model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
			model.addAttribute("metalMap", metalService.getMetalList());	
		}else{
			JobRecLabDt jobRecLabDt = new JobRecLabDt();
			model.addAttribute("jobRecLabDt",jobRecLabDt);
			model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
			model.addAttribute("metalMap", metalService.getMetalList());
			
		}
		
		return "jobRecLabDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/jobRecLabDt/add", method = RequestMethod.POST)
	public String addEditJobRecLabDt(@Valid @ModelAttribute("jobRecLabDt") JobRecLabDt jobRecLabDt,
			BindingResult bindingResult,
			@RequestParam(value="id", required=false)Integer id,
			@RequestParam(value="forLabJobRecMtId", required=false)Integer forLabJobRecMtId,
			@RequestParam(value="forLabJobRecDtId", required=false)Integer forLabJobRecDtId,
			RedirectAttributes redirectAttributes,Principal principal){
		
		
		String retVal = "-1";
		
		if(bindingResult.hasErrors()){
			return "xyz";
		}
		
		try {
			
		retVal=jobRecLabDtService.jobRecLabDtSave(jobRecLabDt, id,forLabJobRecMtId, forLabJobRecDtId, principal);
			
		}  catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		
		
		return retVal;

		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("jobRecLabDt/labRateFromMaster")
	public String labRateFromMaster(
			@RequestParam(value="metalId") Integer metalId,
			@RequestParam(value="labourId") Integer labourId,
			@RequestParam(value="jobRecLabDtId") Integer jobRecLabDtId,
			@RequestParam(value="jobRecDtId") Integer jobRecDtId){
		
		
		
		JobRecDt jobRecDt = jobRecDtService.findOne(jobRecDtId);
		
		Metal metal=metalService.findOne(metalId);
		LabourType labourType =labourTypeService.findOne(labourId);
			

		
		QJobRecMetalDt qJobRecMetalDt = QJobRecMetalDt.jobRecMetalDt;
		JPAQuery query=new JPAQuery(entityManager);
		
		List<Tuple> jobRecMetalList=null;
		
		jobRecMetalList = query.from(qJobRecMetalDt).
				where(qJobRecMetalDt.jobRecDt.id.eq(jobRecDtId).and(qJobRecMetalDt.purity.metal.eq(metal)))
				.groupBy(qJobRecMetalDt.purity.metal).list(qJobRecMetalDt.purity,qJobRecMetalDt.purity.metal.name,qJobRecMetalDt.metalWeight.sum(),qJobRecMetalDt.metalPcs.sum());
		
		List<LabourCharge> labourCharges=null;
		
		for(Tuple tuple : jobRecMetalList){
			
			Double vMetalWt=Math.round((tuple.get(qJobRecMetalDt.metalWeight.sum())/jobRecDt.getPcs())*1000.0)/1000.0;
			
			
			
			
			 labourCharges =labourChargeService.getPurityLabourRates(jobRecDt.getJobRecMt().getParty(), jobRecDt.getDesign().getCategory(),
					vMetalWt,false, metal,labourType,tuple.get(qJobRecMetalDt.purity));
			 
			 if(labourCharges.size()<=0){
				 
				 labourCharges =labourChargeService.getLabourRates(jobRecDt.getJobRecMt().getParty(), jobRecDt.getDesign().getCategory(),
							vMetalWt,false, metal,labourType);
				 
			 }
			
		}
		
		if(labourCharges !=null && labourCharges.size()>0){
			LabourCharge labourCharge = labourCharges.get(0);
			
			JobRecLabDt jobRecLabDtItem=null;
			
			if(jobRecLabDtId==null){
				jobRecLabDtItem = new JobRecLabDt();
				
				jobRecLabDtItem.setMetal(metal);
				jobRecLabDtItem.setLabourType(labourType);
				
				jobRecLabDtItem.setLabourRate(labourCharge.getRate());

				if(labourCharge.getPerPcsRate() == true){
					jobRecLabDtItem.setPcsWise(true);;
				}else if(labourCharge.getPerGramRate() == true){
					jobRecLabDtItem.setPerGramRate(true);
				}else if(labourCharge.getPercentage() == true){
					jobRecLabDtItem.setPercentWise(true);;
				}
				else if(labourCharge.getPerCaratRate() == true){
					jobRecLabDtItem.setPerCaratRate(true);
				}
				
				
				
			}else{
				
				jobRecLabDtItem = jobRecLabDtService.findOne(jobRecLabDtId);
				jobRecLabDtItem.setMetal(metal);
				jobRecLabDtItem.setLabourType(labourType);
				
				jobRecLabDtItem.setLabourRate(labourCharge.getRate());
				jobRecLabDtItem.setPcsWise(false);
				jobRecLabDtItem.setPerGramRate(false);
				jobRecLabDtItem.setPercentWise(false);
				jobRecLabDtItem.setPerCaratRate(false);


				if(labourCharge.getPerPcsRate() == true){
					jobRecLabDtItem.setPcsWise(true);
				}else if(labourCharge.getPerGramRate() == true){
					jobRecLabDtItem.setPerGramRate(true);
				}else if(labourCharge.getPercentage() == true){
					jobRecLabDtItem.setPercentWise(true);
				}
				else if(labourCharge.getPerCaratRate() == true){
					jobRecLabDtItem.setPerCaratRate(true);
				}
				
				
				
				
			}
			
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("labourRate", jobRecLabDtItem.getLabourRate() );
			jsonObject.put("perPcRate", jobRecLabDtItem.getPerPcRate());
			jsonObject.put("perGramRate", jobRecLabDtItem.getPerGramRate());
			jsonObject.put("percentage", jobRecLabDtItem.getPercentWise());
			jsonObject.put("perCaratRate", jobRecLabDtItem.getPerCaratRate());
			
		
			
			return jsonObject.toString();
			
			
			
		}else{
			
			
			return null;
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping("/jobRecLabDt/delete/{id}")
	public String delete(@PathVariable int id, Principal principal){
		
		String retVal = "-1";
		
		JobRecLabDt jobRecLabDt = jobRecLabDtService.findOne(id);
		
		
		jobRecLabDtService.delete(id);
			
		jobRecDtService.updateFob(jobRecLabDt.getJobRecDt(),false);
			
		
		
		return retVal;
	}
	
	
	@RequestMapping("/jobRecDt/applyGrading")
	@ResponseBody
	public String applyGrading(@RequestParam(value = "dtIds", required = true) String dtIds,
			@RequestParam(value = "labType", required = true) String labType,
			Principal principal)  {
		
	 
		return jobRecDtService.applyGradingRate(dtIds, labType, principal);
	}
	
	
	
}

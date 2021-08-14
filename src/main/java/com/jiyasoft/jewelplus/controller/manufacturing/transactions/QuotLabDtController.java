package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QQuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/transactions")
@Controller
public class QuotLabDtController {

	@Autowired
	private IQuotLabDtService quotLabDtService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@ModelAttribute("quotLabDt")
	public QuotLabDt constructLabDt() {
		return new QuotLabDt();
	}
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	
	@RequestMapping("/quotLabDt/listing")
	@ResponseBody
	public String quotLabDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "quotDtId", required = false) Integer quotDtId,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		

		
		StringBuilder sb = new StringBuilder();
		
		
		
		/*sb.append("{\"total\":").append(quotLabDtService.count())
		.append(",\"rows\": [");*/
		
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		List<QuotLabDt> quotLabDts = quotLabDtService.findByQuotDtAndDeactive(quotDt, false);
		
		sb.append("{\"total\":").append(quotLabDts.size()).append(",\"rows\": [");
		
		if(quotLabDts.size() > 0){
			for(QuotLabDt quotLabDt:quotLabDts){
				
			sb.append("{\"id\":\"")
				.append(quotLabDt.getId())
				.append("\",\"labourType\":\"")
				.append((quotLabDt.getLabourType() != null ? quotLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((quotLabDt.getLabourRate() != null ? quotLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((quotLabDt.getLabourValue() != null ? quotLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(quotLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(quotLabDt.getPcsWise())
				.append("\",\"perGram\":\"")
				.append(quotLabDt.getGramWise())
				.append("\",\"percent\":\"")
				.append(quotLabDt.getPercentWise())
				.append("\",\"perCarat\":\"")
				.append(quotLabDt.getPerCaratRate());
			if(!canViewFlag){
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLock(")
				.append(quotLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editQuotLabDt(")
				.append(quotLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a href='javascript:deleteQuotLabDt(event,")
				.append(quotLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(quotLabDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			}else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("")
				.append("\",\"actionLock\":\"")
				.append("");
			}
				sb.append("\"},");
				
			}
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	
	@RequestMapping("/quotLabDt/add")
	public String add(Model model) {
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		return "quotLabDt/add";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/quotLabDt/add", method = RequestMethod.POST)
	public String addEditQuotLabDt(@Valid @ModelAttribute("quotLabDt") QuotLabDt quotLabDt,
			BindingResult bindingResult,
			@RequestParam(value="id")Integer id,
			@RequestParam(value="forLabQuotMtId")Integer quotMtId,
			@RequestParam(value="forLabQuotDtId")Integer quotDtId,
			RedirectAttributes redirectAttributes,Principal principal){
		
		String retVal = "-1";
		if(bindingResult.hasErrors()){
			return "xyz";
		}
		
		try {
			retVal = quotLabDtService.transactionalSave(quotLabDt, id, quotMtId, quotDtId, principal,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		
		return retVal;
	
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/quotLabDt/lockUnlock")
	public String lockUnlockLabCompDt(
			@RequestParam(value="labDtId")Integer labDtId){
		
		String retVal = "-1";
		QuotLabDt quotLabDt = quotLabDtService.findOne(labDtId);
		
			if(quotLabDt.getrLock() == true){
				quotLabDt.setrLock(false);
			}else{
				quotLabDt.setrLock(true);
			}
			
			quotLabDtService.save(quotLabDt);
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/quotLabDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "labId")Integer labId){
		
		String retVal = "";
		
		QuotLabDt quotLabDt = quotLabDtService.findOne(labId);
		if(quotLabDt.getrLock() == true){
			retVal = "-1";
		}
		return retVal;
	}
	
	
	
	@RequestMapping("/quotLabDt/edit/{id}")
	public String editQuotLabDt(@PathVariable int id,Model model){
		QuotLabDt quotLabDt = quotLabDtService.findOne(id);
		model.addAttribute("quotLabDt",quotLabDt);
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		
		return "quotLabDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/quotLabDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal){
		
		String retVal = "-1";
		QuotLabDt quotLabDt = quotLabDtService.findOne(id);
		
		try {
			quotLabDtService.transactionalDelete(quotLabDt,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "error"; 
		}
		
		return retVal;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/quotLabDt/LockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		QuotMt quotMt = quotMtService.findOne(mtId);
		List<QuotLabDt> quotLabDts = quotLabDtService.findByQuotMtAndDeactive(quotMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(QuotLabDt quotLabDt:quotLabDts){
			quotLabDt.setrLock(setValue);
			quotLabDtService.save(quotLabDt);
		}
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/quotLabDt/labRateFromMaster")
	public String labRateFromMaster(
			@RequestParam(value="metalId") Integer metalId,
			@RequestParam(value="labourId") Integer labourId,
			@RequestParam(value="quotLabDtId") Integer quotLabDtId,
			@RequestParam(value="quotDtId") Integer quotDtId){
		
		
		
		QuotDt quotDt =quotDtService.findOne(quotDtId);
		
		Metal metal=metalService.findOne(metalId);
		LabourType labourType =labourTypeService.findOne(labourId);
			

		QQuotMetal qQuotMetal  =QQuotMetal.quotMetal;
		JPAQuery query=new JPAQuery(entityManager);
		
		List<Tuple> quotMetalList=null;
		
		quotMetalList = query.from(qQuotMetal).
				where(qQuotMetal.deactive.eq(false).and(qQuotMetal.quotDt.id.eq(quotDtId)).and(qQuotMetal.purity.metal.eq(metal)))
				.groupBy(qQuotMetal.purity.metal).list(qQuotMetal.purity,qQuotMetal.purity.metal.name,qQuotMetal.metalWeight.sum(),qQuotMetal.metalPcs.sum());
		
		List<LabourCharge> labourCharges=null;
		
		for(Tuple tuple : quotMetalList){
			
			Double vMetalWt= Math.round((tuple.get(qQuotMetal.metalWeight.sum())/quotDt.getPcs())*1000.0)/1000.0;
			
			
			
			
			 labourCharges =labourChargeService.getPurityLabourRates(quotDt.getQuotMt().getParty(), quotDt.getDesign().getCategory(),
					vMetalWt,false, metal,labourType,tuple.get(qQuotMetal.purity));
			 
			 if(labourCharges.size()<=0){
				 
				 labourCharges =labourChargeService.getLabourRates(quotDt.getQuotMt().getParty(), quotDt.getDesign().getCategory(),
							vMetalWt,false, metal,labourType);
				 
			 }
			
		}
		
		if(labourCharges.size()>0){
			LabourCharge labourCharge = labourCharges.get(0);
			
			QuotLabDt quotLabDt=null;
			
			if(quotLabDtId==null){
				quotLabDt = new QuotLabDt();
				
				quotLabDt.setMetal(metal);
				quotLabDt.setLabourType(labourType);
				
				quotLabDt.setLabourRate(labourCharge.getRate());

				if(labourCharge.getPerPcsRate() == true){
					quotLabDt.setPcsWise(true);
				}else if(labourCharge.getPerGramRate() == true){
					quotLabDt.setGramWise(true);
				}else if(labourCharge.getPercentage() == true){
					quotLabDt.setPercentWise(true);
				}
				else if(labourCharge.getPerCaratRate() == true){
					quotLabDt.setPerCaratRate(true);
				}
				
				
				
			}else{
				
				quotLabDt = quotLabDtService.findOne(quotLabDtId);
				quotLabDt.setMetal(metal);
				quotLabDt.setLabourType(labourType);
				
				quotLabDt.setLabourRate(labourCharge.getRate());
				quotLabDt.setPcsWise(false);
				quotLabDt.setGramWise(false);
				quotLabDt.setPercentWise(false);
				quotLabDt.setPerCaratRate(false);


				if(labourCharge.getPerPcsRate() == true){
					quotLabDt.setPcsWise(true);
				}else if(labourCharge.getPerGramRate() == true){
					quotLabDt.setGramWise(true);
				}else if(labourCharge.getPercentage() == true){
					quotLabDt.setPercentWise(true);
				}
				else if(labourCharge.getPerCaratRate() == true){
					quotLabDt.setPerCaratRate(true);
				}
				
				
				
				
			}
			
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("labourRate", quotLabDt.getLabourRate() );
			jsonObject.put("perPcRate", quotLabDt.getPcsWise());
			jsonObject.put("perGramRate", quotLabDt.getGramWise());
			jsonObject.put("percentage", quotLabDt.getPercentWise());
			jsonObject.put("perCaratRate", quotLabDt.getPerCaratRate());
			
		
			
			return jsonObject.toString();
			
			
			
		}else{
			
			
			return null;
		}
		
		
		
		
		
		
		
	}
	
	
	
	
}

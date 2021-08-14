package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class CastingDtController {

	@Autowired
	private ICastingDtService castingDtService;

	@Autowired
	private ICastingMtService castingMtService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IComponentService componentService;

	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@ModelAttribute("castingDt")
	public CastingDt constructDt() {
		return new CastingDt();
	}

	@ModelAttribute("castingMt")
	public CastingMt constructMt() {
		return new CastingMt();
	}
	
	@ModelAttribute("castingCompDt")
	public CastingCompDt constructCompDt() {
		return new CastingCompDt();
	}

	@RequestMapping("/castingDt")
	public String users(Model model,Principal principal) {
		model = populateModel(model,principal);
		return "castingDt";
	}

	@RequestMapping("/castingDt/listing")
	@ResponseBody
	public String castingDtListing(Model model,
				@RequestParam(value = "treeNo", required = false) String treeNo,
			Principal principal) {

		return castingDtService.castingDtListing(treeNo);

	}
	
	
		
	

	@RequestMapping("/addCastingDt/listing")
	@ResponseBody
	public String tranDtListing(Model model,
				@RequestParam(value = "castingMtId", required = false) Integer castingMtId) {
		
				
		
		
	return castingDtService.addCastingBagListing(castingMtId);

	
		
	}	

		
		
		
		
		
	

	@RequestMapping("/castingDt/add")
	public String first(Model model,Principal principal) {
		model = populateModel(model,principal);
		return "castingDt/add";

	}

	public Model populateModel(Model model, Principal principal) {
		
	//	model.addAttribute("departmentMap",departmentService.getDepartmentList());
		
		Department department = departmentService.findByName("Casting");
		
		User user = userService.findByName(principal.getName());
		model.addAttribute("departmentMap",userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("componentMap",componentService.getComponentList());
		model.addAttribute("purityMap",purityService.getPurityList());
		model.addAttribute("colorMap",colorService.getColorList());
		
		return model;
	}

	
	@RequestMapping(value = "/castingDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditCastingDt(
			@Valid @ModelAttribute("castingDt") CastingDt castingDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "castingMtId") Integer castingMtId,
			@RequestParam(value="trfTblData")String trfTblData,
			RedirectAttributes redirectAttributes, Principal principal) {

		String retVal = "1";
		
		synchronized (this) {
			
			if (result.hasErrors()) {
				return "castingDt/add";
			}
			
			JSONArray jsonTrfTblArray = new JSONArray(trfTblData);
			
			

			
			
			String vBagNm="";
			
			
			for (int i = 0; i < jsonTrfTblArray.length(); i++) {
				
				JSONObject jsonObject =   (JSONObject) jsonTrfTblArray.get(i);
				
				TranMetal tranMetal =tranMetalService.findOne(Integer.parseInt(jsonObject.get("id").toString()));
				
		
				if(tranMetal.getCurrStk().equals(false)){
					
					if(vBagNm ==""){
						vBagNm=tranMetal.getBagMt().getName();
						
					}else{
						vBagNm+=","+tranMetal.getBagMt().getName();
					}
					
				}
				
				
				
			}
				
			if(vBagNm !=""){
				
				
				return "error : Can Not Transfer ,Bags are in Transactions ( "+vBagNm+")";
				
			}
				
			
			
			retVal=castingDtService.transferToCasting(jsonTrfTblArray,castingMtId,principal);
			
		}
		

	
		


		return retVal;

	}

	@RequestMapping(value = "/castingDtTrf/add", method = RequestMethod.POST)
	@ResponseBody
	public String addFromCastingDt(
			@Valid @ModelAttribute("castingDt") CastingDt castingDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pODIds") String pOIds,
			@RequestParam(value = "metalWtt") String metalWtt,
			RedirectAttributes redirectAttributes, Principal principal) throws IllegalArgumentException, IllegalAccessException {

		String retVal = "1";
		
		synchronized (this) {
			
			
			
			
			
			

			if (result.hasErrors()) {
				return "castingDt";
			}
			
			
			if(pOIds.length() <= 0){
				return "error : Record Not Selected";
			}
			
			
			String[] data1 = pOIds.split(",");
			
			Department department =departmentService.findOne(castingDt.getDepartment().getId());
			
			String vBagNm="";
			for (int i = 0; i < data1.length; i++) {
				
				CastingDt castDt = castingDtService.findOne(Integer.parseInt(data1[i]));
				
				
				List<TranMt> tranMts =tranMtService.findByBagMtAndCurrStk(castDt.getBagMt(), true);
				for(TranMt tranMt :tranMts){
					
					if(tranMt.getDepartment().getName().equalsIgnoreCase(department.getName())){
						
						break;
						
					}else if(!(tranMt.getDepartment().getName().equalsIgnoreCase("Casting")) && 
							!(tranMt.getDepartment().getName().equalsIgnoreCase("READY FOR CASTING")) 
							&& !(tranMt.getDepartment().getName().equalsIgnoreCase(department.getName())) ){
							
						if(vBagNm ==""){
							vBagNm="Bag No= "+tranMt.getBagMt().getName()+", Department= "+tranMt.getDepartment().getName()+" \n";
							
						}else{
							vBagNm+=", Bag No= "+tranMt.getBagMt().getName()+", Department= "+tranMt.getDepartment().getName()+" \n";
						}
						
					}
					
					
				}
				
				
				
				
			}
			
			
			if(vBagNm !=""){
				return "error : Can Not Transfer ,Bags are in Transactions ( "+vBagNm+")";
				
			}
			
			
			
			
		retVal=castingDtService.castingToDepartmentTransfer(pOIds, castingDt, metalWtt, principal);
			
			
		}
		
	
		

		return retVal;

	}

	@RequestMapping("/castingDt/tree")
	@ResponseBody
	public String treeListing(@RequestParam(value = "cDate") Date cDate,
			@ModelAttribute("castingDt") CastingDt castingDt) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> treeMap = castingMtService.getCastingMtList(cDate);

		sb.append("<select id=\"castingMt.id\" treeNo=\"castingMt.id\" class=\"form-control\" onChange=\"javascript:popPurityColorPcs();popCastingtDt();\">");
		sb.append("<option value=\"\">- Select tree -</option>");

		for (Object key : treeMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">").append(treeMap.get(key)).append("</option>");
			System.out.println("-----------------"+treeMap.keySet());
		}
		
		sb.append("</select>");

		return sb.toString();
	}
	
	@RequestMapping("/castingDt/fromTreeNo/toid")
	@ResponseBody
	public String treeListingSetting(@RequestParam(value = "id") Integer id,
			@ModelAttribute("castingDt") CastingDt castingDt) {
		
		CastingMt castMt = castingMtService.findOne(id);	
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> treeMap = castingMtService.getCastingMtList(castMt.getcDate());

		sb.append("<select id=\"castingMt.id\" treeNo=\"castingMt.id\" class=\"form-control\" onChange=\"javascript:popPurityColorPcs();popCastingtDt();\">");
		sb.append("<option value=\"\">- Select tree -</option>");

		for (Object key : treeMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">").append(treeMap.get(key)).append("</option>");
		}
		sb.append("</select>");
		

		return sb.toString();
	}

	@RequestMapping("/castingDt/purityColorPcs")
	@ResponseBody
	public String purityColorPcs(@RequestParam(value = "treeNo") String treeNo,
			@RequestParam(value = "cDate") Date cDate,
			@ModelAttribute("castingDt") CastingDt castingDt) {

		CastingMt castingMtt = castingMtService.findOne(Integer.parseInt(treeNo));
		String treNo = castingMtt.getTreeNo();
		Double totalPcsWt = castingMtt.getPcsWt();

		String str = "" + castingMtt.getPurity().getName() + "_"+ castingMtt.getColor().getName() + "_" + totalPcsWt + "_"+ castingMtt.getId();

		return str;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/castingDt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		String retVal="-1";
		
		synchronized (this) {
		
			retVal=castingDtService.CastingDelete(id);
			
		}
		
		
		
		
		return retVal;
		
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/castingDt/recast/{id}")
	public String recast(@PathVariable int id) {
		
		
		String retVal = "-1";
		
		CastingDt castingDt = castingDtService.findOne(id);
		
		TranMt tranMts = tranMtService.findByDepartmentAndDeptFromAndBagMtAndCurrStk(castingDt.getDeptTo(), castingDt.getDepartment(), castingDt.getBagMt(), true);
		if(tranMts != null){
			
			
			castingDt.setMetalWt(0.0);
			castingDt.setTransfer(false);
			castingDt.setTransferDate(null);
			castingDtService.save(castingDt);
			
			
			
			List<TranDt> tranDts = tranDtService.findByDepartmentAndDeptFromAndBagMtAndCurrStk(castingDt.getDeptTo(), castingDt.getDepartment(), castingDt.getBagMt(), true);
			
						
			for(TranDt tranDt : tranDts){
				
				TranDt tranDtOld  = tranDtService.findOne(tranDt.getRefDtId());
				tranDtOld.setCurrStk(true);
				tranDtOld.setDeptTo(null);
				tranDtOld.setIssDate(null);
				tranDtService.save(tranDtOld);
				
				tranDtService.delete(tranDt.getId());
			}
			
			
			
			
			
			TranMt tranMtOld  = tranMtService.findOne(tranMts.getRefMtId());
			tranMtOld.setCurrStk(true);
			tranMtOld.setDeptTo(null);
			tranMtOld.setIssueDate(null);
			tranMtService.save(tranMtOld);
			
			tranMtService.delete(tranMts.getId());
			
			
			
			
			MetalTran metalTran = metalTranService.findByRefTranIdAndTranTypeAndBagMtAndPcsWtAndDeactive(castingDt.getCastingMt().getId(), "Casting", castingDt.getBagMt(), true, false);
			if(metalTran != null){
				metalTranService.delete(metalTran.getId());
			}
			
			retVal = "-2";
			
		}
		
		
		return retVal;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/castingDt/updateTotalIssWt")
	public String updateTotalIssWt(@RequestParam(value = "treeNo", required = false) Integer treeId,
			@RequestParam(value = "id", required = false) Integer castingDtId,
			@RequestParam(value = "metalWt", required = false) Double metalWt) {
		
		Double vMetalWt=0.0;
		
		CastingMt castingMt =castingMtService.findOne(treeId);
		
		List<CastingDt> castingDts =castingDtService.findByCastingMtAndDeactive(castingMt, false);
		
		for(CastingDt castingDt :castingDts){
			if(castingDt.getId().equals(castingDtId)){
				castingDt.setMetalWt(metalWt);
				castingDtService.save(castingDt);
			}
			
			vMetalWt +=castingDt.getMetalWt();
		}

		
		
		
		return vMetalWt.toString();
		
	}
	
	
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}

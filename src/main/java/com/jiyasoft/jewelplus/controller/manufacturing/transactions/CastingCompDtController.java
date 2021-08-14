package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CastingCompDtController {
	
	@Autowired
	private ICastingCompDtService castingCompDtService;
	
	@Autowired
	private ICastingMtService castingMtService;
	
	@Autowired
	private IDepartmentService departmentService; 
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private ICompTranService compTranService;
	
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
	
	
	
	@RequestMapping("/castingCompDt/listing")
	@ResponseBody
	public String castingCompDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "cDate", required = false) Date cDate,
			@RequestParam(value = "treeNo", required = false) String treeNo,
			Principal principal) {

		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(castingCompDtService.count()).append(",\"rows\": [");
		
		CastingMt castingMt = castingMtService.findOne(Integer.parseInt(treeNo));
		
		List<CastingCompDt> castingCompDts = castingCompDtService.findByCastingMtAndDeactive(castingMt, false);

		for (CastingCompDt castingCompDt : castingCompDts) {
			sb.append("{\"id\":\"")
					.append(castingCompDt.getId())
					.append("\",\"component\":\"")
					.append((castingCompDt.getComponent() != null ? castingCompDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((castingCompDt.getPurity() != null ? castingCompDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((castingCompDt.getColor() != null ? castingCompDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(castingCompDt.getMetalWt())
					.append("\",\"qty\":\"")
					.append(castingCompDt.getQty())
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:deleteCastingCompDt(")
					.append(castingCompDt.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(castingCompDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/castingCompDt/add" , method=RequestMethod.POST)
	public String addCastingCompDt(@Valid @ModelAttribute("castingCompDt") CastingCompDt castingCompDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "treeId") Integer treeId,
			RedirectAttributes redirectAttributes, Principal principal){
		
		String retVal = "-1";	
		
		if(result.hasErrors()){
			return retVal = "-2";
		}
		
		
		if(treeId == null && id.equals("")){
			return retVal = "-3";
		}
		
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
		
			CastingMt castingMt = castingMtService.findOne(treeId);
			
			castingCompDt.setCastingMt(castingMt);
			castingCompDt.setPurity(castingMt.getPurity());
			castingCompDt.setColor(castingMt.getColor());
			castingCompDt.setCreatedBy(principal.getName());
			castingCompDt.setCreatedDate(new java.util.Date());
			castingCompDt.setMetalRate(castingMt.getMetalRate());
			
			castingCompDtService.save(castingCompDt);
			
			
			//----- new entry in metal tran
			
			/*
			 * Double
			 * mtlRate=metalTranService.getMetalRate(castingCompDt.getPurity().getId(),
			 * castingCompDt.getColor().getId(),
			 * castingCompDt.getCastingMt().getDepartment().getId(),
			 * castingCompDt.getMetalWt());
			 */
			
			MetalTran metalTran = new MetalTran();
			
			metalTran.setColor(castingCompDt.getColor());
			metalTran.setPurity(castingCompDt.getPurity());
			metalTran.setInOutFld("D");
			metalTran.setBagMt(null);
			metalTran.setBalance(castingCompDt.getMetalWt() * -1);
			metalTran.setMetalWt(castingCompDt.getMetalWt());
			metalTran.setDeptLocation(castingCompDt.getCastingMt().getDepartment());
			metalTran.setPurityConv(castingCompDt.getPurity().getPurityConv());
			metalTran.setRefTranId(castingCompDt.getId());
			metalTran.setTranType("CastingComp");
			metalTran.setRemark("");
			metalTran.setDepartment(departmentService.findByName("Component"));
			metalTran.setPcsWt(true);
			metalTran.setCreatedBy(castingCompDt.getCreatedBy());
			metalTran.setCreatedDt(castingCompDt.getCreatedDate());
			metalTran.setTranDate(new Date());
			metalTran.setMetalRate(castingMt.getMetalRate());
			metalTranService.save(metalTran);

			
			//-------new entry in comp tran
			
			/*
			 * Double
			 * compMtlRate=compTranService.getCompMetalRate(castingCompDt.getPurity().getId(
			 * ), castingCompDt.getColor().getId(),
			 * departmentService.findByName("Component").getId(),
			 * castingCompDt.getComponent().getId(),castingCompDt.getMetalWt());
			 */
			
			
			CompTran compTran = new CompTran();
			
			compTran.setColor(castingCompDt.getColor());
			compTran.setPurity(castingCompDt.getPurity());
			compTran.setInOutFld("C");
			compTran.setBagMt(null);
			compTran.setBalance(castingCompDt.getMetalWt());
			compTran.setMetalWt(castingCompDt.getMetalWt());
			compTran.setDeptLocation(departmentService.findByName("Component"));
			compTran.setPurityConv(castingCompDt.getPurity().getPurityConv());
			compTran.setRefTranId(castingCompDt.getId());
			compTran.setTranType("CastingComp");
			compTran.setRemark("Tree No - "+castingMt.getTreeNo());
			compTran.setDepartment(castingCompDt.getCastingMt().getDepartment());
			compTran.setComponent(castingCompDt.getComponent());
			compTran.setCreatedBy(castingCompDt.getCreatedBy());
			compTran.setCreatedDt(castingCompDt.getCreatedDate());
			compTran.setPcs(castingCompDt.getQty());
			compTran.setBalancePcs(castingCompDt.getQty());
			compTran.setTrandate(new Date());
			compTran.setMetalRate(castingCompDt.getMetalRate());
			compTranService.save(compTran);
			
			
			
			
		}
		
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/castingCompDt/delete/{id}")
	public String delete(@PathVariable int id) {
		castingCompDtService.delete(id);
		
		List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(id, "CastingComp",false);
		
		for(MetalTran metalTran : metalTrans){
			metalTranService.delete(metalTran.getId());
		}
		
		List<CompTran> compTrans = compTranService.findByRefTranIdAndTranType(id, "CastingComp");
		
		for(CompTran compTran : compTrans){
			compTranService.delete(compTran.getId());
		}
		
		
		return "-1";
	}
	
	
	

}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IKtConversionDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IKtConversionMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@Controller
@RequestMapping("/manufacturing/transactions")
public class KtConversionDtController {
	
	
	@Autowired
	private IKtConversionDtService ktConversionDtService;
	
	@Autowired
	private IKtConversionMtService ktConversionMtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@ModelAttribute("ktConversionDt")
	public KtConversionDt construct(){
		return new KtConversionDt();
	}
	
	
	@RequestMapping("/ktConversionDt/listing")
	@ResponseBody
	public String ktConversionDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "MtId", required = false) Integer mtId,
			Principal principal) {

		StringBuilder sb = new StringBuilder();

		KtConversionMt ktConversionMt = ktConversionMtService.findOne(mtId);
		List<KtConversionDt> ktConversionDts = ktConversionDtService.findByKtConversionMtAndDeactive(ktConversionMt, false);
		
		sb.append("{\"total\":").append(ktConversionDtService.count()).append(",\"rows\": [");
		
		for (KtConversionDt ktConversionDt : ktConversionDts) {

			sb.append("{\"id\":\"")
					.append(ktConversionDt.getId())
					.append("\",\"purity\":\"")
					.append((ktConversionDt.getIssPurity() != null ? ktConversionDt.getIssPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((ktConversionDt.getIssColor() != null ? ktConversionDt.getIssColor().getName() : ""))
					.append("\",\"freshMetalWt\":\"")
					.append((ktConversionDt.getIssFreshMetalWt() != null ? ktConversionDt.getIssFreshMetalWt() : ""))
					/*.append("\",\"usedMetalWt\":\"")
					.append((ktConversionDt.getIssUsedMetalWt() != null ? ktConversionDt.getIssUsedMetalWt() : "" ))*/
					.append("\",\"pureWt\":\"")
					.append(ktConversionDt.getPureWt())
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editKtConversionDt(")
					.append(ktConversionDt.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/ktConversionDt/add")
	public String addEdit(
			@Valid @ModelAttribute("ktConversionDt") KtConversionDt ktConversionDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value="vMtId") Integer vMtId,
			RedirectAttributes redirectAttributes, Principal principal){
		
		User user = userService.findByName(principal.getName());
		String retVal = "-1";
		String action = "added";
		MetalTran metalTran = null;
		
		
		Integer locationId = 0;
		Double mtlRate =0.0;
		LocationRights locationRights = locationRightsService.findByUserAndDeactiveAndDefaultFlg(user, false, true);
		if(locationRights != null) {
			locationId = locationRights.getDepartment().getId();
		
		}else {
			locationId = departmentService.findByName("Central").getId();	
			
		}
		
		Double freshStockBalance=0.0;
		if(ktConversionDt.getIssFreshMetalWt() > 0){
			freshStockBalance = metalTranService.getStockBalance(ktConversionDt.getIssPurity().getId(), ktConversionDt.getIssColor().getId(), locationId);
			if(freshStockBalance <= 0.0){
				return retVal = "-1";
			}
		}else{
			freshStockBalance = 0.0;
		}
		
		

		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			//fresh
			if(ktConversionDt.getIssFreshMetalWt() > 0){
				if(freshStockBalance < ktConversionDt.getIssFreshMetalWt()){
					return retVal = "-1";
				}
			}
			
			
			KtConversionMt ktConversionMt = ktConversionMtService.findOne(vMtId);
			
			
	
			Double vIssWt= Math.round((ktConversionDt.getIssFreshMetalWt()+ktConversionMt.getTotIssWt())*1000.0)/1000.0;
			
			if(  vIssWt > ktConversionMt.getReqMetalWt() ){
				return retVal = "-11";
			}
			
			
			
			ktConversionDt.setKtConversionMt(ktConversionMt);
			ktConversionDt.setCreatedBy(principal.getName());
			ktConversionDt.setCreatedDt(new java.util.Date());
			ktConversionDt.setUniqueId(new java.util.Date().getTime());
			
			metalTran = new MetalTran();

			retVal = "1";
			
		}else{
			
			KtConversionDt prevktConversionDt = ktConversionDtService.findOne(id);
			
			if(ktConversionDt.getIssPurity().getId().equals(prevktConversionDt.getIssPurity().getId()) && 
					ktConversionDt.getIssColor().getId().equals(prevktConversionDt.getIssColor().getId())){
					
					//fresh
					if(ktConversionDt.getIssFreshMetalWt() > 0){
						
						Double freshDifference = ktConversionDt.getIssFreshMetalWt() - prevktConversionDt.getIssFreshMetalWt();
						if(freshStockBalance < freshDifference){
							return retVal = "-1";
						}
					}
					
					
			}else{
				
				//fresh
				if(ktConversionDt.getIssFreshMetalWt() > 0){
						if(freshStockBalance < ktConversionDt.getIssFreshMetalWt()){
						return retVal = "-1";
					}
				}
			
				
			}
			
			KtConversionMt ktConversionMt = ktConversionMtService.findOne(vMtId);
			
			Double tempVal =  Math.round((ktConversionDt.getIssFreshMetalWt()-prevktConversionDt.getIssFreshMetalWt())*1000.0)/1000.0;
			
			Double vTempVal=  Math.round((tempVal+ktConversionMt.getTotIssWt())*1000.0)/1000.0;
			
			
			if(ktConversionMt.getReqMetalWt() < vTempVal){
				return retVal = "-11";
			}
			
			
			
			ktConversionDt.setKtConversionMt(ktConversionMt);
			ktConversionDt.setModiBy(principal.getName());
			ktConversionDt.setModiDt(new java.util.Date());
			action = "updated";
			
			metalTran = metalTranService.findByRefTranIdAndTranTypeAndInOutFld(id, "AlloyingIssue", "D");
			action = "updated";
			retVal = "2";
		}
		
	
		ktConversionDtService.save(ktConversionDt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		KtConversionDt ktConvDt = ktConversionDtService.findByUniqueId(ktConversionDt.getUniqueId());
		
		if(ktConvDt.getIssFreshMetalWt() > 0){
			metalTran.setDepartment(departmentService.findByName("Alloying"));
			
			if(locationRights != null) {
				metalTran.setDeptLocation(locationRights.getDepartment());
				
				mtlRate=metalTranService.getMetalRate(ktConvDt.getIssPurity().getId(), ktConvDt.getIssColor().getId(), 
						locationRights.getDepartment().getId(), ktConvDt.getIssFreshMetalWt());
				
			}else {
				metalTran.setDeptLocation(departmentService.findByName("Central"));	
			
				mtlRate=metalTranService.getMetalRate(ktConvDt.getIssPurity().getId(), ktConvDt.getIssColor().getId(), 
						departmentService.findByName("Central").getId(), ktConvDt.getIssFreshMetalWt());
			}
			
			
			ktConvDt.setMetalRate(mtlRate != null ? mtlRate :0.0);
			ktConversionDtService.save(ktConvDt);
			
			metalTran.setRefTranId(ktConvDt.getId());
			metalTran.setPurity(ktConvDt.getIssPurity());
			metalTran.setColor(ktConvDt.getIssColor());
			metalTran.setMetalWt(ktConvDt.getIssFreshMetalWt());
			metalTran.setBalance(ktConvDt.getIssFreshMetalWt() * -1);
			metalTran.setInOutFld("D");
			if(ktConvDt.getIssPurity().getPurityConv() != null){
				metalTran.setPurityConv(ktConvDt.getIssPurity().getPurityConv());
			}else{
				metalTran.setPurityConv(0.0);
			}
			metalTran.setTranType("AlloyingIssue");
			metalTran.setCreatedBy(ktConvDt.getCreatedBy());
			metalTran.setCreatedDt(ktConvDt.getCreatedDt());
			metalTran.setTranDate(ktConvDt.getKtConversionMt().getcDate());
			metalTran.setDeactive(false);
			metalTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
			metalTranService.save(metalTran);
		}
		


		return retVal;
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/ktConversionDt/editValidation")
	public String editValidation(
			@RequestParam("dtId") Integer dtId,Principal principal){
		
		KtConversionDt ktConversionDt = ktConversionDtService.findOne(dtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		Date tempDate = ktConversionDt.getCreatedDt();
		String cDate = dateFormat.format(tempDate);
		
		
		Boolean flag = false;
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			flag = true;
		}else{
			if(cDate.equals(curDate)){
				flag = true;
			}
			
		}
		
		return flag.toString();
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping("/ktConversionDt/edit/{id}")
	public String edit(@PathVariable("id") Integer id,Model model){
		
		KtConversionDt ktConversionDt = ktConversionDtService.findOne(id);
		model.addAttribute("ktConversionDt", ktConversionDt);
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("purityMapp", purityService.getPurityList());
		
		return "ktConversionDt/add";
		
	}
	
	
	/*@ResponseBody
	@RequestMapping("/ktConversionDt/delete/{id}")
	public String delete(@PathVariable("id") Integer id){
		
		String retVal = "-1";
		
		ktConversionDtService.delete(id);		
		KtConversionDt ktConversionDt = ktConversionDtService.findOne(id);
		List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(ktConversionDt.getId(), "AlloyingIssue", false);
		for(MetalTran metalTran:metalTrans){
			metalTranService.delete(metalTran.getId());
		}
		
		return retVal;
	}*/
	
	
	
	@RequestMapping("/ktConvDt/issPurityConv")
	@ResponseBody
	public String purityIdFetch(
			@RequestParam(value = "issPurityId") Integer issPurityId) {

		StringBuilder sb = new StringBuilder();
		Purity purity = purityService.findOne(issPurityId);
		
		double purityConv = purity.getPurityConv();
		sb.append(purityConv);

		return sb.toString();
	}
	
	
	
	@ResponseBody
	@RequestMapping("/ktConversionDt/totIssWtUpdate")
	public String totIssWtUpdate(
			@RequestParam("mtId") Integer mtId,
			@RequestParam("totIssWt") Double totIssWt,
			Principal principal){
		
		
		KtConversionMt ktConversionMt = ktConversionMtService.findOne(mtId);
		
		ktConversionMt.setTotIssWt(totIssWt);
		ktConversionMt.setLoss(Math.round((totIssWt-ktConversionMt.getRecWt())*1000.0)/1000.0);
		ktConversionMt.setModiBy(principal.getName());
		ktConversionMt.setModiDt(new java.util.Date());
		
		ktConversionMtService.save(ktConversionMt);
		
		
		return "-1";
		
	}
	

}

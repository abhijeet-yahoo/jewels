package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;


@Controller
@RequestMapping("/manufacturing/transactions")
public class QuotQualityController {

	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IQuotQualityService quotQualityService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IQuotStnDtService quotStnDtService;
	
	@ModelAttribute("quotMt")
	public QuotMt construct() {
		return new QuotMt();
	}
	
	
	@ModelAttribute("quotQuality")
	public QuotQuality constructQuotQuality() {
		return new QuotQuality();
	}
	
	
	@RequestMapping("/quotQuality")
	public String users(Model model) {	
		return "quotQuality";
	}
	
	
	
	@RequestMapping("/quotQuality/listing")
	@ResponseBody
	public String quotQualityListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

			StringBuilder sb = new StringBuilder();
			
			QuotMt quotMt = quotMtService.findByInvNoAndDeactive(pInvNo, false);
			
			List<QuotQuality> quotQualitys = quotQualityService.findByQuotMtAndDeactive(quotMt, false);
			
			sb.append("{\"total\":").append(quotQualitys.size()).append(",\"rows\": [");

			for (QuotQuality quotQuality : quotQualitys) {
				sb.append("{\"id\":\"")
					.append(quotQuality.getId())
					.append("\",\"stoneType\":\"")
					.append((quotQuality.getStoneType() == null ? "" : quotQuality.getStoneType().getName()))
					.append("\",\"shape\":\"")
					.append((quotQuality.getShape() == null ? "" : quotQuality.getShape().getName()))
					.append("\",\"quality\":\"")
					.append((quotQuality.getQuality() == null ? "" : quotQuality.getQuality().getName()))
					.append("\",\"deactive\":\"")
					.append((quotQuality.getDeactive() == null ? "Active" : (quotQuality.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((quotQuality.getDeactiveDt() == null ? "" : quotQuality.getDeactiveDt()))
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editQuotQuality(")
					.append(quotQuality.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteQuotQuality(event,")
				    .append(quotQuality.getId())
				    .append(");' class='btn btn-xs btn-danger triggerRemove")
				    .append(quotQuality.getId())
				    .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
			}

		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}
	
	
	private Model populateModel(Model model) {
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		return model;
	}

	
	
	@ResponseBody
	@RequestMapping(value = "/quotQuality/add", method = RequestMethod.POST)
	public String addEditQuotQuality(@Valid @ModelAttribute("quotQuality") QuotQuality quotQuality,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pOQInvNo", required = false) String pOQInvNo,
			@RequestParam(value = "updateFlg", required = false) String updateFlg,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";


		if (result.hasErrors()) {
			return "quotQuality/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			quotQuality.setCreatedBy(principal.getName());
			quotQuality.setCreatedDate(new java.util.Date());
			quotQuality.setQuotMt(quotMtService.findByInvNoAndDeactive(pOQInvNo, false));

			retVal = "1";
		} else {
		
			quotQuality.setModiBy(principal.getName());
			quotQuality.setModiDate(new java.util.Date());
			quotQuality.setQuotMt(quotMtService.findByInvNoAndDeactive(pOQInvNo, false));

			action = "updated";
			retVal = "1";
		}

		if (quotQuality.getShape().getId() == null) {
			quotQuality.setShape(null);
		}
		if (quotQuality.getQuality() == null || quotQuality.getQuality().getId() == null) {
			quotQuality.setQuality(null);
		}

		quotQualityService.save(quotQuality);
		
		
	if(updateFlg.equalsIgnoreCase("true")){
			
			QuotMt quotMt=quotMtService.findByInvNoAndDeactive(pOQInvNo,false);
			if(quotMt !=null){
				
				List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
				for(QuotDt quotDt :quotDts) {
					List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
					
					for(QuotStnDt quotStnDt: quotStnDts){
						if(quotStnDt.getStoneType().getId().equals(quotQuality.getStoneType().getId()) && quotStnDt.getShape().getId().equals(quotQuality.getShape().getId())){
							quotStnDt.setQuality(quotQuality.getQuality());
							quotStnDt.setModiBy(principal.getName());
							quotStnDt.setModiDate(new java.util.Date());
							quotStnDtService.save(quotStnDt);
						}
						
					}
					
					quotDtService.updateQltyDesc(quotDt.getId());
					
				}
				
				
				
			
				
				retVal="1";
			}

			
		}
		
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}
	
	

	@RequestMapping("/quotQuality/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, Principal principal) {

			QuotQuality quotQuality = quotQualityService.findOne(id);
			model.addAttribute("quotQuality", quotQuality);
			model.addAttribute("qualityMap", qualityService.getQualityList(quotQuality.getShape().getId()));
			model = populateModel(model);
		
		return "quotQuality/add";
	}

	
	
	@ResponseBody
	@RequestMapping("/quotQuality/delete/{id}")
	public String delete(@PathVariable int id) {
		quotQualityService.delete(id);
		return "1";
	}

	
	
	
	@RequestMapping("/quotQuality/list")
	@ResponseBody
	public String qualityList(@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("quotMt") QuotMt quotMt) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> qualityMap = qualityService.getQualityList(shapeId);

		sb.append("<select id=\"quality.id\" name=\"quality.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Quality -</option>");
		for (Object key : qualityMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(qualityMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	
	
	
	
}

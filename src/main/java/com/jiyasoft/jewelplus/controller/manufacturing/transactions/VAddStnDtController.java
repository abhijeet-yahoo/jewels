package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnDtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class VAddStnDtController {
	
	@Autowired
	private IVAddStnDtService vAddStnDtService;
	
	@Autowired
	private IVAddDtService vAddDtService;
	
	@Autowired
	private IVAddStnAdjService vaddStnAdjService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Value("${vaddstnadjtype}")
	private String vaddstnadjtype;

	
	@ModelAttribute("costingMt")
	public CostingMt construct() {
		return new CostingMt();
	}
	
	
	@ModelAttribute("vAddDt")
	public VAddDt constructDt() {
		return new VAddDt();
	}
	
	@ModelAttribute("vAddStnDt")
	public VAddStnDt constructStnDt() {
		return new VAddStnDt();
	}
	
	
	
	
	@RequestMapping("/vAddCostStnDt/listing")
	@ResponseBody
	public String vAddCostStnDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "vAddDtId", required = false) Integer vAddDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		
		
		
		VAddDt vAddDt = vAddDtService.findOne(vAddDtId);
		List<VAddStnDt> vAddStnDts = vAddStnDtService.findByVAddDtAndDeactive(vAddDt, false);
		
		sb.append("{\"total\":").append(vAddStnDts.size())
		.append(",\"rows\": [");

		for(VAddStnDt vAddStnDt:vAddStnDts){
				
				sb.append("{\"id\":\"")
					.append(vAddStnDt.getId())
					.append("\",\"stoneType\":\"")
					.append((vAddStnDt.getShape() != null ? vAddStnDt.getShape().getName() : ""))
					.append("\",\"shape\":\"")
					.append((vAddStnDt.getShape() != null ? vAddStnDt.getShape().getName() : ""))
					.append("\",\"quality\":\"")
					.append((vAddStnDt.getQuality() != null ? vAddStnDt.getQuality().getName() : ""))
					.append("\",\"size\":\"")
					.append((vAddStnDt.getSize() != null ? vAddStnDt.getSize() : ""))
					.append("\",\"sizeGroup\":\"")
					.append((vAddStnDt.getSizeGroup() != null ? vAddStnDt.getSizeGroup().getName() : ""))
					.append("\",\"stone\":\"")
					.append((vAddStnDt.getStone() != null ? vAddStnDt.getStone() : ""))
					.append("\",\"carat\":\"")
					.append((vAddStnDt.getCarat() != null ? vAddStnDt.getCarat() : ""))
					.append("\",\"rate\":\"")
					.append((vAddStnDt.getStnRate() != null ? vAddStnDt.getStnRate() : ""))
					.append("\",\"stoneValue\":\"")
					.append((vAddStnDt.getStoneValue() != null ? vAddStnDt.getStoneValue() : ""))
					.append("\",\"handlingRate\":\"")
					.append((vAddStnDt.getHandlingRate() != null ? vAddStnDt.getHandlingRate() : ""))
					.append("\",\"handlingValue\":\"")
					.append((vAddStnDt.getHandlingValue() != null ? vAddStnDt.getHandlingValue() : ""))
					.append("\",\"setting\":\"")
					.append((vAddStnDt.getSetting() != null ? vAddStnDt.getSetting().getName() : ""))
					.append("\",\"settingType\":\"")
					.append((vAddStnDt.getSettingType() != null ? vAddStnDt.getSettingType().getName() : ""))
					.append("\",\"settingRate\":\"")
					.append((vAddStnDt.getSetRate() != null ? vAddStnDt.getSetRate() : ""))
					.append("\",\"settingValue\":\"")
					.append((vAddStnDt.getSetValue() != null ? vAddStnDt.getSetValue() : ""))
					.append("\",\"rLock\":\"")
					.append((vAddStnDt.getrLock() == null ? "": (vAddStnDt.getrLock() ? "Lock": "Unlock"))) //1 = lock & 0 = unlock
					.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doStoneDtLockUnLock(")
					.append(vAddStnDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editvAddStnDt(")
					.append(vAddStnDt.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/vAddStnDt/delete/")
					.append(vAddStnDt.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(vAddStnDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	
	
	
	@RequestMapping("/vAddStnDt/adjustmentListing")
	@ResponseBody
	public String vAddCostStnDtListing(@RequestParam(value = "vAddMtId", required = false) Integer vAddMtId) {

		return vaddStnAdjService.vaddStnDtListing(vAddMtId, vaddstnadjtype.trim());
		
		
	}
	
	
	

	
	
	
	@RequestMapping("/vAddStnDt/stnPurcAdjtListing")
	@ResponseBody
	public String stnPurcAdjtListing(
			@RequestParam(value = "stonetypeid", required = false) Integer stonetypeid,
			@RequestParam(value = "shapeid", required = false) Integer shapeid,
			@RequestParam(value = "qualityid", required = false) Integer qualityid,
			@RequestParam(value = "sizegroupid", required = false) Integer sizegroupid,
			@RequestParam(value = "size", required = false) String size,
			@RequestParam(value = "rate", required = false) Double rate,
			@RequestParam(value = "qualityGroup", required = false) String qualityGroup) {
		
		
		StringBuilder sb = new StringBuilder();
			
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_vaddstnpurcadj(?,?,?,?,?,?,?) }");
		query.setParameter(1,stonetypeid);
		query.setParameter(2,shapeid);
		query.setParameter(3,qualityid);
		query.setParameter(4,sizegroupid);
		query.setParameter(5,size);
		query.setParameter(6,vaddstnadjtype.trim());
		query.setParameter(7,qualityGroup!=null? qualityGroup.trim():"");
		
		
		List<Object[]> dtList = query.getResultList();
		
		sb.append("{\"total\":").append(dtList.size()).append(",\"rows\": [");
		
		for (Object[] result : dtList) {
			
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			String invDt = sd.format(result[2]);

			
			sb.append("{\"dtId\":\"")
			.append(result[0]!=null? result[0]:"")
			.append("\",\"invNo\":\"")
			.append(result[1]!=null? result[1]:"")
			.append("\",\"invDate\":\"")
			.append(invDt)
			.append("\",\"size\":\"")
			.append(result[3]!=null? result[3]:"")
			.append("\",\"stonetypenm\":\"")
			.append(result[4]!=null? result[4]:"")
			.append("\",\"shapenm\":\"")
			.append(result[5]!=null? result[5]:"")
			.append("\",\"carat\":\"")
			.append(result[6]!=null? result[6]:"")
			.append("\",\"balcarat\":\"")
			.append(result[7]!=null? result[7]:"")
			.append("\",\"qualitynm\":\"")
			.append(result[8]!=null? result[8]:"")
			.append("\",\"sizegroupnm\":\"")
			.append(result[9]!=null? result[9]:"")
			.append("\",\"rate\":\"")
			.append(result[10]!=null? result[10]:"")
			.append("\",\"adjCarat\":\"")
			.append("0.0")
			.append("\"},");
		    
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	

	
	
	@ResponseBody
	@RequestMapping(value = "/vAddStnDt/shapeDropdown")
	public String shapeDropdown(@RequestParam(value = "vAddMtId", required = false) Integer vAddMtId) {
		
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		Map<Integer, String> shapeMap = vAddStnDtService.getShapeList(vAddMtId);
		
		if(shapeMap.size()>0){
			sb.append("<select id=\"shapeId\" name=\"shapeId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Shape</option>");
			for (Object key : shapeMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(shapeMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		else{
			sb.append("<select id=\"shapeId\" name=\"shapeId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Shape</option>");
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		
		return retVal;
	}	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/vAddStnDt/qualityDropdown")
	public String qualityDropdown(@RequestParam(value = "vAddMtId", required = false) Integer vAddMtId) {
		
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		Map<Integer, String> qualityMap = vAddStnDtService.getQualityList(vAddMtId);
		
		if(qualityMap.size()>0){
			sb.append("<select id=\"qualityId\" name=\"qualityId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Quality</option>");
			for (Object key : qualityMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(qualityMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		else{
			sb.append("<select id=\"qualityId\" name=\"qualityId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Quality</option>");
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		
		return retVal;
	}	
	
	
	@ResponseBody
	@RequestMapping(value = "/vAddStnDt/updateStoneRate")
	public String updateStoneRate(@RequestParam(value = "vAddMtId", required = false) Integer vAddMtId,
			@RequestParam(value = "shapeId", required = false) Integer shapeId,
			@RequestParam(value = "qualityId", required = false) Integer qualityId,
			@RequestParam(value = "fromRate", required = false) Double fromRate,
			@RequestParam(value = "toRate", required = false) Double toRate,
			@RequestParam(value = "sizeGroupId", required = false) Integer sizeGroupId,
			Principal principal) {
		
		
		
		vAddDtService.updateQualityStoneRate(vAddMtId,shapeId,qualityId, fromRate, toRate, principal,sizeGroupId);
		
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/vAddStnDt/sizeGroupDropdown")
	public String sizeGroupDropdown(@RequestParam(value = "vAddMtId", required = false) Integer vAddMtId) {
		
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		Map<Integer, String> sizeGroupMap = vAddStnDtService.getSizeGroupList(vAddMtId);
		
		if(sizeGroupMap.size()>0){
			sb.append("<select id=\"sizeGroupId\" name=\"sizeGroupId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Size Group</option>");
			for (Object key : sizeGroupMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(sizeGroupMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		else{
			sb.append("<select id=\"sizeGroupId\" name=\"sizeGroupId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Size Group</option>");
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		
		return retVal;
	}	
	
	

}

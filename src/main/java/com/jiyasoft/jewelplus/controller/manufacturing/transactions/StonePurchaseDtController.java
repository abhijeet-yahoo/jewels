package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseMt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStonePurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStonePurchaseMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class StonePurchaseDtController {
	
	@Autowired
	private IStonePurchaseMtService stonePurchaseMtService;
	
	@Autowired
	private IStonePurchaseDtService stonePurchaseDtService;
	
	
	@RequestMapping("/stonePurchaseDt/listing")
	@ResponseBody
	public String stonePurchaseDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
		Principal principal)
			 {
		
	
		StringBuilder sb = new StringBuilder();
		StonePurchaseMt stonePurchaseMt = stonePurchaseMtService.findOne(mtId);
		
		List<StonePurchaseDt> stonePurchaseDts = stonePurchaseDtService.findByStonePurchaseMtAndDeactive(stonePurchaseMt, false);
		
		sb.append("{\"total\":").append(stonePurchaseDts.size()).append(",\"rows\": [");
			
		if(stonePurchaseDts.size() > 0){
				for (StonePurchaseDt stonePurchaseDt : stonePurchaseDts) {
					sb.append("{\"id\":\"")
							.append(stonePurchaseDt.getId())
							.append("\",\"stoneType\":\"")
							.append((stonePurchaseDt.getStoneType() != null ? stonePurchaseDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((stonePurchaseDt.getShape() != null ? stonePurchaseDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((stonePurchaseDt.getSubShape() != null ? stonePurchaseDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((stonePurchaseDt.getQuality() != null ? stonePurchaseDt.getQuality().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((stonePurchaseDt.getSize() != null ? stonePurchaseDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(stonePurchaseDt.getSieve())	
							.append("\",\"department\":\"")
							.append((stonePurchaseDt.getDepartment() != null ? stonePurchaseDt.getDepartment().getName() : ""))					
							.append("\",\"sizeGroupStr\":\"")
							.append((stonePurchaseDt.getSizeGroup() != null ? stonePurchaseDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(stonePurchaseDt.getStone())
							.append("\",\"balStone\":\"")
							.append(stonePurchaseDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(stonePurchaseDt.getCarat())
							.append("\",\"diffCarat\":\"")
							.append(stonePurchaseDt.getDiffCarat())
							.append("\",\"balCarat\":\"")
							.append(stonePurchaseDt.getBalCarat())
							.append("\",\"rate\":\"")
							.append(stonePurchaseDt.getRate())
							.append("\",\"amount\":\"")
							.append(stonePurchaseDt.getAmount())	
							.append("\",\"packetNo\":\"")
							.append(stonePurchaseDt.getPacketNo() !=null ? stonePurchaseDt.getPacketNo() : ""  )	
							.append("\",\"remark\":\"")
							.append((stonePurchaseDt.getRemark() != null ? stonePurchaseDt.getRemark() : ""));
									
							sb.append("\",\"action1\":\"");
						
							sb.append("<a href='javascript:editStonePurchaseDt(")
							.append(stonePurchaseDt.getId());
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
							sb.append("\",\"action2\":\"");
							sb.append("<a href='javascript:deleteStonePurchaseDt(event,")
							  .append(stonePurchaseDt.getId()).append(", 0);' href='javascript:void(0);'");
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(stonePurchaseDt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
							sb.append("\"},");
						
				}
				
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	@RequestMapping(value = "/stonePurchaseDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("stonePurchaseDt") StonePurchaseDt stonePurchaseDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vStnPurcMtId", required = true) Integer vStnPurcMtId,
			RedirectAttributes redirectAttributes, Principal principal) {
	
		String retVal = "-1";
		
			retVal= stonePurchaseDtService.stonePurchaseSave(stonePurchaseDt, id, vStnPurcMtId, principal); 
					
	
		return retVal;			
		}
	
	
	@ResponseBody
	@RequestMapping(value = "/stonePurchaseDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		StonePurchaseDt stonePurchaseDt = stonePurchaseDtService.findOne(id);
		if(stonePurchaseDt.getBalCarat() < stonePurchaseDt.getCarat()){
			return "-2";
		}
		

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalStnPurcDtId", stonePurchaseDt.getId());
		jsonObject.put("stoneType\\.id", stonePurchaseDt.getStoneType().getId());
		jsonObject.put("shape\\.id", stonePurchaseDt.getShape().getId());
		jsonObject.put("subShape\\.id", stonePurchaseDt.getSubShape() != null ? stonePurchaseDt.getSubShape().getId() : "");
		jsonObject.put("quality", stonePurchaseDt.getQuality().getId());
		jsonObject.put("size", stonePurchaseDt.getSize());
		jsonObject.put("vSieve", stonePurchaseDt.getSieve() != null ? stonePurchaseDt.getSieve() :"");
		jsonObject.put("vSizeGroupStr", stonePurchaseDt.getSizeGroup() !=null ?  stonePurchaseDt.getSizeGroup().getName() : "");
		jsonObject.put("stone", stonePurchaseDt.getStone());
		jsonObject.put("carat", stonePurchaseDt.getCarat());
		jsonObject.put("diffCarat", stonePurchaseDt.getDiffCarat());
		jsonObject.put("rate", stonePurchaseDt.getRate());
		jsonObject.put("amount", stonePurchaseDt.getAmount());
		jsonObject.put("packetNo", stonePurchaseDt.getPacketNo());
		jsonObject.put("remark", stonePurchaseDt.getRemark());
		jsonObject.put("vStnPurcMtId", stonePurchaseDt.getStonePurchaseMt().getId());
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/stonePurchaseDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = stonePurchaseDtService.stonePurchaseDtDelete(id, principal);
		return retVal;
	}


}

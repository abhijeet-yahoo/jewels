
package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostingMtTransferController {


	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private ITranMtService tranMtService;
	
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	

	@ModelAttribute("costingMt")
	public CostingMt construct() {
		return new CostingMt();
	}

	@ModelAttribute("tranMt")
	public TranMt constructTranMt() {
		return new TranMt();
	}

	@RequestMapping("/transferCosting")
	public String costing(Model model) {
		model = populateModel(model);
		return "costingMtTransfer";
	}

	private Model populateModel(Model model) {
		model.addAttribute("departmentMap",departmentService.getDepartmentList());
		return model;
	}

	
	@RequestMapping("/costingMtTransfer/listing")
	@ResponseBody
	public String costingMtTransferListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			@RequestParam(value = "partyFlg", required = false) Boolean partyFlg,
			@RequestParam(value = "partyId", required = false) Integer partyId) {

		return tranMtService.costingTransferListing(limit, offset, sort, order, search, deptId, partyFlg, partyId);
	}
	
	

	@RequestMapping(value = "/transfer/costing", method = RequestMethod.POST)
	@ResponseBody
	public String addEditCastingDt(
			@Valid @ModelAttribute("tranMt") TranMt tranMt,
			BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "pODIds") String pOIds, 
			@RequestParam(value = "costingMtId") Integer costingMtId,
			@RequestParam(value= "vSetNo") Integer setNo,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "transferCosting";
		}

		
		if(pOIds.length() == 0){
			retVal = "-1";
		}else{
		
			retVal =costingMtService.addBagInCosting(pOIds, costingMtId,principal,setNo,netWtWithCompFlg);	
				
		} // ending the main if
		

		return retVal;

	}
	
	
	@RequestMapping("/costing/itemNo")
	@ResponseBody
	public String usedMetalStockCheck() {
		
		StringBuilder sb = new StringBuilder();
		String itemNo = bagMtService.getMaxItemno();
		
		if(itemNo != null){
			sb.append(itemNo);
		}else{
			sb.append("no item found");
		}
		
		return sb.toString();

	}
	
	
	@RequestMapping("/costing/updateItemNoInTable")
	@ResponseBody
	public String updateItemNo(
			@RequestParam(value="Idd") String idd,
			@RequestParam(value="ItemNo") String itemNo) {

		String retVal ="sucessfull";
		
		String vIdd[] = idd.split(",");
		String vItemNo[] = itemNo.split(",");
		
		for(int i=0;i<vIdd.length;i++){
			
			BagMt bagMt = bagMtService.findOne(Integer.parseInt(vIdd[i]));
			
			if(vItemNo[i].equalsIgnoreCase("NO TAG") || vItemNo[i].equalsIgnoreCase("NOTAG")){
				System.out.println("skip");
			}else{
				bagMt.setItemNo(vItemNo[i].trim());
				bagMtService.save(bagMt);
			}
		}
		
		return retVal;

	}
	
	
	
	@RequestMapping("/costing/checkItemNo")
	@ResponseBody
	public String checkItemNo(
			@RequestParam(value="ItemNo") String itemNo,
			@RequestParam(value="bagId") Integer bagId,
			@RequestParam(value="deptId") Integer deptId) {
	
		StringBuilder sb = new StringBuilder();
		Boolean retVal = false;
		
		BagMt bagMtSingle = bagMtService.findOne(bagId);
		
		
		List<BagMt> bagMt = bagMtService.findByItemNo(itemNo.trim());
		
		if(bagMt.size() <= 0){
			BagMt bagMt2 = bagMtService.findOne(bagId);
			bagMt2.setItemNo(itemNo.trim());
			bagMt2.setItemNoDeptId(deptId);
			bagMt2.setItemNoDate(new java.util.Date());
			bagMtService.save(bagMt2);
			retVal = true;
		}else{
			
			if(bagMt.get(0).getOrderDt().getId().equals(bagMtSingle.getOrderDt().getId())){
				BagMt bagMt2 = bagMtService.findOne(bagId);
				bagMt2.setItemNo(itemNo.trim());
				bagMt2.setItemNoDeptId(deptId);
				bagMt2.setItemNoDate(new java.util.Date());
				bagMtService.save(bagMt2);
				retVal = true;	
			}else{
				retVal = false;
			}
			
			
		}
		
		
		
		
		
		sb.append(retVal);
		return sb.toString();

	}
	
	
	
	

}

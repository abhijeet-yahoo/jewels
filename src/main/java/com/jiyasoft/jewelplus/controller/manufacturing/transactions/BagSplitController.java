package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class BagSplitController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@ModelAttribute("tranMt")
	private TranMt construct(){
		return new TranMt();
	}
	
	
	
	@RequestMapping("/bagSplit")
	public String bagSplit(Model model,Principal principal){
		model = populateModel(model, principal);
		return "bagSplit";
	}
	
	
	
	public Model populateModel(Model model,Principal principal){
		
		User user = userService.findByName(principal.getName());
		model.addAttribute("departmenttMap",userDeptTrfRightService.getDepartmentListFromUser(user.getId()));
		
		return model;
		
	}
	
	
	

	
	@RequestMapping("/splitQty/deptTo")
	@ResponseBody
	public String popDeptTo(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@ModelAttribute("tranMt") TranMt tranMt, Principal principal) {
		
		User user = userService.findByName(principal.getName());

		return userDeptTrfRightService.getToDepartmentListDropDown(user.getId(), departmentId,"deptTo.id");
	}
	
	
	@RequestMapping("splitQty/validateTranDtList")
	@ResponseBody
	public String splitValidation(@RequestParam(value="departmentId", required = true) Integer deptId,
			@RequestParam(value="bagName" , required=true) String bagName){
		
		String retVal = "-1";
		
		Department department = departmentService.findOne(deptId);
		BagMt bagMt = bagMtService.findByName(bagName.trim());
		
		TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
		
		List<TranDt> tranDts;
		
		if(tranMt != null){
			tranDts =  tranDtService.findByTranMtAndCurrStk(tranMt, true);
			
			if(tranDts.size() > 0){
				retVal = "-3";
			}
			
		}else{
			retVal = "-2";
		}
		
		
		return retVal;
	}
	
	
	
	@RequestMapping("/splitQty/display")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "BagNo", required = false) String BagNo,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@ModelAttribute("tranMt") TranMt tranMt) {

		String str = null;
		
		if(departmentId != null){
			BagMt bagMt = bagMtService.findByName(BagNo);
			Department department = departmentService.findOne(departmentId);
			TranMt tranMtt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
				
			if(tranMtt != null){
				
				
				OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());

				String uploadFilePath = "/jewels/"+ uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";

				String img = orderDt.getDesign().getDefaultImage();
				String imgName = orderDt.getDesign().getImage1();
				if (img != null && img.equals("2")) {
					imgName = orderDt.getDesign().getImage2();
				} else if (img != null && img.equals("3")) {
					imgName = orderDt.getDesign().getImage3();
				}
				
				 str = "" 
						+ tranMtt.getOrderMt().getParty().getPartyCode() + "#"
						+ tranMtt.getOrderMt().getInvNo() + "#"
						+ tranMtt.getOrderMt().getInvDate() + "#"
						+ tranMtt.getOrderMt().getOrderType().getName() + "#"
						+ tranMtt.getOrderMt().getDispatchDate() + "#"
						+ tranMtt.getOrderDt().getDesign().getMainStyleNo() + "#"
						/*+ tranMtt.getOrderDt().getPurity().getName() + "#"
						+ tranMtt.getOrderDt().getColor().getName() + "#"*/
						+ tranMtt.getPcs() + "#" 
						+ tranMtt.getRecWt() + "#"
						+ uploadFilePath + imgName;
					//System.out.println("\n IIIIIIIZZZZZZZZZZZZZZZ ==   ==  ==   imgName = "+uploadFilePath + imgName+"\n");
				}else{
					str = "-1";
				}
		}else{
			str = "-2";
		}
		
		return str;
	}

	
	
	@RequestMapping("/splitQty/list")
	@ResponseBody
	public String bagList(@RequestParam(value = "term", required = false) String name) {
		Page<BagMt> bagMtList = bagMtService.findByName(15, 0, "name", "ASC", name.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (BagMt bagMt : bagMtList) {
			sb.append("\"")
				.append(bagMt.getName())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	
	
	
}

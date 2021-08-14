package com.jiyasoft.jewelplus.controller.manufacturing.transactions;


import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class DiamondAllocationAgainstOrderController {

	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	private IDepartmentService departmentService;
	
	

	@RequestMapping("/diamondAllocationAgainstOrder")
	public String users(Principal principal) {
	
		return "diamondAllocationAgainstOrder";
	}
	
	@RequestMapping("/diamondDeAllocation")
	public String diamondDeAllocation(Principal principal) {
	
		return "diamondDeAllocation";
	}
	
	
	@RequestMapping("/diamondAllocation/listing")
	@ResponseBody
	public String orderStnDtListing(Model model,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			Principal principal) {
		
		
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo,false );
		
		Department department = departmentService.findByName("Bagging");
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_diamondAllocationAgainstOrder(?,?) }");
		query.setParameter(1,orderMt.getId());
		query.setParameter(2,department.getId());
		
		
		List<Object[]> dtList = query.getResultList();
		
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray =  new JSONArray();

		int srno =1;
		for (Object[] list : dtList) {
						
			
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("stoneType", list[5] != null ? list[5] : "");
			jsonObj.put("shape", list[6] != null ? list[6] : "");
			jsonObj.put("quality", list[7] != null ? list[7] : "");
			jsonObj.put("size", list[8] != null ? list[8] : "");
			jsonObj.put("sieve", list[9] != null ? list[9] : "");
			jsonObj.put("sizeGroup", list[10] != null ? list[10] : "");
			jsonObj.put("totCarat", list[11] != null ? list[11] : "");
			jsonObj.put("totStone", list[12] != null ? list[12] : "");
			jsonObj.put("stkCarat", list[13] != null ? list[13] : "");
			jsonObj.put("stkStone", list[14] != null ? list[14] : "");
			jsonObj.put("allocateCarat", list[15] != null ? list[15] : "");
			jsonObj.put("allocateStone", list[16] != null ? list[16] : "");
			jsonObj.put("issueCarat", "");
			jsonObj.put("issueStone", "");
			jsonObj.put("srno", srno);
			
			srno++ ;
			
			jsonArray.put(jsonObj);
		}	
		
		jsonObject.put("total", dtList.size());
		jsonObject.put("rows", jsonArray);
		 	 
			
		return jsonObject.toString();
	}
	
	@RequestMapping("/diamondDeAllocation/listing")
	@ResponseBody
	public String diamondDeAllocationListing(Model model,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			Principal principal) {
		
		
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo,false );
		
		Department department = departmentService.findByName("Bagging");
		
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_diamondAllocationStock(?,?) }");
		query.setParameter(1,orderMt.getId());
		query.setParameter(2,department.getId());
		
		
		List<Object[]> dtList = query.getResultList();
		
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray =  new JSONArray();

		int srno =1;
		for (Object[] list : dtList) {
						
			
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("stoneType", list[5] != null ? list[5] : "");
			jsonObj.put("shape", list[6] != null ? list[6] : "");
			jsonObj.put("quality", list[7] != null ? list[7] : "");
			jsonObj.put("size", list[8] != null ? list[8] : "");
			jsonObj.put("sieve", list[9] != null ? list[9] : "");
			jsonObj.put("sizeGroup", list[10] != null ? list[10] : "");
			jsonObj.put("allocateCarat", list[11] != null ? list[11] : "");
			jsonObj.put("allocateStone", list[12] != null ? list[12] : "");
			jsonObj.put("stonetranId", list[13] != null ? list[13] : "");
			jsonObj.put("avgRate", list[15] != null ? list[15] : "");
			jsonObj.put("issueCarat", "");
			jsonObj.put("issueStone", "");
			jsonObj.put("srno", srno);
			
			srno++ ;
			
			jsonArray.put(jsonObj);
		}	
		
		jsonObject.put("total", dtList.size());
		jsonObject.put("rows", jsonArray);
		 	 
			
		return jsonObject.toString();
	}
	
	
	
}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class TranMtController {

	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IOrderMtService orderMtService;


	@Autowired
	private IDepartmentService departmentService;


	@ModelAttribute("tranMt")
	public TranMt construct() {
		return new TranMt();
	}

	@RequestMapping("/tranMt")
	public String users(Model model) {
		//model.addAttribute("tranMt", tranMtService.findAll());
		return "tranMt";
	}
	
	
	
	@RequestMapping("/tranMtMarketing")
	public String marketingTranMt(Model model) {
		
		return "tranMtMarketing";
	}

	@RequestMapping("/tranMt/listing")
	@ResponseBody
	public String tranMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

		/*StringBuilder sb = new StringBuilder();
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo,false);

		Long rowCount = null;
		if (orderMt != null) {
			if (search == null) {
				search = "" + orderMt.getId();
			}
			rowCount = tranMtService.count(sort, search, false);

			sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

			List<TranMt> tranMts = tranMtService.findAll();

			for (TranMt tranMt : tranMts) {
				sb.append("{\"id\":\"")
						.append(tranMt.getId())
						.append("\",\"deactive\":\"")
						.append((tranMt.getDeactive() == null ? "Active"
								: (tranMt.getDeactive() ? "Deactive" : "Active")))
						.append("\",\"deactiveDt\":\"")
						.append((tranMt.getDeactiveDt() == null ? "" : tranMt
								.getDeactiveDt()))
						.append("\",\"action1\":\"")
						.append("<a href='<spring:url value='/manufacturing/transactions/tranMt/edit/")
						.append(tranMt.getId())
						.append(".html' />' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
						.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='<spring:url value='/manufacturing/transactions/tranMt/delete/")
						.append(tranMt.getId())
						.append(".html' />' class='btn btn-xs btn-danger triggerRemove")
						.append(tranMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";*/

		return null;
	}

	@RequestMapping("/tranMt/add")
	public String add(Model model) {

		model = populateModel(model);
		return "tranMt/add";
	}

	public Model populateModel(Model model) {

		model.addAttribute("departmentMap",
				departmentService.getDepartmentList());
		return model;

	}

	@RequestMapping(value = "/tranMt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("tranMt") TranMt tranMt,
			BindingResult result,
			@RequestParam(value = "pODIds") String pOIds,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		synchronized (this) {
			
			String retVal = "";

			if (result.hasErrors()) {
				return "tranMt/add";
			}
			
			if(pOIds ==""){
				return "error : please select atleast one record to transfer";
			}
			retVal = tranMtService.setOpeningTransfer(pOIds,principal,tranMt);
			
			return retVal;
			
		}
		
		
	

	}

	@RequestMapping("/tranMt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		TranMt tranMt = tranMtService.findOne(id);
		model.addAttribute("tranMt", tranMt);
		model = populateModel(model);
		return "tranMt/add";
	}

	@RequestMapping("/tranMt/delete/{id}")
	public String delete(@PathVariable int id) {
		tranMtService.delete(id);

		return "redirect:/manufacturing/transactions/tranMt.html";
	}

	@RequestMapping("/tranMtAvailable")
	@ResponseBody
	public String tranMtAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean tranMtAvailable = true;

		/*
		 * if (id == null) { tranMtAvailable = (tranMtService.findByName(name)
		 * == null); } else { TranMt tranMt = tranMtService.findOne(id); if
		 * (!(name.equalsIgnoreCase(tranMt.getName()))) { tranMtAvailable =
		 * (tranMtService.findByName(name) == null); } }
		 */
		return tranMtAvailable.toString();
	}

	@RequestMapping("/tran/bag/list")
	@ResponseBody
	public String bagNoList(
			@RequestParam(value = "term", required = false) String invNo) {

		Page<OrderMt> orderMtList = orderMtService.findByInvNo(15, 0, "invNo",
				"ASC", invNo.toUpperCase(), true);

		StringBuilder sb = new StringBuilder();

		for (OrderMt orderMt : orderMtList) {
			sb.append("\"").append(orderMt.getInvNo()).append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]";

		return str;
	}

	@RequestMapping("/tranMt/ids")
	@ResponseBody
	public String invoiceList(@RequestParam(value = "invNo") String invNo,
			@ModelAttribute("tranMt") TranMt tranMt) {

		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(invNo,false);
		StringBuilder sb = new StringBuilder();

		sb.append(orderMt.getId());

		return sb.toString();

	}

}

package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.common.CommonUtils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IDashboardService;

@RequestMapping("/manufacturing/masters")
@Controller
public class DashboardController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IDashboardService dashboardService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping("/dashboard")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("dashboard");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("groupByMap", CommonUtils.getDisplayGroupBy());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "dashboard";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		return "dashboard";
	}
	
	
	@RequestMapping("/dashboardFilter")
	public String dashboardFilter() {
		return "dashboardFilter";
	}
	
	@RequestMapping("/dashboard/getSalesOrdersData")
	@ResponseBody
	public String getSalesOrdersData(Model model,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pOrdDivisionIds", required = false) String pOrdDivisionIds,
			@RequestParam(value = "pRegionIds", required = false) String pRegionIds,
			@RequestParam(value = "pCustomerTypeIds", required = false) String pCustomerTypeIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pGroupBys", required = false) String pGroupBys) throws ParseException {

	
		
		return  dashboardService.getSalesOrderData(pOrderTypeIds, pOrderIds, pFromOrdDate, pToOrdDate, pOrdDivisionIds, pRegionIds, pCustomerTypeIds, pGroupBys);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/displayGrouByFormat")
	public String displayGrouByFormat(String reportNm) {
		
		String retVal="";
		StringBuilder sb =  new StringBuilder();
		
		Map<String, String> groupByMap = CommonUtils.getDisplayGroupByList();
		
			sb.append("<select id=\"groupById\" name=\"groupById\" class=\"form-control\" onchange=\"javascript:changeGroupByIdFormat(this.value)\">");
			sb.append("<option value=\"\">- Select Group By -</option>");
			for (Object key : groupByMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(groupByMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/displayTranStatusFormat")
	public String displayTranStatusFormat() {
		
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		
		Map<String, String> tranStatusMap = CommonUtils.getTransactionStatusList();
		
			sb.append("<select id=\"tranStatusId\" name=\"tranStatusId\" class=\"form-control\" \">");
			sb.append("<option value=\"\">- Select Status -</option>");
			for (Object key : tranStatusMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(tranStatusMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/displaySalesmanFormat")
	public String displaySalesmanFormat() {
		
		return employeeService.getSalesmanListDropDown();
	}
	
	
	
	@RequestMapping("/dashboard/salesOrder/listing")
	@ResponseBody
	public String salesOrderListing(Model model,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pOrdDivisionIds", required = false) String pOrdDivisionIds,
			@RequestParam(value = "pRegionIds", required = false) String pRegionIds,
			@RequestParam(value = "pCustomerTypeIds", required = false) String pCustomerTypeIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pGroupBys", required = false) String pGroupBys,
			@RequestParam(value = "pClientIds", required = false) String pClientIds
			) throws ParseException {

	
		
		return  dashboardService.salesOrderList(pOrderTypeIds, pOrderIds, pFromOrdDate, pToOrdDate, pOrdDivisionIds, pRegionIds, pCustomerTypeIds, pGroupBys,pClientIds);
	}
	
	
	
	@RequestMapping("/dashboard/getSalesInvoiceData")
	@ResponseBody
	public String getSalesInvoiceData(Model model,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pOrdDivisionIds", required = false) String pOrdDivisionIds,
			@RequestParam(value = "pRegionIds", required = false) String pRegionIds,
			@RequestParam(value = "pCustomerTypeIds", required = false) String pCustomerTypeIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pGroupBys", required = false) String pGroupBys) throws ParseException {

	
		
		return  dashboardService.getSaleInvoiceData(pOrderTypeIds, pOrderIds, pFromOrdDate, pToOrdDate, pOrdDivisionIds, pRegionIds, pCustomerTypeIds, pGroupBys);
	}
	
	

	@RequestMapping("/dashboard/salesInvoice/listing")
	@ResponseBody
	public String salesInvoiceListing(Model model,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pRegionIds", required = false) String pRegionIds,
			@RequestParam(value = "pCustomerTypeIds", required = false) String pCustomerTypeIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pGroupBys", required = false) String pGroupBys,
			@RequestParam(value = "pClientIds", required = false) String pClientIds) throws ParseException {
		
		return  dashboardService.salesInvoiceList(pOrderTypeIds, pOrderIds, pFromOrdDate, pToOrdDate, pRegionIds, pCustomerTypeIds, pGroupBys,pClientIds);
	}
	
	
	
	@RequestMapping("/dashboard/DiamondStock/listing")
	@ResponseBody
	public String diamondStockListing(Model model,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pTranStatus", required = false) String pTranStatus) throws ParseException {
		
		return  dashboardService.diamondStockList(pToOrdDate, pTranStatus);
	}
	
	
	@RequestMapping("/dashboard/getSalesQuotationData")
	@ResponseBody
	public String getSalesQuotationData(Model model){
		
		return  dashboardService.getSalesQuotationData();
				
	}
	
	
	@RequestMapping("/dashboard/wipStock/listing")
	@ResponseBody
	public String wipStockListing(Model model,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate) throws ParseException {
		
		return  dashboardService.wipStockList(pToOrdDate);
	}
	
	@RequestMapping("/dashboard/finishGoodsSock/listing")
	@ResponseBody
	public String finishGoodsStockListing(Model model,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate) throws ParseException {
		
		return  dashboardService.finishGoodsStockList(pToOrdDate);
	}
	
	
	@RequestMapping("/dashboard/getFgIssueData")
	@ResponseBody
	public String getFgIssueData(Model model,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pOrdDivisionIds", required = false) String pOrdDivisionIds,
			@RequestParam(value = "pRegionIds", required = false) String pRegionIds,
			@RequestParam(value = "pCustomerTypeIds", required = false) String pCustomerTypeIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pGroupBys", required = false) String pGroupBys,
			@RequestParam(value = "pClientIds", required = false) String pClientIds) throws ParseException {

	
		
		return  dashboardService.getFgIssueData(pOrderTypeIds, pOrderIds, pFromOrdDate, pToOrdDate, pRegionIds, pCustomerTypeIds, pGroupBys, pClientIds);
	}
	
	
	@RequestMapping("/dashboard/getFgIssueListing")
	@ResponseBody
	public String getFgIssueListing(Model model,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pOrdDivisionIds", required = false) String pOrdDivisionIds,
			@RequestParam(value = "pRegionIds", required = false) String pRegionIds,
			@RequestParam(value = "pCustomerTypeIds", required = false) String pCustomerTypeIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pGroupBys", required = false) String pGroupBys,
			@RequestParam(value = "pClientIds", required = false) String pClientIds) throws ParseException {

	
		
		return  dashboardService.getFgIssueListing(pOrderTypeIds, pOrderIds, pFromOrdDate, pToOrdDate, pRegionIds, pCustomerTypeIds, pGroupBys, pClientIds);
	}
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	

}

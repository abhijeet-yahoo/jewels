package com.jiyasoft.jewelplus.controller.marketing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;

@RequestMapping("/marketing/transactions")
@Controller
public class StockMtController {
	

	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("stockMt")
	public StockMt constructMt() {
		return new StockMt();
	}
	
	
	@RequestMapping("/stockUpload")
	public String excelFilePage(Model model) {
		model.addAttribute("tableDisp", "no");
		return "stockUpload";
	}
	
	@RequestMapping("/stockPickupForPacking")
	public String stockPickupForPacking(Model model) {
		
		return "stockPickupForPacking";
	}
	

	@RequestMapping(value = "/stockMt/excelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model, @RequestParam("excelfile") MultipartFile excelfile, HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile, RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		String retVal = "yes";
		
		synchronized (this) {
			retVal = stockMtService.stockExcelUpload(excelfile, session, tempExcelFile,principal);
			
		}
		
		model.addAttribute("tableDisp", "yes");
		model.addAttribute("retVal", retVal);
		
		return "stockUpload";
	}
	
	@RequestMapping("/stockExcel/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<StockExcel> stockExcels = (List<StockExcel>) httpSession.getAttribute("stockExcelSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(stockExcels.size()).append(",\"rows\": [");
		
		for(StockExcel stockExcel : stockExcels){
			
			sb.append("{\"memoParty\":\"")
			.append((stockExcel.getMemoParty() != null ? stockExcel.getMemoParty() : ""))
			.append("\",\"designNo\":\"")
			.append((stockExcel.getDesignNo() != null ? stockExcel.getDesignNo() : ""))
			.append("\",\"barcode\":\"")
			.append((stockExcel.getBarcode() != null ? stockExcel.getBarcode() : ""))
			.append("\",\"purity\":\"")
			.append((stockExcel.getPurity() != null ? stockExcel.getPurity() : ""))
			.append("\",\"color\":\"")
			.append((stockExcel.getColor() != null ? stockExcel.getColor() : ""))
			.append("\",\"category\":\"")
			.append((stockExcel.getCategory() != null ? stockExcel.getCategory() : ""))
			.append("\",\"shape\":\"")
			.append((stockExcel.getShape() != null ? stockExcel.getShape() : ""))
			.append("\",\"quality\":\"")
			.append((stockExcel.getQuality() != null ? stockExcel.getQuality() : ""))
			.append("\",\"department\":\"")
			.append((stockExcel.getDepartment() != null ? stockExcel.getDepartment() : ""))
			.append("\",\"stoneType\":\"")
			.append((stockExcel.getStonetype() != null ? stockExcel.getStonetype() : ""))
			.append("\",\"sieve\":\"")
			.append((stockExcel.getSieve() != null ? stockExcel.getSieve() : ""))
			.append("\",\"statusRemark\":\"")
			.append((stockExcel.getStatusRemark() != null ? stockExcel.getStatusRemark() : ""))
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	@RequestMapping("/barcodeSearch")
	public String barcodeSearch(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("barcodeSearch");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "barcodeSearch";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "barcodeSearch";
		}
	}
	
	
	@RequestMapping("/barcodeSearch/autoFill/list")
	@ResponseBody
	public String barcodeSearchAutoFillList(@RequestParam(value = "term", required = false) String barcode) {
				
		Integer limit = 15;
		
		if(barcode.length() >= 5){
			limit = 100;
		}
		
		
		
		Page<StockMt> stockList = stockMtService.barcodeSearchAutofill(limit, 0, "name", "ASC", barcode.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (StockMt stockMt : stockList) {
			sb.append("\"")
				.append(stockMt.getBarcode())
				.append("\", ");
		}
	
		
		

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	@RequestMapping("/stockMt/barcodeHistory")
	@ResponseBody
	public String barcodeHistory(@RequestParam(value = "barcode", required = false) String barcode) throws ParseException{
		
		return stockMtService.barcodeHistoryList(barcode);
		
	
}
	
	
	@RequestMapping("/stockMt/barcodeComponentList")
	@ResponseBody
	public String barcodeComponentList(@RequestParam(value = "barcode", required = false) String barcode) throws ParseException{
		
		return stockMtService.barcodeComponentList(barcode);
		
	
}
	
	
	@RequestMapping("/stockMt/barcodeStoneList")
	@ResponseBody
	public String barcodeStoneList(@RequestParam(value = "barcode", required = false) String barcode) throws ParseException{
		
		return stockMtService.barcodeHistoryStoneList(barcode);
		
	
}
	
	@RequestMapping("/barcodeAttachToStock")
	@ResponseBody
	public String barcodeAttachToStock(@RequestParam(value="bagTblData")String bagTblData,
					Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal =stockMtService.barcodeAttachToStock(principal,bagTblData);	
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	return retVal;

	}
	
	


}

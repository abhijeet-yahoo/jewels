package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCTranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class PDCTranMtController {

	@Autowired
	private IPDCTranMtService PDCTranMtService;
	
	@Autowired
	private IPDCService pdcService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@ModelAttribute("pdcTranMt")
	public PDCTranMt construct() {
		return new PDCTranMt();
	}

	@RequestMapping("/PDCTranMt")
	public String users(Model model) {
		model.addAttribute("departmentMap",departmentService.getDepartmentListForPdc());
		return "PDCTranMt";
	}

	
	
	@RequestMapping("/PDCTranMt/listing")
	@ResponseBody
	public String tranMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search) {

		StringBuilder sb = new StringBuilder();
       
		Long rowCount = null;
			rowCount = pdcService.count(sort, search, false);

			sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

			List<PDC> pdc= pdcService.findByCurrentStkAndDeactive(true, false);
			
			for (PDC pdcs : pdc) {
				String imgName = null;;
				imgName = pdcs.getDesign().getDefaultImage();
				
				sb.append("{\"id\":\"")
						.append(pdcs.getId())
						.append("\",\"styleId\":\"")
						.append(pdcs.getDesign().getMainStyleNo())
						.append("\",\"createdDt\":\"")
						.append(pdcs.getDateStr())
						.append("\",\"currentStk\":\"")
						.append(pdcs.getCurrentStk())
						.append("\",\"version\":\"")
						.append(pdcs.getDesign().getVersion())
						.append("\",\"category\":\"")
						.append(pdcs.getDesign().getCategory().getName())
						.append("\",\"purity\":\"")
						.append((pdcs.getPurity() != null ? pdcs.getPurity().getName() : ""))
						.append("\",\"uploadImage\":\"");
						if (imgName != null) {
							sb.append("<a href='/jewels/uploads/manufacturing/design/")
								.append(imgName) 
								.append("' data-lighter class='btn btn-xs btn-warning'>")
								.append("View Image</a>");
						}
						
						sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/pdctranmt/delete/")
						.append(pdcs.getId())
						.append(".html' class='btn btn-xs btn-danger triggerRemove")
						.append(pdcs.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");

			}

			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
			
			return str;
	}
	
	
	@RequestMapping("/PDCTranMt/add")
	public String add(Model model) {
		
		model = populateModel(model);
		return "PDCTranMt/add";
	}

	public Model populateModel(Model model) {

		model.addAttribute("departmentMap",
				departmentService.getDepartmentList());
		return model;

	}

	@RequestMapping(value = "/PDCTranMt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("PDCTranMt") PDCTranMt PDCTranMt,
			BindingResult result, @RequestParam(value = "deptId") Integer deptId,
		    @RequestParam(value ="pODIds") String pODIds,
			RedirectAttributes redirectAttributes, Principal principal) {


		String retVal = "1";

		if (result.hasErrors()) {
			return "PDCTranMt/add";
		}

		String[] data1 = pODIds.split(",");

		if(pODIds.length() < 0){
			return retVal = "-2";
		}
		

		Department department = departmentService.findByName("Opening");
		Department currDept = departmentService.findOne(deptId);
		
		for (int i = 0; i < data1.length; i++) {
			
			PDC pdc = pdcService.findOne(Integer.parseInt(data1[i]));

			java.sql.Time timeValue = new java.sql.Time(new java.util.Date().getTime());
			
				PDCTranMt pdcTranMtNew = new PDCTranMt();
				
				pdcTranMtNew.setDepartment(currDept);
				pdcTranMtNew.setDeptFrom(department);
				pdcTranMtNew.setDesign(pdc.getDesign());
				pdcTranMtNew.setPcs(1.0);
				pdcTranMtNew.setCurrStk(true);
				pdcTranMtNew.setCreatedDate(new java.util.Date());
				pdcTranMtNew.setDeactive(false);
				pdcTranMtNew.setCreatedBy(principal.getName());
				pdcTranMtNew.setCreatedDate(new java.util.Date());
				pdcTranMtNew.setTime(timeValue);

				//---updateing the PDC currstk---//
				
				pdc.setCurrentStk(false);
				pdc.setModiBy(principal.getName());
				pdc.setModiDate(new java.util.Date());
				
				PDCTranMtService.save(pdcTranMtNew,pdc);
				

		} // for loop

	
		return retVal;

	}

	
	
}

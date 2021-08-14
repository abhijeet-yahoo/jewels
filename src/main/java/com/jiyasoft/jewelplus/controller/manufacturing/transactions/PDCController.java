package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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



import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class PDCController {
	

	@Autowired
	private IPDCService pdcService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IPurityService purityService;

	@ModelAttribute("pdc")
	public PDC construct() {
		return new PDC();
	}

	@RequestMapping("/pdc")
	public String users(Model model) {
		model.addAttribute("purityMap", purityService.getPurityList());
		return "pdc";
	}
	
	
	
	@RequestMapping("/pdc/listing")
	@ResponseBody
	public String designListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search) {
	
		StringBuilder sb = new StringBuilder();
		Page<PDC> pdc = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
	
		
		if (search == null && sort == null) {
			rowCount = pdcService.count(sort, search, true);
			pdc = pdcService.findAll(limit, offset, sort, order, search);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("styleNo")) {
			rowCount = pdcService.count(sort, search, true);
			pdc = pdcService.findByStyleNo(limit, offset, sort, order, search, true);
		}else if (search != null && sort != null) {
			rowCount = 0L;
			pdc = new PageImpl<PDC>(new ArrayList<PDC>());
		} else {
			rowCount = pdcService.count(sort, search, true);
		    pdc = pdcService.findByStyleNo(limit, offset, sort, order, search, true);
		}
		
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
		
		
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
					
					sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editpdc(")
					.append(pdcs.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deletePdc(event,")
					.append(pdcs.getId())
					.append(","+pdcs.getCurrentStk())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(pdcs.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");

			
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		
		
		return str;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/pdc/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("pdc") PDC pdc,
			BindingResult result,@RequestParam("id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

			String retval = "-1";
			
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
			
				String styleNo = pdc.getDesign().getMainStyleNo();
				
				if(styleNo.indexOf("[") == -1){
					return retval = "-3";
				}
				
				String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
				String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
		
				Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
				
				if(design != null){
					pdc.setDesign(design);
				}else{
					return retval = "-2";
				}
		
				pdc.setCreatedBy(principal.getName());
				pdc.setCreatedDate(new java.util.Date());
				pdc.setModiBy(principal.getName());
				pdc.setModiDate(new java.util.Date());
				pdcService.save(pdc);
			}else{
				
				PDC pdcEdit = pdcService.findOne(id);
				pdcEdit.setModiBy(principal.getName());
				pdcEdit.setModiDate(new java.util.Date());
				pdcEdit.setPurity(pdc.getPurity());
				pdcService.save(pdcEdit);
				retval = "-4";
			}
			
			

		return retval;

	}
	
	
	
	@ResponseBody
	@RequestMapping("/pdc/editValid")
	public String editValidation(Principal principal){
		Boolean flag = false;
		if(principal.getName().equalsIgnoreCase("Administrator")){
			flag = true;
		}
		return flag.toString();
	}
	

	@RequestMapping("/pdc/edit/{id}")
	public String edit(@PathVariable("id") Integer id,Model model){
		PDC pdc = pdcService.findOne(id);
		model.addAttribute("pdc", pdc);
		model.addAttribute("purityMap", purityService.getPurityList());
		return "pdc/edit";
	}
	
	
	@ResponseBody
	@RequestMapping("/pdc/delete/{id}")
	public String delete(@PathVariable int id) {
		
		String retVal = "-1";
		
		PDC pdc = pdcService.findOne(id);
		
		if(pdc.getCurrentStk().equals(true)){
			 pdcService.delete(id);
		}else{
			retVal = "-2";
		}
		
		return retVal;
	}
	
	
	@RequestMapping("/pdc/styleNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "design.styleNo", required = false) String StyleNo) {
		
		Boolean styleNoAvailable = true;
		
		if(StyleNo != null){
			
			if(StyleNo.indexOf("[") == -1){
				styleNoAvailable = true;
				return styleNoAvailable.toString();
			}
			
			
			String styleNo = StyleNo;	
			String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
			String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
			
			Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
		
			PDC pdc=pdcService.findByDesignAndDeactive(design, false);
		
			if(pdc != null){
				styleNoAvailable = false;
			}else{
				styleNoAvailable = true;
			}
		}else{
			styleNoAvailable = true;
		}
		
		
		return styleNoAvailable.toString();
	}
	
	
	
}

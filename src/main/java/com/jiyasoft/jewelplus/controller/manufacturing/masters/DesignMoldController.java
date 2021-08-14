package com.jiyasoft.jewelplus.controller.manufacturing.masters;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMold;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMoldService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMoldTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;


@RequestMapping("/manufacturing/masters")
@Controller
public class DesignMoldController {

	
	@Autowired
	private IDesignMoldService designMoldService;
	
	@Autowired
	private IDesignMoldTypeService designMoldTypeService;
	
	@Autowired
	private IDesignService designService;
	
	@ModelAttribute("designMold")
	public DesignMold construct(){
		return new DesignMold();
	}
	
	
	@RequestMapping("/designMold")
	public String designMold(Model model){
		model.addAttribute("designMoldTypeMap", designMoldTypeService.getDesignMoldTypeList());
		return "designMold";
	}
	
	
	@RequestMapping("/designMold/listing")
	@ResponseBody
	public String designMoldListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search) {
		

		StringBuilder sb = new StringBuilder();
		Page<DesignMold> designMolds = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
		if (search == null && sort == null) {
			rowCount = designMoldService.count(sort, search, true);
			designMolds = designMoldService.findAll(limit, offset, sort, order, search);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("design")) {
			rowCount = designMoldService.count(sort, search, true);
			designMolds = designMoldService.findByStyleNo(limit, offset, sort, order, search, true);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("designMoldType")) {
			rowCount = designMoldService.count(sort, search, true);
			designMolds = designMoldService.findByDesignMoldType(limit, offset, sort, order, search, true);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("rackNo")) {
			rowCount = designMoldService.count(sort, search, true);
			designMolds = designMoldService.findByRackNo(limit, offset, sort, order, search, true);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("drawerNo")) {
			rowCount = designMoldService.count(sort, search, true);
			designMolds = designMoldService.findByDrawer(limit, offset, sort, order, search, true);
		}else if (search != null && sort != null) {
			rowCount = 0L;
			designMolds = new PageImpl<DesignMold>(new ArrayList<DesignMold>());
		} else {
			rowCount = designMoldService.count(sort, search, true);
			designMolds = designMoldService.findByStyleNo(limit, offset, sort, order, search, true);
		}
		
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
		
		for (DesignMold designMold : designMolds) {
			
			String imgName = null;;
			imgName = designMold.getDesign().getDefaultImage();
			
			sb.append("{\"id\":\"")
					.append(designMold.getId())
					.append("\",\"design\":\"")
					.append((designMold.getDesign() != null ? designMold.getDesign().getMainStyleNo() : ""))
					.append("\",\"designMoldType\":\"")
					.append((designMold.getDesignMoldType() != null ? designMold.getDesignMoldType().getName() : ""))
					.append("\",\"drawerNo\":\"")
					.append(designMold.getDrawerNo())
					.append("\",\"rackNo\":\"")
					.append(designMold.getRackNo())
					.append("\",\"mouldQty\":\"")
					.append(designMold.getMouldQty() !=null ? designMold.getMouldQty() :"")
					.append("\",\"uploadImage\":\"");
					if (imgName != null) {
						sb.append("<a href='/jewels/uploads/manufacturing/design/")
							.append(imgName) 
							.append("' data-lighter class='btn btn-xs btn-warning'>")
							.append("View Image</a>");
					}
					
					sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editDesignMold(")
					.append(designMold.getId())
					.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteDesignMold(event,")
					.append(designMold.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(designMold.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
			}
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		return str;
	}

	
	@ResponseBody
	@RequestMapping(value = "/designMold/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("designMold") DesignMold designMold,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";
		
		if (result.hasErrors()) {
			return "designMold/add";
		}
		
		
		//String styleNo = designMold.getDesign().getStyleNo().substring(0,designMold.getDesign().getStyleNo().indexOf("["));
		//String version = designMold.getDesign().getStyleNo().substring(designMold.getDesign().getStyleNo().indexOf("[")+1,designMold.getDesign().getStyleNo().indexOf("]"));
		
	//	Design design = designService.findByStyleNoAndVersion(styleNo.trim(), version.trim());
		Design design = designService.findByMainStyleNoAndDeactive(designMold.getDesign().getMainStyleNo(), false);
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			designMold.setCreatedBy(principal.getName());
			designMold.setCreatedDate(new java.util.Date());
			designMold.setDesign(design);
		} else {
			designMold.setId(id);
			designMold.setDesign(design);
			designMold.setModiBy(principal.getName());
			designMold.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "-2";
		}

		designMoldService.save(designMold);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		return retVal;

	}
	
	
	
	
	@RequestMapping("/designMold/edit/{id}")
	public String edit(@PathVariable("id") Integer id,Model model){
		DesignMold designMold = designMoldService.findOne(id);
		model.addAttribute("designMold", designMold);
		model.addAttribute("designMoldTypeMap", designMoldTypeService.getDesignMoldTypeList());
		//model.addAttribute("designVersion", designMold.getDesign().getStyleNo()+" ["+designMold.getDesign().getVersion()+"] ");
		return "designMold/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/designMold/delete/{id}")
	public String delete(@PathVariable int id) {
		designMoldService.delete(id);
		return "-1";
	}
	
	
	
	
	
}

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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMoldType;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMoldTypeService;

@Controller
@RequestMapping("/manufacturing/masters")
public class DesignMoldTypeController {

	@Autowired
	private IDesignMoldTypeService designMoldTypeService;
	
	
	@ModelAttribute("designMoldType")
	public DesignMoldType construct() {
		return new DesignMoldType();
	}
	
	
	@RequestMapping("/designMoldType")
	public String designMoldType(){
		return "designMoldType";
	}
	
	
	@RequestMapping("/designMoldType/listing")
	@ResponseBody
	public String designMoldTypeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<DesignMoldType> designMoldTypes = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = designMoldTypeService.count(sort, search, false);
			designMoldTypes = designMoldTypeService.findByName(limit, offset, sort, order,search, false);
		} 
		else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = designMoldTypeService.count(sort, search, false);
			designMoldTypes = designMoldTypeService.findByName(limit, offset, sort, order,search, false);
		} 
		else if (search != null && sort != null) {
			rowCount = 0L;
			designMoldTypes = new PageImpl<DesignMoldType>(new ArrayList<DesignMoldType>());
		} 
		else {
			rowCount = designMoldTypeService.count(sort, search, false);
			designMoldTypes = designMoldTypeService.findByName(limit, offset, sort, order,search, false);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (DesignMoldType designMoldType : designMoldTypes) {
			
			sb.append("{\"id\":\"")
					.append(designMoldType.getId())
					.append("\",\"name\":\"")
					.append(designMoldType.getName())
					.append("\",\"createdBy\":\"")
					.append(designMoldType.getCreatedBy())
					.append("\",\"createdDt\":\"")
					.append(designMoldType.getDateStr())
					.append("\",\"deactive\":\"")
					.append(designMoldType.getDeactive() ? "Yes":"No")
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/masters/designMoldType/edit/")
					.append(designMoldType.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/designMoldType/delete/")
					.append(designMoldType.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(designMoldType.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;

	}
	
	
	@RequestMapping("/designMoldType/add")
	public String add(Model model) {
		return "designMoldType/add";
	}
	
	
	@RequestMapping(value = "/designMoldType/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("designMoldType") DesignMoldType designMoldType,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/designMoldType/add.html";

		if (result.hasErrors()) {
			return "designMoldType/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			designMoldType.setCreatedBy(principal.getName());
			designMoldType.setCreatedDate(new java.util.Date());
		} else {
			designMoldType.setId(id);
			designMoldType.setModiBy(principal.getName());
			designMoldType.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/designMoldType.html";
		}

		designMoldTypeService.save(designMoldType);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}
	
	
	
	@RequestMapping("/designMoldType/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		DesignMoldType designMoldType = designMoldTypeService.findOne(id);
		model.addAttribute("designMoldType", designMoldType);

		return "designMoldType/add";
	}

	@RequestMapping("/designMoldType/delete/{id}")
	public String delete(@PathVariable int id) {
		designMoldTypeService.delete(id);

		return "redirect:/manufacturing/masters/designMoldType.html";
	}

	@RequestMapping("/designMoldTypeAvailable")
	@ResponseBody
	public String designMoldTypeAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean designMoldTypeAvailable = true;

		if (id == null) {
			designMoldTypeAvailable = (designMoldTypeService.findByName(name) == null);
		} else {
			DesignMoldType designMoldType = designMoldTypeService.findOne(id);
			if (!(name.equalsIgnoreCase(designMoldType.getName()))) {
				designMoldTypeAvailable = (designMoldTypeService.findByName(name) == null);
			}
		}

		return designMoldTypeAvailable.toString();
	}
	
	
	
	
}

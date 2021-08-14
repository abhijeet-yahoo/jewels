package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StyleChange;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStyleChangeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class StyleChangeController {
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IStyleChangeService styleChangeService;

	@ModelAttribute
	public Design constructDesign() {
		return new Design();
	}

	@ModelAttribute
	public StyleChange constructStyle() {
		return new StyleChange();
	}

	@RequestMapping("/styleChange")
	public String styleChangeMap() {
		return "styleChange";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/styleChange/add" , method = RequestMethod.POST)
	public String addStyleChange(@ModelAttribute("styleChange") StyleChange styleChange,
			Principal principal){
		
		
		String retVal = "-1";
		
		Design design = designService.findByDesignNoAndDeactive(styleChange.getDesignNo().trim(),false);
		
		if(design != null){
			
			Design design2 = designService.findByStyleNoAndVersion(styleChange.getNewStyleNo().trim(), design.getVersion());
		
				if(design2 != null){
					return retVal = "-2";
				}else{
					design.setStyleNo(styleChange.getNewStyleNo());
					design.setModiBy(principal.getName());
					design.setModiDt(new java.util.Date());
					designService.save(design);
					
					
					StyleChange styleChangeOldRecord = styleChangeService.findByDesign(design);
					
					
						if(styleChangeOldRecord != null){
							
							styleChangeOldRecord.setOldStyleNo(styleChangeOldRecord.getNewStyleNo());
							styleChangeOldRecord.setNewStyleNo(styleChange.getNewStyleNo().trim());
							styleChangeOldRecord.setDesignNo(styleChange.getDesignNo().trim());
							styleChangeOldRecord.setModiBy(principal.getName());
							styleChangeOldRecord.setModiDate(new java.util.Date());
							styleChangeService.save(styleChangeOldRecord);
							
						}else{
							styleChange.setDesign(design);
							styleChange.setCreatedBy(principal.getName());
							styleChange.setCreatedDate(new java.util.Date());
							styleChangeService.save(styleChange);
						}
					
				}
			
		}else{
			
			return retVal = "-3";
			
		} // ending main if-else
		
		
		
		return retVal;
		
	}
	
	
	
	
	@RequestMapping("/designNo/forStyleChangeModel")
	@ResponseBody
	public String styleNoList(@RequestParam(value = "term", required = false) String designNo) {
		Page<Design> designList = designService.findByDesignNo(15, 0, "designNo", "ASC", designNo.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (Design design : designList) {
			sb.append("\"")
				.append(design.getDesignNo())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	
	
	
	
	
	

}

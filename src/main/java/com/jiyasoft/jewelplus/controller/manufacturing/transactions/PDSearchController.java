package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCTranMtService;


@Controller
@RequestMapping("/manufacturing/transactions")
public class PDSearchController {
	
	@Autowired
	private IPDCTranMtService pdcTranMtService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IPDCService pdcService;
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	
	@ModelAttribute("pdcTranMt")
	public PDCTranMt construct(){
		return new PDCTranMt();
	}
	
	
	
	@RequestMapping("/pdSearch")
	public String pdSearch(){
		return "pdSearch";
	}
	
	
	@ResponseBody
	@RequestMapping("/pdSearch/displayResult")
	public String pdDisplayResult(
			@RequestParam("styleNo") String styleNo,
			@RequestParam("version") String version){
		
		String retVal = "-1";
		
		Design design = designService.findByStyleNoAndVersion(styleNo.trim(), version.trim());
		
		if(design != null){
			String uploadFilePath = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
			String imgName = "";
			
			if(design.getDefaultImage() != null){
				imgName = uploadFilePath+design.getDefaultImage();
			}else{
				imgName = uploadFilePath+"blank.png";
			}
		
			PDCTranMt pdcTranMt = pdcTranMtService.findByDesignAndCurrStk(design, true);
			PDC pdc =  pdcService.findByDesignAndDeactive(design, false);
			
			
			if(pdcTranMt != null){
				retVal = pdc.getPurity().getName()+"#"
						+pdcTranMt.getDepartment().getName()+"#"
						+pdcTranMt.getRecWt()+"#"
						+imgName;
			}else{
				retVal = "-2";
			}
		}
		
		
		return retVal;
	}
	
	
	
	
	
	
	
}

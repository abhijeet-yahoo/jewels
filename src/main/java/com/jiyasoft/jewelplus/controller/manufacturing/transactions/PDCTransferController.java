package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPdProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpPdProductionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCTranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class PDCTransferController {


	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	
	@Autowired
	private IEmpPdProductionService empPdProductionService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IPDCTranMtService pdcTranMtService;
	
	@Autowired
	private IPDCService pdcService;
	
	
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	
	

	@ModelAttribute("pdcTranMt")
	public PDCTranMt constructPdcTranMt() {
		return new PDCTranMt();
	}

	@RequestMapping("/pdctransfer")
	public String users(Model model, Principal principal) {
		model = populateModel(model, principal);
	    return "pdctransfer";
	}
	

	
	public Model populateModel(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		model.addAttribute("departmenttMap",userDeptTrfRightService.getDepartmentListFromUserPdc(user.getId()));
		return model;

	}

	@RequestMapping(value = "/pdcTransfer/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditTranMt(
			@Valid @ModelAttribute("pdcTranMt") PDCTranMt pdcTranMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vDepartmentId") Integer vDepartmentId,
			@RequestParam(value = "vStyleNo") String vStyleNo,
			@RequestParam(value = "vDepartmentToId") Integer vDepartmentToId,
			@RequestParam(value = "vIssWt") Double vIssWt,
			@RequestParam(value = "vLoss") Double vLoss,
			@RequestParam(value = "vRecWt") Double vRecWt,
			RedirectAttributes redirectAttributes, Principal principal) {

		    String retVal = "-1";

		String styleNo = vStyleNo;	
		String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
		String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
		Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
		
		Department department = departmentService.findOne(vDepartmentId);
		Department departmentTo = departmentService.findOne(vDepartmentToId);

		 	if(vIssWt <= 0){
				if(department.getAllowZeroWt() == false){
					return retVal = "-3";
				}else if(vRecWt > 0 && vIssWt <= 0){	
						 return retVal = "-4";
				}
			}
		 
		
		DecimalFormat df = new DecimalFormat("#.###");
	
		
		if (id == null || id.equals("") || (id != null && id == 0)) {

			PDCTranMt pdcTranMtOld = pdcTranMtService.findByDesignAndDepartmentAndCurrStk(design, department, true);
			
				//adding the new record
				PDCTranMt pdctranMtNew = new PDCTranMt();
				pdctranMtNew.setDesign(pdcTranMtOld.getDesign());
				pdctranMtNew.setPcs(pdcTranMtOld.getPcs());
				pdctranMtNew.setCurrStk(true);
				pdctranMtNew.setDeactive(false);
				pdctranMtNew.setCreatedBy(principal.getName());
				pdctranMtNew.setCreatedDate(new java.util.Date());
				pdctranMtNew.setDepartment(departmentTo);
				pdctranMtNew.setDeptFrom(pdcTranMtOld.getDepartment());
				pdctranMtNew.setRefMtId(pdcTranMtOld.getId());
				pdctranMtNew.setRecWt(vIssWt);
				pdcTranMtService.save(pdctranMtNew);
			
				//editing the existing record
				pdcTranMtOld.setCurrStk(false);
				pdcTranMtOld.setDeptTo(departmentTo);
				pdcTranMtOld.setIssueDate(new java.util.Date());
				pdcTranMtOld.setModiBy(principal.getName());
				pdcTranMtOld.setModiDate(new java.util.Date());
				pdcTranMtOld.setIssWt(vIssWt);
				pdcTranMtOld.setLoss(Double.parseDouble(df.format(vRecWt - vIssWt)));
                pdcTranMtService.save(pdcTranMtOld);
				
                retVal = "1";
				
			}else {
			
			retVal = "-1";
		}



		return retVal;

	}
	

	@RequestMapping("/pdcTransfer/display")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "styleNo", required = false) String pStyleNo,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@ModelAttribute("pdcTranMt") PDCTranMt pdcTranMt) {

		String str = null;
		
		if(departmentId != null){
			
			
			String styleNo = pStyleNo;
			
			if(styleNo.indexOf("[") == -1){
				return null;
			}
			
			String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
			String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
	
			Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
			
			
			Department department = departmentService.findOne(departmentId);
			
			PDCTranMt pdcTranMtt = pdcTranMtService.findByDesignAndDepartmentAndCurrStk(design, department, true);
			
			PDC pdc =  pdcService.findByDesignAndDeactive(design, false);
				
			if(pdcTranMtt != null){
		
				String uploadFilePath = "/jewels/uploads/"+ uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";

				String img = design.getDefaultImage();
				String imgName = design.getImage1();
				if (img != null && img.equals("2")) {
					imgName = design.getImage2();
				} else if (img != null && img.equals("3")) {
					imgName = design.getImage3();
				}
				
				String empPdStatus = "-1";
				
				
				if(department.getPcsProd().equals(true)){
					
					List<EmpPdProduction> empPdProductions = empPdProductionService.findByDepartmentAndStyleNoAndDeactive(department, design, false);
					
					if(empPdProductions.size() > 0){
						empPdStatus = "-2";
						for(EmpPdProduction empPdProduction:empPdProductions){
							if(empPdProduction.getPdcTranMt().getId().equals(pdcTranMtt.getId())){
								empPdStatus = "-3";								
							}
						}	
					}else{
						empPdStatus = "-4";
					}
					
				}
		
				
				 str = "" 
						+ pdcTranMtt.getDesign().getMainStyleNo() + "#"
						+ pdcTranMtt.getPcs() + "#" 
						+ pdcTranMtt.getRecWt() + "#"
						+ uploadFilePath + imgName + "#"
						+ pdc.getPurity().getName() + "#"
						+ empPdStatus;
						
						
					
				}else{
					str = "-1";
				}
		}else{
			str = "-2";
		}
		
		return str;
	}

	
	
	@RequestMapping("/pdcTransfer/deptTo")
	@ResponseBody
	public String popDeptTo(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@ModelAttribute("pdcTranMt") PDCTranMt pdcTranMt, Principal principal) {
		User user = userService.findByName(principal.getName());

		return  userDeptTrfRightService.getToDepartmentListDropDownPdc(user.getId(), departmentId);
	
	}
	
	
	@RequestMapping("/pdctransfer/styleNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "design.styleNo", required = false) String StyleNo,
			@RequestParam(value = "deptFrom.id", required = false) Integer deptId) {
		
		Boolean styleNoAvailable = true;
		
		if(deptId != null){
			
			
			String styleNo = StyleNo;	
			
			if(styleNo.indexOf("[") == -1){
				return null;
			}
			String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
			String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
			
			Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
			Department department = departmentService.findOne(deptId);
	
			
			PDCTranMt pdcTranMt =pdcTranMtService.findByDesignAndDepartmentAndCurrStk(design, department, true);
			
			if(pdcTranMt != null){
				styleNoAvailable = true;
			}else{
				styleNoAvailable = false;
			}
		}else{
			styleNoAvailable = true;
		}
		
		
		return styleNoAvailable.toString();
	}
	

	@RequestMapping("/pdctransfer/issWtValidation")
	@ResponseBody
	public String issWtValidation(
			@RequestParam(value = "issWt", required = false) Double issWt,
			@RequestParam(value = "recWt", required = false) Double recWt) {

		Boolean issWtValidation = true;

		if(recWt <= 0){
			
			if(issWt > 0){
				
				issWtValidation=false;
				
			}
			
		}

		
		return issWtValidation.toString();
	}
	
	


}

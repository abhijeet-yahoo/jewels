package com.jiyasoft.jewelplus.controller.manufacturing.transactions;



import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDC;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCTranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class PDMetalDeductionController {

	@Autowired
	private IPDCTranMtService pdcTranMtService;

	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IPDCService pdcService;
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	
	@ModelAttribute("pdcTranMt")
	public PDCTranMt construct() {
		return new PDCTranMt();
	}
	
	
	@ModelAttribute("metalTran")
	public MetalTran constructMetalTran() {
		return new MetalTran();
	}
	
	@RequestMapping("/pdMetalDeduction")
	public String users(Model model) {
		model = populateModel(model);
		return "pdMetalDeduction";
	}
	
	
	public Model populateModel(Model model) {
		model.addAttribute("departmentMap",departmentService.getDepartmentListForMetal());
		model.addAttribute("colorMap",colorService.getColorList());
		return model;
	}
	
	
	
	
	@RequestMapping("/pdMetalDeduction/listing")
	@ResponseBody
	public String MetalDeductionListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "StyleNo", required = false) String StyleNo) {
	

		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(metalTranService.count())
				.append(",\"rows\": [");

		
		String styleNo = StyleNo;
		
		if(styleNo.indexOf("[") == -1){
			return null;
		}
		
		String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
		System.out.println(tempStyleNo);
		String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
		Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);

		
		List<MetalTran> metalTrans = metalTranService.findByStyleIdAndTranTypeAndInOutFld(design.getId(), "PdcTran", "C");
		
		for (MetalTran metalTran : metalTrans) {
			
			System.out.println(metalTran.getTranType());
			
			sb.append("{\"id\":\"")
					.append(metalTran.getId())
					.append("\",\"department\":\"")
					.append((metalTran.getDepartment() != null ? metalTran.getDepartment().getName() : ""))
					.append("\",\"styleNo\":\"")
					.append((metalTran.getStyleId() != null ? metalTran.getStyleId() : ""))
					.append("\",\"purity\":\"")
					.append((metalTran.getPurity() != null ? metalTran.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((metalTran.getColor() != null ? metalTran.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append((metalTran.getMetalWt() == null ? "" : metalTran.getMetalWt()))
					/*.append("\",\"usedMetalWt\":\"")
					.append((metalTran.getuMetalWt() == null ? "" : metalTran.getuMetalWt()))*/
					.append("\"},");
					
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	@RequestMapping("/pdMetalDeduction/add")
	public String add(Model model) {
		return "pdMetalDeduction/add";
	}
	
	
	@RequestMapping(value = "/pdMetalDeduction/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMetalDeduction(
			@Valid @ModelAttribute("metalTran") MetalTran metalTran,BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "vStyleNo") String vStyleNo,
			@RequestParam(value = "vLocationId") Integer vLocationId,
			@RequestParam(value = "vPurityId") Integer vPurityId,
			@RequestParam(value = "vColorId") Integer vColorId,
			@RequestParam(value = "vWeight") Double vWeight,
			@RequestParam(value = "UMetalWtt") Double UMetalWtt,
			@RequestParam(value = "vPresentDept") String vPresentDept,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";
	
	/*	
		if (result.hasErrors()) {
			return "pdMetalDeduction/add";
		}
		
		
		DecimalFormat df = new DecimalFormat("#.###");
		Department deptLocation = departmentService.findOne(vLocationId);
		Purity purity = purityService.findOne(vPurityId);
		Color color = colorService.findOne(vColorId);
		Department presentDept = departmentService.findByName(vPresentDept);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			if( (vWeight == 0.0 || vWeight == 0) && (UMetalWtt == 0.0 || UMetalWtt == 0) ){
				return retVal = "-3";
			}
			
			String styleNo = vStyleNo;	
			String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
			String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
			Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
	
			PDCTranMt pdcTranMtNew=pdcTranMtService.findByDesignAndCurrStk(design, true);
		
			Double rec = pdcTranMtNew.getRecWt();
			if((vWeight > 0) && (UMetalWtt == 0.0 || UMetalWtt == 0)){
				if(rec >= vWeight){
					pdcTranMtNew.setRecWt(Double.parseDouble(df.format(rec - vWeight)));
				}else{
					return retVal = "-2";
				}
			}else if ((vWeight == 0.0 || vWeight == 0) && (UMetalWtt > 0)) {
				if(rec >= UMetalWtt){
					pdcTranMtNew.setRecWt(Double.parseDouble(df.format(rec - UMetalWtt)));
				}else{
					return retVal = "-2";
				}
			}else if (vWeight > 0 && UMetalWtt > 0) {
				Double tempTotal  = vWeight + UMetalWtt;
				if(rec >= tempTotal){
					pdcTranMtNew.setRecWt(Double.parseDouble(df.format(rec - tempTotal)));
				}else{
					return retVal = "-2";
				}
			}else{
				
			}
			
			
			pdcTranMtService.save(pdcTranMtNew);
			
			MetalTran metalTrans = new MetalTran();
			
			metalTrans.setTranDate(new java.util.Date());
			metalTrans.setColor(color);
			metalTrans.setPurity(purity);
			metalTrans.setInOutFld("C");
			metalTrans.setStyleId(design.getId());
			metalTrans.setBalance(Double.parseDouble(df.format(vWeight)));
			metalTrans.setMetalWt(Double.parseDouble(df.format(vWeight)));
			metalTrans.setuBalance(Double.parseDouble(df.format(UMetalWtt)));
			metalTrans.setuMetalWt(Double.parseDouble(df.format(UMetalWtt)));
			metalTrans.setDeptLocation(deptLocation);
			metalTrans.setPurityConv(purity.getPurityConv());
			metalTrans.setRefTranId(pdcTranMtNew.getId());
			metalTrans.setTranType("PdcTran");
			metalTrans.setRemark("null");
			metalTrans.setDepartment(presentDept);
			metalTrans.setCreatedBy(principal.getName());
			metalTrans.setCreatedDt(new java.util.Date());
			metalTrans.setDeactive(false);
			
			metalTranService.save(metalTrans);
			
			retVal = "1";
		
		} else {
		
			System.out.println("in main else part ");
		}


		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);*/

		return retVal;

	}

	
	@RequestMapping("pdMetalDeduction/list")
	@ResponseBody
	public String designList(@RequestParam(value = "term", required = false) String name) {
		Page<Design> designList = designService.findByStyleNo(15, 0, "name", "ASC", name.toUpperCase(), true);
		
		StringBuilder sb = new StringBuilder();

		for (Design design : designList) {
			sb.append("\"")
				.append(design.getMainStyleNo())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	

	@RequestMapping("/pdMetalDeduction/displayBreakup")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "styleNo", required = false) String StyleNo,
			@ModelAttribute("pdcTranMt") PDCTranMt pdcTranMt) {

		String str = null;
	
		String styleNo = StyleNo;	
		
		if(styleNo.indexOf("[") == -1){
			return null;
		}
		String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
		String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
		Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
		
		PDCTranMt pdcTranMtt=pdcTranMtService.findByDesignAndCurrStk(design, true);
		
		if(pdcTranMtt != null){
			
			List<MetalTran> metalTrans = metalTranService.findByStyleIdAndTranTypeAndInOutFld(design.getId(), "PdcTran", "D");
			
			List<String> puritys = new ArrayList<String>();
			
			if(metalTrans.size() > 0){
				for(MetalTran metalTran:metalTrans){
					puritys.add(metalTran.getPurity().getName());
				}
			
			}else{
				PDC pdc = pdcService.findByDesignAndDeactive(design, false);
				if(pdc != null){
					puritys.add(pdc.getPurity().getName());
				}				
			}
	

			String uploadFilePath = "/jewels/uploads/"+ uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";

			String img = design.getDefaultImage();
			String imgName = design.getImage1();
			if (img != null && img.equals("2")) {
				imgName = design.getImage2();
			} else if (img != null && img.equals("3")) {
				imgName =design.getImage3();
			}

			str = "" + pdcTranMtt.getDesign().getMainStyleNo() +"#"
					 + pdcTranMtt.getDateStr() + "#"
					 + pdcTranMtt.getPcs() + "#"
					 + pdcTranMtt.getRecWt() + "#" 
					 + pdcTranMtt.getDepartment().getName() + "#"
					 + uploadFilePath + imgName + "#"
					 + purityService.getPurityListDropDownForDeduction(puritys);
			
			
			
		}else{
			str = "-1";
		}

		return str;
		

	}

	
	
	@RequestMapping("/pdMetalDeduction/styleNoAvailable")
	@ResponseBody
	public String styleNoAvailable(
			@RequestParam(value = "design.styleNo", required = false) String StyleNo) {
		Boolean styleNoAvailable = true;

		String styleNo = StyleNo;	
		if(styleNo.indexOf("[") == -1){
			return null;
		}
		String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
		String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
		Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
		
		PDCTranMt pdcTranMt=pdcTranMtService.findByDesignAndCurrStk(design, true);
		
		if(pdcTranMt != null){
			styleNoAvailable = true;
		}else{
			styleNoAvailable = false;
		}
		
		
		return styleNoAvailable.toString();
	}

	

	
}

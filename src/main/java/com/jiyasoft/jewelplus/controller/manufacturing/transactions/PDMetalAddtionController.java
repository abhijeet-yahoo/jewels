package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCTranMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class PDMetalAddtionController {

	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private ITranMtService tranMtService;
	@Autowired
	private UserService userService;

	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IPDCTranMtService pdcTranMtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
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
	
	
	@RequestMapping("/pdMetalAddition")
	public String users(Model model) {
		model = populateModel(model);
		return "pdMetalAddition";
	}
	
	@RequestMapping("/pdMetalAddition/listing")
	@ResponseBody
	public String MetalAdditionListing(Model model,
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

		
		List<MetalTran> metalTrans = metalTranService.findByStyleIdAndTranTypeAndInOutFld(design.getId(), "PdcTran", "D");
		
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
				/*	.append("\",\"usedMetalWt\":\"")
					.append((metalTran.getuMetalWt() == null ? "" : metalTran.getuMetalWt()))*/
					.append("\"},");
					
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	@RequestMapping("/pdMetalAddition/add")
	public String add(Model model) {
		return "pdMetalAddition/add";
	}
	
	public Model populateModel(Model model) {
		model.addAttribute("departmentMap",departmentService.getDepartmentListForMetal());
		model.addAttribute("purityMap",purityService.getPurityList());
		model.addAttribute("colorMap",colorService.getColorList());
		return model;
	}
	
	@RequestMapping(value = "/pdMetalAddition/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMetalAddition(
			@Valid @ModelAttribute("metalTran") MetalTran metalTran,BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "vStyleNo") String vStyleNo,
			@RequestParam(value = "vLocationId") Integer vLocationId,
			@RequestParam(value = "vPurityId") Integer vPurityId,
			@RequestParam(value = "vColorId") Integer vColorId,
			@RequestParam(value = "vWeight") Double vWeight,
			@RequestParam(value = "UMetalWtt") Double UMetalWtt,
			@RequestParam(value = "tempBal") Double tempBal,
			@RequestParam(value = "vPresentDept") String vPresentDept,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		
		/*if (result.hasErrors()) {
			return "pdMetalAddition/add";
		}
		
		
		DecimalFormat df = new DecimalFormat("#.###");
		Department deptLocation = departmentService.findOne(vLocationId);
		Purity purity = purityService.findOne(vPurityId);
		Color color = colorService.findOne(vColorId);
		Department presentDept = departmentService.findByName(vPresentDept);
		
		Double balance = metalTranService.getStockBalance(vPurityId, vColorId,vLocationId);
		
		if(vWeight > 0.0){
			if (balance != null){
				if(balance < vWeight){
					return retVal = "-1";
				}
			}else{
				return retVal = "-1";
			}
		}
			
		Double usedMetalbalance = metalTranService.getUsedMetalStockBalance(vPurityId, vColorId, vLocationId);
		
		if(UMetalWtt > 0.0){
			if (usedMetalbalance != null){
				if(usedMetalbalance < UMetalWtt){
					return retVal = "-2";
				}
			}else{
				return retVal = "-2";
			}
		}
		
		if((vWeight == 0.0 || vWeight == 0) && (UMetalWtt == 0.0 || UMetalWtt == 0)){
			return retVal = "-3";
		}
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
	
			String styleNo = vStyleNo;	
			String tempStyleNo =  styleNo.substring(0,styleNo.indexOf("[")); 
			String tempVersion = styleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
			Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
		
			
			PDCTranMt pdcTranMtNew=pdcTranMtService.findByDesignAndCurrStk(design, true);
			
		
			Double rec = pdcTranMtNew.getRecWt();
			pdcTranMtNew.setRecWt(Double.parseDouble(df.format(vWeight + rec + UMetalWtt)));
			
			pdcTranMtService.save(pdcTranMtNew);
			
			MetalTran metalTrans = new MetalTran();
			
			metalTrans.setTranDate(new java.util.Date());
			metalTrans.setColor(color);
			metalTrans.setPurity(purity);
			metalTrans.setInOutFld("D");
			metalTrans.setStyleId(design.getId());
			metalTrans.setBalance(Double.parseDouble(df.format(vWeight  * -1)));
			metalTrans.setMetalWt(Double.parseDouble(df.format(vWeight)));
			metalTrans.setuBalance(Double.parseDouble(df.format(UMetalWtt  * -1)));
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

	
	@RequestMapping("pdMetalAdd/list")
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
	
	

	@RequestMapping("/pdMetalAddition/displayBreakup")
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
					 + uploadFilePath + imgName;
			
			
			
		}else{
			str = "-1";
		}

		return str;
		

	}

	
	
	@RequestMapping("/styleNoAvailable")
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
		
		System.out.println("styleNoAvailable---------->>>>>>>"+styleNoAvailable);
		
		return styleNoAvailable.toString();
	}
	
	@RequestMapping("/pdMetalAddition/stock")
	@ResponseBody
	public String stockCheck(
			@RequestParam(value = "metalWt", required = false) Double metalWt,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("pdcTranMt") PDCTranMt pdcTranMt) {

		StringBuilder sb = new StringBuilder();

		Double balance = metalTranService.getStockBalance(purityId, colorId,locationId);
		System.out.println("balance =" + balance);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();
	}
	
	
	@RequestMapping("/pdMetalAddition/usedMetal/stock")
	@ResponseBody
	public String usedMetalStockCheck(
			@RequestParam(value = "uMetalWt", required = false) Double metalWt,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("pdcTranMt") PDCTranMt pdcTranMt) {

		StringBuilder sb = new StringBuilder();


	/*	Double balance = metalTranService.getUsedMetalStockBalance(purityId, colorId, locationId);
		System.out.println("balance =" + balance);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}*/

		return sb.toString();
	}
	
	
	
	@RequestMapping("/pdMetalAddition/fillCombo")
	@ResponseBody
	public String fillCombo(
			@RequestParam(value = "purity", required = false) String purity,
			@RequestParam(value = "color", required = false) String color,
			@ModelAttribute("pdcTranMt") PDCTranMt pdcTranMt) {
		
		Purity purity2 = purityService.findByName(purity);
		Color color2 = colorService.findByName(color);
		
		String str = " "+"hello"+"_"+purity2.getId()+"_"+color2.getId();
		
		return str;
	}


	
}

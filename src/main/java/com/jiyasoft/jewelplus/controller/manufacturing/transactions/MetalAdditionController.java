package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalAdditionController {

	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IBagMtService bagMtService;
	
	
	@Autowired
	private IPurityService purityService;

	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@ModelAttribute("tranMt")
	public TranMt construct() {
		return new TranMt();
	}
	
	@ModelAttribute("metalTran")
	public MetalTran constructMetalTran(){
		return new MetalTran();
	}

	@RequestMapping("/metalAddition")
	public String users(Model model, Principal principal) {
		model = populateModel(model, principal);
		return "metalAddition";
	}
	
	@RequestMapping("/metalAddition/listing")
	@ResponseBody
	public String metalAdditionListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "BagNo", required = false) String BagNo) {
	

		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(metalTranService.count())
				.append(",\"rows\": [");

		BagMt bagMt = bagMtService.findByName(BagNo);

		List<MetalTran> metalTrans = metalTranService.findByBagMtAndTranTypeAndInOutFld(bagMt, "BagTran", "D");
		
		for (MetalTran metalTran : metalTrans) {
			
						
			sb.append("{\"id\":\"")
					.append(metalTran.getId())
					.append("\",\"department\":\"")
					.append((metalTran.getDepartment() != null ? metalTran.getDepartment().getName() : ""))
					.append("\",\"partNm\":\"")
					.append((metalTran.getPartNm() != null ? metalTran.getPartNm().getFieldValue() : ""))
					.append("\",\"bagNo\":\"")
					.append((metalTran.getBagMt() != null ? metalTran.getBagMt().getName() : ""))
					.append("\",\"purity\":\"")
					.append((metalTran.getPurity() != null ? metalTran.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((metalTran.getColor() != null ? metalTran.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append((metalTran.getMetalWt() == null ? "" : metalTran.getMetalWt()))
					.append("\"},");
					
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	@RequestMapping("/metalAddition/add")
	public String add(Model model) {
		return "metalAddition/add";
	}
	
	public Model populateModel(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		
		//model.addAttribute("departmentMap",departmentService.getDepartmentListForMetal());
		
		model.addAttribute("purityMap",purityService.getPurityList());
		model.addAttribute("colorMap",colorService.getColorList());
		
		model.addAttribute("departmentMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		
		/* changes as per bhavesh bhai instruction
		 * UserRole userRole = userRoleService.findByUser(user); if
		 * (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true");
		 * 
		 * }else { model.addAttribute("canEditTranddate", "false"); }
		 */
		
		model.addAttribute("canEditTranddate", "false");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		return model;
	}
	
	@RequestMapping(value = "/metalAddition/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMetalAddition(
			@Valid @ModelAttribute("metalTran") MetalTran metalTran,BindingResult result,
			@RequestParam(value = "vPresentDept") String vPresentDept,
			@RequestParam(value = "vBagNo") String vBagNo,
			@RequestParam(value = "vTranDate") String tranDate,
			/*@RequestParam(value = "id") Integer id,
			@RequestParam(value = "vBagNo") String vBagNo,
			@RequestParam(value = "vLocationId") Integer vLocationId,
			@RequestParam(value = "vPurityId") Integer vPurityId,
			@RequestParam(value = "vColorId") Integer vColorId,
			@RequestParam(value = "vWeight") Double vWeight,
			@RequestParam(value = "tempBal") Double tempBal,
			@RequestParam(value = "vPresentDept") String vPresentDept,*/
			
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		String retVal = "-1";

		if (result.hasErrors()) {
			return "metalAddition/add";
		}
		
		Date date= new Date();
		
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
	
		retVal =metalTranService.metalAdditionSave(vBagNo, metalTran, vPresentDept, principal,date);
	
		


		return retVal;

	}

	
	@RequestMapping("/metalAdd/list")
	@ResponseBody
	public String bagList(@RequestParam(value = "term", required = false) String name) {
		Page<BagMt> bagMtList = bagMtService.findByName(15, 0, "name", "ASC", name.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (BagMt bagMt : bagMtList) {
			sb.append("\"")
				.append(bagMt.getName())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	

	@RequestMapping("/metalAddition/displayBreakup")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "BagNo", required = false) String BagNo,Model model) {

		String uploadFilePath = "/jewels/uploads/"+ uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		return bagMtService.metalAdditionBagDetail(BagNo, uploadFilePath);
		

	}
	
	@RequestMapping("/metalAddition/stock")
	@ResponseBody
	public String stockCheck(
			@RequestParam(value = "metalWt", required = false) Double metalWt,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@RequestParam(value = "purityNm", required = false) String purityNm,
			@RequestParam(value = "colorNm", required = false) String colorNm
			) {

		StringBuilder sb = new StringBuilder();

		Purity purity =purityService.findByName(purityNm);
		Color color =colorService.findByName(colorNm);
		

		Double balance = metalTranService.getStockBalance(purity.getId(), color.getId(),locationId);
		

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();
	}
	
	
	/*@RequestMapping("/metalAddition/usedMetal/stock")
	@ResponseBody
	public String usedMetalStockCheck(
			@RequestParam(value = "uMetalWt", required = false) Double metalWt,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("tranMt") TranMt tranMt) {

		StringBuilder sb = new StringBuilder();

		System.out.println("--------->>>>>>>>>metalWt=" + metalWt);
		System.out.println("--------->>>>>>>>>purityId=" + purityId);
		System.out.println("--------->>>>>>>>>colorId=" + colorId);
		System.out.println("--------->>>>>>>>>locationId=" + locationId);

		Double balance = metalTranService.getUsedMetalStockBalance(purityId, colorId, locationId);
		System.out.println("balance =" + balance);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();
	}*/
	
	@RequestMapping("/bagNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "bagMt.name", required = false) String BagNo) {
		Boolean bagNoAvailable = true;
		
		BagMt bagMt = bagMtService.findByName(BagNo);
		TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
		if(tranMt != null){
			bagNoAvailable = true;
		}else{
			bagNoAvailable = false;
		}
		
		
		
		return bagNoAvailable.toString();
	}
	
	@RequestMapping("/metalAddition/fillCombo")
	@ResponseBody
	public String fillCombo(
			@RequestParam(value = "purity", required = false) String purity,
			@RequestParam(value = "color", required = false) String color,
			@ModelAttribute("tranMt") TranMt tranMt) {
		
		Purity purity2 = purityService.findByName(purity);
		Color color2 = colorService.findByName(color);
		
		String str = " "+"hello"+"_"+purity2.getId()+"_"+color2.getId();
		
		return str;
	}
	
	
	
	
	
	
	

}

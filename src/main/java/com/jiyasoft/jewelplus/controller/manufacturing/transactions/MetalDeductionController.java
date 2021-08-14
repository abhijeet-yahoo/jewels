package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalDeductionController {
	
	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IBagMtService bagMtService;
	
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
	public MetalTran constructMetalTran() {
		return new MetalTran();
	}

	@RequestMapping("/metalDeduction")
	public String users(Model model, Principal principal) {
		model = populateModel(model,principal);
		return "metalDeduction";
	}
	
	
	@RequestMapping("/metalDeduction/listing")
	@ResponseBody
	public String metalDeductionListing(Model model,
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

		List<MetalTran> metalTrans = metalTranService.findByBagMtAndTranTypeAndInOutFld(bagMt, "BagTran", "C");
		
		for (MetalTran metalTran : metalTrans) {
			
			System.out.println(metalTran.getTranType());
			
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
	
	@RequestMapping("/metalDeduction/add")
	public String add(Model model) {
		return "metalDeduction/add";
	}
	
	public Model populateModel(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		model.addAttribute("departmentMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		model.addAttribute("colorMap",colorService.getColorList());
		
		
		/*
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
	
	
	@RequestMapping(value = "/metalDeduction/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMetalDeduction(
			@Valid @ModelAttribute("metalTran") MetalTran metalTran,BindingResult result, 
			@RequestParam(value = "vBagNo") String vBagNo,
			@RequestParam(value = "vPresentDept") String vPresentDept,
			@RequestParam(value = "vTranDate") String tranDate,
	/*		@RequestParam(value = "id") Integer id,
			
			@RequestParam(value = "vLocationId") Integer vLocationId,
			@RequestParam(value = "vPurityId") Integer vPurityId,
			@RequestParam(value = "vColorId") Integer vColorId,
			@RequestParam(value = "vWeight") Double vWeight,
			@RequestParam(value = "UMetalWtt") Double UMetalWtt,*/
			
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {
		
		


		String retVal = "Contact Admin :";

		if (result.hasErrors()) {
			return "metalDeduction/add";
		}
		
		
		Date date= new Date();
		
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
	
		
			retVal =metalTranService.metalDeductionSave(vBagNo, metalTran, vPresentDept, principal,date);
		

		return retVal;

	}

	@RequestMapping("/metalDeduct/list")
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
	
	@RequestMapping("/metalDeduction/displayBreakup")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "BagNo", required = false) String BagNo,
			@ModelAttribute("tranMt") TranMt tranMt,Model model) {

		String str = null;
		
		BagMt bagMt = bagMtService.findByName(BagNo);
		TranMt tranMtt = tranMtService.findByBagMtCurrStk(bagMt, true);
		
		if(tranMtt != null){
		
			List<MetalTran> metalTrans = metalTranService.findByBagMtAndTranTypeAndInOutFld(bagMt, "BagTran", "D");
			List<String> puritys = new ArrayList<String>();
			
			if(metalTrans.size() > 0){
				for(MetalTran metalTran:metalTrans){
					puritys.add(metalTran.getPurity().getName());
				}
				/*puritys.add(tranMtt.getOrderDt().getPurity().getName());*/
			}else{
				/*puritys.add(tranMtt.getOrderDt().getPurity().getName());*/
			}
			
			OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());
			String uploadFilePath = "/jewels/uploads/"+ uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";

			String img = orderDt.getDesign().getDefaultImage();
			String imgName = orderDt.getDesign().getImage1();
			if (img != null && img.equals("2")) {
				imgName = orderDt.getDesign().getImage2();
			} else if (img != null && img.equals("3")) {
				imgName = orderDt.getDesign().getImage3();
			}

			str = "" + tranMtt.getOrderMt().getInvNo() + "#"
					+ tranMtt.getOrderMt().getInvDate() + "#"
					+ tranMtt.getOrderMt().getParty().getName() + "#"
					+ tranMtt.getOrderMt().getOrderType().getName() + "#"
					+ tranMtt.getOrderDt().getDesign().getMainStyleNo() + "#"
					/*+ tranMtt.getOrderDt().getPurity().getName() + "#"
					+ tranMtt.getOrderDt().getColor().getName() + "#"*/
					+ tranMtt.getRecWt() + "#" 
					+ tranMtt.getPcs() + "#"
					+ tranMtt.getDepartment().getName() + "#"
					+ uploadFilePath + imgName + "#"
					+ purityService.getPurityListDropDownForDeduction(puritys);
			}else{
				str = "-1";
			}

		return str;

	}
	
	@RequestMapping("/mDeduction/bagNoAvailable")
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
	
	/*@RequestMapping("/metaldeduction/fillCombo")
	@ResponseBody
	public String fillCombo(
			@RequestParam(value = "purity", required = false) String purity,
			@RequestParam(value = "color", required = false) String color,
			@ModelAttribute("tranMt") TranMt tranMt) {
		
		Purity purity2 = purityService.findByName(purity);
		Color color2 = colorService.findByName(color);
		
		String str = " "+"hello"+"_"+purity2.getId()+"_"+color2.getId();
		
		return str;
	}*/
	

	
	
	
	}

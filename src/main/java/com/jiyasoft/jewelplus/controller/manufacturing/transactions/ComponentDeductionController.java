package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpStoneProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ComponentDeductionController {
	
	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@ModelAttribute("tranMt")
	public TranMt construct() {
		return new TranMt();
	}
	
	@ModelAttribute("compTran")
	public CompTran constructCompTran() {
		return new CompTran();
	}

	@RequestMapping("/componentDeduction")
	public String users(Model model,Principal principal) {
		model = populateModel(model,principal);
		return "componentDeduction";
	}
	
	@RequestMapping("/componentDeductionDetails")
	public String componentDeductionDetails(Model model,Principal principal) {
		model = populateModel(model,principal);
		return "componentDeductionDetails";
	}
	
	@RequestMapping("/componentDeductionDetalis/listing")
	@ResponseBody
	public String componentDeductionDetailsListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "BagNo", required = false) String BagNo) {
		


		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(compTranService.count())
				.append(",\"rows\": [");

		BagMt bagMt = bagMtService.findByName(BagNo);

		List<CompTran> compTrans = compTranService.findByBagMtAndTranTypeAndInOutFld(bagMt, "BagTran", "C");
		
		for (CompTran compTran : compTrans) {
			
			sb.append("{\"id\":\"")
					.append(compTran.getId())
					.append("\",\"department\":\"")
					.append((compTran.getDepartment() != null ? compTran.getDepartment().getName() : ""))
					.append("\",\"component\":\"")
					.append((compTran.getComponent() != null ? compTran.getComponent().getName() : ""))
					.append("\",\"partNm\":\"")
					.append((compTran.getPartNm() != null ? compTran.getPartNm().getFieldValue() : ""))
					.append("\",\"bagNo\":\"")
					.append((compTran.getBagMt() != null ? compTran.getBagMt().getName() : ""))
					.append("\",\"purity\":\"")
					.append((compTran.getPurity() != null ? compTran.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((compTran.getColor() != null ? compTran.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(compTran.getMetalWt())
					.append("\",\"qty\":\"")
					.append(compTran.getPcs())
					.append("\"},");
					
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	@RequestMapping("/componentDeduction/listing")
	@ResponseBody
	public String componentDeductionListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "BagNo", required = false) String BagNo) {
		


		BagMt bagMt = bagMtService.findByName(BagNo);
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_bagComponentDetails(?) }");
		query.setParameter(1,bagMt.getId());
		
		StringBuilder sb = new StringBuilder();
		
		List<Object[]> dtList = query.getResultList();
		
		sb.append("{\"total\":").append(dtList.size()).append(",\"rows\": [");
		
		for (Object[] result : dtList) {
			sb.append("{\"partNm\":\"")
			.append(result[0]!=null? result[0]:"")
			.append("\",\"component\":\"")
			.append(result[1]!=null? result[1]:"")
			.append("\",\"chargable\":\"")
			.append(result[2]!=null? result[2]:"")
			.append("\",\"purity\":\"")
			.append(result[3]!=null? result[3]:"")
			.append("\",\"color\":\"")
			.append(result[4]!=null? result[4]:"")
			.append("\",\"metalWt\":\"")
			.append(result[5]!=null? result[5]:"")
			.append("\",\"qty\":\"")
			.append(result[6]!=null? result[6]:"")
			.append("\",\"retQty\":\"")
			.append("")
			.append("\",\"retMetalwt\":\"")
			.append("")
			.append("\"},");
		    
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	@RequestMapping("/componentDeduction/add")
	public String add(Model model) {
		return "componentDeduction/add";
	}
	
	

	

	@RequestMapping(value = "/componentDeduction/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		
	
		model = populateModel(model,principal);
	//	model.addAttribute("componentMap", componentService.getComponentList());
	//	model.addAttribute("departmentMap",departmentService.getDepartmentListForComp());
		
		model.addAttribute("purityMap",purityService.getPurityList());
		model.addAttribute("colorMap",colorService.getColorList());
		
		return "componentDeduction/add";
	}
	
	
	public Model populateModel(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		model.addAttribute("departmentMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));
		
		//model.addAttribute("componentMap",componentService.getComponentList());
		model.addAttribute("purityMap",purityService.getPurityList());
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
	
	@RequestMapping(value = "/componentDeduction/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditComponentDeduction(
			@Valid @ModelAttribute("compTran") CompTran compTran,BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "vBagNo") String vBagNo,
			@RequestParam(value = "vPurityId") Integer vPurityId,
			@RequestParam(value = "vColorId") Integer vColorId,
			@RequestParam(value = "vQty") Double vQty,
			@RequestParam(value = "vPresentDept") String vPresentDept,
			@RequestParam(value = "findingFlg") String findingFlg,
			@RequestParam(value = "vTranDate") String tranDate,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {
 
		String retVal = "1";
		
		synchronized (this) {
			
			if (result.hasErrors()) {
				return "componentDeduction/add";
			}
			
		if (findingFlg.equalsIgnoreCase("false")) {
				
				if(compTran.getPartNm().getId() == null){
					
					return "Part Not Selected ";
					
				}
			
			}

		Date date= new Date();
		
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
		
		Purity purity =purityService.findOne(vPurityId);
		Color color =colorService.findOne(vColorId);
		compTran.setPurity(purity);
		compTran.setColor(color);
		
		retVal=compTranService.compDeductionSave(compTran,id,vBagNo,vQty,vPresentDept,findingFlg,principal,date);
		
			
		}


		return retVal;
		
	
	}

	@RequestMapping("/componentDeduction/saveCompDeduction")
	@ResponseBody
	public String saveCompDeduction(
		
			@RequestParam(value = "vTranDate",required = false) String tranDate,
			@RequestParam(value = "deductionData",required = false) String deductionData,
			@RequestParam(value = "vBagNo",required = false) String vBagNo,
			@RequestParam(value = "vPresentDept",required = false) String vPresentDept,
			@RequestParam(value = "departmentId",required = false) Integer departmentId,
			
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {
 
		String retVal = "1";
		
		synchronized (this) {
			
			Date date= new Date();
		
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
		
		retVal = compTranService.compDeductionSave(vBagNo, vPresentDept, deductionData, principal, date,departmentId);
		
		
			
		}


		return retVal;
		
	
	}
	
	
	
	@RequestMapping("/compDeduct/list")
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
	
	
	@RequestMapping("/componentDeduction/displayBreakup")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "BagNo", required = false) String BagNo,
			@ModelAttribute("tranMt") TranMt tranMt) {

		String str = null;
		
		BagMt bagMt = bagMtService.findByName(BagNo);
		TranMt tranMtt = tranMtService.findByBagMtCurrStk(bagMt, true);
		
		if(tranMtt != null){
			
			List<CompTran> compTrans = compTranService.findByBagMtAndTranTypeAndInOutFld(bagMt, "BagTran", "D");
			
			List<String> components = new ArrayList<String>();
			List<String> puritys 	= new ArrayList<String>();
			List<String> colors 	= new ArrayList<String>();
			
			
			if(compTrans.size() > 0){
				for(CompTran compTran:compTrans){
					components.add(compTran.getComponent().getName());
					puritys.add(compTran.getPurity().getName());
					colors.add(compTran.getColor().getName());
				}
			}else{
				components.add("");
				puritys.add("");
				colors.add("");
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
			
			
			Date date = tranMtt.getOrderMt().getInvDate();
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			String vDate = sd.format(date);
			
			
			
			str = "" + tranMtt.getOrderMt().getInvNo() + "#"
					+ vDate + "#"
					+ tranMtt.getOrderMt().getParty().getName() + "#"
					+ tranMtt.getOrderMt().getOrderType().getName() + "#"
					+ tranMtt.getOrderDt().getDesign().getMainStyleNo() + "#"
					/*+ tranMtt.getOrderDt().getPurity().getName() + "#"
					+ tranMtt.getOrderDt().getColor().getName() + "#"*/
					+ tranMtt.getRecWt() + "#" 
					+ tranMtt.getPcs() + "#"
					+ tranMtt.getDepartment().getName() + "#"
					+ uploadFilePath + imgName + "#"
					+ componentService.getComponentListDropDownForDeduction(components) + "#"
					+ purityService.getPurityListDropDownForDeduction(puritys) + "#"
					+ colorService.getColorListDropDownForDeduction(colors);
			
		}else{
			
			str = "-1";
			
		}

		return str;

	}
	
	@RequestMapping("/cDeduction/bagNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "bagMt.name", required = false) String BagNo) {
		Boolean bagNoAvailable = true;
		
		BagMt bagMt = bagMtService.findByName(BagNo);
		if(bagMt == null) {
			return false +"";
		}
		
		TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
		if(tranMt != null){
			bagNoAvailable = true;
		}else{
			bagNoAvailable = false;
		}
		
		return bagNoAvailable.toString();
	}
	
	@RequestMapping("/compDeduction/fillCombo")
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
	
	@RequestMapping("/deptLocation/list")
	@ResponseBody
	public String deptLocation() {

		return departmentService.getLocationListDropDown();

	}
	
	
	



}

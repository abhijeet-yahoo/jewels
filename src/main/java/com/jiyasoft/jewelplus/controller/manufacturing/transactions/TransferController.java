package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class TransferController {
	
	@Autowired
	private IReadyBagService readyBagService;

	@Autowired
	private UserService userService;

	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;

	@Autowired
	private IDepartmentService departmentService;

	
	@Autowired
	private ITranMetalService tranMetalService;

	@Autowired
	private IBagMtService bagMtService;

	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	
	@Autowired
	private EntityManager entityManager;

	
	@Value("${trfApprovalFlg}")
	private Boolean trfApprovalFlg;
	
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;


	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;

	@Autowired
	private IReportFilterService reportFilterService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@ModelAttribute("reportFilter")
	public ReportFilter constructReportFilter() {
		return new ReportFilter();
	}

	@ModelAttribute("tranMt")
	public TranMt construct() {
		return new TranMt();
	}
	
	@ModelAttribute("bagMt")
	public BagMt constructBagMt() {
		return new BagMt();
	}
	
	
	@RequestMapping("/barcodeGen")
	public String barcodeGen(Model model, Principal principal) {
		/*
		 * Department department = departmentService.findByName("Ready For Exp.");
		 * model.addAttribute("deptId", department.getId());
		 */
		
		return "barcodeGen";
	}

	@RequestMapping("/transfer")
	public String users(Model model, Principal principal) {
		model = populateModel(model, principal);
		return "transfer";
	}
	
	@RequestMapping("/multiBagTransfer")
	public String multiBagUser(Model model, Principal principal) {
		model = populateModel( model, principal);
		model.addAttribute("trantype", "transfer");
		
		return "multiBagTransfer";
	}
	
	@RequestMapping("/trfForReadybagIss")
	public String multiReadybagTransfer(Model model, Principal principal) {
		model = populateModel( model, principal);
		model.addAttribute("trantype", "trfForReadybagIss");
		
		return "multiBagTransfer";
	}
	
	@RequestMapping("/bagRollback")
	public String bagRollback(Model model, Principal principal) {
		
		return "bagRollback";
	}
	
	@RequestMapping("/newReadyBagIssue")
	public String readyBagIssueUser(Model model, Principal principal) {
		model = populateModel( model, principal);
		
		return "newReadyBagIssue";
	}
	

	@RequestMapping("/pendingApproval")
	public String pendingApproval(Model model, Principal principal) {
		model = populateModel( model, principal);
		
		return "pendingApproval";
	}

	
	@RequestMapping("/transfer/tranDt/listing")
	@ResponseBody
	public String tranDtListing(Model model,
			@RequestParam(value = "BagNo", required = false) String BagNo,
			@RequestParam(value = "departmentId", required = false) Integer departmentId){
		
		
		String str="{\"total\":0,\"rows\": []}";

			StringBuilder sb = new StringBuilder();
			
			if(departmentId != null && BagNo != null){
			
					BagMt bagMt = bagMtService.findByName(BagNo);
					Department department = departmentService.findOne(departmentId);
				

					
		
					TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
					
					if(tranMt != null){
						List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
						
						
						sb.append("{\"total\":").append(tranDts.size()).append(",\"rows\": [");
					
						for (TranDt tranDt : tranDts) {
							sb.append("{\"id\":\"")
									.append(tranDt.getId())
									.append("\",\"stoneType\":\"")
									.append((tranDt.getStoneType() != null ? tranDt.getStoneType().getName() : ""))
									.append("\",\"shape\":\"")
									.append((tranDt.getShape() != null ? tranDt.getShape().getName() : ""))
									.append("\",\"partNm\":\"")
									.append((tranDt.getPartNm() != null ? tranDt.getPartNm().getFieldValue() : ""))
									.append("\",\"quality\":\"")
									.append((tranDt.getQuality() != null ? tranDt.getQuality().getName() : ""))
									.append("\",\"size\":\"")
									.append((tranDt.getSize() != null ? tranDt.getSize() : ""))
									.append("\",\"sieve\":\"")
									.append((tranDt.getSieve() != null ? tranDt.getSieve() : ""))
									.append("\",\"sizeGroup\":\"")
									.append((tranDt.getSizeGroup() != null ? tranDt.getSizeGroup().getName() : ""))
									.append("\",\"stones\":\"")
									.append((tranDt.getStone() != null ? tranDt.getStone() : ""))
									.append("\",\"carat\":\"")
									.append((tranDt.getCarat() != null ? tranDt.getCarat() : ""))
									.append("\",\"setting\":\"")
									.append((tranDt.getSetting() != null ? tranDt.getSetting().getName() : ""))
									.append("\",\"settingType\":\"")
									.append((tranDt.getSettingType() != null ? tranDt.getSettingType().getName() : ""))
									.append("\"},");
						}
						
						
						 str = sb.toString();
							str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
									: str);
							str += "]}";
						
						
					}
		
			
					
			
			
			}else{
				System.out.println("do nothing");
			}

		

		return str;
	}
	

	
	@RequestMapping("/bagRollback/listing")
	@ResponseBody
	public String bagRollbackListing(Model model,
			@RequestParam(value = "bagName", required = false) String bagName){
		
		
		String str="{\"total\":0,\"rows\": []}";

			StringBuilder sb = new StringBuilder();
			
			if(bagName != null){
			
					BagMt bagMt = bagMtService.findByName(bagName);
		
					List<TranMt> tranMts = tranMtService.findByBagMt(bagMt);
						
						
						sb.append("{\"total\":").append(tranMts.size()).append(",\"rows\": [");
					
						for (TranMt tranMt : tranMts) {
							sb.append("{\"id\":\"")
									.append(tranMt.getId())
									.append("\",\"createdDt\":\"")
									.append((tranMt.getDateStr() != null ? tranMt.getDateStr(): ""))
									.append("\",\"createdBy\":\"")
									.append((tranMt.getCreatedBy() != null ? tranMt.getCreatedBy() : ""))
									.append("\",\"tranDate\":\"")
									.append((tranMt.getTranDateStr() !=null ? tranMt.getTranDateStr() :""))
									.append("\",\"issueDt\":\"")
									.append((tranMt.getIssueDate() !=null ? tranMt.getIssueDate():""))
									.append("\",\"issueWt\":\"")
									.append((tranMt.getIssWt() != null ? tranMt.getIssWt() : ""))
									.append("\",\"loss\":\"")
									.append((tranMt.getLoss() != null ? tranMt.getLoss() : ""))
									.append("\",\"pcs\":\"")
									.append((tranMt.getPcs() != null ? tranMt.getPcs() : ""))
									.append("\",\"recWt\":\"")
									.append((tranMt.getRecWt() != null ? tranMt.getRecWt() : ""))
									.append("\",\"refMtId\":\"")
									.append((tranMt.getRefMtId() != null ? tranMt.getRefMtId() : ""))
									.append("\",\"bagId\":\"")
									.append((tranMt.getBagMt() != null ? tranMt.getBagMt().getId() : ""))
									.append("\",\"deptid\":\"")
									.append((tranMt.getDepartment() != null ? tranMt.getDepartment().getId() : ""))
									.append("\",\"deptNm\":\"")
									.append((tranMt.getDepartment() != null ? tranMt.getDepartment().getName() : ""))
									.append("\",\"deptFromId\":\"")
									.append((tranMt.getDeptFrom() != null ? tranMt.getDeptFrom().getId() : ""))
									.append("\",\"deptFromNm\":\"")
									.append((tranMt.getDeptFrom() != null ? tranMt.getDeptFrom().getName() : ""))
									.append("\",\"deptToId\":\"")
									.append((tranMt.getDeptTo() != null ? tranMt.getDeptTo().getId() : ""))
									.append("\",\"deptToNm\":\"")
									.append((tranMt.getDeptTo() != null ? tranMt.getDeptTo().getName() : ""))
									.append("\",\"sordMtId\":\"")
									.append((tranMt.getOrderMt() != null ? tranMt.getOrderMt().getId() : ""))
									.append("\",\"sordDtId\":\"")
									.append((tranMt.getOrderDt() != null ? tranMt.getOrderDt().getId() : ""))
									.append("\",\"currStk\":\"")
									.append((tranMt.getCurrStk() != null ? tranMt.getCurrStk() : ""));
									
								if(tranMt.getCurrStk() == true) {
									sb.append("\",\"action1\":\"")
									.append("<a href='javascript:popBagRollback(")
									.append(tranMt.getId())
									.append(");' class='btn btn-warning btn-sm'  ><span class='glyphicon glyphicon-step-backward'></span>&nbsp;Rollback</a>");
								}
									
									sb.append("\"},");
						}
						
						
						 str = sb.toString();
							str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
									: str);
							str += "]}";
						
					
		
			
					
			
			
			}else{
				System.out.println("do nothing");
			}

		

		return str;
	}
	
	
	
	@RequestMapping("/transfer/tranMetal/listing")
	@ResponseBody
	public String tranMetalListing(@RequestParam(value = "BagNo", required = false) String BagNo,
			@RequestParam(value = "departmentId", required = false) Integer departmentId){
		
		
		String str="{\"total\":0,\"rows\": []}";

			StringBuilder sb = new StringBuilder();
			
			if(departmentId != null && BagNo != null){
			
					BagMt bagMt = bagMtService.findByName(BagNo);
					Department department = departmentService.findOne(departmentId);
				

					
		
					TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
					
					if(tranMt != null){
						List<TranMetal> tranMetals = tranMetalService.findByTranMtAndCurrStk(tranMt, true);
						
						
						sb.append("{\"total\":").append(tranMetals.size()).append(",\"rows\": [");
					
						for (TranMetal tranMetal : tranMetals) {
							sb.append("{\"id\":\"")
									.append(tranMetal.getId())
									.append("\",\"partNm\":\"")
									.append((tranMetal.getPartNm() != null ? tranMetal.getPartNm().getFieldValue() : ""))
									.append("\",\"purity\":\"")
									.append((tranMetal.getPurity() != null ? tranMetal.getPurity().getName(): ""))
									.append("\",\"color\":\"")
									.append((tranMetal.getColor() != null ? tranMetal.getColor().getName(): ""))
									.append("\",\"qty\":\"")
									.append((tranMetal.getMetalPcs() != null ? tranMetal.getMetalPcs() : ""))
									.append("\",\"metalWt\":\"")
									.append((tranMetal.getMetalWeight() != null ? tranMetal.getMetalWeight() : ""))
									.append("\"},");
						}
						
						
						 str = sb.toString();
							str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
									: str);
							str += "]}";
						
					}
		
			
					
			
			
			}

		

		return str;
	}

	public Model populateModel(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
	//	UserRole userRole = userRoleService.findByUser(user);
		/*
		 * if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true");
		 * 
		 * }else { model.addAttribute("canEditTranddate", "false"); }
		 */
		
		model.addAttribute("canEditTranddate", "false");
		
		model.addAttribute("departmenttMap",userDeptTrfRightService.getDepartmentListFromUser(user.getId()));
	// model.addAttribute("departmentMap",userDeptTrfRightService.getToDepartmentListFromUser(user.getId()));

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		ReportFilter reportFilter = reportFilterService.findByName("bagHistoryReport");
		if(reportFilter != null) {
			model.addAttribute("xml",reportFilter.getXml());	
		}
		model.addAttribute("locationMap", locationRightsService.getToLocationListFromUser(user.getId(),"All"));
		
		return model;

	}

	@RequestMapping("/transfer/add")
	public String add(Model model) {
		return "transfer/add";
	}

	
	
	@RequestMapping(value = "/transfer/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditTranMt(
			@Valid @ModelAttribute("transfer") TranMt tranMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vDepartmentId") Integer vDepartmentId,
			@RequestParam(value = "vBagNo") String vBagNo,
			@RequestParam(value = "vRemark") String vRemark,
			@RequestParam(value = "vDepartmentToId") Integer vDepartmentToId,
			@RequestParam(value = "vTranDate") String vTranDate,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		
		String retVal = "-1";
		
		Department departmentTo = departmentService.findOne(vDepartmentToId);
		
		if(departmentTo.getName().equalsIgnoreCase("Rejection") || departmentTo.getName().equalsIgnoreCase("cancel") || departmentTo.getName().equalsIgnoreCase("HOLD DEPT") ){
			if(vRemark == null || vRemark == ""){
				return  "Please Enter Remark !";
			}
			
		}
		if (result.hasErrors()) {
			return "transfer/add";
		}
		
		Date date= new Date();
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(vTranDate);
				
		}
		

		synchronized (this) {
		
			retVal =tranMtService.transfer(vBagNo, vDepartmentId, vDepartmentToId, principal,vRemark,date,trfApprovalFlg);
			
		}
	
		
		return retVal;

	}

	@RequestMapping("/transfer/list")
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

	
	@RequestMapping("/transfer/display")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "BagNo", required = false) String BagNo,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@ModelAttribute("tranMt") TranMt tranMt) {

		String str = null;
		String uploadFilePath = "/jewels/uploads/"+ uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		str = tranMtService.displayTranDetails(BagNo, departmentId, tranMt, uploadFilePath);
		
		return str;
	}
	
	@RequestMapping("/transfer/deptTo")
	@ResponseBody
	public String popDeptTo(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			Principal principal,
			@RequestParam(value = "dropDownId", required = false) String  dropDownId) {
		User user = userService.findByName(principal.getName());

		return userDeptTrfRightService.getToDepartmentListDropDown(
				user.getId(), departmentId,dropDownId);
	}
	
	@RequestMapping("/transfer/bagNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "bagMt.name", required = false) String BagNo,
			@RequestParam(value = "deptFrom.id", required = false) Integer deptId) {
		
		
		Boolean bagNoAvailable = true;
		
		if(deptId != null){
			BagMt bagMt = bagMtService.findByName(BagNo);
			Department department = departmentService.findOne(deptId);
			TranMt tranMtt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
			
			if(tranMtt != null){
				bagNoAvailable = true;
			}else{
				bagNoAvailable = false;
			}
		}else{
			bagNoAvailable = true;
		}
		
				
		return bagNoAvailable.toString();
	}
	
	
	@RequestMapping("/modalTransfer/listing")
	@ResponseBody
	public String modalTransferList(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "deptId", required = false) Integer deptId) {
		
		
		return tranMtService.departmentstockList(limit, offset, sort, order, search, deptId);
		
		/*
		 * List<Object[]> objects =new ArrayList<Object[]>();
		 * 
		 * String departmentCond ="";
		 * 
		 * departmentCond = " deptId in (" + deptId + ")  ";
		 */
					
					
				
		
					
				
		/*
		 * @SuppressWarnings("unchecked") TypedQuery<Object[]> query =
		 * (TypedQuery<Object[]>)
		 * entityManager.createNativeQuery("{call jsp_departmentBaglist(?) }");
		 * query.setParameter(1, departmentCond); objects = query.getResultList();
		 */
				
				
				
		/*
		 * String str=""; StringBuilder sb = new StringBuilder();
		 * sb.append("{\"total\":").append(tranmts.getTotalElements()).
		 * append(",\"rows\": [");
		 */
				 
		/*
		 * for(Object[] list:objects){
		 * 
		 * 
		 * sb.append("{\"party\":\"") .append(list[3] != null ? list[3] : "")
		 * .append("\",\"orderNo\":\"") .append(list[4] != null ? list[4] : "")
		 * .append("\",\"refNo\":\"") .append(list[5] != null ? list[5] : "")
		 * .append("\",\"bagNo\":\"") .append(list[6] != null ? list[6] : "")
		 * .append("\",\"styleNo\":\"") .append(list[7] != null ? list[7] : "")
		 * .append("\",\"qty\":\"") .append(list[2] != null ? list[2] : "")
		 * .append("\",\"grossWt\":\"") .append(list[1] != null ? list[1] : "")
		 * 
		 * .append("\"},"); }
		 * 
		 */
			
		/*
		 * for(TranMt tranMt:tranmts){
		 * 
		 * 
		 * sb.append("{\"party\":\"")
		 * .append(tranMt.getOrderMt().getParty().getPartyCode())
		 * .append("\",\"orderNo\":\"") .append(tranMt.getOrderMt().getInvNo())
		 * .append("\",\"refNo\":\"") .append(tranMt.getOrderMt().getRefNo()
		 * !=null?tranMt.getOrderMt().getRefNo():"") .append("\",\"bagNo\":\"")
		 * .append(tranMt.getBagMt().getName()) .append("\",\"styleNo\":\"")
		 * .append(tranMt.getOrderDt().getDesign().getMainStyleNo())
		 * .append("\",\"qty\":\"") .append(tranMt.getPcs())
		 * .append("\",\"grossWt\":\"") .append(tranMt.getRecWt())
		 * 
		 * .append("\"},"); }
		 * 
		 * str = sb.toString(); str = (str.indexOf("},") != -1 ? str.substring(0,
		 * str.length() - 1) : str); str += "]}";
		 * 
		 * System.out.println("str "+str); return str;
		 */
				
				
		
		
		
}
	
	@RequestMapping("/tranMetalPart/listDropDown")
	@ResponseBody
	public String getPartListDropDown(@RequestParam(value = "BagNo", required = false) String BagNo,
			RedirectAttributes redirectAttributes){
		
		BagMt bagMt = bagMtService.findByName(BagNo);
		TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> partMap = tranMetalService.getPartListByTranMt(tranMt);
		sb.append("<select id=\"partNm.id\" name=\"partNm.id\" cssClass=\"form-control\" onChange=\"javascript:getPartData()\">");
		sb.append("<option value=\"\">- Select Part -</option>");
		for (Object key : partMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(partMap.get(key)).append("</option>");
		}
		sb.append("</select>");
		
	

		return sb.toString();
		
	}

	
	@RequestMapping("/tranMetalPart/detail")
	@ResponseBody
	public String getPartData(@RequestParam(value = "BagNo", required = false) String BagNo,
			@RequestParam(value="partNmId")Integer partId){
		
		BagMt bagMt = bagMtService.findByName(BagNo);
		
		TranMetal tranMetal  =tranMetalService.findByBagMtIdAndPartNmIdAndCurrStk(bagMt.getId(), partId, true);
		
		JSONObject object = new JSONObject();
		
		if(tranMetal !=null){
			
			
			object.put("purity\\.name", tranMetal.getPurity().getName());
			object.put("color\\.name", tranMetal.getColor().getName());
			object.put("purity\\.id", tranMetal.getPurity().getId());
			object.put("color\\.id", tranMetal.getColor().getId());
			
			
		}else{
			object.put("purity\\.name", "");
			object.put("color\\.name", "");
			object.put("purity\\.id", "");
			object.put("color\\.id", "");
		}
		
		
		
		return object.toString();
		
		

	
		
		
	}

	
	
	

	
	@RequestMapping("/transfer/tranMetal/modal/listing")
	@ResponseBody
	public String tranMetalModalListing(@RequestParam(value = "BagNo", required = false) String BagNo,
			@RequestParam(value = "deptName", required = false) String deptName){
	
		String str="{\"total\":0,\"rows\": []}";

			StringBuilder sb = new StringBuilder();
			
			if(deptName != null && BagNo != null){
			
					BagMt bagMt = bagMtService.findByName(BagNo);
					Department department = departmentService.findByName(deptName);
	
					TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
					
					if(tranMt != null){
						
						
						List<TranMetal> tranMetals = tranMetalService.findByTranMtAndCurrStk(tranMt, true);
						
						
						sb.append("{\"total\":").append(tranMetals.size()).append(",\"rows\": [");
					
						for (TranMetal tranMetal : tranMetals) {
							sb.append("{\"id\":\"")
									.append(tranMetal.getId())
									.append("\",\"partNm\":\"")
									.append((tranMetal.getPartNm() != null ? tranMetal.getPartNm().getFieldValue() : ""))
									.append("\",\"purity\":\"")
									.append((tranMetal.getPurity() != null ? tranMetal.getPurity().getName(): ""))
									.append("\",\"color\":\"")
									.append((tranMetal.getColor() != null ? tranMetal.getColor().getName(): ""))
									.append("\",\"qty\":\"")
									.append((tranMetal.getMetalPcs() != null ? tranMetal.getMetalPcs() : ""))
									.append("\",\"metalWt\":\"")
									.append((tranMetal.getMetalWeight() != null ? tranMetal.getMetalWeight() : ""))
									.append("\"},");
						}
						
						
						 str = sb.toString();
							str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
									: str);
							str += "]}";
						
					}
		
			
					
			
			
			}

		
		return str;
	}

	
	@RequestMapping("/multiBagTransfer/listing")
	@ResponseBody
	public String multiBagTransferList(@RequestParam(value = "departmentId", required = false) Integer deptId,
			@RequestParam(value = "trantype", required = false) String trantype){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		String departmentCond ="";
		
			departmentCond = " deptId in (" + deptId + ")  ";

		if(trantype.equalsIgnoreCase("transfer")) {
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_departmentBaglist(?) }");
			query.setParameter(1, departmentCond);
			objects = query.getResultList();
		}else {
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_departmentBaglistForReadyBag(?) }");
			query.setParameter(1, departmentCond);
			objects = query.getResultList();
		}
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 	
			
				
				sb.append("{\"party\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"qty\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"image\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"barcode\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"action1\":\"")
					 .append("<a onClick='javascript:popLossBook()'")
					.append(" class='btn btn-xs btn-info' ><span class='glyphicon glyphicon-edit'></span>&nbsp;LossBook</a>")
					.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	
}	
	
	
	
	
	@RequestMapping("/barcodeGen/listing")
	@ResponseBody
	public String barcodeGenList(@RequestParam(value = "departmentId", required = false) Integer barcodeFlgId){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		String departmentCond ="";
		
			departmentCond = " deptId in (" + barcodeFlgId + ")  ";

		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_departmentBaglistBarcodeGen(?) }");
			query.setParameter(1, departmentCond);
			objects = query.getResultList();
		
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
				
				sb.append("{\"party\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"qty\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"image\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	
}	
	
	
	@RequestMapping("/readyBagIssue/listing")
	@ResponseBody
	public String readyBagDetailsList(@RequestParam(value = "locationId", required = false) Integer locationId) {
		
		String str = "";
	//	Department department= departmentService.findByName("Diamond");
		Department department= departmentService.findOne(locationId);
		str=readyBagService.getReadyBagDetail(department.getId());
		
		return str;
		



	
}	
	
	
	
	
	
	
	@RequestMapping("/multiBag/Transfer")
	@ResponseBody
	public String multiBagTransfer(	@RequestParam(value = "deptFrom", required = false) Integer deptFrom,
			@RequestParam(value = "deptTo", required = false) Integer deptTo,
			@RequestParam(value = "vBagNo", required = false) String vBagNo,
			@RequestParam(value = "tranDate", required = false) String tranDate,
			@RequestParam(value = "trantype", required = true) String trantype,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
		Date date= new Date();
		
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
		
		
		String vRemark ="";
		
		synchronized (this) {
			String[] BagDtl = vBagNo.split(",");
			for (int i = 0; i < BagDtl.length; i++) {
				
				if(trantype.equalsIgnoreCase("transfer")) {
					retVal =tranMtService.transfer(BagDtl[i], deptFrom, deptTo,principal,vRemark,date,trfApprovalFlg);	
				}else {
					retVal =tranMtService.transferForReadybag(BagDtl[i], deptFrom, deptTo,principal,vRemark,date,trfApprovalFlg);
				}
				
			}
			
		}
		
	return retVal;

	}
	
	
	
	



	
	@RequestMapping("/transfer/trfSave")
	@ResponseBody
	public String trfsave(
			@RequestParam(value = "vDepartmentId") Integer vDepartmentId,
			@RequestParam(value = "vBagNo") String vBagNo,
			@RequestParam(value = "vRemark") String vRemark,
			@RequestParam(value = "vDepartmentToId") Integer vDepartmentToId,
			@RequestParam(value = "tranDate", required = false) String tranDate,
			Principal principal) throws ParseException {

		
		String retVal = "-1";
		
		Date date= new Date();
		
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
		
		synchronized (this) {
		
			retVal =tranMtService.transfer(vBagNo, vDepartmentId, vDepartmentToId, principal,vRemark,date,trfApprovalFlg);
			
		}
		
		
		return retVal;

	}
	
	
	@RequestMapping("/transfer/rollBack")
	@ResponseBody
	public String bagRollBack(@RequestParam(value="mtId") Integer mtId) {
		
		
		
		
		String retVal= tranMtService.bagRollBack(mtId);
		
		return retVal;
	}
	
	
	@RequestMapping("/penndingApproval/listing")
	@ResponseBody
	public String pendingApprovalList(@RequestParam(value = "deptToId", required = false) Integer deptToId){
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_departmentPendingApprovallist(?) }");
		query.setParameter(1, deptToId);
		objects = query.getResultList();
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
				
				sb.append("{\"party\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"purity\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"color\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"size\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\",\"totStone\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"totCarat\":\"")
				 .append(list[13] != null ? list[13] : "")
				 .append("\",\"fromDept\":\"")
				 .append(list[14] != null ? list[14] : "")
				 .append("\",\"qty\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"action1\":\"")
					 .append("<a onClick='javascript:popLossBook()'")
					.append(" class='btn btn-xs btn-info' ><span class='glyphicon glyphicon-edit'></span>&nbsp;LossBook</a>")
					.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	
}	
	

	
	@RequestMapping("/pendingApproval/Transfer")
	@ResponseBody
	public String pendingApprovalTransfer(
			@RequestParam(value = "deptTo", required = false) Integer deptTo,
			@RequestParam(value = "vBagNo", required = false) String vBagNo,
			@RequestParam(value = "tranDate", required = false) String tranDate,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
		Date date= new Date();
	
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
		
		
		String vRemark ="";
		
		synchronized (this) {
			String[] BagDtl = vBagNo.split(",");
			for (int i = 0; i < BagDtl.length; i++) {
				
				retVal =tranMtService.pendingApprovaltransfer(BagDtl[i], deptTo,principal,vRemark,date);
						
			}
			
		}
		
	return retVal;

	}
	
	
	@RequestMapping("/notApprovalAndReturn")
	@ResponseBody
	public String notApprovalAndReturn(
			@RequestParam(value = "deptTo", required = false) Integer deptTo,
			@RequestParam(value = "vBagNo", required = false) String vBagNo,
			@RequestParam(value = "tranDate", required = false) String tranDate,
			@RequestParam(value = "remark", required = false) String remark,
			Principal principal) throws ParseException {

		String retVal = "-1";
		String bagList ="";
		
		Date date= new Date();
	
		if(tranDate !=null && !tranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(tranDate);
				
		}
		
	
			String[] BagDtll = vBagNo.split(",");
			for (int i = 0; i < BagDtll.length; i++) {
				
				BagMt bagMt = bagMtService.findByName(BagDtll[i]);
				
				Department departmentTo = departmentService.findOne(deptTo);
				
						
				TranMt tranMtt = tranMtService.findByBagMtAndDeptToAndCurrStkAndPendApprovalFlg(bagMt, departmentTo, true, true);
				if(tranMtt.getRepFlg()) {
					if(bagList !=""){
						bagList +="<br />"+tranMtt.getBagMt().getName();
					}else{
						bagList = "Can't return this bag, Issue from stock "+"<br />"+tranMtt.getBagMt().getName();
					}
				}
			}
			
			
			if(!bagList.isEmpty()) {
				return bagList;
			}
		
		
		synchronized (this) {
			String[] BagDtl = vBagNo.split(",");
			for (int i = 0; i < BagDtl.length; i++) {
				
				retVal =tranMtService.notApprovalAndReturn(BagDtl[i],deptTo,principal,remark,date,trfApprovalFlg);	
			}
			
		}
		
	return retVal;

	}
	
	
	@RequestMapping("/transfer/pendingForApprovalPopup")
	@ResponseBody
	public String pendingForApprovalPopup(	@RequestParam(value = "bagNo", required = false) String bagNo,
			Principal principal) throws ParseException {

		String retVal = "false";
	
		try {
			
			BagMt bagMt =  bagMtService.findByName(bagNo.trim());
			
			if(bagMt.getJobWorkFlg()){
				return "Bag not received from jobwork";
			}
			
			if(bagMt.getBagCloseFlg()){
				return "Bag is close";
			}
			
			TranMt tranMt =  tranMtService.findByBagMtAndPendApprovalFlgAndCurrStk(bagMt, true, true);
			if(tranMt != null) {	
				retVal = "Approval Pending From "+tranMt.getDeptTo().getCode() +" Department";
			}else {
				retVal = "false";
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	return retVal;

	}
	
	
	@RequestMapping("/consigMt/locationDropdown")
	@ResponseBody
	public String popDeptTo(Principal principal) {
		User user = userService.findByName(principal.getName());

		Department department = departmentService.findByName("Marketing");
		
		return userDeptTrfRightService.getToDepartmentListDropDown(
				user.getId(), department.getId(),"locationnm");
	}
	
	
	
	/*@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}*/

}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpStoneProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpStoneProductionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class EmpStoneProductionController {

	@Autowired
	private IEmpStoneProductionService empStoneProductionService;

	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private EntityManager entityManager;

	@ModelAttribute("empStoneProduction")
	public EmpStoneProduction construct() {
		return new EmpStoneProduction();
	}
	
	@ModelAttribute("tranDt")
	public TranDt constructTranDt() {
		return new TranDt();
	}

	@RequestMapping("/empStoneProduction")
	public String empStoneProduction(Model model) {
		model = populateModel(model);
		return "empStoneProduction";
	}
	
	@RequestMapping("/empStoneProduction/add")
	public String add(Model model) {
		return "empStoneProduction/add";
	}
	
	
	@RequestMapping("/empStoneProduction/listing")
	@ResponseBody
	public String empStoneProductionListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			@RequestParam(value = "bagNo", required = false) String bagNo) {

		// logger.info("MetalInwardDt Listing");

		Department department = departmentService.findOne(deptId);
		BagMt bagMt = bagMtService.findByName(bagNo);

		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(empStoneProductionService.count()).append(",\"rows\": [");

		List<EmpStoneProduction> empStoneProductions = empStoneProductionService.findByDepartmentAndBagMtAndDeactive(department, bagMt,false);

		for (EmpStoneProduction empStoneProduction : empStoneProductions) {
			sb.append("{\"id\":\"")
					.append(empStoneProduction.getId())
					.append("\",\"employee\":\"")
					.append(empStoneProduction.getEmployee() != null ? empStoneProduction.getEmployee().getName(): "")
					.append("\",\"stoneType\":\"")
					.append((empStoneProduction.getStoneType() != null ? empStoneProduction.getStoneType().getName(): ""))
					.append("\",\"shape\":\"")
					.append((empStoneProduction.getShape() != null ? empStoneProduction.getShape().getName(): ""))
					.append("\",\"partNm\":\"")
					.append((empStoneProduction.getPartNm() != null ? empStoneProduction.getPartNm().getFieldValue(): ""))
					.append("\",\"size\":\"")
					.append(empStoneProduction.getSize())
					.append("\",\"sieve\":\"")
					.append(empStoneProduction.getSieve())
					.append("\",\"quality\":\"")
					.append((empStoneProduction.getQuality() != null ? empStoneProduction.getQuality().getName(): ""))
					.append("\",\"sizeGroup\":\"")
					.append((empStoneProduction.getSizeGroup() != null ? empStoneProduction.getSizeGroup().getName() : ""))
					.append("\",\"stones\":\"")
					.append((empStoneProduction.getStone() != null ? empStoneProduction.getStone() : ""))
					.append("\",\"carat\":\"")
					.append((empStoneProduction.getCarat() != null ? empStoneProduction.getCarat() : ""))
					.append("\",\"setting\":\"")
					.append((empStoneProduction.getSetting() != null ? empStoneProduction.getSetting().getName() : ""))
					.append("\",\"setType\":\"")
					.append((empStoneProduction.getSettingType() != null ? empStoneProduction.getSettingType().getName() : ""))
					.append("\",\"rate\":\"")
					.append((empStoneProduction.getRate() != null ? empStoneProduction.getRate() : ""))
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editEmpStoneProduction(")
					.append(empStoneProduction.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:confirmDelete(")
					.append("event,")
					.append(empStoneProduction.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(empStoneProduction.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	//--------------//----------TranDt Listing---------------------//
	
	
	@RequestMapping("/empStoneProduction/fromTranDt/listing")
	@ResponseBody
	public String empStoneProductionTranListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			@RequestParam(value = "bagNo", required = false) String bagNo,
			@RequestParam(value = "splitQty", required = false) Double splitQty) {

		
		StringBuilder sb = new StringBuilder();
		
		BagMt bagMt =bagMtService.findByName(bagNo);
		
		TranMt tranMt=tranMtService.findByBagMtCurrStk(bagMt, true);
		
		List<TranDt> tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
				
		sb.append("{\"total\":").append(tranDts.size()).append(",\"rows\": [");
		Integer vStone=0;
		Double vCarat =0.0;
		
		for (TranDt tranDt:tranDts) {
			vStone = tranDt.getStone();
			vCarat = tranDt.getCarat();
			if(splitQty !=null && splitQty>0) {
				
				vStone = (int) (vStone/bagMt.getQty());
				vStone=(int) (vStone*splitQty);
				
				vCarat = Math.round(vCarat/bagMt.getQty()*1000.0)/1000.0;
				vCarat = Math.round((vCarat*splitQty)*1000.0)/1000.0;
				
				
			}
			
			sb.append("{\"id\":\"")
			.append(tranDt.getId())
			.append("\",\"employee\":\"")
			.append("")
			.append("\",\"employeeId\":\"")
			.append("")
			.append("\",\"settingId\":\"")
			.append(tranDt.getSetting().getId())
			.append("\",\"bagSrNo\":\"")
			.append(tranDt.getBagSrNo())
			.append("\",\"setTypeId\":\"")
			.append(tranDt.getSettingType().getId())
			.append("\",\"partNm\":\"")
			.append(tranDt.getPartNm().getFieldValue())
			.append("\",\"stoneType\":\"")
			.append(tranDt.getStoneType().getName())
			.append("\",\"shape\":\"")
			.append(tranDt.getShape().getName())
			.append("\",\"quality\":\"")
			.append(tranDt.getQuality() != null ? tranDt.getQuality().getName():"")
			.append("\",\"sizeGroup\":\"")
			.append(tranDt.getSizeGroup().getName())
			.append("\",\"stone\":\"")
			.append(vStone)
			.append("\",\"carat\":\"")
			.append(vCarat)
			.append("\",\"setting\":\"")
			.append(tranDt.getSetting().getName())
			.append("\",\"setType\":\"")
			.append(tranDt.getSettingType().getName())
			.append("\",\"sieve\":\"")
			.append(tranDt.getSieve())
			.append("\",\"size\":\"")
			.append(tranDt.getSize())
			.append("\",\"rate\":\"")
			.append(0.0)
			.append("\",\"tranMtId\":\"")
			.append(tranDt.getTranMt().getId())
			.append("\",\"action1\":\"")	
			.append("<a onClick='javascript:popSplitRecords()'")
			.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-split'></span>&nbsp;split</a>")
		
			.append("\"},");
	
	
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
		
	}

	
	
	public Model populateModel(Model model) {
		model.addAttribute("departmentMap",departmentService.getDepartmentListForEmpStoneProduction());	
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("qualityMap", qualityService.getQualityList());
		//model.addAttribute("sizeGroupMap", sizeGroupService.getSizeGroupList(conId))
		
		return model;
	}
	
	@RequestMapping("empStoneProduction/transfer")
	@ResponseBody
	public String transferAdjustment(@RequestParam(value = "pBagName", required = false) String pBagName,
			@RequestParam(value = "pDept", required = false) Integer pDept,
			@RequestParam(value = "rowIndex", required = false) String rowIndex,
			@RequestParam(value = "data", required = false) String data,
			Principal principal) {

		String retVal = "success";
		
		
		BagMt bagMt = bagMtService.findByName(pBagName);
		Department department = departmentService.findOne(pDept);
		
		JSONArray jsonDt = new JSONArray(data);
		for (int y = 0; y < jsonDt.length(); y++) {
			JSONObject dataBagDt = (JSONObject) jsonDt.get(y);
			
			
			String empName = dataBagDt.getString("employee").trim();
			
			if(empName.length() > 0){
			
					TranDt tranDt = tranDtService.findOne(dataBagDt.getInt("id"));
					
					Shape shape = shapeService.findByName(dataBagDt.getString("shape").toString().trim());
					
					EmpStoneProduction empStnProd = new EmpStoneProduction();
					
					empStnProd.setCarat(Double.parseDouble(dataBagDt.get("carat").toString()));
					empStnProd.setPcs(tranDt.getPcs());
					if(dataBagDt.get("rate").toString() != null && dataBagDt.get("rate").toString() != "" && dataBagDt.get("rate").toString().length() > 0 ){
						empStnProd.setRate(Double.parseDouble(dataBagDt.get("rate").toString()));
					}else{
						empStnProd.setRate(0.0);
					}
					empStnProd.setStone(Integer.parseInt(dataBagDt.get("stones").toString()));
					empStnProd.setBagMt(bagMt);
					empStnProd.setDepartment(department);
					empStnProd.setEmployee(employeeService.findByNameAndDepartmentAndDeactive(dataBagDt.get("employee").toString(), department,false));
					empStnProd.setSetting(settingService.findByName(dataBagDt.get("setting").toString()));
					empStnProd.setSettingType(settingTypeService.findByName(dataBagDt.get("setType").toString()));
					empStnProd.setShape(shapeService.findByName(dataBagDt.getString("shape").toString()));
					//empStnProd.setQuality(qualityService.findByName(dataBagDt.getString("quality").toString()));
					empStnProd.setQuality(qualityService.findByShapeAndNameAndDeactive(shape, dataBagDt.getString("quality").toString().trim(),false));
					//empStnProd.setSizeGroup(sizeGroupService.findByName(dataBagDt.get("sizeGroup").toString().trim()));
					empStnProd.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(shape,dataBagDt.get("sizeGroup").toString().trim(),false));
					empStnProd.setTranMt(tranDt.getTranMt());
					empStnProd.setStoneType(stoneTypeService.findByName(dataBagDt.get("stoneType").toString()));
					empStnProd.setCreatedBy(principal.getName());
					empStnProd.setCreatedDt(new java.util.Date());
					
					empStoneProductionService.save(empStnProd);
			
			}
			
		}
		
		
		return retVal;
		
	}
	
	
	@RequestMapping(value = "/empStoneProduction/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditEmpPcsProduction(
			@Valid @ModelAttribute("empStoneProduction") EmpStoneProduction empStoneProduction,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		if (result.hasErrors()) {
			return "empStoneProduction/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			empStoneProduction.setCreatedBy(principal.getName());
			empStoneProduction.setCreatedDt(new java.util.Date());
		} else {
			empStoneProduction.setModiBy(principal.getName());
			empStoneProduction.setModiDt(new java.util.Date());
			
			EmpStoneProduction empStn = empStoneProductionService.findOne(id);
			empStoneProduction.setBagSr(empStn.getBagSr());
			empStoneProduction.setBagMt(empStn.getBagMt());
			empStoneProduction.setDepartment(empStn.getDepartment());
			empStoneProduction.setTranMt(empStn.getTranMt());
			empStoneProduction.setPcs(empStn.getPcs());
			

			action = "updated";
			retVal = "1";
		}
		
		if(empStoneProduction.getQuality().getId() == null){
			empStoneProduction.setQuality(null);
		}
		

		empStoneProductionService.save(empStoneProduction);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}
	
	
	@RequestMapping("/empStoneProduction/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		EmpStoneProduction empStoneProduction = empStoneProductionService.findOne(id);
		Integer deptId = empStoneProduction.getDepartment().getId();
		model.addAttribute("employeeMap", employeeService.getEmployeeList(deptId));
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("qualityMap", qualityService.getQualityList());
		model.addAttribute("sizeGroupMap", sizeGroupService.getSizeGroupList(empStoneProduction.getShape().getId()));
		model.addAttribute("settingMap", settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		
		model.addAttribute("empStoneProduction", empStoneProduction);
		model = populateModel(model);

		return "empStoneProduction/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/empStoneProduction/delete/{id}")
	public String delete(@PathVariable int id) {
		empStoneProductionService.delete(id);
		return "-1";
	}
	
	
	
	@RequestMapping("/empStoneProd/list")
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
	
	
	@RequestMapping("/empStoneProduction/employee/list")
	@ResponseBody
	public String employeeList(
			@RequestParam(value = "departmentId") Integer departmentId,
			@ModelAttribute("empStoneProduction") EmpStoneProduction empStoneProduction) {

		return employeeService.getEmployeeListDropDown(departmentId);

	}
	
	
	@RequestMapping("/empStnProduction/bagNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "bagMt.name", required = false) String BagNo,
			@RequestParam(value = "department.id", required = false) Integer deptId) {
		
		Boolean bagNoAvailable = true;
		
		if(deptId != null){
			BagMt bagMt = bagMtService.findByName(BagNo);
			Department department = departmentService.findOne(deptId);
			
			
			List<EmpStoneProduction>  empStnProdtt = empStoneProductionService.findByDepartmentAndBagMtAndDeactive(department, bagMt,false);
			TranMt tranMtt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
			
			
			if(empStnProdtt.size() > 0){
				bagNoAvailable = true;
			}else{
				if(tranMtt != null){
					bagNoAvailable = true;
				}else{
					bagNoAvailable = false;
				}
			}
		}else{
			bagNoAvailable = true;
		}
		
		
		return bagNoAvailable.toString();
	}
	
	
	
	@RequestMapping("/empStoneProduction/changeRate")
	@ResponseBody
	public String calculateRate(
			@RequestParam(value = "settingId") Integer settingId,
			@RequestParam(value = "setTypeId") Integer setTypeId,
			@RequestParam(value = "stoneTypeNm") String stoneTypeNm,
			@RequestParam(value = "shapeNm") String shapeNm){

		
		StringBuilder sb = new StringBuilder();
		
		String retVal = "-1";
		
		Setting 	setting = settingService.findOne(settingId);
		SettingType settingType = settingTypeService.findOne(setTypeId);
		StoneType 	stoneType = stoneTypeService.findByName(stoneTypeNm.trim());
		Shape 		shape = shapeService.findByName(shapeNm.trim());
		
		//SettingRate settingRate = settingRateService.findByStoneTypeAndShapeAndSettingAndSettingTypeAndDeactive(stoneType, shape, setting, settingType, false);
		
		
		/*if(settingRate != null){
			sb.append(settingRate.getRate());
		}else{
			sb.append(retVal);
		}*/
		
		return sb.toString();

	}
	
	
	//empStoneProduction/bagSearch
	
	
	@RequestMapping("/empStoneProduction/bagCheck")
	@ResponseBody
	public String bagCheck(
			@RequestParam(value = "deptId") Integer deptId,
			@RequestParam(value = "bagNo") String bagNm){

		
		String retVal = "2";
		
		BagMt bagMt = bagMtService.findByName(bagNm);
		Department department = departmentService.findOne(deptId);
		
		TranMt tranMtt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
		
		if(tranMtt != null){
			retVal = "-1";
		}
		
		return retVal;

	}
	
	

}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPcsProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProdLabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpPcsProductionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class EmpPcsProductionController {

	@Autowired
	private IEmpPcsProductionService empPcsProductionService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IBagMtService bagMtService;

	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IDesignService designService;

	@Autowired
	private IProdLabourTypeService prodLabTypeService;

	@Autowired
	private UserService userService;

	@ModelAttribute("empPcsProduction")
	public EmpPcsProduction construct() {
		return new EmpPcsProduction();
	}

	@RequestMapping("/empPcsProduction")
	public String empPcsProduction(Model model) {
		model = populateModel(model);
		return "empPcsProduction";
	}

	@RequestMapping("/empPcsProduction/listing")
	@ResponseBody
	public String empPcsProductionListing(Model model,
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

		sb.append("{\"total\":").append(empPcsProductionService.count())
				.append(",\"rows\": [");

		List<EmpPcsProduction> empPcsProductions = empPcsProductionService
				.findByDepartmentAndBagMtAndDeactive(department, bagMt,false);

		for (EmpPcsProduction empPcsProduction : empPcsProductions) {
			sb.append("{\"id\":\"")
					.append(empPcsProduction.getId())
					.append("\",\"styleNo\":\"")
					.append((empPcsProduction.getOrderDt() != null ? empPcsProduction.getOrderDt().getDesign().getMainStyleNo(): ""))
					.append("\",\"bagPcs\":\"")
					.append((empPcsProduction.getBagPcs() != null ? empPcsProduction.getBagPcs() : "" ))
					.append("\",\"prodPcs\":\"")
					.append((empPcsProduction.getProdPcs() != null ? empPcsProduction.getProdPcs() : "" ))
					.append("\",\"employee\":\"")
					.append((empPcsProduction.getEmployee() != null ? empPcsProduction.getEmployee().getName() : ""))
					.append("\",\"recWt\":\"")
					.append(empPcsProduction.getRecWt())
					.append("\",\"issWt\":\"")
					.append(empPcsProduction.getIssWt())
					.append("\",\"loss\":\"")
					.append(empPcsProduction.getLoss())
					.append("\",\"rate\":\"")
					.append(empPcsProduction.getRate())
					.append("\",\"prodLabourType\":\"")
					.append((empPcsProduction.getProdLabType() != null ? empPcsProduction.getProdLabType().getName() : ""))
					.append("\",\"deactive\":\"")
					.append(empPcsProduction.getDeactive() ? "Yes" : "No")
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editEmpPcsProduction(")
					.append(empPcsProduction.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteEmpPcsProd(event,")
					.append(empPcsProduction.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(empPcsProduction.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/empPcsProduction/add")
	public String add(Model model) {
		return "empPcsProduction/add";
	}

	public Model populateModel(Model model) {
		model.addAttribute("departmentMap",departmentService.getDepartmentListForEmpPcsProduction());
		// model.addAttribute("employeeMap",employeeService.getEmployeeList(empPcsProduction.getDepartment().getId()));
		return model;
	}

	@RequestMapping(value = "/empPcsProduction/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditEmpPcsProduction(
			@Valid @ModelAttribute("empPcsProduction") EmpPcsProduction empPcsProduction,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vDeptId") Integer vDeptId,
			@RequestParam(value = "vBagNo") String vBagNo,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/trasactions/empPcsProduction/add.html";

		if (result.hasErrors()) {
			return "empPcsProduction/add";
		}

		
		
		Department department = departmentService.findOne(vDeptId);
		BagMt vbagMt = bagMtService.findByName(vBagNo.trim());
		TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(vbagMt, department, true);

		if (id == null || id.equals("") || (id != null && id == 0)) {
			empPcsProduction.setCreatedBy(principal.getName());
			empPcsProduction.setCreatedDt(new java.util.Date());
			empPcsProduction.setDepartment(department);
			empPcsProduction.setBagMt(vbagMt);
			empPcsProduction.setTranMt(tranMt);
			empPcsProduction.setOrderDt(tranMt.getOrderDt());
			empPcsProduction.setProdLabType(empPcsProduction.getProdLabType());
			//empPcsProduction.setRate(vRate);
			
			
		} else {
			EmpPcsProduction emp = empPcsProductionService.findOne(id);
			empPcsProduction.setId(id);
			empPcsProduction.setModiBy(principal.getName());
			empPcsProduction.setModiDt(new java.util.Date());
			empPcsProduction.setDepartment(department);
			empPcsProduction.setBagMt(vbagMt);
			empPcsProduction.setTranMt(emp.getTranMt());
			empPcsProduction.setOrderDt(emp.getOrderDt());
			empPcsProduction.setProdLabType(empPcsProduction.getProdLabType());
			//empPcsProduction.setRate(vRate);

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/empPcsProduction.html";
		}

		if (empPcsProduction.getOrderDt().getId() == null) {
			empPcsProduction.setOrderDt(null);
		}

		empPcsProductionService.save(empPcsProduction);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/empPcsProduction/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		EmpPcsProduction empPcsProduction = empPcsProductionService.findOne(id);
		Integer deptId = empPcsProduction.getDepartment().getId();
		Integer catId = empPcsProduction.getBagMt().getOrderDt().getDesign().getCategory().getId();
		model.addAttribute("employeeMap", employeeService.getEmployeeList(deptId));
		model.addAttribute("prodLabTypeMap", prodLabTypeService.getProdLabourTypeList(deptId,catId));
		
		model.addAttribute("empPcsProduction", empPcsProduction);
		model = populateModel(model);

		return "empPcsProduction/add";
	}
	
	@ResponseBody
	@RequestMapping("/empPcsProduction/delete/{id}")
	public String delete(@PathVariable int id) {
		empPcsProductionService.delete(id);
		return "-1";
	}
	

	@RequestMapping("/empPcsProd/list")
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
	

	@RequestMapping("/empPcsProduction/display")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "BagNo", required = false) String BagNo,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@ModelAttribute("empPcsProduction") EmpPcsProduction empPcsProduction) {


		BagMt bagMt = bagMtService.findByName(BagNo);

		Department department = departmentService.findOne(departmentId);
		TranMt tranMtt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
		
		String str = "";
		
		if(tranMtt != null){
			str = tranMtt.getOrderDt().getDesign().getMainStyleNo() + "#"+ tranMtt.getPcs() + "#" + tranMtt.getRecWt();
		}else{
			str = "-1";
		}
		
		return str;
	}

	@RequestMapping("/empPcsProduction/employee/list")
	@ResponseBody
	public String employeeList(
			@RequestParam(value = "departmentId") Integer departmentId,
			@ModelAttribute("empPcsProduction") EmpPcsProduction empPcsProduction) {

		return employeeService.getEmployeeListDropDown(departmentId);

	}

	@RequestMapping("/empPcsProduction/prodLabType/list")
	@ResponseBody
	public String prodLabTypeList(
			@RequestParam(value = "departmentId") Integer departmentId,
			@RequestParam(value = "BagNo") String BagNo,
			@ModelAttribute("empPcsProduction") EmpPcsProduction empPcsProduction) {

		BagMt bagMt = bagMtService.findByName(BagNo);
		Integer categoryId = bagMt.getOrderDt().getDesign().getCategory().getId();

		return prodLabTypeService.getProdLabourTypeDropDown(departmentId,categoryId);
	}

	@RequestMapping("/empPcsProduction/getRate")
	@ResponseBody
	public String getRate(
			@RequestParam(value = "prodLabTypeId") Integer prodLabTypeId,
			@ModelAttribute("empPcsProduction") EmpPcsProduction empPcsProduction) {
		
		
		ProdLabourType prodLabType = prodLabTypeService.findOne(prodLabTypeId);
		String str = "" + prodLabType.getRate();
		
		System.out.println("prodLabType.getRate()"+prodLabType.getRate());
		
		return str;

	}
	
	
	@RequestMapping("/empPcsProduction/bagNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "bagMt.name", required = false) String BagNo,
			@RequestParam(value = "department.id", required = false) Integer deptId) {
		
		Boolean bagNoAvailable = true;
		
		
		if(deptId != null){
			BagMt bagMt = bagMtService.findByName(BagNo);
			Department department = departmentService.findOne(deptId);
			
			
			List<EmpPcsProduction>  empPcsProdtt = empPcsProductionService.findByDepartmentAndBagMtAndDeactive(department, bagMt,false);
			TranMt tranMtt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
			
			
			if(empPcsProdtt.size() > 0){
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
	
	

}

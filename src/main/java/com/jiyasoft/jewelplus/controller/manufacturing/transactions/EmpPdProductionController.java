package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmpPdProduction;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.PDCTranMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProdLabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmpPdProductionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IPDCTranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class EmpPdProductionController {

	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	
	@Autowired
	private IEmpPdProductionService empPdProductionService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IPDCTranMtService pdcTranMtService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IDesignService designService;

	@Autowired
	private IProdLabourTypeService prodLabTypeService;

	@Autowired
	private UserService userService;
	
	
	@ModelAttribute("empPdProduction")
	public EmpPdProduction construct() {
		return new EmpPdProduction();
	}

	@RequestMapping("/empPdProduction")
	public String empPdProduction(Model model,Principal principal) {
		model = populateModel(model,principal);
		return "empPdProduction";
	}
	
	
	@RequestMapping("/empPdProduction/listing")
	@ResponseBody
	public String empPdProductionListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			@RequestParam(value = "vStyleNo", required = false) String styleNo) {

	
		if(styleNo.indexOf("[") == -1){
			return null;
		}
		
		String vStyleNo = styleNo;	
		String tempStyleNo =  vStyleNo.substring(0,styleNo.indexOf("[")); 
		String tempVersion = vStyleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
		Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
		
		Department department = departmentService.findOne(deptId);
		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(empPdProductionService.count())
				.append(",\"rows\": [");

		List<EmpPdProduction> empPdProductions = empPdProductionService.findByDepartmentAndStyleNoAndDeactive(department, design, false);

		for (EmpPdProduction empPdProduction : empPdProductions) {
			sb.append("{\"id\":\"")
					.append(empPdProduction.getId())
					.append("\",\"styleNo\":\"")
					.append((empPdProduction.getDesign() != null ? empPdProduction.getDesign().getMainStyleNo() : ""))
					.append("\",\"pcs\":\"")
					.append((empPdProduction.getPcs() != null ? empPdProduction.getPcs() : "" ))
					.append("\",\"prodPcs\":\"")
					.append((empPdProduction.getProdPcs() != null ? empPdProduction.getProdPcs() : "" ))
					.append("\",\"employee\":\"")
					.append((empPdProduction.getEmployee() != null ? empPdProduction.getEmployee().getName() : ""))
					.append("\",\"recWt\":\"")
					.append(empPdProduction.getRecWt())
					.append("\",\"issWt\":\"")
					.append(empPdProduction.getIssWt())
					.append("\",\"loss\":\"")
					.append(empPdProduction.getLoss())
					.append("\",\"rate\":\"")
					.append(empPdProduction.getRate())
					.append("\",\"prodLabourType\":\"")
					.append((empPdProduction.getProdLabType() != null ? empPdProduction.getProdLabType().getName() : ""))
					.append("\",\"deactive\":\"")
					.append(empPdProduction.getDeactive() ? "Yes" : "No")
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editEmpPcsProduction(")
					.append(empPdProduction.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteEmpPcsProd(event,")
					.append(empPdProduction.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(empPdProduction.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/empPdProduction/add")
	public String add(Model model) {
		return "empPdProduction/add";
	}

	public Model populateModel(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		model.addAttribute("departmentMap",userDeptTrfRightService.getDepartmentListFromUserPdc(user.getId()));
		return model;
	}

	@RequestMapping(value = "/empPdProduction/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditEmpPdProduction(
			@Valid @ModelAttribute("empPdProduction") EmpPdProduction empPdProduction,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vDeptId") Integer vDeptId,
			@RequestParam(value = "vStyleNo") String vStyleNo,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/trasactions/empPdProduction/add.html";

		if (result.hasErrors()) {
			return "empPdProduction/add";
		}

		
		String vStyleNo1 = vStyleNo;	
		String tempStyleNo =  vStyleNo1.substring(0,vStyleNo.indexOf("[")); 
		String tempVersion = vStyleNo1.substring(vStyleNo.indexOf("[")+1,vStyleNo.indexOf("]"));
		Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
		
		Department department = departmentService.findOne(vDeptId);

		PDCTranMt pdcTranMt =pdcTranMtService.findByDesignAndDepartmentAndCurrStk(design, department, true);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			empPdProduction.setCreatedBy(principal.getName());
			empPdProduction.setCreatedDt(new java.util.Date());
			empPdProduction.setDepartment(department);
			empPdProduction.setDesign(design);
			empPdProduction.setPdcTranMt(pdcTranMt);
			empPdProduction.setProdLabType(empPdProduction.getProdLabType());
	
		} else {
			EmpPdProduction emp = empPdProductionService.findOne(id);
			empPdProduction.setId(id);
			empPdProduction.setModiBy(principal.getName());
			empPdProduction.setModiDt(new java.util.Date());
			empPdProduction.setDepartment(department);
			empPdProduction.setDesign(design);;
			empPdProduction.setPdcTranMt(emp.getPdcTranMt());
			empPdProduction.setProdLabType(empPdProduction.getProdLabType());
			

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/empPdProduction.html";
		}

		empPdProductionService.save(empPdProduction);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/empPdProduction/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		EmpPdProduction empPdProduction = empPdProductionService.findOne(id);
		Integer deptId = empPdProduction.getDepartment().getId();
		Integer catId = empPdProduction.getDesign().getCategory().getId();
		model.addAttribute("employeeMap", employeeService.getEmployeeList(deptId));
		model.addAttribute("prodLabTypeMap", prodLabTypeService.getProdLabourTypeList(deptId,catId));
		
		model.addAttribute("empPdProduction", empPdProduction);
		model = populateModel(model, principal);

		return "empPdProduction/add";
	}
	
	@ResponseBody
	@RequestMapping("/empPdProduction/delete/{id}")
	public String delete(@PathVariable int id) {
		empPdProductionService.delete(id);
		return "-1";
	}
	

	

	@RequestMapping("/empPdProduction/display")
	@ResponseBody
	public String displayBreakUp(
			@RequestParam(value = "StyleNo", required = false) String StyleNo,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@ModelAttribute("empPdProduction") EmpPdProduction empPdProduction) {

		String vStyleNo = StyleNo;	
		if(vStyleNo.indexOf("[") == -1){
			return null;
		}
	
		String tempStyleNo =  vStyleNo.substring(0,StyleNo.indexOf("[")); 
		String tempVersion = vStyleNo.substring(StyleNo.indexOf("[")+1,StyleNo.indexOf("]"));
		Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
		
		Department department = departmentService.findOne(departmentId);
		
		
		PDCTranMt pdcTranMt =pdcTranMtService.findByDesignAndDepartmentAndCurrStk(design, department, true);
		
		String str = "";
		
		if(pdcTranMt != null){
			str = pdcTranMt.getDesign().getMainStyleNo() + "#"+ pdcTranMt.getPcs() + "#" + pdcTranMt.getRecWt();
		}else{
			str = "-1";
		}
		
		return str;
	}

	@RequestMapping("/empPdProduction/employee/list")
	@ResponseBody
	public String employeeList(
			@RequestParam(value = "departmentId") Integer departmentId,
			@ModelAttribute("empPdProduction") EmpPdProduction empPdProduction) {

		return employeeService.getEmployeeListDropDown(departmentId);

	}

	@RequestMapping("/empPdProduction/prodLabType/list")
	@ResponseBody
	public String prodLabTypeList(
			@RequestParam(value = "departmentId") Integer departmentId,
			@RequestParam(value = "StyleNo") String styleNo) {

		String vStyleNo = styleNo;	
		
		if(styleNo.indexOf("[") == -1){
			return null;
		}
		
		String tempStyleNo =  vStyleNo.substring(0,styleNo.indexOf("[")); 
		String tempVersion = vStyleNo.substring(styleNo.indexOf("[")+1,styleNo.indexOf("]"));
		Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);

		Integer categoryId =design.getCategory().getId();
	
		
		return prodLabTypeService.getProdLabourTypeDropDown(departmentId,categoryId);
	}

	@RequestMapping("/empPdProduction/getRate")
	@ResponseBody
	public String getRate(
			@RequestParam(value = "prodLabTypeId") Integer prodLabTypeId,
			@ModelAttribute("empPdProduction") EmpPdProduction empPdProduction) {
		
		
		ProdLabourType prodLabType = prodLabTypeService.findOne(prodLabTypeId);
		String str = "" + prodLabType.getRate();
		
		System.out.println("prodLabType.getRate()"+prodLabType.getRate());
		
		return str;

	}
	
	
	@RequestMapping("/empPdProduction/styleNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "design.styleNo", required = false) String StyleNo,
			@RequestParam(value = "department.id", required = false) Integer deptId) {
		
		Boolean styleNoAvailable = true;
		
		System.out.println(StyleNo);
		if(deptId != null){
			String vStyleNo = StyleNo;	
			if(vStyleNo.indexOf("[") == -1){
				return null;
			}
			
			
			String tempStyleNo =  vStyleNo.substring(0,StyleNo.indexOf("[")); 
			String tempVersion = vStyleNo.substring(StyleNo.indexOf("[")+1,StyleNo.indexOf("]"));
			Design design = designService.findByStyleNoAndVersion(tempStyleNo, tempVersion);
	
			Department department = departmentService.findOne(deptId);
		
			
			List<EmpPdProduction> empPdProductions =empPdProductionService.findByDepartmentAndStyleNoAndDeactive(department, design, false);
			PDCTranMt pdcTranMt =pdcTranMtService.findByDesignAndDepartmentAndCurrStk(design, department, true);
			System.out.println(pdcTranMt);
			if(empPdProductions.size() > 0){
				styleNoAvailable = true;
			}else{
				if(pdcTranMt != null){
					styleNoAvailable = true;
				}else{
					styleNoAvailable = false;
				}
			}
		}else{
			styleNoAvailable = true;
		}
		
		
		return styleNoAvailable.toString();
	}
	
	
	
	
}

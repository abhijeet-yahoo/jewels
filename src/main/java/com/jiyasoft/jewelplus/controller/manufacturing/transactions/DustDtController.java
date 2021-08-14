package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustMtService;


@Controller
@RequestMapping("/manufacturing/transactions")
public class DustDtController {

	@Autowired
	private IDustDtService dustDtService;
	
	@Autowired
	private IDustMtService dustMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IEmployeeService employeeService; 
	
	@ModelAttribute("dustDt")
	public DustDt constructDustDt(){
		return new DustDt();
	}
	
	
	
	@RequestMapping("/dustDt")
	public String users(Model model) {
		return "dustDt";
	}

	@RequestMapping("/dustDt/listing")
	@ResponseBody
	public String dustDttListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			Principal principal) {

		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(dustDtService.count()).append(",\"rows\": [");

		DustMt dustMt = dustMtService.findByInvNoAndDeactive(pInvNo, false);

		List<DustDt> dustDts = dustDtService.findByDustMtAndDeactive(dustMt, false);

		for (DustDt dustDt : dustDts) {

			sb.append("{\"id\":\"")
					.append(dustDt.getId())
					.append("\",\"department\":\"")
					.append((dustDt.getDepartment() != null ? dustDt.getDepartment().getName() : ""))
					.append("\",\"employee\":\"")
					.append((dustDt.getEmployee() != null ? dustDt.getEmployee().getName() : ""))
					.append("\",\"other\":\"")
					.append((dustDt.getOther() != null ? dustDt.getOther() : ""))
					.append("\",\"metalWt\":\"")
					.append((dustDt.getMetalWt() != null ? dustDt.getMetalWt() : ""))
					.append("\",\"deactive\":\"")
					.append(dustDt.getDeactive() ? "Yes" : "No")
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editDustIssDt(")
					.append(dustDt.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteDustDt(event,")
					.append(dustDt.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(dustDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	@RequestMapping("/dustDt/add")
	public String add(Model model) {
		return "dustDt/add";
	}
	
	
	@RequestMapping(value = "/dustDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditDustDt(
			@Valid @ModelAttribute("dustDt") DustDt dustDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "dustMt/add";
		}
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		
		if (dustDt.getOther().matches("[0-9]+")) {
			return retVal = "-8";
		}

		if((dustDt.getDepartment().getId() == null) && (dustDt.getOther().trim() == null || dustDt.getOther().trim() == "" )){
			return retVal = "-2";
		}
		
		if(dustDt.getDepartment().getId() != null && (dustDt.getOther().trim() != "") ){
			return retVal = "-4";
		}
		
		
		DustMt dustMt = dustMtService.findByInvNoAndDeactive(pInvNo, false);
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			dustDt.setDustMt(dustMt);
			dustDt.setCreatedBy(principal.getName());
			dustDt.setCreatedDate(new java.util.Date());
			dustDt.setMetalWt(Double.parseDouble(df.format(dustDt.getMetalWt())));
		}else{
			dustDt.setId(id);
			dustDt.setDustMt(dustMt);
			dustDt.setModiBy(principal.getName());
			dustDt.setModiDate(new java.util.Date());
			dustDt.setMetalWt(Double.parseDouble(df.format(dustDt.getMetalWt())));
			retVal = "-3";
		}
		
		if(dustDt.getDepartment().getId() == null){
			dustDt.setDepartment(null);
		}
		
		
		if(dustDt.getEmployee().getId() == null){
			dustDt.setEmployee(null);
		}
		
		
		dustDtService.save(dustDt);
		
		return retVal;
		
	}
	
	
	@RequestMapping("/dustDt/edit/{id}")
	public String edit(
		@PathVariable int id, Model model) {
		DustDt dustDt = dustDtService.findOne(id);
		model.addAttribute("dustDt", dustDt);
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
		
		if(dustDt.getDepartment() !=null){
			model.addAttribute("employeeMap", employeeService.getEmployeeList(dustDt.getDepartment().getId()));	
		}
		

		return "dustDt/add";
	}

	
	@ResponseBody
	@RequestMapping("/dustDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal) {
		
		String retVal = "1";
		Boolean status = false;

		if(principal.getName().equalsIgnoreCase("Administrator")){
			status = true;
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			DustDt dustDt = dustDtService.findOne(id);
			Date cDate = dustDt.getCreatedDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);
			if(dbDate.equals(curDate)){
				status = true;
			}
			
		}
		
		
		if(status){
			dustDtService.delete(id);
		}else{
			retVal = "-1";
		}
		
			
		return retVal;
	}
	
	
	@RequestMapping("/dustDt/editRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal) {
	
		String retVal = "-1";
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			DustDt dustDt = dustDtService.findOne(dtId);
			Date cDate = dustDt.getCreatedDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);
			if(dbDate.equals(curDate)){
				retVal = "1";
			}else{
				retVal = "-1";
				
			}
			
			
		}

		return retVal;
	}

	@RequestMapping("/dustDt/employee/list")
	@ResponseBody
	public String employeeList(@RequestParam (value="deptId", required=false) Integer deptId) {

		return employeeService.getEmployeeListDropDown(deptId);
	}
 }

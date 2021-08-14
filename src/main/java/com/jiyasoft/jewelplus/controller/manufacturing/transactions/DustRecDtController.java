package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.MetalLock;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalLockService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@Controller
@RequestMapping("/manufacturing/transactions")
public class DustRecDtController {
	
	
	@Autowired
	private IDustRecDtService dustRecDtService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IDustMtService dustMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IMetalLockService metalLockService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@ModelAttribute("dustRecDt")
	public DustRecDt construct(){
		return new DustRecDt();
	}
	
	@RequestMapping("/dustRecDt")
	public String users(Model model) {
		return "dustRecDt";
	}
		
	@RequestMapping("/dustRecDt/listing")
	@ResponseBody
	public String dustRecDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			Principal principal) {

		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(dustRecDtService.count()).append(",\"rows\": [");

		DustMt dustMt = dustMtService.findByInvNoAndDeactive(pInvNo, false);

		List<DustRecDt> dustRecDts = dustRecDtService.findByDustMtAndDeactive(dustMt, false);

		for (DustRecDt dustRecDt : dustRecDts) {

			sb.append("{\"id\":\"")
					.append(dustRecDt.getId())
					.append("\",\"department\":\"")
					.append((dustRecDt.getDepartment() != null ? dustRecDt.getDepartment().getName() : ""))
					.append("\",\"employee\":\"")
					.append((dustRecDt.getEmployee() != null ? dustRecDt.getEmployee().getName() : ""))
					.append("\",\"other\":\"")
					.append((dustRecDt.getOther() != null ? dustRecDt.getOther() : ""))
					.append("\",\"burnWt\":\"")
					.append((dustRecDt.getBurnWt() != null ? dustRecDt.getBurnWt() : ""))
					.append("\",\"purity\":\"")
					.append((dustRecDt.getPurity() != null ? dustRecDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((dustRecDt.getColor() != null ? dustRecDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append((dustRecDt.getMetalWt() != null ? dustRecDt.getMetalWt() : ""))
					.append("\",\"createdDate\":\"")
					.append(dustRecDt.getCreatedDateStr())
					.append("\",\"deactive\":\"")
					.append(dustRecDt.getDeactive() ? "Yes" : "No")
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editDustRecDt(")
					.append(dustRecDt.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteRecDt(event,")
					.append(dustRecDt.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(dustRecDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	@RequestMapping("/dustRecDt/add")
	public String add(Model model) {
		return "dustRecDt/add";
	}
	
	
	
	@RequestMapping(value = "/dustRecDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditDustRecDt(
			@Valid @ModelAttribute("dustRecDt") DustRecDt dustRecDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvRecNo") String pInvNo,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "dustMt/add";
		}
		
		if (dustRecDt.getOther().matches("[0-9]+")) {
			return retVal = "-8";
		}
		
		
		if((dustRecDt.getDepartment().getId() == null) && (dustRecDt.getOther().trim() == null || dustRecDt.getOther().trim() == "" )){
			return retVal = "-11";
		}
		
		if(dustRecDt.getDepartment().getId() != null && (dustRecDt.getOther().trim() != "") ){
			return retVal = "-12";
		}
		
			
		DustMt dustMt = dustMtService.findByInvNoAndDeactive(pInvNo, false);
		
		Purity purity = purityService.findOne(dustRecDt.getPurity().getId());
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			dustRecDt.setDustMt(dustMt);
			dustRecDt.setCreatedBy(principal.getName());
			dustRecDt.setCreatedDate(new java.util.Date());
			dustRecDt.setUniqueId(new java.util.Date().getTime());
			dustRecDt.setMetalWt(Math.round(dustRecDt.getMetalWt()*1000.0)/1000.0);
			dustRecDt.setBurnWt(Math.round(dustRecDt.getBurnWt()*1000.0)/1000.0);
			dustRecDt.setPurityConv(purity.getPurityConv() != null ? purity.getPurityConv() : 0.0);
		}else{
			dustRecDt.setId(id);
			dustRecDt.setDustMt(dustMt);
			dustRecDt.setModiBy(principal.getName());
			dustRecDt.setModiDate(new java.util.Date());
			dustRecDt.setMetalWt(Math.round(dustRecDt.getMetalWt()*1000.0)/1000.0);
			dustRecDt.setBurnWt(Math.round(dustRecDt.getBurnWt()*1000.0)/1000.0);
			
			dustRecDt.setPurityConv(purity.getPurityConv() != null ? purity.getPurityConv() : 0.0);
			retVal = "-3";
		}
		
		if(dustRecDt.getDepartment().getId() == null){
			dustRecDt.setDepartment(null);
		}
		
		if(dustRecDt.getEmployee().getId() == null){
			dustRecDt.setEmployee(null);
		}
		
		dustRecDtService.save(dustRecDt);
		
		
		DustRecDt dustRecDtUni = dustRecDtService.findByUniqueId(dustRecDt.getUniqueId());
		Department department = departmentService.findByName("Refining");
		Department deptLocation = departmentService.findByName("Central");
		
		//MetalTran metalTran = metalTranService.getMetalTranByRefTranIdAndTranTypeAndDeactive(dustRecDtUni.getId(), "Refining", false);
		
		List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(dustRecDtUni.getId(), "Refining", false);
		
		MetalTran metalTran = null;
		
		if(metalTrans.size() > 0){
			if(metalTrans.size() > 1){
				return retVal = "-2";
			}else{
				metalTran = metalTrans.get(0);
			}
		}
		
		

		Double mtlRate=metalTranService.getMetalRate(dustRecDtUni.getPurity().getId(), dustRecDtUni.getColor().getId(), deptLocation.getId(),
				dustRecDtUni.getMetalWt());
	
		
		
		if(metalTran != null){
			
			//-------edit-----metaltran--->>
			
			metalTran.setColor(dustRecDtUni.getColor());
			metalTran.setPurity(dustRecDtUni.getPurity());
			metalTran.setInOutFld("C");
			metalTran.setBalance(dustRecDtUni.getMetalWt());
			metalTran.setMetalWt(dustRecDtUni.getMetalWt());
			metalTran.setDeptLocation(deptLocation);
			metalTran.setPurityConv(dustRecDtUni.getPurityConv());
			metalTran.setRefTranId(dustRecDtUni.getId());
			metalTran.setTranType("Refining");
			metalTran.setRemark("null");
			metalTran.setDepartment(department);
			metalTran.setModiBy(dustRecDtUni.getModiBy());
			metalTran.setModiDt(dustRecDtUni.getModiDate());
			metalTran.setDeactive(false);
			metalTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
			
			if(purity.getPure().equals(true)) {
				
				MetalLock metalLock =metalLockService.findByMetalLockDtAndMetalAndDeactive(new Date(),purity.getMetal(), false);
				if(metalLock !=null) {
					metalTran.setMetalRate(metalLock.getMetalLockRate());
				}
				
				
				
			}
			
			metalTranService.save(metalTran);

			
			
		}else{
			
			//-------add-----metaltran--->>
			
			MetalTran metTran = new MetalTran();
			
			metTran.setColor(dustRecDtUni.getColor());
			metTran.setPurity(dustRecDtUni.getPurity());
			metTran.setInOutFld("C");
			metTran.setBalance(dustRecDtUni.getMetalWt());
			metTran.setMetalWt(dustRecDtUni.getMetalWt());
			metTran.setDeptLocation(deptLocation);
			metTran.setPurityConv(dustRecDtUni.getPurityConv());
			metTran.setRefTranId(dustRecDtUni.getId());
			metTran.setTranType("Refining");
			metTran.setRemark("null");
			metTran.setDepartment(department);
			metTran.setCreatedBy(dustRecDtUni.getCreatedBy());
			metTran.setCreatedDt(dustRecDtUni.getCreatedDate());;
			metTran.setDeactive(false);
			metTran.setTranDate(new Date());
			metTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
			
			if(purity.getPure().equals(true)) {
				
				MetalLock metalLock =metalLockService.findByMetalLockDtAndMetalAndDeactive(new Date(),purity.getMetal(), false);
				if(metalLock !=null) {
					metTran.setMetalRate(metalLock.getMetalLockRate());
				}
				
				
				
			}
			
			metalTranService.save(metTran);
			
		}
		
		return retVal;
		
	}
	
	
	@RequestMapping("/dustRecDt/edit/{id}")
	public String edit(
			@PathVariable int id, Model model) {
		DustRecDt dustRecDt = dustRecDtService.findOne(id);
		model.addAttribute("dustRecDt", dustRecDt);
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
		
		if(dustRecDt.getDepartment()!=null){
		model.addAttribute("employeeMap", employeeService.getEmployeeList(dustRecDt.getDepartment().getId()));
		}
		
		return "dustRecDt/add";
	}

	@ResponseBody
	@RequestMapping("/dustRecDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal) {
		
		
		String retVal = "1";
		Boolean status = false;

		if(principal.getName().equalsIgnoreCase("Administrator")){
			status = true;
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			DustRecDt dustRecDt = dustRecDtService.findOne(id);
			Date cDate = dustRecDt.getCreatedDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);
			if(dbDate.equals(curDate)){
				status = true;
			}
			
		}
		
		if(status){
			dustRecDtService.delete(id);
			List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(id, "Refining", false);
			
			MetalTran metTan = null;
			if(metalTrans.size() > 0){
				metTan = metalTrans.get(0);
			}
			
			if(metTan != null){
				metalTranService.delete(metTan.getId());
			}
		}else{
			retVal = "-1";
		}
		
		
		return retVal;
	}
	
	
	
	@RequestMapping("/dustRecDt/editRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal) {
	
		String retVal = "-1";
		
		if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			DustRecDt dustRecDt = dustRecDtService.findOne(dtId);
			Date cDate = dustRecDt.getCreatedDate();
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
	
	@RequestMapping("/dustRecDt/employee/list")
	@ResponseBody
	public String employeeList(@RequestParam (value="deptId", required=false) Integer deptId) {
		
		return employeeService.getEmployeeListDropDown(deptId);
	}
}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDustRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;


@Controller
@RequestMapping("/manufacturing/transactions")
public class DustMtController {

	
	@Autowired
	private IDustMtService dustMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IDustDtService dustDtService;
	
	@Autowired
	private IDustRecDtService dustRecDtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@ModelAttribute("dustMt")
	public DustMt construct(){
		return new DustMt();
	}
	
	@ModelAttribute("dustDt")
	public DustDt constructDustDt(){
		return new DustDt();
	}
	
	@ModelAttribute("dustRecDt")
	public DustRecDt constructDustRecDt(){
		return new DustRecDt();
	}
	
	
	
	@RequestMapping("/dustMt")
	public String users(Model model) {
		return "dustMt";
	}
		
	@RequestMapping("/dustMt/listing")
	@ResponseBody
	public String dustMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<DustMt> dustMts = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = dustMtService.count(sort, search, false);
			dustMts = dustMtService.findByInvNo(limit, offset,sort, order, search, true);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("invNo")) {
			rowCount = dustMtService.count(sort, search, false);
			dustMts = dustMtService.findByInvNo(limit, offset,sort, order, search, true);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			dustMts = new PageImpl<DustMt>(new ArrayList<DustMt>());
		} else {
			rowCount = dustMtService.count(sort, search, false);
			dustMts = dustMtService.findByInvNo(limit, offset,sort, order, search, true);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (DustMt dustMt : dustMts) {			
				
			sb.append("{\"id\":\"")
					.append(dustMt.getId())
					.append("\",\"invNo\":\"")
					.append(dustMt.getInvNo()) 
					.append("\",\"fromPeriod\":\"")
					.append(dustMt.getFromPeriodStr())
					.append("\",\"toPeriod\":\"")
					.append(dustMt.getToPeriodStr())
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/dustMt/edit/")
					.append(dustMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteDustMt(event,")
					.append(dustMt.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(dustMt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	@RequestMapping("/dustMt/add")
	public String add(Model model) {
		model = populateModel(model);
		return "dustMt/add";

	}
	
	private Model populateModel(Model model) {
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		return model;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/dustMt/add", method = RequestMethod.POST)
	public String addEditDustMt(@Valid @ModelAttribute("dustMt") DustMt dustMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/transactions/dustMt/add.html";
		java.util.Date vDate = null;

		if (result.hasErrors()) {
			return "dustMt/add";
		}
		
		
		
		DustMt dustMtDateChecker = dustMtService.findByFromPeriodAndToPeriodAndDeactive(dustMt.getFromPeriod(), dustMt.getToPeriod(), false);

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			if(dustMtDateChecker != null){
				return retVal = "-1";
			}
			
			vDate = new java.util.Date();
			dustMt.setCreatedBy(principal.getName());			
			dustMt.setCreatedDate(new java.util.Date());
			dustMt.setUniqueId(vDate.getTime());
		} else {
			
			DustMt dustMtOld = dustMtService.findOne(id);
			
			if(dustMtDateChecker != null){
				
				if(dustMtOld.getFromPeriod().equals(dustMtDateChecker.getFromPeriod()) && dustMtOld.getToPeriod().equals(dustMtDateChecker.getToPeriod())){
					System.out.println("all is well !");
				}else{
					return retVal = "-1";
				}
				
			}
			
			
			dustMt.setId(id);
			dustMt.setModiBy(principal.getName());
			dustMt.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "/jewels/manufacturing/transactions/dustMt.html";
		}
		
		
		if (action.equals("added")) {
			Integer maxSrno = dustMtService.maxSrNo();
			maxSrno = (maxSrno == null ? 1 : maxSrno + 1);
			dustMt.setSrNo(maxSrno);
			
		}
		
		dustMtService.save(dustMt);
		
		if (action.equals("added")) {
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			DustMt vDustMt = dustMtService.findByUniqueId(vDate.getTime());
			vDustMt.setInvNo("DUST"+"-"+vDustMt.getSrNo()+"-"+vYear);
			
			retVal = "/jewels/manufacturing/transactions/dustMt/edit/"+vDustMt.getId()+".html?success=true";
			dustMtService.save(vDustMt);
		
		}

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/dustMt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		DustMt dustMt = dustMtService.findOne(id);
		model.addAttribute("dustMt", dustMt);
		model = populateModel(model);
		
		return "dustMt/add";
	}

	
	@ResponseBody
	@RequestMapping("/dustMt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal) {
		
		
		String retVal = "1";
		Boolean status = false;

		if(principal.getName().equalsIgnoreCase("Administrator")){
			status = true;
		}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			DustMt dustMt = dustMtService.findOne(id);
			Date cDate = dustMt.getCreatedDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);
			if(dbDate.equals(curDate)){
				status = true;
			}
			
		}
		
		
		
		if(status){
		
			dustMtService.delete(id);
			DustMt dustMt = dustMtService.findOne(id);
			
			
			List<DustDt> dustDts = dustDtService.findByDustMtAndDeactive(dustMt, false);
			for(DustDt dustDt: dustDts){
				dustDtService.delete(dustDt.getId());
			}
			
			List<DustRecDt> dustRecDts = dustRecDtService.findByDustMtAndDeactive(dustMt, false);
			
			for(DustRecDt dustRecDt:dustRecDts){
				
				dustRecDtService.delete(dustRecDt.getId());
				
				List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(dustRecDt.getId(), "Refining", false);
				
				MetalTran metTan = null;
				if(metalTrans.size() > 0){
					metTan = metalTrans.get(0);
				}
				
				if(metTan != null){
					metalTranService.delete(metTan.getId());
				}
				
			}
			
		}else{
			retVal = "-1";
		}
		
		return retVal;
	}

	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	
	
}

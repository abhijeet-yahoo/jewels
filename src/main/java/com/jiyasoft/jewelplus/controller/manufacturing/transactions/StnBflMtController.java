package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflMt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnBflMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class StnBflMtController {
	

	@Autowired
	private IStnBflMtService stnBflMtService;
	

	
	@ModelAttribute("stnBflMt")
	public StnBflMt constructMt() {
		return new StnBflMt();
	}

	@RequestMapping("/stnBflMt")
	public String users(Model model, Principal principal) {
		return "stnBflMt";
	}
	
	
	
	
	@RequestMapping("/stnBflMt/listing")
	@ResponseBody
	public String stnBflMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<StnBflMt> stnBflMts = null;

		// User loginUser = userService.findByName(principal.getName());

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = stnBflMtService.count(sort, search, false);
			stnBflMts = stnBflMtService.findByInvNo(limit, offset,sort, order, search, true);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = stnBflMtService.count(sort, search, false);
			stnBflMts = stnBflMtService.findByInvNo(limit, offset,sort, order, search, true);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			stnBflMts = new PageImpl<StnBflMt>(new ArrayList<StnBflMt>());
		} else {
			rowCount = stnBflMtService.count(sort, search, false);
			stnBflMts = stnBflMtService.findByInvNo(limit, offset,sort, order, search, true);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (StnBflMt stnBflMt : stnBflMts) {
			sb.append("{\"id\":\"")
					.append(stnBflMt.getId())
					.append("\",\"invNo\":\"")
					.append(stnBflMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(stnBflMt.getInvDateStr())
					.append("\",\"remark\":\"")
					.append((stnBflMt.getRemark() != null ? stnBflMt.getRemark() : ""))
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/stnBflMt/edit/")
					.append(stnBflMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/stnBflMt/delete/")
					.append(stnBflMt.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(stnBflMt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	
	
	
	
	
	@RequestMapping("/stnBflMt/add")
	public String add(Model model, Principal principal){
	
		return "stnBflMt/add";
	} 
	
	
	
	@RequestMapping(value = "/stnBflMt/add", method = RequestMethod.POST)
	public String addEditStnBflMt(
			@Valid @ModelAttribute("stnBflMt") StnBflMt stnBflMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/stnBflMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			return "stnBflMt/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			aDate = new java.util.Date();
			stnBflMt.setCreatedBy(principal.getName());
			stnBflMt.setCreatedDt(new java.util.Date());
			stnBflMt.setUniqueId(aDate.getTime());

		} else {
			stnBflMt.setModiBy(principal.getName());
			stnBflMt.setModiDt(new java.util.Date());

			if (stnBflMt.getDeactive() != stnBflMtService.findOne(id).getDeactive()) {
				stnBflMt.setDeactiveDt(new java.util.Date());
			} else {
				stnBflMt.setDeactiveDt(stnBflMtService.findOne(id).getDeactiveDt());
			}

			if (stnBflMtService.findOne(id).getDeactive() == false) {
				stnBflMt.setDeactive(false);

			} else {
				stnBflMt.setDeactive(true);
			}

			stnBflMt.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/stnBflMt.html";
		}
		
		if (action.equals("added")) {
			Integer maxSrno = stnBflMtService.maxSrNo();
			maxSrno = (maxSrno == null ? 1 : maxSrno + 1);
			stnBflMt.setSrNo(maxSrno);
			
		}
		
		

		stnBflMtService.save(stnBflMt);
		
		if (action.equals("added")) {
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			StnBflMt vStnBflMt = stnBflMtService.findByUniqueId(stnBflMt.getUniqueId());
			vStnBflMt.setInvNo("BFR"+"-"+String.format("%04d", vStnBflMt.getSrNo())+"-"+vYear);
			
			retVal = "redirect:/manufacturing/transactions/stnBflMt/edit/"+vStnBflMt.getId()+".html";
			stnBflMtService.save(vStnBflMt);
		}
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		return retVal;
}
	
	
	
	@RequestMapping("/stnBflMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		StnBflMt stnBflMt = stnBflMtService.findOne(id);
		model.addAttribute("stnBflMt", stnBflMt);

		return "stnBflMt/add";
	}

	
	
	@RequestMapping("/stnBflMt/delete/{id}")
	public String delete(@PathVariable int id) {
	
	/*
		StnBflMt stnBflMt = stnBflMtService.findOne(id);
		
		List<StnBflDt> stnBflDtList = stnBflDtService.findByStnBflMtAndDeactiveOrderByIdDesc(stnBflMt, false); 
	
		if(stnBflDtList.size() <= 0){
			stnBflMtService.delete(id);
			
		}

		
		
		return "redirect:/manufacturing/transactions/stnBflMt.html";*/
		return null;
	}
	
	
	
	@RequestMapping("/stnBflMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {
			invoiceAvailable = (stnBflMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			StnBflMt stnBflMt= stnBflMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(stnBflMt.getInvNo()))) {
				invoiceAvailable = (stnBflMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
}

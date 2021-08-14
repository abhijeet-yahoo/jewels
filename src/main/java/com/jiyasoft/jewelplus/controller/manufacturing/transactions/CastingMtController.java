package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.BaseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IBaseMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICastingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CastingMtController {

	@Autowired
	private ICastingMtService castingMtService;

	
	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IBaseMtService baseMtService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IMetalTranService metalTranService;

	@Autowired
	private ICastingDtService castingDtService;
	
	

	
	@ModelAttribute("castingMt")
	public CastingMt construct() {
		return new CastingMt();
	}

	@RequestMapping("/castingMt")
	public String users(Model model) {
		//model.addAttribute("castingMt", castingMtService.findAll());

		return "castingMt";
	}

	@RequestMapping("/castingMt/listing")
	@ResponseBody
	public String castingMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<CastingMt> castingMts = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = castingMtService.count(sort, search, false);
			castingMts = castingMtService.findByTreeNo(limit, offset, sort, order, search, true);
		} else if (search != null && sort != null
				&& sort.equalsIgnoreCase("name")) {
			rowCount = castingMtService.count(sort, search, false);
			castingMts = castingMtService.findByTreeNo(limit, offset, sort, order, search, true);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			castingMts = new PageImpl<CastingMt>(new ArrayList<CastingMt>());
		} else {
			rowCount = castingMtService.count(sort, search, false);
			castingMts = castingMtService.findByTreeNo(limit, offset, sort, order, search, true);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
		for (CastingMt castingMt : castingMts) {

			sb.append("{\"id\":\"")
			.append(castingMt.getId())
			.append("\",\"cDate\":\"")
			.append(castingMt.getDateStr())
			.append("\",\"treeNo\":\"")
			.append(castingMt.getTreeNo())
			.append("\",\"purity\":\"")
			.append(castingMt.getPurity().getName())
			.append("\",\"color\":\"")
			.append(castingMt.getColor().getName())
			.append("\",\"waxTreeWt\":\"")
			.append(castingMt.getWaxTreeWt() !=null?castingMt.getWaxTreeWt():0.0)
			.append("\",\"stoneWt\":\"")
			.append(castingMt.getStoneWt() !=null?castingMt.getStoneWt():0.0)
			.append("\",\"reqWt\":\"")
			.append(castingMt.getReqWt()!=null?castingMt.getReqWt():0.0)
			.append("\",\"issWt\":\"")
			.append(castingMt.getFreshIssWt() !=null?castingMt.getFreshIssWt():0.0)
			.append("\",\"treeWt\":\"")
			.append(castingMt.getTreeWt()!=null?castingMt.getTreeWt():0.0)
			.append("\",\"castingLoss\":\"")
			.append(castingMt.getCastingLoss() !=null?castingMt.getCastingLoss():0.0)
			.append("\",\"stubWt\":\"")
			.append(castingMt.getStubWt() !=null?castingMt.getStubWt():0.0)
			.append("\",\"pcsWt\":\"")
			.append(castingMt.getPcsWt() !=null?castingMt.getPcsWt():0.0)
			.append("\",\"cutLoss\":\"")
			.append(castingMt.getCuttingLoss() !=null?castingMt.getCuttingLoss():0.0)
			.append("\",\"deactive\":\"")
			.append(castingMt.getDeactive() ? "Yes" : "No")
			.append("\",\"action1\":\"")
			.append("<a href='/jewels/manufacturing/transactions/castingMt/edit/")
			.append(castingMt.getId())
			.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
//			.append("\",\"action2\":\"")
//			.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/castingMt/delete/")
//			.append(castingMt.getId())
//			.append(".html' class='btn btn-xs btn-danger triggerRemove")
//			.append(castingMt.getId())
//			.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
//			.append("\"},");
			
			.append("\",\"action2\":\"")
			.append("<a href='javascript:deleteCasting(event,")
			.append(castingMt.getId())
			.append(");' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
			.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}

	@RequestMapping("/castingMt/add")
	public String add(Model model) {
		model = populateModel(model);
		return "castingMt/add";

	}

	private Model populateModel(Model model) {

		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("baseMtMap", baseMtService.getBaseMtList());
		return model;
	}

	@RequestMapping(value = "/castingMt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("castingMt") CastingMt castingMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "freshIssWt") Double freshIssWt,
			@RequestParam(value = "prevIssWt") Double prevIssWt,
			/*@RequestParam(value = "usedIssWt") Double usedIssWt,
			@RequestParam(value = "prevUsedIssWt") Double prevUsedIssWt,*/
			@RequestParam(value = "pcsWt") Double pcsWt,
			@RequestParam(value = "prevPcsWt") Double prevPcsWt,
			@RequestParam(value = "prevKt") Integer prevKt,
			@RequestParam(value = "prevColor") Integer prevColor,
			@RequestParam(value = "vReqWt") Double vReqWt,
			RedirectAttributes redirectAttributes, Principal principal) {

	return castingMtService.castingMtSave(castingMt, id, freshIssWt, prevIssWt, pcsWt, prevPcsWt, prevKt, prevColor, vReqWt, principal,redirectAttributes);

	}

	@RequestMapping("/castingMt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		CastingMt castingMt = castingMtService.findOne(id);
		model.addAttribute("castingMt", castingMt);
		model = populateModel(model);
		return "castingMt/add";
	}

	
	@RequestMapping("/castingMt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		
		String retVal=""; 
		
		List<CastingDt> castingDts =  castingDtService.findByCastingMtAndDeactive(castingMtService.findOne(id), false);
		if(castingDts.size() > 0) {
			retVal = "Can't Delete Casting Tree, Bag Present In Tree";
		}else {
			
			castingMtService.delete(id);

			List<MetalTran> metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(id, "Casting",false);
			
			Integer metalTranId = null;
			for (MetalTran metalT : metalTran) {
				metalTranId = metalT.getId();
				metalTranService.delete(metalTranId);
			}
			
			retVal="1";
			
		}
		
		

		return retVal;
	}

	@RequestMapping("/castingMtAvailable")
	@ResponseBody
	public String castingMtAvailable(@RequestParam String treeNo,
			@RequestParam Date cDate, @RequestParam Integer id) {

		Boolean castingMtAvailable = true;

		if (id == null) {
			castingMtAvailable = (castingMtService.findByCDateAndTreeNo(cDate,
					treeNo) == null);
		} else {
			CastingMt castingMt = castingMtService.findOne(id);
			if (!(treeNo.equalsIgnoreCase(castingMt.getTreeNo()))) {
				castingMtAvailable = (castingMtService.findByCDateAndTreeNo(
						cDate, treeNo) == null);
			}
		}

		return castingMtAvailable.toString();
	}

	@RequestMapping("/casting/baseNo/list")
	@ResponseBody
	public String BaseNoList(@RequestParam(value = "baseId") Integer baseId,
			@ModelAttribute("castingMt") CastingMt castingMt) {

		StringBuilder sb = new StringBuilder();

		BaseMt baseMt = baseMtService.findOne(baseId);
		Integer baseNumber = baseMt.getBaseNo();

		sb.append(baseNumber);
		return sb.toString();
	}

	@RequestMapping("/casting/baseWt/list")
	@ResponseBody
	public String BaseWtList(@RequestParam(value = "baseId") Integer baseId,
			@ModelAttribute("castingMt") CastingMt castingMt) {

		StringBuilder sb = new StringBuilder();

		BaseMt baseMt = baseMtService.findOne(baseId);
		Double baseWeight = baseMt.getBaseWt();

		sb.append(baseWeight);
		return sb.toString();
	}

	@RequestMapping("/casting/purity/list")
	@ResponseBody
	public String PurityWaxConvList(
			@RequestParam(value = "purityId") Integer purityId,
			@ModelAttribute("castingMt") CastingMt castingMt) {

		StringBuilder sb = new StringBuilder();

		Purity purity = purityService.findOne(purityId);
		Double waxWtConv = purity.getWaxWtConv();
		sb.append(waxWtConv);
		return sb.toString();
	}

	@RequestMapping("/casting/stock")
	@ResponseBody
	public String stockCheck(
			@RequestParam(value = "freshIssWt", required = false) Double freshIssWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("castingMt") CastingMt castingMt) {

		StringBuilder sb = new StringBuilder();

		Integer locationId = departmentService.findByName("Central").getId();
		Double balance = metalTranService.getStockBalance(purityId, colorId,
				locationId);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		// sb.append(balance);

		return sb.toString();
	}

	/*@RequestMapping("/casting/usedMetal/stock")
	@ResponseBody
	public String usedMetalStockCheck(
			@RequestParam(value = "usedIssWt", required = false) Double usedIssWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("castingMt") CastingMt castingMt) {

		StringBuilder sb = new StringBuilder();

		Integer locationId = departmentService.findByName("Central").getId();
		Double balance = metalTranService.getUsedMetalStockBalance(purityId,colorId, locationId);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();
	}*/

	
	
	@RequestMapping("/casting/pcsWt/stock")
	@ResponseBody
	public String pcsWtStockCheck(
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("castingMt") CastingMt castingMt) {

		StringBuilder sb = new StringBuilder();

		Integer locationId = departmentService.findByName("Central").getId();
		Double balance = metalTranService.getPcsWtStockBalance(purityId,colorId, locationId);

		if (balance != null) {
			sb.append(balance);
		} else {
			sb.append("-1");
		}

		return sb.toString();
	}

	
	
	
	
	
	
	
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}

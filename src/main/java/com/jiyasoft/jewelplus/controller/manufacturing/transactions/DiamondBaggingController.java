
package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondBaggingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class DiamondBaggingController {
	
	@Autowired
	private IDiamondBaggingService diamondBaggingService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IStoneChartService stoneChartService;

	@Autowired
	private IOrderStnDtService orderStnDtService;
	
	@Autowired
	private ISettingService settingService;

	@Autowired
	private ISettingTypeService settingTypeService;
	

	@Autowired
	private IDesignGroupService designGroupService;

	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private ISubShapeService subShapeService;

	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IDepartmentService departmentService;
	

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	@Value("${diamond.stocktype}")
	private String diamondStockType;
	
	@Value("${allowNegativeDiamond}")
	private Boolean allowNegativeDiamond;
	
	@Value("${companyName}")
	private String companyName;

	
	@ModelAttribute("diamondBagging")
	public DiamondBagging construct() {
		return new DiamondBagging();
	}

	@ModelAttribute("designStone")
	public DesignStone constructDesignStone() {
		return new DesignStone();
	}
	

	@RequestMapping("/diamondBagging")
	public String users(Model model,Principal principal) {
		populateModel(model,principal);
		model.addAttribute("trfFlg", false);
		return "diamondBagging";
	}
	
	
	@RequestMapping("/diamondBaggingTrf")
	public String diamondBaggingTrf(Model model,Principal principal) {
		populateModel(model,principal);
		model.addAttribute("trfFlg", true);
		return "diamondBaggingTrf";
	}
	
	
	public Model populateModel(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
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
		model.addAttribute("groupList", designGroupService.getDesignGroupList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("settingMap", settingService.getSettingList(0));
		model.addAttribute("settingTypeMap", settingTypeService.getSettingTypeList());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		

		
			
		return model;

	}
	
	
	
	@RequestMapping("/bagCheck")
	@ResponseBody
	public String bagCheck(@RequestParam(value = "bagNm") String bagNm){
		String retVal = "-1";
		BagMt bagMt = bagMtService.findByName(bagNm.trim());
		List<TranDt> tranDts = tranDtService.findByBagMt(bagMt);
		if(tranDts.size() > 0){
			retVal = "-2";
		}
		return retVal;
	}
	
	

	@RequestMapping("/diamondBagging/listing")
	@ResponseBody
	public String bagMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pBagName", required = false) String pBagName) {

		List<DiamondBagging> diamondBaggings = null;

		/*Long rowCount = null;
		rowCount = diamondBaggingService.count(sort, search, false);*/

		

		BagMt bagMt = bagMtService.findByName(pBagName);
		
		//--Earlier this method was used--//diamondBaggings = diamondBaggingService.findByBagMt(bagMt);
		
		diamondBaggings = diamondBaggingService.findByBagMtAndTranTypeAndDeactiveAndBaggingReturn(bagMt,"Bagging", false,false);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(diamondBaggings.size()).append(",\"rows\": [");
		for (DiamondBagging diamondBagging : diamondBaggings) {
			sb.append("{\"id\":\"")
				.append(diamondBagging.getId())
				.append("\",\"invNo\":\"")
				.append((diamondBagging.getStoneInwardDt().getStoneInwardMt() == null ? "" : diamondBagging.getStoneInwardDt().getStoneInwardMt().getInvNo()))
				.append("\",\"bagName\":\"")
				.append((diamondBagging.getBagMt() == null ? "" : diamondBagging.getBagMt().getName()))
				.append("\",\"qty\":\"")
				.append(diamondBagging.getBagPcs())
				.append("\",\"shape\":\"")
				.append((diamondBagging.getShape() == null ? "" : diamondBagging.getShape().getName()))
				.append("\",\"quality\":\"")
				.append((diamondBagging.getQuality() == null ? "" : diamondBagging.getQuality().getName()))
				.append("\",\"mm\":\"")
				.append((diamondBagging.getSize() != null ? diamondBagging.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((diamondBagging.getSieve() != null ? diamondBagging.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((diamondBagging.getSizeGroup() != null ? diamondBagging.getSizeGroup().getName() : ""))
				.append("\",\"stones\":\"")
				.append((diamondBagging.getStone() != null ? diamondBagging.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((diamondBagging.getCarat() != null ? diamondBagging.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((diamondBagging.getStnRate() != null ? diamondBagging.getStnRate() : ""))
				.append("\",\"setting\":\"")
				.append((diamondBagging.getSetting() != null ? diamondBagging.getSetting().getName() : ""))
				.append("\",\"setType\":\"")
				.append((diamondBagging.getSettingType() != null ? diamondBagging.getSettingType().getName() : ""))
				.append("\",\"bagSrno\":\"")
				.append((diamondBagging.getBagSrNo() != null ? diamondBagging.getBagSrNo() : ""))
				.append("\",\"transferred\":\"")
				.append((diamondBagging.getTransfered() != null ? diamondBagging.getTransfered() : ""))
				.append("\",\"deactive\":\"")
				.append((diamondBagging.getDeactive() == null ? "Active" : (diamondBagging.getDeactive() ? "Deactive" : "Active")))
				.append("\",\"deactiveDt\":\"")
				.append((diamondBagging.getDeactiveDt() == null ? "" : diamondBagging.getDeactiveDt()))
				.append("\",\"select\":\"")
				.append((diamondBagging.getDeactiveDt() == null ? "" : diamondBagging.getDeactiveDt()))
				.append("\",\"action1\":\"")
				.append("<a href='/jewels/manufacturing/transactions/diamondBagging/edit/")
				.append(diamondBagging.getId())
				.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a onClick='javascript:bagDtDelete(event, ").append(diamondBagging.getId()).append(", 1);' href='javascript:void(0);'")
				.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(diamondBagging.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
		}

		model.addAttribute("isTransferred", new java.util.Date());

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	@RequestMapping("/diamondBagging/add")
	public String add(Model model) {
		return "diamondBagging/add";
	}

	@RequestMapping(value = "/diamondBagging/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("diamondBagging") DiamondBagging diamondBagging,
			@ModelAttribute("bagMt") BagMt bagMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/diamondBagging/add.html";
		retVal = "-1";

		if (result.hasErrors()) {
			return "diamondBagging/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			diamondBagging.setCreatedBy(principal.getName());
			diamondBagging.setCreatedDt(new java.util.Date());

			retVal = "1";
		} else {
			diamondBagging.setId(id);
			diamondBagging.setModiBy(principal.getName());
			diamondBagging.setModiDate(new java.util.Date());

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/diamondBagging.html";
			retVal = "1";
		}

		if (diamondBagging.getShape().getId() == null) {
			diamondBagging.setShape(null);
		}
		if (diamondBagging.getSubShape().getId() == null) {
			diamondBagging.setSubShape(null);
		}
		if (diamondBagging.getQuality().getId() == null) {
			diamondBagging.setQuality(null);
		}
		if (diamondBagging.getStoneType().getId() == null) {
			diamondBagging.setStoneType(null);
		}
		if (diamondBagging.getSetting().getId() == null) {
			diamondBagging.setSetting(null);
		}
		if (diamondBagging.getSettingType().getId() == null) {
			diamondBagging.setSettingType(null);
		}

		diamondBaggingService.save(diamondBagging);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/diamondBagging/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		DiamondBagging diamondBagging = diamondBaggingService.findOne(id);
		model.addAttribute("diamondBagging", diamondBagging);

		return "diamondBagging/add";
	}

	/*@RequestMapping("/diamondBagging/delete/{id}")
	public String delete(@PathVariable int id) {
		diamondBaggingService.delete(id);

		return "redirect:/manufacturing/transactions/diamondBagging.html";
	}*/

	@RequestMapping("/jobBag/list")
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

	@RequestMapping("/jobBag/details")
	@ResponseBody
	public String bagDetails(@RequestParam(value = "bagName", required = false) String name) {
		
		String uploadFilePath = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";

		return bagMtService.jobBagDetails(name, uploadFilePath);
	}

	@RequestMapping("/diaBagStnDt/edit/{id}")
	public String diaBagStnDtEdit(@PathVariable int id, Model model, Principal principal) {
		OrderStnDt orderStnDt = orderStnDtService.findOne(id);

		DiamondBagging diamondBagging = new DiamondBagging();
		diamondBagging = mapWithOrderStnDt(diamondBagging, orderStnDt);

		model.addAttribute("diaBagStnObj", diamondBagging);

		model = populateModel(model,principal);

		if (orderStnDt != null) {
			Shape shape = orderStnDt.getShape();
			model.addAttribute("subShapeMap", (orderStnDt.getSubShape() == null ? null : subShapeService.getSubShapeList(shape.getId())));
			model.addAttribute("qualityMap", (orderStnDt.getQuality() == null ? null : qualityService.getQualityList(shape.getId())));
			model.addAttribute("sizeGroupMap", (orderStnDt.getSizeGroup() == null ? null : sizeGroupService.getSizeGroupList(shape.getId())));
		}			

		return "diaBagStnDt/add";
	}

	private DiamondBagging mapWithOrderStnDt(DiamondBagging diamondBagging, OrderStnDt orderStnDt) {
		diamondBagging.setStoneType(orderStnDt.getStoneType());
		diamondBagging.setShape(orderStnDt.getShape());
		diamondBagging.setSubShape(orderStnDt.getSubShape());
		diamondBagging.setQuality(orderStnDt.getQuality());
		diamondBagging.setSize(orderStnDt.getSize());
		diamondBagging.setSizeGroup(orderStnDt.getSizeGroup());
		diamondBagging.setSieve(orderStnDt.getSieve());
		diamondBagging.setStone(orderStnDt.getStone());
		diamondBagging.setCarat(orderStnDt.getCarat());
		diamondBagging.setSetting(orderStnDt.getSetting());
		diamondBagging.setSettingType(orderStnDt.getSettingType());

		return diamondBagging;
	}




	@RequestMapping("/diamondBagging/stonePointer")
	@ResponseBody
	public String stonePointer(@RequestParam(value = "shape", required = false) String shape, 
		@RequestParam(value = "size", required = false) String size) {

		return "" + stoneChartService.findByShapeAndSizeAndDeactive(shapeService.findByName(shape), size,false).getPerStoneWt();
	}
	
	
	
	@RequestMapping(value ="diamondBagging/saveAdjustment" , method = RequestMethod.POST)
	@ResponseBody
	public String saveAdjustment(
			@RequestParam(value = "stnPk", required = false) String stnPk,
			@RequestParam(value = "tBStn", required = false) String trfStone,
			@RequestParam(value = "tBCart", required = false) String trfCarat,
			@RequestParam(value = "bagNm", required = false) String bagNm,
			@RequestParam(value = "ordStnDtId", required = false)Integer ordStnDtId,
			Principal principal) {
		
		String retVal = "";
		
		synchronized (this) {
			retVal=diamondBaggingService.saveAdjustment(stnPk,trfStone,trfCarat,bagNm,ordStnDtId,principal);
			
		}
		
		
		return retVal;
		
		
	}

	


	@RequestMapping("diamondBagging/deleteAdjustment")
	@ResponseBody
	public String deleteAdjustment(@RequestParam(value = "adjData", required = false) String adjData,
			Principal principal) {

		String retVal = "1";

		synchronized (this) {
			
			JSONArray json = null;
			json = new JSONArray(adjData);

			for (int x = 0; x < json.length(); x++) {
				JSONObject dataRow = (JSONObject) json.get(x);

				String dt = dataRow.get("id").toString();
				diamondBaggingService.delete(Integer.parseInt(dt));
			}
			
		}
		
		

		return retVal;
	}

	@RequestMapping("/diamondBagging/transfer")
	@ResponseBody
	public String transferAdjustment(@RequestParam(value = "pBagName", required = false) String pBagName,
			@RequestParam(value = "tranDate", required = false) String vTranDate,
			@RequestParam(value = "data", required = false) String data,
			@RequestParam(value = "trfFlg", required = false) Boolean trfFlg,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			Principal principal) throws ParseException {

			String retVal = "";
			
			Date date= new Date();
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				date = originalFormat.parse(vTranDate);
					
			}

			try {
				synchronized (this) {
					
					if(trfFlg.equals(false)) {
						retVal=diamondBaggingService.diamondBaggingTransfer(pBagName, data, principal,diamondStockType,allowNegativeDiamond,date,companyName);	
					}else {
						if(deptId != null && deptId>0) {
							retVal=diamondBaggingService.diamondBaggingTransferOnBag(pBagName, data, principal,diamondStockType,allowNegativeDiamond,date,companyName,deptId);
						}else {
							retVal = "Warning : Department Not Selected";
						}
					}
					
					
					if(retVal == "-1"){
						retVal = "Success : Data Transfered successfully";
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}


	
	
	@ResponseBody
	@RequestMapping("/diamondBagging/quality")
	public String diamondQuality(
			@RequestParam(value = "shape") String shape){
		Shape shapeNew = shapeService.findByName(shape.trim());
		return qualityService.getQualityListDropDown(shapeNew.getId());

	}
	
	
	@ResponseBody
	@RequestMapping("/diamondBagging/saveQuality")
	public String saveDiamondQuality(
			@RequestParam(value = "quality") Integer quality,
			@RequestParam(value = "ordStnId") Integer ordStnId){
		
			
		Quality qualityNew = qualityService.findOne(quality);
		OrderStnDt orderStnDt = orderStnDtService.findOne(ordStnId);
		
		
			orderStnDt.setQuality(qualityNew);
			orderStnDtService.save(orderStnDt);
		

		
		
		
		return "1";
	}
	
	
	
	
	
	
	
	//-----------------(Flawless) New Diamond Bagging code's ------------//
	
	
	@RequestMapping("/jobBag/diamond/details")
	@ResponseBody
	public String bagDiamondDetails(@RequestParam(value = "bagName", required = false) String name) {
		
		String uploadFilePath = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		
	
		return bagMtService.jobBagDiamondDetails(name, uploadFilePath);
		
	}
	
	
	
	
	
	

}



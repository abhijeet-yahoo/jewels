package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;


@RequestMapping("/manufacturing/masters")
@Controller
public class OrderStnDtController {
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IOrderMtService orderMtService;

	@Autowired
	private IOrderDtService orderDtService;

	@Autowired
	private IOrderStnDtService orderStnDtService;

	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;

	@Autowired
	private ISubShapeService subShapeService;

	@Autowired
	private IQualityService qualityService;

	@Autowired
	private IStoneChartService stoneChartService;

	@Autowired
	private ISizeGroupService sizeGroupService;

	@Autowired
	private ISettingService settingService;

	@Autowired
	private ISettingTypeService settingTypeService;

	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IDesignGroupService designGroupService;
	
		
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	
	//---------------------------//
		
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	
	@Autowired
	private IHandlingMasterService handlingMasterService;
	
	@Autowired
	private ISettingChargeService settingChargeService;
	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;

	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IOrderMetalService orderMetalService;
	
	@Autowired
	private IReadyBagService readyBagService;
	
	@Autowired
	private EntityManager entityManager;
	

	@ModelAttribute("orderStnDt")
	public OrderStnDt construct() {
		return new OrderStnDt();
	}

	@ModelAttribute("diamondBagging")
	public DiamondBagging constructDbg() {
		return new DiamondBagging();
	}

	@ModelAttribute("bagMt")
	public BagMt constructBagMt() {
		return new BagMt();
	}
	
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;

	@RequestMapping("/orderStnDt")
	public String users(Model model) {

		return "orderStnDt";
	}
	
	
	//---- For Diamond Bagging Listing ------>>>
	
	
	@RequestMapping("/orderStnDt/listing")
	@ResponseBody
	public String orderStnDtListing(Model model,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "pOrderDtId", required = false) Integer pOrderDtId,
			@RequestParam(value = "pIsBagDt", required = false) Boolean pIsBagDt,
			@RequestParam(value = "pBagName", required = false) String pBagName,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo,false );
		//OrderDt orderDt = orderDtService.findOne(pOrderDtId);
		
		BagMt bagMt = null;
		
		if(pBagName != null){
			bagMt = bagMtService.findByName(pBagName.trim());
		}
		
		

		if (orderMt != null) {
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL JSP_BaggingDtl(?) }");
			query.setParameter(1,bagMt.getId());
			
			
			List<Object[]> dtList = query.getResultList();
			

		//	List<OrderStnDt> orderStnDts = orderStnDtService.findByDesign(orderMt, orderDt, principal, false);

			sb.append("{\"total\":").append(dtList.size()).append(",\"rows\": [");
			
			
			//List<StoneTran> stoneTranList = null;
			
			List<ReadyBag> readyBagList = null;
			
			for (Object[] result : dtList) {
				
				Double bagCarat = 0.0;
				Integer bagStone = 0;
				String setting="";
				String settype="";
				
				if (pIsBagDt == null) {
				} else if (pIsBagDt) {
					readyBagList = readyBagService.findByBagMtAndBagSrNoAndDeactive(bagMt, (Integer) result[10], false);
					if (readyBagList.size() > 0) {
						for (ReadyBag readyBag:readyBagList) {
							bagCarat += readyBag.getCarat();
							bagStone += readyBag.getStone();
							setting = readyBag.getSetting() != null ? readyBag.getSetting().getName():"";
							settype=readyBag.getSettingType() != null ? readyBag.getSettingType().getName():"";
						}
					}
				}
				

				sb.append("{\"id\":\"")
					.append(result[9])
					.append("\",\"partNm\":\"")
					.append(result[16])
					.append("\",\"stoneType\":\"")
					.append(result[4])
					.append("\",\"shape\":\"")
					.append(result[6])
					.append("\",\"subShape\":\"")
					.append((result[7] != null ? result[7] : ""))
					.append("\",\"diaCateg\":\"")
					.append((result[15] != null ? result[15] : ""))
					.append("\",\"quality\":\"")
					.append((result[8] != null ? result[8] : ""))
					.append("\",\"mm\":\"")
					.append((result[12] != null ? result[12] : ""))
					.append("\",\"sieve\":\"")
					.append((result[13] != null ? result[13] : ""))
					.append("\",\"sizeGroup\":\"")
					.append((result[14] != null ? result[14] : ""))
					.append("\",\"stones\":\"")
					.append((result[17] != null ?Math.round((Double.parseDouble(result[17].toString()))) : ""))
					.append("\",\"carat\":\"")
					.append((result[18] != null ? Math.round((Double.parseDouble(result[18].toString()))*1000.0)/1000.0 : ""))
					.append("\",\"bagStones\":\"")
					.append(bagStone)
					.append("\",\"bagCarat\":\"")
					.append(Math.round(bagCarat*1000.0)/1000.0)
					.append("\",\"setting\":\"")
					.append(setting !=""? setting: (result[24] != null ? result[24] : ""))
					.append("\",\"centerStone\":\"")
					.append((result[23] != null ? (result[23] =="1"  ? true : false) : false))
					.append("\",\"setType\":\"")
					.append( settype !=""? settype:(result[25] != null ? result[25] : ""))
					.append("\",\"bagSrno\":\"")
					.append((result[10] != null ? result[10] : ""))
					.append("\",\"trfStone\":\"")
					.append((result[19] != null ?Math.round((Double.parseDouble(result[19].toString()))) : "0"))
					.append("\",\"trfCarat\":\"")
					.append((result[20] != null ? Math.round((Double.parseDouble(result[20].toString()))*1000.0)/1000.0 : "0"))
					.append("\"},");
				
				
				

			}
		}



		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}
	

	
	
	//------ for orderStndt breakup Listing-----------//
	
	
	@RequestMapping("/orderStoneDt/listing")
	@ResponseBody
	public String orderStoneDtListing(Model model,
		@RequestParam(value = "limit", required = false) Integer limit,
		@RequestParam(value = "offset", required = false) Integer offset,
		@RequestParam(value = "sort", required = false) String sort,
		@RequestParam(value = "order", required = false) String order,
		@RequestParam(value = "search", required = false) String search,
		@RequestParam(value = "orderDtId", required = false) Integer orderDtId,Principal principal,
		@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("order");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		/*
		 * String disable = ""; if(canViewFlag){ disable = "disabled = 'disabled'";
		 * }else{ System.err.println("in else view"); }
		 */

	StringBuilder sb = new StringBuilder();
	
	OrderDt orderDt = orderDtService.findOne(orderDtId);
	List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
	
	
	sb.append("{\"total\":").append(orderStnDts.size())
	.append(",\"rows\": [");
	for(OrderStnDt orderStnDt:orderStnDts){
			
		sb.append("{\"id\":\"")
			.append(orderStnDt.getId())
			.append("\",\"partNm\":\"")
			.append((orderStnDt.getPartNm() != null ? orderStnDt.getPartNm().getFieldValue() : ""))
			.append("\",\"stoneType\":\"")
			.append((orderStnDt.getStoneType() != null ? orderStnDt.getStoneType().getName() : ""))
			.append("\",\"shape\":\"")
			.append((orderStnDt.getShape() != null ? orderStnDt.getShape().getName() : ""))
			.append("\",\"subShape\":\"")
			.append((orderStnDt.getSubShape() != null ? orderStnDt.getSubShape().getName() : ""))
			.append("\",\"quality\":\"")
			.append((orderStnDt.getQuality() != null ? orderStnDt.getQuality().getName() : ""))
			.append("\",\"diaCateg\":\"")
			.append((orderStnDt.getDiaCateg() != null ? orderStnDt.getDiaCateg() : ""))
			.append("\",\"size\":\"")
			.append((orderStnDt.getSize() != null ? orderStnDt.getSize() : ""))
			.append("\",\"sieve\":\"")
			.append((orderStnDt.getSieve() != null ? orderStnDt.getSieve() : ""))
			.append("\",\"sizeGroup\":\"")
			.append((orderStnDt.getSizeGroup() != null ? orderStnDt.getSizeGroup().getName() : ""))
			.append("\",\"stone\":\"")
			.append((orderStnDt.getStone() != null ? orderStnDt.getStone() : ""))
			.append("\",\"carat\":\"")
			.append((orderStnDt.getCarat() != null ? orderStnDt.getCarat() : ""))
			.append("\",\"rate\":\"")
			.append((orderStnDt.getStnRate() != null ? orderStnDt.getStnRate() : ""))
			.append("\",\"stoneValue\":\"")
			.append((orderStnDt.getStoneValue() != null ? orderStnDt.getStoneValue() : ""))
			.append("\",\"handlingRate\":\"")
			.append((orderStnDt.getHandlingRate() != null ? orderStnDt.getHandlingRate() : ""))
			.append("\",\"handlingValue\":\"")
			.append((orderStnDt.getHandlingValue() != null ? orderStnDt.getHandlingValue() : ""))
			.append("\",\"setting\":\"")
			.append((orderStnDt.getSetting() != null ? orderStnDt.getSetting().getName() : ""))
			.append("\",\"settingType\":\"")
			.append((orderStnDt.getSettingType() != null ? orderStnDt.getSettingType().getName() : ""))
			.append("\",\"settingRate\":\"")
			.append((orderStnDt.getSetRate() != null ? orderStnDt.getSetRate() : ""))
			.append("\",\"settingValue\":\"")
			.append((orderStnDt.getSetValue() != null ? orderStnDt.getSetValue() : ""))
			.append("\",\"rLock\":\"")
			.append(orderStnDt.getrLock()); //1 = lock & 0 = unlock
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			if(!canViewFlag){
	
			sb.append("\",\"actionLock\":\"")
			.append("<a href='javascript:doStoneDtLockUnLock(")
			.append(orderStnDt.getId())
			.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
			
			.append("\",\"action1\":\"");
			
			sb.append("<a href='javascript:editOrderStnDt(")
			.append(orderStnDt.getId());				
			
			sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			sb.append("\",\"action2\":\"");
			sb.append("<a href='javascript:deleteOrderStnDt(event,").append(orderStnDt.getId());	
			sb.append(");' class='btn btn-xs btn-danger triggerRemove")
			.append(orderStnDt.getId())
			.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			}else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("")
				.append("\",\"actionLock\":\"")
				.append("");
			}
			sb.append("\"},");
			
		}
		else
		{

			
			if(!canViewFlag){
	
			sb.append("\",\"actionLock\":\"")
			.append("<a href='javascript:doStoneDtLockUnLock(")
			.append(orderStnDt.getId())
			.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
			
			.append("\",\"action1\":\"");
			if(roleRights.getCanEdit()){
			sb.append("<a href='javascript:editOrderStnDt(")
			.append(orderStnDt.getId());				
			}else{
				sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
			}
			sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
			sb.append("\",\"action2\":\"");
			if(roleRights.getCanDelete()){
			sb.append("<a href='javascript:deleteOrderStnDt(event,").append(orderStnDt.getId());	
			}else{
				sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
			}
			sb.append(");' class='btn btn-xs btn-danger triggerRemove")
			.append(orderStnDt.getId())
			.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			}else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("")
				.append("\",\"actionLock\":\"")
				.append("");
			}
			sb.append("\"},");
			
		}
		}
	
	
	
	String str = sb.toString();
	str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
			: str);
	str += "]}";

	return str;
	
	
	}
	
	


	//------ for StnInw orderStndt Listing-----------//
	
	
		@RequestMapping("/orderStoneDt/stnInw/listing")
		@ResponseBody
		public String orderStoneDtStnInwListing(Model model,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "orderDtId", required = false) Integer orderDtId,
			@RequestParam(value = "bagQty", required = false) Double bagQty,Principal principal) {
			
			
		StringBuilder sb = new StringBuilder();
		
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
		
		
		sb.append("{\"total\":").append(orderStnDts.size()).append(",\"rows\": [");
		for(OrderStnDt orderStnDt:orderStnDts){
			
			Integer bagStone = (int) Math.round(bagQty*orderStnDt.getStone());
			Double bagCarat = Math.round(bagQty*orderStnDt.getCarat()*1000.0)/1000.0;
				
			sb.append("{\"id\":\"")
				.append(orderStnDt.getId())
				.append("\",\"dtId\":\"")
				.append((orderDtId))
				.append("\",\"srNo\":\"")
				.append(orderStnDt.getSrNo())
				.append("\",\"stoneType\":\"")
				.append((orderStnDt.getStoneType() != null ? orderStnDt.getStoneType().getName() : ""))
				.append("\",\"shape\":\"")
				.append((orderStnDt.getShape() != null ? orderStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((orderStnDt.getQuality() != null ? orderStnDt.getQuality().getName() : ""))
				.append("\",\"diaCateg\":\"")
				.append((orderStnDt.getDiaCateg() != null ? orderStnDt.getDiaCateg() : ""))
				.append("\",\"size\":\"")
				.append((orderStnDt.getSize() != null ? orderStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((orderStnDt.getSieve() != null ? orderStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((orderStnDt.getSizeGroup() != null ? orderStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((orderStnDt.getStone() != null ? orderStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((orderStnDt.getCarat() != null ? orderStnDt.getCarat() : ""))
				.append("\",\"bagStone\":\"")
				.append((bagStone))
				.append("\",\"bagCarat\":\"")
				.append((bagCarat))
				.append("\",\"rate\":\"")
				.append("0.0")
				.append("\",\"rLock\":\"")
				.append(orderStnDt.getrLock()); //1 = lock & 0 = unlock
				sb.append("\"},");
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
		
		}

	private Model populateModel(Model model) {
	
		model.addAttribute("groupList", designGroupService.getDesignGroupList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("settingMap", settingService.getSettingList(0));
		model.addAttribute("settingTypeMap", settingTypeService.getSettingTypeList());

		return model;
	}
	
	
	
/*	@RequestMapping("/add/quotStnDt")
	public String addQuotStnDtPage(Model model,@RequestParam(value = "quotMtId") Integer quotMtId){
		
		QuotMt quotMt = quotMtService.findOne(quotMtId);
		if(quotMt != null){
			
			List<QuotMetal> quotMetals = quotMetalService.findByQuotMtAndDeactive(quotMt, false);
			
			Map<Integer, String> map = new HashMap<Integer, String>();
			for(QuotMetal quotMetal:quotMetals){
				map.put(quotMetal.getPartNm().getId(), quotMetal.getPartNm().getFieldValue());
			}
			model.addAttribute("lookUpMastStoneMap", map);
			
		}
		
		model.addAttribute("partMap", lookUpMastService.getActiveLookUpMastByType("Design Part"));
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		//model.addAttribute("sizeGroupMap", sizeGroupService.getSizeGroupList(shapeId));
		return "quotStnDt/add";
	}*/
	

	@RequestMapping("/orderStnDt/add")
	public String add(Model model,@RequestParam(value = "orderDtId") Integer orderDtId) {
		
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		
		if(orderDt != null){
			List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderDt, false);
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(OrderMetal orderMetal:orderMetals){
				map.put(orderMetal.getPartNm().getId(), orderMetal.getPartNm().getFieldValue());
			}
			model.addAttribute("lookUpMastStoneMap", map);
			
		}
		
		model.addAttribute("partMap", lookUpMastService.getActiveLookUpMastByType("Design Part"));
		
		model = populateModel(model);

		return "orderStnDt/add";
	}
	
	@RequestMapping(value="/orderStnDt/changeStoneType",method=RequestMethod.POST)
	@ResponseBody
	public String changeStoneType(@Valid @ModelAttribute("orderStnDt") OrderStnDt orderStnDt,
			BindingResult result,RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value="vStoneTypeId")Integer vStoneTypeId,
			@RequestParam(value="vInvNo")String vInvNo){
		
		
		
		
		String retVal="-1";
		
		if(vStoneTypeId==null){
			return "-2";
		}
		
		
		OrderMt orderMt=orderMtService.findByInvNoAndDeactive(vInvNo,false);
		if(orderMt !=null){
			List<OrderStnDt>orderStnDts =orderStnDtService.findByOrderMtAndDeactive(orderMt, false);
			
			for(OrderStnDt stnDt: orderStnDts){
				StoneType stoneType=stoneTypeService.findOne(vStoneTypeId);
				stnDt.setStoneType(stoneType);
				stnDt.setModiBy(principal.getName());
				stnDt.setModiDate(new java.util.Date());
				orderStnDtService.save(stnDt);
			}
			
			retVal="1";
		}
		
		
		
		
		
		return retVal;
	}
	
	
	@RequestMapping(value="/orderStnDt/changePointerStoneType",method=RequestMethod.POST)
	@ResponseBody
	public String changePointerStoneType(
			BindingResult result,RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value="vPointerStoneTypeId")Integer vPointerStoneTypeId,
			@RequestParam(value="vMtId") Integer vMtId){
		
		
		
		
		String retVal="-1";
		
	
		retVal = orderStnDtService.changePointerStoneType(vPointerStoneTypeId, vMtId, principal);
		
		return retVal;
	}

	@RequestMapping(value = "/orderStnDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditStnDt(@Valid 
			@ModelAttribute("orderStnDt") OrderStnDt orderStnDt,
			BindingResult result,
			@RequestParam("id") Integer id,
			@RequestParam("pOrderMtId") Integer orderMtId,
			@RequestParam("pOrderDtId") Integer orderDtId,
			@RequestParam("pSieve") String pSieve,
			@RequestParam("pSizeGroup") String pSizeGroup,
			Principal principal) {

		
		String retVal = "";

		if (result.hasErrors()) {
			return "orderStnDt/add";
		}
		
		try {
			retVal = orderStnDtService.transactionalSave(orderStnDt, id, orderMtId, orderDtId, pSieve, pSizeGroup, principal,netWtWithCompFlg);
		} catch (Exception e) {
			retVal = "error";
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/orderStnDt/lockUnlock")
	public String lockUnlockQuotStnDt(
			@RequestParam(value="stnDtId")Integer stnDtId){
		
		String retVal = "-1";
		
		
		OrderStnDt orderStnDt = orderStnDtService.findOne(stnDtId);
		
			if(orderStnDt.getrLock() == true){
				orderStnDt.setrLock(false);
			}else{
				orderStnDt.setrLock(true);
			}
			
		orderStnDtService.save(orderStnDt);
		
		return retVal;
	}
	
	
	
	@RequestMapping("/orderStnDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "stnId")Integer stnId){
		
		String retVal = "";
		OrderStnDt orderStnDt = orderStnDtService.findOne(stnId);
		if(orderStnDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	
	
	
	
	
	

	@RequestMapping(value="/orderStnDt/edit/{id}")
	public String edit(@PathVariable int id, Model model,
			@RequestParam(value = "opt", required = false) String opt) {

		OrderStnDt orderStnDt = null;
		String retVal = "orderStnDt/add";

		if (id != 0) {
			orderStnDt = orderStnDtService.findOne(id);

			model.addAttribute("orderStnDt", orderStnDt);

			model = populateModel(model);

			if (orderStnDt != null) {
				Shape shape = orderStnDt.getShape();
				model.addAttribute("subShapeMap", subShapeService.getSubShapeList(shape.getId()));
				model.addAttribute("qualityMap", qualityService.getQualityList(shape.getId()));
				model.addAttribute("sizeGroupMap", sizeGroupService.getSizeGroupList(shape.getId()));
				model.addAttribute("chartMap", stoneChartService.getStoneChartList(shape.getId()));

				if (orderStnDt.getSizeGroup() != null) {
					model.addAttribute("sizeGroupName", sizeGroupService.findOne(orderStnDt.getSizeGroup().getId()).getName());
					model.addAttribute("sieve", orderStnDt.getSieve());	
				} 
				
				
				List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderStnDt.getOrderDt(), false);
				Map<Integer, String> map = new HashMap<Integer, String>();
				
				for(OrderMetal orderMetal:orderMetals){
					map.put(orderMetal.getPartNm().getId(), orderMetal.getPartNm().getFieldValue());
				}
				model.addAttribute("lookUpMastStoneMap", map);
				
				
			}			
		}

		if (opt == null) {
			retVal = "orderStnDt/add";
		} else if (opt.equals("dbg")) {
			retVal = "diaBagStnDt/add";
		}

		return retVal;
	}

	@RequestMapping("/orderStnDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		String retVal = "-1";
		OrderStnDt orderStnDt = orderStnDtService.findOne(id);
		try {
			orderStnDtService.transactionDelete(orderStnDt,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "-2";
		}
		
		return retVal;
	}
	
	
	
	
	//-------stone rate--------//
		@ResponseBody
		@RequestMapping("/orderStnDt/stoneRateFromMaster")
		public String stoneRate(
				@RequestParam(value="qualityId") Integer qualityId,
				@RequestParam(value="sizeGroupStr") String sizeGroupStr,
				@RequestParam(value="shapeId") Integer shapeId,
				@RequestParam(value="mtPartyId") Integer partyId,
				@RequestParam(value="stoneTypeId") Integer stoneTypeId,
				@RequestParam(value="sieve") String sieve){
			
			String retVal = "0.0";
			
			Shape shape = shapeService.findOne(shapeId);
			SizeGroup sizeGroup = sizeGroupService.findByShapeAndNameAndDeactive(shape, sizeGroupStr, false);
			List<StoneRateMast> stoneRateMast = stoneRateMastService.getStoneRate(stoneTypeId,shapeId,qualityId, sizeGroup.getId(),partyId,sieve);
			
			if(stoneRateMast.size() > 0){
				if(stoneRateMast.get(0).getPerPcRate() > 0){
					retVal = stoneRateMast.get(0).getPerPcRate().toString();
				}else{
					retVal = stoneRateMast.get(0).getStoneRate().toString();
				}
			}
			
			return retVal;
		}
		
		
		//--------handling rate-------//
		@ResponseBody
		@RequestMapping("/orderStnDt/handlingRateFromMaster")
		public String handlingRateMast(
				@RequestParam(value="orderDtId") Integer orderDtId,
				@RequestParam(value="partId") Integer partId,
				@RequestParam(value="shapeId") Integer shapeId,
				@RequestParam(value="stoneTypeId") Integer stoneTypeId,
				@RequestParam(value="mtPartyId") Integer partyId){
			
			String retVal = "0.0";
			
			OrderDt orderDt = orderDtService.findOne(orderDtId);
			LookUpMast lookUpMast = lookUpMastService.findOne(partId);
			Shape shape = shapeService.findOne(shapeId);
			StoneType stoneType = stoneTypeService.findOne(stoneTypeId);
			Party party = partyService.findOne(partyId);
			
			OrderMetal orderMetal =orderMetalService.findByOrderDtAndDeactiveAndPartNm(orderDt,false,lookUpMast);
			
			HandlingMaster handlingMaster = handlingMasterService.findByPartyAndMetalAndStoneTypeAndShapeAndDeactive(party,
					orderMetal.getPurity().getMetal(),stoneType, shape, false);
			
			if(handlingMaster != null){
				if(handlingMaster.getPerCarate() > 0){
					retVal = handlingMaster.getPerCarate().toString();
				}else{
					retVal = handlingMaster.getPercentage().toString();
				}
			}
			
			return retVal;
		}
		
		
		//--------setting rate-------//
		@ResponseBody
		@RequestMapping("/orderStnDt/settingRateFromMaster")
		public String settingRateMast(
				@RequestParam(value="orderDtId") Integer orderDtId,
				@RequestParam(value="partId") Integer partId,
				@RequestParam(value="shapeId") Integer shapeId,
				@RequestParam(value="stoneTypeId") Integer stoneTypeId,
				@RequestParam(value="settingId") Integer settingId,
				@RequestParam(value="settingTypeId") Integer settingTypeId,
				@RequestParam(value="qualityId") Integer qualityId,
				@RequestParam(value="mtPartyId") Integer partyId,
				@RequestParam(value="stone", required=true) String stone,
				@RequestParam(value="carat", required=true) String carat){
			
			String retVal = "0.0";
			
			OrderDt orderDt 			= orderDtService.findOne(orderDtId);
			LookUpMast lookUpMast 	= lookUpMastService.findOne(partId);
			Shape shape 			= shapeService.findOne(shapeId);
			StoneType stoneType 	= stoneTypeService.findOne(stoneTypeId);
			Party party 			= partyService.findOne(partyId);
			Setting setting			= settingService.findOne(settingId);
			SettingType settingType = settingTypeService.findOne(settingTypeId);
			Quality quality			= qualityService.findOne(qualityId);
			OrderMetal orderMetal 	= orderMetalService.findByOrderDtAndDeactiveAndPartNm(orderDt,false,lookUpMast);
			
			Integer stoneVal = Integer.parseInt(stone);
			Double  caratVal = Double.parseDouble(carat);
			
			
			if(orderMetal != null){
				
				Double pointerWt = Math.round((caratVal/stoneVal)*1000.0)/1000.0 ;
				
				List<SettingCharge> settingChargeList = settingChargeService.getRates(party,pointerWt,
						false,orderMetal.getPurity().getMetal(),stoneType,shape,setting,settingType);
				
				SettingCharge settingCharge=null;
				
				if(settingChargeList.size()>0){
					settingCharge = settingChargeList.get(0);
				}
				
									
				if(settingCharge != null){
					
					if(settingCharge.getQualityWiseRate().equals(true)){
						
						List<SettingQualityRate>settingQualityRates=settingQualityRateService.findBySettingChargeAndDeactive(settingCharge, false);
						Boolean isAvailable=false;
						for(SettingQualityRate settingQualityRate:settingQualityRates){
							if(settingQualityRate.getQuality().equals(quality)){
								retVal = settingQualityRate.getQualityRate().toString();
								isAvailable=true;
							}
						}
						
						if(isAvailable.equals(false)){
							retVal = settingCharge.getRate().toString();
						}
						
						
					}else{
						retVal = settingCharge.getRate().toString();
					}
				
				}
			
			
			 }
			
			return retVal;
		}
	
	
	
	
		@RequestMapping("/orderStnDt/updateAsPerMaster")
		@ResponseBody
		public String orderStnDtUpdate(@RequestParam(value = "orderDtId", required = false) Integer orderDtId, Principal principal) {
			
			String retVal="-1";
			
			OrderDt orderDt= orderDtService.findOne(orderDtId);
			List<OrderStnDt> orderStnDts= orderStnDtService.findByDesign(orderDt.getOrderMt(), orderDt, principal, false);
			for (OrderStnDt orderStnDt : orderStnDts) {
				
				orderStnDt.setDeactive(true);
				orderStnDt.setDeactiveDt(new Date());
				orderStnDtService.save(orderStnDt);
				
				
			}
			
			
			List<DesignStone> designStones = designStoneService.findByDesign(orderDt.getDesign());
			orderStnDtService.setOrderStnDt(designStones, orderDt.getOrderMt(), orderDt, principal);
			orderDtService.updateQltyDesc(orderDt.getId());
			retVal="1";
		
		
			
	return retVal;
		}
	
	
	

	
	
	@RequestMapping("/orderStone/stonePointer")
	@ResponseBody
	public String stonePointer(@RequestParam(value = "shape", required = false) String shape, 
		@RequestParam(value = "size", required = false) String size) {

		return "" + stoneChartService.findByShapeAndSizeAndDeactive(shapeService.findByName(shape), size,false).getPerStoneWt();
	}
	
	
	

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};
	

	
	
	@ResponseBody
	@RequestMapping("/orderStnDt/updateQlty")
	public String updateQlty(
			@RequestParam(value="orderDtId")Integer orderDtId,
			@RequestParam(value="shapeId")Integer shapeId,
			@RequestParam(value="qualityId")Integer qualityId){
		
		String retVal = "-1";
		
		if((shapeId !=null && shapeId>0) && (qualityId!=null && qualityId >0)){
		
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		Quality quality =qualityService.findOne(qualityId);
		
		List<OrderStnDt>orderStnDtList =orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
		for(OrderStnDt orderStnDt :orderStnDtList){
			
			if(orderStnDt.getShape().getId().equals(shapeId)){
				orderStnDt.setQuality(quality);
				orderStnDtService.save(orderStnDt);
				
			}
			
		}
		orderDtService.updateQltyDesc(orderDt.getId());
		
		retVal="1";
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

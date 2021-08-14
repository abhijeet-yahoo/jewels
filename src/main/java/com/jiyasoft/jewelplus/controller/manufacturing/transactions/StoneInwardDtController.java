package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondBaggingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class StoneInwardDtController {
	
	
	final private static Logger logger = LoggerFactory
			.getLogger(StoneInwardDtController.class);
	
	@Autowired
	private IStoneInwardDtService stoneInwardDtService;
	
	@Autowired
	private IStoneInwardMtService stoneInwardMtService;
	
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
	private IDiamondBaggingService diamondBaggingService;
	
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Value("${allowNegativeDiamond}")
	private Boolean allowNegativeDiamond;
	
	@Value("${diamond.stocktype}")
	private String diamondStockType;
	
	@Value("${companyName}")
	private String companyName;

	
	@ModelAttribute("stoneInwardDt")
	public StoneInwardDt construct(){
		return new StoneInwardDt();
	}
	
			
	@RequestMapping("/stoneInwardDt")
	public String users(Model model) {
		return "stoneInwardDt";
	}
	
	@RequestMapping("/stoneInwardDt/listing")
	@ResponseBody
	public String stoneInwardDtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag, Principal principal) {

		logger.info("StoneInwardDt Listing");

		StringBuilder sb = new StringBuilder();

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		StoneInwardMt stoneInwardMt = stoneInwardMtService.findByInvNoAndDeactive(pInvNo, false);

		List<StoneInwardDt> stoneInwardDts = stoneInwardDtService
				.findByStoneInwardMtAndDeactiveOrderByIdDesc(stoneInwardMt, false);

		sb.append("{\"total\":").append(stoneInwardDts.size()).append(",\"rows\": [");

		if (stoneInwardDts.size() > 0) {
			for (StoneInwardDt stoneInwardDt : stoneInwardDts) {
				
				String ordRef = null;
				String styleNo = null;
				if(stoneInwardDt.getSordDtId() !=  null) {
					OrderDt orderDt = orderDtService.findOne(stoneInwardDt.getSordDtId());
					ordRef = orderDt.getRefNo();
					styleNo = orderDt.getDesign().getMainStyleNo();
				}
				
				
				sb.append("{\"id\":\"")
				.append(stoneInwardDt.getId())
				.append("\",\"refNo\":\"")
				.append((ordRef != null ? ordRef : ""))
				.append("\",\"styleNo\":\"")
				.append((styleNo != null ? styleNo : ""))
				.append("\",\"stoneType\":\"")
				.append((stoneInwardDt.getStoneType() != null ? stoneInwardDt.getStoneType().getName() : ""))
				.append("\",\"shape\":\"")
				.append((stoneInwardDt.getShape() != null ? stoneInwardDt.getShape().getName() : ""))
				.append("\",\"subShape\":\"")
				.append((stoneInwardDt.getSubShape() != null ? stoneInwardDt.getSubShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((stoneInwardDt.getQuality() != null ? stoneInwardDt.getQuality().getName() : ""))
				.append("\",\"stoneChart\":\"")
				.append((stoneInwardDt.getSize() != null ? stoneInwardDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append(stoneInwardDt.getSieve())
				.append("\",\"sizeGroupStr\":\"")
				.append((stoneInwardDt.getSizeGroup() != null ? stoneInwardDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append(stoneInwardDt.getStone())
				.append("\",\"balStone\":\"")
				.append(stoneInwardDt.getBalStone())
				.append("\",\"carat\":\"")
				.append(stoneInwardDt.getCarat())
				.append("\",\"diffCarat\":\"")
				.append(stoneInwardDt.getDiffCarat())
				.append("\",\"balCarat\":\"")
				.append(stoneInwardDt.getBalCarat())
				.append("\",\"rate\":\"")
				.append(stoneInwardDt.getRate())
				.append("\",\"amount\":\"")
				.append(stoneInwardDt.getAmount())
				.append("\",\"packetNo\":\"")
				.append(stoneInwardDt.getPacketNo() != null ? stoneInwardDt.getPacketNo() : "")
				.append("\",\"remark\":\"")
				.append((stoneInwardDt.getRemark() != null ? stoneInwardDt.getRemark() : ""));
				
				
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				 if(!canViewFlag){

					sb.append("\",\"action1\":\"");
							
								sb.append("<a href='javascript:addeditStoneInwDt(").append(stoneInwardDt.getId());
							
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
							sb.append("\",\"action2\":\"");
							
								sb.append("<a  href='javascript:deleteStoneInwDt(event,")
									.append(stoneInwardDt.getId()).append(", 0);' href='javascript:void(0);'");
							
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(stoneInwardDt.getId())
							 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("");
				}
				
						sb.append("\"},");
			}
			else
			{
				 if(!canViewFlag){

						sb.append("\",\"action1\":\"");
								if (roleRights.getCanEdit()) {
									sb.append("<a href='javascript:addeditStoneInwDt(").append(stoneInwardDt.getId());
								} else {
									sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
								}
								sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
								
								sb.append("\",\"action2\":\"");
								if (roleRights.getCanDelete()) {
									sb.append("<a  href='javascript:deleteStoneInwDt(event,")
										.append(stoneInwardDt.getId()).append(", 0);' href='javascript:void(0);'");
								} else {
									sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
								}
								sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(stoneInwardDt.getId())
								 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						.append("");
					}
					
							sb.append("\"},");
				}
				
				
			}
		}else{
			System.out.println("No record found");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
			
	}

	@RequestMapping("/stoneInwardDt/add")
	public String add(Model model) {
		return "stoneInwardDt/add";
	}

	@RequestMapping(value = "/stoneInwardDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("stoneInwardDt") StoneInwardDt stoneInwardDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "vCarat") Double vCarat,
			@RequestParam(value = "vStone") Integer vStone,
			@RequestParam(value = "sizeGroupStr") String sizeGroupStr,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "stoneInwardDt/add";
		}
	
		retVal=stoneInwardDtService.saveStoneInwardDt(stoneInwardDt, id, pInvNo, vCarat, vStone, sizeGroupStr, principal);
		if(retVal.contains(",")){
		String xy[]=retVal.split(",");
		
		redirectAttributes.addAttribute("success",true);
		redirectAttributes.addAttribute("action",xy[1]);
		return xy[0];
		}else{
			return retVal;
		}
		
	}
	
	

	@RequestMapping(value="/stoneInwardDt/addedit/{id}")
	public String edit(@PathVariable int id, Model model) {
		StoneInwardDt stoneInwardDt =new StoneInwardDt();
		if(id>0) {
		stoneInwardDt = stoneInwardDtService.findOne(id);
		}
		model.addAttribute("stoneInwardDt", stoneInwardDt);
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
			
		if (stoneInwardDt != null) {
			
			Shape shape = stoneInwardDt.getShape();
			model.addAttribute("subShapeMap", (stoneInwardDt.getSubShape() == null ? null : subShapeService.getSubShapeList(shape.getId())));
			model.addAttribute("qualityMap", (stoneInwardDt.getQuality() == null ? null : qualityService.getQualityList(shape.getId())));	
			model.addAttribute("stoneChartMap", (stoneInwardDt.getSize() == null ? null : stoneChartService.getStoneChartList(shape.getId())));
			model.addAttribute("sizeGroupMap", (stoneInwardDt.getSizeGroup() == null ? null : sizeGroupService.getSizeGroupList(shape.getId())));
			model.addAttribute("sizeGroupName", (stoneInwardDt.getSizeGroup() == null ? null : stoneInwardDt.getSizeGroup().getName()));
	}
	
		return "stoneInwardDt/add";
	}

	@RequestMapping("/stoneInwardDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		
		
		String retVal =stoneInwardDtService.stoneInwDtDelete(id, principal);
			
		
	
		return retVal;
	}
	
	

	@RequestMapping("/stnInward/addeditRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal) {
	
		
		
		if(dtId>0) {
			StoneInwardDt stoneInwardDt = stoneInwardDtService.findOne(dtId);
			if(stoneInwardDt.getSordDtId()!= null){
				return "-2";
			}else {
				if(stoneInwardDt.getCarat().equals(stoneInwardDt.getBalCarat())) {
					
					User user = userService.findByName(principal.getName());
					UserRole userRole = userRoleService.findByUser(user);
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
					
				
						return  "1";
						
				}else{
				
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
						StoneInwardMt stoneInwardMt = stoneInwardMtService.findOne(mtId);
						Date cDate = stoneInwardMt.getInvDate();
						String dbDate = dateFormat.format(cDate);
					
					Date date = new Date();
					String curDate = dateFormat.format(date);

						if(dbDate.equals(curDate)){
						
							return  "1";
						
										
					}else {
						return  "-1";
					}
					
					
						
						
					}
					
				}else {
					
					return "-3";
					
				}
				
				
				
			}
			
		}else {
			
			
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
		
				return  "1";
				
		}else{
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				StoneInwardMt stoneInwardMt = stoneInwardMtService.findOne(mtId);
				Date cDate = stoneInwardMt.getInvDate();
				String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);

				if(dbDate.equals(curDate)){
				
					return  "1";
				
								
			}else {
				return  "-1";
			}
			
			
				
				
			}
			
		}
		
		
		
		

		

	
		
		
		
	}
	
	
	
	@RequestMapping("/stnInward/addRights")
	@ResponseBody
	public String addRights(
			@RequestParam(value = "pInvNo", required = false) String pInvNo,Principal principal) {
	
		String retVal = "-1";
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			retVal = "1";
			
	}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			StoneInwardMt stoneInwardMt = stoneInwardMtService.findByInvNoAndDeactive(pInvNo, false);
			Date cDate = stoneInwardMt.getInvDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);

			if(dbDate.equals(curDate)){
				
					retVal = "1";
				
							
			}
			
			
		}
	
		
		
		return retVal;
	}
	
	
	

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	//------------------------------>>>>>>
	
	
	@RequestMapping("/stnRecDt/listing")
	@ResponseBody
	public String stnRecDtListing(
		@RequestParam(value = "pStoneType", required = false) String pStoneType,
		@RequestParam(value = "pShape", required = false) String pShape, 
		@RequestParam(value = "pQuality", required = false) String pQuality,
		@RequestParam(value = "pSize", required = false) String pSize,
		@RequestParam(value = "pBagStn", required = false) String pBagStn, 
		@RequestParam(value = "pCarat", required = false) String pCarat, 
		@RequestParam(value = "pPointer", required = false) String pPointer,
		@RequestParam(value = "pOpt", required = false) String pOpt, 
		@RequestParam(value = "pSizeGroup", required = false) String pSizeGroup,
		@RequestParam(value = "pFrRate", required = false) String pFrRate, 
		@RequestParam(value = "pToRate", required = false) String pToRate) {


		logger.info("StoneInwardDt Listing");

		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.###");

		sb.append("{\"total\":").append(stoneInwardDtService.count()).append(",\"rows\": [");

		SizeGroup sizeGroup = null;

		List<StoneInwardDt> stoneInwardDts = null;

		Shape shapeTmp = shapeService.findByName(pShape);
		if (pOpt.equalsIgnoreCase("Size")) {
			
			stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShapeAndQualityAndSizeAndDeactiveOrderByRateDesc(stoneTypeService.findByName(pStoneType), shapeTmp, 
				qualityService.findByShapeAndNameAndDeactive(shapeTmp, pQuality,false), pSize,false);

		} else if (pOpt.equalsIgnoreCase("Group")) {
			pSizeGroup = pSizeGroup.replace("$", "+");
			sizeGroup = sizeGroupService.findByShapeAndNameAndDeactive(shapeTmp, pSizeGroup,false);

			if(sizeGroup != null){

				stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveOrderByRateDesc(stoneTypeService.findByName(pStoneType), shapeTmp,qualityService.findByShapeAndNameAndDeactive(shapeTmp, pQuality,false), sizeGroup,false);
			}
		} else if (pOpt.equalsIgnoreCase("Quality")) {

			stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShapeAndQualityAndDeactiveOrderByRateDesc(stoneTypeService.findByName(pStoneType), shapeTmp,qualityService.findByShapeAndNameAndDeactive(shapeTmp, pQuality,false),false);
			
		}

		
		StoneChart stoneChart = null;
		
		if(stoneInwardDts != null){
		
		for (StoneInwardDt stoneInwardDt : stoneInwardDts) {
			if (stoneInwardDt.getBalCarat() <= 0) {
				continue;
			}


			if (pFrRate.trim().length() > 0 && pToRate.trim().length() > 0) {
				if ((stoneInwardDt.getRate() >= Double.parseDouble(pFrRate)) && 
					(stoneInwardDt.getRate() <= Double.parseDouble(pToRate))) {
				} else {
					continue;
				}					
			}
			
			stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(stoneInwardDt.getShape(), stoneInwardDt.getSize().trim(),false);
			
			sb.append("{\"id\":\"")
				.append(stoneInwardDt.getId())
				.append("\",\"invNo\":\"")
				.append((stoneInwardDt.getStoneInwardMt() != null ? stoneInwardDt.getStoneInwardMt().getInvNo() : ""))	
				.append("\",\"inwardType\":\"")
				.append((stoneInwardDt.getStoneInwardMt().getInwardType() != null ? stoneInwardDt.getStoneInwardMt().getInwardType().getName() : ""))
				.append("\",\"packetNo\":\"")
				.append((stoneInwardDt.getPacketNo() != null ? stoneInwardDt.getPacketNo() : ""))
				.append("\",\"stoneType\":\"")
				.append((stoneInwardDt.getStoneType() != null ? stoneInwardDt.getStoneType().getName() : ""))
				.append("\",\"shape\":\"")
				.append((stoneInwardDt.getShape() != null ? stoneInwardDt.getShape().getName() : ""))
				.append("\",\"subShape\":\"")
				.append((stoneInwardDt.getSubShape() != null ? stoneInwardDt.getSubShape().getName() : ""))	
				.append("\",\"quality\":\"")
				.append((stoneInwardDt.getQuality() != null ? stoneInwardDt.getQuality().getName() : ""))					
				.append("\",\"mm\":\"")
				.append((stoneInwardDt.getSize() != null ? stoneInwardDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append(stoneInwardDt.getSieve())	
				.append("\",\"sizeGroup\":\"")
				.append((stoneInwardDt.getSizeGroup() != null ? stoneInwardDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append(stoneInwardDt.getStone())	
				.append("\",\"carat\":\"")
				.append(df.format(stoneInwardDt.getCarat()))
				.append("\",\"rate\":\"")
				.append(stoneInwardDt.getRate())
				.append("\",\"amount\":\"")
				.append(stoneInwardDt.getAmount())	
				.append("\",\"balStone\":\"")
				.append(stoneInwardDt.getBalStone())	
				.append("\",\"balCarat\":\"")
				.append(df.format(stoneInwardDt.getBalCarat()))
				.append("\",\"trfStone\":\"")
				.append(0.0)
				.append("\",\"trfCarat\":\"")
				.append(0.0)
				.append("\",\"stonePointer\":\"")
				.append(stoneChart != null ? stoneChart.getPerStoneWt() : "")
				
				.append("\"},");
		}
		
	} // ending if
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/stnRecAdjDt/listing")
	@ResponseBody
	public String stnRecAdjDtListing(
			@RequestParam(value = "pStoneType", required = false) String pStoneType,
			@RequestParam(value = "pShape", required = false) String pShape, 
			@RequestParam(value = "pQuality", required = false) String pQuality,
			@RequestParam(value = "pSize", required = false) String pSize, 
			@RequestParam(value = "pBagStn", required = false) String pBagStn, 
			@RequestParam(value = "pCarat", required = false) String pCarat, 
			@RequestParam(value = "pPointer", required = false) String pPointer,
			@RequestParam(value = "pSizeGroup", required = false) String pSizeGroup) {

		//List<StoneInwardDt> stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShape(stoneTypeService.findByName(pStoneType), shapeService.findByName(pShape));

		pSizeGroup = pSizeGroup.replace("$", "+");

		List<StoneInwardDt> stoneInwardDts = stoneInwardDtService.getFifoDetails(pStoneType, pShape, pQuality, pSize, pBagStn, pCarat, pPointer, pSizeGroup);
System.out.println("\n\n\n\n\n" + pStoneType + " - " +  pShape + " - " +  pQuality + " - " +  pSize + " - " +  pBagStn + " - " +  pCarat + " - " +  pPointer + " - " +  pSizeGroup);

		Double[][] numAry = new Double[stoneInwardDts.size()][3];
		Double[][] numAryTmp = new Double[stoneInwardDts.size()][3];
		Double carat = Double.parseDouble(pCarat);
		Double pointer = Double.parseDouble(pPointer);
		Double[][] adjAry = new Double[stoneInwardDts.size()][3];
		Boolean lAdjusted = false;

		/*for (int x = 0; x < adjAry.length; x++) {
			for (int y = 0; y < adjAry[x].length; y++) {
				adjAry[x][y] = 0.0;
			}
		}*/

		int row = 0; 
		for (StoneInwardDt stoneInwardDt : stoneInwardDts) {
			if (stoneInwardDt.getBalCarat() <= 0) {
				continue;
			}

		System.out.println("\n\nstoneInwardDt.getBalStone().doubleValue() ------------------------------------------------------------->> " + stoneInwardDt.getBalStone().doubleValue());
			numAry[row][0] = stoneInwardDt.getBalStone().doubleValue();
			numAry[row][1] = stoneInwardDt.getBalCarat();
			numAry[row][2] = stoneInwardDt.getId().doubleValue();

			numAryTmp[row][0] = stoneInwardDt.getBalStone().doubleValue();
			numAryTmp[row][1] = stoneInwardDt.getBalCarat();
			numAryTmp[row][2] = stoneInwardDt.getId().doubleValue();

			++row;
		}

		outer : for (int x = 0; x < numAry.length; x++) {
			for (int y = 0; y < 1; y++) {
				// Balance available
				if (numAry[x][1] >= carat) {
					adjAry[x][0] = (pointer == 0 ? 0 : (carat / pointer));
					adjAry[x][1] = carat;

					numAry[x][0] = ((numAry[x][0] - (carat / pointer)) > 0 ? (numAry[x][0] - (carat / pointer)) : 0);
					numAry[x][1] = numAry[x][1] - carat;
System.out.println("if - adjAry[x][1] " + adjAry[x][1] + " -- x="+x+ " -------->> adjAry[x][y]" + x + "-" + y + adjAry[x][y] + " carat " + carat + " - " + pointer + " - " + (carat / pointer) + " - " + numAry[x][0]);
					lAdjusted = true;
					break outer;
				} else if (numAry[x][1] < carat) {
					adjAry[x][1] = numAry[x][1];
					adjAry[x][0] = (pointer == 0 ? 0 : numAry[x][1] / pointer);

					numAry[x][0] = ((numAry[x][0] - (numAry[x][1] / pointer)) > 0 ? (numAry[x][0] - (numAry[x][1] / pointer)) : 0);
					carat = carat - numAry[x][1];
					numAry[x][1] = 0.0;
System.out.println("else - adjAry[x][1] " + adjAry[x][1]);
				}
			}
		}

		/*for (int x = 0; x < numAry.length; x++) {
			for (int y = 0; y < numAry[x].length; y++) {
					System.out.println("x="+x + " y="+y + " ------ Balance " + numAry[x][y] + " Actual - " + numAryTmp[x][y] + " Adj - " + adjAry[x][y]);
			}
		}*/

		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < numAry.length; x++) {
			sb.append("{");
			sb.append("\"balStone\":\"")
				.append(numAry[x][0])	
				.append("\",\"balCarat\":\"")
				.append(numAry[x][1]);

			sb.append("\",\"adjStone\":\"")
				.append(adjAry[x][0])	
				.append("\",\"adjCarat\":\"")
				.append(adjAry[x][1]);

			sb.append("\",\"id\":\"")
				.append(numAry[x][2] == null ? 0 : numAry[x][2].intValue())
				.append("\"},");
		}

		String str = "[" + sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "] # " + lAdjusted;

		return str;
	}
	
	
	
	//////////My methods///////////////////
	
	
	
	@RequestMapping("/diamondChangingBreakup/stn/listing")
	@ResponseBody
	public String stoneBreakupDetails(
			@RequestParam(value = "idd", required = false) Integer id) {
		
		System.out.println("in controller printing the id=====>>>>> "+id);

		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(stoneInwardDtService.count()).append(",\"rows\": [");
		
		DiamondBagging diamondBagging = diamondBaggingService.findOne(id);
		StoneType stoneType = stoneTypeService.findOne(diamondBagging.getStoneType().getId());
		Shape shape = shapeService.findOne(diamondBagging.getShape().getId());
		Quality quality = qualityService.findByShapeAndNameAndDeactive(shape, diamondBagging.getQuality().getName(),false);
		String vSize = diamondBagging.getSize();
		
		List<StoneInwardDt> stoneInwardDts = null;
		
		stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShapeAndQualityAndSizeAndDeactive(stoneType, shape, quality, vSize,false);
		
		Integer trfStone = 0;
		Double trfCarat = 0.0;
		String vSetting = diamondBagging.getSetting() != null ? diamondBagging.getSetting().getName():"-";
		String vSettingType = diamondBagging.getSettingType() != null ? diamondBagging.getSettingType().getName():"-";
		
		for (StoneInwardDt stoneInwardDt : stoneInwardDts) {
			
			if (stoneInwardDt.getBalCarat() <= 0) {
				continue;
			}
			
			sb.append("{\"id\":\"")  
			.append(stoneInwardDt.getId())
			.append("\",\"inwardType\":\"")
			.append((stoneInwardDt.getStoneInwardMt().getInwardType() != null ? stoneInwardDt.getStoneInwardMt().getInwardType().getName() : ""))
			.append("\",\"invNo\":\"")
			.append((stoneInwardDt.getStoneInwardMt() != null ? stoneInwardDt.getStoneInwardMt().getInvNo() : ""))
			.append("\",\"stoneType\":\"")
			.append((stoneInwardDt.getStoneType() != null ? stoneInwardDt.getStoneType().getName() : ""))
			.append("\",\"shape\":\"")
			.append((stoneInwardDt.getShape() != null ? stoneInwardDt.getShape().getName() : ""))
			.append("\",\"subShape\":\"")
			.append((stoneInwardDt.getSubShape() != null ? stoneInwardDt.getSubShape().getName() : ""))	
			.append("\",\"quality\":\"")
			.append((stoneInwardDt.getQuality() != null ? stoneInwardDt.getQuality().getName() : ""))					
			.append("\",\"mm\":\"")
			.append((stoneInwardDt.getSize() != null ? stoneInwardDt.getSize() : ""))
			.append("\",\"sieve\":\"")
			.append(stoneInwardDt.getSieve())	
			.append("\",\"sizeGroup\":\"")
			.append((stoneInwardDt.getSizeGroup().getName()))
			.append("\",\"rate\":\"")
			.append(stoneInwardDt.getRate())
			.append("\",\"balStone\":\"")
			.append(stoneInwardDt.getBalStone())	
			.append("\",\"balCarat\":\"")
			.append(stoneInwardDt.getBalCarat())
			.append("\",\"trfStone\":\"")
			.append(trfStone)
			.append("\",\"trfCarat\":\"")
			.append(trfCarat)
			.append("\",\"setting\":\"")
			.append(vSetting)
			.append("\",\"settingType\":\"")
			.append(vSettingType)
			.append("\"},");
			
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	

	
	
	
	
	@RequestMapping(value = "/breakupImpStnAdj/fromStoneInwardDt", method = RequestMethod.POST)
	@ResponseBody
	public String breakUpDiamondTransferFromStoneInwardDt(
			@Valid @ModelAttribute("stoneInwardDt") StoneInwardDt StoneInwardDtt,
			BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "pODIds") String pOIds,
			@RequestParam(value = "trfBalStone") String trfBalStone,
			@RequestParam(value = "trfBalCarat") String trfBalCarat,
			@RequestParam(value = "dbId") Integer dbId,
			@RequestParam(value = "cSetting") String cSetting,
			@RequestParam(value = "cSettingType") String cSettingType,
			@RequestParam(value = "tempDeptNm") String tempDeptNm,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "1";
		

		if (result.hasErrors()) {
			return "diamondChangingBreakup";
		}
		
		synchronized (this) {
			retVal=diamondBaggingService.diamondChangingAddBreakUp(pOIds, trfBalStone, trfBalCarat, dbId, cSetting, cSettingType, tempDeptNm, principal);	
		}
		
		
		
		System.out.println("retVal "+retVal);
		return retVal;	
		
	}
	
	
	
	
	//-------------------Diamond Rate Stock Change-----------------------------------------//
	
	@RequestMapping("/diamondRateStockChange")
	public String diamondRateStockChange(Model model){
		populateModel(model);
		return "diamondRateStockChange";
	}
	
	
	private Model populateModel(Model model) {
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		return model;
	}
	
	
	@RequestMapping(value="/updateStoneInward/rate", method=RequestMethod.POST)
	@ResponseBody
	public String updateStoneRate(
			@RequestParam(value="ids") String stoneInwardIs,
			@RequestParam(value="rate") String rate,
			Principal principal){
		
		String retVal = "-1";
		
		if(stoneInwardIs.length() > 0){
			
			String tempStoneInwardId[] = stoneInwardIs.split(",");
			String tempRate[] = rate.split(",");
			
			for(int i=0;i<tempStoneInwardId.length;i++){
				
				StoneInwardDt stoneInwardDt = stoneInwardDtService.findOne(Integer.parseInt(tempStoneInwardId[i]));
				stoneInwardDt.setLastUpdatedRate(stoneInwardDt.getRate());
				stoneInwardDt.setRate(Double.parseDouble(tempRate[i]));
				stoneInwardDt.setModiBy(principal.getName());
				stoneInwardDt.setModiDt(new java.util.Date());
				
				stoneInwardDtService.save(stoneInwardDt);
				
			}
			
		}else{
			retVal = "-2";
		}
		
		
		
		return retVal;
	}
	
	

	@RequestMapping(value = "/stoneInwardDt/saveOrderStoneDt", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrderStoneDt(
			@RequestParam(value = "pInwardMtid") Integer pInwardMtid,
			@RequestParam(value = "pBagQty") Double pBagQty,
		    @RequestParam(value = "data") String jsonData,
		    @RequestParam(value = "mOpt") String mOpt,
			Principal principal) throws ParseException {
		
		String retVal = "1";
		
		synchronized (this) {
			try {				
			retVal = stoneInwardDtService.saveOrderStoneInward(pInwardMtid, jsonData, pBagQty, mOpt, principal,allowNegativeDiamond,diamondStockType,companyName);
			
			
				/*
				 * if(retVal == "-1"){ retVal = "Data Transfered Successfully"; }
				 */
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning:Error Occoured,Contact Support";
			}
		}
		
		
		
		
		
		return retVal;
		
		
	}
	
	
	
	@RequestMapping(value = "/stoneInwardDt/ordStockAllocation", method = RequestMethod.POST)
	@ResponseBody
	public String ordStockAllocation(
			@RequestParam(value = "pInvNo") String  pInvNo,
		    @RequestParam(value = "data") String jsonData,
			Principal principal) throws ParseException {
		
		String retVal = "-1";
		
		synchronized (this) {
			try {				
			retVal = stoneInwardDtService.ordStockAllocation(pInvNo, jsonData,principal,allowNegativeDiamond,diamondStockType,companyName);
			
			
				/*
				 * if(retVal == "-1"){ retVal = "Data Transfered Successfully"; }
				 */
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning:Error Occoured,Contact Support";
			}
		}
		
		return retVal;
		
		
	}
	
	@RequestMapping(value = "/stoneInwardDt/returnOrdStock", method = RequestMethod.POST)
	@ResponseBody
	public String returnOrdStock(
			@RequestParam(value = "pInvNo") String  pInvNo,
		    @RequestParam(value = "data") String jsonData,
			Principal principal) throws ParseException {
		
		String retVal = "-1";
		
		synchronized (this) {
			try {				
			retVal = stoneInwardDtService.returnOrdStock(pInvNo, jsonData,principal,allowNegativeDiamond,diamondStockType,companyName);
			
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning:Error Occoured,Contact Support";
			}
		}
		
		return retVal;
		
		
	}
	
	
	
	
	
}

package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;
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
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneOutwardMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class StoneOutwardDtController {
	
	
	final private static Logger logger = LoggerFactory
			.getLogger(StoneOutwardDtController.class);
	
	@Autowired
	private IStoneOutwardDtService stoneOutwardDtService;
	
	@Autowired
	private IStoneOutwardMtService stoneOutwardMtService;
	
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
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private IOrderDtService orderDtService;

	/*
	 * @Value("${diamond.stocktype}") private String diamondStockType;
	 */
	
	@Value("${allowNegativeDiamond}")
	private Boolean allowNegativeDiamond;
	
	@Value("${changingIssStk}")
	private String diamondStockType;
	
	
	
	@ModelAttribute("stoneOutwardDt")
	public StoneOutwardDt construct(){
		return new StoneOutwardDt();
	}
	
			
	@RequestMapping("/stoneOutwardDt")
	public String users(Model model) {
		return "stoneOutwardDt";
	}
	
	@RequestMapping("/stoneOutwardDt/listing")
	@ResponseBody
	public String stoneOutwardDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,Principal principal)
			 {
		
		logger.info("StoneOutwardDt Listing");

		StringBuilder sb = new StringBuilder();
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		sb.append("{\"total\":").append(stoneOutwardDtService.count()).append(",\"rows\": [");
		
		String disable = "";
		if(canViewFlag){
			disable = "disabled = 'disabled'";
		}else{
			System.err.println("in else view");
		}
		
		StoneOutwardMt stoneOutwardMt = stoneOutwardMtService.findByInvNoAndDeactive(pInvNo, false);
		
		List<StoneOutwardDt> stoneOutwardDts = stoneOutwardDtService.findByStoneOutwardMtAndDeactiveOrderByIdDesc(stoneOutwardMt, false);
			
		if(stoneOutwardDts.size() > 0){
				for (StoneOutwardDt stoneOutwardDt : stoneOutwardDts) {
					
					String ordRef = null;
					String styleNo = null;
					if(stoneOutwardDt.getSordDtId() !=  null) {
						OrderDt orderDt = orderDtService.findOne(stoneOutwardDt.getSordDtId());
						ordRef = orderDt.getRefNo();
						styleNo = orderDt.getDesign().getMainStyleNo();
					}
					
					
					sb.append("{\"id\":\"")
							.append(stoneOutwardDt.getId())
							.append("\",\"refNo\":\"")
							.append((ordRef != null ? ordRef : ""))
							.append("\",\"styleNo\":\"")
							.append((styleNo != null ? styleNo : ""))
							.append("\",\"stoneType\":\"")
							.append((stoneOutwardDt.getStoneType() != null ? stoneOutwardDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((stoneOutwardDt.getShape() != null ? stoneOutwardDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((stoneOutwardDt.getSubShape() != null ? stoneOutwardDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((stoneOutwardDt.getQuality() != null ? stoneOutwardDt.getQuality().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((stoneOutwardDt.getSize() != null ? stoneOutwardDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(stoneOutwardDt.getSieve())	
							.append("\",\"sizeGroupStr\":\"")
							.append((stoneOutwardDt.getSizeGroup() != null ? stoneOutwardDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(stoneOutwardDt.getStone())
							.append("\",\"balStone\":\"")
							.append(stoneOutwardDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(stoneOutwardDt.getCarat())
							.append("\",\"diffCarat\":\"")
							.append(stoneOutwardDt.getDiffCarat())
							.append("\",\"balCarat\":\"")
							.append(stoneOutwardDt.getBalCarat())
							.append("\",\"rate\":\"")
							.append(stoneOutwardDt.getRate1())
							.append("\",\"amount\":\"")
							.append(stoneOutwardDt.getAmount())	
							.append("\",\"packetNo\":\"")
							.append(stoneOutwardDt.getPacketNo() !=null ? stoneOutwardDt.getPacketNo() : ""  )	
							.append("\",\"remark\":\"")
							.append((stoneOutwardDt.getRemark() != null ? stoneOutwardDt.getRemark() : ""));
									
							sb.append("\",\"action1\":\"");
							if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
									userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
								if(!canViewFlag) {
								sb.append("<a href='javascript:editStoneOutwDt(").append(stoneOutwardDt.getId());
							
							sb.append(");' class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
							sb.append("\",\"action2\":\"");
							
								sb.append("<a href='javascript:deleteStoneOutwDt(event,")
								  .append(stoneOutwardDt.getId()).append(", 0);' href='javascript:void(0);'");
							
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(stoneOutwardDt.getId())
							 .append("' "+disable+"><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					
							
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
					if(!canViewFlag) {
					if (roleRights.getCanEdit()) {
						sb.append("<a href='javascript:editStoneOutwDt(").append(stoneOutwardDt.getId());
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append("<a href='javascript:deleteStoneOutwDt(event,")
						  .append(stoneOutwardDt.getId()).append(", 0);' href='javascript:void(0);'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(stoneOutwardDt.getId())
					 .append("' "+disable+"><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
					
			}else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("");
			}
			sb.append("\"},");
					
					
					}
				}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/stoneOutwardDt/add")
	public String add(Model model) {
		return "stoneOutwardDt/add";
	}

	@RequestMapping(value = "/stoneOutwardDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("stoneOutwardDt") StoneOutwardDt stoneOutwardDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "vCarat") Double vCarat,
			@RequestParam(value = "vStone") Integer vStone,
			@RequestParam(value = "prevCarat") Double prevCarat,
			@RequestParam(value = "sizeGroupStr") String sizeGroupStr,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "stoneOutwardDt/add";
		}
	
		retVal=stoneOutwardDtService.saveStoneOutwardDt(stoneOutwardDt, id, pInvNo, vCarat, prevCarat,vStone, sizeGroupStr, principal,diamondStockType,allowNegativeDiamond);
		
		if(retVal.contains(",")){
			String xy[]=retVal.split(",");
			
			redirectAttributes.addAttribute("success",true);
			redirectAttributes.addAttribute("action",xy[1]);
			return xy[0];
			}else{
				return retVal;
			}
			
		}
		

	@RequestMapping(value="/stoneOutwardDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		StoneOutwardDt stoneOutwardDt = stoneOutwardDtService.findOne(id);
		model.addAttribute("stoneOutwardDt", stoneOutwardDt);
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
			
		if (stoneOutwardDt != null) {
			
			Shape shape = stoneOutwardDt.getShape();
			model.addAttribute("subShapeMap", (stoneOutwardDt.getSubShape() == null ? null : subShapeService.getSubShapeList(shape.getId())));
			model.addAttribute("qualityMap", (stoneOutwardDt.getQuality() == null ? null : qualityService.getQualityList(shape.getId())));	
			model.addAttribute("stoneChartMap", (stoneOutwardDt.getSize() == null ? null : stoneChartService.getStoneChartList(shape.getId())));
			model.addAttribute("sizeGroupMap", (stoneOutwardDt.getSizeGroup() == null ? null : sizeGroupService.getSizeGroupList(shape.getId())));
			model.addAttribute("sizeGroupName", (stoneOutwardDt.getSizeGroup() == null ? null : stoneOutwardDt.getSizeGroup().getName()));
	}
	
		return "stoneOutwardDt/add";
	}

	@RequestMapping("/stoneOutwardDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		
		
		String retVal = "-1";
		StoneOutwardDt stoneOutwardDt = stoneOutwardDtService.findOne(id);
		
		if(stoneOutwardDt.getSordDtId() == null){
		
			retVal = stoneOutwardDtService.stoneOutDtDelete(id, principal);
			
			if(retVal != "-1"){
				return "/jewels/manufacturing/transactions/stoneOutwardMt/edit/"+stoneOutwardDt.getStoneOutwardMt().getId()+".html";
			}
		}else {
			retVal ="-2";
		}
	
			

		return retVal;
	}
	
	

	@RequestMapping("/stnOutward/editRights")
	@ResponseBody
	public String editRights(
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "dtId", required = false) Integer dtId,Principal principal)  {
	
		String retVal = "-1";
		StoneOutwardDt stoneOutwardDt = stoneOutwardDtService.findOne(dtId);
		
		if(stoneOutwardDt.getSordDtId() == null){
			
		
		
	if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
			
	}else{
	
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//			StoneOutwardMt stoneOutwardMt = stoneOutwardMtService.findOne(mtId);
//			Date cDate = stoneOutwardMt.getInvDate();
//			String dbDate = dateFormat.format(cDate);
//			
//			Date date = new Date();
//			String curDate = dateFormat.format(date);
//
//			if(dbDate.equals(curDate)){
//				
//					retVal = "1";
//							
//			}
		
		retVal = "1";
			
			}
		}else {
			retVal ="-2";
		}
	
		
		
		return retVal;
	}
	
	
	
	@RequestMapping("/stnOutward/addRights")
	@ResponseBody
	public String addRights(
			@RequestParam(value = "pInvNo", required = false) String pInvNo,Principal principal) {
	
		String retVal = "-1";
		
	if(principal.getName().equalsIgnoreCase("Administrator")){
			retVal = "1";
			
	}else{
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			StoneOutwardMt stoneOutwardMt = stoneOutwardMtService.findByInvNoAndDeactive(pInvNo, false);
			Date cDate = stoneOutwardMt.getInvDate();
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
	

	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}

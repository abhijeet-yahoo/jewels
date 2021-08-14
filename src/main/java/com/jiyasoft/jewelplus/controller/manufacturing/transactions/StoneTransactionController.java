package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class StoneTransactionController {
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private IDepartmentService departmentService; 
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private ISubShapeService subShapeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Value("${diamond.stocktype}")
	private String diamondStockType;
	
	@ModelAttribute("stoneTran")
	public StoneTran construct() {
		return new StoneTran();
	}
	
	@RequestMapping("/stoneTransactions")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneTransactions");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		/*
		 * if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true"); }else {
		 * model.addAttribute("canEditTranddate", "false"); }
		 */
		
		model.addAttribute("canEditTranddate", "false");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("departmentMap", departmentService.getDepartmentListForStoneTran());
			model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
			model.addAttribute("shapeMap", shapeService.getShapeList());

		}
		return "stoneTransactions";
	}
	
	@RequestMapping("/stoneTransactions/subShape/list")
	@ResponseBody
	public String productSubShapeList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneTran") StoneTran stoneTran) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> subShapeMap = subShapeService
				.getSubShapeList(shapeId);

		sb.append("<select id=\"subShape.id\" name=\"subShape.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select SubShape -</option>");
		for (Object key : subShapeMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(subShapeMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@RequestMapping("/stoneTransactions/quality/list")
	@ResponseBody
	public String productQualityList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneTran") StoneTran stoneTran) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> qualityMap = qualityService
				.getQualityList(shapeId);

		sb.append("<select id=\"quality.id\" name=\"quality.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Quality -</option>");
		for (Object key : qualityMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(qualityMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	@RequestMapping("/stoneTransactions/stoneChart/list")
	@ResponseBody
	public String productStoneChartList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneTran") StoneTran stoneTran) {

		StringBuilder sb = new StringBuilder();
		Map<String, String> stoneChartMap = stoneChartService
				.getStoneChartList(shapeId);

		// sb.append("<select id=\"size.id\" name=\"size.id\" class=\"form-control\">");

		sb.append("<select id=\"size\" name=\"size\" class=\"form-control\" onChange=\"javascript:getSizeMMDetails();\">");

		sb.append("<option value=\"\">- Select Size -</option>");
		for (Object key : stoneChartMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(stoneChartMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	@RequestMapping("/stoneTransactions/toDept")
	@ResponseBody
	public String fillToDepartment(
			@RequestParam(value = "deptId", required = false) Integer deptId,
			@ModelAttribute("stoneTran") StoneTran stoneTran) {

		return departmentService.getToDepartmentListDropDownForStoneTran(deptId);
	}
	
	@RequestMapping(value = "/stoneTransactions", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("stoneTran") StoneTran stoneTran,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {
 		
		synchronized (this) {
			String retVal = "";
			

			if (result.hasErrors()) {
				return "stoneTransactions";
			}
			
			Department vFromLocation = departmentService.findOne(stoneTran.getDeptLocation().getId());
			StoneType vStoneType = stoneTypeService.findOne(stoneTran.getStoneType().getId());
			Shape vShape = shapeService.findOne(stoneTran.getShape().getId());
			Quality vQuality = qualityService.findOne(stoneTran.getQuality().getId());
			
			
			
			
			StoneChart stnChart = stoneChartService.findByShapeAndSizeAndDeactive(vShape, stoneTran.getSize(), false);
			
			if(stoneTran.getCarat() > 0){
							
				Double balCarat = stoneTranService.getStockBalance(vFromLocation.getId(), vStoneType.getId(), vShape.getId(), vQuality.getId(), stnChart.getSizeGroup().getId(), stnChart.getSieve(), "size");
				
				
				
					if(balCarat >= stoneTran.getCarat()){
						
						
						List<StoneTran>stoneTrans =stoneTranService.getDiamondStockRate(vFromLocation.getId(),vStoneType.getName(),
								vShape.getName(), vQuality.getName(), stnChart.getSize(), stnChart.getSieve());
						
						
						Double adjCarat = stoneTran.getCarat();
						Integer adjStone= stoneTran.getStone();
						
						
					/*
					 * Double valSum=0.0; Double caratSum=0.0; for (StoneTran stoneTran2 :
					 * stoneTrans) {
					 * 
					 * valSum +=
					 * Math.round((stoneTran2.getBalCarat()*stoneTran2.getRate())*100.0)/100.0;
					 * caratSum += Math.round(stoneTran2.getBalCarat()*1000.0)/1000.0;
					 * 
					 * 
					 * }
					 * 
					 * Double avgRate= Math.round((valSum/caratSum)*100.0)/100.0;
					 */
						
						for(StoneTran stoneTran2 :stoneTrans) {
							
							if (adjCarat > 0) {
								if (stoneTran2.getBalCarat() >= adjCarat) {
									
									StoneTran stoneTranDiaLoc = new StoneTran();
									stoneTranDiaLoc.setAvgRate(stoneTran2.getRate());
									stoneTranDiaLoc.setLotNo(stoneTran2.getLotNo());
									
									if(stoneTran.getTranDate() == null){
										stoneTranDiaLoc.setTranDate(new Date());
									}else{
										stoneTranDiaLoc.setTranDate(stoneTran.getTranDate());
									}
									
									
									stoneTranDiaLoc.setDepartment(stoneTran.getDepartment());
									stoneTranDiaLoc.setDeptLocation(vFromLocation);
									stoneTranDiaLoc.setStoneType(stoneTran2.getStoneType());
									stoneTranDiaLoc.setShape(stoneTran2.getShape());
									stoneTranDiaLoc.setQuality(stoneTran2.getQuality());
									stoneTranDiaLoc.setSize(stoneTran2.getSize());
									stoneTranDiaLoc.setSieve(stoneTran2.getSieve());
									stoneTranDiaLoc.setSizeGroup(stoneTran2.getSizeGroup());
									stoneTranDiaLoc.setStone(adjStone);
									stoneTranDiaLoc.setCarat(Math.round(adjCarat * 1000.0) / 1000.0);
									stoneTranDiaLoc.setBalStone(adjStone * -1);
									stoneTranDiaLoc.setBalCarat((Math.round(adjCarat * 1000.0) / 1000.0) * -1);
									stoneTranDiaLoc.setRate(stoneTran2.getRate());
									stoneTranDiaLoc.setInOutFld("D");
									stoneTranDiaLoc.setTranType("LooseDiamond");
									stoneTranDiaLoc.setCreatedDt(new Date());
									stoneTranDiaLoc.setCreatedBy(principal.getName());
									stoneTranDiaLoc.setRemark(stoneTran.getRemark().trim());
									stoneTranDiaLoc.setPacketNo(stoneTran.getPacketNo());
									
									if (stoneTran.getSubShape().getId() == null) {
										stoneTranDiaLoc.setSubShape(null);
									}else {
										stoneTranDiaLoc.setSubShape(stoneTran.getSubShape());
									}
									
									stoneTranService.save(stoneTranDiaLoc);
									
									
									StoneTran stoneTranBagLoc = new StoneTran();
									stoneTranBagLoc.setAvgRate(stoneTran2.getRate());
									stoneTranBagLoc.setLotNo(stoneTran2.getLotNo());
										
									if(stoneTran.getTranDate() == null){
										stoneTranBagLoc.setTranDate(new Date());
									}else{
										stoneTranBagLoc.setTranDate(stoneTran.getTranDate());
									}
									
									
									stoneTranBagLoc.setDepartment(vFromLocation);
									stoneTranBagLoc.setDeptLocation(stoneTran.getDepartment());
									stoneTranBagLoc.setStoneType(stoneTran2.getStoneType());
									stoneTranBagLoc.setShape(stoneTran2.getShape());
									stoneTranBagLoc.setQuality(stoneTran2.getQuality());
									stoneTranBagLoc.setSize(stoneTran2.getSize());
									stoneTranBagLoc.setSieve(stoneTran2.getSieve());
									stoneTranBagLoc.setSizeGroup(stoneTran2.getSizeGroup());
									stoneTranBagLoc.setStone(adjStone);
									stoneTranBagLoc.setCarat(Math.round(adjCarat * 1000.0) / 1000.0);
									stoneTranBagLoc.setBalStone(adjStone);
									stoneTranBagLoc.setBalCarat(Math.round(adjCarat * 1000.0) / 1000.0);
									stoneTranBagLoc.setRate(stoneTran2.getRate());
									stoneTranBagLoc.setInOutFld("C");
									stoneTranBagLoc.setTranType("LooseDiamond");
									stoneTranBagLoc.setCreatedDt(new Date());
									stoneTranBagLoc.setCreatedBy(principal.getName());
									stoneTranBagLoc.setPacketNo(stoneTran.getPacketNo());
									stoneTranBagLoc.setRemark(stoneTran.getRemark().trim());
									
								   if (stoneTran.getSubShape().getId() == null) {
										stoneTranBagLoc.setSubShape(null);
									}else {
										stoneTranBagLoc.setSubShape(stoneTran.getSubShape());
									}
									stoneTranService.save(stoneTranBagLoc);

									break;
									
									
								}else {
									
									adjCarat = Math.round((adjCarat - stoneTran2.getBalCarat()) * 1000.0) / 1000.0;
									adjStone = Math.round(adjStone - stoneTran2.getBalStone());
									
									
									
									StoneTran stoneTranDiaLoc = new StoneTran();
									stoneTranDiaLoc.setAvgRate(stoneTran2.getRate());
									stoneTranDiaLoc.setLotNo(stoneTran2.getLotNo());
									
									if(stoneTran.getTranDate() == null){
										stoneTranDiaLoc.setTranDate(new Date());
									}else{
										stoneTranDiaLoc.setTranDate(stoneTran.getTranDate());
									}
									
									
									stoneTranDiaLoc.setDepartment(stoneTran.getDepartment());
									stoneTranDiaLoc.setDeptLocation(vFromLocation);
									stoneTranDiaLoc.setStoneType(stoneTran2.getStoneType());
									stoneTranDiaLoc.setShape(stoneTran2.getShape());
									stoneTranDiaLoc.setQuality(stoneTran2.getQuality());
									stoneTranDiaLoc.setSize(stoneTran2.getSize());
									stoneTranDiaLoc.setSieve(stoneTran2.getSieve());
									stoneTranDiaLoc.setSizeGroup(stoneTran2.getSizeGroup());
									stoneTranDiaLoc.setStone(stoneTran2.getBalStone());
									stoneTranDiaLoc.setCarat(Math.round(stoneTran2.getBalCarat() * 1000.0) / 1000.0);
									stoneTranDiaLoc.setBalStone(stoneTran2.getBalStone() * -1);
									stoneTranDiaLoc.setBalCarat((Math.round(stoneTran2.getBalCarat() * 1000.0) / 1000.0) * -1);
									stoneTranDiaLoc.setRate(stoneTran2.getRate());
									stoneTranDiaLoc.setInOutFld("D");
									stoneTranDiaLoc.setTranType("LooseDiamond");
									stoneTranDiaLoc.setCreatedDt(new Date());
									stoneTranDiaLoc.setCreatedBy(principal.getName());
									stoneTranDiaLoc.setRemark(stoneTran.getRemark().trim());
									stoneTranDiaLoc.setPacketNo(stoneTran.getPacketNo());
									
									if (stoneTran.getSubShape().getId() == null) {
										stoneTranDiaLoc.setSubShape(null);
									}else {
										stoneTranDiaLoc.setSubShape(stoneTran.getSubShape());
									}
									
									stoneTranService.save(stoneTranDiaLoc);
									
									
									
									StoneTran stoneTranBagLoc = new StoneTran();
									stoneTranBagLoc.setAvgRate(stoneTran2.getRate());
									stoneTranBagLoc.setLotNo(stoneTran2.getLotNo());
										
									if(stoneTran.getTranDate() == null){
										stoneTranBagLoc.setTranDate(new Date());
									}else{
										stoneTranBagLoc.setTranDate(stoneTran.getTranDate());
									}
									
									
									stoneTranBagLoc.setDepartment(vFromLocation);
									stoneTranBagLoc.setDeptLocation(stoneTran.getDepartment());
									stoneTranBagLoc.setStoneType(stoneTran2.getStoneType());
									stoneTranBagLoc.setShape(stoneTran2.getShape());
									stoneTranBagLoc.setQuality(stoneTran2.getQuality());
									stoneTranBagLoc.setSize(stoneTran2.getSize());
									stoneTranBagLoc.setSieve(stoneTran2.getSieve());
									stoneTranBagLoc.setSizeGroup(stoneTran2.getSizeGroup());
									stoneTranBagLoc.setStone(stoneTran2.getBalStone());
									stoneTranBagLoc.setCarat(Math.round(stoneTran2.getBalCarat() * 1000.0) / 1000.0);
									stoneTranBagLoc.setBalStone(stoneTran2.getBalStone());
									stoneTranBagLoc.setBalCarat(Math.round(stoneTran2.getBalCarat() * 1000.0) / 1000.0);
									stoneTranBagLoc.setRate(stoneTran2.getRate());
									stoneTranBagLoc.setInOutFld("C");
									stoneTranBagLoc.setTranType("LooseDiamond");
									stoneTranBagLoc.setCreatedDt(new Date());
									stoneTranBagLoc.setCreatedBy(principal.getName());
									stoneTranBagLoc.setPacketNo(stoneTran.getPacketNo());
									stoneTranBagLoc.setRemark(stoneTran.getRemark().trim());
									
								   if (stoneTran.getSubShape().getId() == null) {
										stoneTranBagLoc.setSubShape(null);
									}else {
										stoneTranBagLoc.setSubShape(stoneTran.getSubShape());
									}
									stoneTranService.save(stoneTranBagLoc);
									
									
								}
							}
							
						}
						
						
						
						 
							
				
							
							 retVal = "1";
							 
							 
						 
					}else{
					retVal = "Stock Not Available, stock Available ("+balCarat+")";
				}
			}
			
			return retVal;
	
		}
		
			}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}

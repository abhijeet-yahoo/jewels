package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class DesignStoneController {

	final private static Logger logger = LoggerFactory.getLogger(DesignStoneController.class);

	@Autowired
	private IDesignService designService;;

	@Autowired
	private IDesignStoneService designStoneService;;

	
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
	private IDesignGroupService designGroupService;
	
	@Autowired
	private IDesignMetalService designMetalService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("designStone")
	public DesignStone construct() {
		return new DesignStone();
	}

	@RequestMapping("/designStone")
	public String users(Model model) {
		return "designStone";
	}

	@RequestMapping("/designStone/listing")
	@ResponseBody
	public String designStoneListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "styleNo", required = false) String styleNo,
			@RequestParam(value = "version", required = false) String version,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {

		logger.info("DesignStone Listing");

		StringBuilder sb = new StringBuilder();
		Design design = designService.findByStyleNoAndVersion(styleNo, version);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		String disable = "";
		if(canViewFlag){
			disable = "disabled = 'disabled'";
		}else{
			System.err.println("in else view");
		}
		
		//	Long rowCount = null;
			if (design != null) {
				if (search == null) {
					search = "" + design.getId();
				}
				
				
			
		//	rowCount = designStoneService.count(sort, search, false);
	
			
			List<DesignStone> designStones = designStoneService.findByDesign(design);
			
			sb.append("{\"total\":").append(designStones.size()).append(",\"rows\": [");

			for (DesignStone designStone : designStones) {

				sb.append("{\"id\":\"")
					.append(designStone.getId())
					.append("\",\"partNm\":\"")
					.append((designStone.getPartNm() != null ? designStone.getPartNm().getFieldValue() : ""))
					.append("\",\"stoneType\":\"")
					.append((designStone.getStoneType() != null ? designStone.getStoneType().getName() : ""))
					.append("\",\"shape\":\"")
					.append((designStone.getShape() != null ? designStone.getShape().getName() : ""))
					.append("\",\"diaCateg\":\"")
					.append((designStone.getDiaCateg() != null ? designStone.getDiaCateg() : ""))
					.append("\",\"subShape\":\"")
					.append((designStone.getSubShape() != null ? designStone.getSubShape().getName() : ""))
					.append("\",\"quality\":\"")
					.append((designStone.getQuality() != null ? designStone.getQuality().getName() : ""))
					.append("\",\"breadth\":\"")
					.append((designStone.getBreadth() != null ? designStone.getBreadth() : ""))
					.append("\",\"mm\":\"")
					.append((designStone.getSize() != null ? designStone.getSize() : ""))
					.append("\",\"sieve\":\"")
					.append((designStone.getSieve() != null ? designStone.getSieve() : ""))
					.append("\",\"sizeGroup\":\"")
					.append((designStone.getSizeGroup() != null ? designStone.getSizeGroup().getName() : ""))
					.append("\",\"stones\":\"")
					.append((designStone.getStone() != null ? designStone.getStone() : ""))
					.append("\",\"carat\":\"")
					.append((designStone.getCarat() != null ? designStone.getCarat() : ""))
					.append("\",\"mcarat\":\"")
					.append((designStone.getMcarat() != null ? designStone.getMcarat() : ""))
					.append("\",\"stnRate\":\"")
					.append((designStone.getStnRate() != null ? designStone.getStnRate() : ""))
					.append("\",\"setting\":\"")
					.append((designStone.getSetting() != null ? designStone.getSetting().getName() : ""))
					.append("\",\"setType\":\"")
					.append((designStone.getSettingType() != null ? designStone.getSettingType().getName() : ""))
					.append("\",\"centerStone\":\"")
					.append((designStone.getCenterStone() != null ? (designStone.getCenterStone() ? designStone.getCenterStone() : false) : false))
					.append("\",\"deactive\":\"")
					.append((designStone.getDeactive() == null ? "Active" : (designStone.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((designStone.getDeactiveDt() == null ? "" : designStone.getDeactiveDt()));
					/*.append("\",\"action1\":\"")
					.append("<a href='javascript:editDStone(")
					.append(designStone.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:designDtDelete(event, ").append(designStone.getId()).append(", 1);' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(designStone.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");*/
					
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					if(!canViewFlag){
						
						sb.append("\",\"action1\":\"");
	
							sb.append("<a href='javascript:editDStone(")
								.append(designStone.getId());
						
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						
						sb.append("\",\"action2\":\"");
					
							sb.append("<a onClick='javascript:designDtDelete(event, ").append(designStone.getId()).append(", 1);' href='javascript:void(0);'");
						
						sb.append("class='btn btn-xs btn-danger triggerRemove")
						 .append(designStone.getId())
						 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						 
					
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"");
						
					}	
						sb.append("\"},");
				}else{
					
					if(!canViewFlag){
						
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='javascript:editDStone(")
								.append(designStone.getId());
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append("<a onClick='javascript:designDtDelete(event, ").append(designStone.getId()).append(", 1);' href='javascript:void(0);'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append("class='btn btn-xs btn-danger triggerRemove")
						 .append(designStone.getId())
						 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						 
					
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"");
						
					}	
					sb.append("\"},");
				}
				
				
			
				
			}
			}
	

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	private Model populateModel(Model model) {
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("settingMap", settingService.getSettingList(0));
		model.addAttribute("settingTypeMap", settingTypeService.getSettingTypeList());

		return model;
	}

	@RequestMapping("/designStone/add")
	public String add(Model model,@RequestParam(value = "designId") Integer designId) {
		
		//System.err.println("designId === "+designId);
		
		Design design = designService.findOne(designId);
		if(design != null){
			
			List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
			Map<Integer, String> map = new HashMap<Integer, String>();
			for(DesignMetal designMetal:designMetals){
				map.put(designMetal.getPartNm().getId(), designMetal.getPartNm().getFieldValue());
			}
			model.addAttribute("lookUpMastStoneMap", map);
			
		}
		
		model.addAttribute("groupList", designGroupService.getDesignGroupList());
		model = populateModel(model);
		return "designStone/add";
	}

	@RequestMapping(value = "/designStone/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("designStone") DesignStone designStone,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "dsStyleNo", required = false) String dsStyleNo,
			@RequestParam(value = "dsVersion", required = false) String dsVersion,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		synchronized (this) {
			
			if (result.hasErrors()) {
				return "designStone/add";
			}

			if (id == null || id.equals("") || (id != null && id == 0)) {
				designStone.setCreatedBy(principal.getName());
				designStone.setCreateDate(new java.util.Date());
				designStone.setDesign(designService.findByStyleNoAndVersion(dsStyleNo, dsVersion));

				retVal = "1";
			} else {
				designStone.setId(id);
				designStone.setModiBy(principal.getName());
				designStone.setModiDt(new java.util.Date());
				designStone.setDesign(designService.findByStyleNoAndVersion(dsStyleNo, dsVersion));

				action = "updated";
				retVal = "1";
			}

			if (designStone.getSubShape() != null && designStone.getSubShape().getId() == null) {
				designStone.setSubShape(null);
			}
			if (designStone.getQuality().getId() == null) {
				designStone.setQuality(null);
			}
			
			
			if (designStone.getDiaCateg() == null) {
				designStone.setDiaCateg("");
			}
			
			
			if (designStone.getSetting().getId() == null) {
				designStone.setSetting(null);
			}
			if (designStone.getSettingType().getId() == null) {
				designStone.setSettingType(null);
			}

			if (designStone.getSizeGroupStr().trim().length() == 0) {
				designStone.setSizeGroup(null);
			} else {
				designStone.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(designStone.getShape(), designStone.getSizeGroupStr(),false));			
			}
			
			
			designStoneService.save(designStone);

			retVal=designService.updateCarat(designStone.getDesign());			
			
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
			
		}
		

		return retVal;
	}

	@RequestMapping(value="/designStone/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		DesignStone designStone = null;

		model = populateModel(model);
		if (id != 0) {
			designStone = designStoneService.findOne(id);
			model.addAttribute("designStone", designStone);

			if (designStone != null) {
				Shape shape = designStone.getShape();
				model.addAttribute("subShapeMap", subShapeService.getSubShapeList(shape.getId()));
				model.addAttribute("qualityMap", qualityService.getQualityList(shape.getId()));
				model.addAttribute("chartMap", stoneChartService.getStoneChartList(shape.getId()));
				model.addAttribute("sizeGroupMap", sizeGroupService.getSizeGroupList(shape.getId()));
				model.addAttribute("sizeGroupName", (designStone.getSizeGroup() == null ? null : designStone.getSizeGroup().getName()));
				
				List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(designStone.getDesign(), false);
				Map<Integer, String> map = new HashMap<Integer, String>();
				for(DesignMetal designMetal:designMetals){
					map.put(designMetal.getPartNm().getId(), designMetal.getPartNm().getFieldValue());
				}
				model.addAttribute("lookUpMastStoneMap", map);
				
			}			
		}

		return "designStone/add";
	}
	
	

	@ResponseBody
	@RequestMapping("/designStone/updateQlty")
	public String updateQlty(
			@RequestParam(value="designId")Integer designId,
			@RequestParam(value="shapeId")Integer shapeId,
			@RequestParam(value="qualityId")Integer qualityId){
		
		String retVal = "-1";
		
		if((shapeId !=null && shapeId>0) && (qualityId!=null && qualityId >0)){
		
		Design design = designService.findOne(designId);
		Quality quality =qualityService.findOne(qualityId);
		
		List<DesignStone>designStoneList =designStoneService.findByDesign(design);

		for(DesignStone designStone : designStoneList){
			if(designStone.getShape().getId().equals(shapeId)){
				designStone.setQuality(quality);
				designStoneService.save(designStone);
				
			}
			
		}
		
		
		retVal="1";
		}
		
		return retVal;
	}

	@RequestMapping("/designStone/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		designStoneService.delete(id);

		return "1";
	}
	
	
	@RequestMapping("/updateDesignStnRate")
	@ResponseBody
	public String updateStoneRate(
			@RequestParam(value = "styleId")Integer styleId){
		
		String retVal = "-1";
		
		try {
			
			Design design = designService.findOne(styleId);
			List<DesignStone> designStones = designStoneService.findByDesign(design);
			
			
			
			
			/*for(DesignStone designStone : designStones){
				StoneRateMast stoneRateMast  = stoneRateMastService.findByStoneTypeAndShapeAndQualityAndSizeAndDeactive(designStone.getStoneType(), 
						designStone.getShape(), designStone.getQuality(), designStone.getSize(), false);
				
				if(stoneRateMast != null){
					designStone.setStnRate(stoneRateMast.getStoneRate());
				}
				
				designStoneService.save(designStone);
				
			}*/
			
		} catch (Exception e) {
			retVal = "-2";
			System.out.println(e);
			
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

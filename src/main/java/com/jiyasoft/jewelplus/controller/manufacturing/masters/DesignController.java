package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCost;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICollectionService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IConceptService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMarketTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPatternMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubCategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubConceptService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmailConceptService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RequestMapping("/manufacturing/masters")
@Controller
public class DesignController {
	
	@Autowired
	private IDesignService designService;

	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IDesignComponentService designComponentService;
	
	@Autowired
	private IDesignMetalService designMetalService;

	@Autowired
	private IOrderDtService orderDtService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private IDesignGroupService designGroupService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ISubCategoryService subCategoryService;

	@Autowired
	private IConceptService conceptService;
	
	@Autowired
	private ICollectionService collectionService;

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
	private IComponentService componentService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;

	@Autowired
	private ISubConceptService subConceptService;

	@Autowired
	private IProductSizeService productSizeService;

	@Autowired
	private IEmailConceptService emailConceptService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private ILookMastService lookMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IPatternMastService patternMastService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IMarketTypeService marketTypeService;
	
	@Autowired
	private IProductTypeService productTypeService;


	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	private String uploadFilePath = "";

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;
		
	@Value("${companyName}")
	private String companyName;
	
	@Value("${stylenoautogeneration}")
	private Boolean stylenoautogeneration;
	

	@ModelAttribute("design")
	public Design construct() {
		return new Design();
	}

	@ModelAttribute("designStone")
	public DesignStone constructDesignStone() {
		return new DesignStone();
	}

	@ModelAttribute("designComponent")
	public DesignComponent constructDesignComponent() {
		return new DesignComponent();
	}
	
	@ModelAttribute("designCost")
	public DesignCost constructDesignCost(){
		return new DesignCost();
	}

	@RequestMapping("/design")
	public String users(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("categoryMap", categoryService.getCategoryList());
		model.addAttribute("collectionMap", collectionService.getCollectionList());
		model.addAttribute("vendorMap", partyService.getPartyList());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			if(stylenoautogeneration) {
				model.addAttribute("stylenoautogeneration", stylenoautogeneration);
			}
			
			model.addAttribute("canAdd", true);	
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
	
			return "design";
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			
			if(stylenoautogeneration) {
				model.addAttribute("stylenoautogeneration", stylenoautogeneration);
			}
			
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
			return "design";
		}
		
		
	}
	}

	@RequestMapping("/design/listing")
	@ResponseBody
	public String designListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = false) String opt,Principal principal) {
		
				
		
		StringBuilder sb = new StringBuilder();
		Page<Design> designs = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	
		designs = designService.findAll(limit, offset, sort, order, search);
		
		
		sb.append("{\"total\":").append(designs.getTotalElements()).append(",\"rows\": [");
		
	
		
		for (Design design : designs) {
			
			String imgName = null;;
			imgName = design.getDefaultImage();
			
			sb.append("{\"id\":\"")
					.append(design.getId())
					.append("\",\"group\":\"")
					.append((design.getDesignGroup() != null ? design.getDesignGroup().getName() : ""))
					.append("\",\"designDate\":\"")
					.append(design.getDesignDtStr())
					.append("\",\"designNo\":\"")
					.append(design.getDesignNo())
					.append("\",\"size\":\"")
					.append(design.getProductSize() != null ? design.getProductSize().getName() :"")
					.append("\",\"styleNo\":\"")
					.append(design.getStyleNo())
					.append("\",\"refNo\":\"")
					.append(design.getRefNo() !=null?design.getRefNo() :"")
					.append("\",\"mainStyleNo\":\"")
					.append(design.getMainStyleNo() !=null? design.getMainStyleNo() :"")
					.append("\",\"version\":\"")
					.append(design.getVersion() != null ? design.getVersion() :"")
					.append("\",\"designer\":\"")
					.append(design.getDesignerEmployee() != null ?design.getDesignerEmployee().getName() : "" )
					.append("\",\"category\":\"")
					.append(design.getCategory() == null ? "" : design.getCategory().getName())
					.append("\",\"subCategory\":\"")
					.append(design.getSubCategory() == null ? "" : design.getSubCategory().getName())
					.append("\",\"concept\":\"")
					.append(design.getConcept() == null ? "" : design.getConcept().getName())
					.append("\",\"uploadImage\":\"");
					if (imgName != null) {
						sb.append("<a href='/jewels/uploads/manufacturing/design/")
							.append(imgName) 
							.append("' data-lighter class='btn btn-xs btn-info'>")
							.append("View Image</a>");
					}

					sb.append("\",\"deactive\":\"")
					.append((design.getDeactive() == null ? "Active" : (design
							.getDeactive() ? "Deactive" : "Active")));

			if (opt == null || opt.equals("1")) {
				sb.append("\",\"deactive\":\"")
					.append((design.getDeactive() == null ? "Active" : (design.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((design.getDeactiveDt() == null ? "" : design.getDeactiveDt()));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/design/edit/")
							.append(design.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				
				sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:designDtDelete(event, ")
							.append(design.getId()).append(", 0);' href='javascript:void(0);'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(design.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>");
							
					
					
					
				sb.append("\",\"action3\":\"")
							.append("<a href='/jewels/manufacturing/masters/design/view/")
							.append(design.getId()).append(".html'")
							.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
							.append("\"},");
				
				}else{
					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/design/edit/")
							.append(design.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				
				sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append("<a onClick='javascript:designDtDelete(event, ")
							.append(design.getId()).append(", 0);' href='javascript:void(0);'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(design.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>");
							
					
					
					
				sb.append("\",\"action3\":\"");
					if (roleRights.getCanView()) {
						sb.append("<a href='/jewels/manufacturing/masters/design/view/")
							.append(design.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
					.append("\"},");
				}
				
					
					
						
			} else if (opt.equals("2")) {
				sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editODesign(")
					.append(design.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Select</a>")
					.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		
		return str;
	}

	@RequestMapping("/design/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = populateModel(model);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("companyName", companyName);
			
			return "design/add";

		}else {
			
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				model.addAttribute("companyName", companyName);
				
			}
			
		}

		
		

		return "design/add";
	}

	private Model populateModel(Model model) {
		model.addAttribute("groupList", designGroupService.getDesignGroupList());
		model.addAttribute("categoryMap", categoryService.getCategoryList());
		model.addAttribute("conceptMap", conceptService.getConceptList());
		model.addAttribute("collectionMap", collectionService.getCollectionList());

		Department department = departmentService.findByName("design");
		model.addAttribute("designerMap", (department == null ? null : employeeService.getEmployeeList(department.getId())));

		Department depModel = departmentService.findByName("model");
		model.addAttribute("modelMap", (depModel == null ? null : employeeService.getEmployeeList(depModel.getId())));
		
		Department depCad = departmentService.findByName("cad");
		model.addAttribute("cadDesignerMap", (depCad == null ? null : employeeService.getEmployeeList(depCad.getId())));
		
		Department depCadQc = departmentService.findByName("cad");
		model.addAttribute("depCadQcMap", (depCadQc == null ? null : employeeService.getEmployeeList(depCadQc.getId())));
		
		Department depCamQc = departmentService.findByName("cam");
		model.addAttribute("cadQcMap", (depCamQc == null ? null : employeeService.getEmployeeList(depCamQc.getId())));
		 
		Department depSizeing = departmentService.findByName("sizing");
		model.addAttribute("sizeingMap", (depSizeing == null ? null : employeeService.getEmployeeList(depSizeing.getId())));
		
		Department deptMerchandiser = departmentService.findByName("design");
		model.addAttribute("deptMerchandiserMap", (deptMerchandiser == null ? null : employeeService.getEmployeeList(deptMerchandiser.getId())));

		// for stone details
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("settingMap", settingService.getSettingList(0));
		model.addAttribute("settingTypeMap", settingTypeService.getSettingTypeList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("patternMap", patternMastService.getPatternList());
		model.addAttribute("lookMap", lookMastService.getLookList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("purityWeightMap", purityService.getPurityWeightList(1));
		
		model.addAttribute("defaultPurity", purityService.getDefaultPurity().getName());
		model.addAttribute("waxWtConv", purityService.getDefaultPurity().getWaxWtConv());
		
		
		model.addAttribute("emailConceptMap", emailConceptService.getEmailConceptList());
		
		//----//--costing tab--//
		model.addAttribute("purityMapForGold", purityService.getPurityList(1)); //(caution)metal id(Gold) is hard coded  
		
		model.addAttribute("processMap", lookUpMastService.getActiveLookUpMastByType("Mfg Process"));
		
		model.addAttribute("marketTypeMastMap", marketTypeService.getMarketTypeMastList());
		model.addAttribute("productTypeMastMap", productTypeService.getProductTypeMastList());
		model.addAttribute("vendorMap", partyService.getPartyList());
		model.addAttribute("stylenoautogeneration", stylenoautogeneration);
		return model;
	}

	

	@RequestMapping("/subCategory/list")
	@ResponseBody
	public String subCategoryList(@RequestParam(value = "catId") Integer catId,
			@ModelAttribute("design") Design design) {

		return subCategoryService.getSubCategoryListDropDown(catId);
	}

	@RequestMapping("/subConcept/list")
	@ResponseBody
	public String subConceptList(@RequestParam(value = "conId") Integer conId,
			@ModelAttribute("design") Design design) {

		return subConceptService.getSubConceptListDropDown(conId);
	}

	@RequestMapping("/productSize/list")
	@ResponseBody
	public String productSizeList(@RequestParam(value = "catId") Integer catId,
			@ModelAttribute("design") Design design) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> productSizeMap = productSizeService
				.getProductSizeList(catId);

		sb.append("<select id=\"productSize.id\" name=\"productSize.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Size -</option>");
		for (Object key : productSizeMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(productSizeMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	private void checkImage(String imgName) {
		String uploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "design"
				+ File.separator;

		File fn = new File(uploadFilePath + "__" + imgName);
		if (fn.exists()) {
			File ff = new File(uploadFilePath + imgName);
			if (ff.exists()) {
				ff.delete();
			}

			fn.renameTo(new File(uploadFilePath + imgName));
		}
	}

	@RequestMapping(value = "/design/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("design") Design design,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "invImg1", required = false) String invImg1,
			@RequestParam(value = "invImg2", required = false) String invImg2,
			@RequestParam(value = "invImg3", required = false) String invImg3,
			@RequestParam(value = "defaultImage", required = false) String defaultImage,
			@RequestParam(value = "oldStyleNo", required = false) String oldStyleNo,
			@RequestParam(value = "defImgChk", required = false) Integer defImgChk,
			@RequestParam(value = "vendorId", required = false) Integer vendorId,
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "collectionId", required = false) Integer collectionId){

		String action = "added";
		String retVal = "";
		
		if (result.hasErrors()) {
			return "design/add";
		}
		
		checkImage(invImg1);
		checkImage(invImg2);
		checkImage(invImg3);
		
		String barcodeuploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator +"Design"+File.separator+ "barcode"
				+ File.separator;


		
		Boolean designNoAvailable = true;
		
		designNoAvailable =designService.checkDesignDuplicate(design.getStyleNo(), design.getVersion(), id);
		if(designNoAvailable.equals(false)){
			redirectAttributes.addFlashAttribute("success", false);
			redirectAttributes.addFlashAttribute("action", "Style No Duplicate---- Can Not Save Please Check");
			return  "redirect:/manufacturing/masters/design/add.html";
		}
		
		
		
		designNoAvailable =designService.checkDuplicateDesignNo(design.getDesignNo(), design.getVersion(), id);
		if(designNoAvailable.equals(false)){
			redirectAttributes.addFlashAttribute("success", false);
			redirectAttributes.addFlashAttribute("action", "Design No Duplicate---- Can Not Save Please Check");
			return  "redirect:/manufacturing/masters/design/add.html";
		}
		
		
		
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			design.setImage1(invImg1);
			design.setImage2(invImg2);
			design.setImage3(invImg3);
			design.setDefaultImage(null);
			design.setCreatedBy(principal.getName());
			design.setCreateDate(new Date());
			design.setUniqueId(new Date().getTime());
	

			/*if(design.getVersion() == null) {
				design.setVersion("0");
			}else{
				design.setVersion(design.getVersion().trim());
			}*/
			
			
			if (design.getCategory().getId() == null) {
				design.setCategory(null);
			}
			
			
		} else {
		//	design.setId(id);
			design.setModiBy(principal.getName());
			design.setModiDt(new java.util.Date());
			design.setDefaultImage(defaultImage);
		
			
			if (invImg1.trim().length() > 0) {
				design.setImage1(invImg1);
			}else{
				design.setImage1("blank.jpg");
			}
			
			if (invImg2.trim().length() > 0) {
				design.setImage2(invImg2);
			}else{
				design.setImage2("blank.jpg");
			}
			
			if (invImg3.trim().length() > 0) {
				design.setImage3(invImg3);
			}else{
				design.setImage3("blank.jpg");
			}
			
			if(defImgChk != null){
				design.setDefImgChk(defImgChk);
			}
			
			if(oldStyleNo != null){
				design.setOldStyleNo(oldStyleNo);
			}
			
			if(design.getBarCode() != null){
				Utils.barcodeGeneration(design.getId(), design.getBarCode(), barcodeuploadFilePath);
			}
			
			
			
			
			action = "updated";
			retVal = "redirect:/manufacturing/masters/design/edit/"+id+".html";
		}
		
		
		design.setModelModiDt(design.getModelModiDt());
	
		if(stylenoautogeneration) {
			
			if(vendorId != null) {
				Party party =  partyService.findOne(vendorId);	
				design.setVendor(party);
			}
			
			Category category = categoryService.findOne(categoryId);
			CollectionMaster collectionMaster = collectionService.findOne(collectionId);
			
			
			design.setCategory(category);
			design.setCollectionMaster(collectionMaster);
			
		}else {
			
			if (design.getCollectionMaster() != null) {
				if (design.getCollectionMaster().getId() == null) {
					design.setCollectionMaster(null);
				}
				}
			
			
			
		}
		
	
		if (design.getVendor() != null) {
			if (design.getVendor().getId() == null) {
				design.setVendor(null);
			}
			}
		
		 design.setDesignNo(design.getDesignNo().trim());
		 design.setStyleNo(design.getStyleNo().trim());
		
		 if(design.getVersion() != null && !design.getVersion().trim().isEmpty()){
			 design.setVersion(design.getVersion().trim());
				design.setMainStyleNo(design.getStyleNo()+"_"+design.getVersion());
		 }else{
				design.setMainStyleNo(design.getStyleNo());
		 }
		 
		 design.setPatternMast(null);
		 design.setLookMast(null);
		 design.setSizerEmployee(null);
		 
		 if(design.getParty().getId() == null){
			 design.setParty(null);
		 }
		 
		 if(design.getMarketTypeMast() != null) {
		 if(design.getMarketTypeMast().getId() ==  null) {
			 design.setMarketTypeMast(null);
		 }
		 }
		 
		 if(design.getProductTypeMast() != null) {
		 if(design.getProductTypeMast().getId() == null) {
			 design.setProductTypeMast(null);
		 } 
		 }
	
		
		/*
		 * if (design.getPatternMast().getId() == null) { design.setPatternMast(null); }
		 * 
		 * if (design.getLookMast().getId() == null) { design.setLookMast(null); }
		 */
			
	if (design.getSubCategory() != null) {		
		
		if (design.getSubCategory().getId() == null) {
			design.setSubCategory(null);
		}
	}
	
		if (design.getDesignGroup().getId() == null) {
			design.setDesignGroup(null);
		}
		
	if(design.getConcept() != null) {
		if (design.getConcept().getId() == null) {
			design.setConcept(null);
		}
	}	
		
		if (design.getProductSize().getId() == null) {
			design.setProductSize(null);
		}
		if (design.getSubConcept() != null && design.getSubConcept().getId() == null) {
			design.setSubConcept(null);
		}
		if (design.getDesignerEmployee().getId() == null) {
			design.setDesignerEmployee(null);
		}
		if (design.getModelMakerEmployee().getId() == null) {
			design.setModelMakerEmployee(null);
		}
		
	
		
		
		/*
		 * if (design.getSizerEmployee().getId() == null) {
		 * design.setSizerEmployee(null); }
		 */
		if ((design.getEmailConcept() == null) || design.getEmailConcept().getId() == null) {
			design.setEmailConcept(null);
		}
		
		if (design.getProcess().getId() == null) {
			design.setProcess(null);
		}
		
		if (design.getCadDesigner().getId() == null) {
			design.setCadDesigner(null);
		}
		
		if (design.getMerchandiser().getId() == null) {
			design.setMerchandiser(null);
		}
		
		/*
		 * if (design.getCamQc().getId() == null) { design.setCamQc(null); }
		 * 
		 * if (design.getCadQc().getId() == null) { design.setCadQc(null); }
		 */
	
		if (design.getPurity().getId() == null) {
			design.setPurity(null);
		}
		
	
		
		design.setCamQc(null);
		design.setCadQc(null);
		
		
		design.setRefNo(design.getRefNo().replaceAll("[\\n\\t\\r ]", " ").trim());
		
		
		designService.save(design);
		
		if (action.equals("added")) {
			
			Design designTmp = designService.findByUniqueId(design.getUniqueId());
			if(designTmp.getBarCode() != null){
				Utils.barcodeGeneration(designTmp.getId(), designTmp.getBarCode(), barcodeuploadFilePath);
			}
			
			DesignMetal designMetal =new DesignMetal();
			designMetal.setColor(colorService.findByDefColorAndDeactive(true, false));
			designMetal.setCreateDate(new Date());
			designMetal.setCreatedBy(principal.getName());
			designMetal.setDesign(designTmp);
			designMetal.setMainMetal(true);
			designMetal.setLossPerc(15.0);
			designMetal.setMetalPcs(1);
			designMetal.setPartNm(lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", "Main Part", false));
			designMetal.setPurity(purityService.findByDefPurityAndDeactive(true, false));
			designMetalService.save(designMetal);
			
			
			retVal = "redirect:/manufacturing/masters/design/edit/"+ designTmp.getId() + ".html";
		}
		
		

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/design/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = editDetails(id, model, principal);
		model = populateModel(model);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			if(stylenoautogeneration) {
				model.addAttribute("stylenoautogeneration", stylenoautogeneration);
			}else {
				model.addAttribute("canAdd", true);	
			}
					
			
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("companyName", companyName);
			
			return "design/add";

		}else {
			
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				
				if(stylenoautogeneration) {
					model.addAttribute("stylenoautogeneration", stylenoautogeneration);
				}else {
					model.addAttribute("canAdd", roleRights.getCanEdit());	
				}
						
				
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				model.addAttribute("companyName", companyName);
				
			}
			
		}
			
	
		
		

		return "design/add";
	}
	
	
	
	/*@RequestMapping("/design/addM/{id}")
	public String addMode(@PathVariable int id, Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		model.addAttribute("addMode", true);
		model = editDetails(id, model, principal);
		model = populateModel(model);

		return "design/add";
	}*/
	
	
	
	@RequestMapping("/design/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = editDetails(id, model, principal);
		model = populateModel(model);
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "design/add";

		}else {
			
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				
			}
			
		}

		
	

		return "design/add";
	}
	
	
	

	private Model editDetails(int id, Model model, Principal principal) {
		Design design = designService.findOne(id);
		String tmpRemarks = design.getRemarks();

		String ttStr = "";
		if (tmpRemarks != null) {
			for (int x=0; x<tmpRemarks.length(); x++) {
				if (((int) tmpRemarks.charAt(x)) == 0) {
					continue;
				} else {
					ttStr += tmpRemarks.substring(x, (x+1));
				}
			}
	
			tmpRemarks = ttStr;
			ttStr = "";
			for (int x=0; x<tmpRemarks.length(); x++) {
				if (tmpRemarks.charAt(x) == ' ') {
					if (((x+1) < tmpRemarks.length()) && tmpRemarks.charAt(x+1) == ' ') {
						continue;
					} else {
						ttStr += tmpRemarks.substring(x, (x+1));					
					}
				} else {
					ttStr += tmpRemarks.substring(x, (x+1));
				}
			}
		}

		design.setRemarks(ttStr);
		model.addAttribute("design", design);
		
		if (design.getCategory() != null) {
			
			model.addAttribute("subCategoryMap", (subCategoryService.getSubCategoryList(design.getCategory().getId())));
			model.addAttribute("productSizeMap", (productSizeService.getProductSizeList(design.getCategory().getId())));	
			
			/*if (design.getProductSize() != null) {
				model.addAttribute("productSizeMap", (design.getProductSize() == null ?  productSizeService.getProductSizeList(design.getCategory().getId()) : productSizeService.getProductSizeList(design.getCategory().getId())));
			}*/
		}
		
		if (design.getConcept() != null) {
			model.addAttribute("subConceptMap", (design.getSubConcept() == null ? subConceptService.getSubConceptList(design.getConcept().getId()) : subConceptService.getSubConceptList(design.getConcept().getId())));
		}

		// Added domain name for tomcat
		String uploadFilePath = "/jewels/"+uploadParentDirecotryName + File.separator + uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		
		tmpUploadImage = tmpUploadImage.replaceAll("\\\\", "/");

		String imgTmp = "";
		imgTmp = (design.getImage1() == null ? tmpUploadImage : (design.getImage1() != null && design.getImage1().trim().length() == 0) ? tmpUploadImage : design.getImage1());
		
		
		model.addAttribute("image1", imgTmp);
		imgTmp = (design.getImage2() == null ? tmpUploadImage : (design.getImage2() != null && design.getImage2().trim().length() == 0) ? tmpUploadImage : design.getImage2());
		model.addAttribute("image2", imgTmp);
		imgTmp = (design.getImage3() == null ? tmpUploadImage : (design.getImage3() != null && design.getImage3().trim().length() == 0) ? tmpUploadImage : design.getImage3());
		model.addAttribute("image3", imgTmp);
		//imgTmp = (design.getDefaultImage() == null ? tmpUploadImage : (design.getDefaultImage() != null && design.getDefaultImage().trim().length() == 0) ? tmpUploadImage : design.getDefaultImage());
		
		String defTmp = "";
		
		if(design.getDefaultImage() != null){
			 defTmp = design.getDefaultImage();
		}
		model.addAttribute("defImgg", defTmp);
		
		Integer chk = null;
		
		if(design.getDefImgChk() != null){
			chk = design.getDefImgChk();
		}
		model.addAttribute("defChk", chk);
		
		model.addAttribute("uploadFilePath", uploadFilePath);

		return model;
	}

	@RequestMapping("/design/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		return "" + designService.delete(id);
	}

	// For Order Design Edit
	@RequestMapping("/order/design/edit/{id}")
	public String editOrderDesign(@PathVariable int id, Model model,
			Principal principal) {
		model = editDetails(id, model, principal);
		model = populateModel(model);

		return "orderDesign/add";
	}

	@RequestMapping(value = "/order/design/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditOrderDesign(
			@Valid @ModelAttribute("orderDesign") OrderDt orderDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		if (result.hasErrors()) {
			return "orderDesign/add";
		}
		
		orderDtService.save(orderDt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
		
	}

	// For Order Design Edit

	/**
	 * File upload procedure
	 */
	@RequestMapping(value = "/design/image/upload", method = RequestMethod.POST)
	@ResponseBody
	public String imageUpload(HttpServletRequest request,
			@RequestParam(value = "file", required = false) Part file,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "jid", required = false) String jid,
			Model model) throws IOException, ServletException {
		
		
		uploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "design"
				+ File.separator;
		

		String retVal = Utils.imageUpload(request, file, image, jid, model,uploadFilePath);
		
		return retVal;
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/subShape/list")
	@ResponseBody
	public String subShapeList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("design") Design design) {

		return subShapeService.getSubShapeListDropDown(shapeId);
	}

	@RequestMapping("/quality/list")
	@ResponseBody
	public String qualityList(@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("design") Design design) {

		return qualityService.getQualityListDropDown(shapeId);
	}

	@RequestMapping("/chart/list")
	@ResponseBody
	public String chartList(@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("design") Design design) {

		return stoneChartService.getStoneChartListDropDown(shapeId);
	}

	@RequestMapping("/sizeMM/details")
	@ResponseBody
	public String sizeMMDetails(
			@RequestParam(value = "shapeId") Integer shapeId,
			@RequestParam(value = "size") String size,
			@ModelAttribute("design") Design design) {

		StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(shapeService.findOne(shapeId), size,false);
	
		 String str = (stoneChart.getSieve() != null ? stoneChart.getSieve() : "") + "_" + (stoneChart.getSizeGroup() != null ? stoneChart.getSizeGroup().getName() : "")
				 + "_" + (stoneChart.getPerStoneWt() != null ? stoneChart.getPerStoneWt() : "");
		 
		return str;
	}

	@RequestMapping("/sizeGroup/list")
	@ResponseBody
	public String sizeGroupList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("design") Design design) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> sizeGroupMap = sizeGroupService.getSizeGroupList(shapeId);

		List<Map.Entry<Integer, String>> sizeGroupMapGv = Lists.newArrayList(sizeGroupMap.entrySet());
	    Collections.sort(sizeGroupMapGv, byMapValues);

	    sb.append("<select id=\"sizeGroup.id\" name=\"sizeGroup.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Sub Shape -</option>");

		Iterator<Entry<Integer, String>> iterator = sizeGroupMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}


	@RequestMapping("/design/styleNo/Available")
	@ResponseBody
	public String styleNoAvailable(@RequestParam String styleNo,
			@RequestParam String version,
			@RequestParam Integer id) {
		Boolean styleNoAvailable = true;
		
		styleNoAvailable =designService.checkDesignDuplicate(styleNo, version, id);

	/*	styleNo = styleNo.trim();
		version = version.trim();


		if (id == null) {
			styleNoAvailable = (designService.findByStyleNoAndVersion(styleNo, version) == null);
		} else {
			Design design = designService.findOne(id);
			if (!(styleNo.equalsIgnoreCase(design.getStyleNo().trim()) && version.equalsIgnoreCase(design.getVersion().trim()))) {
				styleNoAvailable = (designService.findByStyleNoAndVersion(styleNo, version) == null);
			}
		}
*/
		return styleNoAvailable.toString();
	}
	
	
	@RequestMapping("/design/designNo/Available")
	@ResponseBody
	public String designNoAvailable(@RequestParam String designNo,
			@RequestParam String version,
			@RequestParam Integer id) {
		Boolean designNoAvailable = true;
		designNo = designNo.trim();
		version = version.trim();
		
		
		if (id == null) {
			designNoAvailable = (designService.findByDesignNoAndVersionAndDeactive(designNo, version, false) == null);
			} 
		else {
			
			Design design = designService.findOne(id);
			
			if (!(designNo.equalsIgnoreCase(design.getDesignNo().trim()) && version.equalsIgnoreCase(design.getVersion().trim()))) {
				designNoAvailable = (designService.findByDesignNoAndVersionAndDeactive(designNo, version, false) == null);
			}
		}

		return designNoAvailable.toString();
	}
	
	
	

	@RequestMapping("/design/getStyleId")
	@ResponseBody
	public String getStyleId(@RequestParam String styleNo) {

		styleNo = styleNo.trim();
		
		String sId = "-1";

		Design design = designService.findByMainStyleNoAndDeactive(styleNo,false);
		if (design != null) {
			sId = "" + design.getId();
		}

		return sId;
	}

	@RequestMapping("/designNo/list")
	@ResponseBody
	public String styleNoList(@RequestParam(value = "term", required = false) String designNo) {
		Page<Design> designList = designService.findByDesignNo(15, 0, "designNo", "ASC", designNo.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (Design design : designList) {
			sb.append("\"")
				.append(design.getDesignNo())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}

	@RequestMapping("/designStone/stonePointer")
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

	Ordering<Map.Entry<String, String>> byMapValuesStr = new Ordering<Map.Entry<String, String>>() {
		@Override
		public int compare(Map.Entry<String, String> left, Map.Entry<String, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};


	@RequestMapping("/reports/design/listing")
	public String categoryListing(Model model) {
		return "reports/design/listing";
	}

	@RequestMapping("/subCategory/reportList")
	@ResponseBody
	public String subCategoryReportList(@RequestParam(value = "catIds") String catIds,
			@ModelAttribute("design") Design design) {

		return subCategoryService.getSubCategoryReportListDropDown(catIds);
	}
	
	
	@RequestMapping("/version/reportList")
	@ResponseBody
	public String versionList(){
		
	
		
		Query query =  entityManager.createNativeQuery("SELECT distinct SUBSTRING_INDEX(version, '_', -1) as version,0 as style from design where deactive =0 order by version desc");
		@SuppressWarnings("unchecked")
		List<Object[]>versionList =query.getResultList();
		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(1).append(",\"rows\": [");
		
		for (Object[] result : versionList) {
			int j=0;	
			String flg="false";
			for(Object element : result){
				if(j==0){
					if(element.toString().matches(".*\\d.*")){
						flg="true";
						continue;
												
					}
					sb.append("{\"version\":\"")
					.append(element!=null? element:"");
					flg="false";
				}
				j++;
			}
			if(flg.equalsIgnoreCase("false")){
			sb.append("\"},");
			}
			
		}
		
		String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
		

			return str;
	}
	
	
	
	

	@RequestMapping("/design/autoFillList")
	@ResponseBody
	public String designAutoFillList(@RequestParam(value = "term", required = false) String mainStyleNo,
			@RequestParam(value = "partyId", required = false) Integer partyId) {
				
		
		return designService.designAutofill(mainStyleNo, partyId);
	}
	
	@RequestMapping("/design/autoFillListForClientRef")
	@ResponseBody
	public String designAutofillForClientRef(@RequestParam(value = "term", required = false) String mainStyleNo,
			@RequestParam(value = "partyId", required = false) Integer partyId) {
				
		
		return designService.designAutofillForClientRef(mainStyleNo, partyId);
	}

	
	@ResponseBody
	@RequestMapping("/design/styleCheckValidation")
	public String checkStyleValidation(
			@RequestParam(value = "mainStyleNo") String mainStyleNo){
		String retVal = "-2";
		Design design = designService.findByMainStyleNoAndDeactive(mainStyleNo, false);
		if(design != null){
			retVal = "-1";
		}
		
		return retVal;
	}
	

	@RequestMapping(value="/design/designCopy", method=RequestMethod.POST)
	@ResponseBody
	public String designCopySave(
			@RequestParam(value="styleId")Integer styleId,
			@RequestParam(value="styleNo")String styleNo,
			@RequestParam(value="designNo")String designNo,
			@RequestParam(value="versionNo")String versionNo,
			Principal principal
			){
	
		
		String retVal="-1";
		
		styleNo = styleNo.replaceAll("[\\n\\t\\r ]", " ").trim();
		designNo = designNo.replaceAll("[\\n\\t\\r ]", " ").trim();
		versionNo = versionNo.replaceAll("[\\n\\t\\r ]", " ").trim();
		
		retVal=designService.designCopy(styleId, styleNo.trim(), designNo.trim(), versionNo.trim(), principal);
	
		return retVal;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/design/updateDesignGrossWt")
	public String updateDesign(@RequestParam(value = "designId") Integer designId){
		String retVal="";
		if(designId>0){
			Design design = designService.findOne(designId);
			
			
			List<DesignStone>designStones = designStoneService.findByDesign(design);
			Double totCarat=0.0;
			for(DesignStone designStone :designStones){
				totCarat +=designStone.getCarat();
				
			}
			
			Double diaWt =Math.round((totCarat/5)*1000.0)/1000.0;
			
				
			
			
			List<DesignComponent>designComponents=designComponentService.findByDesign(design);
			Double totCompWt=0.0;
			for(DesignComponent designComponent :designComponents){
				totCompWt +=designComponent.getCompWt();

			}
			
			
				design.setGrossWt(Math.round((design.getFinishWt()+diaWt+totCompWt)*1000.0)/1000.0);
				
				retVal = design.getGrossWt().toString();
			
			designService.save(design);
			
		}
		
		
		
		
		return retVal;
		
	}
	
	@RequestMapping("/design/customSearch/listing")
	@ResponseBody
	public String designCustomListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "styleNo") String styleNo,
			@RequestParam(value = "versionNo") String versionNo,
			@RequestParam(value = "designNo") String designNo,
			@RequestParam(value = "reffNo") String reffNo,
			@RequestParam(value = "categoryNm") String categoryNm,
			@RequestParam(value = "subCategoryNm") String subCategoryNm,
			@RequestParam(value = "conceptNm") String conceptNm,
			@RequestParam(value = "designDate") String designDate,
			@RequestParam(value = "opt", required = false) String opt,
			Principal principal) throws ParseException {
		
		StringBuilder sb = new StringBuilder();
		Page<Design> designs = null;
				
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("design");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		designs=designService.customSearch(limit, offset, sort, order, styleNo, versionNo, designNo, reffNo, categoryNm, subCategoryNm, conceptNm,designDate);
		
sb.append("{\"total\":").append(designs.getTotalElements()).append(",\"rows\": [");
		
	
		
		for (Design design : designs) {
			
			String imgName = null;;
			imgName = design.getDefaultImage();
			
			sb.append("{\"id\":\"")
					.append(design.getId())
					.append("\",\"group\":\"")
					.append((design.getDesignGroup() != null ? design.getDesignGroup().getName() : ""))
					.append("\",\"designDate\":\"")
					.append(design.getDesignDtStr())
					.append("\",\"designNo\":\"")
					.append(design.getDesignNo())
					.append("\",\"styleNo\":\"")
					.append(design.getStyleNo())
					.append("\",\"refNo\":\"")
					.append(design.getRefNo() !=null?design.getRefNo() :"")
					.append("\",\"version\":\"")
					.append(design.getVersion())
					.append("\",\"designer\":\"")
					.append(design.getDesignerEmployee() == null ? "" : design.getDesignerEmployee().getName())
					.append("\",\"category\":\"")
					.append(design.getCategory() == null ? "" : design.getCategory().getName())
					.append("\",\"subCategory\":\"")
					.append(design.getSubCategory() == null ? "" : design.getSubCategory().getName())
					.append("\",\"concept\":\"")
					.append(design.getConcept() == null ? "" : design.getConcept().getName())
					.append("\",\"uploadImage\":\"");
					if (imgName != null) {
						sb.append("<a href='/jewels/uploads/manufacturing/design/")
							.append(imgName) 
							.append("' data-lighter class='btn btn-xs btn-info'>")
							.append("View Image</a>");
					}

					sb.append("\",\"deactive\":\"")
					.append((design.getDeactive() == null ? "Active" : (design
							.getDeactive() ? "Deactive" : "Active")));

			if (opt == null || opt.equals("1")) {
				sb.append("\",\"deactive\":\"")
					.append((design.getDeactive() == null ? "Active" : (design.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((design.getDeactiveDt() == null ? "" : design.getDeactiveDt()));
					
					
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/design/edit/")
							.append(design.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				
				sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:designDtDelete(event, ")
							.append(design.getId()).append(", 0);' href='javascript:void(0);'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(design.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>");
							
					
					
					
				sb.append("\",\"action3\":\"")
							.append("<a href='/jewels/manufacturing/masters/design/view/")
							.append(design.getId()).append(".html'")
							.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
							.append("\"},");
				
				}else{
					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/design/edit/")
							.append(design.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				
				sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append("<a onClick='javascript:designDtDelete(event, ")
							.append(design.getId()).append(", 0);' href='javascript:void(0);'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(design.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>");
							
					
					
					
				sb.append("\",\"action3\":\"");
					if (roleRights.getCanView()) {
						sb.append("<a href='/jewels/manufacturing/masters/design/view/")
							.append(design.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
					.append("\"},");
				}
						
			} else if (opt.equals("2")) {
				sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editODesign(")
					.append(design.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Select</a>")
					.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		
		return str;
	}
	
	
	
	@RequestMapping("/designPurity/listing")
	@ResponseBody
	public String designMetalListing(
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "sampleWt", required = false) Double sampleWt,
			@RequestParam(value = "deisgnWaxWt", required = false) Double deisgnWaxWt,
			Principal principal) {

			StringBuilder sb = new StringBuilder();
			
			Purity purity1=null;
			Double waxConv =0.0;
			
			if(purityId != null){
				purity1 =  purityService.findOne(purityId);
				waxConv =  sampleWt / purity1.getWaxWtConv() ;
			}
			List<Purity> puritys = purityService.findAll();
			
			sb.append("{\"total\":").append(puritys.size()).append(",\"rows\": [");
		
			for (Purity purity : puritys) {

				if(purity.getWaxWtConv() > 0){
					
					
					
				sb.append("{\"id\":\"")
					.append(purity.getId())
					.append("\",\"purityNm\":\"")
					.append(purity.getName())
					.append("\",\"waxWtConv\":\"")
					.append(purity.getWaxWtConv())
					.append("\",\"purityWt\":\"")
					.append(Math.round((purity.getWaxWtConv() * deisgnWaxWt)*1000.0)/1000.0);
					if(purity1 != null){
						sb.append("\",\"expNetWt\":\"")
						.append(Math.round((purity.getWaxWtConv() * waxConv)*1000.0)/1000.0);
							
					}else {
						sb.append("\",\"expNetWt\":\"")
						.append("");
					}
					sb.append("\"},");
				}
				}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}
	
	
	@RequestMapping("/deisgnBiodata/report")
	@ResponseBody
	public String deisgnBiodata(@RequestParam(value = "designId", required = false) Integer designId, Principal principal
			) throws SQLException {
		
		synchronized (this) {	
		String retVal = "-1";
		String fileName = "";
		String subRptPath = "";
		String imgPath = "";
		
		Connection conn = null;
		
		String xml = "DesignBioData";
	
		
	try {
		
		
		conn = Utils.getConnection();
		String styleCond = " ' StyleId in (" + designId + ") ' ";
	
		
		fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
		subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
		imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
	
		InputStream input = new FileInputStream(new File(fileName));
		java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
		
		parametersMap.put("styleCond", styleCond);
		
		parametersMap.put("imagepath", imgPath);
		parametersMap.put("subrptpath", subRptPath);
			
		
		JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
		
		String designCatalogFileName = System.currentTimeMillis()+"";
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath + designCatalogFileName+".pdf");
		file.createNewFile();
		JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + designCatalogFileName+".pdf");
		
		String exportFileName = System.currentTimeMillis()+""+principal.getName();
		Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +designCatalogFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportFileName+".pdf");
		
		file.delete();
		
		retVal = exportFileName;
		
	} catch (Exception e) {
		System.out.println(e);
		retVal = "-2";
	} finally {
		conn.close();
	}
	
	return retVal;
	
	}
		}
	
	
	@ResponseBody
	@RequestMapping("/design/getOrderGrossWt")
	public String getOrderDtGrossWt(
			@RequestParam(value = "mainStyleNo") String mainStyleNo,
			@RequestParam(value = "purityId") Integer purityId) {

		
		Design design = designService.findByMainStyleNoAndDeactive(mainStyleNo, false);
		
		Purity purity = purityService.findOne(purityId);
		
		DesignMetal designMetal =designMetalService.findByDesignAndDeactiveAndMainMetal(design, false, true);
		
		Double waxWt=Math.round((design.getWaxWt()-((design.getWaxWt()*designMetal.getLossPerc())/100))*1000.0)/1000.0;
		
		Double netWt = Math.round((waxWt * purity.getWaxWtConv())*1000.0)/1000.0;
		
		List<DesignStone> designStones = designStoneService.findByDesign(design);
	
		Double totalReqCarat =0.0;
		for(DesignStone designStone : designStones) {
			totalReqCarat += designStone.getCarat();
		}
		
		Double grossWt=Math.round((netWt+(totalReqCarat/5))*1000.0)/1000.0;
		
		List<DesignComponent>designComponents =designComponentService.findByDesign(design);
		Double compWt=0.0;
		for(DesignComponent designComponent :designComponents) {
			compWt +=designComponent.getCompWt();
		}
		
		grossWt=Math.round((grossWt+compWt)*1000.0)/1000.0;
		
		JSONObject jsonObject =  new JSONObject();
		jsonObject.put("grossWt", grossWt);
		jsonObject.put("reqCarat", Math.round(totalReqCarat*1000.0)/1000.0);
	
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/design/uploadExcel")
	public String excelFilePage(Model model) {
		model.addAttribute("tableDisp", "no");
		return "uploadExcelDesign";
	}
	
	
	@RequestMapping(value = "/design/excelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model, @RequestParam("excelfile") MultipartFile excelfile, HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile, RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		String retVal = "yes";
		
		synchronized (this) {
			
			retVal = designService.designExcelUpload(excelfile, session, tempExcelFile,principal);
		}
		
		model.addAttribute("tableDisp", "yes");
		model.addAttribute("retVal", retVal);
		
		return "designExcelFileUploaded";
	}
	
	
	@RequestMapping("/designExcel/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<DesignExcel> designExcels = (List<DesignExcel>) httpSession.getAttribute("designExcelSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(designExcels.size()).append(",\"rows\": [");
		
		for(DesignExcel designExcel:designExcels){
			
			sb.append("{\"styleNo\":\"")
			.append((designExcel.getStyleNo() != null ? designExcel.getStyleNo() : ""))
			.append("\",\"designGroup\":\"")
			.append((designExcel.getDesignGroup() != null ? designExcel.getDesignGroup() : ""))
			.append("\",\"designer\":\"")
			.append((designExcel.getDesigner() != null ? designExcel.getDesigner() : ""))
			.append("\",\"category\":\"")
			.append((designExcel.getCategory() != null ? designExcel.getCategory() : ""))
			.append("\",\"subCategory\":\"")
			.append((designExcel.getSubCategory() != null ? designExcel.getSubCategory() : ""))
			.append("\",\"collection\":\"")
			.append((designExcel.getCollection() != null ? designExcel.getCollection() : ""))
			.append("\",\"concept\":\"")
			.append((designExcel.getConcept() != null ? designExcel.getConcept() : ""))
			.append("\",\"subConcept\":\"")
			.append((designExcel.getSubConcept() != null ? designExcel.getSubConcept() : ""))
			.append("\",\"productSize\":\"")
			.append((designExcel.getProductSize() != null ? designExcel.getProductSize() : ""))
			.append("\",\"cadDesigner\":\"")
			.append((designExcel.getCadDesigner() != null ? designExcel.getCadDesigner() : ""))
			.append("\",\"stoneType\":\"")
			.append((designExcel.getStoneType() != null ? designExcel.getStoneType() : ""))
			.append("\",\"shape\":\"")
			.append((designExcel.getShape() != null ? designExcel.getShape() : ""))
			.append("\",\"quality\":\"")
			.append((designExcel.getQuality() != null ? designExcel.getQuality() : ""))
			.append("\",\"quality\":\"")
			.append((designExcel.getQuality() != null ? designExcel.getQuality() : ""))
			.append("\",\"sizegroup\":\"")
			.append((designExcel.getSizegroup() != null ? designExcel.getSizegroup() : ""))
			.append("\",\"settingType\":\"")
			.append((designExcel.getSettingType() != null ? designExcel.getSettingType() : ""))
			.append("\",\"setting\":\"")
			.append((designExcel.getSetting() != null ? designExcel.getSetting() : ""))
			.append("\",\"productType\":\"")
			.append((designExcel.getProductType() != null ? designExcel.getProductType() : ""))
			.append("\",\"marketType\":\"")
			.append((designExcel.getMarketType() != null ? designExcel.getMarketType() : ""))
			.append("\",\"sieve\":\"")
			.append((designExcel.getSieve() != null ? designExcel.getSieve() : ""))
			.append("\",\"statusRemark\":\"")
			.append((designExcel.getStatusRemark() != null ? designExcel.getStatusRemark() : ""))
			.append("\",\"vendor\":\"")
			.append((designExcel.getVendor() != null ? designExcel.getVendor() : ""))
			
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value = "/autoDesignParam/add", method = RequestMethod.POST)
	public String autoDesignParam(@Valid @ModelAttribute("design") Design design,
			 @RequestParam(value = "id") Integer id, Principal principal){

		String retVal =designService.desingNoAutoGeneration(design, principal);
		
	
		
		
	return retVal;	
	}



	//--------Design  excel file download----//
	
	@RequestMapping("/design/downloadExcel/format")
	@ResponseBody
	public String excelFormatDownload(
			@RequestParam(value = "headingVal") String headingVal,Principal principal){
		
		String retVal = "-1";
		String fileName = principal.getName()+new java.util.Date().getTime()+".xlsx";
		String filePath = uploadDirecotryPath + File.separator +"excelfilecontent" + File.separator;
		String tempHeadVal[] = headingVal.split(",");
		
		 try {
	            String filename = filePath+fileName;
	            XSSFWorkbook workbook = new XSSFWorkbook();
	            XSSFSheet sheet = workbook.createSheet("FirstSheet");  

	            XSSFRow rowhead = sheet.createRow((short)0);
	            for(int i=0;i<tempHeadVal.length;i++){
	            	 rowhead.createCell(i).setCellValue(tempHeadVal[i].toString());
	            }
	            
	            FileOutputStream fileOut = new FileOutputStream(filename);
	            workbook.write(fileOut);
	            fileOut.close();
	            workbook.close();
	            retVal = fileName;
	        } catch ( Exception ex ) {
	            System.out.println(ex);
	            retVal = "-2";
	        }
		
		return retVal;
	}
	

  }

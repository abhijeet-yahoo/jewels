package com.jiyasoft.jewelplus.controller.marketing.transactions;

import java.security.Principal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QPackMetalDt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHSNService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalRateService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackStnDtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/marketing/transactions")
@Controller
public class PackingListController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IPackMtService packMtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IPackDtService packDtService;
	
	@Autowired
	private IPackMetalDtService packMetalDtService;
	
	@Autowired
	private IPackStnDtService packStnDtService;
	
	@Autowired
	private IPackCompDtService packCompDtService;
	
	@Autowired
	private IPackLabDtService packLabDtService;
	
	
	@Autowired
	private IPackMetalRateService packMetalRateService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	

	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IHSNService hsnService;
	

	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${tmpUploadImage}")
	private String tmpUploadImage;


	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;
		
	
	
	@ModelAttribute("packMt")
	public PackMt constructMt() {
		return new PackMt();
	}
	
	
	@ModelAttribute("packDt")
	public PackDt constructDt() {
		return new PackDt();
	}
	
	@RequestMapping("/packList")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("packList");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "packList";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "packList";
		}
	}
	
	
	
	@RequestMapping("/packList/listing")
	@ResponseBody
	public String packListListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return packMtService.packMtListing(limit, offset, sort, order, search, principal);

	}
	
	
	
	@RequestMapping("/packList/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("packList");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
	
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("hsnMap", hsnService.getHsnList());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
			
			model.addAttribute("allPartyMap", partyService.getPartyList());
			
			return "packList/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		
	
		
		model.addAttribute("canEditTranddate", "false");
		return "packList/add";
		}
	}
	
	
	
	@RequestMapping("/packList/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("packList");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		PackMt packMt = packMtService.findOne(id);
		model.addAttribute("packMt", packMt);
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
		model.addAttribute("model", "PackList");
		model.addAttribute("hsnMap", hsnService.getHsnList());
		
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		List<PackDt>packDts =packDtService.findByPackMt(packMt);
		
		Boolean disableFlg =false;
		for(PackDt packDt :packDts) {
			if(packDt.getAdjQty()>0) {
				disableFlg =true;
				break;
			}
			
		}
				
				
	 model.addAttribute("disableFlg", disableFlg);
		
		
		
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
			model.addAttribute("mtid", id);
			
			
			return "packList/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		model.addAttribute("mtid", id);
		model.addAttribute("canEditTranddate", "false");
		

		return "packList/add";
		}
	}
	
	
	
	
	
	@RequestMapping(value = "/packList/add", method = RequestMethod.POST)
	public String addEditPackMt(
			@Valid @ModelAttribute("packMt") PackMt packMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		synchronized (this) {
		return packMtService.savePackMt(packMt, id, redirectAttributes, principal, result, pPartyIds, pLocationIds,vTranDate);
			
		}
		}
			
	
	
	
	@RequestMapping(value="/packList/addBarcodeItem",method = RequestMethod.POST)
	@ResponseBody
	public String addBarcodeItem(@RequestParam(value = "barcode") String barcode,
			@RequestParam(value = "mtId") Integer mtId,
			@RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			Principal principal) {
		
		
		synchronized (this) {
			
			return 	packDtService.addBarcodeItem(mtId,barcode, principal,partyId,locationId);
		}
		
		
		
	}
	
	
	
	@RequestMapping("/packDt/listing")
	@ResponseBody
	public String packDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "flag", required = false) String flag){
		
		return packDtService.packDtListing(mtId,flag);
		
	
}	
	
	
	@RequestMapping("/packMetalDt/listing")
	@ResponseBody
	public String packMetalDtList(@RequestParam(value = "packDtId", required = false) Integer packDtId){
		
	return packMetalDtService.packMetalDtListing(packDtId);
	
}
	
	
	
	
	@RequestMapping("/packStnDt/listing")
	@ResponseBody
	public String packStnDtList(@RequestParam(value = "packDtId", required = false) Integer packDtId){
		
		return packStnDtService.packStnDtListing(packDtId);
		
	
}
	
	
	@RequestMapping("/packCompDt/listing")
	@ResponseBody
	public String packCompDtList(@RequestParam(value = "packDtId", required = false) Integer packDtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return packCompDtService.packCompDtListing(packDtId,disableFlg);
		
	
}
	
	
	@RequestMapping("/packLabDt/listing")
	@ResponseBody
	public String packLabDtList(@RequestParam(value = "packDtId", required = false) Integer packDtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return packLabDtService.packLabDtListing(packDtId,disableFlg);
	
}
	
	
	//-----listing from costRate------//
	@RequestMapping("/packMetal/rateMaster/listing")
	@ResponseBody
	public String packMetalRateListing(
			@RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "packMtId", required = false) Integer packMtId) {
		
		return packMtService.rateMasterListing(partyId, packMtId);
	}
	
	
	
	@ResponseBody
	@RequestMapping("/packMetalrate/addTabData")
	public String packMetalRateSave(
			@RequestParam(value = "tabData") String tabData,
			@RequestParam(value = "packMtId") int packMtId,Principal principal
			){
		
		
		String retVal= packMetalRateService.packMetalRateSave(tabData, packMtId, principal);
		
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/packDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = packDtService.deletePackDt(id);
		
		return retVal;
		
	}
	
	
	
	
	@RequestMapping(value = "/packDtApplyRate", method = RequestMethod.POST)
	@ResponseBody
	public String applyRateMerge(@RequestParam(value="mtId")Integer mtId, 
			Principal principal){
		
		

		
		String retval="-1";
		
		try {
			
			PackMt packMt = packMtService.findOne(mtId);
			
			List<PackDt>packDts = packDtService.findByPackMt(packMt);
			
			for(PackDt packDt : packDts){
				
				packDtService.applyRate(packDt,principal);
				
			} 
			retval="1";
			
		} catch (Exception e) {
			e.printStackTrace();
			retval="-1";
		}
			
		
		
	
		
		return retval;
	}
	
	

	@ResponseBody
	@RequestMapping("/packStnDt/updateStnRate")
	public String updateStnRate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "stnRate", required = false) Double stnRate,Principal principal) {
		
		String retVal="-1";
		retVal=packStnDtService.updateStnRate(principal, id, stnRate);
		
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/packStnDt/updateHdlgRate")
	public String updateHdlgRate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "hdlgRate", required = false) Double hdlgRate,Principal principal) {
		
		String retVal="-1";
		retVal=packStnDtService.updateHdlgRate(principal, id, hdlgRate);
		
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/packStnDt/updateSettRate")
	public String updateSettRate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "settRate", required = false) Double settRate,Principal principal) {
		
		String retVal="-1";
		retVal=packStnDtService.updateSettRate(principal, id, settRate);
		
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/packCompDt/updateCompRate")
	public String updateCompRate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "compRate", required = false) Double compRate,Principal principal) {
		
		String retVal="-1";
		retVal=packCompDtService.updateCompRate(principal, id, compRate);
		
		
		return retVal;
	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/packDt/getData/{dtId}")
	public String getCostData(@PathVariable int dtId){
		
		
	return packDtService.packDtData(dtId);
		
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/packStnDt/qualityDropdown")
	public String qualityDropdown(@RequestParam(value = "packMtId", required = false) Integer packMtId,
			@RequestParam(value = "shapeId", required = false) Integer shapeId) {
		
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		Map<Integer, String> qualityMap = packStnDtService.getQualityList(packMtId,shapeId);
		
		if(qualityMap.size()>0){
			sb.append("<select id=\"qualityId\" name=\"qualityId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Quality</option>");
			for (Object key : qualityMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(qualityMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		else{
			sb.append("<select id=\"qualityId\" name=\"qualityId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Quality</option>");
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		
		return retVal;
	}	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/packStnDt/shapeDropdown")
	public String shapeDropdown(@RequestParam(value = "packMtId", required = false) Integer packMtId) {
		
		return packStnDtService.shapeDropdown(packMtId);

	}	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/packStnDt/sizeGroupDropdown")
	public String sizeGroupDropdown(@RequestParam(value = "packMtId", required = false) Integer packMtId,
			@RequestParam(value = "shapeId", required = false) Integer shapeId) {
		
		return packStnDtService.sizeGroupDropdown(packMtId, shapeId);
	}	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/packStnDt/sizeDropdown")
	public String sizeDropdown(@RequestParam(value = "packMtId", required = false) Integer packMtId,
			@RequestParam(value = "shapeId", required = false) Integer shapeId,
			@RequestParam(value = "sizeGroupId", required = false) Integer sizeGroupId) {
		
		return packStnDtService.sizeDropdown(packMtId, shapeId, sizeGroupId);
	}	
	
	@ResponseBody
	@RequestMapping(value = "/packStnDt/updateStoneRate")
	public String updateStoneRate(@RequestParam(value = "packMtId", required = false) Integer packMtId,
			@RequestParam(value = "shapeId", required = false) Integer shapeId,
			@RequestParam(value = "qualityId", required = false) Integer qualityId,
			@RequestParam(value = "sizeGroupId", required = false) Integer sizeGroupId,
			@RequestParam(value = "sizeNm", required = false) String sizeNm,
			@RequestParam(value = "stnRate", required = false) Double stnRate,Principal principal) {
		
		String retVal="-1";
		
		retVal = packStnDtService.updateStoneRate(principal, packMtId, shapeId, qualityId, sizeGroupId, sizeNm, stnRate);
		
		return retVal;
	}	

	
	@ResponseBody
	@RequestMapping("/packList/delete/{id}")
	public String deleteMt(@PathVariable int id){
		String retVal = "-1";
		try {
			
			retVal = packMtService.deletePackMt(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/packMt/dtSummary")
	public String getDtItemSummary(
			@RequestParam(value="mtId")Integer mtId,Principal principal){
		
	
		return packMtService.getDtItemSummary(mtId);
	}
	
	@RequestMapping("/packMt/packingList/report")
	@ResponseBody
	public String marketingReport(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "tranType", required = false) String tranType,
			@RequestParam(value = "opt", required = false) String opt,
			Principal principal
			) throws SQLException {
		
				
		return packMtService.marketingReport(mtId,tranType, uploadDirecotryPath, uploadParentDirecotryName, uploadDirecotryName, tmpUploadImage, reportXmlDirectoryPath, reportoutputdirectorypath, principal,opt);
	
	}
	
	
	
	
	@RequestMapping("/packLabDt/edit/{id}")
	public String editPacktLabDt(@PathVariable int id,Model model){
		
		if(id>0){
			PackLabDt packLabDt = packLabDtService.findOne(id);
			model.addAttribute("packLabDt",packLabDt);
			model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
			model.addAttribute("metalMap", metalService.getMetalList());	
		}else{
			PackLabDt packLabDt = new PackLabDt();
			model.addAttribute("packLabDt",packLabDt);
			model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
			model.addAttribute("metalMap", metalService.getMetalList());
			
			
			
			
			
		}
		
		
		
		
		return "packLabDt/add";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/packLabDt/labRateFromMaster")
	public String labRateFromMaster(
			@RequestParam(value="metalId") Integer metalId,
			@RequestParam(value="labourId") Integer labourId,
			@RequestParam(value="packLabDtId") Integer packLabDtId,
			@RequestParam(value="packDtId") Integer packDtId){
		
		
		
		PackDt packDt =packDtService.findOne(packDtId);
		
		Metal metal=metalService.findOne(metalId);
		LabourType labourType =labourTypeService.findOne(labourId);
			

		
		QPackMetalDt qPackMetalDt = QPackMetalDt.packMetalDt;
		JPAQuery query=new JPAQuery(entityManager);
		
		List<Tuple> packMetalList=null;
		
		packMetalList = query.from(qPackMetalDt).
				where(qPackMetalDt.packDt.id.eq(packDtId).and(qPackMetalDt.purity.metal.eq(metal)))
				.groupBy(qPackMetalDt.purity.metal).list(qPackMetalDt.purity,qPackMetalDt.purity.metal.name,qPackMetalDt.metalWeight.sum(),qPackMetalDt.metalPcs.sum());
		
		List<LabourCharge> labourCharges=null;
		
		for(Tuple tuple : packMetalList){
			
			Double vMetalWt=Math.round((tuple.get(qPackMetalDt.metalWeight.sum())/packDt.getPcs())*1000.0)/1000.0;
			
			
			
			
			 labourCharges =labourChargeService.getPurityLabourRates(packDt.getPackMt().getParty(), packDt.getDesign().getCategory(),
					vMetalWt,false, metal,labourType,tuple.get(qPackMetalDt.purity));
			 
			 if(labourCharges.size()<=0){
				 
				 labourCharges =labourChargeService.getLabourRates(packDt.getPackMt().getParty(), packDt.getDesign().getCategory(),
							vMetalWt,false, metal,labourType);
				 
			 }
			
		}
		
		if(labourCharges !=null && labourCharges.size()>0){
			LabourCharge labourCharge = labourCharges.get(0);
			
			PackLabDt packLabDtItem=null;
			
			if(packLabDtId==null){
				packLabDtItem = new PackLabDt();
				
				packLabDtItem.setMetal(metal);
				packLabDtItem.setLabourType(labourType);
				
				packLabDtItem.setLabourRate(labourCharge.getRate());

				if(labourCharge.getPerPcsRate() == true){
					packLabDtItem.setPerPcRate(true);
				}else if(labourCharge.getPerGramRate() == true){
					packLabDtItem.setPerGramRate(true);
				}else if(labourCharge.getPercentage() == true){
					packLabDtItem.setPercentage(true);
				}
				else if(labourCharge.getPerCaratRate() == true){
					packLabDtItem.setPerCaratRate(true);
				}
				
				
				
			}else{
				
				packLabDtItem = packLabDtService.findOne(packLabDtId);
				packLabDtItem.setMetal(metal);
				packLabDtItem.setLabourType(labourType);
				
				packLabDtItem.setLabourRate(labourCharge.getRate());
				packLabDtItem.setPerPcRate(false);
				packLabDtItem.setPerGramRate(false);
				packLabDtItem.setPercentage(false);
				packLabDtItem.setPerCaratRate(false);


				if(labourCharge.getPerPcsRate() == true){
					packLabDtItem.setPerPcRate(true);
				}else if(labourCharge.getPerGramRate() == true){
					packLabDtItem.setPerGramRate(true);
				}else if(labourCharge.getPercentage() == true){
					packLabDtItem.setPercentage(true);
				}
				else if(labourCharge.getPerCaratRate() == true){
					packLabDtItem.setPerCaratRate(true);
				}
				
				
				
				
			}
			
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("labourRate", packLabDtItem.getLabourRate() );
			jsonObject.put("perPcRate", packLabDtItem.getPerPcRate());
			jsonObject.put("perGramRate", packLabDtItem.getPerGramRate());
			jsonObject.put("percentage", packLabDtItem.getPercentage());
			jsonObject.put("perCaratRate", packLabDtItem.getPerCaratRate());
			
		
			
			return jsonObject.toString();
			
			
			
		}else{
			
			
			return null;
		}
		
		
		
		
		
		
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/packLabDt/add", method = RequestMethod.POST)
	public String addEditPackLabDt(@Valid @ModelAttribute("packLabDt") PackLabDt packLabDt,
			BindingResult bindingResult,
			@RequestParam(value="id", required=false)Integer id,
			@RequestParam(value="forLabPackMtId", required=false)Integer forLabPackMtId,
			@RequestParam(value="forLabPackDtId", required=false)Integer forLabPackDtId,
			RedirectAttributes redirectAttributes,Principal principal){
		
		
		String retVal = "-1";
		
		if(bindingResult.hasErrors()){
			return "xyz";
		}
		
		try {
			
		retVal=packLabDtService.packLabDtSave(packLabDt, id,forLabPackMtId, forLabPackDtId, principal);
		
		
			
		}  catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		
		
		return retVal;

		
	}
	
	
	
	@RequestMapping("/packLabDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "labDtId")Integer labDtId){
		
		String retVal = "";
		
		PackLabDt packLabDt = packLabDtService.findOne(labDtId);
		
	
		
		if(packLabDt.getrLock() == true){
			retVal = "-1";
		}
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/packLabDt/delete/{id}")
	public String delete(@PathVariable int id, Principal principal){
		
		String retVal = "-1";
		
		PackLabDt packLabDt = packLabDtService.findOne(id);
		
		PackDt packDt = packLabDt.getPackDt();
		
		packLabDtService.delete(id);
			
			packDtService.updateFob(packDt);
			
		
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/packLabDt/lockUnlock")
	public String lockUnlockLabDt(
			@RequestParam(value="labDtId")Integer labDtId){
		
		String retVal = "-1";
		PackLabDt packLabDt = packLabDtService.findOne(labDtId);
		
		
	
		
			if(packLabDt.getrLock() == true){
				packLabDt.setrLock(false);
			}else{
				packLabDt.setrLock(true);
			}
		
			packLabDtService.save(packLabDt);
			
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/packMetalDt/updateLossPerc")
	public String updateLossPerc(@RequestParam(value = "packDtId", required = false) Integer packDtId,
			@RequestParam(value = "lossPerc", required = false) Double lossPerc,Principal principal) {
		
		String retVal="-1";
		retVal=packMetalDtService.updateLossPerc(principal, packDtId, lossPerc);
		
		return retVal;
		
	}
	
	
	@RequestMapping("/packCompDt/edit/{id}")
	public String editPacktCompDt(@PathVariable int id,Model model){
		
	
			PackCompDt packCompDt = packCompDtService.findOne(id);
			model.addAttribute("packCompDt",packCompDt);
			model.addAttribute("componentMap",componentService.getComponentList());
			model.addAttribute("purityMap",purityService.getPurityList());
			model.addAttribute("colorMap",colorService.getColorList());
		
		return "packCompDt/add";
	}
	
	@ResponseBody
	@RequestMapping(value="/packCompDt/add", method = RequestMethod.POST)
	public String addEditPackCompDt(@Valid @ModelAttribute("packCompDt") PackCompDt packCompDt,
			BindingResult bindingResult,
			@RequestParam(value="id", required=false)Integer id,
			@RequestParam(value="forCompPackMtId", required=false)Integer forCompPackMtId,
			@RequestParam(value="forCompPackDtId", required=false)Integer forCompPackDtId,
			@RequestParam(value="pComponentId", required=false)Integer pComponentId,
			@RequestParam(value="pPurityId", required=false)Integer pPurityId,
			@RequestParam(value="pColorId", required=false)Integer pColorId,
			RedirectAttributes redirectAttributes,Principal principal){
		
		
		String retVal = "-1";
		
		if(bindingResult.hasErrors()){
			return "xyz";
		}
		
		try {
			
		retVal=packCompDtService.packCompDtSave(packCompDt, id, forCompPackMtId, forCompPackDtId, pComponentId, pPurityId, pColorId, principal);
			
		}  catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		
		
		return retVal;

		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/packLabDt/updatePackLabourRate")
	public String updatePackLabourRate(@RequestParam(value = "packMtId", required = false) Integer packMtId,
			@RequestParam(value = "labourRate", required = false) Double labourRate,Principal principal) {
		
		String retVal="-1";
		
		retVal = packLabDtService.updatePackLabourRate(principal, packMtId, labourRate);
		
		return retVal;
	}	
	
	
	@RequestMapping(value = "/packMt/updatepuritycost", method = RequestMethod.POST)
	@ResponseBody
	public String updatepuritycost(@Valid @ModelAttribute("packMt") PackMt packMt,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate,BindingResult result,RedirectAttributes redirectAttributes,
			Principal principal) throws ParseException{
		
		if (result.hasErrors()) {
			return "-1";
			
		}

		
	String retval="-1";
	
	synchronized (this) {
		
		packMtService.savePackMt(packMt, packMt.getId(), redirectAttributes, principal, result, pPartyIds, pLocationIds, vTranDate);
		
	retval = packDtService.updateFobAsPer999(packMt);	
	}
		return retval;
	}
	
	
	@RequestMapping("/stockPackingListTransfer/listing")
	@ResponseBody
	public String stockPackingListTransfer(@RequestParam(value = "deptId", required = false) Integer deptId){
		
		return packMtService.stockPackingTransferListing(deptId);
	}
	
	
	@RequestMapping("/stockTransferToPackingList")
	@ResponseBody
	public String stockTransferToPackingList(	@RequestParam(value = "vBarcodeIds", required = false) String vBarcodeIds,
			@RequestParam(value = "stkPackMtId", required = false) Integer stkPackMtId,
			@RequestParam(value = "deptId", required = false) Integer locationId,
			
			Principal principal) {

		String retVal = "-1";
		
		synchronized (this) {
			String[] barcode = vBarcodeIds.split(",");
			for (int i = 0; i < barcode.length; i++) {
				
				retVal = packDtService.addBarcodeItem(stkPackMtId, barcode[i], principal, 0, locationId);
				
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

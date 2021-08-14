package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;


@RequestMapping("/manufacturing/transactions")
@Controller
public class StoneConversionController {
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	EntityManager entityManager;
		
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Value("${diamond.stocktype}")
	private String diamondStockType;
	
	@ModelAttribute("stoneTran")
	public StoneTran construct() {
		return new StoneTran();
	}
	
	@ModelAttribute("stoneConv")
	public StoneConversion Constructor() {
		return new StoneConversion();		
	}
	
	@RequestMapping("/stoneConversion")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneConversion");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
			model.addAttribute("shapeMap", shapeService.getShapeList());
			model.addAttribute("locationStnMap", locationRightsService.getToLocationListFromUser(user.getId(),"stone"));
		}
		return "stoneConversion";
	}
	
	
	@RequestMapping("/stoneConversion/available/stock")
	@ResponseBody
	public String stoneGetStock(
			@RequestParam(value = "stoneType", required = false) Integer stoneTypeId,
			@RequestParam(value = "shape", required = false) Integer shapeId,
			@RequestParam(value = "quality", required = false) Integer qualityId,
			@RequestParam(value = "sieve", required = false) String sieveStr,
			@RequestParam(value = "locationId", required = false) Integer locationId){
		
		
		return stoneTranService.getAvailableStock(locationId, stoneTypeId, shapeId, qualityId, sieveStr);
		
	//	Shape shape = shapeService.findOne(shapeId);
		//StoneChart stoneChart =  stoneChartService.findByShapeAndSieveAndDeactive(shape, sieveStr, false);
//		Department department = departmentService.findOne(locationId);
//		
//		
//		List<Tuple>stkBal=null;
//		QStoneTran qStoneTran = QStoneTran.stoneTran;
//		JPAQuery query = new JPAQuery(entityManager);
//		
//		stkBal = query.from(qStoneTran).where(qStoneTran.deactive.eq(false).and(qStoneTran.stoneType.id.eq(stoneTypeId))
//				.and(qStoneTran.shape.id.eq(shapeId)).and(qStoneTran.quality.id.eq(qualityId))
//				.and(qStoneTran.sieve.eq(sieveStr)).and(qStoneTran.deptLocation.id.eq(department.getId())))
//				.groupBy(qStoneTran.sieve).list(qStoneTran.balCarat.sum(),qStoneTran.balCarat.multiply(qStoneTran.rate).sum());
//		
//		Double stnVal=0.0;
//		Double balcarat=0.0;
//		for(Tuple tuple : stkBal) {
//			 stnVal= Math.round((tuple.get(qStoneTran.balCarat.multiply(qStoneTran.rate).sum()))*100.0)/100.0;
//			 
//			 balcarat= Math.round((tuple.get(qStoneTran.balCarat.sum()))*1000.0)/1000.0;
//		}
//		
//		
//		
//		JSONObject jsonObject = new JSONObject();
//		
//		jsonObject.put("availableStkId", balcarat);
//		jsonObject.put("stnVal",balcarat>0? stnVal:0.0);
//		jsonObject.put("avgRate",balcarat>0? Math.round((stnVal/balcarat)*100.0)/100.0:0.0);
//		
//		return jsonObject.toString();
		
	}
	
	
	@RequestMapping(value = "/stoneConversion", method = RequestMethod.POST)
	@ResponseBody
	public String saveStoneConversion(	@Valid @ModelAttribute("stoneTran") StoneTran stoneTran,
										@RequestParam(value="vSieveFrom") String vSieveFrom,
										@RequestParam(value="vshapeIdFrom") Integer vshapeIdFrom,
										@RequestParam(value="vQualityIdFrom") Integer vQualityIdFrom,
										@RequestParam(value="vStoneTypeIdFrom") Integer vStoneTypeIdFrom,
										@RequestParam(value="vtrfCarat") Double vtrfCarat,
										@RequestParam(value="vtrfStone") Integer vtrfStone,
										@RequestParam(value="vtranDate") String vTranDate,
										@RequestParam(value="vLocationId") Integer vLocationId,
								Principal principal) throws ParseException{
		
		
	
		Date date= new Date();
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(vTranDate);
				
		}
		
		
		String retVal="error :: Contact Admin";
		
		if(stoneTran.getQuality().getId().equals(vQualityIdFrom) && stoneTran.getSieve().equalsIgnoreCase(vSieveFrom) ){
			
			return "error : Quality & Sieve Can Not Be Same Please Chek";
			
		}
		
		 synchronized (this) {
			retVal= stoneTranService.saveStnTran(vSieveFrom, vshapeIdFrom, principal, vQualityIdFrom, vStoneTypeIdFrom, vtrfCarat,
					vtrfStone,stoneTran,diamondStockType,date,vLocationId);	
		}
		
		return retVal;
	
	}		
}


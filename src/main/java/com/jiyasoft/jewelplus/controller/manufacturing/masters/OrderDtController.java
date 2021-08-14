package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
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
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/masters")
@Controller
public class OrderDtController {

	@Autowired
	private IOrderMtService orderMtService;

	@Autowired
	private IOrderDtService orderDtService;

	@Autowired
	private IOrderStnDtService orderStnDtService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private IOrderCompDtService orderCompDtService;

	@Autowired
	private IOrderLabDtService orderLabDtService;
	
	@Autowired
	private IOrderMetalService orderMetalService;

	@Autowired
	private UserService userService;

	@Autowired
	private IDesignService designService;;

	
	@Autowired
	private IBagMtService bagMtService;

	
	@Autowired
	private IProductSizeService productSizeService;

		
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;

	
	@Value("${orderstampinst}")
	private String orderstampinst;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;
	
	@ModelAttribute("orderDt")
	public OrderDt construct() {
		return new OrderDt();
	}

	@ModelAttribute("design")
	public Design constructDesign() {
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

	@RequestMapping("/orderDt")
	public String users(Model model) {
		return "order";
	}


	@RequestMapping("/orderDt/listing")
	@ResponseBody
	public String orderDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,
			@RequestParam(value = "mOpt", required = false) String mOpt) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("order");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
	

		if (offset == null) {
			offset = 0;
		}

		StringBuilder sb = new StringBuilder();

		Page<OrderDt> orderDts = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		order = "asc";
		orderDts = orderDtService.searchAll(limit, offset, sort, order, search,	mtId,mOpt);

		DecimalFormat myFormatter = new DecimalFormat("##############");
		
		sb.append("{\"total\":").append(orderDts.getTotalElements())
				.append(",\"rows\": [");
		for (OrderDt orderObj : orderDts) {
			
			Integer totStone =0;
			Double totCarat =0.0;
			List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderObj, false);
			for (OrderStnDt orderStnDt : orderStnDts) {
			
				totStone +=orderStnDt.getStone();
				totCarat +=orderStnDt.getCarat(); 
			}
			
			
			List<OrderMetal>orderMetals=orderMetalService.findByOrderDtAndDeactive(orderObj, false);
			String purityVal="";
			for(OrderMetal orderMetal :orderMetals) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+orderMetal.getPurity().getName()+"-"+orderMetal.getColor().getName();
				 }else {
					 purityVal=orderMetal.getPurity().getName()+"-"+orderMetal.getColor().getName();
				 }
			}
			
			
			sb.append("{\"id\":\"")
					.append(orderObj.getId())
					.append("\",\"barcode\":\"")
					.append(orderObj.getBarcode() != null ? orderObj.getBarcode() : "")
					.append("\",\"style\":\"")
					.append(orderObj.getDesign().getMainStyleNo())
					.append("\",\"srNo\":\"")
					.append(orderObj.getSrNo())
					.append("\",\"defImage\":\"")
					.append((orderObj.getDesign() != null ? orderObj.getDesign().getDefaultImage() : "blank.png"))
					.append("\",\"reqCarat\":\"")
					.append((orderObj.getReqCarat() != null ? orderObj.getReqCarat() : ""))
					.append("\",\"ktCol\":\"")
					.append(purityVal)
					.append("\",\"pcs\":\"")
					.append((orderObj.getPcs() != null ? myFormatter.format(orderObj.getPcs()) : ""))
					.append("\",\"grossWt\":\"")
					.append((orderObj.getGrossWt() != null ? orderObj.getGrossWt() : ""))
					.append("\",\"netWt\":\"")
					.append((orderObj.getNetWt() != null ? orderObj.getNetWt(): ""))
					.append("\",\"remark\":\"")
					.append((orderObj.getRemark() != null ? orderObj.getRemark(): ""))
					.append("\",\"metalRate\":\"")
					.append((orderObj.getMetalRate() != null ? orderObj.getMetalRate() : ""))
					.append("\",\"metalValue\":\"")
					.append((orderObj.getMetalValue() != null ? orderObj.getMetalValue() : ""))
					.append("\",\"stoneValue\":\"")
					.append((orderObj.getStoneValue() != null ? orderObj.getStoneValue() : ""))
					.append("\",\"compValue\":\"")
					.append((orderObj.getCompValue() != null ? orderObj.getCompValue() : ""))
					.append("\",\"labourValue\":\"")
					.append((orderObj.getLabValue() != null ? orderObj.getLabValue() : ""))
					.append("\",\"setValue\":\"")
					.append((orderObj.getSetValue() != null ? orderObj.getSetValue() : ""))
					.append("\",\"handlingCost\":\"")
					.append((orderObj.getHdlgValue() != null ? orderObj.getHdlgValue() : ""))
					.append("\",\"fob\":\"")
					.append((orderObj.getFob() != null ? orderObj.getFob() : ""))
					.append("\",\"other\":\"")
					.append((orderObj.getOther() != null ? orderObj.getOther(): ""))
					.append("\",\"finalPrice\":\"")
					.append((orderObj.getFinalPrice() != null ? orderObj.getFinalPrice() : ""))
					.append("\",\"discPerc\":\"")
					.append((orderObj.getDiscPercent() != null ? orderObj.getDiscPercent() : ""))
					.append("\",\"discAmount\":\"")
					.append((orderObj.getDiscAmount() != null ? orderObj.getDiscAmount() : ""))
					.append("\",\"netAmount\":\"")
					.append((orderObj.getNetAmount() != null ? orderObj.getNetAmount() : ""))
					.append("\",\"stamp\":\"")
					.append((orderObj.getStampInst() != null ? orderObj.getStampInst() : ""))
					.append("\",\"productSize\":\"")
					.append((orderObj.getProductSize() == null ? "" : orderObj.getProductSize().getName()))
					.append("\",\"item\":\"")
					.append((orderObj.getItem() != null ?  orderObj.getItem():""))
					.append("\",\"ordRef\":\"")
					.append((orderObj.getOrdRef() != null ?  orderObj.getOrdRef():""))
					.append("\",\"cancelQty\":\"")
					.append((orderObj.getCancelQty() != null ? orderObj.getCancelQty() :""))
					.append("\",\"totStone\":\"")
					.append((totStone))
					.append("\",\"totCarat\":\"")
					.append((Math.round((totCarat)*1000.0)/1000.0))
					.append("\",\"dueDate\":\"")
					.append(orderObj.getDueDateStr())
					.append("\",\"deliveryDate\":\"")
					.append(orderObj.getDeliveryDateStr())
					.append("\",\"refNo\":\"")
					.append(orderObj.getRefNo() == null ? "" : orderObj.getRefNo())
					.append("\",\"image\":\"")
					.append(orderObj.getDesign().getDefaultImage() != null ? orderObj
							.getDesign().getDefaultImage() : "blank.png")
					.append("\",\"rLock\":\"")
					.append(orderObj.getrLock());	
					// 1 = lock & 0 = unlock
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				if(!canViewFlag){
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doOrderDtLockUnLock(")
					.append(orderObj.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
				
					
					sb.append("\",\"action1\":\"");
					
						sb.append("<a href='javascript:editOrderDt(")
						.append(orderObj.getId());
							
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
				
					sb.append("<a  href='javascript:deleteOrdDt(event,")
					.append(orderObj.getId());	
					sb.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(orderObj.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("")
					.append("\",\"actionLock\":\"")
					.append("");
				}
			}else {
				if(!canViewFlag){
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doOrderDtLockUnLock(")
					.append(orderObj.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
				
					
					sb.append("\",\"action1\":\"");
					if(roleRights.getCanEdit()){
						sb.append("<a href='javascript:editOrderDt(")
						.append(orderObj.getId());
							
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
					if(roleRights.getCanDelete()){
					sb.append("<a  href='javascript:deleteOrdDt(event,")
					.append(orderObj.getId());	
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(orderObj.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("")
					.append("\",\"actionLock\":\"")
					.append("");
				}
			}
					sb.append("\"},");

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	// ----------------Listing for Bag Generation -----------------//

	@RequestMapping("/orderDt/bagGeneration/listing")
	@ResponseBody
	public String orderDtBagGenListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

		StringBuilder sb = new StringBuilder();
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo, false);
		if (orderMt != null) {

			// rowCount = orderDtService.count(sort, search, false);

			List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(
					orderMt, false);

			sb.append("{\"total\":").append(orderDts.size())
					.append(",\"rows\": [");

			Integer srNo = 0;

			for (OrderDt orderObj : orderDts) {
				sb.append("{\"id\":\"")
						.append(orderObj.getId())
						.append("\",\"srNo\":\"")
						.append(++srNo)
						.append("\",\"styleId\":\"")
						.append((orderObj.getDesign() == null ? "" : orderObj
								.getDesign().getId()))
						.append("\",\"style\":\"")
						.append((orderObj.getDesign() == null ? "" : orderObj
								.getDesign().getMainStyleNo()))
						.append("\",\"purity\":\"")
						.append((orderObj.getPurity() == null ? "" : orderObj
								.getPurity().getName()))
						.append("\",\"color\":\"")
						.append((orderObj.getColor() == null ? "" : orderObj
								.getColor().getName()))
						.append("\",\"quantity\":\"")
						.append((orderObj.getPcs() == null ? 0 : orderObj
								.getPcs()))
						.append("\",\"genQty\":\"")
						.append((orderObj.getPcs() == null ? 0 : bagMtService
								.getGenQty(orderMt.getId(), orderObj.getId())))
						.append("\",\"balQty\":\"")
						.append((orderObj.getPcs() == null ? 0 : (orderObj
								.getPcs() - bagMtService.getGenQty(
								orderMt.getId(), orderObj.getId()))))
						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	// ----------------Listing for Stone Inward Against Order -----------------//

		@RequestMapping("/orderDt/stnInwOrderDt/listing")
		@ResponseBody
		public String orderDtStnInwListing(Model model,
				@RequestParam(value = "search", required = false) String search,
				@RequestParam(value = "pInvNo", required = false) String pInvNo) {

			StringBuilder sb = new StringBuilder();
			OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo, false);
			if (orderMt != null) {

			//	List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(orderMt, false);
				
				@SuppressWarnings("unchecked")
				TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_orderstoneInOutQty(?) }");
				query.setParameter(1,orderMt.getId());
				
				List<Object[]> dtList = query.getResultList();
					sb.append("{\"total\":").append(dtList.size()).append(",\"rows\": [");
					
					for (Object[] result : dtList) {
						
						sb.append("{\"srNo\":\"")
						.append(result[0]!=null? result[0]:"")
						.append("\",\"mtId\":\"")
						.append(result[1]!=null? result[1]:"")
						.append("\",\"dtid\":\"")
						.append(result[2]!=null? result[2]:"")
						.append("\",\"ordQty\":\"")
						.append(result[3]!=null? result[3]:"")
						.append("\",\"refNo\":\"")
						.append(result[4]!=null? result[4]:"")
						.append("\",\"item\":\"")
						.append(result[5]!=null? result[5]:"")
						.append("\",\"ordRef\":\"")
						.append(result[6]!=null? result[6]:"")
						.append("\",\"style\":\"")
						.append(result[7]!=null? result[7]:"")
						.append("\",\"prodSize\":\"")
						.append(result[8]!=null? result[8]:"")
						.append("\",\"purity\":\"")
						.append(result[9]!=null? result[9]:"")
						.append("\",\"color\":\"")
						.append(result[10]!=null? result[10]:"")
						.append("\",\"stnRecQty\":\"")
						.append(result[11]!=null? result[11]:0)
						.append("\",\"stnOutQty\":\"")
						.append(result[12]!=null? result[12]:0)
						.append("\",\"bagQty\":\"")
						.append(0)
						.append("\",\"cancelQty\":\"")
						.append(result[13]!=null? result[13]:0)
						.append("\"},");
						
						
					    
					}
				
				
				


		/*
				for (OrderDt orderObj : orderDts) {
					sb.append("{\"dtid\":\"")
							.append(orderObj.getId())
							.append("\",\"srNo\":\"")
							.append(orderObj.getSrNo())
							.append("\",\"styleId\":\"")
							.append((orderObj.getDesign() == null ? "" : orderObj.getDesign().getId()))
							.append("\",\"style\":\"")
							.append((orderObj.getDesign() == null ? "" : orderObj.getDesign().getMainStyleNo()))
							.append("\",\"refNo\":\"")
							.append((orderObj.getRefNo() == null ? "" : orderObj.getRefNo()))
							.append("\",\"item\":\"")
							.append((orderObj.getItem() == null ? "" : orderObj.getItem()))
							.append("\",\"ordRef\":\"")
							.append((orderObj.getOrdRef() == null ? "" : orderObj.getOrdRef()))
							.append("\",\"prodSize\":\"")
							.append((orderObj.getProductSize() == null ? "" : orderObj.getProductSize().getName()))
							.append("\",\"ordQty\":\"")
							.append((orderObj.getPcs() == null ? "" : orderObj.getPcs()))
							.append("\",\"stnRecQty\":\"")
							.append((orderObj.getStnInwardQty() != null ? orderObj.getStnInwardQty() : 0))
							.append("\",\"stnOutQty\":\"")
							.append((orderObj.getStnOutwardQty() != null ? orderObj.getStnOutwardQty() : 0))
							.append("\",\"bagQty\":\"")
							.append((0))
							.append("\",\"purity\":\"")
							.append((orderObj.getPurity() == null ? "" : orderObj.getPurity().getName()))
							.append("\",\"color\":\"")
							.append((orderObj.getColor() == null ? "" : orderObj.getColor().getName()))
							
							.append("\"},");
				}*/
			}
			
			

			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";

			return str;
		}

	@ResponseBody
	@RequestMapping(value = "/orderDt/add", method = RequestMethod.POST)
	public String addEditUser(
			@Valid @ModelAttribute("orderDt") OrderDt orderDt,
			BindingResult result,
			@RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes,
			Principal principal,
			@RequestParam(value = "vOrderMtId", required = true) Integer orderMtIdPk,
			@RequestParam(value = "metalDtData", required = true) String metalDtData) {

		String retVal = "-1";

		if (result.hasErrors()) {
			return "orderDt/add";
		}

		try {
			retVal = orderDtService.transactionalSave(orderDt, id, orderMtIdPk,
					metalDtData, principal, netWtWithCompFlg);
		} catch (Exception e) {
			retVal = "Error:Contact Support";
			e.printStackTrace();
		}

		return retVal;

	}

	@ResponseBody
	@RequestMapping(value = "/orderDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		OrderDt orderDt = orderDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalOrderDtId", orderDt.getId());
		jsonObject.put("design\\.mainStyleNo", orderDt.getDesign().getMainStyleNo());
		jsonObject.put("pcs", orderDt.getPcs() != null ? orderDt.getPcs() : "0");
		jsonObject.put("productSize\\.id",orderDt.getProductSize() != null ? orderDt.getProductSize().getId() : null);
		jsonObject.put("reqCarat",orderDt.getReqCarat() != null ? orderDt.getReqCarat() : "");
		jsonObject.put("purity\\.id", orderDt.getPurity() != null ? orderDt.getPurity().getId() : null);
		jsonObject.put("color\\.id", orderDt.getColor() != null ? orderDt.getColor().getId() : null);
		jsonObject.put("dueDate", orderDt.getDueDateStr() != null ? orderDt.getDueDateStr() : "");
		jsonObject.put("refNo", orderDt.getRefNo() != null ? orderDt.getRefNo()	: "");
		jsonObject.put("stampInst",	orderDt.getStampInst() != null ? orderDt.getStampInst() : "");
		jsonObject.put("discPercent",orderDt.getDiscPercent() != null ? orderDt.getDiscPercent() : "0.0");
		jsonObject.put("remark", orderDt.getRemark() != null ? orderDt.getRemark() : "");
		jsonObject.put("designRemark",	orderDt.getDesignRemark() != null ? orderDt.getDesignRemark()	: "");
		jsonObject.put("barcode", orderDt.getBarcode() != null ? orderDt.getBarcode() : "");
		jsonObject.put("grossWt", orderDt.getGrossWt() !=null ? orderDt.getGrossWt() : "0.0");
		jsonObject.put("item", orderDt.getItem() !=null ? orderDt.getItem() : "");
		jsonObject.put("ordRef", orderDt.getOrdRef() !=null ? orderDt.getOrdRef() : "");
		
		return jsonObject.toString();
	}

	@RequestMapping("/orderDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		return orderDtService.delete(id) + "";
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	private String getDesignRemarks(String designRemarks) {
		String tmpRemarks = designRemarks;

		String ttStr = "";

		if (tmpRemarks != null) {
			tmpRemarks = tmpRemarks.trim();

			for (int x = 0; x < tmpRemarks.length(); x++) {
				if (((int) tmpRemarks.charAt(x)) == 0) {
					continue;
				} else {
					ttStr += tmpRemarks.substring(x, (x + 1));
				}
			}

			tmpRemarks = ttStr;
			ttStr = "";
			for (int x = 0; x < tmpRemarks.length(); x++) {
				if (tmpRemarks.charAt(x) == ' ') {
					if (((x + 1) < tmpRemarks.length())
							&& tmpRemarks.charAt(x + 1) == ' ') {
						continue;
					} else {
						ttStr += tmpRemarks.substring(x, (x + 1));
					}
				} else {
					ttStr += tmpRemarks.substring(x, (x + 1));
				}
			}
		}

		return ttStr;
	}

	// ------------popDesignDetails--------//---------//

	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left,
				Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	@ResponseBody
	@RequestMapping("/orderDt/designAllDetails")
	public String getDeignGrossWt(
			@RequestParam(value = "purityId") Integer purityId,
			@RequestParam(value = "mainStyleNo") String mainStyleNo) {

		String retVal = "-1";

		String tempSize;
		String tempRemark;
		Integer tempProductSizeId;

		Design design = designService.findByMainStyleNoAndDeactive(mainStyleNo,
				false);

		// -----Size-------//

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> productSizeMap = productSizeService
				.getProductSizeList(design.getCategory().getId());

		List<Map.Entry<Integer, String>> productGroupMapGv = Lists
				.newArrayList(productSizeMap.entrySet());
		Collections.sort(productGroupMapGv, byMapValues);

		sb.append("<select id=\"productSize.id\" name=\"productSize.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Size -</option>");

		Iterator<Entry<Integer, String>> iterator = productGroupMapGv
				.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
					.append(et.getValue()).append("</option>");
		}

		sb.append("</select>");

		tempSize = sb.toString();

		// -----Remark-----//

		tempRemark = getDesignRemarks(design.getRemarks());

		// -----ProductSizeId-----//

		tempProductSizeId = (design.getProductSize() != null ? design
				.getProductSize().getId() : null);

		if (tempProductSizeId != null) {
			ProductSize productSize = productSizeService
					.findOne(tempProductSizeId);
			if (productSize.getDeactive().equals(true)) {
				tempProductSizeId = null;
			}

		}

		retVal = tempSize + "_" + tempRemark + "_" + tempProductSizeId;

		return retVal;

	}

	@ResponseBody
	@RequestMapping("/orderDt/lockUnlock")
	public String lockUnlockOrderDt(@RequestParam(value = "dtId") Integer dtId) {

		String retVal = "-1";
		OrderDt orderDt = orderDtService.findOne(dtId);
		List<OrderStnDt> orderStnDts = orderStnDtService
				.findByOrderDtAndDeactive(orderDt, false);
		List<OrderCompDt> orderCompDts = orderCompDtService
				.findByOrderDtAndDeactive(orderDt, false);
		List<OrderLabDt> orderLabDts = orderLabDtService
				.findByOrderDtAndDeactive(orderDt, false);

		if (orderDt.getrLock() == true) {
			orderDt.setrLock(false);

			for (OrderStnDt orderStnDt : orderStnDts) {
				orderStnDt.setrLock(false);
				orderStnDtService.save(orderStnDt);
			}

			for (OrderCompDt orderCompDt : orderCompDts) {
				orderCompDt.setrLock(false);
				orderCompDtService.save(orderCompDt);
			}

			for (OrderLabDt orderLabDt : orderLabDts) {
				orderLabDt.setrLock(false);
				orderLabDtService.save(orderLabDt);
			}

		} else {
			orderDt.setrLock(true);

			for (OrderStnDt orderStnDt : orderStnDts) {
				orderStnDt.setrLock(true);
				orderStnDtService.save(orderStnDt);
			}

			for (OrderCompDt orderCompDt : orderCompDts) {
				orderCompDt.setrLock(true);
				orderCompDtService.save(orderCompDt);
			}

			for (OrderLabDt orderLabDt : orderLabDts) {
				orderLabDt.setrLock(true);
				orderLabDtService.save(orderLabDt);
			}

		}

		orderDtService.save(orderDt);

		return retVal;
	}

	@ResponseBody
	@RequestMapping("/orderDt/dtLockUnlockAll")
	public String lockUnlockAll(@RequestParam(value = "mtId") Integer mtId,
			@RequestParam(value = "status") Integer status) {

		String retVal = "-1";
		OrderMt orderMt = orderMtService.findOne(mtId);
		List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(
				orderMt, false);
		List<OrderStnDt> orderStnDts = orderStnDtService
				.findByOrderMtAndDeactive(orderMt, false);
		List<OrderCompDt> orderCompDts = orderCompDtService
				.findByOrderMtAndDeactive(orderMt, false);
		List<OrderLabDt> orderLabDts = orderLabDtService
				.findByOrderMtAndDeactive(orderMt, false);
		Boolean setValue;

		if (status == 1) {
			setValue = true;
		} else {
			setValue = false;
		}

		orderMt.setrLock(setValue);
		orderMtService.save(orderMt);

		for (OrderDt orderDt : orderDts) {
			orderDt.setrLock(setValue);
			orderDtService.save(orderDt);
		}

		for (OrderStnDt orderStnDt : orderStnDts) {
			orderStnDt.setrLock(setValue);
			orderStnDtService.save(orderStnDt);
		}

		for (OrderCompDt orderCompDt : orderCompDts) {
			orderCompDt.setrLock(setValue);
			orderCompDtService.save(orderCompDt);
		}

		for (OrderLabDt orderLabDt : orderLabDts) {
			orderLabDt.setrLock(setValue);
			orderLabDtService.save(orderLabDt);
		}

		return retVal;
	}

	@ResponseBody
	@RequestMapping("/orderDt/getSize")
	public String sizeMast(@RequestParam(value = "orderDtId") int orderDtId) {

		OrderDt orderDt = orderDtService.findOne(orderDtId);
		Design design = designService.findOne(orderDt.getDesign().getId());

		// -----Size-------//

		String tempSize = "";

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> productSizeMap = productSizeService
				.getProductSizeList(design.getCategory().getId());

		List<Map.Entry<Integer, String>> productGroupMapGv = Lists
				.newArrayList(productSizeMap.entrySet());
		Collections.sort(productGroupMapGv, byMapValues);

		sb.append("<select id=\"productSize.id\" name=\"productSize.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Size -</option>");

		Iterator<Entry<Integer, String>> iterator = productGroupMapGv
				.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
					.append(et.getValue()).append("</option>");
		}

		sb.append("</select>");

		tempSize = sb.toString();

		return tempSize;

	}

	@ResponseBody
	@RequestMapping("/orderDt/getData/{dtId}")
	public String getOrderData(@PathVariable int dtId) {

		OrderDt orderDt = orderDtService.findOne(dtId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("grossWt", orderDt.getGrossWt());
		jsonObject.put("netWt", orderDt.getNetWt());
		jsonObject.put("metalValue", orderDt.getMetalValue());
		jsonObject.put("stoneValue", orderDt.getStoneValue());
		jsonObject.put("compValue", orderDt.getCompValue());
		jsonObject.put("labourValue", orderDt.getLabValue());
		jsonObject.put("setValue", orderDt.getSetValue());
		jsonObject.put("handlingCost", orderDt.getHdlgValue());
		jsonObject.put("fob", orderDt.getFob());
		jsonObject.put("other", orderDt.getOther());
		jsonObject.put("finalPrice", orderDt.getFinalPrice());
		jsonObject.put("discPerc", orderDt.getDiscPercent());
		jsonObject.put("discAmount", orderDt.getDiscAmount());
		jsonObject.put("netAmount", orderDt.getNetAmount());

		return jsonObject.toString();

	}

	@RequestMapping("/order/getTotal")
	@ResponseBody
	public String getOrderTotal(
			@RequestParam(value = "orderMtId") Integer orderMtId) {

		String retVal = "";

		QOrderDt qOrderDt = QOrderDt.orderDt;
		JPAQuery query = new JPAQuery(entityManager);

		List<Double> totOrderQty = null;

		totOrderQty = query
				.from(qOrderDt)
				.where(qOrderDt.deactive.eq(false).and(
						qOrderDt.orderMt.id.eq(orderMtId)))
				.groupBy(qOrderDt.orderMt.id).list(qOrderDt.pcs.sum());

		if (totOrderQty.size() > 0) {

			Double ordQty =  Math.round(totOrderQty.get(0) * 100.0) / 100.0;
			retVal = ordQty.toString();
		} else {
			retVal = "0";
		}

		return retVal;

	}

	@ResponseBody
	@RequestMapping("/orderDt/updateQty")
	public String updateQty(
			@RequestParam(value = "orderDtId") Integer orderDtId,
			@RequestParam(value = "newPcs") Double newPcs) {

		String retVal = "";

		OrderDt orderDt = orderDtService.findOne(orderDtId);
		List<BagMt> bagMts = bagMtService.findByOrderDtAndDeactive(orderDt,
				false);

		if (bagMts.size() > 0) {
			if (newPcs < orderDt.getPcs()) {
				retVal = orderDt.getPcs().toString();
			}
		}

		return retVal;

	}

	@ResponseBody
	@RequestMapping("/orderDt/getStampFromMaster")
	public String getStampFromMaster(
			@RequestParam(value = "partyId") Integer partyId,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "reqCts", required = false) Double reqCts) {

		String retVal = "-1";
		
		if(orderstampinst.equalsIgnoreCase("purity")) {
			@SuppressWarnings("unchecked")
			TypedQuery<ClientStamp> query = (TypedQuery<ClientStamp>) entityManager
					.createNativeQuery("select * from clientstampmast"
							+ " where partyid =" + partyId + " and purityid =" + purityId +" and deactive =0",
							ClientStamp.class);
			
			List<ClientStamp> clientStamps = query.getResultList();

			if (clientStamps.size() > 0) {

				for (ClientStamp clientStamp : clientStamps) {
					return clientStamp.getStampNm();

				}
			}

		
		}else {
			
			@SuppressWarnings("unchecked")
			TypedQuery<ClientStamp> query = (TypedQuery<ClientStamp>) entityManager
					.createNativeQuery("select * from clientstampmast"
							+ " where partyid =" + partyId + " and " + reqCts
							+ " between fromCts and toCts and deactive =0 ",
							ClientStamp.class);
			
			List<ClientStamp> clientStamps = query.getResultList();

			if (clientStamps.size() > 0) {

				for (ClientStamp clientStamp : clientStamps) {
					return clientStamp.getStampNm();

				}
			}

		}
	
	

		return retVal;
	}
		
	
	
	
	@RequestMapping("/orderDt/reportListing")
	@ResponseBody
	public String reportListing(
				@RequestParam(value = "mtIds", required = false) String mtIds
			){
		
		
	return orderDtService.orderDtReportListing(mtIds);		
	}
	
	
	@RequestMapping("/orderDt/pickUpDt/listing")
	@ResponseBody
	public String orderDtPickupListing(
				@RequestParam(value = "orderNm", required = false) String orderNm){
		
					return orderDtService.orderDtPickupListing(orderNm);
	}	
	
	@ResponseBody
	@RequestMapping("/orderDt/checkOrderClose")
	public String checkOrderClose(
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

		String retVal = "false";

		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo, false);
				if(orderMt.getOrderClose() == true) {
					retVal = "true";	
				}else {
					retVal = "false";
				}

		

		return retVal;

	}
	
	
	@ResponseBody
	@RequestMapping("/orderDt/checkOrderApproval")
	public String checkOrderApproval(
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

		String retVal = "false";

		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo, false);
				if(orderMt.getPendApprovalFlg()) {
					retVal = "true";	
				}else {
					retVal = "false";
				}

		

		return retVal;

	}
	
	
	@RequestMapping(value = "/addOrder/quotation", method = RequestMethod.POST)
	@ResponseBody
	public String addOrdQuot(
			@RequestParam(value = "ordMtId") Integer ordMtId,
		    @RequestParam(value = "data") String jsonData,
			Principal principal) {
		
		String retVal ="-1";
		
		/*
		 * synchronized (this) { try { retVal =
		 * meltingIssDtService.meltingStn	Transfer(meltingMtId, jsonData,principal);
		 * if(retVal == "1"){ retVal = "Data Transfered Successfully"; } } catch
		 * (Exception e) { e.printStackTrace(); retVal =
		 * "Warning:Error Occoured,Contact Support"; } }
		 */
		retVal=orderDtService.addOrderFromQuot(ordMtId, jsonData, principal);
		
		return retVal;
	}
	
	@RequestMapping(value = "/orderDt/excelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model, @RequestParam("excelfile") MultipartFile excelfile, HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile,@RequestParam(value="orderMtPkId",required = false)Integer orderMtPkId, RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		
		String retVal = "yes";
		
		synchronized (this) {
			
			retVal = orderDtService.orderDtExcelUpload(excelfile, session, tempExcelFile, principal, orderMtPkId);
		}
		
		model.addAttribute("tableDisp", "yes");
		model.addAttribute("retVal", retVal);
		model.addAttribute("orderMtPkId", orderMtPkId);
		
		return "uploadExcelOrderDtStyle";
	}

	
	//--------order Details excel file download----//
	
	@RequestMapping("/orderDt/downloadExcel/format")
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

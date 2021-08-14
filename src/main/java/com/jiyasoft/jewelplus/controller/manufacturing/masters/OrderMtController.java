package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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

import com.itextpdf.text.DocumentException;
import com.jiyasoft.jewelplus.common.CommonUtils;
import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/masters")
@Controller
public class OrderMtController {

	@Autowired
	private IOrderMtService orderMtService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private IOrderDtService orderDtService;


	@Autowired
	private ILabourTypeService labourTypeService;

	@Autowired
	private IOrderTypeService orderTypeService;

	@Autowired
	private UserService userService;

	@Autowired
	private IPartyService partyService;

	@Autowired
	private IDesignService designService;

	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;

	@Autowired
	private IComponentService componentService;

	@Autowired
	private ISettingService settingService;

	@Autowired
	private ISettingTypeService settingTypeService;

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IQuotMtService quotMtService;

	@Autowired
	private IQuotDtService quotDtService;

	@Autowired
	private UserRoleService userRoleService;


	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	

	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IDepartmentService departmentService;

	
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${orderAuotoNumber}")
	private Boolean orderAuotoNumber;

	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;

	@ModelAttribute("orderMt")
	public OrderMt constructOrderMt() {
		return new OrderMt();
	}

	@ModelAttribute("orderDt")
	public OrderDt constructOrderDt() {
		return new OrderDt();
	}

	@ModelAttribute("orderStnDt")
	public OrderStnDt constructOrderStnDt() {
		return new OrderStnDt();
	}

	@ModelAttribute("orderCompDt")
	public OrderCompDt constructOrderCompDt() {
		return new OrderCompDt();
	}

	@ModelAttribute("orderQuality")
	public OrderQuality constructOrderQuality() {
		return new OrderQuality();
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
	
	@RequestMapping("/orderApproval")
	public String orderApproval() {
		return "orderApproval";
	}
	
	@RequestMapping("/orderUnApproval")
	public String orderUnApproved() {
		return "orderUnApproval";
	}


	@RequestMapping("/orderDt/uploadExcel")
	public String orderDtExcelFilePage(Model model) {
		model.addAttribute("tableDisp", "no");
		return "uploadExcelOrderDtStyle";
	}

	
	@RequestMapping("/order")
	public String users(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("order");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "order";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "order";
		}
	}

	@RequestMapping("/autoOrderClose")
	public String orderClose(Model model) {
		return "autoOrderClose";
	}

	@RequestMapping("/order/listing")
	@ResponseBody
	public String orderListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<OrderMt> orders = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("order");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		// Long rowCount = null;

//		rowCount =orderMtService.countAll(search);
		orders = orderMtService.searchAll(limit, offset, sort, order, search);

		sb.append("{\"total\":").append(orders.getTotalElements()).append(",\"rows\": [");

		for (OrderMt orderObj : orders) {

					sb.append("{\"id\":\"")
					.append(orderObj.getId())
					.append("\",\"invNo\":\"")
					.append(orderObj.getInvNo() != null ? orderObj.getInvNo() : "" )
					.append("\",\"party\":\"")
					.append((orderObj.getParty() == null ? "" : orderObj.getParty().getPartyCode()))
					.append("\",\"refNo\":\"").append(orderObj.getRefNo() == null ? "" : orderObj.getRefNo())
					.append("\",\"ordDate\":\"")
					.append(orderObj.getInvDateStr() == null ? "" : orderObj.getInvDateStr())
					.append("\",\"prodDate\":\"")
					.append(orderObj.getProductionDate() == null ? "" : orderObj.getProdDateStr())
					.append("\",\"delDate\":\"")
					.append(orderObj.getDispatchDate() == null ? "" : orderObj.getDischpathDateStr())
					.append("\",\"totQty\":\"").append(orderDtService.getOrderDtTotal(orderObj.getId()))
					.append("\",\"orderClose\":\"")
					.append((orderObj.getOrderClose() == null ? "Open" : (orderObj.getOrderClose() ? "Close" : "Open")))
					.append("\",\"approve\":\"")
					.append(orderObj.getPendApprovalFlg() ? "No":"Yes")
					.append("\",\"deactiveDt\":\"")
					.append((orderObj.getDeactiveDt() == null ? "" : orderObj.getDeactiveDt()))
					.append("\",\"actionClose\":\"").append("<a href='javascript:doOrderCloseOpen(event,")
					.append(orderObj.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Close-Open</a>");

			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

				sb.append("\",\"action1\":\"");

				sb.append("<a href='/jewels/manufacturing/masters/order/edit/").append(orderObj.getId());

				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				sb.append("<a onClick='javascript:orderDtDelete(event, ").append(orderObj.getId())
						.append(", 0);' href='javascript:void(0);'")
						.append(" class='btn btn-xs btn-danger triggerRemove").append(orderObj.getId());

				sb.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");

				sb.append("\",\"action3\":\"").append("<a href='/jewels/manufacturing/masters/order/view/")
						.append(orderObj.getId()).append(".html'")
						.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>");

			} else {
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/order/edit/").append(orderObj.getId());
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append("<a onClick='javascript:orderDtDelete(event, ").append(orderObj.getId())
							.append(", 0);' href='javascript:void(0);'")
							.append(" class='btn btn-xs btn-danger triggerRemove").append(orderObj.getId());

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");

				sb.append("\",\"action3\":\"");
				if (roleRights.getCanView()) {
					sb.append("<a href='/jewels/manufacturing/masters/order/view/").append(orderObj.getId())
							.append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>");
			}

			sb.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	
	
	
	@RequestMapping("/order/filterListing")
	@ResponseBody
	public String filterListing(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<OrderMt> orders = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		orders = orderMtService.searchAll(limit, offset, sort, order, search);

		sb.append("{\"total\":").append(orders.getTotalElements()).append(",\"rows\": [");

		for (OrderMt orderObj : orders) {

					sb.append("{\"id\":\"")
					.append(orderObj.getId())
					.append("\",\"invNo\":\"")
					.append(orderObj.getInvNo() != null ? orderObj.getInvNo() : "" )
					.append("\",\"party\":\"")
					.append((orderObj.getParty() == null ? "" : orderObj.getParty().getPartyCode()))
					.append("\",\"refNo\":\"").append(orderObj.getRefNo() == null ? "" : orderObj.getRefNo())
					.append("\",\"ordDate\":\"")
					.append(orderObj.getInvDateStr() == null ? "" : orderObj.getInvDateStr())
					.append("\",\"prodDate\":\"")
					.append(orderObj.getProductionDate() == null ? "" : orderObj.getProdDateStr())
					.append("\",\"delDate\":\"")
					.append(orderObj.getDispatchDate() == null ? "" : orderObj.getDischpathDateStr())
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}
	
	
	
	private Model populateModel(Model model, Principal principal) {
		model.addAttribute("orderTypeMap", orderTypeService.getOrderTypeList());
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("labourTypeMap", labourTypeService.getLabourTypeList());
		model.addAttribute("settingMap", settingService.getSettingList());
		model.addAttribute("settingTypeMap", settingTypeService.getSettingTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("orderAuotoNumber", orderAuotoNumber);
		model.addAttribute("priorityMap", CommonUtils.getOrderPriorityList());
		model.addAttribute("gradingMap", lookUpMastService.getActiveLookUpMastByType("Grading"));
		model.addAttribute("laserMarkMap", lookUpMastService.getActiveLookUpMastByType("LaserMark"));
		model.addAttribute("hallMarkMap", lookUpMastService.getActiveLookUpMastByType("HallMark"));
		model.addAttribute("ordDivisionMap", lookUpMastService.getActiveLookUpMastByType("Ord Division"));

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		model.addAttribute("adminRightsMap",
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
						|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
						|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE"));

		return model;
	}

	@RequestMapping("/order/add")
	public String add(Model model, Principal principal) {
		model = populateModel(model, principal);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("order");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);

			return "order/add";

		} else {

			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}

		}

		return "order/add";
	}

	@ResponseBody
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	public String addEditOrderMt(@Valid @ModelAttribute("orderMt") OrderMt orderMt, BindingResult result,
			@RequestParam(value = "id") Integer id, RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "orderMtPartyId") int partyId) {

		
		synchronized (this) {
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			
			String action = "added";
			String retVal = "/jewels/manufacturing/masters/order/add.html";
			Date vDate = null;

			if (result.hasErrors()) {
				return "order/add";
			}

		

				if (id != null) {

					if (orderDtService.getTotalOrderQtys(id) > 0) {

						OrderMt orderMt2 = orderMtService.findOne(id);

						orderMt.setInvNo(orderMt2.getInvNo().trim());
						orderMt.setInvDate(orderMt2.getInvDate());
						orderMt.setOrderType(orderMt2.getOrderType());
						orderMt.setParty(orderMt2.getParty());

					}

			}

			// Party party = partyService.findOne(partyId);

			if (orderAuotoNumber.equals(false)) {

				if(orderMt.getInvNo() != null) {
				OrderMt orderDuplicate = orderMtService.findByInvNoAndDeactive(orderMt.getInvNo().trim(), false);
				if (orderDuplicate != null) {
					if (id == null) {
						return "Error: Duplicate Invoice No";
					} else {
						if (!orderDuplicate.getId().equals(id)) {
							return "Error: Duplicate Invoice No";
						}
					}
				}
				}else {
					return "Error: Invoice No Compulsory";
				}
			}

			if (id == null || id.equals("") || (id != null && id == 0)) {
				vDate = new java.util.Date();
				orderMt.setCreatedBy(principal.getName());
				orderMt.setCreatedDt(vDate);
				orderMt.setUniqueId(vDate.getTime());
				orderMt.setPendApprovalFlg(true);

			} else {

				orderMt.setModiBy(principal.getName());
				orderMt.setModiDt(new java.util.Date());
				// orderMt.setParty(party);
				action = "updated";
				retVal = "/jewels/manufacturing/masters/order/edit/" + orderMt.getId()
						+ ".html?action=updated&success=true";
				
				if(orderMt.getDispatchDate() !=null) {
					List<OrderDt>orderDts = orderDtService.findByOrderMtAndDeactive(orderMt, false);
					for(OrderDt orderDt: orderDts) {
						if(orderDt.getDueDate() ==null) {
							orderDt.setDueDate(orderMt.getDispatchDate());
							orderDtService.save(orderDt);
						}
						
						
					}
					
				}
				
			}
			
			orderMt.setRemark(orderMt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			orderMt.setRefNo(orderMt.getRefNo().replaceAll("[\\n\\t\\r ]", " ").trim());

			if (orderMt.getOrderType().getId() == null) {
				orderMt.setOrderType(null);
			}

			if (orderMt.getPurity().getId() == null) {
				orderMt.setPurity(null);
			}
			if (orderMt.getColor().getId() == null) {
				orderMt.setColor(null);
			}
			
			if (orderMt.getHallMark().getId() == null) {
				orderMt.setHallMark(null);
			}
			
			if (orderMt.getGrading().getId() == null) {
				orderMt.setGrading(null);
			}
			
			if (orderMt.getLaserMark().getId() == null) {
				orderMt.setLaserMark(null);
			}
			
			if (orderMt.getOrdDivision().getId() == null) {
				orderMt.setOrdDivision(null);
			}

			if (action.equals("added")) {

				Long maxSrno = orderMtService.getMaxSrno();
				maxSrno = (maxSrno == null ? 1 : maxSrno + 1);
				orderMt.setSrno(maxSrno);
			}
			orderMtService.save(orderMt);

			if (action.equals("added")) {

				if (orderAuotoNumber.equals(true)) {
					
					LookUpMast lookUpMast= lookUpMastService.findOne(orderMt.getOrdDivision().getId());
					OrderType orderType = orderTypeService.findOne(orderMt.getOrderType().getId());
					
					
					Calendar date = new GregorianCalendar();
					String vYear = "" + date.get(Calendar.YEAR);
					vYear = vYear.substring(2);

					OrderMt vOrderMt = orderMtService.findByUniqueId(vDate.getTime());
					vOrderMt.setInvNo(lookUpMast.getCode()+orderType.getCode()+String.format("%04d", vOrderMt.getSrno())+"-"+ vYear);

					orderMtService.save(vOrderMt);
					retVal = "/jewels/manufacturing/masters/order/edit/" + vOrderMt.getId()
							+ ".html?action=added&success=true";

				} else {
					OrderMt vOrderMt = orderMtService.findByUniqueId(vDate.getTime());
					retVal = "/jewels/manufacturing/masters/order/edit/" + vOrderMt.getId()
							+ ".html?action=added&success=true";
				}

			}

			return retVal;
		}
		
	
	}

	@RequestMapping("/order/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, Principal principal) {
		OrderMt order = orderMtService.findOne(id);
		model.addAttribute("orderMt", order);
		model.addAttribute("rLockStatus", order.getrLock());
		model = populateModel(model, principal);
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("purityMap", purityService.getPurityList());

		model.addAttribute("gradingMap", lookUpMastService.getActiveLookUpMastByType("Grading"));
		model.addAttribute("laserMarkMap", lookUpMastService.getActiveLookUpMastByType("LaserMark"));
		model.addAttribute("hallMarkMap", lookUpMastService.getActiveLookUpMastByType("HallMark"));
		model.addAttribute("ordDivisionMap", lookUpMastService.getActiveLookUpMastByType("Ord Division"));

		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("order");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("roleNm", userRole.getRoleMast().getRoleNm());

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("pendingApprovalFlg", order.getPendApprovalFlg());

			return "order/add";

		} else {

			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				model.addAttribute("pendingApprovalFlg", order.getPendApprovalFlg());
			}

		}

		return "order/add";

	}

	@RequestMapping("/order/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		OrderMt order = orderMtService.findOne(id);
		model.addAttribute("orderMt", order);
		model.addAttribute("rLockStatus", order.getrLock());
		model = populateModel(model, principal);
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("purityMap", purityService.getPurityList());

		model.addAttribute("gradingMap", lookUpMastService.getActiveLookUpMastByType("Grading"));
		model.addAttribute("laserMarkMap", lookUpMastService.getActiveLookUpMastByType("LaserMark"));
		model.addAttribute("hallMarkMap", lookUpMastService.getActiveLookUpMastByType("HallMark"));
		
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("order");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("roleNm", userRole.getRoleMast().getRoleNm());

		model = populateModel(model, principal);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);

			return "order/add";

		} else {

			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
			}

			return "order/add";
		}
	}

	@RequestMapping("/order/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		return "" + orderMtService.delete(id);
	}

	@ResponseBody
	@RequestMapping("/orderMt/orderCloseOpen/{id}")
	public String orderCloseOpen(@PathVariable int id, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			OrderMt orderMt = orderMtService.findOne(id);

			String retVal = null;

			if (orderMt.getOrderClose().equals(true)) {
				orderMt.setOrderClose(false);
				retVal = "-1";
			} else {
				orderMt.setOrderClose(true);
				orderMt.setOrderCloseDt(new java.util.Date());
				retVal = "-2";
			}

			orderMtService.save(orderMt);

			return retVal;
			
		}else {
			return "-3";
		}

	

	}

	@RequestMapping("/orderDesignStone")
	public String orderDesignStone() {
		return "orderDesignStone";
	}

	@RequestMapping("/purity/list")
	@ResponseBody
	public String subCategoryList(@RequestParam(value = "metalId") Integer metalId,
			@ModelAttribute("orderMt") OrderMt orderMt) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> purityMap = purityService.getPurityList(metalId);

		sb.append("<select id=\"purity.id\" name=\"purity.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Purity -</option>");
		for (Object key : purityMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">").append(purityMap.get(key))
					.append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@RequestMapping("/styleNo/list")
	@ResponseBody
	public String styleNoList(@RequestParam(value = "term", required = false) String styleNo) {

		Integer limit = 15;

		if (styleNo.length() >= 7) {
			limit = 100;
		}

		Page<Design> designList = designService.findByMainStyleNo(limit, 0, "styleNo", "ASC", styleNo.toUpperCase(),
				true);

		StringBuilder sb = new StringBuilder();

		for (Design design : designList) {
			sb.append("\"").append(design.getMainStyleNo()).append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("/orderNo/Available")
	@ResponseBody
	public String orderNoAvailable(@RequestParam String orderNo) {
		Boolean orderNoAvailable = false;

		if (orderNo == null) {
		} else {
			orderNo = orderNo.trim();
			orderNoAvailable = (orderMtService.findByInvNoAndDeactive(orderNo, false) != null);
		}

		System.out.println(
				"\n\n\n\n\n orderMtService.findByInvNo(orderNo) " + orderNoAvailable + " - orderNo " + orderNo);

		return orderNoAvailable.toString();
	}

	// ---------orderMt report listing---------//

	@RequestMapping("/order/report/listing")
	@ResponseBody
	public String orderReportListing(Model model, @RequestParam(value = "partyId", required = false) String partyIds,
			@RequestParam(value = "orderTypeId", required = false) String orderTypeIds,
			@RequestParam(value = "ordFlg", required = false) String ordFlg,
			@RequestParam(value = "fromDate",required = false ) String fromDate,
			@RequestParam(value = "toDate",required = false) String toDate,
			@RequestParam(value = "divisionIds",required = false) String divisionIds,
			@RequestParam(value = "regionIds",required = false) String regionIds,
			@RequestParam(value = "customerTypeIds",required = false) String customerTypeIds
			) throws ParseException {

		StringBuilder sb = new StringBuilder();

		List<OrderMt> orderMts = orderMtService.getOrderList(partyIds, orderTypeIds, ordFlg,fromDate,toDate,divisionIds,regionIds,customerTypeIds);

		// Long rowCount = orderMtService.count(partyIds, orderTypeIds);

		sb.append("{\"total\":").append(orderMts.size()).append(",\"rows\": [");

		for (OrderMt orderObj : orderMts) {
			sb.append("{\"id\":\"")
			    .append(orderObj.getId())
			    .append("\",\"invNo\":\"")
			    .append(orderObj.getInvNo())
				.append("\",\"refNo\":\"")
				.append((orderObj.getRefNo() != null ? orderObj.getRefNo() : ""))
				.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	// ---------orderMt Style report listing---------//

	@RequestMapping("/orderWiseStyle/report/listing")
	@ResponseBody
	public String orderStyleReportListing(Model model,
			@RequestParam(value = "orderId", required = false) String orderId) {

		StringBuilder sb = new StringBuilder();

		List<OrderDt> orderDts = orderDtService.getorderStyleList(orderId);

		sb.append("{\"total\":").append(orderDts.size()).append(",\"rows\": [");

		for (OrderDt orderDt : orderDts) {
			sb.append("{\"id\":\"").append(orderDt.getId()).append("\",\"designNo\":\"")
					.append(orderDt.getDesign().getMainStyleNo()).append("\",\"srNo\":\"").append(orderDt.getSrNo())
					.append("\"},");

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	// new method for all order close
	@ResponseBody
	@RequestMapping(value = "/autoOrderClose/closed", method = RequestMethod.POST)
	public String orderCloseAll() {

		return orderMtService.autoOrderClose();

	}

	@RequestMapping(value = "/applyRate/orderMt", method = RequestMethod.POST)
	@ResponseBody
	public String applyRate(@Valid @ModelAttribute("orderMt") OrderMt orderMt,
			@RequestParam(value = "tempPartyId") Integer partyId, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return "-1";
		}

		String retval = "-1";

		try {

			/*
			 * Party party = partyService.findOne(partyId); orderMt.setParty(party);
			 * if(orderMt.getPurity().getId() == null){ orderMt.setPurity(null); }
			 * if(orderMt.getColor().getId() == null){ orderMt.setColor(null); }
			 * 
			 * if (orderMt.getHallMark().getId() == null) { orderMt.setHallMark(null); }
			 * 
			 * if (orderMt.getGrading().getId() == null) { orderMt.setGrading(null); }
			 * 
			 * if (orderMt.getLaserMark().getId() == null) { orderMt.setLaserMark(null); }
			 * 
			 * orderMtService.save(orderMt);
			 */

			List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(orderMt, false);

			for (OrderDt orderDt : orderDts) {
				orderDtService.applyRate(orderDt, principal,netWtWithCompFlg);

			}
			retval = "1";

		} catch (Exception e) {
			e.printStackTrace();
			retval = "-1";
		}

		return retval;
	}
	
	
	
	@RequestMapping(value = "/applyQuotRate/orderMt", method = RequestMethod.POST)
	@ResponseBody
	public String applyQuotRate(@Valid @ModelAttribute("orderMt") OrderMt orderMt,
			@RequestParam(value = "tempPartyId") Integer partyId, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return "-1";
		}

		String retval = "-1";

		try {

			/*
			 * Party party = partyService.findOne(partyId); orderMt.setParty(party);
			 * if(orderMt.getPurity().getId() == null){ orderMt.setPurity(null); }
			 * if(orderMt.getColor().getId() == null){ orderMt.setColor(null); }
			 * 
			 * if (orderMt.getHallMark().getId() == null) { orderMt.setHallMark(null); }
			 * 
			 * if (orderMt.getGrading().getId() == null) { orderMt.setGrading(null); }
			 * 
			 * if (orderMt.getLaserMark().getId() == null) { orderMt.setLaserMark(null); }
			 * 
			 * orderMtService.save(orderMt);
			 */

			List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(orderMt, false);

			for (OrderDt orderDt : orderDts) {
				orderDtService.applyQuotRate(orderDt, principal,netWtWithCompFlg);

			}
			retval = "1";

		} catch (Exception e) {
			e.printStackTrace();
			retval = "-1";
		}

		return retval;
	}
	
	
	
	
	

	@ResponseBody
	@RequestMapping("/orderMt/barcode")
	public String barcode(@RequestParam(value = "mtId") Integer mtId, Principal principal) {

		return orderMtService.applyBarcode(mtId, principal, netWtWithCompFlg);
	}

	@RequestMapping("/order/pickUp")
	public String pickUp(Model model, @RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "pickUpType", required = false) String pickUpType) {

		model.addAttribute("pickUpType", pickUpType);

		return "order/pickUp";
	}

	@RequestMapping("/order/pickUp/listing")
	@ResponseBody
	public String orderListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal,
			@RequestParam(value = "partyId", required = false) Integer partyId) {

		StringBuilder sb = new StringBuilder();
		Party party = partyService.findOne(partyId);

		Page<QuotMt> quomts = quotMtService.findByParty(limit, offset, sort, order, search, true, party.getName());

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		sb.append("{\"total\":").append(quomts.getTotalElements()).append(",\"rows\": [");

		for (QuotMt quotMt : quomts) {
			sb.append("{\"id\":\"").append(quotMt.getId()).append("\",\"party\":\"")
					.append((quotMt.getParty() != null ? quotMt.getParty().getPartyCode() : "")).append("\",\"invNo\":\"")
					.append(quotMt.getInvNo()).append("\",\"invDate\":\"").append(quotMt.getInvDateStr())
					.append("\",\"metalRate\":\"").append((quotMt.getMetalRate() != null ? quotMt.getMetalRate() : ""))
					.append("\",\"silverRate\":\"")
					.append((quotMt.getSilverRate() != null ? quotMt.getMetalRate() : "")).append("\",\"discPerc\":\"")
					.append((quotMt.getDiscPercent() != null ? quotMt.getDiscPercent() : ""))
					.append("\",\"deactive\":\"")
					.append((quotMt.getDeactive() == null ? "" : (quotMt.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"action1\":\"").append("<a href='/jewels/manufacturing/transactions/quotMt/edit/")
					.append(quotMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/quotMt/delete/")
					.append(quotMt.getId()).append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(quotMt.getId()).append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;

	}

	@RequestMapping("/order/pickUpDt/listing")
	@ResponseBody
	public String quotDtPickUpListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal,
			@RequestParam(value = "invNo", required = false) String invNo,
			@RequestParam(value = "pickUpType", required = false) String pickUpType,
			@RequestParam(value = "forOrder", required = false) String forOrderNm) {

		/*
		 * int srno =offset;
		 */
		if (pickUpType.equalsIgnoreCase("order")) {

			OrderMt orderMt = orderMtService.findByInvNoAndDeactive(invNo, false);

			StringBuilder sb = new StringBuilder();
			Page<OrderDt> orderDts = orderDtService.searchAll(limit, offset, sort, order, search, orderMt.getId(),"edit");

			// Long rowCount = quotDtService.countAll(limit, offset, sort, order, search,
			// mtId);

			sb.append("{\"total\":").append(orderDts.getTotalElements()).append(",\"rows\": [");

			// Double balanceQty = 0.0;
			Double transferQty = 0.0;

			for (OrderDt orderDt : orderDts) {

				/* srno +=1; */
				sb.append("{\"id\":\"").append(orderDt.getId())
						/*
						 * .append("\",\"srNo\":\"") .append(srno)
						 */
						.append("\",\"barcode\":\"")
						.append(orderDt.getBarcode() != null ? orderDt.getBarcode() : "")
						.append("\",\"style\":\"")
						.append((orderDt.getDesign() != null ? orderDt.getDesign().getMainStyleNo() : ""))
						.append("\",\"defImage\":\"")
						.append((orderDt.getDesign() != null ? orderDt.getDesign().getDefaultImage() : "blank.png"))
						.append("\",\"pcs\":\"")
						.append((orderDt.getPcs() != null ? orderDt.getPcs() : ""))
						.append("\",\"purity\":\"")
						.append((orderDt.getPurity() != null ? orderDt.getPurity().getName() : ""))
						.append("\",\"color\":\"")
						.append((orderDt.getColor() != null ? orderDt.getColor().getName() : ""))
						.append("\",\"grossWt\":\"")
						.append((orderDt.getGrossWt() != null ? orderDt.getGrossWt() : ""))
						.append("\",\"netWt\":\"")
						.append((orderDt.getNetWt() != null ? orderDt.getNetWt() : ""))
						.append("\",\"metalRate\":\"")
						.append((orderDt.getMetalRate() != null ? orderDt.getMetalRate() : ""))
						.append("\",\"metalValue\":\"")
						.append((orderDt.getMetalValue() != null ? orderDt.getMetalValue() : ""))
						.append("\",\"stoneValue\":\"")
						.append((orderDt.getStoneValue() != null ? orderDt.getStoneValue() : ""))
						.append("\",\"compValue\":\"")
						.append((orderDt.getCompValue() != null ? orderDt.getCompValue() : ""))
						.append("\",\"labourValue\":\"")
						.append((orderDt.getLabValue() != null ? orderDt.getLabValue() : ""))
						.append("\",\"setValue\":\"")
						.append((orderDt.getSetValue() != null ? orderDt.getSetValue() : ""))
						.append("\",\"handlingCost\":\"")
						.append((orderDt.getHdlgValue() != null ? orderDt.getHdlgValue() : ""))
						.append("\",\"fob\":\"")
						.append((orderDt.getFob() != null ? orderDt.getFob() : ""))
						.append("\",\"finalPrice\":\"")
						.append((orderDt.getFinalPrice() != null ? orderDt.getFinalPrice() : ""))
						.append("\",\"discAmount\":\"")
						.append((orderDt.getDiscAmount() != null ? orderDt.getDiscAmount() : ""))
						.append("\",\"image\":\"")
						.append(orderDt.getDesign().getDefaultImage() != null ? orderDt.getDesign().getDefaultImage(): "blank.png")
						.append("\",\"rLock\":\"")
						.append(orderDt.getrLock()) // 1 = lock & 0 = unlock
						.append("\",\"actionLock\":\"")
						.append("<a href='javascript:doQuotDtLockUnLock(")
						.append(orderDt.getId())
						.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
						.append("\",\"adjustedQty\":\"")
						.append(transferQty)
						.append("\",\"balanceQty\":\"")
						.append((orderDt.getPcs() != null ? orderDt.getPcs() : ""))
						.append("\",\"transferQty\":\"")
						.append(orderDt.getPcs() != null ? orderDt.getPcs() : "")
						.append("\"},");

			}

			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";

			return str;

		} else {

			QOrderDt qOrderDt = QOrderDt.orderDt;

			QuotMt quotMt = quotMtService.findByInvNoAndDeactive(invNo, false);

			StringBuilder sb = new StringBuilder();

			Page<QuotDt> quotDts =quotDtService.quotDtPickUpList(limit, offset, sort, order, search, quotMt.getId(),
						forOrderNm,pickUpType);
		
					
					
					

			// Long rowCount = quotDtService.countAll(limit, offset, sort, order, search,
			// mtId);

			OrderMt orderMt = null;
			if (forOrderNm != null && !forOrderNm.isEmpty()) {
				orderMt = orderMtService.findByInvNoAndDeactive(forOrderNm, false);
			}

			sb.append("{\"total\":").append(quotDts.getTotalElements()).append(",\"rows\": [");

			Double balanceQty = 0.0;

			Double transferQty = 0.0;

			for (QuotDt quotDt : quotDts) {

				transferQty = quotDt.getPcs();

				if (forOrderNm != null && !forOrderNm.isEmpty()) {

					JPAQuery query = new JPAQuery(entityManager);
					List<Tuple> ordQty = query.from(qOrderDt)
							.where(qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.eq(orderMt.getId()))
									.and(qOrderDt.design.id.eq(quotDt.getDesign().getId()))
									.and(qOrderDt.purity.id.eq(quotDt.getPurity().getId()))
									.and(qOrderDt.color.id.eq(quotDt.getColor().getId())))
							.list(qOrderDt.pcs, qOrderDt.id);

					for (Tuple ord : ordQty) {
						transferQty = ord.get(qOrderDt.pcs);
					}

				}

				/*
				 * if (pickUpType.equalsIgnoreCase("orderFromQuot")) {
				 * 
				 * balanceQty = quotDt.getPcs() - quotDt.getAdjustedQty(); if (balanceQty <= 0)
				 * { continue; } }
				 */

				/* srno +=1; */
				sb.append("{\"id\":\"").append(quotDt.getId())
						/*
						 * .append("\",\"srNo\":\"") .append(srno)
						 */
						.append("\",\"barcode\":\"").append(quotDt.getBarcode() != null ? quotDt.getBarcode() : "")
						.append("\",\"style\":\"")
						.append((quotDt.getDesign() != null ? quotDt.getDesign().getMainStyleNo() : ""))
						.append("\",\"defImage\":\"")
						.append((quotDt.getDesign() != null ? quotDt.getDesign().getDefaultImage() : "blank.png"))
						.append("\",\"pcs\":\"").append((quotDt.getPcs() != null ? quotDt.getPcs() : ""))
						.append("\",\"purity\":\"")
						.append((quotDt.getPurity() != null ? quotDt.getPurity().getName() : ""))
						.append("\",\"color\":\"")
						.append((quotDt.getColor() != null ? quotDt.getColor().getName() : ""))
						.append("\",\"grossWt\":\"").append((quotDt.getGrossWt() != null ? quotDt.getGrossWt() : ""))
						.append("\",\"netWt\":\"").append((quotDt.getNetWt() != null ? quotDt.getNetWt() : ""))
						.append("\",\"metalRate\":\"")
						.append((quotDt.getMetalRate() != null ? quotDt.getMetalRate() : ""))
						.append("\",\"metalValue\":\"")
						.append((quotDt.getMetalValue() != null ? quotDt.getMetalValue() : ""))
						.append("\",\"stoneValue\":\"")
						.append((quotDt.getStoneValue() != null ? quotDt.getStoneValue() : ""))
						.append("\",\"compValue\":\"")
						.append((quotDt.getCompValue() != null ? quotDt.getCompValue() : ""))
						.append("\",\"labourValue\":\"")
						.append((quotDt.getLabValue() != null ? quotDt.getLabValue() : "")).append("\",\"setValue\":\"")
						.append((quotDt.getSetValue() != null ? quotDt.getSetValue() : ""))
						.append("\",\"handlingCost\":\"")
						.append((quotDt.getHdlgValue() != null ? quotDt.getHdlgValue() : "")).append("\",\"fob\":\"")
						.append((quotDt.getFob() != null ? quotDt.getFob() : "")).append("\",\"finalPrice\":\"")
						.append((quotDt.getFinalPrice() != null ? quotDt.getFinalPrice() : ""))
						.append("\",\"discAmount\":\"")
						.append((quotDt.getDiscAmount() != null ? quotDt.getDiscAmount() : ""))
						.append("\",\"image\":\"")
						.append(quotDt.getDesign().getDefaultImage() != null ? quotDt.getDesign().getDefaultImage()
								: "blank.png")
						.append("\",\"rLock\":\"").append(quotDt.getrLock()) // 1 = lock & 0 = unlock
						.append("\",\"actionLock\":\"").append("<a href='javascript:doQuotDtLockUnLock(")
						.append(quotDt.getId())
						.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
						.append("\",\"adjustedQty\":\"")
						.append((quotDt.getAdjustedQty() != null ? quotDt.getAdjustedQty() : ""))
						.append("\",\"balanceQty\":\"").append(balanceQty).append("\",\"transferQty\":\"")
						.append(transferQty).append("\"},");

			}

			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";

			return str;
		}

	}

	@RequestMapping(value = "/order/transfer", method = RequestMethod.POST)
	@ResponseBody
	public String pickUpDataTransfer(Principal principal, @RequestParam(value = "pODIds") String pOIds,
			@RequestParam(value = "pTransferQty") String pTransferQty, @RequestParam(value = "mtId") Integer mtId,
			@RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "pickUpType", required = false) String pickUpType) {

		String str = "";

		if (pickUpType.equalsIgnoreCase("orderFromQuot")) {

			str = orderDtService.quotToOderPickup(pOIds, pTransferQty, mtId, principal, partyId);

		} else if (pickUpType.equalsIgnoreCase("order")) {
			str = orderDtService.orderToOderPickup(pOIds, pTransferQty, mtId, principal, partyId);
		} else {
			str = quotDtService.quotToQuotPickup(pOIds, pTransferQty, mtId, principal, partyId);
		}

		return str;

	}
	
	@RequestMapping(value = "/quaotDt/pickupFromOrder", method = RequestMethod.POST)
	@ResponseBody
	public String pickUpFromOrder(Principal principal, @RequestParam(value = "pODIds") String pOIds,
			@RequestParam(value = "mtId") Integer mtId) {

		String str = "";
		
			str = quotDtService.orderToQuotPickup(pOIds, mtId, principal);

		return str;

	}


	@RequestMapping("/order/invNoList")
	@ResponseBody
	public String invNoList(@RequestParam(value = "term", required = false) String invNo,
			@RequestParam(value = "partyNm") String partyNm) {

		Page<OrderMt> orderMtList = orderMtService.findByInvNoListByParty(15, 0, "invNo", "ASC", invNo.toUpperCase(),
				true, partyNm);

		StringBuilder sb = new StringBuilder();

		for (OrderMt orderMt : orderMtList) {
			sb.append("\"").append(orderMt.getInvNo()).append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}

	@RequestMapping("/order/mismatchOrderWithQuot")
	@ResponseBody
	public String mismatchOrderWithQuot(@RequestParam(value = "quotInvNo", required = false) String quotInvNo,
			@RequestParam(value = "ordInvNo", required = false) String ordInvNo, Principal principal)
			throws JRException, IOException, DocumentException {

		String retVal = "";
		QuotMt quotMt = quotMtService.findByInvNoAndDeactive(quotInvNo, false);
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(ordInvNo, false);

		String fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/")
				+ "ordquotnotmatch.jasper";

		InputStream input = new FileInputStream(new File(fileName));
		java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();

		parametersMap.put("quotInvNo", "'" + quotInvNo + "'");
		parametersMap.put("ordInvNo", "'" + ordInvNo + "'");
		parametersMap.put("quotMtId", quotMt.getId());
		parametersMap.put("ordMtId", orderMt.getId());

		Connection conn = Utils.getConnection();

		JasperPrint jp = JasperFillManager.fillReport(input, parametersMap, conn);

		String commonFileName = System.currentTimeMillis() + "";
		commonFileName = commonFileName + "ordquotnotmatch";
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath + commonFileName + ".pdf");
		file.createNewFile();
		JasperExportManager.exportReportToPdfFile(jp,
				uploadDirecotryPath + reportoutputdirectorypath + commonFileName + ".pdf");

		String exportCommonFileName = System.currentTimeMillis() + "" + principal.getName() + "ordquotnotmatch";
		Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath + commonFileName + ".pdf",
				uploadDirecotryPath + reportoutputdirectorypath + exportCommonFileName + ".pdf");

		file.delete();

		retVal = "-1$" + exportCommonFileName;

		return retVal;

	}
	
	
	@RequestMapping("/order/uploadExcel")
	public String excelFilePage(Model model) {
		model.addAttribute("tableDisp", "no");
		return "uploadExcelOrder";
	}


	@RequestMapping(value = "/order/excelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model, @RequestParam("excelfile") MultipartFile excelfile, HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile, RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		String retVal = "yes";
		
		synchronized (this) {
			
			retVal = orderMtService.orderExcelUpload(excelfile, session, tempExcelFile,principal);
		}
		
		model.addAttribute("tableDisp", "yes");
		model.addAttribute("retVal", retVal);
		
		return "orderExcelFileUploaded";
	}
	
	
	@RequestMapping("/orderExcel/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<OrderExcel> orderExcels = (List<OrderExcel>) httpSession.getAttribute("orderExcelSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(orderExcels.size()).append(",\"rows\": [");
		
		for(OrderExcel orderExcel:orderExcels){
			
			sb.append("{\"orderType\":\"")
			.append((orderExcel.getOrderType() != null ? orderExcel.getOrderType() : ""))
			.append("\",\"orderNo\":\"")
			.append((orderExcel.getOrderNo() != null ? orderExcel.getOrderNo() : ""))
			.append("\",\"party\":\"")
			.append((orderExcel.getParty()))
			.append("\",\"mtRefNo\":\"")
			.append((orderExcel.getMtRefNo() != null ? orderExcel.getMtRefNo() : ""))
			.append("\",\"prodDate\":\"")
			.append((orderExcel.getProdDate() != null ? orderExcel.getProdDate() : ""))
			.append("\",\"deliveryDate\":\"")
			.append((orderExcel.getDeliveryDate() != null ? orderExcel.getDeliveryDate() : ""))
			.append("\",\"srNo\":\"")
			.append(orderExcel.getSrNo() !=null ? orderExcel.getSrNo() :"")
			.append("\",\"styleNo\":\"")
			.append(orderExcel.getStyleNo() !=null ? orderExcel.getStyleNo() :"")
			.append("\",\"purity\":\"")
			.append(orderExcel.getPurity() !=null ? orderExcel.getPurity() :"")
			.append("\",\"color\":\"")
			.append(orderExcel.getColor() !=null ? orderExcel.getColor() :"")
			.append("\",\"qty\":\"")
			.append(orderExcel.getQty() !=null ? orderExcel.getQty() :"")
			.append("\",\"netWt\":\"")
			.append(orderExcel.getNetWt() !=null ? orderExcel.getNetWt() :"")
			.append("\",\"prodSize\":\"")
			.append(orderExcel.getProdSize() !=null ? orderExcel.getProdSize() :"")
			.append("\",\"dtRefNo\":\"")
			.append(orderExcel.getDtRefNo() !=null ? orderExcel.getDtRefNo() :"")
			.append("\",\"stamp\":\"")
			.append(orderExcel.getStamp() !=null ? orderExcel.getStamp() :"")
			.append("\",\"itemRemark\":\"")
			.append(orderExcel.getItemRemark() !=null ? orderExcel.getItemRemark() :"")
			.append("\",\"ordRefNo\":\"")
			.append(orderExcel.getOrdRefNo() !=null ? orderExcel.getOrdRefNo() :"")
			.append("\",\"ordRefNo\":\"")
			.append(orderExcel.getOrdRefNo() !=null ? orderExcel.getOrdRefNo() :"")
			.append("\",\"item\":\"")
			.append(orderExcel.getItem() !=null ? orderExcel.getItem() :"")
			.append("\",\"shape\":\"")
			.append(orderExcel.getShape() !=null ? orderExcel.getShape() :"")
			.append("\",\"quality\":\"")
			.append(orderExcel.getQuality() !=null ? orderExcel.getQuality() :"")
			.append("\",\"carat\":\"")
			.append(orderExcel.getCarat() !=null ? orderExcel.getCarat() :"")
			.append("\",\"rate\":\"")
			.append(orderExcel.getRate() !=null ? orderExcel.getRate() :"")
			.append("\",\"amount\":\"")
			.append(orderExcel.getAmount() !=null ? orderExcel.getAmount() :"")
			.append("\",\"statusRemark\":\"")
			.append(orderExcel.getStatusRemark() !=null ? orderExcel.getStatusRemark() :"")
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	@RequestMapping(value = "/orderMt/updatepuritycost", method = RequestMethod.POST)
	@ResponseBody
	public String updatepuritycost(@Valid @ModelAttribute("orderMt") OrderMt orderMt,
			@RequestParam(value="tempPartyId")Integer partyId,
			@RequestParam(value="tempPurityId")Integer purityId,
			@RequestParam(value="tempColorId")Integer colorId,BindingResult result,
			Principal principal){
		
		if (result.hasErrors()) {
			return "-1";
			
		}

		
		String retval="-1";
		
		try {
					
			List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(orderMt, false);
			
			for(OrderDt orderDt : orderDts){
				orderDtService.updateFob(orderDt, netWtWithCompFlg);
				
			} 
			retval="1";
			
		} catch (Exception e) {
			e.printStackTrace();
			retval="-1";
		}
			
		
		return retval;
	}
	
	
	
	// ---------get PartyCode for Stone Inward---------//

		@RequestMapping("/orderMt/getPartyCode")
		@ResponseBody
		public String getPartyCode(Model model,
				@RequestParam(value = "orderNo", required = false) String orderNo) {

			OrderMt orderMt =  orderMtService.findByInvNoAndDeactive(orderNo, false);
			
			List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(orderMt, false);
		
			Double orderPcs =0.0;
			for (OrderDt orderDt : orderDts) {
				orderPcs += orderDt.getPcs();
			}
			
			JSONObject jsonObject =  new JSONObject();
			jsonObject.put("partyCode", orderMt.getParty().getPartyCode());
			jsonObject.put("refNo", orderMt.getRefNo());
			jsonObject.put("orderPcs", orderPcs);

			return jsonObject.toString();
		}
		
		
		@RequestMapping("/orderApproval/listing")
		@ResponseBody
		public String orderApprovalListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
				@RequestParam(value = "offset", required = false) Integer offset,
				@RequestParam(value = "sort", required = false) String sort,
				@RequestParam(value = "order", required = false) String order,
				@RequestParam(value = "search", required = false) String search,
				@RequestParam(value = "approvalFlg", required = false) Boolean approvalFlg,Principal principal) {

			StringBuilder sb = new StringBuilder();
			Page<OrderMt> orders = null;

			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("order");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

			if ((search != null) && (search.trim().length() == 0)) {
				search = null;
			}

			orders = orderMtService.pendingForApprovalList(limit, offset, sort, order, search,approvalFlg);

			sb.append("{\"total\":").append(orders.getTotalElements()).append(",\"rows\": [");

			for (OrderMt orderObj : orders) {

						sb.append("{\"id\":\"")
						.append(orderObj.getId())
						.append("\",\"invNo\":\"")
						.append(orderObj.getInvNo() != null ? orderObj.getInvNo() : "" )
						.append("\",\"party\":\"")
						.append((orderObj.getParty() == null ? "" : orderObj.getParty().getPartyCode()))
						.append("\",\"refNo\":\"").append(orderObj.getRefNo() == null ? "" : orderObj.getRefNo())
						.append("\",\"ordDate\":\"")
						.append(orderObj.getInvDateStr() == null ? "" : orderObj.getInvDateStr())
						.append("\",\"prodDate\":\"")
						.append(orderObj.getProductionDate() == null ? "" : orderObj.getProdDateStr())
						.append("\",\"delDate\":\"")
						.append(orderObj.getDispatchDate() == null ? "" : orderObj.getDischpathDateStr())
						.append("\",\"totQty\":\"").append(orderDtService.getOrderDtTotal(orderObj.getId()))
						.append("\",\"orderClose\":\"")
						.append((orderObj.getOrderClose() == null ? "Open" : (orderObj.getOrderClose() ? "Close" : "Open")))
						.append("\",\"deactiveDt\":\"")
						.append((orderObj.getDeactiveDt() == null ? "" : orderObj.getDeactiveDt()));
						
						if(approvalFlg == true) {
							sb.append("\",\"action1\":\"")
							.append("<a href='javascript:orderApprovalDt(")
							.append(orderObj.getId())
							.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Approval</a>");}
						else {
								
								sb.append("\",\"action1\":\"")
								.append("<a href='javascript:orderUnApprovalDt(")
								.append(orderObj.getId())
								.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;UnApproved</a>");
							}
								sb.append("\"},");
			 
			}

			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";

			return str;
		}
			
		
		
		
		@ResponseBody
		@RequestMapping("/order/orderApprove")
		public String orderApprove(@RequestParam(value = "mtId")Integer mtId, Principal principal){
			
			String retVal = "1";
			
			try {
				
				synchronized (this) {
					
					retVal = orderMtService.orderApproval(mtId, principal);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return retVal;
			
		}
		
		@ResponseBody
		@RequestMapping("/order/orderUnApprove")
		public String orderUnApprove(@RequestParam(value = "mtId")Integer mtId, Principal principal){
			
			String retVal = "1";
			try {
				synchronized (this) {
					retVal = orderMtService.orderUnApproval(mtId, principal);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return retVal;
		}
		
		
		@ResponseBody
		@RequestMapping("/orderMt/getSalesPersonFromParty")
		public String getSalesPersonFromParty(@RequestParam(value = "partyId")Integer partyId, Principal principal){
			
			String retVal = "1";
			try {
				synchronized (this) {
					retVal = orderMtService.getSalesPersonFromParty(partyId, principal);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return retVal;
		}
		
		
		
		@RequestMapping("/diamondAllocationOrderList")
		@ResponseBody
		public String diamondAllocationOrderList(){
			
			
			List<Object[]> objects =new ArrayList<Object[]>();
			
			Department department = departmentService.findByName("Bagging");
			
				@SuppressWarnings("unchecked")
				TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_diamondAllocationOrderList(?) }");
				query.setParameter(1, department.getId());
				objects = query.getResultList();
			
			
			
			
			String str="";
			 StringBuilder sb = new StringBuilder();
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			 
			 for(Object[] list:objects){
				 
					
					sb.append("{\"mtId\":\"")
				     .append(list[0] != null ? list[0] : "")
				     .append("\",\"invNo\":\"")
					 .append(list[3] != null ? list[3] : "")
					 .append("\",\"refNo\":\"")
					 .append(list[4] != null ? list[4] : "")
					 .append("\",\"party\":\"")
					 .append(list[5] != null ? list[5] : "")
					
					 .append("\"},");
					
				}
			   
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				
			return str;
			
		
	}	
		
		
		
	
}
	



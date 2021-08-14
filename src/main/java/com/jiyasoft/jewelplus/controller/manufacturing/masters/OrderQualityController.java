package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class OrderQualityController {

	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private IOrderDtService orderDtService;

	@Autowired
	private IOrderQualityService orderQualityService;

	@Autowired
	private IOrderStnDtService orderStnDtService;;

	@Autowired
	private UserService userService;

	@Autowired
	private IShapeService shapeService;

	@Autowired
	private IQualityService qualityService;

	@Autowired
	private IStoneTypeService stoneTypeService;
	
	
	final private static Logger logger = LoggerFactory.getLogger(OrderQualityController.class);

	@ModelAttribute("orderMt")
	public OrderMt construct() {
		return new OrderMt();
	}

	@ModelAttribute("orderQuality")
	public OrderQuality constructOrderQuality() {
		return new OrderQuality();
	}

	@RequestMapping("/orderQuality")
	public String users(Model model) {
		//model.addAttribute("orderQuality", orderQualityService.findAll());

		return "order";
	}

	@RequestMapping("/orderQuality/listing")
	@ResponseBody
	public String orderQualityListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

		logger.info("Order Listing");

		StringBuilder sb = new StringBuilder();
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo,false);

		
		if (orderMt != null) {
			if (search == null) {
				search = "" + orderMt.getId();
			}
		
			
			List<OrderQuality> orderQualitys = orderQualityService.findByOrderMtAndDeactive(orderMt, false);

			sb.append("{\"total\":").append(orderQualitys.size()).append(",\"rows\": [");
			
			for (OrderQuality orderQuality : orderQualitys) {
				sb.append("{\"id\":\"")
					.append(orderQuality.getId())
					.append("\",\"stoneType\":\"")
					.append((orderQuality.getStoneType() == null ? "" : orderQuality.getStoneType().getName()))
					.append("\",\"shape\":\"")
					.append((orderQuality.getShape() == null ? "" : orderQuality.getShape().getName()))
					.append("\",\"quality\":\"")
					.append((orderQuality.getQuality() == null ? "" : orderQuality.getQuality().getName()))
					.append("\",\"deactive\":\"")
					.append((orderQuality.getDeactive() == null ? "Active" : (orderQuality.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((orderQuality.getDeactiveDt() == null ? "" : orderQuality.getDeactiveDt()))
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editOrderQuality(")
					.append(orderQuality.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteOrderQuality(event,")
				    .append(orderQuality.getId())
				    .append(");' class='btn btn-xs btn-danger triggerRemove")
				    .append(orderQuality.getId())
				    .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	@RequestMapping("/orderQuality/add")
	public String add(Model model) {
		return "orderQuality/add";
	}

	private Model populateModel(Model model) {
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());

		return model;
	}

	@RequestMapping(value = "/orderQuality/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditOrderQuality(@Valid @ModelAttribute("orderQuality") OrderQuality orderQuality,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pOQInvNo", required = false) String pOQInvNo,
			@RequestParam(value = "updateFlg", required = false) String updateFlg,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		List<FieldError> fieldErrors = result.getFieldErrors();
		
		for (FieldError fieldError : fieldErrors) {
			System.out.println("fieldError " + fieldError.getDefaultMessage());
		}
		
		if (result.hasErrors()) {
			return "orderQuality/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			orderQuality.setCreatedBy(principal.getName());
			orderQuality.setCreatedDt(new java.util.Date());
			orderQuality.setOrderMt(orderMtService.findByInvNoAndDeactive(pOQInvNo,false));

			retVal = "1";
		} else {
			orderQuality.setId(id);
			orderQuality.setModiBy(principal.getName());
			orderQuality.setModiDt(new java.util.Date());
			orderQuality.setOrderMt(orderMtService.findByInvNoAndDeactive(pOQInvNo,false));
			action = "updated";
			retVal = "1";
		}

		if (orderQuality.getShape().getId() == null) {
			orderQuality.setShape(null);
		}
		if (orderQuality.getQuality() == null || orderQuality.getQuality().getId() == null) {
			orderQuality.setQuality(null);
		}

		orderQualityService.save(orderQuality);
		
		
		if(updateFlg.equalsIgnoreCase("true")){
			
			OrderMt orderMt=orderMtService.findByInvNoAndDeactive(pOQInvNo,false);
			if(orderMt !=null){
				
				List<OrderDt>orderDts =orderDtService.findByOrderMtAndDeactive(orderMt, false);
				for(OrderDt orderDt :orderDts) {
					List<OrderStnDt>orderStnDts =orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
					
					for(OrderStnDt stnDt: orderStnDts){
						if(stnDt.getStoneType().getId().equals(orderQuality.getStoneType().getId()) && stnDt.getShape().getId().equals(orderQuality.getShape().getId())){
							stnDt.setQuality(orderQuality.getQuality());
							stnDt.setModiBy(principal.getName());
							stnDt.setModiDate(new java.util.Date());
							orderStnDtService.save(stnDt);
						}
						
					}
					
					orderDtService.updateQltyDesc(orderDt.getId());
					
				}
				
				
				
			
				
				retVal="1";
			}

			
		}
		
				
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
		
	}

	@RequestMapping("/orderQuality/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, Principal principal) {
		
		//model.addAttribute("canInsert", loginUser.getCanInsert());
		//model.addAttribute("canUpdate", loginUser.getCanUpdate());
		//model.addAttribute("canDelete", loginUser.getCanDelete());

		OrderQuality orderQuality = null;
		if (id != 0) {
			orderQuality = orderQualityService.findOne(id);
			model.addAttribute("orderQuality", orderQuality);

			model.addAttribute("qualityMap", qualityService.getQualityList(orderQuality.getShape().getId()));

			
		}
		
		model = populateModel(model);

		return "orderQuality/add";
	}

	@RequestMapping("/orderQuality/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		orderQualityService.delete(id);

		return "1";
	}

	@RequestMapping("/orderQuality/list")
	@ResponseBody
	public String qualityList(@RequestParam(value = "shapeId") Integer shapeId,
			@RequestParam(value = "qualityDropDownId") String qualityDropDownId) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> qualityMap = qualityService.getQualityList(shapeId);

		sb.append("<select id=\""+qualityDropDownId+"\" name=\""+qualityDropDownId+"\" class=\"form-control\">");
		sb.append("<option value=\"\"> Select Quality </option>");
		for (Object key : qualityMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(qualityMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}

package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;




@RequestMapping("/manufacturing/masters")
@Controller
public class OrderLabDtController {

	@Autowired
	private IOrderLabDtService orderLabDtService;
	
	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IMetalService metalService;
	

	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@ModelAttribute("orderLabDt")
	public OrderLabDt constructLabDt() {
		return new OrderLabDt();
	}
	
	
	
	
	@RequestMapping("/orderLabDt/listing")
	@ResponseBody
	public String orderLabDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "orderDtId", required = false) Integer orderDtId,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {
		
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("order");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
		
		

			StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("{\"total\":").append(orderLabDtService.count())
		.append(",\"rows\": [");
		
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		List<OrderLabDt> orderLabDts = orderLabDtService.findByOrderDtAndDeactive(orderDt, false);
		
		
		if(orderLabDts.size() > 0){
			for(OrderLabDt orderLabDt:orderLabDts){
				
			sb.append("{\"id\":\"")
				.append(orderLabDt.getId())
				.append("\",\"metal\":\"")
				.append(orderLabDt.getMetal().getName())
				.append("\",\"labourType\":\"")
				.append((orderLabDt.getLabourType() != null ? orderLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((orderLabDt.getLabourRate() != null ? orderLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((orderLabDt.getLabourValue() != null ? orderLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(orderLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(orderLabDt.getPcsWise())
				.append("\",\"perGram\":\"")
				.append(orderLabDt.getGramWise())
				.append("\",\"percent\":\"")
				.append(orderLabDt.getPercentWise())
				.append("\",\"perCarat\":\"")
				.append(orderLabDt.getPerCaratRate());
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				if(!canViewFlag){
				
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLock(")
				.append(orderLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
				
				sb.append("\",\"action1\":\"");
				
					sb.append("<a href='javascript:editOrderLabDt(")
					.append(orderLabDt.getId());	
				
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				
					sb.append("<a href='javascript:deleteOrderLabDt(event,")
					.append(orderLabDt.getId());
						
				
				sb.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(orderLabDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("")
					.append("\",\"actionLock\":\"")
					.append("");
				}
				sb.append("\"},");
				
			}
			else
			{
				if(!canViewFlag){
					
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doLabDtLockUnLock(")
					.append(orderLabDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
					
					sb.append("\",\"action1\":\"");
					if(roleRights.getCanEdit()){
						sb.append("<a href='javascript:editOrderLabDt(")
						.append(orderLabDt.getId());	
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

					sb.append("\",\"action2\":\"");
					if(roleRights.getCanDelete()){
						sb.append("<a href='javascript:deleteOrderLabDt(event,")
						.append(orderLabDt.getId());
							
					}
					sb.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(orderLabDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						.append("")
						.append("\",\"actionLock\":\"")
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
	
	
	
	@RequestMapping("/orderLabDt/add")
	public String add(Model model) {
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		return "orderLabDt/add";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/orderLabDt/add", method = RequestMethod.POST)
	public String addEditOrderLabDt(@Valid @ModelAttribute("orderLabDt") OrderLabDt orderLabDt,
			BindingResult bindingResult,
			@RequestParam(value="id")Integer id,
			@RequestParam(value="forLabOrderMtId")Integer orderMtId,
			@RequestParam(value="forLabOrderDtId")Integer orderDtId,
			RedirectAttributes redirectAttributes,Principal principal){
		
		String retVal = "";
		
		try {
			retVal = orderLabDtService.transactionalSave(orderLabDt, id, orderMtId, orderDtId, principal,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		
		return retVal;
	
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/orderLabDt/lockUnlock")
	public String lockUnlockLabCompDt(
			@RequestParam(value="labDtId")Integer labDtId){
		
		String retVal = "-1";
		OrderLabDt orderLabDt = orderLabDtService.findOne(labDtId);
		
			if(orderLabDt.getrLock() == true){
				orderLabDt.setrLock(false);
			}else{
				orderLabDt.setrLock(true);
			}
			
			orderLabDtService.save(orderLabDt);
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/orderLabDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "labId")Integer labId){
		
		String retVal = "";
		
		OrderLabDt orderLabDt = orderLabDtService.findOne(labId);
		if(orderLabDt.getrLock() == true){
			retVal = "-1";
		}
		return retVal;
	}
	
	
	
	@RequestMapping("/orderLabDt/edit/{id}")
	public String editOrderLabDt(@PathVariable int id,Model model){
		OrderLabDt orderLabDt = orderLabDtService.findOne(id);
		model.addAttribute("orderLabDt",orderLabDt);
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		return "orderLabDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/orderLabDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal){
		
		String retVal = "-1";
		OrderLabDt orderLabDt = orderLabDtService.findOne(id);
		try {
			orderLabDtService.transactionalDelete(orderLabDt, netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		return retVal;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/orderLabDt/LockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		OrderMt orderMt = orderMtService.findOne(mtId);
		List<OrderLabDt> orderLabDts = orderLabDtService.findByOrderMtAndDeactive(orderMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(OrderLabDt orderLabDt:orderLabDts){
			orderLabDt.setrLock(setValue);
			orderLabDtService.save(orderLabDt);
		}
		
		return retVal;
	}
	
	
	
	
	
}

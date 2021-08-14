package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MeltingRecDtController {
	
	@Autowired
	private IMeltingRecDtService meltingRecDtService;
	
	@Autowired
	private IMeltingIssDtService meltingIssDtService;
	
	@Autowired
	private IMeltingMtService meltingMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	

	@RequestMapping("/meltingRecDt")
	public String users(Model model) {
		return "meltingRecDt";
	}
	
	@RequestMapping("/meltingRecDt/listing")
	@ResponseBody
	public String meltingMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "recInvNo", required = false) String recInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,
			Principal principal) throws ParseException {

		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("meltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	
//		sb.append("{\"total\":").append(meltingRecDtService.count()).append(",\"rows\": [");
		
		String disable = "";
		if(canViewFlag){
			disable = "disabled = 'disabled'";
		}else{
			System.err.println("in else view");
		}
		
		MeltingMt meltingMt = meltingMtService.findByInvNoAndDeactive(recInvNo, false);
		
		List<MeltingRecDt> meltingRecDts = meltingRecDtService.findByMeltingMtAndDeactive(meltingMt,false );
		
		sb.append("{\"total\":").append(meltingRecDts.size()).append(",\"rows\": [");

		for (MeltingRecDt meltingRecDt : meltingRecDts) {	
			
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(meltingRecDt.getCreatedDt()));
				
			sb.append("{\"id\":\"")
					.append(meltingRecDt.getId())
					.append("\",\"purity\":\"")
					.append((meltingRecDt.getPurity() != null ? meltingRecDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((meltingRecDt.getColor() != null ? meltingRecDt.getColor().getName() : ""))
					.append("\",\"freshMetalWt\":\"")
					.append((meltingRecDt.getRecFreshMetalWt() != null ? meltingRecDt.getRecFreshMetalWt() : ""))
					.append("\",\"stone\":\"")
					.append(meltingRecDt.getRecStone())
					.append("\",\"carat\":\"")
					.append(meltingRecDt.getRecCarat())
					.append("\",\"netWt\":\"")
					.append(meltingRecDt.getRecNetWt())
					.append("\",\"expcPureWt\":\"")
					.append(meltingRecDt.getRecExcpPureWt())
					.append("\",\"deactive\":\"")
					.append(meltingRecDt.getDeactive() ? "Yes":"No")
					.append("\",\"location\":\"")
					.append((meltingRecDt.getDeptLocation() != null ? meltingRecDt.getDeptLocation().getName() : ""));
			
			if(!canViewFlag){
					sb.append("\",\"action1\":\"");
					if(roleRights.getCanEdit()){
						if(principal.getName().equalsIgnoreCase("Administrator")){
							sb.append("<a href='javascript:editMeltingRecDt(").append(meltingRecDt.getId());	
							sb.append(");' class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
						}else{
							if(currdate.equals(invDate)){
								sb.append("<a href='javascript:editMeltingRecDt(").append(meltingRecDt.getId());	
								sb.append(");' class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							}else{
								sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
								sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							}
						}
						
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						sb.append(");' class='btn btn-xs btn-warning' "+disable+"><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					}
					
					
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						
						if(principal.getName().equalsIgnoreCase("Administrator")){
							sb	.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingRecDt/delete/")
							.append(meltingRecDt.getId());
							
						}else{
							if(currdate.equals(invDate)){
								sb	.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/meltingRecDt/delete/")
								.append(meltingRecDt.getId());
							}else{
								sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
							}
						}
						
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
				
					sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(meltingRecDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			}else{
				sb.append("\",\"action1\":\"")
				.append("")
				.append("\",\"action2\":\"")
				.append("");
			}	
					sb.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	@RequestMapping("/meltingRecDt/add")
	public String add(Model model) {
		return "meltingRecDt/add";

	}
	
	
	@RequestMapping(value = "/meltingRecDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMeltingRecDt(@Valid @ModelAttribute("meltingRecDt") MeltingRecDt meltingRecDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "recInvNo") String recInvNo,
			@RequestParam(value = "recPNetWt") Double recPNetWt,
			@RequestParam(value = "recPExcpPureWt") Double recPExcpPureWt,
			@RequestParam(value = "pRecFMetalWt") Double pRecFMetalWt,
			@RequestParam(value = "vLoss") Double vLoss,
			@RequestParam(value = "pvRecTotalExpcPureWts") Double pRecTotalExpcPureWts,
			@RequestParam(value = "pMeltingBal") Double pMeltingBal,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		
		String retVal = "-1";
		

		if (result.hasErrors()) {
			return "meltingRecDt/add";
		}
		
		retVal =meltingRecDtService.meltingRecDtSave(meltingRecDt, id, recInvNo, recPNetWt, recPExcpPureWt, pRecFMetalWt, vLoss, pRecTotalExpcPureWts, principal,pMeltingBal);
		
		if(retVal.contains(",")){
		String xy[]=retVal.split(",");
		
		redirectAttributes.addAttribute("success",true);
		redirectAttributes.addAttribute("action",xy[1]);
		return xy[0];
		}else{
			return retVal;
		}
	}

	@RequestMapping("/meltingRecDt/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		MeltingRecDt meltingRecDt = meltingRecDtService.findOne(id);
		
		User user =userService.findByName(principal.getName());
		
		model.addAttribute("meltingRecDt", meltingRecDt);
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		
		model.addAttribute("locationMetalMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		
		return "meltingRecDt/add";
	}

	@RequestMapping("/meltingRecDt/delete/{id}")
	public String delete(@PathVariable int id) {
		meltingRecDtService.delete(id);
		MeltingRecDt meltingRecDt = meltingRecDtService.findOne(id);
		
		List<MetalTran> metalTrans = metalTranService.findByRefTranIdAndTranTypeAndDeactive(meltingRecDt.getId(), "MeltingReceive",false);
		for(MetalTran metalTran : metalTrans){
			metalTranService.delete(metalTran.getId());
		}
		
		return "redirect:/manufacturing/transactions/meltingMt/edit/"+meltingRecDt.getMeltingMt().getId()+".html";
	}
	
	
	//checkingIssEntry/forMeltingMt
	@RequestMapping("/checkingIssEntry/forMeltingMt")
	@ResponseBody
	public String stockCheck(
			@RequestParam(value = "MeltingId", required = false) Integer meltingId,
			@ModelAttribute("meltingRecDt") MeltingRecDt meltingRecDt) {
		
		StringBuilder sb = new StringBuilder();
		
		MeltingMt meltingMt = meltingMtService.findOne(meltingId);
		List<MeltingIssDt> meltingIssDt = meltingIssDtService.findByMeltingMtAndDeactive(meltingMt, false);
		
		if(meltingIssDt.size() == 0){
			sb.append("-1");
		}
		else{
			sb.append("1");
		}
		
		return sb.toString();
		
	}
	
	
	
	@RequestMapping("/rec/purityConversion")
	@ResponseBody
	public String recPurityConvFetch(
			@RequestParam(value = "PurityId") Integer PurityId) {

		StringBuilder sb = new StringBuilder();
		Purity purity = purityService.findOne(PurityId);

		double purityConv = 0.0;

		if (purity.getPurityConv() != null) {
			purityConv = purity.getPurityConv();
		}

		sb.append(purityConv);

		return sb.toString();
	}
	

}

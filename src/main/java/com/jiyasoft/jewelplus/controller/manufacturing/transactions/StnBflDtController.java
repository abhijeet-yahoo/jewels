package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnBflDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnBflMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class StnBflDtController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IStoneInwardDtService stnInwardDtService;
	
	
	@Autowired
	private IStoneInwardMtService stnInwardMtService;
	
	@Autowired
	private IStnBflDtService stnBflDtService;
	
	@Autowired
	private IStnBflMtService stnBflMtService;
	
	@Autowired
	private IStoneTranService  stoneTranService;
	
	
	
	@ModelAttribute("stnBflDt")
	public StnBflDt constructDt() {
		return new StnBflDt();
	}

	@RequestMapping("/stnBflDt")
	public String users(Model model, Principal principal) {
		User loginUser = userService.findByName(principal.getName());
		//model.addAttribute("canInsert", loginUser.getCanInsert());
		return "stnBflDt";
	}
	
	
	
	@RequestMapping("/stnBflDt/listing")
	@ResponseBody
	public String stoneInwardDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId)
			 {


		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(stnBflDtService.count())
				.append(",\"rows\": [");
		
		
		StnBflMt stnBflMt = stnBflMtService.findOne(mtId);
		
		List<StnBflDt> stnBflDts = stnBflDtService.findByStnBflMtAndDeactiveOrderByIdDesc(stnBflMt, false);
		
		
				for (StnBflDt stnBflDt : stnBflDts) {
					sb.append("{\"id\":\"")
							.append(stnBflDt.getId())
							.append("\",\"stone\":\"")
							.append(stnBflDt.getStone())
							.append("\",\"tranType\":\"")
							.append(stnBflDt.getTranType())
							.append("\",\"carat\":\"")
							.append(stnBflDt.getCarat())
							.append("\",\"inwardType\":\"")
							.append(stnBflDt.getStnInwardMt().getInwardType().getName())	
							.append("\",\"invNo\":\"")
							.append(stnBflDt.getStnInwardMt().getInvNo())	
							.append("\",\"stoneType\":\"")
							.append((stnBflDt.getStnInwardDt().getStoneType().getName()!= null ? stnBflDt.getStnInwardDt().getStoneType().getName() : ""))	
							.append("\",\"shape\":\"")
							.append((stnBflDt.getStnInwardDt().getShape().getName()!= null ? stnBflDt.getStnInwardDt().getShape().getName() : ""))	
							.append("\",\"subShape\":\"")
							.append((stnBflDt.getStnInwardDt().getSubShape()!= null ? stnBflDt.getStnInwardDt().getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((stnBflDt.getStnInwardDt().getQuality().getName()!= null ? stnBflDt.getStnInwardDt().getQuality().getName() : ""))	
							.append("\",\"mm\":\"")
							.append(stnBflDt.getStnInwardDt().getSize())	
							.append("\",\"sieve\":\"")
							.append(stnBflDt.getStnInwardDt().getSieve() )	
							.append("\",\"sizeGroup\":\"")
							.append((stnBflDt.getStnInwardDt().getSizeGroup().getName()!= null ? stnBflDt.getStnInwardDt().getSizeGroup().getName() : ""))	
							.append("\",\"rate\":\"")
							.append(stnBflDt.getStnInwardDt().getRate())	
							.append("\",\"action2\":\"")
      						.append("<a onClick='javascript:deleteDt(event,")
							.append(stnBflDt.getId())
							.append(");' class='btn btn-xs btn-danger triggerRemove")
							.append(stnBflDt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
					}
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	
	@RequestMapping("/stnBflDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		
		String retVal = "";
		synchronized (this) {
			retVal=stnBflDtService.deleteDt(id, principal);	
		}
		
		
		 
	/*	DecimalFormat df = new DecimalFormat("#.###");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		StnBflDt stnBflDt = stnBflDtService.findOne(id);
		
		Date cDate = stnBflDt.getCreatedDt();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);
		
		if(principal.getName().equalsIgnoreCase("super")){
						
			stnBflDtService.delete(id);
			
			Integer stoneTranId = null;
			List<StoneTran> stoneTran=stoneTranService.findByRefTranIdAndTranType(id, "Inward");
			for(StoneTran stnTran:stoneTran)
			{
				stoneTranId = stnTran.getId();
				stoneTranService.delete(stoneTranId);			
			}
			
		    String tranType = stnBflDt.getTranType();
			
		    StoneInwardDt stnInwardDt = stnInwardDtService.findOne(stnBflDt.getStnInwardDt().getId());
		     
		    if(tranType.equalsIgnoreCase("brokenType")){
		 
		    	stnInwardDt.setBrkCarat(Double.parseDouble(df.format(stnInwardDt.getBrkCarat() + stnBflDt.getCarat())));
		    	stnInwardDt.setBrkStone(stnInwardDt.getBrkStone() + stnBflDt.getStone());
		    	
		    }else{

		    	stnInwardDt.setFallCarat(Double.parseDouble(df.format(stnInwardDt.getFallCarat() + stnBflDt.getCarat())));
		    	stnInwardDt.setFallStone(stnInwardDt.getFallStone() + stnBflDt.getStone());
		    }

		    
		    if(stnBflDt.getLoss() == true){

		    	stnInwardDt.setLossCarat(Double.parseDouble(df.format(stnInwardDt.getLossCarat() - stnBflDt.getCarat())));
		    	stnInwardDt.setLossStone(stnInwardDt.getLossStone() - stnBflDt.getStone());
		    	
		    }else{
		    	
		    	
		    	
		    	stnInwardDt.setBalCarat(Double.parseDouble(df.format(stnInwardDt.getBalCarat() - stnBflDt.getCarat())));
		    	stnInwardDt.setBalStone(stnInwardDt.getBalStone() - stnBflDt.getStone());
		    	
		    }
		    
		    
		    stnBflDtService.delete(stnInwardDt, id);
			
			
			
		}else{
			
			if(dbDate.equals(curDate)){
	
				stnBflDtService.delete(id);
				
				Integer stoneTranId = null;
				List<StoneTran> stoneTran=stoneTranService.findByRefTranIdAndTranType(id, "Inward");
				for(StoneTran stnTran:stoneTran)
				{
					stoneTranId = stnTran.getId();
					stoneTranService.delete(stoneTranId);			
				}
				
			    String tranType = stnBflDt.getTranType();
				
			    StoneInwardDt stnInwardDt = stnInwardDtService.findOne(stnBflDt.getStnInwardDt().getId());
			     
			    if(tranType.equalsIgnoreCase("brokenType")){
			 
			    	stnInwardDt.setBrkCarat(Double.parseDouble(df.format(stnInwardDt.getBrkCarat() + stnBflDt.getCarat())));
			    	stnInwardDt.setBrkStone(stnInwardDt.getBrkStone() + stnBflDt.getStone());
			    	
			    }else{

			    	stnInwardDt.setFallCarat(Double.parseDouble(df.format(stnInwardDt.getFallCarat() + stnBflDt.getCarat())));
			    	stnInwardDt.setFallStone(stnInwardDt.getFallStone() + stnBflDt.getStone());
			    }

			    
			    if(stnBflDt.getLoss() == true){

			    	stnInwardDt.setLossCarat(Double.parseDouble(df.format(stnInwardDt.getLossCarat() - stnBflDt.getCarat())));
			    	stnInwardDt.setLossStone(stnInwardDt.getLossStone() - stnBflDt.getStone());
			    	
			    }else{
			    	
			    	
			    	
			    	stnInwardDt.setBalCarat(Double.parseDouble(df.format(stnInwardDt.getBalCarat() - stnBflDt.getCarat())));
			    	stnInwardDt.setBalStone(stnInwardDt.getBalStone() - stnBflDt.getStone());
			    	
			    }
			    
			    
			    stnBflDtService.delete(stnInwardDt, id);
				
				
				
			}
			else{
				
				retVal = "-2";
			}
			
			
		}
		
		*/



		return retVal;
	}
	
		
}

package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCost;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCostLabour;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignCostLabourService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignCostService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;



@Controller
@RequestMapping("/manufacturing/masters")
public class DesignCostController {
	
	
	@Autowired
	private IDesignCostService designCostService;
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IDesignComponentService designComponentService;
	
	@Autowired
	private IDesignCostLabourService designCostLabourService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IPartyService partyService;
	
	
	@ModelAttribute("designCost")
	public DesignCost construct(){
		return new DesignCost();
	}
	
	
	
	@RequestMapping("/designCost")
	public String designCost(Model model){
		model.addAttribute("purityMap", purityService.getPurityList());
		return "designCost";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/designCost/applyRate")
	public String applyRate(
			@ModelAttribute("designCost") DesignCost designCost,
			@RequestParam(value = "designId") Integer designId,
			@RequestParam(value = "goldRate") Double goldRate,
			@RequestParam(value = "purityId") Integer purityId,
			@RequestParam(value = "addedPerc") Double addedPerc,
			@RequestParam(value = "tagPerc") Double tagPerc,
			@RequestParam(value = "dispPerc") Double dispPerc,
			@RequestParam(value = "handlingPerc") Double handlingPerc,Principal principal){
	
		/*System.out.println("designId===="+designId);
		System.out.println("goldRate===="+goldRate);
		System.out.println("purityId===="+purityId);
		System.out.println("addedPerc===="+addedPerc);
		System.out.println("tagPerc===="+tagPerc);
		System.out.println("dispPerc===="+dispPerc);
		System.out.println("handlingPerc===="+handlingPerc);
		*/
		
		Design design = designService.findOne(designId);
		Party party = partyService.findByDefaultFlag(true);
		Purity purity = purityService.findOne(purityId);
		
		/*String retApp =*/ designCostService.applyRate(design, party, goldRate, addedPerc, tagPerc, dispPerc, handlingPerc,purity,principal);
		/*String retFob =*/ designCostService.updateFob(design,addedPerc,dispPerc);
		
		
		
		return "-1";
	}
	
	
	
	
	@RequestMapping("/designCost/popDetails")
	@ResponseBody
	public String popCostDetails(
			@RequestParam(value = "designId") Integer designId){
		
		String retVal = "";
		
		Double perGms 		= 0.0;
		Double waxWt 		= 0.0;
		Double netWt		= 0.0;
		Double metalValue 	= 0.0;
		Double stoneValue 	= 0.0;
		Double cfps 		= 0.0;
		Double setLabour 	= 0.0;
		Double handling 	= 0.0;
		Double rhodium 		= 0.0;
		Double loss 		= 0.0;
		Double other 		= 0.0;
		Double totLabour	= 0.0;
		Double finalCost	= 0.0;
		Double tag			= 0.0;
		
		
		DecimalFormat df = new DecimalFormat("#.##");
		DecimalFormat df1 = new DecimalFormat("#.###");
		
		//DecimalFormat df2 = new DecimalFormat("#.00");
		
		Design design = designService.findOne(designId);
		DesignCost designCost = designCostService.findByDesignAndDeactive(design, false);
		
		if(designCost != null){
			
			waxWt	= design.getWaxWt();
			/*netWt	= design.getGrms();*/
			
			perGms		= designCost.getPerGramRate();
			metalValue	= designCost.getMetalValue();
			stoneValue	= designCost.getStnValue();
			setLabour	= designCost.getSetValue();
			handling	= designCost.getHandlingValue();
			finalCost	= designCost.getFob();	
			tag			= Double.parseDouble(df.format(designCost.getFinalPrice()));
			
			
			
			//--------Design Stone ----------//
			
			List<DesignStone> designStones = designStoneService.findByDesign(design);
			
			
			
			//-----Design Component-----//--------//
			
			List<DesignComponent> designComponents = designComponentService.findByDesign(design);
			Double totCarat =0.0;
			for(DesignStone designStone:designStones){
				totCarat +=designStone.getCarat();
				
			}
			netWt=Double.parseDouble(df1.format(design.getGrms()- totCarat/5));
			
			
			Double totCompValue = 0.0;
			Double totCompWt=0.0;
	
			
			for(DesignComponent designComponent:designComponents){
				
				totCompValue += designComponent.getCompValue();
				totCompWt +=designComponent.getCompWt();
			}
				
				//-----Design Labour-----//--------//
			
			List<DesignCostLabour> designCostLabours = designCostLabourService.findByDesignAndDeactive(design, false);
			
			for(DesignCostLabour designCostLabour : designCostLabours){
				
				if(designCostLabour.getLabourType().getName().equalsIgnoreCase("cfps")){
					cfps = designCostLabour.getLabourValue();
				}
				
				if(designCostLabour.getLabourType().getName().equalsIgnoreCase("RHOD")){
					rhodium = designCostLabour.getLabourValue();
				}
				
				if(designCostLabour.getLabourType().getName().equalsIgnoreCase("loss")){
					loss = designCostLabour.getLabourValue();
				}
				
				if(designCostLabour.getLabourType().getName().equalsIgnoreCase("oths")){
					other = designCostLabour.getLabourValue();
				}
				
				
			}
			
			
			
			totLabour = Double.parseDouble(df.format(cfps+setLabour+handling+rhodium+loss+other));
		
			
			retVal =  perGms+"_"+waxWt+"_"+netWt+"_"+metalValue+"_"+stoneValue+"_"+cfps+"_"+
						setLabour+"_"+handling+"_"+rhodium+"_"+loss+"_"+other+"_"+totLabour+"_"+finalCost+"_"+tag+"_"+totCompValue+"_"+totCompWt;
			
			
		}else{
			 retVal = "-2";
		}
		
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/designCost/editApplyRateDetails")
	@ResponseBody
	public String editApplyRateDetails(
			@RequestParam(value = "designId") Integer designId){
		

		String retVal = "-2";
		
		Design design = designService.findOne(designId);
		DesignCost designCost = designCostService.findByDesignAndDeactive(design, false);
		
		if(designCost != null){
			
			retVal = designCost.getPurity().getId()+"_"+designCost.getMetalRate()+"_"+designCost.getAddedPerc()+"_"+designCost.getTagPerc()
					+"_"+designCost.getDispPerc()+"_"+designCost.getHandlingPerc();
		}
		
		
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

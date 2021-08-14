package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostStnDtItem;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostStnDtItemController {
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;

	@Autowired
	private ICostStnDtItemService costStnDtItemService;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@RequestMapping("/costStnDtItem/listing")
	@ResponseBody
	public String costStnListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costDtId", required = false) Integer costDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		
		CostingDtItem costingDtItem = costingDtItemService.findOne(costDtId);
		List<CostStnDtItem> costStnDtItems = costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
		
		sb.append("{\"total\":").append(costStnDtItems.size()).append(",\"rows\": [");
		
		
		
		if(costStnDtItems.size() > 0){
			for(CostStnDtItem costStnDtItem : costStnDtItems){
				
			sb.append("{\"id\":\"")
				.append(costStnDtItem.getId())
				.append("\",\"stoneType\":\"")
				.append((costStnDtItem.getStoneType() != null ? costStnDtItem.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((costStnDtItem.getPartNm() != null ? costStnDtItem.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((costStnDtItem.getShape() != null ? costStnDtItem.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((costStnDtItem.getQuality() != null ? costStnDtItem.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((costStnDtItem.getSize() != null ? costStnDtItem.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((costStnDtItem.getSieve() != null ? costStnDtItem.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((costStnDtItem.getSizeGroup() != null ? costStnDtItem.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((costStnDtItem.getStone() != null ? costStnDtItem.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((costStnDtItem.getCarat() != null ? costStnDtItem.getCarat() : ""))
				.append("\",\"tagWt\":\"")
				.append((costStnDtItem.getTagWt() != null ? costStnDtItem.getTagWt() : ""))
				.append("\",\"perPcStone\":\"")
				.append((costStnDtItem.getPerStonePcs() != null ? costStnDtItem.getPerStonePcs() : ""))
				.append("\",\"perPcCarat\":\"")
				.append((costStnDtItem.getPerStoneWt() != null ? costStnDtItem.getPerStoneWt() : ""))
				.append("\",\"rate\":\"")
				.append((costStnDtItem.getStnRate() != null ? costStnDtItem.getStnRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((costStnDtItem.getStoneValue() != null ? costStnDtItem.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((costStnDtItem.getHandlingRate() != null ? costStnDtItem.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((costStnDtItem.getHandlingValue() != null ? costStnDtItem.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((costStnDtItem.getSetting() != null ? costStnDtItem.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((costStnDtItem.getSettingType() != null ? costStnDtItem.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((costStnDtItem.getSetRate() != null ? costStnDtItem.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((costStnDtItem.getSetValue() != null ? costStnDtItem.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(costStnDtItem.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((costStnDtItem.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLockItem(")
				.append(costStnDtItem.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostStnDtItem(")
				.append(costStnDtItem.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostStnDtItem(event,")
				.append(costStnDtItem.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(costStnDtItem.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\",\"actStone\":\"")
				.append((costStnDtItem.getActStone() != null ? costStnDtItem.getActStone() : ""))
				.append("\",\"actCarat\":\"")
				.append((costStnDtItem.getActCarat() != null ? costStnDtItem.getActCarat() : ""))
				.append("\"},");
				
			}
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	@RequestMapping("/costStnDtItem/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "stnId")Integer stnId){
		
		String retVal = "sucess";
		CostStnDtItem costStnDtItem = costStnDtItemService.findOne(stnId);
		
		if(costStnDtItem.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
		
		if(costStnDtItem.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/costStnDtItem/edit/{id}")
	public String editcostStnDtt(@PathVariable int id,Model model){
		
		CostStnDtItem costStnDtItem = costStnDtItemService.findOne(id);
		model.addAttribute("costStnDtItem", costStnDtItem);
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		return "costStnDtItem/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/costStnDtItem/saveEdit")
	public String addEditCostStnDt(
			@RequestParam(value="costStnDtId") Integer costStnDtId,
			@RequestParam(value="settingId") Integer settingId,
			@RequestParam(value="setTypeId") Integer setTypeId,
			@RequestParam(value="stoneRate") Double stoneRate,
			@RequestParam(value="handRate") Double handRate,
			@RequestParam(value="setRate") Double setRate,Principal principal){
		
		
		System.out.println("in Controller   ");
		
		String retVal = "-1";
		
		Setting setting = settingService.findOne(settingId);
		SettingType settingType = settingTypeService.findOne(setTypeId);
		
		CostStnDtItem costStnDtItem = costStnDtItemService.findOne(costStnDtId);
		costStnDtItem.setSetting(setting);
		costStnDtItem.setSettingType(settingType);
		costStnDtItem.setStnRate(stoneRate);
		costStnDtItem.setHandlingRate(handRate);
		costStnDtItem.setSetRate(setRate);
	//	costStnDtItem.setCenterStone(Boolean.parseBoolean(centerStone) );
		costStnDtItem.setModiBy(principal.getName());
		costStnDtItem.setModiDate(new java.util.Date());
		retVal = "-2";
		costStnDtItemService.save(costStnDtItem);
		
	//	List<CostStnDtItem> costStnDtItems = costStnDtItemService.findByCostingDtItemAndDeactive(costStnDtItem.getCostingDtItem(), false);
	//	costingDtItemService.applyItemStoneRate(costStnDtItems);
	//	costingDtItemService.applyItemLabRate(costStnDtItem.getCostingDtItem(), principal);
		costingDtItemService.updateItemFob(costStnDtItem.getCostingDtItem(),netWtWithCompFlg);

		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/costStnDtItem/delete/{id}")
	public String delete(@PathVariable int id, Principal principal ){
		
		CostStnDtItem costStnDtItem = costStnDtItemService.findOne(id);
		
		String retVal = "-1";
		
		if(costStnDtItem.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
			costStnDtItemService.delete(id);
	//		costingDtService.updateFob(costStnDt.getCostingDt());
			
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/costStnDtItem/lockUnlock")
	public String lockUnlockCostStnDt(
			@RequestParam(value="stnDtId")Integer stnDtId){
		
		String retVal = "-1";
		CostStnDtItem costStnDtItem = costStnDtItemService.findOne(stnDtId);
		
		
		if(costStnDtItem.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
		
			if(costStnDtItem.getrLock() == true){
				costStnDtItem.setrLock(false);
			}else{
				costStnDtItem.setrLock(true);
			}
			
			costStnDtItemService.save(costStnDtItem);
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/costStnDtItem/rateFromMaster")
	public String calSetRate(
			@RequestParam(value="setItemId") Integer setItemId,
			@RequestParam(value="setTypeItemId") Integer setTypeItemId,
			@RequestParam(value="costStnDtItemId") Integer costStnDtItemId){
		
		
		String retVal = "0.0";
		
		Setting 	setting 	=  settingService.findOne(setItemId);
		SettingType settingType =  settingTypeService.findOne(setTypeItemId);
		CostStnDtItem 	costStnDtItem	=  costStnDtItemService.findOne(costStnDtItemId);
		
		costStnDtItem.setSetting(setting);
		costStnDtItem.setSettingType(settingType);
		
		costStnDtItem=costingDtItemService.applySettingRate(costStnDtItem);
		
		
		
		retVal=costStnDtItem.getSetRate().toString();
		
		
		return retVal;
		
		
	}
	
	
	@RequestMapping("/costingStnDtItem/exportQualityListing")
	@ResponseBody
	public String reportListing(@RequestParam(value = "mtIds", required = false) Integer mtIds){
		
		
		StringBuilder sb = new StringBuilder();
		
		QCostStnDtItem qCostStnDtItem = QCostStnDtItem.costStnDtItem;
		JPAQuery query = new JPAQuery(entityManager);
		List<com.mysema.query.Tuple> coststnDetails = null;
			

		coststnDetails = query.from(qCostStnDtItem)
				.where(qCostStnDtItem.deactive.eq(false).and(qCostStnDtItem.costingMt.id.eq(mtIds))).groupBy(qCostStnDtItem.quality)
				.orderBy(qCostStnDtItem.quality.name.asc()).list(qCostStnDtItem.quality.id, qCostStnDtItem.quality.name);
		 
		 
		sb.append("{\"total\":").append(coststnDetails.size()).append(",\"rows\": [");
				
			for (com.mysema.query.Tuple row:coststnDetails) {
				
					sb.append("{\"id\":\"")
						.append(row.get(qCostStnDtItem.quality.id))
						.append("\",\"name\":\"")
						.append(row.get(qCostStnDtItem.quality.name) !=null ? row.get(qCostStnDtItem.quality.name) :"")
						.append("\"},");
					
				
			}
			
				
				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				return str;			
	}
	
	
	@RequestMapping("/costingStnDtItem/exportSizegroupListing")
	@ResponseBody
	public String sizegroupReportListing(@RequestParam(value = "mtIds", required = false) Integer mtIds){
		
		
		StringBuilder sb = new StringBuilder();
		
		QCostStnDtItem qCostStnDtItem = QCostStnDtItem.costStnDtItem;
		JPAQuery query = new JPAQuery(entityManager);
		List<com.mysema.query.Tuple> coststnDetails = null;
			

		coststnDetails = query.from(qCostStnDtItem)
				.where(qCostStnDtItem.deactive.eq(false).and(qCostStnDtItem.costingMt.id.eq(mtIds))).groupBy(qCostStnDtItem.sizeGroup)
				.orderBy(qCostStnDtItem.sizeGroup.name.asc()).list(qCostStnDtItem.sizeGroup.id, qCostStnDtItem.sizeGroup.name);
		 
		 
		sb.append("{\"total\":").append(coststnDetails.size()).append(",\"rows\": [");
				
			for (com.mysema.query.Tuple row:coststnDetails) {
				
					sb.append("{\"id\":\"")
						.append(row.get(qCostStnDtItem.sizeGroup.id))
						.append("\",\"name\":\"")
						.append(row.get(qCostStnDtItem.sizeGroup.name) !=null ? row.get(qCostStnDtItem.sizeGroup.name) :"")
						.append("\"},");
					
				
			}
			
				
				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				return str;			
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/costStnDtItem/handlingRateFromMaster")
	public String handlingRateFromMaster(
			@RequestParam(value="stnRate") Double stnRate,
			@RequestParam(value="costStnDtItemId") Integer costStnDtItemId){
		
		
		String retVal = "0.0";
		
		
		CostStnDtItem 	costStnDtItem	=  costStnDtItemService.findOne(costStnDtItemId);
		
		costStnDtItem.setStnRate(stnRate);
				
		costStnDtItem=costingDtItemService.applyHandlingRate(costStnDtItem);
		
		
		
		retVal=costStnDtItem.getHandlingRate().toString();
		
		
		return retVal;
		
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costStnDtItem/manualTagUpdate")
	public String manualTagUpdate(@RequestParam(value="costDtItemId") Integer costDtItemId,
			@RequestParam(value="vTagWt") Double vTagWt,Principal principal){
		
		String retVal=costStnDtItemService.updateManualTagWt(costDtItemId, vTagWt,principal,netWtWithCompFlg);
		
		
		return retVal;
		
	}
	
	
}

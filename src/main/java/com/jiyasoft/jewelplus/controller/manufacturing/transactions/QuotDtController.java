package com.jiyasoft.jewelplus.controller.manufacturing.transactions;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.text.ParseException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotStnDtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class QuotDtController {
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	IQuotMetalService quotMetalService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IQuotStnDtService quotStnDtService;
	
	@Autowired
	private IQuotCompDtService quotCompDtService;
	
	@Autowired
	private IQuotLabDtService quotLabDtService;
	
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IProductSizeService productSizeService;
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;
	
	
	@ModelAttribute("quotDt")
	public QuotDt constructDt() {
		return new QuotDt();
	}
	
	@RequestMapping("/quotDt/listing")
	@ResponseBody
	public String quotDtListing(Model model, 
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId, Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {
		
		
		

		StringBuilder sb = new StringBuilder();
		Page<QuotDt> quotDts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}


		quotDts=quotDtService.searchAll(limit, offset, sort, order, search, mtId);

		
		
		sb.append("{\"total\":").append(quotDts.getTotalElements())
		.append(",\"rows\": [");
		for(QuotDt quotDt:quotDts){
			
			List<QuotMetal>quotMetals=quotMetalService.findByQuotDtAndDeactive(quotDt, false);
			String purityVal="";
			for(QuotMetal quotMetal :quotMetals) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+quotMetal.getPurity().getName()+"-"+quotMetal.getColor().getName();
				 }else {
					 purityVal=quotMetal.getPurity().getName()+"-"+quotMetal.getColor().getName();
				 }
			}
			
				
			sb.append("{\"id\":\"")
				.append(quotDt.getId())
				.append("\",\"barcode\":\"")
				.append(quotDt.getBarcode() !=null? quotDt.getBarcode() :"")
				.append("\",\"style\":\"")
				.append((quotDt.getDesign() != null ? quotDt.getDesign().getMainStyleNo(): ""))
				.append("\",\"defImage\":\"")
				.append((quotDt.getDesign() != null ? quotDt.getDesign().getDefaultImage() : "blank.png"))
				.append("\",\"pcs\":\"")
				.append((quotDt.getPcs() != null ? quotDt.getPcs() : ""))
				.append("\",\"ktCol\":\"")
				.append(purityVal)
				.append("\",\"grossWt\":\"")
				.append((quotDt.getGrossWt() != null ? quotDt.getGrossWt() : ""))
				.append("\",\"netWt\":\"")
				.append((quotDt.getNetWt() != null ? quotDt.getNetWt() : ""))
				.append("\",\"lossPercDt\":\"")
				.append((quotDt.getLossPercDt() != null ? quotDt.getLossPercDt() : ""))
				.append("\",\"metalRate\":\"")
				.append((quotDt.getMetalRate() != null ? quotDt.getMetalRate() : ""))
				.append("\",\"metalValue\":\"")
				.append((quotDt.getMetalValue() != null ? quotDt.getMetalValue() : ""))
				.append("\",\"stoneValue\":\"")
				.append((quotDt.getStoneValue() != null ? quotDt.getStoneValue() : ""))
				.append("\",\"compValue\":\"")
				.append((quotDt.getCompValue() != null ? quotDt.getCompValue() : ""))
				.append("\",\"labourValue\":\"")
				.append((quotDt.getLabValue() != null ? quotDt.getLabValue() : ""))
				.append("\",\"setValue\":\"")
				.append((quotDt.getSetValue() != null ? quotDt.getSetValue() : ""))
				.append("\",\"handlingCost\":\"")
				.append((quotDt.getHdlgValue() != null ? quotDt.getHdlgValue() : ""))
				.append("\",\"fob\":\"")
				.append((quotDt.getFob() != null ? quotDt.getFob() : ""))
				.append("\",\"perPcFinalPrice\":\"")
				.append((quotDt.getPerPcFinalPrice() != null ? quotDt.getPerPcFinalPrice() : ""))
				.append("\",\"finalPrice\":\"")
				.append((quotDt.getFinalPrice() != null ? quotDt.getFinalPrice() : ""))
				.append("\",\"discAmount\":\"")
				.append((quotDt.getDiscAmount() != null ? quotDt.getDiscAmount() : ""))
				.append("\",\"image\":\"")
				.append(quotDt.getDesign().getDefaultImage() != null ? quotDt.getDesign().getDefaultImage() :"blank.png")
				.append("\",\"rLock\":\"")
				.append(quotDt.getrLock()); //1 = lock & 0 = unlock
			
			
		
			if(canViewFlag !=null && !canViewFlag ){
				
			sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doQuotDtLockUnLock(")
				.append(quotDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editQuotDt(")
				.append(quotDt.getId())
				.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteQuotDt(event,")
				.append(quotDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(quotDt.getId())
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
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/quotDt/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("quotDt") QuotDt quotDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value="vQuotMtId" , required = true) Integer quotMtIdPk,
			@RequestParam(value="metalDtData" , required = true) String metalDtData) {
		
		String retVal = "-1";
		
		if (result.hasErrors()) {
			return "quotDt/add";
		}
		
		
		try {
			retVal = quotDtService.transactionalSave(quotDt, id, quotMtIdPk, metalDtData,principal,netWtWithCompFlg);
		} catch (Exception e) {
			retVal = "Error:Contact Support";
			e.printStackTrace();
		}
		
				
		
		return retVal;

	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/quotDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		QuotDt quotDt = quotDtService.findOne(id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalQuotDtId", quotDt.getId());
		jsonObject.put("design\\.mainStyleNo", quotDt.getDesign().getMainStyleNo());
		jsonObject.put("pcs", quotDt.getPcs() != null ? quotDt.getPcs() : "0");
		jsonObject.put("productSize\\.id", quotDt.getProductSize() != null ? quotDt.getProductSize().getId() : null);
		jsonObject.put("purity\\.id", quotDt.getPurity() != null ? quotDt.getPurity().getId() : null);
		jsonObject.put("color\\.id", quotDt.getColor() != null ? quotDt.getColor().getId() : null);
		jsonObject.put("refNo", quotDt.getRefNo() != null ? quotDt.getRefNo() : "");
		jsonObject.put("stampInst", quotDt.getStampInst() != null ? quotDt.getStampInst() : "");
		jsonObject.put("remark", quotDt.getRemark() != null ? quotDt.getRemark() : "");
		jsonObject.put("designRemark", quotDt.getDesignRemark() != null ? quotDt.getDesignRemark() : "");
		jsonObject.put("discAmount", quotDt.getDiscAmount() !=null ? quotDt.getDiscAmount() : "0.0");
		jsonObject.put("grossWt", quotDt.getGrossWt() !=null ? quotDt.getGrossWt() : "0.0");
		jsonObject.put("barcode", quotDt.getBarcode() != null ? quotDt.getBarcode() : "");
		
		

		return jsonObject.toString();
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/quotDt/lockUnlock")
	public String lockUnlockCostDt(
			@RequestParam(value="dtId")Integer dtId){
		
		String retVal = "-1";
		QuotDt quotDt = quotDtService.findOne(dtId);
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
		List<QuotLabDt> quotLabDts = quotLabDtService.findByQuotDtAndDeactive(quotDt, false);
		
		
		
			if(quotDt.getrLock() == true){
				quotDt.setrLock(false);
				
				for(QuotStnDt quotStnDt : quotStnDts){
					quotStnDt.setrLock(false);
					quotStnDtService.save(quotStnDt);
				}
				
				for(QuotCompDt quotCompDt : quotCompDts){
					quotCompDt.setrLock(false);
					quotCompDtService.save(quotCompDt);
				}
				
				for(QuotLabDt quotLabDt : quotLabDts){
					quotLabDt.setrLock(false);
					quotLabDtService.save(quotLabDt);
				}
				
			}else{
				quotDt.setrLock(true);
				
				for(QuotStnDt quotStnDt : quotStnDts){
					quotStnDt.setrLock(true);
					quotStnDtService.save(quotStnDt);
				}
				
				for(QuotCompDt quotCompDt : quotCompDts){
					quotCompDt.setrLock(true);
					quotCompDtService.save(quotCompDt);
				}
				
				for(QuotLabDt quotLabDt : quotLabDts){
					quotLabDt.setrLock(true);
					quotLabDtService.save(quotLabDt);
				}
			
				
			}
	
		quotDtService.save(quotDt);
		
		
		

		
		return retVal;
	}
	
	
	@RequestMapping("/quotDt/validation")
	@ResponseBody
	public String validation(
			@RequestParam(value = "dtId")Integer dtId){
		
		String retVal = "";
		
		QuotDt quotDt = quotDtService.findOne(dtId);
		if(quotDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/quotDt/getData/{dtId}")
	public String getQuotData(@PathVariable int dtId){
		
		
		QuotDt quotDt = quotDtService.findOne(dtId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("grossWt", quotDt.getGrossWt());
		jsonObject.put("netWt", quotDt.getNetWt());
		jsonObject.put("metalValue", quotDt.getMetalValue());
		jsonObject.put("stoneValue", quotDt.getStoneValue());
		jsonObject.put("compValue", quotDt.getCompValue());
		jsonObject.put("labourValue", quotDt.getLabValue());
		jsonObject.put("setValue", quotDt.getSetValue());
		jsonObject.put("handlingCost", quotDt.getHdlgValue());
		jsonObject.put("fob", quotDt.getFob());
		jsonObject.put("other", quotDt.getOther());
		jsonObject.put("finalPrice", quotDt.getFinalPrice());
		jsonObject.put("discPerc", quotDt.getDiscPercent());
		jsonObject.put("discAmount", quotDt.getDiscAmount());
		jsonObject.put("netAmount", quotDt.getNetAmount());
		
		return jsonObject.toString();
		
		
	}
	
	
	@ResponseBody
	@RequestMapping("/quotDt/delete/{id}")
	public String delete(@PathVariable int id){
		quotDtService.delete(id);
		
		QuotDt quotDt = quotDtService.findOne(id);
		
		List<QuotMetal>quotMetals =quotMetalService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotMetal quotMetal :quotMetals){
			quotMetalService.delete(quotMetal.getId());
		}
		
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotStnDt quotStnDt:quotStnDts){
			quotStnDtService.delete(quotStnDt.getId());
		}
		
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotCompDt quotCompDt:quotCompDts){
			quotCompDtService.delete(quotCompDt.getId());
		}
		
		List<QuotLabDt> quotLabDts = quotLabDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotLabDt quotLabDt:quotLabDts){
			quotLabDtService.delete(quotLabDt.getId());
		}
		
		
		return "-1";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/quotDt/dtLockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		QuotMt quotMt = quotMtService.findOne(mtId);
		List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotMtAndDeactive(quotMt, false);
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotMtAndDeactive(quotMt, false);
		List<QuotLabDt> quotLabDts = quotLabDtService.findByQuotMtAndDeactive(quotMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		
		quotMt.setrLock(setValue);
		quotMtService.save(quotMt);
		
		
		for(QuotDt quotDt:quotDts){
			quotDt.setrLock(setValue);
			quotDtService.save(quotDt);
		}
		
		for(QuotStnDt quotStnDt:quotStnDts){
			quotStnDt.setrLock(setValue);
			quotStnDtService.save(quotStnDt);
		}
		

		for(QuotCompDt quotCompDt:quotCompDts){
			quotCompDt.setrLock(setValue);
			quotCompDtService.save(quotCompDt);
		}
		
		
		for(QuotLabDt quotLabDt:quotLabDts){
			quotLabDt.setrLock(setValue);
			quotLabDtService.save(quotLabDt);
		}
		

		
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//------------popDesignDetails--------//---------//
	
	

	
	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left, Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};
	
	
	
	@ResponseBody
	@RequestMapping("/quotDt/designAllDetails")
	public String getDeignGrossWt(
			@RequestParam(value="purityId") Integer purityId,
			@RequestParam(value="mainStyleNo") String mainStyleNo){
		
		
		
		String retVal = "-1";
		
		String tempSize ;
		String tempRemark;
		String tempProductSizeId;
		
		Design design = designService.findByMainStyleNoAndDeactive(mainStyleNo, false);
		
		
		
		
		
		
//--------------------------------Size--------------------------------//
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> productSizeMap = productSizeService.getProductSizeList(design.getCategory().getId());
		
		List<Map.Entry<Integer, String>> productGroupMapGv = Lists.newArrayList(productSizeMap.entrySet());
	    Collections.sort(productGroupMapGv, byMapValues);

	    sb.append("<select id=\"productSize.id\" name=\"productSize.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Size -</option>");

		Iterator<Entry<Integer, String>> iterator = productGroupMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		
		sb.append("</select>");
		
		tempSize = sb.toString();
		
		
		//-----Remark-----//
		
		tempRemark = getDesignRemarks(design.getRemarks());
		
				
		//-----ProductSizeId-----//
		
		tempProductSizeId = (design.getProductSize() != null ? design.getProductSize().getId() : "")+"";
		
		
		retVal = tempSize+"_"+tempRemark+"_"+tempProductSizeId;
		
		return retVal;
		
	}
	
	
	private String getDesignRemarks(String designRemarks) {
		String tmpRemarks = designRemarks;

		String ttStr = "";

		if (tmpRemarks != null) {
			tmpRemarks = tmpRemarks.trim();

			for (int x=0; x<tmpRemarks.length(); x++) {
				if (((int) tmpRemarks.charAt(x)) == 0) {
					continue;
				} else {
					ttStr += tmpRemarks.substring(x, (x+1));
				}
			}
			
			tmpRemarks = ttStr;
			ttStr = "";
			for (int x=0; x<tmpRemarks.length(); x++) {
				if (tmpRemarks.charAt(x) == ' ') {
					if (((x+1) < tmpRemarks.length()) && tmpRemarks.charAt(x+1) == ' ') {
						continue;
					} else {
						ttStr += tmpRemarks.substring(x, (x+1));					
					}
				} else {
					ttStr += tmpRemarks.substring(x, (x+1));
				}
			}
		}

		return ttStr;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/quotDt/getSize")
	public String sizeMast(
			@RequestParam(value="quotDtId") int quotDtId){
			
		
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		Design design = designService.findOne(quotDt.getDesign().getId());
		
		//-----Size-------//
		
		String tempSize = "";
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> productSizeMap = productSizeService.getProductSizeList(design.getCategory().getId());
		
		List<Map.Entry<Integer, String>> productGroupMapGv = Lists.newArrayList(productSizeMap.entrySet());
	    Collections.sort(productGroupMapGv, byMapValues);

	    sb.append("<select id=\"productSize.id\" name=\"productSize.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Size -</option>");

		Iterator<Entry<Integer, String>> iterator = productGroupMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
				.append(et.getValue()).append("</option>");
		}
		
		sb.append("</select>");
		
		tempSize = sb.toString();
		
		return tempSize;
		
	}
	
	
	
	@RequestMapping("/quotDt/clientStyleListing")
	@ResponseBody
	public String clientStyleListing(Model model, 
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "partyId", required = false) Integer partyId, Principal principal) {
		
		Party party =partyService.findOne(partyId);
		
		QuotMt quotMt =quotMtService.findByPartyAndMasterFlgAndDeactive(party, true,false);
		
		StringBuilder sb = new StringBuilder();
		List<QuotDt> quotDts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}


		/*
		 * quotDts=quotDtService.searchAll(limit, offset, sort, order, search,
		 * quotMt.getId());
		 */
		
		quotDts=quotDtService.findByQuotMtAndDeactive(quotMt, false);

		
		
		sb.append("{\"total\":").append(quotDts.size())
		.append(",\"rows\": [");
		for(QuotDt quotDt:quotDts){
			
			List<QuotMetal>quotMetals=quotMetalService.findByQuotDtAndDeactive(quotDt, false);
			String purityVal="";
			for(QuotMetal quotMetal :quotMetals) {
				 if(purityVal.length()>0) {
					 purityVal=purityVal+","+quotMetal.getPurity().getName()+"-"+quotMetal.getColor().getName();
				 }else {
					 purityVal=quotMetal.getPurity().getName()+"-"+quotMetal.getColor().getName();
				 }
			}
			
			
			List<QuotStnDt>quotStnDts =quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
			
			TreeSet<String>qltySet = new TreeSet<String>();
			TreeSet<String>settVal = new TreeSet<String>();
			
			
			for(QuotStnDt quotStnDt :quotStnDts) {
				
				qltySet.add(quotStnDt.getQuality() !=null?quotStnDt.getQuality().getName():"");
				settVal.add((quotStnDt.getSetting() !=null?quotStnDt.getSetting().getCode()+"-":"")+(quotStnDt.getSettingType() !=null?quotStnDt.getSettingType().getCode():""));
				 
			}
			
			
				
			sb.append("{\"id\":\"")
				.append(quotDt.getId())
				.append("\",\"barcode\":\"")
				.append(quotDt.getBarcode() !=null? quotDt.getBarcode() :"")
				.append("\",\"style\":\"")
				.append((quotDt.getDesign() != null ? quotDt.getDesign().getMainStyleNo(): ""))
				.append("\",\"defImage\":\"")
				.append((quotDt.getDesign() != null ? quotDt.getDesign().getDefaultImage() : "blank.png"))
				.append("\",\"pcs\":\"")
				.append((quotDt.getPcs() != null ? quotDt.getPcs() : ""))
				.append("\",\"ktCol\":\"")
				.append(purityVal)
				.append("\",\"trfQty\":\"")
				.append("")
				.append("\",\"qltyDesc\":\"")
				.append(qltySet)
				.append("\",\"settDesc\":\"")
				.append(settVal)
				.append("\",\"grossWt\":\"")
				.append((quotDt.getGrossWt() != null ? quotDt.getGrossWt() : ""))
				.append("\",\"netWt\":\"")
				.append((quotDt.getNetWt() != null ? quotDt.getNetWt() : ""))
				.append("\",\"image\":\"")
				.append(quotDt.getDesign().getDefaultImage() != null ? quotDt.getDesign().getDefaultImage() :"blank.png")
				.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	

	@RequestMapping(value = "/quotDt/excelUpload", method = RequestMethod.POST)
	public String excelUpload(Model model, @RequestParam("excelfile") MultipartFile excelfile, HttpSession session,
			@RequestParam("tempFileName") String tempExcelFile,@RequestParam(value="quotMtPkId",required = false)Integer quotMtPkId, RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		
		String retVal = "yes";
		
		synchronized (this) {
			
			retVal = quotDtService.quotDtExcelUpload(excelfile, session, tempExcelFile,principal,quotMtPkId,netWtWithCompFlg);
		}
		
		model.addAttribute("tableDisp", "yes");
		model.addAttribute("retVal", retVal);
		model.addAttribute("quotMtPkId", quotMtPkId);
		
		return "uploadExcelQuotDtStyle";
	}
	
	
	@RequestMapping("/quotDtExcel/tableListing")
	@ResponseBody
	public String displaySessionTableListing(HttpSession httpSession){
		
		@SuppressWarnings("unchecked")
		List<OrderExcel> orderExcels = (List<OrderExcel>) httpSession.getAttribute("qoutDtExcelSessionList");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(orderExcels.size()).append(",\"rows\": [");
		
		for(OrderExcel orderExcel:orderExcels){
			
			sb.append("{\"styleNo\":\"")
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
	
	//--------quot Dt excel file download----//
	
	@RequestMapping("/quotDt/downloadExcel/format")
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

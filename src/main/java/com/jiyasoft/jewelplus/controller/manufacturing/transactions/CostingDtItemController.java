package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingDtItem;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostingDtItemController {
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	

	@Autowired
	private ICostingMtService costingtMtService;

	@Autowired
	private ICostingDtItemService costingDtItemService;

	@Autowired
	private IPartyService partyService;

	@Autowired
	private ICostStnDtItemService costStnDtItemService;

	@Autowired
	private ICostCompDtItemService costCompDtItemService;

	@Autowired
	private ICostLabDtItemService costLabDtItemService;

	@Autowired
	private EntityManager entityManager;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;

	@RequestMapping("/costingDtItem/reportListing")
	@ResponseBody
	public String reportListing(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtIds", required = false) String mtIds,
			@RequestParam(value = "setNoId", required = false) Integer setNoId) {

		return costingDtItemService.costDtItemReportListing(limit, offset, sort, order, search, mtIds, setNoId);
	
	}

	@RequestMapping("/costingDtItem/listing")
	@ResponseBody
	public String costingDtItemListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

	return costingDtItemService.costDtItemListing(limit, offset, sort, order, search, pInvNo);

	}

	@RequestMapping("/costingDtItem/validation")
	@ResponseBody
	public String validation(@RequestParam(value = "dtId") Integer dtId) {

		String retVal = "1";

		CostingDtItem costingDtItem = costingDtItemService.findOne(dtId);

		if (costingDtItem.getCostingMt().getExpClose() == true) {
			return retVal = "-2";
		}

		if (costingDtItem.getrLock() == true) {
			return retVal = "-1";
		}

		return retVal;
	}

	@RequestMapping("/costingDtItem/edit/{id}")
	public String editcostingDtt(@PathVariable int id, Model model) {

		CostingDtItem costingDtItem = costingDtItemService.findOne(id);
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("costingDtItem", costingDtItem);
		return "costingDtItem/add";
	}

	@ResponseBody
	@RequestMapping("/costingDtItem/delete/{id}")
	public String delete(@PathVariable int id, Principal principal) {

		String retVal = costingDtItemService.deleteCostDtItem(id, principal);
		return retVal;
	}

	@ResponseBody
	@RequestMapping("/costingDtItem/mergeCosting")
	public String mergeCosting(@RequestParam(value = "pInvNo") String pInvNo, Principal principal) {

		CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(pInvNo, false);

		String retVal = costingDtItemService.costingDtItemSave(costingMt.getId(), principal);
		return retVal;
	}

	@ResponseBody
	@RequestMapping("/costdtItem/saveEdit")
	public String addEditCostingDt(@RequestParam(value = "costDtItemId") Integer costDtItemId,
			@RequestParam(value = "perPcDiscAmount") Double perPcDiscAmount, Principal principal) {

		String retVal = costingDtItemService.costingDtItemSave(costDtItemId, perPcDiscAmount, principal,netWtWithCompFlg);
		return retVal;
	}

	@ResponseBody
	@RequestMapping("/costingDtItem/lockUnlock")
	public String lockUnlockCostDt(@RequestParam(value = "dtId") Integer dtId) {

		String retVal = "-1";

		CostingDtItem costingDtItem = costingDtItemService.findOne(dtId);

		if (costingDtItem.getCostingMt().getExpClose() == true) {
			retVal = "-2";
		} else {

			List<CostStnDtItem> costStnDtItems = costStnDtItemService.findByCostingDtItemAndDeactive(costingDtItem,
					false);
			List<CostCompDtItem> costCompDtItems = costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem,
					false);
			List<CostLabDtItem> costLabDtItems = costLabDtItemService.findByCostingDtItemAndDeactive(costingDtItem,
					false);

			if (costingDtItem.getrLock() == true) {
				costingDtItem.setrLock(false);

				for (CostStnDtItem costStnDtItem : costStnDtItems) {
					costStnDtItem.setrLock(false);
					costStnDtItemService.save(costStnDtItem);
				}

				for (CostCompDtItem costCompDtItem : costCompDtItems) {
					costCompDtItem.setrLock(false);
					costCompDtItemService.save(costCompDtItem);
				}

				for (CostLabDtItem costLabDtItem : costLabDtItems) {
					costLabDtItem.setrLock(false);
					costLabDtItemService.save(costLabDtItem);
				}

			} else {
				costingDtItem.setrLock(true);

				for (CostStnDtItem costStnDtItem : costStnDtItems) {
					costStnDtItem.setrLock(true);
					costStnDtItemService.save(costStnDtItem);
				}

				for (CostCompDtItem costCompDtItem : costCompDtItems) {
					costCompDtItem.setrLock(true);
					costCompDtItemService.save(costCompDtItem);
				}

				for (CostLabDtItem costLabDtItem : costLabDtItems) {
					costLabDtItem.setrLock(true);
					costLabDtItemService.save(costLabDtItem);
				}

			}

			costingDtItemService.save(costingDtItem);

		}

		return retVal;
	}

	@ResponseBody
	@RequestMapping("/costingDtItem/getData/{dtId}")
	public String getCostItemData(@PathVariable int dtId) {

		CostingDtItem costingDtItem = costingDtItemService.findOne(dtId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("itemNo", costingDtItem.getItemNo());
		jsonObject.put("grossWt", costingDtItem.getGrossWt());
		jsonObject.put("netWt", costingDtItem.getNetWt());
		jsonObject.put("lossPercDt", costingDtItem.getLossPercDt());
		jsonObject.put("metalValue", costingDtItem.getMetalValue());
		jsonObject.put("stoneValue", costingDtItem.getStoneValue());
		jsonObject.put("compValue", costingDtItem.getCompValue());
		jsonObject.put("labourValue", costingDtItem.getLabValue());
		jsonObject.put("setValue", costingDtItem.getSetValue());
		jsonObject.put("handlingCost", costingDtItem.getHdlgValue());
		jsonObject.put("fob", costingDtItem.getFob());
		jsonObject.put("other", costingDtItem.getOther());
		jsonObject.put("finalPrice", costingDtItem.getFinalPrice());
		jsonObject.put("dispPercentDt", costingDtItem.getDispPercentDt());
		jsonObject.put("discAmount", costingDtItem.getDiscAmount());
		jsonObject.put("perPcDiscAmount", costingDtItem.getPerPcDiscAmount());
		jsonObject.put("perPcFinalPrice", costingDtItem.getPerPcFinalPrice());
		jsonObject.put("rLock", costingDtItem.getrLock());
		
		Double addedWtPerc =Math.round((((costingDtItem.getNetWt()-costingDtItem.getActNetWt())/costingDtItem.getActNetWt())*100)*100.0)/100.0;
		jsonObject.put("addWtPerc", addedWtPerc);
		

		return jsonObject.toString();

	}

	@ResponseBody
	@RequestMapping("/costingDtItem/updateTagWtReqCts")
	public String updateTagWtReqCts(@RequestParam(value = "pInvNo") String pInvNo, Principal principal) {

		CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(pInvNo, false);

		String retVal = costStnDtItemService.updateTagWtReqCts(costingMt, principal,netWtWithCompFlg);
		return retVal;
	}

	@ResponseBody
	@RequestMapping("/costingDtItem/autoApplyTagWt")
	public String autoApplyTagWt(@RequestParam(value = "pInvNo") String pInvNo, Principal principal) {

		CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(pInvNo, false);

		String retVal = costStnDtItemService.autoApplyTagWt(costingMt, principal,netWtWithCompFlg);
		return retVal;
	}

	@ResponseBody
	@RequestMapping("/costingDtItem/apply005TagWt")
	public String apply005TagWt(@RequestParam(value = "pInvNo") String pInvNo, Principal principal) {

		CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(pInvNo, false);

		String retVal = costStnDtItemService.apply005TagWt(costingMt, principal,netWtWithCompFlg);
		return retVal;
	}

	@ResponseBody
	@RequestMapping("/costingDtItem/updateBarcode")
	public String updateBarcode(@RequestParam(value = "pInvNo", required = false) String pInvNo, Principal principal) {

		CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(pInvNo, false);

		int retVal = costingDtItemService.getUpdateBarcode(costingMt.getId(), principal);

		return retVal + "";

	}

	@RequestMapping("/costingDtItem/styleNotMatch/report")
	@ResponseBody
	public String bagHistory(@RequestParam(value = "mtid", required = false) Integer exportInvCond, Principal principal,
			@RequestParam(value = "xml", required = false) String xml) throws SQLException {

		String retVal = "-1";
		String fileName = "";

		Connection conn = null;

		try {

			conn = Utils.getConnection();

			fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";

			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();

			parametersMap.put("exportInvCond", exportInvCond);

			JasperPrint jp = JasperFillManager.fillReport(input, parametersMap, conn);

			// ------------//--common--PDF--------//

			String commonFileName = System.currentTimeMillis() + "";
			commonFileName = commonFileName + xml;
			File file = new File(uploadDirecotryPath + reportoutputdirectorypath + commonFileName + ".pdf");
			file.createNewFile();
			JasperExportManager.exportReportToPdfFile(jp,
					uploadDirecotryPath + reportoutputdirectorypath + commonFileName + ".pdf");

			String exportCommonFileName = System.currentTimeMillis() + "" + principal.getName() + xml;
			Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath + commonFileName + ".pdf",
					uploadDirecotryPath + reportoutputdirectorypath + exportCommonFileName + ".pdf");

			file.delete();

			retVal = exportCommonFileName;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conn.close();
		}
		return retVal;

	}

	@RequestMapping("/costingDtItem/ordPcslisting")
	@ResponseBody
	public String ordPcslisting(Model model, @RequestParam(value = "pInvNo", required = false) String pInvNo) {

		StringBuilder sb = new StringBuilder();

		CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(pInvNo.trim(), false);

		QCostingDtItem qCostingDtItem = QCostingDtItem.costingDtItem;
		JPAQuery query = new JPAQuery(entityManager);

		List<Tuple> costingDtList = query.from(qCostingDtItem)
				.where(qCostingDtItem.deactive.eq(false).and(qCostingDtItem.costingMt.id.eq(costingMt.getId())))
				.groupBy(qCostingDtItem.orderDt.orderMt.invNo).list(qCostingDtItem.orderDt.orderMt.invNo,
						qCostingDtItem.orderDt.orderMt.refNo, qCostingDtItem.pcs.sum(),
						qCostingDtItem.finalPrice.sum());

		sb.append("{\"total\":").append(costingDtList.size()).append(",\"rows\": [");
		for (Tuple tuple : costingDtList) {

			Double vPcs = tuple.get(qCostingDtItem.pcs.sum()) != null ? tuple.get(qCostingDtItem.pcs.sum()) : 0.0;
			Double vFinalPrice = tuple.get(qCostingDtItem.finalPrice.sum()) != null
					? tuple.get(qCostingDtItem.finalPrice.sum())
					: 0.0;

			Double vvPcs = Math.round(vPcs * 100.0) / 100.0;
			Double vvFinalPrice = Math.round(vFinalPrice * 100.0) / 100.0;

			sb.append("{\"costMtid\":\"").append(tuple.get(qCostingDtItem.costingMt.id)).append("\",\"ordNo\":\"")
					.append(tuple.get(qCostingDtItem.orderDt.orderMt.invNo) != null
							? tuple.get(qCostingDtItem.orderDt.orderMt.invNo)
							: "")
					.append("\",\"ordRefNo\":\"")
					.append(tuple.get(qCostingDtItem.orderDt.orderMt.refNo) != null
							? tuple.get(qCostingDtItem.orderDt.orderMt.refNo)
							: "")
					.append("\",\"pcs\":\"").append(vvPcs != null ? vvPcs : 0.0).append("\",\"finalPrice\":\"")
					.append(vvFinalPrice != null ? vvFinalPrice : 0.0).append("\"},");

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;

	}

	
	@ResponseBody
	@RequestMapping("/costingDtItem/updateNetWt")
	public String updateStnRate(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "netWt", required = false) Double netWt,Principal principal) {
		
		String retVal="-1";
		retVal=costingDtItemService.updateDtNetWtAndMetalDetails(principal, id, netWt,netWtWithCompFlg);
		
		
		return retVal;
	}
	
	
}

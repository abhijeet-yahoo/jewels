package com.jiyasoft.jewelplus.controller.manufacturing.masters.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Company;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICompanyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;

@RequestMapping("/manufacturing/masters/reports")
@Controller
public class ReportFilterController {
	
    @Autowired
	private EntityManager entityManager;

	@Autowired
	private IReportFilterService reportFilterService;
	
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private ICompanyService companyService;
	

	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${tmpUploadImage}")
	private String tmpUploadImage;

	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;

	@ModelAttribute("design")
	public Design constructDesign() {
		return new Design();
	}

	@ModelAttribute("reportFilter")
	public ReportFilter constructReportFilter() {
		return new ReportFilter();
	}
	
	
	@RequestMapping("/levelOne/combo")
	@ResponseBody
	public String levelOne(
			@RequestParam(value = "group") String group){
		String tempList = reportFilterService.onChangeLevelOne(group);
		return tempList;
	}
	
	@RequestMapping("/levelTwo/combo")
	@ResponseBody
	public String levelTwo(
			@RequestParam(value = "group") String group){
		String tempList = reportFilterService.onChangeLevelTwo(group);
		return tempList;
	}
	
	@RequestMapping("/levelThree/combo")
	@ResponseBody
	public String levelThree(
			@RequestParam(value = "group") String group){
		String tempList = reportFilterService.onChangeLevelThree(group);
		return tempList;
	}
	

	@RequestMapping("/generate/{reportName}")
	public String reportOne(@PathVariable String reportName, Model model,
			Principal principal) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		
		ReportFilter reportFilter = reportFilterService.findByName(reportName);
		
		
		MenuMast menuMast =menuMastService.findByMenuNm(reportFilter.getName());
		String dbGroupValue = (reportFilter.getGroupList() != null ? reportFilter.getGroupList() : null);
		//model.addAttribute("canInsert", loginUser.getCanInsert());
		model.addAttribute("reportFilter", reportFilter);
		model.addAttribute("procName", reportFilter.getProcedureNm());
		model.addAttribute("filterForm", reportFilter.getFilterForm());
		model.addAttribute("xml", reportFilter.getXml());
		model.addAttribute("headingName",menuMast.getMenuHeading());
		model.addAttribute("filterName", reportFilter.getFilterName());
		model.addAttribute("comboLevelOne", reportFilterService.popLevelOneList(dbGroupValue));
		model.addAttribute("comboLevelTwo", reportFilterService.popLevelTwoList(dbGroupValue));
		model.addAttribute("comboLevelThree", reportFilterService.popLevelThreeList(dbGroupValue));
		model.addAttribute("currentDate", curDate);
		model.addAttribute("compulsoryFilter", reportFilter.getCompulsoryFilter());
		model.addAttribute("mandatoryFilter", reportFilter.getMandatoryFilter());
	
		
		String tilesTag = "";
		String jspPage = reportFilter.getFilterForm();

		if (jspPage.equalsIgnoreCase("departmentStock")) {
			tilesTag = "departmentStock";
		}
		
		if (jspPage.equalsIgnoreCase("clientDeptInOut")) {
			tilesTag = "clientDeptInOut";
		}
		
		if (jspPage.equalsIgnoreCase("orderSheet")) {
			tilesTag = "orderSheet";
		}
		
		if (jspPage.equalsIgnoreCase("componentStock")) {
			tilesTag = "componentStock";
		}
		
		if(jspPage.equalsIgnoreCase("orderCatalog")){
			tilesTag = "orderCatalog";
		}
		
		if(jspPage.equalsIgnoreCase("metalStock")){
			tilesTag = "metalStock";
		}
		
		if(jspPage.equalsIgnoreCase("bagging")){
			tilesTag = "bagging";
		}
		
		if(jspPage.equalsIgnoreCase("baggingSummary")){
			tilesTag = "baggingSummary";
		}
		
		if(jspPage.equalsIgnoreCase("casting")){
			tilesTag = "casting";
		}
		
		if(jspPage.equalsIgnoreCase("changingReceive")){
			tilesTag = "changingReceive";
		}
		
		if(jspPage.equalsIgnoreCase("changingIssue")){
			tilesTag = "changingIssue";
		}
		
		if(jspPage.equalsIgnoreCase("invoiceDiamond")){
			tilesTag = "invoiceDiamond";
		}
		
		if(jspPage.equalsIgnoreCase("deptExportToExcel")){
			tilesTag="deptExportToExcel";
		}
		
		if(jspPage.equalsIgnoreCase("exportPackingList")){
			tilesTag="exportPackingList";
		}
		
		if(jspPage.equalsIgnoreCase("exportSalesSummary")){
			tilesTag="exportSalesSummary";
		}
		
		if(jspPage.equalsIgnoreCase("prodDeptWiseReport")){
			tilesTag="prodDeptWiseReport";
		}
		
				
		if(jspPage.equalsIgnoreCase("shapeDiamond")){
			tilesTag = "shapeDiamond";
		}
		
		if(jspPage.equalsIgnoreCase("ordDeptAndStyleWise")){
			tilesTag = "ordDeptAndStyleWise";
		}
		
		if(jspPage.equalsIgnoreCase("bagPrint")){
			tilesTag = "bagPrint";
		}
		
		if(jspPage.equalsIgnoreCase("readyBagReport")){
			tilesTag = "readyBagReport";
		}
		
	
		
		if(jspPage.equalsIgnoreCase("lossDustRecoveryReport")){
			tilesTag="lossDustRecoveryReport";
		}
		
		
		if(jspPage.equalsIgnoreCase("WorkerWiseLossAndDust")){
			tilesTag="WorkerWiseLossAndDust";
		}
		
		if(jspPage.equalsIgnoreCase("lossRepo")){
			tilesTag = "lossRepo";
		}
		
		if(jspPage.equalsIgnoreCase("jobNoCatalog")){
			tilesTag = "jobNoCatalog";
		}
		
		
		if(jspPage.equalsIgnoreCase("orderMetalStyleWiseReq")){
			tilesTag = "orderMetalStyleWiseReq";
		}
		
		if(jspPage.equalsIgnoreCase("orderMetalSummaryReq")){
			tilesTag = "orderMetalSummaryReq";
		}
		
		if(jspPage.equalsIgnoreCase("metalAdditionReport")){
			tilesTag="metalAdditionReport";
		}
		if(jspPage.equalsIgnoreCase("metalDeductionReport")){
			tilesTag="metalDeductionReport";
		}
		
		if(jspPage.equalsIgnoreCase("packingList")){
			tilesTag = "packingList";
		}
		
		if(jspPage.equalsIgnoreCase("costSheet")){
			tilesTag = "costSheet";
		}
		
		
		if(jspPage.equalsIgnoreCase("exportWtDiff")){
			tilesTag = "exportWtDiff";
		}
		
		
		if(jspPage.equalsIgnoreCase("quotSheet")){
			tilesTag = "quotSheet";
		}
		
		if(jspPage.equalsIgnoreCase("orderStoneReq")){
			tilesTag = "orderStoneReq";
		}
		
		if(jspPage.equalsIgnoreCase("orderComponentReq")){
			tilesTag = "orderComponentReq";
		}
		
		if(jspPage.equalsIgnoreCase("departmentIssueReport")){
			tilesTag = "departmentIssueReport";
		}
		
		if(jspPage.equalsIgnoreCase("departmentReceiveReport")){
			tilesTag = "departmentReceiveReport";
		}
		
		if(jspPage.equalsIgnoreCase("exportClientCatalog")){
			tilesTag = "exportClientCatalog";
		}
		
		
		if(jspPage.equalsIgnoreCase("componentIssueReport")){
			tilesTag = "componentIssueReport";
		}
		
		if(jspPage.equalsIgnoreCase("componentReceiveReport")){
			tilesTag = "componentReceiveReport";
		}
		
		if(jspPage.equalsIgnoreCase("metalIssueReport")){
			tilesTag = "metalIssueReport";
		}
		
		if(jspPage.equalsIgnoreCase("metalReceiveReport")){
			tilesTag = "metalReceiveReport";
		}
		
		if(jspPage.equalsIgnoreCase("metalInwardReport")){
			tilesTag = "metalInwardReport";
		}
		
		if(jspPage.equalsIgnoreCase("compInwardReport")){
			tilesTag = "compInwardReport";
		}
		
		if(jspPage.equalsIgnoreCase("bagWiseLoss")){
			tilesTag = "bagWiseLoss";
		}
		
		if(jspPage.equalsIgnoreCase("departmentWiseLossCatalog")){
			tilesTag = "departmentWiseLossCatalog";
		}
		
		if(jspPage.equalsIgnoreCase("brokenRep")){
			tilesTag = "brokenRep";
		}
		
		if(jspPage.equalsIgnoreCase("brokenSummaryRep")){
			tilesTag = "brokenSummaryRep";
		}
		
		if(jspPage.equalsIgnoreCase("fallenSummaryRep")){
			tilesTag = "fallenSummaryRep";
		}
		
		
		if(jspPage.equalsIgnoreCase("diamondAdjRep")){
			tilesTag = "diamondAdjRep";
		}
		
		
		if(jspPage.equalsIgnoreCase("fallenRep")){
			tilesTag = "fallenRep";
		}
		
		if(jspPage.equalsIgnoreCase("annexure")){
			tilesTag = "annexure";
		}
		
		if(jspPage.equalsIgnoreCase("annexureNoCost")){
			tilesTag = "annexureNoCost";
		}
		
		if(jspPage.equalsIgnoreCase("costingTag")){
			tilesTag = "costingTag";
		}
		
		if(jspPage.equalsIgnoreCase("valueAddition")){
			tilesTag = "valueAdditionReport";
		}
		
		if(jspPage.equalsIgnoreCase("costingSticker")){
			tilesTag = "costingSticker";
		}
		
		if (jspPage.equalsIgnoreCase("valueAdditionInvoice")) {
			tilesTag = "valueAdditionInvoice";
		}
		
		if (jspPage.equalsIgnoreCase("demo")) {
			tilesTag = "demo";
		}
		
		if(jspPage.equalsIgnoreCase("hmInvoice")){
			tilesTag = "hmInvoice";
		}
		
		if(jspPage.equalsIgnoreCase("nyCostSheet")){
			tilesTag = "nyCostSheet";
		}
		
		if(jspPage.equalsIgnoreCase("diamondStock")){
			tilesTag = "diamondStock";
		}
		
		if(jspPage.equalsIgnoreCase("groupDiamondRate")){
			tilesTag = "groupDiamondRate";
		}
		
		if(jspPage.equalsIgnoreCase("groupDiamondAvgRate")){
			tilesTag = "groupDiamondAvgRate";
		}
		
		if(jspPage.equalsIgnoreCase("pcsProduction")){
			tilesTag = "pcsProduction";
		}
		
		if(jspPage.equalsIgnoreCase("stoneProduction")){
			tilesTag = "stoneProduction";
		}
		
		if(jspPage.equalsIgnoreCase("factoryStock")){
			tilesTag = "factoryStock";
		}
		
		if(jspPage.equalsIgnoreCase("wip")){
			tilesTag = "wip";
		}
		
		if(jspPage.equalsIgnoreCase("factoryStockWithValue")){
			tilesTag = "factoryStockWithValue";
		}
		
		if(jspPage.equalsIgnoreCase("customProformaB")){
			tilesTag = "customProformaB";
		}
		
		if(jspPage.equalsIgnoreCase("tibCatalog")){
			tilesTag = "tibCatalog";
		}
		
		if(jspPage.equalsIgnoreCase("pcsProductionSummary")){
			tilesTag = "pcsProductionSummary";
		}
		
		if(jspPage.equalsIgnoreCase("stoneProductionSummary")){
			tilesTag = "stoneProductionSummary";
		}
		
		if(jspPage.equalsIgnoreCase("metalAdjustment")){
			tilesTag = "metalAdjustment";
		}
		
		if(jspPage.equalsIgnoreCase("exportMetalAdjustment")){
			tilesTag = "exportMetalAdjustment";
		}
		
		if(jspPage.equalsIgnoreCase("changingReceiveSummary")){
			tilesTag = "changingReceiveSummary";
		}
		
		if(jspPage.equalsIgnoreCase("changingIssueSummary")){
			tilesTag = "changingIssueSummary";
		}
		
		if(jspPage.equalsIgnoreCase("changingReport")){
			tilesTag = "changingReport";
		}
		
		if(jspPage.equalsIgnoreCase("compAdjustment")){
			tilesTag = "compAdjustment";
		}
		
		if(jspPage.equalsIgnoreCase("exportCompAdjustment")){
			tilesTag = "exportCompAdjustment";
		}
		
		if(jspPage.equalsIgnoreCase("exportDiamondAdjustment")){
			tilesTag = "exportDiamondAdjustment";
		}
		
		if(jspPage.equalsIgnoreCase("quotationReport")){
			tilesTag="quotationReport";
		}
		
		if(jspPage.equalsIgnoreCase("netLoss")){
			tilesTag="netLoss";
		}
		
		if(jspPage.equalsIgnoreCase("preCosting")){
			tilesTag="preCosting";
		}
		
		if(jspPage.equalsIgnoreCase("vAdditionAnnexure")){
			tilesTag="vAdditionAnnexure";
		}
		
		if(jspPage.equalsIgnoreCase("factoryStockDateWise")){
			tilesTag="factoryStockDateWise";
		}
		
		if(jspPage.equalsIgnoreCase("factoryStockWithValueDateWise")){
			tilesTag="factoryStockWithValueDateWise";
		}
		
		
		if(jspPage.equalsIgnoreCase("jobAnnexure")){
			tilesTag="jobAnnexure";
		}
		
		if(jspPage.equalsIgnoreCase("jobCostingTag")){
			tilesTag="jobCostingTag";
		}
		
		if(jspPage.equalsIgnoreCase("plainJewelleryCosting")){
			tilesTag="plainJewelleryCosting";
		}
		
		
		if(jspPage.equalsIgnoreCase("jobCostSheet")){
			tilesTag="jobCostSheet";
		}
		
		if(jspPage.equalsIgnoreCase("pdDeptStock")){
			tilesTag="pdDeptStock";
		}
		
		if(jspPage.equalsIgnoreCase("hmannexureNocost")){
			tilesTag="hmannexureNocost";
		}
		
		
		if(jspPage.equalsIgnoreCase("diamondConsumption")){
			tilesTag="diamondConsumption";
		}
		
		if(jspPage.equalsIgnoreCase("diamondCustom")){
			tilesTag="diamondCustom";
		}
		
		if(jspPage.equalsIgnoreCase("diamondInwardStatus")){
			tilesTag="diamondInwardStatus";
		}
		
		if(jspPage.equalsIgnoreCase("pdProduction")){
			tilesTag="pdProduction";
		}
		
		if(jspPage.equalsIgnoreCase("pdProductionSummary")){
			tilesTag="pdProductionSummary";
		}
		
		if(jspPage.equalsIgnoreCase("meltingdetail")){
			tilesTag="meltingdetail";
		}
		
		if(jspPage.equalsIgnoreCase("costSheetSummary")){
			tilesTag="costSheetSummary";
		}
		
		if(jspPage.equalsIgnoreCase("costingSummary")){
			tilesTag="costingSummary";
		}
		
		if(jspPage.equalsIgnoreCase("valueAdditionSummary")){
			tilesTag="valueAdditionSummary";
		}
		
		if(jspPage.equalsIgnoreCase("valueAdditionDiaInSummary")){
			tilesTag="valueAdditionDiaInSummary";
		}
		
		if(jspPage.equalsIgnoreCase("quotationCatalog")){
			tilesTag="quotationCatalog";
		}
		
		if(jspPage.equalsIgnoreCase("quotationCatalogReport")){
			tilesTag="quotationCatalogReport";
		}
		
		
		if(jspPage.equalsIgnoreCase("dateWiseExportClientCatalog")){
			tilesTag="dateWiseExportClientCatalog";
		}
		
		if(jspPage.equalsIgnoreCase("webUpdateCatalog")){
			tilesTag="webUpdateCatalog";
		}
		
		if(jspPage.equalsIgnoreCase("wipEmail")){
			tilesTag="wipEmail";
		}
		
		if(jspPage.equalsIgnoreCase("readyBagIssueReport")){
			tilesTag="readyBagIssueReport";
		}
		
		if(jspPage.equalsIgnoreCase("stoneInwardReport")){
			tilesTag="stoneInwardReport";
		}
		
		if(jspPage.equalsIgnoreCase("stonePurcReport")){
			tilesTag="stonePurcReport";
		}
		
		if(jspPage.equalsIgnoreCase("wipSummary")){
			tilesTag="wipSummary";
		}
		
		if(jspPage.equalsIgnoreCase("wipCasting")){
			tilesTag="wipCasting";
		}
		
		
		if(jspPage.equalsIgnoreCase("wipStatus")){
			tilesTag="wipStatus";
		}
		
		if(jspPage.equalsIgnoreCase("diamondOpCl")){
			tilesTag="diamondOpCl";
		}
		
		if(jspPage.equalsIgnoreCase("diamondOpClGrp")){
			tilesTag="diamondOpClGrp";
		}
		
		if(jspPage.equalsIgnoreCase("groupWiseDiamond")){
			tilesTag="groupWiseDiamond";
		}
		
		
		if(jspPage.equalsIgnoreCase("makingReport")){
			tilesTag="makingReport";
		}
		
		if(jspPage.equalsIgnoreCase("deptWiseLoss")){
			tilesTag="deptWiseLoss";
		}
		
		if(jspPage.equalsIgnoreCase("stoneConversionRep")){
			tilesTag="stoneConversionRep";
		}
		
		if(jspPage.equalsIgnoreCase("deptWiseSummary")){
			tilesTag="deptWiseSummary";
		}
		
		
		if(jspPage.equalsIgnoreCase("wipStatusReport")){
			tilesTag="wipStatusReport";
		}
		
		if(jspPage.equalsIgnoreCase("exportDiaRate")){
			tilesTag="exportDiaRate";
		}
		
		
		if(jspPage.equalsIgnoreCase("quotDiaRate")){
			tilesTag="quotDiaRate";
		}
		
		if(jspPage.equalsIgnoreCase("exportGoldRate")){
			tilesTag="exportGoldRate";
		}
		
		if(jspPage.equalsIgnoreCase("costingStickerFl")){
			tilesTag="costingStickerFl";
		}
		
		if(jspPage.equalsIgnoreCase("diaBalanceSizeWise")){
			tilesTag="diaBalanceSizeWise";
		}
		
		if(jspPage.equalsIgnoreCase("consumptionReport")){
			tilesTag="consumptionReport";
		}
		
		if(jspPage.equalsIgnoreCase("meltingStnRec")){
			tilesTag="meltingStnRec";
		}
		
		if(jspPage.equalsIgnoreCase("exportDiaDetails")){
			tilesTag="exportDiaDetails";
		}
		
		if(jspPage.equalsIgnoreCase("exportDiaStatementII")){
			tilesTag="exportDiaStatementII";
		}
		
		if(jspPage.equalsIgnoreCase("vAddExportDiaDt")){
			tilesTag="vAddExportDiaDt";
		}
		
		if(jspPage.equalsIgnoreCase("exportWiseFinding")){
			tilesTag="exportWiseFinding";
		}
		if (jspPage.equalsIgnoreCase("wipStyleReport")) {
			tilesTag = "wipStyleReport";
		}
		
		if (jspPage.equalsIgnoreCase("orderwisediastock")) {
			tilesTag = "orderwisediastock";
		}
		
		if (jspPage.equalsIgnoreCase("compOutwardReport")) {
			tilesTag = "compOutwardReport";
		}
		
		if (jspPage.equalsIgnoreCase("stockReportLX")) {
			tilesTag = "stockReportLX";
		}
		
		if (jspPage.equalsIgnoreCase("barcodeWiseSummary")) {
			tilesTag = "barcodeWiseSummary";
		}
		
		if (jspPage.equalsIgnoreCase("alloyingReport")) {
			tilesTag = "alloyingReport";
		}
		
		if (jspPage.equalsIgnoreCase("stonePurchaseAdjReport")) {
			tilesTag = "stonePurchaseAdjReport";
		}
		
		if (jspPage.equalsIgnoreCase("exportAdjReport")) {
			tilesTag = "exportAdjReport";
		}

		if (jspPage.equalsIgnoreCase("DeptDiamondstk")) {
			tilesTag = "DeptDiamondstk";
		}
		
		if (jspPage.equalsIgnoreCase("orderDcb1")) {
			tilesTag = "orderDcb1";
		}
		
		if (jspPage.equalsIgnoreCase("orderDcb2")) {
			tilesTag = "orderDcb2";
		}
		
		if (jspPage.equalsIgnoreCase("quotDcb1")) {
			tilesTag = "quotDcb1";
		}
		
		if (jspPage.equalsIgnoreCase("costDcb1")) {
			tilesTag = "costDcb1";
		}
		
		if (jspPage.equalsIgnoreCase("vendorBagBalRpt")) {
			tilesTag = "vendorBagBalRpt";
		}
		
		if (jspPage.equalsIgnoreCase("exportListNuance")) {
			tilesTag = "exportListNuance";
		}
		
		if (jspPage.equalsIgnoreCase("salesOrderSheet")) {
			tilesTag = "salesOrderSheet";
		}
		
		if (jspPage.equalsIgnoreCase("fgStockReceive")) {
			tilesTag = "fgStockReceive";
		}
		
		if (jspPage.equalsIgnoreCase("saleRegister")) {
			tilesTag = "saleRegister";
		}
		
		if (jspPage.equalsIgnoreCase("stoneLooseDia")) {
			tilesTag = "stoneLooseDia";
		}
		
		if (jspPage.equalsIgnoreCase("fgIssueStockSummary")) {
			tilesTag = "fgIssueStockSummary";
		}
		
		if (jspPage.equalsIgnoreCase("stockMis")) {
			tilesTag = "stockMis";
		}
		
		if (jspPage.equalsIgnoreCase("stockMisWithoutValue")) {
			tilesTag = "stockMisWithoutValue";
		}
		
		if (jspPage.equalsIgnoreCase("stockMisAsOn")) {
			tilesTag = "stockMisAsOn";
		}
		
		
		
		
		if (jspPage.equalsIgnoreCase("readyBagTrnsfr")) {
			tilesTag = "readyBagTrnsfr";
		}
		
		if (jspPage.equalsIgnoreCase("purchaseRegisterReport")) {
			tilesTag = "purchaseRegisterReport";
		}
		
		if (jspPage.equalsIgnoreCase("fgIssSummaryWithVal")) {
			tilesTag = "fgIssSummaryWithVal";
		}
		
		if (jspPage.equalsIgnoreCase("jobworkissueDetail")) {
			tilesTag = "jobworkissueDetail";
		}
		
		if (jspPage.equalsIgnoreCase("jobworkRecDetail")) {
			tilesTag = "jobworkRecDetail";
		}
		
		if (jspPage.equalsIgnoreCase("metalInwardReportWithValue")) {
			tilesTag = "metalInwardReportWithValue";
		}
		
		if (jspPage.equalsIgnoreCase("saleRegisterWithCost")) {
			tilesTag = "saleRegisterWithCost";
		}
		
		if (jspPage.equalsIgnoreCase("stockMeltingReport")) {
			tilesTag = "stockMeltingReport";
		}
		
		if (jspPage.equalsIgnoreCase("salesRegisterOneLineSummary")) {
			tilesTag = "salesRegisterOneLineSummary";
		}
		
		if (jspPage.equalsIgnoreCase("stoneOutward")) {
			tilesTag = "stoneOutward";
		}
		
		if (jspPage.equalsIgnoreCase("branchTrfOneLineSummary")) {
			tilesTag = "branchTrfOneLineSummary";
		}
		
		if (jspPage.equalsIgnoreCase("diamondBalanceReq")) {
			tilesTag = "diamondBalanceReq";
		}
		
		if (jspPage.equalsIgnoreCase("consigIssDetails")) {
			tilesTag = "consigIssDetails";
		}
		
		if (jspPage.equalsIgnoreCase("consigRetDetails")) {
			tilesTag = "consigRetDetails";
		}
		
		if (jspPage.equalsIgnoreCase("stockmissummary")) {
			tilesTag = "stockmissummary";
		}
		
		
		
		
		return tilesTag;
	}

	
	

	@ResponseBody
	@RequestMapping(value = "/generateReport", method = RequestMethod.POST)
	public String generateReport(
			@ModelAttribute("design") Design design,
			BindingResult result,
			Model model,
			Principal principal,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "procName", 				required = false) String procName,
			@RequestParam(value = "filterForm", 			required = false) String filterForm,
			@RequestParam(value = "xml", 					required = false) String xml,
			@RequestParam(value = "pClientIds", 			required = false) String pClientIds,
			@RequestParam(value = "pOrderTypeIds",			required = false) String pOrderTypeIds,
			@RequestParam(value = "pOrderIds", 				required = false) String pOrderIds,
			@RequestParam(value = "pDepartmentIds", 		required = false) String pDepartmentIds,
			@RequestParam(value = "pLocationIds", 			required = false) String pLocationIds,
			@RequestParam(value = "pMetalIds", 				required = false) String pMetalIds,
			@RequestParam(value = "pCategoryIds", 			required = false) String pCategoryIds,
			@RequestParam(value = "pVersion", 			required = false) String pVersion,
			@RequestParam(value = "pSubCategoryIds", 		required = false) String pSubCategoryIds,
			@RequestParam(value = "pConceptIds", 			required = false) String pConceptIds,
			@RequestParam(value = "pSubConceptIds", 		required = false) String pSubConceptIds,
			@RequestParam(value = "pStoneTypeIds", 			required = false) String pStoneTypeIds,
			@RequestParam(value = "pShapeIds", 				required = false) String pShapeIds,
			@RequestParam(value = "pQualityIds", 			required = false) String pQualityIds,
			@RequestParam(value = "pMtlInwardInvoiceIds", 		required = false) String pMtlInwardInvoiceIds,
			@RequestParam(value = "pCompInwardInvoiceIds", 		required = false) String pCompInwardInvoiceIds,
			@RequestParam(value = "pExportInvoiceIds", 		required = false) String pExportInvoiceIds,
			@RequestParam(value = "pExportAllInvoiceIds", 		required = false) String pExportAllInvoiceIds,
			@RequestParam(value = "pQuotationInvoiceIds", 	required = false) String pQuotationInvoiceIds,
			@RequestParam(value = "pBagsIds", 				required = false) String pBagsIds,
			@RequestParam(value = "pEmployeeIds", 			required = false) String pEmployeeIds,
			@RequestParam(value = "pExportJobInvoiceIds", 	required = false) String pExportJobInvoiceIds,
			@RequestParam(value = "pFromOrdDate", 	required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", 	required = false) String pToOrdDate,
			@RequestParam(value = "pFromDelvDate", 	required = false) String pFromDelvDate,
			@RequestParam(value = "pToDelvDate", 	required = false) String pToDelvDate,
			@RequestParam(value = "pFromBetDate", 	required = false) String pFromBetDate,
			@RequestParam(value = "pToBetDate", 	required = false) String pToBetDate,
			@RequestParam(value = "pBal", 			required = false) String pBal,
			@RequestParam(value = "pNoBbc", 			required = false) String pNoBbc,
			@RequestParam(value = "pGroup1", 		required = false) String pGroup1,
			@RequestParam(value = "pGroup2", 		required = false) String pGroup2,
			@RequestParam(value = "pGroup3", 		required = false) String pGroup3,
			@RequestParam(value = "pFormat", 		required = false) String pFormat,
			@RequestParam(value = "pDeptFormat",	required = false) String pDeptFormat,
			@RequestParam(value = "pOrdCatalogFormat",	required = false) String pOrdCatalogFormat,
			@RequestParam(value = "pCostSheetFormat",	required = false) String pCostSheetFormat,
			@RequestParam(value = "pQuotationFormat",		required = false) String pQuotationFormat,
			@RequestParam(value = "pLossFormat",		required = false) String pLossFormat,
			@RequestParam(value = "pStoneInvoiceIds",		required = false) String pStoneInvoiceIds,
			@RequestParam(value = "pStonePurcInvoiceIds",		required = false) String pStonePurcInvoiceIds,
			@RequestParam(value = "pVAddSummryFormat",		required = false) String pVAddSummryFormat,
			@RequestParam(value = "pChangingFormat",		required = false) String pChangingFormat,
			@RequestParam(value = "pComponents",		required = false) String pComponents,
			@RequestParam(value = "pPurityIds", required = false) String pPurityIds,
			@RequestParam(value = "pColorIds", required = false) String pColorIds,
			@RequestParam(value = "pMeltingInvoiceIds", required = false) String pMeltingInvoiceIds,
			@RequestParam(value = "pDesignStyleIds", required = false) String pDesignStyleIds,
			@RequestParam(value = "pFromProdDate", required = false) String pFromProdDate,
			@RequestParam(value = "pToProdDate", required = false) String pToProdDate,
			@RequestParam(value = "pDelayCondIds", required = false) Integer delayCond,
			@RequestParam(value = "pPriorityIds", required = false) String priorityCond,
			@RequestParam(value = "pSetNoId", required = false) Integer pSetNoId,
			@RequestParam(value = "pCostItemIds", required = false) String pCostItemIds,
			@RequestParam(value = "pQuotDtIds", required = false) String pQuotDtIds,
			@RequestParam(value = "transactedFlg", required = false) String transactedFlg,
			@RequestParam(value = "pSettingTypeIds", required = false) String pSettingTypeIds,
			@RequestParam(value = "pSordDtIds", required = false) String pSordDtIds,
			@RequestParam(value = "pExportQualityIds", required = false) String pExportQualityIds,
			@RequestParam(value = "pExportSizegroupIds", required = false) String pExportSizegroupIds,
			@RequestParam(value = "compulsoryFilter", required = false) String compulsoryFilter,
			@RequestParam(value = "mandatoryFilter", required = false) String mandatoryFilter,
			@RequestParam(value = "pmOpt", required = false) Integer pmOpt,
			@RequestParam(value = "pMarketingLocationIds", required = false) String pMarketingLocationIds,
			@RequestParam(value = "pMarketingInvoiceIds", required = false) String pMarketingInvoiceIds,
			@RequestParam(value = "pFromDeptIds", required = false) Integer pFromDeptIds,
			@RequestParam(value = "pInwardTypeIds", required = false) String pInwardTypeIds,
			@RequestParam(value = "pDivisionIds", required = false) String pDivisionIds,
			@RequestParam(value = "pRegionIds", required = false) String pRegionIds,
			@RequestParam(value = "pCustomerTypeIds", required = false) String pCustomerTypeIds,
			@RequestParam(value = "pBranchIds", required = false) String pBranchIds)
			throws SQLException {

		String retVal = "-1";
		String fileName = "";
		String subRptPath = "";
		String imgPath = "";
		String emailImgPath = "";
		String barCodePath="";
		String compFilterStr ="";	
		String mandFilterStr ="";		
		
		
		List<Company> companies = companyService.findAll();
		Company company = companies.get(0);
		
		
		
		

		Connection conn = null;
		try {
			
			conn = Utils.getConnection();
			
			
			
			if(compulsoryFilter != null){
				
				String[] compulsoryFilterStr = compulsoryFilter.split(",");
				for (int i = 0; i < compulsoryFilterStr.length; i++) {
					
					String compFilter = compulsoryFilterStr[i];
			
					if(compFilter.equalsIgnoreCase("orderDate".trim())) {
						if (pFromOrdDate.length() > 0 && pToOrdDate.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Order Date Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("dispatchDate".trim())) {
						if (pFromDelvDate.length() > 0 && pToDelvDate.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Dispatch Date Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("fromDepartment".trim())) {
						if (pFromDeptIds != null) {
						}else {
							compFilterStr +="<br />"+"Department Dropdown Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("betweenPeriod".trim())) {
						if (pFromBetDate.length() > 0 && pToBetDate.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Between Period Date Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("productionPeriod".trim())) {
						if (pToProdDate.length() > 0 && pFromProdDate.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Production Period Date Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("priorityCond".trim())) {
						if (priorityCond.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Priority Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("transactedFlg".trim())) {
						if (transactedFlg.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Transacted Not Selected";
						}
					}
					
					
					
					if(compFilter.equalsIgnoreCase("client".trim())) {
						if (pClientIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Client Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("component".trim())) {
						if (pComponents.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Component Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("orderType".trim())) {
						if (pOrderTypeIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "OrderType Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("order".trim())) {
						if (pOrderIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Order Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("inwardType".trim())) {
						if (pInwardTypeIds.length() > 0) {
						}else {
							compFilterStr +="Inward Type Not Selected";
						}
					}
					
					
					if(compFilter.equalsIgnoreCase("orderStyle".trim())) {
						if (pSordDtIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Order Style Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("department".trim())) {
						if (pDepartmentIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Department Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("location".trim())) {
						if (pLocationIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Location Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("marketingLocation".trim())) {
						if (pMarketingLocationIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Location Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("metal".trim())) {
						if (pMetalIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Metal Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("purityFilter".trim())) {
						if (pPurityIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Purity Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("colorFilter".trim())) {
						if (pColorIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Color Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("category".trim())) {
						if (pCategoryIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Category Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("subCategory".trim())) {
						if (pSubCategoryIds.length() > 0) {
						}else {
							compFilterStr ="SubCategory Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("version".trim())) {
						if (pVersion.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Version Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("concept".trim())) {
						if (pConceptIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Concept Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("subConcept".trim())) {
						if (pSubConceptIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Sub Concept Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("stoneType".trim())) {
						if (pStoneTypeIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "StoneType Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("shape".trim())) {
						if (pShapeIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Shape Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("quality".trim())) {
						if (pQualityIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Quality Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("pMtlInwardInvoiceIds".trim())) {
						if (pMtlInwardInvoiceIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Invoice Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("compInwardInvoice".trim())) {
						if (pCompInwardInvoiceIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Invoice Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("exportInvoice".trim())) {
						if (pExportInvoiceIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Invoice Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("exportInvoiceAll".trim())) {
						if (pExportAllInvoiceIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Invoice Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("exportQuality".trim())) {
						if (pExportQualityIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Quality Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("exportSizegroup".trim())) {
						if (pExportSizegroupIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "SizeGroup Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("quotationInvoice".trim())) {
						if (pQuotationInvoiceIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Invoice Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("bags".trim())) {
						if (pBagsIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Bag No Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("employee".trim())) {
						if (pEmployeeIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+ "Employee Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("exportJobInvoice".trim())) {
						if (pExportJobInvoiceIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Invoice Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("stoneInvoice".trim())) {
						if (pStoneInvoiceIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Stone Invoice Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("melting".trim())) {
						if (pMeltingInvoiceIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Melting Not Selected";
						}
					}
					
					if(compFilter.equalsIgnoreCase("design".trim())) {
						if (pDesignStyleIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Design Not Selected";
						}
					}
					
					
					if(compFilter.equalsIgnoreCase("settingType".trim())) {
						if (pSettingTypeIds.length() > 0) {
						}else {
							compFilterStr +="<br />"+"Setting Not Selected";
						}
					}
					
					
					
				}
				/*
				 * if(compFilterStr !=""){
				 * 
				 * return "^c^"+compFilterStr; }
				 */
			}
			
			//Code For Mandatory Filter
			
			if(mandatoryFilter != null){
				
				String[] mandatoryFilterStr = mandatoryFilter.split(",");
				for (int i = 0; i < mandatoryFilterStr.length; i++) {
					
					String mandmandatoryFlt = mandatoryFilterStr[i];
						
					if(mandmandatoryFlt.equalsIgnoreCase("orderDate".trim())) {
						if (pFromOrdDate.length() > 0 && pToOrdDate.length() > 0) {
						}else {
							mandFilterStr +="<br />"+"Order Date Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("dispatchDate".trim())) {
						if (pFromDelvDate.length() > 0 && pToDelvDate.length() > 0) {
						}else {
							mandFilterStr +="<br />"+"Dispatch Date Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("betweenPeriod".trim())) {
						if (pFromBetDate.length() > 0 && pToBetDate.length() > 0) {
						}else {
							mandFilterStr +="<br />"+"Between Period Date Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("productionPeriod".trim())) {
						if (pToProdDate.length() > 0 && pFromProdDate.length() > 0) {
						}else {
							mandFilterStr +="<br />"+"Production Period Date Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("priorityCond".trim())) {
						if (priorityCond.length() > 0) {
						}else {
							mandFilterStr +="<br />"+"Priority Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("transactedFlg".trim())) {
						if (transactedFlg.length() > 0) {
						}else {
							mandFilterStr +="<br />"+"Transacted Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("client".trim())) {
						if (pClientIds.length() > 0) {
						}else {
							mandFilterStr += "Client Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("inwardType".trim())) {
						if (pInwardTypeIds.length() > 0) {
						}else {
							mandFilterStr +="Inward Type Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("component".trim())) {
						if (pComponents.length() > 0) {
						}else {
							mandFilterStr += "Component Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("fromDepartment".trim())) {
						if (pFromDeptIds != null) {
						}else {
							mandFilterStr += "Department Dropdown Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("orderType".trim())) {
						if (pOrderTypeIds.length() > 0) {
						}else {
							mandFilterStr += "OrderTYype Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("order".trim())) {
						if (pOrderIds.length() > 0) {
						}else {
							mandFilterStr += "Order Not Selected";
						}
					}
					
					
					if(mandmandatoryFlt.equalsIgnoreCase("orderStyle".trim())) {
						if (pSordDtIds.length() > 0) {
						}else {
							mandFilterStr += "Order Style Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("department".trim())) {
						if (pDepartmentIds.length() > 0) {
						}else {
							mandFilterStr += "Department Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("location".trim())) {
						if (pLocationIds.length() > 0) {
						}else {
							mandFilterStr += "Location Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("marketingLocation".trim())) {
						if (pMarketingLocationIds.length() > 0) {
						}else {
							mandFilterStr += "Location Not Selected";
						}
					}
					
					
					
					if(mandmandatoryFlt.equalsIgnoreCase("metal".trim())) {
						if (pMetalIds.length() > 0) {
						}else {
							mandFilterStr += "Metal Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("purityFilter".trim())) {
						if (pPurityIds.length() > 0) {
						}else {
							mandFilterStr += "Purity Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("colorFilter".trim())) {
						if (pColorIds.length() > 0) {
						}else {
							mandFilterStr += "Color Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("category".trim())) {
						if (pCategoryIds.length() > 0) {
						}else {
							mandFilterStr += "Category Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("subCategory".trim())) {
						if (pSubCategoryIds.length() > 0) {
						}else {
							mandFilterStr += "SubCategory Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("version".trim())) {
						if (pVersion.length() > 0) {
						}else {
							mandFilterStr ="Version Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("concept".trim())) {
						if (pConceptIds.length() > 0) {
						}else {
							mandFilterStr += "Concept Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("subConcept".trim())) {
						if (pSubConceptIds.length() > 0) {
						}else {
							mandFilterStr += "Sub Concept Not Selected";
						}
					}
					
					if(mandFilterStr.equalsIgnoreCase("stoneType".trim())) {
						if (pStoneTypeIds.length() > 0) {
						}else {
							mandFilterStr += "StoneType Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("shape".trim())) {
						if (pShapeIds.length() > 0) {
						}else {
							mandFilterStr += "Shape Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("quality".trim())) {
						if (pQualityIds.length() > 0) {
						}else {
							mandFilterStr += "Quality Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("pMtlInwardInvoiceIds".trim())) {
						if (pMtlInwardInvoiceIds.length() > 0) {
						}else {
							mandFilterStr += "Invoice Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("compInwardInvoice".trim())) {
						if (pCompInwardInvoiceIds.length() > 0) {
						}else {
							mandFilterStr += "Invoice Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("exportInvoice".trim())) {
						if (pExportInvoiceIds.length() > 0) {
						}else {
							mandFilterStr += "Invoice Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("exportInvoiceAll".trim())) {
						if (pExportAllInvoiceIds.length() > 0) {
						}else {
							mandFilterStr += "Invoice Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("exportQuality".trim())) {
						if (pExportQualityIds.length() > 0) {
						}else {
							mandFilterStr += "Quality Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("exportSizegroup".trim())) {
						if (pExportSizegroupIds.length() > 0) {
						}else {
							mandFilterStr += "SizeGroup Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("quotationInvoice".trim())) {
						if (pQuotationInvoiceIds.length() > 0) {
						}else {
							mandFilterStr += "Invoice Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("bags".trim())) {
						if (pBagsIds.length() > 0) {
						}else {
							mandFilterStr += "Bag No Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("employee".trim())) {
						if (pEmployeeIds.length() > 0) {
						}else {
							mandFilterStr += "Employee Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("exportJobInvoice".trim())) {
						if (pExportJobInvoiceIds.length() > 0) {
						}else {
							mandFilterStr += "Invoice Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("stoneInvoice".trim())) {
						if (pStoneInvoiceIds.length() > 0) {
						}else {
							mandFilterStr += "Stone Invoice Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("melting".trim())) {
						if (pMeltingInvoiceIds.length() > 0) {
						}else {
							mandFilterStr += "Melting Not Selected";
						}
					}
					
					if(mandmandatoryFlt.equalsIgnoreCase("design".trim())) {
						if (pDesignStyleIds.length() > 0) {
						}else {
							mandFilterStr += "Design Not Selected";
						}
					}
					
					
					if(mandmandatoryFlt.equalsIgnoreCase("settingType".trim())) {
						if (pSettingTypeIds.length() > 0) {
						}else {
							mandFilterStr +="Setting Not Selected";
						}
					}
					
				}
				
				
				
			}
			
			
			
			if(mandFilterStr !="" && compFilterStr !=null){
				return "^m^"+mandFilterStr+compFilterStr;
			}
			
			
			
			String fromPeriod = pFromBetDate;
			String toPeriod = pToBetDate;
			
			
			SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");

			

			String whereCond = "";
			String partyCond = "";
			String orderTypeCond = "";
			String orderCond = "";
			String departmentCond = "";
			String locationCond = "";
			String metalCond = "";
			String categoryCond = "";
			String versionCond="";
			String subCategoryCond = "";
			String conceptCond = "";
			String subConceptCond = "";
			String stoneTypeCond = "";
			String shapeCond = "";
			String qualityCond = "";
			String exportInvCond = "";
			String mtlInwardInvCond="";
			String compInwardInvCond="";
			String quotationInvCond = "";
			String bagCond = "";
			String employeeCond = "";
			String exportJobInvCond = "";
			String stoneInvCond ="";
			String stonePurcInvCond ="";
			String ordPartyCond="";
			String componentCond="";
			String purityCond = "";
			String colorCond ="";
			String meltingInvCond ="";
			String designCond ="";
			String dtItemCond="";
			String settingTypeCond = "";
			String sordItemCond = "";
			String exportQualityCond = "";
			String exportSizegroupCond = "";
			String marketinglocationCond = "";
			String fromDeptCond = "";
			String toDeptCond = "";
			Integer deptIdCond = 0;
			String inwardTypeCond = "";
			String divisionCond = "";
			String regionCond = "";
			String customerTypeCond = "";
			String branchCond = "";
			
			
			if(filterForm.equalsIgnoreCase("departmentStock".trim())){
				if(pDeptFormat.equalsIgnoreCase("ClientSummary")){
					xml ="ClientSummaryDeptstk";
				}
				
				if(pDeptFormat.equalsIgnoreCase("DiamondBalClientSummary")){
					xml ="ClientDiamondBalanceSummary";
				}
			}
			
			
			if(filterForm.equalsIgnoreCase("orderCatalog".trim())){
				if(pOrdCatalogFormat.equalsIgnoreCase("SalesOrderCatalog")){
					xml ="salesordercatalog";
				}
			}
			
			if(filterForm.equalsIgnoreCase("costSheetSummary".trim())){
				if(pCostSheetFormat.equalsIgnoreCase("costSheetSummaryNew")){
					xml ="costsheetsummary1";
				}
			}
			
			
			
			
			
			if(filterForm.equalsIgnoreCase("quotationCatalog".trim())){
				if(pQuotationFormat.equalsIgnoreCase("formatTwo")){
					xml ="quotationcatalog1";
				}else if(pQuotationFormat.equalsIgnoreCase("formatThree")){
					xml ="quotationcatalog2";
				}else if(pQuotationFormat.equalsIgnoreCase("formatThreeBarCode")){
					xml ="quotationcatalog3";
				}
			}
			
			
			if(filterForm.equalsIgnoreCase("valueAdditionSummary".trim())){
				if(pVAddSummryFormat.equalsIgnoreCase("valueAdditionStoneDetail")){
					xml ="valuadditionstonesummary";
				}
			}
			
			
			if(filterForm.equalsIgnoreCase("lossRepo".trim())){
				if(pLossFormat.equalsIgnoreCase("formatTwo")){
					xml ="LossReportperc";
				}
			}
			

			if (pClientIds.length() > 0) {
				whereCond = " ' PartyId in (" + pClientIds + ") ";
				partyCond =  " ' PartyId in (" + pClientIds + ") ' ";
				ordPartyCond =  " ' ordpartyid in (" + pClientIds + ") ' ";
			}else{
				partyCond = " ' ' ";
				ordPartyCond = " ' ' ";
			}
			
			
			if(priorityCond.length()>0){
				
				priorityCond =" ' priority in("+priorityCond+") ' ";
				
			}else{
				priorityCond="''";
			}
			
			
			if (pStoneInvoiceIds.length() > 0) {
				stoneInvCond =  " ' MtId in (" + pStoneInvoiceIds + ") ' ";
			}else{
				stoneInvCond = " ' ' ";
			}
			
			
			
			if (pStonePurcInvoiceIds.length() > 0) {
				stonePurcInvCond =  " ' MtId in (" + pStonePurcInvoiceIds + ") ' ";
			}else{
				stonePurcInvCond = " ' ' ";
			}
			
			
			

			if (pOrderTypeIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND OrdTypeId in (" + pOrderTypeIds + ") ";
				} else {
					whereCond = " ' OrdTypeId in (" + pOrderTypeIds + ") ";
				}
				
				orderTypeCond = " ' OrdTypeId in (" + pOrderTypeIds + ") ' ";
			}else{
				orderTypeCond = " ' ' ";
			}

			if (pOrderIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND sordMtId in (" + pOrderIds + ") ";
				} else {
					whereCond = " ' sordMtId in (" + pOrderIds + ") ";
				}
				
				orderCond = " ' sordMtId in (" + pOrderIds + ") ' ";
			}else{
				orderCond = " ' ' ";
			}
			
			if (pSordDtIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND dtId in (" + pSordDtIds + ") ";
				} else {
					whereCond = " ' dtId in (" + pSordDtIds + ") ";
				}
				
				sordItemCond = " ' dtId in (" + pSordDtIds + ") ' ";
			}else{
				sordItemCond = " ' ' ";
			}
			
			
			
			
			if (pDesignStyleIds.length() > 0) 
			{
				if(whereCond.length() > 0)
					whereCond += "  AND styleId in (" + pDesignStyleIds + ") ";
				else
					whereCond = " ' styleId in (" + pDesignStyleIds + ") ";
				
				designCond = " ' styleId in (" + pDesignStyleIds + ") ' ";
			}
			else
				designCond = " ' ' ";
			
			
			
			if (pComponents.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND ComponentId in (" + pComponents + ") ";
				} else {
					whereCond = " ' ComponentId in (" + pComponents + ") ";
				}
				
				componentCond = " ' ComponentId in (" + pComponents + ") ' ";
			}else{
				componentCond = " ' ' ";
			}
			
			if (pMeltingInvoiceIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND mtid in (" + pMeltingInvoiceIds + ")  ";
				} else {
					whereCond = " ' mtid in (" + pMeltingInvoiceIds + ") ";
				}
				
				meltingInvCond = " ' mtid in (" + pMeltingInvoiceIds + ") ' ";
			}else{
				meltingInvCond = " ' ' ";
			}
			
	

			if (pInwardTypeIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND InwardTypeId in (" + pInwardTypeIds + ") ";
				} else {
					whereCond = " ' InwardTypeId in (" + pInwardTypeIds + ") ";
				}
				
				inwardTypeCond = " ' InwardTypeId in (" + pInwardTypeIds + ") ' ";
			}else{
				inwardTypeCond = " ' ' ";
			}
			
			
			if (pPurityIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND purityId in (" + pPurityIds + ") ";
				} else {
					whereCond = " ' purityId in (" + pPurityIds + ") ";
				}
				
				purityCond = " ' purityId in (" + pPurityIds + ") ' ";
			}else{
				purityCond = " ' ' ";
			}
			
			
			if (pColorIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND colorId in (" + pColorIds + ") ";
				} else {
					whereCond = " ' colorId in (" + pColorIds + ") ";
				}
				
				colorCond = " ' colorId in (" + pColorIds + ") ' ";
			}else{
				colorCond = " ' ' ";
			}
			

			if (pDepartmentIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND DeptId in (" + pDepartmentIds + ") ";
				} else {
					whereCond = " ' DeptId in (" + pDepartmentIds + ") ";
				}
				
				departmentCond = " ' DeptId in (" + pDepartmentIds + ") ' ";
				toDeptCond = " ' DeptTo in (" + pDepartmentIds + ") ' ";
				fromDeptCond = " ' DeptFrom in (" + pDepartmentIds + ") ' ";
			}else{
				departmentCond = " ' ' ";
				toDeptCond = " ' ' ";
				fromDeptCond = " ' ' ";
			}
			
			
			if (pFromDeptIds != null ) {
				
				deptIdCond = pFromDeptIds;
			}else{
				deptIdCond = 0;
			}

			if (pLocationIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND LocationId in (" + pLocationIds + ") ";
				} else {
					whereCond = " ' LocationId in (" + pLocationIds + ") ";
				}
				
				locationCond = " ' LocationId in (" + pLocationIds + ") ' ";
			}else{
				locationCond = " ' ' ";
			}
			
			if (pMarketingLocationIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND LocationId in (" + pMarketingLocationIds + ") ";
				} else {
					whereCond = " ' LocationId in (" + pMarketingLocationIds + ") ";
				}
				
				marketinglocationCond = " ' LocationId in (" + pMarketingLocationIds + ") ' ";
			}else{
				marketinglocationCond = " ' ' ";
			}
			
			
			
			
			if (pMetalIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND MetalId in (" + pMetalIds + ") ";
				} else {
					whereCond = " ' MetalId in (" + pMetalIds + ") ";
				}
				
				metalCond = " ' MetalId in (" + pMetalIds + ") ' ";
			}else{
				metalCond = " ' ' ";
			}
			
			
			if (pSettingTypeIds.length() > 0) {
				if (whereCond.length() > 0) {
					whereCond += " AND SetTypeId in (" + pSettingTypeIds + ") ";
				} else {
					whereCond = " ' SetTypeId in (" + pSettingTypeIds + ") ";
				}
				
				settingTypeCond = " ' SetTypeId in (" + pSettingTypeIds + ") ' ";
			}else{
				settingTypeCond = " ' ' ";
			}
			
			
			
			
			
			

			if (pFromOrdDate.length() > 0) {

				Date ordFromDate = dfInput.parse(pFromOrdDate);
				String fOrdFromDate = dfOutput.format(ordFromDate);

				Date ordToDate = dfInput.parse(pToOrdDate);
				String fOrdToDate = dfOutput.format(ordToDate);

				if (whereCond.length() > 0) {
					whereCond += " AND OrderDate between " + "''" + fOrdFromDate+ "''" + " and " + "''" + fOrdToDate + "''" + " ";
				} else {
					whereCond = " ' OrderDate between " + "''" + fOrdFromDate+ "''" + " and " + "''" + fOrdToDate + "''" + " ";
				}
			}

			if (pFromDelvDate.length() > 0) {

				Date delFromDate = dfInput.parse(pFromDelvDate);
				String fDelFromDate = dfOutput.format(delFromDate);

				Date delToDate = dfInput.parse(pToDelvDate);
				String fDelToDate = dfOutput.format(delToDate);

				if (whereCond.length() > 0) {
					whereCond += " AND DispatchDate between " + "''"+ fDelFromDate + "''" + " and " + "''" + fDelToDate+ "''" + " ";
				} else {
					whereCond = " ' DispatchDate between " + "''" + fDelFromDate+ "''" + " and " + "''" + fDelToDate + "''" + " ";
				}
			}

			if(whereCond.length() > 0){
				whereCond += "'";
			}else{
				whereCond = " ' ' ";
			}
			
			System.out.println("----------whereCond------------------"+ whereCond);

			// ------------------for between start-date and end-date condition

			String betweenFromDt = "";
			String betweenToDt = "";
			String fromOrderDate = "";
			String toOrderDate = "";
			String fromDispatchDate = "";
			String toDispatchDate = "";
			String fromProductionDate="";
			String toProductionDate="";
			

			if (pFromBetDate.length() > 0) {

				Date betFromDate = dfInput.parse(pFromBetDate);
				String fBetFromDate = dfOutput.format(betFromDate);

				//Date betToDate = dfInput.parse(pToBetDate);
				//String fBetToDate = dfOutput.format(betToDate);

				betweenFromDt = "'"+fBetFromDate+"'";
				//betweenToDt   = "'"+fBetToDate+"'";
			}else{
				betweenFromDt = " ' ' ";
				//betweenToDt = " ' ' ";
			}
			
			
			if (pToBetDate.length() > 0) {

				//Date betFromDate = dfInput.parse(pFromBetDate);
				//String fBetFromDate = dfOutput.format(betFromDate);

				Date betToDate = dfInput.parse(pToBetDate);
				String fBetToDate = dfOutput.format(betToDate);

				//betweenFromDt = "'"+fBetFromDate+"'";
				betweenToDt   = "'"+fBetToDate+"'";
			}else{
				betweenToDt = " ' ' ";
			}
			
			
			if(pFromOrdDate.length() > 0){
				
				Date ordFromDate = dfInput.parse(pFromOrdDate);
				String fOrdFromDate = dfOutput.format(ordFromDate);

				//Date ordToDate = dfInput.parse(pToOrdDate);
				//String fOrdToDate = dfOutput.format(ordToDate);

				fromOrderDate = "'"+fOrdFromDate+"'";
				//toOrderDate   = "'"+fOrdToDate+"'";
				
			}else{
				//betweenFromDt = " ' ' ";
				fromOrderDate = " ' ' ";
			}
			
			
			
			
			
			if(pToOrdDate.length() > 0){
				
				//Date ordFromDate = dfInput.parse(pFromOrdDate);
				//String fOrdFromDate = dfOutput.format(ordFromDate);

				Date ordToDate = dfInput.parse(pToOrdDate);
				String fOrdToDate = dfOutput.format(ordToDate);

				//fromOrderDate = "'"+fOrdFromDate+"'";
				toOrderDate   = "'"+fOrdToDate+"'";
				
			}else{
				//betweenFromDt = " ' ' ";
				toOrderDate = " ' ' ";
			}
			
			
			
			if(pFromProdDate.length() > 0){
				
				Date ordFromDate = dfInput.parse(pFromProdDate);
				String fOrdFromDate = dfOutput.format(ordFromDate);

				//Date ordToDate = dfInput.parse(pToOrdDate);
				//String fOrdToDate = dfOutput.format(ordToDate);

				fromProductionDate = "'"+fOrdFromDate+"'";
				//toOrderDate   = "'"+fOrdToDate+"'";
				
			}else{
				//betweenFromDt = " ' ' ";
				fromProductionDate = " ' ' ";
			}
			
			
		if(pToProdDate.length() > 0){
				
				Date ordFromDate = dfInput.parse(pToProdDate);
				String fOrdFromDate = dfOutput.format(ordFromDate);

				//Date ordToDate = dfInput.parse(pToOrdDate);
				//String fOrdToDate = dfOutput.format(ordToDate);

				toProductionDate = "'"+fOrdFromDate+"'";
				//toOrderDate   = "'"+fOrdToDate+"'";
				
			}else{
				//betweenFromDt = " ' ' ";
				toProductionDate = " ' ' ";
			}
			
			
			if(pFromDelvDate.length() > 0){
				
				Date disFromDate = dfInput.parse(pFromDelvDate);
				String fDisFromDate = dfOutput.format(disFromDate);

				//Date disToDate = dfInput.parse(pToDelvDate);
				//String fDisToDate = dfOutput.format(disToDate);

				fromDispatchDate = "'"+fDisFromDate+"'";
				//toDispatchDate   = "'"+fDisToDate+"'";
				
			}else{
				//betweenFromDt = " ' ' ";
				fromDispatchDate = " ' ' ";
			}
			
			
			if(pToDelvDate.length() > 0){
				
				//Date disFromDate = dfInput.parse(pFromDelvDate);
				//String fDisFromDate = dfOutput.format(disFromDate);

				Date disToDate = dfInput.parse(pToDelvDate);
				String fDisToDate = dfOutput.format(disToDate);

				//fromDispatchDate = "'"+fDisFromDate+"'";
				toDispatchDate   = "'"+fDisToDate+"'";
				
			}else{
				//betweenFromDt = " ' ' ";
				toDispatchDate = " ' ' ";
			}
			

			System.out.println("----------betweenFromDt------------------"+ betweenFromDt);
			System.out.println("----------betweenToDt--------------------"+ betweenToDt);
			System.out.println("----------fromOrderDate------------------"+ fromOrderDate);
			System.out.println("----------toOrderDate--------------------"+ toOrderDate);
			System.out.println("----------fromDispatchDate------------------"+ fromDispatchDate);
			System.out.println("----------toDispatchDate--------------------"+ toDispatchDate);
			
			

			// --------------------for cond2 --------stone type,shape,quality

			String whereCond2 = "";

			if (pStoneTypeIds.length() > 0) {
				whereCond2 = " ' StoneTypeId in (" + pStoneTypeIds + ") ";
				stoneTypeCond = " ' StoneTypeId in (" + pStoneTypeIds + ") ' ";
			}else{
				stoneTypeCond = " ' ' ";
			}
			
			
			
		
			

			if (pShapeIds.length() > 0) {
				if (whereCond2.length() > 0) {
					whereCond2 += " AND ShapeId in (" + pShapeIds + ") ";
				} else {
					whereCond2 = " ' ShapeId in (" + pShapeIds + ") ";
				}
				
				shapeCond = " ' ShapeId in (" + pShapeIds + ") ' ";
			}else{
				shapeCond = " ' ' ";
			}
			
			

			if (pQualityIds.length() > 0) {
				if (whereCond2.length() > 0) {
					whereCond2 += " AND QualityId in (" + pQualityIds + ") ";
				} else {
					whereCond2 = " ' QualityId in (" + pQualityIds + ") ";
				}
				
				qualityCond = " ' QualityId in (" + pQualityIds + ") ' ";
			}else{
				qualityCond = " ' ' ";
			}

			if(whereCond2.length() > 0){
				whereCond2 += "'";
			}else{
				whereCond2 = " ' ' ";
			}
			
			
			if (pExportInvoiceIds.length() > 0) {
				exportInvCond =  " ' MtId in (" + pExportInvoiceIds + ") ' ";
			}else if(pExportAllInvoiceIds.length() > 0){
				exportInvCond =  " ' MtId in (" + pExportAllInvoiceIds + ") ' ";
			}else{
				exportInvCond = " ' ' ";
			}
			

		
			
			if (pExportQualityIds.length() > 0) {
				exportQualityCond =  " ' qualityId in (" + pExportQualityIds + ") ' ";
			}else{
				exportQualityCond = " ' ' ";
			}
			
			if (pExportSizegroupIds.length() > 0) {
				exportSizegroupCond =  " ' sizegroupId in (" + pExportSizegroupIds + ") ' ";
			}else{
				exportSizegroupCond = " ' ' ";
			}
			
						
			
			if (pMtlInwardInvoiceIds.length() > 0) {
				mtlInwardInvCond =  " ' MtId in (" + pMtlInwardInvoiceIds + ") ' ";
			}else{
				mtlInwardInvCond = " ' ' ";
			}
			
			if (pMeltingInvoiceIds.length() > 0) {
				meltingInvCond =  " ' mtid in (" + pMeltingInvoiceIds + ") ' ";
			}else{
				meltingInvCond = " ' ' ";
			}
			
			
			if (pCompInwardInvoiceIds.length() > 0) {
				compInwardInvCond =  " ' MtId in (" + pCompInwardInvoiceIds + ") ' ";
			}else{
				compInwardInvCond = " ' ' ";
			}
			
			if (pQuotationInvoiceIds.length() > 0) {
				quotationInvCond =  " ' MtId in (" + pQuotationInvoiceIds + ") ' ";
			}else{
				quotationInvCond = " ' ' ";
			}
			
			
			if (pBagsIds.length() > 0) {
				bagCond =  " ' BagId in (" + pBagsIds + ") ' ";
			}else{
				bagCond = " ' ' ";
			}
			
			if (pEmployeeIds.length() > 0) {
				employeeCond =  " ' EmployeeId in (" + pEmployeeIds + ") ' ";
			}else{
				employeeCond = " ' ' ";
			}
			
			if (pExportJobInvoiceIds.length() > 0) {
				exportJobInvCond =  " ' MtId in (" + pExportJobInvoiceIds + ") ' ";
			}else{
				exportJobInvCond = " ' ' ";
			}
			
			if (pCategoryIds.length() > 0) {
				categoryCond = " ' CategId in (" + pCategoryIds + ") ' ";
			}else{
				categoryCond = " ' ' ";
			}
			
			if(pVersion.length()>0){
				String vVersion[] =pVersion.split(",");
				for(int i=0;i<vVersion.length;i++){
					if(versionCond.length()>0){
						versionCond=versionCond+" or version like ''%"+vVersion[i]+"%'' ";
					}else{
						versionCond=" version like ''%"+vVersion[i]+"%'' ";
					}
					
				}
				versionCond="'"+versionCond+"'";
			}else{
				versionCond = " ' ' ";
			}
			
			
			
			
			if (pSubCategoryIds.length() > 0) {
				subCategoryCond = " ' ScategId in (" + pSubCategoryIds + ") ' ";
			}else{
				subCategoryCond = " ' ' ";
			}
			
			if (pConceptIds.length() > 0) {
				conceptCond = " ' ConceptId in (" + pConceptIds + ") ' ";
			}else{
				conceptCond = " ' ' ";
			}
			
			if (pSubConceptIds.length() > 0) {
				subConceptCond = " ' SconceptId in (" + pSubConceptIds + ") ' ";
			}else{
				subConceptCond = " ' ' ";
			}
			
			
			
			if (pCostItemIds.length() > 0) {
				dtItemCond = " ' DtItemId in (" + pCostItemIds + ") ' ";
			}else if(pQuotDtIds.length() > 0){
				dtItemCond = " ' dtid in (" + pQuotDtIds + ") ' ";
			}else{
				dtItemCond = " ' ' ";
			}
			
			
			if (pDivisionIds.length() > 0) {
					divisionCond = " ' OrdDivisionId in (" + pDivisionIds + ") ' ";
			}else{
				divisionCond = " ' ' ";
			}
			
			if (pRegionIds.length() > 0) {
					regionCond = " ' PartyRegionId in (" + pRegionIds + ") ' ";
			}else{
				regionCond = " ' ' ";
			}
			
			
			if (pCustomerTypeIds.length() > 0) {
					
				customerTypeCond = " ' CustomerTypeId in (" + pCustomerTypeIds + ") ' ";
			}else{
				customerTypeCond = " ' ' ";
			}
			
			
			if (pBranchIds.length() > 0) {
				branchCond = " ' BranchId in (" + pBranchIds + ") ' ";
			}else{
				branchCond = " ' ' ";
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
			subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
			imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
			emailImgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/emailConcept/" ;
			barCodePath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/barcode/" ;
			
			
			/*
			 * fileName =
			 * "E:/workspace_jewels_demo/jewels/src/main/webapp/reports/bagprint.jasper";
			 * subRptPath = "E:/workspace_jewels_demo/jewels/src/main/webapp/reports/";
			 */
			
			
			
			pBal = "'"+pBal+"'";
			pNoBbc="'"+pNoBbc+"'";
			transactedFlg = "'"+transactedFlg+"'";
			
			String tranTypeCond ="";
			if(filterForm.equalsIgnoreCase("branchTrfOneLineSummary")) {
				tranTypeCond = "BRANCHTRF";
			}else {
				tranTypeCond = "STOCKTRF";
			}
			
			
			tranTypeCond = " '" + tranTypeCond + " ' ";
			

			InputStream input = new FileInputStream(new File(fileName));
			java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
		
			parametersMap.put("cond", whereCond);
			parametersMap.put("startdate", betweenFromDt);
			parametersMap.put("enddate", betweenToDt);	
			parametersMap.put("fromOrderDate", fromOrderDate);	
			parametersMap.put("toOrderDate", toOrderDate);	
			
			parametersMap.put("fromProductionDate", fromProductionDate);	
			parametersMap.put("toProductionDate", toProductionDate);
			parametersMap.put("delayCond", delayCond);
			
			parametersMap.put("fromDispatchDate", fromDispatchDate);	
			parametersMap.put("toDispatchDate", toDispatchDate);		
			parametersMap.put("cond1", whereCond2);		
			parametersMap.put("imagepath", imgPath);
			parametersMap.put("barCodePath", barCodePath);
			parametersMap.put("emailimgpath", emailImgPath);
			parametersMap.put("subrptpath", subRptPath);
			parametersMap.put("pBalance", pBal);
			parametersMap.put("noBbcCond", pNoBbc);
			parametersMap.put("fromPeriod", fromPeriod);
			parametersMap.put("toPeriod", toPeriod);			
			parametersMap.put("partyCond", partyCond);
 			parametersMap.put("orderTypeCond", orderTypeCond);
 			parametersMap.put("orderCond", orderCond);
 			parametersMap.put("designCond", designCond);
			parametersMap.put("departmentCond", departmentCond);
			parametersMap.put("locationCond", locationCond);
			parametersMap.put("marketinglocationCond", marketinglocationCond);
			parametersMap.put("metalCond", metalCond);
			
 			parametersMap.put("stoneTypeCond", stoneTypeCond);
			parametersMap.put("shapeCond", shapeCond);
			parametersMap.put("qualityCond", qualityCond);
			parametersMap.put("mtlInwardInvCond", mtlInwardInvCond);
			parametersMap.put("compInwardInvCond", compInwardInvCond);
			parametersMap.put("exportinvCond", exportInvCond);
  			parametersMap.put("quotationinvCond", quotationInvCond);
 			parametersMap.put("bagCond", bagCond);
			parametersMap.put("employeeCond", employeeCond);
			parametersMap.put("exportJobInvCond", exportJobInvCond);
			parametersMap.put("categCond", categoryCond);
			parametersMap.put("subCategCond", subCategoryCond);
			parametersMap.put("conceptCond", conceptCond);
			parametersMap.put("subConceptCond", subConceptCond);
			parametersMap.put("p_group1", pGroup1);
			parametersMap.put("p_group2", pGroup2);
			parametersMap.put("p_group3", pGroup3);
			parametersMap.put("p_format", pFormat);
			parametersMap.put("stoneInvCond", stoneInvCond);
			parametersMap.put("stonePurcInvCond", stonePurcInvCond);
			parametersMap.put("ordPartyCond", ordPartyCond);
			parametersMap.put("versionCond", versionCond);
			parametersMap.put("pChangingFormat", "'"+pChangingFormat+"'");
			parametersMap.put("componentCond", componentCond);
			parametersMap.put("meltingInvCond", meltingInvCond);
			parametersMap.put("priorityCond", priorityCond);
			parametersMap.put("pSetNoId", pSetNoId);
			parametersMap.put("dtItemCond", dtItemCond);
			parametersMap.put("transactedFlg", transactedFlg);
			parametersMap.put("settingTypeCond", settingTypeCond);
			parametersMap.put("sordItemCond", sordItemCond);
			parametersMap.put("exportQualityCond", exportQualityCond);
			parametersMap.put("exportSizegroupCond", exportSizegroupCond);
			parametersMap.put("compId", company.getId());
			parametersMap.put("purityCond",purityCond);
			parametersMap.put("fromDeptCond",fromDeptCond);
			parametersMap.put("toDeptCond",toDeptCond);
			parametersMap.put("deptIdCond",deptIdCond);
			parametersMap.put("inwardTypeCond",inwardTypeCond);
			parametersMap.put("tranTypeCond",tranTypeCond);
			parametersMap.put("divisionCond",divisionCond);
			parametersMap.put("regionCond",regionCond);
			parametersMap.put("customerTypeCond",customerTypeCond);
			parametersMap.put("branchCond",branchCond);
			
			
			
			//JasperReport jasperreport = JasperCompileManager.compileReport(input);
			//System.out.println(jasperreport.gets);
			
			
			JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
			
			
			
			if(pmOpt == 0){
			
			
			if(filterForm.equalsIgnoreCase("costingTag".trim())){
				//------------//--EXCEL--------//
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				File outputFile = new File("tags.xlsx");
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
				//Set configuration as you like it!!
				configuration.setDetectCellType(true);
				configuration.setWhitePageBackground(false);
				configuration.setCollapseRowSpan(false);
				configuration.setAutoFitPageHeight(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
				retVal = "-2";
			}else if(filterForm.equalsIgnoreCase("deptExportToExcel".trim())){
				//------------//--EXCEL--------//
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				File outputFile = new File("deptEx.xlsx");
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
				//Set configuration as you like it!!
				configuration.setDetectCellType(true);
				configuration.setWhitePageBackground(false);
				configuration.setCollapseRowSpan(false);
				configuration.setAutoFitPageHeight(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
				retVal = "-9";
			
			
			
			
		}else if(filterForm.equalsIgnoreCase("exportPackingList".trim())){
			//------------//--EXCEL--------//
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			File outputFile = new File("packinglist.xlsx");
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
			//Set configuration as you like it!!
			configuration.setDetectCellType(true);
			configuration.setWhitePageBackground(false);
			configuration.setCollapseRowSpan(false);
			configuration.setAutoFitPageHeight(true);
			configuration.setRemoveEmptySpaceBetweenColumns(true);
			configuration.setRemoveEmptySpaceBetweenRows(true);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			retVal = "-10";
		
		
		
		
	}else if(filterForm.equalsIgnoreCase("bagPrint".trim())){
					String bagPrintFileName = System.currentTimeMillis()+"2";
					File file = new File(uploadDirecotryPath + reportoutputdirectorypath + bagPrintFileName+".pdf");
					file.createNewFile();
					JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + bagPrintFileName+".pdf");
					
					String exportBagPrintFileName = System.currentTimeMillis()+""+principal.getName();
					Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +bagPrintFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportBagPrintFileName+".pdf");
					
					file.delete();
					
					retVal = "-3$"+exportBagPrintFileName;

			}else if(filterForm.equalsIgnoreCase("ordDeptAndStyleWise".trim())){
				
					String OrdStatusFileName = System.currentTimeMillis()+"3";
					File file = new File(uploadDirecotryPath + reportoutputdirectorypath + OrdStatusFileName+".pdf");
					file.createNewFile();
					JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + OrdStatusFileName+".pdf");
					
					String exportOrderStatusFileName = System.currentTimeMillis()+""+principal.getName();
					Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +OrdStatusFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportOrderStatusFileName+".pdf");
					
					file.delete();
					
					retVal = "-4$"+exportOrderStatusFileName;
				
			}else if(filterForm.equalsIgnoreCase("hmInvoice".trim())){
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				File outputFile = new File("hmInv.xlsx");
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
				//Set configuration as you like it!!
				configuration.setDetectCellType(true);
				configuration.setWhitePageBackground(false);
				configuration.setCollapseRowSpan(false);
				configuration.setAutoFitPageHeight(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
				retVal = "-6";
			}else if(filterForm.equalsIgnoreCase("jobCostingTag".trim())){
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				File outputFile = new File("exportJobCostingTag.xlsx");
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
				//Set configuration as you like it!!
				configuration.setDetectCellType(true);
				configuration.setWhitePageBackground(false);
				configuration.setCollapseRowSpan(false);
				configuration.setAutoFitPageHeight(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
				retVal = "-7";
			}else{
				//------------//--common--PDF--------//
				
					String commonFileName = System.currentTimeMillis()+"";
					commonFileName = commonFileName+xml;
					File file = new File(uploadDirecotryPath + reportoutputdirectorypath + commonFileName+".pdf");
					file.createNewFile();
					JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + commonFileName+".pdf");
					
					String exportCommonFileName = System.currentTimeMillis()+""+principal.getName()+xml;
					Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +commonFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportCommonFileName+".pdf");
					
					file.delete();
					
					retVal = "-1$"+exportCommonFileName;
			
			}
			}else if(pmOpt == 1){
				
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				String fileNm=xml+"-"+System.currentTimeMillis()+".xlsx";
				
				//String fileNm="purc.xlsx";
				File outputFile = new File(fileNm);
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
				//Set configuration as you like it!!
				configuration.setDetectCellType(true);
				configuration.setWhitePageBackground(false);
				configuration.setCollapseRowSpan(false);
				configuration.setAutoFitPageHeight(true);
				configuration.setRemoveEmptySpaceBetweenColumns(true);
				configuration.setRemoveEmptySpaceBetweenRows(true);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
				
			
				retVal = fileNm;
			
			
			
		}
		
			
			//------------//--PDF--------//
			//	JasperReport jasperreport = JasperCompileManager.compileReport(input);
			//	JasperPrint jp = JasperFillManager.fillReport(jasperreport,parametersMap, conn);
			//	JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + "tt.pdf");
					
			
			

		} catch (Exception e) {
			//File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
			//file.delete();
			System.out.println(e);
			retVal = "-5";
		} finally {
			conn.close();
		}

		return retVal;

	}
	
	

	
	
	
	@ResponseBody
	@RequestMapping(value = "/generateWipDetail")
	public String generateWipDetail(
			@RequestParam(value = "pSordMtId", required = false) Integer pSordMtId)
			throws SQLException, ParseException {
		
			
		 String str = "";
		
		 str=reportFilterService.getWipDetail(pSordMtId);
		 
		return str;
	}

	@ResponseBody
	@RequestMapping(value = "/display/reportFormat")
	public String reportFormat(String reportNm) {
		
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		Map<String, String> reportFormatMap = reportFilterService.getFilterFormList(reportNm);
		
		if(reportFormatMap.size()>0){
			sb.append("<select id=\"rptFormatId\" name=\"rptFormatId\" class=\"form-control\" onchange=\"javascript:changeReportFormat(this.value)\" style=\"background:#CEF87A\">");
			sb.append("<option value=\"\">- Select Report Format -</option>");
			for (Object key : reportFormatMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(reportFormatMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		else{
			retVal="-1";
		}
		
		return retVal;
	}	
	
	@ResponseBody
	@RequestMapping(value = "/generateWipStatus")
	public String generateWipStatus(
			@RequestParam(value = "pClientIds", required = false) String pClientIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pDepartmentIds", required = false) String pDepartmentIds,
			@RequestParam(value = "pPurityIds", required = false) String pPurityIds,
			@RequestParam(value = "pColorIds", required = false) String pColorIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pFromProdDate", required = false) String pFromProdDate,
			@RequestParam(value = "pToProdDate", required = false) String pToProdDate,
			@RequestParam(value = "pStyleIds", required = false) String pStyleIds,
			@RequestParam(value = "pDelayCondIds", required = false) Integer delayCond,
			@RequestParam(value = "pPriorityIds", required = false) String priorityCond)
			throws SQLException, ParseException {
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
		String partyCond = "";
		String orderTypeCond = "";
		String orderCond = "";
		String departmentCond ="";
		String purityCond = "";
		String colorCond ="";
		String styleCond="";
		
		
		
		
		if (pClientIds.length() > 0) {
		
			partyCond =  "  PartyId in (" + pClientIds + ") ";
		
		}
		
		if (pOrderTypeIds.length() > 0) {
				
			orderTypeCond = "  OrdTypeId in (" + pOrderTypeIds + ")  ";
		}
		if (pOrderIds.length() > 0) {
		
			orderCond = " sordMtId in (" + pOrderIds + ")  ";
		}
		if (pDepartmentIds.length() > 0) {
			
			departmentCond = " deptId in (" + pDepartmentIds + ")  ";
		}
		if (pPurityIds.length() > 0) {
			
			purityCond = " purityId in (" + pPurityIds + ")  ";
		}
		if (pColorIds.length() > 0) {
			
			colorCond = " colorId in (" + pColorIds + ")  ";
		}
		
		
		if(priorityCond.length()>0){
			
			priorityCond ="  priority in("+priorityCond+")  ";
			
		}
		String fProdToDate="";
		String fProdFromDate="";
		String fOrdFromDate="";
		String fOrdToDate="";
		
		if (pFromProdDate.length() > 0) {

			Date prodFromDate = dfInput.parse(pFromProdDate);
			fProdFromDate = dfOutput.format(prodFromDate);

			Date prodToDate = dfInput.parse(pToProdDate);
			fProdToDate = dfOutput.format(prodToDate);

						
		}
		
		
		if (pFromOrdDate.length() > 0) {

			Date ordFromDate = dfInput.parse(pFromOrdDate);
			fOrdFromDate = dfOutput.format(ordFromDate);

			Date ordToDate = dfInput.parse(pToOrdDate);
			fOrdToDate = dfOutput.format(ordToDate);

		
		}

		
		
		 String str = "";
		 
		 str=reportFilterService.getWipStatus(partyCond, orderTypeCond, orderCond, departmentCond, purityCond, colorCond, delayCond, styleCond,fOrdFromDate,fOrdToDate, fProdToDate, fProdFromDate,priorityCond);

		
		return str;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/wipCasting/dynamicTable")
	public String wipCastingTableStructure(
			@RequestParam(value = "pClientIds", required = false) String pClientIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pFromDelvDate", required = false) String pFromDelvDate,
			@RequestParam(value = "pToDelvDate", required = false) String pToDelvDate,
			@RequestParam(value = "pDepartmentIds", required = false) String pDepartmentIds,
			@RequestParam(value = "pWipCastingFormat", required = false) String pWipCastingFormat)
			throws SQLException, ParseException {
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");

		
		
		
		
		String partyCond = "";
		String orderTypeCond = "";
		String orderCond = "";
		String departmentCond ="";
		
		
		
		if (pClientIds.length() > 0) {
		
			partyCond =  "  PartyId in (" + pClientIds + ") ";
		
		}
		
		if (pOrderTypeIds.length() > 0) {
				
			orderTypeCond = "  OrdTypeId in (" + pOrderTypeIds + ")  ";
		}
		if (pOrderIds.length() > 0) {
		
			orderCond = " sordMtId in (" + pOrderIds + ")  ";
		}
		
	if (pDepartmentIds.length() > 0) {
			
			departmentCond = " deptId in (" + pDepartmentIds + ")  ";
		}
		
		
	
		String fOrdToDate="";
		String fOrdFromDate="";
		String fDelFromDate="";
		String fDelToDate="";
		

		if (pFromOrdDate.length() > 0) {

			Date ordFromDate = dfInput.parse(pFromOrdDate);
			fOrdFromDate = dfOutput.format(ordFromDate);

			Date ordToDate = dfInput.parse(pToOrdDate);
			fOrdToDate = dfOutput.format(ordToDate);

		
		}

		if (pFromDelvDate.length() > 0) {

			Date delFromDate = dfInput.parse(pFromDelvDate);
			 fDelFromDate = dfOutput.format(delFromDate);

			Date delToDate = dfInput.parse(pToDelvDate);
			fDelToDate = dfOutput.format(delToDate);

		
		}
		
		 String str = "";
		
		
		 
		 if(pWipCastingFormat.equalsIgnoreCase("wipCasting")) {
			 
			 @SuppressWarnings("unchecked")
				TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_clientCastBalsummarystructure(?,?,?,?,?,?,?,?) }");
				queryheader.setParameter(1, partyCond);
				queryheader.setParameter(2, orderTypeCond);
				queryheader.setParameter(3, orderCond);
				queryheader.setParameter(4, fOrdFromDate);
				queryheader.setParameter(5, fOrdToDate);
				queryheader.setParameter(6, fDelFromDate);
				queryheader.setParameter(7, fDelToDate);
				queryheader.setParameter(8, departmentCond);
				
				List<Object[]> objectHeader = queryheader.getResultList();
				
				
				Map<String, String> mapList = new LinkedHashMap<String, String>();
				
				
				for(int i=0;i<objectHeader.size();i++){
					
					Object xx =objectHeader.get(i);
					
					
					 mapList.put(xx.toString(),xx.toString() );
					
					
				}
				
		
				StringBuilder sb = new StringBuilder();
				
				sb.append("{\"rows\": [");
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb.append("{\"field\":\"")
					 .append(map.getKey())
					 .append("\",\"title\":\"")
					 .append(map.getValue())
					 .append("\"},");
				}
				
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1): str);
				str += "]}";
			 
		 } else if(pWipCastingFormat.equalsIgnoreCase("wipOrderWiseCastingBal")){
			 
			 
			 @SuppressWarnings("unchecked")
				TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_clientOrderCastBalsummarystructure(?,?,?,?,?,?,?,?) }");
				queryheader.setParameter(1, partyCond);
				queryheader.setParameter(2, orderTypeCond);
				queryheader.setParameter(3, orderCond);
				queryheader.setParameter(4, fOrdFromDate);
				queryheader.setParameter(5, fOrdToDate);
				queryheader.setParameter(6, fDelFromDate);
				queryheader.setParameter(7, fDelToDate);
				queryheader.setParameter(8, departmentCond);
				
				List<Object[]> objectHeader = queryheader.getResultList();
				
				
				Map<String, String> mapList = new LinkedHashMap<String, String>();
				
				
				for(int i=0;i<objectHeader.size();i++){
					
					Object xx =objectHeader.get(i);
					
					
					 mapList.put(xx.toString(),xx.toString() );
					
					
				}
				
				
	
				StringBuilder sb = new StringBuilder();
				
				sb.append("{\"rows\": [");
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb.append("{\"field\":\"")
					 .append(map.getKey())
					 .append("\",\"title\":\"")
					 .append(map.getValue())
					 .append("\"},");
				}
				
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1): str);
				str += "]}";
			 
		 }
		
		
		
		
		return str;
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/generateWipCasting")
	public String generateWipCasting(
			@RequestParam(value = "pClientIds", required = false) String pClientIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pFromDelvDate", required = false) String pFromDelvDate,
			@RequestParam(value = "pToDelvDate", required = false) String pToDelvDate,
			@RequestParam(value = "pDepartmentIds", required = false) String pDepartmentIds,
			@RequestParam(value = "pWipCastingFormat", required = false) String pWipFormat)
			throws SQLException, ParseException {
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("pClientIds    "+pClientIds);
		
		
		String partyCond = "";
		String orderTypeCond = "";
		String orderCond = "";
		String departmentCond ="";
		
		
		
		if (pClientIds.length() > 0) {
		
			partyCond =  "  PartyId in (" + pClientIds + ") ";
		
		}
		
		if (pOrderTypeIds.length() > 0) {
				
			orderTypeCond = "  OrdTypeId in (" + pOrderTypeIds + ")  ";
		}
		if (pOrderIds.length() > 0) {
		
			orderCond = " sordMtId in (" + pOrderIds + ")  ";
		}
		
	if (pDepartmentIds.length() > 0) {
			
			departmentCond = " deptId in (" + pDepartmentIds + ")  ";
		}
		
		
	
		String fOrdToDate="";
		String fOrdFromDate="";
		String fDelFromDate="";
		String fDelToDate="";
		

		if (pFromOrdDate.length() > 0) {

			Date ordFromDate = dfInput.parse(pFromOrdDate);
			fOrdFromDate = dfOutput.format(ordFromDate);

			Date ordToDate = dfInput.parse(pToOrdDate);
			fOrdToDate = dfOutput.format(ordToDate);

		
		}

		if (pFromDelvDate.length() > 0) {

			Date delFromDate = dfInput.parse(pFromDelvDate);
			 fDelFromDate = dfOutput.format(delFromDate);

			Date delToDate = dfInput.parse(pToDelvDate);
			fDelToDate = dfOutput.format(delToDate);

		
		}
		
		 String str = "";
		
		 str=reportFilterService.getWipCasting(partyCond, orderTypeCond, orderCond, fOrdFromDate,fOrdToDate ,fDelFromDate, fDelToDate,departmentCond,pWipFormat );
		 
		return str;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/generateWip")
	public String generateWip(
			@RequestParam(value = "pClientIds", required = false) String pClientIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pFromDelvDate", required = false) String pFromDelvDate,
			@RequestParam(value = "pToDelvDate", required = false) String pToDelvDate,
			@RequestParam(value = "pDepartmentIds", required = false) String pDepartmentIds,
			@RequestParam(value = "pWipFormat", required = false) String pWipFormat,Principal principal,
			@RequestParam(value = "pDivisionIds", required = false) String pDivisionIds,
			@RequestParam(value = "pRegionIds", required = false) String pRegionIds,
			@RequestParam(value = "pCustomerTypeIds", required = false) String pCustomerTypeIds)
			throws SQLException, ParseException {
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("pClientIds    "+pClientIds);
		
		
		String partyCond = "";
		String orderTypeCond = "";
		String orderCond = "";
		String departmentCond ="";
		String divisionCond = "";
		String regionCond = "";
		String customerTypeCond = "";
		
		
		if (pClientIds.length() > 0) {
		
			partyCond =  "  PartyId in (" + pClientIds + ") ";
		
		}
		
		if (pOrderTypeIds.length() > 0) {
				
			orderTypeCond = "  OrdTypeId in (" + pOrderTypeIds + ")  ";
		}
		if (pOrderIds.length() > 0) {
		
			orderCond = " sordMtId in (" + pOrderIds + ")  ";
		}
		
	if (pDepartmentIds.length() > 0) {
			
			departmentCond = " deptId in (" + pDepartmentIds + ")  ";
		}
	
	
	
	
	if (pDivisionIds.length() > 0) {
		divisionCond = " OrdDivisionId in (" + pDivisionIds + ") ";
}

if (pRegionIds.length() > 0) {
		regionCond = " PartyRegionId in (" + pRegionIds + ") ";
}

if (pCustomerTypeIds.length() > 0) {
		
	customerTypeCond = " CustomerTypeId in (" + pCustomerTypeIds + ") ";
}		
		
	
		String fOrdToDate="";
		String fOrdFromDate="";
		String fDelFromDate="";
		String fDelToDate="";
		

		if (pFromOrdDate.length() > 0) {

			Date ordFromDate = dfInput.parse(pFromOrdDate);
			fOrdFromDate = dfOutput.format(ordFromDate);

			Date ordToDate = dfInput.parse(pToOrdDate);
			fOrdToDate = dfOutput.format(ordToDate);

		
		}

		if (pFromDelvDate.length() > 0) {

			Date delFromDate = dfInput.parse(pFromDelvDate);
			 fDelFromDate = dfOutput.format(delFromDate);

			Date delToDate = dfInput.parse(pToDelvDate);
			fDelToDate = dfOutput.format(delToDate);

		
		}
		
		 String str = "";
		
		 str=reportFilterService.getWipSummary(partyCond, orderTypeCond, orderCond, fOrdFromDate,fOrdToDate ,fDelFromDate, fDelToDate,
				 departmentCond,pWipFormat,principal,customerTypeCond,regionCond,divisionCond );
		 
		return str;
	}
	
	
	@ResponseBody
	@RequestMapping("/wipSummary/dynamicTable")
	public String wipStatusTableStructure(
			@RequestParam(value = "pClientIds", required = false) String pClientIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pFromDelvDate", required = false) String pFromDelvDate,
			@RequestParam(value = "pToDelvDate", required = false) String pToDelvDate,
			@RequestParam(value = "pDepartmentIds", required = false) String pDepartmentIds,
			@RequestParam(value = "pWipFormat", required = false) String pWipFormat)
			throws SQLException, ParseException {
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");

		
		
		
		
		String partyCond = "";
		String orderTypeCond = "";
		String orderCond = "";
		String departmentCond ="";
		
		
		
		if (pClientIds.length() > 0) {
		
			partyCond =  "  PartyId in (" + pClientIds + ") ";
		
		}
		
		if (pOrderTypeIds.length() > 0) {
				
			orderTypeCond = "  OrdTypeId in (" + pOrderTypeIds + ")  ";
		}
		if (pOrderIds.length() > 0) {
		
			orderCond = " sordMtId in (" + pOrderIds + ")  ";
		}
		
	if (pDepartmentIds.length() > 0) {
			
			departmentCond = " deptId in (" + pDepartmentIds + ")  ";
		}
		
		
	
		String fOrdToDate="";
		String fOrdFromDate="";
		String fDelFromDate="";
		String fDelToDate="";
		

		if (pFromOrdDate.length() > 0) {

			Date ordFromDate = dfInput.parse(pFromOrdDate);
			fOrdFromDate = dfOutput.format(ordFromDate);

			Date ordToDate = dfInput.parse(pToOrdDate);
			fOrdToDate = dfOutput.format(ordToDate);

		
		}

		if (pFromDelvDate.length() > 0) {

			Date delFromDate = dfInput.parse(pFromDelvDate);
			 fDelFromDate = dfOutput.format(delFromDate);

			Date delToDate = dfInput.parse(pToDelvDate);
			fDelToDate = dfOutput.format(delToDate);

		
		}
		
		 String str = "";
		
		
		 
		 if(pWipFormat.equalsIgnoreCase("wipSummary")) {
			 
			 @SuppressWarnings("unchecked")
				TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wipsummarystructure(?,?,?,?,?,?,?,?) }");
				queryheader.setParameter(1, partyCond);
				queryheader.setParameter(2, orderTypeCond);
				queryheader.setParameter(3, orderCond);
				queryheader.setParameter(4, fOrdFromDate);
				queryheader.setParameter(5, fOrdToDate);
				queryheader.setParameter(6, fDelFromDate);
				queryheader.setParameter(7, fDelToDate);
				queryheader.setParameter(8, departmentCond);
				
				List<Object[]> objectHeader = queryheader.getResultList();
				
				
				Map<String, String> mapList = new LinkedHashMap<String, String>();
				
				
				for(int i=0;i<objectHeader.size();i++){
					
					Object xx =objectHeader.get(i);
					
					
					 mapList.put(xx.toString(),xx.toString() );
					
					
				}
				
				
			/*	mapList.put("invno", "Inv No");
				mapList.put("ordqty","Order Qty");
				mapList.put("dispatchdate","Dispatch Date");
			*/	
				

				StringBuilder sb = new StringBuilder();
				
				sb.append("{\"rows\": [");
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb.append("{\"field\":\"")
					 .append(map.getKey())
					 .append("\",\"title\":\"")
					 .append(map.getValue())
					 .append("\"},");
				}
				
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1): str);
				str += "]}";
			 
		 }else if(pWipFormat.equalsIgnoreCase("wipClientSummary")) {
			 
			 @SuppressWarnings("unchecked")
				TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_wipclientsummarystructure(?,?,?,?,?,?,?,?) }");
				queryheader.setParameter(1, partyCond);
				queryheader.setParameter(2, orderTypeCond);
				queryheader.setParameter(3, orderCond);
				queryheader.setParameter(4, fOrdFromDate);
				queryheader.setParameter(5, fOrdToDate);
				queryheader.setParameter(6, fDelFromDate);
				queryheader.setParameter(7, fDelToDate);
				queryheader.setParameter(8, departmentCond);
				
				List<Object[]> objectHeader = queryheader.getResultList();
				
				
				Map<String, String> mapList = new LinkedHashMap<String, String>();
				
				
				for(int i=0;i<objectHeader.size();i++){
					
					Object xx =objectHeader.get(i);
					
					
					 mapList.put(xx.toString(),xx.toString() );
					
					
				}
				
				
			/*	mapList.put("invno", "Inv No");
				mapList.put("ordqty","Order Qty");
				mapList.put("dispatchdate","Dispatch Date");
			*/	
				

				StringBuilder sb = new StringBuilder();
				
				sb.append("{\"rows\": [");
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb.append("{\"field\":\"")
					 .append(map.getKey())
					 .append("\",\"title\":\"")
					 .append(map.getValue())
					 .append("\"},");
				}
				
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1): str);
				str += "]}";
			 
		 }
		 else {
			 
			 
			 @SuppressWarnings("unchecked")
				TypedQuery<Object[]> queryheader = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_rep_stylewisewipstructure() }");
				
				
				List<Object[]> objectHeader = queryheader.getResultList();
				
				
				Map<String, String> mapList = new LinkedHashMap<String, String>();
				
				
				for(int i=0;i<objectHeader.size();i++){
					
					Object xx =objectHeader.get(i);
					
					
					 mapList.put(xx.toString(),xx.toString() );
					
					
				}
				
				
			/*	mapList.put("invno", "Inv No");
				mapList.put("ordqty","Order Qty");
				mapList.put("dispatchdate","Dispatch Date");
			*/	
				

				StringBuilder sb = new StringBuilder();
				
				sb.append("{\"rows\": [");
				for(Map.Entry<String, String> map : mapList.entrySet()){	
					sb.append("{\"field\":\"")
					 .append(map.getKey())
					 .append("\",\"title\":\"")
					 .append(map.getValue())
					 .append("\"},");
				}
				
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1): str);
				str += "]}";
			 
		 }
		
		
		
		
		return str;
		
		
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/generateOrderStatus")
	public String generateOrderStatus(
			@RequestParam(value = "pClientIds", required = false) String pClientIds,
			@RequestParam(value = "pOrderTypeIds", required = false) String pOrderTypeIds,
			@RequestParam(value = "pOrderIds", required = false) String pOrderIds,
			@RequestParam(value = "pFromOrdDate", required = false) String pFromOrdDate,
			@RequestParam(value = "pToOrdDate", required = false) String pToOrdDate,
			@RequestParam(value = "pFromDelvDate", required = false) String pFromDelvDate,
			@RequestParam(value = "pToDelvDate", required = false) String pToDelvDate,
			@RequestParam(value = "pDepartmentIds", required = false) String pDepartmentIds,
			@RequestParam(value = "pWipFormat", required = false) String pWipFormat)
			throws SQLException, ParseException {
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
		String partyCond = "";
		String orderTypeCond = "";
		String orderCond = "";
		String departmentCond ="";
		
		
		
		if (pClientIds.length() > 0) {
		
			partyCond =  "  PartyId in (" + pClientIds + ") ";
		
		}
		
		if (pOrderTypeIds.length() > 0) {
				
			orderTypeCond = "  OrdTypeId in (" + pOrderTypeIds + ")  ";
		}
		if (pOrderIds.length() > 0) {
		
			orderCond = " sordMtId in (" + pOrderIds + ")  ";
		}
		
	if (pDepartmentIds.length() > 0) {
			
			departmentCond = " deptId in (" + pDepartmentIds + ")  ";
		}
		
		
	
		String fOrdToDate="";
		String fOrdFromDate="";
		String fDelFromDate="";
		String fDelToDate="";
		

		if (pFromOrdDate.length() > 0) {

			Date ordFromDate = dfInput.parse(pFromOrdDate);
			fOrdFromDate = dfOutput.format(ordFromDate);

			Date ordToDate = dfInput.parse(pToOrdDate);
			fOrdToDate = dfOutput.format(ordToDate);

		
		}

		if (pFromDelvDate.length() > 0) {

			Date delFromDate = dfInput.parse(pFromDelvDate);
			 fDelFromDate = dfOutput.format(delFromDate);

			Date delToDate = dfInput.parse(pToDelvDate);
			fDelToDate = dfOutput.format(delToDate);

		
		}
		
		 String str = "";
		
		 str=reportFilterService.getOrderStatus(partyCond, orderTypeCond, orderCond, fOrdFromDate,fOrdToDate ,fDelFromDate, fDelToDate,departmentCond);
		 
		return str;
	}
	
	
	@RequestMapping("/common/transactionReport")
	@ResponseBody
	public String jobWorkIssueReport(@RequestParam(value = "mtId", required = false) Integer mtid, Principal principal,
			@RequestParam(value = "xml", required = false) String xml,@RequestParam(value = "opt", required = false) String opt
			) throws SQLException {
	

		
		
	
		String retVal = "-1";
		String fileName = "";
		
		Connection conn = null;
		
		
try {
	
	conn = Utils.getConnection();
		
	
	fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") + xml + ".jasper";
	
	
	InputStream input = new FileInputStream(new File(fileName));
	java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();
	
	parametersMap.put("mtId", mtid);
	
	JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
	
	//------------//--common--PDF--------//
	
	if(opt.equalsIgnoreCase("1")) {
		
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jp));
		String fileNm=xml+"-"+System.currentTimeMillis()+".xlsx";
		
		//String fileNm="purc.xlsx";
		File outputFile = new File(fileNm);
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(uploadDirecotryPath + reportoutputdirectorypath +outputFile));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
		//Set configuration as you like it!!
		configuration.setDetectCellType(true);
		configuration.setWhitePageBackground(false);
		configuration.setCollapseRowSpan(false);
		configuration.setAutoFitPageHeight(true);
		configuration.setRemoveEmptySpaceBetweenColumns(true);
		configuration.setRemoveEmptySpaceBetweenRows(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
		
	
		retVal = fileNm;
	
	}else {
		
		String newFileName = System.currentTimeMillis()+"";
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath + newFileName+".pdf");
		file.createNewFile();
		JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath + newFileName+".pdf");
		
		String exportFileName = System.currentTimeMillis()+""+principal.getName();
		Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +newFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +exportFileName+".pdf");
		
		file.delete();
		
		retVal = exportFileName;
		
		
	}

	
} catch (Exception e) {
	// TODO: handle exception
}
finally {
	conn.close();
}	
	return retVal;

	}
	
	
		
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
		
	
}

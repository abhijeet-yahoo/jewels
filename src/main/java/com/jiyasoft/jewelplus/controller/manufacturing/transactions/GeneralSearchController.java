package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ChangingList;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.GeneralSearch;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCompTran;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondBaggingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IGeneralSearchService;
import com.mysema.query.Tuple;


@Controller
@RequestMapping("/manufacturing/transactions")
public class GeneralSearchController {

	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IOrderTypeService orderTypeService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private IGeneralSearchService generalSearchService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IDiamondBaggingService diamondBaggingService;
	
	@Autowired
	private ICompTranService compTranService;
	
	
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

	@Autowired
	private IReportFilterService reportFilterService;
	
	@ModelAttribute("reportFilter")
	public ReportFilter constructReportFilter() {
		return new ReportFilter();
	}
	
	
	@ModelAttribute("bagMt")
	public BagMt construct(){
		return new BagMt();
	}
	
	
	@RequestMapping("/generalSearch")
	public String generalSearch(Model model){
		model = populateModel(model);
		return "generalSearch"; 
	}
	
	
	public Model populateModel(Model model){
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("orderTypeMap", orderTypeService.getOrderTypeList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
		

		ReportFilter reportFilter = reportFilterService.findByName("bagHistoryReport");
		model.addAttribute("xml",reportFilter.getXml());
		return model;
	}
	
	
	DecimalFormat df = new DecimalFormat("#.###");
	
	@RequestMapping("/generalSearch/listing")
	@ResponseBody
	public String generalSearchListing(
			@RequestParam(value = "pPartyId", required = false) String pPartyId,
			@RequestParam(value = "pOrderTypeId", required = false) Integer pOrderTypeId,
			@RequestParam(value = "pOrderNo", required = false) String pOrderNo,
			@RequestParam(value = "pOrderRefNo", required = false) String pOrderRefNo,
			@RequestParam(value = "pStyleNo", required = false) String pStyleNo,
			@RequestParam(value = "pPurityId", required = false) Integer pPurityId,
			@RequestParam(value = "pColorId", required = false) Integer pColorId,
			@RequestParam(value = "pBagNm", required = false) String pBagNm,
			@RequestParam(value = "pDepartment", required = false) Integer pDepartment,
			@RequestParam(value = "pOrdRef", required = false) String pOrdRef,
			@RequestParam(value = "exportFlg", required = false) Boolean exportFlg) {
		
		
		
			StringBuilder sb = new StringBuilder();
		
		
			Integer styleId = 0;
			String tempStyleNo = "";
			String tempVersion = "";
			
	

			if(pStyleNo.length() > 0){
			Design design = designService.findByStyleNoAndVersion(pStyleNo.trim(), tempVersion.trim());
			styleId = design.getId();
			
			}
			
		
			List<GeneralSearch> generalSearchs = generalSearchService.getGeneralSearchList(pPartyId, pOrderTypeId, pOrderNo.trim(), pOrderRefNo.trim(), styleId, 
					pPurityId, pColorId, pBagNm.trim(), pDepartment,pOrdRef.trim(),exportFlg);
		
		sb.append("{\"total\":").append(generalSearchs.size()).append(",\"rows\": [");		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		for (GeneralSearch generalSearch : generalSearchs) {
			
			sb.append("{\"id\":\"")
					.append(generalSearch.getId())
					.append("\",\"bagId\":\"")
					.append(generalSearch.getBagId() 		!= null ? generalSearch.getBagId() : "")
					.append("\",\"partyNm\":\"")
					.append(generalSearch.getPartyNm() 		!= null ? generalSearch.getPartyNm() : "")
					.append("\",\"orderTypeNm\":\"")
					.append(generalSearch.getOrderType() 	!= null ? generalSearch.getOrderType() : "")
					.append("\",\"orderNo\":\"")
					.append(generalSearch.getOrderNo() 		!= null ? generalSearch.getOrderNo() : "")
					.append("\",\"orderRefNo\":\"")
					.append(generalSearch.getOrderRefNo() 	!= null ? generalSearch.getOrderRefNo() : "")
					.append("\",\"bagNm\":\"")
					.append(generalSearch.getBagNm() 		!= null ? generalSearch.getBagNm() : "")
					.append("\",\"styleNo\":\"")
					.append(generalSearch.getStyleNo() 		!= null ? generalSearch.getStyleNo() : "")
					.append("\",\"kt\":\"")
					.append(generalSearch.getPurityNm() 	!= null ? generalSearch.getPurityNm() : "")
					.append("\",\"color\":\"")
					.append(generalSearch.getColorNm() 		!= null ? generalSearch.getColorNm() : "")
					.append("\",\"ordPcs\":\"")
					.append(generalSearch.getOrdPcs() 		!= null ? generalSearch.getOrdPcs() : "")
					.append("\",\"bagPcs\":\"")
					.append(generalSearch.getBagPcs() 		!= null ? generalSearch.getBagPcs() : "")
					.append("\",\"grossWt\":\"")
					.append(generalSearch.getGrossWt() 		!= null ? generalSearch.getGrossWt() : "")
					.append("\",\"date\":\"")
					.append(generalSearch.getDate() 		!= null ? dateFormat.format(generalSearch.getDate()) : "" )
					.append("\",\"departmentNm\":\"")
					.append(generalSearch.getDeptNm() 		!= null ? generalSearch.getDeptNm() : "")
					.append("\",\"itemNo\":\"")
					.append(generalSearch.getDtItem() 		!= null ? generalSearch.getDtItem() : "")
					.append("\",\"sizeNm\":\"")
					.append(generalSearch.getSizeNm() 		!= null ? generalSearch.getSizeNm() : "")
					.append("\",\"dtRefNo\":\"")
					.append(generalSearch.getDtRefNo() 		!= null ? generalSearch.getDtRefNo() : "")
					.append("\",\"dtOrdref\":\"")
					.append(generalSearch.getDtOrdRef() 		!= null ? generalSearch.getDtOrdRef() : "")
					.append("\",\"expInv\":\"")
					.append(generalSearch.getExpInv() 		!= null ? generalSearch.getExpInv() : "")
					.append("\",\"expDate\":\"")
					.append(generalSearch.getInvDateStr()	    != null ? generalSearch.getInvDateStr() : "")
					.append("\",\"tagPrice\":\"")
					.append(generalSearch.getFinalPrice()   != null ? Double.parseDouble(df.format(generalSearch.getFinalPrice())) : "")
					.append("\",\"bagStatus\":\"")
					.append(generalSearch.getBagCloseFlg() ? "Close" : "Open")
					.append("\",\"diaPcs\":\"")
					.append(generalSearch.getDiaPcs()   != null ? generalSearch.getDiaPcs() : "")
					.append("\",\"diaWt\":\"")
					.append(generalSearch.getDiaWt()   != null ? generalSearch.getDiaWt() : "")
					.append("\",\"colPcs\":\"")
					.append(generalSearch.getColPcs()   != null ? generalSearch.getColPcs() : "")
					.append("\",\"colWt\":\"")
					.append(generalSearch.getColWt()   != null ? generalSearch.getColWt() : "")
					.append("\",\"czPcs\":\"")
					.append(generalSearch.getCzPcs()   != null ? generalSearch.getCzPcs() : "")
					.append("\",\"czWt\":\"")
					.append(generalSearch.getCzWt()   != null ? generalSearch.getCzWt() : "")
					
					.append("\",\"action1\":\"")
					 .append("<a onClick='javascript:popBagHistory()'")
					.append(" class='btn btn-xs btn-info' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Bag History</a>")
					.append("\"},");
			
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		System.out.println("str   "+str);
		
		return str;
	}
	
	
	
	@RequestMapping("/generalSearch/diamondBreakup/listing")
	@ResponseBody
	public String impStnAdjDetails(
			@RequestParam(value = "bagId", required = false) Integer bagId) {
		
		StringBuilder sb = new StringBuilder();
		
		
		List<ChangingList> changingLists = diamondBaggingService.getListForDiamondChanging(bagId);
		DecimalFormat df = new DecimalFormat("#.###");
		DecimalFormat df2 = new DecimalFormat("#.##");
		sb.append("{\"total\":").append(1000).append(",\"rows\": [");
		
		
		
		for(ChangingList diamondBagging:changingLists){
			
			if(diamondBagging.getBalCarat() > 0 && diamondBagging.getBalStone() > 0){
			
				sb.append("{\"id\":\"")
						.append(diamondBagging.getId())
						.append("\",\"stoneType\":\"")
						.append((diamondBagging.getStoneType() != null ? diamondBagging.getStoneType().getName() : ""))
						.append("\",\"shape\":\"")
						.append((diamondBagging.getShape() != null ? diamondBagging.getShape().getName() : ""))
						.append("\",\"subShape\":\"")
						.append((diamondBagging.getSubShape() != null ? diamondBagging.getSubShape().getName() : ""))
						.append("\",\"quality\":\"")
						.append((diamondBagging.getQuality() != null ? diamondBagging.getQuality().getName() : ""))
						.append("\",\"mm\":\"")
						.append((diamondBagging.getSize() != null ? diamondBagging.getSize() : ""))
						.append("\",\"sieve\":\"")
						.append((diamondBagging.getSieve() != null ? diamondBagging.getSieve() : ""))
						.append("\",\"sizeGroup\":\"")
						.append((diamondBagging.getSizeGroup() != null ? diamondBagging.getSizeGroup().getName() : ""))
						.append("\",\"stones\":\"")
						.append(diamondBagging.getBalStone())
						.append("\",\"carat\":\"")
						.append(df.format(diamondBagging.getBalCarat()))
						.append("\",\"rate\":\"")
						.append(diamondBagging.getStnRate())
						.append("\",\"value\":\"")
						.append(df2.format(diamondBagging.getStnRate()*diamondBagging.getBalCarat()))
						.append("\",\"setting\":\"")
						.append((diamondBagging.getSetting() != null ? diamondBagging.getSetting().getName() : ""))
						.append("\",\"setType\":\"")
						.append((diamondBagging.getSettingType() != null ? diamondBagging.getSettingType().getName() : ""))
						.append("\"},");
				
				}
					
			}
			
			
			
	
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		return str;
		
	}
	
	
	
	
	@RequestMapping("/generalSearch/compBreakup/listing")
	@ResponseBody
	public String comnpBreakupDetails(
			@RequestParam(value = "bagId", required = false) Integer bagId) {
		
		StringBuilder sb = new StringBuilder();
		
		QCompTran qCompTran = QCompTran.compTran;
		List<Tuple> tupleList = compTranService.getCompTranTupleList(bagId);
		sb.append("{\"total\":").append(tupleList.size()).append(",\"rows\": [");
		Double compWt=0.0;
		for(Tuple tuple:tupleList){
			
			if(tuple.get(qCompTran.balance.sum())>=0){
				continue;
			}else{
				compWt=-tuple.get(qCompTran.balance.sum());
			}
			
			sb.append("{\"id\":\"")
				.append(tuple.get(qCompTran.id))
				.append("\",\"component\":\"")
				.append((tuple.get(qCompTran.component.name) != null ? tuple.get(qCompTran.component.name) : "" ))
				.append("\",\"purity\":\"")
				.append((tuple.get(qCompTran.purity.name) != null ? tuple.get(qCompTran.purity.name) : "" ))
				.append("\",\"color\":\"")
				.append((tuple.get(qCompTran.color.name) != null ? tuple.get(qCompTran.color.name) : ""))
				.append("\",\"metalWt\":\"")
				.append(compWt)
				.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		return str;
		
	}
	
	
	
	@RequestMapping("/generalSearch/jobBag/list")
	@ResponseBody
	public String bagList(@RequestParam(value = "term", required = false) String name) {
		Page<BagMt> bagMtList = bagMtService.findByName(15, 0, "name", "ASC", name.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (BagMt bagMt : bagMtList) {
			sb.append("\"")
				.append(bagMt.getName())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	
	@RequestMapping("/generalSearch/orderInv/list")
	@ResponseBody
	public String orderInvList(@RequestParam(value = "term", required = false) String invNo) {
		Page<OrderMt> orderMtList = orderMtService.findByInvNo(15, 0, "invNo", "ASC", invNo.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (OrderMt orderMt : orderMtList) {
			sb.append("\"")
				.append(orderMt.getInvNo())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	
	@RequestMapping("/fromBagSearch/bagHistory/{id}")
	public String bagHistory(@PathVariable int id){
		return "fromBagSearch/bagHistory";
	}
	
	
	
	
	
 }

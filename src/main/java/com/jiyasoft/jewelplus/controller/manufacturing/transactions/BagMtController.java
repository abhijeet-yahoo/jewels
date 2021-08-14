package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.io.File;
import java.security.Principal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QBagGenQty;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/transactions")
@Controller
public class BagMtController {
	
	@Autowired
	private IBagMtService bagMtService;

	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;


	@ModelAttribute("bagMt")
	public BagMt construct() {
		return new BagMt();
	}

	@RequestMapping("/bagMt")
	public String users(Model model) {
		//model.addAttribute("bagMt", bagMtService.findAll());

		return "bagMt";
	}
	
	
	
	
	

	@RequestMapping("/bagMt/listing")
	@ResponseBody
	public String bagMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "pOrdDt", required = false) Integer pOrdDt) {

		return bagMtService.bagMtListing(limit, offset, sort, order, search, pInvNo, pOrdDt);
	}

	@RequestMapping("/bagMt/add")
	public String add(Model model) {
		return "bagMt/add";
	}

	@RequestMapping(value = "/bagMt/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("bagMt") BagMt bagMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/bagMt/add.html";

		if (result.hasErrors()) {
			return "bagMt/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			bagMt.setCreatedBy(principal.getName());
			bagMt.setCreatedDate(new java.util.Date());
		} else {
			bagMt.setId(id);
			bagMt.setModiBy(principal.getName());
			bagMt.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/transactions/bagMt.html";
		}
		
		bagMtService.save(bagMt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/bagMt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		BagMt bagMt = bagMtService.findOne(id);
		model.addAttribute("bagMt", bagMt);

		return "bagMt/add";
	}

	@RequestMapping("/bagMt/delete/{id}")
	public String delete(@PathVariable int id) {
		bagMtService.delete(id);

		return "redirect:/manufacturing/transactions/bagMt.html";
	}

	@RequestMapping("/bagMtAvailable")
	@ResponseBody
	public String bagMtAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean bagMtAvailable = true;

		if (id == null) {
			bagMtAvailable = (bagMtService.findByName(name) == null);
		} else {
			BagMt bagMt = bagMtService.findOne(id);
			if (!(name.equalsIgnoreCase(bagMt.getName()))) {
				bagMtAvailable = (bagMtService.findByName(name) == null);
			}
		}

		return bagMtAvailable.toString();
	}

	@RequestMapping("/bag/order/list")
	@ResponseBody
	public String styleNoList(@RequestParam(value = "term", required = false) String invNo) {
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
	
	
	
	@RequestMapping("/generateBarCode")
	@ResponseBody
	public String generateBarCode(@RequestParam(value = "vBagNo", required = false) String vBagNo,
			@RequestParam(value = "barcodeMtId", required = false) Integer barcodeMtId, Principal principal)
			throws ParseException {

		String retVal = "-1";

		String barcodeuploadFilePath = uploadDirecotryPath + File.separator + uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "StockBarcode" + File.separator;

		synchronized (this) {
			String[] BagDtl = vBagNo.split(",");
			for (int i = 0; i < BagDtl.length; i++) {

				retVal = bagMtService.generateBarcode(BagDtl[i], barcodeuploadFilePath, principal, barcodeMtId);

			}
		}

		return retVal;

	}
	

	@RequestMapping("jobBag/add")
	@ResponseBody
	public String jobBagAdd(
		@RequestParam(value = "bagQty", required = false) Double bagQty,
		@RequestParam(value = "pInvNo", required = false) String pInvNo,
		@RequestParam(value = "pODIds", required = false) String pODIds,
		Principal principal) {
		
		
		String barcodeuploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator +"barcode"+File.separator;


		Boolean bagParam =false;
		if(bagQty ==-2 ){
			bagParam=true;
		}
				
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		Double bagCount = 0.0;
		Integer maxSrno = null;
		Double balQty = 0.0;
		Double tmpQty = 0.0;

		Calendar date = new GregorianCalendar();
		String vYear = "" + date.get(Calendar.YEAR);
		vYear = vYear.substring(2);
		
		OrderMt orderMt = orderMtService.findByInvNoAndDeactive(pInvNo, false); 
		List<OrderDt> orderDtList = orderDtService.findByOrderMtAndDeactive(orderMt, false);

		BagMt bagMt = null;
		synchronized(this) {
			maxSrno = bagMtService.getMaxSrno();
			maxSrno = (maxSrno == null ? 0 : maxSrno);

			for (OrderDt orderDt : orderDtList) {
				if (orderDt.getPcs() != null) {
					if (pODIds.trim().length() > 0) {
						if (pODIds.indexOf(""+orderDt.getId()) == -1) {
							continue;
						}
					}

					balQty = (orderDt.getPcs() - bagMtService.getGenQty(orderMt.getId(), orderDt.getId()));
					if (balQty <= 0) {
						continue;						
					}

					if(bagParam.equals(false)){
						bagCount = (balQty / (bagQty == -1 ? balQty : bagQty));
					}else{
						
						QOrderStnDt qOrderStnDt = QOrderStnDt.orderStnDt;
						JPAQuery query = new JPAQuery(entityManager);
						Double totCarat = 0.0;

						List<Double> genCaratSum = query
							.from(qOrderStnDt)
							.where(qOrderStnDt.deactive.eq(false).and(qOrderStnDt.orderMt.id.eq(orderMt.getId()).and(qOrderStnDt.orderDt.id.eq(orderDt.getId()))))
							.list(qOrderStnDt.carat.sum());

						for (Double vCarat : genCaratSum) {
							totCarat = (vCarat == null ? 0.0 : vCarat);
						}
						
						
						QBagGenQty qBagGenQty =QBagGenQty.bagGenQty;
						
						Double vBagQty = 0.0;

						List<Double> genBagQty = query
							.from(qBagGenQty)
							.where(qBagGenQty.deactive.eq(false).and(qBagGenQty.fromCtsWt.loe(totCarat)).and(qBagGenQty.toCtsWt.goe(totCarat)))
							.list(qBagGenQty.qty);

						for (Double vGenBagQty : genBagQty) {
							vBagQty = (vGenBagQty == null ? 0.0 : vGenBagQty);
						}
						
						
						if (vBagQty <= 0) {
							continue;						
						}
						bagQty =vBagQty;
						
						bagCount = (balQty / (bagQty >balQty ? balQty : bagQty));
						
						
						
						
					}
					bagCount = Math.ceil(bagCount);

					tmpQty = 0.0;
					for (int x=0; x<bagCount; x++) {
						bagMt = new BagMt();
						bagMt.setSeqNo(++maxSrno);
						
						int si = maxSrno.toString().length();
						
						String bagSr = null;
						
						switch (si) {
						case 1:
							bagSr = "000"+maxSrno;
							break;
							
						case 2:
							bagSr = "00"+maxSrno;
							break;
							
						case 3:
							bagSr = "0"+maxSrno;
							break;
							
						default:
							bagSr = maxSrno.toString();
							break;
						}
						if(orderMt.getOrderType().getBagPrefix() !=null && !orderMt.getOrderType().getBagPrefix().isEmpty()){
						
						bagMt.setName(orderMt.getOrderType().getBagPrefix() + "-" + (bagSr) + "-" + vYear);
						}else{
							
							bagMt.setName(bagSr + "-" + vYear);
							
						}
						
						bagMt.setOrderMt(orderMt);
						bagMt.setOrderDt(orderDt);
						bagMt.setQty((bagQty == -1 ? balQty : (balQty-tmpQty) > bagQty ? bagQty : (balQty-tmpQty)));

						bagMt.setCreatedBy(principal.getName());
						bagMt.setCreatedDate(new java.util.Date());
						bagMtService.save(bagMt);
						
						Utils.barcodeGeneration(bagMt.getId(), bagMt.getName(), barcodeuploadFilePath);
						

						sb.append("{\"jobBagNo\":\"")
							.append(orderMt.getOrderType().getCode() + "/" + (bagSr) + "/" + vYear)
							.append("\",\"pcs\":\"")
							.append((bagQty == -1 ? balQty : (balQty-tmpQty) > bagQty ? bagQty : (balQty-tmpQty)))
							.append("\"},");			

						tmpQty += (bagQty == -1 ? balQty : bagQty);
					}
					
					if(bagParam.equals(true)){
						bagQty=0.0;	
					}
					
				}
				
				
				
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	

	@RequestMapping("/setOpening/listing")
	@ResponseBody
	public String setOpeningListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "opt", required = false) String opt) {
	
		return bagMtService.setOpeningListing(limit, offset, sort, order, search, pInvNo,opt);
	
	}
	
	
	
	
	//----//----BagMt---- listing------//
	
	@RequestMapping("/bagMt/report/listing")
	@ResponseBody
	public String bagMtReportListing(Model model,
			@RequestParam(value = "orderIds", required = false) String orderIds){
		
		StringBuilder sb = new StringBuilder();
		
		
		List<BagMt> bagMts = bagMtService.findBagsByOrderMt(orderIds);
		
		sb.append("{\"total\":").append(bagMts.size()).append(",\"rows\": [");
		
		for(BagMt bagMt:bagMts){
			
			sb.append("{\"id\":\"")
			.append(bagMt.getId())
			.append("\",\"name\":\"")
			.append(bagMt.getName())
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
		
	}
	
	@RequestMapping("/splitBagSave")
	@ResponseBody
	public String splitBagSave(Model model,
			@RequestParam(value = "bagNo") String bagNo,
			@RequestParam(value = "bgQty") Double bagQty,
			@RequestParam(value = "splitQty") Double splitQty,
			@RequestParam(value = "weight") Double weight,
			@RequestParam(value = "splitWeight") Double splitWeight,
			
			Principal principal){
		
		
		String retVal="";
		
		
		String barcodeuploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator +"barcode"+File.separator;
		
		synchronized (this) {
			retVal=bagMtService.bagSplit(bagNo, bagQty, splitQty, weight,principal,splitWeight,barcodeuploadFilePath);	
		}
		
		return retVal;
	}
	
	
	@RequestMapping("/bagDt/listing")
	@ResponseBody
	public String bagDtListing(Model model,
			@RequestParam(value = "pInvNo", required = false) String pInvNo, Principal principal){
		
		return bagMtService.bagDetailsList(pInvNo, principal);
		
	}
	
}

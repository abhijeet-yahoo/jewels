package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientKtConvMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientKtConvService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientStampService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterFLService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class OrderDtService implements IOrderDtService {

	@Autowired
	private IOrderDtRepository orderDtRepository;
	
	@Autowired
	private IClientWastageService clientWastageService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IDesignComponentService designComponentService;
	
	@Autowired
	private IOrderMetalService orderMetalService;

	@Autowired
	private IOrderLabDtService orderLabDtService;
	
	@Autowired
	private IOrderMetalRateService orderMetalRateService;
	
	@Autowired
	private IOrderStnDtService orderStnDtService;
	
	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private ISettingChargeService settingChargeService;
	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;
	
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private IFindingRateMastService findingRateMastService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private IOrderCompDtService orderCompDtService;
	
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IQuotMetalService quotMetalService;
	
	@Autowired
	private IQuotStnDtService quotStnDtService;
	
	@Autowired
	private IQuotCompDtService quotCompDtService;
	
	@Autowired
	private IQuotLabDtService quotLabDtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IHandlingMasterFLService handlingMasterFLService;

	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IClientKtConvService clientKtConvService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IShapeService shapeService;

	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IProductSizeService productSizeService;
	
	@Autowired
	private IClientStampService clientStampService;
	
	@Autowired
	private IDesignMetalService designMetalService;

	
	@Override
	public List<OrderDt> findAll() {
		return orderDtRepository.findAll();
	}

	@Override
	public Page<OrderDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		QOrderDt qOrderDt = QOrderDt.orderDt;
		BooleanExpression expression = null;

		expression = qOrderDt.deactive.eq(false);

		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return orderDtRepository.findAll(expression, new PageRequest(page, limit, Direction.DESC, sort));
	}

	@Override
	public void save(OrderDt orderDt) {
		orderDtRepository.save(orderDt);
	}

	@Override
	public String delete(int id) {
		
		String retVal="-1";
		OrderDt orderDt = orderDtRepository.findOne(id);
		
		List<StoneTran> stoneTrans =  stoneTranService.findBySordDtIdAndDeactive(orderDt.getId(), false);
		if(stoneTrans.size() > 0) {
			return "2";
		}
		
		List<BagMt> bagMtList = bagMtService.findByOrderMtAndOrderDt(orderDt.getOrderMt(), orderDt);
		
		Boolean flag = false;
		
		if(bagMtList.size() > 0){
			flag = true;
			return "1";
		}else{	
			
			orderDt.setDeactive(true);
			orderDt.setDeactiveDt(new java.util.Date());
		
			List<OrderMetal>orderMetals =orderMetalService.findByOrderDtAndDeactive(orderDt, false);
			for(OrderMetal orderMetal :orderMetals){
				orderMetal.setDeactive(true);
				orderMetal.setDeactiveDt(new Date());
				orderMetalService.save(orderMetal);
			}
			
			/*List<OrderStnDt> orderStnDtList = orderStnDtService.findByOrderMtAndOrderDt(orderDt.getOrderMt(), orderDt);*/
			List<OrderStnDt> orderStnDtList = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
			for (OrderStnDt orderStnDt : orderStnDtList) {
				orderStnDt.setDeactive(true);
				orderStnDt.setDeactiveDt(new java.util.Date());
				orderStnDtService.save(orderStnDt);
			}

			List<OrderCompDt> orderCompDtList = orderCompDtService.findByOrderDtAndDeactive(orderDt, false);
			for (OrderCompDt orderCompDt : orderCompDtList) {
				orderCompDt.setDeactive(true);
				orderCompDt.setDeactiveDt(new java.util.Date());
				orderCompDtService.save(orderCompDt);
				
			}
			
			List<OrderLabDt>orderLabDts =orderLabDtService.findByOrderDtAndDeactive(orderDt, false);
			for (OrderLabDt orderLabDt : orderLabDts) {
				orderLabDt.setDeactive(true);
				orderLabDt.setDeactiveDt(new java.util.Date());
				orderLabDtService.save(orderLabDt);
				
			}
			
			orderDtRepository.save(orderDt);
			
		}
		
		
		return retVal;

	}

	@Override
	public Long count() {
		return orderDtRepository.count();
	}

	@Override
	public OrderDt findOne(int id) {
		return orderDtRepository.findOne(id);
	}

	@Override
	public OrderDt findByUniqueId(Long uniqueId) {
		return orderDtRepository.findByUniqueId(uniqueId);
	}

	

	@Override
	public Map<Integer, String> getOrderDtList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OrderDt> findByStyleNo(Integer limit, Integer offset,
			String sort, String order, String styleNo, Boolean onlyActive) {

		QOrderDt qOrderDt = QOrderDt.orderDt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (styleNo == null) {
				expression = qOrderDt.deactive.eq(false);
			} else {
				expression = qOrderDt.deactive.eq(false).and(qOrderDt.design.styleNo.like(styleNo + "%"));
			}
		} else {
			if (styleNo != null) {
				expression = qOrderDt.design.styleNo.like(styleNo + "%");
			}
		}

//		int page = (offset == 0 ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10;
		}

		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		Page<OrderDt> orderDtList = (Page<OrderDt>) orderDtRepository.findAll(expression,
				new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

		return orderDtList;

	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QOrderDt qOrderDt = QOrderDt.orderDt;
		BooleanExpression expression = qOrderDt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qOrderDt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("orderDtNo") && colValue != null) {
				expression = qOrderDt.deactive.eq(false).and(qOrderDt.design.styleNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("orderDtNo")) && colValue != null) {
				// expression = qOrderDt.designNo.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("orderDtNo") && colValue != null) {
				// expression = qOrderDt.designNo.like(colValue + "%");
			} else if (colName != null && colName.equalsIgnoreCase("styleNo") && colValue != null) {
				expression = qOrderDt.design.styleNo.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return orderDtRepository.count();
			} else {
				return orderDtRepository.count();
			}
		}

		return orderDtRepository.count(expression);
	}
	
	
	
	@Override
	public Double getTotalOrderQtys(Integer orderMtId) {
		QOrderDt qOrderDt = QOrderDt.orderDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		Double retVal = 0.0;
		
		List<Double> orderDtList = query.from(qOrderDt).
				where(qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.eq(orderMtId))).list(qOrderDt.pcs.sum());
		
		
		for (Double qty : orderDtList) {
			if(qty != null){
				retVal = qty;
			}
		}
		
		return retVal;
	}

	@Override
	public String transactionalSave(OrderDt orderDt, Integer id,
			Integer orderMtIdPk, String metalDtData, Principal principal,Boolean netWtWithCompFlg) {
	
		String action = "";
		
		OrderMt orderMt =orderMtService.findOne(orderMtIdPk);
			
		Design design = null;
		
		
		Purity purity = purityService.findOne(orderDt.getPurity().getId());
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			Integer maxSrno =getMaxSrNo(orderMtIdPk);
			if(maxSrno==null){
				maxSrno=1;
			}else{
				maxSrno +=1;
			}
			
			design =designService.findByMainStyleNoAndDeactive(orderDt.getDesign().getMainStyleNo(), false);
		
			orderDt.setDesign(design);
			orderDt.setOrderMt(orderMt);
			orderDt.setCreatedBy(principal.getName());
			orderDt.setCreatedDate(new java.util.Date());
			orderDt.setUniqueId(new java.util.Date().getTime());
			orderDt.setSrNo(maxSrno);
			orderDt.setPurityConv(purity.getPurityConv());
			orderDt.setRemark(orderDt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			orderDt.setStampInst(orderDt.getStampInst().replaceAll("[\\n\\t\\r ]", " ").trim());
			orderDt.setRefNo(orderDt.getRefNo().replaceAll("[\\n\\t\\r ]", " ").trim());
			orderDt.setItem(orderDt.getItem().replaceAll("[\\n\\t\\r ]", " ").trim());
			if(orderDt.getProductSize().getId() == null){
				orderDt.setProductSize(null);
			}
			
			action = "added";
			
		} else {
			
			OrderDt orderDtEdit = findOne(id);
			
			
			/*List<BagMt>bagList =bagMtService.findByOrderDtAndDeactive(orderDtEdit, false);
			
			for(BagMt bagMt :bagList){
			
				
			}*/
			
			
			
			orderDtEdit.setPcs(orderDt.getPcs());
			orderDtEdit.setDiscPercent(orderDt.getDiscPercent());
			orderDtEdit.setDiscAmount(orderDt.getDiscAmount());
			orderDtEdit.setModiBy(principal.getName());
			orderDtEdit.setModiDate(new java.util.Date());
			orderDtEdit.setProductSize(orderDt.getProductSize().getId() != null ? orderDt.getProductSize() : null);
		//	orderDtEdit.setRefNo(orderDt.getRefNo());
			orderDtEdit.setDueDate(orderDt.getDueDate());
			orderDtEdit.setStampInst(orderDt.getStampInst());
			orderDtEdit.setPurity(orderDt.getPurity());
			orderDtEdit.setBarcode(orderDt.getBarcode());
			orderDtEdit.setReqCarat(orderDt.getReqCarat());
			orderDtEdit.setColor(orderDt.getColor());
			orderDtEdit.setGrossWt(orderDt.getGrossWt());
			orderDtEdit.setItem(orderDt.getItem());
			orderDtEdit.setOrdRef(orderDt.getOrdRef());
			orderDtEdit.setCancelQty(orderDt.getCancelQty());
			orderDtEdit.setDeliveryDate(orderDt.getDeliveryDate());
			orderDtEdit.setPurityConv(purity.getPurityConv());
			orderDtEdit.setRemark(orderDt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			orderDtEdit.setStampInst(orderDt.getStampInst().replaceAll("[\\n\\t\\r ]", " ").trim());	
			orderDtEdit.setRefNo(orderDt.getRefNo().replaceAll("[\\n\\t\\r ]", " ").trim());
			orderDtEdit.setItem(orderDt.getItem().replaceAll("[\\n\\t\\r ]", " ").trim());
			save(orderDtEdit);
			
			
			action = "updated";
			
		}
		

				
		if(action.equals("added")){
			
			
			
			save(orderDt);
			
			orderMetalService.addOrderMetal(metalDtData, orderMt, orderDt, principal);
			
			List<DesignStone> designStones = designStoneService.findByDesign(design); 
			orderStnDtService.setOrderStnDt(designStones, orderMt, orderDt,principal);
			
			List<DesignComponent> designComponents = designComponentService.findByDesign(design);
			orderCompDtService.setOrderCompDt(designComponents, orderMt, orderDt, principal);
			
			
			 Double totNetWt=0.0;	
			 String ktDesc="";
			 List<OrderMetal> orderMetals=orderMetalService.findByOrderDtAndDeactive(orderDt, false);
			 if(orderMetals.size()>0){
				 for(OrderMetal orderMetal :orderMetals){
					 totNetWt  += orderMetal.getMetalWeight();	 
					 if(ktDesc.length()>0) {
						 ktDesc= ktDesc+","+orderMetal.getPurity().getId()+"-"+orderMetal.getColor().getId();
					 }else {
						 ktDesc= orderMetal.getPurity().getId()+"-"+orderMetal.getColor().getId();	 
					 }
				 }
				 
			 }
			 
					
				List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
				Double totStnCarat = 0.0;
				for(OrderStnDt orderStnDt:orderStnDts){
					totStnCarat += orderStnDt.getCarat();
				}
				
				Double temVal = totStnCarat/5;
				Double totGrossWt = totNetWt+temVal;
				
				List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDt, false);
				Double totCompMetalWt = 0.0;
				for(OrderCompDt orderCompDt:orderCompDts){
					totCompMetalWt += orderCompDt.getMetalWt();
				}
				
				totGrossWt += totCompMetalWt;
				
				Double grossWtdiff = Math.round((orderDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
				
				OrderMetal orderMetal = orderMetalService.findByOrderDtAndDeactiveAndMainMetal(orderDt,false,true);
				
				orderMetal.setMetalWeight(Math.round((orderMetal.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
				orderMetalService.save(orderMetal);
				
				if(netWtWithCompFlg.equals(true)){
					totNetWt +=totCompMetalWt+grossWtdiff;
				}else {
					totNetWt +=grossWtdiff;
				}
				
				
				orderDt.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
				orderDt.setKtDesc(ktDesc);
				save(orderDt);
			
				//applyRate(orderDt,principal);
				
		
		}else{
			
			orderMetalService.addOrderMetal(metalDtData, orderMt, orderDt, principal);
			
			
			OrderDt orderDtEdit = findOne(id);
			
			
			
			 Double totNetWt=0.0;	
			 List<OrderMetal> orderMetals=orderMetalService.findByOrderDtAndDeactive(orderDtEdit, false);
			 if(orderMetals.size()>0){
				 for(OrderMetal orderMetal :orderMetals){
					 totNetWt  += orderMetal.getMetalWeight();	 
				 }
				 
			 }
			 
					
				List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDtEdit, false);
				Double totStnCarat = 0.0;
				for(OrderStnDt orderStnDt:orderStnDts){
					totStnCarat += orderStnDt.getCarat();
				}
				
				Double temVal = totStnCarat/5;
				Double totGrossWt = totNetWt+temVal;
				
				List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDtEdit, false);
				Double totCompMetalWt = 0.0;
				for(OrderCompDt orderCompDt:orderCompDts){
					totCompMetalWt += orderCompDt.getMetalWt();
				}
				
				totGrossWt += totCompMetalWt;
				
				Double grossWtdiff = Math.round((orderDtEdit.getGrossWt()-totGrossWt)*1000.0)/1000.0;
				
				OrderMetal orderMetal = orderMetalService.findByOrderDtAndDeactiveAndMainMetal(orderDtEdit,false,true);
				
				orderMetal.setMetalWeight(Math.round((orderMetal.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
				orderMetalService.save(orderMetal);
				
				
				if(netWtWithCompFlg.equals(true)){
					totNetWt +=totCompMetalWt+grossWtdiff;
				}else {
					totNetWt +=grossWtdiff;
				}
				
				
				orderDtEdit.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
				
				save(orderDtEdit);
			
			//	applyRate(orderDtEdit,principal);
				
				
			
		}
		
		updateKtDesc(orderDt.getId());
		updateQltyDesc(orderDt.getId());
		
		return action;
	}
	
	
	@Override
	public String applyRate(OrderDt orderDt,Principal principal,Boolean netWtWithCompFlg) {
										
		String retVal="";
		  
		if(orderDt.getrLock().equals(false)){

		applyMetal(orderDt);
		List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
					
		
		applyStoneRate(orderStnDts);
		
		List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDt, false);
		applyCompRate(orderCompDts);
		
		applyLabRate(orderDt,principal);
		
		updateFob(orderDt,netWtWithCompFlg);
		retVal="1";
		
		}
		return retVal;
		
		
	}
	
	
	@Override
	public String applyMetal(OrderDt orderDt) {
			
		OrderMt orderMt =orderDt.getOrderMt();
		
		
		List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderDt, false);
		if(orderMetals !=null){
			
			for(OrderMetal orderMetal:orderMetals){
				
				if(orderMetal.getrLock().equals(false)){
					if (orderMetal.getPurity() != null) {
						Purity purity = purityService.findOne(orderMetal.getPurity().getId());
																	
						OrderMetalRate orderMetalRate=orderMetalRateService.findByOrderMtAndDeactiveAndMetal(orderMt, false,purity.getMetal());
						if(orderMetalRate!=null){
							orderMetal.setMetalRate(orderMetalRate.getRate());
							if(orderMetalRate.getLossPerc()>0) {
								orderMetal.setLossPerc(orderMetalRate.getLossPerc());	
							}else {
								ClientWastage clientWastage =clientWastageService.findByMetalAndPartyAndDeactive(purity.getMetal(), orderMt.getParty(), false);
								if(clientWastage!=null){
									orderMetal.setLossPerc(clientWastage.getWastagePerc());	
								}
							}
							
								
							orderMetalService.save(orderMetal);
							
							
							if(orderMetal.getMainMetal().equals(true)){
								orderDt.setLossPercDt(orderMetal.getLossPerc());
								save(orderDt);
							}
							
						}
							
							
						}
						
						
			}
					
					
				}
				
				
			
				
				
			
	
			}
			
			
		
		
		
		return "";		
	}
	
	@Override
	public String applyStoneRate(List<OrderStnDt> orderStnDts) {
		
		String retVal="error";
		 
		for(OrderStnDt orderStnDt:orderStnDts){
			
			if(orderStnDt.getrLock().equals(true)){
				continue;
			}
			
			
			orderStnDt=applySettingRate(orderStnDt);
			
			orderStnDt=applyStoneRateDt(orderStnDt);
			
			orderStnDt=applyHandlingRate(orderStnDt);
			
			orderStnDtService.save(orderStnDt);
			
			retVal="1";
			
			
		}
		
		
		
		return retVal;
	}
	
	

	@Override
	public String applyCompRate(List<OrderCompDt>orderCompDts) {
		
		String retVal="error";
		
		for(OrderCompDt orderCompDt:orderCompDts){
			
			if(orderCompDt.getrLock().equals(true)){
				continue;
			}

			orderCompDt=applyCompRateDt(orderCompDt);
						
			orderCompDtService.save(orderCompDt);
						
		}
		
			retVal="1";
		return retVal;

	}
	
	
	
	@Override
	public String applyLabRate(OrderDt orderDt,Principal principal) {
		
		QOrderMetal qOrderMetal =QOrderMetal.orderMetal;
		JPAQuery query=new JPAQuery(entityManager);
		
		List<Tuple> orderMetalList=null;
		
		orderMetalList = query.from(qOrderMetal).
				where(qOrderMetal.deactive.eq(false).and(qOrderMetal.orderDt.id.eq(orderDt.getId())))
				.groupBy(qOrderMetal.purity.metal).list(qOrderMetal.purity,qOrderMetal.purity.metal.name,qOrderMetal.metalWeight.sum(),qOrderMetal.metalPcs.sum());
		
		
		
		for(Tuple tuple : orderMetalList){
			
			QOrderCompDt qOrderCompDt = QOrderCompDt.orderCompDt;
			JPAQuery compQuery=new JPAQuery(entityManager);
			
					
			Metal metal =metalService.findByName(tuple.get(qOrderMetal.purity.metal.name));
			
			List<Tuple>orderCompList = compQuery.from(qOrderCompDt).where(qOrderCompDt.deactive.eq(false).
					and(qOrderCompDt.orderDt.id.eq(orderDt.getId())).and(qOrderCompDt.purity.metal.eq(metal)))
					.groupBy(qOrderCompDt.purity.metal).list(qOrderCompDt.metalWt.sum(),qOrderCompDt.compQty.sum());
			
			Double vCompWt=0.0;
			for(Tuple tupleComp : orderCompList){
					vCompWt= Math.round((tupleComp.get(qOrderCompDt.metalWt.sum()))*1000.0)/1000.0;
			}
			
			Double vMetalWt= Math.round((tuple.get(qOrderMetal.metalWeight.sum()))*1000.0)/1000.0;
			Double vNetWt = Math.round((vMetalWt+vCompWt)*1000.0)/1000.0;
			
			List<LabourCharge> labourCharges=null;
			
			 labourCharges =labourChargeService.getPurityWiseRates(orderDt.getOrderMt().getParty(), orderDt.getDesign().getCategory(),
					 vNetWt,false, metal,tuple.get(qOrderMetal.purity));
			 
			 
			 if(labourCharges.size()<=0){
				 
				 labourCharges =labourChargeService.getRates(orderDt.getOrderMt().getParty(), orderDt.getDesign().getCategory(),
						 vNetWt,false, metal);
				 
			 }
			
			
		
			
			List<OrderLabDt>orderLabDts =orderLabDtService.findByOrderDtAndMetalAndDeactive(orderDt, metal, false);
			
			Boolean isAvailable=false;
			for(LabourCharge labourCharge :labourCharges){
				isAvailable=false;
				for(OrderLabDt orderLabDt :orderLabDts){
					if(orderLabDt.getLabourType().equals(labourCharge.getLabourType())){
						
						isAvailable=true;
						if(orderLabDt.getrLock().equals(false)){
							orderLabDt.setLabourRate(labourCharge.getRate());	
							
							
							orderLabDt.setLabourRate(labourCharge.getRate());	
							orderLabDt.setPcsWise(false);
							orderLabDt.setGramWise(false);
							orderLabDt.setPercentWise(false);
							orderLabDt.setPerCaratRate(false);
							
							if(labourCharge.getPerPcsRate() == true){
								orderLabDt.setPcsWise(true);
							}else if(labourCharge.getPerGramRate() == true){
								orderLabDt.setGramWise(true);
							}else if(labourCharge.getPercentage() == true){
								orderLabDt.setPercentWise(true);
							}else if(labourCharge.getPerCaratRate() == true){
								orderLabDt.setPerCaratRate(true);
							}
							
							
							orderLabDtService.save(orderLabDt);
							
							
						}
						
					}
										
				}
				if(!isAvailable){
					
					OrderLabDt orderLabDt = new OrderLabDt();
					
					orderLabDt.setOrderMt(orderDt.getOrderMt());
					orderLabDt.setOrderDt(orderDt);
					orderLabDt.setLabourType(labourCharge.getLabourType());
					orderLabDt.setLabourRate(labourCharge.getRate());
					orderLabDt.setMetal(metal);
					
					if(labourCharge.getPerPcsRate() == true){
						orderLabDt.setPcsWise(true);
					}else if(labourCharge.getPerGramRate() == true){
						orderLabDt.setGramWise(true);
					}else if(labourCharge.getPercentage() == true){
						orderLabDt.setPercentWise(true);
					}else if(labourCharge.getPerCaratRate() == true){
						orderLabDt.setPerCaratRate(true);
					}
					
					orderLabDt.setCreatedDate(new java.util.Date());
					orderLabDt.setCreatedBy(principal.getName());
					
					orderLabDtService.save(orderLabDt);
					
					
				}
				
				
			}
			
			
			
			
			
		}
	
	
		return "";
	}
	
	


@Override
public String updateFob(OrderDt orderDt,Boolean netWtWithCompFlg) {
	try {
		
		Double  tempCal = 0.0;
		Double  tempCal2 = 0.0;
		Double tempLoss=0.0;
		Double totMetalValue=0.0;
		Double totStnValue=0.0;
		Double totSetValue=0.0;
		Double totHdlgValue=0.0; 
		Double totCompValue=0.0;
		Double totLabourValue=0.0;
		Double totCarat=0.0;
		
		
		Double tempVal = 0.0;
			
		
		if(orderDt.getrLock() == true){	
		}else{
			
			
	/*-------------------------------------------------Metal Fob--------------------------------*/					
			
			List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderDt, false);
			if(orderMetals !=null){
				for(OrderMetal orderMetal : orderMetals){
					if (orderMetal.getPurity() != null && orderMetal.getMetalRate()>0) {
						
						if(orderDt.getOrderMt().getParty().getType().getFieldValue().equalsIgnoreCase("Local")) {
							ClientKtConvMast clientKtConvMast=clientKtConvService.findByPartyAndPurityAndDeactive(orderDt.getOrderMt().getParty(), orderMetal.getPurity(), false);
							
							if(clientKtConvMast !=null) {
								tempCal =  orderMetal.getMetalRate()*clientKtConvMast.getPurityConv();
								orderMetal.setPerGramRate(Math.round((tempCal)*100.0)/100.0);
								orderMetal.setMetalValue(Math.round((orderMetal.getMetalWeight()*orderMetal.getPerGramRate())*100.0)/100.0);
								
								
							    
							}else {
								tempCal =  orderMetal.getMetalRate()*orderMetal.getPurity().getPurityConv();
								orderMetal.setPerGramRate(Math.round((tempCal)*100.0)/100.0);
								orderMetal.setMetalValue(Math.round((orderMetal.getMetalWeight()*orderMetal.getPerGramRate())*100.0)/100.0);
							}
							
						}else {
							
							Purity purity = purityService.findOne(orderMetal.getPurity().getId());
							String metalNm=purity.getMetal().getName();
							if(metalNm.equalsIgnoreCase("Gold")){
								tempCal =  orderMetal.getMetalRate()/orderMetal.getPurity().getMetal().getSpecificGravity();
								tempCal2 = (tempCal/(orderMetal.getOrderMt().getIn999().equals(true)?24:23.88)) * (orderMetal.getPurity().getvPurity() != null ? orderMetal.getPurity().getvPurity() : 0.0);
								tempLoss=tempCal2*orderMetal.getLossPerc()/100;
								orderMetal.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
								orderMetal.setMetalValue(Math.round((orderMetal.getMetalWeight()*orderMetal.getPerGramRate())*100.0)/100.0);
							
							}else if (metalNm.equalsIgnoreCase("Silver") || metalNm.equalsIgnoreCase("PLATINUM")) {
								tempCal =  orderMetal.getMetalRate()/orderMetal.getPurity().getMetal().getSpecificGravity();
								tempCal2 = (tempCal*(orderMetal.getPurity().getPurityConv() != null ? orderMetal.getPurity().getPurityConv() : 0.0));
									
								
								tempLoss=tempCal2*orderMetal.getLossPerc()/100;
								orderMetal.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
								orderMetal.setMetalValue(Math.round((orderMetal.getMetalWeight()*orderMetal.getPerGramRate())*100.0)/100.0);
								
								
									
							}else if (metalNm.equalsIgnoreCase("Alloy")) {
								tempCal =  orderMetal.getMetalRate()/1000;
								tempLoss=tempCal*orderMetal.getLossPerc()/100;
								
								orderMetal.setPerGramRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
								orderMetal.setMetalValue(Math.round((orderMetal.getMetalWeight()*orderMetal.getPerGramRate())*100.0)/100.0);
												
								
							}
							
						}
						
					
						
						totMetalValue +=orderMetal.getMetalValue();
						orderMetalService.save(orderMetal);
					
					}
					
					
					
				}
				
			}
			
	/*---------------------------------------------------------------------------------------------------------*/			
			
	/*----------------------------------------------Stone Fob------------------------------------------------------------------*/			
			
			List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
			for(OrderStnDt orderStnDt : orderStnDts){					
					
					if(orderStnDt.getPerPcsRateFlg().equals(false)){
						orderStnDt.setStoneValue(Math.round((orderStnDt.getCarat() * orderStnDt.getStnRate())*100.0)/100.0);
					}else{
						orderStnDt.setStoneValue(Math.round((orderStnDt.getStone() * orderStnDt.getStnRate())*100.0)/100.0);
					}
									
					orderStnDt.setSetValue(Math.round((orderStnDt.getSetRate()*orderStnDt.getStone())*100.0)/100.0);
					
					orderStnDt.setHandlingValue(Math.round((orderStnDt.getCarat() * orderStnDt.getHandlingRate())*100.0)/100.0);
					
								
					orderStnDtService.save(orderStnDt);
					totCarat +=orderStnDt.getCarat();
					totStnValue  	 += orderStnDt.getStoneValue();
					totSetValue  	 += orderStnDt.getSetValue();
					totHdlgValue 	 += orderStnDt.getHandlingValue();
										
					
				}

	/*---------------------------------------------------------------------------------------------------------*/			
			
	/*----------------------------------------------Component Fob------------------------------------------------------------------*/		
			
			
			List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDt, false);
			 
			for(OrderCompDt orderCompDt : orderCompDts){
			
				if(orderCompDt.getPerGramRate().equals(true)){
					orderCompDt.setCompValue(Math.round((orderCompDt.getMetalWt()* orderCompDt.getCompRate())*100.0)/100.0);
				}else{
					orderCompDt.setCompValue(Math.round((orderCompDt.getCompQty()* orderCompDt.getCompRate())*100.0)/100.0);
				}
				
				if(netWtWithCompFlg.equals(true)){
					OrderMetalRate orderMetalRate = orderMetalRateService.findByOrderMtAndDeactiveAndMetal(orderCompDt.getOrderMt(), false,orderCompDt.getPurity().getMetal());
					if(orderMetalRate!=null){
						Double metalRate = orderMetalRate.getRate();
						
						if(orderDt.getOrderMt().getParty().getType().getFieldValue().equalsIgnoreCase("Local")) {
							ClientKtConvMast clientKtConvMast=clientKtConvService.findByPartyAndPurityAndDeactive(orderDt.getOrderMt().getParty(), orderCompDt.getPurity(), false);
							
							if(clientKtConvMast !=null) {
								tempCal =  metalRate*clientKtConvMast.getPurityConv();
								orderCompDt.setMetalRate(Math.round((tempCal)*100.0)/100.0);
								orderCompDt.setMetalValue(Math.round((orderCompDt.getMetalWt()*orderCompDt.getMetalRate())*100.0)/100.0);
								
								
							    
							}else {
								tempCal =  metalRate*orderCompDt.getPurity().getPurityConv();
								orderCompDt.setMetalRate(Math.round((tempCal)*100.0)/100.0);
								orderCompDt.setMetalValue(Math.round((orderCompDt.getMetalWt()*orderCompDt.getMetalRate())*100.0)/100.0);
							}
							
						}else {
							
						
							OrderMetal orderMetal=orderMetalService.findByOrderDtAndDeactiveAndMainMetal(orderCompDt.getOrderDt(),false, true);
							
							
							
							
							String metalNm=orderCompDt.getPurity().getMetal().getName();
							if(metalNm.equalsIgnoreCase("Gold")){
								tempCal =  metalRate/orderCompDt.getPurity().getMetal().getSpecificGravity();
								tempCal2 = (tempCal/(orderCompDt.getOrderMt().getIn999().equals(true)?24:23.88)) * (orderCompDt.getPurity().getvPurity() != null ? orderCompDt.getPurity().getvPurity() : 0.0);
								tempLoss=tempCal2*orderMetal.getLossPerc()/100;
								orderCompDt.setMetalRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
								orderCompDt.setMetalValue(Math.round((orderCompDt.getMetalWt()*orderCompDt.getMetalRate())*100.0)/100.0);
							
							}else if (metalNm.equalsIgnoreCase("Silver") || metalNm.equalsIgnoreCase("PLATINUM")) {
								tempCal =  metalRate/orderCompDt.getPurity().getMetal().getSpecificGravity();
								if(orderMetal.getOrderMt().getIn999().equals(true)){
									tempCal2 = tempCal;
								}else{
									tempCal2 = (tempCal*(orderCompDt.getPurity().getPurityConv() != null ? orderCompDt.getPurity().getPurityConv() : 0.0));	
								}
								
								tempLoss=tempCal2*orderMetal.getLossPerc()/100;
								orderCompDt.setMetalRate(Math.round((tempCal2 + tempLoss)*100.0)/100.0);
								orderCompDt.setMetalValue(Math.round((orderCompDt.getMetalWt()*orderCompDt.getMetalRate())*100.0)/100.0);
								
								
									
							}
						}
						
						
						
					
						
						
						
					
					}
					
					totMetalValue += orderCompDt.getMetalValue();
					
				}
				
						
				orderCompDtService.save(orderCompDt);
				totCompValue += orderCompDt.getCompValue();
				
				
			}
	/*---------------------------------------------------------------------------------------------------------*/				
			
	/*----------------------------------------------Labour Fob------------------------------------------------------------------*/		
			
			List<OrderLabDt> orderLabDts = orderLabDtService.findByOrderDtAndDeactive(orderDt, false);
			for(OrderLabDt orderLabDt : orderLabDts){
					
				Double vTotMetalWt=0.0;
				Double vTOtMetalVal=0.0;
				
				if (orderLabDts != null) {
					vTotMetalWt = 0.0;
					vTOtMetalVal=0.0;
					for (OrderMetal orderMetal : orderMetals) {
						if (orderLabDt.getMetal().getId().equals(
								orderMetal.getPurity().getMetal().getId())) {
							vTotMetalWt += orderMetal.getMetalWeight();
							vTOtMetalVal +=orderMetal.getMetalValue();
						}

					}

				}
				
				
				if(orderLabDt.getPcsWise() == true){
					orderLabDt.setLabourValue(Math.round((orderLabDt.getLabourRate() * 1)*100.0)/100.0);
				
				}else if(orderLabDt.getGramWise() == true){
					orderLabDt.setLabourValue(Math.round((orderLabDt.getLabourRate() * vTotMetalWt)*100.0)/100.0);
				}else if(orderLabDt.getPercentWise() == true){
					orderLabDt.setLabourValue(Math.round(((vTOtMetalVal * orderLabDt.getLabourRate())/100 )*100.0)/100.0);
				}else if(orderLabDt.getPerCaratRate() == true){
					orderLabDt.setLabourValue(Math.round((totCarat * orderLabDt.getLabourRate())*100.0)/100.0);
				}
				
				
				
				
				
				orderLabDtService.save(orderLabDt);
				totLabourValue += orderLabDt.getLabourValue();
				
			}
			
			
	/*---------------------------------------------------------------------------------------------------------*/					
			
			
			orderDt.setMetalValue(Math.round((totMetalValue)*100.0)/100.0);
			orderDt.setStoneValue(Math.round((totStnValue)*100.0)/100.0);
			orderDt.setSetValue(Math.round((totSetValue)*100.0)/100.0);
			orderDt.setHdlgValue(Math.round((totHdlgValue)*100.0)/100.0);
			orderDt.setCompValue(Math.round((totCompValue)*100.0)/100.0);
			orderDt.setLabValue(Math.round((totLabourValue)*100.0)/100.0);
			
			
			
									
			tempVal = orderDt.getMetalValue()+orderDt.getStoneValue()+orderDt.getCompValue()+orderDt.getLabValue()+orderDt.getSetValue()+orderDt.getHdlgValue();
			
			orderDt.setFob(Math.round((tempVal)*100.0)/100.0);
			
			orderDt.setPerPcFinalPrice(Math.round((orderDt.getFob()- orderDt.getDiscAmount())*100.0)/100.0);
			
			orderDt.setFinalPrice(Math.round((orderDt.getPerPcFinalPrice()* orderDt.getPcs())*100.0)/100.0);
			orderDtRepository.save(orderDt);
			
			
			
			
			
		} // ending main else
		
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		
			
			return "";
	
}

@Override
public List<OrderDt> findByOrderMtAndDeactive(OrderMt orderMt, Boolean deactive) {
	// TODO Auto-generated method stub
	return orderDtRepository.findByOrderMtAndDeactive(orderMt, deactive);
}

@Override
public Long countAll(String colValue, Integer mtId) {
	QOrderDt qOrderDt = QOrderDt.orderDt;
	BooleanExpression expression = qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.eq(mtId));
	
		if(colValue!=null && colValue !="" ){
				
			expression = qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.eq(mtId)).and(qOrderDt.design.mainStyleNo.like(colValue + "%"));
		}
	
		
	 return orderDtRepository.count(expression);
}

@Override
public Page<OrderDt> searchAll(Integer limit, Integer offset, String sort,
		String order, String name, Integer mtId, String mOpt) {
	
	QOrderDt qOrderDt = QOrderDt.orderDt;
	BooleanExpression expression = qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.eq(mtId));

	if(name !=null && name !=""){
		expression = qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.eq(mtId)).and(qOrderDt.design.mainStyleNo.like(name + "%"));
	}
	
	

	int page = (offset == 0 ? 0 : (offset / limit));

	if (sort == null) {
		sort = "id";
	}else if (sort.equalsIgnoreCase("srNo")) {
		sort = "srNo";
	} 

	


	if(mOpt.equalsIgnoreCase("add")){
		
		Page<OrderDt> orderDtList = (Page<OrderDt>) orderDtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));
		return orderDtList;
	}else {
		Page<OrderDt> orderDtList = (Page<OrderDt>) orderDtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), sort));
		return orderDtList;
	}
	
}
	
	@Override
	public String updateGrossWt(OrderDt orderDt,Boolean netWtWithComp) {
		
		 Double totNetWt=0.0;	
		 List<OrderMetal> orderMetals=orderMetalService.findByOrderDtAndDeactive(orderDt, false);
		 if(orderMetals.size()>0){
			 for(OrderMetal orderMetal :orderMetals){
				 totNetWt  += orderMetal.getMetalWeight();	 
			 }
			 
		 }
		 
				
			List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
			Double totStnCarat = 0.0;
			for(OrderStnDt orderStnDt:orderStnDts){
				totStnCarat += orderStnDt.getCarat();
			}
			
			Double temVal = totStnCarat/5;
			Double totGrossWt = totNetWt+temVal;
			
			List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDt, false);
			Double totCompMetalWt = 0.0;
			for(OrderCompDt orderCompDt:orderCompDts){
				totCompMetalWt += orderCompDt.getMetalWt();
			}
			
			totGrossWt += totCompMetalWt;
			
			
			Double grossWtdiff = Math.round((orderDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
			
			OrderMetal orderMetal = orderMetalService.findByOrderDtAndDeactiveAndMainMetal(orderDt, false, true);
			orderMetal.setMetalWeight(Math.round((orderMetal.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
			orderMetalService.save(orderMetal);	
			
			
			if(netWtWithComp.equals(true)){
				
				totNetWt +=  totCompMetalWt+grossWtdiff;
			}else {
				totNetWt += grossWtdiff;
			}
			
		
			orderDt.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
			
			save(orderDt);
			
		return null;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@Override
	public String quotToOderPickup(String pOIds, String pTransferQty,
			Integer mtId, Principal principal,Integer partyId) {
		
		String retVal = "1";
		
		if(pOIds.length()<=0){
			return retVal="-1";
		}
		
		

		String quotDtId[] =pOIds.split(",");
		String trfQty[] = pTransferQty.split(",");
		for(int i=0;i<trfQty.length;i++){
			if((Double.parseDouble(trfQty[i]))<=0){
				return retVal="-2";
			}
		
		}
		
		
		
	
	    OrderMt	orderMt = orderMtService.findOne(mtId);
	
		/*int incs = 0;*/
		for(int i1=0;i1<quotDtId.length;i1++){
			
			Integer maxSrno =getMaxSrNo(mtId);
			if(maxSrno==null){
				maxSrno=1;
			}else{
				maxSrno +=1;
			}
			
	
		QuotDt quotDt = quotDtService.findOne(Integer.parseInt(quotDtId[i1]));
	
		// for OrderDt
		OrderDt orderDt  = new OrderDt();
		orderDt.setOrderMt(orderMt);
		orderDt.setDesign(quotDt.getDesign());
		orderDt.setPurity(quotDt.getPurity());
		orderDt.setBarcode(quotDt.getBarcode());
		orderDt.setColor(quotDt.getColor());
		orderDt.setSrNo(maxSrno);
		orderDt.setPcs(Double.parseDouble(trfQty[i1]));
		orderDt.setGrossWt(quotDt.getGrossWt());
		orderDt.setNetWt(quotDt.getNetWt());
		orderDt.setCreatedBy(principal.getName());
		orderDt.setCreatedDate(new Date());
		orderDt.setUniqueId(new java.util.Date().getTime());
		orderDt.setDesignRemark(quotDt.getDesignRemark());
		orderDt.setRemark(quotDt.getRemark());
		orderDt.setStampInst(quotDt.getStampInst());
		orderDt.setProductSize(quotDt.getProductSize());
		orderDt.setPurityConv(quotDt.getPurityConv());
		orderDt.setMetalValue(quotDt.getMetalValue());
		orderDt.setStoneValue(quotDt.getStoneValue());
		orderDt.setSetValue(quotDt.getSetValue());
		orderDt.setCompValue(quotDt.getCompValue());
		orderDt.setLabValue(quotDt.getLabValue());
		orderDt.setHdlgValue(quotDt.getHdlgValue());
		orderDtRepository.save(orderDt);
		
		OrderDt orderDt2 = findByUniqueId(orderDt.getUniqueId());
		
		
		List<QuotMetal> quotMetals=quotMetalService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotMetal quotMetal :quotMetals){
			OrderMetal orderMetal = new OrderMetal();
			orderMetal.setColor(quotMetal.getColor());
			orderMetal.setCreateDate(new Date());
			orderMetal.setCreatedBy(principal.getName());
			orderMetal.setLossPerc(quotMetal.getLossPerc());
			orderMetal.setMainMetal(quotMetal.getMainMetal());
			orderMetal.setMetalPcs(quotMetal.getMetalPcs());
			orderMetal.setMetalRate(quotMetal.getMetalRate());
			orderMetal.setMetalValue(quotMetal.getMetalValue());
			orderMetal.setMetalWeight(quotMetal.getMetalWeight());
			orderMetal.setOrderDt(orderDt2);
			orderMetal.setOrderMt(orderMt);
			orderMetal.setPartNm(quotMetal.getPartNm());
			orderMetal.setPerGramRate(quotMetal.getPerGramRate());
			orderMetal.setPurity(quotMetal.getPurity());
			
			orderMetalService.save(orderMetal);
			
		}
		
		
		
		// for OrderStnDt
		
	
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
				
		
		int srNo=0;
		for(QuotStnDt quotStnDt : quotStnDts){
				srNo+=1;
				OrderStnDt orderStnDt = new OrderStnDt();
				orderStnDt.setOrderDt(orderDt2);
				orderStnDt.setSrNo(srNo);
				orderStnDt.setOrderMt(orderMt);
				orderStnDt.setCreatedBy(principal.getName());
				orderStnDt.setCreatedDate(new java.util.Date());
				orderStnDt.setStoneType(quotStnDt.getStoneType());
				orderStnDt.setShape(quotStnDt.getShape());
				orderStnDt.setSubShape(quotStnDt.getSubShape());
				orderStnDt.setQuality(quotStnDt.getQuality());
				orderStnDt.setDiaCateg(quotStnDt.getDiaCateg()!=null ?quotStnDt.getDiaCateg():"");
				orderStnDt.setSize(quotStnDt.getSize());
				orderStnDt.setSieve(quotStnDt.getSieve());
				orderStnDt.setSizeGroup(quotStnDt.getSizeGroup());
				orderStnDt.setStone(quotStnDt.getStone());
				orderStnDt.setCarat(quotStnDt.getCarat());
				orderStnDt.setSetting(quotStnDt.getSetting());
				orderStnDt.setSettingType(quotStnDt.getSettingType());
				orderStnDt.setStnRate(quotStnDt.getStnRate());
				orderStnDt.setStoneValue(quotStnDt.getStoneValue());
				orderStnDt.setSetRate(quotStnDt.getSetRate());
				orderStnDt.setSetValue(quotStnDt.getSetValue());
				orderStnDt.setHandlingRate(quotStnDt.getHandlingRate());
				orderStnDt.setHandlingValue(quotStnDt.getHandlingValue());
				orderStnDt.setHdlgPercentWise(quotStnDt.getHdlgPercentWise());
				orderStnDt.setHdlgPerCarat(quotStnDt.getHdlgPerCarat());
				orderStnDt.setPerPcsRateFlg(quotStnDt.getPerPcsRateFlg());
				orderStnDt.setPartNm(quotStnDt.getPartNm());
				
							
				orderStnDtService.save(orderStnDt);
			}
		
		
		
		// for OrderCompDt
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
		
		for(QuotCompDt quotCompDt : quotCompDts){
			
			OrderCompDt orderCompDt = new OrderCompDt();
			
			orderCompDt.setOrderDt(orderDt2);
			orderCompDt.setOrderMt(orderMt);
			orderCompDt.setPurity(quotCompDt.getPurity());
			orderCompDt.setColor(quotCompDt.getColor());
			orderCompDt.setCreatedBy(principal.getName());
			orderCompDt.setCreatedDate(new java.util.Date());
			orderCompDt.setComponent(quotCompDt.getComponent());
			orderCompDt.setCompQty(quotCompDt.getCompQty());
			orderCompDt.setPurityConv(quotCompDt.getPurityConv());
			orderCompDt.setMetalWt(quotCompDt.getMetalWt());
			orderCompDt.setCompRate(quotCompDt.getCompRate());
			orderCompDt.setCompValue(quotCompDt.getCompValue());
			orderCompDt.setPerPcRate(quotCompDt.getPerPcRate());
			orderCompDt.setPerGramRate(quotCompDt.getPerGramRate());
			
			
			orderCompDtService.save(orderCompDt);
		}
		
		
		//for Order Labour
		List<QuotLabDt> quotLabDts =quotLabDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotLabDt quotLabDt :quotLabDts){
			
			OrderLabDt orderLabDt =new OrderLabDt();
			
			orderLabDt.setCreatedBy(principal.getName());
			orderLabDt.setCreatedDate(new Date());
			orderLabDt.setGramWise(quotLabDt.getGramWise());
			orderLabDt.setLabourRate(quotLabDt.getLabourRate());
			orderLabDt.setLabourType(quotLabDt.getLabourType());
			orderLabDt.setLabourValue(quotLabDt.getLabourValue());
			orderLabDt.setMetal(quotLabDt.getMetal());
			orderLabDt.setOrderDt(orderDt2);
			orderLabDt.setOrderMt(orderMt);
			orderLabDt.setPcsWise(quotLabDt.getPcsWise());
			orderLabDt.setPerCaratRate(quotLabDt.getPerCaratRate());
			orderLabDt.setPercentWise(quotLabDt.getPercentWise());
			
			
			orderLabDtService.save(orderLabDt);
			
			
		}
	
		
		updateKtDesc(orderDt2.getId());
		updateQltyDesc(orderDt2.getId());
		quotDt.setAdjustedQty(quotDt.getAdjustedQty()+Double.parseDouble(trfQty[i1]));
		quotDtService.save(quotDt);
		
		

		}
		retVal=orderMt.getId()+"";
		
		
	
		return retVal;

	}

	@Override
	public Integer getMaxSrNo(Integer mtId) {
		QOrderDt qOrderDt = QOrderDt.orderDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = 0;

		List<Integer> maxItemno = query
			.from(qOrderDt)
			.where(qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.eq(mtId)))
			.list(qOrderDt.srNo.max());

			retVal =maxItemno.get(0);	

		return retVal;
	}

	@Override
	public List<OrderDt> getorderStyleList(String orderId) {


		List<Integer> styleList =  new ArrayList<Integer>();
		if(orderId.length() > 0){
			String vOrderId[] = orderId.split(",");
			for (int i = 0; i < vOrderId.length; i++) {
				styleList.add(Integer.parseInt(vOrderId[i]));
			}
		}
		
		QOrderDt qOrderDt = QOrderDt.orderDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		
		List<OrderDt> orderDts=null;
		
		if(styleList.size() > 0 ){
			orderDts = query.from(qOrderDt).
					where(qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.in(styleList))).list(qOrderDt);
		
		}
		
	
		return orderDts;
	}

	@Override
	public String getOrderDtTotal(Integer mtId) {
		String retVal = "";

		QOrderDt qOrderDt = QOrderDt.orderDt;
		JPAQuery query = new JPAQuery(entityManager);

		List<Double> totOrderQty = null;

		totOrderQty = query
				.from(qOrderDt)
				.where(qOrderDt.deactive.eq(false).and(
						qOrderDt.orderMt.id.eq(mtId)))
				.groupBy(qOrderDt.orderMt.id).list(qOrderDt.pcs.sum());

		if (totOrderQty.size() > 0) {

			Double ordQty = Math.round(totOrderQty.get(0) * 100.0) / 100.0;
			retVal = ordQty.toString();
		} else {
			retVal = "0";
		}

		return retVal;
	}

	@Override
	public String orderToOderPickup(String pOIds, String pTransferQty,
			Integer mtId, Principal principal, Integer partyId) {
		
		String retVal = "1";
		
		if(pOIds.length()<=0){
			return retVal="-1";
		}
		
		

		String orderDtId[] =pOIds.split(",");
		String trfQty[] = pTransferQty.split(",");
		for(int i=0;i<trfQty.length;i++){
			if((Double.parseDouble(trfQty[i]))<=0){
				return retVal="-2";
			}
		
		}
		
		
	
	    OrderMt	orderMt = orderMtService.findOne(mtId);
	
		/*int incs = 0;*/
		for(int i1=0;i1<orderDtId.length;i1++){
			
			Integer maxSrno =getMaxSrNo(mtId);
			if(maxSrno==null){
				maxSrno=1;
			}else{
				maxSrno +=1;
			}
			
	
		OrderDt orderDtPickUp = findOne(Integer.parseInt(orderDtId[i1]));
	
		// for OrderDt
		OrderDt orderDt  = new OrderDt();
		orderDt.setOrderMt(orderMt);
		orderDt.setDesign(orderDtPickUp.getDesign());
		orderDt.setPurity(orderDtPickUp.getPurity());
		orderDt.setColor(orderDtPickUp.getColor());
		orderDt.setBarcode(orderDtPickUp.getBarcode());
		orderDt.setSrNo(maxSrno);
		orderDt.setPcs(Double.parseDouble(trfQty[i1]));
		orderDt.setGrossWt(orderDtPickUp.getGrossWt());
		orderDt.setReqCarat(orderDtPickUp.getReqCarat());
		orderDt.setNetWt(orderDtPickUp.getNetWt());
		orderDt.setCreatedBy(principal.getName());
		orderDt.setCreatedDate(new Date());
		orderDt.setUniqueId(new java.util.Date().getTime());
		orderDt.setDesignRemark(orderDtPickUp.getDesignRemark());
		orderDt.setRemark(orderDtPickUp.getRemark());
		orderDt.setStampInst(orderDtPickUp.getStampInst());
		orderDt.setProductSize(orderDtPickUp.getProductSize());
		orderDt.setPurityConv(orderDtPickUp.getPurityConv());
		orderDt.setMetalValue(orderDtPickUp.getMetalValue());
		orderDt.setStoneValue(orderDtPickUp.getStoneValue());
		orderDt.setSetValue(orderDtPickUp.getSetValue());
		orderDt.setCompValue(orderDtPickUp.getCompValue());
		orderDt.setLabValue(orderDtPickUp.getLabValue());
		orderDt.setHdlgValue(orderDtPickUp.getHdlgValue());
		orderDtRepository.save(orderDt);
		
	
		
		
		List<OrderMetal> orderMetals=orderMetalService.findByOrderDtAndDeactive(orderDtPickUp, false);
		for(OrderMetal orderMetalPickUp :orderMetals){
			OrderMetal orderMetal = new OrderMetal();
			orderMetal.setColor(orderMetalPickUp.getColor());
			orderMetal.setCreateDate(new Date());
			orderMetal.setCreatedBy(principal.getName());
			orderMetal.setLossPerc(orderMetalPickUp.getLossPerc());
			orderMetal.setMainMetal(orderMetalPickUp.getMainMetal());
			orderMetal.setMetalPcs(orderMetalPickUp.getMetalPcs());
			orderMetal.setMetalRate(orderMetalPickUp.getMetalRate());
			orderMetal.setMetalValue(orderMetalPickUp.getMetalValue());
			orderMetal.setMetalWeight(orderMetalPickUp.getMetalWeight());
			orderMetal.setOrderDt(orderDt);
			orderMetal.setOrderMt(orderMt);
			orderMetal.setPartNm(orderMetalPickUp.getPartNm());
			orderMetal.setPerGramRate(orderMetalPickUp.getPerGramRate());
			orderMetal.setPurity(orderMetalPickUp.getPurity());
			
			orderMetalService.save(orderMetal);
			
		}
		
		
		
		// for OrderStnDt
		
	
		List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDtPickUp, false);
				
		
		int srNo=0;
		for(OrderStnDt orderStnDtPickUp : orderStnDts){
				srNo+=1;
				OrderStnDt orderStnDt = new OrderStnDt();
				orderStnDt.setOrderDt(orderDt);
				orderStnDt.setSrNo(srNo);
				orderStnDt.setOrderMt(orderMt);
				orderStnDt.setCreatedBy(principal.getName());
				orderStnDt.setCreatedDate(new java.util.Date());
				orderStnDt.setStoneType(orderStnDtPickUp.getStoneType());
				orderStnDt.setShape(orderStnDtPickUp.getShape());
				orderStnDt.setSubShape(orderStnDtPickUp.getSubShape());
				orderStnDt.setQuality(orderStnDtPickUp.getQuality());
				orderStnDt.setDiaCateg(orderStnDtPickUp.getDiaCateg());
				orderStnDt.setSize(orderStnDtPickUp.getSize());
				orderStnDt.setSieve(orderStnDtPickUp.getSieve());
				orderStnDt.setSizeGroup(orderStnDtPickUp.getSizeGroup());
				orderStnDt.setStone(orderStnDtPickUp.getStone());
				orderStnDt.setCarat(orderStnDtPickUp.getCarat());
				orderStnDt.setSetting(orderStnDtPickUp.getSetting());
				orderStnDt.setSettingType(orderStnDtPickUp.getSettingType());
				orderStnDt.setStnRate(orderStnDtPickUp.getStnRate());
				orderStnDt.setStoneValue(orderStnDtPickUp.getStoneValue());
				orderStnDt.setSetRate(orderStnDtPickUp.getSetRate());
				orderStnDt.setSetValue(orderStnDtPickUp.getSetValue());
				orderStnDt.setHandlingRate(orderStnDtPickUp.getHandlingRate());
				orderStnDt.setHandlingValue(orderStnDtPickUp.getHandlingValue());
				orderStnDt.setHdlgPercentWise(orderStnDtPickUp.getHdlgPercentWise());
				orderStnDt.setHdlgPerCarat(orderStnDtPickUp.getHdlgPerCarat());
				orderStnDt.setPerPcsRateFlg(orderStnDtPickUp.getPerPcsRateFlg());
				orderStnDt.setPartNm(orderStnDtPickUp.getPartNm());
							
				orderStnDtService.save(orderStnDt);
			}
		
		
		
		// for OrderCompDt
		List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDtPickUp, false);
		
		for(OrderCompDt orderCompDtPickUp : orderCompDts){
			
			OrderCompDt orderCompDt = new OrderCompDt();
			
			orderCompDt.setOrderDt(orderDt);
			orderCompDt.setOrderMt(orderMt);
			orderCompDt.setPurity(orderCompDtPickUp.getPurity());
			orderCompDt.setColor(orderCompDtPickUp.getColor());
			orderCompDt.setCreatedBy(principal.getName());
			orderCompDt.setCreatedDate(new java.util.Date());
			orderCompDt.setComponent(orderCompDtPickUp.getComponent());
			orderCompDt.setCompQty(orderCompDtPickUp.getCompQty());
			orderCompDt.setPurityConv(orderCompDtPickUp.getPurityConv());
			orderCompDt.setMetalWt(orderCompDtPickUp.getMetalWt());
			orderCompDt.setCompRate(orderCompDtPickUp.getCompRate());
			orderCompDt.setCompValue(orderCompDtPickUp.getCompValue());
			orderCompDt.setPerPcRate(orderCompDtPickUp.getPerPcRate());
			orderCompDt.setPerGramRate(orderCompDtPickUp.getPerGramRate());
		
			orderCompDtService.save(orderCompDt);
			
		}
		
		
		//for Order Labour
		List<OrderLabDt> orderLabDts =orderLabDtService.findByOrderDtAndDeactive(orderDtPickUp, false);
		for(OrderLabDt orderLabDtPickUp :orderLabDts){
			
			OrderLabDt orderLabDt =new OrderLabDt();
			
			orderLabDt.setCreatedBy(principal.getName());
			orderLabDt.setCreatedDate(new Date());
			orderLabDt.setGramWise(orderLabDtPickUp.getGramWise());
			orderLabDt.setLabourRate(orderLabDtPickUp.getLabourRate());
			orderLabDt.setLabourType(orderLabDtPickUp.getLabourType());
			orderLabDt.setLabourValue(orderLabDtPickUp.getLabourValue());
			orderLabDt.setMetal(orderLabDtPickUp.getMetal());
			orderLabDt.setOrderDt(orderDt);
			orderLabDt.setOrderMt(orderMt);
			orderLabDt.setPcsWise(orderLabDtPickUp.getPcsWise());
			orderLabDt.setPerCaratRate(orderLabDtPickUp.getPerCaratRate());
			orderLabDt.setPercentWise(orderLabDtPickUp.getPercentWise());
			
			
			orderLabDtService.save(orderLabDt);
			
			
		}
	
		updateKtDesc(orderDt.getId());
		updateQltyDesc(orderDt.getId());
		
	/*	quotDt.setAdjustedQty(quotDt.getAdjustedQty()+Double.parseDouble(trfQty[i]));
		quotDtService.save(quotDt);*/

		}
		retVal=orderMt.getId()+"";
		
		
	
		return retVal;
	}

	@Override
	public OrderStnDt applySettingRate(OrderStnDt orderStnDt) {
		// TODO Auto-generated method stub
		OrderMetal orderMetal = orderMetalService.findByOrderDtAndDeactiveAndPartNm(orderStnDt.getOrderDt(), false, orderStnDt.getPartNm());
		
		if(orderMetal !=null){
			
			Double pointerWt =Math.round((orderStnDt.getCarat()/orderStnDt.getStone())*1000.0)/1000.0 ;
			
			if(orderStnDt.getStoneType() != null && orderStnDt.getShape() != null &&
					orderStnDt.getSetting() != null && orderStnDt.getSettingType() != null){
				
				

				List<SettingCharge> settingChargeList = settingChargeService.getRates(orderStnDt.getOrderMt().getParty(),pointerWt,
						false,orderMetal.getPurity().getMetal(),orderStnDt.getStoneType(),orderStnDt.getShape(),orderStnDt.getSetting(),orderStnDt.getSettingType());
				
				SettingCharge settingCharge=null;
				
				if(settingChargeList.size()>0){
					settingCharge = settingChargeList.get(0);
				}
				
									
				if(settingCharge != null){
					
					if(settingCharge.getQualityWiseRate().equals(true)){
						
						List<SettingQualityRate>settingQualityRates=settingQualityRateService.findBySettingChargeAndDeactive(settingCharge, false);
						Boolean isAvailable=false;
						for(SettingQualityRate settingQualityRate:settingQualityRates){
							if(settingQualityRate.getQuality().equals(orderStnDt.getQuality())){
								orderStnDt.setSetRate(settingQualityRate.getQualityRate());
								isAvailable=true;
							}
						}
						
						if(isAvailable.equals(false)){
							orderStnDt.setSetRate(settingCharge.getRate());
						}
						
					}else{
						orderStnDt.setSetRate(settingCharge.getRate());
					}
					
					
					
				}
				
			}
		
		}
		return orderStnDt;

	}

	@Override
	public OrderStnDt applyHandlingRate(OrderStnDt orderStnDt) {
		// TODO Auto-generated method stub
		
		if(orderStnDt.getStnRate() >0){
			
			List<HandlingMasterFl> handlingList = handlingMasterFLService.getRates(orderStnDt.getOrderMt().getParty(),orderStnDt.getStnRate());
			
			if(handlingList.size() > 0){
				if(handlingList.get(0).getPercentage() > 0){
					
					
					orderStnDt.setHandlingRate(Math.round(((orderStnDt.getStnRate() * handlingList.get(0).getPercentage())/100)*100.0)/100.0);
					
					
					//quotStnDt.setHandlingRate(handlingList.get(0).getPercentage());
					orderStnDt.setHdlgPerCarat(false);
					orderStnDt.setHdlgPercentWise(true);
					
				}else{
					orderStnDt.setHandlingRate(handlingList.get(0).getRate());
					orderStnDt.setHdlgPerCarat(true);
					orderStnDt.setHdlgPercentWise(false);
					
				}
			}
			
			
			
		}
		return orderStnDt;
	}

	@Override
	public OrderStnDt applyStoneRateDt(OrderStnDt orderStnDt) {
		// TODO Auto-generated method stub
		if(orderStnDt.getStoneType() != null && orderStnDt.getShape() != null &&  orderStnDt.getQuality() != null && orderStnDt.getSizeGroup() != null){
			
			List<StoneRateMast> stoneRateMast=stoneRateMastService.getStoneRate(orderStnDt.getStoneType().getId(),orderStnDt.getShape().getId(),orderStnDt.getQuality().getId(), 
					orderStnDt.getSizeGroup().getId(),orderStnDt.getOrderMt().getParty().getId(),orderStnDt.getSieve());
			
			if(stoneRateMast.size() > 0){
				if(stoneRateMast.get(0).getPerPcRate() > 0){
					orderStnDt.setStnRate(stoneRateMast.get(0).getPerPcRate());
					orderStnDt.setPerPcsRateFlg(true);
					
				}else{
					orderStnDt.setStnRate(stoneRateMast.get(0).getStoneRate());
					orderStnDt.setPerPcsRateFlg(false);
					
				}
			}
			
			
		}
		return orderStnDt;
	}

	@Override
	public OrderCompDt applyCompRateDt(OrderCompDt orderCompDt) {
		// TODO Auto-generated method stub
		if(orderCompDt.getComponent() != null && orderCompDt.getPurity() != null){
			
			FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(orderCompDt.getComponent(), orderCompDt.getOrderMt().getParty(),
					orderCompDt.getPurity(), false);
			
			if(findingRateMast != null){
				if(findingRateMast.getPerPcRate().equals(true)){
					orderCompDt.setCompRate(findingRateMast.getRate());
					orderCompDt.setPerPcRate(true);
					orderCompDt.setPerGramRate(false);
				}else{
					orderCompDt.setCompRate(findingRateMast.getRate());
					orderCompDt.setPerPcRate(false);
					orderCompDt.setPerGramRate(true);
				}
			}
			
		}
		return orderCompDt;
	}



	@Override
	public String addOrderFromQuot(Integer orderMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		JSONArray jsonArray = new JSONArray(data);
		OrderMt orderMt = orderMtService.findOne(orderMtId);
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonDtNew = (JSONObject) jsonArray.get(i);
			if(!jsonDtNew.get("trfQty").toString().isEmpty()) {
				QuotDt quotDt = quotDtService.findOne(jsonDtNew.getInt("id"));
				OrderDt orderDt =new OrderDt();
				orderDt.setBarcode(quotDt.getBarcode());
				orderDt.setColor(quotDt.getColor());
				orderDt.setCompValue(quotDt.getCompValue());
				orderDt.setCreatedBy(principal.getName());
				orderDt.setCreatedDate(new Date());
				orderDt.setDesign(quotDt.getDesign());
				orderDt.setDesignRemark(quotDt.getDesignRemark());
				orderDt.setDiscAmount(quotDt.getDiscAmount());
				orderDt.setDiscPercent(quotDt.getDiscPercent());
				orderDt.setFinalPrice(quotDt.getFinalPrice());
				orderDt.setFob(quotDt.getFob());
				orderDt.setGrossWt(quotDt.getGrossWt());
				orderDt.setHandlingValue(quotDt.getHdlgValue());
				orderDt.setKtDesc(quotDt.getKtDesc());
				orderDt.setLabValue(quotDt.getLabValue());
				orderDt.setLossPercDt(quotDt.getLossPercDt());
				orderDt.setLossValue(quotDt.getLossValue());
				orderDt.setLossWt(quotDt.getLossWt());
				orderDt.setMetalRate(quotDt.getMetalRate());
				orderDt.setMetalValue(quotDt.getMetalValue());
				orderDt.setNetAmount(quotDt.getNetAmount());
				orderDt.setNetWt(quotDt.getNetWt());
				orderDt.setOrderMt(orderMt);
				orderDt.setOther(quotDt.getOther());
				orderDt.setPcs(jsonDtNew.getDouble("trfQty"));
				orderDt.setPerGmWt(quotDt.getPerGmWt());
				orderDt.setPerPcFinalPrice(quotDt.getPerPcFinalPrice());
				orderDt.setProductSize(quotDt.getProductSize());
				orderDt.setPurity(quotDt.getPurity());
				orderDt.setPurityConv(quotDt.getPurityConv());
				orderDt.setRemark(quotDt.getRemark());
				orderDt.setRefNo(quotDt.getRefNo());
				orderDt.setSetValue(quotDt.getSetValue());
				
				Integer maxSrno =getMaxSrNo(orderMtId);
				if(maxSrno==null){
					maxSrno=1;
				}else{
					maxSrno +=1;
				}
				orderDt.setSrNo(maxSrno);
				orderDt.setStampInst(quotDt.getStampInst());
				orderDt.setStoneValue(quotDt.getStoneValue());
				orderDt.setrLock(true);
				save(orderDt);
				
				List<QuotMetal>quotMetals =quotMetalService.findByQuotDtAndDeactive(quotDt, false);
				for(QuotMetal quotMetal : quotMetals) {
					OrderMetal orderMetal = new OrderMetal();
					orderMetal.setCastWeight(quotMetal.getCastWeight());
					orderMetal.setColor(quotMetal.getColor());
					orderMetal.setCreateDate(new Date());
					orderMetal.setCreatedBy(principal.getName());
					orderMetal.setLossPerc(quotMetal.getLossPerc());
					orderMetal.setMainMetal(quotMetal.getMainMetal());
					orderMetal.setMetalPcs(quotMetal.getMetalPcs());
					orderMetal.setMetalRate(quotMetal.getMetalRate());
					orderMetal.setMetalValue(quotMetal.getMetalValue());
					orderMetal.setMetalWeight(quotMetal.getMetalWeight());
					orderMetal.setOrderDt(orderDt);
					orderMetal.setOrderMt(orderMt);
					orderMetal.setPartNm(quotMetal.getPartNm());
					orderMetal.setPerGramRate(quotMetal.getPerGramRate());
					orderMetal.setProcessLoss(quotMetal.getProcessLoss());
					orderMetal.setPurity(quotMetal.getPurity());
					orderMetal.setrLock(true);
					orderMetalService.save(orderMetal);
					
				}
				
				List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
				
				
				int srNo=0;
				for(QuotStnDt quotStnDt : quotStnDts){
						srNo+=1;
						OrderStnDt orderStnDt = new OrderStnDt();
						orderStnDt.setOrderDt(orderDt);
						orderStnDt.setSrNo(srNo);
						orderStnDt.setOrderMt(orderMt);
						orderStnDt.setCreatedBy(principal.getName());
						orderStnDt.setCreatedDate(new java.util.Date());
						orderStnDt.setStoneType(quotStnDt.getStoneType());
						orderStnDt.setShape(quotStnDt.getShape());
						orderStnDt.setSubShape(quotStnDt.getSubShape());
						orderStnDt.setQuality(quotStnDt.getQuality());
						orderStnDt.setDiaCateg(quotStnDt.getDiaCateg()!=null ?quotStnDt.getDiaCateg():"");
						orderStnDt.setSize(quotStnDt.getSize());
						orderStnDt.setSieve(quotStnDt.getSieve());
						orderStnDt.setSizeGroup(quotStnDt.getSizeGroup());
						orderStnDt.setStone(quotStnDt.getStone());
						orderStnDt.setCarat(quotStnDt.getCarat());
						orderStnDt.setSetting(quotStnDt.getSetting());
						orderStnDt.setSettingType(quotStnDt.getSettingType());
						orderStnDt.setStnRate(quotStnDt.getStnRate());
						orderStnDt.setStoneValue(quotStnDt.getStoneValue());
						orderStnDt.setSetRate(quotStnDt.getSetRate());
						orderStnDt.setSetValue(quotStnDt.getSetValue());
						orderStnDt.setHandlingRate(quotStnDt.getHandlingRate());
						orderStnDt.setHandlingValue(quotStnDt.getHandlingValue());
						orderStnDt.setHdlgPercentWise(quotStnDt.getHdlgPercentWise());
						orderStnDt.setHdlgPerCarat(quotStnDt.getHdlgPerCarat());
						orderStnDt.setPerPcsRateFlg(quotStnDt.getPerPcsRateFlg());
						orderStnDt.setPartNm(quotStnDt.getPartNm());
						orderStnDt.setrLock(true);
									
						orderStnDtService.save(orderStnDt);
					}
				
				
				
				// for OrderCompDt
				List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
				
				for(QuotCompDt quotCompDt : quotCompDts){
					
					OrderCompDt orderCompDt = new OrderCompDt();
					
					orderCompDt.setOrderDt(orderDt);
					orderCompDt.setOrderMt(orderMt);
					orderCompDt.setPurity(quotCompDt.getPurity());
					orderCompDt.setColor(quotCompDt.getColor());
					orderCompDt.setCreatedBy(principal.getName());
					orderCompDt.setCreatedDate(new java.util.Date());
					orderCompDt.setComponent(quotCompDt.getComponent());
					orderCompDt.setCompQty(quotCompDt.getCompQty());
					orderCompDt.setPurityConv(quotCompDt.getPurityConv());
					orderCompDt.setMetalWt(quotCompDt.getMetalWt());
					orderCompDt.setCompRate(quotCompDt.getCompRate());
					orderCompDt.setCompValue(quotCompDt.getCompValue());
					orderCompDt.setPerPcRate(quotCompDt.getPerPcRate());
					orderCompDt.setPerGramRate(quotCompDt.getPerGramRate());
					orderCompDt.setrLock(true);
					
					orderCompDtService.save(orderCompDt);
				}
				
				
				//for Order Labour
				List<QuotLabDt> quotLabDts =quotLabDtService.findByQuotDtAndDeactive(quotDt, false);
				for(QuotLabDt quotLabDt :quotLabDts){
					
					OrderLabDt orderLabDt =new OrderLabDt();
					
					orderLabDt.setCreatedBy(principal.getName());
					orderLabDt.setCreatedDate(new Date());
					orderLabDt.setGramWise(quotLabDt.getGramWise());
					orderLabDt.setLabourRate(quotLabDt.getLabourRate());
					orderLabDt.setLabourType(quotLabDt.getLabourType());
					orderLabDt.setLabourValue(quotLabDt.getLabourValue());
					orderLabDt.setMetal(quotLabDt.getMetal());
					orderLabDt.setOrderDt(orderDt);
					orderLabDt.setOrderMt(orderMt);
					orderLabDt.setPcsWise(quotLabDt.getPcsWise());
					orderLabDt.setPerCaratRate(quotLabDt.getPerCaratRate());
					orderLabDt.setPercentWise(quotLabDt.getPercentWise());
					orderLabDt.setrLock(true);
					
					orderLabDtService.save(orderLabDt);
					
					
				}
				
				updateKtDesc(orderDt.getId());
				updateQltyDesc(orderDt.getId());
				
				retVal="1";
			}
			
			
			
		}
		
		
		return retVal;
	}

	@Override
	public String orderDtReportListing(String mtIds) {
		// TODO Auto-generated method stub
List<Integer> mtIdList = new ArrayList<Integer>();
		
		if(mtIds.length() > 0){
			String ids[] = mtIds.split(",");
			for(int i=0;i<ids.length;i++){
				mtIdList.add(Integer.parseInt(ids[i]));
			}
		}else{
			mtIdList.add(0);
		}
		
		
		
		QOrderDt qOrderDt = QOrderDt.orderDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<OrderDt> orderDts =  query.from(qOrderDt).
				where(qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.in(mtIdList))).list(qOrderDt);
		
		int srno =0;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"total\":").append(orderDts.size())
		.append(",\"rows\": [");
		
		
		for(OrderDt orderDt:orderDts){
	
		
			srno +=1;
			
		sb.append("{\"id\":")
			.append(orderDt.getId())
			.append(",\"srNo\":\"")
			.append(srno)
			.append("\",\"invNo\":\"")
			.append(orderDt.getOrderMt().getInvNo())
			.append("\",\"ordRefNo\":\"")
			.append((orderDt.getOrderMt().getRefNo() != null ? orderDt.getOrderMt().getRefNo() : ""))
			.append("\",\"style\":\"")
			.append((orderDt.getDesign() != null ? orderDt.getDesign().getMainStyleNo() : ""))
			.append("\",\"purity\":\"")
			.append((orderDt.getPurity() != null ? orderDt.getPurity().getName() : ""))
			.append("\",\"color\":\"")
			.append((orderDt.getColor() != null ? orderDt.getColor().getName() : ""))
			.append("\",\"pcs\":\"")
			.append((orderDt.getPcs() != null ? orderDt.getPcs() : ""))
			.append("\"},");
			
		
		}
	
	
	
	String str = sb.toString();
	str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
			: str);
	str += "]}";

	return str;		
	}
	public String updateKtDesc(Integer ordDtId) {
		// TODO Auto-generated method stub
		OrderDt orderDt =findOne(ordDtId);
		List<OrderMetal>orderMetals= orderMetalService.findByOrderDtAndDeactive(orderDt, false);
		TreeSet<String> ktDesc = new TreeSet<String>();
		 for(OrderMetal orderMetal :orderMetals){
			 ktDesc.add(orderMetal.getPurity().getId().toString());
		 }
		 orderDt.setKtDesc(ktDesc.toString());
		 save(orderDt);
		
		return "1";
	}

	@Override
	public String updateQltyDesc(Integer ordDtId) {
		// TODO Auto-generated method stub
		OrderDt orderDt =findOne(ordDtId);
		List<OrderStnDt>orderStnDts =orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
		TreeSet<String> qltyDesc = new TreeSet<String>();
		for(OrderStnDt orderStnDt:orderStnDts){
			
			if(orderStnDt.getQuality()!=null) {
				qltyDesc.add(orderStnDt.getQuality().getId().toString());	
			}
			
		}
		
		orderDt.setQltyDesc(qltyDesc.toString());
		 save(orderDt);
		return "1";

	}

	@Override
	public String orderDtPickupListing(String orderNm) {
		// TODO Auto-generated method stub
OrderMt orderMt = orderMtService.findByInvNoAndDeactive(orderNm, false);
		
		QOrderDt qOrderDt = QOrderDt.orderDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<OrderDt> orderDts =  query.from(qOrderDt).
				where(qOrderDt.deactive.eq(false).and(qOrderDt.orderMt.id.in(orderMt.getId()))).list(qOrderDt);
		
		int srno =0;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"total\":").append(orderDts.size())
		.append(",\"rows\": [");
		
		
		for(OrderDt orderDt:orderDts){
	
		
			srno +=1;
			
		sb.append("{\"id\":")
			.append(orderDt.getId())
			.append(",\"srNo\":\"")
			.append(srno)
			.append("\",\"invNo\":\"")
			.append(orderDt.getOrderMt().getInvNo())
			.append("\",\"ordRefNo\":\"")
			.append((orderDt.getOrderMt().getRefNo() != null ? orderDt.getOrderMt().getRefNo() : ""))
			.append("\",\"style\":\"")
			.append((orderDt.getDesign() != null ? orderDt.getDesign().getMainStyleNo() : ""))
			.append("\",\"purity\":\"")
			.append((orderDt.getPurity() != null ? orderDt.getPurity().getName() : ""))
			.append("\",\"color\":\"")
			.append((orderDt.getColor() != null ? orderDt.getColor().getName() : ""))
			.append("\",\"pcs\":\"")
			.append((orderDt.getPcs() != null ? orderDt.getPcs() : ""))
			.append("\",\"grossWt\":\"")
			.append((orderDt.getGrossWt() != null ? orderDt.getGrossWt() : ""))
			
			.append("\"},");
			
		}
	
	
	
	String str = sb.toString();
	str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
			: str);
	str += "]}";

	return str;	
	}

	@Override
	public String applyQuotRate(OrderDt orderDt, Principal principal, Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		
		
		if(orderDt.getrLock().equals(false)){
			
			
			QuotMt quotMt =quotMtService.findByPartyAndMasterFlgAndDeactive(orderDt.getOrderMt().getParty(), true, false);
			
			if(quotMt !=null) {
				QuotDt quotDt = quotDtService.findByQuotMtAndDesignAndKtDescAndQltyDescAndDeactive(quotMt, orderDt.getDesign(), orderDt.getKtDesc(), orderDt.getQltyDesc(),false);
				
				if(quotDt !=null) {
					
					applyQuotMetal(orderDt, quotDt);
					
					List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
					List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
					
					
					
					applyQuotStoneRate(orderStnDts,quotStnDts);
					
					
					List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDt, false);
					List<QuotCompDt>quotCompDts = quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
					applyQuotCompRate(orderCompDts,quotCompDts);
					
					applyQuotLabRate(orderDt,quotDt,principal);
					
					updateFob(orderDt,netWtWithCompFlg);
				
					
					
					
				}
				
				
			}
			
		}
		

		
		
		return "1";
	}

	@Override
	public String applyQuotMetal(OrderDt orderDt, QuotDt quotDt) {
		
		List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderDt, false);
		List<QuotMetal>quotMetals = quotMetalService.findByQuotDtAndDeactive(quotDt, false);
		
			
			for(OrderMetal orderMetal:orderMetals){
				
				if(orderMetal.getrLock().equals(false)){
					if (orderMetal.getPurity() != null) {
						
						for(QuotMetal quotMetal:quotMetals) {
							if(orderMetal.getPurity().equals(quotMetal.getPurity())) {
								
								orderMetal.setMetalRate(quotMetal.getMetalRate());
								orderMetal.setLossPerc(quotMetal.getLossPerc());	
								orderMetalService.save(orderMetal);
								
								
								if(orderMetal.getMainMetal().equals(true)){
									orderDt.setLossPercDt(orderMetal.getLossPerc());
									save(orderDt);
								}
								
								
							}
						}
						
						
						
				
						
						
			}
					
					
				}
				
				
			
				
				
			
	
			}
			
			
	
		
		
		return "";	
	}

	@Override
	public String applyQuotStoneRate(List<OrderStnDt> orderStnDts, List<QuotStnDt> quotStnDts) {
		String retVal="error";
		 
		for(OrderStnDt orderStnDt:orderStnDts){
			
			if(orderStnDt.getrLock().equals(true)){
				continue;
			}
			for(QuotStnDt quotStnDt :quotStnDts) {
				if(orderStnDt.getStoneType().equals(quotStnDt.getStoneType()) && orderStnDt.getShape().equals(quotStnDt.getShape()) &&
						orderStnDt.getQuality().equals(quotStnDt.getQuality()) && orderStnDt.getSize().equals(quotStnDt.getSize()) && orderStnDt.getSizeGroup().equals(quotStnDt.getSizeGroup())) {
					
					if(orderStnDt.getHandlingRate()<=0) {
						orderStnDt.setHandlingRate(quotStnDt.getHandlingRate());	
					}
					
					
					
					orderStnDt.setHdlgPerCarat(quotStnDt.getHdlgPerCarat());
					orderStnDt.setHdlgPercentWise(quotStnDt.getHdlgPercentWise());
					orderStnDt.setPerPcsRateFlg(quotStnDt.getPerPcsRateFlg());
					
					if(orderStnDt.getSetRate()<=0) {
						orderStnDt.setSetRate(quotStnDt.getSetRate());
					}
					
					if(orderStnDt.getStnRate()<=0) {
						orderStnDt.setStnRate(quotStnDt.getStnRate());
					}
					
					
					orderStnDtService.save(orderStnDt);
					
				}
				
			}
			
			
			
			
			
			
			retVal="1";
			
			
		}
		
		
		
		return retVal;
	}

	@Override
	public String applyQuotCompRate(List<OrderCompDt> orderCompDts, List<QuotCompDt> quotCompDts) {
		// TODO Auto-generated method stub
		
		for(OrderCompDt orderCompDt :orderCompDts) {
			if(orderCompDt.getrLock().equals(true)){
				continue;
			}
			
			for(QuotCompDt quotCompDt :quotCompDts) {
				
				if(orderCompDt.getComponent().equals(quotCompDt.getComponent()) && orderCompDt.getPurity().equals(quotCompDt.getPurity())) {
					
					orderCompDt.setCompRate(quotCompDt.getCompRate());
					orderCompDt.setMetalRate(quotCompDt.getMetalRate());
					orderCompDt.setPerPcRate(quotCompDt.getPerPcRate());
					
					
					
				}
				
			}
			
			
		}
		
		return "1";
	}

	@Override
	public String applyQuotLabRate(OrderDt orderDt, QuotDt quotDt, Principal principal) {
		
		List<OrderLabDt>orderLabDts = orderLabDtService.findByOrderDtAndDeactive(orderDt, false);
		List<QuotLabDt>quotLabDts = quotLabDtService.findByQuotDtAndDeactive(quotDt, false);
		if(orderLabDts.size()>0) {
			
			for(QuotLabDt quotLabDt :quotLabDts) {
				Boolean isAvilable =false;
				for(OrderLabDt orderLabDt :orderLabDts) {
					
					if(orderLabDt.getLabourType().equals(quotLabDt.getLabourType()) && orderLabDt.getMetal().equals(quotLabDt.getMetal())) {
						isAvilable =true;
						if(orderLabDt.getrLock().equals(false)) {
							
							orderLabDt.setLabourRate(quotLabDt.getLabourRate());
							orderLabDtService.save(orderLabDt);
							
						}
						
					}
					
					
					
				}
				
				if(isAvilable.equals(false)) {
					OrderLabDt orderLabDt = new OrderLabDt();
					orderLabDt.setCreatedBy(principal.getName());
					orderLabDt.setCreatedDate(new Date());
					orderLabDt.setGramWise(quotLabDt.getGramWise());
					orderLabDt.setLabourRate(quotLabDt.getLabourRate());
					orderLabDt.setLabourType(quotLabDt.getLabourType());
					orderLabDt.setMetal(quotLabDt.getMetal());
					orderLabDt.setOrderDt(orderDt);
					orderLabDt.setOrderMt(orderDt.getOrderMt());
					orderLabDt.setPcsWise(quotLabDt.getPcsWise());
					orderLabDt.setPerCaratRate(quotLabDt.getPerCaratRate());
					orderLabDt.setPercentWise(quotLabDt.getPercentWise());
					
					orderLabDtService.save(orderLabDt);
				}
				
			}
			
			
			
			
			
			
			
			for(OrderLabDt orderLabDt :orderLabDts) {
				if(orderLabDt.getrLock().equals(false)) {
					for(QuotLabDt quotLabDt :quotLabDts) {
						
						if(orderLabDt.getLabourType().equals(quotLabDt.getLabourType()) && orderLabDt.getMetal().equals(quotLabDt.getMetal())) {
							
							orderLabDt.setLabourRate(quotLabDt.getLabourRate());
							orderLabDtService.save(orderLabDt);
							
						}
						
						
					}
					
				}
				
				
			}
			
			
		}else {
			
			for(QuotLabDt quotLabDt :quotLabDts) {
				
				OrderLabDt orderLabDt = new OrderLabDt();
				orderLabDt.setCreatedBy(principal.getName());
				orderLabDt.setCreatedDate(new Date());
				orderLabDt.setGramWise(quotLabDt.getGramWise());
				orderLabDt.setLabourRate(quotLabDt.getLabourRate());
				orderLabDt.setLabourType(quotLabDt.getLabourType());
				orderLabDt.setMetal(quotLabDt.getMetal());
				orderLabDt.setOrderDt(orderDt);
				orderLabDt.setOrderMt(orderDt.getOrderMt());
				orderLabDt.setPcsWise(quotLabDt.getPcsWise());
				orderLabDt.setPerCaratRate(quotLabDt.getPerCaratRate());
				orderLabDt.setPercentWise(quotLabDt.getPercentWise());
				
				orderLabDtService.save(orderLabDt);
				
				
			}
			
			
		}
		
		return "1";
	}

	@Override
	public String orderDtExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile,
			Principal principal, Integer mtId) throws ParseException {
		// TODO Auto-generated method stub
			
		synchronized (this) {
			
			OrderMt orderMt = orderMtService.findOne(mtId);
			
			String retVal ="";
			
			
			try {
				if(!tempExcelFile.isEmpty()){
				
				List<OrderExcel> orderExcelList = new ArrayList<OrderExcel>();
			
				Boolean remarkFlg = false;	
				String remark = "";	
				
				    if (tempExcelFile.endsWith("xlsx")) {
				    	int i=1;
			
				    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				        XSSFSheet worksheet = workbook.getSheetAt(0);
				        
				    
				        while (i <= worksheet.getLastRowNum()) {
				        	 remark = "";	
							OrderExcel orderExcel = new OrderExcel();
							XSSFRow row = worksheet.getRow(i++);
							
							if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
								continue;
							}
							 
						
						
							
							//note : temporary list
							//remark is set in createdBy for temporary list
							//status is set in updatedBy for temporary list
							
						
						
													
							Design design = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString(),false);
							if(design == null){
								if(remark == ""){
									remark = "No such style exists ";
								}else {
									remark += ",No such style exists ";
								}
							}
							
							Purity purity =  purityService.findByName(row.getCell(1).toString());
							if(purity == null){
								if(remark == ""){
									remark = "No such purity exists ";
								}else {
									remark += ",No such purity exists ";
								}
								
								
							}
							
							Color color = colorService.findByName(row.getCell(2).toString());
							if(color == null){
								if(remark == ""){
									remark = "No such color exists ";
								}else {
									remark += ",No such color exists ";
								}
								
							}
							
					
							
							if((row.getCell(0) == null || row.getCell(0).toString().isEmpty() ) && (row.getCell(1)==null || row.getCell(1).toString().isEmpty()) 
									&& (row.getCell(2)==null || row.getCell(2).toString().isEmpty()) && (row.getCell(3)==null || row.getCell(3).toString().isEmpty()) 
									 &&  (row.getCell(13)==null || row.getCell(13).toString().isEmpty()) 
									&& (row.getCell(13)==null || row.getCell(13).toString().isEmpty())) {
								
								remark = "Mandatory fields can not be empty";
							}
							
							if (remark != "") {
								
								orderExcel.setStyleNo(row.getCell(0).toString() !=null ? row.getCell(0).toString() :"");
								orderExcel.setPurity(row.getCell(1).toString() !=null ? row.getCell(1).toString() :"");
								orderExcel.setColor(row.getCell(2).toString() !=null ? row.getCell(2).toString() :"");
								orderExcel.setQty(row.getCell(3).toString() !=null ? Double.parseDouble(row.getCell(3).toString()):0.0);
								orderExcel.setNetWt(row.getCell(4).toString() !=null ?Math.round(Double.parseDouble(row.getCell(4).toString())*1000.0)/1000.0:0.0);
								orderExcel.setProdSize(row.getCell(5).toString().equalsIgnoreCase("-") ? "" :row.getCell(5).toString());
								
								
								orderExcel.setDtRefNo(row.getCell(6).toString() !=null ?  row.getCell(6).toString() :"");		
								orderExcel.setStamp(row.getCell(7).toString().equalsIgnoreCase("-") ? "" :row.getCell(7).toString());
								orderExcel.setItemRemark(row.getCell(8).toString().equalsIgnoreCase("-") ?"" : row.getCell(8).toString());
								
							//All Code change
								
								orderExcel.setOrdRefNo(row.getCell(9).toString().equalsIgnoreCase("-") ? "" :row.getCell(9).toString());
								orderExcel.setItem(row.getCell(10).toString().equalsIgnoreCase("-")  ? "" :row.getCell(10).toString());
								orderExcel.setShape(row.getCell(11).toString().equalsIgnoreCase("-") ? "" :row.getCell(11).toString());
								orderExcel.setQuality(row.getCell(12).toString().equalsIgnoreCase("-") ? "" :row.getCell(12).toString());
								orderExcel.setCarat(row.getCell(13).toString().equalsIgnoreCase("-") ? 0.0:Double.parseDouble(row.getCell(13).toString()));
								orderExcel.setRate(row.getCell(14).toString().equalsIgnoreCase("-")  ? 0.0 : Double.parseDouble(row.getCell(14).toString()));
								orderExcel.setAmount(row.getCell(15).toString().equalsIgnoreCase("-") ? 0.0 : Double.parseDouble(row.getCell(15).toString()));
								orderExcel.setStatusRemark(remark);
								orderExcelList.add(orderExcel);
								remarkFlg = true;
							}
													
						}
						
						workbook.close();
						
				    } else if (tempExcelFile.endsWith("xls")) {
				    	int i = 1;
						HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
						HSSFSheet worksheet = workbook.getSheetAt(0);
						
										
						
					
						while (i <= worksheet.getLastRowNum()) {
							
							 remark = "";	
							OrderExcel orderExcel = new OrderExcel();
							HSSFRow row = worksheet.getRow(i++);
				
							if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
								continue;
							}
							
							
									
							//note : temporary list
							//remark is set in createdBy for temporary list
							//status is set in updatedBy for temporary list
							
			
						
							Design design = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString(),false);
							if(design == null){
								if(remark == ""){
									remark = "No such style exists ";
								}else {
									remark += ",No such style exists ";
								}
							}
							
							Purity purity =  purityService.findByName(row.getCell(1).toString());
							if(purity == null){
								if(remark == ""){
									remark = "No such purity exists ";
								}else {
									remark += ",No such purity exists ";
								}
								
								
							}
							
							Color color = colorService.findByName(row.getCell(2).toString());
							if(color == null){
								if(remark == ""){
									remark = "No such color exists ";
								}else {
									remark += ",No such color exists ";
								}
								
							}
							
							
							if((row.getCell(0) == null || row.getCell(0).toString().isEmpty() ) && (row.getCell(1)==null || row.getCell(1).toString().isEmpty()) 
									&& (row.getCell(2)==null || row.getCell(2).toString().isEmpty()) && (row.getCell(3)==null || row.getCell(3).toString().isEmpty()) 
									 &&  (row.getCell(13)==null || row.getCell(13).toString().isEmpty()) 
									&& (row.getCell(13)==null || row.getCell(13).toString().isEmpty())) {
								
								remark = "Mandatory fields can not be empty";
							}
							
							if (remark != "") {
								
								orderExcel.setStyleNo(row.getCell(0).toString() !=null ? row.getCell(0).toString() :"");
								orderExcel.setPurity(row.getCell(1).toString() !=null ? row.getCell(1).toString() :"");
								orderExcel.setColor(row.getCell(2).toString() !=null ? row.getCell(2).toString() :"");
								orderExcel.setQty(row.getCell(3).toString() !=null ? Double.parseDouble(row.getCell(3).toString()):0.0);
								orderExcel.setNetWt(row.getCell(4).toString() !=null ?Math.round(Double.parseDouble(row.getCell(4).toString())*1000.0)/1000.0:0.0);
								orderExcel.setProdSize(row.getCell(5).toString().equalsIgnoreCase("-") ? "" :row.getCell(5).toString());
								
								
								orderExcel.setDtRefNo(row.getCell(6).toString() !=null ? row.getCell(6).toString() :"");		
								orderExcel.setStamp(row.getCell(7).toString().equalsIgnoreCase("-") ? "" :row.getCell(7).toString());
								orderExcel.setItemRemark(row.getCell(8).toString().equalsIgnoreCase("-") ?"" : row.getCell(8).toString());
								
							//All Code change
								
								orderExcel.setOrdRefNo(row.getCell(9).toString().equalsIgnoreCase("-") ? "" :row.getCell(9).toString());
								orderExcel.setItem(row.getCell(10).toString().equalsIgnoreCase("-")  ? "" :row.getCell(10).toString());
								orderExcel.setShape(row.getCell(11).toString().equalsIgnoreCase("-") ? "" :row.getCell(11).toString());
								orderExcel.setQuality(row.getCell(12).toString().equalsIgnoreCase("-") ? "" :row.getCell(12).toString());
								orderExcel.setCarat(row.getCell(13).toString().equalsIgnoreCase("-") ? 0.0:Double.parseDouble(row.getCell(13).toString()));
								orderExcel.setRate(row.getCell(14).toString().equalsIgnoreCase("-")  ? 0.0 : Double.parseDouble(row.getCell(14).toString()));
								orderExcel.setAmount(row.getCell(15).toString().equalsIgnoreCase("-") ? 0.0 : Double.parseDouble(row.getCell(15).toString()));
								orderExcel.setStatusRemark(remark);
								orderExcelList.add(orderExcel);
								remarkFlg = true;
							}
							
							
						}

						workbook.close();
						
				    } else {
				        throw new IllegalArgumentException("The specified file is not Excel file");
				    }
				    
				    
				    // Create Order
				    
				    if (remarkFlg) {
				    	 session.setAttribute("orderExcelSessionList", orderExcelList);  
				    	 retVal="yes";
				    	
				    }else {
				    	
				    	
				    	   if (tempExcelFile.endsWith("xlsx")) {
						    	int i=1;
					
						    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
						        XSSFSheet worksheet = workbook.getSheetAt(0);
						     
						      
						    	Integer dtId = 0;
						        
						        while (i <= worksheet.getLastRowNum()) {
									
									XSSFRow row = worksheet.getRow(i++);
									
									if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
										continue;
									}
									 
								
									//note : temporary list
									//remark is set in createdBy for temporary list
									//status is set in updatedBy for temporary list
																						
									Design design = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString(),false);
									
									Purity purity =  purityService.findByName(row.getCell(1).toString());
									
									Color color = colorService.findByName(row.getCell(2).toString());
									
									Shape shape = null;
									if(row.getCell(11).toString().equalsIgnoreCase("-")) {
										shape = null;
									}else {
									 shape = shapeService.findByName(row.getCell(11).toString());	
									}
									
									Quality quality = null;
									if(row.getCell(12).toString().equalsIgnoreCase("-")) {
										quality = null;
									}else {
										if(shape != null) {
											quality  = qualityService.findByShapeAndName(shape,row.getCell(12).toString());	
										}
											
									}
									
									
									ProductSize productSize =  null;
									if(row.getCell(5).toString().equalsIgnoreCase("-")) {
										productSize= null;
									}else {
										 productSize = productSizeService.findByName(row.getCell(5).toString());
										
									}
									
									
									
									ClientStamp clientStamp =  null;
									String clientStampNm = null;
									if(row.getCell(7).toString().equalsIgnoreCase("-")) {
										if(orderMt.getParty() != null ) {
											clientStamp = clientStampService.findByPartyAndPurityAndDeactive(orderMt.getParty(), purity, false);	
											
											 if(clientStamp != null) {
												  clientStampNm = clientStamp.getStampNm(); 
											 }
										}
										
									}else {
										clientStampNm = row.getCell(7).toString();
									}
										
											
											
											OrderDt orderDt =  new OrderDt();
											orderDt.setOrderMt(orderMt);
											orderDt.setPurity(purity);
											orderDt.setColor(color);
											orderDt.setDesign(design);
											orderDt.setPcs(Double.parseDouble(row.getCell(3).toString()));
											orderDt.setStampInst(clientStampNm);
											orderDt.setItem(row.getCell(10).toString().equalsIgnoreCase("-") ? "" :row.getCell(10).toString());
											orderDt.setOrdRef(row.getCell(9).toString().equalsIgnoreCase("-")  ? "" :row.getCell(9).toString());
											orderDt.setRemark(row.getCell(8).toString().equalsIgnoreCase("-")  ? "" :row.getCell(8).toString());
											orderDt.setRefNo(row.getCell(6).toString().equalsIgnoreCase("-")  ? "" :row.getCell(6).toString().replaceAll("[\\n\\t\\r ]", " ").trim());
											orderDt.setDesignRemark(design.getRemarks());
											
											if(row.getCell(4).toString().equalsIgnoreCase("0") || row.getCell(4).toString().equalsIgnoreCase("0.0")) {
												orderDt.setNetWt(Math.round((design.getWaxWt() * purity.getWaxWtConv())*1000.0)/1000.0);
											}else {
												orderDt.setNetWt(Math.round(Double.parseDouble(row.getCell(4).toString())*1000.0)/1000.0);	
											}
											
											//Set SrNo
											
											Integer maxSrno =getMaxSrNo(orderMt.getId());
											if(maxSrno==null){
												maxSrno=1;
											}else{
												maxSrno +=1;
											}
											
											
											orderDt.setCreatedDate(new Date());
											orderDt.setCreatedBy(principal.getName());
											orderDt.setSrNo(maxSrno);
											orderDt.setProductSize(productSize);
											orderDt.setBarcode(row.getCell(16).toString().equalsIgnoreCase("-") ? "" :row.getCell(16).toString());
											
											save(orderDt);
											
									//		srNo = orderDt.getSrNo();
											dtId = orderDt.getId();
											
											List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
											orderMetalService.setOrderMetalDt(designMetals, orderMt, orderDt, principal);
											
											List<DesignStone> designStones = designStoneService.findByDesign(design); 
											orderStnDtService.setOrderStnDt(designStones, orderMt, orderDt,principal);
											
											List<DesignComponent> designComponents = designComponentService.findByDesign(design);
											orderCompDtService.setOrderCompDt(designComponents, orderMt, orderDt, principal);
											
											Double totalReqCarat =0.0;
											for(DesignStone designStone : designStones) {
												totalReqCarat += designStone.getCarat();
											}
											
											
									//		quotDt.setReqCarat(Math.round((totalReqCarat)*1000.0)/1000.0);
									//		save(quotDt);
										
										
										
										
									/*
									 * List<OrderStnDt> orderStnDts =
									 * orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
									 * 
									 * Double totqltyCarat = 0.0;
									 * 
									 * if(shape !=null) {
									 * 
									 * for(OrderStnDt orderStnDt:orderStnDts){
									 * 
									 * if(orderStnDt.getShape().equals(shape)){
									 * 
									 * if(orderStnDt.getShape().getName().equalsIgnoreCase("ROUND")) {
									 * 
									 * 
									 * 
									 * if(row.getCell(12).toString().contains("S/C") &&
									 * Double.parseDouble(orderStnDt.getSize())<0.90){
									 * 
									 * //orderStnDt.setCarat(Double.parseDouble(row.getCell(20).toString()));
									 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
									 * orderStnDt.setQuality(quality);
									 * 
									 * totqltyCarat +=orderStnDt.getCarat();
									 * 
									 * }else if(row.getCell(12).toString().contains("D/C") &&
									 * Double.parseDouble(orderStnDt.getSize())>=0.90) {
									 * 
									 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
									 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat();
									 * 
									 * } else if(!row.getCell(12).toString().contains("D/C") &&
									 * !row.getCell(12).toString().contains("S/C")){
									 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
									 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat(); }
									 * 
									 * 
									 * 
									 * 
									 * 
									 * }else {
									 * 
									 * 
									 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(7).toString()));
									 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat();
									 * 
									 * 
									 * }
									 * 
									 * }
									 * 
									 * }
									 * 
									 * }
									 * 
									 * 
									 * if(quality != null) {
									 * 
									 * @SuppressWarnings("unchecked") TypedQuery<OrderStnDt> query =
									 * (TypedQuery<OrderStnDt>) entityManager
									 * .createNativeQuery("select * from sordstndt " + " where dtid =" + dtId +
									 * " and qualityid = "+quality.getId()+" ORDER BY carat DESC LIMIT 1 " ,
									 * OrderStnDt.class);
									 * 
									 * List<OrderStnDt> orderStnDt2 = query.getResultList();
									 * 
									 * Double perPcsDiawt = Double.parseDouble(row.getCell(13).toString()) /
									 * Double.parseDouble(row.getCell(3).toString());
									 * 
									 * Double vCaratDiff =Math.round((perPcsDiawt-totqltyCarat)*1000.0)/1000.0;
									 * 
									 * 
									 * for(OrderStnDt orderStnDt : orderStnDt2) {
									 * orderStnDt.setCarat(Math.round((orderStnDt.getCarat()+vCaratDiff)*1000.0)/
									 * 1000.0); orderStnDtService.save(orderStnDt);
									 * 
									 * }
									 * 
									 * }
									 */  
										List<OrderStnDt> orderStnDts3 = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
												Double totStnCarat = 0.0;
											  for(OrderStnDt orderStnDt : orderStnDts3) {
												  totStnCarat +=orderStnDt.getCarat();
											  }
											  
										
											  orderDt.setGrossWt(Math.round((orderDt.getNetWt()+(totStnCarat/5))*100.0)/100.0);
											  save(orderDt);
										
									updateKtDesc(orderDt.getId());
									updateQltyDesc(orderDt.getId());		
								
								
								}

								
								
								workbook.close();
								
						    } else if (tempExcelFile.endsWith("xls")) {
						    	int i = 1;
								HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
								HSSFSheet worksheet = workbook.getSheetAt(0);
								
												
								Integer dtId = 0;
							    	
							        
									while (i <= worksheet.getLastRowNum()) {
										HSSFRow row = worksheet.getRow(i++);
							
										if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
											continue;
										}
										 
									
										//note : temporary list
										//remark is set in createdBy for temporary list
										//status is set in updatedBy for temporary list
																							
										Design design = designService.findByMainStyleNoAndDeactive(row.getCell(0).toString(),false);
										
										Purity purity =  purityService.findByName(row.getCell(1).toString());
										
										Color color = colorService.findByName(row.getCell(2).toString());
										
										Shape shape = null;
										if(row.getCell(11).toString().equalsIgnoreCase("-")) {
											shape = null;
										}else {
										 shape = shapeService.findByName(row.getCell(11).toString());	
										}
										
										Quality quality = null;
										if(row.getCell(12).toString().equalsIgnoreCase("-")) {
											quality = null;
										}else {
											if(shape != null) {
												quality  = qualityService.findByShapeAndName(shape,row.getCell(12).toString());	
											}
												
										}
										
										
										ProductSize productSize =  null;
										if(row.getCell(5).toString().equalsIgnoreCase("-")) {
											productSize= null;
										}else {
											 productSize = productSizeService.findByName(row.getCell(5).toString());
											
										}
										
										
										
										ClientStamp clientStamp =  null;
										String clientStampNm = null;
										if(row.getCell(7).toString().equalsIgnoreCase("-")) {
											if(orderMt.getParty() != null ) {
												clientStamp = clientStampService.findByPartyAndPurityAndDeactive(orderMt.getParty(), purity, false);	
												
												 if(clientStamp != null) {
													  clientStampNm = clientStamp.getStampNm(); 
												 }
											}
											
										}else {
											clientStampNm = row.getCell(7).toString();
										}
											
												
												
												OrderDt orderDt =  new OrderDt();
												orderDt.setOrderMt(orderMt);
												orderDt.setPurity(purity);
												orderDt.setColor(color);
												orderDt.setDesign(design);
												orderDt.setPcs(Double.parseDouble(row.getCell(3).toString()));
												orderDt.setStampInst(clientStampNm);
												orderDt.setItem(row.getCell(10).toString().equalsIgnoreCase("-") ? "" :row.getCell(10).toString());
												orderDt.setOrdRef(row.getCell(9).toString().equalsIgnoreCase("-")  ? "" :row.getCell(9).toString());
												orderDt.setRemark(row.getCell(8).toString().equalsIgnoreCase("-")  ? "" :row.getCell(8).toString());
												orderDt.setRefNo(row.getCell(6).toString().equalsIgnoreCase("-")  ? "" :row.getCell(6).toString());
												orderDt.setDesignRemark(design.getRemarks());
												
												if(row.getCell(4).toString().equalsIgnoreCase("0") || row.getCell(4).toString().equalsIgnoreCase("0.0")) {
													orderDt.setNetWt(Math.round((design.getWaxWt() * purity.getWaxWtConv())*1000.0)/1000.0);
												}else {
													orderDt.setNetWt(Math.round(Double.parseDouble(row.getCell(4).toString())*1000.0)/1000.0);	
												}
												
												
												//Set SrNo
												
												Integer maxSrno =getMaxSrNo(orderMt.getId());
												if(maxSrno==null){
													maxSrno=1;
												}else{
													maxSrno +=1;
												}
												
												orderDt.setCreatedDate(new Date());
												orderDt.setCreatedBy(principal.getName());
												orderDt.setSrNo(maxSrno);
												orderDt.setProductSize(productSize);
												orderDt.setBarcode(row.getCell(16).toString().equalsIgnoreCase("-") ? "" :row.getCell(16).toString());
												
												save(orderDt);
												
										//		srNo = orderDt.getSrNo();
												dtId = orderDt.getId();
												
												List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
												orderMetalService.setOrderMetalDt(designMetals, orderMt, orderDt, principal);
												
												List<DesignStone> designStones = designStoneService.findByDesign(design); 
												orderStnDtService.setOrderStnDt(designStones, orderMt, orderDt,principal);
												
												List<DesignComponent> designComponents = designComponentService.findByDesign(design);
												orderCompDtService.setOrderCompDt(designComponents, orderMt, orderDt, principal);
												
												Double totalReqCarat =0.0;
												for(DesignStone designStone : designStones) {
													totalReqCarat += designStone.getCarat();
												}
												
												
										//		quotDt.setReqCarat(Math.round((totalReqCarat)*1000.0)/1000.0);
										//		save(quotDt);
											
											
											
											
										/*
										 * List<OrderStnDt> orderStnDts =
										 * orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
										 * 
										 * Double totqltyCarat = 0.0;
										 * 
										 * if(shape != null) {
										 * 
										 * for(OrderStnDt orderStnDt:orderStnDts){
										 * 
										 * if(orderStnDt.getShape().equals(shape)){
										 * 
										 * if(orderStnDt.getShape().getName().equalsIgnoreCase("ROUND")) {
										 * 
										 * 
										 * 
										 * if(row.getCell(12).toString().contains("S/C") &&
										 * Double.parseDouble(orderStnDt.getSize())<0.90){
										 * 
										 * //orderStnDt.setCarat(Double.parseDouble(row.getCell(20).toString()));
										 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * orderStnDt.setQuality(quality);
										 * 
										 * totqltyCarat +=orderStnDt.getCarat();
										 * 
										 * }else if(row.getCell(12).toString().contains("D/C") &&
										 * Double.parseDouble(orderStnDt.getSize())>=0.90) {
										 * 
										 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat();
										 * 
										 * } else if(!row.getCell(12).toString().contains("D/C") &&
										 * !row.getCell(12).toString().contains("S/C")){
										 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat(); }
										 * 
										 * 
										 * 
										 * 
										 * 
										 * }else {
										 * 
										 * 
										 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(7).toString()));
										 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat();
										 * 
										 * 
										 * }
										 * 
										 * }
										 * 
										 * } }
										 * 
										 * 
										 * 
										 * if(quality != null) {
										 * 
										 * @SuppressWarnings("unchecked") TypedQuery<OrderStnDt> query =
										 * (TypedQuery<OrderStnDt>) entityManager
										 * .createNativeQuery("select * from sordstndt " + " where dtid =" + dtId +
										 * " and qualityid = "+quality.getId()+" ORDER BY carat DESC LIMIT 1 " ,
										 * OrderStnDt.class);
										 * 
										 * List<OrderStnDt> orderStnDt2 = query.getResultList();
										 * 
										 * Double perPcsDiawt = Double.parseDouble(row.getCell(13).toString()) /
										 * Double.parseDouble(row.getCell(3).toString());
										 * 
										 * Double vCaratDiff =Math.round((perPcsDiawt-totqltyCarat)*1000.0)/1000.0;
										 * 
										 * 
										 * for(OrderStnDt orderStnDt : orderStnDt2) {
										 * orderStnDt.setCarat(Math.round((orderStnDt.getCarat()+vCaratDiff)*1000.0)/
										 * 1000.0); orderStnDtService.save(orderStnDt);
										 * 
										 * }
										 * 
										 * }
										 */	
											
											List<OrderStnDt> orderStnDts3 = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
													Double totStnCarat = 0.0;
												  for(OrderStnDt orderStnDt : orderStnDts3) {
													  totStnCarat +=orderStnDt.getCarat();
												  }
												  
											
												  orderDt.setGrossWt(Math.round((orderDt.getNetWt()+(totStnCarat/5))*100.0)/100.0);
												  save(orderDt);
												  
											updateKtDesc(orderDt.getId());
											updateQltyDesc(orderDt.getId());		
									
									
									}

								workbook.close();
								
						    }
				    	
				    	retVal="1";
				    	   
				    }
				    
				    
				    
				    
				    
				   
					}
				}catch (IOException e) {
					
					e.printStackTrace();
				}
			
			
			
			return retVal;
			
			
		}
	}
	
}

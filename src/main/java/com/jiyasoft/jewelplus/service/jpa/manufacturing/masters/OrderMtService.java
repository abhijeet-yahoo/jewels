package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientReference;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QOrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IOrderMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientRefService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientStampService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class OrderMtService implements IOrderMtService {

	@Autowired
	private IOrderMtRepository orderMtRepository;
	
	@Autowired
	private IPartyService partyService;

	@Autowired
	private IOrderDtService orderDtService;

	
	@Autowired
	private IOrderQualityService orderQualityService;
	
	@Autowired
	private IClientRefService clientRefService;
	
	@Autowired
	private IBagMtService bagMtService;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IQualityService qualityService;
	

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IShapeService shapeService;
	

	@Autowired
	private IDesignService designService;


	@Autowired
	private IOrderTypeService orderTypeService;
	
	@Autowired
	private IDesignComponentService designComponentService;
	
	@Autowired
	private IOrderCompDtService orderCompDtService;
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private IOrderStnDtService orderStnDtService;
	
	@Autowired
	private IOrderMetalService orderMetalService;
	
	@Autowired
	private IDesignMetalService designMetalService;
	
	@Autowired
	private IProductSizeService productSizeService;
	
	@Autowired
	private IClientStampService clientStampService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private ITranMtService tranMtService;

	@Override
	public List<OrderMt> findAll() {
		return orderMtRepository.findAll();
	}

	@Override
	public Page<OrderMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanExpression expression = qOrderMt.deactive.eq(false);
		
		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10000;
		}

		if (order == null) {
			order = "ASC";
		}

		if (sort == null) {
			sort = "id";
		}

		return orderMtRepository.findAll(expression, new PageRequest(page, limit, Direction.DESC, sort));
		}

	@Override
	public void save(OrderMt orderMt) {
		orderMtRepository.save(orderMt);
	}

	@Override
	public String delete(int id) {
		
		OrderMt orderMt = orderMtRepository.findOne(id);
		List<BagMt> bagMtList = bagMtService.findByOrderMt(orderMt);
		
		String retVal="-1";

		Boolean lFound = false;
		
		if(bagMtList.size()>0 ){
			lFound = true;
			return "1";
		}
		
		

		if (!(lFound)) {
			List<OrderDt> orderDtList =orderDtService.findByOrderMtAndDeactive(orderMt, false);
			for (OrderDt orderDt : orderDtList) {
			/*	orderDt.setDeactive(true);
				orderDt.setDeactiveDt(new java.util.Date());*/
				
				List<StoneTran> stoneTrans =  stoneTranService.findBySordDtIdAndDeactive(orderDt.getId(), false);
				if(stoneTrans.size() > 0) {
					return "2";
				}
				
				
			}
			
			
			for (OrderDt orderDt : orderDtList) {
				/*	orderDt.setDeactive(true);
					orderDt.setDeactiveDt(new java.util.Date());*/
					
				
						orderDtService.delete(orderDt.getId());	
					
					
					
				}
			
			List<OrderQuality> orderQualities =orderQualityService.findByOrderMtAndDeactive(orderMt, false);
			for(OrderQuality orderQuality :orderQualities){
				orderQuality.setDeactive(true);
				orderQuality.setDeactiveDt(new java.util.Date());
				orderQualityService.save(orderQuality);
			}
			
			

				/*List<OrderStnDt> orderStnDtList = orderStnDtRepository.findByOrderMtAndOrderDt(orderMt, orderDt);
				for (OrderStnDt orderStnDt : orderStnDtList) {
					orderStnDt.setDeactive(true);
					orderStnDt.setDeactiveDt(new java.util.Date());
				}

				List<OrderCompDt> orderCompDtList = orderCompDtRepository.findByOrderMtAndOrderDt(orderMt, orderDt);
				for (OrderCompDt orderCompDt : orderCompDtList) {
					orderCompDt.setDeactive(true);
					orderCompDt.setDeactiveDt(new java.util.Date());
				}*/
		

			orderMt.setDeactive(true);
			orderMt.setDeactiveDt(new java.util.Date());
			orderMtRepository.save(orderMt);
		}

		return retVal;
	}

	@Override
	public Long count() {
		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanExpression expression = qOrderMt.deactive.eq(false);

		return orderMtRepository.count(expression);
	}

	@Override
	public OrderMt findOne(int id) {
		return orderMtRepository.findOne(id);
	}

	

	@Override
	public Page<OrderMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String invNo, Boolean onlyActive) {

		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanExpression expression = null;
		
		
		if (onlyActive) {
			if (invNo == null) {
				expression = qOrderMt.deactive.eq(false);
			} else {
				expression = qOrderMt.deactive.eq(false).and(
						qOrderMt.invNo.like(invNo + "%"));
			}
		} else {
			if (invNo != null) {
				expression = qOrderMt.invNo.like(invNo + "%");
			}
		}

		//int page = (offset == 0 ? 0 : (offset / limit));
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}
		
		if (sort == null) {
			sort = "id";
		}else if(sort.equalsIgnoreCase("invNo")){
			sort = "invNo";
			
		}
		Page<OrderMt> orderMtList = (Page<OrderMt>) orderMtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return orderMtList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanExpression expression = qOrderMt.deactive.eq(false);
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qOrderMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("invno") && colValue != null) {
				expression = qOrderMt.deactive.eq(false).and(
						qOrderMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invno"))
					&& colValue != null) {
				expression = qOrderMt.invNo.like(colValue + "%");
			}
		}
		
				
		return orderMtRepository.count(expression);
	}

	@Override
	public OrderMt findByUniqueId(Long uniqueId) {
		return orderMtRepository.findByUniqueId(uniqueId);
	}

	

	@Override
	public Long getMaxSrno() {
		QOrderMt qOrderMt = QOrderMt.orderMt;
		JPAQuery query = new JPAQuery(entityManager);
		Long retVal = -1l;

		Calendar date = new GregorianCalendar();

		List<Long> maxSrno = query
				.from(qOrderMt)
				.where(qOrderMt.deactive.eq(false).and(
						qOrderMt.createdDt.year().eq(date.get(Calendar.YEAR)))).list(qOrderMt.srno.max());

		for (Long srno : maxSrno) {
			retVal = srno;
		}
	
		return retVal;
	}

/*	public static void main(String[] args) {
		Calendar date = new GregorianCalendar();
		String vYear = "" + date.get(Calendar.YEAR);
		vYear = vYear.substring(2);

		System.out.println("\n\n\n\n\nretVal " + "" + " YEAR - "
				+ date.get(Calendar.YEAR) + " MONTH - "
				+ date.get(Calendar.MONTH) + " " + new java.util.Date());
	}*/

	
	@Override
	public Page<OrderMt> findByParty(Integer limit, Integer offset,
			String sort, String order, String party, Boolean onlyActive) {
		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (party == null) {
				expression = qOrderMt.deactive.eq(false);
			} else {
				expression = qOrderMt.deactive.eq(false).and(
						qOrderMt.party.name.like(party + "%"));
			}
		} else {
			if (party != null) {
				expression = qOrderMt.invNo.like(party + "%");
			}
		}

				
		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}
		
				
		sort = "party.name";
		
			
		Page<OrderMt> orderMtList = (Page<OrderMt>) orderMtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return orderMtList;
	}

	
	@Override
	public List<OrderMt> getOrderList(String partyIds, String orderTypeIds,String ordFlg,String fromDate,String toDate,String divisionIds,
			String regionIds,String customerTypeIds) throws ParseException {
		
		List<Integer> partyList = new ArrayList<Integer>();
		
		if(partyIds.length() > 0){
			String vPartyId[] = partyIds.split(",");
			for(int i=0;i<vPartyId.length;i++){
				partyList.add(Integer.parseInt(vPartyId[i]));
			}
		}
		
		
		List<Integer> orderTypeList = new ArrayList<Integer>();
		
		if(orderTypeIds.length() > 0){
			String vOrderTypeId[] = orderTypeIds.split(",");
			for(int i=0;i<vOrderTypeId.length;i++){
				orderTypeList.add(Integer.parseInt(vOrderTypeId[i]));
			}	
		}
		
		
		List<Integer> divisionList = new ArrayList<Integer>();
		
		if(divisionIds.length() > 0){
			String vDivisionId[] = divisionIds.split(",");
			for(int i=0;i<vDivisionId.length;i++){
				divisionList.add(Integer.parseInt(vDivisionId[i]));
			}	
		}
		
		
		List<Integer> regionList = new ArrayList<Integer>();
		
		if(regionIds.length() > 0){
			String vRegionId[] = regionIds.split(",");
			for(int i=0;i<vRegionId.length;i++){
				regionList.add(Integer.parseInt(vRegionId[i]));
			}	
		}
		
		
		List<Integer> customerTypeList = new ArrayList<Integer>();
		
		if(customerTypeIds.length() > 0){
			String vCustomerId[] = customerTypeIds.split(",");
			for(int i=0;i<vCustomerId.length;i++){
				customerTypeList.add(Integer.parseInt(vCustomerId[i]));
			}	
		}
		
		
		Boolean orderFlg = false;
		if(ordFlg.equalsIgnoreCase("1")){
			orderFlg = false;
		}else if(ordFlg.equalsIgnoreCase("2")){
			orderFlg = true;
		}
		

		
		
		QOrderMt qOrderMt = QOrderMt.orderMt;
		JPAQuery query = new JPAQuery(entityManager);
		
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
		
		BooleanBuilder where = new BooleanBuilder();
		
		if(!fromDate.isEmpty() && !toDate.isEmpty()){
			Date fromDate1 = dfInput.parse(fromDate);
			Date toDate1 = dfInput.parse(toDate);
			where.and(qOrderMt.invDate.between(fromDate1, toDate1));
		}
		
		if(partyList.size() > 0) {
			where.and(qOrderMt.party.id.in(partyList));
		}
		
		if(orderTypeList.size() > 0) {
			where.and(qOrderMt.orderType.id.in(orderTypeList));
		}
		
		if(divisionList.size() > 0) {
			where.and(qOrderMt.ordDivision.id.in(divisionList));
		}
		
		if(regionList.size() > 0) {
			where.and(qOrderMt.party.partyRegion.id.in(regionList));
		}
		
		if(customerTypeList.size() > 0) {
			where.and(qOrderMt.party.customerType.id.in(customerTypeList));
		}
		
		
		List<OrderMt> orderMts = (List<OrderMt>) orderMtRepository.findAll(where);
		
		return orderMts;
		
//		if(!fromDate.isEmpty() && !toDate.isEmpty()) {
//			
//			SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
//			
//			Date fromDate1 = dfInput.parse(fromDate);
//			Date toDate1 = dfInput.parse(toDate);
//			
//			if(partyList.size() > 0 && orderTypeList.size() > 0 && divisionList.size() > 0){
//				orderMts = query.from(qOrderMt).
//						where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.party.id.in(partyList)).and(qOrderMt.invDate.between(fromDate1, toDate1))
//								.and(qOrderMt.orderType.id.in(orderTypeList)).and(qOrderMt.ordDivision.id.in(divisionList))).orderBy(qOrderMt.id.desc()).list(qOrderMt);
//			}
//			else if(partyList.size() > 0 && orderTypeList.size() <= 0 && divisionList.size() > 0){
//				orderMts = query.from(qOrderMt).
//						where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.party.id.in(partyList)).and(qOrderMt.ordDivision.id.in(divisionList))
//								.and(qOrderMt.invDate.between(fromDate1, toDate1))).orderBy(qOrderMt.id.desc()).list(qOrderMt);
//			}
//			else if(partyList.size() > 0 && orderTypeList.size() <= 0 && divisionList.size() <= 0){
//				orderMts = query.from(qOrderMt).
//						where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.party.id.in(partyList))
//								.and(qOrderMt.invDate.between(fromDate1, toDate1))).orderBy(qOrderMt.id.desc()).list(qOrderMt);
//			}
//			else if(partyList.size() <= 0 && orderTypeList.size() > 0 && divisionList.size() > 0){
//				orderMts = query.from(qOrderMt).
//						where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.orderType.id.in(orderTypeList))
//								.and(qOrderMt.ordDivision.id.in(divisionList)).and(qOrderMt.invDate.between(fromDate1, toDate1)))
//						.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//			}
//			else if(partyList.size() <= 0 && orderTypeList.size() > 0 && divisionList.size() <= 0){
//				orderMts = query.from(qOrderMt).
//						where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.orderType.id.in(orderTypeList))
//								.and(qOrderMt.invDate.between(fromDate1, toDate1)))
//						.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//			}
//			else if(partyList.size() > 0 && orderTypeList.size() > 0 && divisionList.size() <= 0){
//				orderMts = query.from(qOrderMt).
//						where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.orderType.id.in(orderTypeList))
//								.and(qOrderMt.party.id.in(partyList)).and(qOrderMt.invDate.between(fromDate1, toDate1)))
//						.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//			}
//			else if(partyList.size() <= 0 && orderTypeList.size() <= 0 && divisionList.size() > 0){
//				orderMts = query.from(qOrderMt).
//						where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.ordDivision.id.in(divisionList))
//								.and(qOrderMt.invDate.between(fromDate1, toDate1)))
//						.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//			}
//			else{
//				orderMts = query.from(qOrderMt).
//						where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.invDate.between(fromDate1, toDate1)))
//							.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//			}
//			
//		}else {
//			
//		
//		
//		if(partyList.size() > 0 && orderTypeList.size() > 0  && divisionList.size() > 0 ){
//			orderMts = query.from(qOrderMt).
//					where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.party.id.in(partyList))
//							.and(qOrderMt.orderType.id.in(orderTypeList)).and(qOrderMt.ordDivision.id.in(divisionList))).orderBy(qOrderMt.id.desc()).list(qOrderMt);
//		}
//		else if(partyList.size() > 0 && orderTypeList.size() <= 0 && divisionList.size() > 0 ){				
//			orderMts = query.from(qOrderMt).
//					where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.party.id.in(partyList))
//							.and(qOrderMt.ordDivision.id.in(divisionList))).orderBy(qOrderMt.id.desc()).list(qOrderMt);
//		}
//		else if(partyList.size() <= 0 && orderTypeList.size() > 0 && divisionList.size() > 0 ){				
//			orderMts = query.from(qOrderMt).
//					where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.orderType.id.in(orderTypeList))
//							.and(qOrderMt.ordDivision.id.in(divisionList))).orderBy(qOrderMt.id.desc()).list(qOrderMt);
//		}
//		else if(partyList.size() <= 0 && orderTypeList.size() <=0 && divisionList.size() > 0 ){
//			orderMts = query.from(qOrderMt).
//					where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.ordDivision.id.in(divisionList)))
//					.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//		}
//		else if(partyList.size() > 0 && orderTypeList.size() <= 0 && divisionList.size() <= 0){  			
//			orderMts = query.from(qOrderMt).
//					where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.party.id.in(partyList)))
//					.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//		}
//		else if(partyList.size() <= 0 && orderTypeList.size() > 0 && divisionList.size() <= 0){				
//			orderMts = query.from(qOrderMt).
//					where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.orderType.id.in(orderTypeList)))
//					.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//		}
//		else if(partyList.size() > 0 && orderTypeList.size() > 0 && divisionList.size() <= 0){		
//			orderMts = query.from(qOrderMt).
//					where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)).and(qOrderMt.orderType.id.in(orderTypeList))
//							.and(qOrderMt.party.id.in(partyList)))
//					.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//		}
//		
//		else{
//			orderMts = query.from(qOrderMt).
//					where(qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(orderFlg)))
//						.orderBy(qOrderMt.id.desc()).list(qOrderMt);
//		}
//		
//		}
		
		
		
		
	//	return orderMts;
	}

	
	@Override
	public Long count(String partyIds, String orderTypeIds) {
		
		List<Integer> partyList = new ArrayList<Integer>();
		
		if(partyIds.length() > 0){
			String vPartyId[] = partyIds.split(",");
			for(int i=0;i<vPartyId.length;i++){
				partyList.add(Integer.parseInt(vPartyId[i]));
			}
		}else{
			partyList.add(0);
		}
		
		
		List<Integer> orderTypeList = new ArrayList<Integer>();
		
		if(orderTypeIds.length() > 0){
			String vOrderTypeId[] = orderTypeIds.split(",");
			for(int i=0;i<vOrderTypeId.length;i++){
				orderTypeList.add(Integer.parseInt(vOrderTypeId[i]));
			}	
		}else{
			orderTypeList.add(0);
		}
		
		
		QOrderMt qOrderMt = QOrderMt.orderMt;
		
		BooleanExpression expression = null;
		
		if(partyList.size() > 0 && orderTypeList.size() > 0){
			expression = qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(false)).and(qOrderMt.party.id.in(partyList).and(qOrderMt.orderType.id.in(orderTypeList)));
		}else if(partyList.size() > 0 && orderTypeList.size() <= 0){
			expression = qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(false)).and(qOrderMt.party.id.in(partyList));
		}else if(partyList.size() <= 0 && orderTypeList.size() > 0){
			expression = qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(false)).and(qOrderMt.orderType.id.in(orderTypeList));
		}else{
			expression = qOrderMt.deactive.eq(false).and(qOrderMt.orderClose.eq(false));
		}

		return orderMtRepository.count(expression);
	}


	@Override
	public List<OrderMt> findByOrderCloseAndDeactive(Boolean orderClose,
			Boolean deactive) {
		return orderMtRepository.findByOrderCloseAndDeactive(orderClose, deactive);
	}

	@Override
	public Long countAll(String colValue) {
		
		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanExpression expression = qOrderMt.deactive.eq(false);
		
			if(colValue!=null && colValue !="" ){
					
				expression = qOrderMt.deactive.eq(false).and(qOrderMt.invNo.like(colValue + "%"));
			}
		
			
		 return orderMtRepository.count(expression);
	}

	@Override
	public Page<OrderMt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name) {
		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanExpression expression = qOrderMt.deactive.eq(false);

		if(name !=null && name !=""){
			expression = qOrderMt.deactive.eq(false).and(qOrderMt.invNo.like("%"+name+"%").or(qOrderMt.refNo.like(name+"%")).or(qOrderMt.party.name.like(name+"%")));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<OrderMt> orderMtList = (Page<OrderMt>) orderMtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));

		
		
		return orderMtList;
		
	}

	@Override
	public OrderMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return orderMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Page<OrderMt> findByInvNoListByParty(Integer limit, Integer offset,
			String sort, String order, String invNo, Boolean onlyActive,
			String partyNm) {
		
		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanBuilder expression = new BooleanBuilder();

		if (onlyActive) {
			if (invNo == null) {
				expression.and(qOrderMt.deactive.eq(false));
			} else {
				
				if(partyNm != null && !partyNm.trim().isEmpty()){
					
					Party party =partyService.findByName(partyNm);
					
					expression.and(qOrderMt.deactive.eq(false).and(
							qOrderMt.invNo.like(invNo + "%")).and(qOrderMt.party.id.eq(party.getId())));
					
				}else{
					expression.and(qOrderMt.deactive.eq(false).and(
							qOrderMt.invNo.like(invNo + "%")));
				}
				
			}
		} else {
			if (invNo != null) {
				expression.and(qOrderMt.invNo.like(invNo + "%"));
			}
		}

		//int page = (offset == 0 ? 0 : (offset / limit));
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<OrderMt> orderMtList = (Page<OrderMt>) orderMtRepository.findAll(expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return orderMtList;
	}

	@Override
	public String orderExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile, Principal principal) throws ParseException {

		
		synchronized (this) {
			
			String retVal ="";
			
			  
			  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			  DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			  
			
			try {
				if(!tempExcelFile.isEmpty()){
				
				List<OrderExcel> orderExcelList = new ArrayList<OrderExcel>();
				String remark = "";	
				Boolean remarkFlg = false;
				
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
							
						
							OrderType orderType = orderTypeService.findByName(row.getCell(0).toString());
							if(orderType == null){
								if(remark == ""){
									remark = "No such Order Type exists"; 
								}else {
									remark += ",No such Order Type exists";
								}
								
								
							}
							
								OrderMt orderMt = findByInvNoAndDeactive(row.getCell(1).toString(),false);
								if(orderMt != null){
									if(remark == ""){
										remark = "Duplicate Order No"; 
									}else {
										remark += ",Duplicate Order No";
									}
									
									
								}
							
							
					
							
							
							Party party = partyService.findByPartyCodeAndDeactive(row.getCell(2).toString(), false);
							if(party == null){
								if(remark == ""){
									remark = "No such client exists ";
								}else {
									remark += ",No such client exists ";
								}
								
								
							}
						
													
							Design design = designService.findByMainStyleNoAndDeactive(row.getCell(7).toString(),false);
							if(design == null){
								if(remark == ""){
									remark = "No such style exists ";
								}else {
									remark += ",No such style exists ";
								}
							}
							
							Purity purity =  purityService.findByName(row.getCell(8).toString());
							if(purity == null){
								if(remark == ""){
									remark = "No such purity exists ";
								}else {
									remark += ",No such purity exists ";
								}
								
								
							}
							
							Color color = colorService.findByName(row.getCell(9).toString());
							if(color == null){
								if(remark == ""){
									remark = "No such color exists ";
								}else {
									remark += ",No such color exists ";
								}
								
							}
							
							ProductSize productSize = productSizeService.findByName(row.getCell(12).toString());
							if(productSize == null){
								if(remark == ""){
									remark = "No such Size exists ";
								}else {
									remark += ",No such Size exists ";
								}
								
							}
							
//							Shape shape = shapeService.findByName(row.getCell(18).toString());
//							if(shape == null){
//								if(remark == ""){
//									remark = "No such shape exists";
//								}else {
//									remark += ",No such shape exists ";
//								}
//								
//							}
//							
//							Quality quality = qualityService.findByShapeAndName(shape,row.getCell(19).toString());
//							if(quality == null){
//								if(remark == ""){
//									remark = "No such quality exists";
//								}else {
//									remark += ",No such quality exists ";
//								}
//							
//								
//							}
							
							
							
							if((row.getCell(0) == null || row.getCell(0).toString().isEmpty() ) && (row.getCell(1)==null || row.getCell(1).toString().isEmpty()) 
									&& (row.getCell(2)==null || row.getCell(2).toString().isEmpty()) && (row.getCell(3)==null || row.getCell(3).toString().isEmpty()) 
									&& (row.getCell(6) == null || row.getCell(6).toString().isEmpty()) && (row.getCell(7)==null || row.getCell(7).toString().isEmpty()) 
									&& (row.getCell(8)==null || row.getCell(8).toString().isEmpty()) && (row.getCell(9)==null || row.getCell(9).toString().isEmpty()) 
									&& (row.getCell(10) == null || row.getCell(10).toString().isEmpty()) &&  
									 (row.getCell(20)==null || row.getCell(20).toString().isEmpty()) 
									&& (row.getCell(21)==null || row.getCell(21).toString().isEmpty())) {
								
								remark = "Mandatory fields can not be empty";
							}
							
							if (remark != "") {
								
								remarkFlg =true;
								
								orderExcel.setOrderType(row.getCell(0).toString() !=null ? row.getCell(0).toString() :"");
								orderExcel.setOrderNo(row.getCell(1).toString() !=null ? row.getCell(1).toString() :"");
								orderExcel.setParty(row.getCell(2).toString() !=null ? row.getCell(2).toString() :"");
								orderExcel.setMtRefNo(row.getCell(3).toString().equalsIgnoreCase("-") ? "" : row.getCell(3).toString());
							
								//	orderExcel.setProdDate(row.getCell(4).toString() != null ? row.getCell(4).toString() :"");
								orderExcel.setProdDate(row.getCell(4).toString().equalsIgnoreCase("-") ? "" :row.getCell(4).toString());
								
//								String delvDate =row.getCell(5).toString();
//								Date deldate = df.parse(delvDate);
//								
							if(row.getCell(5).toString().equalsIgnoreCase("-")){
								orderExcel.setDeliveryDate(row.getCell(5).toString().equalsIgnoreCase("-") ? "" :row.getCell(5).toString());
							}else {
								String delvDate =row.getCell(5).toString();
								Date deldate = df.parse(delvDate);
								
								orderExcel.setDeliveryDate(row.getCell(5).toString() != null ? formatter.format(deldate) :"");
							}	 
								orderExcel.setSrNo(row.getCell(6).toString() !=null ? (int)(Double.parseDouble(row.getCell(6).toString())):0);
								orderExcel.setStyleNo(row.getCell(7).toString() !=null ? row.getCell(7).toString() :"");
								orderExcel.setPurity(row.getCell(8).toString() !=null ? row.getCell(8).toString() :"");
								orderExcel.setColor(row.getCell(9).toString() !=null ? row.getCell(9).toString() :"");
								orderExcel.setQty(row.getCell(10).toString() !=null ? Double.parseDouble(row.getCell(10).toString()):0.0);
								orderExcel.setNetWt(row.getCell(11).toString() !=null ?Math.round(Double.parseDouble(row.getCell(11).toString())*1000.0)/1000.0:0.0);
								orderExcel.setProdSize(row.getCell(12).toString().equalsIgnoreCase("-") ? "" :row.getCell(12).toString());
								
								
								orderExcel.setDtRefNo(row.getCell(13).toString() !=null ? row.getCell(13).toString() :"");		
								orderExcel.setStamp(row.getCell(14).toString().equalsIgnoreCase("-") ? "" :row.getCell(14).toString());
								orderExcel.setItemRemark(row.getCell(15).toString().equalsIgnoreCase("-") ?"" : row.getCell(15).toString());
								
						//		orderExcel.setOrdRefNo(row.getCell(16).toString() !=null ? row.getCell(16).toString() :"");
							
							//All Code change
								
								orderExcel.setOrdRefNo(row.getCell(16).toString().equalsIgnoreCase("-") ? "" :row.getCell(16).toString());
								orderExcel.setItem(row.getCell(17).toString().equalsIgnoreCase("-")  ? "" :row.getCell(17).toString());
								orderExcel.setShape(row.getCell(18).toString().equalsIgnoreCase("-") ? "" :row.getCell(18).toString());
								orderExcel.setQuality(row.getCell(19).toString().equalsIgnoreCase("-") ? "" :row.getCell(19).toString());
								orderExcel.setCarat(row.getCell(20).toString().equalsIgnoreCase("-") ? 0.0:Double.parseDouble(row.getCell(20).toString()));
								orderExcel.setRate(row.getCell(21).toString().equalsIgnoreCase("-")  ? 0.0 : Double.parseDouble(row.getCell(21).toString()));
								orderExcel.setAmount(row.getCell(22).toString().equalsIgnoreCase("-") ? 0.0 : Double.parseDouble(row.getCell(22).toString()));
								orderExcel.setStatusRemark(remark);
								orderExcelList.add(orderExcel);
							
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
							
							OrderType orderType = orderTypeService.findByName(row.getCell(0).toString());
							if(orderType == null){
								if(remark == ""){
									remark = "No such Order Type exists"; 
								}else {
									remark += ",No such Order Type exists";
								}
								
								
							}
							
							OrderMt orderMt = findByInvNoAndDeactive(row.getCell(1).toString(),false);
							if(orderMt != null){
								if(remark == ""){
									remark = "Duplicate Order No"; 
								}else {
									remark += ",Duplicate Order No";
								}
								
							}
							
							
							Party party = partyService.findByPartyCodeAndDeactive(row.getCell(2).toString(), false);
							if(party == null){
								if(remark == ""){
									remark = "No such client exists ";
								}else {
									remark += ",No such client exists ";
								}
								
							}
						
													
							Design design = designService.findByMainStyleNoAndDeactive(row.getCell(7).toString(),false);
							if(design == null){
								if(remark == ""){
									remark = "No such style exists ";
								}else {
									remark += ",No such style exists ";
								}
							}
							
							Purity purity =  purityService.findByName(row.getCell(8).toString());
							if(purity == null){
								if(remark == ""){
									remark = "No such purity exists ";
								}else {
									remark += ",No such purity exists ";
								}
								
								
							}
							
							Color color = colorService.findByName(row.getCell(9).toString());
							if(color == null){
								if(remark == ""){
									remark = "No such color exists ";
								}else {
									remark += ",No such color exists ";
								}
								
							}
							
							ProductSize productSize = productSizeService.findByName(row.getCell(12).toString());
							if(productSize == null){
								if(remark == ""){
									remark = "No such Size exists ";
								}else {
									remark += ",No such Size exists ";
								}
								
							}
							
//							Shape shape = shapeService.findByName(row.getCell(18).toString());
//							if(shape == null){
//								if(remark == ""){
//									remark = "No such shape exists";
//								}else {
//									remark += ",No such shape exists ";
//								}
//								
//							}
//							
//							Quality quality = qualityService.findByShapeAndName(shape,row.getCell(19).toString());
//							if(quality == null){
//								if(remark == ""){
//									remark = "No such quality exists";
//								}else {
//									remark += ",No such quality exists ";
//								}
//							
//								
//							}
							
							
							
							
							if((row.getCell(0) == null || row.getCell(0).toString().isEmpty() ) && (row.getCell(1)==null || row.getCell(1).toString().isEmpty()) 
									&& (row.getCell(2)==null || row.getCell(2).toString().isEmpty()) && (row.getCell(3)==null || row.getCell(3).toString().isEmpty()) 
									&& (row.getCell(6) == null || row.getCell(6).toString().isEmpty()) && (row.getCell(7)==null || row.getCell(7).toString().isEmpty()) 
									&& (row.getCell(8)==null || row.getCell(8).toString().isEmpty()) && (row.getCell(9)==null || row.getCell(9).toString().isEmpty()) 
									&& (row.getCell(10) == null || row.getCell(10).toString().isEmpty()) 
									 && (row.getCell(20)==null || row.getCell(20).toString().isEmpty()) 
									&& (row.getCell(21)==null || row.getCell(21).toString().isEmpty())) {
								
								remark = "Mandatory fields can not be empty";
							}
							
							if(remark != "") {
								
								remarkFlg =true;
								orderExcel.setOrderType(row.getCell(0).toString() !=null ? row.getCell(0).toString() :"");
								orderExcel.setOrderNo(row.getCell(1).toString() !=null ? row.getCell(1).toString() :"");
								orderExcel.setParty(row.getCell(2).toString() !=null ? row.getCell(2).toString() :"");
								orderExcel.setMtRefNo(row.getCell(3).toString().equalsIgnoreCase("-") ? "" : row.getCell(3).toString());
								orderExcel.setProdDate(row.getCell(4).toString().equalsIgnoreCase("-") ? "" :row.getCell(4).toString());
								
//								String delvDate =row.getCell(5).toString();
//								Date deldate = df.parse(delvDate);
//								
							if(row.getCell(5).toString().equalsIgnoreCase("-")){
								orderExcel.setDeliveryDate(row.getCell(5).toString().equalsIgnoreCase("-") ? "" :row.getCell(5).toString());
							}else {
								String delvDate =row.getCell(5).toString();
								Date deldate = df.parse(delvDate);
								
								orderExcel.setDeliveryDate(row.getCell(5).toString() != null ? formatter.format(deldate) :"");
							}	 
							       
								
								
							//	orderExcel.setDeliveryDate(row.getCell(5).toString().equalsIgnoreCase("-") ? "" :formatter.format(deldate));
						
								
								
								orderExcel.setSrNo(row.getCell(6).toString() !=null ? (int)(Double.parseDouble(row.getCell(6).toString())):0);
								orderExcel.setStyleNo(row.getCell(7).toString() !=null ? row.getCell(7).toString() :"");
								orderExcel.setPurity(row.getCell(8).toString() !=null ? row.getCell(8).toString() :"");
								orderExcel.setColor(row.getCell(9).toString() !=null ? row.getCell(9).toString() :"");
								orderExcel.setQty(row.getCell(10).toString() !=null ? Double.parseDouble(row.getCell(10).toString()):0.0);
								orderExcel.setNetWt(row.getCell(11).toString() !=null ?Math.round(Double.parseDouble(row.getCell(11).toString())*1000.0)/1000.0:0.0);
							
							
								orderExcel.setProdSize(row.getCell(12).toString().equalsIgnoreCase("-") ? "" :row.getCell(14).toString());
								orderExcel.setDtRefNo(row.getCell(13).toString() !=null ? row.getCell(13).toString() :"");
								
								orderExcel.setStamp(row.getCell(14).toString().equalsIgnoreCase("-") ? "" :row.getCell(14).toString());
								
							//	orderExcel.setItemRemark(row.getCell(15).toString() !=null ? row.getCell(15).toString() :"");
								
								orderExcel.setItemRemark(row.getCell(15).toString().equalsIgnoreCase("-") ?"" : row.getCell(15).toString());
								
						//		orderExcel.setOrdRefNo(row.getCell(16).toString() !=null ? row.getCell(16).toString() :"");
							
							//All Code change
								
								orderExcel.setOrdRefNo(row.getCell(16).toString().equalsIgnoreCase("-") ? "" :row.getCell(16).toString());
								orderExcel.setItem(row.getCell(17).toString().equalsIgnoreCase("-")  ? "" :row.getCell(17).toString());
								orderExcel.setShape(row.getCell(18).toString().equalsIgnoreCase("-") ? "" :row.getCell(18).toString());
								orderExcel.setQuality(row.getCell(19).toString().equalsIgnoreCase("-") ? "" :row.getCell(19).toString());
								orderExcel.setCarat(row.getCell(20).toString().equalsIgnoreCase("-") ? 0.0:Double.parseDouble(row.getCell(20).toString()));
								orderExcel.setRate(row.getCell(21).toString().equalsIgnoreCase("-")  ? 0.0 : Double.parseDouble(row.getCell(21).toString()));
								orderExcel.setAmount(row.getCell(22).toString().equalsIgnoreCase("-") ? 0.0 : Double.parseDouble(row.getCell(22).toString()));
														
								orderExcel.setStatusRemark(remark);
								orderExcelList.add(orderExcel);
							}
							
							
							
						}

						workbook.close();
						
				    } else {
				        throw new IllegalArgumentException("The specified file is not Excel file");
				    }
				    
				    
				    // Create Order
				    
				    if (remarkFlg != false) {
				    	 session.setAttribute("orderExcelSessionList", orderExcelList);  
				    	 retVal="yes";
				    	
				    }else {
				    	
				    	
				    	   if (tempExcelFile.endsWith("xlsx")) {
						    	int i=1;
					
						    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
						        XSSFSheet worksheet = workbook.getSheetAt(0);
						     
						        String orderFlg = "";
						        OrderMt orderMt2 = null;
						    	Integer srNo = 0;
						    	Integer dtId = 0;
						        
						        while (i <= worksheet.getLastRowNum()) {
									
									XSSFRow row = worksheet.getRow(i++);
									
									if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
										continue;
									}
									 
								
									//note : temporary list
									//remark is set in createdBy for temporary list
									//status is set in updatedBy for temporary list
									
								
									OrderType orderType = orderTypeService.findByName(row.getCell(0).toString());
																
									Party party = partyService.findByPartyCodeAndDeactive(row.getCell(2).toString(), false);
																						
									Design design = designService.findByMainStyleNoAndDeactive(row.getCell(7).toString(),false);
									
									Purity purity =  purityService.findByName(row.getCell(8).toString());
									
									
									Color color = colorService.findByName(row.getCell(9).toString());
									
									Shape shape = null;
									if(row.getCell(18).toString().equalsIgnoreCase("-")) {
										shape = null;
									}else {
									 shape = shapeService.findByName(row.getCell(18).toString());	
									}
									
									Quality quality = null;
									if(row.getCell(19).toString().equalsIgnoreCase("-")) {
										quality = null;
									}else {
										if(shape != null) {
											quality  = qualityService.findByShapeAndName(shape,row.getCell(19).toString());	
										}
											
									}
									
									
									ProductSize productSize =  null;
									if(row.getCell(12).toString().equalsIgnoreCase("-")) {
										productSize= null;
									}else {
										 productSize = productSizeService.findByName(row.getCell(12).toString());
										
									}
									
									ClientStamp clientStamp =  null;
									String clientStampNm = null;
									if(row.getCell(14).toString().equalsIgnoreCase("-")) {
										 clientStamp = clientStampService.findByPartyAndPurityAndDeactive(party, purity, false);
										 if(clientStamp != null) {
											  clientStampNm = clientStamp.getStampNm(); 
										 }
										
									}else {
										clientStampNm = row.getCell(14).toString();
									}
									
									
									
										
										if(orderFlg ==""){
											
											orderMt2  =  new OrderMt();
											orderMt2.setOrderType(orderType);
											orderMt2.setInvNo(row.getCell(1).toString());
											orderMt2.setInvDate(new Date());
											orderMt2.setParty(party);
											orderMt2.setRefNo(row.getCell(3).toString());
										//	orderMt2.setProductionDate(prodDate1);
										//	orderMt2.setDispatchDate(delvDate1);
											orderMt2.setCreatedDt(new Date());
											orderMt2.setCreatedBy(principal.getName());
											orderMt2.setPendApprovalFlg(true);
											save(orderMt2);
											
											
											
										}
										
										
										if((srNo != (int)(Double.parseDouble(row.getCell(6).toString()))) || orderFlg ==""){
											
										//	int srno = (int)(Double.parseDouble(row.getCell(6).toString()));
											
											
											OrderDt orderDt =  new OrderDt();
											orderDt.setOrderMt(orderMt2);
											orderDt.setPurity(purity);
											orderDt.setColor(color);
											orderDt.setDesign(design);
											orderDt.setPcs(Double.parseDouble(row.getCell(10).toString()));
											orderDt.setStampInst(clientStampNm);
											orderDt.setItem(row.getCell(17).toString().equalsIgnoreCase("-") ? "" :row.getCell(17).toString());
											orderDt.setOrdRef(row.getCell(16).toString().equalsIgnoreCase("-")  ? "" :row.getCell(16).toString());
											orderDt.setRemark(row.getCell(15).toString().equalsIgnoreCase("-")  ? "" :row.getCell(15).toString());
											orderDt.setRefNo(row.getCell(13).toString().equalsIgnoreCase("-")  ? "" :row.getCell(13).toString());
											orderDt.setDesignRemark(design.getRemarks());
											
											if(row.getCell(11).toString().equalsIgnoreCase("0") || row.getCell(11).toString().equalsIgnoreCase("0.0")) {
												orderDt.setNetWt(Math.round((design.getWaxWt() * purity.getWaxWtConv())*1000.0)/1000.0);
											}else {
												orderDt.setNetWt(Math.round(Double.parseDouble(row.getCell(11).toString())*1000.0)/1000.0);	
											}
											
											
											orderDt.setCreatedDate(new Date());
											orderDt.setCreatedBy(principal.getName());
											orderDt.setSrNo((int)(Double.parseDouble(row.getCell(6).toString())));
											orderDt.setProductSize(productSize);
											
											if(row.getCell(5).toString().equalsIgnoreCase("-")){
												orderDt.setDueDate(null);
											}else {
												String delvDate =row.getCell(5).toString();
												Date deldate = df.parse(delvDate);
												
												orderDt.setDueDate(deldate);
													
											}
											
											
											orderDtService.save(orderDt);
											
											srNo = orderDt.getSrNo();
											dtId = orderDt.getId();
											
											List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
											orderMetalService.setOrderMetalDt(designMetals, orderMt2, orderDt, principal);
											
											List<DesignStone> designStones = designStoneService.findByDesign(design); 
											orderStnDtService.setOrderStnDt(designStones, orderMt2, orderDt,principal);
											
											List<DesignComponent> designComponents = designComponentService.findByDesign(design);
											orderCompDtService.setOrderCompDt(designComponents, orderMt2, orderDt, principal);
											
											Double totalReqCarat =0.0;
											for(DesignStone designStone : designStones) {
												totalReqCarat += designStone.getCarat();
											}
											
											
											orderDt.setReqCarat(Math.round((totalReqCarat)*1000.0)/1000.0);
											orderDtService.save(orderDt);
										}
										
										
										OrderDt orderDt =orderDtService.findOne(dtId);
										
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
										 * if(row.getCell(19).toString().contains("S/C") &&
										 * Double.parseDouble(orderStnDt.getSize())<0.90){
										 * 
										 * //orderStnDt.setCarat(Double.parseDouble(row.getCell(20).toString()));
										 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * orderStnDt.setQuality(quality);
										 * 
										 * totqltyCarat +=orderStnDt.getCarat();
										 * 
										 * }else if(row.getCell(19).toString().contains("D/C") &&
										 * Double.parseDouble(orderStnDt.getSize())>=0.90) {
										 * 
										 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat();
										 * 
										 * } else if(!row.getCell(19).toString().contains("D/C") &&
										 * !row.getCell(19).toString().contains("S/C")){
										 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat(); }
										 * 
										 * }else {
										 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
										 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat(); } }
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
										 * List<OrderStnDt> orderStnDts2 = query.getResultList();
										 * 
										 * Double perPcsDiawt = Double.parseDouble(row.getCell(20).toString()) /
										 * Double.parseDouble(row.getCell(10).toString());
										 * 
										 * Double vCaratDiff =Math.round((perPcsDiawt-totqltyCarat)*1000.0)/1000.0;
										 * 
										 * 
										 * for(OrderStnDt orderStnDt : orderStnDts2) {
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
										orderDtService.save(orderDt);
										
										orderDtService.updateKtDesc(orderDt.getId());
										orderDtService.updateQltyDesc(orderDt.getId());		
								
									orderFlg = "true";
									
								}

								
								
								workbook.close();
								
						    } else if (tempExcelFile.endsWith("xls")) {
						    	int i = 1;
								HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
								HSSFSheet worksheet = workbook.getSheetAt(0);
								
												
								   String orderFlg = "";
							        OrderMt orderMt2 = null;
							    	Integer srNo = 0;
							    	Integer dtId = 0;
							    	
							        
									while (i <= worksheet.getLastRowNum()) {
										HSSFRow row = worksheet.getRow(i++);
							
										if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
											continue;
										}
									
									
										OrderType orderType = orderTypeService.findByName(row.getCell(0).toString());
										
										Party party = partyService.findByPartyCodeAndDeactive(row.getCell(2).toString(), false);
																							
										Design design = designService.findByMainStyleNoAndDeactive(row.getCell(7).toString(),false);
										
										Purity purity =  purityService.findByName(row.getCell(8).toString());
										
										Color color = colorService.findByName(row.getCell(9).toString());
										
										Shape shape = null;
										if(row.getCell(18).toString().equalsIgnoreCase("-")) {
											shape = null;
										}else {
										 shape = shapeService.findByName(row.getCell(18).toString());	
										}
										
										Quality quality = null;
										if(row.getCell(19).toString().equalsIgnoreCase("-")) {
											quality = null;
										}else {
											if(shape != null) {
												quality  = qualityService.findByShapeAndName(shape,row.getCell(19).toString());	
											}
												
										}
										
										ProductSize productSize =  null;
										if(row.getCell(12).toString().equalsIgnoreCase("-")) {
											productSize= null;
										}else {
											 productSize = productSizeService.findByName(row.getCell(12).toString());
											
										}
										
										ClientStamp clientStamp =  null;
										String clientStampNm = null;
										if(row.getCell(14).toString().equalsIgnoreCase("-")) {
											 clientStamp = clientStampService.findByPartyAndPurityAndDeactive(party, purity, false);
											 if(clientStamp != null) {
												  clientStampNm = clientStamp.getStampNm(); 
											 }
											
										}else {

												  clientStampNm = row.getCell(14).toString(); 
										}
										
									
											if(orderFlg ==""){
												
												orderMt2  =  new OrderMt();
												orderMt2.setOrderType(orderType);
												orderMt2.setInvNo(row.getCell(1).toString());
												orderMt2.setInvDate(new Date());
												orderMt2.setParty(party);
												orderMt2.setRefNo(row.getCell(3).toString());
											//	orderMt2.setProductionDate(prodDate1);
											//	orderMt2.setDispatchDate(delvDate1);
												orderMt2.setCreatedDt(new Date());
												orderMt2.setCreatedBy(principal.getName());
												
												save(orderMt2);
												
											}
											
											
											if((srNo != (int)(Double.parseDouble(row.getCell(6).toString()))) || orderFlg ==""){
												
											//	int srno = (int)(Double.parseDouble(row.getCell(6).toString()));
												
												
												OrderDt orderDt =  new OrderDt();
												orderDt.setOrderMt(orderMt2);
												orderDt.setPurity(purity);
												orderDt.setColor(color);
												orderDt.setDesign(design);
												orderDt.setPcs(Double.parseDouble(row.getCell(10).toString()));
												orderDt.setStampInst(clientStampNm);
												orderDt.setItem(row.getCell(17).toString().equalsIgnoreCase("-") ? "" :row.getCell(17).toString());
												orderDt.setOrdRef(row.getCell(16).toString().equalsIgnoreCase("-")  ? "" :row.getCell(16).toString());
												orderDt.setRemark(row.getCell(15).toString().equalsIgnoreCase("-")  ? "" :row.getCell(15).toString());
												orderDt.setRefNo(row.getCell(13).toString().equalsIgnoreCase("-")  ? "" :row.getCell(13).toString());
												orderDt.setDesignRemark(design.getRemarks());
												
												
												if(row.getCell(11).toString().equalsIgnoreCase("0") || row.getCell(11).toString().equalsIgnoreCase("0.0")) {
													orderDt.setNetWt(Math.round((design.getWaxWt() * purity.getWaxWtConv())*1000.0)/1000.0);
												}else {
													orderDt.setNetWt(Math.round(Double.parseDouble(row.getCell(11).toString())*1000.0)/1000.0);	
												}
												
												orderDt.setCreatedDate(new Date());
												orderDt.setProductSize(productSize);
												orderDt.setCreatedBy(principal.getName());
												orderDt.setSrNo((int)(Double.parseDouble(row.getCell(6).toString())));
												
												
												if(row.getCell(5).toString().equalsIgnoreCase("-")){
													orderDt.setDueDate(null);
												}else {
													String delvDate =row.getCell(5).toString();
													Date deldate = df.parse(delvDate);
													
													orderDt.setDueDate(deldate);
														
												}
													
												
												orderDtService.save(orderDt);
												
												srNo = orderDt.getSrNo();
												dtId = orderDt.getId();
												
												List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
												orderMetalService.setOrderMetalDt(designMetals, orderMt2, orderDt, principal);
												
												List<DesignStone> designStones = designStoneService.findByDesign(design); 
												orderStnDtService.setOrderStnDt(designStones, orderMt2, orderDt,principal);
												
												
												List<DesignComponent> designComponents = designComponentService.findByDesign(design);
												orderCompDtService.setOrderCompDt(designComponents, orderMt2, orderDt, principal);
												
												Double totalReqCarat =0.0;
												for(DesignStone designStone : designStones) {
													totalReqCarat += designStone.getCarat();
												}
												
												
												orderDt.setReqCarat(totalReqCarat);
												orderDtService.save(orderDt);
												
											}
											
											
											OrderDt orderDt =orderDtService.findOne(dtId);
											
											/*
											 * List<OrderStnDt> orderStnDts =
											 * orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
											 * 
											 * 
											 * Double totqltyCarat = 0.0;
											 * 
											 * if(shape !=null) { for(OrderStnDt orderStnDt:orderStnDts){
											 * if(orderStnDt.getShape().equals(shape)){
											 * 
											 * if(orderStnDt.getShape().getName().equalsIgnoreCase("ROUND")) {
											 * 
											 * if(row.getCell(19).toString().contains("S/C") &&
											 * Double.parseDouble(orderStnDt.getSize())<0.90){
											 * 
											 * //orderStnDt.setCarat(Double.parseDouble(row.getCell(20).toString()));
											 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
											 * orderStnDt.setQuality(quality);
											 * 
											 * totqltyCarat +=orderStnDt.getCarat();
											 * 
											 * }else if(row.getCell(19).toString().contains("D/C") &&
											 * Double.parseDouble(orderStnDt.getSize())>=0.90) {
											 * 
											 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
											 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat();
											 * 
											 * } else if(!row.getCell(19).toString().contains("D/C") &&
											 * !row.getCell(19).toString().contains("S/C")){
											 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
											 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat(); }
											 * 
											 * 
											 * }else {
											 * 
											 * 
											 * orderStnDt.setStnRate(Double.parseDouble(row.getCell(21).toString()));
											 * orderStnDt.setQuality(quality); totqltyCarat +=orderStnDt.getCarat();
											 * 
											 * 
											 * }
											 * 
											 * 
											 * }
											 * 
											 * 
											 * }
											 * 
											 * }
											 * 
											 * if(quality != null) {
											 * 
											 * @SuppressWarnings("unchecked") TypedQuery<OrderStnDt> query =
											 * (TypedQuery<OrderStnDt>) entityManager
											 * .createNativeQuery("select * from sordstndt " + " where dtid =" + dtId +
											 * " and qualityid = "+quality.getId()+" ORDER BY carat DESC LIMIT 1 " ,
											 * OrderStnDt.class);
											 * 
											 * List<OrderStnDt> orderStnDts2 = query.getResultList();
											 * 
											 * Double perPcsDiawt = Double.parseDouble(row.getCell(20).toString()) /
											 * Double.parseDouble(row.getCell(10).toString());
											 * 
											 * Double vCaratDiff =Math.round((perPcsDiawt-totqltyCarat)*1000.0)/1000.0;
											 * 
											 * 
											 * for(OrderStnDt orderStnDt : orderStnDts2) {
											 * orderStnDt.setCarat(Math.round((orderStnDt.getCarat()+vCaratDiff)*1000.0)
											 * /1000.0); orderStnDtService.save(orderStnDt);
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
											orderDtService.save(orderDt);
											orderDtService.updateKtDesc(orderDt.getId());
											orderDtService.updateQltyDesc(orderDt.getId());	
											
									
										orderFlg = "true";
										
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

	@Override
	public Page<OrderMt> pendingForApprovalList(Integer limit, Integer offset, String sort, String order, String name , Boolean approvalFlg) {
		// TODO Auto-generated method stub
		QOrderMt qOrderMt = QOrderMt.orderMt;
		BooleanExpression expression = qOrderMt.deactive.eq(false).and(qOrderMt.pendApprovalFlg.eq(approvalFlg));

		if(name !=null && name !=""){
			expression = qOrderMt.deactive.eq(false).and(qOrderMt.pendApprovalFlg.eq(approvalFlg)).and(qOrderMt.invNo.like("%"+name+"%").or(qOrderMt.refNo.like(name+"%")).or(qOrderMt.party.name.like(name+"%")));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<OrderMt> orderMtList = (Page<OrderMt>) orderMtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));

		
		
		return orderMtList;
	}

	@Override
	public String orderApproval(Integer mtId, Principal principal) {

		String retVal="-2";
		Boolean checkQualityFlg = false;
		try {
			
			OrderMt orderMt = findOne(mtId);
			
			List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderMtAndDeactive(orderMt, false);
			
			if(orderStnDts.size() > 0) {
				
				for (OrderStnDt orderStnDt : orderStnDts) {
					
					if(orderStnDt.getQuality() == null) {
						checkQualityFlg = false;
						break;
						
					}else {
						
						checkQualityFlg = true;
					}
				}	
			}else {
				
				checkQualityFlg = true;
				
			}
			
			if(checkQualityFlg) {
				
				if(orderMt.getApproveDate() == null) {
					orderMt.setApproveDate(new Date());
				}
				
				orderMt.setPendApprovalFlg(false);
				orderMt.setModiBy(principal.getName());
				orderMt.setModiDt(new Date());
				save(orderMt);
				retVal="1";
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	
	

	@Override
	public String orderUnApproval(Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		String retVal="-2";
		Boolean checkBagFlg = true;
		try {
			
			OrderMt orderMt = findOne(mtId);
			
			//Code Comment for specific time
			
			/*
			 * List<BagMt> bagMts = bagMtService.findByOrderMt(orderMt);
			 * 
			 * if(bagMts.size() > 0) { checkBagFlg = false; }else { checkBagFlg = true; }
			 */
			
			if(checkBagFlg) {
				orderMt.setPendApprovalFlg(true);
				orderMt.setModiBy(principal.getName());
				orderMt.setModiDt(new Date());
				save(orderMt);
				retVal="1";
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	

	@Override
	public OrderMt findByInvNoAndDeactiveAndPendApprovalFlg(String invNo, Boolean deactive, Boolean pendApprovalFlg) {
		// TODO Auto-generated method stub
		return orderMtRepository.findByInvNoAndDeactiveAndPendApprovalFlg(invNo, deactive, pendApprovalFlg);
	}

	@Override
	public String autoOrderClose() {
		String retVal = "-1";

		try {

			List<OrderMt> orderMts = findByOrderCloseAndDeactive(false, false);

			Double orderDtQty = 0.0;
			Double tranMtQty = 0.0;

			for (OrderMt orderMt : orderMts) {

				orderDtQty = orderDtService.getTotalOrderQtys(orderMt.getId());
				tranMtQty = tranMtService.getTotalTranQtys(orderMt.getId());

				if (orderDtQty.equals(tranMtQty)) {
					orderMt.setOrderClose(true);
					orderMt.setOrderCloseDt(new java.util.Date());
					save(orderMt);
				}

			}

		} catch (Exception e) {
			retVal = "-2";
			e.printStackTrace();
		}

		return retVal;
	}

	@Override
	public String applyBarcode(Integer mtId, Principal principal, Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		String retVal = "1";
		OrderMt orderMt = findOne(mtId);

		List<OrderDt> orderDts = orderDtService.findByOrderMtAndDeactive(orderMt, false);

		try {
			for (OrderDt orderDt : orderDts) {

				if (orderDt.getrLock().equals(false)) {

					List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderDt, false);
					if (orderMetals.size() > 0) {
						OrderMetal orderMetal = orderMetals.get(0);

						ClientReference clientReference = clientRefService.findByPartyAndDesignAndPurityAndDeactive(
								orderMt.getParty(), orderDt.getDesign(), orderMetal.getPurity(), false);

						if (clientReference != null) {

							orderDt.setGrossWt(clientReference.getFinishWt());
							orderDt.setReqCarat(clientReference.getCaratWt());
							
							
							String stampName = clientStampService.getStampName(clientReference.getCaratWt(), orderMt.getParty().getId());
							
							if(stampName != null && stampName !=""){
								orderDt.setStampInst(stampName); 	
							}
							

							List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(orderDt, false);
							Integer totStn = 0;
							Double totCarat = 0.0;
							for (OrderStnDt orderStnDt : orderStnDts) {

								totStn += orderStnDt.getStone();
								totCarat += Math.round(orderStnDt.getCarat() * 1000.0) / 1000.0;

							}

							Double vDiaWt = Math.round((totCarat / 5) * 1000.0) / 1000.0;

							List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDt,
									false);
							Double totCompWt = 0.0;
							for (OrderCompDt orderCompDt : orderCompDts) {

								totCompWt += Math.round(orderCompDt.getMetalWt() * 1000.0) / 1000.0;

							}

							Double vNetWt = 0.0;
							if (netWtWithCompFlg.equals(true)) {

								vNetWt = Math.round((orderDt.getGrossWt() - vDiaWt) * 1000.0) / 1000.0;
							} else {
								vNetWt = Math.round((orderDt.getGrossWt() - vDiaWt - totCompWt) * 1000.0) / 1000.0;
							}

							orderDt.setNetWt(vNetWt);

							if (orderMetal.getColor().getName().equalsIgnoreCase("Y")) {
								orderDt.setBarcode(clientReference.getY());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("W")) {
								orderDt.setBarcode(clientReference.getW());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("P")) {
								orderDt.setBarcode(clientReference.getP());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("PW")) {
								orderDt.setBarcode(clientReference.getPw());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("PY")) {
								orderDt.setBarcode(clientReference.getPy());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("TT")) {
								orderDt.setBarcode(clientReference.getTt());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("WP")) {
								orderDt.setBarcode(clientReference.getWp());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("WY")) {
								orderDt.setBarcode(clientReference.getWy());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("YP")) {
								orderDt.setBarcode(clientReference.getYp());
							} else if (orderMetal.getColor().getName().equalsIgnoreCase("YW")) {
								orderDt.setBarcode(clientReference.getYw());
							}

							orderDtService.save(orderDt);

							orderMetal.setMetalWeight(vNetWt);

							orderMetalService.save(orderMetal);

						}

					}

				}

				orderDtService.applyRate(orderDt, principal,netWtWithCompFlg);

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e);
			retVal = "-1";
		}

		return retVal;
	}

	@Override
	public String getSalesPersonFromParty(Integer partyId, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal = "";
		
		try {
			
			Party party = partyService.findOne(partyId);
			retVal = party.getSalesMan() != null ? party.getSalesMan() : ""; 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}
	


}

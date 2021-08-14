package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;


import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QGrecMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockCompDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMetalDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockStnDtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IGrecMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class GrecMtService implements IGrecMtService {
	
	@Autowired
	private IGrecMtRepository grecMtRepository;
	
	@Autowired
	private IGrecDtService grecDtService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	

	@Autowired
	private IStockMtRepository stockMtRepository;
	
	@Autowired
	private IStockMetalDtRepository stockMetalDtRepository;
	
	@Autowired
	private IStockStnDtRepository stockStnDtRepository;
	
	@Autowired
	private IStockCompDtRepository stockCompDtRepository;
	
	@Autowired
	private IGrecStnDtService grecStnDtService;
	
	@Autowired
	private IGrecMetalDtService grecMetalDtService;
	
	@Autowired
	private IGrecCompDtService grecCompDtService;
	

	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IOrderTypeService orderTypeService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IOrderMetalService orderMetalService;
	
	@Autowired
	private IOrderStnDtService orderStnDtService;
	
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IOrderCompDtService orderCompDtService;

	@Autowired
	private IDepartmentService departmentService;
	
	
	@Override
	public void save(GrecMt grecMt) {
		grecMtRepository.save(grecMt);
		
	}

	@Override
	public void delete(int id) {
		grecMtRepository.delete(id);
	}


	@Override
	public GrecMt findOne(int id) {
		
		return grecMtRepository.findOne(id);
	}

	@Override
	public GrecMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
	return grecMtRepository.findByInvNoAndDeactive(invNo, deactive);	
	}

	@Override
	public GrecMt findByUniqueId(Long uniqueId) {
		
		return grecMtRepository.findByUniqueId(uniqueId);
	}


	@Override
	public Page<GrecMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,Boolean onlyActive) {
	
		QGrecMt qGrecMt = QGrecMt.grecMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression =qGrecMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =qGrecMt.invNo.like("%" + search + "%");
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("invNo")) {
			sort = "invNo";
		}
	
		Page<GrecMt> grecMtList =(Page<GrecMt>)  grecMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return grecMtList;
	}

	@Override
	public String grecMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		
		Page<GrecMt> grecMts = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("grecMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		grecMts = searchAll(limit, offset, sort, order, search, true);

		
		sb.append("{\"total\":").append(grecMts.getTotalElements()).append(",\"rows\": [");

		for (GrecMt grecMt : grecMts) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(grecMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(grecMt.getId())
			.append("\",\"invNo\":\"")
			.append(grecMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(grecMt.getInvDateStr())
			.append("\",\"party\":\"")
			.append(grecMt.getParty().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/marketing/transactions/grecMt/edit/")
								.append(grecMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteGrecMt(event,");					
								sb.append(grecMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(grecMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/marketing/transactions/grecMt/edit/")
					.append(grecMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteGrecMt(event,")	
						.append(grecMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(grecMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		
		return str;
	}

	@Override
	public String saveGrecMt(GrecMt grecMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,
			BindingResult result, Integer pPartyIds,Integer pOrderTypeIds,String vTranDate,Integer pLocationIds) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/marketing/transactions/grecMt/add.html";
	
		if (result.hasErrors()) {
			return "grecMt/add";
		}
		
		synchronized (this) {
			

			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				grecMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno =getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				grecMt.setSrNo(++maxSrno);
				int si = maxSrno.toString().length();
				
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				Integer presentYear = Integer.parseInt(vYear);
				Integer nextYear = presentYear + 1;
				
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
				
				
				grecMt.setInvNo("GRN/" + (bagSr) + "/"  + presentYear+"-"+nextYear);
				grecMt.setCreatedBy(principal.getName());
				grecMt.setCreatedDt(new java.util.Date());
				

			} else {
				grecMt.setModiBy(principal.getName());
				grecMt.setModiDt(new java.util.Date());
				
				if(pPartyIds != null) {

					Party party =  partyService.findOne(pPartyIds);
					OrderType orderType = orderTypeService.findOne(pOrderTypeIds);
					Department department = departmentService.findOne(pLocationIds);
					
					grecMt.setOrderType(orderType);
					grecMt.setParty(party);
					grecMt.setLocation(department);
				}

				
				action = "updated";
				retVal = "redirect:/marketing/transactions/grecMt.html";
			}
			
			save(grecMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/marketing/transactions/grecMt/edit/"+grecMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
			
		}
		
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QGrecMt qGrecMt = QGrecMt.grecMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qGrecMt)
			.where(qGrecMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qGrecMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		return retVal;
	}

	@Override
	public String deleteGrecMt(Integer mtId) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
		
		GrecMt grecMt = findOne(mtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = grecMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){

			List<GrecDt> grecDts = grecDtService.findByGrecMt(grecMt);
			
			for (GrecDt grecDt : grecDts) {
				
				StockMt stockMt2 = stockMtService.findByRefTranIdAndTranTypeAndDeactive(grecDt.getId(), "GREC", false);
				if(!stockMt2.getCurrStk()) {
					
					return "Can not Delete, Qty Adjusted";
					
				}else {
					List<GrecStnDt> grecStnDts = grecStnDtService.findByGrecDt(grecDt);
					for(GrecStnDt grecStnDt :grecStnDts) {
						grecStnDtService.delete(grecStnDt.getId());
					}
					
					 List<GrecMetalDt> grecMetalDts=grecMetalDtService.findByGrecDt(grecDt);
					for(GrecMetalDt grecMetalDt :grecMetalDts) {
						grecMetalDtService.delete(grecMetalDt.getId());
					}
					
					List<GrecCompDt> grecCompDts =  grecCompDtService.findByGrecDt(grecDt);
					for(GrecCompDt grecCompDt :grecCompDts) {
						grecCompDtService.delete(grecCompDt.getId());
					}
					
					
					List<StockCompDt> stockCompDts = stockCompDtService.findByStockMtAndDeactive(stockMt2, false);
					for (StockCompDt stockCompDt : stockCompDts) {
						stockCompDtRepository.delete(stockCompDt.getStkCompId());
					}
					
					List<StockStnDt> stockStnDts = stockStnDtService.findByStockMtAndDeactive(stockMt2, false);
					for (StockStnDt stockStnDt : stockStnDts) {
						stockStnDtRepository.delete(stockStnDt.getStkStnId());
					}
					
					List<StockMetalDt> stockMetalDts = stockMetalDtService.findByStockMtAndDeactive(stockMt2, false);
					for (StockMetalDt stockMetalDt : stockMetalDts) {
						stockMetalDtRepository.delete(stockMetalDt.getStkMetalId());
					}
					
					stockMtRepository.delete(stockMt2.getMtId()); 
					
					StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("GREC", grecDt.getId(), true);
					if(stockTran !=null) {
						stockTranService.delete(stockTran.getId());
					}
					
			//		TranMt tranMt = tranMtService.findByRefMtIdAndCurrStk(grecDt.getId(), true);
					
			//		tranMtService.delete(tranMt.getId());
					
					
					BagMt bagMt = bagMtService.findOne(stockMt2.getBagId());
				
						bagMt.setBagCloseFlg(false); 
					  
					  bagMt.setModiDate(new Date());
					  bagMtService.save(bagMt);
					
				
				}
					
					grecDtService.delete(grecDt.getId());
				
			}
			
			delete(mtId);
			retVal = "1";
			
		}else {
			
			return "Can Not Delete BackDate Entry";
		
		}
		
		return retVal;
	}

	@Override
	public String orderPickListing(Integer orderTypeId, Integer partyId) {
		// TODO Auto-generated method stub
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_orderPickupList(?,?) }");
			query.setParameter(1, orderTypeId);
			query.setParameter(2, partyId);
			objects = query.getResultList();
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
				
				sb.append("{\"party\":\"")
			     .append(list[11] != null ? list[11] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"qty\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"image\":\"")
				 .append(list[13] != null ? list[13] : "")
				 .append("\",\"barcode\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"mtId\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"dtId\":\"")
				 .append(list[3] != null ? list[3] : "")
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	}

	@Override
	public String orderPickupTransferToStock(Principal principal, String bagIds, Integer grcmtId) {
		
	GrecMt grecMt = findOne(grcmtId);	
		String[] bagId = bagIds.split(",");
		String vMetalBag="";
		String vStnBag="";
		
		
		//For Rate Validation
		for (int i = 0; i < bagId.length; i++) {
			
			BagMt bagMt = bagMtService.findOne(Integer.parseInt(bagId[i].toString()));
			
			List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(bagMt.getOrderDt(), false);
			for (OrderMetal orderMetal : orderMetals) {
				
				if(orderMetal.getMetalRate() <= 0 || orderMetal.getMetalRate() == null) {
					if(vMetalBag !=""){
						vMetalBag +="<br />"+bagMt.getName();
					}else{
						vMetalBag = bagMt.getName();
					}
				}
			}
			
			Boolean stnFlg= false;
			
			List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(bagMt.getOrderDt(), false);
			
			
			for (OrderStnDt orderStnDt : orderStnDts) {
				if(!stnFlg) {
					stnFlg = true;
				if(orderStnDt.getStnRate() <= 0 || orderStnDt.getStnRate() == null) {
					if(vStnBag !=""){
						vStnBag +="<br />"+bagMt.getName();
					}else{
						vStnBag = bagMt.getName();
					}
				}
			}
			}
		}
		
		if(vMetalBag != "" && vStnBag == "") {
			return "Metal Rate not in : "+vMetalBag;
		}	
		
		if(vMetalBag == "" && vStnBag != "") {
			return "Stone Rate not in : "+vStnBag;
		}
		
		if(vMetalBag != "" && vStnBag != "") {
			return "Metal Rate not in : "+vMetalBag +"<br />" + "Stone Rate not in :  "+vStnBag;
		}
		
		
		//Transfer Code
		for (int i = 0; i < bagId.length; i++) {
			
			BagMt bagMt = bagMtService.findOne(Integer.parseInt(bagId[i].toString()));
			
			OrderDt orderDt = orderDtService.findOne(bagMt.getOrderDt().getId());
			
			GrecDt grecDt = new GrecDt();
			grecDt.setGrecMt(grecMt);
			grecDt.setBarcode(bagMt.getBarcode());
			grecDt.setDesign(orderDt.getDesign());
			grecDt.setGrossWt(orderDt.getGrossWt());
			grecDt.setNetWt(orderDt.getNetWt());
			grecDt.setPerGmWt(orderDt.getPerGmWt());
			grecDt.setPcs(orderDt.getPcs());
			grecDt.setLossPercDt(orderDt.getLossPercDt());
			grecDt.setHdlgValue(orderDt.getHdlgValue());
			grecDt.setLossValue(orderDt.getLossValue());
			grecDt.setFob(orderDt.getFob());
			grecDt.setFinalPrice(orderDt.getFinalPrice());
			grecDt.setrLock(orderDt.getrLock());
			grecDt.setPurity(orderDt.getPurity());
			grecDt.setColor(orderDt.getColor());
			grecDt.setRefNo(orderDt.getRefNo());
			grecDt.setStampInst(orderDt.getStampInst());
			grecDt.setProductSize(orderDt.getProductSize());
			grecDt.setMetalRate(orderDt.getMetalRate());
			grecDt.setMetalValue(Math.round(orderDt.getMetalValue()*100.0)/100.0);
			grecDt.setCompValue((Math.round(orderDt.getCompValue()*100.0)/100.0));
			grecDt.setLabValue(Math.round(orderDt.getLabValue()*100.0)/100.0);
			grecDt.setStoneValue(Math.round(orderDt.getStoneValue()*100.0)/100.0);
			grecDt.setCreatedBy(principal.getName());
			grecDt.setCreatedDate(new Date());
			
			grecDtService.save(grecDt);
			
			
			StockMt stockMt = new StockMt();
			stockMt.setBarcode(bagMt.getBarcode());
			stockMt.setCreatedBy(principal.getName());
			stockMt.setCreatedDate(new Date());
			stockMt.setCurrStk(true);
			stockMt.setDesign(bagMt.getOrderDt().getDesign());
			stockMt.setGrossWt(bagMt.getOrderDt().getGrossWt());
			stockMt.setLocation(grecMt.getLocation());
			stockMt.setQty(bagMt.getQty());
			stockMt.setRefTranId(grecDt.getId());
			stockMt.setTranDate(new Date());
			stockMt.setTranType("GREC");
			stockMt.setBagId(bagMt.getId());
			stockMt.setNetWt(Math.round((orderDt.getNetWt())*1000.0)/1000.0);
			stockMt.setLabourValue(Math.round((orderDt.getLabValue())*100.0)/100.0);
			stockMt.setMetalValue(Math.round(orderDt.getMetalValue()*100.0)/100.0);
			stockMt.setStoneValue(Math.round(orderDt.getStoneValue()*100.0)/100.0);
			stockMt.setFactoryCost(Math.round((orderDt.getFob())*100.0)/100.0);
			
			//		stockMt.setFromLocation(tranMt.getDepartment());
			
			stockMtService.save(stockMt);
			
			List<OrderStnDt> orderStnDts = orderStnDtService.findByOrderDtAndDeactive(bagMt.getOrderDt(), false);
			Double totCarat=0.0;
			Double totDiaVal=0.0;
			for(OrderStnDt orderStnDt :orderStnDts) {
				
				//Stock Stone Save Code
				StockStnDt stockStnDt =new StockStnDt();
				stockStnDt.setCarat(orderStnDt.getCarat());
				stockStnDt.setCreatedBy(principal.getName());
				stockStnDt.setCreatedDate(new Date());
				stockStnDt.setQuality(orderStnDt.getQuality());
				stockStnDt.setSetting(orderStnDt.getSetting());
				stockStnDt.setSettingType(orderStnDt.getSettingType());
				stockStnDt.setShape(orderStnDt.getShape());
				stockStnDt.setSize(orderStnDt.getSize());
				stockStnDt.setSizeGroup(orderStnDt.getSizeGroup());
				stockStnDt.setStockMt(stockMt);
				stockStnDt.setStone(orderStnDt.getStone());
				stockStnDt.setStoneType(orderStnDt.getStoneType());
				stockStnDt.setSubShape(orderStnDt.getSubShape());
				stockStnDt.setPartNm(orderStnDt.getPartNm());
				stockStnDt.setSieve(orderStnDt.getSieve());
				stockStnDt.setCenterStone(orderStnDt.getCenterStone());
				
				stockStnDt.setRate(orderStnDt.getStnRate());
				stockStnDt.setAvgRate(orderStnDt.getStnRate());
				stockStnDt.setTransferRate(orderStnDt.getStnRate());
				stockStnDt.setFactoryRate(orderStnDt.getStnRate());
				stockStnDt.setDiamValue(Math.round((orderStnDt.getCarat()*orderStnDt.getStnRate())*100.0)/100.0);
				
				
				stockStnDtService.save(stockStnDt);
				
				//GrcStndt Save Code
				GrecStnDt grecStnDt = new GrecStnDt();
				grecStnDt.setGrecMt(grecMt);
				grecStnDt.setGrecDt(grecDt);
				grecStnDt.setCarat(orderStnDt.getCarat());
				grecStnDt.setShape(orderStnDt.getShape());
				grecStnDt.setStoneType(orderStnDt.getStoneType());
				grecStnDt.setSubShape(orderStnDt.getSubShape());
				grecStnDt.setQuality(orderStnDt.getQuality());
				grecStnDt.setSize(orderStnDt.getSize());
				grecStnDt.setSizeGroup(orderStnDt.getSizeGroup());
				grecStnDt.setSieve(orderStnDt.getSieve());
				grecStnDt.setStone(orderStnDt.getStone());
				grecStnDt.setSetting(orderStnDt.getSetting());
				grecStnDt.setSettingType(orderStnDt.getSettingType());
				grecStnDt.setCreatedBy(principal.getName());
				grecStnDt.setCreatedDate(new java.util.Date());
				grecStnDt.setPartNm(orderStnDt.getPartNm());
				grecStnDt.setDiaCateg(orderStnDt.getDiaCateg());
				grecStnDt.setStnRate(orderStnDt.getSetRate());
				grecStnDt.setSetRate(orderStnDt.getSetRate());
				grecStnDt.setHandlingRate(orderStnDt.getHandlingRate());
				grecStnDt.setSetValue(orderStnDt.getSetValue());
				grecStnDt.setHandlingValue(orderStnDt.getHandlingValue());
				grecStnDt.setStoneValue(orderStnDt.getStoneValue());
				grecStnDt.setCreatedBy(principal.getName());
				grecStnDt.setCreatedDate(new Date());
				
				grecStnDtService.save(grecStnDt);
				
				totCarat  +=stockStnDt.getCarat();
				totDiaVal +=stockStnDt.getDiamValue();
				
			}
			
			List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(bagMt.getOrderDt(), false);
			Double totMetalVal=0.0;
			for(OrderMetal orderMetal :orderMetals) {
				
				//StockMetalDt Save Code
				StockMetalDt stockMetalDt = new StockMetalDt();
				stockMetalDt.setColor(orderMetal.getColor());
				stockMetalDt.setCreatedBy(principal.getName());
				stockMetalDt.setCreatedDate(new Date());
				stockMetalDt.setPurity(orderMetal.getPurity());
				stockMetalDt.setPurityConv(orderMetal.getPurity().getPurityConv());
				stockMetalDt.setStockMt(stockMt);
				stockMetalDt.setMainMetal(orderMetal.getMainMetal());
				stockMetalDt.setMetalPcs(orderMetal.getMetalPcs());
				stockMetalDt.setPartNm(orderMetal.getPartNm());
				stockMetalDt.setMetalWt(orderMetal.getMetalWeight());
				
				stockMetalDt.setMetalRate(Math.round((orderMetal.getMetalRate())*100.0)/100.0);
				stockMetalDt.setMetalValue(Math.round((orderMetal.getMetalWeight()*orderMetal.getMetalRate())*100.0)/100.0);
				
				totMetalVal +=stockMetalDt.getMetalValue();
				stockMetalDtService.save(stockMetalDt);

				
				//GrecMetalDt Save Code
				GrecMetalDt grecMetalDt = new GrecMetalDt();
				grecMetalDt.setColor(orderMetal.getColor());
				grecMetalDt.setCreatedBy(principal.getName());
				grecMetalDt.setCreateDate(new Date());
				grecMetalDt.setPurity(orderMetal.getPurity());
			//	grecMetalDt.setPurityConv(orderMetal.getPurity().());
				grecMetalDt.setMainMetal(orderMetal.getMainMetal());
				grecMetalDt.setMetalPcs(orderMetal.getMetalPcs());
				grecMetalDt.setPartNm(orderMetal.getPartNm());
				grecMetalDt.setMetalWeight(orderMetal.getMetalWeight());
				grecMetalDt.setMetalRate(Math.round((orderMetal.getMetalRate())*100.0)/100.0);
				grecMetalDt.setMetalValue(Math.round((orderMetal.getMetalWeight()*orderMetal.getMetalRate())*100.0)/100.0);
				grecMetalDt.setGrecDt(grecDt);
				grecMetalDt.setGrecMt(grecMt);
				
				grecMetalDtService.save(grecMetalDt);
				
			}
		
			
			List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(bagMt.getOrderDt(), false);
			for (OrderCompDt orderCompDt : orderCompDts) {
			
					StockCompDt stockCompDt = new StockCompDt();
					stockCompDt.setColor(orderCompDt.getColor());
					stockCompDt.setComponent(orderCompDt.getComponent());
					stockCompDt.setCompQty(orderCompDt.getCompQty());
					stockCompDt.setCompWt(Math.round(orderCompDt.getMetalWt()*1000.0)/1000.0);
					stockCompDt.setCreatedBy(principal.getName());
					stockCompDt.setCreatedDate(new Date());
					stockCompDt.setPurity(orderCompDt.getPurity());
					stockCompDt.setPurityConv(orderCompDt.getPurityConv());
					stockCompDt.setStockMt(stockMt);
					
				stockCompDtService.save(stockCompDt);	
				
				
				
				GrecCompDt grecCompDt = new GrecCompDt();
				grecCompDt.setColor(orderCompDt.getColor());
				grecCompDt.setComponent(orderCompDt.getComponent());
				grecCompDt.setCompRate(orderCompDt.getCompRate());
				grecCompDt.setCompQty(orderCompDt.getCompQty());
				grecCompDt.setMetalWt(Math.round(orderCompDt.getMetalWt()*1000.0)/1000.0);
				grecCompDt.setCreatedBy(principal.getName());
				grecCompDt.setCreatedDate(new Date());
				grecCompDt.setPurity(orderCompDt.getPurity());
				grecCompDt.setPurityConv(orderCompDt.getPurityConv());
				grecCompDt.setGrecDt(grecDt);
				grecCompDt.setGrecMt(grecMt);
				grecCompDtService.save(grecCompDt);
				
			} 
			
		
			
			
			StockTran stockTran = new StockTran();
			stockTran.setBarcode(bagMt.getBarcode());
			stockTran.setCreatedBy(principal.getName());
			stockTran.setCreatedDate(new Date());
			stockTran.setCurrStatus("GREC");
			stockTran.setLocation(grecMt.getLocation());
			stockTran.setRefTranId(grecDt.getId());
			stockTran.setTranDate(grecMt.getInvDate());
			stockTran.setTranType("GREC");
			stockTran.setCurrStk(true);
			stockTran.setStockMt(stockMt);
			stockTran.setParty(grecMt.getParty());
			stockTranService.save(stockTran);
			
			
			//Record Dump in Tranmt
			/*
			 * TranMt tranMtNew = new TranMt(); tranMtNew.setBagMt(bagMt);
			 * tranMtNew.setCreatedBy(principal.getName()); tranMtNew.setCreatedDate(new
			 * Date()); tranMtNew.setCurrStk(true); tranMtNew.setTrandate(new Date());
			 * tranMtNew.setDepartment(grecMt.getLocation());
			 */
		//	tranMtNew.setDeptFrom(tranMt.getDeptFrom());
		//	tranMtNew.setDeptTo(tranMt.getDeptTo());
			/*
			 * tranMtNew.setOrderDt(orderDt); tranMtNew.setOrderMt(orderDt.getOrderMt());
			 * tranMtNew.setPcs(bagMt.getQty()); tranMtNew.setRecWt(orderDt.getGrossWt());
			 * tranMtNew.setTime(new java.sql.Time(new java.util.Date().getTime()));
			 * tranMtNew.setRefMtId(grecDt.getId()); tranMtService.save(tranMtNew);
			 */
			
			  bagMt.setBagCloseFlg(true); 
			  bagMt.setModiBy(principal.getName());
			  bagMt.setModiDate(new Date());
			  bagMtService.save(bagMt);
			 
			
		}
		
		
		
		return "1";
	}

	@Override
	public String generateBarcodeForGrec(Integer mtId, String barcodeuploadFilePath, Principal principal) {
		// TODO Auto-generated method stub
		
		
		GrecMt grecMt = findOne(mtId);
	
		
		OrderType orderType = orderTypeService.findOne(grecMt.getOrderType().getId());
		
		List<GrecDt> grecDts = grecDtService.findByGrecMt(grecMt);
		for (GrecDt grecDt : grecDts) {
	
			StockMt stockMt = stockMtService.findByRefTranIdAndTranTypeAndDeactive(grecDt.getId(), "GREC", false);
			
			BagMt bagMt = bagMtService.findOne(stockMt.getBagId());	
			if (bagMt.getBarcode() == null) {
				
				String barcodeNm =null;
				String barcodeType=null;
				if(orderType.getName().equalsIgnoreCase("REPAIR ORDER")) {
					barcodeType="REP";
				}else {
					barcodeType="LDJ";
				}
				
				Integer barcodeSrNo=bagMtService.getMaxBarcodeSrno(barcodeType);
				barcodeSrNo++;
				
				if(orderType.getName().equalsIgnoreCase("REPAIR ORDER")) {
					barcodeNm="REP-"+barcodeSrNo;
				}else {
					barcodeNm="LDJ-"+barcodeSrNo;
				}
				
				bagMt.setBarcodeSrNo(barcodeSrNo);
				bagMt.setBarcode(barcodeNm);
				bagMt.setBarcodeType(barcodeType);
				bagMtService.save(bagMt);
				Utils.barcodeGeneration(bagMt.getId(), barcodeNm, barcodeuploadFilePath);
				
				
				//Stock mt Barcode save
				stockMt.setBarcode(barcodeNm);
				stockMt.setModiBy(principal.getName());
				stockMt.setModiDt(new Date());
				stockMtService.save(stockMt);
				
				
				StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("GREC", grecDt.getId(), true);
				stockTran.setBarcode(barcodeNm);
				stockTran.setModiBy(principal.getName());
				stockTran.setModiDt(new Date());
				stockTranService.save(stockTran);
				
				grecDt.setBarcode(barcodeNm);
				grecDt.setModiBy(principal.getName());
				grecDt.setModiDate(new Date());
				grecDtService.save(grecDt);
				
				
			}
			
		}
		return "1";
	}

	


}

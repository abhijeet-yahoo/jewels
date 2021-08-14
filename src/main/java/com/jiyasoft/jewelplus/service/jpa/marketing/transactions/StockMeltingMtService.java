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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QStockMeltingMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockMeltingMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockMeltingDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockMeltingMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StockMeltingMtService implements IStockMeltingMtService {
	
	@Autowired
	private IStockMeltingMtRepository stockMeltingMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockMeltingDtService stockMeltingDtService;
	
	@Autowired
	private IMeltingIssDtService meltingIssDtService;

	@Override
	public void save(StockMeltingMt stockMeltingMt) {
		// TODO Auto-generated method stub
		stockMeltingMtRepository.save(stockMeltingMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		stockMeltingMtRepository.delete(id);
		
	}

	@Override
	public StockMeltingMt findOne(int id) {
		// TODO Auto-generated method stub
		return stockMeltingMtRepository.findOne(id);
	}

	@Override
	public Page<StockMeltingMt> searchAll(Integer limit, Integer offset, String sort, String order, String search) {
		
		QStockMeltingMt qStockMeltingMt =QStockMeltingMt.stockMeltingMt;
		BooleanExpression expression=null;
		
		if (search != null) {
				expression =qStockMeltingMt.invNo.like("%" + search + "%");
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
	
		Page<StockMeltingMt> stockMeltingMtList =(Page<StockMeltingMt>) stockMeltingMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
			
		return stockMeltingMtList;
	}

	@Override
	public Integer getMaxInvSrno() {
		QStockMeltingMt qStockMeltingMt =QStockMeltingMt.stockMeltingMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStockMeltingMt)
			.where(qStockMeltingMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qStockMeltingMt.invSrNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}
		
		return retVal;
	}

	@Override
	public String saveStockMeltingMt(StockMeltingMt stockMeltingMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result, String vTranDate,Integer pLocationIds)
			throws ParseException {
		
		String action = "added";
		String retVal = "redirect:/marketing/transactions/stockMeltingMt/add.html";
	
	
		
	
		if (result.hasErrors()) {
			return "stockMeltingMt/add";
		}

		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				stockMeltingMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				stockMeltingMt.setInvSrNo(++maxSrno);
				int si = maxSrno.toString().length();
				
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
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
				
				
				stockMeltingMt.setInvNo("STKMELT-" + (bagSr) + "-" + vYear);
				stockMeltingMt.setCreatedBy(principal.getName());
				stockMeltingMt.setCreatedDt(new java.util.Date());
				

			} else {
				
					if(pLocationIds != null) {
						Department department = departmentService.findOne(pLocationIds);
						stockMeltingMt.setLocation(department);
					}
					
				
				stockMeltingMt.setModiBy(principal.getName());
				stockMeltingMt.setModiDt(new java.util.Date());
				
				action = "updated";
				retVal = "redirect:/marketing/transactions/stockMeltingMt.html";
			}
			
		
			
			
			save(stockMeltingMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/marketing/transactions/stockMeltingMt/edit/"+stockMeltingMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
	}
	}

	@Override
	public String stockMeltingMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
	StringBuilder sb = new StringBuilder();
		
		
		Page<StockMeltingMt> stockMeltingList=null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stockMeltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		stockMeltingList = searchAll(limit, offset, sort, order, search);

		sb.append("{\"total\":").append(stockMeltingList.getTotalElements()).append(",\"rows\": [");

		for (StockMeltingMt stockMeltingMt : stockMeltingList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(stockMeltingMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(stockMeltingMt.getId())
			.append("\",\"invNo\":\"")
			.append(stockMeltingMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(stockMeltingMt.getInvDateStr())
			.append("\",\"location\":\"")
			.append(stockMeltingMt.getLocation().getName())
						
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/marketing/transactions/stockMeltingMt/edit/")
								.append(stockMeltingMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteStockMeltingMt(event,");					
								sb.append(stockMeltingMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(stockMeltingMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/marketing/transactions/stockMeltingMt/edit/")
					.append(stockMeltingMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteStockMeltingMt(event,")	
						.append(stockMeltingMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(stockMeltingMt.getId())
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
	public String stockMeltingTransferListing(Integer deptId) {
		// TODO Auto-generated method stub
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_stockMeltingTransferList(?) }");
		query.setParameter(1, deptId);
		objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 	
			
				
				sb.append("{\"party\":\"")
			     .append(list[4] != null ? list[4] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"qty\":\"")
				 .append(list[3] != null ? list[3] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"image\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"barcode\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"mtId\":\"")
				 .append(list[0] != null ? list[0] : "")
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
		
//		Department location = departmentService.findOne(deptId);
//		List<StockMt> stockMt = stockMtService.findByLocationAndCurrStkAndDeactive(location, true, false);
//		
//		String str="";
//		 StringBuilder sb = new StringBuilder();
//		 sb.append("{\"total\":").append(stockMt.size()).append(",\"rows\": [");
//		
//		for (StockMt stockMt2 : stockMt) {
//			
//			String orderNo = null;
//			String refNo = null;
//			String bagNo = null;
//			Integer bagId = 0;
//			if(stockMt2.getBagId() != null) {
//			BagMt bagMt = bagMtService.findOne(stockMt2.getBagId());
//			if(bagMt != null) {
//				orderNo = bagMt.getOrderMt().getInvNo();
//				refNo = bagMt.getOrderDt().getRefNo();
//				bagNo = bagMt.getName();
//				bagId = bagMt.getId();
//			}
//			}
//			
//			
//			StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(stockMt2.getBarcode(), true);
//			
//			if(stockTran.getTranType().equalsIgnoreCase("melting")) {
//				continue;
//			}
//			
//			sb.append("{\"party\":\"")
//		     .append(stockTran.getParty() != null ? stockTran.getParty().getName() : "")
//		     .append("\",\"orderNo\":\"")
//			 .append(orderNo != null ? orderNo : "")
//			 .append("\",\"refNo\":\"")
//			 .append(refNo != null ? refNo : "")
//			 .append("\",\"bagNo\":\"")
//			 .append(bagNo != null ? bagNo : "")
//			 .append("\",\"bagId\":\"")
//			 .append(bagId > 0 ? bagId: "")
//			 .append("\",\"styleNo\":\"")
//			 .append(stockMt2.getDesign() != null ? stockMt2.getDesign().getMainStyleNo() :"")
//			 .append("\",\"qty\":\"")
//			 .append(stockMt2.getQty() !=null ? stockMt2.getQty() :0.0)
//			 .append("\",\"grossWt\":\"")
//			 .append(stockMt2.getGrossWt() != null ? stockMt2.getGrossWt() :0.0)
//			 .append("\",\"image\":\"")
//			 .append(stockMt2.getDesign() != null ? stockMt2.getDesign().getDefaultImage() :"")
//			 .append("\",\"barcode\":\"")
//			 .append(stockMt2.getBarcode() != null ? stockMt2.getBarcode() : "")
//			 .append("\",\"mtId\":\"")
//			 .append(stockMt2.getMtId() != null ? stockMt2.getMtId() : "")
//			 .append("\"},");
//		}
//		
//		str = sb.toString();
//		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
//				: str);
//		str += "]}";
//		
//		return str;
		
		
		
		
		
		
		
	}

	@Override
	public String stockMeltingMtDelete(Integer mtId,Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal = "-1";
		StockMeltingMt stockMeltingMt = findOne(mtId);
		List<StockMeltingDt> stockMeltingDts = stockMeltingDtService.findByStockMeltingMt(stockMeltingMt);
		
		for (StockMeltingDt stockMeltingDt : stockMeltingDts) {
			MeltingIssDt meltingIssDt = meltingIssDtService.findByTranTypeAndRefTranMetalIdAndDeactive("MELTINGISS", stockMeltingDt.getId(), false);
			if(meltingIssDt != null) {
				return  "-2";
			}else {
				
				StockMt stockMt = stockMtService.findOne(stockMeltingDt.getStockMt().getMtId());
				stockMt.setCurrStk(true);
				stockMtService.save(stockMt);
				
			//	StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("Melting", stockMeltingDt.getId(), false);
				
				
			List<StockTran>stockTrans=stockTranService.findByTranTypeAndRefTranId("Melting", stockMeltingDt.getId());
			
			for(StockTran stockTran2 :stockTrans) {
				
				StockTran stockTranNew = stockTranService.findOne(stockTran2.getRefStkTranId());
				stockTranNew.setCurrStk(true);
				stockTranService.save(stockTranNew);
				
			
				
			}
			
			for(StockTran stockTran2 :stockTrans) {
				stockTranService.delete(stockTran2.getId());
			}
				
				
			
				
				stockMeltingDtService.delete(stockMeltingDt.getId());
				retVal = "1";
			}
			
		}
		
		delete(mtId);
		
		return retVal;
	}

	
}

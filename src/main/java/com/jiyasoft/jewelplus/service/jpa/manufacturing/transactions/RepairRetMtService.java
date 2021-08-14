package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QRepairRetMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IRepairRetMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class RepairRetMtService implements IRepairRetMtService{
	
	@Autowired
	private IRepairRetMtRepository repairRetMtRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDepartmentService departmentService;
		
	@Autowired
	private IOrderTypeService orderTypeService;
	
	@Autowired
	private IRepairRetDtService repairRetDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IRepairRetStnDtService repairRetStnDtService;
	
	@Autowired
	private IRepairRetCompDtService repairRetCompDtService;
	
	
	@Autowired
	private IStockMtService stockMtService;
	
	
	@Autowired
	private IRepairRetMetalDtService repairRetMetalDtService;

	@Override
	public Page<RepairRetMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		
		QRepairRetMt qRepairRetMt = QRepairRetMt.repairRetMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression = qRepairRetMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =qRepairRetMt.invNo.like("%" + search + "%");
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
	
		Page<RepairRetMt> repairRetMtList =(Page<RepairRetMt>) repairRetMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return repairRetMtList;
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QRepairRetMt qRepairRetMt = QRepairRetMt.repairRetMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qRepairRetMt)
			.where(qRepairRetMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qRepairRetMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public void save(RepairRetMt repairRetMt) {
		// TODO Auto-generated method stub
		repairRetMtRepository.save(repairRetMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		repairRetMtRepository.delete(id);
	}

	@Override
	public RepairRetMt findOne(int id) {
		// TODO Auto-generated method stub
		return repairRetMtRepository.findOne(id);
	}

	@Override
	public String deleteRepairRetMt(Integer mtId) {
		// TODO Auto-generated method stub
		
		RepairRetMt repairRetMt = findOne(mtId);
		
		List<RepairRetDt> repairRetDts = repairRetDtService.findByRepairRetMt(repairRetMt);
		
		for (RepairRetDt repairRetDt : repairRetDts) {
			
			StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(repairRetDt.getBarcode(), true, false);
			
			StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("REPAIRRETURN", repairRetDt.getId(), false);
			
		
			if(stockMt != null) {
				
				return "Can not Delete";
			}else {
				
				List<RepairRetStnDt> repairRetStnDts = repairRetStnDtService.findByRepairRetDt(repairRetDt);
				for(RepairRetStnDt repairRetStnDt :repairRetStnDts) {
					repairRetStnDtService.delete(repairRetStnDt.getId());
					
				}
				
				List<RepairRetMetalDt> repairRetMetalDts=repairRetMetalDtService.findByRepairRetDt(repairRetDt);
				for(RepairRetMetalDt repairRetMetalDt :repairRetMetalDts) {
					repairRetMetalDtService.delete(repairRetMetalDt.getId());
					
				}
				
				List<RepairRetCompDt>repairRetCompDts=repairRetCompDtService.findByRepairRetDt(repairRetDt);
				for(RepairRetCompDt repairRetCompDt :repairRetCompDts) {
					repairRetCompDtService.delete(repairRetCompDt.getId());
					
				}
				
				
			
				repairRetDtService.delete(repairRetDt.getId());
				
				
				if(stockTran !=null) {
					
					StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
					stockTran2.setCurrStk(true);
					stockTranService.save(stockTran2);
					
					stockTranService.delete(stockTran.getId());
					
				}
				 
				StockMt stockMt2 = stockMtService.findOne(repairRetDt.getStockMt().getMtId());	
				stockMt2.setCurrStk(true);
				stockMtService.save(stockMt2);
				
				
			}
		}
		
		delete(mtId);
		
		return "1";
	}

	@Override
	public String repairRetListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
StringBuilder sb = new StringBuilder();
		
		
		Page<RepairRetMt> repairRetList =null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("repairRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		repairRetList = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(repairRetList.getTotalElements()).append(",\"rows\": [");

		for (RepairRetMt repairRetMt : repairRetList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(repairRetMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(repairRetMt.getId())
			.append("\",\"invNo\":\"")
			.append(repairRetMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(repairRetMt.getInvDateStr())
			.append("\",\"party\":\"")
			.append(repairRetMt.getParty().getName())
			.append("\",\"location\":\"")
			.append(repairRetMt.getLocation().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/repairRetMt/edit/")
								.append(repairRetMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteRepairRetMt(event,");					
								sb.append(repairRetMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(repairRetMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/repairRetMt/edit/")
					.append(repairRetMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteRepairRetMt(event,")	
						.append(repairRetMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(repairRetMt.getId())
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
	public String saveRepairRetMt(RepairRetMt repairRetMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result, Integer pPartyIds, Integer pLocationIds, String vTranDate)
			throws ParseException {
		// TODO Auto-generated method stub
		String action = "Added";
		String retVal = "redirect:/manufacturing/transactions/repairRetMt/add.html";
	
	
		
	
		if (result.hasErrors()) {
			return "repairRetMt/add";
		}

		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				repairRetMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				repairRetMt.setSrNo(++maxSrno);
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
				
				
				repairRetMt.setInvNo("RET/" + (bagSr) + "/" + presentYear+"-"+nextYear);
				repairRetMt.setCreatedBy(principal.getName());
				repairRetMt.setCreatedDate(new java.util.Date());
				

			} else {
				repairRetMt.setModiBy(principal.getName());
				repairRetMt.setModiDate(new java.util.Date());
				
				if(pPartyIds != null) {
					Party party =  partyService.findOne(pPartyIds);
					Department department = departmentService.findOne(pLocationIds);
					
					repairRetMt.setParty(party);
					repairRetMt.setLocation(department);
						
				}
				
				action = "Updated";
		//		retVal = "redirect:/manufacturing/transactions/repairRetMt.html";
			}
			
		
			if(repairRetMt.getHsnMast().getId() == null) {
				repairRetMt.setHsnMast(null);
			}
			
			save(repairRetMt);
			
			/*
			 * if (action.equals("added")) { retVal =
			 * "redirect:/manufacturing/transactions/repairRetMt/edit/"+repairRetMt.getId()+
			 * ".html"; }
			 */
			
			 retVal = "redirect:/manufacturing/transactions/repairRetMt/edit/"+repairRetMt.getId()+".html";
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
	}
	}

	@Override
	public String repairReturnTransferList(Integer partyId, Integer locationId) {
		// TODO Auto-generated method stub
	List<Object[]> objects =new ArrayList<Object[]>();
		
	String orderTypeCond ="";
	
		List<OrderType> orderTypes = orderTypeService.findRepairAndPurchaseOrderType(); 
		for (OrderType orderType : orderTypes) {
			if(orderTypeCond.isEmpty()) {
				orderTypeCond = orderType.getId().toString();	
			}else {
				orderTypeCond = orderTypeCond+ ","+orderType.getId().toString();
			}
			
		}
		
		
		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call JSP_repairRetStkBarcodeList(?,?,?) }");
			query.setParameter(1, orderTypeCond);
			query.setParameter(2, partyId);
			query.setParameter(3, locationId);
			objects = query.getResultList();
		
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
				
				sb.append("{\"party\":\"")
			     .append(list[10] != null ? list[10] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[0] != null ? list[0] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"qty\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"image\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\",\"barcode\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"mtId\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

}

package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetMtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockTranRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ConsigRetMtService implements IConsigRetMtService {
	
	@Autowired
	private IConsigRetMtRepository consigRetMtRepository;
	
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IConsigRetDtService consigRetDtService;

	@Autowired
	private IConsigRetMetalDtService consigRetMetalDtService;
	
	@Autowired
	private IConsigRetStnDtService consigRetStnDtService;
	
	@Autowired
	private IConsigRetCompDtService consigRetCompDtService;
	
	@Autowired
	private IConsigRetLabDtService consigRetLabDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IConsigDtService consigDtService;
	
	@Autowired
	private IConsigMetalDtService consigMetalDtService;
	
	@Autowired
	private IConsigStnDtService consigStnDtService;
	
	@Autowired
	private IConsigCompDtService consigCompDtService;
	
	@Autowired
	private IStockTranRepository stockTranRepository;
	
	@Autowired
	private IConsigLabDtService consigLabDtService;
	
	@Autowired
	private IConsigRetRmMetalDtService consigRetRmMetalDtService;
	
	@Autowired
	private IConsigRetRmCompDtService consigRetRmCompDtService;
	
	@Autowired
	private IConsigRetRmStnDtService consigRetRmStnDtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	
	@Autowired
	private IConsigRmMetalDtService consigRmMetalDtService;
	
	@Autowired
	private ICompTranService compTranService ;
	
	@Autowired
	private IConsigRmCompDtService consigRmCompDtService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IConsigRmStnDtService consigRmStnDtService;
	
	
	

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QConsigRetMt qConsigRetMt = QConsigRetMt.consigRetMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qConsigRetMt)
			.where(qConsigRetMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qConsigRetMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public void save(ConsigRetMt consigRetMt) {
		// TODO Auto-generated method stub
		consigRetMtRepository.save(consigRetMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigRetMtRepository.delete(id);
		
	}

	@Override
	public ConsigRetMt findOne(int id) {
		// TODO Auto-generated method stub
		return consigRetMtRepository.findOne(id);
	}

	@Override
	public String deleteConsigRetMt(Integer mtId) {
		// TODO Auto-generated method stub
String retVal="-1";
		
		ConsigRetMt consigRetMt = findOne(mtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = consigRetMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
			
			List<ConsigRetDt> consigRetDts = consigRetDtService.findByConsigRetMt(consigRetMt);
			
			for (ConsigRetDt consigRetDt : consigRetDts) {
				
				 StockMt stockMt =  stockMtService.findByBarcodeAndCurrStkAndDeactive(consigRetDt.getBarcode(),  true, false);
				if(stockMt == null) {
					
					return "Can not Delete";
					
				}else {
					
					List<ConsigRetStnDt> consigRetStnDts=consigRetStnDtService.findByConsigRetDt(consigRetDt);
					for(ConsigRetStnDt consigRetStnDt :consigRetStnDts) {
						consigRetStnDtService.delete(consigRetStnDt.getId());
					}
					
					List<ConsigRetMetalDt> consigRetMetalDts = consigRetMetalDtService.findByConsigRetDt(consigRetDt);
					for(ConsigRetMetalDt consigRetMetalDt :consigRetMetalDts) {
						consigRetMetalDtService.delete(consigRetMetalDt.getId());
					}
					
					List<ConsigRetCompDt> consigRetCompDts = consigRetCompDtService.findByConsigRetDt(consigRetDt);
					for(ConsigRetCompDt consigRetCompDt :consigRetCompDts) {
						consigRetCompDtService.delete(consigRetCompDt.getId());
					}
					
					
					List<ConsigRetLabDt> consigRetLabDts = consigRetLabDtService.findByConsigRetDt(consigRetDt);
					for(ConsigRetLabDt consigRetLabDt :consigRetLabDts) {
						consigRetLabDtService.delete(consigRetLabDt.getId());
					}
					
					//ConsigDt
					ConsigRetDt consigRetDt2 = consigRetDtService.findByRefConsigDtId(consigRetDt.getRefConsigDtId());
					ConsigDt consigDt = consigDtService.findOne(consigRetDt2.getRefConsigDtId());
					consigDt.setAdjQty(consigDt.getAdjQty() - consigRetDt2.getPcs());
					consigDtService.save(consigDt);
					
					
					consigRetDtService.delete(consigRetDt.getId());
					
					
					
					
					StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("CONSIGNMENTRET", consigRetDt.getId(), true);
					if(stockTran !=null) {
						
						StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
						stockTran2.setCurrStk(true);
						stockTranService.save(stockTran2);
						
						stockTranService.delete(stockTran.getId());
						
					}
					
					
					 
					  stockMt.setCurrStk(false);
					  stockMtService.save(stockMt);
					 
					
					
				}
				
			}
			
			//Loose Metal
			List<ConsigRetRmMetalDt> consigRetRmMetalDts= consigRetRmMetalDtService.findByConsigRetMt(consigRetMt);
			for (ConsigRetRmMetalDt consigRetRmMetalDt : consigRetRmMetalDts) {
				List<MetalTran> metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(consigRetRmMetalDt.getId(), "ConsigRetRmMetal", false);
				for (MetalTran metTran : metalTran) {
					metalTranService.delete(metTran.getId());
				}
				
				if(consigRetRmMetalDt.getRefTranDtId() != null) {
					ConsigRmMetalDt consigRmMetalDt = consigRmMetalDtService.findOne(consigRetRmMetalDt.getRefTranDtId());
					consigRmMetalDt.setBalance(Math.round((consigRmMetalDt.getBalance() + consigRetRmMetalDt.getMetalWt())*100.0)/100.0);
					consigRmMetalDt.setAdjWt(Math.round((consigRmMetalDt.getAdjWt() - consigRetRmMetalDt.getMetalWt())*100.0)/100.0);
					consigRmMetalDtService.save(consigRmMetalDt);
				}
				
				consigRetRmMetalDtService.delete(consigRetRmMetalDt.getId());
			}
			
			
			//Loose Component
			List<ConsigRetRmCompDt> consigRetRmCompDts = consigRetRmCompDtService.findByConsigRetMt(consigRetMt);
			for (ConsigRetRmCompDt consigRetRmCompDt : consigRetRmCompDts) {
				
				List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(consigRetRmCompDt.getId(), "ConsigRetRmComp");
				for (CompTran comTran : compTran) {
					compTranService.delete(comTran.getId());
				}
				
				if(consigRetRmCompDt.getRefTranDtId() != null) {
				
				ConsigRmCompDt consigRmCompDt = consigRmCompDtService.findOne(consigRetRmCompDt.getRefTranDtId());
				consigRmCompDt.setBalance(consigRmCompDt.getBalance() + consigRetRmCompDt.getMetalWt());
				consigRmCompDt.setAdjWt(consigRmCompDt.getAdjWt() - consigRetRmCompDt.getMetalWt() );
				consigRmCompDtService.save(consigRmCompDt);
				
				}
				consigRetRmCompDtService.delete(consigRetRmCompDt.getId());
			}
			
			
			//Loose Stone
			List<ConsigRetRmStnDt> consigRetRmStnDts = consigRetRmStnDtService.findByConsigRetMt(consigRetMt);
			for (ConsigRetRmStnDt consigRetRmStnDt : consigRetRmStnDts) {
				StoneTran stoneTran = stoneTranService.RefTranIdAndTranType(consigRetRmStnDt.getId(), "ConsigRetRmStn");
				stoneTranService.delete(stoneTran.getId());
				
				if(consigRetRmStnDt.getRefTranDtId() != null) {
					ConsigRmStnDt consigRmStnDt = consigRmStnDtService.findOne(consigRetRmStnDt.getRefTranDtId());
					
					consigRmStnDt.setBalCarat(consigRmStnDt.getBalCarat() + consigRetRmStnDt.getCarat());
					consigRmStnDt.setBalStone(consigRmStnDt.getBalStone() + consigRetRmStnDt.getStone());
					consigRmStnDt.setAdjWt(consigRmStnDt.getAdjWt() - consigRetRmStnDt.getCarat());
					consigRmStnDtService.save(consigRmStnDt);
				}
				consigRetRmStnDtService.delete(consigRetRmStnDt.getId());
			}
			
			
			
			
			
			
			delete(consigRetMt.getId());
			
			retVal ="1";
			
	}else {
			
			return "Can Not Delete BackDate Entry";
		
		}
		
		return retVal;
	}

	@Override
	public List<ConsigRetMt> findByParty(Party party) {
		// TODO Auto-generated method stub
		return consigRetMtRepository.findByParty(party);
	}

	@Override
	public String consigRetMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		
		
		Page<ConsigRetMt> consigRetMtList = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("consigRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		consigRetMtList = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(consigRetMtList.getTotalElements()).append(",\"rows\": [");

		for (ConsigRetMt consigRetMt : consigRetMtList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(consigRetMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(consigRetMt.getId())
			.append("\",\"invNo\":\"")
			.append(consigRetMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(consigRetMt.getInvDateStr())
			.append("\",\"party\":\"")
			.append(consigRetMt.getParty().getName())
			.append("\",\"location\":\"")
			.append(consigRetMt.getLocation().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/marketing/transactions/consigRetMt/edit/")
								.append(consigRetMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteConsigRetMt(event,");					
								sb.append(consigRetMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(consigRetMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/marketing/transactions/consigRetMt/edit/")
					.append(consigRetMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteConsigMt(event,")	
						.append(consigRetMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(consigRetMt.getId())
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
	public String saveConsigRetMt(ConsigRetMt consigRetMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result, Integer pPartyIds, Integer pLocationIds, Integer pEmployeeIds,String vTranDate) throws ParseException {
		
		String action = "added";
		String retVal = "redirect:/marketing/transactions/consigRetMt/add.html";
	
		if (result.hasErrors()) {
			return "consigMt/add";
		}
		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				consigRetMt.setInvDate(dates);
				
				}
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno =getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				consigRetMt.setSrNo(++maxSrno);
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
				
				
				consigRetMt.setInvNo("CONSRET/" + (bagSr) + "/" + presentYear+"-"+nextYear);
				consigRetMt.setCreatedBy(principal.getName());
				consigRetMt.setCreatedDate(new java.util.Date());
				

			} else {
				consigRetMt.setModiBy(principal.getName());
				consigRetMt.setModiDate(new java.util.Date());
				
				if(pPartyIds != null) {

					Party party =  partyService.findOne(pPartyIds);
					Department department = departmentService.findOne(pLocationIds);
					
					consigRetMt.setParty(party);
					consigRetMt.setLocation(department);
				}

				
				action = "updated";
				retVal = "redirect:/marketing/transactions/consigRetMt.html";
			}
			

			if(consigRetMt.getHsnMast().getId() == null) {
				consigRetMt.setHsnMast(null);
			}
			
			if(consigRetMt.getModeOfTransport().getId() == null) {
				consigRetMt.setModeOfTransport(null);
			}
			
			if(pEmployeeIds != null) {
				Employee employee = employeeService.findOne(pEmployeeIds);
				consigRetMt.setEmployee(employee);
			}
			
			if(consigRetMt.getEmployee() != null) {
			if(consigRetMt.getEmployee().getId() == null) {
				consigRetMt.setEmployee(null);
			}
			}
			
			save(consigRetMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/marketing/transactions/consigRetMt/edit/"+consigRetMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
		}
	}

	@Override
	public Page<ConsigRetMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		QConsigRetMt qConsigRetMt = QConsigRetMt.consigRetMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression =qConsigRetMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =qConsigRetMt.invNo.like("%" + search + "%");
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
	
		Page<ConsigRetMt> consigRetList =(Page<ConsigRetMt>) consigRetMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return consigRetList;
	}

	@Override
	public String consignmetTransfer(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		ConsigRetMt consigRetMt = findOne(pMtId);
		JSONArray jsonConsigDt = new JSONArray(data);
		
		
		try {
			

			for (int y = 0; y < jsonConsigDt.length(); y++) {
				
				JSONObject dataConsigDt = (JSONObject) jsonConsigDt.get(y);
				
				ConsigDt consigDt = consigDtService.findOne((int)(Double.parseDouble(dataConsigDt.get("id").toString())));
				
				ConsigRetDt consigRetDt2 = consigRetDtService.findByConsigRetMtAndBarcode(consigRetMt,dataConsigDt.get("barcode").toString());
				if(consigRetDt2 != null) {
					return "Duplicate Barcode";
				}
				
				ConsigRetDt consigRetDt = new ConsigRetDt();
				consigRetDt.setBarcode(consigDt.getBarcode());
				consigRetDt.setCreatedBy(principal.getName());
				consigRetDt.setCreatedDate(new Date());
				consigRetDt.setDesign(consigDt.getDesign());
				consigRetDt.setGrossWt(consigDt.getGrossWt());
				consigRetDt.setNetWt(consigDt.getNetWt());
				consigRetDt.setConsigRetMt(consigRetMt);
				consigRetDt.setPcs(consigDt.getPcs());
				consigRetDt.setRefConsigDtId(consigDt.getId());
				consigRetDt.setMetalValue(consigDt.getMetalValue());
				consigRetDt.setStoneValue(consigDt.getStoneValue());
				consigRetDt.setLabValue(consigDt.getLabValue());
				consigRetDt.setSetValue(consigDt.getSetValue());
				consigRetDt.setCompValue(consigDt.getCompValue());
				consigRetDt.setHandlingValue(consigDt.getHandlingValue());
				consigRetDt.setFinalPrice((double) Math.round(consigDt.getFinalPrice()));
				consigRetDt.setFob((double) Math.round(consigDt.getFob()));
				consigRetDt.setDestination(consigDt.getDestination());
				consigRetDt.setDiscAmount(consigDt.getDiscAmount());
				consigRetDt.setDiscPerc(consigDt.getDiscPerc());
				consigRetDt.setLossValue(consigDt.getLossValue());
				consigRetDt.setLossWt(consigDt.getLossWt());
				consigRetDt.setNetAmount(consigDt.getNetAmount());
				consigRetDt.setOrderRef(consigDt.getOrderRef());
				consigRetDt.setOther(consigDt.getOther());
				consigRetDt.setHallMarking(consigDt.getHallMarking());
				consigRetDt.setLazerMarking(consigDt.getLazerMarking());
				consigRetDt.setEngraving(consigDt.getEngraving());
				consigRetDt.setGrading(consigDt.getGrading());
				consigRetDtService.save(consigRetDt);
				
				consigDt.setAdjQty(consigDt.getPcs());
				consigDt.setModiBy(principal.getName());
				consigDt.setModiDate(new Date());
				
				consigDtService.save(consigDt);
				
				List<ConsigMetalDt> consigMetalDts = consigMetalDtService.findByConsigDt(consigDt);
				for(ConsigMetalDt consigMetalDt :consigMetalDts) {
					
					ConsigRetMetalDt consigRetMetalDt = new ConsigRetMetalDt();
					consigRetMetalDt.setColor(consigMetalDt.getColor());
					consigRetMetalDt.setCreateDate(new Date());
					consigRetMetalDt.setCreatedBy(principal.getName());
					consigRetMetalDt.setMainMetal(consigMetalDt.getMainMetal());
					consigRetMetalDt.setMetalPcs(consigMetalDt.getMetalPcs());
					consigRetMetalDt.setMetalWeight(consigMetalDt.getMetalWeight());
					consigRetMetalDt.setConsigRetDt(consigRetDt);
					consigRetMetalDt.setConsigRetMt(consigRetMt);
					consigRetMetalDt.setPartNm(consigMetalDt.getPartNm());
					consigRetMetalDt.setPurity(consigMetalDt.getPurity());
					consigRetMetalDt.setPurityConv(consigMetalDt.getPurityConv());
					consigRetMetalDt.setMetalValue(consigMetalDt.getMetalValue());
					consigRetMetalDt.setMetalRate(consigMetalDt.getMetalRate());
					consigMetalDt.setPerGramRate(consigMetalDt.getPerGramRate());
					consigMetalDt.setPerPcRate(consigMetalDt.getPerPcRate());
					
					consigRetMetalDtService.save(consigRetMetalDt);
					
				}
				
				List<ConsigStnDt> consigStnDts = consigStnDtService.findByConsigDt(consigDt);
				
				for(ConsigStnDt consigStnDt : consigStnDts) {
					
					ConsigRetStnDt consigRetStnDt =new ConsigRetStnDt();
					consigRetStnDt.setCarat(consigStnDt.getCarat());
					consigRetStnDt.setCreatedBy(principal.getName());
					consigRetStnDt.setCreatedDate(new Date());
					consigRetStnDt.setConsigRetDt(consigRetDt);
					consigRetStnDt.setConsigRetMt(consigRetMt);
					consigRetStnDt.setPartNm(consigStnDt.getPartNm());
					consigRetStnDt.setQuality(consigStnDt.getQuality());
					consigRetStnDt.setSetting(consigStnDt.getSetting());
					consigRetStnDt.setSettingType(consigStnDt.getSettingType());
					consigRetStnDt.setShape(consigStnDt.getShape());
					consigRetStnDt.setSieve(consigStnDt.getSieve());
					consigRetStnDt.setSize(consigStnDt.getSize());
					consigRetStnDt.setSizeGroup(consigStnDt.getSizeGroup());
					consigRetStnDt.setStone(consigStnDt.getStone());
					consigRetStnDt.setStoneType(consigStnDt.getStoneType());
					consigRetStnDt.setSubShape(consigStnDt.getSubShape());
					consigRetStnDt.setCenterStone(consigStnDt.getCenterStone());
					consigRetStnDt.setStoneRate(consigStnDt.getStoneRate());
					consigRetStnDt.setStoneValue(consigStnDt.getStoneValue());
					consigRetStnDt.setHandlingRate(consigStnDt.getHandlingRate());
					consigRetStnDt.setHandlingValue(consigStnDt.getHandlingValue());
					consigRetStnDt.setHdlgPerCarat(consigStnDt.getHdlgPerCarat());
					consigRetStnDt.setHdlgPercentWise(consigStnDt.getHdlgPercentWise());
					consigRetStnDt.setrLock(consigStnDt.getrLock());
					consigRetStnDt.setSetRate(consigStnDt.getSetRate());
					consigRetStnDt.setSetValue(consigStnDt.getSetValue());
					consigRetStnDt.setPerPcsRateFlg(consigStnDt.getPerPcsRateFlg());
					consigRetStnDtService.save(consigRetStnDt);
					
					
					
					
				}
				
				
				List<ConsigCompDt> consigCompDts = consigCompDtService.findByConsigDt(consigDt);
				for(ConsigCompDt consigCompDt :consigCompDts) {
					
					ConsigRetCompDt consigRetCompDt = new ConsigRetCompDt();
					consigRetCompDt.setColor(consigCompDt.getColor());
					consigRetCompDt.setComponent(consigCompDt.getComponent());
					consigRetCompDt.setCompQty(consigCompDt.getCompQty());
					consigRetCompDt.setCreatedBy(principal.getName());
					consigRetCompDt.setCreatedDate(new Date());
					consigRetCompDt.setConsigRetMt(consigRetMt);
					consigRetCompDt.setConsigRetDt(consigRetDt);
					consigRetCompDt.setPurity(consigCompDt.getPurity());
					consigRetCompDt.setPurityConv(consigCompDt.getPurityConv());
					consigRetCompDt.setCompWt(consigCompDt.getCompWt());
					consigRetCompDt.setCompValue(consigCompDt.getCompValue());
					consigRetCompDt.setLossPerc(consigCompDt.getLossPerc());
					consigRetCompDt.setMetalRate(consigCompDt.getMetalRate());
					consigRetCompDt.setMetalValue(consigCompDt.getMetalValue());
					consigRetCompDt.setPerGramMetalRate(consigCompDt.getPerGramMetalRate());
					consigRetCompDt.setPerGramRate(consigCompDt.getPerGramRate());
					consigRetCompDtService.save(consigRetCompDt);
					
				}
				
				
				List<ConsigLabDt> consigLabDts = consigLabDtService.findByConsigDt(consigDt);
				for (ConsigLabDt consigLabDt : consigLabDts) {
					ConsigRetLabDt consigRetLabDt = new ConsigRetLabDt();
					consigRetLabDt.setLabourRate(consigLabDt.getLabourRate());
					consigRetLabDt.setConsigRetDt(consigRetDt);
					consigRetLabDt.setConsigRetMt(consigRetMt);
					consigRetLabDt.setLabourType(consigLabDt.getLabourType());
					consigRetLabDt.setLabourValue(consigLabDt.getLabourValue());
					consigRetLabDt.setPerCaratRate(consigLabDt.getPerCaratRate());
					consigRetLabDt.setPercentage(consigLabDt.getPercentage());
					consigRetLabDt.setPerPcRate(consigLabDt.getPerPcRate());
					consigRetLabDt.setPerGramRate(consigLabDt.getPerGramRate());
					consigRetLabDt.setrLock(consigLabDt.getrLock());
					consigRetLabDt.setMetal(consigLabDt.getMetal());
					consigRetLabDtService.save(consigRetLabDt);
					
				}
				
			
				StockTran stockTran = stockTranRepository.findByTranTypeAndRefTranIdAndCurrStk("CONSIGNMENTISS",consigDt.getId(),true);
				StockTran stockTran2 = new StockTran();
				stockTran2.setBarcode(consigRetDt.getBarcode());
				stockTran2.setCreatedBy(principal.getName());
				stockTran2.setCreatedDate(new Date());
				stockTran2.setCurrStatus("CONSIGNMENTRET");
				stockTran2.setCurrStk(true);
				stockTran2.setLocation(stockTran.getLocation());
				stockTran2.setRefStkTranId(stockTran.getId());
				stockTran2.setRefTranId(consigRetDt.getId());
				stockTran2.setTranDate(consigRetMt.getInvDate());
				stockTran2.setTranType("CONSIGNMENTRET");
				stockTran2.setParty(consigRetMt.getParty());
				stockTran2.setEmployee(consigRetMt.getEmployee());
				stockTran2.setStockMt(stockTran.getStockMt());
				stockTranService.save(stockTran2);
				
				stockTran.setCurrStk(false);
				stockTran.setIssueDate(consigRetMt.getInvDate());
				stockTranService.save(stockTran);
				
				
	//		   StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(consigRetDt.getBarcode(),  false, false);
			  StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());	
			  stockMt.setCurrStk(true); 
			  stockMtService.save(stockMt);
			
				
			}
			retVal = "1";
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}


}

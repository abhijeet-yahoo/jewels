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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QSaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockCompDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMetalDtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMtRepository;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockStnDtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetMtRepository;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockTranRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class SaleRetMtService implements ISaleRetMtService {

	
	  @Autowired
	  private ISaleRetMtRepository saleRetMtRepository;
	  
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
	  private ISaleRetDtService saleRetDtService;
	  
	  @Autowired 
	  private ISaleRetMetalDtService saleRetMetalDtService;
	  
	  @Autowired 
	  private ISaleRetStnDtService saleRetStnDtService;
	  
	  @Autowired 
	  private ISaleRetCompDtService saleRetCompDtService;
	  
	  @Autowired 
	  private ISaleRetLabDtService saleRetLabDtService;
	  
	  @Autowired 
	  private IStockTranService stockTranService;
	  
	  @Autowired 
	  private IPartyService partyService;
	  
	  @Autowired 
	  private IDepartmentService departmentService;
	  
	  @Autowired 
	  private ISaleDtService saleDtService;
	  
	  @Autowired 
	  private ISaleMetalDtService saleMetalDtService;
	  
	  @Autowired 
	  private ISaleStnDtService saleStnDtService;
	  
	  @Autowired 
	  private ISaleCompDtService saleCompDtService;
	  
	  @Autowired 
	  private IStockTranRepository stockTranRepository;
	  
	  @Autowired
	  private ISaleLabDtService saleLabDtService;
	  
	  @Autowired
	  private IStockMtService stockMtService;
	  
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
		private ISaleRetRmMetalDtService saleRetRmMetalDtService;
		
		@Autowired
		private ISaleRetRmStnDtService saleRetRmStnDtService;
		
		@Autowired
		private ISaleRetRmCompDtService saleRetRmCompDtService;
		
		@Autowired
		private IMetalTranService metalTranService;
		
		@Autowired
		private ICompTranService compTranService;
		
		@Autowired
		private IStoneTranService stoneTranService;
		
		@Autowired
		private ISaleRmMetalDtService saleRmMetalDtService;
		
		@Autowired
		private ISaleRmCompDtService saleRmCompDtService;
		
		@Autowired
		private ISaleRmStnDtService saleRmStnDtService;
		
	 
		
	@Override
	public void save(SaleRetMt saleRetMt) {

		saleRetMtRepository.save(saleRetMt);

	}

	@Override
	public void delete(int id) {
		 saleRetMtRepository.delete(id);
	
	}

	@Override 
	public SaleRetMt findOne(int id) { 
		
		return saleRetMtRepository.findOne(id);
	}

	@Override
	public Integer getMaxInvSrno() {
		QSaleRetMt qSaleRetMt = QSaleRetMt.saleRetMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qSaleRetMt)
			.where(qSaleRetMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qSaleRetMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public String deleteSaleRetMt(Integer mtId) {
		
		String retVal="-1";
		
		SaleRetMt saleRetMt = findOne(mtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = saleRetMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
			
			List<SaleRetDt> saleRetDts = saleRetDtService.findBySaleRetMt(saleRetMt);
			
			for (SaleRetDt saleRetDt : saleRetDts) {
				
				List<StockTran> stockTrans = stockTranService.findByBarcode(saleRetDt.getBarcode());
				
				if(stockTrans.size() > 1) {
					

					StockMt stockMt =  stockMtService.findByBarcodeAndCurrStkAndDeactive(saleRetDt.getBarcode(),  true, false);
					if(stockMt == null) {
						
						return "Can not Delete, Qty Adjusted";
						
					}else {
						List<SaleRetStnDt> saleRetStnDts=saleRetStnDtService.findBySaleRetDt(saleRetDt);
						for(SaleRetStnDt saleRetStnDt :saleRetStnDts) {
							saleRetStnDtService.delete(saleRetStnDt.getId());
						}
						
						List<SaleRetMetalDt> saleRetMetalDts = saleRetMetalDtService.findBySaleRetDt(saleRetDt);
						for(SaleRetMetalDt saleRetMetalDt :saleRetMetalDts) {
							saleRetMetalDtService.delete(saleRetMetalDt.getId());
						}
						
						List<SaleRetCompDt> saleRetCompDts = saleRetCompDtService.findBySaleRetDt(saleRetDt);
						for(SaleRetCompDt saleRetCompDt :saleRetCompDts) {
							saleRetCompDtService.delete(saleRetCompDt.getId());
						}
						
						
						List<SaleRetLabDt> saleRetLabDts = saleRetLabDtService.findBySaleRetDt(saleRetDt);
						for(SaleRetLabDt saleRetLabDt :saleRetLabDts) {
							saleRetLabDtService.delete(saleRetLabDt.getId());
						}
						
						//SaleDt
						SaleRetDt saleRetDt2 = saleRetDtService.findByRefSaleDtId(saleRetDt.getRefSaleDtId());
						SaleDt saleDt = saleDtService.findOne(saleRetDt2.getRefSaleDtId());
						saleDt.setAdjQty(saleDt.getAdjQty() - saleRetDt2.getPcs());
						saleDtService.save(saleDt);
						
						
					
						StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("SALERET", saleRetDt.getId(), true);
						if(stockTran !=null) {
							
							
							
							StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
							stockTran2.setCurrStk(true);
							stockTranService.save(stockTran2);
							
							stockTranService.delete(stockTran.getId());
							
						}
						
						
						 stockMt.setCurrStk(false); 
						  stockMtService.save(stockMt);
							
						}
						
						
					saleRetDtService.delete(saleRetDt.getId());
					
						
						
					
					
				}else {
					
						
						List<SaleRetStnDt> saleRetStnDts=saleRetStnDtService.findBySaleRetDt(saleRetDt);
						for(SaleRetStnDt saleRetStnDt :saleRetStnDts) {
							saleRetStnDtService.delete(saleRetStnDt.getId());
						}
						
						List<SaleRetMetalDt> saleRetMetalDts = saleRetMetalDtService.findBySaleRetDt(saleRetDt);
						for(SaleRetMetalDt saleRetMetalDt :saleRetMetalDts) {
							saleRetMetalDtService.delete(saleRetMetalDt.getId());
						}
						
						List<SaleRetCompDt> saleRetCompDts = saleRetCompDtService.findBySaleRetDt(saleRetDt);
						for(SaleRetCompDt saleRetCompDt :saleRetCompDts) {
							saleRetCompDtService.delete(saleRetCompDt.getId());
						}
						
						
						List<SaleRetLabDt> saleRetLabDts = saleRetLabDtService.findBySaleRetDt(saleRetDt);
						for(SaleRetLabDt saleRetLabDt :saleRetLabDts) {
							saleRetLabDtService.delete(saleRetLabDt.getId());
						}
						
						
						
						//Stock Mt
						
						StockMt stockMt2 = stockMtService.findByRefTranIdAndTranTypeAndDeactive(saleRetDt.getId(), "SALERET", false);
						if(stockMt2 != null) {
							
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
							
							StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk("SALERET", saleRetDt.getId(), true);
							if(stockTran !=null) {
								stockTranRepository.delete(stockTran.getId());
							}
							
						}
						
						
						saleRetDtService.delete(saleRetDt.getId());
					
						
				}
				
			}
			
			//Loose Metal
			List<SaleRetRmMetalDt> saleRetRmMetalDts = saleRetRmMetalDtService.findBySaleRetMt(saleRetMt);
			for (SaleRetRmMetalDt saleRetRmMetalDt : saleRetRmMetalDts) {
				List<MetalTran> metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(saleRetRmMetalDt.getId(), "SaleRetRmMetal", false);
				for (MetalTran metTran : metalTran) {
					metalTranService.delete(metTran.getId());
				}
				
				if(saleRetRmMetalDt.getRefTranDtId() != null) {
					SaleRmMetalDt saleRmMetalDt = saleRmMetalDtService.findOne(saleRetRmMetalDt.getRefTranDtId());
					saleRmMetalDt.setBalance(Math.round((saleRmMetalDt.getBalance() + saleRetRmMetalDt.getMetalWt())*100.0)/100.0);
					saleRmMetalDt.setAdjWt(Math.round((saleRmMetalDt.getAdjWt() - saleRetRmMetalDt.getMetalWt())*100.0)/100.0);
					saleRmMetalDtService.save(saleRmMetalDt);
				}

				saleRetRmMetalDtService.delete(saleRetRmMetalDt.getId());
			}
			
			
			//Loose Component
			List<SaleRetRmCompDt> saleRetRmCompDts = saleRetRmCompDtService.findBySaleRetMt(saleRetMt);
			for (SaleRetRmCompDt saleRetRmCompDt : saleRetRmCompDts) {
				List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(saleRetRmCompDt.getId(), "SaleRetRmComp");
				for (CompTran comTran : compTran) {
					compTranService.delete(comTran.getId());
				}
				
				
				if(saleRetRmCompDt.getRefTranDtId() != null) {
					
					SaleRmCompDt saleRmCompDt = saleRmCompDtService.findOne(saleRetRmCompDt.getRefTranDtId());
					saleRmCompDt.setBalance(saleRmCompDt.getBalance() + saleRetRmCompDt.getMetalWt());
					saleRmCompDt.setAdjWt(saleRmCompDt.getAdjWt() - saleRetRmCompDt.getMetalWt() );
					saleRmCompDtService.save(saleRmCompDt);
					
					}
				
				saleRmCompDtService.delete(saleRetRmCompDt.getId());
			}
			
			
			//Loose Stone
			List<SaleRetRmStnDt> saleRetRmStnDts = saleRetRmStnDtService.findBySaleRetMt(saleRetMt);
			for (SaleRetRmStnDt saleRetRmStnDt : saleRetRmStnDts) {
				StoneTran stoneTran = stoneTranService.RefTranIdAndTranType(saleRetRmStnDt.getId(), "SaleRetRmStn");
				stoneTranService.delete(stoneTran.getId());
				
				if(saleRetRmStnDt.getRefTranDtId() != null) {
					SaleRmStnDt saleRmStnDt = saleRmStnDtService.findOne(saleRetRmStnDt.getRefTranDtId());
					
					saleRmStnDt.setBalCarat(saleRmStnDt.getBalCarat() + saleRetRmStnDt.getCarat());
					saleRmStnDt.setBalStone(saleRmStnDt.getBalStone() + saleRetRmStnDt.getStone());
					saleRmStnDt.setAdjWt(saleRmStnDt.getAdjWt() - saleRetRmStnDt.getCarat());
					saleRmStnDtService.save(saleRmStnDt);
				}

				saleRetRmStnDtService.delete(saleRetRmStnDt.getId());
			}
			
			
		
			delete(saleRetMt.getId());
			
			retVal ="1";
			
			
		}else {
			
			return "Can Not Delete BackDate Entry";
		
		}
		
		return retVal;
	}

	@Override
	public List<SaleRetMt> findByParty(Party party) {
		
		return saleRetMtRepository.findByParty(party);
	}

	@Override
	public String saleRetMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		
		StringBuilder sb = new StringBuilder();
		
		
		Page<SaleRetMt> saleRetMtList = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("saleRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		saleRetMtList = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(saleRetMtList.getTotalElements()).append(",\"rows\": [");

		for (SaleRetMt saleRetMt : saleRetMtList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(saleRetMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(saleRetMt.getId())
			.append("\",\"invNo\":\"")
			.append(saleRetMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(saleRetMt.getInvDateStr())
			.append("\",\"party\":\"")
			.append(saleRetMt.getParty().getName())
			.append("\",\"location\":\"")
			.append(saleRetMt.getLocation().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/marketing/transactions/saleRetMt/edit/")
								.append(saleRetMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteSaleRetMt(event,");					
								sb.append(saleRetMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(saleRetMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/marketing/transactions/saleRetMt/edit/")
					.append(saleRetMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteSaleMt(event,")	
						.append(saleRetMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(saleRetMt.getId())
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
	public String saveSaleRetMt(SaleRetMt saleRetMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result, Integer pPartyIds, Integer pLocationIds,String vTranDate,Double vOtherCharges,Double vFinalPrice,Double pIgst, Double pSgst,Double pCgst) throws ParseException {
		
		
		String action = "added";
		String retVal = "redirect:/marketing/transactions/saleRetMt/add.html";
	
		if (result.hasErrors()) {
			return "/saleRetMt/add";
		}
		
		synchronized (this) {
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				saleRetMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno =getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				saleRetMt.setSrNo(++maxSrno);
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
				
				
				saleRetMt.setInvNo("SRET-" + (bagSr) + "-" + vYear);
				saleRetMt.setCreatedBy(principal.getName());
				saleRetMt.setCreatedDate(new java.util.Date());
				

			} else {
				saleRetMt.setModiBy(principal.getName());
				saleRetMt.setModiDate(new java.util.Date());
				saleRetMt.setOtherCharges(vOtherCharges);
				saleRetMt.setFinalPrice(vFinalPrice);
				saleRetMt.setCgst(pCgst);
				saleRetMt.setSgst(pSgst);
				saleRetMt.setIgst(pIgst);
				
				if(pPartyIds != null) {

					Party party =  partyService.findOne(pPartyIds);
					Department department = departmentService.findOne(pLocationIds);
					
					saleRetMt.setParty(party);
					saleRetMt.setLocation(department);
				}

				
				action = "updated";
				retVal = "redirect:/marketing/transactions/saleRetMt.html";
			}
			

			if(saleRetMt.getHsnMast().getId() == null) {
				saleRetMt.setHsnMast(null);
			}
			
			if(saleRetMt.getModeOfTransport().getId() == null) {
				saleRetMt.setModeOfTransport(null);
			}

			
			save(saleRetMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/marketing/transactions/saleRetMt/edit/"+saleRetMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
		}
	}

	@Override
	public Page<SaleRetMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		
		QSaleRetMt qSaleRetMt = QSaleRetMt.saleRetMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression = qSaleRetMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =qSaleRetMt.invNo.like("%" + search + "%");
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
	
		Page<SaleRetMt> saleRetList =(Page<SaleRetMt>) saleRetMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return saleRetList;
	}

	@Override
	public String saleTransfer(Integer pMtId, String data, Principal principal) {


		String retVal="-1";
		SaleRetMt saleRetMt = findOne(pMtId);
		JSONArray jsonSaleDt = new JSONArray(data);
		
		
		try {
			

			for (int y = 0; y < jsonSaleDt.length(); y++) {
				
				JSONObject dataSaleDt = (JSONObject) jsonSaleDt.get(y);
				
				SaleDt saleDt = saleDtService.findOne((int)(Double.parseDouble(dataSaleDt.get("id").toString())));
				
				SaleRetDt saleRetDt2 = saleRetDtService.findBySaleRetMtAndBarcode(saleRetMt,dataSaleDt.get("barcode").toString());
				if(saleRetDt2 != null) {
					return "Duplicate Barcode";
				}
				
				SaleRetDt saleRetDt = new SaleRetDt();
				saleRetDt.setBarcode(saleDt.getBarcode());
				saleRetDt.setCreatedBy(principal.getName());
				saleRetDt.setCreatedDate(new Date());
				saleRetDt.setDesign(saleDt.getDesign());
				saleRetDt.setGrossWt(saleDt.getGrossWt());
				saleRetDt.setNetWt(saleDt.getNetWt());
				saleRetDt.setSaleRetMt(saleRetMt);
				saleRetDt.setPcs(saleDt.getPcs());
				saleRetDt.setRefSaleDtId(saleDt.getId());
				
				saleRetDt.setMetalValue(saleDt.getMetalValue());
				saleRetDt.setStoneValue(saleDt.getStoneValue());
				saleRetDt.setLabValue(saleDt.getLabValue());
				saleRetDt.setSetValue(saleDt.getSetValue());
				saleRetDt.setCompValue(saleDt.getCompValue());
				saleRetDt.setHandlingValue(saleDt.getHandlingValue());
				saleRetDt.setFinalPrice((double) Math.round(saleDt.getFinalPrice()));
				saleRetDt.setFob((double) Math.round(saleDt.getFob()));
				saleRetDt.setDestination(saleDt.getDestination());
				saleRetDt.setDiscAmount(saleDt.getDiscAmount());
				saleRetDt.setDiscPerc(saleDt.getDiscPerc());
				saleRetDt.setLossValue(saleDt.getLossValue());
				saleRetDt.setLossWt(saleDt.getLossWt());
				saleRetDt.setNetAmount(saleDt.getNetAmount());
				saleRetDt.setOrderRef(saleDt.getOrderRef());
				saleRetDt.setOther(saleDt.getOther());
				saleRetDt.setHallMarking(saleDt.getHallMarking());
				saleRetDt.setLazerMarking(saleDt.getLazerMarking());
				saleRetDt.setEngraving(saleDt.getEngraving());
				saleRetDt.setGrading(saleDt.getGrading());
				
				saleRetDtService.save(saleRetDt);
				
				saleDt.setAdjQty(saleDt.getPcs());
				saleDt.setModiBy(principal.getName());
				saleDt.setModiDate(new Date());
				
				saleDtService.save(saleDt);
				
				List<SaleMetalDt> saleMetalDts = saleMetalDtService.findBySaleDt(saleDt);
				for(SaleMetalDt saleMetalDt :saleMetalDts) {
					
					SaleRetMetalDt saleRetMetalDt = new SaleRetMetalDt();
					saleRetMetalDt.setColor(saleMetalDt.getColor());
					saleRetMetalDt.setCreateDate(new Date());
					saleRetMetalDt.setCreatedBy(principal.getName());
					saleRetMetalDt.setMainMetal(saleMetalDt.getMainMetal());
					saleRetMetalDt.setMetalPcs(saleMetalDt.getMetalPcs());
					saleRetMetalDt.setMetalWeight(saleMetalDt.getMetalWeight());
					saleRetMetalDt.setSaleRetDt(saleRetDt);
					saleRetMetalDt.setSaleRetMt(saleRetMt);
					saleRetMetalDt.setPartNm(saleMetalDt.getPartNm());
					saleRetMetalDt.setPurity(saleMetalDt.getPurity());
					saleRetMetalDt.setPurityConv(saleMetalDt.getPurityConv());
					saleRetMetalDt.setMetalValue(saleMetalDt.getMetalValue());
					saleRetMetalDt.setMetalRate(Math.round((saleRetDt.getMetalValue() / saleRetDt.getNetWt())*1000.0)/1000.0);
					saleRetMetalDt.setPerGramRate(saleMetalDt.getPerGramRate());
					saleRetMetalDt.setPerPcRate(saleMetalDt.getPerPcRate());
					
					
					saleRetMetalDtService.save(saleRetMetalDt);
					
				}
				
				List<SaleStnDt> saleStnDts = saleStnDtService.findBySaleDt(saleDt);
				for(SaleStnDt saleStnDt : saleStnDts) {
					
					SaleRetStnDt saleRetStnDt =new SaleRetStnDt();
					saleRetStnDt.setCarat(saleStnDt.getCarat());
					saleRetStnDt.setCreatedBy(principal.getName());
					saleRetStnDt.setCreatedDate(new Date());
					saleRetStnDt.setSaleRetDt(saleRetDt);
					saleRetStnDt.setSaleRetMt(saleRetMt);
					saleRetStnDt.setPartNm(saleStnDt.getPartNm());
					saleRetStnDt.setQuality(saleStnDt.getQuality());
					saleRetStnDt.setSetting(saleStnDt.getSetting());
					saleRetStnDt.setSettingType(saleStnDt.getSettingType());
					saleRetStnDt.setShape(saleStnDt.getShape());
					saleRetStnDt.setSieve(saleStnDt.getSieve());
					saleRetStnDt.setSize(saleStnDt.getSize());
					saleRetStnDt.setSizeGroup(saleStnDt.getSizeGroup());
					saleRetStnDt.setStone(saleStnDt.getStone());
					saleRetStnDt.setStoneType(saleStnDt.getStoneType());
					saleRetStnDt.setSubShape(saleStnDt.getSubShape());
					saleRetStnDt.setCenterStone(saleStnDt.getCenterStone());
					saleRetStnDt.setStoneRate(saleStnDt.getStoneRate());
					saleRetStnDt.setStoneValue(saleStnDt.getStoneValue());
					saleRetStnDt.setHandlingRate(saleStnDt.getHandlingRate());
					saleRetStnDt.setHandlingValue(saleStnDt.getHandlingValue());
					saleRetStnDt.setHdlgPerCarat(saleStnDt.getHdlgPerCarat());
					saleRetStnDt.setHdlgPercentWise(saleStnDt.getHdlgPercentWise());
					saleRetStnDt.setrLock(saleStnDt.getrLock());
					saleRetStnDt.setSetRate(saleStnDt.getSetRate());
					saleRetStnDt.setSetValue(saleStnDt.getSetValue());
					saleRetStnDt.setPerPcsRateFlg(saleStnDt.getPerPcsRateFlg());
					saleRetStnDtService.save(saleRetStnDt);
					
					
				}
				
				
				List<SaleCompDt> saleCompDts = saleCompDtService.findBySaleDt(saleDt);
				for(SaleCompDt saleCompDt :saleCompDts) {
					
					SaleRetCompDt saleRetCompDt = new SaleRetCompDt();
					saleRetCompDt.setColor(saleCompDt.getColor());
					saleRetCompDt.setComponent(saleCompDt.getComponent());
					saleRetCompDt.setCompQty(saleCompDt.getCompQty());
					saleRetCompDt.setCreatedBy(principal.getName());
					saleRetCompDt.setCreatedDate(new Date());
					saleRetCompDt.setSaleRetMt(saleRetMt);
					saleRetCompDt.setSaleRetDt(saleRetDt);
					saleRetCompDt.setPurity(saleCompDt.getPurity());
					saleRetCompDt.setPurityConv(saleCompDt.getPurityConv());
					saleRetCompDt.setCompWt(saleCompDt.getCompWt());
					saleRetCompDt.setCompValue(saleCompDt.getCompValue());
			//		saleRetCompDt.setLossPerc(saleCompDt.getLossPerc());
					saleRetCompDt.setMetalRate(saleCompDt.getMetalRate());
					saleRetCompDt.setMetalValue(saleCompDt.getMetalValue());
			//		saleRetCompDt.setPerGramMetalRate(saleCompDt.getPerGramMetalRate());
					saleRetCompDt.setPerGramRate(saleCompDt.getPerGramRate());
				
					saleRetCompDtService.save(saleRetCompDt);
					
					
					
				}
				
				List<SaleLabDt> saleLabDts = saleLabDtService.findBySaleDt(saleDt);
				for (SaleLabDt saleLabDt : saleLabDts) {
					SaleRetLabDt saleRetLabDt = new SaleRetLabDt();
					saleRetLabDt.setLabourRate(saleLabDt.getLabourRate());
					saleRetLabDt.setSaleRetDt(saleRetDt);
					saleRetLabDt.setSaleRetMt(saleRetMt);
					saleRetLabDt.setLabourType(saleLabDt.getLabourType());
					saleRetLabDt.setLabourValue(saleLabDt.getLabourValue());
					saleRetLabDt.setPerCaratRate(saleLabDt.getPerCaratRate());
					saleRetLabDt.setPercentage(saleLabDt.getPercentage());
					saleRetLabDt.setPerPcRate(saleLabDt.getPerPcRate());
					saleRetLabDt.setPerGramRate(saleLabDt.getPerGramRate());
					saleRetLabDt.setrLock(saleLabDt.getrLock());
					saleRetLabDt.setMetal(saleLabDt.getMetal());
					saleRetLabDtService.save(saleRetLabDt);
					
					
				}
				
			
				StockTran stockTran = stockTranRepository.findByTranTypeAndRefTranIdAndCurrStk("SALEISS",saleDt.getId(),true);
				StockTran stockTran2 = new StockTran();
				stockTran2.setBarcode(saleRetDt.getBarcode());
				stockTran2.setCreatedBy(principal.getName());
				stockTran2.setCreatedDate(new Date());
				stockTran2.setCurrStatus("SALERET");
				stockTran2.setCurrStk(true);
				stockTran2.setLocation(stockTran.getLocation());
				stockTran2.setRefStkTranId(stockTran.getId());
				stockTran2.setRefTranId(saleRetDt.getId());
				stockTran2.setTranDate(saleRetMt.getInvDate());
				stockTran2.setTranType("SALERET");
				stockTran2.setParty(saleRetMt.getParty());
				stockTran2.setStockMt(stockTran.getStockMt());
				stockTranService.save(stockTran2);
				
				stockTran.setCurrStk(false);
				stockTran.setIssueDate(saleRetMt.getInvDate());
				stockTranService.save(stockTran);
				
			//	StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(saleRetDt.getBarcode(),  false, false);
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

	@Override
	public String addSaleRetSummaryDetails(Integer mtId, Double fob, Double sgst, Double cgst, Double igst,
			Double otherCharges, Double finalPrice, Principal principal) {
		
		String retVal="-1";
		SaleRetMt saleRetMt = findOne(mtId);
		
		try {
			
			saleRetMt.setCgst(cgst);
			saleRetMt.setSgst(sgst);
			saleRetMt.setIgst(igst);
			saleRetMt.setOtherCharges(otherCharges);
			saleRetMt.setFinalPrice(finalPrice);
			
			save(saleRetMt);
			
			retVal="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	  
	 

}

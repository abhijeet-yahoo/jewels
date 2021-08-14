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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QStkTrfMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStkTrfMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class StkTrfMtService implements IStkTrfMtService{
	
	@Autowired
	private IStkTrfMtRepository stkTrfMtRepository;

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
	private IStockMtService stockMtService;
	
	
	@Autowired
	private IStkTrfCompDtService stkTrfCompDtService;
	
	@Autowired
	private IStkTrfStnDtService stkTrfStnDtService;
	
	@Autowired
	private IStkTrfMetalDtService stkTrfMetalDtService;
	
	@Autowired
	private IStockTranService stockTranService;
	
	@Autowired
	private IStkTrfLabDtService stkTrfLabDtService;
	
	@Autowired
	private IStkTrfDtService stkTrfDtService;
	
	@Autowired
	private IDepartmentService departmentService;

	
	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		
		QStkTrfMt qStkTrfMt = QStkTrfMt.stkTrfMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStkTrfMt)
			.where(qStkTrfMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qStkTrfMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public void save(StkTrfMt stkTrfMt) {
		// TODO Auto-generated method stub
		stkTrfMtRepository.save(stkTrfMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		 stkTrfMtRepository.delete(id);
		
	}

	@Override
	public StkTrfMt findOne(int id) {
		// TODO Auto-generated method stub
		return stkTrfMtRepository.findOne(id);
	}

	@Override
	public String stkTrfMtTransfer(Integer mtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteStkTrfMt(Integer mtId,String tranType) {
		// TODO Auto-generated method stub
		String retVal="-1";
		
		StkTrfMt stkTrfMt = findOne(mtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = stkTrfMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
			
			List<StkTrfDt> stkTrfDts = stkTrfDtService.findByStkTrfMt(stkTrfMt);
			
			for (StkTrfDt stkTrfDt : stkTrfDts) {
				
				if(stkTrfDt.getAdjQty()>0) {
					
					return "Can not Delete, Qty Adjusted";
					
				}else {
					
					List<StkTrfStnDt> stkTrfStnDts= stkTrfStnDtService.findByStkTrfDt(stkTrfDt);
					for(StkTrfStnDt stkTrfStnDt :stkTrfStnDts) {
						stkTrfStnDtService.delete(stkTrfStnDt.getId());
						
					}
					
					List<StkTrfMetalDt> stkTrfMetalDts = stkTrfMetalDtService.findByStkTrfDt(stkTrfDt);
					for(StkTrfMetalDt stkTrfMetalDt :stkTrfMetalDts) {
						
						stkTrfMetalDtService.delete(stkTrfMetalDt.getId());
						
					}
					
					List<StkTrfCompDt> stkTrfCompDts = stkTrfCompDtService.findByStkTrfDt(stkTrfDt);
					for(StkTrfCompDt stkTrfCompDt : stkTrfCompDts) {
						stkTrfCompDtService.delete(stkTrfCompDt.getId());
						
					}
					
					
					List<StkTrfLabDt> stkTrfLabDts = stkTrfLabDtService.findByStkTrfDt(stkTrfDt);
					for(StkTrfLabDt stkTrfLabDt :stkTrfLabDts) {
						stkTrfLabDtService.delete(stkTrfLabDt.getId());
						
					}
					
					
					
					StockTran stockTran = stockTranService.findByTranTypeAndRefTranIdAndCurrStk(tranType, stkTrfDt.getId(), true);
					if(stockTran !=null) {
						
						StockTran stockTran2 = stockTranService.findOne(stockTran.getRefStkTranId());
						stockTran2.setCurrStk(true);
						stockTranService.save(stockTran2);
						
						stockTranService.delete(stockTran.getId());
						
						 StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());	
						 stockMt.setLocation(stockTran2.getLocation());
						 stockMt.setCurrStk(true);
						 stockMtService.save(stockMt);
					}
					
					
				//	StockMt stockMt = stockMtService.findByBarcodeAndCurrStkAndDeactive(stkTrfDt.getBarcode(), false, false);
					
			
					
					
					stkTrfDtService.delete(stkTrfDt.getId());
					
				}
				
			}
	
			delete(stkTrfMt.getId());
			
			retVal ="1";
			
			
			
			}else {
				
				return "Can Not Delete BackDate Entry";
			
			}
		
		return retVal;
	}

	@Override
	public List<StkTrfMt> findByParty(Party party) {
		// TODO Auto-generated method stub
		return stkTrfMtRepository.findByParty(party);
	}

	@Override
	public String stkTrfMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal, String tranType) throws ParseException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		
		Page<StkTrfMt> stkTrfMtList = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		
		MenuMast menuMast = null;
		if(tranType.equalsIgnoreCase("STOCKTRF")) {
			 menuMast = menuMastService.findByMenuNm("stkTrfMt");
		}else {
			 menuMast = menuMastService.findByMenuNm("branchStkTrfMt");
		}
		
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		stkTrfMtList = searchAll(limit, offset, sort, order, search, true,tranType);

		sb.append("{\"total\":").append(stkTrfMtList.getTotalElements()).append(",\"rows\": [");

		for (StkTrfMt stkTrfMt : stkTrfMtList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(stkTrfMt.getInvDate()));

			sb.append("{\"id\":\"").append(stkTrfMt.getId())
			.append("\",\"invNo\":\"")
			.append(stkTrfMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(stkTrfMt.getInvDateStr())
			.append("\",\"party\":\"")
			.append(stkTrfMt.getParty() != null ? stkTrfMt.getParty().getName() :"")
			.append("\",\"location\":\"")
			.append(stkTrfMt.getLocation() != null ? stkTrfMt.getLocation().getName() :"")
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
						
						if(tranType.equalsIgnoreCase("STOCKTRF")) {
							
							sb.append("<a href='/jewels/marketing/transactions/stkTrfMt/edit/")
							.append(stkTrfMt.getId()).append(".html'");
	
					sb.append(
							".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
						}else {
							
							sb.append("<a href='/jewels/marketing/transactions/branchStkTrfMt/edit/")
							.append(stkTrfMt.getId()).append(".html'");
	
					sb.append(
							".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
						}
		
						
						sb.append("\",\"action2\":\"");
						
						sb.append("<a href='javascript:deleteStkTrfMt(event,");					
								sb.append(stkTrfMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(stkTrfMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")

						.append("\"},");
						
			} else {
				
				if(tranType.equalsIgnoreCase("STOCKTRF")) {
					
					if (roleRights.getCanEdit()) {

						sb.append("<a href='/jewels/marketing/transactions/stkTrfMt/edit/")
						.append(stkTrfMt.getId()).append(".html'");
			

					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(
							".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				
					
				}else {
					
					if (roleRights.getCanEdit()) {

						sb.append("<a href='/jewels/marketing/transactions/branchStkTrfMt/edit/")
						.append(stkTrfMt.getId()).append(".html'");
			

					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(
							".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				}
				
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteStkTrfMt(event,")	
						.append(stkTrfMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(stkTrfMt.getId())
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
	public String saveStkTrfMt(StkTrfMt stkTrfMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result,Integer pLocationIds,Integer pToLocationIds,String vTranDate) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/marketing/transactions/stkTrfMt/add.html";
	
		if (result.hasErrors()) {
			return "stkTrfMt/add";
		}
		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				stkTrfMt.setInvDate(dates);
				
				}
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno =getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				stkTrfMt.setSrNo(++maxSrno);
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
				
				
				stkTrfMt.setInvNo("IT/" + (bagSr) + "/"  + presentYear+"-"+nextYear);
				stkTrfMt.setCreatedBy(principal.getName());
				stkTrfMt.setCreatedDate(new java.util.Date());
				

			} else {
				stkTrfMt.setModiBy(principal.getName());
				stkTrfMt.setModiDate(new java.util.Date());
				
				if(pLocationIds != null) {
					Department department = departmentService.findOne(pLocationIds);
					Department toDepartment = departmentService.findOne(pToLocationIds);
				
					stkTrfMt.setToLocation(toDepartment);
					stkTrfMt.setLocation(department);
			
				}
				
				
				action = "updated";
				retVal = "redirect:/marketing/transactions/stkTrfMt.html";
			}
			
			if(stkTrfMt.getHsnMast() != null) {
			if(stkTrfMt.getHsnMast().getId() == null) {
				stkTrfMt.setHsnMast(null);
			}
			}
			
			stkTrfMt.setTranType("STOCKTRF"); 
			save(stkTrfMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/marketing/transactions/stkTrfMt/edit/"+stkTrfMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
		}
	}

	@Override
	public String getDtItemSummary(Integer mtId) {
		// TODO Auto-generated method stub
	
		StkTrfMt stkTrfMt = findOne(mtId);
		
		List<StkTrfDt> stkTrfDts = stkTrfDtService.findByStkTrfMt(stkTrfMt);
		
		JSONObject jsonObject = new JSONObject();
		
		Double totPcs = 0.0;
		Double grossWt = 0.0;
		Double netWt = 0.0;
	//	Double pureWt = 0.0;
		Integer diaStone =0;
		Double diaCarat = 0.0;
		Integer colStone = 0;
		Double colCarat = 0.0;
			
			for (StkTrfDt stkTrfDt : stkTrfDts) {
				
				totPcs += stkTrfDt.getPcs();
				grossWt += stkTrfDt.getGrossWt();
				netWt += stkTrfDt.getNetWt();
				
				
				List<StkTrfStnDt> stkTrfStnDts = stkTrfStnDtService.findByStkTrfDt(stkTrfDt);
				for (StkTrfStnDt stkTrfStnDt : stkTrfStnDts) {
					
					if(stkTrfStnDt.getStoneType().getName().equalsIgnoreCase("Diamond")) {
						diaStone += stkTrfStnDt.getStone();
						diaCarat += stkTrfStnDt.getCarat();
					}else {
						colStone += stkTrfStnDt.getStone();
						colCarat += stkTrfStnDt .getCarat();
						
					}
				}
				
			}
			
			jsonObject.put("totPcs", totPcs);
			jsonObject.put("grossWt", Math.round((grossWt)*1000.0)/1000.0);
			jsonObject.put("netWt",  Math.round((netWt)*1000.0)/1000.0);
		//	jsonObject.put("pureWt", Math.round((pureWt)*1000.0)/1000.0);
			jsonObject.put("diaStone", diaStone);
			jsonObject.put("diaCarat",  Math.round((diaCarat)*1000.0)/1000.0);
			jsonObject.put("colStone", colStone);
			jsonObject.put("colCarat", Math.round((colCarat)*1000.0)/1000.0);
			
			
			
		
	return jsonObject.toString();
	}

	@Override
	public Page<StkTrfMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive, String tranType) {
		// TODO Auto-generated method stub
		QStkTrfMt qStkTrfMt = QStkTrfMt.stkTrfMt;
		BooleanExpression expression=qStkTrfMt.tranType.eq(tranType);
		if (onlyActive) {
			if (search != null) {
				expression = qStkTrfMt.tranType.eq(tranType).and(qStkTrfMt.invNo.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression =qStkTrfMt.invNo.like("%" + search + "%");
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
	
		Page<StkTrfMt> stkTrfList =(Page<StkTrfMt>) stkTrfMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return stkTrfList;
	}

	@Override
	public String saveBranchStkTrfMt(StkTrfMt stkTrfMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result, Integer pLocationIds, Integer pToLocationIds,String vTranDate,Double vOtherCharges,Double vFinalPrice,
			Double pIgst, Double pSgst,Double pCgst) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/marketing/transactions/branchStkTrfMt/add.html";
	
		if (result.hasErrors()) {
			return "branchStkTrfMt/add";
		}
		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				stkTrfMt.setInvDate(dates);
				
				}
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno =getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				stkTrfMt.setSrNo(++maxSrno);
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
				
				
				stkTrfMt.setInvNo("BT/" + (bagSr) + "/"  + presentYear+"-"+nextYear);
				stkTrfMt.setCreatedBy(principal.getName());
				stkTrfMt.setCreatedDate(new java.util.Date());
				

			} else {
				stkTrfMt.setModiBy(principal.getName());
				stkTrfMt.setModiDate(new java.util.Date());
				
				Department department = departmentService.findOne(pLocationIds);
				Department toDepartment = departmentService.findOne(pToLocationIds);
			
				stkTrfMt.setToLocation(toDepartment);
				stkTrfMt.setLocation(department);
				
				stkTrfMt.setCgst(pCgst);
				stkTrfMt.setSgst(pSgst);
				stkTrfMt.setIgst(pIgst);
		
				action = "updated";
				retVal = "redirect:/marketing/transactions/branchStkTrfMt.html";
			}
			
			
			if(stkTrfMt.getHsnMast().getId() == null) {
				stkTrfMt.setHsnMast(null);
			}
			
			if(stkTrfMt.getModeOfTransport().getId() == null) {
				stkTrfMt.setModeOfTransport(null);
			}
			
			stkTrfMt.setOtherCharges(vOtherCharges);;
			stkTrfMt.setFinalPrice(vFinalPrice);
			stkTrfMt.setTranType("BRANCHTRF"); 
			save(stkTrfMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/marketing/transactions/branchStkTrfMt/edit/"+stkTrfMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
		}
	}

	@Override
	public String getBranchWiseGst(Integer locationId,Integer toLocationId) {
		// TODO Auto-generated method stub
		String retVal = "-1";

			Department department = departmentService.findOne(locationId);
			Department toDepartment = departmentService.findOne(toLocationId);
			
			String str = department.getBranchMaster().getGst();
			String toLocationStr = toDepartment.getBranchMaster().getGst();
			
			
			if(str != null && toLocationStr !=null) {
				
				String gstNo1 = str.substring(0,2);
				String gstNo2 = toLocationStr.substring(0,2);
				if(gstNo1.equalsIgnoreCase(gstNo2)) {
					retVal = "1";
				}else {
					retVal = "2";
				}
			}else {
				retVal = "2";
			}
			
			
	
		return retVal;
	}
	
	
	@Override
	public String addSummaryDetails(Integer mtId, Double fob, Double sgst, Double cgst, Double igst,Double otherCharges,Double finalPrice,
			Principal principal) {
		// TODO Auto-generated method stub
		String retVal="-1";
		StkTrfMt stkTrfMt = findOne(mtId);
	
			
			stkTrfMt.setCgst(cgst);
			stkTrfMt.setSgst(sgst);
			stkTrfMt.setIgst(igst);
			stkTrfMt.setOtherCharges(otherCharges);
			stkTrfMt.setFinalPrice(Math.round((finalPrice)*100.0)/100.0);
			
			save(stkTrfMt);
			
			retVal="1";
			
		
		return retVal;
	}

	@Override
	public List<StkTrfMt> findByToLocationAndTranType(Department toLocation,String tranType) {
		// TODO Auto-generated method stub
		return stkTrfMtRepository.findByToLocationAndTranType(toLocation, tranType);
	}

	@Override
	public String getStkTrfPendingList(Integer locationId, String tranType) throws ParseException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
			
			List<Object[]> objects =new ArrayList<Object[]>();
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_branchPendingApprovalLIst(?,?) }");
			query.setParameter(1, locationId);
			query.setParameter(2, tranType);
			objects = query.getResultList();
			
			String str="";
			 StringBuilder sb = new StringBuilder();
			 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
				
			 for(Object[] list:objects){
				 
				 String	invDate="";
				 
				 if(list[1] != null ){
					 Date dispatchDt = dfOutput.parse((list[1] != null ? list[1] : "").toString());
					 invDate = dfInput.format(dispatchDt);
				 }
					
					sb.append("{\"invNo\":\"")
				     .append(list[0] != null ? list[0] : "")
				     .append("\",\"invDate\":\"")
				     .append(invDate)
				     .append("\",\"mtId\":\"")
				     .append(list[2] != null ? list[2] : "")
				     .append("\",\"dtId\":\"")
				     .append(list[3] != null ? list[3] : "")
				     .append("\",\"barcode\":\"")
				     .append(list[4] != null ? list[4] : "")
				     .append("\",\"pcs\":\"")
					 .append(list[5] != null ? list[5] : "")
				     .append("\",\"grossWt\":\"")
					 .append(list[6] != null ? list[6] : "")
				     .append("\",\"netWt\":\"")
					 .append(list[7] != null ? list[7] : "")
					 .append("\",\"metalValue\":\"")
					 .append(list[8] != null ? list[8] : "")
					 .append("\",\"stoneValue\":\"")
					 .append(list[9] != null ? list[9] : "")
					 .append("\",\"compValue\":\"")
					 .append(list[10] != null ? list[10] : "")
					 .append("\",\"labValue\":\"")
					 .append(list[11] != null ? list[11] : "")
					 .append("\",\"setValue\":\"")
					 .append(list[12] != null ? list[12] : "")
					 .append("\",\"handlingValue\":\"")
					 .append(list[13] != null ? list[13] : "")
		             .append("\",\"fob\":\"")
					 .append(list[14] != null ? list[14] : "")
					 .append("\",\"other\":\"")
					 .append(list[15] != null ? list[15] : "")
					 .append("\",\"discAmount\":\"")
					 .append(list[16] != null ? list[16] : "")
					 .append("\",\"finalPrice\":\"")
					 .append(list[17] != null ? list[17] : "")
					 .append("\",\"mainStyleNo\":\"")
					 .append(list[18] != null ? list[18] : "")
					 .append("\",\"ktCol\":\"")
					 .append(list[19] != null ? list[19] : "")
					 .append("\"},");
				}
			   
				str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
						: str);
				str += "]}";
				
				
			return str;
			
	
	}

	@Override
	public String savePendingApproval(String dtIdList,Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal = "-1";
		
	
			String[] dtId = dtIdList.split(",");
			for (int i = 0; i < dtId.length; i++) {
				
				StkTrfDt stkTrfDt = stkTrfDtService.findOne(Integer.parseInt(dtId[i]));
				stkTrfDt.setPendApprovalFlg(false);
				stkTrfDtService.save(stkTrfDt);
				
				StockTran stockTran = stockTranService.findByBarcodeAndCurrStk(stkTrfDt.getBarcode(), true);
				StockTran stockTran2 = new StockTran();
				stockTran2.setBarcode(stockTran.getBarcode());
				stockTran2.setCreatedBy(principal.getName());
				stockTran2.setCreatedDate(new Date());
				stockTran2.setCurrStatus(stockTran.getLocation().getName());
				stockTran2.setCurrStk(true);
				stockTran2.setLocation(stockTran.getLocation());
				stockTran2.setRefStkTranId(stockTran.getId());
				stockTran2.setRefTranId(stockTran.getRefTranId());
				stockTran2.setTranDate(new Date());
				stockTran2.setTranType(stockTran.getTranType());
				stockTran2.setStockMt(stockTran.getStockMt());
				stockTranService.save(stockTran2);
				
				
				stockTran.setCurrStk(false);
				stockTran.setIssueDate(new Date());
				stockTranService.save(stockTran);
				
				//Stock Currstk true code
				StockMt stockMt = stockMtService.findOne(stockTran.getStockMt().getMtId());
				stockMt.setCurrStk(true);
				stockMt.setModiBy(principal.getName());
				stockMt.setModiDt(new Date());
				stockMtService.save(stockMt);
				
				retVal = "1";
			}
			
		
		
	
		return retVal;
	}

}

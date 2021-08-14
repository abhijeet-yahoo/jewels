package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ExchangeRateMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QExchangeRateMaster;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IExchangeRateMastRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICountryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IExchangeRateService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ExchangeRateService implements IExchangeRateService {

	@Autowired
	private IExchangeRateMastRepository exchangeRateMastRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ICountryService countryService;
	
	@Override
	public ExchangeRateMaster findOne(int id) {
		// TODO Auto-generated method stub
		return exchangeRateMastRepository.findOne(id);
	}

	@Override
	public List<ExchangeRateMaster> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ExchangeRateMaster exchangeRateMaster) {
		exchangeRateMastRepository.save(exchangeRateMaster);
		
	}

	@Override
	public void delete(int id) {
		ExchangeRateMaster exchangeRate = exchangeRateMastRepository.findOne(id);
		exchangeRate.setDeactive(true);
		exchangeRate.setDeactiveDt(new java.util.Date());
		exchangeRateMastRepository.save(exchangeRate);
		
	}

	@Override
	public String addExchngRateMaster(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("exchangeRate");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("countryMap",countryService.getCountryWiseCurrencyList());
			
			return "exchangeRate/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "exchangeRate/add";
	}
	
	

	@Override
	public Page<ExchangeRateMaster> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		QExchangeRateMaster qExchangeRateMaster = QExchangeRateMaster.exchangeRateMaster;
		BooleanExpression expression = qExchangeRateMaster.deactive.eq(false);
		if (onlyActive) {
			if (search == null) {
				expression = qExchangeRateMaster.deactive.eq(false);
			} else {
				expression = qExchangeRateMaster.deactive.eq(false).and(qExchangeRateMaster.countryMaster.name.like("%" + search + "%"));
			}
		} else {
			if (search != null) {
				expression = qExchangeRateMaster.countryMaster.name.like("%" + search + "%");
			}
		}

		if (limit == null) {
			limit = 1000;
		}
		if (offset == null) {
			offset = 0;
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("code")) {
			sort = "code";
		} 
		Page<ExchangeRateMaster> exchangeRateMastList = (Page<ExchangeRateMaster>) exchangeRateMastRepository.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		
		return exchangeRateMastList;
	}




	@Override
	public String exchngRtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) {
	
		
		StringBuilder sb = new StringBuilder();
		Page<ExchangeRateMaster> exchangeRates = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("exchangeRate");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		exchangeRates = searchAll(limit, offset, sort, order, search, false);


		sb.append("{\"total\":").append(exchangeRates.getTotalElements()).append(",\"rows\": [");

		for (ExchangeRateMaster exchangeRate : exchangeRates) {
			
			
			sb.append("{\"id\":\"")
					.append(exchangeRate.getId())
					.append("\",\"fromDate\":\"")
					.append(exchangeRate.getFromDateStr()!=null?exchangeRate.getFromDateStr():"")
					.append("\",\"toDate\":\"")
					.append(exchangeRate.getToDateStr()!=null?exchangeRate.getToDateStr():"")
					.append("\",\"country\":\"") 
				    .append(exchangeRate.getCountryMaster()!=null ? exchangeRate.getCountryMaster().getName():"")
				    .append("\",\"currencyNm\":\"")
					.append(exchangeRate.getCountryMaster()!=null?exchangeRate.getCountryMaster().getCurrencyNm():"")
					 .append("\",\"currencySymbol\":\"")
						.append(exchangeRate.getCountryMaster()!=null?exchangeRate.getCountryMaster().getCurrencySymbol():"");
					
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/exchangeRate/edit/")
						.append(exchangeRate.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/exchangeRate/delete/")
							.append(exchangeRate.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(exchangeRate.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
	
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/exchangeRate/edit/")
						.append(exchangeRate.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/exchangeRate/delete/")
							.append(exchangeRate.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(exchangeRate.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
	
			}
			
		}		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println(str);
		
		return str;
	}

	@Override
	public String exchangeRateMasterSave(ExchangeRateMaster exchangeRate, Integer id,
			RedirectAttributes redirectAttributes, Principal principal,BindingResult result) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/manufacturing/masters/exchangeRate/add.html";

		if (result.hasErrors()) {
			return "exchangeRate/add";
		}
		
		/*
		 * retVal = checkDuplicate(exchangeRate, id);
		 * 
		 * if(retVal == "true"){ return "-1"; }
		 */
		if (id == null || id.equals(" ") || (id != null && id == 0)) {
			
			SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
			Date fromDate = dfInput.parse(exchangeRate.getFromDateStr());
			String frmDates = dfOutput.format(fromDate);
			
			Date toDate = dfInput.parse(exchangeRate.getToDateStr());
			String toDates = dfOutput.format(toDate);
			
			
			exchangeRate.setCreatedBy(principal.getName());
			exchangeRate.setCreatedDate(new java.util.Date());
		} else {
			exchangeRate.setId(id);
			exchangeRate.setModiBy(principal.getName());
			exchangeRate.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/exchangeRate.html";
		}

		save(exchangeRate);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@Override
	public String checkDuplicate(ExchangeRateMaster exchangeRateMaster, Integer id) {
		// TODO Auto-generated method stub
		String retVal="false";
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
		
		List<ExchangeRateMaster> exchangeRateMasters=null;
		
			
			@SuppressWarnings("unchecked")
			TypedQuery<ExchangeRateMaster> query =  (TypedQuery<ExchangeRateMaster>)entityManager.createNativeQuery("select * from exchangeratemast"
							+ " where countryid='"+exchangeRateMaster.getCountryMaster().getId()+"' '"
									+ "and FromDate ='"+simpleDateFormat.format(exchangeRateMaster.getFromDate())+"' and deactive =0 ",ExchangeRateMaster.class);
			
			exchangeRateMasters = query.getResultList();
		
			
			
			if(exchangeRateMasters.size() > 0) {
				if(id == null || id.equals("") || (id != null && id == 0)){
					
					retVal="true";
					
				}else {

					if(exchangeRateMasters.get(0).getId().equals(id)){
						retVal="false";
					}else{
						retVal="true";
					}
				}
			}else {
				@SuppressWarnings("unchecked")
				TypedQuery<ExchangeRateMaster> query1 =  (TypedQuery<ExchangeRateMaster>)entityManager.createNativeQuery("select * from exchangeratemast"
						+ " where countryid='"+exchangeRateMaster.getCountryMaster().getId()+exchangeRateMaster.getToDate()+"' between fromdate and todate and deactive =0 ",ExchangeRateMaster.class);
				List<ExchangeRateMaster> exchangeRateMasters2 = query1.getResultList();
				if(exchangeRateMasters2.size()>0){
					if(id == null || id.equals("") || (id != null && id == 0)){
						
						retVal="true";
						
					}else{
					
						if(exchangeRateMasters2.get(0).getId().equals(id)){
							retVal="false";
						}else{
							retVal="true";
						}
						
					}	
					
				}
			}
			
			
			return retVal;
	}

	
	
}

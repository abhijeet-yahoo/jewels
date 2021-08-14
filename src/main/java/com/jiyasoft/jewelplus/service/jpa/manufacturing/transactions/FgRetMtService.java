package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QFgRetMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IFgRetMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgRetMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class FgRetMtService implements IFgRetMtService {
	
	@Autowired
	private IFgRetMtRepository fgRetMtRepository;
	
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
	private IDepartmentService departmentService;
	
	@Autowired
	private IFgRetDtService fgRetDtService;

	@Override
	public void save(FgRetMt fgRetMt) {
		// TODO Auto-generated method stub
		fgRetMtRepository.save(fgRetMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
		fgRetMtRepository.delete(id);
	}

	@Override
	public FgRetMt findOne(int id) {
		// TODO Auto-generated method stub
		return fgRetMtRepository.findOne(id);
	}

	@Override
	public Page<FgRetMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		
		QFgRetMt qFgRetMt = QFgRetMt.fgRetMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression = qFgRetMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =qFgRetMt.invNo.like("%" + search + "%");
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
	
		Page<FgRetMt> fgRetMtList =(Page<FgRetMt>) fgRetMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
			
		return fgRetMtList;
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		
		QFgRetMt qFgRetMt = QFgRetMt.fgRetMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qFgRetMt)
			.where(qFgRetMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qFgRetMt.invSrNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}
		
		return retVal;
	}

	@Override
	public String saveFgRetMt(FgRetMt fgRetMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,
			BindingResult result, String vTranDate,Integer pLocationToIds,Integer pLocationIds) throws ParseException {
		// TODO Auto-generated method stub
		
		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/fgRetMt/add.html";
	
	
		
	
		if (result.hasErrors()) {
			return "fgRetMt/add";
		}

		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				fgRetMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				fgRetMt.setInvSrNo(++maxSrno);
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
				
				
				fgRetMt.setInvNo("FGRET/" + (bagSr) + "/" + presentYear+"-"+nextYear);
				fgRetMt.setCreatedBy(principal.getName());
				fgRetMt.setCreatedDt(new java.util.Date());
				

			} else {
				
				if(pLocationToIds != null) {
					
					Department department = departmentService.findOne(pLocationIds);
					fgRetMt.setLocation(department);
				
					Department department2 = departmentService.findOne(pLocationToIds);
					fgRetMt.setToLocation(department2);
				}
				
				fgRetMt.setModiBy(principal.getName());
				fgRetMt.setModiDt(new java.util.Date());
				
				action = "updated";
				retVal = "redirect:/manufacturing/transactions/fgRetMt.html";
			}
			
		
			
			
			save(fgRetMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/manufacturing/transactions/fgRetMt/edit/"+fgRetMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
	}
	}

	@Override
	public String fgRetMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		
		Page<FgRetMt> fgRetList=null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("fgRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		fgRetList = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(fgRetList.getTotalElements()).append(",\"rows\": [");

		for (FgRetMt fgRetMt : fgRetList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(fgRetMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(fgRetMt.getId())
			.append("\",\"invNo\":\"")
			.append(fgRetMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(fgRetMt.getInvDateStr())
			.append("\",\"location\":\"")
			.append(fgRetMt.getLocation().getName())
			.append("\",\"toLocation\":\"")
			.append(fgRetMt.getToLocation().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/fgRetMt/edit/")
								.append(fgRetMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteFgRetMt(event,");					
								sb.append(fgRetMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(fgRetMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/fgRetMt/edit/")
					.append(fgRetMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteFgRetMt(event,")	
						.append(fgRetMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(fgRetMt.getId())
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
	public String fgRetMtDelete(Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal = "-1";
		try {
			
			FgRetMt fgRetMt = findOne(mtId);
			List<FgRetDt> fgRetDts = fgRetDtService.findByFgRetMt(fgRetMt);
			for (FgRetDt fgRetDt : fgRetDts) {
				retVal=	fgRetDtService.fgRetDtDelete(fgRetDt.getId(), principal);
			}
			
			if(retVal != "1") {
				return retVal;
			}
			delete(mtId);
			
			retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}

}

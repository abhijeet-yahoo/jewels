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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QReadyBagIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IReadyBagIssMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ReadyBagIssMtService implements IReadyBagIssMtService {
	
	@Autowired
	private IReadyBagIssMtRepository readyBagIssMtRepository;
	
	@Autowired
	private UserService userService;
	  
	@Autowired
	private UserRoleService userRoleService;
	  
	@Autowired
	private MenuMastService menuMastService;
	  
	@Autowired
	private RoleRightsService roleRightsService;
	  
	@Autowired
	private IReadyBagIssDtService readyBagIssDtService;
	  
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IDepartmentService departmentService;

	  
	
	@Autowired
	private IReadyBagService readyBagService; 

	@Override
	public void save(ReadyBagIssMt readyBagIssMt) {
		// TODO Auto-generated method stub
		readyBagIssMtRepository.save(readyBagIssMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		readyBagIssMtRepository.delete(id);;
	}

	@Override
	public Page<ReadyBagIssMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		
		QReadyBagIssMt readyBagIssMt = QReadyBagIssMt.readyBagIssMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search != null) {
				expression = readyBagIssMt.invNo.like("%" + search + "%");
			}
		}else{
			if (search != null) {
				expression =readyBagIssMt.invNo.like("%" + search + "%");
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
	
		Page<ReadyBagIssMt> readyBagList =(Page<ReadyBagIssMt>) readyBagIssMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		
		return readyBagList;
	}

	@Override
	public String readyBagIssMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
	StringBuilder sb = new StringBuilder();
		
		
		Page<ReadyBagIssMt> readyBagIssMtList = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		readyBagIssMtList = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(readyBagIssMtList.getTotalElements()).append(",\"rows\": [");

		for (ReadyBagIssMt readyBagIssMt : readyBagIssMtList) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(readyBagIssMt.getInvDate()));

			sb.append("{\"id\":\"")
			.append(readyBagIssMt.getId())
			.append("\",\"invNo\":\"")
			.append(readyBagIssMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(readyBagIssMt.getInvDateStr())
			.append("\",\"location\":\"")
			.append(readyBagIssMt.getLocation().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/readyBagIssMt/edit/")
								.append(readyBagIssMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteReadyBagIssMt(event,");					
								sb.append(readyBagIssMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(readyBagIssMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/readyBagIssMt/edit/")
					.append(readyBagIssMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteReadyBagIssMt(event,")	
						.append(readyBagIssMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(readyBagIssMt.getId())
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
	public String readyBagMtSave(ReadyBagIssMt readyBagIssMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result,Integer pLocationIds,String vTranDate) throws ParseException {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/readyBagIssMt/add.html";
	
		if (result.hasErrors()) {
			return "readyBagIssMt/add";
		}
		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				readyBagIssMt.setInvDate(dates);
				
				}
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				readyBagIssMt.setSrNo(++maxSrno);
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
				
				readyBagIssMt.setInvNo("VOU-" + (bagSr) + "-" + vYear);
				readyBagIssMt.setSrNo(maxSrno);
				readyBagIssMt.setCreatedBy(principal.getName());
				readyBagIssMt.setCreatedDate(new java.util.Date());
				
				

			} else {
				
				if(pLocationIds != null) {
					Department department = departmentService.findOne(pLocationIds);
					readyBagIssMt.setLocation(department);
				}
				readyBagIssMt.setModiBy(principal.getName());
				readyBagIssMt.setModiDate(new java.util.Date());
								
				action = "updated";
				retVal = "redirect:/manufacturing/transactions/readyBagIssMt.html";
			}
			
			save(readyBagIssMt);
			
			if (action.equals("added")) {
			 retVal = "redirect:/manufacturing/transactions/readyBagIssMt/edit/"+readyBagIssMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
			
		}

}

	@Override
	public String deleteMt(Integer mtId) {
		
		String retVal ="-1";
	
			ReadyBagIssMt readyBagIssMt = findOne(mtId);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date cDate = readyBagIssMt.getInvDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);

			if(dbDate.equals(curDate)){
				
				List<ReadyBagIssDt> readyBagIssDts = readyBagIssDtService.findByReadyBagIssMt(readyBagIssMt);
				
				for (ReadyBagIssDt readyBagIssDt : readyBagIssDts) {
					ReadyBag readyBag = readyBagService.findOne(readyBagIssDt.getRefReadyBagId());
					if(readyBag.getPendApprovalFlg()) {
						
						readyBag.setLocation(null);
						readyBag.setPendApprovalFlg(false);
						readyBagService.save(readyBag);
					
						readyBagIssDtService.delete(readyBagIssDt.getId());
					
					retVal = "1";
				}else {
					
					return "Cannot Delete";
				
				}
					
				}
				
				delete(mtId);
				retVal ="1";
							
			}else {
				return "Can Not Delete BackDate Entry";
			}
			
			
		return retVal;
	}

	@Override
	public ReadyBagIssMt findOne(int id) {
		// TODO Auto-generated method stub
		return readyBagIssMtRepository.findOne(id);
	}

	@Override
	public String readyBagPendingApprovalList(Integer locationId, Principal principal) {
		// TODO Auto-generated method stub
	List<Object[]> objects =new ArrayList<Object[]>();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_readyBagPendingApprovalList(?) }");
		query.setParameter(1,locationId);
		objects = query.getResultList();
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
			
		 for(Object[] list:objects){
	
				
				sb.append("{\"mtId\":\"")
			     .append(list[0] != null ? list[0] : "")
			     .append("\",\"bagId\":\"")
			     .append(list[1] != null ? list[1] : "")
			     .append("\",\"bagQty\":\"")
			     .append(list[2] != null ? list[2] : "")
			     .append("\",\"carat\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"stone\":\"")
				 .append(list[4] != null ? list[4] : "")
			     .append("\",\"bagNm\":\"")
				 .append(list[5] != null ? list[5] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"partyCode\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"purity\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"color\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QReadyBagIssMt qReadyBagIssMt = QReadyBagIssMt.readyBagIssMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qReadyBagIssMt)
			.where(qReadyBagIssMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qReadyBagIssMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}


	
}

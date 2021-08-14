package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QBarcodeMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QGrecMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IBarcodeMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBarcodeDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBarcodeMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class BarcodeMtService implements IBarcodeMtService {

	@Autowired
	private IBarcodeMtRepository barcodeMtRepository;
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IBarcodeDtService barcodeDtService;
	
	@Autowired
	private EntityManager entityManager;
	

	@Override
	public BarcodeMt findOne(int id) {
	
		return barcodeMtRepository.findOne(id);
	}

	@Override
	public void save(BarcodeMt barcodeMt) {
	
		barcodeMtRepository.save(barcodeMt);
	}

	@Override
	public void delete(int id) {
		BarcodeMt barcodeMt = barcodeMtRepository.findOne(id);
		barcodeMt.setDeactive(true);
		barcodeMt.setDeactiveDt(new java.util.Date());
		barcodeMtRepository.save(barcodeMt);
				
	}

	@Override
	public BarcodeMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		
		return barcodeMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Page<BarcodeMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		QBarcodeMt qBarcodeMt = QBarcodeMt.barcodeMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qBarcodeMt.deactive.eq(false);
			}else{
				expression = qBarcodeMt.deactive.eq(false).and(qBarcodeMt.invNo.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression =qBarcodeMt.invNo.like("%" + search + "%");
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
		}else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
	
		Page<BarcodeMt> barcodeMtList =(Page<BarcodeMt>) barcodeMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return barcodeMtList;
	}

	@Override
	public String barcodeMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<BarcodeMt> barcodeMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("barcodeMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		barcodeMts = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(barcodeMts.getTotalElements()).append(",\"rows\": [");

		for (BarcodeMt barcodeMt : barcodeMts) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(barcodeMt.getInvDate()));

			sb.append("{\"id\":\"").append(barcodeMt.getId())
			  .append("\",\"invNo\":\"")
			  .append(barcodeMt.getInvNo())
		      .append("\",\"invDate\":\"")
			  .append(barcodeMt.getInvDateStr())
					/*
					 * .append("\",\"deptNm\":\"") .append(barcodeMt.getDepartment().getName())
					 */
					 
			  .append("\",\"action1\":\"");
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

				sb.append("<a href='/jewels/manufacturing/transactions/barcodeMt/edit/")
				.append(barcodeMt.getId())
				.append(".html'");

				sb.append(	".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");

				sb.append("<a href='javascript:deleteBarcodeMt(event,");
				sb.append(barcodeMt.getId());

				sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(barcodeMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")

						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/barcodeMt/edit/").append(barcodeMt.getId())
							.append(".html'");

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteBarcodeMt(event,").append(barcodeMt.getId())
								.append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(barcodeMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>").append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
		
	}

	@Override
	public String barcodeDtListing(Integer mtId) {
		BarcodeMt barcodeMt = findOne(mtId);
		List<BarcodeDt> barcodeDts = barcodeDtService.findByBarcodeMt(barcodeMt);

		String str = "";
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(barcodeDts.size()).append(",\"rows\": [");
		for (BarcodeDt barcodeDt : barcodeDts) {

			sb.append("{\"id\":\"").append(barcodeDt.getId()).append("\",\"barcode\":\"").append(barcodeDt.getBarcode())
					.append("\",\"bagNo\":\"").append(barcodeDt.getBagMt().getName()).append("\",\"styleNo\":\"")
					.append(barcodeDt.getDesign().getMainStyleNo()).append("\",\"kt\":\"")
					.append(barcodeDt.getPurity().getName()).append("\",\"color\":\"")
					.append(barcodeDt.getColor().getName()).append("\"},");

		}

		str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	@Override
	public String saveBarcodeMt(BarcodeMt barcodeMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result) {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/barcodeMt/add.html";

		if (result.hasErrors()) {
			return "barcodeMt/add";
		}

		synchronized (this) {

			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno =getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				barcodeMt.setSrNo(++maxSrno);
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
				
				
				barcodeMt.setInvNo("BG-" + (bagSr) + "-" + vYear);

				barcodeMt.setCreatedBy(principal.getName());
				barcodeMt.setCreatedDt(new java.util.Date());

			} else {
				barcodeMt.setModiBy(principal.getName());
				barcodeMt.setModiDt(new java.util.Date());

				action = "updated";
				retVal = "redirect:/manufacturing/transactions/barcodeMt.html";
			}

			save(barcodeMt);

			if (action.equals("added")) {
				retVal = "redirect:/manufacturing/transactions/barcodeMt/edit/" + barcodeMt.getId() + ".html";
			}

			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);

			return retVal;
			
		}
	}
	
	
	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QBarcodeMt qBarcodeMt = QBarcodeMt.barcodeMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qBarcodeMt)
			.where(qBarcodeMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qBarcodeMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		return retVal;
	}
	
	
}

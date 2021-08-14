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
import org.springframework.ui.Model;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QRepairIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IRepairIssMtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairIssMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class RepairIssMtService implements IRepairIssMtService {

	@Autowired
	private IRepairIssMtRepository repairIssMtRepository;
	
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
	
	@Override
	public List<RepairIssMt> findAll() {
		// TODO Auto-generated method stub
		return repairIssMtRepository.findAll();
	}
	@Override
	public Page<RepairIssMt> findAll(Integer limit, Integer offset, String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		
		return repairIssMtRepository.findAll(new PageRequest(page,limit,(order.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC ), sort));
	}
	@Override
	public void save(RepairIssMt repairIssMt) {
		repairIssMtRepository.save(repairIssMt);
		
	}
	@Override
	public void delete(int id) {
		RepairIssMt repairIssMt = repairIssMtRepository.findOne(id);
		repairIssMt.setDeactive(true);
		repairIssMt.setDeactiveDt(new java.util.Date());
		repairIssMtRepository.save(repairIssMt);
		
	}
	@Override
	public RepairIssMt findOne(int id) {

		return repairIssMtRepository.findOne(id);
	}
	@Override
	public RepairIssMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return repairIssMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}
	@Override
	public Page<RepairIssMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name,
			Boolean onlyActive) {
		QRepairIssMt qRepairIssMt = QRepairIssMt.repairIssMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qRepairIssMt.deactive.eq(false);
			} else {
				expression = qRepairIssMt.deactive.eq(false).and(
						qRepairIssMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qRepairIssMt.invNo.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<RepairIssMt> repairIssMtList = (Page<RepairIssMt>) repairIssMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return repairIssMtList;
	}
	@Override
	public Page<RepairIssMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		
		QRepairIssMt qRepairIssMt = QRepairIssMt.repairIssMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qRepairIssMt.deactive.eq(false);
			}else{
				expression = qRepairIssMt.deactive.eq(false).and(qRepairIssMt.invNo.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression =qRepairIssMt.invNo.like("%" + search + "%");
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
	
		Page<RepairIssMt> repairIssMtList =(Page<RepairIssMt>) repairIssMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return repairIssMtList;
	

	}
	
	
	@Override
	public Integer getMaxInvSrno() {
		QRepairIssMt qRepairIssMt = QRepairIssMt.repairIssMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qRepairIssMt)
			.where(qRepairIssMt.deactive.eq(false).and(qRepairIssMt.invDate.year().eq(date.get(Calendar.YEAR)))).list(qRepairIssMt.invSrNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}
	
	
	@Override
	public String repairIssMtListing(Model model, Integer limit, Integer offset, String sort, String order,
			String search, Principal principal) throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<RepairIssMt> repairIssMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("repairIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
	
		repairIssMts = searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(repairIssMts.getTotalElements()).append(",\"rows\": [");

		for (RepairIssMt repairIssMt : repairIssMts) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(repairIssMt.getInvDate()));

			sb.append("{\"id\":\"").append(repairIssMt.getId())
			.append("\",\"invNo\":\"")
			.append(repairIssMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(repairIssMt.getInvDateStr())
			.append("\",\"deptNm\":\"")
			.append(repairIssMt.getDepartment().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/repairIssMt/edit/")
								.append(repairIssMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteRepairIssMt(event,");					
								sb.append(repairIssMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(repairIssMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/repairIssMt/edit/")
					.append(repairIssMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteRepairIssMt(event,")	
						.append(repairIssMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(repairIssMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		
		return str;

	}
	
	
	
}

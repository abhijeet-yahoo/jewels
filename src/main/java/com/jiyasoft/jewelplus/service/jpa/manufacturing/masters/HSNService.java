package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HSNMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QHSNMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IHSNRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHSNService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class HSNService implements IHSNService {

	@Autowired
	private IHSNRepository hSNRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	public void save(HSNMast hSNMast) {
		hSNRepository.save(hSNMast);		
	}

	@Override
	public void delete(int id) {
		HSNMast hSNMast = hSNRepository.findOne(id);
		hSNMast.setDeactive(true);
		hSNMast.setDeactiveDt(new java.util.Date());
		hSNRepository.save(hSNMast);
	}

	@Override
	public HSNMast findOne(int id) {
		
		return hSNRepository.findOne(id);
	}

	@Override
	public List<HSNMast> findAll() {
		
		return hSNRepository.findAll();
	}

	@Override
	public Page<HSNMast> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		QHSNMast qHSNMast = QHSNMast.hSNMast;
		BooleanExpression expression = qHSNMast.deactive.eq(false);
		if (onlyActive) {
			if (search == null) {
				expression = qHSNMast.deactive.eq(false);
			} else {
				expression = qHSNMast.deactive.eq(false).and(qHSNMast.code.like("%" + search + "%"));
			}
		} else {
			if (search != null) {
				expression = qHSNMast.code.like("%" + search + "%");
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
		Page<HSNMast> hSNMastList = (Page<HSNMast>) hSNRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		
		return hSNMastList;
	}

	@Override
	public String hSNListing (Integer limit, Integer offset,
			String sort, String order, String search,Principal principal, Boolean onlyActive) {
		
		
		StringBuilder sb = new StringBuilder();
		Page<HSNMast> hSNMasts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("hSNMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		hSNMasts = searchAll(limit, offset, sort, order, search, false);


		sb.append("{\"total\":").append(hSNMasts.getTotalElements()).append(",\"rows\": [");

		for (HSNMast hSNMast : hSNMasts) {
			
			
			sb.append("{\"id\":\"")
					.append(hSNMast.getId())
					.append("\",\"code\":\"")
					.append(hSNMast.getCode())
					.append("\",\"desc\":\"")
					.append(hSNMast.getDesc()!=null?hSNMast.getDesc():"")
					.append("\",\"hsnNo\":\"")
					.append(hSNMast.getHsnNo()!=null?hSNMast.getHsnNo():"")
					.append("\",\"cGST\":\"")
					.append(hSNMast.getcGST())
					.append("\",\"sGST\":\"")
					.append(hSNMast.getsGST())
					.append("\",\"iGST\":\"")
					.append(hSNMast.getiGST())
					.append("\",\"deactive\":\"")
					.append(hSNMast.isDeactive() ? "Yes":"No");
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/hSNMast/edit/")
						.append(hSNMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/hSNMast/delete/")
							.append(hSNMast.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(hSNMast.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
	
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/hSNMast/edit/")
						.append(hSNMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/hSNMast/delete/")
							.append(hSNMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(hSNMast.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
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
	public String addHSNMaster(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("hSNMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "hSNMast/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "hSNMast/add";
	}

	@Override
	public HSNMast findByCodeAndDeactive(String code, Boolean deactive) {
		
		return hSNRepository.findByCodeAndDeactive(code, deactive);
	}

	@Override
	public List<HSNMast> findActiveHsn() {
		// TODO Auto-generated method stub
		QHSNMast qhsnMast = QHSNMast.hSNMast;
		BooleanExpression expression = qhsnMast.deactive.eq(false);

		List<HSNMast> hsnList = (List<HSNMast>) hSNRepository.findAll(expression);

		return hsnList;
	}

	@Override
	public Map<Integer, String> getHsnList() {
		// TODO Auto-generated method stub
		Map<Integer, String> hsnMap = new HashMap<Integer, String>();
		List<HSNMast> hsnList = findActiveHsn();

		for (HSNMast hsn : hsnList) {
			hsnMap.put(hsn.getId(), hsn.getDesc());
		}

		return hsnMap;
	}

	@Override
	public String getapplyGst(Integer hsnId) {
		
		JSONObject jsonObject =  new JSONObject();
		try {
			
			HSNMast hsnMast = findOne(hsnId);
			jsonObject.put("cgst", hsnMast.getcGST());
			jsonObject.put("sgst", hsnMast.getsGST());
			jsonObject.put("igst", hsnMast.getiGST());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonObject.toString();
	}


	
	
	
}

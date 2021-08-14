package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QGrecCompDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IGrecCompDtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecDtService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class GrecCompDtService implements IGrecCompDtService{

	@Autowired
	private IGrecCompDtRepository grecCompDtRepository;
	
	@Autowired
	private IGrecDtService grecDtService;
	
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	

	@Override
	public void save(GrecCompDt grecCompDt) {
		grecCompDtRepository.save(grecCompDt);
		
	}

	@Override
	public void delete(int id) {
	grecCompDtRepository.delete(id);
		
	}


	@Override
	public GrecCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return grecCompDtRepository.findOne(id);
	}
	


	@Override
	public List<GrecCompDt> findByGrecMtAndGrecDt(GrecMt grecMt, GrecDt grecDt) {

		QGrecCompDt qGrecCompDt = QGrecCompDt.grecCompDt;
		BooleanExpression expression = qGrecCompDt.deactive.eq(false).and(qGrecCompDt.grecMt.id.eq(grecMt.getId()))
				.and(qGrecCompDt.grecDt.id.eq(grecDt.getId()));

		return (List<GrecCompDt>) grecCompDtRepository.findAll(expression);
	}

	@Override
	public List<GrecCompDt> findByGrecMt(GrecMt grecMt) {
		// TODO Auto-generated method stub
		return grecCompDtRepository.findByGrecMt(grecMt);
	}

	@Override
	public List<GrecCompDt> findByGrecDt(GrecDt grecDt) {
		// TODO Auto-generated method stub
		return grecCompDtRepository.findByGrecDt(grecDt);
	}




	@Override
	public void setGrecCompDt(List<DesignComponent> designComponents, GrecMt grecMt, GrecDt grecDt,
			Principal principal) {
		if (designComponents != null) {

			for (DesignComponent designComponent : designComponents) {

				GrecCompDt grecCompDt = new GrecCompDt();

				grecCompDt.setGrecMt(grecMt);
				grecCompDt.setGrecDt(grecDt);
				grecCompDt.setComponent(designComponent.getComponent());
				grecCompDt.setCompQty(designComponent.getQuantity());
				grecCompDt.setCreatedBy(principal.getName());
				grecCompDt.setCreatedDate(new java.util.Date());
				grecCompDt.setPurity(grecDt.getPurity());
				grecCompDt.setColor(grecDt.getColor());
				grecCompDt.setMetalWt(designComponent.getCompWt());

				grecCompDtRepository.save(grecCompDt);

			}

		}
		
	}

	

	@Override
	public String grecCompDtListing(Integer grecDtId, Boolean canViewFlag, Principal principal) {
	
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("grecMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
			
				StringBuilder sb = new StringBuilder();
			
				
				
				GrecDt grecDt = grecDtService.findOne(grecDtId);
				List<GrecCompDt> grecCompDts = findByGrecDt(grecDt);
				
				sb.append("{\"total\":").append(grecCompDts.size()).append(",\"rows\": [");
				if(grecCompDts.size() > 0){
								for(GrecCompDt grecCompDt:grecCompDts){
					
				sb.append("{\"id\":\"")
					.append(grecCompDt.getId())
					.append("\",\"compName\":\"")
					.append((grecCompDt.getComponent() != null ? grecCompDt.getComponent().getName() : ""))
					.append("\",\"kt\":\"")
					.append((grecCompDt.getPurity() != null ? grecCompDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((grecCompDt.getColor() != null ? grecCompDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append((grecCompDt.getMetalWt() != null ? grecCompDt.getMetalWt() : ""))
					.append("\",\"rate\":\"")
					.append((grecCompDt.getCompRate() != null ? grecCompDt.getCompRate() : ""))
					.append("\",\"compQty\":\"")
					.append((grecCompDt.getCompQty() != null ? grecCompDt.getCompQty() : ""))
					.append("\",\"value\":\"")
					.append((grecCompDt.getCompValue() != null ? grecCompDt.getCompValue() : ""))
					.append("\",\"rLock\":\"")
					.append(grecCompDt.getrLock());
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					if(!canViewFlag){
					
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doCompDtLockUnLock(")
					.append(grecCompDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
					
					sb.append("\",\"action1\":\"");
					
					sb.append("<a href='javascript:editGrecCompDt(")
					.append(grecCompDt.getId());	
					
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
					
						sb.append("<a href='javascript:deleteGrecCompDt(event,")
						.append(grecCompDt.getId());
							
					
					sb.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(grecCompDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						.append("")
						.append("\",\"actionLock\":\"")
						.append("");
					}
					
					sb.append("\"},");		
								
				}else
				{
				if(!canViewFlag){
					
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doCompDtLockUnLock(")
					.append(grecCompDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
					
					sb.append("\",\"action1\":\"");
					if(roleRights.getCanEdit()){
					sb.append("<a href='javascript:editGrecCompDt(")
					.append(grecCompDt.getId());	
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
					if(roleRights.getCanDelete()){
						sb.append("<a href='javascript:deleteGrecCompDt(event,")
						.append(grecCompDt.getId());
						
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(grecCompDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						.append("")
						.append("\",\"actionLock\":\"")
						.append("");
					}
					
				sb.append("\"},");
				}

				}
			}

			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
			System.out.println(str);
			return str;
			
			}


}

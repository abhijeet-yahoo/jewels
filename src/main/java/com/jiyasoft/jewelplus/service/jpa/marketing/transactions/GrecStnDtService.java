package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.QGrecStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IGrecStnDtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecStnDtService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class GrecStnDtService implements IGrecStnDtService {

	@Autowired
	private IGrecStnDtRepository grecStnDtRepository;
	
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
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(GrecStnDt grecStnDt) {
		grecStnDtRepository.save(grecStnDt);
		
	}

	@Override
	public void delete(int id) {
		grecStnDtRepository.delete(id);
	}
	
	@Override
	public GrecStnDt findOne(int id) {
		
		return grecStnDtRepository.findOne(id);
	}


	


	@Override
	public List<GrecStnDt> findByGrecMt(GrecMt grecMt) {
		// TODO Auto-generated method stub
		return grecStnDtRepository.findByGrecMt(grecMt);
	}

	@Override
	public Integer getMaxSrno(GrecMt grecMt, GrecDt grecDt) {
		QGrecStnDt qGrecStnDt = QGrecStnDt.grecStnDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = 0;

		List<Integer> maxSrno = query
			.from(qGrecStnDt)
			.where(qGrecStnDt.deactive.eq(false).and(qGrecStnDt.grecMt.id.eq(grecMt.getId())).and(qGrecStnDt.grecDt.id.eq(grecDt.getId())))
			.list(qGrecStnDt.srNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}
		
		if(retVal == null){
			retVal =0;
		}
		

		return retVal;
	}

	@Override  
	public void setGrecStnDt(List<DesignStone> designStones, GrecMt grecMt, GrecDt grecDt, Principal principal) {
		if(designStones != null){
			
			Boolean recordFound = false;
			int srNo=0;
			for(DesignStone designStone:designStones){
				srNo+=1;
				recordFound = false;
				
				GrecStnDt grecStnDt = new GrecStnDt();
				grecStnDt.setSrNo(srNo);
				grecStnDt.setGrecMt(grecMt);
				grecStnDt.setGrecDt(grecDt);
				grecStnDt.setCarat(designStone.getCarat());
				grecStnDt.setShape(designStone.getShape());
				grecStnDt.setStoneType(designStone.getStoneType());
				grecStnDt.setSubShape(designStone.getSubShape());
				
			
				
				if(!recordFound){
					grecStnDt.setQuality(designStone.getQuality());
				}
				
				grecStnDt.setSize(designStone.getSize());
				grecStnDt.setSizeGroup(designStone.getSizeGroup());
				grecStnDt.setSieve(designStone.getSieve());
				grecStnDt.setStone(designStone.getStone());
				grecStnDt.setSetting(designStone.getSetting());
				grecStnDt.setSettingType(designStone.getSettingType());
				grecStnDt.setCreatedBy(principal.getName());
				grecStnDt.setCreatedDate(new java.util.Date());
				grecStnDt.setPartNm(designStone.getPartNm());
				grecStnDt.setDiaCateg(designStone.getDiaCateg());
				
				
				grecStnDtRepository.save(grecStnDt);
				
				
				
			}
			
		}
		
	}

	@Override
	public List<GrecStnDt> findByGrecDt(GrecDt grecDt) {
		// TODO Auto-generated method stub
		return grecStnDtRepository.findByGrecDt(grecDt);
	}

	

	@Override
	public String grecStnDtListing(Integer grecDtId, Boolean canViewFlag, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("grecMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
			StringBuilder sb = new StringBuilder();
			
			GrecDt grecDt = grecDtService.findOne(grecDtId);
			List<GrecStnDt> grecStnDts = findByGrecDt(grecDt);
			
			
			sb.append("{\"total\":").append(grecStnDts.size())
			.append(",\"rows\": [");
			for(GrecStnDt grecStnDt:grecStnDts){
					
				sb.append("{\"id\":\"")
					.append(grecStnDt.getId())
					.append("\",\"partNm\":\"")
					.append((grecStnDt.getPartNm() != null ? grecStnDt.getPartNm().getFieldValue() : ""))
					.append("\",\"stoneType\":\"")
					.append((grecStnDt.getStoneType() != null ? grecStnDt.getStoneType().getName() : ""))
					.append("\",\"shape\":\"")
					.append((grecStnDt.getShape() != null ? grecStnDt.getShape().getName() : ""))
					.append("\",\"subShape\":\"")
					.append((grecStnDt.getSubShape() != null ? grecStnDt.getSubShape().getName() : ""))
					.append("\",\"quality\":\"")
					.append((grecStnDt.getQuality() != null ? grecStnDt.getQuality().getName() : ""))
					.append("\",\"diaCateg\":\"")
					.append((grecStnDt.getDiaCateg() != null ? grecStnDt.getDiaCateg() : ""))
					.append("\",\"size\":\"")
					.append((grecStnDt.getSize() != null ? grecStnDt.getSize() : ""))
					.append("\",\"sieve\":\"")
					.append((grecStnDt.getSieve() != null ? grecStnDt.getSieve() : ""))
					.append("\",\"sizeGroup\":\"")
					.append((grecStnDt.getSizeGroup() != null ? grecStnDt.getSizeGroup().getName() : ""))
					.append("\",\"stone\":\"")
					.append((grecStnDt.getStone() != null ? grecStnDt.getStone() : ""))
					.append("\",\"carat\":\"")
					.append((grecStnDt.getCarat() != null ? grecStnDt.getCarat() : ""))
					.append("\",\"rate\":\"")
					.append((grecStnDt.getStnRate() != null ? grecStnDt.getStnRate() : ""))
					.append("\",\"stoneValue\":\"")
					.append((grecStnDt.getStoneValue() != null ? grecStnDt.getStoneValue() : ""))
					.append("\",\"handlingRate\":\"")
					.append((grecStnDt.getHandlingRate() != null ? grecStnDt.getHandlingRate() : ""))
					.append("\",\"handlingValue\":\"")
					.append((grecStnDt.getHandlingValue() != null ? grecStnDt.getHandlingValue() : ""))
					.append("\",\"setting\":\"")
					.append((grecStnDt.getSetting() != null ? grecStnDt.getSetting().getName() : ""))
					.append("\",\"settingType\":\"")
					.append((grecStnDt.getSettingType() != null ? grecStnDt.getSettingType().getName() : ""))
					.append("\",\"settingRate\":\"")
					.append((grecStnDt.getSetRate() != null ? grecStnDt.getSetRate() : ""))
					.append("\",\"settingValue\":\"")
					.append((grecStnDt.getSetValue() != null ? grecStnDt.getSetValue() : ""))
					.append("\",\"rLock\":\"")
					.append(grecStnDt.getrLock()); //1 = lock & 0 = unlock
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					if(!canViewFlag){
			
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doStoneDtLockUnLock(")
					.append(grecStnDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
					
					.append("\",\"action1\":\"");
					
					sb.append("<a href='javascript:editGrecStnDt(")
					.append(grecStnDt.getId());				
					
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					sb.append("\",\"action2\":\"");
					sb.append("<a href='javascript:deleteGrecStnDt(event,").append(grecStnDt.getId());	
					sb.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(grecStnDt.getId())
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
				else
				{

					
					if(!canViewFlag){
			
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doStoneDtLockUnLock(")
					.append(grecStnDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
					
					.append("\",\"action1\":\"");
					if(roleRights.getCanEdit()){
					sb.append("<a href='javascript:editGrecStnDt(")
					.append(grecStnDt.getId());				
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					
					sb.append("\",\"action2\":\"");
					if(roleRights.getCanDelete()){
					sb.append("<a href='javascript:deleteGrecStnDt(event,").append(grecStnDt.getId());	
					}else{
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(grecStnDt.getId())
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
			
			
			
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";

			return str;

		
	}

}

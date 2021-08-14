package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.WipStyle;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IWipStyleRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IWipStyleService;

@Service
@Repository
@Transactional
public class WipStyleService implements IWipStyleService {
	

@Autowired
private IWipStyleRepository wipStyleRepository;


@Autowired
private UserService userService;

@Autowired
private UserRoleService userRoleService;

@Autowired
private IPartyService partyService;

@Autowired
private RoleRightsService roleRightsService;

@Autowired
private MenuMastService menuMastService;
	
	@Override
	public List<WipStyle> findByPartyAndDeactive(Party party, Boolean deactive) {
		// TODO Auto-generated method stub
		return wipStyleRepository.findByPartyAndDeactive(party, deactive);
	}

	@Override
	public WipStyle findByPartyAndDesignAndDeactive(Party party, Design design, Boolean deactive) {
		// TODO Auto-generated method stub
		return wipStyleRepository.findByPartyAndDesignAndDeactive(party, design, deactive);
	}

	@Override
	public void save(WipStyle wipstyle) {
		// TODO Auto-generated method stub
		wipStyleRepository.save(wipstyle);
	}

	@Override
	public void delete(int id) {
	
		wipStyleRepository.delete(id);
	}

	@Override
	public WipStyle findOne(int id) {
		// TODO Auto-generated method stub
		return wipStyleRepository.findOne(id);
	}

	@Override
	public String wipStyleListing(String partyCode, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("order");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		StringBuilder sb = new StringBuilder();

		Party party = partyService.findByPartyCodeAndDeactive(partyCode, false);
		List<WipStyle> wipStyles = findByPartyAndDeactive(party, false);

		sb.append("{\"total\":").append(wipStyles.size()).append(",\"rows\": [");
		for (WipStyle wipStyle : wipStyles) {

						sb.append("{\"id\":\"")
						.append(wipStyle.getId())
						.append("\",\"partyNm\":\"")
						.append((wipStyle.getParty() != null ? wipStyle.getParty().getPartyCode() : ""))
						.append("\",\"StyleNo\":\"")
						.append((wipStyle.getDesign() != null ? wipStyle.getDesign().getMainStyleNo() : ""));
	
						sb.append("\",\"action1\":\"").append("<a href='javascript:deleteWipStyle(event,").append(wipStyle.getId());
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(wipStyle.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
						sb.append("\"},");
						
				

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStnLooseDtRepository;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILooseStkConversionService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseRetDtService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class StnLooseDtService implements IStnLooseDtService{
	
	@Autowired
	private IStnLooseDtRepository stnLooseDtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStnLooseMtService stnLooseMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ILooseStkConversionService looseStkConversionService;
	
	@Autowired
	private IStnLooseRetDtService stnLooseRetDtService;

	@Override
	public List<StnLooseDt> findByStnLooseMt(StnLooseMt stnLooseMt) {
		// TODO Auto-generated method stub
		return stnLooseDtRepository.findByStnLooseMt(stnLooseMt);
	}

	@Override
	public void save(StnLooseDt stnLooseDt) {
		// TODO Auto-generated method stub
		stnLooseDtRepository.save(stnLooseDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		stnLooseDtRepository.delete(id);;
		
	}

	@Override
	public StnLooseDt findOne(int id) {
		// TODO Auto-generated method stub
		return stnLooseDtRepository.findOne(id);
	}

	@Override
	public String saveStnLooseDt(StnLooseDt stnLooseDt, Integer id, String pInvNo, Double vCarat, Integer vStone,
			String sizeGroupStr, Principal principal) {
		
		String retVal ="-1";
		String action = "added";
		
		try {
		
			if (id == null || id.equals("") || (id != null && id == 0)) {
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				Integer	maxSrno = getMaxLotSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				++maxSrno;
				int si = maxSrno.toString().length();
				
				String bagSr = null;
				
				switch (si) {
				case 1:
					bagSr = "0000"+maxSrno;
					break;
					
				case 2:
					bagSr = "000"+maxSrno;
					break;
					
				case 3:
					bagSr = "00"+maxSrno;
					break;
				case 4:
					bagSr = "0"+maxSrno;
					break;	
					
				default:
					bagSr = maxSrno.toString();
					break;
				}
				
				stnLooseDt.setLotNo("LSTN-"+bagSr+"-"+vYear);
				stnLooseDt.setLotSrNo(maxSrno);
				stnLooseDt.setCreatedBy(principal.getName());
				stnLooseDt.setCreatedDt(new java.util.Date());
				stnLooseDt.setStnLooseMt(stnLooseMtService.findByInvNo(pInvNo));
				stnLooseDt.setUniqueId(new Date().getTime());
				stnLooseDt.setDepartment(departmentService.findByName("Diamond"));
				stnLooseDt.setBalCarat(vCarat);
				stnLooseDt.setBalStone(vStone);
				stnLooseDt.setCarat(vCarat);
				stnLooseDt.setStone(vStone);
				stnLooseDt.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(stnLooseDt.getShape(), sizeGroupStr,false));
				
				retVal = "1";
				
			} else {
				
				if(stnLooseDt.getBalCarat() > stnLooseDt.getCarat()){
					return retVal = "-11";
				}
				
				
				stnLooseDt.setModiBy(principal.getName());
				stnLooseDt.setModiDt(new java.util.Date());
				stnLooseDt.setStnLooseMt(stnLooseMtService.findByInvNo(pInvNo));
				stnLooseDt.setSizeGroup(sizeGroupService.findByShapeAndNameAndDeactive(stnLooseDt.getShape(), sizeGroupStr,false));
				stnLooseDt.setBalCarat(vCarat);
				stnLooseDt.setBalStone(vStone);
				stnLooseDt.setDepartment(departmentService.findByName("Diamond"));
				stnLooseDt.setCarat(vCarat);
				stnLooseDt.setStone(vStone);
			
				action = "updated";
				
			}
			
			stnLooseDt.setBalAmount(stnLooseDt.getAmount());
			
			
			save(stnLooseDt);
			retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		retVal=retVal+","+action;
		
		return retVal;
	}

	@Override
	public Integer getMaxLotSrno() {
		// TODO Auto-generated method stub
		QStnLooseDt qStnLooseDt = QStnLooseDt.stnLooseDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStnLooseDt)
			.where(qStnLooseDt.createdDt.year().eq(date.get(Calendar.YEAR))).list(qStnLooseDt.lotSrNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public String stnLooseDtListing(Integer mtId,Boolean canViewFlag,Principal principal) {
		// TODO Auto-generated method stub
		
		StnLooseMt stnLooseMt = stnLooseMtService.findOne(mtId);
		List<StnLooseDt> stnLooseDts = findByStnLooseMt(stnLooseMt);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stnLooseDts.size()).append(",\"rows\": [");
		 
		 for (StnLooseDt stnLooseDt : stnLooseDts) {
			
			 String ordRef = null;
				String styleNo = null;
				if(stnLooseDt.getSordDtId() !=  null) {
					OrderDt orderDt = orderDtService.findOne(stnLooseDt.getSordDtId());
					ordRef = orderDt.getRefNo();
					styleNo = orderDt.getDesign().getMainStyleNo();
				}
				
				
				sb.append("{\"id\":\"")
				.append(stnLooseDt.getId())
				.append("\",\"refNo\":\"")
				.append((ordRef != null ? ordRef : ""))
				.append("\",\"styleNo\":\"")
				.append((styleNo != null ? styleNo : ""))
				.append("\",\"stoneType\":\"")
				.append((stnLooseDt.getStoneType() != null ? stnLooseDt.getStoneType().getName() : ""))
				.append("\",\"shape\":\"")
				.append((stnLooseDt.getShape() != null ? stnLooseDt.getShape().getName() : ""))
				.append("\",\"subShape\":\"")
				.append((stnLooseDt.getSubShape() != null ? stnLooseDt.getSubShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((stnLooseDt.getQuality() != null ? stnLooseDt.getQuality().getName() : ""))
				.append("\",\"stoneChart\":\"")
				.append((stnLooseDt.getSize() != null ? stnLooseDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append(stnLooseDt.getSieve())
				.append("\",\"sizeGroupStr\":\"")
				.append((stnLooseDt.getSizeGroup() != null ? stnLooseDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append(stnLooseDt.getStone())
				.append("\",\"balStone\":\"")
				.append(stnLooseDt.getBalStone())
				.append("\",\"carat\":\"")
				.append(stnLooseDt.getCarat())
				.append("\",\"diffCarat\":\"")
				.append(stnLooseDt.getDiffCarat())
				.append("\",\"balCarat\":\"")
				.append(stnLooseDt.getBalCarat())
				.append("\",\"rate\":\"")
				.append(stnLooseDt.getRate())
				.append("\",\"amount\":\"")
				.append(stnLooseDt.getAmount())
				.append("\",\"lotNo\":\"")
				.append(stnLooseDt.getLotNo() != null ? stnLooseDt.getLotNo() : "")
				.append("\",\"remark\":\"")
				.append((stnLooseDt.getRemark() != null ? stnLooseDt.getRemark() : ""));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				 if(!canViewFlag){

					sb.append("\",\"action1\":\"");
							
								sb.append("<a href='javascript:addeditStoneLooseDt(").append(stnLooseDt.getId());
							
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
							sb.append("\",\"action2\":\"");
							
								sb.append("<a  href='javascript:deleteStoneLooseDt(event,")
									.append(stnLooseDt.getId()).append(", 0);' href='javascript:void(0);'");
							
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(stnLooseDt.getId())
							 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
							
							sb.append("\",\"action3\":\"");
							
							sb.append("<a  href='javascript:showConversionDt(event,")
								.append(stnLooseDt.getId()).append(", 0);' href='javascript:void(0);'");
						
							sb.append(" class='btn btn-xs btn-info' ")
							.append(stnLooseDt.getId())
							 .append("'><span class='glyphicon glyphicon-eye-open'></span>&nbsp;Show Conversion</a>");
							
									
						
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("");
				}
				
						sb.append("\"},");
			}
			else
			{
				 if(!canViewFlag){

						sb.append("\",\"action1\":\"");
								if (roleRights.getCanEdit()) {
									sb.append("<a href='javascript:addeditStoneLooseDt(").append(stnLooseDt.getId());
								} else {
									sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
								}
								sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
								
								sb.append("\",\"action2\":\"");
								if (roleRights.getCanDelete()) {
									sb.append("<a  href='javascript:deleteStoneLooseDt(event,")
										.append(stnLooseDt.getId()).append(", 0);' href='javascript:void(0);'");
								} else {
									sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
								}
								sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(stnLooseDt.getId())
								 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
								
								sb.append("\",\"action3\":\"");
								
								sb.append("<a  href='javascript:showConversionDt(event,")
									.append(stnLooseDt.getId()).append(", 0);' href='javascript:void(0);'");
							
								sb.append(" class='btn btn-xs btn-info' ")
								.append(stnLooseDt.getId())
								 .append("'><span class='glyphicon glyphicon-eye-open'></span>&nbsp;Show Conversion</a>");
							
								
					}else{
						sb.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
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

	@Override
	public String stnLooseDtDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
//		Boolean adjFlg = false;
		try {
			StnLooseDt stnLooseDt =stnLooseDtRepository.findOne(id);
			
			List<StnLooseRetDt> stnLooseRetDt = stnLooseRetDtService.findByRefTranId(stnLooseDt.getId());
			if(stnLooseRetDt.size() > 0) {
				
				return	"-3";
				}
			
			List<LooseStkConversion> looseStkConversions = looseStkConversionService.findByStnLooseDt(stnLooseDt);

			if(looseStkConversions.size() > 0) {
				retVal = "-2";
			}else {
				for (LooseStkConversion looseStkConversion : looseStkConversions) {
					looseStkConversionService.stnLooseConversionDtDelete(looseStkConversion.getId(), principal);
				
			}
			
			delete(id);
			retVal ="1";
			}
			
			
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public String getStnLooseBalanceStock(Integer dtId) {
		// TODO Auto-generated method stub
		
		JSONObject jsonObject = new JSONObject();
		
		try {
			StnLooseDt stnLooseDt =stnLooseDtRepository.findOne(dtId);
			
			Double avgRate = Math.round((stnLooseDt.getBalAmount() / stnLooseDt.getBalCarat())*100.0)/100.0;
			
			
			jsonObject.put("vBalCarat", stnLooseDt.getBalCarat());
			jsonObject.put("avgRate", avgRate);
			jsonObject.put("balVal", stnLooseDt.getBalAmount());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return jsonObject.toString();
	}

}

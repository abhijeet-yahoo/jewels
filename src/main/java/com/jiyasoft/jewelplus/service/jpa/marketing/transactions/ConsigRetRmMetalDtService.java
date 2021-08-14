package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmMetalDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetRmMetalDtRepository;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmMetalDtService;

@Service
@Repository
@Transactional
public class ConsigRetRmMetalDtService implements IConsigRetRmMetalDtService{

	@Autowired
	private IConsigRetRmMetalDtRepository consigRetRmMetalDtRepository;
	
	@Autowired
	private IConsigRetMtService consigRetMtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	

	
	@Autowired
	private IConsigRmMetalDtService consigRmMetalDtService;

	@Override
	public List<ConsigRetRmMetalDt> findByConsigRetMt(ConsigRetMt consigRetMt) {
		
		return consigRetRmMetalDtRepository.findByConsigRetMt(consigRetMt);
	}

	@Override
	public ConsigRetRmMetalDt findByUniqueId(Long uniqueId) {
		
		return consigRetRmMetalDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(ConsigRetRmMetalDt consigRetRmMetalDt) {
		
		consigRetRmMetalDtRepository.save(consigRetRmMetalDt);
	}

	@Override
	public void delete(int id) {
		
	 consigRetRmMetalDtRepository.delete(id);
	
	}

	@Override
	public ConsigRetRmMetalDt findOne(int id) {
		
		return consigRetRmMetalDtRepository.findOne(id);
	}

	@Override
	public String consigRetRmMetalDtSave(ConsigRetRmMetalDt consigRetRmMetalDt, Integer id, Integer mtId,
			Principal principal) {
		String action = "added";
		String retVal="-1";
		Date aDate = null;
		
		try {
			
			MetalTran metalTran = null;
			aDate = new java.util.Date();

			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				consigRetRmMetalDt.setCreatedBy(principal.getName());
				consigRetRmMetalDt.setCreatedDt(new java.util.Date());
				consigRetRmMetalDt.setConsigRetMt(consigRetMtService.findOne(mtId));
				consigRetRmMetalDt.setUniqueId(aDate.getTime());
				consigRetRmMetalDt.setDepartment(consigRetRmMetalDt.getDepartment());
				consigRetRmMetalDt.setPurityConv(consigRetRmMetalDt.getPurity().getPurityConv());
		//		metalInwardDt.setBalance(Math.round((invWtt*purityService.findOne(purityyId).getPurityConv())*1000.0)/1000.0);
				metalTran = new MetalTran();
				retVal = "1"; 

			} else {
				consigRetRmMetalDt.setId(id);
				consigRetRmMetalDt.setModiBy(principal.getName());
				consigRetRmMetalDt.setModiDt(new java.util.Date());
				consigRetRmMetalDt.setConsigRetMt(consigRetMtService.findOne(mtId));
				consigRetRmMetalDt.setUniqueId(aDate.getTime());
				consigRetRmMetalDt.setDepartment(consigRetRmMetalDt.getDepartment());
				consigRetRmMetalDt.setPurityConv(consigRetRmMetalDt.getPurity().getPurityConv());
				metalTran = metalTranService.RefTranIdAndTranType(id, "Inward");
				action = "updated";	
				retVal = "2"; 

			}

			consigRetRmMetalDt.setRefTranDtId(null);
			save(consigRetRmMetalDt);
			
			ConsigRetRmMetalDt consigRetRmMetalDt2 = null;

			if(action == "added"){
				consigRetRmMetalDt2 = findByUniqueId(consigRetRmMetalDt.getUniqueId());
			metalTran.setCreatedBy(consigRetRmMetalDt2.getCreatedBy());
			metalTran.setCreatedDt(consigRetRmMetalDt2.getCreatedDt());
			}else{
				consigRetRmMetalDt2 = findOne(id);
				metalTran.setModiBy(principal.getName());
				metalTran.setModiDt(new java.util.Date());
			}
			
			/*
			 * Double mtlRate =
			 * metalTranService.getMetalRate(consigRetRmMetalDt2.getPurity().getId(),
			 * consigRetRmMetalDt2.getColor().getId(),
			 * consigRetRmMetalDt2.getDepartment().getId(),
			 * consigRetRmMetalDt2.getMetalWt());
			 */
			
			//metalTran.setTranDate(new java.util.Date());
			metalTran.setColor(consigRetRmMetalDt2.getColor());
			metalTran.setPurity(consigRetRmMetalDt2.getPurity());
			metalTran.setInOutFld("C");
			metalTran.setBalance(consigRetRmMetalDt2.getMetalWt());
			metalTran.setMetalWt(consigRetRmMetalDt2.getMetalWt());
			metalTran.setDeptLocation(consigRetRmMetalDt2.getDepartment());
			metalTran.setPurityConv(consigRetRmMetalDt2.getPurityConv());
			metalTran.setRefTranId(consigRetRmMetalDt2.getId());
			metalTran.setTranType("ConsigRetRmMetal");
			metalTran.setTranDate(consigRetRmMetalDt2.getConsigRetMt().getInvDate());
			metalTran.setMetalRate(consigRetRmMetalDt.getRate());
			metalTranService.save(metalTran);
			
						
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String consigRetRmMetalDtDelete(Integer id, Principal principal) {
		String retVal = "-1";

		try {
			
			
			Boolean deleteFlg=false;
			
			ConsigRetRmMetalDt consigRetRmMetalDt = findOne(id);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date cDate =  consigRetRmMetalDt.getConsigRetMt().getInvDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);
			
			if(dbDate.equals(curDate)){
				
				deleteFlg =true;
				
			}else {
				
				User user = userService.findByName(principal.getName());
				UserRole userRole = userRoleService.findByUser(user);
				if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
						|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
					
					
					deleteFlg =true;
					
				}
				
			}
			
			
			if(deleteFlg.equals(true)) {
			List<MetalTran> metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(id, "ConsigRetRmMetal", false);
			for (MetalTran metTran : metalTran) {
				metalTranService.delete(metTran.getId());
			}
			
			if(consigRetRmMetalDt.getRefTranDtId() != null) {
				ConsigRmMetalDt consigRmMetalDt = consigRmMetalDtService.findOne(consigRetRmMetalDt.getRefTranDtId());
				consigRmMetalDt.setBalance(Math.round((consigRmMetalDt.getBalance() + consigRetRmMetalDt.getMetalWt())*1000.0)/1000.0);
				consigRmMetalDt.setAdjWt(Math.round((consigRmMetalDt.getAdjWt() - consigRetRmMetalDt.getMetalWt())*1000.0)/1000.0);
				consigRmMetalDtService.save(consigRmMetalDt);
			}
			
			delete(id);
			retVal = "1";
			}else {
				return "Can Not Delete BackDate Entry";
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public String consigRetRmMetalDtListing(Integer mtId,String disableFlg) {
		StringBuilder sb = new StringBuilder();
		
		
		List<ConsigRetRmMetalDt> consigRetRmMetalDts = findByConsigRetMt(consigRetMtService.findOne(mtId));
		sb.append("{\"total\":").append(consigRetRmMetalDts.size()).append(",\"rows\": [");
		
		for (ConsigRetRmMetalDt consigRetRmMetalDt : consigRetRmMetalDts) {
			sb.append("{\"id\":\"")
					.append(consigRetRmMetalDt.getId())
					.append("\",\"department\":\"")
					.append((consigRetRmMetalDt.getDepartment() != null ? consigRetRmMetalDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((consigRetRmMetalDt.getMetal() != null ? consigRetRmMetalDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((consigRetRmMetalDt.getPurity() != null ? consigRetRmMetalDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((consigRetRmMetalDt.getColor() != null ? consigRetRmMetalDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(consigRetRmMetalDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(consigRetRmMetalDt.getRate())
					.append("\",\"adjQty\":\"")
					.append(consigRetRmMetalDt.getAdjWt())
					.append("\",\"amount\":\"")
					.append(consigRetRmMetalDt.getAmount())
					.append("\",\"in999\":\"")
					.append(consigRetRmMetalDt.getIn999())
					.append("\",\"remark\":\"")
					.append((consigRetRmMetalDt.getRemark() != null ? consigRetRmMetalDt.getRemark() : ""));
					
			if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editConsigRetRmMetalDt(")
					.append(consigRetRmMetalDt.getId());
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					sb.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteConsigRetRmMetalDt(event,")
							.append(consigRetRmMetalDt.getId()).append(", 0);' href='javascript:void(0);'");
					
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(consigRetRmMetalDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					sb.append("\"},");
			}else {
				 sb.append("\",\"actionLock\":\"")
					.append("")
					.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					 .append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	@Override
	public String consigMetalRowDataPickup(Integer pMtId, String data, Principal principal) {

		String retVal="-1";
		ConsigRetMt consigRetMt = consigRetMtService.findOne(pMtId);
		JSONArray jsonMetalDt = new JSONArray(data);
		
		try {
			
			

			for (int y = 0; y < jsonMetalDt.length(); y++) {
				
				JSONObject dataMtlDt = (JSONObject) jsonMetalDt.get(y);
				
				ConsigRmMetalDt consigRmMetalDt = consigRmMetalDtService.findOne(Integer.parseInt(dataMtlDt.get("id").toString()));
				
				if(Double.parseDouble(dataMtlDt.get("issueWt").toString()) > Double.parseDouble(dataMtlDt.get("balanceWt").toString())) {
					return "IssueWt greater than balanceWt";
				}else if(Double.parseDouble(dataMtlDt.get("issueWt").toString())<= 0.0 ) {
					return "Can not transfer zero weight";
				}
				else {
					
					MetalTran metalTran = new MetalTran();
					Date aDate  = new java.util.Date();
					
					ConsigRetRmMetalDt consigRetRmMetalDt = new ConsigRetRmMetalDt();
					consigRetRmMetalDt.setPurity(consigRmMetalDt.getPurity());
					consigRetRmMetalDt.setDepartment(consigRmMetalDt.getDepartment());
					consigRetRmMetalDt.setColor(consigRmMetalDt.getColor());
					consigRetRmMetalDt.setMetal(consigRmMetalDt.getMetal());
					consigRetRmMetalDt.setMetalWt((Double.parseDouble(dataMtlDt.get("issueWt").toString())));
					consigRetRmMetalDt.setRate(consigRmMetalDt.getRate());
					consigRetRmMetalDt.setAmount((Math.round(consigRmMetalDt.getRate() * Double.parseDouble(dataMtlDt.get("issueWt").toString())*100.0)/100.0));
					consigRetRmMetalDt.setCreatedBy(principal.getName());
					consigRetRmMetalDt.setCreatedDt(new Date());
					consigRetRmMetalDt.setConsigRetMt(consigRetMt);
					consigRetRmMetalDt.setRefTranDtId(consigRmMetalDt.getId());
					consigRetRmMetalDt.setRate(consigRmMetalDt.getRate());
					consigRetRmMetalDt.setUniqueId(aDate.getTime());
					consigRetRmMetalDt.setPurityConv(consigRmMetalDt.getPurity().getPurityConv());
					save(consigRetRmMetalDt);
					
					Double balwt = consigRmMetalDt.getAdjWt() +Double.parseDouble(dataMtlDt.get("issueWt").toString());
					
					consigRmMetalDt.setBalance(Math.round((consigRmMetalDt.getMetalWt() - balwt)*1000.0)/1000.0);
					consigRmMetalDt.setAdjWt(Math.round((consigRmMetalDt.getAdjWt() +Double.parseDouble(dataMtlDt.get("issueWt").toString()))*1000.0)/1000.0);
					consigRmMetalDtService.save(consigRmMetalDt);
					
					retVal ="1";
					
					
					
					ConsigRetRmMetalDt consigRetRmMetalDt2 = null;

				
					consigRetRmMetalDt2 = findByUniqueId(consigRetRmMetalDt.getUniqueId());
					metalTran.setCreatedBy(consigRetRmMetalDt2.getCreatedBy());
					metalTran.setCreatedDt(consigRetRmMetalDt2.getCreatedDt());
					metalTran.setColor(consigRetRmMetalDt2.getColor());
					metalTran.setPurity(consigRetRmMetalDt2.getPurity());
					metalTran.setInOutFld("C");
					metalTran.setBalance(consigRetRmMetalDt2.getMetalWt());
					metalTran.setMetalWt(consigRetRmMetalDt2.getMetalWt());
					metalTran.setDeptLocation(consigRetRmMetalDt2.getDepartment());
					metalTran.setPurityConv(consigRetRmMetalDt2.getPurityConv());
					metalTran.setRefTranId(consigRetRmMetalDt2.getId());
					metalTran.setTranType("ConsigRetRmMetal");
					metalTran.setTranDate(consigRetRmMetalDt2.getConsigRetMt().getInvDate());
					metalTran.setMetalRate(consigRetRmMetalDt.getRate());
					metalTranService.save(metalTran);
					
				}
				
			
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return retVal;
	}
	
	
	
}

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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmCompDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetRmCompDtRepository;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmCompDtService;

@Service
@Repository
@Transactional
public class ConsigRetRmCompDtService implements IConsigRetRmCompDtService{

	@Autowired
	private IConsigRetRmCompDtRepository consigRetRmCompDtRepository;
	
	@Autowired
	private IConsigRetMtService consigRetMtService;

	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IConsigRmCompDtService consigRmCompDtService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<ConsigRetRmCompDt> findByConsigRetMt(ConsigRetMt consigRetMt) {
		// TODO Auto-generated method stub
		return consigRetRmCompDtRepository.findByConsigRetMt(consigRetMt);
	}

	@Override
	public ConsigRetRmCompDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return consigRetRmCompDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(ConsigRetRmCompDt consigRetRmCompDt) {
		// TODO Auto-generated method stub
		consigRetRmCompDtRepository.save(consigRetRmCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigRetRmCompDtRepository.delete(id);
		
	}

	@Override
	public ConsigRetRmCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigRetRmCompDtRepository.findOne(id);
	}

	@Override
	public String consigRetRmCompDtSave(ConsigRetRmCompDt consigRetRmCompDt, Integer id, Integer mtId,
			Principal principal) {

		String action = "added";
		String retVal="-1";
		
		ConsigRetMt consigRetMt = consigRetMtService.findOne(mtId);
		try {
			
					
			CompTran compTran = null;

			if (id == null || id.equals("") || (id != null && id == 0)) {
				consigRetRmCompDt.setCreatedBy(principal.getName());
				consigRetRmCompDt.setCreatedDt(new java.util.Date());
				consigRetRmCompDt.setUniqueId(new Date().getTime());
				consigRetRmCompDt.setPurityConv(consigRetRmCompDt.getPurity().getPurityConv());
				consigRetRmCompDt.setBalance(consigRetRmCompDt.getMetalWt());
				compTran = new CompTran();
				retVal = "1";
			} else {
				
				consigRetRmCompDt.setModiBy(principal.getName());
				consigRetRmCompDt.setModiDt(new java.util.Date());
				consigRetRmCompDt.setPurityConv(consigRetRmCompDt.getPurity().getPurityConv());
				consigRetRmCompDt.setBalance(consigRetRmCompDt.getMetalWt());
			
				compTran = compTranService.RefTranIdAndTranType(id, "ConsigRetRmComp");
				
				action = "updated";	
				retVal = "2";
			}

			consigRetRmCompDt.setConsigRetMt(consigRetMt);
			consigRetRmCompDt.setRefTranDtId(null);

			save(consigRetRmCompDt);
			
			ConsigRetRmCompDt consigRetRmCompDt2 = null;
			if(action == "added"){
				consigRetRmCompDt2=findByUniqueId(consigRetRmCompDt.getUniqueId());
				compTran.setCreatedBy(consigRetRmCompDt2.getCreatedBy());
				compTran.setCreatedDt(consigRetRmCompDt2.getCreatedDt());
				compTran.setTrandate(consigRetMt.getInvDate());
			}else{
				consigRetRmCompDt2=findOne(id);
				compTran.setModiBy(principal.getName());
				compTran.setModiDt(new java.util.Date());
			}
			
			
			Double compMtlRate=compTranService.getCompMetalRate(consigRetRmCompDt2.getPurity().getId(), consigRetRmCompDt2.getColor().getId(), consigRetRmCompDt2.getDepartment().getId(), 
					consigRetRmCompDt2.getComponent().getId(),consigRetRmCompDt2.getMetalWt());
			
			compTran.setColor(consigRetRmCompDt2.getColor());
			compTran.setPurity(consigRetRmCompDt2.getPurity());
			compTran.setInOutFld("C");
			compTran.setBagMt(null);
			compTran.setBalance(consigRetRmCompDt2.getMetalWt());
			compTran.setMetalWt(consigRetRmCompDt2.getMetalWt());
			compTran.setDeptLocation(consigRetRmCompDt2.getDepartment());
			compTran.setPurityConv(consigRetRmCompDt2.getPurityConv());
			compTran.setRefTranId(consigRetRmCompDt2.getId());
			compTran.setTranType("ConsigRetRmComp");
			compTran.setRemark("");
			compTran.setDepartment(null);
			compTran.setComponent(consigRetRmCompDt2.getComponent());
			
			compTran.setPcs(consigRetRmCompDt2.getQty());
			compTran.setBalancePcs(consigRetRmCompDt2.getQty());
			compTran.setMetalRate(compMtlRate);
			compTranService.save(compTran);
			
			
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String consigRetRmCompDtDelete(Integer id, Principal principal) {
		String retVal ="-1";
		
		try {
			
			
			
			Boolean deleteFlg=false;
			
			ConsigRetRmCompDt consigRetRmCompDt = findOne(id);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date cDate =  consigRetRmCompDt.getConsigRetMt().getInvDate();
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
			List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(id, "ConsigRetRmComp");
			for (CompTran comTran : compTran) {
				compTranService.delete(comTran.getId());
			}
			
			if(consigRetRmCompDt.getRefTranDtId() != null) {
			
			ConsigRmCompDt consigRmCompDt = consigRmCompDtService.findOne(consigRetRmCompDt.getRefTranDtId());
			consigRmCompDt.setBalance(Math.round((consigRmCompDt.getBalance() + consigRetRmCompDt.getMetalWt())*1000.0)/1000.0);
			consigRmCompDt.setAdjWt(Math.round((consigRmCompDt.getAdjWt() - consigRetRmCompDt.getMetalWt())*1000.0)/1000.0);
			
			consigRmCompDt.setBalanceQty(Math.round((consigRmCompDt.getBalanceQty() + consigRetRmCompDt.getQty())*100.0)/100.0);
			consigRmCompDt.setAdjQty(Math.round((consigRmCompDt.getAdjQty() - consigRetRmCompDt.getQty())*1000.0)/1000.0);
			
			consigRmCompDtService.save(consigRmCompDt);
			
			}
			
			delete(id);
			
			retVal ="1";
			}else {
				return "Can Not Delete BackDate Entry";
			}	
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public String consigRetRmCompDtListing(Integer mtId,String disableFlg) {
		
		StringBuilder sb = new StringBuilder();
		
		ConsigRetMt consigRetMt = consigRetMtService.findOne(mtId);

		List<ConsigRetRmCompDt> consigRetRmCompDts= findByConsigRetMt(consigRetMt);
		
		sb.append("{\"total\":").append(consigRetRmCompDts.size()).append(",\"rows\": [");
		
		for (ConsigRetRmCompDt consigRetRmCompDt : consigRetRmCompDts) {
			sb.append("{\"id\":\"")
					.append(consigRetRmCompDt.getId())
					.append("\",\"metal\":\"")
					.append((consigRetRmCompDt.getMetal() != null ? consigRetRmCompDt.getMetal().getName() : ""))
					.append("\",\"component\":\"")
					.append((consigRetRmCompDt.getComponent() != null ? consigRetRmCompDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((consigRetRmCompDt.getPurity() != null ? consigRetRmCompDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((consigRetRmCompDt.getColor() != null ? consigRetRmCompDt.getColor().getName() : ""))
					.append("\",\"department\":\"")
					.append((consigRetRmCompDt.getDepartment() != null ? consigRetRmCompDt.getDepartment().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(consigRetRmCompDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(consigRetRmCompDt.getRate())
					.append("\",\"amount\":\"")
					.append(consigRetRmCompDt.getAmount())
					.append("\",\"qty\":\"")
					.append(consigRetRmCompDt.getQty());
								
			if(disableFlg.equalsIgnoreCase("false")) {
			
					sb.append("\",\"action1\":\"")
					
					.append("<a href='javascript:editConsigRetRmCompDt(").append(consigRetRmCompDt.getId())
					.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteConsigRetRmCompDt(event,")
					  .append(consigRetRmCompDt.getId()).append(", 0);' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(consigRetRmCompDt.getId())
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
	public String consigCompRowDataPickup(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		String retVal="-1";
		ConsigRetMt consigRetMt = consigRetMtService.findOne(pMtId);
		JSONArray jsonComponentDt = new JSONArray(data);
		
		try {
			
			

			for (int y = 0; y < jsonComponentDt.length(); y++) {
				
				JSONObject dataCompDt = (JSONObject) jsonComponentDt.get(y);
				
				CompTran compTran = new CompTran();
				
				ConsigRmCompDt consigRmCompDt = consigRmCompDtService.findOne(Integer.parseInt(dataCompDt.get("id").toString()));
				
				if(Double.parseDouble(dataCompDt.get("issueWt").toString()) > Double.parseDouble(dataCompDt.get("balanceWt").toString())) {
					return "IssueWt greater than balanceWt";
				}else if(Double.parseDouble(dataCompDt.get("issueWt").toString())<= 0.0 ) {
					return "Can not transfer zero weight";
				}
				else {
					
					ConsigRetRmCompDt consigRetRmCompDt = new ConsigRetRmCompDt();
					consigRetRmCompDt.setPurity(consigRmCompDt.getPurity());
					consigRetRmCompDt.setDepartment(consigRmCompDt.getDepartment());
					consigRetRmCompDt.setColor(consigRmCompDt.getColor());
					consigRetRmCompDt.setMetal(consigRmCompDt.getMetal());
					consigRetRmCompDt.setComponent(consigRmCompDt.getComponent());
					consigRetRmCompDt.setMetalWt((Double.parseDouble(dataCompDt.get("issueWt").toString())));
					consigRetRmCompDt.setRate(consigRmCompDt.getRate());
					consigRetRmCompDt.setAmount((Math.round(consigRmCompDt.getRate() * Double.parseDouble(dataCompDt.get("issueWt").toString())*100.0)/100.0));
					consigRetRmCompDt.setCreatedBy(principal.getName());
					consigRetRmCompDt.setCreatedDt(new Date());
					consigRetRmCompDt.setConsigRetMt(consigRetMt);
					consigRetRmCompDt.setQty(Double.parseDouble(dataCompDt.get("issueQty").toString()));
					consigRetRmCompDt.setRefTranDtId(consigRmCompDt.getId());
					consigRetRmCompDt.setRate(consigRmCompDt.getRate());
					consigRetRmCompDt.setUniqueId(new Date().getTime());
					
					
					save(consigRetRmCompDt);
					
					Double balwt = consigRmCompDt.getAdjWt() +Double.parseDouble(dataCompDt.get("issueWt").toString());
					Double balQty = consigRmCompDt.getAdjQty() +Double.parseDouble(dataCompDt.get("issueQty").toString());
					
					consigRmCompDt.setBalance(Math.round((consigRmCompDt.getMetalWt() - balwt)*1000.0)/1000.0);
					consigRmCompDt.setAdjWt(Math.round((consigRmCompDt.getAdjWt() +Double.parseDouble(dataCompDt.get("issueWt").toString()))*1000.0)/1000.0);
					
					consigRmCompDt.setBalanceQty(Math.round((consigRmCompDt.getQty() - balQty)*10.0)/10.0);
					consigRmCompDt.setAdjQty(Math.round((consigRmCompDt.getAdjQty() +Double.parseDouble(dataCompDt.get("issueQty").toString()))*10.0)/10.0);
					consigRmCompDtService.save(consigRmCompDt);
					
					
				
					//Comptran
					ConsigRetRmCompDt consigRetRmCompDt2=findByUniqueId(consigRetRmCompDt.getUniqueId());
				
					compTran.setCreatedBy(consigRetRmCompDt2.getCreatedBy());
						compTran.setCreatedDt(consigRetRmCompDt2.getCreatedDt());
						compTran.setTrandate(consigRetMt.getInvDate());
					
					Double compMtlRate=compTranService.getCompMetalRate(consigRetRmCompDt2.getPurity().getId(), consigRetRmCompDt2.getColor().getId(), consigRetRmCompDt2.getDepartment().getId(), 
							consigRetRmCompDt2.getComponent().getId(),consigRetRmCompDt2.getMetalWt());
					
					compTran.setColor(consigRetRmCompDt2.getColor());
					compTran.setPurity(consigRetRmCompDt2.getPurity());
					compTran.setInOutFld("C");
					compTran.setBagMt(null);
					compTran.setBalance(consigRetRmCompDt2.getMetalWt());
					compTran.setMetalWt(consigRetRmCompDt2.getMetalWt());
					compTran.setDeptLocation(consigRetRmCompDt2.getDepartment());
					compTran.setPurityConv(consigRetRmCompDt2.getPurityConv());
					compTran.setRefTranId(consigRetRmCompDt2.getId());
					compTran.setTranType("ConsigRetRmComp");
					compTran.setRemark("");
					compTran.setDepartment(null);
					compTran.setComponent(consigRetRmCompDt2.getComponent());
					
					compTran.setPcs(consigRetRmCompDt2.getQty());
					compTran.setBalancePcs(consigRetRmCompDt2.getQty());
					compTran.setMetalRate(compMtlRate);
					compTranService.save(compTran);
					
					retVal ="1";
				}
				
			
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return retVal;
	}
	
	
}

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetRmStnDtRepository;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmStnDtService;

@Service
@Repository
@Transactional
public class ConsigRetRmStnDtService implements IConsigRetRmStnDtService {

	@Autowired
	private IConsigRetRmStnDtRepository consigRetRmStnDtRepository;

	@Autowired
	private IConsigRetMtService consigRetMtService;

	@Autowired
	private IStoneTranService stoneTranService;

	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private IConsigRmStnDtService consigRmStnDtService;
	
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;

	@Override
	public List<ConsigRetRmStnDt> findByConsigRetMt(ConsigRetMt consigRetMt) {
		
		return consigRetRmStnDtRepository.findByConsigRetMt(consigRetMt);
	}

	@Override
	public ConsigRetRmStnDt findByUniqueId(Long uniqueId) {
		
		return consigRetRmStnDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(ConsigRetRmStnDt consigRetRmStnDt) {
		
		consigRetRmStnDtRepository.save(consigRetRmStnDt);
	}

	@Override
	public void delete(int id) {
		
		consigRetRmStnDtRepository.delete(id);
	}

	@Override
	public ConsigRetRmStnDt findOne(int id) {
	
		return consigRetRmStnDtRepository.findOne(id);
	}

	@Override
	public String consigRetRmStnDtSave(ConsigRetRmStnDt consigRetRmStnDt, Integer id, Integer mtId,
			Principal principal) {
		String action = "added";
		String retVal = "-1";

		StoneTran stoneTran = null;
		ConsigRetMt consigRetMt = consigRetMtService.findOne(mtId);
		StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(consigRetRmStnDt.getShape(),
				consigRetRmStnDt.getSize(), false);
		if (id == null || id.equals("") || (id != null && id == 0)) {
			consigRetRmStnDt.setCreatedBy(principal.getName());
			consigRetRmStnDt.setCreatedDt(new java.util.Date());

			consigRetRmStnDt.setUniqueId(new Date().getTime());
			consigRetRmStnDt.setBalCarat(consigRetRmStnDt.getCarat());
			consigRetRmStnDt.setBalStone(consigRetRmStnDt.getStone());
			stoneTran = new StoneTran();
			retVal = "1";

		} else {

			if (consigRetRmStnDt.getBalCarat() > consigRetRmStnDt.getCarat()) {
				return retVal = "-11";
			}

			consigRetRmStnDt.setModiBy(principal.getName());
			consigRetRmStnDt.setModiDt(new java.util.Date());
			consigRetRmStnDt.setBalCarat(consigRetRmStnDt.getCarat());
			consigRetRmStnDt.setBalStone(consigRetRmStnDt.getStone());
			consigRetRmStnDt.setDepartment(consigRetRmStnDt.getDepartment());
			stoneTran = stoneTranService.RefTranIdAndTranType(id, "ConsigRetRmStn");
			action = "updated";
			retVal = "2";

		}

		if (consigRetRmStnDt.getSubShape().getId() == null) {
			consigRetRmStnDt.setSubShape(null);
		}

		consigRetRmStnDt.setConsigRetMt(consigRetMt);
		consigRetRmStnDt.setSieve(stoneChart.getSieve());
		consigRetRmStnDt.setSizeGroup(stoneChart.getSizeGroup());
		consigRetRmStnDt.setRefTranDtId(null);
		save(consigRetRmStnDt);

		ConsigRetRmStnDt consigRetRmStnDt2 = null;

		if (action == "added") {
			consigRetRmStnDt2 = findByUniqueId(consigRetRmStnDt.getUniqueId());
			stoneTran.setCreatedBy(consigRetRmStnDt2.getCreatedBy());
			stoneTran.setCreatedDt(new Date());
		} else {
			consigRetRmStnDt2 = findOne(id);
			stoneTran.setModiBy(principal.getName());
			stoneTran.setModiDt(new java.util.Date());
		}

		if (stoneTran != null) {

			stoneTran.setTranDate(consigRetMt.getInvDate());
			stoneTran.setDepartment(consigRetRmStnDt2.getDepartment());
			stoneTran.setDeptLocation(consigRetRmStnDt2.getDepartment());
			stoneTran.setStoneType(consigRetRmStnDt2.getStoneType());
			stoneTran.setShape(consigRetRmStnDt2.getShape());
			stoneTran.setQuality(consigRetRmStnDt2.getQuality());
			stoneTran.setSubShape(consigRetRmStnDt2.getSubShape());
			stoneTran.setSize(consigRetRmStnDt2.getSize());
			stoneTran.setSieve(consigRetRmStnDt2.getSieve());
			stoneTran.setSizeGroup(consigRetRmStnDt2.getSizeGroup());
			stoneTran.setStone(consigRetRmStnDt2.getStone());
			stoneTran.setCarat(consigRetRmStnDt2.getCarat()); // -------------
			stoneTran.setBalStone(consigRetRmStnDt2.getBalStone());
			stoneTran.setBalCarat(consigRetRmStnDt2.getCarat()); // --------------
			stoneTran.setRate(consigRetRmStnDt2.getRate());
			stoneTran.setInOutFld("C");
			stoneTran.setBagMt(null);
			stoneTran.setBagSrNo(0);
			stoneTran.setSettingType(null);
			stoneTran.setSetting(null);
			stoneTran.setPacketNo(consigRetRmStnDt2.getPacketNo());
			stoneTran.setRemark(consigRetRmStnDt2.getRemark());
			stoneTran.setParty(consigRetMt.getParty());
			stoneTran.setTranType("ConsigRetRmStn");
			stoneTran.setRefTranId(consigRetRmStnDt2.getId());
			
			stoneTranService.save(stoneTran);

		}

		return retVal;
	}

	@Override
	public String consigRetRmStnDtDelete(Integer id, Principal principal) {
		String retVal = "-1";

		Boolean deleteFlg=false;
				
			ConsigRetRmStnDt consigRetRmStnDt = findOne(id);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date cDate =  consigRetRmStnDt.getConsigRetMt().getInvDate();
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
				
				StoneTran stoneTran = stoneTranService.RefTranIdAndTranType(id, "ConsigRetRmStn");
				stoneTranService.delete(stoneTran.getId());
				
				if(consigRetRmStnDt.getRefTranDtId() != null) {
					ConsigRmStnDt consigRmStnDt = consigRmStnDtService.findOne(consigRetRmStnDt.getRefTranDtId());
					
					consigRmStnDt.setBalCarat(Math.round((consigRmStnDt.getBalCarat() + consigRetRmStnDt.getCarat())*1000.0)/1000.0);
					consigRmStnDt.setBalStone(consigRmStnDt.getBalStone() + consigRetRmStnDt.getStone());
					consigRmStnDt.setAdjWt(Math.round((consigRmStnDt.getAdjWt() - consigRetRmStnDt.getCarat())*1000.0)/1000.0);
					consigRmStnDtService.save(consigRmStnDt);
				}
				delete(id);
				
				retVal = "1";
				
				
			}else {
				return "Can Not Delete BackDate Entry";
			}


		

		return retVal;
	}

	@Override
	public String consigRetRmStnDtListing(Integer mtId,String disableFlg,Principal principal) {

		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		
		StringBuilder sb = new StringBuilder();
		ConsigRetMt consigRetMt = consigRetMtService.findOne(mtId);
		
		List<ConsigRetRmStnDt> consigRetRmStnDts = findByConsigRetMt(consigRetMt);
		
		sb.append("{\"total\":").append(consigRetRmStnDts.size()).append(",\"rows\": [");
			
		if(consigRetRmStnDts.size() > 0){
				for (ConsigRetRmStnDt consigRetRmStnDt : consigRetRmStnDts) {
					sb.append("{\"id\":\"")
							.append(consigRetRmStnDt.getId())
							.append("\",\"stoneType\":\"")
							.append((consigRetRmStnDt.getStoneType() != null ? consigRetRmStnDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((consigRetRmStnDt.getShape() != null ? consigRetRmStnDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((consigRetRmStnDt.getSubShape() != null ? consigRetRmStnDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((consigRetRmStnDt.getQuality() != null ? consigRetRmStnDt.getQuality().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((consigRetRmStnDt.getSize() != null ? consigRetRmStnDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(consigRetRmStnDt.getSieve())	
							.append("\",\"department\":\"")
							.append((consigRetRmStnDt.getDepartment() != null ? consigRetRmStnDt.getDepartment().getName() : ""))					
							.append("\",\"sizeGroupStr\":\"")
							.append((consigRetRmStnDt.getSizeGroup() != null ? consigRetRmStnDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(consigRetRmStnDt.getStone())
							.append("\",\"balStone\":\"")
							.append(consigRetRmStnDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(consigRetRmStnDt.getCarat())
							.append("\",\"diffCarat\":\"")
							.append(consigRetRmStnDt.getDiffCarat())
							.append("\",\"balCarat\":\"")
							.append(consigRetRmStnDt.getBalCarat())
							.append("\",\"rate\":\"")
							.append(consigRetRmStnDt.getRate())
							.append("\",\"amount\":\"")
							.append(consigRetRmStnDt.getAmount())	
							.append("\",\"packetNo\":\"")
							.append(consigRetRmStnDt.getPacketNo() !=null ? consigRetRmStnDt.getPacketNo() : ""  )	
							.append("\",\"remark\":\"")
							.append((consigRetRmStnDt.getRemark() != null ? consigRetRmStnDt.getRemark() : ""));
									
						if(disableFlg.equalsIgnoreCase("false") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
								|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
							sb.append("\",\"action1\":\"");
						
							sb.append("<a href='javascript:editConsigRetRmStnDt(")
							.append(consigRetRmStnDt.getId());
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
							sb.append("\",\"action2\":\"");
							sb.append("<a href='javascript:deleteConsigRetRmStnDt(event,")
							  .append(consigRetRmStnDt.getId()).append(", 0);' href='javascript:void(0);'");
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(consigRetRmStnDt.getId())
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
				
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@Override
	public String consigStoneRowDataPickup(Integer pMtId, String data, Principal principal) {
		
		
		String retVal="-1";
		ConsigRetMt consigRetMt = consigRetMtService.findOne(pMtId);
		JSONArray jsonStoneDt = new JSONArray(data);
		
		try {
			
			

			for (int y = 0; y < jsonStoneDt.length(); y++) {
				
				JSONObject dataStnDt = (JSONObject) jsonStoneDt.get(y);
				
				ConsigRmStnDt consigRmStnDt = consigRmStnDtService.findOne(Integer.parseInt(dataStnDt.get("id").toString()));
				
				if(Double.parseDouble(dataStnDt.get("issueStone").toString()) > Double.parseDouble(dataStnDt.get("balStone").toString())) {
					return "-2";
				}else if(Double.parseDouble(dataStnDt.get("issueCarat").toString()) > Double.parseDouble(dataStnDt.get("balCarat").toString())) {
					return "-3";
				}
				else {
					
					ConsigRetRmStnDt consigRetRmStnDt = new ConsigRetRmStnDt();
					consigRetRmStnDt.setStone(consigRmStnDt.getStone());
					consigRetRmStnDt.setDepartment(consigRmStnDt.getDepartment());
					consigRetRmStnDt.setStoneType(consigRmStnDt.getStoneType());
					consigRetRmStnDt.setShape(consigRmStnDt.getShape());
					consigRetRmStnDt.setSubShape(consigRmStnDt.getSubShape());
					consigRetRmStnDt.setQuality(consigRmStnDt.getQuality());
					consigRetRmStnDt.setSize(consigRmStnDt.getSize());
					consigRetRmStnDt.setSieve(consigRmStnDt.getSieve());
					consigRetRmStnDt.setSizeGroup(consigRmStnDt.getSizeGroup());
					consigRetRmStnDt.setStone(Integer.parseInt(dataStnDt.get("issueStone").toString()));
					consigRetRmStnDt.setCarat(Double.parseDouble(dataStnDt.get("issueCarat").toString()));
					consigRetRmStnDt.setRate(consigRmStnDt.getStnRate());
					consigRetRmStnDt.setRate1(consigRmStnDt.getStnRate1());
					consigRetRmStnDt.setAmount((Math.round(consigRmStnDt.getStnRate() * Double.parseDouble(dataStnDt.get("issueCarat").toString())*100.0)/100.0));
					consigRetRmStnDt.setCreatedBy(principal.getName());
					consigRetRmStnDt.setCreatedDt(new Date());
					consigRetRmStnDt.setConsigRetMt(consigRetMt);
					consigRetRmStnDt.setRefTranDtId(consigRmStnDt.getId());
					save(consigRetRmStnDt);
					
					consigRmStnDt.setBalStone(consigRmStnDt.getBalStone() - Integer.parseInt(dataStnDt.get("issueStone").toString()));
					consigRmStnDt.setBalCarat(Math.round((consigRmStnDt.getBalCarat() - Double.parseDouble(dataStnDt.get("issueCarat").toString()))*1000.0)/1000.0);
					consigRmStnDt.setAdjWt(Math.round((consigRmStnDt.getAdjWt() + Double.parseDouble(dataStnDt.get("issueCarat").toString()))*1000.0)/1000.0);
					consigRmStnDtService.save(consigRmStnDt);
					
					
					
					StoneTran stoneTran = new StoneTran();
					stoneTran.setCreatedBy(principal.getName());
					stoneTran.setCreatedDt(new Date());
					
					stoneTran.setTranDate(consigRetMt.getInvDate()); 
					stoneTran.setDepartment(null);
					stoneTran.setDeptLocation(consigRmStnDt.getDepartment());
					stoneTran.setStoneType(consigRetRmStnDt.getStoneType());
					stoneTran.setShape(consigRetRmStnDt.getShape());
					stoneTran.setQuality(consigRetRmStnDt.getQuality());
					stoneTran.setSubShape(consigRetRmStnDt.getSubShape());
					stoneTran.setSize(consigRetRmStnDt.getSize());
					stoneTran.setSieve(consigRetRmStnDt.getSieve());
					stoneTran.setSizeGroup(consigRetRmStnDt.getSizeGroup());
					stoneTran.setStone(consigRetRmStnDt.getStone());
					stoneTran.setCarat(consigRetRmStnDt.getCarat());		//-------------
					stoneTran.setBalStone(consigRetRmStnDt.getStone());
					stoneTran.setBalCarat(consigRetRmStnDt.getCarat()); //--------------
					stoneTran.setRate(consigRetRmStnDt.getRate());
					stoneTran.setInOutFld("C");
					stoneTran.setBagMt(null);;
					stoneTran.setBagSrNo(0);
					stoneTran.setSettingType(null);
					stoneTran.setSetting(null);
					stoneTran.setPacketNo(consigRetRmStnDt.getPacketNo());
					stoneTran.setRemark(consigRetRmStnDt.getRemark());
					stoneTran.setParty(consigRetMt.getParty());
					stoneTran.setTranType("ConsigRetRmStn");
					stoneTran.setRefTranId(consigRetRmStnDt.getId());

					stoneTran.setLotNo("CongRet-"+consigRmStnDt.getId());
					
					stoneTranService.save(stoneTran);
					
					
					
					retVal ="1";
				}
				
			
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return retVal;
	}

}

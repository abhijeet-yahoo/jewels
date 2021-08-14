package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetRmStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmStnDtService;

@Service
@Repository
@Transactional
public class SaleRetRmStnDtService implements ISaleRetRmStnDtService {

	@Autowired
	private ISaleRetRmStnDtRepository saleRetRmStnDtRepository;
	
	@Autowired
	private ISaleRetMtService saleRetMtService;
	
	@Autowired
	private IStoneTranService stoneTranService;

	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private ISaleRmStnDtService saleRmStnDtService;

	@Override
	public List<SaleRetRmStnDt> findBySaleRetMt(SaleRetMt saleRetMt) {
		// TODO Auto-generated method stub
		return saleRetRmStnDtRepository.findBySaleRetMt(saleRetMt);
	}

	@Override
	public SaleRetRmStnDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return saleRetRmStnDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(SaleRetRmStnDt saleRetRmStnDt) {
		// TODO Auto-generated method stub
		saleRetRmStnDtRepository.save(saleRetRmStnDt);
	}

	@Override
	public void delete(int id) {
		 saleRetRmStnDtRepository.delete(id);
			
	}

	@Override
	public SaleRetRmStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return saleRetRmStnDtRepository.findOne(id);
	}

	@Override
	public String saleRetRmStnDtSave(SaleRetRmStnDt saleRetRmStnDt, Integer id, Integer mtId, Principal principal) {
		String action = "added";
		String retVal = "-1";

		StoneTran stoneTran = null;
		SaleRetMt saleRetMt = saleRetMtService.findOne(mtId);
		StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(saleRetRmStnDt.getShape(),
				saleRetRmStnDt.getSize(), false);
		if (id == null || id.equals("") || (id != null && id == 0)) {
			saleRetRmStnDt.setCreatedBy(principal.getName());
			saleRetRmStnDt.setCreatedDt(new java.util.Date());

			saleRetRmStnDt.setUniqueId(new Date().getTime());
			saleRetRmStnDt.setBalCarat(saleRetRmStnDt.getCarat());
			saleRetRmStnDt.setBalStone(saleRetRmStnDt.getStone());
			stoneTran = new StoneTran();
			retVal = "1";

		} else {

			if (saleRetRmStnDt.getBalCarat() > saleRetRmStnDt.getCarat()) {
				return retVal = "-11";
			}

			saleRetRmStnDt.setModiBy(principal.getName());
			saleRetRmStnDt.setModiDt(new java.util.Date());
			saleRetRmStnDt.setBalCarat(saleRetRmStnDt.getCarat());
			saleRetRmStnDt.setBalStone(saleRetRmStnDt.getStone());
			saleRetRmStnDt.setDepartment(saleRetRmStnDt.getDepartment());
			stoneTran = stoneTranService.RefTranIdAndTranType(id, "SaleRetRmStn");
			action = "updated";
			retVal = "2";

		}

		if (saleRetRmStnDt.getSubShape().getId() == null) {
			saleRetRmStnDt.setSubShape(null);
		}

		saleRetRmStnDt.setSaleRetMt(saleRetMt);
		saleRetRmStnDt.setSieve(stoneChart.getSieve());
		saleRetRmStnDt.setSizeGroup(stoneChart.getSizeGroup());
		
		saleRetRmStnDt.setRefTranDtId(null);

		save(saleRetRmStnDt);

		SaleRetRmStnDt saleRetRmStnDt2 = null;

		if (action == "added") {
			saleRetRmStnDt2 = findByUniqueId(saleRetRmStnDt.getUniqueId());
			stoneTran.setCreatedBy(saleRetRmStnDt2.getCreatedBy());
			stoneTran.setCreatedDt(new Date());
		} else {
			saleRetRmStnDt2 = findOne(id);
			stoneTran.setModiBy(principal.getName());
			stoneTran.setModiDt(new java.util.Date());
		}

		if (stoneTran != null) {

			stoneTran.setTranDate(saleRetMt.getInvDate());
			stoneTran.setDepartment(saleRetRmStnDt2.getDepartment());
			stoneTran.setDeptLocation(saleRetRmStnDt2.getDepartment());
			stoneTran.setStoneType(saleRetRmStnDt2.getStoneType());
			stoneTran.setShape(saleRetRmStnDt2.getShape());
			stoneTran.setQuality(saleRetRmStnDt2.getQuality());
			stoneTran.setSubShape(saleRetRmStnDt2.getSubShape());
			stoneTran.setSize(saleRetRmStnDt2.getSize());
			stoneTran.setSieve(saleRetRmStnDt2.getSieve());
			stoneTran.setSizeGroup(saleRetRmStnDt2.getSizeGroup());
			stoneTran.setStone(saleRetRmStnDt2.getStone());
			stoneTran.setCarat(saleRetRmStnDt2.getCarat()); // -------------
			stoneTran.setBalStone(saleRetRmStnDt2.getBalStone());
			stoneTran.setBalCarat(saleRetRmStnDt2.getCarat()); // --------------
			stoneTran.setRate(saleRetRmStnDt2.getRate());
			stoneTran.setInOutFld("C");
			stoneTran.setBagMt(null);
			stoneTran.setBagSrNo(0);
			stoneTran.setSettingType(null);
			stoneTran.setSetting(null);
			stoneTran.setPacketNo(saleRetRmStnDt2.getPacketNo());
			stoneTran.setRemark(saleRetRmStnDt2.getRemark());
			stoneTran.setParty(saleRetMt.getParty());
			stoneTran.setTranType("SaleRetRmStn");
			stoneTran.setRefTranId(saleRetRmStnDt2.getId());
			
			stoneTranService.save(stoneTran);

		}

		return retVal;
	}

	@Override
	public String saleRetRmStnDtDelete(Integer id, Principal principal) {
		String retVal = "-1";

		try {
			
			SaleRetRmStnDt saleRetRmStnDt = findOne(id);
			
			StoneTran stoneTran = stoneTranService.RefTranIdAndTranType(id, "SaleRetRmStn");
			stoneTranService.delete(stoneTran.getId());
			
			if(saleRetRmStnDt.getRefTranDtId() != null) {
				SaleRmStnDt saleRmStnDt = saleRmStnDtService.findOne(saleRetRmStnDt.getRefTranDtId());
				
				saleRmStnDt.setBalCarat(saleRmStnDt.getBalCarat() + saleRetRmStnDt.getCarat());
				saleRmStnDt.setBalStone(saleRmStnDt.getBalStone() + saleRetRmStnDt.getStone());
				saleRmStnDt.setAdjWt(saleRmStnDt.getAdjWt() - saleRetRmStnDt.getCarat());
				saleRmStnDtService.save(saleRmStnDt);
			}

			delete(id);
			
			retVal = "1";

		} catch (Exception e) {
			// TODO: handle exception
		}

		return retVal;
	}

	@Override
	public String saleRetRmStnDtListing(Integer mtId,String disableFlg) {
		
		StringBuilder sb = new StringBuilder();
		SaleRetMt saleRetMt = saleRetMtService.findOne(mtId);
		
		List<SaleRetRmStnDt> saleRetRmStnDts = findBySaleRetMt(saleRetMt);
		
		sb.append("{\"total\":").append(saleRetRmStnDts.size()).append(",\"rows\": [");
			
		if(saleRetRmStnDts.size() > 0){
				for (SaleRetRmStnDt saleRetRmStnDt : saleRetRmStnDts) {
					sb.append("{\"id\":\"")
							.append(saleRetRmStnDt.getId())
							.append("\",\"stoneType\":\"")
							.append((saleRetRmStnDt.getStoneType() != null ? saleRetRmStnDt.getStoneType().getName() : ""))
							.append("\",\"shape\":\"")
							.append((saleRetRmStnDt.getShape() != null ? saleRetRmStnDt.getShape().getName() : ""))
							.append("\",\"subShape\":\"")
							.append((saleRetRmStnDt.getSubShape() != null ? saleRetRmStnDt.getSubShape().getName() : ""))	
							.append("\",\"quality\":\"")
							.append((saleRetRmStnDt.getQuality() != null ? saleRetRmStnDt.getQuality().getName() : ""))					
							.append("\",\"stoneChart\":\"")
							.append((saleRetRmStnDt.getSize() != null ? saleRetRmStnDt.getSize() : ""))
							.append("\",\"sieve\":\"")
							.append(saleRetRmStnDt.getSieve())	
							.append("\",\"department\":\"")
							.append((saleRetRmStnDt.getDepartment() != null ? saleRetRmStnDt.getDepartment().getName() : ""))					
							.append("\",\"sizeGroupStr\":\"")
							.append((saleRetRmStnDt.getSizeGroup() != null ? saleRetRmStnDt.getSizeGroup().getName() : ""))
							.append("\",\"stone\":\"")
							.append(saleRetRmStnDt.getStone())
							.append("\",\"balStone\":\"")
							.append(saleRetRmStnDt.getBalStone())
							.append("\",\"carat\":\"")
							.append(saleRetRmStnDt.getCarat())
							.append("\",\"diffCarat\":\"")
							.append(saleRetRmStnDt.getDiffCarat())
							.append("\",\"balCarat\":\"")
							.append(saleRetRmStnDt.getBalCarat())
							.append("\",\"rate\":\"")
							.append(saleRetRmStnDt.getRate())
							.append("\",\"amount\":\"")
							.append(saleRetRmStnDt.getAmount())	
							.append("\",\"packetNo\":\"")
							.append(saleRetRmStnDt.getPacketNo() !=null ? saleRetRmStnDt.getPacketNo() : ""  )	
							.append("\",\"remark\":\"")
							.append((saleRetRmStnDt.getRemark() != null ? saleRetRmStnDt.getRemark() : ""));
						
							if(disableFlg.equalsIgnoreCase("false")) {
							sb.append("\",\"action1\":\"");
							sb.append("<a href='javascript:editSaleRetRmStnDt(event,")
							.append(saleRetRmStnDt.getId()+","+saleRetRmStnDt.getRefTranDtId());
							sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
							sb.append("\",\"action2\":\"");
							sb.append("<a href='javascript:deleteSaleRetRmStnDt(event,")
							  .append(saleRetRmStnDt.getId()).append(", 0);' href='javascript:void(0);'");
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(saleRetRmStnDt.getId())
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
	public String saleStoneRowDataPickup(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		String retVal="-1";
		SaleRetMt saleRetMt = saleRetMtService.findOne(pMtId);
		JSONArray jsonStoneDt = new JSONArray(data);
		
		try {
			
			

			for (int y = 0; y < jsonStoneDt.length(); y++) {
				
				JSONObject dataStnDt = (JSONObject) jsonStoneDt.get(y);
				
				SaleRmStnDt saleRmStnDt = saleRmStnDtService.findOne(Integer.parseInt(dataStnDt.get("id").toString()));
				
				if(Double.parseDouble(dataStnDt.get("issueStone").toString()) > Double.parseDouble(dataStnDt.get("balStone").toString())) {
					return "-2";
				}else if(Double.parseDouble(dataStnDt.get("issueCarat").toString()) > Double.parseDouble(dataStnDt.get("balCarat").toString())) {
					return "-3";
				}
				else {
					
					SaleRetRmStnDt saleRetRmStnDt = new SaleRetRmStnDt();
					saleRetRmStnDt.setStone(saleRmStnDt.getStone());
					saleRetRmStnDt.setDepartment(saleRmStnDt.getDepartment());
					saleRetRmStnDt.setStoneType(saleRmStnDt.getStoneType());
					saleRetRmStnDt.setShape(saleRmStnDt.getShape());
					saleRetRmStnDt.setSubShape(saleRmStnDt.getSubShape());
					saleRetRmStnDt.setQuality(saleRmStnDt.getQuality());
					saleRetRmStnDt.setSize(saleRmStnDt.getSize());
					saleRetRmStnDt.setSieve(saleRmStnDt.getSieve());
					saleRetRmStnDt.setSizeGroup(saleRmStnDt.getSizeGroup());
					saleRetRmStnDt.setStone(Integer.parseInt(dataStnDt.get("issueStone").toString()));
					saleRetRmStnDt.setCarat(Double.parseDouble(dataStnDt.get("issueCarat").toString()));
					saleRetRmStnDt.setRate(saleRmStnDt.getStnRate());
					saleRetRmStnDt.setAmount((Math.round(saleRmStnDt.getStnRate() * Double.parseDouble(dataStnDt.get("issueCarat").toString())*100.0)/100.0));
					saleRetRmStnDt.setCreatedBy(principal.getName());
					saleRetRmStnDt.setCreatedDt(new Date());
					saleRetRmStnDt.setSaleRetMt(saleRetMt);
					saleRetRmStnDt.setRefTranDtId(saleRmStnDt.getId());
					save(saleRetRmStnDt);
					
					saleRmStnDt.setBalStone(saleRmStnDt.getBalStone() - Integer.parseInt(dataStnDt.get("issueStone").toString()));
					saleRmStnDt.setBalCarat(Math.round((saleRmStnDt.getBalCarat() - Double.parseDouble(dataStnDt.get("issueCarat").toString()))*100.0)/100.0);
					saleRmStnDt.setAdjWt(saleRmStnDt.getAdjWt() + Double.parseDouble(dataStnDt.get("issueCarat").toString()));
					saleRmStnDtService.save(saleRmStnDt);
					
					
					
					StoneTran stoneTran = new StoneTran();
					stoneTran.setCreatedBy(principal.getName());
					stoneTran.setCreatedDt(new Date());
					
					stoneTran.setTranDate(saleRetMt.getInvDate()); 
					stoneTran.setDepartment(null);
					stoneTran.setDeptLocation(saleRetRmStnDt.getDepartment());
					stoneTran.setStoneType(saleRetRmStnDt.getStoneType());
					stoneTran.setShape(saleRetRmStnDt.getShape());
					stoneTran.setQuality(saleRetRmStnDt.getQuality());
					stoneTran.setSubShape(saleRetRmStnDt.getSubShape());
					stoneTran.setSize(saleRetRmStnDt.getSize());
					stoneTran.setSieve(saleRetRmStnDt.getSieve());
					stoneTran.setSizeGroup(saleRetRmStnDt.getSizeGroup());
					stoneTran.setStone(saleRetRmStnDt.getStone());
					stoneTran.setCarat(saleRetRmStnDt.getCarat());		//-------------
					stoneTran.setBalStone(saleRetRmStnDt.getStone());
					stoneTran.setBalCarat(saleRetRmStnDt.getCarat()); //--------------
					stoneTran.setRate(saleRetRmStnDt.getRate());
					stoneTran.setInOutFld("C");
					stoneTran.setBagMt(null);;
					stoneTran.setBagSrNo(0);
					stoneTran.setSettingType(null);
					stoneTran.setSetting(null);
					stoneTran.setPacketNo(saleRetRmStnDt.getPacketNo());
					stoneTran.setRemark(saleRetRmStnDt.getRemark());
					stoneTran.setParty(saleRetMt.getParty());
					stoneTran.setTranType("SaleRetRmStn");
					stoneTran.setRefTranId(saleRetRmStnDt.getId());

					stoneTran.setLotNo("SaleRet-"+saleRetRmStnDt.getId());
					
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

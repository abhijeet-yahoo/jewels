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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmMetalDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetRmMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmMetalDtService;

@Service
@Repository
@Transactional
public class SaleRetRmMetalDtService implements ISaleRetRmMetalDtService {

	@Autowired 
	private ISaleRetRmMetalDtRepository saleRetRmMetalDtRepository;
	
	@Autowired
	private ISaleRetMtService saleRetMtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private ISaleRmMetalDtService saleRmMetalDtService;

	@Override
	public List<SaleRetRmMetalDt> findBySaleRetMt(SaleRetMt saleRetMt) {
		// TODO Auto-generated method stub
		return saleRetRmMetalDtRepository.findBySaleRetMt(saleRetMt);
	}

	@Override
	public SaleRetRmMetalDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return saleRetRmMetalDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(SaleRetRmMetalDt saleRetRmMetalDt) {
		// TODO Auto-generated method stub
		saleRetRmMetalDtRepository.save(saleRetRmMetalDt);
	}

	@Override
	public void delete(int id) {
		SaleRetRmMetalDt saleRetRmMetalDt = saleRetRmMetalDtRepository.findOne(id);
		saleRetRmMetalDtRepository.delete(saleRetRmMetalDt.getId());
		
	}

	@Override
	public SaleRetRmMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return saleRetRmMetalDtRepository.findOne(id);
	}

	@Override
	public String saleRetRmMetalDtSave(SaleRetRmMetalDt saleRetRmMetalDt, Integer id, Integer mtId,
			Principal principal) {
		String action = "added";
		String retVal="-1";
		Date aDate = null;
		
		try {
			
			MetalTran metalTran = null;
			aDate = new java.util.Date();

			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				saleRetRmMetalDt.setCreatedBy(principal.getName());
				saleRetRmMetalDt.setCreatedDt(new java.util.Date());
				saleRetRmMetalDt.setSaleRetMt(saleRetMtService.findOne(mtId));
				saleRetRmMetalDt.setUniqueId(aDate.getTime());
				saleRetRmMetalDt.setDepartment(saleRetRmMetalDt.getDepartment());
				saleRetRmMetalDt.setPurityConv(saleRetRmMetalDt.getPurity().getPurityConv());
		//		metalInwardDt.setBalance(Math.round((invWtt*purityService.findOne(purityyId).getPurityConv())*1000.0)/1000.0);
				metalTran = new MetalTran();
				retVal = "1"; 

			} else {
				saleRetRmMetalDt.setId(id);
				saleRetRmMetalDt.setModiBy(principal.getName());
				saleRetRmMetalDt.setModiDt(new java.util.Date());
				saleRetRmMetalDt.setSaleRetMt(saleRetMtService.findOne(mtId));
				saleRetRmMetalDt.setUniqueId(aDate.getTime());
				saleRetRmMetalDt.setDepartment(saleRetRmMetalDt.getDepartment());
				saleRetRmMetalDt.setPurityConv(saleRetRmMetalDt.getPurity().getPurityConv());
				metalTran = metalTranService.RefTranIdAndTranType(id, "Inward");
				action = "updated";	
				retVal = "2"; 

			}

			saleRetRmMetalDt.setRefTranDtId(null);
			save(saleRetRmMetalDt);
			
			SaleRetRmMetalDt saleRetRmMetalDt2 = null;

			if(action == "added"){
				saleRetRmMetalDt2 = findByUniqueId(saleRetRmMetalDt.getUniqueId());
			metalTran.setCreatedBy(saleRetRmMetalDt2.getCreatedBy());
			metalTran.setCreatedDt(saleRetRmMetalDt2.getCreatedDt());
			}else{
				saleRetRmMetalDt2 = findOne(id);
				metalTran.setModiBy(principal.getName());
				metalTran.setModiDt(new java.util.Date());
			}
			
			Double mtlRate = metalTranService.getMetalRate(saleRetRmMetalDt2.getPurity().getId(),saleRetRmMetalDt2.getColor().getId(), 
					saleRetRmMetalDt2.getDepartment().getId(), saleRetRmMetalDt2.getMetalWt());
			
			//metalTran.setTranDate(new java.util.Date());
			metalTran.setColor(saleRetRmMetalDt2.getColor());
			metalTran.setPurity(saleRetRmMetalDt2.getPurity());
			metalTran.setInOutFld("C");
			metalTran.setBalance(saleRetRmMetalDt2.getMetalWt());
			metalTran.setMetalWt(saleRetRmMetalDt2.getMetalWt());
			metalTran.setDeptLocation(saleRetRmMetalDt2.getDepartment());
			metalTran.setPurityConv(saleRetRmMetalDt2.getPurityConv());
			metalTran.setRefTranId(saleRetRmMetalDt2.getId());
			metalTran.setTranType("SaleRetRmMetal");
			metalTran.setTranDate(saleRetRmMetalDt2.getSaleRetMt().getInvDate());
			metalTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
			metalTranService.save(metalTran);
			
						
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String saleRetRmMetalDtDelete(Integer id, Principal principal) {
		
		String retVal = "-1";
		try {
			
			SaleRetRmMetalDt saleRetRmMetalDt = findOne(id);

			List<MetalTran> metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(id, "SaleRetRmMetal", false);
			for (MetalTran metTran : metalTran) {
				metalTranService.delete(metTran.getId());
			}
			
			if(saleRetRmMetalDt.getRefTranDtId() != null) {
				SaleRmMetalDt saleRmMetalDt = saleRmMetalDtService.findOne(saleRetRmMetalDt.getRefTranDtId());
				saleRmMetalDt.setBalance(Math.round((saleRmMetalDt.getBalance() + saleRetRmMetalDt.getMetalWt())*1000.0)/1000.0);
				saleRmMetalDt.setAdjWt(Math.round((saleRmMetalDt.getAdjWt() - saleRetRmMetalDt.getMetalWt())*1000.0)/1000.0);
				saleRmMetalDtService.save(saleRmMetalDt);
			}

			delete(id);
			retVal = "1";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public String saleRetRmMetalDtListing(Integer mtId,String disableFlg) {
		
		
		StringBuilder sb = new StringBuilder();
		List<SaleRetRmMetalDt> saleRetRmMetalDts = findBySaleRetMt(saleRetMtService.findOne(mtId));
		sb.append("{\"total\":").append(saleRetRmMetalDts.size()).append(",\"rows\": [");
		
		for (SaleRetRmMetalDt saleRetRmMetalDt : saleRetRmMetalDts) {
			sb.append("{\"id\":\"")
					.append(saleRetRmMetalDt.getId())
					.append("\",\"department\":\"")
					.append((saleRetRmMetalDt.getDepartment() != null ? saleRetRmMetalDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((saleRetRmMetalDt.getMetal() != null ? saleRetRmMetalDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((saleRetRmMetalDt.getPurity() != null ? saleRetRmMetalDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((saleRetRmMetalDt.getColor() != null ? saleRetRmMetalDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(saleRetRmMetalDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(saleRetRmMetalDt.getRate())
					.append("\",\"amount\":\"")
					.append(saleRetRmMetalDt.getAmount())
					.append("\",\"in999\":\"")
					.append(saleRetRmMetalDt.getIn999())
					.append("\",\"remark\":\"")
					.append((saleRetRmMetalDt.getRemark() != null ? saleRetRmMetalDt.getRemark() : ""));
					
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editSaleRetRmMetalDt(event,")
					.append(saleRetRmMetalDt.getId()+","+saleRetRmMetalDt.getRefTranDtId()).append(", 0);' href='javascript:void(0);'");
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					sb.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteSaleRetRmMetalDt(event,")
							.append(saleRetRmMetalDt.getId()).append(", 0);' href='javascript:void(0);'");
					
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(saleRetRmMetalDt.getId())
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
	public String saleMetalRowDataPickupSave(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		String retVal="-1";
		SaleRetMt saleRetMt = saleRetMtService.findOne(pMtId);
		JSONArray jsonMetalDt = new JSONArray(data);
		
		try {
			
			

			for (int y = 0; y < jsonMetalDt.length(); y++) {
				
				JSONObject dataMtlDt = (JSONObject) jsonMetalDt.get(y);
				
				SaleRmMetalDt saleRmMetalDt = saleRmMetalDtService.findOne(Integer.parseInt(dataMtlDt.get("id").toString()));
				
				if(Double.parseDouble(dataMtlDt.get("issueWt").toString()) > Double.parseDouble(dataMtlDt.get("balanceWt").toString())) {
					return "IssueWt greater than balanceWt";
				}else if(Double.parseDouble(dataMtlDt.get("issueWt").toString())<= 0.0 ) {
					return "Can not transfer zero weight";
				}
				else {
					
					MetalTran metalTran = new MetalTran();
					Date aDate  = new java.util.Date();
					
					SaleRetRmMetalDt saleRetRmMetalDt = new SaleRetRmMetalDt();
					saleRetRmMetalDt.setPurity(saleRmMetalDt.getPurity());
					saleRetRmMetalDt.setDepartment(saleRmMetalDt.getDepartment());
					saleRetRmMetalDt.setColor(saleRmMetalDt.getColor());
					saleRetRmMetalDt.setMetal(saleRmMetalDt.getMetal());
					saleRetRmMetalDt.setMetalWt((Double.parseDouble(dataMtlDt.get("issueWt").toString())));
					saleRetRmMetalDt.setRate(saleRmMetalDt.getRate());
					saleRetRmMetalDt.setAmount((Math.round(saleRmMetalDt.getRate() * Double.parseDouble(dataMtlDt.get("issueWt").toString())*100.0)/100.0));
					saleRetRmMetalDt.setCreatedBy(principal.getName());
					saleRetRmMetalDt.setCreatedDt(new Date());
					saleRetRmMetalDt.setSaleRetMt(saleRetMt);
					saleRetRmMetalDt.setRefTranDtId(saleRmMetalDt.getId());
					saleRetRmMetalDt.setRate(saleRmMetalDt.getRate());
					saleRetRmMetalDt.setUniqueId(aDate.getTime());
					save(saleRetRmMetalDt);
					
					Double balwt = saleRmMetalDt.getAdjWt() +Double.parseDouble(dataMtlDt.get("issueWt").toString());
					
					saleRmMetalDt.setBalance(Math.round((saleRmMetalDt.getMetalWt() - balwt)*1000.0)/1000.0);
					saleRmMetalDt.setAdjWt(Math.round((saleRmMetalDt.getAdjWt() +Double.parseDouble(dataMtlDt.get("issueWt").toString()))*1000.0)/1000.0);
					saleRmMetalDtService.save(saleRmMetalDt);
					
					retVal ="1";
					
					
					
					SaleRetRmMetalDt saleRetRmMetalDt2 = findByUniqueId(saleRetRmMetalDt.getUniqueId());

				
					
					metalTran.setCreatedBy(saleRetRmMetalDt2.getCreatedBy());
					metalTran.setCreatedDt(saleRetRmMetalDt2.getCreatedDt());
					metalTran.setColor(saleRetRmMetalDt2.getColor());
					metalTran.setPurity(saleRetRmMetalDt2.getPurity());
					metalTran.setInOutFld("C");
					metalTran.setBalance(saleRetRmMetalDt2.getMetalWt());
					metalTran.setMetalWt(saleRetRmMetalDt2.getMetalWt());
					metalTran.setDeptLocation(saleRetRmMetalDt2.getDepartment());
					metalTran.setPurityConv(saleRetRmMetalDt2.getPurityConv());
					metalTran.setRefTranId(saleRetRmMetalDt2.getId());
					metalTran.setTranType("SaleRetRmMetal");
					metalTran.setTranDate(saleRetRmMetalDt2.getSaleRetMt().getInvDate());
					metalTran.setMetalRate(saleRetRmMetalDt2.getRate());
					metalTranService.save(metalTran);
					
				}
				
			
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return retVal;
	}
	
	
	
}

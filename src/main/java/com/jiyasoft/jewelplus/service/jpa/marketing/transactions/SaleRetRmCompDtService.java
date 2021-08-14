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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmCompDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetRmCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmCompDtService;

@Service
@Repository
@Transactional
public class SaleRetRmCompDtService implements ISaleRetRmCompDtService {

	@Autowired
	private ISaleRetRmCompDtRepository saleRetRmCompDtRepository;
	
	@Autowired
	private ISaleRetMtService saleRetMtService;
	
	@Autowired
	private ICompTranService compTranService;

	@Autowired
	private ISaleRmCompDtService saleRmCompDtService;
	
	@Override
	public List<SaleRetRmCompDt> findBySaleRetMt(SaleRetMt saleRetMt) {
		// TODO Auto-generated method stub
		return saleRetRmCompDtRepository.findBySaleRetMt(saleRetMt);
	}

	@Override
	public SaleRetRmCompDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return saleRetRmCompDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(SaleRetRmCompDt saleRetRmCompDt) {
		// TODO Auto-generated method stub
		saleRetRmCompDtRepository.save(saleRetRmCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		 saleRetRmCompDtRepository.delete(id);
		
	}

	@Override
	public SaleRetRmCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return saleRetRmCompDtRepository.findOne(id);
	}

	@Override
	public String saleRetRmCompDtSave(SaleRetRmCompDt saleRetRmCompDt, Integer id, Integer mtId, Principal principal) {
		String action = "added";
		String retVal="-1";
		
		SaleRetMt saleRetMt = saleRetMtService.findOne(mtId);
		try {
			
					
			CompTran compTran = null;

			if (id == null || id.equals("") || (id != null && id == 0)) {
				saleRetRmCompDt.setCreatedBy(principal.getName());
				saleRetRmCompDt.setCreatedDt(new java.util.Date());
				saleRetRmCompDt.setUniqueId(new Date().getTime());
				saleRetRmCompDt.setPurityConv(saleRetRmCompDt.getPurity().getPurityConv());
				saleRetRmCompDt.setBalance(saleRetRmCompDt.getMetalWt());
				compTran = new CompTran();
				retVal = "1";
			} else {
				
				saleRetRmCompDt.setModiBy(principal.getName());
				saleRetRmCompDt.setModiDt(new java.util.Date());
				saleRetRmCompDt.setPurityConv(saleRetRmCompDt.getPurity().getPurityConv());
				saleRetRmCompDt.setBalance(saleRetRmCompDt.getMetalWt());
			
				compTran = compTranService.RefTranIdAndTranType(id, "SaleRetRmComp");
				
				action = "updated";	
				retVal = "2";
			}

			saleRetRmCompDt.setSaleRetMt(saleRetMt);

			saleRetRmCompDt.setRefTranDtId(null);
			save(saleRetRmCompDt);
			
			SaleRetRmCompDt saleRetRmCompDt2 = null;
			if(action == "added"){
				saleRetRmCompDt2=findByUniqueId(saleRetRmCompDt.getUniqueId());
				compTran.setCreatedBy(saleRetRmCompDt2.getCreatedBy());
				compTran.setCreatedDt(saleRetRmCompDt2.getCreatedDt());
				compTran.setTrandate(saleRetMt.getInvDate());
			}else{
				saleRetRmCompDt2=findOne(id);
				compTran.setModiBy(principal.getName());
				compTran.setModiDt(new java.util.Date());
			}
			
			Double compMtlRate=compTranService.getCompMetalRate(saleRetRmCompDt2.getPurity().getId(), saleRetRmCompDt2.getColor().getId(), saleRetRmCompDt2.getDepartment().getId(), 
					saleRetRmCompDt2.getComponent().getId(),saleRetRmCompDt2.getMetalWt());
			

			compTran.setColor(saleRetRmCompDt2.getColor());
			compTran.setPurity(saleRetRmCompDt2.getPurity());
			compTran.setInOutFld("C");
			compTran.setBagMt(null);
			compTran.setBalance(saleRetRmCompDt2.getMetalWt());
			compTran.setMetalWt(saleRetRmCompDt2.getMetalWt());
			compTran.setDeptLocation(saleRetRmCompDt2.getDepartment());
			compTran.setPurityConv(saleRetRmCompDt2.getPurityConv());
			compTran.setRefTranId(saleRetRmCompDt2.getId());
			compTran.setTranType("SaleRetRmComp");
			compTran.setRemark("");
			compTran.setDepartment(null);
			compTran.setComponent(saleRetRmCompDt2.getComponent());
			
			compTran.setPcs(saleRetRmCompDt2.getQty());
			compTran.setBalancePcs(saleRetRmCompDt2.getQty());
			compTran.setMetalRate(compMtlRate);
			compTranService.save(compTran);
			
			
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String saleRetRmCompDtDelete(Integer id, Principal principal) {

		String retVal ="-1";
		
		try {
			SaleRetRmCompDt saleRetRmCompDt = findOne(id);
			
			List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(id, "SaleRetRmComp");
			for (CompTran comTran : compTran) {
				compTranService.delete(comTran.getId());
			}
			
			
			if(saleRetRmCompDt.getRefTranDtId() != null) {
				
				SaleRmCompDt saleRmCompDt = saleRmCompDtService.findOne(saleRetRmCompDt.getRefTranDtId());
				saleRmCompDt.setBalance(saleRmCompDt.getBalance() + saleRetRmCompDt.getMetalWt());
				saleRmCompDt.setAdjWt(saleRmCompDt.getAdjWt() - saleRetRmCompDt.getMetalWt() );
				saleRmCompDtService.save(saleRmCompDt);
				
				}
			
			delete(id);
			retVal ="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public String saleRetRmCompDtListing(Integer mtId,String disableFlg) {
	
		StringBuilder sb = new StringBuilder();
		
		SaleRetMt saleRetMt = saleRetMtService.findOne(mtId);

		List<SaleRetRmCompDt> saleRetRmCompDts= findBySaleRetMt(saleRetMt);
		
		sb.append("{\"total\":").append(saleRetRmCompDts.size()).append(",\"rows\": [");
		
		for (SaleRetRmCompDt saleRetRmCompDt : saleRetRmCompDts) {
			sb.append("{\"id\":\"")
					.append(saleRetRmCompDt.getId())
					.append("\",\"metal\":\"")
					.append((saleRetRmCompDt.getMetal() != null ? saleRetRmCompDt.getMetal().getName() : ""))
					.append("\",\"component\":\"")
					.append((saleRetRmCompDt.getComponent() != null ? saleRetRmCompDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((saleRetRmCompDt.getPurity() != null ? saleRetRmCompDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((saleRetRmCompDt.getColor() != null ? saleRetRmCompDt.getColor().getName() : ""))
					.append("\",\"department\":\"")
					.append((saleRetRmCompDt.getDepartment() != null ? saleRetRmCompDt.getDepartment().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(saleRetRmCompDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(saleRetRmCompDt.getRate())
					.append("\",\"amount\":\"")
					.append(saleRetRmCompDt.getAmount())
					.append("\",\"qty\":\"")
					.append(saleRetRmCompDt.getQty());
						
					if(disableFlg.equalsIgnoreCase("false")) {
					sb.append("\",\"action1\":\"")
					
					.append("<a href='javascript:editSaleRetRmCompDt(event,")
					.append(saleRetRmCompDt.getId()+","+saleRetRmCompDt.getRefTranDtId())
					.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteSaleRetRmCompDt(event,")
					  .append(saleRetRmCompDt.getId()).append(", 0);' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(saleRetRmCompDt.getId())
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
	public String saleCompRowDataPickupSave(Integer pMtId, String data, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal="-1";
		SaleRetMt saleRetMt = saleRetMtService.findOne(pMtId);
		JSONArray jsonComponentDt = new JSONArray(data);
		
		try {
			
			

			for (int y = 0; y < jsonComponentDt.length(); y++) {
				
				JSONObject dataCompDt = (JSONObject) jsonComponentDt.get(y);
				
				CompTran compTran = new CompTran();
				
				SaleRmCompDt saleRmCompDt = saleRmCompDtService.findOne(Integer.parseInt(dataCompDt.get("id").toString()));
				
				if(Double.parseDouble(dataCompDt.get("issueWt").toString()) > Double.parseDouble(dataCompDt.get("balanceWt").toString())) {
					return "IssueWt greater than balanceWt";
				}else if(Double.parseDouble(dataCompDt.get("issueWt").toString())<= 0.0 ) {
					return "Can not transfer zero weight";
				}
				else {
					
					SaleRetRmCompDt saleRetRmCompDt = new SaleRetRmCompDt();
					saleRetRmCompDt.setPurity(saleRmCompDt.getPurity());
					saleRetRmCompDt.setDepartment(saleRmCompDt.getDepartment());
					saleRetRmCompDt.setColor(saleRmCompDt.getColor());
					saleRetRmCompDt.setMetal(saleRmCompDt.getMetal());
					saleRetRmCompDt.setComponent(saleRmCompDt.getComponent());
					saleRetRmCompDt.setMetalWt((Double.parseDouble(dataCompDt.get("issueWt").toString())));
					saleRetRmCompDt.setRate(saleRmCompDt.getRate());
					saleRetRmCompDt.setAmount((Math.round(saleRmCompDt.getRate() * Double.parseDouble(dataCompDt.get("issueWt").toString())*100.0)/100.0));
					saleRetRmCompDt.setCreatedBy(principal.getName());
					saleRetRmCompDt.setCreatedDt(new Date());
					saleRetRmCompDt.setSaleRetMt(saleRetMt);
					saleRetRmCompDt.setQty(saleRmCompDt.getQty());
					saleRetRmCompDt.setRefTranDtId(saleRmCompDt.getId());
					saleRetRmCompDt.setRate(saleRmCompDt.getRate());
					saleRetRmCompDt.setUniqueId(new Date().getTime());
					save(saleRetRmCompDt);
					
					Double balwt = saleRmCompDt.getAdjWt() +Double.parseDouble(dataCompDt.get("issueWt").toString());
					
					saleRmCompDt.setBalance(Math.round((saleRmCompDt.getMetalWt() - balwt)*100.0)/100.0);
					saleRmCompDt.setAdjWt(Math.round((saleRmCompDt.getAdjWt() +Double.parseDouble(dataCompDt.get("issueWt").toString()))*100.0)/100.0);
					saleRmCompDtService.save(saleRmCompDt);
					
					
				
					//Comptran
					SaleRetRmCompDt saleRetRmCompDt2=findByUniqueId(saleRetRmCompDt.getUniqueId());
				
						compTran.setCreatedBy(saleRetRmCompDt2.getCreatedBy());
						compTran.setCreatedDt(saleRetRmCompDt2.getCreatedDt());
						compTran.setTrandate(saleRetMt.getInvDate());
					
					Double compMtlRate=compTranService.getCompMetalRate(saleRetRmCompDt2.getPurity().getId(), saleRetRmCompDt2.getColor().getId(), saleRetRmCompDt2.getDepartment().getId(), 
							saleRetRmCompDt2.getComponent().getId(),saleRetRmCompDt2.getMetalWt());
					
					compTran.setColor(saleRetRmCompDt2.getColor());
					compTran.setPurity(saleRetRmCompDt2.getPurity());
					compTran.setInOutFld("C");
					compTran.setBagMt(null);
					compTran.setBalance(saleRetRmCompDt2.getMetalWt());
					compTran.setMetalWt(saleRetRmCompDt2.getMetalWt());
					compTran.setDeptLocation(saleRetRmCompDt2.getDepartment());
					compTran.setPurityConv(saleRetRmCompDt2.getPurityConv());
					compTran.setRefTranId(saleRetRmCompDt2.getId());
					compTran.setTranType("SaleRetRmComp");
					compTran.setRemark("");
					compTran.setDepartment(null);
					compTran.setComponent(saleRetRmCompDt2.getComponent());
					compTran.setPcs(saleRetRmCompDt2.getQty());
					compTran.setBalancePcs(saleRetRmCompDt2.getQty());
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

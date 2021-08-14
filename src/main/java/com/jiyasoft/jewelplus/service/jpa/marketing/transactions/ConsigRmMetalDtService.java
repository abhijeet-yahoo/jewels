package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmMetalDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRmMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmMetalDtService;

@Service
@Repository
@Transactional
public class ConsigRmMetalDtService implements IConsigRmMetalDtService {

	@Autowired
	private IConsigRmMetalDtRepository consigRmMetalDtRepository;
	
	@Autowired
	private IConsigMtService consigMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Override
	public List<ConsigRmMetalDt> findByConsigMt(ConsigMt consigMt) {
		
		return consigRmMetalDtRepository.findByConsigMt(consigMt);
	}

	@Override
	public ConsigRmMetalDt findByUniqueId(Long uniqueId) {
	
		return consigRmMetalDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(ConsigRmMetalDt consigRmMetalDt) {
		
		consigRmMetalDtRepository.save(consigRmMetalDt);
	}

	@Override
	public void delete(int id) {
		consigRmMetalDtRepository.delete(id);
		
	}

	@Override
	public ConsigRmMetalDt findOne(int id) {
		
		return consigRmMetalDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getConsigRmMetalDtList() {
		
		return null;
	}

	@Override
	public String consigRmMetalDtSave(ConsigRmMetalDt consigRmMetalDt, Integer id, Integer mtId, Principal principal) {
		
		String action ="added";
		String retVal="-1";
		
		
		try {
			
			
			ConsigMt consigMt = consigMtService.findOne(mtId);
			
			MetalTran metalTran = null;
			consigRmMetalDt.setConsigMt(consigMt);
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				
				  Double balance =   metalTranService.getStockBalance(consigRmMetalDt.getPurity().getId(),
						  consigRmMetalDt.getColor().getId(),consigRmMetalDt.getDepartment().getId());
				  
				  if (balance == null) { 
					  balance = 0.0;
				  }
				  
				  if (balance < consigRmMetalDt.getMetalWt()) { 
					  return "Error : Stock Not Available (Available In Stock : "+balance+")"; 
					  }
				 
					 
				consigRmMetalDt.setCreatedBy(principal.getName());
				consigRmMetalDt.setCreatedDt(new java.util.Date());
				/* consigRmMetalDt.setConsigMt(consigMtService.findOne(mtId)); */
				consigRmMetalDt.setUniqueId(new Date().getTime());
				consigRmMetalDt.setPurityConv(purityService.findOne(consigRmMetalDt.getPurity().getId()).getPurityConv());
				
				metalTran = new MetalTran();
				retVal = "1";
				
			} else {
				
				
				ConsigRmMetalDt consigRmMetalDt2 = findOne(id);
				  Double balance =  metalTranService.getStockBalance(consigRmMetalDt2.getPurity().getId(),
						  consigRmMetalDt2.getColor().getId(),consigRmMetalDt2.getDepartment().getId());
				  
				  Double difference = consigRmMetalDt.getMetalWt() - consigRmMetalDt2.getMetalWt();
				  if(balance == null) 
				  { balance = 0.0;
				  
				  }
				  
				  if (balance < difference) { return
				  "Error : Stock Not Available (Available In Stock : "+balance+")"; }
				 
				 
			
				consigRmMetalDt.setModiBy(principal.getName());
				consigRmMetalDt.setModiDt(new java.util.Date());
				metalTran = metalTranService.RefTranIdAndTranType(id, "ConsigRmMetalDt");
				retVal="2";
				action ="updated";
			}
			
			 Double mtlRate =metalTranService.getMetalRate(consigRmMetalDt.getPurity().getId(), consigRmMetalDt.getColor().getId(), 
					 consigRmMetalDt.getDepartment().getId(), consigRmMetalDt.getMetalWt());
			 
			 mtlRate = mtlRate != null ? mtlRate :0.0;
			 
			 consigRmMetalDt.setRate(mtlRate);
			 consigRmMetalDt.setAmount(Math.round(mtlRate * consigRmMetalDt.getMetalWt())*100.0/100.0);
			 
			consigRmMetalDt.setBalance(consigRmMetalDt.getMetalWt());

			save(consigRmMetalDt);
			
			
			  
			  if(action == "added"){
				 
			  metalTran.setCreatedBy(consigRmMetalDt.getCreatedBy());
			  metalTran.setCreatedDt(consigRmMetalDt.getCreatedDt());
			  
			  }else{ 
					/* consigRmMetalDt2 = findOne(id); */
			  
			  metalTran.setModiBy(principal.getName()); 
			  metalTran.setModiDt(new java.util.Date());
			  
			  } 
			  
			
			  
			  metalTran.setColor(consigRmMetalDt.getColor());
			  metalTran.setPurity(consigRmMetalDt.getPurity());
			  metalTran.setInOutFld("D");
			  metalTran.setBagMt(null); 
			  metalTran.setBalance(consigRmMetalDt.getMetalWt() * -1); 
			  metalTran.setMetalWt(consigRmMetalDt.getMetalWt());
			  metalTran.setDeptLocation(consigRmMetalDt.getDepartment());
			  metalTran.setPurityConv(consigRmMetalDt.getPurityConv());
			  metalTran.setRefTranId(consigRmMetalDt.getId());
			  metalTran.setTranType("ConsigRmMetalDt");
			  metalTran.setRemark("");
			  metalTran.setDepartment(null);
			  metalTran.setTranDate(new Date());
			metalTran.setMetalRate(mtlRate);			
			metalTranService.save(metalTran);
			
			
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
		
	}

	@Override
	public String consigRmMetalDtDelete(Integer id, Principal principal) {
	String retVal ="-1";
		
		try {
			
			ConsigRmMetalDt consigRmMetalDt = findOne(id);
			
			if(consigRmMetalDt.getAdjWt() > 0) {
				return "Can not delete, Loose metal";
			}else {
			
			MetalTran metalTran = metalTranService.RefTranIdAndTranType(id, "ConsigRmMetalDt");
			metalTranService.delete(metalTran.getId());
			
			delete(id);
			retVal ="1";
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

	
	public String consigRmMetalDtListing(Integer mtId,String disableFlg) {
		
		StringBuilder sb = new StringBuilder();
		
		
		List<ConsigRmMetalDt> consigRmMetalDts=findByConsigMt(consigMtService.findOne(mtId));
		
		sb.append("{\"total\":").append(consigRmMetalDts.size()).append(",\"rows\": [");
		
		for (ConsigRmMetalDt consigRmMetalDt : consigRmMetalDts) {
			sb.append("{\"id\":\"")
					.append(consigRmMetalDt.getId())
					.append("\",\"department\":\"")
					.append((consigRmMetalDt.getDepartment() != null ? consigRmMetalDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((consigRmMetalDt.getMetal() != null ? consigRmMetalDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((consigRmMetalDt.getPurity() != null ? consigRmMetalDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((consigRmMetalDt.getColor() != null ? consigRmMetalDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(consigRmMetalDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(consigRmMetalDt.getRate())
					.append("\",\"amount\":\"")
					.append(consigRmMetalDt.getAmount())
					.append("\",\"balanceWt\":\"")
					.append(consigRmMetalDt.getBalance());
					sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editConsigRmMetalDt(")
					.append(consigRmMetalDt.getId());
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					sb.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteConsigRmMetalDt(event,")
							.append(consigRmMetalDt.getId()).append(", 0);' href='javascript:void(0);'");
					
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(consigRmMetalDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
		
					
					sb.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
		
	}

	@Override
	public String rowMetalDetails(Integer mtId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		
		List<ConsigRmMetalDt> consigRmMetalDts=findByConsigMt(consigMtService.findOne(mtId));
		
		sb.append("{\"total\":").append(consigRmMetalDts.size()).append(",\"rows\": [");
		
		for (ConsigRmMetalDt consigRmMetalDt : consigRmMetalDts) {
		
			Double issueWt =0.0; 
			
			sb.append("{\"id\":\"")
					.append(consigRmMetalDt.getId())
					.append("\",\"department\":\"")
					.append((consigRmMetalDt.getDepartment() != null ? consigRmMetalDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((consigRmMetalDt.getMetal() != null ? consigRmMetalDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((consigRmMetalDt.getPurity() != null ? consigRmMetalDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((consigRmMetalDt.getColor() != null ? consigRmMetalDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(consigRmMetalDt.getMetalWt())
					.append("\",\"balanceWt\":\"")
					.append(consigRmMetalDt.getBalance())
					.append("\",\"issueWt\":\"")
					.append(issueWt)
					.append("\",\"rate\":\"")
					.append(consigRmMetalDt.getRate())
					.append("\",\"adjQty\":\"")
					.append(consigRmMetalDt.getAdjWt())
					.append("\",\"amount\":\"")
					.append(consigRmMetalDt.getAmount());
					sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editConsigRmMetalDt(")
					.append(consigRmMetalDt.getId());
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					sb.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteConsigRmMetalDt(event,")
							.append(consigRmMetalDt.getId()).append(", 0);' href='javascript:void(0);'");
					
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(consigRmMetalDt.getId())
					 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
		
					
					sb.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	
		
	
	
	
}

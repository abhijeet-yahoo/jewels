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
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmMetalDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRmMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmMetalDtService;

@Service
@Repository
@Transactional
public class SaleRmMetalDtService implements ISaleRmMetalDtService{

	@Autowired
	private ISaleRmMetalDtRepository saleRmMetalDtRepository;
	
	@Autowired
	private ISaleMtService saleMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IMetalTranService metalTranService;
	

	@Override
	public List<SaleRmMetalDt> findBySaleMt(SaleMt saleMt) {
		
		return saleRmMetalDtRepository.findBySaleMt(saleMt);
	}


	@Override
	public SaleRmMetalDt findByUniqueId(Long uniqueId) {
		
		return saleRmMetalDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(SaleRmMetalDt saleRmMetalDt) {
		
		saleRmMetalDtRepository.save(saleRmMetalDt);
	}

	@Override
	public void delete(int id) {
		saleRmMetalDtRepository.delete(id);
		
	}

	@Override
	public SaleRmMetalDt findOne(int id) {
		
		return saleRmMetalDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getSaleRmMetalDtList() {
	
		return null;
	}

	@Override
	public String saleRmMetalDtSave(SaleRmMetalDt saleRmMetalDt, Integer id, Integer mtId, Principal principal) {
		
		String action ="added";
		String retVal="-1";
		
		
		try {
			MetalTran metalTran = null;
			saleRmMetalDt.setSaleMt(saleMtService.findOne(mtId));
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				  Double balance =   metalTranService.getStockBalance(saleRmMetalDt.getPurity().getId(),
						  saleRmMetalDt.getColor().getId(),saleRmMetalDt.getDepartment().getId());
				  
				  if (balance == null) { 
					  balance = 0.0;
				  }
				  
				  if (balance < saleRmMetalDt.getMetalWt()) { 
					  return "Error : Stock Not Available (Available In Stock : "+balance+")"; 
					  }
				
				saleRmMetalDt.setCreatedBy(principal.getName());
				saleRmMetalDt.setCreatedDt(new java.util.Date());
				saleRmMetalDt.setUniqueId(new Date().getTime());
				
				metalTran = new MetalTran();
				retVal = "1";
				
			} else {
				
				SaleRmMetalDt saleRmMetalDt2 = findOne(id);
				 Double balance =  metalTranService.getStockBalance(saleRmMetalDt2.getPurity().getId(),
						 saleRmMetalDt2.getColor().getId(),saleRmMetalDt2.getDepartment().getId());
				  
				  Double difference = saleRmMetalDt.getMetalWt() - saleRmMetalDt.getMetalWt();
				  if(balance == null) 
				  { balance = 0.0;
				  
				  }
				  
				  if (balance < difference) { return
				  "Error : Stock Not Available (Available In Stock : "+balance+")"; }
				
				saleRmMetalDt.setModiBy(principal.getName());
				saleRmMetalDt.setModiDt(new java.util.Date());				
				metalTran = metalTranService.RefTranIdAndTranType(id, "SaleRmMetal");
				retVal="2";
				action ="updated";
			}
			
			saleRmMetalDt.setSaleMt(saleMtService.findOne(mtId));
			saleRmMetalDt.setPurityConv(purityService.findOne(saleRmMetalDt.getPurity().getId()).getPurityConv());
			
			 Double mtlRate =metalTranService.getMetalRate(saleRmMetalDt.getPurity().getId(), saleRmMetalDt.getColor().getId(), 
					 saleRmMetalDt.getDepartment().getId(), saleRmMetalDt.getMetalWt());
			 
			 mtlRate = mtlRate != null ? mtlRate :0.0;
			 
		//	 saleRmMetalDt.setRate(mtlRate);
			 saleRmMetalDt.setAmount(Math.round(saleRmMetalDt.getRate() * saleRmMetalDt.getMetalWt())*100.0/100.0);
			 
			 saleRmMetalDt.setBalance(saleRmMetalDt.getMetalWt());

			save(saleRmMetalDt);
			
			  SaleRmMetalDt saleRmMetalDt2=null; 
			  
			  if(action == "added"){
				  saleRmMetalDt2 =  findByUniqueId(saleRmMetalDt.getUniqueId());
			  metalTran.setCreatedBy(saleRmMetalDt2.getCreatedBy());
			  metalTran.setCreatedDt(saleRmMetalDt2.getCreatedDt());
			  
			  }else{ 
				  saleRmMetalDt2 = findOne(id);
			  
			  metalTran.setModiBy(principal.getName()); 
			  metalTran.setModiDt(new java.util.Date());
			  
			  } 
			  
			  metalTran.setColor(saleRmMetalDt2.getColor());
			  metalTran.setPurity(saleRmMetalDt2.getPurity());
			  metalTran.setInOutFld("D");
			  metalTran.setBagMt(null); 
			  metalTran.setBalance(saleRmMetalDt2.getMetalWt() * -1); 
			  metalTran.setMetalWt(saleRmMetalDt2.getMetalWt());
			  metalTran.setDeptLocation(saleRmMetalDt2.getDepartment());
			  metalTran.setPurityConv(saleRmMetalDt2.getPurityConv());
			  metalTran.setRefTranId(saleRmMetalDt2.getId());
			  metalTran.setTranType("SaleRmMetal");
			  metalTran.setRemark("");
			  metalTran.setDepartment(null);
			  metalTran.setMetalRate(mtlRate);
			  
			  metalTran.setTranDate(new Date());
						
			metalTranService.save(metalTran);
			
			
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String saleRmMetalDtDelete(Integer id, Principal principal) {
		String retVal = "-1";

		try {

			SaleRmMetalDt saleRmMetalDt = findOne(id);
			
			if(saleRmMetalDt.getAdjWt() > 0) {
				return "Can not delete, Loose metal";
			}else {
			MetalTran metalTran = metalTranService.RefTranIdAndTranType(id, "SaleRmMetal");
			metalTranService.delete(metalTran.getId());

			delete(id);
			retVal = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}



	@Override
	public String saleRmMetalListing(Integer mtId,String disableFlg) {
		
		StringBuilder sb = new StringBuilder();
		
		
		List<SaleRmMetalDt> saleRmMetalDts=findBySaleMt(saleMtService.findOne(mtId));
		
		sb.append("{\"total\":").append(saleRmMetalDts.size()).append(",\"rows\": [");
		
		for (SaleRmMetalDt saleRmMetalDt : saleRmMetalDts) {
			sb.append("{\"id\":\"")
					.append(saleRmMetalDt.getId())
					.append("\",\"department\":\"")
					.append((saleRmMetalDt.getDepartment() != null ? saleRmMetalDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((saleRmMetalDt.getMetal() != null ? saleRmMetalDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((saleRmMetalDt.getPurity() != null ? saleRmMetalDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((saleRmMetalDt.getColor() != null ? saleRmMetalDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(saleRmMetalDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(saleRmMetalDt.getRate())
					.append("\",\"amount\":\"")
					.append(saleRmMetalDt.getAmount());
				
			if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"action1\":\"")
				.append("<a href='javascript:editSaleRmMetalDt(")
				.append(saleRmMetalDt.getId());
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				sb.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteSaleRmMetalDt(event,")
						.append(saleRmMetalDt.getId()).append(", 0);' href='javascript:void(0);'");
				
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
				 .append(saleRmMetalDt.getId())
				 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				sb.append("\"},");
			}
				else {
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
	public String saleRowMetalDetails(Integer mtId) {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();
		
		List<SaleRmMetalDt> saleRmMetalDts=findBySaleMt(saleMtService.findOne(mtId));
		
		sb.append("{\"total\":").append(saleRmMetalDts.size()).append(",\"rows\": [");
		
		for (SaleRmMetalDt saleRmMetalDt : saleRmMetalDts) {
		
			Double issueWt =0.0; 
			
			sb.append("{\"id\":\"")
					.append(saleRmMetalDt.getId())
					.append("\",\"department\":\"")
					.append((saleRmMetalDt.getDepartment() != null ? saleRmMetalDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((saleRmMetalDt.getMetal() != null ? saleRmMetalDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((saleRmMetalDt.getPurity() != null ? saleRmMetalDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((saleRmMetalDt.getColor() != null ? saleRmMetalDt.getColor().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(saleRmMetalDt.getMetalWt())
					.append("\",\"balanceWt\":\"")
					.append(saleRmMetalDt.getBalance())
					.append("\",\"issueWt\":\"")
					.append(issueWt)
					.append("\",\"rate\":\"")
					.append(saleRmMetalDt.getRate())
					.append("\",\"adjQty\":\"")
					.append(saleRmMetalDt.getAdjWt())
					.append("\",\"amount\":\"")
					.append(saleRmMetalDt.getAmount());
					sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editSaleRetRmMetalDt(")
					.append(saleRmMetalDt.getId());
					sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
					sb.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteSaleRetRmMetalDt(event,")
							.append(saleRmMetalDt.getId()).append(", 0);' href='javascript:void(0);'");
					
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					 .append(saleRmMetalDt.getId())
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

package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmCompDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRmCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmCompDtService;

@Service
@Repository
@Transactional
public class SaleRmCompDtService implements ISaleRmCompDtService {

	@Autowired
	private ISaleRmCompDtRepository saleRmCompDtRepository;
	
	@Autowired
	private ISaleMtService saleMtService;
	
	@Autowired
	private ICompTranService compTranService;

	@Override
	public List<SaleRmCompDt> findBySaleMt(SaleMt saleMt) {
		
		return saleRmCompDtRepository.findBySaleMt(saleMt);
	}

	@Override
	public SaleRmCompDt findByUniqueId(Long uniqueId) {
		
		return saleRmCompDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(SaleRmCompDt saleRmCompDt) {

		saleRmCompDtRepository.save(saleRmCompDt);
		
	}

	@Override
	public void delete(int id) {
		saleRmCompDtRepository.delete(id);
		
	}


	@Override
	public SaleRmCompDt findOne(int id) {
		
		return saleRmCompDtRepository.findOne(id);
	}

	@Override
	public String saveSaleRmCompDt(SaleRmCompDt saleRmCompDt, Integer id, Integer mtId, Principal principal) {
		
		String action = "added";
		String retVal="-1";
			
		
		try {
			
			CompTran compTran = null;


		
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Double tempBal = compTranService.getStockBalance(saleRmCompDt.getPurity().getId(),saleRmCompDt.getColor().getId(),saleRmCompDt.getDepartment().getId(), 
						saleRmCompDt.getComponent().getId());
				
				if (tempBal == null) {
					tempBal = 0.0;
				}

				if (tempBal < saleRmCompDt.getMetalWt()) {
					return "Error : Stock Not Available (Available In Stock : "+tempBal+")";
				}
				
				saleRmCompDt.setCreatedBy(principal.getName());
				saleRmCompDt.setCreatedDt(new java.util.Date());
				saleRmCompDt.setUniqueId(new Date().getTime());
				saleRmCompDt.setPurityConv(saleRmCompDt.getPurity().getPurityConv());
				
				compTran = new CompTran();
				retVal ="1";

				} else {
				
					SaleRmCompDt saleRmCompDt2 =findOne(id);	
					
					
					Double tempBal = compTranService.getStockBalance(saleRmCompDt2.getPurity().getId(),saleRmCompDt2.getColor().getId(),saleRmCompDt2.getDepartment().getId(), 
							saleRmCompDt2.getComponent().getId());
					
					Double difference = saleRmCompDt.getMetalWt() - saleRmCompDt2.getMetalWt();

					if (tempBal < difference) {
						return "Error : Stock Not Available (Available In Stock : "+tempBal+")";
					}
					
					
					saleRmCompDt.setModiBy(principal.getName());
					saleRmCompDt.setModiDt(new java.util.Date());
				
				
				compTran = compTranService.RefTranIdAndTranType(id, "SaleRmComp");
				action = "updated";	
				
				retVal ="2";
			}
			saleRmCompDt.setSaleMt(saleMtService.findOne(mtId));
			saleRmCompDt.setBalance(saleRmCompDt.getMetalWt());
			
			save(saleRmCompDt);

			
			
			SaleRmCompDt saleRmCompDt2=null;
			
			if(action == "added"){
				saleRmCompDt2 = findByUniqueId(saleRmCompDt.getUniqueId());	
				compTran.setCreatedBy(saleRmCompDt.getCreatedBy());
				compTran.setCreatedDt(saleRmCompDt.getCreatedDt());
				compTran.setTrandate(saleRmCompDt.getSaleMt().getInvDate());
			}else{
				saleRmCompDt2 = findOne(id);
				compTran.setModiBy(principal.getName());
				compTran.setModiDt(new java.util.Date());
			}
			
			Double compMtlRate=compTranService.getCompMetalRate(saleRmCompDt2.getPurity().getId(), saleRmCompDt2.getColor().getId(), saleRmCompDt2.getDepartment().getId(), 
					saleRmCompDt2.getComponent().getId(),saleRmCompDt2.getMetalWt());
			

			compTran.setPurity(saleRmCompDt2.getPurity());
			compTran.setInOutFld("D");
			compTran.setBagMt(null);
			compTran.setBalance(saleRmCompDt2.getMetalWt() * -1);
			compTran.setMetalWt(saleRmCompDt2.getMetalWt());
			compTran.setDeptLocation(saleRmCompDt2.getDepartment());
			compTran.setPurityConv(saleRmCompDt2.getPurityConv());
			compTran.setRefTranId(saleRmCompDt2.getId());
			compTran.setTranType("SaleRmComp");
			compTran.setRemark("");
			compTran.setDepartment(saleRmCompDt2.getDepartment());
			compTran.setPcs(saleRmCompDt2.getQty());
			compTran.setComponent(saleRmCompDt2.getComponent());
			compTran.setColor(saleRmCompDt2.getColor());
			compTran.setMetalRate(compMtlRate);
			
			compTranService.save(compTran);
			
			
									
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String saleRmCompDtDelete(Integer id, Principal principal) {
		String retVal = "-1";

		try {
			
			SaleRmCompDt saleRmCompDt = findOne(id);
			if(saleRmCompDt.getAdjWt() > 0) {
				return "Can not delete, Loose Component";
			}else {
			
			List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(id, "SaleRmComp");
			for (CompTran comTran : compTran) {
				compTranService.delete(comTran.getId());
			}
			
			delete(id);
			retVal = "1";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return retVal;
	}

	@Override
	public String saleRmCompDtListing(Integer mtId,String disableFlg) {
		StringBuilder sb = new StringBuilder();
		
		SaleMt saleMt = saleMtService.findOne(mtId);

		List<SaleRmCompDt> saleRmCompDts= findBySaleMt(saleMt);
		
		sb.append("{\"total\":").append(saleRmCompDts.size()).append(",\"rows\": [");
		
		for (SaleRmCompDt saleRmCompDt : saleRmCompDts) {
			sb.append("{\"id\":\"")
					.append(saleRmCompDt.getId())
					.append("\",\"metal\":\"")
					.append((saleRmCompDt.getMetal() != null ? saleRmCompDt.getMetal().getName() : ""))
					.append("\",\"component\":\"")
					.append((saleRmCompDt.getComponent() != null ? saleRmCompDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((saleRmCompDt.getPurity() != null ? saleRmCompDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((saleRmCompDt.getColor() != null ? saleRmCompDt.getColor().getName() : ""))
					.append("\",\"department\":\"")
					.append((saleRmCompDt.getDepartment() != null ? saleRmCompDt.getDepartment().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(saleRmCompDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(saleRmCompDt.getRate())
					.append("\",\"amount\":\"")
					.append(saleRmCompDt.getAmount())
					.append("\",\"qty\":\"")
					.append(saleRmCompDt.getQty());
				
					if(disableFlg.equalsIgnoreCase("false")) {
					sb.append("\",\"action1\":\"")
					.append("<a href='javascript:editSaleRmCompDt(").append(saleRmCompDt.getId())
					.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteSaleRmCompDt(event,")
					  .append(saleRmCompDt.getId()).append(", 0);' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(saleRmCompDt.getId())
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
	public String saleRowCompDtDetails(Integer mtId) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		
		List<SaleRmCompDt> saleRmCompDts = findBySaleMt(saleMtService.findOne(mtId));
		
		sb.append("{\"total\":").append(saleRmCompDts.size()).append(",\"rows\": [");
		
		for (SaleRmCompDt saleRmCompDt : saleRmCompDts) {
			
			Double issueWt =0.0; 
			
			sb.append("{\"id\":\"")
					.append(saleRmCompDt.getId())
					.append("\",\"department\":\"")
					.append((saleRmCompDt.getDepartment() != null ? saleRmCompDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((saleRmCompDt.getMetal() != null ? saleRmCompDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((saleRmCompDt.getPurity() != null ? saleRmCompDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((saleRmCompDt.getColor() != null ? saleRmCompDt.getColor().getName() : ""))
					.append("\",\"component\":\"")
					.append((saleRmCompDt.getComponent() != null ? saleRmCompDt.getComponent().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(saleRmCompDt.getMetalWt())
					.append("\",\"balanceWt\":\"")
					.append(saleRmCompDt.getBalance())
					.append("\",\"issueWt\":\"")
					.append(issueWt)
					.append("\",\"rate\":\"")
					.append(saleRmCompDt.getRate())
					.append("\",\"amount\":\"")
					.append(saleRmCompDt.getAmount());
					
					sb.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
}

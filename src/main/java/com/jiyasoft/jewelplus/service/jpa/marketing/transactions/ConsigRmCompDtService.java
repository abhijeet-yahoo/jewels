package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmCompDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRmCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmCompDtService;

@Service
@Repository
@Transactional
public class ConsigRmCompDtService implements IConsigRmCompDtService {

	@Autowired
	private IConsigRmCompDtRepository consigRmCompDtRepository;
	
	@Autowired
	private IConsigMtService consigMtService;
	
	@Autowired
	private ICompTranService compTranService;


	@Override
	public List<ConsigRmCompDt> findByConsigMt(ConsigMt consigMt) {
		
		return consigRmCompDtRepository.findByConsigMt(consigMt);
	}

	@Override
	public ConsigRmCompDt findByUniqueId(Long uniqueId) {
		
		return consigRmCompDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(ConsigRmCompDt consigRmCompDt) {
		
		consigRmCompDtRepository.save(consigRmCompDt);
	}

	@Override
	public void delete(int id) {
		consigRmCompDtRepository.delete(id);
		
	}

	@Override
	public ConsigRmCompDt findOne(int id) {
		
		return consigRmCompDtRepository.findOne(id);
	}

	@Override
	public String saveConsigRmCompDt(ConsigRmCompDt consigRmCompDt, Integer id, Integer mtId, Principal principal) {
		String action = "added";
		String retVal = "-1";

		try {

			CompTran compTran = null;
			
			ConsigMt consigMt = consigMtService.findOne(mtId);

			Double compMtlRate=compTranService.getCompMetalRate(consigRmCompDt.getPurity().getId(), consigRmCompDt.getColor().getId(), consigRmCompDt.getDepartment().getId(), 
					consigRmCompDt.getComponent().getId(),consigRmCompDt.getMetalWt());
			
			compMtlRate = compMtlRate != null ? compMtlRate :0.0;
			
			if (id == null || id.equals("") || (id != null && id == 0)) {

				Double tempBal = compTranService.getStockBalance(consigRmCompDt.getPurity().getId(),
						consigRmCompDt.getColor().getId(), consigRmCompDt.getDepartment().getId(),
						consigRmCompDt.getComponent().getId());

				if (tempBal == null) {
					tempBal = 0.0;
				}

				if (tempBal < consigRmCompDt.getMetalWt()) {
					return "Error : Stock Not Available (Available In Stock : " + tempBal + ")";
				}
 
				consigRmCompDt.setCreatedBy(principal.getName());
				consigRmCompDt.setCreatedDt(new java.util.Date());
				consigRmCompDt.setUniqueId(new Date().getTime());
				consigRmCompDt.setPurityConv(consigRmCompDt.getPurity().getPurityConv());

				compTran = new CompTran();
				retVal = "1";

			} else {

				ConsigRmCompDt consigRmCompDt2 = findOne(id);

				Double tempBal = compTranService.getStockBalance(consigRmCompDt2.getPurity().getId(),
						consigRmCompDt2.getColor().getId(), consigRmCompDt2.getDepartment().getId(),
						consigRmCompDt2.getComponent().getId());

				Double difference = consigRmCompDt.getMetalWt() - consigRmCompDt2.getMetalWt();

				if (tempBal < difference) {
					return "Error : Stock Not Available (Available In Stock : " + tempBal + ")";
				}

				consigRmCompDt.setModiBy(principal.getName());
				consigRmCompDt.setModiDt(new java.util.Date());

				compTran = compTranService.RefTranIdAndTranType(id, "ConsigRmComp");
				action = "updated";

				retVal = "2";
			}
			
			consigRmCompDt.setRate(compMtlRate);
			consigRmCompDt.setAmount(Math.round(compMtlRate * consigRmCompDt.getMetalWt())*100.0/100.0);
			
			consigRmCompDt.setConsigMt(consigMt);
			consigRmCompDt.setBalance(consigRmCompDt.getMetalWt());

			save(consigRmCompDt);


			if (action == "added") {
				/* consigRmCompDt2 = findByUniqueId(consigRmCompDt.getUniqueId()); */
				compTran.setCreatedBy(consigRmCompDt.getCreatedBy());
				compTran.setCreatedDt(consigRmCompDt.getCreatedDt());
				compTran.setTrandate(consigRmCompDt.getConsigMt().getInvDate());
			} else {
				/* consigRmCompDt2 = findOne(id); */
				compTran.setModiBy(principal.getName());
				compTran.setModiDt(new java.util.Date());
			}
			
			

			compTran.setPurity(consigRmCompDt.getPurity());
			compTran.setInOutFld("D");
			compTran.setBagMt(null);
			compTran.setBalance(consigRmCompDt.getMetalWt() * -1);
			compTran.setMetalWt(consigRmCompDt.getMetalWt());
			compTran.setDeptLocation(consigRmCompDt.getDepartment());
			compTran.setPurityConv(consigRmCompDt.getPurityConv());
			compTran.setRefTranId(consigRmCompDt.getId());
			compTran.setTranType("ConsigRmComp");
			compTran.setRemark("");
			compTran.setDepartment(consigRmCompDt.getDepartment());
			compTran.setPcs(consigRmCompDt.getQty());
			compTran.setComponent(consigRmCompDt.getComponent());
			compTran.setColor(consigRmCompDt.getColor());
			compTran.setMetalRate(compMtlRate);
			compTranService.save(compTran);

		} catch (Exception e) {

			return e.toString();
		}

		return retVal;
	}

	@Override
	public String consigRmCompDtDelete(Integer id, Principal principal) {
		String retVal = "-1";

		try {
			
			ConsigRmCompDt consigRmCompDt = findOne(id);

			if(consigRmCompDt.getAdjWt() > 0) {
				return "Can not delete, Loose Component";
			}else {
			delete(id);
			List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(id, "ConsigRmComp");
			for (CompTran comTran : compTran) {
				compTranService.delete(comTran.getId());
			}
			retVal = "1";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return retVal;
	}
	
	
	
	@Override
	public String consigRmCompDtListing(Integer mtId) {
		
	StringBuilder sb = new StringBuilder();
	
	ConsigMt consigMt = consigMtService.findOne(mtId);

	List<ConsigRmCompDt> consigRmCompDts= findByConsigMt(consigMt);
	
	sb.append("{\"total\":").append(consigRmCompDts.size()).append(",\"rows\": [");
	
	for (ConsigRmCompDt consigRmCompDt : consigRmCompDts) {
		sb.append("{\"id\":\"")
				.append(consigRmCompDt.getId())
				.append("\",\"metal\":\"")
				.append((consigRmCompDt.getMetal() != null ? consigRmCompDt.getMetal().getName() : ""))
				.append("\",\"component\":\"")
				.append((consigRmCompDt.getComponent() != null ? consigRmCompDt.getComponent().getName() : ""))
				.append("\",\"purity\":\"")
				.append((consigRmCompDt.getPurity() != null ? consigRmCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((consigRmCompDt.getColor() != null ? consigRmCompDt.getColor().getName() : ""))
				.append("\",\"department\":\"")
				.append((consigRmCompDt.getDepartment() != null ? consigRmCompDt.getDepartment().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append(consigRmCompDt.getMetalWt())
				.append("\",\"rate\":\"")
				.append(consigRmCompDt.getRate())
				.append("\",\"amount\":\"")
				.append(consigRmCompDt.getAmount())
				.append("\",\"qty\":\"")
				.append(consigRmCompDt.getQty());
							
				sb.append("\",\"action1\":\"")
				
				.append("<a href='javascript:editConsigRmCompDt(").append(consigRmCompDt.getId())
				.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
			
				.append("\",\"action2\":\"")
				.append("<a href='javascript:deleteConsigRmCompDt(event,")
				  .append(consigRmCompDt.getId()).append(", 0);' href='javascript:void(0);'")
				.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(consigRmCompDt.getId())
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
	public String rowCompDtDetails(Integer mtId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		List<ConsigRmCompDt> consigRmCompDts = findByConsigMt(consigMtService.findOne(mtId));
		
		sb.append("{\"total\":").append(consigRmCompDts.size()).append(",\"rows\": [");
		
		for (ConsigRmCompDt consigRmCompDt : consigRmCompDts) {
			
			Double issueWt =0.0; 
			
			sb.append("{\"id\":\"")
					.append(consigRmCompDt.getId())
					.append("\",\"department\":\"")
					.append((consigRmCompDt.getDepartment() != null ? consigRmCompDt.getDepartment().getName() : ""))
					.append("\",\"metal\":\"")
					.append((consigRmCompDt.getMetal() != null ? consigRmCompDt.getMetal().getName() : ""))
					.append("\",\"purity\":\"")
					.append((consigRmCompDt.getPurity() != null ? consigRmCompDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((consigRmCompDt.getColor() != null ? consigRmCompDt.getColor().getName() : ""))
					.append("\",\"component\":\"")
					.append((consigRmCompDt.getComponent() != null ? consigRmCompDt.getComponent().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(consigRmCompDt.getMetalWt())
					.append("\",\"balanceWt\":\"")
					.append(consigRmCompDt.getBalance())
					.append("\",\"issueWt\":\"")
					.append(issueWt)
					.append("\",\"rate\":\"")
					.append(consigRmCompDt.getRate())
					.append("\",\"amount\":\"")
					.append(consigRmCompDt.getAmount());
					
					sb.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	
	
	
	
	
	
	
}

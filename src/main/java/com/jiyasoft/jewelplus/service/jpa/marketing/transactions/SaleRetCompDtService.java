package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetCompDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetDtService;

@Service
@Repository
@Transactional
public class SaleRetCompDtService implements ISaleRetCompDtService {

	@Autowired
	private ISaleRetCompDtRepository saleRetCompDtRepository;
	
	@Autowired
	private ISaleRetDtService saleRetDtService;
	
	
	@Override
	public void save(SaleRetCompDt saleRetCompDt) {
		
		saleRetCompDtRepository.save(saleRetCompDt);
	}

	@Override
	public void delete(int id) {
		saleRetCompDtRepository.delete(id);
		
	}

	@Override
	public SaleRetCompDt findOne(int id) {
		
		return saleRetCompDtRepository.findOne(id);
	}

	@Override
	public List<SaleRetCompDt> findBySaleRetDt(SaleRetDt saleRetDt) {
	
		return saleRetCompDtRepository.findBySaleRetDt(saleRetDt);
	}

	@Override
	public String saleRetCompDtListing(Integer dtId,String disableFlg) {
		
		SaleRetDt saleRetDt = saleRetDtService.findOne(dtId);
		List<SaleRetCompDt> saleRetCompDts = findBySaleRetDt(saleRetDt);
	
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleRetCompDts.size()).append(",\"rows\": [");
		  for(SaleRetCompDt saleRetCompDt :saleRetCompDts){
				sb.append("{\"id\":\"")
				.append(saleRetCompDt.getId())
				.append("\",\"compName\":\"")
				.append((saleRetCompDt.getComponent() != null ? saleRetCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((saleRetCompDt.getPurity() != null ? saleRetCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((saleRetCompDt.getColor() != null ? saleRetCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((saleRetCompDt.getCompWt() != null ? saleRetCompDt.getCompWt() : ""))
				.append("\",\"rate\":\"")
				.append((saleRetCompDt.getCompRate() != null ? saleRetCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((saleRetCompDt.getCompValue() != null ? saleRetCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((saleRetCompDt.getCompQty() != null ? saleRetCompDt.getCompQty() : ""))
				.append("\",\"rLock\":\"")
				.append((saleRetCompDt.getrLock())); //1 = lock & 0 = unlock
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doSaleDtLockUnLock(")
				.append(saleRetCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editSaleRetCompDt(")
				.append(saleRetCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteSaleRetCompDt(event,")
				.append(saleRetCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(saleRetCompDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
				}else {
					 sb.append("\",\"actionLock\":\"")
						.append("")
						.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						 .append("\"},");
				}
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public void setSaleRetCompDt(List<DesignComponent> designComponents, SaleRetMt saleRetMt, SaleRetDt saleRetDt,
			Principal principal) {
		// TODO Auto-generated method stub
		
		if(designComponents != null){
			
			for(DesignComponent designComponent : designComponents){
			
			SaleRetCompDt saleRetCompDt = new SaleRetCompDt();
			
			saleRetCompDt.setSaleRetDt(saleRetDt);
			saleRetCompDt.setSaleRetMt(saleRetMt);
			saleRetCompDt.setComponent(designComponent.getComponent());
			saleRetCompDt.setCompQty(designComponent.getQuantity());
			saleRetCompDt.setCreatedBy(principal.getName());
			saleRetCompDt.setCreatedDate(new java.util.Date());
			saleRetCompDt.setPurity(saleRetDt.getPurity());
			saleRetCompDt.setColor(saleRetDt.getColor());
			saleRetCompDt.setCompWt(designComponent.getCompWt());
								
			save(saleRetCompDt);
			
			}
			
			
		}
	}

}

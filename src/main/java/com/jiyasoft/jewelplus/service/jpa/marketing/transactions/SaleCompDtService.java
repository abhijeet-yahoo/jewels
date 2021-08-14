package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleCompDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;

@Service
@Repository
@Transactional
public class SaleCompDtService implements ISaleCompDtService {
	
	@Autowired
	private ISaleCompDtRepository saleCompDtRepository;
	
	@Autowired
	private ISaleDtService saleDtService;

	@Override
	public void save(SaleCompDt saleCompDt) {
		// TODO Auto-generated method stub
		saleCompDtRepository.save(saleCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		saleCompDtRepository.delete(id);
		
	}

	@Override
	public SaleCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return saleCompDtRepository.findOne(id);
	}

	@Override
	public List<SaleCompDt> findBySaleMt(SaleMt saleMt) {
		// TODO Auto-generated method stub
		return saleCompDtRepository.findBySaleMt(saleMt);
	}

	@Override
	public List<SaleCompDt> findBySaleDt(SaleDt saleDt) {
		// TODO Auto-generated method stub
		return saleCompDtRepository.findBySaleDt(saleDt);
	}

	@Override
	public String saleCompDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
		SaleDt saleDt = saleDtService.findOne(dtId);
		List<SaleCompDt> saleCompDts = findBySaleDt(saleDt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleCompDts.size()).append(",\"rows\": [");
		  for(SaleCompDt saleCompDt :saleCompDts){
				sb.append("{\"id\":\"")
				.append(saleCompDt.getId())
				.append("\",\"compName\":\"")
				.append((saleCompDt.getComponent() != null ? saleCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((saleCompDt.getPurity() != null ? saleCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((saleCompDt.getColor() != null ? saleCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((saleCompDt.getCompWt() != null ? saleCompDt.getCompWt() : ""))
				.append("\",\"rate\":\"")
				.append((saleCompDt.getCompRate() != null ? saleCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((saleCompDt.getCompValue() != null ? saleCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((saleCompDt.getCompQty() != null ? saleCompDt.getCompQty() : ""))
				.append("\",\"rLock\":\"")
				.append((saleCompDt.getrLock())); //1 = lock & 0 = unlock
				
				if(disableFlg.equalsIgnoreCase("false")) {
				
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doSaleDtLockUnLock(")
				.append(saleCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editSaleCompDt(")
				.append(saleCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteSaleCompDt(event,")
				.append(saleCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(saleCompDt.getId())
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

}

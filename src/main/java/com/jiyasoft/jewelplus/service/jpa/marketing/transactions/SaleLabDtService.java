package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleLabDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleLabDtService;

@Service
@Repository
@Transactional
public class SaleLabDtService implements ISaleLabDtService{
	
	@Autowired
	private ISaleLabDtRepository saleLabDtRepository;
	
	@Autowired
	private ISaleDtService saleDtService;

	@Override
	public List<SaleLabDt> findBySaleMt(SaleMt saleMt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleLabDt> findBySaleDt(SaleDt saleDt) {
		// TODO Auto-generated method stub
		return saleLabDtRepository.findBySaleDt(saleDt);
	}

	@Override
	public List<SaleLabDt> findBySaleDtAndMetal(SaleDt SaleDt, Metal metal) {
		// TODO Auto-generated method stub
		return saleLabDtRepository.findBySaleDtAndMetal(SaleDt, metal);
	}

	@Override
	public void save(SaleLabDt saleLabDt) {
		// TODO Auto-generated method stub
		saleLabDtRepository.save(saleLabDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		saleLabDtRepository.delete(id);
		
	}

	@Override
	public SaleLabDt findOne(int id) {
		// TODO Auto-generated method stub
		return saleLabDtRepository.findOne(id);
	}

	@Override
	public String saleLabDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
		SaleDt saleDt = saleDtService.findOne(dtId);
		List<SaleLabDt> saleLabDts = findBySaleDt(saleDt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleLabDts.size()).append(",\"rows\": [");
		  for(SaleLabDt saleLabDt :saleLabDts){
				sb.append("{\"id\":\"")
				.append(saleLabDt.getId())
				.append("\",\"metal\":\"")
				.append((saleLabDt.getMetal() != null ? saleLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((saleLabDt.getLabourType() != null ? saleLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((saleLabDt.getLabourRate() != null ? saleLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((saleLabDt.getLabourValue() != null ? saleLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(saleLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(saleLabDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(saleLabDt.getPerGramRate())
				.append("\",\"percent\":\"")
				.append(saleLabDt.getPercentage())
				.append("\",\"perCaratRate\":\"")
				.append(saleLabDt.getPerCaratRate());
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLockItem(")
				.append(saleLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editConsigLabDtItem(")
				.append(saleLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteConsigLabDtItem(event,")
				.append(saleLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(saleLabDt.getId())
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

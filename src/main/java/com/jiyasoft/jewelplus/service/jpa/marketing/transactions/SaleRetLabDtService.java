package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetLabDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetLabDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetLabDtService;

@Service
@Repository
@Transactional
public class SaleRetLabDtService implements ISaleRetLabDtService {

	@Autowired
	private ISaleRetLabDtRepository saleRetLabDtRepository;
	
	@Autowired
	private ISaleRetDtService saleRetDtService;
	
	
	@Override
	public void save(SaleRetLabDt saleRetLabDt) {
		
		saleRetLabDtRepository.save(saleRetLabDt);	
		
	}

	@Override
	public void delete(int id) {
		saleRetLabDtRepository.delete(id);
		
	}

	@Override
	public SaleRetLabDt findOne(int id) {
		
		return saleRetLabDtRepository.findOne(id);
		
	}

	@Override
	public List<SaleRetLabDt> findBySaleRetDt(SaleRetDt saleRetDt) {
		
		return saleRetLabDtRepository.findBySaleRetDt(saleRetDt);
	}

	@Override
	public List<SaleRetLabDt> findBySaleRetDtAndMetal(SaleRetDt saleRetDt, Metal metal) {
		
		return saleRetLabDtRepository.findBySaleRetDtAndMetal(saleRetDt, metal);
	}

	@Override
	public String saleRetLabDtListing(Integer dtId,String disableFlg) {
		
		SaleRetDt saleRetDt = saleRetDtService.findOne(dtId);
		List<SaleRetLabDt> saleRetLabDts = findBySaleRetDt(saleRetDt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleRetLabDts.size()).append(",\"rows\": [");
		  for(SaleRetLabDt saleRetLabDt :saleRetLabDts){
				sb.append("{\"id\":\"")
				.append(saleRetLabDt.getId())
				.append("\",\"metal\":\"")
				.append((saleRetLabDt.getMetal() != null ? saleRetLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((saleRetLabDt.getLabourType() != null ? saleRetLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((saleRetLabDt.getLabourRate() != null ? saleRetLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((saleRetLabDt.getLabourValue() != null ? saleRetLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(saleRetLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(saleRetLabDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(saleRetLabDt.getPerGramRate())
				.append("\",\"percent\":\"")
				.append(saleRetLabDt.getPercentage())
				.append("\",\"perCaratRate\":\"")
				.append(saleRetLabDt.getPerCaratRate());
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLockItem(")
				.append(saleRetLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editSaleRetLabDtItem(")
				.append(saleRetLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteSaleLabDtItem(event,")
				.append(saleRetLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(saleRetLabDt.getId())
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

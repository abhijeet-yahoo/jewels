package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfLabDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStkTrfLabDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfLabDtService;

@Service
@Repository
@Transactional
public class StkTrfLabDtService implements IStkTrfLabDtService{
	
	@Autowired
	private IStkTrfLabDtRepository stkTrfLabDtRepository;
	
	@Autowired
	private IStkTrfDtService stkTrfDtService;

	@Override
	public void save(StkTrfLabDt stkTrfLabDt) {
		// TODO Auto-generated method stub
		stkTrfLabDtRepository.save(stkTrfLabDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	 stkTrfLabDtRepository.delete(id);
		
	}

	@Override
	public StkTrfLabDt findOne(int id) {
		// TODO Auto-generated method stub
		return stkTrfLabDtRepository.findOne(id);
	}

	@Override
	public List<StkTrfLabDt> findByStkTrfDt(StkTrfDt stkTrfDt) {
		// TODO Auto-generated method stub
		return stkTrfLabDtRepository.findByStkTrfDt(stkTrfDt);
	}

	@Override
	public List<StkTrfLabDt> findByStkTrfDtAndMetal(StkTrfDt stkTrfDt, Metal metal) {
		// TODO Auto-generated method stub
		return stkTrfLabDtRepository.findByStkTrfDtAndMetal(stkTrfDt, metal);
	}

	@Override
	public String stkTrfLabDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
		
		StkTrfDt stkTrfDt = stkTrfDtService.findOne(dtId);
		List<StkTrfLabDt> stkTrfLabDts = findByStkTrfDt(stkTrfDt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stkTrfLabDts.size()).append(",\"rows\": [");
		  for(StkTrfLabDt stkTrfLabDt :stkTrfLabDts){
				sb.append("{\"id\":\"")
				.append(stkTrfLabDt.getId())
				.append("\",\"metal\":\"")
				.append((stkTrfLabDt.getMetal() != null ? stkTrfLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((stkTrfLabDt.getLabourType() != null ? stkTrfLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((stkTrfLabDt.getLabourRate() != null ? stkTrfLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((stkTrfLabDt.getLabourValue() != null ? stkTrfLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(stkTrfLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(stkTrfLabDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(stkTrfLabDt.getPerGramRate())
				.append("\",\"percent\":\"")
				.append(stkTrfLabDt.getPercentage())
				.append("\",\"perCaratRate\":\"")
				.append(stkTrfLabDt.getPerCaratRate());
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLockItem(")
				.append(stkTrfLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editConsigLabDtItem(")
				.append(stkTrfLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteConsigLabDtItem(event,")
				.append(stkTrfLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(stkTrfLabDt.getId())
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

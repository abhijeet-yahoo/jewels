package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigLabDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigLabDtService;

@Service
@Repository
@Transactional
public class ConsigLabDtService implements IConsigLabDtService{
	
	@Autowired
	private IConsigLabDtRepository consigLabDtRepository;
	
	@Autowired
	private IConsigDtService consigDtService;

	@Override
	public void save(ConsigLabDt consigLabDt) {
		// TODO Auto-generated method stub
		consigLabDtRepository.save(consigLabDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigLabDtRepository.delete(id);
	}

	@Override
	public ConsigLabDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigLabDtRepository.findOne(id);
	}

	@Override
	public List<ConsigLabDt> findByConsigDt(ConsigDt consigDt) {
		// TODO Auto-generated method stub
		return consigLabDtRepository.findByConsigDt(consigDt);
	}

	@Override
	public List<ConsigLabDt> findByConsigDtAndMetal(ConsigDt consigDt, Metal metal) {
		// TODO Auto-generated method stub
		return consigLabDtRepository.findByConsigDtAndMetal(consigDt, metal);
	}

	@Override
	public String consigLabDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
		ConsigDt consigDt=consigDtService.findOne(dtId);
		List<ConsigLabDt>consigLabDts = findByConsigDt(consigDt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigLabDts.size()).append(",\"rows\": [");
		  for(ConsigLabDt consigLabDt :consigLabDts){
				sb.append("{\"id\":\"")
				.append(consigLabDt.getId())
				.append("\",\"metal\":\"")
				.append((consigLabDt.getMetal() != null ? consigLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((consigLabDt.getLabourType() != null ? consigLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((consigLabDt.getLabourRate() != null ? consigLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((consigLabDt.getLabourValue() != null ? consigLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(consigLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(consigLabDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(consigLabDt.getPerGramRate())
				.append("\",\"percent\":\"")
				.append(consigLabDt.getPercentage())
				.append("\",\"perCaratRate\":\"")
				.append(consigLabDt.getPerCaratRate());
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLockItem(")
				.append(consigLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editConsigLabDtItem(")
				.append(consigLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteConsigLabDtItem(event,")
				.append(consigLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(consigLabDt.getId())
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

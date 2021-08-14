package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetLabDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetLabDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetLabDtService;

@Service
@Repository
@Transactional
public class ConsigRetLabDtService implements IConsigRetLabDtService{
	
	@Autowired
	private IConsigRetLabDtRepository consigRetLabDtRepository;
	
	@Autowired
	private IConsigRetDtService consigRetDtService;

	@Override
	public void save(ConsigRetLabDt consigRetLabDt) {
		// TODO Auto-generated method stub
		consigRetLabDtRepository.save(consigRetLabDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigRetLabDtRepository.delete(id);
	}

	@Override
	public ConsigRetLabDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigRetLabDtRepository.findOne(id);
	}

	@Override
	public List<ConsigRetLabDt> findByConsigRetDt(ConsigRetDt consigRetDt) {
		// TODO Auto-generated method stub
		return consigRetLabDtRepository.findByConsigRetDt(consigRetDt);
	}

	@Override
	public List<ConsigRetLabDt> findByConsigRetDtAndMetal(ConsigRetDt consigRetDt, Metal metal) {
		// TODO Auto-generated method stub
		return consigRetLabDtRepository.findByConsigRetDtAndMetal(consigRetDt, metal);
	}

	@Override
	public String consigRetLabDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
		
		ConsigRetDt consigRetDt = consigRetDtService.findOne(dtId);
		List<ConsigRetLabDt> consigRetLabDts = findByConsigRetDt(consigRetDt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigRetLabDts.size()).append(",\"rows\": [");
		  for(ConsigRetLabDt consigRetLabDt :consigRetLabDts){
				sb.append("{\"id\":\"")
				.append(consigRetLabDt.getId())
				.append("\",\"metal\":\"")
				.append((consigRetLabDt.getMetal() != null ? consigRetLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((consigRetLabDt.getLabourType() != null ? consigRetLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((consigRetLabDt.getLabourRate() != null ? consigRetLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((consigRetLabDt.getLabourValue() != null ? consigRetLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(consigRetLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(consigRetLabDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(consigRetLabDt.getPerGramRate())
				.append("\",\"percent\":\"")
				.append(consigRetLabDt.getPercentage())
				.append("\",\"perCaratRate\":\"")
				.append(consigRetLabDt.getPerCaratRate());
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLockItem(")
				.append(consigRetLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editConsigRetLabDtItem(")
				.append(consigRetLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteConsigLabDtItem(event,")
				.append(consigRetLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(consigRetLabDt.getId())
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

package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetCompDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetDtService;

@Service
@Repository
@Transactional
public class ConsigRetCompDtService implements IConsigRetCompDtService{
	
	@Autowired
	private IConsigRetCompDtRepository consigRetCompDtRepository;
	
	@Autowired
	private IConsigRetDtService consigRetDtService;

	@Override
	public void save(ConsigRetCompDt consigRetCompDt) {
		// TODO Auto-generated method stub
		consigRetCompDtRepository.save(consigRetCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigRetCompDtRepository.delete(id);
	
	}

	@Override
	public ConsigRetCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigRetCompDtRepository.findOne(id);
	}

	@Override
	public List<ConsigRetCompDt> findByConsigRetDt(ConsigRetDt consigRetDt) {
		// TODO Auto-generated method stub
		return consigRetCompDtRepository.findByConsigRetDt(consigRetDt);
	}

	@Override
	public String consigRetCompDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
	
		ConsigRetDt consigRetDt = consigRetDtService.findOne(dtId);
		List<ConsigRetCompDt> consigRetCompDts = findByConsigRetDt(consigRetDt);
	
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigRetCompDts.size()).append(",\"rows\": [");
		  for(ConsigRetCompDt consigRetCompDt :consigRetCompDts){
				sb.append("{\"id\":\"")
				.append(consigRetCompDt.getId())
				.append("\",\"compName\":\"")
				.append((consigRetCompDt.getComponent() != null ? consigRetCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((consigRetCompDt.getPurity() != null ? consigRetCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((consigRetCompDt.getColor() != null ? consigRetCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((consigRetCompDt.getCompWt() != null ? consigRetCompDt.getCompWt() : ""))
				.append("\",\"rate\":\"")
				.append((consigRetCompDt.getCompRate() != null ? consigRetCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((consigRetCompDt.getCompValue() != null ? consigRetCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((consigRetCompDt.getCompQty() != null ? consigRetCompDt.getCompQty() : ""))
				.append("\",\"rLock\":\"")
				.append((consigRetCompDt.getrLock())); //1 = lock & 0 = unlock
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doConsigDtLockUnLock(")
				.append(consigRetCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editConsigRetCompDt(")
				.append(consigRetCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteConsigRetCompDt(event,")
				.append(consigRetCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(consigRetCompDt.getId())
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

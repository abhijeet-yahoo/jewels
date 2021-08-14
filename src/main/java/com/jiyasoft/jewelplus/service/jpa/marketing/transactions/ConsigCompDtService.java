package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigCompDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;

@Service
@Repository
@Transactional
public class ConsigCompDtService implements IConsigCompDtService {
	
	@Autowired
	private IConsigCompDtRepository consigCompDtRepository;
	
	@Autowired
	private IConsigDtService consigDtService;
	

	@Override
	public void save(ConsigCompDt consigCompDt) {
		// TODO Auto-generated method stub
		consigCompDtRepository.save(consigCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigCompDtRepository.delete(id);
	
	}

	@Override
	public ConsigCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigCompDtRepository.findOne(id);
	}

	@Override
	public List<ConsigCompDt> findByConsigDt(ConsigDt consigDt) {
		// TODO Auto-generated method stub
		return consigCompDtRepository.findByConsigDt(consigDt);
	}

	@Override
	public String consigCompDtListing(Integer dtId) {
		// TODO Auto-generated method stub
		ConsigDt consigDt = consigDtService.findOne(dtId);
		List<ConsigCompDt> consigCompDts = findByConsigDt(consigDt);
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigCompDts.size()).append(",\"rows\": [");
		  for(ConsigCompDt consigCompDt :consigCompDts){
				sb.append("{\"id\":\"")
				.append(consigCompDt.getId())
				.append("\",\"compName\":\"")
				.append((consigCompDt.getComponent() != null ? consigCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((consigCompDt.getPurity() != null ? consigCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((consigCompDt.getColor() != null ? consigCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((consigCompDt.getCompWt() != null ? consigCompDt.getCompWt() : ""))
				.append("\",\"rate\":\"")
				.append((consigCompDt.getCompRate() != null ? consigCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((consigCompDt.getCompValue() != null ? consigCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((consigCompDt.getCompQty() != null ? consigCompDt.getCompQty() : ""))
				.append("\",\"rLock\":\"")
				.append((consigCompDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doConsigDtLockUnLock(")
				.append(consigCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editConsigCompDt(")
				.append(consigCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteConsigCompDt(event,")
				.append(consigCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(consigCompDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	}

}

package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMetalDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetMetalDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMetalDtService;

@Service
@Repository
@Transactional
public class ConsigRetMetalDtService implements IConsigRetMetalDtService{
	
	@Autowired
	private IConsigRetMetalDtRepository consigRetMetalDtRepository;
	
	@Autowired
	private IConsigRetDtService consigRetDtService;
	


	@Override
	public void save(ConsigRetMetalDt consigRetMetalDt) {
		// TODO Auto-generated method stub
		consigRetMetalDtRepository.save(consigRetMetalDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		 consigRetMetalDtRepository.delete(id);
	
	}

	@Override
	public ConsigRetMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigRetMetalDtRepository.findOne(id);
	}

	@Override
	public List<ConsigRetMetalDt> findByConsigRetDt(ConsigRetDt consigRetDt) {
		// TODO Auto-generated method stub
		return consigRetMetalDtRepository.findByConsigRetDt(consigRetDt);
	}

	@Override
	public ConsigRetMetalDt findByConsigRetDtAndPartNm(ConsigRetDt consigRetDt,
			LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return consigRetMetalDtRepository.findByConsigRetDtAndPartNm(consigRetDt, lookUpMast);
	}

	@Override
	public String consigRetMetalDtListing(Integer dtId) {
		// TODO Auto-generated method stub
		ConsigRetDt consigRetDt = consigRetDtService.findOne(dtId);
		List<ConsigRetMetalDt> consigRetMetalDts = findByConsigRetDt(consigRetDt);
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigRetMetalDts.size()).append(",\"rows\": [");
		  for(ConsigRetMetalDt consigRetMetalDt :consigRetMetalDts){
			 sb.append("{\"id\":\"")
				.append(consigRetMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((consigRetMetalDt.getPurity() != null ? consigRetMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((consigRetMetalDt.getColor() != null ? consigRetMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((consigRetMetalDt.getPartNm() != null ? consigRetMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((consigRetMetalDt.getMetalPcs() != null ? consigRetMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((consigRetMetalDt.getMetalWeight() != null ? consigRetMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((consigRetMetalDt.getMetalRate() != null ? consigRetMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((consigRetMetalDt.getPerGramRate() != null ? consigRetMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((consigRetMetalDt.getLossPerc() != null ? consigRetMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((consigRetMetalDt.getMetalValue() != null ? consigRetMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(consigRetMetalDt.getMainMetal())
	    		.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}



}

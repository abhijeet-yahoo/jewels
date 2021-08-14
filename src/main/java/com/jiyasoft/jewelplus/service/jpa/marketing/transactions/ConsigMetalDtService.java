package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMetalDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigMetalDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMetalDtService;

@Service
@Repository
@Transactional
public class ConsigMetalDtService implements IConsigMetalDtService {

	@Autowired
	private IConsigMetalDtRepository consigMetalDtRepository;
	
	@Autowired
	private IConsigDtService consigDtService;
	
	@Override
	public void save(ConsigMetalDt consigMetalDt) {
		// TODO Auto-generated method stub
		consigMetalDtRepository.save(consigMetalDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigMetalDtRepository.delete(id);
		
	}

	@Override
	public ConsigMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigMetalDtRepository.findOne(id);
	}

	@Override
	public List<ConsigMetalDt> findByConsigDt(ConsigDt consigDt) {
		// TODO Auto-generated method stub
		return consigMetalDtRepository.findByConsigDt(consigDt);
	}

	@Override
	public ConsigMetalDt findByConsigDtAndPartNm(ConsigDt consigDt,
			LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return consigMetalDtRepository.findByConsigDtAndPartNm(consigDt, lookUpMast);
	}

	@Override
	public String consigMetalDtListing(Integer dtId) {
		// TODO Auto-generated method stub
		ConsigDt consigDt = consigDtService.findOne(dtId);
		List<ConsigMetalDt> consigMetalDts = findByConsigDt(consigDt);
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigMetalDts.size()).append(",\"rows\": [");
		  for(ConsigMetalDt consigMetalDt :consigMetalDts){
			 sb.append("{\"id\":\"")
				.append(consigMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((consigMetalDt.getPurity() != null ? consigMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((consigMetalDt.getColor() != null ? consigMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((consigMetalDt.getPartNm() != null ? consigMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((consigMetalDt.getMetalPcs() != null ? consigMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((consigMetalDt.getMetalWeight() != null ? consigMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((consigMetalDt.getMetalRate() != null ? consigMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((consigMetalDt.getPerGramRate() != null ? consigMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((consigMetalDt.getLossPerc() != null ? consigMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((consigMetalDt.getMetalValue() != null ? consigMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(consigMetalDt.getMainMetal())
	    		.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

}

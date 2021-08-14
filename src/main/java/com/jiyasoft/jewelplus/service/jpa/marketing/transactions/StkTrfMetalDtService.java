package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMetalDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStkTrfMetalDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfMetalDtService;

@Service
@Repository
@Transactional
public class StkTrfMetalDtService implements IStkTrfMetalDtService {
	
	@Autowired
	private IStkTrfMetalDtRepository stkTrfMetalDtRepository;
	
	@Autowired
	private IStkTrfDtService stkTrfDtService;

	@Override
	public void save(StkTrfMetalDt stkTrfMetalDt) {
		// TODO Auto-generated method stub
		stkTrfMetalDtRepository.save(stkTrfMetalDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	stkTrfMetalDtRepository.delete(id);
		
	}

	@Override
	public StkTrfMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return stkTrfMetalDtRepository.findOne(id);
	}

	@Override
	public List<StkTrfMetalDt> findByStkTrfDt(StkTrfDt stkTrfDt) {
		// TODO Auto-generated method stub
		return stkTrfMetalDtRepository.findByStkTrfDt(stkTrfDt);
	}

	@Override
	public StkTrfMetalDt findByStkTrfDtAndPartNm(StkTrfDt stkTrfDt,
			LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return stkTrfMetalDtRepository.findByStkTrfDtAndPartNm(stkTrfDt, lookUpMast);
	}

	@Override
	public String stkTrfMetalDtListing(Integer dtId) {
		// TODO Auto-generated method stub
		
		StkTrfDt stkTrfDt = stkTrfDtService.findOne(dtId);
		List<StkTrfMetalDt> stkTrfMetalDts = findByStkTrfDt(stkTrfDt);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stkTrfMetalDts.size()).append(",\"rows\": [");
		  for(StkTrfMetalDt stkTrfMetalDt :stkTrfMetalDts){
			 sb.append("{\"id\":\"")
				.append(stkTrfMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((stkTrfMetalDt.getPurity() != null ? stkTrfMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((stkTrfMetalDt.getColor() != null ? stkTrfMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((stkTrfMetalDt.getPartNm() != null ? stkTrfMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((stkTrfMetalDt.getMetalPcs() != null ? stkTrfMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((stkTrfMetalDt.getMetalWeight() != null ? stkTrfMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((stkTrfMetalDt.getMetalRate() != null ? stkTrfMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((stkTrfMetalDt.getPerGramRate() != null ? stkTrfMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((stkTrfMetalDt.getLossPerc() != null ? stkTrfMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((stkTrfMetalDt.getMetalValue() != null ? stkTrfMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(stkTrfMetalDt.getMainMetal())
	    		.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

}

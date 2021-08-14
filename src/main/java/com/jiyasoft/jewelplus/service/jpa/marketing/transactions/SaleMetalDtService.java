package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleMetalDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMetalDtService;

@Service
@Repository
@Transactional
public class SaleMetalDtService implements ISaleMetalDtService{
	
	@Autowired
	private ISaleMetalDtRepository saleMetalDtRepository;

	@Autowired
	private ISaleDtService saleDtService;
	
	@Override
	public void save(SaleMetalDt saleMetalDt) {
		// TODO Auto-generated method stub
		saleMetalDtRepository.save(saleMetalDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		saleMetalDtRepository.delete(id);
		
	}

	@Override
	public SaleMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return saleMetalDtRepository.findOne(id);
	}

	@Override
	public List<SaleMetalDt> findBySaleMt(SaleMt saleMt) {
		// TODO Auto-generated method stub
		return saleMetalDtRepository.findBySaleMt(saleMt);
	}

	@Override
	public List<SaleMetalDt> findBySaleDt(SaleDt saleDt) {
		// TODO Auto-generated method stub
		return saleMetalDtRepository.findBySaleDt(saleDt);
	}

	@Override
	public SaleMetalDt findBySaleDtAndMainMetal(SaleDt saleDt, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return saleMetalDtRepository.findBySaleDtAndMainMetal(saleDt, mainMetal);
	}

	@Override
	public String saleMetalDtListing(Integer dtId) {
		// TODO Auto-generated method stub
	
		
		SaleDt saleDt = saleDtService.findOne(dtId);
		List<SaleMetalDt> saleMetalDts = findBySaleDt(saleDt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleMetalDts.size()).append(",\"rows\": [");
		  for(SaleMetalDt saleMetalDt :saleMetalDts){
			 sb.append("{\"id\":\"")
				.append(saleMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((saleMetalDt.getPurity() != null ? saleMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((saleMetalDt.getColor() != null ? saleMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((saleMetalDt.getPartNm() != null ? saleMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((saleMetalDt.getMetalPcs() != null ? saleMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((saleMetalDt.getMetalWeight() != null ? saleMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((saleMetalDt.getMetalRate() != null ? saleMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((saleMetalDt.getPerGramRate() != null ? saleMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((saleMetalDt.getLossPerc() != null ? saleMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((saleMetalDt.getMetalValue() != null ? saleMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(saleMetalDt.getMainMetal())
	    		.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	
}

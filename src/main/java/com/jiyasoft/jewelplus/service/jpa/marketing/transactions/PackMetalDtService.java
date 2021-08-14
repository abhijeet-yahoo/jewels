package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IPackMetalDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalDtService;

@Service
@Repository
@Transactional
public class PackMetalDtService implements IPackMetalDtService {
	
	@Autowired
	private IPackMetalDtRepository packMetalDtRepository;
	
	@Autowired
	private IPackDtService packDtService;

	@Override
	public void save(PackMetalDt packMetalDt) {
		// TODO Auto-generated method stub
		packMetalDtRepository.save(packMetalDt);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		packMetalDtRepository.delete(id);
		
	}

	@Override
	public PackMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return packMetalDtRepository.findOne(id);
	}

	@Override
	public List<PackMetalDt> findByPackDt(PackDt packDt) {
		// TODO Auto-generated method stub
		return packMetalDtRepository.findByPackDt(packDt);
	}

	@Override
	public PackMetalDt findByPackDtAndPartNm(PackDt packDt, LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return packMetalDtRepository.findByPackDtAndPartNm(packDt, lookUpMast);
	}

	@Override
	public String packMetalDtListing(Integer dtId) {
		// TODO Auto-generated method stub
		PackDt packDt=packDtService.findOne(dtId);
		List<PackMetalDt>packMetalDts=findByPackDt(packDt);
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(packMetalDts.size()).append(",\"rows\": [");
		  for(PackMetalDt packMetalDt :packMetalDts){
			 sb.append("{\"id\":\"")
				.append(packMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((packMetalDt.getPurity() != null ? packMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((packMetalDt.getColor() != null ? packMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((packMetalDt.getPartNm() != null ? packMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((packMetalDt.getMetalPcs() != null ? packMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((packMetalDt.getMetalWeight() != null ? packMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((packMetalDt.getMetalRate() != null ? packMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((packMetalDt.getPerGramRate() != null ? packMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((packMetalDt.getLossPerc() != null ? packMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((packMetalDt.getMetalValue() != null ? packMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(packMetalDt.getMainMetal())
	    		.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public String updateLossPerc(Principal principal, Integer packDtId, Double lossPerc) {
		// TODO Auto-generated method stub
		
		if(lossPerc > 0) {
			PackMetalDt packMetalDt = findOne(packDtId);
			packMetalDt.setLossPerc(lossPerc);
			
			Double vLossRate = Math.round((packMetalDt.getPerGramRate() * lossPerc/100)*100.0)/100.0;
			
			Double vPerGramRate = packMetalDt.getPerGramRate() + vLossRate;
			
			packMetalDt.setPerGramRate(vPerGramRate);
			save(packMetalDt);
			
			packDtService.updateFob(packMetalDt.getPackDt());
		
		}
		
		return null;
	}

	@Override
	public PackMetalDt findByPackDtAndMainMetal(PackDt packDt, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return packMetalDtRepository.findByPackDtAndMainMetal(packDt, true);
	}

}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMetalDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IRepairRetMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetMetalDtService;



@Service
@Repository
@Transactional
public class RepairRetMetalDtService implements IRepairRetMetalDtService {
	
	@Autowired
	private IRepairRetMetalDtRepository repairRetMetalDtRepository;

	@Autowired
	private IRepairRetDtService repairRetDtService;
	
	@Override
	public List<RepairRetMetalDt> findByRepairRetDt(RepairRetDt repairRetDt) {
		// TODO Auto-generated method stub
		return repairRetMetalDtRepository.findByRepairRetDt(repairRetDt);
	}

	@Override
	public RepairRetMetalDt findByRepairRetDtAndPartNm(RepairRetDt repairRetDt, LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return repairRetMetalDtRepository.findByRepairRetDtAndPartNm(repairRetDt, lookUpMast);
	}

	@Override
	public void save(RepairRetMetalDt repairRetMetalDt) {
		// TODO Auto-generated method stub
		repairRetMetalDtRepository.save(repairRetMetalDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		repairRetMetalDtRepository.delete(id);
	}

	@Override
	public RepairRetMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return repairRetMetalDtRepository.findOne(id);
	}

	@Override
	public String repairRetMetalDtListing(Integer dtId) {
		// TODO Auto-generated method stub
		
		RepairRetDt repairRetDt = repairRetDtService.findOne(dtId);
		List<RepairRetMetalDt> repairRetMetalDts =findByRepairRetDt(repairRetDt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(repairRetMetalDts.size()).append(",\"rows\": [");
		  for(RepairRetMetalDt repairRetMetalDt :repairRetMetalDts){
			 sb.append("{\"id\":\"")
				.append(repairRetMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((repairRetMetalDt.getPurity() != null ? repairRetMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((repairRetMetalDt.getColor() != null ? repairRetMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((repairRetMetalDt.getPartNm() != null ? repairRetMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((repairRetMetalDt.getMetalPcs() != null ? repairRetMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((repairRetMetalDt.getMetalWeight() != null ? repairRetMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((repairRetMetalDt.getMetalRate() != null ? repairRetMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((repairRetMetalDt.getPerGramRate() != null ? repairRetMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((repairRetMetalDt.getLossPerc() != null ? repairRetMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((repairRetMetalDt.getMetalValue() != null ? repairRetMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(repairRetMetalDt.getMainMetal())
	    		.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

}

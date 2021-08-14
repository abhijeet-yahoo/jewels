package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IRepairRetCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetDtService;

@Service
@Repository
@Transactional
public class RepairRetCompDtService implements IRepairRetCompDtService{
	
	@Autowired
	private IRepairRetCompDtRepository repairRetCompDtRepository;
	
	@Autowired
	private IRepairRetDtService repairRetDtService;

	@Override
	public List<RepairRetCompDt> findByRepairRetDt(RepairRetDt repairRetDt) {
		// TODO Auto-generated method stub
		return repairRetCompDtRepository.findByRepairRetDt(repairRetDt);
	}

	@Override
	public void save(RepairRetCompDt repairRetCompDt) {
		// TODO Auto-generated method stub
		repairRetCompDtRepository.save(repairRetCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		repairRetCompDtRepository.delete(id);
	}

	@Override
	public RepairRetCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return repairRetCompDtRepository.findOne(id);
	}

	@Override
	public String repairRetCompDtListing(Integer dtId, String disableFlg) {
		// TODO Auto-generated method stub
		
		RepairRetDt repairRetDt = repairRetDtService.findOne(dtId);
		List<RepairRetCompDt>repairRetCompDts = findByRepairRetDt(repairRetDt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(repairRetCompDts.size()).append(",\"rows\": [");
		  for(RepairRetCompDt repairRetCompDt :repairRetCompDts){
				sb.append("{\"id\":\"")
				.append(repairRetCompDt.getId())
				.append("\",\"compName\":\"")
				.append((repairRetCompDt.getComponent() != null ? repairRetCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((repairRetCompDt.getPurity() != null ? repairRetCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((repairRetCompDt.getColor() != null ? repairRetCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((repairRetCompDt.getCompWt() != null ? repairRetCompDt.getCompWt() : ""))
				.append("\",\"rate\":\"")
				.append((repairRetCompDt.getCompRate() != null ? repairRetCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((repairRetCompDt.getCompValue() != null ? repairRetCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((repairRetCompDt.getCompQty() != null ? repairRetCompDt.getCompQty() : ""))
				.append("\",\"perPcs\":\"")
				.append(repairRetCompDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(repairRetCompDt.getPerGramRate())
				.append("\",\"rLock\":\"")
				.append((repairRetCompDt.getrLock())); //1 = lock & 0 = unlock
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doPackDtLockUnLock(")
				.append(repairRetCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editRepairRetCompDt(")
				.append(repairRetCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteRepairRetCompDt(event,")
				.append(repairRetCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(repairRetCompDt.getId())
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

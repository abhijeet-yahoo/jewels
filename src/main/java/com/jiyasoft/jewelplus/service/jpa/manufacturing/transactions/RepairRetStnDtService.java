package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IRepairRetStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetStnDtService;

@Service
@Repository
@Transactional
public class RepairRetStnDtService implements IRepairRetStnDtService{
	
	@Autowired
	private IRepairRetStnDtRepository repairRetStnDtRepository;
	
	@Autowired
	private IRepairRetDtService repairRetDtService;

	@Override
	public void save(RepairRetStnDt repairRetStnDt) {
		// TODO Auto-generated method stub
		repairRetStnDtRepository.save(repairRetStnDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		repairRetStnDtRepository.delete(id);
	}

	@Override
	public RepairRetStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return repairRetStnDtRepository.findOne(id);
	}

	@Override
	public List<RepairRetStnDt> findByRepairRetDt(RepairRetDt repairRetDt) {
		// TODO Auto-generated method stub
		return repairRetStnDtRepository.findByRepairRetDt(repairRetDt);
	}

	

	@Override
	public String repairRetStnDtListing(Integer dtId) {
		// TODO Auto-generated method stub
		
		RepairRetDt repairRetDt = repairRetDtService.findOne(dtId);
		List<RepairRetStnDt>repairRetStnDts = findByRepairRetDt(repairRetDt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(repairRetStnDts.size()).append(",\"rows\": [");
		  for(RepairRetStnDt repairRetStnDt :repairRetStnDts){
			  sb.append("{\"id\":\"")
				.append(repairRetStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((repairRetStnDt.getStoneType() != null ? repairRetStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((repairRetStnDt.getPartNm() != null ? repairRetStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((repairRetStnDt.getShape() != null ? repairRetStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((repairRetStnDt.getQuality() != null ? repairRetStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((repairRetStnDt.getSize() != null ? repairRetStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((repairRetStnDt.getSieve() != null ? repairRetStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((repairRetStnDt.getSizeGroup() != null ? repairRetStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((repairRetStnDt.getStone() != null ? repairRetStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((repairRetStnDt.getCarat() != null ? repairRetStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((repairRetStnDt.getStoneRate() != null ? repairRetStnDt.getStoneRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((repairRetStnDt.getStoneValue() != null ? repairRetStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((repairRetStnDt.getHandlingRate() != null ? repairRetStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((repairRetStnDt.getHandlingValue() != null ? repairRetStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((repairRetStnDt.getSetting() != null ? repairRetStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((repairRetStnDt.getSettingType() != null ? repairRetStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((repairRetStnDt.getSetRate() != null ? repairRetStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((repairRetStnDt.getSetValue() != null ? repairRetStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(repairRetStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((repairRetStnDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(repairRetStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editRepairRetStnDt(")
				.append(repairRetStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteRepairRetStnDt(event,")
				.append(repairRetStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(repairRetStnDt.getId())
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

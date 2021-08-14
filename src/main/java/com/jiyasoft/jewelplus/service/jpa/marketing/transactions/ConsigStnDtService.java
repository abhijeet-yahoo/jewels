package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigStnDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigStnDtService;

@Service
@Repository
@Transactional
public class ConsigStnDtService implements IConsigStnDtService {
	
	@Autowired
	private IConsigStnDtRepository consigStnDtRepository;

	@Autowired
	private IConsigDtService consigDtService;
	
	@Override
	public void save(ConsigStnDt consigStnDt) {
		// TODO Auto-generated method stub
		consigStnDtRepository.save(consigStnDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	consigStnDtRepository.delete(id);
		
	}

	@Override
	public ConsigStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigStnDtRepository.findOne(id);
	}

	@Override
	public List<ConsigStnDt> findByConsigDt(ConsigDt consigDt) {
		// TODO Auto-generated method stub
		return consigStnDtRepository.findByConsigDt(consigDt);
	}

	@Override
	public String consigStnDtListing(Integer dtId) {
		// TODO Auto-generated method stub
		ConsigDt consigDt=consigDtService.findOne(dtId);
		List<ConsigStnDt>consigStnDts = findByConsigDt(consigDt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigStnDts.size()).append(",\"rows\": [");
		  for(ConsigStnDt consigStnDt :consigStnDts){
			  sb.append("{\"id\":\"")
				.append(consigStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((consigStnDt.getStoneType() != null ? consigStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((consigStnDt.getPartNm() != null ? consigStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((consigStnDt.getShape() != null ? consigStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((consigStnDt.getQuality() != null ? consigStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((consigStnDt.getSize() != null ? consigStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((consigStnDt.getSieve() != null ? consigStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((consigStnDt.getSizeGroup() != null ? consigStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((consigStnDt.getStone() != null ? consigStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((consigStnDt.getCarat() != null ? consigStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((consigStnDt.getStoneRate() != null ? consigStnDt.getStoneRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((consigStnDt.getStoneValue() != null ? consigStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((consigStnDt.getHandlingRate() != null ? consigStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((consigStnDt.getHandlingValue() != null ? consigStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((consigStnDt.getSetting() != null ? consigStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((consigStnDt.getSettingType() != null ? consigStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((consigStnDt.getSetRate() != null ? consigStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((consigStnDt.getSetValue() != null ? consigStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(consigStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((consigStnDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(consigStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editconsigStnDt(")
				.append(consigStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteconsigStnDt(event,")
				.append(consigStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(consigStnDt.getId())
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

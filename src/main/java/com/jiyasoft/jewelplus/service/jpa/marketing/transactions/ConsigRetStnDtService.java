package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IConsigRetStnDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetStnDtService;

@Service
@Repository
@Transactional
public class ConsigRetStnDtService implements IConsigRetStnDtService {

	@Autowired
	private IConsigRetStnDtRepository consigRetStnDtRepository;
	
	@Autowired
	private IConsigRetDtService consigRetDtService;

	
	@Override
	public void save(ConsigRetStnDt consigRetStnDt) {
		// TODO Auto-generated method stub
		consigRetStnDtRepository.save(consigRetStnDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		consigRetStnDtRepository.delete(id);
		
	}

	@Override
	public ConsigRetStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return consigRetStnDtRepository.findOne(id);
	}

	@Override
	public List<ConsigRetStnDt> findByConsigRetDt(ConsigRetDt consigRetDt) {
		// TODO Auto-generated method stub
		return consigRetStnDtRepository.findByConsigRetDt(consigRetDt);
	}

	@Override
	public String consigRetStnDtListing(Integer dtId, String disableFlg) {
		// TODO Auto-generated method stub
		
		ConsigRetDt consigRetDt=consigRetDtService.findOne(dtId);
		List<ConsigRetStnDt>consigRetStnDts = findByConsigRetDt(consigRetDt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(consigRetStnDts.size()).append(",\"rows\": [");
		  for(ConsigRetStnDt consigRetStnDt :consigRetStnDts){
			  sb.append("{\"id\":\"")
				.append(consigRetStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((consigRetStnDt.getStoneType() != null ? consigRetStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((consigRetStnDt.getPartNm() != null ? consigRetStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((consigRetStnDt.getShape() != null ? consigRetStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((consigRetStnDt.getQuality() != null ? consigRetStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((consigRetStnDt.getSize() != null ? consigRetStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((consigRetStnDt.getSieve() != null ? consigRetStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((consigRetStnDt.getSizeGroup() != null ? consigRetStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((consigRetStnDt.getStone() != null ? consigRetStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((consigRetStnDt.getCarat() != null ? consigRetStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((consigRetStnDt.getStoneRate() != null ? consigRetStnDt.getStoneRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((consigRetStnDt.getStoneValue() != null ? consigRetStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((consigRetStnDt.getHandlingRate() != null ? consigRetStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((consigRetStnDt.getHandlingValue() != null ? consigRetStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((consigRetStnDt.getSetting() != null ? consigRetStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((consigRetStnDt.getSettingType() != null ? consigRetStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((consigRetStnDt.getSetRate() != null ? consigRetStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((consigRetStnDt.getSetValue() != null ? consigRetStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(consigRetStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((consigRetStnDt.getrLock())); //1 = lock & 0 = unlock
				
			  if(disableFlg.equalsIgnoreCase("false")) {
			  sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(consigRetStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editconsigRetStnDt(")
				.append(consigRetStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteconsigRetStnDt(event,")
				.append(consigRetStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(consigRetStnDt.getId())
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

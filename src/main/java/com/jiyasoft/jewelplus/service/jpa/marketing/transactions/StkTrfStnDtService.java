package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStkTrfStnDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfStnDtService;

@Service
@Repository
@Transactional
public class StkTrfStnDtService implements IStkTrfStnDtService{
	
	@Autowired
	private IStkTrfStnDtRepository stkTrfStnDtRepository;
	
	@Autowired
	private IStkTrfDtService stkTrfDtService;

	@Override
	public void save(StkTrfStnDt stkTrfStnDt) {
		// TODO Auto-generated method stub
		stkTrfStnDtRepository.save(stkTrfStnDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		stkTrfStnDtRepository.delete(id);
	}

	@Override
	public StkTrfStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return stkTrfStnDtRepository.findOne(id);
	}

	@Override
	public List<StkTrfStnDt> findByStkTrfDt(StkTrfDt stkTrfDt) {
		// TODO Auto-generated method stub
		return stkTrfStnDtRepository.findByStkTrfDt(stkTrfDt);
	}

	@Override
	public String stkTrfStnDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
		
		StkTrfDt stkTrfDt = stkTrfDtService.findOne(dtId);
		List<StkTrfStnDt> stkTrfStnDts = findByStkTrfDt(stkTrfDt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stkTrfStnDts.size()).append(",\"rows\": [");
		  for(StkTrfStnDt stkTrfStnDt :stkTrfStnDts){
			  sb.append("{\"id\":\"")
				.append(stkTrfStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((stkTrfStnDt.getStoneType() != null ? stkTrfStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((stkTrfStnDt.getPartNm() != null ? stkTrfStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((stkTrfStnDt.getShape() != null ? stkTrfStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((stkTrfStnDt.getQuality() != null ? stkTrfStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((stkTrfStnDt.getSize() != null ? stkTrfStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((stkTrfStnDt.getSieve() != null ? stkTrfStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((stkTrfStnDt.getSizeGroup() != null ? stkTrfStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((stkTrfStnDt.getStone() != null ? stkTrfStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((stkTrfStnDt.getCarat() != null ? stkTrfStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((stkTrfStnDt.getStoneRate() != null ? stkTrfStnDt.getStoneRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((stkTrfStnDt.getStoneValue() != null ? stkTrfStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((stkTrfStnDt.getHandlingRate() != null ? stkTrfStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((stkTrfStnDt.getHandlingValue() != null ? stkTrfStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((stkTrfStnDt.getSetting() != null ? stkTrfStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((stkTrfStnDt.getSettingType() != null ? stkTrfStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((stkTrfStnDt.getSetRate() != null ? stkTrfStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((stkTrfStnDt.getSetValue() != null ? stkTrfStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(stkTrfStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((stkTrfStnDt.getrLock())); //1 = lock & 0 = unlock
				
			  if(disableFlg.equalsIgnoreCase("false")) {
			  sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(stkTrfStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editStkTrfStnDt(")
				.append(stkTrfStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteStkTrfStnDt(event,")
				.append(stkTrfStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(stkTrfStnDt.getId())
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

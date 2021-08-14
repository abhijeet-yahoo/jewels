package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleStnDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleStnDtService;

@Service
@Repository
@Transactional
public class SaleStnDtService implements ISaleStnDtService{
	
	@Autowired
	private ISaleStnDtRepository saleStnDtRepository;

	@Autowired
	private ISaleDtService saleDtService;
	
	@Override
	public void save(SaleStnDt saleStnDt) {
		// TODO Auto-generated method stub
		saleStnDtRepository.save(saleStnDt);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		saleStnDtRepository.delete(id);
		
	}

	@Override
	public SaleStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return saleStnDtRepository.findOne(id);
	}

	@Override
	public List<SaleStnDt> findBySaleMt(SaleMt saleMt) {
		// TODO Auto-generated method stub
		return saleStnDtRepository.findBySaleMt(saleMt);
	}

	@Override
	public List<SaleStnDt> findBySaleDt(SaleDt saleDt) {
		// TODO Auto-generated method stub
		return saleStnDtRepository.findBySaleDt(saleDt);
	}

	@Override
	public String saleStnDtListing(Integer dtId,String disableFlg) {
		// TODO Auto-generated method stub
		SaleDt saleDt = saleDtService.findOne(dtId);
		List<SaleStnDt> saleStnDts = findBySaleDt(saleDt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
			
			  sb.append("{\"total\":").append(saleStnDts.size()).append(",\"rows\": [");
			
			  for(SaleStnDt saleStnDt :saleStnDts)
			  { sb.append("{\"id\":\"")
			  .append(saleStnDt.getId()) 
			  .append("\",\"stoneType\":\"")
			  .append((saleStnDt.getStoneType() != null ?   saleStnDt.getStoneType().getName() : "")) 
			  .append("\",\"partNm\":\"")
			  .append((saleStnDt.getPartNm() != null ?  saleStnDt.getPartNm().getFieldValue() : "")) 
			  .append("\",\"shape\":\"")
			  .append((saleStnDt.getShape() != null ? saleStnDt.getShape().getName() : ""))
			  .append("\",\"quality\":\"") 
			  .append((saleStnDt.getQuality() != null ?  saleStnDt.getQuality().getName() : "")) 
			  .append("\",\"size\":\"")
			  .append((saleStnDt.getSize() != null ? saleStnDt.getSize() : ""))
			  .append("\",\"sieve\":\"") 
			  .append((saleStnDt.getSieve() != null ?  saleStnDt.getSieve() : "")) 
			  .append("\",\"sizeGroup\":\"")
			  .append((saleStnDt.getSizeGroup() != null ?  saleStnDt.getSizeGroup().getName() : "")) 
			  .append("\",\"stone\":\"")
			  .append((saleStnDt.getStone() != null ? saleStnDt.getStone() : ""))
			  .append("\",\"carat\":\"") 
			  .append((saleStnDt.getCarat() != null ?   saleStnDt.getCarat() : "")) 
			  .append("\",\"rate\":\"")
			  .append((saleStnDt.getStoneRate() != null ? saleStnDt.getStoneRate() : ""))
			  .append("\",\"stoneValue\":\"") 
			  .append((saleStnDt.getStoneValue() != null ?   saleStnDt.getStoneValue() : "")) 
			  .append("\",\"handlingRate\":\"")
			  .append((saleStnDt.getHandlingRate() != null ? saleStnDt.getHandlingRate() :  "")) 
			  .append("\",\"handlingValue\":\"") 
			  .append((saleStnDt.getHandlingValue()  != null ? saleStnDt.getHandlingValue() : "")) 
			  .append("\",\"setting\":\"")
			  .append((saleStnDt.getSetting() != null ? saleStnDt.getSetting().getName() :  "")) 
			  .append("\",\"settingType\":\"") 
			  .append((saleStnDt.getSettingType() !=  null ? saleStnDt.getSettingType().getName() : ""))
			  .append("\",\"settingRate\":\"") 
			  .append((saleStnDt.getSetRate() != null ?
			  saleStnDt.getSetRate() : "")) 
			  .append("\",\"settingValue\":\"")
			  .append((saleStnDt.getSetValue() != null ? saleStnDt.getSetValue() : ""))
			  .append("\",\"centerStone\":\"") 
			  .append(saleStnDt.getCenterStone())
			  .append("\",\"rLock\":\"") 
			  .append((saleStnDt.getrLock())); //1 = lock & 0 =
			  
			  if(disableFlg.equalsIgnoreCase("false")) {
				  
			  
			  sb.append("\",\"actionLock\":\"")
			  .append("<a href='javascript:doStoneDtLockUnLock(")
			  .append(saleStnDt.getId())
			  .append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>") 
			  .append("\",\"action1\":\"") 
			  .append("<a href='javascript:editSaleStnDt(")
			  .append(saleStnDt.getId())
			  .append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>") 
			  .append("\",\"action2\":\"")
			  .append("<a  href='javascript:deleteSaleStnDt(event,")
			  .append(saleStnDt.getId())
			  .append(");'  class='btn btn-xs btn-danger triggerRemove")
			  .append(saleStnDt.getId())
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

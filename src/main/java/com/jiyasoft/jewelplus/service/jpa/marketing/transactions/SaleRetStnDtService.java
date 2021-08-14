package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetStnDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetStnDtService;

@Service
@Repository
@Transactional
public class SaleRetStnDtService implements ISaleRetStnDtService{

	
	@Autowired
	private ISaleRetStnDtRepository saleRetStnDtRepository;
	
	@Autowired
	private ISaleRetDtService saleRetDtService;
	
	
	@Override
	public void save(SaleRetStnDt saleRetStnDt) {
		
		saleRetStnDtRepository.save(saleRetStnDt);
	}

	@Override
	public void delete(int id) {
		 saleRetStnDtRepository.delete(id);
		
	}

	@Override
	public SaleRetStnDt findOne(int id) {
		
		return saleRetStnDtRepository.findOne(id);
	}

	@Override
	public List<SaleRetStnDt> findBySaleRetDt(SaleRetDt saleRetDt) {
		
		return saleRetStnDtRepository.findBySaleRetDt(saleRetDt);
	}

	@Override
	public String saleRetStnDtListing(Integer dtId,String disableFlg) {
		
		SaleRetDt saleRetDt=saleRetDtService.findOne(dtId);
		List<SaleRetStnDt>saleRetStnDts = findBySaleRetDt(saleRetDt);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleRetStnDts.size()).append(",\"rows\": [");
		  for(SaleRetStnDt saleRetStnDt :saleRetStnDts){
			  sb.append("{\"id\":\"")
				.append(saleRetStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((saleRetStnDt.getStoneType() != null ? saleRetStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((saleRetStnDt.getPartNm() != null ? saleRetStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((saleRetStnDt.getShape() != null ? saleRetStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((saleRetStnDt.getQuality() != null ? saleRetStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((saleRetStnDt.getSize() != null ? saleRetStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((saleRetStnDt.getSieve() != null ? saleRetStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((saleRetStnDt.getSizeGroup() != null ? saleRetStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((saleRetStnDt.getStone() != null ? saleRetStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((saleRetStnDt.getCarat() != null ? saleRetStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((saleRetStnDt.getStoneRate() != null ? saleRetStnDt.getStoneRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((saleRetStnDt.getStoneValue() != null ? saleRetStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((saleRetStnDt.getHandlingRate() != null ? saleRetStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((saleRetStnDt.getHandlingValue() != null ? saleRetStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((saleRetStnDt.getSetting() != null ? saleRetStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((saleRetStnDt.getSettingType() != null ? saleRetStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((saleRetStnDt.getSetRate() != null ? saleRetStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((saleRetStnDt.getSetValue() != null ? saleRetStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(saleRetStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((saleRetStnDt.getrLock())); //1 = lock & 0 = unlock
				
			  if(disableFlg.equalsIgnoreCase("false")) {
			  sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(saleRetStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editsaleRetStnDt(")
				.append(saleRetStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deletesaleRetStnDt(event,")
				.append(saleRetStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(saleRetStnDt.getId())
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

	@Override
	public void setSaleRetStnDt(List<DesignStone> designStones, SaleRetMt saleRetMt, SaleRetDt saleRetDt,
			Principal principal) {
		
		if(designStones != null){
			
			Boolean recordFound = false;
			int srNo=0;
			for(DesignStone designStone:designStones){
				srNo+=1;
				recordFound = false;
				
				SaleRetStnDt saleRetStnDt = new SaleRetStnDt();
				saleRetStnDt.setSrNo(srNo);
				saleRetStnDt.setSaleRetDt(saleRetDt);
				saleRetStnDt.setSaleRetMt(saleRetMt);
				saleRetStnDt.setCarat(designStone.getCarat());
				saleRetStnDt.setShape(designStone.getShape());
				saleRetStnDt.setStoneType(designStone.getStoneType());
				saleRetStnDt.setSubShape(designStone.getSubShape());
				
				if(!recordFound){
					saleRetStnDt.setQuality(designStone.getQuality());
				}
				
				saleRetStnDt.setSize(designStone.getSize());
				saleRetStnDt.setSizeGroup(designStone.getSizeGroup());
				saleRetStnDt.setSieve(designStone.getSieve());
				saleRetStnDt.setStone(designStone.getStone());
				saleRetStnDt.setSetting(designStone.getSetting());
				saleRetStnDt.setSettingType(designStone.getSettingType());
				saleRetStnDt.setCreatedBy(principal.getName());
				saleRetStnDt.setCreatedDate(new java.util.Date());
				saleRetStnDt.setPartNm(designStone.getPartNm());
				
				
				save(saleRetStnDt);
				
				
				
			}
			
		}
		
		
	}

}

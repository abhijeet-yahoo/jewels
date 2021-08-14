package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IPackStnDtRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackStnDtService;

@Service
@Repository
@Transactional
public class PackStnDtService implements IPackStnDtService {
	
	@Autowired
	private IPackStnDtRepository packStnDtRepository;
	
	@Autowired
	private IPackDtService packDtService;
	
	@Autowired
	private IPackMtService packMtService;

	@Override
	public void save(PackStnDt packStnDt) {
		// TODO Auto-generated method stub
		packStnDtRepository.save(packStnDt);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		packStnDtRepository.delete(id);
		
	}

	@Override
	public PackStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return packStnDtRepository.findOne(id);
	}

	@Override
	public List<PackStnDt> findByPackDt(PackDt packDt) {
		// TODO Auto-generated method stub
		return packStnDtRepository.findByPackDt(packDt);
	}

	@Override
	public String updateStnRate(Principal principal, Integer packStnId, Double stnRate) {
		// TODO Auto-generated method stub
		
		PackStnDt packStnDt =findOne(packStnId);
		packStnDt.setStoneRate(stnRate);
		packStnDt.setModiBy(principal.getName());
		packStnDt.setModiDate(new Date());
		save(packStnDt);
		
		packDtService.updateFob(packStnDt.getPackDt());
		
		return "1";
	}

	@Override
	public String updateHdlgRate(Principal principal, Integer packStnId, Double hdlgRate) {
		PackStnDt packStnDt =findOne(packStnId);
		packStnDt.setHandlingRate(hdlgRate);
		packStnDt.setModiBy(principal.getName());
		packStnDt.setModiDate(new Date());
		save(packStnDt);
		
		packDtService.updateFob(packStnDt.getPackDt());
		
		return "1";
	}

	@Override
	public String updateSettRate(Principal principal, Integer packStnId, Double settRate) {
		PackStnDt packStnDt =findOne(packStnId);
		packStnDt.setSetRate(settRate);
		packStnDt.setModiBy(principal.getName());
		packStnDt.setModiDate(new Date());
		save(packStnDt);
		
		packDtService.updateFob(packStnDt.getPackDt());
		
		return "1";
	}

	@Override
	public Map<Integer, String> getQualityList(Integer packMtId,Integer shapeId) {
			
		PackMt packMt =packMtService.findOne(packMtId);
		
		Map<Integer, String> qualityMap = new LinkedHashMap<Integer, String>();
		List<PackStnDt> qualityList = findByPackMt(packMt);

		for (PackStnDt packStnDt : qualityList) {
			if(packStnDt.getQuality() !=null && packStnDt.getShape().getId().equals(shapeId)) {
				qualityMap.put(packStnDt.getQuality().getId(), packStnDt.getQuality().getName());	
			}
			
		}

		return qualityMap;
	}

	@Override
	public List<PackStnDt> findByPackMt(PackMt packMt) {
		// TODO Auto-generated method stub
		return packStnDtRepository.findByPackMt(packMt);
	}

	@Override
	public Map<Integer, String> getShapeList(Integer packMtId) {
		PackMt packMt =packMtService.findOne(packMtId);
		
		Map<Integer, String> shapeMap = new LinkedHashMap<Integer, String>();
		List<PackStnDt> shapeList = findByPackMt(packMt);

		for (PackStnDt packStnDt : shapeList) {
			if(packStnDt.getShape() !=null) {
				shapeMap.put(packStnDt.getShape().getId(), packStnDt.getShape().getName());	
			}
			
		}

		return shapeMap;
	}

	@Override
	public Map<Integer, String> getSizeGroupList(Integer packMtId, Integer shapeId) {
		PackMt packMt =packMtService.findOne(packMtId);
		
		Map<Integer, String> groupMap = new LinkedHashMap<Integer, String>();
		List<PackStnDt> groupList = findByPackMt(packMt);

		for (PackStnDt packStnDt : groupList) {
			if(packStnDt.getSizeGroup() !=null && packStnDt.getShape().getId().equals(shapeId)) {
				groupMap.put(packStnDt.getSizeGroup().getId(), packStnDt.getSizeGroup().getName());	
			}
			
		}

		return groupMap;
	}

	@Override
	public Map<String, String> getSizeList(Integer packMtId, Integer shapeId, Integer sizeGroupId) {
		PackMt packMt =packMtService.findOne(packMtId);
		
		Map<String, String> sizeMap = new LinkedHashMap<String, String>();
		List<PackStnDt> groupList = findByPackMt(packMt);

		if(sizeGroupId !=null) {
		for (PackStnDt packStnDt : groupList) {
			if(packStnDt.getSize() !=null && packStnDt.getShape().getId().equals(shapeId) && packStnDt.getSizeGroup().getId().equals(sizeGroupId)) {
				sizeMap.put(packStnDt.getSize(), packStnDt.getSize());	
			}
			
		}
		}else {
			for (PackStnDt packStnDt : groupList) {
				if(packStnDt.getSize() !=null && packStnDt.getShape().getId().equals(shapeId)) {
					sizeMap.put(packStnDt.getSize(), packStnDt.getSize());	
				}
				
			}
		}

		return sizeMap;
	}

	@Override
	public String updateStoneRate(Principal principal, Integer packMtId, Integer shapeId, Integer qualityId,
			Integer sizeGroupId, String sizeNm, Double stnRate) {
		// TODO Auto-generated method stub
		PackMt packMt=packMtService.findOne(packMtId);
		
		List<PackStnDt>packStnDts =findByPackMt(packMt);
		
		if(sizeGroupId !=null && !sizeNm.isEmpty() && sizeNm !=null) {
			for(PackStnDt packStnDt:packStnDts) {
				if(packStnDt.getShape().getId().equals(shapeId) &&packStnDt.getQuality().getId().equals(qualityId)&&packStnDt.getSize().equalsIgnoreCase(sizeNm)) {
					packStnDt.setStoneRate(stnRate);
					packStnDt.setModiBy(principal.getName());
					packStnDt.setModiDate(new Date());
					save(packStnDt);
				}
				
			}
			
		}else if(sizeGroupId !=null && sizeNm.isEmpty() || sizeNm ==null){
			for(PackStnDt packStnDt:packStnDts) {
				if(packStnDt.getShape().getId().equals(shapeId) &&packStnDt.getQuality().getId().equals(qualityId)&&packStnDt.getSizeGroup().getId().equals(sizeGroupId)) {
					packStnDt.setStoneRate(stnRate);
					packStnDt.setModiBy(principal.getName());
					packStnDt.setModiDate(new Date());
					save(packStnDt);
				}
				
			}
		}else if(sizeGroupId ==null && (!sizeNm.isEmpty() || sizeNm !=null)) {
			
			for(PackStnDt packStnDt:packStnDts) {
				if(packStnDt.getShape().getId().equals(shapeId) &&packStnDt.getQuality().getId().equals(qualityId)&&packStnDt.getSize().equalsIgnoreCase(sizeNm)) {
					packStnDt.setStoneRate(stnRate);
					packStnDt.setModiBy(principal.getName());
					packStnDt.setModiDate(new Date());
					save(packStnDt);
				}
				
			}
			
			
		}
		
	
		List<PackDt>packDts=packDtService.findByPackMt(packMt);
		for(PackDt packDt :packDts) {
			packDtService.updateFob(packDt);
		}
		
		return "1";
	}

	@Override
	public String packStnDtListing(Integer dtId) {
		// TODO Auto-generated method stub
	
		PackDt packDt=packDtService.findOne(dtId);
		List<PackStnDt>packStnDts = findByPackDt(packDt);
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(packStnDts.size()).append(",\"rows\": [");
		  for(PackStnDt packStnDt :packStnDts){
			  sb.append("{\"id\":\"")
				.append(packStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((packStnDt.getStoneType() != null ? packStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((packStnDt.getPartNm() != null ? packStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((packStnDt.getShape() != null ? packStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((packStnDt.getQuality() != null ? packStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((packStnDt.getSize() != null ? packStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((packStnDt.getSieve() != null ? packStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((packStnDt.getSizeGroup() != null ? packStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((packStnDt.getStone() != null ? packStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((packStnDt.getCarat() != null ? packStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((packStnDt.getStoneRate() != null ? packStnDt.getStoneRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((packStnDt.getStoneValue() != null ? packStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((packStnDt.getHandlingRate() != null ? packStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((packStnDt.getHandlingValue() != null ? packStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((packStnDt.getSetting() != null ? packStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((packStnDt.getSettingType() != null ? packStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((packStnDt.getSetRate() != null ? packStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((packStnDt.getSetValue() != null ? packStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(packStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((packStnDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(packStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editPackStnDt(")
				.append(packStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deletePackStnDt(event,")
				.append(packStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(packStnDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public String shapeDropdown(Integer packMtId) {
		// TODO Auto-generated method stub
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		Map<Integer, String> shapeMap = getShapeList(packMtId);
		
		if(shapeMap.size()>0){
			sb.append("<select id=\"shapeId\" name=\"shapeId\" class=\"form-control\" onchange=\"javascript:popQualityDropDown()\">");
			sb.append("<option value=\"\">Select Shape</option>");
			for (Object key : shapeMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(shapeMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		else{
			sb.append("<select id=\"shapeId\" name=\"shapeId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Shape</option>");
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		
		return retVal;
	}

	@Override
	public String sizeGroupDropdown(Integer packMtId, Integer shapeId) {
		// TODO Auto-generated method stub
		
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		Map<Integer, String> sizeGroupMap = getSizeGroupList(packMtId,shapeId);
		
		if(sizeGroupMap.size()>0){
			sb.append("<select id=\"sizeGroupId\" name=\"sizeGroupId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Group</option>");
			for (Object key : sizeGroupMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(sizeGroupMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		else{
			sb.append("<select id=\"sizeGroupId\" name=\"sizeGroupId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Group</option>");
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		
		return retVal;
	}

	@Override
	public String sizeDropdown(Integer packMtId, Integer shapeId, Integer sizeGroupId) {
		// TODO Auto-generated method stub
		String retVal="";
		
		StringBuilder sb =  new StringBuilder();
		Map<String, String> sizeMap = getSizeList(packMtId, shapeId, sizeGroupId);
		
		if(sizeMap.size()>0){
			sb.append("<select id=\"sizeId\" name=\"sizeId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Size</option>");
			for (Object key : sizeMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(sizeMap.get(key)).append("</option>");
			}
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		else{
			sb.append("<select id=\"sizeId\" name=\"sizeId\" class=\"form-control\">");
			sb.append("<option value=\"\">Select Size</option>");
			sb.append("</select>");	
			
			retVal=sb.toString();
		}
		
		return retVal;	
	}

}

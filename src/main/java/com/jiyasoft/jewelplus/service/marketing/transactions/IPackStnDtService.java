package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;

public interface IPackStnDtService {
	
	public void save(PackStnDt packStnDt);

	public void delete(int id);
	
	public PackStnDt findOne(int id);
	
	public List<PackStnDt>findByPackDt(PackDt packDt);
	
	public List<PackStnDt>findByPackMt(PackMt packMt);
	
	public String updateStnRate(Principal principal,Integer packStnId,Double stnRate);
	
	public String updateHdlgRate(Principal principal,Integer packStnId,Double hdlgRate);
	
	public String updateSettRate(Principal principal,Integer packStnId,Double settRate);
	
	
	public Map<Integer, String> getQualityList(Integer packMtId,Integer shapeId);
	
	public Map<Integer, String> getShapeList(Integer packMtId);
	
	
	public Map<Integer, String> getSizeGroupList(Integer packMtId,Integer shapeId);
	
	public Map<String, String> getSizeList(Integer packMtId,Integer shapeId,Integer sizeGroupId);
	
	
	public String updateStoneRate(Principal principal,Integer packMtId,Integer shapeId,Integer qualityId,Integer sizeGroupId,String sizeNm, Double stnRate);
	
	public String packStnDtListing(Integer dtId);
	
	public String shapeDropdown( Integer packMtId);
	
	public String sizeGroupDropdown( Integer packMtId,Integer shapeId);
	
	public String sizeDropdown( Integer packMtId,Integer shapeId,Integer sizeGroupId);


}

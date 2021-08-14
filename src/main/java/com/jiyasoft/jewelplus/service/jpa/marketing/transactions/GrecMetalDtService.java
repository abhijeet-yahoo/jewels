package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IGrecMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecMetalDtService;

@Service
@Repository
@Transactional
public class GrecMetalDtService implements IGrecMetalDtService {

	@Autowired
	private IGrecMetalDtRepository grecMetalDtRepository;
	
	@Autowired
	private IGrecDtService grecDtService;
	
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public void save(GrecMetalDt grecMetalDt) {
		grecMetalDtRepository.save(grecMetalDt);		
	}

	@Override
	public void delete(int id) {
		grecMetalDtRepository.delete(id);
	}

	@Override
	public GrecMetalDt findOne(int id) {
		
		return grecMetalDtRepository.findOne(id);
	}

	@Override
	public List<GrecMetalDt> findByGrecDt(GrecDt grecDt) {
		
		return grecMetalDtRepository.findByGrecDt(grecDt);
	}

	@Override
	public GrecMetalDt findByGrecDtAndMainMetal(GrecDt grecDt, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return grecMetalDtRepository.findByGrecDtAndMainMetal(grecDt, mainMetal);
	}

	@Override
	public GrecMetalDt findByGrecDtAndPartNm(GrecDt grecDt, LookUpMast lookUpMast) {
		
		return grecMetalDtRepository.findByGrecDtAndPartNm(grecDt, lookUpMast);
	}

	@Override
	public void addGrecMetalDtFromDesign(List<DesignMetal> designMetals, GrecMt grecMt, GrecDt grecDt,
			Principal principal) {
		

		for(DesignMetal designMetal :designMetals){
			
			GrecMetalDt grecMetalDt = new GrecMetalDt();
			
			grecMetalDt.setColor(designMetal.getColor());
			grecMetalDt.setCreateDate(new Date());
			grecMetalDt.setCreatedBy(principal.getName());
			grecMetalDt.setMainMetal(designMetal.getMainMetal());
			grecMetalDt.setMetalPcs(designMetal.getMetalPcs());
			grecMetalDt.setMetalWeight(designMetal.getMetalWeight());
			grecMetalDt.setPartNm(designMetal.getPartNm());
			grecMetalDt.setPurity(designMetal.getPurity());
			grecMetalDt.setGrecDt(grecDt);
			grecMetalDt.setGrecMt(grecMt);
			//grecMetalDt.setWaxWt(designMetal.getWaxWt());
			
			
			save(grecMetalDt);
			
			
		}
			
	}

	@Override
	public void addGrecMetalDt(String metalDtData, GrecMt grecMt, GrecDt grecDt, Principal principal) {

		
		if(metalDtData.length()>0){
			
			JSONArray jsonArrayMetal =new JSONArray(metalDtData);
			
			for(int i=0 ;i<jsonArrayMetal.length();i++){
				
				JSONObject metalObj =jsonArrayMetal.getJSONObject(i);
				if(metalObj.length()>0){
					if(metalObj.getInt("id") == 0){
						
						GrecMetalDt grecMetalDt = new GrecMetalDt();
						
						
						Color color=colorService.findByName(metalObj.get("color").toString());
						if(color !=null){
							grecMetalDt.setColor(color);
						}
										
						grecMetalDt.setCreateDate(new Date());
						grecMetalDt.setCreatedBy(principal.getName());
						grecMetalDt.setMainMetal(metalObj.getBoolean("mainMetal"));
						grecMetalDt.setMetalPcs(metalObj.getInt("qty"));
						grecMetalDt.setMetalWeight(metalObj.getDouble("metalWt"));
						LookUpMast part = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", metalObj.getString("partNm").toString(),false);
						if(part !=null){
							grecMetalDt.setPartNm(part);	
						}
						
						Purity purity=purityService.findByName(metalObj.get("purity").toString());
						if(purity !=null){
							grecMetalDt.setPurity(purity);
						}
		
						grecMetalDt.setGrecDt(grecDt);
						grecMetalDt.setGrecMt(grecMt);
						
						save(grecMetalDt);
						
					}else{
						
						GrecMetalDt grecMetalDt =findOne(metalObj.getInt("id"));
						if(grecMetalDt !=null){
							
							
							Color color=colorService.findByName(metalObj.get("color").toString());
							if(color !=null){
								grecMetalDt.setColor(color);
							}
							
							Purity purity=purityService.findByName(metalObj.get("purity").toString());
							if(purity !=null){
								grecMetalDt.setPurity(purity);
							}
							
							
				
							
							
							grecMetalDt.setMainMetal(metalObj.getBoolean("mainMetal"));
							grecMetalDt.setMetalPcs(metalObj.getInt("qty"));
							grecMetalDt.setMetalWeight(metalObj.getDouble("metalWt"));
							
							LookUpMast part = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", metalObj.getString("partNm").toString(),false);
							if(part !=null){
								grecMetalDt.setPartNm(part);	
							}
							
							grecMetalDt.setGrecDt(grecDt);
							grecMetalDt.setGrecMt(grecMt);
							grecMetalDt.setModiBy(principal.getName());
							grecMetalDt.setModiDt(new Date());

							save(grecMetalDt);
							
						}
						
					}
						
				}
				
			}
			
			
			
		}

	
	
	}
	@Override
	public List<GrecMetalDt> findByGrecMt(GrecMt grecMt) {
		return grecMetalDtRepository.findByGrecMt(grecMt);
	}

	@Override
	public Boolean grecPartValidation(Integer grecDtId, Integer partId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGrecMetalDt(List<DesignMetal> designMetals, GrecMt grecMt, GrecDt grecDt, Principal principal) {
		
		if(designMetals != null){

			for(DesignMetal designMetal : designMetals){
	
				GrecMetalDt grecMetalDt = new GrecMetalDt();
				grecMetalDt.setGrecMt(grecMt);
				grecMetalDt.setGrecDt(grecDt);
				grecMetalDt.setMainMetal(designMetal.getMainMetal());
				grecMetalDt.setMetalPcs(designMetal.getMetalPcs());
				grecMetalDt.setPartNm(designMetal.getPartNm());
				grecMetalDt.setPurity(grecDt.getPurity());
				grecMetalDt.setColor(grecDt.getColor());
				grecMetalDt.setLossPerc(designMetal.getLossPerc());
				grecMetalDt.setCreatedBy(principal.getName());
				grecMetalDt.setCreateDate(new java.util.Date());
				grecMetalDt.setMetalWeight(grecDt.getNetWt());
				grecMetalDt.setProcessLoss(designMetal.getLossPerc());
				
				
				/*
				 * if(designMetal.getWaxWt()>0) {
				 * 
				 * Double vMetalWt =
				 * Math.round(designMetal.getWaxWt()*grecMetalDt.getPurity().getWaxWtConv())*1000
				 * .0/1000.0;
				 * 
				 * vMetalWt = vMetalWt-(vMetalWt*designMetal.getLossPerc()/100);
				 * 
				 * grecMetalDt.setMetalWeight(Math.round(vMetalWt)*1000.0/1000.0);
				 * 
				 * }
				 */
				
				grecMetalDtRepository.save(grecMetalDt);
				
				
				
			}
			
		}
		
	}

	@Override
	public String grecMetalDtListing(Integer grecDtId) {
		
		StringBuilder sb = new StringBuilder();
		
		GrecDt grecDt = grecDtService.findOne(grecDtId);
		List<GrecMetalDt> grecMetalDts = findByGrecDt(grecDt);
		
		sb.append("{\"total\":").append(grecMetalDts.size()).append(",\"rows\": [");
		
		for(GrecMetalDt grecMetalDt:grecMetalDts){
		
			sb.append("{\"id\":\"")
				.append(grecMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((grecMetalDt.getPurity() != null ? grecMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((grecMetalDt.getColor() != null ? grecMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((grecMetalDt.getPartNm() != null ? grecMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((grecMetalDt.getMetalPcs() != null ? grecMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((grecMetalDt.getMetalWeight() != null ? grecMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((grecMetalDt.getMetalRate() != null ? grecMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((grecMetalDt.getPerGramRate() != null ? grecMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((grecMetalDt.getLossPerc() != null ? grecMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((grecMetalDt.getMetalValue() != null ? grecMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append((grecMetalDt.getMainMetal() != null ? (grecMetalDt.getMainMetal() ? grecMetalDt.getMainMetal() : false) : false));
				

				 sb.append("\"},");
			
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
}

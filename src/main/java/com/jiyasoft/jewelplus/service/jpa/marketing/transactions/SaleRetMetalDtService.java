package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.ISaleRetMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMetalDtService;
@Service
@Repository
@Transactional
public class SaleRetMetalDtService implements ISaleRetMetalDtService {

	@Autowired
	private ISaleRetMetalDtRepository saleRetMetalRepository;
	
	@Autowired
	private ISaleRetDtService saleRetDtService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IPurityService purityService;
	
	@Override
	public void save(SaleRetMetalDt saleRetMetalDt) {
		
		saleRetMetalRepository.save(saleRetMetalDt);
	}

	@Override
	public void delete(int id) {
		
		saleRetMetalRepository.delete(id);
		
	}

	@Override
	public SaleRetMetalDt findOne(int id) {
		
		return saleRetMetalRepository.findOne(id);
	}

	@Override
	public List<SaleRetMetalDt> findBySaleRetDt(SaleRetDt saleRetDt) {
		
		return saleRetMetalRepository.findBySaleRetDt(saleRetDt);
	}

	@Override
	public SaleRetMetalDt findBySaleRetDtAndPartNm(SaleRetDt saleRetDt, 
			LookUpMast lookUpMast) {
	
		return saleRetMetalRepository.findBySaleRetDtAndPartNm(saleRetDt, lookUpMast);
	}

	@Override
	public String saleRetMetalDtListing(Integer dtId) {
		
		SaleRetDt saleRetDt = saleRetDtService.findOne(dtId);
		List<SaleRetMetalDt> saleRetMetalDts = findBySaleRetDt(saleRetDt);
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(saleRetMetalDts.size()).append(",\"rows\": [");
		  for(SaleRetMetalDt saleRetMetalDt :saleRetMetalDts){
			 sb.append("{\"id\":\"")
				.append(saleRetMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((saleRetMetalDt.getPurity() != null ? saleRetMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((saleRetMetalDt.getColor() != null ? saleRetMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((saleRetMetalDt.getPartNm() != null ? saleRetMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((saleRetMetalDt.getMetalPcs() != null ? saleRetMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((saleRetMetalDt.getMetalWeight() != null ? saleRetMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((saleRetMetalDt.getMetalRate() != null ? saleRetMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((saleRetMetalDt.getPerGramRate() != null ? saleRetMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((saleRetMetalDt.getLossPerc() != null ? saleRetMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((saleRetMetalDt.getMetalValue() != null ? saleRetMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(saleRetMetalDt.getMainMetal())
	    		.append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public void addSaleRetMetal(String metalDtData, SaleRetMt saleRetMt, SaleRetDt saleRetDt, Principal principal) {
		// TODO Auto-generated method stub

		
		
		if(metalDtData.length()>0){
			
			JSONArray jsonArrayMetal =new JSONArray(metalDtData);
			
			for(int i=0 ;i<jsonArrayMetal.length();i++){
				
				JSONObject metalObj =jsonArrayMetal.getJSONObject(i);
				if(metalObj.length()>0){
					if(metalObj.getInt("id") == 0){
						
						SaleRetMetalDt saleRetMetalDt = new SaleRetMetalDt();
						
						
						Color color=colorService.findByName(metalObj.get("color").toString());
						if(color !=null){
							saleRetMetalDt.setColor(color);
						}
										
						saleRetMetalDt.setCreateDate(new Date());
						saleRetMetalDt.setCreatedBy(principal.getName());
						saleRetMetalDt.setMainMetal(metalObj.getBoolean("mainMetal"));
						saleRetMetalDt.setMetalPcs(metalObj.getInt("qty"));
						saleRetMetalDt.setMetalWeight(metalObj.getDouble("metalWt"));
			///			saleRetMetalDt.setCastWeight(Math.round((saleRetMetalDt.getMetalWeight()+(saleRetMetalDt.getMetalWeight()*saleRetMetalDt.getProcessLoss()/100))*1000.0)/1000.0);
						LookUpMast part = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", metalObj.getString("partNm").toString(),false);
						if(part !=null){
							saleRetMetalDt.setPartNm(part);	
						}
						
						Purity purity=purityService.findByName(metalObj.get("purity").toString());
						if(purity !=null){
							saleRetMetalDt.setPurity(purity);
						}
		
						saleRetMetalDt.setSaleRetDt(saleRetDt);
						saleRetMetalDt.setSaleRetMt(saleRetMt);
						
						
						save(saleRetMetalDt);
					}else{
						
						SaleRetMetalDt saleRetMetalDt =findOne(metalObj.getInt("id"));
						if(saleRetMetalDt !=null){
							
							
							Color color=colorService.findByName(metalObj.get("color").toString());
							if(color !=null){
								saleRetMetalDt.setColor(color);
							}
							
							Purity purity=purityService.findByName(metalObj.get("purity").toString());
							if(purity !=null){
								saleRetMetalDt.setPurity(purity);
							}
							
							
				
							
							
							saleRetMetalDt.setMainMetal(metalObj.getBoolean("mainMetal"));
							saleRetMetalDt.setMetalPcs(metalObj.getInt("qty"));
							saleRetMetalDt.setMetalWeight(metalObj.getDouble("metalWt"));
				//			saleRetMetalDt.setCastWeight(Math.round((saleRetMetalDt.getMetalWeight()+(saleRetMetalDt.getMetalWeight()*saleRetMetalDt.getProcessLoss()/100))*1000.0)/1000.0);
							
							LookUpMast part = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", metalObj.getString("partNm").toString(),false);
							if(part !=null){
								saleRetMetalDt.setPartNm(part);	
							}
							
							saleRetMetalDt.setSaleRetDt(saleRetDt);
							saleRetMetalDt.setSaleRetMt(saleRetMt);
							//orderMetal.setWaxWt(metalObj.getDouble("waxWt"));
							saleRetMetalDt.setModiBy(principal.getName());
							saleRetMetalDt.setModiDate(new Date());

							save(saleRetMetalDt);
							
						}
						
					}
						
				}
				
			}
			
			
			
		}

	
	
	}

	@Override
	public SaleRetMetalDt findBySaleRetDtAndMainMetal(SaleRetDt saleRetDt,
			Boolean mainMetal) {
		// TODO Auto-generated method stub
		return saleRetMetalRepository.findBySaleRetDtAndMainMetal(saleRetDt, mainMetal);
	}

}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IQuotMetalRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalService;


@Service
@Repository
@Transactional
public class QuotMetalService implements IQuotMetalService {
	
	@Autowired
	private IQuotMetalRepository quotMetalRepository;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public void save(QuotMetal quotMetal) {
		// TODO Auto-generated method stub
		quotMetalRepository.save(quotMetal);
	}

	@Override
	public void delete(int id) {
		QuotMetal quotMetal =quotMetalRepository.findOne(id);
		quotMetal.setDeactive(true);
		quotMetal.setDeactiveDt(new java.util.Date());
		quotMetalRepository.save(quotMetal);
		
	}

	

	@Override
	public QuotMetal findOne(int id) {
		return quotMetalRepository.findOne(id);
	}

	@Override
	public List<QuotMetal> findByQuotDtAndDeactive(QuotDt quotDt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return quotMetalRepository.findByQuotDtAndDeactive(quotDt, deactive);
	}

	@Override
	public QuotMetal findByQuotDtAndDeactiveAndMainMetal(QuotDt quotDt,
			Boolean deactive, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return quotMetalRepository.findByQuotDtAndDeactiveAndMainMetal(quotDt, deactive, mainMetal);
	}

	@Override
	public QuotMetal findByQuotDtAndDeactiveAndPartNm(QuotDt quotDt,
			Boolean deactive, LookUpMast lookUpMast) {
		// TODO Auto-generated method stub
		return quotMetalRepository.findByQuotDtAndDeactiveAndPartNm(quotDt, deactive, lookUpMast);
	}

	@Override
	public void addQuotMetalFromDesign(List<DesignMetal> designMetals,
			QuotMt quotMt, QuotDt quotDt, Principal principal) {

		for(DesignMetal designMetal :designMetals){
			
			QuotMetal quotMetal = new QuotMetal();
			
			quotMetal.setColor(quotDt.getColor());
			quotMetal.setCreateDate(new Date());
			quotMetal.setCreatedBy(principal.getName());
			quotMetal.setMainMetal(designMetal.getMainMetal());
			quotMetal.setMetalPcs(designMetal.getMetalPcs());
		//	quotMetal.setMetalWeight(Math.round((designMetal.getWaxWt() * designMetal.getPurity().getPurityConv())*1000.0)/1000.0);
			quotMetal.setPartNm(designMetal.getPartNm());
			quotMetal.setPurity(quotDt.getPurity());
			quotMetal.setQuotDt(quotDt);
			quotMetal.setQuotMt(quotMt);
			//quotMetal.setWaxWt(designMetal.getWaxWt());
			
				if(designMetal.getWaxWt()>0) {
				  
				  Double vMetalWt =  Math.round((designMetal.getWaxWt() * quotDt.getPurity().getWaxWtConv())*1000.0)/1000.0;
				  
				  vMetalWt = vMetalWt-((vMetalWt*designMetal.getLossPerc())/100);
				  
				  quotMetal.setMetalWeight(Math.round((vMetalWt)*1000.0)/1000.0);
				  
				//  orderMetal.setMetalWeight(vMetalWt);
				  
				  }
				 
			
			
			save(quotMetal);
			
			
		}
			
			
		}
		
		@Override
		public void addQuotMetal(String metalDtData,QuotMt quotMt,QuotDt quotDt,Principal  principal) {
			
			
			
			if(metalDtData.length()>0){
				
				JSONArray jsonArrayMetal =new JSONArray(metalDtData);
				
				for(int i=0 ;i<jsonArrayMetal.length();i++){
					
					JSONObject metalObj =jsonArrayMetal.getJSONObject(i);
					if(metalObj.length()>0){
						if(metalObj.getInt("id") == 0){
							
							QuotMetal quotMetal = new QuotMetal();
							
							
							Color color=colorService.findByName(metalObj.get("color").toString());
							if(color !=null){
								quotMetal.setColor(color);
							}
											
							quotMetal.setCreateDate(new Date());
							quotMetal.setCreatedBy(principal.getName());
							quotMetal.setMainMetal(metalObj.getBoolean("mainMetal"));
							quotMetal.setMetalPcs(metalObj.getInt("qty"));
							quotMetal.setMetalWeight(metalObj.getDouble("metalWt"));
							quotMetal.setProcessLoss(Double.parseDouble(metalObj.get("processLoss").toString()));
							quotMetal.setCastWeight(Math.round((quotMetal.getMetalWeight()+(quotMetal.getMetalWeight()*quotMetal.getProcessLoss()/100))*1000.0)/1000.0);
							
							LookUpMast part = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", metalObj.getString("partNm").toString(),false);
							if(part !=null){
								quotMetal.setPartNm(part);	
							}
							
							Purity purity=purityService.findByName(metalObj.get("purity").toString());
							if(purity !=null){
								quotMetal.setPurity(purity);
							}
			
							quotMetal.setQuotDt(quotDt);
							quotMetal.setQuotMt(quotMt);
							//quotMetal.setWaxWt(metalObj.getDouble("waxWt"));
							
							
							save(quotMetal);
						}else{
							
							QuotMetal quotMetal =findOne(metalObj.getInt("id"));
							if(quotMetal !=null){
								
								
								Color color=colorService.findByName(metalObj.get("color").toString());
								if(color !=null){
									quotMetal.setColor(color);
								}
								
								
								quotMetal.setMainMetal(metalObj.getBoolean("mainMetal"));
								quotMetal.setMetalPcs(metalObj.getInt("qty"));
								quotMetal.setMetalWeight(metalObj.getDouble("metalWt"));
								quotMetal.setCastWeight(Math.round((quotMetal.getMetalWeight()+(quotMetal.getMetalWeight()*quotMetal.getProcessLoss()/100))*1000.0)/1000.0);
								
								LookUpMast part = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", metalObj.getString("partNm").toString(),false);
								if(part !=null){
									quotMetal.setPartNm(part);	
								}
								
								Purity purity=purityService.findByName(metalObj.get("purity").toString());
								if(purity !=null){
									quotMetal.setPurity(purity);
								}
				
								quotMetal.setQuotDt(quotDt);
								quotMetal.setQuotMt(quotMt);
								//quotMetal.setWaxWt(metalObj.getDouble("waxWt"));
								quotMetal.setModiBy(principal.getName());
								quotMetal.setModiDt(new Date());

								save(quotMetal);
								
								
							}
							
							
							
							
							
						}
						
					
						
					}
					
				}
				
				
				
			}

		
		
		
		
	}
	
	
	@Override
	public List<QuotMetal> findByQuotMtAndDeactive(QuotMt quotMt, Boolean deactive) {
		return quotMetalRepository.findByQuotMtAndDeactive(quotMt, deactive);
	}
	
	
	@Override
	public String updateLossPerc(Principal principal, Integer id, Double lossPerc,Boolean netWtWithCompFlg) {
		// TODO Auto-generated method stub
		
		QuotMetal quotMetal = findOne(id);
		quotMetal.setLossPerc(lossPerc);
		save(quotMetal);
		quotDtService.updateFob(quotMetal.getQuotDt(), netWtWithCompFlg);
		
		return null;
	}


	

}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneAdjustment;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStoneAdjustmentRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneAdjustmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;

@Service
@Repository
@Transactional
public class stoneAdjustmentService implements IStoneAdjustmentService {
	@Autowired
	IStoneAdjustmentRepository stoneAdjustmentRepository;
	
	@Autowired
	IStoneInwardDtService stoneInwardDtService;
	
	@Autowired
	IDepartmentService departmentService;
	
	@Autowired
	IStoneTranService stoneTranService;

	@Override
	public String saveAdj(String id, String addCarat, String dedCarat, Principal principal) {

		
		String retVal="";
		if(id.length() == 0){
			return "-1";
		}else {
			
			String vStnDtId[] = id.split(",");
			String vAddCarat[] = addCarat.split(",");
			String vDedCarat[] = dedCarat.split(",");
			
			
			
			for (int j = 0; j < vStnDtId.length; j++) {

				StoneInwardDt stoneInwardPk = stoneInwardDtService
						.findOne(Integer.parseInt(vStnDtId[j]));

				Double stockCarat = stoneInwardPk.getBalCarat();
				Double currTrfCarat = Double.parseDouble(vDedCarat[j]);

				if (stockCarat < currTrfCarat) {
					retVal = "-2";
					return retVal;
				}

			

			} 
			
			
			
			
			
			
		
			DecimalFormat df = new DecimalFormat("#.###");
			Date date = new java.util.Date();
						
			for(int i=0; i < vStnDtId.length; i++){
				
				Double tempAddCarat = 0.00;
				Double tempDedCarat = 0.00;
				tempAddCarat = Double.parseDouble( vAddCarat[i]);
				tempDedCarat = Double.parseDouble(vDedCarat[i]);
				
				df.format(tempAddCarat);
				df.format(tempDedCarat);
		
				if(tempAddCarat>0 || tempDedCarat > 0){
					StoneInwardDt stoneInwardDt = stoneInwardDtService.findOne(Integer.parseInt(vStnDtId[i]));
					
					if (tempAddCarat >0){						
						stoneInwardDt.setBalCarat(Double.parseDouble(df.format(stoneInwardDt.getBalCarat()+tempAddCarat)));
    					stoneInwardDt.setModiBy(principal.getName());
						stoneInwardDt.setModiDt(date);
					}else{
						stoneInwardDt.setBalCarat(Double.parseDouble(df.format(stoneInwardDt.getBalCarat()-tempDedCarat)));
						stoneInwardDt.setModiBy(principal.getName());
						stoneInwardDt.setModiDt(date);
					}
					
					stoneInwardDtService.save(stoneInwardDt);
					
					
					StoneAdjustment stoneAdjustment = new StoneAdjustment();
					if (tempAddCarat >0){
			        
						stoneAdjustment.setBalCarat(tempAddCarat);
						stoneAdjustment.setCarat(tempAddCarat);
						stoneAdjustment.setEntryType("C");
						stoneAdjustment.setStnInwardDt(stoneInwardDt);
						stoneAdjustment.setCreatedBy(principal.getName());
						stoneAdjustment.setCreatedDt(new java.util.Date());
			        
					}else{
						
						stoneAdjustment.setBalCarat(-tempDedCarat);
						stoneAdjustment.setCarat(tempDedCarat);
						stoneAdjustment.setEntryType("D");
						stoneAdjustment.setStnInwardDt(stoneInwardDt);
						stoneAdjustment.setCreatedBy(principal.getName());
						stoneAdjustment.setCreatedDt(new java.util.Date());
					}
					stoneAdjustment.setUniqueId(new java.util.Date().getTime());

			        save(stoneAdjustment);
			        
			        
			        
			        //new entry in stoneTran
					StoneAdjustment stoneAdjustment2 =findByUniqueId(stoneAdjustment.getUniqueId());
					StoneTran stoneTran = new StoneTran();
					
					
					stoneTran.setTranDate(new java.util.Date()); 
					stoneTran.setDepartment(null);
					stoneTran.setDeptLocation(departmentService.findByName("Diamond"));
					stoneTran.setStoneType(stoneInwardDt.getStoneType());
					stoneTran.setShape(stoneInwardDt.getShape());
					stoneTran.setQuality(stoneInwardDt.getQuality());
					stoneTran.setSubShape(stoneInwardDt.getSubShape());
					stoneTran.setSize(stoneInwardDt.getSize());
					stoneTran.setSieve(stoneInwardDt.getSieve());
					stoneTran.setSizeGroup(stoneInwardDt.getSizeGroup());
					stoneTran.setRate(stoneInwardDt.getRate());
					stoneTran.setBagMt(null);;
					stoneTran.setBagSrNo(0);
					stoneTran.setSettingType(null);
					stoneTran.setSetting(null);
					stoneTran.setPacketNo(stoneInwardDt.getPacketNo());
					stoneTran.setRemark(stoneInwardDt.getRemark());
					stoneTran.setParty(stoneInwardDt.getStoneInwardMt().getParty());
					stoneTran.setTranType("StockAdj");
					stoneTran.setRefTranId(stoneAdjustment2.getId());
					stoneTran.setStnRecDtId(stoneInwardDt.getId());
					stoneTran.setCreatedBy(principal.getName());
					stoneTran.setCreatedDt(new java.util.Date());
					stoneTran.setDeactive(false);
					
					if (tempAddCarat >0){
						
						stoneTran.setCarat(tempAddCarat);		//-------------
						stoneTran.setBalCarat(tempAddCarat); //--------------
						stoneTran.setInOutFld("C");
						
												
						
					}else{
						
						stoneTran.setCarat(tempDedCarat);		//-------------
						stoneTran.setBalCarat(-tempDedCarat); //--------------
						stoneTran.setInOutFld("D");
						
						
						
					}
					stoneTranService.save(stoneTran);
					
					
					
					
					/*--------------------------------------------------------*/
			        
					
					
			
					
			} // ending for loop if
				
				
					
				  
				  
	
		} // ending for loop
			
			
			
			
			retVal="1";
			
	} // ending first if-else
		
		
		
		
		return retVal;
	}

	@Override
	public void save(StoneAdjustment stoneAdjustment) {
		stoneAdjustmentRepository.save(stoneAdjustment);
	}

	@Override
	public StoneAdjustment findByUniqueId(Long uniqueId) {
		return stoneAdjustmentRepository.findByUniqueId(uniqueId);
	}
	

}

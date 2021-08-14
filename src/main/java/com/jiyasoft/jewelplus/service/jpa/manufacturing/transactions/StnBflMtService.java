package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStnBflMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStnBflMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnBflDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnBflMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StnBflMtService implements IStnBflMtService{

	@Autowired
	IStnBflMtRepository stnBflMtRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	IStoneInwardDtService stoneInwardDtService;
	
	@Autowired
	IDepartmentService departmentService;
	
	@Autowired
	IStnBflDtService stnBflDtService;
	
	@Autowired
	IStoneTranService stoneTranService;
	
	@Override
	public List<StnBflMt> findAll() {
		return stnBflMtRepository.findAll();
	}

	@Override
	public Page<StnBflMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		return stnBflMtRepository
				.findAll(new PageRequest(page,limit,(order
						.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC ), sort));
	}

	@Override
	public void save(StnBflMt stnBflMt) {
    
		stnBflMtRepository.save(stnBflMt);		
	}

	@Override
	public void delete(int id) {
		StnBflMt stnBflMt = stnBflMtRepository.findOne(id);
		stnBflMt.setDeactive(true);
		stnBflMt.setDeactiveDt(new java.util.Date());
		stnBflMtRepository.save(stnBflMt);
		
	}

	@Override
	public Long count() {

		return stnBflMtRepository.count();
	}

	@Override
	public StnBflMt findOne(int id) {

		return stnBflMtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getStnBflMtList() {

		Map<Integer, String> stnBflMtMap = new HashMap<Integer, String>();
		List<StnBflMt> stnBflMtList = findActiveStnBflMts();
		
		for(StnBflMt stnBflMt : stnBflMtList){
			stnBflMtMap.put(stnBflMt.getId(), stnBflMt.getInvNo());
			
		}
		return stnBflMtMap;
	}

	@Override
	public List<StnBflMt> findActiveStnBflMts() {

		
		QStnBflMt qStnBflMt =QStnBflMt.stnBflMt;
		BooleanExpression expression = qStnBflMt.deactive.eq(false);
		List<StnBflMt> stnBflMtList =(List<StnBflMt>) stnBflMtRepository.findAll(expression);
		
		return stnBflMtList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QStnBflMt qStnBflMt =QStnBflMt.stnBflMt;
		BooleanExpression expression =qStnBflMt.deactive.eq(false);
		if(onlyActive){
			if(colName == null || colValue == null){
				expression=qStnBflMt.deactive.eq(false);
						
			}else if (colName.equalsIgnoreCase("invno") && colValue !=null){
				expression = qStnBflMt.deactive.eq(false)
                                   .and(qStnBflMt.invNo.like(colValue + "%"));				
			}
		}
		else {
			if((colName == null || colName.equalsIgnoreCase("invno")) && colValue != null){
				expression = qStnBflMt.invNo.like(colValue + "%");
				
			}
		}
		return  stnBflMtRepository.count(expression);
	}

	@Override
	public Page<StnBflMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QStnBflMt qStnBflMt = QStnBflMt.stnBflMt;
		BooleanExpression expression = null;
		 if(onlyActive){
			 if(name == null){
				 expression = qStnBflMt.deactive.eq(false);
			 } else {
				expression = qStnBflMt.deactive.eq(false).and(qStnBflMt.invNo.like(name + "%"));
			}
		 }
			 else {
				if(name !=null){
					expression =qStnBflMt.invNo.like(name + "%");
				}
			}
			int page = (offset == 0 ? 0 : (offset / limit));

			if (sort == null) {
				sort = "id";
			} else if (sort.equalsIgnoreCase("updatedBy")) {
				sort = "modiBy";
			} else if (sort.equalsIgnoreCase("updatedDt")) {
				sort = "modiDt";
			}

			Page<StnBflMt> stnBflMtList = (Page<StnBflMt>) stnBflMtRepository
					.findAll(
							expression,
							new PageRequest(page, limit, (order
									.equalsIgnoreCase("asc") ? Direction.DESC
									: Direction.ASC), sort));
		
		return stnBflMtList;
	}

	@Override
	public StnBflMt findByUniqueId(Long uniqueId) {

		return stnBflMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public StnBflMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		
		return stnBflMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	
	
	@Override
	public Integer maxSrNo() {
		
		QStnBflMt qStnBflMt = QStnBflMt.stnBflMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
				.from(qStnBflMt)
				.where(qStnBflMt.deactive.eq(false).and(qStnBflMt.createdDt.year().eq(date.get(Calendar.YEAR)))).list(qStnBflMt.srNo.max());

		for (Integer srNo : maxSrno) {
			retVal = (srNo == null ? 0 : srNo);
		}

		return retVal;
		
		
	}
	
	
	@Override
	public String saveData(String pOIds,String trfAdjStone,String trfAdjCarat,String pTypeFormat,String trfLossStone,String trfLossCarat,Integer pMtId,Principal principal){
		String retVal="";
		
		DecimalFormat df = new DecimalFormat("#.###");
		if(pOIds.length() == 0){
			
			return "-1";
			 
			
		}else {
			
			String vStnDtId[] = pOIds.split(",");
			String vTrfAdjStone[] = trfAdjStone.split(",");
			String vTrfAdjCarat[] = trfAdjCarat.split(",");
			String vTrfLossStone[] = trfLossStone.split(",");
			String vTrfLossCarat[] = trfLossCarat.split(",");
			
			
			
			
			
			
			for (int j = 0; j < vStnDtId.length; j++) {

				StoneInwardDt stoneInwardPk = stoneInwardDtService
						.findOne(Integer.parseInt(vStnDtId[j]));
				
				
				
				Double tempAdjCarat = Double.parseDouble( vTrfAdjCarat[j]);
				
				
				Double tempLossCarat = Double.parseDouble(vTrfLossCarat[j]);
				
				df.format(tempAdjCarat);
				df.format(tempLossCarat);
				
				
				if (tempAdjCarat >0){
					
					if(pTypeFormat.equalsIgnoreCase("brokenType")) {
						Double stockCarat = stoneInwardPk.getBrkCarat();
						Double currTrfCarat = Double.parseDouble(vTrfAdjCarat[j]);
						
						if (stockCarat < currTrfCarat) {
							retVal = "BrokenStockError";
							return retVal;
						}
						
						
					}else{
						
						Double stockCarat = stoneInwardPk.getFallCarat();
						Double currTrfCarat = Double.parseDouble(vTrfAdjCarat[j]);
						
						if (stockCarat < currTrfCarat) {
							retVal = "FallenStockError";
							return retVal;
						}
						
					}			
					
			

				} else{
					
					if(pTypeFormat.equalsIgnoreCase("brokenType")) {
						Double stockCarat = stoneInwardPk.getBrkCarat();
						Double currTrfCarat = Double.parseDouble(vTrfLossCarat[j]);
						
						if (stockCarat < currTrfCarat) {
							retVal = "BrokenStockError";
							return retVal;
						}
						
						
					}else{
						
						Double stockCarat = stoneInwardPk.getFallCarat();
						Double currTrfCarat = Double.parseDouble(vTrfLossCarat[j]);
						
						if (stockCarat < currTrfCarat) {
							retVal = "FallenStockError";
							return retVal;
						}
						
					}			
					

				} 
				
					
				}
				

				
			
			
			
			
		
			/*DecimalFormat df = new DecimalFormat("#.###");*/
			
			
			
			
			StnBflMt stnBflMt = findOne(pMtId);
			
			Date date = new java.util.Date();
			
			
			/*List<StoneInwardDt> stoneInwardDtList = new ArrayList<StoneInwardDt>();
			List<StnBflDt> 		stnBflDtList	  = new ArrayList<StnBflDt>();
			List<StoneTran>stoneTranList = new ArrayList<StoneTran>();*/
			
			for(int i=0; i < vStnDtId.length; i++){
				
				StoneInwardDt stoneInwardDt = stoneInwardDtService.findOne(Integer.parseInt(vStnDtId[i]));

				Integer tempAdjStone = 0;
				Double tempAdjCarat = 0.00;
				
				Integer tempLossStone = 0;
				Double tempLossCarat = 0.00;

				tempAdjStone = Integer.parseInt(vTrfAdjStone[i]);
				tempAdjCarat = Double.parseDouble( vTrfAdjCarat[i]);
				
				tempLossStone = Integer.parseInt(vTrfLossStone[i]);
				tempLossCarat = Double.parseDouble(vTrfLossCarat[i]);
				
				df.format(tempAdjCarat);
				df.format(tempLossCarat);
				
				
				
							
		
				if(tempAdjCarat>0 || tempLossCarat > 0){
					
					StoneTran stoneTran = new StoneTran();
					
					if (tempAdjCarat >0){
						
						if(pTypeFormat.equalsIgnoreCase("brokenType")) {

							stoneInwardDt.setBrkCarat(Double.parseDouble(df.format(stoneInwardDt.getBrkCarat()-tempAdjCarat)));
							stoneInwardDt.setBrkStone(stoneInwardDt.getBrkStone()-tempAdjStone);   
											
										
						}else {
							
						    stoneInwardDt.setFallCarat(Double.parseDouble(df.format(stoneInwardDt.getFallCarat()-tempAdjCarat)));
							stoneInwardDt.setFallStone(stoneInwardDt.getFallStone()-tempAdjStone);
							
							
							
						} 
						
						
						 stoneInwardDt.setBalCarat(Double.parseDouble(df.format(stoneInwardDt.getBalCarat()+tempAdjCarat)));
						 stoneInwardDt.setBalStone(stoneInwardDt.getBalStone()+tempAdjStone);
    					 stoneInwardDt.setModiBy(principal.getName());
						 stoneInwardDt.setModiDt(date);

						
					}else{
						
						if(pTypeFormat.equalsIgnoreCase("brokenType")) {

							stoneInwardDt.setBrkCarat(Double.parseDouble(df.format(stoneInwardDt.getBrkCarat()-tempLossCarat)));
							stoneInwardDt.setBrkStone(stoneInwardDt.getBrkStone()-tempLossStone);   
										
						}else {
		
							 stoneInwardDt.setFallCarat(Double.parseDouble(df.format(stoneInwardDt.getFallCarat()-tempLossCarat)));
								stoneInwardDt.setFallStone(stoneInwardDt.getFallStone()-tempLossStone);
						
						}
						
						stoneInwardDt.setLossCarat(Double.parseDouble(df.format(stoneInwardDt.getLossCarat()+tempLossCarat)));
						stoneInwardDt.setLossStone(stoneInwardDt.getLossStone()+tempLossStone);
						stoneInwardDt.setModiBy(principal.getName());
						stoneInwardDt.setModiDt(date);
						 
						
						
						
					}
					
					
					stoneInwardDtService.save(stoneInwardDt);
					
				
			        StnBflDt stnBflDtNew = new StnBflDt();
					
			        
			        
					stnBflDtNew.setStnBflMt(stnBflMt);
					stnBflDtNew.setStnInwardMt(stoneInwardDt.getStoneInwardMt());
					stnBflDtNew.setStnInwardDt(stoneInwardDt);
					
					if(tempAdjCarat > 0){
														
					stnBflDtNew.setStone(tempAdjStone);
					stnBflDtNew.setCarat(tempAdjCarat);
					
					if(pTypeFormat.equalsIgnoreCase("brokenType")) {
						stnBflDtNew.setTranType("BrokenRec");
	
					}else{
						
						stnBflDtNew.setTranType("FallenRec");
						
					}
					
					}
					else{
						
						stnBflDtNew.setStone(tempLossStone);
						stnBflDtNew.setCarat(tempLossCarat);
						
						if(pTypeFormat.equalsIgnoreCase("brokenType")) {
							stnBflDtNew.setTranType("BrokenLoss");
						}else{
							stnBflDtNew.setTranType("FallenLoss");
						}
						
					}
					
					
					
					stnBflDtNew.setCreatedBy(principal.getName());
					stnBflDtNew.setCreatedDt(date);
					stnBflDtNew.setUniqueId(new java.util.Date().getTime()+i);
					
					stnBflDtService.save(stnBflDtNew);
					
					
					stoneTran.setInOutFld("C");
					stoneTran.setDeptLocation(departmentService.findByName("Diamond"));
					stoneTran.setParty(stoneInwardDt.getStoneInwardMt().getParty());
					stoneTran.setRefTranId(stnBflDtService.findByUniqueId(stnBflDtNew.getUniqueId()).getId());
					stoneTran.setBagSrNo(0);
					stoneTran.setDepartment(null); 
					stoneTran.setQuality(stoneInwardDt.getQuality());
					stoneTran.setRate(stoneInwardDt.getRate());
					stoneTran.setSetting(null);
					stoneTran.setSettingType(null);
					stoneTran.setShape(stoneInwardDt.getShape());
					stoneTran.setSize(stoneInwardDt.getSize());
					stoneTran.setSizeGroup(stoneInwardDt.getSizeGroup());
					stoneTran.setSieve(stoneInwardDt.getSieve());
					stoneTran.setStoneType(stoneInwardDt.getStoneType());
					stoneTran.setSubShape(stoneInwardDt.getSubShape() == null ? null : stoneInwardDt.getSubShape());
					stoneTran.setDeactive(false);
					stoneTran.setCreatedBy(principal.getName());
					stoneTran.setCreatedDt(new java.util.Date());
					stoneTran.setStnRecDtId(stoneInwardDt.getId());
					
					
					if(tempAdjCarat>0){
						stoneTran.setStone(tempAdjStone);
						stoneTran.setCarat(tempAdjCarat);
						stoneTran.setBalStone(tempAdjStone);
						stoneTran.setBalCarat(tempAdjCarat);
						if(pTypeFormat.equalsIgnoreCase("brokenType")) {
							stoneTran.setTranType("BrokenRec");
		
						}else{
							
							stoneTran.setTranType("FallenRec");
							
						}
						
					}else{
						stoneTran.setStone(tempLossStone);
						stoneTran.setCarat(tempLossCarat);
						stoneTran.setBalStone(tempLossStone);
						stoneTran.setBalCarat(tempLossCarat);
						
						if(pTypeFormat.equalsIgnoreCase("brokenType")) {
							stoneTran.setTranType("BrokenLoss");
						}else{
							stoneTran.setTranType("FallenLoss");
						}
						
						
					}
					stoneTranService.save(stoneTran);
					
					
					
					
			
					
			} // ending for loop if
				
				
					
				  
				  
	
		} // ending for loop
			
			
			
			
		retVal="1";	
			
	} // ending first if-else
		
		return retVal;
	}

}

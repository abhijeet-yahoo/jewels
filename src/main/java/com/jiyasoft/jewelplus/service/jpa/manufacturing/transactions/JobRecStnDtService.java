package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QJobRecStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobRecStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class JobRecStnDtService implements IJobRecStnDtService{
	
	@Autowired
	private IJobRecStnDtRepository jobRecStnDtRepository;
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private IJobRecDtService jobRecDtService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private IDepartmentService departmentService;

	@Override
	public void save(JobRecStnDt jobRecStnDt) {
		// TODO Auto-generated method stub
		jobRecStnDtRepository.save(jobRecStnDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobRecStnDt jobRecStnDt = jobRecStnDtRepository.findOne(id);
		jobRecStnDt.setDeactive(true);
		jobRecStnDt.setDeactiveDt(new Date());
		jobRecStnDtRepository.save(jobRecStnDt);
		
	}

	@Override
	public JobRecStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobRecStnDtRepository.findOne(id);
	}

	@Override
	public List<JobRecStnDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecStnDtRepository.findByJobRecMtAndDeactive(jobRecMt, deactive);
	}

	@Override
	public List<JobRecStnDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecStnDtRepository.findByJobRecDtAndDeactive(jobRecDt, deactive);
	}

	@Override
	public String transactionalSave(JobRecStnDt jobRecStnDt, Integer id, Integer jobRecMtId, Integer jobrecDtId,
			String pSieve, String pSizeGroup, Principal principal, Boolean netWtWithCompFlg) {

		String retVal = "Error";

		JobRecMt jobRecMt = jobRecMtService.findOne(jobRecMtId);
		JobRecDt jobRecDt = jobRecDtService.findOne(jobrecDtId);
		
		
		
		  @SuppressWarnings("unchecked")
				TypedQuery<Object> query =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_jobworkDiamondstock(?,?,?,?,?) }");
				query.setParameter(1,jobRecMt.getParty().getId());
				query.setParameter(2,jobRecStnDt.getStoneType().getId());
				query.setParameter(3,jobRecStnDt.getShape().getId());
				query.setParameter(4,jobRecStnDt.getQuality().getId());
				query.setParameter(5,jobRecStnDt.getSize());
				
				
				List<Object> balCaratList = query.getResultList();
				
				Double balCarat = (Double) balCaratList.get(0);	
				
				balCarat = balCarat !=null ? balCarat : 0.0;
				
		if (jobRecStnDt.getCarat() <= balCarat) {
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object> query2 =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_fifo_jobworkDiarate(?,?,?,?,?) }");
			query2.setParameter(1,jobRecMt.getParty().getId());
			query2.setParameter(2,jobRecStnDt.getStoneType().getId());
			query2.setParameter(3,jobRecStnDt.getShape().getId());
			query2.setParameter(4,jobRecStnDt.getQuality().getId());
			query2.setParameter(5,jobRecStnDt.getSize());
			
			List<Object> avgRateList = query2.getResultList();
			
			Double avgRate = (Double) avgRateList.get(0);
			
			

			if (id == null || id.equals("") || (id != null && id == 0)) {
				jobRecStnDt.setCreatedBy(principal.getName());
				jobRecStnDt.setCreatedDate(new java.util.Date());
				jobRecStnDt.setJobRecMt(jobRecMt);
				jobRecStnDt.setJobRecDt(jobRecDt);
				jobRecStnDt.setSieve(pSieve);
				jobRecStnDt.setBagMt(jobRecDt.getBagMt());
				jobRecStnDt.setSizeGroup(stoneChartService
						.findByShapeAndSizeAndDeactive(jobRecStnDt.getShape(), jobRecStnDt.getSize(), false)
						.getSizeGroup());
				int srNo = getMaxSrno(jobRecMt, jobRecDt);
				jobRecStnDt.setBagSrNo(srNo + 1);
				
				retVal = "1";

			} else {
				jobRecStnDt.setId(id);
				jobRecStnDt.setModiBy(principal.getName());
				jobRecStnDt.setModiDate(new java.util.Date());
				jobRecStnDt.setJobRecMt(jobRecMt);
				jobRecStnDt.setJobRecDt(jobRecDt);
				jobRecStnDt.setBagMt(jobRecDt.getBagMt());
				jobRecStnDt.setSieve(pSieve);
				jobRecStnDt.setSizeGroup(stoneChartService
						.findByShapeAndSizeAndDeactive(jobRecStnDt.getShape(), jobRecStnDt.getSize(), false)
						.getSizeGroup());
				retVal = "-2";
			}

			if (jobRecStnDt.getSetting().getId() == null) {
				jobRecStnDt.setSetting(null);
			}
			if (jobRecStnDt.getSettingType().getId() == null) {
				jobRecStnDt.setSettingType(null);
			}
			
			
			jobRecStnDt.setStnRate(avgRate);
			jobRecStnDt.setManualFlg(true);

			save(jobRecStnDt);

			TranMt tranMt = tranMtService.findByBagMtCurrStk(jobRecDt.getBagMt(), true);
			if (tranMt.getDepartment().getName().equalsIgnoreCase("Job Work")) {

				TranDt tranDt = new TranDt();
				tranDt.setBagMt(tranMt.getBagMt());
				tranDt.setBagSrNo(jobRecStnDt.getBagSrNo());
				tranDt.setCarat(jobRecStnDt.getCarat());
				tranDt.setCreatedBy(principal.getName());
				tranDt.setCreatedDate(new Date());
				tranDt.setCurrStk(true);
				tranDt.setDepartment(tranMt.getDepartment());
				tranDt.setDeptFrom(departmentService.findByName("Job Work"));
				tranDt.setPartNm(jobRecStnDt.getPartNm());
				tranDt.setPcs(tranMt.getPcs());
				tranDt.setQuality(jobRecStnDt.getQuality());
				tranDt.setSetting(jobRecStnDt.getSetting());
				tranDt.setSettingType(jobRecStnDt.getSettingType());
				tranDt.setShape(jobRecStnDt.getShape());
				tranDt.setSieve(jobRecStnDt.getSieve());
				tranDt.setSize(jobRecStnDt.getSize());
				tranDt.setSizeGroup(jobRecStnDt.getSizeGroup());
				tranDt.setStone(jobRecStnDt.getStone());
				tranDt.setStoneType(jobRecStnDt.getStoneType());
				tranDt.setTranDate(jobRecMt.getInvDate());
				tranDt.setTranMt(tranMt);
				tranDt.setRate(avgRate);
				tranDt.setAvgRate(avgRate);
				tranDt.setTransferRate((double)Math.round((avgRate+(avgRate*jobRecStnDt.getStoneType().getTransferRatePerc()/100))*100)/100);
				tranDt.setFactoryRate((double)Math.round((tranDt.getTransferRate()+(tranDt.getTransferRate()*jobRecStnDt.getStoneType().getFactoryRatePerc()/100))*100)/100);
				tranDt.setRefTranDtId(jobRecStnDt.getId());
				tranDt.setRefTranType("Jobwork");
				
				tranDtService.save(tranDt);
				
				
				Double caratWt=Math.round((tranDt.getCarat()/5)*1000.0)/1000.0;
				
				
				List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMt, true);
				TranMetal tranMetal = tranMetals.get(0);
				if(tranMetal.getMetalWeight()>0) {
					tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight()-caratWt)*1000.0)/1000.0);
					tranMetalService.save(tranMetal);
				}
				
				

			}

			jobRecDtService.updateGrossWt(jobRecDt, netWtWithCompFlg);
			
			
		
			
			
				
				
			
			
			
			
			

		} else {
			
			retVal="Stock Not sufficient ,Available Stock Carat ="+balCarat;

		}
	
		return retVal;
	}

	@Override
	public Integer getMaxSrno(JobRecMt jobRecMt, JobRecDt jobRecDt) {
		QJobRecStnDt qJobRecStnDt = QJobRecStnDt.jobRecStnDt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = 0;

		List<Integer> maxSrno = query
			.from(qJobRecStnDt)
			.where(qJobRecStnDt.deactive.eq(false).and(qJobRecStnDt.jobRecMt.id.eq(jobRecMt.getId())).and(qJobRecStnDt.jobRecDt.id.eq(jobRecDt.getId())))
			.list(qJobRecStnDt.bagSrNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}
		
		if(retVal == null){
			retVal =0;
		}
		

		return retVal;
	}

	@Override
	public String deleteJobRecStnDt(Integer stnId, Principal principal) {

		String retVal ="-1";
		
		try {
			
			JobRecStnDt jobRecStnDt = findOne(stnId);
			
			if(!jobRecStnDt.getManualFlg()) {
				return "Can not delete, Manual flag false ";
			}else {
				
				
				TranDt tranDt = tranDtService.findByRefTranTypeAndRefTranDtIdAndCurrStk("Jobwork", stnId,true);
				
				if(tranDt !=null) {
				
				Double caratWt=Math.round((tranDt.getCarat()/5)*1000.0)/1000.0;
				
				
				List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranDt.getTranMt(), true);
				TranMetal tranMetal = tranMetals.get(0);
				if(tranMetal.getMetalWeight()>0) {
					tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight()+caratWt)*1000.0)/1000.0);
					tranMetalService.save(tranMetal);
				}
				
				tranDtService.delete(tranDt.getId());
			}
				
				delete(stnId);
				retVal ="1";
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

}

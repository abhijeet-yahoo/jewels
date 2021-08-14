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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobMtlRecDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobMtlRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@Service
@Transactional
@Repository
public class JobMtlRecService implements IJobMtlRecDtService{

	@Autowired
	private IJobMtlRecDtRepository jobMtlRecDtRepository;
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private IPurityService purityService;
	
	@Override
	public List<JobMtlRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobMtlRecDtRepository.findByJobRecMtAndDeactive(jobRecMt, deactive);
	}

	@Override
	public JobMtlRecDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobMtlRecDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(JobMtlRecDt jobMtlRecDt) {
		// TODO Auto-generated method stub
		jobMtlRecDtRepository.save(jobMtlRecDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobMtlRecDt jobMtlRecDt = jobMtlRecDtRepository.findOne(id);
		jobMtlRecDt.setDeactive(true);
		jobMtlRecDt.setDeactiveDt(new Date());
		jobMtlRecDtRepository.save(jobMtlRecDt);
	}

	@Override
	public JobMtlRecDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobMtlRecDtRepository.findOne(id);
	}

	@Override
	public String jobMtlRecDtSave(JobMtlRecDt jobMtlRecDt, Integer id, Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		
		String action = "added";
		String retVal="-1";
		Date aDate = null;
		
			JobRecMt jobRecMt= jobRecMtService.findOne(mtId);
		
			Purity purity =purityService.findOne(jobMtlRecDt.getPurity().getId());
		
		  	@SuppressWarnings("unchecked")
			TypedQuery<Object> query =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_jobworkmetalstock(?,?) }");
			query.setParameter(1,jobRecMt.getParty().getId());
			query.setParameter(2,purity.getMetal().getId());
			
			
			
			List<Object> balPureList = query.getResultList();
			
			Double balPure = (Double) balPureList.get(0);
			
			Double recPure =Math.round((jobMtlRecDt.getMetalWt()*purity.getPurityConv())*1000.0)/1000.0;
			
			if(recPure<=balPure) {

		
			MetalTran metalTran = null;
			aDate = new java.util.Date();
			
			
			@SuppressWarnings("unchecked")
			TypedQuery<Object> query2 =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_fifo_jobworkmetalrate(?,?) }");
			query2.setParameter(1,jobRecMt.getParty().getId());
			query2.setParameter(2,purity.getMetal().getId());
			
			List<Object> mtlRateList = query2.getResultList();
			
			Double mtlRate = (Double) mtlRateList.get(0);
			
			mtlRate = 	mtlRate != null ? mtlRate :0.0;
			
			
			mtlRate=Math.round((mtlRate*purity.getPurityConv())*100.0)/100.0;
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				jobMtlRecDt.setCreatedBy(principal.getName());
				jobMtlRecDt.setCreatedDt(new java.util.Date());
				jobMtlRecDt.setJobRecMt(jobRecMt);
				jobMtlRecDt.setUniqueId(aDate.getTime());
				jobMtlRecDt.setDepartment(jobMtlRecDt.getDepartment());
				jobMtlRecDt.setPurityConv(purity.getPurityConv());
		//		metalInwardDt.setBalance(Math.round((invWtt*purityService.findOne(purityyId).getPurityConv())*1000.0)/1000.0);
				metalTran = new MetalTran();
				retVal = "1"; 

			} else {
				jobMtlRecDt.setId(id);
				jobMtlRecDt.setModiBy(principal.getName());
				jobMtlRecDt.setModiDt(new java.util.Date());
				jobMtlRecDt.setJobRecMt(jobRecMt);
				jobMtlRecDt.setUniqueId(aDate.getTime());
				jobMtlRecDt.setDepartment(jobMtlRecDt.getDepartment());
				jobMtlRecDt.setPurityConv(purity.getPurityConv());
				metalTran = metalTranService.RefTranIdAndTranType(id, "JobMtlRec");
				
				action = "updated";	
				retVal = "2"; 

			}

			jobMtlRecDt.setRate(mtlRate);
			jobMtlRecDt.setAmount(Math.round(mtlRate * jobMtlRecDt.getMetalWt())*100.0/100.0);
			save(jobMtlRecDt);
			

			if(action == "added"){
			
			metalTran.setCreatedBy(jobMtlRecDt.getCreatedBy());
			metalTran.setCreatedDt(jobMtlRecDt.getCreatedDt());
			}else{
				
				metalTran.setModiBy(principal.getName());
				metalTran.setModiDt(new java.util.Date());
			}
			
			/*
			 * Double mtlRate =
			 * metalTranService.getMetalRate(jobMtlRecDt.getPurity().getId(),jobMtlRecDt.
			 * getColor().getId(), jobMtlRecDt.getDepartment().getId(),
			 * jobMtlRecDt.getMetalWt());
			 */
			
			//metalTran.setTranDate(new java.util.Date());
			metalTran.setColor(jobMtlRecDt.getColor());
			metalTran.setPurity(jobMtlRecDt.getPurity());
			metalTran.setInOutFld("C");
			metalTran.setBalance(jobMtlRecDt.getMetalWt());
			metalTran.setMetalWt(jobMtlRecDt.getMetalWt());
			metalTran.setDeptLocation(jobMtlRecDt.getDepartment());
			metalTran.setPurityConv(jobMtlRecDt.getPurityConv());
			metalTran.setRefTranId(jobMtlRecDt.getId());
			metalTran.setTranType("JobMtlRec");
			metalTran.setTranDate(jobMtlRecDt.getJobRecMt().getInvDate());
			metalTran.setMetalRate(mtlRate != null ? mtlRate :0.0);
			
			metalTranService.save(metalTran);
			
			}else {
				retVal="Stock Not sufficient ,Available Stock in Pure ="+balPure;
			}

		
		return retVal;
	}

	@Override
	public String jobMtlRecDtDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
	String retVal ="-1";
		
		try {
	
			List<MetalTran> metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(id, "JobMtlRec",false);
			for (MetalTran metTran : metalTran) {
					metalTranService.delete(metTran.getId());
			}
			
			delete(id);
			retVal = "1";
		
	}catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

}

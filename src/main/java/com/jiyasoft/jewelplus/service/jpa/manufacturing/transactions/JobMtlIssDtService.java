package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobMtlIssDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobMtlIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;

@Service
@Repository
@Transactional
public class JobMtlIssDtService implements IJobMtlIssDtService {
	
	@Autowired
	private IJobMtlIssDtRepository jobMtlIssDtRepository;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IJobIssMtService jobIssMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Override
	public List<JobMtlIssDt> findByJobIssMt(JobIssMt jobIssMt) {
		// TODO Auto-generated method stub
		return jobMtlIssDtRepository.findByJobIssMt(jobIssMt);
	}

	@Override
	public List<JobMtlIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobMtlIssDtRepository.findByJobIssMtAndDeactive(jobIssMt, deactive);
	}

	@Override
	public JobMtlIssDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobMtlIssDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(JobMtlIssDt jobMtlIssDt) {
		// TODO Auto-generated method stub
		jobMtlIssDtRepository.save(jobMtlIssDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobMtlIssDt jobMtlIssDt = jobMtlIssDtRepository.findOne(id);
		jobMtlIssDt.setDeactive(true);
		jobMtlIssDt.setDeactiveDt(new Date());
		jobMtlIssDtRepository.save(jobMtlIssDt);
	}

	@Override
	public JobMtlIssDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobMtlIssDtRepository.findOne(id);
	}

	@Override
	public Map<Integer, String> getJobMtlIssDtList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String jobMtlIssDtSave(JobMtlIssDt jobMtlIssDt, Integer id,Integer mtId,Principal principal) {
		// TODO Auto-generated method stub
		String action ="added";
		String retVal="-1";
		
		JobIssMt jobIssMt = jobIssMtService.findOne(mtId);
		
			MetalTran metalTran = null;
			jobMtlIssDt.setJobIssMt(jobIssMt);
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				  Double balance =   metalTranService.getStockBalance(jobMtlIssDt.getPurity().getId(),
						  jobMtlIssDt.getColor().getId(),jobMtlIssDt.getDepartment().getId());
				  
				  if (balance == null) { 
					  balance = 0.0;
				  }
				  
				  if (balance < jobMtlIssDt.getMetalWt()) { 
					  return "Error : Stock Not Available (Available In Stock : "+balance+")"; 
					  }
				 
		
				jobMtlIssDt.setCreatedBy(principal.getName());
				jobMtlIssDt.setCreatedDt(new java.util.Date());
				jobMtlIssDt.setJobIssMt(jobIssMt);
				jobMtlIssDt.setUniqueId(new Date().getTime());
				jobMtlIssDt.setPurityConv(purityService.findOne(jobMtlIssDt.getPurity().getId()).getPurityConv());
				
				
				metalTran = new MetalTran();
				retVal = "1";
				
			} else {
				
				
				JobMtlIssDt jobMtlIssDt2 = findOne(id);
				
				  Double balance =  metalTranService.getStockBalance(jobMtlIssDt2.getPurity().getId(),
						  jobMtlIssDt2.getColor().getId(),jobMtlIssDt2.getDepartment().getId());
				  
				  Double difference = jobMtlIssDt.getMetalWt() - jobMtlIssDt2.getMetalWt();
				  if(balance == null) 
				  { balance = 0.0;
				  
				  }
				  
				  if (balance < difference) { return
				  "Error : Stock Not Available (Available In Stock : "+balance+")"; }
				 
				
				

		
				jobMtlIssDt.setModiBy(principal.getName());
				jobMtlIssDt.setModiDt(new java.util.Date());

				
		
				metalTran = metalTranService.RefTranIdAndTranType(id, "JobMtlIss");
				retVal="2";
				action ="updated";
			}
			
			
			 Double mtlRate =metalTranService.getMetalRate(jobMtlIssDt.getPurity().getId(), jobMtlIssDt.getColor().getId(), 
					  jobMtlIssDt.getDepartment().getId(), jobMtlIssDt.getMetalWt());
			 
			 mtlRate = mtlRate != null ? mtlRate :0.0;
			 
			 jobMtlIssDt.setRate(mtlRate);
			 jobMtlIssDt.setAmount(Math.round(mtlRate * jobMtlIssDt.getMetalWt())*100.0/100.0);
			 save(jobMtlIssDt);
			
			  
			  
			  if(action == "added"){
				 
			  metalTran.setCreatedBy(jobMtlIssDt.getCreatedBy());
			  metalTran.setCreatedDt(jobMtlIssDt.getCreatedDt());
			  
			  metalTran.setMetalRate(mtlRate);
			  
		
			  }else{ 
				  jobMtlIssDt = findOne(id);
			  
			  metalTran.setModiBy(principal.getName()); 
			  metalTran.setModiDt(new java.util.Date());
			  
			  } 
			  
			  metalTran.setColor(jobMtlIssDt.getColor());
			  metalTran.setPurity(jobMtlIssDt.getPurity());
			  metalTran.setInOutFld("D");
			  metalTran.setBagMt(null); 
			  metalTran.setBalance(jobMtlIssDt.getMetalWt() * -1); 
			  metalTran.setMetalWt(jobMtlIssDt.getMetalWt());
			  metalTran.setDeptLocation(jobMtlIssDt.getDepartment());
			  metalTran.setPurityConv(jobMtlIssDt.getPurityConv());
			  metalTran.setRefTranId(jobMtlIssDt.getId());
			  metalTran.setTranType("JobMtlIss");
			  metalTran.setRemark("");
			  metalTran.setDepartment(null);
			  metalTran.setTranDate(new Date());
						
			metalTranService.save(metalTran);
			
			
	/*	} catch (Exception e) {
			
			return e.toString();
		}*/
		
		return retVal;
	}

	@Override
	public String jobMtlIssDtDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
		String retVal ="-1";
		
		try {
			
			MetalTran metalTran = metalTranService.RefTranIdAndTranType(id, "JobMtlIss");
			metalTranService.delete(metalTran.getId());
			
			delete(id);
			retVal ="1";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

}

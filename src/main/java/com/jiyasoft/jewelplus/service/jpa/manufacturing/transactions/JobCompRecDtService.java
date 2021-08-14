package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobCompRecDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobCompRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;

@Service
@Repository
@Transactional
public class JobCompRecDtService implements IJobCompRecDtService {
	
	@Autowired
	private IJobCompRecDtRepository jobCompRecDtRepository;
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IPurityService purityService;

	@Override
	public List<JobCompRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobCompRecDtRepository.findByJobRecMtAndDeactive(jobRecMt, deactive);
	}

	@Override
	public JobCompRecDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobCompRecDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(JobCompRecDt jobCompRecDt) {
		// TODO Auto-generated method stub
		jobCompRecDtRepository.save(jobCompRecDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobCompRecDt jobCompRecDt = jobCompRecDtRepository.findOne(id);
		jobCompRecDt.setDeactive(true);
		jobCompRecDt.setDeactiveDt(new Date());
		jobCompRecDtRepository.save(jobCompRecDt);
	}

	@Override
	public JobCompRecDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobCompRecDtRepository.findOne(id);
	}

	@Override
	public String jobCompRecDtSave(JobCompRecDt jobCompRecDt, Integer id, Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		
		String action = "added";
		String retVal="-1";
		
		JobRecMt jobRecMt = jobRecMtService.findOne(mtId);
		try {
			
			Purity purity = purityService.findOne(jobCompRecDt.getPurity().getId());
					
			CompTran compTran = null;

			if (id == null || id.equals("") || (id != null && id == 0)) {
				jobCompRecDt.setCreatedBy(principal.getName());
				jobCompRecDt.setCreatedDt(new java.util.Date());
				jobCompRecDt.setUniqueId(new Date().getTime());
				jobCompRecDt.setPurityConv(purity.getPurityConv());
				jobCompRecDt.setBalance(jobCompRecDt.getMetalWt());
				compTran = new CompTran();
				retVal = "1";
			} else {
				
				jobCompRecDt.setModiBy(principal.getName());
				jobCompRecDt.setModiDt(new java.util.Date());
				jobCompRecDt.setPurityConv(jobCompRecDt.getPurity().getPurityConv());
				jobCompRecDt.setBalance(jobCompRecDt.getMetalWt());
			
				compTran = compTranService.RefTranIdAndTranType(id, "JobCompRec");
				
				action = "updated";	
				retVal = "2";
			}

			jobCompRecDt.setJobRecMt(jobRecMt);

			save(jobCompRecDt);
			
			JobCompRecDt jobCompRecDt2 = null;
			if(action == "added"){
				jobCompRecDt2=findByUniqueId(jobCompRecDt.getUniqueId());
				compTran.setCreatedBy(jobCompRecDt2.getCreatedBy());
				compTran.setCreatedDt(jobCompRecDt2.getCreatedDt());
				compTran.setTrandate(jobRecMt.getInvDate());
			}else{
				jobCompRecDt2=findOne(id);
				compTran.setModiBy(principal.getName());
				compTran.setModiDt(new java.util.Date());
			}
			
			Double compMtlRate=compTranService.getCompMetalRate(jobCompRecDt2.getPurity().getId(), jobCompRecDt2.getColor().getId(), jobCompRecDt2.getDepartment().getId(), 
					jobCompRecDt2.getComponent().getId(),jobCompRecDt2.getMetalWt());
			

			compTran.setColor(jobCompRecDt2.getColor());
			compTran.setPurity(jobCompRecDt2.getPurity());
			compTran.setInOutFld("C");
			compTran.setBagMt(null);
			compTran.setBalance(jobCompRecDt2.getMetalWt());
			compTran.setMetalWt(jobCompRecDt2.getMetalWt());
			compTran.setDeptLocation(jobCompRecDt2.getDepartment());
			compTran.setPurityConv(jobCompRecDt2.getPurityConv());
			compTran.setRefTranId(jobCompRecDt2.getId());
			compTran.setTranType("JobCompRec");
			compTran.setRemark("");
			compTran.setDepartment(null);
			compTran.setComponent(jobCompRecDt2.getComponent());
			
			compTran.setPcs(jobCompRecDt2.getQty());
			compTran.setBalancePcs(jobCompRecDt2.getQty());
			compTran.setMetalRate(compMtlRate);
			compTranService.save(compTran);
			
			
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;
	}

	@Override
	public String jobCompRecDtDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
	String retVal ="-1";
		
		try {
			
			delete(id);
			List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(id, "JobCompRec");
			for (CompTran comTran : compTran) {
				compTranService.delete(comTran.getId());
			}
			retVal ="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

}

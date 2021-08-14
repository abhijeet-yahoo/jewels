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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobCompIssDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobCompIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;

@Service
@Repository
@Transactional
public class JobCompIssDtService implements IJobCompIssDtService{
	
	@Autowired
	private IJobCompIssDtRepository jobCompIssDtRepository;
	
	@Autowired
	private IJobIssMtService jobIssMtService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IPurityService purityService;

	@Override
	public List<JobCompIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobCompIssDtRepository.findByJobIssMtAndDeactive(jobIssMt, deactive);
	}

	@Override
	public JobCompIssDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobCompIssDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(JobCompIssDt jobCompIssDt) {
		// TODO Auto-generated method stub
		jobCompIssDtRepository.save(jobCompIssDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobCompIssDt jobCompIssDt = jobCompIssDtRepository.findOne(id);
		jobCompIssDt.setDeactive(true);
		jobCompIssDt.setDeactiveDt(new Date());
		jobCompIssDtRepository.save(jobCompIssDt);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobCompIssDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobCompIssDtRepository.findOne(id);
	}

	@Override
	public String saveJobCompIssDt(JobCompIssDt jobCompIssDt, Integer id, Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal="-1";
			
		
		try {
			
			CompTran compTran = null;
			
			JobIssMt jobIssMt = jobIssMtService.findOne(mtId);

			Double compMtlRate=compTranService.getCompMetalRate(jobCompIssDt.getPurity().getId(), jobCompIssDt.getColor().getId(), jobCompIssDt.getDepartment().getId(), 
					jobCompIssDt.getComponent().getId(),jobCompIssDt.getMetalWt());
		
			compMtlRate = compMtlRate != null ? compMtlRate :0.0;
			
			Purity purity = purityService.findOne(jobCompIssDt.getPurity().getId());
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Double tempBal = compTranService.getStockBalance(jobCompIssDt.getPurity().getId(),jobCompIssDt.getColor().getId(),jobCompIssDt.getDepartment().getId(), 
						jobCompIssDt.getComponent().getId());
				
				if (tempBal == null) {
					tempBal = 0.0;
				}

				if (tempBal < jobCompIssDt.getMetalWt()) {
					return "Error : Stock Not Available (Available In Stock : "+tempBal+")";
				}
				
				jobCompIssDt.setCreatedBy(principal.getName());
				jobCompIssDt.setCreatedDt(new java.util.Date());
				jobCompIssDt.setUniqueId(new Date().getTime());
				jobCompIssDt.setPurityConv(purity.getPurityConv());
				
				compTran = new CompTran();
				retVal ="1";

				} else {
				
					JobCompIssDt jobCompIssDt2 =findOne(id);	
					
					
					Double tempBal = compTranService.getStockBalance(jobCompIssDt2.getPurity().getId(),jobCompIssDt2.getColor().getId(),jobCompIssDt2.getDepartment().getId(), 
							jobCompIssDt2.getComponent().getId());
					
					Double difference = jobCompIssDt.getMetalWt() - jobCompIssDt2.getMetalWt();

					if (tempBal < difference) {
						return "Error : Stock Not Available (Available In Stock : "+tempBal+")";
					}
					
					
					jobCompIssDt.setModiBy(principal.getName());
					jobCompIssDt.setModiDt(new java.util.Date());
				
				
				compTran = compTranService.RefTranIdAndTranType(id, "JobCompIss");
				action = "updated";	
				
				retVal ="2";
			}
			
			
			jobCompIssDt.setRate(compMtlRate);
			jobCompIssDt.setAmount(Math.round(compMtlRate * jobCompIssDt.getMetalWt())*100.0/100.0);
			 
			 
			jobCompIssDt.setJobIssMt(jobIssMt);
			jobCompIssDt.setBalance(jobCompIssDt.getMetalWt());
			
			save(jobCompIssDt);

			
			
		
			
			if(action == "added"){
				
				compTran.setCreatedBy(jobCompIssDt.getCreatedBy());
				compTran.setCreatedDt(jobCompIssDt.getCreatedDt());
				compTran.setTrandate(jobCompIssDt.getJobIssMt().getInvDate());
			}else{
				
				compTran.setModiBy(principal.getName());
				compTran.setModiDt(new java.util.Date());
			}
			
			
			

			compTran.setPurity(jobCompIssDt.getPurity());
			compTran.setInOutFld("D");
			compTran.setBagMt(null);
			compTran.setBalance(jobCompIssDt.getMetalWt() * -1);
			compTran.setMetalWt(jobCompIssDt.getMetalWt());
			compTran.setDeptLocation(jobCompIssDt.getDepartment());
			compTran.setPurityConv(jobCompIssDt.getPurityConv());
			compTran.setRefTranId(jobCompIssDt.getId());
			compTran.setTranType("JobCompIss");
			compTran.setRemark("");
			compTran.setDepartment(jobCompIssDt.getDepartment());
			compTran.setPcs(jobCompIssDt.getQty());
			compTran.setComponent(jobCompIssDt.getComponent());
			compTran.setColor(jobCompIssDt.getColor());
			compTran.setMetalRate(compMtlRate);
			compTranService.save(compTran);
			
			
									
		} catch (Exception e) {
			
			return e.toString();
		}
		
		return retVal;

	}

	@Override
	public String jobCompIssDtDelete(Integer id, Principal principal) {
		String retVal ="-1";
		
		try {
			
			delete(id);
			List<CompTran> compTran = compTranService.findByRefTranIdAndTranType(id, "JobCompIss");
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

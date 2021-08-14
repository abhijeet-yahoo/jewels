package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobRecLabDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;

@Service
@Repository
@Transactional
public class JobRecLabDtService implements IJobRecLabDtService {
	
	@Autowired
	private IJobRecLabDtRepository jobRecLabDtRepository;
	
	@Autowired
	private IJobRecDtService jobRecDtService;
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	

	@Override
	public List<JobRecLabDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecLabDtRepository.findByJobRecMtAndDeactive(jobRecMt, deactive);
	}

	@Override
	public List<JobRecLabDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecLabDtRepository.findByJobRecDtAndDeactive(jobRecDt, deactive);
	}

	@Override
	public List<JobRecLabDt> findByJobRecDtAndMetalAndDeactive(JobRecDt JobRecDt, Metal metal, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecLabDtRepository.findByJobRecDtAndMetalAndDeactive(JobRecDt, metal, deactive);
	}

	@Override
	public void save(JobRecLabDt jobRecLabDt) {
		// TODO Auto-generated method stub
		jobRecLabDtRepository.save(jobRecLabDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobRecLabDt jobRecLabDt = jobRecLabDtRepository.findOne(id);
		jobRecLabDt.setDeactive(true);
		jobRecLabDt.setDeactiveDt(new Date());
		jobRecLabDtRepository.save(jobRecLabDt);
	}

	@Override
	public JobRecLabDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobRecLabDtRepository.findOne(id);
	}

	@Override
	public String jobRecLabDtListing(Integer dtId, String disableFlg) {
		// TODO Auto-generated method stub
		JobRecDt jobRecDt = jobRecDtService.findOne(dtId);
		List<JobRecLabDt> jobRecLabDts = findByJobRecDtAndDeactive(jobRecDt, false);
	
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(jobRecLabDts.size()).append(",\"rows\": [");
		  for(JobRecLabDt jobRecLabDt :jobRecLabDts){
				sb.append("{\"id\":\"")
				.append(jobRecLabDt.getId())
				.append("\",\"metal\":\"")
				.append((jobRecLabDt.getMetal() != null ? jobRecLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((jobRecLabDt.getLabourType() != null ? jobRecLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((jobRecLabDt.getLabourRate() != null ? jobRecLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((jobRecLabDt.getLabourValue() != null ? jobRecLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(jobRecLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(jobRecLabDt.getPcsWise())
				.append("\",\"perGram\":\"")
				.append(jobRecLabDt.getPerGramRate())
				.append("\",\"percent\":\"")
				.append(jobRecLabDt.getPercentWise())
				.append("\",\"perCaratRate\":\"")
				.append(jobRecLabDt.getPerCaratRate());
				if(disableFlg.equalsIgnoreCase("false")) {
					sb.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doLabDtLockUnLock(")
					.append(jobRecLabDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editJobRecLabDt(")
					.append(jobRecLabDt.getId())
					.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteJobRecLabDt(event,")
					.append(jobRecLabDt.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(jobRecLabDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");	
				}else {
					 sb.append("\",\"actionLock\":\"")
						.append("")
						.append("\",\"action1\":\"")
						.append("")
						.append("\",\"action2\":\"")
						 .append("\"},");
				}
				
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
	}

	@Override
	public String jobRecLabDtSave(JobRecLabDt jobRecLabDt, Integer id, Integer jobRecMtId, Integer jobRecDtId,
			Principal principal) {

		String retVal ="-1";
		
		try {
			
			JobRecMt jobRecMt = jobRecMtService.findOne(jobRecMtId);
			JobRecDt jobRecDt = jobRecDtService.findOne(jobRecDtId);
			
			
			
			
			int i=0;
			if(jobRecLabDt.getPcsWise() == true){
				i +=1;
			}
			
			if(jobRecLabDt.getPerGramRate() == true){
				i +=1;
			}
			
			if(jobRecLabDt.getPercentWise() == true){
				i +=1;
			}
			
			if(jobRecLabDt.getPerCaratRate() == true){
				i +=1;
			}
			
			if(i >1){
				return retVal = "-11";
			}else if(i==0){
				return retVal = "-12";
			}
			
			
			Boolean labourCheckFlg =false;
//			List<JobRecLabDt> jobRecLabDts = findByJobRecDtAndDeactive(jobRecDt, false);
//			for (JobRecLabDt jobRecLabDt2 : jobRecLabDts) {
//				
//				if(jobRecLabDt2.getLabourType().getId() == jobRecLabDt.getLabourType().getId()) {
//					labourCheckFlg = true;
//					break;
//				}
//			}
			
			if(labourCheckFlg) {
				return "-13";
			}
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				jobRecLabDt.setJobRecMt(jobRecMt);
				jobRecLabDt.setJobRecDt(jobRecDt);
				jobRecLabDt.setCreatedBy(principal.getName());
				jobRecLabDt.setCreatedDate(new java.util.Date());

				
				
			}else{
				jobRecLabDt.setId(id);
				jobRecLabDt.setJobRecMt(jobRecMt);
				jobRecLabDt.setJobRecDt(jobRecDt);
			
				jobRecLabDt.setModiBy(principal.getName());
				jobRecLabDt.setModiDate(new java.util.Date());
				retVal = "-2";
			}
			
			
			save(jobRecLabDt);
			
			
				
		//costingDtItemService.applyItemLabRate(costingDtItem, principal);
			jobRecDtService.updateFob(jobRecDt, false);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

}

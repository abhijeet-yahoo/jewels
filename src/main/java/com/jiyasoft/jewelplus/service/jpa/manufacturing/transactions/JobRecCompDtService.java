package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobRecCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecDtService;

@Service
@Repository
@Transactional
public class JobRecCompDtService implements IJobRecCompDtService{
	
	@Autowired
	private IJobRecCompDtRepository jobRecCompDtRepository;
	
	@Autowired
	private IJobRecDtService jobRecDtService;

	@Override
	public void save(JobRecCompDt jobRecCompDt) {
		// TODO Auto-generated method stub
		jobRecCompDtRepository.save(jobRecCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobRecCompDt jobRecCompDt = jobRecCompDtRepository.findOne(id);
		jobRecCompDt.setDeactive(true);
		jobRecCompDt.setDeactiveDt(new Date());
		jobRecCompDtRepository.save(jobRecCompDt);
	}

	@Override
	public JobRecCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobRecCompDtRepository.findOne(id);
	}

	@Override
	public List<JobRecCompDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecCompDtRepository.findByJobRecMtAndDeactive(jobRecMt, deactive);
	}

	@Override
	public List<JobRecCompDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecCompDtRepository.findByJobRecDtAndDeactive(jobRecDt, deactive);
	}

	@Override
	public String jobRecCompDtListing(Integer dtId, String disableFlg) {
		// TODO Auto-generated method stub
		JobRecDt jobRecDt = jobRecDtService.findOne(dtId);
		List<JobRecCompDt> jobRecCompDts = findByJobRecDtAndDeactive(jobRecDt, false);
	
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(jobRecCompDts.size()).append(",\"rows\": [");
		  for(JobRecCompDt jobRecCompDt :jobRecCompDts){
				sb.append("{\"id\":\"")
				.append(jobRecCompDt.getId())
				.append("\",\"compName\":\"")
				.append((jobRecCompDt.getComponent() != null ? jobRecCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((jobRecCompDt.getPurity() != null ? jobRecCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((jobRecCompDt.getColor() != null ? jobRecCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((jobRecCompDt.getMetalWt() != null ? jobRecCompDt.getMetalWt() : ""))
				.append("\",\"rate\":\"")
				.append((jobRecCompDt.getCompRate() != null ? jobRecCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((jobRecCompDt.getCompValue() != null ? jobRecCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((jobRecCompDt.getCompPcs() != null ? jobRecCompDt.getCompPcs() : ""))
				.append("\",\"perPcs\":\"")
				.append(jobRecCompDt.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(jobRecCompDt.getPerGramRate())
				.append("\",\"rLock\":\"")
				.append((jobRecCompDt.getrLock())); //1 = lock & 0 = unlock
				
				if(disableFlg.equalsIgnoreCase("false")) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doPackDtLockUnLock(")
				.append(jobRecCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editJobRecCompDt(")
				.append(jobRecCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteJobRecCompDt(event,")
				.append(jobRecCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(jobRecCompDt.getId())
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

}

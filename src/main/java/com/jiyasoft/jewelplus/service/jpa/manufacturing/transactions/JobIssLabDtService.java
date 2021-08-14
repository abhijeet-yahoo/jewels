package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobIssLabDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssLabDtService;

@Service
@Transactional
@Repository
public class JobIssLabDtService implements IJobIssLabDtService{
	
	@Autowired
	private IJobIssLabDtRepository jobIssLabDtRepository;

	@Override
	public List<JobIssLabDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssLabDtRepository.findByJobIssMtAndDeactive(jobIssMt, deactive);
	}

	@Override
	public List<JobIssLabDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssLabDtRepository.findByJobIssDtAndDeactive(jobIssDt, deactive);
	}

	@Override
	public List<JobIssLabDt> findByJobIssDtAndMetalAndDeactive(JobIssDt JobIssDt, Metal metal, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssLabDtRepository.findByJobIssDtAndMetalAndDeactive(JobIssDt, metal, deactive);
	}

	@Override
	public void save(JobIssLabDt jobIssLabDt) {
		// TODO Auto-generated method stub
		jobIssLabDtRepository.save(jobIssLabDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobIssLabDt jobIssLabDt = jobIssLabDtRepository.findOne(id);
		jobIssLabDt.setDeactive(true);
		jobIssLabDt.setDeactiveDt(new Date());
		jobIssLabDtRepository.save(jobIssLabDt);
	}

	@Override
	public JobIssLabDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobIssLabDtRepository.findOne(id);
	}

}

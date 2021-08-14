package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobIssMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMetalDtService;

@Service
@Repository
@Transactional
public class JobIssMetalDtService implements IJobIssMetalDtService{
	
	@Autowired
	private IJobIssMetalDtRepository jobIssMetalDtRepository;

	@Override
	public void save(JobIssMetalDt jobIssMetalDt) {
		// TODO Auto-generated method stub
		jobIssMetalDtRepository.save(jobIssMetalDt);
	}

	@Override
	public void delete(int id) {
	
		JobIssMetalDt jobIssMetalDt =  jobIssMetalDtRepository.findOne(id);
		jobIssMetalDt.setDeactive(true);
		jobIssMetalDt.setDeactiveDt(new Date());
		jobIssMetalDtRepository.save(jobIssMetalDt);
		
	}

	@Override
	public JobIssMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobIssMetalDtRepository.findOne(id);
	}

	@Override
	public List<JobIssMetalDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssMetalDtRepository.findByJobIssMtAndDeactive(jobIssMt, deactive);
	}

	@Override
	public List<JobIssMetalDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssMetalDtRepository.findByJobIssDtAndDeactive(jobIssDt, deactive);
	}

	@Override
	public JobIssMetalDt findByJobIssDtAndDeactiveAndMainMetal(JobIssDt jobIssDt, Boolean deactive, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return jobIssMetalDtRepository.findByJobIssDtAndDeactiveAndMainMetal(jobIssDt, deactive, mainMetal);
	}



}

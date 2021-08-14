package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobRecMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMetalDtService;

@Service
@Repository
@Transactional
public class JobRecMetalDtService implements IJobRecMetalDtService{
	
	@Autowired
	private IJobRecMetalDtRepository jobRecMetalDtRepository;

	@Override
	public void save(JobRecMetalDt jobRecMetalDt) {
		// TODO Auto-generated method stub
		jobRecMetalDtRepository.save(jobRecMetalDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobRecMetalDt jobRecMetalDt = jobRecMetalDtRepository.findOne(id);
		jobRecMetalDt.setDeactive(true);
		jobRecMetalDt.setDeactiveDt(new Date());
		jobRecMetalDtRepository.save(jobRecMetalDt);
	}

	@Override
	public JobRecMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobRecMetalDtRepository.findOne(id);
	}

	@Override
	public List<JobRecMetalDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecMetalDtRepository.findByJobRecMtAndDeactive(jobRecMt, deactive);
	}

	@Override
	public List<JobRecMetalDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecMetalDtRepository.findByJobRecDtAndDeactive(jobRecDt, deactive);
	}

	@Override
	public JobRecMetalDt findByJobRecDtAndDeactiveAndMainMetal(JobRecDt jobRecDt, Boolean deactive, Boolean mainMetal) {
		// TODO Auto-generated method stub
		return jobRecMetalDtRepository.findByJobRecDtAndDeactiveAndMainMetal(jobRecDt, deactive, mainMetal);
	}

}

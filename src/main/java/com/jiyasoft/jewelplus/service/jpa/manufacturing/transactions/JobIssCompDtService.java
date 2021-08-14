package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobIssCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssCompDtService;

@Service
@Repository
@Transactional
public class JobIssCompDtService implements IJobIssCompDtService {

	@Autowired
	private IJobIssCompDtRepository jobIssCompDtRepository;
	
	@Override
	public void save(JobIssCompDt jobIssCompDt) {
		// TODO Auto-generated method stub
		jobIssCompDtRepository.save(jobIssCompDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobIssCompDt jobIssCompDt = jobIssCompDtRepository.findOne(id);
		jobIssCompDt.setDeactive(true);
		jobIssCompDt.setDeactiveDt(new Date());
		jobIssCompDtRepository.save(jobIssCompDt);
	}

	@Override
	public JobIssCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobIssCompDtRepository.findOne(id);
	}

	@Override
	public List<JobIssCompDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssCompDtRepository.findByJobIssMtAndDeactive(jobIssMt, deactive);
	}

	@Override
	public List<JobIssCompDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssCompDtRepository.findByJobIssDtAndDeactive(jobIssDt, deactive);
	}


}

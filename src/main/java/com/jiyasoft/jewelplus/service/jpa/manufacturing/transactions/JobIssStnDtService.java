package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobIssStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssStnDtService;

@Service
@Repository
@Transactional
public class JobIssStnDtService implements IJobIssStnDtService{

	@Autowired
	private IJobIssStnDtRepository jobIssStnDtRepository;
	
	@Override
	public void save(JobIssStnDt jobIssStnDt) {
		// TODO Auto-generated method stub
		jobIssStnDtRepository.save(jobIssStnDt);
	}

	@Override
	public void delete(int id) {
	
		JobIssStnDt jobIssStnDt = jobIssStnDtRepository.findOne(id);
		jobIssStnDt.setDeactive(true);
		jobIssStnDt.setDeactiveDt(new Date());
		jobIssStnDtRepository.save(jobIssStnDt);
	}

	@Override
	public JobIssStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobIssStnDtRepository.findOne(id);
	}

	@Override
	public List<JobIssStnDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssStnDtRepository.findByJobIssMtAndDeactive(jobIssMt, deactive);
	}

	@Override
	public List<JobIssStnDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssStnDtRepository.findByJobIssDtAndDeactive(jobIssDt, deactive);
	}


}

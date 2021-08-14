package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QJobRecMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobRecMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobCompRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobMtlRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobStnRecDtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
@Repository
public class JobRecMtService implements IJobRecMtService{
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IJobRecMtRepository jobRecMtRepository;
	
	@Autowired
	private IJobRecDtService jobRecDtService;
	
	@Autowired
	private IJobMtlRecDtService jobMtlRecDtService;
	
	@Autowired
	private IJobCompRecDtService jobCompRecDtService;
	
	@Autowired
	private IJobStnRecDtService jobStnRecDtService;

	@Override
	public Page<JobRecMt> findAll(Integer limit, Integer offset, String sort, String order, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(JobRecMt jobRecMt) {
		// TODO Auto-generated method stub
		jobRecMtRepository.save(jobRecMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobRecMt jobRecMt = jobRecMtRepository.findOne(id);
		jobRecMt.setDeactive(true);
		jobRecMt.setDeactiveDt(new java.util.Date());
		jobRecMtRepository.save(jobRecMt);
	}

	@Override
	public JobRecMt findOne(int id) {
		// TODO Auto-generated method stub
		return jobRecMtRepository.findOne(id);
	}

	@Override
	public JobRecMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Page<JobRecMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name) {
		// TODO Auto-generated method stub
		QJobRecMt qjobRecIssMt = QJobRecMt.jobRecMt;
		BooleanExpression expression = qjobRecIssMt.deactive.eq(false);

		if(name !=null && name !=""){
			expression = qjobRecIssMt.deactive.eq(false).and(qjobRecIssMt.invNo.like("%"+name+"%").or(qjobRecIssMt.party.partyCode.like("%"+name+"%")).or(qjobRecIssMt.department.name.like("%"+name+"%")));
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		

		 if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("invNo")) {
			sort = "invNo";
		}
		
		Page<JobRecMt> jobRecMtList = (Page<JobRecMt>) jobRecMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));

		return jobRecMtList;
	}

	@Override
	public JobRecMt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobRecMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Page<JobRecMt> getInvNoAutoFill(Integer limit, Integer offset, String sort, String order, String invNo,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getJobRecList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<JobRecMt> findActiveJobRecSortByInvDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String jobRecTransfer(String fromInvoice, String toInvoice, String dtids, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> partyWiseAndDateWiseListing(Integer limit, Integer offset, String sort, String order,
			String search, String partyIds, String fromDate, String toDate) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteJobRecMtInvoice(Integer jobRecMtId, Principal principal) {

		
		String retVal ="-1";
		JobRecMt jobRecMt = findOne(jobRecMtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = jobRecMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
		
		
//		List<JobRecDt>jobRecDts =jobRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
//		
//		Boolean deleteVal= false;
//		for(JobRecDt jobRecDt :jobRecDts) {
//			TranMt tranMt =tranMtService.findOne(jobRecDt.getTranMtId());
//			
//			
//			if(tranMt.getCurrStk().equals(true)) {
//			
//			}else {
//				deleteVal =true;
//				break;
//			}
//		}
//		
//		
//		if(deleteVal.equals(false)) {
//			for(JobRecDt jobRecDt :jobRecDts) {
//				jobRecDtService.deleteJobRecDt(jobRecDt.getId());
//			}
//			
//			
//		}else {
//			return  "Can Not Delete  Bag Not In TranMtId Location";
//		}
//		
//		List<JobMtlRecDt> jobMtlRecDts = jobMtlRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
//		for (JobMtlRecDt jobMtlRecDt : jobMtlRecDts) {
//			jobMtlRecDtService.jobMtlRecDtDelete(jobMtlRecDt.getId(), principal);
//		}
//		
//		List<JobCompRecDt> jobCompRecDts = jobCompRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
//		for (JobCompRecDt jobCompRecDt : jobCompRecDts) {
//			jobCompRecDtService.jobCompRecDtDelete(jobCompRecDt.getId(), principal);
//		}
//		
//		List<JobStnRecDt> jobStnRecDts = jobStnRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
//		for (JobStnRecDt jobStnRecDt : jobStnRecDts) {
//			jobStnRecDtService.jobStnRecDtDelete(jobStnRecDt.getId(), principal);
//		}
//		
//		
			
			
			String jobRecSizeArr ="";
			
			List<JobRecDt>jobRecDts =jobRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
			if(jobRecDts.size() > 0) {
				if(jobRecSizeArr.length() > 0) {
					jobRecSizeArr += " ,Jobwork Details";
				}else {
					jobRecSizeArr = " Jobwork Details";
				}
			}
			
			List<JobMtlRecDt> jobMtlRecDts = jobMtlRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
			if(jobMtlRecDts.size() > 0) {
				if(jobRecSizeArr.length() > 0) {
					jobRecSizeArr += " ,loose Metal Details";
				}else {
					jobRecSizeArr = " loose Metal Details";
				}
			}
			
			List<JobCompRecDt> jobCompRecDts = jobCompRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
			if(jobCompRecDts.size() > 0) {
				if(jobRecSizeArr.length() > 0) {
					jobRecSizeArr += " , loose Stone Details";
				}else {
					jobRecSizeArr = " loose Stone Details";
				}
			}
			
			
			List<JobStnRecDt> jobStnRecDts = jobStnRecDtService.findByJobRecMtAndDeactive(jobRecMt, false);
			if(jobStnRecDts.size() > 0) {
				if(jobRecSizeArr.length() > 0) {
					jobRecSizeArr += " ,loose Component Details";
				}else {
					jobRecSizeArr = " loose Component Details";
				}
			}
			
			
			if(jobRecSizeArr.length() > 0) {
				return jobRecSizeArr;
			}
			
			
		delete(jobRecMtId);
		
			retVal = "1";
		
		}else {
			return "-2";
		}
		
		return retVal;
	}

	@Override
	public String addBagInJobRec(String pOIds, Integer jobRecMtId, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMaxInvSrno() {
		QJobRecMt qJobRecMt = QJobRecMt.jobRecMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qJobRecMt)
			.where(qJobRecMt.deactive.eq(false).and(qJobRecMt.invDate.year().eq(date.get(Calendar.YEAR)))).list(qJobRecMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

}

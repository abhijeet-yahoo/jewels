package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobStnRecDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobStnRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;

@Service
@Repository
@Transactional
public class JobStnRecDtService implements IJobStnRecDtService{
	
	@Autowired
	private IJobStnRecDtRepository jobStnRecDtRepository;
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private EntityManager entityManager;
	

	@Autowired
	private IStoneChartService stoneChartService;
	@Override
	public List<JobStnRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobStnRecDtRepository.findByJobRecMtAndDeactive(jobRecMt, deactive);
	}

	@Override
	public JobStnRecDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobStnRecDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(JobStnRecDt jobStnRecDt) {
		// TODO Auto-generated method stub
		jobStnRecDtRepository.save(jobStnRecDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobStnRecDt jobStnRecDt = jobStnRecDtRepository.findOne(id);
		jobStnRecDt.setDeactive(true);
		jobStnRecDt.setDeactiveDt(new Date());
		jobStnRecDtRepository.save(jobStnRecDt);
	}

	@Override
	public JobStnRecDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobStnRecDtRepository.findOne(id);
	}

	@Override
	public String jobStnRecDtSave(JobStnRecDt jobStnRecDt, Integer id, Integer mtId, Principal principal) {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "-1";
		
		
		JobRecMt jobRecMt = jobRecMtService.findOne(mtId);
		
		
		  @SuppressWarnings("unchecked")
			TypedQuery<Object> query =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_jobworkDiamondstock(?,?,?,?,?) }");
			query.setParameter(1,jobRecMt.getParty().getId());
			query.setParameter(2,jobStnRecDt.getStoneType().getId());
			query.setParameter(3,jobStnRecDt.getShape().getId());
			query.setParameter(4,jobStnRecDt.getQuality().getId());
			query.setParameter(5,jobStnRecDt.getSize());
			
			
			List<Object> balCaratList = query.getResultList();
			
			Double balCarat = (Double) balCaratList.get(0);	
			
			if(balCarat==null) {
				balCarat=0.0;
			}
			
			
			
		
			if (jobStnRecDt.getCarat() <= balCarat) {
				
				
				@SuppressWarnings("unchecked")
				TypedQuery<Object> query2 =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_fifo_jobworkDiarate(?,?,?,?,?) }");
				query2.setParameter(1,jobRecMt.getParty().getId());
				query2.setParameter(2,jobStnRecDt.getStoneType().getId());
				query2.setParameter(3,jobStnRecDt.getShape().getId());
				query2.setParameter(4,jobStnRecDt.getQuality().getId());
				query2.setParameter(5,jobStnRecDt.getSize());
				
				List<Object> avgRateList = query2.getResultList();
				
				Double avgRate = (Double) avgRateList.get(0);
				
				
				StoneTran stoneTran=null;
				
				StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(jobStnRecDt.getShape(), jobStnRecDt.getSize(),false);
				if (id == null || id.equals("") || (id != null && id == 0)) {
					jobStnRecDt.setCreatedBy(principal.getName());
					jobStnRecDt.setCreatedDt(new java.util.Date());
				
					jobStnRecDt.setUniqueId(new Date().getTime());
					jobStnRecDt.setBalCarat(jobStnRecDt.getCarat());
					jobStnRecDt.setBalStone(jobStnRecDt.getStone());
					stoneTran = new StoneTran();
					retVal = "1";
					
				} else {
					
					if(jobStnRecDt.getBalCarat() > jobStnRecDt.getCarat()){
						return retVal = "-11";
					}
					
					
					jobStnRecDt.setModiBy(principal.getName());
					jobStnRecDt.setModiDt(new java.util.Date());
					jobStnRecDt.setBalCarat(jobStnRecDt.getCarat());
					jobStnRecDt.setBalStone(jobStnRecDt.getStone());
					jobStnRecDt.setDepartment(jobStnRecDt.getDepartment());
					stoneTran=stoneTranService.RefTranIdAndTranType(id, "JobStnRec");
					action = "updated";
					retVal ="2";
					
				}
				
			
				
				if (jobStnRecDt.getSubShape().getId() == null) {
					jobStnRecDt.setSubShape(null);
				}
				
				jobStnRecDt.setJobRecMt(jobRecMt);
				jobStnRecDt.setSieve(stoneChart.getSieve());
				jobStnRecDt.setSizeGroup(stoneChart.getSizeGroup());
				
				jobStnRecDt.setRate(avgRate);
				
				save(jobStnRecDt);
				
				
				
				
				if(action == "added"){
					
					stoneTran.setCreatedBy(jobStnRecDt.getCreatedBy());
					stoneTran.setCreatedDt(new Date());
				}else{
					
					stoneTran.setModiBy(principal.getName());
					stoneTran.setModiDt(new java.util.Date());
				}
				
				if(stoneTran != null){
				
					stoneTran.setTranDate(jobRecMt.getInvDate()); 
					stoneTran.setDepartment(null);
					stoneTran.setDeptLocation(jobStnRecDt.getDepartment());
					stoneTran.setStoneType(jobStnRecDt.getStoneType());
					stoneTran.setShape(jobStnRecDt.getShape());
					stoneTran.setQuality(jobStnRecDt.getQuality());
					stoneTran.setSubShape(jobStnRecDt.getSubShape());
					stoneTran.setSize(jobStnRecDt.getSize());
					stoneTran.setSieve(jobStnRecDt.getSieve());
					stoneTran.setSizeGroup(jobStnRecDt.getSizeGroup());
					stoneTran.setStone(jobStnRecDt.getStone());
					stoneTran.setCarat(jobStnRecDt.getCarat());		//-------------
					stoneTran.setBalStone(jobStnRecDt.getBalStone());
					stoneTran.setBalCarat(jobStnRecDt.getCarat()); //--------------
					stoneTran.setRate(avgRate);
					stoneTran.setInOutFld("C");
					stoneTran.setBagMt(null);
					stoneTran.setBagSrNo(0);
					stoneTran.setSettingType(null);
					stoneTran.setSetting(null);
					stoneTran.setPacketNo(jobStnRecDt.getPacketNo());
					stoneTran.setRemark(jobStnRecDt.getRemark());
					stoneTran.setParty(jobRecMt.getParty());
					stoneTran.setTranType("JobStnRec");
					stoneTran.setRefTranId(jobStnRecDt.getId());
										
					stoneTranService.save(stoneTran);
				
				}
				
			}else {
				retVal="Stock Not sufficient ,Available Stock Carat ="+balCarat;
			}
			
		return retVal;
		
	}

	@Override
	public String jobStnRecDtDelete(Integer id, Principal principal) {
	String retVal ="-1";
		
		try {
				delete(id);
				StoneTran stoneTran=stoneTranService.RefTranIdAndTranType(id, "JobStnRec");
				stoneTranService.delete(stoneTran.getId());
			
				retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

}

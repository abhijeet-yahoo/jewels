package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobStnIssDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobStnIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class jobStnIssDtService implements IJobStnIssDtService{
	
	@Autowired
	private IJobStnIssDtRepository jobStnIssDtRepository;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStoneTranService stoneTranService;
	
	@Autowired
	private IJobIssMtService jobIssMtService;
	
	@Autowired
	private IStoneChartService stoneChartService;

	@Override
	public List<JobStnIssDt> findByJobIssMt(JobIssMt jobIssMt) {
		// TODO Auto-generated method stub
		return jobStnIssDtRepository.findByJobIssMt(jobIssMt);
	}

	@Override
	public List<JobStnIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobStnIssDtRepository.findByJobIssMtAndDeactive(jobIssMt, deactive);
	}

	@Override
	public JobStnIssDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobStnIssDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public void save(JobStnIssDt jobStnIssDt) {
		// TODO Auto-generated method stub
		jobStnIssDtRepository.save(jobStnIssDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobStnIssDt jobStnIssDt = jobStnIssDtRepository.findOne(id);
		jobStnIssDt.setDeactive(true);
		jobStnIssDt.setDeactiveDt(new Date());
		jobStnIssDtRepository.save(jobStnIssDt);
	}

	@Override
	public String saveJobStnIssDt(JobStnIssDt jobStnIssDt, Integer id, Integer mtId, Principal principal,String stockType,Boolean allowNegative) {
		// TODO Auto-generated method stub
		String action = "added";
		String retVal = "-1";
		JobIssMt jobIssMt = jobIssMtService.findOne(mtId);
		
		StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(jobStnIssDt.getShape(), jobStnIssDt.getSize(),false);
		/*if (id == null || id.equals("") || (id != null && id == 0)) {*/
			
			if(!allowNegative.equals(true)){
				
				Double stockBal = stoneTranService.getStockBalance(jobStnIssDt.getDepartment().getId(),jobStnIssDt.getStoneType().getId(),jobStnIssDt.getShape().getId(), 
						jobStnIssDt.getQuality().getId(),stoneChart.getSizeGroup().getId(),jobStnIssDt.getSieve(),stockType);
				
				if(stockBal <jobStnIssDt.getCarat()){
					return "Error : Stock Not Available (Available In Stock : "+stockBal+")";
				}
				
			}
			jobStnIssDt.setBalCarat(jobStnIssDt.getCarat());
			jobStnIssDt.setBalStone(jobStnIssDt.getStone());
			jobStnIssDt.setCreatedBy(principal.getName());
			jobStnIssDt.setCreatedDt(new java.util.Date());
			jobStnIssDt.setUniqueId(new Date().getTime());
			jobStnIssDt.setDepartment(jobStnIssDt.getDepartment());
			
			
			
	
		
		if (jobStnIssDt.getSubShape().getId() == null) {
			jobStnIssDt.setSubShape(null);
		}
		
		jobStnIssDt.setJobIssMt(jobIssMt);
		jobStnIssDt.setSieve(stoneChart.getSieve());
		jobStnIssDt.setSizeGroup(stoneChart.getSizeGroup());
		
		save(jobStnIssDt);
		
		
		Double adjCarat = jobStnIssDt.getCarat();
		Integer adjStone= jobStnIssDt.getStone();
		Department location =departmentService.findOne(jobStnIssDt.getDepartment().getId());
		Shape shape =shapeService.findOne(jobStnIssDt.getShape().getId());
		StoneType stoneType=stoneTypeService.findOne(jobStnIssDt.getStoneType().getId());
		Quality quality =qualityService.findOne(jobStnIssDt.getQuality().getId());
		String sizeNm=jobStnIssDt.getSize();
		
		if(adjCarat>0) {
			
			List<StoneTran>stoneTrans =stoneTranService.getDiamondStockForAllocation(location.getId(),stoneType.getName(),
					shape.getName(), quality.getName(), sizeNm, jobStnIssDt.getSieve());
			
			
			Double valSum=0.0;
			Double caratSum=0.0;

			
			 for (StoneTran stoneTranBal : stoneTrans) {
					if(adjCarat>0) {
						
						if(stoneTranBal.getBalCarat()>= adjCarat) {
													
							StoneTran stoneTran = new StoneTran();
							stoneTran.setCreatedBy(jobStnIssDt.getCreatedBy());
							stoneTran.setCreatedDt(jobStnIssDt.getCreatedDt());
							stoneTran.setLotNo(stoneTranBal.getLotNo());

							
							stoneTran.setTranDate(jobIssMt.getInvDate()); 
							stoneTran.setDepartment(null);
							stoneTran.setDeptLocation(location);
							stoneTran.setStoneType(stoneTranBal.getStoneType());
							stoneTran.setShape(stoneTranBal.getShape());
							stoneTran.setQuality(stoneTranBal.getQuality());
							stoneTran.setSize(stoneTranBal.getSize());
							stoneTran.setSieve(stoneTranBal.getSieve());
							stoneTran.setSizeGroup(stoneTranBal.getSizeGroup());
							stoneTran.setStone(adjStone);
							stoneTran.setCarat(adjCarat);		
							stoneTran.setBalStone(adjStone*-1);
							stoneTran.setBalCarat(adjCarat*-1); 
							stoneTran.setRate(stoneTranBal.getRate());
							stoneTran.setInOutFld("D"); 
							stoneTran.setBagMt(null);
							stoneTran.setBagSrNo(0);
							stoneTran.setSettingType(null);
							stoneTran.setSetting(null);
							stoneTran.setPacketNo(jobStnIssDt.getPacketNo());
							stoneTran.setRemark(jobStnIssDt.getRemark());
							stoneTran.setParty(jobIssMt.getParty());
							stoneTran.setTranType("JobStnIss");
							stoneTran.setRefTranId(jobStnIssDt.getId());
							stoneTran.setAvgRate(stoneTranBal.getRate());
							stoneTran.setTransferRate((double)Math.round((stoneTran.getAvgRate()+(stoneTran.getAvgRate()*stoneTranBal.getStoneType().getTransferRatePerc()/100))*100)/100);
							stoneTran.setFactoryRate((double)Math.round((stoneTran.getTransferRate()+(stoneTran.getTransferRate()*stoneTranBal.getStoneType().getFactoryRatePerc()/100))*100)/100);
							
							
							stoneTranService.save(stoneTran);
							
							 valSum += Math.round((adjCarat*stoneTranBal.getRate())*100.0)/100.0;
							  caratSum += Math.round(adjCarat*1000.0)/1000.0;
							
							break;
						}else {
							
							adjCarat =(double) Math.round((adjCarat-stoneTranBal.getBalCarat())*1000)/1000;
							adjStone = Math.round(adjStone-stoneTranBal.getBalStone());
							
							StoneTran stoneTranDiaLoc = new StoneTran();
							stoneTranDiaLoc.setAvgRate(stoneTranBal.getRate());
							stoneTranDiaLoc.setLotNo(stoneTranBal.getLotNo());
							stoneTranDiaLoc.setTranDate(jobIssMt.getInvDate());  
							stoneTranDiaLoc.setDepartment(null);
							stoneTranDiaLoc.setDeptLocation(location);
							stoneTranDiaLoc.setStoneType(stoneTranBal.getStoneType());
							stoneTranDiaLoc.setShape(stoneTranBal.getShape());
							stoneTranDiaLoc.setQuality(stoneTranBal.getQuality());
							stoneTranDiaLoc.setSize(stoneTranBal.getSize());
							stoneTranDiaLoc.setSieve(stoneTranBal.getSieve());
							stoneTranDiaLoc.setSizeGroup(stoneTranBal.getSizeGroup());
							stoneTranDiaLoc.setStone(stoneTranBal.getBalStone());
							stoneTranDiaLoc.setCarat(stoneTranBal.getBalCarat());
							stoneTranDiaLoc.setBalStone(stoneTranBal.getBalStone()*-1);
							stoneTranDiaLoc.setBalCarat(stoneTranBal.getBalCarat()*-1); 
							stoneTranDiaLoc.setRate(stoneTranBal.getRate());
							stoneTranDiaLoc.setInOutFld("D");
							stoneTranDiaLoc.setBagMt(null);;
							stoneTranDiaLoc.setBagSrNo(0);
							stoneTranDiaLoc.setSettingType(null);
							stoneTranDiaLoc.setSetting(null);
							stoneTranDiaLoc.setPacketNo(jobStnIssDt.getPacketNo());
							stoneTranDiaLoc.setRemark(jobStnIssDt.getRemark());
							stoneTranDiaLoc.setParty(jobIssMt.getParty());
							stoneTranDiaLoc.setTranType("JobStnIss");
							stoneTranDiaLoc.setCreatedBy(jobStnIssDt.getCreatedBy());
							stoneTranDiaLoc.setCreatedDt(jobStnIssDt.getCreatedDt());
							stoneTranDiaLoc.setRefTranId(jobStnIssDt.getId());
							stoneTranDiaLoc.setTransferRate((double)Math.round((stoneTranDiaLoc.getAvgRate()+(stoneTranDiaLoc.getAvgRate()*stoneTranBal.getStoneType().getTransferRatePerc()/100))*100)/100);
							stoneTranDiaLoc.setFactoryRate((double)Math.round((stoneTranDiaLoc.getTransferRate()+(stoneTranDiaLoc.getTransferRate()*stoneTranBal.getStoneType().getFactoryRatePerc()/100))*100)/100);
							
							
							stoneTranService.save(stoneTranDiaLoc);
							
							
							
							 valSum += Math.round((stoneTranBal.getBalCarat()*stoneTranBal.getRate())*100.0)/100.0;
							  caratSum += Math.round(stoneTranBal.getBalCarat()*1000.0)/1000.0;
						
						
							
						}
						
						
						
						
					
						
					}
					
					
				}
			 
			 Double avgRate= Math.round((valSum/caratSum)*100.0)/100.0;
				jobStnIssDt.setStnRate(avgRate);
			 
			 jobStnIssDt.setAvgRate(jobStnIssDt.getStnRate());
			 jobStnIssDt.setTransferRate((double)Math.round((jobStnIssDt.getAvgRate()+(jobStnIssDt.getAvgRate()*stoneType.getTransferRatePerc()/100))*100)/100);
			 jobStnIssDt.setFactoryRate((double)Math.round((jobStnIssDt.getTransferRate()+(jobStnIssDt.getTransferRate()*stoneType.getFactoryRatePerc()/100))*100)/100);
			 save(jobStnIssDt);
				
			
			
		}
		
		
		
		
		
		
		retVal ="1";
		
		
		
		
		return retVal;
	}

	@Override
	public String jobStnIssDtDelete(Integer id, Principal principal) {
		// TODO Auto-generated method stub
	String retVal ="-1";
		
		try {
				delete(id);
				
				List<StoneTran>stoneTrans =stoneTranService.findByRefTranIdAndTranType(id, "JobStnIss");
				for(StoneTran stoneTran :stoneTrans) {
					stoneTranService.delete(stoneTran.getId());	
				}
				
			
				retVal = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public JobStnIssDt findOne(Integer id) {
		// TODO Auto-generated method stub
		return jobStnIssDtRepository.findOne(id);
	}

}

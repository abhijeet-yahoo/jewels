package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QJobRecCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QJobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobRecDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobRecStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class JobRecDtService implements IJobRecDtService {
	
	@Autowired
	private IJobRecDtRepository jobRecDtRepository;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IJobRecMtService jobRecMtService;
	
	@Autowired
	private IJobIssDtService jobIssDtService ;
	
	@Autowired
	private IJobIssMetalDtService jobIssMetalDtService;
	
	@Autowired
	private IJobIssStnDtService jobIssStnDtService;
	
	@Autowired
	private IJobIssCompDtService jobIssCompDtService;
	
	@Autowired
	private IJobRecLabDtService jobRecLabDtService;
	
	@Autowired
	private IJobRecMetalDtService jobRecMetalDtService;
	
	@Autowired
	private IJobRecStnDtService jobRecStnDtService;
	
	@Autowired
	private IJobRecCompDtService jobRecCompDtService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Override
	public void save(JobRecDt jobRecDt) {
		// TODO Auto-generated method stub
		jobRecDtRepository.save(jobRecDt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		JobRecDt jobRecDt = jobRecDtRepository.findOne(id);
		jobRecDt.setDeactive(true);
		jobRecDt.setDeactiveDt(new Date());
		jobRecDtRepository.save(jobRecDt);
	}

	@Override
	public JobRecDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobRecDtRepository.findOne(id);
	}

	@Override
	public JobRecDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobRecDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<JobRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecDtRepository.findByJobRecMtAndDeactive(jobRecMt, deactive);
	}

	@Override
	public String calculateFinalPrice(String finalPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobRecDt> findByOrderDtAndJobRecMtAndDeactive(OrderDt orderDt, JobRecMt jobRecMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobRecDtRepository.findByOrderDtAndJobRecMtAndDeactive(orderDt, jobRecMt, deactive);
	}

	@Override
	public List<JobRecDt> getJobRecDtList(JobRecMt jobRecMt) {
		// TODO Auto-generated method stub
		
		QJobRecDt qJobRecDt = QJobRecDt.jobRecDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<JobRecDt> jobRecDtList = null;
		
		jobRecDtList = query.from(qJobRecDt)
					.where(qJobRecDt.jobRecMt.id.eq(jobRecMt.getId()).and(qJobRecDt.deactive.eq(false)))
					.groupBy(qJobRecDt.jobRecMt,qJobRecDt.itemNo,qJobRecDt.orderDt)
					.list(qJobRecDt);
		
		return jobRecDtList;
	}

	@Override
	public Page<JobRecDt> findByItemNo(Integer limit, Integer offset, String sort, String order, String itemNo,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobRecDt> findByItemNoAndDeactive(String itemNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lockUnlockDt(Integer jobRecMtId, Boolean status) {
		// TODO Auto-generated method stub

		QJobRecDt qJobRecDt = QJobRecDt.jobRecDt;
		
		new JPAUpdateClause(entityManager, qJobRecDt).where(qJobRecDt.jobRecMt.id.eq(jobRecMtId))
		.set(qJobRecDt.rLock, status)
		.execute();
	}

	@Override
	public Page<JobRecDt> searchAll(Integer limit, Integer offset, String sort, String order, String name,
			Integer mtId) {
		// TODO Auto-generated method stub
		QJobRecDt qJobRecDt = QJobRecDt.jobRecDt;
		BooleanExpression expression = qJobRecDt.deactive.eq(false).and(qJobRecDt.jobRecMt.id.eq(mtId));

		if(name !=null && name !=""){
			
			expression = qJobRecDt.deactive.eq(false).and(qJobRecDt.jobRecMt.id.eq(mtId)).and(qJobRecDt.bagMt.name.like(name.trim()+"%")
					.or(qJobRecDt.design.mainStyleNo.like(name+"%")).or(qJobRecDt.party.name.like(name+"%")).or(qJobRecDt.orderDt.orderMt.invNo.like(name+"%"))
					.or(qJobRecDt.orderDt.orderMt.refNo.like(name+"%")));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if(sort.equalsIgnoreCase("style")){
			sort = "design";
		}

		Page<JobRecDt> jobRecDtList = (Page<JobRecDt>) jobRecDtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return jobRecDtList;
	}

	@Override
	public String jobRecDtSave(Integer jobRecDtId, Double grossWt, Double other, Integer partyId, String itemNo,
			Double dispPercentDt, Double lossPercDt, Principal principal) {
		// TODO Auto-generated method stub
	String retVal = "-1";

		try {
			Party jobDtParty = partyService.findOne(partyId);
			JobRecDt jobRecDt = findOne(jobRecDtId);
			
			
			BagMt bagMt = bagMtService.findOne(jobRecDt.getBagMt().getId());
			
			if(itemNo.equalsIgnoreCase("NO TAG")){
				
			}else{
			BagMt bagMtValidate = bagMtService.findByItemNoAndDeactive(itemNo, false);
			
					
			if(bagMtValidate != null){
				if(bagMtValidate.getId().equals(bagMt.getId())){
				}else{
					return retVal = "-2";
				}
			}
			}
			

			
			Double vGrossWtDiff=jobRecDt.getGrossWt()-grossWt;
			
			
			jobRecDt.setGrossWt(grossWt);
			jobRecDt.setNetWt(jobRecDt.getNetWt()-vGrossWtDiff);
			jobRecDt.setOther(other);
			jobRecDt.setLossPercDt(lossPercDt);
			jobRecDt.setModiBy(principal.getName());
			jobRecDt.setModiDate(new java.util.Date());
			jobRecDt.setParty(jobDtParty);
			jobRecDt.setItemNo(itemNo);
			jobRecDt.setDispPercentDt(dispPercentDt);
			save(jobRecDt);
			
			
			bagMt.setItemNo(itemNo);
			bagMtService.save(bagMt);
			
	
			
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				return retVal;
	}

	@Override
	public Integer getMaxSetNo(Integer mtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tuple> getSetNoList(Integer jobRecMtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lockDtInvoice(Integer jobRecMtId, Integer setNo, Principal principal, Boolean vRLock) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getMaxSetNoByOrder(Integer mtId, Integer sordDtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addBagInJobRecDt(Integer mtId, String data,Principal principal) {
		// TODO Auto-generated method stub
	String retVal = "-1";
	
	JobRecMt jobRecMt = jobRecMtService.findOne(mtId); 
	
	JSONArray jsonBagDt = new JSONArray(data);
	
	for (int y = 0; y < jsonBagDt.length(); y++) {
		
		
		JSONObject dataJobIssDt = (JSONObject) jsonBagDt.get(y);
		
		JobIssDt jobIssDt = jobIssDtService.findOne(Integer.parseInt((dataJobIssDt.get("dtId").toString())));
		
		JobRecDt jobRecDt = new JobRecDt();
		jobRecDt.setJobRecMt(jobRecMt);
		jobRecDt.setDesign(jobIssDt.getDesign());
		jobRecDt.setBagMt(jobIssDt.getBagMt());
		
		jobRecDt.setParty(jobRecMt.getParty());
		jobRecDt.setPurity(jobIssDt.getPurity());
		jobRecDt.setProcess(jobIssDt.getProcess());
		jobRecDt.setColor(jobIssDt.getColor());
		jobRecDt.setProcess(jobIssDt.getProcess());
		jobRecDt.setOrderDt(jobIssDt.getOrderDt());
		jobRecDt.setDepartment(jobIssDt.getDepartment());
		jobRecDt.setGrossWt(jobIssDt.getGrossWt());
		jobRecDt.setPcs(jobIssDt.getPcs());
		jobRecDt.setCreatedBy(principal.getName());
		jobRecDt.setCreatedDate(new java.util.Date());
		jobRecDt.setItemNo(jobIssDt.getItemNo());
		jobRecDt.setRefJobIssDtId(jobIssDt.getId());
		jobRecDt.setRefJobIssMtId(jobIssDt.getJobIssMt().getId());
		jobRecDt.setTranMtId(jobIssDt.getTranMtId());
		jobRecDt.setMetalValue(jobIssDt.getMetalValue());
		jobRecDt.setStoneValue(jobIssDt.getStoneValue());
		jobRecDt.setFob(jobIssDt.getFob());
		jobRecDt.setFinalPrice(jobIssDt.getFinalPrice());
		
		save(jobRecDt); // End Of Save dt 
		
		
		//Dump Value In BagMt
		BagMt bagMt = bagMtService.findOne(jobRecDt.getBagMt().getId());
		bagMt.setJobWorkFlg(false);
		bagMt.setModiBy(principal.getName());
		bagMt.setModiDate(new Date());
		bagMtService.save(bagMt);
		
		
		
		
		List<JobIssStnDt> jobIssStnDts = jobIssStnDtService.findByJobIssDtAndDeactive(jobIssDt, false);
		
		Double totCarat=0.0;
	
		if(jobIssStnDts != null){
		for(JobIssStnDt jobIssStnDt : jobIssStnDts){
		
			JobRecStnDt jobRecStnDt = new JobRecStnDt();
			jobRecStnDt.setStoneType(jobIssStnDt.getStoneType());
			jobRecStnDt.setJobRecDt(jobRecDt);
			jobRecStnDt.setJobRecMt(jobRecMt);
			jobRecStnDt.setShape(jobIssStnDt.getShape());
			jobRecStnDt.setBagMt(bagMt);
			jobRecStnDt.setQuality(jobIssStnDt.getQuality());
			
			jobRecStnDt.setSizeGroup(jobIssStnDt.getSizeGroup());
			
				jobRecStnDt.setSetting(jobIssStnDt.getSetting());
				jobRecStnDt.setSettingType(jobIssStnDt.getSettingType());
				jobRecStnDt.setOrderDt(jobIssStnDt.getOrderDt());
				jobRecStnDt.setBagSrNo(jobIssStnDt.getBagSrNo());
				jobRecStnDt.setSize(jobIssStnDt.getSize());
				jobRecStnDt.setSieve(jobIssStnDt.getSieve());
				jobRecStnDt.setStone(jobIssStnDt.getStone());
				jobRecStnDt.setCarat(jobIssStnDt.getCarat());
				jobRecStnDt.setPartNm(jobIssStnDt.getPartNm());
				jobRecStnDt.setCreatedBy(principal.getName());
				jobRecStnDt.setCreatedDate(new java.util.Date());
				jobRecStnDt.setCenterStone(jobIssStnDt.getCenterStone() !=null? jobIssStnDt.getCenterStone() :false);
				jobRecStnDt.setStnRate(jobIssStnDt.getStnRate());
				jobRecStnDt.setStoneValue(jobIssStnDt.getStoneValue());
				
				jobRecStnDtService.save(jobRecStnDt);
				
				
			totCarat  +=jobIssStnDt.getCarat();
			
		} //ending jobRecStnDt for loop
		}
		
		List<JobIssMetalDt> jobIssMetalDtLists = jobIssMetalDtService.findByJobIssDtAndDeactive(jobIssDt, false);
		
		if(jobIssMetalDtLists.size() > 0){
		
		for(JobIssMetalDt jobIssMetalDt:jobIssMetalDtLists ) {
			
			JobRecMetalDt jobRecMetalDt = new JobRecMetalDt();
			
			jobRecMetalDt.setJobRecDt(jobRecDt);
			jobRecMetalDt.setJobRecMt(jobRecMt);
			jobRecMetalDt.setBagMt(jobIssMetalDt.getBagMt());
			jobRecMetalDt.setPartNm(jobIssMetalDt.getPartNm());
			jobRecMetalDt.setPurity(jobIssMetalDt.getPurity());
			jobRecMetalDt.setDepartment(jobIssMetalDt.getDepartment());
			jobRecMetalDt.setColor(jobIssMetalDt.getColor());
			jobRecMetalDt.setMetalWeight(jobIssMetalDt.getMetalWeight());
			jobRecMetalDt.setMetalPcs(jobIssMetalDt.getMetalPcs());
			jobRecMetalDt.setCreateDate(new Date());
			jobRecMetalDt.setCreatedBy(principal.getName());
			jobRecMetalDt.setMainMetal(jobIssMetalDt.getMainMetal());		
			jobRecMetalDt.setMetalRate(jobIssMetalDt.getMetalRate());
			jobRecMetalDt.setPerGramRate(jobIssMetalDt.getPerGramRate());
			jobRecMetalDt.setMetalValue(jobIssMetalDt.getMetalValue());
			
			jobRecMetalDtService.save(jobRecMetalDt);
			
		}
		}
		
		List<JobIssCompDt> jobIssCompDts = jobIssCompDtService.findByJobIssDtAndDeactive(jobIssDt, false);
		Double totComWt = 0.0;
		
		if(jobIssCompDts != null){
		for (JobIssCompDt jobIssCompDt : jobIssCompDts) {
		
			JobRecCompDt jobRecCompDt = new JobRecCompDt();
				jobRecCompDt.setJobRecDt(jobRecDt);
				jobRecCompDt.setJobRecMt(jobRecMt);
				jobRecCompDt.setComponent(jobIssCompDt.getComponent());
				jobRecCompDt.setPurity(jobIssCompDt.getPurity());
				jobRecCompDt.setColor(jobIssCompDt.getColor());
				jobRecCompDt.setOrderDt(jobIssCompDt.getOrderDt());
				jobRecCompDt.setBagMt(jobIssCompDt.getBagMt());
				
				jobRecCompDt.setMetalWt(Math.round(jobIssCompDt.getMetalWt()*1000.0)/1000.0);
				jobRecCompDt.setPurityConv(jobIssCompDt.getPurityConv());
				jobRecCompDt.setCreatedBy(principal.getName());
				jobRecCompDt.setCreatedDate(new java.util.Date());
				jobRecCompDt.setCompPcs(jobIssCompDt.getCompPcs());
			
				jobRecCompDtService.save(jobRecCompDt);
				
				totComWt += jobIssCompDt.getMetalWt();
		
			
		} //ending the job Rec CompDt for loop
		
		}
		totCarat=totCarat/5;
		
		
		
		jobRecDt.setNetWt(Math.round((jobRecDt.getGrossWt()-totCarat)*1000.0)/1000.0);
		save(jobRecDt);
		
		TranMt tranMt =tranMtService.findOne(jobRecDt.getTranMtId());
		tranMt.setHoldFlg(false);
		tranMtService.save(tranMt);
		
		Query query = entityManager.createNativeQuery("update jobIssDt set adjustedQty=adjustedQty+"+jobIssDt.getPcs()+" where DtId="+jobIssDt.getId()+" and Deactive=0");
		query.executeUpdate();
		
		retVal ="1";
		
		
	}
			
		return retVal;
	}

	@Override
	public String deleteJobRecDt(Integer dtId) {
		
		JobRecDt jobRecDt = findOne(dtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = jobRecDt.getJobRecMt().getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
			
		
		TranMt tranMt =tranMtService.findOne(jobRecDt.getTranMtId());
		
		if(tranMt.getCurrStk().equals(true)) {
			
			List<JobRecMetalDt>jobRecMetalDts = jobRecMetalDtService.findByJobRecDtAndDeactive(jobRecDt, false);
			for(JobRecMetalDt jobRecMetalDt : jobRecMetalDts) {
				jobRecMetalDtService.delete(jobRecMetalDt.getId());
			}
			
			List<JobRecStnDt> jobRecStnDts = jobRecStnDtService.findByJobRecDtAndDeactive(jobRecDt, false);
			for(JobRecStnDt jobRecStnDt :jobRecStnDts) {
				
				if(jobRecStnDt.getManualFlg()) {
					TranDt tranDt = tranDtService.findByRefTranTypeAndRefTranDtIdAndCurrStk("Jobwork", jobRecStnDt.getId(),true);
					
					if(tranDt !=null) {
					
					Double caratWt=Math.round((tranDt.getCarat()/5)*1000.0)/1000.0;
					
					
					List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranDt.getTranMt(), true);
					TranMetal tranMetal = tranMetals.get(0);
					if(tranMetal.getMetalWeight()>0) {
						tranMetal.setMetalWeight(Math.round((tranMetal.getMetalWeight()+caratWt)*1000.0)/1000.0);
						tranMetalService.save(tranMetal);
					}
					
					tranDtService.delete(tranDt.getId());
				}
				}
				
				
				jobRecStnDtService.delete(jobRecStnDt.getId());
			}
			
			List<JobRecCompDt>jobRecCompDts =jobRecCompDtService.findByJobRecDtAndDeactive(jobRecDt, false);
			
			for(JobRecCompDt jobRecCompDt :jobRecCompDts) {
				jobRecCompDtService.delete(jobRecCompDt.getId());
			}
			
			List<JobRecLabDt>jobRecLabDts =jobRecLabDtService.findByJobRecDtAndDeactive(jobRecDt, false);
			for(JobRecLabDt jobRecLabDt :jobRecLabDts) {
				jobRecLabDtService.delete(jobRecLabDt.getId());
			}
			
			
			delete(dtId);
			
			tranMt.setHoldFlg(true);
			tranMtService.save(tranMt);
			
			
			Query query = entityManager.createNativeQuery("update jobIssDt set adjustedQty=adjustedQty-"+jobRecDt.getPcs()+" where DtId="+jobRecDt.getRefJobIssDtId()+" and Deactive=0");
			query.executeUpdate();
			
			
		}else {
			
			return "Can Not Delete, Bag Not In Same TranMtId Location";
		}
		 
		}else {
			return "Can Not Delete BackDate Entry";
		}
		
		
		return "1";
	}

	@Override
	public String updateDtGrossWtAndMetalDetails(Principal principal, Integer dtId, Double grossWt,Boolean netWtWithComp) {
		
		String retVal="Error";

		JobRecDt jobRecDt = findOne(dtId);
		JobRecMt jobRecMt = jobRecDt.getJobRecMt();
		
		JobIssDt jobIssDt = jobIssDtService.findOne(jobRecDt.getRefJobIssDtId());
		if(jobIssDt.getGrossWt() >0) {
			
			if(jobIssDt.getGrossWt()>grossWt) {
				List<JobRecStnDt>jobRecStnDts=jobRecStnDtService.findByJobRecDtAndDeactive(jobRecDt, false);
				Double vCts=0.0;
				
				for(JobRecStnDt jobRecStnDt:jobRecStnDts){
				
					vCts +=jobRecStnDt.getCarat();
				}
				
				List<JobRecCompDt>jobRecCompDts = jobRecCompDtService.findByJobRecDtAndDeactive(jobRecDt, false);
				Double vCompWt=0.0;
				for(JobRecCompDt jobRecCompDt :jobRecCompDts) {
					vCompWt +=jobRecCompDt.getMetalWt();
				}
				
				

				vCts= Math.round((vCts/5)*1000.0)/1000.0;
				
				
				Double netWt=0.0;
				
				Double vMetalWt=Math.round(((grossWt)-vCts-vCompWt)*1000.0)/1000.0;
				
				
				if(netWtWithComp.equals(true)){
					
					netWt = Math.round(((grossWt)-vCts)*1000.0)/1000.0;
				}else {
					netWt = Math.round(((grossWt)-vCts-vCompWt)*1000.0)/1000.0;
				}
				
				jobRecDt.setGrossWt(Math.round(grossWt*1000.0)/1000.0);
				jobRecDt.setNetWt(netWt);
				jobRecDt.setMetalRate(jobIssDt.getMetalRate());
				save(jobRecDt);
				
				
				JobRecMetalDt jobRecMetalDt =jobRecMetalDtService.findByJobRecDtAndDeactiveAndMainMetal(jobRecDt, false, true);
				if(jobRecMetalDt !=null) {
					jobRecMetalDt.setColor(jobRecDt.getColor());
					jobRecMetalDt.setMetalWeight(vMetalWt);
					jobRecMetalDt.setPurity(jobRecDt.getPurity());
					jobRecMetalDt.setMetalRate(jobIssDt.getMetalRate());
					jobRecMetalDtService.save(jobRecMetalDt);
				}else {
					JobRecMetalDt jobRecMetalDtNew = new JobRecMetalDt();
					jobRecMetalDtNew.setBagMt(jobRecDt.getBagMt());
					jobRecMetalDtNew.setColor(jobRecDt.getColor());
					jobRecMetalDtNew.setCreateDate(new Date());
					jobRecMetalDtNew.setCreatedBy(principal.getName());
					jobRecMetalDtNew.setDepartment(jobRecDt.getDepartment());
					jobRecMetalDtNew.setJobRecDt(jobRecDt);
					jobRecMetalDtNew.setJobRecMt(jobRecDt.getJobRecMt());
					jobRecMetalDtNew.setMainMetal(true);
					jobRecMetalDtNew.setMetalPcs(1);
					jobRecMetalDtNew.setMetalWeight(vMetalWt);
					jobRecMetalDtNew.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part",false));
					jobRecMetalDtNew.setPurity(jobRecDt.getPurity());
					jobRecMetalDtNew.setMetalRate(jobIssDt.getMetalRate());
					jobRecMetalDtService.save(jobRecMetalDtNew);
				}
				
				
				TranMt tranMt = tranMtService.findByBagMtCurrStk(jobRecDt.getBagMt(), true);
				if(tranMt.getDepartment().getName().equalsIgnoreCase("Job Work")) {
					tranMt.setRecWt(jobRecDt.getGrossWt());
					tranMtService.save(tranMt);
					
					List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMt, true);
					TranMetal tranMetal = tranMetals.get(0);
					tranMetal.setMetalWeight(vMetalWt);
					tranMetalService.save(tranMetal);
					
					
				}
				
				retVal="1";
				
			}else {
				retVal="Weight Is Greater Than Issue Wt Not Allow";
			}
			
		
			
			
		}else {
			
			Purity purity =purityService.findOne(jobRecDt.getPurity().getId());
			
			 @SuppressWarnings("unchecked")
				TypedQuery<Object> query =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_jobworkmetalstock(?,?) }");
				query.setParameter(1,jobRecMt.getParty().getId());
				query.setParameter(2,purity.getMetal().getId());
				
				
				
				List<Object> balPureList = query.getResultList();
				
				Double balPure = (Double) balPureList.get(0);
				
				
				List<JobRecStnDt>jobRecStnDts=jobRecStnDtService.findByJobRecDtAndDeactive(jobRecDt, false);
				Double vCts=0.0;
				
				for(JobRecStnDt jobRecStnDt:jobRecStnDts){
				
					vCts +=jobRecStnDt.getCarat();
				}

				vCts= Math.round((vCts/5)*1000.0)/1000.0;
				
				
				List<JobRecCompDt>jobRecCompDts = jobRecCompDtService.findByJobRecDtAndDeactive(jobRecDt, false);
				Double vCompWt=0.0;
				for(JobRecCompDt jobRecCompDt :jobRecCompDts) {
					vCompWt +=jobRecCompDt.getMetalWt();
				}
				
				
				Double netWt=0.0;
				
				Double vMetalWt=Math.round(((grossWt)-vCts-vCompWt)*1000.0)/1000.0;
				
				
				if(netWtWithComp.equals(true)){
					
					netWt = Math.round(((grossWt)-vCts)*1000.0)/1000.0;
				}else {
					netWt = Math.round(((grossWt)-vCts-vCompWt)*1000.0)/1000.0;
				}
				
				
				
				
				Double recPure =Math.round((netWt*purity.getPurityConv())*1000.0)/1000.0;
				
				if(recPure<=balPure) {

					
					@SuppressWarnings("unchecked")
					TypedQuery<Object> query2 =  (TypedQuery<Object>)entityManager.createNativeQuery("{ CALL jsp_fifo_jobworkmetalrate(?,?) }");
					query2.setParameter(1,jobRecMt.getParty().getId());
					query2.setParameter(2,purity.getMetal().getId());
					
					List<Object> mtlRateList = query2.getResultList();
					
					Double mtlRate = (Double) mtlRateList.get(0);
					
					mtlRate = 	mtlRate != null ? mtlRate :0.0;
					
					mtlRate=Math.round((mtlRate*purity.getPurityConv())*100.0)/100.0;
					
					
					jobRecDt.setGrossWt(Math.round((grossWt)*1000.0)/1000.0);
					jobRecDt.setNetWt(netWt);
					jobRecDt.setMetalRate(mtlRate != null ? mtlRate :0.0);
					save(jobRecDt);
					
					JobRecMetalDt jobRecMetalDt =jobRecMetalDtService.findByJobRecDtAndDeactiveAndMainMetal(jobRecDt, false, true);
					if(jobRecMetalDt !=null) {
						jobRecMetalDt.setColor(jobRecDt.getColor());
						jobRecMetalDt.setMetalWeight(vMetalWt);
						jobRecMetalDt.setPurity(jobRecDt.getPurity());
						jobRecMetalDt.setMetalRate(mtlRate != null ? mtlRate :0.0);
						jobRecMetalDtService.save(jobRecMetalDt);
					}else {
						JobRecMetalDt jobRecMetalDtNew = new JobRecMetalDt();
						jobRecMetalDtNew.setBagMt(jobRecDt.getBagMt());
						jobRecMetalDtNew.setColor(jobRecDt.getColor());
						jobRecMetalDtNew.setCreateDate(new Date());
						jobRecMetalDtNew.setCreatedBy(principal.getName());
						jobRecMetalDtNew.setDepartment(jobRecDt.getDepartment());
						jobRecMetalDtNew.setJobRecDt(jobRecDt);
						jobRecMetalDtNew.setJobRecMt(jobRecDt.getJobRecMt());
						jobRecMetalDtNew.setMainMetal(true);
						jobRecMetalDtNew.setMetalPcs(1);
						jobRecMetalDtNew.setMetalWeight(vMetalWt);
						jobRecMetalDtNew.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part",false));
						jobRecMetalDtNew.setPurity(jobRecDt.getPurity());
						jobRecMetalDtNew.setMetalRate(mtlRate != null ? mtlRate :0.0);
						jobRecMetalDtService.save(jobRecMetalDtNew);
					}
					
					
					
					
					
					TranMt tranMt = tranMtService.findByBagMtCurrStk(jobRecDt.getBagMt(), true);
					if(tranMt.getDepartment().getName().equalsIgnoreCase("Job Work")) {
						tranMt.setRecWt(jobRecDt.getGrossWt());
						tranMtService.save(tranMt);
						
						List<TranMetal>tranMetals =tranMetalService.findByTranMtAndCurrStk(tranMt, true);
						TranMetal tranMetal = tranMetals.get(0);
						tranMetal.setMetalWeight(vMetalWt);
						tranMetal.setMetalRate(mtlRate != null ? mtlRate :0.0);	
						tranMetalService.save(tranMetal);
						
						
					}
					
					retVal="1";
				}else {
					retVal="Stock Not sufficient ,Available Stock in Pure ="+balPure;
				}
			
		}
		
		

				
			
		
		
		return retVal;
	}

	@Override
	public String updateGrossWt(JobRecDt jobRecDt, Boolean netWtWithComp) {
		 Double totNetWt=0.0;	
		 List<JobRecMetalDt> jobRecMetalDts=jobRecMetalDtService.findByJobRecDtAndDeactive(jobRecDt, false);
		 if(jobRecMetalDts.size()>0){
			 for(JobRecMetalDt jobRecMetalDt :jobRecMetalDts){
				 totNetWt  += jobRecMetalDt.getMetalWeight();	 
			 }
			 
		 }
		 
				
			List<JobRecStnDt> jobRecStnDts = jobRecStnDtService.findByJobRecDtAndDeactive(jobRecDt, false);
			Double totStnCarat = 0.0;
			for(JobRecStnDt jobRecStnDt:jobRecStnDts){
				totStnCarat += jobRecStnDt.getCarat();
			}
			
			Double temVal = totStnCarat/5;
			Double totGrossWt = totNetWt+temVal;
			
			List<JobRecCompDt> jobRecCompDts = jobRecCompDtService.findByJobRecDtAndDeactive(jobRecDt, false);
			Double totCompMetalWt = 0.0;
			for(JobRecCompDt jobRecCompDt:jobRecCompDts){
				totCompMetalWt += jobRecCompDt.getMetalWt();
			}
			
			totGrossWt += totCompMetalWt;
			
			
			Double grossWtdiff = Math.round((jobRecDt.getGrossWt()-totGrossWt)*1000.0)/1000.0;
			
			JobRecMetalDt jobRecMetalDt = jobRecMetalDtService.findByJobRecDtAndDeactiveAndMainMetal(jobRecDt, false, true);
			jobRecMetalDt.setMetalWeight(Math.round((jobRecMetalDt.getMetalWeight()+grossWtdiff)*1000.0)/1000.0);
			jobRecMetalDtService.save(jobRecMetalDt);	
			
			
			if(netWtWithComp.equals(true)){
				
				totNetWt +=  totCompMetalWt+grossWtdiff;
			}else {
				totNetWt += grossWtdiff;
			}
			
		
			jobRecDt.setNetWt(Math.round(totNetWt*1000.0)/1000.0);
			
			save(jobRecDt);
			
		return null;
	}

	@Override
	public String jobRecDtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal, String pInvNo,Boolean disableFlg) throws ParseException {
		// TODO Auto-generated method stub
		int srno =0;

		StringBuilder sb = new StringBuilder();
		//Page<JobRecDt> jobRecDts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		
		JobRecMt jobRecMt = jobRecMtService.findByInvNoAndDeactive(pInvNo.trim(), false);
		//jobRecDts = searchAll(limit, offset, sort, order, search, jobRecMt.getId());
		
		List<JobRecDt>jobRecDts =findByJobRecMtAndDeactive(jobRecMt, false);
		
		sb.append("{\"total\":").append(jobRecDts.size()).append(",\"rows\": [");
		
			for(JobRecDt jobRecDt:jobRecDts){
				Double vCostMetalWt =0.0;	
				Double vCompMetalWt=0.0;
				Double vTotMetalWt = Math.round((vCostMetalWt+vCompMetalWt)*1000.0)/1000.0;
				
				srno +=1;
				
				String processNm="";
				/*
				 * if(jobRecDt.getProcess() != null){ String[] data1 =
				 * jobRecDt.getProcess().split(",");
				 * 
				 * for(int i=0; i<data1.length; i++){ LabourType labourType =
				 * labourTypeService.findOne(Integer.parseInt(data1[i])); if(processNm == ""){
				 * processNm += labourType.getName();
				 * 
				 * }else { processNm += ","+labourType.getName();
				 * 
				 * } }
				 * 
				 * }
				 */
				
			sb.append("{\"id\":\"")
				.append(jobRecDt.getId())
				.append("\",\"srNo\":\"")
				.append(srno)
				.append("\",\"itemNo\":\"")
				.append((jobRecDt.getItemNo() != null ? jobRecDt.getItemNo() : ""))
				.append("\",\"party\":\"")
				.append((jobRecDt.getParty() != null ? jobRecDt.getParty().getPartyCode() : ""))
				.append("\",\"ordNo\":\"")
				.append((jobRecDt.getOrderDt().getOrderMt().getInvNo() != null ? jobRecDt.getOrderDt().getOrderMt().getInvNo() : ""))
				.append("\",\"ordRefNo\":\"")
				.append((jobRecDt.getOrderDt().getOrderMt().getRefNo() != null ? jobRecDt.getOrderDt().getOrderMt().getRefNo() : ""))
				.append("\",\"style\":\"")
				.append((jobRecDt.getDesign() != null ? jobRecDt.getDesign().getMainStyleNo() : ""))
				.append("\",\"bag\":\"")
				.append((jobRecDt.getBagMt() != null ? jobRecDt.getBagMt().getName() : ""))
				.append("\",\"purity\":\"")
				.append((jobRecDt.getPurity() != null ? jobRecDt.getPurity().getName() : ""))
				.append("\",\"lossPercDt\":\"")
				.append((jobRecDt.getLossPercDt() != null ? jobRecDt.getLossPercDt() : ""))
				.append("\",\"color\":\"")
				.append((jobRecDt.getColor() != null ? jobRecDt.getColor().getName() : ""))
				.append("\",\"pcs\":\"")
				.append((jobRecDt.getPcs() != null ? jobRecDt.getPcs() : ""))
				.append("\",\"grossWt\":\"")
				.append((jobRecDt.getGrossWt() != null ? jobRecDt.getGrossWt() : ""))
				.append("\",\"netWt\":\"")
				.append((jobRecDt.getNetWt() != null ? jobRecDt.getNetWt() : ""))
				.append("\",\"setNo\":\"")
				.append((jobRecDt.getSetNo() != null ? jobRecDt.getSetNo() : ""))
				.append("\",\"metalRate\":\"")
				.append((jobRecDt.getMetalRate() != null ? jobRecDt.getMetalRate() : ""))
				.append("\",\"process\":\"")
				.append((processNm))
				.append("\",\"metalValue\":\"")
				.append((jobRecDt.getMetalValue() != null ? jobRecDt.getMetalValue() : ""))
				.append("\",\"stoneValue\":\"")
				.append((jobRecDt.getStoneValue() != null ? jobRecDt.getStoneValue() : ""))
				.append("\",\"compValue\":\"")
				.append((jobRecDt.getCompValue() != null ? jobRecDt.getCompValue() : ""))
				.append("\",\"labourValue\":\"")
				.append((jobRecDt.getLabValue() != null ? jobRecDt.getLabValue() : ""))
				.append("\",\"setValue\":\"")
				.append((jobRecDt.getSetValue() != null ? jobRecDt.getSetValue() : ""))
				.append("\",\"handlingCost\":\"")
				.append((jobRecDt.getHdlgValue() != null ? jobRecDt.getHdlgValue() : ""))
				.append("\",\"fob\":\"")
				.append((jobRecDt.getFob() != null ? jobRecDt.getFob() : ""))
				.append("\",\"other\":\"")
				.append((jobRecDt.getOther() != null ? jobRecDt.getOther() : ""))
				.append("\",\"dispPercentDt\":\"")
				.append((jobRecDt.getDispPercentDt() != null ? jobRecDt.getDispPercentDt() : ""))
				.append("\",\"discAmount\":\"")
				.append((jobRecDt.getDiscAmount() != null ? jobRecDt.getDiscAmount() : ""))
				.append("\",\"finalPrice\":\"")
				.append((jobRecDt.getFinalPrice() != null ? jobRecDt.getFinalPrice() : ""))
				.append("\",\"image\":\"")
				.append(jobRecDt.getDesign().getDefaultImage() != null ? jobRecDt.getDesign().getDefaultImage() :"blank.png")
				.append("\",\"totMetalWt\":\"")
				.append((vTotMetalWt))
				.append("\",\"rLock\":\"")
				.append((jobRecDt.getrLock())); //1 = lock & 0 = unlock
			
			if(!disableFlg) {
			sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doJobRecDtLockUnLock(")
				.append(jobRecDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editJobRecDt(")
				.append(jobRecDt.getId())
				.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteJobRecDt(event,")
				.append(jobRecDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(jobRecDt.getId())
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
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@Override
	public String applyLabRate(String dtIds, String labType, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal = "-1";
		
		try {
			

			synchronized (this) {
				String[] ids = dtIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					
					JobRecDt jobRecDt = findOne(Integer.parseInt(ids[i]));
					LabourType labourType = labourTypeService.findByCodeAndDeactive(labType, false);
					
					List<LabourCharge> labourCharges=null;
					
					 labourCharges =labourChargeService.getPurityLabourRates(jobRecDt.getParty(), jobRecDt.getDesign().getCategory(),
							 jobRecDt.getNetWt(),false, jobRecDt.getPurity().getMetal(),labourType,jobRecDt.getPurity());
						 
						 if(labourCharges.size()<=0){
							 
							 labourCharges=labourChargeService.getLabourRates(jobRecDt.getParty(), jobRecDt.getDesign().getCategory(), jobRecDt.getNetWt(), false, jobRecDt.getPurity().getMetal(), labourType);
							 
						 }
					
					
					
								
				
					
					List<JobRecLabDt> jobRecLabDts = jobRecLabDtService.findByJobRecDtAndDeactive(jobRecDt, false);
					
					
					Boolean isAvailable=false;
					for(LabourCharge labourCharge :labourCharges){
						isAvailable=false;
						
						for (JobRecLabDt jobRecLabDt : jobRecLabDts) {
							
							if(jobRecLabDt.getLabourType().equals(labourCharge.getLabourType())){
								
								isAvailable=true;
								if(jobRecLabDt.getrLock().equals(false)){
									
									jobRecLabDt.setLabourRate(labourCharge.getRate());	
									jobRecLabDt.setPcsWise(false);
									jobRecLabDt.setGramWise(false);
									jobRecLabDt.setPercentWise(false);
									jobRecLabDt.setPerCaratRate(false);
									jobRecLabDt.setBagmt(jobRecDt.getBagMt());
									jobRecLabDt.setOrderDt(jobRecDt.getBagMt().getOrderDt());
									
									if(labourCharge.getPerPcsRate() == true){
										jobRecLabDt.setPcsWise(true);
									}else if(labourCharge.getPerGramRate() == true){
										jobRecLabDt.setPerGramRate(true);
									}else if(labourCharge.getPercentage() == true){
										jobRecLabDt.setPercentWise(true);
									}else if(labourCharge.getPerCaratRate() == true){
										jobRecLabDt.setPerCaratRate(true);
									}
									
									
									jobRecLabDtService.save(jobRecLabDt);
									
									
								}
								
							}
						}
						
						
						if(!isAvailable){
							
							JobRecLabDt jobRecLabDt = new JobRecLabDt();
							
							jobRecLabDt.setJobRecMt(jobRecDt.getJobRecMt());
							jobRecLabDt.setJobRecDt(jobRecDt);
							jobRecLabDt.setLabourType(labourCharge.getLabourType());
							jobRecLabDt.setLabourRate(labourCharge.getRate());
							jobRecLabDt.setMetal(jobRecDt.getPurity().getMetal());
							jobRecLabDt.setBagmt(jobRecDt.getBagMt());
							jobRecLabDt.setOrderDt(jobRecDt.getBagMt().getOrderDt());
							
							if(labourCharge.getPerPcsRate() == true){
								jobRecLabDt.setPcsWise(true);
							}else if(labourCharge.getPerGramRate() == true){
								jobRecLabDt.setPerGramRate(true);
							}else if(labourCharge.getPercentage() == true){
								jobRecLabDt.setPercentWise(true);
							}else if(labourCharge.getPerCaratRate() == true){
								jobRecLabDt.setPerCaratRate(true);
							}
							
							jobRecLabDt.setCreatedDate(new java.util.Date());
							jobRecLabDt.setCreatedBy(principal.getName());
							
							jobRecLabDtService.save(jobRecLabDt);
							
							
						}
						
					}
					updateFob(jobRecDt, false);
					
					retVal ="1";
					
					
					
					
					
				}
					
					
					
				}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}

	@Override
	public String updateFob(JobRecDt jobRecDt, Boolean deactive) {
		
		try {
			
			List<JobRecMetalDt> jobRecMetalDts = jobRecMetalDtService.findByJobRecDtAndDeactive(jobRecDt, deactive);
			List<JobRecLabDt> jobRecLabDts = jobRecLabDtService.findByJobRecDtAndDeactive(jobRecDt, deactive);
			List<JobRecStnDt> jobRecStnDts = jobRecStnDtService.findByJobRecDtAndDeactive(jobRecDt, deactive);
			
			Double totCarat = 0.0;
			Double totLabourValue = 0.0;
			for (JobRecStnDt jobRecStnDt : jobRecStnDts) {
				totCarat += jobRecStnDt.getCarat();
			}
			
			
			for (JobRecLabDt jobRecLabDt : jobRecLabDts) {
				
				
					
		
				
				Double vTotMetalWt=0.0;
				Double vTOtMetalVal=0.0;
				
				
				for (JobRecMetalDt jobRecMetalDt : jobRecMetalDts) {
					vTotMetalWt += jobRecMetalDt.getMetalWeight();
					vTOtMetalVal +=jobRecMetalDt.getMetalValue();
				}
				
				
				QJobRecCompDt qJobRecCompDt = QJobRecCompDt.jobRecCompDt;
				JPAQuery compQuery=new JPAQuery(entityManager);
				
				List<Tuple> jobRecCompList = compQuery.from(qJobRecCompDt).where(qJobRecCompDt.deactive.eq(false).
						and(qJobRecCompDt.jobRecDt.id.eq(jobRecDt.getId())).and(qJobRecCompDt.purity.metal.eq(jobRecLabDt.getMetal())))
						.groupBy(qJobRecCompDt.purity.metal).list(qJobRecCompDt.metalWt.sum(),qJobRecCompDt.compValue.sum());
				
				
				for(Tuple tupleComp : jobRecCompList){
							
							vTotMetalWt +=  tupleComp.get(qJobRecCompDt.metalWt.sum());
							vTOtMetalVal += tupleComp.get(qJobRecCompDt.compValue.sum());
								
						}
				
				
					vTotMetalWt =  Math.round(vTotMetalWt*1000.0)/1000.0;
				
					vTOtMetalVal =  Math.round(vTOtMetalVal*100.0)/100.0;
					
					if(!jobRecLabDt.getLabourType().getCode().equalsIgnoreCase("Grade")) {
						if(jobRecLabDt.getPcsWise() == true){
							jobRecLabDt.setLabourValue(Math.round((jobRecLabDt.getLabourRate() * jobRecDt.getPcs())*100.0)/100.0);
						
						}else if(jobRecLabDt.getPerGramRate() == true){
							jobRecLabDt.setLabourValue(Math.round((jobRecLabDt.getLabourRate() * vTotMetalWt)*100.0)/100.0);
						}else if(jobRecLabDt.getPercentWise() == true){
							jobRecLabDt.setLabourValue(Math.round(((vTOtMetalVal * jobRecLabDt.getLabourRate())/100 )*100.0)/100.0);
						}else if(jobRecLabDt.getPerCaratRate() == true){
							jobRecLabDt.setLabourValue(Math.round((totCarat * jobRecLabDt.getLabourRate())*100.0)/100.0);
						}
	
						
					}
					
										
					
					jobRecLabDtService.save(jobRecLabDt);
					totLabourValue += jobRecLabDt.getLabourValue();
				
			}
			
			jobRecDt.setLabValue(totLabourValue);
			save(jobRecDt);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		
		return "";
	}

	@Override
	public String applyGradingRate(String dtIds, String labType, Principal principal) {
		// TODO Auto-generated method stub
String retVal = "-1";
		
		try {
			
			
		
				String[] ids = dtIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					
					Double labValue = 0.0;
					
					
					JobRecDt jobRecDt = findOne(Integer.parseInt(ids[i]));
					LabourType labourType = labourTypeService.findByCodeAndDeactive(labType, false);
					
					Double totalCarat = 0.0;
					List<JobRecStnDt> jobRecStnDts = jobRecStnDtService.findByJobRecDtAndDeactive(jobRecDt, false);
					for (JobRecStnDt jobRecStnDt : jobRecStnDts) {
						if(jobRecStnDt.getStoneType().getName().equalsIgnoreCase("Diamond")) {
							totalCarat +=   jobRecStnDt.getCarat();	
						}
						
					}
					
					
				
					
					List<LabourCharge> labourCharges=labourChargeService.getLabourRates(jobRecDt.getBagMt().getOrderMt().getParty(), jobRecDt.getDesign().getCategory(), totalCarat, false, jobRecDt.getPurity().getMetal(), labourType);
					
					if(labourCharges.size() > 0) {
					List<JobRecLabDt> jobRecLabDts = jobRecLabDtService.findByJobRecDtAndDeactive(jobRecDt, false);
					
					Boolean isAvailable=false;
					for(LabourCharge labourCharge :labourCharges){
						isAvailable=false;
						Double totalValue = 0.0;
						
						
						Double pointerCts=0.00;
							for (JobRecStnDt jobRecStnDt : jobRecStnDts) {
								if(jobRecStnDt.getStoneType().getName().equalsIgnoreCase("Diamond")) {
								StoneChart stoneChart = stoneChartService.findByShapeAndSizeAndDeactive(jobRecStnDt.getShape(), jobRecStnDt.getSize(), false);
								if(stoneChart != null) {
									
									Double pointerRate =jobRecStnDt.getJobRecMt().getPointerRate();
									if(pointerRate <= 0) {
										pointerRate = 750.0;
									}
									
									if(stoneChart.getPointerFlg()) {
										
										totalValue += Math.round((pointerRate * jobRecStnDt.getCarat())*100.0)/100.0;	
										pointerCts +=jobRecStnDt.getCarat();
									}else {
										
										 if(labourCharge.getPerCaratRate() == true){
											totalValue += Math.round((labourCharge.getRate() * jobRecStnDt.getCarat())*100.0)/100.0;
										}
										
									}
									
								}
								
								}
							}
							
							if((Math.round(totalCarat*1000.0)/1000.0)>(Math.round(pointerCts*1000.0)/1000.0)) {
							if(labourCharge.getPerPcsRate() == true){
								totalValue += Math.round((labourCharge.getRate() * jobRecDt.getPcs())*100.0)/100.0;
							
							}
							}
						
						
						//Double labRate = Math.round((totalValue / totalCarat)*100.0)/100.0;
						
						
						for (JobRecLabDt jobRecLabDt : jobRecLabDts) {
							
							if(jobRecLabDt.getLabourType().equals(labourCharge.getLabourType())){
								
								isAvailable=true;
								if(jobRecLabDt.getrLock().equals(false)){
									
										
									jobRecLabDt.setPcsWise(false);
									jobRecLabDt.setGramWise(false);
									jobRecLabDt.setPercentWise(false);
									jobRecLabDt.setPerCaratRate(false);
									jobRecLabDt.setBagmt(jobRecDt.getBagMt());
									jobRecLabDt.setOrderDt(jobRecDt.getBagMt().getOrderDt());
									jobRecLabDt.setLabourValue(totalValue);
									
									if((Math.round(totalCarat*1000.0)/1000.0)>(Math.round(pointerCts*1000.0)/1000.0)) {
										if(labourCharge.getPerPcsRate() == true){
											jobRecLabDt.setPerPcRate(true);
											jobRecLabDt.setLabourRate(Math.round((totalValue / jobRecDt.getPcs())*100.0)/100.0);	
										}	
									}else {
										jobRecLabDt.setPerCaratRate(true);
										jobRecLabDt.setLabourRate(Math.round((totalValue / totalCarat)*100.0)/100.0);
									}
									
								 if(labourCharge.getPerCaratRate() == true){
									 jobRecLabDt.setPerCaratRate(true);
									 jobRecLabDt.setLabourRate(Math.round((totalValue / totalCarat)*100.0)/100.0);
										
									}
									
									
									jobRecLabDtService.save(jobRecLabDt);
									
									labValue += totalValue;
									
								}
								
							}
						}
						
						
						if(!isAvailable){
							
							JobRecLabDt jobRecLabDt = new JobRecLabDt();
							
							jobRecLabDt.setJobRecMt(jobRecDt.getJobRecMt());
							jobRecLabDt.setJobRecDt(jobRecDt);
							jobRecLabDt.setLabourType(labourCharge.getLabourType());
							jobRecLabDt.setMetal(jobRecDt.getPurity().getMetal());
							jobRecLabDt.setBagmt(jobRecDt.getBagMt());
							jobRecLabDt.setOrderDt(jobRecDt.getBagMt().getOrderDt());
							jobRecLabDt.setLabourValue(totalValue);
							
							if((Math.round(totalCarat*1000.0)/1000.0)>(Math.round(pointerCts*1000.0)/1000.0)) {
								if(labourCharge.getPerPcsRate() == true){
									jobRecLabDt.setPerPcRate(true);
									jobRecLabDt.setLabourRate(Math.round((totalValue / jobRecDt.getPcs())*100.0)/100.0);	
								}	
							}else {
								jobRecLabDt.setPerCaratRate(true);
								jobRecLabDt.setLabourRate(Math.round((totalValue / totalCarat)*100.0)/100.0);
							}
							
						 if(labourCharge.getPerCaratRate() == true){
							 jobRecLabDt.setPerCaratRate(true);
							 jobRecLabDt.setLabourRate(Math.round((totalValue / totalCarat)*100.0)/100.0);
								
							}
							
							
							jobRecLabDt.setCreatedDate(new java.util.Date());
							jobRecLabDt.setCreatedBy(principal.getName());
							
							jobRecLabDtService.save(jobRecLabDt);
							
							labValue += totalValue;
						}
						
					}
					
					
					
					
					}else {
						
					}
				updateFob(jobRecDt, false);
					
					
					
					retVal ="1";
				}
					
					
					
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
	}



}

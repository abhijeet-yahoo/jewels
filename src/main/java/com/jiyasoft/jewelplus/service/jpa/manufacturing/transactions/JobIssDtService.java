package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QJobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobIssDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class JobIssDtService implements IJobIssDtService {

	@Autowired
	private IJobIssDtRepository jobIssDtRepository;
		
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IJobIssMetalDtService jobIssMetalDtService;
	
	@Autowired
	private IJobIssStnDtService jobIssStnDtService;
	
	@Autowired
	private IJobIssCompDtService jobIssCompDtService;
	
	@Autowired
	private IJobIssLabDtService jobIssLabDtService;
	
	@Autowired
	private IJobIssMtService jobIssMtService;
	
	
	
	@Override
	public List<JobIssDt> findAll() {
		// TODO Auto-generated method stub
		return jobIssDtRepository.findAll();
	}

	@Override
	public void save(JobIssDt jobIssDt) {
		jobIssDtRepository.save(jobIssDt);
		
	}

	@Override
	public void delete(int id) {
		JobIssDt jobIssDt = jobIssDtRepository.findOne(id);
		jobIssDt.setDeactive(true);
		jobIssDt.setDeactiveDt(new java.util.Date());
		jobIssDtRepository.save(jobIssDt);
		
	}

	@Override
	public JobIssDt findOne(int id) {
		// TODO Auto-generated method stub
		return jobIssDtRepository.findOne(id);
	}

	@Override
	public JobIssDt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobIssDtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public List<JobIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssDtRepository.findByJobIssMtAndDeactive(jobIssMt, deactive);
	}

	@Override
	public String calculateFinalPrice(String finalPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobIssDt> findByItemNoAndOrderDtAndJobIssMtAndDeactive(String itemNo, OrderDt orderDt,
			JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssDtRepository.findByItemNoAndOrderDtAndJobIssMtAndDeactive(itemNo, orderDt, jobIssMt, deactive);
	}

	@Override
	public List<JobIssDt> findByOrderDtAndJobIssMtAndDeactive(OrderDt orderDt, JobIssMt jobIssMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssDtRepository.findByOrderDtAndJobIssMtAndDeactive(orderDt, jobIssMt, deactive);
	}

	@Override
	public List<JobIssDt> getJobIssDtList(JobIssMt jobIssMt) {
	
		QJobIssDt qJobIssDt = QJobIssDt.jobIssDt;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<JobIssDt> jobIssDtList = null;
		
		jobIssDtList = query.from(qJobIssDt)
					.where(qJobIssDt.jobIssMt.id.eq(jobIssMt.getId()).and(qJobIssDt.deactive.eq(false)))
					.groupBy(qJobIssDt.jobIssMt,qJobIssDt.itemNo,qJobIssDt.orderDt)
					.list(qJobIssDt);
		
		return jobIssDtList;
	}

	@Override
	public Page<JobIssDt> findByItemNo(Integer limit, Integer offset, String sort, String order, String itemNo,
			Boolean onlyActive) {
		
		QJobIssDt qJobIssDt = QJobIssDt.jobIssDt;
		BooleanExpression expression = qJobIssDt.deactive.eq(false);

		if (onlyActive) {
			if (itemNo == null) {
				expression = qJobIssDt.deactive.eq(false);
			} else {
				expression = qJobIssDt.deactive.eq(false).and(qJobIssDt.itemNo.like(itemNo + "%"));
			}
		} else {
			if (itemNo != null) {
				expression = qJobIssDt.itemNo.like(itemNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "itemNo";
		}

		Page<JobIssDt> jobIssDtList = (Page<JobIssDt>) jobIssDtRepository.findAll(expression,new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC), sort));

		return jobIssDtList;
		
	}

	@Override
	public List<JobIssDt> findByItemNoAndDeactive(String itemNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssDtRepository.findByItemNoAndDeactive(itemNo, deactive);
	}

	@Override
	public void lockUnlockDt(Integer jobIssMtId, Boolean status) {
		
		QJobIssDt qJobIssDt = QJobIssDt.jobIssDt;
		
		new JPAUpdateClause(entityManager, qJobIssDt).where(qJobIssDt.jobIssMt.id.eq(jobIssMtId))
		.set(qJobIssDt.rLock, status)
		.execute();
		
		
		
	}

	@Override
	public Page<JobIssDt> searchAll(Integer limit, Integer offset, String sort, String order, String name,
			Integer mtId) {
		
		QJobIssDt qJobIssDt = QJobIssDt.jobIssDt;
		BooleanExpression expression = qJobIssDt.deactive.eq(false).and(qJobIssDt.jobIssMt.id.eq(mtId));

		if(name !=null && name !=""){
			
			expression = qJobIssDt.deactive.eq(false).and(qJobIssDt.jobIssMt.id.eq(mtId)).and(qJobIssDt.bagMt.name.like(name.trim()+"%")
					.or(qJobIssDt.design.mainStyleNo.like(name+"%")).or(qJobIssDt.party.name.like(name+"%")).or(qJobIssDt.orderDt.orderMt.invNo.like(name+"%"))
					.or(qJobIssDt.orderDt.orderMt.refNo.like(name+"%")));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if(sort.equalsIgnoreCase("style")){
			sort = "design";
		}

		Page<JobIssDt> jobIssDtList = (Page<JobIssDt>) jobIssDtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return jobIssDtList;
	}

	@Override
	public String jobIssDtSave(Integer jobIssDtId, Double grossWt, Double other, Integer partyId, String itemNo,
			Double dispPercentDt, Double lossPercDt, Principal principal) {
		
		
		String retVal = "-1";

		try {
			Party jobDtParty = partyService.findOne(partyId);
			JobIssDt jobIssDt = findOne(jobIssDtId);
			
			
			BagMt bagMt = bagMtService.findOne(jobIssDt.getBagMt().getId());
			
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
			

			
			Double vGrossWtDiff=jobIssDt.getGrossWt()-grossWt;
			
			
			jobIssDt.setGrossWt(grossWt);
			jobIssDt.setNetWt(jobIssDt.getNetWt()-vGrossWtDiff);
			jobIssDt.setOther(other);
			jobIssDt.setLossPercDt(lossPercDt);
			jobIssDt.setModiBy(principal.getName());
			jobIssDt.setModiDate(new java.util.Date());
			jobIssDt.setParty(jobDtParty);
			jobIssDt.setItemNo(itemNo);
			jobIssDt.setDispPercentDt(dispPercentDt);
			save(jobIssDt);
			
			
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
	public List<Tuple> getSetNoList(Integer jobIssMtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lockDtInvoice(Integer jobIssMtId, Integer setNo, Principal principal, Boolean vRLock) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getMaxSetNoByOrder(Integer mtId, Integer sordDtId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteJobIssDt(Integer dtId) {
		
		JobIssDt jobIssDt = findOne(dtId);
		
		TranMt tranMt =tranMtService.findOne(jobIssDt.getTranMtId());
		
		if(tranMt.getCurrStk().equals(true) && jobIssDt.getAdjustedQty()==0) {
			
			List<JobIssMetalDt>jobIssMetalDts = jobIssMetalDtService.findByJobIssDtAndDeactive(jobIssDt, false);
			for(JobIssMetalDt jobIssMetalDt : jobIssMetalDts) {
				jobIssMetalDtService.delete(jobIssMetalDt.getId());
			}
			
			List<JobIssStnDt> jobIssStnDts = jobIssStnDtService.findByJobIssDtAndDeactive(jobIssDt, false);
			for(JobIssStnDt jobIssStnDt :jobIssStnDts) {
				jobIssStnDtService.delete(jobIssStnDt.getId());
			}
			
			List<JobIssCompDt>jobIssCompDts =jobIssCompDtService.findByJobIssDtAndDeactive(jobIssDt, false);
			
			for(JobIssCompDt jobIssCompDt :jobIssCompDts) {
				jobIssCompDtService.delete(jobIssCompDt.getId());
			}
			
			List<JobIssLabDt>jobIssLabDts =jobIssLabDtService.findByJobIssDtAndDeactive(jobIssDt, false);
			for(JobIssLabDt jobIssLabDt :jobIssLabDts) {
				jobIssLabDtService.delete(jobIssLabDt.getId());
			}
			
			
			BagMt bagMt = bagMtService.findOne(jobIssDt.getBagMt().getId());
			bagMt.setJobWorkFlg(false);
			bagMt.setModiDate(new Date());
			bagMtService.save(bagMt);
			
			delete(dtId);
			
			tranMt.setHoldFlg(false);
			tranMtService.save(tranMt);
			
			
			
			
		}else {
			
			return "Can Not Delete,Adjusted Qty >0 Or Bag Not In Same TranMtId Location";
		}
		 
		
		
		return "1";
	}

	@Override
	public String jobIssDtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal, String pInvNo,Boolean disableFlg) throws ParseException {
		// TODO Auto-generated method stub
		int srno =offset;

		StringBuilder sb = new StringBuilder();
		Page<JobIssDt> jobIssDts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		
		JobIssMt jobIssMt = jobIssMtService.findByInvNoAndDeactive(pInvNo.trim(), false);
		jobIssDts = searchAll(limit, offset, sort, order, search, jobIssMt.getId());
		
		sb.append("{\"total\":").append(jobIssDts.getTotalElements()).append(",\"rows\": [");
		
			for(JobIssDt jobIssDt:jobIssDts){
					Double vCostMetalWt =0.0;	
				Double vCompMetalWt=0.0;
					
					Double vTotMetalWt = Math.round((vCostMetalWt+vCompMetalWt)*1000.0)/1000.0;
					
				srno +=1;
				
				String processNm="";
				if(!jobIssDt.getProcess().isEmpty()){
				String[] data1 = jobIssDt.getProcess().split(",");
				
				/*
				 * for(int i=0; i<data1.length; i++){ LabourType labourType =
				 * labourTypeService.findOne(Integer.parseInt(data1[i])); if(processNm == ""){
				 * processNm += labourType.getName();
				 * 
				 * }else { processNm += ","+labourType.getName();
				 * 
				 * } }
				 */
					
			}
				
			sb.append("{\"id\":\"")
				.append(jobIssDt.getId())
				.append("\",\"srNo\":\"")
				.append(srno)
				.append("\",\"itemNo\":\"")
				.append((jobIssDt.getItemNo() != null ? jobIssDt.getItemNo() : ""))
				.append("\",\"party\":\"")
				.append((jobIssDt.getParty() != null ? jobIssDt.getParty().getPartyCode() : ""))
				.append("\",\"ordNo\":\"")
				.append((jobIssDt.getOrderDt().getOrderMt().getInvNo() != null ? jobIssDt.getOrderDt().getOrderMt().getInvNo() : ""))
				.append("\",\"ordRefNo\":\"")
				.append((jobIssDt.getOrderDt().getOrderMt().getRefNo() != null ? jobIssDt.getOrderDt().getOrderMt().getRefNo() : ""))
				.append("\",\"style\":\"")
				.append((jobIssDt.getDesign() != null ? jobIssDt.getDesign().getMainStyleNo() : ""))
				.append("\",\"bag\":\"")
				.append((jobIssDt.getBagMt() != null ? jobIssDt.getBagMt().getName() : ""))
				.append("\",\"purity\":\"")
				.append((jobIssDt.getPurity() != null ? jobIssDt.getPurity().getName() : ""))
				.append("\",\"lossPercDt\":\"")
				.append((jobIssDt.getLossPercDt() != null ? jobIssDt.getLossPercDt() : ""))
				.append("\",\"color\":\"")
				.append((jobIssDt.getColor() != null ? jobIssDt.getColor().getName() : ""))
				.append("\",\"pcs\":\"")
				.append((jobIssDt.getPcs() != null ? jobIssDt.getPcs() : ""))
				.append("\",\"grossWt\":\"")
				.append((jobIssDt.getGrossWt() != null ? jobIssDt.getGrossWt() : ""))
				.append("\",\"netWt\":\"")
				.append((jobIssDt.getNetWt() != null ? jobIssDt.getNetWt() : ""))
				.append("\",\"setNo\":\"")
				.append((jobIssDt.getSetNo() != null ? jobIssDt.getSetNo() : ""))
				.append("\",\"metalRate\":\"")
				.append((jobIssDt.getMetalRate() != null ? jobIssDt.getMetalRate() : ""))
				.append("\",\"metalValue\":\"")
				.append((jobIssDt.getMetalValue() != null ? jobIssDt.getMetalValue() : ""))
				.append("\",\"stoneValue\":\"")
				.append((jobIssDt.getStoneValue() != null ? jobIssDt.getStoneValue() : ""))
				.append("\",\"compValue\":\"")
				.append((jobIssDt.getCompValue() != null ? jobIssDt.getCompValue() : ""))
				.append("\",\"labourValue\":\"")
				.append((jobIssDt.getLabValue() != null ? jobIssDt.getLabValue() : ""))
				.append("\",\"setValue\":\"")
				.append((jobIssDt.getSetValue() != null ? jobIssDt.getSetValue() : ""))
				.append("\",\"handlingCost\":\"")
				.append((jobIssDt.getHdlgValue() != null ? jobIssDt.getHdlgValue() : ""))
				.append("\",\"fob\":\"")
				.append((jobIssDt.getFob() != null ? jobIssDt.getFob() : ""))
				.append("\",\"other\":\"")
				.append((jobIssDt.getOther() != null ? jobIssDt.getOther() : ""))
				.append("\",\"dispPercentDt\":\"")
				.append((jobIssDt.getDispPercentDt() != null ? jobIssDt.getDispPercentDt() : ""))
				.append("\",\"discAmount\":\"")
				.append((jobIssDt.getDiscAmount() != null ? jobIssDt.getDiscAmount() : ""))
				.append("\",\"finalPrice\":\"")
				.append((jobIssDt.getFinalPrice() != null ? jobIssDt.getFinalPrice() : ""))
				.append("\",\"process\":\"")
				.append((processNm))
				.append("\",\"image\":\"")
				.append(jobIssDt.getDesign().getDefaultImage() != null ? jobIssDt.getDesign().getDefaultImage() :"blank.png")
				.append("\",\"totMetalWt\":\"")
				.append((vTotMetalWt))
				.append("\",\"rLock\":\"")
				.append((jobIssDt.getrLock())); //1 = lock & 0 = unlock
			
			if(!disableFlg) {
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCostDtLockUnLock(")
				.append(jobIssDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editJobIssDt(")
				.append(jobIssDt.getId())
				.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteJobIssDt(event,")
				.append(jobIssDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(jobIssDt.getId())
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

}

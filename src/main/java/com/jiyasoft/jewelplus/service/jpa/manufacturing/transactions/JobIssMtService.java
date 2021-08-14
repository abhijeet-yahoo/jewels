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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QJobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IJobIssMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobCompIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobIssStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobMtlIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IJobStnIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
@Repository
public class JobIssMtService implements IJobIssMtService {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IJobIssMtRepository jobIssMtRepository;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ITranDtService tranDtService;
	
	@Autowired
	private IJobIssDtService jobIssDtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IJobIssMetalDtService jobIssMetalDtService;
	
	@Autowired
	private IJobIssStnDtService jobIssStnDtService;
	
	@Autowired
	private IJobIssCompDtService jobIssCompDtService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private IJobMtlIssDtService jobMtlIssDtService;
	
	@Autowired
	private IJobStnIssDtService jobStnIssDtService;
	
	@Autowired
	private IJobCompIssDtService jobCompIssDtService;

	@Override
	public List<JobIssMt> findAll() {
		// TODO Auto-generated method stub
		return jobIssMtRepository.findAll();
	}

	@Override
	public Page<JobIssMt> findAll(Integer limit, Integer offset, String sort, String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return jobIssMtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(JobIssMt jobIssMt) {
		jobIssMtRepository.save(jobIssMt);
		
	}

	@Override
	public void delete(int id) {
		JobIssMt jobIssMt = jobIssMtRepository.findOne(id);
		jobIssMt.setDeactive(true);
		jobIssMt.setDeactiveDt(new java.util.Date());
		jobIssMtRepository.save(jobIssMt);
		
	}

	@Override
	public JobIssMt findOne(int id) {
		// TODO Auto-generated method stub
		return jobIssMtRepository.findOne(id);
	}

	@Override
	public JobIssMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return jobIssMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Page<JobIssMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name) {
		// TODO Auto-generated method stub
		QJobIssMt qjobissIssMt = QJobIssMt.jobIssMt;
		BooleanExpression expression = qjobissIssMt.deactive.eq(false);

		if(name !=null && name !=""){
			expression = qjobissIssMt.deactive.eq(false).and(qjobissIssMt.invNo.like("%"+name+"%").or(qjobissIssMt.party.partyCode.like("%"+name+"%")).or(qjobissIssMt.department.name.like("%"+name+"%")));
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("invNo")) {
			sort = "invNo";
		}
			
		
		
		Page<JobIssMt> jobIssMtList = (Page<JobIssMt>) jobIssMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));

		return jobIssMtList;
	}

	@Override
	public JobIssMt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return jobIssMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Page<JobIssMt> getInvNoAutoFill(Integer limit, Integer offset, String sort, String order, String invNo,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getJobIssList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<JobIssMt> findActiveJobIssSortByInvDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String jobIssTransfer(String fromInvoice, String toInvoice, String dtids, Principal principal) {
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
	public String deleteJobIssMtInvoice(Integer jobIssMtId, Principal principal) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
		JobIssMt jobIssMt = findOne(jobIssMtId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date cDate = jobIssMt.getInvDate();
		String dbDate = dateFormat.format(cDate);
		
		Date date = new Date();
		String curDate = dateFormat.format(date);

		if(dbDate.equals(curDate)){
		
		
//		List<JobIssDt>jobIssDts =jobIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
//		
//		Boolean deleteVal= false;
//		for(JobIssDt jobIssDt :jobIssDts) {
//			TranMt tranMt =tranMtService.findOne(jobIssDt.getTranMtId());
//			
//			
//			if(tranMt.getCurrStk().equals(true) && jobIssDt.getAdjustedQty()==0) {
//			
//			}else {
//				deleteVal =true;
//				break;
//			}
//		}
//		
//		
//		if(deleteVal.equals(false)) {
//			for(JobIssDt jobIssDt :jobIssDts) {
//				jobIssDtService.deleteJobIssDt(jobIssDt.getId());
//			}
//			
//			
//		}else {
//			return  "Can Not Delete Item Is Adjusted Or Bag Not In TranMtId Location";
//		}
//		
//		List<JobMtlIssDt> jobMtlIssDts = jobMtlIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
//		for (JobMtlIssDt jobMtlIssDt : jobMtlIssDts) {
//			jobMtlIssDtService.jobMtlIssDtDelete(jobMtlIssDt.getId(), principal);	
//		}
//		
//		
//		List<JobStnIssDt> jobStnIssDts = jobStnIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
//		for (JobStnIssDt jobStnIssDt : jobStnIssDts) {
//			jobStnIssDtService.jobStnIssDtDelete(jobStnIssDt.getId(), principal);	
//		}
//		
//		List<JobCompIssDt> jobCompIssDts = jobCompIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
//		for (JobCompIssDt jobCompIssDt : jobCompIssDts) {
//			jobCompIssDtService.jobCompIssDtDelete(jobCompIssDt.getId(), principal);	
//		}
			
			

			String jobIssSizeArr ="";
			
			List<JobIssDt>jobIssDts =jobIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
			if(jobIssDts.size() > 0) {
				if(jobIssSizeArr.length() > 0) {
					jobIssSizeArr += " ,Jobwork Details";
				}else {
					jobIssSizeArr = " Jobwork Details";
				}
			}
			
			List<JobMtlIssDt> jobMtlIssDts = jobMtlIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
			if(jobMtlIssDts.size() > 0) {
				if(jobIssSizeArr.length() > 0) {
					jobIssSizeArr += " , loose Metal Details";
				}else {
					jobIssSizeArr = " loose Metal Details";
				}
			}
			
			List<JobStnIssDt> jobStnIssDts = jobStnIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
			if(jobStnIssDts.size() > 0) {
				if(jobIssSizeArr.length() > 0) {
					jobIssSizeArr += " ,loose Stone Details";
				}else {
					jobIssSizeArr = " loose Stone Details";
				}
			}
			
			
			List<JobCompIssDt> jobCompIssDts = jobCompIssDtService.findByJobIssMtAndDeactive(jobIssMt, false);
			if(jobCompIssDts.size() > 0) {
				if(jobIssSizeArr.length() > 0) {
					jobIssSizeArr += " ,loose Component Details";
				}else {
					jobIssSizeArr = " loose Component Details";
				}
			}
			
			
			if(jobIssSizeArr.length() > 0) {
				return jobIssSizeArr;
			}
			
			
		
		delete(jobIssMtId);
		
		retVal = "1";
		
		}else {
			return "-2";
		}
		
		return retVal;
	}

	@Override
	public String addBagInJobIss(String pOIds, Integer jobIssMtId, Principal principal,String vProcessId) {
		
		String retVal = "-1";
		
		
		String[] data1 = pOIds.split(",");
				
		
		for(int i=0; i<data1.length; i++){
			
			 TranMt tranMt = tranMtService.findOne(Integer.parseInt(data1[i]));
			JobIssMt jobIssMt = findOne(jobIssMtId);
			
			//new entry in JobIssDt
			
			JobIssDt jobIssDt = new JobIssDt();
			jobIssDt.setJobIssMt(jobIssMt);
			jobIssDt.setDesign(tranMt.getOrderDt().getDesign());
			jobIssDt.setStyleNo(tranMt.getOrderDt().getDesign().getMainStyleNo());
			jobIssDt.setVersion(tranMt.getOrderDt().getDesign().getVersion());
			jobIssDt.setBagMt(tranMt.getBagMt());
			
			jobIssDt.setParty(tranMt.getOrderDt().getOrderMt().getParty());
			jobIssDt.setPurity(tranMt.getOrderDt().getPurity());
		
			jobIssDt.setColor(tranMt.getOrderDt().getColor());
		
			jobIssDt.setOrderDt(tranMt.getOrderDt());
			jobIssDt.setDepartment(tranMt.getDepartment());
			jobIssDt.setGrossWt(tranMt.getRecWt());
			jobIssDt.setPcs(tranMt.getPcs());
			jobIssDt.setCreatedBy(principal.getName());
			jobIssDt.setCreatedDate(new java.util.Date());
			jobIssDt.setItemNo(tranMt.getBagMt().getItemNo());
			jobIssDt.setProcess(vProcessId);
			jobIssDt.setTranMtId(tranMt.getId());
			
			
			jobIssDtService.save(jobIssDt);
			
			//Dump Value In BagMt
			BagMt bagMt = bagMtService.findOne(jobIssDt.getBagMt().getId());
			bagMt.setJobWorkFlg(true);
		//	bagMt.setCostingMtId(costingDt.getCostingMt().getId());
			bagMt.setModiBy(principal.getName());
			bagMt.setModiDate(new Date());
			bagMtService.save(bagMt);
			
			
			// new entry in CostStnDt
			List<TranDt>tranDts = tranDtService.findByTranMtAndCurrStk(tranMt, true);
			
			Double totCarat=0.0;
			Double totStoneVal=0.0;
			Double totMetalVal=0.0;
			
			for(TranDt tranDt:tranDts){
				
				Double rate = tranDt.getFactoryRate() != null ? tranDt.getFactoryRate() : 0.0;
			
				JobIssStnDt jobIssStnDt = new JobIssStnDt();
				jobIssStnDt.setStoneType(tranDt.getStoneType());
				jobIssStnDt.setJobIssDt(jobIssDt);
				jobIssStnDt.setJobIssMt(jobIssMt);
				jobIssStnDt.setShape(tranDt.getShape());
				jobIssStnDt.setBagMt(bagMt);
				jobIssStnDt.setQuality(tranDt.getQuality());
				jobIssStnDt.setSizeGroup(tranDt.getSizeGroup());
				jobIssStnDt.setSetting(tranDt.getSetting());
				jobIssStnDt.setSettingType(tranDt.getSettingType());
				jobIssStnDt.setOrderDt(tranDt.getTranMt().getOrderDt());
				jobIssStnDt.setBagSrNo(tranDt.getBagSrNo());
				jobIssStnDt.setSize(tranDt.getSize());
				jobIssStnDt.setSieve(tranDt.getSieve());
				jobIssStnDt.setStone(tranDt.getStone());
				jobIssStnDt.setCarat(tranDt.getCarat());
				jobIssStnDt.setPartNm(tranDt.getPartNm());
				jobIssStnDt.setCreatedBy(principal.getName());
				jobIssStnDt.setCreatedDate(new java.util.Date());
				jobIssStnDt.setCenterStone(tranDt.getCenterStone() !=null? tranDt.getCenterStone() :false);
				jobIssStnDt.setStnRate(tranDt.getFactoryRate() != null ? tranDt.getFactoryRate() : 0.0);
				jobIssStnDt.setStoneValue(Math.round((tranDt.getCarat() * rate)*100.0)/100.0);
					
					jobIssStnDtService.save(jobIssStnDt);
					
					
				totCarat  +=jobIssStnDt.getCarat();
				totStoneVal += jobIssStnDt.getStoneValue();
				
				
				
			} //ending CostStnDt for loop
			
			
			
			//CostMetalDt

			List<TranMetal> tranMtLists = tranMetalService.findByBagMtAndDepartmentAndCurrStk(tranMt.getBagMt(),tranMt.getDepartment(), true);
		
			for(TranMetal tranMetal:tranMtLists ) {
				
				Double perGrmRate = tranMetal.getMetalWeight()> 0 ?   tranMetal.getMetalRate() :0.0;
				Double metalRate =tranMetal.getMetalWeight()> 0 ?   Math.round((perGrmRate/tranMetal.getPurityConv())*100.0)/100.0 :0.0;
				Double metalValue =tranMetal.getMetalWeight()> 0 ?  Math.round((tranMetal.getMetalWeight() * perGrmRate)*100.0)/100.0 :0.0;
				
				JobIssMetalDt jobIssMetalDt = new JobIssMetalDt();
				
				jobIssMetalDt.setJobIssDt(jobIssDt);
				jobIssMetalDt.setJobIssMt(jobIssMt);
				jobIssMetalDt.setBagMt(tranMetal.getBagMt());
				jobIssMetalDt.setPartNm(tranMetal.getPartNm());
				jobIssMetalDt.setPurity(tranMetal.getPurity());
				jobIssMetalDt.setDepartment(tranMetal.getDepartment());
				jobIssMetalDt.setColor(tranMetal.getColor());
				jobIssMetalDt.setMetalWeight(tranMetal.getMetalWeight());
				jobIssMetalDt.setMetalPcs(tranMetal.getMetalPcs());
				jobIssMetalDt.setCreateDate(new Date());
				jobIssMetalDt.setCreatedBy(principal.getName());
				jobIssMetalDt.setMainMetal(tranMetal.getMainMetal());					
				jobIssMetalDt.setPurityConv(tranMetal.getPurityConv());
				jobIssMetalDt.setMetalRate(metalRate);
				jobIssMetalDt.setPerGramRate(perGrmRate);
				jobIssMetalDt.setMetalValue(metalValue);
				
				jobIssMetalDtService.save(jobIssMetalDt);
				
				totMetalVal +=jobIssMetalDt.getMetalValue();
			}
			
			//new entry in CostCompDt
			
			
			//List<CompTran> compTrans = compTranService.findByBagMtAndDeactive(tranMt.getBagMt(), false);
			
			List<CompTran> compTrans = compTranService.getCompTranListForCosting(tranMt.getBagMt().getId());
			
			Double totComWt = 0.0;
			
			for(CompTran compTran : compTrans){
				
				if(compTran.getComponent().getChargable().equals(false)){
					continue;
				}
				
				
				List<CompTran> cTran = compTranService.findByBagMtAndPurityAndColorAndComponent(compTran.getBagMt(),compTran.getPurity(), compTran.getColor(), compTran.getComponent());
			
				
					Double balance = 0.0;
					Double compPcs = 0.0;
				
					for(CompTran comp:cTran){
						balance += comp.getBalance(); 
						compPcs += comp.getBalancePcs();
					}
					
					
					if(balance >= 0){
						continue;
					}
				
					JobIssCompDt jobIssCompDt = new JobIssCompDt();
					jobIssCompDt.setJobIssDt(jobIssDt);
					jobIssCompDt.setJobIssMt(jobIssMt);
					jobIssCompDt.setComponent(compTran.getComponent());
					jobIssCompDt.setPurity(compTran.getPurity());
					jobIssCompDt.setColor(compTran.getColor());
					jobIssCompDt.setOrderDt(tranMt.getOrderDt());
					jobIssCompDt.setBagMt(compTran.getBagMt());
					
					if(balance < 0){
						balance = -balance;
						compPcs = -compPcs;
					}
					
					//System.out.println(balance);
					
					jobIssCompDt.setMetalWt(Math.round(balance*1000.0)/1000.0);
					jobIssCompDt.setPurityConv(compTran.getPurityConv());
					jobIssCompDt.setCreatedBy(principal.getName());
					jobIssCompDt.setCreatedDate(new java.util.Date());
					jobIssCompDt.setCompPcs(compPcs);
				
					jobIssCompDtService.save(jobIssCompDt);
					
					totComWt += balance;
				
			} //ending the CostCompDt for loop
			
			
			//setting netWt of costDt
			
			totCarat=totCarat/5;
			
			jobIssDt.setNetWt(Math.round((jobIssDt.getGrossWt()-totCarat)*1000.0)/1000.0);
			jobIssDt.setMetalValue(totMetalVal);
			jobIssDt.setStoneValue(totStoneVal);
			jobIssDt.setFob(Math.round((totMetalVal+totStoneVal)*100.0)/100.0);
			jobIssDt.setFinalPrice(Math.round((totMetalVal+totStoneVal)*100.0)/100.0);
			
			jobIssDtService.save(jobIssDt);
			
			
			tranMt.setHoldFlg(true);
			tranMtService.save(tranMt);

			
			
		} // ending the main for loop
		
		retVal="1";
		return retVal;
	}

	@Override
	public Integer getMaxInvSrno() {
		QJobIssMt qJobIssMt = QJobIssMt.jobIssMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qJobIssMt)
			.where(qJobIssMt.deactive.eq(false).and(qJobIssMt.invDate.year().eq(date.get(Calendar.YEAR)))).list(qJobIssMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	@Override
	public String jobIssMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		Page<JobIssMt> jobIssMts = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		jobIssMts = findByInvNo(limit, offset, sort, order, search);

		sb.append("{\"total\":").append(jobIssMts.getTotalElements()).append(",\"rows\": [");

		for (JobIssMt jobIssMt : jobIssMts) {

			sb.append("{\"id\":\"")
			.append(jobIssMt.getId())
			.append("\",\"party\":\"")
					.append((jobIssMt.getParty() != null ? jobIssMt.getParty().getPartyCode() : ""))
					.append("\",\"invNo\":\"")
					.append(jobIssMt.getInvNo())
					.append("\",\"vouType\":\"")
					.append(jobIssMt.getDepartment().getName())
					.append("\",\"invDate\":\"")
					.append(jobIssMt.getInvDateStr())
					.append("\",\"expClose\":\"")
					.append((jobIssMt.getExpClose() != null ? (jobIssMt.getExpClose() ? "Closed" : "Open") : ""))
					.append("\",\"actionClose\":\"").append("<a href='javascript:doCostMtOpen(event,")
					.append(jobIssMt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Open Invoice</a>");

			sb.append("\",\"action1\":\"").append("<a href='/jewels/manufacturing/transactions/jobIssMt/edit/")
					.append(jobIssMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"").append("<a href='javascript:deleteJobIss(event,")
					.append(jobIssMt.getId())
					.append(");' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

}

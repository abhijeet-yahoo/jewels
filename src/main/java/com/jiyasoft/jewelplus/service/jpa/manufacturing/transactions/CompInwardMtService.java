package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCompInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICompInwardMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class CompInwardMtService implements ICompInwardMtService {

	@Autowired
	ICompInwardMtRepository compInwardMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ICompInwardDtService compInwardDtService;
	
	@Autowired
	private ICompTranService compTranService;

	@Override
	public List<CompInwardMt> findAll() {
		return compInwardMtRepository.findAll();
	}

	@Override
	public Page<CompInwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return compInwardMtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(CompInwardMt compInwardMt) {
		compInwardMtRepository.save(compInwardMt);

	}

	@Override
	public void delete(int id) {
		CompInwardMt compInwardMt = compInwardMtRepository.findOne(id);
		compInwardMt.setDeactive(true);
		compInwardMt.setDeactiveDt(new java.util.Date());
		compInwardMtRepository.save(compInwardMt);

	}

	@Override
	public Long count() {

		return compInwardMtRepository.count();
	}

	@Override
	public CompInwardMt findOne(int id) {

		return compInwardMtRepository.findOne(id);
	}



	@Override
	public Map<Integer, String> getCompInwardMtList() {

		Map<Integer, String> compInwardMtMap = new HashMap<Integer, String>();
		List<CompInwardMt> compInwardMtList = findActiveCompInwardMts();

		for (CompInwardMt compInwardMt : compInwardMtList) {
			compInwardMtMap.put(compInwardMt.getId(), compInwardMt.getInvNo());
		}
		return compInwardMtMap;
	}

	@Override
	public List<CompInwardMt> findActiveCompInwardMts() {
		QCompInwardMt qCompInwardMt = QCompInwardMt.compInwardMt;
		BooleanExpression expression = qCompInwardMt.deactive.eq(false);

		List<CompInwardMt> compInwardMtList = (List<CompInwardMt>) compInwardMtRepository
				.findAll(expression);

		return compInwardMtList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QCompInwardMt qCompInwardMt = QCompInwardMt.compInwardMt;
		BooleanExpression expression = qCompInwardMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qCompInwardMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("invno") && colValue != null) {
				expression = qCompInwardMt.deactive.eq(false).and(
						qCompInwardMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invno"))
					&& colValue != null) {
				expression = qCompInwardMt.invNo.like(colValue + "%");
			}
		}

		return compInwardMtRepository.count(expression);
	}

	@Override
	public Page<CompInwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QCompInwardMt qCompInwardMt = QCompInwardMt.compInwardMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qCompInwardMt.deactive.eq(false);
			} else {
				expression = qCompInwardMt.deactive.eq(false).and(
						qCompInwardMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qCompInwardMt.invNo.like(name + "%");
			}
		}

		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}


		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<CompInwardMt> compInwardMtList = (Page<CompInwardMt>) compInwardMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return compInwardMtList;
	}

	@Override
	public CompInwardMt findByUniqueId(Long uniqueId) {
		return compInwardMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public CompInwardMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return compInwardMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public List<Object[]> partyWiseAndDateWiseListing(String partyIds,
			String fromDate, String toDate) throws ParseException {
		
		if(fromDate != null && toDate != null && fromDate != "" && toDate != "" ){
			SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
			
			Date ordFromDate = dfInput.parse(fromDate);
			String frmDates = dfOutput.format(ordFromDate);
			
			Date ordToDate = dfInput.parse(toDate);
			String toDates = dfOutput.format(ordToDate);
			Query tempQuery;
			if(partyIds !=null && partyIds!="" ){
				 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from comprecmt  where  cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' and partyid in ('"+partyIds+"')  ");
			}else{
				 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from comprecmt  where  cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' ");	
			}
			
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			
			return list;
			
		}else{
			Query tempQuery = entityManager.createNativeQuery("select MtId,InvNo from comprecmt where  partyid in ('"+partyIds+"') and deactive = 0 ");
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			return list;
		}
	}

	@Override
	public Page<CompInwardMt> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QCompInwardMt qCompInwardMt = QCompInwardMt.compInwardMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qCompInwardMt.deactive.eq(false);
			}else{
				expression = qCompInwardMt.deactive.eq(false).and(qCompInwardMt.invNo.like("%" + search + "%").or(qCompInwardMt.party.name.like("%" + search + "%"))
						.or(qCompInwardMt.inwardType.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qCompInwardMt.invNo.like("%" + search + "%").or(qCompInwardMt.party.name.like("%" + search + "%")).or(qCompInwardMt.inwardType.name.like("%" + search + "%"));
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("invNo")) {
			sort = "invNo";
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
	
		Page<CompInwardMt> compInwardList =(Page<CompInwardMt>) compInwardMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return compInwardList;
		
	}

	@Override
	public String compInwDelete(int id, Principal principal) {
		
		String retVal ="-1";
		
		try {
			
			

			CompInwardMt compInwardMt = findOne(id);
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			
			Date cDate = compInwardMt.getInvDate();
			String dbDate = dateFormat.format(cDate);
			
			Date date = new Date();
			String curDate = dateFormat.format(date);
				
			
			if(dbDate.equals(curDate) || principal.getName().equalsIgnoreCase("Admin") ){
					
				List<CompInwardDt> compInwardDt = compInwardDtService.findByCompInwardMt(compInwardMt);
				List<CompTran> compTran;
				
				for (CompInwardDt comp : compInwardDt) {
					
					compInwardDtService.delete(comp.getId());

					compTran = compTranService.findByRefTranIdAndTranType(comp.getId(), "Inward");

					for (CompTran comTran : compTran) {
						compTranService.delete(comTran.getId());
					}
				}
				
				delete(id);
				
				retVal = "1";
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

	@Override
	public Integer getMaxInvSrno() {
		QCompInwardMt qCompInwardMt = QCompInwardMt.compInwardMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qCompInwardMt)
			.where(qCompInwardMt.deactive.eq(false).and(qCompInwardMt.invDate.year().eq(date.get(Calendar.YEAR)))).list(qCompInwardMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

}

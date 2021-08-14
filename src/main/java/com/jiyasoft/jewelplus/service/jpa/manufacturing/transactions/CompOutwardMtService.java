package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCompOutwardMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICompOutwardMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class CompOutwardMtService implements ICompOutwardMtService{

	@Autowired
	ICompOutwardMtRepository compOutwardMtRepository;
	
	@Autowired
	private ICompOutwardDtService compOutwardDtService;
	
	@Autowired
	private ICompTranService compTranService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<CompOutwardMt> findAll() {
		return compOutwardMtRepository.findAll();
	}

	@Override
	public Page<CompOutwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		
		return compOutwardMtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(CompOutwardMt compOutwardMt) {
		compOutwardMtRepository.save(compOutwardMt);
		
	}

	@Override
	public void delete(int id) {
		CompOutwardMt compOutwardMt = compOutwardMtRepository.findOne(id);
		compOutwardMt.setDeactive(true);
		compOutwardMt.setDeactiveDt(new java.util.Date());
		compOutwardMtRepository.save(compOutwardMt);
		
	}

	@Override
	public Long count() {
		return compOutwardMtRepository.count();
	}

	@Override
	public CompOutwardMt findOne(int id) {
		return compOutwardMtRepository.findOne(id);
	}

	@Override
	public CompOutwardMt findByInvNo(String invNo) {
		return compOutwardMtRepository.findByInvNo(invNo);
	}

	@Override
	public Map<Integer, String> getCompOutwardMtList() {
		Map<Integer, String> compOutwardMtMap = new HashMap<Integer, String>();
		List<CompOutwardMt> compOutwardMtList = findActiveCompOutwardMts();
		
		for(CompOutwardMt compOutwardMt : compOutwardMtList){
			compOutwardMtMap.put(compOutwardMt.getId(),
					compOutwardMt.getInvNo());
		}
		return compOutwardMtMap;
	}

	@Override
	public List<CompOutwardMt> findActiveCompOutwardMts() {
		QCompOutwardMt qCompOutwardMt = QCompOutwardMt.compOutwardMt;
		BooleanExpression expression = qCompOutwardMt.deactive.eq(false);
		
		List<CompOutwardMt> compOutwardMtList = (List<CompOutwardMt>) compOutwardMtRepository
				.findAll(expression);

		return compOutwardMtList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QCompOutwardMt qCompOutwardMt = QCompOutwardMt.compOutwardMt;
		BooleanExpression expression = qCompOutwardMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qCompOutwardMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("invno") && colValue != null) {
				expression = qCompOutwardMt.deactive.eq(false).and(
						qCompOutwardMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invno"))
					&& colValue != null) {
				expression = qCompOutwardMt.invNo.like(colValue + "%");
			}
		}

		return compOutwardMtRepository.count(expression);
	}

	@Override
	public Page<CompOutwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		QCompOutwardMt qCompOutwardMt = QCompOutwardMt.compOutwardMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qCompOutwardMt.deactive.eq(false);
			} else {
				expression = qCompOutwardMt.deactive.eq(false).and(
						qCompOutwardMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qCompOutwardMt.invNo.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<CompOutwardMt> compOutwardMtList = (Page<CompOutwardMt>) compOutwardMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return compOutwardMtList;
	}

	@Override
	public CompOutwardMt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return compOutwardMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public CompOutwardMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return compOutwardMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Page<CompOutwardMt> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		
		QCompOutwardMt qCompOutwardMt = QCompOutwardMt.compOutwardMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qCompOutwardMt.deactive.eq(false);
			}else{
				expression = qCompOutwardMt.deactive.eq(false).and(qCompOutwardMt.invNo.like("%" + search + "%").or(qCompOutwardMt.party.name.like("%" + search + "%"))
						.or(qCompOutwardMt.inwardType.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qCompOutwardMt.invNo.like("%" + search + "%").or(qCompOutwardMt.party.name.like("%" + search + "%")).or(qCompOutwardMt.inwardType.name.like("%" + search + "%"));
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
	
		Page<CompOutwardMt> compOutwardList =(Page<CompOutwardMt>) compOutwardMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return compOutwardList;
	}

	@Override
	public String compOutDelete(int id) {
		
		String retVal = "-1";
		try {
			
			delete(id);

			CompOutwardMt compOutwardMt = findOne(id);

			Integer compOutwardDtId = null;
			Integer compTranId = null;
			List<CompOutwardDt> compOutwardDt = compOutwardDtService
					.findByCompOutwardMt(compOutwardMt);
			List<CompTran> compTran;

			for (CompOutwardDt comp : compOutwardDt) {
				compOutwardDtId = comp.getId();
				compOutwardDtService.delete(compOutwardDtId);

				compTran = compTranService.findByRefTranIdAndTranType(
						compOutwardDtId, "Outward");

				for (CompTran comTran : compTran) {
					compTranId = comTran.getId();
					compTranService.delete(compTranId);
				}

			}
			retVal ="1";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
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
				 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from compretmt  where  cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' and partyid in ('"+partyIds+"')  ");
			}else{
				 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from compretmt  where  cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' ");	
			}
			
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			
			return list;
			
		}else{
			Query tempQuery = entityManager.createNativeQuery("select MtId,InvNo from compretmt where  partyid in ('"+partyIds+"') and deactive = 0 ");
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			return list;
		}
	}

	@Override
	public Integer getMaxCompOutInvSrno() {
		// TODO Auto-generated method stub
		QCompOutwardMt qCompOutwardMt = QCompOutwardMt.compOutwardMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qCompOutwardMt)
			.where(qCompOutwardMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qCompOutwardMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}
	

}

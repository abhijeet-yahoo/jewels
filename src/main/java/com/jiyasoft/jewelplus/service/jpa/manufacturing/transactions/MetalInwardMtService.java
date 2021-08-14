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

import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMetalInwardMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalInwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCompInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QMetalInwardMt;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MetalInwardMtService implements IMetalInwardMtService {

	@Autowired
	IMetalInwardMtRepository metalInwardMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IMetalInwardDtService metalInwardDtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	

	@Override
	public List<MetalInwardMt> findAll() {
		return metalInwardMtRepository.findAll();
	}

	@Override
	public Page<MetalInwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return metalInwardMtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

	}

	@Override
	public void save(MetalInwardMt metalInwardMt) {
		metalInwardMtRepository.save(metalInwardMt);
	}

	@Override
	public void delete(int id) {
		MetalInwardMt metalInwardMt = metalInwardMtRepository.findOne(id);
		metalInwardMt.setDeactive(true);
		metalInwardMt.setDeactiveDt(new java.util.Date());
		metalInwardMtRepository.save(metalInwardMt);

	}

	@Override
	public Long count() {

		return metalInwardMtRepository.count();
	}

	@Override
	public MetalInwardMt findOne(int id) {
		return metalInwardMtRepository.findOne(id);

	}

	

	@Override
	public Map<Integer, String> getMetalInwardMtList() {
		Map<Integer, String> metalInwardMtMap = new HashMap<Integer, String>();
		List<MetalInwardMt> metalInwardMtList = findActiveMetalInwardMts();

		for (MetalInwardMt metalInwardMt : metalInwardMtList) {
			metalInwardMtMap.put(metalInwardMt.getId(),
					metalInwardMt.getInvNo());
		}

		return metalInwardMtMap;
	}

	@Override
	public List<MetalInwardMt> findActiveMetalInwardMts() {
		QMetalInwardMt qMetalInwardMt = QMetalInwardMt.metalInwardMt;
		BooleanExpression expression = qMetalInwardMt.deactive.eq(false);

		List<MetalInwardMt> metalInwardMtList = (List<MetalInwardMt>) metalInwardMtRepository
				.findAll(expression);

		return metalInwardMtList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QMetalInwardMt qMetalInwardMt = QMetalInwardMt.metalInwardMt;
		BooleanExpression expression = qMetalInwardMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qMetalInwardMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qMetalInwardMt.deactive.eq(false).and(
						qMetalInwardMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qMetalInwardMt.invNo.like(colValue + "%");
			}
		}

		return metalInwardMtRepository.count(expression);
	}

	@Override
	public Page<MetalInwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QMetalInwardMt qMetalInwardMt = QMetalInwardMt.metalInwardMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qMetalInwardMt.deactive.eq(false);
			} else {
				expression = qMetalInwardMt.deactive.eq(false).and(
						qMetalInwardMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qMetalInwardMt.invNo.like(name + "%");
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

		Page<MetalInwardMt> metalInwardMtList = (Page<MetalInwardMt>) metalInwardMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
	
		return metalInwardMtList;	
	}

	@Override
	public MetalInwardMt findByUniqueId(Long uniqueId) {
		return metalInwardMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public MetalInwardMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return metalInwardMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}
	
	
	@Override
	public List<Object[]>  partyWiseAndDateWiseListing(String partyIds,
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
				 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from mtlrecmt  where  cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' and partyid in ('"+partyIds+"')  ");
			}else{
				 tempQuery = entityManager.createNativeQuery("select MtId,InvNo from mtlrecmt  where  cast(invdate as date) between '"+frmDates+"' and '"+toDates+"' ");	
			}
			
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			
			return list;
			
		}else{
			Query tempQuery = entityManager.createNativeQuery("select MtId,InvNo from mtlrecmt where  partyid in ('"+partyIds+"') and deactive = 0 ");
			@SuppressWarnings("unchecked")
			List<Object[]> list= tempQuery.getResultList();
			
			return list;
		}
		
	}

	@Override
	public Page<MetalInwardMt> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		// TODO Auto-generated method stub
		QMetalInwardMt qMetalInwardMt = QMetalInwardMt.metalInwardMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qMetalInwardMt.deactive.eq(false);
			}else{
				expression = qMetalInwardMt.deactive.eq(false).and(qMetalInwardMt.invNo.like("%" + search + "%").or(qMetalInwardMt.party.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qMetalInwardMt.invNo.like("%" + search + "%").or(qMetalInwardMt.party.name.like("%" + search + "%"));
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
	
		Page<MetalInwardMt> metalInwardList =(Page<MetalInwardMt>) metalInwardMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return metalInwardList;
	}

	@Override
	public String metalInwDelete(int id) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
		
		try {
			
			delete(id);

			MetalInwardMt metalInwardMt = findOne(id);

			Integer metalInwardDtId = null;
			Integer metalTranId = null;
			List<MetalInwardDt> metalInwardDt = metalInwardDtService.findByMetalInwardMt(metalInwardMt);
			List<MetalTran> metalTran;

			for (MetalInwardDt metal : metalInwardDt) {
				metalInwardDtId = metal.getId();
				metalInwardDtService.delete(metalInwardDtId);

				metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(metalInwardDtId, "Inward",false);

				for (MetalTran metTran : metalTran) {
					metalTranId = metTran.getId();
					metalTranService.delete(metalTranId);
				}
					
			}
			
			retVal ="1";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		return retVal;
	}

	@Override
	public Integer getMaxInvSrno() {
		QMetalInwardMt qMetalInwardMt = QMetalInwardMt.metalInwardMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qMetalInwardMt)
			.where(qMetalInwardMt.deactive.eq(false).and(qMetalInwardMt.invDate.year().eq(date.get(Calendar.YEAR)))).list(qMetalInwardMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

}

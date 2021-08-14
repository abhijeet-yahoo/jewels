package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QMetalOutwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneOutwardMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IMetalOutwardMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalOutwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalOutwardMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MetalOutwardMtService implements IMetalOutwardMtService {
	
	@Autowired
	IMetalOutwardMtRepository metalOutwardMtRepository;
	
	@Autowired
	private IMetalOutwardDtService metalOutwardDtService;
	
	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<MetalOutwardMt> findAll() {
		return metalOutwardMtRepository.findAll();
	}

	@Override
	public Page<MetalOutwardMt> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return metalOutwardMtRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));

	}

	@Override
	public void save(MetalOutwardMt metalOutwardMt) {
		metalOutwardMtRepository.save(metalOutwardMt);
	}

	@Override
	public void delete(int id) {
		MetalOutwardMt metalOutwardMt = metalOutwardMtRepository.findOne(id);
		metalOutwardMt.setDeactive(true);
		metalOutwardMt.setDeactiveDt(new java.util.Date());
		metalOutwardMtRepository.save(metalOutwardMt);

	}

	@Override
	public Long count() {

		return metalOutwardMtRepository.count();
	}

	@Override
	public MetalOutwardMt findOne(int id) {
		return metalOutwardMtRepository.findOne(id);

	}

	@Override
	public MetalOutwardMt findByInvNo(String invNo) {
		return metalOutwardMtRepository.findByInvNo(invNo);
	}

	@Override
	public Map<Integer, String> getMetalOutwardMtList() {
		Map<Integer, String> metalOutwardMtMap = new HashMap<Integer, String>();
		List<MetalOutwardMt> metalOutwardMtList = findActiveMetalOutwardMts();

		for (MetalOutwardMt metalOutwardMt : metalOutwardMtList) {
			metalOutwardMtMap.put(metalOutwardMt.getId(),
					metalOutwardMt.getInvNo());
		}

		return metalOutwardMtMap;
	}

	@Override
	public List<MetalOutwardMt> findActiveMetalOutwardMts() {
		QMetalOutwardMt qMetalOutwardMt = QMetalOutwardMt.metalOutwardMt;
		BooleanExpression expression = qMetalOutwardMt.deactive.eq(false);

		List<MetalOutwardMt> metalOutwardMtList = (List<MetalOutwardMt>) metalOutwardMtRepository
				.findAll(expression);

		return metalOutwardMtList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QMetalOutwardMt qMetalOutwardMt = QMetalOutwardMt.metalOutwardMt;
		BooleanExpression expression = qMetalOutwardMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qMetalOutwardMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("invno") && colValue != null) {
				expression = qMetalOutwardMt.deactive.eq(false).and(
						qMetalOutwardMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invno"))
					&& colValue != null) {
				expression = qMetalOutwardMt.invNo.like(colValue + "%");
			}
		}

		return metalOutwardMtRepository.count(expression);
	}

	@Override
	public Page<MetalOutwardMt> findByInvNo(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QMetalOutwardMt qMetalOutwardMt = QMetalOutwardMt.metalOutwardMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qMetalOutwardMt.deactive.eq(false);
			} else {
				expression = qMetalOutwardMt.deactive.eq(false).and(
						qMetalOutwardMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qMetalOutwardMt.invNo.like(name + "%");
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

		Page<MetalOutwardMt> metalOutwardMtList = (Page<MetalOutwardMt>) metalOutwardMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return metalOutwardMtList;
	}

	@Override
	public MetalOutwardMt findById(Integer id) {
		
		return metalOutwardMtRepository.findById(id);
	}

	@Override
	public MetalOutwardMt findByUniqueId(Long uniqueId) {
		// TODO Auto-generated method stub
		return metalOutwardMtRepository.findByUniqueId(uniqueId);
	}

	@Override
	public MetalOutwardMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return metalOutwardMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Page<MetalOutwardMt> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QMetalOutwardMt qMetalOutwardMt = QMetalOutwardMt.metalOutwardMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qMetalOutwardMt.deactive.eq(false);
			}else{
				expression = qMetalOutwardMt.deactive.eq(false).and(qMetalOutwardMt.invNo.like("%" + search + "%").or(qMetalOutwardMt.party.name.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression =qMetalOutwardMt.invNo.like("%" + search + "%").or(qMetalOutwardMt.party.name.like("%" + search + "%"));
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
	
		Page<MetalOutwardMt> metalOutwardList =(Page<MetalOutwardMt>) metalOutwardMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return metalOutwardList;
	}

	@Override
	public String metalOutDelete(int id) {
		
		String retVal ="-1";
		
		try {
			

			delete(id);
			
			MetalOutwardMt metalOutwardMt=findOne(id);
			
			Integer metalOutwardDtId= null;
			Integer metalTranId=null;
			List<MetalOutwardDt> metalOutwardDt=metalOutwardDtService.findByMetalOutwardMt(metalOutwardMt);
			List<MetalTran> metalTran;
			
			for(MetalOutwardDt metal:metalOutwardDt)
			{
				metalOutwardDtId = metal.getId();
				metalOutwardDtService.delete(metalOutwardDtId);
				
				metalTran=metalTranService.findByRefTranIdAndTranTypeAndDeactive(metalOutwardDtId, "Outward",false);
						
				for(MetalTran metTran:metalTran)
				{
					metalTranId = metTran.getId();
					metalTranService.delete(metalTranId);				
				}
				
				
			}
			retVal ="1";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QMetalOutwardMt qMetalOutwardMt = QMetalOutwardMt.metalOutwardMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qMetalOutwardMt)
			.where(qMetalOutwardMt.invDate.year().eq(date.get(Calendar.YEAR))).list(qMetalOutwardMt.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}


}

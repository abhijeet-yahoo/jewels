package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QBagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QFgIssMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IFgIssMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgIssMtService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class FgIssMtService implements IFgIssMtService {

	@Autowired
	private IFgIssMtRepository fgIssMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public List<FgIssMt> findAll() {
		// TODO Auto-generated method stub
		return fgIssMtRepository.findAll();
	}

	@Override
	public Page<FgIssMt> findAll(Integer limit, Integer offset, String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		return fgIssMtRepository
				.findAll(new PageRequest(page,limit,(order
						.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC ), sort));
	}

	@Override
	public void save(FgIssMt fgIssMt) {
		// TODO Auto-generated method stub
		fgIssMtRepository.save(fgIssMt);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		FgIssMt fgIssMt = fgIssMtRepository.findOne(id);
		fgIssMt.setDeactive(true);
		fgIssMt.setDeactiveDt(new java.util.Date());
		fgIssMtRepository.save(fgIssMt);
	}

	
	@Override
	public FgIssMt findOne(int id) {
		// TODO Auto-generated method stub
		return fgIssMtRepository.findOne(id);
	}

	@Override
	public FgIssMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		// TODO Auto-generated method stub
		return fgIssMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}



	

	@Override
	public Page<FgIssMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name,
			Boolean onlyActive) {
		QFgIssMt qFgIssMt = QFgIssMt.fgIssMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qFgIssMt.deactive.eq(false);
			} else {
				expression = qFgIssMt.deactive.eq(false).and(
						qFgIssMt.invNo.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qFgIssMt.invNo.like(name + "%");
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

		Page<FgIssMt> fgIssMtList = (Page<FgIssMt>) fgIssMtRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return fgIssMtList;
	}

	
	

	@Override
	public Page<FgIssMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		QFgIssMt qFgIssMt = QFgIssMt.fgIssMt;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qFgIssMt.deactive.eq(false);
			}else{
				expression = qFgIssMt.deactive.eq(false).and(qFgIssMt.invNo.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression =qFgIssMt.invNo.like("%" + search + "%");
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
		}else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
	
		Page<FgIssMt> fgIssMtList =(Page<FgIssMt>) fgIssMtRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return fgIssMtList;
	}

	@Override
	public Integer getMaxInvSrno() {
		// TODO Auto-generated method stub
		QFgIssMt qFgIssMt = QFgIssMt.fgIssMt;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qFgIssMt)
			.where(qFgIssMt.deactive.eq(false).and(qFgIssMt.invDate.year().eq(date.get(Calendar.YEAR)))).list(qFgIssMt.invSrNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

	
	

}

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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStoneConversionRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneConversionService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StoneConversionService implements IStoneConversionService {
	
	@Autowired
	private IStoneConversionRepository stoneConversionRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Page<StoneConversion> findAll(Integer limit, Integer offset, String sort, String order, String search) {
		QStoneConversion qStoneConversion = QStoneConversion.stoneConversion;
		BooleanExpression expression=null;
		
			if (search != null) {
		
				expression = qStoneConversion.invNo.like("%" + search + "%")
						.or(qStoneConversion.quality.name.like("%" + search + "%"));
				
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
	
		Page<StoneConversion> stoneConvList =(Page<StoneConversion>) stoneConversionRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.DESC: Direction.ASC),sort));
		

	
		return stoneConvList;
	}

	@Override
	public void save(StoneConversion stoneConversion) {
		// TODO Auto-generated method stub
		stoneConversionRepository.save(stoneConversion);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		stoneConversionRepository.delete(id);
		
	}
	
	
	@Override
	public Integer getMaxInvSrno() {
		QStoneConversion qStoneConversion = QStoneConversion.stoneConversion;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		Calendar date = new GregorianCalendar();
		
		List<Integer> maxSrno = query
			.from(qStoneConversion)
			.where(qStoneConversion.invDate.year().eq(date.get(Calendar.YEAR))).list(qStoneConversion.srNo.max());
		
		for (Integer srno : maxSrno) {
			retVal = (srno == null ? 0 : srno);
		}

		
		
		return retVal;
	}

}

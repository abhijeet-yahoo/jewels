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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QQuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IQuotMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class QuotMtService implements IQuotMtService {
	
	@Autowired
	private IQuotMtRepository quotMtRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IPartyService partyService;

	@Override
	public List<QuotMt> findAll() {
		return quotMtRepository.findAll();
	}

	@Override
	public Page<QuotMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return quotMtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(QuotMt quotMt) {
		quotMtRepository.save(quotMt);
	}

	@Override
	public void delete(int id) {
		QuotMt quotMt = quotMtRepository.findOne(id);
		quotMt.setDeactive(true);
		quotMt.setDeactiveDt(new java.util.Date());
		quotMtRepository.save(quotMt);
		
	}

	@Override
	public Long count() {
		return quotMtRepository.count();
	}

	@Override
	public QuotMt findOne(int id) {
		return quotMtRepository.findOne(id);
	}

	@Override
	public QuotMt findByInvNoAndDeactive(String invNo, Boolean deactive) {
		return quotMtRepository.findByInvNoAndDeactive(invNo, deactive);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QQuotMt qQuotMt = QQuotMt.quotMt;
		BooleanExpression expression = qQuotMt.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qQuotMt.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("invNo") && colValue != null) {
				expression = qQuotMt.deactive.eq(false).and(
						qQuotMt.invNo.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("invNo"))
					&& colValue != null) {

				expression = qQuotMt.invNo.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return quotMtRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return quotMtRepository.count();
			}
		}

		return quotMtRepository.count(expression);
	}

	@Override
	public Page<QuotMt> findByInvNo(Integer limit, Integer offset, String sort,
			String order, String invNo, Boolean onlyActive) {
		
		QQuotMt qQuotMt = QQuotMt.quotMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (invNo == null) {
				expression = qQuotMt.deactive.eq(false);
			} else {
				expression = qQuotMt.deactive.eq(false).and(
						qQuotMt.invNo.like(invNo + "%"));
			}
		} else {
			if (invNo != null) {
				expression = qQuotMt.invNo.like(invNo + "%");
			}
		}

		//int page = (offset == 0 ? 0 : (offset / limit));
		
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

		Page<QuotMt> quotMtList = (Page<QuotMt>) quotMtRepository.findAll(expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return quotMtList;
	}

	@Override
	public QuotMt findByUniqueId(Long uniqueId) {
		return quotMtRepository.findByUniqueId(uniqueId);
	}


	@Override
	public Long maxBySrNo() {
		
		QQuotMt qQuotMt = QQuotMt.quotMt;
		JPAQuery query = new JPAQuery(entityManager);
		Calendar date = new GregorianCalendar();
		
		Long retVal = -1L;
		
		List<Long> maxSrno = query.from(qQuotMt).
					where(qQuotMt.deactive.eq(false).and(qQuotMt.createdDate.year().eq(date.get(Calendar.YEAR)))).list(qQuotMt.srNo.max());
		
		
		for(Long max:maxSrno){
			retVal = max;
		}
		

		return retVal;
	}

	@Override
	public Long countAll(String colValue) {
		QQuotMt qQuotMt = QQuotMt.quotMt;
		BooleanExpression expression = qQuotMt.deactive.eq(false);
		
			if(colValue!=null && colValue !="" ){
					
				expression = qQuotMt.deactive.eq(false).and(qQuotMt.invNo.like(colValue + "%"));
			}
		
			
		 return quotMtRepository.count(expression);
	}

	@Override
	public Page<QuotMt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name) {
		QQuotMt qQuotMt = QQuotMt.quotMt;
		BooleanExpression expression = qQuotMt.deactive.eq(false);

		if(name !=null && name !=""){
			expression = qQuotMt.deactive.eq(false).and(qQuotMt.invNo.like(name + "%").or(qQuotMt.party.name.like(name + "%").or(qQuotMt.refNo.like(name + "%"))));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<QuotMt> quotMtList = (Page<QuotMt>) quotMtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC
								: Direction.ASC), sort));

		
		
		return quotMtList;
	}
	
	
	
	@Override
	public Page<QuotMt> findByParty(Integer limit, Integer offset, String sort, String order, String search, Boolean onlyActive,String partyName) {
		
		QQuotMt qQuotMt = QQuotMt.quotMt;
		BooleanExpression expression = null;

		if (onlyActive) {
			if ( search == null) {
				expression = qQuotMt.deactive.eq(false).and(
						qQuotMt.party.name.like(partyName + "%"));
			} else {
				expression = qQuotMt.deactive.eq(false).and(
						qQuotMt.party.name.like(partyName + "%")).and(qQuotMt.invNo.like("%" + search + "%"));
			}
			
		}else {
				if(search != null){

					expression = qQuotMt.party.name.like(partyName + "%").and(qQuotMt.invNo.like("%" + search + "%"));
				}else {
					expression=null;
					
					}
	}

		//int page = (offset == 0 ? 0 : (offset / limit));
		
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

		Page<QuotMt> quotMtList = (Page<QuotMt>) quotMtRepository.findAll(expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		return quotMtList;
	}









@Override
	public long countByParty(String partyName,String colName, String colValue, Boolean onlyActive) {
		QQuotMt qQuotMt = QQuotMt.quotMt;
		BooleanExpression expression = qQuotMt.deactive.eq(false);
		
		if (onlyActive) {
			if ( colValue == null) {
				expression = qQuotMt.deactive.eq(false).and(
						qQuotMt.party.name.like(partyName + "%"));
			} else {
				expression = qQuotMt.deactive.eq(false).and(
						qQuotMt.party.name.like(partyName + "%")).and(qQuotMt.invNo.like("%" + colValue + "%"));
			}
			
		}else {
				if(colValue != null){

					expression = qQuotMt.party.name.like(partyName + "%").and(qQuotMt.invNo.like("%" + colValue + "%"));
				}else {
					expression=null;
					
					}
	}
		return quotMtRepository.count(expression);
	
	}



@Override
public Page<QuotMt> findByInvNoListByParty(Integer limit, Integer offset, String sort,
		String order, String invNo, Boolean onlyActive,String partyNm) {
	
	QQuotMt qQuotMt = QQuotMt.quotMt;
	BooleanBuilder expression = new BooleanBuilder();

	if (onlyActive) {
		if (invNo == null) {
			expression.and(qQuotMt.deactive.eq(false));
		} else {
			
			if(partyNm != null && !partyNm.trim().isEmpty()){
				
				Party party =partyService.findByPartyCodeAndDeactive(partyNm, false);
				
				expression.and(qQuotMt.deactive.eq(false).and(
						qQuotMt.invNo.like(invNo + "%")).and(qQuotMt.party.id.eq(party.getId())));
				
			}else{
				expression.and(qQuotMt.deactive.eq(false).and(
						qQuotMt.invNo.like(invNo + "%")));
			}
			
		}
	} else {
		if (invNo != null) {
			expression.and(qQuotMt.invNo.like(invNo + "%"));
		}
	}

	//int page = (offset == 0 ? 0 : (offset / limit));
	
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

	Page<QuotMt> quotMtList = (Page<QuotMt>) quotMtRepository.findAll(expression,
			new PageRequest(page, limit,
					(order.equalsIgnoreCase("asc") ? Direction.ASC
							: Direction.DESC), sort));

	return quotMtList;
}


@Override
public QuotMt findByRefNoAndDeactive(String refNo, Boolean deactive) {
	// TODO Auto-generated method stub
	return quotMtRepository.findByRefNoAndDeactive(refNo, deactive);
}

@Override
public QuotMt findByPartyAndMasterFlgAndDeactive(Party party, Boolean masterFlg, Boolean deactive) {
	// TODO Auto-generated method stub
	return quotMtRepository.findByPartyAndMasterFlgAndDeactive(party, masterFlg, deactive);
}


	
	
}

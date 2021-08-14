package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmailConcept;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QEmailConcept;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IEmialConceptRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmailConceptService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class EmailConceptService implements IEmailConceptService{
	
	@Autowired
	private IEmialConceptRepository emailConceptRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<EmailConcept> findAll() {		
		return emailConceptRepository.findAll();
	}

	@Override
	public Page<EmailConcept> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return emailConceptRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));	}

	@Override
	public void save(EmailConcept emailConcept) {
		emailConceptRepository.save(emailConcept);
		
	}

	@Override
	public void delete(int id) {
		
		
		emailConceptRepository.delete(id);
		
	}

	@Override
	public Long count() {
		return emailConceptRepository.count();
	}

	@Override
	public EmailConcept findOne(int id) {
		return emailConceptRepository.findOne(id);
	}

	@Override
	public EmailConcept findByName(String name) {
	
		return emailConceptRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getEmailConceptList() {
		
		Map<Integer, String> emailConceptMap = new HashMap<Integer, String>();
		List<EmailConcept> emailConceptList = findActiveEmialConcept();

		for (EmailConcept emailConcept : emailConceptList) {
			emailConceptMap.put(emailConcept.getId(), emailConcept.getEmailConIdMax() + " - " + emailConcept.getName()+" - "+emailConcept.getVersion());
		}

		return emailConceptMap;
	}

	@Override
	public List<EmailConcept> findActiveEmialConcept() {
		
		QEmailConcept qEmailConcept = QEmailConcept.emailConcept;
		BooleanExpression expression = qEmailConcept.deactive.eq(false);

		List<EmailConcept> emailConceptList = (List<EmailConcept>) emailConceptRepository
				.findAll(expression);

		return emailConceptList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QEmailConcept qEmailConcept = QEmailConcept.emailConcept;
		BooleanExpression expression = qEmailConcept.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qEmailConcept.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qEmailConcept.deactive.eq(false).and(
						qEmailConcept.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {

				expression = qEmailConcept.name.like(colValue + "%");
			} else if (colName != null && colValue == null) {
				return emailConceptRepository.count();
			} else if (colName != null && colValue != null) {
				return 0L;
			} else {
				return emailConceptRepository.count();
			}
		}

		return emailConceptRepository.count(expression);
	}
	
	
	
	@Override
	public Long countAll(String colValue) {
		QEmailConcept qEmailConcept = QEmailConcept.emailConcept;
		BooleanExpression expression = qEmailConcept.deactive.eq(false);
		
			if(colValue!=null && colValue !="" ){
					
				expression = qEmailConcept.name.like(colValue + "%").or(qEmailConcept.id.like(colValue+"%")).or(qEmailConcept.version.like(colValue+"%"));
			}
			
		 return emailConceptRepository.count(expression);
	}
	
	
	
	

	@Override
	public Page<EmailConcept> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		QEmailConcept qEmailConcept = QEmailConcept.emailConcept;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qEmailConcept.deactive.eq(false);
			} else {
				expression = qEmailConcept.deactive.eq(false).and(
						qEmailConcept.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qEmailConcept.name.like(name + "%");
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

		Page<EmailConcept> emailConceptList = (Page<EmailConcept>) emailConceptRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return emailConceptList;
	}
	
	
	
	
	
	public Page<EmailConcept> searchAll(Integer limit, Integer offset,
			String sort, String order, String name) {
		QEmailConcept qEmailConcept = QEmailConcept.emailConcept;
		BooleanExpression expression =qEmailConcept.deactive.eq(false);

		if(name !=null && name !=""){
		expression = qEmailConcept.name.like(name + "%").or(qEmailConcept.id.like(name+"%")).or(qEmailConcept.version.like(name+"%"));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<EmailConcept> emailConceptList = (Page<EmailConcept>) emailConceptRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return emailConceptList;
	}
	
	

	@Override
	public Integer maxSrNo() {
				
		QEmailConcept qEmailConcept=QEmailConcept.emailConcept;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = -1;
		
		List<Integer> maxSrNo = query
				.from(qEmailConcept)
				.list(qEmailConcept.emailConIdMax.max());
		
		for(Integer srNo : maxSrNo)
		{
			retVal = (srNo == null ? 0 : srNo);
		}
				
		return retVal;
	}
	
	

}

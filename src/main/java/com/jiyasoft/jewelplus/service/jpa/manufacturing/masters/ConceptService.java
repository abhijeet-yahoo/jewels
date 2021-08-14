package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QConcept;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IConceptRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IConceptService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ConceptService implements IConceptService {

	@Autowired
	private IConceptRepository conceptRepository;

	@Override
	public List<Concept> findAll() {
		return conceptRepository.findAll();
	}

	@Override
	public Page<Concept> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		limit = (limit == null ? 10 : limit);
		offset = (offset == null ? 0 : offset);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return conceptRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Concept concept) {
		conceptRepository.save(concept);
	}

	@Override
	public void delete(int id) {
		Concept concept = conceptRepository.findOne(id);
		concept.setDeactive(true);
		concept.setDeactiveDt(new java.util.Date());
		conceptRepository.save(concept);
	}

	@Override
	public Long count() {
		return conceptRepository.count();
	}

	@Override
	public Concept findOne(int id) {
		return conceptRepository.findOne(id);
	}

	@Override
	public Concept findByName(String conceptName) {
		return conceptRepository.findByName(conceptName);
	}

	@Override
	public Map<Integer, String> getConceptList() {
		Map<Integer, String> conceptMap = new LinkedHashMap<Integer, String>();
		Page<Concept> conceptList = findActiveConceptsSortByName();

		for (Concept concept : conceptList) {
			conceptMap.put(concept.getId(), concept.getName());
		}

		return conceptMap;
	}

	@Override
	public List<Concept> findActiveConcepts() {
		QConcept qConcept = QConcept.concept;
		BooleanExpression expression = qConcept.deactive.eq(false);

		List<Concept> conceptList = (List<Concept>) conceptRepository
				.findAll(expression);

		return conceptList;
	}

	@Override
	public Page<Concept> findActiveConceptsSortByName() {

		QConcept qConcept = QConcept.concept;
		BooleanExpression expression = qConcept.deactive.eq(false);

		Page<Concept> conceptList = conceptRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return conceptList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QConcept qConcept = QConcept.concept;
		BooleanExpression expression = qConcept.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qConcept.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qConcept.deactive.eq(false).and(
						qConcept.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qConcept.name.like(colValue + "%");
			}
		}

		return conceptRepository.count(expression);
	}

	@Override
	public Page<Concept> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QConcept qConcept = QConcept.concept;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qConcept.deactive.eq(false);
			} else {
				expression = qConcept.deactive.eq(false).and(
						qConcept.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qConcept.name.like(name + "%");
			}
		}

		// new addition
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}
		// new addition

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<Concept> conceptList = (Page<Concept>) conceptRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return conceptList;
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		QConcept qConcept = QConcept.concept;
		BooleanExpression expression = null;		
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qConcept.deactive.eq(false);			
				}else{
					expression = qConcept.deactive.eq(false).and(qConcept.name.like("%" + colValue + "%"));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qConcept.name.like("%" + colValue + "%");
				}
			}
			
			return conceptRepository.count(expression);
	}

	@Override
	public Page<Concept> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {

		QConcept qConcept = QConcept.concept;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qConcept.deactive.eq(false);
			}else{
				expression = qConcept.deactive.eq(false).and(qConcept.name.like("%" + search + "%"));
			}
		}else{
			if (search != null) {
				expression = qConcept.name.like("%" + search + "%");
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
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("categCode")) {
			sort = "categCode";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<Concept> conceptMastList =(Page<Concept>) conceptRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return conceptMastList;
	}



}

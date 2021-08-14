	package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QFindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourCharge;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IFindingRateMastRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class FindingRateMastService implements IFindingRateMastService{
	
	@Autowired
	private IFindingRateMastRepository findingRateMastRepository;

	@Override
	public List<FindingRateMast> findAll() {
		
		return findingRateMastRepository.findAll();
	}

	@Override
	public Page<FindingRateMast> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		
		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		}

		return findingRateMastRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(FindingRateMast findingRateMast) {

      findingRateMastRepository.save(findingRateMast);
		
	}

	@Override
	public void delete(int id) {
		FindingRateMast findingRateMast = findingRateMastRepository.findOne(id);
		findingRateMast.setDeactive(true);
		findingRateMast.setDeactiveDt(new java.util.Date());
		findingRateMastRepository.save(findingRateMast);
		
	}

	@Override
	public Long count() {

		return findingRateMastRepository.count();
	}
	
	@Override
	public FindingRateMast findOne(int id) {
	
		return findingRateMastRepository.findOne(id);
	}

	
	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QFindingRateMast qFindingRateMast = QFindingRateMast.findingRateMast; 
		BooleanExpression expression = null;

	if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qFindingRateMast.deactive.eq(false);
			} else if ((colName == null || colName.equalsIgnoreCase("name")) && colValue != null) {
				expression = qFindingRateMast.deactive.eq(false)
						.and(qFindingRateMast.party.partyCode.like("%" +colValue + "%").or(qFindingRateMast.purity.name.like("%" +colValue + "%")
						.or(qFindingRateMast.component.name.like("%" +colValue + "%").or(qFindingRateMast.rate.like("%" +colValue + "%")))));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name")) && colValue != null) {
				expression = qFindingRateMast.party.partyCode.like("%" +colValue + "%").or(qFindingRateMast.purity.name.like("%" +colValue + "%")
						.or(qFindingRateMast.component.name.like("%" +colValue + "%").or(qFindingRateMast.rate.like("%" +colValue + "%"))));
				}
			}
		

		return findingRateMastRepository.count(expression);
	}

	@Override
	public Page<FindingRateMast> findByPartyAndPurityAndComponentAndRate(
			Integer limit, Integer offset, String sort, String order,
			String name, Boolean onlyActive) {
		
		QFindingRateMast qFindingRateMast = QFindingRateMast.findingRateMast;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (name == null) {
				expression = qFindingRateMast.deactive.eq(false);
			} else {
				expression = qFindingRateMast.deactive.eq(false)
						.and(qFindingRateMast.party.partyCode.like("%" +name + "%").or(qFindingRateMast.purity.name.like("%" +name + "%")
						.or(qFindingRateMast.component.name.like("%" +name + "%").or(qFindingRateMast.rate.like("%" +name + "%")))));
			}
			
		} else {
			if (name != null) {
				expression = qFindingRateMast.party.partyCode.like("%" +name + "%").or(qFindingRateMast.purity.name.like("%" +name + "%")
						.or(qFindingRateMast.component.name.like("%" +name + "%").or(qFindingRateMast.rate.like("%" +name + "%"))));
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

		Page<FindingRateMast> findingRateMastList = (Page<FindingRateMast>) findingRateMastRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return findingRateMastList;
	}


	@Override
	public FindingRateMast findByComponentAndPartyAndPurityAndDeactive(
			Component component, Party party, Purity purity, 
			Boolean deactive) {
		
		return findingRateMastRepository.findByComponentAndPartyAndPurityAndDeactive(component, party, purity, deactive);
	}

	@Override
	public FindingRateMast findByPartyAndComponentAndPurityAndPerGramRateAndPerPcRateAndDeactive(
			Party party, Component component, Purity purity,
			Double perGramRate, Double perPcRate, Boolean deactive) {
		
		return findingRateMastRepository.findByPartyAndComponentAndPurityAndPerGramRateAndPerPcRateAndDeactive(party, component, purity, perGramRate, perPcRate, deactive);
	}

	
	@Override
	public FindingRateMast findByPartyAndComponentAndPurityAndDeactive(
			Party party, Component component, Purity purity, Boolean deactive) {
	
		return findingRateMastRepository.findByPartyAndComponentAndPurityAndDeactive(party, component, purity, deactive);
	}

	@Override
	public Page<FindingRateMast> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		
		QFindingRateMast qFindingRateMast = QFindingRateMast.findingRateMast;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qFindingRateMast.deactive.eq(false);
			} else {
				expression = qFindingRateMast.deactive.eq(false).and(qFindingRateMast.party.name.like("%" + search + "%").or(qFindingRateMast.purity.name.like("%" + search+ "%").or(qFindingRateMast.component.name.like("%" + search+ "%"))));
			}
		} else {
			if (search != null) {
				expression = qFindingRateMast.party.name.like("%" + search + "%").or(qFindingRateMast.purity.name.like("%" + search+ "%").or(qFindingRateMast.component.name.like("%" + search+ "%")));
			}
		}

		if (limit == null) {
			limit = 1000;
		}
		if (offset == null) {
			offset = 0;
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party";
		} else if (sort.equalsIgnoreCase("purity")) {
			sort = "purity";
		}
		else if (sort.equalsIgnoreCase("component")) {
			sort = "component";
		}
		Page<FindingRateMast> findingRateMastList = (Page<FindingRateMast>) findingRateMastRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		return findingRateMastList;
		
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QFindingRateMast qFindingRateMast = QFindingRateMast.findingRateMast;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qFindingRateMast.deactive.eq(false);
			} else {
				expression = qFindingRateMast.deactive.eq(false).and(qFindingRateMast.party.name.like("%" + colValue + "%").or(qFindingRateMast.purity.name.like("%" + colValue + "%").or(qFindingRateMast.component.name.like("%" + colValue + "%"))));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qFindingRateMast.party.name.like("%" + colValue + "%").or(
						qFindingRateMast.purity.name.like("%" + colValue + "%").or(
								qFindingRateMast.component.name.like("%" + colValue + "%")));
			}
		}

		return findingRateMastRepository.count(expression);
		
		
	}

	@Override
	public Page<FindingRateMast> customSearch(Integer limit, Integer offset,
			String sort, String order, String partyCode, String purityName,
			String findingName) {
		
		QFindingRateMast qFindingRateMast = QFindingRateMast.findingRateMast;
		BooleanBuilder where = new BooleanBuilder();
		where.and(qFindingRateMast.deactive.eq(false));
		if (partyCode != null) {
			where.and(qFindingRateMast.party.partyCode.like("%" + partyCode + "%"));
		}

		if (purityName != null) {
			where.and(qFindingRateMast.purity.name.like("%" + purityName + "%"));
		}

		if (findingName != null) {
			where.and(qFindingRateMast.component.name.like("%" + findingName + "%"));
		}

		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		}

		Page<FindingRateMast> findingRateList = (Page<FindingRateMast>) findingRateMastRepository
				.findAll(
						where,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return findingRateList;
	}

	@Override
	public Long customSearchCount(String partyCode, String purityName,
			String findingName) {
		QFindingRateMast qFindingRateMast = QFindingRateMast.findingRateMast;
		BooleanBuilder where = new BooleanBuilder();
		where.and(qFindingRateMast.deactive.eq(false));
		if (partyCode != null) {
			where.and(qFindingRateMast.party.partyCode.like("%" + partyCode + "%"));
		}

		if (purityName != null) {
			where.and(qFindingRateMast.purity.name.like("%" + purityName + "%"));
		}

		if (findingName != null) {
			where.and(qFindingRateMast.component.name.like("%" + findingName + "%"));
		}

		

		return findingRateMastRepository.count(where);
	}
}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientReference;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QClientReference;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IClientRefRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientRefService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ClientRefService implements IClientRefService{

	@Autowired
	private IClientRefRepository clientRefRepository;
	
	@Override
	public List<ClientReference> findAll() {
		// TODO Auto-generated method stub
		return clientRefRepository.findAll();
	}

	@Override
	public Page<ClientReference> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		
		limit = (limit == null ? 10 : limit);
		offset = (offset == null ? 0 : offset);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return clientRefRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(ClientReference clientReference) {
		clientRefRepository.save(clientReference);
		
	}

	@Override
	public String delete(int id) {
		String retVal="-1";
		ClientReference clientReference = clientRefRepository.findOne(id);
		clientReference.setDeactive(true);
		clientReference.setDeactiveDt(new java.util.Date());
		clientRefRepository.save(clientReference);
		retVal="1";
		return retVal;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return clientRefRepository.count();
	}

	@Override
	public ClientReference findOne(int id) {
		// TODO Auto-generated method stub
		return clientRefRepository.findOne(id);
	}

	@Override
	public List<ClientReference> findActiveClientReferences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive,Party party) {
		QClientReference qClientReference = QClientReference.clientReference;
		BooleanExpression expression = qClientReference.deactive.eq(false);
		

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qClientReference.deactive.eq(false).and(qClientReference.party.eq(party));
			} else {
				expression = qClientReference.deactive.eq(false).and(
						qClientReference.party.name.like(colValue + "%").or(qClientReference.design.mainStyleNo.like(colValue +"%")).or(qClientReference.purity.name.like(colValue +"%")))
						.and(qClientReference.party.eq(party));
			}
		}else {
			if (colValue == null) {
				expression = qClientReference.party.eq(party);
			} else {
				expression = qClientReference.party.name.like(colValue + "%").or(qClientReference.design.mainStyleNo.like(colValue +"%"))
						.or(qClientReference.purity.name.like(colValue +"%")).and(qClientReference.party.eq(party));
			}
		}
		
		
		return clientRefRepository.count(expression);
	}

	@Override
	public Page<ClientReference> findByParty(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive,Party party) {
		QClientReference qClientReference = QClientReference.clientReference;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qClientReference.deactive.eq(false).and(qClientReference.party.eq(party));
			} else {
				expression = qClientReference.deactive.eq(false).and(
						qClientReference.party.name.like(name + "%").or(qClientReference.design.mainStyleNo.like(name +"%")).or(qClientReference.purity.name.like(name +"%"))
						.or(qClientReference.y.like(name +"%")).or(qClientReference.w.like(name +"%")).or(qClientReference.p.like(name +"%"))
						.or(qClientReference.wy.like(name +"%")).or(qClientReference.wp.like(name +"%")).or(qClientReference.yw.like(name +"%"))
						.or(qClientReference.yp.like(name +"%")).or(qClientReference.py.like(name +"%")).or(qClientReference.pw.like(name +"%"))
						.or(qClientReference.tt.like(name +"%"))).and(qClientReference.party.eq(party));
			}
		} else {
			if (name != null) {
				expression = qClientReference.party.name.like(name + "%").or(qClientReference.design.mainStyleNo.like(name +"%")).or(qClientReference.purity.name.like(name +"%"))
						.or(qClientReference.y.like(name +"%")).or(qClientReference.w.like(name +"%")).or(qClientReference.p.like(name +"%"))
						.or(qClientReference.wy.like(name +"%")).or(qClientReference.wp.like(name +"%")).or(qClientReference.yw.like(name +"%"))
						.or(qClientReference.yp.like(name +"%")).or(qClientReference.py.like(name +"%")).or(qClientReference.pw.like(name +"%"))
						.or(qClientReference.tt.like(name +"%")).and(qClientReference.party.eq(party));
			}
		}

		// new addition
		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
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

		Page<ClientReference> clientRefList = (Page<ClientReference>) clientRefRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return clientRefList;
		
	}

	@Override
	public ClientReference findByPartyAndDesignAndPurityAndDeactive(
			Party party, Design design, Purity purity, Boolean deactive) {
		// TODO Auto-generated method stub
		return clientRefRepository.findByPartyAndDesignAndPurityAndDeactive(party, design, purity, deactive);
	}
	
	

}

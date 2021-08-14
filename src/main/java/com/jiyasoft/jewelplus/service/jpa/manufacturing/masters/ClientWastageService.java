package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IClientWastageRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ClientWastageService implements IClientWastageService{
	
	@Autowired
	private IClientWastageRepository clientWastageRepository;

	@Override
	public List<ClientWastage> findAll() {
		return clientWastageRepository.findAll();
	}

	@Override
	public Page<ClientWastage> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		limit = (limit == null ? 10 : limit);
		offset = (offset == null ? 0 : offset);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return clientWastageRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(ClientWastage clientWastage) {
		clientWastageRepository.save(clientWastage);
		
	}

	@Override
	public ClientWastage findByMetalAndPartyAndDeactive(Metal metal,
			Party party, Boolean deactive) {
			return clientWastageRepository.findByMetalAndPartyAndDeactive(metal, party, deactive);
	}

	@Override
	public void delete(int id) {
		ClientWastage clientWastage = clientWastageRepository.findOne(id);
		clientWastage.setDeactive(true);
		clientWastage.setDeactiveDt(new java.util.Date());
		clientWastageRepository.save(clientWastage);
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return clientWastageRepository.count();
	}

	@Override
	public ClientWastage findOne(int id) {
		return clientWastageRepository.findOne(id);
	}

	@Override
	public List<ClientWastage> findActiveClientWastages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QClientWastage qClientWastage = QClientWastage.clientWastage;
		BooleanExpression expression = qClientWastage.deactive.eq(false);

		if (onlyActive) {
			if ( colValue == null) {
				expression = qClientWastage.deactive.eq(false);
			} else if (colValue != null) {
				expression = qClientWastage.deactive.eq(false).and(
						qClientWastage.metal.name.like(colValue + "%").or(qClientWastage.party.name.like(colValue + "%")));
			}
		} else {
			if ( colValue != null) {
				expression = qClientWastage.metal.name.like(colValue + "%").or(qClientWastage.party.name.like(colValue + "%"));
			}
		}

		return clientWastageRepository.count(expression);
	}

	@Override
	public Page<ClientWastage> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QClientWastage qClientWastage = QClientWastage.clientWastage;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qClientWastage.deactive.eq(false);
			} else {
				expression = qClientWastage.deactive.eq(false).and(
						qClientWastage.metal.name.like(name + "%").or(qClientWastage.party.name.like(name + "%")));
			}
		} else {
			if (name != null) {
				expression = qClientWastage.metal.name.like(name + "%").or(qClientWastage.party.name.like(name + "%"));
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

		Page<ClientWastage> clientWastageList = (Page<ClientWastage>) clientWastageRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return clientWastageList;
	}

	@Override
	public Long countAll(String colValue) {
		QClientWastage qClientWastage = QClientWastage.clientWastage;
		BooleanExpression expression = qClientWastage.deactive.eq(false);
		
			if(colValue!=null && colValue !="" ){
					
				expression = qClientWastage.deactive.eq(false).and(qClientWastage.party.name.like(colValue + "%").or(qClientWastage.metal.name.like(colValue+"%")));
			}
		
			System.out.println("count expression "+expression);
		 return clientWastageRepository.count(expression);
	}

	@Override
	public Page<ClientWastage> searchAll(Integer limit, Integer offset,
			String sort, String order, String name) {
		
		QClientWastage qClientWastage = QClientWastage.clientWastage;
		BooleanExpression expression = qClientWastage.deactive.eq(false);

		if(name !=null && name !=""){
		expression =qClientWastage.deactive.eq(false).and(qClientWastage.party.name.like(name + "%").or(qClientWastage.metal.name.like(name+"%")));
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<ClientWastage> clientWastageList = (Page<ClientWastage>) clientWastageRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("search expression "+expression);
		
		return clientWastageList;
	}

	@Override
	public ClientWastage findByMetalAndPartyAndDesignGroupAndCategoryAndSubCategoryAndDeactive(Metal metal, Party party,
			DesignGroup designGroup, Category category, SubCategory subCategory, Boolean deactive) {
		// TODO Auto-generated method stub
		return clientWastageRepository.findByMetalAndPartyAndDesignGroupAndCategoryAndSubCategoryAndDeactive(metal, party, designGroup, category, subCategory, deactive);
	}
	
	

}

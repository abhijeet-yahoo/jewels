package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientKtConvMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QClientKtConvMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IClientKtConvRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientKtConvService;
import com.mysema.query.types.expr.BooleanExpression;
@Service
@Repository
@Transactional

public class ClientKtConvService implements IClientKtConvService {

	@Autowired
	private IClientKtConvRepository clientKtConvRepository;
	
	@Override
	public void save(ClientKtConvMast clientKtConvMast) {
		
		clientKtConvRepository.save(clientKtConvMast);
	}

	@Override
	public void delete(int id) {
		ClientKtConvMast clientKtConvMast = clientKtConvRepository.findOne(id);
		clientKtConvMast.setDeactive(true);
		clientKtConvMast.setDeactiveDt(new java.util.Date());
		clientKtConvRepository.save(clientKtConvMast);
	}

	@Override
	public List<ClientKtConvMast> findAll() {
		
		return clientKtConvRepository.findAll();
	}

	@Override
	public ClientKtConvMast findByPartyAndPurityAndDeactive(Party party, Purity purity, Boolean deactive) {
		
		return clientKtConvRepository.findByPartyAndPurityAndDeactive(party, purity, deactive);
	}

	@Override
	public Page<ClientKtConvMast> searchAll(Integer limit, Integer offset, String sort, String order, String name) {
		QClientKtConvMast qClientKtConvMast = QClientKtConvMast.clientKtConvMast;
		BooleanExpression expression = qClientKtConvMast.deactive.eq(false);
		if(name !=null && name !=""){
			expression =qClientKtConvMast.deactive.eq(false).and(qClientKtConvMast.party.name.like(name + "%").or(qClientKtConvMast.purity.name.like(name +"%")));
		}
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		
		Page<ClientKtConvMast> clientKtConvMastList = (Page<ClientKtConvMast>) clientKtConvRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));
		
		return clientKtConvMastList;
	}

	@Override
	public ClientKtConvMast findOne(int id) {
		
		return clientKtConvRepository.findOne(id);
	}


}

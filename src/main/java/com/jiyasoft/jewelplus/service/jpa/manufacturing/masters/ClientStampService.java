package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QClientStamp;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IClientStampRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientStampService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ClientStampService implements IClientStampService {
	
	@Autowired
	private IClientStampRepository clientStampRepository;

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(ClientStamp clientStamp) {
		// TODO Auto-generated method stub
		clientStampRepository.save(clientStamp);
	}

	@Override
	public void delete(int id) {
		ClientStamp clientStamp = clientStampRepository.findOne(id);
		clientStamp.setDeactive(true);
		clientStamp.setDeactiveDt(new Date());
	   clientStampRepository.save(clientStamp);
	}

	@Override
	public ClientStamp findOne(int id) {
		// TODO Auto-generated method stub
		return clientStampRepository.findOne(id);
	}


	@Override
	public Page<ClientStamp> findByParty(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive,
			Party party) {


		QClientStamp qClientStamp = QClientStamp.clientStamp;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qClientStamp.deactive.eq(false).and(qClientStamp.party.eq(party));
			} else {
				expression = qClientStamp.deactive.eq(false).and(
						qClientStamp.party.name.like(name + "%").or(qClientStamp.stampNm.like(name +"%")))
						.and(qClientStamp.party.eq(party));
			}
		} else {
			if (name != null) {
				expression = qClientStamp.party.name.like(name + "%").or(qClientStamp.stampNm.like(name +"%")).and(qClientStamp.party.eq(party));
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

		Page<ClientStamp> clientStampList = (Page<ClientStamp>) clientStampRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return clientStampList;
	}

	@Override
	public String CheckDuplicate(Double fromCts, Double toCts, Integer id,Integer vPartyId) {
		
		String retVal="false";
		@SuppressWarnings("unchecked")
		TypedQuery<ClientStamp> query =  (TypedQuery<ClientStamp>)entityManager.createNativeQuery("select * from clientstampmast"
						+ " where partyid ="+vPartyId+" and "+fromCts+" between fromCts and toCts and deactive =0 ",ClientStamp.class);
		
		
		
		List<ClientStamp> clientStamps = query.getResultList();
		if(clientStamps.size()>0){
			if(id == null || id.equals("") || (id != null && id == 0)){
			
				retVal="true";
				
			}else{
			
				if(clientStamps.get(0).getId().equals(id)){
					retVal="false";
				}else{
					retVal="true";
				}
				
			}
		}else{
			
			@SuppressWarnings("unchecked")
			TypedQuery<ClientStamp> query1 =  (TypedQuery<ClientStamp>)entityManager.createNativeQuery("select * from clientstampmast"
					+ " where PartyId="+vPartyId+" and "+toCts+" between fromCts and toCts and deactive =0 ",ClientStamp.class);
			List<ClientStamp> clientStamps2 = query1.getResultList();
			if(clientStamps2.size()>0){
				if(id == null || id.equals("") || (id != null && id == 0)){
					
					retVal="true";
					
				}else{
				
					if(clientStamps2.get(0).getId().equals(id)){
						retVal="false";
					}else{
						retVal="true";
					}
					
				}	
				
			}

		}
		
		
		return retVal;
	}

	@Override
	public List<ClientStamp> findByPartyAndFromCtsAndDeactive(Party party,
			Double fromCts, Boolean deactive) {
		// TODO Auto-generated method stub
		return clientStampRepository.findByPartyAndFromCtsAndDeactive(party, fromCts, deactive);
	}

	@Override
	public String getStampName(Double carat,  Integer vPartyId) {
		
		String stampName="";
		
		@SuppressWarnings("unchecked")
		TypedQuery<ClientStamp> query =  (TypedQuery<ClientStamp>)entityManager.createNativeQuery("select * from clientstampmast"
						+ " where partyid ="+vPartyId+" and "+carat+" between fromCts and toCts and deactive =0 ",ClientStamp.class);
		
		List<ClientStamp> clientStamps = query.getResultList();
		
		if(clientStamps.size()>0){
			for (ClientStamp clientStamp : clientStamps) {
				
				stampName = clientStamp.getStampNm();
			}
		}
		
		return stampName;
	}

	@Override
	public String CheckDuplicatePurityWise(Integer id, Integer vPartyId, Integer vPurityId) {
		
		String retVal="false";
		@SuppressWarnings("unchecked")
		TypedQuery<ClientStamp> query =  (TypedQuery<ClientStamp>)entityManager.createNativeQuery("select * from clientstampmast"
						+ " where partyid ="+vPartyId+" and purityid= "+vPurityId+" and deactive =0 ",ClientStamp.class);
		
		
		
		List<ClientStamp> clientStamps = query.getResultList();
		if(clientStamps.size()>0){
			if(id == null || id.equals("") || (id != null && id == 0)){
			
				retVal="true";
				
			}else{
			
				if(clientStamps.get(0).getId().equals(id)){
					retVal="false";
				}else{
					retVal="true";
				}
				
			}
		}
		return retVal;
	}

	@Override
	public ClientStamp findByPartyAndPurityAndDeactive(Party party, Purity purity, Boolean deactive) {
		// TODO Auto-generated method stub
		return clientStampRepository.findByPartyAndPurityAndDeactive(party, purity, deactive);
	}

	@Override
	public ClientStamp findByStampNmAndDeactive(String name, Boolean deactive) {
		// TODO Auto-generated method stub
		return clientStampRepository.findByStampNmAndDeactive(name, deactive);
	}


}

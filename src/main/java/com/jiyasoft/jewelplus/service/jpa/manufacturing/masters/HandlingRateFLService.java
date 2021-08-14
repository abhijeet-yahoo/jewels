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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QHandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IHandlingMasterFLRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterFLService;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class HandlingRateFLService implements IHandlingMasterFLService{
	
	@Autowired
	private IHandlingMasterFLRepository handlingMasterFLRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<HandlingMasterFl> findByPartyAndFromDiaRateAndDeactive(
			Party party, Double fromDiaRate, Boolean deactive) {
		// TODO Auto-generated method stub
		return handlingMasterFLRepository.findByPartyAndFromDiaRateAndDeactive(party, fromDiaRate, deactive);
	}

	@Override
	public void save(HandlingMasterFl handlingMasterFl) {
		
		handlingMasterFLRepository.save(handlingMasterFl);
	}

	@Override
	public void delete(int id) {
		HandlingMasterFl handlingMasterFl =  handlingMasterFLRepository.findOne(id);
		handlingMasterFl.setDeactive(true);
		handlingMasterFl.setDeactiveDt(new Date());
		handlingMasterFLRepository.save(handlingMasterFl);
		
	}

	@Override
	public HandlingMasterFl findOne(int id) {
		// TODO Auto-generated method stub
		return handlingMasterFLRepository.findOne(id);
	}

	@Override
	public Page<HandlingMasterFl> findByParty(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive,
			Party party) {
		
		QHandlingMasterFl qHandlingMasterFl = QHandlingMasterFl.handlingMasterFl;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qHandlingMasterFl.deactive.eq(false).and(qHandlingMasterFl.party.eq(party));
			} else {
				expression = qHandlingMasterFl.deactive.eq(false).and(
						qHandlingMasterFl.party.name.like(name + "%").or(qHandlingMasterFl.rate.like(name +"%")))
						.and(qHandlingMasterFl.party.eq(party));
			}
		} else {
			if (name != null) {
				expression = qHandlingMasterFl.party.name.like(name + "%").or(qHandlingMasterFl.rate.like(name +"%")).and(qHandlingMasterFl.party.eq(party));
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

		Page<HandlingMasterFl> handlingMasterFlList = (Page<HandlingMasterFl>) handlingMasterFLRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return handlingMasterFlList;
	}

	@Override
	public String CheckDuplicate(Double fromDiaRate, Double toDiaRate,
			Integer id, Integer vPartyId) {
			// TODO Auto-generated method stub
		String retVal="false";
		@SuppressWarnings("unchecked")
		TypedQuery<HandlingMasterFl> query =  (TypedQuery<HandlingMasterFl>)entityManager.createNativeQuery("select * from handlingmastfl"
						+ " where partyid ="+vPartyId+" and "+fromDiaRate+" between fromDiaRate and toDiaRate and deactive =0 ",HandlingMasterFl.class);
		
		
		
		List<HandlingMasterFl> handlingMasterFls = query.getResultList();
		if(handlingMasterFls.size()>0){
			if(id == null || id.equals("") || (id != null && id == 0)){
			
				retVal="true";
				
			}else{
			
				if(handlingMasterFls.get(0).getId().equals(id)){
					retVal="false";
				}else{
					retVal="true";
				}
				
			}
		}else{
			
			@SuppressWarnings("unchecked")
			TypedQuery<HandlingMasterFl> query1 =  (TypedQuery<HandlingMasterFl>)entityManager.createNativeQuery("select * from handlingmastfl"
					+ " where PartyId="+vPartyId+" and "+toDiaRate+" between fromDiaRate and toDiaRate and deactive =0 ",HandlingMasterFl.class);
			List<HandlingMasterFl> handlingMasterFls2 = query1.getResultList();
			if(handlingMasterFls2.size()>0){
				if(id == null || id.equals("") || (id != null && id == 0)){
					
					retVal="true";
					
				}else{
				
					if(handlingMasterFls2.get(0).getId().equals(id)){
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
	public List<HandlingMasterFl> getRates(Party party, Double stoneRate) {
		QHandlingMasterFl qHandlingMasterFl = QHandlingMasterFl.handlingMasterFl;
		JPAQuery query = new JPAQuery(entityManager);
	
	
		List<HandlingMasterFl> handlingMasterList=query.from(qHandlingMasterFl)
				.where(qHandlingMasterFl.party.eq(party).and(qHandlingMasterFl.deactive.eq(false)).
						and(qHandlingMasterFl.fromDiaRate.lt(stoneRate).or(qHandlingMasterFl.fromDiaRate.eq(stoneRate))).
						and(qHandlingMasterFl.toDiaRate.gt(stoneRate).or(qHandlingMasterFl.toDiaRate.eq(stoneRate)))).list(qHandlingMasterFl);
		
			
		return handlingMasterList;
	}

}

package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStyleLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QClientStyleLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourCharge;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IClientStyleLabRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientStyleLabService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ClientStyleLabService implements IClientStyleLabService {
	@Autowired
	private IClientStyleLabRepository clientStyleLabRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Page<ClientStyleLabDt> findAll(Integer limit, Integer offset, String sort, String order, String search) {

		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		}

		QClientStyleLabDt qClientStyleLabDt = QClientStyleLabDt.clientStyleLabDt;
		BooleanExpression expression = null;

		if (search != null) {
			expression = qClientStyleLabDt.rate
							.like(search + "%")
							.or(qClientStyleLabDt.design.mainStyleNo
									.like(search + "%")
									.or(qClientStyleLabDt.party.partyCode
											.like(search + "%")
											.or(qClientStyleLabDt.metal.name
													.like(search + "%")
													.or(qClientStyleLabDt.labourType.name
															.like(search + "%")))));
		} 

		return clientStyleLabRepository.findAll(expression, new PageRequest(page,
				limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(ClientStyleLabDt clientStyleLabDt) {
		// TODO Auto-generated method stub
		clientStyleLabRepository.save(clientStyleLabDt);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		clientStyleLabRepository.delete(id);
		
	}



	@Override
	public ClientStyleLabDt findOne(int id) {
		// TODO Auto-generated method stub
		return clientStyleLabRepository.findOne(id);
	}

	@Override
	public ClientStyleLabDt findByRate(Double rate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ClientStyleLabDt> searchAll(Integer limit, Integer offset, String sort, String order, String search ){
		QClientStyleLabDt qClientStyleLabDt = QClientStyleLabDt.clientStyleLabDt;
		BooleanExpression expression = null;

			if (search != null) {
				expression = qClientStyleLabDt.design.mainStyleNo.like("%" + search + "%").or(qClientStyleLabDt.party.partyCode.like("%" + search + "%").or(qClientStyleLabDt.metal.name.like("%" + search + "%").or(qClientStyleLabDt.labourType.name.like("%"	+ search + "%"))));
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
		} else if (sort.equalsIgnoreCase("styleNo")) {
			sort = "design";
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party";
		} else if (sort.equalsIgnoreCase("metal")) {
			sort = "metal";
		} else if (sort.equalsIgnoreCase("labourType")) {
			sort = "labourType";
		}

		Page<ClientStyleLabDt> labourMastList = (Page<ClientStyleLabDt>) clientStyleLabRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		return labourMastList;
	}

	@Override
	public String CheckDuplicate(Integer id, Integer vPartyId, Integer vStyleId, Integer metalId,
			Integer labourTypeId, Double fromWt, Double toWt, Integer purityId) {
		
		String retVal="false";
		
		
		List<ClientStyleLabDt> labourCharges=null;
		if(purityId !=null && purityId > 0 ){
			@SuppressWarnings("unchecked")
			TypedQuery<ClientStyleLabDt> query =  (TypedQuery<ClientStyleLabDt>)entityManager.createNativeQuery("select * from clientstylelabdt"
							+ " where partyid ="+vPartyId+" and styleId ="+vStyleId+" and metalId ="+metalId+" and labTypeId ="+labourTypeId+" and purityId ="+purityId+
							" and "+fromWt+" between fromWeight and toWeight ",ClientStyleLabDt.class);
			
			 labourCharges = query.getResultList();
			
		}else{
		
			@SuppressWarnings("unchecked")
			TypedQuery<ClientStyleLabDt> query =  (TypedQuery<ClientStyleLabDt>)entityManager.createNativeQuery("select * from clientstylelabdt"
							+ " where partyid ="+vPartyId+" and styleId ="+vStyleId+" and metalId ="+metalId+" and labTypeId ="+labourTypeId+
							" and "+fromWt+" between fromWeight and toWeight ",ClientStyleLabDt.class);
			
			 labourCharges = query.getResultList();
			
		}
		
		
		
		if(labourCharges.size()>0){
			if(id == null || id.equals("") || (id != null && id == 0)){
			
				retVal="true";
				
			}else{
			
				if(labourCharges.get(0).getId().equals(id)){
					retVal="false";
				}else{
					retVal="true";
				}
				
			}
		}else{
			
			List<ClientStyleLabDt> labourCharges2 = null;
			if(purityId !=null && purityId > 0 ){
				@SuppressWarnings("unchecked")
				TypedQuery<ClientStyleLabDt> query1 =  (TypedQuery<ClientStyleLabDt>)entityManager.createNativeQuery("select * from clientstylelabdt"
						+ " where partyid ="+vPartyId+" and styleId ="+vStyleId+" and metalId ="+metalId+" and labTypeId ="+labourTypeId+" and purityId ="+purityId+
						" and "+toWt+" between fromWeight and toWeight ",ClientStyleLabDt.class);
					
				
				labourCharges2 = query1.getResultList();
			}else{
				@SuppressWarnings("unchecked")
				TypedQuery<ClientStyleLabDt> query1 =  (TypedQuery<ClientStyleLabDt>)entityManager.createNativeQuery("select * from clientstylelabdt"
						+ " where partyid ="+vPartyId+" and styleId ="+vStyleId+" and metalId ="+metalId+" and labTypeId ="+labourTypeId+
						" and "+toWt+" between fromWeight and toWeight ",ClientStyleLabDt.class);
					
				 labourCharges2 = query1.getResultList();
			}
		
			if(labourCharges2.size()>0){
				if(id == null || id.equals("") || (id != null && id == 0)){
					
					retVal="true";
					
				}else{
				
					if(labourCharges2.get(0).getId().equals(id)){
						retVal="false";
					}else{
						retVal="true";
					}
					
				}	
				
			}

		}
		
		
		return retVal;
	}

}

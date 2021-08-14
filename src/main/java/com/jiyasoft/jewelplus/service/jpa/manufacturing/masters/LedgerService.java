package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;


import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QParty;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ILedgerRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILedgerService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;



@Service
@Repository
@Transactional
public class LedgerService implements ILedgerService {

	@Autowired
	private ILedgerRepository ledgerRepository;
	
	@Autowired
	private EntityManager entityManager;
	

	@Override
	public List<Party> findAll() {
		
		BooleanExpression expression = null;
		return (List<Party>) ledgerRepository.findAll(expression);
	}

	@Override
	public void save(Party party) {
		ledgerRepository.save(party);
	}

	@Override
	public void delete(int id) {
		/*Party ledger = ledgerRepository.findOne(id);
		ledger.setDeactive(true);
		ledger.setDeactiveDt(new java.util.Date());*/
		ledgerRepository.delete(id);
	}


	@Override
	public Party findOne(int id) {
		return ledgerRepository.findOne(id);
	}

	@Override
	public Party findByNameAndDeactive(String name, Boolean deactive) {
		return ledgerRepository.findByNameAndDeactive(name, deactive);
	}

	@Override
	public Map<Integer, String> getPartyListForPurchase() {
		Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
		Page<Party> ledgerLists = findActivePartySortByNameForPurchase();
		
		for(Party ledger:ledgerLists){
			mapList.put(ledger.getId(), ledger.getName());
		}
		
		return mapList;
	}

	@Override
	public Page<Party> findActivePartySortByNameForPurchase() {
		QParty qParty = QParty.party;
		BooleanExpression expression = qParty.deactive.eq(false).and(qParty.ledgerGroup.name.equalsIgnoreCase("Sundry Creditors"));
		Page<Party> ledgerList = ledgerRepository.findAll(expression,new PageRequest(0, 10000, Direction.ASC, "name"));
		return ledgerList;
	}

	@Override
	public Map<Integer, String> getPartyListForSale() {
		Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
		Page<Party>  ledgerLists = findActivePartySortByNameForSale();
		
		for(Party ledger : ledgerLists){
		  mapList.put(ledger.getId(), ledger.getName());
		}
		
		return mapList;
	}

	@Override
	public Page<Party> findActivePartySortByNameForSale() {
		
		  QParty qParty = QParty.party;
	       BooleanExpression expression = qParty.deactive.eq(false).and(qParty.ledgerGroup.name.equalsIgnoreCase("Sundry Debtors"));
	       Page<Party> lederGroupList = ledgerRepository.findAll(expression, new PageRequest(0,10000, Direction.ASC,"name"));
	       
			return lederGroupList;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, String> getPartyList(String ledgerGroupNms,String ledgerType) {
		Map<Integer, String> ledgerMap = new LinkedHashMap<Integer, String>();
		Page<Party> ledgerPage = findActivePartySortByName(ledgerGroupNms,ledgerType);
		for(Party ledger:ledgerPage){
			ledgerMap.put(ledger.getId(), ledger.getName());
		}
		return ledgerMap;
	}

	@Override
	public Page<Party> findActivePartySortByName(String ledgerGroupNms,String ledgerType) {
		
		
		
		List<Integer> vPartyGroup = new ArrayList<Integer>();
		if(ledgerGroupNms.length()>0){
			String temp[]=ledgerGroupNms.split(",");
			for(int i=0;i<temp.length;i++){
				vPartyGroup.add(Integer.parseInt(temp[i]));
			}
		}
		
		List<Integer> vPartyType = new ArrayList<Integer>();
		if(ledgerType.length()>0){
			String temp[]=ledgerType.split(",");
			for(int i=0;i<temp.length;i++){
				vPartyType.add(Integer.parseInt(temp[i]));
			}
		}
		

		QParty qParty = QParty.party;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qParty.deactive.eq(false));
		
		if(vPartyGroup.size() > 0){
			builder.and(qParty.ledgerGroup.id.in(vPartyGroup));
		}
		
		if(vPartyType.size() > 0){
			builder.and(qParty.type.id.in(vPartyType));
		}
			
			Page<Party> ledgerList = ledgerRepository.findAll(builder, new PageRequest(0,1000000, Direction.ASC,"name"));
			return ledgerList;
	}

	@Override
	public List<Object[]> getPartyListValueByQuery(String customQuery,
			String tranType, String ledgerGroup) {
		Query query=null;
		if(tranType.equalsIgnoreCase("SALE")){
		 query = entityManager.createNativeQuery("select "+ customQuery +" from ledger_vw where ledgergroup in("+ledgerGroup+" )");
		
		}else if(tranType.equalsIgnoreCase("PURC")){
			query = entityManager.createNativeQuery("select "+ customQuery +" from ledger_vw where ledgergroup in("+ledgerGroup+" )");
		}
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		return list;
	}

	
	@Override
	public Page<Party> findActivePartySortByName() {
		QParty qParty = QParty.party;
		BooleanExpression expression =qParty.deactive.eq(false).and(qParty.ledgerGroup.name.notIn("Cash&Bank"));
		Page<Party> ledgerList= ledgerRepository.findAll(expression,new PageRequest(0, 10000, Direction.ASC, "name"));
		
		
		return ledgerList;
	}


	@Override
	public Page<Party> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive) {

		QParty qParty = QParty.party;
		BooleanExpression expression = null;
	
		if (onlyActive) {
			if (search == null) {
				expression = qParty.deactive.eq(false);
			} else {
				expression = qParty.deactive.eq(false).and(qParty.name.like("%" + search + "%").or(qParty.ledgerGroup.name.like("%" + search + "%")));
			}
		} else {
			if (search != null) {
				expression = qParty.name.like("%" + search + "%").or(qParty.ledgerGroup.name.like("%" + search + "%"));
			}
		}
		
		
		if(limit == null){
			limit = 1000;
		}
		
		if(offset == null){
			offset = 0;
		}
		
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("ledgerGroup")) {
			sort = "ledgerGroup";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		

		Page<Party> ledgerList = (Page<Party>) ledgerRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

	
		
		return ledgerList;
	}
	
	
	
	@Override
	public Party findByMailingNameAndDeactive(String mailingNm, Boolean deactive) {
		return ledgerRepository.findByMailingNameAndDeactive(mailingNm, deactive);
	}

	@Override
	public String partySave(Party party, Integer id, Principal principal,
			HttpSession httpSession) {
		
	
	String retVal = "redirect:/manufacturing/masters/ledger/add.html";
		
		try {
			
			Party ledgerCodeDbData = findByPartyCodeAndDeactive(party.getPartyCode(), false);
					
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
			//	LedgerGroup ledgerGroup = ledgerGroupService.findOne(party.getLedgerGroup().getId());
			//	String vPartyGroupCode = ledgerGroup.getLedgerGroupCode();
				
			//	Integer maxsrno = getMaxSrno(ledgerGroup.getId());
				
			//	 String newSrno = String.format("%03d" , (maxsrno+1));
			//	String vPartynm =  party.getName().substring(0, 4);
			//	String finalCode =vPartyGroupCode+vPartynm+newSrno;
				
			//	Party ledgerCodeDbData = findByCodeAndDeactive(finalCode, false);
				 
				if( ledgerCodeDbData != null){
					return retVal = "-1";
				}
				
		//		party.setPartyCode(partyCode);
		//		party.setSrNo(maxsrno+1);
				party.setCreatedBy(principal.getName());
				party.setCreateDt(new java.util.Date());
				
			}else{
				if(ledgerCodeDbData != null ){
					if(!(ledgerCodeDbData.getId().equals(party.getId()))){
						return retVal = "-1";
					}
				}
				
				Party party2 = findOne(id); 
				party.setSrNo(party2.getSrNo());
				party.setName(party.getName().toUpperCase());
				party.setModiBy(principal.getName());
				party.setModiDt(new java.util.Date());
				
				retVal = "redirect:/manufacturing/masters/ledger.html";
			}
			
			if(party.getLedgerGroup().getId() == null){
				party.setLedgerGroup(null);
			}
			if(party.getCountry().getId() == null){
				party.setCountry(null);
			}
			
			if(party.getStateMast().getId() == null){
				party.setStateMast(null);
			}
			
			if(party.getType().getId() == null){
				party.setType(null);
			}
			
			save(party);
			
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "Erorr : Record Not Saved("+e+") Contact Support";
		}
	
		
		return retVal;
	}
	
	

	@Override
	public Map<Integer, String> getTransporterList() {
		
		Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
		Page<Party>  ledgerLists = findActiveTransporter();
		
		for(Party ledger : ledgerLists){
		  mapList.put(ledger.getId(), ledger.getName());
		}
		return mapList;
	}

	@Override
	public Page<Party> findActiveTransporter() {
		
		QParty qParty = QParty.party;
	       BooleanExpression expression = qParty.deactive.eq(false).and(qParty.ledgerGroup.name.equalsIgnoreCase("Transporter"));
	       Page<Party> lederGroupList = ledgerRepository.findAll(expression, new PageRequest(0,10000, Direction.ASC,"name"));
	       
			return lederGroupList;
	}

	@Override
	public Page<Party> getPartyAuotFillByTranSetting(Integer limit,
			Integer offset, String sort, String order, String search,
			 String ledgerType, String ledgerGroup,
			Boolean onlyActive) {

		List<Integer> vLedgerGroup = new ArrayList<Integer>();
		if(ledgerGroup.length()>0){
			String temp[]=ledgerGroup.split(",");
			for(int i=0;i<temp.length;i++){
				vLedgerGroup.add(Integer.parseInt(temp[i]));
			}
		}
		
		List<Integer> vPartyType = new ArrayList<Integer>();
		if(ledgerType.length()>0){
			String temp[]=ledgerType.split(",");
			for(int i=0;i<temp.length;i++){
				vPartyType.add(Integer.parseInt(temp[i]));
			}
		}
		

		QParty qParty = QParty.party;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(onlyActive){
			builder.and(qParty.deactive.eq(false)).and(qParty.name.like("%"+search+"%"))
			;
		}else{
			builder.and(qParty.name.like("%"+search+"%"));
		}

		
		if(vLedgerGroup.size() > 0){
			builder.and(qParty.ledgerGroup.id.in(vLedgerGroup));
		}
		
		if(vPartyType.size() > 0){
			builder.and(qParty.type.id.in(vPartyType));
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} 
		
		Page<Party> ledgerList = (Page<Party>) ledgerRepository
				.findAll(
						builder,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		
		
		return ledgerList;
	}

	@Override
	public List<Party> autoCompletePartyList(String ledgerNm) {
		// TODO Auto-generated method stub
		QParty qParty = QParty.party;
		BooleanExpression expression = qParty.deactive.eq(false);

		expression = qParty.deactive.eq(false).and(qParty.name.like(ledgerNm+"%")) ;
				
		return (List<Party>) ledgerRepository.findAll(expression);
	}

	@Override
	public Party findByPartyCodeAndDeactive(String code, Boolean deactive) {
		// TODO Auto-generated method stub
		return ledgerRepository.findByPartyCodeAndDeactive(code, deactive);
	}

	@Override
	public List<Party> findDistinctByCity(String cityNm) {
		// TODO Auto-generated method stub
		return ledgerRepository.findDistinctByCityStartingWith(cityNm);
	}

	@Override
	public Integer getMaxSrno(Integer ledgerGroupId) {
		QParty qParty = QParty.party;
		JPAQuery query = new JPAQuery(entityManager);
		Integer retVal = 0;

		
		
		List<Integer> maxSrno = query
				.from(qParty)
				.where(qParty.deactive.eq(false).and(qParty.ledgerGroup.id.eq(ledgerGroupId))).
				list(qParty.srNo.max());

		for (Integer srno : maxSrno) {
			retVal = srno;
		}
		
		if(retVal ==null){
			retVal=0;
		}
	
		return retVal;
	}

	
}

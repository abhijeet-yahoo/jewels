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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLabourCharge;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ILabourChargeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class LabourChargeService implements ILabourChargeService {

	@Autowired
	ILabourChargeRepository labourChargeRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<LabourCharge> findAll() {
		return labourChargeRepository.findAll();
	}

	@Override
	public Page<LabourCharge> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		}

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanExpression expression = null;

		if (search != null) {
			expression = qLabourCharge.deactive
					.eq(false)
					.and(qLabourCharge.rate
							.like(search + "%")
							.or(qLabourCharge.category.name
									.like(search + "%")
									.or(qLabourCharge.party.partyCode
											.like(search + "%")
											.or(qLabourCharge.metal.name
													.like(search + "%")
													.or(qLabourCharge.labourType.name
															.like(search + "%"))))));
		} else {
			expression = qLabourCharge.deactive.eq(false);
		}

		return labourChargeRepository.findAll(expression, new PageRequest(page,
				limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(LabourCharge labourCharge) {
		labourChargeRepository.save(labourCharge);

	}

	@Override
	public void delete(int id) {
		/*
		 * LabourCharge labourCharge = labourChargeRepository.findOne(id);
		 * labourCharge.setDeactive(true); labourCharge.setDeactiveDt(new
		 * java.util.Date());
		 */
		labourChargeRepository.delete(id);

	}

	@Override
	public Long count() {
		return labourChargeRepository.count();
	}

	@Override
	public Long count(Predicate predicate) {
		return labourChargeRepository.count(predicate);
	}

	@Override
	public LabourCharge findOne(int id) {
		return labourChargeRepository.findOne(id);
	}

	@Override
	public LabourCharge findByRate(Double rate) {
		return labourChargeRepository.findByRate(rate);
	}

	@Override
	public Page<LabourCharge> findByCategory(Category category, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return labourChargeRepository.findByCategory(category, new PageRequest(
				page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Predicate countByCategory(Integer id) {
		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanExpression expression = qLabourCharge.category.id.eq(id);
		return expression;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanExpression expression = qLabourCharge.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {

				expression = qLabourCharge.deactive.eq(false);

			} else if (colValue != null) {
				expression = qLabourCharge.deactive
						.eq(false)
						.and(qLabourCharge.rate
								.like(colValue + "%")
								.or(qLabourCharge.party.partyCode
										.like(colValue + "%")
										.or(qLabourCharge.category.name
												.like(colValue + "%")
												.or(qLabourCharge.metal.name
														.like(colValue + "%")
														.or(qLabourCharge.labourType.name
																.like(colValue
																		+ "%"))))));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qLabourCharge.rate
						.like(colValue + "%")
						.or(qLabourCharge.party.partyCode
								.like(colValue + "%")
								.or(qLabourCharge.category.name.like(
										colValue + "%").or(
										qLabourCharge.metal.name.like(
												colValue + "%").or(
												qLabourCharge.labourType.name
														.like(colValue + "%")))));
			}
		}

		return labourChargeRepository.count(expression);
	}

	@Override
	public Page<LabourCharge> findByRate(Integer limit, Integer offset,
			String sort, String order, String perPcsRate, Boolean onlyActive) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (perPcsRate == null) {
				expression = qLabourCharge.deactive.eq(false);
			} else {
				expression = qLabourCharge.deactive.eq(false).and(
						qLabourCharge.category.name.like(perPcsRate + "%"));
			}
		} else {
			if (perPcsRate != null) {
				expression = qLabourCharge.rate
						.like(perPcsRate + "%")
						.or(qLabourCharge.category.name.like(perPcsRate + "%"))
						.or(qLabourCharge.party.name.like(perPcsRate + "%"))
						.or(qLabourCharge.labourType.name
								.like(perPcsRate + "%"));

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

		Page<LabourCharge> labourChargeList = (Page<LabourCharge>) labourChargeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return labourChargeList;
	}

	@Override
	public List<LabourCharge> findByCategoryAndLabourType(Category category,
			LabourType labourType) {
		return labourChargeRepository.findByCategoryAndLabourType(category,
				labourType);
	}

	@Override
	public List<LabourCharge> findByDefLabour(Boolean defLabour) {
		return labourChargeRepository.findByDefLabour(defLabour);
	}

	@Override
	public List<LabourCharge> findByCategoryAndDeactive(Category category,
			Boolean deactive) {
		return labourChargeRepository.findByCategoryAndDeactive(category,
				deactive);
	}

	@Override
	public List<LabourCharge> findByPartyAndCategoryAndLabourType(Party party,
			Category category, LabourType labourType) {

		return labourChargeRepository.findByPartyAndCategoryAndLabourType(
				party, category, labourType);
	}

	// new method for search
	@Override
	public Page<LabourCharge> findByCategory(Category category, Integer limit,
			Integer offset, String sort, String order, String name,
			Boolean onlyActive) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qLabourCharge.deactive.eq(false).and(
						qLabourCharge.category.id.eq(category.getId()));
			} else {
				expression = qLabourCharge.deactive
						.eq(false)
						.and(qLabourCharge.category.id.eq(category.getId()))
						.and(qLabourCharge.rate
								.like(name + "%")
								.or(qLabourCharge.category.name
										.like(name + "%")
										.or(qLabourCharge.metal.name
												.like(name + "%")
												.or(qLabourCharge.party.partyCode
														.like(name + "%")
														.or(qLabourCharge.labourType.name
																.like(name
																		+ "%"))))));
			}
		} else {
			if (name != null) {
				expression = qLabourCharge.category.id
						.eq(category.getId())
						.and(qLabourCharge.rate
								.like(name + "%")
								.or(qLabourCharge.category.name
										.like(name + "%")
										.or(qLabourCharge.metal.name
												.like(name + "%")
												.or(qLabourCharge.party.partyCode
														.like(name + "%")
														.or(qLabourCharge.labourType.name
																.like(name
																		+ "%"))))));
			}
		}

		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<LabourCharge> labourChargeList = (Page<LabourCharge>) labourChargeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return labourChargeList;
	}

	@Override
	public Long countByCategory(Category category, String colName,
			String colValue, Boolean onlyActive) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanExpression expression = qLabourCharge.deactive.eq(false);

		if (onlyActive) {
			if (colValue == null) {
				expression = qLabourCharge.deactive.eq(false);
			} else {
				expression = qLabourCharge.category.id
						.eq(category.getId())
						.and(qLabourCharge.rate
								.like(colValue + "%")
								.or(qLabourCharge.category.name
										.like(colValue + "%")
										.or(qLabourCharge.party.partyCode
												.like(colValue + "%")
												.or(qLabourCharge.metal.name
														.like(colValue + "%")
														.or(qLabourCharge.labourType.name
																.like(colValue
																		+ "%"))))));
			}
		} else {
			if (colValue != null) {
				expression = qLabourCharge.category.id
						.eq(category.getId())
						.and(qLabourCharge.rate
								.like(colValue + "%")
								.or(qLabourCharge.category.name
										.like(colValue + "%")
										.or(qLabourCharge.metal.name
												.like(colValue + "%")
												.or(qLabourCharge.party.partyCode
														.like(colValue + "%")
														.or(qLabourCharge.labourType.name
																.like(colValue
																		+ "%"))))));
			} else {
				expression = qLabourCharge.category.id.eq(category.getId());
			}
		}

		return labourChargeRepository.count(expression);
	}

	@Override
	public List<LabourCharge> findByCategoryAndDeactiveAndParty(
			Category category, Boolean deactive, Party party) {
		return labourChargeRepository.findByCategoryAndDeactiveAndParty(
				category, deactive, party);
	}

	@Override
	public List<LabourCharge> getRates(Party party, Category category,
			Double metalWt, Boolean deactive, Metal metal) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		JPAQuery query = new JPAQuery(entityManager);

		List<LabourCharge> labourChargeMasts = query
				.from(qLabourCharge)
				.where(qLabourCharge.category
						.eq(category)
						.and(qLabourCharge.party.eq(party))
						.and(qLabourCharge.deactive.eq(false))
						.and((qLabourCharge.fromWeight.lt(metalWt)
								.or(qLabourCharge.fromWeight.eq(metalWt))))
						.and((qLabourCharge.toWeight.gt(metalWt)
								.or(qLabourCharge.toWeight.eq(metalWt))))
						.and(qLabourCharge.metal.eq(metal)).and(qLabourCharge.defLabour.eq(true)))
				.list(qLabourCharge);

		return labourChargeMasts;

	}

	@Override
	public List<LabourCharge> getLabourRates(Party party, Category category,
			Double metalWt, Boolean deactive, Metal metal, LabourType labourType) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		JPAQuery query = new JPAQuery(entityManager);
		

		List<LabourCharge> labourChargeMasts = query
				.from(qLabourCharge)
				.where(qLabourCharge.category
						.eq(category)
						.and(qLabourCharge.party.eq(party))
						.and(qLabourCharge.deactive.eq(false))
						.and((qLabourCharge.fromWeight.lt(metalWt)
								.or(qLabourCharge.fromWeight.eq(metalWt))))
						.and((qLabourCharge.toWeight.gt(metalWt)
								.or(qLabourCharge.toWeight.eq(metalWt))))
						.and(qLabourCharge.metal.eq(metal))
						.and(qLabourCharge.labourType.eq(labourType)))
				.list(qLabourCharge);

		return labourChargeMasts;
	}

	


	@Override
	public Page<LabourCharge> customSearch(Integer limit, Integer offset,
			String sort, String order, String partyCode, String categoryNm,
			String metalNm, String labourTypeNm) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanBuilder where = new BooleanBuilder();

		if (partyCode != null) {
			where.and(qLabourCharge.party.partyCode.like("%" + partyCode + "%"));
		}

		if (categoryNm != null) {
			where.and(qLabourCharge.category.name.like("%" + categoryNm + "%"));
		}

		if (metalNm != null) {
			where.and(qLabourCharge.metal.name.like("%" + metalNm + "%"));
		}
		

		if (labourTypeNm != null) {
			where.and(qLabourCharge.labourType.name.like("%" + labourTypeNm	+ "%"));
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

		Page<LabourCharge> labourChargeList = (Page<LabourCharge>) labourChargeRepository
				.findAll(
						where,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return labourChargeList;
	}

	@Override
	public Long customSearchCount(String partyCode, String categoryNm,
			String metalNm, String labourTypeNm) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanBuilder where = new BooleanBuilder();

		if (partyCode != null) {
			where.and(qLabourCharge.party.partyCode.like("%" + partyCode + "%"));
		}

		if (categoryNm != null) {
			where.and(qLabourCharge.category.name.like("%" + categoryNm + "%"));
		}

		if (metalNm != null) {
			where.and(qLabourCharge.metal.name.like("%" + metalNm + "%"));
		}
	

		if (labourTypeNm != null) {
			where.and(qLabourCharge.labourType.name.like("%" + labourTypeNm	+ "%"));
		}
	
		

		return labourChargeRepository.count(where);
	}

	@Override
	public List<LabourCharge> findByPartyAndCategoryAndMetalAndDeactive(
			Party party, Category category, Metal metal, Boolean deactive) {
		// TODO Auto-generated method stub
		return labourChargeRepository
				.findByPartyAndCategoryAndMetalAndDeactive(party, category,
						metal, deactive);
	}

	@Override
	public Page<LabourCharge> searchAll(Integer limit, Integer offset,String sort, String order, String search, Boolean onlyActive) {
		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (search == null) {
				expression = qLabourCharge.deactive.eq(false);
			} else {
				expression = qLabourCharge.deactive.eq(false).and(qLabourCharge.category.name.like("%" + search + "%").or(qLabourCharge.party.partyCode.like("%" + search + "%").or(qLabourCharge.metal.name.like("%" + search + "%").or(qLabourCharge.labourType.name.like("%" + search+ "%")))));
			}
		} else {
			if (search != null) {
				expression = qLabourCharge.category.name.like("%" + search + "%").or(qLabourCharge.party.partyCode.like("%" + search + "%").or(qLabourCharge.metal.name.like("%" + search + "%").or(qLabourCharge.labourType.name.like("%"	+ search + "%"))));
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
		} else if (sort.equalsIgnoreCase("category")) {
			sort = "category";
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party";
		} else if (sort.equalsIgnoreCase("metal")) {
			sort = "metal";
		} else if (sort.equalsIgnoreCase("labourType")) {
			sort = "labourType";
		}

		Page<LabourCharge> labourMastList = (Page<LabourCharge>) labourChargeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		return labourMastList;

	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qLabourCharge.deactive.eq(false);
			} else {
				expression = qLabourCharge.deactive.eq(false).and(qLabourCharge.category.name.like("%" + colValue + "%").or(qLabourCharge.party.partyCode.like(	"%" + colValue + "%").or(qLabourCharge.metal.name.like(	"%" + colValue + "%").or(qLabourCharge.labourType.name.like("%" + colValue+ "%")))));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qLabourCharge.category.name.like(
						"%" + colValue + "%").or(
						qLabourCharge.party.name.like("%" + colValue + "%").or(
								qLabourCharge.metal.name.like(
										"%" + colValue + "%").or(
										qLabourCharge.labourType.name.like("%"
												+ colValue + "%"))));
			}
		}

		return labourChargeRepository.count(expression);
	}

	@Override
	public String CheckDuplicate(Integer id, Integer vPartyId,
			Integer vCategoryId, Integer metalId, Integer labourTypeId,
			Double fromWt, Double toWt,Integer purityId) {
		
		
		String retVal="false";
		
		
		List<LabourCharge> labourCharges=null;
		if(purityId !=null && purityId > 0 ){
			@SuppressWarnings("unchecked")
			TypedQuery<LabourCharge> query =  (TypedQuery<LabourCharge>)entityManager.createNativeQuery("select * from labourcharge"
							+ " where partyid ="+vPartyId+" and categId ="+vCategoryId+" and metalId ="+metalId+" and labTypeId ="+labourTypeId+" and purityId ="+purityId+
							" and "+fromWt+" between fromWeight and toWeight and deactive =0 ",LabourCharge.class);
			
			 labourCharges = query.getResultList();
			
		}else{
		
			@SuppressWarnings("unchecked")
			TypedQuery<LabourCharge> query =  (TypedQuery<LabourCharge>)entityManager.createNativeQuery("select * from labourcharge"
							+ " where partyid ="+vPartyId+" and categId ="+vCategoryId+" and metalId ="+metalId+" and labTypeId ="+labourTypeId+
							" and "+fromWt+" between fromWeight and toWeight and deactive =0 ",LabourCharge.class);
			
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
			
			List<LabourCharge> labourCharges2 = null;
			if(purityId !=null && purityId > 0 ){
				@SuppressWarnings("unchecked")
				TypedQuery<LabourCharge> query1 =  (TypedQuery<LabourCharge>)entityManager.createNativeQuery("select * from labourcharge"
						+ " where partyid ="+vPartyId+" and categId ="+vCategoryId+" and metalId ="+metalId+" and labTypeId ="+labourTypeId+" and purityId ="+purityId+
						" and "+toWt+" between fromWeight and toWeight and deactive =0 ",LabourCharge.class);
					
				
				labourCharges2 = query1.getResultList();
			}else{
				@SuppressWarnings("unchecked")
				TypedQuery<LabourCharge> query1 =  (TypedQuery<LabourCharge>)entityManager.createNativeQuery("select * from labourcharge"
						+ " where partyid ="+vPartyId+" and categId ="+vCategoryId+" and metalId ="+metalId+" and labTypeId ="+labourTypeId+
						" and "+toWt+" between fromWeight and toWeight and deactive =0 ",LabourCharge.class);
					
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

	@Override
	public List<LabourCharge> getPurityWiseRates(Party party,
			Category category, Double metalWt, Boolean deactive, Metal metal,
			Purity purity) {
		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		JPAQuery query = new JPAQuery(entityManager);

		List<LabourCharge> labourChargeMasts = query
				.from(qLabourCharge)
				.where(qLabourCharge.category
						.eq(category)
						.and(qLabourCharge.party.eq(party))
						.and(qLabourCharge.deactive.eq(false))
						.and((qLabourCharge.fromWeight.lt(metalWt)
								.or(qLabourCharge.fromWeight.eq(metalWt))))
						.and((qLabourCharge.toWeight.gt(metalWt)
								.or(qLabourCharge.toWeight.eq(metalWt))))
						.and(qLabourCharge.metal.eq(metal)).and(qLabourCharge.purity.eq(purity)).and(qLabourCharge.defLabour.eq(true)))
				.list(qLabourCharge);

		return labourChargeMasts;
	}

	@Override
	public List<LabourCharge> getPurityLabourRates(Party party,
			Category category, Double metalWt, Boolean deactive, Metal metal,
			LabourType labourType, Purity purity) {
		
		QLabourCharge qLabourCharge = QLabourCharge.labourCharge;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<LabourCharge> labourChargeMasts = query
				.from(qLabourCharge)
				.where(qLabourCharge.category
						.eq(category)
						.and(qLabourCharge.party.eq(party))
						.and(qLabourCharge.deactive.eq(false))
						.and((qLabourCharge.fromWeight.lt(metalWt)
								.or(qLabourCharge.fromWeight.eq(metalWt))))
						.and((qLabourCharge.toWeight.gt(metalWt)
								.or(qLabourCharge.toWeight.eq(metalWt))))
						.and(qLabourCharge.metal.eq(metal))
						.and(qLabourCharge.labourType.eq(labourType)).and(qLabourCharge.purity.eq(purity)))
				.list(qLabourCharge);

		return labourChargeMasts;
	}

}

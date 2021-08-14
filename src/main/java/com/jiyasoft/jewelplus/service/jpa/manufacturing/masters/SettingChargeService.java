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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QSettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ISettingChargeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class SettingChargeService implements ISettingChargeService {
	
	@Autowired
	ISettingChargeRepository settingChargeRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<SettingCharge> findAll() {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanExpression expression = qSettingCharge.deactive.eq(false);
		
		return  (List<SettingCharge>) settingChargeRepository.findAll(expression);
	}

	@Override
	public Page<SettingCharge> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanExpression expression = null;
		
		if(search != null){
			expression = qSettingCharge.deactive.eq(false)
					.and(qSettingCharge.setting.name.like(search + "%").or(qSettingCharge.shape.name.like(search + "%")
							.or(qSettingCharge.settingType.name.like(search + "%").or(qSettingCharge.stoneType.name.like(search + "%")
									.or(qSettingCharge.metal.name.like(search + "%")
									.or(qSettingCharge.party.partyCode.like(search + "%")))))));
		}else{
			expression = qSettingCharge.deactive.eq(false);
		}
		
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return settingChargeRepository
				.findAll(expression,new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(SettingCharge settingCharge) {
		settingChargeRepository.save(settingCharge);
		
	}

	@Override
	public void delete(int id) {
		SettingCharge settingCharge = settingChargeRepository.findOne(id);
		settingCharge.setDeactive(true);
		settingCharge.setDeactiveDt(new java.util.Date());
		settingChargeRepository.save(settingCharge);
		
	}

	@Override
	public Long count() {
		return settingChargeRepository.count();
	}

	@Override
	public Long count(Predicate predicate) {
		return settingChargeRepository.count(predicate);
	}

	@Override
	public SettingCharge findOne(int id) {
		return settingChargeRepository.findOne(id);
	}

	@Override
	public SettingCharge findByRate(Double rate) {
		return settingChargeRepository.findByRate(rate);
	}

	@Override
	public Page<SettingCharge> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return settingChargeRepository.findByShape(shape, new PageRequest(
				page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Predicate countByShape(Integer id) {
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanExpression expression = qSettingCharge.shape.id.eq(id);

		return expression;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanExpression expression = qSettingCharge.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSettingCharge.deactive.eq(false);
			} else if (colValue != null) {
				expression = qSettingCharge.deactive.eq(false)
						.and(qSettingCharge.setting.name.like(colValue + "%").or(qSettingCharge.shape.name.like(colValue + "%")
								.or(qSettingCharge.metal.name.like(colValue + "%")
								.or(qSettingCharge.settingType.name.like(colValue + "%").or(qSettingCharge.stoneType.name.like(colValue + "%")
										.or(qSettingCharge.party.partyCode.like(colValue + "%")))))));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name")) && colValue != null) {
				expression = qSettingCharge.rate.like(colValue + "%");
			}
		}

		return settingChargeRepository.count(expression);
	}

	@Override
	public Page<SettingCharge> findByRate(Integer limit, Integer offset,
			String sort, String order, String rate, Boolean onlyActive) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (rate == null) {
				expression = qSettingCharge.deactive.eq(false);
			} else {
				expression = qSettingCharge.deactive.eq(false).and(
						qSettingCharge.rate.like(rate + "%"));
			}
		} else {
			if (rate != null) {
				expression = qSettingCharge.rate.like(rate + "%");
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

		Page<SettingCharge> settingChargeList = (Page<SettingCharge>) settingChargeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return settingChargeList;
		
	}

	
	
	@Override
	public List<SettingCharge> getRates(Party party,Double pointerWt, Boolean deactive,Metal metal,StoneType stoneType, Shape shape, Setting setting,
			SettingType settingType) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		JPAQuery query = new JPAQuery(entityManager);
	
	
		List<SettingCharge> settingChargeList=query.from(qSettingCharge)
				.where(qSettingCharge.party.eq(party).and(qSettingCharge.deactive.eq(false)).and(qSettingCharge.metal.eq(metal)).
						and(qSettingCharge.stoneType.eq(stoneType)).and(qSettingCharge.shape.eq(shape)).
						and(qSettingCharge.setting.eq(setting)).and(qSettingCharge.settingType.eq(settingType)).
						and(qSettingCharge.fromWeight.lt(pointerWt).or(qSettingCharge.fromWeight.eq(pointerWt))).
						and(qSettingCharge.toWeight.gt(pointerWt).or(qSettingCharge.toWeight.eq(pointerWt)))).list(qSettingCharge);
		
			
		return settingChargeList;
		
	}

	
	

	@Override
	public SettingCharge findByStoneTypeAndShapeAndPartyAndMetalAndSettingAndSettingTypeAndFromWeightAndToWeightAndDeactive(
			StoneType stoneType, Shape shape, Party party,Metal metal, Setting setting,
			SettingType settingType,Double fromWeight, Double toWeight, Boolean deactive) {
		return settingChargeRepository.findByStoneTypeAndShapeAndPartyAndMetalAndSettingAndSettingTypeAndFromWeightAndToWeightAndDeactive(stoneType, shape,party,metal, setting, settingType,fromWeight,toWeight ,deactive);
	}

	@Override
	public List<SettingCharge> findByShapeAndDeactive(Shape shape,
			Boolean deative) {
		
		return settingChargeRepository.findByShapeAndDeactive(shape, deative);
	}

	@Override
	public Page<SettingCharge> findByShapeAndDeactive(Shape shape,
			Boolean deactive, Integer limit, Integer offset, String sort,
			String order, String search) {
	
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "size";
		}
	

		return settingChargeRepository.findByShapeAndDeactive(shape,deactive,new PageRequest(page,limit, (order.equalsIgnoreCase("asc") ? Direction.DESC : Direction.ASC), sort));

	}

	@Override
	public Page<SettingCharge> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qSettingCharge.deactive.eq(false);
			} else {
				expression = qSettingCharge.deactive.eq(false).and(
						qSettingCharge.setting.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qSettingCharge.setting.name.like(name + "%").or(qSettingCharge.shape.name.like(name + "%")).or(qSettingCharge.stoneType.name.like(name + "%")).or(qSettingCharge.settingType.name.like(name + "%")).or(qSettingCharge.party.name.like(name + "%"));
				
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

		Page<SettingCharge> stoneChartList = (Page<SettingCharge>) settingChargeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return stoneChartList;
	}

	@Override
	public Long countByShape(Shape shape, String colName, String colValue,
			Boolean onlyActive) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanExpression expression = qSettingCharge.deactive.eq(false);
		
		if (onlyActive) {
			if (colValue == null) {
				expression = qSettingCharge.deactive.eq(false);
			} else {
				expression = qSettingCharge.shape.id.eq(shape.getId())
						.and(qSettingCharge.setting.name.like(colValue + "%").or(qSettingCharge.shape.name.like(colValue + "%")
						.or(qSettingCharge.stoneType.name.like(colValue + "%").or(qSettingCharge.settingType.name.like(colValue + "%")
								.or(qSettingCharge.metal.name.like(colValue + "%")
								.or(qSettingCharge.party.partyCode.like(colValue + "%")))))));
			}
		} else {
			if (colValue != null) {
				expression =qSettingCharge.shape.id.eq(shape.getId())
						.and(qSettingCharge.setting.name.like(colValue + "%").or(qSettingCharge.shape.name.like(colValue + "%")
						.or(qSettingCharge.stoneType.name.like(colValue + "%").or(qSettingCharge.settingType.name.like(colValue + "%")
								.or(qSettingCharge.metal.name.like(colValue + "%")
								.or(qSettingCharge.party.partyCode.like(colValue + "%")))))));
			}else {
				expression =qSettingCharge.shape.id.eq(shape.getId());
			}
		}
		
		return settingChargeRepository.count(expression);
	}

	@Override
	public Page<SettingCharge> findByShape(Shape shape, Integer limit,
			Integer offset, String sort, String order, String name,
			Boolean onlyActive) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge; 
		BooleanExpression expression = null;

		
		if (onlyActive) {
			if (name == null) {
				expression = qSettingCharge.deactive.eq(false).and(qSettingCharge.shape.id.eq(shape.getId()));
			} else {
				expression =qSettingCharge.shape.id.eq(shape.getId()).and(qSettingCharge.deactive.eq(false))
						.and(qSettingCharge.setting.name.like(name + "%").or(qSettingCharge.shape.name.like(name + "%")
								.or(qSettingCharge.metal.name.like(name + "%")
								.or(qSettingCharge.settingType.name.like(name + "%").or(qSettingCharge.stoneType.name.like(name + "%")
										.or(qSettingCharge.party.name.like(name + "%")))))));
			}
		} else {
			if (name != null) {
			
				expression = qSettingCharge.shape.id.eq(shape.getId())
						.and(qSettingCharge.setting.name.like(name + "%").or(qSettingCharge.shape.name.like(name + "%")
								.or(qSettingCharge.metal.name.like(name + "%")
								.or(qSettingCharge.settingType.name.like(name + "%").or(qSettingCharge.stoneType.name.like(name + "%")
										.or(qSettingCharge.party.name.like(name + "%")))))));
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

		Page<SettingCharge> settingChargeList = (Page<SettingCharge>) settingChargeRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return settingChargeList; 
	}

	
	@Override
	public Page<SettingCharge> customSearch(Integer limit, Integer offset,
			String sort, String order, String partyCode,
			String metalNm, String stoneTypeNm, String shapeNm,
			String settingNm, String settingTypeNm) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanBuilder where = new BooleanBuilder();
		
		if(partyCode != null){
			where.and(qSettingCharge.party.partyCode.like("%" + partyCode +"%"));
		}
		
		if(metalNm != null){
			where.and(qSettingCharge.metal.name.like("%" + metalNm +"%"));
		}
		
		if(stoneTypeNm != null){
			where.and(qSettingCharge.stoneType.name.like("%" + stoneTypeNm +"%"));
		}
		
		if(shapeNm != null){
			where.and(qSettingCharge.shape.name.like("%" + shapeNm +"%"));
		}
		
		if(settingNm != null){
			where.and(qSettingCharge.setting.name.like("%" + settingNm +"%"));
		}
		
		if(settingTypeNm != null){
			where.and(qSettingCharge.settingType.name.like("%" + settingTypeNm +"%"));
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
		
		Page<SettingCharge> settingChargeList = (Page<SettingCharge>) settingChargeRepository.findAll(where,
				new PageRequest(page, limit,(order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC), sort));
		
		return settingChargeList;
	}

	
	@Override
	public Long customSearchCount(String partyCode,
			String metalNm, String stoneTypeNm, String shapeNm,
			String settingNm, String settingTypeNm) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge;
		BooleanBuilder where = new BooleanBuilder();
		
		if(partyCode != null){
			where.and(qSettingCharge.party.partyCode.like("%" + partyCode +"%"));
		}
		
		if(metalNm != null){
			where.and(qSettingCharge.metal.name.like("%" + metalNm +"%"));
		}
		
		if(stoneTypeNm != null){
			where.and(qSettingCharge.stoneType.name.like("%" + stoneTypeNm +"%"));
		}
		
		if(shapeNm != null){
			where.and(qSettingCharge.shape.name.like("%" + shapeNm +"%"));
		}
		
		if(settingNm != null){
			where.and(qSettingCharge.setting.name.like("%" + settingNm +"%"));
		}
		
		if(settingTypeNm != null){
			where.and(qSettingCharge.settingType.name.like("%" + settingTypeNm +"%"));
		}
		
		return settingChargeRepository.count(where);
	}
	
	
	@Override
	public SettingCharge findByUniqueId(Long uniqueId) {
		return settingChargeRepository.findByUniqueId(uniqueId);
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {

		QSettingCharge qSettingCharge = QSettingCharge.settingCharge; 
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qSettingCharge.deactive.eq(false);
			} else if ((colName == null || colName.equalsIgnoreCase("name")) && colValue != null) {
				expression =qSettingCharge.deactive.eq(false)
							.and(qSettingCharge.party.partyCode.like(colValue + "%")
							.or(qSettingCharge.shape.name.like(colValue + "%")
							.or(qSettingCharge.metal.name.like(colValue + "%"))
							.or(qSettingCharge.stoneType.name.like(colValue+"%"))
							.or(qSettingCharge.settingType.name.like(colValue + "%"))
							.or(qSettingCharge.setting.name.like(colValue+"%"))));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name")) && colValue != null) {
				expression = (qSettingCharge.party.partyCode.like(colValue + "%")
						.or(qSettingCharge.shape.name.like(colValue + "%"))
						.or(qSettingCharge.stoneType.name.like(colValue+"%")
						.or(qSettingCharge.metal.name.like(colValue+"%"))
						.or(qSettingCharge.settingType.name.like(colValue+"%")
						.or(qSettingCharge.setting.name.like(colValue+"%")))));
			}
		}

		return settingChargeRepository.count(expression);
		
	}

	@Override
	public Page<SettingCharge> searchAll(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {
		
		QSettingCharge qSettingCharge = QSettingCharge.settingCharge; 
		BooleanExpression expression = null;
		
		 if(onlyActive){
	        	if(name == null ){
	        		expression = qSettingCharge.deactive.eq(false);
	        		
	        	}else {
	        		expression = qSettingCharge.deactive.eq(false)
	        				.and(qSettingCharge.party.partyCode.like(name + "%").or(qSettingCharge.shape.name.like(name + "%")
	        						.or(qSettingCharge.stoneType.name.like(name+"%")
	        						.or(qSettingCharge.metal.name.like(name+"%")
	        						.or(qSettingCharge.setting.name.like(name+"%")
	        						.or(qSettingCharge.settingType.name.like(name+"%")))))));
				}
	        }
	        else {
					expression = (qSettingCharge.party.partyCode.like(name + "%")
							.or(qSettingCharge.shape.name.like(name + "%"))
							.or(qSettingCharge.stoneType.name.like(name+"%")
							.or(qSettingCharge.metal.name.like(name+"%")
							.or(qSettingCharge.setting.name.like(name+"%")
			        		.or(qSettingCharge.settingType.name.like(name+"%"))))));
				
			}
	    	int page = (offset == 0 ? 0 : (offset / limit));

			if (sort == null) {
				sort = "id";
			} else if (sort.equalsIgnoreCase("updatedBy")) {
				sort = "modiBy";
			} else if (sort.equalsIgnoreCase("updatedDt")) {
				sort = "modiDt";
			}

			Page<SettingCharge> settingChargeList = (Page<SettingCharge>) settingChargeRepository
					.findAll(
							expression,
							new PageRequest(page, limit, (order
									.equalsIgnoreCase("asc") ? Direction.ASC
									: Direction.DESC), sort));

			return settingChargeList;
		
	}

	@Override
	public String CheckDuplicate(Integer id, Integer vStoneTypeId,Integer shapeId,
			Integer vPartyId, Integer metalId, Integer settingId,
			Integer settingTypeId, Double fromWeight, Double toWeight) {
		// TODO Auto-generated method stub
		String retVal="false";
		@SuppressWarnings("unchecked")
		TypedQuery<SettingCharge> query =  (TypedQuery<SettingCharge>)entityManager.createNativeQuery("select * from settingcharge"
				+ " where stonetypeid ="+vStoneTypeId+" and partyid ="+vPartyId+" and metalId ="+metalId+" and settingid ="+settingId+" and setTypeid ="+settingTypeId+" and shapeId ="+shapeId+
				" and "+fromWeight+" between fromWeight and toWeight and deactive =0 ",SettingCharge.class);
		
		
		
		List<SettingCharge> settingCharges = query.getResultList();
		if(settingCharges.size()>0){
			if(id == null || id.equals("") || (id != null && id == 0)){
			
				retVal="true";
				
			}else{
			
				if(settingCharges.get(0).getId().equals(id)){
					retVal="false";
				}else{
					retVal="true";
				}
				
			}
		}else{
			
			@SuppressWarnings("unchecked")
			TypedQuery<SettingCharge> query1 =  (TypedQuery<SettingCharge>)entityManager.createNativeQuery("select * from settingcharge"
					+ " where stonetypeid ="+vStoneTypeId+" and partyid ="+vPartyId+" and metalId ="+metalId+" and settingid ="+settingId+" and setTypeid ="+settingTypeId+" and shapeId ="+shapeId+
					" and "+toWeight+" between fromWeight and toWeight and deactive =0 ",SettingCharge.class);
			
			
			List<SettingCharge> settingCharges2 = query1.getResultList();
			if(settingCharges2.size()>0){
				if(id == null || id.equals("") || (id != null && id == 0)){
					
					retVal="true";
					
				}else{
				
					if(settingCharges2.get(0).getId().equals(id)){
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

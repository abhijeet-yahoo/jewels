package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QStoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IStoneRateMastRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;



@Service
@Repository
@Transactional
public class StoneRateMastService  implements IStoneRateMastService {

	@Autowired
	private IStoneRateMastRepository stoneRateMastRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<StoneRateMast> findAll() {
		return stoneRateMastRepository.findAll();
	}

	@Override
	public Page<StoneRateMast> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return stoneRateMastRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(StoneRateMast stoneRateMast) {
		stoneRateMastRepository.save(stoneRateMast);
		
	}

	@Override
	public void delete(int id) {
		StoneRateMast stoneRateMast = stoneRateMastRepository.findOne(id);
		stoneRateMast.setDeactive(true);
		stoneRateMast.setDeactiveDt(new java.util.Date());
		stoneRateMastRepository.save(stoneRateMast);
		
	}

	@Override
	public Long count() {
		return stoneRateMastRepository.count();
	}

	@Override
	public Long count(Predicate predicate) {
		return stoneRateMastRepository.count(predicate);
	}

	@Override
	public StoneRateMast findOne(int id) {
		return stoneRateMastRepository.findOne(id);
	}

	@Override
	public List<StoneRateMast> findByStoneTypeAndDeactive(StoneType stoneType,
			Boolean deactive) {
		 
		return stoneRateMastRepository.findByStoneTypeAndDeactive(stoneType, deactive);
	}

	@Override
	public List<StoneRateMast> findByStoneTypeAndShapeAndDeactive(
			StoneType stoneType, Shape shape, Boolean deactive) {
		return stoneRateMastRepository.findByStoneTypeAndShapeAndDeactive(stoneType, shape, deactive);
	}

	@Override
	public StoneRateMast findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveAndPartyAndSieve(
			StoneType stoneType, Shape shape, Quality quality,SizeGroup sizeGroup,
			Boolean deactive,Party party,String sieve) {
		return stoneRateMastRepository.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveAndPartyAndSieve(stoneType, shape, quality, sizeGroup, deactive,party,sieve);
	}

	@Override
	public List<StoneRateMast> findByDeactive(Boolean deactive) {
		return stoneRateMastRepository.findByDeactive(deactive);
	}
	
	
	@Override
	public String updateStoneRateMast(Integer stoneTypeId) {
		
		String retVal = "";
		Integer res = 2;
		
		
		try{
			
			Query query = entityManager.createNativeQuery("{ CALL jsp_updateStoneRate(?) }");
			query.setParameter(1, stoneTypeId);
			res = query.executeUpdate();
			retVal = res+"";
		
			
		} catch (Exception e) {
			System.out.println("printing error message : = "+ e);
			retVal = res+"";
		}
		
		return retVal;
	}
	
	
	
	@Override
	public List<StoneRateMast> getStoneRate(Integer stoneTypeId,Integer shapeId, Integer qualityId, Integer sizeGroupId,Integer partyId,String sieve) {

		QStoneRateMast qStoneRateMast = QStoneRateMast.stoneRateMast;
		
		JPAQuery query = new JPAQuery(entityManager);
		JPAQuery query1 = new JPAQuery(entityManager);
		
		List<StoneRateMast> stoneRateMasts=null;
		
		 stoneRateMasts = query.from(qStoneRateMast)
				.where(qStoneRateMast.stoneType.id.eq(stoneTypeId).and(qStoneRateMast.shape.id.eq(shapeId))
						.and(qStoneRateMast.quality.id.eq(qualityId).and(qStoneRateMast.sizeGroup.id.eq(sizeGroupId).and(qStoneRateMast.sieve.eq(sieve))
								.and(qStoneRateMast.party.id.eq(partyId).
						and(qStoneRateMast.deactive.eq(false)))))).list(qStoneRateMast);
		
		if(stoneRateMasts.size()<=0 ) {
		
		
			
			stoneRateMasts = query1.from(qStoneRateMast)
				.where(qStoneRateMast.stoneType.id.eq(stoneTypeId).and(qStoneRateMast.shape.id.eq(shapeId)).and(qStoneRateMast.quality.id.eq(qualityId).and(qStoneRateMast.sizeGroup.id.eq(sizeGroupId).and(qStoneRateMast.party.id.eq(partyId).
						and(qStoneRateMast.deactive.eq(false)))))).list(qStoneRateMast);
		
			
		
		}
		
		return stoneRateMasts;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {

		QStoneRateMast qStoneRateMast = QStoneRateMast.stoneRateMast;
		BooleanExpression expression = qStoneRateMast.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qStoneRateMast.deactive.eq(false);
			} else if (colValue != null){
				expression=qStoneRateMast.deactive.eq(false)
						.and(qStoneRateMast.party.partyCode.like("%"+colValue+"%")
						.or(qStoneRateMast.shape.name.like("%"+colValue+"%")
						.or(qStoneRateMast.stoneType.name.like("%"+colValue+"%")
						.or(qStoneRateMast.quality.name.like("%"+colValue+"%")
						.or(qStoneRateMast.size.like("%"+colValue+"%"))))));
			}
		} 
		
		return stoneRateMastRepository.count(expression);
	}

	@Override
	public Page<StoneRateMast> findBySearchAndDeactive(Integer limit,
			Integer offset, String sort, String order, String name,
			Boolean onlyActive) {
		
		
		QStoneRateMast qStoneRateMast = QStoneRateMast.stoneRateMast;
		BooleanExpression expression = null;
        if(onlyActive){
        	if(name == null ){
        		expression = qStoneRateMast.deactive.eq(false);
        	}else {
        		expression = qStoneRateMast.deactive.eq(false)
        				.and(qStoneRateMast.party.partyCode.like("%"+name+"%")
        				.or(qStoneRateMast.shape.name.like("%"+name+"%")
        				.or(qStoneRateMast.stoneType.name.like("%"+name+"%")
        				.or(qStoneRateMast.quality.name.like(name + "%")
        				.or(qStoneRateMast.size.like("%"+name+"%"))))));
			}
        }
        else {
        	if (name != null) {
        		expression = qStoneRateMast.party.partyCode.like("%"+name+"%")
        				.or(qStoneRateMast.shape.name.like("%"+name+"%")
        						.or(qStoneRateMast.stoneType.name.like("%"+name+"%")
        								.or(qStoneRateMast.quality.name.like("%"+name+"%")
        										.or(qStoneRateMast.size.like("%"+name+"%")))));
			}
		}
    	
        int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("party")) {
			sort = "party.id";
		} else if (sort.equalsIgnoreCase("stoneType")) {
			sort = "stoneType.id";
		}else if (sort.equalsIgnoreCase("shape")) {
			sort = "shape.id";
		}else if (sort.equalsIgnoreCase("quality")) {
			sort = "quality.id";
		}else{
			sort = "size";
		}

		Page<StoneRateMast> stoneRateMastList = (Page<StoneRateMast>) stoneRateMastRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return stoneRateMastList;
	}
	
	
	@Override
	public StoneRateMast findByStoneTypeAndShapeAndQualityAndPartyAndFromWeightAndToWeightAndDeactive(
			StoneType stoneType, Shape shape, Quality quality, Party party,
			Double fromWeight, Double toWeight, Boolean deactive) {
		
		return stoneRateMastRepository.findByStoneTypeAndShapeAndQualityAndPartyAndFromWeightAndToWeightAndDeactive(stoneType, shape, quality, party, fromWeight, toWeight, deactive);
	}

	@Override
	public List<StoneRateMast> getBetweenStoneRate(StoneType stoneType, Shape shape,
			Quality quality, Party party, Double caratWt, Boolean deactive) {

		
		QStoneRateMast qStoneRateMast = QStoneRateMast.stoneRateMast;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<StoneRateMast> stoneRateMasts = query.from(qStoneRateMast)
				.where(qStoneRateMast.stoneType.eq(stoneType).and(qStoneRateMast.shape.eq(shape)).and(qStoneRateMast.quality.eq(quality)).and(qStoneRateMast.party.eq(party))
						.and(qStoneRateMast.deactive.eq(false)).and((qStoneRateMast.fromWeight.lt(caratWt).or(qStoneRateMast.fromWeight.eq(caratWt))))
						.and((qStoneRateMast.toWeight.gt(caratWt).or(qStoneRateMast.toWeight.eq(caratWt))))).list(qStoneRateMast);
		

		return stoneRateMasts;

	}

	@Override
	public Page<StoneRateMast> customSearch(Integer limit, Integer offset,
			String sort, String order,String partyCode,
			String stoneTypeNm, String shapeNm, String qualityNm, String sizeGroupNm) {
		// TODO Auto-generated method stub
		QStoneRateMast qStoneRateMast = QStoneRateMast.stoneRateMast;
		BooleanBuilder where = new BooleanBuilder();
		
		
		if(partyCode != null && partyCode !=""){
			where.and(qStoneRateMast.party.partyCode.like(partyCode +"%"));
		}
		
		if(stoneTypeNm != null && stoneTypeNm !=""){
			where.and(qStoneRateMast.stoneType.name.like("%" + stoneTypeNm +"%"));
		}
		
		if(shapeNm != null && shapeNm !=""){
			where.and(qStoneRateMast.shape.name.like(shapeNm +"%"));
		}
		
		if(qualityNm != null && qualityNm !=""){
			where.and(qStoneRateMast.quality.name.like("%" + qualityNm +"%"));
		}
		
		
		if(sizeGroupNm != null && sizeGroupNm !=""){
			where.and(qStoneRateMast.sizeGroup.name.like("%" + sizeGroupNm +"%"));
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
		
		Page<StoneRateMast> stoneRateMastList = (Page<StoneRateMast>) stoneRateMastRepository.findAll(where,
				new PageRequest(page, limit,(order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC), sort));
		
		
		System.out.println("stoneRateMastList   "+where);
		return stoneRateMastList;
	}

	@Override
	public Long customSearchCount(String partyCode,
			String stoneTypeNm, String shapeNm, String qualityNm, String sizeGroupNm) {
		// TODO Auto-generated method stub
		
		
		QStoneRateMast qStoneRateMast = QStoneRateMast.stoneRateMast;
		BooleanBuilder where = new BooleanBuilder();
		
		
		if(partyCode != null && partyCode !=""){
			where.and(qStoneRateMast.party.partyCode.like(partyCode +"%"));
		}
		
		if(stoneTypeNm != null && stoneTypeNm !=""){
			where.and(qStoneRateMast.stoneType.name.like("%" + stoneTypeNm +"%"));
		}
		
		if(shapeNm != null && shapeNm !=""){
			where.and(qStoneRateMast.shape.name.like(shapeNm +"%"));
		}
		
		if(qualityNm != null && qualityNm !=""){
			where.and(qStoneRateMast.quality.name.like("%" + qualityNm +"%"));
		}
		
		
		if(sizeGroupNm != null && sizeGroupNm !=""){
			where.and(qStoneRateMast.sizeGroup.name.like("%" + sizeGroupNm +"%"));
		}
		
		
		return stoneRateMastRepository.count(where);
	}

	@Override
	public List<StoneRateMast> getStoneRateForLaxmi(Integer stoneTypeId, Integer shapeId, Integer qualityId,
			Integer sizeGroupId, Integer partyId, String sieve) {
		
		QStoneRateMast qStoneRateMast = QStoneRateMast.stoneRateMast;
		JPAQuery query = new JPAQuery(entityManager);
		JPAQuery query1 = new JPAQuery(entityManager);
		
		List<StoneRateMast> stoneRateMasts=null;
		
		 stoneRateMasts = query1.from(qStoneRateMast)
				.where(qStoneRateMast.stoneType.id.eq(stoneTypeId).and(qStoneRateMast.shape.id.eq(shapeId)).and(qStoneRateMast.quality.id.eq(qualityId)
						.and(qStoneRateMast.sizeGroup.id.eq(sizeGroupId).and(qStoneRateMast.party.id.eq(partyId).and(qStoneRateMast.sieve.equalsIgnoreCase(sieve)).
						and(qStoneRateMast.deactive.eq(false)))))).list(qStoneRateMast);
		 
		 if(stoneRateMasts.size()>0) {
			 return stoneRateMasts;
		 }else {
		
			 stoneRateMasts = query.from(qStoneRateMast)
						.where(qStoneRateMast.stoneType.id.eq(stoneTypeId).and(qStoneRateMast.shape.id.eq(shapeId))
								.and(qStoneRateMast.quality.id.eq(qualityId).and(qStoneRateMast.sizeGroup.id.eq(sizeGroupId).and(qStoneRateMast.party.id.eq(partyId).
								and(qStoneRateMast.deactive.eq(false)))))).list(qStoneRateMast);
			 
			 return stoneRateMasts;
			 
		 }
		
		
		
		
		
	}
	
}

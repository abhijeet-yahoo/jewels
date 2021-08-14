package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QHandlingMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IHandlingMasterRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class HandlingMasterService  implements IHandlingMasterService{
	
	@Autowired
	private IHandlingMasterRepository handlingMasterRepository;

	@Override
	public List<HandlingMaster> findAll() {
		
		return handlingMasterRepository.findAll();
	}

	@Override
	public Page<HandlingMaster> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {
	
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return handlingMasterRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(HandlingMaster handlingMaster) {
	
		handlingMasterRepository.save(handlingMaster);
	}

	@Override
	public void delete(int id) {
	
		HandlingMaster handlingMaster = handlingMasterRepository.findOne(id);
		handlingMaster.setDeactive(true);
		handlingMaster.setDeactiveDt(new java.util.Date());
		handlingMasterRepository.save(handlingMaster);
		
	}

	@Override
	public Long count() {

		return handlingMasterRepository.count();
	}

	@Override
	public HandlingMaster findOne(int id) {
	
		return handlingMasterRepository.findOne(id);
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QHandlingMaster qHandlingMaster = QHandlingMaster.handlingMaster;
		BooleanExpression expression = qHandlingMaster.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qHandlingMaster.deactive.eq(false);
			} else if ((colName == null || colName.equalsIgnoreCase("name")) && colValue != null) {
				expression =qHandlingMaster.deactive.eq(false)
							.and(qHandlingMaster.party.partyCode.like(colValue + "%")
							.or(qHandlingMaster.shape.name.like(colValue + "%")
							.or(qHandlingMaster.metal.name.	like(colValue + "%"))
							.or(qHandlingMaster.stoneType.name.like(colValue+"%"))));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name")) && colValue != null) {
				expression = (qHandlingMaster.party.partyCode.like(colValue + "%")
						.or(qHandlingMaster.shape.name.like(colValue + "%"))
						.or(qHandlingMaster.stoneType.name.like(colValue+"%")
						.or(qHandlingMaster.metal.name.like(colValue+"%"))));
			}
		}

		return handlingMasterRepository.count(expression);
	}

	@Override
	public Page<HandlingMaster> findByPartyAndShap(Integer limit,
			Integer offset, String sort, String order, String name,
			Boolean onlyActive) {
		
		QHandlingMaster qHandlingMaster = QHandlingMaster.handlingMaster;
		BooleanExpression expression = null;
        if(onlyActive){
        	if(name == null ){
        		expression = qHandlingMaster.deactive.eq(false);
        		
        	}else {
        		expression = qHandlingMaster.deactive.eq(false)
        				.and(qHandlingMaster.party.partyCode.like(name + "%").or(qHandlingMaster.shape.name.like(name + "%")
        						.or(qHandlingMaster.stoneType.name.like(name+"%")
        						.or(qHandlingMaster.metal.name.like(name+"%")))));
			}
        }
        else {
				expression = (qHandlingMaster.party.partyCode.like(name + "%")
						.or(qHandlingMaster.shape.name.like(name + "%"))
						.or(qHandlingMaster.stoneType.name.like(name+"%")
						.or(qHandlingMaster.metal.name.like(name+"%"))));
			
		}
    	int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<HandlingMaster> handlingMasterList = (Page<HandlingMaster>) handlingMasterRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return handlingMasterList;

	}

	@Override
	public HandlingMaster findByPartyAndMetalAndStoneTypeAndShapeAndDeactive(
			Party party,Metal metal ,StoneType stoneType, Shape shape, Boolean deactive) {
		return handlingMasterRepository.findByPartyAndMetalAndStoneTypeAndShapeAndDeactive(party,metal,stoneType, shape, deactive);
	}

}

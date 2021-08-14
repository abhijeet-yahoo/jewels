package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesignStone;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignStoneRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class DesignStoneService implements IDesignStoneService {

	@Autowired
	private IDesignStoneRepository designStoneRepository;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<DesignStone> findAll() {
		return designStoneRepository.findAll();
	}

	@Override
	public Page<DesignStone> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return designStoneRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(DesignStone designStone) {
		designStoneRepository.save(designStone);
	}

	@Override
	public void delete(int id) {
		DesignStone designStone = designStoneRepository.findOne(id);
		designStone.setDeactive(true);
		designStone.setDeactiveDt(new java.util.Date());
		designStoneRepository.save(designStone);
		
		designService.updateCarat(designStone.getDesign());
	}

	@Override
	public Long count() {
		return designStoneRepository.count();
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QDesignStone qDesignStone  = QDesignStone.designStone;
		BooleanExpression expression = qDesignStone.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDesignStone.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("styleId") && colValue != null) {
				expression = qDesignStone.deactive.eq(false).and(qDesignStone.design.id.eq(Integer.parseInt(colValue)));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("styleId")) && colValue != null) {
				expression = qDesignStone.design.id.eq(Integer.parseInt(colValue));
			}
		}

		return designStoneRepository.count(expression);
	}

	@Override
	public DesignStone findOne(int id) {
		return designStoneRepository.findOne(id);
	}

	public List<DesignStone> findByDesign(Design design) {
		QDesignStone qDesignStone = QDesignStone.designStone;
		BooleanExpression expression = qDesignStone.deactive.eq(false).and(qDesignStone.design.id.eq(design.getId()));
		
		return (List<DesignStone>) designStoneRepository.findAll(expression);
	}

	@Override
	public Map<Integer, String> getDesignStoneList() {
		return null;
	}

	@Override
	public Page<DesignStone> findByDesign(Design design, Integer limit, Integer offset, String sort,
			String order, String search) {
		
		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return designStoneRepository.findByDesign(design, new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}
	
	
	@Override
	public List<Tuple> getDesignStoneSumList(Integer styleId) {
		
		QDesignStone qDesignStone = QDesignStone.designStone;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Tuple> designStoneList = null;
		
		designStoneList = query.from(qDesignStone).
				where(qDesignStone.deactive.eq(false).and(qDesignStone.design.id.eq(styleId))).groupBy(qDesignStone.design.id).list(qDesignStone.stone.sum(),qDesignStone.carat.sum());
		
		return designStoneList;
	}

}

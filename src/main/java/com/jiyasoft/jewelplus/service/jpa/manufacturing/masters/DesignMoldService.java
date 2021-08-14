package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMold;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesignMold;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignMoldRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMoldService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class DesignMoldService implements IDesignMoldService{
	
	@Autowired
	private IDesignMoldRepository designMoldRepository;

	@Override
	public List<DesignMold> findAll() {
		return designMoldRepository.findAll();
	}

	@Override
	public void save(DesignMold designMold) {
		designMoldRepository.save(designMold);
	}

	@Override
	public void delete(int id) {
		DesignMold designMold = designMoldRepository.findOne(id);
		designMold.setDeactive(true);
		designMold.setDeactiveDt(new java.util.Date());
		designMoldRepository.save(designMold);
	}

	@Override
	public Long count() {
		return designMoldRepository.count();
	}

	@Override
	public DesignMold findOne(int id) {
		return designMoldRepository.findOne(id);
	}
	
	
	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QDesignMold qDesignMold = QDesignMold.designMold;
		BooleanExpression expression = qDesignMold.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qDesignMold.deactive.eq(false);
			} else if (colName == null && colValue != null) {
				expression = qDesignMold.deactive.eq(false).and(qDesignMold.design.styleNo.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("designMoldType") && colValue != null) {
				expression = qDesignMold.deactive.eq(false).and(qDesignMold.designMoldType.name.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("drawerNo") && colValue != null) {
				expression = qDesignMold.deactive.eq(false).and(qDesignMold.drawerNo.like(colValue + "%"));
			} else if (colName.equalsIgnoreCase("rackNo") && colValue != null) {
				expression = qDesignMold.deactive.eq(false).and(qDesignMold.rackNo.like(colValue + "%"));
			} 
		}

		return designMoldRepository.count(expression);
	}
	
	
	

	@Override
	public Page<DesignMold> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {
		
		QDesignMold qDesignMold = QDesignMold.designMold;
		BooleanExpression expression = qDesignMold.deactive.eq(false);

		int page = ((offset == null || offset == 0)  ? 0 : (offset / limit));

		if (limit == null) {
			limit = 10;
		}

		if (order == null) {
			order = "asc";
		}

		if (sort == null) {
			sort = "id";
		}

		return designMoldRepository.findAll(expression, new PageRequest(page, limit,(order.equalsIgnoreCase("asc") ? Direction.ASC
				: Direction.DESC), sort));
	}

	@Override
	public Page<DesignMold> findByStyleNo(Integer limit, Integer offset,
			String sort, String order, String styleNo, Boolean onlyActive) {
		
		QDesignMold qDesignMold = QDesignMold.designMold;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (styleNo == null) {
				expression = qDesignMold.deactive.eq(false);
			} else {
				expression = qDesignMold.deactive.eq(false).and(qDesignMold.design.styleNo.like(styleNo.trim() + "%"));
			}
		} else {
			if (styleNo != null) {
				expression = qDesignMold.design.styleNo.like(styleNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}
		
		

		Page<DesignMold> designMoldList = (Page<DesignMold>) designMoldRepository.findAll(expression,
				new PageRequest(page, limit, Direction.DESC, sort));

		return designMoldList;
	}

	@Override
	public Page<DesignMold> findByDesignMoldType(Integer limit, Integer offset,
			String sort, String order, String designMoldType, Boolean onlyActive) {
			
			QDesignMold qDesignMold = QDesignMold.designMold;
			BooleanExpression expression = null;
	
			if (onlyActive) {
				if (designMoldType == null) {
					expression = qDesignMold.deactive.eq(false);
				} else {
					expression = qDesignMold.deactive.eq(false).and(qDesignMold.designMoldType.name.like(designMoldType.trim() + "%"));
				}
			} else {
				if (designMoldType != null) {
					expression = qDesignMold.designMoldType.name.like(designMoldType + "%");
				}
			}
	
			int page = (offset == 0 ? 0 : (offset / limit));
	
			if (sort == null) {
				sort = "id";
			}
	
			Page<DesignMold> designMoldList = (Page<DesignMold>) designMoldRepository.findAll(expression,
					new PageRequest(page, limit, Direction.DESC, sort));
	
			return designMoldList;
	}

	@Override
	public Page<DesignMold> findByRackNo(Integer limit, Integer offset,
			String sort, String order, String rackNo, Boolean onlyActive) {
		
		QDesignMold qDesignMold = QDesignMold.designMold;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (rackNo == null) {
				expression = qDesignMold.deactive.eq(false);
			} else {
				expression = qDesignMold.deactive.eq(false).and(qDesignMold.rackNo.like(rackNo.trim() + "%"));
			}
		} else {
			if (rackNo != null) {
				expression = qDesignMold.design.styleNo.like(rackNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<DesignMold> designMoldList = (Page<DesignMold>) designMoldRepository.findAll(expression,
				new PageRequest(page, limit, Direction.DESC, sort));

		return designMoldList;
	}

	@Override
	public Page<DesignMold> findByDrawer(Integer limit, Integer offset,
			String sort, String order, String drawerNo, Boolean onlyActive) {
		
		QDesignMold qDesignMold = QDesignMold.designMold;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (drawerNo == null) {
				expression = qDesignMold.deactive.eq(false);
			} else {
				expression = qDesignMold.deactive.eq(false).and(qDesignMold.drawerNo.like(drawerNo.trim() + "%"));
			}
		} else {
			if (drawerNo != null) {
				expression = qDesignMold.design.styleNo.like(drawerNo + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		Page<DesignMold> designMoldList = (Page<DesignMold>) designMoldRepository.findAll(expression,
				new PageRequest(page, limit, Direction.DESC, sort));

		return designMoldList;
	}


	
	

}

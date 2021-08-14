package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.MarketTypeMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QMarketTypeMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IMarketTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMarketTypeService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class MarketTypeService implements IMarketTypeService {

	@Autowired IMarketTypeRepository marketTypeRepository;

	@Override
	public void save(MarketTypeMast marketTypeMast) {
		marketTypeRepository.save(marketTypeMast);	
	}

	@Override
	public void delete(int id) {
		MarketTypeMast marketTypeMast = marketTypeRepository.findOne(id);
		marketTypeMast.setDeactive(true);
		marketTypeMast.setDeactiveDt(new java.util.Date());
		marketTypeRepository.save(marketTypeMast);
		
	}

	@Override
	public MarketTypeMast findOne(int id) {
		
		return marketTypeRepository.findOne(id);
	}

	@Override
	public MarketTypeMast findByNameAndDeactive(String marketTypeMastName,Boolean deactive) {
		
		return marketTypeRepository.findByNameAndDeactive(marketTypeMastName, deactive);
	}

	@Override
	public Map<Integer, String> getMarketTypeMastList() {
		Map<Integer,String> marketTypeMastMap = new LinkedHashMap<Integer, String>();
		Page<MarketTypeMast> marketTypeMastList = findActiveMarketTypeMastSortByName();
		
		for(MarketTypeMast marketTypeMast : marketTypeMastList)
		{
			marketTypeMastMap.put(marketTypeMast.getId(), marketTypeMast.getName());
		}
		return marketTypeMastMap;
	}

	@Override
	public List<MarketTypeMast> findActiveMarketTypeMasts() {
		QMarketTypeMast qMarketTypeMast= QMarketTypeMast.marketTypeMast;
		BooleanExpression expression = qMarketTypeMast.deactive.eq(false);

		List<MarketTypeMast> marketTypeMastList = (List<MarketTypeMast>) marketTypeRepository
				.findAll(expression);

		return marketTypeMastList;
	}

	@Override
	public Page<MarketTypeMast> findActiveMarketTypeMastSortByName() {
		QMarketTypeMast qMarketTypeMast = QMarketTypeMast.marketTypeMast;
		BooleanExpression expression = qMarketTypeMast.deactive.eq(false);

		Page<MarketTypeMast> marketTypeMastList = marketTypeRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return marketTypeMastList;
	}

	@Override
	public Page<MarketTypeMast> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		QMarketTypeMast qMarketTypeMast = QMarketTypeMast.marketTypeMast;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qMarketTypeMast.deactive.eq(false);
			}else{
				expression = qMarketTypeMast.deactive.eq(false).and(qMarketTypeMast.name.like("%" + search + "%").or(qMarketTypeMast.code.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qMarketTypeMast.name.like("%" + search + "%").or(qMarketTypeMast.code.like("%" + search + "%"));
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("code")) {
			sort = "code";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<MarketTypeMast> marketTypeMastList =(Page<MarketTypeMast>) marketTypeRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return marketTypeMastList;
	}


	@Override
	public MarketTypeMast findByCodeAndDeactive(String code, Boolean deactive) {
	
		return marketTypeRepository.findByCodeAndDeactive(code, deactive);
	}

	@Override
	public List<MarketTypeMast> findAllByOrderByNameAsc() {
		// TODO Auto-generated method stub
		return marketTypeRepository.findAllByOrderByNameAsc();
	}
	
}

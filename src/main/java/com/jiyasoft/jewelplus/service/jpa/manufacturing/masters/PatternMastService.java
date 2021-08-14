package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.PatternMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QPatternMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IPatternMastRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPatternMastService;
import com.mysema.query.types.expr.BooleanExpression;


@Service
@Repository
@Transactional
public class PatternMastService implements IPatternMastService {

	@Autowired
	private IPatternMastRepository patternMastRepository;


	@Override
	public Page<PatternMast> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
	
		QPatternMast qPatternMast =QPatternMast.patternMast;
		BooleanExpression expression=null;
		if (onlyActive) {
			if (search == null) {
				expression = qPatternMast.deactive.eq(false);
			}else{
				expression = qPatternMast.deactive.eq(false).and(qPatternMast.name.like("%" + search + "%").or(qPatternMast.code.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qPatternMast.name.like("%" + search + "%").or(qPatternMast.code.like("%" + search + "%"));
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
	
		Page<PatternMast> patternMastList =(Page<PatternMast>) patternMastRepository.findAll(
				expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		

	
		return patternMastList;
	}

	@Override
	public PatternMast findOne(int id) {
		// TODO Auto-generated method stub
		return patternMastRepository.findOne(id);
	}

	@Override
	public void save(PatternMast patternMast) {
	
		patternMastRepository.save(patternMast);
		
	}

	@Override
	public void delete(int id) {
	
		PatternMast patternMast=patternMastRepository.findOne(id);
		patternMast.setDeactive(true);
		patternMast.setDeactiveDt(new java.util.Date());
		patternMastRepository.delete(id);
		
	}

	@Override
	public PatternMast findByName(String name) {
		// TODO Auto-generated method stub
		return patternMastRepository.findByName(name);
	}

	@Override
	public Map<Integer, String> getPatternList() {
		Map<Integer, String> patternMap = new LinkedHashMap<Integer, String>();
		Page<PatternMast> patternLists = findActivePatternSortByName();
		
		for(PatternMast patternMast:patternLists){
			patternMap.put(patternMast.getId(), patternMast.getName());
		}
		return patternMap;
	}

	@Override
	public Page<PatternMast> findActivePatternSortByName() {
		QPatternMast qPatternMast = QPatternMast.patternMast;
		BooleanExpression expression=qPatternMast.deactive.eq(false);
		Page<PatternMast> patternList = patternMastRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));
		return patternList;
	}
}

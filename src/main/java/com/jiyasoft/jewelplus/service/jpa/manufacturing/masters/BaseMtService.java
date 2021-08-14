package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BaseMt;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IBaseMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IBaseMtService;

@Service
@Repository
@Transactional
public class BaseMtService implements IBaseMtService {

	@Autowired
	private IBaseMtRepository baseMtRepository;

	@Override
	public List<BaseMt> findAll() {
		return baseMtRepository.findAll();
	}

	@Override
	public Page<BaseMt> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return baseMtRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(BaseMt baseMt) {
		baseMtRepository.save(baseMt);
	}

	@Override
	public void delete(int id) {
		//baseMtRepository.delete(id);
	}

	@Override
	public Long count() {
		return baseMtRepository.count();
	}

	@Override
	public BaseMt findOne(int id) {
		return baseMtRepository.findOne(id);
	}

	@Override
	public BaseMt findByBaseNo(Integer baseNo) {
		return baseMtRepository.findByBaseNo(baseNo);
	}

	@Override
	public Map<Integer, String> getBaseMtList() {

		Map<Integer, String> baseMtMap = new HashMap<Integer, String>();
		List<BaseMt> baseMtList = findActiveBaseMts();
		//baseMtMap.put(0, "Base No ( Wt )");
		for (BaseMt baseMt : baseMtList) {
			baseMtMap.put(baseMt.getId(),baseMt.getBaseNo()+"     "+"("+baseMt.getBaseWt()+"Wt)");
		}

		return baseMtMap;
	}

	@Override
	public List<BaseMt> findActiveBaseMts() {
		// QColor qColor = QColor.color;
		// BooleanExpression expression = qColor.deactive.eq(false);

		List<BaseMt> baseMtList = (List<BaseMt>) baseMtRepository.findAll();
		return baseMtList;
	}

}
